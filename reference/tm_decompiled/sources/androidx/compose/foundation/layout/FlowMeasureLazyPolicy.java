package androidx.compose.foundation.layout;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.FlowLayoutOverflow;
import androidx.compose.foundation.layout.FlowLineMeasurePolicy;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ContextualFlowLayout.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u00ad\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0017\u0010\u0013\u001a\u0013\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0002\b\u00170\u0014\u0012;\u0010\u0018\u001a7\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00160\u0019¢\u0006\u0002\b\u0017¢\u0006\u0004\b\u001f\u0010 J\u0018\u0010*\u001a\u0014\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0\u0019J\u001b\u0010.\u001a\u00020-*\u00020+2\u0006\u0010/\u001a\u00020,H\u0002¢\u0006\u0004\b0\u00101J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0005HÆ\u0003J\t\u00104\u001a\u00020\u0007HÆ\u0003J\u0010\u00105\u001a\u00020\tHÂ\u0003¢\u0006\u0004\b6\u00107J\t\u00108\u001a\u00020\u000bHÆ\u0003J\u0010\u00109\u001a\u00020\tHÂ\u0003¢\u0006\u0004\b:\u00107J\t\u0010;\u001a\u00020\u000eHÂ\u0003J\t\u0010<\u001a\u00020\u000eHÂ\u0003J\t\u0010=\u001a\u00020\u000eHÂ\u0003J\t\u0010>\u001a\u00020\u0012HÂ\u0003J\u001a\u0010?\u001a\u0013\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0002\b\u00170\u0014HÂ\u0003JC\u0010@\u001a7\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00160\u0019¢\u0006\u0002\b\u0017HÂ\u0003¢\u0006\u0002\u0010AJÎ\u0001\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0019\b\u0002\u0010\u0013\u001a\u0013\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0002\b\u00170\u00142=\b\u0002\u0010\u0018\u001a7\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00160\u0019¢\u0006\u0002\b\u0017HÆ\u0001¢\u0006\u0004\bC\u0010DJ\u0013\u0010E\u001a\u00020\u00032\b\u0010F\u001a\u0004\u0018\u00010GHÖ\u0003J\t\u0010H\u001a\u00020\u000eHÖ\u0001J\t\u0010I\u001a\u00020JHÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010!R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0010\u0010\f\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0013\u001a\u0013\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0002\b\u00170\u0014X\u0082\u0004¢\u0006\u0002\n\u0000RE\u0010\u0018\u001a7\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00160\u0019¢\u0006\u0002\b\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010)¨\u0006K"}, d2 = {"Landroidx/compose/foundation/layout/FlowMeasureLazyPolicy;", "Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;", "isHorizontal", "", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "mainAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisAlignment", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "crossAxisArrangementSpacing", "itemCount", "", "maxLines", "maxItemsInMainAxis", "overflow", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "overflowComposables", "", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "getComposable", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "index", "Landroidx/compose/foundation/layout/FlowLineInfo;", "info", "<init>", "(ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/layout/CrossAxisAlignment;FIIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;Ljava/util/List;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "()Z", "getHorizontalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "getVerticalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "F", "getCrossAxisAlignment", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "Lkotlin/jvm/functions/Function4;", "getMeasurePolicy", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "measure", "constraints", "measure-0kLqBqw", "(Landroidx/compose/ui/layout/SubcomposeMeasureScope;J)Landroidx/compose/ui/layout/MeasureResult;", "component1", "component2", "component3", "component4", "component4-D9Ej5fM", "()F", "component5", "component6", "component6-D9Ej5fM", "component7", "component8", "component9", "component10", "component11", "component12", "()Lkotlin/jvm/functions/Function4;", "copy", "copy-E4Q9ldg", "(ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/layout/CrossAxisAlignment;FIIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;Ljava/util/List;Lkotlin/jvm/functions/Function4;)Landroidx/compose/foundation/layout/FlowMeasureLazyPolicy;", "equals", "other", "", "hashCode", "toString", "", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final /* data */ class FlowMeasureLazyPolicy implements FlowLineMeasurePolicy {
    private final CrossAxisAlignment crossAxisAlignment;
    private final float crossAxisArrangementSpacing;
    private final Function4<Integer, FlowLineInfo, Composer, Integer, Unit> getComposable;
    private final Arrangement.Horizontal horizontalArrangement;
    private final boolean isHorizontal;
    private final int itemCount;
    private final float mainAxisSpacing;
    private final int maxItemsInMainAxis;
    private final int maxLines;
    private final FlowLayoutOverflowState overflow;
    private final List<Function2<Composer, Integer, Unit>> overflowComposables;
    private final Arrangement.Vertical verticalArrangement;

    public /* synthetic */ FlowMeasureLazyPolicy(boolean z, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f, CrossAxisAlignment crossAxisAlignment, float f2, int i, int i2, int i3, FlowLayoutOverflowState flowLayoutOverflowState, List list, Function4 function4, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, horizontal, vertical, f, crossAxisAlignment, f2, i, i2, i3, flowLayoutOverflowState, list, function4);
    }

    /* renamed from: component10, reason: from getter */
    private final FlowLayoutOverflowState getOverflow() {
        return this.overflow;
    }

    private final List<Function2<Composer, Integer, Unit>> component11() {
        return this.overflowComposables;
    }

    private final Function4<Integer, FlowLineInfo, Composer, Integer, Unit> component12() {
        return this.getComposable;
    }

    /* renamed from: component4-D9Ej5fM, reason: not valid java name and from getter */
    private final float getMainAxisSpacing() {
        return this.mainAxisSpacing;
    }

    /* renamed from: component6-D9Ej5fM, reason: not valid java name and from getter */
    private final float getCrossAxisArrangementSpacing() {
        return this.crossAxisArrangementSpacing;
    }

    /* renamed from: component7, reason: from getter */
    private final int getItemCount() {
        return this.itemCount;
    }

    /* renamed from: component8, reason: from getter */
    private final int getMaxLines() {
        return this.maxLines;
    }

    /* renamed from: component9, reason: from getter */
    private final int getMaxItemsInMainAxis() {
        return this.maxItemsInMainAxis;
    }

    /* renamed from: copy-E4Q9ldg$default, reason: not valid java name */
    public static /* synthetic */ FlowMeasureLazyPolicy m809copyE4Q9ldg$default(FlowMeasureLazyPolicy flowMeasureLazyPolicy, boolean z, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f, CrossAxisAlignment crossAxisAlignment, float f2, int i, int i2, int i3, FlowLayoutOverflowState flowLayoutOverflowState, List list, Function4 function4, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            z = flowMeasureLazyPolicy.isHorizontal;
        }
        if ((i4 & 2) != 0) {
            horizontal = flowMeasureLazyPolicy.horizontalArrangement;
        }
        if ((i4 & 4) != 0) {
            vertical = flowMeasureLazyPolicy.verticalArrangement;
        }
        if ((i4 & 8) != 0) {
            f = flowMeasureLazyPolicy.mainAxisSpacing;
        }
        if ((i4 & 16) != 0) {
            crossAxisAlignment = flowMeasureLazyPolicy.crossAxisAlignment;
        }
        if ((i4 & 32) != 0) {
            f2 = flowMeasureLazyPolicy.crossAxisArrangementSpacing;
        }
        if ((i4 & 64) != 0) {
            i = flowMeasureLazyPolicy.itemCount;
        }
        if ((i4 & 128) != 0) {
            i2 = flowMeasureLazyPolicy.maxLines;
        }
        if ((i4 & 256) != 0) {
            i3 = flowMeasureLazyPolicy.maxItemsInMainAxis;
        }
        if ((i4 & 512) != 0) {
            flowLayoutOverflowState = flowMeasureLazyPolicy.overflow;
        }
        if ((i4 & 1024) != 0) {
            list = flowMeasureLazyPolicy.overflowComposables;
        }
        if ((i4 & 2048) != 0) {
            function4 = flowMeasureLazyPolicy.getComposable;
        }
        List list2 = list;
        Function4 function42 = function4;
        int i5 = i3;
        FlowLayoutOverflowState flowLayoutOverflowState2 = flowLayoutOverflowState;
        int i6 = i;
        int i7 = i2;
        CrossAxisAlignment crossAxisAlignment2 = crossAxisAlignment;
        float f3 = f2;
        return flowMeasureLazyPolicy.m811copyE4Q9ldg(z, horizontal, vertical, f, crossAxisAlignment2, f3, i6, i7, i5, flowLayoutOverflowState2, list2, function42);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsHorizontal() {
        return this.isHorizontal;
    }

    /* renamed from: component2, reason: from getter */
    public final Arrangement.Horizontal getHorizontalArrangement() {
        return this.horizontalArrangement;
    }

    /* renamed from: component3, reason: from getter */
    public final Arrangement.Vertical getVerticalArrangement() {
        return this.verticalArrangement;
    }

    /* renamed from: component5, reason: from getter */
    public final CrossAxisAlignment getCrossAxisAlignment() {
        return this.crossAxisAlignment;
    }

    /* renamed from: copy-E4Q9ldg, reason: not valid java name */
    public final FlowMeasureLazyPolicy m811copyE4Q9ldg(boolean isHorizontal, Arrangement.Horizontal horizontalArrangement, Arrangement.Vertical verticalArrangement, float mainAxisSpacing, CrossAxisAlignment crossAxisAlignment, float crossAxisArrangementSpacing, int itemCount, int maxLines, int maxItemsInMainAxis, FlowLayoutOverflowState overflow, List<? extends Function2<? super Composer, ? super Integer, Unit>> overflowComposables, Function4<? super Integer, ? super FlowLineInfo, ? super Composer, ? super Integer, Unit> getComposable) {
        return new FlowMeasureLazyPolicy(isHorizontal, horizontalArrangement, verticalArrangement, mainAxisSpacing, crossAxisAlignment, crossAxisArrangementSpacing, itemCount, maxLines, maxItemsInMainAxis, overflow, overflowComposables, getComposable, null);
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy, androidx.compose.foundation.layout.RowColumnMeasurePolicy
    /* renamed from: createConstraints-xF2OJ5Q */
    public /* synthetic */ long mo771createConstraintsxF2OJ5Q(int i, int i2, int i3, int i4, boolean z) {
        return FlowLineMeasurePolicy.CC.m806$default$createConstraintsxF2OJ5Q(this, i, i2, i3, i4, z);
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy, androidx.compose.foundation.layout.RowColumnMeasurePolicy
    public /* synthetic */ int crossAxisSize(Placeable placeable) {
        return FlowLineMeasurePolicy.CC.$default$crossAxisSize(this, placeable);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FlowMeasureLazyPolicy)) {
            return false;
        }
        FlowMeasureLazyPolicy flowMeasureLazyPolicy = (FlowMeasureLazyPolicy) other;
        return this.isHorizontal == flowMeasureLazyPolicy.isHorizontal && Intrinsics.areEqual(this.horizontalArrangement, flowMeasureLazyPolicy.horizontalArrangement) && Intrinsics.areEqual(this.verticalArrangement, flowMeasureLazyPolicy.verticalArrangement) && Dp.m7404equalsimpl0(this.mainAxisSpacing, flowMeasureLazyPolicy.mainAxisSpacing) && Intrinsics.areEqual(this.crossAxisAlignment, flowMeasureLazyPolicy.crossAxisAlignment) && Dp.m7404equalsimpl0(this.crossAxisArrangementSpacing, flowMeasureLazyPolicy.crossAxisArrangementSpacing) && this.itemCount == flowMeasureLazyPolicy.itemCount && this.maxLines == flowMeasureLazyPolicy.maxLines && this.maxItemsInMainAxis == flowMeasureLazyPolicy.maxItemsInMainAxis && Intrinsics.areEqual(this.overflow, flowMeasureLazyPolicy.overflow) && Intrinsics.areEqual(this.overflowComposables, flowMeasureLazyPolicy.overflowComposables) && Intrinsics.areEqual(this.getComposable, flowMeasureLazyPolicy.getComposable);
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public /* synthetic */ int getCrossAxisPosition(Placeable placeable, int i, LayoutDirection layoutDirection, int i2) {
        return FlowLineMeasurePolicy.CC.$default$getCrossAxisPosition(this, placeable, i, layoutDirection, i2);
    }

    public int hashCode() {
        return (((((((((((((((((((((UByte$$ExternalSyntheticBackport0.m(this.isHorizontal) * 31) + this.horizontalArrangement.hashCode()) * 31) + this.verticalArrangement.hashCode()) * 31) + Dp.m7405hashCodeimpl(this.mainAxisSpacing)) * 31) + this.crossAxisAlignment.hashCode()) * 31) + Dp.m7405hashCodeimpl(this.crossAxisArrangementSpacing)) * 31) + this.itemCount) * 31) + this.maxLines) * 31) + this.maxItemsInMainAxis) * 31) + this.overflow.hashCode()) * 31) + this.overflowComposables.hashCode()) * 31) + this.getComposable.hashCode();
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy, androidx.compose.foundation.layout.RowColumnMeasurePolicy
    public /* synthetic */ int mainAxisSize(Placeable placeable) {
        return FlowLineMeasurePolicy.CC.$default$mainAxisSize(this, placeable);
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy, androidx.compose.foundation.layout.RowColumnMeasurePolicy
    public /* synthetic */ MeasureResult placeHelper(Placeable[] placeableArr, MeasureScope measureScope, int i, int[] iArr, int i2, int i3, int[] iArr2, int i4, int i5, int i6) {
        return FlowLineMeasurePolicy.CC.$default$placeHelper(this, placeableArr, measureScope, i, iArr, i2, i3, iArr2, i4, i5, i6);
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy, androidx.compose.foundation.layout.RowColumnMeasurePolicy
    public /* synthetic */ void populateMainAxisPositions(int i, int[] iArr, int[] iArr2, MeasureScope measureScope) {
        FlowLineMeasurePolicy.CC.$default$populateMainAxisPositions(this, i, iArr, iArr2, measureScope);
    }

    public String toString() {
        return "FlowMeasureLazyPolicy(isHorizontal=" + this.isHorizontal + ", horizontalArrangement=" + this.horizontalArrangement + ", verticalArrangement=" + this.verticalArrangement + ", mainAxisSpacing=" + ((Object) Dp.m7410toStringimpl(this.mainAxisSpacing)) + ", crossAxisAlignment=" + this.crossAxisAlignment + ", crossAxisArrangementSpacing=" + ((Object) Dp.m7410toStringimpl(this.crossAxisArrangementSpacing)) + ", itemCount=" + this.itemCount + ", maxLines=" + this.maxLines + ", maxItemsInMainAxis=" + this.maxItemsInMainAxis + ", overflow=" + this.overflow + ", overflowComposables=" + this.overflowComposables + ", getComposable=" + this.getComposable + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FlowMeasureLazyPolicy(boolean z, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f, CrossAxisAlignment crossAxisAlignment, float f2, int i, int i2, int i3, FlowLayoutOverflowState flowLayoutOverflowState, List<? extends Function2<? super Composer, ? super Integer, Unit>> list, Function4<? super Integer, ? super FlowLineInfo, ? super Composer, ? super Integer, Unit> function4) {
        this.isHorizontal = z;
        this.horizontalArrangement = horizontal;
        this.verticalArrangement = vertical;
        this.mainAxisSpacing = f;
        this.crossAxisAlignment = crossAxisAlignment;
        this.crossAxisArrangementSpacing = f2;
        this.itemCount = i;
        this.maxLines = i2;
        this.maxItemsInMainAxis = i3;
        this.overflow = flowLayoutOverflowState;
        this.overflowComposables = list;
        this.getComposable = function4;
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public boolean isHorizontal() {
        return this.isHorizontal;
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public Arrangement.Horizontal getHorizontalArrangement() {
        return this.horizontalArrangement;
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public Arrangement.Vertical getVerticalArrangement() {
        return this.verticalArrangement;
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public CrossAxisAlignment getCrossAxisAlignment() {
        return this.crossAxisAlignment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult getMeasurePolicy$lambda$0(FlowMeasureLazyPolicy flowMeasureLazyPolicy, SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
        return flowMeasureLazyPolicy.m810measure0kLqBqw(subcomposeMeasureScope, constraints.getValue());
    }

    public final Function2<SubcomposeMeasureScope, Constraints, MeasureResult> getMeasurePolicy() {
        return new Function2() { // from class: androidx.compose.foundation.layout.FlowMeasureLazyPolicy$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                MeasureResult measurePolicy$lambda$0;
                measurePolicy$lambda$0 = FlowMeasureLazyPolicy.getMeasurePolicy$lambda$0(FlowMeasureLazyPolicy.this, (SubcomposeMeasureScope) obj, (Constraints) obj2);
                return measurePolicy$lambda$0;
            }
        };
    }

    /* renamed from: measure-0kLqBqw, reason: not valid java name */
    private final MeasureResult m810measure0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, long j) {
        LayoutOrientation layoutOrientation;
        if (this.itemCount <= 0 || this.maxLines == 0 || this.maxItemsInMainAxis == 0 || (Constraints.m7341getMaxHeightimpl(j) == 0 && this.overflow.getType$foundation_layout() != FlowLayoutOverflow.OverflowType.Visible)) {
            return MeasureScope.CC.layout$default(subcomposeMeasureScope, 0, 0, null, new Function1() { // from class: androidx.compose.foundation.layout.FlowMeasureLazyPolicy$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit measure_0kLqBqw$lambda$1;
                    measure_0kLqBqw$lambda$1 = FlowMeasureLazyPolicy.measure_0kLqBqw$lambda$1((Placeable.PlacementScope) obj);
                    return measure_0kLqBqw$lambda$1;
                }
            }, 4, null);
        }
        ContextualFlowItemIterator contextualFlowItemIterator = new ContextualFlowItemIterator(this.itemCount, new Function2() { // from class: androidx.compose.foundation.layout.FlowMeasureLazyPolicy$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                List measure_0kLqBqw$lambda$2;
                measure_0kLqBqw$lambda$2 = FlowMeasureLazyPolicy.measure_0kLqBqw$lambda$2(SubcomposeMeasureScope.this, this, ((Integer) obj).intValue(), (FlowLineInfo) obj2);
                return measure_0kLqBqw$lambda$2;
            }
        });
        this.overflow.setItemCount$foundation_layout(this.itemCount);
        FlowMeasureLazyPolicy flowMeasureLazyPolicy = this;
        this.overflow.m800setOverflowMeasurablesVKLhPVY$foundation_layout(flowMeasureLazyPolicy, j, new Function2() { // from class: androidx.compose.foundation.layout.FlowMeasureLazyPolicy$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Measurable measure_0kLqBqw$lambda$4;
                measure_0kLqBqw$lambda$4 = FlowMeasureLazyPolicy.measure_0kLqBqw$lambda$4(FlowMeasureLazyPolicy.this, subcomposeMeasureScope, ((Boolean) obj).booleanValue(), ((Integer) obj2).intValue());
                return measure_0kLqBqw$lambda$4;
            }
        });
        SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
        ContextualFlowItemIterator contextualFlowItemIterator2 = contextualFlowItemIterator;
        float f = this.mainAxisSpacing;
        float f2 = this.crossAxisArrangementSpacing;
        if (isHorizontal()) {
            layoutOrientation = LayoutOrientation.Horizontal;
        } else {
            layoutOrientation = LayoutOrientation.Vertical;
        }
        return FlowLayoutKt.m794breakDownItemsdi9J0FM(subcomposeMeasureScope2, flowMeasureLazyPolicy, contextualFlowItemIterator2, f, f2, OrientationIndependentConstraints.m844constructorimpl(j, layoutOrientation), this.maxItemsInMainAxis, this.maxLines, this.overflow);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measure_0kLqBqw$lambda$1(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List measure_0kLqBqw$lambda$2(SubcomposeMeasureScope subcomposeMeasureScope, final FlowMeasureLazyPolicy flowMeasureLazyPolicy, final int i, final FlowLineInfo flowLineInfo) {
        return subcomposeMeasureScope.subcompose(Integer.valueOf(i), ComposableLambdaKt.composableLambdaInstance(-195060736, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.FlowMeasureLazyPolicy$measure$measurablesIterator$1$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i2) {
                Function4 function4;
                ComposerKt.sourceInformation(composer, "C486@20598L26:ContextualFlowLayout.kt#2w3rfo");
                if (!composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-195060736, i2, -1, "androidx.compose.foundation.layout.FlowMeasureLazyPolicy.measure.<anonymous>.<anonymous> (ContextualFlowLayout.kt:486)");
                }
                function4 = FlowMeasureLazyPolicy.this.getComposable;
                function4.invoke(Integer.valueOf(i), flowLineInfo, composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Measurable measure_0kLqBqw$lambda$4(FlowMeasureLazyPolicy flowMeasureLazyPolicy, SubcomposeMeasureScope subcomposeMeasureScope, boolean z, int i) {
        Function2<? super Composer, ? super Integer, Unit> function2 = (Function2) CollectionsKt.getOrNull(flowMeasureLazyPolicy.overflowComposables, !z ? 1 : 0);
        if (function2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(z);
        sb.append(flowMeasureLazyPolicy.itemCount);
        sb.append(i);
        return (Measurable) CollectionsKt.getOrNull(subcomposeMeasureScope.subcompose(sb.toString(), function2), 0);
    }
}
