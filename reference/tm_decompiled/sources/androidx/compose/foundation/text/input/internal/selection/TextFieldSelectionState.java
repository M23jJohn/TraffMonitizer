package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.foundation.content.TransferableContent_androidKt;
import androidx.compose.foundation.content.internal.ReceiveContentConfiguration;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.foundation.internal.ClipboardUtils_androidKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldCursor_androidKt;
import androidx.compose.foundation.text.TextLayoutHelperKt;
import androidx.compose.foundation.text.contextmenu.modifier.ToolbarRequester;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldCharSequenceKt;
import androidx.compose.foundation.text.input.internal.IndexTransformationType;
import androidx.compose.foundation.text.input.internal.MathUtilsKt;
import androidx.compose.foundation.text.input.internal.SelectionWedgeAffinity;
import androidx.compose.foundation.text.input.internal.TextLayoutState;
import androidx.compose.foundation.text.input.internal.TextLayoutStateKt;
import androidx.compose.foundation.text.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text.input.internal.WedgeAffinity;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionGesturesKt;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.SelectionLayout;
import androidx.compose.foundation.text.selection.SelectionLayoutKt;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.foundation.text.selection.TextSelectionDelegateKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.platform.ClipEntry;
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: TextFieldSelectionState.kt */
@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0001\u0018\u00002\u00020\u0001:\u0006Ç\u0001È\u0001É\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010j\u001a\u00020k2\u0006\u0010l\u001a\u00020\tH\u0000¢\u0006\u0002\bmJ\b\u0010n\u001a\u00020\tH\u0002J\u0006\u0010o\u001a\u00020pJ>\u0010q\u001a\u00020)2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010r\u001a\u00020!2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tJ\u0012\u0010s\u001a\u00020)*\u00020tH\u0086@¢\u0006\u0002\u0010uJ\u001a\u0010v\u001a\u00020)*\u00020t2\u0006\u0010w\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010xJ\u000e\u0010y\u001a\u00020)H\u0086@¢\u0006\u0002\u0010zJ\u000e\u0010{\u001a\u00020)2\u0006\u0010T\u001a\u00020SJ\u0006\u0010|\u001a\u00020)J\u0012\u0010}\u001a\u00020)*\u00020tH\u0086@¢\u0006\u0002\u0010uJ<\u0010~\u001a\u00020)*\u00020t2\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u00012\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020)0(2\r\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020)0(H\u0086@¢\u0006\u0003\u0010\u0083\u0001J\u001b\u0010\u0084\u0001\u001a\u00020\t2\u0007\u0010\u0085\u0001\u001a\u000202H\u0002¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u0013\u0010\u0088\u0001\u001a\u00020)*\u00020tH\u0082@¢\u0006\u0002\u0010uJ#\u0010\u0089\u0001\u001a\u00020)*\u00020t2\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020)0(H\u0086@¢\u0006\u0003\u0010\u008a\u0001J\u0007\u0010\u008b\u0001\u001a\u00020)J\u001b\u0010\u008c\u0001\u001a\u00020)*\u00020t2\u0006\u0010w\u001a\u00020\tH\u0082@¢\u0006\u0002\u0010xJ\u000f\u0010\u008d\u0001\u001a\u00020)H\u0082@¢\u0006\u0002\u0010zJ\u000f\u0010\u008e\u0001\u001a\u00020)H\u0082@¢\u0006\u0002\u0010zJ\t\u0010\u0094\u0001\u001a\u00020pH\u0002J\u001f\u0010\u0095\u0001\u001a\u00020k2\u0006\u0010w\u001a\u00020\t2\u0006\u0010l\u001a\u00020\tH\u0000¢\u0006\u0003\b\u0096\u0001J\u001a\u0010\u0097\u0001\u001a\u0002022\u0006\u0010w\u001a\u00020\tH\u0002¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001J\"\u0010\u009a\u0001\u001a\u00020)2\u0007\u0010\u009b\u0001\u001a\u00020A2\u0007\u0010\u009c\u0001\u001a\u000202¢\u0006\u0006\b\u009d\u0001\u0010\u009e\u0001J\t\u0010\u009f\u0001\u001a\u00020)H\u0002J\u0007\u0010 \u0001\u001a\u00020)J\u0007\u0010¡\u0001\u001a\u00020\tJ\u000f\u0010¢\u0001\u001a\u00020)H\u0086@¢\u0006\u0002\u0010zJ\u0007\u0010£\u0001\u001a\u00020\tJ\u001b\u0010¤\u0001\u001a\u00020)2\t\b\u0002\u0010¥\u0001\u001a\u00020\tH\u0086@¢\u0006\u0003\u0010¦\u0001J\u000f\u0010©\u0001\u001a\u00020)H\u0086@¢\u0006\u0002\u0010zJ\u0007\u0010ª\u0001\u001a\u00020\tJ\u000f\u0010«\u0001\u001a\u00020)H\u0086@¢\u0006\u0002\u0010zJ\u000f\u0010¬\u0001\u001a\u00020)H\u0082@¢\u0006\u0002\u0010zJ\u0007\u0010\u00ad\u0001\u001a\u00020\tJ\u0007\u0010®\u0001\u001a\u00020)J\u0007\u0010¯\u0001\u001a\u00020\tJ\u0007\u0010°\u0001\u001a\u00020)J\u0018\u0010r\u001a\u00020)2\u0007\u0010±\u0001\u001a\u00020pH\u0082@¢\u0006\u0003\u0010²\u0001J\u0007\u0010³\u0001\u001a\u00020)J\t\u0010´\u0001\u001a\u00020)H\u0002JW\u0010µ\u0001\u001a\u00030¶\u00012\b\u0010·\u0001\u001a\u00030¸\u00012\u0007\u0010¹\u0001\u001a\u00020g2\u0007\u0010º\u0001\u001a\u00020g2\u0006\u0010w\u001a\u00020\t2\b\u0010»\u0001\u001a\u00030¼\u00012\t\b\u0002\u0010½\u0001\u001a\u00020\t2\t\b\u0002\u0010¾\u0001\u001a\u00020\tH\u0002¢\u0006\u0006\b¿\u0001\u0010À\u0001JC\u0010Á\u0001\u001a\u00030¶\u00012\u0007\u0010Â\u0001\u001a\u00020g2\u0007\u0010Ã\u0001\u001a\u00020g2\n\u0010Ä\u0001\u001a\u0005\u0018\u00010¶\u00012\u0006\u0010w\u001a\u00020\t2\b\u0010»\u0001\u001a\u00030¼\u0001H\u0002¢\u0006\u0006\bÅ\u0001\u0010Æ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010#\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010\u001bR\"\u0010'\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010.\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010/\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010+\"\u0004\b1\u0010-R+\u00103\u001a\u0002022\u0006\u0010\"\u001a\u0002028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b8\u0010&\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0014\u00109\u001a\u0002028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b:\u00105R+\u0010;\u001a\u0002022\u0006\u0010\"\u001a\u0002028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b>\u0010&\u001a\u0004\b<\u00105\"\u0004\b=\u00107R\u0011\u0010?\u001a\u0002028F¢\u0006\u0006\u001a\u0004\b@\u00105R/\u0010B\u001a\u0004\u0018\u00010A2\b\u0010\"\u001a\u0004\u0018\u00010A8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bG\u0010&\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR+\u0010I\u001a\u00020H2\u0006\u0010\"\u001a\u00020H8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bN\u0010&\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR+\u0010O\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bR\u0010&\u001a\u0004\bP\u0010\u0019\"\u0004\bQ\u0010\u001bR+\u0010T\u001a\u00020S2\u0006\u0010\"\u001a\u00020S8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bY\u0010&\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR+\u0010Z\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t8F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b]\u0010&\u001a\u0004\b[\u0010\u0019\"\u0004\b\\\u0010\u001bR\u0016\u0010^\u001a\u0004\u0018\u00010_8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u0014\u0010b\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bc\u0010\u0019R\u0010\u0010d\u001a\u0004\u0018\u00010eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020gX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010h\u001a\u0004\u0018\u00010iX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u008f\u0001\u001a\u0004\u0018\u00010p8@X\u0080\u0084\u0002¢\u0006\u0010\n\u0006\b\u0092\u0001\u0010\u0093\u0001\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0010\u0010§\u0001\u001a\u00030¨\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Ê\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "density", "Landroidx/compose/ui/unit/Density;", "enabled", "", "readOnly", "isFocused", "isPassword", "toolbarRequester", "Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "platformSelectionBehaviors", "Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "clipboard", "Landroidx/compose/ui/platform/Clipboard;", "<init>", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/ui/unit/Density;ZZZZLandroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;Landroidx/compose/ui/platform/Clipboard;)V", "getTextFieldState$foundation_release", "()Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "()Z", "setFocused", "(Z)V", "getPlatformSelectionBehaviors$foundation_release", "()Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "textToolbarHandler", "Landroidx/compose/foundation/text/input/internal/selection/TextToolbarHandler;", "<set-?>", "isInTouchMode", "setInTouchMode", "isInTouchMode$delegate", "Landroidx/compose/runtime/MutableState;", "requestAutofillAction", "Lkotlin/Function0;", "", "getRequestAutofillAction", "()Lkotlin/jvm/functions/Function0;", "setRequestAutofillAction", "(Lkotlin/jvm/functions/Function0;)V", "receiveContentConfiguration", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "getReceiveContentConfiguration", "setReceiveContentConfiguration", "Landroidx/compose/ui/geometry/Offset;", "startTextLayoutPositionInWindow", "getStartTextLayoutPositionInWindow-F1C5BW0", "()J", "setStartTextLayoutPositionInWindow-k-4lQ0M", "(J)V", "startTextLayoutPositionInWindow$delegate", "currentTextLayoutPositionInWindow", "getCurrentTextLayoutPositionInWindow-F1C5BW0", "rawHandleDragPosition", "getRawHandleDragPosition-F1C5BW0", "setRawHandleDragPosition-k-4lQ0M", "rawHandleDragPosition$delegate", "handleDragPosition", "getHandleDragPosition-F1C5BW0", "Landroidx/compose/foundation/text/Handle;", "draggingHandle", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle$delegate", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "directDragGestureInitiator", "getDirectDragGestureInitiator", "()Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "setDirectDragGestureInitiator", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;)V", "directDragGestureInitiator$delegate", "showCursorHandle", "getShowCursorHandle", "setShowCursorHandle", "showCursorHandle$delegate", "Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;", "textToolbarState", "getTextToolbarState", "()Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;", "setTextToolbarState", "(Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;)V", "textToolbarState$delegate", "textToolbarShown", "getTextToolbarShown", "setTextToolbarShown$foundation_release", "textToolbarShown$delegate", "textLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getTextLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "editable", "getEditable$foundation_release", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "previousRawDragOffset", "", "pressInteraction", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "getCursorHandleState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState;", "includePosition", "getCursorHandleState$foundation_release", "isCursorHandleInVisibleBounds", "getCursorRect", "Landroidx/compose/ui/geometry/Rect;", "update", "showTextToolbar", "cursorHandleGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectionHandleGestures", "isStartHandle", "(Landroidx/compose/ui/input/pointer/PointerInputScope;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startToolbarAndHandlesVisibilityObserver", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTextToolbarState", "dispose", "detectTouchMode", "detectTextFieldTapGestures", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "requestFocus", "showKeyboard", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "placeCursorAtNearestOffset", "offset", "placeCursorAtNearestOffset-k-4lQ0M", "(J)Z", "detectCursorHandleDragGestures", "textFieldSelectionGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "maybeSuggestSelectionRange", "detectSelectionHandleDragGestures", "observeTextChanges", "observeTextToolbarVisibility", "derivedVisibleContentBounds", "getDerivedVisibleContentBounds$foundation_release", "()Landroidx/compose/ui/geometry/Rect;", "derivedVisibleContentBounds$delegate", "Landroidx/compose/runtime/State;", "getContentRect", "getSelectionHandleState", "getSelectionHandleState$foundation_release", "getHandlePosition", "getHandlePosition-tuRUvjQ", "(Z)J", "updateHandleDragging", "handle", "position", "updateHandleDragging-Uv8p0NA", "(Landroidx/compose/foundation/text/Handle;J)V", "markStartContentVisibleOffset", "clearHandleDragging", "canCut", "cut", "canCopy", "copy", "cancelSelection", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clipboardPasteState", "Landroidx/compose/foundation/text/input/internal/selection/ClipboardPasteState;", "updateClipboardEntry", "canPaste", "paste", "pasteAsPlainText", "canSelectAll", "selectAll", "canAutofill", "autofill", "contentRect", "(Landroidx/compose/ui/geometry/Rect;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deselect", "hideTextToolbar", "updateSelection", "Landroidx/compose/ui/text/TextRange;", "textFieldCharSequence", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "startOffset", "endOffset", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "allowPreviousSelectionCollapsed", "isStartOfSelection", "updateSelection-SsL-Rf8", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;IIZLandroidx/compose/foundation/text/selection/SelectionAdjustment;ZZ)J", "getTextFieldSelection", "rawStartOffset", "rawEndOffset", "previousSelection", "getTextFieldSelection-qeG_v_k", "(IILandroidx/compose/ui/text/TextRange;ZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)J", "InputType", "TextFieldMouseSelectionObserver", "TextFieldTextDragObserver", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldSelectionState {
    public static final int $stable = 8;
    private Clipboard clipboard;
    private ClipboardPasteState clipboardPasteState;
    private final CoroutineScope coroutineScope;
    private Density density;
    private boolean enabled;
    private HapticFeedback hapticFeedBack;
    private boolean isFocused;
    private boolean isPassword;
    private final PlatformSelectionBehaviors platformSelectionBehaviors;
    private PressInteraction.Press pressInteraction;
    private SelectionLayout previousSelectionLayout;
    private boolean readOnly;
    private Function0<? extends ReceiveContentConfiguration> receiveContentConfiguration;
    private Function0<Unit> requestAutofillAction;
    private final TransformedTextFieldState textFieldState;
    private final TextLayoutState textLayoutState;
    private TextToolbarHandler textToolbarHandler;
    private final ToolbarRequester toolbarRequester;

    /* renamed from: isInTouchMode$delegate, reason: from kotlin metadata */
    private final MutableState isInTouchMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);

    /* renamed from: startTextLayoutPositionInWindow$delegate, reason: from kotlin metadata */
    private final MutableState startTextLayoutPositionInWindow = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m4332boximpl(Offset.INSTANCE.m4358getUnspecifiedF1C5BW0()), null, 2, null);

    /* renamed from: rawHandleDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState rawHandleDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m4332boximpl(Offset.INSTANCE.m4358getUnspecifiedF1C5BW0()), null, 2, null);

    /* renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* renamed from: directDragGestureInitiator$delegate, reason: from kotlin metadata */
    private final MutableState directDragGestureInitiator = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(InputType.None, null, 2, null);

    /* renamed from: showCursorHandle$delegate, reason: from kotlin metadata */
    private final MutableState showCursorHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* renamed from: textToolbarState$delegate, reason: from kotlin metadata */
    private final MutableState textToolbarState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TextToolbarState.None, null, 2, null);

    /* renamed from: textToolbarShown$delegate, reason: from kotlin metadata */
    private final MutableState textToolbarShown = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
    private int previousRawDragOffset = -1;

    /* renamed from: derivedVisibleContentBounds$delegate, reason: from kotlin metadata */
    private final State derivedVisibleContentBounds = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Rect derivedVisibleContentBounds_delegate$lambda$22;
            derivedVisibleContentBounds_delegate$lambda$22 = TextFieldSelectionState.derivedVisibleContentBounds_delegate$lambda$22(TextFieldSelectionState.this);
            return derivedVisibleContentBounds_delegate$lambda$22;
        }
    });

    /* compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IndexTransformationType.values().length];
            try {
                iArr[IndexTransformationType.Untransformed.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[IndexTransformationType.Deletion.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[IndexTransformationType.Insertion.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[IndexTransformationType.Replacement.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean observeTextToolbarVisibility$lambda$21$lambda$20(Rect rect) {
        return rect == null;
    }

    public TextFieldSelectionState(TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, Density density, boolean z, boolean z2, boolean z3, boolean z4, ToolbarRequester toolbarRequester, CoroutineScope coroutineScope, PlatformSelectionBehaviors platformSelectionBehaviors, Clipboard clipboard) {
        this.textFieldState = transformedTextFieldState;
        this.textLayoutState = textLayoutState;
        this.density = density;
        this.enabled = z;
        this.readOnly = z2;
        this.isFocused = z3;
        this.isPassword = z4;
        this.toolbarRequester = toolbarRequester;
        this.coroutineScope = coroutineScope;
        this.platformSelectionBehaviors = platformSelectionBehaviors;
        this.clipboard = clipboard;
        this.clipboardPasteState = new ClipboardPasteState(this.clipboard);
    }

    /* renamed from: getTextFieldState$foundation_release, reason: from getter */
    public final TransformedTextFieldState getTextFieldState() {
        return this.textFieldState;
    }

    /* renamed from: isFocused, reason: from getter */
    public final boolean getIsFocused() {
        return this.isFocused;
    }

    public final void setFocused(boolean z) {
        this.isFocused = z;
    }

    /* renamed from: getPlatformSelectionBehaviors$foundation_release, reason: from getter */
    public final PlatformSelectionBehaviors getPlatformSelectionBehaviors() {
        return this.platformSelectionBehaviors;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isInTouchMode() {
        return ((Boolean) this.isInTouchMode.getValue()).booleanValue();
    }

    public final void setInTouchMode(boolean z) {
        this.isInTouchMode.setValue(Boolean.valueOf(z));
    }

    public final Function0<Unit> getRequestAutofillAction() {
        return this.requestAutofillAction;
    }

    public final void setRequestAutofillAction(Function0<Unit> function0) {
        this.requestAutofillAction = function0;
    }

    public final Function0<ReceiveContentConfiguration> getReceiveContentConfiguration() {
        return this.receiveContentConfiguration;
    }

    public final void setReceiveContentConfiguration(Function0<? extends ReceiveContentConfiguration> function0) {
        this.receiveContentConfiguration = function0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getStartTextLayoutPositionInWindow-F1C5BW0, reason: not valid java name */
    private final long m1682getStartTextLayoutPositionInWindowF1C5BW0() {
        return ((Offset) this.startTextLayoutPositionInWindow.getValue()).m4353unboximpl();
    }

    /* renamed from: setStartTextLayoutPositionInWindow-k-4lQ0M, reason: not valid java name */
    private final void m1686setStartTextLayoutPositionInWindowk4lQ0M(long j) {
        this.startTextLayoutPositionInWindow.setValue(Offset.m4332boximpl(j));
    }

    /* renamed from: getCurrentTextLayoutPositionInWindow-F1C5BW0, reason: not valid java name */
    private final long m1679getCurrentTextLayoutPositionInWindowF1C5BW0() {
        LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
        return textLayoutCoordinates != null ? LayoutCoordinatesKt.positionInWindow(textLayoutCoordinates) : Offset.INSTANCE.m4358getUnspecifiedF1C5BW0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getRawHandleDragPosition-F1C5BW0, reason: not valid java name */
    private final long m1681getRawHandleDragPositionF1C5BW0() {
        return ((Offset) this.rawHandleDragPosition.getValue()).m4353unboximpl();
    }

    /* renamed from: setRawHandleDragPosition-k-4lQ0M, reason: not valid java name */
    private final void m1685setRawHandleDragPositionk4lQ0M(long j) {
        this.rawHandleDragPosition.setValue(Offset.m4332boximpl(j));
    }

    /* renamed from: getHandleDragPosition-F1C5BW0, reason: not valid java name */
    public final long m1689getHandleDragPositionF1C5BW0() {
        if ((m1681getRawHandleDragPositionF1C5BW0() & 9223372034707292159L) == InlineClassHelperKt.UnspecifiedPackedFloats) {
            return Offset.INSTANCE.m4358getUnspecifiedF1C5BW0();
        }
        if ((m1682getStartTextLayoutPositionInWindowF1C5BW0() & 9223372034707292159L) == InlineClassHelperKt.UnspecifiedPackedFloats) {
            return TextLayoutStateKt.m1624fromDecorationToTextLayoutUv8p0NA(this.textLayoutState, m1681getRawHandleDragPositionF1C5BW0());
        }
        return Offset.m4348plusMKHz9U(m1681getRawHandleDragPositionF1C5BW0(), Offset.m4347minusMKHz9U(m1682getStartTextLayoutPositionInWindowF1C5BW0(), m1679getCurrentTextLayoutPositionInWindowF1C5BW0()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Handle getDraggingHandle() {
        return (Handle) this.draggingHandle.getValue();
    }

    public final void setDraggingHandle(Handle handle) {
        this.draggingHandle.setValue(handle);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "", "<init>", "(Ljava/lang/String;I)V", "None", "Touch", "Mouse", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class InputType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ InputType[] $VALUES;
        public static final InputType None = new InputType("None", 0);
        public static final InputType Touch = new InputType("Touch", 1);
        public static final InputType Mouse = new InputType("Mouse", 2);

        private static final /* synthetic */ InputType[] $values() {
            return new InputType[]{None, Touch, Mouse};
        }

        public static EnumEntries<InputType> getEntries() {
            return $ENTRIES;
        }

        private InputType(String str, int i) {
        }

        static {
            InputType[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public static InputType valueOf(String str) {
            return (InputType) Enum.valueOf(InputType.class, str);
        }

        public static InputType[] values() {
            return (InputType[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final InputType getDirectDragGestureInitiator() {
        return (InputType) this.directDragGestureInitiator.getValue();
    }

    public final void setDirectDragGestureInitiator(InputType inputType) {
        this.directDragGestureInitiator.setValue(inputType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean getShowCursorHandle() {
        return ((Boolean) this.showCursorHandle.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setShowCursorHandle(boolean z) {
        this.showCursorHandle.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final TextToolbarState getTextToolbarState() {
        return (TextToolbarState) this.textToolbarState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextToolbarState(TextToolbarState textToolbarState) {
        this.textToolbarState.setValue(textToolbarState);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getTextToolbarShown() {
        return ((Boolean) this.textToolbarShown.getValue()).booleanValue();
    }

    public final void setTextToolbarShown$foundation_release(boolean z) {
        this.textToolbarShown.setValue(Boolean.valueOf(z));
    }

    private final LayoutCoordinates getTextLayoutCoordinates() {
        LayoutCoordinates textLayoutNodeCoordinates = this.textLayoutState.getTextLayoutNodeCoordinates();
        if (textLayoutNodeCoordinates == null || !textLayoutNodeCoordinates.isAttached()) {
            return null;
        }
        return textLayoutNodeCoordinates;
    }

    public final boolean getEditable$foundation_release() {
        return this.enabled && !this.readOnly;
    }

    public final TextFieldHandleState getCursorHandleState$foundation_release(boolean includePosition) {
        TextFieldCharSequence visualText = this.textFieldState.getVisualText();
        boolean showCursorHandle = getShowCursorHandle();
        boolean z = getDirectDragGestureInitiator() == InputType.None;
        Handle draggingHandle = getDraggingHandle();
        if (!showCursorHandle || !z || !TextRange.m6842getCollapsedimpl(visualText.getSelection()) || !visualText.shouldShowSelection() || visualText.length() <= 0 || (draggingHandle != Handle.Cursor && !isCursorHandleInVisibleBounds())) {
            return TextFieldHandleState.INSTANCE.getHidden();
        }
        return new TextFieldHandleState(true, includePosition ? getCursorRect().m4370getBottomCenterF1C5BW0() : Offset.INSTANCE.m4358getUnspecifiedF1C5BW0(), 0.0f, ResolvedTextDirection.Ltr, false, null);
    }

    private final boolean isCursorHandleInVisibleBounds() {
        Rect visibleBounds;
        Snapshot.Companion companion = Snapshot.INSTANCE;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            long m4370getBottomCenterF1C5BW0 = getCursorRect().m4370getBottomCenterF1C5BW0();
            companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
            LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
            if (textLayoutCoordinates == null || (visibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates)) == null) {
                return false;
            }
            return SelectionManagerKt.m1837containsInclusiveUv8p0NA(visibleBounds, m4370getBottomCenterF1C5BW0);
        } catch (Throwable th) {
            companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
            throw th;
        }
    }

    public final Rect getCursorRect() {
        float right;
        float rint;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Rect.INSTANCE.getZero();
        }
        TextFieldCharSequence visualText = this.textFieldState.getVisualText();
        if (!TextRange.m6842getCollapsedimpl(visualText.getSelection())) {
            return Rect.INSTANCE.getZero();
        }
        Rect cursorRect = layoutResult.getCursorRect(TextRange.m6848getStartimpl(visualText.getSelection()));
        float coerceAtLeast = RangesKt.coerceAtLeast((float) Math.floor(this.density.mo459toPx0680j_4(TextFieldCursor_androidKt.getDefaultCursorThickness())), 1.0f);
        if (layoutResult.getLayoutInput().getLayoutDirection() == LayoutDirection.Ltr) {
            right = cursorRect.getLeft() + (coerceAtLeast / 2);
        } else {
            right = cursorRect.getRight() - (coerceAtLeast / 2);
        }
        float f = coerceAtLeast / 2;
        float coerceAtLeast2 = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(right, ((int) (layoutResult.getSize() >> 32)) - f), f);
        if (((int) coerceAtLeast) % 2 == 1) {
            rint = ((float) Math.floor(coerceAtLeast2)) + 0.5f;
        } else {
            rint = (float) Math.rint(coerceAtLeast2);
        }
        return new Rect(rint - f, cursorRect.getTop(), rint + f, cursorRect.getBottom());
    }

    public final void update(HapticFeedback hapticFeedBack, Clipboard clipboard, TextToolbarHandler showTextToolbar, Density density, boolean enabled, boolean readOnly, boolean isPassword) {
        if (!enabled) {
            hideTextToolbar();
        }
        this.hapticFeedBack = hapticFeedBack;
        this.clipboard = clipboard;
        this.textToolbarHandler = showTextToolbar;
        this.density = density;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.isPassword = isPassword;
    }

    public final Object cursorHandleGestures(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new TextFieldSelectionState$cursorHandleGestures$2(this, pointerInputScope, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    public final Object selectionHandleGestures(PointerInputScope pointerInputScope, boolean z, Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new TextFieldSelectionState$selectionHandleGestures$2(this, pointerInputScope, z, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0056 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object startToolbarAndHandlesVisibilityObserver(Continuation<? super Unit> continuation) {
        TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1 textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1;
        int i;
        try {
            if (continuation instanceof TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1) {
                textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1 = (TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1) continuation;
                if ((textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1.label & Integer.MIN_VALUE) != 0) {
                    textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1.label -= Integer.MIN_VALUE;
                    Object obj = textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1.label;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2 textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2 = new TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2(this, null);
                        textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1.label = 1;
                        obj = CoroutineScopeKt.coroutineScope(textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2, textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1);
                        if (obj == coroutine_suspended) {
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
            if (i != 0) {
            }
            return Unit.INSTANCE;
        } finally {
            setShowCursorHandle(false);
            if (getTextToolbarState() != TextToolbarState.None) {
                hideTextToolbar();
            }
        }
        textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1 = new TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1(this, continuation);
        Object obj2 = textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = textFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1.label;
    }

    public final void updateTextToolbarState(TextToolbarState textToolbarState) {
        setTextToolbarState(textToolbarState);
    }

    public final void dispose() {
        hideTextToolbar();
        this.hapticFeedBack = null;
    }

    public final Object detectTouchMode(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        Object awaitPointerEventScope = pointerInputScope.awaitPointerEventScope(new TextFieldSelectionState$detectTouchMode$2(this, null), continuation);
        return awaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitPointerEventScope : Unit.INSTANCE;
    }

    public final Object detectTextFieldTapGestures(PointerInputScope pointerInputScope, MutableInteractionSource mutableInteractionSource, final Function0<Unit> function0, final Function0<Unit> function02, Continuation<? super Unit> continuation) {
        Object detectTapAndPress = TapGestureDetectorKt.detectTapAndPress(pointerInputScope, new TextFieldSelectionState$detectTextFieldTapGestures$2(mutableInteractionSource, this, null), new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit detectTextFieldTapGestures$lambda$5;
                detectTextFieldTapGestures$lambda$5 = TextFieldSelectionState.detectTextFieldTapGestures$lambda$5(Function0.this, this, function02, (Offset) obj);
                return detectTextFieldTapGestures$lambda$5;
            }
        }, continuation);
        return detectTapAndPress == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? detectTapAndPress : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectTextFieldTapGestures$lambda$5(Function0 function0, TextFieldSelectionState textFieldSelectionState, Function0 function02, Offset offset) {
        TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String detectTextFieldTapGestures$lambda$5$lambda$4;
                detectTextFieldTapGestures$lambda$5$lambda$4 = TextFieldSelectionState.detectTextFieldTapGestures$lambda$5$lambda$4();
                return detectTextFieldTapGestures$lambda$5$lambda$4;
            }
        });
        function0.invoke();
        if (textFieldSelectionState.enabled && textFieldSelectionState.isFocused) {
            if (!textFieldSelectionState.readOnly) {
                function02.invoke();
                if (textFieldSelectionState.textFieldState.getVisualText().length() > 0) {
                    textFieldSelectionState.setShowCursorHandle(true);
                }
            }
            textFieldSelectionState.updateTextToolbarState(TextToolbarState.None);
            textFieldSelectionState.m1684placeCursorAtNearestOffsetk4lQ0M(TextLayoutStateKt.m1624fromDecorationToTextLayoutUv8p0NA(textFieldSelectionState.textLayoutState, textFieldSelectionState.textLayoutState.m1617coercedInVisibleBoundsOfInputTextMKHz9U$foundation_release(offset.m4353unboximpl())));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String detectTextFieldTapGestures$lambda$5$lambda$4() {
        return "onTapTextField";
    }

    /* renamed from: placeCursorAtNearestOffset-k-4lQ0M, reason: not valid java name */
    private final boolean m1684placeCursorAtNearestOffsetk4lQ0M(long offset) {
        int m6818getOffsetForPositionk4lQ0M;
        IndexTransformationType indexTransformationType;
        int m6848getStartimpl;
        SelectionWedgeAffinity selectionWedgeAffinity;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null || (m6818getOffsetForPositionk4lQ0M = layoutResult.m6818getOffsetForPositionk4lQ0M(offset)) == -1) {
            return false;
        }
        TransformedTextFieldState transformedTextFieldState = this.textFieldState;
        long m1633mapFromTransformedjx7JFs = transformedTextFieldState.m1633mapFromTransformedjx7JFs(m6818getOffsetForPositionk4lQ0M);
        long m1636mapToTransformedGEjPoXI = transformedTextFieldState.m1636mapToTransformedGEjPoXI(m1633mapFromTransformedjx7JFs);
        if (TextRange.m6842getCollapsedimpl(m1633mapFromTransformedjx7JFs) && TextRange.m6842getCollapsedimpl(m1636mapToTransformedGEjPoXI)) {
            indexTransformationType = IndexTransformationType.Untransformed;
        } else if (!TextRange.m6842getCollapsedimpl(m1633mapFromTransformedjx7JFs) && !TextRange.m6842getCollapsedimpl(m1636mapToTransformedGEjPoXI)) {
            indexTransformationType = IndexTransformationType.Replacement;
        } else if (TextRange.m6842getCollapsedimpl(m1633mapFromTransformedjx7JFs) && !TextRange.m6842getCollapsedimpl(m1636mapToTransformedGEjPoXI)) {
            indexTransformationType = IndexTransformationType.Insertion;
        } else {
            indexTransformationType = IndexTransformationType.Deletion;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[indexTransformationType.ordinal()];
        SelectionWedgeAffinity selectionWedgeAffinity2 = null;
        if (i == 1) {
            m6848getStartimpl = TextRange.m6848getStartimpl(m1633mapFromTransformedjx7JFs);
        } else if (i == 2) {
            m6848getStartimpl = TextRange.m6848getStartimpl(m1633mapFromTransformedjx7JFs);
        } else if (i == 3) {
            if (MathUtilsKt.m1566findClosestRect9KIMszo(offset, layoutResult.getCursorRect(TextRange.m6848getStartimpl(m1636mapToTransformedGEjPoXI)), layoutResult.getCursorRect(TextRange.m6843getEndimpl(m1636mapToTransformedGEjPoXI))) < 0) {
                selectionWedgeAffinity = new SelectionWedgeAffinity(WedgeAffinity.Start);
            } else {
                selectionWedgeAffinity = new SelectionWedgeAffinity(WedgeAffinity.End);
            }
            selectionWedgeAffinity2 = selectionWedgeAffinity;
            m6848getStartimpl = TextRange.m6848getStartimpl(m1633mapFromTransformedjx7JFs);
        } else {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            if (MathUtilsKt.m1566findClosestRect9KIMszo(offset, layoutResult.getCursorRect(TextRange.m6848getStartimpl(m1636mapToTransformedGEjPoXI)), layoutResult.getCursorRect(TextRange.m6843getEndimpl(m1636mapToTransformedGEjPoXI))) < 0) {
                m6848getStartimpl = TextRange.m6848getStartimpl(m1633mapFromTransformedjx7JFs);
            } else {
                m6848getStartimpl = TextRange.m6843getEndimpl(m1633mapFromTransformedjx7JFs);
            }
        }
        long TextRange = TextRangeKt.TextRange(m6848getStartimpl);
        if (TextRange.m6841equalsimpl0(TextRange, this.textFieldState.getUntransformedText().getSelection()) && (selectionWedgeAffinity2 == null || Intrinsics.areEqual(selectionWedgeAffinity2, this.textFieldState.getSelectionWedgeAffinity()))) {
            return false;
        }
        this.textFieldState.m1639selectUntransformedCharsIn5zctL8(TextRange);
        if (selectionWedgeAffinity2 != null) {
            this.textFieldState.setSelectionWedgeAffinity(selectionWedgeAffinity2);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object detectCursorHandleDragGestures(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        TextFieldSelectionState$detectCursorHandleDragGestures$1 textFieldSelectionState$detectCursorHandleDragGestures$1;
        int i;
        Ref.LongRef longRef;
        Throwable th;
        Ref.LongRef longRef2;
        if (continuation instanceof TextFieldSelectionState$detectCursorHandleDragGestures$1) {
            textFieldSelectionState$detectCursorHandleDragGestures$1 = (TextFieldSelectionState$detectCursorHandleDragGestures$1) continuation;
            if ((textFieldSelectionState$detectCursorHandleDragGestures$1.label & Integer.MIN_VALUE) != 0) {
                textFieldSelectionState$detectCursorHandleDragGestures$1.label -= Integer.MIN_VALUE;
                TextFieldSelectionState$detectCursorHandleDragGestures$1 textFieldSelectionState$detectCursorHandleDragGestures$12 = textFieldSelectionState$detectCursorHandleDragGestures$1;
                Object obj = textFieldSelectionState$detectCursorHandleDragGestures$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = textFieldSelectionState$detectCursorHandleDragGestures$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.LongRef longRef3 = new Ref.LongRef();
                    longRef3.element = Offset.INSTANCE.m4358getUnspecifiedF1C5BW0();
                    final Ref.LongRef longRef4 = new Ref.LongRef();
                    longRef4.element = Offset.INSTANCE.m4358getUnspecifiedF1C5BW0();
                    try {
                        Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda10
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                Unit detectCursorHandleDragGestures$lambda$8;
                                detectCursorHandleDragGestures$lambda$8 = TextFieldSelectionState.detectCursorHandleDragGestures$lambda$8(Ref.LongRef.this, this, longRef4, (Offset) obj2);
                                return detectCursorHandleDragGestures$lambda$8;
                            }
                        };
                        Function0 function0 = new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda11
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                Unit detectCursorHandleDragGestures$lambda$9;
                                detectCursorHandleDragGestures$lambda$9 = TextFieldSelectionState.detectCursorHandleDragGestures$lambda$9(Ref.LongRef.this, longRef4, this);
                                return detectCursorHandleDragGestures$lambda$9;
                            }
                        };
                        Function0 function02 = new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda12
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                Unit detectCursorHandleDragGestures$lambda$10;
                                detectCursorHandleDragGestures$lambda$10 = TextFieldSelectionState.detectCursorHandleDragGestures$lambda$10(Ref.LongRef.this, longRef4, this);
                                return detectCursorHandleDragGestures$lambda$10;
                            }
                        };
                        Function2 function2 = new Function2() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda13
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj2, Object obj3) {
                                Unit detectCursorHandleDragGestures$lambda$11;
                                detectCursorHandleDragGestures$lambda$11 = TextFieldSelectionState.detectCursorHandleDragGestures$lambda$11(Ref.LongRef.this, this, longRef3, (PointerInputChange) obj2, (Offset) obj3);
                                return detectCursorHandleDragGestures$lambda$11;
                            }
                        };
                        textFieldSelectionState$detectCursorHandleDragGestures$12.L$0 = longRef3;
                        textFieldSelectionState$detectCursorHandleDragGestures$12.L$1 = longRef4;
                        textFieldSelectionState$detectCursorHandleDragGestures$12.label = 1;
                        if (DragGestureDetectorKt.detectDragGestures(pointerInputScope, function1, function0, function02, function2, textFieldSelectionState$detectCursorHandleDragGestures$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        longRef = longRef3;
                        longRef2 = longRef4;
                    } catch (Throwable th2) {
                        longRef = longRef3;
                        th = th2;
                        longRef2 = longRef4;
                        detectCursorHandleDragGestures$onDragStop(longRef, longRef2, this);
                        throw th;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    longRef2 = (Ref.LongRef) textFieldSelectionState$detectCursorHandleDragGestures$12.L$1;
                    longRef = (Ref.LongRef) textFieldSelectionState$detectCursorHandleDragGestures$12.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th3) {
                        th = th3;
                        detectCursorHandleDragGestures$onDragStop(longRef, longRef2, this);
                        throw th;
                    }
                }
                detectCursorHandleDragGestures$onDragStop(longRef, longRef2, this);
                return Unit.INSTANCE;
            }
        }
        textFieldSelectionState$detectCursorHandleDragGestures$1 = new TextFieldSelectionState$detectCursorHandleDragGestures$1(this, continuation);
        TextFieldSelectionState$detectCursorHandleDragGestures$1 textFieldSelectionState$detectCursorHandleDragGestures$122 = textFieldSelectionState$detectCursorHandleDragGestures$1;
        Object obj2 = textFieldSelectionState$detectCursorHandleDragGestures$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = textFieldSelectionState$detectCursorHandleDragGestures$122.label;
        if (i != 0) {
        }
        detectCursorHandleDragGestures$onDragStop(longRef, longRef2, this);
        return Unit.INSTANCE;
    }

    private static final void detectCursorHandleDragGestures$onDragStop(Ref.LongRef longRef, Ref.LongRef longRef2, TextFieldSelectionState textFieldSelectionState) {
        if ((longRef.element & 9223372034707292159L) != InlineClassHelperKt.UnspecifiedPackedFloats) {
            longRef.element = Offset.INSTANCE.m4358getUnspecifiedF1C5BW0();
            longRef2.element = Offset.INSTANCE.m4358getUnspecifiedF1C5BW0();
            textFieldSelectionState.clearHandleDragging();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectCursorHandleDragGestures$lambda$8(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Ref.LongRef longRef2, Offset offset) {
        longRef.element = SelectionHandlesKt.m1802getAdjustedCoordinatesk4lQ0M(textFieldSelectionState.getCursorRect().m4370getBottomCenterF1C5BW0());
        longRef2.element = Offset.INSTANCE.m4359getZeroF1C5BW0();
        textFieldSelectionState.setInTouchMode(true);
        textFieldSelectionState.markStartContentVisibleOffset();
        textFieldSelectionState.m1690updateHandleDraggingUv8p0NA(Handle.Cursor, longRef.element);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectCursorHandleDragGestures$lambda$9(Ref.LongRef longRef, Ref.LongRef longRef2, TextFieldSelectionState textFieldSelectionState) {
        detectCursorHandleDragGestures$onDragStop(longRef, longRef2, textFieldSelectionState);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectCursorHandleDragGestures$lambda$10(Ref.LongRef longRef, Ref.LongRef longRef2, TextFieldSelectionState textFieldSelectionState) {
        detectCursorHandleDragGestures$onDragStop(longRef, longRef2, textFieldSelectionState);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectCursorHandleDragGestures$lambda$11(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Ref.LongRef longRef2, PointerInputChange pointerInputChange, Offset offset) {
        longRef.element = Offset.m4348plusMKHz9U(longRef.element, offset.m4353unboximpl());
        textFieldSelectionState.m1690updateHandleDraggingUv8p0NA(Handle.Cursor, Offset.m4348plusMKHz9U(longRef2.element, longRef.element));
        if (textFieldSelectionState.m1684placeCursorAtNearestOffsetk4lQ0M(textFieldSelectionState.m1689getHandleDragPositionF1C5BW0())) {
            pointerInputChange.consume();
            HapticFeedback hapticFeedback = textFieldSelectionState.hapticFeedBack;
            if (hapticFeedback != null) {
                hapticFeedback.mo5404performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m5421getTextHandleMove5zf0vsI());
            }
        }
        return Unit.INSTANCE;
    }

    public final Object textFieldSelectionGestures(PointerInputScope pointerInputScope, Function0<Unit> function0, Continuation<? super Unit> continuation) {
        Object selectionGesturePointerInputBtf2 = SelectionGesturesKt.selectionGesturePointerInputBtf2(pointerInputScope, new TextFieldMouseSelectionObserver(function0), new TextFieldTextDragObserver(function0), continuation);
        return selectionGesturePointerInputBtf2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? selectionGesturePointerInputBtf2 : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J/\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\rH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010 \u001a\u00020\u0004H\u0016J\u0017\u0010!\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\nH\u0016¢\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\nH\u0016¢\u0006\u0004\b%\u0010#R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$TextFieldMouseSelectionObserver;", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "requestFocus", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/jvm/functions/Function0;)V", "dragBeginOffsetInText", "", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "isDoubleOrTripleClickOnly", "", "onStart", "downPosition", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "clickCount", "onStart-9KIMszo", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;I)Z", "onDrag", "dragPosition", "onDrag-3MmeM6k", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "updateSelection", "Landroidx/compose/ui/text/TextRange;", "layoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "isStartOfSelection", "updateSelection-12glfjA", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;Landroidx/compose/ui/text/TextLayoutResult;Z)J", "onDragDone", "onExtend", "onExtend-k-4lQ0M", "(J)Z", "onExtendDrag", "onExtendDrag-k-4lQ0M", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    final class TextFieldMouseSelectionObserver implements MouseSelectionObserver {
        private int dragBeginOffsetInText = -1;
        private long dragBeginPosition = Offset.INSTANCE.m4358getUnspecifiedF1C5BW0();
        private boolean isDoubleOrTripleClickOnly = true;
        private final Function0<Unit> requestFocus;

        public TextFieldMouseSelectionObserver(Function0<Unit> function0) {
            this.requestFocus = function0;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* renamed from: onStart-9KIMszo, reason: not valid java name */
        public boolean mo1696onStart9KIMszo(long downPosition, SelectionAdjustment adjustment, int clickCount) {
            TextLayoutResult layoutResult = TextFieldSelectionState.this.textLayoutState.getLayoutResult();
            if (!TextFieldSelectionState.this.enabled || layoutResult == null || TextFieldSelectionState.this.getTextFieldState().getVisualText().length() == 0) {
                return false;
            }
            this.isDoubleOrTripleClickOnly = clickCount >= 2;
            TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    String onStart_9KIMszo$lambda$0;
                    onStart_9KIMszo$lambda$0 = TextFieldSelectionState.TextFieldMouseSelectionObserver.onStart_9KIMszo$lambda$0();
                    return onStart_9KIMszo$lambda$0;
                }
            });
            TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.Mouse);
            this.requestFocus.invoke();
            TextFieldSelectionState.this.previousRawDragOffset = -1;
            this.dragBeginOffsetInText = -1;
            this.dragBeginPosition = downPosition;
            this.dragBeginOffsetInText = TextRange.m6848getStartimpl(m1692updateSelection12glfjA(downPosition, adjustment, layoutResult, true));
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onStart_9KIMszo$lambda$0() {
            return "Mouse.onStart";
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* renamed from: onDrag-3MmeM6k, reason: not valid java name */
        public boolean mo1693onDrag3MmeM6k(final long dragPosition, SelectionAdjustment adjustment) {
            TextLayoutResult layoutResult = TextFieldSelectionState.this.textLayoutState.getLayoutResult();
            if (!TextFieldSelectionState.this.enabled || layoutResult == null || TextFieldSelectionState.this.getTextFieldState().getVisualText().length() == 0) {
                return false;
            }
            TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    String onDrag_3MmeM6k$lambda$1;
                    onDrag_3MmeM6k$lambda$1 = TextFieldSelectionState.TextFieldMouseSelectionObserver.onDrag_3MmeM6k$lambda$1(dragPosition);
                    return onDrag_3MmeM6k$lambda$1;
                }
            });
            if (TextRange.m6841equalsimpl0(TextFieldSelectionState.this.getTextFieldState().getVisualText().getSelection(), m1692updateSelection12glfjA(dragPosition, adjustment, layoutResult, false))) {
                return true;
            }
            this.isDoubleOrTripleClickOnly = false;
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onDrag_3MmeM6k$lambda$1(long j) {
            return "Mouse.onDrag " + ((Object) Offset.m4351toStringimpl(j));
        }

        /* renamed from: updateSelection-12glfjA, reason: not valid java name */
        private final long m1692updateSelection12glfjA(long dragPosition, SelectionAdjustment adjustment, TextLayoutResult layoutResult, boolean isStartOfSelection) {
            int length = layoutResult.getLayoutInput().getText().length();
            int i = this.dragBeginOffsetInText;
            if (i < 0 || i > length) {
                i = TextFieldSelectionState.this.textLayoutState.m1619getOffsetForPosition3MmeM6k(this.dragBeginPosition, false);
            }
            int m1619getOffsetForPosition3MmeM6k = TextFieldSelectionState.this.textLayoutState.m1619getOffsetForPosition3MmeM6k(dragPosition, false);
            TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
            long m1687updateSelectionSsLRf8 = textFieldSelectionState.m1687updateSelectionSsLRf8(textFieldSelectionState.getTextFieldState().getVisualText(), i, m1619getOffsetForPosition3MmeM6k, false, adjustment, false, isStartOfSelection);
            if (this.dragBeginOffsetInText == -1 && !TextRange.m6842getCollapsedimpl(m1687updateSelectionSsLRf8)) {
                this.dragBeginOffsetInText = TextRange.m6848getStartimpl(m1687updateSelectionSsLRf8);
            }
            if (TextRange.m6847getReversedimpl(m1687updateSelectionSsLRf8)) {
                m1687updateSelectionSsLRf8 = TextFieldSelectionStateKt.m1701reverse5zctL8(m1687updateSelectionSsLRf8);
            }
            TextFieldSelectionState.this.getTextFieldState().m1638selectCharsIn5zctL8(m1687updateSelectionSsLRf8);
            TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
            return m1687updateSelectionSsLRf8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onDragDone$lambda$2() {
            return "Mouse.onDragDone";
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        public void onDragDone() {
            TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    String onDragDone$lambda$2;
                    onDragDone$lambda$2 = TextFieldSelectionState.TextFieldMouseSelectionObserver.onDragDone$lambda$2();
                    return onDragDone$lambda$2;
                }
            });
            TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.None);
            if (this.isDoubleOrTripleClickOnly) {
                TextFieldSelectionState.this.maybeSuggestSelectionRange();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onExtend_k_4lQ0M$lambda$3() {
            return "Mouse.onExtend";
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* renamed from: onExtend-k-4lQ0M, reason: not valid java name */
        public boolean mo1694onExtendk4lQ0M(long downPosition) {
            TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    String onExtend_k_4lQ0M$lambda$3;
                    onExtend_k_4lQ0M$lambda$3 = TextFieldSelectionState.TextFieldMouseSelectionObserver.onExtend_k_4lQ0M$lambda$3();
                    return onExtend_k_4lQ0M$lambda$3;
                }
            });
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onExtendDrag_k_4lQ0M$lambda$4() {
            return "Mouse.onExtendDrag";
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* renamed from: onExtendDrag-k-4lQ0M, reason: not valid java name */
        public boolean mo1695onExtendDragk4lQ0M(long dragPosition) {
            TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldMouseSelectionObserver$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    String onExtendDrag_k_4lQ0M$lambda$4;
                    onExtendDrag_k_4lQ0M$lambda$4 = TextFieldSelectionState.TextFieldMouseSelectionObserver.onExtendDrag_k_4lQ0M$lambda$4();
                    return onExtendDrag_k_4lQ0M$lambda$4;
                }
            });
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0011\u001a\u00020\u0004H\u0002J\u0017\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0004H\u0016J\b\u0010\u0017\u001a\u00020\u0004H\u0016J\b\u0010\u0018\u001a\u00020\u0004H\u0016J\u0017\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001b\u0010\u0015J\u0017\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001e\u0010\u0015R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$TextFieldTextDragObserver;", "Landroidx/compose/foundation/text/TextDragObserver;", "requestFocus", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/jvm/functions/Function0;)V", "dragBeginOffsetInText", "", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "dragTotalDistance", "actingHandle", "Landroidx/compose/foundation/text/Handle;", "isLongPressSelectionOnly", "", "onDragStop", "onDown", "point", "onDown-k-4lQ0M", "(J)V", "onUp", "onStop", "onCancel", "onStart", "startPoint", "onStart-k-4lQ0M", "onDrag", "delta", "onDrag-k-4lQ0M", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    final class TextFieldTextDragObserver implements TextDragObserver {
        private final Function0<Unit> requestFocus;
        private int dragBeginOffsetInText = -1;
        private long dragBeginPosition = Offset.INSTANCE.m4358getUnspecifiedF1C5BW0();
        private long dragTotalDistance = Offset.INSTANCE.m4359getZeroF1C5BW0();
        private Handle actingHandle = Handle.SelectionEnd;
        private boolean isLongPressSelectionOnly = true;

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* renamed from: onDown-k-4lQ0M */
        public void mo1383onDownk4lQ0M(long point) {
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onUp() {
        }

        public TextFieldTextDragObserver(Function0<Unit> function0) {
            this.requestFocus = function0;
        }

        private final void onDragStop() {
            if ((this.dragBeginPosition & 9223372034707292159L) != InlineClassHelperKt.UnspecifiedPackedFloats) {
                TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldTextDragObserver$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        String onDragStop$lambda$0;
                        onDragStop$lambda$0 = TextFieldSelectionState.TextFieldTextDragObserver.onDragStop$lambda$0();
                        return onDragStop$lambda$0;
                    }
                });
                TextFieldSelectionState.this.clearHandleDragging();
                this.dragBeginOffsetInText = -1;
                this.dragBeginPosition = Offset.INSTANCE.m4358getUnspecifiedF1C5BW0();
                this.dragTotalDistance = Offset.INSTANCE.m4359getZeroF1C5BW0();
                TextFieldSelectionState.this.previousRawDragOffset = -1;
                TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.None);
                this.requestFocus.invoke();
                if (this.isLongPressSelectionOnly) {
                    TextFieldSelectionState.this.maybeSuggestSelectionRange();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onDragStop$lambda$0() {
            return "Touch.onDragStop";
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onStop() {
            onDragStop();
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onCancel() {
            onDragStop();
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* renamed from: onStart-k-4lQ0M */
        public void mo1385onStartk4lQ0M(final long startPoint) {
            if (TextFieldSelectionState.this.enabled) {
                TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldTextDragObserver$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        String onStart_k_4lQ0M$lambda$1;
                        onStart_k_4lQ0M$lambda$1 = TextFieldSelectionState.TextFieldTextDragObserver.onStart_k_4lQ0M$lambda$1(startPoint);
                        return onStart_k_4lQ0M$lambda$1;
                    }
                });
                TextFieldSelectionState.this.m1690updateHandleDraggingUv8p0NA(this.actingHandle, startPoint);
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.Touch);
                this.dragBeginPosition = startPoint;
                this.dragTotalDistance = Offset.INSTANCE.m4359getZeroF1C5BW0();
                TextFieldSelectionState.this.previousRawDragOffset = -1;
                this.isLongPressSelectionOnly = true;
                if (TextFieldSelectionState.this.textLayoutState.getLayoutResult() == null) {
                    return;
                }
                if (!TextFieldSelectionState.this.textLayoutState.m1620isPositionOnTextk4lQ0M(startPoint)) {
                    int m1616getOffsetForPosition3MmeM6k$default = TextLayoutState.m1616getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, startPoint, false, 2, null);
                    HapticFeedback hapticFeedback = TextFieldSelectionState.this.hapticFeedBack;
                    if (hapticFeedback != null) {
                        hapticFeedback.mo5404performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m5421getTextHandleMove5zf0vsI());
                    }
                    TextFieldSelectionState.this.getTextFieldState().placeCursorBeforeCharAt(m1616getOffsetForPosition3MmeM6k$default);
                    TextFieldSelectionState.this.setShowCursorHandle(true);
                    this.isLongPressSelectionOnly = false;
                    TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Cursor);
                    return;
                }
                if (TextFieldSelectionState.this.getTextFieldState().getVisualText().length() == 0) {
                    return;
                }
                int m1616getOffsetForPosition3MmeM6k$default2 = TextLayoutState.m1616getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, startPoint, false, 2, null);
                long m1688updateSelectionSsLRf8$default = TextFieldSelectionState.m1688updateSelectionSsLRf8$default(TextFieldSelectionState.this, new TextFieldCharSequence(TextFieldSelectionState.this.getTextFieldState().getVisualText(), TextRange.INSTANCE.m6853getZerod9O1mEE(), null, null, null, null, 60, null), m1616getOffsetForPosition3MmeM6k$default2, m1616getOffsetForPosition3MmeM6k$default2, false, SelectionAdjustment.INSTANCE.getWord(), false, false, 96, null);
                TextFieldSelectionState.this.getTextFieldState().m1638selectCharsIn5zctL8(m1688updateSelectionSsLRf8$default);
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
                this.dragBeginOffsetInText = TextRange.m6848getStartimpl(m1688updateSelectionSsLRf8$default);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onStart_k_4lQ0M$lambda$1(long j) {
            return "Touch.onDragStart after longPress at " + ((Object) Offset.m4351toStringimpl(j));
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* renamed from: onDrag-k-4lQ0M */
        public void mo1384onDragk4lQ0M(long delta) {
            int intValue;
            int m1619getOffsetForPosition3MmeM6k;
            SelectionAdjustment word;
            Handle handle;
            if (!TextFieldSelectionState.this.enabled || TextFieldSelectionState.this.textLayoutState.getLayoutResult() == null || TextFieldSelectionState.this.getTextFieldState().getVisualText().length() == 0) {
                return;
            }
            long m4348plusMKHz9U = Offset.m4348plusMKHz9U(this.dragTotalDistance, delta);
            this.dragTotalDistance = m4348plusMKHz9U;
            final long m4348plusMKHz9U2 = Offset.m4348plusMKHz9U(this.dragBeginPosition, m4348plusMKHz9U);
            TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$TextFieldTextDragObserver$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    String onDrag_k_4lQ0M$lambda$2;
                    onDrag_k_4lQ0M$lambda$2 = TextFieldSelectionState.TextFieldTextDragObserver.onDrag_k_4lQ0M$lambda$2(m4348plusMKHz9U2);
                    return onDrag_k_4lQ0M$lambda$2;
                }
            });
            if (this.dragBeginOffsetInText < 0 && !TextFieldSelectionState.this.textLayoutState.m1620isPositionOnTextk4lQ0M(m4348plusMKHz9U2)) {
                intValue = TextLayoutState.m1616getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, this.dragBeginPosition, false, 2, null);
                m1619getOffsetForPosition3MmeM6k = TextLayoutState.m1616getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, m4348plusMKHz9U2, false, 2, null);
                if (intValue == m1619getOffsetForPosition3MmeM6k) {
                    word = SelectionAdjustment.INSTANCE.getNone();
                } else {
                    word = SelectionAdjustment.INSTANCE.getWord();
                }
            } else {
                Integer valueOf = Integer.valueOf(this.dragBeginOffsetInText);
                if (valueOf.intValue() < 0) {
                    valueOf = null;
                }
                intValue = valueOf != null ? valueOf.intValue() : TextFieldSelectionState.this.textLayoutState.m1619getOffsetForPosition3MmeM6k(this.dragBeginPosition, false);
                m1619getOffsetForPosition3MmeM6k = TextFieldSelectionState.this.textLayoutState.m1619getOffsetForPosition3MmeM6k(m4348plusMKHz9U2, false);
                if (this.dragBeginOffsetInText < 0 && intValue == m1619getOffsetForPosition3MmeM6k) {
                    return;
                }
                word = SelectionAdjustment.INSTANCE.getWord();
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
            }
            int i = intValue;
            int i2 = m1619getOffsetForPosition3MmeM6k;
            SelectionAdjustment selectionAdjustment = word;
            long selection = TextFieldSelectionState.this.getTextFieldState().getVisualText().getSelection();
            TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
            long m1688updateSelectionSsLRf8$default = TextFieldSelectionState.m1688updateSelectionSsLRf8$default(textFieldSelectionState, textFieldSelectionState.getTextFieldState().getVisualText(), i, i2, false, selectionAdjustment, false, false, 64, null);
            if (this.dragBeginOffsetInText == -1 && !TextRange.m6842getCollapsedimpl(m1688updateSelectionSsLRf8$default)) {
                this.dragBeginOffsetInText = TextRange.m6848getStartimpl(m1688updateSelectionSsLRf8$default);
            }
            if (TextRange.m6847getReversedimpl(m1688updateSelectionSsLRf8$default)) {
                m1688updateSelectionSsLRf8$default = TextFieldSelectionStateKt.m1701reverse5zctL8(m1688updateSelectionSsLRf8$default);
            }
            if (!TextRange.m6841equalsimpl0(m1688updateSelectionSsLRf8$default, selection)) {
                if (TextRange.m6848getStartimpl(m1688updateSelectionSsLRf8$default) != TextRange.m6848getStartimpl(selection) && TextRange.m6843getEndimpl(m1688updateSelectionSsLRf8$default) == TextRange.m6843getEndimpl(selection)) {
                    handle = Handle.SelectionStart;
                } else if (TextRange.m6848getStartimpl(m1688updateSelectionSsLRf8$default) == TextRange.m6848getStartimpl(selection) && TextRange.m6843getEndimpl(m1688updateSelectionSsLRf8$default) != TextRange.m6843getEndimpl(selection)) {
                    handle = Handle.SelectionEnd;
                } else if ((TextRange.m6848getStartimpl(m1688updateSelectionSsLRf8$default) + TextRange.m6843getEndimpl(m1688updateSelectionSsLRf8$default)) / 2.0f > (TextRange.m6848getStartimpl(selection) + TextRange.m6843getEndimpl(selection)) / 2.0f) {
                    handle = Handle.SelectionEnd;
                } else {
                    handle = Handle.SelectionStart;
                }
                this.actingHandle = handle;
                this.isLongPressSelectionOnly = false;
            }
            if (TextRange.m6842getCollapsedimpl(selection) || !TextRange.m6842getCollapsedimpl(m1688updateSelectionSsLRf8$default)) {
                TextFieldSelectionState.this.getTextFieldState().m1638selectCharsIn5zctL8(m1688updateSelectionSsLRf8$default);
            }
            TextFieldSelectionState.this.m1690updateHandleDraggingUv8p0NA(this.actingHandle, m4348plusMKHz9U2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onDrag_k_4lQ0M$lambda$2(long j) {
            return "Touch.onDrag at " + ((Object) Offset.m4351toStringimpl(j));
        }
    }

    public final void maybeSuggestSelectionRange() {
        PlatformSelectionBehaviors platformSelectionBehaviors = this.platformSelectionBehaviors;
        if (platformSelectionBehaviors == null) {
            return;
        }
        CharSequence text = this.textFieldState.getVisualText().getText();
        long selection = this.textFieldState.getVisualText().getSelection();
        if (text.length() <= 0 || TextRange.m6842getCollapsedimpl(selection)) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionState$maybeSuggestSelectionRange$1(platformSelectionBehaviors, text, selection, this, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object detectSelectionHandleDragGestures(PointerInputScope pointerInputScope, final boolean z, Continuation<? super Unit> continuation) {
        TextFieldSelectionState$detectSelectionHandleDragGestures$1 textFieldSelectionState$detectSelectionHandleDragGestures$1;
        int i;
        final Handle handle;
        Ref.LongRef longRef;
        Ref.LongRef longRef2;
        if (continuation instanceof TextFieldSelectionState$detectSelectionHandleDragGestures$1) {
            textFieldSelectionState$detectSelectionHandleDragGestures$1 = (TextFieldSelectionState$detectSelectionHandleDragGestures$1) continuation;
            if ((textFieldSelectionState$detectSelectionHandleDragGestures$1.label & Integer.MIN_VALUE) != 0) {
                textFieldSelectionState$detectSelectionHandleDragGestures$1.label -= Integer.MIN_VALUE;
                TextFieldSelectionState$detectSelectionHandleDragGestures$1 textFieldSelectionState$detectSelectionHandleDragGestures$12 = textFieldSelectionState$detectSelectionHandleDragGestures$1;
                Object obj = textFieldSelectionState$detectSelectionHandleDragGestures$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = textFieldSelectionState$detectSelectionHandleDragGestures$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.LongRef longRef3 = new Ref.LongRef();
                    longRef3.element = Offset.INSTANCE.m4358getUnspecifiedF1C5BW0();
                    final Ref.LongRef longRef4 = new Ref.LongRef();
                    longRef4.element = Offset.INSTANCE.m4359getZeroF1C5BW0();
                    final Handle handle2 = z ? Handle.SelectionStart : Handle.SelectionEnd;
                    try {
                        try {
                            Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    Unit detectSelectionHandleDragGestures$lambda$13;
                                    detectSelectionHandleDragGestures$lambda$13 = TextFieldSelectionState.detectSelectionHandleDragGestures$lambda$13(Ref.LongRef.this, this, z, handle2, longRef4, (Offset) obj2);
                                    return detectSelectionHandleDragGestures$lambda$13;
                                }
                            };
                            handle2 = handle2;
                            longRef3 = longRef3;
                            Function0 function0 = new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    Unit detectSelectionHandleDragGestures$lambda$14;
                                    detectSelectionHandleDragGestures$lambda$14 = TextFieldSelectionState.detectSelectionHandleDragGestures$lambda$14(Ref.LongRef.this, this, longRef4);
                                    return detectSelectionHandleDragGestures$lambda$14;
                                }
                            };
                            Function0 function02 = new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    Unit detectSelectionHandleDragGestures$lambda$15;
                                    detectSelectionHandleDragGestures$lambda$15 = TextFieldSelectionState.detectSelectionHandleDragGestures$lambda$15(Ref.LongRef.this, this, longRef4);
                                    return detectSelectionHandleDragGestures$lambda$15;
                                }
                            };
                            try {
                                Function2 function2 = new Function2() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda4
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj2, Object obj3) {
                                        Unit detectSelectionHandleDragGestures$lambda$16;
                                        detectSelectionHandleDragGestures$lambda$16 = TextFieldSelectionState.detectSelectionHandleDragGestures$lambda$16(Ref.LongRef.this, this, handle2, longRef3, z, (PointerInputChange) obj2, (Offset) obj3);
                                        return detectSelectionHandleDragGestures$lambda$16;
                                    }
                                };
                                longRef4 = longRef4;
                                textFieldSelectionState$detectSelectionHandleDragGestures$12.L$0 = longRef3;
                                textFieldSelectionState$detectSelectionHandleDragGestures$12.L$1 = longRef4;
                                textFieldSelectionState$detectSelectionHandleDragGestures$12.L$2 = handle2;
                                textFieldSelectionState$detectSelectionHandleDragGestures$12.label = 1;
                                if (DragGestureDetectorKt.detectDragGestures(pointerInputScope, function1, function0, function02, function2, textFieldSelectionState$detectSelectionHandleDragGestures$12) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                handle = handle2;
                                longRef = longRef3;
                                longRef2 = longRef4;
                            } catch (Throwable th) {
                                th = th;
                                longRef4 = longRef4;
                                handle = handle2;
                                longRef = longRef3;
                                longRef2 = longRef4;
                                TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda5
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        String detectSelectionHandleDragGestures$lambda$17;
                                        detectSelectionHandleDragGestures$lambda$17 = TextFieldSelectionState.detectSelectionHandleDragGestures$lambda$17(TextFieldSelectionState.this, handle);
                                        return detectSelectionHandleDragGestures$lambda$17;
                                    }
                                });
                                if (getDraggingHandle() == handle) {
                                    detectSelectionHandleDragGestures$onDragStop$12(longRef, this, longRef2);
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            handle2 = handle2;
                            longRef3 = longRef3;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    handle = (Handle) textFieldSelectionState$detectSelectionHandleDragGestures$12.L$2;
                    longRef2 = (Ref.LongRef) textFieldSelectionState$detectSelectionHandleDragGestures$12.L$1;
                    longRef = (Ref.LongRef) textFieldSelectionState$detectSelectionHandleDragGestures$12.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th4) {
                        th = th4;
                        TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                String detectSelectionHandleDragGestures$lambda$17;
                                detectSelectionHandleDragGestures$lambda$17 = TextFieldSelectionState.detectSelectionHandleDragGestures$lambda$17(TextFieldSelectionState.this, handle);
                                return detectSelectionHandleDragGestures$lambda$17;
                            }
                        });
                        if (getDraggingHandle() == handle) {
                        }
                        throw th;
                    }
                }
                TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        String detectSelectionHandleDragGestures$lambda$17;
                        detectSelectionHandleDragGestures$lambda$17 = TextFieldSelectionState.detectSelectionHandleDragGestures$lambda$17(TextFieldSelectionState.this, handle);
                        return detectSelectionHandleDragGestures$lambda$17;
                    }
                });
                if (getDraggingHandle() == handle) {
                    detectSelectionHandleDragGestures$onDragStop$12(longRef, this, longRef2);
                }
                return Unit.INSTANCE;
            }
        }
        textFieldSelectionState$detectSelectionHandleDragGestures$1 = new TextFieldSelectionState$detectSelectionHandleDragGestures$1(this, continuation);
        TextFieldSelectionState$detectSelectionHandleDragGestures$1 textFieldSelectionState$detectSelectionHandleDragGestures$122 = textFieldSelectionState$detectSelectionHandleDragGestures$1;
        Object obj2 = textFieldSelectionState$detectSelectionHandleDragGestures$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = textFieldSelectionState$detectSelectionHandleDragGestures$122.label;
        if (i != 0) {
        }
        TextFieldSelectionStateKt.logDebug(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String detectSelectionHandleDragGestures$lambda$17;
                detectSelectionHandleDragGestures$lambda$17 = TextFieldSelectionState.detectSelectionHandleDragGestures$lambda$17(TextFieldSelectionState.this, handle);
                return detectSelectionHandleDragGestures$lambda$17;
            }
        });
        if (getDraggingHandle() == handle) {
        }
        return Unit.INSTANCE;
    }

    private static final void detectSelectionHandleDragGestures$onDragStop$12(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Ref.LongRef longRef2) {
        if ((longRef.element & 9223372034707292159L) != InlineClassHelperKt.UnspecifiedPackedFloats) {
            textFieldSelectionState.clearHandleDragging();
            longRef.element = Offset.INSTANCE.m4358getUnspecifiedF1C5BW0();
            longRef2.element = Offset.INSTANCE.m4359getZeroF1C5BW0();
            textFieldSelectionState.previousRawDragOffset = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectSelectionHandleDragGestures$lambda$13(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, boolean z, Handle handle, Ref.LongRef longRef2, Offset offset) {
        longRef.element = SelectionHandlesKt.m1802getAdjustedCoordinatesk4lQ0M(textFieldSelectionState.m1680getHandlePositiontuRUvjQ(z));
        textFieldSelectionState.m1690updateHandleDraggingUv8p0NA(handle, longRef.element);
        longRef2.element = Offset.INSTANCE.m4359getZeroF1C5BW0();
        textFieldSelectionState.previousRawDragOffset = -1;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectSelectionHandleDragGestures$lambda$14(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Ref.LongRef longRef2) {
        detectSelectionHandleDragGestures$onDragStop$12(longRef, textFieldSelectionState, longRef2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectSelectionHandleDragGestures$lambda$15(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Ref.LongRef longRef2) {
        detectSelectionHandleDragGestures$onDragStop$12(longRef, textFieldSelectionState, longRef2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit detectSelectionHandleDragGestures$lambda$16(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Handle handle, Ref.LongRef longRef2, boolean z, PointerInputChange pointerInputChange, Offset offset) {
        int m6848getStartimpl;
        int m6818getOffsetForPositionk4lQ0M;
        longRef.element = Offset.m4348plusMKHz9U(longRef.element, offset.m4353unboximpl());
        TextLayoutResult layoutResult = textFieldSelectionState.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Unit.INSTANCE;
        }
        textFieldSelectionState.m1690updateHandleDraggingUv8p0NA(handle, Offset.m4348plusMKHz9U(longRef2.element, longRef.element));
        if (z) {
            m6848getStartimpl = layoutResult.m6818getOffsetForPositionk4lQ0M(textFieldSelectionState.m1689getHandleDragPositionF1C5BW0());
        } else {
            m6848getStartimpl = TextRange.m6848getStartimpl(textFieldSelectionState.textFieldState.getVisualText().getSelection());
        }
        int i = m6848getStartimpl;
        if (z) {
            m6818getOffsetForPositionk4lQ0M = TextRange.m6843getEndimpl(textFieldSelectionState.textFieldState.getVisualText().getSelection());
        } else {
            m6818getOffsetForPositionk4lQ0M = layoutResult.m6818getOffsetForPositionk4lQ0M(textFieldSelectionState.m1689getHandleDragPositionF1C5BW0());
        }
        long selection = textFieldSelectionState.textFieldState.getVisualText().getSelection();
        long m1688updateSelectionSsLRf8$default = m1688updateSelectionSsLRf8$default(textFieldSelectionState, textFieldSelectionState.textFieldState.getVisualText(), i, m6818getOffsetForPositionk4lQ0M, z, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), false, false, 96, null);
        if (TextRange.m6842getCollapsedimpl(selection) || !TextRange.m6842getCollapsedimpl(m1688updateSelectionSsLRf8$default)) {
            textFieldSelectionState.textFieldState.m1638selectCharsIn5zctL8(m1688updateSelectionSsLRf8$default);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String detectSelectionHandleDragGestures$lambda$17(TextFieldSelectionState textFieldSelectionState, Handle handle) {
        return "Selection Handle drag cancelled for draggingHandle: " + textFieldSelectionState.getDraggingHandle() + " definedOn: " + handle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextChanges(Continuation<? super Unit> continuation) {
        Object collect = FlowKt.drop(FlowKt.distinctUntilChanged(SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TextFieldCharSequence observeTextChanges$lambda$18;
                observeTextChanges$lambda$18 = TextFieldSelectionState.observeTextChanges$lambda$18(TextFieldSelectionState.this);
                return observeTextChanges$lambda$18;
            }
        }), TextFieldSelectionState$observeTextChanges$3.INSTANCE), 1).collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeTextChanges$4
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((TextFieldCharSequence) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(TextFieldCharSequence textFieldCharSequence, Continuation<? super Unit> continuation2) {
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.None);
                return Unit.INSTANCE;
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldCharSequence observeTextChanges$lambda$18(TextFieldSelectionState textFieldSelectionState) {
        return textFieldSelectionState.textFieldState.getVisualText();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextToolbarVisibility(Continuation<? super Unit> continuation) {
        Flow snapshotFlow = SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Rect derivedVisibleContentBounds$foundation_release;
                derivedVisibleContentBounds$foundation_release = TextFieldSelectionState.this.getDerivedVisibleContentBounds$foundation_release();
                return derivedVisibleContentBounds$foundation_release;
            }
        });
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            snapshotFlow = FlowKt.distinctUntilChangedBy(snapshotFlow, new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    boolean observeTextToolbarVisibility$lambda$21$lambda$20;
                    observeTextToolbarVisibility$lambda$21$lambda$20 = TextFieldSelectionState.observeTextToolbarVisibility$lambda$21$lambda$20((Rect) obj);
                    return Boolean.valueOf(observeTextToolbarVisibility$lambda$21$lambda$20);
                }
            });
        }
        Object collect = snapshotFlow.collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeTextToolbarVisibility$4
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((Rect) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(Rect rect, Continuation<? super Unit> continuation2) {
                Object showTextToolbar;
                if (rect != null) {
                    showTextToolbar = TextFieldSelectionState.this.showTextToolbar(rect, continuation2);
                    return showTextToolbar == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? showTextToolbar : Unit.INSTANCE;
                }
                TextFieldSelectionState.this.hideTextToolbar();
                return Unit.INSTANCE;
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }

    public final Rect getDerivedVisibleContentBounds$foundation_release() {
        return (Rect) this.derivedVisibleContentBounds.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect derivedVisibleContentBounds_delegate$lambda$22(TextFieldSelectionState textFieldSelectionState) {
        LayoutCoordinates textLayoutCoordinates;
        boolean m6842getCollapsedimpl = TextRange.m6842getCollapsedimpl(textFieldSelectionState.textFieldState.getVisualText().getSelection());
        if ((!(m6842getCollapsedimpl && textFieldSelectionState.getTextToolbarState() == TextToolbarState.Cursor) && (m6842getCollapsedimpl || textFieldSelectionState.getTextToolbarState() != TextToolbarState.Selection)) || textFieldSelectionState.getDraggingHandle() != null || !textFieldSelectionState.isInTouchMode() || (textLayoutCoordinates = textFieldSelectionState.getTextLayoutCoordinates()) == null) {
            return null;
        }
        Rect visibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates);
        Rect m4383Recttz77jQw = RectKt.m4383Recttz77jQw(textLayoutCoordinates.mo6060localToRootMKHz9U(visibleBounds.m4378getTopLeftF1C5BW0()), visibleBounds.m4376getSizeNHjbRc());
        Rect contentRect = textFieldSelectionState.getContentRect();
        if (contentRect.overlaps(m4383Recttz77jQw)) {
            return contentRect.intersect(m4383Recttz77jQw);
        }
        return null;
    }

    private final Rect getContentRect() {
        LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
        if (textLayoutCoordinates != null) {
            TextFieldCharSequence visualText = this.textFieldState.getVisualText();
            if (TextRange.m6842getCollapsedimpl(visualText.getSelection())) {
                Rect cursorRect = getCursorRect();
                return RectKt.m4383Recttz77jQw(textLayoutCoordinates.mo6060localToRootMKHz9U(cursorRect.m4378getTopLeftF1C5BW0()), cursorRect.m4376getSizeNHjbRc());
            }
            long mo6060localToRootMKHz9U = textLayoutCoordinates.mo6060localToRootMKHz9U(m1680getHandlePositiontuRUvjQ(true));
            long mo6060localToRootMKHz9U2 = textLayoutCoordinates.mo6060localToRootMKHz9U(m1680getHandlePositiontuRUvjQ(false));
            TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
            if (layoutResult == null) {
                return Rect.INSTANCE.getZero();
            }
            float top = layoutResult.getCursorRect(TextRange.m6848getStartimpl(visualText.getSelection())).getTop();
            float intBitsToFloat = Float.intBitsToFloat((int) (textLayoutCoordinates.mo6060localToRootMKHz9U(Offset.m4335constructorimpl((Float.floatToRawIntBits(0.0f) << 32) | (Float.floatToRawIntBits(top) & 4294967295L))) & 4294967295L));
            float top2 = layoutResult.getCursorRect(TextRange.m6843getEndimpl(visualText.getSelection())).getTop();
            float intBitsToFloat2 = Float.intBitsToFloat((int) (textLayoutCoordinates.mo6060localToRootMKHz9U(Offset.m4335constructorimpl((Float.floatToRawIntBits(top2) & 4294967295L) | (Float.floatToRawIntBits(0.0f) << 32))) & 4294967295L));
            int i = (int) (mo6060localToRootMKHz9U >> 32);
            int i2 = (int) (mo6060localToRootMKHz9U2 >> 32);
            return new Rect(Math.min(Float.intBitsToFloat(i), Float.intBitsToFloat(i2)), Math.min(intBitsToFloat, intBitsToFloat2), Math.max(Float.intBitsToFloat(i), Float.intBitsToFloat(i2)), Math.max(Float.intBitsToFloat((int) (mo6060localToRootMKHz9U & 4294967295L)), Float.intBitsToFloat((int) (mo6060localToRootMKHz9U2 & 4294967295L))));
        }
        androidx.compose.foundation.internal.InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("textLayoutCoordinates should not be null.");
        throw new KotlinNothingValueException();
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0052, code lost:
    
        if (((r0 == null || (r0 = androidx.compose.foundation.text.selection.SelectionManagerKt.visibleBounds(r0)) == null) ? false : androidx.compose.foundation.text.selection.SelectionManagerKt.m1837containsInclusiveUv8p0NA(r0, r4)) != false) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final TextFieldHandleState getSelectionHandleState$foundation_release(boolean isStartHandle, boolean includePosition) {
        Rect visibleBounds;
        Handle handle = isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return TextFieldHandleState.INSTANCE.getHidden();
        }
        long selection = this.textFieldState.getVisualText().getSelection();
        if (TextRange.m6842getCollapsedimpl(selection)) {
            return TextFieldHandleState.INSTANCE.getHidden();
        }
        long m1680getHandlePositiontuRUvjQ = m1680getHandlePositiontuRUvjQ(isStartHandle);
        if (getDirectDragGestureInitiator() == InputType.None) {
            if (getDraggingHandle() != handle) {
                LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
            }
            if (!this.textFieldState.getVisualText().shouldShowSelection()) {
                return TextFieldHandleState.INSTANCE.getHidden();
            }
            ResolvedTextDirection bidiRunDirection = layoutResult.getBidiRunDirection(isStartHandle ? TextRange.m6848getStartimpl(selection) : Math.max(TextRange.m6843getEndimpl(selection) - 1, 0));
            boolean m6847getReversedimpl = TextRange.m6847getReversedimpl(selection);
            if (includePosition) {
                LayoutCoordinates textLayoutCoordinates2 = getTextLayoutCoordinates();
                if (textLayoutCoordinates2 != null && (visibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates2)) != null) {
                    m1680getHandlePositiontuRUvjQ = TextLayoutStateKt.m1623coerceIn3MmeM6k(m1680getHandlePositiontuRUvjQ, visibleBounds);
                }
            } else {
                m1680getHandlePositiontuRUvjQ = Offset.INSTANCE.m4358getUnspecifiedF1C5BW0();
            }
            return new TextFieldHandleState(true, m1680getHandlePositiontuRUvjQ, TextLayoutHelperKt.getLineHeight(layoutResult, isStartHandle ? TextRange.m6848getStartimpl(selection) : TextRange.m6843getEndimpl(selection)), bidiRunDirection, m6847getReversedimpl, null);
        }
        return TextFieldHandleState.INSTANCE.getHidden();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getHandlePosition-tuRUvjQ, reason: not valid java name */
    public final long m1680getHandlePositiontuRUvjQ(boolean isStartHandle) {
        int m6843getEndimpl;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Offset.INSTANCE.m4359getZeroF1C5BW0();
        }
        long selection = this.textFieldState.getVisualText().getSelection();
        if (isStartHandle) {
            m6843getEndimpl = TextRange.m6848getStartimpl(selection);
        } else {
            m6843getEndimpl = TextRange.m6843getEndimpl(selection);
        }
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(layoutResult, m6843getEndimpl, isStartHandle, TextRange.m6847getReversedimpl(selection));
    }

    /* renamed from: updateHandleDragging-Uv8p0NA, reason: not valid java name */
    public final void m1690updateHandleDraggingUv8p0NA(Handle handle, long position) {
        setDraggingHandle(handle);
        m1685setRawHandleDragPositionk4lQ0M(position);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markStartContentVisibleOffset() {
        m1686setStartTextLayoutPositionInWindowk4lQ0M(m1679getCurrentTextLayoutPositionInWindowF1C5BW0());
    }

    public final void clearHandleDragging() {
        setDraggingHandle(null);
        m1685setRawHandleDragPositionk4lQ0M(Offset.INSTANCE.m4358getUnspecifiedF1C5BW0());
        m1686setStartTextLayoutPositionInWindowk4lQ0M(Offset.INSTANCE.m4358getUnspecifiedF1C5BW0());
    }

    public final boolean canCut() {
        return (TextRange.m6842getCollapsedimpl(this.textFieldState.getVisualText().getSelection()) || !getEditable$foundation_release() || this.isPassword) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object cut(Continuation<? super Unit> continuation) {
        TextFieldSelectionState$cut$1 textFieldSelectionState$cut$1;
        int i;
        if (continuation instanceof TextFieldSelectionState$cut$1) {
            textFieldSelectionState$cut$1 = (TextFieldSelectionState$cut$1) continuation;
            if ((textFieldSelectionState$cut$1.label & Integer.MIN_VALUE) != 0) {
                textFieldSelectionState$cut$1.label -= Integer.MIN_VALUE;
                Object obj = textFieldSelectionState$cut$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = textFieldSelectionState$cut$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    TextFieldCharSequence visualText = this.textFieldState.getVisualText();
                    if (TextRange.m6842getCollapsedimpl(visualText.getSelection())) {
                        return Unit.INSTANCE;
                    }
                    AnnotatedString annotatedString = new AnnotatedString(TextFieldCharSequenceKt.getSelectedText(visualText).toString(), null, 2, null);
                    Clipboard clipboard = this.clipboard;
                    ClipEntry clipEntry = ClipboardUtils_androidKt.toClipEntry(annotatedString);
                    textFieldSelectionState$cut$1.label = 1;
                    if (clipboard.setClipEntry(clipEntry, textFieldSelectionState$cut$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                this.textFieldState.deleteSelectedText();
                return Unit.INSTANCE;
            }
        }
        textFieldSelectionState$cut$1 = new TextFieldSelectionState$cut$1(this, continuation);
        Object obj2 = textFieldSelectionState$cut$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = textFieldSelectionState$cut$1.label;
        if (i != 0) {
        }
        this.textFieldState.deleteSelectedText();
        return Unit.INSTANCE;
    }

    public final boolean canCopy() {
        return (TextRange.m6842getCollapsedimpl(this.textFieldState.getVisualText().getSelection()) || this.isPassword) ? false : true;
    }

    public static /* synthetic */ Object copy$default(TextFieldSelectionState textFieldSelectionState, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return textFieldSelectionState.copy(z, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object copy(boolean z, Continuation<? super Unit> continuation) {
        TextFieldSelectionState$copy$1 textFieldSelectionState$copy$1;
        int i;
        if (continuation instanceof TextFieldSelectionState$copy$1) {
            textFieldSelectionState$copy$1 = (TextFieldSelectionState$copy$1) continuation;
            if ((textFieldSelectionState$copy$1.label & Integer.MIN_VALUE) != 0) {
                textFieldSelectionState$copy$1.label -= Integer.MIN_VALUE;
                Object obj = textFieldSelectionState$copy$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = textFieldSelectionState$copy$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    TextFieldCharSequence visualText = this.textFieldState.getVisualText();
                    if (TextRange.m6842getCollapsedimpl(visualText.getSelection())) {
                        return Unit.INSTANCE;
                    }
                    AnnotatedString annotatedString = new AnnotatedString(TextFieldCharSequenceKt.getSelectedText(visualText).toString(), null, 2, null);
                    Clipboard clipboard = this.clipboard;
                    ClipEntry clipEntry = ClipboardUtils_androidKt.toClipEntry(annotatedString);
                    textFieldSelectionState$copy$1.Z$0 = z;
                    textFieldSelectionState$copy$1.label = 1;
                    if (clipboard.setClipEntry(clipEntry, textFieldSelectionState$copy$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    z = textFieldSelectionState$copy$1.Z$0;
                    ResultKt.throwOnFailure(obj);
                }
                if (z) {
                    return Unit.INSTANCE;
                }
                this.textFieldState.collapseSelectionToMax();
                return Unit.INSTANCE;
            }
        }
        textFieldSelectionState$copy$1 = new TextFieldSelectionState$copy$1(this, continuation);
        Object obj2 = textFieldSelectionState$copy$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = textFieldSelectionState$copy$1.label;
        if (i != 0) {
        }
        if (z) {
        }
    }

    public final Object updateClipboardEntry(Continuation<? super Unit> continuation) {
        Object update = this.clipboardPasteState.update(continuation);
        return update == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? update : Unit.INSTANCE;
    }

    public final boolean canPaste() {
        if (!getEditable$foundation_release()) {
            return false;
        }
        if (this.clipboardPasteState.get_hasText()) {
            return true;
        }
        Function0<? extends ReceiveContentConfiguration> function0 = this.receiveContentConfiguration;
        return (function0 != null ? function0.invoke() : null) != null && this.clipboardPasteState.get_hasClip();
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0071, code lost:
    
        if (pasteAsPlainText(r0) == r1) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0060, code lost:
    
        if (r12 == r1) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b8, code lost:
    
        if (pasteAsPlainText(r0) == r1) goto L44;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object paste(Continuation<? super Unit> continuation) {
        TextFieldSelectionState$paste$1 textFieldSelectionState$paste$1;
        int i;
        ReceiveContentConfiguration invoke;
        ClipEntry clipEntry;
        String readPlainText;
        if (continuation instanceof TextFieldSelectionState$paste$1) {
            textFieldSelectionState$paste$1 = (TextFieldSelectionState$paste$1) continuation;
            if ((textFieldSelectionState$paste$1.label & Integer.MIN_VALUE) != 0) {
                textFieldSelectionState$paste$1.label -= Integer.MIN_VALUE;
                Object obj = textFieldSelectionState$paste$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = textFieldSelectionState$paste$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Function0<? extends ReceiveContentConfiguration> function0 = this.receiveContentConfiguration;
                    if (function0 == null || (invoke = function0.invoke()) == null) {
                        textFieldSelectionState$paste$1.label = 1;
                    } else {
                        Clipboard clipboard = this.clipboard;
                        textFieldSelectionState$paste$1.L$0 = invoke;
                        textFieldSelectionState$paste$1.label = 2;
                        obj = clipboard.getClipEntry(textFieldSelectionState$paste$1);
                    }
                    return coroutine_suspended;
                }
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                if (i != 2) {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                invoke = (ReceiveContentConfiguration) textFieldSelectionState$paste$1.L$0;
                ResultKt.throwOnFailure(obj);
                ClipEntry clipEntry2 = (ClipEntry) obj;
                if (clipEntry2 != null) {
                    TransferableContent onReceive = invoke.getReceiveContentListener().onReceive(new TransferableContent(clipEntry2, clipEntry2.getClipMetadata(), TransferableContent.Source.INSTANCE.m411getClipboardkB6V9T0(), null, 8, null));
                    if (onReceive != null && (clipEntry = onReceive.getClipEntry()) != null && (readPlainText = TransferableContent_androidKt.readPlainText(clipEntry)) != null) {
                        TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, readPlainText, false, TextFieldEditUndoBehavior.NeverMerge, false, 10, null);
                    }
                    return Unit.INSTANCE;
                }
                textFieldSelectionState$paste$1.L$0 = null;
                textFieldSelectionState$paste$1.label = 3;
            }
        }
        textFieldSelectionState$paste$1 = new TextFieldSelectionState$paste$1(this, continuation);
        Object obj2 = textFieldSelectionState$paste$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = textFieldSelectionState$paste$1.label;
        if (i != 0) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
    
        if (r8 == r1) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0053, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0044, code lost:
    
        if (r8 == r1) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object pasteAsPlainText(Continuation<? super Unit> continuation) {
        TextFieldSelectionState$pasteAsPlainText$1 textFieldSelectionState$pasteAsPlainText$1;
        int i;
        ClipEntry clipEntry;
        if (continuation instanceof TextFieldSelectionState$pasteAsPlainText$1) {
            textFieldSelectionState$pasteAsPlainText$1 = (TextFieldSelectionState$pasteAsPlainText$1) continuation;
            if ((textFieldSelectionState$pasteAsPlainText$1.label & Integer.MIN_VALUE) != 0) {
                textFieldSelectionState$pasteAsPlainText$1.label -= Integer.MIN_VALUE;
                Object obj = textFieldSelectionState$pasteAsPlainText$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = textFieldSelectionState$pasteAsPlainText$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Clipboard clipboard = this.clipboard;
                    textFieldSelectionState$pasteAsPlainText$1.label = 1;
                    obj = clipboard.getClipEntry(textFieldSelectionState$pasteAsPlainText$1);
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        String str = (String) obj;
                        if (str != null) {
                            TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, str, false, TextFieldEditUndoBehavior.NeverMerge, false, 10, null);
                            return Unit.INSTANCE;
                        }
                        return Unit.INSTANCE;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                clipEntry = (ClipEntry) obj;
                if (clipEntry != null) {
                    textFieldSelectionState$pasteAsPlainText$1.label = 2;
                    obj = ClipboardUtils_androidKt.readText(clipEntry, textFieldSelectionState$pasteAsPlainText$1);
                }
                return Unit.INSTANCE;
            }
        }
        textFieldSelectionState$pasteAsPlainText$1 = new TextFieldSelectionState$pasteAsPlainText$1(this, continuation);
        Object obj2 = textFieldSelectionState$pasteAsPlainText$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = textFieldSelectionState$pasteAsPlainText$1.label;
        if (i != 0) {
        }
        clipEntry = (ClipEntry) obj2;
        if (clipEntry != null) {
        }
        return Unit.INSTANCE;
    }

    public final boolean canSelectAll() {
        return TextRange.m6844getLengthimpl(this.textFieldState.getVisualText().getSelection()) != this.textFieldState.getVisualText().length();
    }

    public final void selectAll() {
        this.textFieldState.selectAll();
    }

    public final boolean canAutofill() {
        return getEditable$foundation_release() && TextRange.m6842getCollapsedimpl(this.textFieldState.getVisualText().getSelection());
    }

    public final void autofill() {
        Function0<Unit> function0 = this.requestAutofillAction;
        if (function0 != null) {
            function0.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object showTextToolbar(Rect rect, Continuation<? super Unit> continuation) {
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            this.toolbarRequester.show();
        } else {
            TextToolbarHandler textToolbarHandler = this.textToolbarHandler;
            if (textToolbarHandler != null) {
                Object showTextToolbar = textToolbarHandler.showTextToolbar(this, rect, continuation);
                return showTextToolbar == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? showTextToolbar : Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    public final void deselect() {
        if (!TextRange.m6842getCollapsedimpl(this.textFieldState.getVisualText().getSelection())) {
            this.textFieldState.collapseSelectionToEnd();
        }
        setShowCursorHandle(false);
        updateTextToolbarState(TextToolbarState.None);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideTextToolbar() {
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            this.toolbarRequester.hide();
            return;
        }
        TextToolbarHandler textToolbarHandler = this.textToolbarHandler;
        if (textToolbarHandler != null) {
            textToolbarHandler.hideTextToolbar();
        }
    }

    /* renamed from: updateSelection-SsL-Rf8$default, reason: not valid java name */
    static /* synthetic */ long m1688updateSelectionSsLRf8$default(TextFieldSelectionState textFieldSelectionState, TextFieldCharSequence textFieldCharSequence, int i, int i2, boolean z, SelectionAdjustment selectionAdjustment, boolean z2, boolean z3, int i3, Object obj) {
        if ((i3 & 32) != 0) {
            z2 = false;
        }
        if ((i3 & 64) != 0) {
            z3 = false;
        }
        return textFieldSelectionState.m1687updateSelectionSsLRf8(textFieldCharSequence, i, i2, z, selectionAdjustment, z2, z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateSelection-SsL-Rf8, reason: not valid java name */
    public final long m1687updateSelectionSsLRf8(TextFieldCharSequence textFieldCharSequence, int startOffset, int endOffset, boolean isStartHandle, SelectionAdjustment adjustment, boolean allowPreviousSelectionCollapsed, boolean isStartOfSelection) {
        HapticFeedback hapticFeedback;
        TextRange m6836boximpl = TextRange.m6836boximpl(textFieldCharSequence.getSelection());
        long packedValue = m6836boximpl.getPackedValue();
        if (isStartOfSelection || (!allowPreviousSelectionCollapsed && TextRange.m6842getCollapsedimpl(packedValue))) {
            m6836boximpl = null;
        }
        long m1683getTextFieldSelectionqeG_v_k = m1683getTextFieldSelectionqeG_v_k(startOffset, endOffset, m6836boximpl, isStartHandle, adjustment);
        if (!TextRange.m6841equalsimpl0(m1683getTextFieldSelectionqeG_v_k, textFieldCharSequence.getSelection())) {
            boolean z = TextRange.m6847getReversedimpl(m1683getTextFieldSelectionqeG_v_k) != TextRange.m6847getReversedimpl(textFieldCharSequence.getSelection()) && TextRange.m6841equalsimpl0(TextRangeKt.TextRange(TextRange.m6843getEndimpl(m1683getTextFieldSelectionqeG_v_k), TextRange.m6848getStartimpl(m1683getTextFieldSelectionqeG_v_k)), textFieldCharSequence.getSelection());
            if (isInTouchMode() && !z && (hapticFeedback = this.hapticFeedBack) != null) {
                hapticFeedback.mo5404performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m5421getTextHandleMove5zf0vsI());
            }
        }
        return m1683getTextFieldSelectionqeG_v_k;
    }

    /* renamed from: getTextFieldSelection-qeG_v_k, reason: not valid java name */
    private final long m1683getTextFieldSelectionqeG_v_k(int rawStartOffset, int rawEndOffset, TextRange previousSelection, boolean isStartHandle, SelectionAdjustment adjustment) {
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return TextRange.INSTANCE.m6853getZerod9O1mEE();
        }
        if (previousSelection == null && Intrinsics.areEqual(adjustment, SelectionAdjustment.INSTANCE.getCharacter())) {
            return TextRangeKt.TextRange(rawStartOffset, rawEndOffset);
        }
        SelectionLayout m1806getTextFieldSelectionLayoutRcvTLA = SelectionLayoutKt.m1806getTextFieldSelectionLayoutRcvTLA(layoutResult, rawStartOffset, rawEndOffset, this.previousRawDragOffset, previousSelection != null ? previousSelection.getPackedValue() : TextRange.INSTANCE.m6853getZerod9O1mEE(), previousSelection == null, isStartHandle);
        if (previousSelection != null && !m1806getTextFieldSelectionLayoutRcvTLA.shouldRecomputeSelection(this.previousSelectionLayout)) {
            return previousSelection.getPackedValue();
        }
        long m1790toTextRanged9O1mEE = adjustment.adjust(m1806getTextFieldSelectionLayoutRcvTLA).m1790toTextRanged9O1mEE();
        this.previousSelectionLayout = m1806getTextFieldSelectionLayoutRcvTLA;
        this.previousRawDragOffset = isStartHandle ? rawStartOffset : rawEndOffset;
        return m1790toTextRanged9O1mEE;
    }
}
