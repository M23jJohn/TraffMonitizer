package androidx.compose.foundation.relocation;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.relocation.BringIntoViewModifierNodeKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;

/* compiled from: BringIntoViewRequester.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096@¢\u0006\u0002\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/relocation/BringIntoViewRequesterImpl;", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "<init>", "()V", "nodes", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/foundation/relocation/BringIntoViewRequesterNode;", "getNodes", "()Landroidx/compose/runtime/collection/MutableVector;", "bringIntoView", "", "rect", "Landroidx/compose/ui/geometry/Rect;", "(Landroidx/compose/ui/geometry/Rect;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class BringIntoViewRequesterImpl implements BringIntoViewRequester {
    private final MutableVector<BringIntoViewRequesterNode> nodes = new MutableVector<>(new BringIntoViewRequesterNode[16], 0);

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect bringIntoView$lambda$1$lambda$0(Rect rect) {
        return rect;
    }

    public final MutableVector<BringIntoViewRequesterNode> getNodes() {
        return this.nodes;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x006c -> B:10:0x006f). Please report as a decompilation issue!!! */
    @Override // androidx.compose.foundation.relocation.BringIntoViewRequester
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object bringIntoView(Rect rect, Continuation<? super Unit> continuation) {
        BringIntoViewRequesterImpl$bringIntoView$1 bringIntoViewRequesterImpl$bringIntoView$1;
        int i;
        final Rect rect2;
        int i2;
        Object[] objArr;
        int i3;
        if (continuation instanceof BringIntoViewRequesterImpl$bringIntoView$1) {
            bringIntoViewRequesterImpl$bringIntoView$1 = (BringIntoViewRequesterImpl$bringIntoView$1) continuation;
            if ((bringIntoViewRequesterImpl$bringIntoView$1.label & Integer.MIN_VALUE) != 0) {
                bringIntoViewRequesterImpl$bringIntoView$1.label -= Integer.MIN_VALUE;
                Object obj = bringIntoViewRequesterImpl$bringIntoView$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = bringIntoViewRequesterImpl$bringIntoView$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableVector<BringIntoViewRequesterNode> mutableVector = this.nodes;
                    BringIntoViewRequesterNode[] bringIntoViewRequesterNodeArr = mutableVector.content;
                    int size = mutableVector.getSize();
                    rect2 = rect;
                    i2 = size;
                    objArr = bringIntoViewRequesterNodeArr;
                    i3 = 0;
                    if (i3 < i2) {
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    i2 = bringIntoViewRequesterImpl$bringIntoView$1.I$1;
                    i3 = bringIntoViewRequesterImpl$bringIntoView$1.I$0;
                    objArr = (Object[]) bringIntoViewRequesterImpl$bringIntoView$1.L$1;
                    Rect rect3 = (Rect) bringIntoViewRequesterImpl$bringIntoView$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    rect2 = rect3;
                    i3++;
                    if (i3 < i2) {
                        BringIntoViewRequesterNode bringIntoViewRequesterNode = (BringIntoViewRequesterNode) objArr[i3];
                        Function0 function0 = new Function0() { // from class: androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                Rect bringIntoView$lambda$1$lambda$0;
                                bringIntoView$lambda$1$lambda$0 = BringIntoViewRequesterImpl.bringIntoView$lambda$1$lambda$0(Rect.this);
                                return bringIntoView$lambda$1$lambda$0;
                            }
                        };
                        bringIntoViewRequesterImpl$bringIntoView$1.L$0 = rect2;
                        bringIntoViewRequesterImpl$bringIntoView$1.L$1 = objArr;
                        bringIntoViewRequesterImpl$bringIntoView$1.I$0 = i3;
                        bringIntoViewRequesterImpl$bringIntoView$1.I$1 = i2;
                        bringIntoViewRequesterImpl$bringIntoView$1.label = 1;
                        if (BringIntoViewModifierNodeKt.bringIntoView(bringIntoViewRequesterNode, function0, bringIntoViewRequesterImpl$bringIntoView$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i3++;
                        if (i3 < i2) {
                            return Unit.INSTANCE;
                        }
                    }
                }
            }
        }
        bringIntoViewRequesterImpl$bringIntoView$1 = new BringIntoViewRequesterImpl$bringIntoView$1(this, continuation);
        Object obj2 = bringIntoViewRequesterImpl$bringIntoView$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = bringIntoViewRequesterImpl$bringIntoView$1.label;
        if (i != 0) {
        }
    }
}
