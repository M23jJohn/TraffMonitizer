package androidx.compose.foundation.text.selection;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: SelectionGestures.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 1, 1, 1}, l = {358, 386}, m = "mouseSelectionBtf2", n = {"$this$mouseSelectionBtf2", "observer", "$this$mouseSelectionBtf2", "observer", "dragConsumed"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
/* loaded from: classes.dex */
final class SelectionGesturesKt$mouseSelectionBtf2$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    SelectionGesturesKt$mouseSelectionBtf2$1(Continuation<? super SelectionGesturesKt$mouseSelectionBtf2$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object mouseSelectionBtf2;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        mouseSelectionBtf2 = SelectionGesturesKt.mouseSelectionBtf2(null, null, null, null, this);
        return mouseSelectionBtf2;
    }
}
