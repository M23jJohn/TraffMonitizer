package androidx.compose.ui.text;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: Savers.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/text/AnnotationType;", "", "<init>", "(Ljava/lang/String;I)V", "Paragraph", "Span", "VerbatimTts", "Url", "Link", "Clickable", "String", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
final class AnnotationType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AnnotationType[] $VALUES;
    public static final AnnotationType Paragraph = new AnnotationType("Paragraph", 0);
    public static final AnnotationType Span = new AnnotationType("Span", 1);
    public static final AnnotationType VerbatimTts = new AnnotationType("VerbatimTts", 2);
    public static final AnnotationType Url = new AnnotationType("Url", 3);
    public static final AnnotationType Link = new AnnotationType("Link", 4);
    public static final AnnotationType Clickable = new AnnotationType("Clickable", 5);
    public static final AnnotationType String = new AnnotationType("String", 6);

    private static final /* synthetic */ AnnotationType[] $values() {
        return new AnnotationType[]{Paragraph, Span, VerbatimTts, Url, Link, Clickable, String};
    }

    public static EnumEntries<AnnotationType> getEntries() {
        return $ENTRIES;
    }

    private AnnotationType(String str, int i) {
    }

    static {
        AnnotationType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static AnnotationType valueOf(String str) {
        return (AnnotationType) Enum.valueOf(AnnotationType.class, str);
    }

    public static AnnotationType[] values() {
        return (AnnotationType[]) $VALUES.clone();
    }
}
