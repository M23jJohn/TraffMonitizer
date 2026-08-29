package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.r0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0169r0 implements FlowCollector {
    public final /* synthetic */ FlowCollector a;

    public C0169r0(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        C0163q0 c0163q0;
        int i;
        if (continuation instanceof C0163q0) {
            c0163q0 = (C0163q0) continuation;
            int i2 = c0163q0.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c0163q0.b = i2 - Integer.MIN_VALUE;
                Object obj2 = c0163q0.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c0163q0.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    if (obj instanceof AbstractC0204w0) {
                        c0163q0.b = 1;
                        if (flowCollector.emit(obj, c0163q0) == coroutine_suspended) {
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
        c0163q0 = new C0163q0(this, continuation);
        Object obj22 = c0163q0.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0163q0.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
