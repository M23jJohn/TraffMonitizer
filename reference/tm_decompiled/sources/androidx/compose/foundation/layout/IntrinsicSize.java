package androidx.compose.foundation.layout;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: Intrinsic.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/foundation/layout/IntrinsicSize;", "", "<init>", "(Ljava/lang/String;I)V", "Min", "Max", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class IntrinsicSize {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ IntrinsicSize[] $VALUES;
    public static final IntrinsicSize Min = new IntrinsicSize("Min", 0);
    public static final IntrinsicSize Max = new IntrinsicSize("Max", 1);

    private static final /* synthetic */ IntrinsicSize[] $values() {
        return new IntrinsicSize[]{Min, Max};
    }

    public static EnumEntries<IntrinsicSize> getEntries() {
        return $ENTRIES;
    }

    private IntrinsicSize(String str, int i) {
    }

    static {
        IntrinsicSize[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static IntrinsicSize valueOf(String str) {
        return (IntrinsicSize) Enum.valueOf(IntrinsicSize.class, str);
    }

    public static IntrinsicSize[] values() {
        return (IntrinsicSize[]) $VALUES.clone();
    }
}
