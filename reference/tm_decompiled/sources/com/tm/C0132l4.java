package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.l4, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0132l4 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public C0132l4(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        C0125k4 c0125k4;
        int i;
        if (continuation instanceof C0125k4) {
            c0125k4 = (C0125k4) continuation;
            int i2 = c0125k4.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c0125k4.b = i2 - Integer.MIN_VALUE;
                Object obj2 = c0125k4.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c0125k4.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    if (((Z0) obj) instanceof P0) {
                        c0125k4.b = 1;
                        if (flowCollector.emit(obj, c0125k4) == coroutine_suspended) {
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
        c0125k4 = new C0125k4(this, continuation);
        Object obj22 = c0125k4.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0125k4.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
