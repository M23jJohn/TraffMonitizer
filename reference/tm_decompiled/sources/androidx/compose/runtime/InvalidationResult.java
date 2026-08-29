package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: Composer.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/runtime/InvalidationResult;", "", "<init>", "(Ljava/lang/String;I)V", "IGNORED", "SCHEDULED", "DEFERRED", "IMMINENT", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class InvalidationResult {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ InvalidationResult[] $VALUES;
    public static final InvalidationResult IGNORED = new InvalidationResult("IGNORED", 0);
    public static final InvalidationResult SCHEDULED = new InvalidationResult("SCHEDULED", 1);
    public static final InvalidationResult DEFERRED = new InvalidationResult("DEFERRED", 2);
    public static final InvalidationResult IMMINENT = new InvalidationResult("IMMINENT", 3);

    private static final /* synthetic */ InvalidationResult[] $values() {
        return new InvalidationResult[]{IGNORED, SCHEDULED, DEFERRED, IMMINENT};
    }

    public static EnumEntries<InvalidationResult> getEntries() {
        return $ENTRIES;
    }

    private InvalidationResult(String str, int i) {
    }

    static {
        InvalidationResult[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static InvalidationResult valueOf(String str) {
        return (InvalidationResult) Enum.valueOf(InvalidationResult.class, str);
    }

    public static InvalidationResult[] values() {
        return (InvalidationResult[]) $VALUES.clone();
    }
}
