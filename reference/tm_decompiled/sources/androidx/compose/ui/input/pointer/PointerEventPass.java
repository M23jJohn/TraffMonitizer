package androidx.compose.ui.input.pointer;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: PointerEvent.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerEventPass;", "", "<init>", "(Ljava/lang/String;I)V", "Initial", "Main", "Final", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PointerEventPass {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ PointerEventPass[] $VALUES;
    public static final PointerEventPass Initial = new PointerEventPass("Initial", 0);
    public static final PointerEventPass Main = new PointerEventPass("Main", 1);
    public static final PointerEventPass Final = new PointerEventPass("Final", 2);

    private static final /* synthetic */ PointerEventPass[] $values() {
        return new PointerEventPass[]{Initial, Main, Final};
    }

    public static EnumEntries<PointerEventPass> getEntries() {
        return $ENTRIES;
    }

    private PointerEventPass(String str, int i) {
    }

    static {
        PointerEventPass[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static PointerEventPass valueOf(String str) {
        return (PointerEventPass) Enum.valueOf(PointerEventPass.class, str);
    }

    public static PointerEventPass[] values() {
        return (PointerEventPass[]) $VALUES.clone();
    }
}
