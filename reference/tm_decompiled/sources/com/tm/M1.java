package com.tm;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class M1 {
    public final int a;
    public final String b;
    public final int c;
    public final P1 d;
    public final C0166q3 e;

    public M1(int i, String host, int i2, P1 listener, C0166q3 socketRepository) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(socketRepository, "socketRepository");
        this.a = i;
        this.b = host;
        this.c = i2;
        this.d = listener;
        this.e = socketRepository;
    }

    public abstract Object a(Continuation continuation);

    public abstract Object a(byte[] bArr, C0150o1 c0150o1);

    public abstract void a();

    public abstract void a(C0092g c0092g);
}
