package androidx.compose.foundation.text.selection;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: SelectionGestures.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/selection/DownResolution;", "", "<init>", "(Ljava/lang/String;I)V", "Up", "Drag", "Timeout", "Cancel", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class DownResolution {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DownResolution[] $VALUES;
    public static final DownResolution Up = new DownResolution("Up", 0);
    public static final DownResolution Drag = new DownResolution("Drag", 1);
    public static final DownResolution Timeout = new DownResolution("Timeout", 2);
    public static final DownResolution Cancel = new DownResolution("Cancel", 3);

    private static final /* synthetic */ DownResolution[] $values() {
        return new DownResolution[]{Up, Drag, Timeout, Cancel};
    }

    public static EnumEntries<DownResolution> getEntries() {
        return $ENTRIES;
    }

    private DownResolution(String str, int i) {
    }

    static {
        DownResolution[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static DownResolution valueOf(String str) {
        return (DownResolution) Enum.valueOf(DownResolution.class, str);
    }

    public static DownResolution[] values() {
        return (DownResolution[]) $VALUES.clone();
    }
}
