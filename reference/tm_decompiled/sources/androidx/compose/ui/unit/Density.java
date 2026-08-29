package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.FontScaling;
import kotlin.Metadata;

/* compiled from: Density.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0013\u0010\b\u001a\u00020\u0003*\u00020\tH\u0017¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r*\u00020\tH\u0017¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\b\u001a\u00020\u0003*\u00020\u0010H\u0017¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\f\u001a\u00020\r*\u00020\u0010H\u0017¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\t*\u00020\rH\u0017¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u0010*\u00020\rH\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u0015\u001a\u00020\t*\u00020\u0003H\u0017¢\u0006\u0004\b\u0016\u0010\u000bJ\u0013\u0010\u0018\u001a\u00020\u0010*\u00020\u0003H\u0017¢\u0006\u0004\b\u0019\u0010\u001bJ\f\u0010\u001c\u001a\u00020\u001d*\u00020\u001eH\u0017J\u0013\u0010\u001f\u001a\u00020 *\u00020!H\u0017¢\u0006\u0004\b\"\u0010#J\u0013\u0010$\u001a\u00020!*\u00020 H\u0017¢\u0006\u0004\b%\u0010#R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006&À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/FontScaling;", "density", "", "getDensity$annotations", "()V", "getDensity", "()F", "toPx", "Landroidx/compose/ui/unit/Dp;", "toPx-0680j_4", "(F)F", "roundToPx", "", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "toPx--R2X_6o", "(J)F", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(I)F", "toSp", "toSp-kPz2Gy4", "(I)J", "(F)J", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/unit/DpSize;", "toSize-XkaWNTQ", "(J)J", "toDpSize", "toDpSize-k-rfVVM", "ui-unit"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface Density extends FontScaling {
    float getDensity();

    /* renamed from: roundToPx--R2X_6o */
    int mo452roundToPxR2X_6o(long j);

    /* renamed from: roundToPx-0680j_4 */
    int mo453roundToPx0680j_4(float f);

    /* renamed from: toDp-u2uoSUM */
    float mo455toDpu2uoSUM(float f);

    /* renamed from: toDp-u2uoSUM */
    float mo456toDpu2uoSUM(int i);

    /* renamed from: toDpSize-k-rfVVM */
    long mo457toDpSizekrfVVM(long j);

    /* renamed from: toPx--R2X_6o */
    float mo458toPxR2X_6o(long j);

    /* renamed from: toPx-0680j_4 */
    float mo459toPx0680j_4(float f);

    Rect toRect(DpRect dpRect);

    /* renamed from: toSize-XkaWNTQ */
    long mo460toSizeXkaWNTQ(long j);

    /* renamed from: toSp-kPz2Gy4 */
    long mo462toSpkPz2Gy4(float f);

    /* renamed from: toSp-kPz2Gy4 */
    long mo463toSpkPz2Gy4(int i);

    /* compiled from: Density.kt */
    /* renamed from: androidx.compose.ui.unit.Density$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        /* renamed from: $default$toPx-0680j_4, reason: not valid java name */
        public static float m7369$default$toPx0680j_4(Density _this, float f) {
            return f * _this.getDensity();
        }

        /* renamed from: $default$roundToPx-0680j_4, reason: not valid java name */
        public static int m7364$default$roundToPx0680j_4(Density _this, float f) {
            float mo459toPx0680j_4 = _this.mo459toPx0680j_4(f);
            if (Float.isInfinite(mo459toPx0680j_4)) {
                return Integer.MAX_VALUE;
            }
            return Math.round(mo459toPx0680j_4);
        }

        /* renamed from: $default$toPx--R2X_6o, reason: not valid java name */
        public static float m7368$default$toPxR2X_6o(Density _this, long j) {
            if (!TextUnitType.m7625equalsimpl0(TextUnit.m7596getTypeUIouoOA(j), TextUnitType.INSTANCE.m7630getSpUIouoOA())) {
                InlineClassHelperKt.throwIllegalStateException("Only Sp can convert to Px");
            }
            return _this.mo459toPx0680j_4(_this.mo454toDpGaN1DYA(j));
        }

        /* renamed from: $default$roundToPx--R2X_6o, reason: not valid java name */
        public static int m7363$default$roundToPxR2X_6o(Density _this, long j) {
            return Math.round(_this.mo458toPxR2X_6o(j));
        }

        /* renamed from: $default$toDp-u2uoSUM, reason: not valid java name */
        public static float m7366$default$toDpu2uoSUM(Density _this, int i) {
            return Dp.m7399constructorimpl(i / _this.getDensity());
        }

        /* renamed from: $default$toSp-kPz2Gy4, reason: not valid java name */
        public static long m7372$default$toSpkPz2Gy4(Density _this, int i) {
            return _this.mo461toSp0xMU5do(_this.mo456toDpu2uoSUM(i));
        }

        /* renamed from: $default$toDp-u2uoSUM, reason: not valid java name */
        public static float m7365$default$toDpu2uoSUM(Density _this, float f) {
            return Dp.m7399constructorimpl(f / _this.getDensity());
        }

        /* renamed from: $default$toSp-kPz2Gy4, reason: not valid java name */
        public static long m7371$default$toSpkPz2Gy4(Density _this, float f) {
            return _this.mo461toSp0xMU5do(_this.mo455toDpu2uoSUM(f));
        }

        public static Rect $default$toRect(Density _this, DpRect dpRect) {
            return new Rect(_this.mo459toPx0680j_4(dpRect.m7482getLeftD9Ej5fM()), _this.mo459toPx0680j_4(dpRect.m7484getTopD9Ej5fM()), _this.mo459toPx0680j_4(dpRect.m7483getRightD9Ej5fM()), _this.mo459toPx0680j_4(dpRect.m7481getBottomD9Ej5fM()));
        }

        /* renamed from: $default$toSize-XkaWNTQ, reason: not valid java name */
        public static long m7370$default$toSizeXkaWNTQ(Density _this, long j) {
            if (j == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
                return Size.INSTANCE.m4420getUnspecifiedNHjbRc();
            }
            float mo459toPx0680j_4 = _this.mo459toPx0680j_4(DpSize.m7497getWidthD9Ej5fM(j));
            float mo459toPx0680j_42 = _this.mo459toPx0680j_4(DpSize.m7495getHeightD9Ej5fM(j));
            return Size.m4403constructorimpl((Float.floatToRawIntBits(mo459toPx0680j_42) & 4294967295L) | (Float.floatToRawIntBits(mo459toPx0680j_4) << 32));
        }

        /* renamed from: $default$toDpSize-k-rfVVM, reason: not valid java name */
        public static long m7367$default$toDpSizekrfVVM(Density _this, long j) {
            return j != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? DpKt.m7421DpSizeYgX7TsA(_this.mo455toDpu2uoSUM(Float.intBitsToFloat((int) (j >> 32))), _this.mo455toDpu2uoSUM(Float.intBitsToFloat((int) (j & 4294967295L)))) : DpSize.INSTANCE.m7506getUnspecifiedMYxV2XQ();
        }
    }

    /* compiled from: Density.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void getDensity$annotations() {
        }

        @Deprecated
        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m7387toDpGaN1DYA(Density density, long j) {
            return FontScaling.CC.m7508$default$toDpGaN1DYA(density, j);
        }

        @Deprecated
        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m7394toSp0xMU5do(Density density, float f) {
            return FontScaling.CC.m7509$default$toSp0xMU5do(density, f);
        }

        @Deprecated
        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m7392toPx0680j_4(Density density, float f) {
            return CC.m7369$default$toPx0680j_4(density, f);
        }

        @Deprecated
        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m7386roundToPx0680j_4(Density density, float f) {
            return CC.m7364$default$roundToPx0680j_4(density, f);
        }

        @Deprecated
        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m7391toPxR2X_6o(Density density, long j) {
            return CC.m7368$default$toPxR2X_6o(density, j);
        }

        @Deprecated
        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m7385roundToPxR2X_6o(Density density, long j) {
            return CC.m7363$default$roundToPxR2X_6o(density, j);
        }

        @Deprecated
        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m7389toDpu2uoSUM(Density density, int i) {
            return CC.m7366$default$toDpu2uoSUM(density, i);
        }

        @Deprecated
        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m7396toSpkPz2Gy4(Density density, int i) {
            return CC.m7372$default$toSpkPz2Gy4(density, i);
        }

        @Deprecated
        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m7388toDpu2uoSUM(Density density, float f) {
            return CC.m7365$default$toDpu2uoSUM(density, f);
        }

        @Deprecated
        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m7395toSpkPz2Gy4(Density density, float f) {
            return CC.m7371$default$toSpkPz2Gy4(density, f);
        }

        @Deprecated
        public static Rect toRect(Density density, DpRect dpRect) {
            return CC.$default$toRect(density, dpRect);
        }

        @Deprecated
        /* renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m7393toSizeXkaWNTQ(Density density, long j) {
            return CC.m7370$default$toSizeXkaWNTQ(density, j);
        }

        @Deprecated
        /* renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m7390toDpSizekrfVVM(Density density, long j) {
            return CC.m7367$default$toDpSizekrfVVM(density, j);
        }
    }
}
