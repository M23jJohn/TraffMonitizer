package androidx.compose.foundation.text.input.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: TextFieldLayoutStateCache.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\u001a6\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001H\u0002¨\u0006\u0005"}, d2 = {"mergeNullableLists", "", "T", "first", "second", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldLayoutStateCacheKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> List<T> mergeNullableLists(List<? extends T> list, List<? extends T> list2) {
        List<? extends T> list3;
        List<? extends T> list4 = list;
        if ((list4 == null || list4.isEmpty()) && ((list3 = list2) == null || list3.isEmpty())) {
            return null;
        }
        if (list4 == null || list4.isEmpty()) {
            return list2;
        }
        List<? extends T> list5 = list2;
        if (list5 == null || list5.isEmpty()) {
            return list;
        }
        List createListBuilder = CollectionsKt.createListBuilder();
        createListBuilder.addAll(list4);
        createListBuilder.addAll(list5);
        return CollectionsKt.build(createListBuilder);
    }
}
