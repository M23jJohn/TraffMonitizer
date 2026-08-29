package androidx.compose.foundation.lazy.layout;

import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* compiled from: LazyLayoutMeasuredItem.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b!\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J/\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H&¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItemProvider;", "T", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;", "", "<init>", "()V", "placeablesCache", "Landroidx/collection/MutableIntObjectMap;", "", "Landroidx/compose/ui/layout/Placeable;", "getAndMeasure", "index", "", "lane", "span", "constraints", "Landroidx/compose/ui/unit/Constraints;", "getAndMeasure--hBUhpc", "(IIIJ)Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;", "getPlaceables", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "getPlaceables-3p2s80s", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;IJ)Ljava/util/List;", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class LazyLayoutMeasuredItemProvider<T extends LazyLayoutMeasuredItem> {
    public static final int $stable = 8;
    private final MutableIntObjectMap<List<Placeable>> placeablesCache = IntObjectMapKt.mutableIntObjectMapOf();

    /* renamed from: getAndMeasure--hBUhpc */
    public abstract T mo1008getAndMeasurehBUhpc(int index, int lane, int span, long constraints);

    /* renamed from: getPlaceables-3p2s80s, reason: not valid java name */
    public final List<Placeable> m1087getPlaceables3p2s80s(LazyLayoutMeasureScope lazyLayoutMeasureScope, int i, long j) {
        List<Placeable> list = this.placeablesCache.get(i);
        if (list != null) {
            return list;
        }
        List<Measurable> compose = lazyLayoutMeasureScope.compose(i);
        int size = compose.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(compose.get(i2).mo6055measureBRTryo0(j));
        }
        ArrayList arrayList2 = arrayList;
        this.placeablesCache.set(i, arrayList2);
        return arrayList2;
    }
}
