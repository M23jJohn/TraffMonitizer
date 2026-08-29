package androidx.compose.foundation.lazy.grid;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutStickyItemsKt;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.foundation.lazy.layout.StickyItemsPlacement;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyGridMeasure.kt */
@Metadata(d1 = {"\u0000¸\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\u001aü\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030 2\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00132\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*23\u0010+\u001a/\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0011000 0,2!\u00101\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020\u00030,2\b\u00103\u001a\u0004\u0018\u0001042/\u00105\u001a+\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u0002080,¢\u0006\u0002\b9\u0012\u0004\u0012\u00020:06H\u0000¢\u0006\u0004\b;\u0010<\u001aA\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001d0 2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030 2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00130,H\u0082\b\u001aF\u0010?\u001a\b\u0012\u0004\u0012\u00020@0 2\u0006\u0010A\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00132\f\u0010B\u001a\b\u0012\u0004\u0012\u00020@0 2\b\u0010C\u001a\u0004\u0018\u00010$H\u0002\u001a\u008c\u0001\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001d0E2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020@0 2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001d0 2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u001d0 2\u0006\u0010I\u001a\u00020\u00032\u0006\u0010J\u001a\u00020\u00032\u0006\u0010K\u001a\u00020\u00032\u0006\u0010L\u001a\u00020\u00032\u0006\u0010M\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0002\u001a+\u0010N\u001a\u000208\"\u0004\b\u0000\u0010O*\b\u0012\u0004\u0012\u0002HO0E2\f\u0010P\u001a\b\u0012\u0004\u0012\u0002HO0QH\u0002¢\u0006\u0002\u0010R¨\u0006S"}, d2 = {"measureLazyGrid", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "itemsCount", "", "measuredLineProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;", "measuredItemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenLines", "firstVisibleLineIndex", "firstVisibleLineScrollOffset", "scrollToBeConsumed", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "itemAnimator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "slotsPerLine", "pinnedItems", "", "isInLookaheadScope", "isLookingAhead", "approachLayoutInfo", "Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "prefetchInfoRetriever", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "line", "Lkotlin/Pair;", "lineIndexProvider", "itemIndex", "stickyItemsScrollBehavior", "Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;", "layout", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyGrid-t1x4au0", "(ILandroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;IIIIIIFJZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;ILjava/util/List;ZZLandroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/graphics/GraphicsContext;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "calculateExtraItems", "filter", "linesRetainedForLookahead", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLine;", "lastVisibleItemIndex", "visibleLines", "lastApproachLayoutInfo", "calculateItemsOffsets", "", "lines", "itemsBefore", "itemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "firstLineScrollOffset", "addAllFromArray", "T", "arr", "", "(Ljava/util/List;[Ljava/lang/Object;)V", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyGridMeasureKt {
    private static final int calculateItemsOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0425  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0495 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x04b9  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x024a  */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25, types: [int] */
    /* JADX WARN: Type inference failed for: r3v45 */
    /* renamed from: measureLazyGrid-t1x4au0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final LazyGridMeasureResult m1042measureLazyGridt1x4au0(int i, final LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, int i2, int i3, int i4, int i5, int i6, int i7, float f, long j, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density, LazyLayoutItemAnimator<LazyGridMeasuredItem> lazyLayoutItemAnimator, int i8, List<Integer> list, boolean z3, final boolean z4, LazyGridLayoutInfo lazyGridLayoutInfo, CoroutineScope coroutineScope, final MutableState<Unit> mutableState, GraphicsContext graphicsContext, Function1<? super Integer, ? extends List<Pair<Integer, Constraints>>> function1, Function1<? super Integer, Integer> function12, StickyItemsPlacement stickyItemsPlacement, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        int i9;
        int i10;
        int i11;
        float f2;
        float f3;
        int i12;
        LazyGridMeasuredLine lazyGridMeasuredLine;
        int i13;
        int i14;
        int size;
        ArrayList arrayList;
        List list2;
        int i15;
        int i16;
        ArrayDeque arrayDeque;
        List<LazyGridMeasuredLine> linesRetainedForLookahead;
        int size2;
        int i17;
        int size3;
        int i18;
        int m7359constrainWidthK40F9xA;
        int m7341getMaxHeightimpl;
        List plus;
        final LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider2;
        int i19;
        boolean z5;
        Arrangement.Horizontal horizontal2;
        Density density2;
        int i20;
        Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function32;
        int i21;
        int i22;
        boolean z6;
        List list3;
        int i23;
        int i24;
        int i25;
        float f4;
        ArrayDeque arrayDeque2;
        int i26;
        int i27;
        int i28;
        int i29;
        int i30;
        LazyGridMeasuredItem[] items;
        LazyGridMeasuredItem lazyGridMeasuredItem;
        int i31;
        int i32;
        int i33;
        int i34 = i;
        boolean z7 = true;
        if (!(i3 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("negative beforeContentPadding");
        }
        if (!(i4 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("negative afterContentPadding");
        }
        if (i34 <= 0) {
            int m7344getMinWidthimpl = Constraints.m7344getMinWidthimpl(j);
            int m7343getMinHeightimpl = Constraints.m7343getMinHeightimpl(j);
            lazyLayoutItemAnimator.onMeasured(0, m7344getMinWidthimpl, m7343getMinHeightimpl, new ArrayList(), lazyGridMeasuredItemProvider.getKeyIndexMap(), lazyGridMeasuredItemProvider, z, z4, i8, z3, 0, 0, coroutineScope, graphicsContext);
            if (!z4) {
                long m1075getMinSizeToFitDisappearingItemsYbymL2g = lazyLayoutItemAnimator.m1075getMinSizeToFitDisappearingItemsYbymL2g();
                if (!IntSize.m7572equalsimpl0(m1075getMinSizeToFitDisappearingItemsYbymL2g, IntSize.INSTANCE.m7579getZeroYbymL2g())) {
                    m7344getMinWidthimpl = ConstraintsKt.m7359constrainWidthK40F9xA(j, (int) (m1075getMinSizeToFitDisappearingItemsYbymL2g >> 32));
                    m7343getMinHeightimpl = ConstraintsKt.m7358constrainHeightK40F9xA(j, (int) (m1075getMinSizeToFitDisappearingItemsYbymL2g & 4294967295L));
                }
            }
            return new LazyGridMeasureResult(null, 0, false, 0.0f, function3.invoke(Integer.valueOf(m7344getMinWidthimpl), Integer.valueOf(m7343getMinHeightimpl), new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit measureLazyGrid_t1x4au0$lambda$2;
                    measureLazyGrid_t1x4au0$lambda$2 = LazyGridMeasureKt.measureLazyGrid_t1x4au0$lambda$2((Placeable.PlacementScope) obj);
                    return measureLazyGrid_t1x4au0$lambda$2;
                }
            }), 0.0f, false, coroutineScope, density, i8, function1, function12, CollectionsKt.emptyList(), -i3, i2 + i4, 0, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
        }
        int round = Math.round(f);
        int i35 = i7 - round;
        if (i6 == 0 && i35 < 0) {
            round += i35;
            i35 = 0;
        }
        ArrayDeque arrayDeque3 = new ArrayDeque();
        int i36 = -i3;
        int i37 = (i5 < 0 ? i5 : 0) + i36;
        int i38 = i35 + i37;
        int i39 = i6;
        while (i38 < 0 && i39 > 0) {
            i39--;
            LazyGridMeasuredLine andMeasure = lazyGridMeasuredLineProvider.getAndMeasure(i39);
            arrayDeque3.add(0, andMeasure);
            i38 += andMeasure.getMainAxisSizeWithSpacings();
        }
        if (i38 < i37) {
            round -= i37 - i38;
            i38 = i37;
        }
        int i40 = i38 - i37;
        int i41 = i2 + i4;
        int i42 = i39;
        int coerceAtLeast = RangesKt.coerceAtLeast(i41, 0);
        int i43 = -i40;
        int i44 = i42;
        int i45 = i36;
        int i46 = 0;
        boolean z8 = false;
        while (i46 < arrayDeque3.size()) {
            if (i43 >= coerceAtLeast) {
                arrayDeque3.remove(i46);
                Unit unit = Unit.INSTANCE;
                z8 = true;
            } else {
                i44++;
                i43 += ((LazyGridMeasuredLine) arrayDeque3.get(i46)).getMainAxisSizeWithSpacings();
                Integer.valueOf(i46);
                i46++;
            }
        }
        int i47 = i40;
        boolean z9 = z8;
        int i48 = i44;
        while (i48 < i34 && (i43 < coerceAtLeast || i43 <= 0 || arrayDeque3.isEmpty())) {
            LazyGridMeasuredLine andMeasure2 = lazyGridMeasuredLineProvider.getAndMeasure(i48);
            if (andMeasure2.isEmpty()) {
                break;
            }
            i43 += andMeasure2.getMainAxisSizeWithSpacings();
            if (i43 <= i37) {
                i31 = coerceAtLeast;
                i32 = i48;
                if (((LazyGridMeasuredItem) ArraysKt.last(andMeasure2.getItems())).getIndex() != i - 1) {
                    i33 = i32 + 1;
                    i47 -= andMeasure2.getMainAxisSizeWithSpacings();
                    Unit unit2 = Unit.INSTANCE;
                    z9 = true;
                    coerceAtLeast = i31;
                    i42 = i33;
                    i48 = i32 + 1;
                    i34 = i;
                }
            } else {
                i31 = coerceAtLeast;
                i32 = i48;
            }
            Boolean.valueOf(arrayDeque3.add(andMeasure2));
            i33 = i42;
            coerceAtLeast = i31;
            i42 = i33;
            i48 = i32 + 1;
            i34 = i;
        }
        if (i43 < i2) {
            int i49 = i2 - i43;
            i47 -= i49;
            i43 += i49;
            int i50 = i42;
            while (i47 < i3 && i50 > 0) {
                i50--;
                LazyGridMeasuredLine andMeasure3 = lazyGridMeasuredLineProvider.getAndMeasure(i50);
                arrayDeque3.add(0, andMeasure3);
                i47 += andMeasure3.getMainAxisSizeWithSpacings();
            }
            i9 = i49 + round;
            if (i47 < 0) {
                i9 += i47;
                i10 = i43 + i47;
                i11 = 0;
                float f5 = (MathKt.getSign(Math.round(f)) == MathKt.getSign(i9) || Math.abs(Math.round(f)) < Math.abs(i9)) ? f : i9;
                f2 = f - f5;
                f3 = 0.0f;
                if (z4 && i9 > round && f2 <= 0.0f) {
                    f3 = (i9 - round) + f2;
                }
                float f6 = f3;
                if (!(i11 < 0)) {
                    InlineClassHelperKt.throwIllegalArgumentException("negative initial offset");
                }
                i12 = -i11;
                LazyGridMeasuredLine lazyGridMeasuredLine2 = (LazyGridMeasuredLine) arrayDeque3.first();
                LazyGridMeasuredItem lazyGridMeasuredItem2 = (LazyGridMeasuredItem) ArraysKt.firstOrNull(lazyGridMeasuredLine2.getItems());
                int index = lazyGridMeasuredItem2 == null ? lazyGridMeasuredItem2.getIndex() : 0;
                lazyGridMeasuredLine = (LazyGridMeasuredLine) arrayDeque3.lastOrNull();
                if (lazyGridMeasuredLine != null || (items = lazyGridMeasuredLine.getItems()) == null || (lazyGridMeasuredItem = (LazyGridMeasuredItem) ArraysKt.lastOrNull(items)) == null) {
                    i13 = i12;
                    i14 = 0;
                } else {
                    i13 = i12;
                    i14 = lazyGridMeasuredItem.getIndex();
                }
                List<Integer> list4 = list;
                size = list4.size();
                arrayList = null;
                list2 = null;
                i15 = 0;
                while (i15 < size) {
                    int i51 = i14;
                    int intValue = list.get(i15).intValue();
                    if (intValue < 0 || intValue >= index) {
                        f4 = f5;
                        arrayDeque2 = arrayDeque3;
                        i26 = i10;
                        i27 = size;
                        i28 = i15;
                        i29 = i41;
                        i30 = i45;
                    } else {
                        i27 = size;
                        int spanOf = lazyGridMeasuredLineProvider.spanOf(intValue);
                        i28 = i15;
                        f4 = f5;
                        arrayDeque2 = arrayDeque3;
                        i30 = i45;
                        i29 = i41;
                        i26 = i10;
                        LazyGridMeasuredItem mo1008getAndMeasurehBUhpc = lazyGridMeasuredItemProvider.mo1008getAndMeasurehBUhpc(intValue, 0, spanOf, lazyGridMeasuredLineProvider.m1046childConstraintsJhjzzOo$foundation_release(0, spanOf));
                        ArrayList arrayList2 = list2 == null ? new ArrayList() : list2;
                        arrayList2.add(mo1008getAndMeasurehBUhpc);
                        list2 = arrayList2;
                    }
                    i15 = i28 + 1;
                    arrayDeque3 = arrayDeque2;
                    i10 = i26;
                    f5 = f4;
                    size = i27;
                    i45 = i30;
                    i41 = i29;
                    i14 = i51;
                }
                i16 = i14;
                float f7 = f5;
                arrayDeque = arrayDeque3;
                int i52 = i10;
                int i53 = i41;
                int i54 = i45;
                if (list2 == null) {
                    list2 = CollectionsKt.emptyList();
                }
                ArrayDeque arrayDeque4 = arrayDeque;
                int i55 = index;
                linesRetainedForLookahead = linesRetainedForLookahead(i16, i, lazyGridMeasuredLineProvider, z4, arrayDeque4, lazyGridLayoutInfo);
                int i56 = i;
                LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider2 = lazyGridMeasuredLineProvider;
                size2 = list4.size();
                i17 = 0;
                while (i17 < size2) {
                    int intValue2 = list.get(i17).intValue();
                    if (i16 + 1 > intValue2 || intValue2 >= i56) {
                        i24 = size2;
                    } else {
                        if (z4) {
                            int size4 = linesRetainedForLookahead.size();
                            int i57 = 0;
                            while (i57 < size4) {
                                int i58 = i57;
                                LazyGridMeasuredItem[] items2 = linesRetainedForLookahead.get(i57).getItems();
                                int i59 = size2;
                                int length = items2.length;
                                int i60 = 0;
                                while (i60 < length) {
                                    int i61 = i60;
                                    if (items2[i60].getIndex() == intValue2) {
                                        i24 = i59;
                                    } else {
                                        i60 = i61 + 1;
                                    }
                                }
                                i57 = i58 + 1;
                                size2 = i59;
                            }
                        }
                        int i62 = i17;
                        int spanOf2 = lazyGridMeasuredLineProvider2.spanOf(intValue2);
                        i24 = size2;
                        i25 = i62;
                        LazyGridMeasuredItem mo1008getAndMeasurehBUhpc2 = lazyGridMeasuredItemProvider.mo1008getAndMeasurehBUhpc(intValue2, 0, spanOf2, lazyGridMeasuredLineProvider2.m1046childConstraintsJhjzzOo$foundation_release(0, spanOf2));
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        List list5 = arrayList;
                        list5.add(mo1008getAndMeasurehBUhpc2);
                        arrayList = list5;
                        i17 = i25 + 1;
                        i56 = i;
                        lazyGridMeasuredLineProvider2 = lazyGridMeasuredLineProvider;
                        size2 = i24;
                    }
                    i25 = i17;
                    i17 = i25 + 1;
                    i56 = i;
                    lazyGridMeasuredLineProvider2 = lazyGridMeasuredLineProvider;
                    size2 = i24;
                }
                if (arrayList == null) {
                    arrayList = CollectionsKt.emptyList();
                }
                List list6 = arrayList;
                if (i3 <= 0 || i5 < 0) {
                    size3 = arrayDeque.size();
                    i18 = 0;
                    while (i18 < size3) {
                        int mainAxisSizeWithSpacings = ((LazyGridMeasuredLine) arrayDeque.get(i18)).getMainAxisSizeWithSpacings();
                        if (i11 == 0 || mainAxisSizeWithSpacings > i11 || i18 == CollectionsKt.getLastIndex(arrayDeque4)) {
                            break;
                        }
                        i11 -= mainAxisSizeWithSpacings;
                        i18++;
                        lazyGridMeasuredLine2 = (LazyGridMeasuredLine) arrayDeque.get(i18);
                    }
                }
                int i63 = i11;
                LazyGridMeasuredLine lazyGridMeasuredLine3 = lazyGridMeasuredLine2;
                if (!z) {
                    m7359constrainWidthK40F9xA = Constraints.m7342getMaxWidthimpl(j);
                } else {
                    m7359constrainWidthK40F9xA = ConstraintsKt.m7359constrainWidthK40F9xA(j, i52);
                }
                int i64 = m7359constrainWidthK40F9xA;
                if (!z) {
                    m7341getMaxHeightimpl = ConstraintsKt.m7358constrainHeightK40F9xA(j, i52);
                } else {
                    m7341getMaxHeightimpl = Constraints.m7341getMaxHeightimpl(j);
                }
                int i65 = m7341getMaxHeightimpl;
                if (linesRetainedForLookahead.isEmpty()) {
                    plus = CollectionsKt.plus((Collection) arrayDeque, (Iterable) linesRetainedForLookahead);
                    lazyGridMeasuredItemProvider2 = lazyGridMeasuredItemProvider;
                    i19 = i2;
                    z5 = z;
                    horizontal2 = horizontal;
                    density2 = density;
                    i20 = i52;
                    function32 = function3;
                    i21 = i64;
                    i22 = i13;
                    z6 = false;
                    list3 = list2;
                } else {
                    plus = arrayDeque4;
                    lazyGridMeasuredItemProvider2 = lazyGridMeasuredItemProvider;
                    i19 = i2;
                    horizontal2 = horizontal;
                    density2 = density;
                    i20 = i52;
                    function32 = function3;
                    i21 = i64;
                    i22 = i13;
                    z6 = false;
                    list3 = list2;
                    z5 = z;
                }
                final List<LazyGridMeasuredItem> calculateItemsOffsets = calculateItemsOffsets(plus, list3, list6, i21, i65, i20, i19, i22, z5, vertical, horizontal2, z2, density2);
                int i66 = i20;
                int i67 = i19;
                lazyLayoutItemAnimator.onMeasured((int) f7, i21, i65, calculateItemsOffsets, lazyGridMeasuredItemProvider2.getKeyIndexMap(), lazyGridMeasuredItemProvider2, z, z4, i8, z3, i63, i66, coroutineScope, graphicsContext);
                if (!z4) {
                    long m1075getMinSizeToFitDisappearingItemsYbymL2g2 = lazyLayoutItemAnimator.m1075getMinSizeToFitDisappearingItemsYbymL2g();
                    if (!IntSize.m7572equalsimpl0(m1075getMinSizeToFitDisappearingItemsYbymL2g2, IntSize.INSTANCE.m7579getZeroYbymL2g())) {
                        int i68 = z ? i65 : i21;
                        int m7359constrainWidthK40F9xA2 = ConstraintsKt.m7359constrainWidthK40F9xA(j, Math.max(i21, (int) (m1075getMinSizeToFitDisappearingItemsYbymL2g2 >> 32)));
                        i65 = ConstraintsKt.m7358constrainHeightK40F9xA(j, Math.max(i65, (int) (m1075getMinSizeToFitDisappearingItemsYbymL2g2 & 4294967295L)));
                        int i69 = z ? i65 : m7359constrainWidthK40F9xA2;
                        if (i69 != i68) {
                            int size5 = calculateItemsOffsets.size();
                            for (?? r3 = z6; r3 < size5; r3++) {
                                calculateItemsOffsets.get(r3).updateMainAxisLayoutSize(i69);
                            }
                        }
                        i23 = m7359constrainWidthK40F9xA2;
                        int i70 = i65;
                        final List applyStickyItems = LazyLayoutStickyItemsKt.applyStickyItems(stickyItemsPlacement, i55, i16, calculateItemsOffsets, lazyGridMeasuredItemProvider2.getHeaderIndices(), i3, i4, i23, i70, new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LazyGridMeasuredItem measureLazyGrid_t1x4au0$lambda$9;
                                measureLazyGrid_t1x4au0$lambda$9 = LazyGridMeasureKt.measureLazyGrid_t1x4au0$lambda$9(LazyGridMeasuredLineProvider.this, lazyGridMeasuredItemProvider2, ((Integer) obj).intValue());
                                return measureLazyGrid_t1x4au0$lambda$9;
                            }
                        });
                        if (i16 == i - 1 && i66 <= i67) {
                            z7 = z6;
                        }
                        return new LazyGridMeasureResult(lazyGridMeasuredLine3, i63, z7, f7, function32.invoke(Integer.valueOf(i23), Integer.valueOf(i70), new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit measureLazyGrid_t1x4au0$lambda$13;
                                measureLazyGrid_t1x4au0$lambda$13 = LazyGridMeasureKt.measureLazyGrid_t1x4au0$lambda$13(MutableState.this, calculateItemsOffsets, applyStickyItems, z4, (Placeable.PlacementScope) obj);
                                return measureLazyGrid_t1x4au0$lambda$13;
                            }
                        }), f6, z9, coroutineScope, density, i8, function1, function12, LazyLayoutMeasuredItemKt.updatedVisibleItems(i55, i16, calculateItemsOffsets, applyStickyItems), i54, i53, i, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
                    }
                }
                i23 = i21;
                int i702 = i65;
                final List applyStickyItems2 = LazyLayoutStickyItemsKt.applyStickyItems(stickyItemsPlacement, i55, i16, calculateItemsOffsets, lazyGridMeasuredItemProvider2.getHeaderIndices(), i3, i4, i23, i702, new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LazyGridMeasuredItem measureLazyGrid_t1x4au0$lambda$9;
                        measureLazyGrid_t1x4au0$lambda$9 = LazyGridMeasureKt.measureLazyGrid_t1x4au0$lambda$9(LazyGridMeasuredLineProvider.this, lazyGridMeasuredItemProvider2, ((Integer) obj).intValue());
                        return measureLazyGrid_t1x4au0$lambda$9;
                    }
                });
                if (i16 == i - 1) {
                    z7 = z6;
                }
                return new LazyGridMeasureResult(lazyGridMeasuredLine3, i63, z7, f7, function32.invoke(Integer.valueOf(i23), Integer.valueOf(i702), new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit measureLazyGrid_t1x4au0$lambda$13;
                        measureLazyGrid_t1x4au0$lambda$13 = LazyGridMeasureKt.measureLazyGrid_t1x4au0$lambda$13(MutableState.this, calculateItemsOffsets, applyStickyItems2, z4, (Placeable.PlacementScope) obj);
                        return measureLazyGrid_t1x4au0$lambda$13;
                    }
                }), f6, z9, coroutineScope, density, i8, function1, function12, LazyLayoutMeasuredItemKt.updatedVisibleItems(i55, i16, calculateItemsOffsets, applyStickyItems2), i54, i53, i, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
            }
        } else {
            i9 = round;
        }
        i10 = i43;
        i11 = i47;
        if (MathKt.getSign(Math.round(f)) == MathKt.getSign(i9)) {
        }
        f2 = f - f5;
        f3 = 0.0f;
        if (z4) {
            f3 = (i9 - round) + f2;
        }
        float f62 = f3;
        if (!(i11 < 0)) {
        }
        i12 = -i11;
        LazyGridMeasuredLine lazyGridMeasuredLine22 = (LazyGridMeasuredLine) arrayDeque3.first();
        LazyGridMeasuredItem lazyGridMeasuredItem22 = (LazyGridMeasuredItem) ArraysKt.firstOrNull(lazyGridMeasuredLine22.getItems());
        if (lazyGridMeasuredItem22 == null) {
        }
        lazyGridMeasuredLine = (LazyGridMeasuredLine) arrayDeque3.lastOrNull();
        if (lazyGridMeasuredLine != null) {
        }
        i13 = i12;
        i14 = 0;
        List<Integer> list42 = list;
        size = list42.size();
        arrayList = null;
        list2 = null;
        i15 = 0;
        while (i15 < size) {
        }
        i16 = i14;
        float f72 = f5;
        arrayDeque = arrayDeque3;
        int i522 = i10;
        int i532 = i41;
        int i542 = i45;
        if (list2 == null) {
        }
        ArrayDeque arrayDeque42 = arrayDeque;
        int i552 = index;
        linesRetainedForLookahead = linesRetainedForLookahead(i16, i, lazyGridMeasuredLineProvider, z4, arrayDeque42, lazyGridLayoutInfo);
        int i562 = i;
        LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider22 = lazyGridMeasuredLineProvider;
        size2 = list42.size();
        i17 = 0;
        while (i17 < size2) {
        }
        if (arrayList == null) {
        }
        List list62 = arrayList;
        if (i3 <= 0) {
        }
        size3 = arrayDeque.size();
        i18 = 0;
        while (i18 < size3) {
        }
        int i632 = i11;
        LazyGridMeasuredLine lazyGridMeasuredLine32 = lazyGridMeasuredLine22;
        if (!z) {
        }
        int i642 = m7359constrainWidthK40F9xA;
        if (!z) {
        }
        int i652 = m7341getMaxHeightimpl;
        if (linesRetainedForLookahead.isEmpty()) {
        }
        final List calculateItemsOffsets2 = calculateItemsOffsets(plus, list3, list62, i21, i652, i20, i19, i22, z5, vertical, horizontal2, z2, density2);
        int i662 = i20;
        int i672 = i19;
        lazyLayoutItemAnimator.onMeasured((int) f72, i21, i652, calculateItemsOffsets2, lazyGridMeasuredItemProvider2.getKeyIndexMap(), lazyGridMeasuredItemProvider2, z, z4, i8, z3, i632, i662, coroutineScope, graphicsContext);
        if (!z4) {
        }
        i23 = i21;
        int i7022 = i652;
        final List applyStickyItems22 = LazyLayoutStickyItemsKt.applyStickyItems(stickyItemsPlacement, i552, i16, calculateItemsOffsets2, lazyGridMeasuredItemProvider2.getHeaderIndices(), i3, i4, i23, i7022, new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LazyGridMeasuredItem measureLazyGrid_t1x4au0$lambda$9;
                measureLazyGrid_t1x4au0$lambda$9 = LazyGridMeasureKt.measureLazyGrid_t1x4au0$lambda$9(LazyGridMeasuredLineProvider.this, lazyGridMeasuredItemProvider2, ((Integer) obj).intValue());
                return measureLazyGrid_t1x4au0$lambda$9;
            }
        });
        if (i16 == i - 1) {
        }
        return new LazyGridMeasureResult(lazyGridMeasuredLine32, i632, z7, f72, function32.invoke(Integer.valueOf(i23), Integer.valueOf(i7022), new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit measureLazyGrid_t1x4au0$lambda$13;
                measureLazyGrid_t1x4au0$lambda$13 = LazyGridMeasureKt.measureLazyGrid_t1x4au0$lambda$13(MutableState.this, calculateItemsOffsets2, applyStickyItems22, z4, (Placeable.PlacementScope) obj);
                return measureLazyGrid_t1x4au0$lambda$13;
            }
        }), f62, z9, coroutineScope, density, i8, function1, function12, LazyLayoutMeasuredItemKt.updatedVisibleItems(i552, i16, calculateItemsOffsets2, applyStickyItems22), i542, i532, i, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measureLazyGrid_t1x4au0$lambda$2(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazyGridMeasuredItem measureLazyGrid_t1x4au0$lambda$9(LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, int i) {
        int spanOf = lazyGridMeasuredLineProvider.spanOf(i);
        return lazyGridMeasuredItemProvider.mo1008getAndMeasurehBUhpc(i, 0, spanOf, lazyGridMeasuredLineProvider.m1046childConstraintsJhjzzOo$foundation_release(0, spanOf));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measureLazyGrid_t1x4au0$lambda$13(MutableState mutableState, final List list, final List list2, final boolean z, Placeable.PlacementScope placementScope) {
        placementScope.withMotionFrameOfReferencePlacement(new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit measureLazyGrid_t1x4au0$lambda$13$lambda$12;
                measureLazyGrid_t1x4au0$lambda$13$lambda$12 = LazyGridMeasureKt.measureLazyGrid_t1x4au0$lambda$13$lambda$12(list, list2, z, (Placeable.PlacementScope) obj);
                return measureLazyGrid_t1x4au0$lambda$13$lambda$12;
            }
        });
        ObservableScopeInvalidator.m1107attachToScopeimpl(mutableState);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        r6 = r9.get(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final List<LazyGridMeasuredLine> linesRetainedForLookahead(int i, int i2, LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, boolean z, List<LazyGridMeasuredLine> list, LazyGridLayoutInfo lazyGridLayoutInfo) {
        LazyGridItemInfo lazyGridItemInfo;
        int index;
        int min;
        ArrayList arrayList = null;
        if (z && lazyGridLayoutInfo != null && !lazyGridLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            List<LazyGridItemInfo> visibleItemsInfo = lazyGridLayoutInfo.getVisibleItemsInfo();
            int size = visibleItemsInfo.size();
            while (true) {
                size--;
                if (-1 >= size) {
                    lazyGridItemInfo = null;
                    break;
                }
                if (visibleItemsInfo.get(size).getIndex() <= i || (size != 0 && visibleItemsInfo.get(size - 1).getIndex() > i)) {
                }
            }
            LazyGridItemInfo lazyGridItemInfo2 = (LazyGridItemInfo) CollectionsKt.last((List) lazyGridLayoutInfo.getVisibleItemsInfo());
            LazyGridMeasuredLine lazyGridMeasuredLine = (LazyGridMeasuredLine) CollectionsKt.lastOrNull((List) list);
            int index2 = lazyGridMeasuredLine != null ? lazyGridMeasuredLine.getIndex() + 1 : 0;
            if (lazyGridItemInfo != null && (index = lazyGridItemInfo.getIndex()) <= (min = Math.min(lazyGridItemInfo2.getIndex(), i2 - 1))) {
                while (true) {
                    if (arrayList != null) {
                        int size2 = arrayList.size();
                        for (int i3 = 0; i3 < size2; i3++) {
                            for (LazyGridMeasuredItem lazyGridMeasuredItem : arrayList.get(i3).getItems()) {
                                if (lazyGridMeasuredItem.getIndex() == index) {
                                    break;
                                }
                            }
                        }
                    }
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    LazyGridMeasuredLine andMeasure = lazyGridMeasuredLineProvider.getAndMeasure(index2);
                    index2++;
                    arrayList.add(andMeasure);
                    if (index == min) {
                        break;
                    }
                    index++;
                }
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyGridMeasuredItem> calculateItemsOffsets(List<LazyGridMeasuredLine> list, List<LazyGridMeasuredItem> list2, List<LazyGridMeasuredItem> list3, int i, int i2, int i3, int i4, int i5, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density) {
        int i6 = z ? i2 : i;
        boolean z3 = i3 < Math.min(i6, i4);
        if (z3) {
            if (!(i5 == 0)) {
                InlineClassHelperKt.throwIllegalStateException("non-zero firstLineScrollOffset");
            }
        }
        List<LazyGridMeasuredLine> list4 = list;
        int size = list4.size();
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            i7 += list.get(i8).getItems().length;
        }
        ArrayList arrayList = new ArrayList(i7);
        if (z3) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                InlineClassHelperKt.throwIllegalArgumentException("no items");
            }
            int size2 = list.size();
            int[] iArr = new int[size2];
            for (int i9 = 0; i9 < size2; i9++) {
                iArr[i9] = list.get(calculateItemsOffsets$reverseAware(i9, z2, size2)).getMainAxisSize();
            }
            int[] iArr2 = new int[size2];
            if (z) {
                if (vertical != null) {
                    vertical.arrange(density, i6, iArr, iArr2);
                } else {
                    InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null verticalArrangement");
                    throw new KotlinNothingValueException();
                }
            } else if (horizontal != null) {
                horizontal.arrange(density, i6, iArr, LayoutDirection.Ltr, iArr2);
            } else {
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null horizontalArrangement");
                throw new KotlinNothingValueException();
            }
            IntRange indices = ArraysKt.getIndices(iArr2);
            if (z2) {
                indices = RangesKt.reversed(indices);
            }
            int first = indices.getFirst();
            int last = indices.getLast();
            int step = indices.getStep();
            if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
                while (true) {
                    int i10 = iArr2[first];
                    LazyGridMeasuredLine lazyGridMeasuredLine = list.get(calculateItemsOffsets$reverseAware(first, z2, size2));
                    if (z2) {
                        i10 = (i6 - i10) - lazyGridMeasuredLine.getMainAxisSize();
                    }
                    addAllFromArray(arrayList, lazyGridMeasuredLine.position(i10, i, i2));
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            int size3 = list2.size() - 1;
            if (size3 >= 0) {
                int i11 = i5;
                while (true) {
                    int i12 = size3 - 1;
                    LazyGridMeasuredItem lazyGridMeasuredItem = list2.get(size3);
                    i11 -= lazyGridMeasuredItem.getMainAxisSizeWithSpacings();
                    lazyGridMeasuredItem.position(i11, 0, i, i2);
                    arrayList.add(lazyGridMeasuredItem);
                    if (i12 < 0) {
                        break;
                    }
                    size3 = i12;
                }
            }
            int size4 = list4.size();
            int i13 = i5;
            for (int i14 = 0; i14 < size4; i14++) {
                LazyGridMeasuredLine lazyGridMeasuredLine2 = list.get(i14);
                addAllFromArray(arrayList, lazyGridMeasuredLine2.position(i13, i, i2));
                i13 += lazyGridMeasuredLine2.getMainAxisSizeWithSpacings();
            }
            int size5 = list3.size();
            for (int i15 = 0; i15 < size5; i15++) {
                LazyGridMeasuredItem lazyGridMeasuredItem2 = list3.get(i15);
                lazyGridMeasuredItem2.position(i13, 0, i, i2);
                arrayList.add(lazyGridMeasuredItem2);
                i13 += lazyGridMeasuredItem2.getMainAxisSizeWithSpacings();
            }
        }
        return arrayList;
    }

    private static final <T> void addAllFromArray(List<T> list, T[] tArr) {
        for (T t : tArr) {
            list.add(t);
        }
    }

    private static final List<LazyGridMeasuredItem> calculateExtraItems(List<Integer> list, LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, Function1<? super Integer, Boolean> function1) {
        LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider2;
        int size = list.size();
        ArrayList arrayList = null;
        int i = 0;
        while (i < size) {
            int intValue = list.get(i).intValue();
            if (function1.invoke(Integer.valueOf(intValue)).booleanValue()) {
                int spanOf = lazyGridMeasuredLineProvider.spanOf(intValue);
                lazyGridMeasuredItemProvider2 = lazyGridMeasuredItemProvider;
                LazyGridMeasuredItem mo1008getAndMeasurehBUhpc = lazyGridMeasuredItemProvider2.mo1008getAndMeasurehBUhpc(intValue, 0, spanOf, lazyGridMeasuredLineProvider.m1046childConstraintsJhjzzOo$foundation_release(0, spanOf));
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(mo1008getAndMeasurehBUhpc);
            } else {
                lazyGridMeasuredItemProvider2 = lazyGridMeasuredItemProvider;
            }
            i++;
            lazyGridMeasuredItemProvider = lazyGridMeasuredItemProvider2;
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measureLazyGrid_t1x4au0$lambda$13$lambda$12(List list, List list2, boolean z, Placeable.PlacementScope placementScope) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((LazyGridMeasuredItem) list.get(i)).place(placementScope, z);
        }
        int size2 = list2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((LazyGridMeasuredItem) list2.get(i2)).place(placementScope, z);
        }
        return Unit.INSTANCE;
    }
}
