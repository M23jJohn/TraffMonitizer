package com.ptrbrynt.kotlin_bloc.core;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: Add missing generic type declarations: [E] */
/* compiled from: Bloc.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u0002H\u0003\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\u008a@"}, d2 = {"<anonymous>", "", "E", "Event", "State", "it"}, k = 3, mv = {1, 6, 0}, xi = 176)
@DebugMetadata(c = "com.ptrbrynt.kotlin_bloc.core.Bloc$on$1", f = "Bloc.kt", i = {}, l = {73}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class Bloc$on$1<E> extends SuspendLambda implements Function2<E, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<Emitter<State>, E, Continuation<? super Unit>, Object> $mapEventToState;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Bloc<Event, State> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public Bloc$on$1(Function3<? super Emitter<State>, ? super E, ? super Continuation<? super Unit>, ? extends Object> function3, Bloc<Event, State> bloc, Continuation<? super Bloc$on$1> continuation) {
        super(2, continuation);
        this.$mapEventToState = function3;
        this.this$0 = bloc;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Bloc$on$1 bloc$on$1 = new Bloc$on$1(this.$mapEventToState, this.this$0, continuation);
        bloc$on$1.L$0 = obj;
        return bloc$on$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return invoke2((Bloc$on$1<E>) obj, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(E e, Continuation<? super Unit> continuation) {
        return ((Bloc$on$1) create(e, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.L$0;
            Function3<Emitter<State>, E, Continuation<? super Unit>, Object> function3 = this.$mapEventToState;
            Emitter emitter = this.this$0.getEmitter();
            this.label = 1;
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
