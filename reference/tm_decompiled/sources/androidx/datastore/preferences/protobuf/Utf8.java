package androidx.datastore.preferences.protobuf;

import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes3.dex */
final class Utf8 {
    private static final long ASCII_MASK_LONG = -9187201950435737472L;
    static final int COMPLETE = 0;
    static final int MALFORMED = -1;
    static final int MAX_BYTES_PER_CHAR = 3;
    private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    private static final Processor processor;

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int byte1) {
        if (byte1 > -12) {
            return -1;
        }
        return byte1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int byte1, int byte2) {
        if (byte1 > -12 || byte2 > -65) {
            return -1;
        }
        return byte1 ^ (byte2 << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int byte1, int byte2, int byte3) {
        if (byte1 > -12 || byte2 > -65 || byte3 > -65) {
            return -1;
        }
        return (byte1 ^ (byte2 << 8)) ^ (byte3 << 16);
    }

    static {
        Processor safeProcessor;
        if (UnsafeProcessor.isAvailable() && !Android.isOnAndroidDevice()) {
            safeProcessor = new UnsafeProcessor();
        } else {
            safeProcessor = new SafeProcessor();
        }
        processor = safeProcessor;
    }

    static boolean isValidUtf8(byte[] bytes) {
        return processor.isValidUtf8(bytes, 0, bytes.length);
    }

    static boolean isValidUtf8(byte[] bytes, int index, int limit) {
        return processor.isValidUtf8(bytes, index, limit);
    }

    static int partialIsValidUtf8(int state, byte[] bytes, int index, int limit) {
        return processor.partialIsValidUtf8(state, bytes, index, limit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(byte[] bytes, int index, int limit) {
        byte b = bytes[index - 1];
        int i = limit - index;
        if (i == 0) {
            return incompleteStateFor(b);
        }
        if (i == 1) {
            return incompleteStateFor(b, bytes[index]);
        }
        if (i == 2) {
            return incompleteStateFor(b, bytes[index], bytes[index + 1]);
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(final ByteBuffer buffer, final int byte1, final int index, final int remaining) {
        if (remaining == 0) {
            return incompleteStateFor(byte1);
        }
        if (remaining == 1) {
            return incompleteStateFor(byte1, buffer.get(index));
        }
        if (remaining == 2) {
            return incompleteStateFor(byte1, buffer.get(index), buffer.get(index + 1));
        }
        throw new AssertionError();
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int index, int length) {
            super("Unpaired surrogate at index " + index + " of " + length);
        }
    }

    static int encodedLength(String string) {
        int length = string.length();
        int i = 0;
        while (i < length && string.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (true) {
            if (i < length) {
                char charAt = string.charAt(i);
                if (charAt >= 2048) {
                    i2 += encodedLengthGeneral(string, i);
                    break;
                }
                i2 += (127 - charAt) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (i2 >= length) {
            return i2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i2 + 4294967296L));
    }

    private static int encodedLengthGeneral(String string, int start) {
        int length = string.length();
        int i = 0;
        while (start < length) {
            char charAt = string.charAt(start);
            if (charAt < 2048) {
                i += (127 - charAt) >>> 31;
            } else {
                i += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(string, start) < 65536) {
                        throw new UnpairedSurrogateException(start, length);
                    }
                    start++;
                }
            }
            start++;
        }
        return i;
    }

    static int encode(String in, byte[] out, int offset, int length) {
        return processor.encodeUtf8(in, out, offset, length);
    }

    static boolean isValidUtf8(ByteBuffer buffer) {
        return processor.isValidUtf8(buffer, buffer.position(), buffer.remaining());
    }

    static int partialIsValidUtf8(int state, ByteBuffer buffer, int index, int limit) {
        return processor.partialIsValidUtf8(state, buffer, index, limit);
    }

    static String decodeUtf8(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
        return processor.decodeUtf8(buffer, index, size);
    }

    static String decodeUtf8(byte[] bytes, int index, int size) throws InvalidProtocolBufferException {
        return processor.decodeUtf8(bytes, index, size);
    }

    static void encodeUtf8(String in, ByteBuffer out) {
        processor.encodeUtf8(in, out);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int estimateConsecutiveAscii(ByteBuffer buffer, int index, int limit) {
        int i = limit - 7;
        int i2 = index;
        while (i2 < i && (buffer.getLong(i2) & (-9187201950435737472L)) == 0) {
            i2 += 8;
        }
        return i2 - index;
    }

    static abstract class Processor {
        abstract String decodeUtf8(byte[] bytes, int index, int size) throws InvalidProtocolBufferException;

        abstract String decodeUtf8Direct(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException;

        abstract int encodeUtf8(String in, byte[] out, int offset, int length);

        abstract void encodeUtf8Direct(String in, ByteBuffer out);

        abstract int partialIsValidUtf8(int state, byte[] bytes, int index, int limit);

        abstract int partialIsValidUtf8Direct(final int state, final ByteBuffer buffer, int index, final int limit);

        Processor() {
        }

        final boolean isValidUtf8(byte[] bytes, int index, int limit) {
            return partialIsValidUtf8(0, bytes, index, limit) == 0;
        }

        final boolean isValidUtf8(ByteBuffer buffer, int index, int limit) {
            return partialIsValidUtf8(0, buffer, index, limit) == 0;
        }

        final int partialIsValidUtf8(final int state, final ByteBuffer buffer, int index, final int limit) {
            if (buffer.hasArray()) {
                int arrayOffset = buffer.arrayOffset();
                return partialIsValidUtf8(state, buffer.array(), index + arrayOffset, arrayOffset + limit);
            }
            if (buffer.isDirect()) {
                return partialIsValidUtf8Direct(state, buffer, index, limit);
            }
            return partialIsValidUtf8Default(state, buffer, index, limit);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0017, code lost:
        
            if (r8.get(r9) > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x004c, code lost:
        
            if (r8.get(r9) > (-65)) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x008f, code lost:
        
            if (r8.get(r7) > (-65)) goto L53;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        final int partialIsValidUtf8Default(final int state, final ByteBuffer buffer, int index, final int limit) {
            byte b;
            int i;
            int i2;
            if (state != 0) {
                if (index >= limit) {
                    return state;
                }
                byte b2 = (byte) state;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        i2 = index + 1;
                    }
                    return -1;
                }
                if (b2 < -16) {
                    byte b3 = (byte) (~(state >> 8));
                    if (b3 == 0) {
                        int i3 = index + 1;
                        byte b4 = buffer.get(index);
                        if (i3 >= limit) {
                            return Utf8.incompleteStateFor(b2, b4);
                        }
                        index = i3;
                        b3 = b4;
                    }
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        i2 = index + 1;
                    }
                    return -1;
                }
                byte b5 = (byte) (~(state >> 8));
                if (b5 == 0) {
                    i = index + 1;
                    b5 = buffer.get(index);
                    if (i >= limit) {
                        return Utf8.incompleteStateFor(b2, b5);
                    }
                    b = 0;
                } else {
                    b = (byte) (state >> 16);
                    i = index;
                }
                if (b == 0) {
                    int i4 = i + 1;
                    byte b6 = buffer.get(i);
                    if (i4 >= limit) {
                        return Utf8.incompleteStateFor(b2, b5, b6);
                    }
                    b = b6;
                    i = i4;
                }
                if (b5 <= -65 && (((b2 << 28) + (b5 + 112)) >> 30) == 0 && b <= -65) {
                    index = i + 1;
                }
                return -1;
                index = i2;
            }
            return partialIsValidUtf8(buffer, index, limit);
        }

        private static int partialIsValidUtf8(final ByteBuffer buffer, int index, final int limit) {
            int estimateConsecutiveAscii = index + Utf8.estimateConsecutiveAscii(buffer, index, limit);
            while (estimateConsecutiveAscii < limit) {
                int i = estimateConsecutiveAscii + 1;
                byte b = buffer.get(estimateConsecutiveAscii);
                if (b >= 0) {
                    estimateConsecutiveAscii = i;
                } else if (b < -32) {
                    if (i >= limit) {
                        return b;
                    }
                    if (b < -62 || buffer.get(i) > -65) {
                        return -1;
                    }
                    estimateConsecutiveAscii += 2;
                } else {
                    if (b >= -16) {
                        if (i >= limit - 2) {
                            return Utf8.incompleteStateFor(buffer, b, i, limit - i);
                        }
                        int i2 = estimateConsecutiveAscii + 2;
                        byte b2 = buffer.get(i);
                        if (b2 <= -65 && (((b << 28) + (b2 + 112)) >> 30) == 0) {
                            int i3 = estimateConsecutiveAscii + 3;
                            if (buffer.get(i2) <= -65) {
                                estimateConsecutiveAscii += 4;
                                if (buffer.get(i3) > -65) {
                                }
                            }
                        }
                        return -1;
                    }
                    if (i >= limit - 1) {
                        return Utf8.incompleteStateFor(buffer, b, i, limit - i);
                    }
                    int i4 = estimateConsecutiveAscii + 2;
                    byte b3 = buffer.get(i);
                    if (b3 > -65 || ((b == -32 && b3 < -96) || ((b == -19 && b3 >= -96) || buffer.get(i4) > -65))) {
                        return -1;
                    }
                    estimateConsecutiveAscii += 3;
                }
            }
            return 0;
        }

        final String decodeUtf8(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
            if (buffer.hasArray()) {
                return decodeUtf8(buffer.array(), buffer.arrayOffset() + index, size);
            }
            if (buffer.isDirect()) {
                return decodeUtf8Direct(buffer, index, size);
            }
            return decodeUtf8Default(buffer, index, size);
        }

        final String decodeUtf8Default(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
            if ((index | size | ((buffer.limit() - index) - size)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(buffer.limit()), Integer.valueOf(index), Integer.valueOf(size)));
            }
            int i = index + size;
            char[] cArr = new char[size];
            int i2 = 0;
            while (index < i) {
                byte b = buffer.get(index);
                if (!DecodeUtil.isOneByte(b)) {
                    break;
                }
                index++;
                DecodeUtil.handleOneByte(b, cArr, i2);
                i2++;
            }
            int i3 = i2;
            while (index < i) {
                int i4 = index + 1;
                byte b2 = buffer.get(index);
                if (DecodeUtil.isOneByte(b2)) {
                    int i5 = i3 + 1;
                    DecodeUtil.handleOneByte(b2, cArr, i3);
                    while (i4 < i) {
                        byte b3 = buffer.get(i4);
                        if (!DecodeUtil.isOneByte(b3)) {
                            break;
                        }
                        i4++;
                        DecodeUtil.handleOneByte(b3, cArr, i5);
                        i5++;
                    }
                    i3 = i5;
                    index = i4;
                } else if (DecodeUtil.isTwoBytes(b2)) {
                    if (i4 >= i) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    index += 2;
                    DecodeUtil.handleTwoBytes(b2, buffer.get(i4), cArr, i3);
                    i3++;
                } else if (DecodeUtil.isThreeBytes(b2)) {
                    if (i4 >= i - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i6 = index + 2;
                    index += 3;
                    DecodeUtil.handleThreeBytes(b2, buffer.get(i4), buffer.get(i6), cArr, i3);
                    i3++;
                } else {
                    if (i4 >= i - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    byte b4 = buffer.get(i4);
                    int i7 = index + 3;
                    byte b5 = buffer.get(index + 2);
                    index += 4;
                    DecodeUtil.handleFourBytes(b2, b4, b5, buffer.get(i7), cArr, i3);
                    i3 += 2;
                }
            }
            return new String(cArr, 0, i3);
        }

        final void encodeUtf8(String in, ByteBuffer out) {
            if (out.hasArray()) {
                int arrayOffset = out.arrayOffset();
                Java8Compatibility.position(out, Utf8.encode(in, out.array(), out.position() + arrayOffset, out.remaining()) - arrayOffset);
            } else if (out.isDirect()) {
                encodeUtf8Direct(in, out);
            } else {
                encodeUtf8Default(in, out);
            }
        }

        final void encodeUtf8Default(String in, ByteBuffer out) {
            int length = in.length();
            int position = out.position();
            int i = 0;
            while (i < length) {
                try {
                    char charAt = in.charAt(i);
                    if (charAt >= 128) {
                        break;
                    }
                    out.put(position + i, (byte) charAt);
                    i++;
                } catch (IndexOutOfBoundsException unused) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(i) + " at index " + (out.position() + Math.max(i, (position - out.position()) + 1)));
                }
            }
            if (i == length) {
                Java8Compatibility.position(out, position + i);
                return;
            }
            position += i;
            while (i < length) {
                char charAt2 = in.charAt(i);
                if (charAt2 < 128) {
                    out.put(position, (byte) charAt2);
                } else if (charAt2 < 2048) {
                    int i2 = position + 1;
                    try {
                        out.put(position, (byte) ((charAt2 >>> 6) | 192));
                        out.put(i2, (byte) ((charAt2 & '?') | 128));
                        position = i2;
                    } catch (IndexOutOfBoundsException unused2) {
                        position = i2;
                        throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(i) + " at index " + (out.position() + Math.max(i, (position - out.position()) + 1)));
                    }
                } else if (charAt2 < 55296 || 57343 < charAt2) {
                    int i3 = position + 1;
                    out.put(position, (byte) ((charAt2 >>> '\f') | 224));
                    position += 2;
                    out.put(i3, (byte) (((charAt2 >>> 6) & 63) | 128));
                    out.put(position, (byte) ((charAt2 & '?') | 128));
                } else {
                    int i4 = i + 1;
                    if (i4 != length) {
                        try {
                            char charAt3 = in.charAt(i4);
                            if (Character.isSurrogatePair(charAt2, charAt3)) {
                                int codePoint = Character.toCodePoint(charAt2, charAt3);
                                int i5 = position + 1;
                                try {
                                    out.put(position, (byte) ((codePoint >>> 18) | 240));
                                    int i6 = position + 2;
                                    try {
                                        out.put(i5, (byte) (((codePoint >>> 12) & 63) | 128));
                                        position += 3;
                                        out.put(i6, (byte) (((codePoint >>> 6) & 63) | 128));
                                        out.put(position, (byte) ((codePoint & 63) | 128));
                                        i = i4;
                                    } catch (IndexOutOfBoundsException unused3) {
                                        i = i4;
                                        position = i6;
                                        throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(i) + " at index " + (out.position() + Math.max(i, (position - out.position()) + 1)));
                                    }
                                } catch (IndexOutOfBoundsException unused4) {
                                    position = i5;
                                    i = i4;
                                    throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(i) + " at index " + (out.position() + Math.max(i, (position - out.position()) + 1)));
                                }
                            } else {
                                i = i4;
                            }
                        } catch (IndexOutOfBoundsException unused5) {
                        }
                    }
                    throw new UnpairedSurrogateException(i, length);
                }
                i++;
                position++;
            }
            Java8Compatibility.position(out, position);
        }
    }

    static final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
        
            if (r8[r9] > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0046, code lost:
        
            if (r8[r9] > (-65)) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0083, code lost:
        
            if (r8[r7] > (-65)) goto L53;
         */
        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int partialIsValidUtf8(int state, byte[] bytes, int index, int limit) {
            byte b;
            int i;
            int i2;
            if (state != 0) {
                if (index >= limit) {
                    return state;
                }
                byte b2 = (byte) state;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        i2 = index + 1;
                    }
                    return -1;
                }
                if (b2 < -16) {
                    byte b3 = (byte) (~(state >> 8));
                    if (b3 == 0) {
                        int i3 = index + 1;
                        byte b4 = bytes[index];
                        if (i3 >= limit) {
                            return Utf8.incompleteStateFor(b2, b4);
                        }
                        index = i3;
                        b3 = b4;
                    }
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        i2 = index + 1;
                    }
                    return -1;
                }
                byte b5 = (byte) (~(state >> 8));
                if (b5 == 0) {
                    i = index + 1;
                    b5 = bytes[index];
                    if (i >= limit) {
                        return Utf8.incompleteStateFor(b2, b5);
                    }
                    b = 0;
                } else {
                    b = (byte) (state >> 16);
                    i = index;
                }
                if (b == 0) {
                    int i4 = i + 1;
                    byte b6 = bytes[i];
                    if (i4 >= limit) {
                        return Utf8.incompleteStateFor(b2, b5, b6);
                    }
                    b = b6;
                    i = i4;
                }
                if (b5 <= -65 && (((b2 << 28) + (b5 + 112)) >> 30) == 0 && b <= -65) {
                    index = i + 1;
                }
                return -1;
                index = i2;
            }
            return partialIsValidUtf8(bytes, index, limit);
        }

        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        int partialIsValidUtf8Direct(int state, ByteBuffer buffer, int index, int limit) {
            return partialIsValidUtf8Default(state, buffer, index, limit);
        }

        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        String decodeUtf8(byte[] bytes, int index, int size) throws InvalidProtocolBufferException {
            if ((index | size | ((bytes.length - index) - size)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bytes.length), Integer.valueOf(index), Integer.valueOf(size)));
            }
            int i = index + size;
            char[] cArr = new char[size];
            int i2 = 0;
            while (index < i) {
                byte b = bytes[index];
                if (!DecodeUtil.isOneByte(b)) {
                    break;
                }
                index++;
                DecodeUtil.handleOneByte(b, cArr, i2);
                i2++;
            }
            int i3 = i2;
            while (index < i) {
                int i4 = index + 1;
                byte b2 = bytes[index];
                if (DecodeUtil.isOneByte(b2)) {
                    int i5 = i3 + 1;
                    DecodeUtil.handleOneByte(b2, cArr, i3);
                    while (i4 < i) {
                        byte b3 = bytes[i4];
                        if (!DecodeUtil.isOneByte(b3)) {
                            break;
                        }
                        i4++;
                        DecodeUtil.handleOneByte(b3, cArr, i5);
                        i5++;
                    }
                    i3 = i5;
                    index = i4;
                } else if (DecodeUtil.isTwoBytes(b2)) {
                    if (i4 >= i) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    index += 2;
                    DecodeUtil.handleTwoBytes(b2, bytes[i4], cArr, i3);
                    i3++;
                } else if (DecodeUtil.isThreeBytes(b2)) {
                    if (i4 >= i - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i6 = index + 2;
                    index += 3;
                    DecodeUtil.handleThreeBytes(b2, bytes[i4], bytes[i6], cArr, i3);
                    i3++;
                } else {
                    if (i4 >= i - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    byte b4 = bytes[i4];
                    int i7 = index + 3;
                    byte b5 = bytes[index + 2];
                    index += 4;
                    DecodeUtil.handleFourBytes(b2, b4, b5, bytes[i7], cArr, i3);
                    i3 += 2;
                }
            }
            return new String(cArr, 0, i3);
        }

        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        String decodeUtf8Direct(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
            return decodeUtf8Default(buffer, index, size);
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        
            return r10 + r0;
         */
        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int encodeUtf8(String in, byte[] out, int offset, int length) {
            int i;
            int i2;
            char charAt;
            int length2 = in.length();
            int i3 = length + offset;
            int i4 = 0;
            while (i4 < length2 && (i2 = i4 + offset) < i3 && (charAt = in.charAt(i4)) < 128) {
                out[i2] = (byte) charAt;
                i4++;
            }
            int i5 = offset + i4;
            while (i4 < length2) {
                char charAt2 = in.charAt(i4);
                if (charAt2 < 128 && i5 < i3) {
                    out[i5] = (byte) charAt2;
                    i5++;
                } else if (charAt2 < 2048 && i5 <= i3 - 2) {
                    int i6 = i5 + 1;
                    out[i5] = (byte) ((charAt2 >>> 6) | 960);
                    i5 += 2;
                    out[i6] = (byte) ((charAt2 & '?') | 128);
                } else {
                    if ((charAt2 >= 55296 && 57343 >= charAt2) || i5 > i3 - 3) {
                        if (i5 <= i3 - 4) {
                            int i7 = i4 + 1;
                            if (i7 != in.length()) {
                                char charAt3 = in.charAt(i7);
                                if (Character.isSurrogatePair(charAt2, charAt3)) {
                                    int codePoint = Character.toCodePoint(charAt2, charAt3);
                                    out[i5] = (byte) ((codePoint >>> 18) | 240);
                                    out[i5 + 1] = (byte) (((codePoint >>> 12) & 63) | 128);
                                    int i8 = i5 + 3;
                                    out[i5 + 2] = (byte) (((codePoint >>> 6) & 63) | 128);
                                    i5 += 4;
                                    out[i8] = (byte) ((codePoint & 63) | 128);
                                    i4 = i7;
                                } else {
                                    i4 = i7;
                                }
                            }
                            throw new UnpairedSurrogateException(i4 - 1, length2);
                        }
                        if (55296 <= charAt2 && charAt2 <= 57343 && ((i = i4 + 1) == in.length() || !Character.isSurrogatePair(charAt2, in.charAt(i)))) {
                            throw new UnpairedSurrogateException(i4, length2);
                        }
                        throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i5);
                    }
                    out[i5] = (byte) ((charAt2 >>> '\f') | 480);
                    int i9 = i5 + 2;
                    out[i5 + 1] = (byte) (((charAt2 >>> 6) & 63) | 128);
                    i5 += 3;
                    out[i9] = (byte) ((charAt2 & '?') | 128);
                }
                i4++;
            }
            return i5;
        }

        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        void encodeUtf8Direct(String in, ByteBuffer out) {
            encodeUtf8Default(in, out);
        }

        private static int partialIsValidUtf8(byte[] bytes, int index, int limit) {
            while (index < limit && bytes[index] >= 0) {
                index++;
            }
            if (index >= limit) {
                return 0;
            }
            return partialIsValidUtf8NonAscii(bytes, index, limit);
        }

        private static int partialIsValidUtf8NonAscii(byte[] bytes, int index, int limit) {
            while (index < limit) {
                int i = index + 1;
                byte b = bytes[index];
                if (b < 0) {
                    if (b < -32) {
                        if (i >= limit) {
                            return b;
                        }
                        if (b >= -62) {
                            index += 2;
                            if (bytes[i] > -65) {
                            }
                        }
                        return -1;
                    }
                    if (b >= -16) {
                        if (i >= limit - 2) {
                            return Utf8.incompleteStateFor(bytes, i, limit);
                        }
                        int i2 = index + 2;
                        byte b2 = bytes[i];
                        if (b2 <= -65 && (((b << 28) + (b2 + 112)) >> 30) == 0) {
                            int i3 = index + 3;
                            if (bytes[i2] <= -65) {
                                index += 4;
                                if (bytes[i3] > -65) {
                                }
                            }
                        }
                        return -1;
                    }
                    if (i >= limit - 1) {
                        return Utf8.incompleteStateFor(bytes, i, limit);
                    }
                    int i4 = index + 2;
                    byte b3 = bytes[i];
                    if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                        index += 3;
                        if (bytes[i4] > -65) {
                        }
                    }
                    return -1;
                }
                index = i;
            }
            return 0;
        }
    }

    static final class UnsafeProcessor extends Processor {
        UnsafeProcessor() {
        }

        static boolean isAvailable() {
            return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
        
            if (androidx.datastore.preferences.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x009e, code lost:
        
            if (androidx.datastore.preferences.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L59;
         */
        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int partialIsValidUtf8(int state, byte[] bytes, final int index, final int limit) {
            long j;
            byte b = 0;
            if ((index | limit | (bytes.length - limit)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bytes.length), Integer.valueOf(index), Integer.valueOf(limit)));
            }
            long j2 = index;
            long j3 = limit;
            if (state != 0) {
                if (j2 >= j3) {
                    return state;
                }
                byte b2 = (byte) state;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        long j4 = 1 + j2;
                        if (UnsafeUtil.getByte(bytes, j2) <= -65) {
                            j2 = j4;
                        }
                    }
                    return -1;
                }
                if (b2 < -16) {
                    byte b3 = (byte) (~(state >> 8));
                    if (b3 == 0) {
                        long j5 = j2 + 1;
                        b3 = UnsafeUtil.getByte(bytes, j2);
                        if (j5 >= j3) {
                            return Utf8.incompleteStateFor(b2, b3);
                        }
                        j2 = j5;
                    }
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        j = j2 + 1;
                    }
                    return -1;
                }
                byte b4 = (byte) (~(state >> 8));
                if (b4 == 0) {
                    long j6 = j2 + 1;
                    b4 = UnsafeUtil.getByte(bytes, j2);
                    if (j6 >= j3) {
                        return Utf8.incompleteStateFor(b2, b4);
                    }
                    j2 = j6;
                } else {
                    b = (byte) (state >> 16);
                }
                if (b == 0) {
                    long j7 = j2 + 1;
                    b = UnsafeUtil.getByte(bytes, j2);
                    if (j7 >= j3) {
                        return Utf8.incompleteStateFor(b2, b4, b);
                    }
                    j2 = j7;
                }
                if (b4 <= -65 && (((b2 << 28) + (b4 + 112)) >> 30) == 0 && b <= -65) {
                    j = j2 + 1;
                }
                return -1;
                j2 = j;
            }
            return partialIsValidUtf8(bytes, j2, (int) (j3 - j2));
        }

        /* JADX WARN: Code restructure failed: missing block: B:34:0x0063, code lost:
        
            if (androidx.datastore.preferences.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00a8, code lost:
        
            if (androidx.datastore.preferences.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L59;
         */
        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int partialIsValidUtf8Direct(final int state, ByteBuffer buffer, final int index, final int limit) {
            long j;
            byte b = 0;
            if ((index | limit | (buffer.limit() - limit)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(buffer.limit()), Integer.valueOf(index), Integer.valueOf(limit)));
            }
            long addressOffset = UnsafeUtil.addressOffset(buffer) + index;
            long j2 = (limit - index) + addressOffset;
            if (state != 0) {
                if (addressOffset >= j2) {
                    return state;
                }
                byte b2 = (byte) state;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        long j3 = 1 + addressOffset;
                        if (UnsafeUtil.getByte(addressOffset) <= -65) {
                            addressOffset = j3;
                        }
                    }
                    return -1;
                }
                if (b2 < -16) {
                    byte b3 = (byte) (~(state >> 8));
                    if (b3 == 0) {
                        long j4 = addressOffset + 1;
                        b3 = UnsafeUtil.getByte(addressOffset);
                        if (j4 >= j2) {
                            return Utf8.incompleteStateFor(b2, b3);
                        }
                        addressOffset = j4;
                    }
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        j = addressOffset + 1;
                    }
                    return -1;
                }
                byte b4 = (byte) (~(state >> 8));
                if (b4 == 0) {
                    long j5 = addressOffset + 1;
                    b4 = UnsafeUtil.getByte(addressOffset);
                    if (j5 >= j2) {
                        return Utf8.incompleteStateFor(b2, b4);
                    }
                    addressOffset = j5;
                } else {
                    b = (byte) (state >> 16);
                }
                if (b == 0) {
                    long j6 = addressOffset + 1;
                    b = UnsafeUtil.getByte(addressOffset);
                    if (j6 >= j2) {
                        return Utf8.incompleteStateFor(b2, b4, b);
                    }
                    addressOffset = j6;
                }
                if (b4 <= -65 && (((b2 << 28) + (b4 + 112)) >> 30) == 0 && b <= -65) {
                    j = addressOffset + 1;
                }
                return -1;
                addressOffset = j;
            }
            return partialIsValidUtf8(addressOffset, (int) (j2 - addressOffset));
        }

        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        String decodeUtf8(byte[] bytes, int index, int size) throws InvalidProtocolBufferException {
            String str = new String(bytes, index, size, Internal.UTF_8);
            if (str.indexOf(okio.Utf8.REPLACEMENT_CODE_POINT) >= 0 && !Arrays.equals(str.getBytes(Internal.UTF_8), Arrays.copyOfRange(bytes, index, size + index))) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            return str;
        }

        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        String decodeUtf8Direct(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
            int i;
            if ((index | size | ((buffer.limit() - index) - size)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(buffer.limit()), Integer.valueOf(index), Integer.valueOf(size)));
            }
            long addressOffset = UnsafeUtil.addressOffset(buffer) + index;
            long j = size + addressOffset;
            char[] cArr = new char[size];
            int i2 = 0;
            while (addressOffset < j) {
                byte b = UnsafeUtil.getByte(addressOffset);
                if (!DecodeUtil.isOneByte(b)) {
                    break;
                }
                addressOffset++;
                DecodeUtil.handleOneByte(b, cArr, i2);
                i2++;
            }
            int i3 = i2;
            while (addressOffset < j) {
                long j2 = addressOffset + 1;
                byte b2 = UnsafeUtil.getByte(addressOffset);
                if (DecodeUtil.isOneByte(b2)) {
                    i = i3 + 1;
                    DecodeUtil.handleOneByte(b2, cArr, i3);
                    while (j2 < j) {
                        byte b3 = UnsafeUtil.getByte(j2);
                        if (!DecodeUtil.isOneByte(b3)) {
                            break;
                        }
                        j2++;
                        DecodeUtil.handleOneByte(b3, cArr, i);
                        i++;
                    }
                    addressOffset = j2;
                } else if (DecodeUtil.isTwoBytes(b2)) {
                    if (j2 >= j) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    addressOffset += 2;
                    DecodeUtil.handleTwoBytes(b2, UnsafeUtil.getByte(j2), cArr, i3);
                    i3++;
                } else if (DecodeUtil.isThreeBytes(b2)) {
                    if (j2 >= j - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    long j3 = 2 + addressOffset;
                    addressOffset += 3;
                    i = i3 + 1;
                    DecodeUtil.handleThreeBytes(b2, UnsafeUtil.getByte(j2), UnsafeUtil.getByte(j3), cArr, i3);
                } else {
                    if (j2 >= j - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    byte b4 = UnsafeUtil.getByte(j2);
                    long j4 = addressOffset + 3;
                    byte b5 = UnsafeUtil.getByte(2 + addressOffset);
                    addressOffset += 4;
                    DecodeUtil.handleFourBytes(b2, b4, b5, UnsafeUtil.getByte(j4), cArr, i3);
                    i3 += 2;
                }
                i3 = i;
            }
            return new String(cArr, 0, i3);
        }

        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        int encodeUtf8(final String in, final byte[] out, final int offset, final int length) {
            long j;
            long j2;
            long j3;
            int i;
            char charAt;
            long j4 = offset;
            long j5 = length + j4;
            int length2 = in.length();
            if (length2 > length || out.length - length < offset) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(length2 - 1) + " at index " + (offset + length));
            }
            int i2 = 0;
            while (true) {
                j = 1;
                if (i2 >= length2 || (charAt = in.charAt(i2)) >= 128) {
                    break;
                }
                UnsafeUtil.putByte(out, j4, (byte) charAt);
                i2++;
                j4 = 1 + j4;
            }
            if (i2 == length2) {
                return (int) j4;
            }
            while (i2 < length2) {
                char charAt2 = in.charAt(i2);
                if (charAt2 < 128 && j4 < j5) {
                    UnsafeUtil.putByte(out, j4, (byte) charAt2);
                    j3 = j5;
                    j2 = j;
                    j4 += j;
                } else if (charAt2 >= 2048 || j4 > j5 - 2) {
                    j2 = j;
                    if ((charAt2 >= 55296 && 57343 >= charAt2) || j4 > j5 - 3) {
                        j3 = j5;
                        if (j4 <= j3 - 4) {
                            int i3 = i2 + 1;
                            if (i3 != length2) {
                                char charAt3 = in.charAt(i3);
                                if (Character.isSurrogatePair(charAt2, charAt3)) {
                                    int codePoint = Character.toCodePoint(charAt2, charAt3);
                                    UnsafeUtil.putByte(out, j4, (byte) ((codePoint >>> 18) | 240));
                                    UnsafeUtil.putByte(out, j4 + j2, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j6 = j4 + 3;
                                    UnsafeUtil.putByte(out, j4 + 2, (byte) (((codePoint >>> 6) & 63) | 128));
                                    j4 += 4;
                                    UnsafeUtil.putByte(out, j6, (byte) ((codePoint & 63) | 128));
                                    i2 = i3;
                                } else {
                                    i2 = i3;
                                }
                            }
                            throw new UnpairedSurrogateException(i2 - 1, length2);
                        }
                        if (55296 > charAt2 || charAt2 > 57343 || ((i = i2 + 1) != length2 && Character.isSurrogatePair(charAt2, in.charAt(i)))) {
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + j4);
                        }
                        throw new UnpairedSurrogateException(i2, length2);
                    }
                    UnsafeUtil.putByte(out, j4, (byte) ((charAt2 >>> '\f') | 480));
                    j3 = j5;
                    long j7 = j4 + 2;
                    UnsafeUtil.putByte(out, j4 + j2, (byte) (((charAt2 >>> 6) & 63) | 128));
                    j4 += 3;
                    UnsafeUtil.putByte(out, j7, (byte) ((charAt2 & '?') | 128));
                } else {
                    j2 = j;
                    long j8 = j4 + j2;
                    UnsafeUtil.putByte(out, j4, (byte) ((charAt2 >>> 6) | 960));
                    j4 += 2;
                    UnsafeUtil.putByte(out, j8, (byte) ((charAt2 & '?') | 128));
                    j3 = j5;
                }
                i2++;
                j = j2;
                j5 = j3;
            }
            return (int) j4;
        }

        @Override // androidx.datastore.preferences.protobuf.Utf8.Processor
        void encodeUtf8Direct(String in, ByteBuffer out) {
            long j;
            char c;
            long j2;
            long j3;
            long j4;
            int i;
            char c2;
            char charAt;
            long addressOffset = UnsafeUtil.addressOffset(out);
            long position = out.position() + addressOffset;
            long limit = out.limit() + addressOffset;
            int length = in.length();
            if (length > limit - position) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(length - 1) + " at index " + out.limit());
            }
            int i2 = 0;
            while (true) {
                j = 1;
                c = 128;
                if (i2 >= length || (charAt = in.charAt(i2)) >= 128) {
                    break;
                }
                UnsafeUtil.putByte(position, (byte) charAt);
                i2++;
                position = 1 + position;
            }
            if (i2 == length) {
                Java8Compatibility.position(out, (int) (position - addressOffset));
                return;
            }
            while (i2 < length) {
                char charAt2 = in.charAt(i2);
                if (charAt2 >= c || position >= limit) {
                    j2 = j;
                    if (charAt2 < 2048 && position <= limit - 2) {
                        long j5 = position + j2;
                        UnsafeUtil.putByte(position, (byte) ((charAt2 >>> 6) | 960));
                        position += 2;
                        UnsafeUtil.putByte(j5, (byte) ((charAt2 & '?') | 128));
                        j3 = addressOffset;
                        j4 = limit;
                    } else {
                        if ((charAt2 >= 55296 && 57343 >= charAt2) || position > limit - 3) {
                            j3 = addressOffset;
                            j4 = limit;
                            if (position <= j4 - 4) {
                                int i3 = i2 + 1;
                                if (i3 != length) {
                                    char charAt3 = in.charAt(i3);
                                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                                        UnsafeUtil.putByte(position, (byte) ((codePoint >>> 18) | 240));
                                        c2 = 128;
                                        UnsafeUtil.putByte(position + j2, (byte) (((codePoint >>> 12) & 63) | 128));
                                        long j6 = position + 3;
                                        UnsafeUtil.putByte(position + 2, (byte) (((codePoint >>> 6) & 63) | 128));
                                        position += 4;
                                        UnsafeUtil.putByte(j6, (byte) ((codePoint & 63) | 128));
                                        i2 = i3;
                                    } else {
                                        i2 = i3;
                                    }
                                }
                                throw new UnpairedSurrogateException(i2 - 1, length);
                            }
                            if (55296 > charAt2 || charAt2 > 57343 || ((i = i2 + 1) != length && Character.isSurrogatePair(charAt2, in.charAt(i)))) {
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + position);
                            }
                            throw new UnpairedSurrogateException(i2, length);
                        }
                        UnsafeUtil.putByte(position, (byte) ((charAt2 >>> '\f') | 480));
                        j3 = addressOffset;
                        long j7 = position + 2;
                        j4 = limit;
                        UnsafeUtil.putByte(position + j2, (byte) (((charAt2 >>> 6) & 63) | 128));
                        position += 3;
                        UnsafeUtil.putByte(j7, (byte) ((charAt2 & '?') | 128));
                    }
                    c2 = 128;
                } else {
                    UnsafeUtil.putByte(position, (byte) charAt2);
                    j3 = addressOffset;
                    j4 = limit;
                    c2 = c;
                    position += j;
                    j2 = j;
                }
                i2++;
                c = c2;
                j = j2;
                addressOffset = j3;
                limit = j4;
            }
            Java8Compatibility.position(out, (int) (position - addressOffset));
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bytes, long offset, final int maxChars) {
            int i = 0;
            if (maxChars < 16) {
                return 0;
            }
            int i2 = 8 - (((int) offset) & 7);
            while (i < i2) {
                long j = 1 + offset;
                if (UnsafeUtil.getByte(bytes, offset) < 0) {
                    return i;
                }
                i++;
                offset = j;
            }
            while (true) {
                int i3 = i + 8;
                if (i3 > maxChars || (UnsafeUtil.getLong((Object) bytes, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + offset) & (-9187201950435737472L)) != 0) {
                    break;
                }
                offset += 8;
                i = i3;
            }
            while (i < maxChars) {
                long j2 = offset + 1;
                if (UnsafeUtil.getByte(bytes, offset) < 0) {
                    return i;
                }
                i++;
                offset = j2;
            }
            return maxChars;
        }

        private static int unsafeEstimateConsecutiveAscii(long address, final int maxChars) {
            if (maxChars < 16) {
                return 0;
            }
            int i = (int) ((-address) & 7);
            int i2 = i;
            while (i2 > 0) {
                long j = 1 + address;
                if (UnsafeUtil.getByte(address) < 0) {
                    return i - i2;
                }
                i2--;
                address = j;
            }
            int i3 = maxChars - i;
            while (i3 >= 8 && (UnsafeUtil.getLong(address) & (-9187201950435737472L)) == 0) {
                address += 8;
                i3 -= 8;
            }
            return maxChars - i3;
        }

        /* JADX WARN: Code restructure failed: missing block: B:69:0x0039, code lost:
        
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static int partialIsValidUtf8(final byte[] bytes, long offset, int remaining) {
            int unsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(bytes, offset, remaining);
            int i = remaining - unsafeEstimateConsecutiveAscii;
            long j = offset + unsafeEstimateConsecutiveAscii;
            while (true) {
                byte b = 0;
                while (true) {
                    if (i <= 0) {
                        break;
                    }
                    long j2 = j + 1;
                    b = UnsafeUtil.getByte(bytes, j);
                    if (b < 0) {
                        j = j2;
                        break;
                    }
                    i--;
                    j = j2;
                }
                if (i != 0) {
                    int i2 = i - 1;
                    if (b >= -32) {
                        if (b >= -16) {
                            if (i2 < 3) {
                                return unsafeIncompleteStateFor(bytes, b, j, i2);
                            }
                            i -= 4;
                            long j3 = 1 + j;
                            byte b2 = UnsafeUtil.getByte(bytes, j);
                            if (b2 > -65 || (((b << 28) + (b2 + 112)) >> 30) != 0) {
                                break;
                            }
                            long j4 = 2 + j;
                            if (UnsafeUtil.getByte(bytes, j3) > -65) {
                                break;
                            }
                            j += 3;
                            if (UnsafeUtil.getByte(bytes, j4) > -65) {
                                break;
                            }
                        } else {
                            if (i2 < 2) {
                                return unsafeIncompleteStateFor(bytes, b, j, i2);
                            }
                            i -= 3;
                            long j5 = 1 + j;
                            byte b3 = UnsafeUtil.getByte(bytes, j);
                            if (b3 > -65 || ((b == -32 && b3 < -96) || (b == -19 && b3 >= -96))) {
                                break;
                            }
                            j += 2;
                            if (UnsafeUtil.getByte(bytes, j5) > -65) {
                                break;
                            }
                        }
                    } else if (i2 != 0) {
                        i -= 2;
                        if (b < -62) {
                            break;
                        }
                        long j6 = 1 + j;
                        if (UnsafeUtil.getByte(bytes, j) > -65) {
                            break;
                        }
                        j = j6;
                    } else {
                        return b;
                    }
                } else {
                    return 0;
                }
            }
            return -1;
        }

        /* JADX WARN: Code restructure failed: missing block: B:69:0x0039, code lost:
        
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static int partialIsValidUtf8(long address, int remaining) {
            int unsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(address, remaining);
            long j = address + unsafeEstimateConsecutiveAscii;
            int i = remaining - unsafeEstimateConsecutiveAscii;
            while (true) {
                byte b = 0;
                while (true) {
                    if (i <= 0) {
                        break;
                    }
                    long j2 = j + 1;
                    b = UnsafeUtil.getByte(j);
                    if (b < 0) {
                        j = j2;
                        break;
                    }
                    i--;
                    j = j2;
                }
                if (i != 0) {
                    int i2 = i - 1;
                    if (b >= -32) {
                        if (b >= -16) {
                            if (i2 < 3) {
                                return unsafeIncompleteStateFor(j, b, i2);
                            }
                            i -= 4;
                            long j3 = 1 + j;
                            byte b2 = UnsafeUtil.getByte(j);
                            if (b2 > -65 || (((b << 28) + (b2 + 112)) >> 30) != 0) {
                                break;
                            }
                            long j4 = 2 + j;
                            if (UnsafeUtil.getByte(j3) > -65) {
                                break;
                            }
                            j += 3;
                            if (UnsafeUtil.getByte(j4) > -65) {
                                break;
                            }
                        } else {
                            if (i2 < 2) {
                                return unsafeIncompleteStateFor(j, b, i2);
                            }
                            i -= 3;
                            long j5 = 1 + j;
                            byte b3 = UnsafeUtil.getByte(j);
                            if (b3 > -65 || ((b == -32 && b3 < -96) || (b == -19 && b3 >= -96))) {
                                break;
                            }
                            j += 2;
                            if (UnsafeUtil.getByte(j5) > -65) {
                                break;
                            }
                        }
                    } else if (i2 != 0) {
                        i -= 2;
                        if (b < -62) {
                            break;
                        }
                        long j6 = 1 + j;
                        if (UnsafeUtil.getByte(j) > -65) {
                            break;
                        }
                        j = j6;
                    } else {
                        return b;
                    }
                } else {
                    return 0;
                }
            }
            return -1;
        }

        private static int unsafeIncompleteStateFor(byte[] bytes, int byte1, long offset, int remaining) {
            if (remaining == 0) {
                return Utf8.incompleteStateFor(byte1);
            }
            if (remaining == 1) {
                return Utf8.incompleteStateFor(byte1, UnsafeUtil.getByte(bytes, offset));
            }
            if (remaining == 2) {
                return Utf8.incompleteStateFor(byte1, UnsafeUtil.getByte(bytes, offset), UnsafeUtil.getByte(bytes, offset + 1));
            }
            throw new AssertionError();
        }

        private static int unsafeIncompleteStateFor(long address, final int byte1, int remaining) {
            if (remaining == 0) {
                return Utf8.incompleteStateFor(byte1);
            }
            if (remaining == 1) {
                return Utf8.incompleteStateFor(byte1, UnsafeUtil.getByte(address));
            }
            if (remaining == 2) {
                return Utf8.incompleteStateFor(byte1, UnsafeUtil.getByte(address), UnsafeUtil.getByte(address + 1));
            }
            throw new AssertionError();
        }
    }

    private static class DecodeUtil {
        private static char highSurrogate(int codePoint) {
            return (char) ((codePoint >>> 10) + okio.Utf8.HIGH_SURROGATE_HEADER);
        }

        private static boolean isNotTrailingByte(byte b) {
            return b > -65;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isOneByte(byte b) {
            return b >= 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isThreeBytes(byte b) {
            return b < -16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isTwoBytes(byte b) {
            return b < -32;
        }

        private static char lowSurrogate(int codePoint) {
            return (char) ((codePoint & 1023) + okio.Utf8.LOG_SURROGATE_HEADER);
        }

        private static int trailingByteValue(byte b) {
            return b & okio.Utf8.REPLACEMENT_BYTE;
        }

        private DecodeUtil() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleOneByte(byte byte1, char[] resultArr, int resultPos) {
            resultArr[resultPos] = (char) byte1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleTwoBytes(byte byte1, byte byte2, char[] resultArr, int resultPos) throws InvalidProtocolBufferException {
            if (byte1 < -62 || isNotTrailingByte(byte2)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            resultArr[resultPos] = (char) (((byte1 & 31) << 6) | trailingByteValue(byte2));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleThreeBytes(byte byte1, byte byte2, byte byte3, char[] resultArr, int resultPos) throws InvalidProtocolBufferException {
            if (isNotTrailingByte(byte2) || ((byte1 == -32 && byte2 < -96) || ((byte1 == -19 && byte2 >= -96) || isNotTrailingByte(byte3)))) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            resultArr[resultPos] = (char) (((byte1 & 15) << 12) | (trailingByteValue(byte2) << 6) | trailingByteValue(byte3));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleFourBytes(byte byte1, byte byte2, byte byte3, byte byte4, char[] resultArr, int resultPos) throws InvalidProtocolBufferException {
            if (isNotTrailingByte(byte2) || (((byte1 << 28) + (byte2 + 112)) >> 30) != 0 || isNotTrailingByte(byte3) || isNotTrailingByte(byte4)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            int trailingByteValue = ((byte1 & 7) << 18) | (trailingByteValue(byte2) << 12) | (trailingByteValue(byte3) << 6) | trailingByteValue(byte4);
            resultArr[resultPos] = highSurrogate(trailingByteValue);
            resultArr[resultPos + 1] = lowSurrogate(trailingByteValue);
        }
    }

    private Utf8() {
    }
}
