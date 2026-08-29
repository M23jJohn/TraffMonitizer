package com.ptrbrynt.kotlin_bloc.core;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: Add missing generic type declarations: [State] */
/* compiled from: Bloc.kt */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u001f\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"com/ptrbrynt/kotlin_bloc/core/Bloc$emitter$1", "Lcom/ptrbrynt/kotlin_bloc/core/Emitter;", "emit", "", "state", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitEach", "states", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Bloc$emitter$1<State> implements Emitter<State> {
    final /* synthetic */ Bloc<Event, State> this$0;

    Bloc$emitter$1(Bloc<Event, State> bloc) {
        this.this$0 = bloc;
    }

    @Override // com.ptrbrynt.kotlin_bloc.core.Emitter
    public Object emit(State state, Continuation<? super Unit> continuation) {
        Object emit = this.this$0.getMutableChangeFlow().emit(new Change<>(this.this$0.getState(), state), continuation);
        return emit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emit : Unit.INSTANCE;
    }

    @Override // com.ptrbrynt.kotlin_bloc.core.Emitter
    public Object emitEach(Flow<? extends State> flow, Continuation<? super Unit> continuation) {
        Job launchIn = FlowKt.launchIn(FlowKt.onEach(flow, new Bloc$emitter$1$emitEach$2(this, null)), this.this$0.getBlocScope());
        return launchIn == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? launchIn : Unit.INSTANCE;
    }
}
