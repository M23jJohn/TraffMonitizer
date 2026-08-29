package androidx.compose.foundation.lazy.layout;

import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* compiled from: LazyLayoutCacheWindow.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001c\u0010\u0000\u001a\u00020\u00012\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\bH\u0007¨\u0006\n"}, d2 = {"LazyLayoutCacheWindow", "Landroidx/compose/foundation/lazy/layout/LazyLayoutCacheWindow;", "ahead", "Landroidx/compose/ui/unit/Dp;", "behind", "LazyLayoutCacheWindow-YgX7TsA", "(FF)Landroidx/compose/foundation/lazy/layout/LazyLayoutCacheWindow;", "aheadFraction", "", "behindFraction", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyLayoutCacheWindowKt {
    /* renamed from: LazyLayoutCacheWindow-YgX7TsA, reason: not valid java name */
    public static final LazyLayoutCacheWindow m1061LazyLayoutCacheWindowYgX7TsA(float f, float f2) {
        return new DpLazyLayoutCacheWindow(f, f2, null);
    }

    public static /* synthetic */ LazyLayoutCacheWindow LazyLayoutCacheWindow$default(float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        return LazyLayoutCacheWindow(f, f2);
    }

    public static final LazyLayoutCacheWindow LazyLayoutCacheWindow(float f, float f2) {
        return new FractionLazyLayoutCacheWindow(f, f2);
    }

    /* renamed from: LazyLayoutCacheWindow-YgX7TsA$default, reason: not valid java name */
    public static /* synthetic */ LazyLayoutCacheWindow m1062LazyLayoutCacheWindowYgX7TsA$default(float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.m7399constructorimpl(0);
        }
        if ((i & 2) != 0) {
            f2 = Dp.m7399constructorimpl(0);
        }
        return m1061LazyLayoutCacheWindowYgX7TsA(f, f2);
    }
}
