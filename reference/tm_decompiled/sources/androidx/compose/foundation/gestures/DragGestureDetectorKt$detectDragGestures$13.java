package androidx.compose.foundation.gestures;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* compiled from: DragGestureDetector.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$13", f = "DragGestureDetector.kt", i = {0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7}, l = {249, 255, 1105, 1142, 282, 1181, 1219, 1231}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "initialDown", "awaitTouchSlop", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2d6ksA65w_u24default$iv", "pointer$iv", "touchSlopDetector$iv", "touchSlop$iv", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2d6ksA65w_u24default$iv", "pointer$iv", "touchSlopDetector$iv", "dragEvent$iv", "touchSlop$iv", "$this$awaitEachGesture", "down", "drag", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2d6ksA65w$iv", "pointer$iv", "touchSlopDetector$iv", "touchSlop$iv", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2d6ksA65w$iv", "pointer$iv", "touchSlopDetector$iv", "dragEvent$iv", "touchSlop$iv", "$this$drag_u2dVnAYq1g$iv", "orientation$iv", "$this$awaitDragOrUp_u2djO51t88$iv$iv", "pointer$iv$iv"}, s = {"L$0", "L$0", "L$1", "Z$0", "L$0", "L$1", "L$2", "L$4", "L$5", "F$0", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "F$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$4", "L$5", "F$0", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "F$0", "L$0", "L$2", "L$3", "L$4"})
/* loaded from: classes.dex */
final class DragGestureDetectorKt$detectDragGestures$13 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
    final /* synthetic */ Function0<Unit> $onDragCancel;
    final /* synthetic */ Function1<PointerInputChange, Unit> $onDragEnd;
    final /* synthetic */ Function3<PointerInputChange, PointerInputChange, Offset, Unit> $onDragStart;
    final /* synthetic */ Orientation $orientationLock;
    final /* synthetic */ Ref.LongRef $overSlop;
    final /* synthetic */ Function0<Boolean> $shouldAwaitTouchSlop;
    float F$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DragGestureDetectorKt$detectDragGestures$13(Function0<Boolean> function0, Ref.LongRef longRef, Orientation orientation, Function3<? super PointerInputChange, ? super PointerInputChange, ? super Offset, Unit> function3, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Function0<Unit> function02, Function1<? super PointerInputChange, Unit> function1, Continuation<? super DragGestureDetectorKt$detectDragGestures$13> continuation) {
        super(2, continuation);
        this.$shouldAwaitTouchSlop = function0;
        this.$overSlop = longRef;
        this.$orientationLock = orientation;
        this.$onDragStart = function3;
        this.$onDrag = function2;
        this.$onDragCancel = function02;
        this.$onDragEnd = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DragGestureDetectorKt$detectDragGestures$13 dragGestureDetectorKt$detectDragGestures$13 = new DragGestureDetectorKt$detectDragGestures$13(this.$shouldAwaitTouchSlop, this.$overSlop, this.$orientationLock, this.$onDragStart, this.$onDrag, this.$onDragCancel, this.$onDragEnd, continuation);
        dragGestureDetectorKt$detectDragGestures$13.L$0 = obj;
        return dragGestureDetectorKt$detectDragGestures$13;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
        return ((DragGestureDetectorKt$detectDragGestures$13) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x03cb, code lost:
    
        if (r6 != r1) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x04d2, code lost:
    
        if (androidx.compose.foundation.gestures.DragGestureDetectorKt.m529isPointerUpDmW0f2w(r10.getCurrentEvent(), r2) != false) goto L213;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x01e8, code lost:
    
        if (r11 == r1) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x0183, code lost:
    
        if (r4 == r1) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x0157, code lost:
    
        if (r3 == r1) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x04fd, code lost:
    
        if (r7 != r1) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x058b, code lost:
    
        if (r9 == false) goto L211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0306, code lost:
    
        if (r5 == r1) goto L174;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:35:0x0537, B:48:0x0565], limit reached: 225 */
    /* JADX WARN: Removed duplicated region for block: B:149:0x04a2  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x05a2  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x05a8  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02c8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02dc  */
    /* JADX WARN: Type inference failed for: r9v10, types: [kotlin.jvm.internal.DefaultConstructorMarker] */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:103:0x0402 -> B:62:0x048a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:105:0x040a -> B:62:0x048a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:114:0x0436 -> B:62:0x048a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:116:0x0490 -> B:95:0x03b0). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:122:0x0459 -> B:64:0x02c4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:126:0x0482 -> B:60:0x0484). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:130:0x0497 -> B:64:0x02c4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:153:0x01b6 -> B:147:0x02ab). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:164:0x0225 -> B:146:0x02aa). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:184:0x0276 -> B:147:0x02ab). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:188:0x02a1 -> B:143:0x02a3). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x04fd -> B:7:0x0500). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:81:0x033e -> B:72:0x02eb). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:93:0x0396 -> B:63:0x048c). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        long j;
        AwaitPointerEventScope awaitPointerEventScope;
        Object awaitFirstDown;
        AwaitPointerEventScope awaitPointerEventScope2;
        PointerInputChange pointerInputChange;
        boolean booleanValue;
        Object awaitFirstDown$default;
        PointerInputChange pointerInputChange2;
        long j2;
        float f;
        TouchSlopDetector touchSlopDetector;
        Ref.LongRef longRef;
        AwaitPointerEventScope awaitPointerEventScope3;
        Ref.LongRef longRef2;
        Object obj2;
        AwaitPointerEventScope awaitPointerEventScope4;
        PointerInputChange pointerInputChange3;
        AwaitPointerEventScope awaitPointerEventScope5;
        PointerInputChange pointerInputChange4;
        PointerInputChange pointerInputChange5;
        long id;
        PointerInputChange pointerInputChange6;
        PointerInputChange pointerInputChange7;
        AwaitPointerEventScope awaitPointerEventScope6;
        Object obj3;
        float f2;
        Ref.LongRef longRef3;
        Ref.LongRef longRef4;
        Object obj4;
        TouchSlopDetector touchSlopDetector2;
        AwaitPointerEventScope awaitPointerEventScope7;
        PointerEventPass pointerEventPass;
        PointerInputChange pointerInputChange8;
        Ref.LongRef longRef5;
        PointerInputChange pointerInputChange9;
        PointerInputChange pointerInputChange10;
        long id2;
        Function2<PointerInputChange, Offset, Unit> function2;
        Orientation orientation;
        int size;
        int i;
        PointerInputChange pointerInputChange11;
        PointerInputChange pointerInputChange12;
        boolean z;
        PointerInputChange pointerInputChange13;
        PointerInputChange pointerInputChange14;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = 0;
        int i3 = 1;
        PointerEventPass pointerEventPass2 = 0;
        switch (this.label) {
            case 0:
                j = InlineClassHelperKt.UnspecifiedPackedFloats;
                ResultKt.throwOnFailure(obj);
                awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                this.L$0 = awaitPointerEventScope;
                this.label = 1;
                awaitFirstDown = TapGestureDetectorKt.awaitFirstDown(awaitPointerEventScope, false, PointerEventPass.Initial, this);
                break;
            case 1:
                j = InlineClassHelperKt.UnspecifiedPackedFloats;
                awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                awaitFirstDown = obj;
                awaitPointerEventScope2 = awaitPointerEventScope;
                pointerInputChange = (PointerInputChange) awaitFirstDown;
                booleanValue = this.$shouldAwaitTouchSlop.invoke().booleanValue();
                if (!booleanValue) {
                    pointerInputChange.consume();
                }
                this.L$0 = awaitPointerEventScope2;
                this.L$1 = pointerInputChange;
                this.Z$0 = booleanValue;
                this.label = 2;
                awaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope2, false, null, this, 2, null);
                break;
            case 2:
                j = InlineClassHelperKt.UnspecifiedPackedFloats;
                booleanValue = this.Z$0;
                pointerInputChange = (PointerInputChange) this.L$1;
                AwaitPointerEventScope awaitPointerEventScope8 = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                awaitPointerEventScope2 = awaitPointerEventScope8;
                awaitFirstDown$default = obj;
                pointerInputChange2 = (PointerInputChange) awaitFirstDown$default;
                this.$overSlop.element = Offset.INSTANCE.m4359getZeroF1C5BW0();
                if (!booleanValue) {
                    j2 = 9223372034707292159L;
                    if (ComposeFoundationFlags.DragGesturePickUpEnabled && pointerInputChange == null) {
                        List<PointerInputChange> changes = awaitPointerEventScope2.getCurrentEvent().getChanges();
                        size = changes.size();
                        for (i = 0; i < size; i++) {
                            if (changes.get(i).getPressed()) {
                                pointerInputChange6 = pointerInputChange;
                                pointerInputChange7 = pointerInputChange2;
                                awaitPointerEventScope6 = awaitPointerEventScope2;
                                this.L$0 = awaitPointerEventScope6;
                                this.L$1 = pointerInputChange7;
                                this.L$2 = pointerInputChange6;
                                this.L$3 = null;
                                this.L$4 = null;
                                this.L$5 = null;
                                this.L$6 = null;
                                this.label = 5;
                                obj3 = awaitPointerEventScope6.awaitPointerEvent(PointerEventPass.Final, this);
                                break;
                            }
                        }
                    }
                    if (pointerInputChange != null) {
                        this.$onDragStart.invoke(pointerInputChange2, pointerInputChange, Offset.m4332boximpl(this.$overSlop.element));
                        this.$onDrag.invoke(pointerInputChange, Offset.m4332boximpl(this.$overSlop.element));
                        id2 = pointerInputChange.getId();
                        function2 = this.$onDrag;
                        orientation = ComposeFoundationFlags.DragGesturePickUpEnabled ? null : this.$orientationLock;
                        break;
                    }
                    return Unit.INSTANCE;
                }
                id = pointerInputChange2.getId();
                int type = pointerInputChange2.getType();
                Orientation orientation2 = this.$orientationLock;
                longRef2 = this.$overSlop;
                long m4359getZeroF1C5BW0 = Offset.INSTANCE.m4359getZeroF1C5BW0();
                j2 = 9223372034707292159L;
                if (DragGestureDetectorKt.m529isPointerUpDmW0f2w(awaitPointerEventScope2.getCurrentEvent(), id)) {
                    pointerInputChange = pointerEventPass2;
                    if (pointerInputChange != null && !pointerInputChange.isConsumed()) {
                        i2 = 0;
                        i3 = 1;
                        pointerEventPass2 = 0;
                        id = pointerInputChange2.getId();
                        int type2 = pointerInputChange2.getType();
                        Orientation orientation22 = this.$orientationLock;
                        longRef2 = this.$overSlop;
                        long m4359getZeroF1C5BW02 = Offset.INSTANCE.m4359getZeroF1C5BW0();
                        j2 = 9223372034707292159L;
                        if (DragGestureDetectorKt.m529isPointerUpDmW0f2w(awaitPointerEventScope2.getCurrentEvent(), id)) {
                            f = DragGestureDetectorKt.m530pointerSlopE8SPZFQ(awaitPointerEventScope2.getViewConfiguration(), type2);
                            longRef = new Ref.LongRef();
                            longRef.element = id;
                            touchSlopDetector = new TouchSlopDetector(orientation22, m4359getZeroF1C5BW02, pointerEventPass2);
                            awaitPointerEventScope3 = awaitPointerEventScope2;
                            this.L$0 = awaitPointerEventScope3;
                            this.L$1 = pointerInputChange2;
                            this.L$2 = awaitPointerEventScope2;
                            this.L$3 = longRef2;
                            this.L$4 = longRef;
                            this.L$5 = touchSlopDetector;
                            this.L$6 = pointerEventPass2;
                            this.F$0 = f;
                            this.label = 3;
                            obj2 = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope2, pointerEventPass2, this, i3, pointerEventPass2);
                            break;
                        }
                    }
                    if (ComposeFoundationFlags.DragGesturePickUpEnabled) {
                        List<PointerInputChange> changes2 = awaitPointerEventScope2.getCurrentEvent().getChanges();
                        size = changes2.size();
                        while (i < size) {
                        }
                    }
                    if (pointerInputChange != null) {
                    }
                    return Unit.INSTANCE;
                }
                break;
            case 3:
                j = InlineClassHelperKt.UnspecifiedPackedFloats;
                float f3 = this.F$0;
                TouchSlopDetector touchSlopDetector3 = (TouchSlopDetector) this.L$5;
                Ref.LongRef longRef6 = (Ref.LongRef) this.L$4;
                Ref.LongRef longRef7 = (Ref.LongRef) this.L$3;
                AwaitPointerEventScope awaitPointerEventScope9 = (AwaitPointerEventScope) this.L$2;
                PointerInputChange pointerInputChange15 = (PointerInputChange) this.L$1;
                AwaitPointerEventScope awaitPointerEventScope10 = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                f = f3;
                touchSlopDetector = touchSlopDetector3;
                longRef = longRef6;
                pointerInputChange2 = pointerInputChange15;
                awaitPointerEventScope3 = awaitPointerEventScope10;
                j2 = 9223372034707292159L;
                longRef2 = longRef7;
                awaitPointerEventScope2 = awaitPointerEventScope9;
                obj2 = obj;
                PointerEvent pointerEvent = (PointerEvent) obj2;
                List<PointerInputChange> changes3 = pointerEvent.getChanges();
                int size2 = changes3.size();
                int i4 = i2;
                while (true) {
                    if (i4 < size2) {
                        pointerInputChange3 = changes3.get(i4);
                        awaitPointerEventScope4 = awaitPointerEventScope2;
                        if (!PointerId.m5907equalsimpl0(pointerInputChange3.getId(), longRef.element)) {
                            i4++;
                            awaitPointerEventScope2 = awaitPointerEventScope4;
                        }
                    } else {
                        awaitPointerEventScope4 = awaitPointerEventScope2;
                        pointerInputChange3 = null;
                    }
                }
                PointerInputChange pointerInputChange16 = pointerInputChange3;
                if (pointerInputChange16 != null && !pointerInputChange16.isConsumed()) {
                    if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange16)) {
                        List<PointerInputChange> changes4 = pointerEvent.getChanges();
                        int size3 = changes4.size();
                        int i5 = 0;
                        while (true) {
                            if (i5 < size3) {
                                pointerInputChange5 = changes4.get(i5);
                                if (!pointerInputChange5.getPressed()) {
                                    i5++;
                                }
                            } else {
                                pointerInputChange5 = null;
                            }
                        }
                        PointerInputChange pointerInputChange17 = pointerInputChange5;
                        if (pointerInputChange17 != null) {
                            longRef.element = pointerInputChange17.getId();
                        }
                    } else {
                        long m641addPointerInputChangedBAh8RU = touchSlopDetector.m641addPointerInputChangedBAh8RU(pointerInputChange16, f);
                        if ((m641addPointerInputChangedBAh8RU & j2) == j) {
                            this.L$0 = awaitPointerEventScope3;
                            this.L$1 = pointerInputChange2;
                            awaitPointerEventScope2 = awaitPointerEventScope4;
                            this.L$2 = awaitPointerEventScope2;
                            this.L$3 = longRef2;
                            this.L$4 = longRef;
                            this.L$5 = touchSlopDetector;
                            this.L$6 = pointerInputChange16;
                            this.F$0 = f;
                            this.label = 4;
                            if (awaitPointerEventScope2.awaitPointerEvent(PointerEventPass.Final, this) != coroutine_suspended) {
                                awaitPointerEventScope5 = awaitPointerEventScope3;
                                pointerInputChange4 = pointerInputChange16;
                                if (!pointerInputChange4.isConsumed()) {
                                    awaitPointerEventScope2 = awaitPointerEventScope5;
                                    pointerInputChange = null;
                                    if (pointerInputChange != null) {
                                    }
                                    if (ComposeFoundationFlags.DragGesturePickUpEnabled) {
                                    }
                                    if (pointerInputChange != null) {
                                    }
                                    return Unit.INSTANCE;
                                }
                                awaitPointerEventScope3 = awaitPointerEventScope5;
                                i2 = 0;
                                i3 = 1;
                                pointerEventPass2 = 0;
                                this.L$0 = awaitPointerEventScope3;
                                this.L$1 = pointerInputChange2;
                                this.L$2 = awaitPointerEventScope2;
                                this.L$3 = longRef2;
                                this.L$4 = longRef;
                                this.L$5 = touchSlopDetector;
                                this.L$6 = pointerEventPass2;
                                this.F$0 = f;
                                this.label = 3;
                                obj2 = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope2, pointerEventPass2, this, i3, pointerEventPass2);
                                break;
                            }
                            return coroutine_suspended;
                        }
                        pointerInputChange16.consume();
                        longRef2.element = m641addPointerInputChangedBAh8RU;
                        if (pointerInputChange16.isConsumed()) {
                            awaitPointerEventScope2 = awaitPointerEventScope3;
                            pointerInputChange = pointerInputChange16;
                            if (pointerInputChange != null) {
                                i2 = 0;
                                i3 = 1;
                                pointerEventPass2 = 0;
                                id = pointerInputChange2.getId();
                                int type22 = pointerInputChange2.getType();
                                Orientation orientation222 = this.$orientationLock;
                                longRef2 = this.$overSlop;
                                long m4359getZeroF1C5BW022 = Offset.INSTANCE.m4359getZeroF1C5BW0();
                                j2 = 9223372034707292159L;
                                if (DragGestureDetectorKt.m529isPointerUpDmW0f2w(awaitPointerEventScope2.getCurrentEvent(), id)) {
                                }
                                break;
                            }
                            if (ComposeFoundationFlags.DragGesturePickUpEnabled) {
                            }
                            if (pointerInputChange != null) {
                            }
                            return Unit.INSTANCE;
                        }
                        touchSlopDetector.reset();
                    }
                    awaitPointerEventScope2 = awaitPointerEventScope4;
                    i2 = 0;
                    i3 = 1;
                    pointerEventPass2 = 0;
                    this.L$0 = awaitPointerEventScope3;
                    this.L$1 = pointerInputChange2;
                    this.L$2 = awaitPointerEventScope2;
                    this.L$3 = longRef2;
                    this.L$4 = longRef;
                    this.L$5 = touchSlopDetector;
                    this.L$6 = pointerEventPass2;
                    this.F$0 = f;
                    this.label = 3;
                    obj2 = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope2, pointerEventPass2, this, i3, pointerEventPass2);
                }
                awaitPointerEventScope2 = awaitPointerEventScope3;
                pointerInputChange = null;
                if (pointerInputChange != null) {
                }
                if (ComposeFoundationFlags.DragGesturePickUpEnabled) {
                }
                if (pointerInputChange != null) {
                }
                return Unit.INSTANCE;
            case 4:
                j = InlineClassHelperKt.UnspecifiedPackedFloats;
                float f4 = this.F$0;
                pointerInputChange4 = (PointerInputChange) this.L$6;
                TouchSlopDetector touchSlopDetector4 = (TouchSlopDetector) this.L$5;
                Ref.LongRef longRef8 = (Ref.LongRef) this.L$4;
                Ref.LongRef longRef9 = (Ref.LongRef) this.L$3;
                AwaitPointerEventScope awaitPointerEventScope11 = (AwaitPointerEventScope) this.L$2;
                PointerInputChange pointerInputChange18 = (PointerInputChange) this.L$1;
                awaitPointerEventScope5 = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                f = f4;
                touchSlopDetector = touchSlopDetector4;
                longRef = longRef8;
                awaitPointerEventScope2 = awaitPointerEventScope11;
                pointerInputChange2 = pointerInputChange18;
                j2 = 9223372034707292159L;
                longRef2 = longRef9;
                if (!pointerInputChange4.isConsumed()) {
                }
                break;
            case 5:
                j = InlineClassHelperKt.UnspecifiedPackedFloats;
                pointerInputChange6 = (PointerInputChange) this.L$2;
                pointerInputChange7 = (PointerInputChange) this.L$1;
                awaitPointerEventScope6 = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj3 = obj;
                j2 = 9223372034707292159L;
                PointerEvent pointerEvent2 = (PointerEvent) obj3;
                List<PointerInputChange> changes5 = pointerEvent2.getChanges();
                int size4 = changes5.size();
                int i6 = 0;
                while (true) {
                    if (i6 < size4) {
                        if (changes5.get(i6).isConsumed()) {
                            List<PointerInputChange> changes6 = pointerEvent2.getChanges();
                            int size5 = changes6.size();
                            for (int i7 = 0; i7 < size5; i7++) {
                                if (changes6.get(i7).getPressed()) {
                                    break;
                                }
                            }
                        } else {
                            i6++;
                        }
                    }
                }
                List<PointerInputChange> changes7 = pointerEvent2.getChanges();
                int size6 = changes7.size();
                for (int i8 = 0; i8 < size6; i8++) {
                    if (changes7.get(i8).getPressed()) {
                        PointerInputChange pointerInputChange19 = (PointerInputChange) CollectionsKt.firstOrNull((List) pointerEvent2.getChanges());
                        long m4347minusMKHz9U = Offset.m4347minusMKHz9U(pointerInputChange19 != null ? pointerInputChange19.getPosition() : Offset.INSTANCE.m4359getZeroF1C5BW0(), pointerInputChange7.getPosition());
                        long id3 = pointerInputChange7.getId();
                        int type3 = pointerInputChange7.getType();
                        Orientation orientation3 = this.$orientationLock;
                        longRef3 = this.$overSlop;
                        if (DragGestureDetectorKt.m529isPointerUpDmW0f2w(awaitPointerEventScope6.getCurrentEvent(), id3)) {
                            awaitPointerEventScope2 = awaitPointerEventScope6;
                            pointerInputChange2 = pointerInputChange7;
                            pointerInputChange = null;
                            if (ComposeFoundationFlags.DragGesturePickUpEnabled) {
                            }
                            if (pointerInputChange != null) {
                            }
                            return Unit.INSTANCE;
                        }
                        f2 = DragGestureDetectorKt.m530pointerSlopE8SPZFQ(awaitPointerEventScope6.getViewConfiguration(), type3);
                        longRef4 = new Ref.LongRef();
                        longRef4.element = id3;
                        pointerEventPass = null;
                        touchSlopDetector2 = new TouchSlopDetector(orientation3, m4347minusMKHz9U, null);
                        awaitPointerEventScope7 = awaitPointerEventScope6;
                        this.L$0 = awaitPointerEventScope7;
                        this.L$1 = pointerInputChange7;
                        this.L$2 = awaitPointerEventScope6;
                        this.L$3 = longRef3;
                        this.L$4 = longRef4;
                        this.L$5 = touchSlopDetector2;
                        this.L$6 = pointerEventPass;
                        this.F$0 = f2;
                        this.label = 6;
                        obj4 = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope6, pointerEventPass, this, 1, pointerEventPass);
                        break;
                    }
                }
                awaitPointerEventScope2 = awaitPointerEventScope6;
                pointerInputChange2 = pointerInputChange7;
                pointerInputChange = pointerInputChange6;
                if (ComposeFoundationFlags.DragGesturePickUpEnabled) {
                }
                if (pointerInputChange != null) {
                }
                return Unit.INSTANCE;
            case 6:
                j = InlineClassHelperKt.UnspecifiedPackedFloats;
                f2 = this.F$0;
                TouchSlopDetector touchSlopDetector5 = (TouchSlopDetector) this.L$5;
                Ref.LongRef longRef10 = (Ref.LongRef) this.L$4;
                longRef3 = (Ref.LongRef) this.L$3;
                AwaitPointerEventScope awaitPointerEventScope12 = (AwaitPointerEventScope) this.L$2;
                PointerInputChange pointerInputChange20 = (PointerInputChange) this.L$1;
                AwaitPointerEventScope awaitPointerEventScope13 = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                longRef4 = longRef10;
                awaitPointerEventScope6 = awaitPointerEventScope12;
                obj4 = obj;
                touchSlopDetector2 = touchSlopDetector5;
                pointerInputChange7 = pointerInputChange20;
                awaitPointerEventScope7 = awaitPointerEventScope13;
                j2 = 9223372034707292159L;
                PointerEvent pointerEvent3 = (PointerEvent) obj4;
                List<PointerInputChange> changes8 = pointerEvent3.getChanges();
                int size7 = changes8.size();
                int i9 = 0;
                while (true) {
                    if (i9 < size7) {
                        pointerInputChange8 = changes8.get(i9);
                        List<PointerInputChange> list = changes8;
                        int i10 = size7;
                        if (!PointerId.m5907equalsimpl0(pointerInputChange8.getId(), longRef4.element)) {
                            i9++;
                            changes8 = list;
                            size7 = i10;
                        }
                    } else {
                        pointerInputChange8 = null;
                    }
                }
                PointerInputChange pointerInputChange21 = pointerInputChange8;
                if (pointerInputChange21 != null && !pointerInputChange21.isConsumed()) {
                    if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange21)) {
                        List<PointerInputChange> changes9 = pointerEvent3.getChanges();
                        int size8 = changes9.size();
                        int i11 = 0;
                        while (true) {
                            if (i11 < size8) {
                                pointerInputChange10 = changes9.get(i11);
                                if (!pointerInputChange10.getPressed()) {
                                    i11++;
                                }
                            } else {
                                pointerInputChange10 = null;
                            }
                        }
                        PointerInputChange pointerInputChange22 = pointerInputChange10;
                        if (pointerInputChange22 != null) {
                            longRef4.element = pointerInputChange22.getId();
                        }
                    } else {
                        if ((touchSlopDetector2.m641addPointerInputChangedBAh8RU(pointerInputChange21, f2) & j2) == j) {
                            this.L$0 = awaitPointerEventScope7;
                            this.L$1 = pointerInputChange7;
                            this.L$2 = awaitPointerEventScope6;
                            this.L$3 = longRef3;
                            this.L$4 = longRef4;
                            this.L$5 = touchSlopDetector2;
                            this.L$6 = pointerInputChange21;
                            this.F$0 = f2;
                            this.label = 7;
                            if (awaitPointerEventScope6.awaitPointerEvent(PointerEventPass.Final, this) != coroutine_suspended) {
                                longRef5 = longRef3;
                                pointerInputChange9 = pointerInputChange21;
                                if (!pointerInputChange9.isConsumed()) {
                                    longRef3 = longRef5;
                                }
                            }
                            return coroutine_suspended;
                        }
                        pointerInputChange21.consume();
                        longRef3.element = PointerEventKt.positionChange(pointerInputChange21);
                        if (pointerInputChange21.isConsumed()) {
                            pointerInputChange2 = pointerInputChange7;
                            awaitPointerEventScope2 = awaitPointerEventScope7;
                            pointerInputChange = pointerInputChange21;
                            if (ComposeFoundationFlags.DragGesturePickUpEnabled) {
                            }
                            if (pointerInputChange != null) {
                            }
                            return Unit.INSTANCE;
                        }
                        touchSlopDetector2.reset();
                    }
                    pointerEventPass = null;
                    this.L$0 = awaitPointerEventScope7;
                    this.L$1 = pointerInputChange7;
                    this.L$2 = awaitPointerEventScope6;
                    this.L$3 = longRef3;
                    this.L$4 = longRef4;
                    this.L$5 = touchSlopDetector2;
                    this.L$6 = pointerEventPass;
                    this.F$0 = f2;
                    this.label = 6;
                    obj4 = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope6, pointerEventPass, this, 1, pointerEventPass);
                    break;
                }
                pointerInputChange2 = pointerInputChange7;
                awaitPointerEventScope2 = awaitPointerEventScope7;
                pointerInputChange = null;
                if (ComposeFoundationFlags.DragGesturePickUpEnabled) {
                }
                if (pointerInputChange != null) {
                }
                return Unit.INSTANCE;
            case 7:
                f2 = this.F$0;
                pointerInputChange9 = (PointerInputChange) this.L$6;
                TouchSlopDetector touchSlopDetector6 = (TouchSlopDetector) this.L$5;
                Ref.LongRef longRef11 = (Ref.LongRef) this.L$4;
                longRef5 = (Ref.LongRef) this.L$3;
                AwaitPointerEventScope awaitPointerEventScope14 = (AwaitPointerEventScope) this.L$2;
                PointerInputChange pointerInputChange23 = (PointerInputChange) this.L$1;
                j = InlineClassHelperKt.UnspecifiedPackedFloats;
                AwaitPointerEventScope awaitPointerEventScope15 = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                awaitPointerEventScope7 = awaitPointerEventScope15;
                touchSlopDetector2 = touchSlopDetector6;
                longRef4 = longRef11;
                awaitPointerEventScope6 = awaitPointerEventScope14;
                pointerInputChange7 = pointerInputChange23;
                j2 = 9223372034707292159L;
                if (!pointerInputChange9.isConsumed()) {
                }
                pointerInputChange2 = pointerInputChange7;
                awaitPointerEventScope2 = awaitPointerEventScope7;
                pointerInputChange = null;
                if (ComposeFoundationFlags.DragGesturePickUpEnabled) {
                }
                if (pointerInputChange != null) {
                }
                return Unit.INSTANCE;
            case 8:
                Ref.LongRef longRef12 = (Ref.LongRef) this.L$4;
                AwaitPointerEventScope awaitPointerEventScope16 = (AwaitPointerEventScope) this.L$3;
                Orientation orientation4 = (Orientation) this.L$2;
                Function2<PointerInputChange, Offset, Unit> function22 = (Function2) this.L$1;
                AwaitPointerEventScope awaitPointerEventScope17 = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                Object awaitPointerEvent$default = obj;
                PointerEvent pointerEvent4 = (PointerEvent) awaitPointerEvent$default;
                List<PointerInputChange> changes10 = pointerEvent4.getChanges();
                int size9 = changes10.size();
                int i12 = 0;
                while (true) {
                    if (i12 < size9) {
                        pointerInputChange12 = changes10.get(i12);
                        if (!PointerId.m5907equalsimpl0(pointerInputChange12.getId(), longRef12.element)) {
                            i12++;
                        }
                    } else {
                        pointerInputChange12 = null;
                    }
                }
                PointerInputChange pointerInputChange24 = pointerInputChange12;
                if (pointerInputChange24 == null) {
                    pointerInputChange13 = null;
                } else if (!PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange24)) {
                    long positionChangeIgnoreConsumed = PointerEventKt.positionChangeIgnoreConsumed(pointerInputChange24);
                    if ((orientation4 != null ? Float.intBitsToFloat((int) (orientation4 == Orientation.Vertical ? positionChangeIgnoreConsumed & 4294967295L : positionChangeIgnoreConsumed >> 32)) : Offset.m4341getDistanceimpl(positionChangeIgnoreConsumed)) == 0.0f) {
                        z = true;
                        break;
                    } else {
                        z = false;
                        break;
                    }
                } else {
                    List<PointerInputChange> changes11 = pointerEvent4.getChanges();
                    int size10 = changes11.size();
                    int i13 = 0;
                    while (true) {
                        if (i13 < size10) {
                            PointerInputChange pointerInputChange25 = changes11.get(i13);
                            if (pointerInputChange25.getPressed()) {
                                pointerInputChange14 = pointerInputChange25;
                            } else {
                                i13++;
                            }
                        } else {
                            pointerInputChange14 = null;
                        }
                    }
                    PointerInputChange pointerInputChange26 = pointerInputChange14;
                    if (pointerInputChange26 != null) {
                        longRef12.element = pointerInputChange26.getId();
                        this.L$0 = awaitPointerEventScope17;
                        this.L$1 = function22;
                        this.L$2 = orientation4;
                        this.L$3 = awaitPointerEventScope16;
                        this.L$4 = longRef12;
                        this.L$5 = null;
                        this.L$6 = null;
                        this.label = 8;
                        awaitPointerEvent$default = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope16, null, this, 1, null);
                        break;
                    }
                    pointerInputChange13 = pointerInputChange24;
                }
                if (pointerInputChange13 != null && !pointerInputChange13.isConsumed()) {
                    if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange13)) {
                        pointerInputChange11 = pointerInputChange13;
                        if (pointerInputChange11 != null) {
                            this.$onDragCancel.invoke();
                        } else {
                            this.$onDragEnd.invoke(pointerInputChange11);
                        }
                        return Unit.INSTANCE;
                    }
                    function22.invoke(pointerInputChange13, Offset.m4332boximpl(PointerEventKt.positionChange(pointerInputChange13)));
                    pointerInputChange13.consume();
                    id2 = pointerInputChange13.getId();
                    orientation = orientation4;
                    function2 = function22;
                    awaitPointerEventScope2 = awaitPointerEventScope17;
                    Ref.LongRef longRef13 = new Ref.LongRef();
                    longRef13.element = id2;
                    longRef12 = longRef13;
                    awaitPointerEventScope16 = awaitPointerEventScope2;
                    awaitPointerEventScope17 = awaitPointerEventScope16;
                    function22 = function2;
                    orientation4 = orientation;
                    this.L$0 = awaitPointerEventScope17;
                    this.L$1 = function22;
                    this.L$2 = orientation4;
                    this.L$3 = awaitPointerEventScope16;
                    this.L$4 = longRef12;
                    this.L$5 = null;
                    this.L$6 = null;
                    this.label = 8;
                    awaitPointerEvent$default = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope16, null, this, 1, null);
                }
                pointerInputChange11 = null;
                if (pointerInputChange11 != null) {
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
