package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.RectRulers;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;

/* compiled from: RulerAlignment.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0005"}, d2 = {"fitInside", "Landroidx/compose/ui/Modifier;", "rulers", "Landroidx/compose/ui/layout/RectRulers;", "fitOutside", "foundation-layout"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RulerAlignmentKt {
    public static final Modifier fitInside(Modifier modifier, final RectRulers rectRulers) {
        return LayoutModifierKt.layout(modifier, new Function3() { // from class: androidx.compose.foundation.layout.RulerAlignmentKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                MeasureResult fitInside$lambda$2;
                fitInside$lambda$2 = RulerAlignmentKt.fitInside$lambda$2(RectRulers.this, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                return fitInside$lambda$2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult fitInside$lambda$2(final RectRulers rectRulers, MeasureScope measureScope, final Measurable measurable, Constraints constraints) {
        if (Constraints.m7338getHasBoundedWidthimpl(constraints.getValue()) && Constraints.m7337getHasBoundedHeightimpl(constraints.getValue())) {
            final int m7342getMaxWidthimpl = Constraints.m7342getMaxWidthimpl(constraints.getValue());
            final int m7341getMaxHeightimpl = Constraints.m7341getMaxHeightimpl(constraints.getValue());
            return MeasureScope.CC.layout$default(measureScope, m7342getMaxWidthimpl, m7341getMaxHeightimpl, null, new Function1() { // from class: androidx.compose.foundation.layout.RulerAlignmentKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit fitInside$lambda$2$lambda$0;
                    fitInside$lambda$2$lambda$0 = RulerAlignmentKt.fitInside$lambda$2$lambda$0(RectRulers.this, m7342getMaxWidthimpl, m7341getMaxHeightimpl, measurable, (Placeable.PlacementScope) obj);
                    return fitInside$lambda$2$lambda$0;
                }
            }, 4, null);
        }
        final Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(constraints.getValue());
        final int width = mo6055measureBRTryo0.getWidth();
        final int height = mo6055measureBRTryo0.getHeight();
        return MeasureScope.CC.layout$default(measureScope, width, height, null, new Function1() { // from class: androidx.compose.foundation.layout.RulerAlignmentKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit fitInside$lambda$2$lambda$1;
                fitInside$lambda$2$lambda$1 = RulerAlignmentKt.fitInside$lambda$2$lambda$1(RectRulers.this, width, height, mo6055measureBRTryo0, (Placeable.PlacementScope) obj);
                return fitInside$lambda$2$lambda$1;
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit fitInside$lambda$2$lambda$0(RectRulers rectRulers, int i, int i2, Measurable measurable, Placeable.PlacementScope placementScope) {
        int roundToInt = MathKt.roundToInt(placementScope.current(rectRulers.getLeft(), 0.0f));
        if (roundToInt < 0) {
            roundToInt = 0;
        }
        int i3 = roundToInt > i ? i : roundToInt;
        int roundToInt2 = MathKt.roundToInt(placementScope.current(rectRulers.getTop(), 0.0f));
        if (roundToInt2 < 0) {
            roundToInt2 = 0;
        }
        int i4 = roundToInt2 > i2 ? i2 : roundToInt2;
        int roundToInt3 = MathKt.roundToInt(placementScope.current(rectRulers.getRight(), i));
        if (roundToInt3 < 0) {
            roundToInt3 = 0;
        }
        if (roundToInt3 <= i) {
            i = roundToInt3;
        }
        int roundToInt4 = MathKt.roundToInt(placementScope.current(rectRulers.getBottom(), i2));
        int i5 = roundToInt4 >= 0 ? roundToInt4 : 0;
        if (i5 <= i2) {
            i2 = i5;
        }
        Placeable.PlacementScope.place$default(placementScope, measurable.mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(i - i3, i2 - i4)), i3, i4, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit fitInside$lambda$2$lambda$1(RectRulers rectRulers, int i, int i2, Placeable placeable, Placeable.PlacementScope placementScope) {
        int roundToInt = MathKt.roundToInt(placementScope.current(rectRulers.getLeft(), 0.0f));
        if (roundToInt < 0) {
            roundToInt = 0;
        }
        if (roundToInt > i) {
            roundToInt = i;
        }
        int roundToInt2 = MathKt.roundToInt(placementScope.current(rectRulers.getTop(), 0.0f));
        if (roundToInt2 < 0) {
            roundToInt2 = 0;
        }
        if (roundToInt2 > i2) {
            roundToInt2 = i2;
        }
        int roundToInt3 = MathKt.roundToInt(placementScope.current(rectRulers.getRight(), i));
        if (roundToInt3 < 0) {
            roundToInt3 = 0;
        }
        if (roundToInt3 > i) {
            roundToInt3 = i;
        }
        int roundToInt4 = MathKt.roundToInt(placementScope.current(rectRulers.getBottom(), i2));
        int i3 = roundToInt4 >= 0 ? roundToInt4 : 0;
        if (i3 > i2) {
            i3 = i2;
        }
        Placeable.PlacementScope.place$default(placementScope, placeable, ((roundToInt + roundToInt3) - i) / 2, ((roundToInt2 + i3) - i2) / 2, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static final Modifier fitOutside(Modifier modifier, final RectRulers rectRulers) {
        return LayoutModifierKt.layout(modifier, new Function3() { // from class: androidx.compose.foundation.layout.RulerAlignmentKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                MeasureResult fitOutside$lambda$5;
                fitOutside$lambda$5 = RulerAlignmentKt.fitOutside$lambda$5(RectRulers.this, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                return fitOutside$lambda$5;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult fitOutside$lambda$5(final RectRulers rectRulers, MeasureScope measureScope, final Measurable measurable, Constraints constraints) {
        if (Constraints.m7338getHasBoundedWidthimpl(constraints.getValue()) && Constraints.m7337getHasBoundedHeightimpl(constraints.getValue())) {
            final int m7342getMaxWidthimpl = Constraints.m7342getMaxWidthimpl(constraints.getValue());
            final int m7341getMaxHeightimpl = Constraints.m7341getMaxHeightimpl(constraints.getValue());
            return MeasureScope.CC.layout$default(measureScope, m7342getMaxWidthimpl, m7341getMaxHeightimpl, null, new Function1() { // from class: androidx.compose.foundation.layout.RulerAlignmentKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit fitOutside$lambda$5$lambda$3;
                    fitOutside$lambda$5$lambda$3 = RulerAlignmentKt.fitOutside$lambda$5$lambda$3(RectRulers.this, m7342getMaxWidthimpl, m7341getMaxHeightimpl, measurable, (Placeable.PlacementScope) obj);
                    return fitOutside$lambda$5$lambda$3;
                }
            }, 4, null);
        }
        return MeasureScope.CC.layout$default(measureScope, 0, 0, null, new Function1() { // from class: androidx.compose.foundation.layout.RulerAlignmentKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit fitOutside$lambda$5$lambda$4;
                fitOutside$lambda$5$lambda$4 = RulerAlignmentKt.fitOutside$lambda$5$lambda$4(Measurable.this, (Placeable.PlacementScope) obj);
                return fitOutside$lambda$5$lambda$4;
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit fitOutside$lambda$5$lambda$3(RectRulers rectRulers, int i, int i2, Measurable measurable, Placeable.PlacementScope placementScope) {
        int i3;
        int i4;
        int roundToInt = MathKt.roundToInt(placementScope.current(rectRulers.getLeft(), 0.0f));
        int roundToInt2 = MathKt.roundToInt(placementScope.current(rectRulers.getTop(), 0.0f));
        int roundToInt3 = MathKt.roundToInt(placementScope.current(rectRulers.getRight(), i));
        int roundToInt4 = MathKt.roundToInt(placementScope.current(rectRulers.getBottom(), i2));
        if (roundToInt > 0) {
            i = roundToInt;
        } else {
            if (roundToInt2 <= 0) {
                if (roundToInt3 < i) {
                    i -= roundToInt3;
                    i3 = roundToInt3;
                    i4 = 0;
                } else if (roundToInt4 < i2) {
                    i2 -= roundToInt4;
                    i4 = roundToInt4;
                    i3 = 0;
                } else {
                    i = 0;
                    i2 = 0;
                    i3 = 0;
                    i4 = i3;
                }
                Placeable.PlacementScope.place$default(placementScope, measurable.mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(i, i2)), i3, i4, 0.0f, 4, null);
                return Unit.INSTANCE;
            }
            i2 = roundToInt2;
        }
        i3 = 0;
        i4 = i3;
        Placeable.PlacementScope.place$default(placementScope, measurable.mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(i, i2)), i3, i4, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit fitOutside$lambda$5$lambda$4(Measurable measurable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, measurable.mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(0, 0)), 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
