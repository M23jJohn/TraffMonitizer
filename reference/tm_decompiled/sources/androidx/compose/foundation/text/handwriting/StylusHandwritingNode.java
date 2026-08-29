package androidx.compose.foundation.text.handwriting;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.gestures.TapGestureDetector_androidKt;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusRequesterModifierNode;
import androidx.compose.ui.focus.FocusRequesterModifierNodeKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: StylusHandwriting.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0011\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J'\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 J\b\u0010!\u001a\u00020\u0007H\u0016J\u0006\u0010\"\u001a\u00020\u0007R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\tR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/compose/foundation/text/handwriting/StylusHandwritingNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "onHandwritingSlopExceeded", "Lkotlin/Function0;", "", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "getOnHandwritingSlopExceeded", "()Lkotlin/jvm/functions/Function0;", "setOnHandwritingSlopExceeded", "focused", "", "onFocusEvent", "focusState", "Landroidx/compose/ui/focus/FocusState;", "touchBoundsExpansion", "Landroidx/compose/ui/node/TouchBoundsExpansion;", "getTouchBoundsExpansion-RZrCHBk", "()J", "suspendingPointerInputModifierNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onCancelPointerInput", "resetPointerInputHandler", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public class StylusHandwritingNode extends DelegatingNode implements PointerInputModifierNode, FocusEventModifierNode, FocusRequesterModifierNode {
    public static final int $stable = 8;
    private boolean focused;
    private Function0<Unit> onHandwritingSlopExceeded;
    private final SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = (SuspendingPointerInputModifierNode) delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.handwriting.StylusHandwritingNode$suspendingPointerInputModifierNode$1

        /* compiled from: StylusHandwriting.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.handwriting.StylusHandwritingNode$suspendingPointerInputModifierNode$1$1", f = "StylusHandwriting.kt", i = {0, 1, 1, 1, 2, 2}, l = {116, 144, 182}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "firstDown", "pass", "$this$awaitEachGesture", "firstDown"}, s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1"})
        /* renamed from: androidx.compose.foundation.text.handwriting.StylusHandwritingNode$suspendingPointerInputModifierNode$1$1, reason: invalid class name */
        static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ StylusHandwritingNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(StylusHandwritingNode stylusHandwritingNode, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = stylusHandwritingNode;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:23:0x01b3, code lost:
            
                if (r5 != r1) goto L76;
             */
            /* JADX WARN: Code restructure failed: missing block: B:24:0x01b5, code lost:
            
                return r1;
             */
            /* JADX WARN: Code restructure failed: missing block: B:54:0x00fc, code lost:
            
                if (r10 != r1) goto L42;
             */
            /* JADX WARN: Code restructure failed: missing block: B:92:0x005d, code lost:
            
                if (r8 == r1) goto L75;
             */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x01b3 -> B:7:0x01b6). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x00fc -> B:29:0x0100). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object invokeSuspend(Object obj) {
                AwaitPointerEventScope awaitPointerEventScope;
                Object awaitFirstDown;
                boolean z;
                AwaitPointerEventScope awaitPointerEventScope2;
                PointerEventPass pointerEventPass;
                PointerInputChange pointerInputChange;
                PointerInputChange pointerInputChange2;
                Object awaitPointerEvent;
                PointerInputChange pointerInputChange3;
                PointerInputChange pointerInputChange4;
                boolean z2;
                PointerInputChange pointerInputChange5;
                AwaitPointerEventScope awaitPointerEventScope3;
                PointerInputChange pointerInputChange6;
                Object awaitPointerEvent2;
                PointerInputChange pointerInputChange7;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                int i2 = 2;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                    this.L$0 = awaitPointerEventScope;
                    this.label = 1;
                    awaitFirstDown = TapGestureDetectorKt.awaitFirstDown(awaitPointerEventScope, true, PointerEventPass.Initial, this);
                } else if (i == 1) {
                    awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    awaitFirstDown = obj;
                } else if (i == 2) {
                    pointerEventPass = (PointerEventPass) this.L$2;
                    pointerInputChange = (PointerInputChange) this.L$1;
                    awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    awaitPointerEvent = obj;
                    pointerInputChange2 = null;
                    PointerEvent pointerEvent = (PointerEvent) awaitPointerEvent;
                    List<PointerInputChange> changes = pointerEvent.getChanges();
                    int size = changes.size();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= size) {
                            pointerInputChange3 = pointerInputChange;
                            pointerInputChange4 = null;
                            break;
                        }
                        pointerInputChange4 = changes.get(i3);
                        PointerInputChange pointerInputChange8 = pointerInputChange4;
                        if (!pointerInputChange8.isConsumed()) {
                            pointerInputChange3 = pointerInputChange;
                            if (PointerId.m5907equalsimpl0(pointerInputChange8.getId(), pointerInputChange3.getId()) && pointerInputChange8.getPressed()) {
                                break;
                            }
                        } else {
                            pointerInputChange3 = pointerInputChange;
                        }
                        i3++;
                        pointerInputChange = pointerInputChange3;
                    }
                    PointerInputChange pointerInputChange9 = pointerInputChange4;
                    if (pointerInputChange9 != null && pointerInputChange9.getUptimeMillis() - pointerInputChange3.getUptimeMillis() < awaitPointerEventScope2.getViewConfiguration().getLongPressTimeoutMillis() && !TapGestureDetector_androidKt.isDeepPress(pointerEvent)) {
                        if (Offset.m4341getDistanceimpl(Offset.m4347minusMKHz9U(pointerInputChange9.getPosition(), pointerInputChange3.getPosition())) > awaitPointerEventScope2.getViewConfiguration().getHandwritingSlop()) {
                            pointerInputChange2 = pointerInputChange9;
                        } else {
                            pointerInputChange = pointerInputChange3;
                            i2 = 2;
                            this.L$0 = awaitPointerEventScope2;
                            this.L$1 = pointerInputChange;
                            this.L$2 = pointerEventPass;
                            this.label = i2;
                            awaitPointerEvent = awaitPointerEventScope2.awaitPointerEvent(pointerEventPass, this);
                        }
                    }
                    if (pointerInputChange2 == null) {
                        return Unit.INSTANCE;
                    }
                    z2 = this.this$0.focused;
                    if (!z2) {
                        FocusRequesterModifierNodeKt.requestFocus(this.this$0);
                    }
                    this.this$0.getOnHandwritingSlopExceeded().invoke();
                    pointerInputChange2.consume();
                    pointerInputChange5 = pointerInputChange3;
                    awaitPointerEventScope3 = awaitPointerEventScope2;
                    this.L$0 = awaitPointerEventScope3;
                    this.L$1 = pointerInputChange5;
                    pointerInputChange6 = null;
                    this.L$2 = null;
                    this.label = 3;
                    awaitPointerEvent2 = awaitPointerEventScope3.awaitPointerEvent(PointerEventPass.Initial, this);
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    pointerInputChange5 = (PointerInputChange) this.L$1;
                    awaitPointerEventScope3 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    awaitPointerEvent2 = obj;
                    pointerInputChange6 = null;
                    List<PointerInputChange> changes2 = ((PointerEvent) awaitPointerEvent2).getChanges();
                    int size2 = changes2.size();
                    int i4 = 0;
                    while (true) {
                        if (i4 >= size2) {
                            pointerInputChange7 = pointerInputChange6;
                            break;
                        }
                        pointerInputChange7 = changes2.get(i4);
                        PointerInputChange pointerInputChange10 = pointerInputChange7;
                        if (!pointerInputChange10.isConsumed() && PointerId.m5907equalsimpl0(pointerInputChange10.getId(), pointerInputChange5.getId()) && pointerInputChange10.getPressed()) {
                            break;
                        }
                        i4++;
                    }
                    PointerInputChange pointerInputChange11 = pointerInputChange7;
                    if (pointerInputChange11 == null) {
                        return Unit.INSTANCE;
                    }
                    pointerInputChange11.consume();
                    this.L$0 = awaitPointerEventScope3;
                    this.L$1 = pointerInputChange5;
                    pointerInputChange6 = null;
                    this.L$2 = null;
                    this.label = 3;
                    awaitPointerEvent2 = awaitPointerEventScope3.awaitPointerEvent(PointerEventPass.Initial, this);
                }
                PointerInputChange pointerInputChange12 = (PointerInputChange) awaitFirstDown;
                if (!PointerType.m5997equalsimpl0(pointerInputChange12.getType(), PointerType.INSTANCE.m6003getStylusT8wyACA()) && !PointerType.m5997equalsimpl0(pointerInputChange12.getType(), PointerType.INSTANCE.m6001getEraserT8wyACA())) {
                    return Unit.INSTANCE;
                }
                boolean z3 = Float.intBitsToFloat((int) (pointerInputChange12.getPosition() >> 32)) >= 0.0f && Float.intBitsToFloat((int) (pointerInputChange12.getPosition() >> 32)) < ((float) ((int) (awaitPointerEventScope.mo5820getSizeYbymL2g() >> 32))) && Float.intBitsToFloat((int) (pointerInputChange12.getPosition() & 4294967295L)) >= 0.0f && Float.intBitsToFloat((int) (pointerInputChange12.getPosition() & 4294967295L)) < ((float) ((int) (awaitPointerEventScope.mo5820getSizeYbymL2g() & 4294967295L)));
                z = this.this$0.focused;
                awaitPointerEventScope2 = awaitPointerEventScope;
                pointerEventPass = (z || z3) ? PointerEventPass.Initial : PointerEventPass.Main;
                pointerInputChange = pointerInputChange12;
                pointerInputChange2 = null;
                this.L$0 = awaitPointerEventScope2;
                this.L$1 = pointerInputChange;
                this.L$2 = pointerEventPass;
                this.label = i2;
                awaitPointerEvent = awaitPointerEventScope2.awaitPointerEvent(pointerEventPass, this);
            }
        }

        @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            Object awaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new AnonymousClass1(StylusHandwritingNode.this, null), continuation);
            return awaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitEachGesture : Unit.INSTANCE;
        }
    }));

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ boolean interceptOutOfBoundsChildEvents() {
        return PointerInputModifierNode.CC.$default$interceptOutOfBoundsChildEvents(this);
    }

    @Override // androidx.compose.ui.Modifier.Node, androidx.compose.ui.node.DelegatableNode, androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ void onDensityChange() {
        onCancelPointerInput();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ void onViewConfigurationChange() {
        onCancelPointerInput();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ boolean sharePointerInputWithSiblings() {
        return PointerInputModifierNode.CC.$default$sharePointerInputWithSiblings(this);
    }

    public final Function0<Unit> getOnHandwritingSlopExceeded() {
        return this.onHandwritingSlopExceeded;
    }

    public final void setOnHandwritingSlopExceeded(Function0<Unit> function0) {
        this.onHandwritingSlopExceeded = function0;
    }

    public StylusHandwritingNode(Function0<Unit> function0) {
        this.onHandwritingSlopExceeded = function0;
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public void onFocusEvent(FocusState focusState) {
        this.focused = focusState.isFocused();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: getTouchBoundsExpansion-RZrCHBk */
    public long mo260getTouchBoundsExpansionRZrCHBk() {
        return StylusHandwritingKt.getHandwritingBoundsExpansion().m6244roundToTouchBoundsExpansionTW6G1oQ(DelegatableNodeKt.requireDensity(this));
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY */
    public void mo267onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        this.suspendingPointerInputModifierNode.mo267onPointerEventH0pRuoY(pointerEvent, pass, bounds);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        this.suspendingPointerInputModifierNode.onCancelPointerInput();
    }

    public final void resetPointerInputHandler() {
        this.suspendingPointerInputModifierNode.resetPointerInputHandler();
    }
}
