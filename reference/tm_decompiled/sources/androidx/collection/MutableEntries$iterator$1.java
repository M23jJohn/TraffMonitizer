package androidx.collection;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* compiled from: ScatterMap.kt */
@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0010)\n\u0002\u0010'\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010(\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00020\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u0096\u0002J\u0015\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR,\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"androidx/collection/MutableEntries$iterator$1", "", "", "current", "", "getCurrent", "()I", "setCurrent", "(I)V", "iterator", "", "getIterator", "()Ljava/util/Iterator;", "setIterator", "(Ljava/util/Iterator;)V", "hasNext", "", "next", "remove", "", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MutableEntries$iterator$1<K, V> implements Iterator<Map.Entry<K, V>>, KMutableIterator {
    private int current = -1;
    private Iterator<? extends Map.Entry<K, V>> iterator;
    final /* synthetic */ MutableEntries<K, V> this$0;

    MutableEntries$iterator$1(MutableEntries<K, V> mutableEntries) {
        this.this$0 = mutableEntries;
        this.iterator = SequencesKt.iterator(new AnonymousClass1(mutableEntries, this, null));
    }

    public final Iterator<Map.Entry<K, V>> getIterator() {
        return this.iterator;
    }

    public final void setIterator(Iterator<? extends Map.Entry<K, V>> it) {
        Intrinsics.checkNotNullParameter(it, "<set-?>");
        this.iterator = it;
    }

    public final int getCurrent() {
        return this.current;
    }

    public final void setCurrent(int i) {
        this.current = i;
    }

    /* compiled from: ScatterMap.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010'\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "K", "V", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "androidx.collection.MutableEntries$iterator$1$1", f = "ScatterMap.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {1538}, m = "invokeSuspend", n = {"$this$iterator", "m$iv", "lastIndex$iv", "i$iv", "slot$iv", "bitCount$iv", "j$iv"}, s = {"L$0", "L$3", "I$0", "I$1", "J$0", "I$2", "I$3"})
    /* renamed from: androidx.collection.MutableEntries$iterator$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Map.Entry<K, V>>, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ MutableEntries<K, V> this$0;
        final /* synthetic */ MutableEntries$iterator$1 this$1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MutableEntries<K, V> mutableEntries, MutableEntries$iterator$1 mutableEntries$iterator$1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = mutableEntries;
            this.this$1 = mutableEntries$iterator$1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.this$1, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super Map.Entry<K, V>> sequenceScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x00d1  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0061  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00ce  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0077  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x00b6 -> B:6:0x00bc). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0061 -> B:7:0x0075). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00ce -> B:17:0x00cf). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x007e -> B:5:0x00b9). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            SequenceScope sequenceScope;
            MutableScatterMap mutableScatterMap;
            MutableEntries$iterator$1 mutableEntries$iterator$1;
            MutableEntries<K, V> mutableEntries;
            long[] jArr;
            int length;
            int i;
            long j;
            MutableScatterMap mutableScatterMap2;
            MutableScatterMap mutableScatterMap3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            int i3 = 8;
            int i4 = 1;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                sequenceScope = (SequenceScope) this.L$0;
                mutableScatterMap = ((MutableEntries) this.this$0).parent;
                mutableEntries$iterator$1 = this.this$1;
                mutableEntries = this.this$0;
                jArr = mutableScatterMap.metadata;
                length = jArr.length - 2;
                if (length >= 0) {
                    i = 0;
                    j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                    }
                }
                return Unit.INSTANCE;
            }
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i5 = this.I$3;
            int i6 = this.I$2;
            long j2 = this.J$0;
            int i7 = this.I$1;
            int i8 = this.I$0;
            long[] jArr2 = (long[]) this.L$3;
            MutableEntries<K, V> mutableEntries2 = (MutableEntries) this.L$2;
            MutableEntries$iterator$1 mutableEntries$iterator$12 = (MutableEntries$iterator$1) this.L$1;
            SequenceScope sequenceScope2 = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            int i9 = i3;
            int i10 = i4;
            j2 >>= i9;
            i5 += i10;
            i4 = i10;
            i3 = i9;
            if (i5 < i6) {
                int i11 = i4;
                if (i6 == i3) {
                    int i12 = i8;
                    i = i7;
                    length = i12;
                    jArr = jArr2;
                    mutableEntries = mutableEntries2;
                    mutableEntries$iterator$1 = mutableEntries$iterator$12;
                    sequenceScope = sequenceScope2;
                    if (i != length) {
                        i++;
                        i4 = i11;
                        j = jArr[i];
                        if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                            int i13 = 8 - ((~(i - length)) >>> 31);
                            int i14 = i;
                            i8 = length;
                            i7 = i14;
                            sequenceScope2 = sequenceScope;
                            i5 = 0;
                            jArr2 = jArr;
                            mutableEntries2 = mutableEntries;
                            i6 = i13;
                            mutableEntries$iterator$12 = mutableEntries$iterator$1;
                            j2 = j;
                            if (i5 < i6) {
                                if ((255 & j2) < 128) {
                                    mutableEntries$iterator$12.setCurrent((i7 << 3) + i5);
                                    mutableScatterMap2 = ((MutableEntries) mutableEntries2).parent;
                                    Object[] objArr = mutableScatterMap2.keys;
                                    i9 = i3;
                                    mutableScatterMap3 = ((MutableEntries) mutableEntries2).parent;
                                    MutableMapEntry mutableMapEntry = new MutableMapEntry(objArr, mutableScatterMap3.values, mutableEntries$iterator$12.getCurrent());
                                    this.L$0 = sequenceScope2;
                                    this.L$1 = mutableEntries$iterator$12;
                                    this.L$2 = mutableEntries2;
                                    this.L$3 = jArr2;
                                    this.I$0 = i8;
                                    this.I$1 = i7;
                                    this.J$0 = j2;
                                    this.I$2 = i6;
                                    this.I$3 = i5;
                                    i10 = 1;
                                    this.label = 1;
                                    if (sequenceScope2.yield(mutableMapEntry, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    j2 >>= i9;
                                    i5 += i10;
                                    i4 = i10;
                                    i3 = i9;
                                    if (i5 < i6) {
                                    }
                                }
                                int i92 = i3;
                                int i102 = i4;
                                j2 >>= i92;
                                i5 += i102;
                                i4 = i102;
                                i3 = i92;
                                if (i5 < i6) {
                                }
                            }
                        } else {
                            i11 = i4;
                            if (i != length) {
                            }
                        }
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public Map.Entry<K, V> next() {
        return this.iterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        MutableScatterMap mutableScatterMap;
        if (this.current != -1) {
            mutableScatterMap = ((MutableEntries) this.this$0).parent;
            mutableScatterMap.removeValueAt(this.current);
            this.current = -1;
        }
    }
}
