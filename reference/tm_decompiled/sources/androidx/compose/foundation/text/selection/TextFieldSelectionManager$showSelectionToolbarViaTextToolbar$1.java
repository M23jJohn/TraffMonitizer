package androidx.compose.foundation.text.selection;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.platform.TextToolbar;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: TextFieldSelectionManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1", f = "TextFieldSelectionManager.kt", i = {0, 0, 0, 0, 0, 0}, l = {992}, m = "invokeSuspend", n = {"this_$iv", "previousSnapshot$iv", "observer$iv", "newSnapshot$iv", "copy", "cut"}, s = {"L$0", "L$2", "L$3", "L$4", "L$5", "L$6"})
/* loaded from: classes.dex */
final class TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    final /* synthetic */ TextFieldSelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1(TextFieldSelectionManager textFieldSelectionManager, Continuation<? super TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1> continuation) {
        super(2, continuation);
        this.this$0 = textFieldSelectionManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Snapshot.Companion companion;
        final TextFieldSelectionManager textFieldSelectionManager;
        Snapshot currentThreadSnapshot;
        Function1<Object, Unit> readObserver;
        Snapshot snapshot;
        Function0<Unit> function0;
        Function0<Unit> function02;
        Rect contentRect;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            companion = Snapshot.INSTANCE;
            textFieldSelectionManager = this.this$0;
            currentThreadSnapshot = companion.getCurrentThreadSnapshot();
            readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
            try {
                Function0<Unit> function03 = textFieldSelectionManager.canCopy$foundation_release() ? new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit invokeSuspend$lambda$5$lambda$0;
                        invokeSuspend$lambda$5$lambda$0 = TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1.invokeSuspend$lambda$5$lambda$0(TextFieldSelectionManager.this);
                        return invokeSuspend$lambda$5$lambda$0;
                    }
                } : null;
                Function0<Unit> function04 = textFieldSelectionManager.canCut$foundation_release() ? new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit invokeSuspend$lambda$5$lambda$1;
                        invokeSuspend$lambda$5$lambda$1 = TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1.invokeSuspend$lambda$5$lambda$1(TextFieldSelectionManager.this);
                        return invokeSuspend$lambda$5$lambda$1;
                    }
                } : null;
                this.L$0 = companion;
                this.L$1 = textFieldSelectionManager;
                this.L$2 = currentThreadSnapshot;
                this.L$3 = readObserver;
                this.L$4 = makeCurrentNonObservable;
                this.L$5 = function03;
                this.L$6 = function04;
                this.label = 1;
                if (textFieldSelectionManager.updateClipboardEntry$foundation_release(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                snapshot = makeCurrentNonObservable;
                function0 = function03;
                function02 = function04;
            } catch (Throwable th) {
                th = th;
                snapshot = makeCurrentNonObservable;
                companion.restoreNonObservable(currentThreadSnapshot, snapshot, readObserver);
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Function0<Unit> function05 = (Function0) this.L$6;
            Function0<Unit> function06 = (Function0) this.L$5;
            snapshot = (Snapshot) this.L$4;
            readObserver = (Function1) this.L$3;
            currentThreadSnapshot = (Snapshot) this.L$2;
            textFieldSelectionManager = (TextFieldSelectionManager) this.L$1;
            companion = (Snapshot.Companion) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                function02 = function05;
                function0 = function06;
            } catch (Throwable th2) {
                th = th2;
                companion.restoreNonObservable(currentThreadSnapshot, snapshot, readObserver);
                throw th;
            }
        }
        Function0<Unit> function07 = textFieldSelectionManager.canPaste$foundation_release() ? new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit invokeSuspend$lambda$5$lambda$2;
                invokeSuspend$lambda$5$lambda$2 = TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1.invokeSuspend$lambda$5$lambda$2(TextFieldSelectionManager.this);
                return invokeSuspend$lambda$5$lambda$2;
            }
        } : null;
        Function0<Unit> function08 = textFieldSelectionManager.canSelectAll$foundation_release() ? new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit invokeSuspend$lambda$5$lambda$3;
                invokeSuspend$lambda$5$lambda$3 = TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1.invokeSuspend$lambda$5$lambda$3(TextFieldSelectionManager.this);
                return invokeSuspend$lambda$5$lambda$3;
            }
        } : null;
        Function0<Unit> function09 = textFieldSelectionManager.canAutofill$foundation_release() ? new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit invokeSuspend$lambda$5$lambda$4;
                invokeSuspend$lambda$5$lambda$4 = TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1.invokeSuspend$lambda$5$lambda$4(TextFieldSelectionManager.this);
                return invokeSuspend$lambda$5$lambda$4;
            }
        } : null;
        TextToolbar textToolbar = textFieldSelectionManager.getTextToolbar();
        if (textToolbar != null) {
            contentRect = textFieldSelectionManager.getContentRect();
            textToolbar.showMenu(contentRect, function0, function07, function02, function08, function09);
        }
        Unit unit = Unit.INSTANCE;
        companion.restoreNonObservable(currentThreadSnapshot, snapshot, readObserver);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$5$lambda$0(TextFieldSelectionManager textFieldSelectionManager) {
        CoroutineScope coroutineScope = textFieldSelectionManager.getCoroutineScope();
        if (coroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$1$copy$1$1(textFieldSelectionManager, null), 1, null);
        }
        textFieldSelectionManager.hideSelectionToolbar$foundation_release();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$5$lambda$1(TextFieldSelectionManager textFieldSelectionManager) {
        CoroutineScope coroutineScope = textFieldSelectionManager.getCoroutineScope();
        if (coroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$1$cut$1$1(textFieldSelectionManager, null), 1, null);
        }
        textFieldSelectionManager.hideSelectionToolbar$foundation_release();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$5$lambda$2(TextFieldSelectionManager textFieldSelectionManager) {
        CoroutineScope coroutineScope = textFieldSelectionManager.getCoroutineScope();
        if (coroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$1$paste$1$1(textFieldSelectionManager, null), 1, null);
        }
        textFieldSelectionManager.hideSelectionToolbar$foundation_release();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$5$lambda$3(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.selectAll$foundation_release();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$5$lambda$4(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.autofill$foundation_release();
        return Unit.INSTANCE;
    }
}
