package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Transition.kt */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$snapTo$2", f = "Transition.kt", i = {}, l = {458}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SeekableTransitionState$snapTo$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ S $targetState;
    final /* synthetic */ Transition<S> $transition;
    int label;
    final /* synthetic */ SeekableTransitionState<S> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SeekableTransitionState$snapTo$2(SeekableTransitionState<S> seekableTransitionState, S s, Transition<S> transition, Continuation<? super SeekableTransitionState$snapTo$2> continuation) {
        super(1, continuation);
        this.this$0 = seekableTransitionState;
        this.$targetState = s;
        this.$transition = transition;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new SeekableTransitionState$snapTo$2(this.this$0, this.$targetState, this.$transition, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((SeekableTransitionState$snapTo$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        float f;
        Object waitForCompositionAfterTargetStateChange;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.endAllAnimations();
            ((SeekableTransitionState) this.this$0).lastFrameTimeNanos = Long.MIN_VALUE;
            this.this$0.setFraction(0.0f);
            S s = this.$targetState;
            if (Intrinsics.areEqual(s, this.this$0.getCurrentState())) {
                f = -4.0f;
            } else {
                f = Intrinsics.areEqual(s, this.this$0.getTargetState()) ? -5.0f : -3.0f;
            }
            this.$transition.updateTarget$animation_core(this.$targetState);
            this.$transition.setPlayTimeNanos(0L);
            this.this$0.setTargetState$animation_core(this.$targetState);
            this.this$0.setFraction(0.0f);
            this.this$0.setCurrentState$animation_core(this.$targetState);
            this.$transition.resetAnimationFraction$animation_core(f);
            if (f == -3.0f) {
                this.label = 1;
                waitForCompositionAfterTargetStateChange = this.this$0.waitForCompositionAfterTargetStateChange(this);
                if (waitForCompositionAfterTargetStateChange == coroutine_suspended) {
                    return coroutine_suspended;
                }
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
