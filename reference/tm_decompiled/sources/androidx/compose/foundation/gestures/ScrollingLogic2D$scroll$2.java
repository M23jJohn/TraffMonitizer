package androidx.compose.foundation.gestures;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: Scrollable2D.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/Scroll2DScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollingLogic2D$scroll$2", f = "Scrollable2D.kt", i = {}, l = {496}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class ScrollingLogic2D$scroll$2 extends SuspendLambda implements Function2<Scroll2DScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<NestedScrollScope, Continuation<? super Unit>, Object> $block;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ScrollingLogic2D this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ScrollingLogic2D$scroll$2(ScrollingLogic2D scrollingLogic2D, Function2<? super NestedScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super ScrollingLogic2D$scroll$2> continuation) {
        super(2, continuation);
        this.this$0 = scrollingLogic2D;
        this.$block = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ScrollingLogic2D$scroll$2 scrollingLogic2D$scroll$2 = new ScrollingLogic2D$scroll$2(this.this$0, this.$block, continuation);
        scrollingLogic2D$scroll$2.L$0 = obj;
        return scrollingLogic2D$scroll$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Scroll2DScope scroll2DScope, Continuation<? super Unit> continuation) {
        return ((ScrollingLogic2D$scroll$2) create(scroll2DScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ScrollingLogic2D$nestedScrollScope$1 scrollingLogic2D$nestedScrollScope$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.outerStateScope = (Scroll2DScope) this.L$0;
            Function2<NestedScrollScope, Continuation<? super Unit>, Object> function2 = this.$block;
            scrollingLogic2D$nestedScrollScope$1 = this.this$0.nestedScrollScope;
            this.label = 1;
            if (function2.invoke(scrollingLogic2D$nestedScrollScope$1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
