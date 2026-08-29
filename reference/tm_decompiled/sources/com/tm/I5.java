package com.tm;

import com.traffmonetizer.sdk.model.DebugMessage;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class I5 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public I5(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        H5 h5;
        int i;
        if (continuation instanceof H5) {
            h5 = (H5) continuation;
            int i2 = h5.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                h5.b = i2 - Integer.MIN_VALUE;
                Object obj2 = h5.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = h5.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    F0 f0 = (F0) obj;
                    Intrinsics.checkNotNullParameter(f0, "<this>");
                    DebugMessage debugMessage = new DebugMessage(f0.a, f0.b);
                    h5.b = 1;
                    if (flowCollector.emit(debugMessage, h5) == coroutine_suspended) {
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
        h5 = new H5(this, continuation);
        Object obj22 = h5.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = h5.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
