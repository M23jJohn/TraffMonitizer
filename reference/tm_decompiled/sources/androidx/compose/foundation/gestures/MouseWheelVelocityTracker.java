package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.util.VelocityTracker1D;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.Metadata;

/* compiled from: MouseWheelScrollable.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/compose/foundation/gestures/MouseWheelVelocityTracker;", "", "<init>", "()V", "xVelocityTracker", "Landroidx/compose/ui/input/pointer/util/VelocityTracker1D;", "yVelocityTracker", "addDelta", "", "timeMillis", "", "delta", "Landroidx/compose/ui/geometry/Offset;", "addDelta-Uv8p0NA", "(JJ)V", "calculateVelocity", "Landroidx/compose/ui/unit/Velocity;", "calculateVelocity-9UxMQ8M", "()J", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class MouseWheelVelocityTracker {
    private final VelocityTracker1D xVelocityTracker = new VelocityTracker1D(true);
    private final VelocityTracker1D yVelocityTracker = new VelocityTracker1D(true);

    /* renamed from: addDelta-Uv8p0NA, reason: not valid java name */
    public final void m561addDeltaUv8p0NA(long timeMillis, long delta) {
        this.xVelocityTracker.addDataPoint(timeMillis, Float.intBitsToFloat((int) (delta >> 32)));
        this.yVelocityTracker.addDataPoint(timeMillis, Float.intBitsToFloat((int) (delta & 4294967295L)));
    }

    /* renamed from: calculateVelocity-9UxMQ8M, reason: not valid java name */
    public final long m562calculateVelocity9UxMQ8M() {
        return VelocityKt.Velocity(this.xVelocityTracker.calculateVelocity(Float.MAX_VALUE), this.yVelocityTracker.calculateVelocity(Float.MAX_VALUE));
    }
}
