package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.animation.core.VectorizedAnimationSpec;
import androidx.compose.animation.core.VectorizedFiniteAnimationSpec;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FloatCompanionObject;

/* compiled from: TransformableState.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B1\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001¢\u0006\u0004\b\b\u0010\tJ,\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\b\b\u0000\u0010\f*\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\f0\u000fH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/gestures/DelegatingAnimationSpec;", "Landroidx/compose/animation/core/AnimationSpec;", "Landroidx/compose/foundation/gestures/AnimationData;", "zoomAnimationSpec", "", "offsetAnimationSpec", "Landroidx/compose/ui/geometry/Offset;", "rotationAnimationSpec", "<init>", "(Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/AnimationSpec;)V", "vectorize", "Landroidx/compose/animation/core/VectorizedAnimationSpec;", "V", "Landroidx/compose/animation/core/AnimationVector;", "converter", "Landroidx/compose/animation/core/TwoWayConverter;", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class DelegatingAnimationSpec implements AnimationSpec<AnimationData> {
    private final AnimationSpec<Offset> offsetAnimationSpec;
    private final AnimationSpec<Float> rotationAnimationSpec;
    private final AnimationSpec<Float> zoomAnimationSpec;

    public DelegatingAnimationSpec(AnimationSpec<Float> animationSpec, AnimationSpec<Offset> animationSpec2, AnimationSpec<Float> animationSpec3) {
        this.zoomAnimationSpec = animationSpec;
        this.offsetAnimationSpec = animationSpec2;
        this.rotationAnimationSpec = animationSpec3;
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public <V extends AnimationVector> VectorizedAnimationSpec<V> vectorize(final TwoWayConverter<AnimationData, V> converter) {
        final VectorizedAnimationSpec<V> vectorize = this.zoomAnimationSpec.vectorize(VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
        final VectorizedAnimationSpec<V> vectorize2 = this.offsetAnimationSpec.vectorize(VectorConvertersKt.getVectorConverter(Offset.INSTANCE));
        final VectorizedAnimationSpec<V> vectorize3 = this.rotationAnimationSpec.vectorize(VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
        return new VectorizedFiniteAnimationSpec<V>() { // from class: androidx.compose.foundation.gestures.DelegatingAnimationSpec$vectorize$1
            @Override // androidx.compose.animation.core.VectorizedAnimationSpec
            public /* synthetic */ AnimationVector getEndVelocity(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
                return VectorizedAnimationSpec.CC.$default$getEndVelocity(this, animationVector, animationVector2, animationVector3);
            }

            @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec, androidx.compose.animation.core.VectorizedAnimationSpec
            public /* synthetic */ boolean isInfinite() {
                return VectorizedFiniteAnimationSpec.CC.$default$isInfinite(this);
            }

            /* JADX WARN: Incorrect types in method signature: (TV;TV;TV;)J */
            @Override // androidx.compose.animation.core.VectorizedAnimationSpec
            public long getDurationNanos(AnimationVector initialValue, AnimationVector targetValue, AnimationVector initialVelocity) {
                AnimationData invoke = converter.getConvertFromVector().invoke(initialValue);
                AnimationData invoke2 = converter.getConvertFromVector().invoke(targetValue);
                AnimationData invoke3 = converter.getConvertFromVector().invoke(initialVelocity);
                return Math.max(vectorize.getDurationNanos(zoomVector(invoke), zoomVector(invoke2), zoomVector(invoke3)), Math.max(vectorize2.getDurationNanos(offsetVector(invoke), offsetVector(invoke2), offsetVector(invoke3)), vectorize3.getDurationNanos(degreesVector(invoke), degreesVector(invoke2), degreesVector(invoke3))));
            }

            /* JADX WARN: Incorrect return type in method signature: (JTV;TV;TV;)TV; */
            @Override // androidx.compose.animation.core.VectorizedAnimationSpec
            public AnimationVector getVelocityFromNanos(long playTimeNanos, AnimationVector initialValue, AnimationVector targetValue, AnimationVector initialVelocity) {
                AnimationData invoke = converter.getConvertFromVector().invoke(initialValue);
                AnimationData invoke2 = converter.getConvertFromVector().invoke(targetValue);
                AnimationData invoke3 = converter.getConvertFromVector().invoke(initialVelocity);
                return packToAnimationVector(vectorize.getVelocityFromNanos(playTimeNanos, zoomVector(invoke), zoomVector(invoke2), zoomVector(invoke3)), vectorize2.getVelocityFromNanos(playTimeNanos, offsetVector(invoke), offsetVector(invoke2), offsetVector(invoke3)), vectorize3.getVelocityFromNanos(playTimeNanos, degreesVector(invoke), degreesVector(invoke2), degreesVector(invoke3)));
            }

            /* JADX WARN: Incorrect return type in method signature: (JTV;TV;TV;)TV; */
            @Override // androidx.compose.animation.core.VectorizedAnimationSpec
            public AnimationVector getValueFromNanos(long playTimeNanos, AnimationVector initialValue, AnimationVector targetValue, AnimationVector initialVelocity) {
                AnimationData invoke = converter.getConvertFromVector().invoke(initialValue);
                AnimationData invoke2 = converter.getConvertFromVector().invoke(targetValue);
                AnimationData invoke3 = converter.getConvertFromVector().invoke(initialVelocity);
                return packToAnimationVector(vectorize.getValueFromNanos(playTimeNanos, zoomVector(invoke), zoomVector(invoke2), zoomVector(invoke3)), vectorize2.getValueFromNanos(playTimeNanos, offsetVector(invoke), offsetVector(invoke2), offsetVector(invoke3)), vectorize3.getValueFromNanos(playTimeNanos, degreesVector(invoke), degreesVector(invoke2), degreesVector(invoke3)));
            }

            private final AnimationVector1D zoomVector(AnimationData animationData) {
                return VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE).getConvertToVector().invoke(Float.valueOf(animationData.getZoom()));
            }

            private final AnimationVector2D offsetVector(AnimationData animationData) {
                Function1<Offset, AnimationVector2D> convertToVector = VectorConvertersKt.getVectorConverter(Offset.INSTANCE).getConvertToVector();
                float intBitsToFloat = Float.intBitsToFloat((int) (animationData.m491getOffsetF1C5BW0() >> 32));
                float intBitsToFloat2 = Float.intBitsToFloat((int) (animationData.m491getOffsetF1C5BW0() & 4294967295L));
                return convertToVector.invoke(Offset.m4332boximpl(Offset.m4335constructorimpl((Float.floatToRawIntBits(intBitsToFloat) << 32) | (Float.floatToRawIntBits(intBitsToFloat2) & 4294967295L))));
            }

            private final AnimationVector1D degreesVector(AnimationData animationData) {
                return VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE).getConvertToVector().invoke(Float.valueOf(animationData.getDegrees()));
            }

            /* JADX WARN: Incorrect return type in method signature: (Landroidx/compose/animation/core/AnimationVector1D;Landroidx/compose/animation/core/AnimationVector2D;Landroidx/compose/animation/core/AnimationVector1D;)TV; */
            private final AnimationVector packToAnimationVector(AnimationVector1D zoom, AnimationVector2D offset, AnimationVector1D rotation) {
                Function1<AnimationData, V> convertToVector = converter.getConvertToVector();
                float value = zoom.getValue();
                float v1 = offset.getV1();
                float v2 = offset.getV2();
                return (AnimationVector) convertToVector.invoke(new AnimationData(value, Offset.m4335constructorimpl((Float.floatToRawIntBits(v2) & 4294967295L) | (Float.floatToRawIntBits(v1) << 32)), rotation.getValue(), null));
            }
        };
    }
}
