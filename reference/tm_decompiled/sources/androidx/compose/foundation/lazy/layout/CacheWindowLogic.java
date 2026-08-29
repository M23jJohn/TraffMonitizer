package androidx.compose.foundation.lazy.layout;

import androidx.collection.IntIntMapKt;
import androidx.collection.IntObjectMapKt;
import androidx.collection.IntSetKt;
import androidx.collection.MutableIntIntMap;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.util.AndroidTrace_androidKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: CacheWindowLogic.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b \b!\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u001e\u001a\u00020\u001f*\u00020 2\u0006\u0010!\u001a\u00020\u000fJ\b\u0010\"\u001a\u00020\u001fH\u0002J\n\u0010#\u001a\u00020\u001f*\u00020 J\u0006\u0010$\u001a\u00020\u0013J\u0014\u0010%\u001a\u00020\u001f*\u00020 2\u0006\u0010!\u001a\u00020\u000fH\u0002J\u0014\u0010&\u001a\u00020\u001f*\u00020 2\u0006\u0010!\u001a\u00020\u000fH\u0002J\u0014\u0010'\u001a\u00020\u001f*\u00020 2\u0006\u0010(\u001a\u00020\u0013H\u0002J\u0006\u0010)\u001a\u00020\u001fJD\u0010*\u001a\u00020\u001f*\u00020 2\u0006\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u0013H\u0002J@\u00102\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u0011H\u0002J\u001c\u00104\u001a\u00020\u0011*\u00020 2\u0006\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u0013H\u0002J\u0018\u00107\u001a\u00020\u001f2\u0006\u00105\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u0011H\u0002J\u0018\u00109\u001a\u00020\u001f2\u0006\u00105\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u0011H\u0002J\u0018\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020\u00112\u0006\u0010<\u001a\u00020\u0011H\u0002J\u001c\u0010=\u001a\u00020\u001f*\u00020 2\u0006\u00105\u001a\u00020\u00112\u0006\u0010>\u001a\u00020\u0011H\u0002J\f\u0010?\u001a\u00020\u001f*\u00020 H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Landroidx/compose/foundation/lazy/layout/CacheWindowLogic;", "", "cacheWindow", "Landroidx/compose/foundation/lazy/layout/LazyLayoutCacheWindow;", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutCacheWindow;)V", "prefetchWindowHandles", "Landroidx/collection/MutableIntObjectMap;", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "indicesToRemove", "Landroidx/collection/MutableIntSet;", "windowCache", "Landroidx/collection/MutableIntIntMap;", "previousPassDelta", "", "previousPassItemCount", "", "hasUpdatedVisibleItemsOnce", "", "value", "prefetchWindowStartLine", "getPrefetchWindowStartLine$foundation_release", "()I", "prefetchWindowEndLine", "getPrefetchWindowEndLine$foundation_release", "prefetchWindowStartExtraSpace", "prefetchWindowEndExtraSpace", "shouldRefillWindow", "itemsCount", "onScroll", "", "Landroidx/compose/foundation/lazy/layout/CacheWindowScope;", "delta", "traceWindowInfo", "onVisibleItemsUpdated", "hasValidBounds", "fillCacheWindowBackward", "fillCacheWindowForward", "refillWindow", "refillForward", "resetStrategy", "onPrefetchForward", "visibleWindowStart", "visibleWindowEnd", "prefetchForwardWindow", "mainAxisExtraSpaceEnd", "mainAxisExtraSpaceStart", "scrollDelta", "applyForwardPrefetch", "onKeepAround", "keepAroundWindow", "getItemSizeOrPrefetch", "index", "isUrgent", "cachePrefetchedItem", "size", "cacheVisibleItemsInfo", "removeOutOfBoundsItems", "startLine", "endLine", "onItemPrefetched", "itemSize", "scheduleNextItemIfNeeded", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class CacheWindowLogic {
    public static final int $stable = 8;
    private final LazyLayoutCacheWindow cacheWindow;
    private boolean hasUpdatedVisibleItemsOnce;
    private int itemsCount;
    private int prefetchWindowEndExtraSpace;
    private int prefetchWindowStartExtraSpace;
    private float previousPassDelta;
    private boolean shouldRefillWindow;
    private final MutableIntObjectMap<List<LazyLayoutPrefetchState.PrefetchHandle>> prefetchWindowHandles = IntObjectMapKt.mutableIntObjectMapOf();
    private final MutableIntSet indicesToRemove = IntSetKt.mutableIntSetOf();
    private final MutableIntIntMap windowCache = IntIntMapKt.mutableIntIntMapOf();
    private int previousPassItemCount = -1;
    private int prefetchWindowStartLine = Integer.MAX_VALUE;
    private int prefetchWindowEndLine = Integer.MIN_VALUE;

    public CacheWindowLogic(LazyLayoutCacheWindow lazyLayoutCacheWindow) {
        this.cacheWindow = lazyLayoutCacheWindow;
    }

    /* renamed from: getPrefetchWindowStartLine$foundation_release, reason: from getter */
    public final int getPrefetchWindowStartLine() {
        return this.prefetchWindowStartLine;
    }

    /* renamed from: getPrefetchWindowEndLine$foundation_release, reason: from getter */
    public final int getPrefetchWindowEndLine() {
        return this.prefetchWindowEndLine;
    }

    public final void onScroll(CacheWindowScope cacheWindowScope, float f) {
        traceWindowInfo();
        fillCacheWindowBackward(cacheWindowScope, f);
        fillCacheWindowForward(cacheWindowScope, f);
        this.previousPassDelta = f;
        traceWindowInfo();
    }

    private final void traceWindowInfo() {
        AndroidTrace_androidKt.traceValue("prefetchWindowStartExtraSpace", this.prefetchWindowStartExtraSpace);
        AndroidTrace_androidKt.traceValue("prefetchWindowEndExtraSpace", this.prefetchWindowEndExtraSpace);
        AndroidTrace_androidKt.traceValue("prefetchWindowStartIndex", this.prefetchWindowStartLine);
        AndroidTrace_androidKt.traceValue("prefetchWindowEndIndex", this.prefetchWindowEndLine);
    }

    public final void onVisibleItemsUpdated(CacheWindowScope cacheWindowScope) {
        if (!this.hasUpdatedVisibleItemsOnce) {
            LazyLayoutCacheWindow lazyLayoutCacheWindow = this.cacheWindow;
            Density density = cacheWindowScope.getDensity();
            if ((density != null ? lazyLayoutCacheWindow.calculateAheadWindow(density, cacheWindowScope.getMainAxisViewportSize()) : 0) != 0) {
                this.shouldRefillWindow = true;
            }
            this.hasUpdatedVisibleItemsOnce = true;
        }
        int i = this.previousPassItemCount;
        if (i != -1 && i != cacheWindowScope.getTotalItemsCount()) {
            this.shouldRefillWindow = true;
            this.prefetchWindowStartLine = RangesKt.coerceAtLeast(this.prefetchWindowStartLine, 0);
            int lastLineIndex = cacheWindowScope.getLastLineIndex();
            if (lastLineIndex != -1) {
                this.prefetchWindowEndLine = RangesKt.coerceAtMost(this.prefetchWindowEndLine, lastLineIndex);
            }
        }
        this.itemsCount = cacheWindowScope.getTotalItemsCount();
        if (!cacheWindowScope.getHasVisibleItems()) {
            resetStrategy();
        } else {
            int visibleLineCount = cacheWindowScope.getVisibleLineCount();
            for (int i2 = 0; i2 < visibleLineCount; i2++) {
                cacheVisibleItemsInfo(cacheWindowScope.getVisibleItemLine(i2), cacheWindowScope.getVisibleItemSize(i2));
            }
            if (this.shouldRefillWindow) {
                refillWindow(cacheWindowScope, this.previousPassDelta <= 0.0f);
                this.shouldRefillWindow = false;
            }
        }
        this.previousPassItemCount = cacheWindowScope.getTotalItemsCount();
    }

    public final boolean hasValidBounds() {
        return (this.prefetchWindowStartLine == Integer.MAX_VALUE || this.prefetchWindowEndLine == Integer.MIN_VALUE) ? false : true;
    }

    private final void fillCacheWindowBackward(CacheWindowScope cacheWindowScope, float f) {
        if (cacheWindowScope.getHasVisibleItems()) {
            int mainAxisViewportSize = cacheWindowScope.getMainAxisViewportSize();
            LazyLayoutCacheWindow lazyLayoutCacheWindow = this.cacheWindow;
            Density density = cacheWindowScope.getDensity();
            int calculateBehindWindow = density != null ? lazyLayoutCacheWindow.calculateBehindWindow(density, mainAxisViewportSize) : 0;
            this.itemsCount = cacheWindowScope.getTotalItemsCount();
            onKeepAround(cacheWindowScope.getFirstVisibleLineIndex(), cacheWindowScope.getLastVisibleLineIndex(), cacheWindowScope.getMainAxisExtraSpaceEnd(), cacheWindowScope.getMainAxisExtraSpaceStart(), calculateBehindWindow, f, cacheWindowScope.getTotalItemsCount());
        }
    }

    private final void fillCacheWindowForward(CacheWindowScope cacheWindowScope, float f) {
        if (cacheWindowScope.getHasVisibleItems()) {
            int mainAxisViewportSize = cacheWindowScope.getMainAxisViewportSize();
            LazyLayoutCacheWindow lazyLayoutCacheWindow = this.cacheWindow;
            Density density = cacheWindowScope.getDensity();
            int calculateAheadWindow = density != null ? lazyLayoutCacheWindow.calculateAheadWindow(density, mainAxisViewportSize) : 0;
            onPrefetchForward(cacheWindowScope, cacheWindowScope.getFirstVisibleLineIndex(), cacheWindowScope.getLastVisibleLineIndex(), calculateAheadWindow, cacheWindowScope.getMainAxisExtraSpaceEnd(), cacheWindowScope.getMainAxisExtraSpaceStart(), f, f <= 0.0f);
        }
    }

    private final void refillWindow(CacheWindowScope cacheWindowScope, boolean z) {
        if (cacheWindowScope.getHasVisibleItems()) {
            int mainAxisViewportSize = cacheWindowScope.getMainAxisViewportSize();
            LazyLayoutCacheWindow lazyLayoutCacheWindow = this.cacheWindow;
            Density density = cacheWindowScope.getDensity();
            onPrefetchForward(cacheWindowScope, cacheWindowScope.getFirstVisibleLineIndex(), cacheWindowScope.getLastVisibleLineIndex(), density != null ? lazyLayoutCacheWindow.calculateAheadWindow(density, mainAxisViewportSize) : 0, cacheWindowScope.getMainAxisExtraSpaceEnd(), cacheWindowScope.getMainAxisExtraSpaceStart(), 0.0f, z);
        }
    }

    public final void resetStrategy() {
        this.prefetchWindowStartLine = Integer.MAX_VALUE;
        this.prefetchWindowEndLine = Integer.MIN_VALUE;
        this.prefetchWindowStartExtraSpace = 0;
        this.prefetchWindowEndExtraSpace = 0;
        this.shouldRefillWindow = false;
        this.windowCache.clear();
        MutableIntObjectMap<List<LazyLayoutPrefetchState.PrefetchHandle>> mutableIntObjectMap = this.prefetchWindowHandles;
        long[] jArr = mutableIntObjectMap.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        int i4 = (i << 3) + i3;
                        int i5 = mutableIntObjectMap.keys[i4];
                        List list = (List) mutableIntObjectMap.values[i4];
                        int size = list.size();
                        for (int i6 = 0; i6 < size; i6++) {
                            ((LazyLayoutPrefetchState.PrefetchHandle) list.get(i6)).cancel();
                        }
                        mutableIntObjectMap.removeValueAt(i4);
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    private final void onPrefetchForward(CacheWindowScope cacheWindowScope, int i, int i2, int i3, int i4, int i5, float f, boolean z) {
        int i6;
        boolean z2 = Math.signum(f) == Math.signum(this.previousPassDelta);
        if (z) {
            if (!z2 || this.shouldRefillWindow) {
                this.prefetchWindowEndExtraSpace = i3 - i4;
                this.prefetchWindowEndLine = i2;
            } else {
                this.prefetchWindowEndExtraSpace += MathKt.roundToInt(Math.abs(f));
            }
            while (this.prefetchWindowEndExtraSpace > 0 && cacheWindowScope.getLastIndexInLine(this.prefetchWindowEndLine) != -1 && cacheWindowScope.getLastIndexInLine(this.prefetchWindowEndLine) < this.itemsCount - 1) {
                int itemSizeOrPrefetch = getItemSizeOrPrefetch(cacheWindowScope, this.prefetchWindowEndLine + 1, this.prefetchWindowEndLine + 1 == i2 + 1 && Math.abs(f) >= ((float) i4));
                if (itemSizeOrPrefetch == -1) {
                    return;
                }
                this.prefetchWindowEndLine++;
                this.prefetchWindowEndExtraSpace -= itemSizeOrPrefetch;
            }
            return;
        }
        if (!z2 || this.shouldRefillWindow) {
            this.prefetchWindowStartExtraSpace = i3 - i5;
            this.prefetchWindowStartLine = i;
        } else {
            this.prefetchWindowStartExtraSpace += MathKt.roundToInt(Math.abs(f));
        }
        while (this.prefetchWindowStartExtraSpace > 0 && (i6 = this.prefetchWindowStartLine) > 0) {
            int itemSizeOrPrefetch2 = getItemSizeOrPrefetch(cacheWindowScope, this.prefetchWindowStartLine - 1, i6 + (-1) == i + (-1) && Math.abs(f) >= ((float) i5));
            if (itemSizeOrPrefetch2 == -1) {
                return;
            }
            this.prefetchWindowStartLine--;
            this.prefetchWindowStartExtraSpace -= itemSizeOrPrefetch2;
        }
    }

    private final void onKeepAround(int visibleWindowStart, int visibleWindowEnd, int mainAxisExtraSpaceEnd, int mainAxisExtraSpaceStart, int keepAroundWindow, float scrollDelta, int itemsCount) {
        int i;
        int i2;
        if (scrollDelta <= 0.0f) {
            this.prefetchWindowStartExtraSpace = keepAroundWindow - mainAxisExtraSpaceStart;
            this.prefetchWindowStartLine = visibleWindowStart;
            while (this.prefetchWindowStartExtraSpace > 0 && (i2 = this.prefetchWindowStartLine) > 0 && this.windowCache.containsKey(i2 - 1)) {
                this.prefetchWindowStartLine--;
                this.prefetchWindowStartExtraSpace -= this.windowCache.get(this.prefetchWindowStartLine - 1);
            }
            removeOutOfBoundsItems(0, this.prefetchWindowStartLine - 1);
            return;
        }
        this.prefetchWindowEndExtraSpace = keepAroundWindow - mainAxisExtraSpaceEnd;
        this.prefetchWindowEndLine = visibleWindowEnd;
        while (this.prefetchWindowEndExtraSpace > 0 && (i = this.prefetchWindowEndLine) < itemsCount - 1 && this.windowCache.containsKey(i + 1)) {
            int i3 = this.windowCache.get(this.prefetchWindowEndLine + 1);
            this.prefetchWindowEndLine++;
            this.prefetchWindowEndExtraSpace -= i3;
        }
        removeOutOfBoundsItems(this.prefetchWindowEndLine + 1, itemsCount - 1);
    }

    private final int getItemSizeOrPrefetch(final CacheWindowScope cacheWindowScope, int i, boolean z) {
        List<LazyLayoutPrefetchState.PrefetchHandle> list;
        List<LazyLayoutPrefetchState.PrefetchHandle> list2;
        if (this.windowCache.containsKey(i)) {
            return this.windowCache.get(i);
        }
        int i2 = 0;
        if (this.prefetchWindowHandles.containsKey(i)) {
            if (z && (list2 = this.prefetchWindowHandles.get(i)) != null) {
                int size = list2.size();
                while (i2 < size) {
                    list2.get(i2).markAsUrgent();
                    i2++;
                }
            }
            return -1;
        }
        this.prefetchWindowHandles.set(i, cacheWindowScope.schedulePrefetch(i, new Function2() { // from class: androidx.compose.foundation.lazy.layout.CacheWindowLogic$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit itemSizeOrPrefetch$lambda$8;
                itemSizeOrPrefetch$lambda$8 = CacheWindowLogic.getItemSizeOrPrefetch$lambda$8(CacheWindowLogic.this, cacheWindowScope, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
                return itemSizeOrPrefetch$lambda$8;
            }
        }));
        if (z && (list = this.prefetchWindowHandles.get(i)) != null) {
            int size2 = list.size();
            while (i2 < size2) {
                list.get(i2).markAsUrgent();
                i2++;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getItemSizeOrPrefetch$lambda$8(CacheWindowLogic cacheWindowLogic, CacheWindowScope cacheWindowScope, int i, int i2) {
        cacheWindowLogic.onItemPrefetched(cacheWindowScope, i, i2);
        return Unit.INSTANCE;
    }

    private final void cachePrefetchedItem(int index, int size) {
        this.windowCache.set(index, size);
        if (index > this.prefetchWindowEndLine) {
            this.prefetchWindowEndLine = index;
            this.prefetchWindowEndExtraSpace -= size;
        } else if (index < this.prefetchWindowStartLine) {
            this.prefetchWindowStartLine = index;
            this.prefetchWindowStartExtraSpace -= size;
        }
    }

    private final void cacheVisibleItemsInfo(int index, int size) {
        if (this.windowCache.containsKey(index) && this.windowCache.get(index) != size) {
            this.shouldRefillWindow = true;
        }
        this.windowCache.set(index, size);
        this.prefetchWindowStartLine = Math.min(this.prefetchWindowStartLine, index);
        this.prefetchWindowEndLine = Math.max(this.prefetchWindowEndLine, index);
        List<LazyLayoutPrefetchState.PrefetchHandle> remove = this.prefetchWindowHandles.remove(index);
        if (remove != null) {
            int size2 = remove.size();
            for (int i = 0; i < size2; i++) {
                remove.get(i).cancel();
            }
        }
    }

    private final void removeOutOfBoundsItems(int startLine, int endLine) {
        char c;
        long j;
        long j2;
        long j3;
        int i;
        char c2;
        long j4;
        this.indicesToRemove.clear();
        MutableIntObjectMap<List<LazyLayoutPrefetchState.PrefetchHandle>> mutableIntObjectMap = this.prefetchWindowHandles;
        int[] iArr = mutableIntObjectMap.keys;
        long[] jArr = mutableIntObjectMap.metadata;
        int length = jArr.length - 2;
        char c3 = 7;
        long j5 = -9187201950435737472L;
        if (length >= 0) {
            int i2 = 0;
            j2 = 128;
            while (true) {
                long j6 = jArr[i2];
                j3 = 255;
                if ((((~j6) << c3) & j6 & j5) != j5) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    int i4 = 0;
                    while (i4 < i3) {
                        if ((j6 & 255) < 128) {
                            c2 = c3;
                            int i5 = iArr[(i2 << 3) + i4];
                            if (startLine <= i5 && i5 <= endLine) {
                                j4 = j5;
                                this.indicesToRemove.add(i5);
                                j6 >>= 8;
                                i4++;
                                c3 = c2;
                                j5 = j4;
                            }
                        } else {
                            c2 = c3;
                        }
                        j4 = j5;
                        j6 >>= 8;
                        i4++;
                        c3 = c2;
                        j5 = j4;
                    }
                    c = c3;
                    j = j5;
                    if (i3 != 8) {
                        break;
                    }
                } else {
                    c = c3;
                    j = j5;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
                c3 = c;
                j5 = j;
            }
        } else {
            c = 7;
            j = -9187201950435737472L;
            j2 = 128;
            j3 = 255;
        }
        MutableIntIntMap mutableIntIntMap = this.windowCache;
        int[] iArr2 = mutableIntIntMap.keys;
        long[] jArr2 = mutableIntIntMap.metadata;
        int length2 = jArr2.length - 2;
        if (length2 >= 0) {
            int i6 = 0;
            while (true) {
                long j7 = jArr2[i6];
                if ((((~j7) << c) & j7 & j) != j) {
                    int i7 = 8 - ((~(i6 - length2)) >>> 31);
                    for (int i8 = 0; i8 < i7; i8++) {
                        if ((j7 & j3) < j2 && startLine <= (i = iArr2[(i6 << 3) + i8]) && i <= endLine) {
                            this.indicesToRemove.add(i);
                        }
                        j7 >>= 8;
                    }
                    if (i7 != 8) {
                        break;
                    }
                }
                if (i6 == length2) {
                    break;
                } else {
                    i6++;
                }
            }
        }
        MutableIntSet mutableIntSet = this.indicesToRemove;
        int[] iArr3 = mutableIntSet.elements;
        long[] jArr3 = mutableIntSet.metadata;
        int length3 = jArr3.length - 2;
        if (length3 < 0) {
            return;
        }
        int i9 = 0;
        while (true) {
            long j8 = jArr3[i9];
            if ((((~j8) << c) & j8 & j) != j) {
                int i10 = 8 - ((~(i9 - length3)) >>> 31);
                for (int i11 = 0; i11 < i10; i11++) {
                    if ((j8 & j3) < j2) {
                        int i12 = iArr3[(i9 << 3) + i11];
                        List<LazyLayoutPrefetchState.PrefetchHandle> remove = this.prefetchWindowHandles.remove(i12);
                        if (remove != null) {
                            int size = remove.size();
                            for (int i13 = 0; i13 < size; i13++) {
                                remove.get(i13).cancel();
                            }
                        }
                        this.windowCache.remove(i12);
                    }
                    j8 >>= 8;
                }
                if (i10 != 8) {
                    return;
                }
            }
            if (i9 == length3) {
                return;
            } else {
                i9++;
            }
        }
    }

    private final void onItemPrefetched(CacheWindowScope cacheWindowScope, int i, int i2) {
        cachePrefetchedItem(i, i2);
        scheduleNextItemIfNeeded(cacheWindowScope);
        traceWindowInfo();
    }

    private final void scheduleNextItemIfNeeded(final CacheWindowScope cacheWindowScope) {
        int i;
        if (Math.signum(this.previousPassDelta) <= 0.0f) {
            if (this.prefetchWindowEndExtraSpace > 0) {
                i = this.prefetchWindowEndLine + 1;
            }
            i = -1;
        } else {
            if (Math.signum(this.previousPassDelta) > 0.0f && this.prefetchWindowStartExtraSpace > 0) {
                i = this.prefetchWindowStartLine - 1;
            }
            i = -1;
        }
        if (i <= 0 || cacheWindowScope.getLastIndexInLine(i) == -1 || cacheWindowScope.getLastIndexInLine(i) >= this.itemsCount) {
            return;
        }
        this.prefetchWindowHandles.set(i, cacheWindowScope.schedulePrefetch(i, new Function2() { // from class: androidx.compose.foundation.lazy.layout.CacheWindowLogic$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit scheduleNextItemIfNeeded$lambda$15;
                scheduleNextItemIfNeeded$lambda$15 = CacheWindowLogic.scheduleNextItemIfNeeded$lambda$15(CacheWindowLogic.this, cacheWindowScope, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
                return scheduleNextItemIfNeeded$lambda$15;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit scheduleNextItemIfNeeded$lambda$15(CacheWindowLogic cacheWindowLogic, CacheWindowScope cacheWindowScope, int i, int i2) {
        cacheWindowLogic.onItemPrefetched(cacheWindowScope, i, i2);
        return Unit.INSTANCE;
    }
}
