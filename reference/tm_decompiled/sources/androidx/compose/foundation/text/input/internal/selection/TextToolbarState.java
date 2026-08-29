package androidx.compose.foundation.text.input.internal.selection;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: TextFieldSelectionState.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;", "", "<init>", "(Ljava/lang/String;I)V", "None", "Cursor", "Selection", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextToolbarState {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ TextToolbarState[] $VALUES;
    public static final TextToolbarState None = new TextToolbarState("None", 0);
    public static final TextToolbarState Cursor = new TextToolbarState("Cursor", 1);
    public static final TextToolbarState Selection = new TextToolbarState("Selection", 2);

    private static final /* synthetic */ TextToolbarState[] $values() {
        return new TextToolbarState[]{None, Cursor, Selection};
    }

    public static EnumEntries<TextToolbarState> getEntries() {
        return $ENTRIES;
    }

    private TextToolbarState(String str, int i) {
    }

    static {
        TextToolbarState[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static TextToolbarState valueOf(String str) {
        return (TextToolbarState) Enum.valueOf(TextToolbarState.class, str);
    }

    public static TextToolbarState[] values() {
        return (TextToolbarState[]) $VALUES.clone();
    }
}
