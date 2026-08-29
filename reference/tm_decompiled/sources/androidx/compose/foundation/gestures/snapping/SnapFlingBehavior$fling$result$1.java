package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SnapFlingBehavior.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/gestures/snapping/AnimationResult;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$result$1", f = "SnapFlingBehavior.kt", i = {0}, l = {134, 150}, m = "invokeSuspend", n = {"remainingScrollOffset"}, s = {"L$0"})
/* loaded from: classes.dex */
final class SnapFlingBehavior$fling$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AnimationResult<Float, AnimationVector1D>>, Object> {
    final /* synthetic */ float $initialVelocity;
    final /* synthetic */ Function1<Float, Unit> $onRemainingScrollOffsetUpdate;
    final /* synthetic */ ScrollScope $this_fling;
    Object L$0;
    int label;
    final /* synthetic */ SnapFlingBehavior this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SnapFlingBehavior$fling$result$1(SnapFlingBehavior snapFlingBehavior, float f, Function1<? super Float, Unit> function1, ScrollScope scrollScope, Continuation<? super SnapFlingBehavior$fling$result$1> continuation) {
        super(2, continuation);
        this.this$0 = snapFlingBehavior;
        this.$initialVelocity = f;
        this.$onRemainingScrollOffsetUpdate = function1;
        this.$this_fling = scrollScope;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SnapFlingBehavior$fling$result$1(this.this$0, this.$initialVelocity, this.$onRemainingScrollOffsetUpdate, this.$this_fling, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
        return ((SnapFlingBehavior$fling$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0085, code lost:
    
        if (r2 == r1) goto L22;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        DecayAnimationSpec decayAnimationSpec;
        SnapLayoutInfoProvider snapLayoutInfoProvider;
        final Ref.FloatRef floatRef;
        Object tryApproach;
        SnapLayoutInfoProvider snapLayoutInfoProvider2;
        AnimationSpec animationSpec;
        Object animateWithTarget;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            decayAnimationSpec = this.this$0.decayAnimationSpec;
            float calculateTargetValue = DecayAnimationSpecKt.calculateTargetValue(decayAnimationSpec, 0.0f, this.$initialVelocity);
            snapLayoutInfoProvider = this.this$0.snapLayoutInfoProvider;
            float calculateApproachOffset = snapLayoutInfoProvider.calculateApproachOffset(this.$initialVelocity, calculateTargetValue);
            if (Float.isNaN(calculateApproachOffset)) {
                InlineClassHelperKt.throwIllegalStateException("calculateApproachOffset returned NaN. Please use a valid value.");
            }
            floatRef = new Ref.FloatRef();
            floatRef.element = Math.abs(calculateApproachOffset) * Math.signum(this.$initialVelocity);
            this.$onRemainingScrollOffsetUpdate.invoke(Boxing.boxFloat(floatRef.element));
            SnapFlingBehavior snapFlingBehavior = this.this$0;
            ScrollScope scrollScope = this.$this_fling;
            float f = floatRef.element;
            float f2 = this.$initialVelocity;
            final Function1<Float, Unit> function1 = this.$onRemainingScrollOffsetUpdate;
            this.L$0 = floatRef;
            this.label = 1;
            tryApproach = snapFlingBehavior.tryApproach(scrollScope, f, f2, new Function1() { // from class: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$result$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Unit invokeSuspend$lambda$1;
                    invokeSuspend$lambda$1 = SnapFlingBehavior$fling$result$1.invokeSuspend$lambda$1(Ref.FloatRef.this, function1, ((Float) obj2).floatValue());
                    return invokeSuspend$lambda$1;
                }
            }, this);
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            Ref.FloatRef floatRef2 = (Ref.FloatRef) this.L$0;
            ResultKt.throwOnFailure(obj);
            floatRef = floatRef2;
            tryApproach = obj;
        }
        AnimationState animationState = (AnimationState) tryApproach;
        snapLayoutInfoProvider2 = this.this$0.snapLayoutInfoProvider;
        float calculateSnapOffset = snapLayoutInfoProvider2.calculateSnapOffset(((Number) animationState.getVelocity()).floatValue());
        if (Float.isNaN(calculateSnapOffset)) {
            InlineClassHelperKt.throwIllegalStateException("calculateSnapOffset returned NaN. Please use a valid value.");
        }
        floatRef.element = calculateSnapOffset;
        ScrollScope scrollScope2 = this.$this_fling;
        float f3 = floatRef.element;
        float f4 = floatRef.element;
        AnimationState copy$default = AnimationStateKt.copy$default(animationState, 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
        animationSpec = this.this$0.snapAnimationSpec;
        final Function1<Float, Unit> function12 = this.$onRemainingScrollOffsetUpdate;
        this.L$0 = null;
        this.label = 2;
        animateWithTarget = SnapFlingBehaviorKt.animateWithTarget(scrollScope2, f3, f4, copy$default, animationSpec, new Function1() { // from class: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$result$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                Unit invokeSuspend$lambda$4;
                invokeSuspend$lambda$4 = SnapFlingBehavior$fling$result$1.invokeSuspend$lambda$4(Ref.FloatRef.this, function12, ((Float) obj2).floatValue());
                return invokeSuspend$lambda$4;
            }
        }, this);
        return animateWithTarget == coroutine_suspended ? coroutine_suspended : animateWithTarget;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(Ref.FloatRef floatRef, Function1 function1, float f) {
        floatRef.element -= f;
        function1.invoke(Float.valueOf(floatRef.element));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$4(Ref.FloatRef floatRef, Function1 function1, float f) {
        floatRef.element -= f;
        function1.invoke(Float.valueOf(floatRef.element));
        return Unit.INSTANCE;
    }
}
