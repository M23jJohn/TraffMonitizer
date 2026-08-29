package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;

/* compiled from: EditingBuffer.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", "target", "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EditingBufferKt {
    /* renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m7016updateRangeAfterDeletepWDy79M(long j, long j2) {
        int m6844getLengthimpl;
        int m6846getMinimpl = TextRange.m6846getMinimpl(j);
        int m6845getMaximpl = TextRange.m6845getMaximpl(j);
        if (TextRange.m6850intersects5zctL8(j2, j)) {
            if (TextRange.m6838contains5zctL8(j2, j)) {
                m6846getMinimpl = TextRange.m6846getMinimpl(j2);
                m6845getMaximpl = m6846getMinimpl;
            } else {
                if (TextRange.m6838contains5zctL8(j, j2)) {
                    m6844getLengthimpl = TextRange.m6844getLengthimpl(j2);
                } else if (TextRange.m6839containsimpl(j2, m6846getMinimpl)) {
                    m6846getMinimpl = TextRange.m6846getMinimpl(j2);
                    m6844getLengthimpl = TextRange.m6844getLengthimpl(j2);
                } else {
                    m6845getMaximpl = TextRange.m6846getMinimpl(j2);
                }
                m6845getMaximpl -= m6844getLengthimpl;
            }
        } else if (m6845getMaximpl > TextRange.m6846getMinimpl(j2)) {
            m6846getMinimpl -= TextRange.m6844getLengthimpl(j2);
            m6844getLengthimpl = TextRange.m6844getLengthimpl(j2);
            m6845getMaximpl -= m6844getLengthimpl;
        }
        return TextRangeKt.TextRange(m6846getMinimpl, m6845getMaximpl);
    }
}
