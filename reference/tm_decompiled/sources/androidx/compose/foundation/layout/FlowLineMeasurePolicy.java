package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.FlowLineMeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlowLayout.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0016J\f\u0010\u0014\u001a\u00020\u0012*\u00020\u0013H\u0016J7\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJg\u0010\u001e\u001a\u00020\u001f2\u000e\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u00122\b\u0010)\u001a\u0004\u0018\u00010&2\u0006\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010-J(\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u00122\u0006\u00100\u001a\u0002012\u0006\u0010$\u001a\u00020\u0012H\u0016J(\u00102\u001a\u0002032\u0006\u0010'\u001a\u00020\u00122\u0006\u00104\u001a\u00020&2\u0006\u0010%\u001a\u00020&2\u0006\u0010\"\u001a\u00020#H\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00065À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;", "Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;", "isHorizontal", "", "()Z", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "getHorizontalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "getVerticalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "crossAxisAlignment", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "getCrossAxisAlignment", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "mainAxisSize", "", "Landroidx/compose/ui/layout/Placeable;", "crossAxisSize", "createConstraints", "Landroidx/compose/ui/unit/Constraints;", "mainAxisMin", "crossAxisMin", "mainAxisMax", "crossAxisMax", "isPrioritizing", "createConstraints-xF2OJ5Q", "(IIIIZ)J", "placeHelper", "Landroidx/compose/ui/layout/MeasureResult;", "placeables", "", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "beforeCrossAxisAlignmentLine", "mainAxisPositions", "", "mainAxisLayoutSize", "crossAxisLayoutSize", "crossAxisOffset", "currentLineIndex", "startIndex", "endIndex", "([Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/MeasureScope;I[III[IIII)Landroidx/compose/ui/layout/MeasureResult;", "getCrossAxisPosition", "placeable", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "populateMainAxisPositions", "", "childrenMainAxisSize", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public interface FlowLineMeasurePolicy extends RowColumnMeasurePolicy {
    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    /* renamed from: createConstraints-xF2OJ5Q */
    long mo771createConstraintsxF2OJ5Q(int mainAxisMin, int crossAxisMin, int mainAxisMax, int crossAxisMax, boolean isPrioritizing);

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    int crossAxisSize(Placeable placeable);

    CrossAxisAlignment getCrossAxisAlignment();

    int getCrossAxisPosition(Placeable placeable, int crossAxisLayoutSize, LayoutDirection layoutDirection, int beforeCrossAxisAlignmentLine);

    Arrangement.Horizontal getHorizontalArrangement();

    Arrangement.Vertical getVerticalArrangement();

    boolean isHorizontal();

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    int mainAxisSize(Placeable placeable);

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    MeasureResult placeHelper(Placeable[] placeables, MeasureScope measureScope, int beforeCrossAxisAlignmentLine, int[] mainAxisPositions, int mainAxisLayoutSize, int crossAxisLayoutSize, int[] crossAxisOffset, int currentLineIndex, int startIndex, int endIndex);

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    void populateMainAxisPositions(int mainAxisLayoutSize, int[] childrenMainAxisSize, int[] mainAxisPositions, MeasureScope measureScope);

    /* compiled from: FlowLayout.kt */
    /* renamed from: androidx.compose.foundation.layout.FlowLineMeasurePolicy$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static int $default$mainAxisSize(FlowLineMeasurePolicy _this, Placeable placeable) {
            return _this.isHorizontal() ? placeable.getMeasuredWidth() : placeable.getMeasuredHeight();
        }

        public static int $default$crossAxisSize(FlowLineMeasurePolicy _this, Placeable placeable) {
            return _this.isHorizontal() ? placeable.getMeasuredHeight() : placeable.getMeasuredWidth();
        }

        /* renamed from: $default$createConstraints-xF2OJ5Q, reason: not valid java name */
        public static long m806$default$createConstraintsxF2OJ5Q(FlowLineMeasurePolicy _this, int i, int i2, int i3, int i4, boolean z) {
            if (_this.isHorizontal()) {
                return RowKt.createRowConstraints(z, i, i2, i3, i4);
            }
            return ColumnKt.createColumnConstraints(z, i, i2, i3, i4);
        }

        public static MeasureResult $default$placeHelper(final FlowLineMeasurePolicy _this, final Placeable[] placeableArr, MeasureScope measureScope, final int i, final int[] iArr, int i2, final int i3, final int[] iArr2, final int i4, final int i5, final int i6) {
            int i7;
            int i8;
            LayoutDirection layoutDirection;
            if (_this.isHorizontal()) {
                i8 = i2;
                i7 = i3;
            } else {
                i7 = i2;
                i8 = i3;
            }
            if (_this.isHorizontal()) {
                layoutDirection = LayoutDirection.Ltr;
            } else {
                layoutDirection = measureScope.getLayoutDirection();
            }
            final LayoutDirection layoutDirection2 = layoutDirection;
            return MeasureScope.CC.layout$default(measureScope, i8, i7, null, new Function1() { // from class: androidx.compose.foundation.layout.FlowLineMeasurePolicy$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return FlowLineMeasurePolicy.CC.placeHelper$lambda$1$lambda$0(iArr2, i4, i5, i6, placeableArr, _this, i3, layoutDirection2, i, iArr, (Placeable.PlacementScope) obj);
                }
            }, 4, null);
        }

        public static Unit placeHelper$lambda$1$lambda$0(int[] iArr, int i, int i2, int i3, Placeable[] placeableArr, FlowLineMeasurePolicy flowLineMeasurePolicy, int i4, LayoutDirection layoutDirection, int i5, int[] iArr2, Placeable.PlacementScope placementScope) {
            int i6 = iArr != null ? iArr[i] : 0;
            for (int i7 = i2; i7 < i3; i7++) {
                Placeable placeable = placeableArr[i7];
                Intrinsics.checkNotNull(placeable);
                int crossAxisPosition = flowLineMeasurePolicy.getCrossAxisPosition(placeable, i4, layoutDirection, i5) + i6;
                if (flowLineMeasurePolicy.isHorizontal()) {
                    Placeable.PlacementScope.place$default(placementScope, placeable, iArr2[i7 - i2], crossAxisPosition, 0.0f, 4, null);
                } else {
                    Placeable.PlacementScope.place$default(placementScope, placeable, crossAxisPosition, iArr2[i7 - i2], 0.0f, 4, null);
                }
            }
            return Unit.INSTANCE;
        }

        public static int $default$getCrossAxisPosition(FlowLineMeasurePolicy _this, Placeable placeable, int i, LayoutDirection layoutDirection, int i2) {
            CrossAxisAlignment crossAxisAlignment;
            RowColumnParentData rowColumnParentData = RowColumnImplKt.getRowColumnParentData(placeable);
            if (rowColumnParentData == null || (crossAxisAlignment = rowColumnParentData.getCrossAxisAlignment()) == null) {
                crossAxisAlignment = _this.getCrossAxisAlignment();
            }
            return crossAxisAlignment.align$foundation_layout(i - _this.crossAxisSize(placeable), layoutDirection, placeable, i2);
        }

        public static void $default$populateMainAxisPositions(FlowLineMeasurePolicy _this, int i, int[] iArr, int[] iArr2, MeasureScope measureScope) {
            if (_this.isHorizontal()) {
                _this.getHorizontalArrangement().arrange(measureScope, i, iArr, measureScope.getLayoutDirection(), iArr2);
            } else {
                _this.getVerticalArrangement().arrange(measureScope, i, iArr, iArr2);
            }
        }
    }
}
