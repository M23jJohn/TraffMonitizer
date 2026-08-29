package androidx.compose.foundation.text.selection;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: AndroidSelectionHandles.android.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class AndroidSelectionHandles_androidKt$drawSelectionHandle$1 implements Function3<Modifier, Composer, Integer, Modifier> {
    final /* synthetic */ Function0<Boolean> $iconVisible;
    final /* synthetic */ boolean $isLeft;

    AndroidSelectionHandles_androidKt$drawSelectionHandle$1(Function0<Boolean> function0, boolean z) {
        this.$iconVisible = function0;
        this.$isLeft = z;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
        return invoke(modifier, composer, num.intValue());
    }

    public final Modifier invoke(Modifier modifier, Composer composer, int i) {
        composer.startReplaceGroup(-196777734);
        ComposerKt.sourceInformation(composer, "C129@5425L7,130@5472L678:AndroidSelectionHandles.android.kt#eksfi3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-196777734, i, -1, "androidx.compose.foundation.text.selection.drawSelectionHandle.<anonymous> (AndroidSelectionHandles.android.kt:129)");
        }
        ProvidableCompositionLocal<TextSelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object consume = composer.consume(localTextSelectionColors);
        ComposerKt.sourceInformationMarkerEnd(composer);
        final long handleColor = ((TextSelectionColors) consume).getHandleColor();
        ComposerKt.sourceInformationMarkerStart(composer, -124837472, "CC(remember):AndroidSelectionHandles.android.kt#9igjgp");
        boolean changed = composer.changed(handleColor) | composer.changed(this.$iconVisible) | composer.changed(this.$isLeft);
        final Function0<Boolean> function0 = this.$iconVisible;
        final boolean z = this.$isLeft;
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$drawSelectionHandle$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    DrawResult invoke$lambda$3$lambda$2;
                    invoke$lambda$3$lambda$2 = AndroidSelectionHandles_androidKt$drawSelectionHandle$1.invoke$lambda$3$lambda$2(handleColor, function0, z, (CacheDrawScope) obj);
                    return invoke$lambda$3$lambda$2;
                }
            };
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Modifier drawWithCache = DrawModifierKt.drawWithCache(modifier, (Function1) rememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return drawWithCache;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DrawResult invoke$lambda$3$lambda$2(long j, final Function0 function0, final boolean z, CacheDrawScope cacheDrawScope) {
        final ImageBitmap createHandleImage = AndroidSelectionHandles_androidKt.createHandleImage(cacheDrawScope, Float.intBitsToFloat((int) (cacheDrawScope.m4127getSizeNHjbRc() >> 32)) / 2.0f);
        final ColorFilter m4656tintxETnrds$default = ColorFilter.Companion.m4656tintxETnrds$default(ColorFilter.INSTANCE, j, 0, 2, null);
        return cacheDrawScope.onDrawWithContent(new Function1() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$drawSelectionHandle$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit invoke$lambda$3$lambda$2$lambda$1;
                invoke$lambda$3$lambda$2$lambda$1 = AndroidSelectionHandles_androidKt$drawSelectionHandle$1.invoke$lambda$3$lambda$2$lambda$1(Function0.this, z, createHandleImage, m4656tintxETnrds$default, (ContentDrawScope) obj);
                return invoke$lambda$3$lambda$2$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$3$lambda$2$lambda$1(Function0 function0, boolean z, ImageBitmap imageBitmap, ColorFilter colorFilter, ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        if (!((Boolean) function0.invoke()).booleanValue()) {
            return Unit.INSTANCE;
        }
        if (z) {
            ContentDrawScope contentDrawScope2 = contentDrawScope;
            long mo5120getCenterF1C5BW0 = contentDrawScope2.mo5120getCenterF1C5BW0();
            DrawContext drawContext = contentDrawScope2.getDrawContext();
            long mo5128getSizeNHjbRc = drawContext.mo5128getSizeNHjbRc();
            drawContext.getCanvas().save();
            try {
                drawContext.getTransform().mo5135scale0AR0LA0(-1.0f, 1.0f, mo5120getCenterF1C5BW0);
                DrawScope.CC.m5196drawImagegbVJVH8$default(contentDrawScope2, imageBitmap, 0L, 0.0f, null, colorFilter, 0, 46, null);
            } finally {
                drawContext.getCanvas().restore();
                drawContext.mo5129setSizeuvyYCjk(mo5128getSizeNHjbRc);
            }
        } else {
            DrawScope.CC.m5196drawImagegbVJVH8$default(contentDrawScope, imageBitmap, 0L, 0.0f, null, colorFilter, 0, 46, null);
        }
        return Unit.INSTANCE;
    }
}
