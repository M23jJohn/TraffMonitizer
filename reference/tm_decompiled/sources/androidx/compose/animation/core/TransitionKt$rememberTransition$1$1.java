package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;

/* compiled from: Transition.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.core.TransitionKt$rememberTransition$1$1", f = "Transition.kt", i = {0}, l = {2173}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv"}, s = {"L$0"})
/* loaded from: classes.dex */
final class TransitionKt$rememberTransition$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TransitionState<T> $transitionState;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TransitionKt$rememberTransition$1$1(TransitionState<T> transitionState, Continuation<? super TransitionKt$rememberTransition$1$1> continuation) {
        super(2, continuation);
        this.$transitionState = transitionState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TransitionKt$rememberTransition$1$1(this.$transitionState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TransitionKt$rememberTransition$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Mutex compositionContinuationMutex;
        TransitionState transitionState;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ((SeekableTransitionState) this.$transitionState).observeTotalDuration$animation_core();
            compositionContinuationMutex = ((SeekableTransitionState) this.$transitionState).getCompositionContinuationMutex();
            TransitionState transitionState2 = this.$transitionState;
            this.L$0 = compositionContinuationMutex;
            this.L$1 = transitionState2;
            this.label = 1;
            if (compositionContinuationMutex.lock(null, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            transitionState = transitionState2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            transitionState = (TransitionState) this.L$1;
            compositionContinuationMutex = (Mutex) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        try {
            ((SeekableTransitionState) transitionState).setComposedTargetState$animation_core(((SeekableTransitionState) transitionState).getTargetState());
            CancellableContinuation compositionContinuation$animation_core = ((SeekableTransitionState) transitionState).getCompositionContinuation$animation_core();
            if (compositionContinuation$animation_core != null) {
                Result.Companion companion = Result.INSTANCE;
                compositionContinuation$animation_core.resumeWith(Result.m8075constructorimpl(((SeekableTransitionState) transitionState).getTargetState()));
            }
            ((SeekableTransitionState) transitionState).setCompositionContinuation$animation_core(null);
            Unit unit = Unit.INSTANCE;
            compositionContinuationMutex.unlock(null);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            compositionContinuationMutex.unlock(null);
            throw th;
        }
    }
}
