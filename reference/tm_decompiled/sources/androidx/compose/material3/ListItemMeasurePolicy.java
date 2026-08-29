package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;

/* compiled from: ListItem.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JV\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\t\u001a\u00020\u00042,\u0010\n\u001a(\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00040\u000b¢\u0006\u0002\b\u000eH\u0002JV\u0010\u000f\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\u0010\u001a\u00020\u00042,\u0010\n\u001a(\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00040\u000b¢\u0006\u0002\b\u000eH\u0002J(\u0010\u0011\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\t\u001a\u00020\u0004H\u0016J(\u0010\u0012\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J2\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00070\u00072\u0006\u0010\u0017\u001a\u00020\u0018H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ(\u0010\u001b\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\t\u001a\u00020\u0004H\u0016J(\u0010\u001c\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\u0010\u001a\u00020\u0004H\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Landroidx/compose/material3/ListItemMeasurePolicy;", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "()V", "calculateIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "intrinsicMeasure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "Lkotlin/ExtensionFunctionType;", "calculateIntrinsicWidth", "height", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class ListItemMeasurePolicy implements MultiContentMeasurePolicy {
    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo816measure3p2s80s(MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j) {
        int subtractConstraintSafely;
        boolean isSupportingMultilineHeuristic;
        float m2359verticalPaddingyh95HIg;
        long j2;
        Placeable placeable;
        long j3;
        Placeable placeable2;
        Placeable placeable3;
        Placeable placeable4;
        Placeable placeable5;
        float m2359verticalPaddingyh95HIg2;
        int m2358calculateWidthyeHjK3Y;
        int m2357calculateHeightN4Jib3Y;
        MeasureResult place;
        List<? extends Measurable> list2 = list.get(0);
        List<? extends Measurable> list3 = list.get(1);
        List<? extends Measurable> list4 = list.get(2);
        List<? extends Measurable> list5 = list.get(3);
        List<? extends Measurable> list6 = list.get(4);
        long m7332copyZbe2FdA$default = Constraints.m7332copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        float listItemStartPadding = ListItemKt.getListItemStartPadding();
        float listItemEndPadding = ListItemKt.getListItemEndPadding();
        int i = measureScope.mo453roundToPx0680j_4(Dp.m7399constructorimpl(listItemStartPadding + listItemEndPadding));
        Measurable measurable = (Measurable) CollectionsKt.firstOrNull((List) list5);
        int minIntrinsicWidth = measurable != null ? measurable.minIntrinsicWidth(Constraints.m7341getMaxHeightimpl(j)) : 0;
        Measurable measurable2 = (Measurable) CollectionsKt.firstOrNull((List) list6);
        subtractConstraintSafely = ListItemKt.subtractConstraintSafely(Constraints.m7342getMaxWidthimpl(m7332copyZbe2FdA$default), minIntrinsicWidth + (measurable2 != null ? measurable2.minIntrinsicWidth(Constraints.m7341getMaxHeightimpl(j)) : 0) + i);
        Measurable measurable3 = (Measurable) CollectionsKt.firstOrNull((List) list4);
        isSupportingMultilineHeuristic = ListItemKt.isSupportingMultilineHeuristic(measureScope, measurable3 != null ? measurable3.minIntrinsicHeight(subtractConstraintSafely) : 0);
        m2359verticalPaddingyh95HIg = ListItemKt.m2359verticalPaddingyh95HIg(ListItemType.INSTANCE.m2372invokeZLSjz4$material3_release(CollectionsKt.firstOrNull((List) list3) != null, CollectionsKt.firstOrNull((List) list4) != null, isSupportingMultilineHeuristic));
        float f = 2;
        long m7361offsetNN6EwU = ConstraintsKt.m7361offsetNN6EwU(m7332copyZbe2FdA$default, -i, -measureScope.mo453roundToPx0680j_4(Dp.m7399constructorimpl(m2359verticalPaddingyh95HIg * f)));
        Measurable measurable4 = (Measurable) CollectionsKt.firstOrNull((List) list5);
        Placeable mo6055measureBRTryo0 = measurable4 != null ? measurable4.mo6055measureBRTryo0(m7361offsetNN6EwU) : null;
        int widthOrZero = TextFieldImplKt.widthOrZero(mo6055measureBRTryo0);
        Measurable measurable5 = (Measurable) CollectionsKt.firstOrNull((List) list6);
        if (measurable5 != null) {
            j2 = m7361offsetNN6EwU;
            placeable = measurable5.mo6055measureBRTryo0(ConstraintsKt.m7362offsetNN6EwU$default(j2, -widthOrZero, 0, 2, null));
        } else {
            j2 = m7361offsetNN6EwU;
            placeable = null;
        }
        int widthOrZero2 = widthOrZero + TextFieldImplKt.widthOrZero(placeable);
        Measurable measurable6 = (Measurable) CollectionsKt.firstOrNull((List) list2);
        if (measurable6 != null) {
            j3 = j2;
            placeable2 = measurable6.mo6055measureBRTryo0(ConstraintsKt.m7362offsetNN6EwU$default(j2, -widthOrZero2, 0, 2, null));
        } else {
            j3 = j2;
            placeable2 = null;
        }
        int heightOrZero = TextFieldImplKt.heightOrZero(placeable2);
        Measurable measurable7 = (Measurable) CollectionsKt.firstOrNull((List) list4);
        if (measurable7 != null) {
            placeable3 = placeable;
            placeable4 = mo6055measureBRTryo0;
            placeable5 = measurable7.mo6055measureBRTryo0(ConstraintsKt.m7361offsetNN6EwU(j3, -widthOrZero2, -heightOrZero));
        } else {
            placeable3 = placeable;
            placeable4 = mo6055measureBRTryo0;
            placeable5 = null;
        }
        int heightOrZero2 = heightOrZero + TextFieldImplKt.heightOrZero(placeable5);
        boolean z = (placeable5 == null || placeable5.get(AlignmentLineKt.getFirstBaseline()) == placeable5.get(AlignmentLineKt.getLastBaseline())) ? false : true;
        Measurable measurable8 = (Measurable) CollectionsKt.firstOrNull((List) list3);
        Placeable mo6055measureBRTryo02 = measurable8 != null ? measurable8.mo6055measureBRTryo0(ConstraintsKt.m7361offsetNN6EwU(j3, -widthOrZero2, -heightOrZero2)) : null;
        int m2372invokeZLSjz4$material3_release = ListItemType.INSTANCE.m2372invokeZLSjz4$material3_release(mo6055measureBRTryo02 != null, placeable5 != null, z);
        m2359verticalPaddingyh95HIg2 = ListItemKt.m2359verticalPaddingyh95HIg(m2372invokeZLSjz4$material3_release);
        float m7399constructorimpl = Dp.m7399constructorimpl(f * m2359verticalPaddingyh95HIg2);
        MeasureScope measureScope2 = measureScope;
        m2358calculateWidthyeHjK3Y = ListItemKt.m2358calculateWidthyeHjK3Y(measureScope2, TextFieldImplKt.widthOrZero(placeable4), TextFieldImplKt.widthOrZero(placeable3), TextFieldImplKt.widthOrZero(placeable2), TextFieldImplKt.widthOrZero(mo6055measureBRTryo02), TextFieldImplKt.widthOrZero(placeable5), i, j);
        m2357calculateHeightN4Jib3Y = ListItemKt.m2357calculateHeightN4Jib3Y(measureScope2, TextFieldImplKt.heightOrZero(placeable4), TextFieldImplKt.heightOrZero(placeable3), TextFieldImplKt.heightOrZero(placeable2), TextFieldImplKt.heightOrZero(mo6055measureBRTryo02), TextFieldImplKt.heightOrZero(placeable5), m2372invokeZLSjz4$material3_release, measureScope.mo453roundToPx0680j_4(m7399constructorimpl), j);
        place = ListItemKt.place(measureScope, m2358calculateWidthyeHjK3Y, m2357calculateHeightN4Jib3Y, placeable4, placeable3, placeable2, mo6055measureBRTryo02, placeable5, ListItemType.m2364equalsimpl0(m2372invokeZLSjz4$material3_release, ListItemType.INSTANCE.m2370getThreeLineAlXitO8()), measureScope.mo453roundToPx0680j_4(listItemStartPadding), measureScope.mo453roundToPx0680j_4(listItemEndPadding), measureScope.mo453roundToPx0680j_4(m2359verticalPaddingyh95HIg2));
        return place;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i) {
        return calculateIntrinsicHeight(intrinsicMeasureScope, list, i, ListItemMeasurePolicy$maxIntrinsicHeight$1.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i) {
        return calculateIntrinsicWidth(intrinsicMeasureScope, list, i, ListItemMeasurePolicy$maxIntrinsicWidth$1.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i) {
        return calculateIntrinsicHeight(intrinsicMeasureScope, list, i, ListItemMeasurePolicy$minIntrinsicHeight$1.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i) {
        return calculateIntrinsicWidth(intrinsicMeasureScope, list, i, ListItemMeasurePolicy$minIntrinsicWidth$1.INSTANCE);
    }

    private final int calculateIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        int m2358calculateWidthyeHjK3Y;
        List<? extends IntrinsicMeasurable> list2 = list.get(0);
        List<? extends IntrinsicMeasurable> list3 = list.get(1);
        List<? extends IntrinsicMeasurable> list4 = list.get(2);
        List<? extends IntrinsicMeasurable> list5 = list.get(3);
        List<? extends IntrinsicMeasurable> list6 = list.get(4);
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list5);
        int intValue = intrinsicMeasurable != null ? function2.invoke(intrinsicMeasurable, Integer.valueOf(i)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list6);
        int intValue2 = intrinsicMeasurable2 != null ? function2.invoke(intrinsicMeasurable2, Integer.valueOf(i)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list2);
        int intValue3 = intrinsicMeasurable3 != null ? function2.invoke(intrinsicMeasurable3, Integer.valueOf(i)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list3);
        int intValue4 = intrinsicMeasurable4 != null ? function2.invoke(intrinsicMeasurable4, Integer.valueOf(i)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable5 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list4);
        m2358calculateWidthyeHjK3Y = ListItemKt.m2358calculateWidthyeHjK3Y(intrinsicMeasureScope, intValue, intValue2, intValue3, intValue4, intrinsicMeasurable5 != null ? function2.invoke(intrinsicMeasurable5, Integer.valueOf(i)).intValue() : 0, intrinsicMeasureScope.mo453roundToPx0680j_4(Dp.m7399constructorimpl(ListItemKt.getListItemStartPadding() + ListItemKt.getListItemEndPadding())), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
        return m2358calculateWidthyeHjK3Y;
    }

    private final int calculateIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        int subtractConstraintSafely;
        int i2;
        int i3;
        boolean isSupportingMultilineHeuristic;
        float m2359verticalPaddingyh95HIg;
        int m2357calculateHeightN4Jib3Y;
        List<? extends IntrinsicMeasurable> list2 = list.get(0);
        List<? extends IntrinsicMeasurable> list3 = list.get(1);
        List<? extends IntrinsicMeasurable> list4 = list.get(2);
        List<? extends IntrinsicMeasurable> list5 = list.get(3);
        List<? extends IntrinsicMeasurable> list6 = list.get(4);
        subtractConstraintSafely = ListItemKt.subtractConstraintSafely(i, intrinsicMeasureScope.mo453roundToPx0680j_4(Dp.m7399constructorimpl(ListItemKt.getListItemStartPadding() + ListItemKt.getListItemEndPadding())));
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list5);
        if (intrinsicMeasurable != null) {
            i2 = function2.invoke(intrinsicMeasurable, Integer.valueOf(subtractConstraintSafely)).intValue();
            subtractConstraintSafely = ListItemKt.subtractConstraintSafely(subtractConstraintSafely, intrinsicMeasurable.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            i2 = 0;
        }
        IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list6);
        if (intrinsicMeasurable2 != null) {
            i3 = function2.invoke(intrinsicMeasurable2, Integer.valueOf(subtractConstraintSafely)).intValue();
            subtractConstraintSafely = ListItemKt.subtractConstraintSafely(subtractConstraintSafely, intrinsicMeasurable2.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            i3 = 0;
        }
        IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list3);
        int intValue = intrinsicMeasurable3 != null ? function2.invoke(intrinsicMeasurable3, Integer.valueOf(subtractConstraintSafely)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list2);
        int intValue2 = intrinsicMeasurable4 != null ? function2.invoke(intrinsicMeasurable4, Integer.valueOf(subtractConstraintSafely)).intValue() : 0;
        IntrinsicMeasurable intrinsicMeasurable5 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list4);
        int intValue3 = intrinsicMeasurable5 != null ? function2.invoke(intrinsicMeasurable5, Integer.valueOf(subtractConstraintSafely)).intValue() : 0;
        isSupportingMultilineHeuristic = ListItemKt.isSupportingMultilineHeuristic(intrinsicMeasureScope, intValue3);
        int m2372invokeZLSjz4$material3_release = ListItemType.INSTANCE.m2372invokeZLSjz4$material3_release(intValue > 0, intValue3 > 0, isSupportingMultilineHeuristic);
        m2359verticalPaddingyh95HIg = ListItemKt.m2359verticalPaddingyh95HIg(m2372invokeZLSjz4$material3_release);
        int i4 = intValue2;
        int i5 = intValue;
        m2357calculateHeightN4Jib3Y = ListItemKt.m2357calculateHeightN4Jib3Y(intrinsicMeasureScope, i2, i3, i4, i5, intValue3, m2372invokeZLSjz4$material3_release, intrinsicMeasureScope.mo453roundToPx0680j_4(Dp.m7399constructorimpl(m2359verticalPaddingyh95HIg * 2)), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
        return m2357calculateHeightN4Jib3Y;
    }
}
