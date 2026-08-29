package com.tm;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* renamed from: com.tm.z3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0228z3 extends Lambda implements Function1 {
    public final /* synthetic */ D3 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0228z3(D3 d3) {
        super(1);
        this.a = d3;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        long longValue = ((Number) obj).longValue();
        D3 d3 = this.a;
        P1 p1 = d3.d;
        H1 h1 = (H1) p1;
        BuildersKt__Builders_commonKt.launch$default(h1.g, null, null, new C0122k1(h1, d3.a, longValue, null), 3, null);
        return Unit.INSTANCE;
    }
}
