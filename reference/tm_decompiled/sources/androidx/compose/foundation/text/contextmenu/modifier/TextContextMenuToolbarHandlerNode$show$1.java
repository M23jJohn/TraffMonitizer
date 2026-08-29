package androidx.compose.foundation.text.contextmenu.modifier;

import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuProvider;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TextContextMenuToolbarHandlerModifier.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode$show$1", f = "TextContextMenuToolbarHandlerModifier.kt", i = {}, l = {182, 183, 185, 185}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class TextContextMenuToolbarHandlerNode$show$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TextContextMenuProvider $provider;
    Object L$0;
    int label;
    final /* synthetic */ TextContextMenuToolbarHandlerNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextContextMenuToolbarHandlerNode$show$1(TextContextMenuToolbarHandlerNode textContextMenuToolbarHandlerNode, TextContextMenuProvider textContextMenuProvider, Continuation<? super TextContextMenuToolbarHandlerNode$show$1> continuation) {
        super(2, continuation);
        this.this$0 = textContextMenuToolbarHandlerNode;
        this.$provider = textContextMenuProvider;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TextContextMenuToolbarHandlerNode$show$1(this.this$0, this.$provider, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextContextMenuToolbarHandlerNode$show$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:
    
        if (r7.invoke(r6) == r0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0055, code lost:
    
        if (r6.$provider.showTextContextMenu(r6.this$0, r6) == r0) goto L37;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Throwable th;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Throwable th2) {
            Function1<Continuation<? super Unit>, Object> onHide = this.this$0.getOnHide();
            if (onHide == null) {
                throw th2;
            }
            this.L$0 = th2;
            this.label = 4;
            if (onHide.invoke(this) != coroutine_suspended) {
                th = th2;
            }
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function1<Continuation<? super Unit>, Object> onShow = this.this$0.getOnShow();
            if (onShow != null) {
                this.label = 1;
                if (onShow.invoke(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    Function1<Continuation<? super Unit>, Object> onHide2 = this.this$0.getOnHide();
                    if (onHide2 != null) {
                        this.label = 3;
                    }
                    return Unit.INSTANCE;
                }
                if (i == 3) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                if (i != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                th = (Throwable) this.L$0;
                ResultKt.throwOnFailure(obj);
                throw th;
            }
            ResultKt.throwOnFailure(obj);
        }
        this.label = 2;
    }
}
