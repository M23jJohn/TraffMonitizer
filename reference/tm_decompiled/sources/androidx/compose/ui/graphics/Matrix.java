package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Matrix.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b)\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 Q2\u00020\u0001:\u0001QB\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0086\n¢\u0006\u0004\b\r\u0010\u000eJ(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\tH\u0086\n¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001d¢\u0006\u0004\b\u001b\u0010\u001eJ\u0018\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020$H\u0016¢\u0006\u0004\b%\u0010&J\r\u0010'\u001a\u00020\u0010¢\u0006\u0004\b(\u0010)J\r\u0010*\u001a\u00020\u0010¢\u0006\u0004\b+\u0010)J\u0015\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0000¢\u0006\u0004\b.\u0010\"J\u0015\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\t¢\u0006\u0004\b1\u00102J\u0015\u00103\u001a\u00020\u00102\u0006\u00100\u001a\u00020\t¢\u0006\u0004\b4\u00102J\u0015\u00105\u001a\u00020\u00102\u0006\u00100\u001a\u00020\t¢\u0006\u0004\b6\u00102J+\u00107\u001a\u00020\u00102\b\b\u0002\u00108\u001a\u00020\t2\b\b\u0002\u00109\u001a\u00020\t2\b\b\u0002\u0010:\u001a\u00020\t¢\u0006\u0004\b;\u0010<J+\u0010=\u001a\u00020\u00102\b\b\u0002\u00108\u001a\u00020\t2\b\b\u0002\u00109\u001a\u00020\t2\b\b\u0002\u0010:\u001a\u00020\t¢\u0006\u0004\b>\u0010<J{\u0010?\u001a\u00020\u00102\b\b\u0002\u0010@\u001a\u00020\t2\b\b\u0002\u0010A\u001a\u00020\t2\b\b\u0002\u0010B\u001a\u00020\t2\b\b\u0002\u0010C\u001a\u00020\t2\b\b\u0002\u0010D\u001a\u00020\t2\b\b\u0002\u0010E\u001a\u00020\t2\b\b\u0002\u0010F\u001a\u00020\t2\b\b\u0002\u0010G\u001a\u00020\t2\b\b\u0002\u0010H\u001a\u00020\t2\b\b\u0002\u0010I\u001a\u00020\t2\b\b\u0002\u0010J\u001a\u00020\t¢\u0006\u0004\bK\u0010LJ\u0013\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010P\u001a\u00020\u000bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006R"}, d2 = {"Landroidx/compose/ui/graphics/Matrix;", "", "values", "", "constructor-impl", "([F)[F", "getValues", "()[F", "get", "", "row", "", "column", "get-impl", "([FII)F", "set", "", "v", "set-impl", "([FIIF)V", "map", "Landroidx/compose/ui/geometry/Offset;", "point", "map-MK-Hz9U", "([FJ)J", "Landroidx/compose/ui/geometry/Rect;", "rect", "map-impl", "([FLandroidx/compose/ui/geometry/Rect;)Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/geometry/MutableRect;", "([FLandroidx/compose/ui/geometry/MutableRect;)V", "timesAssign", "m", "timesAssign-58bKbWc", "([F[F)V", "toString", "", "toString-impl", "([F)Ljava/lang/String;", "invert", "invert-impl", "([F)V", "reset", "reset-impl", "setFrom", "matrix", "setFrom-58bKbWc", "rotateX", "degrees", "rotateX-impl", "([FF)V", "rotateY", "rotateY-impl", "rotateZ", "rotateZ-impl", "scale", "x", "y", "z", "scale-impl", "([FFFF)V", "translate", "translate-impl", "resetToPivotedTransform", "pivotX", "pivotY", "translationX", "translationY", "translationZ", "rotationX", "rotationY", "rotationZ", "scaleX", "scaleY", "scaleZ", "resetToPivotedTransform-impl", "([FFFFFFFFFFFF)V", "equals", "", "other", "hashCode", "Companion", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
/* loaded from: classes.dex */
public final class Matrix {
    public static final int Perspective0 = 3;
    public static final int Perspective1 = 7;
    public static final int Perspective2 = 15;
    public static final int ScaleX = 0;
    public static final int ScaleY = 5;
    public static final int ScaleZ = 10;
    public static final int SkewX = 4;
    public static final int SkewY = 1;
    public static final int TranslateX = 12;
    public static final int TranslateY = 13;
    public static final int TranslateZ = 14;
    private final float[] values;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Matrix m4864boximpl(float[] fArr) {
        return new Matrix(fArr);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static float[] m4865constructorimpl(float[] fArr) {
        return fArr;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m4867equalsimpl(float[] fArr, Object obj) {
        return (obj instanceof Matrix) && Intrinsics.areEqual(fArr, ((Matrix) obj).m4889unboximpl());
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4868equalsimpl0(float[] fArr, float[] fArr2) {
        return Intrinsics.areEqual(fArr, fArr2);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m4870hashCodeimpl(float[] fArr) {
        return Arrays.hashCode(fArr);
    }

    public boolean equals(Object other) {
        return m4867equalsimpl(this.values, other);
    }

    public int hashCode() {
        return m4870hashCodeimpl(this.values);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ float[] m4889unboximpl() {
        return this.values;
    }

    private /* synthetic */ Matrix(float[] fArr) {
        this.values = fArr;
    }

    public final float[] getValues() {
        return this.values;
    }

    /* renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ float[] m4866constructorimpl$default(float[] fArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            fArr = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        }
        return m4865constructorimpl(fArr);
    }

    /* renamed from: get-impl, reason: not valid java name */
    public static final float m4869getimpl(float[] fArr, int i, int i2) {
        return fArr[(i * 4) + i2];
    }

    /* renamed from: set-impl, reason: not valid java name */
    public static final void m4883setimpl(float[] fArr, int i, int i2, float f) {
        fArr[(i * 4) + i2] = f;
    }

    /* renamed from: map-MK-Hz9U, reason: not valid java name */
    public static final long m4872mapMKHz9U(float[] fArr, long j) {
        if (fArr.length < 16) {
            return j;
        }
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[3];
        float f4 = fArr[4];
        float f5 = fArr[5];
        float f6 = fArr[7];
        float f7 = fArr[12];
        float f8 = fArr[13];
        float f9 = fArr[15];
        float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L));
        float f10 = 1 / (((f3 * intBitsToFloat) + (f6 * intBitsToFloat2)) + f9);
        if ((Float.floatToRawIntBits(f10) & Integer.MAX_VALUE) >= 2139095040) {
            f10 = 0.0f;
        }
        float f11 = ((f * intBitsToFloat) + (f4 * intBitsToFloat2) + f7) * f10;
        float f12 = f10 * ((f2 * intBitsToFloat) + (f5 * intBitsToFloat2) + f8);
        return Offset.m4335constructorimpl((Float.floatToRawIntBits(f11) << 32) | (Float.floatToRawIntBits(f12) & 4294967295L));
    }

    /* renamed from: map-impl, reason: not valid java name */
    public static final Rect m4873mapimpl(float[] fArr, Rect rect) {
        if (fArr.length < 16) {
            return rect;
        }
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[3];
        float f4 = fArr[4];
        float f5 = fArr[5];
        float f6 = fArr[7];
        float f7 = fArr[12];
        float f8 = fArr[13];
        float f9 = fArr[15];
        float left = rect.getLeft();
        float top = rect.getTop();
        float right = rect.getRight();
        float bottom = rect.getBottom();
        float f10 = f3 * left;
        float f11 = f6 * top;
        float f12 = 1.0f / ((f10 + f11) + f9);
        if ((Float.floatToRawIntBits(f12) & Integer.MAX_VALUE) >= 2139095040) {
            f12 = 0.0f;
        }
        float f13 = f * left;
        float f14 = f4 * top;
        float f15 = f12 * (f13 + f14 + f7);
        float f16 = left * f2;
        float f17 = top * f5;
        float f18 = f12 * (f16 + f17 + f8);
        float f19 = f6 * bottom;
        float f20 = 1.0f / ((f10 + f19) + f9);
        if ((Float.floatToRawIntBits(f20) & Integer.MAX_VALUE) >= 2139095040) {
            f20 = 0.0f;
        }
        float f21 = f4 * bottom;
        float f22 = (f13 + f21 + f7) * f20;
        float f23 = f5 * bottom;
        float f24 = f20 * (f16 + f23 + f8);
        float f25 = f3 * right;
        float f26 = 1.0f / ((f11 + f25) + f9);
        if ((Float.floatToRawIntBits(f26) & Integer.MAX_VALUE) >= 2139095040) {
            f26 = 0.0f;
        }
        float f27 = f * right;
        float f28 = f26 * (f27 + f14 + f7);
        float f29 = right * f2;
        float f30 = f26 * (f17 + f29 + f8);
        float f31 = 1.0f / ((f25 + f19) + f9);
        float f32 = (Float.floatToRawIntBits(f31) & Integer.MAX_VALUE) < 2139095040 ? f31 : 0.0f;
        float f33 = (f27 + f21 + f7) * f32;
        float f34 = f32 * (f29 + f23 + f8);
        return new Rect(Math.min(f15, Math.min(f22, Math.min(f28, f33))), Math.min(f18, Math.min(f24, Math.min(f30, f34))), Math.max(f15, Math.max(f22, Math.max(f28, f33))), Math.max(f18, Math.max(f24, Math.max(f30, f34))));
    }

    /* renamed from: map-impl, reason: not valid java name */
    public static final void m4874mapimpl(float[] fArr, MutableRect mutableRect) {
        if (fArr.length < 16) {
            return;
        }
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[3];
        float f4 = fArr[4];
        float f5 = fArr[5];
        float f6 = fArr[7];
        float f7 = fArr[12];
        float f8 = fArr[13];
        float f9 = fArr[15];
        float left = mutableRect.getLeft();
        float top = mutableRect.getTop();
        float right = mutableRect.getRight();
        float bottom = mutableRect.getBottom();
        float f10 = f3 * left;
        float f11 = f6 * top;
        float f12 = 1.0f / ((f10 + f11) + f9);
        if ((Float.floatToRawIntBits(f12) & Integer.MAX_VALUE) >= 2139095040) {
            f12 = 0.0f;
        }
        float f13 = f * left;
        float f14 = f4 * top;
        float f15 = f12 * (f13 + f14 + f7);
        float f16 = left * f2;
        float f17 = top * f5;
        float f18 = f12 * (f16 + f17 + f8);
        float f19 = f6 * bottom;
        float f20 = 1.0f / ((f10 + f19) + f9);
        if ((Float.floatToRawIntBits(f20) & Integer.MAX_VALUE) >= 2139095040) {
            f20 = 0.0f;
        }
        float f21 = f4 * bottom;
        float f22 = (f13 + f21 + f7) * f20;
        float f23 = f5 * bottom;
        float f24 = f20 * (f16 + f23 + f8);
        float f25 = f3 * right;
        float f26 = 1.0f / ((f11 + f25) + f9);
        if ((Float.floatToRawIntBits(f26) & Integer.MAX_VALUE) >= 2139095040) {
            f26 = 0.0f;
        }
        float f27 = f * right;
        float f28 = f26 * (f27 + f14 + f7);
        float f29 = right * f2;
        float f30 = f26 * (f17 + f29 + f8);
        float f31 = 1.0f / ((f25 + f19) + f9);
        float f32 = (Float.floatToRawIntBits(f31) & Integer.MAX_VALUE) < 2139095040 ? f31 : 0.0f;
        float f33 = (f27 + f21 + f7) * f32;
        float f34 = f32 * (f29 + f23 + f8);
        mutableRect.setLeft(Math.min(f15, Math.min(f22, Math.min(f28, f33))));
        mutableRect.setTop(Math.min(f18, Math.min(f24, Math.min(f30, f34))));
        mutableRect.setRight(Math.max(f15, Math.max(f22, Math.max(f28, f33))));
        mutableRect.setBottom(Math.max(f18, Math.max(f24, Math.max(f30, f34))));
    }

    /* renamed from: timesAssign-58bKbWc, reason: not valid java name */
    public static final void m4885timesAssign58bKbWc(float[] fArr, float[] fArr2) {
        if (fArr.length >= 16 && fArr2.length >= 16) {
            float f = fArr[0];
            float f2 = fArr2[0];
            float f3 = fArr[1];
            float f4 = fArr2[4];
            float f5 = fArr[2];
            float f6 = fArr2[8];
            float f7 = fArr[3];
            float f8 = fArr2[12];
            float f9 = (f * f2) + (f3 * f4) + (f5 * f6) + (f7 * f8);
            float f10 = fArr2[1];
            float f11 = fArr2[5];
            float f12 = fArr2[9];
            float f13 = fArr2[13];
            float f14 = (f * f10) + (f3 * f11) + (f5 * f12) + (f7 * f13);
            float f15 = fArr2[2];
            float f16 = fArr2[6];
            float f17 = fArr2[10];
            float f18 = fArr2[14];
            float f19 = (f * f15) + (f3 * f16) + (f5 * f17) + (f7 * f18);
            float f20 = fArr2[3];
            float f21 = fArr2[7];
            float f22 = fArr2[11];
            float f23 = fArr2[15];
            float f24 = (f * f20) + (f3 * f21) + (f5 * f22) + (f7 * f23);
            float f25 = fArr[4];
            float f26 = fArr[5];
            float f27 = fArr[6];
            float f28 = fArr[7];
            float f29 = (f25 * f2) + (f26 * f4) + (f27 * f6) + (f28 * f8);
            float f30 = (f25 * f10) + (f26 * f11) + (f27 * f12) + (f28 * f13);
            float f31 = (f25 * f15) + (f26 * f16) + (f27 * f17) + (f28 * f18);
            float f32 = (f25 * f20) + (f26 * f21) + (f27 * f22) + (f28 * f23);
            float f33 = fArr[8];
            float f34 = fArr[9];
            float f35 = fArr[10];
            float f36 = fArr[11];
            float f37 = (f33 * f2) + (f34 * f4) + (f35 * f6) + (f36 * f8);
            float f38 = (f33 * f10) + (f34 * f11) + (f35 * f12) + (f36 * f13);
            float f39 = (f33 * f15) + (f34 * f16) + (f35 * f17) + (f36 * f18);
            float f40 = (f33 * f20) + (f34 * f21) + (f35 * f22) + (f36 * f23);
            float f41 = fArr[12];
            float f42 = fArr[13];
            float f43 = (f2 * f41) + (f4 * f42);
            float f44 = fArr[14];
            float f45 = f43 + (f6 * f44);
            float f46 = fArr[15];
            fArr[0] = f9;
            fArr[1] = f14;
            fArr[2] = f19;
            fArr[3] = f24;
            fArr[4] = f29;
            fArr[5] = f30;
            fArr[6] = f31;
            fArr[7] = f32;
            fArr[8] = f37;
            fArr[9] = f38;
            fArr[10] = f39;
            fArr[11] = f40;
            fArr[12] = f45 + (f8 * f46);
            fArr[13] = (f10 * f41) + (f11 * f42) + (f12 * f44) + (f13 * f46);
            fArr[14] = (f15 * f41) + (f16 * f42) + (f17 * f44) + (f18 * f46);
            fArr[15] = (f41 * f20) + (f42 * f21) + (f44 * f22) + (f46 * f23);
        }
    }

    public String toString() {
        return m4886toStringimpl(this.values);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m4886toStringimpl(float[] fArr) {
        return StringsKt.trimIndent("\n            |" + fArr[0] + ' ' + fArr[1] + ' ' + fArr[2] + ' ' + fArr[3] + "|\n            |" + fArr[4] + ' ' + fArr[5] + ' ' + fArr[6] + ' ' + fArr[7] + "|\n            |" + fArr[8] + ' ' + fArr[9] + ' ' + fArr[10] + ' ' + fArr[11] + "|\n            |" + fArr[12] + ' ' + fArr[13] + ' ' + fArr[14] + ' ' + fArr[15] + "|\n        ");
    }

    /* renamed from: invert-impl, reason: not valid java name */
    public static final void m4871invertimpl(float[] fArr) {
        if (fArr.length < 16) {
            return;
        }
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[3];
        float f5 = fArr[4];
        float f6 = fArr[5];
        float f7 = fArr[6];
        float f8 = fArr[7];
        float f9 = fArr[8];
        float f10 = fArr[9];
        float f11 = fArr[10];
        float f12 = fArr[11];
        float f13 = fArr[12];
        float f14 = fArr[13];
        float f15 = fArr[14];
        float f16 = fArr[15];
        float f17 = (f * f6) - (f2 * f5);
        float f18 = (f * f7) - (f3 * f5);
        float f19 = (f * f8) - (f4 * f5);
        float f20 = (f2 * f7) - (f3 * f6);
        float f21 = (f2 * f8) - (f4 * f6);
        float f22 = (f3 * f8) - (f4 * f7);
        float f23 = (f9 * f14) - (f10 * f13);
        float f24 = (f9 * f15) - (f11 * f13);
        float f25 = (f9 * f16) - (f12 * f13);
        float f26 = (f10 * f15) - (f11 * f14);
        float f27 = (f10 * f16) - (f12 * f14);
        float f28 = (f11 * f16) - (f12 * f15);
        float f29 = (((((f17 * f28) - (f18 * f27)) + (f19 * f26)) + (f20 * f25)) - (f21 * f24)) + (f22 * f23);
        if (f29 == 0.0f) {
            return;
        }
        float f30 = 1.0f / f29;
        fArr[0] = (((f6 * f28) - (f7 * f27)) + (f8 * f26)) * f30;
        fArr[1] = ((((-f2) * f28) + (f3 * f27)) - (f4 * f26)) * f30;
        fArr[2] = (((f14 * f22) - (f15 * f21)) + (f16 * f20)) * f30;
        fArr[3] = ((((-f10) * f22) + (f11 * f21)) - (f12 * f20)) * f30;
        float f31 = -f5;
        fArr[4] = (((f31 * f28) + (f7 * f25)) - (f8 * f24)) * f30;
        fArr[5] = (((f28 * f) - (f3 * f25)) + (f4 * f24)) * f30;
        float f32 = -f13;
        fArr[6] = (((f32 * f22) + (f15 * f19)) - (f16 * f18)) * f30;
        fArr[7] = (((f22 * f9) - (f11 * f19)) + (f12 * f18)) * f30;
        fArr[8] = (((f5 * f27) - (f6 * f25)) + (f8 * f23)) * f30;
        fArr[9] = ((((-f) * f27) + (f25 * f2)) - (f4 * f23)) * f30;
        fArr[10] = (((f13 * f21) - (f14 * f19)) + (f16 * f17)) * f30;
        fArr[11] = ((((-f9) * f21) + (f19 * f10)) - (f12 * f17)) * f30;
        fArr[12] = (((f31 * f26) + (f6 * f24)) - (f7 * f23)) * f30;
        fArr[13] = (((f * f26) - (f2 * f24)) + (f3 * f23)) * f30;
        fArr[14] = (((f32 * f20) + (f14 * f18)) - (f15 * f17)) * f30;
        fArr[15] = (((f9 * f20) - (f10 * f18)) + (f11 * f17)) * f30;
    }

    /* renamed from: reset-impl, reason: not valid java name */
    public static final void m4875resetimpl(float[] fArr) {
        if (fArr.length < 16) {
            return;
        }
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = 0.0f;
        fArr[5] = 1.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 0.0f;
        fArr[9] = 0.0f;
        fArr[10] = 1.0f;
        fArr[11] = 0.0f;
        fArr[12] = 0.0f;
        fArr[13] = 0.0f;
        fArr[14] = 0.0f;
        fArr[15] = 1.0f;
    }

    /* renamed from: setFrom-58bKbWc, reason: not valid java name */
    public static final void m4884setFrom58bKbWc(float[] fArr, float[] fArr2) {
        if (fArr.length >= 16 && fArr2.length >= 16) {
            fArr[0] = fArr2[0];
            fArr[1] = fArr2[1];
            fArr[2] = fArr2[2];
            fArr[3] = fArr2[3];
            fArr[4] = fArr2[4];
            fArr[5] = fArr2[5];
            fArr[6] = fArr2[6];
            fArr[7] = fArr2[7];
            fArr[8] = fArr2[8];
            fArr[9] = fArr2[9];
            fArr[10] = fArr2[10];
            fArr[11] = fArr2[11];
            fArr[12] = fArr2[12];
            fArr[13] = fArr2[13];
            fArr[14] = fArr2[14];
            fArr[15] = fArr2[15];
        }
    }

    /* renamed from: rotateX-impl, reason: not valid java name */
    public static final void m4878rotateXimpl(float[] fArr, float f) {
        if (fArr.length < 16) {
            return;
        }
        double d = f * 0.017453292519943295d;
        float sin = (float) Math.sin(d);
        float cos = (float) Math.cos(d);
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[5];
        float f5 = fArr[6];
        float f6 = fArr[9];
        float f7 = fArr[10];
        float f8 = fArr[13];
        float f9 = fArr[14];
        fArr[1] = (f2 * cos) - (f3 * sin);
        fArr[2] = (f2 * sin) + (f3 * cos);
        fArr[5] = (f4 * cos) - (f5 * sin);
        fArr[6] = (f4 * sin) + (f5 * cos);
        fArr[9] = (f6 * cos) - (f7 * sin);
        fArr[10] = (f6 * sin) + (f7 * cos);
        fArr[13] = (f8 * cos) - (f9 * sin);
        fArr[14] = (f8 * sin) + (f9 * cos);
    }

    /* renamed from: rotateY-impl, reason: not valid java name */
    public static final void m4879rotateYimpl(float[] fArr, float f) {
        if (fArr.length < 16) {
            return;
        }
        double d = f * 0.017453292519943295d;
        float sin = (float) Math.sin(d);
        float cos = (float) Math.cos(d);
        float f2 = fArr[0];
        float f3 = fArr[2];
        float f4 = fArr[4];
        float f5 = fArr[6];
        float f6 = fArr[8];
        float f7 = fArr[10];
        float f8 = fArr[12];
        float f9 = fArr[14];
        fArr[0] = (f2 * cos) + (f3 * sin);
        fArr[2] = ((-f2) * sin) + (f3 * cos);
        fArr[4] = (f4 * cos) + (f5 * sin);
        fArr[6] = ((-f4) * sin) + (f5 * cos);
        fArr[8] = (f6 * cos) + (f7 * sin);
        fArr[10] = ((-f6) * sin) + (f7 * cos);
        fArr[12] = (f8 * cos) + (f9 * sin);
        fArr[14] = ((-f8) * sin) + (f9 * cos);
    }

    /* renamed from: rotateZ-impl, reason: not valid java name */
    public static final void m4880rotateZimpl(float[] fArr, float f) {
        if (fArr.length < 16) {
            return;
        }
        double d = f * 0.017453292519943295d;
        float sin = (float) Math.sin(d);
        float cos = (float) Math.cos(d);
        float f2 = fArr[0];
        float f3 = fArr[4];
        float f4 = -sin;
        float f5 = fArr[1];
        float f6 = fArr[5];
        float f7 = fArr[2];
        float f8 = fArr[6];
        float f9 = fArr[3];
        float f10 = fArr[7];
        fArr[0] = (cos * f2) + (sin * f3);
        fArr[1] = (cos * f5) + (sin * f6);
        fArr[2] = (cos * f7) + (sin * f8);
        fArr[3] = (cos * f9) + (sin * f10);
        fArr[4] = (f2 * f4) + (f3 * cos);
        fArr[5] = (f5 * f4) + (f6 * cos);
        fArr[6] = (f7 * f4) + (f8 * cos);
        fArr[7] = (f4 * f9) + (cos * f10);
    }

    /* renamed from: scale-impl$default, reason: not valid java name */
    public static /* synthetic */ void m4882scaleimpl$default(float[] fArr, float f, float f2, float f3, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 1.0f;
        }
        if ((i & 2) != 0) {
            f2 = 1.0f;
        }
        if ((i & 4) != 0) {
            f3 = 1.0f;
        }
        m4881scaleimpl(fArr, f, f2, f3);
    }

    /* renamed from: scale-impl, reason: not valid java name */
    public static final void m4881scaleimpl(float[] fArr, float f, float f2, float f3) {
        if (fArr.length < 16) {
            return;
        }
        fArr[0] = fArr[0] * f;
        fArr[1] = fArr[1] * f;
        fArr[2] = fArr[2] * f;
        fArr[3] = fArr[3] * f;
        fArr[4] = fArr[4] * f2;
        fArr[5] = fArr[5] * f2;
        fArr[6] = fArr[6] * f2;
        fArr[7] = fArr[7] * f2;
        fArr[8] = fArr[8] * f3;
        fArr[9] = fArr[9] * f3;
        fArr[10] = fArr[10] * f3;
        fArr[11] = fArr[11] * f3;
    }

    /* renamed from: translate-impl$default, reason: not valid java name */
    public static /* synthetic */ void m4888translateimpl$default(float[] fArr, float f, float f2, float f3, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        if ((i & 4) != 0) {
            f3 = 0.0f;
        }
        m4887translateimpl(fArr, f, f2, f3);
    }

    /* renamed from: translate-impl, reason: not valid java name */
    public static final void m4887translateimpl(float[] fArr, float f, float f2, float f3) {
        if (fArr.length < 16) {
            return;
        }
        float f4 = (fArr[0] * f) + (fArr[4] * f2) + (fArr[8] * f3) + fArr[12];
        float f5 = (fArr[1] * f) + (fArr[5] * f2) + (fArr[9] * f3) + fArr[13];
        float f6 = (fArr[2] * f) + (fArr[6] * f2) + (fArr[10] * f3) + fArr[14];
        float f7 = (fArr[3] * f) + (fArr[7] * f2) + (fArr[11] * f3) + fArr[15];
        fArr[12] = f4;
        fArr[13] = f5;
        fArr[14] = f6;
        fArr[15] = f7;
    }

    /* renamed from: resetToPivotedTransform-impl$default, reason: not valid java name */
    public static /* synthetic */ void m4877resetToPivotedTransformimpl$default(float[] fArr, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        if ((i & 4) != 0) {
            f3 = 0.0f;
        }
        if ((i & 8) != 0) {
            f4 = 0.0f;
        }
        if ((i & 16) != 0) {
            f5 = 0.0f;
        }
        if ((i & 32) != 0) {
            f6 = 0.0f;
        }
        if ((i & 64) != 0) {
            f7 = 0.0f;
        }
        if ((i & 128) != 0) {
            f8 = 0.0f;
        }
        if ((i & 256) != 0) {
            f9 = 1.0f;
        }
        if ((i & 512) != 0) {
            f10 = 1.0f;
        }
        if ((i & 1024) != 0) {
            f11 = 1.0f;
        }
        m4876resetToPivotedTransformimpl(fArr, f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11);
    }

    /* renamed from: resetToPivotedTransform-impl, reason: not valid java name */
    public static final void m4876resetToPivotedTransformimpl(float[] fArr, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        double d = f6 * 0.017453292519943295d;
        float sin = (float) Math.sin(d);
        float cos = (float) Math.cos(d);
        float f12 = -sin;
        float f13 = (f4 * cos) - (f5 * sin);
        float f14 = (f4 * sin) + (f5 * cos);
        double d2 = f7 * 0.017453292519943295d;
        float sin2 = (float) Math.sin(d2);
        float cos2 = (float) Math.cos(d2);
        float f15 = -sin2;
        float f16 = sin * sin2;
        float f17 = sin * cos2;
        float f18 = cos * sin2;
        float f19 = cos * cos2;
        float f20 = (f3 * cos2) + (f14 * sin2);
        float f21 = ((-f3) * sin2) + (f14 * cos2);
        double d3 = f8 * 0.017453292519943295d;
        float sin3 = (float) Math.sin(d3);
        float cos3 = (float) Math.cos(d3);
        float f22 = -sin3;
        float f23 = (f22 * cos2) + (cos3 * f16);
        float f24 = cos * cos3;
        float f25 = (f22 * f15) + (cos3 * f17);
        float f26 = ((cos2 * cos3) + (f16 * sin3)) * f9;
        float f27 = sin3 * cos * f9;
        float f28 = ((cos3 * f15) + (sin3 * f17)) * f9;
        float f29 = f23 * f10;
        float f30 = f24 * f10;
        float f31 = f25 * f10;
        float f32 = f18 * f11;
        float f33 = f12 * f11;
        float f34 = f19 * f11;
        if (fArr.length < 16) {
            return;
        }
        fArr[0] = f26;
        fArr[1] = f27;
        fArr[2] = f28;
        fArr[3] = 0.0f;
        fArr[4] = f29;
        fArr[5] = f30;
        fArr[6] = f31;
        fArr[7] = 0.0f;
        fArr[8] = f32;
        fArr[9] = f33;
        fArr[10] = f34;
        fArr[11] = 0.0f;
        float f35 = -f;
        fArr[12] = ((f26 * f35) - (f29 * f2)) + f20 + f;
        fArr[13] = ((f27 * f35) - (f30 * f2)) + f13 + f2;
        fArr[14] = ((f35 * f28) - (f2 * f31)) + f21;
        fArr[15] = 1.0f;
    }
}
