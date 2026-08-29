package androidx.compose.runtime;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SlotTable.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0086\u0002J\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u000eH\u0086\u0002J\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nJ\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nJ%\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0017H\u0082\bJ\u0016\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\nJ\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/BitVector;", "", "<init>", "()V", "first", "", "second", "others", "", "size", "", "getSize", "()I", "get", "", "index", "set", "", "value", "nextSet", "nextClear", "nextBit", "valueSelector", "Lkotlin/Function1;", "setRange", "start", "end", "toString", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BitVector {
    public static final int $stable = 8;
    private long first;
    private long[] others;
    private long second;

    public BitVector() {
        long[] jArr;
        jArr = SlotTableKt.EmptyLongArray;
        this.others = jArr;
    }

    public final int getSize() {
        return (this.others.length + 2) * 64;
    }

    public final boolean get(int index) {
        int i;
        if (index < 64) {
            return ((1 << index) & this.first) != 0;
        }
        if (index < 128) {
            return ((1 << (index - 64)) & this.second) != 0;
        }
        long[] jArr = this.others;
        int length = jArr.length;
        if (length != 0 && (index / 64) - 2 < length) {
            return ((1 << (index % 64)) & jArr[i]) != 0;
        }
        return false;
    }

    public final void set(int index, boolean value) {
        if (index < 64) {
            this.first = ((value ? 1L : 0L) << index) | ((~(1 << index)) & this.first);
            return;
        }
        if (index < 128) {
            this.second = ((value ? 1L : 0L) << index) | ((~(1 << (index - 64))) & this.second);
            return;
        }
        int i = index / 64;
        int i2 = i - 2;
        int i3 = index % 64;
        long j = 1 << i3;
        long[] jArr = this.others;
        if (i2 >= jArr.length) {
            jArr = Arrays.copyOf(jArr, i - 1);
            Intrinsics.checkNotNullExpressionValue(jArr, "copyOf(...)");
            this.others = jArr;
        }
        jArr[i2] = ((value ? 1L : 0L) << i3) | ((~j) & jArr[i2]);
    }

    private final int nextBit(int index, Function1<? super Long, Long> valueSelector) {
        int numberOfTrailingZeros;
        if (index < 64 && (numberOfTrailingZeros = Long.numberOfTrailingZeros((valueSelector.invoke(Long.valueOf(this.first)).longValue() >>> index) << index)) < 64) {
            return numberOfTrailingZeros;
        }
        if (index < 128) {
            int i = index - 64;
            int numberOfTrailingZeros2 = Long.numberOfTrailingZeros((valueSelector.invoke(Long.valueOf(this.second)).longValue() >>> i) << i);
            if (numberOfTrailingZeros2 < 64) {
                return numberOfTrailingZeros2 + 64;
            }
        }
        int max = Math.max(index, 128);
        int i2 = (max / 64) - 2;
        long[] jArr = this.others;
        int length = jArr.length;
        for (int i3 = i2; i3 < length; i3++) {
            long longValue = valueSelector.invoke(Long.valueOf(jArr[i3])).longValue();
            if (i3 == i2) {
                int i4 = max % 64;
                longValue = (longValue >>> i4) << i4;
            }
            int numberOfTrailingZeros3 = Long.numberOfTrailingZeros(longValue);
            if (numberOfTrailingZeros3 < 64) {
                return (i3 * 64) + 128 + numberOfTrailingZeros3;
            }
        }
        return Integer.MAX_VALUE;
    }

    public final void setRange(int start, int end) {
        long j = start < end ? -1L : 0L;
        this.first = ((((start < 64 ? 1 : 0) * j) >>> (64 - (Math.min(64, end) - start))) << start) | this.first;
        if (end > 64) {
            int max = Math.max(start, 64);
            this.second = (((j * (max < 128 ? 1 : 0)) >>> (128 - (Math.min(128, end) - max))) << max) | this.second;
            if (end > 128) {
                for (int max2 = Math.max(max, 128); max2 < end; max2++) {
                    set(max2, true);
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BitVector [");
        int size = getSize();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            if (get(i)) {
                if (!z) {
                    sb.append(", ");
                }
                sb.append(i);
                z = false;
            }
        }
        sb.append(']');
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public final int nextSet(int index) {
        int numberOfTrailingZeros;
        if (index < 64 && (numberOfTrailingZeros = Long.numberOfTrailingZeros((this.first >>> index) << index)) < 64) {
            return numberOfTrailingZeros;
        }
        if (index < 128) {
            int i = index - 64;
            int numberOfTrailingZeros2 = Long.numberOfTrailingZeros((this.second >>> i) << i);
            if (numberOfTrailingZeros2 < 64) {
                return numberOfTrailingZeros2 + 64;
            }
        }
        int max = Math.max(index, 128);
        int i2 = (max / 64) - 2;
        long[] jArr = this.others;
        int length = jArr.length;
        for (int i3 = i2; i3 < length; i3++) {
            long j = jArr[i3];
            if (i3 == i2) {
                int i4 = max % 64;
                j = (j >>> i4) << i4;
            }
            int numberOfTrailingZeros3 = Long.numberOfTrailingZeros(j);
            if (numberOfTrailingZeros3 < 64) {
                return (i3 * 64) + 128 + numberOfTrailingZeros3;
            }
        }
        return Integer.MAX_VALUE;
    }

    public final int nextClear(int index) {
        int numberOfTrailingZeros;
        if (index < 64 && (numberOfTrailingZeros = Long.numberOfTrailingZeros(((~this.first) >>> index) << index)) < 64) {
            return numberOfTrailingZeros;
        }
        if (index < 128) {
            int i = index - 64;
            int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(((~this.second) >>> i) << i);
            if (numberOfTrailingZeros2 < 64) {
                return numberOfTrailingZeros2 + 64;
            }
        }
        int max = Math.max(index, 128);
        int i2 = (max / 64) - 2;
        long[] jArr = this.others;
        int length = jArr.length;
        for (int i3 = i2; i3 < length; i3++) {
            long j = ~jArr[i3];
            if (i3 == i2) {
                int i4 = max % 64;
                j = (j >>> i4) << i4;
            }
            int numberOfTrailingZeros3 = Long.numberOfTrailingZeros(j);
            if (numberOfTrailingZeros3 < 64) {
                return (i3 * 64) + 128 + numberOfTrailingZeros3;
            }
        }
        return Integer.MAX_VALUE;
    }
}
