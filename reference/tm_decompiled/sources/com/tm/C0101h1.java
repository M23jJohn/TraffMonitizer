package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;

/* renamed from: com.tm.h1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0101h1 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ J2 b;
    public final /* synthetic */ H1 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0101h1(J2 j2, H1 h1, Continuation continuation) {
        super(2, continuation);
        this.b = j2;
        this.c = h1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0101h1(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0101h1(this.b, this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<State> stateFlow = this.b.getStateFlow();
            C0094g1 c0094g1 = new C0094g1(this.c);
            this.a = 1;
            if (stateFlow.collect(c0094g1, this) == coroutine_suspended) {
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
