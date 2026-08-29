package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class E4 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public E4(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        D4 d4;
        int i;
        if (continuation instanceof D4) {
            d4 = (D4) continuation;
            int i2 = d4.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                d4.b = i2 - Integer.MIN_VALUE;
                Object obj2 = d4.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = d4.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    N1 n1 = new N1(((H0) obj).c);
                    d4.b = 1;
                    if (flowCollector.emit(n1, d4) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj2);
                }
                return Unit.INSTANCE;
            }
        }
        d4 = new D4(this, continuation);
        Object obj22 = d4.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = d4.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
