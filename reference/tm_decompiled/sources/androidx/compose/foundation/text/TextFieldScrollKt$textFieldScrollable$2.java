package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: TextFieldScroll.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class TextFieldScrollKt$textFieldScrollable$2 implements Function3<Modifier, Composer, Integer, Modifier> {
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ TextFieldScrollerPosition $scrollerPosition;

    TextFieldScrollKt$textFieldScrollable$2(TextFieldScrollerPosition textFieldScrollerPosition, boolean z, MutableInteractionSource mutableInteractionSource) {
        this.$scrollerPosition = textFieldScrollerPosition;
        this.$enabled = z;
        this.$interactionSource = mutableInteractionSource;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
        return invoke(modifier, composer, num.intValue());
    }

    public final Modifier invoke(Modifier modifier, Composer composer, int i) {
        composer.startReplaceGroup(805428266);
        ComposerKt.sourceInformation(composer, "C71@3041L7,73@3218L464,73@3194L488,88@3858L452:TextFieldScroll.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(805428266, i, -1, "androidx.compose.foundation.text.textFieldScrollable.<anonymous> (TextFieldScroll.kt:71)");
        }
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object consume = composer.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd(composer);
        boolean z = this.$scrollerPosition.getOrientation() == Orientation.Vertical || !(consume == LayoutDirection.Rtl);
        ComposerKt.sourceInformationMarkerStart(composer, 922460410, "CC(remember):TextFieldScroll.kt#9igjgp");
        boolean changed = composer.changed(this.$scrollerPosition);
        final TextFieldScrollerPosition textFieldScrollerPosition = this.$scrollerPosition;
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    float invoke$lambda$1$lambda$0;
                    invoke$lambda$1$lambda$0 = TextFieldScrollKt$textFieldScrollable$2.invoke$lambda$1$lambda$0(TextFieldScrollerPosition.this, ((Float) obj).floatValue());
                    return Float.valueOf(invoke$lambda$1$lambda$0);
                }
            };
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        ScrollableState rememberScrollableState = ScrollableStateKt.rememberScrollableState((Function1) rememberedValue, composer, 0);
        ComposerKt.sourceInformationMarkerStart(composer, 922480878, "CC(remember):TextFieldScroll.kt#9igjgp");
        boolean changed2 = composer.changed(rememberScrollableState) | composer.changed(this.$scrollerPosition);
        TextFieldScrollerPosition textFieldScrollerPosition2 = this.$scrollerPosition;
        Object rememberedValue2 = composer.rememberedValue();
        if (changed2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = new TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1(rememberScrollableState, textFieldScrollerPosition2);
            composer.updateRememberedValue(rememberedValue2);
        }
        TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1 textFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1 = (TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1) rememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        Modifier scrollable$default = ScrollableKt.scrollable$default(Modifier.INSTANCE, textFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1, this.$scrollerPosition.getOrientation(), this.$enabled && this.$scrollerPosition.getMaximum() != 0.0f, z, null, this.$interactionSource, 16, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return scrollable$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float invoke$lambda$1$lambda$0(TextFieldScrollerPosition textFieldScrollerPosition, float f) {
        float offset = textFieldScrollerPosition.getOffset() + f;
        if (offset > textFieldScrollerPosition.getMaximum()) {
            f = textFieldScrollerPosition.getMaximum() - textFieldScrollerPosition.getOffset();
        } else if (offset < 0.0f) {
            f = -textFieldScrollerPosition.getOffset();
        }
        textFieldScrollerPosition.setOffset(textFieldScrollerPosition.getOffset() + f);
        return f;
    }
}
