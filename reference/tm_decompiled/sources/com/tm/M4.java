package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class M4 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public M4(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        L4 l4;
        int i;
        if (continuation instanceof L4) {
            l4 = (L4) continuation;
            int i2 = l4.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                l4.b = i2 - Integer.MIN_VALUE;
                Object obj2 = l4.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = l4.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    if (((Z0) obj) instanceof C0059b1) {
                        l4.b = 1;
                        if (flowCollector.emit(obj, l4) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
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
        l4 = new L4(this, continuation);
        Object obj22 = l4.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = l4.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
