package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import kotlin.Metadata;

/* compiled from: LazyListLayoutInfo.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0012\u0010\r\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\nR\u0014\u0010\u001d\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\nR\u0014\u0010\u001f\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006!À\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "", "visibleItemsInfo", "", "Landroidx/compose/foundation/lazy/LazyListItemInfo;", "getVisibleItemsInfo", "()Ljava/util/List;", "viewportStartOffset", "", "getViewportStartOffset", "()I", "viewportEndOffset", "getViewportEndOffset", "totalItemsCount", "getTotalItemsCount", "viewportSize", "Landroidx/compose/ui/unit/IntSize;", "getViewportSize-YbymL2g", "()J", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "reverseLayout", "", "getReverseLayout", "()Z", "beforeContentPadding", "getBeforeContentPadding", "afterContentPadding", "getAfterContentPadding", "mainAxisItemSpacing", "getMainAxisItemSpacing", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public interface LazyListLayoutInfo {
    int getAfterContentPadding();

    int getBeforeContentPadding();

    int getMainAxisItemSpacing();

    Orientation getOrientation();

    boolean getReverseLayout();

    int getTotalItemsCount();

    int getViewportEndOffset();

    /* renamed from: getViewportSize-YbymL2g, reason: not valid java name */
    long mo995getViewportSizeYbymL2g();

    int getViewportStartOffset();

    List<LazyListItemInfo> getVisibleItemsInfo();

    /* compiled from: LazyListLayoutInfo.kt */
    /* renamed from: androidx.compose.foundation.lazy.LazyListLayoutInfo$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static int $default$getAfterContentPadding(LazyListLayoutInfo _this) {
            return 0;
        }

        public static int $default$getBeforeContentPadding(LazyListLayoutInfo _this) {
            return 0;
        }

        public static int $default$getMainAxisItemSpacing(LazyListLayoutInfo _this) {
            return 0;
        }

        public static boolean $default$getReverseLayout(LazyListLayoutInfo _this) {
            return false;
        }

        /* renamed from: $default$getViewportSize-YbymL2g, reason: not valid java name */
        public static long m996$default$getViewportSizeYbymL2g(LazyListLayoutInfo _this) {
            return IntSize.INSTANCE.m7579getZeroYbymL2g();
        }

        public static Orientation $default$getOrientation(LazyListLayoutInfo _this) {
            return Orientation.Vertical;
        }
    }

    /* compiled from: LazyListLayoutInfo.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* renamed from: getViewportSize-YbymL2g, reason: not valid java name */
        public static long m998getViewportSizeYbymL2g(LazyListLayoutInfo lazyListLayoutInfo) {
            return CC.m996$default$getViewportSizeYbymL2g(lazyListLayoutInfo);
        }

        @Deprecated
        public static Orientation getOrientation(LazyListLayoutInfo lazyListLayoutInfo) {
            return CC.$default$getOrientation(lazyListLayoutInfo);
        }

        @Deprecated
        public static boolean getReverseLayout(LazyListLayoutInfo lazyListLayoutInfo) {
            return CC.$default$getReverseLayout(lazyListLayoutInfo);
        }

        @Deprecated
        public static int getBeforeContentPadding(LazyListLayoutInfo lazyListLayoutInfo) {
            return CC.$default$getBeforeContentPadding(lazyListLayoutInfo);
        }

        @Deprecated
        public static int getAfterContentPadding(LazyListLayoutInfo lazyListLayoutInfo) {
            return CC.$default$getAfterContentPadding(lazyListLayoutInfo);
        }

        @Deprecated
        public static int getMainAxisItemSpacing(LazyListLayoutInfo lazyListLayoutInfo) {
            return CC.$default$getMainAxisItemSpacing(lazyListLayoutInfo);
        }
    }
}
