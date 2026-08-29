package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.core.app.NotificationCompat;
import androidx.core.view.PointerIconCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* compiled from: DragGestureDetector.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$2", f = "DragGestureDetector.kt", i = {0, 0, 1, 1, 1}, l = {PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, 1037}, m = "invokeSuspend", n = {"$this$withTimeout", "finished", "$this$withTimeout", NotificationCompat.CATEGORY_EVENT, "finished"}, s = {"L$0", "I$0", "L$0", "L$1", "I$0"})
/* loaded from: classes.dex */
final class DragGestureDetectorKt$awaitLongPressOrCancellation$2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<PointerInputChange> $currentDown;
    final /* synthetic */ Ref.BooleanRef $deepPress;
    final /* synthetic */ Ref.ObjectRef<PointerInputChange> $longPress;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DragGestureDetectorKt$awaitLongPressOrCancellation$2(Ref.BooleanRef booleanRef, Ref.ObjectRef<PointerInputChange> objectRef, Ref.ObjectRef<PointerInputChange> objectRef2, Continuation<? super DragGestureDetectorKt$awaitLongPressOrCancellation$2> continuation) {
        super(2, continuation);
        this.$deepPress = booleanRef;
        this.$currentDown = objectRef;
        this.$longPress = objectRef2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DragGestureDetectorKt$awaitLongPressOrCancellation$2 dragGestureDetectorKt$awaitLongPressOrCancellation$2 = new DragGestureDetectorKt$awaitLongPressOrCancellation$2(this.$deepPress, this.$currentDown, this.$longPress, continuation);
        dragGestureDetectorKt$awaitLongPressOrCancellation$2.L$0 = obj;
        return dragGestureDetectorKt$awaitLongPressOrCancellation$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
        return ((DragGestureDetectorKt$awaitLongPressOrCancellation$2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0052, code lost:
    
        if (r8 == r1) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a4, code lost:
    
        r2 = r6 ? 1 : 0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00e8 A[EDGE_INSN: B:69:0x00e8->B:13:0x00e8 BREAK  A[LOOP:0: B:7:0x00d5->B:10:0x00e5], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x00d7  */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v10, types: [T] */
    /* JADX WARN: Type inference failed for: r9v11, types: [T, androidx.compose.ui.input.pointer.PointerInputChange] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x00c4 -> B:6:0x00c7). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        AwaitPointerEventScope awaitPointerEventScope;
        int i;
        Object obj2;
        Object awaitPointerEvent;
        AwaitPointerEventScope awaitPointerEventScope2;
        PointerEvent pointerEvent;
        int size;
        int i2;
        T t;
        PointerInputChange pointerInputChange;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        Object obj3 = null;
        boolean z = true;
        z = true;
        z = true;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
            i = 0;
            if (i != 0) {
            }
        } else {
            if (i3 == 1) {
                i = this.I$0;
                awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = obj;
                PointerEvent pointerEvent2 = (PointerEvent) obj2;
                List<PointerInputChange> changes = pointerEvent2.getChanges();
                int size2 = changes.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size2) {
                        i = z ? 1 : 0;
                        break;
                    }
                    if (!PointerEventKt.changedToUpIgnoreConsumed(changes.get(i4))) {
                        break;
                    }
                    i4++;
                }
                List<PointerInputChange> changes2 = pointerEvent2.getChanges();
                int size3 = changes2.size();
                for (int i5 = 0; i5 < size3; i5++) {
                    PointerInputChange pointerInputChange2 = changes2.get(i5);
                    if (pointerInputChange2.isConsumed() || PointerEventKt.m5870isOutOfBoundsjwHxaWs(pointerInputChange2, awaitPointerEventScope.mo5820getSizeYbymL2g(), awaitPointerEventScope.mo5819getExtendedTouchPaddingNHjbRc())) {
                        break;
                    }
                }
                if (TapGestureDetector_androidKt.isDeepPress(pointerEvent2)) {
                    this.$deepPress.element = z;
                    i = z ? 1 : 0;
                }
                this.L$0 = awaitPointerEventScope;
                this.L$1 = pointerEvent2;
                this.I$0 = i;
                this.label = 2;
                awaitPointerEvent = awaitPointerEventScope.awaitPointerEvent(PointerEventPass.Final, this);
                if (awaitPointerEvent != coroutine_suspended) {
                    awaitPointerEventScope2 = awaitPointerEventScope;
                    pointerEvent = pointerEvent2;
                    List<PointerInputChange> changes3 = ((PointerEvent) awaitPointerEvent).getChanges();
                    size = changes3.size();
                    i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                        }
                        i2++;
                    }
                    if (!DragGestureDetectorKt.m529isPointerUpDmW0f2w(pointerEvent, this.$currentDown.element.getId())) {
                    }
                    awaitPointerEventScope = awaitPointerEventScope2;
                    obj3 = null;
                    z = true;
                    if (i != 0) {
                    }
                }
                return coroutine_suspended;
            }
            if (i3 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$0;
            pointerEvent = (PointerEvent) this.L$1;
            awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            awaitPointerEvent = obj;
            List<PointerInputChange> changes32 = ((PointerEvent) awaitPointerEvent).getChanges();
            size = changes32.size();
            i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                if (changes32.get(i2).isConsumed()) {
                    i = z ? 1 : 0;
                    break;
                }
                i2++;
            }
            if (!DragGestureDetectorKt.m529isPointerUpDmW0f2w(pointerEvent, this.$currentDown.element.getId())) {
                List<PointerInputChange> changes4 = pointerEvent.getChanges();
                int size4 = changes4.size();
                int i6 = 0;
                while (true) {
                    if (i6 >= size4) {
                        pointerInputChange = null;
                        break;
                    }
                    pointerInputChange = changes4.get(i6);
                    if (pointerInputChange.getPressed()) {
                        break;
                    }
                    i6++;
                }
                PointerInputChange pointerInputChange3 = pointerInputChange;
                if (pointerInputChange3 != 0) {
                    this.$currentDown.element = pointerInputChange3;
                    this.$longPress.element = this.$currentDown.element;
                } else {
                    i = z ? 1 : 0;
                    awaitPointerEventScope = awaitPointerEventScope2;
                    obj3 = null;
                    if (i != 0) {
                        this.L$0 = awaitPointerEventScope;
                        this.L$1 = obj3;
                        this.I$0 = i;
                        this.label = z ? 1 : 0;
                        obj2 = awaitPointerEventScope.awaitPointerEvent(PointerEventPass.Main, this);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
            } else {
                Ref.ObjectRef<PointerInputChange> objectRef = this.$longPress;
                List<PointerInputChange> changes5 = pointerEvent.getChanges();
                Ref.ObjectRef<PointerInputChange> objectRef2 = this.$currentDown;
                int size5 = changes5.size();
                int i7 = 0;
                while (true) {
                    if (i7 >= size5) {
                        t = 0;
                        break;
                    }
                    t = changes5.get(i7);
                    Ref.ObjectRef<PointerInputChange> objectRef3 = objectRef2;
                    if (PointerId.m5907equalsimpl0(((PointerInputChange) t).getId(), objectRef2.element.getId())) {
                        break;
                    }
                    i7++;
                    objectRef2 = objectRef3;
                }
                objectRef.element = t;
            }
            awaitPointerEventScope = awaitPointerEventScope2;
            obj3 = null;
            z = true;
            if (i != 0) {
            }
        }
    }
}
