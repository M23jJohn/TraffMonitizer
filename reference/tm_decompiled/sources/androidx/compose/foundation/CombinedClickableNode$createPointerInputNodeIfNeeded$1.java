package androidx.compose.foundation;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: Clickable.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class CombinedClickableNode$createPointerInputNodeIfNeeded$1 implements PointerInputEventHandler {
    final /* synthetic */ CombinedClickableNode this$0;

    CombinedClickableNode$createPointerInputNodeIfNeeded$1(CombinedClickableNode combinedClickableNode) {
        this.this$0 = combinedClickableNode;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0052 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        Function1 function1;
        Function1 function12;
        Function0 function0;
        Function0 function02;
        if (this.this$0.getEnabled()) {
            function02 = this.this$0.onDoubleClick;
            if (function02 != null) {
                final CombinedClickableNode combinedClickableNode = this.this$0;
                function1 = new Function1() { // from class: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit invoke$lambda$0;
                        invoke$lambda$0 = CombinedClickableNode$createPointerInputNodeIfNeeded$1.invoke$lambda$0(CombinedClickableNode.this, (Offset) obj);
                        return invoke$lambda$0;
                    }
                };
                if (this.this$0.getEnabled()) {
                    function0 = this.this$0.onLongClick;
                    if (function0 != null) {
                        final CombinedClickableNode combinedClickableNode2 = this.this$0;
                        function12 = new Function1() { // from class: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit invoke$lambda$1;
                                invoke$lambda$1 = CombinedClickableNode$createPointerInputNodeIfNeeded$1.invoke$lambda$1(CombinedClickableNode.this, (Offset) obj);
                                return invoke$lambda$1;
                            }
                        };
                        AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, null);
                        final CombinedClickableNode combinedClickableNode3 = this.this$0;
                        Object detectTapGestures = TapGestureDetectorKt.detectTapGestures(pointerInputScope, function1, function12, anonymousClass3, new Function1() { // from class: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit invoke$lambda$2;
                                invoke$lambda$2 = CombinedClickableNode$createPointerInputNodeIfNeeded$1.invoke$lambda$2(CombinedClickableNode.this, (Offset) obj);
                                return invoke$lambda$2;
                            }
                        }, continuation);
                        return detectTapGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? detectTapGestures : Unit.INSTANCE;
                    }
                }
                function12 = null;
                AnonymousClass3 anonymousClass32 = new AnonymousClass3(this.this$0, null);
                final CombinedClickableNode combinedClickableNode32 = this.this$0;
                Object detectTapGestures2 = TapGestureDetectorKt.detectTapGestures(pointerInputScope, function1, function12, anonymousClass32, new Function1() { // from class: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit invoke$lambda$2;
                        invoke$lambda$2 = CombinedClickableNode$createPointerInputNodeIfNeeded$1.invoke$lambda$2(CombinedClickableNode.this, (Offset) obj);
                        return invoke$lambda$2;
                    }
                }, continuation);
                if (detectTapGestures2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                }
            }
        }
        function1 = null;
        if (this.this$0.getEnabled()) {
        }
        function12 = null;
        AnonymousClass3 anonymousClass322 = new AnonymousClass3(this.this$0, null);
        final CombinedClickableNode combinedClickableNode322 = this.this$0;
        Object detectTapGestures22 = TapGestureDetectorKt.detectTapGestures(pointerInputScope, function1, function12, anonymousClass322, new Function1() { // from class: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit invoke$lambda$2;
                invoke$lambda$2 = CombinedClickableNode$createPointerInputNodeIfNeeded$1.invoke$lambda$2(CombinedClickableNode.this, (Offset) obj);
                return invoke$lambda$2;
            }
        }, continuation);
        if (detectTapGestures22 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$0(CombinedClickableNode combinedClickableNode, Offset offset) {
        Function0 function0;
        function0 = combinedClickableNode.onDoubleClick;
        if (function0 != null) {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1(CombinedClickableNode combinedClickableNode, Offset offset) {
        Function0 function0;
        function0 = combinedClickableNode.onLongClick;
        if (function0 != null) {
            function0.invoke();
        }
        if (combinedClickableNode.getHapticFeedbackEnabled()) {
            ((HapticFeedback) CompositionLocalConsumerModifierNodeKt.currentValueOf(combinedClickableNode, CompositionLocalsKt.getLocalHapticFeedback())).mo5404performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m5417getLongPress5zf0vsI());
        }
        return Unit.INSTANCE;
    }

    /* compiled from: Clickable.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", "offset", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$3", f = "Clickable.kt", i = {}, l = {1121}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ CombinedClickableNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(CombinedClickableNode combinedClickableNode, Continuation<? super AnonymousClass3> continuation) {
            super(3, continuation);
            this.this$0 = combinedClickableNode;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
            return m358invoked4ec7I(pressGestureScope, offset.m4353unboximpl(), continuation);
        }

        /* renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m358invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, continuation);
            anonymousClass3.L$0 = pressGestureScope;
            anonymousClass3.J$0 = j;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PressGestureScope pressGestureScope = (PressGestureScope) this.L$0;
                long j = this.J$0;
                if (this.this$0.getEnabled()) {
                    this.label = 1;
                    if (this.this$0.m261handlePressInteractiond4ec7I(pressGestureScope, j, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$2(CombinedClickableNode combinedClickableNode, Offset offset) {
        if (combinedClickableNode.getEnabled()) {
            combinedClickableNode.getOnClick().invoke();
        }
        return Unit.INSTANCE;
    }
}
