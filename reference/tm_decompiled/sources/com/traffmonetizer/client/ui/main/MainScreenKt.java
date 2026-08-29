package com.traffmonetizer.client.ui.main;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.layout.WindowInsets_androidKt;
import androidx.compose.material3.MenuKt;
import androidx.compose.material3.NavigationBarKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SnackbarHostKt;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.PointerIconCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.FlowExtKt;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.NavHostController;
import androidx.navigation.NavOptions;
import androidx.navigation.NavOptionsBuilder;
import androidx.navigation.Navigator;
import androidx.navigation.compose.NavGraphBuilderKt;
import androidx.navigation.compose.NavHostControllerKt;
import androidx.navigation.compose.NavHostKt;
import androidx.profileinstaller.ProfileVerifier;
import com.traffmonetizer.client.AuthViewModel;
import com.traffmonetizer.client.R;
import com.traffmonetizer.client.service.ServiceViewModel;
import com.traffmonetizer.client.traffmonetizer_repository.model.ConnectionStatusEntity;
import com.traffmonetizer.client.ui.Screen;
import com.traffmonetizer.client.ui.main.connection.ConnectionIndicatorKt;
import com.traffmonetizer.client.ui.main.home.TraffStatsEntity$$ExternalSyntheticBackport0;
import com.traffmonetizer.client.ui.main.settings.SettingsScreenKt;
import com.traffmonetizer.client.ui.theme.ColorKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainScreen.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\b\u001a\u0015\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000b\u001a#\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0007¢\u0006\u0002\u0010\u0011¨\u0006\u0012²\u0006\n\u0010\u0013\u001a\u00020\u000eX\u008a\u0084\u0002²\u0006\f\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u008a\u0084\u0002"}, d2 = {"MainScreen", "", "rootNavController", "Landroidx/navigation/NavController;", "authViewModel", "Lcom/traffmonetizer/client/AuthViewModel;", "serviceViewModel", "Lcom/traffmonetizer/client/service/ServiceViewModel;", "(Landroidx/navigation/NavController;Lcom/traffmonetizer/client/AuthViewModel;Lcom/traffmonetizer/client/service/ServiceViewModel;Landroidx/compose/runtime/Composer;I)V", "MainBottomBar", "tabNavController", "(Landroidx/navigation/NavController;Landroidx/compose/runtime/Composer;I)V", "MainTopBar", "connectionStatus", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "onCheckConnection", "Lkotlin/Function0;", "(Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_productionRelease", "connectionsStatus", "navBackStackEntry", "Landroidx/navigation/NavBackStackEntry;"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MainScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainBottomBar$lambda$2(NavController navController, int i, Composer composer, int i2) {
        MainBottomBar(navController, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$6(NavController navController, AuthViewModel authViewModel, ServiceViewModel serviceViewModel, int i, Composer composer, int i2) {
        MainScreen(navController, authViewModel, serviceViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainTopBar$lambda$1(ConnectionStatusEntity connectionStatusEntity, Function0 function0, int i, Composer composer, int i2) {
        MainTopBar(connectionStatusEntity, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void MainScreen(final NavController rootNavController, final AuthViewModel authViewModel, final ServiceViewModel serviceViewModel, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(rootNavController, "rootNavController");
        Intrinsics.checkNotNullParameter(authViewModel, "authViewModel");
        Intrinsics.checkNotNullParameter(serviceViewModel, "serviceViewModel");
        Composer startRestartGroup = composer.startRestartGroup(766887481);
        ComposerKt.sourceInformation(startRestartGroup, "C(MainScreen)N(rootNavController,authViewModel,serviceViewModel)54@2440L23,55@2532L29,56@2590L32,58@2654L169,63@2845L35,64@2905L47,65@2959L435,57@2627L767:MainScreen.kt#398ajf");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(rootNavController) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? startRestartGroup.changed(authViewModel) : startRestartGroup.changedInstance(authViewModel) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= (i & 512) == 0 ? startRestartGroup.changed(serviceViewModel) : startRestartGroup.changedInstance(serviceViewModel) ? 256 : 128;
        }
        if (!startRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(766887481, i2, -1, "com.traffmonetizer.client.ui.main.MainScreen (MainScreen.kt:53)");
            }
            final NavHostController rememberNavController = NavHostControllerKt.rememberNavController(new Navigator[0], startRestartGroup, 0);
            final State collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(serviceViewModel.getTraffConnectionStatus(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, startRestartGroup, 0, 7);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1755495783, "CC(remember):MainScreen.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new SnackbarHostState();
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            final SnackbarHostState snackbarHostState = (SnackbarHostState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            composer2 = startRestartGroup;
            ScaffoldKt.m2564ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(257105661, true, new Function2() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit MainScreen$lambda$2;
                    MainScreen$lambda$2 = MainScreenKt.MainScreen$lambda$2(NavController.this, collectAsStateWithLifecycle, (Composer) obj, ((Integer) obj2).intValue());
                    return MainScreen$lambda$2;
                }
            }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(291929598, true, new Function2() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit MainScreen$lambda$3;
                    MainScreen$lambda$3 = MainScreenKt.MainScreen$lambda$3(NavHostController.this, (Composer) obj, ((Integer) obj2).intValue());
                    return MainScreen$lambda$3;
                }
            }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(326753535, true, new Function2() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit MainScreen$lambda$4;
                    MainScreen$lambda$4 = MainScreenKt.MainScreen$lambda$4(SnackbarHostState.this, (Composer) obj, ((Integer) obj2).intValue());
                    return MainScreen$lambda$4;
                }
            }, startRestartGroup, 54), null, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(511899848, true, new Function3() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Unit MainScreen$lambda$5;
                    MainScreen$lambda$5 = MainScreenKt.MainScreen$lambda$5(NavHostController.this, authViewModel, snackbarHostState, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    return MainScreen$lambda$5;
                }
            }, startRestartGroup, 54), composer2, 805309872, 497);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit MainScreen$lambda$6;
                    MainScreen$lambda$6 = MainScreenKt.MainScreen$lambda$6(NavController.this, authViewModel, serviceViewModel, i, (Composer) obj, ((Integer) obj2).intValue());
                    return MainScreen$lambda$6;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$2(final NavController navController, State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C59@2718L94,59@2668L145:MainScreen.kt#398ajf");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(257105661, i, -1, "com.traffmonetizer.client.ui.main.MainScreen.<anonymous> (MainScreen.kt:59)");
            }
            ConnectionStatusEntity MainScreen$lambda$0 = MainScreen$lambda$0(state);
            ComposerKt.sourceInformationMarkerStart(composer, -2029560805, "CC(remember):MainScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(navController);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function0() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit MainScreen$lambda$2$0$0;
                        MainScreen$lambda$2$0$0 = MainScreenKt.MainScreen$lambda$2$0$0(NavController.this);
                        return MainScreen$lambda$2$0$0;
                    }
                };
                composer.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            MainTopBar(MainScreen$lambda$0, (Function0) rememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$2$0$0(NavController navController) {
        NavController.navigate$default(navController, Screen.ConnectionCheckScreen.INSTANCE.getRoute(), (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$3(NavHostController navHostController, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C63@2847L31:MainScreen.kt#398ajf");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(291929598, i, -1, "com.traffmonetizer.client.ui.main.MainScreen.<anonymous> (MainScreen.kt:63)");
            }
            MainBottomBar(navHostController, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$4(SnackbarHostState snackbarHostState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C64@2907L43:MainScreen.kt#398ajf");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(326753535, i, -1, "com.traffmonetizer.client.ui.main.MainScreen.<anonymous> (MainScreen.kt:64)");
            }
            SnackbarHostKt.SnackbarHost(snackbarHostState, null, null, composer, 6, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$5(NavHostController navHostController, final AuthViewModel authViewModel, final SnackbarHostState snackbarHostState, PaddingValues innerPadding, Composer composer, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(innerPadding, "innerPadding");
        ComposerKt.sourceInformation(composer, "CN(innerPadding)70@3157L231,66@2985L403:MainScreen.kt#398ajf");
        if ((i & 6) == 0) {
            i2 = i | (composer.changed(innerPadding) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (!composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(511899848, i2, -1, "com.traffmonetizer.client.ui.main.MainScreen.<anonymous> (MainScreen.kt:66)");
            }
            String route = Screen.HomeTab.INSTANCE.getRoute();
            Modifier padding = PaddingKt.padding(Modifier.INSTANCE, innerPadding);
            ComposerKt.sourceInformationMarkerStart(composer, -1985478545, "CC(remember):MainScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(authViewModel);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function1() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit MainScreen$lambda$5$0$0;
                        MainScreen$lambda$5$0$0 = MainScreenKt.MainScreen$lambda$5$0$0(AuthViewModel.this, snackbarHostState, (NavGraphBuilder) obj);
                        return MainScreen$lambda$5$0$0;
                    }
                };
                composer.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            NavHostKt.NavHost(navHostController, route, padding, null, null, null, null, null, null, null, (Function1) rememberedValue, composer, 0, 0, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$5$0$0(final AuthViewModel authViewModel, final SnackbarHostState snackbarHostState, NavGraphBuilder NavHost) {
        Intrinsics.checkNotNullParameter(NavHost, "$this$NavHost");
        NavGraphBuilderKt.composable$default(NavHost, Screen.HomeTab.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableSingletons$MainScreenKt.INSTANCE.m8039getLambda$650259003$app_productionRelease(), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.SettingsTab.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(-950249490, true, new Function4() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                Unit MainScreen$lambda$5$0$0$0;
                MainScreen$lambda$5$0$0$0 = MainScreenKt.MainScreen$lambda$5$0$0$0(AuthViewModel.this, snackbarHostState, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                return MainScreen$lambda$5$0$0$0;
            }
        }), 254, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$5$0$0$0(AuthViewModel authViewModel, SnackbarHostState snackbarHostState, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)75@3316L48:MainScreen.kt#398ajf");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-950249490, i, -1, "com.traffmonetizer.client.ui.main.MainScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainScreen.kt:75)");
        }
        SettingsScreenKt.SettingsScreen(authViewModel, snackbarHostState, null, composer, AuthViewModel.$stable | 48, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    public static final void MainBottomBar(final NavController tabNavController, Composer composer, final int i) {
        int i2;
        NavDestination destination;
        Intrinsics.checkNotNullParameter(tabNavController, "tabNavController");
        Composer startRestartGroup = composer.startRestartGroup(-473872848);
        ComposerKt.sourceInformation(startRestartGroup, "C(MainBottomBar)N(tabNavController)83@3509L30,86@3620L981,86@3606L995:MainScreen.kt#398ajf");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(tabNavController) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (!startRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-473872848, i2, -1, "com.traffmonetizer.client.ui.main.MainBottomBar (MainScreen.kt:82)");
            }
            NavBackStackEntry MainBottomBar$lambda$0 = MainBottomBar$lambda$0(NavHostControllerKt.currentBackStackEntryAsState(tabNavController, startRestartGroup, i2 & 14));
            final String route = (MainBottomBar$lambda$0 == null || (destination = MainBottomBar$lambda$0.getDestination()) == null) ? null : destination.getRoute();
            NavigationBarKt.m2429NavigationBarHsRjFd4(null, 0L, 0L, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1157114281, true, new Function3() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Unit MainBottomBar$lambda$1;
                    MainBottomBar$lambda$1 = MainScreenKt.MainBottomBar$lambda$1(route, tabNavController, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    return MainBottomBar$lambda$1;
                }
            }, startRestartGroup, 54), startRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit MainBottomBar$lambda$2;
                    MainBottomBar$lambda$2 = MainScreenKt.MainBottomBar$lambda$2(NavController.this, i, (Composer) obj, ((Integer) obj2).intValue());
                    return MainBottomBar$lambda$2;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainBottomBar$lambda$1(String str, final NavController navController, RowScope NavigationBar, Composer composer, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(NavigationBar, "$this$NavigationBar");
        ComposerKt.sourceInformation(composer, "C91@3875L213,87@3630L468,102@4368L217,98@4107L488:MainScreen.kt#398ajf");
        if ((i & 6) == 0) {
            i2 = i | (composer.changed(NavigationBar) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (!composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1157114281, i2, -1, "com.traffmonetizer.client.ui.main.MainBottomBar.<anonymous> (MainScreen.kt:87)");
            }
            boolean areEqual = Intrinsics.areEqual(str, Screen.HomeTab.INSTANCE.getRoute());
            ComposerKt.sourceInformationMarkerStart(composer, 409713996, "CC(remember):MainScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(navController);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function0() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit MainBottomBar$lambda$1$0$0;
                        MainBottomBar$lambda$1$0$0 = MainScreenKt.MainBottomBar$lambda$1$0$0(NavController.this);
                        return MainBottomBar$lambda$1$0$0;
                    }
                };
                composer.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            int i3 = (i2 & 14) | 1575936;
            NavigationBarKt.NavigationBarItem(NavigationBar, areEqual, (Function0) rememberedValue, ComposableSingletons$MainScreenKt.INSTANCE.getLambda$990835516$app_productionRelease(), null, false, ComposableSingletons$MainScreenKt.INSTANCE.m8038getLambda$255144935$app_productionRelease(), false, null, null, composer, i3, 472);
            boolean areEqual2 = Intrinsics.areEqual(str, Screen.SettingsTab.INSTANCE.getRoute());
            ComposerKt.sourceInformationMarkerStart(composer, 409729776, "CC(remember):MainScreen.kt#9igjgp");
            boolean changedInstance2 = composer.changedInstance(navController);
            Object rememberedValue2 = composer.rememberedValue();
            if (changedInstance2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = new Function0() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit MainBottomBar$lambda$1$1$0;
                        MainBottomBar$lambda$1$1$0 = MainScreenKt.MainBottomBar$lambda$1$1$0(NavController.this);
                        return MainBottomBar$lambda$1$1$0;
                    }
                };
                composer.updateRememberedValue(rememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            NavigationBarKt.NavigationBarItem(NavigationBar, areEqual2, (Function0) rememberedValue2, ComposableSingletons$MainScreenKt.INSTANCE.getLambda$1981651507$app_productionRelease(), null, false, ComposableSingletons$MainScreenKt.INSTANCE.m8037getLambda$1404653616$app_productionRelease(), false, null, null, composer, i3, 472);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainBottomBar$lambda$1$0$0(final NavController navController) {
        navController.navigate(Screen.HomeTab.INSTANCE.getRoute(), new Function1() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit MainBottomBar$lambda$1$0$0$0;
                MainBottomBar$lambda$1$0$0$0 = MainScreenKt.MainBottomBar$lambda$1$0$0$0(NavController.this, (NavOptionsBuilder) obj);
                return MainBottomBar$lambda$1$0$0$0;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainBottomBar$lambda$1$0$0$0(NavController navController, NavOptionsBuilder navigate) {
        Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
        NavOptionsBuilder.popUpTo$default(navigate, navController.getGraph().getStartDestinationId(), (Function1) null, 2, (Object) null);
        navigate.setLaunchSingleTop(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainBottomBar$lambda$1$1$0(final NavController navController) {
        navController.navigate(Screen.SettingsTab.INSTANCE.getRoute(), new Function1() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit MainBottomBar$lambda$1$1$0$0;
                MainBottomBar$lambda$1$1$0$0 = MainScreenKt.MainBottomBar$lambda$1$1$0$0(NavController.this, (NavOptionsBuilder) obj);
                return MainBottomBar$lambda$1$1$0$0;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainBottomBar$lambda$1$1$0$0(NavController navController, NavOptionsBuilder navigate) {
        Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
        NavOptionsBuilder.popUpTo$default(navigate, navController.getGraph().getStartDestinationId(), (Function1) null, 2, (Object) null);
        navigate.setLaunchSingleTop(true);
        return Unit.INSTANCE;
    }

    public static final void MainTopBar(final ConnectionStatusEntity connectionStatus, final Function0<Unit> onCheckConnection, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(connectionStatus, "connectionStatus");
        Intrinsics.checkNotNullParameter(onCheckConnection, "onCheckConnection");
        Composer startRestartGroup = composer.startRestartGroup(2143483834);
        ComposerKt.sourceInformation(startRestartGroup, "C(MainTopBar)N(connectionStatus,onCheckConnection)122@4906L10,117@4721L666:MainScreen.kt#398ajf");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(connectionStatus) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(onCheckConnection) ? 32 : 16;
        }
        int i3 = i2;
        if (!startRestartGroup.shouldExecute((i3 & 19) != 18, i3 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2143483834, i3, -1, "com.traffmonetizer.client.ui.main.MainTopBar (MainScreen.kt:116)");
            }
            Modifier m879paddingVpY3zN4 = PaddingKt.m879paddingVpY3zN4(SizeKt.m916height3ABfNKs(WindowInsetsPaddingKt.windowInsetsPadding(BackgroundKt.m297backgroundbw27NRU$default(ShadowKt.m4190shadows4CzXII$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7399constructorimpl(12), null, false, 0L, 0L, 26, null), ColorKt.getAppBarColor(), null, 2, null), WindowInsets_androidKt.getStatusBars(WindowInsets.INSTANCE, startRestartGroup, 6)), Dp.m7399constructorimpl(52)), Dp.m7399constructorimpl(16), Dp.m7399constructorimpl(8));
            Arrangement.HorizontalOrVertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, startRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, m879paddingVpY3zN4);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(constructor);
            } else {
                startRestartGroup.useNode();
            }
            Composer m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
            Updater.m3912setimpl(m3905constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(m))) {
                m3905constructorimpl.updateRememberedValue(Integer.valueOf(m));
                m3905constructorimpl.apply(Integer.valueOf(m), setCompositeKeyHash);
            }
            Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 437469677, "C129@5162L37,128@5133L183,133@5325L56:MainScreen.kt#398ajf");
            ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.logo_font, startRestartGroup, 0), "", SizeKt.m932sizeVpY3zN4(Modifier.INSTANCE, Dp.m7399constructorimpl(180), Dp.m7399constructorimpl(41)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, startRestartGroup, 432, MenuKt.InTransitionDuration);
            ConnectionIndicatorKt.ConnectionIndicator(connectionStatus, onCheckConnection, startRestartGroup, i3 & 126);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.MainScreenKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit MainTopBar$lambda$1;
                    MainTopBar$lambda$1 = MainScreenKt.MainTopBar$lambda$1(ConnectionStatusEntity.this, onCheckConnection, i, (Composer) obj, ((Integer) obj2).intValue());
                    return MainTopBar$lambda$1;
                }
            });
        }
    }

    private static final ConnectionStatusEntity MainScreen$lambda$0(State<? extends ConnectionStatusEntity> state) {
        return state.getValue();
    }

    private static final NavBackStackEntry MainBottomBar$lambda$0(State<NavBackStackEntry> state) {
        return state.getValue();
    }
}
