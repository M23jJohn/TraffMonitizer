package com.tm;

import java.io.DataInputStream;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* renamed from: com.tm.h, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public abstract class AbstractC0099h {
    public static final byte[] a(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) (i >> (i2 * 8));
        }
        return bArr;
    }

    public static final long b(DataInputStream dataInputStream) {
        Intrinsics.checkNotNullParameter(dataInputStream, "<this>");
        dataInputStream.readFully(new byte[8]);
        return (r1[0] & 255) | (r1[7] << 56) | ((r1[6] & 255) << 48) | ((r1[5] & 255) << 40) | ((r1[4] & 255) << 32) | ((r1[3] & 255) << 24) | ((r1[2] & 255) << 16) | ((r1[1] & 255) << 8);
    }

    public static final String c(DataInputStream dataInputStream) {
        Intrinsics.checkNotNullParameter(dataInputStream, "<this>");
        byte[] bArr = new byte[a(dataInputStream)];
        dataInputStream.readFully(bArr);
        return new String(bArr, Charsets.UTF_8);
    }

    public static final byte[] a(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        byte[] plus = ArraysKt.plus(new byte[0], a(str.length()));
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return ArraysKt.plus(plus, bytes);
    }

    public static final int a(DataInputStream dataInputStream) {
        Intrinsics.checkNotNullParameter(dataInputStream, "<this>");
        byte[] bArr = new byte[4];
        dataInputStream.readFully(bArr);
        return (bArr[3] << 24) | ((bArr[2] & UByte.MAX_VALUE) << 16) | ((bArr[1] & UByte.MAX_VALUE) << 8) | (bArr[0] & UByte.MAX_VALUE);
    }
}
