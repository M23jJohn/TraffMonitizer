package com.tm;

import com.traffmonetizer.sdk.model.ServicePaused;
import com.traffmonetizer.sdk.model.ServiceStarted;
import com.traffmonetizer.sdk.model.ServiceStatus;
import com.traffmonetizer.sdk.model.ServiceStopped;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class M5 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public M5(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        L5 l5;
        int i;
        ServiceStatus servicePaused;
        if (continuation instanceof L5) {
            l5 = (L5) continuation;
            int i2 = l5.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                l5.b = i2 - Integer.MIN_VALUE;
                Object obj2 = l5.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = l5.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    P2 state = (P2) obj;
                    ServiceStatus.Companion.getClass();
                    Intrinsics.checkNotNullParameter(state, "state");
                    if (Intrinsics.areEqual(state, O2.b)) {
                        servicePaused = ServiceStarted.INSTANCE;
                    } else if (Intrinsics.areEqual(state, S2.b)) {
                        servicePaused = ServiceStopped.INSTANCE;
                    } else {
                        if (!(state instanceof M2)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        servicePaused = new ServicePaused(((M2) state).b.name());
                    }
                    l5.b = 1;
                    if (flowCollector.emit(servicePaused, l5) == coroutine_suspended) {
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
        l5 = new L5(this, continuation);
        Object obj22 = l5.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = l5.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
