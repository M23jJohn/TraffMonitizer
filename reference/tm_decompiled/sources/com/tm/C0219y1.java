package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.y1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0219y1 implements FlowCollector {
    public final /* synthetic */ H1 a;

    public C0219y1(H1 h1) {
        this.a = h1;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        H1 h1 = this.a;
        int i = ((N1) obj).a;
        int i2 = H1.i;
        h1.a(i, true);
        return Unit.INSTANCE;
    }
}
