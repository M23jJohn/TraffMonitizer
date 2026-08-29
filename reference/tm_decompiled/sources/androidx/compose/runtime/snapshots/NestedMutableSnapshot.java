package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.compose.runtime.snapshots.tooling.SnapshotObserverKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Snapshot.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001BO\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b\u0012\u0006\u0010\f\u001a\u00020\u0001¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0017\u001a\u00020\nH\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\nH\u0002R\u0011\u0010\f\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/snapshots/NestedMutableSnapshot;", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "snapshotId", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "readObserver", "Lkotlin/Function1;", "", "", "writeObserver", "parent", "<init>", "(JLandroidx/compose/runtime/snapshots/SnapshotIdSet;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/snapshots/MutableSnapshot;)V", "getParent", "()Landroidx/compose/runtime/snapshots/MutableSnapshot;", "deactivated", "", "root", "Landroidx/compose/runtime/snapshots/Snapshot;", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "dispose", "apply", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "deactivate", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NestedMutableSnapshot extends MutableSnapshot {
    public static final int $stable = 8;
    private boolean deactivated;
    private final MutableSnapshot parent;

    public final MutableSnapshot getParent() {
        return this.parent;
    }

    public NestedMutableSnapshot(long j, SnapshotIdSet snapshotIdSet, Function1<Object, Unit> function1, Function1<Object, Unit> function12, MutableSnapshot mutableSnapshot) {
        super(j, snapshotIdSet, function1, function12);
        this.parent = mutableSnapshot;
        mutableSnapshot.mo4042nestedActivated$runtime(this);
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this.parent.getRoot();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (getDisposed()) {
            return;
        }
        super.dispose();
        deactivate();
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0070, code lost:
    
        if (r2 == null) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008d A[Catch: all -> 0x00de, TryCatch #1 {all -> 0x00de, blocks: (B:19:0x0054, B:24:0x0062, B:26:0x006a, B:28:0x0081, B:30:0x008d, B:31:0x0092, B:36:0x0072, B:38:0x007b), top: B:12:0x003e }] */
    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SnapshotApplyResult apply() {
        Map<StateRecord, ? extends StateRecord> map;
        NestedMutableSnapshot nestedMutableSnapshot;
        Map<StateRecord, ? extends StateRecord> optimisticMerges;
        if (this.parent.getApplied() || this.parent.getDisposed()) {
            return new SnapshotApplyResult.Failure(this);
        }
        MutableScatterSet<StateObject> modified$runtime = getModified$runtime();
        long snapshotId = getSnapshotId();
        if (modified$runtime != null) {
            optimisticMerges = SnapshotKt.optimisticMerges(this.parent.getSnapshotId(), this, this.parent.getInvalid());
            map = optimisticMerges;
        } else {
            map = null;
        }
        synchronized (SnapshotKt.getLock()) {
            try {
                SnapshotKt.validateOpen(this);
            } catch (Throwable th) {
                th = th;
            }
            try {
                if (modified$runtime != null && modified$runtime.get_size() != 0) {
                    nestedMutableSnapshot = this;
                    SnapshotApplyResult innerApplyLocked$runtime = nestedMutableSnapshot.innerApplyLocked$runtime(this.parent.getSnapshotId(), modified$runtime, map, this.parent.getInvalid());
                    if (!Intrinsics.areEqual(innerApplyLocked$runtime, SnapshotApplyResult.Success.INSTANCE)) {
                        return innerApplyLocked$runtime;
                    }
                    MutableScatterSet<StateObject> modified$runtime2 = nestedMutableSnapshot.parent.getModified$runtime();
                    if (modified$runtime2 != null) {
                        modified$runtime2.addAll(modified$runtime);
                    }
                    nestedMutableSnapshot.parent.setModified$runtime(modified$runtime);
                    setModified$runtime(null);
                    if (Intrinsics.compare(nestedMutableSnapshot.parent.getSnapshotId(), snapshotId) < 0) {
                        nestedMutableSnapshot.parent.advance$runtime();
                    }
                    MutableSnapshot mutableSnapshot = nestedMutableSnapshot.parent;
                    mutableSnapshot.setInvalid$runtime(mutableSnapshot.getInvalid().clear(snapshotId).andNot(getPreviousIds()));
                    nestedMutableSnapshot.parent.recordPrevious$runtime(snapshotId);
                    nestedMutableSnapshot.parent.recordPreviousPinnedSnapshot$runtime(takeoverPinnedSnapshot$runtime());
                    nestedMutableSnapshot.parent.recordPreviousList$runtime(getPreviousIds());
                    nestedMutableSnapshot.parent.recordPreviousPinnedSnapshots$runtime(getPreviousPinnedSnapshots());
                    Unit unit = Unit.INSTANCE;
                    setApplied$runtime(true);
                    deactivate();
                    SnapshotObserverKt.dispatchObserverOnApplied(nestedMutableSnapshot, modified$runtime);
                    return SnapshotApplyResult.Success.INSTANCE;
                }
                nestedMutableSnapshot = this;
                closeAndReleasePinning$runtime();
                Unit unit2 = Unit.INSTANCE;
                if (Intrinsics.compare(nestedMutableSnapshot.parent.getSnapshotId(), snapshotId) < 0) {
                }
                MutableSnapshot mutableSnapshot2 = nestedMutableSnapshot.parent;
                mutableSnapshot2.setInvalid$runtime(mutableSnapshot2.getInvalid().clear(snapshotId).andNot(getPreviousIds()));
                nestedMutableSnapshot.parent.recordPrevious$runtime(snapshotId);
                nestedMutableSnapshot.parent.recordPreviousPinnedSnapshot$runtime(takeoverPinnedSnapshot$runtime());
                nestedMutableSnapshot.parent.recordPreviousList$runtime(getPreviousIds());
                nestedMutableSnapshot.parent.recordPreviousPinnedSnapshots$runtime(getPreviousPinnedSnapshots());
                Unit unit3 = Unit.INSTANCE;
                setApplied$runtime(true);
                deactivate();
                SnapshotObserverKt.dispatchObserverOnApplied(nestedMutableSnapshot, modified$runtime);
                return SnapshotApplyResult.Success.INSTANCE;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    private final void deactivate() {
        if (this.deactivated) {
            return;
        }
        this.deactivated = true;
        this.parent.mo4043nestedDeactivated$runtime(this);
    }
}
