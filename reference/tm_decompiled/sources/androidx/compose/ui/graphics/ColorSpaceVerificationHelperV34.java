package androidx.compose.ui.graphics;

import android.graphics.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidColorSpace.android.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/graphics/ColorSpaceVerificationHelperV34;", "", "<init>", "()V", "obtainAndroidColorSpace", "Landroid/graphics/ColorSpace;", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "obtainComposeColorSpaceFromId", "id", "", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class ColorSpaceVerificationHelperV34 {
    public static final ColorSpaceVerificationHelperV34 INSTANCE = new ColorSpaceVerificationHelperV34();

    private ColorSpaceVerificationHelperV34() {
    }

    @JvmStatic
    public static final ColorSpace obtainAndroidColorSpace(androidx.compose.ui.graphics.colorspace.ColorSpace colorSpace) {
        ColorSpace.Named named;
        ColorSpace colorSpace2;
        ColorSpace.Named named2;
        ColorSpace colorSpace3;
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getBt2020Hlg())) {
            named2 = ColorSpace.Named.BT2020_HLG;
            colorSpace3 = ColorSpace.get(named2);
            return colorSpace3;
        }
        if (!Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getBt2020Pq())) {
            return null;
        }
        named = ColorSpace.Named.BT2020_PQ;
        colorSpace2 = ColorSpace.get(named);
        return colorSpace2;
    }

    @JvmStatic
    public static final androidx.compose.ui.graphics.colorspace.ColorSpace obtainComposeColorSpaceFromId(int id) {
        ColorSpace.Named named;
        int ordinal;
        ColorSpace.Named named2;
        int ordinal2;
        named = ColorSpace.Named.BT2020_HLG;
        ordinal = named.ordinal();
        if (id == ordinal) {
            return ColorSpaces.INSTANCE.getBt2020Hlg();
        }
        named2 = ColorSpace.Named.BT2020_PQ;
        ordinal2 = named2.ordinal();
        return id == ordinal2 ? ColorSpaces.INSTANCE.getBt2020Pq() : ColorSpaces.INSTANCE.getUnspecified$ui_graphics_release();
    }
}
