package androidx.compose.foundation.text.modifiers;

import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;

/* compiled from: MultiParagraphLayoutCache.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0082\u0002¢\u0006\u0004\b\u0003\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"times", "Landroidx/compose/ui/unit/TextUnit;", "other", "times-NB67dxo", "(JJ)J", "DefaultFontSize", "J", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MultiParagraphLayoutCacheKt {
    private static final long DefaultFontSize = TextUnitKt.getSp(14);

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: times-NB67dxo, reason: not valid java name */
    public static final long m1749timesNB67dxo(long j, long j2) {
        if (TextUnit.m7599isEmimpl(j2)) {
            if (TextUnit.m7599isEmimpl(j)) {
                throw new IllegalStateException("Cannot convert Em to Px when style.fontSize is Em (" + ((Object) TextUnit.m7604toStringimpl(j2)) + "). Please declare the style.fontSize with Sp units instead.");
            }
            if (TextUnit.m7595getRawTypeimpl(j) == 0) {
                long j3 = DefaultFontSize;
                float m7597getValueimpl = TextUnit.m7597getValueimpl(j2);
                TextUnitKt.m7610checkArithmeticR2X_6o(j3);
                return TextUnitKt.pack(TextUnit.m7595getRawTypeimpl(j3), TextUnit.m7597getValueimpl(j3) * m7597getValueimpl);
            }
            float m7597getValueimpl2 = TextUnit.m7597getValueimpl(j2);
            TextUnitKt.m7610checkArithmeticR2X_6o(j);
            return TextUnitKt.pack(TextUnit.m7595getRawTypeimpl(j), TextUnit.m7597getValueimpl(j) * m7597getValueimpl2);
        }
        throw new IllegalArgumentException("The multiplier must be in em, but was " + ((Object) TextUnit.m7604toStringimpl(j2)) + '.');
    }
}
