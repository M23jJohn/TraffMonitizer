package com.traffmonetizer.client.ui;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.core.view.PointerIconCompat;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigator;
import androidx.navigation.compose.NavGraphBuilderKt;
import androidx.navigation.compose.NavHostControllerKt;
import androidx.navigation.compose.NavHostKt;
import com.traffmonetizer.client.AuthViewModel;
import com.traffmonetizer.client.service.ServiceViewModel;
import com.traffmonetizer.client.ui.Screen;
import com.traffmonetizer.client.ui.login.LoginScreenKt;
import com.traffmonetizer.client.ui.main.MainScreenKt;
import com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreenKt;
import com.traffmonetizer.client.ui.registration.RegistrationScreenKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AppNavigation.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\b\u001a\u001d\u0010\t\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0003¢\u0006\u0002\u0010\f\u001a\r\u0010\r\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u000e¨\u0006\u000f²\u0006\f\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u008a\u0084\u0002"}, d2 = {"AppNavigation", "", "modifier", "Landroidx/compose/ui/Modifier;", "authViewModel", "Lcom/traffmonetizer/client/AuthViewModel;", "serviceViewModel", "Lcom/traffmonetizer/client/service/ServiceViewModel;", "(Landroidx/compose/ui/Modifier;Lcom/traffmonetizer/client/AuthViewModel;Lcom/traffmonetizer/client/service/ServiceViewModel;Landroidx/compose/runtime/Composer;I)V", "LoginNavigation", "navController", "Landroidx/navigation/NavController;", "(Lcom/traffmonetizer/client/AuthViewModel;Landroidx/navigation/NavController;Landroidx/compose/runtime/Composer;I)V", "InitialScreen", "(Landroidx/compose/runtime/Composer;I)V", "app_productionRelease", "isLoggedIn", ""}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AppNavigationKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AppNavigation$lambda$1(Modifier modifier, AuthViewModel authViewModel, ServiceViewModel serviceViewModel, int i, Composer composer, int i2) {
        AppNavigation(modifier, authViewModel, serviceViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit InitialScreen$lambda$0(int i, Composer composer, int i2) {
        InitialScreen(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LoginNavigation$lambda$2(AuthViewModel authViewModel, NavController navController, int i, Composer composer, int i2) {
        LoginNavigation(authViewModel, navController, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void AppNavigation(final Modifier modifier, final AuthViewModel authViewModel, final ServiceViewModel serviceViewModel, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        Intrinsics.checkNotNullParameter(authViewModel, "authViewModel");
        Intrinsics.checkNotNullParameter(serviceViewModel, "serviceViewModel");
        Composer startRestartGroup = composer.startRestartGroup(-771616922);
        ComposerKt.sourceInformation(startRestartGroup, "C(AppNavigation)N(modifier,authViewModel,serviceViewModel)32@1416L23,34@1445L45,40@1627L786,36@1496L917:AppNavigation.kt#lc08b0");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? startRestartGroup.changed(authViewModel) : startRestartGroup.changedInstance(authViewModel) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changedInstance(serviceViewModel) ? 256 : 128;
        }
        boolean z = true;
        if (!startRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-771616922, i2, -1, "com.traffmonetizer.client.ui.AppNavigation (AppNavigation.kt:31)");
            }
            final NavHostController rememberNavController = NavHostControllerKt.rememberNavController(new Navigator[0], startRestartGroup, 0);
            LoginNavigation(authViewModel, rememberNavController, startRestartGroup, AuthViewModel.$stable | ((i2 >> 3) & 14));
            String route = Screen.Initial.INSTANCE.getRoute();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1505491864, "CC(remember):AppNavigation.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(rememberNavController);
            if ((i2 & 112) != 32 && ((i2 & 64) == 0 || !startRestartGroup.changedInstance(authViewModel))) {
                z = false;
            }
            boolean changedInstance2 = changedInstance | z | startRestartGroup.changedInstance(serviceViewModel);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changedInstance2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function1() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit AppNavigation$lambda$0$0;
                        AppNavigation$lambda$0$0 = AppNavigationKt.AppNavigation$lambda$0$0(NavHostController.this, authViewModel, serviceViewModel, (NavGraphBuilder) obj);
                        return AppNavigation$lambda$0$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            NavHostKt.NavHost(rememberNavController, route, modifier, null, null, null, null, null, null, null, (Function1) rememberedValue, startRestartGroup, (i2 << 6) & 896, 0, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit AppNavigation$lambda$1;
                    AppNavigation$lambda$1 = AppNavigationKt.AppNavigation$lambda$1(Modifier.this, authViewModel, serviceViewModel, i, (Composer) obj, ((Integer) obj2).intValue());
                    return AppNavigation$lambda$1;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AppNavigation$lambda$0$0(final NavHostController navHostController, final AuthViewModel authViewModel, final ServiceViewModel serviceViewModel, NavGraphBuilder NavHost) {
        Intrinsics.checkNotNullParameter(NavHost, "$this$NavHost");
        NavGraphBuilderKt.composable$default(NavHost, Screen.Initial.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableSingletons$AppNavigationKt.INSTANCE.getLambda$927293577$app_productionRelease(), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Login.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(-57276416, true, new Function4() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                Unit AppNavigation$lambda$0$0$0;
                AppNavigation$lambda$0$0$0 = AppNavigationKt.AppNavigation$lambda$0$0$0(NavHostController.this, authViewModel, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                return AppNavigation$lambda$0$0$0;
            }
        }), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Registration.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(742298113, true, new Function4() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                Unit AppNavigation$lambda$0$0$1;
                AppNavigation$lambda$0$0$1 = AppNavigationKt.AppNavigation$lambda$0$0$1(NavHostController.this, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                return AppNavigation$lambda$0$0$1;
            }
        }), 254, (Object) null);
        NavGraphBuilderKt.navigation$default(NavHost, Screen.MainScreen.INSTANCE.getRoute(), Screen.MainGraph.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, new Function1() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit AppNavigation$lambda$0$0$2;
                AppNavigation$lambda$0$0$2 = AppNavigationKt.AppNavigation$lambda$0$0$2(NavHostController.this, authViewModel, serviceViewModel, (NavGraphBuilder) obj);
                return AppNavigation$lambda$0$0$2;
            }
        }, 508, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AppNavigation$lambda$0$0$0(NavHostController navHostController, AuthViewModel authViewModel, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)45@1763L61:AppNavigation.kt#lc08b0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-57276416, i, -1, "com.traffmonetizer.client.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:45)");
        }
        LoginScreenKt.LoginScreen(navHostController, authViewModel, composer, AuthViewModel.$stable << 3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AppNavigation$lambda$0$0$1(NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)48@1895L53:AppNavigation.kt#lc08b0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(742298113, i, -1, "com.traffmonetizer.client.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:48)");
        }
        RegistrationScreenKt.RegistrationScreen(navHostController, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AppNavigation$lambda$0$0$2(final NavHostController navHostController, final AuthViewModel authViewModel, final ServiceViewModel serviceViewModel, NavGraphBuilder navigation) {
        Intrinsics.checkNotNullParameter(navigation, "$this$navigation");
        NavGraphBuilderKt.composable$default(navigation, Screen.MainScreen.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(1346835509, true, new Function4() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                Unit AppNavigation$lambda$0$0$2$0;
                AppNavigation$lambda$0$0$2$0 = AppNavigationKt.AppNavigation$lambda$0$0$2$0(NavHostController.this, authViewModel, serviceViewModel, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                return AppNavigation$lambda$0$0$2$0;
            }
        }), 254, (Object) null);
        NavGraphBuilderKt.composable$default(navigation, Screen.ConnectionCheckScreen.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(637065004, true, new Function4() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                Unit AppNavigation$lambda$0$0$2$1;
                AppNavigation$lambda$0$0$2$1 = AppNavigationKt.AppNavigation$lambda$0$0$2$1(NavHostController.this, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                return AppNavigation$lambda$0$0$2$1;
            }
        }), 254, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AppNavigation$lambda$0$0$2$0(NavHostController navHostController, AuthViewModel authViewModel, ServiceViewModel serviceViewModel, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)55@2157L78:AppNavigation.kt#lc08b0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1346835509, i, -1, "com.traffmonetizer.client.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:55)");
        }
        MainScreenKt.MainScreen(navHostController, authViewModel, serviceViewModel, composer, AuthViewModel.$stable << 3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AppNavigation$lambda$0$0$2$1(NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)58@2327L56:AppNavigation.kt#lc08b0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(637065004, i, -1, "com.traffmonetizer.client.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:58)");
        }
        ConnectionCheckScreenKt.ConnectionCheckScreen(navHostController, null, composer, 0, 2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    private static final void LoginNavigation(final AuthViewModel authViewModel, final NavController navController, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(-291621559);
        ComposerKt.sourceInformation(startRestartGroup, "C(LoginNavigation)N(authViewModel,navController)69@2577L16,70@2625L548,70@2598L575:AppNavigation.kt#lc08b0");
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? startRestartGroup.changed(authViewModel) : startRestartGroup.changedInstance(authViewModel) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(navController) ? 32 : 16;
        }
        if (!startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-291621559, i2, -1, "com.traffmonetizer.client.ui.LoginNavigation (AppNavigation.kt:68)");
            }
            State collectAsState = SnapshotStateKt.collectAsState(authViewModel.isLoggedIn(), null, startRestartGroup, 0, 1);
            Boolean LoginNavigation$lambda$0 = LoginNavigation$lambda$0(collectAsState);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1075437395, "CC(remember):AppNavigation.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(navController) | startRestartGroup.changed(collectAsState);
            AppNavigationKt$LoginNavigation$1$1 rememberedValue = startRestartGroup.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new AppNavigationKt$LoginNavigation$1$1(navController, collectAsState, null);
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            EffectsKt.LaunchedEffect(LoginNavigation$lambda$0, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue, startRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit LoginNavigation$lambda$2;
                    LoginNavigation$lambda$2 = AppNavigationKt.LoginNavigation$lambda$2(AuthViewModel.this, navController, i, (Composer) obj, ((Integer) obj2).intValue());
                    return LoginNavigation$lambda$2;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void InitialScreen(Composer composer, final int i) {
        Composer startRestartGroup = composer.startRestartGroup(-1398284647);
        ComposerKt.sourceInformation(startRestartGroup, "C(InitialScreen):AppNavigation.kt#lc08b0");
        if (startRestartGroup.shouldExecute(i != 0, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1398284647, i, -1, "com.traffmonetizer.client.ui.InitialScreen (AppNavigation.kt:91)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit InitialScreen$lambda$0;
                    InitialScreen$lambda$0 = AppNavigationKt.InitialScreen$lambda$0(i, (Composer) obj, ((Integer) obj2).intValue());
                    return InitialScreen$lambda$0;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean LoginNavigation$lambda$0(State<Boolean> state) {
        return state.getValue();
    }
}
