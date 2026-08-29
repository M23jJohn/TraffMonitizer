package androidx.compose.material3.internal;

import androidx.autofill.HintConstants;
import androidx.compose.animation.ColorVectorConverterKt;
import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldKt;
import androidx.compose.material3.Typography;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutIdParentData;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

/* compiled from: TextFieldImpl.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0002\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00012\u0011\u00102\u001a\r\u0012\u0004\u0012\u00020.03¢\u0006\u0002\b42\u0006\u00105\u001a\u0002062\u0013\u00107\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u00108\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u00109\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u0010:\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u0010;\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u0010<\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u0010=\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020?2\b\b\u0002\u0010A\u001a\u00020?2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0011\u0010H\u001a\r\u0012\u0004\u0012\u00020.03¢\u0006\u0002\b4H\u0001¢\u0006\u0002\u0010I\u001a-\u0010J\u001a\u00020.2\u0006\u0010K\u001a\u00020L2\u0011\u0010M\u001a\r\u0012\u0004\u0012\u00020.03¢\u0006\u0002\b4H\u0003ø\u0001\u0000¢\u0006\u0004\bN\u0010O\u001a5\u0010J\u001a\u00020.2\u0006\u0010K\u001a\u00020L2\u0006\u0010P\u001a\u00020Q2\u0011\u0010M\u001a\r\u0012\u0004\u0012\u00020.03¢\u0006\u0002\b4H\u0003ø\u0001\u0000¢\u0006\u0004\bR\u0010S\u001a×\u0001\u0010T\u001a\u00020.2\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020L2\u0006\u0010X\u001a\u00020L2\u0006\u0010Y\u001a\u00020L2\u0006\u0010Z\u001a\u00020?2\u0099\u0001\u0010M\u001a\u0094\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020]0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(`\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020L0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(a\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020L0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(b\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020]0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(c\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020]0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(d\u0012\u0004\u0012\u00020.0[¢\u0006\u0002\b4H\u0083\bø\u0001\u0000¢\u0006\u0004\be\u0010f\u001aH\u0010g\u001a\b\u0012\u0004\u0012\u00020h0\\2\u0006\u0010@\u001a\u00020?2\u0006\u0010A\u001a\u00020?2\u0006\u0010i\u001a\u00020?2\u0006\u0010F\u001a\u00020G2\u0006\u0010j\u001a\u00020\u00032\u0006\u0010k\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0004\bl\u0010m\u001a\u0012\u0010n\u001a\u00020\u00142\b\u0010o\u001a\u0004\u0018\u00010pH\u0000\u001a\u0012\u0010q\u001a\u00020\u00142\b\u0010o\u001a\u0004\u0018\u00010pH\u0000\u001a\u001c\u0010r\u001a\u00020\b*\u00020\b2\u0006\u0010A\u001a\u00020?2\u0006\u0010s\u001a\u00020\u0001H\u0000\u001a\u001c\u0010t\u001a\u00020\b*\u00020\b2\u0006\u0010u\u001a\u00020v2\u0006\u0010w\u001a\u00020xH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\r\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u000e\u0010\u0005\"\u0016\u0010\u000f\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0010\u0010\u0005\"\u0016\u0010\u0011\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0012\u0010\u0005\"\u000e\u0010\u0013\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0018\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0019\u0010\u0005\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u001c\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u001d\u0010\u0005\"\u000e\u0010\u001e\u001a\u00020\u0014X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010 \u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b!\u0010\u0005\"\u000e\u0010\"\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010#\u001a\u00020$X\u0080\u0004¢\u0006\n\n\u0002\u0010'\u001a\u0004\b%\u0010&\"\u001a\u0010(\u001a\u0004\u0018\u00010)*\u00020*8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006y²\u0006\n\u0010z\u001a\u00020?X\u008a\u0084\u0002²\u0006\n\u0010{\u001a\u00020?X\u008a\u0084\u0002"}, d2 = {"ContainerId", "", "HorizontalIconPadding", "Landroidx/compose/ui/unit/Dp;", "getHorizontalIconPadding", "()F", "F", "IconDefaultSizeModifier", "Landroidx/compose/ui/Modifier;", "getIconDefaultSizeModifier", "()Landroidx/compose/ui/Modifier;", "LabelId", "LeadingId", "MinFocusedLabelLineHeight", "getMinFocusedLabelLineHeight", "MinSupportingTextLineHeight", "getMinSupportingTextLineHeight", "MinTextLineHeight", "getMinTextLineHeight", "PlaceholderAnimationDelayOrDuration", "", "PlaceholderAnimationDuration", "PlaceholderId", "PrefixId", "PrefixSuffixTextPadding", "getPrefixSuffixTextPadding", "SuffixId", "SupportingId", "SupportingTopPadding", "getSupportingTopPadding", "TextFieldAnimationDuration", "TextFieldId", "TextFieldPadding", "getTextFieldPadding", "TrailingId", "ZeroConstraints", "Landroidx/compose/ui/unit/Constraints;", "getZeroConstraints", "()J", "J", "layoutId", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "getLayoutId", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;)Ljava/lang/Object;", "CommonDecorationBox", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/material3/internal/TextFieldType;", "value", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "singleLine", "", "enabled", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "colors", "Landroidx/compose/material3/TextFieldColors;", "container", "(Landroidx/compose/material3/internal/TextFieldType;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/material3/TextFieldColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "Decoration", "contentColor", "Landroidx/compose/ui/graphics/Color;", "content", "Decoration-Iv8Zu3U", "(JLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "Decoration-3J-VO9M", "(JLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TextFieldTransitionScope", "inputState", "Landroidx/compose/material3/internal/InputPhase;", "focusedLabelTextStyleColor", "unfocusedLabelTextStyleColor", "labelColor", "showLabel", "Lkotlin/Function5;", "Landroidx/compose/runtime/State;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "labelProgress", "labelTextStyleColor", "labelContentColor", "placeholderOpacity", "prefixSuffixOpacity", "TextFieldTransitionScope-Jy8F4Js", "(Landroidx/compose/material3/internal/InputPhase;JJJZLkotlin/jvm/functions/Function7;Landroidx/compose/runtime/Composer;I)V", "animateBorderStrokeAsState", "Landroidx/compose/foundation/BorderStroke;", "focused", "focusedBorderThickness", "unfocusedBorderThickness", "animateBorderStrokeAsState-NuRrP5Q", "(ZZZLandroidx/compose/material3/TextFieldColors;FFLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "heightOrZero", "placeable", "Landroidx/compose/ui/layout/Placeable;", "widthOrZero", "defaultErrorSemantics", "defaultErrorMessage", "textFieldBackground", "color", "Landroidx/compose/ui/graphics/ColorProducer;", "shape", "Landroidx/compose/ui/graphics/Shape;", "material3_release", "showPlaceholder", "showPrefixSuffix"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldImplKt {
    public static final String ContainerId = "Container";
    private static final Modifier IconDefaultSizeModifier;
    public static final String LabelId = "Label";
    public static final String LeadingId = "Leading";
    private static final float MinFocusedLabelLineHeight;
    private static final float MinSupportingTextLineHeight;
    private static final int PlaceholderAnimationDelayOrDuration = 67;
    private static final int PlaceholderAnimationDuration = 83;
    public static final String PlaceholderId = "Hint";
    public static final String PrefixId = "Prefix";
    public static final String SuffixId = "Suffix";
    public static final String SupportingId = "Supporting";
    public static final int TextFieldAnimationDuration = 150;
    public static final String TextFieldId = "TextField";
    private static final float TextFieldPadding;
    public static final String TrailingId = "Trailing";
    private static final long ZeroConstraints = ConstraintsKt.Constraints(0, 0, 0, 0);
    private static final float HorizontalIconPadding = Dp.m7399constructorimpl(12);
    private static final float SupportingTopPadding = Dp.m7399constructorimpl(4);
    private static final float PrefixSuffixTextPadding = Dp.m7399constructorimpl(2);
    private static final float MinTextLineHeight = Dp.m7399constructorimpl(24);

    /* compiled from: TextFieldImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TextFieldType.values().length];
            try {
                iArr[TextFieldType.Filled.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextFieldType.Outlined.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[InputPhase.values().length];
            try {
                iArr2[InputPhase.Focused.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[InputPhase.UnfocusedEmpty.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[InputPhase.UnfocusedNotEmpty.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:345:0x0583, code lost:
    
        if (r34 != false) goto L342;
     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x052d, code lost:
    
        if (r34 != false) goto L323;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x047f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0496  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x04fd  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0538  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0557  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0575  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x058e  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x05ef  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x060d  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0626  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0645  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0663  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x067c  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x06d1  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x06f0  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x06fb  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0717  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x074c  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0767  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0772  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x078e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x07a9  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x07b4  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x080b  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0821  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x083d  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0872  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x087f  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x089b  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x08aa  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x08ff  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x094a  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x096e  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x09c0  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x09e4  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0a30  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0a70  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0a97  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0ac5  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0aed  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0c0a  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0ba9  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0acb  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0a9c  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0a72  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0906  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x08a3  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0817  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x07ac  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x079a  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x076a  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0758  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x06f3  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x06df  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x0654  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x05fe  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0c1f  */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void CommonDecorationBox(final TextFieldType textFieldType, final String str, final Function2<? super Composer, ? super Integer, Unit> function2, final VisualTransformation visualTransformation, final Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, Function2<? super Composer, ? super Integer, Unit> function28, boolean z, boolean z2, boolean z3, final InteractionSource interactionSource, final PaddingValues paddingValues, final TextFieldColors textFieldColors, Function2<? super Composer, ? super Integer, Unit> function29, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        int i5;
        Function2<? super Composer, ? super Integer, Unit> function210;
        int i6;
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
        int i20;
        int i21;
        int i22;
        final Function2<? super Composer, ? super Integer, Unit> function211;
        final Function2<? super Composer, ? super Integer, Unit> function212;
        Function2<? super Composer, ? super Integer, Unit> function213;
        Function2<? super Composer, ? super Integer, Unit> function214;
        Function2<? super Composer, ? super Integer, Unit> function215;
        boolean z4;
        Object rememberedValue;
        long m2812labelColorXeAY9LY$material3_release;
        boolean z5;
        long m6878getColor0d7_KjU;
        long m6878getColor0d7_KjU2;
        long j;
        int i23;
        float f;
        Float f2;
        InputPhase inputPhase;
        int i24;
        float f3;
        Transition transition;
        TwoWayConverter<Float, AnimationVector1D> twoWayConverter;
        InputPhase inputPhase2;
        int i25;
        float f4;
        Float f5;
        InputPhase inputPhase3;
        int i26;
        float f6;
        TwoWayConverter<Float, AnimationVector1D> twoWayConverter2;
        InputPhase inputPhase4;
        int i27;
        float f7;
        Float f8;
        InputPhase inputPhase5;
        int i28;
        float f9;
        final State createTransitionAnimation;
        Function2<? super Composer, ? super Integer, Unit> function216;
        Transition transition2;
        InputPhase inputPhase6;
        boolean changed;
        Object rememberedValue2;
        InputPhase inputPhase7;
        TwoWayConverter twoWayConverter3;
        Color color;
        InputPhase inputPhase8;
        State state;
        Transition transition3;
        boolean changed2;
        Object rememberedValue3;
        int i29;
        Composer composer2;
        TextStyle textStyle;
        final TextStyle textStyle2;
        final float f10;
        ComposableLambda rememberComposableLambda;
        Object rememberedValue4;
        final TextStyle textStyle3;
        Function2<? super Composer, ? super Integer, Unit> function217;
        ComposableLambda composableLambda;
        Object rememberedValue5;
        State state2;
        final TextStyle textStyle4;
        State state3;
        final State state4;
        Function2<? super Composer, ? super Integer, Unit> function218;
        ComposableLambda composableLambda2;
        ComposableLambda composableLambda3;
        ComposableLambda composableLambda4;
        final Function2<? super Composer, ? super Integer, Unit> function219;
        ComposableLambda composableLambda5;
        ComposableLambda composableLambda6;
        ComposableLambda rememberComposableLambda2;
        final Function2<? super Composer, ? super Integer, Unit> function220;
        int i30;
        ComposableLambda rememberComposableLambda3;
        int i31;
        final Function2<? super Composer, ? super Integer, Unit> function221;
        final boolean z6;
        final Function2<? super Composer, ? super Integer, Unit> function222;
        final boolean z7;
        final Function2<? super Composer, ? super Integer, Unit> function223;
        final Function2<? super Composer, ? super Integer, Unit> function224;
        final boolean z8;
        final Function2<? super Composer, ? super Integer, Unit> function225;
        final Function2<? super Composer, ? super Integer, Unit> function226;
        final Function2<? super Composer, ? super Integer, Unit> function227;
        ScopeUpdateScope endRestartGroup;
        int i32;
        int i33;
        Composer startRestartGroup = composer.startRestartGroup(1514469103);
        ComposerKt.sourceInformation(startRestartGroup, "C(CommonDecorationBox)P(15,16,4,17,7,9,8,14,10,12,13,11,3,6,5,2)98@4054L121,104@4251L25,114@4594L10,121@4911L7503:TextFieldImpl.kt#mqatfk");
        if ((i3 & 1) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = (startRestartGroup.changed(textFieldType) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        if ((i3 & 2) != 0) {
            i4 |= 48;
        } else if ((i & 48) == 0) {
            i4 |= startRestartGroup.changed(str) ? 32 : 16;
            if ((i3 & 4) == 0) {
                i4 |= 384;
            } else if ((i & 384) == 0) {
                i4 |= startRestartGroup.changedInstance(function2) ? 256 : 128;
                if ((i3 & 8) != 0) {
                    i4 |= 3072;
                } else if ((i & 3072) == 0) {
                    i4 |= startRestartGroup.changed(visualTransformation) ? 2048 : 1024;
                }
                if ((i3 & 16) != 0) {
                    i4 |= 24576;
                } else if ((i & 24576) == 0) {
                    i4 |= startRestartGroup.changedInstance(function22) ? 16384 : 8192;
                }
                i5 = i3 & 32;
                if (i5 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function210 = function23;
                } else {
                    function210 = function23;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        i4 |= startRestartGroup.changedInstance(function210) ? 131072 : 65536;
                    }
                }
                i6 = i3 & 64;
                if (i6 != 0) {
                    i4 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    i4 |= startRestartGroup.changedInstance(function24) ? 1048576 : 524288;
                }
                i7 = i3 & 128;
                if (i7 != 0) {
                    i4 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    i8 = i7;
                    i4 |= startRestartGroup.changedInstance(function25) ? 8388608 : 4194304;
                    i9 = i3 & 256;
                    if (i9 == 0) {
                        i4 |= 100663296;
                    } else if ((i & 100663296) == 0) {
                        i10 = i9;
                        i4 |= startRestartGroup.changedInstance(function26) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        i11 = i3 & 512;
                        if (i11 != 0) {
                            i4 |= 805306368;
                        } else if ((i & 805306368) == 0) {
                            i12 = i11;
                            i4 |= startRestartGroup.changedInstance(function27) ? 536870912 : 268435456;
                            i13 = i3 & 1024;
                            if (i13 == 0) {
                                i15 = i2 | 6;
                                i14 = i13;
                            } else if ((i2 & 6) == 0) {
                                i14 = i13;
                                i15 = i2 | (startRestartGroup.changedInstance(function28) ? 4 : 2);
                            } else {
                                i14 = i13;
                                i15 = i2;
                            }
                            i16 = i3 & 2048;
                            if (i16 == 0) {
                                i15 |= 48;
                                i17 = i16;
                            } else if ((i2 & 48) == 0) {
                                i17 = i16;
                                i15 |= startRestartGroup.changed(z) ? 32 : 16;
                            } else {
                                i17 = i16;
                            }
                            int i34 = i15;
                            i18 = i3 & 4096;
                            if (i18 == 0) {
                                i19 = i34 | 384;
                            } else {
                                int i35 = i34;
                                if ((i2 & 384) == 0) {
                                    i35 |= startRestartGroup.changed(z2) ? 256 : 128;
                                }
                                i19 = i35;
                            }
                            i20 = i3 & 8192;
                            if (i20 == 0) {
                                i21 = i19 | 3072;
                            } else {
                                int i36 = i19;
                                if ((i2 & 3072) == 0) {
                                    i36 |= startRestartGroup.changed(z3) ? 2048 : 1024;
                                }
                                i21 = i36;
                            }
                            if ((i3 & 16384) == 0) {
                                i21 |= 24576;
                            } else if ((i2 & 24576) == 0) {
                                i21 |= startRestartGroup.changed(interactionSource) ? 16384 : 8192;
                            }
                            if ((32768 & i3) == 0) {
                                i22 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            } else {
                                if ((196608 & i2) == 0) {
                                    i22 = startRestartGroup.changed(paddingValues) ? 131072 : 65536;
                                }
                                if ((65536 & i3) == 0) {
                                    i33 = (1572864 & i2) == 0 ? startRestartGroup.changed(textFieldColors) ? 1048576 : 524288 : 1572864;
                                    if ((131072 & i3) != 0) {
                                        i32 = (12582912 & i2) == 0 ? startRestartGroup.changedInstance(function29) ? 8388608 : 4194304 : 12582912;
                                        if ((306783379 & i4) != 306783378 && (4793491 & i21) == 4793490 && startRestartGroup.getSkipping()) {
                                            startRestartGroup.skipToGroupEnd();
                                            function226 = function24;
                                            function224 = function26;
                                            function225 = function27;
                                            function222 = function28;
                                            z8 = z;
                                            z7 = z3;
                                            function221 = function29;
                                            composer2 = startRestartGroup;
                                            function227 = function210;
                                            function223 = function25;
                                            z6 = z2;
                                        } else {
                                            if (i5 != 0) {
                                                function210 = null;
                                            }
                                            function211 = i6 != 0 ? null : function24;
                                            function212 = i8 != 0 ? null : function25;
                                            function213 = i10 != 0 ? null : function26;
                                            function214 = i12 != 0 ? null : function27;
                                            Function2<? super Composer, ? super Integer, Unit> function228 = i14 != 0 ? null : function28;
                                            boolean z9 = i17 != 0 ? false : z;
                                            boolean z10 = i18 != 0 ? true : z2;
                                            boolean z11 = i20 != 0 ? false : z3;
                                            if (ComposerKt.isTraceInProgress()) {
                                                function215 = function210;
                                                ComposerKt.traceEventStart(1514469103, i4, i21, "androidx.compose.material3.internal.CommonDecorationBox (TextFieldImpl.kt:96)");
                                            } else {
                                                function215 = function210;
                                            }
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 318599163, "CC(remember):TextFieldImpl.kt#9igjgp");
                                            int i37 = i21;
                                            z4 = ((i4 & 112) == 32) | ((i4 & 7168) == 2048);
                                            rememberedValue = startRestartGroup.rememberedValue();
                                            if (!z4 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue = visualTransformation.filter(new AnnotatedString(str, null, null, 6, null));
                                                startRestartGroup.updateRememberedValue(rememberedValue);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            String text = ((TransformedText) rememberedValue).getText().getText();
                                            boolean booleanValue = FocusInteractionKt.collectIsFocusedAsState(interactionSource, startRestartGroup, (i37 >> 12) & 14).getValue().booleanValue();
                                            InputPhase inputPhase9 = booleanValue ? InputPhase.Focused : text.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
                                            m2812labelColorXeAY9LY$material3_release = textFieldColors.m2812labelColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                            int i38 = i4;
                                            Typography typography = MaterialTheme.INSTANCE.getTypography(startRestartGroup, 6);
                                            final TextStyle bodyLarge = typography.getBodyLarge();
                                            final TextStyle bodySmall = typography.getBodySmall();
                                            z5 = !(Color.m4616equalsimpl0(bodyLarge.m6878getColor0d7_KjU(), Color.INSTANCE.m4651getUnspecified0d7_KjU()) || Color.m4616equalsimpl0(bodySmall.m6878getColor0d7_KjU(), Color.INSTANCE.m4651getUnspecified0d7_KjU())) || (!Color.m4616equalsimpl0(bodyLarge.m6878getColor0d7_KjU(), Color.INSTANCE.m4651getUnspecified0d7_KjU()) && Color.m4616equalsimpl0(bodySmall.m6878getColor0d7_KjU(), Color.INSTANCE.m4651getUnspecified0d7_KjU()));
                                            m6878getColor0d7_KjU = bodySmall.m6878getColor0d7_KjU();
                                            if (z5 && m6878getColor0d7_KjU == 16) {
                                                m6878getColor0d7_KjU = m2812labelColorXeAY9LY$material3_release;
                                            }
                                            m6878getColor0d7_KjU2 = bodyLarge.m6878getColor0d7_KjU();
                                            if (z5 && m6878getColor0d7_KjU2 == 16) {
                                                m6878getColor0d7_KjU2 = m2812labelColorXeAY9LY$material3_release;
                                            }
                                            boolean z12 = function22 != null;
                                            final boolean z13 = z5;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1087703202, "CC(TextFieldTransitionScope)P(2,1:c#ui.graphics.Color,5:c#ui.graphics.Color,3:c#ui.graphics.Color,4)349@14528L59,352@14632L334,364@15016L1126,394@16193L363,406@16607L318,418@17028L197,424@17231L150:TextFieldImpl.kt#mqatfk");
                                            boolean z14 = z12;
                                            Transition updateTransition = TransitionKt.updateTransition(inputPhase9, "TextFieldInputState", startRestartGroup, 48, 0);
                                            TextFieldImplKt$TextFieldTransitionScope$labelProgress$1 textFieldImplKt$TextFieldTransitionScope$labelProgress$1 = TextFieldImplKt$TextFieldTransitionScope$labelProgress$1.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                            InputPhase inputPhase10 = (InputPhase) updateTransition.getCurrentState();
                                            startRestartGroup.startReplaceGroup(-2036730335);
                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                            if (ComposerKt.isTraceInProgress()) {
                                                j = m6878getColor0d7_KjU;
                                                ComposerKt.traceEventStart(-2036730335, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:356)");
                                            } else {
                                                j = m6878getColor0d7_KjU;
                                            }
                                            i23 = WhenMappings.$EnumSwitchMapping$1[inputPhase10.ordinal()];
                                            if (i23 != 1) {
                                                if (i23 == 2) {
                                                    f = 0.0f;
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                    }
                                                    startRestartGroup.endReplaceGroup();
                                                    Float valueOf = Float.valueOf(f);
                                                    InputPhase inputPhase11 = (InputPhase) updateTransition.getTargetState();
                                                    startRestartGroup.startReplaceGroup(-2036730335);
                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        f2 = valueOf;
                                                        inputPhase = inputPhase11;
                                                    } else {
                                                        f2 = valueOf;
                                                        inputPhase = inputPhase11;
                                                        ComposerKt.traceEventStart(-2036730335, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:356)");
                                                    }
                                                    i24 = WhenMappings.$EnumSwitchMapping$1[inputPhase.ordinal()];
                                                    if (i24 != 1) {
                                                        if (i24 == 2) {
                                                            f3 = 0.0f;
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventEnd();
                                                            }
                                                            startRestartGroup.endReplaceGroup();
                                                            State createTransitionAnimation2 = TransitionKt.createTransitionAnimation(updateTransition, f2, Float.valueOf(f3), textFieldImplKt$TextFieldTransitionScope$labelProgress$1.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter, "LabelProgress", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                            TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                                            TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                            InputPhase inputPhase12 = (InputPhase) updateTransition.getCurrentState();
                                                            startRestartGroup.startReplaceGroup(1435837472);
                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                transition = updateTransition;
                                                                twoWayConverter = vectorConverter2;
                                                                inputPhase2 = inputPhase12;
                                                                ComposerKt.traceEventStart(1435837472, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:386)");
                                                            } else {
                                                                transition = updateTransition;
                                                                twoWayConverter = vectorConverter2;
                                                                inputPhase2 = inputPhase12;
                                                            }
                                                            i25 = WhenMappings.$EnumSwitchMapping$1[inputPhase2.ordinal()];
                                                            if (i25 != 1) {
                                                                if (i25 != 2) {
                                                                    if (i25 != 3) {
                                                                        throw new NoWhenBranchMatchedException();
                                                                    }
                                                                }
                                                                f4 = 0.0f;
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventEnd();
                                                                }
                                                                startRestartGroup.endReplaceGroup();
                                                                Float valueOf2 = Float.valueOf(f4);
                                                                InputPhase inputPhase13 = (InputPhase) transition.getTargetState();
                                                                startRestartGroup.startReplaceGroup(1435837472);
                                                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    f5 = valueOf2;
                                                                    inputPhase3 = inputPhase13;
                                                                } else {
                                                                    f5 = valueOf2;
                                                                    inputPhase3 = inputPhase13;
                                                                    ComposerKt.traceEventStart(1435837472, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:386)");
                                                                }
                                                                i26 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
                                                                if (i26 != 1) {
                                                                    if (i26 != 2) {
                                                                        if (i26 != 3) {
                                                                            throw new NoWhenBranchMatchedException();
                                                                        }
                                                                    }
                                                                    f6 = 0.0f;
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                        ComposerKt.traceEventEnd();
                                                                    }
                                                                    startRestartGroup.endReplaceGroup();
                                                                    final State createTransitionAnimation3 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                                                    TwoWayConverter<Float, AnimationVector1D> vectorConverter3 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                                    InputPhase inputPhase14 = (InputPhase) transition.getCurrentState();
                                                                    startRestartGroup.startReplaceGroup(1128033978);
                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                        twoWayConverter2 = vectorConverter3;
                                                                        inputPhase4 = inputPhase14;
                                                                        ComposerKt.traceEventStart(1128033978, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:398)");
                                                                    } else {
                                                                        twoWayConverter2 = vectorConverter3;
                                                                        inputPhase4 = inputPhase14;
                                                                    }
                                                                    i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
                                                                    if (i27 != 1) {
                                                                        if (i27 != 2) {
                                                                            if (i27 != 3) {
                                                                                throw new NoWhenBranchMatchedException();
                                                                            }
                                                                        } else if (z14) {
                                                                            f7 = 0.0f;
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                                ComposerKt.traceEventEnd();
                                                                            }
                                                                            startRestartGroup.endReplaceGroup();
                                                                            Float valueOf3 = Float.valueOf(f7);
                                                                            InputPhase inputPhase15 = (InputPhase) transition.getTargetState();
                                                                            startRestartGroup.startReplaceGroup(1128033978);
                                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                                f8 = valueOf3;
                                                                                inputPhase5 = inputPhase15;
                                                                            } else {
                                                                                f8 = valueOf3;
                                                                                inputPhase5 = inputPhase15;
                                                                                ComposerKt.traceEventStart(1128033978, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:398)");
                                                                            }
                                                                            i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                                                                            if (i28 != 1) {
                                                                                if (i28 != 2) {
                                                                                    if (i28 != 3) {
                                                                                        throw new NoWhenBranchMatchedException();
                                                                                    }
                                                                                } else if (z14) {
                                                                                    f9 = 0.0f;
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        ComposerKt.traceEventEnd();
                                                                                    }
                                                                                    startRestartGroup.endReplaceGroup();
                                                                                    createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                                    Transition transition4 = transition;
                                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                                    TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                                                                    InputPhase inputPhase16 = (InputPhase) transition4.getTargetState();
                                                                                    startRestartGroup.startReplaceGroup(-107432127);
                                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        transition2 = transition4;
                                                                                        function216 = function228;
                                                                                        inputPhase6 = inputPhase16;
                                                                                        ComposerKt.traceEventStart(-107432127, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:410)");
                                                                                    } else {
                                                                                        function216 = function228;
                                                                                        transition2 = transition4;
                                                                                        inputPhase6 = inputPhase16;
                                                                                    }
                                                                                    long j2 = WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1 ? j : m6878getColor0d7_KjU2;
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        ComposerKt.traceEventEnd();
                                                                                    }
                                                                                    startRestartGroup.endReplaceGroup();
                                                                                    ColorSpace m4619getColorSpaceimpl = Color.m4619getColorSpaceimpl(j2);
                                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                                                                    changed = startRestartGroup.changed(m4619getColorSpaceimpl);
                                                                                    rememberedValue2 = startRestartGroup.rememberedValue();
                                                                                    if (!changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                                                                        rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl);
                                                                                        startRestartGroup.updateRememberedValue(rememberedValue2);
                                                                                    }
                                                                                    TwoWayConverter twoWayConverter4 = (TwoWayConverter) rememberedValue2;
                                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                                                    InputPhase inputPhase17 = (InputPhase) transition2.getCurrentState();
                                                                                    startRestartGroup.startReplaceGroup(-107432127);
                                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        inputPhase7 = inputPhase17;
                                                                                        twoWayConverter3 = twoWayConverter4;
                                                                                        ComposerKt.traceEventStart(-107432127, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:410)");
                                                                                    } else {
                                                                                        inputPhase7 = inputPhase17;
                                                                                        twoWayConverter3 = twoWayConverter4;
                                                                                    }
                                                                                    long j3 = WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1 ? j : m6878getColor0d7_KjU2;
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        ComposerKt.traceEventEnd();
                                                                                    }
                                                                                    startRestartGroup.endReplaceGroup();
                                                                                    Color m4605boximpl = Color.m4605boximpl(j3);
                                                                                    InputPhase inputPhase18 = (InputPhase) transition2.getTargetState();
                                                                                    startRestartGroup.startReplaceGroup(-107432127);
                                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        color = m4605boximpl;
                                                                                        inputPhase8 = inputPhase18;
                                                                                        ComposerKt.traceEventStart(-107432127, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:410)");
                                                                                    } else {
                                                                                        color = m4605boximpl;
                                                                                        inputPhase8 = inputPhase18;
                                                                                    }
                                                                                    long j4 = WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1 ? j : m6878getColor0d7_KjU2;
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        ComposerKt.traceEventEnd();
                                                                                    }
                                                                                    startRestartGroup.endReplaceGroup();
                                                                                    State createTransitionAnimation4 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                                    Transition transition5 = transition2;
                                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                                    TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$1 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                                                                    startRestartGroup.startReplaceGroup(1023351670);
                                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        state = createTransitionAnimation4;
                                                                                        transition3 = transition5;
                                                                                        ComposerKt.traceEventStart(1023351670, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:421)");
                                                                                    } else {
                                                                                        state = createTransitionAnimation4;
                                                                                        transition3 = transition5;
                                                                                    }
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        ComposerKt.traceEventEnd();
                                                                                    }
                                                                                    startRestartGroup.endReplaceGroup();
                                                                                    ColorSpace m4619getColorSpaceimpl2 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                                                                    changed2 = startRestartGroup.changed(m4619getColorSpaceimpl2);
                                                                                    rememberedValue3 = startRestartGroup.rememberedValue();
                                                                                    if (!changed2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                                                                        rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl2);
                                                                                        startRestartGroup.updateRememberedValue(rememberedValue3);
                                                                                    }
                                                                                    TwoWayConverter twoWayConverter5 = (TwoWayConverter) rememberedValue3;
                                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                                                    startRestartGroup.startReplaceGroup(1023351670);
                                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        ComposerKt.traceEventStart(1023351670, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:421)");
                                                                                    }
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        ComposerKt.traceEventEnd();
                                                                                    }
                                                                                    startRestartGroup.endReplaceGroup();
                                                                                    Color m4605boximpl2 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                                                                                    startRestartGroup.startReplaceGroup(1023351670);
                                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        i29 = 0;
                                                                                        ComposerKt.traceEventStart(1023351670, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:421)");
                                                                                    } else {
                                                                                        i29 = 0;
                                                                                    }
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        ComposerKt.traceEventEnd();
                                                                                    }
                                                                                    startRestartGroup.endReplaceGroup();
                                                                                    final State createTransitionAnimation5 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl2, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$1.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter5, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                                    composer2 = startRestartGroup;
                                                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                                    ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                                                                                    final float floatValue = ((Number) createTransitionAnimation2.getValue()).floatValue();
                                                                                    composer2.startReplaceGroup(-156998101);
                                                                                    ComposerKt.sourceInformation(composer2, "*138@5668L506");
                                                                                    if (function22 == null) {
                                                                                        f10 = floatValue;
                                                                                        textStyle = bodyLarge;
                                                                                        textStyle2 = bodySmall;
                                                                                        rememberComposableLambda = null;
                                                                                    } else {
                                                                                        final State state5 = state;
                                                                                        Function2<Composer, Integer, Unit> function229 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedLabel$1$1
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

                                                                                            public final void invoke(Composer composer3, int i39) {
                                                                                                ComposerKt.sourceInformation(composer3, "C147@6101L55:TextFieldImpl.kt#mqatfk");
                                                                                                if ((i39 & 3) != 2 || !composer3.getSkipping()) {
                                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                                        ComposerKt.traceEventStart(-1236585568, i39, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:139)");
                                                                                                    }
                                                                                                    TextStyle lerp = TextStyleKt.lerp(TextStyle.this, bodySmall, floatValue);
                                                                                                    boolean z15 = z13;
                                                                                                    State<Color> state6 = state5;
                                                                                                    if (z15) {
                                                                                                        lerp = TextStyle.m6861copyp1EtxEg$default(lerp, state6.getValue().m4625unboximpl(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null);
                                                                                                    }
                                                                                                    TextFieldImplKt.m3162Decoration3JVO9M(createTransitionAnimation5.getValue().m4625unboximpl(), lerp, function22, composer3, 0);
                                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                                        ComposerKt.traceEventEnd();
                                                                                                        return;
                                                                                                    }
                                                                                                    return;
                                                                                                }
                                                                                                composer3.skipToGroupEnd();
                                                                                            }
                                                                                        };
                                                                                        textStyle = bodyLarge;
                                                                                        textStyle2 = bodySmall;
                                                                                        f10 = floatValue;
                                                                                        rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1236585568, true, function229, composer2, 54);
                                                                                    }
                                                                                    composer2.endReplaceGroup();
                                                                                    final long m2814placeholderColorXeAY9LY$material3_release = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                                    ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                                    rememberedValue4 = composer2.rememberedValue();
                                                                                    textStyle3 = textStyle;
                                                                                    if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                                                        rememberedValue4 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Boolean>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$showPlaceholder$2$1
                                                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                                            {
                                                                                                super(0);
                                                                                            }

                                                                                            /* JADX WARN: Can't rename method to resolve collision */
                                                                                            @Override // kotlin.jvm.functions.Function0
                                                                                            public final Boolean invoke() {
                                                                                                return Boolean.valueOf(createTransitionAnimation3.getValue().floatValue() > 0.0f);
                                                                                            }
                                                                                        });
                                                                                        composer2.updateRememberedValue(rememberedValue4);
                                                                                    }
                                                                                    State state6 = (State) rememberedValue4;
                                                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                                    composer2.startReplaceGroup(-156965270);
                                                                                    ComposerKt.sourceInformation(composer2, "160@6765L362");
                                                                                    if (function215 == null && text.length() == 0 && CommonDecorationBox$lambda$15$lambda$7(state6)) {
                                                                                        final Function2<? super Composer, ? super Integer, Unit> function230 = function215;
                                                                                        Function3<Modifier, Composer, Integer, Unit> function3 = new Function3<Modifier, Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedPlaceholder$1
                                                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                                            /* JADX WARN: Multi-variable type inference failed */
                                                                                            {
                                                                                                super(3);
                                                                                            }

                                                                                            @Override // kotlin.jvm.functions.Function3
                                                                                            public /* bridge */ /* synthetic */ Unit invoke(Modifier modifier, Composer composer3, Integer num) {
                                                                                                invoke(modifier, composer3, num.intValue());
                                                                                                return Unit.INSTANCE;
                                                                                            }

                                                                                            public final void invoke(Modifier modifier, Composer composer3, int i39) {
                                                                                                ComposerKt.sourceInformation(composer3, "C161@6826L34,161@6799L310:TextFieldImpl.kt#mqatfk");
                                                                                                if ((i39 & 6) == 0) {
                                                                                                    i39 |= composer3.changed(modifier) ? 4 : 2;
                                                                                                }
                                                                                                if ((i39 & 19) == 18 && composer3.getSkipping()) {
                                                                                                    composer3.skipToGroupEnd();
                                                                                                    return;
                                                                                                }
                                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                                    ComposerKt.traceEventStart(-660524084, i39, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:161)");
                                                                                                }
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -1325817438, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                                                boolean changed3 = composer3.changed(createTransitionAnimation3);
                                                                                                final State<Float> state7 = createTransitionAnimation3;
                                                                                                Object rememberedValue6 = composer3.rememberedValue();
                                                                                                if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                                                                    rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedPlaceholder$1$1$1
                                                                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                                                        {
                                                                                                            super(1);
                                                                                                        }

                                                                                                        @Override // kotlin.jvm.functions.Function1
                                                                                                        public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                                                                                            invoke2(graphicsLayerScope);
                                                                                                            return Unit.INSTANCE;
                                                                                                        }

                                                                                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                                                        public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                                                                                                            graphicsLayerScope.setAlpha(state7.getValue().floatValue());
                                                                                                        }
                                                                                                    };
                                                                                                    composer3.updateRememberedValue(rememberedValue6);
                                                                                                }
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                Modifier graphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifier, (Function1) rememberedValue6);
                                                                                                long j5 = m2814placeholderColorXeAY9LY$material3_release;
                                                                                                TextStyle textStyle5 = textStyle3;
                                                                                                Function2<Composer, Integer, Unit> function231 = function230;
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                                                                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                                                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                                                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                                                                                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, graphicsLayer);
                                                                                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                                                                if (!(composer3.getApplier() instanceof Applier)) {
                                                                                                    ComposablesKt.invalidApplier();
                                                                                                }
                                                                                                composer3.startReusableNode();
                                                                                                if (composer3.getInserting()) {
                                                                                                    composer3.createNode(constructor);
                                                                                                } else {
                                                                                                    composer3.useNode();
                                                                                                }
                                                                                                Composer m3905constructorimpl = Updater.m3905constructorimpl(composer3);
                                                                                                Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                                                                Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                                                                if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                                                                    m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                                                                    m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                                                                                }
                                                                                                Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                                                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -1245776707, "C162@6888L199:TextFieldImpl.kt#mqatfk");
                                                                                                TextFieldImplKt.m3162Decoration3JVO9M(j5, textStyle5, function231, composer3, 0);
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                composer3.endNode();
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                                    ComposerKt.traceEventEnd();
                                                                                                }
                                                                                            }
                                                                                        };
                                                                                        function217 = function230;
                                                                                        textStyle3 = textStyle3;
                                                                                        composableLambda = ComposableLambdaKt.rememberComposableLambda(-660524084, true, function3, composer2, 54);
                                                                                    } else {
                                                                                        function217 = function215;
                                                                                        composableLambda = null;
                                                                                    }
                                                                                    composer2.endReplaceGroup();
                                                                                    final long m2815prefixColorXeAY9LY$material3_release = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                                    ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                                    rememberedValue5 = composer2.rememberedValue();
                                                                                    if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                                                                        rememberedValue5 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Boolean>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$showPrefixSuffix$2$1
                                                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                                            {
                                                                                                super(0);
                                                                                            }

                                                                                            /* JADX WARN: Can't rename method to resolve collision */
                                                                                            @Override // kotlin.jvm.functions.Function0
                                                                                            public final Boolean invoke() {
                                                                                                return Boolean.valueOf(createTransitionAnimation.getValue().floatValue() > 0.0f);
                                                                                            }
                                                                                        });
                                                                                        composer2.updateRememberedValue(rememberedValue5);
                                                                                    }
                                                                                    state2 = (State) rememberedValue5;
                                                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                                    composer2.startReplaceGroup(-156940524);
                                                                                    ComposerKt.sourceInformation(composer2, "177@7507L341");
                                                                                    if (function213 == null && CommonDecorationBox$lambda$15$lambda$9(state2)) {
                                                                                        textStyle4 = textStyle3;
                                                                                        state4 = createTransitionAnimation;
                                                                                        final Function2<? super Composer, ? super Integer, Unit> function231 = function213;
                                                                                        Function2<Composer, Integer, Unit> function232 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedPrefix$1
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

                                                                                            public final void invoke(Composer composer3, int i39) {
                                                                                                ComposerKt.sourceInformation(composer3, "C178@7556L35,178@7529L301:TextFieldImpl.kt#mqatfk");
                                                                                                if ((i39 & 3) == 2 && composer3.getSkipping()) {
                                                                                                    composer3.skipToGroupEnd();
                                                                                                    return;
                                                                                                }
                                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                                    ComposerKt.traceEventStart(274398694, i39, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:178)");
                                                                                                }
                                                                                                Modifier.Companion companion = Modifier.INSTANCE;
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -1325794077, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                                                boolean changed3 = composer3.changed(state4);
                                                                                                final State<Float> state7 = state4;
                                                                                                Object rememberedValue6 = composer3.rememberedValue();
                                                                                                if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                                                                    rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedPrefix$1$1$1
                                                                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                                                        {
                                                                                                            super(1);
                                                                                                        }

                                                                                                        @Override // kotlin.jvm.functions.Function1
                                                                                                        public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                                                                                            invoke2(graphicsLayerScope);
                                                                                                            return Unit.INSTANCE;
                                                                                                        }

                                                                                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                                                        public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                                                                                                            graphicsLayerScope.setAlpha(state7.getValue().floatValue());
                                                                                                        }
                                                                                                    };
                                                                                                    composer3.updateRememberedValue(rememberedValue6);
                                                                                                }
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                Modifier graphicsLayer = GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) rememberedValue6);
                                                                                                long j5 = m2815prefixColorXeAY9LY$material3_release;
                                                                                                TextStyle textStyle5 = textStyle4;
                                                                                                Function2<Composer, Integer, Unit> function233 = function231;
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                                                                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                                                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                                                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                                                                                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, graphicsLayer);
                                                                                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                                                                if (!(composer3.getApplier() instanceof Applier)) {
                                                                                                    ComposablesKt.invalidApplier();
                                                                                                }
                                                                                                composer3.startReusableNode();
                                                                                                if (composer3.getInserting()) {
                                                                                                    composer3.createNode(constructor);
                                                                                                } else {
                                                                                                    composer3.useNode();
                                                                                                }
                                                                                                Composer m3905constructorimpl = Updater.m3905constructorimpl(composer3);
                                                                                                Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                                                                Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                                                                if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                                                                    m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                                                                    m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                                                                                }
                                                                                                Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                                                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -1245051865, "C179@7619L189:TextFieldImpl.kt#mqatfk");
                                                                                                TextFieldImplKt.m3162Decoration3JVO9M(j5, textStyle5, function233, composer3, 0);
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                composer3.endNode();
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                                    ComposerKt.traceEventEnd();
                                                                                                }
                                                                                            }
                                                                                        };
                                                                                        state3 = state2;
                                                                                        function218 = function231;
                                                                                        composableLambda2 = composableLambda;
                                                                                        composableLambda3 = ComposableLambdaKt.rememberComposableLambda(274398694, true, function232, composer2, 54);
                                                                                    } else {
                                                                                        textStyle4 = textStyle3;
                                                                                        state3 = state2;
                                                                                        state4 = createTransitionAnimation;
                                                                                        function218 = function213;
                                                                                        composableLambda2 = composableLambda;
                                                                                        composableLambda3 = null;
                                                                                    }
                                                                                    composer2.endReplaceGroup();
                                                                                    final long m2816suffixColorXeAY9LY$material3_release = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                                    composer2.startReplaceGroup(-156921964);
                                                                                    ComposerKt.sourceInformation(composer2, "191@8087L341");
                                                                                    if (function214 == null && CommonDecorationBox$lambda$15$lambda$9(state3)) {
                                                                                        function219 = function214;
                                                                                        composableLambda4 = composableLambda3;
                                                                                        composableLambda5 = ComposableLambdaKt.rememberComposableLambda(-1526229403, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedSuffix$1
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

                                                                                            public final void invoke(Composer composer3, int i39) {
                                                                                                ComposerKt.sourceInformation(composer3, "C192@8136L35,192@8109L301:TextFieldImpl.kt#mqatfk");
                                                                                                if ((i39 & 3) == 2 && composer3.getSkipping()) {
                                                                                                    composer3.skipToGroupEnd();
                                                                                                    return;
                                                                                                }
                                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                                    ComposerKt.traceEventStart(-1526229403, i39, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:192)");
                                                                                                }
                                                                                                Modifier.Companion companion = Modifier.INSTANCE;
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -1325775517, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                                                boolean changed3 = composer3.changed(state4);
                                                                                                final State<Float> state7 = state4;
                                                                                                Object rememberedValue6 = composer3.rememberedValue();
                                                                                                if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                                                                    rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedSuffix$1$1$1
                                                                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                                                        {
                                                                                                            super(1);
                                                                                                        }

                                                                                                        @Override // kotlin.jvm.functions.Function1
                                                                                                        public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                                                                                            invoke2(graphicsLayerScope);
                                                                                                            return Unit.INSTANCE;
                                                                                                        }

                                                                                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                                                        public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                                                                                                            graphicsLayerScope.setAlpha(state7.getValue().floatValue());
                                                                                                        }
                                                                                                    };
                                                                                                    composer3.updateRememberedValue(rememberedValue6);
                                                                                                }
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                Modifier graphicsLayer = GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) rememberedValue6);
                                                                                                long j5 = m2816suffixColorXeAY9LY$material3_release;
                                                                                                TextStyle textStyle5 = textStyle4;
                                                                                                Function2<Composer, Integer, Unit> function233 = function219;
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                                                                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                                                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                                                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                                                                                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, graphicsLayer);
                                                                                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                                                                if (!(composer3.getApplier() instanceof Applier)) {
                                                                                                    ComposablesKt.invalidApplier();
                                                                                                }
                                                                                                composer3.startReusableNode();
                                                                                                if (composer3.getInserting()) {
                                                                                                    composer3.createNode(constructor);
                                                                                                } else {
                                                                                                    composer3.useNode();
                                                                                                }
                                                                                                Composer m3905constructorimpl = Updater.m3905constructorimpl(composer3);
                                                                                                Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                                                                Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                                                                if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                                                                    m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                                                                    m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                                                                                }
                                                                                                Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                                                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -1244476505, "C193@8199L189:TextFieldImpl.kt#mqatfk");
                                                                                                TextFieldImplKt.m3162Decoration3JVO9M(j5, textStyle5, function233, composer3, 0);
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                composer3.endNode();
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                                    ComposerKt.traceEventEnd();
                                                                                                }
                                                                                            }
                                                                                        }, composer2, 54);
                                                                                    } else {
                                                                                        composableLambda4 = composableLambda3;
                                                                                        function219 = function214;
                                                                                        composableLambda5 = null;
                                                                                    }
                                                                                    composer2.endReplaceGroup();
                                                                                    final long m2813leadingIconColorXeAY9LY$material3_release = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                                    composer2.startReplaceGroup(-156902962);
                                                                                    ComposerKt.sourceInformation(composer2, "*205@8655L61");
                                                                                    ComposableLambda rememberComposableLambda4 = function211 == null ? null : ComposableLambdaKt.rememberComposableLambda(-130107406, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedLeading$1$1
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

                                                                                        public final void invoke(Composer composer3, int i39) {
                                                                                            ComposerKt.sourceInformation(composer3, "C205@8657L57:TextFieldImpl.kt#mqatfk");
                                                                                            if ((i39 & 3) == 2 && composer3.getSkipping()) {
                                                                                                composer3.skipToGroupEnd();
                                                                                                return;
                                                                                            }
                                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                                                ComposerKt.traceEventStart(-130107406, i39, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:205)");
                                                                                            }
                                                                                            TextFieldImplKt.m3163DecorationIv8Zu3U(m2813leadingIconColorXeAY9LY$material3_release, function211, composer3, 0);
                                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                                                ComposerKt.traceEventEnd();
                                                                                            }
                                                                                        }
                                                                                    }, composer2, 54);
                                                                                    composer2.endReplaceGroup();
                                                                                    final long m2819trailingIconColorXeAY9LY$material3_release = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                                    composer2.startReplaceGroup(-156893937);
                                                                                    ComposerKt.sourceInformation(composer2, "*211@8937L62");
                                                                                    if (function212 == null) {
                                                                                        composableLambda6 = rememberComposableLambda4;
                                                                                        rememberComposableLambda2 = null;
                                                                                    } else {
                                                                                        composableLambda6 = rememberComposableLambda4;
                                                                                        rememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(2079816678, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedTrailing$1$1
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

                                                                                            public final void invoke(Composer composer3, int i39) {
                                                                                                ComposerKt.sourceInformation(composer3, "C211@8939L58:TextFieldImpl.kt#mqatfk");
                                                                                                if ((i39 & 3) == 2 && composer3.getSkipping()) {
                                                                                                    composer3.skipToGroupEnd();
                                                                                                    return;
                                                                                                }
                                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                                    ComposerKt.traceEventStart(2079816678, i39, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:211)");
                                                                                                }
                                                                                                TextFieldImplKt.m3163DecorationIv8Zu3U(m2819trailingIconColorXeAY9LY$material3_release, function212, composer3, 0);
                                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                                    ComposerKt.traceEventEnd();
                                                                                                }
                                                                                            }
                                                                                        }, composer2, 54);
                                                                                    }
                                                                                    composer2.endReplaceGroup();
                                                                                    final long m2817supportingTextColorXeAY9LY$material3_release = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                                    composer2.startReplaceGroup(-156884470);
                                                                                    ComposerKt.sourceInformation(composer2, "*217@9228L217");
                                                                                    if (function216 == null) {
                                                                                        function220 = function216;
                                                                                        i30 = 1;
                                                                                        rememberComposableLambda3 = null;
                                                                                    } else {
                                                                                        function220 = function216;
                                                                                        i30 = 1;
                                                                                        rememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(1263707005, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedSupporting$1$1
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

                                                                                            public final void invoke(Composer composer3, int i39) {
                                                                                                ComposerKt.sourceInformation(composer3, "C218@9250L177:TextFieldImpl.kt#mqatfk");
                                                                                                if ((i39 & 3) != 2 || !composer3.getSkipping()) {
                                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                                        ComposerKt.traceEventStart(1263707005, i39, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:218)");
                                                                                                    }
                                                                                                    TextFieldImplKt.m3162Decoration3JVO9M(m2817supportingTextColorXeAY9LY$material3_release, textStyle2, function220, composer3, 0);
                                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                                        ComposerKt.traceEventEnd();
                                                                                                        return;
                                                                                                    }
                                                                                                    return;
                                                                                                }
                                                                                                composer3.skipToGroupEnd();
                                                                                            }
                                                                                        }, composer2, 54);
                                                                                    }
                                                                                    composer2.endReplaceGroup();
                                                                                    i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                                                                                    if (i31 == i30) {
                                                                                        function221 = function29;
                                                                                        composer2.startReplaceGroup(-568105095);
                                                                                        ComposerKt.sourceInformation(composer2, "228@9583L167,234@9768L769");
                                                                                        TextFieldKt.TextFieldLayout(Modifier.INSTANCE, function2, rememberComposableLambda, composableLambda2, composableLambda6, rememberComposableLambda2, composableLambda4, composableLambda5, z9, f10, ComposableLambdaKt.rememberComposableLambda(1750327932, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$containerWithId$1
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

                                                                                            public final void invoke(Composer composer3, int i39) {
                                                                                                ComposerKt.sourceInformation(composer3, "C229@9605L127:TextFieldImpl.kt#mqatfk");
                                                                                                if ((i39 & 3) != 2 || !composer3.getSkipping()) {
                                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                                        ComposerKt.traceEventStart(1750327932, i39, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:229)");
                                                                                                    }
                                                                                                    Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.ContainerId);
                                                                                                    Function2<Composer, Integer, Unit> function233 = function221;
                                                                                                    ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                                                                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true);
                                                                                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                                                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                                                                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                                                                                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, layoutId);
                                                                                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                                                                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                                                                    if (!(composer3.getApplier() instanceof Applier)) {
                                                                                                        ComposablesKt.invalidApplier();
                                                                                                    }
                                                                                                    composer3.startReusableNode();
                                                                                                    if (composer3.getInserting()) {
                                                                                                        composer3.createNode(constructor);
                                                                                                    } else {
                                                                                                        composer3.useNode();
                                                                                                    }
                                                                                                    Composer m3905constructorimpl = Updater.m3905constructorimpl(composer3);
                                                                                                    Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                                                                    Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                                                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                                                                    if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                                                                        m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                                                                        m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                                                                                    }
                                                                                                    Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                                                                                    ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                                                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                                                                    ComposerKt.sourceInformationMarkerStart(composer3, -1242994023, "C230@9699L11:TextFieldImpl.kt#mqatfk");
                                                                                                    function233.invoke(composer3, 0);
                                                                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                    composer3.endNode();
                                                                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                                        ComposerKt.traceEventEnd();
                                                                                                        return;
                                                                                                    }
                                                                                                    return;
                                                                                                }
                                                                                                composer3.skipToGroupEnd();
                                                                                            }
                                                                                        }, composer2, 54), rememberComposableLambda3, paddingValues, composer2, ((i38 >> 3) & 112) | 6 | ((i37 << 21) & 234881024), ((i37 >> 9) & 896) | 6);
                                                                                        composer2.endReplaceGroup();
                                                                                        Unit unit = Unit.INSTANCE;
                                                                                    } else if (i31 != 2) {
                                                                                        composer2.startReplaceGroup(-565271199);
                                                                                        composer2.endReplaceGroup();
                                                                                        Unit unit2 = Unit.INSTANCE;
                                                                                        function221 = function29;
                                                                                    } else {
                                                                                        composer2.startReplaceGroup(-567018607);
                                                                                        ComposerKt.sourceInformation(composer2, "253@10659L38,254@10766L314,275@11639L463,264@11098L1286");
                                                                                        ComposerKt.sourceInformationMarkerStart(composer2, -156837817, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                                        Object rememberedValue6 = composer2.rememberedValue();
                                                                                        if (rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                                                            rememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m4400boximpl(Size.INSTANCE.m4421getZeroNHjbRc()), null, 2, null);
                                                                                            composer2.updateRememberedValue(rememberedValue6);
                                                                                        }
                                                                                        final MutableState mutableState = (MutableState) rememberedValue6;
                                                                                        ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                                        function221 = function29;
                                                                                        ComposableLambda rememberComposableLambda5 = ComposableLambdaKt.rememberComposableLambda(157291737, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$borderContainerWithId$1
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

                                                                                            public final void invoke(Composer composer3, int i39) {
                                                                                                ComposerKt.sourceInformation(composer3, "C255@10788L274:TextFieldImpl.kt#mqatfk");
                                                                                                if ((i39 & 3) != 2 || !composer3.getSkipping()) {
                                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                                        ComposerKt.traceEventStart(157291737, i39, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:255)");
                                                                                                    }
                                                                                                    Modifier outlineCutout = OutlinedTextFieldKt.outlineCutout(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.ContainerId), new MutablePropertyReference0Impl(mutableState) { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$borderContainerWithId$1.1
                                                                                                        @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
                                                                                                        public Object get() {
                                                                                                            return ((MutableState) this.receiver).getValue();
                                                                                                        }

                                                                                                        @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
                                                                                                        public void set(Object obj) {
                                                                                                            ((MutableState) this.receiver).setValue(obj);
                                                                                                        }
                                                                                                    }, paddingValues);
                                                                                                    Function2<Composer, Integer, Unit> function233 = function221;
                                                                                                    ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                                                                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true);
                                                                                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                                                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                                                                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                                                                                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, outlineCutout);
                                                                                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                                                                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                                                                    if (!(composer3.getApplier() instanceof Applier)) {
                                                                                                        ComposablesKt.invalidApplier();
                                                                                                    }
                                                                                                    composer3.startReusableNode();
                                                                                                    if (composer3.getInserting()) {
                                                                                                        composer3.createNode(constructor);
                                                                                                    } else {
                                                                                                        composer3.useNode();
                                                                                                    }
                                                                                                    Composer m3905constructorimpl = Updater.m3905constructorimpl(composer3);
                                                                                                    Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                                                                    Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                                                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                                                                    if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                                                                        m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                                                                        m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                                                                                    }
                                                                                                    Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                                                                                    ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                                                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                                                                    ComposerKt.sourceInformationMarkerStart(composer3, -1241674663, "C260@11029L11:TextFieldImpl.kt#mqatfk");
                                                                                                    function233.invoke(composer3, 0);
                                                                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                    composer3.endNode();
                                                                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                                        ComposerKt.traceEventEnd();
                                                                                                        return;
                                                                                                    }
                                                                                                    return;
                                                                                                }
                                                                                                composer3.skipToGroupEnd();
                                                                                            }
                                                                                        }, composer2, 54);
                                                                                        Modifier.Companion companion = Modifier.INSTANCE;
                                                                                        ComposerKt.sourceInformationMarkerStart(composer2, -156806032, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                                        boolean changed3 = composer2.changed(f10);
                                                                                        Object rememberedValue7 = composer2.rememberedValue();
                                                                                        if (changed3 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                                                                            rememberedValue7 = (Function1) new Function1<Size, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$1$1
                                                                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                                                {
                                                                                                    super(1);
                                                                                                }

                                                                                                @Override // kotlin.jvm.functions.Function1
                                                                                                public /* bridge */ /* synthetic */ Unit invoke(Size size) {
                                                                                                    m3168invokeuvyYCjk(size.m4417unboximpl());
                                                                                                    return Unit.INSTANCE;
                                                                                                }

                                                                                                /* renamed from: invoke-uvyYCjk, reason: not valid java name */
                                                                                                public final void m3168invokeuvyYCjk(long j5) {
                                                                                                    float m4412getWidthimpl = Size.m4412getWidthimpl(j5) * f10;
                                                                                                    float m4409getHeightimpl = Size.m4409getHeightimpl(j5) * f10;
                                                                                                    if (Size.m4412getWidthimpl(mutableState.getValue().m4417unboximpl()) == m4412getWidthimpl && Size.m4409getHeightimpl(mutableState.getValue().m4417unboximpl()) == m4409getHeightimpl) {
                                                                                                        return;
                                                                                                    }
                                                                                                    mutableState.setValue(Size.m4400boximpl(SizeKt.Size(m4412getWidthimpl, m4409getHeightimpl)));
                                                                                                }
                                                                                            };
                                                                                            composer2.updateRememberedValue(rememberedValue7);
                                                                                        }
                                                                                        ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                                        OutlinedTextFieldKt.OutlinedTextFieldLayout(companion, function2, composableLambda2, rememberComposableLambda, composableLambda6, rememberComposableLambda2, composableLambda4, composableLambda5, z9, f10, (Function1) rememberedValue7, rememberComposableLambda5, rememberComposableLambda3, paddingValues, composer2, ((i38 >> 3) & 112) | 6 | ((i37 << 21) & 234881024), ((i37 >> 6) & 7168) | 48);
                                                                                        composer2.endReplaceGroup();
                                                                                        Unit unit3 = Unit.INSTANCE;
                                                                                    }
                                                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        ComposerKt.traceEventEnd();
                                                                                    }
                                                                                    z6 = z10;
                                                                                    function222 = function220;
                                                                                    z7 = z11;
                                                                                    function223 = function212;
                                                                                    function224 = function218;
                                                                                    z8 = z9;
                                                                                    function225 = function219;
                                                                                    function226 = function211;
                                                                                    function227 = function217;
                                                                                }
                                                                            }
                                                                            f9 = 1.0f;
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            startRestartGroup.endReplaceGroup();
                                                                            createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                            Transition transition42 = transition;
                                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                            TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                                                            InputPhase inputPhase162 = (InputPhase) transition42.getTargetState();
                                                                            startRestartGroup.startReplaceGroup(-107432127);
                                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
                                                                            }
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            startRestartGroup.endReplaceGroup();
                                                                            ColorSpace m4619getColorSpaceimpl3 = Color.m4619getColorSpaceimpl(j2);
                                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                                                            changed = startRestartGroup.changed(m4619getColorSpaceimpl3);
                                                                            rememberedValue2 = startRestartGroup.rememberedValue();
                                                                            if (!changed) {
                                                                            }
                                                                            rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl3);
                                                                            startRestartGroup.updateRememberedValue(rememberedValue2);
                                                                            TwoWayConverter twoWayConverter42 = (TwoWayConverter) rememberedValue2;
                                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                                            InputPhase inputPhase172 = (InputPhase) transition2.getCurrentState();
                                                                            startRestartGroup.startReplaceGroup(-107432127);
                                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
                                                                            }
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            startRestartGroup.endReplaceGroup();
                                                                            Color m4605boximpl3 = Color.m4605boximpl(j3);
                                                                            InputPhase inputPhase182 = (InputPhase) transition2.getTargetState();
                                                                            startRestartGroup.startReplaceGroup(-107432127);
                                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
                                                                            }
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            startRestartGroup.endReplaceGroup();
                                                                            State createTransitionAnimation42 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                            Transition transition52 = transition2;
                                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                            TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$12 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                                                            startRestartGroup.startReplaceGroup(1023351670);
                                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            startRestartGroup.endReplaceGroup();
                                                                            ColorSpace m4619getColorSpaceimpl22 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                                                            changed2 = startRestartGroup.changed(m4619getColorSpaceimpl22);
                                                                            rememberedValue3 = startRestartGroup.rememberedValue();
                                                                            if (!changed2) {
                                                                            }
                                                                            rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl22);
                                                                            startRestartGroup.updateRememberedValue(rememberedValue3);
                                                                            TwoWayConverter twoWayConverter52 = (TwoWayConverter) rememberedValue3;
                                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                                            startRestartGroup.startReplaceGroup(1023351670);
                                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            startRestartGroup.endReplaceGroup();
                                                                            Color m4605boximpl22 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                                                                            startRestartGroup.startReplaceGroup(1023351670);
                                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            startRestartGroup.endReplaceGroup();
                                                                            final State<Color> createTransitionAnimation52 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl22, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$12.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter52, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                            composer2 = startRestartGroup;
                                                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                            ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                                                                            final float floatValue2 = ((Number) createTransitionAnimation2.getValue()).floatValue();
                                                                            composer2.startReplaceGroup(-156998101);
                                                                            ComposerKt.sourceInformation(composer2, "*138@5668L506");
                                                                            if (function22 == null) {
                                                                            }
                                                                            composer2.endReplaceGroup();
                                                                            final long m2814placeholderColorXeAY9LY$material3_release2 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                            ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                            rememberedValue4 = composer2.rememberedValue();
                                                                            textStyle3 = textStyle;
                                                                            if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                                            }
                                                                            State state62 = (State) rememberedValue4;
                                                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                            composer2.startReplaceGroup(-156965270);
                                                                            ComposerKt.sourceInformation(composer2, "160@6765L362");
                                                                            if (function215 == null) {
                                                                            }
                                                                            function217 = function215;
                                                                            composableLambda = null;
                                                                            composer2.endReplaceGroup();
                                                                            final long m2815prefixColorXeAY9LY$material3_release2 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                            ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                            rememberedValue5 = composer2.rememberedValue();
                                                                            if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                                                            }
                                                                            state2 = (State) rememberedValue5;
                                                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                            composer2.startReplaceGroup(-156940524);
                                                                            ComposerKt.sourceInformation(composer2, "177@7507L341");
                                                                            if (function213 == null) {
                                                                            }
                                                                            textStyle4 = textStyle3;
                                                                            state3 = state2;
                                                                            state4 = createTransitionAnimation;
                                                                            function218 = function213;
                                                                            composableLambda2 = composableLambda;
                                                                            composableLambda3 = null;
                                                                            composer2.endReplaceGroup();
                                                                            final long m2816suffixColorXeAY9LY$material3_release2 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                            composer2.startReplaceGroup(-156921964);
                                                                            ComposerKt.sourceInformation(composer2, "191@8087L341");
                                                                            if (function214 == null) {
                                                                            }
                                                                            composableLambda4 = composableLambda3;
                                                                            function219 = function214;
                                                                            composableLambda5 = null;
                                                                            composer2.endReplaceGroup();
                                                                            final long m2813leadingIconColorXeAY9LY$material3_release2 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                            composer2.startReplaceGroup(-156902962);
                                                                            ComposerKt.sourceInformation(composer2, "*205@8655L61");
                                                                            if (function211 == null) {
                                                                            }
                                                                            composer2.endReplaceGroup();
                                                                            final long m2819trailingIconColorXeAY9LY$material3_release2 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                            composer2.startReplaceGroup(-156893937);
                                                                            ComposerKt.sourceInformation(composer2, "*211@8937L62");
                                                                            if (function212 == null) {
                                                                            }
                                                                            composer2.endReplaceGroup();
                                                                            final long m2817supportingTextColorXeAY9LY$material3_release2 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                            composer2.startReplaceGroup(-156884470);
                                                                            ComposerKt.sourceInformation(composer2, "*217@9228L217");
                                                                            if (function216 == null) {
                                                                            }
                                                                            composer2.endReplaceGroup();
                                                                            i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                                                                            if (i31 == i30) {
                                                                            }
                                                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                            }
                                                                            z6 = z10;
                                                                            function222 = function220;
                                                                            z7 = z11;
                                                                            function223 = function212;
                                                                            function224 = function218;
                                                                            z8 = z9;
                                                                            function225 = function219;
                                                                            function226 = function211;
                                                                            function227 = function217;
                                                                        }
                                                                    }
                                                                    f7 = 1.0f;
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    startRestartGroup.endReplaceGroup();
                                                                    Float valueOf32 = Float.valueOf(f7);
                                                                    InputPhase inputPhase152 = (InputPhase) transition.getTargetState();
                                                                    startRestartGroup.startReplaceGroup(1128033978);
                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                                                                    if (i28 != 1) {
                                                                    }
                                                                    f9 = 1.0f;
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    startRestartGroup.endReplaceGroup();
                                                                    createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                    Transition transition422 = transition;
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                                                    InputPhase inputPhase1622 = (InputPhase) transition422.getTargetState();
                                                                    startRestartGroup.startReplaceGroup(-107432127);
                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
                                                                    }
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    startRestartGroup.endReplaceGroup();
                                                                    ColorSpace m4619getColorSpaceimpl32 = Color.m4619getColorSpaceimpl(j2);
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                                                    changed = startRestartGroup.changed(m4619getColorSpaceimpl32);
                                                                    rememberedValue2 = startRestartGroup.rememberedValue();
                                                                    if (!changed) {
                                                                    }
                                                                    rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl32);
                                                                    startRestartGroup.updateRememberedValue(rememberedValue2);
                                                                    TwoWayConverter twoWayConverter422 = (TwoWayConverter) rememberedValue2;
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                                    InputPhase inputPhase1722 = (InputPhase) transition2.getCurrentState();
                                                                    startRestartGroup.startReplaceGroup(-107432127);
                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
                                                                    }
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    startRestartGroup.endReplaceGroup();
                                                                    Color m4605boximpl32 = Color.m4605boximpl(j3);
                                                                    InputPhase inputPhase1822 = (InputPhase) transition2.getTargetState();
                                                                    startRestartGroup.startReplaceGroup(-107432127);
                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
                                                                    }
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    startRestartGroup.endReplaceGroup();
                                                                    State createTransitionAnimation422 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                    Transition transition522 = transition2;
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$122 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                                                    startRestartGroup.startReplaceGroup(1023351670);
                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    startRestartGroup.endReplaceGroup();
                                                                    ColorSpace m4619getColorSpaceimpl222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                                                    changed2 = startRestartGroup.changed(m4619getColorSpaceimpl222);
                                                                    rememberedValue3 = startRestartGroup.rememberedValue();
                                                                    if (!changed2) {
                                                                    }
                                                                    rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl222);
                                                                    startRestartGroup.updateRememberedValue(rememberedValue3);
                                                                    TwoWayConverter twoWayConverter522 = (TwoWayConverter) rememberedValue3;
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                                    startRestartGroup.startReplaceGroup(1023351670);
                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    startRestartGroup.endReplaceGroup();
                                                                    Color m4605boximpl222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                                                                    startRestartGroup.startReplaceGroup(1023351670);
                                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    startRestartGroup.endReplaceGroup();
                                                                    final State<Color> createTransitionAnimation522 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$122.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter522, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                    composer2 = startRestartGroup;
                                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                    ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                                                                    final float floatValue22 = ((Number) createTransitionAnimation2.getValue()).floatValue();
                                                                    composer2.startReplaceGroup(-156998101);
                                                                    ComposerKt.sourceInformation(composer2, "*138@5668L506");
                                                                    if (function22 == null) {
                                                                    }
                                                                    composer2.endReplaceGroup();
                                                                    final long m2814placeholderColorXeAY9LY$material3_release22 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                    ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                    rememberedValue4 = composer2.rememberedValue();
                                                                    textStyle3 = textStyle;
                                                                    if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                                    }
                                                                    State state622 = (State) rememberedValue4;
                                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                    composer2.startReplaceGroup(-156965270);
                                                                    ComposerKt.sourceInformation(composer2, "160@6765L362");
                                                                    if (function215 == null) {
                                                                    }
                                                                    function217 = function215;
                                                                    composableLambda = null;
                                                                    composer2.endReplaceGroup();
                                                                    final long m2815prefixColorXeAY9LY$material3_release22 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                    ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                    rememberedValue5 = composer2.rememberedValue();
                                                                    if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                                                    }
                                                                    state2 = (State) rememberedValue5;
                                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                    composer2.startReplaceGroup(-156940524);
                                                                    ComposerKt.sourceInformation(composer2, "177@7507L341");
                                                                    if (function213 == null) {
                                                                    }
                                                                    textStyle4 = textStyle3;
                                                                    state3 = state2;
                                                                    state4 = createTransitionAnimation;
                                                                    function218 = function213;
                                                                    composableLambda2 = composableLambda;
                                                                    composableLambda3 = null;
                                                                    composer2.endReplaceGroup();
                                                                    final long m2816suffixColorXeAY9LY$material3_release22 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                    composer2.startReplaceGroup(-156921964);
                                                                    ComposerKt.sourceInformation(composer2, "191@8087L341");
                                                                    if (function214 == null) {
                                                                    }
                                                                    composableLambda4 = composableLambda3;
                                                                    function219 = function214;
                                                                    composableLambda5 = null;
                                                                    composer2.endReplaceGroup();
                                                                    final long m2813leadingIconColorXeAY9LY$material3_release22 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                    composer2.startReplaceGroup(-156902962);
                                                                    ComposerKt.sourceInformation(composer2, "*205@8655L61");
                                                                    if (function211 == null) {
                                                                    }
                                                                    composer2.endReplaceGroup();
                                                                    final long m2819trailingIconColorXeAY9LY$material3_release22 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                    composer2.startReplaceGroup(-156893937);
                                                                    ComposerKt.sourceInformation(composer2, "*211@8937L62");
                                                                    if (function212 == null) {
                                                                    }
                                                                    composer2.endReplaceGroup();
                                                                    final long m2817supportingTextColorXeAY9LY$material3_release22 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                    composer2.startReplaceGroup(-156884470);
                                                                    ComposerKt.sourceInformation(composer2, "*217@9228L217");
                                                                    if (function216 == null) {
                                                                    }
                                                                    composer2.endReplaceGroup();
                                                                    i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                                                                    if (i31 == i30) {
                                                                    }
                                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                    }
                                                                    z6 = z10;
                                                                    function222 = function220;
                                                                    z7 = z11;
                                                                    function223 = function212;
                                                                    function224 = function218;
                                                                    z8 = z9;
                                                                    function225 = function219;
                                                                    function226 = function211;
                                                                    function227 = function217;
                                                                }
                                                                f6 = 1.0f;
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                startRestartGroup.endReplaceGroup();
                                                                final State<Float> createTransitionAnimation32 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$12 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                                                TwoWayConverter<Float, AnimationVector1D> vectorConverter32 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                                InputPhase inputPhase142 = (InputPhase) transition.getCurrentState();
                                                                startRestartGroup.startReplaceGroup(1128033978);
                                                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
                                                                if (i27 != 1) {
                                                                }
                                                                f7 = 1.0f;
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                startRestartGroup.endReplaceGroup();
                                                                Float valueOf322 = Float.valueOf(f7);
                                                                InputPhase inputPhase1522 = (InputPhase) transition.getTargetState();
                                                                startRestartGroup.startReplaceGroup(1128033978);
                                                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                                                                if (i28 != 1) {
                                                                }
                                                                f9 = 1.0f;
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                startRestartGroup.endReplaceGroup();
                                                                createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$12.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                Transition transition4222 = transition;
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                                                InputPhase inputPhase16222 = (InputPhase) transition4222.getTargetState();
                                                                startRestartGroup.startReplaceGroup(-107432127);
                                                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
                                                                }
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                startRestartGroup.endReplaceGroup();
                                                                ColorSpace m4619getColorSpaceimpl322 = Color.m4619getColorSpaceimpl(j2);
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                                                changed = startRestartGroup.changed(m4619getColorSpaceimpl322);
                                                                rememberedValue2 = startRestartGroup.rememberedValue();
                                                                if (!changed) {
                                                                }
                                                                rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl322);
                                                                startRestartGroup.updateRememberedValue(rememberedValue2);
                                                                TwoWayConverter twoWayConverter4222 = (TwoWayConverter) rememberedValue2;
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                                InputPhase inputPhase17222 = (InputPhase) transition2.getCurrentState();
                                                                startRestartGroup.startReplaceGroup(-107432127);
                                                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
                                                                }
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                startRestartGroup.endReplaceGroup();
                                                                Color m4605boximpl322 = Color.m4605boximpl(j3);
                                                                InputPhase inputPhase18222 = (InputPhase) transition2.getTargetState();
                                                                startRestartGroup.startReplaceGroup(-107432127);
                                                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
                                                                }
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                startRestartGroup.endReplaceGroup();
                                                                State createTransitionAnimation4222 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                Transition transition5222 = transition2;
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$1222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                                                startRestartGroup.startReplaceGroup(1023351670);
                                                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                startRestartGroup.endReplaceGroup();
                                                                ColorSpace m4619getColorSpaceimpl2222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                                                changed2 = startRestartGroup.changed(m4619getColorSpaceimpl2222);
                                                                rememberedValue3 = startRestartGroup.rememberedValue();
                                                                if (!changed2) {
                                                                }
                                                                rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl2222);
                                                                startRestartGroup.updateRememberedValue(rememberedValue3);
                                                                TwoWayConverter twoWayConverter5222 = (TwoWayConverter) rememberedValue3;
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                                startRestartGroup.startReplaceGroup(1023351670);
                                                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                startRestartGroup.endReplaceGroup();
                                                                Color m4605boximpl2222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                                                                startRestartGroup.startReplaceGroup(1023351670);
                                                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                startRestartGroup.endReplaceGroup();
                                                                final State<Color> createTransitionAnimation5222 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl2222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$1222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter5222, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                                composer2 = startRestartGroup;
                                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                                                                final float floatValue222 = ((Number) createTransitionAnimation2.getValue()).floatValue();
                                                                composer2.startReplaceGroup(-156998101);
                                                                ComposerKt.sourceInformation(composer2, "*138@5668L506");
                                                                if (function22 == null) {
                                                                }
                                                                composer2.endReplaceGroup();
                                                                final long m2814placeholderColorXeAY9LY$material3_release222 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                rememberedValue4 = composer2.rememberedValue();
                                                                textStyle3 = textStyle;
                                                                if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                                }
                                                                State state6222 = (State) rememberedValue4;
                                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                composer2.startReplaceGroup(-156965270);
                                                                ComposerKt.sourceInformation(composer2, "160@6765L362");
                                                                if (function215 == null) {
                                                                }
                                                                function217 = function215;
                                                                composableLambda = null;
                                                                composer2.endReplaceGroup();
                                                                final long m2815prefixColorXeAY9LY$material3_release222 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                                rememberedValue5 = composer2.rememberedValue();
                                                                if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                                                }
                                                                state2 = (State) rememberedValue5;
                                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                composer2.startReplaceGroup(-156940524);
                                                                ComposerKt.sourceInformation(composer2, "177@7507L341");
                                                                if (function213 == null) {
                                                                }
                                                                textStyle4 = textStyle3;
                                                                state3 = state2;
                                                                state4 = createTransitionAnimation;
                                                                function218 = function213;
                                                                composableLambda2 = composableLambda;
                                                                composableLambda3 = null;
                                                                composer2.endReplaceGroup();
                                                                final long m2816suffixColorXeAY9LY$material3_release222 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                composer2.startReplaceGroup(-156921964);
                                                                ComposerKt.sourceInformation(composer2, "191@8087L341");
                                                                if (function214 == null) {
                                                                }
                                                                composableLambda4 = composableLambda3;
                                                                function219 = function214;
                                                                composableLambda5 = null;
                                                                composer2.endReplaceGroup();
                                                                final long m2813leadingIconColorXeAY9LY$material3_release222 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                composer2.startReplaceGroup(-156902962);
                                                                ComposerKt.sourceInformation(composer2, "*205@8655L61");
                                                                if (function211 == null) {
                                                                }
                                                                composer2.endReplaceGroup();
                                                                final long m2819trailingIconColorXeAY9LY$material3_release222 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                composer2.startReplaceGroup(-156893937);
                                                                ComposerKt.sourceInformation(composer2, "*211@8937L62");
                                                                if (function212 == null) {
                                                                }
                                                                composer2.endReplaceGroup();
                                                                final long m2817supportingTextColorXeAY9LY$material3_release222 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                                composer2.startReplaceGroup(-156884470);
                                                                ComposerKt.sourceInformation(composer2, "*217@9228L217");
                                                                if (function216 == null) {
                                                                }
                                                                composer2.endReplaceGroup();
                                                                i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                                                                if (i31 == i30) {
                                                                }
                                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                z6 = z10;
                                                                function222 = function220;
                                                                z7 = z11;
                                                                function223 = function212;
                                                                function224 = function218;
                                                                z8 = z9;
                                                                function225 = function219;
                                                                function226 = function211;
                                                                function227 = function217;
                                                            }
                                                            f4 = 1.0f;
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            startRestartGroup.endReplaceGroup();
                                                            Float valueOf22 = Float.valueOf(f4);
                                                            InputPhase inputPhase132 = (InputPhase) transition.getTargetState();
                                                            startRestartGroup.startReplaceGroup(1435837472);
                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            i26 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
                                                            if (i26 != 1) {
                                                            }
                                                            f6 = 1.0f;
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            startRestartGroup.endReplaceGroup();
                                                            final State<Float> createTransitionAnimation322 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                            TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$122 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                                            TwoWayConverter<Float, AnimationVector1D> vectorConverter322 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                            InputPhase inputPhase1422 = (InputPhase) transition.getCurrentState();
                                                            startRestartGroup.startReplaceGroup(1128033978);
                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
                                                            if (i27 != 1) {
                                                            }
                                                            f7 = 1.0f;
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            startRestartGroup.endReplaceGroup();
                                                            Float valueOf3222 = Float.valueOf(f7);
                                                            InputPhase inputPhase15222 = (InputPhase) transition.getTargetState();
                                                            startRestartGroup.startReplaceGroup(1128033978);
                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                                                            if (i28 != 1) {
                                                            }
                                                            f9 = 1.0f;
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            startRestartGroup.endReplaceGroup();
                                                            createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$122.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                            Transition transition42222 = transition;
                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                            TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                                            InputPhase inputPhase162222 = (InputPhase) transition42222.getTargetState();
                                                            startRestartGroup.startReplaceGroup(-107432127);
                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
                                                            }
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            startRestartGroup.endReplaceGroup();
                                                            ColorSpace m4619getColorSpaceimpl3222 = Color.m4619getColorSpaceimpl(j2);
                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                                            changed = startRestartGroup.changed(m4619getColorSpaceimpl3222);
                                                            rememberedValue2 = startRestartGroup.rememberedValue();
                                                            if (!changed) {
                                                            }
                                                            rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl3222);
                                                            startRestartGroup.updateRememberedValue(rememberedValue2);
                                                            TwoWayConverter twoWayConverter42222 = (TwoWayConverter) rememberedValue2;
                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                            InputPhase inputPhase172222 = (InputPhase) transition2.getCurrentState();
                                                            startRestartGroup.startReplaceGroup(-107432127);
                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
                                                            }
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            startRestartGroup.endReplaceGroup();
                                                            Color m4605boximpl3222 = Color.m4605boximpl(j3);
                                                            InputPhase inputPhase182222 = (InputPhase) transition2.getTargetState();
                                                            startRestartGroup.startReplaceGroup(-107432127);
                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
                                                            }
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            startRestartGroup.endReplaceGroup();
                                                            State createTransitionAnimation42222 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                            Transition transition52222 = transition2;
                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                            TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$12222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                                            startRestartGroup.startReplaceGroup(1023351670);
                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            startRestartGroup.endReplaceGroup();
                                                            ColorSpace m4619getColorSpaceimpl22222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                                            changed2 = startRestartGroup.changed(m4619getColorSpaceimpl22222);
                                                            rememberedValue3 = startRestartGroup.rememberedValue();
                                                            if (!changed2) {
                                                            }
                                                            rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl22222);
                                                            startRestartGroup.updateRememberedValue(rememberedValue3);
                                                            TwoWayConverter twoWayConverter52222 = (TwoWayConverter) rememberedValue3;
                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                            startRestartGroup.startReplaceGroup(1023351670);
                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            startRestartGroup.endReplaceGroup();
                                                            Color m4605boximpl22222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                                                            startRestartGroup.startReplaceGroup(1023351670);
                                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            startRestartGroup.endReplaceGroup();
                                                            final State<Color> createTransitionAnimation52222 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl22222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$12222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter52222, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                            composer2 = startRestartGroup;
                                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                                            ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                                                            final float floatValue2222 = ((Number) createTransitionAnimation2.getValue()).floatValue();
                                                            composer2.startReplaceGroup(-156998101);
                                                            ComposerKt.sourceInformation(composer2, "*138@5668L506");
                                                            if (function22 == null) {
                                                            }
                                                            composer2.endReplaceGroup();
                                                            final long m2814placeholderColorXeAY9LY$material3_release2222 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                            ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                            rememberedValue4 = composer2.rememberedValue();
                                                            textStyle3 = textStyle;
                                                            if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                            }
                                                            State state62222 = (State) rememberedValue4;
                                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                                            composer2.startReplaceGroup(-156965270);
                                                            ComposerKt.sourceInformation(composer2, "160@6765L362");
                                                            if (function215 == null) {
                                                            }
                                                            function217 = function215;
                                                            composableLambda = null;
                                                            composer2.endReplaceGroup();
                                                            final long m2815prefixColorXeAY9LY$material3_release2222 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                            ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                            rememberedValue5 = composer2.rememberedValue();
                                                            if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                                            }
                                                            state2 = (State) rememberedValue5;
                                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                                            composer2.startReplaceGroup(-156940524);
                                                            ComposerKt.sourceInformation(composer2, "177@7507L341");
                                                            if (function213 == null) {
                                                            }
                                                            textStyle4 = textStyle3;
                                                            state3 = state2;
                                                            state4 = createTransitionAnimation;
                                                            function218 = function213;
                                                            composableLambda2 = composableLambda;
                                                            composableLambda3 = null;
                                                            composer2.endReplaceGroup();
                                                            final long m2816suffixColorXeAY9LY$material3_release2222 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                            composer2.startReplaceGroup(-156921964);
                                                            ComposerKt.sourceInformation(composer2, "191@8087L341");
                                                            if (function214 == null) {
                                                            }
                                                            composableLambda4 = composableLambda3;
                                                            function219 = function214;
                                                            composableLambda5 = null;
                                                            composer2.endReplaceGroup();
                                                            final long m2813leadingIconColorXeAY9LY$material3_release2222 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                            composer2.startReplaceGroup(-156902962);
                                                            ComposerKt.sourceInformation(composer2, "*205@8655L61");
                                                            if (function211 == null) {
                                                            }
                                                            composer2.endReplaceGroup();
                                                            final long m2819trailingIconColorXeAY9LY$material3_release2222 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                            composer2.startReplaceGroup(-156893937);
                                                            ComposerKt.sourceInformation(composer2, "*211@8937L62");
                                                            if (function212 == null) {
                                                            }
                                                            composer2.endReplaceGroup();
                                                            final long m2817supportingTextColorXeAY9LY$material3_release2222 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                            composer2.startReplaceGroup(-156884470);
                                                            ComposerKt.sourceInformation(composer2, "*217@9228L217");
                                                            if (function216 == null) {
                                                            }
                                                            composer2.endReplaceGroup();
                                                            i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                                                            if (i31 == i30) {
                                                            }
                                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                                            if (ComposerKt.isTraceInProgress()) {
                                                            }
                                                            z6 = z10;
                                                            function222 = function220;
                                                            z7 = z11;
                                                            function223 = function212;
                                                            function224 = function218;
                                                            z8 = z9;
                                                            function225 = function219;
                                                            function226 = function211;
                                                            function227 = function217;
                                                        } else if (i24 != 3) {
                                                            throw new NoWhenBranchMatchedException();
                                                        }
                                                    }
                                                    f3 = 1.0f;
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    startRestartGroup.endReplaceGroup();
                                                    State createTransitionAnimation22 = TransitionKt.createTransitionAnimation(updateTransition, f2, Float.valueOf(f3), textFieldImplKt$TextFieldTransitionScope$labelProgress$1.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter, "LabelProgress", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$12 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                                    TwoWayConverter<Float, AnimationVector1D> vectorConverter22 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                    InputPhase inputPhase122 = (InputPhase) updateTransition.getCurrentState();
                                                    startRestartGroup.startReplaceGroup(1435837472);
                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    i25 = WhenMappings.$EnumSwitchMapping$1[inputPhase2.ordinal()];
                                                    if (i25 != 1) {
                                                    }
                                                    f4 = 1.0f;
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    startRestartGroup.endReplaceGroup();
                                                    Float valueOf222 = Float.valueOf(f4);
                                                    InputPhase inputPhase1322 = (InputPhase) transition.getTargetState();
                                                    startRestartGroup.startReplaceGroup(1435837472);
                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    i26 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
                                                    if (i26 != 1) {
                                                    }
                                                    f6 = 1.0f;
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    startRestartGroup.endReplaceGroup();
                                                    final State<Float> createTransitionAnimation3222 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$12.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1222 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                                    TwoWayConverter<Float, AnimationVector1D> vectorConverter3222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                    InputPhase inputPhase14222 = (InputPhase) transition.getCurrentState();
                                                    startRestartGroup.startReplaceGroup(1128033978);
                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
                                                    if (i27 != 1) {
                                                    }
                                                    f7 = 1.0f;
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    startRestartGroup.endReplaceGroup();
                                                    Float valueOf32222 = Float.valueOf(f7);
                                                    InputPhase inputPhase152222 = (InputPhase) transition.getTargetState();
                                                    startRestartGroup.startReplaceGroup(1128033978);
                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                                                    if (i28 != 1) {
                                                    }
                                                    f9 = 1.0f;
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    startRestartGroup.endReplaceGroup();
                                                    createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1222.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                    Transition transition422222 = transition;
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                                    InputPhase inputPhase1622222 = (InputPhase) transition422222.getTargetState();
                                                    startRestartGroup.startReplaceGroup(-107432127);
                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    startRestartGroup.endReplaceGroup();
                                                    ColorSpace m4619getColorSpaceimpl32222 = Color.m4619getColorSpaceimpl(j2);
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                                    changed = startRestartGroup.changed(m4619getColorSpaceimpl32222);
                                                    rememberedValue2 = startRestartGroup.rememberedValue();
                                                    if (!changed) {
                                                    }
                                                    rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl32222);
                                                    startRestartGroup.updateRememberedValue(rememberedValue2);
                                                    TwoWayConverter twoWayConverter422222 = (TwoWayConverter) rememberedValue2;
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                    InputPhase inputPhase1722222 = (InputPhase) transition2.getCurrentState();
                                                    startRestartGroup.startReplaceGroup(-107432127);
                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    startRestartGroup.endReplaceGroup();
                                                    Color m4605boximpl32222 = Color.m4605boximpl(j3);
                                                    InputPhase inputPhase1822222 = (InputPhase) transition2.getTargetState();
                                                    startRestartGroup.startReplaceGroup(-107432127);
                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    startRestartGroup.endReplaceGroup();
                                                    State createTransitionAnimation422222 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                    Transition transition522222 = transition2;
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$122222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                                    startRestartGroup.startReplaceGroup(1023351670);
                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    startRestartGroup.endReplaceGroup();
                                                    ColorSpace m4619getColorSpaceimpl222222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                                    changed2 = startRestartGroup.changed(m4619getColorSpaceimpl222222);
                                                    rememberedValue3 = startRestartGroup.rememberedValue();
                                                    if (!changed2) {
                                                    }
                                                    rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl222222);
                                                    startRestartGroup.updateRememberedValue(rememberedValue3);
                                                    TwoWayConverter twoWayConverter522222 = (TwoWayConverter) rememberedValue3;
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                                    startRestartGroup.startReplaceGroup(1023351670);
                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    startRestartGroup.endReplaceGroup();
                                                    Color m4605boximpl222222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                                                    startRestartGroup.startReplaceGroup(1023351670);
                                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    startRestartGroup.endReplaceGroup();
                                                    final State<Color> createTransitionAnimation522222 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl222222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$122222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter522222, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                                    composer2 = startRestartGroup;
                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                    ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                                                    final float floatValue22222 = ((Number) createTransitionAnimation22.getValue()).floatValue();
                                                    composer2.startReplaceGroup(-156998101);
                                                    ComposerKt.sourceInformation(composer2, "*138@5668L506");
                                                    if (function22 == null) {
                                                    }
                                                    composer2.endReplaceGroup();
                                                    final long m2814placeholderColorXeAY9LY$material3_release22222 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                    ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                    rememberedValue4 = composer2.rememberedValue();
                                                    textStyle3 = textStyle;
                                                    if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                    }
                                                    State state622222 = (State) rememberedValue4;
                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                    composer2.startReplaceGroup(-156965270);
                                                    ComposerKt.sourceInformation(composer2, "160@6765L362");
                                                    if (function215 == null) {
                                                    }
                                                    function217 = function215;
                                                    composableLambda = null;
                                                    composer2.endReplaceGroup();
                                                    final long m2815prefixColorXeAY9LY$material3_release22222 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                    ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                                                    rememberedValue5 = composer2.rememberedValue();
                                                    if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                                    }
                                                    state2 = (State) rememberedValue5;
                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                    composer2.startReplaceGroup(-156940524);
                                                    ComposerKt.sourceInformation(composer2, "177@7507L341");
                                                    if (function213 == null) {
                                                    }
                                                    textStyle4 = textStyle3;
                                                    state3 = state2;
                                                    state4 = createTransitionAnimation;
                                                    function218 = function213;
                                                    composableLambda2 = composableLambda;
                                                    composableLambda3 = null;
                                                    composer2.endReplaceGroup();
                                                    final long m2816suffixColorXeAY9LY$material3_release22222 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                    composer2.startReplaceGroup(-156921964);
                                                    ComposerKt.sourceInformation(composer2, "191@8087L341");
                                                    if (function214 == null) {
                                                    }
                                                    composableLambda4 = composableLambda3;
                                                    function219 = function214;
                                                    composableLambda5 = null;
                                                    composer2.endReplaceGroup();
                                                    final long m2813leadingIconColorXeAY9LY$material3_release22222 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                    composer2.startReplaceGroup(-156902962);
                                                    ComposerKt.sourceInformation(composer2, "*205@8655L61");
                                                    if (function211 == null) {
                                                    }
                                                    composer2.endReplaceGroup();
                                                    final long m2819trailingIconColorXeAY9LY$material3_release22222 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                    composer2.startReplaceGroup(-156893937);
                                                    ComposerKt.sourceInformation(composer2, "*211@8937L62");
                                                    if (function212 == null) {
                                                    }
                                                    composer2.endReplaceGroup();
                                                    final long m2817supportingTextColorXeAY9LY$material3_release22222 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                                    composer2.startReplaceGroup(-156884470);
                                                    ComposerKt.sourceInformation(composer2, "*217@9228L217");
                                                    if (function216 == null) {
                                                    }
                                                    composer2.endReplaceGroup();
                                                    i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                                                    if (i31 == i30) {
                                                    }
                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                    }
                                                    z6 = z10;
                                                    function222 = function220;
                                                    z7 = z11;
                                                    function223 = function212;
                                                    function224 = function218;
                                                    z8 = z9;
                                                    function225 = function219;
                                                    function226 = function211;
                                                    function227 = function217;
                                                } else if (i23 != 3) {
                                                    throw new NoWhenBranchMatchedException();
                                                }
                                            }
                                            f = 1.0f;
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            Float valueOf4 = Float.valueOf(f);
                                            InputPhase inputPhase112 = (InputPhase) updateTransition.getTargetState();
                                            startRestartGroup.startReplaceGroup(-2036730335);
                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            i24 = WhenMappings.$EnumSwitchMapping$1[inputPhase.ordinal()];
                                            if (i24 != 1) {
                                            }
                                            f3 = 1.0f;
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            State createTransitionAnimation222 = TransitionKt.createTransitionAnimation(updateTransition, f2, Float.valueOf(f3), textFieldImplKt$TextFieldTransitionScope$labelProgress$1.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter, "LabelProgress", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$122 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                            TwoWayConverter<Float, AnimationVector1D> vectorConverter222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                            InputPhase inputPhase1222 = (InputPhase) updateTransition.getCurrentState();
                                            startRestartGroup.startReplaceGroup(1435837472);
                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            i25 = WhenMappings.$EnumSwitchMapping$1[inputPhase2.ordinal()];
                                            if (i25 != 1) {
                                            }
                                            f4 = 1.0f;
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            Float valueOf2222 = Float.valueOf(f4);
                                            InputPhase inputPhase13222 = (InputPhase) transition.getTargetState();
                                            startRestartGroup.startReplaceGroup(1435837472);
                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            i26 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
                                            if (i26 != 1) {
                                            }
                                            f6 = 1.0f;
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            final State<Float> createTransitionAnimation32222 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$122.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$12222 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                            TwoWayConverter<Float, AnimationVector1D> vectorConverter32222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                            InputPhase inputPhase142222 = (InputPhase) transition.getCurrentState();
                                            startRestartGroup.startReplaceGroup(1128033978);
                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
                                            if (i27 != 1) {
                                            }
                                            f7 = 1.0f;
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            Float valueOf322222 = Float.valueOf(f7);
                                            InputPhase inputPhase1522222 = (InputPhase) transition.getTargetState();
                                            startRestartGroup.startReplaceGroup(1128033978);
                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                                            if (i28 != 1) {
                                            }
                                            f9 = 1.0f;
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$12222.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                            Transition transition4222222 = transition;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1222222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                            InputPhase inputPhase16222222 = (InputPhase) transition4222222.getTargetState();
                                            startRestartGroup.startReplaceGroup(-107432127);
                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            ColorSpace m4619getColorSpaceimpl322222 = Color.m4619getColorSpaceimpl(j2);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                            changed = startRestartGroup.changed(m4619getColorSpaceimpl322222);
                                            rememberedValue2 = startRestartGroup.rememberedValue();
                                            if (!changed) {
                                            }
                                            rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl322222);
                                            startRestartGroup.updateRememberedValue(rememberedValue2);
                                            TwoWayConverter twoWayConverter4222222 = (TwoWayConverter) rememberedValue2;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                            InputPhase inputPhase17222222 = (InputPhase) transition2.getCurrentState();
                                            startRestartGroup.startReplaceGroup(-107432127);
                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            Color m4605boximpl322222 = Color.m4605boximpl(j3);
                                            InputPhase inputPhase18222222 = (InputPhase) transition2.getTargetState();
                                            startRestartGroup.startReplaceGroup(-107432127);
                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            State createTransitionAnimation4222222 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                            Transition transition5222222 = transition2;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$1222222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                            startRestartGroup.startReplaceGroup(1023351670);
                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            ColorSpace m4619getColorSpaceimpl2222222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                            changed2 = startRestartGroup.changed(m4619getColorSpaceimpl2222222);
                                            rememberedValue3 = startRestartGroup.rememberedValue();
                                            if (!changed2) {
                                            }
                                            rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl2222222);
                                            startRestartGroup.updateRememberedValue(rememberedValue3);
                                            TwoWayConverter twoWayConverter5222222 = (TwoWayConverter) rememberedValue3;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                            startRestartGroup.startReplaceGroup(1023351670);
                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            Color m4605boximpl2222222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                                            startRestartGroup.startReplaceGroup(1023351670);
                                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            final State<Color> createTransitionAnimation5222222 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl2222222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$1222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter5222222, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                            composer2 = startRestartGroup;
                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                            ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                                            final float floatValue222222 = ((Number) createTransitionAnimation222.getValue()).floatValue();
                                            composer2.startReplaceGroup(-156998101);
                                            ComposerKt.sourceInformation(composer2, "*138@5668L506");
                                            if (function22 == null) {
                                            }
                                            composer2.endReplaceGroup();
                                            final long m2814placeholderColorXeAY9LY$material3_release222222 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                            ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                                            rememberedValue4 = composer2.rememberedValue();
                                            textStyle3 = textStyle;
                                            if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                            }
                                            State state6222222 = (State) rememberedValue4;
                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                            composer2.startReplaceGroup(-156965270);
                                            ComposerKt.sourceInformation(composer2, "160@6765L362");
                                            if (function215 == null) {
                                            }
                                            function217 = function215;
                                            composableLambda = null;
                                            composer2.endReplaceGroup();
                                            final long m2815prefixColorXeAY9LY$material3_release222222 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                            ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                                            rememberedValue5 = composer2.rememberedValue();
                                            if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                            }
                                            state2 = (State) rememberedValue5;
                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                            composer2.startReplaceGroup(-156940524);
                                            ComposerKt.sourceInformation(composer2, "177@7507L341");
                                            if (function213 == null) {
                                            }
                                            textStyle4 = textStyle3;
                                            state3 = state2;
                                            state4 = createTransitionAnimation;
                                            function218 = function213;
                                            composableLambda2 = composableLambda;
                                            composableLambda3 = null;
                                            composer2.endReplaceGroup();
                                            final long m2816suffixColorXeAY9LY$material3_release222222 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                            composer2.startReplaceGroup(-156921964);
                                            ComposerKt.sourceInformation(composer2, "191@8087L341");
                                            if (function214 == null) {
                                            }
                                            composableLambda4 = composableLambda3;
                                            function219 = function214;
                                            composableLambda5 = null;
                                            composer2.endReplaceGroup();
                                            final long m2813leadingIconColorXeAY9LY$material3_release222222 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                            composer2.startReplaceGroup(-156902962);
                                            ComposerKt.sourceInformation(composer2, "*205@8655L61");
                                            if (function211 == null) {
                                            }
                                            composer2.endReplaceGroup();
                                            final long m2819trailingIconColorXeAY9LY$material3_release222222 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                            composer2.startReplaceGroup(-156893937);
                                            ComposerKt.sourceInformation(composer2, "*211@8937L62");
                                            if (function212 == null) {
                                            }
                                            composer2.endReplaceGroup();
                                            final long m2817supportingTextColorXeAY9LY$material3_release222222 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue);
                                            composer2.startReplaceGroup(-156884470);
                                            ComposerKt.sourceInformation(composer2, "*217@9228L217");
                                            if (function216 == null) {
                                            }
                                            composer2.endReplaceGroup();
                                            i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                                            if (i31 == i30) {
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            z6 = z10;
                                            function222 = function220;
                                            z7 = z11;
                                            function223 = function212;
                                            function224 = function218;
                                            z8 = z9;
                                            function225 = function219;
                                            function226 = function211;
                                            function227 = function217;
                                        }
                                        endRestartGroup = composer2.endRestartGroup();
                                        if (endRestartGroup != null) {
                                            final Function2<? super Composer, ? super Integer, Unit> function233 = function221;
                                            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$4
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

                                                public final void invoke(Composer composer3, int i39) {
                                                    TextFieldImplKt.CommonDecorationBox(TextFieldType.this, str, function2, visualTransformation, function22, function227, function226, function223, function224, function225, function222, z8, z6, z7, interactionSource, paddingValues, textFieldColors, function233, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                                                }
                                            });
                                            return;
                                        }
                                        return;
                                    }
                                    i21 |= i32;
                                    if ((306783379 & i4) != 306783378) {
                                    }
                                    if (i5 != 0) {
                                    }
                                    if (i6 != 0) {
                                    }
                                    if (i8 != 0) {
                                    }
                                    if (i10 != 0) {
                                    }
                                    if (i12 != 0) {
                                    }
                                    if (i14 != 0) {
                                    }
                                    if (i17 != 0) {
                                    }
                                    if (i18 != 0) {
                                    }
                                    if (i20 != 0) {
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 318599163, "CC(remember):TextFieldImpl.kt#9igjgp");
                                    int i372 = i21;
                                    z4 = ((i4 & 112) == 32) | ((i4 & 7168) == 2048);
                                    rememberedValue = startRestartGroup.rememberedValue();
                                    if (!z4) {
                                    }
                                    rememberedValue = visualTransformation.filter(new AnnotatedString(str, null, null, 6, null));
                                    startRestartGroup.updateRememberedValue(rememberedValue);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    String text2 = ((TransformedText) rememberedValue).getText().getText();
                                    boolean booleanValue2 = FocusInteractionKt.collectIsFocusedAsState(interactionSource, startRestartGroup, (i372 >> 12) & 14).getValue().booleanValue();
                                    InputPhase inputPhase92 = booleanValue2 ? InputPhase.Focused : text2.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
                                    m2812labelColorXeAY9LY$material3_release = textFieldColors.m2812labelColorXeAY9LY$material3_release(z10, z11, booleanValue2);
                                    int i382 = i4;
                                    Typography typography2 = MaterialTheme.INSTANCE.getTypography(startRestartGroup, 6);
                                    final TextStyle bodyLarge2 = typography2.getBodyLarge();
                                    final TextStyle bodySmall2 = typography2.getBodySmall();
                                    if (Color.m4616equalsimpl0(bodyLarge2.m6878getColor0d7_KjU(), Color.INSTANCE.m4651getUnspecified0d7_KjU())) {
                                    }
                                    m6878getColor0d7_KjU = bodySmall2.m6878getColor0d7_KjU();
                                    if (z5) {
                                        m6878getColor0d7_KjU = m2812labelColorXeAY9LY$material3_release;
                                    }
                                    m6878getColor0d7_KjU2 = bodyLarge2.m6878getColor0d7_KjU();
                                    if (z5) {
                                        m6878getColor0d7_KjU2 = m2812labelColorXeAY9LY$material3_release;
                                    }
                                    if (function22 != null) {
                                    }
                                    final boolean z132 = z5;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1087703202, "CC(TextFieldTransitionScope)P(2,1:c#ui.graphics.Color,5:c#ui.graphics.Color,3:c#ui.graphics.Color,4)349@14528L59,352@14632L334,364@15016L1126,394@16193L363,406@16607L318,418@17028L197,424@17231L150:TextFieldImpl.kt#mqatfk");
                                    boolean z142 = z12;
                                    Transition updateTransition2 = TransitionKt.updateTransition(inputPhase92, "TextFieldInputState", startRestartGroup, 48, 0);
                                    TextFieldImplKt$TextFieldTransitionScope$labelProgress$1 textFieldImplKt$TextFieldTransitionScope$labelProgress$12 = TextFieldImplKt$TextFieldTransitionScope$labelProgress$1.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                    TwoWayConverter<Float, AnimationVector1D> vectorConverter4 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                    InputPhase inputPhase102 = (InputPhase) updateTransition2.getCurrentState();
                                    startRestartGroup.startReplaceGroup(-2036730335);
                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    i23 = WhenMappings.$EnumSwitchMapping$1[inputPhase102.ordinal()];
                                    if (i23 != 1) {
                                    }
                                    f = 1.0f;
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    Float valueOf42 = Float.valueOf(f);
                                    InputPhase inputPhase1122 = (InputPhase) updateTransition2.getTargetState();
                                    startRestartGroup.startReplaceGroup(-2036730335);
                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    i24 = WhenMappings.$EnumSwitchMapping$1[inputPhase.ordinal()];
                                    if (i24 != 1) {
                                    }
                                    f3 = 1.0f;
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    State createTransitionAnimation2222 = TransitionKt.createTransitionAnimation(updateTransition2, f2, Float.valueOf(f3), textFieldImplKt$TextFieldTransitionScope$labelProgress$12.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter4, "LabelProgress", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1222 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                    TwoWayConverter<Float, AnimationVector1D> vectorConverter2222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                    InputPhase inputPhase12222 = (InputPhase) updateTransition2.getCurrentState();
                                    startRestartGroup.startReplaceGroup(1435837472);
                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    i25 = WhenMappings.$EnumSwitchMapping$1[inputPhase2.ordinal()];
                                    if (i25 != 1) {
                                    }
                                    f4 = 1.0f;
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    Float valueOf22222 = Float.valueOf(f4);
                                    InputPhase inputPhase132222 = (InputPhase) transition.getTargetState();
                                    startRestartGroup.startReplaceGroup(1435837472);
                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    i26 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
                                    if (i26 != 1) {
                                    }
                                    f6 = 1.0f;
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    final State<Float> createTransitionAnimation322222 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1222.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$122222 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                    TwoWayConverter<Float, AnimationVector1D> vectorConverter322222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                    InputPhase inputPhase1422222 = (InputPhase) transition.getCurrentState();
                                    startRestartGroup.startReplaceGroup(1128033978);
                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
                                    if (i27 != 1) {
                                    }
                                    f7 = 1.0f;
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    Float valueOf3222222 = Float.valueOf(f7);
                                    InputPhase inputPhase15222222 = (InputPhase) transition.getTargetState();
                                    startRestartGroup.startReplaceGroup(1128033978);
                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                                    if (i28 != 1) {
                                    }
                                    f9 = 1.0f;
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$122222.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                    Transition transition42222222 = transition;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12222222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                    InputPhase inputPhase162222222 = (InputPhase) transition42222222.getTargetState();
                                    startRestartGroup.startReplaceGroup(-107432127);
                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    ColorSpace m4619getColorSpaceimpl3222222 = Color.m4619getColorSpaceimpl(j2);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                    changed = startRestartGroup.changed(m4619getColorSpaceimpl3222222);
                                    rememberedValue2 = startRestartGroup.rememberedValue();
                                    if (!changed) {
                                    }
                                    rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl3222222);
                                    startRestartGroup.updateRememberedValue(rememberedValue2);
                                    TwoWayConverter twoWayConverter42222222 = (TwoWayConverter) rememberedValue2;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                    InputPhase inputPhase172222222 = (InputPhase) transition2.getCurrentState();
                                    startRestartGroup.startReplaceGroup(-107432127);
                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    Color m4605boximpl3222222 = Color.m4605boximpl(j3);
                                    InputPhase inputPhase182222222 = (InputPhase) transition2.getTargetState();
                                    startRestartGroup.startReplaceGroup(-107432127);
                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    State createTransitionAnimation42222222 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                    Transition transition52222222 = transition2;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$12222222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                    startRestartGroup.startReplaceGroup(1023351670);
                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    ColorSpace m4619getColorSpaceimpl22222222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                    changed2 = startRestartGroup.changed(m4619getColorSpaceimpl22222222);
                                    rememberedValue3 = startRestartGroup.rememberedValue();
                                    if (!changed2) {
                                    }
                                    rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl22222222);
                                    startRestartGroup.updateRememberedValue(rememberedValue3);
                                    TwoWayConverter twoWayConverter52222222 = (TwoWayConverter) rememberedValue3;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                    startRestartGroup.startReplaceGroup(1023351670);
                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    Color m4605boximpl22222222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                                    startRestartGroup.startReplaceGroup(1023351670);
                                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    final State<Color> createTransitionAnimation52222222 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl22222222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$12222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter52222222, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                    composer2 = startRestartGroup;
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                                    final float floatValue2222222 = ((Number) createTransitionAnimation2222.getValue()).floatValue();
                                    composer2.startReplaceGroup(-156998101);
                                    ComposerKt.sourceInformation(composer2, "*138@5668L506");
                                    if (function22 == null) {
                                    }
                                    composer2.endReplaceGroup();
                                    final long m2814placeholderColorXeAY9LY$material3_release2222222 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue2);
                                    ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                                    rememberedValue4 = composer2.rememberedValue();
                                    textStyle3 = textStyle;
                                    if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    }
                                    State state62222222 = (State) rememberedValue4;
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    composer2.startReplaceGroup(-156965270);
                                    ComposerKt.sourceInformation(composer2, "160@6765L362");
                                    if (function215 == null) {
                                    }
                                    function217 = function215;
                                    composableLambda = null;
                                    composer2.endReplaceGroup();
                                    final long m2815prefixColorXeAY9LY$material3_release2222222 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue2);
                                    ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                                    rememberedValue5 = composer2.rememberedValue();
                                    if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                    }
                                    state2 = (State) rememberedValue5;
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    composer2.startReplaceGroup(-156940524);
                                    ComposerKt.sourceInformation(composer2, "177@7507L341");
                                    if (function213 == null) {
                                    }
                                    textStyle4 = textStyle3;
                                    state3 = state2;
                                    state4 = createTransitionAnimation;
                                    function218 = function213;
                                    composableLambda2 = composableLambda;
                                    composableLambda3 = null;
                                    composer2.endReplaceGroup();
                                    final long m2816suffixColorXeAY9LY$material3_release2222222 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue2);
                                    composer2.startReplaceGroup(-156921964);
                                    ComposerKt.sourceInformation(composer2, "191@8087L341");
                                    if (function214 == null) {
                                    }
                                    composableLambda4 = composableLambda3;
                                    function219 = function214;
                                    composableLambda5 = null;
                                    composer2.endReplaceGroup();
                                    final long m2813leadingIconColorXeAY9LY$material3_release2222222 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue2);
                                    composer2.startReplaceGroup(-156902962);
                                    ComposerKt.sourceInformation(composer2, "*205@8655L61");
                                    if (function211 == null) {
                                    }
                                    composer2.endReplaceGroup();
                                    final long m2819trailingIconColorXeAY9LY$material3_release2222222 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue2);
                                    composer2.startReplaceGroup(-156893937);
                                    ComposerKt.sourceInformation(composer2, "*211@8937L62");
                                    if (function212 == null) {
                                    }
                                    composer2.endReplaceGroup();
                                    final long m2817supportingTextColorXeAY9LY$material3_release2222222 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue2);
                                    composer2.startReplaceGroup(-156884470);
                                    ComposerKt.sourceInformation(composer2, "*217@9228L217");
                                    if (function216 == null) {
                                    }
                                    composer2.endReplaceGroup();
                                    i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                                    if (i31 == i30) {
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    z6 = z10;
                                    function222 = function220;
                                    z7 = z11;
                                    function223 = function212;
                                    function224 = function218;
                                    z8 = z9;
                                    function225 = function219;
                                    function226 = function211;
                                    function227 = function217;
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup != null) {
                                    }
                                }
                                i21 |= i33;
                                if ((131072 & i3) != 0) {
                                }
                                i21 |= i32;
                                if ((306783379 & i4) != 306783378) {
                                }
                                if (i5 != 0) {
                                }
                                if (i6 != 0) {
                                }
                                if (i8 != 0) {
                                }
                                if (i10 != 0) {
                                }
                                if (i12 != 0) {
                                }
                                if (i14 != 0) {
                                }
                                if (i17 != 0) {
                                }
                                if (i18 != 0) {
                                }
                                if (i20 != 0) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 318599163, "CC(remember):TextFieldImpl.kt#9igjgp");
                                int i3722 = i21;
                                z4 = ((i4 & 112) == 32) | ((i4 & 7168) == 2048);
                                rememberedValue = startRestartGroup.rememberedValue();
                                if (!z4) {
                                }
                                rememberedValue = visualTransformation.filter(new AnnotatedString(str, null, null, 6, null));
                                startRestartGroup.updateRememberedValue(rememberedValue);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                String text22 = ((TransformedText) rememberedValue).getText().getText();
                                boolean booleanValue22 = FocusInteractionKt.collectIsFocusedAsState(interactionSource, startRestartGroup, (i3722 >> 12) & 14).getValue().booleanValue();
                                InputPhase inputPhase922 = booleanValue22 ? InputPhase.Focused : text22.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
                                m2812labelColorXeAY9LY$material3_release = textFieldColors.m2812labelColorXeAY9LY$material3_release(z10, z11, booleanValue22);
                                int i3822 = i4;
                                Typography typography22 = MaterialTheme.INSTANCE.getTypography(startRestartGroup, 6);
                                final TextStyle bodyLarge22 = typography22.getBodyLarge();
                                final TextStyle bodySmall22 = typography22.getBodySmall();
                                if (Color.m4616equalsimpl0(bodyLarge22.m6878getColor0d7_KjU(), Color.INSTANCE.m4651getUnspecified0d7_KjU())) {
                                }
                                m6878getColor0d7_KjU = bodySmall22.m6878getColor0d7_KjU();
                                if (z5) {
                                }
                                m6878getColor0d7_KjU2 = bodyLarge22.m6878getColor0d7_KjU();
                                if (z5) {
                                }
                                if (function22 != null) {
                                }
                                final boolean z1322 = z5;
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1087703202, "CC(TextFieldTransitionScope)P(2,1:c#ui.graphics.Color,5:c#ui.graphics.Color,3:c#ui.graphics.Color,4)349@14528L59,352@14632L334,364@15016L1126,394@16193L363,406@16607L318,418@17028L197,424@17231L150:TextFieldImpl.kt#mqatfk");
                                boolean z1422 = z12;
                                Transition updateTransition22 = TransitionKt.updateTransition(inputPhase922, "TextFieldInputState", startRestartGroup, 48, 0);
                                TextFieldImplKt$TextFieldTransitionScope$labelProgress$1 textFieldImplKt$TextFieldTransitionScope$labelProgress$122 = TextFieldImplKt$TextFieldTransitionScope$labelProgress$1.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                TwoWayConverter<Float, AnimationVector1D> vectorConverter42 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                InputPhase inputPhase1022 = (InputPhase) updateTransition22.getCurrentState();
                                startRestartGroup.startReplaceGroup(-2036730335);
                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                i23 = WhenMappings.$EnumSwitchMapping$1[inputPhase1022.ordinal()];
                                if (i23 != 1) {
                                }
                                f = 1.0f;
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                startRestartGroup.endReplaceGroup();
                                Float valueOf422 = Float.valueOf(f);
                                InputPhase inputPhase11222 = (InputPhase) updateTransition22.getTargetState();
                                startRestartGroup.startReplaceGroup(-2036730335);
                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                i24 = WhenMappings.$EnumSwitchMapping$1[inputPhase.ordinal()];
                                if (i24 != 1) {
                                }
                                f3 = 1.0f;
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                startRestartGroup.endReplaceGroup();
                                State createTransitionAnimation22222 = TransitionKt.createTransitionAnimation(updateTransition22, f2, Float.valueOf(f3), textFieldImplKt$TextFieldTransitionScope$labelProgress$122.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition22.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter42, "LabelProgress", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$12222 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                TwoWayConverter<Float, AnimationVector1D> vectorConverter22222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                InputPhase inputPhase122222 = (InputPhase) updateTransition22.getCurrentState();
                                startRestartGroup.startReplaceGroup(1435837472);
                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                i25 = WhenMappings.$EnumSwitchMapping$1[inputPhase2.ordinal()];
                                if (i25 != 1) {
                                }
                                f4 = 1.0f;
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                startRestartGroup.endReplaceGroup();
                                Float valueOf222222 = Float.valueOf(f4);
                                InputPhase inputPhase1322222 = (InputPhase) transition.getTargetState();
                                startRestartGroup.startReplaceGroup(1435837472);
                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                i26 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
                                if (i26 != 1) {
                                }
                                f6 = 1.0f;
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                startRestartGroup.endReplaceGroup();
                                final State<Float> createTransitionAnimation3222222 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$12222.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1222222 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                TwoWayConverter<Float, AnimationVector1D> vectorConverter3222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                InputPhase inputPhase14222222 = (InputPhase) transition.getCurrentState();
                                startRestartGroup.startReplaceGroup(1128033978);
                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
                                if (i27 != 1) {
                                }
                                f7 = 1.0f;
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                startRestartGroup.endReplaceGroup();
                                Float valueOf32222222 = Float.valueOf(f7);
                                InputPhase inputPhase152222222 = (InputPhase) transition.getTargetState();
                                startRestartGroup.startReplaceGroup(1128033978);
                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                                if (i28 != 1) {
                                }
                                f9 = 1.0f;
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                startRestartGroup.endReplaceGroup();
                                createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1222222.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                Transition transition422222222 = transition;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122222222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                InputPhase inputPhase1622222222 = (InputPhase) transition422222222.getTargetState();
                                startRestartGroup.startReplaceGroup(-107432127);
                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                startRestartGroup.endReplaceGroup();
                                ColorSpace m4619getColorSpaceimpl32222222 = Color.m4619getColorSpaceimpl(j2);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                changed = startRestartGroup.changed(m4619getColorSpaceimpl32222222);
                                rememberedValue2 = startRestartGroup.rememberedValue();
                                if (!changed) {
                                }
                                rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl32222222);
                                startRestartGroup.updateRememberedValue(rememberedValue2);
                                TwoWayConverter twoWayConverter422222222 = (TwoWayConverter) rememberedValue2;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                InputPhase inputPhase1722222222 = (InputPhase) transition2.getCurrentState();
                                startRestartGroup.startReplaceGroup(-107432127);
                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                startRestartGroup.endReplaceGroup();
                                Color m4605boximpl32222222 = Color.m4605boximpl(j3);
                                InputPhase inputPhase1822222222 = (InputPhase) transition2.getTargetState();
                                startRestartGroup.startReplaceGroup(-107432127);
                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                startRestartGroup.endReplaceGroup();
                                State createTransitionAnimation422222222 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                Transition transition522222222 = transition2;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$122222222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                startRestartGroup.startReplaceGroup(1023351670);
                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                startRestartGroup.endReplaceGroup();
                                ColorSpace m4619getColorSpaceimpl222222222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                changed2 = startRestartGroup.changed(m4619getColorSpaceimpl222222222);
                                rememberedValue3 = startRestartGroup.rememberedValue();
                                if (!changed2) {
                                }
                                rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl222222222);
                                startRestartGroup.updateRememberedValue(rememberedValue3);
                                TwoWayConverter twoWayConverter522222222 = (TwoWayConverter) rememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                startRestartGroup.startReplaceGroup(1023351670);
                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                startRestartGroup.endReplaceGroup();
                                Color m4605boximpl222222222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                                startRestartGroup.startReplaceGroup(1023351670);
                                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                startRestartGroup.endReplaceGroup();
                                final State<Color> createTransitionAnimation522222222 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl222222222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$122222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter522222222, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                composer2 = startRestartGroup;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                                final float floatValue22222222 = ((Number) createTransitionAnimation22222.getValue()).floatValue();
                                composer2.startReplaceGroup(-156998101);
                                ComposerKt.sourceInformation(composer2, "*138@5668L506");
                                if (function22 == null) {
                                }
                                composer2.endReplaceGroup();
                                final long m2814placeholderColorXeAY9LY$material3_release22222222 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue22);
                                ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                                rememberedValue4 = composer2.rememberedValue();
                                textStyle3 = textStyle;
                                if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                }
                                State state622222222 = (State) rememberedValue4;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.startReplaceGroup(-156965270);
                                ComposerKt.sourceInformation(composer2, "160@6765L362");
                                if (function215 == null) {
                                }
                                function217 = function215;
                                composableLambda = null;
                                composer2.endReplaceGroup();
                                final long m2815prefixColorXeAY9LY$material3_release22222222 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue22);
                                ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                                rememberedValue5 = composer2.rememberedValue();
                                if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                }
                                state2 = (State) rememberedValue5;
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.startReplaceGroup(-156940524);
                                ComposerKt.sourceInformation(composer2, "177@7507L341");
                                if (function213 == null) {
                                }
                                textStyle4 = textStyle3;
                                state3 = state2;
                                state4 = createTransitionAnimation;
                                function218 = function213;
                                composableLambda2 = composableLambda;
                                composableLambda3 = null;
                                composer2.endReplaceGroup();
                                final long m2816suffixColorXeAY9LY$material3_release22222222 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue22);
                                composer2.startReplaceGroup(-156921964);
                                ComposerKt.sourceInformation(composer2, "191@8087L341");
                                if (function214 == null) {
                                }
                                composableLambda4 = composableLambda3;
                                function219 = function214;
                                composableLambda5 = null;
                                composer2.endReplaceGroup();
                                final long m2813leadingIconColorXeAY9LY$material3_release22222222 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue22);
                                composer2.startReplaceGroup(-156902962);
                                ComposerKt.sourceInformation(composer2, "*205@8655L61");
                                if (function211 == null) {
                                }
                                composer2.endReplaceGroup();
                                final long m2819trailingIconColorXeAY9LY$material3_release22222222 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue22);
                                composer2.startReplaceGroup(-156893937);
                                ComposerKt.sourceInformation(composer2, "*211@8937L62");
                                if (function212 == null) {
                                }
                                composer2.endReplaceGroup();
                                final long m2817supportingTextColorXeAY9LY$material3_release22222222 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue22);
                                composer2.startReplaceGroup(-156884470);
                                ComposerKt.sourceInformation(composer2, "*217@9228L217");
                                if (function216 == null) {
                                }
                                composer2.endReplaceGroup();
                                i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                                if (i31 == i30) {
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                z6 = z10;
                                function222 = function220;
                                z7 = z11;
                                function223 = function212;
                                function224 = function218;
                                z8 = z9;
                                function225 = function219;
                                function226 = function211;
                                function227 = function217;
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                }
                            }
                            i21 |= i22;
                            if ((65536 & i3) == 0) {
                            }
                            i21 |= i33;
                            if ((131072 & i3) != 0) {
                            }
                            i21 |= i32;
                            if ((306783379 & i4) != 306783378) {
                            }
                            if (i5 != 0) {
                            }
                            if (i6 != 0) {
                            }
                            if (i8 != 0) {
                            }
                            if (i10 != 0) {
                            }
                            if (i12 != 0) {
                            }
                            if (i14 != 0) {
                            }
                            if (i17 != 0) {
                            }
                            if (i18 != 0) {
                            }
                            if (i20 != 0) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 318599163, "CC(remember):TextFieldImpl.kt#9igjgp");
                            int i37222 = i21;
                            z4 = ((i4 & 112) == 32) | ((i4 & 7168) == 2048);
                            rememberedValue = startRestartGroup.rememberedValue();
                            if (!z4) {
                            }
                            rememberedValue = visualTransformation.filter(new AnnotatedString(str, null, null, 6, null));
                            startRestartGroup.updateRememberedValue(rememberedValue);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            String text222 = ((TransformedText) rememberedValue).getText().getText();
                            boolean booleanValue222 = FocusInteractionKt.collectIsFocusedAsState(interactionSource, startRestartGroup, (i37222 >> 12) & 14).getValue().booleanValue();
                            InputPhase inputPhase9222 = booleanValue222 ? InputPhase.Focused : text222.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
                            m2812labelColorXeAY9LY$material3_release = textFieldColors.m2812labelColorXeAY9LY$material3_release(z10, z11, booleanValue222);
                            int i38222 = i4;
                            Typography typography222 = MaterialTheme.INSTANCE.getTypography(startRestartGroup, 6);
                            final TextStyle bodyLarge222 = typography222.getBodyLarge();
                            final TextStyle bodySmall222 = typography222.getBodySmall();
                            if (Color.m4616equalsimpl0(bodyLarge222.m6878getColor0d7_KjU(), Color.INSTANCE.m4651getUnspecified0d7_KjU())) {
                            }
                            m6878getColor0d7_KjU = bodySmall222.m6878getColor0d7_KjU();
                            if (z5) {
                            }
                            m6878getColor0d7_KjU2 = bodyLarge222.m6878getColor0d7_KjU();
                            if (z5) {
                            }
                            if (function22 != null) {
                            }
                            final boolean z13222 = z5;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1087703202, "CC(TextFieldTransitionScope)P(2,1:c#ui.graphics.Color,5:c#ui.graphics.Color,3:c#ui.graphics.Color,4)349@14528L59,352@14632L334,364@15016L1126,394@16193L363,406@16607L318,418@17028L197,424@17231L150:TextFieldImpl.kt#mqatfk");
                            boolean z14222 = z12;
                            Transition updateTransition222 = TransitionKt.updateTransition(inputPhase9222, "TextFieldInputState", startRestartGroup, 48, 0);
                            TextFieldImplKt$TextFieldTransitionScope$labelProgress$1 textFieldImplKt$TextFieldTransitionScope$labelProgress$1222 = TextFieldImplKt$TextFieldTransitionScope$labelProgress$1.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                            TwoWayConverter<Float, AnimationVector1D> vectorConverter422 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                            InputPhase inputPhase10222 = (InputPhase) updateTransition222.getCurrentState();
                            startRestartGroup.startReplaceGroup(-2036730335);
                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            i23 = WhenMappings.$EnumSwitchMapping$1[inputPhase10222.ordinal()];
                            if (i23 != 1) {
                            }
                            f = 1.0f;
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.endReplaceGroup();
                            Float valueOf4222 = Float.valueOf(f);
                            InputPhase inputPhase112222 = (InputPhase) updateTransition222.getTargetState();
                            startRestartGroup.startReplaceGroup(-2036730335);
                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            i24 = WhenMappings.$EnumSwitchMapping$1[inputPhase.ordinal()];
                            if (i24 != 1) {
                            }
                            f3 = 1.0f;
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.endReplaceGroup();
                            State createTransitionAnimation222222 = TransitionKt.createTransitionAnimation(updateTransition222, f2, Float.valueOf(f3), textFieldImplKt$TextFieldTransitionScope$labelProgress$1222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition222.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter422, "LabelProgress", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$122222 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                            TwoWayConverter<Float, AnimationVector1D> vectorConverter222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                            InputPhase inputPhase1222222 = (InputPhase) updateTransition222.getCurrentState();
                            startRestartGroup.startReplaceGroup(1435837472);
                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            i25 = WhenMappings.$EnumSwitchMapping$1[inputPhase2.ordinal()];
                            if (i25 != 1) {
                            }
                            f4 = 1.0f;
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.endReplaceGroup();
                            Float valueOf2222222 = Float.valueOf(f4);
                            InputPhase inputPhase13222222 = (InputPhase) transition.getTargetState();
                            startRestartGroup.startReplaceGroup(1435837472);
                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            i26 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
                            if (i26 != 1) {
                            }
                            f6 = 1.0f;
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.endReplaceGroup();
                            final State<Float> createTransitionAnimation32222222 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$122222.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$12222222 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                            TwoWayConverter<Float, AnimationVector1D> vectorConverter32222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                            InputPhase inputPhase142222222 = (InputPhase) transition.getCurrentState();
                            startRestartGroup.startReplaceGroup(1128033978);
                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
                            if (i27 != 1) {
                            }
                            f7 = 1.0f;
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.endReplaceGroup();
                            Float valueOf322222222 = Float.valueOf(f7);
                            InputPhase inputPhase1522222222 = (InputPhase) transition.getTargetState();
                            startRestartGroup.startReplaceGroup(1128033978);
                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                            if (i28 != 1) {
                            }
                            f9 = 1.0f;
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.endReplaceGroup();
                            createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$12222222.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                            Transition transition4222222222 = transition;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1222222222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                            InputPhase inputPhase16222222222 = (InputPhase) transition4222222222.getTargetState();
                            startRestartGroup.startReplaceGroup(-107432127);
                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.endReplaceGroup();
                            ColorSpace m4619getColorSpaceimpl322222222 = Color.m4619getColorSpaceimpl(j2);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                            changed = startRestartGroup.changed(m4619getColorSpaceimpl322222222);
                            rememberedValue2 = startRestartGroup.rememberedValue();
                            if (!changed) {
                            }
                            rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl322222222);
                            startRestartGroup.updateRememberedValue(rememberedValue2);
                            TwoWayConverter twoWayConverter4222222222 = (TwoWayConverter) rememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                            InputPhase inputPhase17222222222 = (InputPhase) transition2.getCurrentState();
                            startRestartGroup.startReplaceGroup(-107432127);
                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.endReplaceGroup();
                            Color m4605boximpl322222222 = Color.m4605boximpl(j3);
                            InputPhase inputPhase18222222222 = (InputPhase) transition2.getTargetState();
                            startRestartGroup.startReplaceGroup(-107432127);
                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.endReplaceGroup();
                            State createTransitionAnimation4222222222 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                            Transition transition5222222222 = transition2;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$1222222222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                            startRestartGroup.startReplaceGroup(1023351670);
                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.endReplaceGroup();
                            ColorSpace m4619getColorSpaceimpl2222222222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                            changed2 = startRestartGroup.changed(m4619getColorSpaceimpl2222222222);
                            rememberedValue3 = startRestartGroup.rememberedValue();
                            if (!changed2) {
                            }
                            rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl2222222222);
                            startRestartGroup.updateRememberedValue(rememberedValue3);
                            TwoWayConverter twoWayConverter5222222222 = (TwoWayConverter) rememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                            startRestartGroup.startReplaceGroup(1023351670);
                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.endReplaceGroup();
                            Color m4605boximpl2222222222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                            startRestartGroup.startReplaceGroup(1023351670);
                            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.endReplaceGroup();
                            final State<Color> createTransitionAnimation5222222222 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl2222222222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$1222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter5222222222, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                            composer2 = startRestartGroup;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                            final float floatValue222222222 = ((Number) createTransitionAnimation222222.getValue()).floatValue();
                            composer2.startReplaceGroup(-156998101);
                            ComposerKt.sourceInformation(composer2, "*138@5668L506");
                            if (function22 == null) {
                            }
                            composer2.endReplaceGroup();
                            final long m2814placeholderColorXeAY9LY$material3_release222222222 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue222);
                            ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                            rememberedValue4 = composer2.rememberedValue();
                            textStyle3 = textStyle;
                            if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            }
                            State state6222222222 = (State) rememberedValue4;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.startReplaceGroup(-156965270);
                            ComposerKt.sourceInformation(composer2, "160@6765L362");
                            if (function215 == null) {
                            }
                            function217 = function215;
                            composableLambda = null;
                            composer2.endReplaceGroup();
                            final long m2815prefixColorXeAY9LY$material3_release222222222 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue222);
                            ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                            rememberedValue5 = composer2.rememberedValue();
                            if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            }
                            state2 = (State) rememberedValue5;
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            composer2.startReplaceGroup(-156940524);
                            ComposerKt.sourceInformation(composer2, "177@7507L341");
                            if (function213 == null) {
                            }
                            textStyle4 = textStyle3;
                            state3 = state2;
                            state4 = createTransitionAnimation;
                            function218 = function213;
                            composableLambda2 = composableLambda;
                            composableLambda3 = null;
                            composer2.endReplaceGroup();
                            final long m2816suffixColorXeAY9LY$material3_release222222222 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue222);
                            composer2.startReplaceGroup(-156921964);
                            ComposerKt.sourceInformation(composer2, "191@8087L341");
                            if (function214 == null) {
                            }
                            composableLambda4 = composableLambda3;
                            function219 = function214;
                            composableLambda5 = null;
                            composer2.endReplaceGroup();
                            final long m2813leadingIconColorXeAY9LY$material3_release222222222 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue222);
                            composer2.startReplaceGroup(-156902962);
                            ComposerKt.sourceInformation(composer2, "*205@8655L61");
                            if (function211 == null) {
                            }
                            composer2.endReplaceGroup();
                            final long m2819trailingIconColorXeAY9LY$material3_release222222222 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue222);
                            composer2.startReplaceGroup(-156893937);
                            ComposerKt.sourceInformation(composer2, "*211@8937L62");
                            if (function212 == null) {
                            }
                            composer2.endReplaceGroup();
                            final long m2817supportingTextColorXeAY9LY$material3_release222222222 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue222);
                            composer2.startReplaceGroup(-156884470);
                            ComposerKt.sourceInformation(composer2, "*217@9228L217");
                            if (function216 == null) {
                            }
                            composer2.endReplaceGroup();
                            i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                            if (i31 == i30) {
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            z6 = z10;
                            function222 = function220;
                            z7 = z11;
                            function223 = function212;
                            function224 = function218;
                            z8 = z9;
                            function225 = function219;
                            function226 = function211;
                            function227 = function217;
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        i12 = i11;
                        i13 = i3 & 1024;
                        if (i13 == 0) {
                        }
                        i16 = i3 & 2048;
                        if (i16 == 0) {
                        }
                        int i342 = i15;
                        i18 = i3 & 4096;
                        if (i18 == 0) {
                        }
                        i20 = i3 & 8192;
                        if (i20 == 0) {
                        }
                        if ((i3 & 16384) == 0) {
                        }
                        if ((32768 & i3) == 0) {
                        }
                        i21 |= i22;
                        if ((65536 & i3) == 0) {
                        }
                        i21 |= i33;
                        if ((131072 & i3) != 0) {
                        }
                        i21 |= i32;
                        if ((306783379 & i4) != 306783378) {
                        }
                        if (i5 != 0) {
                        }
                        if (i6 != 0) {
                        }
                        if (i8 != 0) {
                        }
                        if (i10 != 0) {
                        }
                        if (i12 != 0) {
                        }
                        if (i14 != 0) {
                        }
                        if (i17 != 0) {
                        }
                        if (i18 != 0) {
                        }
                        if (i20 != 0) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 318599163, "CC(remember):TextFieldImpl.kt#9igjgp");
                        int i372222 = i21;
                        z4 = ((i4 & 112) == 32) | ((i4 & 7168) == 2048);
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (!z4) {
                        }
                        rememberedValue = visualTransformation.filter(new AnnotatedString(str, null, null, 6, null));
                        startRestartGroup.updateRememberedValue(rememberedValue);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        String text2222 = ((TransformedText) rememberedValue).getText().getText();
                        boolean booleanValue2222 = FocusInteractionKt.collectIsFocusedAsState(interactionSource, startRestartGroup, (i372222 >> 12) & 14).getValue().booleanValue();
                        InputPhase inputPhase92222 = booleanValue2222 ? InputPhase.Focused : text2222.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
                        m2812labelColorXeAY9LY$material3_release = textFieldColors.m2812labelColorXeAY9LY$material3_release(z10, z11, booleanValue2222);
                        int i382222 = i4;
                        Typography typography2222 = MaterialTheme.INSTANCE.getTypography(startRestartGroup, 6);
                        final TextStyle bodyLarge2222 = typography2222.getBodyLarge();
                        final TextStyle bodySmall2222 = typography2222.getBodySmall();
                        if (Color.m4616equalsimpl0(bodyLarge2222.m6878getColor0d7_KjU(), Color.INSTANCE.m4651getUnspecified0d7_KjU())) {
                        }
                        m6878getColor0d7_KjU = bodySmall2222.m6878getColor0d7_KjU();
                        if (z5) {
                        }
                        m6878getColor0d7_KjU2 = bodyLarge2222.m6878getColor0d7_KjU();
                        if (z5) {
                        }
                        if (function22 != null) {
                        }
                        final boolean z132222 = z5;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1087703202, "CC(TextFieldTransitionScope)P(2,1:c#ui.graphics.Color,5:c#ui.graphics.Color,3:c#ui.graphics.Color,4)349@14528L59,352@14632L334,364@15016L1126,394@16193L363,406@16607L318,418@17028L197,424@17231L150:TextFieldImpl.kt#mqatfk");
                        boolean z142222 = z12;
                        Transition updateTransition2222 = TransitionKt.updateTransition(inputPhase92222, "TextFieldInputState", startRestartGroup, 48, 0);
                        TextFieldImplKt$TextFieldTransitionScope$labelProgress$1 textFieldImplKt$TextFieldTransitionScope$labelProgress$12222 = TextFieldImplKt$TextFieldTransitionScope$labelProgress$1.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                        TwoWayConverter<Float, AnimationVector1D> vectorConverter4222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                        InputPhase inputPhase102222 = (InputPhase) updateTransition2222.getCurrentState();
                        startRestartGroup.startReplaceGroup(-2036730335);
                        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        i23 = WhenMappings.$EnumSwitchMapping$1[inputPhase102222.ordinal()];
                        if (i23 != 1) {
                        }
                        f = 1.0f;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.endReplaceGroup();
                        Float valueOf42222 = Float.valueOf(f);
                        InputPhase inputPhase1122222 = (InputPhase) updateTransition2222.getTargetState();
                        startRestartGroup.startReplaceGroup(-2036730335);
                        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        i24 = WhenMappings.$EnumSwitchMapping$1[inputPhase.ordinal()];
                        if (i24 != 1) {
                        }
                        f3 = 1.0f;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.endReplaceGroup();
                        State createTransitionAnimation2222222 = TransitionKt.createTransitionAnimation(updateTransition2222, f2, Float.valueOf(f3), textFieldImplKt$TextFieldTransitionScope$labelProgress$12222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition2222.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter4222, "LabelProgress", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1222222 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                        TwoWayConverter<Float, AnimationVector1D> vectorConverter2222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                        InputPhase inputPhase12222222 = (InputPhase) updateTransition2222.getCurrentState();
                        startRestartGroup.startReplaceGroup(1435837472);
                        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        i25 = WhenMappings.$EnumSwitchMapping$1[inputPhase2.ordinal()];
                        if (i25 != 1) {
                        }
                        f4 = 1.0f;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.endReplaceGroup();
                        Float valueOf22222222 = Float.valueOf(f4);
                        InputPhase inputPhase132222222 = (InputPhase) transition.getTargetState();
                        startRestartGroup.startReplaceGroup(1435837472);
                        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        i26 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
                        if (i26 != 1) {
                        }
                        f6 = 1.0f;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.endReplaceGroup();
                        final State<Float> createTransitionAnimation322222222 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1222222.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$122222222 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                        TwoWayConverter<Float, AnimationVector1D> vectorConverter322222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                        InputPhase inputPhase1422222222 = (InputPhase) transition.getCurrentState();
                        startRestartGroup.startReplaceGroup(1128033978);
                        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
                        if (i27 != 1) {
                        }
                        f7 = 1.0f;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.endReplaceGroup();
                        Float valueOf3222222222 = Float.valueOf(f7);
                        InputPhase inputPhase15222222222 = (InputPhase) transition.getTargetState();
                        startRestartGroup.startReplaceGroup(1128033978);
                        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                        if (i28 != 1) {
                        }
                        f9 = 1.0f;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.endReplaceGroup();
                        createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$122222222.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                        Transition transition42222222222 = transition;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12222222222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                        InputPhase inputPhase162222222222 = (InputPhase) transition42222222222.getTargetState();
                        startRestartGroup.startReplaceGroup(-107432127);
                        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.endReplaceGroup();
                        ColorSpace m4619getColorSpaceimpl3222222222 = Color.m4619getColorSpaceimpl(j2);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                        changed = startRestartGroup.changed(m4619getColorSpaceimpl3222222222);
                        rememberedValue2 = startRestartGroup.rememberedValue();
                        if (!changed) {
                        }
                        rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl3222222222);
                        startRestartGroup.updateRememberedValue(rememberedValue2);
                        TwoWayConverter twoWayConverter42222222222 = (TwoWayConverter) rememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                        InputPhase inputPhase172222222222 = (InputPhase) transition2.getCurrentState();
                        startRestartGroup.startReplaceGroup(-107432127);
                        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.endReplaceGroup();
                        Color m4605boximpl3222222222 = Color.m4605boximpl(j3);
                        InputPhase inputPhase182222222222 = (InputPhase) transition2.getTargetState();
                        startRestartGroup.startReplaceGroup(-107432127);
                        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.endReplaceGroup();
                        State createTransitionAnimation42222222222 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                        Transition transition52222222222 = transition2;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$12222222222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                        startRestartGroup.startReplaceGroup(1023351670);
                        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.endReplaceGroup();
                        ColorSpace m4619getColorSpaceimpl22222222222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                        changed2 = startRestartGroup.changed(m4619getColorSpaceimpl22222222222);
                        rememberedValue3 = startRestartGroup.rememberedValue();
                        if (!changed2) {
                        }
                        rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl22222222222);
                        startRestartGroup.updateRememberedValue(rememberedValue3);
                        TwoWayConverter twoWayConverter52222222222 = (TwoWayConverter) rememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                        startRestartGroup.startReplaceGroup(1023351670);
                        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.endReplaceGroup();
                        Color m4605boximpl22222222222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                        startRestartGroup.startReplaceGroup(1023351670);
                        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.endReplaceGroup();
                        final State<Color> createTransitionAnimation52222222222 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl22222222222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$12222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter52222222222, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                        composer2 = startRestartGroup;
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                        final float floatValue2222222222 = ((Number) createTransitionAnimation2222222.getValue()).floatValue();
                        composer2.startReplaceGroup(-156998101);
                        ComposerKt.sourceInformation(composer2, "*138@5668L506");
                        if (function22 == null) {
                        }
                        composer2.endReplaceGroup();
                        final long m2814placeholderColorXeAY9LY$material3_release2222222222 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue2222);
                        ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                        rememberedValue4 = composer2.rememberedValue();
                        textStyle3 = textStyle;
                        if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        }
                        State state62222222222 = (State) rememberedValue4;
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        composer2.startReplaceGroup(-156965270);
                        ComposerKt.sourceInformation(composer2, "160@6765L362");
                        if (function215 == null) {
                        }
                        function217 = function215;
                        composableLambda = null;
                        composer2.endReplaceGroup();
                        final long m2815prefixColorXeAY9LY$material3_release2222222222 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue2222);
                        ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                        rememberedValue5 = composer2.rememberedValue();
                        if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        }
                        state2 = (State) rememberedValue5;
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        composer2.startReplaceGroup(-156940524);
                        ComposerKt.sourceInformation(composer2, "177@7507L341");
                        if (function213 == null) {
                        }
                        textStyle4 = textStyle3;
                        state3 = state2;
                        state4 = createTransitionAnimation;
                        function218 = function213;
                        composableLambda2 = composableLambda;
                        composableLambda3 = null;
                        composer2.endReplaceGroup();
                        final long m2816suffixColorXeAY9LY$material3_release2222222222 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue2222);
                        composer2.startReplaceGroup(-156921964);
                        ComposerKt.sourceInformation(composer2, "191@8087L341");
                        if (function214 == null) {
                        }
                        composableLambda4 = composableLambda3;
                        function219 = function214;
                        composableLambda5 = null;
                        composer2.endReplaceGroup();
                        final long m2813leadingIconColorXeAY9LY$material3_release2222222222 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue2222);
                        composer2.startReplaceGroup(-156902962);
                        ComposerKt.sourceInformation(composer2, "*205@8655L61");
                        if (function211 == null) {
                        }
                        composer2.endReplaceGroup();
                        final long m2819trailingIconColorXeAY9LY$material3_release2222222222 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue2222);
                        composer2.startReplaceGroup(-156893937);
                        ComposerKt.sourceInformation(composer2, "*211@8937L62");
                        if (function212 == null) {
                        }
                        composer2.endReplaceGroup();
                        final long m2817supportingTextColorXeAY9LY$material3_release2222222222 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue2222);
                        composer2.startReplaceGroup(-156884470);
                        ComposerKt.sourceInformation(composer2, "*217@9228L217");
                        if (function216 == null) {
                        }
                        composer2.endReplaceGroup();
                        i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                        if (i31 == i30) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        z6 = z10;
                        function222 = function220;
                        z7 = z11;
                        function223 = function212;
                        function224 = function218;
                        z8 = z9;
                        function225 = function219;
                        function226 = function211;
                        function227 = function217;
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    i10 = i9;
                    i11 = i3 & 512;
                    if (i11 != 0) {
                    }
                    i12 = i11;
                    i13 = i3 & 1024;
                    if (i13 == 0) {
                    }
                    i16 = i3 & 2048;
                    if (i16 == 0) {
                    }
                    int i3422 = i15;
                    i18 = i3 & 4096;
                    if (i18 == 0) {
                    }
                    i20 = i3 & 8192;
                    if (i20 == 0) {
                    }
                    if ((i3 & 16384) == 0) {
                    }
                    if ((32768 & i3) == 0) {
                    }
                    i21 |= i22;
                    if ((65536 & i3) == 0) {
                    }
                    i21 |= i33;
                    if ((131072 & i3) != 0) {
                    }
                    i21 |= i32;
                    if ((306783379 & i4) != 306783378) {
                    }
                    if (i5 != 0) {
                    }
                    if (i6 != 0) {
                    }
                    if (i8 != 0) {
                    }
                    if (i10 != 0) {
                    }
                    if (i12 != 0) {
                    }
                    if (i14 != 0) {
                    }
                    if (i17 != 0) {
                    }
                    if (i18 != 0) {
                    }
                    if (i20 != 0) {
                    }
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 318599163, "CC(remember):TextFieldImpl.kt#9igjgp");
                    int i3722222 = i21;
                    z4 = ((i4 & 112) == 32) | ((i4 & 7168) == 2048);
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (!z4) {
                    }
                    rememberedValue = visualTransformation.filter(new AnnotatedString(str, null, null, 6, null));
                    startRestartGroup.updateRememberedValue(rememberedValue);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    String text22222 = ((TransformedText) rememberedValue).getText().getText();
                    boolean booleanValue22222 = FocusInteractionKt.collectIsFocusedAsState(interactionSource, startRestartGroup, (i3722222 >> 12) & 14).getValue().booleanValue();
                    InputPhase inputPhase922222 = booleanValue22222 ? InputPhase.Focused : text22222.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
                    m2812labelColorXeAY9LY$material3_release = textFieldColors.m2812labelColorXeAY9LY$material3_release(z10, z11, booleanValue22222);
                    int i3822222 = i4;
                    Typography typography22222 = MaterialTheme.INSTANCE.getTypography(startRestartGroup, 6);
                    final TextStyle bodyLarge22222 = typography22222.getBodyLarge();
                    final TextStyle bodySmall22222 = typography22222.getBodySmall();
                    if (Color.m4616equalsimpl0(bodyLarge22222.m6878getColor0d7_KjU(), Color.INSTANCE.m4651getUnspecified0d7_KjU())) {
                    }
                    m6878getColor0d7_KjU = bodySmall22222.m6878getColor0d7_KjU();
                    if (z5) {
                    }
                    m6878getColor0d7_KjU2 = bodyLarge22222.m6878getColor0d7_KjU();
                    if (z5) {
                    }
                    if (function22 != null) {
                    }
                    final boolean z1322222 = z5;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1087703202, "CC(TextFieldTransitionScope)P(2,1:c#ui.graphics.Color,5:c#ui.graphics.Color,3:c#ui.graphics.Color,4)349@14528L59,352@14632L334,364@15016L1126,394@16193L363,406@16607L318,418@17028L197,424@17231L150:TextFieldImpl.kt#mqatfk");
                    boolean z1422222 = z12;
                    Transition updateTransition22222 = TransitionKt.updateTransition(inputPhase922222, "TextFieldInputState", startRestartGroup, 48, 0);
                    TextFieldImplKt$TextFieldTransitionScope$labelProgress$1 textFieldImplKt$TextFieldTransitionScope$labelProgress$122222 = TextFieldImplKt$TextFieldTransitionScope$labelProgress$1.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                    TwoWayConverter<Float, AnimationVector1D> vectorConverter42222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                    InputPhase inputPhase1022222 = (InputPhase) updateTransition22222.getCurrentState();
                    startRestartGroup.startReplaceGroup(-2036730335);
                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    i23 = WhenMappings.$EnumSwitchMapping$1[inputPhase1022222.ordinal()];
                    if (i23 != 1) {
                    }
                    f = 1.0f;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    Float valueOf422222 = Float.valueOf(f);
                    InputPhase inputPhase11222222 = (InputPhase) updateTransition22222.getTargetState();
                    startRestartGroup.startReplaceGroup(-2036730335);
                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    i24 = WhenMappings.$EnumSwitchMapping$1[inputPhase.ordinal()];
                    if (i24 != 1) {
                    }
                    f3 = 1.0f;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    State createTransitionAnimation22222222 = TransitionKt.createTransitionAnimation(updateTransition22222, f2, Float.valueOf(f3), textFieldImplKt$TextFieldTransitionScope$labelProgress$122222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition22222.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter42222, "LabelProgress", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$12222222 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                    TwoWayConverter<Float, AnimationVector1D> vectorConverter22222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                    InputPhase inputPhase122222222 = (InputPhase) updateTransition22222.getCurrentState();
                    startRestartGroup.startReplaceGroup(1435837472);
                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    i25 = WhenMappings.$EnumSwitchMapping$1[inputPhase2.ordinal()];
                    if (i25 != 1) {
                    }
                    f4 = 1.0f;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    Float valueOf222222222 = Float.valueOf(f4);
                    InputPhase inputPhase1322222222 = (InputPhase) transition.getTargetState();
                    startRestartGroup.startReplaceGroup(1435837472);
                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    i26 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
                    if (i26 != 1) {
                    }
                    f6 = 1.0f;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    final State<Float> createTransitionAnimation3222222222 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$12222222.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1222222222 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                    TwoWayConverter<Float, AnimationVector1D> vectorConverter3222222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                    InputPhase inputPhase14222222222 = (InputPhase) transition.getCurrentState();
                    startRestartGroup.startReplaceGroup(1128033978);
                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
                    if (i27 != 1) {
                    }
                    f7 = 1.0f;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    Float valueOf32222222222 = Float.valueOf(f7);
                    InputPhase inputPhase152222222222 = (InputPhase) transition.getTargetState();
                    startRestartGroup.startReplaceGroup(1128033978);
                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                    if (i28 != 1) {
                    }
                    f9 = 1.0f;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                    Transition transition422222222222 = transition;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122222222222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                    InputPhase inputPhase1622222222222 = (InputPhase) transition422222222222.getTargetState();
                    startRestartGroup.startReplaceGroup(-107432127);
                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
                    }
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    ColorSpace m4619getColorSpaceimpl32222222222 = Color.m4619getColorSpaceimpl(j2);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                    changed = startRestartGroup.changed(m4619getColorSpaceimpl32222222222);
                    rememberedValue2 = startRestartGroup.rememberedValue();
                    if (!changed) {
                    }
                    rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl32222222222);
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                    TwoWayConverter twoWayConverter422222222222 = (TwoWayConverter) rememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                    InputPhase inputPhase1722222222222 = (InputPhase) transition2.getCurrentState();
                    startRestartGroup.startReplaceGroup(-107432127);
                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
                    }
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    Color m4605boximpl32222222222 = Color.m4605boximpl(j3);
                    InputPhase inputPhase1822222222222 = (InputPhase) transition2.getTargetState();
                    startRestartGroup.startReplaceGroup(-107432127);
                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
                    }
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    State createTransitionAnimation422222222222 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                    Transition transition522222222222 = transition2;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$122222222222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                    startRestartGroup.startReplaceGroup(1023351670);
                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    ColorSpace m4619getColorSpaceimpl222222222222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                    changed2 = startRestartGroup.changed(m4619getColorSpaceimpl222222222222);
                    rememberedValue3 = startRestartGroup.rememberedValue();
                    if (!changed2) {
                    }
                    rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl222222222222);
                    startRestartGroup.updateRememberedValue(rememberedValue3);
                    TwoWayConverter twoWayConverter522222222222 = (TwoWayConverter) rememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                    startRestartGroup.startReplaceGroup(1023351670);
                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    Color m4605boximpl222222222222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                    startRestartGroup.startReplaceGroup(1023351670);
                    ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    final State<Color> createTransitionAnimation522222222222 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl222222222222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$122222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter522222222222, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                    composer2 = startRestartGroup;
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                    final float floatValue22222222222 = ((Number) createTransitionAnimation22222222.getValue()).floatValue();
                    composer2.startReplaceGroup(-156998101);
                    ComposerKt.sourceInformation(composer2, "*138@5668L506");
                    if (function22 == null) {
                    }
                    composer2.endReplaceGroup();
                    final long m2814placeholderColorXeAY9LY$material3_release22222222222 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue22222);
                    ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                    rememberedValue4 = composer2.rememberedValue();
                    textStyle3 = textStyle;
                    if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    }
                    State state622222222222 = (State) rememberedValue4;
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer2.startReplaceGroup(-156965270);
                    ComposerKt.sourceInformation(composer2, "160@6765L362");
                    if (function215 == null) {
                    }
                    function217 = function215;
                    composableLambda = null;
                    composer2.endReplaceGroup();
                    final long m2815prefixColorXeAY9LY$material3_release22222222222 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue22222);
                    ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                    rememberedValue5 = composer2.rememberedValue();
                    if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    }
                    state2 = (State) rememberedValue5;
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer2.startReplaceGroup(-156940524);
                    ComposerKt.sourceInformation(composer2, "177@7507L341");
                    if (function213 == null) {
                    }
                    textStyle4 = textStyle3;
                    state3 = state2;
                    state4 = createTransitionAnimation;
                    function218 = function213;
                    composableLambda2 = composableLambda;
                    composableLambda3 = null;
                    composer2.endReplaceGroup();
                    final long m2816suffixColorXeAY9LY$material3_release22222222222 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue22222);
                    composer2.startReplaceGroup(-156921964);
                    ComposerKt.sourceInformation(composer2, "191@8087L341");
                    if (function214 == null) {
                    }
                    composableLambda4 = composableLambda3;
                    function219 = function214;
                    composableLambda5 = null;
                    composer2.endReplaceGroup();
                    final long m2813leadingIconColorXeAY9LY$material3_release22222222222 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue22222);
                    composer2.startReplaceGroup(-156902962);
                    ComposerKt.sourceInformation(composer2, "*205@8655L61");
                    if (function211 == null) {
                    }
                    composer2.endReplaceGroup();
                    final long m2819trailingIconColorXeAY9LY$material3_release22222222222 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue22222);
                    composer2.startReplaceGroup(-156893937);
                    ComposerKt.sourceInformation(composer2, "*211@8937L62");
                    if (function212 == null) {
                    }
                    composer2.endReplaceGroup();
                    final long m2817supportingTextColorXeAY9LY$material3_release22222222222 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue22222);
                    composer2.startReplaceGroup(-156884470);
                    ComposerKt.sourceInformation(composer2, "*217@9228L217");
                    if (function216 == null) {
                    }
                    composer2.endReplaceGroup();
                    i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                    if (i31 == i30) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    z6 = z10;
                    function222 = function220;
                    z7 = z11;
                    function223 = function212;
                    function224 = function218;
                    z8 = z9;
                    function225 = function219;
                    function226 = function211;
                    function227 = function217;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                i8 = i7;
                i9 = i3 & 256;
                if (i9 == 0) {
                }
                i10 = i9;
                i11 = i3 & 512;
                if (i11 != 0) {
                }
                i12 = i11;
                i13 = i3 & 1024;
                if (i13 == 0) {
                }
                i16 = i3 & 2048;
                if (i16 == 0) {
                }
                int i34222 = i15;
                i18 = i3 & 4096;
                if (i18 == 0) {
                }
                i20 = i3 & 8192;
                if (i20 == 0) {
                }
                if ((i3 & 16384) == 0) {
                }
                if ((32768 & i3) == 0) {
                }
                i21 |= i22;
                if ((65536 & i3) == 0) {
                }
                i21 |= i33;
                if ((131072 & i3) != 0) {
                }
                i21 |= i32;
                if ((306783379 & i4) != 306783378) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                if (i8 != 0) {
                }
                if (i10 != 0) {
                }
                if (i12 != 0) {
                }
                if (i14 != 0) {
                }
                if (i17 != 0) {
                }
                if (i18 != 0) {
                }
                if (i20 != 0) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 318599163, "CC(remember):TextFieldImpl.kt#9igjgp");
                int i37222222 = i21;
                z4 = ((i4 & 112) == 32) | ((i4 & 7168) == 2048);
                rememberedValue = startRestartGroup.rememberedValue();
                if (!z4) {
                }
                rememberedValue = visualTransformation.filter(new AnnotatedString(str, null, null, 6, null));
                startRestartGroup.updateRememberedValue(rememberedValue);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                String text222222 = ((TransformedText) rememberedValue).getText().getText();
                boolean booleanValue222222 = FocusInteractionKt.collectIsFocusedAsState(interactionSource, startRestartGroup, (i37222222 >> 12) & 14).getValue().booleanValue();
                InputPhase inputPhase9222222 = booleanValue222222 ? InputPhase.Focused : text222222.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
                m2812labelColorXeAY9LY$material3_release = textFieldColors.m2812labelColorXeAY9LY$material3_release(z10, z11, booleanValue222222);
                int i38222222 = i4;
                Typography typography222222 = MaterialTheme.INSTANCE.getTypography(startRestartGroup, 6);
                final TextStyle bodyLarge222222 = typography222222.getBodyLarge();
                final TextStyle bodySmall222222 = typography222222.getBodySmall();
                if (Color.m4616equalsimpl0(bodyLarge222222.m6878getColor0d7_KjU(), Color.INSTANCE.m4651getUnspecified0d7_KjU())) {
                }
                m6878getColor0d7_KjU = bodySmall222222.m6878getColor0d7_KjU();
                if (z5) {
                }
                m6878getColor0d7_KjU2 = bodyLarge222222.m6878getColor0d7_KjU();
                if (z5) {
                }
                if (function22 != null) {
                }
                final boolean z13222222 = z5;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1087703202, "CC(TextFieldTransitionScope)P(2,1:c#ui.graphics.Color,5:c#ui.graphics.Color,3:c#ui.graphics.Color,4)349@14528L59,352@14632L334,364@15016L1126,394@16193L363,406@16607L318,418@17028L197,424@17231L150:TextFieldImpl.kt#mqatfk");
                boolean z14222222 = z12;
                Transition updateTransition222222 = TransitionKt.updateTransition(inputPhase9222222, "TextFieldInputState", startRestartGroup, 48, 0);
                TextFieldImplKt$TextFieldTransitionScope$labelProgress$1 textFieldImplKt$TextFieldTransitionScope$labelProgress$1222222 = TextFieldImplKt$TextFieldTransitionScope$labelProgress$1.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                TwoWayConverter<Float, AnimationVector1D> vectorConverter422222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                InputPhase inputPhase10222222 = (InputPhase) updateTransition222222.getCurrentState();
                startRestartGroup.startReplaceGroup(-2036730335);
                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                i23 = WhenMappings.$EnumSwitchMapping$1[inputPhase10222222.ordinal()];
                if (i23 != 1) {
                }
                f = 1.0f;
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.endReplaceGroup();
                Float valueOf4222222 = Float.valueOf(f);
                InputPhase inputPhase112222222 = (InputPhase) updateTransition222222.getTargetState();
                startRestartGroup.startReplaceGroup(-2036730335);
                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                i24 = WhenMappings.$EnumSwitchMapping$1[inputPhase.ordinal()];
                if (i24 != 1) {
                }
                f3 = 1.0f;
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.endReplaceGroup();
                State createTransitionAnimation222222222 = TransitionKt.createTransitionAnimation(updateTransition222222, f2, Float.valueOf(f3), textFieldImplKt$TextFieldTransitionScope$labelProgress$1222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition222222.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter422222, "LabelProgress", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$122222222 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                TwoWayConverter<Float, AnimationVector1D> vectorConverter222222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                InputPhase inputPhase1222222222 = (InputPhase) updateTransition222222.getCurrentState();
                startRestartGroup.startReplaceGroup(1435837472);
                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                i25 = WhenMappings.$EnumSwitchMapping$1[inputPhase2.ordinal()];
                if (i25 != 1) {
                }
                f4 = 1.0f;
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.endReplaceGroup();
                Float valueOf2222222222 = Float.valueOf(f4);
                InputPhase inputPhase13222222222 = (InputPhase) transition.getTargetState();
                startRestartGroup.startReplaceGroup(1435837472);
                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                i26 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
                if (i26 != 1) {
                }
                f6 = 1.0f;
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.endReplaceGroup();
                final State<Float> createTransitionAnimation32222222222 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$122222222.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$12222222222 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                TwoWayConverter<Float, AnimationVector1D> vectorConverter32222222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                InputPhase inputPhase142222222222 = (InputPhase) transition.getCurrentState();
                startRestartGroup.startReplaceGroup(1128033978);
                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
                if (i27 != 1) {
                }
                f7 = 1.0f;
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.endReplaceGroup();
                Float valueOf322222222222 = Float.valueOf(f7);
                InputPhase inputPhase1522222222222 = (InputPhase) transition.getTargetState();
                startRestartGroup.startReplaceGroup(1128033978);
                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                if (i28 != 1) {
                }
                f9 = 1.0f;
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.endReplaceGroup();
                createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$12222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                Transition transition4222222222222 = transition;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1222222222222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                InputPhase inputPhase16222222222222 = (InputPhase) transition4222222222222.getTargetState();
                startRestartGroup.startReplaceGroup(-107432127);
                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.endReplaceGroup();
                ColorSpace m4619getColorSpaceimpl322222222222 = Color.m4619getColorSpaceimpl(j2);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                changed = startRestartGroup.changed(m4619getColorSpaceimpl322222222222);
                rememberedValue2 = startRestartGroup.rememberedValue();
                if (!changed) {
                }
                rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl322222222222);
                startRestartGroup.updateRememberedValue(rememberedValue2);
                TwoWayConverter twoWayConverter4222222222222 = (TwoWayConverter) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                InputPhase inputPhase17222222222222 = (InputPhase) transition2.getCurrentState();
                startRestartGroup.startReplaceGroup(-107432127);
                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.endReplaceGroup();
                Color m4605boximpl322222222222 = Color.m4605boximpl(j3);
                InputPhase inputPhase18222222222222 = (InputPhase) transition2.getTargetState();
                startRestartGroup.startReplaceGroup(-107432127);
                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.endReplaceGroup();
                State createTransitionAnimation4222222222222 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1222222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                Transition transition5222222222222 = transition2;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$1222222222222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                startRestartGroup.startReplaceGroup(1023351670);
                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.endReplaceGroup();
                ColorSpace m4619getColorSpaceimpl2222222222222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
                changed2 = startRestartGroup.changed(m4619getColorSpaceimpl2222222222222);
                rememberedValue3 = startRestartGroup.rememberedValue();
                if (!changed2) {
                }
                rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl2222222222222);
                startRestartGroup.updateRememberedValue(rememberedValue3);
                TwoWayConverter twoWayConverter5222222222222 = (TwoWayConverter) rememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                startRestartGroup.startReplaceGroup(1023351670);
                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.endReplaceGroup();
                Color m4605boximpl2222222222222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
                startRestartGroup.startReplaceGroup(1023351670);
                ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.endReplaceGroup();
                final State<Color> createTransitionAnimation5222222222222 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl2222222222222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$1222222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter5222222222222, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                composer2 = startRestartGroup;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
                final float floatValue222222222222 = ((Number) createTransitionAnimation222222222.getValue()).floatValue();
                composer2.startReplaceGroup(-156998101);
                ComposerKt.sourceInformation(composer2, "*138@5668L506");
                if (function22 == null) {
                }
                composer2.endReplaceGroup();
                final long m2814placeholderColorXeAY9LY$material3_release222222222222 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue222222);
                ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
                rememberedValue4 = composer2.rememberedValue();
                textStyle3 = textStyle;
                if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                }
                State state6222222222222 = (State) rememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.startReplaceGroup(-156965270);
                ComposerKt.sourceInformation(composer2, "160@6765L362");
                if (function215 == null) {
                }
                function217 = function215;
                composableLambda = null;
                composer2.endReplaceGroup();
                final long m2815prefixColorXeAY9LY$material3_release222222222222 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue222222);
                ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
                rememberedValue5 = composer2.rememberedValue();
                if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                }
                state2 = (State) rememberedValue5;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.startReplaceGroup(-156940524);
                ComposerKt.sourceInformation(composer2, "177@7507L341");
                if (function213 == null) {
                }
                textStyle4 = textStyle3;
                state3 = state2;
                state4 = createTransitionAnimation;
                function218 = function213;
                composableLambda2 = composableLambda;
                composableLambda3 = null;
                composer2.endReplaceGroup();
                final long m2816suffixColorXeAY9LY$material3_release222222222222 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue222222);
                composer2.startReplaceGroup(-156921964);
                ComposerKt.sourceInformation(composer2, "191@8087L341");
                if (function214 == null) {
                }
                composableLambda4 = composableLambda3;
                function219 = function214;
                composableLambda5 = null;
                composer2.endReplaceGroup();
                final long m2813leadingIconColorXeAY9LY$material3_release222222222222 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue222222);
                composer2.startReplaceGroup(-156902962);
                ComposerKt.sourceInformation(composer2, "*205@8655L61");
                if (function211 == null) {
                }
                composer2.endReplaceGroup();
                final long m2819trailingIconColorXeAY9LY$material3_release222222222222 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue222222);
                composer2.startReplaceGroup(-156893937);
                ComposerKt.sourceInformation(composer2, "*211@8937L62");
                if (function212 == null) {
                }
                composer2.endReplaceGroup();
                final long m2817supportingTextColorXeAY9LY$material3_release222222222222 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue222222);
                composer2.startReplaceGroup(-156884470);
                ComposerKt.sourceInformation(composer2, "*217@9228L217");
                if (function216 == null) {
                }
                composer2.endReplaceGroup();
                i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
                if (i31 == i30) {
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                if (ComposerKt.isTraceInProgress()) {
                }
                z6 = z10;
                function222 = function220;
                z7 = z11;
                function223 = function212;
                function224 = function218;
                z8 = z9;
                function225 = function219;
                function226 = function211;
                function227 = function217;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            if ((i3 & 8) != 0) {
            }
            if ((i3 & 16) != 0) {
            }
            i5 = i3 & 32;
            if (i5 != 0) {
            }
            i6 = i3 & 64;
            if (i6 != 0) {
            }
            i7 = i3 & 128;
            if (i7 != 0) {
            }
            i8 = i7;
            i9 = i3 & 256;
            if (i9 == 0) {
            }
            i10 = i9;
            i11 = i3 & 512;
            if (i11 != 0) {
            }
            i12 = i11;
            i13 = i3 & 1024;
            if (i13 == 0) {
            }
            i16 = i3 & 2048;
            if (i16 == 0) {
            }
            int i342222 = i15;
            i18 = i3 & 4096;
            if (i18 == 0) {
            }
            i20 = i3 & 8192;
            if (i20 == 0) {
            }
            if ((i3 & 16384) == 0) {
            }
            if ((32768 & i3) == 0) {
            }
            i21 |= i22;
            if ((65536 & i3) == 0) {
            }
            i21 |= i33;
            if ((131072 & i3) != 0) {
            }
            i21 |= i32;
            if ((306783379 & i4) != 306783378) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            if (i8 != 0) {
            }
            if (i10 != 0) {
            }
            if (i12 != 0) {
            }
            if (i14 != 0) {
            }
            if (i17 != 0) {
            }
            if (i18 != 0) {
            }
            if (i20 != 0) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 318599163, "CC(remember):TextFieldImpl.kt#9igjgp");
            int i372222222 = i21;
            z4 = ((i4 & 112) == 32) | ((i4 & 7168) == 2048);
            rememberedValue = startRestartGroup.rememberedValue();
            if (!z4) {
            }
            rememberedValue = visualTransformation.filter(new AnnotatedString(str, null, null, 6, null));
            startRestartGroup.updateRememberedValue(rememberedValue);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            String text2222222 = ((TransformedText) rememberedValue).getText().getText();
            boolean booleanValue2222222 = FocusInteractionKt.collectIsFocusedAsState(interactionSource, startRestartGroup, (i372222222 >> 12) & 14).getValue().booleanValue();
            InputPhase inputPhase92222222 = booleanValue2222222 ? InputPhase.Focused : text2222222.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
            m2812labelColorXeAY9LY$material3_release = textFieldColors.m2812labelColorXeAY9LY$material3_release(z10, z11, booleanValue2222222);
            int i382222222 = i4;
            Typography typography2222222 = MaterialTheme.INSTANCE.getTypography(startRestartGroup, 6);
            final TextStyle bodyLarge2222222 = typography2222222.getBodyLarge();
            final TextStyle bodySmall2222222 = typography2222222.getBodySmall();
            if (Color.m4616equalsimpl0(bodyLarge2222222.m6878getColor0d7_KjU(), Color.INSTANCE.m4651getUnspecified0d7_KjU())) {
            }
            m6878getColor0d7_KjU = bodySmall2222222.m6878getColor0d7_KjU();
            if (z5) {
            }
            m6878getColor0d7_KjU2 = bodyLarge2222222.m6878getColor0d7_KjU();
            if (z5) {
            }
            if (function22 != null) {
            }
            final boolean z132222222 = z5;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1087703202, "CC(TextFieldTransitionScope)P(2,1:c#ui.graphics.Color,5:c#ui.graphics.Color,3:c#ui.graphics.Color,4)349@14528L59,352@14632L334,364@15016L1126,394@16193L363,406@16607L318,418@17028L197,424@17231L150:TextFieldImpl.kt#mqatfk");
            boolean z142222222 = z12;
            Transition updateTransition2222222 = TransitionKt.updateTransition(inputPhase92222222, "TextFieldInputState", startRestartGroup, 48, 0);
            TextFieldImplKt$TextFieldTransitionScope$labelProgress$1 textFieldImplKt$TextFieldTransitionScope$labelProgress$12222222 = TextFieldImplKt$TextFieldTransitionScope$labelProgress$1.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter4222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
            InputPhase inputPhase102222222 = (InputPhase) updateTransition2222222.getCurrentState();
            startRestartGroup.startReplaceGroup(-2036730335);
            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
            }
            i23 = WhenMappings.$EnumSwitchMapping$1[inputPhase102222222.ordinal()];
            if (i23 != 1) {
            }
            f = 1.0f;
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            Float valueOf42222222 = Float.valueOf(f);
            InputPhase inputPhase1122222222 = (InputPhase) updateTransition2222222.getTargetState();
            startRestartGroup.startReplaceGroup(-2036730335);
            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
            }
            i24 = WhenMappings.$EnumSwitchMapping$1[inputPhase.ordinal()];
            if (i24 != 1) {
            }
            f3 = 1.0f;
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            State createTransitionAnimation2222222222 = TransitionKt.createTransitionAnimation(updateTransition2222222, f2, Float.valueOf(f3), textFieldImplKt$TextFieldTransitionScope$labelProgress$12222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition2222222.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter4222222, "LabelProgress", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1222222222 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter2222222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
            InputPhase inputPhase12222222222 = (InputPhase) updateTransition2222222.getCurrentState();
            startRestartGroup.startReplaceGroup(1435837472);
            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
            }
            i25 = WhenMappings.$EnumSwitchMapping$1[inputPhase2.ordinal()];
            if (i25 != 1) {
            }
            f4 = 1.0f;
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            Float valueOf22222222222 = Float.valueOf(f4);
            InputPhase inputPhase132222222222 = (InputPhase) transition.getTargetState();
            startRestartGroup.startReplaceGroup(1435837472);
            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
            }
            i26 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
            if (i26 != 1) {
            }
            f6 = 1.0f;
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            final State<Float> createTransitionAnimation322222222222 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$122222222222 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter322222222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
            InputPhase inputPhase1422222222222 = (InputPhase) transition.getCurrentState();
            startRestartGroup.startReplaceGroup(1128033978);
            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
            }
            i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
            if (i27 != 1) {
            }
            f7 = 1.0f;
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            Float valueOf3222222222222 = Float.valueOf(f7);
            InputPhase inputPhase15222222222222 = (InputPhase) transition.getTargetState();
            startRestartGroup.startReplaceGroup(1128033978);
            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
            }
            i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
            if (i28 != 1) {
            }
            f9 = 1.0f;
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$122222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
            Transition transition42222222222222 = transition;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12222222222222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
            InputPhase inputPhase162222222222222 = (InputPhase) transition42222222222222.getTargetState();
            startRestartGroup.startReplaceGroup(-107432127);
            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
            }
            if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            ColorSpace m4619getColorSpaceimpl3222222222222 = Color.m4619getColorSpaceimpl(j2);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
            changed = startRestartGroup.changed(m4619getColorSpaceimpl3222222222222);
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (!changed) {
            }
            rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl3222222222222);
            startRestartGroup.updateRememberedValue(rememberedValue2);
            TwoWayConverter twoWayConverter42222222222222 = (TwoWayConverter) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
            InputPhase inputPhase172222222222222 = (InputPhase) transition2.getCurrentState();
            startRestartGroup.startReplaceGroup(-107432127);
            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
            }
            if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            Color m4605boximpl3222222222222 = Color.m4605boximpl(j3);
            InputPhase inputPhase182222222222222 = (InputPhase) transition2.getTargetState();
            startRestartGroup.startReplaceGroup(-107432127);
            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
            }
            if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            State createTransitionAnimation42222222222222 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12222222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
            Transition transition52222222222222 = transition2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$12222222222222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
            startRestartGroup.startReplaceGroup(1023351670);
            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            ColorSpace m4619getColorSpaceimpl22222222222222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
            changed2 = startRestartGroup.changed(m4619getColorSpaceimpl22222222222222);
            rememberedValue3 = startRestartGroup.rememberedValue();
            if (!changed2) {
            }
            rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl22222222222222);
            startRestartGroup.updateRememberedValue(rememberedValue3);
            TwoWayConverter twoWayConverter52222222222222 = (TwoWayConverter) rememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
            startRestartGroup.startReplaceGroup(1023351670);
            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            Color m4605boximpl22222222222222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
            startRestartGroup.startReplaceGroup(1023351670);
            ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            final State<Color> createTransitionAnimation52222222222222 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl22222222222222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$12222222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter52222222222222, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
            composer2 = startRestartGroup;
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
            final float floatValue2222222222222 = ((Number) createTransitionAnimation2222222222.getValue()).floatValue();
            composer2.startReplaceGroup(-156998101);
            ComposerKt.sourceInformation(composer2, "*138@5668L506");
            if (function22 == null) {
            }
            composer2.endReplaceGroup();
            final long m2814placeholderColorXeAY9LY$material3_release2222222222222 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue2222222);
            ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
            rememberedValue4 = composer2.rememberedValue();
            textStyle3 = textStyle;
            if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
            }
            State state62222222222222 = (State) rememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.startReplaceGroup(-156965270);
            ComposerKt.sourceInformation(composer2, "160@6765L362");
            if (function215 == null) {
            }
            function217 = function215;
            composableLambda = null;
            composer2.endReplaceGroup();
            final long m2815prefixColorXeAY9LY$material3_release2222222222222 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue2222222);
            ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
            rememberedValue5 = composer2.rememberedValue();
            if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
            }
            state2 = (State) rememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.startReplaceGroup(-156940524);
            ComposerKt.sourceInformation(composer2, "177@7507L341");
            if (function213 == null) {
            }
            textStyle4 = textStyle3;
            state3 = state2;
            state4 = createTransitionAnimation;
            function218 = function213;
            composableLambda2 = composableLambda;
            composableLambda3 = null;
            composer2.endReplaceGroup();
            final long m2816suffixColorXeAY9LY$material3_release2222222222222 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue2222222);
            composer2.startReplaceGroup(-156921964);
            ComposerKt.sourceInformation(composer2, "191@8087L341");
            if (function214 == null) {
            }
            composableLambda4 = composableLambda3;
            function219 = function214;
            composableLambda5 = null;
            composer2.endReplaceGroup();
            final long m2813leadingIconColorXeAY9LY$material3_release2222222222222 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue2222222);
            composer2.startReplaceGroup(-156902962);
            ComposerKt.sourceInformation(composer2, "*205@8655L61");
            if (function211 == null) {
            }
            composer2.endReplaceGroup();
            final long m2819trailingIconColorXeAY9LY$material3_release2222222222222 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue2222222);
            composer2.startReplaceGroup(-156893937);
            ComposerKt.sourceInformation(composer2, "*211@8937L62");
            if (function212 == null) {
            }
            composer2.endReplaceGroup();
            final long m2817supportingTextColorXeAY9LY$material3_release2222222222222 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue2222222);
            composer2.startReplaceGroup(-156884470);
            ComposerKt.sourceInformation(composer2, "*217@9228L217");
            if (function216 == null) {
            }
            composer2.endReplaceGroup();
            i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
            if (i31 == i30) {
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
            }
            z6 = z10;
            function222 = function220;
            z7 = z11;
            function223 = function212;
            function224 = function218;
            z8 = z9;
            function225 = function219;
            function226 = function211;
            function227 = function217;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        if ((i3 & 4) == 0) {
        }
        if ((i3 & 8) != 0) {
        }
        if ((i3 & 16) != 0) {
        }
        i5 = i3 & 32;
        if (i5 != 0) {
        }
        i6 = i3 & 64;
        if (i6 != 0) {
        }
        i7 = i3 & 128;
        if (i7 != 0) {
        }
        i8 = i7;
        i9 = i3 & 256;
        if (i9 == 0) {
        }
        i10 = i9;
        i11 = i3 & 512;
        if (i11 != 0) {
        }
        i12 = i11;
        i13 = i3 & 1024;
        if (i13 == 0) {
        }
        i16 = i3 & 2048;
        if (i16 == 0) {
        }
        int i3422222 = i15;
        i18 = i3 & 4096;
        if (i18 == 0) {
        }
        i20 = i3 & 8192;
        if (i20 == 0) {
        }
        if ((i3 & 16384) == 0) {
        }
        if ((32768 & i3) == 0) {
        }
        i21 |= i22;
        if ((65536 & i3) == 0) {
        }
        i21 |= i33;
        if ((131072 & i3) != 0) {
        }
        i21 |= i32;
        if ((306783379 & i4) != 306783378) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        if (i8 != 0) {
        }
        if (i10 != 0) {
        }
        if (i12 != 0) {
        }
        if (i14 != 0) {
        }
        if (i17 != 0) {
        }
        if (i18 != 0) {
        }
        if (i20 != 0) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 318599163, "CC(remember):TextFieldImpl.kt#9igjgp");
        int i3722222222 = i21;
        z4 = ((i4 & 112) == 32) | ((i4 & 7168) == 2048);
        rememberedValue = startRestartGroup.rememberedValue();
        if (!z4) {
        }
        rememberedValue = visualTransformation.filter(new AnnotatedString(str, null, null, 6, null));
        startRestartGroup.updateRememberedValue(rememberedValue);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        String text22222222 = ((TransformedText) rememberedValue).getText().getText();
        boolean booleanValue22222222 = FocusInteractionKt.collectIsFocusedAsState(interactionSource, startRestartGroup, (i3722222222 >> 12) & 14).getValue().booleanValue();
        InputPhase inputPhase922222222 = booleanValue22222222 ? InputPhase.Focused : text22222222.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
        m2812labelColorXeAY9LY$material3_release = textFieldColors.m2812labelColorXeAY9LY$material3_release(z10, z11, booleanValue22222222);
        int i3822222222 = i4;
        Typography typography22222222 = MaterialTheme.INSTANCE.getTypography(startRestartGroup, 6);
        final TextStyle bodyLarge22222222 = typography22222222.getBodyLarge();
        final TextStyle bodySmall22222222 = typography22222222.getBodySmall();
        if (Color.m4616equalsimpl0(bodyLarge22222222.m6878getColor0d7_KjU(), Color.INSTANCE.m4651getUnspecified0d7_KjU())) {
        }
        m6878getColor0d7_KjU = bodySmall22222222.m6878getColor0d7_KjU();
        if (z5) {
        }
        m6878getColor0d7_KjU2 = bodyLarge22222222.m6878getColor0d7_KjU();
        if (z5) {
        }
        if (function22 != null) {
        }
        final boolean z1322222222 = z5;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1087703202, "CC(TextFieldTransitionScope)P(2,1:c#ui.graphics.Color,5:c#ui.graphics.Color,3:c#ui.graphics.Color,4)349@14528L59,352@14632L334,364@15016L1126,394@16193L363,406@16607L318,418@17028L197,424@17231L150:TextFieldImpl.kt#mqatfk");
        boolean z1422222222 = z12;
        Transition updateTransition22222222 = TransitionKt.updateTransition(inputPhase922222222, "TextFieldInputState", startRestartGroup, 48, 0);
        TextFieldImplKt$TextFieldTransitionScope$labelProgress$1 textFieldImplKt$TextFieldTransitionScope$labelProgress$122222222 = TextFieldImplKt$TextFieldTransitionScope$labelProgress$1.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
        TwoWayConverter<Float, AnimationVector1D> vectorConverter42222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
        InputPhase inputPhase1022222222 = (InputPhase) updateTransition22222222.getCurrentState();
        startRestartGroup.startReplaceGroup(-2036730335);
        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        i23 = WhenMappings.$EnumSwitchMapping$1[inputPhase1022222222.ordinal()];
        if (i23 != 1) {
        }
        f = 1.0f;
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.endReplaceGroup();
        Float valueOf422222222 = Float.valueOf(f);
        InputPhase inputPhase11222222222 = (InputPhase) updateTransition22222222.getTargetState();
        startRestartGroup.startReplaceGroup(-2036730335);
        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        i24 = WhenMappings.$EnumSwitchMapping$1[inputPhase.ordinal()];
        if (i24 != 1) {
        }
        f3 = 1.0f;
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.endReplaceGroup();
        State createTransitionAnimation22222222222 = TransitionKt.createTransitionAnimation(updateTransition22222222, f2, Float.valueOf(f3), textFieldImplKt$TextFieldTransitionScope$labelProgress$122222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition22222222.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter42222222, "LabelProgress", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$12222222222 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
        TwoWayConverter<Float, AnimationVector1D> vectorConverter22222222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
        InputPhase inputPhase122222222222 = (InputPhase) updateTransition22222222.getCurrentState();
        startRestartGroup.startReplaceGroup(1435837472);
        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        i25 = WhenMappings.$EnumSwitchMapping$1[inputPhase2.ordinal()];
        if (i25 != 1) {
        }
        f4 = 1.0f;
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.endReplaceGroup();
        Float valueOf222222222222 = Float.valueOf(f4);
        InputPhase inputPhase1322222222222 = (InputPhase) transition.getTargetState();
        startRestartGroup.startReplaceGroup(1435837472);
        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        i26 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
        if (i26 != 1) {
        }
        f6 = 1.0f;
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.endReplaceGroup();
        final State<Float> createTransitionAnimation3222222222222 = TransitionKt.createTransitionAnimation(transition, f5, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$12222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter, "PlaceholderOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1222222222222 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
        TwoWayConverter<Float, AnimationVector1D> vectorConverter3222222222222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
        InputPhase inputPhase14222222222222 = (InputPhase) transition.getCurrentState();
        startRestartGroup.startReplaceGroup(1128033978);
        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        i27 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
        if (i27 != 1) {
        }
        f7 = 1.0f;
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.endReplaceGroup();
        Float valueOf32222222222222 = Float.valueOf(f7);
        InputPhase inputPhase152222222222222 = (InputPhase) transition.getTargetState();
        startRestartGroup.startReplaceGroup(1128033978);
        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        i28 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
        if (i28 != 1) {
        }
        f9 = 1.0f;
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.endReplaceGroup();
        createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, f8, Float.valueOf(f9), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1222222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter2, "PrefixSuffixOpacity", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        Transition transition422222222222222 = transition;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122222222222222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
        InputPhase inputPhase1622222222222222 = (InputPhase) transition422222222222222.getTargetState();
        startRestartGroup.startReplaceGroup(-107432127);
        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        if (WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()] == 1) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.endReplaceGroup();
        ColorSpace m4619getColorSpaceimpl32222222222222 = Color.m4619getColorSpaceimpl(j2);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
        changed = startRestartGroup.changed(m4619getColorSpaceimpl32222222222222);
        rememberedValue2 = startRestartGroup.rememberedValue();
        if (!changed) {
        }
        rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl32222222222222);
        startRestartGroup.updateRememberedValue(rememberedValue2);
        TwoWayConverter twoWayConverter422222222222222 = (TwoWayConverter) rememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
        InputPhase inputPhase1722222222222222 = (InputPhase) transition2.getCurrentState();
        startRestartGroup.startReplaceGroup(-107432127);
        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        if (WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()] == 1) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.endReplaceGroup();
        Color m4605boximpl32222222222222 = Color.m4605boximpl(j3);
        InputPhase inputPhase1822222222222222 = (InputPhase) transition2.getTargetState();
        startRestartGroup.startReplaceGroup(-107432127);
        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        if (WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] == 1) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.endReplaceGroup();
        State createTransitionAnimation422222222222222 = TransitionKt.createTransitionAnimation(transition2, color, Color.m4605boximpl(j4), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122222222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        Transition transition522222222222222 = transition2;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$122222222222222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
        startRestartGroup.startReplaceGroup(1023351670);
        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.endReplaceGroup();
        ColorSpace m4619getColorSpaceimpl222222222222222 = Color.m4619getColorSpaceimpl(m2812labelColorXeAY9LY$material3_release);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1918408083, "CC(remember):Transition.kt#9igjgp");
        changed2 = startRestartGroup.changed(m4619getColorSpaceimpl222222222222222);
        rememberedValue3 = startRestartGroup.rememberedValue();
        if (!changed2) {
        }
        rememberedValue3 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl222222222222222);
        startRestartGroup.updateRememberedValue(rememberedValue3);
        TwoWayConverter twoWayConverter522222222222222 = (TwoWayConverter) rememberedValue3;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
        startRestartGroup.startReplaceGroup(1023351670);
        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.endReplaceGroup();
        Color m4605boximpl222222222222222 = Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release);
        startRestartGroup.startReplaceGroup(1023351670);
        ComposerKt.sourceInformation(startRestartGroup, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.endReplaceGroup();
        final State<Color> createTransitionAnimation522222222222222 = TransitionKt.createTransitionAnimation(transition3, m4605boximpl222222222222222, Color.m4605boximpl(m2812labelColorXeAY9LY$material3_release), textFieldImplKt$TextFieldTransitionScope$labelContentColor$122222222222222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) transition3.getSegment(), (Transition.Segment) startRestartGroup, (Composer) Integer.valueOf(i29)), twoWayConverter522222222222222, "LabelContentColor", startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        composer2 = startRestartGroup;
        ComposerKt.sourceInformationMarkerEnd(composer2);
        ComposerKt.sourceInformationMarkerEnd(composer2);
        ComposerKt.sourceInformationMarkerStart(composer2, -571896332, "CP(1,2)155@6472L107,172@7259L108:TextFieldImpl.kt#mqatfk");
        final float floatValue22222222222222 = ((Number) createTransitionAnimation22222222222.getValue()).floatValue();
        composer2.startReplaceGroup(-156998101);
        ComposerKt.sourceInformation(composer2, "*138@5668L506");
        if (function22 == null) {
        }
        composer2.endReplaceGroup();
        final long m2814placeholderColorXeAY9LY$material3_release22222222222222 = textFieldColors.m2814placeholderColorXeAY9LY$material3_release(z10, z11, booleanValue22222222);
        ComposerKt.sourceInformationMarkerStart(composer2, -156971732, "CC(remember):TextFieldImpl.kt#9igjgp");
        rememberedValue4 = composer2.rememberedValue();
        textStyle3 = textStyle;
        if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
        }
        State state622222222222222 = (State) rememberedValue4;
        ComposerKt.sourceInformationMarkerEnd(composer2);
        composer2.startReplaceGroup(-156965270);
        ComposerKt.sourceInformation(composer2, "160@6765L362");
        if (function215 == null) {
        }
        function217 = function215;
        composableLambda = null;
        composer2.endReplaceGroup();
        final long m2815prefixColorXeAY9LY$material3_release22222222222222 = textFieldColors.m2815prefixColorXeAY9LY$material3_release(z10, z11, booleanValue22222222);
        ComposerKt.sourceInformationMarkerStart(composer2, -156946547, "CC(remember):TextFieldImpl.kt#9igjgp");
        rememberedValue5 = composer2.rememberedValue();
        if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
        }
        state2 = (State) rememberedValue5;
        ComposerKt.sourceInformationMarkerEnd(composer2);
        composer2.startReplaceGroup(-156940524);
        ComposerKt.sourceInformation(composer2, "177@7507L341");
        if (function213 == null) {
        }
        textStyle4 = textStyle3;
        state3 = state2;
        state4 = createTransitionAnimation;
        function218 = function213;
        composableLambda2 = composableLambda;
        composableLambda3 = null;
        composer2.endReplaceGroup();
        final long m2816suffixColorXeAY9LY$material3_release22222222222222 = textFieldColors.m2816suffixColorXeAY9LY$material3_release(z10, z11, booleanValue22222222);
        composer2.startReplaceGroup(-156921964);
        ComposerKt.sourceInformation(composer2, "191@8087L341");
        if (function214 == null) {
        }
        composableLambda4 = composableLambda3;
        function219 = function214;
        composableLambda5 = null;
        composer2.endReplaceGroup();
        final long m2813leadingIconColorXeAY9LY$material3_release22222222222222 = textFieldColors.m2813leadingIconColorXeAY9LY$material3_release(z10, z11, booleanValue22222222);
        composer2.startReplaceGroup(-156902962);
        ComposerKt.sourceInformation(composer2, "*205@8655L61");
        if (function211 == null) {
        }
        composer2.endReplaceGroup();
        final long m2819trailingIconColorXeAY9LY$material3_release22222222222222 = textFieldColors.m2819trailingIconColorXeAY9LY$material3_release(z10, z11, booleanValue22222222);
        composer2.startReplaceGroup(-156893937);
        ComposerKt.sourceInformation(composer2, "*211@8937L62");
        if (function212 == null) {
        }
        composer2.endReplaceGroup();
        final long m2817supportingTextColorXeAY9LY$material3_release22222222222222 = textFieldColors.m2817supportingTextColorXeAY9LY$material3_release(z10, z11, booleanValue22222222);
        composer2.startReplaceGroup(-156884470);
        ComposerKt.sourceInformation(composer2, "*217@9228L217");
        if (function216 == null) {
        }
        composer2.endReplaceGroup();
        i31 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
        if (i31 == i30) {
        }
        ComposerKt.sourceInformationMarkerEnd(composer2);
        ComposerKt.sourceInformationMarkerEnd(composer2);
        if (ComposerKt.isTraceInProgress()) {
        }
        z6 = z10;
        function222 = function220;
        z7 = z11;
        function223 = function212;
        function224 = function218;
        z8 = z9;
        function225 = function219;
        function226 = function211;
        function227 = function217;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Decoration-3J-VO9M, reason: not valid java name */
    public static final void m3162Decoration3JVO9M(long j, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        final long j2;
        final TextStyle textStyle2;
        final Function2<? super Composer, ? super Integer, Unit> function22;
        Composer startRestartGroup = composer.startRestartGroup(1208685580);
        ComposerKt.sourceInformation(startRestartGroup, "C(Decoration)P(1:c#ui.graphics.Color,2)298@12599L62:TextFieldImpl.kt#mqatfk");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(textStyle) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && startRestartGroup.getSkipping()) {
            startRestartGroup.skipToGroupEnd();
            function22 = function2;
            textStyle2 = textStyle;
            j2 = j;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1208685580, i2, -1, "androidx.compose.material3.internal.Decoration (TextFieldImpl.kt:298)");
            }
            ProvideContentColorTextStyleKt.m3088ProvideContentColorTextStyle3JVO9M(j, textStyle, function2, startRestartGroup, i2 & 1022);
            j2 = j;
            textStyle2 = textStyle;
            function22 = function2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$Decoration$1
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

                public final void invoke(Composer composer2, int i3) {
                    TextFieldImplKt.m3162Decoration3JVO9M(j2, textStyle2, function22, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Decoration-Iv8Zu3U, reason: not valid java name */
    public static final void m3163DecorationIv8Zu3U(final long j, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(660142980);
        ComposerKt.sourceInformation(startRestartGroup, "C(Decoration)P(1:c#ui.graphics.Color)303@12806L84:TextFieldImpl.kt#mqatfk");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && startRestartGroup.getSkipping()) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(660142980, i2, -1, "androidx.compose.material3.internal.Decoration (TextFieldImpl.kt:303)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(j)), function2, startRestartGroup, (i2 & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$Decoration$2
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

                public final void invoke(Composer composer2, int i3) {
                    TextFieldImplKt.m3163DecorationIv8Zu3U(j, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static final Modifier defaultErrorSemantics(Modifier modifier, boolean z, final String str) {
        return z ? SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$defaultErrorSemantics$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                SemanticsPropertiesKt.error(semanticsPropertyReceiver, str);
            }
        }, 1, null) : modifier;
    }

    public static final Modifier textFieldBackground(Modifier modifier, final ColorProducer colorProducer, final Shape shape) {
        return DrawModifierKt.drawWithCache(modifier, new Function1<CacheDrawScope, DrawResult>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$textFieldBackground$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DrawResult invoke(CacheDrawScope cacheDrawScope) {
                final Outline mo361createOutlinePq9zytI = Shape.this.mo361createOutlinePq9zytI(cacheDrawScope.m4127getSizeNHjbRc(), cacheDrawScope.getLayoutDirection(), cacheDrawScope);
                final ColorProducer colorProducer2 = colorProducer;
                return cacheDrawScope.onDrawBehind(new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$textFieldBackground$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope drawScope) {
                        OutlineKt.m4895drawOutlinewDX37Ww$default(drawScope, Outline.this, colorProducer2.mo2216invoke0d7_KjU(), 0.0f, null, null, 0, 60, null);
                    }
                });
            }
        });
    }

    public static final int widthOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getWidth();
        }
        return 0;
    }

    public static final int heightOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getHeight();
        }
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:145:0x016e, code lost:
    
        if (r31 != false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x012c, code lost:
    
        if (r31 != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x022a, code lost:
    
        if (r31 != false) goto L99;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03fc  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0300  */
    /* renamed from: TextFieldTransitionScope-Jy8F4Js, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void m3164TextFieldTransitionScopeJy8F4Js(InputPhase inputPhase, long j, long j2, long j3, boolean z, Function7<? super State<Float>, ? super State<Color>, ? super State<Color>, ? super State<Float>, ? super State<Float>, ? super Composer, ? super Integer, Unit> function7, Composer composer, int i) {
        float f;
        int i2;
        float f2;
        int i3;
        int i4;
        float f3;
        int i5;
        float f4;
        int i6;
        int i7;
        float f5;
        int i8;
        boolean changed;
        Object rememberedValue;
        boolean changed2;
        Object rememberedValue2;
        ComposerKt.sourceInformationMarkerStart(composer, -1087703202, "CC(TextFieldTransitionScope)P(2,1:c#ui.graphics.Color,5:c#ui.graphics.Color,3:c#ui.graphics.Color,4)349@14528L59,352@14632L334,364@15016L1126,394@16193L363,406@16607L318,418@17028L197,424@17231L150:TextFieldImpl.kt#mqatfk");
        Transition updateTransition = TransitionKt.updateTransition(inputPhase, "TextFieldInputState", composer, (i & 14) | 48, 0);
        TextFieldImplKt$TextFieldTransitionScope$labelProgress$1 textFieldImplKt$TextFieldTransitionScope$labelProgress$1 = TextFieldImplKt$TextFieldTransitionScope$labelProgress$1.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(composer, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
        TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
        InputPhase inputPhase2 = (InputPhase) updateTransition.getCurrentState();
        composer.startReplaceGroup(-2036730335);
        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2036730335, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:356)");
        }
        int i9 = WhenMappings.$EnumSwitchMapping$1[inputPhase2.ordinal()];
        float f6 = 0.0f;
        if (i9 != 1) {
            if (i9 == 2) {
                f = 0.0f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceGroup();
                Float valueOf = Float.valueOf(f);
                InputPhase inputPhase3 = (InputPhase) updateTransition.getTargetState();
                composer.startReplaceGroup(-2036730335);
                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2036730335, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:356)");
                }
                i2 = WhenMappings.$EnumSwitchMapping$1[inputPhase3.ordinal()];
                if (i2 != 1) {
                    if (i2 == 2) {
                        f2 = 0.0f;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer.endReplaceGroup();
                        State createTransitionAnimation = TransitionKt.createTransitionAnimation(updateTransition, valueOf, Float.valueOf(f2), textFieldImplKt$TextFieldTransitionScope$labelProgress$1.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter, "LabelProgress", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                        TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                        ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                        InputPhase inputPhase4 = (InputPhase) updateTransition.getCurrentState();
                        composer.startReplaceGroup(1435837472);
                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1435837472, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:386)");
                        }
                        i3 = WhenMappings.$EnumSwitchMapping$1[inputPhase4.ordinal()];
                        if (i3 != 1) {
                            if (i3 != 2) {
                                i4 = 3;
                                if (i3 != 3) {
                                    throw new NoWhenBranchMatchedException();
                                }
                            } else {
                                i4 = 3;
                            }
                            f3 = 0.0f;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer.endReplaceGroup();
                            Float valueOf2 = Float.valueOf(f3);
                            InputPhase inputPhase5 = (InputPhase) updateTransition.getTargetState();
                            composer.startReplaceGroup(1435837472);
                            ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1435837472, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:386)");
                            }
                            i5 = WhenMappings.$EnumSwitchMapping$1[inputPhase5.ordinal()];
                            if (i5 != 1) {
                                if (i5 != 2) {
                                    if (i5 != i4) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                }
                                f4 = 0.0f;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer.endReplaceGroup();
                                State createTransitionAnimation2 = TransitionKt.createTransitionAnimation(updateTransition, valueOf2, Float.valueOf(f4), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter2, "PlaceholderOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                                TwoWayConverter<Float, AnimationVector1D> vectorConverter3 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                                ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                InputPhase inputPhase6 = (InputPhase) updateTransition.getCurrentState();
                                composer.startReplaceGroup(1128033978);
                                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1128033978, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:398)");
                                }
                                i6 = WhenMappings.$EnumSwitchMapping$1[inputPhase6.ordinal()];
                                if (i6 == 1) {
                                    i7 = 3;
                                } else if (i6 != 2) {
                                    i7 = 3;
                                    if (i6 != 3) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                } else {
                                    i7 = 3;
                                    if (z) {
                                        f5 = 0.0f;
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        composer.endReplaceGroup();
                                        Float valueOf3 = Float.valueOf(f5);
                                        InputPhase inputPhase7 = (InputPhase) updateTransition.getTargetState();
                                        composer.startReplaceGroup(1128033978);
                                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1128033978, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:398)");
                                        }
                                        i8 = WhenMappings.$EnumSwitchMapping$1[inputPhase7.ordinal()];
                                        if (i8 != 1) {
                                            if (i8 != 2) {
                                                if (i8 != i7) {
                                                    throw new NoWhenBranchMatchedException();
                                                }
                                            }
                                        }
                                        f6 = 1.0f;
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        composer.endReplaceGroup();
                                        State createTransitionAnimation3 = TransitionKt.createTransitionAnimation(updateTransition, valueOf3, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter3, "PrefixSuffixOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                        InputPhase inputPhase8 = (InputPhase) updateTransition.getTargetState();
                                        composer.startReplaceGroup(-107432127);
                                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-107432127, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:410)");
                                        }
                                        long j4 = WhenMappings.$EnumSwitchMapping$1[inputPhase8.ordinal()] != 1 ? j : j2;
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        composer.endReplaceGroup();
                                        ColorSpace m4619getColorSpaceimpl = Color.m4619getColorSpaceimpl(j4);
                                        ComposerKt.sourceInformationMarkerStart(composer, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                        changed = composer.changed(m4619getColorSpaceimpl);
                                        rememberedValue = composer.rememberedValue();
                                        if (!changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl);
                                            composer.updateRememberedValue(rememberedValue);
                                        }
                                        TwoWayConverter twoWayConverter = (TwoWayConverter) rememberedValue;
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                        InputPhase inputPhase9 = (InputPhase) updateTransition.getCurrentState();
                                        composer.startReplaceGroup(-107432127);
                                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-107432127, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:410)");
                                        }
                                        long j5 = WhenMappings.$EnumSwitchMapping$1[inputPhase9.ordinal()] != 1 ? j : j2;
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        composer.endReplaceGroup();
                                        Color m4605boximpl = Color.m4605boximpl(j5);
                                        InputPhase inputPhase10 = (InputPhase) updateTransition.getTargetState();
                                        composer.startReplaceGroup(-107432127);
                                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-107432127, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:410)");
                                        }
                                        long j6 = WhenMappings.$EnumSwitchMapping$1[inputPhase10.ordinal()] != 1 ? j : j2;
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        composer.endReplaceGroup();
                                        State createTransitionAnimation4 = TransitionKt.createTransitionAnimation(updateTransition, m4605boximpl, Color.m4605boximpl(j6), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), twoWayConverter, "LabelTextStyleColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$1 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                        composer.startReplaceGroup(1023351670);
                                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1023351670, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:421)");
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        composer.endReplaceGroup();
                                        ColorSpace m4619getColorSpaceimpl2 = Color.m4619getColorSpaceimpl(j3);
                                        ComposerKt.sourceInformationMarkerStart(composer, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                        changed2 = composer.changed(m4619getColorSpaceimpl2);
                                        rememberedValue2 = composer.rememberedValue();
                                        if (!changed2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl2);
                                            composer.updateRememberedValue(rememberedValue2);
                                        }
                                        TwoWayConverter twoWayConverter2 = (TwoWayConverter) rememberedValue2;
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                        composer.startReplaceGroup(1023351670);
                                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1023351670, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:421)");
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        composer.endReplaceGroup();
                                        Color m4605boximpl2 = Color.m4605boximpl(j3);
                                        composer.startReplaceGroup(1023351670);
                                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1023351670, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:421)");
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        composer.endReplaceGroup();
                                        State createTransitionAnimation5 = TransitionKt.createTransitionAnimation(updateTransition, m4605boximpl2, Color.m4605boximpl(j3), textFieldImplKt$TextFieldTransitionScope$labelContentColor$1.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), twoWayConverter2, "LabelContentColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        function7.invoke(createTransitionAnimation, createTransitionAnimation4, createTransitionAnimation5, createTransitionAnimation2, createTransitionAnimation3, composer, Integer.valueOf(i & 458752));
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                    }
                                }
                                f5 = 1.0f;
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                composer.endReplaceGroup();
                                Float valueOf32 = Float.valueOf(f5);
                                InputPhase inputPhase72 = (InputPhase) updateTransition.getTargetState();
                                composer.startReplaceGroup(1128033978);
                                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                i8 = WhenMappings.$EnumSwitchMapping$1[inputPhase72.ordinal()];
                                if (i8 != 1) {
                                }
                                f6 = 1.0f;
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                composer.endReplaceGroup();
                                State createTransitionAnimation32 = TransitionKt.createTransitionAnimation(updateTransition, valueOf32, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter3, "PrefixSuffixOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                InputPhase inputPhase82 = (InputPhase) updateTransition.getTargetState();
                                composer.startReplaceGroup(-107432127);
                                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                if (WhenMappings.$EnumSwitchMapping$1[inputPhase82.ordinal()] != 1) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                composer.endReplaceGroup();
                                ColorSpace m4619getColorSpaceimpl3 = Color.m4619getColorSpaceimpl(j4);
                                ComposerKt.sourceInformationMarkerStart(composer, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                changed = composer.changed(m4619getColorSpaceimpl3);
                                rememberedValue = composer.rememberedValue();
                                if (!changed) {
                                }
                                rememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl3);
                                composer.updateRememberedValue(rememberedValue);
                                TwoWayConverter twoWayConverter3 = (TwoWayConverter) rememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                InputPhase inputPhase92 = (InputPhase) updateTransition.getCurrentState();
                                composer.startReplaceGroup(-107432127);
                                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                if (WhenMappings.$EnumSwitchMapping$1[inputPhase92.ordinal()] != 1) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                composer.endReplaceGroup();
                                Color m4605boximpl3 = Color.m4605boximpl(j5);
                                InputPhase inputPhase102 = (InputPhase) updateTransition.getTargetState();
                                composer.startReplaceGroup(-107432127);
                                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                if (WhenMappings.$EnumSwitchMapping$1[inputPhase102.ordinal()] != 1) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                composer.endReplaceGroup();
                                State createTransitionAnimation42 = TransitionKt.createTransitionAnimation(updateTransition, m4605boximpl3, Color.m4605boximpl(j6), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), twoWayConverter3, "LabelTextStyleColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$12 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                                composer.startReplaceGroup(1023351670);
                                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                composer.endReplaceGroup();
                                ColorSpace m4619getColorSpaceimpl22 = Color.m4619getColorSpaceimpl(j3);
                                ComposerKt.sourceInformationMarkerStart(composer, 1918408083, "CC(remember):Transition.kt#9igjgp");
                                changed2 = composer.changed(m4619getColorSpaceimpl22);
                                rememberedValue2 = composer.rememberedValue();
                                if (!changed2) {
                                }
                                rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl22);
                                composer.updateRememberedValue(rememberedValue2);
                                TwoWayConverter twoWayConverter22 = (TwoWayConverter) rememberedValue2;
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                                composer.startReplaceGroup(1023351670);
                                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                composer.endReplaceGroup();
                                Color m4605boximpl22 = Color.m4605boximpl(j3);
                                composer.startReplaceGroup(1023351670);
                                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                composer.endReplaceGroup();
                                State createTransitionAnimation52 = TransitionKt.createTransitionAnimation(updateTransition, m4605boximpl22, Color.m4605boximpl(j3), textFieldImplKt$TextFieldTransitionScope$labelContentColor$12.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), twoWayConverter22, "LabelContentColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                function7.invoke(createTransitionAnimation, createTransitionAnimation42, createTransitionAnimation52, createTransitionAnimation2, createTransitionAnimation32, composer, Integer.valueOf(i & 458752));
                                ComposerKt.sourceInformationMarkerEnd(composer);
                            }
                            f4 = 1.0f;
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer.endReplaceGroup();
                            State createTransitionAnimation22 = TransitionKt.createTransitionAnimation(updateTransition, valueOf2, Float.valueOf(f4), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter2, "PlaceholderOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$12 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                            TwoWayConverter<Float, AnimationVector1D> vectorConverter32 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                            ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                            InputPhase inputPhase62 = (InputPhase) updateTransition.getCurrentState();
                            composer.startReplaceGroup(1128033978);
                            ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            i6 = WhenMappings.$EnumSwitchMapping$1[inputPhase62.ordinal()];
                            if (i6 == 1) {
                            }
                            f5 = 1.0f;
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer.endReplaceGroup();
                            Float valueOf322 = Float.valueOf(f5);
                            InputPhase inputPhase722 = (InputPhase) updateTransition.getTargetState();
                            composer.startReplaceGroup(1128033978);
                            ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            i8 = WhenMappings.$EnumSwitchMapping$1[inputPhase722.ordinal()];
                            if (i8 != 1) {
                            }
                            f6 = 1.0f;
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer.endReplaceGroup();
                            State createTransitionAnimation322 = TransitionKt.createTransitionAnimation(updateTransition, valueOf322, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$12.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter32, "PrefixSuffixOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                            InputPhase inputPhase822 = (InputPhase) updateTransition.getTargetState();
                            composer.startReplaceGroup(-107432127);
                            ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase822.ordinal()] != 1) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer.endReplaceGroup();
                            ColorSpace m4619getColorSpaceimpl32 = Color.m4619getColorSpaceimpl(j4);
                            ComposerKt.sourceInformationMarkerStart(composer, 1918408083, "CC(remember):Transition.kt#9igjgp");
                            changed = composer.changed(m4619getColorSpaceimpl32);
                            rememberedValue = composer.rememberedValue();
                            if (!changed) {
                            }
                            rememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl32);
                            composer.updateRememberedValue(rememberedValue);
                            TwoWayConverter twoWayConverter32 = (TwoWayConverter) rememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                            InputPhase inputPhase922 = (InputPhase) updateTransition.getCurrentState();
                            composer.startReplaceGroup(-107432127);
                            ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase922.ordinal()] != 1) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer.endReplaceGroup();
                            Color m4605boximpl32 = Color.m4605boximpl(j5);
                            InputPhase inputPhase1022 = (InputPhase) updateTransition.getTargetState();
                            composer.startReplaceGroup(-107432127);
                            ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (WhenMappings.$EnumSwitchMapping$1[inputPhase1022.ordinal()] != 1) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer.endReplaceGroup();
                            State createTransitionAnimation422 = TransitionKt.createTransitionAnimation(updateTransition, m4605boximpl32, Color.m4605boximpl(j6), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), twoWayConverter32, "LabelTextStyleColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$122 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                            composer.startReplaceGroup(1023351670);
                            ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer.endReplaceGroup();
                            ColorSpace m4619getColorSpaceimpl222 = Color.m4619getColorSpaceimpl(j3);
                            ComposerKt.sourceInformationMarkerStart(composer, 1918408083, "CC(remember):Transition.kt#9igjgp");
                            changed2 = composer.changed(m4619getColorSpaceimpl222);
                            rememberedValue2 = composer.rememberedValue();
                            if (!changed2) {
                            }
                            rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl222);
                            composer.updateRememberedValue(rememberedValue2);
                            TwoWayConverter twoWayConverter222 = (TwoWayConverter) rememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                            composer.startReplaceGroup(1023351670);
                            ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer.endReplaceGroup();
                            Color m4605boximpl222 = Color.m4605boximpl(j3);
                            composer.startReplaceGroup(1023351670);
                            ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer.endReplaceGroup();
                            State createTransitionAnimation522 = TransitionKt.createTransitionAnimation(updateTransition, m4605boximpl222, Color.m4605boximpl(j3), textFieldImplKt$TextFieldTransitionScope$labelContentColor$122.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), twoWayConverter222, "LabelContentColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            function7.invoke(createTransitionAnimation, createTransitionAnimation422, createTransitionAnimation522, createTransitionAnimation22, createTransitionAnimation322, composer, Integer.valueOf(i & 458752));
                            ComposerKt.sourceInformationMarkerEnd(composer);
                        }
                        i4 = 3;
                        f3 = 1.0f;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        composer.endReplaceGroup();
                        Float valueOf22 = Float.valueOf(f3);
                        InputPhase inputPhase52 = (InputPhase) updateTransition.getTargetState();
                        composer.startReplaceGroup(1435837472);
                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        i5 = WhenMappings.$EnumSwitchMapping$1[inputPhase52.ordinal()];
                        if (i5 != 1) {
                        }
                        f4 = 1.0f;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        composer.endReplaceGroup();
                        State createTransitionAnimation222 = TransitionKt.createTransitionAnimation(updateTransition, valueOf22, Float.valueOf(f4), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter2, "PlaceholderOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$122 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                        TwoWayConverter<Float, AnimationVector1D> vectorConverter322 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                        ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                        InputPhase inputPhase622 = (InputPhase) updateTransition.getCurrentState();
                        composer.startReplaceGroup(1128033978);
                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        i6 = WhenMappings.$EnumSwitchMapping$1[inputPhase622.ordinal()];
                        if (i6 == 1) {
                        }
                        f5 = 1.0f;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        composer.endReplaceGroup();
                        Float valueOf3222 = Float.valueOf(f5);
                        InputPhase inputPhase7222 = (InputPhase) updateTransition.getTargetState();
                        composer.startReplaceGroup(1128033978);
                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        i8 = WhenMappings.$EnumSwitchMapping$1[inputPhase7222.ordinal()];
                        if (i8 != 1) {
                        }
                        f6 = 1.0f;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        composer.endReplaceGroup();
                        State createTransitionAnimation3222 = TransitionKt.createTransitionAnimation(updateTransition, valueOf3222, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$122.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter322, "PrefixSuffixOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                        InputPhase inputPhase8222 = (InputPhase) updateTransition.getTargetState();
                        composer.startReplaceGroup(-107432127);
                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (WhenMappings.$EnumSwitchMapping$1[inputPhase8222.ordinal()] != 1) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        composer.endReplaceGroup();
                        ColorSpace m4619getColorSpaceimpl322 = Color.m4619getColorSpaceimpl(j4);
                        ComposerKt.sourceInformationMarkerStart(composer, 1918408083, "CC(remember):Transition.kt#9igjgp");
                        changed = composer.changed(m4619getColorSpaceimpl322);
                        rememberedValue = composer.rememberedValue();
                        if (!changed) {
                        }
                        rememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl322);
                        composer.updateRememberedValue(rememberedValue);
                        TwoWayConverter twoWayConverter322 = (TwoWayConverter) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                        InputPhase inputPhase9222 = (InputPhase) updateTransition.getCurrentState();
                        composer.startReplaceGroup(-107432127);
                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (WhenMappings.$EnumSwitchMapping$1[inputPhase9222.ordinal()] != 1) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        composer.endReplaceGroup();
                        Color m4605boximpl322 = Color.m4605boximpl(j5);
                        InputPhase inputPhase10222 = (InputPhase) updateTransition.getTargetState();
                        composer.startReplaceGroup(-107432127);
                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (WhenMappings.$EnumSwitchMapping$1[inputPhase10222.ordinal()] != 1) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        composer.endReplaceGroup();
                        State createTransitionAnimation4222 = TransitionKt.createTransitionAnimation(updateTransition, m4605boximpl322, Color.m4605boximpl(j6), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), twoWayConverter322, "LabelTextStyleColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$1222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                        composer.startReplaceGroup(1023351670);
                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        composer.endReplaceGroup();
                        ColorSpace m4619getColorSpaceimpl2222 = Color.m4619getColorSpaceimpl(j3);
                        ComposerKt.sourceInformationMarkerStart(composer, 1918408083, "CC(remember):Transition.kt#9igjgp");
                        changed2 = composer.changed(m4619getColorSpaceimpl2222);
                        rememberedValue2 = composer.rememberedValue();
                        if (!changed2) {
                        }
                        rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl2222);
                        composer.updateRememberedValue(rememberedValue2);
                        TwoWayConverter twoWayConverter2222 = (TwoWayConverter) rememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                        composer.startReplaceGroup(1023351670);
                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        composer.endReplaceGroup();
                        Color m4605boximpl2222 = Color.m4605boximpl(j3);
                        composer.startReplaceGroup(1023351670);
                        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        composer.endReplaceGroup();
                        State createTransitionAnimation5222 = TransitionKt.createTransitionAnimation(updateTransition, m4605boximpl2222, Color.m4605boximpl(j3), textFieldImplKt$TextFieldTransitionScope$labelContentColor$1222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), twoWayConverter2222, "LabelContentColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        function7.invoke(createTransitionAnimation, createTransitionAnimation4222, createTransitionAnimation5222, createTransitionAnimation222, createTransitionAnimation3222, composer, Integer.valueOf(i & 458752));
                        ComposerKt.sourceInformationMarkerEnd(composer);
                    }
                    if (i2 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                }
                f2 = 1.0f;
                if (ComposerKt.isTraceInProgress()) {
                }
                composer.endReplaceGroup();
                State createTransitionAnimation6 = TransitionKt.createTransitionAnimation(updateTransition, valueOf, Float.valueOf(f2), textFieldImplKt$TextFieldTransitionScope$labelProgress$1.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter, "LabelProgress", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$12 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                TwoWayConverter<Float, AnimationVector1D> vectorConverter22 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                InputPhase inputPhase42 = (InputPhase) updateTransition.getCurrentState();
                composer.startReplaceGroup(1435837472);
                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                i3 = WhenMappings.$EnumSwitchMapping$1[inputPhase42.ordinal()];
                if (i3 != 1) {
                }
                f3 = 1.0f;
                if (ComposerKt.isTraceInProgress()) {
                }
                composer.endReplaceGroup();
                Float valueOf222 = Float.valueOf(f3);
                InputPhase inputPhase522 = (InputPhase) updateTransition.getTargetState();
                composer.startReplaceGroup(1435837472);
                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                i5 = WhenMappings.$EnumSwitchMapping$1[inputPhase522.ordinal()];
                if (i5 != 1) {
                }
                f4 = 1.0f;
                if (ComposerKt.isTraceInProgress()) {
                }
                composer.endReplaceGroup();
                State createTransitionAnimation2222 = TransitionKt.createTransitionAnimation(updateTransition, valueOf222, Float.valueOf(f4), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$12.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter22, "PlaceholderOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1222 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                TwoWayConverter<Float, AnimationVector1D> vectorConverter3222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                InputPhase inputPhase6222 = (InputPhase) updateTransition.getCurrentState();
                composer.startReplaceGroup(1128033978);
                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                i6 = WhenMappings.$EnumSwitchMapping$1[inputPhase6222.ordinal()];
                if (i6 == 1) {
                }
                f5 = 1.0f;
                if (ComposerKt.isTraceInProgress()) {
                }
                composer.endReplaceGroup();
                Float valueOf32222 = Float.valueOf(f5);
                InputPhase inputPhase72222 = (InputPhase) updateTransition.getTargetState();
                composer.startReplaceGroup(1128033978);
                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                i8 = WhenMappings.$EnumSwitchMapping$1[inputPhase72222.ordinal()];
                if (i8 != 1) {
                }
                f6 = 1.0f;
                if (ComposerKt.isTraceInProgress()) {
                }
                composer.endReplaceGroup();
                State createTransitionAnimation32222 = TransitionKt.createTransitionAnimation(updateTransition, valueOf32222, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1222.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter3222, "PrefixSuffixOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                InputPhase inputPhase82222 = (InputPhase) updateTransition.getTargetState();
                composer.startReplaceGroup(-107432127);
                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                if (WhenMappings.$EnumSwitchMapping$1[inputPhase82222.ordinal()] != 1) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                composer.endReplaceGroup();
                ColorSpace m4619getColorSpaceimpl3222 = Color.m4619getColorSpaceimpl(j4);
                ComposerKt.sourceInformationMarkerStart(composer, 1918408083, "CC(remember):Transition.kt#9igjgp");
                changed = composer.changed(m4619getColorSpaceimpl3222);
                rememberedValue = composer.rememberedValue();
                if (!changed) {
                }
                rememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl3222);
                composer.updateRememberedValue(rememberedValue);
                TwoWayConverter twoWayConverter3222 = (TwoWayConverter) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                InputPhase inputPhase92222 = (InputPhase) updateTransition.getCurrentState();
                composer.startReplaceGroup(-107432127);
                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                if (WhenMappings.$EnumSwitchMapping$1[inputPhase92222.ordinal()] != 1) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                composer.endReplaceGroup();
                Color m4605boximpl3222 = Color.m4605boximpl(j5);
                InputPhase inputPhase102222 = (InputPhase) updateTransition.getTargetState();
                composer.startReplaceGroup(-107432127);
                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                if (WhenMappings.$EnumSwitchMapping$1[inputPhase102222.ordinal()] != 1) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                composer.endReplaceGroup();
                State createTransitionAnimation42222 = TransitionKt.createTransitionAnimation(updateTransition, m4605boximpl3222, Color.m4605boximpl(j6), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$12222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), twoWayConverter3222, "LabelTextStyleColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$12222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
                composer.startReplaceGroup(1023351670);
                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                composer.endReplaceGroup();
                ColorSpace m4619getColorSpaceimpl22222 = Color.m4619getColorSpaceimpl(j3);
                ComposerKt.sourceInformationMarkerStart(composer, 1918408083, "CC(remember):Transition.kt#9igjgp");
                changed2 = composer.changed(m4619getColorSpaceimpl22222);
                rememberedValue2 = composer.rememberedValue();
                if (!changed2) {
                }
                rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl22222);
                composer.updateRememberedValue(rememberedValue2);
                TwoWayConverter twoWayConverter22222 = (TwoWayConverter) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                composer.startReplaceGroup(1023351670);
                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                composer.endReplaceGroup();
                Color m4605boximpl22222 = Color.m4605boximpl(j3);
                composer.startReplaceGroup(1023351670);
                ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
                if (ComposerKt.isTraceInProgress()) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                composer.endReplaceGroup();
                State createTransitionAnimation52222 = TransitionKt.createTransitionAnimation(updateTransition, m4605boximpl22222, Color.m4605boximpl(j3), textFieldImplKt$TextFieldTransitionScope$labelContentColor$12222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), twoWayConverter22222, "LabelContentColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                function7.invoke(createTransitionAnimation6, createTransitionAnimation42222, createTransitionAnimation52222, createTransitionAnimation2222, createTransitionAnimation32222, composer, Integer.valueOf(i & 458752));
                ComposerKt.sourceInformationMarkerEnd(composer);
            }
            if (i9 != 3) {
                throw new NoWhenBranchMatchedException();
            }
        }
        f = 1.0f;
        if (ComposerKt.isTraceInProgress()) {
        }
        composer.endReplaceGroup();
        Float valueOf4 = Float.valueOf(f);
        InputPhase inputPhase32 = (InputPhase) updateTransition.getTargetState();
        composer.startReplaceGroup(-2036730335);
        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        i2 = WhenMappings.$EnumSwitchMapping$1[inputPhase32.ordinal()];
        if (i2 != 1) {
        }
        f2 = 1.0f;
        if (ComposerKt.isTraceInProgress()) {
        }
        composer.endReplaceGroup();
        State createTransitionAnimation62 = TransitionKt.createTransitionAnimation(updateTransition, valueOf4, Float.valueOf(f2), textFieldImplKt$TextFieldTransitionScope$labelProgress$1.invoke((TextFieldImplKt$TextFieldTransitionScope$labelProgress$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter, "LabelProgress", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$122 = TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(composer, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
        TwoWayConverter<Float, AnimationVector1D> vectorConverter222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
        InputPhase inputPhase422 = (InputPhase) updateTransition.getCurrentState();
        composer.startReplaceGroup(1435837472);
        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        i3 = WhenMappings.$EnumSwitchMapping$1[inputPhase422.ordinal()];
        if (i3 != 1) {
        }
        f3 = 1.0f;
        if (ComposerKt.isTraceInProgress()) {
        }
        composer.endReplaceGroup();
        Float valueOf2222 = Float.valueOf(f3);
        InputPhase inputPhase5222 = (InputPhase) updateTransition.getTargetState();
        composer.startReplaceGroup(1435837472);
        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        i5 = WhenMappings.$EnumSwitchMapping$1[inputPhase5222.ordinal()];
        if (i5 != 1) {
        }
        f4 = 1.0f;
        if (ComposerKt.isTraceInProgress()) {
        }
        composer.endReplaceGroup();
        State createTransitionAnimation22222 = TransitionKt.createTransitionAnimation(updateTransition, valueOf2222, Float.valueOf(f4), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$122.invoke((TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter222, "PlaceholderOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$12222 = TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(composer, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
        TwoWayConverter<Float, AnimationVector1D> vectorConverter32222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
        InputPhase inputPhase62222 = (InputPhase) updateTransition.getCurrentState();
        composer.startReplaceGroup(1128033978);
        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        i6 = WhenMappings.$EnumSwitchMapping$1[inputPhase62222.ordinal()];
        if (i6 == 1) {
        }
        f5 = 1.0f;
        if (ComposerKt.isTraceInProgress()) {
        }
        composer.endReplaceGroup();
        Float valueOf322222 = Float.valueOf(f5);
        InputPhase inputPhase722222 = (InputPhase) updateTransition.getTargetState();
        composer.startReplaceGroup(1128033978);
        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        i8 = WhenMappings.$EnumSwitchMapping$1[inputPhase722222.ordinal()];
        if (i8 != 1) {
        }
        f6 = 1.0f;
        if (ComposerKt.isTraceInProgress()) {
        }
        composer.endReplaceGroup();
        State createTransitionAnimation322222 = TransitionKt.createTransitionAnimation(updateTransition, valueOf322222, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$12222.invoke((TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), vectorConverter32222, "PrefixSuffixOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122222 = TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(composer, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
        InputPhase inputPhase822222 = (InputPhase) updateTransition.getTargetState();
        composer.startReplaceGroup(-107432127);
        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        if (WhenMappings.$EnumSwitchMapping$1[inputPhase822222.ordinal()] != 1) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        composer.endReplaceGroup();
        ColorSpace m4619getColorSpaceimpl32222 = Color.m4619getColorSpaceimpl(j4);
        ComposerKt.sourceInformationMarkerStart(composer, 1918408083, "CC(remember):Transition.kt#9igjgp");
        changed = composer.changed(m4619getColorSpaceimpl32222);
        rememberedValue = composer.rememberedValue();
        if (!changed) {
        }
        rememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl32222);
        composer.updateRememberedValue(rememberedValue);
        TwoWayConverter twoWayConverter32222 = (TwoWayConverter) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
        InputPhase inputPhase922222 = (InputPhase) updateTransition.getCurrentState();
        composer.startReplaceGroup(-107432127);
        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        if (WhenMappings.$EnumSwitchMapping$1[inputPhase922222.ordinal()] != 1) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        composer.endReplaceGroup();
        Color m4605boximpl32222 = Color.m4605boximpl(j5);
        InputPhase inputPhase1022222 = (InputPhase) updateTransition.getTargetState();
        composer.startReplaceGroup(-107432127);
        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        if (WhenMappings.$EnumSwitchMapping$1[inputPhase1022222.ordinal()] != 1) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        composer.endReplaceGroup();
        State createTransitionAnimation422222 = TransitionKt.createTransitionAnimation(updateTransition, m4605boximpl32222, Color.m4605boximpl(j6), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$122222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), twoWayConverter32222, "LabelTextStyleColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$122222 = TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(composer, -1939694975, "CC(animateColor)P(2)68@3220L31,69@3287L70,73@3370L70:Transition.kt#xbi5r1");
        composer.startReplaceGroup(1023351670);
        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        composer.endReplaceGroup();
        ColorSpace m4619getColorSpaceimpl222222 = Color.m4619getColorSpaceimpl(j3);
        ComposerKt.sourceInformationMarkerStart(composer, 1918408083, "CC(remember):Transition.kt#9igjgp");
        changed2 = composer.changed(m4619getColorSpaceimpl222222);
        rememberedValue2 = composer.rememberedValue();
        if (!changed2) {
        }
        rememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m4619getColorSpaceimpl222222);
        composer.updateRememberedValue(rememberedValue2);
        TwoWayConverter twoWayConverter222222 = (TwoWayConverter) rememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
        composer.startReplaceGroup(1023351670);
        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        composer.endReplaceGroup();
        Color m4605boximpl222222 = Color.m4605boximpl(j3);
        composer.startReplaceGroup(1023351670);
        ComposerKt.sourceInformation(composer, "C:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        composer.endReplaceGroup();
        State createTransitionAnimation522222 = TransitionKt.createTransitionAnimation(updateTransition, m4605boximpl222222, Color.m4605boximpl(j3), textFieldImplKt$TextFieldTransitionScope$labelContentColor$122222.invoke((TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1) updateTransition.getSegment(), (Transition.Segment) composer, (Composer) 0), twoWayConverter222222, "LabelContentColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        function7.invoke(createTransitionAnimation62, createTransitionAnimation422222, createTransitionAnimation522222, createTransitionAnimation22222, createTransitionAnimation322222, composer, Integer.valueOf(i & 458752));
        ComposerKt.sourceInformationMarkerEnd(composer);
    }

    /* renamed from: animateBorderStrokeAsState-NuRrP5Q, reason: not valid java name */
    public static final State<BorderStroke> m3167animateBorderStrokeAsStateNuRrP5Q(boolean z, boolean z2, boolean z3, TextFieldColors textFieldColors, float f, float f2, Composer composer, int i) {
        State<Color> rememberUpdatedState;
        State<Dp> rememberUpdatedState2;
        Composer composer2 = composer;
        ComposerKt.sourceInformationMarkerStart(composer2, 2047013045, "C(animateBorderStrokeAsState)P(1,4,2!1,3:c#ui.unit.Dp,5:c#ui.unit.Dp)458@18252L73:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2047013045, i, -1, "androidx.compose.material3.internal.animateBorderStrokeAsState (TextFieldImpl.kt:441)");
        }
        long m2811indicatorColorXeAY9LY$material3_release = textFieldColors.m2811indicatorColorXeAY9LY$material3_release(z, z2, z3);
        if (z) {
            composer2.startReplaceGroup(1023053998);
            ComposerKt.sourceInformation(composer2, "445@17754L84");
            rememberUpdatedState = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(m2811indicatorColorXeAY9LY$material3_release, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer, 48, 12);
            composer2 = composer;
            composer2.endReplaceGroup();
        } else {
            composer2.startReplaceGroup(1023165505);
            ComposerKt.sourceInformation(composer2, "447@17868L33");
            rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m4605boximpl(m2811indicatorColorXeAY9LY$material3_release), composer2, 0);
            composer2.endReplaceGroup();
        }
        State<Color> state = rememberUpdatedState;
        if (z) {
            composer2.startReplaceGroup(1023269417);
            ComposerKt.sourceInformation(composer2, "453@18068L85");
            rememberUpdatedState2 = AnimateAsStateKt.m178animateDpAsStateAjpBEmI(z3 ? f : f2, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer2, 48, 12);
            composer2.endReplaceGroup();
        } else {
            composer2.startReplaceGroup(1023478388);
            ComposerKt.sourceInformation(composer2, "455@18183L46");
            rememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(Dp.m7397boximpl(f2), composer2, (i >> 15) & 14);
            composer2.endReplaceGroup();
        }
        State<BorderStroke> rememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(BorderStrokeKt.m327BorderStrokecXLIe8U(rememberUpdatedState2.getValue().m7413unboximpl(), state.getValue().m4625unboximpl()), composer2, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer2);
        return rememberUpdatedState3;
    }

    public static final Object getLayoutId(IntrinsicMeasurable intrinsicMeasurable) {
        Object parentData = intrinsicMeasurable.getParentData();
        LayoutIdParentData layoutIdParentData = parentData instanceof LayoutIdParentData ? (LayoutIdParentData) parentData : null;
        if (layoutIdParentData != null) {
            return layoutIdParentData.getLayoutId();
        }
        return null;
    }

    static {
        float f = 16;
        TextFieldPadding = Dp.m7399constructorimpl(f);
        MinFocusedLabelLineHeight = Dp.m7399constructorimpl(f);
        MinSupportingTextLineHeight = Dp.m7399constructorimpl(f);
        float f2 = 48;
        IconDefaultSizeModifier = androidx.compose.foundation.layout.SizeKt.m914defaultMinSizeVpY3zN4(Modifier.INSTANCE, Dp.m7399constructorimpl(f2), Dp.m7399constructorimpl(f2));
    }

    public static final long getZeroConstraints() {
        return ZeroConstraints;
    }

    public static final float getTextFieldPadding() {
        return TextFieldPadding;
    }

    public static final float getHorizontalIconPadding() {
        return HorizontalIconPadding;
    }

    public static final float getSupportingTopPadding() {
        return SupportingTopPadding;
    }

    public static final float getPrefixSuffixTextPadding() {
        return PrefixSuffixTextPadding;
    }

    public static final float getMinTextLineHeight() {
        return MinTextLineHeight;
    }

    public static final float getMinFocusedLabelLineHeight() {
        return MinFocusedLabelLineHeight;
    }

    public static final float getMinSupportingTextLineHeight() {
        return MinSupportingTextLineHeight;
    }

    public static final Modifier getIconDefaultSizeModifier() {
        return IconDefaultSizeModifier;
    }

    private static final boolean CommonDecorationBox$lambda$15$lambda$7(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean CommonDecorationBox$lambda$15$lambda$9(State<Boolean> state) {
        return state.getValue().booleanValue();
    }
}
