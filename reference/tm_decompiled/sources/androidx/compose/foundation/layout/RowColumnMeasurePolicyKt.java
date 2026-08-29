package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.RowColumnMeasurePolicy;
import androidx.compose.foundation.layout.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: RowColumnMeasurePolicy.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\u001a\u0085\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0004H\u0000¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;", "mainAxisMin", "", "crossAxisMin", "mainAxisMax", "crossAxisMax", "arrangementSpacingInt", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "startIndex", "endIndex", "crossAxisOffset", "", "currentLineIndex", "(Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;IIIIILandroidx/compose/ui/layout/MeasureScope;Ljava/util/List;[Landroidx/compose/ui/layout/Placeable;II[II)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RowColumnMeasurePolicyKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x019d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final MeasureResult measure(RowColumnMeasurePolicy rowColumnMeasurePolicy, int i, int i2, int i3, int i4, int i5, MeasureScope measureScope, List<? extends Measurable> list, Placeable[] placeableArr, int i6, int i7, int[] iArr, int i8) {
        int i9;
        int i10;
        int i11;
        int i12;
        FlowLayoutData flowLayoutData;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        FlowLayoutData flowLayoutData2;
        int[] iArr2;
        int i18;
        long j;
        RowColumnMeasurePolicy rowColumnMeasurePolicy2;
        int i19;
        long j2 = i5;
        int i20 = i7 - i6;
        int[] iArr3 = new int[i20];
        int i21 = i6;
        float f = 0.0f;
        int i22 = 0;
        int i23 = 0;
        boolean z = false;
        int i24 = 0;
        int i25 = 0;
        while (true) {
            boolean z2 = true;
            if (i21 >= i7) {
                break;
            }
            Measurable measurable = list.get(i21);
            RowColumnParentData rowColumnParentData = RowColumnImplKt.getRowColumnParentData(measurable);
            float weight = RowColumnImplKt.getWeight(rowColumnParentData);
            if (!z && !RowColumnImplKt.isRelative(rowColumnParentData)) {
                z2 = false;
            }
            if (weight > 0.0f) {
                f += weight;
                i24++;
                iArr2 = iArr3;
                i18 = i21;
                j = j2;
            } else {
                Integer valueOf = (i4 == Integer.MAX_VALUE || rowColumnParentData == null || (flowLayoutData2 = rowColumnParentData.getFlowLayoutData()) == null) ? null : Integer.valueOf(Math.round(flowLayoutData2.getFillCrossAxisFraction() * i4));
                int i26 = i3 - i25;
                Placeable placeable = placeableArr[i21];
                if (placeable == null) {
                    i18 = i21;
                    j = j2;
                    i19 = i23;
                    iArr2 = iArr3;
                    rowColumnMeasurePolicy2 = rowColumnMeasurePolicy;
                    placeable = measurable.mo6055measureBRTryo0(RowColumnMeasurePolicy.CC.m908createConstraintsxF2OJ5Q$default(rowColumnMeasurePolicy2, 0, valueOf != null ? valueOf.intValue() : 0, i3 != Integer.MAX_VALUE ? i26 < 0 ? 0 : i26 : Integer.MAX_VALUE, valueOf != null ? valueOf.intValue() : i4, false, 16, null));
                } else {
                    iArr2 = iArr3;
                    i18 = i21;
                    j = j2;
                    rowColumnMeasurePolicy2 = rowColumnMeasurePolicy;
                    i19 = i23;
                }
                int mainAxisSize = rowColumnMeasurePolicy2.mainAxisSize(placeable);
                int crossAxisSize = rowColumnMeasurePolicy2.crossAxisSize(placeable);
                iArr2[i18 - i6] = mainAxisSize;
                int i27 = i26 - mainAxisSize;
                if (i27 < 0) {
                    i27 = 0;
                }
                int min = Math.min(i5, i27);
                i25 += mainAxisSize + min;
                int max = Math.max(i19, crossAxisSize);
                placeableArr[i18] = placeable;
                i22 = min;
                i23 = max;
            }
            i21 = i18 + 1;
            z = z2;
            iArr3 = iArr2;
            j2 = j;
        }
        int[] iArr4 = iArr3;
        long j3 = j2;
        RowColumnMeasurePolicy rowColumnMeasurePolicy3 = rowColumnMeasurePolicy;
        int i28 = i23;
        if (i24 == 0) {
            i25 -= i22;
            i11 = 0;
            i10 = 0;
        } else {
            long j4 = j3 * (i24 - 1);
            long j5 = ((i3 != Integer.MAX_VALUE ? i3 : i) - i25) - j4;
            if (j5 < 0) {
                j5 = 0;
            }
            float f2 = j5 / f;
            for (int i29 = i6; i29 < i7; i29++) {
                j5 -= Math.round(RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(list.get(i29))) * f2);
            }
            int i30 = i6;
            int i31 = 0;
            while (i30 < i7) {
                if (placeableArr[i30] == null) {
                    Measurable measurable2 = list.get(i30);
                    RowColumnParentData rowColumnParentData2 = RowColumnImplKt.getRowColumnParentData(measurable2);
                    float weight2 = RowColumnImplKt.getWeight(rowColumnParentData2);
                    Integer valueOf2 = (i4 == i9 || rowColumnParentData2 == null || (flowLayoutData = rowColumnParentData2.getFlowLayoutData()) == null) ? null : Integer.valueOf(Math.round(flowLayoutData.getFillCrossAxisFraction() * i4));
                    if (!(weight2 > 0.0f)) {
                        InlineClassHelperKt.throwIllegalStateException("All weights <= 0 should have placeables");
                    }
                    int sign = MathKt.getSign(j5);
                    long j6 = j5 - sign;
                    int i32 = 0;
                    int max2 = Math.max(0, Math.round(weight2 * f2) + sign);
                    if (RowColumnImplKt.getFill(rowColumnParentData2)) {
                        i13 = Integer.MAX_VALUE;
                        if (max2 != Integer.MAX_VALUE) {
                            i14 = 0;
                            i32 = max2;
                            if (valueOf2 == null) {
                                i12 = i13;
                                i15 = valueOf2.intValue();
                            } else {
                                i12 = i13;
                                i15 = i14;
                            }
                            rowColumnMeasurePolicy3 = rowColumnMeasurePolicy;
                            Placeable mo6055measureBRTryo0 = measurable2.mo6055measureBRTryo0(rowColumnMeasurePolicy3.mo771createConstraintsxF2OJ5Q(i32, i15, max2, valueOf2 == null ? valueOf2.intValue() : i4, true));
                            int mainAxisSize2 = rowColumnMeasurePolicy3.mainAxisSize(mo6055measureBRTryo0);
                            int crossAxisSize2 = rowColumnMeasurePolicy3.crossAxisSize(mo6055measureBRTryo0);
                            iArr4[i30 - i6] = mainAxisSize2;
                            i31 += mainAxisSize2;
                            int max3 = Math.max(i28, crossAxisSize2);
                            placeableArr[i30] = mo6055measureBRTryo0;
                            i28 = max3;
                            j5 = j6;
                        }
                    } else {
                        i13 = Integer.MAX_VALUE;
                    }
                    i14 = 0;
                    if (valueOf2 == null) {
                    }
                    rowColumnMeasurePolicy3 = rowColumnMeasurePolicy;
                    Placeable mo6055measureBRTryo02 = measurable2.mo6055measureBRTryo0(rowColumnMeasurePolicy3.mo771createConstraintsxF2OJ5Q(i32, i15, max2, valueOf2 == null ? valueOf2.intValue() : i4, true));
                    int mainAxisSize22 = rowColumnMeasurePolicy3.mainAxisSize(mo6055measureBRTryo02);
                    int crossAxisSize22 = rowColumnMeasurePolicy3.crossAxisSize(mo6055measureBRTryo02);
                    iArr4[i30 - i6] = mainAxisSize22;
                    i31 += mainAxisSize22;
                    int max32 = Math.max(i28, crossAxisSize22);
                    placeableArr[i30] = mo6055measureBRTryo02;
                    i28 = max32;
                    j5 = j6;
                } else {
                    i12 = i9;
                }
                i30++;
                i9 = i12;
            }
            i10 = 0;
            i11 = (int) (i31 + j4);
            int i33 = i3 - i25;
            if (i11 < 0) {
                i11 = 0;
            }
            if (i11 > i33) {
                i11 = i33;
            }
        }
        int i34 = i28;
        if (z) {
            int i35 = i10;
            int i36 = i35;
            for (int i37 = i6; i37 < i7; i37++) {
                Placeable placeable2 = placeableArr[i37];
                Intrinsics.checkNotNull(placeable2);
                CrossAxisAlignment crossAxisAlignment = RowColumnImplKt.getCrossAxisAlignment(RowColumnImplKt.getRowColumnParentData(placeable2));
                Integer calculateAlignmentLinePosition$foundation_layout = crossAxisAlignment != null ? crossAxisAlignment.calculateAlignmentLinePosition$foundation_layout(placeable2) : null;
                if (calculateAlignmentLinePosition$foundation_layout != null) {
                    int intValue = calculateAlignmentLinePosition$foundation_layout.intValue();
                    int crossAxisSize3 = rowColumnMeasurePolicy3.crossAxisSize(placeable2);
                    i35 = Math.max(i35, intValue != Integer.MIN_VALUE ? calculateAlignmentLinePosition$foundation_layout.intValue() : i10);
                    if (intValue == Integer.MIN_VALUE) {
                        intValue = crossAxisSize3;
                    }
                    i36 = Math.max(i36, crossAxisSize3 - intValue);
                }
            }
            int i38 = i36;
            i17 = i35;
            i16 = i38;
        } else {
            i16 = i10;
            i17 = i16;
        }
        int i39 = i25 + i11;
        if (i39 < 0) {
            i39 = i10;
        }
        int max4 = Math.max(i39, i);
        int max5 = Math.max(i34, Math.max(i2, i16 + i17));
        int[] iArr5 = new int[i20];
        rowColumnMeasurePolicy3.populateMainAxisPositions(max4, iArr4, iArr5, measureScope);
        return rowColumnMeasurePolicy3.placeHelper(placeableArr, measureScope, i17, iArr5, max4, max5, iArr, i8, i6, i7);
    }
}
