package androidx.compose.foundation.gestures;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Transformable.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt", f = "Transformable.kt", i = {0, 0}, l = {312}, m = "awaitCtrlMouseScrollOrNull", n = {"$this$awaitCtrlMouseScrollOrNull", "scrollConfig"}, s = {"L$0", "L$1"})
/* loaded from: classes.dex */
final class TransformableKt$awaitCtrlMouseScrollOrNull$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    TransformableKt$awaitCtrlMouseScrollOrNull$1(Continuation<? super TransformableKt$awaitCtrlMouseScrollOrNull$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object awaitCtrlMouseScrollOrNull;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        awaitCtrlMouseScrollOrNull = TransformableKt.awaitCtrlMouseScrollOrNull(null, null, this);
        return awaitCtrlMouseScrollOrNull;
    }
}
