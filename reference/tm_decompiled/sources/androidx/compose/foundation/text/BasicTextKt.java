package androidx.compose.foundation.text;

import androidx.compose.foundation.text.modifiers.SelectableTextAnnotatedStringElement;
import androidx.compose.foundation.text.modifiers.SelectionController;
import androidx.compose.foundation.text.modifiers.TextAnnotatedStringElement;
import androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode;
import androidx.compose.foundation.text.modifiers.TextAnnotatedStringNodeKt;
import androidx.compose.foundation.text.modifiers.TextStringSimpleElement;
import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.foundation.text.selection.SelectionRegistrarKt;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BasicText.kt */
@Metadata(d1 = {"\u0000ª\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0083\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0099\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00182\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001b0\u001a2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001aw\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u008d\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00182\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001b0\u001a2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007¢\u0006\u0004\b\u0016\u0010 \u001aa\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¢\u0006\u0004\b!\u0010\"\u001aw\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00182\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001b0\u001aH\u0007¢\u0006\u0004\b#\u0010$\u001ak\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0007¢\u0006\u0004\b#\u0010%\u001a\u0081\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00182\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001b0\u001aH\u0007¢\u0006\u0004\b\u001e\u0010&\u001a\u001e\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020)0(2\b\u0010*\u001a\u0004\u0018\u00010+H\u0002\u001a@\u0010,\u001a\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020/\u0012\f\u0012\n\u0012\u0004\u0012\u000201\u0018\u0001000.\u0018\u00010-2\f\u00102\u001a\b\u0012\u0004\u0012\u0002030-2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000e00H\u0002\u001aÉ\u0001\u00105\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u00106\u001a\u0002072\u0014\u00108\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020:09\u0018\u00010-2\u001c\u0010;\u001a\u0018\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010<0-\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\u0010=\u001a\u0004\u0018\u00010>2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0014\u0010?\u001a\u0010\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0004\bA\u0010B\u001a·\u0001\u0010C\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00182\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0006\u0010D\u001a\u00020\u000e2\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u00106\u001a\u0002072\b\u0010=\u001a\u0004\u0018\u00010>2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0014\u0010?\u001a\u0010\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0003¢\u0006\u0004\bE\u0010F¨\u0006G²\u0006\n\u0010H\u001a\u00020\u0018X\u008a\u008e\u0002"}, d2 = {"BasicText", "", "text", "", "modifier", "Landroidx/compose/ui/Modifier;", "style", "Landroidx/compose/ui/text/TextStyle;", "onTextLayout", "Lkotlin/Function1;", "Landroidx/compose/ui/text/TextLayoutResult;", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "color", "Landroidx/compose/ui/graphics/ColorProducer;", "autoSize", "Landroidx/compose/foundation/text/TextAutoSize;", "BasicText-RWo7tUw", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILandroidx/compose/ui/graphics/ColorProducer;Landroidx/compose/foundation/text/TextAutoSize;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/text/AnnotatedString;", "inlineContent", "", "Landroidx/compose/foundation/text/InlineTextContent;", "BasicText-CL7eQgs", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILjava/util/Map;Landroidx/compose/ui/graphics/ColorProducer;Landroidx/compose/foundation/text/TextAutoSize;Landroidx/compose/runtime/Composer;III)V", "BasicText-VhcvRP8", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILandroidx/compose/ui/graphics/ColorProducer;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILjava/util/Map;Landroidx/compose/ui/graphics/ColorProducer;Landroidx/compose/runtime/Composer;II)V", "BasicText-BpD7jsM", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZILandroidx/compose/runtime/Composer;II)V", "BasicText-4YKlhWE", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZILjava/util/Map;Landroidx/compose/runtime/Composer;II)V", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILjava/util/Map;Landroidx/compose/runtime/Composer;II)V", "selectionIdSaver", "Landroidx/compose/runtime/saveable/Saver;", "", "selectionRegistrar", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "measureWithTextRangeMeasureConstraints", "", "Lkotlin/Pair;", "Landroidx/compose/ui/layout/Placeable;", "Lkotlin/Function0;", "Landroidx/compose/ui/unit/IntOffset;", "measurables", "Landroidx/compose/ui/layout/Measurable;", "shouldMeasureLinks", "textModifier", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "placeholders", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "onPlaceholderLayout", "Landroidx/compose/ui/geometry/Rect;", "selectionController", "Landroidx/compose/foundation/text/modifiers/SelectionController;", "onShowTranslation", "Landroidx/compose/foundation/text/modifiers/TextAnnotatedStringNode$TextSubstitutionValue;", "textModifier-CL7eQgs", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILandroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/text/modifiers/SelectionController;Landroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/text/TextAutoSize;)Landroidx/compose/ui/Modifier;", "LayoutWithLinksAndInlineContent", "hasInlineContent", "LayoutWithLinksAndInlineContent-11Od_4g", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/AnnotatedString;Lkotlin/jvm/functions/Function1;ZLjava/util/Map;Landroidx/compose/ui/text/TextStyle;IZIILandroidx/compose/ui/text/font/FontFamily$Resolver;Landroidx/compose/foundation/text/modifiers/SelectionController;Landroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/text/TextAutoSize;Landroidx/compose/runtime/Composer;III)V", "foundation_release", "displayedText"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BasicTextKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicText_4YKlhWE$lambda$16(AnnotatedString annotatedString, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, Map map, int i3, int i4, Composer composer, int i5) {
        m1273BasicText4YKlhWE(annotatedString, modifier, textStyle, function1, i, z, i2, map, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicText_4YKlhWE$lambda$17(String str, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, int i4, int i5, Composer composer, int i6) {
        m1274BasicText4YKlhWE(str, modifier, textStyle, function1, i, z, i2, i3, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicText_BpD7jsM$lambda$15(String str, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, int i4, Composer composer, int i5) {
        m1275BasicTextBpD7jsM(str, modifier, textStyle, function1, i, z, i2, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicText_CL7eQgs$lambda$12(AnnotatedString annotatedString, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, Map map, ColorProducer colorProducer, TextAutoSize textAutoSize, int i4, int i5, int i6, Composer composer, int i7) {
        m1276BasicTextCL7eQgs(annotatedString, modifier, textStyle, function1, i, z, i2, i3, map, colorProducer, textAutoSize, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicText_RWo7tUw$lambda$14(AnnotatedString annotatedString, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, Map map, ColorProducer colorProducer, int i4, int i5, Composer composer, int i6) {
        m1277BasicTextRWo7tUw(annotatedString, modifier, textStyle, function1, i, z, i2, i3, map, colorProducer, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicText_RWo7tUw$lambda$3(String str, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, ColorProducer colorProducer, TextAutoSize textAutoSize, int i4, int i5, Composer composer, int i6) {
        m1278BasicTextRWo7tUw(str, modifier, textStyle, (Function1<? super TextLayoutResult, Unit>) function1, i, z, i2, i3, colorProducer, textAutoSize, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicText_VhcvRP8$lambda$13(String str, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, ColorProducer colorProducer, int i4, int i5, Composer composer, int i6) {
        m1280BasicTextVhcvRP8(str, modifier, textStyle, function1, i, z, i2, i3, colorProducer, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicText_VhcvRP8$lambda$18(AnnotatedString annotatedString, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, Map map, int i4, int i5, Composer composer, int i6) {
        m1279BasicTextVhcvRP8(annotatedString, modifier, textStyle, function1, i, z, i2, i3, map, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnnotatedString LayoutWithLinksAndInlineContent_11Od_4g$lambda$27$lambda$26(AnnotatedString annotatedString) {
        return annotatedString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LayoutWithLinksAndInlineContent_11Od_4g$lambda$43(Modifier modifier, AnnotatedString annotatedString, Function1 function1, boolean z, Map map, TextStyle textStyle, int i, boolean z2, int i2, int i3, FontFamily.Resolver resolver, SelectionController selectionController, ColorProducer colorProducer, Function1 function12, TextAutoSize textAutoSize, int i4, int i5, int i6, Composer composer, int i7) {
        m1281LayoutWithLinksAndInlineContent11Od_4g(modifier, annotatedString, function1, z, map, textStyle, i, z2, i2, i3, resolver, selectionController, colorProducer, function12, textAutoSize, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
    /* renamed from: BasicText-RWo7tUw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m1278BasicTextRWo7tUw(final String str, Modifier modifier, TextStyle textStyle, Function1<? super TextLayoutResult, Unit> function1, int i, boolean z, int i2, int i3, ColorProducer colorProducer, TextAutoSize textAutoSize, Composer composer, final int i4, final int i5) {
        int i6;
        Modifier modifier2;
        int i7;
        TextStyle textStyle2;
        int i8;
        Function1<? super TextLayoutResult, Unit> function12;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        final boolean z2;
        final int i18;
        final TextAutoSize textAutoSize2;
        Composer composer2;
        final Modifier modifier3;
        final TextStyle textStyle3;
        final Function1<? super TextLayoutResult, Unit> function13;
        final int i19;
        final int i20;
        final ColorProducer colorProducer2;
        ScopeUpdateScope endRestartGroup;
        int i21;
        int i22;
        SelectionController selectionController;
        int i23;
        TextStyle textStyle4;
        Modifier modifier4;
        Modifier m1282textModifierCL7eQgs;
        Composer startRestartGroup = composer.startRestartGroup(-1040751001);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicText)N(text,modifier,style,onTextLayout,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines,minLines,color,autoSize)104@5458L7,119@6129L7,121@6142L94,154@7432L41:BasicText.kt#423gt5");
        if ((i5 & 1) != 0) {
            i6 = i4 | 6;
        } else if ((i4 & 6) == 0) {
            i6 = (startRestartGroup.changed(str) ? 4 : 2) | i4;
        } else {
            i6 = i4;
        }
        int i24 = i5 & 2;
        if (i24 != 0) {
            i6 |= 48;
        } else if ((i4 & 48) == 0) {
            modifier2 = modifier;
            i6 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i7 = i5 & 4;
            if (i7 == 0) {
                i6 |= 384;
            } else if ((i4 & 384) == 0) {
                textStyle2 = textStyle;
                i6 |= startRestartGroup.changed(textStyle2) ? 256 : 128;
                i8 = i5 & 8;
                if (i8 != 0) {
                    i6 |= 3072;
                } else if ((i4 & 3072) == 0) {
                    function12 = function1;
                    i6 |= startRestartGroup.changedInstance(function12) ? 2048 : 1024;
                    i9 = i5 & 16;
                    if (i9 == 0) {
                        i6 |= 24576;
                    } else if ((i4 & 24576) == 0) {
                        i10 = i;
                        i6 |= startRestartGroup.changed(i10) ? 16384 : 8192;
                        i11 = i5 & 32;
                        if (i11 != 0) {
                            i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            i6 |= startRestartGroup.changed(z) ? 131072 : 65536;
                        }
                        i12 = i5 & 64;
                        if (i12 != 0) {
                            i6 |= 1572864;
                        } else if ((i4 & 1572864) == 0) {
                            i6 |= startRestartGroup.changed(i2) ? 1048576 : 524288;
                        }
                        i13 = i5 & 128;
                        if (i13 != 0) {
                            i6 |= 12582912;
                        } else if ((i4 & 12582912) == 0) {
                            i14 = i13;
                            i6 |= startRestartGroup.changed(i3) ? 8388608 : 4194304;
                            i15 = i5 & 256;
                            if (i15 == 0) {
                                i6 |= 100663296;
                            } else if ((i4 & 100663296) == 0) {
                                i16 = i15;
                                i6 |= startRestartGroup.changedInstance(colorProducer) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                i17 = i5 & 512;
                                int i25 = 805306368;
                                if (i17 == 0) {
                                    if ((i4 & 805306368) == 0) {
                                        i25 = (i4 & 1073741824) == 0 ? startRestartGroup.changed(textAutoSize) : startRestartGroup.changedInstance(textAutoSize) ? 536870912 : 268435456;
                                    }
                                    if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378, i6 & 1)) {
                                        startRestartGroup.skipToGroupEnd();
                                        z2 = z;
                                        i18 = i3;
                                        textAutoSize2 = textAutoSize;
                                        composer2 = startRestartGroup;
                                        modifier3 = modifier2;
                                        textStyle3 = textStyle2;
                                        function13 = function12;
                                        i19 = i10;
                                        i20 = i2;
                                        colorProducer2 = colorProducer;
                                    } else {
                                        if (i24 != 0) {
                                            modifier2 = Modifier.INSTANCE;
                                        }
                                        if (i7 != 0) {
                                            textStyle2 = TextStyle.INSTANCE.getDefault();
                                        }
                                        Function1<? super TextLayoutResult, Unit> function14 = i8 != 0 ? null : function12;
                                        int m7324getClipgIe3tQ8 = i9 != 0 ? TextOverflow.INSTANCE.m7324getClipgIe3tQ8() : i10;
                                        boolean z3 = i11 != 0 ? true : z;
                                        int i26 = i12 != 0 ? Integer.MAX_VALUE : i2;
                                        int i27 = i14 != 0 ? 1 : i3;
                                        ColorProducer colorProducer3 = i16 != 0 ? null : colorProducer;
                                        TextAutoSize textAutoSize3 = i17 != 0 ? null : textAutoSize;
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1040751001, i6, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:102)");
                                        }
                                        HeightInLinesModifierKt.validateMinMaxLines(i27, i26);
                                        ProvidableCompositionLocal<SelectionRegistrar> localSelectionRegistrar = SelectionRegistrarKt.getLocalSelectionRegistrar();
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                        Object consume = startRestartGroup.consume(localSelectionRegistrar);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        final SelectionRegistrar selectionRegistrar = (SelectionRegistrar) consume;
                                        if (selectionRegistrar != null) {
                                            startRestartGroup.startReplaceGroup(356434979);
                                            ComposerKt.sourceInformation(startRestartGroup, "107@5606L7,109@5760L77,109@5677L160,112@5850L180");
                                            ProvidableCompositionLocal<TextSelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                            Object consume2 = startRestartGroup.consume(localTextSelectionColors);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            long backgroundColor = ((TextSelectionColors) consume2).getBackgroundColor();
                                            Object[] objArr = {selectionRegistrar};
                                            Saver<Long, Long> selectionIdSaver = selectionIdSaver(selectionRegistrar);
                                            i21 = i26;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 565693972, "CC(remember):BasicText.kt#9igjgp");
                                            boolean changedInstance = startRestartGroup.changedInstance(selectionRegistrar);
                                            Object rememberedValue = startRestartGroup.rememberedValue();
                                            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue = new Function0() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda15
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        long nextSelectableId;
                                                        nextSelectableId = SelectionRegistrar.this.nextSelectableId();
                                                        return Long.valueOf(nextSelectableId);
                                                    }
                                                };
                                                startRestartGroup.updateRememberedValue(rememberedValue);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            i22 = i6;
                                            long longValue = ((Number) RememberSaveableKt.m4033rememberSaveable(objArr, (Saver) selectionIdSaver, (Function0) rememberedValue, startRestartGroup, 0)).longValue();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 565696955, "CC(remember):BasicText.kt#9igjgp");
                                            boolean changed = startRestartGroup.changed(longValue) | startRestartGroup.changed(selectionRegistrar) | startRestartGroup.changed(backgroundColor);
                                            Object rememberedValue2 = startRestartGroup.rememberedValue();
                                            if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue2 = new SelectionController(longValue, selectionRegistrar, backgroundColor, null, 8, null);
                                                startRestartGroup.updateRememberedValue(rememberedValue2);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            startRestartGroup.endReplaceGroup();
                                            selectionController = (SelectionController) rememberedValue2;
                                        } else {
                                            i21 = i26;
                                            i22 = i6;
                                            startRestartGroup.startReplaceGroup(356926143);
                                            startRestartGroup.endReplaceGroup();
                                            selectionController = null;
                                        }
                                        ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver = CompositionLocalsKt.getLocalFontFamilyResolver();
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                        Object consume3 = startRestartGroup.consume(localFontFamilyResolver);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        FontFamily.Resolver resolver = (FontFamily.Resolver) consume3;
                                        BasicText_androidKt.BackgroundTextMeasurement(str, textStyle2, resolver, startRestartGroup, ((i22 >> 3) & 112) | (i22 & 14));
                                        if (selectionController != null || function14 != null || textAutoSize3 != null) {
                                            i23 = i27;
                                            textStyle4 = textStyle2;
                                            startRestartGroup.startReplaceGroup(357244017);
                                            ComposerKt.sourceInformation(startRestartGroup, "133@6715L7");
                                            AnnotatedString annotatedString = new AnnotatedString(str, null, 2, null);
                                            ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver2 = CompositionLocalsKt.getLocalFontFamilyResolver();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                            Object consume4 = startRestartGroup.consume(localFontFamilyResolver2);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            modifier4 = modifier2;
                                            m1282textModifierCL7eQgs = m1282textModifierCL7eQgs(modifier4, annotatedString, textStyle4, function14, m7324getClipgIe3tQ8, z3, i21, i23, (FontFamily.Resolver) consume4, null, null, selectionController, colorProducer3, null, textAutoSize3);
                                            startRestartGroup.endReplaceGroup();
                                        } else {
                                            startRestartGroup.startReplaceGroup(357887763);
                                            startRestartGroup.endReplaceGroup();
                                            int i28 = i27;
                                            TextStyle textStyle5 = textStyle2;
                                            textStyle4 = textStyle5;
                                            i23 = i28;
                                            m1282textModifierCL7eQgs = modifier2.then(new TextStringSimpleElement(str, textStyle5, resolver, m7324getClipgIe3tQ8, z3, i21, i28, colorProducer3, null));
                                            modifier4 = modifier2;
                                        }
                                        EmptyMeasurePolicy emptyMeasurePolicy = EmptyMeasurePolicy.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 544976794, "CC(Layout)P(1)122@4875L27,125@5041L333:Layout.kt#80mrfh");
                                        int m = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, m1282textModifierCL7eQgs);
                                        CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1405779621, "CC(ReusableComposeNode)N(factory,update):Composables.kt#9igjgp");
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
                                        Updater.m3912setimpl(m3905constructorimpl, emptyMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(m))) {
                                            m3905constructorimpl.updateRememberedValue(Integer.valueOf(m));
                                            m3905constructorimpl.apply(Integer.valueOf(m), setCompositeKeyHash);
                                        }
                                        startRestartGroup.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        composer2 = startRestartGroup;
                                        modifier3 = modifier4;
                                        textStyle3 = textStyle4;
                                        function13 = function14;
                                        i19 = m7324getClipgIe3tQ8;
                                        z2 = z3;
                                        i20 = i21;
                                        i18 = i23;
                                        colorProducer2 = colorProducer3;
                                        textAutoSize2 = textAutoSize3;
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup == null) {
                                        endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda16
                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(Object obj, Object obj2) {
                                                Unit BasicText_RWo7tUw$lambda$3;
                                                BasicText_RWo7tUw$lambda$3 = BasicTextKt.BasicText_RWo7tUw$lambda$3(str, modifier3, textStyle3, function13, i19, z2, i20, i18, colorProducer2, textAutoSize2, i4, i5, (Composer) obj, ((Integer) obj2).intValue());
                                                return BasicText_RWo7tUw$lambda$3;
                                            }
                                        });
                                        return;
                                    }
                                    return;
                                }
                                i6 |= i25;
                                if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378, i6 & 1)) {
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup == null) {
                                }
                            }
                            i16 = i15;
                            i17 = i5 & 512;
                            int i252 = 805306368;
                            if (i17 == 0) {
                            }
                            i6 |= i252;
                            if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378, i6 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                            }
                        }
                        i14 = i13;
                        i15 = i5 & 256;
                        if (i15 == 0) {
                        }
                        i16 = i15;
                        i17 = i5 & 512;
                        int i2522 = 805306368;
                        if (i17 == 0) {
                        }
                        i6 |= i2522;
                        if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378, i6 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i10 = i;
                    i11 = i5 & 32;
                    if (i11 != 0) {
                    }
                    i12 = i5 & 64;
                    if (i12 != 0) {
                    }
                    i13 = i5 & 128;
                    if (i13 != 0) {
                    }
                    i14 = i13;
                    i15 = i5 & 256;
                    if (i15 == 0) {
                    }
                    i16 = i15;
                    i17 = i5 & 512;
                    int i25222 = 805306368;
                    if (i17 == 0) {
                    }
                    i6 |= i25222;
                    if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378, i6 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                function12 = function1;
                i9 = i5 & 16;
                if (i9 == 0) {
                }
                i10 = i;
                i11 = i5 & 32;
                if (i11 != 0) {
                }
                i12 = i5 & 64;
                if (i12 != 0) {
                }
                i13 = i5 & 128;
                if (i13 != 0) {
                }
                i14 = i13;
                i15 = i5 & 256;
                if (i15 == 0) {
                }
                i16 = i15;
                i17 = i5 & 512;
                int i252222 = 805306368;
                if (i17 == 0) {
                }
                i6 |= i252222;
                if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378, i6 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            textStyle2 = textStyle;
            i8 = i5 & 8;
            if (i8 != 0) {
            }
            function12 = function1;
            i9 = i5 & 16;
            if (i9 == 0) {
            }
            i10 = i;
            i11 = i5 & 32;
            if (i11 != 0) {
            }
            i12 = i5 & 64;
            if (i12 != 0) {
            }
            i13 = i5 & 128;
            if (i13 != 0) {
            }
            i14 = i13;
            i15 = i5 & 256;
            if (i15 == 0) {
            }
            i16 = i15;
            i17 = i5 & 512;
            int i2522222 = 805306368;
            if (i17 == 0) {
            }
            i6 |= i2522222;
            if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378, i6 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i7 = i5 & 4;
        if (i7 == 0) {
        }
        textStyle2 = textStyle;
        i8 = i5 & 8;
        if (i8 != 0) {
        }
        function12 = function1;
        i9 = i5 & 16;
        if (i9 == 0) {
        }
        i10 = i;
        i11 = i5 & 32;
        if (i11 != 0) {
        }
        i12 = i5 & 64;
        if (i12 != 0) {
        }
        i13 = i5 & 128;
        if (i13 != 0) {
        }
        i14 = i13;
        i15 = i5 & 256;
        if (i15 == 0) {
        }
        i16 = i15;
        i17 = i5 & 512;
        int i25222222 = 805306368;
        if (i17 == 0) {
        }
        i6 |= i25222222;
        if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378, i6 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x0254, code lost:
    
        if (r5 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L174;
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x04a5  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0189  */
    /* renamed from: BasicText-CL7eQgs, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m1276BasicTextCL7eQgs(AnnotatedString annotatedString, Modifier modifier, TextStyle textStyle, Function1<? super TextLayoutResult, Unit> function1, int i, boolean z, int i2, int i3, Map<String, InlineTextContent> map, ColorProducer colorProducer, TextAutoSize textAutoSize, Composer composer, final int i4, final int i5, final int i6) {
        int i7;
        int i8;
        TextStyle textStyle2;
        int i9;
        Function1<? super TextLayoutResult, Unit> function12;
        int i10;
        int i11;
        int i12;
        int i13;
        final int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        final AnnotatedString annotatedString2;
        Composer composer2;
        final Modifier modifier2;
        final int i24;
        final Map<String, InlineTextContent> map2;
        final ColorProducer colorProducer2;
        final int i25;
        final TextStyle textStyle3;
        final Function1<? super TextLayoutResult, Unit> function13;
        final boolean z2;
        final TextAutoSize textAutoSize2;
        ScopeUpdateScope endRestartGroup;
        int i26;
        Modifier modifier3;
        TextStyle textStyle4;
        Function1<? super TextLayoutResult, Unit> function14;
        int i27;
        int i28;
        SelectionController selectionController;
        boolean z3;
        int i29;
        Function1<? super TextLayoutResult, Unit> function15;
        int i30;
        TextStyle textStyle5;
        Composer startRestartGroup = composer.startRestartGroup(-1343466571);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicText)N(text,modifier,style,onTextLayout,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines,minLines,inlineContent,color,autoSize)202@10280L7,219@11037L7:BasicText.kt#423gt5");
        if ((i6 & 1) != 0) {
            i7 = i4 | 6;
        } else if ((i4 & 6) == 0) {
            i7 = (startRestartGroup.changed(annotatedString) ? 4 : 2) | i4;
        } else {
            i7 = i4;
        }
        int i31 = i6 & 2;
        if (i31 != 0) {
            i7 |= 48;
        } else if ((i4 & 48) == 0) {
            i7 |= startRestartGroup.changed(modifier) ? 32 : 16;
            i8 = i6 & 4;
            if (i8 == 0) {
                i7 |= 384;
            } else if ((i4 & 384) == 0) {
                textStyle2 = textStyle;
                i7 |= startRestartGroup.changed(textStyle2) ? 256 : 128;
                i9 = i6 & 8;
                if (i9 != 0) {
                    i7 |= 3072;
                } else if ((i4 & 3072) == 0) {
                    function12 = function1;
                    i7 |= startRestartGroup.changedInstance(function12) ? 2048 : 1024;
                    i10 = i6 & 16;
                    if (i10 == 0) {
                        i7 |= 24576;
                    } else if ((i4 & 24576) == 0) {
                        i11 = i;
                        i7 |= startRestartGroup.changed(i11) ? 16384 : 8192;
                        i12 = i6 & 32;
                        if (i12 != 0) {
                            i7 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            i7 |= startRestartGroup.changed(z) ? 131072 : 65536;
                        }
                        i13 = i6 & 64;
                        if (i13 != 0) {
                            i7 |= 1572864;
                            i14 = i2;
                        } else {
                            i14 = i2;
                            if ((i4 & 1572864) == 0) {
                                i7 |= startRestartGroup.changed(i14) ? 1048576 : 524288;
                            }
                        }
                        i15 = i6 & 128;
                        if (i15 != 0) {
                            i7 |= 12582912;
                        } else if ((i4 & 12582912) == 0) {
                            i16 = i15;
                            i7 |= startRestartGroup.changed(i3) ? 8388608 : 4194304;
                            i17 = i6 & 256;
                            if (i17 == 0) {
                                i7 |= 100663296;
                            } else if ((i4 & 100663296) == 0) {
                                i18 = i17;
                                i7 |= startRestartGroup.changedInstance(map) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                i19 = i6 & 512;
                                if (i19 != 0) {
                                    i7 |= 805306368;
                                } else if ((i4 & 805306368) == 0) {
                                    i20 = i19;
                                    i7 |= startRestartGroup.changedInstance(colorProducer) ? 536870912 : 268435456;
                                    i21 = i6 & 1024;
                                    if (i21 == 0) {
                                        i23 = i5 | 6;
                                    } else {
                                        if ((i5 & 6) != 0) {
                                            i22 = i5;
                                            if (!startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i22 & 3) != 2, i7 & 1)) {
                                                annotatedString2 = annotatedString;
                                                composer2 = startRestartGroup;
                                                composer2.skipToGroupEnd();
                                                modifier2 = modifier;
                                                i24 = i3;
                                                map2 = map;
                                                colorProducer2 = colorProducer;
                                                i25 = i11;
                                                textStyle3 = textStyle2;
                                                function13 = function12;
                                                z2 = z;
                                                textAutoSize2 = textAutoSize;
                                            } else {
                                                Modifier.Companion companion = i31 != 0 ? Modifier.INSTANCE : modifier;
                                                TextStyle textStyle6 = i8 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle2;
                                                Function1<? super TextLayoutResult, Unit> function16 = i9 != 0 ? null : function12;
                                                if (i10 != 0) {
                                                    i11 = TextOverflow.INSTANCE.m7324getClipgIe3tQ8();
                                                }
                                                boolean z4 = i12 != 0 ? true : z;
                                                if (i13 != 0) {
                                                    i14 = Integer.MAX_VALUE;
                                                }
                                                int i32 = i14;
                                                int i33 = i11;
                                                int i34 = i16 != 0 ? 1 : i3;
                                                Map<String, InlineTextContent> emptyMap = i18 != 0 ? MapsKt.emptyMap() : map;
                                                ColorProducer colorProducer3 = i20 != 0 ? null : colorProducer;
                                                TextAutoSize textAutoSize3 = i21 != 0 ? null : textAutoSize;
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1343466571, i7, i22, "androidx.compose.foundation.text.BasicText (BasicText.kt:200)");
                                                }
                                                HeightInLinesModifierKt.validateMinMaxLines(i34, i32);
                                                ProvidableCompositionLocal<SelectionRegistrar> localSelectionRegistrar = SelectionRegistrarKt.getLocalSelectionRegistrar();
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                                Object consume = startRestartGroup.consume(localSelectionRegistrar);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                final SelectionRegistrar selectionRegistrar = (SelectionRegistrar) consume;
                                                if (selectionRegistrar != null) {
                                                    i26 = i22;
                                                    startRestartGroup.startReplaceGroup(1588280149);
                                                    ComposerKt.sourceInformation(startRestartGroup, "205@10428L7,207@10582L77,207@10499L160,210@10672L180");
                                                    ProvidableCompositionLocal<TextSelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
                                                    modifier3 = companion;
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                                    Object consume2 = startRestartGroup.consume(localTextSelectionColors);
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    long backgroundColor = ((TextSelectionColors) consume2).getBackgroundColor();
                                                    textStyle4 = textStyle6;
                                                    function14 = function16;
                                                    Object[] objArr = {selectionRegistrar};
                                                    Saver<Long, Long> selectionIdSaver = selectionIdSaver(selectionRegistrar);
                                                    i27 = i7;
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1713809570, "CC(remember):BasicText.kt#9igjgp");
                                                    boolean changedInstance = startRestartGroup.changedInstance(selectionRegistrar);
                                                    Object rememberedValue = startRestartGroup.rememberedValue();
                                                    if (changedInstance) {
                                                        i28 = i32;
                                                    } else {
                                                        i28 = i32;
                                                    }
                                                    rememberedValue = new Function0() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda20
                                                        @Override // kotlin.jvm.functions.Function0
                                                        public final Object invoke() {
                                                            long nextSelectableId;
                                                            nextSelectableId = SelectionRegistrar.this.nextSelectableId();
                                                            return Long.valueOf(nextSelectableId);
                                                        }
                                                    };
                                                    startRestartGroup.updateRememberedValue(rememberedValue);
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    long longValue = ((Number) RememberSaveableKt.m4033rememberSaveable(objArr, (Saver) selectionIdSaver, (Function0) rememberedValue, startRestartGroup, 0)).longValue();
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1713812553, "CC(remember):BasicText.kt#9igjgp");
                                                    boolean changed = startRestartGroup.changed(longValue) | startRestartGroup.changed(selectionRegistrar) | startRestartGroup.changed(backgroundColor);
                                                    Object rememberedValue2 = startRestartGroup.rememberedValue();
                                                    if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                                        rememberedValue2 = new SelectionController(longValue, selectionRegistrar, backgroundColor, null, 8, null);
                                                        startRestartGroup.updateRememberedValue(rememberedValue2);
                                                    }
                                                    selectionController = (SelectionController) rememberedValue2;
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    startRestartGroup.endReplaceGroup();
                                                } else {
                                                    i26 = i22;
                                                    modifier3 = companion;
                                                    textStyle4 = textStyle6;
                                                    function14 = function16;
                                                    i27 = i7;
                                                    i28 = i32;
                                                    startRestartGroup.startReplaceGroup(1588771313);
                                                    startRestartGroup.endReplaceGroup();
                                                    selectionController = null;
                                                }
                                                boolean hasInlineContent = AnnotatedStringResolveInlineContentKt.hasInlineContent(annotatedString);
                                                boolean hasLinks = TextAnnotatedStringNodeKt.hasLinks(annotatedString);
                                                ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver = CompositionLocalsKt.getLocalFontFamilyResolver();
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                                Object consume3 = startRestartGroup.consume(localFontFamilyResolver);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                FontFamily.Resolver resolver = (FontFamily.Resolver) consume3;
                                                if (!hasInlineContent && !hasLinks) {
                                                    startRestartGroup.startReplaceGroup(1589018166);
                                                    ComposerKt.sourceInformation(startRestartGroup, "222@11096L174,230@11349L746");
                                                    TextStyle textStyle7 = textStyle4;
                                                    BasicText_androidKt.BackgroundTextMeasurement(annotatedString, textStyle7, resolver, null, startRestartGroup, (i27 & 14) | 3072 | ((i27 >> 3) & 112));
                                                    z3 = z4;
                                                    annotatedString2 = annotatedString;
                                                    i29 = i33;
                                                    function15 = function14;
                                                    SelectionController selectionController2 = selectionController;
                                                    i30 = i28;
                                                    Modifier m1282textModifierCL7eQgs = m1282textModifierCL7eQgs(modifier3, annotatedString2, textStyle7, function15, i29, z3, i30, i34, resolver, null, null, selectionController2, colorProducer3, null, textAutoSize3);
                                                    EmptyMeasurePolicy emptyMeasurePolicy = EmptyMeasurePolicy.INSTANCE;
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 544976794, "CC(Layout)P(1)122@4875L27,125@5041L333:Layout.kt#80mrfh");
                                                    int m = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                                                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, m1282textModifierCL7eQgs);
                                                    CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
                                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1405779621, "CC(ReusableComposeNode)N(factory,update):Composables.kt#9igjgp");
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
                                                    Updater.m3912setimpl(m3905constructorimpl, emptyMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                    Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                    if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(m))) {
                                                        m3905constructorimpl.updateRememberedValue(Integer.valueOf(m));
                                                        m3905constructorimpl.apply(Integer.valueOf(m), setCompositeKeyHash);
                                                    }
                                                    startRestartGroup.endNode();
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    startRestartGroup.endReplaceGroup();
                                                    composer2 = startRestartGroup;
                                                    textStyle5 = textStyle7;
                                                } else {
                                                    annotatedString2 = annotatedString;
                                                    z3 = z4;
                                                    i29 = i33;
                                                    function15 = function14;
                                                    SelectionController selectionController3 = selectionController;
                                                    i30 = i28;
                                                    startRestartGroup.startReplaceGroup(1590033974);
                                                    ComposerKt.sourceInformation(startRestartGroup, "253@12268L39,269@12876L292,255@12317L895");
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1713863484, "CC(remember):BasicText.kt#9igjgp");
                                                    boolean z5 = (i27 & 14) == 4;
                                                    Object rememberedValue3 = startRestartGroup.rememberedValue();
                                                    if (z5 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                                        rememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(annotatedString2, null, 2, null);
                                                        startRestartGroup.updateRememberedValue(rememberedValue3);
                                                    }
                                                    final MutableState mutableState = (MutableState) rememberedValue3;
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    AnnotatedString BasicText_CL7eQgs$lambda$8 = BasicText_CL7eQgs$lambda$8(mutableState);
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1713883193, "CC(remember):BasicText.kt#9igjgp");
                                                    boolean changed2 = startRestartGroup.changed(mutableState);
                                                    Object rememberedValue4 = startRestartGroup.rememberedValue();
                                                    if (changed2 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                        rememberedValue4 = new Function1() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda0
                                                            @Override // kotlin.jvm.functions.Function1
                                                            public final Object invoke(Object obj) {
                                                                Unit BasicText_CL7eQgs$lambda$11$lambda$10;
                                                                BasicText_CL7eQgs$lambda$11$lambda$10 = BasicTextKt.BasicText_CL7eQgs$lambda$11$lambda$10(MutableState.this, (TextAnnotatedStringNode.TextSubstitutionValue) obj);
                                                                return BasicText_CL7eQgs$lambda$11$lambda$10;
                                                            }
                                                        };
                                                        startRestartGroup.updateRememberedValue(rememberedValue4);
                                                    }
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                    int i35 = i27 << 6;
                                                    composer2 = startRestartGroup;
                                                    Modifier modifier4 = modifier3;
                                                    TextStyle textStyle8 = textStyle4;
                                                    m1281LayoutWithLinksAndInlineContent11Od_4g(modifier4, BasicText_CL7eQgs$lambda$8, function15, hasInlineContent, emptyMap, textStyle8, i29, z3, i30, i34, resolver, selectionController3, colorProducer3, (Function1) rememberedValue4, textAutoSize3, composer2, ((i27 >> 3) & 910) | ((i27 >> 12) & 57344) | ((i27 << 9) & 458752) | (3670016 & i35) | (29360128 & i35) | (234881024 & i35) | (i35 & 1879048192), ((i27 >> 21) & 896) | ((i26 << 12) & 57344), 0);
                                                    textStyle5 = textStyle8;
                                                    modifier3 = modifier4;
                                                    composer2.endReplaceGroup();
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                                i24 = i34;
                                                colorProducer2 = colorProducer3;
                                                textAutoSize2 = textAutoSize3;
                                                map2 = emptyMap;
                                                i14 = i30;
                                                z2 = z3;
                                                i25 = i29;
                                                function13 = function15;
                                                textStyle3 = textStyle5;
                                                modifier2 = modifier3;
                                            }
                                            endRestartGroup = composer2.endRestartGroup();
                                            if (endRestartGroup != null) {
                                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda1
                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(Object obj, Object obj2) {
                                                        Unit BasicText_CL7eQgs$lambda$12;
                                                        BasicText_CL7eQgs$lambda$12 = BasicTextKt.BasicText_CL7eQgs$lambda$12(AnnotatedString.this, modifier2, textStyle3, function13, i25, z2, i14, i24, map2, colorProducer2, textAutoSize2, i4, i5, i6, (Composer) obj, ((Integer) obj2).intValue());
                                                        return BasicText_CL7eQgs$lambda$12;
                                                    }
                                                });
                                                return;
                                            }
                                            return;
                                        }
                                        i23 = i5 | ((i5 & 8) == 0 ? startRestartGroup.changed(textAutoSize) : startRestartGroup.changedInstance(textAutoSize) ? 4 : 2);
                                    }
                                    i22 = i23;
                                    if (!startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i22 & 3) != 2, i7 & 1)) {
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup != null) {
                                    }
                                }
                                i20 = i19;
                                i21 = i6 & 1024;
                                if (i21 == 0) {
                                }
                                i22 = i23;
                                if (!startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i22 & 3) != 2, i7 & 1)) {
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                }
                            }
                            i18 = i17;
                            i19 = i6 & 512;
                            if (i19 != 0) {
                            }
                            i20 = i19;
                            i21 = i6 & 1024;
                            if (i21 == 0) {
                            }
                            i22 = i23;
                            if (!startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i22 & 3) != 2, i7 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        i16 = i15;
                        i17 = i6 & 256;
                        if (i17 == 0) {
                        }
                        i18 = i17;
                        i19 = i6 & 512;
                        if (i19 != 0) {
                        }
                        i20 = i19;
                        i21 = i6 & 1024;
                        if (i21 == 0) {
                        }
                        i22 = i23;
                        if (!startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i22 & 3) != 2, i7 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    i11 = i;
                    i12 = i6 & 32;
                    if (i12 != 0) {
                    }
                    i13 = i6 & 64;
                    if (i13 != 0) {
                    }
                    i15 = i6 & 128;
                    if (i15 != 0) {
                    }
                    i16 = i15;
                    i17 = i6 & 256;
                    if (i17 == 0) {
                    }
                    i18 = i17;
                    i19 = i6 & 512;
                    if (i19 != 0) {
                    }
                    i20 = i19;
                    i21 = i6 & 1024;
                    if (i21 == 0) {
                    }
                    i22 = i23;
                    if (!startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i22 & 3) != 2, i7 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                function12 = function1;
                i10 = i6 & 16;
                if (i10 == 0) {
                }
                i11 = i;
                i12 = i6 & 32;
                if (i12 != 0) {
                }
                i13 = i6 & 64;
                if (i13 != 0) {
                }
                i15 = i6 & 128;
                if (i15 != 0) {
                }
                i16 = i15;
                i17 = i6 & 256;
                if (i17 == 0) {
                }
                i18 = i17;
                i19 = i6 & 512;
                if (i19 != 0) {
                }
                i20 = i19;
                i21 = i6 & 1024;
                if (i21 == 0) {
                }
                i22 = i23;
                if (!startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i22 & 3) != 2, i7 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            textStyle2 = textStyle;
            i9 = i6 & 8;
            if (i9 != 0) {
            }
            function12 = function1;
            i10 = i6 & 16;
            if (i10 == 0) {
            }
            i11 = i;
            i12 = i6 & 32;
            if (i12 != 0) {
            }
            i13 = i6 & 64;
            if (i13 != 0) {
            }
            i15 = i6 & 128;
            if (i15 != 0) {
            }
            i16 = i15;
            i17 = i6 & 256;
            if (i17 == 0) {
            }
            i18 = i17;
            i19 = i6 & 512;
            if (i19 != 0) {
            }
            i20 = i19;
            i21 = i6 & 1024;
            if (i21 == 0) {
            }
            i22 = i23;
            if (!startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i22 & 3) != 2, i7 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        i8 = i6 & 4;
        if (i8 == 0) {
        }
        textStyle2 = textStyle;
        i9 = i6 & 8;
        if (i9 != 0) {
        }
        function12 = function1;
        i10 = i6 & 16;
        if (i10 == 0) {
        }
        i11 = i;
        i12 = i6 & 32;
        if (i12 != 0) {
        }
        i13 = i6 & 64;
        if (i13 != 0) {
        }
        i15 = i6 & 128;
        if (i15 != 0) {
        }
        i16 = i15;
        i17 = i6 & 256;
        if (i17 == 0) {
        }
        i18 = i17;
        i19 = i6 & 512;
        if (i19 != 0) {
        }
        i20 = i19;
        i21 = i6 & 1024;
        if (i21 == 0) {
        }
        i22 = i23;
        if (!startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i22 & 3) != 2, i7 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    private static final AnnotatedString BasicText_CL7eQgs$lambda$8(MutableState<AnnotatedString> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicText_CL7eQgs$lambda$11$lambda$10(MutableState mutableState, TextAnnotatedStringNode.TextSubstitutionValue textSubstitutionValue) {
        AnnotatedString original;
        if (textSubstitutionValue.isShowingSubstitution()) {
            original = textSubstitutionValue.getSubstitution();
        } else {
            original = textSubstitutionValue.getOriginal();
        }
        mutableState.setValue(original);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00c5  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: BasicText-VhcvRP8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m1280BasicTextVhcvRP8(final String str, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, ColorProducer colorProducer, Composer composer, final int i4, final int i5) {
        String str2;
        int i6;
        Modifier modifier2;
        int i7;
        TextStyle textStyle2;
        int i8;
        Function1 function12;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        Composer composer2;
        final int i16;
        final int i17;
        final ColorProducer colorProducer2;
        final Modifier modifier3;
        final TextStyle textStyle3;
        final Function1 function13;
        final boolean z2;
        final int i18;
        ScopeUpdateScope endRestartGroup;
        int i19;
        TextStyle textStyle4;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        Composer startRestartGroup = composer.startRestartGroup(-1186827822);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicText)N(text,modifier,style,onTextLayout,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines,minLines,color)318@15279L93:BasicText.kt#423gt5");
        if ((i5 & 1) != 0) {
            i6 = i4 | 6;
            str2 = str;
        } else {
            str2 = str;
            if ((i4 & 6) == 0) {
                i6 = (startRestartGroup.changed(str2) ? 4 : 2) | i4;
            } else {
                i6 = i4;
            }
        }
        int i26 = i5 & 2;
        if (i26 != 0) {
            i6 |= 48;
        } else if ((i4 & 48) == 0) {
            modifier2 = modifier;
            i6 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i7 = i5 & 4;
            if (i7 == 0) {
                i6 |= 384;
            } else if ((i4 & 384) == 0) {
                textStyle2 = textStyle;
                i6 |= startRestartGroup.changed(textStyle2) ? 256 : 128;
                i8 = i5 & 8;
                if (i8 != 0) {
                    i6 |= 3072;
                } else if ((i4 & 3072) == 0) {
                    function12 = function1;
                    i6 |= startRestartGroup.changedInstance(function12) ? 2048 : 1024;
                    i9 = i5 & 16;
                    if (i9 == 0) {
                        i6 |= 24576;
                    } else if ((i4 & 24576) == 0) {
                        i6 |= startRestartGroup.changed(i) ? 16384 : 8192;
                        i10 = i5 & 32;
                        if (i10 != 0) {
                            i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((196608 & i4) == 0) {
                            i6 |= startRestartGroup.changed(z) ? 131072 : 65536;
                            i11 = i5 & 64;
                            if (i11 == 0) {
                                i6 |= 1572864;
                            } else if ((i4 & 1572864) == 0) {
                                i6 |= startRestartGroup.changed(i2) ? 1048576 : 524288;
                            }
                            i12 = i5 & 128;
                            if (i12 == 0) {
                                i6 |= 12582912;
                            } else if ((i4 & 12582912) == 0) {
                                i13 = i12;
                                i6 |= startRestartGroup.changed(i3) ? 8388608 : 4194304;
                                i14 = i5 & 256;
                                if (i14 != 0) {
                                    i6 |= 100663296;
                                } else if ((i4 & 100663296) == 0) {
                                    i15 = i14;
                                    i6 |= startRestartGroup.changedInstance(colorProducer) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                    if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
                                        composer2 = startRestartGroup;
                                        composer2.skipToGroupEnd();
                                        i16 = i;
                                        i17 = i2;
                                        colorProducer2 = colorProducer;
                                        modifier3 = modifier2;
                                        textStyle3 = textStyle2;
                                        function13 = function12;
                                        z2 = z;
                                        i18 = i3;
                                    } else {
                                        Modifier modifier4 = i26 != 0 ? Modifier.INSTANCE : modifier2;
                                        if (i7 != 0) {
                                            textStyle4 = TextStyle.INSTANCE.getDefault();
                                            i19 = i10;
                                        } else {
                                            i19 = i10;
                                            textStyle4 = textStyle2;
                                        }
                                        Function1 function14 = i8 != 0 ? null : function12;
                                        if (i9 != 0) {
                                            i21 = TextOverflow.INSTANCE.m7324getClipgIe3tQ8();
                                            i20 = i11;
                                        } else {
                                            i20 = i11;
                                            i21 = i;
                                        }
                                        boolean z3 = i19 != 0 ? true : z;
                                        if (i20 != 0) {
                                            int i27 = i13;
                                            i23 = Integer.MAX_VALUE;
                                            i22 = i27;
                                        } else {
                                            i22 = i13;
                                            i23 = i2;
                                        }
                                        if (i22 != 0) {
                                            i24 = i15;
                                            i25 = 1;
                                        } else {
                                            i24 = i15;
                                            i25 = i3;
                                        }
                                        ColorProducer colorProducer3 = i24 != 0 ? null : colorProducer;
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1186827822, i6, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:317)");
                                        }
                                        composer2 = startRestartGroup;
                                        m1278BasicTextRWo7tUw(str2, modifier4, textStyle4, (Function1<? super TextLayoutResult, Unit>) function14, i21, z3, i23, i25, colorProducer3, (TextAutoSize) null, composer2, i6 & 268435454, 512);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        modifier3 = modifier4;
                                        textStyle3 = textStyle4;
                                        function13 = function14;
                                        i16 = i21;
                                        z2 = z3;
                                        i17 = i23;
                                        i18 = i25;
                                        colorProducer2 = colorProducer3;
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup == null) {
                                        endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda17
                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(Object obj, Object obj2) {
                                                Unit BasicText_VhcvRP8$lambda$13;
                                                BasicText_VhcvRP8$lambda$13 = BasicTextKt.BasicText_VhcvRP8$lambda$13(str, modifier3, textStyle3, function13, i16, z2, i17, i18, colorProducer2, i4, i5, (Composer) obj, ((Integer) obj2).intValue());
                                                return BasicText_VhcvRP8$lambda$13;
                                            }
                                        });
                                        return;
                                    }
                                    return;
                                }
                                i15 = i14;
                                if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup == null) {
                                }
                            }
                            i13 = i12;
                            i14 = i5 & 256;
                            if (i14 != 0) {
                            }
                            i15 = i14;
                            if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                            }
                        }
                        i11 = i5 & 64;
                        if (i11 == 0) {
                        }
                        i12 = i5 & 128;
                        if (i12 == 0) {
                        }
                        i13 = i12;
                        i14 = i5 & 256;
                        if (i14 != 0) {
                        }
                        i15 = i14;
                        if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i10 = i5 & 32;
                    if (i10 != 0) {
                    }
                    i11 = i5 & 64;
                    if (i11 == 0) {
                    }
                    i12 = i5 & 128;
                    if (i12 == 0) {
                    }
                    i13 = i12;
                    i14 = i5 & 256;
                    if (i14 != 0) {
                    }
                    i15 = i14;
                    if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                function12 = function1;
                i9 = i5 & 16;
                if (i9 == 0) {
                }
                i10 = i5 & 32;
                if (i10 != 0) {
                }
                i11 = i5 & 64;
                if (i11 == 0) {
                }
                i12 = i5 & 128;
                if (i12 == 0) {
                }
                i13 = i12;
                i14 = i5 & 256;
                if (i14 != 0) {
                }
                i15 = i14;
                if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            textStyle2 = textStyle;
            i8 = i5 & 8;
            if (i8 != 0) {
            }
            function12 = function1;
            i9 = i5 & 16;
            if (i9 == 0) {
            }
            i10 = i5 & 32;
            if (i10 != 0) {
            }
            i11 = i5 & 64;
            if (i11 == 0) {
            }
            i12 = i5 & 128;
            if (i12 == 0) {
            }
            i13 = i12;
            i14 = i5 & 256;
            if (i14 != 0) {
            }
            i15 = i14;
            if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i7 = i5 & 4;
        if (i7 == 0) {
        }
        textStyle2 = textStyle;
        i8 = i5 & 8;
        if (i8 != 0) {
        }
        function12 = function1;
        i9 = i5 & 16;
        if (i9 == 0) {
        }
        i10 = i5 & 32;
        if (i10 != 0) {
        }
        i11 = i5 & 64;
        if (i11 == 0) {
        }
        i12 = i5 & 128;
        if (i12 == 0) {
        }
        i13 = i12;
        i14 = i5 & 256;
        if (i14 != 0) {
        }
        i15 = i14;
        if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0104  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: BasicText-RWo7tUw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m1277BasicTextRWo7tUw(final AnnotatedString annotatedString, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, Map map, ColorProducer colorProducer, Composer composer, final int i4, final int i5) {
        AnnotatedString annotatedString2;
        int i6;
        Modifier modifier2;
        int i7;
        TextStyle textStyle2;
        int i8;
        Function1 function12;
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
        Composer composer2;
        final int i19;
        final Map map2;
        final Modifier modifier3;
        final TextStyle textStyle3;
        final Function1 function13;
        final int i20;
        final boolean z2;
        final int i21;
        final ColorProducer colorProducer2;
        ScopeUpdateScope endRestartGroup;
        int i22;
        Modifier modifier4;
        int i23;
        boolean z3;
        int i24;
        int i25;
        int i26;
        int i27;
        Composer startRestartGroup = composer.startRestartGroup(-1064305212);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicText)N(text,modifier,style,onTextLayout,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines,minLines,inlineContent,color)360@17696L195:BasicText.kt#423gt5");
        if ((i5 & 1) != 0) {
            i6 = i4 | 6;
            annotatedString2 = annotatedString;
        } else {
            annotatedString2 = annotatedString;
            if ((i4 & 6) == 0) {
                i6 = (startRestartGroup.changed(annotatedString2) ? 4 : 2) | i4;
            } else {
                i6 = i4;
            }
        }
        int i28 = i5 & 2;
        if (i28 != 0) {
            i6 |= 48;
        } else if ((i4 & 48) == 0) {
            modifier2 = modifier;
            i6 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i7 = i5 & 4;
            if (i7 == 0) {
                i6 |= 384;
            } else if ((i4 & 384) == 0) {
                textStyle2 = textStyle;
                i6 |= startRestartGroup.changed(textStyle2) ? 256 : 128;
                i8 = i5 & 8;
                if (i8 != 0) {
                    i6 |= 3072;
                } else if ((i4 & 3072) == 0) {
                    function12 = function1;
                    i6 |= startRestartGroup.changedInstance(function12) ? 2048 : 1024;
                    i9 = i5 & 16;
                    if (i9 == 0) {
                        i6 |= 24576;
                    } else if ((i4 & 24576) == 0) {
                        i10 = i;
                        i6 |= startRestartGroup.changed(i10) ? 16384 : 8192;
                        i11 = i5 & 32;
                        if (i11 != 0) {
                            i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((196608 & i4) == 0) {
                            i6 |= startRestartGroup.changed(z) ? 131072 : 65536;
                            i12 = i5 & 64;
                            if (i12 == 0) {
                                i6 |= 1572864;
                            } else if ((i4 & 1572864) == 0) {
                                i6 |= startRestartGroup.changed(i2) ? 1048576 : 524288;
                            }
                            i13 = i5 & 128;
                            if (i13 == 0) {
                                i6 |= 12582912;
                            } else if ((i4 & 12582912) == 0) {
                                i14 = i13;
                                i6 |= startRestartGroup.changed(i3) ? 8388608 : 4194304;
                                i15 = i5 & 256;
                                if (i15 != 0) {
                                    i6 |= 100663296;
                                } else if ((i4 & 100663296) == 0) {
                                    i16 = i15;
                                    i6 |= startRestartGroup.changedInstance(map) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                    i17 = i5 & 512;
                                    if (i17 == 0) {
                                        i6 |= 805306368;
                                    } else if ((i4 & 805306368) == 0) {
                                        i18 = i17;
                                        i6 |= startRestartGroup.changedInstance(colorProducer) ? 536870912 : 268435456;
                                        if (!startRestartGroup.shouldExecute((i6 & 306783379) != 306783378, i6 & 1)) {
                                            composer2 = startRestartGroup;
                                            composer2.skipToGroupEnd();
                                            i19 = i2;
                                            map2 = map;
                                            modifier3 = modifier2;
                                            textStyle3 = textStyle2;
                                            function13 = function12;
                                            i20 = i10;
                                            z2 = z;
                                            i21 = i3;
                                            colorProducer2 = colorProducer;
                                        } else {
                                            if (i28 != 0) {
                                                modifier4 = Modifier.INSTANCE;
                                                i22 = i11;
                                            } else {
                                                i22 = i11;
                                                modifier4 = modifier2;
                                            }
                                            TextStyle textStyle4 = i7 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle2;
                                            Function1 function14 = i8 != 0 ? null : function12;
                                            int m7324getClipgIe3tQ8 = i9 != 0 ? TextOverflow.INSTANCE.m7324getClipgIe3tQ8() : i10;
                                            if (i22 != 0) {
                                                i23 = i14;
                                                z3 = true;
                                            } else {
                                                i23 = i14;
                                                z3 = z;
                                            }
                                            if (i12 != 0) {
                                                int i29 = i16;
                                                i25 = Integer.MAX_VALUE;
                                                i24 = i29;
                                            } else {
                                                i24 = i16;
                                                i25 = i2;
                                            }
                                            if (i23 != 0) {
                                                i26 = i18;
                                                i27 = 1;
                                            } else {
                                                i26 = i18;
                                                i27 = i3;
                                            }
                                            Map emptyMap = i24 != 0 ? MapsKt.emptyMap() : map;
                                            ColorProducer colorProducer3 = i26 != 0 ? null : colorProducer;
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1064305212, i6, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:359)");
                                            }
                                            composer2 = startRestartGroup;
                                            m1276BasicTextCL7eQgs(annotatedString2, modifier4, textStyle4, function14, m7324getClipgIe3tQ8, z3, i25, i27, emptyMap, colorProducer3, null, composer2, i6 & 2147483646, 0, 1024);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                            modifier3 = modifier4;
                                            textStyle3 = textStyle4;
                                            function13 = function14;
                                            i20 = m7324getClipgIe3tQ8;
                                            z2 = z3;
                                            i19 = i25;
                                            i21 = i27;
                                            map2 = emptyMap;
                                            colorProducer2 = colorProducer3;
                                        }
                                        endRestartGroup = composer2.endRestartGroup();
                                        if (endRestartGroup != null) {
                                            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda13
                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(Object obj, Object obj2) {
                                                    Unit BasicText_RWo7tUw$lambda$14;
                                                    BasicText_RWo7tUw$lambda$14 = BasicTextKt.BasicText_RWo7tUw$lambda$14(AnnotatedString.this, modifier3, textStyle3, function13, i20, z2, i19, i21, map2, colorProducer2, i4, i5, (Composer) obj, ((Integer) obj2).intValue());
                                                    return BasicText_RWo7tUw$lambda$14;
                                                }
                                            });
                                            return;
                                        }
                                        return;
                                    }
                                    i18 = i17;
                                    if (!startRestartGroup.shouldExecute((i6 & 306783379) != 306783378, i6 & 1)) {
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup != null) {
                                    }
                                }
                                i16 = i15;
                                i17 = i5 & 512;
                                if (i17 == 0) {
                                }
                                i18 = i17;
                                if (!startRestartGroup.shouldExecute((i6 & 306783379) != 306783378, i6 & 1)) {
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                }
                            }
                            i14 = i13;
                            i15 = i5 & 256;
                            if (i15 != 0) {
                            }
                            i16 = i15;
                            i17 = i5 & 512;
                            if (i17 == 0) {
                            }
                            i18 = i17;
                            if (!startRestartGroup.shouldExecute((i6 & 306783379) != 306783378, i6 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        i12 = i5 & 64;
                        if (i12 == 0) {
                        }
                        i13 = i5 & 128;
                        if (i13 == 0) {
                        }
                        i14 = i13;
                        i15 = i5 & 256;
                        if (i15 != 0) {
                        }
                        i16 = i15;
                        i17 = i5 & 512;
                        if (i17 == 0) {
                        }
                        i18 = i17;
                        if (!startRestartGroup.shouldExecute((i6 & 306783379) != 306783378, i6 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    i10 = i;
                    i11 = i5 & 32;
                    if (i11 != 0) {
                    }
                    i12 = i5 & 64;
                    if (i12 == 0) {
                    }
                    i13 = i5 & 128;
                    if (i13 == 0) {
                    }
                    i14 = i13;
                    i15 = i5 & 256;
                    if (i15 != 0) {
                    }
                    i16 = i15;
                    i17 = i5 & 512;
                    if (i17 == 0) {
                    }
                    i18 = i17;
                    if (!startRestartGroup.shouldExecute((i6 & 306783379) != 306783378, i6 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                function12 = function1;
                i9 = i5 & 16;
                if (i9 == 0) {
                }
                i10 = i;
                i11 = i5 & 32;
                if (i11 != 0) {
                }
                i12 = i5 & 64;
                if (i12 == 0) {
                }
                i13 = i5 & 128;
                if (i13 == 0) {
                }
                i14 = i13;
                i15 = i5 & 256;
                if (i15 != 0) {
                }
                i16 = i15;
                i17 = i5 & 512;
                if (i17 == 0) {
                }
                i18 = i17;
                if (!startRestartGroup.shouldExecute((i6 & 306783379) != 306783378, i6 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            textStyle2 = textStyle;
            i8 = i5 & 8;
            if (i8 != 0) {
            }
            function12 = function1;
            i9 = i5 & 16;
            if (i9 == 0) {
            }
            i10 = i;
            i11 = i5 & 32;
            if (i11 != 0) {
            }
            i12 = i5 & 64;
            if (i12 == 0) {
            }
            i13 = i5 & 128;
            if (i13 == 0) {
            }
            i14 = i13;
            i15 = i5 & 256;
            if (i15 != 0) {
            }
            i16 = i15;
            i17 = i5 & 512;
            if (i17 == 0) {
            }
            i18 = i17;
            if (!startRestartGroup.shouldExecute((i6 & 306783379) != 306783378, i6 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i7 = i5 & 4;
        if (i7 == 0) {
        }
        textStyle2 = textStyle;
        i8 = i5 & 8;
        if (i8 != 0) {
        }
        function12 = function1;
        i9 = i5 & 16;
        if (i9 == 0) {
        }
        i10 = i;
        i11 = i5 & 32;
        if (i11 != 0) {
        }
        i12 = i5 & 64;
        if (i12 == 0) {
        }
        i13 = i5 & 128;
        if (i13 == 0) {
        }
        i14 = i13;
        i15 = i5 & 256;
        if (i15 != 0) {
        }
        i16 = i15;
        i17 = i5 & 512;
        if (i17 == 0) {
        }
        i18 = i17;
        if (!startRestartGroup.shouldExecute((i6 & 306783379) != 306783378, i6 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x004e  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: BasicText-BpD7jsM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m1275BasicTextBpD7jsM(final String str, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, Composer composer, final int i3, final int i4) {
        String str2;
        int i5;
        Modifier modifier2;
        int i6;
        TextStyle textStyle2;
        int i7;
        int i8;
        int i9;
        boolean z2;
        int i10;
        Composer composer2;
        final int i11;
        final int i12;
        final Modifier modifier3;
        final TextStyle textStyle3;
        final Function1 function12;
        ScopeUpdateScope endRestartGroup;
        int i13;
        TextStyle textStyle4;
        int i14;
        int i15;
        Composer startRestartGroup = composer.startRestartGroup(1022429478);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicText)N(text,modifier,style,onTextLayout,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines)385@18278L235:BasicText.kt#423gt5");
        if ((i4 & 1) != 0) {
            i5 = i3 | 6;
            str2 = str;
        } else {
            str2 = str;
            if ((i3 & 6) == 0) {
                i5 = (startRestartGroup.changed(str2) ? 4 : 2) | i3;
            } else {
                i5 = i3;
            }
        }
        int i16 = i4 & 2;
        if (i16 != 0) {
            i5 |= 48;
        } else if ((i3 & 48) == 0) {
            modifier2 = modifier;
            i5 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i6 = i4 & 4;
            if (i6 == 0) {
                i5 |= 384;
            } else if ((i3 & 384) == 0) {
                textStyle2 = textStyle;
                i5 |= startRestartGroup.changed(textStyle2) ? 256 : 128;
                i7 = i4 & 8;
                if (i7 != 0) {
                    i5 |= 3072;
                } else if ((i3 & 3072) == 0) {
                    i5 |= startRestartGroup.changedInstance(function1) ? 2048 : 1024;
                    i8 = i4 & 16;
                    if (i8 == 0) {
                        i5 |= 24576;
                    } else if ((i3 & 24576) == 0) {
                        i5 |= startRestartGroup.changed(i) ? 16384 : 8192;
                        i9 = i4 & 32;
                        if (i9 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((196608 & i3) == 0) {
                            z2 = z;
                            i5 |= startRestartGroup.changed(z2) ? 131072 : 65536;
                            i10 = i4 & 64;
                            if (i10 == 0) {
                                i5 |= 1572864;
                            } else if ((i3 & 1572864) == 0) {
                                i5 |= startRestartGroup.changed(i2) ? 1048576 : 524288;
                            }
                            if (startRestartGroup.shouldExecute((i5 & 599187) == 599186, i5 & 1)) {
                                composer2 = startRestartGroup;
                                composer2.skipToGroupEnd();
                                i11 = i;
                                i12 = i2;
                                modifier3 = modifier2;
                                textStyle3 = textStyle2;
                                function12 = function1;
                            } else {
                                Modifier modifier4 = i16 != 0 ? Modifier.INSTANCE : modifier2;
                                if (i6 != 0) {
                                    textStyle4 = TextStyle.INSTANCE.getDefault();
                                    i13 = i8;
                                } else {
                                    i13 = i8;
                                    textStyle4 = textStyle2;
                                }
                                Function1 function13 = i7 != 0 ? null : function1;
                                if (i13 != 0) {
                                    i15 = TextOverflow.INSTANCE.m7324getClipgIe3tQ8();
                                    i14 = i9;
                                } else {
                                    i14 = i9;
                                    i15 = i;
                                }
                                if (i14 != 0) {
                                    z2 = true;
                                }
                                int i17 = i10 != 0 ? Integer.MAX_VALUE : i2;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1022429478, i5, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:384)");
                                }
                                composer2 = startRestartGroup;
                                m1278BasicTextRWo7tUw(str2, modifier4, textStyle4, (Function1<? super TextLayoutResult, Unit>) function13, i15, z2, i17, 1, (ColorProducer) null, (TextAutoSize) null, composer2, (i5 & 14) | 12582912 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | (i5 & 3670016), 768);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                textStyle3 = textStyle4;
                                function12 = function13;
                                i11 = i15;
                                i12 = i17;
                            }
                            final boolean z3 = z2;
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda11
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit BasicText_BpD7jsM$lambda$15;
                                        BasicText_BpD7jsM$lambda$15 = BasicTextKt.BasicText_BpD7jsM$lambda$15(str, modifier3, textStyle3, function12, i11, z3, i12, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                        return BasicText_BpD7jsM$lambda$15;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        z2 = z;
                        i10 = i4 & 64;
                        if (i10 == 0) {
                        }
                        if (startRestartGroup.shouldExecute((i5 & 599187) == 599186, i5 & 1)) {
                        }
                        final boolean z32 = z2;
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i9 = i4 & 32;
                    if (i9 != 0) {
                    }
                    z2 = z;
                    i10 = i4 & 64;
                    if (i10 == 0) {
                    }
                    if (startRestartGroup.shouldExecute((i5 & 599187) == 599186, i5 & 1)) {
                    }
                    final boolean z322 = z2;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                i8 = i4 & 16;
                if (i8 == 0) {
                }
                i9 = i4 & 32;
                if (i9 != 0) {
                }
                z2 = z;
                i10 = i4 & 64;
                if (i10 == 0) {
                }
                if (startRestartGroup.shouldExecute((i5 & 599187) == 599186, i5 & 1)) {
                }
                final boolean z3222 = z2;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            textStyle2 = textStyle;
            i7 = i4 & 8;
            if (i7 != 0) {
            }
            i8 = i4 & 16;
            if (i8 == 0) {
            }
            i9 = i4 & 32;
            if (i9 != 0) {
            }
            z2 = z;
            i10 = i4 & 64;
            if (i10 == 0) {
            }
            if (startRestartGroup.shouldExecute((i5 & 599187) == 599186, i5 & 1)) {
            }
            final boolean z32222 = z2;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i6 = i4 & 4;
        if (i6 == 0) {
        }
        textStyle2 = textStyle;
        i7 = i4 & 8;
        if (i7 != 0) {
        }
        i8 = i4 & 16;
        if (i8 == 0) {
        }
        i9 = i4 & 32;
        if (i9 != 0) {
        }
        z2 = z;
        i10 = i4 & 64;
        if (i10 == 0) {
        }
        if (startRestartGroup.shouldExecute((i5 & 599187) == 599186, i5 & 1)) {
        }
        final boolean z322222 = z2;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0088  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: BasicText-4YKlhWE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m1273BasicText4YKlhWE(final AnnotatedString annotatedString, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, Map map, Composer composer, final int i3, final int i4) {
        AnnotatedString annotatedString2;
        int i5;
        Modifier modifier2;
        int i6;
        TextStyle textStyle2;
        int i7;
        Function1 function12;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        Composer composer2;
        final int i13;
        final int i14;
        final Modifier modifier3;
        final TextStyle textStyle3;
        final Function1 function13;
        final boolean z2;
        final Map map2;
        ScopeUpdateScope endRestartGroup;
        int i15;
        Modifier modifier4;
        int i16;
        Function1 function14;
        Composer startRestartGroup = composer.startRestartGroup(-648605928);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicText)N(text,modifier,style,onTextLayout,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines,inlineContent)409@18970L274:BasicText.kt#423gt5");
        if ((i4 & 1) != 0) {
            i5 = i3 | 6;
            annotatedString2 = annotatedString;
        } else {
            annotatedString2 = annotatedString;
            if ((i3 & 6) == 0) {
                i5 = (startRestartGroup.changed(annotatedString2) ? 4 : 2) | i3;
            } else {
                i5 = i3;
            }
        }
        int i17 = i4 & 2;
        if (i17 != 0) {
            i5 |= 48;
        } else if ((i3 & 48) == 0) {
            modifier2 = modifier;
            i5 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i6 = i4 & 4;
            if (i6 == 0) {
                i5 |= 384;
            } else if ((i3 & 384) == 0) {
                textStyle2 = textStyle;
                i5 |= startRestartGroup.changed(textStyle2) ? 256 : 128;
                i7 = i4 & 8;
                if (i7 != 0) {
                    i5 |= 3072;
                } else if ((i3 & 3072) == 0) {
                    function12 = function1;
                    i5 |= startRestartGroup.changedInstance(function12) ? 2048 : 1024;
                    i8 = i4 & 16;
                    if (i8 == 0) {
                        i5 |= 24576;
                    } else if ((i3 & 24576) == 0) {
                        i5 |= startRestartGroup.changed(i) ? 16384 : 8192;
                        i9 = i4 & 32;
                        if (i9 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((196608 & i3) == 0) {
                            i5 |= startRestartGroup.changed(z) ? 131072 : 65536;
                            i10 = i4 & 64;
                            if (i10 == 0) {
                                i5 |= 1572864;
                            } else if ((i3 & 1572864) == 0) {
                                i5 |= startRestartGroup.changed(i2) ? 1048576 : 524288;
                            }
                            i11 = i4 & 128;
                            if (i11 == 0) {
                                i5 |= 12582912;
                            } else if ((i3 & 12582912) == 0) {
                                i12 = i11;
                                i5 |= startRestartGroup.changedInstance(map) ? 8388608 : 4194304;
                                if (!startRestartGroup.shouldExecute((i5 & 4793491) != 4793490, i5 & 1)) {
                                    composer2 = startRestartGroup;
                                    composer2.skipToGroupEnd();
                                    i13 = i;
                                    i14 = i2;
                                    modifier3 = modifier2;
                                    textStyle3 = textStyle2;
                                    function13 = function12;
                                    z2 = z;
                                    map2 = map;
                                } else {
                                    if (i17 != 0) {
                                        modifier4 = Modifier.INSTANCE;
                                        i15 = i8;
                                    } else {
                                        i15 = i8;
                                        modifier4 = modifier2;
                                    }
                                    TextStyle textStyle4 = i6 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle2;
                                    if (i7 != 0) {
                                        function14 = null;
                                        i16 = i9;
                                    } else {
                                        i16 = i9;
                                        function14 = function12;
                                    }
                                    int m7324getClipgIe3tQ8 = i15 != 0 ? TextOverflow.INSTANCE.m7324getClipgIe3tQ8() : i;
                                    boolean z3 = i16 != 0 ? true : z;
                                    int i18 = i10 != 0 ? Integer.MAX_VALUE : i2;
                                    Map emptyMap = i12 != 0 ? MapsKt.emptyMap() : map;
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-648605928, i5, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:408)");
                                    }
                                    composer2 = startRestartGroup;
                                    m1276BasicTextCL7eQgs(annotatedString2, modifier4, textStyle4, function14, m7324getClipgIe3tQ8, z3, i18, 1, emptyMap, null, null, composer2, (i5 & 14) | 12582912 | (i5 & 112) | (i5 & 896) | (i5 & 7168) | (57344 & i5) | (458752 & i5) | (3670016 & i5) | ((i5 << 3) & 234881024), 0, 1536);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    modifier3 = modifier4;
                                    textStyle3 = textStyle4;
                                    function13 = function14;
                                    i13 = m7324getClipgIe3tQ8;
                                    z2 = z3;
                                    i14 = i18;
                                    map2 = emptyMap;
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                    endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda12
                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(Object obj, Object obj2) {
                                            Unit BasicText_4YKlhWE$lambda$16;
                                            BasicText_4YKlhWE$lambda$16 = BasicTextKt.BasicText_4YKlhWE$lambda$16(AnnotatedString.this, modifier3, textStyle3, function13, i13, z2, i14, map2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                            return BasicText_4YKlhWE$lambda$16;
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            i12 = i11;
                            if (!startRestartGroup.shouldExecute((i5 & 4793491) != 4793490, i5 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        i10 = i4 & 64;
                        if (i10 == 0) {
                        }
                        i11 = i4 & 128;
                        if (i11 == 0) {
                        }
                        i12 = i11;
                        if (!startRestartGroup.shouldExecute((i5 & 4793491) != 4793490, i5 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    i9 = i4 & 32;
                    if (i9 != 0) {
                    }
                    i10 = i4 & 64;
                    if (i10 == 0) {
                    }
                    i11 = i4 & 128;
                    if (i11 == 0) {
                    }
                    i12 = i11;
                    if (!startRestartGroup.shouldExecute((i5 & 4793491) != 4793490, i5 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                function12 = function1;
                i8 = i4 & 16;
                if (i8 == 0) {
                }
                i9 = i4 & 32;
                if (i9 != 0) {
                }
                i10 = i4 & 64;
                if (i10 == 0) {
                }
                i11 = i4 & 128;
                if (i11 == 0) {
                }
                i12 = i11;
                if (!startRestartGroup.shouldExecute((i5 & 4793491) != 4793490, i5 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            textStyle2 = textStyle;
            i7 = i4 & 8;
            if (i7 != 0) {
            }
            function12 = function1;
            i8 = i4 & 16;
            if (i8 == 0) {
            }
            i9 = i4 & 32;
            if (i9 != 0) {
            }
            i10 = i4 & 64;
            if (i10 == 0) {
            }
            i11 = i4 & 128;
            if (i11 == 0) {
            }
            i12 = i11;
            if (!startRestartGroup.shouldExecute((i5 & 4793491) != 4793490, i5 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i6 = i4 & 4;
        if (i6 == 0) {
        }
        textStyle2 = textStyle;
        i7 = i4 & 8;
        if (i7 != 0) {
        }
        function12 = function1;
        i8 = i4 & 16;
        if (i8 == 0) {
        }
        i9 = i4 & 32;
        if (i9 != 0) {
        }
        i10 = i4 & 64;
        if (i10 == 0) {
        }
        i11 = i4 & 128;
        if (i11 == 0) {
        }
        i12 = i11;
        if (!startRestartGroup.shouldExecute((i5 & 4793491) != 4793490, i5 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0088  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compat")
    /* renamed from: BasicText-4YKlhWE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m1274BasicText4YKlhWE(final String str, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, Composer composer, final int i4, final int i5) {
        String str2;
        int i6;
        Modifier modifier2;
        int i7;
        TextStyle textStyle2;
        int i8;
        Function1 function12;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        Composer composer2;
        final int i14;
        final int i15;
        final Modifier modifier3;
        final TextStyle textStyle3;
        final Function1 function13;
        final boolean z2;
        final int i16;
        ScopeUpdateScope endRestartGroup;
        int i17;
        Modifier modifier4;
        int i18;
        Function1 function14;
        Composer startRestartGroup = composer.startRestartGroup(1542716361);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicText)N(text,modifier,style,onTextLayout,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines,minLines)433@19643L86:BasicText.kt#423gt5");
        if ((i5 & 1) != 0) {
            i6 = i4 | 6;
            str2 = str;
        } else {
            str2 = str;
            if ((i4 & 6) == 0) {
                i6 = (startRestartGroup.changed(str2) ? 4 : 2) | i4;
            } else {
                i6 = i4;
            }
        }
        int i19 = i5 & 2;
        if (i19 != 0) {
            i6 |= 48;
        } else if ((i4 & 48) == 0) {
            modifier2 = modifier;
            i6 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i7 = i5 & 4;
            if (i7 == 0) {
                i6 |= 384;
            } else if ((i4 & 384) == 0) {
                textStyle2 = textStyle;
                i6 |= startRestartGroup.changed(textStyle2) ? 256 : 128;
                i8 = i5 & 8;
                if (i8 != 0) {
                    i6 |= 3072;
                } else if ((i4 & 3072) == 0) {
                    function12 = function1;
                    i6 |= startRestartGroup.changedInstance(function12) ? 2048 : 1024;
                    i9 = i5 & 16;
                    if (i9 == 0) {
                        i6 |= 24576;
                    } else if ((i4 & 24576) == 0) {
                        i6 |= startRestartGroup.changed(i) ? 16384 : 8192;
                        i10 = i5 & 32;
                        if (i10 != 0) {
                            i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((196608 & i4) == 0) {
                            i6 |= startRestartGroup.changed(z) ? 131072 : 65536;
                            i11 = i5 & 64;
                            if (i11 == 0) {
                                i6 |= 1572864;
                            } else if ((i4 & 1572864) == 0) {
                                i6 |= startRestartGroup.changed(i2) ? 1048576 : 524288;
                            }
                            i12 = i5 & 128;
                            if (i12 == 0) {
                                i6 |= 12582912;
                            } else if ((i4 & 12582912) == 0) {
                                i13 = i12;
                                i6 |= startRestartGroup.changed(i3) ? 8388608 : 4194304;
                                if (!startRestartGroup.shouldExecute((i6 & 4793491) != 4793490, i6 & 1)) {
                                    composer2 = startRestartGroup;
                                    composer2.skipToGroupEnd();
                                    i14 = i;
                                    i15 = i2;
                                    modifier3 = modifier2;
                                    textStyle3 = textStyle2;
                                    function13 = function12;
                                    z2 = z;
                                    i16 = i3;
                                } else {
                                    if (i19 != 0) {
                                        modifier4 = Modifier.INSTANCE;
                                        i17 = i9;
                                    } else {
                                        i17 = i9;
                                        modifier4 = modifier2;
                                    }
                                    TextStyle textStyle4 = i7 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle2;
                                    if (i8 != 0) {
                                        function14 = null;
                                        i18 = i10;
                                    } else {
                                        i18 = i10;
                                        function14 = function12;
                                    }
                                    int m7324getClipgIe3tQ8 = i17 != 0 ? TextOverflow.INSTANCE.m7324getClipgIe3tQ8() : i;
                                    boolean z3 = i18 != 0 ? true : z;
                                    int i20 = i11 != 0 ? Integer.MAX_VALUE : i2;
                                    int i21 = i13 != 0 ? 1 : i3;
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1542716361, i6, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:433)");
                                    }
                                    composer2 = startRestartGroup;
                                    m1278BasicTextRWo7tUw(str2, modifier4, textStyle4, (Function1<? super TextLayoutResult, Unit>) function14, m7324getClipgIe3tQ8, z3, i20, i21, (ColorProducer) null, (TextAutoSize) null, composer2, i6 & 33554430, 768);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    modifier3 = modifier4;
                                    textStyle3 = textStyle4;
                                    function13 = function14;
                                    i14 = m7324getClipgIe3tQ8;
                                    z2 = z3;
                                    i15 = i20;
                                    i16 = i21;
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                    endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda10
                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(Object obj, Object obj2) {
                                            Unit BasicText_4YKlhWE$lambda$17;
                                            BasicText_4YKlhWE$lambda$17 = BasicTextKt.BasicText_4YKlhWE$lambda$17(str, modifier3, textStyle3, function13, i14, z2, i15, i16, i4, i5, (Composer) obj, ((Integer) obj2).intValue());
                                            return BasicText_4YKlhWE$lambda$17;
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            i13 = i12;
                            if (!startRestartGroup.shouldExecute((i6 & 4793491) != 4793490, i6 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        i11 = i5 & 64;
                        if (i11 == 0) {
                        }
                        i12 = i5 & 128;
                        if (i12 == 0) {
                        }
                        i13 = i12;
                        if (!startRestartGroup.shouldExecute((i6 & 4793491) != 4793490, i6 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    i10 = i5 & 32;
                    if (i10 != 0) {
                    }
                    i11 = i5 & 64;
                    if (i11 == 0) {
                    }
                    i12 = i5 & 128;
                    if (i12 == 0) {
                    }
                    i13 = i12;
                    if (!startRestartGroup.shouldExecute((i6 & 4793491) != 4793490, i6 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                function12 = function1;
                i9 = i5 & 16;
                if (i9 == 0) {
                }
                i10 = i5 & 32;
                if (i10 != 0) {
                }
                i11 = i5 & 64;
                if (i11 == 0) {
                }
                i12 = i5 & 128;
                if (i12 == 0) {
                }
                i13 = i12;
                if (!startRestartGroup.shouldExecute((i6 & 4793491) != 4793490, i6 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            textStyle2 = textStyle;
            i8 = i5 & 8;
            if (i8 != 0) {
            }
            function12 = function1;
            i9 = i5 & 16;
            if (i9 == 0) {
            }
            i10 = i5 & 32;
            if (i10 != 0) {
            }
            i11 = i5 & 64;
            if (i11 == 0) {
            }
            i12 = i5 & 128;
            if (i12 == 0) {
            }
            i13 = i12;
            if (!startRestartGroup.shouldExecute((i6 & 4793491) != 4793490, i6 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i7 = i5 & 4;
        if (i7 == 0) {
        }
        textStyle2 = textStyle;
        i8 = i5 & 8;
        if (i8 != 0) {
        }
        function12 = function1;
        i9 = i5 & 16;
        if (i9 == 0) {
        }
        i10 = i5 & 32;
        if (i10 != 0) {
        }
        i11 = i5 & 64;
        if (i11 == 0) {
        }
        i12 = i5 & 128;
        if (i12 == 0) {
        }
        i13 = i12;
        if (!startRestartGroup.shouldExecute((i6 & 4793491) != 4793490, i6 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00c5  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compat")
    /* renamed from: BasicText-VhcvRP8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m1279BasicTextVhcvRP8(final AnnotatedString annotatedString, Modifier modifier, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, Map map, Composer composer, final int i4, final int i5) {
        AnnotatedString annotatedString2;
        int i6;
        Modifier modifier2;
        int i7;
        TextStyle textStyle2;
        int i8;
        Function1 function12;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        Composer composer2;
        final int i16;
        final int i17;
        final Map map2;
        final Modifier modifier3;
        final TextStyle textStyle3;
        final Function1 function13;
        final boolean z2;
        final int i18;
        ScopeUpdateScope endRestartGroup;
        int i19;
        TextStyle textStyle4;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        Composer startRestartGroup = composer.startRestartGroup(851408699);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicText)N(text,modifier,style,onTextLayout,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines,minLines,inlineContent)448@20200L281:BasicText.kt#423gt5");
        if ((i5 & 1) != 0) {
            i6 = i4 | 6;
            annotatedString2 = annotatedString;
        } else {
            annotatedString2 = annotatedString;
            if ((i4 & 6) == 0) {
                i6 = (startRestartGroup.changed(annotatedString2) ? 4 : 2) | i4;
            } else {
                i6 = i4;
            }
        }
        int i26 = i5 & 2;
        if (i26 != 0) {
            i6 |= 48;
        } else if ((i4 & 48) == 0) {
            modifier2 = modifier;
            i6 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i7 = i5 & 4;
            if (i7 == 0) {
                i6 |= 384;
            } else if ((i4 & 384) == 0) {
                textStyle2 = textStyle;
                i6 |= startRestartGroup.changed(textStyle2) ? 256 : 128;
                i8 = i5 & 8;
                if (i8 != 0) {
                    i6 |= 3072;
                } else if ((i4 & 3072) == 0) {
                    function12 = function1;
                    i6 |= startRestartGroup.changedInstance(function12) ? 2048 : 1024;
                    i9 = i5 & 16;
                    if (i9 == 0) {
                        i6 |= 24576;
                    } else if ((i4 & 24576) == 0) {
                        i6 |= startRestartGroup.changed(i) ? 16384 : 8192;
                        i10 = i5 & 32;
                        if (i10 != 0) {
                            i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((196608 & i4) == 0) {
                            i6 |= startRestartGroup.changed(z) ? 131072 : 65536;
                            i11 = i5 & 64;
                            if (i11 == 0) {
                                i6 |= 1572864;
                            } else if ((i4 & 1572864) == 0) {
                                i6 |= startRestartGroup.changed(i2) ? 1048576 : 524288;
                            }
                            i12 = i5 & 128;
                            if (i12 == 0) {
                                i6 |= 12582912;
                            } else if ((i4 & 12582912) == 0) {
                                i13 = i12;
                                i6 |= startRestartGroup.changed(i3) ? 8388608 : 4194304;
                                i14 = i5 & 256;
                                if (i14 != 0) {
                                    i6 |= 100663296;
                                } else if ((i4 & 100663296) == 0) {
                                    i15 = i14;
                                    i6 |= startRestartGroup.changedInstance(map) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                    if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
                                        composer2 = startRestartGroup;
                                        composer2.skipToGroupEnd();
                                        i16 = i;
                                        i17 = i2;
                                        map2 = map;
                                        modifier3 = modifier2;
                                        textStyle3 = textStyle2;
                                        function13 = function12;
                                        z2 = z;
                                        i18 = i3;
                                    } else {
                                        Modifier modifier4 = i26 != 0 ? Modifier.INSTANCE : modifier2;
                                        if (i7 != 0) {
                                            textStyle4 = TextStyle.INSTANCE.getDefault();
                                            i19 = i10;
                                        } else {
                                            i19 = i10;
                                            textStyle4 = textStyle2;
                                        }
                                        Function1 function14 = i8 != 0 ? null : function12;
                                        if (i9 != 0) {
                                            i21 = TextOverflow.INSTANCE.m7324getClipgIe3tQ8();
                                            i20 = i11;
                                        } else {
                                            i20 = i11;
                                            i21 = i;
                                        }
                                        boolean z3 = i19 != 0 ? true : z;
                                        if (i20 != 0) {
                                            int i27 = i13;
                                            i23 = Integer.MAX_VALUE;
                                            i22 = i27;
                                        } else {
                                            i22 = i13;
                                            i23 = i2;
                                        }
                                        if (i22 != 0) {
                                            i24 = i15;
                                            i25 = 1;
                                        } else {
                                            i24 = i15;
                                            i25 = i3;
                                        }
                                        Map emptyMap = i24 != 0 ? MapsKt.emptyMap() : map;
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(851408699, i6, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:448)");
                                        }
                                        composer2 = startRestartGroup;
                                        m1276BasicTextCL7eQgs(annotatedString2, modifier4, textStyle4, function14, i21, z3, i23, i25, emptyMap, null, null, composer2, i6 & 268435454, 0, 1536);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        modifier3 = modifier4;
                                        textStyle3 = textStyle4;
                                        function13 = function14;
                                        i16 = i21;
                                        z2 = z3;
                                        i17 = i23;
                                        i18 = i25;
                                        map2 = emptyMap;
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup == null) {
                                        endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda14
                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(Object obj, Object obj2) {
                                                Unit BasicText_VhcvRP8$lambda$18;
                                                BasicText_VhcvRP8$lambda$18 = BasicTextKt.BasicText_VhcvRP8$lambda$18(AnnotatedString.this, modifier3, textStyle3, function13, i16, z2, i17, i18, map2, i4, i5, (Composer) obj, ((Integer) obj2).intValue());
                                                return BasicText_VhcvRP8$lambda$18;
                                            }
                                        });
                                        return;
                                    }
                                    return;
                                }
                                i15 = i14;
                                if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup == null) {
                                }
                            }
                            i13 = i12;
                            i14 = i5 & 256;
                            if (i14 != 0) {
                            }
                            i15 = i14;
                            if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                            }
                        }
                        i11 = i5 & 64;
                        if (i11 == 0) {
                        }
                        i12 = i5 & 128;
                        if (i12 == 0) {
                        }
                        i13 = i12;
                        i14 = i5 & 256;
                        if (i14 != 0) {
                        }
                        i15 = i14;
                        if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i10 = i5 & 32;
                    if (i10 != 0) {
                    }
                    i11 = i5 & 64;
                    if (i11 == 0) {
                    }
                    i12 = i5 & 128;
                    if (i12 == 0) {
                    }
                    i13 = i12;
                    i14 = i5 & 256;
                    if (i14 != 0) {
                    }
                    i15 = i14;
                    if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                function12 = function1;
                i9 = i5 & 16;
                if (i9 == 0) {
                }
                i10 = i5 & 32;
                if (i10 != 0) {
                }
                i11 = i5 & 64;
                if (i11 == 0) {
                }
                i12 = i5 & 128;
                if (i12 == 0) {
                }
                i13 = i12;
                i14 = i5 & 256;
                if (i14 != 0) {
                }
                i15 = i14;
                if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            textStyle2 = textStyle;
            i8 = i5 & 8;
            if (i8 != 0) {
            }
            function12 = function1;
            i9 = i5 & 16;
            if (i9 == 0) {
            }
            i10 = i5 & 32;
            if (i10 != 0) {
            }
            i11 = i5 & 64;
            if (i11 == 0) {
            }
            i12 = i5 & 128;
            if (i12 == 0) {
            }
            i13 = i12;
            i14 = i5 & 256;
            if (i14 != 0) {
            }
            i15 = i14;
            if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i7 = i5 & 4;
        if (i7 == 0) {
        }
        textStyle2 = textStyle;
        i8 = i5 & 8;
        if (i8 != 0) {
        }
        function12 = function1;
        i9 = i5 & 16;
        if (i9 == 0) {
        }
        i10 = i5 & 32;
        if (i10 != 0) {
        }
        i11 = i5 & 64;
        if (i11 == 0) {
        }
        i12 = i5 & 128;
        if (i12 == 0) {
        }
        i13 = i12;
        i14 = i5 & 256;
        if (i14 != 0) {
        }
        i15 = i14;
        if (startRestartGroup.shouldExecute((i6 & 38347923) == 38347922, i6 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    private static final Saver<Long, Long> selectionIdSaver(final SelectionRegistrar selectionRegistrar) {
        return SaverKt.Saver(new Function2() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Long selectionIdSaver$lambda$19;
                selectionIdSaver$lambda$19 = BasicTextKt.selectionIdSaver$lambda$19(SelectionRegistrar.this, (SaverScope) obj, ((Long) obj2).longValue());
                return selectionIdSaver$lambda$19;
            }
        }, new Function1() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Long valueOf;
                valueOf = Long.valueOf(((Long) obj).longValue());
                return valueOf;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long selectionIdSaver$lambda$19(SelectionRegistrar selectionRegistrar, SaverScope saverScope, long j) {
        if (SelectionRegistrarKt.hasSelection(selectionRegistrar, j)) {
            return Long.valueOf(j);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Pair<Placeable, Function0<IntOffset>>> measureWithTextRangeMeasureConstraints(List<? extends Measurable> list, Function0<Boolean> function0) {
        if (!function0.invoke().booleanValue()) {
            return null;
        }
        TextRangeLayoutMeasureScope textRangeLayoutMeasureScope = new TextRangeLayoutMeasureScope();
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Measurable measurable = list.get(i);
            Object parentData = measurable.getParentData();
            Intrinsics.checkNotNull(parentData, "null cannot be cast to non-null type androidx.compose.foundation.text.TextRangeLayoutModifier");
            TextRangeLayoutMeasureResult measure = ((TextRangeLayoutModifier) parentData).getMeasurePolicy().measure(textRangeLayoutMeasureScope);
            arrayList.add(new Pair(measurable.mo6055measureBRTryo0(Constraints.INSTANCE.m7351fitPrioritizingWidthZbe2FdA(measure.getWidth(), measure.getWidth(), measure.getHeight(), measure.getHeight())), measure.getPlace()));
        }
        return arrayList;
    }

    /* renamed from: textModifier-CL7eQgs, reason: not valid java name */
    private static final Modifier m1282textModifierCL7eQgs(Modifier modifier, AnnotatedString annotatedString, TextStyle textStyle, Function1<? super TextLayoutResult, Unit> function1, int i, boolean z, int i2, int i3, FontFamily.Resolver resolver, List<AnnotatedString.Range<Placeholder>> list, Function1<? super List<Rect>, Unit> function12, SelectionController selectionController, ColorProducer colorProducer, Function1<? super TextAnnotatedStringNode.TextSubstitutionValue, Unit> function13, TextAutoSize textAutoSize) {
        if (selectionController == null) {
            return modifier.then(Modifier.INSTANCE).then(new TextAnnotatedStringElement(annotatedString, textStyle, resolver, function1, i, z, i2, i3, list, function12, null, colorProducer, textAutoSize, function13, null));
        }
        return modifier.then(selectionController.getModifier()).then(new SelectableTextAnnotatedStringElement(annotatedString, textStyle, resolver, function1, i, z, i2, i3, list, function12, selectionController, colorProducer, textAutoSize, null));
    }

    /* JADX WARN: Code restructure failed: missing block: B:97:0x0361, code lost:
    
        if (r1 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L232;
     */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0594  */
    /* JADX WARN: Removed duplicated region for block: B:138:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0589  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01f4  */
    /* renamed from: LayoutWithLinksAndInlineContent-11Od_4g, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void m1281LayoutWithLinksAndInlineContent11Od_4g(final Modifier modifier, final AnnotatedString annotatedString, final Function1<? super TextLayoutResult, Unit> function1, final boolean z, Map<String, InlineTextContent> map, final TextStyle textStyle, final int i, final boolean z2, final int i2, final int i3, final FontFamily.Resolver resolver, final SelectionController selectionController, final ColorProducer colorProducer, final Function1<? super TextAnnotatedStringNode.TextSubstitutionValue, Unit> function12, final TextAutoSize textAutoSize, Composer composer, final int i4, final int i5, final int i6) {
        Modifier modifier2;
        int i7;
        TextStyle textStyle2;
        boolean z3;
        int i8;
        int i9;
        final Map<String, InlineTextContent> map2;
        ScopeUpdateScope endRestartGroup;
        final TextLinkScope textLinkScope;
        int i10;
        Function0 function0;
        Pair<List<AnnotatedString.Range<Placeholder>>, List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>>> pair;
        List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>> list;
        Function1 function13;
        final MutableState mutableState;
        int i11;
        TextMeasurePolicy textMeasurePolicy;
        Composer startRestartGroup = composer.startRestartGroup(-2118572703);
        ComposerKt.sourceInformation(startRestartGroup, "C(LayoutWithLinksAndInlineContent)N(modifier,text,onTextLayout,hasInlineContent,inlineContent,style,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines,minLines,fontFamilyResolver,selectionController,color,onShowTranslation,autoSize)678@28236L162,694@28726L117,685@28404L1429:BasicText.kt#423gt5");
        if ((i6 & 1) != 0) {
            i7 = i4 | 6;
            modifier2 = modifier;
        } else {
            modifier2 = modifier;
            if ((i4 & 6) == 0) {
                i7 = (startRestartGroup.changed(modifier2) ? 4 : 2) | i4;
            } else {
                i7 = i4;
            }
        }
        if ((i6 & 2) != 0) {
            i7 |= 48;
        } else if ((i4 & 48) == 0) {
            i7 |= startRestartGroup.changed(annotatedString) ? 32 : 16;
        }
        if ((i6 & 4) != 0) {
            i7 |= 384;
        } else if ((i4 & 384) == 0) {
            i7 |= startRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i6 & 8) != 0) {
            i7 |= 3072;
        } else if ((i4 & 3072) == 0) {
            i7 |= startRestartGroup.changed(z) ? 2048 : 1024;
        }
        int i12 = i6 & 16;
        if (i12 != 0) {
            i7 |= 24576;
        } else if ((i4 & 24576) == 0) {
            i7 |= startRestartGroup.changedInstance(map) ? 16384 : 8192;
            if ((i6 & 32) == 0) {
                i7 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                textStyle2 = textStyle;
            } else {
                textStyle2 = textStyle;
                if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    i7 |= startRestartGroup.changed(textStyle2) ? 131072 : 65536;
                }
            }
            if ((i6 & 64) == 0) {
                i7 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                i7 |= startRestartGroup.changed(i) ? 1048576 : 524288;
            }
            if ((i6 & 128) == 0) {
                i7 |= 12582912;
            } else if ((12582912 & i4) == 0) {
                z3 = z2;
                i7 |= startRestartGroup.changed(z3) ? 8388608 : 4194304;
                if ((i6 & 256) != 0) {
                    i7 |= 100663296;
                } else if ((100663296 & i4) == 0) {
                    i7 |= startRestartGroup.changed(i2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                    if ((i6 & 512) == 0) {
                        i7 |= 805306368;
                    } else if ((805306368 & i4) == 0) {
                        i7 |= startRestartGroup.changed(i3) ? 536870912 : 268435456;
                        if ((i6 & 1024) != 0) {
                            i8 = i5 | 6;
                        } else if ((i5 & 6) == 0) {
                            i8 = i5 | (startRestartGroup.changedInstance(resolver) ? 4 : 2);
                        } else {
                            i8 = i5;
                        }
                        if ((i6 & 2048) != 0) {
                            i8 |= 48;
                        } else if ((i5 & 48) == 0) {
                            i8 |= startRestartGroup.changedInstance(selectionController) ? 32 : 16;
                        }
                        i9 = i8;
                        if ((i6 & 4096) != 0) {
                            i9 |= 384;
                        } else if ((i5 & 384) == 0) {
                            i9 |= startRestartGroup.changedInstance(colorProducer) ? 256 : 128;
                            if ((i6 & 8192) == 0) {
                                i9 |= 3072;
                            } else if ((i5 & 3072) == 0) {
                                i9 |= startRestartGroup.changedInstance(function12) ? 2048 : 1024;
                                if ((i6 & 16384) != 0) {
                                    i9 |= 24576;
                                } else if ((i5 & 24576) == 0) {
                                    i9 |= (32768 & i5) == 0 ? startRestartGroup.changed(textAutoSize) : startRestartGroup.changedInstance(textAutoSize) ? 16384 : 8192;
                                }
                                if (!startRestartGroup.shouldExecute((306783379 & i7) == 306783378 || (i9 & 9363) != 9362, i7 & 1)) {
                                    startRestartGroup.skipToGroupEnd();
                                    map2 = map;
                                } else {
                                    Map<String, InlineTextContent> emptyMap = i12 != 0 ? MapsKt.emptyMap() : map;
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2118572703, i7, i9, "androidx.compose.foundation.text.LayoutWithLinksAndInlineContent (BasicText.kt:646)");
                                    }
                                    if (TextAnnotatedStringNodeKt.hasLinks(annotatedString)) {
                                        startRestartGroup.startReplaceGroup(145661411);
                                        ComposerKt.sourceInformation(startRestartGroup, "650@27301L38");
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -133848153, "CC(remember):BasicText.kt#9igjgp");
                                        boolean z4 = (i7 & 112) == 32;
                                        Object rememberedValue = startRestartGroup.rememberedValue();
                                        if (z4 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue = new TextLinkScope(annotatedString);
                                            startRestartGroup.updateRememberedValue(rememberedValue);
                                        }
                                        textLinkScope = (TextLinkScope) rememberedValue;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        startRestartGroup.endReplaceGroup();
                                    } else {
                                        startRestartGroup.startReplaceGroup(145727068);
                                        startRestartGroup.endReplaceGroup();
                                        textLinkScope = null;
                                    }
                                    if (TextAnnotatedStringNodeKt.hasLinks(annotatedString)) {
                                        startRestartGroup.startReplaceGroup(145925283);
                                        ComposerKt.sourceInformation(startRestartGroup, "657@27566L70");
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -133839641, "CC(remember):BasicText.kt#9igjgp");
                                        i10 = i9;
                                        boolean changed = ((i7 & 112) == 32) | startRestartGroup.changed(textLinkScope);
                                        Object rememberedValue2 = startRestartGroup.rememberedValue();
                                        if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue2 = new Function0() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda2
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Object invoke() {
                                                    AnnotatedString LayoutWithLinksAndInlineContent_11Od_4g$lambda$25$lambda$24;
                                                    LayoutWithLinksAndInlineContent_11Od_4g$lambda$25$lambda$24 = BasicTextKt.LayoutWithLinksAndInlineContent_11Od_4g$lambda$25$lambda$24(TextLinkScope.this, annotatedString);
                                                    return LayoutWithLinksAndInlineContent_11Od_4g$lambda$25$lambda$24;
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue2);
                                        }
                                        function0 = (Function0) rememberedValue2;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        startRestartGroup.endReplaceGroup();
                                    } else {
                                        i10 = i9;
                                        startRestartGroup.startReplaceGroup(146022561);
                                        ComposerKt.sourceInformation(startRestartGroup, "659@27666L8");
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -133836503, "CC(remember):BasicText.kt#9igjgp");
                                        boolean z5 = (i7 & 112) == 32;
                                        Object rememberedValue3 = startRestartGroup.rememberedValue();
                                        if (z5 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue3 = new Function0() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda3
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Object invoke() {
                                                    AnnotatedString LayoutWithLinksAndInlineContent_11Od_4g$lambda$27$lambda$26;
                                                    LayoutWithLinksAndInlineContent_11Od_4g$lambda$27$lambda$26 = BasicTextKt.LayoutWithLinksAndInlineContent_11Od_4g$lambda$27$lambda$26(AnnotatedString.this);
                                                    return LayoutWithLinksAndInlineContent_11Od_4g$lambda$27$lambda$26;
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue3);
                                        }
                                        function0 = (Function0) rememberedValue3;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        startRestartGroup.endReplaceGroup();
                                    }
                                    Function0 function02 = function0;
                                    if (z) {
                                        pair = AnnotatedStringResolveInlineContentKt.resolveInlineContent(annotatedString, emptyMap);
                                    } else {
                                        pair = new Pair<>(null, null);
                                    }
                                    List<AnnotatedString.Range<Placeholder>> component1 = pair.component1();
                                    List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>> component2 = pair.component2();
                                    if (z) {
                                        startRestartGroup.startReplaceGroup(146338668);
                                        ComposerKt.sourceInformation(startRestartGroup, "670@27983L61");
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -133826306, "CC(remember):BasicText.kt#9igjgp");
                                        Object rememberedValue4 = startRestartGroup.rememberedValue();
                                        list = component2;
                                        if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                            function13 = null;
                                            rememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                                            startRestartGroup.updateRememberedValue(rememberedValue4);
                                        } else {
                                            function13 = null;
                                        }
                                        mutableState = (MutableState) rememberedValue4;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        startRestartGroup.endReplaceGroup();
                                    } else {
                                        list = component2;
                                        function13 = null;
                                        startRestartGroup.startReplaceGroup(146426428);
                                        startRestartGroup.endReplaceGroup();
                                        mutableState = null;
                                    }
                                    if (z) {
                                        startRestartGroup.startReplaceGroup(146519677);
                                        ComposerKt.sourceInformation(startRestartGroup, "675@28166L44");
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -133820467, "CC(remember):BasicText.kt#9igjgp");
                                        boolean changed2 = startRestartGroup.changed(mutableState);
                                        Object rememberedValue5 = startRestartGroup.rememberedValue();
                                        if (changed2) {
                                            i11 = i7;
                                        } else {
                                            i11 = i7;
                                        }
                                        rememberedValue5 = new Function1() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda4
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                Unit LayoutWithLinksAndInlineContent_11Od_4g$lambda$30$lambda$29;
                                                LayoutWithLinksAndInlineContent_11Od_4g$lambda$30$lambda$29 = BasicTextKt.LayoutWithLinksAndInlineContent_11Od_4g$lambda$30$lambda$29(MutableState.this, (List) obj);
                                                return LayoutWithLinksAndInlineContent_11Od_4g$lambda$30$lambda$29;
                                            }
                                        };
                                        startRestartGroup.updateRememberedValue(rememberedValue5);
                                        function13 = (Function1) rememberedValue5;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        startRestartGroup.endReplaceGroup();
                                    } else {
                                        i11 = i7;
                                        startRestartGroup.startReplaceGroup(146591100);
                                        startRestartGroup.endReplaceGroup();
                                    }
                                    Function1 function14 = function13;
                                    int i13 = (i11 >> 3) & 14;
                                    final MutableState mutableState2 = mutableState;
                                    final TextLinkScope textLinkScope2 = textLinkScope;
                                    List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>> list2 = list;
                                    BasicText_androidKt.BackgroundTextMeasurement(annotatedString, textStyle2, resolver, component1, startRestartGroup, ((i10 << 6) & 896) | ((i11 >> 12) & 112) | i13);
                                    AnnotatedString annotatedString2 = (AnnotatedString) function02.invoke();
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -133802474, "CC(remember):BasicText.kt#9igjgp");
                                    boolean changedInstance = startRestartGroup.changedInstance(textLinkScope2) | ((i11 & 896) == 256);
                                    Object rememberedValue6 = startRestartGroup.rememberedValue();
                                    if (changedInstance || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue6 = new Function1() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda5
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                Unit LayoutWithLinksAndInlineContent_11Od_4g$lambda$34$lambda$33;
                                                LayoutWithLinksAndInlineContent_11Od_4g$lambda$34$lambda$33 = BasicTextKt.LayoutWithLinksAndInlineContent_11Od_4g$lambda$34$lambda$33(TextLinkScope.this, function1, (TextLayoutResult) obj);
                                                return LayoutWithLinksAndInlineContent_11Od_4g$lambda$34$lambda$33;
                                            }
                                        };
                                        startRestartGroup.updateRememberedValue(rememberedValue6);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    Map<String, InlineTextContent> map3 = emptyMap;
                                    Modifier m1282textModifierCL7eQgs = m1282textModifierCL7eQgs(modifier2, annotatedString2, textStyle, (Function1) rememberedValue6, i, z3, i2, i3, resolver, component1, function14, selectionController, colorProducer, function12, textAutoSize);
                                    if (!z) {
                                        startRestartGroup.startReplaceGroup(147770775);
                                        ComposerKt.sourceInformation(startRestartGroup, "713@29493L55");
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -133777992, "CC(remember):BasicText.kt#9igjgp");
                                        boolean changedInstance2 = startRestartGroup.changedInstance(textLinkScope2);
                                        Object rememberedValue7 = startRestartGroup.rememberedValue();
                                        if (changedInstance2 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue7 = new Function0() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda6
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Object invoke() {
                                                    boolean LayoutWithLinksAndInlineContent_11Od_4g$lambda$37$lambda$36;
                                                    LayoutWithLinksAndInlineContent_11Od_4g$lambda$37$lambda$36 = BasicTextKt.LayoutWithLinksAndInlineContent_11Od_4g$lambda$37$lambda$36(TextLinkScope.this);
                                                    return Boolean.valueOf(LayoutWithLinksAndInlineContent_11Od_4g$lambda$37$lambda$36);
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue7);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        LinksTextMeasurePolicy linksTextMeasurePolicy = new LinksTextMeasurePolicy((Function0) rememberedValue7);
                                        startRestartGroup.endReplaceGroup();
                                        textMeasurePolicy = linksTextMeasurePolicy;
                                    } else {
                                        startRestartGroup.startReplaceGroup(147947537);
                                        ComposerKt.sourceInformation(startRestartGroup, "717@29664L55,718@29754L39");
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -133772520, "CC(remember):BasicText.kt#9igjgp");
                                        boolean changedInstance3 = startRestartGroup.changedInstance(textLinkScope2);
                                        Object rememberedValue8 = startRestartGroup.rememberedValue();
                                        if (changedInstance3 || rememberedValue8 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue8 = new Function0() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda7
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Object invoke() {
                                                    boolean LayoutWithLinksAndInlineContent_11Od_4g$lambda$40$lambda$39;
                                                    LayoutWithLinksAndInlineContent_11Od_4g$lambda$40$lambda$39 = BasicTextKt.LayoutWithLinksAndInlineContent_11Od_4g$lambda$40$lambda$39(TextLinkScope.this);
                                                    return Boolean.valueOf(LayoutWithLinksAndInlineContent_11Od_4g$lambda$40$lambda$39);
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue8);
                                        }
                                        Function0 function03 = (Function0) rememberedValue8;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -133769656, "CC(remember):BasicText.kt#9igjgp");
                                        boolean changed3 = startRestartGroup.changed(mutableState2);
                                        Object rememberedValue9 = startRestartGroup.rememberedValue();
                                        if (changed3 || rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue9 = new Function0() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda8
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Object invoke() {
                                                    List LayoutWithLinksAndInlineContent_11Od_4g$lambda$42$lambda$41;
                                                    LayoutWithLinksAndInlineContent_11Od_4g$lambda$42$lambda$41 = BasicTextKt.LayoutWithLinksAndInlineContent_11Od_4g$lambda$42$lambda$41(MutableState.this);
                                                    return LayoutWithLinksAndInlineContent_11Od_4g$lambda$42$lambda$41;
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue9);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        TextMeasurePolicy textMeasurePolicy2 = new TextMeasurePolicy(function03, (Function0) rememberedValue9);
                                        startRestartGroup.endReplaceGroup();
                                        textMeasurePolicy = textMeasurePolicy2;
                                    }
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                                    int m = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                                    CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, m1282textModifierCL7eQgs);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
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
                                    Updater.m3912setimpl(m3905constructorimpl, textMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(m))) {
                                        m3905constructorimpl.updateRememberedValue(Integer.valueOf(m));
                                        m3905constructorimpl.apply(Integer.valueOf(m), setCompositeKeyHash);
                                    }
                                    Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -433564842, "C:BasicText.kt#423gt5");
                                    if (textLinkScope2 == null) {
                                        startRestartGroup.startReplaceGroup(-433557001);
                                        startRestartGroup.endReplaceGroup();
                                    } else {
                                        startRestartGroup.startReplaceGroup(-291080374);
                                        ComposerKt.sourceInformation(startRestartGroup, "687@28455L18");
                                        textLinkScope2.LinksComposables(startRestartGroup, 0);
                                        startRestartGroup.endReplaceGroup();
                                        Unit unit = Unit.INSTANCE;
                                    }
                                    if (list2 == null) {
                                        startRestartGroup.startReplaceGroup(-433506223);
                                        startRestartGroup.endReplaceGroup();
                                    } else {
                                        startRestartGroup.startReplaceGroup(-433506222);
                                        ComposerKt.sourceInformation(startRestartGroup, "*688@28511L48");
                                        AnnotatedStringResolveInlineContentKt.InlineChildren(annotatedString, list2, startRestartGroup, i13);
                                        Unit unit2 = Unit.INSTANCE;
                                        startRestartGroup.endReplaceGroup();
                                        Unit unit3 = Unit.INSTANCE;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    startRestartGroup.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    map2 = map3;
                                }
                                endRestartGroup = startRestartGroup.endRestartGroup();
                                if (endRestartGroup != null) {
                                    endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextKt$$ExternalSyntheticLambda9
                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(Object obj, Object obj2) {
                                            Unit LayoutWithLinksAndInlineContent_11Od_4g$lambda$43;
                                            LayoutWithLinksAndInlineContent_11Od_4g$lambda$43 = BasicTextKt.LayoutWithLinksAndInlineContent_11Od_4g$lambda$43(Modifier.this, annotatedString, function1, z, map2, textStyle, i, z2, i2, i3, resolver, selectionController, colorProducer, function12, textAutoSize, i4, i5, i6, (Composer) obj, ((Integer) obj2).intValue());
                                            return LayoutWithLinksAndInlineContent_11Od_4g$lambda$43;
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            if ((i6 & 16384) != 0) {
                            }
                            if (!startRestartGroup.shouldExecute((306783379 & i7) == 306783378 || (i9 & 9363) != 9362, i7 & 1)) {
                            }
                            endRestartGroup = startRestartGroup.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        if ((i6 & 8192) == 0) {
                        }
                        if ((i6 & 16384) != 0) {
                        }
                        if (!startRestartGroup.shouldExecute((306783379 & i7) == 306783378 || (i9 & 9363) != 9362, i7 & 1)) {
                        }
                        endRestartGroup = startRestartGroup.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    if ((i6 & 1024) != 0) {
                    }
                    if ((i6 & 2048) != 0) {
                    }
                    i9 = i8;
                    if ((i6 & 4096) != 0) {
                    }
                    if ((i6 & 8192) == 0) {
                    }
                    if ((i6 & 16384) != 0) {
                    }
                    if (!startRestartGroup.shouldExecute((306783379 & i7) == 306783378 || (i9 & 9363) != 9362, i7 & 1)) {
                    }
                    endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                if ((i6 & 512) == 0) {
                }
                if ((i6 & 1024) != 0) {
                }
                if ((i6 & 2048) != 0) {
                }
                i9 = i8;
                if ((i6 & 4096) != 0) {
                }
                if ((i6 & 8192) == 0) {
                }
                if ((i6 & 16384) != 0) {
                }
                if (!startRestartGroup.shouldExecute((306783379 & i7) == 306783378 || (i9 & 9363) != 9362, i7 & 1)) {
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            z3 = z2;
            if ((i6 & 256) != 0) {
            }
            if ((i6 & 512) == 0) {
            }
            if ((i6 & 1024) != 0) {
            }
            if ((i6 & 2048) != 0) {
            }
            i9 = i8;
            if ((i6 & 4096) != 0) {
            }
            if ((i6 & 8192) == 0) {
            }
            if ((i6 & 16384) != 0) {
            }
            if (!startRestartGroup.shouldExecute((306783379 & i7) == 306783378 || (i9 & 9363) != 9362, i7 & 1)) {
            }
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        if ((i6 & 32) == 0) {
        }
        if ((i6 & 64) == 0) {
        }
        if ((i6 & 128) == 0) {
        }
        z3 = z2;
        if ((i6 & 256) != 0) {
        }
        if ((i6 & 512) == 0) {
        }
        if ((i6 & 1024) != 0) {
        }
        if ((i6 & 2048) != 0) {
        }
        i9 = i8;
        if ((i6 & 4096) != 0) {
        }
        if ((i6 & 8192) == 0) {
        }
        if ((i6 & 16384) != 0) {
        }
        if (!startRestartGroup.shouldExecute((306783379 & i7) == 306783378 || (i9 & 9363) != 9362, i7 & 1)) {
        }
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnnotatedString LayoutWithLinksAndInlineContent_11Od_4g$lambda$25$lambda$24(TextLinkScope textLinkScope, AnnotatedString annotatedString) {
        AnnotatedString applyAnnotators$foundation_release;
        return (textLinkScope == null || (applyAnnotators$foundation_release = textLinkScope.applyAnnotators$foundation_release()) == null) ? annotatedString : applyAnnotators$foundation_release;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LayoutWithLinksAndInlineContent_11Od_4g$lambda$30$lambda$29(MutableState mutableState, List list) {
        if (mutableState != null) {
            mutableState.setValue(list);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LayoutWithLinksAndInlineContent_11Od_4g$lambda$34$lambda$33(TextLinkScope textLinkScope, Function1 function1, TextLayoutResult textLayoutResult) {
        if (textLinkScope != null) {
            textLinkScope.setTextLayoutResult(textLayoutResult);
        }
        if (function1 != null) {
            function1.invoke(textLayoutResult);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LayoutWithLinksAndInlineContent_11Od_4g$lambda$37$lambda$36(TextLinkScope textLinkScope) {
        if (textLinkScope != null) {
            return textLinkScope.getShouldMeasureLinks().invoke().booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LayoutWithLinksAndInlineContent_11Od_4g$lambda$40$lambda$39(TextLinkScope textLinkScope) {
        if (textLinkScope != null) {
            return textLinkScope.getShouldMeasureLinks().invoke().booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List LayoutWithLinksAndInlineContent_11Od_4g$lambda$42$lambda$41(MutableState mutableState) {
        if (mutableState != null) {
            return (List) mutableState.getValue();
        }
        return null;
    }
}
