package androidx.compose.runtime;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScatterSetWrapperKt;
import androidx.compose.runtime.internal.Trace;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.TransparentObserverMutableSnapshot;
import androidx.compose.runtime.snapshots.TransparentObserverSnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Recomposer.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "parentFrameClock", "Landroidx/compose/runtime/MonotonicFrameClock;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2", f = "Recomposer.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {587, 598}, m = "invokeSuspend", n = {"parentFrameClock", "toRecompose", "toInsert", "toApply", "toLateApply", "toComplete", "modifiedValues", "modifiedValuesSet", "alreadyComposed", "parentFrameClock", "toRecompose", "toInsert", "toApply", "toLateApply", "toComplete", "modifiedValues", "modifiedValuesSet", "alreadyComposed"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"})
/* loaded from: classes.dex */
final class Recomposer$runRecomposeAndApplyChanges$2 extends SuspendLambda implements Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    final /* synthetic */ Recomposer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Recomposer$runRecomposeAndApplyChanges$2(Recomposer recomposer, Continuation<? super Recomposer$runRecomposeAndApplyChanges$2> continuation) {
        super(3, continuation);
        this.this$0 = recomposer;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, MonotonicFrameClock monotonicFrameClock, Continuation<? super Unit> continuation) {
        Recomposer$runRecomposeAndApplyChanges$2 recomposer$runRecomposeAndApplyChanges$2 = new Recomposer$runRecomposeAndApplyChanges$2(this.this$0, continuation);
        recomposer$runRecomposeAndApplyChanges$2.L$0 = monotonicFrameClock;
        return recomposer$runRecomposeAndApplyChanges$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00bd  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0110 -> B:6:0x0118). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x011e -> B:7:0x00b5). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        MonotonicFrameClock monotonicFrameClock;
        MutableScatterSet mutableScatterSetOf;
        MutableScatterSet mutableScatterSetOf2;
        List list;
        Set set;
        final List list2;
        MutableScatterSet mutableScatterSet;
        List list3;
        MutableScatterSet mutableScatterSet2;
        final List list4;
        final MutableScatterSet mutableScatterSet3;
        final List list5;
        final MutableScatterSet mutableScatterSet4;
        boolean recordComposerModifications;
        boolean shouldKeepRecomposing;
        Object awaitWorkAvailable;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            monotonicFrameClock = (MonotonicFrameClock) this.L$0;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            mutableScatterSetOf = ScatterSetKt.mutableScatterSetOf();
            MutableScatterSet mutableScatterSetOf3 = ScatterSetKt.mutableScatterSetOf();
            MutableScatterSet mutableScatterSet5 = new MutableScatterSet(0, 1, null);
            Set wrapIntoSet = ScatterSetWrapperKt.wrapIntoSet(mutableScatterSet5);
            mutableScatterSetOf2 = ScatterSetKt.mutableScatterSetOf();
            list = arrayList;
            set = wrapIntoSet;
            list2 = arrayList2;
            mutableScatterSet = mutableScatterSet5;
            list3 = arrayList3;
            mutableScatterSet2 = mutableScatterSetOf3;
            shouldKeepRecomposing = this.this$0.getShouldKeepRecomposing();
            if (!shouldKeepRecomposing) {
            }
        } else if (i == 1) {
            MutableScatterSet mutableScatterSet6 = (MutableScatterSet) this.L$8;
            set = (Set) this.L$7;
            mutableScatterSet = (MutableScatterSet) this.L$6;
            mutableScatterSet2 = (MutableScatterSet) this.L$5;
            MutableScatterSet mutableScatterSet7 = (MutableScatterSet) this.L$4;
            List list6 = (List) this.L$3;
            list2 = (List) this.L$2;
            List list7 = (List) this.L$1;
            MonotonicFrameClock monotonicFrameClock2 = (MonotonicFrameClock) this.L$0;
            ResultKt.throwOnFailure(obj);
            mutableScatterSet4 = mutableScatterSet6;
            monotonicFrameClock = monotonicFrameClock2;
            list4 = list6;
            list5 = list7;
            mutableScatterSet3 = mutableScatterSet7;
            final Set set2 = set;
            final MutableScatterSet mutableScatterSet8 = mutableScatterSet2;
            final MutableScatterSet mutableScatterSet9 = mutableScatterSet;
            recordComposerModifications = this.this$0.recordComposerModifications();
            if (recordComposerModifications) {
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            MutableScatterSet mutableScatterSet10 = (MutableScatterSet) this.L$8;
            set = (Set) this.L$7;
            mutableScatterSet = (MutableScatterSet) this.L$6;
            mutableScatterSet2 = (MutableScatterSet) this.L$5;
            mutableScatterSetOf = (MutableScatterSet) this.L$4;
            list3 = (List) this.L$3;
            list2 = (List) this.L$2;
            list = (List) this.L$1;
            MonotonicFrameClock monotonicFrameClock3 = (MonotonicFrameClock) this.L$0;
            ResultKt.throwOnFailure(obj);
            mutableScatterSetOf2 = mutableScatterSet10;
            monotonicFrameClock = monotonicFrameClock3;
            this.this$0.discardUnusedMovableContentState();
            shouldKeepRecomposing = this.this$0.getShouldKeepRecomposing();
            if (!shouldKeepRecomposing) {
                this.L$0 = monotonicFrameClock;
                this.L$1 = list;
                this.L$2 = list2;
                this.L$3 = list3;
                this.L$4 = mutableScatterSetOf;
                this.L$5 = mutableScatterSet2;
                this.L$6 = mutableScatterSet;
                this.L$7 = set;
                this.L$8 = mutableScatterSetOf2;
                this.label = 1;
                awaitWorkAvailable = this.this$0.awaitWorkAvailable(this);
                if (awaitWorkAvailable != coroutine_suspended) {
                    List list8 = list;
                    mutableScatterSet3 = mutableScatterSetOf;
                    mutableScatterSet4 = mutableScatterSetOf2;
                    list4 = list3;
                    list5 = list8;
                    final Set set22 = set;
                    final MutableScatterSet mutableScatterSet82 = mutableScatterSet2;
                    final MutableScatterSet mutableScatterSet92 = mutableScatterSet;
                    recordComposerModifications = this.this$0.recordComposerModifications();
                    if (recordComposerModifications) {
                        List list9 = list4;
                        mutableScatterSetOf2 = mutableScatterSet4;
                        mutableScatterSetOf = mutableScatterSet3;
                        list = list5;
                        list3 = list9;
                        mutableScatterSet = mutableScatterSet92;
                        mutableScatterSet2 = mutableScatterSet82;
                        set = set22;
                        shouldKeepRecomposing = this.this$0.getShouldKeepRecomposing();
                        if (!shouldKeepRecomposing) {
                        }
                    } else {
                        final Recomposer recomposer = this.this$0;
                        this.L$0 = monotonicFrameClock;
                        this.L$1 = list5;
                        this.L$2 = list2;
                        this.L$3 = list4;
                        this.L$4 = mutableScatterSet3;
                        this.L$5 = mutableScatterSet82;
                        this.L$6 = mutableScatterSet92;
                        this.L$7 = set22;
                        this.L$8 = mutableScatterSet4;
                        this.label = 2;
                        if (monotonicFrameClock.withFrameNanos(new Function1() { // from class: androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                Unit invokeSuspend$lambda$22;
                                invokeSuspend$lambda$22 = Recomposer$runRecomposeAndApplyChanges$2.invokeSuspend$lambda$22(Recomposer.this, mutableScatterSet92, mutableScatterSet4, list5, list2, mutableScatterSet3, list4, mutableScatterSet82, set22, ((Long) obj2).longValue());
                                return invokeSuspend$lambda$22;
                            }
                        }, this) != coroutine_suspended) {
                            List list10 = list4;
                            mutableScatterSetOf2 = mutableScatterSet4;
                            mutableScatterSetOf = mutableScatterSet3;
                            list = list5;
                            list3 = list10;
                            mutableScatterSet = mutableScatterSet92;
                            mutableScatterSet2 = mutableScatterSet82;
                            set = set22;
                            this.this$0.discardUnusedMovableContentState();
                            shouldKeepRecomposing = this.this$0.getShouldKeepRecomposing();
                            if (!shouldKeepRecomposing) {
                                return Unit.INSTANCE;
                            }
                        }
                    }
                }
                return coroutine_suspended;
            }
        }
    }

    private static final void invokeSuspend$clearRecompositionState(Recomposer recomposer, List<ControlledComposition> list, List<MovableContentStateReference> list2, List<ControlledComposition> list3, MutableScatterSet<ControlledComposition> mutableScatterSet, MutableScatterSet<ControlledComposition> mutableScatterSet2, MutableScatterSet<Object> mutableScatterSet3, MutableScatterSet<ControlledComposition> mutableScatterSet4) {
        long j;
        synchronized (recomposer.stateLock) {
            list.clear();
            list2.clear();
            int size = list3.size();
            for (int i = 0; i < size; i++) {
                ControlledComposition controlledComposition = list3.get(i);
                controlledComposition.abandonChanges();
                recomposer.recordFailedCompositionLocked(controlledComposition);
            }
            list3.clear();
            MutableScatterSet<ControlledComposition> mutableScatterSet5 = mutableScatterSet;
            Object[] objArr = mutableScatterSet5.elements;
            long[] jArr = mutableScatterSet5.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i2 = 0;
                while (true) {
                    long j2 = jArr[i2];
                    j = 255;
                    if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i3 = 8 - ((~(i2 - length)) >>> 31);
                        for (int i4 = 0; i4 < i3; i4++) {
                            if ((j2 & 255) < 128) {
                                ControlledComposition controlledComposition2 = (ControlledComposition) objArr[(i2 << 3) + i4];
                                controlledComposition2.abandonChanges();
                                recomposer.recordFailedCompositionLocked(controlledComposition2);
                            }
                            j2 >>= 8;
                        }
                        if (i3 != 8) {
                            break;
                        }
                    }
                    if (i2 == length) {
                        break;
                    } else {
                        i2++;
                    }
                }
            } else {
                j = 255;
            }
            mutableScatterSet.clear();
            MutableScatterSet<ControlledComposition> mutableScatterSet6 = mutableScatterSet2;
            Object[] objArr2 = mutableScatterSet6.elements;
            long[] jArr2 = mutableScatterSet6.metadata;
            int length2 = jArr2.length - 2;
            if (length2 >= 0) {
                int i5 = 0;
                while (true) {
                    long j3 = jArr2[i5];
                    if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i6 = 8 - ((~(i5 - length2)) >>> 31);
                        for (int i7 = 0; i7 < i6; i7++) {
                            if ((j3 & j) < 128) {
                                ((ControlledComposition) objArr2[(i5 << 3) + i7]).changesApplied();
                            }
                            j3 >>= 8;
                        }
                        if (i6 != 8) {
                            break;
                        }
                    }
                    if (i5 == length2) {
                        break;
                    } else {
                        i5++;
                    }
                }
            }
            mutableScatterSet2.clear();
            mutableScatterSet3.clear();
            MutableScatterSet<ControlledComposition> mutableScatterSet7 = mutableScatterSet4;
            Object[] objArr3 = mutableScatterSet7.elements;
            long[] jArr3 = mutableScatterSet7.metadata;
            int length3 = jArr3.length - 2;
            if (length3 >= 0) {
                int i8 = 0;
                while (true) {
                    long j4 = jArr3[i8];
                    if ((((~j4) << 7) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i9 = 8 - ((~(i8 - length3)) >>> 31);
                        for (int i10 = 0; i10 < i9; i10++) {
                            if ((j4 & j) < 128) {
                                ControlledComposition controlledComposition3 = (ControlledComposition) objArr3[(i8 << 3) + i10];
                                controlledComposition3.abandonChanges();
                                recomposer.recordFailedCompositionLocked(controlledComposition3);
                            }
                            j4 >>= 8;
                        }
                        if (i9 != 8) {
                            break;
                        }
                    }
                    if (i8 == length3) {
                        break;
                    } else {
                        i8++;
                    }
                }
            }
            mutableScatterSet4.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    private static final void invokeSuspend$fillToInsert(List<MovableContentStateReference> list, Recomposer recomposer) {
        List list2;
        List list3;
        list.clear();
        synchronized (recomposer.stateLock) {
            list2 = recomposer.movableContentAwaitingInsert;
            int size = list2.size();
            for (int i = 0; i < size; i++) {
                list.add((MovableContentStateReference) list2.get(i));
            }
            list3 = recomposer.movableContentAwaitingInsert;
            list3.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0331 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Unit invokeSuspend$lambda$22(Recomposer recomposer, MutableScatterSet mutableScatterSet, MutableScatterSet mutableScatterSet2, List list, List list2, MutableScatterSet mutableScatterSet3, List list3, MutableScatterSet mutableScatterSet4, Set set, long j) {
        boolean hasBroadcastFrameClockAwaiters;
        boolean z;
        List performInsertValues;
        ControlledComposition performRecompose;
        char c;
        long j2;
        long j3;
        BroadcastFrameClock broadcastFrameClock;
        Recomposer recomposer2 = recomposer;
        List list4 = list3;
        MutableScatterSet mutableScatterSet5 = mutableScatterSet4;
        hasBroadcastFrameClockAwaiters = recomposer2.getHasBroadcastFrameClockAwaiters();
        if (hasBroadcastFrameClockAwaiters) {
            Object beginSection = Trace.INSTANCE.beginSection("Recomposer:animation");
            try {
                broadcastFrameClock = recomposer2.broadcastFrameClock;
                broadcastFrameClock.sendFrame(j);
                Snapshot.INSTANCE.sendApplyNotifications();
                Unit unit = Unit.INSTANCE;
            } finally {
                Trace.INSTANCE.endSection(beginSection);
            }
        }
        Object beginSection2 = Trace.INSTANCE.beginSection("Recomposer:recompose");
        try {
            recomposer2.recordComposerModifications();
            synchronized (recomposer2.stateLock) {
                MutableVector mutableVector = recomposer2.compositionInvalidations;
                Object[] objArr = mutableVector.content;
                int size = mutableVector.getSize();
                z = 0;
                for (int i = 0; i < size; i++) {
                    list.add((ControlledComposition) objArr[i]);
                }
                recomposer2.compositionInvalidations.clear();
                Unit unit2 = Unit.INSTANCE;
            }
            mutableScatterSet.clear();
            mutableScatterSet2.clear();
            while (true) {
                if (list.isEmpty() && list2.isEmpty()) {
                    break;
                }
                try {
                    int size2 = list.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ControlledComposition controlledComposition = (ControlledComposition) list.get(i2);
                        performRecompose = recomposer2.performRecompose(controlledComposition, mutableScatterSet);
                        if (performRecompose != null) {
                            list3.add(performRecompose);
                            Unit unit3 = Unit.INSTANCE;
                            Unit unit4 = Unit.INSTANCE;
                        }
                        mutableScatterSet2.add(controlledComposition);
                    }
                    list.clear();
                    if (!mutableScatterSet.isNotEmpty() && recomposer2.compositionInvalidations.getSize() == 0) {
                        if (list.isEmpty()) {
                            recomposer2 = recomposer;
                        } else {
                            try {
                                invokeSuspend$fillToInsert(list2, recomposer2);
                                while (!list2.isEmpty()) {
                                    performInsertValues = recomposer2.performInsertValues(list2, mutableScatterSet);
                                    mutableScatterSet3.plusAssign((Iterable) performInsertValues);
                                    invokeSuspend$fillToInsert(list2, recomposer2);
                                }
                            } catch (Throwable th) {
                                Recomposer.processCompositionError$default(recomposer2, th, null, true, 2, null);
                                invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                return Unit.INSTANCE;
                            }
                        }
                        list4 = list3;
                        mutableScatterSet5 = mutableScatterSet4;
                        z = 0;
                    }
                    synchronized (recomposer2.stateLock) {
                        List knownCompositionsLocked = recomposer2.knownCompositionsLocked();
                        int size3 = knownCompositionsLocked.size();
                        for (int i3 = 0; i3 < size3; i3++) {
                            ControlledComposition controlledComposition2 = (ControlledComposition) knownCompositionsLocked.get(i3);
                            if (!mutableScatterSet2.contains(controlledComposition2) && controlledComposition2.observesAnyOf(set)) {
                                list.add(controlledComposition2);
                            }
                        }
                        MutableVector mutableVector2 = recomposer2.compositionInvalidations;
                        int size4 = mutableVector2.getSize();
                        int i4 = 0;
                        for (int i5 = 0; i5 < size4; i5++) {
                            ControlledComposition controlledComposition3 = (ControlledComposition) mutableVector2.content[i5];
                            if (!mutableScatterSet2.contains(controlledComposition3) && !list.contains(controlledComposition3)) {
                                list.add(controlledComposition3);
                                i4++;
                            } else if (i4 > 0) {
                                mutableVector2.content[i5 - i4] = mutableVector2.content[i5];
                            }
                        }
                        int i6 = size4 - i4;
                        ArraysKt.fill(mutableVector2.content, (Object) null, i6, size4);
                        mutableVector2.setSize(i6);
                        Unit unit5 = Unit.INSTANCE;
                    }
                    if (list.isEmpty()) {
                    }
                    list4 = list3;
                    mutableScatterSet5 = mutableScatterSet4;
                    z = 0;
                } catch (Throwable th2) {
                    try {
                        Recomposer.processCompositionError$default(recomposer, th2, null, true, 2, null);
                        invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                        return Unit.INSTANCE;
                    } finally {
                        list.clear();
                    }
                }
            }
            Snapshot current = Snapshot.INSTANCE.getCurrent();
            TransparentObserverSnapshot transparentObserverMutableSnapshot = current instanceof MutableSnapshot ? new TransparentObserverMutableSnapshot((MutableSnapshot) current, null, null, true, false) : new TransparentObserverSnapshot(current, null, true, z);
            try {
                Snapshot makeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                try {
                    if (!list4.isEmpty()) {
                        recomposer2.changeCount = recomposer2.getChangeCount() + 1;
                        try {
                            int size5 = list4.size();
                            for (int i7 = z; i7 < size5; i7++) {
                                mutableScatterSet5.add((ControlledComposition) list4.get(i7));
                            }
                            int size6 = list4.size();
                            for (int i8 = z; i8 < size6; i8++) {
                                ((ControlledComposition) list4.get(i8)).applyChanges();
                            }
                            list4.clear();
                        } catch (Throwable th3) {
                            try {
                                Recomposer.processCompositionError$default(recomposer2, th3, null, false, 6, null);
                                invokeSuspend$clearRecompositionState(recomposer, list, list2, list4, mutableScatterSet3, mutableScatterSet5, mutableScatterSet, mutableScatterSet2);
                                return Unit.INSTANCE;
                            } finally {
                                list3.clear();
                            }
                        }
                    }
                    if (mutableScatterSet3.isNotEmpty()) {
                        try {
                            mutableScatterSet5.plusAssign((ScatterSet) mutableScatterSet3);
                            MutableScatterSet mutableScatterSet6 = mutableScatterSet3;
                            Object[] objArr2 = mutableScatterSet6.elements;
                            long[] jArr = mutableScatterSet6.metadata;
                            c = 7;
                            int length = jArr.length - 2;
                            j2 = 128;
                            int i9 = z;
                            if (length >= 0) {
                                while (true) {
                                    long j4 = jArr[i9];
                                    j3 = 255;
                                    if ((((~j4) << 7) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                                        int i10 = 8 - ((~(i9 - length)) >>> 31);
                                        for (int i11 = 0; i11 < i10; i11++) {
                                            if ((j4 & 255) < 128) {
                                                ((ControlledComposition) objArr2[(i9 << 3) + i11]).applyLateChanges();
                                            }
                                            j4 >>= 8;
                                        }
                                        if (i10 != 8) {
                                            break;
                                        }
                                    }
                                    if (i9 == length) {
                                        break;
                                    }
                                    i9++;
                                }
                            } else {
                                j3 = 255;
                            }
                        } catch (Throwable th4) {
                            try {
                                Recomposer.processCompositionError$default(recomposer, th4, null, false, 6, null);
                                invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet5, mutableScatterSet, mutableScatterSet2);
                                return Unit.INSTANCE;
                            } finally {
                                mutableScatterSet3.clear();
                            }
                        }
                    } else {
                        c = 7;
                        j2 = 128;
                        j3 = 255;
                    }
                    if (mutableScatterSet4.isNotEmpty()) {
                        try {
                            MutableScatterSet mutableScatterSet7 = mutableScatterSet4;
                            Object[] objArr3 = mutableScatterSet7.elements;
                            long[] jArr2 = mutableScatterSet7.metadata;
                            int length2 = jArr2.length - 2;
                            if (length2 >= 0) {
                                int i12 = 0;
                                while (true) {
                                    long j5 = jArr2[i12];
                                    if ((((~j5) << c) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                        int i13 = 8 - ((~(i12 - length2)) >>> 31);
                                        for (int i14 = 0; i14 < i13; i14++) {
                                            if ((j5 & j3) < j2) {
                                                ((ControlledComposition) objArr3[(i12 << 3) + i14]).changesApplied();
                                            }
                                            j5 >>= 8;
                                        }
                                        if (i13 != 8) {
                                            break;
                                        }
                                    }
                                    if (i12 == length2) {
                                        break;
                                    }
                                    i12++;
                                }
                            }
                        } catch (Throwable th5) {
                            try {
                                Recomposer.processCompositionError$default(recomposer, th5, null, false, 6, null);
                                invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                return Unit.INSTANCE;
                            } finally {
                                mutableScatterSet4.clear();
                            }
                        }
                    }
                    Unit unit6 = Unit.INSTANCE;
                    transparentObserverMutableSnapshot.dispose();
                    synchronized (recomposer.stateLock) {
                        recomposer.deriveStateLocked();
                    }
                    Snapshot.INSTANCE.notifyObjectsInitialized();
                    mutableScatterSet2.clear();
                    mutableScatterSet.clear();
                    recomposer.compositionsRemoved = null;
                    Unit unit7 = Unit.INSTANCE;
                    Trace.INSTANCE.endSection(beginSection2);
                    return Unit.INSTANCE;
                } finally {
                    transparentObserverMutableSnapshot.restoreCurrent(makeCurrent);
                }
            } finally {
                transparentObserverMutableSnapshot.dispose();
            }
        } catch (Throwable th6) {
            throw th6;
        }
    }
}
