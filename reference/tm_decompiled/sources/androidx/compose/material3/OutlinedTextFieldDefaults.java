package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.internal.TextFieldType;
import androidx.compose.material3.tokens.OutlinedTextFieldTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.PropertyReference0Impl;

/* compiled from: TextFieldDefaults.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b)\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\\\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00132\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\"\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$JR\u0010%\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010 \u001a\u00020\u00132\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\"\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\u009c\u0002\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u00180,¢\u0006\u0002\b-2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u001a2\u0006\u0010/\u001a\u0002002\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00103\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00104\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00105\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00106\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00107\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\b\b\u0002\u0010 \u001a\u00020\u00132\b\b\u0002\u00108\u001a\u0002092\u0013\b\u0002\u0010:\u001a\r\u0012\u0004\u0012\u00020\u00180,¢\u0006\u0002\b-H\u0007¢\u0006\u0002\u0010;J\r\u0010 \u001a\u00020\u0013H\u0007¢\u0006\u0002\u0010<JÂ\u0003\u0010 \u001a\u00020\u00132\b\b\u0002\u0010=\u001a\u00020>2\b\b\u0002\u0010?\u001a\u00020>2\b\b\u0002\u0010@\u001a\u00020>2\b\b\u0002\u0010A\u001a\u00020>2\b\b\u0002\u0010B\u001a\u00020>2\b\b\u0002\u0010C\u001a\u00020>2\b\b\u0002\u0010D\u001a\u00020>2\b\b\u0002\u0010E\u001a\u00020>2\b\b\u0002\u0010F\u001a\u00020>2\b\b\u0002\u0010G\u001a\u00020>2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\b\b\u0002\u0010J\u001a\u00020>2\b\b\u0002\u0010K\u001a\u00020>2\b\b\u0002\u0010L\u001a\u00020>2\b\b\u0002\u0010M\u001a\u00020>2\b\b\u0002\u0010N\u001a\u00020>2\b\b\u0002\u0010O\u001a\u00020>2\b\b\u0002\u0010P\u001a\u00020>2\b\b\u0002\u0010Q\u001a\u00020>2\b\b\u0002\u0010R\u001a\u00020>2\b\b\u0002\u0010S\u001a\u00020>2\b\b\u0002\u0010T\u001a\u00020>2\b\b\u0002\u0010U\u001a\u00020>2\b\b\u0002\u0010V\u001a\u00020>2\b\b\u0002\u0010W\u001a\u00020>2\b\b\u0002\u0010X\u001a\u00020>2\b\b\u0002\u0010Y\u001a\u00020>2\b\b\u0002\u0010Z\u001a\u00020>2\b\b\u0002\u0010[\u001a\u00020>2\b\b\u0002\u0010\\\u001a\u00020>2\b\b\u0002\u0010]\u001a\u00020>2\b\b\u0002\u0010^\u001a\u00020>2\b\b\u0002\u0010_\u001a\u00020>2\b\b\u0002\u0010`\u001a\u00020>2\b\b\u0002\u0010a\u001a\u00020>2\b\b\u0002\u0010b\u001a\u00020>2\b\b\u0002\u0010c\u001a\u00020>2\b\b\u0002\u0010d\u001a\u00020>2\b\b\u0002\u0010e\u001a\u00020>2\b\b\u0002\u0010f\u001a\u00020>2\b\b\u0002\u0010g\u001a\u00020>2\b\b\u0002\u0010h\u001a\u00020>2\b\b\u0002\u0010i\u001a\u00020>H\u0007ø\u0001\u0000¢\u0006\u0004\bj\u0010kJ8\u00108\u001a\u0002092\b\b\u0002\u0010l\u001a\u00020\u00042\b\b\u0002\u0010m\u001a\u00020\u00042\b\b\u0002\u0010n\u001a\u00020\u00042\b\b\u0002\u0010o\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\bp\u0010qR\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u0019\u0010\f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u0011\u0010\u000e\u001a\u00020\u000f8G¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u00020\u0013*\u00020\u00148AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006r"}, d2 = {"Landroidx/compose/material3/OutlinedTextFieldDefaults;", "", "()V", "FocusedBorderThickness", "Landroidx/compose/ui/unit/Dp;", "getFocusedBorderThickness-D9Ej5fM", "()F", "F", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "defaultOutlinedTextFieldColors", "Landroidx/compose/material3/TextFieldColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultOutlinedTextFieldColors", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", TextFieldImplKt.ContainerId, "", "enabled", "", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "modifier", "Landroidx/compose/ui/Modifier;", "colors", "focusedBorderThickness", "unfocusedBorderThickness", "Container-4EFweAY", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "ContainerBox", "ContainerBox-nbWgWpA", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "DecorationBox", "value", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "singleLine", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "container", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "focusedTextColor", "Landroidx/compose/ui/graphics/Color;", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedSupportingTextColor", "unfocusedSupportingTextColor", "disabledSupportingTextColor", "errorSupportingTextColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "colors-0hiis_0", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIIII)Landroidx/compose/material3/TextFieldColors;", "start", "top", "end", "bottom", "contentPadding-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class OutlinedTextFieldDefaults {
    public static final int $stable = 0;
    public static final OutlinedTextFieldDefaults INSTANCE = new OutlinedTextFieldDefaults();
    private static final float MinHeight = Dp.m7399constructorimpl(56);
    private static final float MinWidth = Dp.m7399constructorimpl(280);
    private static final float UnfocusedBorderThickness = Dp.m7399constructorimpl(1);
    private static final float FocusedBorderThickness = Dp.m7399constructorimpl(2);

    private OutlinedTextFieldDefaults() {
    }

    public final Shape getShape(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -1066756961, "C729@37132L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1066756961, i, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.<get-shape> (TextFieldDefaults.kt:729)");
        }
        Shape value = ShapesKt.getValue(OutlinedTextFieldTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return value;
    }

    /* renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m2502getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m2503getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m2504getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m2501getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x019a  */
    /* renamed from: Container-4EFweAY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m2497Container4EFweAY(final boolean z, final boolean z2, final InteractionSource interactionSource, Modifier modifier, TextFieldColors textFieldColors, Shape shape, float f, float f2, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        TextFieldColors textFieldColors2;
        Shape shape2;
        float f3;
        float f4;
        TextFieldColors textFieldColors3;
        Shape shape3;
        float f5;
        Modifier modifier3;
        int i4;
        TextFieldColors textFieldColors4;
        Shape shape4;
        float f6;
        float f7;
        Composer composer2;
        final float f8;
        final float f9;
        final Shape shape5;
        final TextFieldColors textFieldColors5;
        final Modifier modifier4;
        int i5;
        int i6;
        int i7;
        int i8;
        Composer startRestartGroup = composer.startRestartGroup(1035477640);
        ComposerKt.sourceInformation(startRestartGroup, "C(Container)P(1,4,3,5!1,6,2:c#ui.unit.Dp,7:c#ui.unit.Dp)772@39024L8,773@39083L5,777@39264L25,779@39335L222,788@39599L198,792@39806L153:TextFieldDefaults.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= startRestartGroup.changed(z2) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= startRestartGroup.changed(interactionSource) ? 256 : 128;
        }
        int i9 = i2 & 8;
        if (i9 != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 2048 : 1024;
            if ((i & 24576) != 0) {
                if ((i2 & 16) == 0) {
                    textFieldColors2 = textFieldColors;
                    if (startRestartGroup.changed(textFieldColors2)) {
                        i8 = 16384;
                        i3 |= i8;
                    }
                } else {
                    textFieldColors2 = textFieldColors;
                }
                i8 = 8192;
                i3 |= i8;
            } else {
                textFieldColors2 = textFieldColors;
            }
            if ((196608 & i) != 0) {
                if ((i2 & 32) == 0) {
                    shape2 = shape;
                    if (startRestartGroup.changed(shape2)) {
                        i7 = 131072;
                        i3 |= i7;
                    }
                } else {
                    shape2 = shape;
                }
                i7 = 65536;
                i3 |= i7;
            } else {
                shape2 = shape;
            }
            if ((1572864 & i) != 0) {
                if ((i2 & 64) == 0) {
                    f3 = f;
                    if (startRestartGroup.changed(f3)) {
                        i6 = 1048576;
                        i3 |= i6;
                    }
                } else {
                    f3 = f;
                }
                i6 = 524288;
                i3 |= i6;
            } else {
                f3 = f;
            }
            if ((12582912 & i) != 0) {
                if ((i2 & 128) == 0) {
                    f4 = f2;
                    if (startRestartGroup.changed(f4)) {
                        i5 = 8388608;
                        i3 |= i5;
                    }
                } else {
                    f4 = f2;
                }
                i5 = 4194304;
                i3 |= i5;
            } else {
                f4 = f2;
            }
            if ((i2 & 256) == 0) {
                i3 |= 100663296;
            } else if ((i & 100663296) == 0) {
                i3 |= startRestartGroup.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
            }
            if ((38347923 & i3) == 38347922 || !startRestartGroup.getSkipping()) {
                startRestartGroup.startDefaults();
                if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                    Modifier.Companion companion = i9 == 0 ? Modifier.INSTANCE : modifier2;
                    if ((i2 & 16) == 0) {
                        textFieldColors3 = colors(startRestartGroup, (i3 >> 24) & 14);
                        i3 &= -57345;
                    } else {
                        textFieldColors3 = textFieldColors2;
                    }
                    if ((i2 & 32) == 0) {
                        shape3 = INSTANCE.getShape(startRestartGroup, 6);
                        i3 &= -458753;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i2 & 64) == 0) {
                        f5 = FocusedBorderThickness;
                        i3 &= -3670017;
                    } else {
                        f5 = f3;
                    }
                    if ((i2 & 128) == 0) {
                        shape4 = shape3;
                        f7 = UnfocusedBorderThickness;
                        modifier3 = companion;
                        i4 = i3 & (-29360129);
                        textFieldColors4 = textFieldColors3;
                        f6 = f5;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1035477640, i4, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.Container (TextFieldDefaults.kt:776)");
                        }
                        int i10 = i4 >> 6;
                        boolean booleanValue = FocusInteractionKt.collectIsFocusedAsState(interactionSource, startRestartGroup, i10 & 14).getValue().booleanValue();
                        State<BorderStroke> m3167animateBorderStrokeAsStateNuRrP5Q = TextFieldImplKt.m3167animateBorderStrokeAsStateNuRrP5Q(z, z2, booleanValue, textFieldColors4, f6, f7, startRestartGroup, (i10 & 458752) | ((i4 >> 3) & 7168) | (i4 & 126) | (57344 & i10));
                        Modifier modifier5 = modifier3;
                        Shape shape6 = shape4;
                        final State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(textFieldColors4.m2766containerColorXeAY9LY$material3_release(z, z2, booleanValue), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, startRestartGroup, 48, 12);
                        composer2 = startRestartGroup;
                        BoxKt.Box(TextFieldImplKt.textFieldBackground(BorderKt.border(modifier5, m3167animateBorderStrokeAsStateNuRrP5Q.getValue(), shape6), new TextFieldDefaults$sam$androidx_compose_ui_graphics_ColorProducer$0(new PropertyReference0Impl(m158animateColorAsStateeuL9pac) { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$Container$1
                            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                            public Object get() {
                                return ((State) this.receiver).getValue();
                            }
                        }), shape6), composer2, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        f8 = f6;
                        f9 = f7;
                        shape5 = shape6;
                        textFieldColors5 = textFieldColors4;
                        modifier4 = modifier5;
                    } else {
                        modifier3 = companion;
                        i4 = i3;
                        textFieldColors4 = textFieldColors3;
                        shape4 = shape3;
                        f6 = f5;
                    }
                } else {
                    startRestartGroup.skipToGroupEnd();
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        i3 &= -29360129;
                    }
                    i4 = i3;
                    textFieldColors4 = textFieldColors2;
                    f6 = f3;
                    shape4 = shape2;
                    modifier3 = modifier2;
                }
                f7 = f4;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                int i102 = i4 >> 6;
                boolean booleanValue2 = FocusInteractionKt.collectIsFocusedAsState(interactionSource, startRestartGroup, i102 & 14).getValue().booleanValue();
                State<BorderStroke> m3167animateBorderStrokeAsStateNuRrP5Q2 = TextFieldImplKt.m3167animateBorderStrokeAsStateNuRrP5Q(z, z2, booleanValue2, textFieldColors4, f6, f7, startRestartGroup, (i102 & 458752) | ((i4 >> 3) & 7168) | (i4 & 126) | (57344 & i102));
                Modifier modifier52 = modifier3;
                Shape shape62 = shape4;
                final Object m158animateColorAsStateeuL9pac2 = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(textFieldColors4.m2766containerColorXeAY9LY$material3_release(z, z2, booleanValue2), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, startRestartGroup, 48, 12);
                composer2 = startRestartGroup;
                BoxKt.Box(TextFieldImplKt.textFieldBackground(BorderKt.border(modifier52, m3167animateBorderStrokeAsStateNuRrP5Q2.getValue(), shape62), new TextFieldDefaults$sam$androidx_compose_ui_graphics_ColorProducer$0(new PropertyReference0Impl(m158animateColorAsStateeuL9pac2) { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$Container$1
                    @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                    public Object get() {
                        return ((State) this.receiver).getValue();
                    }
                }), shape62), composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                }
                f8 = f6;
                f9 = f7;
                shape5 = shape62;
                textFieldColors5 = textFieldColors4;
                modifier4 = modifier52;
            } else {
                startRestartGroup.skipToGroupEnd();
                float f10 = f3;
                composer2 = startRestartGroup;
                f8 = f10;
                modifier4 = modifier2;
                textFieldColors5 = textFieldColors2;
                shape5 = shape2;
                f9 = f4;
            }
            ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$Container$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i11) {
                        OutlinedTextFieldDefaults.this.m2497Container4EFweAY(z, z2, interactionSource, modifier4, textFieldColors5, shape5, f8, f9, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
                return;
            }
            return;
        }
        modifier2 = modifier;
        if ((i & 24576) != 0) {
        }
        if ((196608 & i) != 0) {
        }
        if ((1572864 & i) != 0) {
        }
        if ((12582912 & i) != 0) {
        }
        if ((i2 & 256) == 0) {
        }
        if ((38347923 & i3) == 38347922) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i9 == 0) {
        }
        if ((i2 & 16) == 0) {
        }
        if ((i2 & 32) == 0) {
        }
        if ((i2 & 64) == 0) {
        }
        if ((i2 & 128) == 0) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:82:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x03ee  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void DecorationBox(final String str, final Function2<? super Composer, ? super Integer, Unit> function2, final boolean z, final boolean z2, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean z3, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, Function2<? super Composer, ? super Integer, Unit> function28, TextFieldColors textFieldColors, PaddingValues paddingValues, Function2<? super Composer, ? super Integer, Unit> function29, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        int i5;
        boolean z4;
        int i6;
        Function2<? super Composer, ? super Integer, Unit> function210;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        Function2<? super Composer, ? super Integer, Unit> function211;
        final TextFieldColors textFieldColors2;
        PaddingValues paddingValues2;
        boolean z5;
        PaddingValues paddingValues3;
        Function2<? super Composer, ? super Integer, Unit> function212;
        ComposableLambda composableLambda;
        Composer composer2;
        Function2<? super Composer, ? super Integer, Unit> function213;
        Function2<? super Composer, ? super Integer, Unit> function214;
        Function2<? super Composer, ? super Integer, Unit> function215;
        int i20;
        Function2<? super Composer, ? super Integer, Unit> function216;
        final TextFieldColors textFieldColors3;
        Function2<? super Composer, ? super Integer, Unit> function217;
        Function2<? super Composer, ? super Integer, Unit> function218;
        PaddingValues paddingValues4;
        Composer composer3;
        final Function2<? super Composer, ? super Integer, Unit> function219;
        final boolean z6;
        final Function2<? super Composer, ? super Integer, Unit> function220;
        final Function2<? super Composer, ? super Integer, Unit> function221;
        final Function2<? super Composer, ? super Integer, Unit> function222;
        final Function2<? super Composer, ? super Integer, Unit> function223;
        final Function2<? super Composer, ? super Integer, Unit> function224;
        final PaddingValues paddingValues5;
        final Function2<? super Composer, ? super Integer, Unit> function225;
        final Function2<? super Composer, ? super Integer, Unit> function226;
        ScopeUpdateScope endRestartGroup;
        Composer startRestartGroup = composer.startRestartGroup(-350442135);
        ComposerKt.sourceInformation(startRestartGroup, "C(DecorationBox)P(15,4,3,11,16,5,6,7,9,8,14,10,12,13!1,2)870@44562L8,872@44674L408,885@45099L709:TextFieldDefaults.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = (startRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        if ((i3 & 2) != 0) {
            i4 |= 48;
        } else if ((i & 48) == 0) {
            i4 |= startRestartGroup.changedInstance(function2) ? 32 : 16;
            if ((i3 & 4) == 0) {
                i4 |= 384;
            } else if ((i & 384) == 0) {
                i4 |= startRestartGroup.changed(z) ? 256 : 128;
            }
            if ((i3 & 8) == 0) {
                i4 |= 3072;
            } else if ((i & 3072) == 0) {
                i4 |= startRestartGroup.changed(z2) ? 2048 : 1024;
                int i21 = 8192;
                if ((i3 & 16) != 0) {
                    i4 |= 24576;
                } else if ((i & 24576) == 0) {
                    i4 |= startRestartGroup.changed(visualTransformation) ? 16384 : 8192;
                    if ((i3 & 32) == 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        i4 |= startRestartGroup.changed(interactionSource) ? 131072 : 65536;
                    }
                    i5 = i3 & 64;
                    if (i5 == 0) {
                        i4 |= 1572864;
                        z4 = z3;
                    } else {
                        z4 = z3;
                        if ((i & 1572864) == 0) {
                            i4 |= startRestartGroup.changed(z4) ? 1048576 : 524288;
                        }
                    }
                    i6 = i3 & 128;
                    if (i6 == 0) {
                        i4 |= 12582912;
                        function210 = function22;
                    } else {
                        function210 = function22;
                        if ((i & 12582912) == 0) {
                            i4 |= startRestartGroup.changedInstance(function210) ? 8388608 : 4194304;
                        }
                    }
                    i7 = i3 & 256;
                    if (i7 == 0) {
                        i4 |= 100663296;
                    } else if ((i & 100663296) == 0) {
                        i4 |= startRestartGroup.changedInstance(function23) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                    }
                    i8 = i3 & 512;
                    if (i8 == 0) {
                        i4 |= 805306368;
                    } else if ((i & 805306368) == 0) {
                        i9 = i8;
                        i4 |= startRestartGroup.changedInstance(function24) ? 536870912 : 268435456;
                        i10 = i3 & 1024;
                        if (i10 != 0) {
                            i12 = i2 | 6;
                            i11 = i10;
                        } else if ((i2 & 6) == 0) {
                            i11 = i10;
                            i12 = i2 | (startRestartGroup.changedInstance(function25) ? 4 : 2);
                        } else {
                            i11 = i10;
                            i12 = i2;
                        }
                        i13 = i3 & 2048;
                        if (i13 != 0) {
                            i12 |= 48;
                            i14 = i13;
                        } else if ((i2 & 48) == 0) {
                            i14 = i13;
                            i12 |= startRestartGroup.changedInstance(function26) ? 32 : 16;
                        } else {
                            i14 = i13;
                        }
                        int i22 = i12;
                        i15 = i3 & 4096;
                        if (i15 != 0) {
                            i16 = i22 | 384;
                        } else if ((i2 & 384) == 0) {
                            i16 = i22 | (startRestartGroup.changedInstance(function27) ? 256 : 128);
                        } else {
                            i16 = i22;
                        }
                        i17 = i3 & 8192;
                        if (i17 != 0) {
                            i18 = i16 | 3072;
                        } else {
                            i18 = i16;
                            if ((i2 & 3072) == 0) {
                                i18 |= startRestartGroup.changedInstance(function28) ? 2048 : 1024;
                                if ((i2 & 24576) == 0) {
                                    if ((i3 & 16384) == 0 && startRestartGroup.changed(textFieldColors)) {
                                        i21 = 16384;
                                    }
                                    i18 |= i21;
                                }
                                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                    i18 |= ((i3 & 32768) == 0 && startRestartGroup.changed(paddingValues)) ? 131072 : 65536;
                                }
                                i19 = i3 & 65536;
                                if (i19 == 0) {
                                    i18 |= 1572864;
                                } else if ((i2 & 1572864) == 0) {
                                    i18 |= startRestartGroup.changedInstance(function29) ? 1048576 : 524288;
                                }
                                if ((i3 & 131072) == 0) {
                                    i18 |= 12582912;
                                } else if ((i2 & 12582912) == 0) {
                                    i18 |= startRestartGroup.changed(this) ? 8388608 : 4194304;
                                }
                                if ((i4 & 306783379) == 306783378 || (i18 & 4793491) != 4793490 || !startRestartGroup.getSkipping()) {
                                    startRestartGroup.startDefaults();
                                    if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                        final boolean z7 = i5 == 0 ? false : z4;
                                        if (i6 != 0) {
                                            function210 = null;
                                        }
                                        Function2<? super Composer, ? super Integer, Unit> function227 = i7 == 0 ? null : function23;
                                        Function2<? super Composer, ? super Integer, Unit> function228 = i9 == 0 ? null : function24;
                                        Function2<? super Composer, ? super Integer, Unit> function229 = i11 == 0 ? null : function25;
                                        function211 = i14 == 0 ? null : function26;
                                        Function2<? super Composer, ? super Integer, Unit> function230 = i15 == 0 ? null : function27;
                                        Function2<? super Composer, ? super Integer, Unit> function231 = i17 == 0 ? function28 : null;
                                        if ((i3 & 16384) == 0) {
                                            textFieldColors2 = colors(startRestartGroup, (i18 >> 21) & 14);
                                            i18 &= -57345;
                                        } else {
                                            textFieldColors2 = textFieldColors;
                                        }
                                        if ((i3 & 32768) == 0) {
                                            paddingValues2 = m2496contentPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                                            i18 &= -458753;
                                        } else {
                                            paddingValues2 = paddingValues;
                                        }
                                        if (i19 == 0) {
                                            paddingValues3 = paddingValues2;
                                            z5 = z7;
                                            function212 = function231;
                                            composableLambda = ComposableLambdaKt.rememberComposableLambda(-1448570018, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$DecorationBox$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                                    invoke(composer4, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer4, int i23) {
                                                    ComposerKt.sourceInformation(composer4, "C879@44918L5,873@44688L384:TextFieldDefaults.kt#uh7d8r");
                                                    if ((i23 & 3) != 2 || !composer4.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-1448570018, i23, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:873)");
                                                        }
                                                        OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z, z7, interactionSource, Modifier.INSTANCE, textFieldColors2, OutlinedTextFieldDefaults.INSTANCE.getShape(composer4, 6), OutlinedTextFieldDefaults.INSTANCE.m2501getFocusedBorderThicknessD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2504getUnfocusedBorderThicknessD9Ej5fM(), composer4, 114822144, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer4.skipToGroupEnd();
                                                }
                                            }, startRestartGroup, 54);
                                        } else {
                                            z5 = z7;
                                            paddingValues3 = paddingValues2;
                                            function212 = function231;
                                            composableLambda = function29;
                                        }
                                        composer2 = startRestartGroup;
                                        function213 = function228;
                                        function214 = function210;
                                        function215 = function230;
                                        i20 = i18;
                                        function216 = function212;
                                        textFieldColors3 = textFieldColors2;
                                        function217 = function227;
                                        function218 = function229;
                                        paddingValues4 = paddingValues3;
                                    } else {
                                        startRestartGroup.skipToGroupEnd();
                                        if ((i3 & 16384) != 0) {
                                            i18 &= -57345;
                                        }
                                        if ((32768 & i3) != 0) {
                                            i18 &= -458753;
                                        }
                                        function217 = function23;
                                        function213 = function24;
                                        function211 = function26;
                                        function215 = function27;
                                        function216 = function28;
                                        textFieldColors3 = textFieldColors;
                                        paddingValues4 = paddingValues;
                                        composableLambda = function29;
                                        composer2 = startRestartGroup;
                                        z5 = z4;
                                        function214 = function210;
                                        i20 = i18;
                                        function218 = function25;
                                    }
                                    composer2.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-350442135, i4, i20, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox (TextFieldDefaults.kt:884)");
                                    }
                                    boolean z8 = z5;
                                    Function2<? super Composer, ? super Integer, Unit> function232 = composableLambda;
                                    int i23 = i4 << 3;
                                    int i24 = i4 >> 3;
                                    int i25 = (i24 & 7168) | (i23 & 896) | (i23 & 112) | 6;
                                    int i26 = i4 >> 9;
                                    int i27 = i20 << 21;
                                    int i28 = ((i20 >> 9) & 14) | ((i4 >> 6) & 112) | (i4 & 896) | (i26 & 7168) | (57344 & i24) | (458752 & i20) | ((i20 << 6) & 3670016) | ((i20 << 3) & 29360128);
                                    Function2<? super Composer, ? super Integer, Unit> function233 = function211;
                                    Composer composer4 = composer2;
                                    TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, str, function2, visualTransformation, function214, function217, function213, function218, function233, function215, function216, z2, z, z8, interactionSource, paddingValues4, textFieldColors3, function232, composer4, i25 | (i26 & 57344) | (i26 & 458752) | (i26 & 3670016) | (i27 & 29360128) | (i27 & 234881024) | (i27 & 1879048192), i28, 0);
                                    composer3 = composer4;
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    function219 = function233;
                                    z6 = z8;
                                    function220 = function213;
                                    function221 = function218;
                                    function222 = function215;
                                    function223 = function232;
                                    function224 = function214;
                                    paddingValues5 = paddingValues4;
                                    function225 = function216;
                                    function226 = function217;
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    function226 = function23;
                                    function221 = function25;
                                    function219 = function26;
                                    function222 = function27;
                                    function225 = function28;
                                    textFieldColors3 = textFieldColors;
                                    paddingValues5 = paddingValues;
                                    function223 = function29;
                                    composer3 = startRestartGroup;
                                    z6 = z4;
                                    function224 = function210;
                                    function220 = function24;
                                }
                                endRestartGroup = composer3.endRestartGroup();
                                if (endRestartGroup == null) {
                                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$DecorationBox$2
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                            invoke(composer5, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer5, int i29) {
                                            OutlinedTextFieldDefaults.this.DecorationBox(str, function2, z, z2, visualTransformation, interactionSource, z6, function224, function226, function220, function221, function219, function222, function225, textFieldColors3, paddingValues5, function223, composer5, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                        }
                        if ((i2 & 24576) == 0) {
                        }
                        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        }
                        i19 = i3 & 65536;
                        if (i19 == 0) {
                        }
                        if ((i3 & 131072) == 0) {
                        }
                        if ((i4 & 306783379) == 306783378) {
                        }
                        startRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                        }
                        if (i5 == 0) {
                        }
                        if (i6 != 0) {
                        }
                        if (i7 == 0) {
                        }
                        if (i9 == 0) {
                        }
                        if (i11 == 0) {
                        }
                        if (i14 == 0) {
                        }
                        if (i15 == 0) {
                        }
                        if (i17 == 0) {
                        }
                        if ((i3 & 16384) == 0) {
                        }
                        if ((i3 & 32768) == 0) {
                        }
                        if (i19 == 0) {
                        }
                        composer2 = startRestartGroup;
                        function213 = function228;
                        function214 = function210;
                        function215 = function230;
                        i20 = i18;
                        function216 = function212;
                        textFieldColors3 = textFieldColors2;
                        function217 = function227;
                        function218 = function229;
                        paddingValues4 = paddingValues3;
                        composer2.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        boolean z82 = z5;
                        Function2<? super Composer, ? super Integer, Unit> function2322 = composableLambda;
                        int i232 = i4 << 3;
                        int i242 = i4 >> 3;
                        int i252 = (i242 & 7168) | (i232 & 896) | (i232 & 112) | 6;
                        int i262 = i4 >> 9;
                        int i272 = i20 << 21;
                        int i282 = ((i20 >> 9) & 14) | ((i4 >> 6) & 112) | (i4 & 896) | (i262 & 7168) | (57344 & i242) | (458752 & i20) | ((i20 << 6) & 3670016) | ((i20 << 3) & 29360128);
                        Function2<? super Composer, ? super Integer, Unit> function2332 = function211;
                        Composer composer42 = composer2;
                        TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, str, function2, visualTransformation, function214, function217, function213, function218, function2332, function215, function216, z2, z, z82, interactionSource, paddingValues4, textFieldColors3, function2322, composer42, i252 | (i262 & 57344) | (i262 & 458752) | (i262 & 3670016) | (i272 & 29360128) | (i272 & 234881024) | (i272 & 1879048192), i282, 0);
                        composer3 = composer42;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        function219 = function2332;
                        z6 = z82;
                        function220 = function213;
                        function221 = function218;
                        function222 = function215;
                        function223 = function2322;
                        function224 = function214;
                        paddingValues5 = paddingValues4;
                        function225 = function216;
                        function226 = function217;
                        endRestartGroup = composer3.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i9 = i8;
                    i10 = i3 & 1024;
                    if (i10 != 0) {
                    }
                    i13 = i3 & 2048;
                    if (i13 != 0) {
                    }
                    int i222 = i12;
                    i15 = i3 & 4096;
                    if (i15 != 0) {
                    }
                    i17 = i3 & 8192;
                    if (i17 != 0) {
                    }
                    if ((i2 & 24576) == 0) {
                    }
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    }
                    i19 = i3 & 65536;
                    if (i19 == 0) {
                    }
                    if ((i3 & 131072) == 0) {
                    }
                    if ((i4 & 306783379) == 306783378) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                    }
                    if (i5 == 0) {
                    }
                    if (i6 != 0) {
                    }
                    if (i7 == 0) {
                    }
                    if (i9 == 0) {
                    }
                    if (i11 == 0) {
                    }
                    if (i14 == 0) {
                    }
                    if (i15 == 0) {
                    }
                    if (i17 == 0) {
                    }
                    if ((i3 & 16384) == 0) {
                    }
                    if ((i3 & 32768) == 0) {
                    }
                    if (i19 == 0) {
                    }
                    composer2 = startRestartGroup;
                    function213 = function228;
                    function214 = function210;
                    function215 = function230;
                    i20 = i18;
                    function216 = function212;
                    textFieldColors3 = textFieldColors2;
                    function217 = function227;
                    function218 = function229;
                    paddingValues4 = paddingValues3;
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    boolean z822 = z5;
                    Function2<? super Composer, ? super Integer, Unit> function23222 = composableLambda;
                    int i2322 = i4 << 3;
                    int i2422 = i4 >> 3;
                    int i2522 = (i2422 & 7168) | (i2322 & 896) | (i2322 & 112) | 6;
                    int i2622 = i4 >> 9;
                    int i2722 = i20 << 21;
                    int i2822 = ((i20 >> 9) & 14) | ((i4 >> 6) & 112) | (i4 & 896) | (i2622 & 7168) | (57344 & i2422) | (458752 & i20) | ((i20 << 6) & 3670016) | ((i20 << 3) & 29360128);
                    Function2<? super Composer, ? super Integer, Unit> function23322 = function211;
                    Composer composer422 = composer2;
                    TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, str, function2, visualTransformation, function214, function217, function213, function218, function23322, function215, function216, z2, z, z822, interactionSource, paddingValues4, textFieldColors3, function23222, composer422, i2522 | (i2622 & 57344) | (i2622 & 458752) | (i2622 & 3670016) | (i2722 & 29360128) | (i2722 & 234881024) | (i2722 & 1879048192), i2822, 0);
                    composer3 = composer422;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    function219 = function23322;
                    z6 = z822;
                    function220 = function213;
                    function221 = function218;
                    function222 = function215;
                    function223 = function23222;
                    function224 = function214;
                    paddingValues5 = paddingValues4;
                    function225 = function216;
                    function226 = function217;
                    endRestartGroup = composer3.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                if ((i3 & 32) == 0) {
                }
                i5 = i3 & 64;
                if (i5 == 0) {
                }
                i6 = i3 & 128;
                if (i6 == 0) {
                }
                i7 = i3 & 256;
                if (i7 == 0) {
                }
                i8 = i3 & 512;
                if (i8 == 0) {
                }
                i9 = i8;
                i10 = i3 & 1024;
                if (i10 != 0) {
                }
                i13 = i3 & 2048;
                if (i13 != 0) {
                }
                int i2222 = i12;
                i15 = i3 & 4096;
                if (i15 != 0) {
                }
                i17 = i3 & 8192;
                if (i17 != 0) {
                }
                if ((i2 & 24576) == 0) {
                }
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                }
                i19 = i3 & 65536;
                if (i19 == 0) {
                }
                if ((i3 & 131072) == 0) {
                }
                if ((i4 & 306783379) == 306783378) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i5 == 0) {
                }
                if (i6 != 0) {
                }
                if (i7 == 0) {
                }
                if (i9 == 0) {
                }
                if (i11 == 0) {
                }
                if (i14 == 0) {
                }
                if (i15 == 0) {
                }
                if (i17 == 0) {
                }
                if ((i3 & 16384) == 0) {
                }
                if ((i3 & 32768) == 0) {
                }
                if (i19 == 0) {
                }
                composer2 = startRestartGroup;
                function213 = function228;
                function214 = function210;
                function215 = function230;
                i20 = i18;
                function216 = function212;
                textFieldColors3 = textFieldColors2;
                function217 = function227;
                function218 = function229;
                paddingValues4 = paddingValues3;
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                boolean z8222 = z5;
                Function2<? super Composer, ? super Integer, Unit> function232222 = composableLambda;
                int i23222 = i4 << 3;
                int i24222 = i4 >> 3;
                int i25222 = (i24222 & 7168) | (i23222 & 896) | (i23222 & 112) | 6;
                int i26222 = i4 >> 9;
                int i27222 = i20 << 21;
                int i28222 = ((i20 >> 9) & 14) | ((i4 >> 6) & 112) | (i4 & 896) | (i26222 & 7168) | (57344 & i24222) | (458752 & i20) | ((i20 << 6) & 3670016) | ((i20 << 3) & 29360128);
                Function2<? super Composer, ? super Integer, Unit> function233222 = function211;
                Composer composer4222 = composer2;
                TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, str, function2, visualTransformation, function214, function217, function213, function218, function233222, function215, function216, z2, z, z8222, interactionSource, paddingValues4, textFieldColors3, function232222, composer4222, i25222 | (i26222 & 57344) | (i26222 & 458752) | (i26222 & 3670016) | (i27222 & 29360128) | (i27222 & 234881024) | (i27222 & 1879048192), i28222, 0);
                composer3 = composer4222;
                if (ComposerKt.isTraceInProgress()) {
                }
                function219 = function233222;
                z6 = z8222;
                function220 = function213;
                function221 = function218;
                function222 = function215;
                function223 = function232222;
                function224 = function214;
                paddingValues5 = paddingValues4;
                function225 = function216;
                function226 = function217;
                endRestartGroup = composer3.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            int i212 = 8192;
            if ((i3 & 16) != 0) {
            }
            if ((i3 & 32) == 0) {
            }
            i5 = i3 & 64;
            if (i5 == 0) {
            }
            i6 = i3 & 128;
            if (i6 == 0) {
            }
            i7 = i3 & 256;
            if (i7 == 0) {
            }
            i8 = i3 & 512;
            if (i8 == 0) {
            }
            i9 = i8;
            i10 = i3 & 1024;
            if (i10 != 0) {
            }
            i13 = i3 & 2048;
            if (i13 != 0) {
            }
            int i22222 = i12;
            i15 = i3 & 4096;
            if (i15 != 0) {
            }
            i17 = i3 & 8192;
            if (i17 != 0) {
            }
            if ((i2 & 24576) == 0) {
            }
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            }
            i19 = i3 & 65536;
            if (i19 == 0) {
            }
            if ((i3 & 131072) == 0) {
            }
            if ((i4 & 306783379) == 306783378) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i5 == 0) {
            }
            if (i6 != 0) {
            }
            if (i7 == 0) {
            }
            if (i9 == 0) {
            }
            if (i11 == 0) {
            }
            if (i14 == 0) {
            }
            if (i15 == 0) {
            }
            if (i17 == 0) {
            }
            if ((i3 & 16384) == 0) {
            }
            if ((i3 & 32768) == 0) {
            }
            if (i19 == 0) {
            }
            composer2 = startRestartGroup;
            function213 = function228;
            function214 = function210;
            function215 = function230;
            i20 = i18;
            function216 = function212;
            textFieldColors3 = textFieldColors2;
            function217 = function227;
            function218 = function229;
            paddingValues4 = paddingValues3;
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            boolean z82222 = z5;
            Function2<? super Composer, ? super Integer, Unit> function2322222 = composableLambda;
            int i232222 = i4 << 3;
            int i242222 = i4 >> 3;
            int i252222 = (i242222 & 7168) | (i232222 & 896) | (i232222 & 112) | 6;
            int i262222 = i4 >> 9;
            int i272222 = i20 << 21;
            int i282222 = ((i20 >> 9) & 14) | ((i4 >> 6) & 112) | (i4 & 896) | (i262222 & 7168) | (57344 & i242222) | (458752 & i20) | ((i20 << 6) & 3670016) | ((i20 << 3) & 29360128);
            Function2<? super Composer, ? super Integer, Unit> function2332222 = function211;
            Composer composer42222 = composer2;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, str, function2, visualTransformation, function214, function217, function213, function218, function2332222, function215, function216, z2, z, z82222, interactionSource, paddingValues4, textFieldColors3, function2322222, composer42222, i252222 | (i262222 & 57344) | (i262222 & 458752) | (i262222 & 3670016) | (i272222 & 29360128) | (i272222 & 234881024) | (i272222 & 1879048192), i282222, 0);
            composer3 = composer42222;
            if (ComposerKt.isTraceInProgress()) {
            }
            function219 = function2332222;
            z6 = z82222;
            function220 = function213;
            function221 = function218;
            function222 = function215;
            function223 = function2322222;
            function224 = function214;
            paddingValues5 = paddingValues4;
            function225 = function216;
            function226 = function217;
            endRestartGroup = composer3.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        if ((i3 & 4) == 0) {
        }
        if ((i3 & 8) == 0) {
        }
        int i2122 = 8192;
        if ((i3 & 16) != 0) {
        }
        if ((i3 & 32) == 0) {
        }
        i5 = i3 & 64;
        if (i5 == 0) {
        }
        i6 = i3 & 128;
        if (i6 == 0) {
        }
        i7 = i3 & 256;
        if (i7 == 0) {
        }
        i8 = i3 & 512;
        if (i8 == 0) {
        }
        i9 = i8;
        i10 = i3 & 1024;
        if (i10 != 0) {
        }
        i13 = i3 & 2048;
        if (i13 != 0) {
        }
        int i222222 = i12;
        i15 = i3 & 4096;
        if (i15 != 0) {
        }
        i17 = i3 & 8192;
        if (i17 != 0) {
        }
        if ((i2 & 24576) == 0) {
        }
        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
        }
        i19 = i3 & 65536;
        if (i19 == 0) {
        }
        if ((i3 & 131072) == 0) {
        }
        if ((i4 & 306783379) == 306783378) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i5 == 0) {
        }
        if (i6 != 0) {
        }
        if (i7 == 0) {
        }
        if (i9 == 0) {
        }
        if (i11 == 0) {
        }
        if (i14 == 0) {
        }
        if (i15 == 0) {
        }
        if (i17 == 0) {
        }
        if ((i3 & 16384) == 0) {
        }
        if ((i3 & 32768) == 0) {
        }
        if (i19 == 0) {
        }
        composer2 = startRestartGroup;
        function213 = function228;
        function214 = function210;
        function215 = function230;
        i20 = i18;
        function216 = function212;
        textFieldColors3 = textFieldColors2;
        function217 = function227;
        function218 = function229;
        paddingValues4 = paddingValues3;
        composer2.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        boolean z822222 = z5;
        Function2<? super Composer, ? super Integer, Unit> function23222222 = composableLambda;
        int i2322222 = i4 << 3;
        int i2422222 = i4 >> 3;
        int i2522222 = (i2422222 & 7168) | (i2322222 & 896) | (i2322222 & 112) | 6;
        int i2622222 = i4 >> 9;
        int i2722222 = i20 << 21;
        int i2822222 = ((i20 >> 9) & 14) | ((i4 >> 6) & 112) | (i4 & 896) | (i2622222 & 7168) | (57344 & i2422222) | (458752 & i20) | ((i20 << 6) & 3670016) | ((i20 << 3) & 29360128);
        Function2<? super Composer, ? super Integer, Unit> function23322222 = function211;
        Composer composer422222 = composer2;
        TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, str, function2, visualTransformation, function214, function217, function213, function218, function23322222, function215, function216, z2, z, z822222, interactionSource, paddingValues4, textFieldColors3, function23222222, composer422222, i2522222 | (i2622222 & 57344) | (i2622222 & 458752) | (i2622222 & 3670016) | (i2722222 & 29360128) | (i2722222 & 234881024) | (i2722222 & 1879048192), i2822222, 0);
        composer3 = composer422222;
        if (ComposerKt.isTraceInProgress()) {
        }
        function219 = function23322222;
        z6 = z822222;
        function220 = function213;
        function221 = function218;
        function222 = function215;
        function223 = function23222222;
        function224 = function214;
        paddingValues5 = paddingValues4;
        function225 = function216;
        function226 = function217;
        endRestartGroup = composer3.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* renamed from: contentPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2496contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults outlinedTextFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return outlinedTextFieldDefaults.m2500contentPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* renamed from: contentPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2500contentPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m874PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    public final TextFieldColors colors(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -471651810, "C(colors)921@46417L11,921@46429L30:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-471651810, i, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.colors (TextFieldDefaults.kt:921)");
        }
        TextFieldColors defaultOutlinedTextFieldColors = getDefaultOutlinedTextFieldColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultOutlinedTextFieldColors;
    }

    /* renamed from: colors-0hiis_0, reason: not valid java name */
    public final TextFieldColors m2499colors0hiis_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, TextSelectionColors textSelectionColors, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, Composer composer, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        ComposerKt.sourceInformationMarkerStart(composer, 1767617725, "C(colors)P(30:c#ui.graphics.Color,41:c#ui.graphics.Color,9:c#ui.graphics.Color,20:c#ui.graphics.Color,23:c#ui.graphics.Color,34:c#ui.graphics.Color,2:c#ui.graphics.Color,12:c#ui.graphics.Color,0:c#ui.graphics.Color,13:c#ui.graphics.Color,32,22:c#ui.graphics.Color,33:c#ui.graphics.Color,1:c#ui.graphics.Color,11:c#ui.graphics.Color,25:c#ui.graphics.Color,36:c#ui.graphics.Color,4:c#ui.graphics.Color,15:c#ui.graphics.Color,31:c#ui.graphics.Color,42:c#ui.graphics.Color,10:c#ui.graphics.Color,21:c#ui.graphics.Color,24:c#ui.graphics.Color,35:c#ui.graphics.Color,3:c#ui.graphics.Color,14:c#ui.graphics.Color,26:c#ui.graphics.Color,37:c#ui.graphics.Color,5:c#ui.graphics.Color,16:c#ui.graphics.Color,29:c#ui.graphics.Color,40:c#ui.graphics.Color,8:c#ui.graphics.Color,19:c#ui.graphics.Color,27:c#ui.graphics.Color,38:c#ui.graphics.Color,6:c#ui.graphics.Color,17:c#ui.graphics.Color,28:c#ui.graphics.Color,39:c#ui.graphics.Color,7:c#ui.graphics.Color,18:c#ui.graphics.Color)1023@53240L11,1023@53252L30:TextFieldDefaults.kt#uh7d8r");
        long m4651getUnspecified0d7_KjU = (i6 & 1) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j;
        long m4651getUnspecified0d7_KjU2 = (i6 & 2) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j2;
        long m4651getUnspecified0d7_KjU3 = (i6 & 4) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j3;
        long m4651getUnspecified0d7_KjU4 = (i6 & 8) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j4;
        long m4651getUnspecified0d7_KjU5 = (i6 & 16) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j5;
        long m4651getUnspecified0d7_KjU6 = (i6 & 32) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j6;
        long m4651getUnspecified0d7_KjU7 = (i6 & 64) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j7;
        long m4651getUnspecified0d7_KjU8 = (i6 & 128) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j8;
        long m4651getUnspecified0d7_KjU9 = (i6 & 256) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j9;
        long m4651getUnspecified0d7_KjU10 = (i6 & 512) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j10;
        TextSelectionColors textSelectionColors2 = (i6 & 1024) != 0 ? null : textSelectionColors;
        long m4651getUnspecified0d7_KjU11 = (i6 & 2048) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j11;
        long m4651getUnspecified0d7_KjU12 = (i6 & 4096) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j12;
        long m4651getUnspecified0d7_KjU13 = (i6 & 8192) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j13;
        long m4651getUnspecified0d7_KjU14 = (i6 & 16384) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j14;
        long m4651getUnspecified0d7_KjU15 = (32768 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j15;
        long m4651getUnspecified0d7_KjU16 = (65536 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j16;
        long m4651getUnspecified0d7_KjU17 = (131072 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j17;
        long m4651getUnspecified0d7_KjU18 = (262144 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j18;
        long m4651getUnspecified0d7_KjU19 = (524288 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j19;
        long m4651getUnspecified0d7_KjU20 = (1048576 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j20;
        long m4651getUnspecified0d7_KjU21 = (2097152 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j21;
        long m4651getUnspecified0d7_KjU22 = (4194304 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j22;
        long m4651getUnspecified0d7_KjU23 = (8388608 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j23;
        long m4651getUnspecified0d7_KjU24 = (16777216 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j24;
        long m4651getUnspecified0d7_KjU25 = (33554432 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j25;
        long m4651getUnspecified0d7_KjU26 = (67108864 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j26;
        long m4651getUnspecified0d7_KjU27 = (134217728 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j27;
        long m4651getUnspecified0d7_KjU28 = (268435456 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j28;
        long m4651getUnspecified0d7_KjU29 = (536870912 & i6) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j29;
        long m4651getUnspecified0d7_KjU30 = (i6 & 1073741824) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j30;
        long m4651getUnspecified0d7_KjU31 = (i7 & 1) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j31;
        long m4651getUnspecified0d7_KjU32 = (i7 & 2) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j32;
        long m4651getUnspecified0d7_KjU33 = (i7 & 4) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j33;
        long m4651getUnspecified0d7_KjU34 = (i7 & 8) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j34;
        long m4651getUnspecified0d7_KjU35 = (i7 & 16) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j35;
        long m4651getUnspecified0d7_KjU36 = (i7 & 32) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j36;
        long m4651getUnspecified0d7_KjU37 = (i7 & 64) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j37;
        long m4651getUnspecified0d7_KjU38 = (i7 & 128) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j38;
        long m4651getUnspecified0d7_KjU39 = (i7 & 256) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j39;
        long m4651getUnspecified0d7_KjU40 = (i7 & 512) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j40;
        long m4651getUnspecified0d7_KjU41 = (i7 & 1024) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j41;
        long m4651getUnspecified0d7_KjU42 = (i7 & 2048) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j42;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1767617725, i, i2, "androidx.compose.material3.OutlinedTextFieldDefaults.colors (TextFieldDefaults.kt:1023)");
        }
        TextFieldColors m2767copyejIjP34 = getDefaultOutlinedTextFieldColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i5 >> 6) & 112).m2767copyejIjP34(m4651getUnspecified0d7_KjU, m4651getUnspecified0d7_KjU2, m4651getUnspecified0d7_KjU3, m4651getUnspecified0d7_KjU4, m4651getUnspecified0d7_KjU5, m4651getUnspecified0d7_KjU6, m4651getUnspecified0d7_KjU7, m4651getUnspecified0d7_KjU8, m4651getUnspecified0d7_KjU9, m4651getUnspecified0d7_KjU10, textSelectionColors2, m4651getUnspecified0d7_KjU11, m4651getUnspecified0d7_KjU12, m4651getUnspecified0d7_KjU13, m4651getUnspecified0d7_KjU14, m4651getUnspecified0d7_KjU15, m4651getUnspecified0d7_KjU16, m4651getUnspecified0d7_KjU17, m4651getUnspecified0d7_KjU18, m4651getUnspecified0d7_KjU19, m4651getUnspecified0d7_KjU20, m4651getUnspecified0d7_KjU21, m4651getUnspecified0d7_KjU22, m4651getUnspecified0d7_KjU23, m4651getUnspecified0d7_KjU24, m4651getUnspecified0d7_KjU25, m4651getUnspecified0d7_KjU26, m4651getUnspecified0d7_KjU27, m4651getUnspecified0d7_KjU28, m4651getUnspecified0d7_KjU29, m4651getUnspecified0d7_KjU30, m4651getUnspecified0d7_KjU31, m4651getUnspecified0d7_KjU32, m4651getUnspecified0d7_KjU33, m4651getUnspecified0d7_KjU34, m4651getUnspecified0d7_KjU35, m4651getUnspecified0d7_KjU36, m4651getUnspecified0d7_KjU37, m4651getUnspecified0d7_KjU38, m4651getUnspecified0d7_KjU39, m4651getUnspecified0d7_KjU40, m4651getUnspecified0d7_KjU41, m4651getUnspecified0d7_KjU42);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return m2767copyejIjP34;
    }

    public final TextFieldColors getDefaultOutlinedTextFieldColors(ColorScheme colorScheme, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -292363577, "C:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-292363577, i, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.<get-defaultOutlinedTextFieldColors> (TextFieldDefaults.kt:1071)");
        }
        TextFieldColors defaultOutlinedTextFieldColorsCached = colorScheme.getDefaultOutlinedTextFieldColorsCached();
        composer.startReplaceGroup(1540400102);
        ComposerKt.sourceInformation(composer, "*1086@57012L7");
        if (defaultOutlinedTextFieldColorsCached == null) {
            long fromToken = ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getFocusInputColor());
            long fromToken2 = ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getInputColor());
            long m4614copywmQWz5c$default = Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
            long fromToken3 = ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getErrorInputColor());
            long m4650getTransparent0d7_KjU = Color.INSTANCE.m4650getTransparent0d7_KjU();
            long m4650getTransparent0d7_KjU2 = Color.INSTANCE.m4650getTransparent0d7_KjU();
            long m4650getTransparent0d7_KjU3 = Color.INSTANCE.m4650getTransparent0d7_KjU();
            long m4650getTransparent0d7_KjU4 = Color.INSTANCE.m4650getTransparent0d7_KjU();
            long fromToken4 = ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getCaretColor());
            long fromToken5 = ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getErrorFocusCaretColor());
            ProvidableCompositionLocal<TextSelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd(composer);
            TextFieldColors textFieldColors = new TextFieldColors(fromToken, fromToken2, m4614copywmQWz5c$default, fromToken3, m4650getTransparent0d7_KjU, m4650getTransparent0d7_KjU2, m4650getTransparent0d7_KjU3, m4650getTransparent0d7_KjU4, fromToken4, fromToken5, (TextSelectionColors) consume, ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getFocusOutlineColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getOutlineColor()), Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getDisabledOutlineColor()), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getErrorOutlineColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getFocusLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getLeadingIconColor()), Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getDisabledLeadingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getErrorLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getFocusTrailingIconColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getTrailingIconColor()), Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getDisabledTrailingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getErrorTrailingIconColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getFocusLabelColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getLabelColor()), Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getDisabledLabelColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getErrorLabelColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor()), Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getFocusSupportingColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getSupportingColor()), Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getDisabledSupportingColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getErrorSupportingColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor()), Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor()), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor()), Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor()), null);
            colorScheme.setDefaultOutlinedTextFieldColorsCached$material3_release(textFieldColors);
            defaultOutlinedTextFieldColorsCached = textFieldColors;
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultOutlinedTextFieldColorsCached;
    }

    /* JADX WARN: Code restructure failed: missing block: B:72:0x0136, code lost:
    
        if ((r28 & 64) != 0) goto L124;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00e0  */
    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to OutlinedTextFieldDefaults.Container", replaceWith = @ReplaceWith(expression = "Container(\n    enabled = enabled,\n    isError = isError,\n    interactionSource = interactionSource,\n    colors = colors,\n    shape = shape,\n    focusedBorderThickness = focusedBorderThickness,\n    unfocusedBorderThickness = unfocusedBorderThickness,\n)", imports = {}))
    /* renamed from: ContainerBox-nbWgWpA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m2498ContainerBoxnbWgWpA(final boolean z, final boolean z2, final InteractionSource interactionSource, TextFieldColors textFieldColors, Shape shape, float f, float f2, Composer composer, final int i, final int i2) {
        boolean z3;
        int i3;
        boolean z4;
        InteractionSource interactionSource2;
        TextFieldColors textFieldColors2;
        Shape shape2;
        float f3;
        float f4;
        final TextFieldColors textFieldColors3;
        final Shape shape3;
        final float f5;
        final float f6;
        ScopeUpdateScope endRestartGroup;
        int i4;
        int i5;
        int i6;
        int i7;
        Composer startRestartGroup = composer.startRestartGroup(1461761386);
        ComposerKt.sourceInformation(startRestartGroup, "C(ContainerBox)P(1,4,3!1,5,2:c#ui.unit.Dp,6:c#ui.unit.Dp)1174@62656L8,1175@62715L5,1179@62864L348:TextFieldDefaults.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            z3 = z;
        } else if ((i & 6) == 0) {
            z3 = z;
            i3 = (startRestartGroup.changed(z3) ? 4 : 2) | i;
        } else {
            z3 = z;
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            z4 = z2;
            i3 |= startRestartGroup.changed(z4) ? 32 : 16;
            if ((i2 & 4) == 0) {
                i3 |= 384;
            } else if ((i & 384) == 0) {
                interactionSource2 = interactionSource;
                i3 |= startRestartGroup.changed(interactionSource2) ? 256 : 128;
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        textFieldColors2 = textFieldColors;
                        if (startRestartGroup.changed(textFieldColors2)) {
                            i7 = 2048;
                            i3 |= i7;
                        }
                    } else {
                        textFieldColors2 = textFieldColors;
                    }
                    i7 = 1024;
                    i3 |= i7;
                } else {
                    textFieldColors2 = textFieldColors;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        shape2 = shape;
                        if (startRestartGroup.changed(shape2)) {
                            i6 = 16384;
                            i3 |= i6;
                        }
                    } else {
                        shape2 = shape;
                    }
                    i6 = 8192;
                    i3 |= i6;
                } else {
                    shape2 = shape;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        f3 = f;
                        if (startRestartGroup.changed(f3)) {
                            i5 = 131072;
                            i3 |= i5;
                        }
                    } else {
                        f3 = f;
                    }
                    i5 = 65536;
                    i3 |= i5;
                } else {
                    f3 = f;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        f4 = f2;
                        if (startRestartGroup.changed(f4)) {
                            i4 = 1048576;
                            i3 |= i4;
                        }
                    } else {
                        f4 = f2;
                    }
                    i4 = 524288;
                    i3 |= i4;
                } else {
                    f4 = f2;
                }
                if ((i2 & 128) != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    i3 |= startRestartGroup.changed(this) ? 8388608 : 4194304;
                }
                if ((4793491 & i3) == 4793490 || !startRestartGroup.getSkipping()) {
                    startRestartGroup.startDefaults();
                    if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                        if ((i2 & 8) != 0) {
                            textFieldColors2 = colors(startRestartGroup, (i3 >> 21) & 14);
                            i3 &= -7169;
                        }
                        if ((i2 & 16) != 0) {
                            shape2 = INSTANCE.getShape(startRestartGroup, 6);
                            i3 &= -57345;
                        }
                        if ((i2 & 32) != 0) {
                            f3 = FocusedBorderThickness;
                            i3 &= -458753;
                        }
                        if ((i2 & 64) != 0) {
                            f4 = UnfocusedBorderThickness;
                            i3 &= -3670017;
                        }
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1461761386, i3, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.ContainerBox (TextFieldDefaults.kt:1179)");
                        }
                        int i8 = (i3 & 14) | 3072 | (i3 & 112) | (i3 & 896);
                        int i9 = i3 << 3;
                        int i10 = (i9 & 234881024) | i8 | (57344 & i9) | (458752 & i9) | (3670016 & i9) | (29360128 & i9);
                        InteractionSource interactionSource3 = interactionSource2;
                        textFieldColors3 = textFieldColors2;
                        shape3 = shape2;
                        f5 = f3;
                        f6 = f4;
                        m2497Container4EFweAY(z3, z4, interactionSource3, Modifier.INSTANCE, textFieldColors3, shape3, f5, f6, startRestartGroup, i10, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -57345;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                        }
                    }
                } else {
                    startRestartGroup.skipToGroupEnd();
                    textFieldColors3 = textFieldColors2;
                    shape3 = shape2;
                    f5 = f3;
                    f6 = f4;
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$ContainerBox$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i11) {
                            OutlinedTextFieldDefaults.this.m2498ContainerBoxnbWgWpA(z, z2, interactionSource, textFieldColors3, shape3, f5, f6, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                    return;
                }
                return;
            }
            interactionSource2 = interactionSource;
            if ((i & 3072) == 0) {
            }
            if ((i & 24576) == 0) {
            }
            if ((196608 & i) == 0) {
            }
            if ((1572864 & i) == 0) {
            }
            if ((i2 & 128) != 0) {
            }
            if ((4793491 & i3) == 4793490) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if ((i2 & 8) != 0) {
            }
            if ((i2 & 16) != 0) {
            }
            if ((i2 & 32) != 0) {
            }
            if ((i2 & 64) != 0) {
            }
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            int i82 = (i3 & 14) | 3072 | (i3 & 112) | (i3 & 896);
            int i92 = i3 << 3;
            int i102 = (i92 & 234881024) | i82 | (57344 & i92) | (458752 & i92) | (3670016 & i92) | (29360128 & i92);
            InteractionSource interactionSource32 = interactionSource2;
            textFieldColors3 = textFieldColors2;
            shape3 = shape2;
            f5 = f3;
            f6 = f4;
            m2497Container4EFweAY(z3, z4, interactionSource32, Modifier.INSTANCE, textFieldColors3, shape3, f5, f6, startRestartGroup, i102, 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        z4 = z2;
        if ((i2 & 4) == 0) {
        }
        interactionSource2 = interactionSource;
        if ((i & 3072) == 0) {
        }
        if ((i & 24576) == 0) {
        }
        if ((196608 & i) == 0) {
        }
        if ((1572864 & i) == 0) {
        }
        if ((i2 & 128) != 0) {
        }
        if ((4793491 & i3) == 4793490) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if ((i2 & 8) != 0) {
        }
        if ((i2 & 16) != 0) {
        }
        if ((i2 & 32) != 0) {
        }
        if ((i2 & 64) != 0) {
        }
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        int i822 = (i3 & 14) | 3072 | (i3 & 112) | (i3 & 896);
        int i922 = i3 << 3;
        int i1022 = (i922 & 234881024) | i822 | (57344 & i922) | (458752 & i922) | (3670016 & i922) | (29360128 & i922);
        InteractionSource interactionSource322 = interactionSource2;
        textFieldColors3 = textFieldColors2;
        shape3 = shape2;
        f5 = f3;
        f6 = f4;
        m2497Container4EFweAY(z3, z4, interactionSource322, Modifier.INSTANCE, textFieldColors3, shape3, f5, f6, startRestartGroup, i1022, 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }
}
