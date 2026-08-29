package androidx.compose.foundation;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Hoverable.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.HoverableNode", f = "Hoverable.kt", i = {}, l = {114}, m = "emitExit", n = {}, s = {})
/* loaded from: classes.dex */
final class HoverableNode$emitExit$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HoverableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HoverableNode$emitExit$1(HoverableNode hoverableNode, Continuation<? super HoverableNode$emitExit$1> continuation) {
        super(continuation);
        this.this$0 = hoverableNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object emitExit;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        emitExit = this.this$0.emitExit(this);
        return emitExit;
    }
}
