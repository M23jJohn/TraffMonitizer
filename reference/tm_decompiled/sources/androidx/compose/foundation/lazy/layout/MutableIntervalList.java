package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.IntervalList;
import androidx.compose.runtime.collection.MutableVector;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: IntervalList.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0002\u0010\u0010J2\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0018\u0010\u0014\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0004\u0012\u00020\u000f0\u0015H\u0016J\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\u0017\u001a\u00020\tH\u0096\u0002J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\u0019\u001a\u00020\tH\u0002J\u0011\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\tH\u0082\bJ\u001a\u0010\u001b\u001a\u00020\u001c*\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\u0017\u001a\u00020\tH\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/lazy/layout/MutableIntervalList;", "T", "Landroidx/compose/foundation/lazy/layout/IntervalList;", "<init>", "()V", "intervals", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/foundation/lazy/layout/IntervalList$Interval;", "value", "", "size", "getSize", "()I", "lastInterval", "addInterval", "", "(ILjava/lang/Object;)V", "forEach", "fromIndex", "toIndex", "block", "Lkotlin/Function1;", "get", "index", "getIntervalForIndex", "itemIndex", "checkIndexBounds", "contains", "", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MutableIntervalList<T> implements IntervalList<T> {
    public static final int $stable = 8;
    private final MutableVector<IntervalList.Interval<T>> intervals = new MutableVector<>(new IntervalList.Interval[16], 0);
    private IntervalList.Interval<? extends T> lastInterval;
    private int size;

    @Override // androidx.compose.foundation.lazy.layout.IntervalList
    public int getSize() {
        return this.size;
    }

    public final void addInterval(int size, T value) {
        if (!(size >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("size should be >=0");
        }
        if (size == 0) {
            return;
        }
        IntervalList.Interval<T> interval = new IntervalList.Interval<>(getSize(), size, value);
        this.size = getSize() + size;
        this.intervals.add(interval);
    }

    private final IntervalList.Interval<T> getIntervalForIndex(int itemIndex) {
        int binarySearch;
        IntervalList.Interval<? extends T> interval = this.lastInterval;
        if (interval != null && contains(interval, itemIndex)) {
            return interval;
        }
        MutableVector<IntervalList.Interval<T>> mutableVector = this.intervals;
        binarySearch = IntervalListKt.binarySearch(mutableVector, itemIndex);
        IntervalList.Interval interval2 = (IntervalList.Interval<? extends T>) mutableVector.content[binarySearch];
        this.lastInterval = interval2;
        return interval2;
    }

    private final void checkIndexBounds(int index) {
        if (index < 0 || index >= getSize()) {
            InlineClassHelperKt.throwIndexOutOfBoundsException("Index " + index + ", size " + getSize());
        }
    }

    private final boolean contains(IntervalList.Interval<? extends T> interval, int i) {
        return i < interval.getStartIndex() + interval.getSize() && interval.getStartIndex() <= i;
    }

    @Override // androidx.compose.foundation.lazy.layout.IntervalList
    public void forEach(int fromIndex, int toIndex, Function1<? super IntervalList.Interval<? extends T>, Unit> block) {
        int binarySearch;
        if (fromIndex < 0 || fromIndex >= getSize()) {
            InlineClassHelperKt.throwIndexOutOfBoundsException("Index " + fromIndex + ", size " + getSize());
        }
        if (toIndex < 0 || toIndex >= getSize()) {
            InlineClassHelperKt.throwIndexOutOfBoundsException("Index " + toIndex + ", size " + getSize());
        }
        if (!(toIndex >= fromIndex)) {
            InlineClassHelperKt.throwIllegalArgumentException("toIndex (" + toIndex + ") should be not smaller than fromIndex (" + fromIndex + ')');
        }
        binarySearch = IntervalListKt.binarySearch(this.intervals, fromIndex);
        int startIndex = this.intervals.content[binarySearch].getStartIndex();
        while (startIndex <= toIndex) {
            IntervalList.Interval<T> interval = this.intervals.content[binarySearch];
            block.invoke(interval);
            startIndex += interval.getSize();
            binarySearch++;
        }
    }

    @Override // androidx.compose.foundation.lazy.layout.IntervalList
    public IntervalList.Interval<T> get(int index) {
        if (index < 0 || index >= getSize()) {
            InlineClassHelperKt.throwIndexOutOfBoundsException("Index " + index + ", size " + getSize());
        }
        return getIntervalForIndex(index);
    }
}
