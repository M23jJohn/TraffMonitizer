package androidx.compose.ui.hapticfeedback;

import kotlin.Metadata;

/* compiled from: PlatformHapticFeedback.android.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001c\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0013\u0010\u0017\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0018\u0010\u0007R\u0013\u0010\u0019\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001a\u0010\u0007R\u0013\u0010\u001b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001c\u0010\u0007R\u0013\u0010\u001d\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001e\u0010\u0007R\u0013\u0010\u001f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b \u0010\u0007¨\u0006!"}, d2 = {"Landroidx/compose/ui/hapticfeedback/PlatformHapticFeedbackType;", "", "<init>", "()V", "Confirm", "Landroidx/compose/ui/hapticfeedback/HapticFeedbackType;", "getConfirm-5zf0vsI", "()I", "I", "ContextClick", "getContextClick-5zf0vsI", "GestureEnd", "getGestureEnd-5zf0vsI", "GestureThresholdActivate", "getGestureThresholdActivate-5zf0vsI", "KeyboardTap", "getKeyboardTap-5zf0vsI", "LongPress", "getLongPress-5zf0vsI", "Reject", "getReject-5zf0vsI", "SegmentFrequentTick", "getSegmentFrequentTick-5zf0vsI", "SegmentTick", "getSegmentTick-5zf0vsI", "TextHandleMove", "getTextHandleMove-5zf0vsI", "ToggleOff", "getToggleOff-5zf0vsI", "ToggleOn", "getToggleOn-5zf0vsI", "VirtualKey", "getVirtualKey-5zf0vsI", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PlatformHapticFeedbackType {
    public static final int $stable = 0;
    public static final PlatformHapticFeedbackType INSTANCE = new PlatformHapticFeedbackType();
    private static final int Confirm = HapticFeedbackType.m5406constructorimpl(16);
    private static final int ContextClick = HapticFeedbackType.m5406constructorimpl(6);
    private static final int GestureEnd = HapticFeedbackType.m5406constructorimpl(13);
    private static final int GestureThresholdActivate = HapticFeedbackType.m5406constructorimpl(23);
    private static final int KeyboardTap = HapticFeedbackType.m5406constructorimpl(3);
    private static final int LongPress = HapticFeedbackType.m5406constructorimpl(0);
    private static final int Reject = HapticFeedbackType.m5406constructorimpl(17);
    private static final int SegmentFrequentTick = HapticFeedbackType.m5406constructorimpl(27);
    private static final int SegmentTick = HapticFeedbackType.m5406constructorimpl(26);
    private static final int TextHandleMove = HapticFeedbackType.m5406constructorimpl(9);
    private static final int ToggleOff = HapticFeedbackType.m5406constructorimpl(22);
    private static final int ToggleOn = HapticFeedbackType.m5406constructorimpl(21);
    private static final int VirtualKey = HapticFeedbackType.m5406constructorimpl(1);

    private PlatformHapticFeedbackType() {
    }

    /* renamed from: getConfirm-5zf0vsI, reason: not valid java name */
    public final int m5425getConfirm5zf0vsI() {
        return Confirm;
    }

    /* renamed from: getContextClick-5zf0vsI, reason: not valid java name */
    public final int m5426getContextClick5zf0vsI() {
        return ContextClick;
    }

    /* renamed from: getGestureEnd-5zf0vsI, reason: not valid java name */
    public final int m5427getGestureEnd5zf0vsI() {
        return GestureEnd;
    }

    /* renamed from: getGestureThresholdActivate-5zf0vsI, reason: not valid java name */
    public final int m5428getGestureThresholdActivate5zf0vsI() {
        return GestureThresholdActivate;
    }

    /* renamed from: getKeyboardTap-5zf0vsI, reason: not valid java name */
    public final int m5429getKeyboardTap5zf0vsI() {
        return KeyboardTap;
    }

    /* renamed from: getLongPress-5zf0vsI, reason: not valid java name */
    public final int m5430getLongPress5zf0vsI() {
        return LongPress;
    }

    /* renamed from: getReject-5zf0vsI, reason: not valid java name */
    public final int m5431getReject5zf0vsI() {
        return Reject;
    }

    /* renamed from: getSegmentFrequentTick-5zf0vsI, reason: not valid java name */
    public final int m5432getSegmentFrequentTick5zf0vsI() {
        return SegmentFrequentTick;
    }

    /* renamed from: getSegmentTick-5zf0vsI, reason: not valid java name */
    public final int m5433getSegmentTick5zf0vsI() {
        return SegmentTick;
    }

    /* renamed from: getTextHandleMove-5zf0vsI, reason: not valid java name */
    public final int m5434getTextHandleMove5zf0vsI() {
        return TextHandleMove;
    }

    /* renamed from: getToggleOff-5zf0vsI, reason: not valid java name */
    public final int m5435getToggleOff5zf0vsI() {
        return ToggleOff;
    }

    /* renamed from: getToggleOn-5zf0vsI, reason: not valid java name */
    public final int m5436getToggleOn5zf0vsI() {
        return ToggleOn;
    }

    /* renamed from: getVirtualKey-5zf0vsI, reason: not valid java name */
    public final int m5437getVirtualKey5zf0vsI() {
        return VirtualKey;
    }
}
