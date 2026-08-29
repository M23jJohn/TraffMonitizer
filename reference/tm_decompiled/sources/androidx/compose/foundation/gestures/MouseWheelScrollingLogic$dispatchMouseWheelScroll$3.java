package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.gestures.MouseWheelScrollingLogic;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.channels.Channel;

/* compiled from: MouseWheelScrollable.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/NestedScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.MouseWheelScrollingLogic$dispatchMouseWheelScroll$3", f = "MouseWheelScrollable.kt", i = {0, 0, 1, 1, 1, 2, 2}, l = {253, 266, 283}, m = "invokeSuspend", n = {"$this$userScroll", "requiredAnimation", "$this$userScroll", "requiredAnimation", "durationMillis", "$this$userScroll", "requiredAnimation"}, s = {"L$0", "L$1", "L$0", "L$1", "I$0", "L$0", "L$1"})
/* loaded from: classes.dex */
final class MouseWheelScrollingLogic$dispatchMouseWheelScroll$3 extends SuspendLambda implements Function2<NestedScrollScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> $animationState;
    final /* synthetic */ float $speed;
    final /* synthetic */ Ref.ObjectRef<MouseWheelScrollingLogic.MouseWheelScrollDelta> $targetScrollDelta;
    final /* synthetic */ Ref.FloatRef $targetValue;
    final /* synthetic */ ScrollingLogic $this_dispatchMouseWheelScroll;
    final /* synthetic */ float $threshold;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ MouseWheelScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MouseWheelScrollingLogic$dispatchMouseWheelScroll$3(Ref.FloatRef floatRef, Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> objectRef, Ref.ObjectRef<MouseWheelScrollingLogic.MouseWheelScrollDelta> objectRef2, float f, MouseWheelScrollingLogic mouseWheelScrollingLogic, float f2, ScrollingLogic scrollingLogic, Continuation<? super MouseWheelScrollingLogic$dispatchMouseWheelScroll$3> continuation) {
        super(2, continuation);
        this.$targetValue = floatRef;
        this.$animationState = objectRef;
        this.$targetScrollDelta = objectRef2;
        this.$threshold = f;
        this.this$0 = mouseWheelScrollingLogic;
        this.$speed = f2;
        this.$this_dispatchMouseWheelScroll = scrollingLogic;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MouseWheelScrollingLogic$dispatchMouseWheelScroll$3 mouseWheelScrollingLogic$dispatchMouseWheelScroll$3 = new MouseWheelScrollingLogic$dispatchMouseWheelScroll$3(this.$targetValue, this.$animationState, this.$targetScrollDelta, this.$threshold, this.this$0, this.$speed, this.$this_dispatchMouseWheelScroll, continuation);
        mouseWheelScrollingLogic$dispatchMouseWheelScroll$3.L$0 = obj;
        return mouseWheelScrollingLogic$dispatchMouseWheelScroll$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(NestedScrollScope nestedScrollScope, Continuation<? super Unit> continuation) {
        return ((MouseWheelScrollingLogic$dispatchMouseWheelScroll$3) create(nestedScrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x019a  */
    /* JADX WARN: Type inference failed for: r2v16, types: [T, androidx.compose.animation.core.AnimationState] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x012c -> B:9:0x0066). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0158 -> B:7:0x0159). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Ref.BooleanRef booleanRef;
        NestedScrollScope nestedScrollScope;
        final Ref.BooleanRef booleanRef2;
        Object dispatchMouseWheelScroll$waitNextScrollDelta;
        Object dispatchMouseWheelScroll$waitNextScrollDelta2;
        Object animateMouseWheelScroll;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            NestedScrollScope nestedScrollScope2 = (NestedScrollScope) this.L$0;
            booleanRef = new Ref.BooleanRef();
            booleanRef.element = true;
            nestedScrollScope = nestedScrollScope2;
        } else if (i == 1) {
            Ref.BooleanRef booleanRef3 = (Ref.BooleanRef) this.L$2;
            booleanRef = (Ref.BooleanRef) this.L$1;
            NestedScrollScope nestedScrollScope3 = (NestedScrollScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            nestedScrollScope = nestedScrollScope3;
            booleanRef3.element = ((Boolean) obj).booleanValue();
        } else if (i == 2) {
            int i2 = this.I$0;
            Ref.BooleanRef booleanRef4 = (Ref.BooleanRef) this.L$1;
            NestedScrollScope nestedScrollScope4 = (NestedScrollScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            booleanRef2 = booleanRef4;
            nestedScrollScope = nestedScrollScope4;
            if (!booleanRef2.element) {
                this.L$0 = nestedScrollScope;
                this.L$1 = booleanRef2;
                this.L$2 = booleanRef2;
                this.label = 3;
                dispatchMouseWheelScroll$waitNextScrollDelta = MouseWheelScrollingLogic.dispatchMouseWheelScroll$waitNextScrollDelta(this.this$0, this.$targetScrollDelta, this.$targetValue, this.$this_dispatchMouseWheelScroll, this.$animationState, 50 - i2, this);
                if (dispatchMouseWheelScroll$waitNextScrollDelta != coroutine_suspended) {
                    booleanRef = booleanRef2;
                    booleanRef2.element = ((Boolean) dispatchMouseWheelScroll$waitNextScrollDelta).booleanValue();
                }
                return coroutine_suspended;
            }
            if (booleanRef2.element) {
                booleanRef2.element = false;
                float floatValue = this.$targetValue.element - this.$animationState.element.getValue().floatValue();
                if (this.$targetScrollDelta.element.getShouldApplyImmediately() || Math.abs(floatValue) < this.$threshold) {
                    this.this$0.dispatchMouseWheelScroll(nestedScrollScope, floatValue);
                    this.L$0 = nestedScrollScope;
                    this.L$1 = booleanRef2;
                    this.L$2 = booleanRef2;
                    this.label = 1;
                    dispatchMouseWheelScroll$waitNextScrollDelta2 = MouseWheelScrollingLogic.dispatchMouseWheelScroll$waitNextScrollDelta(this.this$0, this.$targetScrollDelta, this.$targetValue, this.$this_dispatchMouseWheelScroll, this.$animationState, 50L, this);
                    if (dispatchMouseWheelScroll$waitNextScrollDelta2 != coroutine_suspended) {
                        booleanRef = booleanRef2;
                        booleanRef2.element = ((Boolean) dispatchMouseWheelScroll$waitNextScrollDelta2).booleanValue();
                    }
                } else {
                    float signum = Math.signum(floatValue) * this.$threshold;
                    this.this$0.dispatchMouseWheelScroll(nestedScrollScope, signum);
                    Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> objectRef = this.$animationState;
                    objectRef.element = AnimationStateKt.copy$default((AnimationState) objectRef.element, this.$animationState.element.getValue().floatValue() + signum, 0.0f, 0L, 0L, false, 30, (Object) null);
                    int coerceAtMost = RangesKt.coerceAtMost(MathKt.roundToInt(Math.abs(this.$targetValue.element - this.$animationState.element.getValue().floatValue()) / this.$speed), 100);
                    MouseWheelScrollingLogic mouseWheelScrollingLogic = this.this$0;
                    AnimationState<Float, AnimationVector1D> animationState = this.$animationState.element;
                    float f = this.$targetValue.element;
                    final MouseWheelScrollingLogic mouseWheelScrollingLogic2 = this.this$0;
                    final Ref.ObjectRef<MouseWheelScrollingLogic.MouseWheelScrollDelta> objectRef2 = this.$targetScrollDelta;
                    final Ref.FloatRef floatRef = this.$targetValue;
                    final ScrollingLogic scrollingLogic = this.$this_dispatchMouseWheelScroll;
                    this.L$0 = nestedScrollScope;
                    this.L$1 = booleanRef2;
                    this.L$2 = null;
                    this.I$0 = coerceAtMost;
                    this.label = 2;
                    animateMouseWheelScroll = mouseWheelScrollingLogic.animateMouseWheelScroll(nestedScrollScope, animationState, f, coerceAtMost, new Function1() { // from class: androidx.compose.foundation.gestures.MouseWheelScrollingLogic$dispatchMouseWheelScroll$3$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            boolean invokeSuspend$lambda$0;
                            invokeSuspend$lambda$0 = MouseWheelScrollingLogic$dispatchMouseWheelScroll$3.invokeSuspend$lambda$0(MouseWheelScrollingLogic.this, objectRef2, floatRef, scrollingLogic, booleanRef2, ((Float) obj2).floatValue());
                            return Boolean.valueOf(invokeSuspend$lambda$0);
                        }
                    }, this);
                    if (animateMouseWheelScroll != coroutine_suspended) {
                        i2 = coerceAtMost;
                        if (!booleanRef2.element) {
                        }
                        if (booleanRef2.element) {
                            return Unit.INSTANCE;
                        }
                    }
                }
                return coroutine_suspended;
            }
        } else {
            if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Ref.BooleanRef booleanRef5 = (Ref.BooleanRef) this.L$2;
            booleanRef = (Ref.BooleanRef) this.L$1;
            NestedScrollScope nestedScrollScope5 = (NestedScrollScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            booleanRef2 = booleanRef5;
            nestedScrollScope = nestedScrollScope5;
            dispatchMouseWheelScroll$waitNextScrollDelta = obj;
            booleanRef2.element = ((Boolean) dispatchMouseWheelScroll$waitNextScrollDelta).booleanValue();
        }
        booleanRef2 = booleanRef;
        if (booleanRef2.element) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [T, androidx.compose.foundation.gestures.MouseWheelScrollingLogic$MouseWheelScrollDelta] */
    public static final boolean invokeSuspend$lambda$0(MouseWheelScrollingLogic mouseWheelScrollingLogic, Ref.ObjectRef objectRef, Ref.FloatRef floatRef, ScrollingLogic scrollingLogic, Ref.BooleanRef booleanRef, float f) {
        Channel channel;
        MouseWheelScrollingLogic.MouseWheelScrollDelta sumOrNull;
        boolean isLowScrollingDelta;
        channel = mouseWheelScrollingLogic.channel;
        sumOrNull = mouseWheelScrollingLogic.sumOrNull(channel);
        if (sumOrNull != null) {
            mouseWheelScrollingLogic.trackVelocity(sumOrNull);
            objectRef.element = ((MouseWheelScrollingLogic.MouseWheelScrollDelta) objectRef.element).plus(sumOrNull);
            floatRef.element = scrollingLogic.m625toFloatk4lQ0M(scrollingLogic.m623reverseIfNeededMKHz9U(((MouseWheelScrollingLogic.MouseWheelScrollDelta) objectRef.element).m560getValueF1C5BW0()));
            isLowScrollingDelta = MouseWheelScrollableKt.isLowScrollingDelta(floatRef.element - f);
            booleanRef.element = !isLowScrollingDelta;
        }
        return sumOrNull != null;
    }
}
