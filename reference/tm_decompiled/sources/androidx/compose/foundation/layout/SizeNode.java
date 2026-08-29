package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Size.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B7\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ#\u0010 \u001a\u00020!*\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001cH\u0016¢\u0006\u0004\b&\u0010'J\u001c\u0010(\u001a\u00020)*\u00020*2\u0006\u0010#\u001a\u00020+2\u0006\u0010,\u001a\u00020)H\u0016J\u001c\u0010-\u001a\u00020)*\u00020*2\u0006\u0010#\u001a\u00020+2\u0006\u0010.\u001a\u00020)H\u0016J\u001c\u0010/\u001a\u00020)*\u00020*2\u0006\u0010#\u001a\u00020+2\u0006\u0010,\u001a\u00020)H\u0016J\u001c\u00100\u001a\u00020)*\u00020*2\u0006\u0010#\u001a\u00020+2\u0006\u0010.\u001a\u00020)H\u0016R\u001c\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001c\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0013\u0010\r\"\u0004\b\u0014\u0010\u000fR\u001c\u0010\u0007\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0015\u0010\r\"\u0004\b\u0016\u0010\u000fR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001b\u001a\u00020\u001c*\u00020\u001d8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u00061"}, d2 = {"Landroidx/compose/foundation/layout/SizeNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "minWidth", "Landroidx/compose/ui/unit/Dp;", "minHeight", "maxWidth", "maxHeight", "enforceIncoming", "", "<init>", "(FFFFZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getMinWidth-D9Ej5fM", "()F", "setMinWidth-0680j_4", "(F)V", "F", "getMinHeight-D9Ej5fM", "setMinHeight-0680j_4", "getMaxWidth-D9Ej5fM", "setMaxWidth-0680j_4", "getMaxHeight-D9Ej5fM", "setMaxHeight-0680j_4", "getEnforceIncoming", "()Z", "setEnforceIncoming", "(Z)V", "targetConstraints", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/unit/Density;", "getTargetConstraints-OenEA2s", "(Landroidx/compose/ui/unit/Density;)J", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class SizeNode extends Modifier.Node implements LayoutModifierNode {
    private boolean enforceIncoming;
    private float maxHeight;
    private float maxWidth;
    private float minHeight;
    private float minWidth;

    public /* synthetic */ SizeNode(float f, float f2, float f3, float f4, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, z);
    }

    public /* synthetic */ SizeNode(float f, float f2, float f3, float f4, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Dp.INSTANCE.m7419getUnspecifiedD9Ej5fM() : f, (i & 2) != 0 ? Dp.INSTANCE.m7419getUnspecifiedD9Ej5fM() : f2, (i & 4) != 0 ? Dp.INSTANCE.m7419getUnspecifiedD9Ej5fM() : f3, (i & 8) != 0 ? Dp.INSTANCE.m7419getUnspecifiedD9Ej5fM() : f4, z, null);
    }

    /* renamed from: getMinWidth-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMinWidth() {
        return this.minWidth;
    }

    /* renamed from: setMinWidth-0680j_4, reason: not valid java name */
    public final void m947setMinWidth0680j_4(float f) {
        this.minWidth = f;
    }

    /* renamed from: getMinHeight-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMinHeight() {
        return this.minHeight;
    }

    /* renamed from: setMinHeight-0680j_4, reason: not valid java name */
    public final void m946setMinHeight0680j_4(float f) {
        this.minHeight = f;
    }

    /* renamed from: getMaxWidth-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMaxWidth() {
        return this.maxWidth;
    }

    /* renamed from: setMaxWidth-0680j_4, reason: not valid java name */
    public final void m945setMaxWidth0680j_4(float f) {
        this.maxWidth = f;
    }

    /* renamed from: getMaxHeight-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMaxHeight() {
        return this.maxHeight;
    }

    /* renamed from: setMaxHeight-0680j_4, reason: not valid java name */
    public final void m944setMaxHeight0680j_4(float f) {
        this.maxHeight = f;
    }

    public final boolean getEnforceIncoming() {
        return this.enforceIncoming;
    }

    public final void setEnforceIncoming(boolean z) {
        this.enforceIncoming = z;
    }

    private SizeNode(float f, float f2, float f3, float f4, boolean z) {
        this.minWidth = f;
        this.minHeight = f2;
        this.maxWidth = f3;
        this.maxHeight = f4;
        this.enforceIncoming = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
    
        if (r4 != Integer.MAX_VALUE) goto L24;
     */
    /* renamed from: getTargetConstraints-OenEA2s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final long m939getTargetConstraintsOenEA2s(Density density) {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        if (Float.isNaN(this.maxWidth)) {
            i = Integer.MAX_VALUE;
        } else {
            i = density.mo453roundToPx0680j_4(this.maxWidth);
            if (i < 0) {
                i = 0;
            }
        }
        if (Float.isNaN(this.maxHeight)) {
            i2 = Integer.MAX_VALUE;
        } else {
            i2 = density.mo453roundToPx0680j_4(this.maxHeight);
            if (i2 < 0) {
                i2 = 0;
            }
        }
        if (!Float.isNaN(this.minWidth)) {
            i3 = density.mo453roundToPx0680j_4(this.minWidth);
            if (i3 < 0) {
                i3 = 0;
            }
            if (i3 > i) {
                i3 = i;
            }
        }
        i3 = 0;
        if (!Float.isNaN(this.minHeight)) {
            int mo453roundToPx0680j_4 = density.mo453roundToPx0680j_4(this.minHeight);
            if (mo453roundToPx0680j_4 < 0) {
                mo453roundToPx0680j_4 = 0;
            }
            if (mo453roundToPx0680j_4 > i2) {
                mo453roundToPx0680j_4 = i2;
            }
            if (mo453roundToPx0680j_4 != Integer.MAX_VALUE) {
                i4 = mo453roundToPx0680j_4;
            }
        }
        return ConstraintsKt.Constraints(i3, i, i4, i2);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo108measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        int m7344getMinWidthimpl;
        int m7342getMaxWidthimpl;
        int m7343getMinHeightimpl;
        int m7341getMaxHeightimpl;
        long Constraints;
        long m939getTargetConstraintsOenEA2s = m939getTargetConstraintsOenEA2s(measureScope);
        if (this.enforceIncoming) {
            Constraints = ConstraintsKt.m7357constrainN9IONVI(j, m939getTargetConstraintsOenEA2s);
        } else {
            if (!Float.isNaN(this.minWidth)) {
                m7344getMinWidthimpl = Constraints.m7344getMinWidthimpl(m939getTargetConstraintsOenEA2s);
            } else {
                m7344getMinWidthimpl = Constraints.m7344getMinWidthimpl(j);
                int m7342getMaxWidthimpl2 = Constraints.m7342getMaxWidthimpl(m939getTargetConstraintsOenEA2s);
                if (m7344getMinWidthimpl > m7342getMaxWidthimpl2) {
                    m7344getMinWidthimpl = m7342getMaxWidthimpl2;
                }
            }
            if (!Float.isNaN(this.maxWidth)) {
                m7342getMaxWidthimpl = Constraints.m7342getMaxWidthimpl(m939getTargetConstraintsOenEA2s);
            } else {
                m7342getMaxWidthimpl = Constraints.m7342getMaxWidthimpl(j);
                int m7344getMinWidthimpl2 = Constraints.m7344getMinWidthimpl(m939getTargetConstraintsOenEA2s);
                if (m7342getMaxWidthimpl < m7344getMinWidthimpl2) {
                    m7342getMaxWidthimpl = m7344getMinWidthimpl2;
                }
            }
            if (!Float.isNaN(this.minHeight)) {
                m7343getMinHeightimpl = Constraints.m7343getMinHeightimpl(m939getTargetConstraintsOenEA2s);
            } else {
                m7343getMinHeightimpl = Constraints.m7343getMinHeightimpl(j);
                int m7341getMaxHeightimpl2 = Constraints.m7341getMaxHeightimpl(m939getTargetConstraintsOenEA2s);
                if (m7343getMinHeightimpl > m7341getMaxHeightimpl2) {
                    m7343getMinHeightimpl = m7341getMaxHeightimpl2;
                }
            }
            if (!Float.isNaN(this.maxHeight)) {
                m7341getMaxHeightimpl = Constraints.m7341getMaxHeightimpl(m939getTargetConstraintsOenEA2s);
            } else {
                m7341getMaxHeightimpl = Constraints.m7341getMaxHeightimpl(j);
                int m7343getMinHeightimpl2 = Constraints.m7343getMinHeightimpl(m939getTargetConstraintsOenEA2s);
                if (m7341getMaxHeightimpl < m7343getMinHeightimpl2) {
                    m7341getMaxHeightimpl = m7343getMinHeightimpl2;
                }
            }
            Constraints = ConstraintsKt.Constraints(m7344getMinWidthimpl, m7342getMaxWidthimpl, m7343getMinHeightimpl, m7341getMaxHeightimpl);
        }
        final Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(Constraints);
        return MeasureScope.CC.layout$default(measureScope, mo6055measureBRTryo0.getWidth(), mo6055measureBRTryo0.getHeight(), null, new Function1() { // from class: androidx.compose.foundation.layout.SizeNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit measure_3p2s80s$lambda$3;
                measure_3p2s80s$lambda$3 = SizeNode.measure_3p2s80s$lambda$3(Placeable.this, (Placeable.PlacementScope) obj);
                return measure_3p2s80s$lambda$3;
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measure_3p2s80s$lambda$3(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long m939getTargetConstraintsOenEA2s = m939getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m7340getHasFixedWidthimpl(m939getTargetConstraintsOenEA2s)) {
            return Constraints.m7342getMaxWidthimpl(m939getTargetConstraintsOenEA2s);
        }
        if (!this.enforceIncoming) {
            i = ConstraintsKt.m7358constrainHeightK40F9xA(m939getTargetConstraintsOenEA2s, i);
        }
        return ConstraintsKt.m7359constrainWidthK40F9xA(m939getTargetConstraintsOenEA2s, intrinsicMeasurable.minIntrinsicWidth(i));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long m939getTargetConstraintsOenEA2s = m939getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m7339getHasFixedHeightimpl(m939getTargetConstraintsOenEA2s)) {
            return Constraints.m7341getMaxHeightimpl(m939getTargetConstraintsOenEA2s);
        }
        if (!this.enforceIncoming) {
            i = ConstraintsKt.m7359constrainWidthK40F9xA(m939getTargetConstraintsOenEA2s, i);
        }
        return ConstraintsKt.m7358constrainHeightK40F9xA(m939getTargetConstraintsOenEA2s, intrinsicMeasurable.minIntrinsicHeight(i));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long m939getTargetConstraintsOenEA2s = m939getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m7340getHasFixedWidthimpl(m939getTargetConstraintsOenEA2s)) {
            return Constraints.m7342getMaxWidthimpl(m939getTargetConstraintsOenEA2s);
        }
        if (!this.enforceIncoming) {
            i = ConstraintsKt.m7358constrainHeightK40F9xA(m939getTargetConstraintsOenEA2s, i);
        }
        return ConstraintsKt.m7359constrainWidthK40F9xA(m939getTargetConstraintsOenEA2s, intrinsicMeasurable.maxIntrinsicWidth(i));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long m939getTargetConstraintsOenEA2s = m939getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m7339getHasFixedHeightimpl(m939getTargetConstraintsOenEA2s)) {
            return Constraints.m7341getMaxHeightimpl(m939getTargetConstraintsOenEA2s);
        }
        if (!this.enforceIncoming) {
            i = ConstraintsKt.m7359constrainWidthK40F9xA(m939getTargetConstraintsOenEA2s, i);
        }
        return ConstraintsKt.m7358constrainHeightK40F9xA(m939getTargetConstraintsOenEA2s, intrinsicMeasurable.maxIntrinsicHeight(i));
    }
}
