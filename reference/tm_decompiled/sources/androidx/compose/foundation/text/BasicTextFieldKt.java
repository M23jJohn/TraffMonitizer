package androidx.compose.foundation.text;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.HoverInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.contextmenu.modifier.ToolbarRequesterImpl;
import androidx.compose.foundation.text.handwriting.StylusHandwritingKt;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.OutputTransformation;
import androidx.compose.foundation.text.input.TextFieldDecorator;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.internal.CodepointTransformation;
import androidx.compose.foundation.text.input.internal.SingleLineCodepointTransformation;
import androidx.compose.foundation.text.input.internal.TextFieldCoreModifier;
import androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifier;
import androidx.compose.foundation.text.input.internal.TextFieldTextLayoutModifier;
import androidx.compose.foundation.text.input.internal.TextLayoutState;
import androidx.compose.foundation.text.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt;
import androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt;
import androidx.compose.foundation.text.selection.OffsetProvider;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors_androidKt;
import androidx.compose.foundation.text.selection.SelectedTextType;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: BasicTextField.kt */
@Metadata(d1 = {"\u0000Ð\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÛ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u001228\b\u0002\u0010\u0013\u001a2\u0012\u0004\u0012\u00020\u0015\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0014¢\u0006\u0002\b\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010$\u001a\u00020%H\u0007¢\u0006\u0002\u0010&\u001añ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u001228\b\u0002\u0010\u0013\u001a2\u0012\u0004\u0012\u00020\u0015\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0014¢\u0006\u0002\b\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010)\u001a\u00020\u0007H\u0001¢\u0006\u0002\u0010*\u001a\u001c\u0010+\u001a\u00020\u0005*\u00020\u00052\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002\u001a\u0015\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020-H\u0001¢\u0006\u0002\u00102\u001a\u0015\u00103\u001a\u00020\u00012\u0006\u00101\u001a\u00020-H\u0001¢\u0006\u0002\u00102\u001aî\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u00108\u001a\u0002092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\u00010;2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020\u00072\b\b\u0002\u0010?\u001a\u00020@2\b\b\u0002\u0010A\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020C2\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010;2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f23\b\u0002\u0010D\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\bE¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00010;¢\u0006\u0002\bEH\u0007¢\u0006\u0002\u0010G\u001aî\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u00108\u001a\u00020H2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u00010;2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020\u00072\b\b\u0002\u0010?\u001a\u00020@2\b\b\u0002\u0010A\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020C2\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010;2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f23\b\u0002\u0010D\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\bE¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00010;¢\u0006\u0002\bEH\u0007¢\u0006\u0002\u0010I\u001aâ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u00108\u001a\u0002092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\u00010;2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020\u00072\b\b\u0002\u0010?\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020C2\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010;2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f23\b\u0002\u0010D\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\bE¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00010;¢\u0006\u0002\bEH\u0007¢\u0006\u0002\u0010J\u001aâ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u00108\u001a\u00020H2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u00010;2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020\u00072\b\b\u0002\u0010?\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020C2\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010;2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f23\b\u0002\u0010D\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\bE¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00010;¢\u0006\u0002\bEH\u0007¢\u0006\u0002\u0010K\"\u000e\u00104\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0004\n\u0002\u00107¨\u0006L²\u0006\n\u0010M\u001a\u00020NX\u008a\u0084\u0002²\u0006\n\u0010O\u001a\u00020NX\u008a\u0084\u0002²\u0006\n\u0010P\u001a\u00020NX\u008a\u0084\u0002²\u0006\n\u0010Q\u001a\u00020HX\u008a\u008e\u0002²\u0006\n\u0010R\u001a\u000209X\u008a\u008e\u0002"}, d2 = {"BasicTextField", "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "lineLimits", "Landroidx/compose/foundation/text/input/TextFieldLineLimits;", "onTextLayout", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/Function0;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "getResult", "Lkotlin/ExtensionFunctionType;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "decorator", "Landroidx/compose/foundation/text/input/TextFieldDecorator;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/foundation/text/input/TextFieldLineLimits;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/text/input/TextFieldDecorator;Landroidx/compose/foundation/ScrollState;Landroidx/compose/runtime/Composer;III)V", "codepointTransformation", "Landroidx/compose/foundation/text/input/internal/CodepointTransformation;", "isPassword", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/foundation/text/input/TextFieldLineLimits;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/internal/CodepointTransformation;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/text/input/TextFieldDecorator;Landroidx/compose/foundation/ScrollState;ZLandroidx/compose/runtime/Composer;III)V", "addContextMenuComponents", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "TextFieldCursorHandle", "selectionState", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/runtime/Composer;I)V", "TextFieldSelectionHandles", "DefaultTextFieldDecorator", "MinTouchTargetSizeForHandles", "Landroidx/compose/ui/unit/DpSize;", "J", "value", "", "onValueChange", "Lkotlin/Function1;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "minLines", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "decorationBox", "Landroidx/compose/runtime/Composable;", "innerTextField", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "foundation_release", "cursorHandleState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState;", "startHandleState", "endHandleState", "textFieldValueState", "lastTextValue"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BasicTextFieldKt {
    private static final TextFieldDecorator DefaultTextFieldDecorator = BasicTextFieldKt$DefaultTextFieldDecorator$1.INSTANCE;
    private static final long MinTouchTargetSizeForHandles;

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$0(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, TextFieldLineLimits textFieldLineLimits, Function2 function2, MutableInteractionSource mutableInteractionSource, Brush brush, OutputTransformation outputTransformation, TextFieldDecorator textFieldDecorator, ScrollState scrollState, int i, int i2, int i3, Composer composer, int i4) {
        BasicTextField(textFieldState, modifier, z, z2, inputTransformation, textStyle, keyboardOptions, keyboardActionHandler, textFieldLineLimits, (Function2<? super Density, ? super Function0<TextLayoutResult>, Unit>) function2, mutableInteractionSource, brush, outputTransformation, textFieldDecorator, scrollState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$17(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, TextFieldLineLimits textFieldLineLimits, Function2 function2, MutableInteractionSource mutableInteractionSource, Brush brush, CodepointTransformation codepointTransformation, OutputTransformation outputTransformation, TextFieldDecorator textFieldDecorator, ScrollState scrollState, boolean z3, int i, int i2, int i3, Composer composer, int i4) {
        BasicTextField(textFieldState, modifier, z, z2, inputTransformation, textStyle, keyboardOptions, keyboardActionHandler, textFieldLineLimits, function2, mutableInteractionSource, brush, codepointTransformation, outputTransformation, textFieldDecorator, scrollState, z3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$47(String str, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z3, int i, int i2, VisualTransformation visualTransformation, Function1 function12, MutableInteractionSource mutableInteractionSource, Brush brush, Function3 function3, int i3, int i4, int i5, Composer composer, int i6) {
        BasicTextField(str, (Function1<? super String, Unit>) function1, modifier, z, z2, textStyle, keyboardOptions, keyboardActions, z3, i, i2, visualTransformation, (Function1<? super TextLayoutResult, Unit>) function12, mutableInteractionSource, brush, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) function3, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$52(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z3, int i, int i2, VisualTransformation visualTransformation, Function1 function12, MutableInteractionSource mutableInteractionSource, Brush brush, Function3 function3, int i3, int i4, int i5, Composer composer, int i6) {
        BasicTextField(textFieldValue, (Function1<? super TextFieldValue, Unit>) function1, modifier, z, z2, textStyle, keyboardOptions, keyboardActions, z3, i, i2, visualTransformation, (Function1<? super TextLayoutResult, Unit>) function12, mutableInteractionSource, brush, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) function3, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$56(String str, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z3, int i, VisualTransformation visualTransformation, Function1 function12, MutableInteractionSource mutableInteractionSource, Brush brush, Function3 function3, int i2, int i3, int i4, Composer composer, int i5) {
        BasicTextField(str, function1, modifier, z, z2, textStyle, keyboardOptions, keyboardActions, z3, i, visualTransformation, function12, mutableInteractionSource, brush, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$60(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z3, int i, VisualTransformation visualTransformation, Function1 function12, MutableInteractionSource mutableInteractionSource, Brush brush, Function3 function3, int i2, int i3, int i4, Composer composer, int i5) {
        BasicTextField(textFieldValue, function1, modifier, z, z2, textStyle, keyboardOptions, keyboardActions, z3, i, visualTransformation, function12, mutableInteractionSource, brush, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TextFieldCursorHandle$lambda$23(TextFieldSelectionState textFieldSelectionState, int i, Composer composer, int i2) {
        TextFieldCursorHandle(textFieldSelectionState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TextFieldSelectionHandles$lambda$34(TextFieldSelectionState textFieldSelectionState, int i, Composer composer, int i2) {
        TextFieldSelectionHandles(textFieldSelectionState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void BasicTextField(final TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, TextFieldLineLimits textFieldLineLimits, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function2, MutableInteractionSource mutableInteractionSource, Brush brush, OutputTransformation outputTransformation, TextFieldDecorator textFieldDecorator, ScrollState scrollState, Composer composer, final int i, final int i2, final int i3) {
        TextFieldState textFieldState2;
        int i4;
        Modifier modifier2;
        int i5;
        boolean z3;
        int i6;
        boolean z4;
        int i7;
        InputTransformation inputTransformation2;
        int i8;
        TextStyle textStyle2;
        int i9;
        KeyboardOptions keyboardOptions2;
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
        int i23;
        Composer composer2;
        final TextFieldLineLimits textFieldLineLimits2;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function22;
        final TextFieldDecorator textFieldDecorator2;
        final boolean z5;
        final boolean z6;
        final InputTransformation inputTransformation3;
        final TextStyle textStyle3;
        final Modifier modifier3;
        final KeyboardOptions keyboardOptions3;
        final KeyboardActionHandler keyboardActionHandler2;
        final MutableInteractionSource mutableInteractionSource2;
        final Brush brush2;
        final OutputTransformation outputTransformation2;
        final ScrollState scrollState2;
        ScopeUpdateScope endRestartGroup;
        Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function23;
        MutableInteractionSource mutableInteractionSource3;
        ScrollState scrollState3;
        TextFieldLineLimits textFieldLineLimits3;
        OutputTransformation outputTransformation3;
        Brush brush3;
        TextFieldDecorator textFieldDecorator3;
        KeyboardActionHandler keyboardActionHandler3;
        TextStyle textStyle4;
        Composer startRestartGroup = composer.startRestartGroup(469439921);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicTextField)N(state,modifier,enabled,readOnly,inputTransformation,textStyle,keyboardOptions,onKeyboardAction,lineLimits,onTextLayout,interactionSource,cursorBrush,outputTransformation,decorator,scrollState)202@12315L610:BasicTextField.kt#423gt5");
        if ((i3 & 1) != 0) {
            i4 = i | 6;
            textFieldState2 = textFieldState;
        } else if ((i & 6) == 0) {
            textFieldState2 = textFieldState;
            i4 = (startRestartGroup.changed(textFieldState2) ? 4 : 2) | i;
        } else {
            textFieldState2 = textFieldState;
            i4 = i;
        }
        int i24 = i3 & 2;
        if (i24 != 0) {
            i4 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i5 = i3 & 4;
            if (i5 == 0) {
                i4 |= 384;
            } else if ((i & 384) == 0) {
                z3 = z;
                i4 |= startRestartGroup.changed(z3) ? 256 : 128;
                i6 = i3 & 8;
                if (i6 != 0) {
                    i4 |= 3072;
                } else if ((i & 3072) == 0) {
                    z4 = z2;
                    i4 |= startRestartGroup.changed(z4) ? 2048 : 1024;
                    i7 = i3 & 16;
                    int i25 = 8192;
                    if (i7 == 0) {
                        i4 |= 24576;
                    } else if ((i & 24576) == 0) {
                        inputTransformation2 = inputTransformation;
                        i4 |= startRestartGroup.changed(inputTransformation2) ? 16384 : 8192;
                        i8 = i3 & 32;
                        if (i8 != 0) {
                            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            textStyle2 = textStyle;
                        } else {
                            textStyle2 = textStyle;
                            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i4 |= startRestartGroup.changed(textStyle2) ? 131072 : 65536;
                            }
                        }
                        i9 = i3 & 64;
                        if (i9 != 0) {
                            i4 |= 1572864;
                            keyboardOptions2 = keyboardOptions;
                        } else {
                            keyboardOptions2 = keyboardOptions;
                            if ((i & 1572864) == 0) {
                                i4 |= startRestartGroup.changed(keyboardOptions2) ? 1048576 : 524288;
                            }
                        }
                        i10 = i3 & 128;
                        if (i10 != 0) {
                            i4 |= 12582912;
                        } else if ((i & 12582912) == 0) {
                            i4 |= startRestartGroup.changed(keyboardActionHandler) ? 8388608 : 4194304;
                        }
                        i11 = i3 & 256;
                        if (i11 != 0) {
                            i4 |= 100663296;
                        } else if ((i & 100663296) == 0) {
                            i12 = i11;
                            i4 |= startRestartGroup.changed(textFieldLineLimits) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                            i13 = i3 & 512;
                            if (i13 == 0) {
                                i4 |= 805306368;
                            } else if ((i & 805306368) == 0) {
                                i14 = i13;
                                i4 |= startRestartGroup.changedInstance(function2) ? 536870912 : 268435456;
                                i15 = i3 & 1024;
                                if (i15 != 0) {
                                    i17 = i2 | 6;
                                    i16 = i15;
                                } else if ((i2 & 6) == 0) {
                                    i16 = i15;
                                    i17 = i2 | (startRestartGroup.changed(mutableInteractionSource) ? 4 : 2);
                                } else {
                                    i16 = i15;
                                    i17 = i2;
                                }
                                i18 = i3 & 2048;
                                if (i18 != 0) {
                                    i17 |= 48;
                                    i19 = i18;
                                } else if ((i2 & 48) == 0) {
                                    i19 = i18;
                                    i17 |= startRestartGroup.changed(brush) ? 32 : 16;
                                } else {
                                    i19 = i18;
                                }
                                int i26 = i17;
                                i20 = i3 & 4096;
                                if (i20 != 0) {
                                    i21 = i26 | 384;
                                } else if ((i2 & 384) == 0) {
                                    i21 = i26 | (startRestartGroup.changed(outputTransformation) ? 256 : 128);
                                } else {
                                    i21 = i26;
                                }
                                i22 = i3 & 8192;
                                if (i22 != 0) {
                                    i23 = i21 | 3072;
                                } else {
                                    int i27 = i21;
                                    if ((i2 & 3072) == 0) {
                                        i23 = i27 | ((i2 & 4096) == 0 ? startRestartGroup.changed(textFieldDecorator) : startRestartGroup.changedInstance(textFieldDecorator) ? 2048 : 1024);
                                    } else {
                                        i23 = i27;
                                    }
                                }
                                if ((i2 & 24576) == 0) {
                                    if ((i3 & 16384) == 0 && startRestartGroup.changed(scrollState)) {
                                        i25 = 16384;
                                    }
                                    i23 |= i25;
                                }
                                if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i23 & 9363) != 9362, i4 & 1)) {
                                    startRestartGroup.startDefaults();
                                    ComposerKt.sourceInformation(startRestartGroup, "198@12169L21");
                                    if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                        startRestartGroup.skipToGroupEnd();
                                        if ((i3 & 16384) != 0) {
                                            i23 &= -57345;
                                        }
                                        textFieldLineLimits3 = textFieldLineLimits;
                                        function23 = function2;
                                        mutableInteractionSource3 = mutableInteractionSource;
                                        brush3 = brush;
                                        outputTransformation3 = outputTransformation;
                                        textFieldDecorator3 = textFieldDecorator;
                                        scrollState3 = scrollState;
                                        textStyle4 = textStyle2;
                                        keyboardActionHandler3 = keyboardActionHandler;
                                    } else {
                                        if (i24 != 0) {
                                            modifier2 = Modifier.INSTANCE;
                                        }
                                        if (i5 != 0) {
                                            z3 = true;
                                        }
                                        if (i6 != 0) {
                                            z4 = false;
                                        }
                                        if (i7 != 0) {
                                            inputTransformation2 = null;
                                        }
                                        TextStyle textStyle5 = i8 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle2;
                                        if (i9 != 0) {
                                            keyboardOptions2 = KeyboardOptions.INSTANCE.getDefault();
                                        }
                                        KeyboardActionHandler keyboardActionHandler4 = i10 != 0 ? null : keyboardActionHandler;
                                        TextFieldLineLimits textFieldLineLimits4 = i12 != 0 ? TextFieldLineLimits.INSTANCE.getDefault() : textFieldLineLimits;
                                        function23 = i14 != 0 ? null : function2;
                                        mutableInteractionSource3 = i16 != 0 ? null : mutableInteractionSource;
                                        SolidColor cursorBrush = i19 != 0 ? BasicTextFieldDefaults.INSTANCE.getCursorBrush() : brush;
                                        OutputTransformation outputTransformation4 = i20 != 0 ? null : outputTransformation;
                                        TextFieldDecorator textFieldDecorator4 = i22 == 0 ? textFieldDecorator : null;
                                        if ((i3 & 16384) != 0) {
                                            i23 &= -57345;
                                            scrollState3 = ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1);
                                        } else {
                                            scrollState3 = scrollState;
                                        }
                                        textFieldLineLimits3 = textFieldLineLimits4;
                                        outputTransformation3 = outputTransformation4;
                                        brush3 = cursorBrush;
                                        textFieldDecorator3 = textFieldDecorator4;
                                        keyboardActionHandler3 = keyboardActionHandler4;
                                        textStyle4 = textStyle5;
                                    }
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(469439921, i4, i23, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:201)");
                                    }
                                    int i28 = (i23 & 14) | 384 | (i23 & 112);
                                    int i29 = i23 << 3;
                                    InputTransformation inputTransformation4 = inputTransformation2;
                                    KeyboardOptions keyboardOptions4 = keyboardOptions2;
                                    MutableInteractionSource mutableInteractionSource4 = mutableInteractionSource3;
                                    composer2 = startRestartGroup;
                                    TextFieldState textFieldState3 = textFieldState2;
                                    Modifier modifier4 = modifier2;
                                    TextFieldLineLimits textFieldLineLimits5 = textFieldLineLimits3;
                                    BasicTextField(textFieldState3, modifier4, z3, z4, inputTransformation4, textStyle4, keyboardOptions4, keyboardActionHandler3, textFieldLineLimits5, function23, mutableInteractionSource4, brush3, null, outputTransformation3, textFieldDecorator3, scrollState3, false, composer2, i4 & 2147483646, i28 | (i29 & 7168) | (57344 & i29) | (i29 & 458752), 65536);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    modifier3 = modifier4;
                                    z5 = z3;
                                    z6 = z4;
                                    inputTransformation3 = inputTransformation4;
                                    textStyle3 = textStyle4;
                                    keyboardOptions3 = keyboardOptions4;
                                    keyboardActionHandler2 = keyboardActionHandler3;
                                    textFieldLineLimits2 = textFieldLineLimits5;
                                    function22 = function23;
                                    mutableInteractionSource2 = mutableInteractionSource4;
                                    brush2 = brush3;
                                    outputTransformation2 = outputTransformation3;
                                    textFieldDecorator2 = textFieldDecorator3;
                                    scrollState2 = scrollState3;
                                } else {
                                    composer2 = startRestartGroup;
                                    composer2.skipToGroupEnd();
                                    textFieldLineLimits2 = textFieldLineLimits;
                                    function22 = function2;
                                    textFieldDecorator2 = textFieldDecorator;
                                    z5 = z3;
                                    z6 = z4;
                                    inputTransformation3 = inputTransformation2;
                                    textStyle3 = textStyle2;
                                    modifier3 = modifier2;
                                    keyboardOptions3 = keyboardOptions2;
                                    keyboardActionHandler2 = keyboardActionHandler;
                                    mutableInteractionSource2 = mutableInteractionSource;
                                    brush2 = brush;
                                    outputTransformation2 = outputTransformation;
                                    scrollState2 = scrollState;
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                    endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda10
                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(Object obj, Object obj2) {
                                            Unit BasicTextField$lambda$0;
                                            BasicTextField$lambda$0 = BasicTextFieldKt.BasicTextField$lambda$0(TextFieldState.this, modifier3, z5, z6, inputTransformation3, textStyle3, keyboardOptions3, keyboardActionHandler2, textFieldLineLimits2, function22, mutableInteractionSource2, brush2, outputTransformation2, textFieldDecorator2, scrollState2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                            return BasicTextField$lambda$0;
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            i14 = i13;
                            i15 = i3 & 1024;
                            if (i15 != 0) {
                            }
                            i18 = i3 & 2048;
                            if (i18 != 0) {
                            }
                            int i262 = i17;
                            i20 = i3 & 4096;
                            if (i20 != 0) {
                            }
                            i22 = i3 & 8192;
                            if (i22 != 0) {
                            }
                            if ((i2 & 24576) == 0) {
                            }
                            if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i23 & 9363) != 9362, i4 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        i12 = i11;
                        i13 = i3 & 512;
                        if (i13 == 0) {
                        }
                        i14 = i13;
                        i15 = i3 & 1024;
                        if (i15 != 0) {
                        }
                        i18 = i3 & 2048;
                        if (i18 != 0) {
                        }
                        int i2622 = i17;
                        i20 = i3 & 4096;
                        if (i20 != 0) {
                        }
                        i22 = i3 & 8192;
                        if (i22 != 0) {
                        }
                        if ((i2 & 24576) == 0) {
                        }
                        if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i23 & 9363) != 9362, i4 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    inputTransformation2 = inputTransformation;
                    i8 = i3 & 32;
                    if (i8 != 0) {
                    }
                    i9 = i3 & 64;
                    if (i9 != 0) {
                    }
                    i10 = i3 & 128;
                    if (i10 != 0) {
                    }
                    i11 = i3 & 256;
                    if (i11 != 0) {
                    }
                    i12 = i11;
                    i13 = i3 & 512;
                    if (i13 == 0) {
                    }
                    i14 = i13;
                    i15 = i3 & 1024;
                    if (i15 != 0) {
                    }
                    i18 = i3 & 2048;
                    if (i18 != 0) {
                    }
                    int i26222 = i17;
                    i20 = i3 & 4096;
                    if (i20 != 0) {
                    }
                    i22 = i3 & 8192;
                    if (i22 != 0) {
                    }
                    if ((i2 & 24576) == 0) {
                    }
                    if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i23 & 9363) != 9362, i4 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                z4 = z2;
                i7 = i3 & 16;
                int i252 = 8192;
                if (i7 == 0) {
                }
                inputTransformation2 = inputTransformation;
                i8 = i3 & 32;
                if (i8 != 0) {
                }
                i9 = i3 & 64;
                if (i9 != 0) {
                }
                i10 = i3 & 128;
                if (i10 != 0) {
                }
                i11 = i3 & 256;
                if (i11 != 0) {
                }
                i12 = i11;
                i13 = i3 & 512;
                if (i13 == 0) {
                }
                i14 = i13;
                i15 = i3 & 1024;
                if (i15 != 0) {
                }
                i18 = i3 & 2048;
                if (i18 != 0) {
                }
                int i262222 = i17;
                i20 = i3 & 4096;
                if (i20 != 0) {
                }
                i22 = i3 & 8192;
                if (i22 != 0) {
                }
                if ((i2 & 24576) == 0) {
                }
                if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i23 & 9363) != 9362, i4 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            z3 = z;
            i6 = i3 & 8;
            if (i6 != 0) {
            }
            z4 = z2;
            i7 = i3 & 16;
            int i2522 = 8192;
            if (i7 == 0) {
            }
            inputTransformation2 = inputTransformation;
            i8 = i3 & 32;
            if (i8 != 0) {
            }
            i9 = i3 & 64;
            if (i9 != 0) {
            }
            i10 = i3 & 128;
            if (i10 != 0) {
            }
            i11 = i3 & 256;
            if (i11 != 0) {
            }
            i12 = i11;
            i13 = i3 & 512;
            if (i13 == 0) {
            }
            i14 = i13;
            i15 = i3 & 1024;
            if (i15 != 0) {
            }
            i18 = i3 & 2048;
            if (i18 != 0) {
            }
            int i2622222 = i17;
            i20 = i3 & 4096;
            if (i20 != 0) {
            }
            i22 = i3 & 8192;
            if (i22 != 0) {
            }
            if ((i2 & 24576) == 0) {
            }
            if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i23 & 9363) != 9362, i4 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i5 = i3 & 4;
        if (i5 == 0) {
        }
        z3 = z;
        i6 = i3 & 8;
        if (i6 != 0) {
        }
        z4 = z2;
        i7 = i3 & 16;
        int i25222 = 8192;
        if (i7 == 0) {
        }
        inputTransformation2 = inputTransformation;
        i8 = i3 & 32;
        if (i8 != 0) {
        }
        i9 = i3 & 64;
        if (i9 != 0) {
        }
        i10 = i3 & 128;
        if (i10 != 0) {
        }
        i11 = i3 & 256;
        if (i11 != 0) {
        }
        i12 = i11;
        i13 = i3 & 512;
        if (i13 == 0) {
        }
        i14 = i13;
        i15 = i3 & 1024;
        if (i15 != 0) {
        }
        i18 = i3 & 2048;
        if (i18 != 0) {
        }
        int i26222222 = i17;
        i20 = i3 & 4096;
        if (i20 != 0) {
        }
        i22 = i3 & 8192;
        if (i22 != 0) {
        }
        if ((i2 & 24576) == 0) {
        }
        if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i23 & 9363) != 9362, i4 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x08c2  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:191:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x089d  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x025d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void BasicTextField(final TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, TextFieldLineLimits textFieldLineLimits, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function2, MutableInteractionSource mutableInteractionSource, Brush brush, CodepointTransformation codepointTransformation, OutputTransformation outputTransformation, TextFieldDecorator textFieldDecorator, ScrollState scrollState, boolean z3, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        int i5;
        boolean z4;
        int i6;
        boolean z5;
        int i7;
        InputTransformation inputTransformation2;
        int i8;
        TextStyle textStyle2;
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
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActionHandler keyboardActionHandler2;
        final TextFieldLineLimits textFieldLineLimits2;
        final CodepointTransformation codepointTransformation2;
        final ScrollState scrollState2;
        final boolean z6;
        Composer composer2;
        final boolean z7;
        final InputTransformation inputTransformation3;
        final Modifier modifier3;
        final boolean z8;
        final TextStyle textStyle3;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function22;
        final MutableInteractionSource mutableInteractionSource2;
        final Brush brush2;
        final OutputTransformation outputTransformation2;
        final TextFieldDecorator textFieldDecorator2;
        ScopeUpdateScope endRestartGroup;
        KeyboardOptions keyboardOptions3;
        KeyboardActionHandler keyboardActionHandler3;
        TextFieldLineLimits textFieldLineLimits3;
        MutableInteractionSource mutableInteractionSource3;
        CodepointTransformation codepointTransformation3;
        ScrollState scrollState3;
        int i28;
        final TextFieldDecorator textFieldDecorator3;
        boolean z9;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function23;
        final boolean z10;
        final TextStyle textStyle4;
        final Brush brush3;
        final ScrollState scrollState4;
        boolean z11;
        int i29;
        OutputTransformation outputTransformation3;
        boolean z12;
        MutableInteractionSource mutableInteractionSource4;
        final boolean z13;
        LayoutDirection layoutDirection;
        final TextFieldLineLimits textFieldLineLimits4;
        Density density;
        SingleLineCodepointTransformation singleLineCodepointTransformation;
        KeyboardOptions keyboardOptions4;
        final PlatformSelectionBehaviors platformSelectionBehaviors;
        Density density2;
        boolean z14;
        Clipboard clipboard;
        final TransformedTextFieldState transformedTextFieldState;
        boolean z15;
        Composer startRestartGroup = composer.startRestartGroup(965149429);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicTextField)N(state,modifier,enabled,readOnly,inputTransformation,textStyle,keyboardOptions,onKeyboardAction,lineLimits,onTextLayout,interactionSource,cursorBrush,codepointTransformation,outputTransformation,decorator,scrollState,isPassword)252@14291L7,253@14346L7,259@14716L25,260@14790L25,264@15134L107,269@15278L846,287@16322L48,294@16698L24,303@17116L35,304@17194L7,306@17244L617,321@17914L7,322@17968L7,325@18014L2132,370@20163L510,370@20152L521,386@20721L51,386@20679L93,394@21074L1339,450@24077L3165:BasicTextField.kt#423gt5");
        if ((i3 & 1) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = (startRestartGroup.changed(textFieldState) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        int i30 = i3 & 2;
        if (i30 != 0) {
            i4 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i5 = i3 & 4;
            if (i5 == 0) {
                i4 |= 384;
            } else if ((i & 384) == 0) {
                z4 = z;
                i4 |= startRestartGroup.changed(z4) ? 256 : 128;
                i6 = i3 & 8;
                if (i6 != 0) {
                    i4 |= 3072;
                } else if ((i & 3072) == 0) {
                    z5 = z2;
                    i4 |= startRestartGroup.changed(z5) ? 2048 : 1024;
                    i7 = i3 & 16;
                    if (i7 == 0) {
                        i4 |= 24576;
                    } else if ((i & 24576) == 0) {
                        inputTransformation2 = inputTransformation;
                        i4 |= startRestartGroup.changed(inputTransformation2) ? 16384 : 8192;
                        i8 = i3 & 32;
                        if (i8 != 0) {
                            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            textStyle2 = textStyle;
                        } else {
                            textStyle2 = textStyle;
                            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i4 |= startRestartGroup.changed(textStyle2) ? 131072 : 65536;
                            }
                        }
                        i9 = i3 & 64;
                        if (i9 != 0) {
                            i4 |= 1572864;
                        } else if ((i & 1572864) == 0) {
                            i4 |= startRestartGroup.changed(keyboardOptions) ? 1048576 : 524288;
                        }
                        i10 = i3 & 128;
                        if (i10 != 0) {
                            i4 |= 12582912;
                        } else if ((i & 12582912) == 0) {
                            i4 |= startRestartGroup.changed(keyboardActionHandler) ? 8388608 : 4194304;
                        }
                        i11 = i3 & 256;
                        if (i11 != 0) {
                            i4 |= 100663296;
                        } else if ((i & 100663296) == 0) {
                            i12 = i11;
                            i4 |= startRestartGroup.changed(textFieldLineLimits) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                            i13 = i3 & 512;
                            if (i13 == 0) {
                                i4 |= 805306368;
                            } else if ((i & 805306368) == 0) {
                                i14 = i13;
                                i4 |= startRestartGroup.changedInstance(function2) ? 536870912 : 268435456;
                                i15 = i3 & 1024;
                                if (i15 != 0) {
                                    i17 = i2 | 6;
                                    i16 = i15;
                                } else if ((i2 & 6) == 0) {
                                    i16 = i15;
                                    i17 = i2 | (startRestartGroup.changed(mutableInteractionSource) ? 4 : 2);
                                } else {
                                    i16 = i15;
                                    i17 = i2;
                                }
                                i18 = i3 & 2048;
                                if (i18 != 0) {
                                    i17 |= 48;
                                    i19 = i18;
                                } else if ((i2 & 48) == 0) {
                                    i19 = i18;
                                    i17 |= startRestartGroup.changed(brush) ? 32 : 16;
                                } else {
                                    i19 = i18;
                                }
                                int i31 = i17;
                                i20 = i3 & 4096;
                                if (i20 != 0) {
                                    i21 = i31 | 384;
                                } else {
                                    int i32 = i31;
                                    if ((i2 & 384) == 0) {
                                        i32 |= startRestartGroup.changed(codepointTransformation) ? 256 : 128;
                                    }
                                    i21 = i32;
                                }
                                i22 = i3 & 8192;
                                if (i22 != 0) {
                                    i23 = i21 | 3072;
                                } else {
                                    int i33 = i21;
                                    if ((i2 & 3072) == 0) {
                                        i23 = i33 | (startRestartGroup.changed(outputTransformation) ? 2048 : 1024);
                                    } else {
                                        i23 = i33;
                                    }
                                }
                                i24 = i3 & 16384;
                                if (i24 != 0) {
                                    i25 = i23 | 24576;
                                } else {
                                    int i34 = i23;
                                    if ((i2 & 24576) == 0) {
                                        i25 = i34 | ((i2 & 32768) == 0 ? startRestartGroup.changed(textFieldDecorator) : startRestartGroup.changedInstance(textFieldDecorator) ? 16384 : 8192);
                                    } else {
                                        i25 = i34;
                                    }
                                }
                                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                    i25 |= ((i3 & 32768) == 0 && startRestartGroup.changed(scrollState)) ? 131072 : 65536;
                                }
                                i26 = i3 & 65536;
                                if (i26 != 0) {
                                    i25 |= 1572864;
                                } else if ((i2 & 1572864) == 0) {
                                    i25 |= startRestartGroup.changed(z3) ? 1048576 : 524288;
                                }
                                i27 = i25;
                                if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i27 & 599187) != 599186, i4 & 1)) {
                                    startRestartGroup.startDefaults();
                                    ComposerKt.sourceInformation(startRestartGroup, "247@14085L21");
                                    if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                        startRestartGroup.skipToGroupEnd();
                                        if ((i3 & 32768) != 0) {
                                            keyboardActionHandler3 = keyboardActionHandler;
                                            textFieldLineLimits3 = textFieldLineLimits;
                                            function23 = function2;
                                            mutableInteractionSource3 = mutableInteractionSource;
                                            brush3 = brush;
                                            codepointTransformation3 = codepointTransformation;
                                            outputTransformation3 = outputTransformation;
                                            textFieldDecorator3 = textFieldDecorator;
                                            scrollState4 = scrollState;
                                            z11 = z3;
                                            z9 = z4;
                                            z10 = z5;
                                            textStyle4 = textStyle2;
                                            i29 = i27 & (-458753);
                                            keyboardOptions3 = keyboardOptions;
                                        } else {
                                            keyboardOptions3 = keyboardOptions;
                                            keyboardActionHandler3 = keyboardActionHandler;
                                            textFieldLineLimits3 = textFieldLineLimits;
                                            function23 = function2;
                                            mutableInteractionSource3 = mutableInteractionSource;
                                            brush3 = brush;
                                            codepointTransformation3 = codepointTransformation;
                                            outputTransformation3 = outputTransformation;
                                            textFieldDecorator3 = textFieldDecorator;
                                            scrollState4 = scrollState;
                                            z11 = z3;
                                            z9 = z4;
                                            z10 = z5;
                                            textStyle4 = textStyle2;
                                            i29 = i27;
                                        }
                                    } else {
                                        if (i30 != 0) {
                                            modifier2 = Modifier.INSTANCE;
                                        }
                                        if (i5 != 0) {
                                            z4 = true;
                                        }
                                        if (i6 != 0) {
                                            z5 = false;
                                        }
                                        if (i7 != 0) {
                                            inputTransformation2 = null;
                                        }
                                        if (i8 != 0) {
                                            textStyle2 = TextStyle.INSTANCE.getDefault();
                                        }
                                        keyboardOptions3 = i9 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                                        keyboardActionHandler3 = i10 != 0 ? null : keyboardActionHandler;
                                        textFieldLineLimits3 = i12 != 0 ? TextFieldLineLimits.INSTANCE.getDefault() : textFieldLineLimits;
                                        Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function24 = i14 != 0 ? null : function2;
                                        mutableInteractionSource3 = i16 != 0 ? null : mutableInteractionSource;
                                        SolidColor cursorBrush = i19 != 0 ? BasicTextFieldDefaults.INSTANCE.getCursorBrush() : brush;
                                        codepointTransformation3 = i20 != 0 ? null : codepointTransformation;
                                        OutputTransformation outputTransformation4 = i22 != 0 ? null : outputTransformation;
                                        TextFieldDecorator textFieldDecorator4 = i24 != 0 ? null : textFieldDecorator;
                                        if ((i3 & 32768) != 0) {
                                            scrollState3 = ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1);
                                            i28 = i27 & (-458753);
                                        } else {
                                            scrollState3 = scrollState;
                                            i28 = i27;
                                        }
                                        textFieldDecorator3 = textFieldDecorator4;
                                        z9 = z4;
                                        function23 = function24;
                                        z10 = z5;
                                        textStyle4 = textStyle2;
                                        brush3 = cursorBrush;
                                        scrollState4 = scrollState3;
                                        z11 = i26 != 0 ? false : z3;
                                        i29 = i28;
                                        outputTransformation3 = outputTransformation4;
                                    }
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(965149429, i4, i29, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:251)");
                                    }
                                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                    Object consume = startRestartGroup.consume(localDensity);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    Density density3 = (Density) consume;
                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                    Object consume2 = startRestartGroup.consume(localLayoutDirection);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    LayoutDirection layoutDirection2 = (LayoutDirection) consume2;
                                    final boolean areEqual = Intrinsics.areEqual(textFieldLineLimits3, TextFieldLineLimits.SingleLine.INSTANCE);
                                    if (mutableInteractionSource3 == null) {
                                        startRestartGroup.startReplaceGroup(-2038064986);
                                        ComposerKt.sourceInformation(startRestartGroup, "257@14551L39");
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -204291364, "CC(remember):BasicTextField.kt#9igjgp");
                                        Object rememberedValue = startRestartGroup.rememberedValue();
                                        z12 = z11;
                                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue = InteractionSourceKt.MutableInteractionSource();
                                            startRestartGroup.updateRememberedValue(rememberedValue);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        startRestartGroup.endReplaceGroup();
                                        mutableInteractionSource4 = (MutableInteractionSource) rememberedValue;
                                    } else {
                                        z12 = z11;
                                        startRestartGroup.startReplaceGroup(-204292015);
                                        startRestartGroup.endReplaceGroup();
                                        mutableInteractionSource4 = mutableInteractionSource3;
                                    }
                                    Orientation orientation = areEqual ? Orientation.Horizontal : Orientation.Vertical;
                                    MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource4;
                                    KeyboardActionHandler keyboardActionHandler4 = keyboardActionHandler3;
                                    boolean booleanValue = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource5, startRestartGroup, 0).getValue().booleanValue();
                                    final boolean booleanValue2 = HoverInteractionKt.collectIsHoveredAsState(mutableInteractionSource5, startRestartGroup, 0).getValue().booleanValue();
                                    if (booleanValue) {
                                        startRestartGroup.startReplaceGroup(-204274364);
                                        ComposerKt.sourceInformation(startRestartGroup, "263@15075L7");
                                        ProvidableCompositionLocal<WindowInfo> localWindowInfo = CompositionLocalsKt.getLocalWindowInfo();
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                        Object consume3 = startRestartGroup.consume(localWindowInfo);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        boolean isWindowFocused = ((WindowInfo) consume3).isWindowFocused();
                                        startRestartGroup.endReplaceGroup();
                                        z13 = isWindowFocused;
                                    } else {
                                        startRestartGroup.startReplaceGroup(-2037536751);
                                        startRestartGroup.endReplaceGroup();
                                        z13 = false;
                                    }
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -204272640, "CC(remember):BasicTextField.kt#9igjgp");
                                    Object rememberedValue2 = startRestartGroup.rememberedValue();
                                    if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        layoutDirection = layoutDirection2;
                                        textFieldLineLimits4 = textFieldLineLimits3;
                                        density = density3;
                                        rememberedValue2 = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_LATEST, 2, null);
                                        startRestartGroup.updateRememberedValue(rememberedValue2);
                                    } else {
                                        layoutDirection = layoutDirection2;
                                        textFieldLineLimits4 = textFieldLineLimits3;
                                        density = density3;
                                    }
                                    final MutableSharedFlow mutableSharedFlow = (MutableSharedFlow) rememberedValue2;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -204267293, "CC(remember):BasicTextField.kt#9igjgp");
                                    boolean z16 = ((i4 & 14) == 4) | ((i29 & 896) == 256) | ((i29 & 7168) == 2048);
                                    Object rememberedValue3 = startRestartGroup.rememberedValue();
                                    if (z16 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        if (codepointTransformation3 == null) {
                                            SingleLineCodepointTransformation singleLineCodepointTransformation2 = SingleLineCodepointTransformation.INSTANCE;
                                            if (!areEqual) {
                                                singleLineCodepointTransformation2 = null;
                                            }
                                            singleLineCodepointTransformation = singleLineCodepointTransformation2;
                                        } else {
                                            singleLineCodepointTransformation = codepointTransformation3;
                                        }
                                        rememberedValue3 = new TransformedTextFieldState(textFieldState, inputTransformation2, singleLineCodepointTransformation, outputTransformation3);
                                        startRestartGroup.updateRememberedValue(rememberedValue3);
                                    }
                                    final TransformedTextFieldState transformedTextFieldState2 = (TransformedTextFieldState) rememberedValue3;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -204234683, "CC(remember):BasicTextField.kt#9igjgp");
                                    boolean changed = startRestartGroup.changed(transformedTextFieldState2);
                                    Object rememberedValue4 = startRestartGroup.rememberedValue();
                                    if (changed || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue4 = new TextLayoutState();
                                        startRestartGroup.updateRememberedValue(rememberedValue4);
                                    }
                                    final TextLayoutState textLayoutState = (TextLayoutState) rememberedValue4;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    final KeyboardOptions fillUnspecifiedValuesWith$foundation_release = keyboardOptions3.fillUnspecifiedValuesWith$foundation_release(inputTransformation2 != null ? inputTransformation2.getKeyboardOptions() : null);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 683737348, "CC(remember):Effects.kt#9igjgp");
                                    Object rememberedValue5 = startRestartGroup.rememberedValue();
                                    if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue5 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, startRestartGroup);
                                        startRestartGroup.updateRememberedValue(rememberedValue5);
                                    }
                                    CoroutineScope coroutineScope = (CoroutineScope) rememberedValue5;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    if (ComposeFoundationFlags.isSmartSelectionEnabled) {
                                        startRestartGroup.startReplaceGroup(-2035764848);
                                        ComposerKt.sourceInformation(startRestartGroup, "299@16959L85");
                                        LocaleList localeList = textStyle4.getLocaleList();
                                        if (localeList == null) {
                                            localeList = LocaleList.INSTANCE.getCurrent();
                                        }
                                        keyboardOptions4 = keyboardOptions3;
                                        PlatformSelectionBehaviors rememberPlatformSelectionBehaviors = PlatformSelectionBehaviors_androidKt.rememberPlatformSelectionBehaviors(SelectedTextType.EditableText, localeList, startRestartGroup, 6);
                                        startRestartGroup.endReplaceGroup();
                                        platformSelectionBehaviors = rememberPlatformSelectionBehaviors;
                                    } else {
                                        keyboardOptions4 = keyboardOptions3;
                                        startRestartGroup.startReplaceGroup(-2035576399);
                                        startRestartGroup.endReplaceGroup();
                                        platformSelectionBehaviors = null;
                                    }
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -204209288, "CC(remember):BasicTextField.kt#9igjgp");
                                    Object rememberedValue6 = startRestartGroup.rememberedValue();
                                    if (rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue6 = new ToolbarRequesterImpl();
                                        startRestartGroup.updateRememberedValue(rememberedValue6);
                                    }
                                    final ToolbarRequesterImpl toolbarRequesterImpl = (ToolbarRequesterImpl) rememberedValue6;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ProvidableCompositionLocal<Clipboard> localClipboard = CompositionLocalsKt.getLocalClipboard();
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                    Object consume4 = startRestartGroup.consume(localClipboard);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    Clipboard clipboard2 = (Clipboard) consume4;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -204204610, "CC(remember):BasicTextField.kt#9igjgp");
                                    boolean changed2 = startRestartGroup.changed(transformedTextFieldState2);
                                    Object rememberedValue7 = startRestartGroup.rememberedValue();
                                    if (changed2 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue7 = new TextFieldSelectionState(transformedTextFieldState2, textLayoutState, density, z9, z10, z13, z12, toolbarRequesterImpl, coroutineScope, platformSelectionBehaviors, clipboard2);
                                        density2 = density;
                                        z14 = z12;
                                        clipboard = clipboard2;
                                        startRestartGroup.updateRememberedValue(rememberedValue7);
                                    } else {
                                        z14 = z12;
                                        clipboard = clipboard2;
                                        density2 = density;
                                    }
                                    final TextFieldSelectionState textFieldSelectionState = (TextFieldSelectionState) rememberedValue7;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ProvidableCompositionLocal<HapticFeedback> localHapticFeedback = CompositionLocalsKt.getLocalHapticFeedback();
                                    OutputTransformation outputTransformation5 = outputTransformation3;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                    Object consume5 = startRestartGroup.consume(localHapticFeedback);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    final HapticFeedback hapticFeedback = (HapticFeedback) consume5;
                                    int i35 = i29;
                                    ProvidableCompositionLocal<TextToolbar> localTextToolbar = CompositionLocalsKt.getLocalTextToolbar();
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                    Object consume6 = startRestartGroup.consume(localTextToolbar);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    TextToolbar textToolbar = (TextToolbar) consume6;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -204178455, "CC(remember):BasicTextField.kt#9igjgp");
                                    boolean changed3 = startRestartGroup.changed(coroutineScope) | startRestartGroup.changed(textToolbar);
                                    Object rememberedValue8 = startRestartGroup.rememberedValue();
                                    if (changed3 || rememberedValue8 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue8 = new BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1(textToolbar, coroutineScope);
                                        startRestartGroup.updateRememberedValue(rememberedValue8);
                                    }
                                    final BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1 basicTextFieldKt$BasicTextField$textToolbarHandler$1$1 = (BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1) rememberedValue8;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -204111309, "CC(remember):BasicTextField.kt#9igjgp");
                                    final Density density4 = density2;
                                    boolean changed4 = ((i4 & 896) == 256) | startRestartGroup.changed(transformedTextFieldState2) | ((57344 & i4) == 16384) | startRestartGroup.changedInstance(textFieldSelectionState) | startRestartGroup.changedInstance(hapticFeedback) | startRestartGroup.changedInstance(clipboard) | startRestartGroup.changed(basicTextFieldKt$BasicTextField$textToolbarHandler$1$1) | startRestartGroup.changed(density2) | ((i4 & 7168) == 2048) | ((i35 & 3670016) == 1048576);
                                    Object rememberedValue9 = startRestartGroup.rememberedValue();
                                    if (changed4 || rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                                        final Clipboard clipboard3 = clipboard;
                                        final InputTransformation inputTransformation4 = inputTransformation2;
                                        final boolean z17 = z14;
                                        final boolean z18 = z9;
                                        final boolean z19 = z10;
                                        rememberedValue9 = new Function0() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda6
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Object invoke() {
                                                Unit BasicTextField$lambda$10$lambda$9;
                                                BasicTextField$lambda$10$lambda$9 = BasicTextFieldKt.BasicTextField$lambda$10$lambda$9(TransformedTextFieldState.this, inputTransformation4, textFieldSelectionState, hapticFeedback, clipboard3, basicTextFieldKt$BasicTextField$textToolbarHandler$1$1, density4, z18, z19, z17);
                                                return BasicTextField$lambda$10$lambda$9;
                                            }
                                        };
                                        transformedTextFieldState = transformedTextFieldState2;
                                        z15 = z18;
                                        startRestartGroup.updateRememberedValue(rememberedValue9);
                                    } else {
                                        transformedTextFieldState = transformedTextFieldState2;
                                        z15 = z9;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    EffectsKt.SideEffect((Function0) rememberedValue9, startRestartGroup, 0);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -204093912, "CC(remember):BasicTextField.kt#9igjgp");
                                    boolean changedInstance = startRestartGroup.changedInstance(textFieldSelectionState);
                                    Object rememberedValue10 = startRestartGroup.rememberedValue();
                                    if (changedInstance || rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue10 = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda7
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                DisposableEffectResult BasicTextField$lambda$13$lambda$12;
                                                BasicTextField$lambda$13$lambda$12 = BasicTextFieldKt.BasicTextField$lambda$13$lambda$12(TextFieldSelectionState.this, (DisposableEffectScope) obj);
                                                return BasicTextField$lambda$13$lambda$12;
                                            }
                                        };
                                        startRestartGroup.updateRememberedValue(rememberedValue10);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    EffectsKt.DisposableEffect(textFieldSelectionState, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue10, startRestartGroup, 0);
                                    final boolean z20 = (z14 || KeyboardType.m7073equalsimpl0(keyboardOptions4.getKeyboardType(), KeyboardType.INSTANCE.m7092getPasswordPjHm6EE()) || KeyboardType.m7073equalsimpl0(keyboardOptions4.getKeyboardType(), KeyboardType.INSTANCE.m7091getNumberPasswordPjHm6EE())) ? false : true;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -204081328, "CC(remember):BasicTextField.kt#9igjgp");
                                    boolean changed5 = startRestartGroup.changed(z20) | startRestartGroup.changedInstance(mutableSharedFlow);
                                    Object rememberedValue11 = startRestartGroup.rememberedValue();
                                    if (changed5 || rememberedValue11 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue11 = new Function0() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda8
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Object invoke() {
                                                Unit BasicTextField$lambda$15$lambda$14;
                                                BasicTextField$lambda$15$lambda$14 = BasicTextFieldKt.BasicTextField$lambda$15$lambda$14(z20, mutableSharedFlow);
                                                return BasicTextField$lambda$15$lambda$14;
                                            }
                                        };
                                        startRestartGroup.updateRememberedValue(rememberedValue11);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    final boolean z21 = z15;
                                    keyboardActionHandler2 = keyboardActionHandler4;
                                    final Orientation orientation2 = orientation;
                                    Modifier addContextMenuComponents = addContextMenuComponents(PointerIconKt.pointerHoverIcon$default(ScrollableKt.scrollable$default(StylusHandwritingKt.stylusHandwriting(modifier2, z15, z20, (Function0) rememberedValue11).then(new TextFieldDecoratorModifier(transformedTextFieldState, textLayoutState, textFieldSelectionState, inputTransformation2, z21, z10, fillUnspecifiedValuesWith$foundation_release, keyboardActionHandler4, areEqual, mutableInteractionSource4, z14, mutableSharedFlow)), scrollState4, orientation2, z21 && textFieldSelectionState.getDirectDragGestureInitiator() == TextFieldSelectionState.InputType.None, ScrollableDefaults.INSTANCE.reverseDirection(layoutDirection, orientation, false), null, mutableInteractionSource4, 16, null), PointerIcon.INSTANCE.getText(), false, 2, null), textFieldSelectionState, coroutineScope);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                                    int m = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                                    CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, addContextMenuComponents);
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
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1520421984, "C451@24194L3042,451@24144L3092:BasicTextField.kt#423gt5");
                                    ContextMenu_androidKt.ContextMenuArea(textFieldSelectionState, z21, ComposableLambdaKt.rememberComposableLambda(-673241599, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$4$1
                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                            invoke(composer3, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer3, int i36) {
                                            ComposerKt.sourceInformation(composer3, "C453@24310L2916,453@24299L2927:BasicTextField.kt#423gt5");
                                            if (!composer3.shouldExecute((i36 & 3) != 2, i36 & 1)) {
                                                composer3.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-673241599, i36, -1, "androidx.compose.foundation.text.BasicTextField.<anonymous>.<anonymous> (BasicTextField.kt:452)");
                                            }
                                            TextFieldDecorator textFieldDecorator5 = TextFieldDecorator.this;
                                            if (textFieldDecorator5 == null) {
                                                textFieldDecorator5 = BasicTextFieldKt.DefaultTextFieldDecorator;
                                            }
                                            final TextFieldLineLimits textFieldLineLimits5 = textFieldLineLimits4;
                                            final TextLayoutState textLayoutState2 = textLayoutState;
                                            final TextStyle textStyle5 = textStyle4;
                                            final boolean z22 = z13;
                                            final boolean z23 = booleanValue2;
                                            final TransformedTextFieldState transformedTextFieldState3 = transformedTextFieldState;
                                            final TextFieldSelectionState textFieldSelectionState2 = textFieldSelectionState;
                                            final Brush brush4 = brush3;
                                            final boolean z24 = z21;
                                            final boolean z25 = z10;
                                            final ScrollState scrollState5 = scrollState4;
                                            final Orientation orientation3 = orientation2;
                                            final ToolbarRequesterImpl toolbarRequesterImpl2 = toolbarRequesterImpl;
                                            final PlatformSelectionBehaviors platformSelectionBehaviors2 = platformSelectionBehaviors;
                                            final boolean z26 = areEqual;
                                            final Function2<Density, Function0<TextLayoutResult>, Unit> function25 = function23;
                                            final KeyboardOptions keyboardOptions5 = fillUnspecifiedValuesWith$foundation_release;
                                            textFieldDecorator5.Decoration(ComposableLambdaKt.rememberComposableLambda(1969169726, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$4$1.1
                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                                    invoke(composer4, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer4, int i37) {
                                                    int i38;
                                                    int i39;
                                                    ComposerKt.sourceInformation(composer4, "C464@24671L2541:BasicTextField.kt#423gt5");
                                                    if (!composer4.shouldExecute((i37 & 3) != 2, i37 & 1)) {
                                                        composer4.skipToGroupEnd();
                                                        return;
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(1969169726, i37, -1, "androidx.compose.foundation.text.BasicTextField.<anonymous>.<anonymous>.<anonymous> (BasicTextField.kt:454)");
                                                    }
                                                    TextFieldLineLimits textFieldLineLimits6 = TextFieldLineLimits.this;
                                                    if (textFieldLineLimits6 instanceof TextFieldLineLimits.MultiLine) {
                                                        i38 = ((TextFieldLineLimits.MultiLine) textFieldLineLimits6).getMinHeightInLines();
                                                        i39 = ((TextFieldLineLimits.MultiLine) TextFieldLineLimits.this).getMaxHeightInLines();
                                                    } else {
                                                        i38 = 1;
                                                        i39 = 1;
                                                    }
                                                    Modifier then = ClipKt.clipToBounds(TextFieldSizeKt.textFieldMinSize(HeightInLinesModifierKt.heightInLines(SizeKt.m918heightInVpY3zN4$default(Modifier.INSTANCE, textLayoutState2.m1618getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), textStyle5, i38, i39), textStyle5)).then(new TextFieldCoreModifier(z22, z23, textLayoutState2, transformedTextFieldState3, textFieldSelectionState2, brush4, z24 && !z25, scrollState5, orientation3, toolbarRequesterImpl2, platformSelectionBehaviors2));
                                                    TextLayoutState textLayoutState3 = textLayoutState2;
                                                    TransformedTextFieldState transformedTextFieldState4 = transformedTextFieldState3;
                                                    TextStyle textStyle6 = textStyle5;
                                                    boolean z27 = z26;
                                                    Function2<Density, Function0<TextLayoutResult>, Unit> function26 = function25;
                                                    KeyboardOptions keyboardOptions6 = keyboardOptions5;
                                                    boolean z28 = z24;
                                                    boolean z29 = z22;
                                                    TextFieldSelectionState textFieldSelectionState3 = textFieldSelectionState2;
                                                    boolean z30 = z25;
                                                    ComposerKt.sourceInformationMarkerStart(composer4, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                                                    MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true);
                                                    ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                                                    int m2 = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                                                    CompositionLocalMap currentCompositionLocalMap2 = composer4.getCurrentCompositionLocalMap();
                                                    Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer4, then);
                                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                    ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                                    if (!(composer4.getApplier() instanceof Applier)) {
                                                        ComposablesKt.invalidApplier();
                                                    }
                                                    composer4.startReusableNode();
                                                    if (composer4.getInserting()) {
                                                        composer4.createNode(constructor2);
                                                    } else {
                                                        composer4.useNode();
                                                    }
                                                    Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer4);
                                                    Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                    if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(m2))) {
                                                        m3905constructorimpl2.updateRememberedValue(Integer.valueOf(m2));
                                                        m3905constructorimpl2.apply(Integer.valueOf(m2), setCompositeKeyHash2);
                                                    }
                                                    Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                                    ComposerKt.sourceInformationMarkerStart(composer4, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                                                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                                    ComposerKt.sourceInformationMarkerStart(composer4, -811364182, "C491@26181L530:BasicTextField.kt#423gt5");
                                                    BoxKt.Box(new TextFieldTextLayoutModifier(textLayoutState3, transformedTextFieldState4, textStyle6, z27, function26, keyboardOptions6), composer4, 0);
                                                    if (z28 && z29 && textFieldSelectionState3.isInTouchMode()) {
                                                        composer4.startReplaceGroup(-810654004);
                                                        ComposerKt.sourceInformation(composer4, "508@26946L67");
                                                        BasicTextFieldKt.TextFieldSelectionHandles(textFieldSelectionState3, composer4, 0);
                                                        if (!z30) {
                                                            composer4.startReplaceGroup(-810526873);
                                                            ComposerKt.sourceInformation(composer4, "510@27083L63");
                                                            BasicTextFieldKt.TextFieldCursorHandle(textFieldSelectionState3, composer4, 0);
                                                            composer4.endReplaceGroup();
                                                        } else {
                                                            composer4.startReplaceGroup(-810412514);
                                                            composer4.endReplaceGroup();
                                                        }
                                                        composer4.endReplaceGroup();
                                                    } else {
                                                        composer4.startReplaceGroup(-810390690);
                                                        composer4.endReplaceGroup();
                                                    }
                                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                                    composer4.endNode();
                                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                    }
                                                }
                                            }, composer3, 54), composer3, 6);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    }, startRestartGroup, 54), startRestartGroup, ((i4 >> 3) & 112) | 384);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    startRestartGroup.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    composer2 = startRestartGroup;
                                    z7 = z21;
                                    inputTransformation3 = inputTransformation2;
                                    modifier3 = modifier2;
                                    mutableInteractionSource2 = mutableInteractionSource3;
                                    keyboardOptions2 = keyboardOptions4;
                                    codepointTransformation2 = codepointTransformation3;
                                    z6 = z14;
                                    outputTransformation2 = outputTransformation5;
                                    textFieldDecorator2 = textFieldDecorator3;
                                    textFieldLineLimits2 = textFieldLineLimits4;
                                    textStyle3 = textStyle4;
                                    brush2 = brush3;
                                    z8 = z10;
                                    scrollState2 = scrollState4;
                                    function22 = function23;
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    keyboardOptions2 = keyboardOptions;
                                    keyboardActionHandler2 = keyboardActionHandler;
                                    textFieldLineLimits2 = textFieldLineLimits;
                                    codepointTransformation2 = codepointTransformation;
                                    scrollState2 = scrollState;
                                    z6 = z3;
                                    composer2 = startRestartGroup;
                                    z7 = z4;
                                    inputTransformation3 = inputTransformation2;
                                    modifier3 = modifier2;
                                    z8 = z5;
                                    textStyle3 = textStyle2;
                                    function22 = function2;
                                    mutableInteractionSource2 = mutableInteractionSource;
                                    brush2 = brush;
                                    outputTransformation2 = outputTransformation;
                                    textFieldDecorator2 = textFieldDecorator;
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                    endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda9
                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(Object obj, Object obj2) {
                                            Unit BasicTextField$lambda$17;
                                            BasicTextField$lambda$17 = BasicTextFieldKt.BasicTextField$lambda$17(TextFieldState.this, modifier3, z7, z8, inputTransformation3, textStyle3, keyboardOptions2, keyboardActionHandler2, textFieldLineLimits2, function22, mutableInteractionSource2, brush2, codepointTransformation2, outputTransformation2, textFieldDecorator2, scrollState2, z6, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                            return BasicTextField$lambda$17;
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            i14 = i13;
                            i15 = i3 & 1024;
                            if (i15 != 0) {
                            }
                            i18 = i3 & 2048;
                            if (i18 != 0) {
                            }
                            int i312 = i17;
                            i20 = i3 & 4096;
                            if (i20 != 0) {
                            }
                            i22 = i3 & 8192;
                            if (i22 != 0) {
                            }
                            i24 = i3 & 16384;
                            if (i24 != 0) {
                            }
                            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            }
                            i26 = i3 & 65536;
                            if (i26 != 0) {
                            }
                            i27 = i25;
                            if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i27 & 599187) != 599186, i4 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        i12 = i11;
                        i13 = i3 & 512;
                        if (i13 == 0) {
                        }
                        i14 = i13;
                        i15 = i3 & 1024;
                        if (i15 != 0) {
                        }
                        i18 = i3 & 2048;
                        if (i18 != 0) {
                        }
                        int i3122 = i17;
                        i20 = i3 & 4096;
                        if (i20 != 0) {
                        }
                        i22 = i3 & 8192;
                        if (i22 != 0) {
                        }
                        i24 = i3 & 16384;
                        if (i24 != 0) {
                        }
                        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        }
                        i26 = i3 & 65536;
                        if (i26 != 0) {
                        }
                        i27 = i25;
                        if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i27 & 599187) != 599186, i4 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    inputTransformation2 = inputTransformation;
                    i8 = i3 & 32;
                    if (i8 != 0) {
                    }
                    i9 = i3 & 64;
                    if (i9 != 0) {
                    }
                    i10 = i3 & 128;
                    if (i10 != 0) {
                    }
                    i11 = i3 & 256;
                    if (i11 != 0) {
                    }
                    i12 = i11;
                    i13 = i3 & 512;
                    if (i13 == 0) {
                    }
                    i14 = i13;
                    i15 = i3 & 1024;
                    if (i15 != 0) {
                    }
                    i18 = i3 & 2048;
                    if (i18 != 0) {
                    }
                    int i31222 = i17;
                    i20 = i3 & 4096;
                    if (i20 != 0) {
                    }
                    i22 = i3 & 8192;
                    if (i22 != 0) {
                    }
                    i24 = i3 & 16384;
                    if (i24 != 0) {
                    }
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    }
                    i26 = i3 & 65536;
                    if (i26 != 0) {
                    }
                    i27 = i25;
                    if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i27 & 599187) != 599186, i4 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                z5 = z2;
                i7 = i3 & 16;
                if (i7 == 0) {
                }
                inputTransformation2 = inputTransformation;
                i8 = i3 & 32;
                if (i8 != 0) {
                }
                i9 = i3 & 64;
                if (i9 != 0) {
                }
                i10 = i3 & 128;
                if (i10 != 0) {
                }
                i11 = i3 & 256;
                if (i11 != 0) {
                }
                i12 = i11;
                i13 = i3 & 512;
                if (i13 == 0) {
                }
                i14 = i13;
                i15 = i3 & 1024;
                if (i15 != 0) {
                }
                i18 = i3 & 2048;
                if (i18 != 0) {
                }
                int i312222 = i17;
                i20 = i3 & 4096;
                if (i20 != 0) {
                }
                i22 = i3 & 8192;
                if (i22 != 0) {
                }
                i24 = i3 & 16384;
                if (i24 != 0) {
                }
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                }
                i26 = i3 & 65536;
                if (i26 != 0) {
                }
                i27 = i25;
                if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i27 & 599187) != 599186, i4 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            z4 = z;
            i6 = i3 & 8;
            if (i6 != 0) {
            }
            z5 = z2;
            i7 = i3 & 16;
            if (i7 == 0) {
            }
            inputTransformation2 = inputTransformation;
            i8 = i3 & 32;
            if (i8 != 0) {
            }
            i9 = i3 & 64;
            if (i9 != 0) {
            }
            i10 = i3 & 128;
            if (i10 != 0) {
            }
            i11 = i3 & 256;
            if (i11 != 0) {
            }
            i12 = i11;
            i13 = i3 & 512;
            if (i13 == 0) {
            }
            i14 = i13;
            i15 = i3 & 1024;
            if (i15 != 0) {
            }
            i18 = i3 & 2048;
            if (i18 != 0) {
            }
            int i3122222 = i17;
            i20 = i3 & 4096;
            if (i20 != 0) {
            }
            i22 = i3 & 8192;
            if (i22 != 0) {
            }
            i24 = i3 & 16384;
            if (i24 != 0) {
            }
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            }
            i26 = i3 & 65536;
            if (i26 != 0) {
            }
            i27 = i25;
            if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i27 & 599187) != 599186, i4 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i5 = i3 & 4;
        if (i5 == 0) {
        }
        z4 = z;
        i6 = i3 & 8;
        if (i6 != 0) {
        }
        z5 = z2;
        i7 = i3 & 16;
        if (i7 == 0) {
        }
        inputTransformation2 = inputTransformation;
        i8 = i3 & 32;
        if (i8 != 0) {
        }
        i9 = i3 & 64;
        if (i9 != 0) {
        }
        i10 = i3 & 128;
        if (i10 != 0) {
        }
        i11 = i3 & 256;
        if (i11 != 0) {
        }
        i12 = i11;
        i13 = i3 & 512;
        if (i13 == 0) {
        }
        i14 = i13;
        i15 = i3 & 1024;
        if (i15 != 0) {
        }
        i18 = i3 & 2048;
        if (i18 != 0) {
        }
        int i31222222 = i17;
        i20 = i3 & 4096;
        if (i20 != 0) {
        }
        i22 = i3 & 8192;
        if (i22 != 0) {
        }
        i24 = i3 & 16384;
        if (i24 != 0) {
        }
        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
        }
        i26 = i3 & 65536;
        if (i26 != 0) {
        }
        i27 = i25;
        if (startRestartGroup.shouldExecute((i4 & 306783379) == 306783378 || (i27 & 599187) != 599186, i4 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$10$lambda$9(TransformedTextFieldState transformedTextFieldState, InputTransformation inputTransformation, TextFieldSelectionState textFieldSelectionState, HapticFeedback hapticFeedback, Clipboard clipboard, BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1 basicTextFieldKt$BasicTextField$textToolbarHandler$1$1, Density density, boolean z, boolean z2, boolean z3) {
        transformedTextFieldState.update(inputTransformation);
        textFieldSelectionState.update(hapticFeedback, clipboard, basicTextFieldKt$BasicTextField$textToolbarHandler$1$1, density, z, z2, z3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$15$lambda$14(boolean z, MutableSharedFlow mutableSharedFlow) {
        if (z) {
            mutableSharedFlow.tryEmit(Unit.INSTANCE);
        }
        return Unit.INSTANCE;
    }

    private static final Modifier addContextMenuComponents(Modifier modifier, TextFieldSelectionState textFieldSelectionState, CoroutineScope coroutineScope) {
        return ComposeFoundationFlags.isNewContextMenuEnabled ? TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents(modifier, textFieldSelectionState, coroutineScope) : modifier;
    }

    public static final void TextFieldCursorHandle(final TextFieldSelectionState textFieldSelectionState, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(1991581797);
        ComposerKt.sourceInformation(startRestartGroup, "C(TextFieldCursorHandle)N(selectionState)532@27794L128:BasicTextField.kt#423gt5");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(textFieldSelectionState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (!startRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1991581797, i2, -1, "androidx.compose.foundation.text.TextFieldCursorHandle (BasicTextField.kt:529)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1645300795, "CC(remember):BasicTextField.kt#9igjgp");
            boolean changed = startRestartGroup.changed(textFieldSelectionState);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        TextFieldHandleState TextFieldCursorHandle$lambda$19$lambda$18;
                        TextFieldCursorHandle$lambda$19$lambda$18 = BasicTextFieldKt.TextFieldCursorHandle$lambda$19$lambda$18(TextFieldSelectionState.this);
                        return TextFieldCursorHandle$lambda$19$lambda$18;
                    }
                });
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (TextFieldCursorHandle$lambda$20((State) rememberedValue).getVisible()) {
                startRestartGroup.startReplaceGroup(535453998);
                ComposerKt.sourceInformation(startRestartGroup, "537@28011L100,541@28190L87,536@27968L383");
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1645293879, "CC(remember):BasicTextField.kt#9igjgp");
                boolean changedInstance = startRestartGroup.changedInstance(textFieldSelectionState);
                OffsetProvider rememberedValue2 = startRestartGroup.rememberedValue();
                if (changedInstance || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue2 = new OffsetProvider() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldCursorHandle$1$1
                        @Override // androidx.compose.foundation.text.selection.OffsetProvider
                        /* renamed from: provide-F1C5BW0, reason: not valid java name */
                        public final long mo1265provideF1C5BW0() {
                            return TextFieldSelectionState.this.getCursorHandleState$foundation_release(true).m1664getPositionF1C5BW0();
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                }
                OffsetProvider offsetProvider = (OffsetProvider) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1645288164, "CC(remember):BasicTextField.kt#9igjgp");
                boolean changedInstance2 = startRestartGroup.changedInstance(textFieldSelectionState);
                PointerInputEventHandler rememberedValue3 = startRestartGroup.rememberedValue();
                if (changedInstance2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue3 = new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldCursorHandle$2$1
                        @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                            Object cursorHandleGestures = TextFieldSelectionState.this.cursorHandleGestures(pointerInputScope, continuation);
                            return cursorHandleGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? cursorHandleGestures : Unit.INSTANCE;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                AndroidCursorHandle_androidKt.m1247CursorHandleUSBMPiE(offsetProvider, SuspendingPointerInputFilterKt.pointerInput(companion, textFieldSelectionState, (PointerInputEventHandler) rememberedValue3), MinTouchTargetSizeForHandles, startRestartGroup, 384, 0);
                startRestartGroup.endReplaceGroup();
            } else {
                startRestartGroup.startReplaceGroup(535837437);
                startRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit TextFieldCursorHandle$lambda$23;
                    TextFieldCursorHandle$lambda$23 = BasicTextFieldKt.TextFieldCursorHandle$lambda$23(TextFieldSelectionState.this, i, (Composer) obj, ((Integer) obj2).intValue());
                    return TextFieldCursorHandle$lambda$23;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldHandleState TextFieldCursorHandle$lambda$19$lambda$18(TextFieldSelectionState textFieldSelectionState) {
        return textFieldSelectionState.getCursorHandleState$foundation_release(false);
    }

    public static final void TextFieldSelectionHandles(final TextFieldSelectionState textFieldSelectionState, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(2025287684);
        ComposerKt.sourceInformation(startRestartGroup, "C(TextFieldSelectionHandles)N(selectionState)553@28557L240,582@29612L241:BasicTextField.kt#423gt5");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(textFieldSelectionState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (!startRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2025287684, i2, -1, "androidx.compose.foundation.text.TextFieldSelectionHandles (BasicTextField.kt:550)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 681285748, "CC(remember):BasicTextField.kt#9igjgp");
            boolean changed = startRestartGroup.changed(textFieldSelectionState);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        TextFieldHandleState TextFieldSelectionHandles$lambda$25$lambda$24;
                        TextFieldSelectionHandles$lambda$25$lambda$24 = BasicTextFieldKt.TextFieldSelectionHandles$lambda$25$lambda$24(TextFieldSelectionState.this);
                        return TextFieldSelectionHandles$lambda$25$lambda$24;
                    }
                });
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            State state = (State) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (TextFieldSelectionHandles$lambda$26(state).getVisible()) {
                startRestartGroup.startReplaceGroup(-354691912);
                ComposerKt.sourceInformation(startRestartGroup, "563@28888L167,572@29282L94,562@28842L662");
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 681296267, "CC(remember):BasicTextField.kt#9igjgp");
                boolean changedInstance = startRestartGroup.changedInstance(textFieldSelectionState);
                OffsetProvider rememberedValue2 = startRestartGroup.rememberedValue();
                if (changedInstance || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue2 = new OffsetProvider() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$1$1
                        @Override // androidx.compose.foundation.text.selection.OffsetProvider
                        /* renamed from: provide-F1C5BW0 */
                        public final long mo1265provideF1C5BW0() {
                            return TextFieldSelectionState.this.getSelectionHandleState$foundation_release(true, true).m1664getPositionF1C5BW0();
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                }
                OffsetProvider offsetProvider = (OffsetProvider) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ResolvedTextDirection direction = TextFieldSelectionHandles$lambda$26(state).getDirection();
                boolean handlesCrossed = TextFieldSelectionHandles$lambda$26(state).getHandlesCrossed();
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 681308802, "CC(remember):BasicTextField.kt#9igjgp");
                boolean changedInstance2 = startRestartGroup.changedInstance(textFieldSelectionState);
                PointerInputEventHandler rememberedValue3 = startRestartGroup.rememberedValue();
                if (changedInstance2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue3 = new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$2$1
                        @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                            Object selectionHandleGestures = TextFieldSelectionState.this.selectionHandleGestures(pointerInputScope, true, continuation);
                            return selectionHandleGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? selectionHandleGestures : Unit.INSTANCE;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                AndroidSelectionHandles_androidKt.m1766SelectionHandlewLIcFTc(offsetProvider, true, direction, handlesCrossed, MinTouchTargetSizeForHandles, TextFieldSelectionHandles$lambda$26(state).getLineHeight(), SuspendingPointerInputFilterKt.pointerInput(companion, textFieldSelectionState, (PointerInputEventHandler) rememberedValue3), startRestartGroup, 24624, 0);
                startRestartGroup.endReplaceGroup();
            } else {
                startRestartGroup.startReplaceGroup(-354040354);
                startRestartGroup.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 681319509, "CC(remember):BasicTextField.kt#9igjgp");
            boolean changed2 = startRestartGroup.changed(textFieldSelectionState);
            Object rememberedValue4 = startRestartGroup.rememberedValue();
            if (changed2 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                rememberedValue4 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        TextFieldHandleState TextFieldSelectionHandles$lambda$30$lambda$29;
                        TextFieldSelectionHandles$lambda$30$lambda$29 = BasicTextFieldKt.TextFieldSelectionHandles$lambda$30$lambda$29(TextFieldSelectionState.this);
                        return TextFieldSelectionHandles$lambda$30$lambda$29;
                    }
                });
                startRestartGroup.updateRememberedValue(rememberedValue4);
            }
            State state2 = (State) rememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (TextFieldSelectionHandles$lambda$31(state2).getVisible()) {
                startRestartGroup.startReplaceGroup(-353646437);
                ComposerKt.sourceInformation(startRestartGroup, "592@29942L168,601@30334L95,591@29896L659");
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 681329996, "CC(remember):BasicTextField.kt#9igjgp");
                boolean changedInstance3 = startRestartGroup.changedInstance(textFieldSelectionState);
                OffsetProvider rememberedValue5 = startRestartGroup.rememberedValue();
                if (changedInstance3 || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue5 = new OffsetProvider() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$3$1
                        @Override // androidx.compose.foundation.text.selection.OffsetProvider
                        /* renamed from: provide-F1C5BW0 */
                        public final long mo1265provideF1C5BW0() {
                            return TextFieldSelectionState.this.getSelectionHandleState$foundation_release(false, true).m1664getPositionF1C5BW0();
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue5);
                }
                OffsetProvider offsetProvider2 = (OffsetProvider) rememberedValue5;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ResolvedTextDirection direction2 = TextFieldSelectionHandles$lambda$31(state2).getDirection();
                boolean handlesCrossed2 = TextFieldSelectionHandles$lambda$31(state2).getHandlesCrossed();
                Modifier.Companion companion2 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 681342467, "CC(remember):BasicTextField.kt#9igjgp");
                boolean changedInstance4 = startRestartGroup.changedInstance(textFieldSelectionState);
                PointerInputEventHandler rememberedValue6 = startRestartGroup.rememberedValue();
                if (changedInstance4 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue6 = new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$4$1
                        @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                            Object selectionHandleGestures = TextFieldSelectionState.this.selectionHandleGestures(pointerInputScope, false, continuation);
                            return selectionHandleGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? selectionHandleGestures : Unit.INSTANCE;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue6);
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                AndroidSelectionHandles_androidKt.m1766SelectionHandlewLIcFTc(offsetProvider2, false, direction2, handlesCrossed2, MinTouchTargetSizeForHandles, TextFieldSelectionHandles$lambda$31(state2).getLineHeight(), SuspendingPointerInputFilterKt.pointerInput(companion2, textFieldSelectionState, (PointerInputEventHandler) rememberedValue6), startRestartGroup, 24624, 0);
                startRestartGroup.endReplaceGroup();
            } else {
                startRestartGroup.startReplaceGroup(-352997762);
                startRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit TextFieldSelectionHandles$lambda$34;
                    TextFieldSelectionHandles$lambda$34 = BasicTextFieldKt.TextFieldSelectionHandles$lambda$34(TextFieldSelectionState.this, i, (Composer) obj, ((Integer) obj2).intValue());
                    return TextFieldSelectionHandles$lambda$34;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldHandleState TextFieldSelectionHandles$lambda$25$lambda$24(TextFieldSelectionState textFieldSelectionState) {
        return textFieldSelectionState.getSelectionHandleState$foundation_release(true, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldHandleState TextFieldSelectionHandles$lambda$30$lambda$29(TextFieldSelectionState textFieldSelectionState) {
        return textFieldSelectionState.getSelectionHandleState$foundation_release(false, false);
    }

    static {
        float f = 40;
        MinTouchTargetSizeForHandles = DpKt.m7421DpSizeYgX7TsA(Dp.m7399constructorimpl(f), Dp.m7399constructorimpl(f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$36$lambda$35(TextLayoutResult textLayoutResult) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x03cc, code lost:
    
        if (r4 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L249;
     */
    /* JADX WARN: Removed duplicated region for block: B:108:0x04d4  */
    /* JADX WARN: Removed duplicated region for block: B:111:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x04b3  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0235  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void BasicTextField(final String str, final Function1<? super String, Unit> function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z3, int i, int i2, VisualTransformation visualTransformation, Function1<? super TextLayoutResult, Unit> function12, MutableInteractionSource mutableInteractionSource, Brush brush, Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i3, final int i4, final int i5) {
        int i6;
        Modifier modifier2;
        int i7;
        boolean z4;
        int i8;
        boolean z5;
        int i9;
        TextStyle textStyle2;
        int i10;
        KeyboardOptions keyboardOptions2;
        int i11;
        KeyboardActions keyboardActions2;
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
        Composer composer2;
        final int i26;
        final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function32;
        final KeyboardActions keyboardActions3;
        final boolean z6;
        final KeyboardOptions keyboardOptions3;
        final boolean z7;
        final TextStyle textStyle3;
        final Modifier modifier3;
        final boolean z8;
        final int i27;
        final VisualTransformation visualTransformation2;
        final Function1<? super TextLayoutResult, Unit> function13;
        final MutableInteractionSource mutableInteractionSource2;
        final Brush brush2;
        ScopeUpdateScope endRestartGroup;
        int i28;
        KeyboardActions keyboardActions4;
        Function1<? super TextLayoutResult, Unit> function14;
        MutableInteractionSource mutableInteractionSource3;
        int i29;
        SolidColor solidColor;
        KeyboardActions keyboardActions5;
        Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function33;
        boolean z9;
        int i30;
        int i31;
        boolean z10;
        boolean z11;
        TextStyle textStyle4;
        Modifier modifier4;
        VisualTransformation visualTransformation3;
        Function1<? super TextLayoutResult, Unit> function15;
        Brush brush3;
        KeyboardOptions keyboardOptions4;
        MutableInteractionSource mutableInteractionSource4;
        int i32;
        Modifier modifier5;
        int i33;
        Composer startRestartGroup = composer.startRestartGroup(2026950908);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,visualTransformation,onTextLayout,interactionSource,cursorBrush,decorationBox)740@38542L57,746@38895L242,746@38884L253,757@39458L41,761@39576L373,759@39505L1033:BasicTextField.kt#423gt5");
        if ((i5 & 1) != 0) {
            i6 = i3 | 6;
        } else if ((i3 & 6) == 0) {
            i6 = (startRestartGroup.changed(str) ? 4 : 2) | i3;
        } else {
            i6 = i3;
        }
        if ((i5 & 2) != 0) {
            i6 |= 48;
        } else if ((i3 & 48) == 0) {
            i6 |= startRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i34 = i5 & 4;
        if (i34 != 0) {
            i6 |= 384;
        } else if ((i3 & 384) == 0) {
            modifier2 = modifier;
            i6 |= startRestartGroup.changed(modifier2) ? 256 : 128;
            i7 = i5 & 8;
            if (i7 == 0) {
                i6 |= 3072;
            } else if ((i3 & 3072) == 0) {
                z4 = z;
                i6 |= startRestartGroup.changed(z4) ? 2048 : 1024;
                i8 = i5 & 16;
                if (i8 != 0) {
                    i6 |= 24576;
                } else if ((i3 & 24576) == 0) {
                    z5 = z2;
                    i6 |= startRestartGroup.changed(z5) ? 16384 : 8192;
                    i9 = i5 & 32;
                    if (i9 == 0) {
                        i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        textStyle2 = textStyle;
                    } else {
                        textStyle2 = textStyle;
                        if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            i6 |= startRestartGroup.changed(textStyle2) ? 131072 : 65536;
                        }
                    }
                    i10 = i5 & 64;
                    if (i10 == 0) {
                        i6 |= 1572864;
                        keyboardOptions2 = keyboardOptions;
                    } else {
                        keyboardOptions2 = keyboardOptions;
                        if ((i3 & 1572864) == 0) {
                            i6 |= startRestartGroup.changed(keyboardOptions2) ? 1048576 : 524288;
                        }
                    }
                    i11 = i5 & 128;
                    if (i11 == 0) {
                        i6 |= 12582912;
                        keyboardActions2 = keyboardActions;
                    } else {
                        keyboardActions2 = keyboardActions;
                        if ((i3 & 12582912) == 0) {
                            i6 |= startRestartGroup.changed(keyboardActions2) ? 8388608 : 4194304;
                        }
                    }
                    i12 = i5 & 256;
                    if (i12 == 0) {
                        i6 |= 100663296;
                    } else if ((i3 & 100663296) == 0) {
                        i6 |= startRestartGroup.changed(z3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                    }
                    if ((i3 & 805306368) == 0) {
                        if ((i5 & 512) == 0 && startRestartGroup.changed(i)) {
                            i33 = 536870912;
                            i6 |= i33;
                        }
                        i33 = 268435456;
                        i6 |= i33;
                    }
                    i13 = i5 & 1024;
                    if (i13 == 0) {
                        i15 = i4 | 6;
                        i14 = i13;
                    } else if ((i4 & 6) == 0) {
                        i14 = i13;
                        i15 = i4 | (startRestartGroup.changed(i2) ? 4 : 2);
                    } else {
                        i14 = i13;
                        i15 = i4;
                    }
                    i16 = i5 & 2048;
                    if (i16 == 0) {
                        i15 |= 48;
                        i17 = i16;
                    } else if ((i4 & 48) == 0) {
                        i17 = i16;
                        i15 |= startRestartGroup.changed(visualTransformation) ? 32 : 16;
                    } else {
                        i17 = i16;
                    }
                    int i35 = i15;
                    i18 = i5 & 4096;
                    if (i18 == 0) {
                        i19 = i35 | 384;
                    } else if ((i4 & 384) == 0) {
                        i19 = i35 | (startRestartGroup.changedInstance(function12) ? 256 : 128);
                    } else {
                        i19 = i35;
                    }
                    i20 = i5 & 8192;
                    if (i20 == 0) {
                        i21 = i19 | 3072;
                    } else {
                        int i36 = i19;
                        if ((i4 & 3072) == 0) {
                            i21 = i36 | (startRestartGroup.changed(mutableInteractionSource) ? 2048 : 1024);
                        } else {
                            i21 = i36;
                        }
                    }
                    i22 = i5 & 16384;
                    if (i22 == 0) {
                        i23 = i21 | 24576;
                    } else {
                        i23 = i21;
                        if ((i4 & 24576) == 0) {
                            i23 |= startRestartGroup.changed(brush) ? 16384 : 8192;
                            i24 = i5 & 32768;
                            if (i24 != 0) {
                                i23 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i23 |= startRestartGroup.changedInstance(function3) ? 131072 : 65536;
                            }
                            i25 = i23;
                            if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (74899 & i25) != 74898, i6 & 1)) {
                                startRestartGroup.startDefaults();
                                ComposerKt.sourceInformation(startRestartGroup, "732@38127L2");
                                if ((i3 & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i5 & 512) != 0) {
                                        i6 &= -1879048193;
                                    }
                                    z9 = z3;
                                    i30 = i;
                                    i31 = i2;
                                    visualTransformation3 = visualTransformation;
                                    function33 = function3;
                                    keyboardActions5 = keyboardActions2;
                                    z10 = z4;
                                    keyboardOptions4 = keyboardOptions2;
                                    z11 = z5;
                                    textStyle4 = textStyle2;
                                    modifier4 = modifier2;
                                    function15 = function12;
                                    mutableInteractionSource4 = mutableInteractionSource;
                                    brush3 = brush;
                                } else {
                                    if (i34 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        z4 = true;
                                    }
                                    if (i8 != 0) {
                                        z5 = false;
                                    }
                                    if (i9 != 0) {
                                        textStyle2 = TextStyle.INSTANCE.getDefault();
                                    }
                                    if (i10 != 0) {
                                        keyboardOptions2 = KeyboardOptions.INSTANCE.getDefault();
                                    }
                                    KeyboardActions keyboardActions6 = i11 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions2;
                                    boolean z12 = i12 != 0 ? false : z3;
                                    if ((i5 & 512) != 0) {
                                        i28 = z12 ? 1 : Integer.MAX_VALUE;
                                        i6 &= -1879048193;
                                    } else {
                                        i28 = i;
                                    }
                                    int i37 = i14 != 0 ? 1 : i2;
                                    VisualTransformation none = i17 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                                    if (i18 != 0) {
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2015310786, "CC(remember):BasicTextField.kt#9igjgp");
                                        Object rememberedValue = startRestartGroup.rememberedValue();
                                        keyboardActions4 = keyboardActions6;
                                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda2
                                                @Override // kotlin.jvm.functions.Function1
                                                public final Object invoke(Object obj) {
                                                    Unit BasicTextField$lambda$36$lambda$35;
                                                    BasicTextField$lambda$36$lambda$35 = BasicTextFieldKt.BasicTextField$lambda$36$lambda$35((TextLayoutResult) obj);
                                                    return BasicTextField$lambda$36$lambda$35;
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue);
                                        }
                                        function14 = (Function1) rememberedValue;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    } else {
                                        keyboardActions4 = keyboardActions6;
                                        function14 = function12;
                                    }
                                    MutableInteractionSource mutableInteractionSource5 = i20 != 0 ? null : mutableInteractionSource;
                                    Function1<? super TextLayoutResult, Unit> function16 = function14;
                                    if (i22 != 0) {
                                        mutableInteractionSource3 = mutableInteractionSource5;
                                        i29 = i6;
                                        solidColor = new SolidColor(Color.INSTANCE.m4641getBlack0d7_KjU(), null);
                                    } else {
                                        mutableInteractionSource3 = mutableInteractionSource5;
                                        i29 = i6;
                                        solidColor = brush;
                                    }
                                    if (i24 != 0) {
                                        i6 = i29;
                                        function33 = ComposableSingletons$BasicTextFieldKt.INSTANCE.getLambda$759698998$foundation_release();
                                        z9 = z12;
                                        i30 = i28;
                                        i31 = i37;
                                        z10 = z4;
                                        z11 = z5;
                                        textStyle4 = textStyle2;
                                        modifier4 = modifier2;
                                        visualTransformation3 = none;
                                        keyboardActions5 = keyboardActions4;
                                    } else {
                                        keyboardActions5 = keyboardActions4;
                                        i6 = i29;
                                        function33 = function3;
                                        z9 = z12;
                                        i30 = i28;
                                        i31 = i37;
                                        z10 = z4;
                                        z11 = z5;
                                        textStyle4 = textStyle2;
                                        modifier4 = modifier2;
                                        visualTransformation3 = none;
                                    }
                                    function15 = function16;
                                    brush3 = solidColor;
                                    keyboardOptions4 = keyboardOptions2;
                                    mutableInteractionSource4 = mutableInteractionSource3;
                                }
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(2026950908, i6, i25, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:737)");
                                }
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2015297451, "CC(remember):BasicTextField.kt#9igjgp");
                                Object rememberedValue2 = startRestartGroup.rememberedValue();
                                if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    i32 = i25;
                                    rememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue(str, 0L, (TextRange) null, 6, (DefaultConstructorMarker) null), null, 2, null);
                                    startRestartGroup.updateRememberedValue(rememberedValue2);
                                } else {
                                    i32 = i25;
                                }
                                final MutableState mutableState = (MutableState) rememberedValue2;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                final TextFieldValue m7098copy3r_uNRQ$default = TextFieldValue.m7098copy3r_uNRQ$default(BasicTextField$lambda$38(mutableState), str, 0L, (TextRange) null, 6, (Object) null);
                                KeyboardActions keyboardActions7 = keyboardActions5;
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2015285970, "CC(remember):BasicTextField.kt#9igjgp");
                                boolean changed = startRestartGroup.changed(m7098copy3r_uNRQ$default);
                                Object rememberedValue3 = startRestartGroup.rememberedValue();
                                if (changed) {
                                    modifier5 = modifier4;
                                } else {
                                    modifier5 = modifier4;
                                }
                                rememberedValue3 = new Function0() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda3
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        Unit BasicTextField$lambda$41$lambda$40;
                                        BasicTextField$lambda$41$lambda$40 = BasicTextFieldKt.BasicTextField$lambda$41$lambda$40(TextFieldValue.this, mutableState);
                                        return BasicTextField$lambda$41$lambda$40;
                                    }
                                };
                                startRestartGroup.updateRememberedValue(rememberedValue3);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                EffectsKt.SideEffect((Function0) rememberedValue3, startRestartGroup, 0);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2015268155, "CC(remember):BasicTextField.kt#9igjgp");
                                boolean z13 = (i6 & 14) == 4;
                                Object rememberedValue4 = startRestartGroup.rememberedValue();
                                if (z13 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str, null, 2, null);
                                    startRestartGroup.updateRememberedValue(rememberedValue4);
                                }
                                final MutableState mutableState2 = (MutableState) rememberedValue4;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ImeOptions imeOptions$foundation_release = keyboardOptions4.toImeOptions$foundation_release(z9);
                                boolean z14 = !z9;
                                int i38 = i32;
                                int i39 = z9 ? 1 : i31;
                                int i40 = z9 ? 1 : i30;
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2015264047, "CC(remember):BasicTextField.kt#9igjgp");
                                boolean changed2 = startRestartGroup.changed(mutableState2) | ((i6 & 112) == 32);
                                Object rememberedValue5 = startRestartGroup.rememberedValue();
                                if (changed2 || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue5 = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda4
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            Unit BasicTextField$lambda$46$lambda$45;
                                            BasicTextField$lambda$46$lambda$45 = BasicTextFieldKt.BasicTextField$lambda$46$lambda$45(Function1.this, mutableState, mutableState2, (TextFieldValue) obj);
                                            return BasicTextField$lambda$46$lambda$45;
                                        }
                                    };
                                    startRestartGroup.updateRememberedValue(rememberedValue5);
                                }
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                int i41 = i38 << 9;
                                Modifier modifier6 = modifier5;
                                composer2 = startRestartGroup;
                                CoreTextFieldKt.CoreTextField(m7098copy3r_uNRQ$default, (Function1) rememberedValue5, modifier6, textStyle4, visualTransformation3, function15, mutableInteractionSource4, brush3, z14, i40, i39, imeOptions$foundation_release, keyboardActions7, z10, z11, function33, null, composer2, (i6 & 896) | ((i6 >> 6) & 7168) | (57344 & i41) | (458752 & i41) | (3670016 & i41) | (i41 & 29360128), ((i6 >> 15) & 896) | (i6 & 7168) | (i6 & 57344) | (i38 & 458752), 65536);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                keyboardOptions3 = keyboardOptions4;
                                modifier3 = modifier6;
                                textStyle3 = textStyle4;
                                mutableInteractionSource2 = mutableInteractionSource4;
                                brush2 = brush3;
                                keyboardActions3 = keyboardActions7;
                                z6 = z10;
                                function32 = function33;
                                z8 = z9;
                                visualTransformation2 = visualTransformation3;
                                function13 = function15;
                                z7 = z11;
                                i26 = i30;
                                i27 = i31;
                            } else {
                                composer2 = startRestartGroup;
                                composer2.skipToGroupEnd();
                                i26 = i;
                                function32 = function3;
                                keyboardActions3 = keyboardActions2;
                                z6 = z4;
                                keyboardOptions3 = keyboardOptions2;
                                z7 = z5;
                                textStyle3 = textStyle2;
                                modifier3 = modifier2;
                                z8 = z3;
                                i27 = i2;
                                visualTransformation2 = visualTransformation;
                                function13 = function12;
                                mutableInteractionSource2 = mutableInteractionSource;
                                brush2 = brush;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda5
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit BasicTextField$lambda$47;
                                        BasicTextField$lambda$47 = BasicTextFieldKt.BasicTextField$lambda$47(str, function1, modifier3, z6, z7, textStyle3, keyboardOptions3, keyboardActions3, z8, i26, i27, visualTransformation2, function13, mutableInteractionSource2, brush2, function32, i3, i4, i5, (Composer) obj, ((Integer) obj2).intValue());
                                        return BasicTextField$lambda$47;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                    }
                    i24 = i5 & 32768;
                    if (i24 != 0) {
                    }
                    i25 = i23;
                    if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (74899 & i25) != 74898, i6 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                z5 = z2;
                i9 = i5 & 32;
                if (i9 == 0) {
                }
                i10 = i5 & 64;
                if (i10 == 0) {
                }
                i11 = i5 & 128;
                if (i11 == 0) {
                }
                i12 = i5 & 256;
                if (i12 == 0) {
                }
                if ((i3 & 805306368) == 0) {
                }
                i13 = i5 & 1024;
                if (i13 == 0) {
                }
                i16 = i5 & 2048;
                if (i16 == 0) {
                }
                int i352 = i15;
                i18 = i5 & 4096;
                if (i18 == 0) {
                }
                i20 = i5 & 8192;
                if (i20 == 0) {
                }
                i22 = i5 & 16384;
                if (i22 == 0) {
                }
                i24 = i5 & 32768;
                if (i24 != 0) {
                }
                i25 = i23;
                if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (74899 & i25) != 74898, i6 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            z4 = z;
            i8 = i5 & 16;
            if (i8 != 0) {
            }
            z5 = z2;
            i9 = i5 & 32;
            if (i9 == 0) {
            }
            i10 = i5 & 64;
            if (i10 == 0) {
            }
            i11 = i5 & 128;
            if (i11 == 0) {
            }
            i12 = i5 & 256;
            if (i12 == 0) {
            }
            if ((i3 & 805306368) == 0) {
            }
            i13 = i5 & 1024;
            if (i13 == 0) {
            }
            i16 = i5 & 2048;
            if (i16 == 0) {
            }
            int i3522 = i15;
            i18 = i5 & 4096;
            if (i18 == 0) {
            }
            i20 = i5 & 8192;
            if (i20 == 0) {
            }
            i22 = i5 & 16384;
            if (i22 == 0) {
            }
            i24 = i5 & 32768;
            if (i24 != 0) {
            }
            i25 = i23;
            if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (74899 & i25) != 74898, i6 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i7 = i5 & 8;
        if (i7 == 0) {
        }
        z4 = z;
        i8 = i5 & 16;
        if (i8 != 0) {
        }
        z5 = z2;
        i9 = i5 & 32;
        if (i9 == 0) {
        }
        i10 = i5 & 64;
        if (i10 == 0) {
        }
        i11 = i5 & 128;
        if (i11 == 0) {
        }
        i12 = i5 & 256;
        if (i12 == 0) {
        }
        if ((i3 & 805306368) == 0) {
        }
        i13 = i5 & 1024;
        if (i13 == 0) {
        }
        i16 = i5 & 2048;
        if (i16 == 0) {
        }
        int i35222 = i15;
        i18 = i5 & 4096;
        if (i18 == 0) {
        }
        i20 = i5 & 8192;
        if (i20 == 0) {
        }
        i22 = i5 & 16384;
        if (i22 == 0) {
        }
        i24 = i5 & 32768;
        if (i24 != 0) {
        }
        i25 = i23;
        if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (74899 & i25) != 74898, i6 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    private static final TextFieldValue BasicTextField$lambda$38(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$41$lambda$40(TextFieldValue textFieldValue, MutableState mutableState) {
        if (!TextRange.m6841equalsimpl0(textFieldValue.getSelection(), BasicTextField$lambda$38(mutableState).getSelection()) || !Intrinsics.areEqual(textFieldValue.getComposition(), BasicTextField$lambda$38(mutableState).getComposition())) {
            mutableState.setValue(textFieldValue);
        }
        return Unit.INSTANCE;
    }

    private static final String BasicTextField$lambda$43(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$46$lambda$45(Function1 function1, MutableState mutableState, MutableState mutableState2, TextFieldValue textFieldValue) {
        mutableState.setValue(textFieldValue);
        boolean areEqual = Intrinsics.areEqual(BasicTextField$lambda$43(mutableState2), textFieldValue.getText());
        mutableState2.setValue(textFieldValue.getText());
        if (!areEqual) {
            function1.invoke(textFieldValue.getText());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$49$lambda$48(TextLayoutResult textLayoutResult) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0449  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void BasicTextField(final TextFieldValue textFieldValue, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z3, int i, int i2, VisualTransformation visualTransformation, Function1<? super TextLayoutResult, Unit> function12, MutableInteractionSource mutableInteractionSource, Brush brush, Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i3, final int i4, final int i5) {
        int i6;
        Modifier modifier2;
        int i7;
        boolean z4;
        int i8;
        boolean z5;
        int i9;
        TextStyle textStyle2;
        int i10;
        KeyboardOptions keyboardOptions2;
        int i11;
        KeyboardActions keyboardActions2;
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
        Composer composer2;
        final int i26;
        final VisualTransformation visualTransformation2;
        final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function32;
        final boolean z6;
        final KeyboardActions keyboardActions3;
        final boolean z7;
        final TextStyle textStyle3;
        final KeyboardOptions keyboardOptions3;
        final Modifier modifier3;
        final boolean z8;
        final int i27;
        final Function1<? super TextLayoutResult, Unit> function13;
        final MutableInteractionSource mutableInteractionSource2;
        final Brush brush2;
        ScopeUpdateScope endRestartGroup;
        int i28;
        Function1<? super TextLayoutResult, Unit> function14;
        MutableInteractionSource mutableInteractionSource3;
        boolean z9;
        int i29;
        SolidColor solidColor;
        boolean z10;
        int i30;
        int i31;
        VisualTransformation visualTransformation3;
        KeyboardOptions keyboardOptions4;
        KeyboardActions keyboardActions4;
        MutableInteractionSource mutableInteractionSource4;
        Brush brush3;
        boolean z11;
        TextStyle textStyle4;
        int i32;
        Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function33;
        boolean z12;
        Function1<? super TextLayoutResult, Unit> function15;
        Function1<? super TextLayoutResult, Unit> function16;
        int i33;
        Composer startRestartGroup = composer.startRestartGroup(-971111025);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,visualTransformation,onTextLayout,interactionSource,cursorBrush,decorationBox)897@47431L90,895@47369L741:BasicTextField.kt#423gt5");
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
        }
        int i34 = i5 & 4;
        if (i34 != 0) {
            i6 |= 384;
        } else if ((i3 & 384) == 0) {
            modifier2 = modifier;
            i6 |= startRestartGroup.changed(modifier2) ? 256 : 128;
            i7 = i5 & 8;
            if (i7 == 0) {
                i6 |= 3072;
            } else if ((i3 & 3072) == 0) {
                z4 = z;
                i6 |= startRestartGroup.changed(z4) ? 2048 : 1024;
                i8 = i5 & 16;
                if (i8 != 0) {
                    i6 |= 24576;
                } else if ((i3 & 24576) == 0) {
                    z5 = z2;
                    i6 |= startRestartGroup.changed(z5) ? 16384 : 8192;
                    i9 = i5 & 32;
                    if (i9 == 0) {
                        i6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        textStyle2 = textStyle;
                    } else {
                        textStyle2 = textStyle;
                        if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            i6 |= startRestartGroup.changed(textStyle2) ? 131072 : 65536;
                        }
                    }
                    i10 = i5 & 64;
                    if (i10 == 0) {
                        i6 |= 1572864;
                        keyboardOptions2 = keyboardOptions;
                    } else {
                        keyboardOptions2 = keyboardOptions;
                        if ((i3 & 1572864) == 0) {
                            i6 |= startRestartGroup.changed(keyboardOptions2) ? 1048576 : 524288;
                        }
                    }
                    i11 = i5 & 128;
                    if (i11 == 0) {
                        i6 |= 12582912;
                        keyboardActions2 = keyboardActions;
                    } else {
                        keyboardActions2 = keyboardActions;
                        if ((i3 & 12582912) == 0) {
                            i6 |= startRestartGroup.changed(keyboardActions2) ? 8388608 : 4194304;
                        }
                    }
                    i12 = i5 & 256;
                    if (i12 == 0) {
                        i6 |= 100663296;
                    } else if ((i3 & 100663296) == 0) {
                        i6 |= startRestartGroup.changed(z3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                    }
                    if ((i3 & 805306368) == 0) {
                        if ((i5 & 512) == 0 && startRestartGroup.changed(i)) {
                            i33 = 536870912;
                            i6 |= i33;
                        }
                        i33 = 268435456;
                        i6 |= i33;
                    }
                    i13 = i5 & 1024;
                    if (i13 == 0) {
                        i15 = i4 | 6;
                        i14 = i13;
                    } else if ((i4 & 6) == 0) {
                        i14 = i13;
                        i15 = i4 | (startRestartGroup.changed(i2) ? 4 : 2);
                    } else {
                        i14 = i13;
                        i15 = i4;
                    }
                    i16 = i5 & 2048;
                    if (i16 == 0) {
                        i15 |= 48;
                        i17 = i16;
                    } else if ((i4 & 48) == 0) {
                        i17 = i16;
                        i15 |= startRestartGroup.changed(visualTransformation) ? 32 : 16;
                    } else {
                        i17 = i16;
                    }
                    int i35 = i15;
                    i18 = i5 & 4096;
                    if (i18 == 0) {
                        i19 = i35 | 384;
                    } else if ((i4 & 384) == 0) {
                        i19 = i35 | (startRestartGroup.changedInstance(function12) ? 256 : 128);
                    } else {
                        i19 = i35;
                    }
                    i20 = i5 & 8192;
                    if (i20 == 0) {
                        i21 = i19 | 3072;
                    } else {
                        int i36 = i19;
                        if ((i4 & 3072) == 0) {
                            i21 = i36 | (startRestartGroup.changed(mutableInteractionSource) ? 2048 : 1024);
                        } else {
                            i21 = i36;
                        }
                    }
                    i22 = i5 & 16384;
                    if (i22 == 0) {
                        i23 = i21 | 24576;
                    } else {
                        i23 = i21;
                        if ((i4 & 24576) == 0) {
                            i23 |= startRestartGroup.changed(brush) ? 16384 : 8192;
                            i24 = i5 & 32768;
                            if (i24 != 0) {
                                i23 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i23 |= startRestartGroup.changedInstance(function3) ? 131072 : 65536;
                            }
                            i25 = i23;
                            if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (74899 & i25) != 74898, i6 & 1)) {
                                startRestartGroup.startDefaults();
                                ComposerKt.sourceInformation(startRestartGroup, "889@47108L2");
                                if ((i3 & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i5 & 512) != 0) {
                                        i6 &= -1879048193;
                                    }
                                    z10 = z3;
                                    i30 = i;
                                    i31 = i2;
                                    brush3 = brush;
                                    i32 = i6;
                                    keyboardActions4 = keyboardActions2;
                                    textStyle4 = textStyle2;
                                    keyboardOptions4 = keyboardOptions2;
                                    visualTransformation3 = visualTransformation;
                                    mutableInteractionSource4 = mutableInteractionSource;
                                    z12 = z4;
                                    z11 = z5;
                                    function15 = function12;
                                    function33 = function3;
                                } else {
                                    if (i34 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        z4 = true;
                                    }
                                    if (i8 != 0) {
                                        z5 = false;
                                    }
                                    if (i9 != 0) {
                                        textStyle2 = TextStyle.INSTANCE.getDefault();
                                    }
                                    if (i10 != 0) {
                                        keyboardOptions2 = KeyboardOptions.INSTANCE.getDefault();
                                    }
                                    KeyboardActions keyboardActions5 = i11 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions2;
                                    boolean z13 = i12 != 0 ? false : z3;
                                    if ((i5 & 512) != 0) {
                                        i28 = z13 ? 1 : Integer.MAX_VALUE;
                                        i6 &= -1879048193;
                                    } else {
                                        i28 = i;
                                    }
                                    int i37 = i14 != 0 ? 1 : i2;
                                    VisualTransformation none = i17 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                                    KeyboardActions keyboardActions6 = keyboardActions5;
                                    if (i18 != 0) {
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1229772271, "CC(remember):BasicTextField.kt#9igjgp");
                                        Object rememberedValue = startRestartGroup.rememberedValue();
                                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda13
                                                @Override // kotlin.jvm.functions.Function1
                                                public final Object invoke(Object obj) {
                                                    Unit BasicTextField$lambda$49$lambda$48;
                                                    BasicTextField$lambda$49$lambda$48 = BasicTextFieldKt.BasicTextField$lambda$49$lambda$48((TextLayoutResult) obj);
                                                    return BasicTextField$lambda$49$lambda$48;
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue);
                                        }
                                        function14 = (Function1) rememberedValue;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    } else {
                                        function14 = function12;
                                    }
                                    MutableInteractionSource mutableInteractionSource5 = i20 != 0 ? null : mutableInteractionSource;
                                    Function1<? super TextLayoutResult, Unit> function17 = function14;
                                    if (i22 != 0) {
                                        mutableInteractionSource3 = mutableInteractionSource5;
                                        z9 = z13;
                                        i29 = i6;
                                        solidColor = new SolidColor(Color.INSTANCE.m4641getBlack0d7_KjU(), null);
                                    } else {
                                        mutableInteractionSource3 = mutableInteractionSource5;
                                        z9 = z13;
                                        i29 = i6;
                                        solidColor = brush;
                                    }
                                    if (i24 != 0) {
                                        boolean z14 = z5;
                                        function33 = ComposableSingletons$BasicTextFieldKt.INSTANCE.getLambda$486633673$foundation_release();
                                        keyboardOptions4 = keyboardOptions2;
                                        z11 = z14;
                                        z10 = z9;
                                        i30 = i28;
                                        i31 = i37;
                                        visualTransformation3 = none;
                                        keyboardActions4 = keyboardActions6;
                                        mutableInteractionSource4 = mutableInteractionSource3;
                                        brush3 = solidColor;
                                        textStyle4 = textStyle2;
                                        i32 = i29;
                                    } else {
                                        z10 = z9;
                                        i30 = i28;
                                        i31 = i37;
                                        visualTransformation3 = none;
                                        keyboardOptions4 = keyboardOptions2;
                                        keyboardActions4 = keyboardActions6;
                                        mutableInteractionSource4 = mutableInteractionSource3;
                                        brush3 = solidColor;
                                        z11 = z5;
                                        textStyle4 = textStyle2;
                                        i32 = i29;
                                        function33 = function3;
                                    }
                                    z12 = z4;
                                    function15 = function17;
                                }
                                startRestartGroup.endDefaults();
                                VisualTransformation visualTransformation4 = visualTransformation3;
                                if (ComposerKt.isTraceInProgress()) {
                                    function16 = function15;
                                    ComposerKt.traceEventStart(-971111025, i32, i25, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:894)");
                                } else {
                                    function16 = function15;
                                }
                                Modifier modifier4 = modifier2;
                                Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function34 = function33;
                                ImeOptions imeOptions$foundation_release = keyboardOptions4.toImeOptions$foundation_release(z10);
                                boolean z15 = !z10;
                                Brush brush4 = brush3;
                                int i38 = z10 ? 1 : i31;
                                MutableInteractionSource mutableInteractionSource6 = mutableInteractionSource4;
                                int i39 = z10 ? 1 : i30;
                                KeyboardOptions keyboardOptions5 = keyboardOptions4;
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1229761847, "CC(remember):BasicTextField.kt#9igjgp");
                                boolean z16 = ((i32 & 14) == 4) | ((i32 & 112) == 32);
                                Object rememberedValue2 = startRestartGroup.rememberedValue();
                                if (z16 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue2 = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda14
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            Unit BasicTextField$lambda$51$lambda$50;
                                            BasicTextField$lambda$51$lambda$50 = BasicTextFieldKt.BasicTextField$lambda$51$lambda$50(TextFieldValue.this, function1, (TextFieldValue) obj);
                                            return BasicTextField$lambda$51$lambda$50;
                                        }
                                    };
                                    startRestartGroup.updateRememberedValue(rememberedValue2);
                                }
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                int i40 = i25 << 9;
                                int i41 = ((i32 >> 6) & 7168) | (i32 & 910) | (i40 & 57344) | (i40 & 458752) | (i40 & 3670016) | (i40 & 29360128);
                                int i42 = (i32 & 7168) | ((i32 >> 15) & 896) | (57344 & i32) | (i25 & 458752);
                                composer2 = startRestartGroup;
                                TextStyle textStyle5 = textStyle4;
                                boolean z17 = z10;
                                Function1<? super TextLayoutResult, Unit> function18 = function16;
                                CoreTextFieldKt.CoreTextField(textFieldValue, (Function1) rememberedValue2, modifier4, textStyle5, visualTransformation4, function18, mutableInteractionSource6, brush4, z15, i39, i38, imeOptions$foundation_release, keyboardActions4, z12, z11, function34, null, composer2, i41, i42, 65536);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                keyboardActions3 = keyboardActions4;
                                function32 = function34;
                                i26 = i30;
                                i27 = i31;
                                z8 = z17;
                                visualTransformation2 = visualTransformation4;
                                brush2 = brush4;
                                z6 = z12;
                                keyboardOptions3 = keyboardOptions5;
                                function13 = function18;
                                z7 = z11;
                                mutableInteractionSource2 = mutableInteractionSource6;
                                textStyle3 = textStyle5;
                                modifier3 = modifier4;
                            } else {
                                composer2 = startRestartGroup;
                                composer2.skipToGroupEnd();
                                i26 = i;
                                visualTransformation2 = visualTransformation;
                                function32 = function3;
                                z6 = z4;
                                keyboardActions3 = keyboardActions2;
                                z7 = z5;
                                textStyle3 = textStyle2;
                                keyboardOptions3 = keyboardOptions2;
                                modifier3 = modifier2;
                                z8 = z3;
                                i27 = i2;
                                function13 = function12;
                                mutableInteractionSource2 = mutableInteractionSource;
                                brush2 = brush;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda15
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit BasicTextField$lambda$52;
                                        BasicTextField$lambda$52 = BasicTextFieldKt.BasicTextField$lambda$52(TextFieldValue.this, function1, modifier3, z6, z7, textStyle3, keyboardOptions3, keyboardActions3, z8, i26, i27, visualTransformation2, function13, mutableInteractionSource2, brush2, function32, i3, i4, i5, (Composer) obj, ((Integer) obj2).intValue());
                                        return BasicTextField$lambda$52;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                    }
                    i24 = i5 & 32768;
                    if (i24 != 0) {
                    }
                    i25 = i23;
                    if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (74899 & i25) != 74898, i6 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                z5 = z2;
                i9 = i5 & 32;
                if (i9 == 0) {
                }
                i10 = i5 & 64;
                if (i10 == 0) {
                }
                i11 = i5 & 128;
                if (i11 == 0) {
                }
                i12 = i5 & 256;
                if (i12 == 0) {
                }
                if ((i3 & 805306368) == 0) {
                }
                i13 = i5 & 1024;
                if (i13 == 0) {
                }
                i16 = i5 & 2048;
                if (i16 == 0) {
                }
                int i352 = i15;
                i18 = i5 & 4096;
                if (i18 == 0) {
                }
                i20 = i5 & 8192;
                if (i20 == 0) {
                }
                i22 = i5 & 16384;
                if (i22 == 0) {
                }
                i24 = i5 & 32768;
                if (i24 != 0) {
                }
                i25 = i23;
                if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (74899 & i25) != 74898, i6 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            z4 = z;
            i8 = i5 & 16;
            if (i8 != 0) {
            }
            z5 = z2;
            i9 = i5 & 32;
            if (i9 == 0) {
            }
            i10 = i5 & 64;
            if (i10 == 0) {
            }
            i11 = i5 & 128;
            if (i11 == 0) {
            }
            i12 = i5 & 256;
            if (i12 == 0) {
            }
            if ((i3 & 805306368) == 0) {
            }
            i13 = i5 & 1024;
            if (i13 == 0) {
            }
            i16 = i5 & 2048;
            if (i16 == 0) {
            }
            int i3522 = i15;
            i18 = i5 & 4096;
            if (i18 == 0) {
            }
            i20 = i5 & 8192;
            if (i20 == 0) {
            }
            i22 = i5 & 16384;
            if (i22 == 0) {
            }
            i24 = i5 & 32768;
            if (i24 != 0) {
            }
            i25 = i23;
            if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (74899 & i25) != 74898, i6 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i7 = i5 & 8;
        if (i7 == 0) {
        }
        z4 = z;
        i8 = i5 & 16;
        if (i8 != 0) {
        }
        z5 = z2;
        i9 = i5 & 32;
        if (i9 == 0) {
        }
        i10 = i5 & 64;
        if (i10 == 0) {
        }
        i11 = i5 & 128;
        if (i11 == 0) {
        }
        i12 = i5 & 256;
        if (i12 == 0) {
        }
        if ((i3 & 805306368) == 0) {
        }
        i13 = i5 & 1024;
        if (i13 == 0) {
        }
        i16 = i5 & 2048;
        if (i16 == 0) {
        }
        int i35222 = i15;
        i18 = i5 & 4096;
        if (i18 == 0) {
        }
        i20 = i5 & 8192;
        if (i20 == 0) {
        }
        i22 = i5 & 16384;
        if (i22 == 0) {
        }
        i24 = i5 & 32768;
        if (i24 != 0) {
        }
        i25 = i23;
        if (startRestartGroup.shouldExecute((i6 & 306783379) == 306783378 || (74899 & i25) != 74898, i6 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$51$lambda$50(TextFieldValue textFieldValue, Function1 function1, TextFieldValue textFieldValue2) {
        if (!Intrinsics.areEqual(textFieldValue, textFieldValue2)) {
            function1.invoke(textFieldValue2);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$54$lambda$53(TextLayoutResult textLayoutResult) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0362  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void BasicTextField(final String str, final Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z3, int i, VisualTransformation visualTransformation, Function1 function12, MutableInteractionSource mutableInteractionSource, Brush brush, Function3 function3, Composer composer, final int i2, final int i3, final int i4) {
        String str2;
        int i5;
        int i6;
        Modifier modifier2;
        int i7;
        int i8;
        int i9;
        TextStyle textStyle2;
        int i10;
        KeyboardOptions keyboardOptions2;
        int i11;
        KeyboardActions keyboardActions2;
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
        Composer composer2;
        final boolean z4;
        final boolean z5;
        final boolean z6;
        final Function1 function13;
        final MutableInteractionSource mutableInteractionSource2;
        final TextStyle textStyle3;
        final KeyboardOptions keyboardOptions3;
        final KeyboardActions keyboardActions3;
        final Modifier modifier3;
        final int i26;
        final VisualTransformation visualTransformation2;
        final Brush brush2;
        final Function3 function32;
        ScopeUpdateScope endRestartGroup;
        Function1 function14;
        int i27;
        MutableInteractionSource mutableInteractionSource3;
        Brush brush3;
        int i28;
        Composer startRestartGroup = composer.startRestartGroup(74291967);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,keyboardOptions,keyboardActions,singleLine,maxLines,visualTransformation,onTextLayout,interactionSource,cursorBrush,decorationBox)933@48746L2,934@48800L39,939@49041L580:BasicTextField.kt#423gt5");
        if ((i4 & 1) != 0) {
            i5 = i2 | 6;
            str2 = str;
        } else if ((i2 & 6) == 0) {
            str2 = str;
            i5 = (startRestartGroup.changed(str2) ? 4 : 2) | i2;
        } else {
            str2 = str;
            i5 = i2;
        }
        if ((i4 & 2) != 0) {
            i5 |= 48;
        } else if ((i2 & 48) == 0) {
            i5 |= startRestartGroup.changedInstance(function1) ? 32 : 16;
            i6 = i4 & 4;
            if (i6 == 0) {
                i5 |= 384;
            } else if ((i2 & 384) == 0) {
                modifier2 = modifier;
                i5 |= startRestartGroup.changed(modifier2) ? 256 : 128;
                i7 = i4 & 8;
                if (i7 != 0) {
                    i5 |= 3072;
                } else if ((i2 & 3072) == 0) {
                    i5 |= startRestartGroup.changed(z) ? 2048 : 1024;
                    i8 = i4 & 16;
                    if (i8 == 0) {
                        i5 |= 24576;
                    } else if ((i2 & 24576) == 0) {
                        i5 |= startRestartGroup.changed(z2) ? 16384 : 8192;
                        i9 = i4 & 32;
                        if (i9 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            textStyle2 = textStyle;
                        } else {
                            textStyle2 = textStyle;
                            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i5 |= startRestartGroup.changed(textStyle2) ? 131072 : 65536;
                            }
                        }
                        i10 = i4 & 64;
                        if (i10 != 0) {
                            i5 |= 1572864;
                            keyboardOptions2 = keyboardOptions;
                        } else {
                            keyboardOptions2 = keyboardOptions;
                            if ((i2 & 1572864) == 0) {
                                i5 |= startRestartGroup.changed(keyboardOptions2) ? 1048576 : 524288;
                            }
                        }
                        i11 = i4 & 128;
                        if (i11 != 0) {
                            i5 |= 12582912;
                            keyboardActions2 = keyboardActions;
                        } else {
                            keyboardActions2 = keyboardActions;
                            if ((i2 & 12582912) == 0) {
                                i5 |= startRestartGroup.changed(keyboardActions2) ? 8388608 : 4194304;
                            }
                        }
                        i12 = i4 & 256;
                        if (i12 != 0) {
                            i5 |= 100663296;
                        } else if ((i2 & 100663296) == 0) {
                            i5 |= startRestartGroup.changed(z3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        }
                        i13 = i4 & 512;
                        if (i13 != 0) {
                            i5 |= 805306368;
                        } else if ((i2 & 805306368) == 0) {
                            i14 = i13;
                            i5 |= startRestartGroup.changed(i) ? 536870912 : 268435456;
                            i15 = i4 & 1024;
                            if (i15 == 0) {
                                i17 = i3 | 6;
                                i16 = i15;
                            } else if ((i3 & 6) == 0) {
                                i16 = i15;
                                i17 = i3 | (startRestartGroup.changed(visualTransformation) ? 4 : 2);
                            } else {
                                i16 = i15;
                                i17 = i3;
                            }
                            i18 = i4 & 2048;
                            if (i18 == 0) {
                                i17 |= 48;
                                i19 = i18;
                            } else if ((i3 & 48) == 0) {
                                i19 = i18;
                                i17 |= startRestartGroup.changedInstance(function12) ? 32 : 16;
                            } else {
                                i19 = i18;
                            }
                            int i29 = i17;
                            i20 = i4 & 4096;
                            if (i20 == 0) {
                                i21 = i29 | 384;
                            } else {
                                int i30 = i29;
                                if ((i3 & 384) == 0) {
                                    i30 |= startRestartGroup.changed(mutableInteractionSource) ? 256 : 128;
                                }
                                i21 = i30;
                            }
                            i22 = i4 & 8192;
                            if (i22 == 0) {
                                i23 = i21 | 3072;
                            } else {
                                int i31 = i21;
                                if ((i3 & 3072) == 0) {
                                    i23 = i31 | (startRestartGroup.changed(brush) ? 2048 : 1024);
                                } else {
                                    i23 = i31;
                                }
                            }
                            i24 = i4 & 16384;
                            if (i24 == 0) {
                                i25 = i23 | 24576;
                            } else {
                                int i32 = i23;
                                if ((i3 & 24576) == 0) {
                                    i32 |= startRestartGroup.changedInstance(function3) ? 16384 : 8192;
                                }
                                i25 = i32;
                            }
                            if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i25 & 9363) != 9362, i5 & 1)) {
                                composer2 = startRestartGroup;
                                composer2.skipToGroupEnd();
                                z4 = z;
                                z5 = z2;
                                z6 = z3;
                                function13 = function12;
                                mutableInteractionSource2 = mutableInteractionSource;
                                textStyle3 = textStyle2;
                                keyboardOptions3 = keyboardOptions2;
                                keyboardActions3 = keyboardActions2;
                                modifier3 = modifier2;
                                i26 = i;
                                visualTransformation2 = visualTransformation;
                                brush2 = brush;
                                function32 = function3;
                            } else {
                                Modifier modifier4 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                                boolean z7 = i7 != 0 ? true : z;
                                boolean z8 = i8 != 0 ? false : z2;
                                TextStyle textStyle4 = i9 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle2;
                                KeyboardOptions keyboardOptions4 = i10 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions2;
                                KeyboardActions keyboardActions4 = i11 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions2;
                                boolean z9 = i12 != 0 ? false : z3;
                                int i33 = i14 != 0 ? Integer.MAX_VALUE : i;
                                VisualTransformation none = i16 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                                if (i19 != 0) {
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1619843519, "CC(remember):BasicTextField.kt#9igjgp");
                                    Object rememberedValue = startRestartGroup.rememberedValue();
                                    if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda16
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                Unit BasicTextField$lambda$54$lambda$53;
                                                BasicTextField$lambda$54$lambda$53 = BasicTextFieldKt.BasicTextField$lambda$54$lambda$53((TextLayoutResult) obj);
                                                return BasicTextField$lambda$54$lambda$53;
                                            }
                                        };
                                        startRestartGroup.updateRememberedValue(rememberedValue);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    function14 = (Function1) rememberedValue;
                                } else {
                                    function14 = function12;
                                }
                                if (i20 != 0) {
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1619841754, "CC(remember):BasicTextField.kt#9igjgp");
                                    Object rememberedValue2 = startRestartGroup.rememberedValue();
                                    if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue2 = InteractionSourceKt.MutableInteractionSource();
                                        startRestartGroup.updateRememberedValue(rememberedValue2);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    mutableInteractionSource3 = (MutableInteractionSource) rememberedValue2;
                                    i27 = i22;
                                } else {
                                    i27 = i22;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                if (i27 != 0) {
                                    composer2 = startRestartGroup;
                                    brush3 = new SolidColor(Color.INSTANCE.m4641getBlack0d7_KjU(), null);
                                    i28 = i24;
                                } else {
                                    composer2 = startRestartGroup;
                                    brush3 = brush;
                                    i28 = i24;
                                }
                                Function3 lambda$444370233$foundation_release = i28 != 0 ? ComposableSingletons$BasicTextFieldKt.INSTANCE.getLambda$444370233$foundation_release() : function3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(74291967, i5, i25, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:938)");
                                }
                                int i34 = i25 << 3;
                                boolean z10 = z8;
                                BasicTextField(str2, (Function1<? super String, Unit>) function1, modifier4, z7, z10, textStyle4, keyboardOptions4, keyboardActions4, z9, i33, 1, none, (Function1<? super TextLayoutResult, Unit>) function14, mutableInteractionSource3, brush3, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) lambda$444370233$foundation_release, composer2, i5 & 2147483646, (i34 & 112) | 6 | (i34 & 896) | (i34 & 7168) | (57344 & i34) | (i34 & 458752), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                z4 = z7;
                                z5 = z10;
                                textStyle3 = textStyle4;
                                keyboardOptions3 = keyboardOptions4;
                                keyboardActions3 = keyboardActions4;
                                z6 = z9;
                                i26 = i33;
                                visualTransformation2 = none;
                                function13 = function14;
                                mutableInteractionSource2 = mutableInteractionSource3;
                                brush2 = brush3;
                                function32 = lambda$444370233$foundation_release;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda17
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit BasicTextField$lambda$56;
                                        BasicTextField$lambda$56 = BasicTextFieldKt.BasicTextField$lambda$56(str, function1, modifier3, z4, z5, textStyle3, keyboardOptions3, keyboardActions3, z6, i26, visualTransformation2, function13, mutableInteractionSource2, brush2, function32, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                        return BasicTextField$lambda$56;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        i14 = i13;
                        i15 = i4 & 1024;
                        if (i15 == 0) {
                        }
                        i18 = i4 & 2048;
                        if (i18 == 0) {
                        }
                        int i292 = i17;
                        i20 = i4 & 4096;
                        if (i20 == 0) {
                        }
                        i22 = i4 & 8192;
                        if (i22 == 0) {
                        }
                        i24 = i4 & 16384;
                        if (i24 == 0) {
                        }
                        if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i25 & 9363) != 9362, i5 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i9 = i4 & 32;
                    if (i9 != 0) {
                    }
                    i10 = i4 & 64;
                    if (i10 != 0) {
                    }
                    i11 = i4 & 128;
                    if (i11 != 0) {
                    }
                    i12 = i4 & 256;
                    if (i12 != 0) {
                    }
                    i13 = i4 & 512;
                    if (i13 != 0) {
                    }
                    i14 = i13;
                    i15 = i4 & 1024;
                    if (i15 == 0) {
                    }
                    i18 = i4 & 2048;
                    if (i18 == 0) {
                    }
                    int i2922 = i17;
                    i20 = i4 & 4096;
                    if (i20 == 0) {
                    }
                    i22 = i4 & 8192;
                    if (i22 == 0) {
                    }
                    i24 = i4 & 16384;
                    if (i24 == 0) {
                    }
                    if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i25 & 9363) != 9362, i5 & 1)) {
                    }
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
                i10 = i4 & 64;
                if (i10 != 0) {
                }
                i11 = i4 & 128;
                if (i11 != 0) {
                }
                i12 = i4 & 256;
                if (i12 != 0) {
                }
                i13 = i4 & 512;
                if (i13 != 0) {
                }
                i14 = i13;
                i15 = i4 & 1024;
                if (i15 == 0) {
                }
                i18 = i4 & 2048;
                if (i18 == 0) {
                }
                int i29222 = i17;
                i20 = i4 & 4096;
                if (i20 == 0) {
                }
                i22 = i4 & 8192;
                if (i22 == 0) {
                }
                i24 = i4 & 16384;
                if (i24 == 0) {
                }
                if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i25 & 9363) != 9362, i5 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            modifier2 = modifier;
            i7 = i4 & 8;
            if (i7 != 0) {
            }
            i8 = i4 & 16;
            if (i8 == 0) {
            }
            i9 = i4 & 32;
            if (i9 != 0) {
            }
            i10 = i4 & 64;
            if (i10 != 0) {
            }
            i11 = i4 & 128;
            if (i11 != 0) {
            }
            i12 = i4 & 256;
            if (i12 != 0) {
            }
            i13 = i4 & 512;
            if (i13 != 0) {
            }
            i14 = i13;
            i15 = i4 & 1024;
            if (i15 == 0) {
            }
            i18 = i4 & 2048;
            if (i18 == 0) {
            }
            int i292222 = i17;
            i20 = i4 & 4096;
            if (i20 == 0) {
            }
            i22 = i4 & 8192;
            if (i22 == 0) {
            }
            i24 = i4 & 16384;
            if (i24 == 0) {
            }
            if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i25 & 9363) != 9362, i5 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        i6 = i4 & 4;
        if (i6 == 0) {
        }
        modifier2 = modifier;
        i7 = i4 & 8;
        if (i7 != 0) {
        }
        i8 = i4 & 16;
        if (i8 == 0) {
        }
        i9 = i4 & 32;
        if (i9 != 0) {
        }
        i10 = i4 & 64;
        if (i10 != 0) {
        }
        i11 = i4 & 128;
        if (i11 != 0) {
        }
        i12 = i4 & 256;
        if (i12 != 0) {
        }
        i13 = i4 & 512;
        if (i13 != 0) {
        }
        i14 = i13;
        i15 = i4 & 1024;
        if (i15 == 0) {
        }
        i18 = i4 & 2048;
        if (i18 == 0) {
        }
        int i2922222 = i17;
        i20 = i4 & 4096;
        if (i20 == 0) {
        }
        i22 = i4 & 8192;
        if (i22 == 0) {
        }
        i24 = i4 & 16384;
        if (i24 == 0) {
        }
        if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i25 & 9363) != 9362, i5 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicTextField$lambda$58$lambda$57(TextLayoutResult textLayoutResult) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0362  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void BasicTextField(final TextFieldValue textFieldValue, final Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z3, int i, VisualTransformation visualTransformation, Function1 function12, MutableInteractionSource mutableInteractionSource, Brush brush, Function3 function3, Composer composer, final int i2, final int i3, final int i4) {
        TextFieldValue textFieldValue2;
        int i5;
        int i6;
        Modifier modifier2;
        int i7;
        int i8;
        int i9;
        TextStyle textStyle2;
        int i10;
        KeyboardOptions keyboardOptions2;
        int i11;
        KeyboardActions keyboardActions2;
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
        Composer composer2;
        final boolean z4;
        final boolean z5;
        final boolean z6;
        final Function1 function13;
        final MutableInteractionSource mutableInteractionSource2;
        final TextStyle textStyle3;
        final KeyboardOptions keyboardOptions3;
        final KeyboardActions keyboardActions3;
        final Modifier modifier3;
        final int i26;
        final VisualTransformation visualTransformation2;
        final Brush brush2;
        final Function3 function32;
        ScopeUpdateScope endRestartGroup;
        Function1 function14;
        int i27;
        MutableInteractionSource mutableInteractionSource3;
        Brush brush3;
        int i28;
        Composer startRestartGroup = composer.startRestartGroup(1742344466);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,keyboardOptions,keyboardActions,singleLine,maxLines,visualTransformation,onTextLayout,interactionSource,cursorBrush,decorationBox)973@50273L2,974@50327L39,979@50568L580:BasicTextField.kt#423gt5");
        if ((i4 & 1) != 0) {
            i5 = i2 | 6;
            textFieldValue2 = textFieldValue;
        } else if ((i2 & 6) == 0) {
            textFieldValue2 = textFieldValue;
            i5 = (startRestartGroup.changed(textFieldValue2) ? 4 : 2) | i2;
        } else {
            textFieldValue2 = textFieldValue;
            i5 = i2;
        }
        if ((i4 & 2) != 0) {
            i5 |= 48;
        } else if ((i2 & 48) == 0) {
            i5 |= startRestartGroup.changedInstance(function1) ? 32 : 16;
            i6 = i4 & 4;
            if (i6 == 0) {
                i5 |= 384;
            } else if ((i2 & 384) == 0) {
                modifier2 = modifier;
                i5 |= startRestartGroup.changed(modifier2) ? 256 : 128;
                i7 = i4 & 8;
                if (i7 != 0) {
                    i5 |= 3072;
                } else if ((i2 & 3072) == 0) {
                    i5 |= startRestartGroup.changed(z) ? 2048 : 1024;
                    i8 = i4 & 16;
                    if (i8 == 0) {
                        i5 |= 24576;
                    } else if ((i2 & 24576) == 0) {
                        i5 |= startRestartGroup.changed(z2) ? 16384 : 8192;
                        i9 = i4 & 32;
                        if (i9 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            textStyle2 = textStyle;
                        } else {
                            textStyle2 = textStyle;
                            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i5 |= startRestartGroup.changed(textStyle2) ? 131072 : 65536;
                            }
                        }
                        i10 = i4 & 64;
                        if (i10 != 0) {
                            i5 |= 1572864;
                            keyboardOptions2 = keyboardOptions;
                        } else {
                            keyboardOptions2 = keyboardOptions;
                            if ((i2 & 1572864) == 0) {
                                i5 |= startRestartGroup.changed(keyboardOptions2) ? 1048576 : 524288;
                            }
                        }
                        i11 = i4 & 128;
                        if (i11 != 0) {
                            i5 |= 12582912;
                            keyboardActions2 = keyboardActions;
                        } else {
                            keyboardActions2 = keyboardActions;
                            if ((i2 & 12582912) == 0) {
                                i5 |= startRestartGroup.changed(keyboardActions2) ? 8388608 : 4194304;
                            }
                        }
                        i12 = i4 & 256;
                        if (i12 != 0) {
                            i5 |= 100663296;
                        } else if ((i2 & 100663296) == 0) {
                            i5 |= startRestartGroup.changed(z3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        }
                        i13 = i4 & 512;
                        if (i13 != 0) {
                            i5 |= 805306368;
                        } else if ((i2 & 805306368) == 0) {
                            i14 = i13;
                            i5 |= startRestartGroup.changed(i) ? 536870912 : 268435456;
                            i15 = i4 & 1024;
                            if (i15 == 0) {
                                i17 = i3 | 6;
                                i16 = i15;
                            } else if ((i3 & 6) == 0) {
                                i16 = i15;
                                i17 = i3 | (startRestartGroup.changed(visualTransformation) ? 4 : 2);
                            } else {
                                i16 = i15;
                                i17 = i3;
                            }
                            i18 = i4 & 2048;
                            if (i18 == 0) {
                                i17 |= 48;
                                i19 = i18;
                            } else if ((i3 & 48) == 0) {
                                i19 = i18;
                                i17 |= startRestartGroup.changedInstance(function12) ? 32 : 16;
                            } else {
                                i19 = i18;
                            }
                            int i29 = i17;
                            i20 = i4 & 4096;
                            if (i20 == 0) {
                                i21 = i29 | 384;
                            } else {
                                int i30 = i29;
                                if ((i3 & 384) == 0) {
                                    i30 |= startRestartGroup.changed(mutableInteractionSource) ? 256 : 128;
                                }
                                i21 = i30;
                            }
                            i22 = i4 & 8192;
                            if (i22 == 0) {
                                i23 = i21 | 3072;
                            } else {
                                int i31 = i21;
                                if ((i3 & 3072) == 0) {
                                    i23 = i31 | (startRestartGroup.changed(brush) ? 2048 : 1024);
                                } else {
                                    i23 = i31;
                                }
                            }
                            i24 = i4 & 16384;
                            if (i24 == 0) {
                                i25 = i23 | 24576;
                            } else {
                                int i32 = i23;
                                if ((i3 & 24576) == 0) {
                                    i32 |= startRestartGroup.changedInstance(function3) ? 16384 : 8192;
                                }
                                i25 = i32;
                            }
                            if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i25 & 9363) != 9362, i5 & 1)) {
                                composer2 = startRestartGroup;
                                composer2.skipToGroupEnd();
                                z4 = z;
                                z5 = z2;
                                z6 = z3;
                                function13 = function12;
                                mutableInteractionSource2 = mutableInteractionSource;
                                textStyle3 = textStyle2;
                                keyboardOptions3 = keyboardOptions2;
                                keyboardActions3 = keyboardActions2;
                                modifier3 = modifier2;
                                i26 = i;
                                visualTransformation2 = visualTransformation;
                                brush2 = brush;
                                function32 = function3;
                            } else {
                                Modifier modifier4 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                                boolean z7 = i7 != 0 ? true : z;
                                boolean z8 = i8 != 0 ? false : z2;
                                TextStyle textStyle4 = i9 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle2;
                                KeyboardOptions keyboardOptions4 = i10 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions2;
                                KeyboardActions keyboardActions4 = i11 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions2;
                                boolean z9 = i12 != 0 ? false : z3;
                                int i33 = i14 != 0 ? Integer.MAX_VALUE : i;
                                VisualTransformation none = i16 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                                if (i19 != 0) {
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -644192876, "CC(remember):BasicTextField.kt#9igjgp");
                                    Object rememberedValue = startRestartGroup.rememberedValue();
                                    if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda0
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                Unit BasicTextField$lambda$58$lambda$57;
                                                BasicTextField$lambda$58$lambda$57 = BasicTextFieldKt.BasicTextField$lambda$58$lambda$57((TextLayoutResult) obj);
                                                return BasicTextField$lambda$58$lambda$57;
                                            }
                                        };
                                        startRestartGroup.updateRememberedValue(rememberedValue);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    function14 = (Function1) rememberedValue;
                                } else {
                                    function14 = function12;
                                }
                                if (i20 != 0) {
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -644191111, "CC(remember):BasicTextField.kt#9igjgp");
                                    Object rememberedValue2 = startRestartGroup.rememberedValue();
                                    if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue2 = InteractionSourceKt.MutableInteractionSource();
                                        startRestartGroup.updateRememberedValue(rememberedValue2);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    mutableInteractionSource3 = (MutableInteractionSource) rememberedValue2;
                                    i27 = i22;
                                } else {
                                    i27 = i22;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                                if (i27 != 0) {
                                    composer2 = startRestartGroup;
                                    brush3 = new SolidColor(Color.INSTANCE.m4641getBlack0d7_KjU(), null);
                                    i28 = i24;
                                } else {
                                    composer2 = startRestartGroup;
                                    brush3 = brush;
                                    i28 = i24;
                                }
                                Function3 m1288getLambda$665310900$foundation_release = i28 != 0 ? ComposableSingletons$BasicTextFieldKt.INSTANCE.m1288getLambda$665310900$foundation_release() : function3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1742344466, i5, i25, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:978)");
                                }
                                int i34 = i25 << 3;
                                boolean z10 = z8;
                                BasicTextField(textFieldValue2, (Function1<? super TextFieldValue, Unit>) function1, modifier4, z7, z10, textStyle4, keyboardOptions4, keyboardActions4, z9, i33, 1, none, (Function1<? super TextLayoutResult, Unit>) function14, mutableInteractionSource3, brush3, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) m1288getLambda$665310900$foundation_release, composer2, i5 & 2147483646, (i34 & 112) | 6 | (i34 & 896) | (i34 & 7168) | (57344 & i34) | (i34 & 458752), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                z4 = z7;
                                z5 = z10;
                                textStyle3 = textStyle4;
                                keyboardOptions3 = keyboardOptions4;
                                keyboardActions3 = keyboardActions4;
                                z6 = z9;
                                i26 = i33;
                                visualTransformation2 = none;
                                function13 = function14;
                                mutableInteractionSource2 = mutableInteractionSource3;
                                brush2 = brush3;
                                function32 = m1288getLambda$665310900$foundation_release;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda1
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit BasicTextField$lambda$60;
                                        BasicTextField$lambda$60 = BasicTextFieldKt.BasicTextField$lambda$60(TextFieldValue.this, function1, modifier3, z4, z5, textStyle3, keyboardOptions3, keyboardActions3, z6, i26, visualTransformation2, function13, mutableInteractionSource2, brush2, function32, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                        return BasicTextField$lambda$60;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        i14 = i13;
                        i15 = i4 & 1024;
                        if (i15 == 0) {
                        }
                        i18 = i4 & 2048;
                        if (i18 == 0) {
                        }
                        int i292 = i17;
                        i20 = i4 & 4096;
                        if (i20 == 0) {
                        }
                        i22 = i4 & 8192;
                        if (i22 == 0) {
                        }
                        i24 = i4 & 16384;
                        if (i24 == 0) {
                        }
                        if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i25 & 9363) != 9362, i5 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i9 = i4 & 32;
                    if (i9 != 0) {
                    }
                    i10 = i4 & 64;
                    if (i10 != 0) {
                    }
                    i11 = i4 & 128;
                    if (i11 != 0) {
                    }
                    i12 = i4 & 256;
                    if (i12 != 0) {
                    }
                    i13 = i4 & 512;
                    if (i13 != 0) {
                    }
                    i14 = i13;
                    i15 = i4 & 1024;
                    if (i15 == 0) {
                    }
                    i18 = i4 & 2048;
                    if (i18 == 0) {
                    }
                    int i2922 = i17;
                    i20 = i4 & 4096;
                    if (i20 == 0) {
                    }
                    i22 = i4 & 8192;
                    if (i22 == 0) {
                    }
                    i24 = i4 & 16384;
                    if (i24 == 0) {
                    }
                    if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i25 & 9363) != 9362, i5 & 1)) {
                    }
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
                i10 = i4 & 64;
                if (i10 != 0) {
                }
                i11 = i4 & 128;
                if (i11 != 0) {
                }
                i12 = i4 & 256;
                if (i12 != 0) {
                }
                i13 = i4 & 512;
                if (i13 != 0) {
                }
                i14 = i13;
                i15 = i4 & 1024;
                if (i15 == 0) {
                }
                i18 = i4 & 2048;
                if (i18 == 0) {
                }
                int i29222 = i17;
                i20 = i4 & 4096;
                if (i20 == 0) {
                }
                i22 = i4 & 8192;
                if (i22 == 0) {
                }
                i24 = i4 & 16384;
                if (i24 == 0) {
                }
                if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i25 & 9363) != 9362, i5 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            modifier2 = modifier;
            i7 = i4 & 8;
            if (i7 != 0) {
            }
            i8 = i4 & 16;
            if (i8 == 0) {
            }
            i9 = i4 & 32;
            if (i9 != 0) {
            }
            i10 = i4 & 64;
            if (i10 != 0) {
            }
            i11 = i4 & 128;
            if (i11 != 0) {
            }
            i12 = i4 & 256;
            if (i12 != 0) {
            }
            i13 = i4 & 512;
            if (i13 != 0) {
            }
            i14 = i13;
            i15 = i4 & 1024;
            if (i15 == 0) {
            }
            i18 = i4 & 2048;
            if (i18 == 0) {
            }
            int i292222 = i17;
            i20 = i4 & 4096;
            if (i20 == 0) {
            }
            i22 = i4 & 8192;
            if (i22 == 0) {
            }
            i24 = i4 & 16384;
            if (i24 == 0) {
            }
            if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i25 & 9363) != 9362, i5 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        i6 = i4 & 4;
        if (i6 == 0) {
        }
        modifier2 = modifier;
        i7 = i4 & 8;
        if (i7 != 0) {
        }
        i8 = i4 & 16;
        if (i8 == 0) {
        }
        i9 = i4 & 32;
        if (i9 != 0) {
        }
        i10 = i4 & 64;
        if (i10 != 0) {
        }
        i11 = i4 & 128;
        if (i11 != 0) {
        }
        i12 = i4 & 256;
        if (i12 != 0) {
        }
        i13 = i4 & 512;
        if (i13 != 0) {
        }
        i14 = i13;
        i15 = i4 & 1024;
        if (i15 == 0) {
        }
        i18 = i4 & 2048;
        if (i18 == 0) {
        }
        int i2922222 = i17;
        i20 = i4 & 4096;
        if (i20 == 0) {
        }
        i22 = i4 & 8192;
        if (i22 == 0) {
        }
        i24 = i4 & 16384;
        if (i24 == 0) {
        }
        if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i25 & 9363) != 9362, i5 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult BasicTextField$lambda$13$lambda$12(final TextFieldSelectionState textFieldSelectionState, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$lambda$13$lambda$12$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                TextFieldSelectionState.this.dispose();
            }
        };
    }

    private static final TextFieldHandleState TextFieldCursorHandle$lambda$20(State<TextFieldHandleState> state) {
        return state.getValue();
    }

    private static final TextFieldHandleState TextFieldSelectionHandles$lambda$26(State<TextFieldHandleState> state) {
        return state.getValue();
    }

    private static final TextFieldHandleState TextFieldSelectionHandles$lambda$31(State<TextFieldHandleState> state) {
        return state.getValue();
    }
}
