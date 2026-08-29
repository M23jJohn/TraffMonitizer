package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class B2 implements FlowCollector {
    public final /* synthetic */ FlowCollector a;

    public B2(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        A2 a2;
        int i;
        if (continuation instanceof A2) {
            a2 = (A2) continuation;
            int i2 = a2.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                a2.b = i2 - Integer.MIN_VALUE;
                Object obj2 = a2.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = a2.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    if (obj instanceof K2) {
                        a2.b = 1;
                        if (flowCollector.emit(obj, a2) == coroutine_suspended) {
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
        a2 = new A2(this, continuation);
        Object obj22 = a2.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = a2.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
