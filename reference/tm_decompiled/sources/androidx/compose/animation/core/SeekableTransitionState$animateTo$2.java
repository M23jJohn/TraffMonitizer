package androidx.compose.animation.core;

import androidx.compose.animation.core.SeekableTransitionState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.sync.Mutex;

/* compiled from: Transition.kt */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$animateTo$2", f = "Transition.kt", i = {}, l = {600}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SeekableTransitionState$animateTo$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
    final /* synthetic */ S $targetState;
    final /* synthetic */ Transition<S> $transition;
    int label;
    final /* synthetic */ SeekableTransitionState<S> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SeekableTransitionState$animateTo$2(Transition<S> transition, SeekableTransitionState<S> seekableTransitionState, S s, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super SeekableTransitionState$animateTo$2> continuation) {
        super(1, continuation);
        this.$transition = transition;
        this.this$0 = seekableTransitionState;
        this.$targetState = s;
        this.$animationSpec = finiteAnimationSpec;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new SeekableTransitionState$animateTo$2(this.$transition, this.this$0, this.$targetState, this.$animationSpec, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((SeekableTransitionState$animateTo$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1", f = "Transition.kt", i = {0}, l = {2173, 613, 615, 669, 671}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv"}, s = {"L$0"})
    /* renamed from: androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
        final /* synthetic */ S $targetState;
        final /* synthetic */ Transition<S> $transition;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SeekableTransitionState<S> seekableTransitionState, S s, Transition<S> transition, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = seekableTransitionState;
            this.$targetState = s;
            this.$transition = transition;
            this.$animationSpec = finiteAnimationSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$targetState, this.$transition, this.$animationSpec, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0209, code lost:
        
            if (r2 == r0) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x01f4, code lost:
        
            if (r2 == r0) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00c0, code lost:
        
            if (r2 == r0) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00b1, code lost:
        
            if (r2 == r0) goto L75;
         */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00d2  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Mutex mutex;
            SeekableTransitionState seekableTransitionState;
            Object doOneFrame;
            Object waitForCompositionAfterTargetStateChange;
            Object runAnimations;
            SeekableTransitionState.SeekingAnimationState seekingAnimationState;
            SeekableTransitionState.Companion companion;
            AnimationVector1D zeroVelocity;
            SeekableTransitionState.Companion companion2;
            long roundToLong;
            SeekableTransitionState.Companion companion3;
            SeekableTransitionState.Companion companion4;
            SeekableTransitionState.Companion companion5;
            Object waitForComposition;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Object targetState = this.this$0.getTargetState();
                    if (!Intrinsics.areEqual(this.$targetState, targetState)) {
                        this.this$0.moveAnimationToInitialState();
                        this.this$0.setFraction(0.0f);
                        this.$transition.updateTarget$animation_core(this.$targetState);
                        this.$transition.setPlayTimeNanos(0L);
                        this.this$0.setCurrentState$animation_core(targetState);
                        this.this$0.setTargetState$animation_core(this.$targetState);
                    }
                    Mutex compositionContinuationMutex = this.this$0.getCompositionContinuationMutex();
                    SeekableTransitionState seekableTransitionState2 = this.this$0;
                    this.L$0 = compositionContinuationMutex;
                    this.L$1 = seekableTransitionState2;
                    this.label = 1;
                    if (compositionContinuationMutex.lock(null, this) != coroutine_suspended) {
                        mutex = compositionContinuationMutex;
                        seekableTransitionState = seekableTransitionState2;
                    }
                    return coroutine_suspended;
                }
                if (i == 1) {
                    seekableTransitionState = (SeekableTransitionState) this.L$1;
                    mutex = (Mutex) this.L$0;
                    ResultKt.throwOnFailure(obj);
                } else if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 3;
                    waitForCompositionAfterTargetStateChange = this.this$0.waitForCompositionAfterTargetStateChange(this);
                } else {
                    if (i == 3) {
                        ResultKt.throwOnFailure(obj);
                        if (!Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                            if (this.this$0.getFraction() < 1.0f) {
                                seekingAnimationState = ((SeekableTransitionState) this.this$0).currentAnimation;
                                FiniteAnimationSpec<Float> finiteAnimationSpec = this.$animationSpec;
                                VectorizedAnimationSpec vectorize = finiteAnimationSpec != null ? finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE)) : null;
                                if (seekingAnimationState == null || !Intrinsics.areEqual(vectorize, seekingAnimationState.getAnimationSpec())) {
                                    VectorizedAnimationSpec animationSpec = seekingAnimationState != null ? seekingAnimationState.getAnimationSpec() : null;
                                    if (animationSpec != null) {
                                        long progressNanos = seekingAnimationState.getProgressNanos();
                                        AnimationVector1D start = seekingAnimationState.getStart();
                                        companion4 = SeekableTransitionState.Companion;
                                        AnimationVector1D target1 = companion4.getTarget1();
                                        AnimationVector1D initialVelocity = seekingAnimationState.getInitialVelocity();
                                        if (initialVelocity == null) {
                                            companion5 = SeekableTransitionState.Companion;
                                            initialVelocity = companion5.getZeroVelocity();
                                        }
                                        zeroVelocity = (AnimationVector1D) animationSpec.getVelocityFromNanos(progressNanos, start, target1, initialVelocity);
                                    } else if (seekingAnimationState == null || seekingAnimationState.getProgressNanos() == 0) {
                                        companion = SeekableTransitionState.Companion;
                                        zeroVelocity = companion.getZeroVelocity();
                                    } else {
                                        long durationNanos = seekingAnimationState.getDurationNanos();
                                        if (durationNanos == Long.MIN_VALUE) {
                                            durationNanos = this.this$0.getTotalDurationNanos();
                                        }
                                        float f = durationNanos / 1.0E9f;
                                        if (f <= 0.0f) {
                                            companion2 = SeekableTransitionState.Companion;
                                            zeroVelocity = companion2.getZeroVelocity();
                                        } else {
                                            zeroVelocity = new AnimationVector1D(1.0f / f);
                                        }
                                    }
                                    if (seekingAnimationState == null) {
                                        seekingAnimationState = new SeekableTransitionState.SeekingAnimationState();
                                    }
                                    seekingAnimationState.setAnimationSpec(vectorize);
                                    seekingAnimationState.setComplete(false);
                                    seekingAnimationState.setValue(this.this$0.getFraction());
                                    seekingAnimationState.getStart().set$animation_core(0, this.this$0.getFraction());
                                    seekingAnimationState.setDurationNanos(this.this$0.getTotalDurationNanos());
                                    seekingAnimationState.setProgressNanos(0L);
                                    seekingAnimationState.setInitialVelocity(zeroVelocity);
                                    if (vectorize != null) {
                                        AnimationVector1D start2 = seekingAnimationState.getStart();
                                        companion3 = SeekableTransitionState.Companion;
                                        roundToLong = vectorize.getDurationNanos(start2, companion3.getTarget1(), zeroVelocity);
                                    } else {
                                        roundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - this.this$0.getFraction()));
                                    }
                                    seekingAnimationState.setAnimationSpecDuration(roundToLong);
                                    ((SeekableTransitionState) this.this$0).currentAnimation = seekingAnimationState;
                                }
                            }
                            this.L$0 = null;
                            this.L$1 = null;
                            this.label = 4;
                            runAnimations = this.this$0.runAnimations(this);
                        }
                        return Unit.INSTANCE;
                    }
                    if (i != 4) {
                        if (i != 5) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        this.this$0.setFraction(0.0f);
                        return Unit.INSTANCE;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.this$0.setCurrentState$animation_core(this.$targetState);
                    this.label = 5;
                    waitForComposition = this.this$0.waitForComposition(this);
                }
                Object composedTargetState$animation_core = seekableTransitionState.getComposedTargetState$animation_core();
                mutex.unlock(null);
                if (!Intrinsics.areEqual(this.$targetState, composedTargetState$animation_core)) {
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 2;
                    doOneFrame = this.this$0.doOneFrame(this);
                }
                if (!Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                }
                return Unit.INSTANCE;
            } catch (Throwable th) {
                mutex.unlock(null);
                throw th;
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(this.this$0, this.$targetState, this.$transition, this.$animationSpec, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.$transition.onTransitionEnd$animation_core();
        return Unit.INSTANCE;
    }
}
