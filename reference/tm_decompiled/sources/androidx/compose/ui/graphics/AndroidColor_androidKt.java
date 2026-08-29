package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.ULong;

/* compiled from: AndroidColor.android.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u0002*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"toColorLong", "", "Landroidx/compose/ui/graphics/Color;", "toColorLong-8_81llA", "(J)J", "fromColorLong", "Landroidx/compose/ui/graphics/Color$Companion;", "colorLong", "(Landroidx/compose/ui/graphics/Color$Companion;J)J", "ui-graphics_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidColor_androidKt {
    /* renamed from: toColorLong-8_81llA, reason: not valid java name */
    public static final long m4452toColorLong8_81llA(long j) {
        int compare;
        long j2 = 63 & j;
        compare = Long.compare(ULong.m8252constructorimpl(j2) ^ Long.MIN_VALUE, 16 ^ Long.MIN_VALUE);
        return compare < 0 ? j : ULong.m8252constructorimpl(ULong.m8252constructorimpl(j & ULong.m8252constructorimpl(-64L)) | ULong.m8252constructorimpl(ULong.m8252constructorimpl(j2) - 1));
    }

    public static final long fromColorLong(Color.Companion companion, long j) {
        long j2 = 63 & j;
        if (j2 >= 16) {
            j = (j & (-64)) | (j2 + 1);
        }
        return Color.m4611constructorimpl(ULong.m8252constructorimpl(j));
    }
}
