package androidx.compose.ui.input.pointer;

import kotlin.Metadata;

/* compiled from: PointerEvent.android.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b*\u001a\r\u0010\u0003\u001a\u00020\u0004H\u0000¢\u0006\u0002\u0010\u0005\u001a\u0019\u0010\u0013\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0011\u0010\u0019\u001a\u00020\u0001*\u00020\b¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0011\u0010\u001c\u001a\u00020\u0001*\u00020\b¢\u0006\u0004\b\u001d\u0010\u001b\"\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0015\u0010\u000b\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\n\"\u0015\u0010\r\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\n\"\u0015\u0010\u000f\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\n\"\u0015\u0010\u0011\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\n\"\u0015\u0010\u0017\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\n\"\u0015\u0010\u001e\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\n\"\u0015\u0010 \u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b!\u0010\n\"\u0015\u0010\"\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b#\u0010\n\"\u0015\u0010$\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b%\u0010\n\"\u0015\u0010&\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b'\u0010\n\"\u0015\u0010(\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b)\u0010\n\"\u0015\u0010*\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b+\u0010\n\"\u0015\u0010,\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b-\u0010\n\"\u0015\u0010.\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b/\u0010\n\"\u0015\u00100\u001a\u00020\u0007*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b1\u0010\n*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001*\f\b\u0000\u0010\u0002\"\u00020\u00012\u00020\u0001¨\u00062"}, d2 = {"NativePointerButtons", "", "NativePointerKeyboardModifiers", "EmptyPointerKeyboardModifiers", "Landroidx/compose/ui/input/pointer/PointerKeyboardModifiers;", "()I", "isPrimaryPressed", "", "Landroidx/compose/ui/input/pointer/PointerButtons;", "isPrimaryPressed-aHzCx-E", "(I)Z", "isSecondaryPressed", "isSecondaryPressed-aHzCx-E", "isTertiaryPressed", "isTertiaryPressed-aHzCx-E", "isBackPressed", "isBackPressed-aHzCx-E", "isForwardPressed", "isForwardPressed-aHzCx-E", "isPressed", "buttonIndex", "isPressed-bNIWhpI", "(II)Z", "areAnyPressed", "getAreAnyPressed-aHzCx-E", "indexOfFirstPressed", "indexOfFirstPressed-aHzCx-E", "(I)I", "indexOfLastPressed", "indexOfLastPressed-aHzCx-E", "isCtrlPressed", "isCtrlPressed-5xRPYO0", "isMetaPressed", "isMetaPressed-5xRPYO0", "isAltPressed", "isAltPressed-5xRPYO0", "isAltGraphPressed", "isAltGraphPressed-5xRPYO0", "isSymPressed", "isSymPressed-5xRPYO0", "isShiftPressed", "isShiftPressed-5xRPYO0", "isFunctionPressed", "isFunctionPressed-5xRPYO0", "isCapsLockOn", "isCapsLockOn-5xRPYO0", "isScrollLockOn", "isScrollLockOn-5xRPYO0", "isNumLockOn", "isNumLockOn-5xRPYO0", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PointerEvent_androidKt {
    /* renamed from: getAreAnyPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m5885getAreAnyPressedaHzCxE(int i) {
        return i != 0;
    }

    /* renamed from: indexOfFirstPressed-aHzCx-E, reason: not valid java name */
    public static final int m5886indexOfFirstPressedaHzCxE(int i) {
        if (i == 0) {
            return -1;
        }
        int i2 = 0;
        for (int i3 = (i & (-97)) | ((i & 96) >>> 5); (i3 & 1) == 0; i3 >>>= 1) {
            i2++;
        }
        return i2;
    }

    /* renamed from: indexOfLastPressed-aHzCx-E, reason: not valid java name */
    public static final int m5887indexOfLastPressedaHzCxE(int i) {
        int i2 = -1;
        for (int i3 = (i & (-97)) | ((i & 96) >>> 5); i3 != 0; i3 >>>= 1) {
            i2++;
        }
        return i2;
    }

    /* renamed from: isAltGraphPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m5888isAltGraphPressed5xRPYO0(int i) {
        return false;
    }

    /* renamed from: isAltPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m5889isAltPressed5xRPYO0(int i) {
        return (i & 2) != 0;
    }

    /* renamed from: isBackPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m5890isBackPressedaHzCxE(int i) {
        return (i & 8) != 0;
    }

    /* renamed from: isCapsLockOn-5xRPYO0, reason: not valid java name */
    public static final boolean m5891isCapsLockOn5xRPYO0(int i) {
        return (i & 1048576) != 0;
    }

    /* renamed from: isCtrlPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m5892isCtrlPressed5xRPYO0(int i) {
        return (i & 4096) != 0;
    }

    /* renamed from: isForwardPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m5893isForwardPressedaHzCxE(int i) {
        return (i & 16) != 0;
    }

    /* renamed from: isFunctionPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m5894isFunctionPressed5xRPYO0(int i) {
        return (i & 8) != 0;
    }

    /* renamed from: isMetaPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m5895isMetaPressed5xRPYO0(int i) {
        return (i & 65536) != 0;
    }

    /* renamed from: isNumLockOn-5xRPYO0, reason: not valid java name */
    public static final boolean m5896isNumLockOn5xRPYO0(int i) {
        return (i & 2097152) != 0;
    }

    /* renamed from: isPrimaryPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m5898isPrimaryPressedaHzCxE(int i) {
        return (i & 33) != 0;
    }

    /* renamed from: isScrollLockOn-5xRPYO0, reason: not valid java name */
    public static final boolean m5899isScrollLockOn5xRPYO0(int i) {
        return (i & 4194304) != 0;
    }

    /* renamed from: isSecondaryPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m5900isSecondaryPressedaHzCxE(int i) {
        return (i & 66) != 0;
    }

    /* renamed from: isShiftPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m5901isShiftPressed5xRPYO0(int i) {
        return (i & 1) != 0;
    }

    /* renamed from: isSymPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m5902isSymPressed5xRPYO0(int i) {
        return (i & 4) != 0;
    }

    /* renamed from: isTertiaryPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m5903isTertiaryPressedaHzCxE(int i) {
        return (i & 4) != 0;
    }

    public static final int EmptyPointerKeyboardModifiers() {
        return PointerKeyboardModifiers.m5988constructorimpl(0);
    }

    /* renamed from: isPressed-bNIWhpI, reason: not valid java name */
    public static final boolean m5897isPressedbNIWhpI(int i, int i2) {
        if (i2 == 0) {
            return m5898isPrimaryPressedaHzCxE(i);
        }
        if (i2 != 1) {
            return (i2 == 2 || i2 == 3 || i2 == 4) ? (i & (1 << i2)) != 0 : (i & (1 << (i2 + 2))) != 0;
        }
        return m5900isSecondaryPressedaHzCxE(i);
    }
}
