package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.EditorInfo;
import androidx.activity.ComponentDialog$$ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;

/* compiled from: EditorInfo.android.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/input/internal/EditorInfoApi34;", "", "<init>", "()V", "setHandwritingGestures", "", "editorInfo", "Landroid/view/inputmethod/EditorInfo;", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class EditorInfoApi34 {
    public static final EditorInfoApi34 INSTANCE = new EditorInfoApi34();

    private EditorInfoApi34() {
    }

    public final void setHandwritingGestures(EditorInfo editorInfo) {
        editorInfo.setSupportedHandwritingGestures(CollectionsKt.listOf((Object[]) new Class[]{ComponentDialog$$ExternalSyntheticApiModelOutline0.m28m(), ComponentDialog$$ExternalSyntheticApiModelOutline0.m$3(), ComponentDialog$$ExternalSyntheticApiModelOutline0.m$1(), ComponentDialog$$ExternalSyntheticApiModelOutline0.m$2(), ComponentDialog$$ExternalSyntheticApiModelOutline0.m$4(), ComponentDialog$$ExternalSyntheticApiModelOutline0.m$5(), ComponentDialog$$ExternalSyntheticApiModelOutline0.m$6()}));
        editorInfo.setSupportedHandwritingGesturePreviews(SetsKt.setOf((Object[]) new Class[]{ComponentDialog$$ExternalSyntheticApiModelOutline0.m28m(), ComponentDialog$$ExternalSyntheticApiModelOutline0.m$3(), ComponentDialog$$ExternalSyntheticApiModelOutline0.m$1(), ComponentDialog$$ExternalSyntheticApiModelOutline0.m$2()}));
    }
}
