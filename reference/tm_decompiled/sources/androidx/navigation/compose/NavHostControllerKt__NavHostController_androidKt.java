package androidx.navigation.compose;

import android.content.Context;
import android.os.Bundle;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigator;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: NavHostController.android.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u00012\"\u0010\u0002\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00040\u0003\"\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0002\u0010\u0006\u001a\u0015\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0002¢\u0006\u0002\b\n\u001a\u001f\u0010\u000b\u001a\f\u0012\u0004\u0012\u00020\u0001\u0012\u0002\b\u00030\f2\u0006\u0010\b\u001a\u00020\tH\u0002¢\u0006\u0002\b\r¨\u0006\u000e"}, d2 = {"rememberNavController", "Landroidx/navigation/NavHostController;", "navigators", "", "Landroidx/navigation/Navigator;", "Landroidx/navigation/NavDestination;", "([Landroidx/navigation/Navigator;Landroidx/compose/runtime/Composer;I)Landroidx/navigation/NavHostController;", "createNavController", "context", "Landroid/content/Context;", "createNavController$NavHostControllerKt__NavHostController_androidKt", "NavControllerSaver", "Landroidx/compose/runtime/saveable/Saver;", "NavControllerSaver$NavHostControllerKt__NavHostController_androidKt", "navigation-compose_release"}, k = 5, mv = {2, 0, 0}, xi = 48, xs = "androidx/navigation/compose/NavHostControllerKt")
/* loaded from: classes3.dex */
final /* synthetic */ class NavHostControllerKt__NavHostController_androidKt {
    public static final NavHostController rememberNavController(Navigator<? extends NavDestination>[] navigatorArr, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -342848815, "C(rememberNavController)34@1229L7,35@1323L52,35@1248L127:NavHostController.android.kt#opm8kd");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-342848815, i, -1, "androidx.navigation.compose.rememberNavController (NavHostController.android.kt:33)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd(composer);
        final Context context = (Context) consume;
        Object[] copyOf = Arrays.copyOf(navigatorArr, navigatorArr.length);
        Saver<NavHostController, ?> NavControllerSaver$NavHostControllerKt__NavHostController_androidKt = NavControllerSaver$NavHostControllerKt__NavHostController_androidKt(context);
        ComposerKt.sourceInformationMarkerStart(composer, 1234778757, "CC(remember):NavHostController.android.kt#9igjgp");
        boolean changedInstance = composer.changedInstance(context);
        Object rememberedValue = composer.rememberedValue();
        if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Function0() { // from class: androidx.navigation.compose.NavHostControllerKt__NavHostController_androidKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    NavHostController createNavController$NavHostControllerKt__NavHostController_androidKt;
                    createNavController$NavHostControllerKt__NavHostController_androidKt = NavHostControllerKt__NavHostController_androidKt.createNavController$NavHostControllerKt__NavHostController_androidKt(context);
                    return createNavController$NavHostControllerKt__NavHostController_androidKt;
                }
            };
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        NavHostController navHostController = (NavHostController) RememberSaveableKt.m4032rememberSaveable(copyOf, (Saver) NavControllerSaver$NavHostControllerKt__NavHostController_androidKt, (String) null, (Function0) rememberedValue, composer, 0, 4);
        for (Navigator<? extends NavDestination> navigator : navigatorArr) {
            navHostController.getNavigatorProvider().addNavigator(navigator);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return navHostController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NavHostController createNavController$NavHostControllerKt__NavHostController_androidKt(Context context) {
        NavHostController navHostController = new NavHostController(context);
        navHostController.getNavigatorProvider().addNavigator(new ComposeNavGraphNavigator(navHostController.getNavigatorProvider()));
        navHostController.getNavigatorProvider().addNavigator(new ComposeNavigator());
        navHostController.getNavigatorProvider().addNavigator(new DialogNavigator());
        return navHostController;
    }

    private static final Saver<NavHostController, ?> NavControllerSaver$NavHostControllerKt__NavHostController_androidKt(final Context context) {
        return SaverKt.Saver(new Function2() { // from class: androidx.navigation.compose.NavHostControllerKt__NavHostController_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Bundle NavControllerSaver$lambda$4$NavHostControllerKt__NavHostController_androidKt;
                NavControllerSaver$lambda$4$NavHostControllerKt__NavHostController_androidKt = NavHostControllerKt__NavHostController_androidKt.NavControllerSaver$lambda$4$NavHostControllerKt__NavHostController_androidKt((SaverScope) obj, (NavHostController) obj2);
                return NavControllerSaver$lambda$4$NavHostControllerKt__NavHostController_androidKt;
            }
        }, new Function1() { // from class: androidx.navigation.compose.NavHostControllerKt__NavHostController_androidKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                NavHostController NavControllerSaver$lambda$6$NavHostControllerKt__NavHostController_androidKt;
                NavControllerSaver$lambda$6$NavHostControllerKt__NavHostController_androidKt = NavHostControllerKt__NavHostController_androidKt.NavControllerSaver$lambda$6$NavHostControllerKt__NavHostController_androidKt(context, (Bundle) obj);
                return NavControllerSaver$lambda$6$NavHostControllerKt__NavHostController_androidKt;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bundle NavControllerSaver$lambda$4$NavHostControllerKt__NavHostController_androidKt(SaverScope saverScope, NavHostController navHostController) {
        return navHostController.saveState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NavHostController NavControllerSaver$lambda$6$NavHostControllerKt__NavHostController_androidKt(Context context, Bundle bundle) {
        NavHostController createNavController$NavHostControllerKt__NavHostController_androidKt = createNavController$NavHostControllerKt__NavHostController_androidKt(context);
        createNavController$NavHostControllerKt__NavHostController_androidKt.restoreState(bundle);
        return createNavController$NavHostControllerKt__NavHostController_androidKt;
    }
}
