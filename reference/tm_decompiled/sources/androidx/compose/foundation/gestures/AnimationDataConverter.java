package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationVector4D;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* compiled from: TransformableState.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005R \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR \u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Landroidx/compose/foundation/gestures/AnimationDataConverter;", "Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/foundation/gestures/AnimationData;", "Landroidx/compose/animation/core/AnimationVector4D;", "<init>", "()V", "convertToVector", "Lkotlin/Function1;", "getConvertToVector", "()Lkotlin/jvm/functions/Function1;", "convertFromVector", "getConvertFromVector", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class AnimationDataConverter implements TwoWayConverter<AnimationData, AnimationVector4D> {
    public static final AnimationDataConverter INSTANCE = new AnimationDataConverter();

    @Override // androidx.compose.animation.core.TwoWayConverter
    public Function1<AnimationVector4D, AnimationData> getConvertFromVector() {
        return new Function1() { // from class: androidx.compose.foundation.gestures.AnimationDataConverter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                AnimationData _get_convertFromVector_$lambda$1;
                _get_convertFromVector_$lambda$1 = AnimationDataConverter._get_convertFromVector_$lambda$1((AnimationVector4D) obj);
                return _get_convertFromVector_$lambda$1;
            }
        };
    }

    @Override // androidx.compose.animation.core.TwoWayConverter
    public Function1<AnimationData, AnimationVector4D> getConvertToVector() {
        return new Function1() { // from class: androidx.compose.foundation.gestures.AnimationDataConverter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                AnimationVector4D _get_convertToVector_$lambda$0;
                _get_convertToVector_$lambda$0 = AnimationDataConverter._get_convertToVector_$lambda$0((AnimationData) obj);
                return _get_convertToVector_$lambda$0;
            }
        };
    }

    private AnimationDataConverter() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationVector4D _get_convertToVector_$lambda$0(AnimationData animationData) {
        return new AnimationVector4D(animationData.getZoom(), Float.intBitsToFloat((int) (animationData.m491getOffsetF1C5BW0() >> 32)), Float.intBitsToFloat((int) (animationData.m491getOffsetF1C5BW0() & 4294967295L)), animationData.getDegrees());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationData _get_convertFromVector_$lambda$1(AnimationVector4D animationVector4D) {
        float v1 = animationVector4D.getV1();
        float v2 = animationVector4D.getV2();
        float v3 = animationVector4D.getV3();
        return new AnimationData(v1, Offset.m4335constructorimpl((Float.floatToRawIntBits(v3) & 4294967295L) | (Float.floatToRawIntBits(v2) << 32)), animationVector4D.getV4(), null);
    }
}
