package com.traffmonetizer.client.ui.registration;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RegistrationScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$RegistrationScreenKt {
    public static final ComposableSingletons$RegistrationScreenKt INSTANCE = new ComposableSingletons$RegistrationScreenKt();

    /* renamed from: lambda$-1307207447, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f70lambda$1307207447 = ComposableLambdaKt.composableLambdaInstance(-1307207447, false, new Function3() { // from class: com.traffmonetizer.client.ui.registration.ComposableSingletons$RegistrationScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Unit lambda__1307207447$lambda$0;
            lambda__1307207447$lambda$0 = ComposableSingletons$RegistrationScreenKt.lambda__1307207447$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            return lambda__1307207447$lambda$0;
        }
    });

    /* renamed from: getLambda$-1307207447$app_productionRelease, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8068getLambda$1307207447$app_productionRelease() {
        return f70lambda$1307207447;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__1307207447$lambda$0(RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C30@1074L19:RegistrationScreen.kt#zdccdh");
        if (!composer.shouldExecute((i & 17) != 16, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1307207447, i, -1, "com.traffmonetizer.client.ui.registration.ComposableSingletons$RegistrationScreenKt.lambda$-1307207447.<anonymous> (RegistrationScreen.kt:30)");
            }
            TextKt.m2849Text4IGK_g("Back", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
