package androidx.compose.foundation.gestures;

import android.content.Context;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.runtime.CompositionLocalAccessorScope;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* compiled from: BringIntoViewSpec.android.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\"\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001a\u0010\u0007\u001a\u00020\u0002X\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"LocalBringIntoViewSpec", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "getLocalBringIntoViewSpec$annotations", "()V", "getLocalBringIntoViewSpec", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "PivotBringIntoViewSpec", "getPivotBringIntoViewSpec$annotations", "getPivotBringIntoViewSpec", "()Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BringIntoViewSpec_androidKt {
    private static final ProvidableCompositionLocal<BringIntoViewSpec> LocalBringIntoViewSpec = CompositionLocalKt.compositionLocalWithComputedDefaultOf(new Function1() { // from class: androidx.compose.foundation.gestures.BringIntoViewSpec_androidKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            BringIntoViewSpec LocalBringIntoViewSpec$lambda$0;
            LocalBringIntoViewSpec$lambda$0 = BringIntoViewSpec_androidKt.LocalBringIntoViewSpec$lambda$0((CompositionLocalAccessorScope) obj);
            return LocalBringIntoViewSpec$lambda$0;
        }
    });
    private static final BringIntoViewSpec PivotBringIntoViewSpec = new BringIntoViewSpec() { // from class: androidx.compose.foundation.gestures.BringIntoViewSpec_androidKt$PivotBringIntoViewSpec$1
        private final float childFraction;
        private final float parentFraction = 0.3f;

        @Override // androidx.compose.foundation.gestures.BringIntoViewSpec
        public /* synthetic */ AnimationSpec getScrollAnimationSpec() {
            AnimationSpec defaultScrollAnimationSpec$foundation_release;
            defaultScrollAnimationSpec$foundation_release = BringIntoViewSpec.INSTANCE.getDefaultScrollAnimationSpec$foundation_release();
            return defaultScrollAnimationSpec$foundation_release;
        }

        public final float getParentFraction() {
            return this.parentFraction;
        }

        public final float getChildFraction() {
            return this.childFraction;
        }

        @Override // androidx.compose.foundation.gestures.BringIntoViewSpec
        public float calculateScrollDistance(float offset, float size, float containerSize) {
            float abs = Math.abs((size + offset) - offset);
            boolean z = abs <= containerSize;
            float f = (this.parentFraction * containerSize) - (this.childFraction * abs);
            float f2 = containerSize - f;
            if (z && f2 < abs) {
                f = containerSize - abs;
            }
            return offset - f;
        }
    };

    public static /* synthetic */ void getLocalBringIntoViewSpec$annotations() {
    }

    public static /* synthetic */ void getPivotBringIntoViewSpec$annotations() {
    }

    public static final ProvidableCompositionLocal<BringIntoViewSpec> getLocalBringIntoViewSpec() {
        return LocalBringIntoViewSpec;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BringIntoViewSpec LocalBringIntoViewSpec$lambda$0(CompositionLocalAccessorScope compositionLocalAccessorScope) {
        if (!((Context) compositionLocalAccessorScope.getCurrentValue(AndroidCompositionLocals_androidKt.getLocalContext())).getPackageManager().hasSystemFeature("android.software.leanback")) {
            return BringIntoViewSpec.INSTANCE.getDefaultBringIntoViewSpec$foundation_release();
        }
        return PivotBringIntoViewSpec;
    }

    public static final BringIntoViewSpec getPivotBringIntoViewSpec() {
        return PivotBringIntoViewSpec;
    }
}
