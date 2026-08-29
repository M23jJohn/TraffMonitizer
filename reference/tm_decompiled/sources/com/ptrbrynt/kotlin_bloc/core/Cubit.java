package com.ptrbrynt.kotlin_bloc.core;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: Cubit.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u001f\u0010\n\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lcom/ptrbrynt/kotlin_bloc/core/Cubit;", "State", "Lcom/ptrbrynt/kotlin_bloc/core/BlocBase;", "Lcom/ptrbrynt/kotlin_bloc/core/Emitter;", "initial", "(Ljava/lang/Object;)V", "emit", "", "state", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitEach", "states", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Cubit<State> extends BlocBase<State> implements Emitter<State> {
    @Override // com.ptrbrynt.kotlin_bloc.core.Emitter
    public Object emit(State state, Continuation<? super Unit> continuation) {
        return emit$suspendImpl(this, state, continuation);
    }

    @Override // com.ptrbrynt.kotlin_bloc.core.Emitter
    public Object emitEach(Flow<? extends State> flow, Continuation<? super Unit> continuation) {
        return emitEach$suspendImpl(this, flow, continuation);
    }

    public Cubit(State state) {
        super(state);
    }

    static /* synthetic */ Object emit$suspendImpl(Cubit cubit, Object obj, Continuation continuation) {
        Object emit = cubit.getMutableChangeFlow().emit(new Change<>(cubit.getState(), obj), continuation);
        return emit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emit : Unit.INSTANCE;
    }

    static /* synthetic */ Object emitEach$suspendImpl(Cubit cubit, Flow flow, Continuation continuation) {
        Job launchIn = FlowKt.launchIn(FlowKt.onEach(flow, new Cubit$emitEach$2(cubit, null)), cubit.getBlocScope());
        return launchIn == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? launchIn : Unit.INSTANCE;
    }
}
