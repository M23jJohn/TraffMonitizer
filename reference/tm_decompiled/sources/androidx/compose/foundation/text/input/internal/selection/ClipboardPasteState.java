package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.ui.platform.Clipboard;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: TextFieldSelectionState.android.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/ClipboardPasteState;", "", "clipboard", "Landroidx/compose/ui/platform/Clipboard;", "<init>", "(Landroidx/compose/ui/platform/Clipboard;)V", "_hasClip", "", "_hasText", "hasText", "getHasText", "()Z", "hasClip", "getHasClip", "update", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ClipboardPasteState {
    public static final int $stable = 8;
    private boolean _hasClip;
    private boolean _hasText;
    private final Clipboard clipboard;

    public ClipboardPasteState(Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    /* renamed from: getHasText, reason: from getter */
    public final boolean get_hasText() {
        return this._hasText;
    }

    /* renamed from: getHasClip, reason: from getter */
    public final boolean get_hasClip() {
        return this._hasClip;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0022, code lost:
    
        if (r2.hasMimeType("text/*") == true) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object update(Continuation<? super Unit> continuation) {
        boolean hasPrimaryClip = this.clipboard.getNativeClipboard().hasPrimaryClip();
        this._hasClip = hasPrimaryClip;
        boolean z = hasPrimaryClip && (r2 = this.clipboard.getNativeClipboard().getPrimaryClipDescription()) != null;
        this._hasText = z;
        return Unit.INSTANCE;
    }
}
