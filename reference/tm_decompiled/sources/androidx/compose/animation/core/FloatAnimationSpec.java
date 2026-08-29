package androidx.compose.animation.core;

import kotlin.Metadata;

/* compiled from: FloatAnimationSpec.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J(\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H&J(\u0010\t\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H&J \u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016J \u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H&J,\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\r\"\b\b\u0000\u0010\u000e*\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u000e0\u0011H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0003"}, d2 = {"Landroidx/compose/animation/core/FloatAnimationSpec;", "Landroidx/compose/animation/core/AnimationSpec;", "", "getValueFromNanos", "playTimeNanos", "", "initialValue", "targetValue", "initialVelocity", "getVelocityFromNanos", "getEndVelocity", "getDurationNanos", "vectorize", "Landroidx/compose/animation/core/VectorizedFloatAnimationSpec;", "V", "Landroidx/compose/animation/core/AnimationVector;", "converter", "Landroidx/compose/animation/core/TwoWayConverter;", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public interface FloatAnimationSpec extends AnimationSpec<Float> {
    long getDurationNanos(float initialValue, float targetValue, float initialVelocity);

    float getEndVelocity(float initialValue, float targetValue, float initialVelocity);

    float getValueFromNanos(long playTimeNanos, float initialValue, float targetValue, float initialVelocity);

    float getVelocityFromNanos(long playTimeNanos, float initialValue, float targetValue, float initialVelocity);

    @Override // androidx.compose.animation.core.AnimationSpec
    <V extends AnimationVector> VectorizedFloatAnimationSpec<V> vectorize(TwoWayConverter<Float, V> converter);

    /* compiled from: FloatAnimationSpec.kt */
    /* renamed from: androidx.compose.animation.core.FloatAnimationSpec$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static float $default$getEndVelocity(FloatAnimationSpec _this, float f, float f2, float f3) {
            return _this.getVelocityFromNanos(_this.getDurationNanos(f, f2, f3), f, f2, f3);
        }

        /* renamed from: $default$vectorize, reason: collision with other method in class */
        public static VectorizedFloatAnimationSpec m204$default$vectorize(FloatAnimationSpec _this, TwoWayConverter twoWayConverter) {
            return new VectorizedFloatAnimationSpec(_this);
        }
    }

    /* compiled from: FloatAnimationSpec.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static float getEndVelocity(FloatAnimationSpec floatAnimationSpec, float f, float f2, float f3) {
            return CC.$default$getEndVelocity(floatAnimationSpec, f, f2, f3);
        }

        @Deprecated
        public static <V extends AnimationVector> VectorizedFloatAnimationSpec<V> vectorize(FloatAnimationSpec floatAnimationSpec, TwoWayConverter<Float, V> twoWayConverter) {
            return CC.m204$default$vectorize(floatAnimationSpec, (TwoWayConverter) twoWayConverter);
        }
    }
}
