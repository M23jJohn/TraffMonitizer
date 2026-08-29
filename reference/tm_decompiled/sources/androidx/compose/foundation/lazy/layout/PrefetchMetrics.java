package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import kotlin.Metadata;

/* compiled from: LazyLayoutPrefetchState.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001R\u001c\u0010\u0007\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/lazy/layout/PrefetchMetrics;", "", "<init>", "()V", "getAverage", "Landroidx/compose/foundation/lazy/layout/Averages;", "contentType", "averagesByContentType", "Landroidx/collection/MutableScatterMap;", "lastUsedContentType", "lastUsedAverage", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PrefetchMetrics {
    public static final int $stable = 8;
    private final MutableScatterMap<Object, Averages> averagesByContentType = ScatterMapKt.mutableScatterMapOf();
    private Averages lastUsedAverage;
    private Object lastUsedContentType;

    public final Averages getAverage(Object contentType) {
        Averages averages = this.lastUsedAverage;
        if (this.lastUsedContentType == contentType && averages != null) {
            return averages;
        }
        MutableScatterMap<Object, Averages> mutableScatterMap = this.averagesByContentType;
        Averages averages2 = mutableScatterMap.get(contentType);
        if (averages2 == null) {
            averages2 = new Averages();
            mutableScatterMap.set(contentType, averages2);
        }
        Averages averages3 = averages2;
        this.lastUsedContentType = contentType;
        this.lastUsedAverage = averages3;
        return averages3;
    }
}
