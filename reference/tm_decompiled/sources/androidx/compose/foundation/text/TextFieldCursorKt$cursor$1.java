package androidx.compose.foundation.text;

import androidx.compose.foundation.text.input.internal.CursorAnimationState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* compiled from: TextFieldCursor.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class TextFieldCursorKt$cursor$1 implements Function3<Modifier, Composer, Integer, Modifier> {
    final /* synthetic */ Brush $cursorBrush;
    final /* synthetic */ OffsetMapping $offsetMapping;
    final /* synthetic */ LegacyTextFieldState $state;
    final /* synthetic */ TextFieldValue $value;

    TextFieldCursorKt$cursor$1(Brush brush, LegacyTextFieldState legacyTextFieldState, TextFieldValue textFieldValue, OffsetMapping offsetMapping) {
        this.$cursorBrush = brush;
        this.$state = legacyTextFieldState;
        this.$value = textFieldValue;
        this.$offsetMapping = offsetMapping;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
        return invoke(modifier, composer, num.intValue());
    }

    public final Modifier invoke(Modifier modifier, Composer composer, int i) {
        Modifier.Companion companion;
        composer.startReplaceGroup(-84507373);
        ComposerKt.sourceInformation(composer, "C46@1735L7,47@1777L63,54@2290L7:TextFieldCursor.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-84507373, i, -1, "androidx.compose.foundation.text.cursor.<anonymous> (TextFieldCursor.kt:46)");
        }
        ProvidableCompositionLocal<Boolean> localCursorBlinkEnabled = CompositionLocalsKt.getLocalCursorBlinkEnabled();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object consume = composer.consume(localCursorBlinkEnabled);
        ComposerKt.sourceInformationMarkerEnd(composer);
        boolean booleanValue = ((Boolean) consume).booleanValue();
        ComposerKt.sourceInformationMarkerStart(composer, 392796434, "CC(remember):TextFieldCursor.kt#9igjgp");
        boolean changed = composer.changed(booleanValue);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new CursorAnimationState(booleanValue);
            composer.updateRememberedValue(rememberedValue);
        }
        final CursorAnimationState cursorAnimationState = (CursorAnimationState) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        Brush brush = this.$cursorBrush;
        boolean z = ((brush instanceof SolidColor) && ((SolidColor) brush).getValue() == 16) ? false : true;
        ProvidableCompositionLocal<WindowInfo> localWindowInfo = CompositionLocalsKt.getLocalWindowInfo();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object consume2 = composer.consume(localWindowInfo);
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (((WindowInfo) consume2).isWindowFocused() && this.$state.getHasFocus() && TextRange.m6842getCollapsedimpl(this.$value.getSelection()) && z) {
            composer.startReplaceGroup(-707487962);
            ComposerKt.sourceInformation(composer, "58@2517L81,58@2462L136,61@2631L1888");
            AnnotatedString annotatedString = this.$value.getAnnotatedString();
            TextRange m6836boximpl = TextRange.m6836boximpl(this.$value.getSelection());
            ComposerKt.sourceInformationMarkerStart(composer, 392820132, "CC(remember):TextFieldCursor.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(cursorAnimationState);
            Object rememberedValue2 = composer.rememberedValue();
            if (changedInstance || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = (Function2) new TextFieldCursorKt$cursor$1$1$1(cursorAnimationState, null);
                composer.updateRememberedValue(rememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            EffectsKt.LaunchedEffect(annotatedString, m6836boximpl, (Function2) rememberedValue2, composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, 392825587, "CC(remember):TextFieldCursor.kt#9igjgp");
            boolean changedInstance2 = composer.changedInstance(cursorAnimationState) | composer.changedInstance(this.$offsetMapping) | composer.changed(this.$value) | composer.changedInstance(this.$state) | composer.changed(this.$cursorBrush);
            final OffsetMapping offsetMapping = this.$offsetMapping;
            final TextFieldValue textFieldValue = this.$value;
            final LegacyTextFieldState legacyTextFieldState = this.$state;
            final Brush brush2 = this.$cursorBrush;
            Object rememberedValue3 = composer.rememberedValue();
            if (changedInstance2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: androidx.compose.foundation.text.TextFieldCursorKt$cursor$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        Unit invoke$lambda$4$lambda$3;
                        invoke$lambda$4$lambda$3 = TextFieldCursorKt$cursor$1.invoke$lambda$4$lambda$3(CursorAnimationState.this, offsetMapping, textFieldValue, legacyTextFieldState, brush2, (ContentDrawScope) obj2);
                        return invoke$lambda$4$lambda$3;
                    }
                };
                composer.updateRememberedValue(obj);
                rememberedValue3 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            companion = DrawModifierKt.drawWithContent(modifier, (Function1) rememberedValue3);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-705473241);
            composer.endReplaceGroup();
            companion = Modifier.INSTANCE;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return companion;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$4$lambda$3(CursorAnimationState cursorAnimationState, OffsetMapping offsetMapping, TextFieldValue textFieldValue, LegacyTextFieldState legacyTextFieldState, Brush brush, ContentDrawScope contentDrawScope) {
        Rect rect;
        float rint;
        TextLayoutResult value;
        contentDrawScope.drawContent();
        float cursorAlpha = cursorAnimationState.getCursorAlpha();
        if (cursorAlpha != 0.0f) {
            int originalToTransformed = offsetMapping.originalToTransformed(TextRange.m6848getStartimpl(textFieldValue.getSelection()));
            TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
            if (layoutResult == null || (value = layoutResult.getValue()) == null || (rect = value.getCursorRect(originalToTransformed)) == null) {
                rect = new Rect(0.0f, 0.0f, 0.0f, 0.0f);
            }
            float coerceAtLeast = RangesKt.coerceAtLeast((float) Math.floor(contentDrawScope.mo459toPx0680j_4(TextFieldCursor_androidKt.getDefaultCursorThickness())), 1.0f);
            float f = coerceAtLeast / 2;
            float coerceAtLeast2 = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(rect.getLeft() + f, Float.intBitsToFloat((int) (contentDrawScope.mo5121getSizeNHjbRc() >> 32)) - f), f);
            if (((int) coerceAtLeast) % 2 == 1) {
                rint = ((float) Math.floor(coerceAtLeast2)) + 0.5f;
            } else {
                rint = (float) Math.rint(coerceAtLeast2);
            }
            DrawScope.CC.m5197drawLine1RTmtNc$default(contentDrawScope, brush, Offset.m4335constructorimpl((Float.floatToRawIntBits(rect.getTop()) & 4294967295L) | (Float.floatToRawIntBits(rint) << 32)), Offset.m4335constructorimpl((Float.floatToRawIntBits(rect.getBottom()) & 4294967295L) | (Float.floatToRawIntBits(rint) << 32)), coerceAtLeast, 0, null, cursorAlpha, null, 0, 432, null);
        }
        return Unit.INSTANCE;
    }
}
