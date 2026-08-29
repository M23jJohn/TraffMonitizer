package com.traffmonetizer.client.ui.main.settings;

import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.MenuKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.unit.Dp;
import com.traffmonetizer.client.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: DeviceNameView.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$DeviceNameViewKt {
    public static final ComposableSingletons$DeviceNameViewKt INSTANCE = new ComposableSingletons$DeviceNameViewKt();
    private static Function2<Composer, Integer, Unit> lambda$1308683894 = ComposableLambdaKt.composableLambdaInstance(1308683894, false, new Function2() { // from class: com.traffmonetizer.client.ui.main.settings.ComposableSingletons$DeviceNameViewKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Unit lambda_1308683894$lambda$0;
            lambda_1308683894$lambda$0 = ComposableSingletons$DeviceNameViewKt.lambda_1308683894$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            return lambda_1308683894$lambda$0;
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$805847536 = ComposableLambdaKt.composableLambdaInstance(805847536, false, new Function2() { // from class: com.traffmonetizer.client.ui.main.settings.ComposableSingletons$DeviceNameViewKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Unit lambda_805847536$lambda$0;
            lambda_805847536$lambda$0 = ComposableSingletons$DeviceNameViewKt.lambda_805847536$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            return lambda_805847536$lambda$0;
        }
    });

    public final Function2<Composer, Integer, Unit> getLambda$1308683894$app_productionRelease() {
        return lambda$1308683894;
    }

    public final Function2<Composer, Integer, Unit> getLambda$805847536$app_productionRelease() {
        return lambda$805847536;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_1308683894$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C90@3127L31,89@3094L184:DeviceNameView.kt#euo8mi");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1308683894, i, -1, "com.traffmonetizer.client.ui.main.settings.ComposableSingletons$DeviceNameViewKt.lambda$1308683894.<anonymous> (DeviceNameView.kt:89)");
            }
            ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.pen, composer, 0), "Edit device name", SizeKt.m930size3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(18)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer, 432, MenuKt.InTransitionDuration);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_805847536$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C165@5305L31,164@5268L200:DeviceNameView.kt#euo8mi");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(805847536, i, -1, "com.traffmonetizer.client.ui.main.settings.ComposableSingletons$DeviceNameViewKt.lambda$805847536.<anonymous> (DeviceNameView.kt:164)");
            }
            ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.pen, composer, 0), "Edit device name", SizeKt.m930size3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(18)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer, 432, MenuKt.InTransitionDuration);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
