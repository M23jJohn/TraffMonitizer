package com.tm;

import com.traffmonetizer.sdk.model.ConnectionConnected;
import com.traffmonetizer.sdk.model.ConnectionDisconnected;
import com.traffmonetizer.sdk.model.ConnectionFailed;
import com.traffmonetizer.sdk.model.ConnectionStatus;
import com.traffmonetizer.sdk.model.HelloConnecting;
import com.traffmonetizer.sdk.model.LoadBalancerResolving;
import com.traffmonetizer.sdk.model.SocketConnecting;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class F5 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public F5(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        E5 e5;
        int i;
        ConnectionStatus connectionStatus;
        if (continuation instanceof E5) {
            e5 = (E5) continuation;
            int i2 = e5.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                e5.b = i2 - Integer.MIN_VALUE;
                Object obj2 = e5.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = e5.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    AbstractC0079e0 state = (AbstractC0079e0) obj;
                    ConnectionStatus.Companion.getClass();
                    Intrinsics.checkNotNullParameter(state, "state");
                    if (Intrinsics.areEqual(state, Z.b)) {
                        connectionStatus = ConnectionConnected.INSTANCE;
                    } else if (Intrinsics.areEqual(state, C0051a0.b)) {
                        connectionStatus = ConnectionDisconnected.INSTANCE;
                    } else if (state instanceof C0065c0) {
                        connectionStatus = new ConnectionFailed(((C0065c0) state).b);
                    } else if (Intrinsics.areEqual(state, L0.b)) {
                        connectionStatus = HelloConnecting.INSTANCE;
                    } else if (Intrinsics.areEqual(state, W0.b)) {
                        connectionStatus = LoadBalancerResolving.INSTANCE;
                    } else {
                        if (!Intrinsics.areEqual(state, C0068c3.b)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        connectionStatus = SocketConnecting.INSTANCE;
                    }
                    e5.b = 1;
                    if (flowCollector.emit(connectionStatus, e5) == coroutine_suspended) {
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
        e5 = new E5(this, continuation);
        Object obj22 = e5.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = e5.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
