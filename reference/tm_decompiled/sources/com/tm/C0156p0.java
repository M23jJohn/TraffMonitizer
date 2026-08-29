package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* renamed from: com.tm.p0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0156p0 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ C0190u0 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0156p0(C0190u0 c0190u0, Continuation continuation) {
        super(2, continuation);
        this.b = c0190u0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0156p0(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0156p0(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow drop = FlowKt.drop(FlowKt.distinctUntilChanged(new C0149o0(this.b.b.a)), 1);
            C0128l0 c0128l0 = new C0128l0(this.b);
            this.a = 1;
            if (drop.collect(c0128l0, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
