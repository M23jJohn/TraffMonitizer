package androidx.compose.ui.draw;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.FontScaling;
import kotlin.Metadata;

/* compiled from: Shadow.kt */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/draw/InnerShadowScope;", "Landroidx/compose/ui/draw/ShadowScope;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public interface InnerShadowScope extends ShadowScope {

    /* compiled from: Shadow.kt */
    /* renamed from: androidx.compose.ui.draw.InnerShadowScope$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    /* compiled from: Shadow.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m4165roundToPxR2X_6o(InnerShadowScope innerShadowScope, long j) {
            return Density.CC.m7363$default$roundToPxR2X_6o(innerShadowScope, j);
        }

        @Deprecated
        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m4166roundToPx0680j_4(InnerShadowScope innerShadowScope, float f) {
            return Density.CC.m7364$default$roundToPx0680j_4(innerShadowScope, f);
        }

        @Deprecated
        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m4167toDpGaN1DYA(InnerShadowScope innerShadowScope, long j) {
            return FontScaling.CC.m7508$default$toDpGaN1DYA(innerShadowScope, j);
        }

        @Deprecated
        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4168toDpu2uoSUM(InnerShadowScope innerShadowScope, float f) {
            return Density.CC.m7365$default$toDpu2uoSUM(innerShadowScope, f);
        }

        @Deprecated
        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4169toDpu2uoSUM(InnerShadowScope innerShadowScope, int i) {
            return Density.CC.m7366$default$toDpu2uoSUM((Density) innerShadowScope, i);
        }

        @Deprecated
        /* renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m4170toDpSizekrfVVM(InnerShadowScope innerShadowScope, long j) {
            return Density.CC.m7367$default$toDpSizekrfVVM(innerShadowScope, j);
        }

        @Deprecated
        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m4171toPxR2X_6o(InnerShadowScope innerShadowScope, long j) {
            return Density.CC.m7368$default$toPxR2X_6o(innerShadowScope, j);
        }

        @Deprecated
        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m4172toPx0680j_4(InnerShadowScope innerShadowScope, float f) {
            return Density.CC.m7369$default$toPx0680j_4(innerShadowScope, f);
        }

        @Deprecated
        public static Rect toRect(InnerShadowScope innerShadowScope, DpRect dpRect) {
            return Density.CC.$default$toRect(innerShadowScope, dpRect);
        }

        @Deprecated
        /* renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m4173toSizeXkaWNTQ(InnerShadowScope innerShadowScope, long j) {
            return Density.CC.m7370$default$toSizeXkaWNTQ(innerShadowScope, j);
        }

        @Deprecated
        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m4174toSp0xMU5do(InnerShadowScope innerShadowScope, float f) {
            return FontScaling.CC.m7509$default$toSp0xMU5do(innerShadowScope, f);
        }

        @Deprecated
        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4175toSpkPz2Gy4(InnerShadowScope innerShadowScope, float f) {
            return Density.CC.m7371$default$toSpkPz2Gy4(innerShadowScope, f);
        }

        @Deprecated
        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4176toSpkPz2Gy4(InnerShadowScope innerShadowScope, int i) {
            return Density.CC.m7372$default$toSpkPz2Gy4((Density) innerShadowScope, i);
        }
    }
}
