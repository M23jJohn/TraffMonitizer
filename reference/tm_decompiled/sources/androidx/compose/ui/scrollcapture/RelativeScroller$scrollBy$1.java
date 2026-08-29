package androidx.compose.ui.scrollcapture;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: ComposeScrollCaptureCallback.android.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.ui.scrollcapture.RelativeScroller", f = "ComposeScrollCaptureCallback.android.kt", i = {}, l = {296}, m = "scrollBy", n = {}, s = {})
/* loaded from: classes.dex */
final class RelativeScroller$scrollBy$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RelativeScroller this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RelativeScroller$scrollBy$1(RelativeScroller relativeScroller, Continuation<? super RelativeScroller$scrollBy$1> continuation) {
        super(continuation);
        this.this$0 = relativeScroller;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object scrollBy;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        scrollBy = this.this$0.scrollBy(0.0f, this);
        return scrollBy;
    }
}
