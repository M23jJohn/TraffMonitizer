package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.d5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0077d5 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public C0077d5(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        C0070c5 c0070c5;
        int i;
        if (continuation instanceof C0070c5) {
            c0070c5 = (C0070c5) continuation;
            int i2 = c0070c5.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c0070c5.b = i2 - Integer.MIN_VALUE;
                Object obj2 = c0070c5.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c0070c5.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    Z0 z0 = (Z0) obj;
                    if (z0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.PongMessage");
                    }
                    c0070c5.b = 1;
                    if (flowCollector.emit((C0080e1) z0, c0070c5) == coroutine_suspended) {
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
        c0070c5 = new C0070c5(this, continuation);
        Object obj22 = c0070c5.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0070c5.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
