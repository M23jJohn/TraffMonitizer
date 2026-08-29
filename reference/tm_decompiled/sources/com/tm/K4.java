package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class K4 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public K4(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        J4 j4;
        int i;
        if (continuation instanceof J4) {
            j4 = (J4) continuation;
            int i2 = j4.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                j4.b = i2 - Integer.MIN_VALUE;
                Object obj2 = j4.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = j4.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    C0059b1 c0059b1 = (C0059b1) obj;
                    K1 k1 = new K1(c0059b1.c, c0059b1.e, c0059b1.d);
                    j4.b = 1;
                    if (flowCollector.emit(k1, j4) == coroutine_suspended) {
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
        j4 = new J4(this, continuation);
        Object obj22 = j4.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = j4.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
