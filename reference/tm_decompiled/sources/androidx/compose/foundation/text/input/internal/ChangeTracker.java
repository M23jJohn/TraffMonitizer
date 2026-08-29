package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChangeTracker.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001:\u0001!B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0003\u0010\u0004J\u001e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\nJ\u0006\u0010\u0012\u001a\u00020\u000eJ\u0017\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0019\u0010\u0017J\b\u0010\u001a\u001a\u00020\u001bH\u0016J*\u0010\u001c\u001a\u00020\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\""}, d2 = {"Landroidx/compose/foundation/text/input/internal/ChangeTracker;", "Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "initialChanges", "<init>", "(Landroidx/compose/foundation/text/input/internal/ChangeTracker;)V", "_changes", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/foundation/text/input/internal/ChangeTracker$Change;", "_changesTemp", "changeCount", "", "getChangeCount", "()I", "trackChange", "", "preStart", "preEnd", "postLength", "clearChanges", "getRange", "Landroidx/compose/ui/text/TextRange;", "changeIndex", "getRange--jx7JFs", "(I)J", "getOriginalRange", "getOriginalRange--jx7JFs", "toString", "", "appendNewChange", "mergedOverlappingChange", "preMin", "preMax", "postDelta", "Change", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ChangeTracker implements TextFieldBuffer.ChangeList {
    public static final int $stable = 8;
    private MutableVector<Change> _changes;
    private MutableVector<Change> _changesTemp;

    /* JADX WARN: Multi-variable type inference failed */
    public ChangeTracker() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ChangeTracker(ChangeTracker changeTracker) {
        MutableVector<Change> mutableVector;
        this._changes = new MutableVector<>(new Change[16], 0);
        this._changesTemp = new MutableVector<>(new Change[16], 0);
        if (changeTracker == null || (mutableVector = changeTracker._changes) == null) {
            return;
        }
        Change[] changeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            Change change = changeArr[i];
            this._changes.add(new Change(change.getPreStart(), change.getPreEnd(), change.getOriginalStart(), change.getOriginalEnd()));
        }
    }

    public /* synthetic */ ChangeTracker(ChangeTracker changeTracker, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : changeTracker);
    }

    @Override // androidx.compose.foundation.text.input.TextFieldBuffer.ChangeList
    public int getChangeCount() {
        return this._changes.getSize();
    }

    public final void trackChange(int preStart, int preEnd, int postLength) {
        int preEnd2;
        if (preStart == preEnd && postLength == 0) {
            return;
        }
        int min = Math.min(preStart, preEnd);
        int max = Math.max(preStart, preEnd);
        int i = postLength - (max - min);
        Change change = null;
        boolean z = false;
        for (int i2 = 0; i2 < this._changes.getSize(); i2++) {
            Change change2 = this._changes.content[i2];
            int preStart2 = change2.getPreStart();
            if ((min > preStart2 || preStart2 > max) && (min > (preEnd2 = change2.getPreEnd()) || preEnd2 > max)) {
                int preStart3 = change2.getPreStart();
                if (min > change2.getPreEnd() || preStart3 > min) {
                    int preStart4 = change2.getPreStart();
                    if (max > change2.getPreEnd() || preStart4 > max) {
                        if (change2.getPreStart() > max && !z) {
                            appendNewChange(change, min, max, i);
                            z = true;
                        }
                        if (z) {
                            change2.setPreStart(change2.getPreStart() + i);
                            change2.setPreEnd(change2.getPreEnd() + i);
                        }
                        this._changesTemp.add(change2);
                    }
                }
            }
            if (change == null) {
                change = change2;
            } else {
                change.setPreEnd(change2.getPreEnd());
                change.setOriginalEnd(change2.getOriginalEnd());
            }
        }
        if (!z) {
            appendNewChange(change, min, max, i);
        }
        MutableVector<Change> mutableVector = this._changes;
        this._changes = this._changesTemp;
        this._changesTemp = mutableVector;
        mutableVector.clear();
    }

    public final void clearChanges() {
        this._changes.clear();
    }

    @Override // androidx.compose.foundation.text.input.TextFieldBuffer.ChangeList
    /* renamed from: getRange--jx7JFs */
    public long mo1478getRangejx7JFs(int changeIndex) {
        Change change = this._changes.content[changeIndex];
        return TextRangeKt.TextRange(change.getPreStart(), change.getPreEnd());
    }

    @Override // androidx.compose.foundation.text.input.TextFieldBuffer.ChangeList
    /* renamed from: getOriginalRange--jx7JFs */
    public long mo1477getOriginalRangejx7JFs(int changeIndex) {
        Change change = this._changes.content[changeIndex];
        return TextRangeKt.TextRange(change.getOriginalStart(), change.getOriginalEnd());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChangeList(changes=[");
        MutableVector<Change> mutableVector = this._changes;
        Change[] changeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            Change change = changeArr[i];
            sb.append("(" + change.getOriginalStart() + ',' + change.getOriginalEnd() + ")->(" + change.getPreStart() + ',' + change.getPreEnd() + ')');
            if (i < getChangeCount() - 1) {
                sb.append(", ");
            }
        }
        sb.append("])");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    private final void appendNewChange(Change mergedOverlappingChange, int preMin, int preMax, int postDelta) {
        int preEnd;
        if (this._changesTemp.getSize() == 0) {
            preEnd = 0;
        } else {
            Change last = this._changesTemp.last();
            preEnd = last.getPreEnd() - last.getOriginalEnd();
        }
        if (mergedOverlappingChange == null) {
            int i = preMin - preEnd;
            mergedOverlappingChange = new Change(preMin, preMax + postDelta, i, (preMax - preMin) + i);
        } else {
            if (mergedOverlappingChange.getPreStart() > preMin) {
                mergedOverlappingChange.setPreStart(preMin);
                mergedOverlappingChange.setOriginalStart(preMin);
            }
            if (preMax > mergedOverlappingChange.getPreEnd()) {
                int preEnd2 = mergedOverlappingChange.getPreEnd() - mergedOverlappingChange.getOriginalEnd();
                mergedOverlappingChange.setPreEnd(preMax);
                mergedOverlappingChange.setOriginalEnd(preMax - preEnd2);
            }
            mergedOverlappingChange.setPreEnd(mergedOverlappingChange.getPreEnd() + postDelta);
        }
        this._changesTemp.add(mergedOverlappingChange);
    }

    /* compiled from: ChangeTracker.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\f¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/text/input/internal/ChangeTracker$Change;", "", "preStart", "", "preEnd", "originalStart", "originalEnd", "<init>", "(IIII)V", "getPreStart", "()I", "setPreStart", "(I)V", "getPreEnd", "setPreEnd", "getOriginalStart", "setOriginalStart", "getOriginalEnd", "setOriginalEnd", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class Change {
        private int originalEnd;
        private int originalStart;
        private int preEnd;
        private int preStart;

        public static /* synthetic */ Change copy$default(Change change, int i, int i2, int i3, int i4, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                i = change.preStart;
            }
            if ((i5 & 2) != 0) {
                i2 = change.preEnd;
            }
            if ((i5 & 4) != 0) {
                i3 = change.originalStart;
            }
            if ((i5 & 8) != 0) {
                i4 = change.originalEnd;
            }
            return change.copy(i, i2, i3, i4);
        }

        /* renamed from: component1, reason: from getter */
        public final int getPreStart() {
            return this.preStart;
        }

        /* renamed from: component2, reason: from getter */
        public final int getPreEnd() {
            return this.preEnd;
        }

        /* renamed from: component3, reason: from getter */
        public final int getOriginalStart() {
            return this.originalStart;
        }

        /* renamed from: component4, reason: from getter */
        public final int getOriginalEnd() {
            return this.originalEnd;
        }

        public final Change copy(int preStart, int preEnd, int originalStart, int originalEnd) {
            return new Change(preStart, preEnd, originalStart, originalEnd);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Change)) {
                return false;
            }
            Change change = (Change) other;
            return this.preStart == change.preStart && this.preEnd == change.preEnd && this.originalStart == change.originalStart && this.originalEnd == change.originalEnd;
        }

        public int hashCode() {
            return (((((this.preStart * 31) + this.preEnd) * 31) + this.originalStart) * 31) + this.originalEnd;
        }

        public String toString() {
            return "Change(preStart=" + this.preStart + ", preEnd=" + this.preEnd + ", originalStart=" + this.originalStart + ", originalEnd=" + this.originalEnd + ')';
        }

        public Change(int i, int i2, int i3, int i4) {
            this.preStart = i;
            this.preEnd = i2;
            this.originalStart = i3;
            this.originalEnd = i4;
        }

        public final int getPreStart() {
            return this.preStart;
        }

        public final void setPreStart(int i) {
            this.preStart = i;
        }

        public final int getPreEnd() {
            return this.preEnd;
        }

        public final void setPreEnd(int i) {
            this.preEnd = i;
        }

        public final int getOriginalStart() {
            return this.originalStart;
        }

        public final void setOriginalStart(int i) {
            this.originalStart = i;
        }

        public final int getOriginalEnd() {
            return this.originalEnd;
        }

        public final void setOriginalEnd(int i) {
            this.originalEnd = i;
        }
    }
}
