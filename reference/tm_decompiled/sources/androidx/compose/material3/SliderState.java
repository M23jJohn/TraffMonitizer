package androidx.compose.material3;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: Slider.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B;\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010R\u001a\u00020\b2\u0006\u0010S\u001a\u00020\u0003H\u0016J?\u0010T\u001a\u00020\b2\u0006\u0010U\u001a\u00020V2'\u0010W\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0Y\u0012\u0006\u0012\u0004\u0018\u00010Z0X¢\u0006\u0002\b[H\u0096@¢\u0006\u0002\u0010\\J\u001a\u0010]\u001a\u00020\b2\u0006\u0010^\u001a\u00020_H\u0000ø\u0001\u0000¢\u0006\u0004\b`\u0010aJ \u0010b\u001a\u00020\u00032\u0006\u0010c\u001a\u00020\u00032\u0006\u0010d\u001a\u00020\u00032\u0006\u0010e\u001a\u00020\u0003H\u0002J \u0010f\u001a\u00020\u00032\u0006\u0010c\u001a\u00020\u00032\u0006\u0010d\u001a\u00020\u00032\u0006\u0010g\u001a\u00020\u0003H\u0002J\u001d\u0010h\u001a\u00020\b2\u0006\u0010i\u001a\u00020\u00032\u0006\u0010j\u001a\u00020\u0005H\u0000¢\u0006\u0002\bkR\u0014\u0010\f\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R+\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00158@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001d\u001a\u00020\u0015X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0018\"\u0004\b\u001f\u0010\u001aR(\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010!X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010(R+\u0010)\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b*\u0010\u000e\"\u0004\b+\u0010,R+\u0010/\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b2\u0010.\u001a\u0004\b0\u0010\u000e\"\u0004\b1\u0010,R\u000e\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R+\u00107\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00038@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b:\u0010.\u001a\u0004\b8\u0010\u000e\"\u0004\b9\u0010,R\u0014\u0010;\u001a\u00020<X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R+\u0010?\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\b@\u00106\"\u0004\bA\u0010BR+\u0010E\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00038@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bH\u0010.\u001a\u0004\bF\u0010\u000e\"\u0004\bG\u0010,R$\u0010\u0002\u001a\u00020\u00032\u0006\u0010I\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bJ\u0010\u000e\"\u0004\bK\u0010,R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\bL\u0010MR+\u0010N\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bQ\u0010.\u001a\u0004\bO\u0010\u000e\"\u0004\bP\u0010,\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006l"}, d2 = {"Landroidx/compose/material3/SliderState;", "Landroidx/compose/foundation/gestures/DraggableState;", "value", "", "steps", "", "onValueChangeFinished", "Lkotlin/Function0;", "", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "(FILkotlin/jvm/functions/Function0;Lkotlin/ranges/ClosedFloatingPointRange;)V", "coercedValueAsFraction", "getCoercedValueAsFraction$material3_release", "()F", "dragScope", "Landroidx/compose/foundation/gestures/DragScope;", "gestureEndAction", "getGestureEndAction$material3_release", "()Lkotlin/jvm/functions/Function0;", "<set-?>", "", "isDragging", "isDragging$material3_release", "()Z", "setDragging", "(Z)V", "isDragging$delegate", "Landroidx/compose/runtime/MutableState;", "isRtl", "isRtl$material3_release", "setRtl$material3_release", "onValueChange", "Lkotlin/Function1;", "getOnValueChange$material3_release", "()Lkotlin/jvm/functions/Function1;", "setOnValueChange$material3_release", "(Lkotlin/jvm/functions/Function1;)V", "getOnValueChangeFinished", "setOnValueChangeFinished", "(Lkotlin/jvm/functions/Function0;)V", "pressOffset", "getPressOffset", "setPressOffset", "(F)V", "pressOffset$delegate", "Landroidx/compose/runtime/MutableFloatState;", "rawOffset", "getRawOffset", "setRawOffset", "rawOffset$delegate", "scrollMutex", "Landroidx/compose/foundation/MutatorMutex;", "getSteps", "()I", "thumbWidth", "getThumbWidth$material3_release", "setThumbWidth$material3_release", "thumbWidth$delegate", "tickFractions", "", "getTickFractions$material3_release", "()[F", "totalWidth", "getTotalWidth", "setTotalWidth", "(I)V", "totalWidth$delegate", "Landroidx/compose/runtime/MutableIntState;", "trackHeight", "getTrackHeight$material3_release", "setTrackHeight$material3_release", "trackHeight$delegate", "newVal", "getValue", "setValue", "getValueRange", "()Lkotlin/ranges/ClosedFloatingPointRange;", "valueState", "getValueState", "setValueState", "valueState$delegate", "dispatchRawDelta", "delta", "drag", "dragPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPress", "pos", "Landroidx/compose/ui/geometry/Offset;", "onPress-k-4lQ0M$material3_release", "(J)V", "scaleToOffset", "minPx", "maxPx", "userValue", "scaleToUserValue", "offset", "updateDimensions", "newTrackHeight", "newTotalWidth", "updateDimensions$material3_release", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SliderState implements DraggableState {
    public static final int $stable = 8;
    private final DragScope dragScope;
    private final Function0<Unit> gestureEndAction;

    /* renamed from: isDragging$delegate, reason: from kotlin metadata */
    private final MutableState isDragging;
    private boolean isRtl;
    private Function1<? super Float, Unit> onValueChange;
    private Function0<Unit> onValueChangeFinished;

    /* renamed from: pressOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState pressOffset;

    /* renamed from: rawOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState rawOffset;
    private final MutatorMutex scrollMutex;
    private final int steps;

    /* renamed from: thumbWidth$delegate, reason: from kotlin metadata */
    private final MutableFloatState thumbWidth;
    private final float[] tickFractions;

    /* renamed from: totalWidth$delegate, reason: from kotlin metadata */
    private final MutableIntState totalWidth;

    /* renamed from: trackHeight$delegate, reason: from kotlin metadata */
    private final MutableFloatState trackHeight;
    private final ClosedFloatingPointRange<Float> valueRange;

    /* renamed from: valueState$delegate, reason: from kotlin metadata */
    private final MutableFloatState valueState;

    public SliderState() {
        this(0.0f, 0, null, null, 15, null);
    }

    public SliderState(float f, int i, Function0<Unit> function0, ClosedFloatingPointRange<Float> closedFloatingPointRange) {
        float[] stepsToTickFractions;
        this.steps = i;
        this.onValueChangeFinished = function0;
        this.valueRange = closedFloatingPointRange;
        this.valueState = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        stepsToTickFractions = SliderKt.stepsToTickFractions(i);
        this.tickFractions = stepsToTickFractions;
        this.totalWidth = SnapshotIntStateKt.mutableIntStateOf(0);
        this.trackHeight = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.thumbWidth = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.isDragging = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.gestureEndAction = new Function0<Unit>() { // from class: androidx.compose.material3.SliderState$gestureEndAction$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Function0<Unit> onValueChangeFinished;
                if (SliderState.this.isDragging$material3_release() || (onValueChangeFinished = SliderState.this.getOnValueChangeFinished()) == null) {
                    return;
                }
                onValueChangeFinished.invoke();
            }
        };
        this.rawOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(scaleToOffset(0.0f, 0.0f, f));
        this.pressOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.dragScope = new DragScope() { // from class: androidx.compose.material3.SliderState$dragScope$1
            @Override // androidx.compose.foundation.gestures.DragScope
            public void dragBy(float pixels) {
                SliderState.this.dispatchRawDelta(pixels);
            }
        };
        this.scrollMutex = new MutatorMutex();
    }

    public final int getSteps() {
        return this.steps;
    }

    public final Function0<Unit> getOnValueChangeFinished() {
        return this.onValueChangeFinished;
    }

    public final void setOnValueChangeFinished(Function0<Unit> function0) {
        this.onValueChangeFinished = function0;
    }

    public /* synthetic */ SliderState(float f, int i, Function0 function0, ClosedFloatingPointRange closedFloatingPointRange, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0.0f : f, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? null : function0, (i2 & 8) != 0 ? RangesKt.rangeTo(0.0f, 1.0f) : closedFloatingPointRange);
    }

    public final ClosedFloatingPointRange<Float> getValueRange() {
        return this.valueRange;
    }

    private final float getValueState() {
        return this.valueState.getFloatValue();
    }

    private final void setValueState(float f) {
        this.valueState.setFloatValue(f);
    }

    public final void setValue(float f) {
        float snapValueToTick;
        snapValueToTick = SliderKt.snapValueToTick(RangesKt.coerceIn(f, this.valueRange.getStart().floatValue(), this.valueRange.getEndInclusive().floatValue()), this.tickFractions, this.valueRange.getStart().floatValue(), this.valueRange.getEndInclusive().floatValue());
        setValueState(snapValueToTick);
    }

    public final float getValue() {
        return getValueState();
    }

    @Override // androidx.compose.foundation.gestures.DraggableState
    public Object drag(MutatePriority mutatePriority, Function2<? super DragScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new SliderState$drag$2(this, mutatePriority, function2, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.DraggableState
    public void dispatchRawDelta(float delta) {
        float snapValueToTick;
        float f = 2;
        float max = Math.max(getTotalWidth() - (getThumbWidth$material3_release() / f), 0.0f);
        float min = Math.min(getThumbWidth$material3_release() / f, max);
        setRawOffset(getRawOffset() + delta + getPressOffset());
        setPressOffset(0.0f);
        snapValueToTick = SliderKt.snapValueToTick(getRawOffset(), this.tickFractions, min, max);
        float scaleToUserValue = scaleToUserValue(min, max, snapValueToTick);
        if (scaleToUserValue == getValue()) {
            return;
        }
        Function1<? super Float, Unit> function1 = this.onValueChange;
        if (function1 == null) {
            setValue(scaleToUserValue);
        } else if (function1 != null) {
            function1.invoke(Float.valueOf(scaleToUserValue));
        }
    }

    public final Function1<Float, Unit> getOnValueChange$material3_release() {
        return this.onValueChange;
    }

    public final void setOnValueChange$material3_release(Function1<? super Float, Unit> function1) {
        this.onValueChange = function1;
    }

    /* renamed from: getTickFractions$material3_release, reason: from getter */
    public final float[] getTickFractions() {
        return this.tickFractions;
    }

    private final int getTotalWidth() {
        return this.totalWidth.getIntValue();
    }

    private final void setTotalWidth(int i) {
        this.totalWidth.setIntValue(i);
    }

    /* renamed from: isRtl$material3_release, reason: from getter */
    public final boolean getIsRtl() {
        return this.isRtl;
    }

    public final void setRtl$material3_release(boolean z) {
        this.isRtl = z;
    }

    public final float getTrackHeight$material3_release() {
        return this.trackHeight.getFloatValue();
    }

    public final void setTrackHeight$material3_release(float f) {
        this.trackHeight.setFloatValue(f);
    }

    public final float getThumbWidth$material3_release() {
        return this.thumbWidth.getFloatValue();
    }

    public final void setThumbWidth$material3_release(float f) {
        this.thumbWidth.setFloatValue(f);
    }

    public final float getCoercedValueAsFraction$material3_release() {
        float calcFraction;
        calcFraction = SliderKt.calcFraction(this.valueRange.getStart().floatValue(), this.valueRange.getEndInclusive().floatValue(), RangesKt.coerceIn(getValue(), this.valueRange.getStart().floatValue(), this.valueRange.getEndInclusive().floatValue()));
        return calcFraction;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDragging(boolean z) {
        this.isDragging.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isDragging$material3_release() {
        return ((Boolean) this.isDragging.getValue()).booleanValue();
    }

    public final void updateDimensions$material3_release(float newTrackHeight, int newTotalWidth) {
        setTrackHeight$material3_release(newTrackHeight);
        setTotalWidth(newTotalWidth);
    }

    public final Function0<Unit> getGestureEndAction$material3_release() {
        return this.gestureEndAction;
    }

    /* renamed from: onPress-k-4lQ0M$material3_release, reason: not valid java name */
    public final void m2681onPressk4lQ0M$material3_release(long pos) {
        setPressOffset((this.isRtl ? getTotalWidth() - Offset.m4343getXimpl(pos) : Offset.m4343getXimpl(pos)) - getRawOffset());
    }

    private final float getRawOffset() {
        return this.rawOffset.getFloatValue();
    }

    private final void setRawOffset(float f) {
        this.rawOffset.setFloatValue(f);
    }

    private final float getPressOffset() {
        return this.pressOffset.getFloatValue();
    }

    private final void setPressOffset(float f) {
        this.pressOffset.setFloatValue(f);
    }

    private final float scaleToUserValue(float minPx, float maxPx, float offset) {
        float scale;
        scale = SliderKt.scale(minPx, maxPx, offset, this.valueRange.getStart().floatValue(), this.valueRange.getEndInclusive().floatValue());
        return scale;
    }

    private final float scaleToOffset(float minPx, float maxPx, float userValue) {
        float scale;
        scale = SliderKt.scale(this.valueRange.getStart().floatValue(), this.valueRange.getEndInclusive().floatValue(), userValue, minPx, maxPx);
        return scale;
    }
}
