package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* compiled from: TimePicker.kt */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.AnalogTimePickerState$rotateTo$2", f = "TimePicker.kt", i = {}, l = {803, 806}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class AnalogTimePickerState$rotateTo$2 extends SuspendLambda implements Function1<Continuation<? super Object>, Object> {
    final /* synthetic */ float $angle;
    final /* synthetic */ boolean $animate;
    int label;
    final /* synthetic */ AnalogTimePickerState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AnalogTimePickerState$rotateTo$2(AnalogTimePickerState analogTimePickerState, float f, boolean z, Continuation<? super AnalogTimePickerState$rotateTo$2> continuation) {
        super(1, continuation);
        this.this$0 = analogTimePickerState;
        this.$angle = f;
        this.$animate = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new AnalogTimePickerState$rotateTo$2(this.this$0, this.$angle, this.$animate, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Object> continuation) {
        return invoke2((Continuation<Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<Object> continuation) {
        return ((AnalogTimePickerState$rotateTo$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a9, code lost:
    
        if (r12.snapTo(kotlin.coroutines.jvm.internal.Boxing.boxFloat(r1), r11) == r0) goto L28;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int minute;
        float f;
        int minute2;
        float offsetAngle;
        float endValueForAnimation;
        Animatable animatable;
        Animatable animatable2;
        float offsetAngle2;
        int hour;
        float f2;
        int hour2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (TimePickerSelectionMode.m2903equalsimpl0(this.this$0.mo1914getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2907getHouryecRtBI())) {
                AnalogTimePickerState analogTimePickerState = this.this$0;
                hour = analogTimePickerState.toHour(this.$angle);
                analogTimePickerState.hourAngle = (hour % 12) * 0.5235988f;
                TimePickerState state = this.this$0.getState();
                AnalogTimePickerState analogTimePickerState2 = this.this$0;
                f2 = analogTimePickerState2.hourAngle;
                hour2 = analogTimePickerState2.toHour(f2);
                state.setHour((hour2 % 12) + (this.this$0.isAfternoon() ? 12 : 0));
            } else {
                AnalogTimePickerState analogTimePickerState3 = this.this$0;
                minute = analogTimePickerState3.toMinute(this.$angle);
                analogTimePickerState3.minuteAngle = minute * 0.10471976f;
                TimePickerState state2 = this.this$0.getState();
                AnalogTimePickerState analogTimePickerState4 = this.this$0;
                f = analogTimePickerState4.minuteAngle;
                minute2 = analogTimePickerState4.toMinute(f);
                state2.setMinute(minute2);
            }
            if (!this.$animate) {
                animatable2 = this.this$0.anim;
                offsetAngle2 = this.this$0.offsetAngle(this.$angle);
                this.label = 1;
            } else {
                AnalogTimePickerState analogTimePickerState5 = this.this$0;
                offsetAngle = analogTimePickerState5.offsetAngle(this.$angle);
                endValueForAnimation = analogTimePickerState5.endValueForAnimation(offsetAngle);
                animatable = this.this$0.anim;
                this.label = 2;
                Object animateTo$default = Animatable.animateTo$default(animatable, Boxing.boxFloat(endValueForAnimation), AnimationSpecKt.spring$default(1.0f, 700.0f, null, 4, null), null, null, this, 12, null);
                if (animateTo$default != coroutine_suspended) {
                    return animateTo$default;
                }
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        return Unit.INSTANCE;
    }
}
