package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class U4 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public U4(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        T4 t4;
        int i;
        if (continuation instanceof T4) {
            t4 = (T4) continuation;
            int i2 = t4.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                t4.b = i2 - Integer.MIN_VALUE;
                Object obj2 = t4.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = t4.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    R5 r5 = (R5) obj;
                    S5 s5 = new S5(r5.c, r5.e, r5.d);
                    t4.b = 1;
                    if (flowCollector.emit(s5, t4) == coroutine_suspended) {
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
        t4 = new T4(this, continuation);
        Object obj22 = t4.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = t4.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
