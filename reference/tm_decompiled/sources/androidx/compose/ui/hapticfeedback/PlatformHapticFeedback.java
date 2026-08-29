package androidx.compose.ui.hapticfeedback;

import android.view.View;
import kotlin.Metadata;

/* compiled from: PlatformHapticFeedback.android.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/compose/ui/hapticfeedback/PlatformHapticFeedback;", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "performHapticFeedback", "", "hapticFeedbackType", "Landroidx/compose/ui/hapticfeedback/HapticFeedbackType;", "performHapticFeedback-CdsT49E", "(I)V", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PlatformHapticFeedback implements HapticFeedback {
    public static final int $stable = 8;
    private final View view;

    public PlatformHapticFeedback(View view) {
        this.view = view;
    }

    @Override // androidx.compose.ui.hapticfeedback.HapticFeedback
    /* renamed from: performHapticFeedback-CdsT49E */
    public void mo5404performHapticFeedbackCdsT49E(int hapticFeedbackType) {
        if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5412getConfirm5zf0vsI())) {
            this.view.performHapticFeedback(16);
            return;
        }
        if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5413getContextClick5zf0vsI())) {
            this.view.performHapticFeedback(6);
            return;
        }
        if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5414getGestureEnd5zf0vsI())) {
            this.view.performHapticFeedback(13);
            return;
        }
        if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5415getGestureThresholdActivate5zf0vsI())) {
            this.view.performHapticFeedback(23);
            return;
        }
        if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5416getKeyboardTap5zf0vsI())) {
            this.view.performHapticFeedback(3);
            return;
        }
        if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5417getLongPress5zf0vsI())) {
            this.view.performHapticFeedback(0);
            return;
        }
        if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5418getReject5zf0vsI())) {
            this.view.performHapticFeedback(17);
            return;
        }
        if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5419getSegmentFrequentTick5zf0vsI())) {
            this.view.performHapticFeedback(27);
            return;
        }
        if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5420getSegmentTick5zf0vsI())) {
            this.view.performHapticFeedback(26);
            return;
        }
        if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5421getTextHandleMove5zf0vsI())) {
            this.view.performHapticFeedback(9);
            return;
        }
        if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5422getToggleOff5zf0vsI())) {
            this.view.performHapticFeedback(22);
        } else if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5423getToggleOn5zf0vsI())) {
            this.view.performHapticFeedback(21);
        } else if (HapticFeedbackType.m5408equalsimpl0(hapticFeedbackType, HapticFeedbackType.INSTANCE.m5424getVirtualKey5zf0vsI())) {
            this.view.performHapticFeedback(1);
        }
    }
}
