package androidx.compose.ui.graphics;

import android.graphics.ColorSpace;
import android.graphics.ComposeShader;
import android.graphics.Shader;
import android.view.autofill.AutofillId;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;
import java.util.function.DoubleUnaryOperator;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class Api26Bitmap$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ android.graphics.BlendModeColorFilter m(int i, android.graphics.BlendMode blendMode) {
        return new android.graphics.BlendModeColorFilter(i, blendMode);
    }

    public static /* bridge */ /* synthetic */ android.graphics.BlendModeColorFilter m(Object obj) {
        return (android.graphics.BlendModeColorFilter) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ColorSpace.Rgb m4499m(Object obj) {
        return (ColorSpace.Rgb) obj;
    }

    public static /* synthetic */ ColorSpace.Rgb m(String str, float[] fArr, float[] fArr2, ColorSpace.Rgb.TransferParameters transferParameters) {
        return new ColorSpace.Rgb(str, fArr, fArr2, transferParameters);
    }

    public static /* synthetic */ ColorSpace.Rgb m(String str, float[] fArr, float[] fArr2, DoubleUnaryOperator doubleUnaryOperator, DoubleUnaryOperator doubleUnaryOperator2, float f, float f2) {
        return new ColorSpace.Rgb(str, fArr, fArr2, doubleUnaryOperator, doubleUnaryOperator2, f, f2);
    }

    public static /* synthetic */ ComposeShader m(Shader shader, Shader shader2, android.graphics.BlendMode blendMode) {
        return new ComposeShader(shader, shader2, blendMode);
    }

    public static /* synthetic */ ViewTranslationRequest.Builder m(AutofillId autofillId, long j) {
        return new ViewTranslationRequest.Builder(autofillId, j);
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ViewTranslationResponse m4502m(Object obj) {
        return (ViewTranslationResponse) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m4505m() {
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m4506m(Object obj) {
        return obj instanceof android.graphics.BlendModeColorFilter;
    }

    /* renamed from: m$1, reason: collision with other method in class */
    public static /* synthetic */ void m4511m$1() {
    }

    public static /* bridge */ /* synthetic */ boolean m$1(Object obj) {
        return obj instanceof ColorSpace.Rgb;
    }

    /* renamed from: m$2, reason: collision with other method in class */
    public static /* synthetic */ void m4514m$2() {
    }

    /* renamed from: m$3, reason: collision with other method in class */
    public static /* synthetic */ void m4516m$3() {
    }

    /* renamed from: m$4, reason: collision with other method in class */
    public static /* synthetic */ void m4518m$4() {
    }
}
