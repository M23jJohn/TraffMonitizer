# TraffMonetizer cli_v2 wire protocol (reverse-engineered from binary v1.3.3)

Reference binary: skopeo-extracted `traffmonetizer/cli_v2:latest` → `/tmp/tm-rootfs/usr/local/bin/cli` (stripped static-pie x86-64, ~2.7 MiB). Analysis artifacts: `/tmp/cli-disasm.txt` (full disassembly via llvm-objdump), `/tmp/tm-rootfs` (extracted image rootfs). File offset == virtual address in this binary.

## Frame format (CONFIRMED)

```
[1 byte: message code][16 bytes: instance id (UUID in GUID order)][payload fields...]
```

- No overall length prefix; the message code determines the payload field sequence.
- Integers: LITTLE-ENDIAN. u32 read 0x6f445 / u64 read 0x6f4ce / u32 write 0x6d282 — all raw, no byteswap. (The big-endian u16 helpers at 0x6ee65/0x6ee8a are DNS-only, in `ns_client.rs` — a red herring for this protocol.)
- String: 4-byte LE length + UTF-8 bytes, validated `0 < len <= 0x100000` (1 MiB) — read_string 0x6f8be (`cmpl $0x100000, %esi; jbe` at 0x6f955).
- Instance id: 16-byte UUID written in Microsoft-GUID (mixed-endian) order — `uuid::Uuid::to_bytes_le()`: encoder 0x6c0aa applies `rolw $8` to the first four u16s (load bytes 0-1 → ax, 2-3 → cx, 4-5 → dx, 6-7 → si; store cx at out+0, ax at out+2, dx at out+4, si at out+6) and copies the last 8 bytes verbatim. Net effect vs RFC-4122 in-memory bytes `m0..m15`: wire = `[m3, m2, m1, m0, m5, m4, m7, m6, m8..m15]`. Decoder 0x41907 is the exact inverse.
- Hello's u32 status field: statuses 0/1/2 = OK/warning/error family (cmp against 2 at 0x71134; `cmovbl` saturation `min(status,5)` into a u8 at 0x7114d-0x7115d in the response path) — a status code, not a success bool.

## Message codes (CONFIRMED, triple-verified)

Client → server: 1=Hello, 2=Bye, 3=Fetch, 4=Packet, 5=AcceptReady, 6=AcceptUnready, 7=Connect, 8=Eof, 9=StatMessage, 10=BallanceMessage, 11=UdpPacket, 12=PingMessage, 13=LogMessage, 14=SetNameMessage,  files15=GetNameMessage, 16=UdpBind, 17=AcceptContentDelivery, 18=ContentDeliveryStat, 19=ConnectMessageV2, 20=AckMessage.

Server → client: 101=HelloResponse, 102=ByeResponse, 103=FetchResponse, 104=PacketResponse, 105=AcceptReadyResponse, 106=AcceptUnreadyResponse, 107=ConnectResponse, 108=EofResponse, 109=StatResponse, 110=BallanceResponse, 111=PongResponse, 112=SetNameResponse, 113=GetNameResponse, 114=AcceptContentDeliveryResponse, 114=AcceptContentDeliveryResponse, 115=ContentDeliveryStatResponse.

## Field plans (per-code state machines)

Every message code has a "field plan" — an async state machine that serializes/deserializes its payload fields. Constructor table 0x1ad3ac (36 entries). Writer tail 0x423b5: emits code byte + GUID instance id, then jumps to the plan's poll function via `jmpq *%rax` (rax = table[0x1ad3ac + 4*code]).

