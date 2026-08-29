package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.r1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0170r1 implements FlowCollector {
    public final /* synthetic */ FlowCollector a;

    public C0170r1(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        C0164q1 c0164q1;
        int i;
        if (continuation instanceof C0164q1) {
            c0164q1 = (C0164q1) continuation;
            int i2 = c0164q1.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c0164q1.b = i2 - Integer.MIN_VALUE;
                Object obj2 = c0164q1.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c0164q1.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    if (obj instanceof J1) {
                        c0164q1.b = 1;
                        if (flowCollector.emit(obj, c0164q1) == coroutine_suspended) {
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
        c0164q1 = new C0164q1(this, continuation);
        Object obj22 = c0164q1.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0164q1.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
