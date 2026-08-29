package androidx.compose.animation.core;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.Transition.DeferredAnimation;
import androidx.compose.animation.core.Transition.TransitionAnimationState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;

/* compiled from: Transition.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a3\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00152\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0016\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00172\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0018\u001aa\u0010\u001f\u001a\u0018\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0 R\b\u0012\u0004\u0012\u0002H\"0\u0001\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010!*\u00020#*\b\u0012\u0004\u0012\u0002H\"0\u00012\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0%2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010&\u001a\\\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\"0\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u0010(\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u0002H\u00020\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010-\u001aA\u0010.\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\"0\u00012\u0006\u0010/\u001a\u0002H\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\u0006\u00100\u001a\u00020\u0005H\u0001¢\u0006\u0002\u00101\u001a¦\u0001\u00102\u001a\b\u0012\u0004\u0012\u0002H\u000203\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010!*\u00020#*\b\u0012\u0004\u0012\u0002H\"0\u00012\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0%2*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u0002060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u0002H\u00020\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010:\u001am\u0010;\u001a\b\u0012\u0004\u0012\u0002H\u000203\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010!*\u00020#*\b\u0012\u0004\u0012\u0002H\"0\u00012\u0006\u0010<\u001a\u0002H\u00022\u0006\u0010=\u001a\u0002H\u00022\f\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u0002062\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0%2\u0006\u0010\u0004\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010?\u001ai\u0010@\u001a\u00020\f\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010!*\u00020#*\b\u0012\u0004\u0012\u0002H\"0\u00012\u001c\u0010A\u001a\u0018\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0BR\b\u0012\u0004\u0012\u0002H\"0\u00012\u0006\u0010<\u001a\u0002H\u00022\u0006\u0010=\u001a\u0002H\u00022\f\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u000206H\u0003¢\u0006\u0002\u0010C\u001a\u0082\u0001\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001a03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020\u001a0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010F\u001a\b\u0012\u0004\u0012\u00020G03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020G060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020G0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010H\u001a\b\u0012\u0004\u0012\u00020I03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020I060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020I0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010J\u001a\b\u0012\u0004\u0012\u00020K03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020K060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020K0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010L\u001a\b\u0012\u0004\u0012\u00020M03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020M060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020M0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010N\u001a\b\u0012\u0004\u0012\u00020\b03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010O\u001a\b\u0012\u0004\u0012\u00020P03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020P060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020P0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010Q\u001a\b\u0012\u0004\u0012\u00020R03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020R060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020R0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\"\u000e\u0010\u0007\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000\"\u001e\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u001b\u0010\r\u001a\u00020\u000e8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010\"\u000e\u0010\u0019\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"updateTransition", "Landroidx/compose/animation/core/Transition;", "T", "targetState", "label", "", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "AnimationDebugDurationScale", "", "SeekableTransitionStateTotalDurationChanged", "Lkotlin/Function1;", "Landroidx/compose/animation/core/SeekableTransitionState;", "", "SeekableStateObserver", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "getSeekableStateObserver", "()Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "SeekableStateObserver$delegate", "Lkotlin/Lazy;", "rememberTransition", "transitionState", "Landroidx/compose/animation/core/TransitionState;", "(Landroidx/compose/animation/core/TransitionState;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/core/MutableTransitionState;", "(Landroidx/compose/animation/core/MutableTransitionState;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "NoReset", "", "ResetNoSnap", "ResetAnimationSnap", "ResetAnimationSnapCurrent", "ResetAnimationSnapTarget", "createDeferredAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "V", "S", "Landroidx/compose/animation/core/AnimationVector;", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition$DeferredAnimation;", "createChildTransition", "transformToChildState", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "parentState", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/animation/core/Transition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "createChildTransitionInternal", "initialState", "childLabel", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/core/Transition;", "animateValue", "Landroidx/compose/runtime/State;", "transitionSpec", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lkotlin/ExtensionFunctionType;", "targetValueByState", "state", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Lkotlin/jvm/functions/Function3;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "createTransitionAnimation", "initialValue", "targetValue", "animationSpec", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "UpdateInitialAndTargetValues", "transitionAnimation", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/Transition$TransitionAnimationState;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/runtime/Composer;I)V", "animateFloat", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function3;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateDp", "Landroidx/compose/ui/unit/Dp;", "animateOffset", "Landroidx/compose/ui/geometry/Offset;", "animateSize", "Landroidx/compose/ui/geometry/Size;", "animateIntOffset", "Landroidx/compose/ui/unit/IntOffset;", "animateInt", "animateIntSize", "Landroidx/compose/ui/unit/IntSize;", "animateRect", "Landroidx/compose/ui/geometry/Rect;", "animation-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TransitionKt {
    public static final int AnimationDebugDurationScale = 1;
    private static final float NoReset = -1.0f;
    private static final float ResetAnimationSnap = -3.0f;
    private static final float ResetAnimationSnapCurrent = -4.0f;
    private static final float ResetAnimationSnapTarget = -5.0f;
    private static final float ResetNoSnap = -2.0f;
    private static final Function1<SeekableTransitionState<?>, Unit> SeekableTransitionStateTotalDurationChanged = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit SeekableTransitionStateTotalDurationChanged$lambda$4;
            SeekableTransitionStateTotalDurationChanged$lambda$4 = TransitionKt.SeekableTransitionStateTotalDurationChanged$lambda$4((SeekableTransitionState) obj);
            return SeekableTransitionStateTotalDurationChanged$lambda$4;
        }
    };
    private static final Lazy SeekableStateObserver$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            SnapshotStateObserver SeekableStateObserver_delegate$lambda$7;
            SeekableStateObserver_delegate$lambda$7 = TransitionKt.SeekableStateObserver_delegate$lambda$7();
            return SeekableStateObserver_delegate$lambda$7;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UpdateInitialAndTargetValues$lambda$32(Transition transition, Transition.TransitionAnimationState transitionAnimationState, Object obj, Object obj2, FiniteAnimationSpec finiteAnimationSpec, int i, Composer composer, int i2) {
        UpdateInitialAndTargetValues(transition, transitionAnimationState, obj, obj2, finiteAnimationSpec, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final <T> Transition<T> updateTransition(T t, String str, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 2029166765, "C(updateTransition)N(targetState,label)88@3890L51,89@3957L22,90@4013L190,90@3984L219:Transition.kt#pdpnli");
        if ((i2 & 2) != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2029166765, i, -1, "androidx.compose.animation.core.updateTransition (Transition.kt:87)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 114112704, "CC(remember):Transition.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Transition(t, str);
            composer.updateRememberedValue(rememberedValue);
        }
        final Transition<T> transition = (Transition) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        transition.animateTo$animation_core(t, composer, (i & 8) | 48 | (i & 14));
        ComposerKt.sourceInformationMarkerStart(composer, 114116779, "CC(remember):Transition.kt#9igjgp");
        Object rememberedValue2 = composer.rememberedValue();
        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    DisposableEffectResult updateTransition$lambda$3$lambda$2;
                    updateTransition$lambda$3$lambda$2 = TransitionKt.updateTransition$lambda$3$lambda$2(Transition.this, (DisposableEffectScope) obj);
                    return updateTransition$lambda$3$lambda$2;
                }
            };
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.DisposableEffect(transition, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue2, composer, 54);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return transition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SeekableTransitionStateTotalDurationChanged$lambda$4(SeekableTransitionState seekableTransitionState) {
        seekableTransitionState.onTotalDurationChanged$animation_core();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SnapshotStateObserver SeekableStateObserver_delegate$lambda$7() {
        SnapshotStateObserver snapshotStateObserver = new SnapshotStateObserver(new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit SeekableStateObserver_delegate$lambda$7$lambda$5;
                SeekableStateObserver_delegate$lambda$7$lambda$5 = TransitionKt.SeekableStateObserver_delegate$lambda$7$lambda$5((Function0) obj);
                return SeekableStateObserver_delegate$lambda$7$lambda$5;
            }
        });
        snapshotStateObserver.start();
        return snapshotStateObserver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SeekableStateObserver_delegate$lambda$7$lambda$5(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    public static final SnapshotStateObserver getSeekableStateObserver() {
        return (SnapshotStateObserver) SeekableStateObserver$delegate.getValue();
    }

    public static final <T> Transition<T> rememberTransition(TransitionState<T> transitionState, String str, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 1643203617, "C(rememberTransition)N(transitionState,label)806@34785L472,825@35887L190,825@35858L219:Transition.kt#pdpnli");
        if ((i2 & 2) != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1643203617, i, -1, "androidx.compose.animation.core.rememberTransition (Transition.kt:804)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1429283303, "CC(remember):Transition.kt#9igjgp");
        int i3 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i3 > 4 && composer.changed(transitionState)) || (i & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            Snapshot.Companion companion = Snapshot.INSTANCE;
            Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
            try {
                Object transition = new Transition((TransitionState) transitionState, str);
                companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                composer.updateRememberedValue(transition);
                rememberedValue = transition;
            } catch (Throwable th) {
                companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                throw th;
            }
        }
        final Transition<T> transition2 = (Transition) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (transitionState instanceof SeekableTransitionState) {
            composer.startReplaceGroup(-1357588631);
            ComposerKt.sourceInformation(composer, "814@35394L382,814@35320L456");
            SeekableTransitionState seekableTransitionState = (SeekableTransitionState) transitionState;
            Object currentState = seekableTransitionState.getCurrentState();
            Object targetState = seekableTransitionState.getTargetState();
            ComposerKt.sourceInformationMarkerStart(composer, -1429263905, "CC(remember):Transition.kt#9igjgp");
            if ((i3 <= 4 || !composer.changed(transitionState)) && (i & 6) != 4) {
                z = false;
            }
            Object rememberedValue2 = composer.rememberedValue();
            if (z || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = (Function2) new TransitionKt$rememberTransition$1$1(transitionState, null);
                composer.updateRememberedValue(rememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            EffectsKt.LaunchedEffect(currentState, targetState, (Function2) rememberedValue2, composer, 0);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-1357127072);
            ComposerKt.sourceInformation(composer, "823@35809L38");
            transition2.animateTo$animation_core(transitionState.getTargetState(), composer, 0);
            composer.endReplaceGroup();
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1429248321, "CC(remember):Transition.kt#9igjgp");
        boolean changed = composer.changed(transition2);
        Object rememberedValue3 = composer.rememberedValue();
        if (changed || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
            rememberedValue3 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    DisposableEffectResult rememberTransition$lambda$13$lambda$12;
                    rememberTransition$lambda$13$lambda$12 = TransitionKt.rememberTransition$lambda$13$lambda$12(Transition.this, (DisposableEffectScope) obj);
                    return rememberTransition$lambda$13$lambda$12;
                }
            };
            composer.updateRememberedValue(rememberedValue3);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.DisposableEffect(transition2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue3, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return transition2;
    }

    @Deprecated(message = "Use rememberTransition() instead", replaceWith = @ReplaceWith(expression = "rememberTransition(transitionState, label)", imports = {}))
    public static final <T> Transition<T> updateTransition(MutableTransitionState<T> mutableTransitionState, String str, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 882913843, "C(updateTransition)N(transitionState,label)865@37546L32:Transition.kt#pdpnli");
        if ((i2 & 2) != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(882913843, i, -1, "androidx.compose.animation.core.updateTransition (Transition.kt:863)");
        }
        Transition<T> rememberTransition = rememberTransition(mutableTransitionState, str, composer, i & 126, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return rememberTransition;
    }

    public static final <S, T, V extends AnimationVector> Transition<S>.DeferredAnimation<T, V> createDeferredAnimation(final Transition<S> transition, TwoWayConverter<T, V> twoWayConverter, String str, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -1714122528, "C(createDeferredAnimation)N(typeConverter,label)1739@73787L58,1740@73877L43,1740@73850L70:Transition.kt#pdpnli");
        if ((i2 & 2) != 0) {
            str = "DeferredAnimation";
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1714122528, i, -1, "androidx.compose.animation.core.createDeferredAnimation (Transition.kt:1738)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 1995701050, "CC(remember):Transition.kt#9igjgp");
        int i3 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i3 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = transition.new DeferredAnimation(twoWayConverter, str);
            composer.updateRememberedValue(rememberedValue);
        }
        final Transition<S>.DeferredAnimation<T, V> deferredAnimation = (Transition.DeferredAnimation) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, 1995703915, "CC(remember):Transition.kt#9igjgp");
        if ((i3 <= 4 || !composer.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        boolean changedInstance = composer.changedInstance(deferredAnimation) | z;
        Object rememberedValue2 = composer.rememberedValue();
        if (changedInstance || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    DisposableEffectResult createDeferredAnimation$lambda$17$lambda$16;
                    createDeferredAnimation$lambda$17$lambda$16 = TransitionKt.createDeferredAnimation$lambda$17$lambda$16(Transition.this, deferredAnimation, (DisposableEffectScope) obj);
                    return createDeferredAnimation$lambda$17$lambda$16;
                }
            };
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.DisposableEffect(deferredAnimation, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue2, composer, 0);
        if (transition.isSeeking()) {
            deferredAnimation.setupSeeking$animation_core();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return deferredAnimation;
    }

    public static final <S, T> Transition<T> createChildTransition(Transition<S> transition, String str, Function3<? super S, ? super Composer, ? super Integer, ? extends T> function3, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -539313577, "CC(createChildTransition)N(label,transformToChildState)1768@75281L36,1769@75341L74,1770@75438L39,1771@75489L63:Transition.kt#pdpnli");
        boolean z = true;
        if ((i2 & 1) != 0) {
            str = "ChildTransition";
        }
        String str2 = str;
        ComposerKt.sourceInformationMarkerStart(composer, 1410703355, "CC(remember):Transition.kt#9igjgp");
        int i3 = i & 14;
        if (((i3 ^ 6) <= 4 || !composer.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        Object rememberedValue = composer.rememberedValue();
        if (z || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = transition.getCurrentState();
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (transition.isSeeking()) {
            rememberedValue = transition.getCurrentState();
        }
        int i4 = (i >> 3) & 112;
        Transition<T> createChildTransitionInternal = createChildTransitionInternal(transition, function3.invoke(rememberedValue, composer, Integer.valueOf(i4)), function3.invoke(transition.getTargetState(), composer, Integer.valueOf(i4)), str2, composer, i3 | ((i << 6) & 7168));
        ComposerKt.sourceInformationMarkerEnd(composer);
        return createChildTransitionInternal;
    }

    public static final <S, T> Transition<T> createChildTransitionInternal(final Transition<S> transition, T t, T t2, String str, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -198307638, "C(createChildTransitionInternal)N(initialState,targetState,childLabel)1782@75760L124,1786@75919L92,1786@75890L121:Transition.kt#pdpnli");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-198307638, i, -1, "androidx.compose.animation.core.createChildTransitionInternal (Transition.kt:1780)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1595072442, "CC(remember):Transition.kt#9igjgp");
        int i2 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i2 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Transition(new MutableTransitionState(t), transition, transition.getLabel() + " > " + str);
            composer.updateRememberedValue(rememberedValue);
        }
        final Transition<T> transition2 = (Transition) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -1595067386, "CC(remember):Transition.kt#9igjgp");
        if ((i2 <= 4 || !composer.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        boolean changed = composer.changed(transition2) | z;
        Object rememberedValue2 = composer.rememberedValue();
        if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    DisposableEffectResult createChildTransitionInternal$lambda$22$lambda$21;
                    createChildTransitionInternal$lambda$22$lambda$21 = TransitionKt.createChildTransitionInternal$lambda$22$lambda$21(Transition.this, transition2, (DisposableEffectScope) obj);
                    return createChildTransitionInternal$lambda$22$lambda$21;
                }
            };
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.DisposableEffect(transition2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue2, composer, 0);
        if (transition.isSeeking()) {
            transition2.seek(t, t2, transition.getLastSeekedTimeNanos());
        } else {
            transition2.updateTarget$animation_core(t2);
            transition2.setSeeking$animation_core(false);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return transition2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult createChildTransitionInternal$lambda$22$lambda$21(final Transition transition, final Transition transition2, DisposableEffectScope disposableEffectScope) {
        transition.addTransition$animation_core(transition2);
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createChildTransitionInternal$lambda$22$lambda$21$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Transition.this.removeTransition$animation_core(transition2);
            }
        };
    }

    public static final <S, T, V extends AnimationVector> State<T> animateValue(Transition<S> transition, TwoWayConverter<T, V> twoWayConverter, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<T>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, ? extends T> function32, Composer composer, int i, int i2) {
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1848@78638L32,1855@79111L49,1855@79092L75,1856@79207L45,1856@79192L67,1858@79272L89:Transition.kt#pdpnli");
        TransitionKt$animateValue$1 transitionKt$animateValue$1 = (i2 & 2) != 0 ? new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<T>>() { // from class: androidx.compose.animation.core.TransitionKt$animateValue$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Composer composer2, Integer num) {
                return invoke((Transition.Segment) obj, composer2, num.intValue());
            }

            public final SpringSpec<T> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                composer2.startReplaceGroup(-2137771706);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2137771706, i3, -1, "androidx.compose.animation.core.animateValue.<anonymous> (Transition.kt:1834)");
                }
                SpringSpec<T> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return spring$default;
            }
        } : function3;
        String str2 = (i2 & 4) != 0 ? "ValueAnimation" : str;
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666853325);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666599280);
            ComposerKt.sourceInformation(composer, "1844@78495L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054611820, "CC(remember):Transition.kt#9igjgp");
            boolean z = (((i & 14) ^ 6) > 4 && composer.changed(transition)) || (i & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        int i3 = (i >> 9) & 112;
        T invoke = function32.invoke(currentState, composer, Integer.valueOf(i3));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592126, "CC(remember):Transition.kt#9igjgp");
        int i4 = i & 14;
        int i5 = i4 ^ 6;
        boolean z2 = (i5 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        T invoke2 = function32.invoke((Object) ((State) rememberedValue).getValue(), composer, Integer.valueOf(i3));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589058, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (i5 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object rememberedValue2 = composer.rememberedValue();
        if (z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<T> createTransitionAnimation = createTransitionAnimation(transition, invoke, invoke2, transitionKt$animateValue$1.invoke((Object) ((State) rememberedValue2).getValue(), composer, Integer.valueOf((i >> 3) & 112)), twoWayConverter, str2, composer, (57344 & (i << 9)) | i4 | (458752 & (i << 6)));
        ComposerKt.sourceInformationMarkerEnd(composer);
        return createTransitionAnimation;
    }

    public static final <S, T, V extends AnimationVector> State<T> createTransitionAnimation(final Transition<S> transition, T t, T t2, FiniteAnimationSpec<T> finiteAnimationSpec, TwoWayConverter<T, V> twoWayConverter, String str, Composer composer, int i) {
        Function1<Object, Unit> function1;
        Snapshot snapshot;
        ComposerKt.sourceInformationMarkerStart(composer, -304821198, "C(createTransitionAnimation)N(initialValue,targetValue,animationSpec,typeConverter,label)1871@79670L978,1889@80653L91,1891@80788L108,1891@80750L146:Transition.kt#pdpnli");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-304821198, i, -1, "androidx.compose.animation.core.createTransitionAnimation (Transition.kt:1869)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -875384412, "CC(remember):Transition.kt#9igjgp");
        int i2 = i & 14;
        int i3 = i2 ^ 6;
        boolean z = (i3 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z || rememberedValue == Composer.INSTANCE.getEmpty()) {
            Snapshot.Companion companion = Snapshot.INSTANCE;
            Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
            try {
                function1 = readObserver;
                snapshot = makeCurrentNonObservable;
                try {
                    Object transitionAnimationState = transition.new TransitionAnimationState(t, AnimationStateKt.createZeroVectorFrom(twoWayConverter, t2), twoWayConverter, str);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshot, function1);
                    composer.updateRememberedValue(transitionAnimationState);
                    rememberedValue = transitionAnimationState;
                } catch (Throwable th) {
                    th = th;
                    companion.restoreNonObservable(currentThreadSnapshot, snapshot, function1);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                function1 = readObserver;
                snapshot = makeCurrentNonObservable;
            }
        }
        final Transition.TransitionAnimationState transitionAnimationState2 = (Transition.TransitionAnimationState) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        int i4 = (i >> 3) & 8;
        int i5 = i << 3;
        UpdateInitialAndTargetValues(transition, transitionAnimationState2, t, t2, finiteAnimationSpec, composer, (i4 << 9) | (i4 << 6) | i2 | (i5 & 896) | (i5 & 7168) | (57344 & i5));
        ComposerKt.sourceInformationMarkerStart(composer, -875349506, "CC(remember):Transition.kt#9igjgp");
        boolean changed = composer.changed(transitionAnimationState2) | ((i3 > 4 && composer.changed(transition)) || (i & 6) == 4);
        Object rememberedValue2 = composer.rememberedValue();
        if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    DisposableEffectResult createTransitionAnimation$lambda$31$lambda$30;
                    createTransitionAnimation$lambda$31$lambda$30 = TransitionKt.createTransitionAnimation$lambda$31$lambda$30(Transition.this, transitionAnimationState2, (DisposableEffectScope) obj);
                    return createTransitionAnimation$lambda$31$lambda$30;
                }
            };
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.DisposableEffect(transitionAnimationState2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return transitionAnimationState2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult createTransitionAnimation$lambda$31$lambda$30(final Transition transition, final Transition.TransitionAnimationState transitionAnimationState, DisposableEffectScope disposableEffectScope) {
        transition.addAnimation$animation_core(transitionAnimationState);
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createTransitionAnimation$lambda$31$lambda$30$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Transition.this.removeAnimation$animation_core(transitionAnimationState);
            }
        };
    }

    private static final <S, T, V extends AnimationVector> void UpdateInitialAndTargetValues(final Transition<S> transition, final Transition<S>.TransitionAnimationState<T, V> transitionAnimationState, final T t, final T t2, final FiniteAnimationSpec<T> finiteAnimationSpec, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(867041821);
        ComposerKt.sourceInformation(startRestartGroup, "C(UpdateInitialAndTargetValues)N(transitionAnimation,initialValue,targetValue,animationSpec):Transition.kt#pdpnli");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(transition) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(transitionAnimationState) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= (i & 512) == 0 ? startRestartGroup.changed(t) : startRestartGroup.changedInstance(t) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= (i & 4096) == 0 ? startRestartGroup.changed(t2) : startRestartGroup.changedInstance(t2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= (32768 & i) == 0 ? startRestartGroup.changed(finiteAnimationSpec) : startRestartGroup.changedInstance(finiteAnimationSpec) ? 16384 : 8192;
        }
        if (!startRestartGroup.shouldExecute((i2 & 9363) != 9362, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(867041821, i2, -1, "androidx.compose.animation.core.UpdateInitialAndTargetValues (Transition.kt:1907)");
            }
            if (transition.isSeeking()) {
                transitionAnimationState.updateInitialAndTargetValue$animation_core(t, t2, finiteAnimationSpec);
            } else {
                transitionAnimationState.updateTargetValue$animation_core(t2, finiteAnimationSpec);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.animation.core.TransitionKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit UpdateInitialAndTargetValues$lambda$32;
                    UpdateInitialAndTargetValues$lambda$32 = TransitionKt.UpdateInitialAndTargetValues$lambda$32(Transition.this, transitionAnimationState, t, t2, finiteAnimationSpec, i, (Composer) obj, ((Integer) obj2).intValue());
                    return UpdateInitialAndTargetValues$lambda$32;
                }
            });
        }
    }

    public static final <S> State<Float> animateFloat(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Float>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Float> function32, Composer composer, int i, int i2) {
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, 844118987, "CC(animateFloat)N(transitionSpec,label,targetValueByState)1951@83597L78:Transition.kt#pdpnli");
        TransitionKt$animateFloat$1 transitionKt$animateFloat$1 = (i2 & 1) != 0 ? new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Float>>() { // from class: androidx.compose.animation.core.TransitionKt$animateFloat$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ SpringSpec<Float> invoke(Object obj, Composer composer2, Integer num) {
                return invoke((Transition.Segment) obj, composer2, num.intValue());
            }

            public final SpringSpec<Float> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                composer2.startReplaceGroup(-985243360);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-985243360, i3, -1, "androidx.compose.animation.core.animateFloat.<anonymous> (Transition.kt:1947)");
                }
                SpringSpec<Float> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return spring$default;
            }
        } : function3;
        String str2 = (i2 & 2) != 0 ? "FloatAnimation" : str;
        TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        int i3 = i << 3;
        int i4 = (i & 14) | (i3 & 896) | (i3 & 7168) | (i3 & 57344);
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1848@78638L32,1855@79111L49,1855@79092L75,1856@79207L45,1856@79192L67,1858@79272L89:Transition.kt#pdpnli");
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666853325);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666599280);
            ComposerKt.sourceInformation(composer, "1844@78495L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054611820, "CC(remember):Transition.kt#9igjgp");
            boolean z = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        int i5 = (i4 >> 9) & 112;
        Float invoke = function32.invoke(currentState, composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592126, "CC(remember):Transition.kt#9igjgp");
        int i6 = i4 & 14;
        int i7 = i6 ^ 6;
        boolean z2 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Float invoke2 = function32.invoke((Object) ((State) rememberedValue).getValue(), composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589058, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue2 = composer.rememberedValue();
        if (z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<Float> createTransitionAnimation = createTransitionAnimation(transition, invoke, invoke2, transitionKt$animateFloat$1.invoke((Object) ((State) rememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str2, composer, i6 | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return createTransitionAnimation;
    }

    public static final <S> State<Dp> animateDp(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Dp>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Dp> function32, Composer composer, int i, int i2) {
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, -89793049, "CC(animateDp)N(transitionSpec,label,targetValueByState)1981@85315L75:Transition.kt#pdpnli");
        TransitionKt$animateDp$1 transitionKt$animateDp$1 = (i2 & 1) != 0 ? new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Dp>>() { // from class: androidx.compose.animation.core.TransitionKt$animateDp$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ SpringSpec<Dp> invoke(Object obj, Composer composer2, Integer num) {
                return invoke((Transition.Segment) obj, composer2, num.intValue());
            }

            public final SpringSpec<Dp> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                composer2.startReplaceGroup(-1953972046);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1953972046, i3, -1, "androidx.compose.animation.core.animateDp.<anonymous> (Transition.kt:1977)");
                }
                SpringSpec<Dp> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Dp.m7397boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Dp.INSTANCE)), 3, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return spring$default;
            }
        } : function3;
        String str2 = (i2 & 2) != 0 ? "DpAnimation" : str;
        TwoWayConverter<Dp, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(Dp.INSTANCE);
        int i3 = i << 3;
        int i4 = (i & 14) | (i3 & 896) | (i3 & 7168) | (i3 & 57344);
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1848@78638L32,1855@79111L49,1855@79092L75,1856@79207L45,1856@79192L67,1858@79272L89:Transition.kt#pdpnli");
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666853325);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666599280);
            ComposerKt.sourceInformation(composer, "1844@78495L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054611820, "CC(remember):Transition.kt#9igjgp");
            boolean z = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        int i5 = (i4 >> 9) & 112;
        Dp invoke = function32.invoke(currentState, composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592126, "CC(remember):Transition.kt#9igjgp");
        int i6 = i4 & 14;
        int i7 = i6 ^ 6;
        boolean z2 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Dp invoke2 = function32.invoke((Object) ((State) rememberedValue).getValue(), composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589058, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue2 = composer.rememberedValue();
        if (z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<Dp> createTransitionAnimation = createTransitionAnimation(transition, invoke, invoke2, transitionKt$animateDp$1.invoke((Object) ((State) rememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str2, composer, i6 | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return createTransitionAnimation;
    }

    public static final <S> State<Offset> animateOffset(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Offset>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Offset> function32, Composer composer, int i, int i2) {
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, -787164050, "CC(animateOffset)N(transitionSpec,label,targetValueByState)2011@87059L79:Transition.kt#pdpnli");
        TransitionKt$animateOffset$1 transitionKt$animateOffset$1 = (i2 & 1) != 0 ? new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Offset>>() { // from class: androidx.compose.animation.core.TransitionKt$animateOffset$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ SpringSpec<Offset> invoke(Object obj, Composer composer2, Integer num) {
                return invoke((Transition.Segment) obj, composer2, num.intValue());
            }

            public final SpringSpec<Offset> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                composer2.startReplaceGroup(-1662821959);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1662821959, i3, -1, "androidx.compose.animation.core.animateOffset.<anonymous> (Transition.kt:2007)");
                }
                SpringSpec<Offset> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Offset.m4332boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Offset.INSTANCE)), 3, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return spring$default;
            }
        } : function3;
        String str2 = (i2 & 2) != 0 ? "OffsetAnimation" : str;
        TwoWayConverter<Offset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(Offset.INSTANCE);
        int i3 = i << 3;
        int i4 = (i & 14) | (i3 & 896) | (i3 & 7168) | (i3 & 57344);
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1848@78638L32,1855@79111L49,1855@79092L75,1856@79207L45,1856@79192L67,1858@79272L89:Transition.kt#pdpnli");
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666853325);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666599280);
            ComposerKt.sourceInformation(composer, "1844@78495L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054611820, "CC(remember):Transition.kt#9igjgp");
            boolean z = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        int i5 = (i4 >> 9) & 112;
        Offset invoke = function32.invoke(currentState, composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592126, "CC(remember):Transition.kt#9igjgp");
        int i6 = i4 & 14;
        int i7 = i6 ^ 6;
        boolean z2 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Offset invoke2 = function32.invoke((Object) ((State) rememberedValue).getValue(), composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589058, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue2 = composer.rememberedValue();
        if (z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<Offset> createTransitionAnimation = createTransitionAnimation(transition, invoke, invoke2, transitionKt$animateOffset$1.invoke((Object) ((State) rememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str2, composer, i6 | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return createTransitionAnimation;
    }

    public static final <S> State<Size> animateSize(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Size>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Size> function32, Composer composer, int i, int i2) {
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, 967983196, "CC(animateSize)N(transitionSpec,label,targetValueByState)2041@88792L77:Transition.kt#pdpnli");
        TransitionKt$animateSize$1 transitionKt$animateSize$1 = (i2 & 1) != 0 ? new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Size>>() { // from class: androidx.compose.animation.core.TransitionKt$animateSize$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ SpringSpec<Size> invoke(Object obj, Composer composer2, Integer num) {
                return invoke((Transition.Segment) obj, composer2, num.intValue());
            }

            public final SpringSpec<Size> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                composer2.startReplaceGroup(493329511);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(493329511, i3, -1, "androidx.compose.animation.core.animateSize.<anonymous> (Transition.kt:2037)");
                }
                SpringSpec<Size> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Size.m4400boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Size.INSTANCE)), 3, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return spring$default;
            }
        } : function3;
        String str2 = (i2 & 2) != 0 ? "SizeAnimation" : str;
        TwoWayConverter<Size, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(Size.INSTANCE);
        int i3 = i << 3;
        int i4 = (i & 14) | (i3 & 896) | (i3 & 7168) | (i3 & 57344);
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1848@78638L32,1855@79111L49,1855@79092L75,1856@79207L45,1856@79192L67,1858@79272L89:Transition.kt#pdpnli");
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666853325);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666599280);
            ComposerKt.sourceInformation(composer, "1844@78495L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054611820, "CC(remember):Transition.kt#9igjgp");
            boolean z = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        int i5 = (i4 >> 9) & 112;
        Size invoke = function32.invoke(currentState, composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592126, "CC(remember):Transition.kt#9igjgp");
        int i6 = i4 & 14;
        int i7 = i6 ^ 6;
        boolean z2 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Size invoke2 = function32.invoke((Object) ((State) rememberedValue).getValue(), composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589058, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue2 = composer.rememberedValue();
        if (z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<Size> createTransitionAnimation = createTransitionAnimation(transition, invoke, invoke2, transitionKt$animateSize$1.invoke((Object) ((State) rememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str2, composer, i6 | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return createTransitionAnimation;
    }

    public static final <S> State<IntOffset> animateIntOffset(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntOffset>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, IntOffset> function32, Composer composer, int i, int i2) {
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, -1335046959, "CC(animateIntOffset)N(transitionSpec,label,targetValueByState)2075@90580L82:Transition.kt#pdpnli");
        TransitionKt$animateIntOffset$1 transitionKt$animateIntOffset$1 = (i2 & 1) != 0 ? new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<IntOffset>>() { // from class: androidx.compose.animation.core.TransitionKt$animateIntOffset$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ SpringSpec<IntOffset> invoke(Object obj, Composer composer2, Integer num) {
                return invoke((Transition.Segment) obj, composer2, num.intValue());
            }

            public final SpringSpec<IntOffset> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                composer2.startReplaceGroup(-428458074);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-428458074, i3, -1, "androidx.compose.animation.core.animateIntOffset.<anonymous> (Transition.kt:2070)");
                }
                long j = 1;
                SpringSpec<IntOffset> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m7522boximpl(IntOffset.m7525constructorimpl((j & 4294967295L) | (j << 32))), 3, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return spring$default;
            }
        } : function3;
        String str2 = (i2 & 2) != 0 ? "IntOffsetAnimation" : str;
        TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE);
        int i3 = i << 3;
        int i4 = (i & 14) | (i3 & 896) | (i3 & 7168) | (i3 & 57344);
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1848@78638L32,1855@79111L49,1855@79092L75,1856@79207L45,1856@79192L67,1858@79272L89:Transition.kt#pdpnli");
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666853325);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666599280);
            ComposerKt.sourceInformation(composer, "1844@78495L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054611820, "CC(remember):Transition.kt#9igjgp");
            boolean z = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        int i5 = (i4 >> 9) & 112;
        IntOffset invoke = function32.invoke(currentState, composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592126, "CC(remember):Transition.kt#9igjgp");
        int i6 = i4 & 14;
        int i7 = i6 ^ 6;
        boolean z2 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        IntOffset invoke2 = function32.invoke((Object) ((State) rememberedValue).getValue(), composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589058, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue2 = composer.rememberedValue();
        if (z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<IntOffset> createTransitionAnimation = createTransitionAnimation(transition, invoke, invoke2, transitionKt$animateIntOffset$1.invoke((Object) ((State) rememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str2, composer, i6 | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return createTransitionAnimation;
    }

    public static final <S> State<Integer> animateInt(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Integer>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Integer> function32, Composer composer, int i, int i2) {
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, -230569122, "CC(animateInt)N(transitionSpec,label,targetValueByState)2105@92287L76:Transition.kt#pdpnli");
        TransitionKt$animateInt$1 transitionKt$animateInt$1 = (i2 & 1) != 0 ? new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Integer>>() { // from class: androidx.compose.animation.core.TransitionKt$animateInt$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ SpringSpec<Integer> invoke(Object obj, Composer composer2, Integer num) {
                return invoke((Transition.Segment) obj, composer2, num.intValue());
            }

            public final SpringSpec<Integer> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                composer2.startReplaceGroup(2109424115);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2109424115, i3, -1, "androidx.compose.animation.core.animateInt.<anonymous> (Transition.kt:2101)");
                }
                SpringSpec<Integer> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, 1, 3, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return spring$default;
            }
        } : function3;
        String str2 = (i2 & 2) != 0 ? "IntAnimation" : str;
        TwoWayConverter<Integer, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE);
        int i3 = i << 3;
        int i4 = (i & 14) | (i3 & 896) | (i3 & 7168) | (i3 & 57344);
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1848@78638L32,1855@79111L49,1855@79092L75,1856@79207L45,1856@79192L67,1858@79272L89:Transition.kt#pdpnli");
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666853325);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666599280);
            ComposerKt.sourceInformation(composer, "1844@78495L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054611820, "CC(remember):Transition.kt#9igjgp");
            boolean z = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        int i5 = (i4 >> 9) & 112;
        Integer invoke = function32.invoke(currentState, composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592126, "CC(remember):Transition.kt#9igjgp");
        int i6 = i4 & 14;
        int i7 = i6 ^ 6;
        boolean z2 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Integer invoke2 = function32.invoke((Object) ((State) rememberedValue).getValue(), composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589058, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue2 = composer.rememberedValue();
        if (z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<Integer> createTransitionAnimation = createTransitionAnimation(transition, invoke, invoke2, transitionKt$animateInt$1.invoke((Object) ((State) rememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str2, composer, i6 | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return createTransitionAnimation;
    }

    public static final <S> State<IntSize> animateIntSize(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntSize>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, IntSize> function32, Composer composer, int i, int i2) {
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, -2107443841, "CC(animateIntSize)N(transitionSpec,label,targetValueByState)2136@94040L80:Transition.kt#pdpnli");
        TransitionKt$animateIntSize$1 transitionKt$animateIntSize$1 = (i2 & 1) != 0 ? new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<IntSize>>() { // from class: androidx.compose.animation.core.TransitionKt$animateIntSize$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ SpringSpec<IntSize> invoke(Object obj, Composer composer2, Integer num) {
                return invoke((Transition.Segment) obj, composer2, num.intValue());
            }

            public final SpringSpec<IntSize> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                composer2.startReplaceGroup(811932052);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(811932052, i3, -1, "androidx.compose.animation.core.animateIntSize.<anonymous> (Transition.kt:2132)");
                }
                long j = 1;
                SpringSpec<IntSize> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m7566boximpl(IntSize.m7569constructorimpl((j & 4294967295L) | (j << 32))), 3, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return spring$default;
            }
        } : function3;
        String str2 = (i2 & 2) != 0 ? "IntSizeAnimation" : str;
        TwoWayConverter<IntSize, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntSize.INSTANCE);
        int i3 = i << 3;
        int i4 = (i & 14) | (i3 & 896) | (i3 & 7168) | (i3 & 57344);
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1848@78638L32,1855@79111L49,1855@79092L75,1856@79207L45,1856@79192L67,1858@79272L89:Transition.kt#pdpnli");
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666853325);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666599280);
            ComposerKt.sourceInformation(composer, "1844@78495L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054611820, "CC(remember):Transition.kt#9igjgp");
            boolean z = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        int i5 = (i4 >> 9) & 112;
        IntSize invoke = function32.invoke(currentState, composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592126, "CC(remember):Transition.kt#9igjgp");
        int i6 = i4 & 14;
        int i7 = i6 ^ 6;
        boolean z2 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        IntSize invoke2 = function32.invoke((Object) ((State) rememberedValue).getValue(), composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589058, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue2 = composer.rememberedValue();
        if (z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<IntSize> createTransitionAnimation = createTransitionAnimation(transition, invoke, invoke2, transitionKt$animateIntSize$1.invoke((Object) ((State) rememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str2, composer, i6 | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return createTransitionAnimation;
    }

    public static final <S> State<Rect> animateRect(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Rect>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Rect> function32, Composer composer, int i, int i2) {
        Object currentState;
        ComposerKt.sourceInformationMarkerStart(composer, 1420827071, "CC(animateRect)N(transitionSpec,label,targetValueByState)2166@95774L77:Transition.kt#pdpnli");
        TransitionKt$animateRect$1 transitionKt$animateRect$1 = (i2 & 1) != 0 ? new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Rect>>() { // from class: androidx.compose.animation.core.TransitionKt$animateRect$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ SpringSpec<Rect> invoke(Object obj, Composer composer2, Integer num) {
                return invoke((Transition.Segment) obj, composer2, num.intValue());
            }

            public final SpringSpec<Rect> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                composer2.startReplaceGroup(946173386);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(946173386, i3, -1, "androidx.compose.animation.core.animateRect.<anonymous> (Transition.kt:2162)");
                }
                SpringSpec<Rect> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.INSTANCE), 3, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return spring$default;
            }
        } : function3;
        String str2 = (i2 & 2) != 0 ? "RectAnimation" : str;
        TwoWayConverter<Rect, AnimationVector4D> vectorConverter = VectorConvertersKt.getVectorConverter(Rect.INSTANCE);
        int i3 = i << 3;
        int i4 = (i & 14) | (i3 & 896) | (i3 & 7168) | (i3 & 57344);
        ComposerKt.sourceInformationMarkerStart(composer, 1143035377, "CC(animateValue)N(typeConverter,transitionSpec,label,targetValueByState)1848@78638L32,1855@79111L49,1855@79092L75,1856@79207L45,1856@79192L67,1858@79272L89:Transition.kt#pdpnli");
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666853325);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666599280);
            ComposerKt.sourceInformation(composer, "1844@78495L67");
            ComposerKt.sourceInformationMarkerStart(composer, -1054611820, "CC(remember):Transition.kt#9igjgp");
            boolean z = (((i4 & 14) ^ 6) > 4 && composer.changed(transition)) || (i4 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.INSTANCE.getEmpty()) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endReplaceGroup();
        }
        int i5 = (i4 >> 9) & 112;
        Rect invoke = function32.invoke(currentState, composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054592126, "CC(remember):Transition.kt#9igjgp");
        int i6 = i4 & 14;
        int i7 = i6 ^ 6;
        boolean z2 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue = composer.rememberedValue();
        if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Rect invoke2 = function32.invoke((Object) ((State) rememberedValue).getValue(), composer, Integer.valueOf(i5));
        ComposerKt.sourceInformationMarkerStart(composer, -1054589058, "CC(remember):Transition.kt#9igjgp");
        boolean z3 = (i7 > 4 && composer.changed(transition)) || (i4 & 6) == 4;
        Object rememberedValue2 = composer.rememberedValue();
        if (z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        State<Rect> createTransitionAnimation = createTransitionAnimation(transition, invoke, invoke2, transitionKt$animateRect$1.invoke((Object) ((State) rememberedValue2).getValue(), composer, Integer.valueOf((i4 >> 3) & 112)), vectorConverter, str2, composer, i6 | ((i4 << 6) & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return createTransitionAnimation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult updateTransition$lambda$3$lambda$2(final Transition transition, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$lambda$3$lambda$2$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Transition.this.onDisposed$animation_core();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult rememberTransition$lambda$13$lambda$12(final Transition transition, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$rememberTransition$lambda$13$lambda$12$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Transition.this.onDisposed$animation_core();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult createDeferredAnimation$lambda$17$lambda$16(final Transition transition, final Transition.DeferredAnimation deferredAnimation, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createDeferredAnimation$lambda$17$lambda$16$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Transition.this.removeAnimation$animation_core(deferredAnimation);
            }
        };
    }
}
