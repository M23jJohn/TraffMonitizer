package com.traffmonetizer.client.ui.main.home;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import com.traffmonetizer.client.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StatsView.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$StatsViewKt {
    public static final ComposableSingletons$StatsViewKt INSTANCE = new ComposableSingletons$StatsViewKt();
    private static Function3<RowScope, Composer, Integer, Unit> lambda$2098053610 = ComposableLambdaKt.composableLambdaInstance(2098053610, false, new Function3() { // from class: com.traffmonetizer.client.ui.main.home.ComposableSingletons$StatsViewKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Unit lambda_2098053610$lambda$0;
            lambda_2098053610$lambda$0 = ComposableSingletons$StatsViewKt.lambda_2098053610$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            return lambda_2098053610$lambda$0;
        }
    });

    /* renamed from: lambda$-1365381943, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f68lambda$1365381943 = ComposableLambdaKt.composableLambdaInstance(-1365381943, false, new Function2() { // from class: com.traffmonetizer.client.ui.main.home.ComposableSingletons$StatsViewKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Unit lambda__1365381943$lambda$0;
            lambda__1365381943$lambda$0 = ComposableSingletons$StatsViewKt.lambda__1365381943$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            return lambda__1365381943$lambda$0;
        }
    });

    /* renamed from: getLambda$-1365381943$app_productionRelease, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8046getLambda$1365381943$app_productionRelease() {
        return f68lambda$1365381943;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$2098053610$app_productionRelease() {
        return lambda$2098053610;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_2098053610$lambda$0(RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C119@4018L42,120@4100L10,118@3989L146:StatsView.kt#92mpw2");
        if (!composer.shouldExecute((i & 17) != 16, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2098053610, i, -1, "com.traffmonetizer.client.ui.main.home.ComposableSingletons$StatsViewKt.lambda$2098053610.<anonymous> (StatsView.kt:118)");
            }
            TextKt.m2849Text4IGK_g(StringResources_androidKt.stringResource(R.string.more_stats_button, composer, 0), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getLabelLarge(), composer, 0, 0, 65534);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__1365381943$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C130@4242L54:StatsView.kt#92mpw2");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1365381943, i, -1, "com.traffmonetizer.client.ui.main.home.ComposableSingletons$StatsViewKt.lambda$-1365381943.<anonymous> (StatsView.kt:130)");
            }
            StatsViewKt.HomeStatsView(null, composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
