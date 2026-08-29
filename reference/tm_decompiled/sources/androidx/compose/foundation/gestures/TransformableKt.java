package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.TransformEvent;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelResult;

/* compiled from: Transformable.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a:\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a(\u0010\f\u001a\u00020\r*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0014\u001a\u001a\u0010\u0015\u001a\u00020\t*\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0017\u001a\u001c\u0010\u0018\u001a\u0004\u0018\u00010\t*\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0017\u001a<\u0010\u0019\u001a\u00020\r*\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\bH\u0082@¢\u0006\u0002\u0010\u001b\"\u000e\u0010\n\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"transformable", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/gestures/TransformableState;", "lockRotationOnZoomPan", "", "enabled", "canPan", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "SCROLL_FACTOR", "", "detectZoomByCtrlMouseScroll", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "channel", "Lkotlinx/coroutines/channels/Channel;", "Landroidx/compose/foundation/gestures/TransformEvent;", "scrollConfig", "Landroidx/compose/foundation/gestures/ScrollConfig;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlinx/coroutines/channels/Channel;Landroidx/compose/foundation/gestures/ScrollConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFirstCtrlMouseScroll", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/gestures/ScrollConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitCtrlMouseScrollOrNull", "detectZoom", "panZoomLock", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;ZLkotlinx/coroutines/channels/Channel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TransformableKt {
    public static final float SCROLL_FACTOR = 545.0f;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean transformable$lambda$0(Offset offset) {
        return true;
    }

    public static /* synthetic */ Modifier transformable$default(Modifier modifier, TransformableState transformableState, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        return transformable(modifier, transformableState, z, z2);
    }

    public static final Modifier transformable(Modifier modifier, TransformableState transformableState, boolean z, boolean z2) {
        return transformable(modifier, transformableState, new Function1() { // from class: androidx.compose.foundation.gestures.TransformableKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean transformable$lambda$0;
                transformable$lambda$0 = TransformableKt.transformable$lambda$0((Offset) obj);
                return Boolean.valueOf(transformable$lambda$0);
            }
        }, z, z2);
    }

    public static /* synthetic */ Modifier transformable$default(Modifier modifier, TransformableState transformableState, Function1 function1, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = true;
        }
        return transformable(modifier, transformableState, function1, z, z2);
    }

    public static final Modifier transformable(Modifier modifier, TransformableState transformableState, Function1<? super Offset, Boolean> function1, boolean z, boolean z2) {
        return modifier.then(new TransformableElement(transformableState, function1, z, z2));
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0051 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x004a -> B:10:0x004d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final java.lang.Object awaitFirstCtrlMouseScroll(androidx.compose.ui.input.pointer.AwaitPointerEventScope r5, androidx.compose.foundation.gestures.ScrollConfig r6, kotlin.coroutines.Continuation<? super androidx.compose.ui.geometry.Offset> r7) {
        /*
            boolean r0 = r7 instanceof androidx.compose.foundation.gestures.TransformableKt$awaitFirstCtrlMouseScroll$1
            if (r0 == 0) goto L14
            r0 = r7
            androidx.compose.foundation.gestures.TransformableKt$awaitFirstCtrlMouseScroll$1 r0 = (androidx.compose.foundation.gestures.TransformableKt$awaitFirstCtrlMouseScroll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            androidx.compose.foundation.gestures.TransformableKt$awaitFirstCtrlMouseScroll$1 r0 = new androidx.compose.foundation.gestures.TransformableKt$awaitFirstCtrlMouseScroll$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r5 = r0.L$1
            androidx.compose.foundation.gestures.ScrollConfig r5 = (androidx.compose.foundation.gestures.ScrollConfig) r5
            java.lang.Object r6 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r6 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r6
            kotlin.ResultKt.throwOnFailure(r7)
            r4 = r6
            r6 = r5
            r5 = r4
            goto L4d
        L35:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3d:
            kotlin.ResultKt.throwOnFailure(r7)
        L40:
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = awaitCtrlMouseScrollOrNull(r5, r6, r0)
            if (r7 != r1) goto L4d
            return r1
        L4d:
            androidx.compose.ui.geometry.Offset r7 = (androidx.compose.ui.geometry.Offset) r7
            if (r7 == 0) goto L40
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformableKt.awaitFirstCtrlMouseScroll(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.gestures.ScrollConfig, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object awaitCtrlMouseScrollOrNull(AwaitPointerEventScope awaitPointerEventScope, ScrollConfig scrollConfig, Continuation<? super Offset> continuation) {
        TransformableKt$awaitCtrlMouseScrollOrNull$1 transformableKt$awaitCtrlMouseScrollOrNull$1;
        int i;
        PointerEvent pointerEvent;
        if (continuation instanceof TransformableKt$awaitCtrlMouseScrollOrNull$1) {
            transformableKt$awaitCtrlMouseScrollOrNull$1 = (TransformableKt$awaitCtrlMouseScrollOrNull$1) continuation;
            if ((transformableKt$awaitCtrlMouseScrollOrNull$1.label & Integer.MIN_VALUE) != 0) {
                transformableKt$awaitCtrlMouseScrollOrNull$1.label -= Integer.MIN_VALUE;
                Object obj = transformableKt$awaitCtrlMouseScrollOrNull$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = transformableKt$awaitCtrlMouseScrollOrNull$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    transformableKt$awaitCtrlMouseScrollOrNull$1.L$0 = awaitPointerEventScope;
                    transformableKt$awaitCtrlMouseScrollOrNull$1.L$1 = scrollConfig;
                    transformableKt$awaitCtrlMouseScrollOrNull$1.label = 1;
                    obj = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope, null, transformableKt$awaitCtrlMouseScrollOrNull$1, 1, null);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    scrollConfig = (ScrollConfig) transformableKt$awaitCtrlMouseScrollOrNull$1.L$1;
                    awaitPointerEventScope = (AwaitPointerEventScope) transformableKt$awaitCtrlMouseScrollOrNull$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                pointerEvent = (PointerEvent) obj;
                if (PointerEvent_androidKt.m5892isCtrlPressed5xRPYO0(pointerEvent.getKeyboardModifiers()) || !PointerEventType.m5874equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m5883getScroll7fucELk())) {
                    return null;
                }
                long mo487calculateMouseWheelScroll8xgXZGE = scrollConfig.mo487calculateMouseWheelScroll8xgXZGE(awaitPointerEventScope, pointerEvent, awaitPointerEventScope.mo5820getSizeYbymL2g());
                if (Offset.m4340equalsimpl0(mo487calculateMouseWheelScroll8xgXZGE, Offset.INSTANCE.m4359getZeroF1C5BW0())) {
                    return null;
                }
                List<PointerInputChange> changes = pointerEvent.getChanges();
                int size = changes.size();
                for (int i2 = 0; i2 < size; i2++) {
                    changes.get(i2).consume();
                }
                return Offset.m4332boximpl(mo487calculateMouseWheelScroll8xgXZGE);
            }
        }
        transformableKt$awaitCtrlMouseScrollOrNull$1 = new TransformableKt$awaitCtrlMouseScrollOrNull$1(continuation);
        Object obj2 = transformableKt$awaitCtrlMouseScrollOrNull$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = transformableKt$awaitCtrlMouseScrollOrNull$1.label;
        if (i != 0) {
        }
        pointerEvent = (PointerEvent) obj2;
        if (PointerEvent_androidKt.m5892isCtrlPressed5xRPYO0(pointerEvent.getKeyboardModifiers())) {
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x02c3, code lost:
    
        if (r6 != 0) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x02c5, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x020d, code lost:
    
        if (r13.invoke(androidx.compose.ui.geometry.Offset.m4332boximpl(r9)).booleanValue() != false) goto L69;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0152 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02ca A[EDGE_INSN: B:97:0x02ca->B:96:0x02ca BREAK  A[LOOP:0: B:13:0x02b5->B:16:0x02c7], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x0297 -> B:12:0x02a4). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object detectZoom(AwaitPointerEventScope awaitPointerEventScope, boolean z, Channel<TransformEvent> channel, Function1<? super Offset, Boolean> function1, Continuation<? super Unit> continuation) {
        TransformableKt$detectZoom$1 transformableKt$detectZoom$1;
        Object obj;
        Object coroutine_suspended;
        int i;
        Channel<TransformEvent> channel2;
        Function1<? super Offset, Boolean> function12;
        boolean z2;
        float f;
        long j;
        int i2;
        int i3;
        float f2;
        float f3;
        AwaitPointerEventScope awaitPointerEventScope2;
        Function1<? super Offset, Boolean> function13;
        AwaitPointerEventScope awaitPointerEventScope3;
        float f4;
        float f5;
        Channel<TransformEvent> channel3;
        int size;
        int i4;
        int i5;
        TransformableKt$detectZoom$1 transformableKt$detectZoom$12;
        float f6;
        long j2;
        int i6;
        int i7;
        TransformableKt$detectZoom$1 transformableKt$detectZoom$13;
        Object awaitPointerEvent;
        float f7;
        int i8;
        int i9;
        Channel<TransformEvent> channel4;
        AwaitPointerEventScope awaitPointerEventScope4;
        PointerEvent pointerEvent;
        float f8;
        float f9;
        long j3;
        int size2;
        int i10;
        int size3;
        int i11;
        int size4;
        int i12;
        if (continuation instanceof TransformableKt$detectZoom$1) {
            transformableKt$detectZoom$1 = (TransformableKt$detectZoom$1) continuation;
            if ((transformableKt$detectZoom$1.label & Integer.MIN_VALUE) != 0) {
                transformableKt$detectZoom$1.label -= Integer.MIN_VALUE;
                TransformableKt$detectZoom$1 transformableKt$detectZoom$14 = transformableKt$detectZoom$1;
                obj = transformableKt$detectZoom$14.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = transformableKt$detectZoom$14.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    long m4359getZeroF1C5BW0 = Offset.INSTANCE.m4359getZeroF1C5BW0();
                    float touchSlop = awaitPointerEventScope.getViewConfiguration().getTouchSlop();
                    transformableKt$detectZoom$14.L$0 = awaitPointerEventScope;
                    channel2 = channel;
                    transformableKt$detectZoom$14.L$1 = channel2;
                    function12 = function1;
                    transformableKt$detectZoom$14.L$2 = function12;
                    z2 = z;
                    transformableKt$detectZoom$14.Z$0 = z2;
                    transformableKt$detectZoom$14.F$0 = 0.0f;
                    transformableKt$detectZoom$14.F$1 = 1.0f;
                    transformableKt$detectZoom$14.J$0 = m4359getZeroF1C5BW0;
                    transformableKt$detectZoom$14.I$0 = 0;
                    transformableKt$detectZoom$14.F$2 = touchSlop;
                    transformableKt$detectZoom$14.I$1 = 0;
                    transformableKt$detectZoom$14.label = 1;
                    if (TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope, false, null, transformableKt$detectZoom$14, 2, null) != coroutine_suspended) {
                        f = touchSlop;
                        j = m4359getZeroF1C5BW0;
                        i2 = 0;
                        i3 = 0;
                        f2 = 1.0f;
                        f3 = 0.0f;
                        awaitPointerEventScope2 = awaitPointerEventScope;
                    }
                    return coroutine_suspended;
                }
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        i5 = transformableKt$detectZoom$14.I$2;
                        i8 = transformableKt$detectZoom$14.I$1;
                        f7 = transformableKt$detectZoom$14.F$2;
                        i9 = transformableKt$detectZoom$14.I$0;
                        j2 = transformableKt$detectZoom$14.J$0;
                        float f10 = transformableKt$detectZoom$14.F$1;
                        f3 = transformableKt$detectZoom$14.F$0;
                        boolean z3 = transformableKt$detectZoom$14.Z$0;
                        pointerEvent = (PointerEvent) transformableKt$detectZoom$14.L$3;
                        Function1<? super Offset, Boolean> function14 = (Function1) transformableKt$detectZoom$14.L$2;
                        Channel<TransformEvent> channel5 = (Channel) transformableKt$detectZoom$14.L$1;
                        AwaitPointerEventScope awaitPointerEventScope5 = (AwaitPointerEventScope) transformableKt$detectZoom$14.L$0;
                        ResultKt.throwOnFailure(obj);
                        f2 = f10;
                        channel4 = channel5;
                        awaitPointerEventScope4 = awaitPointerEventScope5;
                        function12 = function14;
                        z2 = z3;
                        transformableKt$detectZoom$13 = transformableKt$detectZoom$14;
                        awaitPointerEvent = obj;
                        List<PointerInputChange> changes = ((PointerEvent) awaitPointerEvent).getChanges();
                        size3 = changes.size();
                        Object obj2 = coroutine_suspended;
                        i11 = 0;
                        while (true) {
                            if (i11 >= size3) {
                                break;
                            }
                            if (changes.get(i11).isConsumed()) {
                                break;
                            }
                            i11++;
                        }
                        boolean z4 = false;
                        if (i5 == 0 && !z4) {
                            List<PointerInputChange> changes2 = pointerEvent.getChanges();
                            size4 = changes2.size();
                            for (i12 = 0; i12 < size4; i12++) {
                                if (changes2.get(i12).getPressed()) {
                                    i2 = i8;
                                    f = f7;
                                    i3 = i9;
                                    transformableKt$detectZoom$14 = transformableKt$detectZoom$13;
                                    awaitPointerEventScope2 = awaitPointerEventScope4;
                                    coroutine_suspended = obj2;
                                    channel2 = channel4;
                                    j = j2;
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    i2 = transformableKt$detectZoom$14.I$1;
                    f = transformableKt$detectZoom$14.F$2;
                    i3 = transformableKt$detectZoom$14.I$0;
                    j = transformableKt$detectZoom$14.J$0;
                    float f11 = transformableKt$detectZoom$14.F$1;
                    float f12 = transformableKt$detectZoom$14.F$0;
                    boolean z5 = transformableKt$detectZoom$14.Z$0;
                    Function1<? super Offset, Boolean> function15 = (Function1) transformableKt$detectZoom$14.L$2;
                    Channel<TransformEvent> channel6 = (Channel) transformableKt$detectZoom$14.L$1;
                    AwaitPointerEventScope awaitPointerEventScope6 = (AwaitPointerEventScope) transformableKt$detectZoom$14.L$0;
                    ResultKt.throwOnFailure(obj);
                    f5 = f11;
                    channel3 = channel6;
                    z2 = z5;
                    f4 = f12;
                    function13 = function15;
                    awaitPointerEventScope3 = awaitPointerEventScope6;
                    PointerEvent pointerEvent2 = (PointerEvent) obj;
                    List<PointerInputChange> changes3 = pointerEvent2.getChanges();
                    size = changes3.size();
                    int i13 = i2;
                    i4 = 0;
                    while (true) {
                        if (i4 >= size) {
                            i5 = 0;
                            break;
                        }
                        if (changes3.get(i4).isConsumed()) {
                            i5 = 1;
                            break;
                        }
                        i4++;
                    }
                    if (i5 != 0) {
                        float calculateZoom = TransformGestureDetectorKt.calculateZoom(pointerEvent2);
                        float calculateRotation = TransformGestureDetectorKt.calculateRotation(pointerEvent2);
                        float f13 = f5;
                        float f14 = f4;
                        long calculatePan = TransformGestureDetectorKt.calculatePan(pointerEvent2);
                        if (i3 == 0) {
                            float f15 = f13 * calculateZoom;
                            float f16 = f14 + calculateRotation;
                            long m4348plusMKHz9U = Offset.m4348plusMKHz9U(j, calculatePan);
                            int i14 = i3;
                            float calculateCentroidSize = TransformGestureDetectorKt.calculateCentroidSize(pointerEvent2, false);
                            float abs = Math.abs(1 - f15) * calculateCentroidSize;
                            float abs2 = Math.abs(((3.1415927f * f16) * calculateCentroidSize) / 180.0f);
                            float m4341getDistanceimpl = Offset.m4341getDistanceimpl(m4348plusMKHz9U);
                            if (abs > f || abs2 > f || (m4341getDistanceimpl > f && function13.invoke(Offset.m4332boximpl(calculatePan)).booleanValue())) {
                                i7 = (!z2 || abs2 >= f) ? 0 : 1;
                                channel3.mo9628trySendJP2dKIU(TransformEvent.TransformStarted.INSTANCE);
                                i6 = 1;
                                f8 = f15;
                                f9 = f16;
                            } else {
                                i7 = i13;
                                f8 = f15;
                                f9 = f16;
                                i6 = i14;
                            }
                            j3 = m4348plusMKHz9U;
                        } else {
                            f8 = f13;
                            f9 = f14;
                            j3 = j;
                            i6 = i3;
                            i7 = i13;
                        }
                        if (i6 == 0) {
                            transformableKt$detectZoom$12 = transformableKt$detectZoom$14;
                        } else {
                            float f17 = i7 != 0 ? 0.0f : calculateRotation;
                            if (f17 != 0.0f) {
                                transformableKt$detectZoom$12 = transformableKt$detectZoom$14;
                            } else if (calculateZoom == 1.0f) {
                                transformableKt$detectZoom$12 = transformableKt$detectZoom$14;
                                if (!Offset.m4340equalsimpl0(calculatePan, Offset.INSTANCE.m4359getZeroF1C5BW0())) {
                                }
                                List<PointerInputChange> changes4 = pointerEvent2.getChanges();
                                size2 = changes4.size();
                                for (i10 = 0; i10 < size2; i10++) {
                                    PointerInputChange pointerInputChange = changes4.get(i10);
                                    if (PointerEventKt.positionChanged(pointerInputChange)) {
                                        pointerInputChange.consume();
                                    }
                                }
                            } else {
                                transformableKt$detectZoom$12 = transformableKt$detectZoom$14;
                            }
                            channel3.mo9628trySendJP2dKIU(new TransformEvent.TransformDelta(calculateZoom, calculatePan, f17, null));
                            List<PointerInputChange> changes42 = pointerEvent2.getChanges();
                            size2 = changes42.size();
                            while (i10 < size2) {
                            }
                        }
                        j2 = j3;
                        f6 = f8;
                        f3 = f9;
                    } else {
                        int i15 = i3;
                        transformableKt$detectZoom$12 = transformableKt$detectZoom$14;
                        ChannelResult.m9639boximpl(channel3.mo9628trySendJP2dKIU(TransformEvent.TransformStopped.INSTANCE));
                        f6 = f5;
                        f3 = f4;
                        j2 = j;
                        i6 = i15;
                        i7 = i13;
                    }
                    PointerEventPass pointerEventPass = PointerEventPass.Final;
                    transformableKt$detectZoom$13 = transformableKt$detectZoom$12;
                    transformableKt$detectZoom$13.L$0 = awaitPointerEventScope3;
                    transformableKt$detectZoom$13.L$1 = channel3;
                    transformableKt$detectZoom$13.L$2 = function13;
                    transformableKt$detectZoom$13.L$3 = pointerEvent2;
                    transformableKt$detectZoom$13.Z$0 = z2;
                    transformableKt$detectZoom$13.F$0 = f3;
                    transformableKt$detectZoom$13.F$1 = f6;
                    transformableKt$detectZoom$13.J$0 = j2;
                    transformableKt$detectZoom$13.I$0 = i6;
                    transformableKt$detectZoom$13.F$2 = f;
                    transformableKt$detectZoom$13.I$1 = i7;
                    transformableKt$detectZoom$13.I$2 = i5;
                    transformableKt$detectZoom$13.label = 3;
                    awaitPointerEvent = awaitPointerEventScope3.awaitPointerEvent(pointerEventPass, transformableKt$detectZoom$13);
                    if (awaitPointerEvent != coroutine_suspended) {
                        float f18 = f6;
                        f7 = f;
                        i8 = i7;
                        i9 = i6;
                        channel4 = channel3;
                        f2 = f18;
                        awaitPointerEventScope4 = awaitPointerEventScope3;
                        function12 = function13;
                        pointerEvent = pointerEvent2;
                        List<PointerInputChange> changes5 = ((PointerEvent) awaitPointerEvent).getChanges();
                        size3 = changes5.size();
                        Object obj22 = coroutine_suspended;
                        i11 = 0;
                        while (true) {
                            if (i11 >= size3) {
                            }
                            i11++;
                        }
                        boolean z42 = false;
                        if (i5 == 0) {
                            List<PointerInputChange> changes22 = pointerEvent.getChanges();
                            size4 = changes22.size();
                            while (i12 < size4) {
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    return coroutine_suspended;
                }
                i2 = transformableKt$detectZoom$14.I$1;
                f = transformableKt$detectZoom$14.F$2;
                i3 = transformableKt$detectZoom$14.I$0;
                j = transformableKt$detectZoom$14.J$0;
                f2 = transformableKt$detectZoom$14.F$1;
                f3 = transformableKt$detectZoom$14.F$0;
                boolean z6 = transformableKt$detectZoom$14.Z$0;
                function12 = (Function1) transformableKt$detectZoom$14.L$2;
                Channel<TransformEvent> channel7 = (Channel) transformableKt$detectZoom$14.L$1;
                awaitPointerEventScope2 = (AwaitPointerEventScope) transformableKt$detectZoom$14.L$0;
                ResultKt.throwOnFailure(obj);
                z2 = z6;
                channel2 = channel7;
                transformableKt$detectZoom$14.L$0 = awaitPointerEventScope2;
                transformableKt$detectZoom$14.L$1 = channel2;
                transformableKt$detectZoom$14.L$2 = function12;
                transformableKt$detectZoom$14.L$3 = null;
                transformableKt$detectZoom$14.Z$0 = z2;
                transformableKt$detectZoom$14.F$0 = f3;
                transformableKt$detectZoom$14.F$1 = f2;
                transformableKt$detectZoom$14.J$0 = j;
                transformableKt$detectZoom$14.I$0 = i3;
                transformableKt$detectZoom$14.F$2 = f;
                transformableKt$detectZoom$14.I$1 = i2;
                transformableKt$detectZoom$14.label = 2;
                obj = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope2, null, transformableKt$detectZoom$14, 1, null);
                if (obj != coroutine_suspended) {
                    float f19 = f3;
                    f5 = f2;
                    channel3 = channel2;
                    f4 = f19;
                    AwaitPointerEventScope awaitPointerEventScope7 = awaitPointerEventScope2;
                    function13 = function12;
                    awaitPointerEventScope3 = awaitPointerEventScope7;
                    PointerEvent pointerEvent22 = (PointerEvent) obj;
                    List<PointerInputChange> changes32 = pointerEvent22.getChanges();
                    size = changes32.size();
                    int i132 = i2;
                    i4 = 0;
                    while (true) {
                        if (i4 >= size) {
                        }
                        i4++;
                    }
                    if (i5 != 0) {
                    }
                    PointerEventPass pointerEventPass2 = PointerEventPass.Final;
                    transformableKt$detectZoom$13 = transformableKt$detectZoom$12;
                    transformableKt$detectZoom$13.L$0 = awaitPointerEventScope3;
                    transformableKt$detectZoom$13.L$1 = channel3;
                    transformableKt$detectZoom$13.L$2 = function13;
                    transformableKt$detectZoom$13.L$3 = pointerEvent22;
                    transformableKt$detectZoom$13.Z$0 = z2;
                    transformableKt$detectZoom$13.F$0 = f3;
                    transformableKt$detectZoom$13.F$1 = f6;
                    transformableKt$detectZoom$13.J$0 = j2;
                    transformableKt$detectZoom$13.I$0 = i6;
                    transformableKt$detectZoom$13.F$2 = f;
                    transformableKt$detectZoom$13.I$1 = i7;
                    transformableKt$detectZoom$13.I$2 = i5;
                    transformableKt$detectZoom$13.label = 3;
                    awaitPointerEvent = awaitPointerEventScope3.awaitPointerEvent(pointerEventPass2, transformableKt$detectZoom$13);
                    if (awaitPointerEvent != coroutine_suspended) {
                    }
                }
                return coroutine_suspended;
            }
        }
        transformableKt$detectZoom$1 = new TransformableKt$detectZoom$1(continuation);
        TransformableKt$detectZoom$1 transformableKt$detectZoom$142 = transformableKt$detectZoom$1;
        obj = transformableKt$detectZoom$142.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = transformableKt$detectZoom$142.label;
        if (i != 0) {
        }
        transformableKt$detectZoom$142.L$0 = awaitPointerEventScope2;
        transformableKt$detectZoom$142.L$1 = channel2;
        transformableKt$detectZoom$142.L$2 = function12;
        transformableKt$detectZoom$142.L$3 = null;
        transformableKt$detectZoom$142.Z$0 = z2;
        transformableKt$detectZoom$142.F$0 = f3;
        transformableKt$detectZoom$142.F$1 = f2;
        transformableKt$detectZoom$142.J$0 = j;
        transformableKt$detectZoom$142.I$0 = i3;
        transformableKt$detectZoom$142.F$2 = f;
        transformableKt$detectZoom$142.I$1 = i2;
        transformableKt$detectZoom$142.label = 2;
        obj = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope2, null, transformableKt$detectZoom$142, 1, null);
        if (obj != coroutine_suspended) {
        }
        return coroutine_suspended;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object detectZoomByCtrlMouseScroll(PointerInputScope pointerInputScope, Channel<TransformEvent> channel, ScrollConfig scrollConfig, Continuation<? super Unit> continuation) {
        Object awaitPointerEventScope = pointerInputScope.awaitPointerEventScope(new TransformableKt$detectZoomByCtrlMouseScroll$2(continuation.getContext(), scrollConfig, channel, null), continuation);
        return awaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitPointerEventScope : Unit.INSTANCE;
    }
}
