package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class S implements FlowCollector {
    public final /* synthetic */ X a;

    public S(X x) {
        this.a = x;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        C5 c5 = this.a.d;
        Object a = c5.a.a(new C0080e1(((C0148o) c5.b.a.getValue()).c), continuation);
        if (a != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            a = Unit.INSTANCE;
        }
        return a == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? a : Unit.INSTANCE;
    }
}
