package com.tm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Z5 {
    public DatagramSocket a;

    public Z5() {
        DatagramSocket datagramSocket = this.a;
        if (datagramSocket != null) {
            datagramSocket.isConnected();
        }
    }

    public final byte[] a() {
        byte[] bArr = new byte[2048];
        DatagramPacket datagramPacket = new DatagramPacket(bArr, 2048);
        DatagramSocket datagramSocket = this.a;
        if (datagramSocket != null) {
            datagramSocket.receive(datagramPacket);
        }
        byte[] copyOf = Arrays.copyOf(bArr, datagramPacket.getLength());
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        return copyOf;
    }
}
