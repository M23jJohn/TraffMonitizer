package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextField.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ8\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\n2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0002J<\u0010\u0011\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\n2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0002J\"\u0010\u0014\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\"\u0010\u0015\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016J,\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00190\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\"\u0010\u001e\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\"\u0010\u001f\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006 "}, d2 = {"Landroidx/compose/material3/TextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "singleLine", "", "animationProgress", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "intrinsicWidth", "", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "width", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class TextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    public TextFieldMeasurePolicy(boolean z, float f, PaddingValues paddingValues) {
        this.singleLine = z;
        this.animationProgress = f;
        this.paddingValues = paddingValues;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo80measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        Measurable measurable;
        Measurable measurable2;
        long j2;
        Placeable placeable;
        Measurable measurable3;
        Measurable measurable4;
        int i;
        final Placeable placeable2;
        Placeable placeable3;
        long j3;
        Placeable placeable4;
        Placeable placeable5;
        Measurable measurable5;
        Placeable placeable6;
        Measurable measurable6;
        Placeable placeable7;
        Measurable measurable7;
        int m2847calculateWidthyeHjK3Y;
        int m2846calculateHeightmKXJcVc;
        final TextFieldMeasurePolicy textFieldMeasurePolicy = this;
        final MeasureScope measureScope2 = measureScope;
        List<? extends Measurable> list2 = list;
        final int i2 = measureScope2.mo453roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getTop());
        int i3 = measureScope2.mo453roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getBottom());
        long m7332copyZbe2FdA$default = Constraints.m7332copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        int size = list2.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size) {
                measurable = null;
                break;
            }
            measurable = list2.get(i4);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), TextFieldImplKt.LeadingId)) {
                break;
            }
            i4++;
        }
        Measurable measurable8 = measurable;
        Placeable mo6055measureBRTryo0 = measurable8 != null ? measurable8.mo6055measureBRTryo0(m7332copyZbe2FdA$default) : null;
        int widthOrZero = TextFieldImplKt.widthOrZero(mo6055measureBRTryo0);
        int max = Math.max(0, TextFieldImplKt.heightOrZero(mo6055measureBRTryo0));
        int size2 = list2.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size2) {
                measurable2 = null;
                break;
            }
            measurable2 = list2.get(i5);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), TextFieldImplKt.TrailingId)) {
                break;
            }
            i5++;
        }
        Measurable measurable9 = measurable2;
        if (measurable9 != null) {
            j2 = m7332copyZbe2FdA$default;
            placeable = measurable9.mo6055measureBRTryo0(ConstraintsKt.m7362offsetNN6EwU$default(j2, -widthOrZero, 0, 2, null));
        } else {
            j2 = m7332copyZbe2FdA$default;
            placeable = null;
        }
        int widthOrZero2 = widthOrZero + TextFieldImplKt.widthOrZero(placeable);
        int max2 = Math.max(max, TextFieldImplKt.heightOrZero(placeable));
        int size3 = list2.size();
        int i6 = 0;
        while (true) {
            if (i6 >= size3) {
                measurable3 = null;
                break;
            }
            measurable3 = list2.get(i6);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), TextFieldImplKt.PrefixId)) {
                break;
            }
            i6++;
        }
        Measurable measurable10 = measurable3;
        final Placeable mo6055measureBRTryo02 = measurable10 != null ? measurable10.mo6055measureBRTryo0(ConstraintsKt.m7362offsetNN6EwU$default(j2, -widthOrZero2, 0, 2, null)) : null;
        int widthOrZero3 = widthOrZero2 + TextFieldImplKt.widthOrZero(mo6055measureBRTryo02);
        int max3 = Math.max(max2, TextFieldImplKt.heightOrZero(mo6055measureBRTryo02));
        int size4 = list2.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size4) {
                measurable4 = null;
                break;
            }
            measurable4 = list2.get(i7);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable4), TextFieldImplKt.SuffixId)) {
                break;
            }
            i7++;
        }
        Measurable measurable11 = measurable4;
        if (measurable11 != null) {
            placeable2 = placeable;
            i = widthOrZero3;
            placeable3 = mo6055measureBRTryo0;
            j3 = j2;
            placeable4 = measurable11.mo6055measureBRTryo0(ConstraintsKt.m7362offsetNN6EwU$default(j2, -widthOrZero3, 0, 2, null));
        } else {
            i = widthOrZero3;
            placeable2 = placeable;
            placeable3 = mo6055measureBRTryo0;
            j3 = j2;
            placeable4 = null;
        }
        int widthOrZero4 = TextFieldImplKt.widthOrZero(placeable4) + i;
        int max4 = Math.max(max3, TextFieldImplKt.heightOrZero(placeable4));
        int i8 = -widthOrZero4;
        long m7361offsetNN6EwU = ConstraintsKt.m7361offsetNN6EwU(j3, i8, -i3);
        int size5 = list2.size();
        int i9 = 0;
        while (true) {
            if (i9 >= size5) {
                placeable5 = placeable4;
                measurable5 = null;
                break;
            }
            measurable5 = list2.get(i9);
            int i10 = i9;
            placeable5 = placeable4;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable5), TextFieldImplKt.LabelId)) {
                break;
            }
            i9 = i10 + 1;
            placeable4 = placeable5;
        }
        Measurable measurable12 = measurable5;
        Placeable mo6055measureBRTryo03 = measurable12 != null ? measurable12.mo6055measureBRTryo0(m7361offsetNN6EwU) : null;
        int size6 = list2.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size6) {
                placeable6 = mo6055measureBRTryo03;
                measurable6 = null;
                break;
            }
            measurable6 = list2.get(i11);
            placeable6 = mo6055measureBRTryo03;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable6), TextFieldImplKt.SupportingId)) {
                break;
            }
            i11++;
            mo6055measureBRTryo03 = placeable6;
        }
        Measurable measurable13 = measurable6;
        int minIntrinsicHeight = measurable13 != null ? measurable13.minIntrinsicHeight(Constraints.m7344getMinWidthimpl(j)) : 0;
        int heightOrZero = TextFieldImplKt.heightOrZero(placeable6) + i2;
        long j4 = j3;
        long m7361offsetNN6EwU2 = ConstraintsKt.m7361offsetNN6EwU(Constraints.m7332copyZbe2FdA$default(j, 0, 0, 0, 0, 11, null), i8, ((-heightOrZero) - i3) - minIntrinsicHeight);
        int size7 = list2.size();
        int i12 = 0;
        while (i12 < size7) {
            Measurable measurable14 = list2.get(i12);
            int i13 = size7;
            int i14 = heightOrZero;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable14), TextFieldImplKt.TextFieldId)) {
                Placeable mo6055measureBRTryo04 = measurable14.mo6055measureBRTryo0(m7361offsetNN6EwU2);
                long m7332copyZbe2FdA$default2 = Constraints.m7332copyZbe2FdA$default(m7361offsetNN6EwU2, 0, 0, 0, 0, 14, null);
                int size8 = list2.size();
                int i15 = 0;
                while (true) {
                    if (i15 >= size8) {
                        placeable7 = mo6055measureBRTryo04;
                        measurable7 = null;
                        break;
                    }
                    measurable7 = list2.get(i15);
                    placeable7 = mo6055measureBRTryo04;
                    int i16 = size8;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable7), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    i15++;
                    size8 = i16;
                    mo6055measureBRTryo04 = placeable7;
                }
                Measurable measurable15 = measurable7;
                final Placeable mo6055measureBRTryo05 = measurable15 != null ? measurable15.mo6055measureBRTryo0(m7332copyZbe2FdA$default2) : null;
                int max5 = Math.max(max4, Math.max(TextFieldImplKt.heightOrZero(placeable7), TextFieldImplKt.heightOrZero(mo6055measureBRTryo05)) + i14 + i3);
                m2847calculateWidthyeHjK3Y = TextFieldKt.m2847calculateWidthyeHjK3Y(TextFieldImplKt.widthOrZero(placeable3), TextFieldImplKt.widthOrZero(placeable2), TextFieldImplKt.widthOrZero(mo6055measureBRTryo02), TextFieldImplKt.widthOrZero(placeable5), placeable7.getWidth(), TextFieldImplKt.widthOrZero(placeable6), TextFieldImplKt.widthOrZero(mo6055measureBRTryo05), j);
                int i17 = m2847calculateWidthyeHjK3Y;
                final Placeable mo6055measureBRTryo06 = measurable13 != null ? measurable13.mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(ConstraintsKt.m7362offsetNN6EwU$default(j4, 0, -max5, 1, null), 0, m2847calculateWidthyeHjK3Y, 0, 0, 9, null)) : null;
                int heightOrZero2 = TextFieldImplKt.heightOrZero(mo6055measureBRTryo06);
                m2846calculateHeightmKXJcVc = TextFieldKt.m2846calculateHeightmKXJcVc(placeable7.getHeight(), TextFieldImplKt.heightOrZero(placeable6), TextFieldImplKt.heightOrZero(placeable3), TextFieldImplKt.heightOrZero(placeable2), TextFieldImplKt.heightOrZero(mo6055measureBRTryo02), TextFieldImplKt.heightOrZero(placeable5), TextFieldImplKt.heightOrZero(mo6055measureBRTryo05), TextFieldImplKt.heightOrZero(mo6055measureBRTryo06), textFieldMeasurePolicy.animationProgress, j, measureScope2.getDensity(), textFieldMeasurePolicy.paddingValues);
                int i18 = m2846calculateHeightmKXJcVc - heightOrZero2;
                int size9 = list2.size();
                int i19 = 0;
                while (i19 < size9) {
                    Measurable measurable16 = list2.get(i19);
                    final int i20 = m2846calculateHeightmKXJcVc;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable16), TextFieldImplKt.ContainerId)) {
                        final Placeable mo6055measureBRTryo07 = measurable16.mo6055measureBRTryo0(ConstraintsKt.Constraints(i17 != Integer.MAX_VALUE ? i17 : 0, i17, i18 != Integer.MAX_VALUE ? i18 : 0, i18));
                        final int i21 = i17;
                        final Placeable placeable8 = placeable3;
                        final Placeable placeable9 = placeable5;
                        final Placeable placeable10 = placeable6;
                        final Placeable placeable11 = placeable7;
                        return MeasureScope.CC.layout$default(measureScope, i21, i20, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$measure$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope placementScope) {
                                boolean z;
                                PaddingValues paddingValues;
                                boolean z2;
                                float f;
                                Placeable placeable12 = Placeable.this;
                                if (placeable12 != null) {
                                    int i22 = i21;
                                    int i23 = i20;
                                    Placeable placeable13 = placeable11;
                                    Placeable placeable14 = mo6055measureBRTryo05;
                                    Placeable placeable15 = placeable8;
                                    Placeable placeable16 = placeable2;
                                    Placeable placeable17 = mo6055measureBRTryo02;
                                    Placeable placeable18 = placeable9;
                                    Placeable placeable19 = mo6055measureBRTryo07;
                                    Placeable placeable20 = mo6055measureBRTryo06;
                                    z2 = textFieldMeasurePolicy.singleLine;
                                    int i24 = i2;
                                    int height = i24 + Placeable.this.getHeight();
                                    f = textFieldMeasurePolicy.animationProgress;
                                    TextFieldKt.placeWithLabel(placementScope, i22, i23, placeable13, placeable12, placeable14, placeable15, placeable16, placeable17, placeable18, placeable19, placeable20, z2, i24, height, f, measureScope2.getDensity());
                                    return;
                                }
                                int i25 = i21;
                                int i26 = i20;
                                Placeable placeable21 = placeable11;
                                Placeable placeable22 = mo6055measureBRTryo05;
                                Placeable placeable23 = placeable8;
                                Placeable placeable24 = placeable2;
                                Placeable placeable25 = mo6055measureBRTryo02;
                                Placeable placeable26 = placeable9;
                                Placeable placeable27 = mo6055measureBRTryo07;
                                Placeable placeable28 = mo6055measureBRTryo06;
                                z = textFieldMeasurePolicy.singleLine;
                                float density = measureScope2.getDensity();
                                paddingValues = textFieldMeasurePolicy.paddingValues;
                                TextFieldKt.placeWithoutLabel(placementScope, i25, i26, placeable21, placeable22, placeable23, placeable24, placeable25, placeable26, placeable27, placeable28, z, density, paddingValues);
                            }
                        }, 4, null);
                    }
                    i19++;
                    i17 = i17;
                    m2846calculateHeightmKXJcVc = i20;
                    textFieldMeasurePolicy = this;
                    measureScope2 = measureScope;
                    list2 = list;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            i12++;
            textFieldMeasurePolicy = this;
            measureScope2 = measureScope;
            heightOrZero = i14;
            m7361offsetNN6EwU2 = m7361offsetNN6EwU2;
            size7 = i13;
            list2 = list;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$maxIntrinsicHeight$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.maxIntrinsicHeight(i2));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$minIntrinsicHeight$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.minIntrinsicHeight(i2));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicWidth(list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$maxIntrinsicWidth$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.maxIntrinsicWidth(i2));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        return intrinsicWidth(list, i, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$minIntrinsicWidth$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.minIntrinsicWidth(i2));
            }
        });
    }

    private final int intrinsicWidth(List<? extends IntrinsicMeasurable> measurables, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> intrinsicMeasurer) {
        IntrinsicMeasurable intrinsicMeasurable;
        IntrinsicMeasurable intrinsicMeasurable2;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        IntrinsicMeasurable intrinsicMeasurable5;
        IntrinsicMeasurable intrinsicMeasurable6;
        int m2847calculateWidthyeHjK3Y;
        int size = measurables.size();
        for (int i = 0; i < size; i++) {
            IntrinsicMeasurable intrinsicMeasurable7 = measurables.get(i);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable7), TextFieldImplKt.TextFieldId)) {
                int intValue = intrinsicMeasurer.invoke(intrinsicMeasurable7, Integer.valueOf(height)).intValue();
                int size2 = measurables.size();
                int i2 = 0;
                while (true) {
                    intrinsicMeasurable = null;
                    if (i2 >= size2) {
                        intrinsicMeasurable2 = null;
                        break;
                    }
                    intrinsicMeasurable2 = measurables.get(i2);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable2), TextFieldImplKt.LabelId)) {
                        break;
                    }
                    i2++;
                }
                IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable2;
                int intValue2 = intrinsicMeasurable8 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable8, Integer.valueOf(height)).intValue() : 0;
                int size3 = measurables.size();
                int i3 = 0;
                while (true) {
                    if (i3 >= size3) {
                        intrinsicMeasurable3 = null;
                        break;
                    }
                    intrinsicMeasurable3 = measurables.get(i3);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable3), TextFieldImplKt.TrailingId)) {
                        break;
                    }
                    i3++;
                }
                IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable3;
                int intValue3 = intrinsicMeasurable9 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable9, Integer.valueOf(height)).intValue() : 0;
                int size4 = measurables.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size4) {
                        intrinsicMeasurable4 = null;
                        break;
                    }
                    intrinsicMeasurable4 = measurables.get(i4);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.PrefixId)) {
                        break;
                    }
                    i4++;
                }
                IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable4;
                int intValue4 = intrinsicMeasurable10 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable10, Integer.valueOf(height)).intValue() : 0;
                int size5 = measurables.size();
                int i5 = 0;
                while (true) {
                    if (i5 >= size5) {
                        intrinsicMeasurable5 = null;
                        break;
                    }
                    intrinsicMeasurable5 = measurables.get(i5);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.SuffixId)) {
                        break;
                    }
                    i5++;
                }
                IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable5;
                int intValue5 = intrinsicMeasurable11 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable11, Integer.valueOf(height)).intValue() : 0;
                int size6 = measurables.size();
                int i6 = 0;
                while (true) {
                    if (i6 >= size6) {
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = measurables.get(i6);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable6), TextFieldImplKt.LeadingId)) {
                        break;
                    }
                    i6++;
                }
                IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable6;
                int intValue6 = intrinsicMeasurable12 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable12, Integer.valueOf(height)).intValue() : 0;
                int size7 = measurables.size();
                int i7 = 0;
                while (true) {
                    if (i7 >= size7) {
                        break;
                    }
                    IntrinsicMeasurable intrinsicMeasurable13 = measurables.get(i7);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable13), TextFieldImplKt.PlaceholderId)) {
                        intrinsicMeasurable = intrinsicMeasurable13;
                        break;
                    }
                    i7++;
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable;
                m2847calculateWidthyeHjK3Y = TextFieldKt.m2847calculateWidthyeHjK3Y(intValue6, intValue3, intValue4, intValue5, intValue, intValue2, intrinsicMeasurable14 != null ? intrinsicMeasurer.invoke(intrinsicMeasurable14, Integer.valueOf(height)).intValue() : 0, TextFieldImplKt.getZeroConstraints());
                return m2847calculateWidthyeHjK3Y;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        IntrinsicMeasurable intrinsicMeasurable;
        int i2;
        int i3;
        int i4;
        IntrinsicMeasurable intrinsicMeasurable2;
        int i5;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        int i6;
        IntrinsicMeasurable intrinsicMeasurable5;
        int i7;
        IntrinsicMeasurable intrinsicMeasurable6;
        IntrinsicMeasurable intrinsicMeasurable7;
        int m2846calculateHeightmKXJcVc;
        int size = list.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size) {
                intrinsicMeasurable = null;
                break;
            }
            intrinsicMeasurable = list.get(i8);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable), TextFieldImplKt.LeadingId)) {
                break;
            }
            i8++;
        }
        IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable;
        if (intrinsicMeasurable8 != null) {
            i2 = i;
            i3 = TextFieldKt.substractConstraintSafely(i2, intrinsicMeasurable8.maxIntrinsicWidth(Integer.MAX_VALUE));
            i4 = function2.invoke(intrinsicMeasurable8, Integer.valueOf(i2)).intValue();
        } else {
            i2 = i;
            i3 = i2;
            i4 = 0;
        }
        int size2 = list.size();
        int i9 = 0;
        while (true) {
            if (i9 >= size2) {
                intrinsicMeasurable2 = null;
                break;
            }
            intrinsicMeasurable2 = list.get(i9);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable2), TextFieldImplKt.TrailingId)) {
                break;
            }
            i9++;
        }
        IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable2;
        if (intrinsicMeasurable9 != null) {
            i3 = TextFieldKt.substractConstraintSafely(i3, intrinsicMeasurable9.maxIntrinsicWidth(Integer.MAX_VALUE));
            i5 = function2.invoke(intrinsicMeasurable9, Integer.valueOf(i2)).intValue();
        } else {
            i5 = 0;
        }
        int size3 = list.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size3) {
                intrinsicMeasurable3 = null;
                break;
            }
            intrinsicMeasurable3 = list.get(i10);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable3), TextFieldImplKt.LabelId)) {
                break;
            }
            i10++;
        }
        IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable3;
        int intValue = intrinsicMeasurable10 != null ? function2.invoke(intrinsicMeasurable10, Integer.valueOf(i3)).intValue() : 0;
        int size4 = list.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size4) {
                intrinsicMeasurable4 = null;
                break;
            }
            intrinsicMeasurable4 = list.get(i11);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.PrefixId)) {
                break;
            }
            i11++;
        }
        IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable4;
        if (intrinsicMeasurable11 != null) {
            i6 = function2.invoke(intrinsicMeasurable11, Integer.valueOf(i3)).intValue();
            i3 = TextFieldKt.substractConstraintSafely(i3, intrinsicMeasurable11.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            i6 = 0;
        }
        int size5 = list.size();
        int i12 = 0;
        while (true) {
            if (i12 >= size5) {
                intrinsicMeasurable5 = null;
                break;
            }
            intrinsicMeasurable5 = list.get(i12);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.SuffixId)) {
                break;
            }
            i12++;
        }
        IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable5;
        if (intrinsicMeasurable12 != null) {
            int intValue2 = function2.invoke(intrinsicMeasurable12, Integer.valueOf(i3)).intValue();
            i3 = TextFieldKt.substractConstraintSafely(i3, intrinsicMeasurable12.maxIntrinsicWidth(Integer.MAX_VALUE));
            i7 = intValue2;
        } else {
            i7 = 0;
        }
        int size6 = list.size();
        for (int i13 = 0; i13 < size6; i13++) {
            IntrinsicMeasurable intrinsicMeasurable13 = list.get(i13);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable13), TextFieldImplKt.TextFieldId)) {
                int intValue3 = function2.invoke(intrinsicMeasurable13, Integer.valueOf(i3)).intValue();
                int size7 = list.size();
                int i14 = 0;
                while (true) {
                    if (i14 >= size7) {
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = list.get(i14);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable6), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    i14++;
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable6;
                int intValue4 = intrinsicMeasurable14 != null ? function2.invoke(intrinsicMeasurable14, Integer.valueOf(i3)).intValue() : 0;
                int size8 = list.size();
                int i15 = 0;
                while (true) {
                    if (i15 >= size8) {
                        intrinsicMeasurable7 = null;
                        break;
                    }
                    intrinsicMeasurable7 = list.get(i15);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId(intrinsicMeasurable7), TextFieldImplKt.SupportingId)) {
                        break;
                    }
                    i15++;
                }
                IntrinsicMeasurable intrinsicMeasurable15 = intrinsicMeasurable7;
                m2846calculateHeightmKXJcVc = TextFieldKt.m2846calculateHeightmKXJcVc(intValue3, intValue, i4, i5, i6, i7, intValue4, intrinsicMeasurable15 != null ? function2.invoke(intrinsicMeasurable15, Integer.valueOf(i2)).intValue() : 0, this.animationProgress, TextFieldImplKt.getZeroConstraints(), intrinsicMeasureScope.getDensity(), this.paddingValues);
                return m2846calculateHeightmKXJcVc;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
