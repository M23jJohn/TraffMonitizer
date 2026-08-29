package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class K3 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public K3(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        J3 j3;
        int i;
        if (continuation instanceof J3) {
            j3 = (J3) continuation;
            int i2 = j3.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                j3.b = i2 - Integer.MIN_VALUE;
                Object obj2 = j3.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = j3.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    C0120k c0120k = (C0120k) obj;
                    C0106i c0106i = new C0106i(c0120k.e, c0120k.f);
                    j3.b = 1;
                    if (flowCollector.emit(c0106i, j3) == coroutine_suspended) {
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
        j3 = new J3(this, continuation);
        Object obj22 = j3.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = j3.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
