package androidx.compose.foundation.pager;

import androidx.collection.IntObjectMapKt;
import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PagerMeasurePolicy.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 implements LazyLayoutMeasurePolicy {
    final /* synthetic */ int $beyondViewportPageCount;
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ CoroutineScope $coroutineScope;
    final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
    final /* synthetic */ Function0<PagerLazyLayoutItemProvider> $itemProviderLambda;
    final /* synthetic */ Orientation $orientation;
    final /* synthetic */ Function0<Integer> $pageCount;
    final /* synthetic */ PageSize $pageSize;
    final /* synthetic */ float $pageSpacing;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ SnapPosition $snapPosition;
    final /* synthetic */ PagerState $state;
    final /* synthetic */ Alignment.Vertical $verticalAlignment;

    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(PagerState pagerState, Orientation orientation, PaddingValues paddingValues, boolean z, float f, PageSize pageSize, Function0<PagerLazyLayoutItemProvider> function0, Function0<Integer> function02, Alignment.Vertical vertical, Alignment.Horizontal horizontal, int i, SnapPosition snapPosition, CoroutineScope coroutineScope) {
        this.$state = pagerState;
        this.$orientation = orientation;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z;
        this.$pageSpacing = f;
        this.$pageSize = pageSize;
        this.$itemProviderLambda = function0;
        this.$pageCount = function02;
        this.$verticalAlignment = vertical;
        this.$horizontalAlignment = horizontal;
        this.$beyondViewportPageCount = i;
        this.$snapPosition = snapPosition;
        this.$coroutineScope = coroutineScope;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
    /* renamed from: measure-0kLqBqw */
    public final MeasureResult mo993measure0kLqBqw(final LazyLayoutMeasureScope lazyLayoutMeasureScope, final long j) {
        int i;
        int i2;
        int i3;
        int m7342getMaxWidthimpl;
        int i4;
        long m7525constructorimpl;
        ObservableScopeInvalidator.m1107attachToScopeimpl(this.$state.m1194getMeasurementScopeInvalidatorzYiylxw$foundation_release());
        boolean z = this.$orientation == Orientation.Vertical;
        CheckScrollableContainerConstraintsKt.m329checkScrollableContainerConstraintsK40F9xA(j, z ? Orientation.Vertical : Orientation.Horizontal);
        if (z) {
            i = lazyLayoutMeasureScope.mo453roundToPx0680j_4(this.$contentPadding.mo822calculateLeftPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection()));
        } else {
            i = lazyLayoutMeasureScope.mo453roundToPx0680j_4(PaddingKt.calculateStartPadding(this.$contentPadding, lazyLayoutMeasureScope.getLayoutDirection()));
        }
        if (z) {
            i2 = lazyLayoutMeasureScope.mo453roundToPx0680j_4(this.$contentPadding.mo823calculateRightPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection()));
        } else {
            i2 = lazyLayoutMeasureScope.mo453roundToPx0680j_4(PaddingKt.calculateEndPadding(this.$contentPadding, lazyLayoutMeasureScope.getLayoutDirection()));
        }
        int i5 = lazyLayoutMeasureScope.mo453roundToPx0680j_4(this.$contentPadding.getTop());
        int i6 = lazyLayoutMeasureScope.mo453roundToPx0680j_4(this.$contentPadding.getBottom());
        int i7 = i2;
        int i8 = i5 + i6;
        final int i9 = i + i7;
        int i10 = z ? i8 : i9;
        if (z && !this.$reverseLayout) {
            i3 = i5;
        } else if (z && this.$reverseLayout) {
            i3 = i6;
        } else {
            i3 = (z || this.$reverseLayout) ? i7 : i;
        }
        int i11 = i10 - i3;
        long m7361offsetNN6EwU = ConstraintsKt.m7361offsetNN6EwU(j, -i9, -i8);
        LazyLayoutMeasureScope lazyLayoutMeasureScope2 = lazyLayoutMeasureScope;
        this.$state.setDensity$foundation_release(lazyLayoutMeasureScope2);
        int i12 = lazyLayoutMeasureScope.mo453roundToPx0680j_4(this.$pageSpacing);
        if (z) {
            m7342getMaxWidthimpl = Constraints.m7341getMaxHeightimpl(j) - i8;
        } else {
            m7342getMaxWidthimpl = Constraints.m7342getMaxWidthimpl(j) - i9;
        }
        if (!this.$reverseLayout || m7342getMaxWidthimpl > 0) {
            i4 = i8;
            m7525constructorimpl = IntOffset.m7525constructorimpl((i << 32) | (i5 & 4294967295L));
        } else {
            if (!z) {
                i += m7342getMaxWidthimpl;
            }
            if (z) {
                i5 += m7342getMaxWidthimpl;
            }
            i4 = i8;
            m7525constructorimpl = IntOffset.m7525constructorimpl((i5 & 4294967295L) | (i << 32));
        }
        int coerceAtLeast = RangesKt.coerceAtLeast(this.$pageSize.calculateMainAxisPageSize(lazyLayoutMeasureScope2, m7342getMaxWidthimpl, i12), 0);
        this.$state.m1198setPremeasureConstraintsBRTryo0$foundation_release(ConstraintsKt.Constraints$default(0, this.$orientation == Orientation.Vertical ? Constraints.m7342getMaxWidthimpl(m7361offsetNN6EwU) : coerceAtLeast, 0, this.$orientation != Orientation.Vertical ? Constraints.m7341getMaxHeightimpl(m7361offsetNN6EwU) : coerceAtLeast, 5, null));
        PagerLazyLayoutItemProvider invoke = this.$itemProviderLambda.invoke();
        int i13 = m7342getMaxWidthimpl + i3 + i11;
        Snapshot.Companion companion = Snapshot.INSTANCE;
        PagerState pagerState = this.$state;
        long j2 = m7525constructorimpl;
        SnapPosition snapPosition = this.$snapPosition;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            int matchScrollPositionWithKey$foundation_release = pagerState.matchScrollPositionWithKey$foundation_release(invoke, pagerState.getCurrentPage());
            int i14 = m7342getMaxWidthimpl;
            int currentPageOffset = PagerKt.currentPageOffset(snapPosition, i13, coerceAtLeast, i12, i3, i11, pagerState.getCurrentPage(), pagerState.getCurrentPageOffsetFraction(), pagerState.getPageCount());
            Unit unit = Unit.INSTANCE;
            companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
            final int i15 = i4;
            PagerMeasureResult m1189measurePagerBiYVr7A = PagerMeasureKt.m1189measurePagerBiYVr7A(lazyLayoutMeasureScope, this.$pageCount.invoke().intValue(), invoke, i14, i3, i11, i12, matchScrollPositionWithKey$foundation_release, currentPageOffset, m7361offsetNN6EwU, this.$orientation, this.$verticalAlignment, this.$horizontalAlignment, this.$reverseLayout, j2, coerceAtLeast, this.$beyondViewportPageCount, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(invoke, this.$state.getPinnedPages(), this.$state.getBeyondBoundsInfo()), this.$snapPosition, this.$state.m1195getPlacementScopeInvalidatorzYiylxw$foundation_release(), this.$coroutineScope, new Function3() { // from class: androidx.compose.foundation.pager.PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    MeasureResult measure_0kLqBqw$lambda$2;
                    measure_0kLqBqw$lambda$2 = PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1.measure_0kLqBqw$lambda$2(LazyLayoutMeasureScope.this, j, i9, i15, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), (Function1) obj3);
                    return measure_0kLqBqw$lambda$2;
                }
            }, IntObjectMapKt.mutableIntObjectMapOf());
            PagerState.applyMeasureResult$foundation_release$default(this.$state, m1189measurePagerBiYVr7A, lazyLayoutMeasureScope.isLookingAhead(), false, 4, null);
            return m1189measurePagerBiYVr7A;
        } catch (Throwable th) {
            companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult measure_0kLqBqw$lambda$2(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j, int i, int i2, int i3, int i4, Function1 function1) {
        return lazyLayoutMeasureScope.layout(ConstraintsKt.m7359constrainWidthK40F9xA(j, i3 + i), ConstraintsKt.m7358constrainHeightK40F9xA(j, i4 + i2), MapsKt.emptyMap(), function1);
    }
}
