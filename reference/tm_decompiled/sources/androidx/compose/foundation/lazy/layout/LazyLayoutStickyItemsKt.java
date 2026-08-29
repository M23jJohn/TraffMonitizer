package androidx.compose.foundation.lazy.layout;

import androidx.collection.IntList;
import androidx.compose.ui.unit.IntOffset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: LazyLayoutStickyItems.kt */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082\b\u001ax\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\r\"\b\b\u0000\u0010\u000e*\u00020\t*\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\b2\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H\u000e0\u001bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0007\u001a\u00020\b*\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Debug", "", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "mainAxisOffset", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;", "getMainAxisOffset", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)I", "applyStickyItems", "", "T", "Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;", "firstVisibleItemIndex", "lastVisibleItemIndex", "positionedItems", "", "stickyItems", "Landroidx/collection/IntList;", "beforeContentPadding", "afterContentPadding", "layoutWidth", "layoutHeight", "getAndMeasure", "Lkotlin/Function1;", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyLayoutStickyItemsKt {
    private static final boolean Debug = false;

    private static final void debugLog(Function0<String> function0) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getMainAxisOffset(LazyLayoutMeasuredItem lazyLayoutMeasuredItem) {
        long mo1006getOffsetBjo55l4 = lazyLayoutMeasuredItem.mo1006getOffsetBjo55l4(0);
        return lazyLayoutMeasuredItem.getIsVertical() ? IntOffset.m7532getYimpl(mo1006getOffsetBjo55l4) : IntOffset.m7531getXimpl(mo1006getOffsetBjo55l4);
    }

    public static final <T extends LazyLayoutMeasuredItem> List<T> applyStickyItems(StickyItemsPlacement stickyItemsPlacement, int i, int i2, List<T> list, IntList intList, int i3, int i4, int i5, int i6, Function1<? super Integer, ? extends T> function1) {
        T remove;
        StickyItemsPlacement stickyItemsPlacement2 = stickyItemsPlacement;
        if (stickyItemsPlacement2 != null) {
            List<T> list2 = list;
            if (!list2.isEmpty() && intList._size != 0) {
                IntList stickingIndices = stickyItemsPlacement2.getStickingIndices(i, i2, intList);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList(list.size());
                int size = list2.size();
                for (int i7 = 0; i7 < size; i7++) {
                    T t = list.get(i7);
                    if (intList.contains(t.getIndex())) {
                        arrayList2.add(t);
                    }
                }
                ArrayList arrayList3 = arrayList2;
                int[] iArr = stickingIndices.content;
                int i8 = stickingIndices._size;
                int i9 = 0;
                while (i9 < i8) {
                    int i10 = iArr[i9];
                    Iterator<T> it = list.iterator();
                    int i11 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i11 = -1;
                            break;
                        }
                        if (it.next().getIndex() == i10) {
                            break;
                        }
                        i11++;
                    }
                    if (i11 == -1) {
                        remove = function1.invoke(Integer.valueOf(i10));
                    } else {
                        remove = list.remove(i11);
                    }
                    T t2 = remove;
                    int calculateStickingItemOffset = stickyItemsPlacement2.calculateStickingItemOffset(arrayList3, i10, remove.getMainAxisSizeWithSpacings(), i11 == -1 ? Integer.MIN_VALUE : getMainAxisOffset(remove), i3, i4, i5, i6);
                    t2.setNonScrollableItem(true);
                    t2.position(calculateStickingItemOffset, 0, i5, i6);
                    arrayList.add(t2);
                    i9++;
                    stickyItemsPlacement2 = stickyItemsPlacement;
                }
                return arrayList;
            }
        }
        return CollectionsKt.emptyList();
    }
}