- **Hello (plan 1)**: constructor 0x42a07, poll 0x71053. Fields: `[String][u32][String]`. Verified: state-0 arm 0x7107e reads a String first; state-1 arm 0x7111d reads u32 (deviceType), state-2 the version String. The 0x41eee arm builds the internal Hello struct with a "0.0.0.0" bind-address default (CLI-only concept; the reference SDK's com.tm.O0 also has the field, ignored on Android). Handshake call site 0x552c1-0x55317: builds token String (virtual call at *0x38) then `String("1.3.3")` (rdx=5, 0x6aae3) — the version is the bare version, no `/sdk` or `/app/` suffix.
- **HelloResponse (plan 21)**: constructor 0x42bc0, poll 0x719e8, states table 0x1ae5e0: 0→0x71a10, 1→0x71aed, 2→0x71af9, 3→0x already correct (byte+String) — response parsing structure was always right. `statusPair` [u8 success][String msg] confirmed at 0x71a23/0x71a67.
- **Plan-1 state machine (Hello/plan-1 family)**: sub-table 0x1ae5a8 states: 0→0x7107e (String), 1→0x717a2, 2→0x717bf, 3→0x71092 (String, *second* one), 4→0x7111d (u32), 5→0x7116c (String, *third*), 6→0x71819, 7→0x719a6, 7→0x719a6, 8→0x719b2, 9→0x7182c, 0x71875, 11→0x718fb.
- 0x21190/0x21220 are panic/assert stubs (case-split `ud2` on states 1/2) — not protocol code.

## HELLO handshake (flow, from logs + call sites)

1. Resolve balancer `blnc.traffmonetizer.com` (DNS A records, possibly TXT; ns_client.rs).
2. TLS connect (default SSLSocketFactory) to the resolved server on port 769.
3. Send Hello: `[1][GUID id][String token][u32 3][String "1.3.3"]`.
4. Await HelloResponse (101): `[u8 success][String message]`.
5. On success: enter steady state (ping loop, stat/balance polling, channel serving).

## Tunnel flow (from logs `do_tunnel start session=` etc.)

Server → node Connect/ConnectV2 (code 7/19): open TCP to host:port, reply ConnectResponse (107). Then bidirectional Packet (4) relay with Eof (8)/Ack (20) flow control. ConnectV2 carries feature keys "wnd"/"dns" (log `handle_connect_v2 start session=wnddns`). UdpBind (16) for UDP. Tunnel buffer 0x40000 (256 KiB), TCP buffers with u32 saturation, connect timeout 10 s, session timeout  flow control confirmed. Tunnel buffer 0x40000 (256 KiB).

## ENV config (binary defaults)

- `BASE_CONNECT_TIMEOUT_MS` = 10000 (10 s)
- `BASE_SESSION_TIMEOUT_MS` = 120000 (120 s)
- `BASE_TUNNEL_BUFFER_SIZE` = 0x40000 (256 KiB, min 0x1000)
- `BASE_TCP_BUFFER_SIZE` / `BASE_TCP_REMOTE_BUFFER_SIZE` = u32-saturating
- `BASE_LOG` = log level
- ping interval: 200 ms constant 0xBEBC200 at 0x6e969 (suspected) or the 1000 ms ContentDeliveryStat path at 0x2cbc2 (ContentDeliveryStat reporting, code 18, not ping) — the 20 s value in the app is the reference SDK's; kept as-is until proven otherwise.
- stat/balance polling: not pinned from the binary; app uses 30 s / 60 s via NodeManager. Server-driven otherwise.

## What remains unpinned (PROBABLE / UNKNOWN)

- Stat/Ballance response payload layouts beyond the status pair (traffic counts u64? balance f64 or integer cents?) — plan 9/10 arms at 0x76ac0/0x72bb4 region not fully decoded.
- UdpPacket (11) and UdpBind (16) exact field order (tunnel agent's salvage shows the Connect handler parses `u32 id, String host, u16 port`  — port is u16 BE in DNS-adjacent paths, LE in the messaging layer; treat as PROBABLE).
- Whether the server rejects/ignores an unknown version string (we now send bare "1.3.3"; the old SDK sent "1.2.11/sdk" and worked, so likely lenient).
- ContentDelivery messages (17/18/114/115) semantics (near-certainly mobile-irrelevant; the CLI uses them for CDN tests).
- Whether the server drives channel ids (we saw id extraction at 0x47276 `u32 id` from the Connect struct at +0xa4).
- Whether the 200 ms constant drives PING (vs ContentDeliveryStat reporting).
- ConnectResponse/EofResponse/PacketResponse exact layouts (started: response parsers table 0x1ae6ac entries 102-115 → 0x729a4..0x72e4a; per-arm field reading not yet decoded).

## App implementation mapping (after this rewrite)

- `MessageType.kt` — verified codes 1-20/101-115.
- `Wire.kt` — LE ints; String = LE-len + bytes; `instanceIdGuid()` = uuid.to_bytes_le() transform.
- `OutboundMessages.kt` — header = [code][GUID id]; Hello = header + String(token) + u32(3) + String("1.3.3").
- `InboundParser.kt` — type byte → enum via 101-115 mapping; statusPair = u8 + String; channel messages read per Connect/ConnectV2/Packet/Eof/Ack (channelId u32, host String, port u32-le-probable).
- `DeviceIdentity.kt` — UUID v3 (name-based, ANDROID_ID) → RFC-4122 bytes → GUID transform on write.
- `NodeConfig.kt` — SDK_VERSION 1.3.3; helloVersion = bare version.
- `TmConnection.kt` — `codeFor()` uses new codes; HELLO awaits code-101 response.
- `TraffMonetizerEngine.kt` — NodeConfig built without appVersion (removed field).

## Provenance & caveats

- Response codes 101-115 (byte-exact via 3 independent evidence chains: serializer dispatch jump tables 0x1ad31c/0x1ad370, Debug impl name dispatch 0x662a7 with tables 0x1ae1e0/0x1ae234, and the raw name blob at 0x1bcd81).
- UUID transform byte-exact vs 0x6c0aa: 5000/5000 random cases match my `instanceIdGuid`; round-trip through decoder 0x41907 inverts (2000/2000).
- Hello field order [String][u32][String] (plan-1 state machine, states 0→String, 1→u32, 2→String).
- HelloResponse = [u8][String] (plan 21, states 3→u8 0x6fc24, 4→String 0x6f8be).
- If the server still rejects the new HELLO, the next suspects are (in order): deviceType u32 value (reference used 3; binary's Hello struct has a u8 flag at +0x1c8 possibly deviceType — VERIFY), version string content (bare "1.3.3" verified as the built constant, but its field index within the plan needs one more check), and the "0.0.0.0" bind-address field (which the handshake code may additionally serialize as the third String if the internal struct includes it — but plan says 3 fields and we matched them all).
- The string-width/endianness primitives are the same for both codecs (messaging.rs vs ns_client.rs): LE u32 len + bytes.
- The app's current Stat/Ballance response reading (f64 balance as LongLE→Double.fromBits) is speculative — see "remains unpinned".
- **Bug fix log (runtime failure)**: server answers HELLO with type byte 101; old `fromCode()` rejected 101 (mapping was sequential 0-33, max 33) → parse error → HELLO_RESPONSE never parsed → timeout at 101s. Fixed by the code mapping rewrite. Second potential failure: missing GUID transform (server couldn't match instance id) → fixed by `instanceIdGuid()`.
- **Local verification**: kotlinc frontend type-check (all pure-Kotlin packages) passes with the rewritten files; CI (GitHub Actions `assembleDebug`) is the authoritative build.
- CI assumes a build-only file list — reference jar paths in `/tmp` may not survive reboot: kotlin-compiler-embeddable-1.9.24.jar from `~/.gradle/caches`, classpath annotations-13.0.jar + kotlin-stdlib + kotlinx-coroutines-core-jvm-1.6.4 + /tmp/roomjars + /tmp/clsall + android-34/android.jar; JDK /usr/lib/jvm/java-17-openjdk-arm64.
- Build env memory: Gradle 9.3.1 wrapper distro cannot download intact locally; Gradle 8.9 exists at /opt/gradle-8.9 but AGP 9.1.1 needs 9.x. Local full builds not possible; CI is the verifier.
- The 5-agent disassembly fan-out died mostly from the shared 8-req/min rate limit (3 to 429s, 1 watchdog stall, 1 stopped by coordinator); salvage from transcripts at /tmp/claude-0/.../tasks/*.output; the full protocol knowledge is now consolidated in this file and no longer depends on those transcripts.

## Reboot caveat

/tmp/tm-rootfs and /tmp/cli-disasm.txt are session artifacts and may not survive reboot. The binary can be re-extracted with: `skopeo copy docker://traffmonetizer/cli_v2:latest dir:/tmp/cli-img && tar -xf /tmp/cli-img/*.tar -C /tmp/tm-rootfs` (approximate; adjust layers). The disassembly was produced with `/data/data/com.termux/files/usr/bin/llvm-objdump -d` on the binary. If re-extraction is needed, all addresses in this file remain valid as long as the same image version (1.3.3) is used.
