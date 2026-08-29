package androidx.compose.foundation.text.input.internal;

import android.view.InputDevice;
import android.view.KeyEvent;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.text.KeyCommand;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.core.app.NotificationCompat;
import androidx.core.view.InputDeviceCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: TextFieldKeyEventHandler.android.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J7\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J]\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/text/input/internal/AndroidTextFieldKeyEventHandler;", "Landroidx/compose/foundation/text/input/internal/TextFieldKeyEventHandler;", "<init>", "()V", "onPreKeyEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/key/KeyEvent;", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "keyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "onPreKeyEvent-MyFupTE", "(Landroid/view/KeyEvent;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/focus/FocusManager;Landroidx/compose/ui/platform/SoftwareKeyboardController;)Z", "onKeyEvent", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "clipboardKeyCommandsHandler", "Landroidx/compose/foundation/text/input/internal/ClipboardKeyCommandsHandler;", "editable", "singleLine", "onSubmit", "Lkotlin/Function0;", "onKeyEvent-8zsqlwg", "(Landroid/view/KeyEvent;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/platform/SoftwareKeyboardController;ZZLkotlin/jvm/functions/Function0;)Z", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidTextFieldKeyEventHandler extends TextFieldKeyEventHandler {
    public static final int $stable = 8;

    @Override // androidx.compose.foundation.text.input.internal.TextFieldKeyEventHandler
    /* renamed from: onPreKeyEvent-MyFupTE, reason: not valid java name */
    public boolean mo1511onPreKeyEventMyFupTE(KeyEvent event, TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, FocusManager focusManager, SoftwareKeyboardController keyboardController) {
        boolean m1608isKeyCodeYhN2O0w;
        boolean m1608isKeyCodeYhN2O0w2;
        boolean m1608isKeyCodeYhN2O0w3;
        boolean m1608isKeyCodeYhN2O0w4;
        boolean m1608isKeyCodeYhN2O0w5;
        boolean mo1511onPreKeyEventMyFupTE = super.mo1511onPreKeyEventMyFupTE(event, textFieldState, textFieldSelectionState, focusManager, keyboardController);
        if (ComposeFoundationFlags.isTextFieldDpadNavigationEnabled) {
            return mo1511onPreKeyEventMyFupTE;
        }
        if (mo1511onPreKeyEventMyFupTE) {
            return true;
        }
        InputDevice device = event.getDevice();
        if (device == null || !device.supportsSource(InputDeviceCompat.SOURCE_DPAD) || device.isVirtual() || !KeyEventType.m5769equalsimpl0(KeyEvent_androidKt.m5777getTypeZmokQxo(event), KeyEventType.INSTANCE.m5773getKeyDownCS__XNY()) || event.getSource() == 257) {
            return false;
        }
        m1608isKeyCodeYhN2O0w = TextFieldKeyEventHandler_androidKt.m1608isKeyCodeYhN2O0w(event, 19);
        if (m1608isKeyCodeYhN2O0w) {
            return focusManager.mo4236moveFocus3ESFkO8(FocusDirection.INSTANCE.m4233getUpdhqQ8s());
        }
        m1608isKeyCodeYhN2O0w2 = TextFieldKeyEventHandler_androidKt.m1608isKeyCodeYhN2O0w(event, 20);
        if (m1608isKeyCodeYhN2O0w2) {
            return focusManager.mo4236moveFocus3ESFkO8(FocusDirection.INSTANCE.m4226getDowndhqQ8s());
        }
        m1608isKeyCodeYhN2O0w3 = TextFieldKeyEventHandler_androidKt.m1608isKeyCodeYhN2O0w(event, 21);
        if (m1608isKeyCodeYhN2O0w3) {
            return focusManager.mo4236moveFocus3ESFkO8(FocusDirection.INSTANCE.m4229getLeftdhqQ8s());
        }
        m1608isKeyCodeYhN2O0w4 = TextFieldKeyEventHandler_androidKt.m1608isKeyCodeYhN2O0w(event, 22);
        if (m1608isKeyCodeYhN2O0w4) {
            return focusManager.mo4236moveFocus3ESFkO8(FocusDirection.INSTANCE.m4232getRightdhqQ8s());
        }
        m1608isKeyCodeYhN2O0w5 = TextFieldKeyEventHandler_androidKt.m1608isKeyCodeYhN2O0w(event, 23);
        if (!m1608isKeyCodeYhN2O0w5) {
            return false;
        }
        keyboardController.show();
        return true;
    }

    @Override // androidx.compose.foundation.text.input.internal.TextFieldKeyEventHandler
    /* renamed from: onKeyEvent-8zsqlwg, reason: not valid java name */
    public boolean mo1510onKeyEvent8zsqlwg(KeyEvent event, TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, Function1<? super KeyCommand, ? extends Unit> clipboardKeyCommandsHandler, SoftwareKeyboardController keyboardController, boolean editable, boolean singleLine, Function0<Boolean> onSubmit) {
        if (KeyEventType.m5769equalsimpl0(KeyEvent_androidKt.m5777getTypeZmokQxo(event), KeyEventType.INSTANCE.m5773getKeyDownCS__XNY()) && event.isFromSource(InputDeviceCompat.SOURCE_KEYBOARD) && !TextFieldKeyEventHandler_androidKt.m1607isFromSoftKeyboardZmokQxo(event)) {
            textFieldSelectionState.setInTouchMode(false);
        }
        return super.mo1510onKeyEvent8zsqlwg(event, textFieldState, textLayoutState, textFieldSelectionState, clipboardKeyCommandsHandler, keyboardController, editable, singleLine, onSubmit);
    }
}
