package com.ptrbrynt.kotlin_bloc.core;

import androidx.core.view.MotionEventCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Add missing generic type declarations: [State] */
/* compiled from: Bloc.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Event", "State", "it"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.ptrbrynt.kotlin_bloc.core.Bloc$emitter$1$emitEach$2", f = "Bloc.kt", i = {}, l = {MotionEventCompat.AXIS_GENERIC_11}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class Bloc$emitter$1$emitEach$2<State> extends SuspendLambda implements Function2<State, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Bloc$emitter$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Bloc$emitter$1$emitEach$2(Bloc$emitter$1 bloc$emitter$1, Continuation<? super Bloc$emitter$1$emitEach$2> continuation) {
        super(2, continuation);
        this.this$0 = bloc$emitter$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Bloc$emitter$1$emitEach$2 bloc$emitter$1$emitEach$2 = new Bloc$emitter$1$emitEach$2(this.this$0, continuation);
        bloc$emitter$1$emitEach$2.L$0 = obj;
        return bloc$emitter$1$emitEach$2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return invoke2((Bloc$emitter$1$emitEach$2<State>) obj, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(State state, Continuation<? super Unit> continuation) {
        return ((Bloc$emitter$1$emitEach$2) create(state, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.L$0;
            this.label = 1;
            if (this.this$0.emit(obj2, this) == coroutine_suspended) {
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
