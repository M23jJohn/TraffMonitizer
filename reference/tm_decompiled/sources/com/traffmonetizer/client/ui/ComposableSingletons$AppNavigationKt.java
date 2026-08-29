package com.traffmonetizer.client.ui;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.navigation.NavBackStackEntry;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppNavigation.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$AppNavigationKt {
    public static final ComposableSingletons$AppNavigationKt INSTANCE = new ComposableSingletons$AppNavigationKt();
    private static Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit> lambda$927293577 = ComposableLambdaKt.composableLambdaInstance(927293577, false, new Function4() { // from class: com.traffmonetizer.client.ui.ComposableSingletons$AppNavigationKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            Unit lambda_927293577$lambda$0;
            lambda_927293577$lambda$0 = ComposableSingletons$AppNavigationKt.lambda_927293577$lambda$0((AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            return lambda_927293577$lambda$0;
        }
    });

    public final Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit> getLambda$927293577$app_productionRelease() {
        return lambda$927293577;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_927293577$lambda$0(AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)42@1684L15:AppNavigation.kt#lc08b0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(927293577, i, -1, "com.traffmonetizer.client.ui.ComposableSingletons$AppNavigationKt.lambda$927293577.<anonymous> (AppNavigation.kt:42)");
        }
        AppNavigationKt.InitialScreen(composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }
}
