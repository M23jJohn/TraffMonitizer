package com.traffmonetizer.client.ui.main.settings;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$SettingsScreenKt {
    public static final ComposableSingletons$SettingsScreenKt INSTANCE = new ComposableSingletons$SettingsScreenKt();

    /* renamed from: lambda$-165721512, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f69lambda$165721512 = ComposableLambdaKt.composableLambdaInstance(-165721512, false, new Function2() { // from class: com.traffmonetizer.client.ui.main.settings.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Unit lambda__165721512$lambda$0;
            lambda__165721512$lambda$0 = ComposableSingletons$SettingsScreenKt.lambda__165721512$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            return lambda__165721512$lambda$0;
        }
    });

    /* renamed from: getLambda$-165721512$app_productionRelease, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8058getLambda$165721512$app_productionRelease() {
        return f69lambda$165721512;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__165721512$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C208@7261L2,209@7292L2,210@7327L2,211@7364L2,212@7401L2,213@7430L2,203@6983L459:SettingsScreen.kt#euo8mi");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-165721512, i, -1, "com.traffmonetizer.client.ui.main.settings.ComposableSingletons$SettingsScreenKt.lambda$-165721512.<anonymous> (SettingsScreen.kt:203)");
            }
            DeviceNameState deviceNameState = new DeviceNameState(null, DeviceNameStatus.SHOWING);
            ComposerKt.sourceInformationMarkerStart(composer, -344573862, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function1() { // from class: com.traffmonetizer.client.ui.main.settings.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit lambda__165721512$lambda$0$0$0;
                        lambda__165721512$lambda$0$0$0 = ComposableSingletons$SettingsScreenKt.lambda__165721512$lambda$0$0$0(((Boolean) obj).booleanValue());
                        return lambda__165721512$lambda$0$0$0;
                    }
                };
                composer.updateRememberedValue(rememberedValue);
            }
            Function1 function1 = (Function1) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, -344572870, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue2 = composer.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = new Function0() { // from class: com.traffmonetizer.client.ui.main.settings.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit unit;
                        unit = Unit.INSTANCE;
                        return unit;
                    }
                };
                composer.updateRememberedValue(rememberedValue2);
            }
            Function0 function0 = (Function0) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, -344571750, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue3 = composer.rememberedValue();
            if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                rememberedValue3 = new Function0() { // from class: com.traffmonetizer.client.ui.main.settings.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit unit;
                        unit = Unit.INSTANCE;
                        return unit;
                    }
                };
                composer.updateRememberedValue(rememberedValue3);
            }
            Function0 function02 = (Function0) rememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, -344570566, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue4 = composer.rememberedValue();
            if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                rememberedValue4 = new Function1() { // from class: com.traffmonetizer.client.ui.main.settings.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit lambda__165721512$lambda$0$3$0;
                        lambda__165721512$lambda$0$3$0 = ComposableSingletons$SettingsScreenKt.lambda__165721512$lambda$0$3$0((String) obj);
                        return lambda__165721512$lambda$0$3$0;
                    }
                };
                composer.updateRememberedValue(rememberedValue4);
            }
            Function1 function12 = (Function1) rememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, -344569382, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue5 = composer.rememberedValue();
            if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                rememberedValue5 = new Function1() { // from class: com.traffmonetizer.client.ui.main.settings.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit lambda__165721512$lambda$0$4$0;
                        lambda__165721512$lambda$0$4$0 = ComposableSingletons$SettingsScreenKt.lambda__165721512$lambda$0$4$0(((Boolean) obj).booleanValue());
                        return lambda__165721512$lambda$0$4$0;
                    }
                };
                composer.updateRememberedValue(rememberedValue5);
            }
            Function1 function13 = (Function1) rememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, -344568454, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue6 = composer.rememberedValue();
            if (rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                rememberedValue6 = new Function0() { // from class: com.traffmonetizer.client.ui.main.settings.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit unit;
                        unit = Unit.INSTANCE;
                        return unit;
                    }
                };
                composer.updateRememberedValue(rememberedValue6);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            SettingsScreenKt.SettingsScreenContent(true, "19yV5m91ctK3TXnp0gU0O5dVQWeGrkha+rbnfxluL4k=", deviceNameState, true, function1, function0, function02, function12, function13, (Function0) rememberedValue6, composer, 920349750);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__165721512$lambda$0$0$0(boolean z) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__165721512$lambda$0$3$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__165721512$lambda$0$4$0(boolean z) {
        return Unit.INSTANCE;
    }
}
