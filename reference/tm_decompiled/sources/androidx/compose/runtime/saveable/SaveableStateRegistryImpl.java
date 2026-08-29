package androidx.compose.runtime.saveable;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: SaveableStateRegistry.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B9\u0012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0018\u00010\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0007\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0006H\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\u0004H\u0016J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00042\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000fH\u0016J\u001c\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00050\u0003H\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000f0\u000e\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/saveable/SaveableStateRegistryImpl;", "Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "restored", "", "", "", "", "canBeSaved", "Lkotlin/Function1;", "", "<init>", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "Landroidx/collection/MutableScatterMap;", "valueProviders", "", "Lkotlin/Function0;", "value", "consumeRestored", "key", "registerProvider", "Landroidx/compose/runtime/saveable/SaveableStateRegistry$Entry;", "valueProvider", "performSave", "runtime-saveable"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class SaveableStateRegistryImpl implements SaveableStateRegistry {
    private final Function1<Object, Boolean> canBeSaved;
    private final MutableScatterMap<String, List<Object>> restored;
    private MutableScatterMap<String, List<Function0<Object>>> valueProviders;

    public SaveableStateRegistryImpl(Map<String, ? extends List<? extends Object>> map, Function1<Object, Boolean> function1) {
        this.canBeSaved = function1;
        this.restored = (map == null || map.isEmpty()) ? null : SaveableStateRegistryKt.toMutableScatterMap(map);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public boolean canBeSaved(Object value) {
        return this.canBeSaved.invoke(value).booleanValue();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Object consumeRestored(String key) {
        MutableScatterMap<String, List<Object>> mutableScatterMap;
        MutableScatterMap<String, List<Object>> mutableScatterMap2 = this.restored;
        List<Object> remove = mutableScatterMap2 != null ? mutableScatterMap2.remove(key) : null;
        List<Object> list = remove;
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (remove.size() > 1 && (mutableScatterMap = this.restored) != null) {
            mutableScatterMap.put(key, remove.subList(1, remove.size()));
        }
        return remove.get(0);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public SaveableStateRegistry.Entry registerProvider(final String key, final Function0<? extends Object> valueProvider) {
        boolean fastIsBlank;
        fastIsBlank = SaveableStateRegistryKt.fastIsBlank(key);
        if (fastIsBlank) {
            throw new IllegalArgumentException("Registered key is empty or blank".toString());
        }
        final MutableScatterMap<String, List<Function0<Object>>> mutableScatterMap = this.valueProviders;
        if (mutableScatterMap == null) {
            mutableScatterMap = ScatterMapKt.mutableScatterMapOf();
            this.valueProviders = mutableScatterMap;
        }
        ArrayList arrayList = mutableScatterMap.get(key);
        if (arrayList == null) {
            arrayList = new ArrayList();
            mutableScatterMap.set(key, arrayList);
        }
        arrayList.add(valueProvider);
        return new SaveableStateRegistry.Entry() { // from class: androidx.compose.runtime.saveable.SaveableStateRegistryImpl$registerProvider$3
            @Override // androidx.compose.runtime.saveable.SaveableStateRegistry.Entry
            public void unregister() {
                List<Function0<Object>> remove = mutableScatterMap.remove(key);
                if (remove != null) {
                    remove.remove(valueProvider);
                }
                List<Function0<Object>> list = remove;
                if (list == null || list.isEmpty()) {
                    return;
                }
                mutableScatterMap.set(key, remove);
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00a7  */
    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Map<String, List<Object>> performSave() {
        char c;
        long j;
        long j2;
        long j3;
        MutableScatterMap<String, List<Function0<Object>>> mutableScatterMap;
        long[] jArr;
        int i;
        long[] jArr2;
        int i2;
        char c2;
        long j4;
        MutableScatterMap<String, List<Object>> mutableScatterMap2 = this.restored;
        if (mutableScatterMap2 == null && this.valueProviders == null) {
            return MapsKt.emptyMap();
        }
        int i3 = 0;
        int size = mutableScatterMap2 != null ? mutableScatterMap2.get_size() : 0;
        MutableScatterMap<String, List<Function0<Object>>> mutableScatterMap3 = this.valueProviders;
        HashMap hashMap = new HashMap(size + (mutableScatterMap3 != null ? mutableScatterMap3.get_size() : 0));
        MutableScatterMap<String, List<Object>> mutableScatterMap4 = this.restored;
        char c3 = 7;
        long j5 = -9187201950435737472L;
        int i4 = 8;
        if (mutableScatterMap4 != null) {
            MutableScatterMap<String, List<Object>> mutableScatterMap5 = mutableScatterMap4;
            Object[] objArr = mutableScatterMap5.keys;
            Object[] objArr2 = mutableScatterMap5.values;
            long[] jArr3 = mutableScatterMap5.metadata;
            int length = jArr3.length - 2;
            if (length >= 0) {
                int i5 = 0;
                j2 = 128;
                while (true) {
                    long j6 = jArr3[i5];
                    j3 = 255;
                    if ((((~j6) << c3) & j6 & j5) != j5) {
                        int i6 = 8 - ((~(i5 - length)) >>> 31);
                        int i7 = 0;
                        while (i7 < i6) {
                            if ((j6 & 255) < 128) {
                                int i8 = (i5 << 3) + i7;
                                c2 = c3;
                                j4 = j5;
                                hashMap.put((String) objArr[i8], (List) objArr2[i8]);
                            } else {
                                c2 = c3;
                                j4 = j5;
                            }
                            j6 >>= 8;
                            i7++;
                            c3 = c2;
                            j5 = j4;
                        }
                        c = c3;
                        j = j5;
                        if (i6 != 8) {
                            break;
                        }
                    } else {
                        c = c3;
                        j = j5;
                    }
                    if (i5 == length) {
                        break;
                    }
                    i5++;
                    c3 = c;
                    j5 = j;
                }
                mutableScatterMap = this.valueProviders;
                if (mutableScatterMap != null) {
                    MutableScatterMap<String, List<Function0<Object>>> mutableScatterMap6 = mutableScatterMap;
                    Object[] objArr3 = mutableScatterMap6.keys;
                    Object[] objArr4 = mutableScatterMap6.values;
                    long[] jArr4 = mutableScatterMap6.metadata;
                    int length2 = jArr4.length - 2;
                    if (length2 >= 0) {
                        int i9 = 0;
                        while (true) {
                            long j7 = jArr4[i9];
                            if ((((~j7) << c) & j7 & j) != j) {
                                int i10 = 8 - ((~(i9 - length2)) >>> 31);
                                int i11 = i3;
                                while (i11 < i10) {
                                    if ((j7 & j3) < j2) {
                                        int i12 = (i9 << 3) + i11;
                                        Object obj = objArr3[i12];
                                        List list = (List) objArr4[i12];
                                        String str = (String) obj;
                                        i2 = i4;
                                        if (list.size() == 1) {
                                            Object invoke = ((Function0) list.get(i3)).invoke();
                                            if (invoke != null) {
                                                if (!canBeSaved(invoke)) {
                                                    throw new IllegalStateException(RememberSaveableKt.generateCannotBeSavedErrorMessage(invoke).toString());
                                                }
                                                Object[] objArr5 = new Object[1];
                                                objArr5[i3] = invoke;
                                                hashMap.put(str, CollectionsKt.arrayListOf(objArr5));
                                            }
                                            jArr2 = jArr4;
                                        } else {
                                            HashMap hashMap2 = hashMap;
                                            int size2 = list.size();
                                            ArrayList arrayList = new ArrayList(size2);
                                            jArr2 = jArr4;
                                            int i13 = 0;
                                            while (i13 < size2) {
                                                int i14 = i13;
                                                Object invoke2 = ((Function0) list.get(i13)).invoke();
                                                if (invoke2 != null && !canBeSaved(invoke2)) {
                                                    throw new IllegalStateException(RememberSaveableKt.generateCannotBeSavedErrorMessage(invoke2).toString());
                                                }
                                                arrayList.add(invoke2);
                                                i13 = i14 + 1;
                                            }
                                            hashMap2.put(str, arrayList);
                                        }
                                    } else {
                                        jArr2 = jArr4;
                                        i2 = i4;
                                    }
                                    j7 >>= i2;
                                    i11++;
                                    i4 = i2;
                                    jArr4 = jArr2;
                                    i3 = 0;
                                }
                                jArr = jArr4;
                                i = i4;
                                if (i10 != i) {
                                    break;
                                }
                            } else {
                                jArr = jArr4;
                                i = i4;
                            }
                            if (i9 == length2) {
                                break;
                            }
                            i9++;
                            i4 = i;
                            jArr4 = jArr;
                            i3 = 0;
                        }
                    }
                }
                return hashMap;
            }
        }
        c = 7;
        j = -9187201950435737472L;
        j2 = 128;
        j3 = 255;
        mutableScatterMap = this.valueProviders;
        if (mutableScatterMap != null) {
        }
        return hashMap;
    }
}
