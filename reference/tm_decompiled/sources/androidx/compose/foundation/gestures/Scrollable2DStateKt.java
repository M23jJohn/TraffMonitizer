package androidx.compose.foundation.gestures;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* compiled from: Scrollable2DState.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u001a!\u0010\u0005\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Scrollable2DState", "Landroidx/compose/foundation/gestures/Scrollable2DState;", "consumeScrollDelta", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "rememberScrollable2DState", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/Scrollable2DState;", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Scrollable2DStateKt {
    public static final Scrollable2DState Scrollable2DState(Function1<? super Offset, Offset> function1) {
        return new DefaultScrollable2DState(function1);
    }

    public static final Scrollable2DState rememberScrollable2DState(Function1<? super Offset, Offset> function1, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1315827064, "C(rememberScrollable2DState)N(consumeScrollDelta)123@5880L40,124@5932L63:Scrollable2DState.kt#8bwon0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1315827064, i, -1, "androidx.compose.foundation.gestures.rememberScrollable2DState (Scrollable2DState.kt:122)");
        }
        final State rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composer, i & 14);
        ComposerKt.sourceInformationMarkerStart(composer, 1789429207, "CC(remember):Scrollable2DState.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = Scrollable2DState(new Function1() { // from class: androidx.compose.foundation.gestures.Scrollable2DStateKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Offset rememberScrollable2DState$lambda$1$lambda$0;
                    rememberScrollable2DState$lambda$1$lambda$0 = Scrollable2DStateKt.rememberScrollable2DState$lambda$1$lambda$0(State.this, (Offset) obj);
                    return rememberScrollable2DState$lambda$1$lambda$0;
                }
            });
            composer.updateRememberedValue(rememberedValue);
        }
        Scrollable2DState scrollable2DState = (Scrollable2DState) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return scrollable2DState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset rememberScrollable2DState$lambda$1$lambda$0(State state, Offset offset) {
        return (Offset) ((Function1) state.getValue()).invoke(offset);
    }
}
