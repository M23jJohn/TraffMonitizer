package androidx.compose.animation.core;

import kotlin.Metadata;

/* compiled from: MonoSpline.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ#\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0011J \u0010\u0014\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u0011J\u0018\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0003H\u0002J \u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u0011J\u0018\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0011H\u0002R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/compose/animation/core/MonoSpline;", "", "time", "", "y", "", "periodicBias", "", "<init>", "([F[[FF)V", "timePoints", "values", "[[F", "tangents", "slopeTemp", "makeFloatArray", "a", "", "b", "(II)[[F", "getPos", "t", "j", "", "v", "Landroidx/compose/animation/core/AnimationVector;", "index", "getSlope", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MonoSpline {
    public static final int $stable = 8;
    private final float[] slopeTemp;
    private final float[][] tangents;
    private final float[] timePoints;
    private final float[][] values;

    public MonoSpline(float[] fArr, float[][] fArr2, float f) {
        int i;
        int length = fArr.length;
        int i2 = 0;
        int length2 = fArr2[0].length;
        this.slopeTemp = new float[length2];
        int i3 = length - 1;
        float[][] makeFloatArray = makeFloatArray(i3, length2);
        float[][] makeFloatArray2 = makeFloatArray(length, length2);
        for (int i4 = 0; i4 < length2; i4++) {
            int i5 = 0;
            while (i5 < i3) {
                int i6 = i5 + 1;
                float f2 = fArr[i6] - fArr[i5];
                float[] fArr3 = makeFloatArray[i5];
                float f3 = (fArr2[i6][i4] - fArr2[i5][i4]) / f2;
                fArr3[i4] = f3;
                if (i5 == 0) {
                    makeFloatArray2[i5][i4] = f3;
                } else {
                    makeFloatArray2[i5][i4] = (makeFloatArray[i5 - 1][i4] + f3) * 0.5f;
                }
                i5 = i6;
            }
            makeFloatArray2[i3][i4] = makeFloatArray[length - 2][i4];
        }
        if (!Float.isNaN(f)) {
            for (int i7 = 0; i7 < length2; i7++) {
                float[] fArr4 = makeFloatArray[length - 2];
                float f4 = fArr4[i7] * (1 - f);
                float[] fArr5 = makeFloatArray[0];
                float f5 = f4 + (fArr5[i7] * f);
                fArr5[i7] = f5;
                fArr4[i7] = f5;
                makeFloatArray2[i3][i7] = f5;
                makeFloatArray2[0][i7] = f5;
            }
        }
        int i8 = 0;
        while (i8 < i3) {
            int i9 = i2;
            while (i9 < length2) {
                float f6 = makeFloatArray[i8][i9];
                if (f6 == 0.0f) {
                    makeFloatArray2[i8][i9] = 0.0f;
                    makeFloatArray2[i8 + 1][i9] = 0.0f;
                    i = length2;
                } else {
                    float f7 = makeFloatArray2[i8][i9] / f6;
                    int i10 = i8 + 1;
                    float f8 = makeFloatArray2[i10][i9] / f6;
                    i = length2;
                    float hypot = (float) Math.hypot(f7, f8);
                    if (hypot > 9.0d) {
                        float f9 = 3.0f / hypot;
                        float[] fArr6 = makeFloatArray2[i8];
                        float[] fArr7 = makeFloatArray[i8];
                        fArr6[i9] = f7 * f9 * fArr7[i9];
                        makeFloatArray2[i10][i9] = f9 * f8 * fArr7[i9];
                    }
                }
                i9++;
                length2 = i;
            }
            i8++;
            i2 = 0;
        }
        this.timePoints = fArr;
        this.values = fArr2;
        this.tangents = makeFloatArray2;
    }

    private final float[][] makeFloatArray(int a, int b) {
        float[][] fArr = new float[a][];
        for (int i = 0; i < a; i++) {
            fArr[i] = new float[b];
        }
        return fArr;
    }

    public final float getPos(float t, int j) {
        int i;
        float[][] fArr = this.values;
        float[][] fArr2 = this.tangents;
        float[] fArr3 = this.timePoints;
        int length = fArr3.length;
        int i2 = 0;
        if (t <= fArr3[0]) {
            i = 0;
        } else {
            i = length - 1;
            if (t < fArr3[i]) {
                i = -1;
            }
        }
        if (i != -1) {
            float f = fArr[i][j];
            float f2 = fArr3[i];
            return f + ((t - f2) * getSlope(f2, j));
        }
        int i3 = length - 1;
        while (i2 < i3) {
            float[] fArr4 = this.timePoints;
            float f3 = fArr4[i2];
            if (t == f3) {
                return fArr[i2][j];
            }
            int i4 = i2 + 1;
            float f4 = fArr4[i4];
            if (t < f4) {
                float f5 = f4 - f3;
                return MonoSplineKt.hermiteInterpolate(f5, (t - f3) / f5, fArr[i2][j], fArr[i4][j], fArr2[i2][j], fArr2[i4][j]);
            }
            i2 = i4;
        }
        return 0.0f;
    }

    public static /* synthetic */ void getPos$default(MonoSpline monoSpline, float f, AnimationVector animationVector, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        monoSpline.getPos(f, animationVector, i);
    }

    public final void getPos(float time, AnimationVector v, int index) {
        int i;
        float[] fArr = this.timePoints;
        int length = fArr.length;
        int i2 = 0;
        int length2 = this.values[0].length;
        if (time <= fArr[0]) {
            i = 0;
        } else {
            i = length - 1;
            if (time < fArr[i]) {
                i = -1;
            }
        }
        if (i != -1) {
            getSlope(fArr[i], this.slopeTemp);
            while (i2 < length2) {
                v.set$animation_core(i2, this.values[i][i2] + ((time - this.timePoints[i]) * this.slopeTemp[i2]));
                i2++;
            }
            return;
        }
        int i3 = length - 1;
        int i4 = index;
        while (i4 < i3) {
            float[] fArr2 = this.timePoints;
            float f = fArr2[i4];
            if (time == f) {
                while (i2 < length2) {
                    v.set$animation_core(i2, this.values[i4][i2]);
                    i2++;
                }
                return;
            }
            int i5 = i4 + 1;
            float f2 = fArr2[i5];
            if (time < f2) {
                float f3 = f2 - f;
                float f4 = (time - f) / f3;
                while (i2 < length2) {
                    float[][] fArr3 = this.values;
                    float f5 = fArr3[i4][i2];
                    float f6 = fArr3[i5][i2];
                    float[][] fArr4 = this.tangents;
                    v.set$animation_core(i2, MonoSplineKt.hermiteInterpolate(f3, f4, f5, f6, fArr4[i4][i2], fArr4[i5][i2]));
                    i2++;
                }
                return;
            }
            i4 = i5;
        }
    }

    private final void getSlope(float time, float[] v) {
        int length = this.values[0].length;
        float[] fArr = this.timePoints;
        int length2 = fArr.length;
        float f = fArr[0];
        int i = length2 - 1;
        float f2 = fArr[i];
        if (time < f) {
            time = f;
        }
        if (time <= f2) {
            f2 = time;
        }
        if (v.length < length) {
            return;
        }
        int i2 = 0;
        while (i2 < i) {
            float[] fArr2 = this.timePoints;
            int i3 = i2 + 1;
            float f3 = fArr2[i3];
            if (f2 <= f3) {
                float f4 = fArr2[i2];
                float f5 = f3 - f4;
                float f6 = (f2 - f4) / f5;
                for (int i4 = 0; i4 < length; i4++) {
                    float[][] fArr3 = this.values;
                    float f7 = fArr3[i2][i4];
                    float f8 = fArr3[i3][i4];
                    float[][] fArr4 = this.tangents;
                    v[i4] = MonoSplineKt.hermiteDifferential(f5, f6, f7, f8, fArr4[i2][i4], fArr4[i3][i4]) / f5;
                }
                return;
            }
            i2 = i3;
        }
    }

    public static /* synthetic */ void getSlope$default(MonoSpline monoSpline, float f, AnimationVector animationVector, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        monoSpline.getSlope(f, animationVector, i);
    }

    public final void getSlope(float time, AnimationVector v, int index) {
        int i;
        float[] fArr = this.timePoints;
        float[][] fArr2 = this.values;
        float[][] fArr3 = this.tangents;
        int length = fArr.length;
        int i2 = 0;
        int length2 = fArr2[0].length;
        if (time <= fArr[0]) {
            i = 0;
        } else {
            i = length - 1;
            if (time < fArr[i]) {
                i = -1;
            }
        }
        if (i != -1) {
            float[] fArr4 = fArr3[i];
            if (fArr4.length < length2) {
                return;
            }
            while (i2 < length2) {
                v.set$animation_core(i2, fArr4[i2]);
                i2++;
            }
            return;
        }
        int i3 = length - 1;
        int i4 = index;
        while (i4 < i3) {
            int i5 = i4 + 1;
            float f = fArr[i5];
            if (time <= f) {
                float f2 = fArr[i4];
                float f3 = f - f2;
                float f4 = (time - f2) / f3;
                while (i2 < length2) {
                    v.set$animation_core(i2, MonoSplineKt.hermiteDifferential(f3, f4, fArr2[i4][i2], fArr2[i5][i2], fArr3[i4][i2], fArr3[i5][i2]) / f3);
                    i2++;
                }
                return;
            }
            i4 = i5;
        }
    }

    private final float getSlope(float time, int j) {
        float[] fArr = this.timePoints;
        float[][] fArr2 = this.values;
        float[][] fArr3 = this.tangents;
        int length = fArr.length;
        int i = 0;
        float f = fArr[0];
        int i2 = length - 1;
        float f2 = fArr[i2];
        if (time < f) {
            time = f;
        }
        if (time <= f2) {
            f2 = time;
        }
        while (i < i2) {
            int i3 = i + 1;
            float f3 = fArr[i3];
            if (f2 <= f3) {
                float f4 = fArr2[i][j];
                float f5 = fArr2[i3][j];
                float f6 = fArr3[i][j];
                float f7 = fArr3[i3][j];
                float f8 = fArr[i];
                float f9 = f3 - f8;
                return MonoSplineKt.hermiteDifferential(f9, (f2 - f8) / f9, f4, f5, f6, f7) / f9;
            }
            i = i3;
        }
        return 0.0f;
    }
}
