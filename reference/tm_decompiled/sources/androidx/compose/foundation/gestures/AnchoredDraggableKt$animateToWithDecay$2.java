package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Ref;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: AnchoredDraggable.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u0002H\n"}, d2 = {"<anonymous>", "", "T", "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "anchors", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "latestTarget"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$2", f = "AnchoredDraggable.kt", i = {}, l = {1389, 1407, 1431}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class AnchoredDraggableKt$animateToWithDecay$2<T> extends SuspendLambda implements Function4<AnchoredDragScope, DraggableAnchors<T>, T, Continuation<? super Unit>, Object> {
    final /* synthetic */ DecayAnimationSpec<Float> $decayAnimationSpec;
    final /* synthetic */ Ref.FloatRef $remainingVelocity;
    final /* synthetic */ AnimationSpec<Float> $snapAnimationSpec;
    final /* synthetic */ AnchoredDraggableState<T> $this_animateToWithDecay;
    final /* synthetic */ float $velocity;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AnchoredDraggableKt$animateToWithDecay$2(AnchoredDraggableState<T> anchoredDraggableState, float f, AnimationSpec<Float> animationSpec, Ref.FloatRef floatRef, DecayAnimationSpec<Float> decayAnimationSpec, Continuation<? super AnchoredDraggableKt$animateToWithDecay$2> continuation) {
        super(4, continuation);
        this.$this_animateToWithDecay = anchoredDraggableState;
        this.$velocity = f;
        this.$snapAnimationSpec = animationSpec;
        this.$remainingVelocity = floatRef;
        this.$decayAnimationSpec = decayAnimationSpec;
    }

    public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, T t, Continuation<? super Unit> continuation) {
        AnchoredDraggableKt$animateToWithDecay$2 anchoredDraggableKt$animateToWithDecay$2 = new AnchoredDraggableKt$animateToWithDecay$2(this.$this_animateToWithDecay, this.$velocity, this.$snapAnimationSpec, this.$remainingVelocity, this.$decayAnimationSpec, continuation);
        anchoredDraggableKt$animateToWithDecay$2.L$0 = anchoredDragScope;
        anchoredDraggableKt$animateToWithDecay$2.L$1 = draggableAnchors;
        anchoredDraggableKt$animateToWithDecay$2.L$2 = t;
        return anchoredDraggableKt$animateToWithDecay$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function4
    public /* bridge */ /* synthetic */ Object invoke(AnchoredDragScope anchoredDragScope, Object obj, Object obj2, Continuation<? super Unit> continuation) {
        return invoke(anchoredDragScope, (DraggableAnchors<DraggableAnchors<T>>) obj, (DraggableAnchors<T>) obj2, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00db, code lost:
    
        if (r2 == r1) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c6, code lost:
    
        if (androidx.compose.animation.core.SuspendAnimationKt.animateDecay$default(r22, r3, false, new androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$2$$ExternalSyntheticLambda0(r2, r7, r9, r5), r29, 2, null) == r1) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00f7, code lost:
    
        if (r2 == r1) goto L42;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object animateTo;
        Object animateTo2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final AnchoredDragScope anchoredDragScope = (AnchoredDragScope) this.L$0;
            DraggableAnchors draggableAnchors = (DraggableAnchors) this.L$1;
            Object obj2 = this.L$2;
            final float positionOf = draggableAnchors.positionOf(obj2);
            if (!Float.isNaN(positionOf)) {
                final Ref.FloatRef floatRef = new Ref.FloatRef();
                floatRef.element = Float.isNaN(this.$this_animateToWithDecay.getOffset()) ? 0.0f : this.$this_animateToWithDecay.getOffset();
                if (floatRef.element != positionOf) {
                    if (this.$velocity * (positionOf - floatRef.element) < 0.0f || this.$velocity == 0.0f) {
                        this.L$0 = null;
                        this.L$1 = null;
                        this.label = 1;
                        animateTo = AnchoredDraggableKt.animateTo(this.$this_animateToWithDecay, this.$velocity, anchoredDragScope, draggableAnchors, obj2, this.$snapAnimationSpec, this);
                    } else {
                        float calculateTargetValue = DecayAnimationSpecKt.calculateTargetValue(this.$decayAnimationSpec, floatRef.element, this.$velocity);
                        float f = this.$velocity;
                        if (f <= 0.0f ? calculateTargetValue > positionOf : calculateTargetValue < positionOf) {
                            this.L$0 = null;
                            this.L$1 = null;
                            this.label = 3;
                            animateTo2 = AnchoredDraggableKt.animateTo(this.$this_animateToWithDecay, f, anchoredDragScope, draggableAnchors, obj2, this.$snapAnimationSpec, this);
                        } else {
                            AnimationState AnimationState$default = AnimationStateKt.AnimationState$default(floatRef.element, this.$velocity, 0L, 0L, false, 28, null);
                            DecayAnimationSpec<Float> decayAnimationSpec = this.$decayAnimationSpec;
                            final Ref.FloatRef floatRef2 = this.$remainingVelocity;
                            this.L$0 = null;
                            this.L$1 = null;
                            this.label = 2;
                        }
                    }
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
            this.$remainingVelocity.element = 0.0f;
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
        } else {
            if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.$remainingVelocity.element = 0.0f;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$2(float f, Ref.FloatRef floatRef, AnchoredDragScope anchoredDragScope, Ref.FloatRef floatRef2, AnimationScope animationScope) {
        float coerceToTarget;
        if ((((Number) animationScope.getValue()).floatValue() < f && floatRef.element > f) || (((Number) animationScope.getValue()).floatValue() > f && floatRef.element < f)) {
            coerceToTarget = AnchoredDraggableKt.coerceToTarget(((Number) animationScope.getValue()).floatValue(), f);
            anchoredDragScope.dragTo(coerceToTarget, ((Number) animationScope.getVelocity()).floatValue());
            floatRef2.element = Float.isNaN(((Number) animationScope.getVelocity()).floatValue()) ? 0.0f : ((Number) animationScope.getVelocity()).floatValue();
            floatRef.element = coerceToTarget;
            animationScope.cancelAnimation();
        } else {
            anchoredDragScope.dragTo(((Number) animationScope.getValue()).floatValue(), ((Number) animationScope.getVelocity()).floatValue());
            floatRef2.element = ((Number) animationScope.getVelocity()).floatValue();
            floatRef.element = ((Number) animationScope.getValue()).floatValue();
        }
        return Unit.INSTANCE;
    }
}
