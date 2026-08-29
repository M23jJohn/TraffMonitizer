package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridLaneInfo;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: LazyStaggeredGridLaneInfo.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0001\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005J\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005J\u0006\u0010\u0013\u001a\u00020\u0005J\u0006\u0010\u0014\u001a\u00020\u0005J\u0006\u0010\u0015\u001a\u00020\fJ\u0016\u0010\u0016\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005J\u0016\u0010\u0017\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005J\u000e\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0005J\u0018\u0010\u001a\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u0005J\u001a\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLaneInfo;", "", "<init>", "()V", "anchor", "", "lanes", "", "spannedItems", "Lkotlin/collections/ArrayDeque;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLaneInfo$SpannedItem;", "setLane", "", "itemIndex", "lane", "getLane", "assignedToLane", "", "targetLane", "upperBound", "lowerBound", "reset", "findPreviousItemIndex", "findNextItemIndex", "ensureValidIndex", "requestedIndex", "setGaps", "gaps", "getGaps", "ensureCapacity", "capacity", "newOffset", "SpannedItem", "Companion", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridLaneInfo {
    public static final int LaneFullSpan = -2;
    public static final int LaneUnset = -1;
    private static final int MaxCapacity = 131072;
    private int anchor;
    private int[] lanes = new int[16];
    private final ArrayDeque<SpannedItem> spannedItems = new ArrayDeque<>();
    public static final int $stable = 8;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LazyStaggeredGridLaneInfo.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLaneInfo$SpannedItem;", "", "index", "", "gaps", "", "<init>", "(I[I)V", "getIndex", "()I", "getGaps", "()[I", "setGaps", "([I)V", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final class SpannedItem {
        private int[] gaps;
        private final int index;

        public SpannedItem(int i, int[] iArr) {
            this.index = i;
            this.gaps = iArr;
        }

        public final int[] getGaps() {
            return this.gaps;
        }

        public final int getIndex() {
            return this.index;
        }

        public final void setGaps(int[] iArr) {
            this.gaps = iArr;
        }
    }

    public final void setLane(int itemIndex, int lane) {
        if (!(itemIndex >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("Negative lanes are not supported");
        }
        ensureValidIndex(itemIndex);
        this.lanes[itemIndex - this.anchor] = lane + 1;
    }

    public final int getLane(int itemIndex) {
        if (itemIndex < getAnchor() || itemIndex >= upperBound()) {
            return -1;
        }
        return this.lanes[itemIndex - this.anchor] - 1;
    }

    public final boolean assignedToLane(int itemIndex, int targetLane) {
        int lane = getLane(itemIndex);
        return lane == targetLane || lane == -1 || lane == -2;
    }

    public final int upperBound() {
        return this.anchor + this.lanes.length;
    }

    /* renamed from: lowerBound, reason: from getter */
    public final int getAnchor() {
        return this.anchor;
    }

    public final void reset() {
        ArraysKt.fill$default(this.lanes, 0, 0, 0, 6, (Object) null);
        this.spannedItems.clear();
    }

    public final int findPreviousItemIndex(int itemIndex, int targetLane) {
        do {
            itemIndex--;
            if (-1 >= itemIndex) {
                return -1;
            }
        } while (!assignedToLane(itemIndex, targetLane));
        return itemIndex;
    }

    public final int findNextItemIndex(int itemIndex, int targetLane) {
        int upperBound = upperBound();
        for (int i = itemIndex + 1; i < upperBound; i++) {
            if (assignedToLane(i, targetLane)) {
                return i;
            }
        }
        return upperBound();
    }

    public final void ensureValidIndex(int requestedIndex) {
        int i = this.anchor;
        int i2 = requestedIndex - i;
        if (i2 >= 0 && i2 < 131072) {
            ensureCapacity$default(this, i2 + 1, 0, 2, null);
        } else {
            int max = Math.max(requestedIndex - (this.lanes.length / 2), 0);
            this.anchor = max;
            int i3 = max - i;
            if (i3 >= 0) {
                int[] iArr = this.lanes;
                if (i3 < iArr.length) {
                    ArraysKt.copyInto(iArr, iArr, 0, i3, iArr.length);
                }
                int[] iArr2 = this.lanes;
                ArraysKt.fill(iArr2, 0, Math.max(0, iArr2.length - i3), this.lanes.length);
            } else {
                int i4 = -i3;
                int[] iArr3 = this.lanes;
                if (iArr3.length + i4 < 131072) {
                    ensureCapacity(iArr3.length + i4 + 1, i4);
                } else {
                    if (i4 < iArr3.length) {
                        ArraysKt.copyInto(iArr3, iArr3, i4, 0, iArr3.length - i4);
                    }
                    int[] iArr4 = this.lanes;
                    ArraysKt.fill(iArr4, 0, 0, Math.min(iArr4.length, i4));
                }
            }
        }
        while (!this.spannedItems.isEmpty() && this.spannedItems.first().getIndex() < getAnchor()) {
            this.spannedItems.removeFirst();
        }
        while (!this.spannedItems.isEmpty() && this.spannedItems.last().getIndex() > upperBound()) {
            this.spannedItems.removeLast();
        }
    }

    public final void setGaps(int itemIndex, int[] gaps) {
        ArrayDeque<SpannedItem> arrayDeque = this.spannedItems;
        final Integer valueOf = Integer.valueOf(itemIndex);
        int binarySearch = CollectionsKt.binarySearch(arrayDeque, 0, arrayDeque.size(), new Function1<SpannedItem, Integer>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridLaneInfo$setGaps$$inlined$binarySearchBy$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(LazyStaggeredGridLaneInfo.SpannedItem spannedItem) {
                return Integer.valueOf(ComparisonsKt.compareValues(Integer.valueOf(spannedItem.getIndex()), valueOf));
            }
        });
        if (binarySearch < 0) {
            if (gaps == null) {
                return;
            }
            this.spannedItems.add(-(binarySearch + 1), new SpannedItem(itemIndex, gaps));
            return;
        }
        if (gaps == null) {
            this.spannedItems.remove(binarySearch);
        } else {
            this.spannedItems.get(binarySearch).setGaps(gaps);
        }
    }

    public final int[] getGaps(int itemIndex) {
        ArrayDeque<SpannedItem> arrayDeque = this.spannedItems;
        final Integer valueOf = Integer.valueOf(itemIndex);
        SpannedItem spannedItem = (SpannedItem) CollectionsKt.getOrNull(this.spannedItems, CollectionsKt.binarySearch(arrayDeque, 0, arrayDeque.size(), new Function1<SpannedItem, Integer>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridLaneInfo$getGaps$$inlined$binarySearchBy$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(LazyStaggeredGridLaneInfo.SpannedItem spannedItem2) {
                return Integer.valueOf(ComparisonsKt.compareValues(Integer.valueOf(spannedItem2.getIndex()), valueOf));
            }
        }));
        if (spannedItem != null) {
            return spannedItem.getGaps();
        }
        return null;
    }

    static /* synthetic */ void ensureCapacity$default(LazyStaggeredGridLaneInfo lazyStaggeredGridLaneInfo, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        lazyStaggeredGridLaneInfo.ensureCapacity(i, i2);
    }

    private final void ensureCapacity(int capacity, int newOffset) {
        if (!(capacity <= 131072)) {
            InlineClassHelperKt.throwIllegalArgumentException("Requested item capacity " + capacity + " is larger than max supported: 131072!");
        }
        int[] iArr = this.lanes;
        if (iArr.length < capacity) {
            int length = iArr.length;
            while (length < capacity) {
                length *= 2;
            }
            this.lanes = ArraysKt.copyInto$default(this.lanes, new int[length], newOffset, 0, 0, 12, (Object) null);
        }
    }
}
