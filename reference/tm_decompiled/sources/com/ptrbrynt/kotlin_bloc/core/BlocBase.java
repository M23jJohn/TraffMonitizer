package com.ptrbrynt.kotlin_bloc.core;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: BlocBase.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b0\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR \u0010\u000f\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00028\u0000@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/ptrbrynt/kotlin_bloc/core/BlocBase;", "State", "", "initial", "(Ljava/lang/Object;)V", "blocScope", "Lkotlinx/coroutines/CoroutineScope;", "getBlocScope", "()Lkotlinx/coroutines/CoroutineScope;", "mutableChangeFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/ptrbrynt/kotlin_bloc/core/Change;", "getMutableChangeFlow", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "<set-?>", "state", "getState", "()Ljava/lang/Object;", "Ljava/lang/Object;", "stateFlow", "Lkotlinx/coroutines/flow/Flow;", "getStateFlow", "()Lkotlinx/coroutines/flow/Flow;", "onChange", "", "change", "core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BlocBase<State> {
    private final CoroutineScope blocScope;
    private final MutableSharedFlow<Change<State>> mutableChangeFlow;
    private State state;
    private final Flow<State> stateFlow;

    public BlocBase(State state) {
        Bloc.INSTANCE.getObserver().onCreate(this);
        this.blocScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getUnconfined());
        MutableSharedFlow<Change<State>> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        BuildersKt__Builders_commonKt.launch$default(getBlocScope(), null, null, new BlocBase$mutableChangeFlow$1$1(MutableSharedFlow$default, this, null), 3, null);
        this.mutableChangeFlow = MutableSharedFlow$default;
        final MutableSharedFlow<Change<State>> mutableSharedFlow = MutableSharedFlow$default;
        this.stateFlow = new Flow<State>() { // from class: com.ptrbrynt.kotlin_bloc.core.BlocBase$special$$inlined$map$1

            /* compiled from: Collect.kt */
            @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\b"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 1, mv = {1, 6, 0}, xi = 48)
            /* renamed from: com.ptrbrynt.kotlin_bloc.core.BlocBase$special$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2 implements FlowCollector<Change<State>> {
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;

                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.ptrbrynt.kotlin_bloc.core.BlocBase$special$$inlined$map$1$2", f = "BlocBase.kt", i = {}, l = {137}, m = "emit", n = {}, s = {})
                /* renamed from: com.ptrbrynt.kotlin_bloc.core.BlocBase$special$$inlined$map$1$2$1, reason: invalid class name */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    int i;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                            Object obj2 = anonymousClass1.result;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            i = anonymousClass1.label;
                            if (i != 0) {
                                ResultKt.throwOnFailure(obj2);
                                FlowCollector flowCollector = this.$this_unsafeFlow$inlined;
                                Object newState = ((Change) obj).getNewState();
                                anonymousClass1.label = 1;
                                if (flowCollector.emit(newState, anonymousClass1) == coroutine_suspended) {
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
                    anonymousClass1 = new AnonymousClass1(continuation);
                    Object obj22 = anonymousClass1.result;
                    Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = anonymousClass1.label;
                    if (i != 0) {
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        };
        this.state = state;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final CoroutineScope getBlocScope() {
        return this.blocScope;
    }

    protected final MutableSharedFlow<Change<State>> getMutableChangeFlow() {
        return this.mutableChangeFlow;
    }

    public final Flow<State> getStateFlow() {
        return this.stateFlow;
    }

    public final State getState() {
        return this.state;
    }

    public void onChange(Change<State> change) {
        Intrinsics.checkNotNullParameter(change, "change");
        Bloc.INSTANCE.getObserver().onChange(this, change);
        this.state = change.getNewState();
    }
}
