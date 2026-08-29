package androidx.compose.foundation.text.selection;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: SelectionLayout.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/text/selection/CrossStatus;", "", "<init>", "(Ljava/lang/String;I)V", "CROSSED", "NOT_CROSSED", "COLLAPSED", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CrossStatus {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CrossStatus[] $VALUES;
    public static final CrossStatus CROSSED = new CrossStatus("CROSSED", 0);
    public static final CrossStatus NOT_CROSSED = new CrossStatus("NOT_CROSSED", 1);
    public static final CrossStatus COLLAPSED = new CrossStatus("COLLAPSED", 2);

    private static final /* synthetic */ CrossStatus[] $values() {
        return new CrossStatus[]{CROSSED, NOT_CROSSED, COLLAPSED};
    }

    public static EnumEntries<CrossStatus> getEntries() {
        return $ENTRIES;
    }

    private CrossStatus(String str, int i) {
    }

    static {
        CrossStatus[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static CrossStatus valueOf(String str) {
        return (CrossStatus) Enum.valueOf(CrossStatus.class, str);
    }

    public static CrossStatus[] values() {
        return (CrossStatus[]) $VALUES.clone();
    }
}
