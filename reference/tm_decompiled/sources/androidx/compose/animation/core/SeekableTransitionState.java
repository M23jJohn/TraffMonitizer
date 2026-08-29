package androidx.compose.animation.core;

import androidx.collection.MutableObjectList;
import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: Transition.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 Z*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002YZB\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010=\u001a\u00020\u001fH\u0002J\u000e\u0010>\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\u000e\u0010@\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\u000e\u0010A\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\u0018\u0010B\u001a\u00020\u001f2\u0006\u0010C\u001a\u0002072\u0006\u0010D\u001a\u00020\u0018H\u0002J\u0016\u0010E\u001a\u00020\u001f2\u0006\u0010\u0007\u001a\u00028\u0000H\u0086@¢\u0006\u0002\u0010FJ\"\u0010G\u001a\u00020\u001f2\b\b\u0001\u0010!\u001a\u00020 2\b\b\u0002\u0010\u0007\u001a\u00028\u0000H\u0086@¢\u0006\u0002\u0010HJ\u000e\u0010I\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\u000e\u0010J\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\b\u0010K\u001a\u00020\u001fH\u0002J*\u0010L\u001a\u00020\u001f2\b\b\u0002\u0010\u0007\u001a\u00028\u00002\u0010\b\u0002\u0010M\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010NH\u0086@¢\u0006\u0002\u0010OJ\u001b\u0010P\u001a\u00020\u001f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0010¢\u0006\u0002\bQJ\r\u0010R\u001a\u00020\u001fH\u0010¢\u0006\u0002\bSJ\r\u0010T\u001a\u00020\u001fH\u0000¢\u0006\u0002\bUJ\r\u0010V\u001a\u00020\u001fH\u0000¢\u0006\u0002\bWJ\b\u0010X\u001a\u00020\u001fH\u0002R+\u0010\u0007\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00028\u00008V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0005R+\u0010\r\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00028\u00008V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\f\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u0005R\u001c\u0010\u0011\u001a\u00028\u0000X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\t\"\u0004\b\u0013\u0010\u0005R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010!\u001a\u00020 2\u0006\u0010\u0006\u001a\u00020 8G@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010(\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010)X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020/X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020706X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001f0:X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001f0:X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006["}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState;", "S", "Landroidx/compose/animation/core/TransitionState;", "initialState", "<init>", "(Ljava/lang/Object;)V", "<set-?>", "targetState", "getTargetState", "()Ljava/lang/Object;", "setTargetState$animation_core", "targetState$delegate", "Landroidx/compose/runtime/MutableState;", "currentState", "getCurrentState", "setCurrentState$animation_core", "currentState$delegate", "composedTargetState", "getComposedTargetState$animation_core", "setComposedTargetState$animation_core", "Ljava/lang/Object;", "transition", "Landroidx/compose/animation/core/Transition;", "totalDurationNanos", "", "getTotalDurationNanos$animation_core", "()J", "setTotalDurationNanos$animation_core", "(J)V", "recalculateTotalDurationNanos", "Lkotlin/Function0;", "", "", "fraction", "getFraction", "()F", "setFraction", "(F)V", "fraction$delegate", "Landroidx/compose/runtime/MutableFloatState;", "compositionContinuation", "Lkotlinx/coroutines/CancellableContinuation;", "getCompositionContinuation$animation_core", "()Lkotlinx/coroutines/CancellableContinuation;", "setCompositionContinuation$animation_core", "(Lkotlinx/coroutines/CancellableContinuation;)V", "compositionContinuationMutex", "Lkotlinx/coroutines/sync/Mutex;", "getCompositionContinuationMutex$animation_core", "()Lkotlinx/coroutines/sync/Mutex;", "mutatorMutex", "Landroidx/compose/animation/core/MutatorMutex;", "lastFrameTimeNanos", "initialValueAnimations", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/animation/core/SeekableTransitionState$SeekingAnimationState;", "currentAnimation", "firstFrameLambda", "Lkotlin/Function1;", "durationScale", "animateOneFrameLambda", "endAllAnimations", "runAnimations", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doOneFrame", "animateOneFrame", "recalculateAnimationValue", "animation", "deltaPlayTimeNanos", "snapTo", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "seekTo", "(FLjava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "waitForCompositionAfterTargetStateChange", "waitForComposition", "moveAnimationToInitialState", "animateTo", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "(Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transitionConfigured", "transitionConfigured$animation_core", "transitionRemoved", "transitionRemoved$animation_core", "observeTotalDuration", "observeTotalDuration$animation_core", "onTotalDurationChanged", "onTotalDurationChanged$animation_core", "seekToFraction", "SeekingAnimationState", "Companion", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SeekableTransitionState<S> extends TransitionState<S> {
    private final Function1<Long, Unit> animateOneFrameLambda;
    private S composedTargetState;
    private CancellableContinuation<? super S> compositionContinuation;
    private final Mutex compositionContinuationMutex;
    private SeekingAnimationState currentAnimation;

    /* renamed from: currentState$delegate, reason: from kotlin metadata */
    private final MutableState currentState;
    private float durationScale;
    private final Function1<Long, Unit> firstFrameLambda;

    /* renamed from: fraction$delegate, reason: from kotlin metadata */
    private final MutableFloatState fraction;
    private final MutableObjectList<SeekingAnimationState> initialValueAnimations;
    private long lastFrameTimeNanos;
    private final MutatorMutex mutatorMutex;
    private final Function0<Unit> recalculateTotalDurationNanos;

    /* renamed from: targetState$delegate, reason: from kotlin metadata */
    private final MutableState targetState;
    private long totalDurationNanos;
    private Transition<S> transition;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final AnimationVector1D ZeroVelocity = new AnimationVector1D(0.0f);
    private static final AnimationVector1D Target1 = new AnimationVector1D(1.0f);

    public SeekableTransitionState(S s) {
        super(null);
        this.targetState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(s, null, 2, null);
        this.currentState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(s, null, 2, null);
        this.composedTargetState = s;
        this.recalculateTotalDurationNanos = new Function0() { // from class: androidx.compose.animation.core.SeekableTransitionState$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit recalculateTotalDurationNanos$lambda$0;
                recalculateTotalDurationNanos$lambda$0 = SeekableTransitionState.recalculateTotalDurationNanos$lambda$0(SeekableTransitionState.this);
                return recalculateTotalDurationNanos$lambda$0;
            }
        };
        this.fraction = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.compositionContinuationMutex = MutexKt.Mutex$default(false, 1, null);
        this.mutatorMutex = new MutatorMutex();
        this.lastFrameTimeNanos = Long.MIN_VALUE;
        this.initialValueAnimations = new MutableObjectList<>(0, 1, null);
        this.firstFrameLambda = new Function1() { // from class: androidx.compose.animation.core.SeekableTransitionState$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit firstFrameLambda$lambda$1;
                firstFrameLambda$lambda$1 = SeekableTransitionState.firstFrameLambda$lambda$1(SeekableTransitionState.this, ((Long) obj).longValue());
                return firstFrameLambda$lambda$1;
            }
        };
        this.animateOneFrameLambda = new Function1() { // from class: androidx.compose.animation.core.SeekableTransitionState$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit animateOneFrameLambda$lambda$4;
                animateOneFrameLambda$lambda$4 = SeekableTransitionState.animateOneFrameLambda$lambda$4(SeekableTransitionState.this, ((Long) obj).longValue());
                return animateOneFrameLambda$lambda$4;
            }
        };
    }

    @Override // androidx.compose.animation.core.TransitionState
    public S getTargetState() {
        return (S) this.targetState.getValue();
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void setTargetState$animation_core(S s) {
        this.targetState.setValue(s);
    }

    @Override // androidx.compose.animation.core.TransitionState
    public S getCurrentState() {
        return (S) this.currentState.getValue();
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void setCurrentState$animation_core(S s) {
        this.currentState.setValue(s);
    }

    public final S getComposedTargetState$animation_core() {
        return this.composedTargetState;
    }

    public final void setComposedTargetState$animation_core(S s) {
        this.composedTargetState = s;
    }

    /* renamed from: getTotalDurationNanos$animation_core, reason: from getter */
    public final long getTotalDurationNanos() {
        return this.totalDurationNanos;
    }

    public final void setTotalDurationNanos$animation_core(long j) {
        this.totalDurationNanos = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit recalculateTotalDurationNanos$lambda$0(SeekableTransitionState seekableTransitionState) {
        Transition<S> transition = seekableTransitionState.transition;
        seekableTransitionState.totalDurationNanos = transition != null ? transition.getTotalDurationNanos() : 0L;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFraction(float f) {
        this.fraction.setFloatValue(f);
    }

    public final float getFraction() {
        return this.fraction.getFloatValue();
    }

    public final CancellableContinuation<S> getCompositionContinuation$animation_core() {
        return this.compositionContinuation;
    }

    public final void setCompositionContinuation$animation_core(CancellableContinuation<? super S> cancellableContinuation) {
        this.compositionContinuation = cancellableContinuation;
    }

    /* renamed from: getCompositionContinuationMutex$animation_core, reason: from getter */
    public final Mutex getCompositionContinuationMutex() {
        return this.compositionContinuationMutex;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit firstFrameLambda$lambda$1(SeekableTransitionState seekableTransitionState, long j) {
        seekableTransitionState.lastFrameTimeNanos = j;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animateOneFrameLambda$lambda$4(SeekableTransitionState seekableTransitionState, long j) {
        long j2 = j - seekableTransitionState.lastFrameTimeNanos;
        seekableTransitionState.lastFrameTimeNanos = j;
        long roundToLong = MathKt.roundToLong(j2 / seekableTransitionState.durationScale);
        if (seekableTransitionState.initialValueAnimations.isNotEmpty()) {
            MutableObjectList<SeekingAnimationState> mutableObjectList = seekableTransitionState.initialValueAnimations;
            Object[] objArr = mutableObjectList.content;
            int i = mutableObjectList._size;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                SeekingAnimationState seekingAnimationState = (SeekingAnimationState) objArr[i3];
                seekableTransitionState.recalculateAnimationValue(seekingAnimationState, roundToLong);
                seekingAnimationState.setComplete(true);
            }
            Transition<S> transition = seekableTransitionState.transition;
            if (transition != null) {
                transition.updateInitialValues$animation_core();
            }
            MutableObjectList<SeekingAnimationState> mutableObjectList2 = seekableTransitionState.initialValueAnimations;
            int i4 = mutableObjectList2._size;
            Object[] objArr2 = mutableObjectList2.content;
            IntRange until = RangesKt.until(0, mutableObjectList2._size);
            int first = until.getFirst();
            int last = until.getLast();
            if (first <= last) {
                while (true) {
                    objArr2[first - i2] = objArr2[first];
                    if (((SeekingAnimationState) objArr2[first]).getIsComplete()) {
                        i2++;
                    }
                    if (first == last) {
                        break;
                    }
                    first++;
                }
            }
            ArraysKt.fill(objArr2, (Object) null, i4 - i2, i4);
            mutableObjectList2._size -= i2;
        }
        SeekingAnimationState seekingAnimationState2 = seekableTransitionState.currentAnimation;
        if (seekingAnimationState2 != null) {
            seekingAnimationState2.setDurationNanos(seekableTransitionState.totalDurationNanos);
            seekableTransitionState.recalculateAnimationValue(seekingAnimationState2, roundToLong);
            seekableTransitionState.setFraction(seekingAnimationState2.getValue());
            if (seekingAnimationState2.getValue() == 1.0f) {
                seekableTransitionState.currentAnimation = null;
            }
            seekableTransitionState.seekToFraction();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void endAllAnimations() {
        Transition<S> transition = this.transition;
        if (transition != null) {
            transition.clearInitialAnimations$animation_core();
        }
        this.initialValueAnimations.clear();
        if (this.currentAnimation != null) {
            this.currentAnimation = null;
            setFraction(1.0f);
            seekToFraction();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x006d, code lost:
    
        if (androidx.compose.runtime.MonotonicFrameClockKt.withFrameNanos(r10, r0) == r1) goto L40;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object runAnimations(Continuation<? super Unit> continuation) {
        SeekableTransitionState$runAnimations$1 seekableTransitionState$runAnimations$1;
        Object coroutine_suspended;
        int i;
        if (continuation instanceof SeekableTransitionState$runAnimations$1) {
            seekableTransitionState$runAnimations$1 = (SeekableTransitionState$runAnimations$1) continuation;
            if ((seekableTransitionState$runAnimations$1.label & Integer.MIN_VALUE) != 0) {
                seekableTransitionState$runAnimations$1.label -= Integer.MIN_VALUE;
                Object obj = seekableTransitionState$runAnimations$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = seekableTransitionState$runAnimations$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.initialValueAnimations.isEmpty() && this.currentAnimation == null) {
                        return Unit.INSTANCE;
                    }
                    if (SuspendAnimationKt.getDurationScale(seekableTransitionState$runAnimations$1.getContext()) == 0.0f) {
                        endAllAnimations();
                        this.lastFrameTimeNanos = Long.MIN_VALUE;
                        return Unit.INSTANCE;
                    }
                    if (this.lastFrameTimeNanos == Long.MIN_VALUE) {
                        Function1<Long, Unit> function1 = this.firstFrameLambda;
                        seekableTransitionState$runAnimations$1.label = 1;
                    }
                } else {
                    if (i != 1 && i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                do {
                    if (!this.initialValueAnimations.isNotEmpty() || this.currentAnimation != null) {
                        seekableTransitionState$runAnimations$1.label = 2;
                    } else {
                        this.lastFrameTimeNanos = Long.MIN_VALUE;
                        return Unit.INSTANCE;
                    }
                } while (animateOneFrame(seekableTransitionState$runAnimations$1) != coroutine_suspended);
                return coroutine_suspended;
            }
        }
        seekableTransitionState$runAnimations$1 = new SeekableTransitionState$runAnimations$1(this, continuation);
        Object obj2 = seekableTransitionState$runAnimations$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = seekableTransitionState$runAnimations$1.label;
        if (i != 0) {
        }
        do {
            if (!this.initialValueAnimations.isNotEmpty()) {
            }
            seekableTransitionState$runAnimations$1.label = 2;
        } while (animateOneFrame(seekableTransitionState$runAnimations$1) != coroutine_suspended);
        return coroutine_suspended;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object doOneFrame(Continuation<? super Unit> continuation) {
        if (this.lastFrameTimeNanos == Long.MIN_VALUE) {
            Object withFrameNanos = MonotonicFrameClockKt.withFrameNanos(this.firstFrameLambda, continuation);
            return withFrameNanos == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withFrameNanos : Unit.INSTANCE;
        }
        Object animateOneFrame = animateOneFrame(continuation);
        return animateOneFrame == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? animateOneFrame : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object animateOneFrame(Continuation<? super Unit> continuation) {
        float durationScale = SuspendAnimationKt.getDurationScale(continuation.getContext());
        if (durationScale <= 0.0f) {
            endAllAnimations();
            return Unit.INSTANCE;
        }
        this.durationScale = durationScale;
        Object withFrameNanos = MonotonicFrameClockKt.withFrameNanos(this.animateOneFrameLambda, continuation);
        return withFrameNanos == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withFrameNanos : Unit.INSTANCE;
    }

    private final void recalculateAnimationValue(SeekingAnimationState animation, long deltaPlayTimeNanos) {
        long progressNanos = animation.getProgressNanos() + deltaPlayTimeNanos;
        animation.setProgressNanos(progressNanos);
        long animationSpecDuration = animation.getAnimationSpecDuration();
        if (progressNanos >= animationSpecDuration) {
            animation.setValue(1.0f);
            return;
        }
        VectorizedAnimationSpec<AnimationVector1D> animationSpec = animation.getAnimationSpec();
        if (animationSpec != null) {
            AnimationVector1D start = animation.getStart();
            AnimationVector1D animationVector1D = Target1;
            AnimationVector1D initialVelocity = animation.getInitialVelocity();
            if (initialVelocity == null) {
                initialVelocity = ZeroVelocity;
            }
            animation.setValue(RangesKt.coerceIn(animationSpec.getValueFromNanos(progressNanos, start, animationVector1D, initialVelocity).get$animation_core(0), 0.0f, 1.0f));
            return;
        }
        float f = progressNanos / animationSpecDuration;
        animation.setValue((animation.getStart().get$animation_core(0) * (1 - f)) + (f * 1.0f));
    }

    public final Object snapTo(S s, Continuation<? super Unit> continuation) {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return Unit.INSTANCE;
        }
        if (Intrinsics.areEqual(getCurrentState(), s) && Intrinsics.areEqual(getTargetState(), s)) {
            return Unit.INSTANCE;
        }
        Object mutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new SeekableTransitionState$snapTo$2(this, s, transition, null), continuation, 1, null);
        return mutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? mutate$default : Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object seekTo$default(SeekableTransitionState seekableTransitionState, float f, Object obj, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = seekableTransitionState.getTargetState();
        }
        return seekableTransitionState.seekTo(f, obj, continuation);
    }

    public final Object seekTo(float f, S s, Continuation<? super Unit> continuation) {
        boolean z = false;
        if (0.0f <= f && f <= 1.0f) {
            z = true;
        }
        if (!z) {
            PreconditionsKt.throwIllegalArgumentException("Expecting fraction between 0 and 1. Got " + f);
        }
        Transition<S> transition = this.transition;
        if (transition == null) {
            return Unit.INSTANCE;
        }
        Object mutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new SeekableTransitionState$seekTo$3(s, getTargetState(), this, transition, f, null), continuation, 1, null);
        return mutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? mutate$default : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0050, code lost:
    
        if (kotlinx.coroutines.sync.Mutex.DefaultImpls.lock$default(r2, null, r0, 1, null) == r1) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object waitForCompositionAfterTargetStateChange(Continuation<? super Unit> continuation) {
        SeekableTransitionState$waitForCompositionAfterTargetStateChange$1 seekableTransitionState$waitForCompositionAfterTargetStateChange$1;
        int i;
        Object targetState;
        Object obj;
        if (continuation instanceof SeekableTransitionState$waitForCompositionAfterTargetStateChange$1) {
            seekableTransitionState$waitForCompositionAfterTargetStateChange$1 = (SeekableTransitionState$waitForCompositionAfterTargetStateChange$1) continuation;
            if ((seekableTransitionState$waitForCompositionAfterTargetStateChange$1.label & Integer.MIN_VALUE) != 0) {
                seekableTransitionState$waitForCompositionAfterTargetStateChange$1.label -= Integer.MIN_VALUE;
                Object obj2 = seekableTransitionState$waitForCompositionAfterTargetStateChange$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = seekableTransitionState$waitForCompositionAfterTargetStateChange$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    targetState = getTargetState();
                    Mutex mutex = this.compositionContinuationMutex;
                    seekableTransitionState$waitForCompositionAfterTargetStateChange$1.L$0 = targetState;
                    seekableTransitionState$waitForCompositionAfterTargetStateChange$1.label = 1;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        obj = seekableTransitionState$waitForCompositionAfterTargetStateChange$1.L$0;
                        ResultKt.throwOnFailure(obj2);
                        if (!Intrinsics.areEqual(obj2, obj)) {
                            this.lastFrameTimeNanos = Long.MIN_VALUE;
                            throw new CancellationException("snapTo() was canceled because state was changed to " + obj2 + " instead of " + obj);
                        }
                        return Unit.INSTANCE;
                    }
                    Object obj3 = seekableTransitionState$waitForCompositionAfterTargetStateChange$1.L$0;
                    ResultKt.throwOnFailure(obj2);
                    targetState = obj3;
                }
                if (!Intrinsics.areEqual(targetState, this.composedTargetState)) {
                    Mutex.DefaultImpls.unlock$default(this.compositionContinuationMutex, null, 1, null);
                    return Unit.INSTANCE;
                }
                seekableTransitionState$waitForCompositionAfterTargetStateChange$1.L$0 = targetState;
                seekableTransitionState$waitForCompositionAfterTargetStateChange$1.label = 2;
                SeekableTransitionState$waitForCompositionAfterTargetStateChange$1 seekableTransitionState$waitForCompositionAfterTargetStateChange$12 = seekableTransitionState$waitForCompositionAfterTargetStateChange$1;
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(seekableTransitionState$waitForCompositionAfterTargetStateChange$12), 1);
                cancellableContinuationImpl.initCancellability();
                setCompositionContinuation$animation_core(cancellableContinuationImpl);
                Mutex.DefaultImpls.unlock$default(getCompositionContinuationMutex(), null, 1, null);
                Object result = cancellableContinuationImpl.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(seekableTransitionState$waitForCompositionAfterTargetStateChange$12);
                }
                if (result != coroutine_suspended) {
                    obj = targetState;
                    obj2 = result;
                    if (!Intrinsics.areEqual(obj2, obj)) {
                    }
                    return Unit.INSTANCE;
                }
                return coroutine_suspended;
            }
        }
        seekableTransitionState$waitForCompositionAfterTargetStateChange$1 = new SeekableTransitionState$waitForCompositionAfterTargetStateChange$1(this, continuation);
        Object obj22 = seekableTransitionState$waitForCompositionAfterTargetStateChange$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = seekableTransitionState$waitForCompositionAfterTargetStateChange$1.label;
        if (i != 0) {
        }
        if (!Intrinsics.areEqual(targetState, this.composedTargetState)) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0050, code lost:
    
        if (kotlinx.coroutines.sync.Mutex.DefaultImpls.lock$default(r2, null, r0, 1, null) == r1) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object waitForComposition(Continuation<? super Unit> continuation) {
        SeekableTransitionState$waitForComposition$1 seekableTransitionState$waitForComposition$1;
        Object coroutine_suspended;
        int i;
        Object targetState;
        Object result;
        Object obj;
        if (continuation instanceof SeekableTransitionState$waitForComposition$1) {
            seekableTransitionState$waitForComposition$1 = (SeekableTransitionState$waitForComposition$1) continuation;
            if ((seekableTransitionState$waitForComposition$1.label & Integer.MIN_VALUE) != 0) {
                seekableTransitionState$waitForComposition$1.label -= Integer.MIN_VALUE;
                Object obj2 = seekableTransitionState$waitForComposition$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = seekableTransitionState$waitForComposition$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    targetState = getTargetState();
                    Mutex mutex = this.compositionContinuationMutex;
                    seekableTransitionState$waitForComposition$1.L$0 = targetState;
                    seekableTransitionState$waitForComposition$1.label = 1;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        obj = seekableTransitionState$waitForComposition$1.L$0;
                        ResultKt.throwOnFailure(obj2);
                        if (Intrinsics.areEqual(obj2, obj)) {
                            this.lastFrameTimeNanos = Long.MIN_VALUE;
                            throw new CancellationException("targetState while waiting for composition");
                        }
                        return Unit.INSTANCE;
                    }
                    Object obj3 = seekableTransitionState$waitForComposition$1.L$0;
                    ResultKt.throwOnFailure(obj2);
                    targetState = obj3;
                }
                seekableTransitionState$waitForComposition$1.L$0 = targetState;
                seekableTransitionState$waitForComposition$1.label = 2;
                SeekableTransitionState$waitForComposition$1 seekableTransitionState$waitForComposition$12 = seekableTransitionState$waitForComposition$1;
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(seekableTransitionState$waitForComposition$12), 1);
                cancellableContinuationImpl.initCancellability();
                setCompositionContinuation$animation_core(cancellableContinuationImpl);
                Mutex.DefaultImpls.unlock$default(getCompositionContinuationMutex(), null, 1, null);
                result = cancellableContinuationImpl.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(seekableTransitionState$waitForComposition$12);
                }
                if (result != coroutine_suspended) {
                    obj = targetState;
                    obj2 = result;
                    if (Intrinsics.areEqual(obj2, obj)) {
                    }
                }
                return coroutine_suspended;
            }
        }
        seekableTransitionState$waitForComposition$1 = new SeekableTransitionState$waitForComposition$1(this, continuation);
        Object obj22 = seekableTransitionState$waitForComposition$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = seekableTransitionState$waitForComposition$1.label;
        if (i != 0) {
        }
        seekableTransitionState$waitForComposition$1.L$0 = targetState;
        seekableTransitionState$waitForComposition$1.label = 2;
        SeekableTransitionState$waitForComposition$1 seekableTransitionState$waitForComposition$122 = seekableTransitionState$waitForComposition$1;
        CancellableContinuationImpl cancellableContinuationImpl2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(seekableTransitionState$waitForComposition$122), 1);
        cancellableContinuationImpl2.initCancellability();
        setCompositionContinuation$animation_core(cancellableContinuationImpl2);
        Mutex.DefaultImpls.unlock$default(getCompositionContinuationMutex(), null, 1, null);
        result = cancellableContinuationImpl2.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        }
        if (result != coroutine_suspended) {
        }
        return coroutine_suspended;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveAnimationToInitialState() {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return;
        }
        SeekingAnimationState seekingAnimationState = this.currentAnimation;
        if (seekingAnimationState == null) {
            if (this.totalDurationNanos <= 0 || getFraction() == 1.0f || Intrinsics.areEqual(getCurrentState(), getTargetState())) {
                seekingAnimationState = null;
            } else {
                seekingAnimationState = new SeekingAnimationState();
                seekingAnimationState.setValue(getFraction());
                long j = this.totalDurationNanos;
                seekingAnimationState.setDurationNanos(j);
                seekingAnimationState.setAnimationSpecDuration(MathKt.roundToLong(j * (1.0d - getFraction())));
                seekingAnimationState.getStart().set$animation_core(0, getFraction());
            }
        }
        if (seekingAnimationState != null) {
            seekingAnimationState.setDurationNanos(this.totalDurationNanos);
            this.initialValueAnimations.add(seekingAnimationState);
            transition.setInitialAnimations$animation_core(seekingAnimationState);
        }
        this.currentAnimation = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateTo$default(SeekableTransitionState seekableTransitionState, Object obj, FiniteAnimationSpec finiteAnimationSpec, Continuation continuation, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = seekableTransitionState.getTargetState();
        }
        if ((i & 2) != 0) {
            finiteAnimationSpec = null;
        }
        return seekableTransitionState.animateTo(obj, finiteAnimationSpec, continuation);
    }

    public final Object animateTo(S s, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super Unit> continuation) {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return Unit.INSTANCE;
        }
        Object mutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new SeekableTransitionState$animateTo$2(transition, this, s, finiteAnimationSpec, null), continuation, 1, null);
        return mutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? mutate$default : Unit.INSTANCE;
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void transitionConfigured$animation_core(Transition<S> transition) {
        Transition<S> transition2 = this.transition;
        if (!(transition2 == null || Intrinsics.areEqual(transition, transition2))) {
            PreconditionsKt.throwIllegalStateException("An instance of SeekableTransitionState has been used in different Transitions. Previous instance: " + this.transition + ", new instance: " + transition);
        }
        this.transition = transition;
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void transitionRemoved$animation_core() {
        this.transition = null;
        TransitionKt.getSeekableStateObserver().clear(this);
    }

    public final void observeTotalDuration$animation_core() {
        Function1 function1;
        SnapshotStateObserver seekableStateObserver = TransitionKt.getSeekableStateObserver();
        function1 = TransitionKt.SeekableTransitionStateTotalDurationChanged;
        seekableStateObserver.observeReads(this, function1, this.recalculateTotalDurationNanos);
    }

    public final void onTotalDurationChanged$animation_core() {
        long j = this.totalDurationNanos;
        observeTotalDuration$animation_core();
        long j2 = this.totalDurationNanos;
        if (j != j2) {
            SeekingAnimationState seekingAnimationState = this.currentAnimation;
            if (seekingAnimationState == null) {
                if (j2 != 0) {
                    seekToFraction();
                    return;
                }
                return;
            }
            long progressNanos = seekingAnimationState.getProgressNanos();
            long j3 = this.totalDurationNanos;
            if (progressNanos > j3) {
                endAllAnimations();
                return;
            }
            seekingAnimationState.setDurationNanos(j3);
            if (seekingAnimationState.getAnimationSpec() == null) {
                seekingAnimationState.setAnimationSpecDuration(MathKt.roundToLong((1.0d - seekingAnimationState.getStart().get$animation_core(0)) * this.totalDurationNanos));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void seekToFraction() {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return;
        }
        transition.seekAnimations$animation_core(MathKt.roundToLong(getFraction() * transition.getTotalDurationNanos()));
    }

    /* compiled from: Transition.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010*\u001a\u00020+H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u001a\u0010$\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0007\"\u0004\b&\u0010\tR\u001a\u0010'\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0007\"\u0004\b)\u0010\t¨\u0006,"}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState$SeekingAnimationState;", "", "<init>", "()V", "progressNanos", "", "getProgressNanos", "()J", "setProgressNanos", "(J)V", "animationSpec", "Landroidx/compose/animation/core/VectorizedAnimationSpec;", "Landroidx/compose/animation/core/AnimationVector1D;", "getAnimationSpec", "()Landroidx/compose/animation/core/VectorizedAnimationSpec;", "setAnimationSpec", "(Landroidx/compose/animation/core/VectorizedAnimationSpec;)V", "isComplete", "", "()Z", "setComplete", "(Z)V", "value", "", "getValue", "()F", "setValue", "(F)V", "start", "getStart", "()Landroidx/compose/animation/core/AnimationVector1D;", "setStart", "(Landroidx/compose/animation/core/AnimationVector1D;)V", "initialVelocity", "getInitialVelocity", "setInitialVelocity", "durationNanos", "getDurationNanos", "setDurationNanos", "animationSpecDuration", "getAnimationSpecDuration", "setAnimationSpecDuration", "toString", "", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class SeekingAnimationState {
        public static final int $stable = 8;
        private VectorizedAnimationSpec<AnimationVector1D> animationSpec;
        private long animationSpecDuration;
        private long durationNanos;
        private AnimationVector1D initialVelocity;
        private boolean isComplete;
        private long progressNanos;
        private AnimationVector1D start = new AnimationVector1D(0.0f);
        private float value;

        public final long getProgressNanos() {
            return this.progressNanos;
        }

        public final void setProgressNanos(long j) {
            this.progressNanos = j;
        }

        public final VectorizedAnimationSpec<AnimationVector1D> getAnimationSpec() {
            return this.animationSpec;
        }

        public final void setAnimationSpec(VectorizedAnimationSpec<AnimationVector1D> vectorizedAnimationSpec) {
            this.animationSpec = vectorizedAnimationSpec;
        }

        /* renamed from: isComplete, reason: from getter */
        public final boolean getIsComplete() {
            return this.isComplete;
        }

        public final void setComplete(boolean z) {
            this.isComplete = z;
        }

        public final float getValue() {
            return this.value;
        }

        public final void setValue(float f) {
            this.value = f;
        }

        public final AnimationVector1D getStart() {
            return this.start;
        }

        public final void setStart(AnimationVector1D animationVector1D) {
            this.start = animationVector1D;
        }

        public final AnimationVector1D getInitialVelocity() {
            return this.initialVelocity;
        }

        public final void setInitialVelocity(AnimationVector1D animationVector1D) {
            this.initialVelocity = animationVector1D;
        }

        public final long getDurationNanos() {
            return this.durationNanos;
        }

        public final void setDurationNanos(long j) {
            this.durationNanos = j;
        }

        public final long getAnimationSpecDuration() {
            return this.animationSpecDuration;
        }

        public final void setAnimationSpecDuration(long j) {
            this.animationSpecDuration = j;
        }

        public String toString() {
            return "progress nanos: " + this.progressNanos + ", animationSpec: " + this.animationSpec + ", isComplete: " + this.isComplete + ", value: " + this.value + ", start: " + this.start + ", initialVelocity: " + this.initialVelocity + ", durationNanos: " + this.durationNanos + ", animationSpecDuration: " + this.animationSpecDuration;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState$Companion;", "", "<init>", "()V", "ZeroVelocity", "Landroidx/compose/animation/core/AnimationVector1D;", "getZeroVelocity", "()Landroidx/compose/animation/core/AnimationVector1D;", "Target1", "getTarget1", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AnimationVector1D getZeroVelocity() {
            return SeekableTransitionState.ZeroVelocity;
        }

        public final AnimationVector1D getTarget1() {
            return SeekableTransitionState.Target1;
        }
    }
}
