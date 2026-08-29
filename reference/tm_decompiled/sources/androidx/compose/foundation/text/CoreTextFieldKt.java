package androidx.compose.foundation.text;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationConstants;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.relocation.BringIntoViewRequester;
import androidx.compose.foundation.relocation.BringIntoViewRequesterKt;
import androidx.compose.foundation.text.handwriting.StylusHandwritingKt;
import androidx.compose.foundation.text.input.internal.CoreTextFieldSemanticsModifier;
import androidx.compose.foundation.text.input.internal.LegacyAdaptingPlatformTextInputModifierNodeKt;
import androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter;
import androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter_androidKt;
import androidx.compose.foundation.text.selection.OffsetProvider;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors_androidKt;
import androidx.compose.foundation.text.selection.SelectedTextType;
import androidx.compose.foundation.text.selection.SelectionGesturesKt;
import androidx.compose.foundation.text.selection.SelectionHandleAnchor;
import androidx.compose.foundation.text.selection.SelectionHandleInfo;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: CoreTextField.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aú\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u001323\b\u0002\u0010\u001d\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001f2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0001¢\u0006\u0002\u0010%\u001a0\u0010&\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u001fH\u0003¢\u0006\u0002\u0010*\u001a\u001c\u0010+\u001a\u00020\u0007*\u00020\u00072\u0006\u0010,\u001a\u00020-2\u0006\u0010'\u001a\u00020(H\u0002\u001a \u0010.\u001a\u00020\u00012\u0006\u0010,\u001a\u00020-2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u0013H\u0000\u001a0\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u0002042\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00105\u001a\u000206H\u0002\u001a\u0010\u00107\u001a\u00020\u00012\u0006\u0010,\u001a\u00020-H\u0002\u001a2\u00108\u001a\u00020\u0001*\u0002092\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\r2\u0006\u00105\u001a\u000206H\u0080@¢\u0006\u0002\u0010=\u001a\u001d\u0010>\u001a\u00020\u00012\u0006\u0010'\u001a\u00020(2\u0006\u0010?\u001a\u00020\u0013H\u0003¢\u0006\u0002\u0010@\u001a\u0015\u0010A\u001a\u00020\u00012\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\u0010B\u001a \u0010C\u001a\u00020\u00012\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00105\u001a\u000206H\u0002\u001a\u001c\u0010D\u001a\u00020\u0007*\u00020\u00072\u0006\u0010E\u001a\u00020(2\u0006\u0010F\u001a\u00020GH\u0002¨\u0006H²\u0006\n\u0010I\u001a\u00020\u0013X\u008a\u0084\u0002"}, d2 = {"CoreTextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "onTextLayout", "Landroidx/compose/ui/text/TextLayoutResult;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "softWrap", "", "maxLines", "", "minLines", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "enabled", "readOnly", "decorationBox", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "innerTextField", "textScrollerPosition", "Landroidx/compose/foundation/text/TextFieldScrollerPosition;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;ZIILandroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/foundation/text/KeyboardActions;ZZLkotlin/jvm/functions/Function3;Landroidx/compose/foundation/text/TextFieldScrollerPosition;Landroidx/compose/runtime/Composer;III)V", "CoreTextFieldRootBox", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "content", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "previewKeyEventToDeselectOnBack", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "tapToFocus", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "allowKeyboard", "startInputSession", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "endInputSession", "bringSelectionEndIntoView", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "textDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "textLayoutResult", "(Landroidx/compose/foundation/relocation/BringIntoViewRequester;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/foundation/text/TextDelegate;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/text/input/OffsetMapping;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "SelectionToolbarAndHandles", "show", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;ZLandroidx/compose/runtime/Composer;I)V", "TextFieldCursorHandle", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/runtime/Composer;I)V", "notifyFocusedRect", "addContextMenuComponents", "textFieldSelectionManager", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "foundation_release", "writeable"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CoreTextFieldKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CoreTextField$lambda$41(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, TextStyle textStyle, VisualTransformation visualTransformation, Function1 function12, MutableInteractionSource mutableInteractionSource, Brush brush, boolean z, int i, int i2, ImeOptions imeOptions, KeyboardActions keyboardActions, boolean z2, boolean z3, Function3 function3, TextFieldScrollerPosition textFieldScrollerPosition, int i3, int i4, int i5, Composer composer, int i6) {
        CoreTextField(textFieldValue, function1, modifier, textStyle, visualTransformation, function12, mutableInteractionSource, brush, z, i, i2, imeOptions, keyboardActions, z2, z3, function3, textFieldScrollerPosition, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CoreTextFieldRootBox$lambda$43(Modifier modifier, TextFieldSelectionManager textFieldSelectionManager, Function2 function2, int i, Composer composer, int i2) {
        CoreTextFieldRootBox(modifier, textFieldSelectionManager, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionToolbarAndHandles$lambda$49(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Composer composer, int i2) {
        SelectionToolbarAndHandles(textFieldSelectionManager, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TextFieldCursorHandle$lambda$55(TextFieldSelectionManager textFieldSelectionManager, int i, Composer composer, int i2) {
        TextFieldCursorHandle(textFieldSelectionManager, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CoreTextField$lambda$1$lambda$0(TextLayoutResult textLayoutResult) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0492, code lost:
    
        if (r11 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L275;
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x0a1f, code lost:
    
        if (r11 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L446;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x0b03, code lost:
    
        if (r11 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L480;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0782  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x07dd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0813  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x082e  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0874  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x089b  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x08a3  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x08b7  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x091a  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0929  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x095c  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x096f  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x097e  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x09d2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0a17  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0a48  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0a51  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0a66  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0a91  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0ac4  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0afb  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0b4d  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0baa  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0bc1  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0c19  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0c61  */
    /* JADX WARN: Removed duplicated region for block: B:272:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0bca  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0b06  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0a94  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0a4a  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0a22  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0971  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x095e  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x091c  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x08a5  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x089d  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0825  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x07a2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x0c3c  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:437:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0252  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void CoreTextField(final TextFieldValue textFieldValue, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, TextStyle textStyle, VisualTransformation visualTransformation, Function1<? super TextLayoutResult, Unit> function12, MutableInteractionSource mutableInteractionSource, Brush brush, boolean z, int i, int i2, ImeOptions imeOptions, KeyboardActions keyboardActions, boolean z2, boolean z3, Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function3, TextFieldScrollerPosition textFieldScrollerPosition, Composer composer, final int i3, final int i4, final int i5) {
        int i6;
        int i7;
        Modifier modifier2;
        int i8;
        TextStyle textStyle2;
        int i9;
        int i10;
        Function1<? super TextLayoutResult, Unit> function13;
        int i11;
        MutableInteractionSource mutableInteractionSource2;
        int i12;
        SolidColor solidColor;
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
        final int i28;
        final KeyboardActions keyboardActions2;
        final boolean z4;
        final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function32;
        final TextFieldScrollerPosition textFieldScrollerPosition2;
        Composer composer2;
        final Brush brush2;
        final MutableInteractionSource mutableInteractionSource3;
        final Function1<? super TextLayoutResult, Unit> function14;
        final TextStyle textStyle3;
        final Modifier modifier3;
        final VisualTransformation visualTransformation2;
        final boolean z5;
        final int i29;
        final ImeOptions imeOptions2;
        final boolean z6;
        ScopeUpdateScope endRestartGroup;
        MutableInteractionSource mutableInteractionSource4;
        ImeOptions imeOptions3;
        KeyboardActions keyboardActions3;
        Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> lambda$559628295$foundation_release;
        TextFieldScrollerPosition textFieldScrollerPosition3;
        boolean z7;
        boolean z8;
        int i30;
        final Function1<? super TextLayoutResult, Unit> function15;
        boolean z9;
        VisualTransformation visualTransformation3;
        Modifier modifier4;
        ImeOptions imeOptions4;
        int i31;
        int i32;
        Brush brush3;
        MutableInteractionSource mutableInteractionSource5;
        Brush brush4;
        LegacyPlatformTextInputServiceAdapter legacyPlatformTextInputServiceAdapter;
        WindowInfo windowInfo;
        int i33;
        TextFieldScrollerPosition textFieldScrollerPosition4;
        TextFieldScrollerPosition textFieldScrollerPosition5;
        AnnotatedString annotatedString;
        TextStyle textStyle4;
        boolean z10;
        FontFamily.Resolver resolver;
        FocusManager focusManager;
        final LegacyTextFieldState legacyTextFieldState;
        boolean z11;
        boolean changedInstance;
        final TextFieldSelectionManager textFieldSelectionManager;
        final LegacyTextFieldState legacyTextFieldState2;
        boolean z12;
        final TextInputService textInputService;
        ImeOptions imeOptions5;
        final OffsetMapping offsetMapping;
        CoroutineScope coroutineScope;
        BringIntoViewRequester bringIntoViewRequester;
        VisualTransformation visualTransformation4;
        String str;
        boolean z13;
        final TextFieldSelectionManager textFieldSelectionManager2;
        boolean changed;
        boolean z14;
        boolean z15;
        boolean z16;
        CoreTextFieldKt$CoreTextField$2$1 rememberedValue;
        State state;
        boolean changedInstance2;
        Object rememberedValue2;
        boolean changedInstance3;
        Object rememberedValue3;
        FocusRequester focusRequester;
        boolean z17;
        final ImeOptions imeOptions6;
        final boolean z18;
        boolean changedInstance4;
        Object rememberedValue4;
        final TextFieldValue textFieldValue2;
        final WindowInfo windowInfo2;
        boolean changedInstance5;
        Object rememberedValue5;
        boolean z19;
        WindowInfo windowInfo3;
        final LegacyTextFieldState legacyTextFieldState3;
        boolean changedInstance6;
        Modifier modifier5;
        boolean changedInstance7;
        Object rememberedValue6;
        boolean changed2;
        int i34;
        boolean changedInstance8;
        Object rememberedValue7;
        final boolean z20;
        Modifier.Companion companion;
        String str2;
        int i35;
        Composer startRestartGroup = composer.startRestartGroup(31062401);
        ComposerKt.sourceInformation(startRestartGroup, "C(CoreTextField)N(value,onValueChange,modifier,textStyle,visualTransformation,onTextLayout,interactionSource,cursorBrush,softWrap,maxLines,minLines,imeOptions,keyboardActions,enabled,readOnly,decorationBox,textScrollerPosition)212@12013L29,213@12083L58,214@12187L72,219@12317L7,220@12378L7,221@12446L7,222@12511L7,223@12556L7,224@12625L7,245@13467L277,257@14049L21,259@14095L453,288@14948L26,291@15041L24,292@15099L37,294@15156L51,300@15445L7,302@15540L7,303@15597L7,319@16182L1514,354@17803L42,355@17871L971,355@17850L992,381@18912L28,382@19008L992,411@20291L577,429@20939L1606,482@23157L48,482@23131L74,484@23240L503,484@23211L532,516@24417L987,533@25467L7,535@25536L151,567@26896L5409,567@26843L5462:CoreTextField.kt#423gt5");
        if ((i5 & 1) != 0) {
            i6 = i3 | 6;
        } else if ((i3 & 6) == 0) {
            i6 = (startRestartGroup.changed(textFieldValue) ? 4 : 2) | i3;
        } else {
            i6 = i3;
        }
        if ((i5 & 2) != 0) {
            i6 |= 48;
        } else if ((i3 & 48) == 0) {
            i6 |= startRestartGroup.changedInstance(function1) ? 32 : 16;
            i7 = i5 & 4;
            if (i7 == 0) {
                i6 |= 384;
            } else if ((i3 & 384) == 0) {
                modifier2 = modifier;
                i6 |= startRestartGroup.changed(modifier2) ? 256 : 128;
                i8 = i5 & 8;
                if (i8 != 0) {
                    i6 |= 3072;
                } else if ((i3 & 3072) == 0) {
                    textStyle2 = textStyle;
                    i6 |= startRestartGroup.changed(textStyle2) ? 2048 : 1024;
                    i9 = i5 & 16;
                    if (i9 == 0) {
                        i6 |= 24576;
                    } else if ((i3 & 24576) == 0) {
                        i6 |= startRestartGroup.changed(visualTransformation) ? 16384 : 8192;
                        i10 = i5 & 32;
                        if (i10 != 0) {
                            i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            function13 = function12;
                        } else {
                            function13 = function12;
                            if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i6 |= startRestartGroup.changedInstance(function13) ? 131072 : 65536;
                            }
                        }
                        i11 = i5 & 64;
                        if (i11 != 0) {
                            i6 |= 1572864;
                            mutableInteractionSource2 = mutableInteractionSource;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource;
                            if ((i3 & 1572864) == 0) {
                                i6 |= startRestartGroup.changed(mutableInteractionSource2) ? 1048576 : 524288;
                            }
                        }
                        i12 = i5 & 128;
                        if (i12 != 0) {
                            i6 |= 12582912;
                            solidColor = brush;
                        } else {
                            solidColor = brush;
                            if ((i3 & 12582912) == 0) {
                                i6 |= startRestartGroup.changed(solidColor) ? 8388608 : 4194304;
                            }
                        }
                        i13 = i5 & 256;
                        if (i13 != 0) {
                            i6 |= 100663296;
                        } else if ((i3 & 100663296) == 0) {
                            i14 = i13;
                            i6 |= startRestartGroup.changed(z) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                            i15 = i5 & 512;
                            if (i15 == 0) {
                                i6 |= 805306368;
                            } else if ((i3 & 805306368) == 0) {
                                i16 = i15;
                                i6 |= startRestartGroup.changed(i) ? 536870912 : 268435456;
                                i17 = i5 & 1024;
                                if (i17 != 0) {
                                    i19 = i4 | 6;
                                    i18 = i17;
                                } else if ((i4 & 6) == 0) {
                                    i18 = i17;
                                    i19 = i4 | (startRestartGroup.changed(i2) ? 4 : 2);
                                } else {
                                    i18 = i17;
                                    i19 = i4;
                                }
                                if ((i4 & 48) == 0) {
                                    if ((i5 & 2048) == 0 && startRestartGroup.changed(imeOptions)) {
                                        i35 = 32;
                                        i19 |= i35;
                                    }
                                    i35 = 16;
                                    i19 |= i35;
                                }
                                int i36 = i19;
                                i20 = i5 & 4096;
                                if (i20 != 0) {
                                    i21 = i36 | 384;
                                } else if ((i4 & 384) == 0) {
                                    i21 = i36 | (startRestartGroup.changed(keyboardActions) ? 256 : 128);
                                } else {
                                    i21 = i36;
                                }
                                i22 = i5 & 8192;
                                if (i22 != 0) {
                                    i23 = i21 | 3072;
                                } else {
                                    int i37 = i21;
                                    if ((i4 & 3072) == 0) {
                                        i23 = i37 | (startRestartGroup.changed(z2) ? 2048 : 1024);
                                    } else {
                                        i23 = i37;
                                    }
                                }
                                i24 = i5 & 16384;
                                if (i24 != 0) {
                                    i25 = i23 | 24576;
                                } else {
                                    i25 = i23;
                                    if ((i4 & 24576) == 0) {
                                        i25 |= startRestartGroup.changed(z3) ? 16384 : 8192;
                                        i26 = i5 & 32768;
                                        if (i26 == 0) {
                                            i25 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                                        } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                            i25 |= startRestartGroup.changedInstance(function3) ? 131072 : 65536;
                                        }
                                        i27 = i5 & 65536;
                                        if (i27 == 0) {
                                            i25 |= 1572864;
                                        } else if ((i4 & 1572864) == 0) {
                                            i25 |= startRestartGroup.changed(textFieldScrollerPosition) ? 1048576 : 524288;
                                        }
                                        if (!startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (i25 & 599187) != 599186, i6 & 1)) {
                                            startRestartGroup.startDefaults();
                                            ComposerKt.sourceInformation(startRestartGroup, "197@11338L2");
                                            if ((i3 & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                                startRestartGroup.skipToGroupEnd();
                                                if ((i5 & 2048) != 0) {
                                                    i25 &= -113;
                                                }
                                                MutableInteractionSource mutableInteractionSource6 = mutableInteractionSource2;
                                                brush3 = solidColor;
                                                mutableInteractionSource5 = mutableInteractionSource6;
                                                visualTransformation3 = visualTransformation;
                                                i32 = i;
                                                i31 = i2;
                                                keyboardActions3 = keyboardActions;
                                                z7 = z2;
                                                z8 = z3;
                                                lambda$559628295$foundation_release = function3;
                                                textFieldScrollerPosition3 = textFieldScrollerPosition;
                                                function15 = function13;
                                                modifier4 = modifier2;
                                                i30 = i25;
                                                z9 = z;
                                                imeOptions4 = imeOptions;
                                            } else {
                                                if (i7 != 0) {
                                                    modifier2 = Modifier.INSTANCE;
                                                }
                                                if (i8 != 0) {
                                                    textStyle2 = TextStyle.INSTANCE.getDefault();
                                                }
                                                VisualTransformation none = i9 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                                                if (i10 != 0) {
                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213793597, "CC(remember):CoreTextField.kt#9igjgp");
                                                    Object rememberedValue8 = startRestartGroup.rememberedValue();
                                                    if (rememberedValue8 == Composer.INSTANCE.getEmpty()) {
                                                        rememberedValue8 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda8
                                                            @Override // kotlin.jvm.functions.Function1
                                                            public final Object invoke(Object obj) {
                                                                Unit CoreTextField$lambda$1$lambda$0;
                                                                CoreTextField$lambda$1$lambda$0 = CoreTextFieldKt.CoreTextField$lambda$1$lambda$0((TextLayoutResult) obj);
                                                                return CoreTextField$lambda$1$lambda$0;
                                                            }
                                                        };
                                                        startRestartGroup.updateRememberedValue(rememberedValue8);
                                                    }
                                                    function13 = (Function1) rememberedValue8;
                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                }
                                                if (i11 != 0) {
                                                    mutableInteractionSource2 = null;
                                                }
                                                if (i12 != 0) {
                                                    mutableInteractionSource4 = mutableInteractionSource2;
                                                    solidColor = new SolidColor(Color.INSTANCE.m4651getUnspecified0d7_KjU(), null);
                                                } else {
                                                    mutableInteractionSource4 = mutableInteractionSource2;
                                                }
                                                boolean z21 = i14 != 0 ? true : z;
                                                int i38 = i16 != 0 ? Integer.MAX_VALUE : i;
                                                int i39 = i18 != 0 ? 1 : i2;
                                                if ((i5 & 2048) != 0) {
                                                    imeOptions3 = ImeOptions.INSTANCE.getDefault();
                                                    i25 &= -113;
                                                } else {
                                                    imeOptions3 = imeOptions;
                                                }
                                                keyboardActions3 = i20 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                                                boolean z22 = i22 != 0 ? true : z2;
                                                boolean z23 = i24 != 0 ? false : z3;
                                                lambda$559628295$foundation_release = i26 != 0 ? ComposableSingletons$CoreTextFieldKt.INSTANCE.getLambda$559628295$foundation_release() : function3;
                                                textFieldScrollerPosition3 = i27 != 0 ? null : textFieldScrollerPosition;
                                                z7 = z22;
                                                z8 = z23;
                                                i30 = i25;
                                                function15 = function13;
                                                z9 = z21;
                                                visualTransformation3 = none;
                                                modifier4 = modifier2;
                                                imeOptions4 = imeOptions3;
                                                i31 = i39;
                                                i32 = i38;
                                                brush3 = solidColor;
                                                mutableInteractionSource5 = mutableInteractionSource4;
                                            }
                                            startRestartGroup.endDefaults();
                                            if (ComposerKt.isTraceInProgress()) {
                                                brush4 = brush3;
                                                ComposerKt.traceEventStart(31062401, i6, i30, "androidx.compose.foundation.text.CoreTextField (CoreTextField.kt:211)");
                                            } else {
                                                brush4 = brush3;
                                            }
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213771970, "CC(remember):CoreTextField.kt#9igjgp");
                                            Object rememberedValue9 = startRestartGroup.rememberedValue();
                                            if (rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue9 = new FocusRequester();
                                                startRestartGroup.updateRememberedValue(rememberedValue9);
                                            }
                                            final FocusRequester focusRequester2 = (FocusRequester) rememberedValue9;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213769701, "CC(remember):CoreTextField.kt#9igjgp");
                                            Object rememberedValue10 = startRestartGroup.rememberedValue();
                                            int i40 = i6;
                                            if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue10 = LegacyPlatformTextInputServiceAdapter_androidKt.createLegacyPlatformTextInputServiceAdapter();
                                                startRestartGroup.updateRememberedValue(rememberedValue10);
                                            }
                                            LegacyPlatformTextInputServiceAdapter legacyPlatformTextInputServiceAdapter2 = (LegacyPlatformTextInputServiceAdapter) rememberedValue10;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213766359, "CC(remember):CoreTextField.kt#9igjgp");
                                            Object rememberedValue11 = startRestartGroup.rememberedValue();
                                            boolean z24 = z9;
                                            if (rememberedValue11 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue11 = new TextInputService(legacyPlatformTextInputServiceAdapter2);
                                                startRestartGroup.updateRememberedValue(rememberedValue11);
                                            }
                                            final TextInputService textInputService2 = (TextInputService) rememberedValue11;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                            TextStyle textStyle5 = textStyle2;
                                            final int i41 = i31;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                            Object consume = startRestartGroup.consume(localDensity);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            final Density density = (Density) consume;
                                            ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver = CompositionLocalsKt.getLocalFontFamilyResolver();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                            Object consume2 = startRestartGroup.consume(localFontFamilyResolver);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            FontFamily.Resolver resolver2 = (FontFamily.Resolver) consume2;
                                            ProvidableCompositionLocal<TextSelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                            Object consume3 = startRestartGroup.consume(localTextSelectionColors);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            long backgroundColor = ((TextSelectionColors) consume3).getBackgroundColor();
                                            ProvidableCompositionLocal<FocusManager> localFocusManager = CompositionLocalsKt.getLocalFocusManager();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                            Object consume4 = startRestartGroup.consume(localFocusManager);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            FocusManager focusManager2 = (FocusManager) consume4;
                                            ProvidableCompositionLocal<WindowInfo> localWindowInfo = CompositionLocalsKt.getLocalWindowInfo();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                            Object consume5 = startRestartGroup.consume(localWindowInfo);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            WindowInfo windowInfo4 = (WindowInfo) consume5;
                                            KeyboardActions keyboardActions4 = keyboardActions3;
                                            ProvidableCompositionLocal<SoftwareKeyboardController> localSoftwareKeyboardController = CompositionLocalsKt.getLocalSoftwareKeyboardController();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                            Object consume6 = startRestartGroup.consume(localSoftwareKeyboardController);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            SoftwareKeyboardController softwareKeyboardController = (SoftwareKeyboardController) consume6;
                                            final Orientation orientation = (i32 == 1 && !z24 && imeOptions4.getSingleLine()) ? Orientation.Horizontal : Orientation.Vertical;
                                            Modifier modifier6 = modifier4;
                                            if (textFieldScrollerPosition3 != null) {
                                                legacyPlatformTextInputServiceAdapter = legacyPlatformTextInputServiceAdapter2;
                                                windowInfo = windowInfo4;
                                                i33 = i32;
                                                startRestartGroup.startReplaceGroup(-213745070);
                                                startRestartGroup.endReplaceGroup();
                                                textFieldScrollerPosition4 = textFieldScrollerPosition3;
                                            } else {
                                                startRestartGroup.startReplaceGroup(-213743954);
                                                ComposerKt.sourceInformation(startRestartGroup, "231@12956L70,231@12885L141");
                                                legacyPlatformTextInputServiceAdapter = legacyPlatformTextInputServiceAdapter2;
                                                Object[] objArr = {orientation};
                                                Saver<TextFieldScrollerPosition, Object> saver = TextFieldScrollerPosition.INSTANCE.getSaver();
                                                i33 = i32;
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213741753, "CC(remember):CoreTextField.kt#9igjgp");
                                                boolean changed3 = startRestartGroup.changed(orientation.ordinal());
                                                Object rememberedValue12 = startRestartGroup.rememberedValue();
                                                if (changed3) {
                                                    windowInfo = windowInfo4;
                                                } else {
                                                    windowInfo = windowInfo4;
                                                }
                                                rememberedValue12 = new Function0() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda11
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        TextFieldScrollerPosition CoreTextField$lambda$6$lambda$5;
                                                        CoreTextField$lambda$6$lambda$5 = CoreTextFieldKt.CoreTextField$lambda$6$lambda$5(Orientation.this);
                                                        return CoreTextField$lambda$6$lambda$5;
                                                    }
                                                };
                                                startRestartGroup.updateRememberedValue(rememberedValue12);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                textFieldScrollerPosition4 = (TextFieldScrollerPosition) RememberSaveableKt.m4033rememberSaveable(objArr, (Saver) saver, (Function0) rememberedValue12, startRestartGroup, 0);
                                                startRestartGroup.endReplaceGroup();
                                            }
                                            if (textFieldScrollerPosition4.getOrientation() != orientation) {
                                                if (orientation == Orientation.Vertical) {
                                                    str2 = "only single-line, non-wrap text fields can scroll horizontally";
                                                } else {
                                                    str2 = "single-line, non-wrap text fields can only scroll horizontally";
                                                }
                                                throw new IllegalArgumentException("Mismatching scroller orientation; ".concat(str2));
                                            }
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213725194, "CC(remember):CoreTextField.kt#9igjgp");
                                            int i42 = i40 & 14;
                                            boolean z25 = (i42 == 4) | ((i40 & 57344) == 16384);
                                            Object rememberedValue13 = startRestartGroup.rememberedValue();
                                            if (z25 || rememberedValue13 == Composer.INSTANCE.getEmpty()) {
                                                TransformedText filterWithValidation = ValidatingOffsetMappingKt.filterWithValidation(visualTransformation3, textFieldValue.getAnnotatedString());
                                                TextRange composition = textFieldValue.getComposition();
                                                if (composition != null) {
                                                    textFieldScrollerPosition5 = textFieldScrollerPosition4;
                                                    TransformedText m1393applyCompositionDecoration72CqOWE = TextFieldDelegate.INSTANCE.m1393applyCompositionDecoration72CqOWE(composition.getPackedValue(), filterWithValidation);
                                                    if (m1393applyCompositionDecoration72CqOWE != null) {
                                                        rememberedValue13 = m1393applyCompositionDecoration72CqOWE;
                                                        startRestartGroup.updateRememberedValue(rememberedValue13);
                                                    }
                                                } else {
                                                    textFieldScrollerPosition5 = textFieldScrollerPosition4;
                                                }
                                                rememberedValue13 = filterWithValidation;
                                                startRestartGroup.updateRememberedValue(rememberedValue13);
                                            } else {
                                                textFieldScrollerPosition5 = textFieldScrollerPosition4;
                                            }
                                            TransformedText transformedText = (TransformedText) rememberedValue13;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            AnnotatedString text = transformedText.getText();
                                            final OffsetMapping offsetMapping2 = transformedText.getOffsetMapping();
                                            RecomposeScope currentRecomposeScope = ComposablesKt.getCurrentRecomposeScope(startRestartGroup, 0);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213704922, "CC(remember):CoreTextField.kt#9igjgp");
                                            boolean changed4 = startRestartGroup.changed(softwareKeyboardController);
                                            Object rememberedValue14 = startRestartGroup.rememberedValue();
                                            if (changed4 || rememberedValue14 == Composer.INSTANCE.getEmpty()) {
                                                annotatedString = text;
                                                textStyle4 = textStyle5;
                                                z10 = z24;
                                                resolver = resolver2;
                                                rememberedValue14 = new LegacyTextFieldState(new TextDelegate(text, textStyle5, 0, 0, z24, 0, density, resolver2, null, AnimationConstants.DefaultDurationMillis, null), currentRecomposeScope, softwareKeyboardController);
                                                startRestartGroup.updateRememberedValue(rememberedValue14);
                                            } else {
                                                annotatedString = text;
                                                textStyle4 = textStyle5;
                                                z10 = z24;
                                                resolver = resolver2;
                                            }
                                            LegacyTextFieldState legacyTextFieldState4 = (LegacyTextFieldState) rememberedValue14;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            legacyTextFieldState4.m1330updatefnh65Uc(textFieldValue.getAnnotatedString(), annotatedString, textStyle4, z10, density, resolver, function1, keyboardActions4, focusManager2, backgroundColor);
                                            legacyTextFieldState4.getProcessor().reset(textFieldValue, legacyTextFieldState4.getInputSession());
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213678053, "CC(remember):CoreTextField.kt#9igjgp");
                                            Object rememberedValue15 = startRestartGroup.rememberedValue();
                                            if (rememberedValue15 == Composer.INSTANCE.getEmpty()) {
                                                focusManager = focusManager2;
                                                rememberedValue15 = new UndoManager(0, 1, null);
                                                startRestartGroup.updateRememberedValue(rememberedValue15);
                                            } else {
                                                focusManager = focusManager2;
                                            }
                                            UndoManager undoManager = (UndoManager) rememberedValue15;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            UndoManager.snapshotIfNeeded$default(undoManager, textFieldValue, 0L, 2, null);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 683737348, "CC(remember):Effects.kt#9igjgp");
                                            Object rememberedValue16 = startRestartGroup.rememberedValue();
                                            if (rememberedValue16 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue16 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, startRestartGroup);
                                                startRestartGroup.updateRememberedValue(rememberedValue16);
                                            }
                                            final CoroutineScope coroutineScope2 = (CoroutineScope) rememberedValue16;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213673210, "CC(remember):CoreTextField.kt#9igjgp");
                                            Object rememberedValue17 = startRestartGroup.rememberedValue();
                                            MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource5;
                                            if (rememberedValue17 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue17 = BringIntoViewRequesterKt.BringIntoViewRequester();
                                                startRestartGroup.updateRememberedValue(rememberedValue17);
                                            }
                                            final BringIntoViewRequester bringIntoViewRequester2 = (BringIntoViewRequester) rememberedValue17;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213671372, "CC(remember):CoreTextField.kt#9igjgp");
                                            Object rememberedValue18 = startRestartGroup.rememberedValue();
                                            if (rememberedValue18 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue18 = new TextFieldSelectionManager(undoManager);
                                                startRestartGroup.updateRememberedValue(rememberedValue18);
                                            }
                                            TextFieldSelectionManager textFieldSelectionManager3 = (TextFieldSelectionManager) rememberedValue18;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            textFieldSelectionManager3.setOffsetMapping$foundation_release(offsetMapping2);
                                            textFieldSelectionManager3.setVisualTransformation$foundation_release(visualTransformation3);
                                            textFieldSelectionManager3.setOnValueChange$foundation_release(legacyTextFieldState4.getOnValueChange());
                                            textFieldSelectionManager3.setState$foundation_release(legacyTextFieldState4);
                                            textFieldSelectionManager3.setValue$foundation_release(textFieldValue);
                                            ProvidableCompositionLocal<Clipboard> localClipboard = CompositionLocalsKt.getLocalClipboard();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                            Object consume7 = startRestartGroup.consume(localClipboard);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            textFieldSelectionManager3.setClipboard$foundation_release((Clipboard) consume7);
                                            textFieldSelectionManager3.setCoroutineScope$foundation_release(coroutineScope2);
                                            ProvidableCompositionLocal<TextToolbar> localTextToolbar = CompositionLocalsKt.getLocalTextToolbar();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                            Object consume8 = startRestartGroup.consume(localTextToolbar);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            textFieldSelectionManager3.setTextToolbar((TextToolbar) consume8);
                                            ProvidableCompositionLocal<HapticFeedback> localHapticFeedback = CompositionLocalsKt.getLocalHapticFeedback();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                            Object consume9 = startRestartGroup.consume(localHapticFeedback);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            textFieldSelectionManager3.setHapticFeedBack((HapticFeedback) consume9);
                                            textFieldSelectionManager3.setFocusRequester(focusRequester2);
                                            textFieldSelectionManager3.setEditable(!z8);
                                            final boolean z26 = z7;
                                            textFieldSelectionManager3.setEnabled(z26);
                                            if (ComposeFoundationFlags.isSmartSelectionEnabled) {
                                                startRestartGroup.startReplaceGroup(1966776937);
                                                ComposerKt.sourceInformation(startRestartGroup, "310@15872L87");
                                                textFieldSelectionManager3.setPlatformSelectionBehaviors$foundation_release(PlatformSelectionBehaviors_androidKt.rememberPlatformSelectionBehaviors(SelectedTextType.EditableText, textStyle4.getLocaleList(), startRestartGroup, 6));
                                                startRestartGroup.endReplaceGroup();
                                            } else {
                                                startRestartGroup.startReplaceGroup(1966923009);
                                                startRestartGroup.endReplaceGroup();
                                            }
                                            Modifier.Companion companion2 = Modifier.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213637077, "CC(remember):CoreTextField.kt#9igjgp");
                                            int i43 = i30 & 7168;
                                            int i44 = i30;
                                            int i45 = i44 & 57344;
                                            boolean changedInstance9 = (i43 == 2048) | startRestartGroup.changedInstance(legacyTextFieldState4) | (i45 == 16384) | startRestartGroup.changedInstance(textInputService2) | (i42 == 4);
                                            int i46 = (i44 & 112) ^ 48;
                                            if (i46 <= 32 || !startRestartGroup.changed(imeOptions4)) {
                                                legacyTextFieldState = legacyTextFieldState4;
                                                if ((i44 & 48) != 32) {
                                                    z11 = false;
                                                    changedInstance = changedInstance9 | z11 | startRestartGroup.changedInstance(offsetMapping2) | startRestartGroup.changedInstance(coroutineScope2) | startRestartGroup.changedInstance(bringIntoViewRequester2) | startRestartGroup.changedInstance(textFieldSelectionManager3);
                                                    Object rememberedValue19 = startRestartGroup.rememberedValue();
                                                    if (changedInstance) {
                                                        textFieldSelectionManager = textFieldSelectionManager3;
                                                        if (rememberedValue19 != Composer.INSTANCE.getEmpty()) {
                                                            visualTransformation4 = visualTransformation3;
                                                            coroutineScope = coroutineScope2;
                                                            str = "CC(<get-current>):CompositionLocal.kt#9igjgp";
                                                            imeOptions5 = imeOptions4;
                                                            z12 = z8;
                                                            legacyTextFieldState2 = legacyTextFieldState;
                                                            z13 = z26;
                                                            textFieldSelectionManager2 = textFieldSelectionManager;
                                                            bringIntoViewRequester = bringIntoViewRequester2;
                                                            offsetMapping = offsetMapping2;
                                                            textInputService = textInputService2;
                                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                            Modifier textFieldFocusModifier = TextFieldGestureModifiersKt.textFieldFocusModifier(companion2, z13, focusRequester2, mutableInteractionSource7, (Function1) rememberedValue19);
                                                            State rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf((z13 || z12) ? false : true), startRestartGroup, 0);
                                                            Unit unit = Unit.INSTANCE;
                                                            final boolean z27 = z13;
                                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213583572, "CC(remember):CoreTextField.kt#9igjgp");
                                                            changed = startRestartGroup.changed(rememberUpdatedState) | startRestartGroup.changedInstance(legacyTextFieldState2) | startRestartGroup.changedInstance(textInputService) | startRestartGroup.changedInstance(textFieldSelectionManager2);
                                                            if (i46 > 32 || !startRestartGroup.changed(imeOptions5)) {
                                                                z14 = changed;
                                                                if ((i44 & 48) != 32) {
                                                                    z15 = false;
                                                                    z16 = z14 | z15;
                                                                    rememberedValue = startRestartGroup.rememberedValue();
                                                                    if (!z16 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                                                        CoreTextFieldKt$CoreTextField$2$1 coreTextFieldKt$CoreTextField$2$1 = new CoreTextFieldKt$CoreTextField$2$1(legacyTextFieldState2, rememberUpdatedState, textInputService, textFieldSelectionManager2, imeOptions5, null);
                                                                        state = rememberUpdatedState;
                                                                        rememberedValue = coreTextFieldKt$CoreTextField$2$1;
                                                                        startRestartGroup.updateRememberedValue(rememberedValue);
                                                                    } else {
                                                                        state = rememberUpdatedState;
                                                                    }
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    State state2 = state;
                                                                    EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue, startRestartGroup, 6);
                                                                    Modifier.Companion companion3 = Modifier.INSTANCE;
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213551203, "CC(remember):CoreTextField.kt#9igjgp");
                                                                    changedInstance2 = startRestartGroup.changedInstance(legacyTextFieldState2);
                                                                    rememberedValue2 = startRestartGroup.rememberedValue();
                                                                    if (!changedInstance2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                                                        rememberedValue2 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda13
                                                                            @Override // kotlin.jvm.functions.Function1
                                                                            public final Object invoke(Object obj) {
                                                                                Unit CoreTextField$lambda$19$lambda$18;
                                                                                CoreTextField$lambda$19$lambda$18 = CoreTextFieldKt.CoreTextField$lambda$19$lambda$18(LegacyTextFieldState.this, ((Boolean) obj).booleanValue());
                                                                                return CoreTextField$lambda$19$lambda$18;
                                                                            }
                                                                        };
                                                                        startRestartGroup.updateRememberedValue(rememberedValue2);
                                                                    }
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    Modifier updateSelectionTouchMode = SelectionGesturesKt.updateSelectionTouchMode(companion3, (Function1) rememberedValue2);
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213547167, "CC(remember):CoreTextField.kt#9igjgp");
                                                                    changedInstance3 = startRestartGroup.changedInstance(legacyTextFieldState2) | (i45 != 16384) | (i43 != 2048) | startRestartGroup.changedInstance(offsetMapping) | startRestartGroup.changedInstance(textFieldSelectionManager2);
                                                                    rememberedValue3 = startRestartGroup.rememberedValue();
                                                                    if (!changedInstance3 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                                                        final OffsetMapping offsetMapping3 = offsetMapping;
                                                                        final TextFieldSelectionManager textFieldSelectionManager4 = textFieldSelectionManager2;
                                                                        final LegacyTextFieldState legacyTextFieldState5 = legacyTextFieldState2;
                                                                        final boolean z28 = z12;
                                                                        rememberedValue3 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda14
                                                                            @Override // kotlin.jvm.functions.Function1
                                                                            public final Object invoke(Object obj) {
                                                                                Unit CoreTextField$lambda$22$lambda$21;
                                                                                CoreTextField$lambda$22$lambda$21 = CoreTextFieldKt.CoreTextField$lambda$22$lambda$21(LegacyTextFieldState.this, focusRequester2, z28, z27, textFieldSelectionManager4, offsetMapping3, (Offset) obj);
                                                                                return CoreTextField$lambda$22$lambda$21;
                                                                            }
                                                                        };
                                                                        focusRequester = focusRequester2;
                                                                        z17 = z27;
                                                                        startRestartGroup.updateRememberedValue(rememberedValue3);
                                                                    } else {
                                                                        focusRequester = focusRequester2;
                                                                        z17 = z27;
                                                                    }
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    FocusRequester focusRequester3 = focusRequester;
                                                                    imeOptions6 = imeOptions5;
                                                                    z18 = z17;
                                                                    Modifier pointerHoverIcon$default = PointerIconKt.pointerHoverIcon$default(SelectionGesturesKt.selectionGestureInput(TextFieldPressGestureFilterKt.tapPressTextFieldModifier(updateSelectionTouchMode, mutableInteractionSource7, z17, (Function1) rememberedValue3), textFieldSelectionManager2.getMouseSelectionObserver(), textFieldSelectionManager2.getTouchSelectionObserver()), PointerIcon.INSTANCE.getText(), false, 2, null);
                                                                    Modifier.Companion companion4 = Modifier.INSTANCE;
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213506526, "CC(remember):CoreTextField.kt#9igjgp");
                                                                    changedInstance4 = startRestartGroup.changedInstance(legacyTextFieldState2) | (i42 != 4) | startRestartGroup.changedInstance(offsetMapping);
                                                                    rememberedValue4 = startRestartGroup.rememberedValue();
                                                                    if (!changedInstance4 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                                        textFieldValue2 = textFieldValue;
                                                                        rememberedValue4 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda15
                                                                            @Override // kotlin.jvm.functions.Function1
                                                                            public final Object invoke(Object obj) {
                                                                                Unit CoreTextField$lambda$26$lambda$25;
                                                                                CoreTextField$lambda$26$lambda$25 = CoreTextFieldKt.CoreTextField$lambda$26$lambda$25(LegacyTextFieldState.this, textFieldValue2, offsetMapping, (DrawScope) obj);
                                                                                return CoreTextField$lambda$26$lambda$25;
                                                                            }
                                                                        };
                                                                        startRestartGroup.updateRememberedValue(rememberedValue4);
                                                                    } else {
                                                                        textFieldValue2 = textFieldValue;
                                                                    }
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    final Modifier drawBehind = DrawModifierKt.drawBehind(companion4, (Function1) rememberedValue4);
                                                                    Modifier.Companion companion5 = Modifier.INSTANCE;
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213484761, "CC(remember):CoreTextField.kt#9igjgp");
                                                                    windowInfo2 = windowInfo;
                                                                    changedInstance5 = (i43 != 2048) | startRestartGroup.changedInstance(legacyTextFieldState2) | startRestartGroup.changed(windowInfo2) | startRestartGroup.changedInstance(textFieldSelectionManager2) | (i42 != 4) | startRestartGroup.changedInstance(offsetMapping);
                                                                    rememberedValue5 = startRestartGroup.rememberedValue();
                                                                    if (!changedInstance5 || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                                                        final TextFieldValue textFieldValue3 = textFieldValue2;
                                                                        final OffsetMapping offsetMapping4 = offsetMapping;
                                                                        final TextFieldSelectionManager textFieldSelectionManager5 = textFieldSelectionManager2;
                                                                        final LegacyTextFieldState legacyTextFieldState6 = legacyTextFieldState2;
                                                                        rememberedValue5 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda0
                                                                            @Override // kotlin.jvm.functions.Function1
                                                                            public final Object invoke(Object obj) {
                                                                                Unit CoreTextField$lambda$30$lambda$29;
                                                                                CoreTextField$lambda$30$lambda$29 = CoreTextFieldKt.CoreTextField$lambda$30$lambda$29(LegacyTextFieldState.this, z18, windowInfo2, textFieldSelectionManager5, textFieldValue3, offsetMapping4, (LayoutCoordinates) obj);
                                                                                return CoreTextField$lambda$30$lambda$29;
                                                                            }
                                                                        };
                                                                        z19 = z18;
                                                                        windowInfo3 = windowInfo2;
                                                                        startRestartGroup.updateRememberedValue(rememberedValue5);
                                                                    } else {
                                                                        z19 = z18;
                                                                        windowInfo3 = windowInfo2;
                                                                    }
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    final Modifier onGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(companion5, (Function1) rememberedValue5);
                                                                    final VisualTransformation visualTransformation5 = visualTransformation4;
                                                                    boolean z29 = z19;
                                                                    CoreTextFieldSemanticsModifier coreTextFieldSemanticsModifier = new CoreTextFieldSemanticsModifier(transformedText, textFieldValue, legacyTextFieldState2, z12, z29, visualTransformation5 instanceof PasswordVisualTransformation, offsetMapping, textFieldSelectionManager2, imeOptions6, focusRequester3);
                                                                    Brush brush5 = brush4;
                                                                    final OffsetMapping offsetMapping5 = offsetMapping;
                                                                    legacyTextFieldState3 = legacyTextFieldState2;
                                                                    Modifier cursor = TextFieldCursorKt.cursor(Modifier.INSTANCE, legacyTextFieldState3, textFieldValue, offsetMapping5, brush5, (z29 || z12 || !windowInfo3.isWindowFocused() || legacyTextFieldState2.hasHighlight()) ? false : true);
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213415343, "CC(remember):CoreTextField.kt#9igjgp");
                                                                    changedInstance6 = startRestartGroup.changedInstance(textFieldSelectionManager2);
                                                                    Object rememberedValue20 = startRestartGroup.rememberedValue();
                                                                    if (changedInstance6) {
                                                                        modifier5 = cursor;
                                                                    } else {
                                                                        modifier5 = cursor;
                                                                    }
                                                                    rememberedValue20 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda1
                                                                        @Override // kotlin.jvm.functions.Function1
                                                                        public final Object invoke(Object obj) {
                                                                            DisposableEffectResult CoreTextField$lambda$33$lambda$32;
                                                                            CoreTextField$lambda$33$lambda$32 = CoreTextFieldKt.CoreTextField$lambda$33$lambda$32(TextFieldSelectionManager.this, (DisposableEffectScope) obj);
                                                                            return CoreTextField$lambda$33$lambda$32;
                                                                        }
                                                                    };
                                                                    startRestartGroup.updateRememberedValue(rememberedValue20);
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    EffectsKt.DisposableEffect(textFieldSelectionManager2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue20, startRestartGroup, 0);
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213412232, "CC(remember):CoreTextField.kt#9igjgp");
                                                                    changedInstance7 = startRestartGroup.changedInstance(legacyTextFieldState3) | startRestartGroup.changedInstance(textInputService) | (i42 != 4) | ((i46 <= 32 && startRestartGroup.changed(imeOptions6)) || (i44 & 48) == 32);
                                                                    rememberedValue6 = startRestartGroup.rememberedValue();
                                                                    if (!changedInstance7 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                                        rememberedValue6 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda2
                                                                            @Override // kotlin.jvm.functions.Function1
                                                                            public final Object invoke(Object obj) {
                                                                                DisposableEffectResult CoreTextField$lambda$36$lambda$35;
                                                                                CoreTextField$lambda$36$lambda$35 = CoreTextFieldKt.CoreTextField$lambda$36$lambda$35(LegacyTextFieldState.this, textInputService, textFieldValue, imeOptions6, (DisposableEffectScope) obj);
                                                                                return CoreTextField$lambda$36$lambda$35;
                                                                            }
                                                                        };
                                                                        startRestartGroup.updateRememberedValue(rememberedValue6);
                                                                    }
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    EffectsKt.DisposableEffect(imeOptions6, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue6, startRestartGroup, (i44 >> 3) & 14);
                                                                    int i47 = i33;
                                                                    Modifier m1408textFieldKeyInput2WJ9YEU = TextFieldKeyInputKt.m1408textFieldKeyInput2WJ9YEU(Modifier.INSTANCE, legacyTextFieldState3, textFieldSelectionManager2, textFieldValue, legacyTextFieldState3.getOnValueChange(), !z12, i47 != 1, offsetMapping5, undoManager, imeOptions6.getImeAction());
                                                                    final boolean z30 = KeyboardType.m7073equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m7092getPasswordPjHm6EE()) && !KeyboardType.m7073equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m7091getNumberPasswordPjHm6EE());
                                                                    Modifier.Companion companion6 = Modifier.INSTANCE;
                                                                    boolean CoreTextField$lambda$16 = CoreTextField$lambda$16(state2);
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213374084, "CC(remember):CoreTextField.kt#9igjgp");
                                                                    final LegacyPlatformTextInputServiceAdapter legacyPlatformTextInputServiceAdapter3 = legacyPlatformTextInputServiceAdapter;
                                                                    changed2 = startRestartGroup.changed(z30) | startRestartGroup.changedInstance(legacyPlatformTextInputServiceAdapter3);
                                                                    Object rememberedValue21 = startRestartGroup.rememberedValue();
                                                                    if (changed2) {
                                                                        i34 = i47;
                                                                    } else {
                                                                        i34 = i47;
                                                                    }
                                                                    rememberedValue21 = new Function0() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda3
                                                                        @Override // kotlin.jvm.functions.Function0
                                                                        public final Object invoke() {
                                                                            Unit CoreTextField$lambda$38$lambda$37;
                                                                            CoreTextField$lambda$38$lambda$37 = CoreTextFieldKt.CoreTextField$lambda$38$lambda$37(z30, legacyPlatformTextInputServiceAdapter3);
                                                                            return CoreTextField$lambda$38$lambda$37;
                                                                        }
                                                                    };
                                                                    startRestartGroup.updateRememberedValue(rememberedValue21);
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    Modifier stylusHandwriting = StylusHandwritingKt.stylusHandwriting(companion6, CoreTextField$lambda$16, z30, (Function0) rememberedValue21);
                                                                    ProvidableCompositionLocal<Color> localAutofillHighlightColor = AutofillHighlightKt.getLocalAutofillHighlightColor();
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, str);
                                                                    Object consume10 = startRestartGroup.consume(localAutofillHighlightColor);
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    final long m4625unboximpl = ((Color) consume10).m4625unboximpl();
                                                                    Modifier.Companion companion7 = Modifier.INSTANCE;
                                                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213339112, "CC(remember):CoreTextField.kt#9igjgp");
                                                                    changedInstance8 = startRestartGroup.changedInstance(legacyTextFieldState3) | startRestartGroup.changed(m4625unboximpl);
                                                                    rememberedValue7 = startRestartGroup.rememberedValue();
                                                                    if (!changedInstance8 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                                                        rememberedValue7 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda9
                                                                            @Override // kotlin.jvm.functions.Function1
                                                                            public final Object invoke(Object obj) {
                                                                                Unit CoreTextField$lambda$40$lambda$39;
                                                                                CoreTextField$lambda$40$lambda$39 = CoreTextFieldKt.CoreTextField$lambda$40$lambda$39(LegacyTextFieldState.this, m4625unboximpl, (DrawScope) obj);
                                                                                return CoreTextField$lambda$40$lambda$39;
                                                                            }
                                                                        };
                                                                        startRestartGroup.updateRememberedValue(rememberedValue7);
                                                                    }
                                                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                    final TextFieldScrollerPosition textFieldScrollerPosition6 = textFieldScrollerPosition5;
                                                                    Modifier addContextMenuComponents = addContextMenuComponents(OnGloballyPositionedModifierKt.onGloballyPositioned(TextFieldScrollKt.textFieldScrollable(previewKeyEventToDeselectOnBack(TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus(LegacyAdaptingPlatformTextInputModifierNodeKt.legacyTextInputAdapter(modifier6.then(DrawModifierKt.drawBehind(companion7, (Function1) rememberedValue7)), legacyPlatformTextInputServiceAdapter3, legacyTextFieldState3, textFieldSelectionManager2).then(stylusHandwriting).then(textFieldFocusModifier), legacyTextFieldState3, focusManager), legacyTextFieldState3, textFieldSelectionManager2).then(m1408textFieldKeyInput2WJ9YEU), textFieldScrollerPosition6, mutableInteractionSource7, z29).then(pointerHoverIcon$default).then(coreTextFieldSemanticsModifier), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$decorationBoxModifier$1
                                                                        @Override // kotlin.jvm.functions.Function1
                                                                        public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                                                                            invoke2(layoutCoordinates);
                                                                            return Unit.INSTANCE;
                                                                        }

                                                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                        public final void invoke2(LayoutCoordinates layoutCoordinates) {
                                                                            TextLayoutResultProxy layoutResult = LegacyTextFieldState.this.getLayoutResult();
                                                                            if (layoutResult != null) {
                                                                                layoutResult.setDecorationBoxCoordinates(layoutCoordinates);
                                                                            }
                                                                        }
                                                                    }), textFieldSelectionManager2, coroutineScope);
                                                                    z20 = !z29 && legacyTextFieldState3.getHasFocus() && legacyTextFieldState3.isInTouchMode() && windowInfo3.isWindowFocused();
                                                                    if (!z20) {
                                                                        companion = TextFieldSelectionManager_androidKt.textFieldMagnifier(Modifier.INSTANCE, textFieldSelectionManager2);
                                                                    } else {
                                                                        companion = Modifier.INSTANCE;
                                                                    }
                                                                    final Modifier modifier7 = companion;
                                                                    final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function33 = lambda$559628295$foundation_release;
                                                                    final int i48 = i34;
                                                                    final TextStyle textStyle6 = textStyle4;
                                                                    final Modifier modifier8 = modifier5;
                                                                    final TextFieldSelectionManager textFieldSelectionManager6 = textFieldSelectionManager2;
                                                                    final BringIntoViewRequester bringIntoViewRequester3 = bringIntoViewRequester;
                                                                    final boolean z31 = z12;
                                                                    CoreTextFieldRootBox(addContextMenuComponents, textFieldSelectionManager6, ComposableLambdaKt.rememberComposableLambda(-814563849, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5

                                                                        /* compiled from: CoreTextField.kt */
                                                                        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                                                                        /* renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1, reason: invalid class name */
                                                                        static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
                                                                            final /* synthetic */ BringIntoViewRequester $bringIntoViewRequester;
                                                                            final /* synthetic */ Modifier $cursorModifier;
                                                                            final /* synthetic */ Density $density;
                                                                            final /* synthetic */ Modifier $drawModifier;
                                                                            final /* synthetic */ Modifier $magnifierModifier;
                                                                            final /* synthetic */ TextFieldSelectionManager $manager;
                                                                            final /* synthetic */ int $maxLines;
                                                                            final /* synthetic */ int $minLines;
                                                                            final /* synthetic */ OffsetMapping $offsetMapping;
                                                                            final /* synthetic */ Modifier $onPositionedModifier;
                                                                            final /* synthetic */ Function1<TextLayoutResult, Unit> $onTextLayout;
                                                                            final /* synthetic */ boolean $readOnly;
                                                                            final /* synthetic */ TextFieldScrollerPosition $scrollerPosition;
                                                                            final /* synthetic */ boolean $showHandleAndMagnifier;
                                                                            final /* synthetic */ LegacyTextFieldState $state;
                                                                            final /* synthetic */ TextStyle $textStyle;
                                                                            final /* synthetic */ TextFieldValue $value;
                                                                            final /* synthetic */ VisualTransformation $visualTransformation;

                                                                            /* JADX WARN: Multi-variable type inference failed */
                                                                            AnonymousClass1(LegacyTextFieldState legacyTextFieldState, TextStyle textStyle, int i, int i2, TextFieldScrollerPosition textFieldScrollerPosition, TextFieldValue textFieldValue, VisualTransformation visualTransformation, Modifier modifier, Modifier modifier2, Modifier modifier3, Modifier modifier4, BringIntoViewRequester bringIntoViewRequester, TextFieldSelectionManager textFieldSelectionManager, boolean z, boolean z2, Function1<? super TextLayoutResult, Unit> function1, OffsetMapping offsetMapping, Density density) {
                                                                                this.$state = legacyTextFieldState;
                                                                                this.$textStyle = textStyle;
                                                                                this.$minLines = i;
                                                                                this.$maxLines = i2;
                                                                                this.$scrollerPosition = textFieldScrollerPosition;
                                                                                this.$value = textFieldValue;
                                                                                this.$visualTransformation = visualTransformation;
                                                                                this.$cursorModifier = modifier;
                                                                                this.$drawModifier = modifier2;
                                                                                this.$onPositionedModifier = modifier3;
                                                                                this.$magnifierModifier = modifier4;
                                                                                this.$bringIntoViewRequester = bringIntoViewRequester;
                                                                                this.$manager = textFieldSelectionManager;
                                                                                this.$showHandleAndMagnifier = z;
                                                                                this.$readOnly = z2;
                                                                                this.$onTextLayout = function1;
                                                                                this.$offsetMapping = offsetMapping;
                                                                                this.$density = density;
                                                                            }

                                                                            @Override // kotlin.jvm.functions.Function2
                                                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                                                invoke(composer, num.intValue());
                                                                                return Unit.INSTANCE;
                                                                            }

                                                                            public final void invoke(Composer composer, int i) {
                                                                                ComposerKt.sourceInformation(composer, "C582@27771L22,591@28158L4131,591@28122L4167:CoreTextField.kt#423gt5");
                                                                                if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                                                                                    composer.skipToGroupEnd();
                                                                                    return;
                                                                                }
                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                    ComposerKt.traceEventStart(-44346382, i, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:571)");
                                                                                }
                                                                                Modifier heightInLines = HeightInLinesModifierKt.heightInLines(SizeKt.m918heightInVpY3zN4$default(Modifier.INSTANCE, this.$state.m1323getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), this.$textStyle, this.$minLines, this.$maxLines);
                                                                                TextFieldScrollerPosition textFieldScrollerPosition = this.$scrollerPosition;
                                                                                TextFieldValue textFieldValue = this.$value;
                                                                                VisualTransformation visualTransformation = this.$visualTransformation;
                                                                                ComposerKt.sourceInformationMarkerStart(composer, 332827112, "CC(remember):CoreTextField.kt#9igjgp");
                                                                                boolean changedInstance = composer.changedInstance(this.$state);
                                                                                final LegacyTextFieldState legacyTextFieldState = this.$state;
                                                                                Object rememberedValue = composer.rememberedValue();
                                                                                if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                                                                    rememberedValue = 
                                                                                    /*  JADX ERROR: Method code generation error
                                                                                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0066: CONSTRUCTOR (r7v1 'rememberedValue' java.lang.Object) = (r6v1 'legacyTextFieldState' androidx.compose.foundation.text.LegacyTextFieldState A[DONT_INLINE]) A[MD:(androidx.compose.foundation.text.LegacyTextFieldState):void (m)] (LINE:583) call: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0.<init>(androidx.compose.foundation.text.LegacyTextFieldState):void type: CONSTRUCTOR in method: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5.1.invoke(androidx.compose.runtime.Composer, int):void, file: classes.dex
                                                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                                                                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                                                                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                                                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                                                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                                                                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                                                                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                                                                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                                                                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                                                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                                                                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                                                                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                                                                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                                                                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                                                                                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:305)
                                                                                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                                                                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                                                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                                                                        	... 25 more
                                                                                        */
                                                                                    /*
                                                                                        this = this;
                                                                                        java.lang.String r0 = "C582@27771L22,591@28158L4131,591@28122L4167:CoreTextField.kt#423gt5"
                                                                                        androidx.compose.runtime.ComposerKt.sourceInformation(r14, r0)
                                                                                        r0 = r15 & 3
                                                                                        r1 = 0
                                                                                        r2 = 1
                                                                                        r3 = 2
                                                                                        if (r0 == r3) goto Le
                                                                                        r0 = r2
                                                                                        goto Lf
                                                                                    Le:
                                                                                        r0 = r1
                                                                                    Lf:
                                                                                        r4 = r15 & 1
                                                                                        boolean r0 = r14.shouldExecute(r0, r4)
                                                                                        if (r0 == 0) goto Lca
                                                                                        boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                                                                        if (r0 == 0) goto L26
                                                                                        r0 = -1
                                                                                        java.lang.String r4 = "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:571)"
                                                                                        r5 = -44346382(0xfffffffffd5b53f2, float:-1.822105E37)
                                                                                        androidx.compose.runtime.ComposerKt.traceEventStart(r5, r15, r0, r4)
                                                                                    L26:
                                                                                        androidx.compose.ui.Modifier$Companion r15 = androidx.compose.ui.Modifier.INSTANCE
                                                                                        androidx.compose.ui.Modifier r15 = (androidx.compose.ui.Modifier) r15
                                                                                        androidx.compose.foundation.text.LegacyTextFieldState r0 = r13.$state
                                                                                        float r0 = r0.m1323getMinHeightForSingleLineFieldD9Ej5fM()
                                                                                        r4 = 0
                                                                                        r5 = 0
                                                                                        androidx.compose.ui.Modifier r15 = androidx.compose.foundation.layout.SizeKt.m918heightInVpY3zN4$default(r15, r0, r4, r3, r5)
                                                                                        androidx.compose.ui.text.TextStyle r0 = r13.$textStyle
                                                                                        int r3 = r13.$minLines
                                                                                        int r4 = r13.$maxLines
                                                                                        androidx.compose.ui.Modifier r15 = androidx.compose.foundation.text.HeightInLinesModifierKt.heightInLines(r15, r0, r3, r4)
                                                                                        androidx.compose.foundation.text.TextFieldScrollerPosition r0 = r13.$scrollerPosition
                                                                                        androidx.compose.ui.text.input.TextFieldValue r3 = r13.$value
                                                                                        androidx.compose.ui.text.input.VisualTransformation r4 = r13.$visualTransformation
                                                                                        r5 = 332827112(0x13d689e8, float:5.415718E-27)
                                                                                        java.lang.String r6 = "CC(remember):CoreTextField.kt#9igjgp"
                                                                                        androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r14, r5, r6)
                                                                                        androidx.compose.foundation.text.LegacyTextFieldState r5 = r13.$state
                                                                                        boolean r5 = r14.changedInstance(r5)
                                                                                        androidx.compose.foundation.text.LegacyTextFieldState r6 = r13.$state
                                                                                        java.lang.Object r7 = r14.rememberedValue()
                                                                                        if (r5 != 0) goto L64
                                                                                        androidx.compose.runtime.Composer$Companion r5 = androidx.compose.runtime.Composer.INSTANCE
                                                                                        java.lang.Object r5 = r5.getEmpty()
                                                                                        if (r7 != r5) goto L6c
                                                                                    L64:
                                                                                        androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0 r7 = new androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0
                                                                                        r7.<init>(r6)
                                                                                        r14.updateRememberedValue(r7)
                                                                                    L6c:
                                                                                        kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
                                                                                        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r14)
                                                                                        androidx.compose.ui.Modifier r15 = androidx.compose.foundation.text.TextFieldScroll_androidKt.textFieldScroll(r15, r0, r3, r4, r7)
                                                                                        androidx.compose.ui.Modifier r0 = r13.$cursorModifier
                                                                                        androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                                        androidx.compose.ui.Modifier r0 = r13.$drawModifier
                                                                                        androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                                        androidx.compose.ui.text.TextStyle r0 = r13.$textStyle
                                                                                        androidx.compose.ui.Modifier r15 = androidx.compose.foundation.text.TextFieldSizeKt.textFieldMinSize(r15, r0)
                                                                                        androidx.compose.ui.Modifier r0 = r13.$onPositionedModifier
                                                                                        androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                                        androidx.compose.ui.Modifier r0 = r13.$magnifierModifier
                                                                                        androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                                        androidx.compose.foundation.relocation.BringIntoViewRequester r0 = r13.$bringIntoViewRequester
                                                                                        androidx.compose.ui.Modifier r15 = androidx.compose.foundation.relocation.BringIntoViewRequesterKt.bringIntoViewRequester(r15, r0)
                                                                                        androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1 r3 = new androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1
                                                                                        androidx.compose.foundation.text.selection.TextFieldSelectionManager r4 = r13.$manager
                                                                                        androidx.compose.foundation.text.LegacyTextFieldState r5 = r13.$state
                                                                                        boolean r6 = r13.$showHandleAndMagnifier
                                                                                        boolean r7 = r13.$readOnly
                                                                                        kotlin.jvm.functions.Function1<androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r8 = r13.$onTextLayout
                                                                                        androidx.compose.ui.text.input.TextFieldValue r9 = r13.$value
                                                                                        androidx.compose.ui.text.input.OffsetMapping r10 = r13.$offsetMapping
                                                                                        androidx.compose.ui.unit.Density r11 = r13.$density
                                                                                        int r12 = r13.$maxLines
                                                                                        r3.<init>()
                                                                                        r0 = 54
                                                                                        r4 = 1412697320(0x54340ce8, float:3.09324258E12)
                                                                                        androidx.compose.runtime.internal.ComposableLambda r0 = androidx.compose.runtime.internal.ComposableLambdaKt.rememberComposableLambda(r4, r2, r3, r14, r0)
                                                                                        kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
                                                                                        r2 = 48
                                                                                        androidx.compose.foundation.text.selection.SimpleLayoutKt.SimpleLayout(r15, r0, r14, r2, r1)
                                                                                        boolean r14 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                                                                        if (r14 == 0) goto Lc9
                                                                                        androidx.compose.runtime.ComposerKt.traceEventEnd()
                                                                                    Lc9:
                                                                                        return
                                                                                    Lca:
                                                                                        r14.skipToGroupEnd()
                                                                                        return
                                                                                    */
                                                                                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5.AnonymousClass1.invoke(androidx.compose.runtime.Composer, int):void");
                                                                                }
                                                                            }

                                                                            @Override // kotlin.jvm.functions.Function2
                                                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                                                invoke(composer3, num.intValue());
                                                                                return Unit.INSTANCE;
                                                                            }

                                                                            public final void invoke(Composer composer3, int i49) {
                                                                                ComposerKt.sourceInformation(composer3, "C568@26920L5379,568@26906L5393:CoreTextField.kt#423gt5");
                                                                                if (!composer3.shouldExecute((i49 & 3) != 2, i49 & 1)) {
                                                                                    composer3.skipToGroupEnd();
                                                                                    return;
                                                                                }
                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                    ComposerKt.traceEventStart(-814563849, i49, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:568)");
                                                                                }
                                                                                function33.invoke(ComposableLambdaKt.rememberComposableLambda(-44346382, true, new AnonymousClass1(legacyTextFieldState3, textStyle6, i41, i48, textFieldScrollerPosition6, textFieldValue, visualTransformation5, modifier8, drawBehind, onGloballyPositioned, modifier7, bringIntoViewRequester3, textFieldSelectionManager6, z20, z31, function15, offsetMapping5, density), composer3, 54), composer3, 6);
                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                    ComposerKt.traceEventEnd();
                                                                                }
                                                                            }
                                                                        }, startRestartGroup, 54), startRestartGroup, 384);
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventEnd();
                                                                        }
                                                                        function32 = function33;
                                                                        i28 = i41;
                                                                        composer2 = startRestartGroup;
                                                                        function14 = function15;
                                                                        imeOptions2 = imeOptions6;
                                                                        textFieldScrollerPosition2 = textFieldScrollerPosition3;
                                                                        mutableInteractionSource3 = mutableInteractionSource7;
                                                                        keyboardActions2 = keyboardActions4;
                                                                        brush2 = brush5;
                                                                        z6 = z29;
                                                                        modifier3 = modifier6;
                                                                        i29 = i34;
                                                                        z5 = z10;
                                                                        textStyle3 = textStyle4;
                                                                        z4 = z12;
                                                                        visualTransformation2 = visualTransformation5;
                                                                    }
                                                                } else {
                                                                    z14 = changed;
                                                                }
                                                                z15 = true;
                                                                z16 = z14 | z15;
                                                                rememberedValue = startRestartGroup.rememberedValue();
                                                                if (z16) {
                                                                }
                                                                CoreTextFieldKt$CoreTextField$2$1 coreTextFieldKt$CoreTextField$2$12 = new CoreTextFieldKt$CoreTextField$2$1(legacyTextFieldState2, rememberUpdatedState, textInputService, textFieldSelectionManager2, imeOptions5, null);
                                                                state = rememberUpdatedState;
                                                                rememberedValue = coreTextFieldKt$CoreTextField$2$12;
                                                                startRestartGroup.updateRememberedValue(rememberedValue);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                State state22 = state;
                                                                EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue, startRestartGroup, 6);
                                                                Modifier.Companion companion32 = Modifier.INSTANCE;
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213551203, "CC(remember):CoreTextField.kt#9igjgp");
                                                                changedInstance2 = startRestartGroup.changedInstance(legacyTextFieldState2);
                                                                rememberedValue2 = startRestartGroup.rememberedValue();
                                                                if (!changedInstance2) {
                                                                }
                                                                rememberedValue2 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda13
                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public final Object invoke(Object obj) {
                                                                        Unit CoreTextField$lambda$19$lambda$18;
                                                                        CoreTextField$lambda$19$lambda$18 = CoreTextFieldKt.CoreTextField$lambda$19$lambda$18(LegacyTextFieldState.this, ((Boolean) obj).booleanValue());
                                                                        return CoreTextField$lambda$19$lambda$18;
                                                                    }
                                                                };
                                                                startRestartGroup.updateRememberedValue(rememberedValue2);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                Modifier updateSelectionTouchMode2 = SelectionGesturesKt.updateSelectionTouchMode(companion32, (Function1) rememberedValue2);
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213547167, "CC(remember):CoreTextField.kt#9igjgp");
                                                                changedInstance3 = startRestartGroup.changedInstance(legacyTextFieldState2) | (i45 != 16384) | (i43 != 2048) | startRestartGroup.changedInstance(offsetMapping) | startRestartGroup.changedInstance(textFieldSelectionManager2);
                                                                rememberedValue3 = startRestartGroup.rememberedValue();
                                                                if (changedInstance3) {
                                                                }
                                                                final OffsetMapping offsetMapping32 = offsetMapping;
                                                                final TextFieldSelectionManager textFieldSelectionManager42 = textFieldSelectionManager2;
                                                                final LegacyTextFieldState legacyTextFieldState52 = legacyTextFieldState2;
                                                                final boolean z282 = z12;
                                                                rememberedValue3 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda14
                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public final Object invoke(Object obj) {
                                                                        Unit CoreTextField$lambda$22$lambda$21;
                                                                        CoreTextField$lambda$22$lambda$21 = CoreTextFieldKt.CoreTextField$lambda$22$lambda$21(LegacyTextFieldState.this, focusRequester2, z282, z27, textFieldSelectionManager42, offsetMapping32, (Offset) obj);
                                                                        return CoreTextField$lambda$22$lambda$21;
                                                                    }
                                                                };
                                                                focusRequester = focusRequester2;
                                                                z17 = z27;
                                                                startRestartGroup.updateRememberedValue(rememberedValue3);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                FocusRequester focusRequester32 = focusRequester;
                                                                imeOptions6 = imeOptions5;
                                                                z18 = z17;
                                                                Modifier pointerHoverIcon$default2 = PointerIconKt.pointerHoverIcon$default(SelectionGesturesKt.selectionGestureInput(TextFieldPressGestureFilterKt.tapPressTextFieldModifier(updateSelectionTouchMode2, mutableInteractionSource7, z17, (Function1) rememberedValue3), textFieldSelectionManager2.getMouseSelectionObserver(), textFieldSelectionManager2.getTouchSelectionObserver()), PointerIcon.INSTANCE.getText(), false, 2, null);
                                                                Modifier.Companion companion42 = Modifier.INSTANCE;
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213506526, "CC(remember):CoreTextField.kt#9igjgp");
                                                                changedInstance4 = startRestartGroup.changedInstance(legacyTextFieldState2) | (i42 != 4) | startRestartGroup.changedInstance(offsetMapping);
                                                                rememberedValue4 = startRestartGroup.rememberedValue();
                                                                if (changedInstance4) {
                                                                }
                                                                textFieldValue2 = textFieldValue;
                                                                rememberedValue4 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda15
                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public final Object invoke(Object obj) {
                                                                        Unit CoreTextField$lambda$26$lambda$25;
                                                                        CoreTextField$lambda$26$lambda$25 = CoreTextFieldKt.CoreTextField$lambda$26$lambda$25(LegacyTextFieldState.this, textFieldValue2, offsetMapping, (DrawScope) obj);
                                                                        return CoreTextField$lambda$26$lambda$25;
                                                                    }
                                                                };
                                                                startRestartGroup.updateRememberedValue(rememberedValue4);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                final Modifier drawBehind2 = DrawModifierKt.drawBehind(companion42, (Function1) rememberedValue4);
                                                                Modifier.Companion companion52 = Modifier.INSTANCE;
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213484761, "CC(remember):CoreTextField.kt#9igjgp");
                                                                windowInfo2 = windowInfo;
                                                                changedInstance5 = (i43 != 2048) | startRestartGroup.changedInstance(legacyTextFieldState2) | startRestartGroup.changed(windowInfo2) | startRestartGroup.changedInstance(textFieldSelectionManager2) | (i42 != 4) | startRestartGroup.changedInstance(offsetMapping);
                                                                rememberedValue5 = startRestartGroup.rememberedValue();
                                                                if (changedInstance5) {
                                                                }
                                                                final TextFieldValue textFieldValue32 = textFieldValue2;
                                                                final OffsetMapping offsetMapping42 = offsetMapping;
                                                                final TextFieldSelectionManager textFieldSelectionManager52 = textFieldSelectionManager2;
                                                                final LegacyTextFieldState legacyTextFieldState62 = legacyTextFieldState2;
                                                                rememberedValue5 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda0
                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public final Object invoke(Object obj) {
                                                                        Unit CoreTextField$lambda$30$lambda$29;
                                                                        CoreTextField$lambda$30$lambda$29 = CoreTextFieldKt.CoreTextField$lambda$30$lambda$29(LegacyTextFieldState.this, z18, windowInfo2, textFieldSelectionManager52, textFieldValue32, offsetMapping42, (LayoutCoordinates) obj);
                                                                        return CoreTextField$lambda$30$lambda$29;
                                                                    }
                                                                };
                                                                z19 = z18;
                                                                windowInfo3 = windowInfo2;
                                                                startRestartGroup.updateRememberedValue(rememberedValue5);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                final Modifier onGloballyPositioned2 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion52, (Function1) rememberedValue5);
                                                                final VisualTransformation visualTransformation52 = visualTransformation4;
                                                                boolean z292 = z19;
                                                                CoreTextFieldSemanticsModifier coreTextFieldSemanticsModifier2 = new CoreTextFieldSemanticsModifier(transformedText, textFieldValue, legacyTextFieldState2, z12, z292, visualTransformation52 instanceof PasswordVisualTransformation, offsetMapping, textFieldSelectionManager2, imeOptions6, focusRequester32);
                                                                Brush brush52 = brush4;
                                                                final OffsetMapping offsetMapping52 = offsetMapping;
                                                                legacyTextFieldState3 = legacyTextFieldState2;
                                                                Modifier cursor2 = TextFieldCursorKt.cursor(Modifier.INSTANCE, legacyTextFieldState3, textFieldValue, offsetMapping52, brush52, (z292 || z12 || !windowInfo3.isWindowFocused() || legacyTextFieldState2.hasHighlight()) ? false : true);
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213415343, "CC(remember):CoreTextField.kt#9igjgp");
                                                                changedInstance6 = startRestartGroup.changedInstance(textFieldSelectionManager2);
                                                                Object rememberedValue202 = startRestartGroup.rememberedValue();
                                                                if (changedInstance6) {
                                                                }
                                                                rememberedValue202 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda1
                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public final Object invoke(Object obj) {
                                                                        DisposableEffectResult CoreTextField$lambda$33$lambda$32;
                                                                        CoreTextField$lambda$33$lambda$32 = CoreTextFieldKt.CoreTextField$lambda$33$lambda$32(TextFieldSelectionManager.this, (DisposableEffectScope) obj);
                                                                        return CoreTextField$lambda$33$lambda$32;
                                                                    }
                                                                };
                                                                startRestartGroup.updateRememberedValue(rememberedValue202);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                EffectsKt.DisposableEffect(textFieldSelectionManager2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue202, startRestartGroup, 0);
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213412232, "CC(remember):CoreTextField.kt#9igjgp");
                                                                changedInstance7 = startRestartGroup.changedInstance(legacyTextFieldState3) | startRestartGroup.changedInstance(textInputService) | (i42 != 4) | ((i46 <= 32 && startRestartGroup.changed(imeOptions6)) || (i44 & 48) == 32);
                                                                rememberedValue6 = startRestartGroup.rememberedValue();
                                                                if (!changedInstance7) {
                                                                }
                                                                rememberedValue6 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda2
                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public final Object invoke(Object obj) {
                                                                        DisposableEffectResult CoreTextField$lambda$36$lambda$35;
                                                                        CoreTextField$lambda$36$lambda$35 = CoreTextFieldKt.CoreTextField$lambda$36$lambda$35(LegacyTextFieldState.this, textInputService, textFieldValue, imeOptions6, (DisposableEffectScope) obj);
                                                                        return CoreTextField$lambda$36$lambda$35;
                                                                    }
                                                                };
                                                                startRestartGroup.updateRememberedValue(rememberedValue6);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                EffectsKt.DisposableEffect(imeOptions6, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue6, startRestartGroup, (i44 >> 3) & 14);
                                                                int i472 = i33;
                                                                Modifier m1408textFieldKeyInput2WJ9YEU2 = TextFieldKeyInputKt.m1408textFieldKeyInput2WJ9YEU(Modifier.INSTANCE, legacyTextFieldState3, textFieldSelectionManager2, textFieldValue, legacyTextFieldState3.getOnValueChange(), !z12, i472 != 1, offsetMapping52, undoManager, imeOptions6.getImeAction());
                                                                if (KeyboardType.m7073equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m7092getPasswordPjHm6EE())) {
                                                                }
                                                                Modifier.Companion companion62 = Modifier.INSTANCE;
                                                                boolean CoreTextField$lambda$162 = CoreTextField$lambda$16(state22);
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213374084, "CC(remember):CoreTextField.kt#9igjgp");
                                                                final LegacyPlatformTextInputServiceAdapter legacyPlatformTextInputServiceAdapter32 = legacyPlatformTextInputServiceAdapter;
                                                                changed2 = startRestartGroup.changed(z30) | startRestartGroup.changedInstance(legacyPlatformTextInputServiceAdapter32);
                                                                Object rememberedValue212 = startRestartGroup.rememberedValue();
                                                                if (changed2) {
                                                                }
                                                                rememberedValue212 = new Function0() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda3
                                                                    @Override // kotlin.jvm.functions.Function0
                                                                    public final Object invoke() {
                                                                        Unit CoreTextField$lambda$38$lambda$37;
                                                                        CoreTextField$lambda$38$lambda$37 = CoreTextFieldKt.CoreTextField$lambda$38$lambda$37(z30, legacyPlatformTextInputServiceAdapter32);
                                                                        return CoreTextField$lambda$38$lambda$37;
                                                                    }
                                                                };
                                                                startRestartGroup.updateRememberedValue(rememberedValue212);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                Modifier stylusHandwriting2 = StylusHandwritingKt.stylusHandwriting(companion62, CoreTextField$lambda$162, z30, (Function0) rememberedValue212);
                                                                ProvidableCompositionLocal<Color> localAutofillHighlightColor2 = AutofillHighlightKt.getLocalAutofillHighlightColor();
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, str);
                                                                Object consume102 = startRestartGroup.consume(localAutofillHighlightColor2);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                final long m4625unboximpl2 = ((Color) consume102).m4625unboximpl();
                                                                Modifier.Companion companion72 = Modifier.INSTANCE;
                                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213339112, "CC(remember):CoreTextField.kt#9igjgp");
                                                                changedInstance8 = startRestartGroup.changedInstance(legacyTextFieldState3) | startRestartGroup.changed(m4625unboximpl2);
                                                                rememberedValue7 = startRestartGroup.rememberedValue();
                                                                if (!changedInstance8) {
                                                                }
                                                                rememberedValue7 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda9
                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public final Object invoke(Object obj) {
                                                                        Unit CoreTextField$lambda$40$lambda$39;
                                                                        CoreTextField$lambda$40$lambda$39 = CoreTextFieldKt.CoreTextField$lambda$40$lambda$39(LegacyTextFieldState.this, m4625unboximpl2, (DrawScope) obj);
                                                                        return CoreTextField$lambda$40$lambda$39;
                                                                    }
                                                                };
                                                                startRestartGroup.updateRememberedValue(rememberedValue7);
                                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                                final TextFieldScrollerPosition textFieldScrollerPosition62 = textFieldScrollerPosition5;
                                                                Modifier addContextMenuComponents2 = addContextMenuComponents(OnGloballyPositionedModifierKt.onGloballyPositioned(TextFieldScrollKt.textFieldScrollable(previewKeyEventToDeselectOnBack(TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus(LegacyAdaptingPlatformTextInputModifierNodeKt.legacyTextInputAdapter(modifier6.then(DrawModifierKt.drawBehind(companion72, (Function1) rememberedValue7)), legacyPlatformTextInputServiceAdapter32, legacyTextFieldState3, textFieldSelectionManager2).then(stylusHandwriting2).then(textFieldFocusModifier), legacyTextFieldState3, focusManager), legacyTextFieldState3, textFieldSelectionManager2).then(m1408textFieldKeyInput2WJ9YEU2), textFieldScrollerPosition62, mutableInteractionSource7, z292).then(pointerHoverIcon$default2).then(coreTextFieldSemanticsModifier2), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$decorationBoxModifier$1
                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                                                                        invoke2(layoutCoordinates);
                                                                        return Unit.INSTANCE;
                                                                    }

                                                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                    public final void invoke2(LayoutCoordinates layoutCoordinates) {
                                                                        TextLayoutResultProxy layoutResult = LegacyTextFieldState.this.getLayoutResult();
                                                                        if (layoutResult != null) {
                                                                            layoutResult.setDecorationBoxCoordinates(layoutCoordinates);
                                                                        }
                                                                    }
                                                                }), textFieldSelectionManager2, coroutineScope);
                                                                if (z292) {
                                                                }
                                                                if (!z20) {
                                                                }
                                                                final Modifier modifier72 = companion;
                                                                final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function332 = lambda$559628295$foundation_release;
                                                                final int i482 = i34;
                                                                final TextStyle textStyle62 = textStyle4;
                                                                final Modifier modifier82 = modifier5;
                                                                final TextFieldSelectionManager textFieldSelectionManager62 = textFieldSelectionManager2;
                                                                final BringIntoViewRequester bringIntoViewRequester32 = bringIntoViewRequester;
                                                                final boolean z312 = z12;
                                                                CoreTextFieldRootBox(addContextMenuComponents2, textFieldSelectionManager62, ComposableLambdaKt.rememberComposableLambda(-814563849, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5

                                                                    /* compiled from: CoreTextField.kt */
                                                                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                                                                    /* renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1, reason: invalid class name */
                                                                    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
                                                                        final /* synthetic */ BringIntoViewRequester $bringIntoViewRequester;
                                                                        final /* synthetic */ Modifier $cursorModifier;
                                                                        final /* synthetic */ Density $density;
                                                                        final /* synthetic */ Modifier $drawModifier;
                                                                        final /* synthetic */ Modifier $magnifierModifier;
                                                                        final /* synthetic */ TextFieldSelectionManager $manager;
                                                                        final /* synthetic */ int $maxLines;
                                                                        final /* synthetic */ int $minLines;
                                                                        final /* synthetic */ OffsetMapping $offsetMapping;
                                                                        final /* synthetic */ Modifier $onPositionedModifier;
                                                                        final /* synthetic */ Function1<TextLayoutResult, Unit> $onTextLayout;
                                                                        final /* synthetic */ boolean $readOnly;
                                                                        final /* synthetic */ TextFieldScrollerPosition $scrollerPosition;
                                                                        final /* synthetic */ boolean $showHandleAndMagnifier;
                                                                        final /* synthetic */ LegacyTextFieldState $state;
                                                                        final /* synthetic */ TextStyle $textStyle;
                                                                        final /* synthetic */ TextFieldValue $value;
                                                                        final /* synthetic */ VisualTransformation $visualTransformation;

                                                                        /* JADX WARN: Multi-variable type inference failed */
                                                                        AnonymousClass1(LegacyTextFieldState legacyTextFieldState, TextStyle textStyle, int i, int i2, TextFieldScrollerPosition textFieldScrollerPosition, TextFieldValue textFieldValue, VisualTransformation visualTransformation, Modifier modifier, Modifier modifier2, Modifier modifier3, Modifier modifier4, BringIntoViewRequester bringIntoViewRequester, TextFieldSelectionManager textFieldSelectionManager, boolean z, boolean z2, Function1<? super TextLayoutResult, Unit> function1, OffsetMapping offsetMapping, Density density) {
                                                                            this.$state = legacyTextFieldState;
                                                                            this.$textStyle = textStyle;
                                                                            this.$minLines = i;
                                                                            this.$maxLines = i2;
                                                                            this.$scrollerPosition = textFieldScrollerPosition;
                                                                            this.$value = textFieldValue;
                                                                            this.$visualTransformation = visualTransformation;
                                                                            this.$cursorModifier = modifier;
                                                                            this.$drawModifier = modifier2;
                                                                            this.$onPositionedModifier = modifier3;
                                                                            this.$magnifierModifier = modifier4;
                                                                            this.$bringIntoViewRequester = bringIntoViewRequester;
                                                                            this.$manager = textFieldSelectionManager;
                                                                            this.$showHandleAndMagnifier = z;
                                                                            this.$readOnly = z2;
                                                                            this.$onTextLayout = function1;
                                                                            this.$offsetMapping = offsetMapping;
                                                                            this.$density = density;
                                                                        }

                                                                        @Override // kotlin.jvm.functions.Function2
                                                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                                            invoke(composer, num.intValue());
                                                                            return Unit.INSTANCE;
                                                                        }

                                                                        /*  JADX ERROR: Method code generation error
                                                                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0066: CONSTRUCTOR (r7v1 'rememberedValue' java.lang.Object) = (r6v1 'legacyTextFieldState' androidx.compose.foundation.text.LegacyTextFieldState A[DONT_INLINE]) A[MD:(androidx.compose.foundation.text.LegacyTextFieldState):void (m)] (LINE:583) call: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0.<init>(androidx.compose.foundation.text.LegacyTextFieldState):void type: CONSTRUCTOR in method: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5.1.invoke(androidx.compose.runtime.Composer, int):void, file: classes.dex
                                                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                                                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                                                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                                                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                                                                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:305)
                                                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                                                            	... 20 more
                                                                            */
                                                                        public final void invoke(androidx.compose.runtime.Composer r14, int r15) {
                                                                            /*
                                                                                r13 = this;
                                                                                java.lang.String r0 = "C582@27771L22,591@28158L4131,591@28122L4167:CoreTextField.kt#423gt5"
                                                                                androidx.compose.runtime.ComposerKt.sourceInformation(r14, r0)
                                                                                r0 = r15 & 3
                                                                                r1 = 0
                                                                                r2 = 1
                                                                                r3 = 2
                                                                                if (r0 == r3) goto Le
                                                                                r0 = r2
                                                                                goto Lf
                                                                            Le:
                                                                                r0 = r1
                                                                            Lf:
                                                                                r4 = r15 & 1
                                                                                boolean r0 = r14.shouldExecute(r0, r4)
                                                                                if (r0 == 0) goto Lca
                                                                                boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                                                                if (r0 == 0) goto L26
                                                                                r0 = -1
                                                                                java.lang.String r4 = "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:571)"
                                                                                r5 = -44346382(0xfffffffffd5b53f2, float:-1.822105E37)
                                                                                androidx.compose.runtime.ComposerKt.traceEventStart(r5, r15, r0, r4)
                                                                            L26:
                                                                                androidx.compose.ui.Modifier$Companion r15 = androidx.compose.ui.Modifier.INSTANCE
                                                                                androidx.compose.ui.Modifier r15 = (androidx.compose.ui.Modifier) r15
                                                                                androidx.compose.foundation.text.LegacyTextFieldState r0 = r13.$state
                                                                                float r0 = r0.m1323getMinHeightForSingleLineFieldD9Ej5fM()
                                                                                r4 = 0
                                                                                r5 = 0
                                                                                androidx.compose.ui.Modifier r15 = androidx.compose.foundation.layout.SizeKt.m918heightInVpY3zN4$default(r15, r0, r4, r3, r5)
                                                                                androidx.compose.ui.text.TextStyle r0 = r13.$textStyle
                                                                                int r3 = r13.$minLines
                                                                                int r4 = r13.$maxLines
                                                                                androidx.compose.ui.Modifier r15 = androidx.compose.foundation.text.HeightInLinesModifierKt.heightInLines(r15, r0, r3, r4)
                                                                                androidx.compose.foundation.text.TextFieldScrollerPosition r0 = r13.$scrollerPosition
                                                                                androidx.compose.ui.text.input.TextFieldValue r3 = r13.$value
                                                                                androidx.compose.ui.text.input.VisualTransformation r4 = r13.$visualTransformation
                                                                                r5 = 332827112(0x13d689e8, float:5.415718E-27)
                                                                                java.lang.String r6 = "CC(remember):CoreTextField.kt#9igjgp"
                                                                                androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r14, r5, r6)
                                                                                androidx.compose.foundation.text.LegacyTextFieldState r5 = r13.$state
                                                                                boolean r5 = r14.changedInstance(r5)
                                                                                androidx.compose.foundation.text.LegacyTextFieldState r6 = r13.$state
                                                                                java.lang.Object r7 = r14.rememberedValue()
                                                                                if (r5 != 0) goto L64
                                                                                androidx.compose.runtime.Composer$Companion r5 = androidx.compose.runtime.Composer.INSTANCE
                                                                                java.lang.Object r5 = r5.getEmpty()
                                                                                if (r7 != r5) goto L6c
                                                                            L64:
                                                                                androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0 r7 = new androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0
                                                                                r7.<init>(r6)
                                                                                r14.updateRememberedValue(r7)
                                                                            L6c:
                                                                                kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
                                                                                androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r14)
                                                                                androidx.compose.ui.Modifier r15 = androidx.compose.foundation.text.TextFieldScroll_androidKt.textFieldScroll(r15, r0, r3, r4, r7)
                                                                                androidx.compose.ui.Modifier r0 = r13.$cursorModifier
                                                                                androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                                androidx.compose.ui.Modifier r0 = r13.$drawModifier
                                                                                androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                                androidx.compose.ui.text.TextStyle r0 = r13.$textStyle
                                                                                androidx.compose.ui.Modifier r15 = androidx.compose.foundation.text.TextFieldSizeKt.textFieldMinSize(r15, r0)
                                                                                androidx.compose.ui.Modifier r0 = r13.$onPositionedModifier
                                                                                androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                                androidx.compose.ui.Modifier r0 = r13.$magnifierModifier
                                                                                androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                                androidx.compose.foundation.relocation.BringIntoViewRequester r0 = r13.$bringIntoViewRequester
                                                                                androidx.compose.ui.Modifier r15 = androidx.compose.foundation.relocation.BringIntoViewRequesterKt.bringIntoViewRequester(r15, r0)
                                                                                androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1 r3 = new androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1
                                                                                androidx.compose.foundation.text.selection.TextFieldSelectionManager r4 = r13.$manager
                                                                                androidx.compose.foundation.text.LegacyTextFieldState r5 = r13.$state
                                                                                boolean r6 = r13.$showHandleAndMagnifier
                                                                                boolean r7 = r13.$readOnly
                                                                                kotlin.jvm.functions.Function1<androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r8 = r13.$onTextLayout
                                                                                androidx.compose.ui.text.input.TextFieldValue r9 = r13.$value
                                                                                androidx.compose.ui.text.input.OffsetMapping r10 = r13.$offsetMapping
                                                                                androidx.compose.ui.unit.Density r11 = r13.$density
                                                                                int r12 = r13.$maxLines
                                                                                r3.<init>()
                                                                                r0 = 54
                                                                                r4 = 1412697320(0x54340ce8, float:3.09324258E12)
                                                                                androidx.compose.runtime.internal.ComposableLambda r0 = androidx.compose.runtime.internal.ComposableLambdaKt.rememberComposableLambda(r4, r2, r3, r14, r0)
                                                                                kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
                                                                                r2 = 48
                                                                                androidx.compose.foundation.text.selection.SimpleLayoutKt.SimpleLayout(r15, r0, r14, r2, r1)
                                                                                boolean r14 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                                                                if (r14 == 0) goto Lc9
                                                                                androidx.compose.runtime.ComposerKt.traceEventEnd()
                                                                            Lc9:
                                                                                return
                                                                            Lca:
                                                                                r14.skipToGroupEnd()
                                                                                return
                                                                            */
                                                                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5.AnonymousClass1.invoke(androidx.compose.runtime.Composer, int):void");
                                                                        }
                                                                    }

                                                                    @Override // kotlin.jvm.functions.Function2
                                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                                        invoke(composer3, num.intValue());
                                                                        return Unit.INSTANCE;
                                                                    }

                                                                    public final void invoke(Composer composer3, int i49) {
                                                                        ComposerKt.sourceInformation(composer3, "C568@26920L5379,568@26906L5393:CoreTextField.kt#423gt5");
                                                                        if (!composer3.shouldExecute((i49 & 3) != 2, i49 & 1)) {
                                                                            composer3.skipToGroupEnd();
                                                                            return;
                                                                        }
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventStart(-814563849, i49, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:568)");
                                                                        }
                                                                        function332.invoke(ComposableLambdaKt.rememberComposableLambda(-44346382, true, new AnonymousClass1(legacyTextFieldState3, textStyle62, i41, i482, textFieldScrollerPosition62, textFieldValue, visualTransformation52, modifier82, drawBehind2, onGloballyPositioned2, modifier72, bringIntoViewRequester32, textFieldSelectionManager62, z20, z312, function15, offsetMapping52, density), composer3, 54), composer3, 6);
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventEnd();
                                                                        }
                                                                    }
                                                                }, startRestartGroup, 54), startRestartGroup, 384);
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                }
                                                                function32 = function332;
                                                                i28 = i41;
                                                                composer2 = startRestartGroup;
                                                                function14 = function15;
                                                                imeOptions2 = imeOptions6;
                                                                textFieldScrollerPosition2 = textFieldScrollerPosition3;
                                                                mutableInteractionSource3 = mutableInteractionSource7;
                                                                keyboardActions2 = keyboardActions4;
                                                                brush2 = brush52;
                                                                z6 = z292;
                                                                modifier3 = modifier6;
                                                                i29 = i34;
                                                                z5 = z10;
                                                                textStyle3 = textStyle4;
                                                                z4 = z12;
                                                                visualTransformation2 = visualTransformation52;
                                                            }
                                                        } else {
                                                            textFieldSelectionManager = textFieldSelectionManager3;
                                                        }
                                                        final ImeOptions imeOptions7 = imeOptions4;
                                                        final boolean z32 = z8;
                                                        rememberedValue19 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda12
                                                            @Override // kotlin.jvm.functions.Function1
                                                            public final Object invoke(Object obj) {
                                                                Unit CoreTextField$lambda$15$lambda$14;
                                                                CoreTextField$lambda$15$lambda$14 = CoreTextFieldKt.CoreTextField$lambda$15$lambda$14(LegacyTextFieldState.this, z26, z32, textInputService2, textFieldValue, imeOptions7, offsetMapping2, textFieldSelectionManager, coroutineScope2, bringIntoViewRequester2, (FocusState) obj);
                                                                return CoreTextField$lambda$15$lambda$14;
                                                            }
                                                        };
                                                        legacyTextFieldState2 = legacyTextFieldState;
                                                        z12 = z32;
                                                        textInputService = textInputService2;
                                                        imeOptions5 = imeOptions7;
                                                        offsetMapping = offsetMapping2;
                                                        coroutineScope = coroutineScope2;
                                                        bringIntoViewRequester = bringIntoViewRequester2;
                                                        visualTransformation4 = visualTransformation3;
                                                        str = "CC(<get-current>):CompositionLocal.kt#9igjgp";
                                                        z13 = z26;
                                                        textFieldSelectionManager2 = textFieldSelectionManager;
                                                        startRestartGroup.updateRememberedValue(rememberedValue19);
                                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                        Modifier textFieldFocusModifier2 = TextFieldGestureModifiersKt.textFieldFocusModifier(companion2, z13, focusRequester2, mutableInteractionSource7, (Function1) rememberedValue19);
                                                        State rememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf((z13 || z12) ? false : true), startRestartGroup, 0);
                                                        Unit unit2 = Unit.INSTANCE;
                                                        final boolean z272 = z13;
                                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213583572, "CC(remember):CoreTextField.kt#9igjgp");
                                                        changed = startRestartGroup.changed(rememberUpdatedState2) | startRestartGroup.changedInstance(legacyTextFieldState2) | startRestartGroup.changedInstance(textInputService) | startRestartGroup.changedInstance(textFieldSelectionManager2);
                                                        if (i46 > 32) {
                                                        }
                                                        z14 = changed;
                                                        if ((i44 & 48) != 32) {
                                                        }
                                                        z15 = true;
                                                        z16 = z14 | z15;
                                                        rememberedValue = startRestartGroup.rememberedValue();
                                                        if (z16) {
                                                        }
                                                        CoreTextFieldKt$CoreTextField$2$1 coreTextFieldKt$CoreTextField$2$122 = new CoreTextFieldKt$CoreTextField$2$1(legacyTextFieldState2, rememberUpdatedState2, textInputService, textFieldSelectionManager2, imeOptions5, null);
                                                        state = rememberUpdatedState2;
                                                        rememberedValue = coreTextFieldKt$CoreTextField$2$122;
                                                        startRestartGroup.updateRememberedValue(rememberedValue);
                                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                        State state222 = state;
                                                        EffectsKt.LaunchedEffect(unit2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue, startRestartGroup, 6);
                                                        Modifier.Companion companion322 = Modifier.INSTANCE;
                                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213551203, "CC(remember):CoreTextField.kt#9igjgp");
                                                        changedInstance2 = startRestartGroup.changedInstance(legacyTextFieldState2);
                                                        rememberedValue2 = startRestartGroup.rememberedValue();
                                                        if (!changedInstance2) {
                                                        }
                                                        rememberedValue2 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda13
                                                            @Override // kotlin.jvm.functions.Function1
                                                            public final Object invoke(Object obj) {
                                                                Unit CoreTextField$lambda$19$lambda$18;
                                                                CoreTextField$lambda$19$lambda$18 = CoreTextFieldKt.CoreTextField$lambda$19$lambda$18(LegacyTextFieldState.this, ((Boolean) obj).booleanValue());
                                                                return CoreTextField$lambda$19$lambda$18;
                                                            }
                                                        };
                                                        startRestartGroup.updateRememberedValue(rememberedValue2);
                                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                        Modifier updateSelectionTouchMode22 = SelectionGesturesKt.updateSelectionTouchMode(companion322, (Function1) rememberedValue2);
                                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213547167, "CC(remember):CoreTextField.kt#9igjgp");
                                                        changedInstance3 = startRestartGroup.changedInstance(legacyTextFieldState2) | (i45 != 16384) | (i43 != 2048) | startRestartGroup.changedInstance(offsetMapping) | startRestartGroup.changedInstance(textFieldSelectionManager2);
                                                        rememberedValue3 = startRestartGroup.rememberedValue();
                                                        if (changedInstance3) {
                                                        }
                                                        final OffsetMapping offsetMapping322 = offsetMapping;
                                                        final TextFieldSelectionManager textFieldSelectionManager422 = textFieldSelectionManager2;
                                                        final LegacyTextFieldState legacyTextFieldState522 = legacyTextFieldState2;
                                                        final boolean z2822 = z12;
                                                        rememberedValue3 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda14
                                                            @Override // kotlin.jvm.functions.Function1
                                                            public final Object invoke(Object obj) {
                                                                Unit CoreTextField$lambda$22$lambda$21;
                                                                CoreTextField$lambda$22$lambda$21 = CoreTextFieldKt.CoreTextField$lambda$22$lambda$21(LegacyTextFieldState.this, focusRequester2, z2822, z272, textFieldSelectionManager422, offsetMapping322, (Offset) obj);
                                                                return CoreTextField$lambda$22$lambda$21;
                                                            }
                                                        };
                                                        focusRequester = focusRequester2;
                                                        z17 = z272;
                                                        startRestartGroup.updateRememberedValue(rememberedValue3);
                                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                        FocusRequester focusRequester322 = focusRequester;
                                                        imeOptions6 = imeOptions5;
                                                        z18 = z17;
                                                        Modifier pointerHoverIcon$default22 = PointerIconKt.pointerHoverIcon$default(SelectionGesturesKt.selectionGestureInput(TextFieldPressGestureFilterKt.tapPressTextFieldModifier(updateSelectionTouchMode22, mutableInteractionSource7, z17, (Function1) rememberedValue3), textFieldSelectionManager2.getMouseSelectionObserver(), textFieldSelectionManager2.getTouchSelectionObserver()), PointerIcon.INSTANCE.getText(), false, 2, null);
                                                        Modifier.Companion companion422 = Modifier.INSTANCE;
                                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213506526, "CC(remember):CoreTextField.kt#9igjgp");
                                                        changedInstance4 = startRestartGroup.changedInstance(legacyTextFieldState2) | (i42 != 4) | startRestartGroup.changedInstance(offsetMapping);
                                                        rememberedValue4 = startRestartGroup.rememberedValue();
                                                        if (changedInstance4) {
                                                        }
                                                        textFieldValue2 = textFieldValue;
                                                        rememberedValue4 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda15
                                                            @Override // kotlin.jvm.functions.Function1
                                                            public final Object invoke(Object obj) {
                                                                Unit CoreTextField$lambda$26$lambda$25;
                                                                CoreTextField$lambda$26$lambda$25 = CoreTextFieldKt.CoreTextField$lambda$26$lambda$25(LegacyTextFieldState.this, textFieldValue2, offsetMapping, (DrawScope) obj);
                                                                return CoreTextField$lambda$26$lambda$25;
                                                            }
                                                        };
                                                        startRestartGroup.updateRememberedValue(rememberedValue4);
                                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                        final Modifier drawBehind22 = DrawModifierKt.drawBehind(companion422, (Function1) rememberedValue4);
                                                        Modifier.Companion companion522 = Modifier.INSTANCE;
                                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213484761, "CC(remember):CoreTextField.kt#9igjgp");
                                                        windowInfo2 = windowInfo;
                                                        changedInstance5 = (i43 != 2048) | startRestartGroup.changedInstance(legacyTextFieldState2) | startRestartGroup.changed(windowInfo2) | startRestartGroup.changedInstance(textFieldSelectionManager2) | (i42 != 4) | startRestartGroup.changedInstance(offsetMapping);
                                                        rememberedValue5 = startRestartGroup.rememberedValue();
                                                        if (changedInstance5) {
                                                        }
                                                        final TextFieldValue textFieldValue322 = textFieldValue2;
                                                        final OffsetMapping offsetMapping422 = offsetMapping;
                                                        final TextFieldSelectionManager textFieldSelectionManager522 = textFieldSelectionManager2;
                                                        final LegacyTextFieldState legacyTextFieldState622 = legacyTextFieldState2;
                                                        rememberedValue5 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda0
                                                            @Override // kotlin.jvm.functions.Function1
                                                            public final Object invoke(Object obj) {
                                                                Unit CoreTextField$lambda$30$lambda$29;
                                                                CoreTextField$lambda$30$lambda$29 = CoreTextFieldKt.CoreTextField$lambda$30$lambda$29(LegacyTextFieldState.this, z18, windowInfo2, textFieldSelectionManager522, textFieldValue322, offsetMapping422, (LayoutCoordinates) obj);
                                                                return CoreTextField$lambda$30$lambda$29;
                                                            }
                                                        };
                                                        z19 = z18;
                                                        windowInfo3 = windowInfo2;
                                                        startRestartGroup.updateRememberedValue(rememberedValue5);
                                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                        final Modifier onGloballyPositioned22 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion522, (Function1) rememberedValue5);
                                                        final VisualTransformation visualTransformation522 = visualTransformation4;
                                                        boolean z2922 = z19;
                                                        CoreTextFieldSemanticsModifier coreTextFieldSemanticsModifier22 = new CoreTextFieldSemanticsModifier(transformedText, textFieldValue, legacyTextFieldState2, z12, z2922, visualTransformation522 instanceof PasswordVisualTransformation, offsetMapping, textFieldSelectionManager2, imeOptions6, focusRequester322);
                                                        Brush brush522 = brush4;
                                                        final OffsetMapping offsetMapping522 = offsetMapping;
                                                        legacyTextFieldState3 = legacyTextFieldState2;
                                                        Modifier cursor22 = TextFieldCursorKt.cursor(Modifier.INSTANCE, legacyTextFieldState3, textFieldValue, offsetMapping522, brush522, (z2922 || z12 || !windowInfo3.isWindowFocused() || legacyTextFieldState2.hasHighlight()) ? false : true);
                                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213415343, "CC(remember):CoreTextField.kt#9igjgp");
                                                        changedInstance6 = startRestartGroup.changedInstance(textFieldSelectionManager2);
                                                        Object rememberedValue2022 = startRestartGroup.rememberedValue();
                                                        if (changedInstance6) {
                                                        }
                                                        rememberedValue2022 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda1
                                                            @Override // kotlin.jvm.functions.Function1
                                                            public final Object invoke(Object obj) {
                                                                DisposableEffectResult CoreTextField$lambda$33$lambda$32;
                                                                CoreTextField$lambda$33$lambda$32 = CoreTextFieldKt.CoreTextField$lambda$33$lambda$32(TextFieldSelectionManager.this, (DisposableEffectScope) obj);
                                                                return CoreTextField$lambda$33$lambda$32;
                                                            }
                                                        };
                                                        startRestartGroup.updateRememberedValue(rememberedValue2022);
                                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                        EffectsKt.DisposableEffect(textFieldSelectionManager2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue2022, startRestartGroup, 0);
                                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213412232, "CC(remember):CoreTextField.kt#9igjgp");
                                                        changedInstance7 = startRestartGroup.changedInstance(legacyTextFieldState3) | startRestartGroup.changedInstance(textInputService) | (i42 != 4) | ((i46 <= 32 && startRestartGroup.changed(imeOptions6)) || (i44 & 48) == 32);
                                                        rememberedValue6 = startRestartGroup.rememberedValue();
                                                        if (!changedInstance7) {
                                                        }
                                                        rememberedValue6 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda2
                                                            @Override // kotlin.jvm.functions.Function1
                                                            public final Object invoke(Object obj) {
                                                                DisposableEffectResult CoreTextField$lambda$36$lambda$35;
                                                                CoreTextField$lambda$36$lambda$35 = CoreTextFieldKt.CoreTextField$lambda$36$lambda$35(LegacyTextFieldState.this, textInputService, textFieldValue, imeOptions6, (DisposableEffectScope) obj);
                                                                return CoreTextField$lambda$36$lambda$35;
                                                            }
                                                        };
                                                        startRestartGroup.updateRememberedValue(rememberedValue6);
                                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                        EffectsKt.DisposableEffect(imeOptions6, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue6, startRestartGroup, (i44 >> 3) & 14);
                                                        int i4722 = i33;
                                                        Modifier m1408textFieldKeyInput2WJ9YEU22 = TextFieldKeyInputKt.m1408textFieldKeyInput2WJ9YEU(Modifier.INSTANCE, legacyTextFieldState3, textFieldSelectionManager2, textFieldValue, legacyTextFieldState3.getOnValueChange(), !z12, i4722 != 1, offsetMapping522, undoManager, imeOptions6.getImeAction());
                                                        if (KeyboardType.m7073equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m7092getPasswordPjHm6EE())) {
                                                        }
                                                        Modifier.Companion companion622 = Modifier.INSTANCE;
                                                        boolean CoreTextField$lambda$1622 = CoreTextField$lambda$16(state222);
                                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213374084, "CC(remember):CoreTextField.kt#9igjgp");
                                                        final LegacyPlatformTextInputServiceAdapter legacyPlatformTextInputServiceAdapter322 = legacyPlatformTextInputServiceAdapter;
                                                        changed2 = startRestartGroup.changed(z30) | startRestartGroup.changedInstance(legacyPlatformTextInputServiceAdapter322);
                                                        Object rememberedValue2122 = startRestartGroup.rememberedValue();
                                                        if (changed2) {
                                                        }
                                                        rememberedValue2122 = new Function0() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda3
                                                            @Override // kotlin.jvm.functions.Function0
                                                            public final Object invoke() {
                                                                Unit CoreTextField$lambda$38$lambda$37;
                                                                CoreTextField$lambda$38$lambda$37 = CoreTextFieldKt.CoreTextField$lambda$38$lambda$37(z30, legacyPlatformTextInputServiceAdapter322);
                                                                return CoreTextField$lambda$38$lambda$37;
                                                            }
                                                        };
                                                        startRestartGroup.updateRememberedValue(rememberedValue2122);
                                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                        Modifier stylusHandwriting22 = StylusHandwritingKt.stylusHandwriting(companion622, CoreTextField$lambda$1622, z30, (Function0) rememberedValue2122);
                                                        ProvidableCompositionLocal<Color> localAutofillHighlightColor22 = AutofillHighlightKt.getLocalAutofillHighlightColor();
                                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, str);
                                                        Object consume1022 = startRestartGroup.consume(localAutofillHighlightColor22);
                                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                        final long m4625unboximpl22 = ((Color) consume1022).m4625unboximpl();
                                                        Modifier.Companion companion722 = Modifier.INSTANCE;
                                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213339112, "CC(remember):CoreTextField.kt#9igjgp");
                                                        changedInstance8 = startRestartGroup.changedInstance(legacyTextFieldState3) | startRestartGroup.changed(m4625unboximpl22);
                                                        rememberedValue7 = startRestartGroup.rememberedValue();
                                                        if (!changedInstance8) {
                                                        }
                                                        rememberedValue7 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda9
                                                            @Override // kotlin.jvm.functions.Function1
                                                            public final Object invoke(Object obj) {
                                                                Unit CoreTextField$lambda$40$lambda$39;
                                                                CoreTextField$lambda$40$lambda$39 = CoreTextFieldKt.CoreTextField$lambda$40$lambda$39(LegacyTextFieldState.this, m4625unboximpl22, (DrawScope) obj);
                                                                return CoreTextField$lambda$40$lambda$39;
                                                            }
                                                        };
                                                        startRestartGroup.updateRememberedValue(rememberedValue7);
                                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                        final TextFieldScrollerPosition textFieldScrollerPosition622 = textFieldScrollerPosition5;
                                                        Modifier addContextMenuComponents22 = addContextMenuComponents(OnGloballyPositionedModifierKt.onGloballyPositioned(TextFieldScrollKt.textFieldScrollable(previewKeyEventToDeselectOnBack(TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus(LegacyAdaptingPlatformTextInputModifierNodeKt.legacyTextInputAdapter(modifier6.then(DrawModifierKt.drawBehind(companion722, (Function1) rememberedValue7)), legacyPlatformTextInputServiceAdapter322, legacyTextFieldState3, textFieldSelectionManager2).then(stylusHandwriting22).then(textFieldFocusModifier2), legacyTextFieldState3, focusManager), legacyTextFieldState3, textFieldSelectionManager2).then(m1408textFieldKeyInput2WJ9YEU22), textFieldScrollerPosition622, mutableInteractionSource7, z2922).then(pointerHoverIcon$default22).then(coreTextFieldSemanticsModifier22), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$decorationBoxModifier$1
                                                            @Override // kotlin.jvm.functions.Function1
                                                            public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                                                                invoke2(layoutCoordinates);
                                                                return Unit.INSTANCE;
                                                            }

                                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                            public final void invoke2(LayoutCoordinates layoutCoordinates) {
                                                                TextLayoutResultProxy layoutResult = LegacyTextFieldState.this.getLayoutResult();
                                                                if (layoutResult != null) {
                                                                    layoutResult.setDecorationBoxCoordinates(layoutCoordinates);
                                                                }
                                                            }
                                                        }), textFieldSelectionManager2, coroutineScope);
                                                        if (z2922) {
                                                        }
                                                        if (!z20) {
                                                        }
                                                        final Modifier modifier722 = companion;
                                                        final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function3322 = lambda$559628295$foundation_release;
                                                        final int i4822 = i34;
                                                        final TextStyle textStyle622 = textStyle4;
                                                        final Modifier modifier822 = modifier5;
                                                        final TextFieldSelectionManager textFieldSelectionManager622 = textFieldSelectionManager2;
                                                        final BringIntoViewRequester bringIntoViewRequester322 = bringIntoViewRequester;
                                                        final boolean z3122 = z12;
                                                        CoreTextFieldRootBox(addContextMenuComponents22, textFieldSelectionManager622, ComposableLambdaKt.rememberComposableLambda(-814563849, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5

                                                            /* compiled from: CoreTextField.kt */
                                                            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                                                            /* renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1, reason: invalid class name */
                                                            static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
                                                                final /* synthetic */ BringIntoViewRequester $bringIntoViewRequester;
                                                                final /* synthetic */ Modifier $cursorModifier;
                                                                final /* synthetic */ Density $density;
                                                                final /* synthetic */ Modifier $drawModifier;
                                                                final /* synthetic */ Modifier $magnifierModifier;
                                                                final /* synthetic */ TextFieldSelectionManager $manager;
                                                                final /* synthetic */ int $maxLines;
                                                                final /* synthetic */ int $minLines;
                                                                final /* synthetic */ OffsetMapping $offsetMapping;
                                                                final /* synthetic */ Modifier $onPositionedModifier;
                                                                final /* synthetic */ Function1<TextLayoutResult, Unit> $onTextLayout;
                                                                final /* synthetic */ boolean $readOnly;
                                                                final /* synthetic */ TextFieldScrollerPosition $scrollerPosition;
                                                                final /* synthetic */ boolean $showHandleAndMagnifier;
                                                                final /* synthetic */ LegacyTextFieldState $state;
                                                                final /* synthetic */ TextStyle $textStyle;
                                                                final /* synthetic */ TextFieldValue $value;
                                                                final /* synthetic */ VisualTransformation $visualTransformation;

                                                                /* JADX WARN: Multi-variable type inference failed */
                                                                AnonymousClass1(LegacyTextFieldState legacyTextFieldState, TextStyle textStyle, int i, int i2, TextFieldScrollerPosition textFieldScrollerPosition, TextFieldValue textFieldValue, VisualTransformation visualTransformation, Modifier modifier, Modifier modifier2, Modifier modifier3, Modifier modifier4, BringIntoViewRequester bringIntoViewRequester, TextFieldSelectionManager textFieldSelectionManager, boolean z, boolean z2, Function1<? super TextLayoutResult, Unit> function1, OffsetMapping offsetMapping, Density density) {
                                                                    this.$state = legacyTextFieldState;
                                                                    this.$textStyle = textStyle;
                                                                    this.$minLines = i;
                                                                    this.$maxLines = i2;
                                                                    this.$scrollerPosition = textFieldScrollerPosition;
                                                                    this.$value = textFieldValue;
                                                                    this.$visualTransformation = visualTransformation;
                                                                    this.$cursorModifier = modifier;
                                                                    this.$drawModifier = modifier2;
                                                                    this.$onPositionedModifier = modifier3;
                                                                    this.$magnifierModifier = modifier4;
                                                                    this.$bringIntoViewRequester = bringIntoViewRequester;
                                                                    this.$manager = textFieldSelectionManager;
                                                                    this.$showHandleAndMagnifier = z;
                                                                    this.$readOnly = z2;
                                                                    this.$onTextLayout = function1;
                                                                    this.$offsetMapping = offsetMapping;
                                                                    this.$density = density;
                                                                }

                                                                @Override // kotlin.jvm.functions.Function2
                                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                                    invoke(composer, num.intValue());
                                                                    return Unit.INSTANCE;
                                                                }

                                                                /*  JADX ERROR: Method code generation error
                                                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0066: CONSTRUCTOR (r7v1 'rememberedValue' java.lang.Object) = (r6v1 'legacyTextFieldState' androidx.compose.foundation.text.LegacyTextFieldState A[DONT_INLINE]) A[MD:(androidx.compose.foundation.text.LegacyTextFieldState):void (m)] (LINE:583) call: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0.<init>(androidx.compose.foundation.text.LegacyTextFieldState):void type: CONSTRUCTOR in method: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5.1.invoke(androidx.compose.runtime.Composer, int):void, file: classes.dex
                                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                                                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                                                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:305)
                                                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                                                    	... 19 more
                                                                    */
                                                                public final void invoke(androidx.compose.runtime.Composer r14, int r15) {
                                                                    /*
                                                                        r13 = this;
                                                                        java.lang.String r0 = "C582@27771L22,591@28158L4131,591@28122L4167:CoreTextField.kt#423gt5"
                                                                        androidx.compose.runtime.ComposerKt.sourceInformation(r14, r0)
                                                                        r0 = r15 & 3
                                                                        r1 = 0
                                                                        r2 = 1
                                                                        r3 = 2
                                                                        if (r0 == r3) goto Le
                                                                        r0 = r2
                                                                        goto Lf
                                                                    Le:
                                                                        r0 = r1
                                                                    Lf:
                                                                        r4 = r15 & 1
                                                                        boolean r0 = r14.shouldExecute(r0, r4)
                                                                        if (r0 == 0) goto Lca
                                                                        boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                                                        if (r0 == 0) goto L26
                                                                        r0 = -1
                                                                        java.lang.String r4 = "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:571)"
                                                                        r5 = -44346382(0xfffffffffd5b53f2, float:-1.822105E37)
                                                                        androidx.compose.runtime.ComposerKt.traceEventStart(r5, r15, r0, r4)
                                                                    L26:
                                                                        androidx.compose.ui.Modifier$Companion r15 = androidx.compose.ui.Modifier.INSTANCE
                                                                        androidx.compose.ui.Modifier r15 = (androidx.compose.ui.Modifier) r15
                                                                        androidx.compose.foundation.text.LegacyTextFieldState r0 = r13.$state
                                                                        float r0 = r0.m1323getMinHeightForSingleLineFieldD9Ej5fM()
                                                                        r4 = 0
                                                                        r5 = 0
                                                                        androidx.compose.ui.Modifier r15 = androidx.compose.foundation.layout.SizeKt.m918heightInVpY3zN4$default(r15, r0, r4, r3, r5)
                                                                        androidx.compose.ui.text.TextStyle r0 = r13.$textStyle
                                                                        int r3 = r13.$minLines
                                                                        int r4 = r13.$maxLines
                                                                        androidx.compose.ui.Modifier r15 = androidx.compose.foundation.text.HeightInLinesModifierKt.heightInLines(r15, r0, r3, r4)
                                                                        androidx.compose.foundation.text.TextFieldScrollerPosition r0 = r13.$scrollerPosition
                                                                        androidx.compose.ui.text.input.TextFieldValue r3 = r13.$value
                                                                        androidx.compose.ui.text.input.VisualTransformation r4 = r13.$visualTransformation
                                                                        r5 = 332827112(0x13d689e8, float:5.415718E-27)
                                                                        java.lang.String r6 = "CC(remember):CoreTextField.kt#9igjgp"
                                                                        androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r14, r5, r6)
                                                                        androidx.compose.foundation.text.LegacyTextFieldState r5 = r13.$state
                                                                        boolean r5 = r14.changedInstance(r5)
                                                                        androidx.compose.foundation.text.LegacyTextFieldState r6 = r13.$state
                                                                        java.lang.Object r7 = r14.rememberedValue()
                                                                        if (r5 != 0) goto L64
                                                                        androidx.compose.runtime.Composer$Companion r5 = androidx.compose.runtime.Composer.INSTANCE
                                                                        java.lang.Object r5 = r5.getEmpty()
                                                                        if (r7 != r5) goto L6c
                                                                    L64:
                                                                        androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0 r7 = new androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0
                                                                        r7.<init>(r6)
                                                                        r14.updateRememberedValue(r7)
                                                                    L6c:
                                                                        kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
                                                                        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r14)
                                                                        androidx.compose.ui.Modifier r15 = androidx.compose.foundation.text.TextFieldScroll_androidKt.textFieldScroll(r15, r0, r3, r4, r7)
                                                                        androidx.compose.ui.Modifier r0 = r13.$cursorModifier
                                                                        androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                        androidx.compose.ui.Modifier r0 = r13.$drawModifier
                                                                        androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                        androidx.compose.ui.text.TextStyle r0 = r13.$textStyle
                                                                        androidx.compose.ui.Modifier r15 = androidx.compose.foundation.text.TextFieldSizeKt.textFieldMinSize(r15, r0)
                                                                        androidx.compose.ui.Modifier r0 = r13.$onPositionedModifier
                                                                        androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                        androidx.compose.ui.Modifier r0 = r13.$magnifierModifier
                                                                        androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                        androidx.compose.foundation.relocation.BringIntoViewRequester r0 = r13.$bringIntoViewRequester
                                                                        androidx.compose.ui.Modifier r15 = androidx.compose.foundation.relocation.BringIntoViewRequesterKt.bringIntoViewRequester(r15, r0)
                                                                        androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1 r3 = new androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1
                                                                        androidx.compose.foundation.text.selection.TextFieldSelectionManager r4 = r13.$manager
                                                                        androidx.compose.foundation.text.LegacyTextFieldState r5 = r13.$state
                                                                        boolean r6 = r13.$showHandleAndMagnifier
                                                                        boolean r7 = r13.$readOnly
                                                                        kotlin.jvm.functions.Function1<androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r8 = r13.$onTextLayout
                                                                        androidx.compose.ui.text.input.TextFieldValue r9 = r13.$value
                                                                        androidx.compose.ui.text.input.OffsetMapping r10 = r13.$offsetMapping
                                                                        androidx.compose.ui.unit.Density r11 = r13.$density
                                                                        int r12 = r13.$maxLines
                                                                        r3.<init>()
                                                                        r0 = 54
                                                                        r4 = 1412697320(0x54340ce8, float:3.09324258E12)
                                                                        androidx.compose.runtime.internal.ComposableLambda r0 = androidx.compose.runtime.internal.ComposableLambdaKt.rememberComposableLambda(r4, r2, r3, r14, r0)
                                                                        kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
                                                                        r2 = 48
                                                                        androidx.compose.foundation.text.selection.SimpleLayoutKt.SimpleLayout(r15, r0, r14, r2, r1)
                                                                        boolean r14 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                                                        if (r14 == 0) goto Lc9
                                                                        androidx.compose.runtime.ComposerKt.traceEventEnd()
                                                                    Lc9:
                                                                        return
                                                                    Lca:
                                                                        r14.skipToGroupEnd()
                                                                        return
                                                                    */
                                                                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5.AnonymousClass1.invoke(androidx.compose.runtime.Composer, int):void");
                                                                }
                                                            }

                                                            @Override // kotlin.jvm.functions.Function2
                                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                                invoke(composer3, num.intValue());
                                                                return Unit.INSTANCE;
                                                            }

                                                            public final void invoke(Composer composer3, int i49) {
                                                                ComposerKt.sourceInformation(composer3, "C568@26920L5379,568@26906L5393:CoreTextField.kt#423gt5");
                                                                if (!composer3.shouldExecute((i49 & 3) != 2, i49 & 1)) {
                                                                    composer3.skipToGroupEnd();
                                                                    return;
                                                                }
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventStart(-814563849, i49, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:568)");
                                                                }
                                                                function3322.invoke(ComposableLambdaKt.rememberComposableLambda(-44346382, true, new AnonymousClass1(legacyTextFieldState3, textStyle622, i41, i4822, textFieldScrollerPosition622, textFieldValue, visualTransformation522, modifier822, drawBehind22, onGloballyPositioned22, modifier722, bringIntoViewRequester322, textFieldSelectionManager622, z20, z3122, function15, offsetMapping522, density), composer3, 54), composer3, 6);
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventEnd();
                                                                }
                                                            }
                                                        }, startRestartGroup, 54), startRestartGroup, 384);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                        }
                                                        function32 = function3322;
                                                        i28 = i41;
                                                        composer2 = startRestartGroup;
                                                        function14 = function15;
                                                        imeOptions2 = imeOptions6;
                                                        textFieldScrollerPosition2 = textFieldScrollerPosition3;
                                                        mutableInteractionSource3 = mutableInteractionSource7;
                                                        keyboardActions2 = keyboardActions4;
                                                        brush2 = brush522;
                                                        z6 = z2922;
                                                        modifier3 = modifier6;
                                                        i29 = i34;
                                                        z5 = z10;
                                                        textStyle3 = textStyle4;
                                                        z4 = z12;
                                                        visualTransformation2 = visualTransformation522;
                                                    }
                                                } else {
                                                    legacyTextFieldState = legacyTextFieldState4;
                                                }
                                                z11 = true;
                                                changedInstance = changedInstance9 | z11 | startRestartGroup.changedInstance(offsetMapping2) | startRestartGroup.changedInstance(coroutineScope2) | startRestartGroup.changedInstance(bringIntoViewRequester2) | startRestartGroup.changedInstance(textFieldSelectionManager3);
                                                Object rememberedValue192 = startRestartGroup.rememberedValue();
                                                if (changedInstance) {
                                                }
                                                final ImeOptions imeOptions72 = imeOptions4;
                                                final boolean z322 = z8;
                                                rememberedValue192 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda12
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj) {
                                                        Unit CoreTextField$lambda$15$lambda$14;
                                                        CoreTextField$lambda$15$lambda$14 = CoreTextFieldKt.CoreTextField$lambda$15$lambda$14(LegacyTextFieldState.this, z26, z322, textInputService2, textFieldValue, imeOptions72, offsetMapping2, textFieldSelectionManager, coroutineScope2, bringIntoViewRequester2, (FocusState) obj);
                                                        return CoreTextField$lambda$15$lambda$14;
                                                    }
                                                };
                                                legacyTextFieldState2 = legacyTextFieldState;
                                                z12 = z322;
                                                textInputService = textInputService2;
                                                imeOptions5 = imeOptions72;
                                                offsetMapping = offsetMapping2;
                                                coroutineScope = coroutineScope2;
                                                bringIntoViewRequester = bringIntoViewRequester2;
                                                visualTransformation4 = visualTransformation3;
                                                str = "CC(<get-current>):CompositionLocal.kt#9igjgp";
                                                z13 = z26;
                                                textFieldSelectionManager2 = textFieldSelectionManager;
                                                startRestartGroup.updateRememberedValue(rememberedValue192);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                Modifier textFieldFocusModifier22 = TextFieldGestureModifiersKt.textFieldFocusModifier(companion2, z13, focusRequester2, mutableInteractionSource7, (Function1) rememberedValue192);
                                                State rememberUpdatedState22 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf((z13 || z12) ? false : true), startRestartGroup, 0);
                                                Unit unit22 = Unit.INSTANCE;
                                                final boolean z2722 = z13;
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213583572, "CC(remember):CoreTextField.kt#9igjgp");
                                                changed = startRestartGroup.changed(rememberUpdatedState22) | startRestartGroup.changedInstance(legacyTextFieldState2) | startRestartGroup.changedInstance(textInputService) | startRestartGroup.changedInstance(textFieldSelectionManager2);
                                                if (i46 > 32) {
                                                }
                                                z14 = changed;
                                                if ((i44 & 48) != 32) {
                                                }
                                                z15 = true;
                                                z16 = z14 | z15;
                                                rememberedValue = startRestartGroup.rememberedValue();
                                                if (z16) {
                                                }
                                                CoreTextFieldKt$CoreTextField$2$1 coreTextFieldKt$CoreTextField$2$1222 = new CoreTextFieldKt$CoreTextField$2$1(legacyTextFieldState2, rememberUpdatedState22, textInputService, textFieldSelectionManager2, imeOptions5, null);
                                                state = rememberUpdatedState22;
                                                rememberedValue = coreTextFieldKt$CoreTextField$2$1222;
                                                startRestartGroup.updateRememberedValue(rememberedValue);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                State state2222 = state;
                                                EffectsKt.LaunchedEffect(unit22, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue, startRestartGroup, 6);
                                                Modifier.Companion companion3222 = Modifier.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213551203, "CC(remember):CoreTextField.kt#9igjgp");
                                                changedInstance2 = startRestartGroup.changedInstance(legacyTextFieldState2);
                                                rememberedValue2 = startRestartGroup.rememberedValue();
                                                if (!changedInstance2) {
                                                }
                                                rememberedValue2 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda13
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj) {
                                                        Unit CoreTextField$lambda$19$lambda$18;
                                                        CoreTextField$lambda$19$lambda$18 = CoreTextFieldKt.CoreTextField$lambda$19$lambda$18(LegacyTextFieldState.this, ((Boolean) obj).booleanValue());
                                                        return CoreTextField$lambda$19$lambda$18;
                                                    }
                                                };
                                                startRestartGroup.updateRememberedValue(rememberedValue2);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                Modifier updateSelectionTouchMode222 = SelectionGesturesKt.updateSelectionTouchMode(companion3222, (Function1) rememberedValue2);
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213547167, "CC(remember):CoreTextField.kt#9igjgp");
                                                changedInstance3 = startRestartGroup.changedInstance(legacyTextFieldState2) | (i45 != 16384) | (i43 != 2048) | startRestartGroup.changedInstance(offsetMapping) | startRestartGroup.changedInstance(textFieldSelectionManager2);
                                                rememberedValue3 = startRestartGroup.rememberedValue();
                                                if (changedInstance3) {
                                                }
                                                final OffsetMapping offsetMapping3222 = offsetMapping;
                                                final TextFieldSelectionManager textFieldSelectionManager4222 = textFieldSelectionManager2;
                                                final LegacyTextFieldState legacyTextFieldState5222 = legacyTextFieldState2;
                                                final boolean z28222 = z12;
                                                rememberedValue3 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda14
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj) {
                                                        Unit CoreTextField$lambda$22$lambda$21;
                                                        CoreTextField$lambda$22$lambda$21 = CoreTextFieldKt.CoreTextField$lambda$22$lambda$21(LegacyTextFieldState.this, focusRequester2, z28222, z2722, textFieldSelectionManager4222, offsetMapping3222, (Offset) obj);
                                                        return CoreTextField$lambda$22$lambda$21;
                                                    }
                                                };
                                                focusRequester = focusRequester2;
                                                z17 = z2722;
                                                startRestartGroup.updateRememberedValue(rememberedValue3);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                FocusRequester focusRequester3222 = focusRequester;
                                                imeOptions6 = imeOptions5;
                                                z18 = z17;
                                                Modifier pointerHoverIcon$default222 = PointerIconKt.pointerHoverIcon$default(SelectionGesturesKt.selectionGestureInput(TextFieldPressGestureFilterKt.tapPressTextFieldModifier(updateSelectionTouchMode222, mutableInteractionSource7, z17, (Function1) rememberedValue3), textFieldSelectionManager2.getMouseSelectionObserver(), textFieldSelectionManager2.getTouchSelectionObserver()), PointerIcon.INSTANCE.getText(), false, 2, null);
                                                Modifier.Companion companion4222 = Modifier.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213506526, "CC(remember):CoreTextField.kt#9igjgp");
                                                changedInstance4 = startRestartGroup.changedInstance(legacyTextFieldState2) | (i42 != 4) | startRestartGroup.changedInstance(offsetMapping);
                                                rememberedValue4 = startRestartGroup.rememberedValue();
                                                if (changedInstance4) {
                                                }
                                                textFieldValue2 = textFieldValue;
                                                rememberedValue4 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda15
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj) {
                                                        Unit CoreTextField$lambda$26$lambda$25;
                                                        CoreTextField$lambda$26$lambda$25 = CoreTextFieldKt.CoreTextField$lambda$26$lambda$25(LegacyTextFieldState.this, textFieldValue2, offsetMapping, (DrawScope) obj);
                                                        return CoreTextField$lambda$26$lambda$25;
                                                    }
                                                };
                                                startRestartGroup.updateRememberedValue(rememberedValue4);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                final Modifier drawBehind222 = DrawModifierKt.drawBehind(companion4222, (Function1) rememberedValue4);
                                                Modifier.Companion companion5222 = Modifier.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213484761, "CC(remember):CoreTextField.kt#9igjgp");
                                                windowInfo2 = windowInfo;
                                                changedInstance5 = (i43 != 2048) | startRestartGroup.changedInstance(legacyTextFieldState2) | startRestartGroup.changed(windowInfo2) | startRestartGroup.changedInstance(textFieldSelectionManager2) | (i42 != 4) | startRestartGroup.changedInstance(offsetMapping);
                                                rememberedValue5 = startRestartGroup.rememberedValue();
                                                if (changedInstance5) {
                                                }
                                                final TextFieldValue textFieldValue3222 = textFieldValue2;
                                                final OffsetMapping offsetMapping4222 = offsetMapping;
                                                final TextFieldSelectionManager textFieldSelectionManager5222 = textFieldSelectionManager2;
                                                final LegacyTextFieldState legacyTextFieldState6222 = legacyTextFieldState2;
                                                rememberedValue5 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda0
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj) {
                                                        Unit CoreTextField$lambda$30$lambda$29;
                                                        CoreTextField$lambda$30$lambda$29 = CoreTextFieldKt.CoreTextField$lambda$30$lambda$29(LegacyTextFieldState.this, z18, windowInfo2, textFieldSelectionManager5222, textFieldValue3222, offsetMapping4222, (LayoutCoordinates) obj);
                                                        return CoreTextField$lambda$30$lambda$29;
                                                    }
                                                };
                                                z19 = z18;
                                                windowInfo3 = windowInfo2;
                                                startRestartGroup.updateRememberedValue(rememberedValue5);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                final Modifier onGloballyPositioned222 = OnGloballyPositionedModifierKt.onGloballyPositioned(companion5222, (Function1) rememberedValue5);
                                                final VisualTransformation visualTransformation5222 = visualTransformation4;
                                                boolean z29222 = z19;
                                                CoreTextFieldSemanticsModifier coreTextFieldSemanticsModifier222 = new CoreTextFieldSemanticsModifier(transformedText, textFieldValue, legacyTextFieldState2, z12, z29222, visualTransformation5222 instanceof PasswordVisualTransformation, offsetMapping, textFieldSelectionManager2, imeOptions6, focusRequester3222);
                                                Brush brush5222 = brush4;
                                                final OffsetMapping offsetMapping5222 = offsetMapping;
                                                legacyTextFieldState3 = legacyTextFieldState2;
                                                Modifier cursor222 = TextFieldCursorKt.cursor(Modifier.INSTANCE, legacyTextFieldState3, textFieldValue, offsetMapping5222, brush5222, (z29222 || z12 || !windowInfo3.isWindowFocused() || legacyTextFieldState2.hasHighlight()) ? false : true);
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213415343, "CC(remember):CoreTextField.kt#9igjgp");
                                                changedInstance6 = startRestartGroup.changedInstance(textFieldSelectionManager2);
                                                Object rememberedValue20222 = startRestartGroup.rememberedValue();
                                                if (changedInstance6) {
                                                }
                                                rememberedValue20222 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda1
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj) {
                                                        DisposableEffectResult CoreTextField$lambda$33$lambda$32;
                                                        CoreTextField$lambda$33$lambda$32 = CoreTextFieldKt.CoreTextField$lambda$33$lambda$32(TextFieldSelectionManager.this, (DisposableEffectScope) obj);
                                                        return CoreTextField$lambda$33$lambda$32;
                                                    }
                                                };
                                                startRestartGroup.updateRememberedValue(rememberedValue20222);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                EffectsKt.DisposableEffect(textFieldSelectionManager2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue20222, startRestartGroup, 0);
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213412232, "CC(remember):CoreTextField.kt#9igjgp");
                                                changedInstance7 = startRestartGroup.changedInstance(legacyTextFieldState3) | startRestartGroup.changedInstance(textInputService) | (i42 != 4) | ((i46 <= 32 && startRestartGroup.changed(imeOptions6)) || (i44 & 48) == 32);
                                                rememberedValue6 = startRestartGroup.rememberedValue();
                                                if (!changedInstance7) {
                                                }
                                                rememberedValue6 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda2
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj) {
                                                        DisposableEffectResult CoreTextField$lambda$36$lambda$35;
                                                        CoreTextField$lambda$36$lambda$35 = CoreTextFieldKt.CoreTextField$lambda$36$lambda$35(LegacyTextFieldState.this, textInputService, textFieldValue, imeOptions6, (DisposableEffectScope) obj);
                                                        return CoreTextField$lambda$36$lambda$35;
                                                    }
                                                };
                                                startRestartGroup.updateRememberedValue(rememberedValue6);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                EffectsKt.DisposableEffect(imeOptions6, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue6, startRestartGroup, (i44 >> 3) & 14);
                                                int i47222 = i33;
                                                Modifier m1408textFieldKeyInput2WJ9YEU222 = TextFieldKeyInputKt.m1408textFieldKeyInput2WJ9YEU(Modifier.INSTANCE, legacyTextFieldState3, textFieldSelectionManager2, textFieldValue, legacyTextFieldState3.getOnValueChange(), !z12, i47222 != 1, offsetMapping5222, undoManager, imeOptions6.getImeAction());
                                                if (KeyboardType.m7073equalsimpl0(imeOptions6.getKeyboardType(), KeyboardType.INSTANCE.m7092getPasswordPjHm6EE())) {
                                                }
                                                Modifier.Companion companion6222 = Modifier.INSTANCE;
                                                boolean CoreTextField$lambda$16222 = CoreTextField$lambda$16(state2222);
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213374084, "CC(remember):CoreTextField.kt#9igjgp");
                                                final LegacyPlatformTextInputServiceAdapter legacyPlatformTextInputServiceAdapter3222 = legacyPlatformTextInputServiceAdapter;
                                                changed2 = startRestartGroup.changed(z30) | startRestartGroup.changedInstance(legacyPlatformTextInputServiceAdapter3222);
                                                Object rememberedValue21222 = startRestartGroup.rememberedValue();
                                                if (changed2) {
                                                }
                                                rememberedValue21222 = new Function0() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda3
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        Unit CoreTextField$lambda$38$lambda$37;
                                                        CoreTextField$lambda$38$lambda$37 = CoreTextFieldKt.CoreTextField$lambda$38$lambda$37(z30, legacyPlatformTextInputServiceAdapter3222);
                                                        return CoreTextField$lambda$38$lambda$37;
                                                    }
                                                };
                                                startRestartGroup.updateRememberedValue(rememberedValue21222);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                Modifier stylusHandwriting222 = StylusHandwritingKt.stylusHandwriting(companion6222, CoreTextField$lambda$16222, z30, (Function0) rememberedValue21222);
                                                ProvidableCompositionLocal<Color> localAutofillHighlightColor222 = AutofillHighlightKt.getLocalAutofillHighlightColor();
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, str);
                                                Object consume10222 = startRestartGroup.consume(localAutofillHighlightColor222);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                final long m4625unboximpl222 = ((Color) consume10222).m4625unboximpl();
                                                Modifier.Companion companion7222 = Modifier.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -213339112, "CC(remember):CoreTextField.kt#9igjgp");
                                                changedInstance8 = startRestartGroup.changedInstance(legacyTextFieldState3) | startRestartGroup.changed(m4625unboximpl222);
                                                rememberedValue7 = startRestartGroup.rememberedValue();
                                                if (!changedInstance8) {
                                                }
                                                rememberedValue7 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda9
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj) {
                                                        Unit CoreTextField$lambda$40$lambda$39;
                                                        CoreTextField$lambda$40$lambda$39 = CoreTextFieldKt.CoreTextField$lambda$40$lambda$39(LegacyTextFieldState.this, m4625unboximpl222, (DrawScope) obj);
                                                        return CoreTextField$lambda$40$lambda$39;
                                                    }
                                                };
                                                startRestartGroup.updateRememberedValue(rememberedValue7);
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                                final TextFieldScrollerPosition textFieldScrollerPosition6222 = textFieldScrollerPosition5;
                                                Modifier addContextMenuComponents222 = addContextMenuComponents(OnGloballyPositionedModifierKt.onGloballyPositioned(TextFieldScrollKt.textFieldScrollable(previewKeyEventToDeselectOnBack(TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus(LegacyAdaptingPlatformTextInputModifierNodeKt.legacyTextInputAdapter(modifier6.then(DrawModifierKt.drawBehind(companion7222, (Function1) rememberedValue7)), legacyPlatformTextInputServiceAdapter3222, legacyTextFieldState3, textFieldSelectionManager2).then(stylusHandwriting222).then(textFieldFocusModifier22), legacyTextFieldState3, focusManager), legacyTextFieldState3, textFieldSelectionManager2).then(m1408textFieldKeyInput2WJ9YEU222), textFieldScrollerPosition6222, mutableInteractionSource7, z29222).then(pointerHoverIcon$default222).then(coreTextFieldSemanticsModifier222), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$decorationBoxModifier$1
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                                                        invoke2(layoutCoordinates);
                                                        return Unit.INSTANCE;
                                                    }

                                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                    public final void invoke2(LayoutCoordinates layoutCoordinates) {
                                                        TextLayoutResultProxy layoutResult = LegacyTextFieldState.this.getLayoutResult();
                                                        if (layoutResult != null) {
                                                            layoutResult.setDecorationBoxCoordinates(layoutCoordinates);
                                                        }
                                                    }
                                                }), textFieldSelectionManager2, coroutineScope);
                                                if (z29222) {
                                                }
                                                if (!z20) {
                                                }
                                                final Modifier modifier7222 = companion;
                                                final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function33222 = lambda$559628295$foundation_release;
                                                final int i48222 = i34;
                                                final TextStyle textStyle6222 = textStyle4;
                                                final Modifier modifier8222 = modifier5;
                                                final TextFieldSelectionManager textFieldSelectionManager6222 = textFieldSelectionManager2;
                                                final BringIntoViewRequester bringIntoViewRequester3222 = bringIntoViewRequester;
                                                final boolean z31222 = z12;
                                                CoreTextFieldRootBox(addContextMenuComponents222, textFieldSelectionManager6222, ComposableLambdaKt.rememberComposableLambda(-814563849, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5

                                                    /* compiled from: CoreTextField.kt */
                                                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                                                    /* renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1, reason: invalid class name */
                                                    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
                                                        final /* synthetic */ BringIntoViewRequester $bringIntoViewRequester;
                                                        final /* synthetic */ Modifier $cursorModifier;
                                                        final /* synthetic */ Density $density;
                                                        final /* synthetic */ Modifier $drawModifier;
                                                        final /* synthetic */ Modifier $magnifierModifier;
                                                        final /* synthetic */ TextFieldSelectionManager $manager;
                                                        final /* synthetic */ int $maxLines;
                                                        final /* synthetic */ int $minLines;
                                                        final /* synthetic */ OffsetMapping $offsetMapping;
                                                        final /* synthetic */ Modifier $onPositionedModifier;
                                                        final /* synthetic */ Function1<TextLayoutResult, Unit> $onTextLayout;
                                                        final /* synthetic */ boolean $readOnly;
                                                        final /* synthetic */ TextFieldScrollerPosition $scrollerPosition;
                                                        final /* synthetic */ boolean $showHandleAndMagnifier;
                                                        final /* synthetic */ LegacyTextFieldState $state;
                                                        final /* synthetic */ TextStyle $textStyle;
                                                        final /* synthetic */ TextFieldValue $value;
                                                        final /* synthetic */ VisualTransformation $visualTransformation;

                                                        /* JADX WARN: Multi-variable type inference failed */
                                                        AnonymousClass1(LegacyTextFieldState legacyTextFieldState, TextStyle textStyle, int i, int i2, TextFieldScrollerPosition textFieldScrollerPosition, TextFieldValue textFieldValue, VisualTransformation visualTransformation, Modifier modifier, Modifier modifier2, Modifier modifier3, Modifier modifier4, BringIntoViewRequester bringIntoViewRequester, TextFieldSelectionManager textFieldSelectionManager, boolean z, boolean z2, Function1<? super TextLayoutResult, Unit> function1, OffsetMapping offsetMapping, Density density) {
                                                            this.$state = legacyTextFieldState;
                                                            this.$textStyle = textStyle;
                                                            this.$minLines = i;
                                                            this.$maxLines = i2;
                                                            this.$scrollerPosition = textFieldScrollerPosition;
                                                            this.$value = textFieldValue;
                                                            this.$visualTransformation = visualTransformation;
                                                            this.$cursorModifier = modifier;
                                                            this.$drawModifier = modifier2;
                                                            this.$onPositionedModifier = modifier3;
                                                            this.$magnifierModifier = modifier4;
                                                            this.$bringIntoViewRequester = bringIntoViewRequester;
                                                            this.$manager = textFieldSelectionManager;
                                                            this.$showHandleAndMagnifier = z;
                                                            this.$readOnly = z2;
                                                            this.$onTextLayout = function1;
                                                            this.$offsetMapping = offsetMapping;
                                                            this.$density = density;
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                            invoke(composer, num.intValue());
                                                            return Unit.INSTANCE;
                                                        }

                                                        /*  JADX ERROR: Method code generation error
                                                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0066: CONSTRUCTOR (r7v1 'rememberedValue' java.lang.Object) = (r6v1 'legacyTextFieldState' androidx.compose.foundation.text.LegacyTextFieldState A[DONT_INLINE]) A[MD:(androidx.compose.foundation.text.LegacyTextFieldState):void (m)] (LINE:583) call: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0.<init>(androidx.compose.foundation.text.LegacyTextFieldState):void type: CONSTRUCTOR in method: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5.1.invoke(androidx.compose.runtime.Composer, int):void, file: classes.dex
                                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                                                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:305)
                                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                                            	... 18 more
                                                            */
                                                        public final void invoke(androidx.compose.runtime.Composer r14, int r15) {
                                                            /*
                                                                r13 = this;
                                                                java.lang.String r0 = "C582@27771L22,591@28158L4131,591@28122L4167:CoreTextField.kt#423gt5"
                                                                androidx.compose.runtime.ComposerKt.sourceInformation(r14, r0)
                                                                r0 = r15 & 3
                                                                r1 = 0
                                                                r2 = 1
                                                                r3 = 2
                                                                if (r0 == r3) goto Le
                                                                r0 = r2
                                                                goto Lf
                                                            Le:
                                                                r0 = r1
                                                            Lf:
                                                                r4 = r15 & 1
                                                                boolean r0 = r14.shouldExecute(r0, r4)
                                                                if (r0 == 0) goto Lca
                                                                boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                                                if (r0 == 0) goto L26
                                                                r0 = -1
                                                                java.lang.String r4 = "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:571)"
                                                                r5 = -44346382(0xfffffffffd5b53f2, float:-1.822105E37)
                                                                androidx.compose.runtime.ComposerKt.traceEventStart(r5, r15, r0, r4)
                                                            L26:
                                                                androidx.compose.ui.Modifier$Companion r15 = androidx.compose.ui.Modifier.INSTANCE
                                                                androidx.compose.ui.Modifier r15 = (androidx.compose.ui.Modifier) r15
                                                                androidx.compose.foundation.text.LegacyTextFieldState r0 = r13.$state
                                                                float r0 = r0.m1323getMinHeightForSingleLineFieldD9Ej5fM()
                                                                r4 = 0
                                                                r5 = 0
                                                                androidx.compose.ui.Modifier r15 = androidx.compose.foundation.layout.SizeKt.m918heightInVpY3zN4$default(r15, r0, r4, r3, r5)
                                                                androidx.compose.ui.text.TextStyle r0 = r13.$textStyle
                                                                int r3 = r13.$minLines
                                                                int r4 = r13.$maxLines
                                                                androidx.compose.ui.Modifier r15 = androidx.compose.foundation.text.HeightInLinesModifierKt.heightInLines(r15, r0, r3, r4)
                                                                androidx.compose.foundation.text.TextFieldScrollerPosition r0 = r13.$scrollerPosition
                                                                androidx.compose.ui.text.input.TextFieldValue r3 = r13.$value
                                                                androidx.compose.ui.text.input.VisualTransformation r4 = r13.$visualTransformation
                                                                r5 = 332827112(0x13d689e8, float:5.415718E-27)
                                                                java.lang.String r6 = "CC(remember):CoreTextField.kt#9igjgp"
                                                                androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r14, r5, r6)
                                                                androidx.compose.foundation.text.LegacyTextFieldState r5 = r13.$state
                                                                boolean r5 = r14.changedInstance(r5)
                                                                androidx.compose.foundation.text.LegacyTextFieldState r6 = r13.$state
                                                                java.lang.Object r7 = r14.rememberedValue()
                                                                if (r5 != 0) goto L64
                                                                androidx.compose.runtime.Composer$Companion r5 = androidx.compose.runtime.Composer.INSTANCE
                                                                java.lang.Object r5 = r5.getEmpty()
                                                                if (r7 != r5) goto L6c
                                                            L64:
                                                                androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0 r7 = new androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$$ExternalSyntheticLambda0
                                                                r7.<init>(r6)
                                                                r14.updateRememberedValue(r7)
                                                            L6c:
                                                                kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
                                                                androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r14)
                                                                androidx.compose.ui.Modifier r15 = androidx.compose.foundation.text.TextFieldScroll_androidKt.textFieldScroll(r15, r0, r3, r4, r7)
                                                                androidx.compose.ui.Modifier r0 = r13.$cursorModifier
                                                                androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                androidx.compose.ui.Modifier r0 = r13.$drawModifier
                                                                androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                androidx.compose.ui.text.TextStyle r0 = r13.$textStyle
                                                                androidx.compose.ui.Modifier r15 = androidx.compose.foundation.text.TextFieldSizeKt.textFieldMinSize(r15, r0)
                                                                androidx.compose.ui.Modifier r0 = r13.$onPositionedModifier
                                                                androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                androidx.compose.ui.Modifier r0 = r13.$magnifierModifier
                                                                androidx.compose.ui.Modifier r15 = r15.then(r0)
                                                                androidx.compose.foundation.relocation.BringIntoViewRequester r0 = r13.$bringIntoViewRequester
                                                                androidx.compose.ui.Modifier r15 = androidx.compose.foundation.relocation.BringIntoViewRequesterKt.bringIntoViewRequester(r15, r0)
                                                                androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1 r3 = new androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1
                                                                androidx.compose.foundation.text.selection.TextFieldSelectionManager r4 = r13.$manager
                                                                androidx.compose.foundation.text.LegacyTextFieldState r5 = r13.$state
                                                                boolean r6 = r13.$showHandleAndMagnifier
                                                                boolean r7 = r13.$readOnly
                                                                kotlin.jvm.functions.Function1<androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r8 = r13.$onTextLayout
                                                                androidx.compose.ui.text.input.TextFieldValue r9 = r13.$value
                                                                androidx.compose.ui.text.input.OffsetMapping r10 = r13.$offsetMapping
                                                                androidx.compose.ui.unit.Density r11 = r13.$density
                                                                int r12 = r13.$maxLines
                                                                r3.<init>()
                                                                r0 = 54
                                                                r4 = 1412697320(0x54340ce8, float:3.09324258E12)
                                                                androidx.compose.runtime.internal.ComposableLambda r0 = androidx.compose.runtime.internal.ComposableLambdaKt.rememberComposableLambda(r4, r2, r3, r14, r0)
                                                                kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
                                                                r2 = 48
                                                                androidx.compose.foundation.text.selection.SimpleLayoutKt.SimpleLayout(r15, r0, r14, r2, r1)
                                                                boolean r14 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                                                if (r14 == 0) goto Lc9
                                                                androidx.compose.runtime.ComposerKt.traceEventEnd()
                                                            Lc9:
                                                                return
                                                            Lca:
                                                                r14.skipToGroupEnd()
                                                                return
                                                            */
                                                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5.AnonymousClass1.invoke(androidx.compose.runtime.Composer, int):void");
                                                        }
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                        invoke(composer3, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(Composer composer3, int i49) {
                                                        ComposerKt.sourceInformation(composer3, "C568@26920L5379,568@26906L5393:CoreTextField.kt#423gt5");
                                                        if (!composer3.shouldExecute((i49 & 3) != 2, i49 & 1)) {
                                                            composer3.skipToGroupEnd();
                                                            return;
                                                        }
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-814563849, i49, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:568)");
                                                        }
                                                        function33222.invoke(ComposableLambdaKt.rememberComposableLambda(-44346382, true, new AnonymousClass1(legacyTextFieldState3, textStyle6222, i41, i48222, textFieldScrollerPosition6222, textFieldValue, visualTransformation5222, modifier8222, drawBehind222, onGloballyPositioned222, modifier7222, bringIntoViewRequester3222, textFieldSelectionManager6222, z20, z31222, function15, offsetMapping5222, density), composer3, 54), composer3, 6);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                        }
                                                    }
                                                }, startRestartGroup, 54), startRestartGroup, 384);
                                                if (ComposerKt.isTraceInProgress()) {
                                                }
                                                function32 = function33222;
                                                i28 = i41;
                                                composer2 = startRestartGroup;
                                                function14 = function15;
                                                imeOptions2 = imeOptions6;
                                                textFieldScrollerPosition2 = textFieldScrollerPosition3;
                                                mutableInteractionSource3 = mutableInteractionSource7;
                                                keyboardActions2 = keyboardActions4;
                                                brush2 = brush5222;
                                                z6 = z29222;
                                                modifier3 = modifier6;
                                                i29 = i34;
                                                z5 = z10;
                                                textStyle3 = textStyle4;
                                                z4 = z12;
                                                visualTransformation2 = visualTransformation5222;
                                            } else {
                                                startRestartGroup.skipToGroupEnd();
                                                i28 = i2;
                                                keyboardActions2 = keyboardActions;
                                                z4 = z3;
                                                function32 = function3;
                                                textFieldScrollerPosition2 = textFieldScrollerPosition;
                                                composer2 = startRestartGroup;
                                                brush2 = solidColor;
                                                mutableInteractionSource3 = mutableInteractionSource2;
                                                function14 = function13;
                                                textStyle3 = textStyle2;
                                                modifier3 = modifier2;
                                                visualTransformation2 = visualTransformation;
                                                z5 = z;
                                                i29 = i;
                                                imeOptions2 = imeOptions;
                                                z6 = z2;
                                            }
                                            endRestartGroup = composer2.endRestartGroup();
                                            if (endRestartGroup == null) {
                                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda10
                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(Object obj, Object obj2) {
                                                        Unit CoreTextField$lambda$41;
                                                        CoreTextField$lambda$41 = CoreTextFieldKt.CoreTextField$lambda$41(TextFieldValue.this, function1, modifier3, textStyle3, visualTransformation2, function14, mutableInteractionSource3, brush2, z5, i29, i28, imeOptions2, keyboardActions2, z6, z4, function32, textFieldScrollerPosition2, i3, i4, i5, (Composer) obj, ((Integer) obj2).intValue());
                                                        return CoreTextField$lambda$41;
                                                    }
                                                });
                                                return;
                                            }
                                            return;
                                        }
                                    }
                                    i26 = i5 & 32768;
                                    if (i26 == 0) {
                                    }
                                    i27 = i5 & 65536;
                                    if (i27 == 0) {
                                    }
                                    if (!startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (i25 & 599187) != 599186, i6 & 1)) {
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup == null) {
                                    }
                                }
                                i16 = i15;
                                i17 = i5 & 1024;
                                if (i17 != 0) {
                                }
                                if ((i4 & 48) == 0) {
                                }
                                int i362 = i19;
                                i20 = i5 & 4096;
                                if (i20 != 0) {
                                }
                                i22 = i5 & 8192;
                                if (i22 != 0) {
                                }
                                i24 = i5 & 16384;
                                if (i24 != 0) {
                                }
                                i26 = i5 & 32768;
                                if (i26 == 0) {
                                }
                                i27 = i5 & 65536;
                                if (i27 == 0) {
                                }
                                if (!startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (i25 & 599187) != 599186, i6 & 1)) {
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup == null) {
                                }
                            }
                            i14 = i13;
                            i15 = i5 & 512;
                            if (i15 == 0) {
                            }
                            i16 = i15;
                            i17 = i5 & 1024;
                            if (i17 != 0) {
                            }
                            if ((i4 & 48) == 0) {
                            }
                            int i3622 = i19;
                            i20 = i5 & 4096;
                            if (i20 != 0) {
                            }
                            i22 = i5 & 8192;
                            if (i22 != 0) {
                            }
                            i24 = i5 & 16384;
                            if (i24 != 0) {
                            }
                            i26 = i5 & 32768;
                            if (i26 == 0) {
                            }
                            i27 = i5 & 65536;
                            if (i27 == 0) {
                            }
                            if (!startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (i25 & 599187) != 599186, i6 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                            }
                        }
                        i10 = i5 & 32;
                        if (i10 != 0) {
                        }
                        i11 = i5 & 64;
                        if (i11 != 0) {
                        }
                        i12 = i5 & 128;
                        if (i12 != 0) {
                        }
                        i13 = i5 & 256;
                        if (i13 != 0) {
                        }
                        i14 = i13;
                        i15 = i5 & 512;
                        if (i15 == 0) {
                        }
                        i16 = i15;
                        i17 = i5 & 1024;
                        if (i17 != 0) {
                        }
                        if ((i4 & 48) == 0) {
                        }
                        int i36222 = i19;
                        i20 = i5 & 4096;
                        if (i20 != 0) {
                        }
                        i22 = i5 & 8192;
                        if (i22 != 0) {
                        }
                        i24 = i5 & 16384;
                        if (i24 != 0) {
                        }
                        i26 = i5 & 32768;
                        if (i26 == 0) {
                        }
                        i27 = i5 & 65536;
                        if (i27 == 0) {
                        }
                        if (!startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (i25 & 599187) != 599186, i6 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    textStyle2 = textStyle;
                    i9 = i5 & 16;
                    if (i9 == 0) {
                    }
                    i10 = i5 & 32;
                    if (i10 != 0) {
                    }
                    i11 = i5 & 64;
                    if (i11 != 0) {
                    }
                    i12 = i5 & 128;
                    if (i12 != 0) {
                    }
                    i13 = i5 & 256;
                    if (i13 != 0) {
                    }
                    i14 = i13;
                    i15 = i5 & 512;
                    if (i15 == 0) {
                    }
                    i16 = i15;
                    i17 = i5 & 1024;
                    if (i17 != 0) {
                    }
                    if ((i4 & 48) == 0) {
                    }
                    int i362222 = i19;
                    i20 = i5 & 4096;
                    if (i20 != 0) {
                    }
                    i22 = i5 & 8192;
                    if (i22 != 0) {
                    }
                    i24 = i5 & 16384;
                    if (i24 != 0) {
                    }
                    i26 = i5 & 32768;
                    if (i26 == 0) {
                    }
                    i27 = i5 & 65536;
                    if (i27 == 0) {
                    }
                    if (!startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (i25 & 599187) != 599186, i6 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                modifier2 = modifier;
                i8 = i5 & 8;
                if (i8 != 0) {
                }
                textStyle2 = textStyle;
                i9 = i5 & 16;
                if (i9 == 0) {
                }
                i10 = i5 & 32;
                if (i10 != 0) {
                }
                i11 = i5 & 64;
                if (i11 != 0) {
                }
                i12 = i5 & 128;
                if (i12 != 0) {
                }
                i13 = i5 & 256;
                if (i13 != 0) {
                }
                i14 = i13;
                i15 = i5 & 512;
                if (i15 == 0) {
                }
                i16 = i15;
                i17 = i5 & 1024;
                if (i17 != 0) {
                }
                if ((i4 & 48) == 0) {
                }
                int i3622222 = i19;
                i20 = i5 & 4096;
                if (i20 != 0) {
                }
                i22 = i5 & 8192;
                if (i22 != 0) {
                }
                i24 = i5 & 16384;
                if (i24 != 0) {
                }
                i26 = i5 & 32768;
                if (i26 == 0) {
                }
                i27 = i5 & 65536;
                if (i27 == 0) {
                }
                if (!startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (i25 & 599187) != 599186, i6 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            i7 = i5 & 4;
            if (i7 == 0) {
            }
            modifier2 = modifier;
            i8 = i5 & 8;
            if (i8 != 0) {
            }
            textStyle2 = textStyle;
            i9 = i5 & 16;
            if (i9 == 0) {
            }
            i10 = i5 & 32;
            if (i10 != 0) {
            }
            i11 = i5 & 64;
            if (i11 != 0) {
            }
            i12 = i5 & 128;
            if (i12 != 0) {
            }
            i13 = i5 & 256;
            if (i13 != 0) {
            }
            i14 = i13;
            i15 = i5 & 512;
            if (i15 == 0) {
            }
            i16 = i15;
            i17 = i5 & 1024;
            if (i17 != 0) {
            }
            if ((i4 & 48) == 0) {
            }
            int i36222222 = i19;
            i20 = i5 & 4096;
            if (i20 != 0) {
            }
            i22 = i5 & 8192;
            if (i22 != 0) {
            }
            i24 = i5 & 16384;
            if (i24 != 0) {
            }
            i26 = i5 & 32768;
            if (i26 == 0) {
            }
            i27 = i5 & 65536;
            if (i27 == 0) {
            }
            if (!startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (i25 & 599187) != 599186, i6 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final TextFieldScrollerPosition CoreTextField$lambda$6$lambda$5(Orientation orientation) {
            return new TextFieldScrollerPosition(orientation, 0.0f, 2, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit CoreTextField$lambda$15$lambda$14(LegacyTextFieldState legacyTextFieldState, boolean z, boolean z2, TextInputService textInputService, TextFieldValue textFieldValue, ImeOptions imeOptions, OffsetMapping offsetMapping, TextFieldSelectionManager textFieldSelectionManager, CoroutineScope coroutineScope, BringIntoViewRequester bringIntoViewRequester, FocusState focusState) {
            TextLayoutResultProxy layoutResult;
            if (legacyTextFieldState.getHasFocus() == focusState.isFocused()) {
                return Unit.INSTANCE;
            }
            legacyTextFieldState.setHasFocus(focusState.isFocused());
            if (legacyTextFieldState.getHasFocus() && z && !z2) {
                startInputSession(textInputService, legacyTextFieldState, textFieldValue, imeOptions, offsetMapping);
            } else {
                endInputSession(legacyTextFieldState);
            }
            if (focusState.isFocused() && (layoutResult = legacyTextFieldState.getLayoutResult()) != null) {
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new CoreTextFieldKt$CoreTextField$focusModifier$1$1$1$1(bringIntoViewRequester, textFieldValue, legacyTextFieldState, layoutResult, offsetMapping, null), 3, null);
            }
            if (!focusState.isFocused()) {
                TextFieldSelectionManager.m1861deselect_kEHs6E$foundation_release$default(textFieldSelectionManager, null, 1, null);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit CoreTextField$lambda$19$lambda$18(LegacyTextFieldState legacyTextFieldState, boolean z) {
            legacyTextFieldState.setInTouchMode(z);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit CoreTextField$lambda$22$lambda$21(LegacyTextFieldState legacyTextFieldState, FocusRequester focusRequester, boolean z, boolean z2, TextFieldSelectionManager textFieldSelectionManager, OffsetMapping offsetMapping, Offset offset) {
            tapToFocus(legacyTextFieldState, focusRequester, !z);
            if (legacyTextFieldState.getHasFocus() && z2) {
                if (legacyTextFieldState.getHandleState() != HandleState.Selection) {
                    TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
                    if (layoutResult != null) {
                        TextFieldDelegate.INSTANCE.m1396setCursorOffsetULxng0E$foundation_release(offset.m4353unboximpl(), layoutResult, legacyTextFieldState.getProcessor(), offsetMapping, legacyTextFieldState.getOnValueChange());
                        if (legacyTextFieldState.getTextDelegate().getText().length() > 0) {
                            legacyTextFieldState.setHandleState(HandleState.Cursor);
                        }
                    }
                } else {
                    textFieldSelectionManager.m1865deselect_kEHs6E$foundation_release(offset);
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit CoreTextField$lambda$26$lambda$25(LegacyTextFieldState legacyTextFieldState, TextFieldValue textFieldValue, OffsetMapping offsetMapping, DrawScope drawScope) {
            TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
            if (layoutResult != null) {
                TextFieldDelegate.INSTANCE.m1394drawQ1vqE60$foundation_release(drawScope.getDrawContext().getCanvas(), textFieldValue, legacyTextFieldState.m1325getSelectionPreviewHighlightRanged9O1mEE(), legacyTextFieldState.m1322getDeletionPreviewHighlightRanged9O1mEE(), offsetMapping, layoutResult.getValue(), legacyTextFieldState.getHighlightPaint(), legacyTextFieldState.getSelectionBackgroundColor());
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit CoreTextField$lambda$30$lambda$29(LegacyTextFieldState legacyTextFieldState, boolean z, WindowInfo windowInfo, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, OffsetMapping offsetMapping, LayoutCoordinates layoutCoordinates) {
            TextInputSession inputSession;
            legacyTextFieldState.setLayoutCoordinates(layoutCoordinates);
            TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
            if (layoutResult != null) {
                layoutResult.setInnerTextFieldCoordinates(layoutCoordinates);
            }
            if (z) {
                if (legacyTextFieldState.getHandleState() == HandleState.Selection) {
                    if (legacyTextFieldState.getShowFloatingToolbar() && windowInfo.isWindowFocused()) {
                        textFieldSelectionManager.showSelectionToolbar$foundation_release();
                    } else {
                        textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                    }
                    legacyTextFieldState.setShowSelectionHandleStart(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(textFieldSelectionManager, true));
                    legacyTextFieldState.setShowSelectionHandleEnd(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(textFieldSelectionManager, false));
                    legacyTextFieldState.setShowCursorHandle(TextRange.m6842getCollapsedimpl(textFieldValue.getSelection()));
                } else if (legacyTextFieldState.getHandleState() == HandleState.Cursor) {
                    legacyTextFieldState.setShowCursorHandle(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(textFieldSelectionManager, true));
                }
                notifyFocusedRect(legacyTextFieldState, textFieldValue, offsetMapping);
                TextLayoutResultProxy layoutResult2 = legacyTextFieldState.getLayoutResult();
                if (layoutResult2 != null && (inputSession = legacyTextFieldState.getInputSession()) != null && legacyTextFieldState.getHasFocus()) {
                    TextFieldDelegate.INSTANCE.updateTextLayoutResult$foundation_release(inputSession, textFieldValue, offsetMapping, layoutResult2);
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final DisposableEffectResult CoreTextField$lambda$36$lambda$35(LegacyTextFieldState legacyTextFieldState, TextInputService textInputService, TextFieldValue textFieldValue, ImeOptions imeOptions, DisposableEffectScope disposableEffectScope) {
            if (legacyTextFieldState.getHasFocus()) {
                legacyTextFieldState.setInputSession(TextFieldDelegate.INSTANCE.restartInput$foundation_release(textInputService, textFieldValue, legacyTextFieldState.getProcessor(), imeOptions, legacyTextFieldState.getOnValueChange(), legacyTextFieldState.getOnImeActionPerformed()));
            }
            return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$lambda$36$lambda$35$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit CoreTextField$lambda$38$lambda$37(boolean z, LegacyPlatformTextInputServiceAdapter legacyPlatformTextInputServiceAdapter) {
            if (z) {
                legacyPlatformTextInputServiceAdapter.startStylusHandwriting();
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit CoreTextField$lambda$40$lambda$39(LegacyTextFieldState legacyTextFieldState, long j, DrawScope drawScope) {
            if (legacyTextFieldState.getAutofillHighlightOn() || legacyTextFieldState.getJustAutofilled()) {
                DrawScope.CC.m5206drawRectnJ9OG0$default(drawScope, j, 0L, 0L, 0.0f, null, null, 0, 126, null);
            }
            return Unit.INSTANCE;
        }

        private static final void CoreTextFieldRootBox(final Modifier modifier, final TextFieldSelectionManager textFieldSelectionManager, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
            int i2;
            Composer startRestartGroup = composer.startRestartGroup(2036174316);
            ComposerKt.sourceInformation(startRestartGroup, "C(CoreTextFieldRootBox)N(modifier,manager,content)682@32464L83:CoreTextField.kt#423gt5");
            if ((i & 6) == 0) {
                i2 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
            } else {
                i2 = i;
            }
            if ((i & 48) == 0) {
                i2 |= startRestartGroup.changedInstance(textFieldSelectionManager) ? 32 : 16;
            }
            if ((i & 384) == 0) {
                i2 |= startRestartGroup.changedInstance(function2) ? 256 : 128;
            }
            if (!startRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
                startRestartGroup.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2036174316, i2, -1, "androidx.compose.foundation.text.CoreTextFieldRootBox (CoreTextField.kt:681)");
                }
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                int m = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, modifier);
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
                Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(m))) {
                    m3905constructorimpl.updateRememberedValue(Integer.valueOf(m));
                    m3905constructorimpl.apply(Integer.valueOf(m), setCompositeKeyHash);
                }
                Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1799443472, "C682@32512L33:CoreTextField.kt#423gt5");
                ContextMenu_androidKt.ContextMenuArea(textFieldSelectionManager, function2, startRestartGroup, (i2 >> 3) & 126);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        Unit CoreTextFieldRootBox$lambda$43;
                        CoreTextFieldRootBox$lambda$43 = CoreTextFieldKt.CoreTextFieldRootBox$lambda$43(Modifier.this, textFieldSelectionManager, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                        return CoreTextFieldRootBox$lambda$43;
                    }
                });
            }
        }

        private static final Modifier previewKeyEventToDeselectOnBack(Modifier modifier, final LegacyTextFieldState legacyTextFieldState, final TextFieldSelectionManager textFieldSelectionManager) {
            return KeyInputModifierKt.onPreviewKeyEvent(modifier, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$previewKeyEventToDeselectOnBack$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                    return m1300invokeZmokQxo(keyEvent.m5765unboximpl());
                }

                /* renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m1300invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    boolean z;
                    if (LegacyTextFieldState.this.getHandleState() == HandleState.Selection && KeyEventHelpers_androidKt.m1303cancelsTextSelectionZmokQxo(keyEvent)) {
                        z = true;
                        TextFieldSelectionManager.m1861deselect_kEHs6E$foundation_release$default(textFieldSelectionManager, null, 1, null);
                    } else {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
            });
        }

        public static final void tapToFocus(LegacyTextFieldState legacyTextFieldState, FocusRequester focusRequester, boolean z) {
            SoftwareKeyboardController keyboardController;
            if (!legacyTextFieldState.getHasFocus()) {
                FocusRequester.m4250requestFocus3ESFkO8$default(focusRequester, 0, 1, null);
            } else {
                if (!z || (keyboardController = legacyTextFieldState.getKeyboardController()) == null) {
                    return;
                }
                keyboardController.show();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void startInputSession(TextInputService textInputService, LegacyTextFieldState legacyTextFieldState, TextFieldValue textFieldValue, ImeOptions imeOptions, OffsetMapping offsetMapping) {
            legacyTextFieldState.setInputSession(TextFieldDelegate.INSTANCE.onFocus$foundation_release(textInputService, textFieldValue, legacyTextFieldState.getProcessor(), imeOptions, legacyTextFieldState.getOnValueChange(), legacyTextFieldState.getOnImeActionPerformed()));
            notifyFocusedRect(legacyTextFieldState, textFieldValue, offsetMapping);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void endInputSession(LegacyTextFieldState legacyTextFieldState) {
            TextInputSession inputSession = legacyTextFieldState.getInputSession();
            if (inputSession != null) {
                TextFieldDelegate.INSTANCE.onBlur$foundation_release(inputSession, legacyTextFieldState.getProcessor(), legacyTextFieldState.getOnValueChange());
            }
            legacyTextFieldState.setInputSession(null);
        }

        public static final Object bringSelectionEndIntoView(BringIntoViewRequester bringIntoViewRequester, TextFieldValue textFieldValue, TextDelegate textDelegate, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, Continuation<? super Unit> continuation) {
            Rect rect;
            int originalToTransformed = offsetMapping.originalToTransformed(TextRange.m6845getMaximpl(textFieldValue.getSelection()));
            if (originalToTransformed < textLayoutResult.getLayoutInput().getText().length()) {
                rect = textLayoutResult.getBoundingBox(originalToTransformed);
            } else if (originalToTransformed != 0) {
                rect = textLayoutResult.getBoundingBox(originalToTransformed - 1);
            } else {
                rect = new Rect(0.0f, 0.0f, 1.0f, (int) (TextFieldDelegateKt.computeSizeForDefaultText$default(textDelegate.getStyle(), textDelegate.getDensity(), textDelegate.getFontFamilyResolver(), null, 0, 24, null) & 4294967295L));
            }
            Object bringIntoView = bringIntoViewRequester.bringIntoView(rect, continuation);
            return bringIntoView == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? bringIntoView : Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void SelectionToolbarAndHandles(final TextFieldSelectionManager textFieldSelectionManager, final boolean z, Composer composer, final int i) {
            int i2;
            TextLayoutResultProxy layoutResult;
            TextLayoutResult value;
            Composer startRestartGroup = composer.startRestartGroup(626339208);
            ComposerKt.sourceInformation(startRestartGroup, "C(SelectionToolbarAndHandles)N(manager,show):CoreTextField.kt#423gt5");
            if ((i & 6) == 0) {
                i2 = (startRestartGroup.changedInstance(textFieldSelectionManager) ? 4 : 2) | i;
            } else {
                i2 = i;
            }
            if ((i & 48) == 0) {
                i2 |= startRestartGroup.changed(z) ? 32 : 16;
            }
            if (!startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
                startRestartGroup.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(626339208, i2, -1, "androidx.compose.foundation.text.SelectionToolbarAndHandles (CoreTextField.kt:1034)");
                }
                if (z) {
                    startRestartGroup.startReplaceGroup(1529773841);
                    ComposerKt.sourceInformation(startRestartGroup, "");
                    LegacyTextFieldState state = textFieldSelectionManager.getState();
                    TextLayoutResult textLayoutResult = null;
                    if (state != null && (layoutResult = state.getLayoutResult()) != null && (value = layoutResult.getValue()) != null) {
                        LegacyTextFieldState state2 = textFieldSelectionManager.getState();
                        if (!(state2 != null ? state2.getIsLayoutResultStale() : true)) {
                            textLayoutResult = value;
                        }
                    }
                    if (textLayoutResult == null) {
                        startRestartGroup.startReplaceGroup(1530097387);
                    } else {
                        startRestartGroup.startReplaceGroup(1530097388);
                        ComposerKt.sourceInformation(startRestartGroup, "");
                        if (!TextRange.m6842getCollapsedimpl(textFieldSelectionManager.getValue$foundation_release().getSelection())) {
                            startRestartGroup.startReplaceGroup(2109807302);
                            ComposerKt.sourceInformation(startRestartGroup, "");
                            int originalToTransformed = textFieldSelectionManager.getOffsetMapping().originalToTransformed(TextRange.m6848getStartimpl(textFieldSelectionManager.getValue$foundation_release().getSelection()));
                            int originalToTransformed2 = textFieldSelectionManager.getOffsetMapping().originalToTransformed(TextRange.m6843getEndimpl(textFieldSelectionManager.getValue$foundation_release().getSelection()));
                            ResolvedTextDirection bidiRunDirection = textLayoutResult.getBidiRunDirection(originalToTransformed);
                            ResolvedTextDirection bidiRunDirection2 = textLayoutResult.getBidiRunDirection(Math.max(originalToTransformed2 - 1, 0));
                            LegacyTextFieldState state3 = textFieldSelectionManager.getState();
                            if (state3 != null && state3.getShowSelectionHandleStart()) {
                                startRestartGroup.startReplaceGroup(2110225306);
                                ComposerKt.sourceInformation(startRestartGroup, "1050@47759L220");
                                TextFieldSelectionManagerKt.TextFieldSelectionHandle(true, bidiRunDirection, textFieldSelectionManager, startRestartGroup, ((i2 << 6) & 896) | 6);
                                startRestartGroup.endReplaceGroup();
                            } else {
                                startRestartGroup.startReplaceGroup(2110490542);
                                startRestartGroup.endReplaceGroup();
                            }
                            LegacyTextFieldState state4 = textFieldSelectionManager.getState();
                            if (state4 != null && state4.getShowSelectionHandleEnd()) {
                                startRestartGroup.startReplaceGroup(2110574459);
                                ComposerKt.sourceInformation(startRestartGroup, "1057@48111L219");
                                TextFieldSelectionManagerKt.TextFieldSelectionHandle(false, bidiRunDirection2, textFieldSelectionManager, startRestartGroup, ((i2 << 6) & 896) | 6);
                                startRestartGroup.endReplaceGroup();
                            } else {
                                startRestartGroup.startReplaceGroup(2110838734);
                                startRestartGroup.endReplaceGroup();
                            }
                            startRestartGroup.endReplaceGroup();
                        } else {
                            startRestartGroup.startReplaceGroup(2110860558);
                            startRestartGroup.endReplaceGroup();
                        }
                        LegacyTextFieldState state5 = textFieldSelectionManager.getState();
                        if (state5 != null) {
                            if (textFieldSelectionManager.isTextChanged$foundation_release()) {
                                state5.setShowFloatingToolbar(false);
                            }
                            if (state5.getHasFocus()) {
                                if (state5.getShowFloatingToolbar()) {
                                    textFieldSelectionManager.showSelectionToolbar$foundation_release();
                                } else {
                                    textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                                }
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                    startRestartGroup.endReplaceGroup();
                    startRestartGroup.endReplaceGroup();
                } else {
                    startRestartGroup.startReplaceGroup(1989076778);
                    startRestartGroup.endReplaceGroup();
                    textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        Unit SelectionToolbarAndHandles$lambda$49;
                        SelectionToolbarAndHandles$lambda$49 = CoreTextFieldKt.SelectionToolbarAndHandles$lambda$49(TextFieldSelectionManager.this, z, i, (Composer) obj, ((Integer) obj2).intValue());
                        return SelectionToolbarAndHandles$lambda$49;
                    }
                });
            }
        }

        public static final void TextFieldCursorHandle(final TextFieldSelectionManager textFieldSelectionManager, Composer composer, final int i) {
            int i2;
            AnnotatedString transformedText$foundation_release;
            Composer startRestartGroup = composer.startRestartGroup(-1436003720);
            ComposerKt.sourceInformation(startRestartGroup, "C(TextFieldCursorHandle)N(manager):CoreTextField.kt#423gt5");
            if ((i & 6) == 0) {
                i2 = (startRestartGroup.changedInstance(textFieldSelectionManager) ? 4 : 2) | i;
            } else {
                i2 = i;
            }
            if (!startRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
                startRestartGroup.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1436003720, i2, -1, "androidx.compose.foundation.text.TextFieldCursorHandle (CoreTextField.kt:1081)");
                }
                LegacyTextFieldState state = textFieldSelectionManager.getState();
                if (state != null && state.getShowCursorHandle() && (transformedText$foundation_release = textFieldSelectionManager.getTransformedText$foundation_release()) != null && transformedText$foundation_release.length() > 0) {
                    startRestartGroup.startReplaceGroup(-2112330600);
                    ComposerKt.sourceInformation(startRestartGroup, "1083@49274L50,1084@49387L7,1086@49447L12,1088@49532L645,1100@50209L385,1085@49404L1201");
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1315066198, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean changed = startRestartGroup.changed(textFieldSelectionManager);
                    Object rememberedValue = startRestartGroup.rememberedValue();
                    if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                        rememberedValue = textFieldSelectionManager.cursorDragObserver$foundation_release();
                        startRestartGroup.updateRememberedValue(rememberedValue);
                    }
                    final TextDragObserver textDragObserver = (TextDragObserver) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object consume = startRestartGroup.consume(localDensity);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    final long m1867getCursorPositiontuRUvjQ$foundation_release = textFieldSelectionManager.m1867getCursorPositiontuRUvjQ$foundation_release((Density) consume);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1315060700, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean changed2 = startRestartGroup.changed(m1867getCursorPositiontuRUvjQ$foundation_release);
                    OffsetProvider rememberedValue2 = startRestartGroup.rememberedValue();
                    if (changed2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        rememberedValue2 = new OffsetProvider() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1$1
                            @Override // androidx.compose.foundation.text.selection.OffsetProvider
                            /* renamed from: provide-F1C5BW0 */
                            public final long mo1265provideF1C5BW0() {
                                return m1867getCursorPositiontuRUvjQ$foundation_release;
                            }
                        };
                        startRestartGroup.updateRememberedValue(rememberedValue2);
                    }
                    OffsetProvider offsetProvider = (OffsetProvider) rememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1315057347, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean changedInstance = startRestartGroup.changedInstance(textDragObserver) | startRestartGroup.changedInstance(textFieldSelectionManager);
                    PointerInputEventHandler rememberedValue3 = startRestartGroup.rememberedValue();
                    if (changedInstance || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        rememberedValue3 = new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1

                            /* compiled from: CoreTextField.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1", f = "CoreTextField.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1, reason: invalid class name */
                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ TextFieldSelectionManager $manager;
                                final /* synthetic */ TextDragObserver $observer;
                                final /* synthetic */ PointerInputScope $this_pointerInput;
                                private /* synthetic */ Object L$0;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass1(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, TextFieldSelectionManager textFieldSelectionManager, Continuation<? super AnonymousClass1> continuation) {
                                    super(2, continuation);
                                    this.$this_pointerInput = pointerInputScope;
                                    this.$observer = textDragObserver;
                                    this.$manager = textFieldSelectionManager;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_pointerInput, this.$observer, this.$manager, continuation);
                                    anonymousClass1.L$0 = obj;
                                    return anonymousClass1;
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object obj) {
                                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    if (this.label == 0) {
                                        ResultKt.throwOnFailure(obj);
                                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new C00131(this.$this_pointerInput, this.$observer, null), 1, null);
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass2(this.$this_pointerInput, this.$manager, null), 1, null);
                                        return Unit.INSTANCE;
                                    }
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }

                                /* compiled from: CoreTextField.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$1", f = "CoreTextField.kt", i = {}, l = {1094}, m = "invokeSuspend", n = {}, s = {})
                                /* renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$1, reason: invalid class name and collision with other inner class name */
                                static final class C00131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ TextDragObserver $observer;
                                    final /* synthetic */ PointerInputScope $this_pointerInput;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C00131(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, Continuation<? super C00131> continuation) {
                                        super(2, continuation);
                                        this.$this_pointerInput = pointerInputScope;
                                        this.$observer = textDragObserver;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new C00131(this.$this_pointerInput, this.$observer, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((C00131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            this.label = 1;
                                            if (LongPressTextDragObserverKt.detectDownAndDragGesturesWithObserver(this.$this_pointerInput, this.$observer, this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }

                                /* compiled from: CoreTextField.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$2", f = "CoreTextField.kt", i = {}, l = {1097}, m = "invokeSuspend", n = {}, s = {})
                                /* renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$2, reason: invalid class name */
                                static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ TextFieldSelectionManager $manager;
                                    final /* synthetic */ PointerInputScope $this_pointerInput;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass2(PointerInputScope pointerInputScope, TextFieldSelectionManager textFieldSelectionManager, Continuation<? super AnonymousClass2> continuation) {
                                        super(2, continuation);
                                        this.$this_pointerInput = pointerInputScope;
                                        this.$manager = textFieldSelectionManager;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass2(this.$this_pointerInput, this.$manager, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            PointerInputScope pointerInputScope = this.$this_pointerInput;
                                            final TextFieldSelectionManager textFieldSelectionManager = this.$manager;
                                            this.label = 1;
                                            if (TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, 
                                            /*  JADX ERROR: Method code generation error
                                                jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$2$$ExternalSyntheticLambda0, state: NOT_LOADED
                                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:305)
                                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                                	at jadx.core.codegen.ConditionGen.addCompare(ConditionGen.java:129)
                                                	at jadx.core.codegen.ConditionGen.add(ConditionGen.java:57)
                                                	at jadx.core.codegen.ConditionGen.add(ConditionGen.java:46)
                                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:115)
                                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                                */
                                            /*
                                                this = this;
                                                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                                int r1 = r9.label
                                                r2 = 1
                                                if (r1 == 0) goto L17
                                                if (r1 != r2) goto Lf
                                                kotlin.ResultKt.throwOnFailure(r10)
                                                goto L34
                                            Lf:
                                                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                                                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                                                r10.<init>(r0)
                                                throw r10
                                            L17:
                                                kotlin.ResultKt.throwOnFailure(r10)
                                                androidx.compose.ui.input.pointer.PointerInputScope r1 = r9.$this_pointerInput
                                                androidx.compose.foundation.text.selection.TextFieldSelectionManager r10 = r9.$manager
                                                androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$2$$ExternalSyntheticLambda0 r5 = new androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$2$$ExternalSyntheticLambda0
                                                r5.<init>(r10)
                                                r6 = r9
                                                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                                                r9.label = r2
                                                r2 = 0
                                                r3 = 0
                                                r4 = 0
                                                r7 = 7
                                                r8 = 0
                                                java.lang.Object r10 = androidx.compose.foundation.gestures.TapGestureDetectorKt.detectTapGestures$default(r1, r2, r3, r4, r5, r6, r7, r8)
                                                if (r10 != r0) goto L34
                                                return r0
                                            L34:
                                                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                                                return r10
                                            */
                                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1.AnonymousClass1.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
                                        }

                                        /* JADX INFO: Access modifiers changed from: private */
                                        public static final Unit invokeSuspend$lambda$0(TextFieldSelectionManager textFieldSelectionManager, Offset offset) {
                                            textFieldSelectionManager.showSelectionToolbar$foundation_release();
                                            return Unit.INSTANCE;
                                        }
                                    }
                                }

                                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                                public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                                    Object coroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass1(pointerInputScope, TextDragObserver.this, textFieldSelectionManager, null), continuation);
                                    return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
                                }
                            };
                            startRestartGroup.updateRememberedValue(rememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, textDragObserver, (PointerInputEventHandler) rememberedValue3);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1315035943, "CC(remember):CoreTextField.kt#9igjgp");
                        boolean changed3 = startRestartGroup.changed(m1867getCursorPositiontuRUvjQ$foundation_release);
                        Object rememberedValue4 = startRestartGroup.rememberedValue();
                        if (changed3 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue4 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    Unit TextFieldCursorHandle$lambda$54$lambda$53;
                                    TextFieldCursorHandle$lambda$54$lambda$53 = CoreTextFieldKt.TextFieldCursorHandle$lambda$54$lambda$53(m1867getCursorPositiontuRUvjQ$foundation_release, (SemanticsPropertyReceiver) obj);
                                    return TextFieldCursorHandle$lambda$54$lambda$53;
                                }
                            };
                            startRestartGroup.updateRememberedValue(rememberedValue4);
                        }
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        AndroidCursorHandle_androidKt.m1247CursorHandleUSBMPiE(offsetProvider, SemanticsModifierKt.semantics$default(pointerInput, false, (Function1) rememberedValue4, 1, null), 0L, startRestartGroup, 0, 4);
                        startRestartGroup.endReplaceGroup();
                    } else {
                        startRestartGroup.startReplaceGroup(-2111021718);
                        startRestartGroup.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            Unit TextFieldCursorHandle$lambda$55;
                            TextFieldCursorHandle$lambda$55 = CoreTextFieldKt.TextFieldCursorHandle$lambda$55(TextFieldSelectionManager.this, i, (Composer) obj, ((Integer) obj2).intValue());
                            return TextFieldCursorHandle$lambda$55;
                        }
                    });
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final Unit TextFieldCursorHandle$lambda$54$lambda$53(long j, SemanticsPropertyReceiver semanticsPropertyReceiver) {
                semanticsPropertyReceiver.set(SelectionHandlesKt.getSelectionHandleInfoKey(), new SelectionHandleInfo(Handle.Cursor, j, SelectionHandleAnchor.Middle, true, null));
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void notifyFocusedRect(LegacyTextFieldState legacyTextFieldState, TextFieldValue textFieldValue, OffsetMapping offsetMapping) {
                Snapshot.Companion companion = Snapshot.INSTANCE;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
                    if (layoutResult == null) {
                        return;
                    }
                    TextInputSession inputSession = legacyTextFieldState.getInputSession();
                    if (inputSession == null) {
                        return;
                    }
                    LayoutCoordinates layoutCoordinates = legacyTextFieldState.getLayoutCoordinates();
                    if (layoutCoordinates == null) {
                        return;
                    }
                    TextFieldDelegate.INSTANCE.notifyFocusedRect$foundation_release(textFieldValue, legacyTextFieldState.getTextDelegate(), layoutResult.getValue(), layoutCoordinates, inputSession, legacyTextFieldState.getHasFocus(), offsetMapping);
                    Unit unit = Unit.INSTANCE;
                } finally {
                    companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                }
            }

            private static final Modifier addContextMenuComponents(Modifier modifier, TextFieldSelectionManager textFieldSelectionManager, CoroutineScope coroutineScope) {
                return ComposeFoundationFlags.isNewContextMenuEnabled ? TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents(modifier, textFieldSelectionManager, coroutineScope) : modifier;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final boolean CoreTextField$lambda$16(State<Boolean> state) {
                return state.getValue().booleanValue();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DisposableEffectResult CoreTextField$lambda$33$lambda$32(final TextFieldSelectionManager textFieldSelectionManager, DisposableEffectScope disposableEffectScope) {
                return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$lambda$33$lambda$32$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public void dispose() {
                        TextFieldSelectionManager.this.hideSelectionToolbar$foundation_release();
                    }
                };
            }
        }
