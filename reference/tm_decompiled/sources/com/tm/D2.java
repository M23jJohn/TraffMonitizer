package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Bloc;
import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* loaded from: classes3.dex */
public final class D2 extends SuspendLambda implements Function2 {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ Function3 c;
    public final /* synthetic */ Bloc d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D2(C0137m2 c0137m2, Bloc bloc, Continuation continuation) {
        super(2, continuation);
        this.c = c0137m2;
        this.d = bloc;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        D2 d2 = new D2((C0137m2) this.c, this.d, continuation);
        d2.b = obj;
        return d2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((D2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.b;
            Function3 function3 = this.c;
            Emitter emitter = this.d.getEmitter();
            this.a = 1;
            if (function3.invoke(emitter, obj2, this) == coroutine_suspended) {
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
