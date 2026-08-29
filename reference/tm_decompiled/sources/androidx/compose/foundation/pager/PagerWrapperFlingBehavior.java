package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* compiled from: LazyLayoutPager.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/pager/PagerWrapperFlingBehavior;", "Landroidx/compose/foundation/gestures/FlingBehavior;", "originalFlingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "pagerState", "Landroidx/compose/foundation/pager/PagerState;", "<init>", "(Landroidx/compose/foundation/gestures/TargetedFlingBehavior;Landroidx/compose/foundation/pager/PagerState;)V", "getOriginalFlingBehavior", "()Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "getPagerState", "()Landroidx/compose/foundation/pager/PagerState;", "performFling", "", "Landroidx/compose/foundation/gestures/ScrollScope;", "initialVelocity", "(Landroidx/compose/foundation/gestures/ScrollScope;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class PagerWrapperFlingBehavior implements FlingBehavior {
    private final TargetedFlingBehavior originalFlingBehavior;
    private final PagerState pagerState;

    public PagerWrapperFlingBehavior(TargetedFlingBehavior targetedFlingBehavior, PagerState pagerState) {
        this.originalFlingBehavior = targetedFlingBehavior;
        this.pagerState = pagerState;
    }

    public final TargetedFlingBehavior getOriginalFlingBehavior() {
        return this.originalFlingBehavior;
    }

    public final PagerState getPagerState() {
        return this.pagerState;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // androidx.compose.foundation.gestures.FlingBehavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object performFling(final ScrollScope scrollScope, float f, Continuation<? super Float> continuation) {
        PagerWrapperFlingBehavior$performFling$1 pagerWrapperFlingBehavior$performFling$1;
        int i;
        if (continuation instanceof PagerWrapperFlingBehavior$performFling$1) {
            pagerWrapperFlingBehavior$performFling$1 = (PagerWrapperFlingBehavior$performFling$1) continuation;
            if ((pagerWrapperFlingBehavior$performFling$1.label & Integer.MIN_VALUE) != 0) {
                pagerWrapperFlingBehavior$performFling$1.label -= Integer.MIN_VALUE;
                Object obj = pagerWrapperFlingBehavior$performFling$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = pagerWrapperFlingBehavior$performFling$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    TargetedFlingBehavior targetedFlingBehavior = this.originalFlingBehavior;
                    Function1<? super Float, Unit> function1 = new Function1() { // from class: androidx.compose.foundation.pager.PagerWrapperFlingBehavior$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            Unit performFling$lambda$2$lambda$1;
                            performFling$lambda$2$lambda$1 = PagerWrapperFlingBehavior.performFling$lambda$2$lambda$1(PagerWrapperFlingBehavior.this, scrollScope, ((Float) obj2).floatValue());
                            return performFling$lambda$2$lambda$1;
                        }
                    };
                    pagerWrapperFlingBehavior$performFling$1.label = 1;
                    obj = targetedFlingBehavior.performFling(scrollScope, f, function1, pagerWrapperFlingBehavior$performFling$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                float floatValue = ((Number) obj).floatValue();
                if (this.pagerState.getCurrentPageOffsetFraction() != 0.0f && Math.abs(this.pagerState.getCurrentPageOffsetFraction()) < 0.001d) {
                    PagerState pagerState = this.pagerState;
                    PagerState.requestScrollToPage$default(pagerState, pagerState.getCurrentPage(), 0.0f, 2, null);
                } else {
                    Boxing.boxFloat(this.pagerState.getCurrentPageOffsetFraction());
                }
                return Boxing.boxFloat(floatValue);
            }
        }
        pagerWrapperFlingBehavior$performFling$1 = new PagerWrapperFlingBehavior$performFling$1(this, continuation);
        Object obj2 = pagerWrapperFlingBehavior$performFling$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pagerWrapperFlingBehavior$performFling$1.label;
        if (i != 0) {
        }
        float floatValue2 = ((Number) obj2).floatValue();
        if (this.pagerState.getCurrentPageOffsetFraction() != 0.0f) {
            PagerState pagerState2 = this.pagerState;
            PagerState.requestScrollToPage$default(pagerState2, pagerState2.getCurrentPage(), 0.0f, 2, null);
            return Boxing.boxFloat(floatValue2);
        }
        Boxing.boxFloat(this.pagerState.getCurrentPageOffsetFraction());
        return Boxing.boxFloat(floatValue2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit performFling$lambda$2$lambda$1(PagerWrapperFlingBehavior pagerWrapperFlingBehavior, ScrollScope scrollScope, float f) {
        pagerWrapperFlingBehavior.pagerState.updateTargetPage(scrollScope, MathKt.roundToInt(pagerWrapperFlingBehavior.pagerState.getPageSizeWithSpacing$foundation_release() != 0 ? f / pagerWrapperFlingBehavior.pagerState.getPageSizeWithSpacing$foundation_release() : 0.0f) + pagerWrapperFlingBehavior.pagerState.getCurrentPage());
        return Unit.INSTANCE;
    }
}
