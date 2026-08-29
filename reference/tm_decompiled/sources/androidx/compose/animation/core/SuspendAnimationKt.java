package androidx.compose.animation.core;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.ui.MotionDurationScale;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: SuspendAnimation.kt */
@Metadata(d1 = {"\u0000n\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u001ap\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u000726\u0010\b\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00010\tH\u0086@¢\u0006\u0002\u0010\u000e\u001a^\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u001026\u0010\b\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00010\tH\u0086@¢\u0006\u0002\u0010\u0011\u001a\u0096\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00162\u0006\u0010\u0002\u001a\u0002H\u00122\u0006\u0010\u0004\u001a\u0002H\u00122\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u0001H\u00122\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00120\u000726\u0010\b\u001a2\u0012\u0013\u0012\u0011H\u0012¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u0011H\u0012¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00010\tH\u0086@¢\u0006\u0002\u0010\u0017\u001aw\u0010\u0018\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00192\u0006\u0010\u0004\u001a\u0002H\u00122\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u001b2%\b\u0002\u0010\b\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d\u0012\u0004\u0012\u00020\u00010\u001c¢\u0006\u0002\b\u001eH\u0086@¢\u0006\u0002\u0010\u001f\u001am\u0010\u000f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00192\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00120 2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2%\b\u0002\u0010\b\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d\u0012\u0004\u0012\u00020\u00010\u001c¢\u0006\u0002\b\u001eH\u0086@¢\u0006\u0002\u0010!\u001as\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00192\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130#2\b\b\u0002\u0010$\u001a\u00020%2%\b\u0002\u0010\b\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d\u0012\u0004\u0012\u00020\u00010\u001c¢\u0006\u0002\b\u001eH\u0080@¢\u0006\u0002\u0010&\u001aW\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(\"\u0004\b\u0001\u0010\u0012\"\b\b\u0002\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130#2!\u0010)\u001a\u001d\u0012\u0013\u0012\u00110%¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(*\u0012\u0004\u0012\u0002H(0\u001cH\u0082@¢\u0006\u0002\u0010+\u001a<\u00100\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u0019H\u0000\u001a\u0085\u0001\u00102\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d2\u0006\u0010*\u001a\u00020%2\u0006\u0010,\u001a\u00020\u00032\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130#2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00192#\u0010\b\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d\u0012\u0004\u0012\u00020\u00010\u001c¢\u0006\u0002\b\u001eH\u0002\u001a\u0085\u0001\u00104\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d2\u0006\u0010*\u001a\u00020%2\u0006\u00105\u001a\u00020%2\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130#2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00192#\u0010\b\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d\u0012\u0004\u0012\u00020\u00010\u001c¢\u0006\u0002\b\u001eH\u0002\"\u0018\u0010,\u001a\u00020\u0003*\u00020-8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/¨\u00066"}, d2 = {"animate", "", "initialValue", "", "targetValue", "initialVelocity", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "value", "velocity", "(FFFLandroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateDecay", "Landroidx/compose/animation/core/FloatDecayAnimationSpec;", "(FFLandroidx/compose/animation/core/FloatDecayAnimationSpec;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "(Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateTo", "Landroidx/compose/animation/core/AnimationState;", "sequentialAnimation", "", "Lkotlin/Function1;", "Landroidx/compose/animation/core/AnimationScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/animation/core/AnimationState;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/compose/animation/core/DecayAnimationSpec;", "(Landroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/DecayAnimationSpec;ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animation", "Landroidx/compose/animation/core/Animation;", "startTimeNanos", "", "(Landroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/Animation;JLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callWithFrameNanos", "R", "onFrame", "frameTimeNanos", "(Landroidx/compose/animation/core/Animation;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "durationScale", "Lkotlin/coroutines/CoroutineContext;", "getDurationScale", "(Lkotlin/coroutines/CoroutineContext;)F", "updateState", "state", "doAnimationFrameWithScale", "anim", "doAnimationFrame", "playTimeNanos", "animation-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SuspendAnimationKt {
    public static /* synthetic */ Object animate$default(float f, float f2, float f3, AnimationSpec animationSpec, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            f3 = 0.0f;
        }
        if ((i & 8) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return animate(f, f2, f3, animationSpec, function2, continuation);
    }

    public static final Object animate(float f, float f2, float f3, AnimationSpec<Float> animationSpec, Function2<? super Float, ? super Float, Unit> function2, Continuation<? super Unit> continuation) {
        Object animate = animate(VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), Boxing.boxFloat(f), Boxing.boxFloat(f2), Boxing.boxFloat(f3), animationSpec, function2, continuation);
        return animate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? animate : Unit.INSTANCE;
    }

    public static final Object animateDecay(float f, float f2, FloatDecayAnimationSpec floatDecayAnimationSpec, final Function2<? super Float, ? super Float, Unit> function2, Continuation<? super Unit> continuation) {
        Object animate$default = animate$default(AnimationStateKt.AnimationState$default(f, f2, 0L, 0L, false, 28, null), AnimationKt.DecayAnimation(floatDecayAnimationSpec, f, f2), 0L, new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit animateDecay$lambda$0;
                animateDecay$lambda$0 = SuspendAnimationKt.animateDecay$lambda$0(Function2.this, (AnimationScope) obj);
                return animateDecay$lambda$0;
            }
        }, continuation, 2, null);
        return animate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? animate$default : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animateDecay$lambda$0(Function2 function2, AnimationScope animationScope) {
        function2.invoke(animationScope.getValue(), Float.valueOf(((AnimationVector1D) animationScope.getVelocityVector()).getValue()));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object animate$default(TwoWayConverter twoWayConverter, Object obj, Object obj2, Object obj3, AnimationSpec animationSpec, Function2 function2, Continuation continuation, int i, Object obj4) {
        if ((i & 8) != 0) {
            obj3 = null;
        }
        if ((i & 16) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return animate(twoWayConverter, obj, obj2, obj3, animationSpec, function2, continuation);
    }

    public static final <T, V extends AnimationVector> Object animate(final TwoWayConverter<T, V> twoWayConverter, T t, T t2, T t3, AnimationSpec<T> animationSpec, final Function2<? super T, ? super T, Unit> function2, Continuation<? super Unit> continuation) {
        V newInstance;
        if (t3 == null || (newInstance = twoWayConverter.getConvertToVector().invoke(t3)) == null) {
            newInstance = AnimationVectorsKt.newInstance(twoWayConverter.getConvertToVector().invoke(t));
        }
        AnimationVector animationVector = newInstance;
        Object animate$default = animate$default(new AnimationState(twoWayConverter, t, animationVector, 0L, 0L, false, 56, null), new TargetBasedAnimation(animationSpec, twoWayConverter, t, t2, animationVector), 0L, new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit animate$lambda$2;
                animate$lambda$2 = SuspendAnimationKt.animate$lambda$2(Function2.this, twoWayConverter, (AnimationScope) obj);
                return animate$lambda$2;
            }
        }, continuation, 2, null);
        return animate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? animate$default : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animate$lambda$2(Function2 function2, TwoWayConverter twoWayConverter, AnimationScope animationScope) {
        function2.invoke(animationScope.getValue(), twoWayConverter.getConvertFromVector().invoke(animationScope.getVelocityVector()));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateTo$default(AnimationState animationState, Object obj, AnimationSpec animationSpec, boolean z, Function1 function1, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i & 4) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    Unit animateTo$lambda$3;
                    animateTo$lambda$3 = SuspendAnimationKt.animateTo$lambda$3((AnimationScope) obj3);
                    return animateTo$lambda$3;
                }
            };
        }
        return animateTo(animationState, obj, animationSpec2, z2, function1, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animateTo$lambda$3(AnimationScope animationScope) {
        return Unit.INSTANCE;
    }

    public static final <T, V extends AnimationVector> Object animateTo(AnimationState<T, V> animationState, T t, AnimationSpec<T> animationSpec, boolean z, Function1<? super AnimationScope<T, V>, Unit> function1, Continuation<? super Unit> continuation) {
        Object animate = animate(animationState, new TargetBasedAnimation(animationSpec, animationState.getTypeConverter(), animationState.getValue(), t, animationState.getVelocityVector()), z ? animationState.getLastFrameTimeNanos() : Long.MIN_VALUE, function1, continuation);
        return animate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? animate : Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateDecay$default(AnimationState animationState, DecayAnimationSpec decayAnimationSpec, boolean z, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Unit animateDecay$lambda$4;
                    animateDecay$lambda$4 = SuspendAnimationKt.animateDecay$lambda$4((AnimationScope) obj2);
                    return animateDecay$lambda$4;
                }
            };
        }
        return animateDecay(animationState, decayAnimationSpec, z, function1, (Continuation<? super Unit>) continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animateDecay$lambda$4(AnimationScope animationScope) {
        return Unit.INSTANCE;
    }

    public static final <T, V extends AnimationVector> Object animateDecay(AnimationState<T, V> animationState, DecayAnimationSpec<T> decayAnimationSpec, boolean z, Function1<? super AnimationScope<T, V>, Unit> function1, Continuation<? super Unit> continuation) {
        Object animate = animate(animationState, new DecayAnimation((DecayAnimationSpec) decayAnimationSpec, (TwoWayConverter) animationState.getTypeConverter(), (Object) animationState.getValue(), (AnimationVector) animationState.getVelocityVector()), z ? animationState.getLastFrameTimeNanos() : Long.MIN_VALUE, function1, continuation);
        return animate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? animate : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00e5 A[Catch: CancellationException -> 0x004c, TRY_LEAVE, TryCatch #2 {CancellationException -> 0x004c, blocks: (B:15:0x0047, B:17:0x00d8, B:19:0x00e5, B:24:0x0108), top: B:14:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x011f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Type inference failed for: r12v0, types: [T, androidx.compose.animation.core.AnimationScope] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T, V extends AnimationVector> Object animate(final AnimationState<T, V> animationState, Animation<T, V> animation, long j, final Function1<? super AnimationScope<T, V>, Unit> function1, Continuation<? super Unit> continuation) {
        SuspendAnimationKt$animate$4 suspendAnimationKt$animate$4;
        SuspendAnimationKt$animate$4 suspendAnimationKt$animate$42;
        Object coroutine_suspended;
        int i;
        final Ref.ObjectRef objectRef;
        final AnimationState<T, V> animationState2;
        AnimationState<T, V> animationState3;
        final float durationScale;
        Ref.ObjectRef objectRef2;
        Function1<? super AnimationScope<T, V>, Unit> function12;
        AnimationScope animationScope;
        AnimationScope animationScope2;
        T t;
        Function1 function13;
        final Animation<T, V> animation2 = animation;
        if (continuation instanceof SuspendAnimationKt$animate$4) {
            suspendAnimationKt$animate$4 = (SuspendAnimationKt$animate$4) continuation;
            if ((suspendAnimationKt$animate$4.label & Integer.MIN_VALUE) != 0) {
                suspendAnimationKt$animate$4.label -= Integer.MIN_VALUE;
                suspendAnimationKt$animate$42 = suspendAnimationKt$animate$4;
                Object obj = suspendAnimationKt$animate$42.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = suspendAnimationKt$animate$42.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    final T valueFromNanos = animation2.getValueFromNanos(0L);
                    final V velocityVectorFromNanos = animation2.getVelocityVectorFromNanos(0L);
                    objectRef = new Ref.ObjectRef();
                    if (j == Long.MIN_VALUE) {
                        try {
                            durationScale = getDurationScale(suspendAnimationKt$animate$42.getContext());
                            animationState2 = animationState;
                        } catch (CancellationException e) {
                            e = e;
                            animationState2 = animationState;
                        }
                        try {
                            Function1 function14 = new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    Unit animate$lambda$8;
                                    animate$lambda$8 = SuspendAnimationKt.animate$lambda$8(Ref.ObjectRef.this, valueFromNanos, animation2, velocityVectorFromNanos, animationState2, durationScale, function1, ((Long) obj2).longValue());
                                    return animate$lambda$8;
                                }
                            };
                            objectRef2 = objectRef;
                            try {
                                suspendAnimationKt$animate$42.L$0 = animationState2;
                                suspendAnimationKt$animate$42.L$1 = animation2;
                                suspendAnimationKt$animate$42.L$2 = function1;
                                suspendAnimationKt$animate$42.L$3 = objectRef2;
                                suspendAnimationKt$animate$42.label = 1;
                                if (callWithFrameNanos(animation2, function14, suspendAnimationKt$animate$42) != coroutine_suspended) {
                                    animationState3 = animationState2;
                                    function12 = function1;
                                }
                                return coroutine_suspended;
                            } catch (CancellationException e2) {
                                e = e2;
                                animationState3 = animationState2;
                                objectRef = objectRef2;
                                animationScope = (AnimationScope) objectRef.element;
                                if (animationScope != null) {
                                }
                                animationScope2 = (AnimationScope) objectRef.element;
                                if (animationScope2 != null) {
                                    animationState3.setRunning$animation_core(false);
                                }
                                throw e;
                            }
                        } catch (CancellationException e3) {
                            e = e3;
                            animationState3 = animationState2;
                            animationScope = (AnimationScope) objectRef.element;
                            if (animationScope != null) {
                            }
                            animationScope2 = (AnimationScope) objectRef.element;
                            if (animationScope2 != null) {
                            }
                            throw e;
                        }
                    }
                    objectRef2 = objectRef;
                    try {
                        ?? r12 = (T) new AnimationScope(valueFromNanos, animation2.getTypeConverter(), velocityVectorFromNanos, j, animation2.getTargetValue(), j, true, new Function0() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                Unit animate$lambda$9;
                                animate$lambda$9 = SuspendAnimationKt.animate$lambda$9(AnimationState.this);
                                return animate$lambda$9;
                            }
                        });
                        doAnimationFrameWithScale(r12, j, getDurationScale(suspendAnimationKt$animate$42.getContext()), animation2, animationState, function1);
                        objectRef2.element = r12;
                        animationState3 = animationState;
                        animation2 = animation;
                        function12 = function1;
                    } catch (CancellationException e4) {
                        e = e4;
                        animationState3 = animationState;
                        objectRef = objectRef2;
                        animationScope = (AnimationScope) objectRef.element;
                        if (animationScope != null) {
                            animationScope.setRunning$animation_core(false);
                        }
                        animationScope2 = (AnimationScope) objectRef.element;
                        if (animationScope2 != null && animationScope2.getLastFrameTimeNanos() == animationState3.getLastFrameTimeNanos()) {
                            animationState3.setRunning$animation_core(false);
                        }
                        throw e;
                    }
                    objectRef = objectRef2;
                } else {
                    if (i != 1 && i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    objectRef = (Ref.ObjectRef) suspendAnimationKt$animate$42.L$3;
                    function12 = (Function1) suspendAnimationKt$animate$42.L$2;
                    animation2 = (Animation) suspendAnimationKt$animate$42.L$1;
                    animationState3 = (AnimationState) suspendAnimationKt$animate$42.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (CancellationException e5) {
                        e = e5;
                        animationScope = (AnimationScope) objectRef.element;
                        if (animationScope != null) {
                        }
                        animationScope2 = (AnimationScope) objectRef.element;
                        if (animationScope2 != null) {
                        }
                        throw e;
                    }
                }
                do {
                    t = objectRef.element;
                    Intrinsics.checkNotNull(t);
                    if (!((AnimationScope) t).isRunning()) {
                        final float durationScale2 = getDurationScale(suspendAnimationKt$animate$42.getContext());
                        final Ref.ObjectRef objectRef3 = objectRef;
                        final Function1<? super AnimationScope<T, V>, Unit> function15 = function12;
                        final Animation<T, V> animation3 = animation2;
                        final AnimationState<T, V> animationState4 = animationState3;
                        try {
                            function13 = new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    Unit animate$lambda$11;
                                    animate$lambda$11 = SuspendAnimationKt.animate$lambda$11(Ref.ObjectRef.this, durationScale2, animation3, animationState4, function15, ((Long) obj2).longValue());
                                    return animate$lambda$11;
                                }
                            };
                            objectRef = objectRef3;
                            animation2 = animation3;
                            animationState3 = animationState4;
                            function12 = function15;
                            suspendAnimationKt$animate$42.L$0 = animationState3;
                            suspendAnimationKt$animate$42.L$1 = animation2;
                            suspendAnimationKt$animate$42.L$2 = function12;
                            suspendAnimationKt$animate$42.L$3 = objectRef;
                            suspendAnimationKt$animate$42.label = 2;
                        } catch (CancellationException e6) {
                            e = e6;
                            objectRef = objectRef3;
                            animationState3 = animationState4;
                            animationScope = (AnimationScope) objectRef.element;
                            if (animationScope != null) {
                            }
                            animationScope2 = (AnimationScope) objectRef.element;
                            if (animationScope2 != null) {
                            }
                            throw e;
                        }
                    } else {
                        return Unit.INSTANCE;
                    }
                } while (callWithFrameNanos(animation2, function13, suspendAnimationKt$animate$42) != coroutine_suspended);
                return coroutine_suspended;
            }
        }
        suspendAnimationKt$animate$4 = new SuspendAnimationKt$animate$4(continuation);
        suspendAnimationKt$animate$42 = suspendAnimationKt$animate$4;
        Object obj2 = suspendAnimationKt$animate$42.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = suspendAnimationKt$animate$42.label;
        if (i != 0) {
        }
        do {
            t = objectRef.element;
            Intrinsics.checkNotNull(t);
            if (!((AnimationScope) t).isRunning()) {
            }
        } while (callWithFrameNanos(animation2, function13, suspendAnimationKt$animate$42) != coroutine_suspended);
        return coroutine_suspended;
    }

    public static /* synthetic */ Object animate$default(AnimationState animationState, Animation animation, long j, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MIN_VALUE;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Unit animate$lambda$5;
                    animate$lambda$5 = SuspendAnimationKt.animate$lambda$5((AnimationScope) obj2);
                    return animate$lambda$5;
                }
            };
        }
        return animate(animationState, animation, j2, function1, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animate$lambda$5(AnimationScope animationScope) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [T, androidx.compose.animation.core.AnimationScope] */
    public static final Unit animate$lambda$8(Ref.ObjectRef objectRef, Object obj, Animation animation, AnimationVector animationVector, final AnimationState animationState, float f, Function1 function1, long j) {
        ?? animationScope = new AnimationScope(obj, animation.getTypeConverter(), animationVector, j, animation.getTargetValue(), j, true, new Function0() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit animate$lambda$8$lambda$6;
                animate$lambda$8$lambda$6 = SuspendAnimationKt.animate$lambda$8$lambda$6(AnimationState.this);
                return animate$lambda$8$lambda$6;
            }
        });
        doAnimationFrameWithScale(animationScope, j, f, animation, animationState, function1);
        objectRef.element = animationScope;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animate$lambda$8$lambda$6(AnimationState animationState) {
        animationState.setRunning$animation_core(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animate$lambda$9(AnimationState animationState) {
        animationState.setRunning$animation_core(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit animate$lambda$11(Ref.ObjectRef objectRef, float f, Animation animation, AnimationState animationState, Function1 function1, long j) {
        T t = objectRef.element;
        Intrinsics.checkNotNull(t);
        doAnimationFrameWithScale((AnimationScope) t, j, f, animation, animationState, function1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <R, T, V extends AnimationVector> Object callWithFrameNanos(Animation<T, V> animation, final Function1<? super Long, ? extends R> function1, Continuation<? super R> continuation) {
        if (animation.getIsInfinite()) {
            return InfiniteAnimationPolicyKt.withInfiniteAnimationFrameNanos(function1, continuation);
        }
        return MonotonicFrameClockKt.withFrameNanos(new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Object callWithFrameNanos$lambda$12;
                callWithFrameNanos$lambda$12 = SuspendAnimationKt.callWithFrameNanos$lambda$12(Function1.this, ((Long) obj).longValue());
                return callWithFrameNanos$lambda$12;
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object callWithFrameNanos$lambda$12(Function1 function1, long j) {
        return function1.invoke(Long.valueOf(j));
    }

    public static final float getDurationScale(CoroutineContext coroutineContext) {
        MotionDurationScale motionDurationScale = (MotionDurationScale) coroutineContext.get(MotionDurationScale.INSTANCE);
        float scaleFactor = motionDurationScale != null ? motionDurationScale.getScaleFactor() : 1.0f;
        if (!(scaleFactor >= 0.0f)) {
            PreconditionsKt.throwIllegalStateException("negative scale factor");
        }
        return scaleFactor;
    }

    public static final <T, V extends AnimationVector> void updateState(AnimationScope<T, V> animationScope, AnimationState<T, V> animationState) {
        animationState.setValue$animation_core(animationScope.getValue());
        AnimationVectorsKt.copyFrom(animationState.getVelocityVector(), animationScope.getVelocityVector());
        animationState.setFinishedTimeNanos$animation_core(animationScope.getFinishedTimeNanos());
        animationState.setLastFrameTimeNanos$animation_core(animationScope.getLastFrameTimeNanos());
        animationState.setRunning$animation_core(animationScope.isRunning());
    }

    private static final <T, V extends AnimationVector> void doAnimationFrameWithScale(AnimationScope<T, V> animationScope, long j, float f, Animation<T, V> animation, AnimationState<T, V> animationState, Function1<? super AnimationScope<T, V>, Unit> function1) {
        long startTimeNanos;
        if (f == 0.0f) {
            startTimeNanos = animation.getDurationNanos();
        } else {
            startTimeNanos = (long) ((j - animationScope.getStartTimeNanos()) / f);
        }
        doAnimationFrame(animationScope, j, startTimeNanos, animation, animationState, function1);
    }

    private static final <T, V extends AnimationVector> void doAnimationFrame(AnimationScope<T, V> animationScope, long j, long j2, Animation<T, V> animation, AnimationState<T, V> animationState, Function1<? super AnimationScope<T, V>, Unit> function1) {
        animationScope.setLastFrameTimeNanos$animation_core(j);
        animationScope.setValue$animation_core(animation.getValueFromNanos(j2));
        animationScope.setVelocityVector$animation_core(animation.getVelocityVectorFromNanos(j2));
        if (animation.isFinishedFromNanos(j2)) {
            animationScope.setFinishedTimeNanos$animation_core(animationScope.getLastFrameTimeNanos());
            animationScope.setRunning$animation_core(false);
        }
        updateState(animationScope, animationState);
        function1.invoke(animationScope);
    }
}
