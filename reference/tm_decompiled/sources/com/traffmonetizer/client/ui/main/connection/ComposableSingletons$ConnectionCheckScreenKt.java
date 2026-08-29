package com.traffmonetizer.client.ui.main.connection;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: ConnectionCheckScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$ConnectionCheckScreenKt {
    public static final ComposableSingletons$ConnectionCheckScreenKt INSTANCE = new ComposableSingletons$ConnectionCheckScreenKt();

    /* renamed from: lambda$-1860359253, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f67lambda$1860359253 = ComposableLambdaKt.composableLambdaInstance(-1860359253, false, new Function2() { // from class: com.traffmonetizer.client.ui.main.connection.ComposableSingletons$ConnectionCheckScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Unit lambda__1860359253$lambda$0;
            lambda__1860359253$lambda$0 = ComposableSingletons$ConnectionCheckScreenKt.lambda__1860359253$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            return lambda__1860359253$lambda$0;
        }
    });

    /* renamed from: lambda$-1072712108, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f66lambda$1072712108 = ComposableLambdaKt.composableLambdaInstance(-1072712108, false, new Function2() { // from class: com.traffmonetizer.client.ui.main.connection.ComposableSingletons$ConnectionCheckScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Unit lambda__1072712108$lambda$0;
            lambda__1072712108$lambda$0 = ComposableSingletons$ConnectionCheckScreenKt.lambda__1072712108$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            return lambda__1072712108$lambda$0;
        }
    });

    /* renamed from: getLambda$-1072712108$app_productionRelease, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8042getLambda$1072712108$app_productionRelease() {
        return f66lambda$1072712108;
    }

    /* renamed from: getLambda$-1860359253$app_productionRelease, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8043getLambda$1860359253$app_productionRelease() {
        return f67lambda$1860359253;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__1860359253$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C107@3849L131:ConnectionCheckScreen.kt#8cw6nj");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1860359253, i, -1, "com.traffmonetizer.client.ui.main.connection.ComposableSingletons$ConnectionCheckScreenKt.lambda$-1860359253.<anonymous> (ConnectionCheckScreen.kt:107)");
            }
            IconKt.m2306Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.INSTANCE.getDefault()), "Back", (Modifier) null, 0L, composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__1072712108$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C183@6442L2,182@6409L45:ConnectionCheckScreen.kt#8cw6nj");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1072712108, i, -1, "com.traffmonetizer.client.ui.main.connection.ComposableSingletons$ConnectionCheckScreenKt.lambda$-1072712108.<anonymous> (ConnectionCheckScreen.kt:182)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -84183370, "CC(remember):ConnectionCheckScreen.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function0() { // from class: com.traffmonetizer.client.ui.main.connection.ComposableSingletons$ConnectionCheckScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit unit;
                        unit = Unit.INSTANCE;
                        return unit;
                    }
                };
                composer.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ConnectionCheckScreenKt.MainTopBar((Function0) rememberedValue, composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
