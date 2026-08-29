package androidx.compose.foundation.text;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: KeyCommand.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b6\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8¨\u00069"}, d2 = {"Landroidx/compose/foundation/text/KeyCommand;", "", "editsText", "", "<init>", "(Ljava/lang/String;IZ)V", "getEditsText", "()Z", "LEFT_CHAR", "RIGHT_CHAR", "RIGHT_WORD", "LEFT_WORD", "NEXT_PARAGRAPH", "PREV_PARAGRAPH", "LINE_START", "LINE_END", "LINE_LEFT", "LINE_RIGHT", "UP", "DOWN", "CENTER", "PAGE_UP", "PAGE_DOWN", "HOME", "END", "COPY", "PASTE", "CUT", "DELETE_PREV_CHAR", "DELETE_NEXT_CHAR", "DELETE_PREV_WORD", "DELETE_NEXT_WORD", "DELETE_FROM_LINE_START", "DELETE_TO_LINE_END", "SELECT_ALL", "SELECT_LEFT_CHAR", "SELECT_RIGHT_CHAR", "SELECT_UP", "SELECT_DOWN", "SELECT_PAGE_UP", "SELECT_PAGE_DOWN", "SELECT_HOME", "SELECT_END", "SELECT_LEFT_WORD", "SELECT_RIGHT_WORD", "SELECT_NEXT_PARAGRAPH", "SELECT_PREV_PARAGRAPH", "SELECT_LINE_START", "SELECT_LINE_END", "SELECT_LINE_LEFT", "SELECT_LINE_RIGHT", "DESELECT", "NEW_LINE", "TAB", "UNDO", "REDO", "CHARACTER_PALETTE", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class KeyCommand {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ KeyCommand[] $VALUES;
    private final boolean editsText;
    public static final KeyCommand LEFT_CHAR = new KeyCommand("LEFT_CHAR", 0, false);
    public static final KeyCommand RIGHT_CHAR = new KeyCommand("RIGHT_CHAR", 1, false);
    public static final KeyCommand RIGHT_WORD = new KeyCommand("RIGHT_WORD", 2, false);
    public static final KeyCommand LEFT_WORD = new KeyCommand("LEFT_WORD", 3, false);
    public static final KeyCommand NEXT_PARAGRAPH = new KeyCommand("NEXT_PARAGRAPH", 4, false);
    public static final KeyCommand PREV_PARAGRAPH = new KeyCommand("PREV_PARAGRAPH", 5, false);
    public static final KeyCommand LINE_START = new KeyCommand("LINE_START", 6, false);
    public static final KeyCommand LINE_END = new KeyCommand("LINE_END", 7, false);
    public static final KeyCommand LINE_LEFT = new KeyCommand("LINE_LEFT", 8, false);
    public static final KeyCommand LINE_RIGHT = new KeyCommand("LINE_RIGHT", 9, false);
    public static final KeyCommand UP = new KeyCommand("UP", 10, false);
    public static final KeyCommand DOWN = new KeyCommand("DOWN", 11, false);
    public static final KeyCommand CENTER = new KeyCommand("CENTER", 12, false);
    public static final KeyCommand PAGE_UP = new KeyCommand("PAGE_UP", 13, false);
    public static final KeyCommand PAGE_DOWN = new KeyCommand("PAGE_DOWN", 14, false);
    public static final KeyCommand HOME = new KeyCommand("HOME", 15, false);
    public static final KeyCommand END = new KeyCommand("END", 16, false);
    public static final KeyCommand COPY = new KeyCommand("COPY", 17, false);
    public static final KeyCommand PASTE = new KeyCommand("PASTE", 18, true);
    public static final KeyCommand CUT = new KeyCommand("CUT", 19, true);
    public static final KeyCommand DELETE_PREV_CHAR = new KeyCommand("DELETE_PREV_CHAR", 20, true);
    public static final KeyCommand DELETE_NEXT_CHAR = new KeyCommand("DELETE_NEXT_CHAR", 21, true);
    public static final KeyCommand DELETE_PREV_WORD = new KeyCommand("DELETE_PREV_WORD", 22, true);
    public static final KeyCommand DELETE_NEXT_WORD = new KeyCommand("DELETE_NEXT_WORD", 23, true);
    public static final KeyCommand DELETE_FROM_LINE_START = new KeyCommand("DELETE_FROM_LINE_START", 24, true);
    public static final KeyCommand DELETE_TO_LINE_END = new KeyCommand("DELETE_TO_LINE_END", 25, true);
    public static final KeyCommand SELECT_ALL = new KeyCommand("SELECT_ALL", 26, false);
    public static final KeyCommand SELECT_LEFT_CHAR = new KeyCommand("SELECT_LEFT_CHAR", 27, false);
    public static final KeyCommand SELECT_RIGHT_CHAR = new KeyCommand("SELECT_RIGHT_CHAR", 28, false);
    public static final KeyCommand SELECT_UP = new KeyCommand("SELECT_UP", 29, false);
    public static final KeyCommand SELECT_DOWN = new KeyCommand("SELECT_DOWN", 30, false);
    public static final KeyCommand SELECT_PAGE_UP = new KeyCommand("SELECT_PAGE_UP", 31, false);
    public static final KeyCommand SELECT_PAGE_DOWN = new KeyCommand("SELECT_PAGE_DOWN", 32, false);
    public static final KeyCommand SELECT_HOME = new KeyCommand("SELECT_HOME", 33, false);
    public static final KeyCommand SELECT_END = new KeyCommand("SELECT_END", 34, false);
    public static final KeyCommand SELECT_LEFT_WORD = new KeyCommand("SELECT_LEFT_WORD", 35, false);
    public static final KeyCommand SELECT_RIGHT_WORD = new KeyCommand("SELECT_RIGHT_WORD", 36, false);
    public static final KeyCommand SELECT_NEXT_PARAGRAPH = new KeyCommand("SELECT_NEXT_PARAGRAPH", 37, false);
    public static final KeyCommand SELECT_PREV_PARAGRAPH = new KeyCommand("SELECT_PREV_PARAGRAPH", 38, false);
    public static final KeyCommand SELECT_LINE_START = new KeyCommand("SELECT_LINE_START", 39, false);
    public static final KeyCommand SELECT_LINE_END = new KeyCommand("SELECT_LINE_END", 40, false);
    public static final KeyCommand SELECT_LINE_LEFT = new KeyCommand("SELECT_LINE_LEFT", 41, false);
    public static final KeyCommand SELECT_LINE_RIGHT = new KeyCommand("SELECT_LINE_RIGHT", 42, false);
    public static final KeyCommand DESELECT = new KeyCommand("DESELECT", 43, false);
    public static final KeyCommand NEW_LINE = new KeyCommand("NEW_LINE", 44, true);
    public static final KeyCommand TAB = new KeyCommand("TAB", 45, true);
    public static final KeyCommand UNDO = new KeyCommand("UNDO", 46, true);
    public static final KeyCommand REDO = new KeyCommand("REDO", 47, true);
    public static final KeyCommand CHARACTER_PALETTE = new KeyCommand("CHARACTER_PALETTE", 48, true);

    private static final /* synthetic */ KeyCommand[] $values() {
        return new KeyCommand[]{LEFT_CHAR, RIGHT_CHAR, RIGHT_WORD, LEFT_WORD, NEXT_PARAGRAPH, PREV_PARAGRAPH, LINE_START, LINE_END, LINE_LEFT, LINE_RIGHT, UP, DOWN, CENTER, PAGE_UP, PAGE_DOWN, HOME, END, COPY, PASTE, CUT, DELETE_PREV_CHAR, DELETE_NEXT_CHAR, DELETE_PREV_WORD, DELETE_NEXT_WORD, DELETE_FROM_LINE_START, DELETE_TO_LINE_END, SELECT_ALL, SELECT_LEFT_CHAR, SELECT_RIGHT_CHAR, SELECT_UP, SELECT_DOWN, SELECT_PAGE_UP, SELECT_PAGE_DOWN, SELECT_HOME, SELECT_END, SELECT_LEFT_WORD, SELECT_RIGHT_WORD, SELECT_NEXT_PARAGRAPH, SELECT_PREV_PARAGRAPH, SELECT_LINE_START, SELECT_LINE_END, SELECT_LINE_LEFT, SELECT_LINE_RIGHT, DESELECT, NEW_LINE, TAB, UNDO, REDO, CHARACTER_PALETTE};
    }

    public static EnumEntries<KeyCommand> getEntries() {
        return $ENTRIES;
    }

    private KeyCommand(String str, int i, boolean z) {
        this.editsText = z;
    }

    public final boolean getEditsText() {
        return this.editsText;
    }

    static {
        KeyCommand[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static KeyCommand valueOf(String str) {
        return (KeyCommand) Enum.valueOf(KeyCommand.class, str);
    }

    public static KeyCommand[] values() {
        return (KeyCommand[]) $VALUES.clone();
    }
}
