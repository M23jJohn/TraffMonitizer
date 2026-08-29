package com.tm;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* renamed from: com.tm.g2, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0095g2 implements CoroutineScope {
    public final C0190u0 a;
    public final X b;
    public final J2 c;
    public final H1 d;
    public boolean e;

    public C0095g2(C0155p configRepository, C5 traffmonetizerRepository, S0 loadBalancer, C0166q3 socketRepository, A0 connectivityRepository) {
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        Intrinsics.checkNotNullParameter(traffmonetizerRepository, "traffmonetizerRepository");
        Intrinsics.checkNotNullParameter(loadBalancer, "loadBalancer");
        Intrinsics.checkNotNullParameter(socketRepository, "socketRepository");
        Intrinsics.checkNotNullParameter(connectivityRepository, "connectivityRepository");
        C0190u0 c0190u0 = new C0190u0(connectivityRepository, configRepository);
        this.a = c0190u0;
        X x = new X(loadBalancer, configRepository, socketRepository, traffmonetizerRepository, c0190u0);
        this.b = x;
        J2 j2 = new J2(x, c0190u0, traffmonetizerRepository, configRepository);
        this.c = j2;
        this.d = new H1(j2, traffmonetizerRepository, new O1(socketRepository), configRepository);
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new Z1(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new C0060b2(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new C0074d2(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new C0088f2(this, null), 3, null);
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public final CoroutineContext getCoroutineContext() {
        return Dispatchers.getDefault();
    }
}
