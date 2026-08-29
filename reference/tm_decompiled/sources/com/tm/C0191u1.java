package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.u1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0191u1 implements FlowCollector {
    public final /* synthetic */ H1 a;

    public C0191u1(H1 h1) {
        this.a = h1;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        H1.a(this.a, (I1) obj);
        return Unit.INSTANCE;
    }
}
