package androidx.compose.foundation.layout;

import androidx.compose.animation.core.FloatDecayAnimationSpec;
import androidx.compose.foundation.layout.AndroidFlingSpline;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;

/* compiled from: WindowInsetsConnection.android.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0002J\u000e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007J\u0018\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0016J \u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0016J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0016J \u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0016R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/layout/SplineBasedFloatDecayAnimationSpec;", "Landroidx/compose/animation/core/FloatDecayAnimationSpec;", "density", "Landroidx/compose/ui/unit/Density;", "<init>", "(Landroidx/compose/ui/unit/Density;)V", "absVelocityThreshold", "", "getAbsVelocityThreshold", "()F", "magicPhysicalCoefficient", "getSplineDeceleration", "", "velocity", "flingDistance", "getTargetValue", "initialValue", "initialVelocity", "getValueFromNanos", "playTimeNanos", "", "getDurationNanos", "getVelocityFromNanos", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class SplineBasedFloatDecayAnimationSpec implements FloatDecayAnimationSpec {
    private final float magicPhysicalCoefficient;

    @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
    public float getAbsVelocityThreshold() {
        return 0.0f;
    }

    public SplineBasedFloatDecayAnimationSpec(Density density) {
        this.magicPhysicalCoefficient = density.getDensity() * 386.0878f * 160.0f * 0.84f;
    }

    private final double getSplineDeceleration(float velocity) {
        float f;
        AndroidFlingSpline androidFlingSpline = AndroidFlingSpline.INSTANCE;
        f = WindowInsetsConnection_androidKt.PlatformFlingScrollFriction;
        return androidFlingSpline.deceleration(velocity, f * this.magicPhysicalCoefficient);
    }

    public final float flingDistance(float velocity) {
        float f;
        double d;
        double d2;
        double splineDeceleration = getSplineDeceleration(velocity);
        f = WindowInsetsConnection_androidKt.PlatformFlingScrollFriction;
        double d3 = f * this.magicPhysicalCoefficient;
        d = WindowInsetsConnection_androidKt.DecelerationRate;
        d2 = WindowInsetsConnection_androidKt.DecelMinusOne;
        return ((float) (d3 * Math.exp((d / d2) * splineDeceleration))) * Math.signum(velocity);
    }

    @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
    public float getTargetValue(float initialValue, float initialVelocity) {
        return initialValue + flingDistance(initialVelocity);
    }

    @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
    public float getValueFromNanos(long playTimeNanos, float initialValue, float initialVelocity) {
        long durationNanos = getDurationNanos(0.0f, initialVelocity);
        return initialValue + (flingDistance(initialVelocity) * AndroidFlingSpline.FlingResult.m724getDistanceCoefficientimpl(AndroidFlingSpline.INSTANCE.m719flingPositionLfoxSSI(durationNanos > 0 ? playTimeNanos / durationNanos : 1.0f)));
    }

    @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
    public long getDurationNanos(float initialValue, float initialVelocity) {
        double d;
        double splineDeceleration = getSplineDeceleration(initialVelocity);
        d = WindowInsetsConnection_androidKt.DecelMinusOne;
        return (long) (Math.exp(splineDeceleration / d) * 1.0E9d);
    }

    @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
    public float getVelocityFromNanos(long playTimeNanos, float initialValue, float initialVelocity) {
        long durationNanos = getDurationNanos(0.0f, initialVelocity);
        return ((AndroidFlingSpline.FlingResult.m725getVelocityCoefficientimpl(AndroidFlingSpline.INSTANCE.m719flingPositionLfoxSSI(durationNanos > 0 ? playTimeNanos / durationNanos : 1.0f)) * flingDistance(initialVelocity)) / durationNanos) * 1.0E9f;
    }
}
