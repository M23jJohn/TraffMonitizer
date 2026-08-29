package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: LongPressTextDragObserver.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0080@¢\u0006\u0002\u0010\u0005\u001a\u001a\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0080@¢\u0006\u0002\u0010\u0005\u001a\u001a\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010\u0005\u001a\u001a\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0082@¢\u0006\u0002\u0010\u0005¨\u0006\t"}, d2 = {"detectDragGesturesAfterLongPressWithObserver", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "observer", "Landroidx/compose/foundation/text/TextDragObserver;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text/TextDragObserver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectDownAndDragGesturesWithObserver", "detectPreDragGesturesWithObserver", "detectDragGesturesWithObserver", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LongPressTextDragObserverKt {
    public static final Object detectDragGesturesAfterLongPressWithObserver(PointerInputScope pointerInputScope, final TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object detectDragGesturesAfterLongPress = DragGestureDetectorKt.detectDragGesturesAfterLongPress(pointerInputScope, new Function1() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit detectDragGesturesAfterLongPressWithObserver$lambda$0;
                detectDragGesturesAfterLongPressWithObserver$lambda$0 = LongPressTextDragObserverKt.detectDragGesturesAfterLongPressWithObserver$lambda$0(TextDragObserver.this, (Offset) obj);
                return detectDragGesturesAfterLongPressWithObserver$lambda$0;
            }
        }, new Function0() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit detectDragGesturesAfterLongPressWithObserver$lambda$1;
                detectDragGesturesAfterLongPressWithObserver$lambda$1 = LongPressTextDragObserverKt.detectDragGesturesAfterLongPressWithObserver$lambda$1(TextDragObserver.this);
                return detectDragGesturesAfterLongPressWithObserver$lambda$1;
            }
        }, new Function0() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit detectDragGesturesAfterLongPressWithObserver$lambda$2;
                detectDragGesturesAfterLongPressWithObserver$lambda$2 = LongPressTextDragObserverKt.detectDragGesturesAfterLongPressWithObserver$lambda$2(TextDragObserver.this);
                return detectDragGesturesAfterLongPressWithObserver$lambda$2;
            }
        }, new Function2() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit detectDragGesturesAfterLongPressWithObserver$lambda$3;
                detectDragGesturesAfterLongPressWithObserver$lambda$3 = LongPressTextDragObserverKt.detectDragGesturesAfterLongPressWithObserver$lambda$3(TextDragObserver.this, (PointerInputChange) obj, (Offset) obj2);
                return detectDragGesturesAfterLongPressWithObserver$lambda$3;
            }
        }, continuation);
        return detectDragGesturesAfterLongPress == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? detectDragGesturesAfterLongPress : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectDragGesturesAfterLongPressWithObserver$lambda$1(TextDragObserver textDragObserver) {
        textDragObserver.onStop();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectDragGesturesAfterLongPressWithObserver$lambda$3(TextDragObserver textDragObserver, PointerInputChange pointerInputChange, Offset offset) {
        textDragObserver.mo1384onDragk4lQ0M(offset.m4353unboximpl());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectDragGesturesAfterLongPressWithObserver$lambda$0(TextDragObserver textDragObserver, Offset offset) {
        textDragObserver.mo1385onStartk4lQ0M(offset.m4353unboximpl());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectDragGesturesAfterLongPressWithObserver$lambda$2(TextDragObserver textDragObserver) {
        textDragObserver.onCancel();
        return Unit.INSTANCE;
    }

    public static final Object detectDownAndDragGesturesWithObserver(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2(pointerInputScope, textDragObserver, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object detectPreDragGesturesWithObserver(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object awaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new LongPressTextDragObserverKt$detectPreDragGesturesWithObserver$2(textDragObserver, null), continuation);
        return awaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitEachGesture : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object detectDragGesturesWithObserver(PointerInputScope pointerInputScope, final TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object detectDragGestures = DragGestureDetectorKt.detectDragGestures(pointerInputScope, new Function1() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit detectDragGesturesWithObserver$lambda$4;
                detectDragGesturesWithObserver$lambda$4 = LongPressTextDragObserverKt.detectDragGesturesWithObserver$lambda$4(TextDragObserver.this, (Offset) obj);
                return detectDragGesturesWithObserver$lambda$4;
            }
        }, new Function0() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit detectDragGesturesWithObserver$lambda$5;
                detectDragGesturesWithObserver$lambda$5 = LongPressTextDragObserverKt.detectDragGesturesWithObserver$lambda$5(TextDragObserver.this);
                return detectDragGesturesWithObserver$lambda$5;
            }
        }, new Function0() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit detectDragGesturesWithObserver$lambda$6;
                detectDragGesturesWithObserver$lambda$6 = LongPressTextDragObserverKt.detectDragGesturesWithObserver$lambda$6(TextDragObserver.this);
                return detectDragGesturesWithObserver$lambda$6;
            }
        }, new Function2() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit detectDragGesturesWithObserver$lambda$7;
                detectDragGesturesWithObserver$lambda$7 = LongPressTextDragObserverKt.detectDragGesturesWithObserver$lambda$7(TextDragObserver.this, (PointerInputChange) obj, (Offset) obj2);
                return detectDragGesturesWithObserver$lambda$7;
            }
        }, continuation);
        return detectDragGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? detectDragGestures : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectDragGesturesWithObserver$lambda$5(TextDragObserver textDragObserver) {
        textDragObserver.onStop();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectDragGesturesWithObserver$lambda$7(TextDragObserver textDragObserver, PointerInputChange pointerInputChange, Offset offset) {
        textDragObserver.mo1384onDragk4lQ0M(offset.m4353unboximpl());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectDragGesturesWithObserver$lambda$4(TextDragObserver textDragObserver, Offset offset) {
        textDragObserver.mo1385onStartk4lQ0M(offset.m4353unboximpl());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectDragGesturesWithObserver$lambda$6(TextDragObserver textDragObserver) {
        textDragObserver.onCancel();
        return Unit.INSTANCE;
    }
}
