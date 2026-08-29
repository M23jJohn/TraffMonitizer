package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class O implements FlowCollector {
    public final /* synthetic */ FlowCollector a;

    public O(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        N n;
        int i;
        if (continuation instanceof N) {
            n = (N) continuation;
            int i2 = n.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                n.b = i2 - Integer.MIN_VALUE;
                Object obj2 = n.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = n.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    if (obj instanceof AbstractC0058b0) {
                        n.b = 1;
                        if (flowCollector.emit(obj, n) == coroutine_suspended) {
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
        n = new N(this, continuation);
        Object obj22 = n.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = n.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
