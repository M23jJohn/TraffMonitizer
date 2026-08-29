package androidx.compose.animation.core;

import kotlin.Metadata;

/* compiled from: MonoSpline.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\u001a8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0000\u001a8\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"MonoSplineIsExtrapolate", "", "hermiteInterpolate", "", "h", "x", "y1", "y2", "t1", "t2", "hermiteDifferential", "animation-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MonoSplineKt {
    private static final boolean MonoSplineIsExtrapolate = true;

    public static final float hermiteDifferential(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f2 * f2;
        float f8 = 2;
        return (f * ((f5 - ((f8 * f2) * ((f8 * f5) + f6))) + ((3 * (f5 + f6)) * f7))) - ((6 * (f2 - f7)) * (f3 - f4));
    }

    public static final float hermiteInterpolate(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f2 * f2;
        float f8 = f7 * f2;
        float f9 = 2;
        return ((((f5 * f) * ((f2 - (f9 * f7)) + f8)) + ((f * f6) * (f8 - f7))) + f3) - (((3 * f7) - (f9 * f8)) * (f3 - f4));
    }
}
