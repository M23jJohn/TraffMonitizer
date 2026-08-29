package androidx.compose.foundation.text.contextmenu.internal;

import android.os.Handler;
import android.os.Looper;
import android.view.ActionMode;
import android.view.View;
import androidx.compose.foundation.text.contextmenu.internal.AndroidTextContextMenuToolbarProvider;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidTextContextMenuToolbarProvider.android.kt */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.contextmenu.internal.AndroidTextContextMenuToolbarProvider$showTextContextMenu$2", f = "AndroidTextContextMenuToolbarProvider.android.kt", i = {}, l = {180}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class AndroidTextContextMenuToolbarProvider$showTextContextMenu$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ TextContextMenuDataProvider $dataProvider;
    int label;
    final /* synthetic */ AndroidTextContextMenuToolbarProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AndroidTextContextMenuToolbarProvider$showTextContextMenu$2(AndroidTextContextMenuToolbarProvider androidTextContextMenuToolbarProvider, TextContextMenuDataProvider textContextMenuDataProvider, Continuation<? super AndroidTextContextMenuToolbarProvider$showTextContextMenu$2> continuation) {
        super(1, continuation);
        this.this$0 = androidTextContextMenuToolbarProvider;
        this.$dataProvider = textContextMenuDataProvider;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new AndroidTextContextMenuToolbarProvider$showTextContextMenu$2(this.this$0, this.$dataProvider, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((AndroidTextContextMenuToolbarProvider$showTextContextMenu$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SnapshotStateObserver snapshotStateObserver;
        ActionMode actionMode;
        Runnable runnable;
        View view;
        final TextActionModeCallback createActionModeCallback;
        View view2;
        View view3;
        Runnable runnable2;
        View view4;
        Runnable runnable3;
        View view5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final AndroidTextContextMenuToolbarProvider.TextContextMenuSessionImpl textContextMenuSessionImpl = new AndroidTextContextMenuToolbarProvider.TextContextMenuSessionImpl();
                createActionModeCallback = this.this$0.createActionModeCallback(textContextMenuSessionImpl, this.$dataProvider);
                Looper myLooper = Looper.myLooper();
                view2 = this.this$0.view;
                Handler handler = view2.getHandler();
                if (myLooper != (handler != null ? handler.getLooper() : null)) {
                    runnable2 = this.this$0.startActionModeRunnable;
                    if (runnable2 == null) {
                        final AndroidTextContextMenuToolbarProvider androidTextContextMenuToolbarProvider = this.this$0;
                        Runnable runnable4 = new Runnable() { // from class: androidx.compose.foundation.text.contextmenu.internal.AndroidTextContextMenuToolbarProvider$showTextContextMenu$2$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                AndroidTextContextMenuToolbarProvider$showTextContextMenu$2.invokeSuspend$lambda$1(AndroidTextContextMenuToolbarProvider.this, createActionModeCallback, textContextMenuSessionImpl);
                            }
                        };
                        this.this$0.startActionModeRunnable = runnable4;
                        runnable2 = runnable4;
                    }
                    view4 = this.this$0.view;
                    Boxing.boxBoolean(view4.post(runnable2));
                } else {
                    AndroidTextContextMenuToolbarProvider androidTextContextMenuToolbarProvider2 = this.this$0;
                    TextToolbarHelper textToolbarHelper = TextToolbarHelper.INSTANCE;
                    view3 = this.this$0.view;
                    ActionMode startActionMode = textToolbarHelper.startActionMode(view3, createActionModeCallback);
                    if (startActionMode == null) {
                        return Unit.INSTANCE;
                    }
                    androidTextContextMenuToolbarProvider2.actionMode = startActionMode;
                }
                this.label = 1;
                if (textContextMenuSessionImpl.awaitClose(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (actionMode != null) {
                actionMode.finish();
            }
            runnable3 = this.this$0.startActionModeRunnable;
            if (runnable3 != null) {
                view5 = this.this$0.view;
                Boxing.boxBoolean(view5.removeCallbacks(runnable3));
            }
            this.this$0.actionMode = null;
            return Unit.INSTANCE;
        } finally {
            snapshotStateObserver = this.this$0.snapshotStateObserver;
            snapshotStateObserver.clear();
            actionMode = this.this$0.actionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
            runnable = this.this$0.startActionModeRunnable;
            if (runnable != null) {
                view = this.this$0.view;
                Boxing.boxBoolean(view.removeCallbacks(runnable));
            }
            this.this$0.actionMode = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1(AndroidTextContextMenuToolbarProvider androidTextContextMenuToolbarProvider, TextActionModeCallback textActionModeCallback, AndroidTextContextMenuToolbarProvider.TextContextMenuSessionImpl textContextMenuSessionImpl) {
        View view;
        ActionMode actionMode;
        TextToolbarHelper textToolbarHelper = TextToolbarHelper.INSTANCE;
        view = androidTextContextMenuToolbarProvider.view;
        ActionMode startActionMode = textToolbarHelper.startActionMode(view, textActionModeCallback);
        actionMode = androidTextContextMenuToolbarProvider.actionMode;
        Intrinsics.areEqual(actionMode, startActionMode);
        if (startActionMode == null) {
            textContextMenuSessionImpl.close();
        }
    }
}
