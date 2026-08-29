package androidx.compose.runtime.snapshots;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* compiled from: SnapshotIdSet.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "", "Landroidx/compose/runtime/snapshots/SnapshotId;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1", f = "SnapshotIdSet.kt", i = {0, 0, 1, 1, 2, 2}, l = {252, 256, 263}, m = "invokeSuspend", n = {"$this$sequence", "$this$forEach$iv", "$this$sequence", "index", "$this$sequence", "index"}, s = {"L$0", "L$1", "L$0", "I$0", "L$0", "I$0"})
/* loaded from: classes.dex */
final class SnapshotIdSet$iterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Long>, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SnapshotIdSet this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SnapshotIdSet$iterator$1(SnapshotIdSet snapshotIdSet, Continuation<? super SnapshotIdSet$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = snapshotIdSet;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SnapshotIdSet$iterator$1 snapshotIdSet$iterator$1 = new SnapshotIdSet$iterator$1(this.this$0, continuation);
        snapshotIdSet$iterator$1.L$0 = obj;
        return snapshotIdSet$iterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Long> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SnapshotIdSet$iterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b8, code lost:
    
        if (r13.yield(kotlin.coroutines.jvm.internal.Boxing.boxLong(r20.this$0.lowerBound + r2), r20) == r1) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0079, code lost:
    
        if (r15.yield(r4, r20) == r1) goto L40;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x00d6 -> B:8:0x00fa). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00f8 -> B:7:0x00f9). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x009b -> B:22:0x00bb). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00b8 -> B:22:0x00bb). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0079 -> B:33:0x007d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        SequenceScope sequenceScope;
        SequenceScope sequenceScope2;
        int length;
        long[] jArr;
        int i;
        long j;
        SequenceScope sequenceScope3;
        int i2;
        SequenceScope sequenceScope4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        int i4 = 0;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            sequenceScope = (SequenceScope) this.L$0;
            long[] jArr2 = this.this$0.belowBound;
            if (jArr2 != null) {
                sequenceScope2 = sequenceScope;
                length = jArr2.length;
                jArr = jArr2;
                i = 0;
                if (i >= length) {
                }
            }
            j = 1;
            if (this.this$0.lowerSet != 0) {
            }
            if (this.this$0.upperSet != 0) {
            }
            return Unit.INSTANCE;
        }
        if (i3 == 1) {
            length = this.I$1;
            i = this.I$0;
            jArr = (long[]) this.L$1;
            sequenceScope2 = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            i++;
            if (i >= length) {
                Long boxLong = Boxing.boxLong(jArr[i]);
                this.L$0 = sequenceScope2;
                this.L$1 = jArr;
                this.I$0 = i;
                this.I$1 = length;
                this.label = 1;
            } else {
                sequenceScope = sequenceScope2;
                j = 1;
                if (this.this$0.lowerSet != 0) {
                    sequenceScope3 = sequenceScope;
                    i2 = 0;
                    if (i2 >= 64) {
                    }
                }
                if (this.this$0.upperSet != 0) {
                }
                return Unit.INSTANCE;
            }
        } else {
            if (i3 != 2) {
                if (i3 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i5 = this.I$0;
                sequenceScope4 = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                j = 1;
                i4 = i5;
                i4++;
                if (i4 < 64) {
                    if ((this.this$0.upperSet & (j << i4)) != 0) {
                        this.L$0 = sequenceScope4;
                        this.L$1 = null;
                        this.I$0 = i4;
                        this.label = 3;
                        if (sequenceScope4.yield(Boxing.boxLong(this.this$0.lowerBound + i4 + 64), this) != coroutine_suspended) {
                            i5 = i4;
                            i4 = i5;
                        }
                        return coroutine_suspended;
                    }
                    i4++;
                    if (i4 < 64) {
                    }
                }
                return Unit.INSTANCE;
            }
            i2 = this.I$0;
            sequenceScope3 = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            j = 1;
            i2++;
            if (i2 >= 64) {
                sequenceScope = sequenceScope3;
                if (this.this$0.upperSet != 0) {
                    sequenceScope4 = sequenceScope;
                    if (i4 < 64) {
                    }
                }
                return Unit.INSTANCE;
            }
            if ((this.this$0.lowerSet & (j << i2)) != 0) {
                this.L$0 = sequenceScope3;
                this.L$1 = null;
                this.I$0 = i2;
                this.label = 2;
            }
            i2++;
            if (i2 >= 64) {
            }
        }
    }
}
