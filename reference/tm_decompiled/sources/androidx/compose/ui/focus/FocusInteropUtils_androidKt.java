package androidx.compose.ui.focus;

import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;

/* compiled from: FocusInteropUtils.android.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u0001H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0015\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0007H\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\r\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0000\u001a%\u0010\u0011\u001a\u00020\u0012*\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0000¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"toFocusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "androidDirection", "", "toAndroidFocusDirection", "toAndroidFocusDirection-3ESFkO8", "(I)Ljava/lang/Integer;", "Landroidx/compose/ui/input/key/KeyEvent;", "toFocusDirection-ZmokQxo", "(Landroid/view/KeyEvent;)Landroidx/compose/ui/focus/FocusDirection;", "toLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "androidLayoutDirection", "calculateBoundingRectRelativeTo", "Landroidx/compose/ui/geometry/Rect;", "Landroid/view/View;", "view", "requestInteropFocus", "", "direction", "rect", "Landroid/graphics/Rect;", "(Landroid/view/View;Ljava/lang/Integer;Landroid/graphics/Rect;)Z", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FocusInteropUtils_androidKt {
    public static final FocusDirection toFocusDirection(int i) {
        if (i == 1) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4231getPreviousdhqQ8s());
        }
        if (i == 2) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4230getNextdhqQ8s());
        }
        if (i == 17) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4229getLeftdhqQ8s());
        }
        if (i == 33) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4233getUpdhqQ8s());
        }
        if (i == 66) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4232getRightdhqQ8s());
        }
        if (i != 130) {
            return null;
        }
        return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4226getDowndhqQ8s());
    }

    /* renamed from: toAndroidFocusDirection-3ESFkO8, reason: not valid java name */
    public static final Integer m4234toAndroidFocusDirection3ESFkO8(int i) {
        if (FocusDirection.m4222equalsimpl0(i, FocusDirection.INSTANCE.m4233getUpdhqQ8s())) {
            return 33;
        }
        if (FocusDirection.m4222equalsimpl0(i, FocusDirection.INSTANCE.m4226getDowndhqQ8s())) {
            return 130;
        }
        if (FocusDirection.m4222equalsimpl0(i, FocusDirection.INSTANCE.m4229getLeftdhqQ8s())) {
            return 17;
        }
        if (FocusDirection.m4222equalsimpl0(i, FocusDirection.INSTANCE.m4232getRightdhqQ8s())) {
            return 66;
        }
        if (FocusDirection.m4222equalsimpl0(i, FocusDirection.INSTANCE.m4230getNextdhqQ8s())) {
            return 2;
        }
        return FocusDirection.m4222equalsimpl0(i, FocusDirection.INSTANCE.m4231getPreviousdhqQ8s()) ? 1 : null;
    }

    /* renamed from: toFocusDirection-ZmokQxo, reason: not valid java name */
    public static final FocusDirection m4235toFocusDirectionZmokQxo(KeyEvent keyEvent) {
        long m5776getKeyZmokQxo = KeyEvent_androidKt.m5776getKeyZmokQxo(keyEvent);
        if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5628getNavigatePreviousEK5gGoQ())) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4231getPreviousdhqQ8s());
        }
        if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5626getNavigateNextEK5gGoQ())) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4230getNextdhqQ8s());
        }
        if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5704getTabEK5gGoQ())) {
            return FocusDirection.m4219boximpl(KeyEvent_androidKt.m5782isShiftPressedZmokQxo(keyEvent) ? FocusDirection.INSTANCE.m4231getPreviousdhqQ8s() : FocusDirection.INSTANCE.m4230getNextdhqQ8s());
        }
        if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5545getDirectionRightEK5gGoQ())) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4232getRightdhqQ8s());
        }
        if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5544getDirectionLeftEK5gGoQ())) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4229getLeftdhqQ8s());
        }
        if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5546getDirectionUpEK5gGoQ()) || Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5657getPageUpEK5gGoQ())) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4233getUpdhqQ8s());
        }
        if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5541getDirectionDownEK5gGoQ()) || Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5656getPageDownEK5gGoQ())) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4226getDowndhqQ8s());
        }
        if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5540getDirectionCenterEK5gGoQ()) || Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5554getEnterEK5gGoQ()) || Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5646getNumPadEnterEK5gGoQ())) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4227getEnterdhqQ8s());
        }
        if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5483getBackEK5gGoQ()) || Key.m5468equalsimpl0(m5776getKeyZmokQxo, Key.INSTANCE.m5557getEscapeEK5gGoQ())) {
            return FocusDirection.m4219boximpl(FocusDirection.INSTANCE.m4228getExitdhqQ8s());
        }
        return null;
    }

    public static final LayoutDirection toLayoutDirection(int i) {
        if (i == 0) {
            return LayoutDirection.Ltr;
        }
        if (i != 1) {
            return null;
        }
        return LayoutDirection.Rtl;
    }

    public static final Rect calculateBoundingRectRelativeTo(View view, View view2) {
        view.getLocationInWindow(FocusInteropUtils.INSTANCE.getTempCoordinates());
        int i = FocusInteropUtils.INSTANCE.getTempCoordinates()[0];
        int i2 = FocusInteropUtils.INSTANCE.getTempCoordinates()[1];
        view2.getLocationInWindow(FocusInteropUtils.INSTANCE.getTempCoordinates());
        float f = i - FocusInteropUtils.INSTANCE.getTempCoordinates()[0];
        float f2 = i2 - FocusInteropUtils.INSTANCE.getTempCoordinates()[1];
        return new Rect(f, f2, view.getWidth() + f, view.getHeight() + f2);
    }

    public static final boolean requestInteropFocus(View view, Integer num, android.graphics.Rect rect) {
        if (num == null) {
            return view.requestFocus();
        }
        if (!(view instanceof ViewGroup)) {
            return view.requestFocus(num.intValue(), rect);
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.isFocused()) {
            return true;
        }
        if (viewGroup.isFocusable() && !viewGroup.hasFocus()) {
            return viewGroup.requestFocus(num.intValue(), rect);
        }
        if (view instanceof AndroidComposeView) {
            return ((AndroidComposeView) view).requestFocus(num.intValue(), rect);
        }
        if (rect != null) {
            View findNextFocusFromRect = FocusFinder.getInstance().findNextFocusFromRect(viewGroup, rect, num.intValue());
            return findNextFocusFromRect != null ? findNextFocusFromRect.requestFocus(num.intValue(), rect) : viewGroup.requestFocus(num.intValue(), rect);
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(viewGroup, viewGroup.hasFocus() ? viewGroup.findFocus() : null, num.intValue());
        return findNextFocus != null ? findNextFocus.requestFocus(num.intValue()) : view.requestFocus(num.intValue());
    }
}
