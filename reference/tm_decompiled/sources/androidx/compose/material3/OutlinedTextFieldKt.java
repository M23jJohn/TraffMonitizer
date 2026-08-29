package androidx.compose.material3;

import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.tokens.TypeScaleTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: OutlinedTextField.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aØ\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\t0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\b\b\u0002\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020'2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.H\u0007¢\u0006\u0002\u0010/\u001aØ\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u0002002\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\t0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\b\b\u0002\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020'2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.H\u0007¢\u0006\u0002\u00101\u001a\u0080\u0002\u00102\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0011\u00103\u001a\r\u0012\u0004\u0012\u00020\t0\u0016¢\u0006\u0002\b\u00172\u0019\u0010\u0018\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\r¢\u0006\u0002\b\u00172\u0013\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0013\u00104\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0013\u00105\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0013\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0006\u0010%\u001a\u00020\u00112\u0006\u00106\u001a\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\t0\r2\u0011\u0010:\u001a\r\u0012\u0004\u0012\u00020\t0\u0016¢\u0006\u0002\b\u00172\u0013\u0010;\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\u0010>\u001ar\u0010?\u001a\u00020'2\u0006\u0010@\u001a\u00020'2\u0006\u0010A\u001a\u00020'2\u0006\u0010B\u001a\u00020'2\u0006\u0010C\u001a\u00020'2\u0006\u0010D\u001a\u00020'2\u0006\u0010E\u001a\u00020'2\u0006\u0010F\u001a\u00020'2\u0006\u0010G\u001a\u00020'2\u0006\u00106\u001a\u0002072\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u0002072\u0006\u0010<\u001a\u00020=H\u0002ø\u0001\u0000¢\u0006\u0004\bK\u0010L\u001aj\u0010M\u001a\u00020'2\u0006\u0010N\u001a\u00020'2\u0006\u0010O\u001a\u00020'2\u0006\u0010P\u001a\u00020'2\u0006\u0010Q\u001a\u00020'2\u0006\u0010R\u001a\u00020'2\u0006\u0010S\u001a\u00020'2\u0006\u0010T\u001a\u00020'2\u0006\u00106\u001a\u0002072\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u0002072\u0006\u0010<\u001a\u00020=H\u0002ø\u0001\u0000¢\u0006\u0004\bU\u0010V\u001a\"\u0010W\u001a\u00020\u000f*\u00020\u000f2\f\u0010X\u001a\b\u0012\u0004\u0012\u0002090\u00162\u0006\u0010<\u001a\u00020=H\u0000\u001a\u009a\u0001\u0010Y\u001a\u00020\t*\u00020Z2\u0006\u0010[\u001a\u00020'2\u0006\u0010\\\u001a\u00020'2\b\u0010]\u001a\u0004\u0018\u00010^2\b\u0010_\u001a\u0004\u0018\u00010^2\b\u0010`\u001a\u0004\u0018\u00010^2\b\u0010a\u001a\u0004\u0018\u00010^2\u0006\u0010b\u001a\u00020^2\b\u0010c\u001a\u0004\u0018\u00010^2\b\u0010d\u001a\u0004\u0018\u00010^2\u0006\u0010e\u001a\u00020^2\b\u0010f\u001a\u0004\u0018\u00010^2\u0006\u00106\u001a\u0002072\u0006\u0010%\u001a\u00020\u00112\u0006\u0010J\u001a\u0002072\u0006\u0010g\u001a\u00020h2\u0006\u0010<\u001a\u00020=H\u0002\u001a\u0014\u0010i\u001a\u00020'*\u00020'2\u0006\u0010j\u001a\u00020'H\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0016\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006k"}, d2 = {"OutlinedTextFieldInnerPadding", "Landroidx/compose/ui/unit/Dp;", "F", "OutlinedTextFieldTopPadding", "Landroidx/compose/ui/unit/TextUnit;", "getOutlinedTextFieldTopPadding", "()J", "J", "OutlinedTextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "label", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "isError", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "minLines", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/TextFieldColors;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "OutlinedTextFieldLayout", "textField", "leading", "trailing", "animationProgress", "", "onLabelMeasured", "Landroidx/compose/ui/geometry/Size;", "container", "supporting", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZFLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;II)V", "calculateHeight", "leadingHeight", "trailingHeight", "prefixHeight", "suffixHeight", "textFieldHeight", "labelHeight", "placeholderHeight", "supportingHeight", "constraints", "Landroidx/compose/ui/unit/Constraints;", "density", "calculateHeight-mKXJcVc", "(IIIIIIIIFJFLandroidx/compose/foundation/layout/PaddingValues;)I", "calculateWidth", "leadingPlaceableWidth", "trailingPlaceableWidth", "prefixPlaceableWidth", "suffixPlaceableWidth", "textFieldPlaceableWidth", "labelPlaceableWidth", "placeholderPlaceableWidth", "calculateWidth-DHJA7U0", "(IIIIIIIFJFLandroidx/compose/foundation/layout/PaddingValues;)I", "outlineCutout", "labelSize", "place", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "totalHeight", "width", "leadingPlaceable", "Landroidx/compose/ui/layout/Placeable;", "trailingPlaceable", "prefixPlaceable", "suffixPlaceable", "textFieldPlaceable", "labelPlaceable", "placeholderPlaceable", "containerPlaceable", "supportingPlaceable", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "substractConstraintSafely", "from", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class OutlinedTextFieldKt {
    private static final float OutlinedTextFieldInnerPadding = Dp.m7399constructorimpl(4);
    private static final long OutlinedTextFieldTopPadding;

    /* JADX INFO: Access modifiers changed from: private */
    public static final int substractConstraintSafely(int i, int i2) {
        return i == Integer.MAX_VALUE ? i : i - i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x05be  */
    /* JADX WARN: Removed duplicated region for block: B:110:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x04a1  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x04f4  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0393  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x03c4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x03f8  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0419  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x043c  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x03f4  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x03d4  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void OutlinedTextField(final String str, final Function1<? super String, Unit> function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, Composer composer, final int i3, final int i4, final int i5, final int i6) {
        final String str2;
        int i7;
        final Function1<? super String, Unit> function12;
        Modifier modifier2;
        int i8;
        boolean z5;
        int i9;
        boolean z6;
        TextStyle textStyle2;
        int i10;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        int i11;
        Function2<? super Composer, ? super Integer, Unit> function29;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        int i28;
        int i29;
        int i30;
        int i31;
        int i32;
        int i33;
        int i34;
        int i35;
        final Shape shape2;
        TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function210;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function211;
        int i36;
        final Function2<? super Composer, ? super Integer, Unit> function212;
        final KeyboardOptions keyboardOptions2;
        final Function2<? super Composer, ? super Integer, Unit> function213;
        final KeyboardActions keyboardActions2;
        final Function2<? super Composer, ? super Integer, Unit> function214;
        final boolean z7;
        final int i37;
        final int i38;
        MutableInteractionSource mutableInteractionSource2;
        final TextFieldColors textFieldColors2;
        final VisualTransformation visualTransformation2;
        int i39;
        final Function2<? super Composer, ? super Integer, Unit> function215;
        final boolean z8;
        final boolean z9;
        int i40;
        final MutableInteractionSource mutableInteractionSource3;
        long m6878getColor0d7_KjU;
        final TextStyle textStyle4;
        Composer composer2;
        final Function2<? super Composer, ? super Integer, Unit> function216;
        final boolean z10;
        final boolean z11;
        final boolean z12;
        final KeyboardOptions keyboardOptions3;
        final KeyboardActions keyboardActions3;
        final boolean z13;
        final int i41;
        final int i42;
        final VisualTransformation visualTransformation3;
        final Function2<? super Composer, ? super Integer, Unit> function217;
        final Function2<? super Composer, ? super Integer, Unit> function218;
        final Function2<? super Composer, ? super Integer, Unit> function219;
        final Function2<? super Composer, ? super Integer, Unit> function220;
        final Shape shape3;
        final MutableInteractionSource mutableInteractionSource4;
        final TextFieldColors textFieldColors3;
        final Function2<? super Composer, ? super Integer, Unit> function221;
        ScopeUpdateScope endRestartGroup;
        int i43;
        Composer startRestartGroup = composer.startRestartGroup(-1922450045);
        ComposerKt.sourceInformation(startRestartGroup, "C(OutlinedTextField)P(21,11,10,1,14,19,6,12,7,20,13,17,18,3,22,5,4,16,8,9,2,15)175@9821L7,191@10625L5,192@10688L8,204@11208L7,206@11308L2853,206@11221L2940:OutlinedTextField.kt#uh7d8r");
        if ((i6 & 1) != 0) {
            i7 = i3 | 6;
            str2 = str;
        } else {
            str2 = str;
            if ((i3 & 6) == 0) {
                i7 = (startRestartGroup.changed(str2) ? 4 : 2) | i3;
            } else {
                i7 = i3;
            }
        }
        if ((i6 & 2) != 0) {
            i7 |= 48;
            function12 = function1;
        } else {
            function12 = function1;
            if ((i3 & 48) == 0) {
                i7 |= startRestartGroup.changedInstance(function12) ? 32 : 16;
            }
        }
        int i44 = i6 & 4;
        if (i44 != 0) {
            i7 |= 384;
        } else if ((i3 & 384) == 0) {
            modifier2 = modifier;
            i7 |= startRestartGroup.changed(modifier2) ? 256 : 128;
            i8 = i6 & 8;
            if (i8 == 0) {
                i7 |= 3072;
            } else if ((i3 & 3072) == 0) {
                z5 = z;
                i7 |= startRestartGroup.changed(z5) ? 2048 : 1024;
                i9 = i6 & 16;
                if (i9 != 0) {
                    i7 |= 24576;
                } else if ((i3 & 24576) == 0) {
                    z6 = z2;
                    i7 |= startRestartGroup.changed(z6) ? 16384 : 8192;
                    if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                        textStyle2 = textStyle;
                        i7 |= ((i6 & 32) == 0 && startRestartGroup.changed(textStyle2)) ? 131072 : 65536;
                    } else {
                        textStyle2 = textStyle;
                    }
                    i10 = i6 & 64;
                    if (i10 == 0) {
                        i7 |= 1572864;
                        function28 = function2;
                    } else {
                        function28 = function2;
                        if ((i3 & 1572864) == 0) {
                            i7 |= startRestartGroup.changedInstance(function28) ? 1048576 : 524288;
                        }
                    }
                    i11 = i6 & 128;
                    if (i11 == 0) {
                        i7 |= 12582912;
                        function29 = function22;
                    } else {
                        function29 = function22;
                        if ((i3 & 12582912) == 0) {
                            i7 |= startRestartGroup.changedInstance(function29) ? 8388608 : 4194304;
                        }
                    }
                    i12 = i6 & 256;
                    if (i12 == 0) {
                        i7 |= 100663296;
                    } else if ((i3 & 100663296) == 0) {
                        i13 = i12;
                        i7 |= startRestartGroup.changedInstance(function23) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        i14 = i6 & 512;
                        if (i14 != 0) {
                            i7 |= 805306368;
                        } else if ((i3 & 805306368) == 0) {
                            i15 = i14;
                            i7 |= startRestartGroup.changedInstance(function24) ? 536870912 : 268435456;
                            i16 = i6 & 1024;
                            if (i16 == 0) {
                                i18 = i4 | 6;
                                i17 = i16;
                            } else if ((i4 & 6) == 0) {
                                i17 = i16;
                                i18 = i4 | (startRestartGroup.changedInstance(function25) ? 4 : 2);
                            } else {
                                i17 = i16;
                                i18 = i4;
                            }
                            i19 = i6 & 2048;
                            if (i19 == 0) {
                                i18 |= 48;
                                i20 = i19;
                            } else if ((i4 & 48) == 0) {
                                i20 = i19;
                                i18 |= startRestartGroup.changedInstance(function26) ? 32 : 16;
                            } else {
                                i20 = i19;
                            }
                            int i45 = i18;
                            i21 = i7;
                            i22 = i6 & 4096;
                            if (i22 == 0) {
                                i23 = i45 | 384;
                            } else {
                                int i46 = i45;
                                if ((i4 & 384) == 0) {
                                    i46 |= startRestartGroup.changedInstance(function27) ? 256 : 128;
                                }
                                i23 = i46;
                            }
                            i24 = i6 & 8192;
                            if (i24 == 0) {
                                i25 = i23 | 3072;
                            } else {
                                int i47 = i23;
                                if ((i4 & 3072) == 0) {
                                    i25 = i47 | (startRestartGroup.changed(z3) ? 2048 : 1024);
                                } else {
                                    i25 = i47;
                                }
                            }
                            i26 = i6 & 16384;
                            if (i26 == 0) {
                                i27 = i25 | 24576;
                            } else {
                                i27 = i25;
                                if ((i4 & 24576) == 0) {
                                    i27 |= startRestartGroup.changed(visualTransformation) ? 16384 : 8192;
                                    i28 = i6 & 32768;
                                    if (i28 != 0) {
                                        i27 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                                    } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                        i27 |= startRestartGroup.changed(keyboardOptions) ? 131072 : 65536;
                                    }
                                    i29 = i6 & 65536;
                                    if (i29 != 0) {
                                        i27 |= 1572864;
                                    } else if ((i4 & 1572864) == 0) {
                                        i27 |= startRestartGroup.changed(keyboardActions) ? 1048576 : 524288;
                                    }
                                    i30 = i6 & 131072;
                                    if (i30 != 0) {
                                        i27 |= 12582912;
                                    } else if ((i4 & 12582912) == 0) {
                                        i27 |= startRestartGroup.changed(z4) ? 8388608 : 4194304;
                                    }
                                    if ((i4 & 100663296) == 0) {
                                        i27 |= ((i6 & 262144) == 0 && startRestartGroup.changed(i)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                    }
                                    i31 = i6 & 524288;
                                    if (i31 != 0) {
                                        i27 |= 805306368;
                                    } else if ((i4 & 805306368) == 0) {
                                        i27 |= startRestartGroup.changed(i2) ? 536870912 : 268435456;
                                    }
                                    i32 = i6 & 1048576;
                                    if (i32 != 0) {
                                        i33 = i5 | 6;
                                    } else if ((i5 & 6) == 0) {
                                        i33 = i5 | (startRestartGroup.changed(mutableInteractionSource) ? 4 : 2);
                                    } else {
                                        i33 = i5;
                                    }
                                    if ((i5 & 48) == 0) {
                                        i33 |= ((i6 & 2097152) == 0 && startRestartGroup.changed(shape)) ? 32 : 16;
                                    }
                                    if ((i5 & 384) == 0) {
                                        if ((i6 & 4194304) == 0 && startRestartGroup.changed(textFieldColors)) {
                                            i43 = 256;
                                            i33 |= i43;
                                        }
                                        i43 = 128;
                                        i33 |= i43;
                                    }
                                    int i48 = i33;
                                    if ((i21 & 306783379) == 306783378 || (i27 & 306783379) != 306783378 || (i48 & 147) != 146 || !startRestartGroup.getSkipping()) {
                                        startRestartGroup.startDefaults();
                                        if ((i3 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                            if (i44 != 0) {
                                                modifier2 = Modifier.INSTANCE;
                                            }
                                            if (i8 != 0) {
                                                z5 = true;
                                            }
                                            if (i9 != 0) {
                                                z6 = false;
                                            }
                                            if ((i6 & 32) != 0) {
                                                ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                                Object consume = startRestartGroup.consume(localTextStyle);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                i34 = i21 & (-458753);
                                                textStyle2 = (TextStyle) consume;
                                            } else {
                                                i34 = i21;
                                            }
                                            if (i10 != 0) {
                                                function28 = null;
                                            }
                                            if (i11 != 0) {
                                                function29 = null;
                                            }
                                            Function2<? super Composer, ? super Integer, Unit> function222 = i13 != 0 ? null : function23;
                                            Function2<? super Composer, ? super Integer, Unit> function223 = i15 != 0 ? null : function24;
                                            Function2<? super Composer, ? super Integer, Unit> function224 = i17 != 0 ? null : function25;
                                            Function2<? super Composer, ? super Integer, Unit> function225 = i20 != 0 ? null : function26;
                                            Function2<? super Composer, ? super Integer, Unit> function226 = i22 != 0 ? null : function27;
                                            boolean z14 = i24 != 0 ? false : z3;
                                            VisualTransformation none = i26 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                                            KeyboardOptions keyboardOptions4 = i28 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                                            KeyboardActions keyboardActions4 = i29 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                                            boolean z15 = i30 != 0 ? false : z4;
                                            if ((i6 & 262144) != 0) {
                                                i35 = z15 ? 1 : Integer.MAX_VALUE;
                                                i27 &= -234881025;
                                            } else {
                                                i35 = i;
                                            }
                                            int i49 = i31 != 0 ? 1 : i2;
                                            MutableInteractionSource mutableInteractionSource5 = i32 == 0 ? mutableInteractionSource : null;
                                            Shape shape4 = (i6 & 2097152) != 0 ? OutlinedTextFieldDefaults.INSTANCE.getShape(startRestartGroup, 6) : shape;
                                            MutableInteractionSource mutableInteractionSource6 = mutableInteractionSource5;
                                            if ((i6 & 4194304) != 0) {
                                                Modifier modifier4 = modifier2;
                                                textFieldColors2 = OutlinedTextFieldDefaults.INSTANCE.colors(startRestartGroup, 6);
                                                textStyle3 = textStyle2;
                                                modifier3 = modifier4;
                                                shape2 = shape4;
                                                function210 = function222;
                                                function211 = function223;
                                                i36 = i27;
                                                function212 = function224;
                                                keyboardOptions2 = keyboardOptions4;
                                                function213 = function225;
                                                keyboardActions2 = keyboardActions4;
                                                function214 = function226;
                                                z7 = z15;
                                                i37 = i35;
                                                i38 = i49;
                                                mutableInteractionSource2 = mutableInteractionSource6;
                                            } else {
                                                shape2 = shape4;
                                                textStyle3 = textStyle2;
                                                function210 = function222;
                                                modifier3 = modifier2;
                                                function211 = function223;
                                                i36 = i27;
                                                function212 = function224;
                                                keyboardOptions2 = keyboardOptions4;
                                                function213 = function225;
                                                keyboardActions2 = keyboardActions4;
                                                function214 = function226;
                                                z7 = z15;
                                                i37 = i35;
                                                i38 = i49;
                                                mutableInteractionSource2 = mutableInteractionSource6;
                                                textFieldColors2 = textFieldColors;
                                            }
                                            visualTransformation2 = none;
                                            i39 = i34;
                                            function215 = function29;
                                            z8 = z14;
                                            z9 = z6;
                                            i40 = 0;
                                        } else {
                                            startRestartGroup.skipToGroupEnd();
                                            i39 = (i6 & 32) != 0 ? i21 & (-458753) : i21;
                                            if ((262144 & i6) != 0) {
                                                i27 &= -234881025;
                                            }
                                            function210 = function23;
                                            function211 = function24;
                                            function212 = function25;
                                            function213 = function26;
                                            function214 = function27;
                                            visualTransformation2 = visualTransformation;
                                            keyboardOptions2 = keyboardOptions;
                                            keyboardActions2 = keyboardActions;
                                            z7 = z4;
                                            i37 = i;
                                            i38 = i2;
                                            mutableInteractionSource2 = mutableInteractionSource;
                                            shape2 = shape;
                                            z9 = z6;
                                            textStyle3 = textStyle2;
                                            function215 = function29;
                                            modifier3 = modifier2;
                                            i36 = i27;
                                            i40 = 0;
                                            z8 = z3;
                                            textFieldColors2 = textFieldColors;
                                        }
                                        startRestartGroup.endDefaults();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1922450045, i39, i36, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:193)");
                                        }
                                        startRestartGroup.startReplaceGroup(30062948);
                                        ComposerKt.sourceInformation(startRestartGroup, "195@10782L39");
                                        if (mutableInteractionSource2 == null) {
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 30063599, "CC(remember):OutlinedTextField.kt#9igjgp");
                                            Object rememberedValue = startRestartGroup.rememberedValue();
                                            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue = InteractionSourceKt.MutableInteractionSource();
                                                startRestartGroup.updateRememberedValue(rememberedValue);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            mutableInteractionSource3 = (MutableInteractionSource) rememberedValue;
                                        } else {
                                            mutableInteractionSource3 = mutableInteractionSource2;
                                        }
                                        startRestartGroup.endReplaceGroup();
                                        startRestartGroup.startReplaceGroup(30069058);
                                        ComposerKt.sourceInformation(startRestartGroup, "*199@11006L25");
                                        m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
                                        if (m6878getColor0d7_KjU == 16) {
                                            m6878getColor0d7_KjU = textFieldColors2.m2818textColorXeAY9LY$material3_release(z5, z8, FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, startRestartGroup, i40).getValue().booleanValue());
                                        }
                                        startRestartGroup.endReplaceGroup();
                                        final TextStyle merge = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                        Object consume2 = startRestartGroup.consume(localDensity);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        final Density density = (Density) consume2;
                                        final boolean z16 = z5;
                                        CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-1886965181, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                invoke(composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer3, int i50) {
                                                Modifier.Companion companion;
                                                ComposerKt.sourceInformation(composer3, "C222@12041L38,240@12860L1285,207@11318L2837:OutlinedTextField.kt#uh7d8r");
                                                if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-1886965181, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:207)");
                                                    }
                                                    Modifier modifier5 = Modifier.this;
                                                    if (function28 != null) {
                                                        companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.1
                                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                            }

                                                            @Override // kotlin.jvm.functions.Function1
                                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                                invoke2(semanticsPropertyReceiver);
                                                                return Unit.INSTANCE;
                                                            }
                                                        }), 0.0f, density.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                                                    } else {
                                                        companion = Modifier.INSTANCE;
                                                    }
                                                    Modifier then = modifier5.then(companion);
                                                    boolean z17 = z8;
                                                    Strings.Companion companion2 = Strings.INSTANCE;
                                                    Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                                                    SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                                                    String str3 = str2;
                                                    Function1<String, Unit> function13 = function12;
                                                    boolean z18 = z16;
                                                    boolean z19 = z9;
                                                    TextStyle textStyle5 = merge;
                                                    KeyboardOptions keyboardOptions5 = keyboardOptions2;
                                                    KeyboardActions keyboardActions5 = keyboardActions2;
                                                    boolean z20 = z7;
                                                    int i51 = i37;
                                                    int i52 = i38;
                                                    VisualTransformation visualTransformation4 = visualTransformation2;
                                                    MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                                                    SolidColor solidColor2 = solidColor;
                                                    final String str4 = str2;
                                                    final boolean z21 = z16;
                                                    final boolean z22 = z7;
                                                    final VisualTransformation visualTransformation5 = visualTransformation2;
                                                    final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                                                    final boolean z23 = z8;
                                                    final Function2<Composer, Integer, Unit> function227 = function28;
                                                    final Function2<Composer, Integer, Unit> function228 = function215;
                                                    final Function2<Composer, Integer, Unit> function229 = function210;
                                                    final Function2<Composer, Integer, Unit> function230 = function211;
                                                    final Function2<Composer, Integer, Unit> function231 = function212;
                                                    final Function2<Composer, Integer, Unit> function232 = function213;
                                                    final Function2<Composer, Integer, Unit> function233 = function214;
                                                    final TextFieldColors textFieldColors4 = textFieldColors2;
                                                    final Shape shape5 = shape2;
                                                    BasicTextFieldKt.BasicTextField(str3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(1474611661, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.3
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        /* JADX WARN: Multi-variable type inference failed */
                                                        {
                                                            super(3);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function3
                                                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                                                            invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                                                            return Unit.INSTANCE;
                                                        }

                                                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                                                            int i54;
                                                            ComposerKt.sourceInformation(composer4, "C257@13714L391,241@12926L1201:OutlinedTextField.kt#uh7d8r");
                                                            if ((i53 & 6) == 0) {
                                                                i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                                                            } else {
                                                                i54 = i53;
                                                            }
                                                            if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventStart(1474611661, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:241)");
                                                                }
                                                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                                                int i55 = i54;
                                                                String str5 = str4;
                                                                boolean z24 = z21;
                                                                boolean z25 = z22;
                                                                VisualTransformation visualTransformation6 = visualTransformation5;
                                                                MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                                                boolean z26 = z23;
                                                                Function2<Composer, Integer, Unit> function235 = function227;
                                                                Function2<Composer, Integer, Unit> function236 = function228;
                                                                Function2<Composer, Integer, Unit> function237 = function229;
                                                                Function2<Composer, Integer, Unit> function238 = function230;
                                                                Function2<Composer, Integer, Unit> function239 = function231;
                                                                Function2<Composer, Integer, Unit> function240 = function232;
                                                                Function2<Composer, Integer, Unit> function241 = function233;
                                                                TextFieldColors textFieldColors5 = textFieldColors4;
                                                                final boolean z27 = z21;
                                                                final boolean z28 = z23;
                                                                final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                                                final TextFieldColors textFieldColors6 = textFieldColors4;
                                                                final Shape shape6 = shape5;
                                                                outlinedTextFieldDefaults.DecorationBox(str5, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(2108828640, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.1.3.1
                                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                    {
                                                                        super(2);
                                                                    }

                                                                    @Override // kotlin.jvm.functions.Function2
                                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                                                        invoke(composer5, num.intValue());
                                                                        return Unit.INSTANCE;
                                                                    }

                                                                    public final void invoke(Composer composer5, int i56) {
                                                                        ComposerKt.sourceInformation(composer5, "C258@13770L309:OutlinedTextField.kt#uh7d8r");
                                                                        if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                                ComposerKt.traceEventStart(2108828640, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:258)");
                                                                            }
                                                                            OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                                ComposerKt.traceEventEnd();
                                                                                return;
                                                                            }
                                                                            return;
                                                                        }
                                                                        composer5.skipToGroupEnd();
                                                                    }
                                                                }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventEnd();
                                                                    return;
                                                                }
                                                                return;
                                                            }
                                                            composer4.skipToGroupEnd();
                                                        }
                                                    }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer3.skipToGroupEnd();
                                            }
                                        }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        textStyle4 = textStyle3;
                                        composer2 = startRestartGroup;
                                        function216 = function28;
                                        z10 = z8;
                                        z11 = z16;
                                        z12 = z9;
                                        keyboardOptions3 = keyboardOptions2;
                                        keyboardActions3 = keyboardActions2;
                                        z13 = z7;
                                        i41 = i37;
                                        i42 = i38;
                                        visualTransformation3 = visualTransformation2;
                                        function217 = function215;
                                        function218 = function211;
                                        function219 = function212;
                                        function220 = function213;
                                        shape3 = shape2;
                                        mutableInteractionSource4 = mutableInteractionSource2;
                                        textFieldColors3 = textFieldColors2;
                                        function221 = function214;
                                    } else {
                                        startRestartGroup.skipToGroupEnd();
                                        function210 = function23;
                                        function219 = function25;
                                        z10 = z3;
                                        visualTransformation3 = visualTransformation;
                                        keyboardOptions3 = keyboardOptions;
                                        keyboardActions3 = keyboardActions;
                                        z13 = z4;
                                        i41 = i;
                                        i42 = i2;
                                        mutableInteractionSource4 = mutableInteractionSource;
                                        shape3 = shape;
                                        textFieldColors3 = textFieldColors;
                                        composer2 = startRestartGroup;
                                        z11 = z5;
                                        z12 = z6;
                                        textStyle4 = textStyle2;
                                        function216 = function28;
                                        function217 = function29;
                                        modifier3 = modifier2;
                                        function218 = function24;
                                        function220 = function26;
                                        function221 = function27;
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup != null) {
                                        final Function2<? super Composer, ? super Integer, Unit> function227 = function210;
                                        final Modifier modifier5 = modifier3;
                                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$2
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                invoke(composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer3, int i50) {
                                                OutlinedTextFieldKt.OutlinedTextField(str, function1, modifier5, z11, z12, textStyle4, function216, function217, function227, function218, function219, function220, function221, z10, visualTransformation3, keyboardOptions3, keyboardActions3, z13, i41, i42, mutableInteractionSource4, shape3, textFieldColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
                                            }
                                        });
                                        return;
                                    }
                                    return;
                                }
                            }
                            i28 = i6 & 32768;
                            if (i28 != 0) {
                            }
                            i29 = i6 & 65536;
                            if (i29 != 0) {
                            }
                            i30 = i6 & 131072;
                            if (i30 != 0) {
                            }
                            if ((i4 & 100663296) == 0) {
                            }
                            i31 = i6 & 524288;
                            if (i31 != 0) {
                            }
                            i32 = i6 & 1048576;
                            if (i32 != 0) {
                            }
                            if ((i5 & 48) == 0) {
                            }
                            if ((i5 & 384) == 0) {
                            }
                            int i482 = i33;
                            if ((i21 & 306783379) == 306783378) {
                            }
                            startRestartGroup.startDefaults();
                            if ((i3 & 1) != 0) {
                            }
                            if (i44 != 0) {
                            }
                            if (i8 != 0) {
                            }
                            if (i9 != 0) {
                            }
                            if ((i6 & 32) != 0) {
                            }
                            if (i10 != 0) {
                            }
                            if (i11 != 0) {
                            }
                            if (i13 != 0) {
                            }
                            if (i15 != 0) {
                            }
                            if (i17 != 0) {
                            }
                            if (i20 != 0) {
                            }
                            if (i22 != 0) {
                            }
                            if (i24 != 0) {
                            }
                            if (i26 != 0) {
                            }
                            if (i28 != 0) {
                            }
                            if (i29 != 0) {
                            }
                            if (i30 != 0) {
                            }
                            if ((i6 & 262144) != 0) {
                            }
                            if (i31 != 0) {
                            }
                            if (i32 == 0) {
                            }
                            if ((i6 & 2097152) != 0) {
                            }
                            MutableInteractionSource mutableInteractionSource62 = mutableInteractionSource5;
                            if ((i6 & 4194304) != 0) {
                            }
                            visualTransformation2 = none;
                            i39 = i34;
                            function215 = function29;
                            z8 = z14;
                            z9 = z6;
                            i40 = 0;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.startReplaceGroup(30062948);
                            ComposerKt.sourceInformation(startRestartGroup, "195@10782L39");
                            if (mutableInteractionSource2 == null) {
                            }
                            startRestartGroup.endReplaceGroup();
                            startRestartGroup.startReplaceGroup(30069058);
                            ComposerKt.sourceInformation(startRestartGroup, "*199@11006L25");
                            m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
                            if (m6878getColor0d7_KjU == 16) {
                            }
                            startRestartGroup.endReplaceGroup();
                            final TextStyle merge2 = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object consume22 = startRestartGroup.consume(localDensity2);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            final Density density2 = (Density) consume22;
                            final boolean z162 = z5;
                            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-1886965181, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i50) {
                                    Modifier.Companion companion;
                                    ComposerKt.sourceInformation(composer3, "C222@12041L38,240@12860L1285,207@11318L2837:OutlinedTextField.kt#uh7d8r");
                                    if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1886965181, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:207)");
                                        }
                                        Modifier modifier52 = Modifier.this;
                                        if (function28 != null) {
                                            companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.1
                                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                    invoke2(semanticsPropertyReceiver);
                                                    return Unit.INSTANCE;
                                                }
                                            }), 0.0f, density2.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                                        } else {
                                            companion = Modifier.INSTANCE;
                                        }
                                        Modifier then = modifier52.then(companion);
                                        boolean z17 = z8;
                                        Strings.Companion companion2 = Strings.INSTANCE;
                                        Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                                        SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                                        String str3 = str2;
                                        Function1<String, Unit> function13 = function12;
                                        boolean z18 = z162;
                                        boolean z19 = z9;
                                        TextStyle textStyle5 = merge2;
                                        KeyboardOptions keyboardOptions5 = keyboardOptions2;
                                        KeyboardActions keyboardActions5 = keyboardActions2;
                                        boolean z20 = z7;
                                        int i51 = i37;
                                        int i52 = i38;
                                        VisualTransformation visualTransformation4 = visualTransformation2;
                                        MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                                        SolidColor solidColor2 = solidColor;
                                        final String str4 = str2;
                                        final boolean z21 = z162;
                                        final boolean z22 = z7;
                                        final VisualTransformation visualTransformation5 = visualTransformation2;
                                        final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                                        final boolean z23 = z8;
                                        final Function2<? super Composer, ? super Integer, Unit> function2272 = function28;
                                        final Function2<? super Composer, ? super Integer, Unit> function228 = function215;
                                        final Function2<? super Composer, ? super Integer, Unit> function229 = function210;
                                        final Function2<? super Composer, ? super Integer, Unit> function230 = function211;
                                        final Function2<? super Composer, ? super Integer, Unit> function231 = function212;
                                        final Function2<? super Composer, ? super Integer, Unit> function232 = function213;
                                        final Function2<? super Composer, ? super Integer, Unit> function233 = function214;
                                        final TextFieldColors textFieldColors4 = textFieldColors2;
                                        final Shape shape5 = shape2;
                                        BasicTextFieldKt.BasicTextField(str3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(1474611661, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.3
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                                                invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                                                int i54;
                                                ComposerKt.sourceInformation(composer4, "C257@13714L391,241@12926L1201:OutlinedTextField.kt#uh7d8r");
                                                if ((i53 & 6) == 0) {
                                                    i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                                                } else {
                                                    i54 = i53;
                                                }
                                                if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(1474611661, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:241)");
                                                    }
                                                    OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                                    int i55 = i54;
                                                    String str5 = str4;
                                                    boolean z24 = z21;
                                                    boolean z25 = z22;
                                                    VisualTransformation visualTransformation6 = visualTransformation5;
                                                    MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                                    boolean z26 = z23;
                                                    Function2<Composer, Integer, Unit> function235 = function2272;
                                                    Function2<Composer, Integer, Unit> function236 = function228;
                                                    Function2<Composer, Integer, Unit> function237 = function229;
                                                    Function2<Composer, Integer, Unit> function238 = function230;
                                                    Function2<Composer, Integer, Unit> function239 = function231;
                                                    Function2<Composer, Integer, Unit> function240 = function232;
                                                    Function2<Composer, Integer, Unit> function241 = function233;
                                                    TextFieldColors textFieldColors5 = textFieldColors4;
                                                    final boolean z27 = z21;
                                                    final boolean z28 = z23;
                                                    final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                                    final TextFieldColors textFieldColors6 = textFieldColors4;
                                                    final Shape shape6 = shape5;
                                                    outlinedTextFieldDefaults.DecorationBox(str5, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(2108828640, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.1.3.1
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(2);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                                            invoke(composer5, num.intValue());
                                                            return Unit.INSTANCE;
                                                        }

                                                        public final void invoke(Composer composer5, int i56) {
                                                            ComposerKt.sourceInformation(composer5, "C258@13770L309:OutlinedTextField.kt#uh7d8r");
                                                            if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventStart(2108828640, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:258)");
                                                                }
                                                                OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventEnd();
                                                                    return;
                                                                }
                                                                return;
                                                            }
                                                            composer5.skipToGroupEnd();
                                                        }
                                                    }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer4.skipToGroupEnd();
                                            }
                                        }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            textStyle4 = textStyle3;
                            composer2 = startRestartGroup;
                            function216 = function28;
                            z10 = z8;
                            z11 = z162;
                            z12 = z9;
                            keyboardOptions3 = keyboardOptions2;
                            keyboardActions3 = keyboardActions2;
                            z13 = z7;
                            i41 = i37;
                            i42 = i38;
                            visualTransformation3 = visualTransformation2;
                            function217 = function215;
                            function218 = function211;
                            function219 = function212;
                            function220 = function213;
                            shape3 = shape2;
                            mutableInteractionSource4 = mutableInteractionSource2;
                            textFieldColors3 = textFieldColors2;
                            function221 = function214;
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        i15 = i14;
                        i16 = i6 & 1024;
                        if (i16 == 0) {
                        }
                        i19 = i6 & 2048;
                        if (i19 == 0) {
                        }
                        int i452 = i18;
                        i21 = i7;
                        i22 = i6 & 4096;
                        if (i22 == 0) {
                        }
                        i24 = i6 & 8192;
                        if (i24 == 0) {
                        }
                        i26 = i6 & 16384;
                        if (i26 == 0) {
                        }
                        i28 = i6 & 32768;
                        if (i28 != 0) {
                        }
                        i29 = i6 & 65536;
                        if (i29 != 0) {
                        }
                        i30 = i6 & 131072;
                        if (i30 != 0) {
                        }
                        if ((i4 & 100663296) == 0) {
                        }
                        i31 = i6 & 524288;
                        if (i31 != 0) {
                        }
                        i32 = i6 & 1048576;
                        if (i32 != 0) {
                        }
                        if ((i5 & 48) == 0) {
                        }
                        if ((i5 & 384) == 0) {
                        }
                        int i4822 = i33;
                        if ((i21 & 306783379) == 306783378) {
                        }
                        startRestartGroup.startDefaults();
                        if ((i3 & 1) != 0) {
                        }
                        if (i44 != 0) {
                        }
                        if (i8 != 0) {
                        }
                        if (i9 != 0) {
                        }
                        if ((i6 & 32) != 0) {
                        }
                        if (i10 != 0) {
                        }
                        if (i11 != 0) {
                        }
                        if (i13 != 0) {
                        }
                        if (i15 != 0) {
                        }
                        if (i17 != 0) {
                        }
                        if (i20 != 0) {
                        }
                        if (i22 != 0) {
                        }
                        if (i24 != 0) {
                        }
                        if (i26 != 0) {
                        }
                        if (i28 != 0) {
                        }
                        if (i29 != 0) {
                        }
                        if (i30 != 0) {
                        }
                        if ((i6 & 262144) != 0) {
                        }
                        if (i31 != 0) {
                        }
                        if (i32 == 0) {
                        }
                        if ((i6 & 2097152) != 0) {
                        }
                        MutableInteractionSource mutableInteractionSource622 = mutableInteractionSource5;
                        if ((i6 & 4194304) != 0) {
                        }
                        visualTransformation2 = none;
                        i39 = i34;
                        function215 = function29;
                        z8 = z14;
                        z9 = z6;
                        i40 = 0;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.startReplaceGroup(30062948);
                        ComposerKt.sourceInformation(startRestartGroup, "195@10782L39");
                        if (mutableInteractionSource2 == null) {
                        }
                        startRestartGroup.endReplaceGroup();
                        startRestartGroup.startReplaceGroup(30069058);
                        ComposerKt.sourceInformation(startRestartGroup, "*199@11006L25");
                        m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
                        if (m6878getColor0d7_KjU == 16) {
                        }
                        startRestartGroup.endReplaceGroup();
                        final TextStyle merge22 = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                        ProvidableCompositionLocal<Density> localDensity22 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object consume222 = startRestartGroup.consume(localDensity22);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        final Density density22 = (Density) consume222;
                        final boolean z1622 = z5;
                        CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-1886965181, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i50) {
                                Modifier.Companion companion;
                                ComposerKt.sourceInformation(composer3, "C222@12041L38,240@12860L1285,207@11318L2837:OutlinedTextField.kt#uh7d8r");
                                if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1886965181, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:207)");
                                    }
                                    Modifier modifier52 = Modifier.this;
                                    if (function28 != null) {
                                        companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.1
                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }
                                        }), 0.0f, density22.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                                    } else {
                                        companion = Modifier.INSTANCE;
                                    }
                                    Modifier then = modifier52.then(companion);
                                    boolean z17 = z8;
                                    Strings.Companion companion2 = Strings.INSTANCE;
                                    Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                                    SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                                    String str3 = str2;
                                    Function1<String, Unit> function13 = function12;
                                    boolean z18 = z1622;
                                    boolean z19 = z9;
                                    TextStyle textStyle5 = merge22;
                                    KeyboardOptions keyboardOptions5 = keyboardOptions2;
                                    KeyboardActions keyboardActions5 = keyboardActions2;
                                    boolean z20 = z7;
                                    int i51 = i37;
                                    int i52 = i38;
                                    VisualTransformation visualTransformation4 = visualTransformation2;
                                    MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                                    SolidColor solidColor2 = solidColor;
                                    final String str4 = str2;
                                    final boolean z21 = z1622;
                                    final boolean z22 = z7;
                                    final VisualTransformation visualTransformation5 = visualTransformation2;
                                    final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                                    final boolean z23 = z8;
                                    final Function2<? super Composer, ? super Integer, Unit> function2272 = function28;
                                    final Function2<? super Composer, ? super Integer, Unit> function228 = function215;
                                    final Function2<? super Composer, ? super Integer, Unit> function229 = function210;
                                    final Function2<? super Composer, ? super Integer, Unit> function230 = function211;
                                    final Function2<? super Composer, ? super Integer, Unit> function231 = function212;
                                    final Function2<? super Composer, ? super Integer, Unit> function232 = function213;
                                    final Function2<? super Composer, ? super Integer, Unit> function233 = function214;
                                    final TextFieldColors textFieldColors4 = textFieldColors2;
                                    final Shape shape5 = shape2;
                                    BasicTextFieldKt.BasicTextField(str3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(1474611661, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.3
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                                            invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                                            int i54;
                                            ComposerKt.sourceInformation(composer4, "C257@13714L391,241@12926L1201:OutlinedTextField.kt#uh7d8r");
                                            if ((i53 & 6) == 0) {
                                                i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                                            } else {
                                                i54 = i53;
                                            }
                                            if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1474611661, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:241)");
                                                }
                                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                                int i55 = i54;
                                                String str5 = str4;
                                                boolean z24 = z21;
                                                boolean z25 = z22;
                                                VisualTransformation visualTransformation6 = visualTransformation5;
                                                MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                                boolean z26 = z23;
                                                Function2<Composer, Integer, Unit> function235 = function2272;
                                                Function2<Composer, Integer, Unit> function236 = function228;
                                                Function2<Composer, Integer, Unit> function237 = function229;
                                                Function2<Composer, Integer, Unit> function238 = function230;
                                                Function2<Composer, Integer, Unit> function239 = function231;
                                                Function2<Composer, Integer, Unit> function240 = function232;
                                                Function2<Composer, Integer, Unit> function241 = function233;
                                                TextFieldColors textFieldColors5 = textFieldColors4;
                                                final boolean z27 = z21;
                                                final boolean z28 = z23;
                                                final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                                final TextFieldColors textFieldColors6 = textFieldColors4;
                                                final Shape shape6 = shape5;
                                                outlinedTextFieldDefaults.DecorationBox(str5, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(2108828640, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.1.3.1
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                                        invoke(composer5, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(Composer composer5, int i56) {
                                                        ComposerKt.sourceInformation(composer5, "C258@13770L309:OutlinedTextField.kt#uh7d8r");
                                                        if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(2108828640, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:258)");
                                                            }
                                                            OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventEnd();
                                                                return;
                                                            }
                                                            return;
                                                        }
                                                        composer5.skipToGroupEnd();
                                                    }
                                                }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer4.skipToGroupEnd();
                                        }
                                    }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        textStyle4 = textStyle3;
                        composer2 = startRestartGroup;
                        function216 = function28;
                        z10 = z8;
                        z11 = z1622;
                        z12 = z9;
                        keyboardOptions3 = keyboardOptions2;
                        keyboardActions3 = keyboardActions2;
                        z13 = z7;
                        i41 = i37;
                        i42 = i38;
                        visualTransformation3 = visualTransformation2;
                        function217 = function215;
                        function218 = function211;
                        function219 = function212;
                        function220 = function213;
                        shape3 = shape2;
                        mutableInteractionSource4 = mutableInteractionSource2;
                        textFieldColors3 = textFieldColors2;
                        function221 = function214;
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    i13 = i12;
                    i14 = i6 & 512;
                    if (i14 != 0) {
                    }
                    i15 = i14;
                    i16 = i6 & 1024;
                    if (i16 == 0) {
                    }
                    i19 = i6 & 2048;
                    if (i19 == 0) {
                    }
                    int i4522 = i18;
                    i21 = i7;
                    i22 = i6 & 4096;
                    if (i22 == 0) {
                    }
                    i24 = i6 & 8192;
                    if (i24 == 0) {
                    }
                    i26 = i6 & 16384;
                    if (i26 == 0) {
                    }
                    i28 = i6 & 32768;
                    if (i28 != 0) {
                    }
                    i29 = i6 & 65536;
                    if (i29 != 0) {
                    }
                    i30 = i6 & 131072;
                    if (i30 != 0) {
                    }
                    if ((i4 & 100663296) == 0) {
                    }
                    i31 = i6 & 524288;
                    if (i31 != 0) {
                    }
                    i32 = i6 & 1048576;
                    if (i32 != 0) {
                    }
                    if ((i5 & 48) == 0) {
                    }
                    if ((i5 & 384) == 0) {
                    }
                    int i48222 = i33;
                    if ((i21 & 306783379) == 306783378) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i3 & 1) != 0) {
                    }
                    if (i44 != 0) {
                    }
                    if (i8 != 0) {
                    }
                    if (i9 != 0) {
                    }
                    if ((i6 & 32) != 0) {
                    }
                    if (i10 != 0) {
                    }
                    if (i11 != 0) {
                    }
                    if (i13 != 0) {
                    }
                    if (i15 != 0) {
                    }
                    if (i17 != 0) {
                    }
                    if (i20 != 0) {
                    }
                    if (i22 != 0) {
                    }
                    if (i24 != 0) {
                    }
                    if (i26 != 0) {
                    }
                    if (i28 != 0) {
                    }
                    if (i29 != 0) {
                    }
                    if (i30 != 0) {
                    }
                    if ((i6 & 262144) != 0) {
                    }
                    if (i31 != 0) {
                    }
                    if (i32 == 0) {
                    }
                    if ((i6 & 2097152) != 0) {
                    }
                    MutableInteractionSource mutableInteractionSource6222 = mutableInteractionSource5;
                    if ((i6 & 4194304) != 0) {
                    }
                    visualTransformation2 = none;
                    i39 = i34;
                    function215 = function29;
                    z8 = z14;
                    z9 = z6;
                    i40 = 0;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.startReplaceGroup(30062948);
                    ComposerKt.sourceInformation(startRestartGroup, "195@10782L39");
                    if (mutableInteractionSource2 == null) {
                    }
                    startRestartGroup.endReplaceGroup();
                    startRestartGroup.startReplaceGroup(30069058);
                    ComposerKt.sourceInformation(startRestartGroup, "*199@11006L25");
                    m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
                    if (m6878getColor0d7_KjU == 16) {
                    }
                    startRestartGroup.endReplaceGroup();
                    final TextStyle merge222 = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                    ProvidableCompositionLocal<Density> localDensity222 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume2222 = startRestartGroup.consume(localDensity222);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    final Density density222 = (Density) consume2222;
                    final boolean z16222 = z5;
                    CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-1886965181, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i50) {
                            Modifier.Companion companion;
                            ComposerKt.sourceInformation(composer3, "C222@12041L38,240@12860L1285,207@11318L2837:OutlinedTextField.kt#uh7d8r");
                            if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1886965181, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:207)");
                                }
                                Modifier modifier52 = Modifier.this;
                                if (function28 != null) {
                                    companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.1
                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }
                                    }), 0.0f, density222.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                                } else {
                                    companion = Modifier.INSTANCE;
                                }
                                Modifier then = modifier52.then(companion);
                                boolean z17 = z8;
                                Strings.Companion companion2 = Strings.INSTANCE;
                                Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                                SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                                String str3 = str2;
                                Function1<String, Unit> function13 = function12;
                                boolean z18 = z16222;
                                boolean z19 = z9;
                                TextStyle textStyle5 = merge222;
                                KeyboardOptions keyboardOptions5 = keyboardOptions2;
                                KeyboardActions keyboardActions5 = keyboardActions2;
                                boolean z20 = z7;
                                int i51 = i37;
                                int i52 = i38;
                                VisualTransformation visualTransformation4 = visualTransformation2;
                                MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                                SolidColor solidColor2 = solidColor;
                                final String str4 = str2;
                                final boolean z21 = z16222;
                                final boolean z22 = z7;
                                final VisualTransformation visualTransformation5 = visualTransformation2;
                                final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                                final boolean z23 = z8;
                                final Function2<? super Composer, ? super Integer, Unit> function2272 = function28;
                                final Function2<? super Composer, ? super Integer, Unit> function228 = function215;
                                final Function2<? super Composer, ? super Integer, Unit> function229 = function210;
                                final Function2<? super Composer, ? super Integer, Unit> function230 = function211;
                                final Function2<? super Composer, ? super Integer, Unit> function231 = function212;
                                final Function2<? super Composer, ? super Integer, Unit> function232 = function213;
                                final Function2<? super Composer, ? super Integer, Unit> function233 = function214;
                                final TextFieldColors textFieldColors4 = textFieldColors2;
                                final Shape shape5 = shape2;
                                BasicTextFieldKt.BasicTextField(str3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(1474611661, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                                        invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                                        int i54;
                                        ComposerKt.sourceInformation(composer4, "C257@13714L391,241@12926L1201:OutlinedTextField.kt#uh7d8r");
                                        if ((i53 & 6) == 0) {
                                            i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                                        } else {
                                            i54 = i53;
                                        }
                                        if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1474611661, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:241)");
                                            }
                                            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                            int i55 = i54;
                                            String str5 = str4;
                                            boolean z24 = z21;
                                            boolean z25 = z22;
                                            VisualTransformation visualTransformation6 = visualTransformation5;
                                            MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                            boolean z26 = z23;
                                            Function2<Composer, Integer, Unit> function235 = function2272;
                                            Function2<Composer, Integer, Unit> function236 = function228;
                                            Function2<Composer, Integer, Unit> function237 = function229;
                                            Function2<Composer, Integer, Unit> function238 = function230;
                                            Function2<Composer, Integer, Unit> function239 = function231;
                                            Function2<Composer, Integer, Unit> function240 = function232;
                                            Function2<Composer, Integer, Unit> function241 = function233;
                                            TextFieldColors textFieldColors5 = textFieldColors4;
                                            final boolean z27 = z21;
                                            final boolean z28 = z23;
                                            final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                            final TextFieldColors textFieldColors6 = textFieldColors4;
                                            final Shape shape6 = shape5;
                                            outlinedTextFieldDefaults.DecorationBox(str5, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(2108828640, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.1.3.1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                                    invoke(composer5, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer5, int i56) {
                                                    ComposerKt.sourceInformation(composer5, "C258@13770L309:OutlinedTextField.kt#uh7d8r");
                                                    if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(2108828640, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:258)");
                                                        }
                                                        OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer5.skipToGroupEnd();
                                                }
                                            }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    textStyle4 = textStyle3;
                    composer2 = startRestartGroup;
                    function216 = function28;
                    z10 = z8;
                    z11 = z16222;
                    z12 = z9;
                    keyboardOptions3 = keyboardOptions2;
                    keyboardActions3 = keyboardActions2;
                    z13 = z7;
                    i41 = i37;
                    i42 = i38;
                    visualTransformation3 = visualTransformation2;
                    function217 = function215;
                    function218 = function211;
                    function219 = function212;
                    function220 = function213;
                    shape3 = shape2;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    textFieldColors3 = textFieldColors2;
                    function221 = function214;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                z6 = z2;
                if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                }
                i10 = i6 & 64;
                if (i10 == 0) {
                }
                i11 = i6 & 128;
                if (i11 == 0) {
                }
                i12 = i6 & 256;
                if (i12 == 0) {
                }
                i13 = i12;
                i14 = i6 & 512;
                if (i14 != 0) {
                }
                i15 = i14;
                i16 = i6 & 1024;
                if (i16 == 0) {
                }
                i19 = i6 & 2048;
                if (i19 == 0) {
                }
                int i45222 = i18;
                i21 = i7;
                i22 = i6 & 4096;
                if (i22 == 0) {
                }
                i24 = i6 & 8192;
                if (i24 == 0) {
                }
                i26 = i6 & 16384;
                if (i26 == 0) {
                }
                i28 = i6 & 32768;
                if (i28 != 0) {
                }
                i29 = i6 & 65536;
                if (i29 != 0) {
                }
                i30 = i6 & 131072;
                if (i30 != 0) {
                }
                if ((i4 & 100663296) == 0) {
                }
                i31 = i6 & 524288;
                if (i31 != 0) {
                }
                i32 = i6 & 1048576;
                if (i32 != 0) {
                }
                if ((i5 & 48) == 0) {
                }
                if ((i5 & 384) == 0) {
                }
                int i482222 = i33;
                if ((i21 & 306783379) == 306783378) {
                }
                startRestartGroup.startDefaults();
                if ((i3 & 1) != 0) {
                }
                if (i44 != 0) {
                }
                if (i8 != 0) {
                }
                if (i9 != 0) {
                }
                if ((i6 & 32) != 0) {
                }
                if (i10 != 0) {
                }
                if (i11 != 0) {
                }
                if (i13 != 0) {
                }
                if (i15 != 0) {
                }
                if (i17 != 0) {
                }
                if (i20 != 0) {
                }
                if (i22 != 0) {
                }
                if (i24 != 0) {
                }
                if (i26 != 0) {
                }
                if (i28 != 0) {
                }
                if (i29 != 0) {
                }
                if (i30 != 0) {
                }
                if ((i6 & 262144) != 0) {
                }
                if (i31 != 0) {
                }
                if (i32 == 0) {
                }
                if ((i6 & 2097152) != 0) {
                }
                MutableInteractionSource mutableInteractionSource62222 = mutableInteractionSource5;
                if ((i6 & 4194304) != 0) {
                }
                visualTransformation2 = none;
                i39 = i34;
                function215 = function29;
                z8 = z14;
                z9 = z6;
                i40 = 0;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.startReplaceGroup(30062948);
                ComposerKt.sourceInformation(startRestartGroup, "195@10782L39");
                if (mutableInteractionSource2 == null) {
                }
                startRestartGroup.endReplaceGroup();
                startRestartGroup.startReplaceGroup(30069058);
                ComposerKt.sourceInformation(startRestartGroup, "*199@11006L25");
                m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
                if (m6878getColor0d7_KjU == 16) {
                }
                startRestartGroup.endReplaceGroup();
                final TextStyle merge2222 = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                ProvidableCompositionLocal<Density> localDensity2222 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume22222 = startRestartGroup.consume(localDensity2222);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                final Density density2222 = (Density) consume22222;
                final boolean z162222 = z5;
                CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-1886965181, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i50) {
                        Modifier.Companion companion;
                        ComposerKt.sourceInformation(composer3, "C222@12041L38,240@12860L1285,207@11318L2837:OutlinedTextField.kt#uh7d8r");
                        if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1886965181, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:207)");
                            }
                            Modifier modifier52 = Modifier.this;
                            if (function28 != null) {
                                companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.1
                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }
                                }), 0.0f, density2222.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                            } else {
                                companion = Modifier.INSTANCE;
                            }
                            Modifier then = modifier52.then(companion);
                            boolean z17 = z8;
                            Strings.Companion companion2 = Strings.INSTANCE;
                            Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                            SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                            String str3 = str2;
                            Function1<String, Unit> function13 = function12;
                            boolean z18 = z162222;
                            boolean z19 = z9;
                            TextStyle textStyle5 = merge2222;
                            KeyboardOptions keyboardOptions5 = keyboardOptions2;
                            KeyboardActions keyboardActions5 = keyboardActions2;
                            boolean z20 = z7;
                            int i51 = i37;
                            int i52 = i38;
                            VisualTransformation visualTransformation4 = visualTransformation2;
                            MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                            SolidColor solidColor2 = solidColor;
                            final String str4 = str2;
                            final boolean z21 = z162222;
                            final boolean z22 = z7;
                            final VisualTransformation visualTransformation5 = visualTransformation2;
                            final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                            final boolean z23 = z8;
                            final Function2<? super Composer, ? super Integer, Unit> function2272 = function28;
                            final Function2<? super Composer, ? super Integer, Unit> function228 = function215;
                            final Function2<? super Composer, ? super Integer, Unit> function229 = function210;
                            final Function2<? super Composer, ? super Integer, Unit> function230 = function211;
                            final Function2<? super Composer, ? super Integer, Unit> function231 = function212;
                            final Function2<? super Composer, ? super Integer, Unit> function232 = function213;
                            final Function2<? super Composer, ? super Integer, Unit> function233 = function214;
                            final TextFieldColors textFieldColors4 = textFieldColors2;
                            final Shape shape5 = shape2;
                            BasicTextFieldKt.BasicTextField(str3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(1474611661, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                                    invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                                    int i54;
                                    ComposerKt.sourceInformation(composer4, "C257@13714L391,241@12926L1201:OutlinedTextField.kt#uh7d8r");
                                    if ((i53 & 6) == 0) {
                                        i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                                    } else {
                                        i54 = i53;
                                    }
                                    if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1474611661, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:241)");
                                        }
                                        OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                        int i55 = i54;
                                        String str5 = str4;
                                        boolean z24 = z21;
                                        boolean z25 = z22;
                                        VisualTransformation visualTransformation6 = visualTransformation5;
                                        MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                        boolean z26 = z23;
                                        Function2<Composer, Integer, Unit> function235 = function2272;
                                        Function2<Composer, Integer, Unit> function236 = function228;
                                        Function2<Composer, Integer, Unit> function237 = function229;
                                        Function2<Composer, Integer, Unit> function238 = function230;
                                        Function2<Composer, Integer, Unit> function239 = function231;
                                        Function2<Composer, Integer, Unit> function240 = function232;
                                        Function2<Composer, Integer, Unit> function241 = function233;
                                        TextFieldColors textFieldColors5 = textFieldColors4;
                                        final boolean z27 = z21;
                                        final boolean z28 = z23;
                                        final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                        final TextFieldColors textFieldColors6 = textFieldColors4;
                                        final Shape shape6 = shape5;
                                        outlinedTextFieldDefaults.DecorationBox(str5, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(2108828640, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.1.3.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                                invoke(composer5, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer5, int i56) {
                                                ComposerKt.sourceInformation(composer5, "C258@13770L309:OutlinedTextField.kt#uh7d8r");
                                                if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(2108828640, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:258)");
                                                    }
                                                    OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer5.skipToGroupEnd();
                                            }
                                        }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
                if (ComposerKt.isTraceInProgress()) {
                }
                textStyle4 = textStyle3;
                composer2 = startRestartGroup;
                function216 = function28;
                z10 = z8;
                z11 = z162222;
                z12 = z9;
                keyboardOptions3 = keyboardOptions2;
                keyboardActions3 = keyboardActions2;
                z13 = z7;
                i41 = i37;
                i42 = i38;
                visualTransformation3 = visualTransformation2;
                function217 = function215;
                function218 = function211;
                function219 = function212;
                function220 = function213;
                shape3 = shape2;
                mutableInteractionSource4 = mutableInteractionSource2;
                textFieldColors3 = textFieldColors2;
                function221 = function214;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            z5 = z;
            i9 = i6 & 16;
            if (i9 != 0) {
            }
            z6 = z2;
            if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
            }
            i10 = i6 & 64;
            if (i10 == 0) {
            }
            i11 = i6 & 128;
            if (i11 == 0) {
            }
            i12 = i6 & 256;
            if (i12 == 0) {
            }
            i13 = i12;
            i14 = i6 & 512;
            if (i14 != 0) {
            }
            i15 = i14;
            i16 = i6 & 1024;
            if (i16 == 0) {
            }
            i19 = i6 & 2048;
            if (i19 == 0) {
            }
            int i452222 = i18;
            i21 = i7;
            i22 = i6 & 4096;
            if (i22 == 0) {
            }
            i24 = i6 & 8192;
            if (i24 == 0) {
            }
            i26 = i6 & 16384;
            if (i26 == 0) {
            }
            i28 = i6 & 32768;
            if (i28 != 0) {
            }
            i29 = i6 & 65536;
            if (i29 != 0) {
            }
            i30 = i6 & 131072;
            if (i30 != 0) {
            }
            if ((i4 & 100663296) == 0) {
            }
            i31 = i6 & 524288;
            if (i31 != 0) {
            }
            i32 = i6 & 1048576;
            if (i32 != 0) {
            }
            if ((i5 & 48) == 0) {
            }
            if ((i5 & 384) == 0) {
            }
            int i4822222 = i33;
            if ((i21 & 306783379) == 306783378) {
            }
            startRestartGroup.startDefaults();
            if ((i3 & 1) != 0) {
            }
            if (i44 != 0) {
            }
            if (i8 != 0) {
            }
            if (i9 != 0) {
            }
            if ((i6 & 32) != 0) {
            }
            if (i10 != 0) {
            }
            if (i11 != 0) {
            }
            if (i13 != 0) {
            }
            if (i15 != 0) {
            }
            if (i17 != 0) {
            }
            if (i20 != 0) {
            }
            if (i22 != 0) {
            }
            if (i24 != 0) {
            }
            if (i26 != 0) {
            }
            if (i28 != 0) {
            }
            if (i29 != 0) {
            }
            if (i30 != 0) {
            }
            if ((i6 & 262144) != 0) {
            }
            if (i31 != 0) {
            }
            if (i32 == 0) {
            }
            if ((i6 & 2097152) != 0) {
            }
            MutableInteractionSource mutableInteractionSource622222 = mutableInteractionSource5;
            if ((i6 & 4194304) != 0) {
            }
            visualTransformation2 = none;
            i39 = i34;
            function215 = function29;
            z8 = z14;
            z9 = z6;
            i40 = 0;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.startReplaceGroup(30062948);
            ComposerKt.sourceInformation(startRestartGroup, "195@10782L39");
            if (mutableInteractionSource2 == null) {
            }
            startRestartGroup.endReplaceGroup();
            startRestartGroup.startReplaceGroup(30069058);
            ComposerKt.sourceInformation(startRestartGroup, "*199@11006L25");
            m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
            if (m6878getColor0d7_KjU == 16) {
            }
            startRestartGroup.endReplaceGroup();
            final TextStyle merge22222 = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            ProvidableCompositionLocal<Density> localDensity22222 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume222222 = startRestartGroup.consume(localDensity22222);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final Density density22222 = (Density) consume222222;
            final boolean z1622222 = z5;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-1886965181, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i50) {
                    Modifier.Companion companion;
                    ComposerKt.sourceInformation(composer3, "C222@12041L38,240@12860L1285,207@11318L2837:OutlinedTextField.kt#uh7d8r");
                    if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1886965181, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:207)");
                        }
                        Modifier modifier52 = Modifier.this;
                        if (function28 != null) {
                            companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.1
                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }
                            }), 0.0f, density22222.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                        } else {
                            companion = Modifier.INSTANCE;
                        }
                        Modifier then = modifier52.then(companion);
                        boolean z17 = z8;
                        Strings.Companion companion2 = Strings.INSTANCE;
                        Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                        SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                        String str3 = str2;
                        Function1<String, Unit> function13 = function12;
                        boolean z18 = z1622222;
                        boolean z19 = z9;
                        TextStyle textStyle5 = merge22222;
                        KeyboardOptions keyboardOptions5 = keyboardOptions2;
                        KeyboardActions keyboardActions5 = keyboardActions2;
                        boolean z20 = z7;
                        int i51 = i37;
                        int i52 = i38;
                        VisualTransformation visualTransformation4 = visualTransformation2;
                        MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                        SolidColor solidColor2 = solidColor;
                        final String str4 = str2;
                        final boolean z21 = z1622222;
                        final boolean z22 = z7;
                        final VisualTransformation visualTransformation5 = visualTransformation2;
                        final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                        final boolean z23 = z8;
                        final Function2<? super Composer, ? super Integer, Unit> function2272 = function28;
                        final Function2<? super Composer, ? super Integer, Unit> function228 = function215;
                        final Function2<? super Composer, ? super Integer, Unit> function229 = function210;
                        final Function2<? super Composer, ? super Integer, Unit> function230 = function211;
                        final Function2<? super Composer, ? super Integer, Unit> function231 = function212;
                        final Function2<? super Composer, ? super Integer, Unit> function232 = function213;
                        final Function2<? super Composer, ? super Integer, Unit> function233 = function214;
                        final TextFieldColors textFieldColors4 = textFieldColors2;
                        final Shape shape5 = shape2;
                        BasicTextFieldKt.BasicTextField(str3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(1474611661, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                                invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                                int i54;
                                ComposerKt.sourceInformation(composer4, "C257@13714L391,241@12926L1201:OutlinedTextField.kt#uh7d8r");
                                if ((i53 & 6) == 0) {
                                    i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                                } else {
                                    i54 = i53;
                                }
                                if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1474611661, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:241)");
                                    }
                                    OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                    int i55 = i54;
                                    String str5 = str4;
                                    boolean z24 = z21;
                                    boolean z25 = z22;
                                    VisualTransformation visualTransformation6 = visualTransformation5;
                                    MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                    boolean z26 = z23;
                                    Function2<Composer, Integer, Unit> function235 = function2272;
                                    Function2<Composer, Integer, Unit> function236 = function228;
                                    Function2<Composer, Integer, Unit> function237 = function229;
                                    Function2<Composer, Integer, Unit> function238 = function230;
                                    Function2<Composer, Integer, Unit> function239 = function231;
                                    Function2<Composer, Integer, Unit> function240 = function232;
                                    Function2<Composer, Integer, Unit> function241 = function233;
                                    TextFieldColors textFieldColors5 = textFieldColors4;
                                    final boolean z27 = z21;
                                    final boolean z28 = z23;
                                    final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                    final TextFieldColors textFieldColors6 = textFieldColors4;
                                    final Shape shape6 = shape5;
                                    outlinedTextFieldDefaults.DecorationBox(str5, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(2108828640, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.1.3.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                            invoke(composer5, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer5, int i56) {
                                            ComposerKt.sourceInformation(composer5, "C258@13770L309:OutlinedTextField.kt#uh7d8r");
                                            if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(2108828640, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:258)");
                                                }
                                                OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer5.skipToGroupEnd();
                                        }
                                    }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer4.skipToGroupEnd();
                            }
                        }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
            }
            textStyle4 = textStyle3;
            composer2 = startRestartGroup;
            function216 = function28;
            z10 = z8;
            z11 = z1622222;
            z12 = z9;
            keyboardOptions3 = keyboardOptions2;
            keyboardActions3 = keyboardActions2;
            z13 = z7;
            i41 = i37;
            i42 = i38;
            visualTransformation3 = visualTransformation2;
            function217 = function215;
            function218 = function211;
            function219 = function212;
            function220 = function213;
            shape3 = shape2;
            mutableInteractionSource4 = mutableInteractionSource2;
            textFieldColors3 = textFieldColors2;
            function221 = function214;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i8 = i6 & 8;
        if (i8 == 0) {
        }
        z5 = z;
        i9 = i6 & 16;
        if (i9 != 0) {
        }
        z6 = z2;
        if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
        }
        i10 = i6 & 64;
        if (i10 == 0) {
        }
        i11 = i6 & 128;
        if (i11 == 0) {
        }
        i12 = i6 & 256;
        if (i12 == 0) {
        }
        i13 = i12;
        i14 = i6 & 512;
        if (i14 != 0) {
        }
        i15 = i14;
        i16 = i6 & 1024;
        if (i16 == 0) {
        }
        i19 = i6 & 2048;
        if (i19 == 0) {
        }
        int i4522222 = i18;
        i21 = i7;
        i22 = i6 & 4096;
        if (i22 == 0) {
        }
        i24 = i6 & 8192;
        if (i24 == 0) {
        }
        i26 = i6 & 16384;
        if (i26 == 0) {
        }
        i28 = i6 & 32768;
        if (i28 != 0) {
        }
        i29 = i6 & 65536;
        if (i29 != 0) {
        }
        i30 = i6 & 131072;
        if (i30 != 0) {
        }
        if ((i4 & 100663296) == 0) {
        }
        i31 = i6 & 524288;
        if (i31 != 0) {
        }
        i32 = i6 & 1048576;
        if (i32 != 0) {
        }
        if ((i5 & 48) == 0) {
        }
        if ((i5 & 384) == 0) {
        }
        int i48222222 = i33;
        if ((i21 & 306783379) == 306783378) {
        }
        startRestartGroup.startDefaults();
        if ((i3 & 1) != 0) {
        }
        if (i44 != 0) {
        }
        if (i8 != 0) {
        }
        if (i9 != 0) {
        }
        if ((i6 & 32) != 0) {
        }
        if (i10 != 0) {
        }
        if (i11 != 0) {
        }
        if (i13 != 0) {
        }
        if (i15 != 0) {
        }
        if (i17 != 0) {
        }
        if (i20 != 0) {
        }
        if (i22 != 0) {
        }
        if (i24 != 0) {
        }
        if (i26 != 0) {
        }
        if (i28 != 0) {
        }
        if (i29 != 0) {
        }
        if (i30 != 0) {
        }
        if ((i6 & 262144) != 0) {
        }
        if (i31 != 0) {
        }
        if (i32 == 0) {
        }
        if ((i6 & 2097152) != 0) {
        }
        MutableInteractionSource mutableInteractionSource6222222 = mutableInteractionSource5;
        if ((i6 & 4194304) != 0) {
        }
        visualTransformation2 = none;
        i39 = i34;
        function215 = function29;
        z8 = z14;
        z9 = z6;
        i40 = 0;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.startReplaceGroup(30062948);
        ComposerKt.sourceInformation(startRestartGroup, "195@10782L39");
        if (mutableInteractionSource2 == null) {
        }
        startRestartGroup.endReplaceGroup();
        startRestartGroup.startReplaceGroup(30069058);
        ComposerKt.sourceInformation(startRestartGroup, "*199@11006L25");
        m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
        if (m6878getColor0d7_KjU == 16) {
        }
        startRestartGroup.endReplaceGroup();
        final TextStyle merge222222 = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
        ProvidableCompositionLocal<Density> localDensity222222 = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2222222 = startRestartGroup.consume(localDensity222222);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        final Density density222222 = (Density) consume2222222;
        final boolean z16222222 = z5;
        CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-1886965181, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                invoke(composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer3, int i50) {
                Modifier.Companion companion;
                ComposerKt.sourceInformation(composer3, "C222@12041L38,240@12860L1285,207@11318L2837:OutlinedTextField.kt#uh7d8r");
                if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1886965181, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:207)");
                    }
                    Modifier modifier52 = Modifier.this;
                    if (function28 != null) {
                        companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.1
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }
                        }), 0.0f, density222222.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                    } else {
                        companion = Modifier.INSTANCE;
                    }
                    Modifier then = modifier52.then(companion);
                    boolean z17 = z8;
                    Strings.Companion companion2 = Strings.INSTANCE;
                    Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                    SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                    String str3 = str2;
                    Function1<String, Unit> function13 = function12;
                    boolean z18 = z16222222;
                    boolean z19 = z9;
                    TextStyle textStyle5 = merge222222;
                    KeyboardOptions keyboardOptions5 = keyboardOptions2;
                    KeyboardActions keyboardActions5 = keyboardActions2;
                    boolean z20 = z7;
                    int i51 = i37;
                    int i52 = i38;
                    VisualTransformation visualTransformation4 = visualTransformation2;
                    MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                    SolidColor solidColor2 = solidColor;
                    final String str4 = str2;
                    final boolean z21 = z16222222;
                    final boolean z22 = z7;
                    final VisualTransformation visualTransformation5 = visualTransformation2;
                    final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                    final boolean z23 = z8;
                    final Function2<? super Composer, ? super Integer, Unit> function2272 = function28;
                    final Function2<? super Composer, ? super Integer, Unit> function228 = function215;
                    final Function2<? super Composer, ? super Integer, Unit> function229 = function210;
                    final Function2<? super Composer, ? super Integer, Unit> function230 = function211;
                    final Function2<? super Composer, ? super Integer, Unit> function231 = function212;
                    final Function2<? super Composer, ? super Integer, Unit> function232 = function213;
                    final Function2<? super Composer, ? super Integer, Unit> function233 = function214;
                    final TextFieldColors textFieldColors4 = textFieldColors2;
                    final Shape shape5 = shape2;
                    BasicTextFieldKt.BasicTextField(str3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(1474611661, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                            int i54;
                            ComposerKt.sourceInformation(composer4, "C257@13714L391,241@12926L1201:OutlinedTextField.kt#uh7d8r");
                            if ((i53 & 6) == 0) {
                                i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                            } else {
                                i54 = i53;
                            }
                            if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1474611661, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:241)");
                                }
                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                int i55 = i54;
                                String str5 = str4;
                                boolean z24 = z21;
                                boolean z25 = z22;
                                VisualTransformation visualTransformation6 = visualTransformation5;
                                MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                boolean z26 = z23;
                                Function2<Composer, Integer, Unit> function235 = function2272;
                                Function2<Composer, Integer, Unit> function236 = function228;
                                Function2<Composer, Integer, Unit> function237 = function229;
                                Function2<Composer, Integer, Unit> function238 = function230;
                                Function2<Composer, Integer, Unit> function239 = function231;
                                Function2<Composer, Integer, Unit> function240 = function232;
                                Function2<Composer, Integer, Unit> function241 = function233;
                                TextFieldColors textFieldColors5 = textFieldColors4;
                                final boolean z27 = z21;
                                final boolean z28 = z23;
                                final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                final TextFieldColors textFieldColors6 = textFieldColors4;
                                final Shape shape6 = shape5;
                                outlinedTextFieldDefaults.DecorationBox(str5, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(2108828640, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.1.3.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i56) {
                                        ComposerKt.sourceInformation(composer5, "C258@13770L309:OutlinedTextField.kt#uh7d8r");
                                        if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(2108828640, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:258)");
                                            }
                                            OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer5.skipToGroupEnd();
                                    }
                                }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer4.skipToGroupEnd();
                        }
                    }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer3.skipToGroupEnd();
            }
        }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
        if (ComposerKt.isTraceInProgress()) {
        }
        textStyle4 = textStyle3;
        composer2 = startRestartGroup;
        function216 = function28;
        z10 = z8;
        z11 = z16222222;
        z12 = z9;
        keyboardOptions3 = keyboardOptions2;
        keyboardActions3 = keyboardActions2;
        z13 = z7;
        i41 = i37;
        i42 = i38;
        visualTransformation3 = visualTransformation2;
        function217 = function215;
        function218 = function211;
        function219 = function212;
        function220 = function213;
        shape3 = shape2;
        mutableInteractionSource4 = mutableInteractionSource2;
        textFieldColors3 = textFieldColors2;
        function221 = function214;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x05be  */
    /* JADX WARN: Removed duplicated region for block: B:110:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x04a1  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x04f4  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0393  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x03c4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x03f8  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0419  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x043c  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x03f4  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x03d4  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void OutlinedTextField(final TextFieldValue textFieldValue, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, Composer composer, final int i3, final int i4, final int i5, final int i6) {
        final TextFieldValue textFieldValue2;
        int i7;
        final Function1<? super TextFieldValue, Unit> function12;
        Modifier modifier2;
        int i8;
        boolean z5;
        int i9;
        boolean z6;
        TextStyle textStyle2;
        int i10;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        int i11;
        Function2<? super Composer, ? super Integer, Unit> function29;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        int i28;
        int i29;
        int i30;
        int i31;
        int i32;
        int i33;
        int i34;
        int i35;
        final Shape shape2;
        TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function210;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function211;
        int i36;
        final Function2<? super Composer, ? super Integer, Unit> function212;
        final KeyboardOptions keyboardOptions2;
        final Function2<? super Composer, ? super Integer, Unit> function213;
        final KeyboardActions keyboardActions2;
        final Function2<? super Composer, ? super Integer, Unit> function214;
        final boolean z7;
        final int i37;
        final int i38;
        MutableInteractionSource mutableInteractionSource2;
        final TextFieldColors textFieldColors2;
        final VisualTransformation visualTransformation2;
        int i39;
        final Function2<? super Composer, ? super Integer, Unit> function215;
        final boolean z8;
        final boolean z9;
        int i40;
        final MutableInteractionSource mutableInteractionSource3;
        long m6878getColor0d7_KjU;
        final TextStyle textStyle4;
        Composer composer2;
        final Function2<? super Composer, ? super Integer, Unit> function216;
        final boolean z10;
        final boolean z11;
        final boolean z12;
        final KeyboardOptions keyboardOptions3;
        final KeyboardActions keyboardActions3;
        final boolean z13;
        final int i41;
        final int i42;
        final VisualTransformation visualTransformation3;
        final Function2<? super Composer, ? super Integer, Unit> function217;
        final Function2<? super Composer, ? super Integer, Unit> function218;
        final Function2<? super Composer, ? super Integer, Unit> function219;
        final Function2<? super Composer, ? super Integer, Unit> function220;
        final Shape shape3;
        final MutableInteractionSource mutableInteractionSource4;
        final TextFieldColors textFieldColors3;
        final Function2<? super Composer, ? super Integer, Unit> function221;
        ScopeUpdateScope endRestartGroup;
        int i43;
        Composer startRestartGroup = composer.startRestartGroup(-1570442800);
        ComposerKt.sourceInformation(startRestartGroup, "C(OutlinedTextField)P(21,11,10,1,14,19,6,12,7,20,13,17,18,3,22,5,4,16,8,9,2,15)350@19364L7,366@20168L5,367@20231L8,379@20751L7,381@20851L2858,381@20764L2945:OutlinedTextField.kt#uh7d8r");
        if ((i6 & 1) != 0) {
            i7 = i3 | 6;
            textFieldValue2 = textFieldValue;
        } else {
            textFieldValue2 = textFieldValue;
            if ((i3 & 6) == 0) {
                i7 = (startRestartGroup.changed(textFieldValue2) ? 4 : 2) | i3;
            } else {
                i7 = i3;
            }
        }
        if ((i6 & 2) != 0) {
            i7 |= 48;
            function12 = function1;
        } else {
            function12 = function1;
            if ((i3 & 48) == 0) {
                i7 |= startRestartGroup.changedInstance(function12) ? 32 : 16;
            }
        }
        int i44 = i6 & 4;
        if (i44 != 0) {
            i7 |= 384;
        } else if ((i3 & 384) == 0) {
            modifier2 = modifier;
            i7 |= startRestartGroup.changed(modifier2) ? 256 : 128;
            i8 = i6 & 8;
            if (i8 == 0) {
                i7 |= 3072;
            } else if ((i3 & 3072) == 0) {
                z5 = z;
                i7 |= startRestartGroup.changed(z5) ? 2048 : 1024;
                i9 = i6 & 16;
                if (i9 != 0) {
                    i7 |= 24576;
                } else if ((i3 & 24576) == 0) {
                    z6 = z2;
                    i7 |= startRestartGroup.changed(z6) ? 16384 : 8192;
                    if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                        textStyle2 = textStyle;
                        i7 |= ((i6 & 32) == 0 && startRestartGroup.changed(textStyle2)) ? 131072 : 65536;
                    } else {
                        textStyle2 = textStyle;
                    }
                    i10 = i6 & 64;
                    if (i10 == 0) {
                        i7 |= 1572864;
                        function28 = function2;
                    } else {
                        function28 = function2;
                        if ((i3 & 1572864) == 0) {
                            i7 |= startRestartGroup.changedInstance(function28) ? 1048576 : 524288;
                        }
                    }
                    i11 = i6 & 128;
                    if (i11 == 0) {
                        i7 |= 12582912;
                        function29 = function22;
                    } else {
                        function29 = function22;
                        if ((i3 & 12582912) == 0) {
                            i7 |= startRestartGroup.changedInstance(function29) ? 8388608 : 4194304;
                        }
                    }
                    i12 = i6 & 256;
                    if (i12 == 0) {
                        i7 |= 100663296;
                    } else if ((i3 & 100663296) == 0) {
                        i13 = i12;
                        i7 |= startRestartGroup.changedInstance(function23) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        i14 = i6 & 512;
                        if (i14 != 0) {
                            i7 |= 805306368;
                        } else if ((i3 & 805306368) == 0) {
                            i15 = i14;
                            i7 |= startRestartGroup.changedInstance(function24) ? 536870912 : 268435456;
                            i16 = i6 & 1024;
                            if (i16 == 0) {
                                i18 = i4 | 6;
                                i17 = i16;
                            } else if ((i4 & 6) == 0) {
                                i17 = i16;
                                i18 = i4 | (startRestartGroup.changedInstance(function25) ? 4 : 2);
                            } else {
                                i17 = i16;
                                i18 = i4;
                            }
                            i19 = i6 & 2048;
                            if (i19 == 0) {
                                i18 |= 48;
                                i20 = i19;
                            } else if ((i4 & 48) == 0) {
                                i20 = i19;
                                i18 |= startRestartGroup.changedInstance(function26) ? 32 : 16;
                            } else {
                                i20 = i19;
                            }
                            int i45 = i18;
                            i21 = i7;
                            i22 = i6 & 4096;
                            if (i22 == 0) {
                                i23 = i45 | 384;
                            } else {
                                int i46 = i45;
                                if ((i4 & 384) == 0) {
                                    i46 |= startRestartGroup.changedInstance(function27) ? 256 : 128;
                                }
                                i23 = i46;
                            }
                            i24 = i6 & 8192;
                            if (i24 == 0) {
                                i25 = i23 | 3072;
                            } else {
                                int i47 = i23;
                                if ((i4 & 3072) == 0) {
                                    i25 = i47 | (startRestartGroup.changed(z3) ? 2048 : 1024);
                                } else {
                                    i25 = i47;
                                }
                            }
                            i26 = i6 & 16384;
                            if (i26 == 0) {
                                i27 = i25 | 24576;
                            } else {
                                i27 = i25;
                                if ((i4 & 24576) == 0) {
                                    i27 |= startRestartGroup.changed(visualTransformation) ? 16384 : 8192;
                                    i28 = i6 & 32768;
                                    if (i28 != 0) {
                                        i27 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                                    } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                        i27 |= startRestartGroup.changed(keyboardOptions) ? 131072 : 65536;
                                    }
                                    i29 = i6 & 65536;
                                    if (i29 != 0) {
                                        i27 |= 1572864;
                                    } else if ((i4 & 1572864) == 0) {
                                        i27 |= startRestartGroup.changed(keyboardActions) ? 1048576 : 524288;
                                    }
                                    i30 = i6 & 131072;
                                    if (i30 != 0) {
                                        i27 |= 12582912;
                                    } else if ((i4 & 12582912) == 0) {
                                        i27 |= startRestartGroup.changed(z4) ? 8388608 : 4194304;
                                    }
                                    if ((i4 & 100663296) == 0) {
                                        i27 |= ((i6 & 262144) == 0 && startRestartGroup.changed(i)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                    }
                                    i31 = i6 & 524288;
                                    if (i31 != 0) {
                                        i27 |= 805306368;
                                    } else if ((i4 & 805306368) == 0) {
                                        i27 |= startRestartGroup.changed(i2) ? 536870912 : 268435456;
                                    }
                                    i32 = i6 & 1048576;
                                    if (i32 != 0) {
                                        i33 = i5 | 6;
                                    } else if ((i5 & 6) == 0) {
                                        i33 = i5 | (startRestartGroup.changed(mutableInteractionSource) ? 4 : 2);
                                    } else {
                                        i33 = i5;
                                    }
                                    if ((i5 & 48) == 0) {
                                        i33 |= ((i6 & 2097152) == 0 && startRestartGroup.changed(shape)) ? 32 : 16;
                                    }
                                    if ((i5 & 384) == 0) {
                                        if ((i6 & 4194304) == 0 && startRestartGroup.changed(textFieldColors)) {
                                            i43 = 256;
                                            i33 |= i43;
                                        }
                                        i43 = 128;
                                        i33 |= i43;
                                    }
                                    int i48 = i33;
                                    if ((i21 & 306783379) == 306783378 || (i27 & 306783379) != 306783378 || (i48 & 147) != 146 || !startRestartGroup.getSkipping()) {
                                        startRestartGroup.startDefaults();
                                        if ((i3 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                            if (i44 != 0) {
                                                modifier2 = Modifier.INSTANCE;
                                            }
                                            if (i8 != 0) {
                                                z5 = true;
                                            }
                                            if (i9 != 0) {
                                                z6 = false;
                                            }
                                            if ((i6 & 32) != 0) {
                                                ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                                Object consume = startRestartGroup.consume(localTextStyle);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                i34 = i21 & (-458753);
                                                textStyle2 = (TextStyle) consume;
                                            } else {
                                                i34 = i21;
                                            }
                                            if (i10 != 0) {
                                                function28 = null;
                                            }
                                            if (i11 != 0) {
                                                function29 = null;
                                            }
                                            Function2<? super Composer, ? super Integer, Unit> function222 = i13 != 0 ? null : function23;
                                            Function2<? super Composer, ? super Integer, Unit> function223 = i15 != 0 ? null : function24;
                                            Function2<? super Composer, ? super Integer, Unit> function224 = i17 != 0 ? null : function25;
                                            Function2<? super Composer, ? super Integer, Unit> function225 = i20 != 0 ? null : function26;
                                            Function2<? super Composer, ? super Integer, Unit> function226 = i22 != 0 ? null : function27;
                                            boolean z14 = i24 != 0 ? false : z3;
                                            VisualTransformation none = i26 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                                            KeyboardOptions keyboardOptions4 = i28 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                                            KeyboardActions keyboardActions4 = i29 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                                            boolean z15 = i30 != 0 ? false : z4;
                                            if ((i6 & 262144) != 0) {
                                                i35 = z15 ? 1 : Integer.MAX_VALUE;
                                                i27 &= -234881025;
                                            } else {
                                                i35 = i;
                                            }
                                            int i49 = i31 != 0 ? 1 : i2;
                                            MutableInteractionSource mutableInteractionSource5 = i32 == 0 ? mutableInteractionSource : null;
                                            Shape shape4 = (i6 & 2097152) != 0 ? OutlinedTextFieldDefaults.INSTANCE.getShape(startRestartGroup, 6) : shape;
                                            MutableInteractionSource mutableInteractionSource6 = mutableInteractionSource5;
                                            if ((i6 & 4194304) != 0) {
                                                Modifier modifier4 = modifier2;
                                                textFieldColors2 = OutlinedTextFieldDefaults.INSTANCE.colors(startRestartGroup, 6);
                                                textStyle3 = textStyle2;
                                                modifier3 = modifier4;
                                                shape2 = shape4;
                                                function210 = function222;
                                                function211 = function223;
                                                i36 = i27;
                                                function212 = function224;
                                                keyboardOptions2 = keyboardOptions4;
                                                function213 = function225;
                                                keyboardActions2 = keyboardActions4;
                                                function214 = function226;
                                                z7 = z15;
                                                i37 = i35;
                                                i38 = i49;
                                                mutableInteractionSource2 = mutableInteractionSource6;
                                            } else {
                                                shape2 = shape4;
                                                textStyle3 = textStyle2;
                                                function210 = function222;
                                                modifier3 = modifier2;
                                                function211 = function223;
                                                i36 = i27;
                                                function212 = function224;
                                                keyboardOptions2 = keyboardOptions4;
                                                function213 = function225;
                                                keyboardActions2 = keyboardActions4;
                                                function214 = function226;
                                                z7 = z15;
                                                i37 = i35;
                                                i38 = i49;
                                                mutableInteractionSource2 = mutableInteractionSource6;
                                                textFieldColors2 = textFieldColors;
                                            }
                                            visualTransformation2 = none;
                                            i39 = i34;
                                            function215 = function29;
                                            z8 = z14;
                                            z9 = z6;
                                            i40 = 0;
                                        } else {
                                            startRestartGroup.skipToGroupEnd();
                                            i39 = (i6 & 32) != 0 ? i21 & (-458753) : i21;
                                            if ((262144 & i6) != 0) {
                                                i27 &= -234881025;
                                            }
                                            function210 = function23;
                                            function211 = function24;
                                            function212 = function25;
                                            function213 = function26;
                                            function214 = function27;
                                            visualTransformation2 = visualTransformation;
                                            keyboardOptions2 = keyboardOptions;
                                            keyboardActions2 = keyboardActions;
                                            z7 = z4;
                                            i37 = i;
                                            i38 = i2;
                                            mutableInteractionSource2 = mutableInteractionSource;
                                            shape2 = shape;
                                            z9 = z6;
                                            textStyle3 = textStyle2;
                                            function215 = function29;
                                            modifier3 = modifier2;
                                            i36 = i27;
                                            i40 = 0;
                                            z8 = z3;
                                            textFieldColors2 = textFieldColors;
                                        }
                                        startRestartGroup.endDefaults();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1570442800, i39, i36, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:368)");
                                        }
                                        startRestartGroup.startReplaceGroup(30368324);
                                        ComposerKt.sourceInformation(startRestartGroup, "370@20325L39");
                                        if (mutableInteractionSource2 == null) {
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 30368975, "CC(remember):OutlinedTextField.kt#9igjgp");
                                            Object rememberedValue = startRestartGroup.rememberedValue();
                                            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue = InteractionSourceKt.MutableInteractionSource();
                                                startRestartGroup.updateRememberedValue(rememberedValue);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            mutableInteractionSource3 = (MutableInteractionSource) rememberedValue;
                                        } else {
                                            mutableInteractionSource3 = mutableInteractionSource2;
                                        }
                                        startRestartGroup.endReplaceGroup();
                                        startRestartGroup.startReplaceGroup(30374434);
                                        ComposerKt.sourceInformation(startRestartGroup, "*374@20549L25");
                                        m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
                                        if (m6878getColor0d7_KjU == 16) {
                                            m6878getColor0d7_KjU = textFieldColors2.m2818textColorXeAY9LY$material3_release(z5, z8, FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, startRestartGroup, i40).getValue().booleanValue());
                                        }
                                        startRestartGroup.endReplaceGroup();
                                        final TextStyle merge = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                        Object consume2 = startRestartGroup.consume(localDensity);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        final Density density = (Density) consume2;
                                        final boolean z16 = z5;
                                        CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1830921872, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                invoke(composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer3, int i50) {
                                                Modifier.Companion companion;
                                                ComposerKt.sourceInformation(composer3, "C397@21584L38,415@22403L1290,382@20861L2842:OutlinedTextField.kt#uh7d8r");
                                                if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(1830921872, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:382)");
                                                    }
                                                    Modifier modifier5 = Modifier.this;
                                                    if (function28 != null) {
                                                        companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.1
                                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                            }

                                                            @Override // kotlin.jvm.functions.Function1
                                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                                invoke2(semanticsPropertyReceiver);
                                                                return Unit.INSTANCE;
                                                            }
                                                        }), 0.0f, density.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                                                    } else {
                                                        companion = Modifier.INSTANCE;
                                                    }
                                                    Modifier then = modifier5.then(companion);
                                                    boolean z17 = z8;
                                                    Strings.Companion companion2 = Strings.INSTANCE;
                                                    Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                                                    SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                                                    TextFieldValue textFieldValue3 = textFieldValue2;
                                                    Function1<TextFieldValue, Unit> function13 = function12;
                                                    boolean z18 = z16;
                                                    boolean z19 = z9;
                                                    TextStyle textStyle5 = merge;
                                                    KeyboardOptions keyboardOptions5 = keyboardOptions2;
                                                    KeyboardActions keyboardActions5 = keyboardActions2;
                                                    boolean z20 = z7;
                                                    int i51 = i37;
                                                    int i52 = i38;
                                                    VisualTransformation visualTransformation4 = visualTransformation2;
                                                    MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                                                    SolidColor solidColor2 = solidColor;
                                                    final TextFieldValue textFieldValue4 = textFieldValue2;
                                                    final boolean z21 = z16;
                                                    final boolean z22 = z7;
                                                    final VisualTransformation visualTransformation5 = visualTransformation2;
                                                    final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                                                    final boolean z23 = z8;
                                                    final Function2<Composer, Integer, Unit> function227 = function28;
                                                    final Function2<Composer, Integer, Unit> function228 = function215;
                                                    final Function2<Composer, Integer, Unit> function229 = function210;
                                                    final Function2<Composer, Integer, Unit> function230 = function211;
                                                    final Function2<Composer, Integer, Unit> function231 = function212;
                                                    final Function2<Composer, Integer, Unit> function232 = function213;
                                                    final Function2<Composer, Integer, Unit> function233 = function214;
                                                    final TextFieldColors textFieldColors4 = textFieldColors2;
                                                    final Shape shape5 = shape2;
                                                    BasicTextFieldKt.BasicTextField(textFieldValue3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(-757328870, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.3
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        /* JADX WARN: Multi-variable type inference failed */
                                                        {
                                                            super(3);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function3
                                                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                                                            invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                                                            return Unit.INSTANCE;
                                                        }

                                                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                                                            int i54;
                                                            ComposerKt.sourceInformation(composer4, "C432@23262L391,416@22469L1206:OutlinedTextField.kt#uh7d8r");
                                                            if ((i53 & 6) == 0) {
                                                                i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                                                            } else {
                                                                i54 = i53;
                                                            }
                                                            if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventStart(-757328870, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:416)");
                                                                }
                                                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                                                String text = TextFieldValue.this.getText();
                                                                boolean z24 = z21;
                                                                int i55 = i54;
                                                                boolean z25 = z22;
                                                                VisualTransformation visualTransformation6 = visualTransformation5;
                                                                MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                                                boolean z26 = z23;
                                                                Function2<Composer, Integer, Unit> function235 = function227;
                                                                Function2<Composer, Integer, Unit> function236 = function228;
                                                                Function2<Composer, Integer, Unit> function237 = function229;
                                                                Function2<Composer, Integer, Unit> function238 = function230;
                                                                Function2<Composer, Integer, Unit> function239 = function231;
                                                                Function2<Composer, Integer, Unit> function240 = function232;
                                                                Function2<Composer, Integer, Unit> function241 = function233;
                                                                TextFieldColors textFieldColors5 = textFieldColors4;
                                                                final boolean z27 = z21;
                                                                final boolean z28 = z23;
                                                                final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                                                final TextFieldColors textFieldColors6 = textFieldColors4;
                                                                final Shape shape6 = shape5;
                                                                outlinedTextFieldDefaults.DecorationBox(text, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(255570733, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.3.1
                                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                    {
                                                                        super(2);
                                                                    }

                                                                    @Override // kotlin.jvm.functions.Function2
                                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                                                        invoke(composer5, num.intValue());
                                                                        return Unit.INSTANCE;
                                                                    }

                                                                    public final void invoke(Composer composer5, int i56) {
                                                                        ComposerKt.sourceInformation(composer5, "C433@23318L309:OutlinedTextField.kt#uh7d8r");
                                                                        if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                                ComposerKt.traceEventStart(255570733, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:433)");
                                                                            }
                                                                            OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                                ComposerKt.traceEventEnd();
                                                                                return;
                                                                            }
                                                                            return;
                                                                        }
                                                                        composer5.skipToGroupEnd();
                                                                    }
                                                                }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventEnd();
                                                                    return;
                                                                }
                                                                return;
                                                            }
                                                            composer4.skipToGroupEnd();
                                                        }
                                                    }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer3.skipToGroupEnd();
                                            }
                                        }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        textStyle4 = textStyle3;
                                        composer2 = startRestartGroup;
                                        function216 = function28;
                                        z10 = z8;
                                        z11 = z16;
                                        z12 = z9;
                                        keyboardOptions3 = keyboardOptions2;
                                        keyboardActions3 = keyboardActions2;
                                        z13 = z7;
                                        i41 = i37;
                                        i42 = i38;
                                        visualTransformation3 = visualTransformation2;
                                        function217 = function215;
                                        function218 = function211;
                                        function219 = function212;
                                        function220 = function213;
                                        shape3 = shape2;
                                        mutableInteractionSource4 = mutableInteractionSource2;
                                        textFieldColors3 = textFieldColors2;
                                        function221 = function214;
                                    } else {
                                        startRestartGroup.skipToGroupEnd();
                                        function210 = function23;
                                        function219 = function25;
                                        z10 = z3;
                                        visualTransformation3 = visualTransformation;
                                        keyboardOptions3 = keyboardOptions;
                                        keyboardActions3 = keyboardActions;
                                        z13 = z4;
                                        i41 = i;
                                        i42 = i2;
                                        mutableInteractionSource4 = mutableInteractionSource;
                                        shape3 = shape;
                                        textFieldColors3 = textFieldColors;
                                        composer2 = startRestartGroup;
                                        z11 = z5;
                                        z12 = z6;
                                        textStyle4 = textStyle2;
                                        function216 = function28;
                                        function217 = function29;
                                        modifier3 = modifier2;
                                        function218 = function24;
                                        function220 = function26;
                                        function221 = function27;
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup != null) {
                                        final Function2<? super Composer, ? super Integer, Unit> function227 = function210;
                                        final Modifier modifier5 = modifier3;
                                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$4
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                invoke(composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer3, int i50) {
                                                OutlinedTextFieldKt.OutlinedTextField(TextFieldValue.this, function1, modifier5, z11, z12, textStyle4, function216, function217, function227, function218, function219, function220, function221, z10, visualTransformation3, keyboardOptions3, keyboardActions3, z13, i41, i42, mutableInteractionSource4, shape3, textFieldColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
                                            }
                                        });
                                        return;
                                    }
                                    return;
                                }
                            }
                            i28 = i6 & 32768;
                            if (i28 != 0) {
                            }
                            i29 = i6 & 65536;
                            if (i29 != 0) {
                            }
                            i30 = i6 & 131072;
                            if (i30 != 0) {
                            }
                            if ((i4 & 100663296) == 0) {
                            }
                            i31 = i6 & 524288;
                            if (i31 != 0) {
                            }
                            i32 = i6 & 1048576;
                            if (i32 != 0) {
                            }
                            if ((i5 & 48) == 0) {
                            }
                            if ((i5 & 384) == 0) {
                            }
                            int i482 = i33;
                            if ((i21 & 306783379) == 306783378) {
                            }
                            startRestartGroup.startDefaults();
                            if ((i3 & 1) != 0) {
                            }
                            if (i44 != 0) {
                            }
                            if (i8 != 0) {
                            }
                            if (i9 != 0) {
                            }
                            if ((i6 & 32) != 0) {
                            }
                            if (i10 != 0) {
                            }
                            if (i11 != 0) {
                            }
                            if (i13 != 0) {
                            }
                            if (i15 != 0) {
                            }
                            if (i17 != 0) {
                            }
                            if (i20 != 0) {
                            }
                            if (i22 != 0) {
                            }
                            if (i24 != 0) {
                            }
                            if (i26 != 0) {
                            }
                            if (i28 != 0) {
                            }
                            if (i29 != 0) {
                            }
                            if (i30 != 0) {
                            }
                            if ((i6 & 262144) != 0) {
                            }
                            if (i31 != 0) {
                            }
                            if (i32 == 0) {
                            }
                            if ((i6 & 2097152) != 0) {
                            }
                            MutableInteractionSource mutableInteractionSource62 = mutableInteractionSource5;
                            if ((i6 & 4194304) != 0) {
                            }
                            visualTransformation2 = none;
                            i39 = i34;
                            function215 = function29;
                            z8 = z14;
                            z9 = z6;
                            i40 = 0;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.startReplaceGroup(30368324);
                            ComposerKt.sourceInformation(startRestartGroup, "370@20325L39");
                            if (mutableInteractionSource2 == null) {
                            }
                            startRestartGroup.endReplaceGroup();
                            startRestartGroup.startReplaceGroup(30374434);
                            ComposerKt.sourceInformation(startRestartGroup, "*374@20549L25");
                            m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
                            if (m6878getColor0d7_KjU == 16) {
                            }
                            startRestartGroup.endReplaceGroup();
                            final TextStyle merge2 = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object consume22 = startRestartGroup.consume(localDensity2);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            final Density density2 = (Density) consume22;
                            final boolean z162 = z5;
                            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1830921872, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i50) {
                                    Modifier.Companion companion;
                                    ComposerKt.sourceInformation(composer3, "C397@21584L38,415@22403L1290,382@20861L2842:OutlinedTextField.kt#uh7d8r");
                                    if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1830921872, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:382)");
                                        }
                                        Modifier modifier52 = Modifier.this;
                                        if (function28 != null) {
                                            companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.1
                                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                    invoke2(semanticsPropertyReceiver);
                                                    return Unit.INSTANCE;
                                                }
                                            }), 0.0f, density2.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                                        } else {
                                            companion = Modifier.INSTANCE;
                                        }
                                        Modifier then = modifier52.then(companion);
                                        boolean z17 = z8;
                                        Strings.Companion companion2 = Strings.INSTANCE;
                                        Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                                        SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                                        TextFieldValue textFieldValue3 = textFieldValue2;
                                        Function1<TextFieldValue, Unit> function13 = function12;
                                        boolean z18 = z162;
                                        boolean z19 = z9;
                                        TextStyle textStyle5 = merge2;
                                        KeyboardOptions keyboardOptions5 = keyboardOptions2;
                                        KeyboardActions keyboardActions5 = keyboardActions2;
                                        boolean z20 = z7;
                                        int i51 = i37;
                                        int i52 = i38;
                                        VisualTransformation visualTransformation4 = visualTransformation2;
                                        MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                                        SolidColor solidColor2 = solidColor;
                                        final TextFieldValue textFieldValue4 = textFieldValue2;
                                        final boolean z21 = z162;
                                        final boolean z22 = z7;
                                        final VisualTransformation visualTransformation5 = visualTransformation2;
                                        final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                                        final boolean z23 = z8;
                                        final Function2<? super Composer, ? super Integer, Unit> function2272 = function28;
                                        final Function2<? super Composer, ? super Integer, Unit> function228 = function215;
                                        final Function2<? super Composer, ? super Integer, Unit> function229 = function210;
                                        final Function2<? super Composer, ? super Integer, Unit> function230 = function211;
                                        final Function2<? super Composer, ? super Integer, Unit> function231 = function212;
                                        final Function2<? super Composer, ? super Integer, Unit> function232 = function213;
                                        final Function2<? super Composer, ? super Integer, Unit> function233 = function214;
                                        final TextFieldColors textFieldColors4 = textFieldColors2;
                                        final Shape shape5 = shape2;
                                        BasicTextFieldKt.BasicTextField(textFieldValue3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(-757328870, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.3
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                                                invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                                                int i54;
                                                ComposerKt.sourceInformation(composer4, "C432@23262L391,416@22469L1206:OutlinedTextField.kt#uh7d8r");
                                                if ((i53 & 6) == 0) {
                                                    i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                                                } else {
                                                    i54 = i53;
                                                }
                                                if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-757328870, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:416)");
                                                    }
                                                    OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                                    String text = TextFieldValue.this.getText();
                                                    boolean z24 = z21;
                                                    int i55 = i54;
                                                    boolean z25 = z22;
                                                    VisualTransformation visualTransformation6 = visualTransformation5;
                                                    MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                                    boolean z26 = z23;
                                                    Function2<Composer, Integer, Unit> function235 = function2272;
                                                    Function2<Composer, Integer, Unit> function236 = function228;
                                                    Function2<Composer, Integer, Unit> function237 = function229;
                                                    Function2<Composer, Integer, Unit> function238 = function230;
                                                    Function2<Composer, Integer, Unit> function239 = function231;
                                                    Function2<Composer, Integer, Unit> function240 = function232;
                                                    Function2<Composer, Integer, Unit> function241 = function233;
                                                    TextFieldColors textFieldColors5 = textFieldColors4;
                                                    final boolean z27 = z21;
                                                    final boolean z28 = z23;
                                                    final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                                    final TextFieldColors textFieldColors6 = textFieldColors4;
                                                    final Shape shape6 = shape5;
                                                    outlinedTextFieldDefaults.DecorationBox(text, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(255570733, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.3.1
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(2);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                                            invoke(composer5, num.intValue());
                                                            return Unit.INSTANCE;
                                                        }

                                                        public final void invoke(Composer composer5, int i56) {
                                                            ComposerKt.sourceInformation(composer5, "C433@23318L309:OutlinedTextField.kt#uh7d8r");
                                                            if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventStart(255570733, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:433)");
                                                                }
                                                                OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventEnd();
                                                                    return;
                                                                }
                                                                return;
                                                            }
                                                            composer5.skipToGroupEnd();
                                                        }
                                                    }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer4.skipToGroupEnd();
                                            }
                                        }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            textStyle4 = textStyle3;
                            composer2 = startRestartGroup;
                            function216 = function28;
                            z10 = z8;
                            z11 = z162;
                            z12 = z9;
                            keyboardOptions3 = keyboardOptions2;
                            keyboardActions3 = keyboardActions2;
                            z13 = z7;
                            i41 = i37;
                            i42 = i38;
                            visualTransformation3 = visualTransformation2;
                            function217 = function215;
                            function218 = function211;
                            function219 = function212;
                            function220 = function213;
                            shape3 = shape2;
                            mutableInteractionSource4 = mutableInteractionSource2;
                            textFieldColors3 = textFieldColors2;
                            function221 = function214;
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        i15 = i14;
                        i16 = i6 & 1024;
                        if (i16 == 0) {
                        }
                        i19 = i6 & 2048;
                        if (i19 == 0) {
                        }
                        int i452 = i18;
                        i21 = i7;
                        i22 = i6 & 4096;
                        if (i22 == 0) {
                        }
                        i24 = i6 & 8192;
                        if (i24 == 0) {
                        }
                        i26 = i6 & 16384;
                        if (i26 == 0) {
                        }
                        i28 = i6 & 32768;
                        if (i28 != 0) {
                        }
                        i29 = i6 & 65536;
                        if (i29 != 0) {
                        }
                        i30 = i6 & 131072;
                        if (i30 != 0) {
                        }
                        if ((i4 & 100663296) == 0) {
                        }
                        i31 = i6 & 524288;
                        if (i31 != 0) {
                        }
                        i32 = i6 & 1048576;
                        if (i32 != 0) {
                        }
                        if ((i5 & 48) == 0) {
                        }
                        if ((i5 & 384) == 0) {
                        }
                        int i4822 = i33;
                        if ((i21 & 306783379) == 306783378) {
                        }
                        startRestartGroup.startDefaults();
                        if ((i3 & 1) != 0) {
                        }
                        if (i44 != 0) {
                        }
                        if (i8 != 0) {
                        }
                        if (i9 != 0) {
                        }
                        if ((i6 & 32) != 0) {
                        }
                        if (i10 != 0) {
                        }
                        if (i11 != 0) {
                        }
                        if (i13 != 0) {
                        }
                        if (i15 != 0) {
                        }
                        if (i17 != 0) {
                        }
                        if (i20 != 0) {
                        }
                        if (i22 != 0) {
                        }
                        if (i24 != 0) {
                        }
                        if (i26 != 0) {
                        }
                        if (i28 != 0) {
                        }
                        if (i29 != 0) {
                        }
                        if (i30 != 0) {
                        }
                        if ((i6 & 262144) != 0) {
                        }
                        if (i31 != 0) {
                        }
                        if (i32 == 0) {
                        }
                        if ((i6 & 2097152) != 0) {
                        }
                        MutableInteractionSource mutableInteractionSource622 = mutableInteractionSource5;
                        if ((i6 & 4194304) != 0) {
                        }
                        visualTransformation2 = none;
                        i39 = i34;
                        function215 = function29;
                        z8 = z14;
                        z9 = z6;
                        i40 = 0;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.startReplaceGroup(30368324);
                        ComposerKt.sourceInformation(startRestartGroup, "370@20325L39");
                        if (mutableInteractionSource2 == null) {
                        }
                        startRestartGroup.endReplaceGroup();
                        startRestartGroup.startReplaceGroup(30374434);
                        ComposerKt.sourceInformation(startRestartGroup, "*374@20549L25");
                        m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
                        if (m6878getColor0d7_KjU == 16) {
                        }
                        startRestartGroup.endReplaceGroup();
                        final TextStyle merge22 = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                        ProvidableCompositionLocal<Density> localDensity22 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object consume222 = startRestartGroup.consume(localDensity22);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        final Density density22 = (Density) consume222;
                        final boolean z1622 = z5;
                        CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1830921872, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i50) {
                                Modifier.Companion companion;
                                ComposerKt.sourceInformation(composer3, "C397@21584L38,415@22403L1290,382@20861L2842:OutlinedTextField.kt#uh7d8r");
                                if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1830921872, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:382)");
                                    }
                                    Modifier modifier52 = Modifier.this;
                                    if (function28 != null) {
                                        companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.1
                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }
                                        }), 0.0f, density22.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                                    } else {
                                        companion = Modifier.INSTANCE;
                                    }
                                    Modifier then = modifier52.then(companion);
                                    boolean z17 = z8;
                                    Strings.Companion companion2 = Strings.INSTANCE;
                                    Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                                    SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                                    TextFieldValue textFieldValue3 = textFieldValue2;
                                    Function1<TextFieldValue, Unit> function13 = function12;
                                    boolean z18 = z1622;
                                    boolean z19 = z9;
                                    TextStyle textStyle5 = merge22;
                                    KeyboardOptions keyboardOptions5 = keyboardOptions2;
                                    KeyboardActions keyboardActions5 = keyboardActions2;
                                    boolean z20 = z7;
                                    int i51 = i37;
                                    int i52 = i38;
                                    VisualTransformation visualTransformation4 = visualTransformation2;
                                    MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                                    SolidColor solidColor2 = solidColor;
                                    final TextFieldValue textFieldValue4 = textFieldValue2;
                                    final boolean z21 = z1622;
                                    final boolean z22 = z7;
                                    final VisualTransformation visualTransformation5 = visualTransformation2;
                                    final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                                    final boolean z23 = z8;
                                    final Function2<? super Composer, ? super Integer, Unit> function2272 = function28;
                                    final Function2<? super Composer, ? super Integer, Unit> function228 = function215;
                                    final Function2<? super Composer, ? super Integer, Unit> function229 = function210;
                                    final Function2<? super Composer, ? super Integer, Unit> function230 = function211;
                                    final Function2<? super Composer, ? super Integer, Unit> function231 = function212;
                                    final Function2<? super Composer, ? super Integer, Unit> function232 = function213;
                                    final Function2<? super Composer, ? super Integer, Unit> function233 = function214;
                                    final TextFieldColors textFieldColors4 = textFieldColors2;
                                    final Shape shape5 = shape2;
                                    BasicTextFieldKt.BasicTextField(textFieldValue3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(-757328870, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.3
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                                            invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                                            int i54;
                                            ComposerKt.sourceInformation(composer4, "C432@23262L391,416@22469L1206:OutlinedTextField.kt#uh7d8r");
                                            if ((i53 & 6) == 0) {
                                                i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                                            } else {
                                                i54 = i53;
                                            }
                                            if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-757328870, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:416)");
                                                }
                                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                                String text = TextFieldValue.this.getText();
                                                boolean z24 = z21;
                                                int i55 = i54;
                                                boolean z25 = z22;
                                                VisualTransformation visualTransformation6 = visualTransformation5;
                                                MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                                boolean z26 = z23;
                                                Function2<Composer, Integer, Unit> function235 = function2272;
                                                Function2<Composer, Integer, Unit> function236 = function228;
                                                Function2<Composer, Integer, Unit> function237 = function229;
                                                Function2<Composer, Integer, Unit> function238 = function230;
                                                Function2<Composer, Integer, Unit> function239 = function231;
                                                Function2<Composer, Integer, Unit> function240 = function232;
                                                Function2<Composer, Integer, Unit> function241 = function233;
                                                TextFieldColors textFieldColors5 = textFieldColors4;
                                                final boolean z27 = z21;
                                                final boolean z28 = z23;
                                                final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                                final TextFieldColors textFieldColors6 = textFieldColors4;
                                                final Shape shape6 = shape5;
                                                outlinedTextFieldDefaults.DecorationBox(text, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(255570733, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.3.1
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                                        invoke(composer5, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(Composer composer5, int i56) {
                                                        ComposerKt.sourceInformation(composer5, "C433@23318L309:OutlinedTextField.kt#uh7d8r");
                                                        if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(255570733, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:433)");
                                                            }
                                                            OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventEnd();
                                                                return;
                                                            }
                                                            return;
                                                        }
                                                        composer5.skipToGroupEnd();
                                                    }
                                                }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer4.skipToGroupEnd();
                                        }
                                    }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        textStyle4 = textStyle3;
                        composer2 = startRestartGroup;
                        function216 = function28;
                        z10 = z8;
                        z11 = z1622;
                        z12 = z9;
                        keyboardOptions3 = keyboardOptions2;
                        keyboardActions3 = keyboardActions2;
                        z13 = z7;
                        i41 = i37;
                        i42 = i38;
                        visualTransformation3 = visualTransformation2;
                        function217 = function215;
                        function218 = function211;
                        function219 = function212;
                        function220 = function213;
                        shape3 = shape2;
                        mutableInteractionSource4 = mutableInteractionSource2;
                        textFieldColors3 = textFieldColors2;
                        function221 = function214;
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    i13 = i12;
                    i14 = i6 & 512;
                    if (i14 != 0) {
                    }
                    i15 = i14;
                    i16 = i6 & 1024;
                    if (i16 == 0) {
                    }
                    i19 = i6 & 2048;
                    if (i19 == 0) {
                    }
                    int i4522 = i18;
                    i21 = i7;
                    i22 = i6 & 4096;
                    if (i22 == 0) {
                    }
                    i24 = i6 & 8192;
                    if (i24 == 0) {
                    }
                    i26 = i6 & 16384;
                    if (i26 == 0) {
                    }
                    i28 = i6 & 32768;
                    if (i28 != 0) {
                    }
                    i29 = i6 & 65536;
                    if (i29 != 0) {
                    }
                    i30 = i6 & 131072;
                    if (i30 != 0) {
                    }
                    if ((i4 & 100663296) == 0) {
                    }
                    i31 = i6 & 524288;
                    if (i31 != 0) {
                    }
                    i32 = i6 & 1048576;
                    if (i32 != 0) {
                    }
                    if ((i5 & 48) == 0) {
                    }
                    if ((i5 & 384) == 0) {
                    }
                    int i48222 = i33;
                    if ((i21 & 306783379) == 306783378) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i3 & 1) != 0) {
                    }
                    if (i44 != 0) {
                    }
                    if (i8 != 0) {
                    }
                    if (i9 != 0) {
                    }
                    if ((i6 & 32) != 0) {
                    }
                    if (i10 != 0) {
                    }
                    if (i11 != 0) {
                    }
                    if (i13 != 0) {
                    }
                    if (i15 != 0) {
                    }
                    if (i17 != 0) {
                    }
                    if (i20 != 0) {
                    }
                    if (i22 != 0) {
                    }
                    if (i24 != 0) {
                    }
                    if (i26 != 0) {
                    }
                    if (i28 != 0) {
                    }
                    if (i29 != 0) {
                    }
                    if (i30 != 0) {
                    }
                    if ((i6 & 262144) != 0) {
                    }
                    if (i31 != 0) {
                    }
                    if (i32 == 0) {
                    }
                    if ((i6 & 2097152) != 0) {
                    }
                    MutableInteractionSource mutableInteractionSource6222 = mutableInteractionSource5;
                    if ((i6 & 4194304) != 0) {
                    }
                    visualTransformation2 = none;
                    i39 = i34;
                    function215 = function29;
                    z8 = z14;
                    z9 = z6;
                    i40 = 0;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.startReplaceGroup(30368324);
                    ComposerKt.sourceInformation(startRestartGroup, "370@20325L39");
                    if (mutableInteractionSource2 == null) {
                    }
                    startRestartGroup.endReplaceGroup();
                    startRestartGroup.startReplaceGroup(30374434);
                    ComposerKt.sourceInformation(startRestartGroup, "*374@20549L25");
                    m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
                    if (m6878getColor0d7_KjU == 16) {
                    }
                    startRestartGroup.endReplaceGroup();
                    final TextStyle merge222 = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                    ProvidableCompositionLocal<Density> localDensity222 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume2222 = startRestartGroup.consume(localDensity222);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    final Density density222 = (Density) consume2222;
                    final boolean z16222 = z5;
                    CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1830921872, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i50) {
                            Modifier.Companion companion;
                            ComposerKt.sourceInformation(composer3, "C397@21584L38,415@22403L1290,382@20861L2842:OutlinedTextField.kt#uh7d8r");
                            if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1830921872, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:382)");
                                }
                                Modifier modifier52 = Modifier.this;
                                if (function28 != null) {
                                    companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.1
                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }
                                    }), 0.0f, density222.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                                } else {
                                    companion = Modifier.INSTANCE;
                                }
                                Modifier then = modifier52.then(companion);
                                boolean z17 = z8;
                                Strings.Companion companion2 = Strings.INSTANCE;
                                Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                                SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                                TextFieldValue textFieldValue3 = textFieldValue2;
                                Function1<TextFieldValue, Unit> function13 = function12;
                                boolean z18 = z16222;
                                boolean z19 = z9;
                                TextStyle textStyle5 = merge222;
                                KeyboardOptions keyboardOptions5 = keyboardOptions2;
                                KeyboardActions keyboardActions5 = keyboardActions2;
                                boolean z20 = z7;
                                int i51 = i37;
                                int i52 = i38;
                                VisualTransformation visualTransformation4 = visualTransformation2;
                                MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                                SolidColor solidColor2 = solidColor;
                                final TextFieldValue textFieldValue4 = textFieldValue2;
                                final boolean z21 = z16222;
                                final boolean z22 = z7;
                                final VisualTransformation visualTransformation5 = visualTransformation2;
                                final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                                final boolean z23 = z8;
                                final Function2<? super Composer, ? super Integer, Unit> function2272 = function28;
                                final Function2<? super Composer, ? super Integer, Unit> function228 = function215;
                                final Function2<? super Composer, ? super Integer, Unit> function229 = function210;
                                final Function2<? super Composer, ? super Integer, Unit> function230 = function211;
                                final Function2<? super Composer, ? super Integer, Unit> function231 = function212;
                                final Function2<? super Composer, ? super Integer, Unit> function232 = function213;
                                final Function2<? super Composer, ? super Integer, Unit> function233 = function214;
                                final TextFieldColors textFieldColors4 = textFieldColors2;
                                final Shape shape5 = shape2;
                                BasicTextFieldKt.BasicTextField(textFieldValue3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(-757328870, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                                        invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                                        int i54;
                                        ComposerKt.sourceInformation(composer4, "C432@23262L391,416@22469L1206:OutlinedTextField.kt#uh7d8r");
                                        if ((i53 & 6) == 0) {
                                            i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                                        } else {
                                            i54 = i53;
                                        }
                                        if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-757328870, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:416)");
                                            }
                                            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                            String text = TextFieldValue.this.getText();
                                            boolean z24 = z21;
                                            int i55 = i54;
                                            boolean z25 = z22;
                                            VisualTransformation visualTransformation6 = visualTransformation5;
                                            MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                            boolean z26 = z23;
                                            Function2<Composer, Integer, Unit> function235 = function2272;
                                            Function2<Composer, Integer, Unit> function236 = function228;
                                            Function2<Composer, Integer, Unit> function237 = function229;
                                            Function2<Composer, Integer, Unit> function238 = function230;
                                            Function2<Composer, Integer, Unit> function239 = function231;
                                            Function2<Composer, Integer, Unit> function240 = function232;
                                            Function2<Composer, Integer, Unit> function241 = function233;
                                            TextFieldColors textFieldColors5 = textFieldColors4;
                                            final boolean z27 = z21;
                                            final boolean z28 = z23;
                                            final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                            final TextFieldColors textFieldColors6 = textFieldColors4;
                                            final Shape shape6 = shape5;
                                            outlinedTextFieldDefaults.DecorationBox(text, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(255570733, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.3.1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                                    invoke(composer5, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer5, int i56) {
                                                    ComposerKt.sourceInformation(composer5, "C433@23318L309:OutlinedTextField.kt#uh7d8r");
                                                    if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(255570733, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:433)");
                                                        }
                                                        OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer5.skipToGroupEnd();
                                                }
                                            }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    textStyle4 = textStyle3;
                    composer2 = startRestartGroup;
                    function216 = function28;
                    z10 = z8;
                    z11 = z16222;
                    z12 = z9;
                    keyboardOptions3 = keyboardOptions2;
                    keyboardActions3 = keyboardActions2;
                    z13 = z7;
                    i41 = i37;
                    i42 = i38;
                    visualTransformation3 = visualTransformation2;
                    function217 = function215;
                    function218 = function211;
                    function219 = function212;
                    function220 = function213;
                    shape3 = shape2;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    textFieldColors3 = textFieldColors2;
                    function221 = function214;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                z6 = z2;
                if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                }
                i10 = i6 & 64;
                if (i10 == 0) {
                }
                i11 = i6 & 128;
                if (i11 == 0) {
                }
                i12 = i6 & 256;
                if (i12 == 0) {
                }
                i13 = i12;
                i14 = i6 & 512;
                if (i14 != 0) {
                }
                i15 = i14;
                i16 = i6 & 1024;
                if (i16 == 0) {
                }
                i19 = i6 & 2048;
                if (i19 == 0) {
                }
                int i45222 = i18;
                i21 = i7;
                i22 = i6 & 4096;
                if (i22 == 0) {
                }
                i24 = i6 & 8192;
                if (i24 == 0) {
                }
                i26 = i6 & 16384;
                if (i26 == 0) {
                }
                i28 = i6 & 32768;
                if (i28 != 0) {
                }
                i29 = i6 & 65536;
                if (i29 != 0) {
                }
                i30 = i6 & 131072;
                if (i30 != 0) {
                }
                if ((i4 & 100663296) == 0) {
                }
                i31 = i6 & 524288;
                if (i31 != 0) {
                }
                i32 = i6 & 1048576;
                if (i32 != 0) {
                }
                if ((i5 & 48) == 0) {
                }
                if ((i5 & 384) == 0) {
                }
                int i482222 = i33;
                if ((i21 & 306783379) == 306783378) {
                }
                startRestartGroup.startDefaults();
                if ((i3 & 1) != 0) {
                }
                if (i44 != 0) {
                }
                if (i8 != 0) {
                }
                if (i9 != 0) {
                }
                if ((i6 & 32) != 0) {
                }
                if (i10 != 0) {
                }
                if (i11 != 0) {
                }
                if (i13 != 0) {
                }
                if (i15 != 0) {
                }
                if (i17 != 0) {
                }
                if (i20 != 0) {
                }
                if (i22 != 0) {
                }
                if (i24 != 0) {
                }
                if (i26 != 0) {
                }
                if (i28 != 0) {
                }
                if (i29 != 0) {
                }
                if (i30 != 0) {
                }
                if ((i6 & 262144) != 0) {
                }
                if (i31 != 0) {
                }
                if (i32 == 0) {
                }
                if ((i6 & 2097152) != 0) {
                }
                MutableInteractionSource mutableInteractionSource62222 = mutableInteractionSource5;
                if ((i6 & 4194304) != 0) {
                }
                visualTransformation2 = none;
                i39 = i34;
                function215 = function29;
                z8 = z14;
                z9 = z6;
                i40 = 0;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.startReplaceGroup(30368324);
                ComposerKt.sourceInformation(startRestartGroup, "370@20325L39");
                if (mutableInteractionSource2 == null) {
                }
                startRestartGroup.endReplaceGroup();
                startRestartGroup.startReplaceGroup(30374434);
                ComposerKt.sourceInformation(startRestartGroup, "*374@20549L25");
                m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
                if (m6878getColor0d7_KjU == 16) {
                }
                startRestartGroup.endReplaceGroup();
                final TextStyle merge2222 = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                ProvidableCompositionLocal<Density> localDensity2222 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume22222 = startRestartGroup.consume(localDensity2222);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                final Density density2222 = (Density) consume22222;
                final boolean z162222 = z5;
                CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1830921872, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i50) {
                        Modifier.Companion companion;
                        ComposerKt.sourceInformation(composer3, "C397@21584L38,415@22403L1290,382@20861L2842:OutlinedTextField.kt#uh7d8r");
                        if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1830921872, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:382)");
                            }
                            Modifier modifier52 = Modifier.this;
                            if (function28 != null) {
                                companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.1
                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }
                                }), 0.0f, density2222.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                            } else {
                                companion = Modifier.INSTANCE;
                            }
                            Modifier then = modifier52.then(companion);
                            boolean z17 = z8;
                            Strings.Companion companion2 = Strings.INSTANCE;
                            Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                            SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                            TextFieldValue textFieldValue3 = textFieldValue2;
                            Function1<TextFieldValue, Unit> function13 = function12;
                            boolean z18 = z162222;
                            boolean z19 = z9;
                            TextStyle textStyle5 = merge2222;
                            KeyboardOptions keyboardOptions5 = keyboardOptions2;
                            KeyboardActions keyboardActions5 = keyboardActions2;
                            boolean z20 = z7;
                            int i51 = i37;
                            int i52 = i38;
                            VisualTransformation visualTransformation4 = visualTransformation2;
                            MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                            SolidColor solidColor2 = solidColor;
                            final TextFieldValue textFieldValue4 = textFieldValue2;
                            final boolean z21 = z162222;
                            final boolean z22 = z7;
                            final VisualTransformation visualTransformation5 = visualTransformation2;
                            final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                            final boolean z23 = z8;
                            final Function2<? super Composer, ? super Integer, Unit> function2272 = function28;
                            final Function2<? super Composer, ? super Integer, Unit> function228 = function215;
                            final Function2<? super Composer, ? super Integer, Unit> function229 = function210;
                            final Function2<? super Composer, ? super Integer, Unit> function230 = function211;
                            final Function2<? super Composer, ? super Integer, Unit> function231 = function212;
                            final Function2<? super Composer, ? super Integer, Unit> function232 = function213;
                            final Function2<? super Composer, ? super Integer, Unit> function233 = function214;
                            final TextFieldColors textFieldColors4 = textFieldColors2;
                            final Shape shape5 = shape2;
                            BasicTextFieldKt.BasicTextField(textFieldValue3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(-757328870, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                                    invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                                    int i54;
                                    ComposerKt.sourceInformation(composer4, "C432@23262L391,416@22469L1206:OutlinedTextField.kt#uh7d8r");
                                    if ((i53 & 6) == 0) {
                                        i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                                    } else {
                                        i54 = i53;
                                    }
                                    if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-757328870, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:416)");
                                        }
                                        OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                        String text = TextFieldValue.this.getText();
                                        boolean z24 = z21;
                                        int i55 = i54;
                                        boolean z25 = z22;
                                        VisualTransformation visualTransformation6 = visualTransformation5;
                                        MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                        boolean z26 = z23;
                                        Function2<Composer, Integer, Unit> function235 = function2272;
                                        Function2<Composer, Integer, Unit> function236 = function228;
                                        Function2<Composer, Integer, Unit> function237 = function229;
                                        Function2<Composer, Integer, Unit> function238 = function230;
                                        Function2<Composer, Integer, Unit> function239 = function231;
                                        Function2<Composer, Integer, Unit> function240 = function232;
                                        Function2<Composer, Integer, Unit> function241 = function233;
                                        TextFieldColors textFieldColors5 = textFieldColors4;
                                        final boolean z27 = z21;
                                        final boolean z28 = z23;
                                        final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                        final TextFieldColors textFieldColors6 = textFieldColors4;
                                        final Shape shape6 = shape5;
                                        outlinedTextFieldDefaults.DecorationBox(text, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(255570733, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.3.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                                invoke(composer5, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer5, int i56) {
                                                ComposerKt.sourceInformation(composer5, "C433@23318L309:OutlinedTextField.kt#uh7d8r");
                                                if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(255570733, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:433)");
                                                    }
                                                    OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer5.skipToGroupEnd();
                                            }
                                        }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
                if (ComposerKt.isTraceInProgress()) {
                }
                textStyle4 = textStyle3;
                composer2 = startRestartGroup;
                function216 = function28;
                z10 = z8;
                z11 = z162222;
                z12 = z9;
                keyboardOptions3 = keyboardOptions2;
                keyboardActions3 = keyboardActions2;
                z13 = z7;
                i41 = i37;
                i42 = i38;
                visualTransformation3 = visualTransformation2;
                function217 = function215;
                function218 = function211;
                function219 = function212;
                function220 = function213;
                shape3 = shape2;
                mutableInteractionSource4 = mutableInteractionSource2;
                textFieldColors3 = textFieldColors2;
                function221 = function214;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            z5 = z;
            i9 = i6 & 16;
            if (i9 != 0) {
            }
            z6 = z2;
            if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
            }
            i10 = i6 & 64;
            if (i10 == 0) {
            }
            i11 = i6 & 128;
            if (i11 == 0) {
            }
            i12 = i6 & 256;
            if (i12 == 0) {
            }
            i13 = i12;
            i14 = i6 & 512;
            if (i14 != 0) {
            }
            i15 = i14;
            i16 = i6 & 1024;
            if (i16 == 0) {
            }
            i19 = i6 & 2048;
            if (i19 == 0) {
            }
            int i452222 = i18;
            i21 = i7;
            i22 = i6 & 4096;
            if (i22 == 0) {
            }
            i24 = i6 & 8192;
            if (i24 == 0) {
            }
            i26 = i6 & 16384;
            if (i26 == 0) {
            }
            i28 = i6 & 32768;
            if (i28 != 0) {
            }
            i29 = i6 & 65536;
            if (i29 != 0) {
            }
            i30 = i6 & 131072;
            if (i30 != 0) {
            }
            if ((i4 & 100663296) == 0) {
            }
            i31 = i6 & 524288;
            if (i31 != 0) {
            }
            i32 = i6 & 1048576;
            if (i32 != 0) {
            }
            if ((i5 & 48) == 0) {
            }
            if ((i5 & 384) == 0) {
            }
            int i4822222 = i33;
            if ((i21 & 306783379) == 306783378) {
            }
            startRestartGroup.startDefaults();
            if ((i3 & 1) != 0) {
            }
            if (i44 != 0) {
            }
            if (i8 != 0) {
            }
            if (i9 != 0) {
            }
            if ((i6 & 32) != 0) {
            }
            if (i10 != 0) {
            }
            if (i11 != 0) {
            }
            if (i13 != 0) {
            }
            if (i15 != 0) {
            }
            if (i17 != 0) {
            }
            if (i20 != 0) {
            }
            if (i22 != 0) {
            }
            if (i24 != 0) {
            }
            if (i26 != 0) {
            }
            if (i28 != 0) {
            }
            if (i29 != 0) {
            }
            if (i30 != 0) {
            }
            if ((i6 & 262144) != 0) {
            }
            if (i31 != 0) {
            }
            if (i32 == 0) {
            }
            if ((i6 & 2097152) != 0) {
            }
            MutableInteractionSource mutableInteractionSource622222 = mutableInteractionSource5;
            if ((i6 & 4194304) != 0) {
            }
            visualTransformation2 = none;
            i39 = i34;
            function215 = function29;
            z8 = z14;
            z9 = z6;
            i40 = 0;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.startReplaceGroup(30368324);
            ComposerKt.sourceInformation(startRestartGroup, "370@20325L39");
            if (mutableInteractionSource2 == null) {
            }
            startRestartGroup.endReplaceGroup();
            startRestartGroup.startReplaceGroup(30374434);
            ComposerKt.sourceInformation(startRestartGroup, "*374@20549L25");
            m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
            if (m6878getColor0d7_KjU == 16) {
            }
            startRestartGroup.endReplaceGroup();
            final TextStyle merge22222 = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            ProvidableCompositionLocal<Density> localDensity22222 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume222222 = startRestartGroup.consume(localDensity22222);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final Density density22222 = (Density) consume222222;
            final boolean z1622222 = z5;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1830921872, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i50) {
                    Modifier.Companion companion;
                    ComposerKt.sourceInformation(composer3, "C397@21584L38,415@22403L1290,382@20861L2842:OutlinedTextField.kt#uh7d8r");
                    if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1830921872, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:382)");
                        }
                        Modifier modifier52 = Modifier.this;
                        if (function28 != null) {
                            companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.1
                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }
                            }), 0.0f, density22222.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                        } else {
                            companion = Modifier.INSTANCE;
                        }
                        Modifier then = modifier52.then(companion);
                        boolean z17 = z8;
                        Strings.Companion companion2 = Strings.INSTANCE;
                        Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                        SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                        TextFieldValue textFieldValue3 = textFieldValue2;
                        Function1<TextFieldValue, Unit> function13 = function12;
                        boolean z18 = z1622222;
                        boolean z19 = z9;
                        TextStyle textStyle5 = merge22222;
                        KeyboardOptions keyboardOptions5 = keyboardOptions2;
                        KeyboardActions keyboardActions5 = keyboardActions2;
                        boolean z20 = z7;
                        int i51 = i37;
                        int i52 = i38;
                        VisualTransformation visualTransformation4 = visualTransformation2;
                        MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                        SolidColor solidColor2 = solidColor;
                        final TextFieldValue textFieldValue4 = textFieldValue2;
                        final boolean z21 = z1622222;
                        final boolean z22 = z7;
                        final VisualTransformation visualTransformation5 = visualTransformation2;
                        final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                        final boolean z23 = z8;
                        final Function2<? super Composer, ? super Integer, Unit> function2272 = function28;
                        final Function2<? super Composer, ? super Integer, Unit> function228 = function215;
                        final Function2<? super Composer, ? super Integer, Unit> function229 = function210;
                        final Function2<? super Composer, ? super Integer, Unit> function230 = function211;
                        final Function2<? super Composer, ? super Integer, Unit> function231 = function212;
                        final Function2<? super Composer, ? super Integer, Unit> function232 = function213;
                        final Function2<? super Composer, ? super Integer, Unit> function233 = function214;
                        final TextFieldColors textFieldColors4 = textFieldColors2;
                        final Shape shape5 = shape2;
                        BasicTextFieldKt.BasicTextField(textFieldValue3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(-757328870, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                                invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                                int i54;
                                ComposerKt.sourceInformation(composer4, "C432@23262L391,416@22469L1206:OutlinedTextField.kt#uh7d8r");
                                if ((i53 & 6) == 0) {
                                    i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                                } else {
                                    i54 = i53;
                                }
                                if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-757328870, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:416)");
                                    }
                                    OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                    String text = TextFieldValue.this.getText();
                                    boolean z24 = z21;
                                    int i55 = i54;
                                    boolean z25 = z22;
                                    VisualTransformation visualTransformation6 = visualTransformation5;
                                    MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                    boolean z26 = z23;
                                    Function2<Composer, Integer, Unit> function235 = function2272;
                                    Function2<Composer, Integer, Unit> function236 = function228;
                                    Function2<Composer, Integer, Unit> function237 = function229;
                                    Function2<Composer, Integer, Unit> function238 = function230;
                                    Function2<Composer, Integer, Unit> function239 = function231;
                                    Function2<Composer, Integer, Unit> function240 = function232;
                                    Function2<Composer, Integer, Unit> function241 = function233;
                                    TextFieldColors textFieldColors5 = textFieldColors4;
                                    final boolean z27 = z21;
                                    final boolean z28 = z23;
                                    final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                    final TextFieldColors textFieldColors6 = textFieldColors4;
                                    final Shape shape6 = shape5;
                                    outlinedTextFieldDefaults.DecorationBox(text, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(255570733, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.3.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                            invoke(composer5, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer5, int i56) {
                                            ComposerKt.sourceInformation(composer5, "C433@23318L309:OutlinedTextField.kt#uh7d8r");
                                            if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(255570733, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:433)");
                                                }
                                                OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer5.skipToGroupEnd();
                                        }
                                    }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer4.skipToGroupEnd();
                            }
                        }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
            }
            textStyle4 = textStyle3;
            composer2 = startRestartGroup;
            function216 = function28;
            z10 = z8;
            z11 = z1622222;
            z12 = z9;
            keyboardOptions3 = keyboardOptions2;
            keyboardActions3 = keyboardActions2;
            z13 = z7;
            i41 = i37;
            i42 = i38;
            visualTransformation3 = visualTransformation2;
            function217 = function215;
            function218 = function211;
            function219 = function212;
            function220 = function213;
            shape3 = shape2;
            mutableInteractionSource4 = mutableInteractionSource2;
            textFieldColors3 = textFieldColors2;
            function221 = function214;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i8 = i6 & 8;
        if (i8 == 0) {
        }
        z5 = z;
        i9 = i6 & 16;
        if (i9 != 0) {
        }
        z6 = z2;
        if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
        }
        i10 = i6 & 64;
        if (i10 == 0) {
        }
        i11 = i6 & 128;
        if (i11 == 0) {
        }
        i12 = i6 & 256;
        if (i12 == 0) {
        }
        i13 = i12;
        i14 = i6 & 512;
        if (i14 != 0) {
        }
        i15 = i14;
        i16 = i6 & 1024;
        if (i16 == 0) {
        }
        i19 = i6 & 2048;
        if (i19 == 0) {
        }
        int i4522222 = i18;
        i21 = i7;
        i22 = i6 & 4096;
        if (i22 == 0) {
        }
        i24 = i6 & 8192;
        if (i24 == 0) {
        }
        i26 = i6 & 16384;
        if (i26 == 0) {
        }
        i28 = i6 & 32768;
        if (i28 != 0) {
        }
        i29 = i6 & 65536;
        if (i29 != 0) {
        }
        i30 = i6 & 131072;
        if (i30 != 0) {
        }
        if ((i4 & 100663296) == 0) {
        }
        i31 = i6 & 524288;
        if (i31 != 0) {
        }
        i32 = i6 & 1048576;
        if (i32 != 0) {
        }
        if ((i5 & 48) == 0) {
        }
        if ((i5 & 384) == 0) {
        }
        int i48222222 = i33;
        if ((i21 & 306783379) == 306783378) {
        }
        startRestartGroup.startDefaults();
        if ((i3 & 1) != 0) {
        }
        if (i44 != 0) {
        }
        if (i8 != 0) {
        }
        if (i9 != 0) {
        }
        if ((i6 & 32) != 0) {
        }
        if (i10 != 0) {
        }
        if (i11 != 0) {
        }
        if (i13 != 0) {
        }
        if (i15 != 0) {
        }
        if (i17 != 0) {
        }
        if (i20 != 0) {
        }
        if (i22 != 0) {
        }
        if (i24 != 0) {
        }
        if (i26 != 0) {
        }
        if (i28 != 0) {
        }
        if (i29 != 0) {
        }
        if (i30 != 0) {
        }
        if ((i6 & 262144) != 0) {
        }
        if (i31 != 0) {
        }
        if (i32 == 0) {
        }
        if ((i6 & 2097152) != 0) {
        }
        MutableInteractionSource mutableInteractionSource6222222 = mutableInteractionSource5;
        if ((i6 & 4194304) != 0) {
        }
        visualTransformation2 = none;
        i39 = i34;
        function215 = function29;
        z8 = z14;
        z9 = z6;
        i40 = 0;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.startReplaceGroup(30368324);
        ComposerKt.sourceInformation(startRestartGroup, "370@20325L39");
        if (mutableInteractionSource2 == null) {
        }
        startRestartGroup.endReplaceGroup();
        startRestartGroup.startReplaceGroup(30374434);
        ComposerKt.sourceInformation(startRestartGroup, "*374@20549L25");
        m6878getColor0d7_KjU = textStyle3.m6878getColor0d7_KjU();
        if (m6878getColor0d7_KjU == 16) {
        }
        startRestartGroup.endReplaceGroup();
        final TextStyle merge222222 = textStyle3.merge(new TextStyle(m6878getColor0d7_KjU, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
        ProvidableCompositionLocal<Density> localDensity222222 = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2222222 = startRestartGroup.consume(localDensity222222);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        final Density density222222 = (Density) consume2222222;
        final boolean z16222222 = z5;
        CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(textFieldColors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1830921872, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                invoke(composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer3, int i50) {
                Modifier.Companion companion;
                ComposerKt.sourceInformation(composer3, "C397@21584L38,415@22403L1290,382@20861L2842:OutlinedTextField.kt#uh7d8r");
                if ((i50 & 3) != 2 || !composer3.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1830921872, i50, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:382)");
                    }
                    Modifier modifier52 = Modifier.this;
                    if (function28 != null) {
                        companion = PaddingKt.m882paddingqDBjuR0$default(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.1
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }
                        }), 0.0f, density222222.mo454toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                    } else {
                        companion = Modifier.INSTANCE;
                    }
                    Modifier then = modifier52.then(companion);
                    boolean z17 = z8;
                    Strings.Companion companion2 = Strings.INSTANCE;
                    Modifier m914defaultMinSizeVpY3zN4 = SizeKt.m914defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z17, Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.default_error_message), composer3, 0)), OutlinedTextFieldDefaults.INSTANCE.m2503getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2502getMinHeightD9Ej5fM());
                    SolidColor solidColor = new SolidColor(textFieldColors2.m2768cursorColorvNxB06k$material3_release(z8), null);
                    TextFieldValue textFieldValue3 = textFieldValue2;
                    Function1<TextFieldValue, Unit> function13 = function12;
                    boolean z18 = z16222222;
                    boolean z19 = z9;
                    TextStyle textStyle5 = merge222222;
                    KeyboardOptions keyboardOptions5 = keyboardOptions2;
                    KeyboardActions keyboardActions5 = keyboardActions2;
                    boolean z20 = z7;
                    int i51 = i37;
                    int i52 = i38;
                    VisualTransformation visualTransformation4 = visualTransformation2;
                    MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource3;
                    SolidColor solidColor2 = solidColor;
                    final TextFieldValue textFieldValue4 = textFieldValue2;
                    final boolean z21 = z16222222;
                    final boolean z22 = z7;
                    final VisualTransformation visualTransformation5 = visualTransformation2;
                    final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource3;
                    final boolean z23 = z8;
                    final Function2<? super Composer, ? super Integer, Unit> function2272 = function28;
                    final Function2<? super Composer, ? super Integer, Unit> function228 = function215;
                    final Function2<? super Composer, ? super Integer, Unit> function229 = function210;
                    final Function2<? super Composer, ? super Integer, Unit> function230 = function211;
                    final Function2<? super Composer, ? super Integer, Unit> function231 = function212;
                    final Function2<? super Composer, ? super Integer, Unit> function232 = function213;
                    final Function2<? super Composer, ? super Integer, Unit> function233 = function214;
                    final TextFieldColors textFieldColors4 = textFieldColors2;
                    final Shape shape5 = shape2;
                    BasicTextFieldKt.BasicTextField(textFieldValue3, function13, m914defaultMinSizeVpY3zN4, z18, z19, textStyle5, keyboardOptions5, keyboardActions5, z20, i51, i52, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource7, solidColor2, ComposableLambdaKt.rememberComposableLambda(-757328870, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function234, Composer composer4, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function234, composer4, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function234, Composer composer4, int i53) {
                            int i54;
                            ComposerKt.sourceInformation(composer4, "C432@23262L391,416@22469L1206:OutlinedTextField.kt#uh7d8r");
                            if ((i53 & 6) == 0) {
                                i54 = i53 | (composer4.changedInstance(function234) ? 4 : 2);
                            } else {
                                i54 = i53;
                            }
                            if ((i54 & 19) != 18 || !composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-757328870, i54, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:416)");
                                }
                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                String text = TextFieldValue.this.getText();
                                boolean z24 = z21;
                                int i55 = i54;
                                boolean z25 = z22;
                                VisualTransformation visualTransformation6 = visualTransformation5;
                                MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource8;
                                boolean z26 = z23;
                                Function2<Composer, Integer, Unit> function235 = function2272;
                                Function2<Composer, Integer, Unit> function236 = function228;
                                Function2<Composer, Integer, Unit> function237 = function229;
                                Function2<Composer, Integer, Unit> function238 = function230;
                                Function2<Composer, Integer, Unit> function239 = function231;
                                Function2<Composer, Integer, Unit> function240 = function232;
                                Function2<Composer, Integer, Unit> function241 = function233;
                                TextFieldColors textFieldColors5 = textFieldColors4;
                                final boolean z27 = z21;
                                final boolean z28 = z23;
                                final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource8;
                                final TextFieldColors textFieldColors6 = textFieldColors4;
                                final Shape shape6 = shape5;
                                outlinedTextFieldDefaults.DecorationBox(text, function234, z24, z25, visualTransformation6, mutableInteractionSource9, z26, function235, function236, function237, function238, function239, function240, function241, textFieldColors5, null, ComposableLambdaKt.rememberComposableLambda(255570733, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.3.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                        invoke(composer5, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer5, int i56) {
                                        ComposerKt.sourceInformation(composer5, "C433@23318L309:OutlinedTextField.kt#uh7d8r");
                                        if ((i56 & 3) != 2 || !composer5.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(255570733, i56, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:433)");
                                            }
                                            OutlinedTextFieldDefaults.INSTANCE.m2497Container4EFweAY(z27, z28, mutableInteractionSource10, null, textFieldColors6, shape6, 0.0f, 0.0f, composer5, 100663296, ComposerKt.invocationKey);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer5.skipToGroupEnd();
                                    }
                                }, composer4, 54), composer4, (i55 << 3) & 112, 14155776, 32768);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer4.skipToGroupEnd();
                        }
                    }, composer3, 54), composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer3.skipToGroupEnd();
            }
        }, startRestartGroup, 54), startRestartGroup, ProvidedValue.$stable | 48);
        if (ComposerKt.isTraceInProgress()) {
        }
        textStyle4 = textStyle3;
        composer2 = startRestartGroup;
        function216 = function28;
        z10 = z8;
        z11 = z16222222;
        z12 = z9;
        keyboardOptions3 = keyboardOptions2;
        keyboardActions3 = keyboardActions2;
        z13 = z7;
        i41 = i37;
        i42 = i38;
        visualTransformation3 = visualTransformation2;
        function217 = function215;
        function218 = function211;
        function219 = function212;
        function220 = function213;
        shape3 = shape2;
        mutableInteractionSource4 = mutableInteractionSource2;
        textFieldColors3 = textFieldColors2;
        function221 = function214;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    public static final void OutlinedTextFieldLayout(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, Function3<? super Modifier, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final Function2<? super Composer, ? super Integer, Unit> function25, final Function2<? super Composer, ? super Integer, Unit> function26, final boolean z, float f, final Function1<? super Size, Unit> function1, final Function2<? super Composer, ? super Integer, Unit> function27, Function2<? super Composer, ? super Integer, Unit> function28, PaddingValues paddingValues, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        PaddingValues paddingValues2;
        int i5;
        float f2;
        Function3<? super Modifier, ? super Composer, ? super Integer, Unit> function32;
        float f3;
        Function2<? super Composer, ? super Integer, Unit> function29;
        Composer startRestartGroup = composer.startRestartGroup(1408290209);
        ComposerKt.sourceInformation(startRestartGroup, "C(OutlinedTextFieldLayout)P(4,12,7,2,3,13,8,10,9!1,5!1,11)470@24558L267,478@24873L7,479@24885L3739:OutlinedTextField.kt#uh7d8r");
        if ((i & 6) == 0) {
            i3 = i | (startRestartGroup.changed(modifier) ? 4 : 2);
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= startRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= startRestartGroup.changedInstance(function3) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= startRestartGroup.changedInstance(function22) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i3 |= startRestartGroup.changedInstance(function23) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i3 |= startRestartGroup.changedInstance(function24) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i3 |= startRestartGroup.changedInstance(function25) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i3 |= startRestartGroup.changedInstance(function26) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i3 |= startRestartGroup.changed(z) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & i) == 0) {
            i3 |= startRestartGroup.changed(f) ? 536870912 : 268435456;
        }
        int i6 = i3;
        if ((i2 & 6) == 0) {
            i4 = i2 | (startRestartGroup.changedInstance(function1) ? 4 : 2);
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= startRestartGroup.changedInstance(function27) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i4 |= startRestartGroup.changedInstance(function28) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            paddingValues2 = paddingValues;
            i4 |= startRestartGroup.changed(paddingValues2) ? 2048 : 1024;
        } else {
            paddingValues2 = paddingValues;
        }
        int i7 = i4;
        if ((i6 & 306783379) != 306783378 || (i7 & 1171) != 1170 || !startRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1408290209, i6, i7, "androidx.compose.material3.OutlinedTextFieldLayout (OutlinedTextField.kt:468)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 602705309, "CC(remember):OutlinedTextField.kt#9igjgp");
            boolean z2 = ((i7 & 7168) == 2048) | ((i7 & 14) == 4) | ((234881024 & i6) == 67108864) | ((1879048192 & i6) == 536870912);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new OutlinedTextFieldMeasurePolicy(function1, z, f, paddingValues2);
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy = (OutlinedTextFieldMeasurePolicy) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            LayoutDirection layoutDirection = (LayoutDirection) consume;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(constructor);
            } else {
                startRestartGroup.useNode();
            }
            Composer m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
            Updater.m3912setimpl(m3905constructorimpl, outlinedTextFieldMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -828374317, "C482@24954L11,550@27430L186:OutlinedTextField.kt#uh7d8r");
            function27.invoke(startRestartGroup, Integer.valueOf((i7 >> 3) & 14));
            startRestartGroup.startReplaceGroup(250370369);
            ComposerKt.sourceInformation(startRestartGroup, "485@25018L219");
            if (function23 != null) {
                Modifier then = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.LeadingId).then(TextFieldImplKt.getIconDefaultSizeModifier());
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, then);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                startRestartGroup.startReusableNode();
                if (startRestartGroup.getInserting()) {
                    startRestartGroup.createNode(constructor2);
                } else {
                    startRestartGroup.useNode();
                }
                Composer m3905constructorimpl2 = Updater.m3905constructorimpl(startRestartGroup);
                Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -367907446, "C489@25210L9:OutlinedTextField.kt#uh7d8r");
                function23.invoke(startRestartGroup, Integer.valueOf((i6 >> 12) & 14));
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            }
            startRestartGroup.endReplaceGroup();
            startRestartGroup.startReplaceGroup(250379492);
            ComposerKt.sourceInformation(startRestartGroup, "493@25304L221");
            if (function24 != null) {
                Modifier then2 = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.TrailingId).then(TextFieldImplKt.getIconDefaultSizeModifier());
                Alignment center2 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(startRestartGroup, then2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                startRestartGroup.startReusableNode();
                if (startRestartGroup.getInserting()) {
                    startRestartGroup.createNode(constructor3);
                } else {
                    startRestartGroup.useNode();
                }
                Composer m3905constructorimpl3 = Updater.m3905constructorimpl(startRestartGroup);
                Updater.m3912setimpl(m3905constructorimpl3, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3912setimpl(m3905constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (m3905constructorimpl3.getInserting() || !Intrinsics.areEqual(m3905constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    m3905constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    m3905constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m3912setimpl(m3905constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -367622711, "C497@25497L10:OutlinedTextField.kt#uh7d8r");
                function24.invoke(startRestartGroup, Integer.valueOf((i6 >> 15) & 14));
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            }
            startRestartGroup.endReplaceGroup();
            float calculateStartPadding = PaddingKt.calculateStartPadding(paddingValues2, layoutDirection);
            float calculateEndPadding = PaddingKt.calculateEndPadding(paddingValues2, layoutDirection);
            if (function23 != null) {
                i5 = 0;
                calculateStartPadding = Dp.m7399constructorimpl(RangesKt.coerceAtLeast(Dp.m7399constructorimpl(calculateStartPadding - TextFieldImplKt.getHorizontalIconPadding()), Dp.m7399constructorimpl(0)));
            } else {
                i5 = 0;
            }
            float f4 = calculateStartPadding;
            if (function24 != null) {
                calculateEndPadding = Dp.m7399constructorimpl(RangesKt.coerceAtLeast(Dp.m7399constructorimpl(calculateEndPadding - TextFieldImplKt.getHorizontalIconPadding()), Dp.m7399constructorimpl(i5)));
            }
            startRestartGroup.startReplaceGroup(250410106);
            ComposerKt.sourceInformation(startRestartGroup, "518@26256L309");
            if (function25 != null) {
                Modifier m882paddingqDBjuR0$default = PaddingKt.m882paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m918heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.PrefixId), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), f4, 0.0f, TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, 10, null);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap4 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(startRestartGroup, m882paddingqDBjuR0$default);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                startRestartGroup.startReusableNode();
                if (startRestartGroup.getInserting()) {
                    startRestartGroup.createNode(constructor4);
                } else {
                    startRestartGroup.useNode();
                }
                Composer m3905constructorimpl4 = Updater.m3905constructorimpl(startRestartGroup);
                Updater.m3912setimpl(m3905constructorimpl4, maybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3912setimpl(m3905constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (m3905constructorimpl4.getInserting() || !Intrinsics.areEqual(m3905constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    m3905constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    m3905constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                }
                Updater.m3912setimpl(m3905constructorimpl4, materializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -366589109, "C524@26539L8:OutlinedTextField.kt#uh7d8r");
                function25.invoke(startRestartGroup, Integer.valueOf((i6 >> 18) & 14));
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            }
            startRestartGroup.endReplaceGroup();
            startRestartGroup.startReplaceGroup(250422072);
            ComposerKt.sourceInformation(startRestartGroup, "528@26630L307");
            if (function26 != null) {
                f2 = calculateEndPadding;
                Modifier m882paddingqDBjuR0$default2 = PaddingKt.m882paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m918heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.SuffixId), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, f2, 0.0f, 10, null);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap5 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier5 = ComposedModifierKt.materializeModifier(startRestartGroup, m882paddingqDBjuR0$default2);
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                startRestartGroup.startReusableNode();
                if (startRestartGroup.getInserting()) {
                    startRestartGroup.createNode(constructor5);
                } else {
                    startRestartGroup.useNode();
                }
                Composer m3905constructorimpl5 = Updater.m3905constructorimpl(startRestartGroup);
                Updater.m3912setimpl(m3905constructorimpl5, maybeCachedBoxMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3912setimpl(m3905constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (m3905constructorimpl5.getInserting() || !Intrinsics.areEqual(m3905constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                    m3905constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                    m3905constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
                }
                Updater.m3912setimpl(m3905constructorimpl5, materializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -366220085, "C534@26911L8:OutlinedTextField.kt#uh7d8r");
                function26.invoke(startRestartGroup, Integer.valueOf((i6 >> 21) & 14));
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            } else {
                f2 = calculateEndPadding;
            }
            startRestartGroup.endReplaceGroup();
            Modifier wrapContentHeight$default = SizeKt.wrapContentHeight$default(SizeKt.m918heightInVpY3zN4$default(Modifier.INSTANCE, TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null);
            if (function25 != null) {
                f4 = Dp.m7399constructorimpl(0);
            }
            Modifier m882paddingqDBjuR0$default3 = PaddingKt.m882paddingqDBjuR0$default(wrapContentHeight$default, f4, 0.0f, function26 == null ? f2 : Dp.m7399constructorimpl(0), 0.0f, 10, null);
            startRestartGroup.startReplaceGroup(250444361);
            ComposerKt.sourceInformation(startRestartGroup, "547@27339L63");
            if (function3 != null) {
                function32 = function3;
                function32.invoke(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.PlaceholderId).then(m882paddingqDBjuR0$default3), startRestartGroup, Integer.valueOf((i6 >> 3) & 112));
            } else {
                function32 = function3;
            }
            startRestartGroup.endReplaceGroup();
            Modifier then3 = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.TextFieldId).then(m882paddingqDBjuR0$default3);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap6 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier6 = ComposedModifierKt.materializeModifier(startRestartGroup, then3);
            Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(constructor6);
            } else {
                startRestartGroup.useNode();
            }
            Composer m3905constructorimpl6 = Updater.m3905constructorimpl(startRestartGroup);
            Updater.m3912setimpl(m3905constructorimpl6, maybeCachedBoxMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl6.getInserting() || !Intrinsics.areEqual(m3905constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                m3905constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                m3905constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
            }
            Updater.m3912setimpl(m3905constructorimpl6, materializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -365545432, "C554@27591L11:OutlinedTextField.kt#uh7d8r");
            function2.invoke(startRestartGroup, Integer.valueOf((i6 >> 3) & 14));
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.startReplaceGroup(250455481);
            ComposerKt.sourceInformation(startRestartGroup, "558@27667L501");
            if (function22 != null) {
                f3 = f;
                Modifier layoutId = LayoutIdKt.layoutId(SizeKt.wrapContentHeight$default(SizeKt.m918heightInVpY3zN4$default(Modifier.INSTANCE, DpKt.m7442lerpMdfbLM(TextFieldImplKt.getMinTextLineHeight(), TextFieldImplKt.getMinFocusedLabelLineHeight(), f3), 0.0f, 2, null), null, false, 3, null), TextFieldImplKt.LabelId);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap7 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier7 = ComposedModifierKt.materializeModifier(startRestartGroup, layoutId);
                Function0<ComposeUiNode> constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                startRestartGroup.startReusableNode();
                if (startRestartGroup.getInserting()) {
                    startRestartGroup.createNode(constructor7);
                } else {
                    startRestartGroup.useNode();
                }
                Composer m3905constructorimpl7 = Updater.m3905constructorimpl(startRestartGroup);
                Updater.m3912setimpl(m3905constructorimpl7, maybeCachedBoxMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3912setimpl(m3905constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (m3905constructorimpl7.getInserting() || !Intrinsics.areEqual(m3905constructorimpl7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                    m3905constructorimpl7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                    m3905constructorimpl7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
                }
                Updater.m3912setimpl(m3905constructorimpl7, materializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -364997972, "C570@28143L7:OutlinedTextField.kt#uh7d8r");
                function22.invoke(startRestartGroup, Integer.valueOf((i6 >> 9) & 14));
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            } else {
                f3 = f;
            }
            startRestartGroup.endReplaceGroup();
            startRestartGroup.startReplaceGroup(250473414);
            ComposerKt.sourceInformation(startRestartGroup, "575@28238L317");
            if (function28 != null) {
                Modifier padding = PaddingKt.padding(SizeKt.wrapContentHeight$default(SizeKt.m918heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.SupportingId), TextFieldImplKt.getMinSupportingTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldDefaults.m2826supportingTextPaddinga9UjIt4$material3_release$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null));
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap8 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier8 = ComposedModifierKt.materializeModifier(startRestartGroup, padding);
                Function0<ComposeUiNode> constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                startRestartGroup.startReusableNode();
                if (startRestartGroup.getInserting()) {
                    startRestartGroup.createNode(constructor8);
                } else {
                    startRestartGroup.useNode();
                }
                Composer m3905constructorimpl8 = Updater.m3905constructorimpl(startRestartGroup);
                Updater.m3912setimpl(m3905constructorimpl8, maybeCachedBoxMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3912setimpl(m3905constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (m3905constructorimpl8.getInserting() || !Intrinsics.areEqual(m3905constructorimpl8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
                    m3905constructorimpl8.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash8));
                    m3905constructorimpl8.apply(Integer.valueOf(currentCompositeKeyHash8), setCompositeKeyHash8);
                }
                Updater.m3912setimpl(m3905constructorimpl8, materializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -364618873, "C581@28525L12:OutlinedTextField.kt#uh7d8r");
                function29 = function28;
                function29.invoke(startRestartGroup, Integer.valueOf((i7 >> 6) & 14));
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            } else {
                function29 = function28;
            }
            startRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
            function29 = function28;
            function32 = function3;
            f3 = f;
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            final Function2<? super Composer, ? super Integer, Unit> function210 = function29;
            final Function3<? super Modifier, ? super Composer, ? super Integer, Unit> function33 = function32;
            final float f5 = f3;
            final PaddingValues paddingValues3 = paddingValues2;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextFieldLayout$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i8) {
                    OutlinedTextFieldKt.OutlinedTextFieldLayout(Modifier.this, function2, function33, function22, function23, function24, function25, function26, z, f5, function1, function27, function210, paddingValues3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateWidth-DHJA7U0, reason: not valid java name */
    public static final int m2508calculateWidthDHJA7U0(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f, long j, float f2, PaddingValues paddingValues) {
        int i8 = i3 + i4;
        return Math.max(i + Math.max(i5 + i8, Math.max(i7 + i8, MathHelpersKt.lerp(i6, 0, f))) + i2, Math.max(MathKt.roundToInt((i6 + (Dp.m7399constructorimpl(paddingValues.mo822calculateLeftPaddingu2uoSUM(LayoutDirection.Ltr) + paddingValues.mo823calculateRightPaddingu2uoSUM(LayoutDirection.Ltr)) * f2)) * f), Constraints.m7344getMinWidthimpl(j)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateHeight-mKXJcVc, reason: not valid java name */
    public static final int m2507calculateHeightmKXJcVc(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f, long j, float f2, PaddingValues paddingValues) {
        int maxOf = ComparisonsKt.maxOf(i5, i7, i3, i4, MathHelpersKt.lerp(i6, 0, f));
        float top = paddingValues.getTop() * f2;
        return Math.max(Constraints.m7343getMinHeightimpl(j), Math.max(i, Math.max(i2, MathKt.roundToInt(MathHelpersKt.lerp(top, Math.max(top, i6 / 2.0f), f) + maxOf + (paddingValues.getBottom() * f2)))) + i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void place(Placeable.PlacementScope placementScope, int i, int i2, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Placeable placeable6, Placeable placeable7, Placeable placeable8, Placeable placeable9, float f, boolean z, float f2, LayoutDirection layoutDirection, PaddingValues paddingValues) {
        Placeable placeable10;
        boolean z2;
        int i3;
        int i4;
        Placeable.PlacementScope.m6126place70tqf50$default(placementScope, placeable8, IntOffset.INSTANCE.m7542getZeronOccac(), 0.0f, 2, null);
        int heightOrZero = i - TextFieldImplKt.heightOrZero(placeable9);
        int roundToInt = MathKt.roundToInt(paddingValues.getTop() * f2);
        int roundToInt2 = MathKt.roundToInt(PaddingKt.calculateStartPadding(paddingValues, layoutDirection) * f2);
        float horizontalIconPadding = TextFieldImplKt.getHorizontalIconPadding() * f2;
        if (placeable != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, Alignment.INSTANCE.getCenterVertically().align(placeable.getHeight(), heightOrZero), 0.0f, 4, null);
        }
        if (placeable6 != null) {
            placeable10 = placeable6;
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable10, MathKt.roundToInt(placeable == null ? 0.0f : (TextFieldImplKt.widthOrZero(placeable) - horizontalIconPadding) * (1 - f)) + roundToInt2, MathHelpersKt.lerp(z ? Alignment.INSTANCE.getCenterVertically().align(placeable6.getHeight(), heightOrZero) : roundToInt, -(placeable6.getHeight() / 2), f), 0.0f, 4, null);
        } else {
            placeable10 = placeable6;
        }
        if (placeable3 != null) {
            i4 = roundToInt;
            i3 = heightOrZero;
            z2 = z;
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, TextFieldImplKt.widthOrZero(placeable), place$calculateVerticalPosition(z, heightOrZero, roundToInt, placeable10, placeable3), 0.0f, 4, null);
        } else {
            z2 = z;
            i3 = heightOrZero;
            i4 = roundToInt;
        }
        int widthOrZero = TextFieldImplKt.widthOrZero(placeable) + TextFieldImplKt.widthOrZero(placeable3);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, widthOrZero, place$calculateVerticalPosition(z2, i3, i4, placeable10, placeable5), 0.0f, 4, null);
        if (placeable7 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable7, widthOrZero, place$calculateVerticalPosition(z2, i3, i4, placeable10, placeable7), 0.0f, 4, null);
        }
        if (placeable4 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, (i2 - TextFieldImplKt.widthOrZero(placeable2)) - placeable4.getWidth(), place$calculateVerticalPosition(z2, i3, i4, placeable10, placeable4), 0.0f, 4, null);
        }
        if (placeable2 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i2 - placeable2.getWidth(), Alignment.INSTANCE.getCenterVertically().align(placeable2.getHeight(), i3), 0.0f, 4, null);
        }
        if (placeable9 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable9, 0, i3, 0.0f, 4, null);
        }
    }

    private static final int place$calculateVerticalPosition(boolean z, int i, int i2, Placeable placeable, Placeable placeable2) {
        if (z) {
            i2 = Alignment.INSTANCE.getCenterVertically().align(placeable2.getHeight(), i);
        }
        return Math.max(i2, TextFieldImplKt.heightOrZero(placeable) / 2);
    }

    public static final Modifier outlineCutout(Modifier modifier, final Function0<Size> function0, final PaddingValues paddingValues) {
        return DrawModifierKt.drawWithContent(modifier, new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$outlineCutout$1

            /* compiled from: OutlinedTextField.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[LayoutDirection.values().length];
                    try {
                        iArr[LayoutDirection.Rtl.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope contentDrawScope) {
                float f;
                float coerceAtLeast;
                long m4417unboximpl = function0.invoke().m4417unboximpl();
                float m4412getWidthimpl = Size.m4412getWidthimpl(m4417unboximpl);
                if (m4412getWidthimpl > 0.0f) {
                    f = OutlinedTextFieldKt.OutlinedTextFieldInnerPadding;
                    float f2 = contentDrawScope.mo459toPx0680j_4(f);
                    float f3 = contentDrawScope.mo459toPx0680j_4(paddingValues.mo822calculateLeftPaddingu2uoSUM(contentDrawScope.getLayoutDirection())) - f2;
                    float f4 = 2;
                    float f5 = m4412getWidthimpl + f3 + (f2 * f4);
                    if (WhenMappings.$EnumSwitchMapping$0[contentDrawScope.getLayoutDirection().ordinal()] == 1) {
                        coerceAtLeast = Size.m4412getWidthimpl(contentDrawScope.mo5121getSizeNHjbRc()) - f5;
                    } else {
                        coerceAtLeast = RangesKt.coerceAtLeast(f3, 0.0f);
                    }
                    float f6 = coerceAtLeast;
                    if (WhenMappings.$EnumSwitchMapping$0[contentDrawScope.getLayoutDirection().ordinal()] == 1) {
                        f5 = Size.m4412getWidthimpl(contentDrawScope.mo5121getSizeNHjbRc()) - RangesKt.coerceAtLeast(f3, 0.0f);
                    }
                    float f7 = f5;
                    float m4409getHeightimpl = Size.m4409getHeightimpl(m4417unboximpl);
                    float f8 = (-m4409getHeightimpl) / f4;
                    float f9 = m4409getHeightimpl / f4;
                    int m4603getDifferencertfAjoo = ClipOp.INSTANCE.m4603getDifferencertfAjoo();
                    DrawContext drawContext = contentDrawScope.getDrawContext();
                    long mo5128getSizeNHjbRc = drawContext.mo5128getSizeNHjbRc();
                    drawContext.getCanvas().save();
                    try {
                        drawContext.getTransform().mo5131clipRectN_I0leg(f6, f8, f7, f9, m4603getDifferencertfAjoo);
                        contentDrawScope.drawContent();
                        return;
                    } finally {
                        drawContext.getCanvas().restore();
                        drawContext.mo5129setSizeuvyYCjk(mo5128getSizeNHjbRc);
                    }
                }
                contentDrawScope.drawContent();
            }
        });
    }

    public static final long getOutlinedTextFieldTopPadding() {
        return OutlinedTextFieldTopPadding;
    }

    static {
        long m3762getBodySmallLineHeightXSAIIZE = TypeScaleTokens.INSTANCE.m3762getBodySmallLineHeightXSAIIZE();
        TextUnitKt.m7610checkArithmeticR2X_6o(m3762getBodySmallLineHeightXSAIIZE);
        OutlinedTextFieldTopPadding = TextUnitKt.pack(TextUnit.m7595getRawTypeimpl(m3762getBodySmallLineHeightXSAIIZE), TextUnit.m7597getValueimpl(m3762getBodySmallLineHeightXSAIIZE) / 2);
    }
}
