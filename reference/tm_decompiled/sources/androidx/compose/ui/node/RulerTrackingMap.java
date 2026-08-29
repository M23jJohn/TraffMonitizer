package androidx.compose.ui.node;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.layout.Ruler;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LookaheadDelegate.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0014J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0014H\u0086\u0002J\u0011\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\bH\u0086\u0002J8\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001f2 \u0010 \u001a\u001c\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f\u0018\u00010!J\u0006\u0010\"\u001a\u00020\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/compose/ui/node/RulerTrackingMap;", "", "<init>", "()V", "size", "", "rulers", "", "Landroidx/compose/ui/layout/Ruler;", "[Landroidx/compose/ui/layout/Ruler;", "values", "", "accessFlags", "", "layoutNodes", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/ui/node/WeakReference;", "Landroidx/compose/ui/node/LayoutNode;", "newRulers", "getOrDefault", "", "ruler", "defaultValue", "set", "", "value", "contains", "", "notifyChanged", "isLookingAhead", "node", "Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "rulerReaders", "Landroidx/collection/MutableScatterMap;", "clear", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class RulerTrackingMap {
    private int size;
    private Ruler[] rulers = new Ruler[32];
    private float[] values = new float[32];
    private byte[] accessFlags = new byte[32];
    private MutableScatterSet<WeakReference<LayoutNode>> layoutNodes = ScatterSetKt.mutableScatterSetOf();
    private final MutableScatterSet<Ruler> newRulers = ScatterSetKt.mutableScatterSetOf();

    public final float getOrDefault(Ruler ruler, float defaultValue) {
        int indexOf = ArraysKt.indexOf(this.rulers, ruler);
        return indexOf < 0 ? defaultValue : this.values[indexOf];
    }

    public final void set(Ruler ruler, float value) {
        int indexOf = ArraysKt.indexOf(this.rulers, ruler);
        if (indexOf < 0) {
            int i = this.size;
            Ruler[] rulerArr = this.rulers;
            if (i == rulerArr.length) {
                int i2 = i * 2;
                Object[] copyOf = Arrays.copyOf(rulerArr, i2);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
                this.rulers = (Ruler[]) copyOf;
                float[] copyOf2 = Arrays.copyOf(this.values, i2);
                Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(...)");
                this.values = copyOf2;
                byte[] copyOf3 = Arrays.copyOf(this.accessFlags, i2);
                Intrinsics.checkNotNullExpressionValue(copyOf3, "copyOf(...)");
                this.accessFlags = copyOf3;
            }
            this.rulers[i] = ruler;
            this.accessFlags[i] = 3;
            this.values[i] = value;
            this.size++;
            return;
        }
        float[] fArr = this.values;
        if (fArr[indexOf] != value) {
            fArr[indexOf] = value;
            this.accessFlags[indexOf] = 1;
        } else {
            byte[] bArr = this.accessFlags;
            if (bArr[indexOf] == 2) {
                bArr[indexOf] = 0;
            }
        }
    }

    public final boolean contains(Ruler ruler) {
        return ArraysKt.contains(this.rulers, ruler);
    }

    public final void notifyChanged(boolean isLookingAhead, LookaheadCapablePlaceable node, MutableScatterMap<Ruler, MutableScatterSet<WeakReference<LayoutNode>>> rulerReaders) {
        char c;
        char c2;
        long j;
        long j2;
        LayoutNode layoutNode;
        char c3;
        char c4;
        char c5;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            byte b = this.accessFlags[i2];
            if (b == 3) {
                MutableScatterSet<Ruler> mutableScatterSet = this.newRulers;
                Ruler ruler = this.rulers[i2];
                Intrinsics.checkNotNull(ruler);
                mutableScatterSet.plusAssign((MutableScatterSet<Ruler>) ruler);
            } else if (b != 0 && rulerReaders != null) {
                Ruler ruler2 = this.rulers[i2];
                Intrinsics.checkNotNull(ruler2);
                MutableScatterSet<WeakReference<LayoutNode>> remove = rulerReaders.remove(ruler2);
                if (remove != null) {
                    this.layoutNodes.plusAssign(remove);
                }
            }
        }
        int i3 = this.size;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            c = 2;
            if (i4 >= i3) {
                break;
            }
            byte[] bArr = this.accessFlags;
            if (bArr[i4] == 2) {
                i5++;
            } else if (i5 > 0) {
                Ruler[] rulerArr = this.rulers;
                rulerArr[i4 - i5] = rulerArr[i4];
            }
            bArr[i4] = 2;
            i4++;
        }
        int i6 = this.size;
        for (int i7 = i6 - i5; i7 < i6; i7++) {
            this.rulers[i7] = null;
        }
        this.size -= i5;
        LookaheadCapablePlaceable parent = node.getParent();
        MutableScatterSet<Ruler> mutableScatterSet2 = this.newRulers;
        Object[] objArr = mutableScatterSet2.elements;
        long[] jArr = mutableScatterSet2.metadata;
        int length = jArr.length - 2;
        char c6 = 7;
        if (length >= 0) {
            int i8 = 0;
            j = 128;
            while (true) {
                long j3 = jArr[i8];
                j2 = 255;
                if ((((~j3) << c6) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i9 = 8 - ((~(i8 - length)) >>> 31);
                    int i10 = 0;
                    while (i10 < i9) {
                        if ((j3 & 255) < 128) {
                            c4 = c;
                            c5 = c6;
                            (parent == null ? node : parent).invalidateChildrenOfDefiningRuler$ui_release((Ruler) objArr[(i8 << 3) + i10]);
                        } else {
                            c4 = c;
                            c5 = c6;
                        }
                        j3 >>= 8;
                        i10++;
                        c = c4;
                        c6 = c5;
                    }
                    c3 = c;
                    c2 = c6;
                    if (i9 != 8) {
                        break;
                    }
                } else {
                    c3 = c;
                    c2 = c6;
                }
                if (i8 == length) {
                    break;
                }
                i8++;
                c = c3;
                c6 = c2;
            }
        } else {
            c2 = 7;
            j = 128;
            j2 = 255;
        }
        this.newRulers.clear();
        MutableScatterSet<WeakReference<LayoutNode>> mutableScatterSet3 = this.layoutNodes;
        Object[] objArr2 = mutableScatterSet3.elements;
        long[] jArr2 = mutableScatterSet3.metadata;
        int length2 = jArr2.length - 2;
        if (length2 >= 0) {
            int i11 = 0;
            while (true) {
                long j4 = jArr2[i11];
                if ((((~j4) << c2) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i12 = 8 - ((~(i11 - length2)) >>> 31);
                    for (int i13 = 0; i13 < i12; i13++) {
                        if ((j4 & j2) < j && (layoutNode = (LayoutNode) ((WeakReference) objArr2[(i11 << 3) + i13]).get()) != null) {
                            if (isLookingAhead) {
                                layoutNode.requestLookaheadRelayout$ui_release(false);
                            } else {
                                layoutNode.requestRelayout$ui_release(false);
                            }
                        }
                        j4 >>= 8;
                    }
                    if (i12 != 8) {
                        break;
                    }
                }
                if (i11 == length2) {
                    break;
                } else {
                    i11++;
                }
            }
        }
        this.layoutNodes.clear();
    }

    public final void clear() {
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.rulers[i2] = null;
            this.values[i2] = Float.NaN;
            this.accessFlags[i2] = 0;
        }
        this.size = 0;
    }
}
