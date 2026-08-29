package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.a5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0056a5 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public C0056a5(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        Z4 z4;
        int i;
        if (continuation instanceof Z4) {
            z4 = (Z4) continuation;
            int i2 = z4.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                z4.b = i2 - Integer.MIN_VALUE;
                Object obj2 = z4.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = z4.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    if (((Z0) obj) instanceof C0080e1) {
                        z4.b = 1;
                        if (flowCollector.emit(obj, z4) == coroutine_suspended) {
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
        z4 = new Z4(this, continuation);
        Object obj22 = z4.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = z4.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
