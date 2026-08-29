package androidx.core.graphics;

import android.graphics.Color;
import android.graphics.ColorSpace;
import kotlin.Metadata;

/* compiled from: Color.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\n\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0087\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0087\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0087\n\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0087\u0002\u001a\r\u0010\u0000\u001a\u00020\t*\u00020\tH\u0086\n\u001a\r\u0010\u0003\u001a\u00020\t*\u00020\tH\u0086\n\u001a\r\u0010\u0004\u001a\u00020\t*\u00020\tH\u0086\n\u001a\r\u0010\u0005\u001a\u00020\t*\u00020\tH\u0086\n\u001a\r\u0010\u0015\u001a\u00020\u0002*\u00020\tH\u0087\b\u001a\r\u0010\u0016\u001a\u00020\u0017*\u00020\tH\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0017H\u0087\n\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0017H\u0087\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0017H\u0087\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0017H\u0087\n\u001a\r\u0010\u0015\u001a\u00020\u0002*\u00020\u0017H\u0087\b\u001a\r\u0010\u0019\u001a\u00020\t*\u00020\u0017H\u0087\b\u001a\u0015\u0010\"\u001a\u00020\u0017*\u00020\t2\u0006\u0010\u001e\u001a\u00020#H\u0087\f\u001a\u0015\u0010\"\u001a\u00020\u0017*\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001fH\u0087\f\u001a\u0015\u0010\"\u001a\u00020\u0017*\u00020\u00172\u0006\u0010\u001e\u001a\u00020#H\u0087\f\u001a\u0015\u0010\"\u001a\u00020\u0017*\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001fH\u0087\f\u001a\u0015\u0010\"\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001e\u001a\u00020#H\u0087\f\u001a\u0015\u0010\"\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001fH\u0087\f\u001a\r\u0010\u0019\u001a\u00020\t*\u00020$H\u0087\b\"\u0016\u0010\b\u001a\u00020\t*\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u0016\u0010\f\u001a\u00020\t*\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b\"\u0016\u0010\u000e\u001a\u00020\t*\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000b\"\u0016\u0010\u0010\u001a\u00020\t*\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000b\"\u0016\u0010\u0012\u001a\u00020\u0001*\u00020\t8Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\"\u0016\u0010\b\u001a\u00020\u0001*\u00020\u00178Ç\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\u0018\"\u0016\u0010\f\u001a\u00020\u0001*\u00020\u00178Ç\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u0018\"\u0016\u0010\u000e\u001a\u00020\u0001*\u00020\u00178Ç\u0002¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0018\"\u0016\u0010\u0010\u001a\u00020\u0001*\u00020\u00178Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0018\"\u0016\u0010\u0012\u001a\u00020\u0001*\u00020\u00178Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0018\"\u0016\u0010\u001a\u001a\u00020\u001b*\u00020\u00178Ç\u0002¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001c\"\u0016\u0010\u001d\u001a\u00020\u001b*\u00020\u00178Ç\u0002¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001c\"\u0016\u0010\u001e\u001a\u00020\u001f*\u00020\u00178Ç\u0002¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006%"}, d2 = {"component1", "", "Landroid/graphics/Color;", "component2", "component3", "component4", "plus", "c", "alpha", "", "getAlpha", "(I)I", "red", "getRed", "green", "getGreen", "blue", "getBlue", "luminance", "getLuminance", "(I)F", "toColor", "toColorLong", "", "(J)F", "toColorInt", "isSrgb", "", "(J)Z", "isWideGamut", "colorSpace", "Landroid/graphics/ColorSpace;", "getColorSpace", "(J)Landroid/graphics/ColorSpace;", "convertTo", "Landroid/graphics/ColorSpace$Named;", "", "core-ktx_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ColorKt {
    public static final int component1(int i) {
        return (i >> 24) & 255;
    }

    public static final int component2(int i) {
        return (i >> 16) & 255;
    }

    public static final int component3(int i) {
        return (i >> 8) & 255;
    }

    public static final int component4(int i) {
        return i & 255;
    }

    public static final int getAlpha(int i) {
        return (i >> 24) & 255;
    }

    public static final int getBlue(int i) {
        return i & 255;
    }

    public static final int getGreen(int i) {
        return (i >> 8) & 255;
    }

    public static final int getRed(int i) {
        return (i >> 16) & 255;
    }

    public static final float component1(Color color) {
        float component;
        component = color.getComponent(0);
        return component;
    }

    public static final float component2(Color color) {
        float component;
        component = color.getComponent(1);
        return component;
    }

    public static final float component3(Color color) {
        float component;
        component = color.getComponent(2);
        return component;
    }

    public static final float component4(Color color) {
        float component;
        component = color.getComponent(3);
        return component;
    }

    public static final Color plus(Color color, Color color2) {
        return ColorUtils.compositeColors(color2, color);
    }

    public static final float getLuminance(int i) {
        float luminance;
        luminance = Color.luminance(i);
        return luminance;
    }

    public static final Color toColor(int i) {
        Color valueOf;
        valueOf = Color.valueOf(i);
        return valueOf;
    }

    public static final long toColorLong(int i) {
        long pack;
        pack = Color.pack(i);
        return pack;
    }

    public static final float component1(long j) {
        float red;
        red = Color.red(j);
        return red;
    }

    public static final float component2(long j) {
        float green;
        green = Color.green(j);
        return green;
    }

    public static final float component3(long j) {
        float blue;
        blue = Color.blue(j);
        return blue;
    }

    public static final float component4(long j) {
        float alpha;
        alpha = Color.alpha(j);
        return alpha;
    }

    public static final float getAlpha(long j) {
        float alpha;
        alpha = Color.alpha(j);
        return alpha;
    }

    public static final float getRed(long j) {
        float red;
        red = Color.red(j);
        return red;
    }

    public static final float getGreen(long j) {
        float green;
        green = Color.green(j);
        return green;
    }

    public static final float getBlue(long j) {
        float blue;
        blue = Color.blue(j);
        return blue;
    }

    public static final float getLuminance(long j) {
        float luminance;
        luminance = Color.luminance(j);
        return luminance;
    }

    public static final Color toColor(long j) {
        Color valueOf;
        valueOf = Color.valueOf(j);
        return valueOf;
    }

    public static final int toColorInt(long j) {
        int argb;
        argb = Color.toArgb(j);
        return argb;
    }

    public static final boolean isSrgb(long j) {
        boolean isSrgb;
        isSrgb = Color.isSrgb(j);
        return isSrgb;
    }

    public static final boolean isWideGamut(long j) {
        boolean isWideGamut;
        isWideGamut = Color.isWideGamut(j);
        return isWideGamut;
    }

    public static final ColorSpace getColorSpace(long j) {
        ColorSpace colorSpace;
        colorSpace = Color.colorSpace(j);
        return colorSpace;
    }

    public static final long convertTo(int i, ColorSpace.Named named) {
        ColorSpace colorSpace;
        long convert;
        colorSpace = ColorSpace.get(named);
        convert = Color.convert(i, colorSpace);
        return convert;
    }

    public static final long convertTo(int i, ColorSpace colorSpace) {
        long convert;
        convert = Color.convert(i, colorSpace);
        return convert;
    }

    public static final long convertTo(long j, ColorSpace.Named named) {
        ColorSpace colorSpace;
        long convert;
        colorSpace = ColorSpace.get(named);
        convert = Color.convert(j, colorSpace);
        return convert;
    }

    public static final long convertTo(long j, ColorSpace colorSpace) {
        long convert;
        convert = Color.convert(j, colorSpace);
        return convert;
    }

    public static final Color convertTo(Color color, ColorSpace.Named named) {
        ColorSpace colorSpace;
        Color convert;
        colorSpace = ColorSpace.get(named);
        convert = color.convert(colorSpace);
        return convert;
    }

    public static final Color convertTo(Color color, ColorSpace colorSpace) {
        Color convert;
        convert = color.convert(colorSpace);
        return convert;
    }

    public static final int toColorInt(String str) {
        return Color.parseColor(str);
    }
}
