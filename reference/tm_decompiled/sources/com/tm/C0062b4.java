package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.b4, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0062b4 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public C0062b4(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        C0055a4 c0055a4;
        int i;
        if (continuation instanceof C0055a4) {
            c0055a4 = (C0055a4) continuation;
            int i2 = c0055a4.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c0055a4.b = i2 - Integer.MIN_VALUE;
                Object obj2 = c0055a4.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c0055a4.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    C0172r3 c0172r3 = (C0172r3) obj;
                    C0179s3 c0179s3 = new C0179s3(c0172r3.e, c0172r3.f, c0172r3.g);
                    c0055a4.b = 1;
                    if (flowCollector.emit(c0179s3, c0055a4) == coroutine_suspended) {
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
        c0055a4 = new C0055a4(this, continuation);
        Object obj22 = c0055a4.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0055a4.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
