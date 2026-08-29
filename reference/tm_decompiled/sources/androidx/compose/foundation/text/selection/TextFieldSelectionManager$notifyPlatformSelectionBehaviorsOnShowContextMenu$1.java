package androidx.compose.foundation.text.selection;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: TextFieldSelectionManager.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.TextFieldSelectionManager", f = "TextFieldSelectionManager.kt", i = {}, l = {783}, m = "notifyPlatformSelectionBehaviorsOnShowContextMenu", n = {}, s = {})
/* loaded from: classes.dex */
final class TextFieldSelectionManager$notifyPlatformSelectionBehaviorsOnShowContextMenu$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TextFieldSelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldSelectionManager$notifyPlatformSelectionBehaviorsOnShowContextMenu$1(TextFieldSelectionManager textFieldSelectionManager, Continuation<? super TextFieldSelectionManager$notifyPlatformSelectionBehaviorsOnShowContextMenu$1> continuation) {
        super(continuation);
        this.this$0 = textFieldSelectionManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object notifyPlatformSelectionBehaviorsOnShowContextMenu;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        notifyPlatformSelectionBehaviorsOnShowContextMenu = this.this$0.notifyPlatformSelectionBehaviorsOnShowContextMenu(this);
        return notifyPlatformSelectionBehaviorsOnShowContextMenu;
    }
}
