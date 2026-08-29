package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider;
import androidx.compose.foundation.lazy.layout.IntervalList;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* compiled from: LazyGridSpanLayoutProvider.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001:\u0003)*+B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0018\u001a\u00020\u000bH\u0002J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u000bJ\u000e\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000bJ\u0016\u0010%\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u000bJ\b\u0010'\u001a\u00020(H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\rR$\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u001f¨\u0006,"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;", "", "gridContent", "Landroidx/compose/foundation/lazy/grid/LazyGridIntervalContent;", "<init>", "(Landroidx/compose/foundation/lazy/grid/LazyGridIntervalContent;)V", "buckets", "Ljava/util/ArrayList;", "Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider$Bucket;", "Lkotlin/collections/ArrayList;", "bucketSize", "", "getBucketSize", "()I", "lastLineIndex", "lastLineStartItemIndex", "lastLineStartKnownSpan", "cachedBucketIndex", "cachedBucket", "", "previousDefaultSpans", "", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "getDefaultSpans", "currentSlotsPerLine", "totalSize", "getTotalSize", "value", "slotsPerLine", "getSlotsPerLine", "setSlotsPerLine", "(I)V", "getLineConfiguration", "Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider$LineConfiguration;", "lineIndex", "getLineIndexOfItem", "itemIndex", "spanOf", "maxSpan", "invalidateCache", "", "LineConfiguration", "Bucket", "LazyGridItemSpanScopeImpl", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyGridSpanLayoutProvider {
    public static final int $stable = 8;
    private final ArrayList<Bucket> buckets;
    private final List<Integer> cachedBucket;
    private int cachedBucketIndex;
    private final LazyGridIntervalContent gridContent;
    private int lastLineIndex;
    private int lastLineStartItemIndex;
    private int lastLineStartKnownSpan;
    private List<GridItemSpan> previousDefaultSpans;
    private int slotsPerLine;

    /* compiled from: LazyGridSpanLayoutProvider.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider$LineConfiguration;", "", "firstItemIndex", "", "spans", "", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "<init>", "(ILjava/util/List;)V", "getFirstItemIndex", "()I", "getSpans", "()Ljava/util/List;", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class LineConfiguration {
        public static final int $stable = 8;
        private final int firstItemIndex;
        private final List<GridItemSpan> spans;

        public LineConfiguration(int i, List<GridItemSpan> list) {
            this.firstItemIndex = i;
            this.spans = list;
        }

        public final int getFirstItemIndex() {
            return this.firstItemIndex;
        }

        public final List<GridItemSpan> getSpans() {
            return this.spans;
        }
    }

    public LazyGridSpanLayoutProvider(LazyGridIntervalContent lazyGridIntervalContent) {
        this.gridContent = lazyGridIntervalContent;
        ArrayList<Bucket> arrayList = new ArrayList<>();
        int i = 0;
        arrayList.add(new Bucket(i, i, 2, null));
        this.buckets = arrayList;
        this.cachedBucketIndex = -1;
        this.cachedBucket = new ArrayList();
        this.previousDefaultSpans = CollectionsKt.emptyList();
    }

    private final int getBucketSize() {
        return ((int) Math.sqrt((getTotalSize() * 1.0d) / this.slotsPerLine)) + 1;
    }

    private final List<GridItemSpan> getDefaultSpans(int currentSlotsPerLine) {
        if (currentSlotsPerLine == this.previousDefaultSpans.size()) {
            return this.previousDefaultSpans;
        }
        ArrayList arrayList = new ArrayList(currentSlotsPerLine);
        for (int i = 0; i < currentSlotsPerLine; i++) {
            arrayList.add(GridItemSpan.m1017boximpl(LazyGridSpanKt.GridItemSpan(1)));
        }
        ArrayList arrayList2 = arrayList;
        this.previousDefaultSpans = arrayList2;
        return arrayList2;
    }

    public final int getTotalSize() {
        return this.gridContent.getIntervals().getSize();
    }

    public final int getSlotsPerLine() {
        return this.slotsPerLine;
    }

    public final void setSlotsPerLine(int i) {
        if (i != this.slotsPerLine) {
            this.slotsPerLine = i;
            invalidateCache();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f2 A[LOOP:1: B:29:0x00d4->B:37:0x00f2, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f6 A[EDGE_INSN: B:38:0x00f6->B:39:0x00f6 BREAK  A[LOOP:1: B:29:0x00d4->B:37:0x00f2], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0155 A[LOOP:2: B:61:0x0138->B:69:0x0155, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0164 A[EDGE_INSN: B:70:0x0164->B:71:0x0164 BREAK  A[LOOP:2: B:61:0x0138->B:69:0x0155], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final LineConfiguration getLineConfiguration(int lineIndex) {
        int i;
        boolean z;
        int i2;
        int i3;
        int i4;
        int i5;
        if (!this.gridContent.getHasCustomSpans()) {
            int i6 = lineIndex * this.slotsPerLine;
            return new LineConfiguration(i6, getDefaultSpans(RangesKt.coerceAtLeast(RangesKt.coerceAtMost(this.slotsPerLine, getTotalSize() - i6), 0)));
        }
        int min = Math.min(lineIndex / getBucketSize(), this.buckets.size() - 1);
        int bucketSize = getBucketSize() * min;
        int firstItemIndex = this.buckets.get(min).getFirstItemIndex();
        int firstItemKnownSpan = this.buckets.get(min).getFirstItemKnownSpan();
        int i7 = this.lastLineIndex;
        if (bucketSize <= i7 && i7 <= lineIndex) {
            firstItemIndex = this.lastLineStartItemIndex;
            firstItemKnownSpan = this.lastLineStartKnownSpan;
            bucketSize = i7;
        } else if (min == this.cachedBucketIndex && (i = lineIndex - bucketSize) < this.cachedBucket.size()) {
            firstItemIndex = this.cachedBucket.get(i).intValue();
            bucketSize = lineIndex;
            firstItemKnownSpan = 0;
        }
        if (bucketSize % getBucketSize() == 0) {
            int bucketSize2 = getBucketSize();
            int i8 = lineIndex - bucketSize;
            if (2 <= i8 && i8 < bucketSize2) {
                z = true;
                if (z) {
                    this.cachedBucketIndex = min;
                    this.cachedBucket.clear();
                }
                if (!(bucketSize > lineIndex)) {
                    InlineClassHelperKt.throwIllegalStateException("currentLine (" + bucketSize + ") > lineIndex (" + lineIndex + ')');
                }
                while (bucketSize < lineIndex && firstItemIndex < getTotalSize()) {
                    if (z) {
                        this.cachedBucket.add(Integer.valueOf(firstItemIndex));
                    }
                    i4 = 0;
                    while (i4 < this.slotsPerLine && firstItemIndex < getTotalSize()) {
                        if (firstItemKnownSpan != 0) {
                            i5 = firstItemKnownSpan;
                            firstItemKnownSpan = spanOf(firstItemIndex, this.slotsPerLine - i4);
                        } else {
                            i5 = 0;
                        }
                        i4 += firstItemKnownSpan;
                        if (i4 <= this.slotsPerLine) {
                            break;
                        }
                        firstItemIndex++;
                        firstItemKnownSpan = i5;
                    }
                    bucketSize++;
                    if (bucketSize % getBucketSize() == 0 && firstItemIndex < getTotalSize()) {
                        if (!(this.buckets.size() != bucketSize / getBucketSize())) {
                            InlineClassHelperKt.throwIllegalStateException("invalid starting point");
                        }
                        this.buckets.add(new Bucket(firstItemIndex, firstItemKnownSpan));
                    }
                }
                this.lastLineIndex = lineIndex;
                this.lastLineStartItemIndex = firstItemIndex;
                this.lastLineStartKnownSpan = firstItemKnownSpan;
                ArrayList arrayList = new ArrayList();
                i2 = 0;
                int i9 = firstItemIndex;
                while (i2 < this.slotsPerLine && i9 < getTotalSize()) {
                    if (firstItemKnownSpan != 0) {
                        int i10 = firstItemKnownSpan;
                        firstItemKnownSpan = spanOf(i9, this.slotsPerLine - i2);
                        i3 = i10;
                    } else {
                        i3 = 0;
                    }
                    i2 += firstItemKnownSpan;
                    if (i2 <= this.slotsPerLine) {
                        break;
                    }
                    i9++;
                    arrayList.add(GridItemSpan.m1017boximpl(LazyGridSpanKt.GridItemSpan(firstItemKnownSpan)));
                    firstItemKnownSpan = i3;
                }
                return new LineConfiguration(firstItemIndex, arrayList);
            }
        }
        z = false;
        if (z) {
        }
        if (!(bucketSize > lineIndex)) {
        }
        while (bucketSize < lineIndex) {
            if (z) {
            }
            i4 = 0;
            while (i4 < this.slotsPerLine) {
                if (firstItemKnownSpan != 0) {
                }
                i4 += firstItemKnownSpan;
                if (i4 <= this.slotsPerLine) {
                }
            }
            bucketSize++;
            if (bucketSize % getBucketSize() == 0) {
                if (!(this.buckets.size() != bucketSize / getBucketSize())) {
                }
                this.buckets.add(new Bucket(firstItemIndex, firstItemKnownSpan));
            }
        }
        this.lastLineIndex = lineIndex;
        this.lastLineStartItemIndex = firstItemIndex;
        this.lastLineStartKnownSpan = firstItemKnownSpan;
        ArrayList arrayList2 = new ArrayList();
        i2 = 0;
        int i92 = firstItemIndex;
        while (i2 < this.slotsPerLine) {
            if (firstItemKnownSpan != 0) {
            }
            i2 += firstItemKnownSpan;
            if (i2 <= this.slotsPerLine) {
            }
        }
        return new LineConfiguration(firstItemIndex, arrayList2);
    }

    public final int getLineIndexOfItem(final int itemIndex) {
        int i = 0;
        if (getTotalSize() <= 0) {
            return 0;
        }
        if (!(itemIndex < getTotalSize())) {
            InlineClassHelperKt.throwIllegalArgumentException("ItemIndex > total count");
        }
        if (!this.gridContent.getHasCustomSpans()) {
            return itemIndex / this.slotsPerLine;
        }
        int binarySearch$default = CollectionsKt.binarySearch$default(this.buckets, 0, 0, new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int lineIndexOfItem$lambda$8;
                lineIndexOfItem$lambda$8 = LazyGridSpanLayoutProvider.getLineIndexOfItem$lambda$8(itemIndex, (LazyGridSpanLayoutProvider.Bucket) obj);
                return Integer.valueOf(lineIndexOfItem$lambda$8);
            }
        }, 3, (Object) null);
        int i2 = 2;
        if (binarySearch$default < 0) {
            binarySearch$default = (-binarySearch$default) - 2;
        }
        int bucketSize = getBucketSize() * binarySearch$default;
        int firstItemIndex = this.buckets.get(binarySearch$default).getFirstItemIndex();
        if (!(firstItemIndex <= itemIndex)) {
            InlineClassHelperKt.throwIllegalArgumentException("currentItemIndex > itemIndex");
        }
        int i3 = 0;
        while (firstItemIndex < itemIndex) {
            int i4 = firstItemIndex + 1;
            int spanOf = spanOf(firstItemIndex, this.slotsPerLine - i3);
            i3 += spanOf;
            int i5 = this.slotsPerLine;
            if (i3 >= i5) {
                if (i3 == i5) {
                    bucketSize++;
                    i3 = 0;
                } else {
                    bucketSize++;
                    i3 = spanOf;
                }
            }
            if (bucketSize % getBucketSize() == 0 && bucketSize / getBucketSize() >= this.buckets.size()) {
                this.buckets.add(new Bucket(i4 - (i3 > 0 ? 1 : 0), i, i2, null));
            }
            firstItemIndex = i4;
        }
        return i3 + spanOf(itemIndex, this.slotsPerLine - i3) > this.slotsPerLine ? bucketSize + 1 : bucketSize;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getLineIndexOfItem$lambda$8(int i, Bucket bucket) {
        return bucket.getFirstItemIndex() - i;
    }

    public final int spanOf(int itemIndex, int maxSpan) {
        LazyGridItemSpanScopeImpl lazyGridItemSpanScopeImpl = LazyGridItemSpanScopeImpl.INSTANCE;
        lazyGridItemSpanScopeImpl.setMaxCurrentLineSpan(maxSpan);
        lazyGridItemSpanScopeImpl.setMaxLineSpan(this.slotsPerLine);
        IntervalList.Interval<LazyGridInterval> interval = this.gridContent.getIntervals().get(itemIndex);
        return GridItemSpan.m1021getCurrentLineSpanimpl(interval.getValue().getSpan().invoke(lazyGridItemSpanScopeImpl, Integer.valueOf(itemIndex - interval.getStartIndex())).getPackedValue());
    }

    private final void invalidateCache() {
        this.buckets.clear();
        int i = 0;
        this.buckets.add(new Bucket(i, i, 2, null));
        this.lastLineIndex = 0;
        this.lastLineStartItemIndex = 0;
        this.lastLineStartKnownSpan = 0;
        this.cachedBucketIndex = -1;
        this.cachedBucket.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LazyGridSpanLayoutProvider.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider$Bucket;", "", "firstItemIndex", "", "firstItemKnownSpan", "<init>", "(II)V", "getFirstItemIndex", "()I", "getFirstItemKnownSpan", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final class Bucket {
        private final int firstItemIndex;
        private final int firstItemKnownSpan;

        public Bucket(int i, int i2) {
            this.firstItemIndex = i;
            this.firstItemKnownSpan = i2;
        }

        public /* synthetic */ Bucket(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, (i3 & 2) != 0 ? 0 : i2);
        }

        public final int getFirstItemIndex() {
            return this.firstItemIndex;
        }

        public final int getFirstItemKnownSpan() {
            return this.firstItemKnownSpan;
        }
    }

    /* compiled from: LazyGridSpanLayoutProvider.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider$LazyGridItemSpanScopeImpl;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "<init>", "()V", "maxCurrentLineSpan", "", "getMaxCurrentLineSpan", "()I", "setMaxCurrentLineSpan", "(I)V", "maxLineSpan", "getMaxLineSpan", "setMaxLineSpan", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class LazyGridItemSpanScopeImpl implements LazyGridItemSpanScope {
        public static final LazyGridItemSpanScopeImpl INSTANCE = new LazyGridItemSpanScopeImpl();
        private static int maxCurrentLineSpan;
        private static int maxLineSpan;

        private LazyGridItemSpanScopeImpl() {
        }

        @Override // androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
        public int getMaxCurrentLineSpan() {
            return maxCurrentLineSpan;
        }

        public void setMaxCurrentLineSpan(int i) {
            maxCurrentLineSpan = i;
        }

        @Override // androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
        public int getMaxLineSpan() {
            return maxLineSpan;
        }

        public void setMaxLineSpan(int i) {
            maxLineSpan = i;
        }
    }
}
