package androidx.compose.ui.draw;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.FontScaling;
import kotlin.Metadata;

/* compiled from: Shadow.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u0018\u0010\u000b\u001a\u00020\fX¦\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u0012X¦\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0017\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0005\"\u0004\b\u0019\u0010\u0007R\u0018\u0010\u001a\u001a\u00020\u001bX¦\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0018\u0010 \u001a\u00020!X¦\u000e¢\u0006\f\u001a\u0004\b\"\u0010\u000e\"\u0004\b#\u0010\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006$À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/draw/ShadowScope;", "Landroidx/compose/ui/unit/Density;", "radius", "", "getRadius", "()F", "setRadius", "(F)V", "spread", "getSpread", "setSpread", "color", "Landroidx/compose/ui/graphics/Color;", "getColor-0d7_KjU", "()J", "setColor-8_81llA", "(J)V", "brush", "Landroidx/compose/ui/graphics/Brush;", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "setBrush", "(Landroidx/compose/ui/graphics/Brush;)V", "alpha", "getAlpha", "setAlpha", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", "offset", "Landroidx/compose/ui/geometry/Offset;", "getOffset-F1C5BW0", "setOffset-k-4lQ0M", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ShadowScope extends Density {
    float getAlpha();

    /* renamed from: getBlendMode-0nO6VwU */
    int mo4106getBlendMode0nO6VwU();

    Brush getBrush();

    /* renamed from: getColor-0d7_KjU */
    long mo4107getColor0d7_KjU();

    /* renamed from: getOffset-F1C5BW0 */
    long mo4108getOffsetF1C5BW0();

    float getRadius();

    float getSpread();

    void setAlpha(float f);

    /* renamed from: setBlendMode-s9anfk8 */
    void mo4109setBlendModes9anfk8(int i);

    void setBrush(Brush brush);

    /* renamed from: setColor-8_81llA */
    void mo4110setColor8_81llA(long j);

    /* renamed from: setOffset-k-4lQ0M */
    void mo4111setOffsetk4lQ0M(long j);

    void setRadius(float f);

    void setSpread(float f);

    /* compiled from: Shadow.kt */
    /* renamed from: androidx.compose.ui.draw.ShadowScope$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    /* compiled from: Shadow.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m4205roundToPxR2X_6o(ShadowScope shadowScope, long j) {
            return Density.CC.m7363$default$roundToPxR2X_6o(shadowScope, j);
        }

        @Deprecated
        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m4206roundToPx0680j_4(ShadowScope shadowScope, float f) {
            return Density.CC.m7364$default$roundToPx0680j_4(shadowScope, f);
        }

        @Deprecated
        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m4207toDpGaN1DYA(ShadowScope shadowScope, long j) {
            return FontScaling.CC.m7508$default$toDpGaN1DYA(shadowScope, j);
        }

        @Deprecated
        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4208toDpu2uoSUM(ShadowScope shadowScope, float f) {
            return Density.CC.m7365$default$toDpu2uoSUM(shadowScope, f);
        }

        @Deprecated
        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4209toDpu2uoSUM(ShadowScope shadowScope, int i) {
            return Density.CC.m7366$default$toDpu2uoSUM((Density) shadowScope, i);
        }

        @Deprecated
        /* renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m4210toDpSizekrfVVM(ShadowScope shadowScope, long j) {
            return Density.CC.m7367$default$toDpSizekrfVVM(shadowScope, j);
        }

        @Deprecated
        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m4211toPxR2X_6o(ShadowScope shadowScope, long j) {
            return Density.CC.m7368$default$toPxR2X_6o(shadowScope, j);
        }

        @Deprecated
        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m4212toPx0680j_4(ShadowScope shadowScope, float f) {
            return Density.CC.m7369$default$toPx0680j_4(shadowScope, f);
        }

        @Deprecated
        public static Rect toRect(ShadowScope shadowScope, DpRect dpRect) {
            return Density.CC.$default$toRect(shadowScope, dpRect);
        }

        @Deprecated
        /* renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m4213toSizeXkaWNTQ(ShadowScope shadowScope, long j) {
            return Density.CC.m7370$default$toSizeXkaWNTQ(shadowScope, j);
        }

        @Deprecated
        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m4214toSp0xMU5do(ShadowScope shadowScope, float f) {
            return FontScaling.CC.m7509$default$toSp0xMU5do(shadowScope, f);
        }

        @Deprecated
        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4215toSpkPz2Gy4(ShadowScope shadowScope, float f) {
            return Density.CC.m7371$default$toSpkPz2Gy4(shadowScope, f);
        }

        @Deprecated
        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4216toSpkPz2Gy4(ShadowScope shadowScope, int i) {
            return Density.CC.m7372$default$toSpkPz2Gy4((Density) shadowScope, i);
        }
    }
}
