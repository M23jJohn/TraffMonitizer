package androidx.compose.foundation.lazy;

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
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyListMeasure.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u000e\u001a\u009c\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u00032\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001e2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00112\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2/\u0010+\u001a+\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020/0-¢\u0006\u0002\b0\u0012\u0004\u0012\u0002010,H\u0000¢\u0006\u0004\b2\u00103\u001a\\\u00104\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001e2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u001b062\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00032\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001e2\u0006\u00107\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00112\b\u00108\u001a\u0004\u0018\u00010\"H\u0002\u001a4\u00109\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001e2\u0006\u0010:\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00032\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001eH\u0002\u001a\u008c\u0001\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001b062\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001e2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001e2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001e2\u0006\u0010?\u001a\u00020\u00032\u0006\u0010@\u001a\u00020\u00032\u0006\u0010A\u001a\u00020\u00032\u0006\u0010B\u001a\u00020\u00032\u0006\u0010C\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0002¨\u0006D"}, d2 = {"measureLazyList", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "itemsCount", "", "measuredItemProvider", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenItems", "firstVisibleItemIndex", "firstVisibleItemScrollOffset", "scrollToBeConsumed", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "itemAnimator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "beyondBoundsItemCount", "pinnedItems", "", "hasLookaheadOccurred", "isLookingAhead", "approachLayoutInfo", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "stickyItemsPlacement", "Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;", "layout", "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyList-LCrQqZ4", "(ILandroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;IIIIIIFJZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;ILjava/util/List;ZZLandroidx/compose/foundation/lazy/LazyListLayoutInfo;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/graphics/GraphicsContext;Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "createItemsAfterList", "visibleItems", "", "consumedScroll", "lastApproachLayoutInfo", "createItemsBeforeList", "currentFirstItemIndex", "calculateItemsOffsets", "items", "extraItemsBefore", "extraItemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "itemsScrollOffset", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyListMeasureKt {
    private static final int calculateItemsOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:129:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x043c  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0490  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x044d  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x03fb  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x03eb  */
    /* renamed from: measureLazyList-LCrQqZ4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final LazyListMeasureResult m1001measureLazyListLCrQqZ4(int i, final LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i2, int i3, int i4, int i5, int i6, int i7, float f, long j, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density, LazyLayoutItemAnimator<LazyListMeasuredItem> lazyLayoutItemAnimator, int i8, List<Integer> list, boolean z3, final boolean z4, LazyListLayoutInfo lazyListLayoutInfo, CoroutineScope coroutineScope, final MutableState<Unit> mutableState, GraphicsContext graphicsContext, StickyItemsPlacement stickyItemsPlacement, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        LazyListMeasuredItemProvider lazyListMeasuredItemProvider2;
        int i14;
        int i15;
        int i16;
        int i17;
        LazyListMeasuredItem lazyListMeasuredItem;
        int i18;
        LazyListMeasuredItem lazyListMeasuredItem2;
        int i19;
        LazyListMeasuredItem lazyListMeasuredItem3;
        List<LazyListMeasuredItem> list2;
        int i20;
        Integer valueOf;
        int i21;
        int i22 = i3;
        boolean z5 = true;
        if (!(i22 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("invalid beforeContentPadding");
        }
        if (!(i4 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("invalid afterContentPadding");
        }
        if (i <= 0) {
            int m7344getMinWidthimpl = Constraints.m7344getMinWidthimpl(j);
            int m7343getMinHeightimpl = Constraints.m7343getMinHeightimpl(j);
            lazyLayoutItemAnimator.onMeasured(0, m7344getMinWidthimpl, m7343getMinHeightimpl, new ArrayList(), lazyListMeasuredItemProvider.getKeyIndexMap(), lazyListMeasuredItemProvider, z, z4, 1, z3, 0, 0, coroutineScope, graphicsContext);
            if (!z4) {
                long m1075getMinSizeToFitDisappearingItemsYbymL2g = lazyLayoutItemAnimator.m1075getMinSizeToFitDisappearingItemsYbymL2g();
                if (!IntSize.m7572equalsimpl0(m1075getMinSizeToFitDisappearingItemsYbymL2g, IntSize.INSTANCE.m7579getZeroYbymL2g())) {
                    m7344getMinWidthimpl = ConstraintsKt.m7359constrainWidthK40F9xA(j, (int) (m1075getMinSizeToFitDisappearingItemsYbymL2g >> 32));
                    m7343getMinHeightimpl = ConstraintsKt.m7358constrainHeightK40F9xA(j, (int) (m1075getMinSizeToFitDisappearingItemsYbymL2g & 4294967295L));
                }
            }
            return new LazyListMeasureResult(null, 0, false, 0.0f, function3.invoke(Integer.valueOf(m7344getMinWidthimpl), Integer.valueOf(m7343getMinHeightimpl), new Function1() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit measureLazyList_LCrQqZ4$lambda$2;
                    measureLazyList_LCrQqZ4$lambda$2 = LazyListMeasureKt.measureLazyList_LCrQqZ4$lambda$2((Placeable.PlacementScope) obj);
                    return measureLazyList_LCrQqZ4$lambda$2;
                }
            }), 0.0f, false, coroutineScope, density, lazyListMeasuredItemProvider.getChildConstraints(), CollectionsKt.emptyList(), -i22, i2 + i4, 0, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5, null);
        }
        int i23 = i6;
        if (i23 >= i) {
            i23 = i - 1;
            i9 = 0;
        } else {
            i9 = i7;
        }
        int round = Math.round(f);
        int i24 = i9 - round;
        if (i23 == 0 && i24 < 0) {
            round += i24;
            i24 = 0;
        }
        int i25 = round;
        ArrayDeque arrayDeque = new ArrayDeque();
        int i26 = -i22;
        int i27 = (i5 < 0 ? i5 : 0) + i26;
        int i28 = i24 + i27;
        int i29 = 0;
        while (i28 < 0 && i23 > 0) {
            int i30 = i23 - 1;
            int i31 = i27;
            LazyListMeasuredItem m1007getAndMeasure0kLqBqw$default = LazyListMeasuredItemProvider.m1007getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i30, 0L, 2, null);
            arrayDeque.add(0, m1007getAndMeasure0kLqBqw$default);
            i29 = Math.max(i29, m1007getAndMeasure0kLqBqw$default.getCrossAxisSize());
            i28 = m1007getAndMeasure0kLqBqw$default.getMainAxisSizeWithSpacings() + i28;
            i23 = i30;
            i27 = i31;
            i26 = i26;
            i25 = i25;
        }
        int i32 = i28;
        int i33 = i25;
        int i34 = i26;
        int i35 = i27;
        int i36 = i29;
        if (i32 < i35) {
            i11 = i33 - (i35 - i32);
            i10 = i35;
        } else {
            i10 = i32;
            i11 = i33;
        }
        int i37 = i10 - i35;
        int i38 = i2 + i4;
        int coerceAtLeast = RangesKt.coerceAtLeast(i38, 0);
        int i39 = -i37;
        int i40 = i23;
        int i41 = 0;
        boolean z6 = false;
        while (i41 < arrayDeque.size()) {
            if (i39 >= coerceAtLeast) {
                arrayDeque.remove(i41);
                Unit unit = Unit.INSTANCE;
                z6 = true;
            } else {
                i40++;
                i39 += ((LazyListMeasuredItem) arrayDeque.get(i41)).getMainAxisSizeWithSpacings();
                Integer.valueOf(i41);
                i41++;
            }
        }
        int i42 = i23;
        int i43 = i36;
        int i44 = i37;
        int i45 = i39;
        boolean z7 = z6;
        int i46 = i38;
        int i47 = i40;
        while (i47 < i && (i45 < coerceAtLeast || i45 <= 0 || arrayDeque.isEmpty())) {
            int i48 = i46;
            int i49 = i45;
            int i50 = coerceAtLeast;
            int i51 = i42;
            int i52 = i44;
            int i53 = i43;
            LazyListMeasuredItem m1007getAndMeasure0kLqBqw$default2 = LazyListMeasuredItemProvider.m1007getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i47, 0L, 2, null);
            int i54 = i47;
            int mainAxisSizeWithSpacings = m1007getAndMeasure0kLqBqw$default2.getMainAxisSizeWithSpacings() + i49;
            if (mainAxisSizeWithSpacings <= i35 && i54 != i - 1) {
                i21 = i54 + 1;
                i44 = i52 - m1007getAndMeasure0kLqBqw$default2.getMainAxisSizeWithSpacings();
                Unit unit2 = Unit.INSTANCE;
                z7 = true;
            } else {
                int max = Math.max(i53, m1007getAndMeasure0kLqBqw$default2.getCrossAxisSize());
                Boolean.valueOf(arrayDeque.add(m1007getAndMeasure0kLqBqw$default2));
                i44 = i52;
                i53 = max;
                i21 = i51;
            }
            i45 = mainAxisSizeWithSpacings;
            i46 = i48;
            i47 = i54 + 1;
            i43 = i53;
            i42 = i21;
            coerceAtLeast = i50;
        }
        int i55 = i46;
        int i56 = i42;
        int i57 = i44;
        int i58 = i43;
        int i59 = i47;
        int i60 = i45;
        if (i60 < i2) {
            int i61 = i2 - i60;
            int i62 = i60 + i61;
            int i63 = i57 - i61;
            i14 = i56;
            while (i63 < i22 && i14 > 0) {
                i14--;
                int i64 = i63;
                LazyListMeasuredItem m1007getAndMeasure0kLqBqw$default3 = LazyListMeasuredItemProvider.m1007getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i14, 0L, 2, null);
                arrayDeque.add(0, m1007getAndMeasure0kLqBqw$default3);
                i58 = Math.max(i58, m1007getAndMeasure0kLqBqw$default3.getCrossAxisSize());
                i63 = i64 + m1007getAndMeasure0kLqBqw$default3.getMainAxisSizeWithSpacings();
                i59 = i59;
                i22 = i3;
            }
            i12 = i59;
            int i65 = i63;
            i13 = 0;
            lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
            i15 = i61 + i11;
            if (i65 < 0) {
                i15 += i65;
                i16 = i62 + i65;
                i17 = 0;
            } else {
                i16 = i62;
                i17 = i65;
            }
        } else {
            i12 = i59;
            i13 = 0;
            lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
            i14 = i56;
            i15 = i11;
            i16 = i60;
            i17 = i57;
        }
        float f2 = (MathKt.getSign(Math.round(f)) != MathKt.getSign(i15) || Math.abs(Math.round(f)) < Math.abs(i15)) ? f : i15;
        float f3 = f - f2;
        float f4 = 0.0f;
        if (z4 && i15 > i11 && f3 <= 0.0f) {
            f4 = (i15 - i11) + f3;
        }
        float f5 = f4;
        if ((i17 >= 0 ? 1 : i13) == 0) {
            InlineClassHelperKt.throwIllegalArgumentException("negative currentFirstItemScrollOffset");
        }
        int i66 = -i17;
        LazyListMeasuredItem lazyListMeasuredItem4 = (LazyListMeasuredItem) arrayDeque.first();
        if (i3 > 0 || i5 < 0) {
            int size = arrayDeque.size();
            int i67 = i13;
            while (i67 < size) {
                int mainAxisSizeWithSpacings2 = ((LazyListMeasuredItem) arrayDeque.get(i67)).getMainAxisSizeWithSpacings();
                if (i17 == 0 || mainAxisSizeWithSpacings2 > i17) {
                    break;
                }
                lazyListMeasuredItem = lazyListMeasuredItem4;
                if (i67 == CollectionsKt.getLastIndex(arrayDeque)) {
                    break;
                }
                i17 -= mainAxisSizeWithSpacings2;
                i67++;
                lazyListMeasuredItem4 = (LazyListMeasuredItem) arrayDeque.get(i67);
            }
            lazyListMeasuredItem = lazyListMeasuredItem4;
            i18 = i17;
            lazyListMeasuredItem2 = lazyListMeasuredItem;
        } else {
            i18 = i17;
            lazyListMeasuredItem2 = lazyListMeasuredItem4;
        }
        List<LazyListMeasuredItem> createItemsBeforeList = createItemsBeforeList(i14, lazyListMeasuredItemProvider2, i8, list);
        int size2 = createItemsBeforeList.size();
        int i68 = i58;
        for (int i69 = 0; i69 < size2; i69++) {
            i68 = Math.max(i68, createItemsBeforeList.get(i69).getCrossAxisSize());
        }
        ArrayDeque arrayDeque2 = arrayDeque;
        List<LazyListMeasuredItem> createItemsAfterList = createItemsAfterList(arrayDeque2, lazyListMeasuredItemProvider, i, i8, list, f2, z4, lazyListLayoutInfo);
        int size3 = createItemsAfterList.size();
        int i70 = i68;
        for (int i71 = 0; i71 < size3; i71++) {
            i70 = Math.max(i70, createItemsAfterList.get(i71).getCrossAxisSize());
        }
        boolean z8 = Intrinsics.areEqual(lazyListMeasuredItem2, arrayDeque.first()) && createItemsBeforeList.isEmpty() && createItemsAfterList.isEmpty();
        int m7359constrainWidthK40F9xA = ConstraintsKt.m7359constrainWidthK40F9xA(j, z ? i70 : i16);
        if (z) {
            i70 = i16;
        }
        int m7358constrainHeightK40F9xA = ConstraintsKt.m7358constrainHeightK40F9xA(j, i70);
        float f6 = f2;
        List<LazyListMeasuredItem> calculateItemsOffsets = calculateItemsOffsets(arrayDeque2, createItemsBeforeList, createItemsAfterList, m7359constrainWidthK40F9xA, m7358constrainHeightK40F9xA, i16, i2, i66, z, vertical, horizontal, z2, density);
        LazyListMeasuredItem lazyListMeasuredItem5 = lazyListMeasuredItem2;
        lazyLayoutItemAnimator.onMeasured((int) f6, m7359constrainWidthK40F9xA, m7358constrainHeightK40F9xA, calculateItemsOffsets, lazyListMeasuredItemProvider.getKeyIndexMap(), lazyListMeasuredItemProvider, z, z4, 1, z3, i18, i16, coroutineScope, graphicsContext);
        int i72 = m7359constrainWidthK40F9xA;
        int i73 = i16;
        if (!z4) {
            long m1075getMinSizeToFitDisappearingItemsYbymL2g2 = lazyLayoutItemAnimator.m1075getMinSizeToFitDisappearingItemsYbymL2g();
            if (!IntSize.m7572equalsimpl0(m1075getMinSizeToFitDisappearingItemsYbymL2g2, IntSize.INSTANCE.m7579getZeroYbymL2g())) {
                int i74 = z ? m7358constrainHeightK40F9xA : i72;
                i72 = ConstraintsKt.m7359constrainWidthK40F9xA(j, Math.max(i72, (int) (m1075getMinSizeToFitDisappearingItemsYbymL2g2 >> 32)));
                int m7358constrainHeightK40F9xA2 = ConstraintsKt.m7358constrainHeightK40F9xA(j, Math.max(m7358constrainHeightK40F9xA, (int) (m1075getMinSizeToFitDisappearingItemsYbymL2g2 & 4294967295L)));
                int i75 = z ? m7358constrainHeightK40F9xA2 : i72;
                if (i75 != i74) {
                    int size4 = calculateItemsOffsets.size();
                    for (int i76 = 0; i76 < size4; i76++) {
                        calculateItemsOffsets.get(i76).updateMainAxisLayoutSize(i75);
                    }
                }
                i19 = m7358constrainHeightK40F9xA2;
                int i77 = i72;
                lazyListMeasuredItem3 = (LazyListMeasuredItem) arrayDeque.firstOrNull();
                if (lazyListMeasuredItem3 == null) {
                    list2 = calculateItemsOffsets;
                    i20 = lazyListMeasuredItem3.getIndex();
                } else {
                    list2 = calculateItemsOffsets;
                    i20 = 0;
                }
                LazyListMeasuredItem lazyListMeasuredItem6 = (LazyListMeasuredItem) arrayDeque.lastOrNull();
                final List<LazyListMeasuredItem> list3 = list2;
                final List applyStickyItems = LazyLayoutStickyItemsKt.applyStickyItems(stickyItemsPlacement, i20, lazyListMeasuredItem6 == null ? lazyListMeasuredItem6.getIndex() : 0, list3, lazyListMeasuredItemProvider.getHeaderIndexes(), i3, i4, i77, i19, new Function1() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LazyListMeasuredItem measureLazyList_LCrQqZ4$lambda$7;
                        measureLazyList_LCrQqZ4$lambda$7 = LazyListMeasureKt.measureLazyList_LCrQqZ4$lambda$7(LazyListMeasuredItemProvider.this, ((Integer) obj).intValue());
                        return measureLazyList_LCrQqZ4$lambda$7;
                    }
                });
                Integer num = null;
                if (!z8) {
                    LazyListMeasuredItem lazyListMeasuredItem7 = (LazyListMeasuredItem) CollectionsKt.firstOrNull((List) list3);
                    if (lazyListMeasuredItem7 != null) {
                        valueOf = Integer.valueOf(lazyListMeasuredItem7.getIndex());
                    }
                    valueOf = null;
                } else {
                    LazyListMeasuredItem lazyListMeasuredItem8 = (LazyListMeasuredItem) arrayDeque.firstOrNull();
                    if (lazyListMeasuredItem8 != null) {
                        valueOf = Integer.valueOf(lazyListMeasuredItem8.getIndex());
                    }
                    valueOf = null;
                }
                if (!z8) {
                    LazyListMeasuredItem lazyListMeasuredItem9 = (LazyListMeasuredItem) CollectionsKt.lastOrNull((List) list3);
                    if (lazyListMeasuredItem9 != null) {
                        num = Integer.valueOf(lazyListMeasuredItem9.getIndex());
                    }
                } else {
                    LazyListMeasuredItem lazyListMeasuredItem10 = (LazyListMeasuredItem) arrayDeque.lastOrNull();
                    if (lazyListMeasuredItem10 != null) {
                        num = Integer.valueOf(lazyListMeasuredItem10.getIndex());
                    }
                }
                if (i12 >= i && i73 <= i2) {
                    z5 = false;
                }
                return new LazyListMeasureResult(lazyListMeasuredItem5, i18, z5, f6, function3.invoke(Integer.valueOf(i77), Integer.valueOf(i19), new Function1() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit measureLazyList_LCrQqZ4$lambda$11;
                        measureLazyList_LCrQqZ4$lambda$11 = LazyListMeasureKt.measureLazyList_LCrQqZ4$lambda$11(MutableState.this, list3, applyStickyItems, z4, (Placeable.PlacementScope) obj);
                        return measureLazyList_LCrQqZ4$lambda$11;
                    }
                }), f5, z7, coroutineScope, density, lazyListMeasuredItemProvider.getChildConstraints(), LazyLayoutMeasuredItemKt.updatedVisibleItems(valueOf == null ? valueOf.intValue() : 0, num == null ? num.intValue() : 0, list3, applyStickyItems), i34, i55, i, z2, !z ? Orientation.Vertical : Orientation.Horizontal, i4, i5, null);
            }
        }
        i19 = m7358constrainHeightK40F9xA;
        int i772 = i72;
        lazyListMeasuredItem3 = (LazyListMeasuredItem) arrayDeque.firstOrNull();
        if (lazyListMeasuredItem3 == null) {
        }
        LazyListMeasuredItem lazyListMeasuredItem62 = (LazyListMeasuredItem) arrayDeque.lastOrNull();
        final List list32 = list2;
        final List applyStickyItems2 = LazyLayoutStickyItemsKt.applyStickyItems(stickyItemsPlacement, i20, lazyListMeasuredItem62 == null ? lazyListMeasuredItem62.getIndex() : 0, list32, lazyListMeasuredItemProvider.getHeaderIndexes(), i3, i4, i772, i19, new Function1() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LazyListMeasuredItem measureLazyList_LCrQqZ4$lambda$7;
                measureLazyList_LCrQqZ4$lambda$7 = LazyListMeasureKt.measureLazyList_LCrQqZ4$lambda$7(LazyListMeasuredItemProvider.this, ((Integer) obj).intValue());
                return measureLazyList_LCrQqZ4$lambda$7;
            }
        });
        Integer num2 = null;
        if (!z8) {
        }
        if (!z8) {
        }
        if (i12 >= i) {
            z5 = false;
        }
        if (!z) {
        }
        return new LazyListMeasureResult(lazyListMeasuredItem5, i18, z5, f6, function3.invoke(Integer.valueOf(i772), Integer.valueOf(i19), new Function1() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit measureLazyList_LCrQqZ4$lambda$11;
                measureLazyList_LCrQqZ4$lambda$11 = LazyListMeasureKt.measureLazyList_LCrQqZ4$lambda$11(MutableState.this, list32, applyStickyItems2, z4, (Placeable.PlacementScope) obj);
                return measureLazyList_LCrQqZ4$lambda$11;
            }
        }), f5, z7, coroutineScope, density, lazyListMeasuredItemProvider.getChildConstraints(), LazyLayoutMeasuredItemKt.updatedVisibleItems(valueOf == null ? valueOf.intValue() : 0, num2 == null ? num2.intValue() : 0, list32, applyStickyItems2), i34, i55, i, z2, !z ? Orientation.Vertical : Orientation.Horizontal, i4, i5, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measureLazyList_LCrQqZ4$lambda$2(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazyListMeasuredItem measureLazyList_LCrQqZ4$lambda$7(LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i) {
        return LazyListMeasuredItemProvider.m1007getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i, 0L, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measureLazyList_LCrQqZ4$lambda$11(MutableState mutableState, final List list, final List list2, final boolean z, Placeable.PlacementScope placementScope) {
        placementScope.withMotionFrameOfReferencePlacement(new Function1() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit measureLazyList_LCrQqZ4$lambda$11$lambda$10;
                measureLazyList_LCrQqZ4$lambda$11$lambda$10 = LazyListMeasureKt.measureLazyList_LCrQqZ4$lambda$11$lambda$10(list, list2, z, (Placeable.PlacementScope) obj);
                return measureLazyList_LCrQqZ4$lambda$11$lambda$10;
            }
        });
        ObservableScopeInvalidator.m1107attachToScopeimpl(mutableState);
        return Unit.INSTANCE;
    }

    private static final List<LazyListMeasuredItem> createItemsAfterList(List<LazyListMeasuredItem> list, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i, int i2, List<Integer> list2, float f, boolean z, LazyListLayoutInfo lazyListLayoutInfo) {
        ArrayList arrayList;
        LazyListItemInfo lazyListItemInfo;
        LazyListMeasuredItem lazyListMeasuredItem;
        LazyListMeasuredItem lazyListMeasuredItem2;
        int mainAxisSizeWithSpacings;
        LazyListMeasuredItem lazyListMeasuredItem3;
        int index;
        int min;
        LazyListMeasuredItem lazyListMeasuredItem4;
        LazyListMeasuredItem lazyListMeasuredItem5;
        int i3 = i - 1;
        int min2 = Math.min(((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex() + i2, i3);
        int index2 = ((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex() + 1;
        if (index2 <= min2) {
            int i4 = index2;
            arrayList = null;
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(LazyListMeasuredItemProvider.m1007getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i4, 0L, 2, null));
                if (i4 == min2) {
                    break;
                }
                i4++;
            }
        } else {
            arrayList = null;
        }
        if (z && lazyListLayoutInfo != null && !lazyListLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            List<LazyListItemInfo> visibleItemsInfo = lazyListLayoutInfo.getVisibleItemsInfo();
            int size = visibleItemsInfo.size();
            while (true) {
                size--;
                if (-1 >= size) {
                    lazyListItemInfo = null;
                    break;
                }
                if (visibleItemsInfo.get(size).getIndex() <= min2 || (size != 0 && visibleItemsInfo.get(size - 1).getIndex() > min2)) {
                }
            }
            lazyListItemInfo = visibleItemsInfo.get(size);
            LazyListItemInfo lazyListItemInfo2 = (LazyListItemInfo) CollectionsKt.last((List) lazyListLayoutInfo.getVisibleItemsInfo());
            if (lazyListItemInfo != null && (index = lazyListItemInfo.getIndex()) <= (min = Math.min(lazyListItemInfo2.getIndex(), i3))) {
                int i5 = index;
                while (true) {
                    if (arrayList != null) {
                        int size2 = arrayList.size();
                        int i6 = 0;
                        while (true) {
                            if (i6 >= size2) {
                                lazyListMeasuredItem5 = null;
                                break;
                            }
                            lazyListMeasuredItem5 = arrayList.get(i6);
                            if (lazyListMeasuredItem5.getIndex() == i5) {
                                break;
                            }
                            i6++;
                        }
                        lazyListMeasuredItem4 = lazyListMeasuredItem5;
                    } else {
                        lazyListMeasuredItem4 = null;
                    }
                    if (lazyListMeasuredItem4 == null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(LazyListMeasuredItemProvider.m1007getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i5, 0L, 2, null));
                    }
                    if (i5 == min) {
                        break;
                    }
                    i5++;
                }
            }
            float viewportEndOffset = ((lazyListLayoutInfo.getViewportEndOffset() - lazyListItemInfo2.getOffset()) - lazyListItemInfo2.getSize()) - f;
            if (viewportEndOffset > 0.0f) {
                int index3 = lazyListItemInfo2.getIndex() + 1;
                int i7 = 0;
                while (index3 < i && i7 < viewportEndOffset) {
                    if (index3 <= min2) {
                        int size3 = list.size();
                        int i8 = 0;
                        while (true) {
                            if (i8 >= size3) {
                                lazyListMeasuredItem3 = null;
                                break;
                            }
                            lazyListMeasuredItem3 = list.get(i8);
                            if (lazyListMeasuredItem3.getIndex() == index3) {
                                break;
                            }
                            i8++;
                        }
                        lazyListMeasuredItem = lazyListMeasuredItem3;
                    } else if (arrayList != null) {
                        int size4 = arrayList.size();
                        int i9 = 0;
                        while (true) {
                            if (i9 >= size4) {
                                lazyListMeasuredItem2 = null;
                                break;
                            }
                            lazyListMeasuredItem2 = arrayList.get(i9);
                            if (lazyListMeasuredItem2.getIndex() == index3) {
                                break;
                            }
                            i9++;
                        }
                        lazyListMeasuredItem = lazyListMeasuredItem2;
                    } else {
                        lazyListMeasuredItem = null;
                    }
                    if (lazyListMeasuredItem != null) {
                        index3++;
                        mainAxisSizeWithSpacings = lazyListMeasuredItem.getMainAxisSizeWithSpacings();
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(LazyListMeasuredItemProvider.m1007getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, index3, 0L, 2, null));
                        index3++;
                        mainAxisSizeWithSpacings = ((LazyListMeasuredItem) CollectionsKt.last((List) arrayList)).getMainAxisSizeWithSpacings();
                    }
                    i7 += mainAxisSizeWithSpacings;
                }
            }
        }
        if (arrayList != null && ((LazyListMeasuredItem) CollectionsKt.last((List) arrayList)).getIndex() > min2) {
            min2 = ((LazyListMeasuredItem) CollectionsKt.last((List) arrayList)).getIndex();
        }
        int size5 = list2.size();
        for (int i10 = 0; i10 < size5; i10++) {
            int intValue = list2.get(i10).intValue();
            if (intValue > min2) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(LazyListMeasuredItemProvider.m1007getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, intValue, 0L, 2, null));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyListMeasuredItem> createItemsBeforeList(int i, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i2, List<Integer> list) {
        LazyListMeasuredItemProvider lazyListMeasuredItemProvider2;
        int max = Math.max(0, i - i2);
        int i3 = i - 1;
        ArrayList arrayList = null;
        if (max <= i3) {
            int i4 = i3;
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
                arrayList.add(LazyListMeasuredItemProvider.m1007getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider2, i4, 0L, 2, null));
                if (i4 == max) {
                    break;
                }
                i4--;
                lazyListMeasuredItemProvider = lazyListMeasuredItemProvider2;
            }
        } else {
            lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
        }
        int size = list.size() - 1;
        if (size >= 0) {
            while (true) {
                int i5 = size - 1;
                int intValue = list.get(size).intValue();
                if (intValue < max) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(LazyListMeasuredItemProvider.m1007getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider2, intValue, 0L, 2, null));
                }
                if (i5 < 0) {
                    break;
                }
                size = i5;
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyListMeasuredItem> calculateItemsOffsets(List<LazyListMeasuredItem> list, List<LazyListMeasuredItem> list2, List<LazyListMeasuredItem> list3, int i, int i2, int i3, int i4, int i5, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density) {
        int i6 = z ? i2 : i;
        boolean z3 = i3 < Math.min(i6, i4);
        if (z3) {
            if (!(i5 == 0)) {
                InlineClassHelperKt.throwIllegalStateException("non-zero itemsScrollOffset");
            }
        }
        ArrayList arrayList = new ArrayList(list.size() + list2.size() + list3.size());
        if (z3) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                InlineClassHelperKt.throwIllegalArgumentException("no extra items");
            }
            int size = list.size();
            int[] iArr = new int[size];
            for (int i7 = 0; i7 < size; i7++) {
                iArr[i7] = list.get(calculateItemsOffsets$reverseAware(i7, z2, size)).getSize();
            }
            int[] iArr2 = new int[size];
            if (z) {
                if (vertical != null) {
                    vertical.arrange(density, i6, iArr, iArr2);
                } else {
                    InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null verticalArrangement when isVertical == true");
                    throw new KotlinNothingValueException();
                }
            } else if (horizontal != null) {
                horizontal.arrange(density, i6, iArr, LayoutDirection.Ltr, iArr2);
            } else {
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null horizontalArrangement when isVertical == false");
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
                    int i8 = iArr2[first];
                    LazyListMeasuredItem lazyListMeasuredItem = list.get(calculateItemsOffsets$reverseAware(first, z2, size));
                    if (z2) {
                        i8 = (i6 - i8) - lazyListMeasuredItem.getSize();
                    }
                    lazyListMeasuredItem.position(i8, i, i2);
                    arrayList.add(lazyListMeasuredItem);
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            int size2 = list2.size();
            int i9 = i5;
            for (int i10 = 0; i10 < size2; i10++) {
                LazyListMeasuredItem lazyListMeasuredItem2 = list2.get(i10);
                i9 -= lazyListMeasuredItem2.getMainAxisSizeWithSpacings();
                lazyListMeasuredItem2.position(i9, i, i2);
                arrayList.add(lazyListMeasuredItem2);
            }
            int size3 = list.size();
            int i11 = i5;
            for (int i12 = 0; i12 < size3; i12++) {
                LazyListMeasuredItem lazyListMeasuredItem3 = list.get(i12);
                lazyListMeasuredItem3.position(i11, i, i2);
                arrayList.add(lazyListMeasuredItem3);
                i11 += lazyListMeasuredItem3.getMainAxisSizeWithSpacings();
            }
            int size4 = list3.size();
            for (int i13 = 0; i13 < size4; i13++) {
                LazyListMeasuredItem lazyListMeasuredItem4 = list3.get(i13);
                lazyListMeasuredItem4.position(i11, i, i2);
                arrayList.add(lazyListMeasuredItem4);
                i11 += lazyListMeasuredItem4.getMainAxisSizeWithSpacings();
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measureLazyList_LCrQqZ4$lambda$11$lambda$10(List list, List list2, boolean z, Placeable.PlacementScope placementScope) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((LazyListMeasuredItem) list.get(i)).place(placementScope, z);
        }
        int size2 = list2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((LazyListMeasuredItem) list2.get(i2)).place(placementScope, z);
        }
        return Unit.INSTANCE;
    }
}
