package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.y4, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0222y4 implements FlowCollector, SuspendFunction {
    public final /* synthetic */ FlowCollector a;

    public C0222y4(FlowCollector flowCollector) {
        this.a = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(Object obj, Continuation continuation) {
        C0215x4 c0215x4;
        int i;
        if (continuation instanceof C0215x4) {
            c0215x4 = (C0215x4) continuation;
            int i2 = c0215x4.b;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c0215x4.b = i2 - Integer.MIN_VALUE;
                Object obj2 = c0215x4.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c0215x4.b;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    FlowCollector flowCollector = this.a;
                    AbstractC0182t abstractC0182t = (AbstractC0182t) obj;
                    C0200v3 c0200v3 = new C0200v3(abstractC0182t.c(), abstractC0182t.d(), abstractC0182t.e(), abstractC0182t instanceof C0189u ? ((C0189u) abstractC0182t).f : null);
                    c0215x4.b = 1;
                    if (flowCollector.emit(c0200v3, c0215x4) == coroutine_suspended) {
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
        c0215x4 = new C0215x4(this, continuation);
        Object obj22 = c0215x4.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0215x4.b;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }
}
