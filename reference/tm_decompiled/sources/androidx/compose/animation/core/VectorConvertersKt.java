package androidx.compose.animation.core;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;

/* compiled from: VectorConverters.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001aJ\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00020\u0006\u001a!\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0080\b\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e0\u0001*\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e0\u0001*\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0014\"\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0001*\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u001a\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000e0\u0001*\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u001d\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u0001*\u00020 8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010!\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u001f0\u0001*\u00020#8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010$\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u001f0\u0001*\u00020&8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010'\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u001f0\u0001*\u00020)8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010*\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u001f0\u0001*\u00020,8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010-\"\u001a\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u00100\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u001f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u00101\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u001f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u00102\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u001f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u00103\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u001f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"TwoWayConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "convertToVector", "Lkotlin/Function1;", "convertFromVector", "lerp", "", "start", "stop", "fraction", "VectorConverter", "Landroidx/compose/animation/core/AnimationVector1D;", "Lkotlin/Float$Companion;", "getVectorConverter", "(Lkotlin/jvm/internal/FloatCompanionObject;)Landroidx/compose/animation/core/TwoWayConverter;", "", "Lkotlin/Int$Companion;", "(Lkotlin/jvm/internal/IntCompanionObject;)Landroidx/compose/animation/core/TwoWayConverter;", "FloatToVector", "IntToVector", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/animation/core/AnimationVector4D;", "Landroidx/compose/ui/geometry/Rect$Companion;", "(Landroidx/compose/ui/geometry/Rect$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/unit/Dp;", "Landroidx/compose/ui/unit/Dp$Companion;", "(Landroidx/compose/ui/unit/Dp$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/unit/DpOffset;", "Landroidx/compose/animation/core/AnimationVector2D;", "Landroidx/compose/ui/unit/DpOffset$Companion;", "(Landroidx/compose/ui/unit/DpOffset$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/geometry/Size$Companion;", "(Landroidx/compose/ui/geometry/Size$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/geometry/Offset$Companion;", "(Landroidx/compose/ui/geometry/Offset$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/unit/IntOffset;", "Landroidx/compose/ui/unit/IntOffset$Companion;", "(Landroidx/compose/ui/unit/IntOffset$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/ui/unit/IntSize$Companion;", "(Landroidx/compose/ui/unit/IntSize$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "DpToVector", "DpOffsetToVector", "SizeToVector", "OffsetToVector", "IntOffsetToVector", "IntSizeToVector", "RectToVector", "animation-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class VectorConvertersKt {
    private static final TwoWayConverter<Float, AnimationVector1D> FloatToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector1D FloatToVector$lambda$0;
            FloatToVector$lambda$0 = VectorConvertersKt.FloatToVector$lambda$0(((Float) obj).floatValue());
            return FloatToVector$lambda$0;
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda17
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            float value;
            value = ((AnimationVector1D) obj).getValue();
            return Float.valueOf(value);
        }
    });
    private static final TwoWayConverter<Integer, AnimationVector1D> IntToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector1D IntToVector$lambda$2;
            IntToVector$lambda$2 = VectorConvertersKt.IntToVector$lambda$2(((Integer) obj).intValue());
            return IntToVector$lambda$2;
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            int IntToVector$lambda$3;
            IntToVector$lambda$3 = VectorConvertersKt.IntToVector$lambda$3((AnimationVector1D) obj);
            return Integer.valueOf(IntToVector$lambda$3);
        }
    });
    private static final TwoWayConverter<Dp, AnimationVector1D> DpToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector1D DpToVector$lambda$4;
            DpToVector$lambda$4 = VectorConvertersKt.DpToVector$lambda$4((Dp) obj);
            return DpToVector$lambda$4;
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Dp DpToVector$lambda$5;
            DpToVector$lambda$5 = VectorConvertersKt.DpToVector$lambda$5((AnimationVector1D) obj);
            return DpToVector$lambda$5;
        }
    });
    private static final TwoWayConverter<DpOffset, AnimationVector2D> DpOffsetToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector2D DpOffsetToVector$lambda$6;
            DpOffsetToVector$lambda$6 = VectorConvertersKt.DpOffsetToVector$lambda$6((DpOffset) obj);
            return DpOffsetToVector$lambda$6;
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            DpOffset DpOffsetToVector$lambda$7;
            DpOffsetToVector$lambda$7 = VectorConvertersKt.DpOffsetToVector$lambda$7((AnimationVector2D) obj);
            return DpOffsetToVector$lambda$7;
        }
    });
    private static final TwoWayConverter<Size, AnimationVector2D> SizeToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector2D SizeToVector$lambda$8;
            SizeToVector$lambda$8 = VectorConvertersKt.SizeToVector$lambda$8((Size) obj);
            return SizeToVector$lambda$8;
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Size SizeToVector$lambda$9;
            SizeToVector$lambda$9 = VectorConvertersKt.SizeToVector$lambda$9((AnimationVector2D) obj);
            return SizeToVector$lambda$9;
        }
    });
    private static final TwoWayConverter<Offset, AnimationVector2D> OffsetToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector2D OffsetToVector$lambda$10;
            OffsetToVector$lambda$10 = VectorConvertersKt.OffsetToVector$lambda$10((Offset) obj);
            return OffsetToVector$lambda$10;
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Offset OffsetToVector$lambda$11;
            OffsetToVector$lambda$11 = VectorConvertersKt.OffsetToVector$lambda$11((AnimationVector2D) obj);
            return OffsetToVector$lambda$11;
        }
    });
    private static final TwoWayConverter<IntOffset, AnimationVector2D> IntOffsetToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector2D IntOffsetToVector$lambda$12;
            IntOffsetToVector$lambda$12 = VectorConvertersKt.IntOffsetToVector$lambda$12((IntOffset) obj);
            return IntOffsetToVector$lambda$12;
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda12
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            IntOffset IntOffsetToVector$lambda$13;
            IntOffsetToVector$lambda$13 = VectorConvertersKt.IntOffsetToVector$lambda$13((AnimationVector2D) obj);
            return IntOffsetToVector$lambda$13;
        }
    });
    private static final TwoWayConverter<IntSize, AnimationVector2D> IntSizeToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda13
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector2D IntSizeToVector$lambda$14;
            IntSizeToVector$lambda$14 = VectorConvertersKt.IntSizeToVector$lambda$14((IntSize) obj);
            return IntSizeToVector$lambda$14;
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda14
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            IntSize IntSizeToVector$lambda$15;
            IntSizeToVector$lambda$15 = VectorConvertersKt.IntSizeToVector$lambda$15((AnimationVector2D) obj);
            return IntSizeToVector$lambda$15;
        }
    });
    private static final TwoWayConverter<Rect, AnimationVector4D> RectToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda15
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector4D RectToVector$lambda$16;
            RectToVector$lambda$16 = VectorConvertersKt.RectToVector$lambda$16((Rect) obj);
            return RectToVector$lambda$16;
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$$ExternalSyntheticLambda16
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Rect RectToVector$lambda$17;
            RectToVector$lambda$17 = VectorConvertersKt.RectToVector$lambda$17((AnimationVector4D) obj);
            return RectToVector$lambda$17;
        }
    });

    public static final float lerp(float f, float f2, float f3) {
        return (f * (1 - f3)) + (f2 * f3);
    }

    public static final <T, V extends AnimationVector> TwoWayConverter<T, V> TwoWayConverter(Function1<? super T, ? extends V> function1, Function1<? super V, ? extends T> function12) {
        return new TwoWayConverterImpl(function1, function12);
    }

    public static final TwoWayConverter<Float, AnimationVector1D> getVectorConverter(FloatCompanionObject floatCompanionObject) {
        return FloatToVector;
    }

    public static final TwoWayConverter<Integer, AnimationVector1D> getVectorConverter(IntCompanionObject intCompanionObject) {
        return IntToVector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationVector1D FloatToVector$lambda$0(float f) {
        return new AnimationVector1D(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationVector1D IntToVector$lambda$2(int i) {
        return new AnimationVector1D(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int IntToVector$lambda$3(AnimationVector1D animationVector1D) {
        return (int) animationVector1D.getValue();
    }

    public static final TwoWayConverter<Rect, AnimationVector4D> getVectorConverter(Rect.Companion companion) {
        return RectToVector;
    }

    public static final TwoWayConverter<Dp, AnimationVector1D> getVectorConverter(Dp.Companion companion) {
        return DpToVector;
    }

    public static final TwoWayConverter<DpOffset, AnimationVector2D> getVectorConverter(DpOffset.Companion companion) {
        return DpOffsetToVector;
    }

    public static final TwoWayConverter<Size, AnimationVector2D> getVectorConverter(Size.Companion companion) {
        return SizeToVector;
    }

    public static final TwoWayConverter<Offset, AnimationVector2D> getVectorConverter(Offset.Companion companion) {
        return OffsetToVector;
    }

    public static final TwoWayConverter<IntOffset, AnimationVector2D> getVectorConverter(IntOffset.Companion companion) {
        return IntOffsetToVector;
    }

    public static final TwoWayConverter<IntSize, AnimationVector2D> getVectorConverter(IntSize.Companion companion) {
        return IntSizeToVector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationVector1D DpToVector$lambda$4(Dp dp) {
        return new AnimationVector1D(dp.m7413unboximpl());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Dp DpToVector$lambda$5(AnimationVector1D animationVector1D) {
        return Dp.m7397boximpl(Dp.m7399constructorimpl(animationVector1D.getValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationVector2D DpOffsetToVector$lambda$6(DpOffset dpOffset) {
        return new AnimationVector2D(DpOffset.m7460getXD9Ej5fM(dpOffset.m7468unboximpl()), DpOffset.m7462getYD9Ej5fM(dpOffset.m7468unboximpl()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DpOffset DpOffsetToVector$lambda$7(AnimationVector2D animationVector2D) {
        float m7399constructorimpl = Dp.m7399constructorimpl(animationVector2D.getV1());
        float m7399constructorimpl2 = Dp.m7399constructorimpl(animationVector2D.getV2());
        return DpOffset.m7454boximpl(DpOffset.m7455constructorimpl((Float.floatToRawIntBits(m7399constructorimpl) << 32) | (Float.floatToRawIntBits(m7399constructorimpl2) & 4294967295L)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationVector2D SizeToVector$lambda$8(Size size) {
        return new AnimationVector2D(Float.intBitsToFloat((int) (size.m4417unboximpl() >> 32)), Float.intBitsToFloat((int) (size.m4417unboximpl() & 4294967295L)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Size SizeToVector$lambda$9(AnimationVector2D animationVector2D) {
        float v1 = animationVector2D.getV1();
        float v2 = animationVector2D.getV2();
        return Size.m4400boximpl(Size.m4403constructorimpl((Float.floatToRawIntBits(v1) << 32) | (Float.floatToRawIntBits(v2) & 4294967295L)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationVector2D OffsetToVector$lambda$10(Offset offset) {
        return new AnimationVector2D(Float.intBitsToFloat((int) (offset.m4353unboximpl() >> 32)), Float.intBitsToFloat((int) (offset.m4353unboximpl() & 4294967295L)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset OffsetToVector$lambda$11(AnimationVector2D animationVector2D) {
        float v1 = animationVector2D.getV1();
        float v2 = animationVector2D.getV2();
        return Offset.m4332boximpl(Offset.m4335constructorimpl((Float.floatToRawIntBits(v1) << 32) | (Float.floatToRawIntBits(v2) & 4294967295L)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationVector2D IntOffsetToVector$lambda$12(IntOffset intOffset) {
        return new AnimationVector2D(IntOffset.m7531getXimpl(intOffset.m7540unboximpl()), IntOffset.m7532getYimpl(intOffset.m7540unboximpl()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntOffset IntOffsetToVector$lambda$13(AnimationVector2D animationVector2D) {
        return IntOffset.m7522boximpl(IntOffset.m7525constructorimpl((Math.round(animationVector2D.getV1()) << 32) | (Math.round(animationVector2D.getV2()) & 4294967295L)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationVector2D IntSizeToVector$lambda$14(IntSize intSize) {
        return new AnimationVector2D((int) (intSize.m7578unboximpl() >> 32), (int) (intSize.m7578unboximpl() & 4294967295L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntSize IntSizeToVector$lambda$15(AnimationVector2D animationVector2D) {
        int round = Math.round(animationVector2D.getV1());
        if (round < 0) {
            round = 0;
        }
        return IntSize.m7566boximpl(IntSize.m7569constructorimpl(((Math.round(animationVector2D.getV2()) >= 0 ? r6 : 0) & 4294967295L) | (round << 32)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationVector4D RectToVector$lambda$16(Rect rect) {
        return new AnimationVector4D(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect RectToVector$lambda$17(AnimationVector4D animationVector4D) {
        return new Rect(animationVector4D.getV1(), animationVector4D.getV2(), animationVector4D.getV3(), animationVector4D.getV4());
    }
}
