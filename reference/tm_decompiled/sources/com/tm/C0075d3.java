package com.tm;

import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.d3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0075d3 {
    public final boolean a;
    public Socket b;

    public C0075d3(boolean z) {
        this.a = z;
        Socket socket = this.b;
        if (socket != null) {
            socket.isConnected();
        }
    }

    public final void a(String host, int i) {
        Socket socket;
        Intrinsics.checkNotNullParameter(host, "host");
        if (this.b != null) {
            return;
        }
        try {
            if (this.a) {
                socket = SSLSocketFactory.getDefault().createSocket();
                socket.setTcpNoDelay(true);
                socket.setSendBufferSize(1048576);
                socket.setReceiveBufferSize(1048576);
                Intrinsics.checkNotNullExpressionValue(socket, "{\n            SSLSocketF…4\n            }\n        }");
            } else {
                socket = new Socket();
                socket.setTcpNoDelay(true);
                socket.setSendBufferSize(65536);
                socket.setReceiveBufferSize(65536);
            }
            this.b = socket;
            Intrinsics.checkNotNull(socket);
            socket.connect(new InetSocketAddress(host, i), 10000);
        } catch (Throwable th) {
            this.b = null;
            throw th;
        }
    }
}
