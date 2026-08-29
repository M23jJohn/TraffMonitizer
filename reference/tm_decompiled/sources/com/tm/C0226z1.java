package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* renamed from: com.tm.z1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0226z1 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ H1 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0226z1(H1 h1, Continuation continuation) {
        super(2, continuation);
        this.b = h1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0226z1(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0226z1(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            X1 x1 = this.b.a.a.b;
            if (x1 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("receiver");
                x1 = null;
            }
            Flow filterNotNull = FlowKt.filterNotNull(x1.d);
            C0219y1 c0219y1 = new C0219y1(this.b);
            this.a = 1;
            Object collect = filterNotNull.collect(new G4(new I4(new E4(c0219y1))), this);
            if (collect != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                collect = Unit.INSTANCE;
            }
            if (collect != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                collect = Unit.INSTANCE;
            }
            if (collect != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                collect = Unit.INSTANCE;
            }
            if (collect == coroutine_suspended) {
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
