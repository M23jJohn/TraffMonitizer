package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.IntrinsicMeasurable;
import java.util.List;
import kotlin.Metadata;

/* compiled from: RowColumnImpl.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005J$\u0010\u000b\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005J$\u0010\f\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005J$\u0010\u000e\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005J$\u0010\u000f\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005J$\u0010\u0010\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005J$\u0010\u0011\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005J$\u0010\u0012\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005¨\u0006\u0013"}, d2 = {"Landroidx/compose/foundation/layout/IntrinsicMeasureBlocks;", "", "<init>", "()V", "HorizontalMinWidth", "", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "availableHeight", "mainAxisSpacing", "VerticalMinWidth", "HorizontalMinHeight", "availableWidth", "VerticalMinHeight", "HorizontalMaxWidth", "VerticalMaxWidth", "HorizontalMaxHeight", "VerticalMaxHeight", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class IntrinsicMeasureBlocks {
    public static final int $stable = 0;
    public static final IntrinsicMeasureBlocks INSTANCE = new IntrinsicMeasureBlocks();

    private IntrinsicMeasureBlocks() {
    }

    public final int HorizontalMinWidth(List<? extends IntrinsicMeasurable> measurables, int availableHeight, int mainAxisSpacing) {
        if (measurables.isEmpty()) {
            return 0;
        }
        int size = measurables.size();
        int i = 0;
        int i2 = 0;
        float f = 0.0f;
        for (int i3 = 0; i3 < size; i3++) {
            IntrinsicMeasurable intrinsicMeasurable = measurables.get(i3);
            float weight = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(intrinsicMeasurable));
            int minIntrinsicWidth = intrinsicMeasurable.minIntrinsicWidth(availableHeight);
            if (weight == 0.0f) {
                i2 += minIntrinsicWidth;
            } else if (weight > 0.0f) {
                f += weight;
                i = Math.max(i, Math.round(minIntrinsicWidth / weight));
            }
        }
        return Math.round(i * f) + i2 + ((measurables.size() - 1) * mainAxisSpacing);
    }

    public final int VerticalMinWidth(List<? extends IntrinsicMeasurable> measurables, int availableHeight, int mainAxisSpacing) {
        int round;
        if (measurables.isEmpty()) {
            return 0;
        }
        int min = Math.min((measurables.size() - 1) * mainAxisSpacing, availableHeight);
        List<? extends IntrinsicMeasurable> list = measurables;
        int size = list.size();
        int i = 0;
        float f = 0.0f;
        for (int i2 = 0; i2 < size; i2++) {
            IntrinsicMeasurable intrinsicMeasurable = measurables.get(i2);
            float weight = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(intrinsicMeasurable));
            if (weight == 0.0f) {
                int min2 = Math.min(intrinsicMeasurable.maxIntrinsicHeight(Integer.MAX_VALUE), availableHeight == Integer.MAX_VALUE ? Integer.MAX_VALUE : availableHeight - min);
                min += min2;
                i = Math.max(i, intrinsicMeasurable.minIntrinsicWidth(min2));
            } else if (weight > 0.0f) {
                f += weight;
            }
        }
        if (f == 0.0f) {
            round = 0;
        } else {
            round = availableHeight == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.round(Math.max(availableHeight - min, 0) / f);
        }
        int size2 = list.size();
        for (int i3 = 0; i3 < size2; i3++) {
            IntrinsicMeasurable intrinsicMeasurable2 = measurables.get(i3);
            float weight2 = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(intrinsicMeasurable2));
            if (weight2 > 0.0f) {
                i = Math.max(i, intrinsicMeasurable2.minIntrinsicWidth(round != Integer.MAX_VALUE ? Math.round(round * weight2) : Integer.MAX_VALUE));
            }
        }
        return i;
    }

    public final int HorizontalMinHeight(List<? extends IntrinsicMeasurable> measurables, int availableWidth, int mainAxisSpacing) {
        int round;
        if (measurables.isEmpty()) {
            return 0;
        }
        int min = Math.min((measurables.size() - 1) * mainAxisSpacing, availableWidth);
        List<? extends IntrinsicMeasurable> list = measurables;
        int size = list.size();
        int i = 0;
        float f = 0.0f;
        for (int i2 = 0; i2 < size; i2++) {
            IntrinsicMeasurable intrinsicMeasurable = measurables.get(i2);
            float weight = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(intrinsicMeasurable));
            if (weight == 0.0f) {
                int min2 = Math.min(intrinsicMeasurable.maxIntrinsicWidth(Integer.MAX_VALUE), availableWidth == Integer.MAX_VALUE ? Integer.MAX_VALUE : availableWidth - min);
                min += min2;
                i = Math.max(i, intrinsicMeasurable.minIntrinsicHeight(min2));
            } else if (weight > 0.0f) {
                f += weight;
            }
        }
        if (f == 0.0f) {
            round = 0;
        } else {
            round = availableWidth == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.round(Math.max(availableWidth - min, 0) / f);
        }
        int size2 = list.size();
        for (int i3 = 0; i3 < size2; i3++) {
            IntrinsicMeasurable intrinsicMeasurable2 = measurables.get(i3);
            float weight2 = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(intrinsicMeasurable2));
            if (weight2 > 0.0f) {
                i = Math.max(i, intrinsicMeasurable2.minIntrinsicHeight(round != Integer.MAX_VALUE ? Math.round(round * weight2) : Integer.MAX_VALUE));
            }
        }
        return i;
    }

    public final int VerticalMinHeight(List<? extends IntrinsicMeasurable> measurables, int availableWidth, int mainAxisSpacing) {
        if (measurables.isEmpty()) {
            return 0;
        }
        int size = measurables.size();
        int i = 0;
        int i2 = 0;
        float f = 0.0f;
        for (int i3 = 0; i3 < size; i3++) {
            IntrinsicMeasurable intrinsicMeasurable = measurables.get(i3);
            float weight = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(intrinsicMeasurable));
            int minIntrinsicHeight = intrinsicMeasurable.minIntrinsicHeight(availableWidth);
            if (weight == 0.0f) {
                i2 += minIntrinsicHeight;
            } else if (weight > 0.0f) {
                f += weight;
                i = Math.max(i, Math.round(minIntrinsicHeight / weight));
            }
        }
        return Math.round(i * f) + i2 + ((measurables.size() - 1) * mainAxisSpacing);
    }

    public final int HorizontalMaxWidth(List<? extends IntrinsicMeasurable> measurables, int availableHeight, int mainAxisSpacing) {
        if (measurables.isEmpty()) {
            return 0;
        }
        int size = measurables.size();
        int i = 0;
        int i2 = 0;
        float f = 0.0f;
        for (int i3 = 0; i3 < size; i3++) {
            IntrinsicMeasurable intrinsicMeasurable = measurables.get(i3);
            float weight = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(intrinsicMeasurable));
            int maxIntrinsicWidth = intrinsicMeasurable.maxIntrinsicWidth(availableHeight);
            if (weight == 0.0f) {
                i2 += maxIntrinsicWidth;
            } else if (weight > 0.0f) {
                f += weight;
                i = Math.max(i, Math.round(maxIntrinsicWidth / weight));
            }
        }
        return Math.round(i * f) + i2 + ((measurables.size() - 1) * mainAxisSpacing);
    }

    public final int VerticalMaxWidth(List<? extends IntrinsicMeasurable> measurables, int availableHeight, int mainAxisSpacing) {
        int round;
        if (measurables.isEmpty()) {
            return 0;
        }
        int min = Math.min((measurables.size() - 1) * mainAxisSpacing, availableHeight);
        List<? extends IntrinsicMeasurable> list = measurables;
        int size = list.size();
        int i = 0;
        float f = 0.0f;
        for (int i2 = 0; i2 < size; i2++) {
            IntrinsicMeasurable intrinsicMeasurable = measurables.get(i2);
            float weight = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(intrinsicMeasurable));
            if (weight == 0.0f) {
                int min2 = Math.min(intrinsicMeasurable.maxIntrinsicHeight(Integer.MAX_VALUE), availableHeight == Integer.MAX_VALUE ? Integer.MAX_VALUE : availableHeight - min);
                min += min2;
                i = Math.max(i, intrinsicMeasurable.maxIntrinsicWidth(min2));
            } else if (weight > 0.0f) {
                f += weight;
            }
        }
        if (f == 0.0f) {
            round = 0;
        } else {
            round = availableHeight == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.round(Math.max(availableHeight - min, 0) / f);
        }
        int size2 = list.size();
        for (int i3 = 0; i3 < size2; i3++) {
            IntrinsicMeasurable intrinsicMeasurable2 = measurables.get(i3);
            float weight2 = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(intrinsicMeasurable2));
            if (weight2 > 0.0f) {
                i = Math.max(i, intrinsicMeasurable2.maxIntrinsicWidth(round != Integer.MAX_VALUE ? Math.round(round * weight2) : Integer.MAX_VALUE));
            }
        }
        return i;
    }

    public final int HorizontalMaxHeight(List<? extends IntrinsicMeasurable> measurables, int availableWidth, int mainAxisSpacing) {
        int round;
        if (measurables.isEmpty()) {
            return 0;
        }
        int min = Math.min((measurables.size() - 1) * mainAxisSpacing, availableWidth);
        List<? extends IntrinsicMeasurable> list = measurables;
        int size = list.size();
        int i = 0;
        float f = 0.0f;
        for (int i2 = 0; i2 < size; i2++) {
            IntrinsicMeasurable intrinsicMeasurable = measurables.get(i2);
            float weight = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(intrinsicMeasurable));
            if (weight == 0.0f) {
                int min2 = Math.min(intrinsicMeasurable.maxIntrinsicWidth(Integer.MAX_VALUE), availableWidth == Integer.MAX_VALUE ? Integer.MAX_VALUE : availableWidth - min);
                min += min2;
                i = Math.max(i, intrinsicMeasurable.maxIntrinsicHeight(min2));
            } else if (weight > 0.0f) {
                f += weight;
            }
        }
        if (f == 0.0f) {
            round = 0;
        } else {
            round = availableWidth == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.round(Math.max(availableWidth - min, 0) / f);
        }
        int size2 = list.size();
        for (int i3 = 0; i3 < size2; i3++) {
            IntrinsicMeasurable intrinsicMeasurable2 = measurables.get(i3);
            float weight2 = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(intrinsicMeasurable2));
            if (weight2 > 0.0f) {
                i = Math.max(i, intrinsicMeasurable2.maxIntrinsicHeight(round != Integer.MAX_VALUE ? Math.round(round * weight2) : Integer.MAX_VALUE));
            }
        }
        return i;
    }

    public final int VerticalMaxHeight(List<? extends IntrinsicMeasurable> measurables, int availableWidth, int mainAxisSpacing) {
        if (measurables.isEmpty()) {
            return 0;
        }
        int size = measurables.size();
        int i = 0;
        int i2 = 0;
        float f = 0.0f;
        for (int i3 = 0; i3 < size; i3++) {
            IntrinsicMeasurable intrinsicMeasurable = measurables.get(i3);
            float weight = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(intrinsicMeasurable));
            int maxIntrinsicHeight = intrinsicMeasurable.maxIntrinsicHeight(availableWidth);
            if (weight == 0.0f) {
                i2 += maxIntrinsicHeight;
            } else if (weight > 0.0f) {
                f += weight;
                i = Math.max(i, Math.round(maxIntrinsicHeight / weight));
            }
        }
        return Math.round(i * f) + i2 + ((measurables.size() - 1) * mainAxisSpacing);
    }
}
