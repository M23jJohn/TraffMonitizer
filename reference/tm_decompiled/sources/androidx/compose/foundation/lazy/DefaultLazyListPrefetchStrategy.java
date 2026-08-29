package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.LazyListPrefetchScope;
import androidx.compose.foundation.lazy.LazyListPrefetchStrategy;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.NestedPrefetchScope;
import androidx.compose.foundation.lazy.layout.PrefetchScheduler;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: LazyListPrefetchStrategy.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0014\u0010\u0014\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0014\u0010\u0015\u001a\u00020\u000f*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0003H\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0002J\u0014\u0010\u0019\u001a\u00020\u0003*\u00020\u00132\u0006\u0010\u001a\u001a\u00020\nH\u0002J\u001c\u0010\u001b\u001a\u00020\u000f*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/lazy/DefaultLazyListPrefetchStrategy;", "Landroidx/compose/foundation/lazy/LazyListPrefetchStrategy;", "initialNestedPrefetchItemCount", "", "<init>", "(I)V", "indexToPrefetch", "currentPrefetchHandle", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "wasScrollingForward", "", "previousPassItemCount", "previousPassDelta", "", "onScroll", "", "Landroidx/compose/foundation/lazy/LazyListPrefetchScope;", "delta", "layoutInfo", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "onVisibleItemsUpdated", "onNestedPrefetch", "Landroidx/compose/foundation/lazy/layout/NestedPrefetchScope;", "firstVisibleItemIndex", "resetPrefetchState", "calculateIndexToPrefetch", "scrollingForward", "evaluatePrefetchForCancellation", "currentPrefetchingIndex", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class DefaultLazyListPrefetchStrategy implements LazyListPrefetchStrategy {
    private LazyLayoutPrefetchState.PrefetchHandle currentPrefetchHandle;
    private int indexToPrefetch;
    private final int initialNestedPrefetchItemCount;
    private float previousPassDelta;
    private int previousPassItemCount;
    private boolean wasScrollingForward;

    public DefaultLazyListPrefetchStrategy() {
        this(0, 1, null);
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public /* synthetic */ PrefetchScheduler getPrefetchScheduler() {
        return LazyListPrefetchStrategy.CC.$default$getPrefetchScheduler(this);
    }

    public DefaultLazyListPrefetchStrategy(int i) {
        this.initialNestedPrefetchItemCount = i;
        this.indexToPrefetch = -1;
        this.previousPassItemCount = -1;
    }

    public /* synthetic */ DefaultLazyListPrefetchStrategy(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 2 : i);
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public void onScroll(LazyListPrefetchScope lazyListPrefetchScope, float f, LazyListLayoutInfo lazyListLayoutInfo) {
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle2;
        if (!lazyListLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            boolean z = f < 0.0f;
            int calculateIndexToPrefetch = calculateIndexToPrefetch(lazyListLayoutInfo, z);
            if (calculateIndexToPrefetch >= 0 && calculateIndexToPrefetch < lazyListLayoutInfo.getTotalItemsCount()) {
                if (calculateIndexToPrefetch != this.indexToPrefetch) {
                    if (this.wasScrollingForward != z) {
                        resetPrefetchState();
                    }
                    this.wasScrollingForward = z;
                    this.indexToPrefetch = calculateIndexToPrefetch;
                    this.currentPrefetchHandle = LazyListPrefetchScope.CC.schedulePrefetch$default(lazyListPrefetchScope, calculateIndexToPrefetch, null, 2, null);
                }
                if (z) {
                    LazyListItemInfo lazyListItemInfo = (LazyListItemInfo) CollectionsKt.last((List) lazyListLayoutInfo.getVisibleItemsInfo());
                    if (((lazyListItemInfo.getOffset() + lazyListItemInfo.getSize()) + lazyListLayoutInfo.getMainAxisItemSpacing()) - lazyListLayoutInfo.getViewportEndOffset() < (-f) && (prefetchHandle2 = this.currentPrefetchHandle) != null) {
                        prefetchHandle2.markAsUrgent();
                    }
                } else if (lazyListLayoutInfo.getViewportStartOffset() - ((LazyListItemInfo) CollectionsKt.first((List) lazyListLayoutInfo.getVisibleItemsInfo())).getOffset() < f && (prefetchHandle = this.currentPrefetchHandle) != null) {
                    prefetchHandle.markAsUrgent();
                }
            }
        }
        this.previousPassDelta = f;
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public void onVisibleItemsUpdated(LazyListPrefetchScope lazyListPrefetchScope, LazyListLayoutInfo lazyListLayoutInfo) {
        evaluatePrefetchForCancellation(lazyListLayoutInfo, this.indexToPrefetch, this.wasScrollingForward);
        int totalItemsCount = lazyListLayoutInfo.getTotalItemsCount();
        int i = this.previousPassItemCount;
        if (i != -1 && this.previousPassDelta != 0.0f && i != totalItemsCount && !lazyListLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            int calculateIndexToPrefetch = calculateIndexToPrefetch(lazyListLayoutInfo, this.previousPassDelta < 0.0f);
            if (calculateIndexToPrefetch >= 0 && calculateIndexToPrefetch < totalItemsCount) {
                this.indexToPrefetch = calculateIndexToPrefetch;
                this.currentPrefetchHandle = LazyListPrefetchScope.CC.schedulePrefetch$default(lazyListPrefetchScope, calculateIndexToPrefetch, null, 2, null);
            }
        }
        this.previousPassItemCount = totalItemsCount;
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public void onNestedPrefetch(NestedPrefetchScope nestedPrefetchScope, int i) {
        int nestedPrefetchItemCount;
        if (nestedPrefetchScope.getNestedPrefetchItemCount() == -1) {
            nestedPrefetchItemCount = this.initialNestedPrefetchItemCount;
        } else {
            nestedPrefetchItemCount = nestedPrefetchScope.getNestedPrefetchItemCount();
        }
        for (int i2 = 0; i2 < nestedPrefetchItemCount; i2++) {
            nestedPrefetchScope.schedulePrecomposition(i + i2);
        }
    }

    private final void resetPrefetchState() {
        this.indexToPrefetch = -1;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle = this.currentPrefetchHandle;
        if (prefetchHandle != null) {
            prefetchHandle.cancel();
        }
        this.currentPrefetchHandle = null;
    }

    private final int calculateIndexToPrefetch(LazyListLayoutInfo lazyListLayoutInfo, boolean z) {
        if (z) {
            return ((LazyListItemInfo) CollectionsKt.last((List) lazyListLayoutInfo.getVisibleItemsInfo())).getIndex() + 1;
        }
        return ((LazyListItemInfo) CollectionsKt.first((List) lazyListLayoutInfo.getVisibleItemsInfo())).getIndex() - 1;
    }

    private final void evaluatePrefetchForCancellation(LazyListLayoutInfo lazyListLayoutInfo, int i, boolean z) {
        if (i == -1 || lazyListLayoutInfo.getVisibleItemsInfo().isEmpty() || i == calculateIndexToPrefetch(lazyListLayoutInfo, z)) {
            return;
        }
        resetPrefetchState();
    }
}
