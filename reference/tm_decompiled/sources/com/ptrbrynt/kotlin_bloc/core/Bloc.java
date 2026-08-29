package com.ptrbrynt.kotlin_bloc.core;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: Bloc.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 !*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003:\u0001!B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005JT\u0010\u0013\u001a\u00020\u0011\"\n\b\u0002\u0010\u0014\u0018\u0001*\u00028\u000025\b\b\u0010\u0015\u001a/\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0004\u0012\u0002H\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0016¢\u0006\u0002\b\u0019H\u0084\bø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0005J\u001c\u0010\u001c\u001a\u00020\u00112\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001eH\u0014J\u0018\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000 *\b\u0012\u0004\u0012\u00028\u00000 H\u0014R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00078\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"Lcom/ptrbrynt/kotlin_bloc/core/Bloc;", "Event", "State", "Lcom/ptrbrynt/kotlin_bloc/core/BlocBase;", "initial", "(Ljava/lang/Object;)V", "emitter", "Lcom/ptrbrynt/kotlin_bloc/core/Emitter;", "getEmitter$annotations", "()V", "getEmitter", "()Lcom/ptrbrynt/kotlin_bloc/core/Emitter;", "eventFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "getEventFlow", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "add", "", NotificationCompat.CATEGORY_EVENT, DebugKt.DEBUG_PROPERTY_VALUE_ON, "E", "mapEventToState", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;)V", "onEvent", "onTransition", "transition", "Lcom/ptrbrynt/kotlin_bloc/core/Transition;", "transformEvents", "Lkotlinx/coroutines/flow/Flow;", "Companion", "core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Bloc<Event, State> extends BlocBase<State> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static BlocObserver observer = new SilentBlocObserver();
    private final Emitter<State> emitter;
    private final MutableSharedFlow<Event> eventFlow;

    public static /* synthetic */ void getEmitter$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public Flow<Event> transformEvents(Flow<? extends Event> flow) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        return flow;
    }

    public Bloc(State state) {
        super(state);
        MutableSharedFlow<Event> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this.eventFlow = MutableSharedFlow$default;
        FlowKt.launchIn(FlowKt.onEach(FlowKt.zip(FlowKt.onEach(MutableSharedFlow$default, new AnonymousClass1(this, null)), getMutableChangeFlow(), new AnonymousClass2(null)), new AnonymousClass3(this, null)), getBlocScope());
        this.emitter = new Bloc$emitter$1(this);
    }

    protected final MutableSharedFlow<Event> getEventFlow() {
        return this.eventFlow;
    }

    /* compiled from: Bloc.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Event", "State", "it"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.ptrbrynt.kotlin_bloc.core.Bloc$1", f = "Bloc.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.ptrbrynt.kotlin_bloc.core.Bloc$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<Event, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ Bloc<Event, State> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Bloc<Event, State> bloc, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = bloc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
            return invoke2((AnonymousClass1) obj, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Event event, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(event, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this$0.onEvent(this.L$0);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: Bloc.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0006H\u008a@"}, d2 = {"<anonymous>", "Lcom/ptrbrynt/kotlin_bloc/core/Transition;", "Event", "State", NotificationCompat.CATEGORY_EVENT, "change", "Lcom/ptrbrynt/kotlin_bloc/core/Change;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.ptrbrynt.kotlin_bloc.core.Bloc$2", f = "Bloc.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.ptrbrynt.kotlin_bloc.core.Bloc$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function3<Event, Change<State>, Continuation<? super Transition<Event, State>>, Object> {
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
        }

        public final Object invoke(Event event, Change<State> change, Continuation<? super Transition<Event, State>> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = event;
            anonymousClass2.L$1 = change;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((AnonymousClass2) obj, (Change) obj2, (Continuation<? super Transition<AnonymousClass2, State>>) obj3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.L$0;
            Change change = (Change) this.L$1;
            return new Transition(change.getState(), obj2, change.getNewState());
        }
    }

    /* compiled from: Bloc.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Event", "State", "it", "Lcom/ptrbrynt/kotlin_bloc/core/Transition;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.ptrbrynt.kotlin_bloc.core.Bloc$3", f = "Bloc.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.ptrbrynt.kotlin_bloc.core.Bloc$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<Transition<Event, State>, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ Bloc<Event, State> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Bloc<Event, State> bloc, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.this$0 = bloc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Transition<Event, State> transition, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(transition, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this$0.onTransition((Transition) this.L$0);
            return Unit.INSTANCE;
        }
    }

    public final Emitter<State> getEmitter() {
        return this.emitter;
    }

    protected final /* synthetic */ <E extends Event> void on(Function3<? super Emitter<State>, ? super E, ? super Continuation<? super Unit>, ? extends Object> mapEventToState) {
        Intrinsics.checkNotNullParameter(mapEventToState, "mapEventToState");
        Flow transformEvents = transformEvents(this.eventFlow);
        Intrinsics.needClassReification();
        Bloc$on$$inlined$filterIsInstance$1 bloc$on$$inlined$filterIsInstance$1 = new Bloc$on$$inlined$filterIsInstance$1(transformEvents);
        Intrinsics.needClassReification();
        FlowKt.launchIn(FlowKt.onEach(bloc$on$$inlined$filterIsInstance$1, new Bloc$on$1(mapEventToState, this, null)), getBlocScope());
    }

    public final void add(Event event) {
        BuildersKt__Builders_commonKt.launch$default(getBlocScope(), null, null, new Bloc$add$1(this, event, null), 3, null);
    }

    protected void onEvent(Event event) {
        observer.onEvent(this, event);
    }

    protected void onTransition(Transition<Event, State> transition) {
        Intrinsics.checkNotNullParameter(transition, "transition");
        observer.onTransition(this, transition);
    }

    /* compiled from: Bloc.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/ptrbrynt/kotlin_bloc/core/Bloc$Companion;", "", "()V", "observer", "Lcom/ptrbrynt/kotlin_bloc/core/BlocObserver;", "getObserver", "()Lcom/ptrbrynt/kotlin_bloc/core/BlocObserver;", "setObserver", "(Lcom/ptrbrynt/kotlin_bloc/core/BlocObserver;)V", "core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BlocObserver getObserver() {
            return Bloc.observer;
        }

        public final void setObserver(BlocObserver blocObserver) {
            Intrinsics.checkNotNullParameter(blocObserver, "<set-?>");
            Bloc.observer = blocObserver;
        }
    }
}
