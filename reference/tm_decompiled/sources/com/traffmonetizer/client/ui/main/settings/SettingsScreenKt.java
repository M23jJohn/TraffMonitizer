package com.traffmonetizer.client.ui.main.settings;

import android.content.Context;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.material3.SwitchKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.UriHandler;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.hilt.lifecycle.viewmodel.compose.HiltViewModelKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.compose.FlowExtKt;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import com.traffmonetizer.client.AuthViewModel;
import com.traffmonetizer.client.R;
import com.traffmonetizer.client.ui.main.home.TraffStatsEntity$$ExternalSyntheticBackport0;
import com.traffmonetizer.client.ui.theme.ColorKt;
import com.traffmonetizer.client.ui.theme.ThemeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SettingsScreen.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\b\u001a\u0093\u0001\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u00122\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u00122\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014H\u0003¢\u0006\u0002\u0010\u0019\u001a)\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u000b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u0012H\u0003¢\u0006\u0002\u0010\u001d\u001a#\u0010\u001e\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014H\u0003¢\u0006\u0002\u0010\u001f\u001a\r\u0010 \u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010!\u001a\r\u0010\"\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010!¨\u0006#²\u0006\f\u0010\f\u001a\u0004\u0018\u00010\rX\u008a\u0084\u0002²\u0006\n\u0010$\u001a\u00020%X\u008a\u0084\u0002"}, d2 = {"SettingsScreen", "", "authViewModel", "Lcom/traffmonetizer/client/AuthViewModel;", "snackbarHostState", "Landroidx/compose/material3/SnackbarHostState;", "settingsViewModel", "Lcom/traffmonetizer/client/ui/main/settings/SettingsViewModel;", "(Lcom/traffmonetizer/client/AuthViewModel;Landroidx/compose/material3/SnackbarHostState;Lcom/traffmonetizer/client/ui/main/settings/SettingsViewModel;Landroidx/compose/runtime/Composer;II)V", "SettingsScreenContent", "wifiOnly", "", "token", "", "deviceName", "Lcom/traffmonetizer/client/ui/main/settings/DeviceNameState;", "debugModeActive", "onWifiOnlyChanged", "Lkotlin/Function1;", "onResetToken", "Lkotlin/Function0;", "onEditDeviceName", "onSubmitDeviceName", "onDebugModeChanged", "onSendLogs", "(ZLjava/lang/String;Lcom/traffmonetizer/client/ui/main/settings/DeviceNameState;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "WifiOnlySwitch", "value", "onChanged", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "TokenSection", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MoreSettings", "(Landroidx/compose/runtime/Composer;I)V", "SettingsScreenPreview", "app_productionRelease", "settingsState", "Lcom/traffmonetizer/client/ui/main/settings/SettingsState;"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SettingsScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MoreSettings$lambda$2(int i, Composer composer, int i2) {
        MoreSettings(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$9(AuthViewModel authViewModel, SnackbarHostState snackbarHostState, SettingsViewModel settingsViewModel, int i, int i2, Composer composer, int i3) {
        SettingsScreen(authViewModel, snackbarHostState, settingsViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreenContent$lambda$1(boolean z, String str, DeviceNameState deviceNameState, boolean z2, Function1 function1, Function0 function0, Function0 function02, Function1 function12, Function1 function13, Function0 function03, int i, Composer composer, int i2) {
        SettingsScreenContent(z, str, deviceNameState, z2, function1, function0, function02, function12, function13, function03, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreenPreview$lambda$0(int i, Composer composer, int i2) {
        SettingsScreenPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TokenSection$lambda$1(String str, Function0 function0, int i, Composer composer, int i2) {
        TokenSection(str, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit WifiOnlySwitch$lambda$1(boolean z, Function1 function1, int i, Composer composer, int i2) {
        WifiOnlySwitch(z, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void SettingsScreen(final AuthViewModel authViewModel, final SnackbarHostState snackbarHostState, SettingsViewModel settingsViewModel, Composer composer, final int i, final int i2) {
        int i3;
        final SettingsViewModel settingsViewModel2;
        CreationExtras.Empty empty;
        int i4;
        boolean z;
        Object rememberedValue;
        Context context;
        int i5;
        boolean changedInstance;
        Object rememberedValue2;
        int i6;
        int i7;
        final Context context2;
        int i8;
        boolean wifiOnly;
        String SettingsScreen$lambda$0;
        boolean z2;
        Object rememberedValue3;
        boolean z3;
        Object rememberedValue4;
        boolean z4;
        Object rememberedValue5;
        boolean z5;
        Object rememberedValue6;
        boolean z6;
        boolean z7;
        Object rememberedValue7;
        boolean changedInstance2;
        Object rememberedValue8;
        int i9;
        SettingsViewModel settingsViewModel3 = settingsViewModel;
        Intrinsics.checkNotNullParameter(authViewModel, "authViewModel");
        Intrinsics.checkNotNullParameter(snackbarHostState, "snackbarHostState");
        Composer startRestartGroup = composer.startRestartGroup(-1872659697);
        ComposerKt.sourceInformation(startRestartGroup, "C(SettingsScreen)N(authViewModel,snackbarHostState,settingsViewModel)48@2132L29,49@2219L29,51@2266L24,52@2322L7,54@2356L339,54@2335L360,70@2922L37,71@2984L26,72@3039L38,73@3108L39,74@3178L38,75@3239L39,65@2701L583:SettingsScreen.kt#euo8mi");
        if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? startRestartGroup.changed(authViewModel) : startRestartGroup.changedInstance(authViewModel) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= startRestartGroup.changed(snackbarHostState) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            if ((i2 & 4) == 0) {
                if ((i & 512) == 0 ? startRestartGroup.changed(settingsViewModel3) : startRestartGroup.changedInstance(settingsViewModel3)) {
                    i9 = 256;
                    i3 |= i9;
                }
            }
            i9 = 128;
            i3 |= i9;
        }
        boolean z8 = true;
        if (startRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            startRestartGroup.startDefaults();
            ComposerKt.sourceInformation(startRestartGroup, "46@2075L15");
            if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                startRestartGroup.skipToGroupEnd();
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
            } else if ((i2 & 4) != 0) {
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1192010736, "CC(hiltViewModel)P(1)38@1622L7,43@1774L47,44@1833L54:HiltViewModel.kt#gplxbw");
                ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent(startRestartGroup, LocalViewModelStoreOwner.$stable);
                if (current == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                }
                ViewModelProvider.Factory createHiltViewModelFactory = HiltViewModelKt.createHiltViewModelFactory(current, startRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1729797275, "CC(viewModel)P(3,2,1)56@2573L7,67@2980L63:ViewModel.kt#3tja67");
                if (current instanceof HasDefaultViewModelProviderFactory) {
                    empty = ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras();
                } else {
                    empty = CreationExtras.Empty.INSTANCE;
                }
                i4 = 256;
                z = false;
                ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(SettingsViewModel.class), current, (String) null, createHiltViewModelFactory, empty, startRestartGroup, 0, 0);
                startRestartGroup = startRestartGroup;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                settingsViewModel3 = (SettingsViewModel) viewModel;
                i3 &= -897;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1872659697, i3, -1, "com.traffmonetizer.client.ui.main.settings.SettingsScreen (SettingsScreen.kt:47)");
                }
                State collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(authViewModel.getToken(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, startRestartGroup, 0, 7);
                State collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(settingsViewModel3.getSettingsState(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, startRestartGroup, 0, 7);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 683737348, "CC(remember):Effects.kt#9igjgp");
                rememberedValue = startRestartGroup.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    rememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, startRestartGroup);
                    startRestartGroup.updateRememberedValue(rememberedValue);
                }
                CoroutineScope coroutineScope = (CoroutineScope) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object consume = startRestartGroup.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                context = (Context) consume;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34658014, "CC(remember):SettingsScreen.kt#9igjgp");
                i5 = (i3 & 896) ^ 384;
                changedInstance = ((i3 & 112) != 32 ? true : z) | (((i5 > i4 || !startRestartGroup.changedInstance(settingsViewModel3)) && (i3 & 384) != i4) ? z : true) | startRestartGroup.changedInstance(coroutineScope) | startRestartGroup.changedInstance(context);
                rememberedValue2 = startRestartGroup.rememberedValue();
                if (!changedInstance || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    settingsViewModel2 = settingsViewModel3;
                    i6 = i3;
                    i7 = i4;
                    context2 = context;
                    i8 = 4;
                    rememberedValue2 = (Function2) new SettingsScreenKt$SettingsScreen$1$1(settingsViewModel2, coroutineScope, snackbarHostState, context2, null);
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                } else {
                    settingsViewModel2 = settingsViewModel3;
                    i6 = i3;
                    i7 = i4;
                    context2 = context;
                    i8 = 4;
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                EffectsKt.LaunchedEffect((Object) true, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue2, startRestartGroup, 6);
                wifiOnly = SettingsScreen$lambda$1(collectAsStateWithLifecycle2).getWifiOnly();
                SettingsScreen$lambda$0 = SettingsScreen$lambda$0(collectAsStateWithLifecycle);
                if (SettingsScreen$lambda$0 == null) {
                    SettingsScreen$lambda$0 = "";
                }
                DeviceNameState deviceName = SettingsScreen$lambda$1(collectAsStateWithLifecycle2).getDeviceName();
                boolean debugMode = SettingsScreen$lambda$1(collectAsStateWithLifecycle2).getDebugMode();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34640204, "CC(remember):SettingsScreen.kt#9igjgp");
                z2 = ((i5 > i7 || !startRestartGroup.changedInstance(settingsViewModel2)) && (i6 & 384) != i7) ? z : true;
                rememberedValue3 = startRestartGroup.rememberedValue();
                if (!z2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue3 = new Function1() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            Unit SettingsScreen$lambda$3$0;
                            SettingsScreen$lambda$3$0 = SettingsScreenKt.SettingsScreen$lambda$3$0(SettingsViewModel.this, ((Boolean) obj).booleanValue());
                            return SettingsScreen$lambda$3$0;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue3);
                }
                Function1 function1 = (Function1) rememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34638231, "CC(remember):SettingsScreen.kt#9igjgp");
                z3 = ((i6 & 14) != i8 || ((i6 & 8) != 0 && startRestartGroup.changedInstance(authViewModel))) ? true : z;
                rememberedValue4 = startRestartGroup.rememberedValue();
                if (!z3 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue4 = new Function0() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit SettingsScreen$lambda$4$0;
                            SettingsScreen$lambda$4$0 = SettingsScreenKt.SettingsScreen$lambda$4$0(AuthViewModel.this);
                            return SettingsScreen$lambda$4$0;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue4);
                }
                Function0 function0 = (Function0) rememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34636459, "CC(remember):SettingsScreen.kt#9igjgp");
                z4 = ((i5 > i7 || !startRestartGroup.changedInstance(settingsViewModel2)) && (i6 & 384) != i7) ? z : true;
                rememberedValue5 = startRestartGroup.rememberedValue();
                if (!z4 || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue5 = new Function0() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit SettingsScreen$lambda$5$0;
                            SettingsScreen$lambda$5$0 = SettingsScreenKt.SettingsScreen$lambda$5$0(SettingsViewModel.this);
                            return SettingsScreen$lambda$5$0;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue5);
                }
                Function0 function02 = (Function0) rememberedValue5;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34634250, "CC(remember):SettingsScreen.kt#9igjgp");
                z5 = ((i5 > 256 || !startRestartGroup.changedInstance(settingsViewModel2)) && (i6 & 384) != 256) ? z : true;
                rememberedValue6 = startRestartGroup.rememberedValue();
                if (!z5 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue6 = new Function1() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            Unit SettingsScreen$lambda$6$0;
                            SettingsScreen$lambda$6$0 = SettingsScreenKt.SettingsScreen$lambda$6$0(SettingsViewModel.this, (String) obj);
                            return SettingsScreen$lambda$6$0;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue6);
                }
                Function1 function12 = (Function1) rememberedValue6;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34632011, "CC(remember):SettingsScreen.kt#9igjgp");
                if (i5 > 256 || !startRestartGroup.changedInstance(settingsViewModel2)) {
                    z6 = wifiOnly;
                    if ((i6 & 384) != 256) {
                        z7 = z;
                        rememberedValue7 = startRestartGroup.rememberedValue();
                        if (!z7 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue7 = new Function1() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda11
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    Unit SettingsScreen$lambda$7$0;
                                    SettingsScreen$lambda$7$0 = SettingsScreenKt.SettingsScreen$lambda$7$0(SettingsViewModel.this, ((Boolean) obj).booleanValue());
                                    return SettingsScreen$lambda$7$0;
                                }
                            };
                            startRestartGroup.updateRememberedValue(rememberedValue7);
                        }
                        Function1 function13 = (Function1) rememberedValue7;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34630058, "CC(remember):SettingsScreen.kt#9igjgp");
                        if ((i5 > 256 || !startRestartGroup.changedInstance(settingsViewModel2)) && (i6 & 384) != 256) {
                            z8 = z;
                        }
                        changedInstance2 = z8 | startRestartGroup.changedInstance(context2);
                        rememberedValue8 = startRestartGroup.rememberedValue();
                        if (!changedInstance2 || rememberedValue8 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue8 = new Function0() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda12
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    Unit SettingsScreen$lambda$8$0;
                                    SettingsScreen$lambda$8$0 = SettingsScreenKt.SettingsScreen$lambda$8$0(SettingsViewModel.this, context2);
                                    return SettingsScreen$lambda$8$0;
                                }
                            };
                            startRestartGroup.updateRememberedValue(rememberedValue8);
                        }
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        Composer composer2 = startRestartGroup;
                        SettingsScreenContent(z6, SettingsScreen$lambda$0, deviceName, debugMode, function1, function0, function02, function12, function13, (Function0) rememberedValue8, composer2, 0);
                        startRestartGroup = composer2;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                } else {
                    z6 = wifiOnly;
                }
                z7 = true;
                rememberedValue7 = startRestartGroup.rememberedValue();
                if (!z7) {
                }
                rememberedValue7 = new Function1() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit SettingsScreen$lambda$7$0;
                        SettingsScreen$lambda$7$0 = SettingsScreenKt.SettingsScreen$lambda$7$0(SettingsViewModel.this, ((Boolean) obj).booleanValue());
                        return SettingsScreen$lambda$7$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue7);
                Function1 function132 = (Function1) rememberedValue7;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34630058, "CC(remember):SettingsScreen.kt#9igjgp");
                if (i5 > 256) {
                }
                z8 = z;
                changedInstance2 = z8 | startRestartGroup.changedInstance(context2);
                rememberedValue8 = startRestartGroup.rememberedValue();
                if (!changedInstance2) {
                }
                rememberedValue8 = new Function0() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit SettingsScreen$lambda$8$0;
                        SettingsScreen$lambda$8$0 = SettingsScreenKt.SettingsScreen$lambda$8$0(SettingsViewModel.this, context2);
                        return SettingsScreen$lambda$8$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue8);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Composer composer22 = startRestartGroup;
                SettingsScreenContent(z6, SettingsScreen$lambda$0, deviceName, debugMode, function1, function0, function02, function12, function132, (Function0) rememberedValue8, composer22, 0);
                startRestartGroup = composer22;
                if (ComposerKt.isTraceInProgress()) {
                }
            }
            i4 = 256;
            z = false;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            State collectAsStateWithLifecycle3 = FlowExtKt.collectAsStateWithLifecycle(authViewModel.getToken(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, startRestartGroup, 0, 7);
            State collectAsStateWithLifecycle22 = FlowExtKt.collectAsStateWithLifecycle(settingsViewModel3.getSettingsState(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, startRestartGroup, 0, 7);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 683737348, "CC(remember):Effects.kt#9igjgp");
            rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            }
            CoroutineScope coroutineScope2 = (CoroutineScope) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ProvidableCompositionLocal<Context> localContext2 = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume2 = startRestartGroup.consume(localContext2);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            context = (Context) consume2;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34658014, "CC(remember):SettingsScreen.kt#9igjgp");
            i5 = (i3 & 896) ^ 384;
            changedInstance = ((i3 & 112) != 32 ? true : z) | (((i5 > i4 || !startRestartGroup.changedInstance(settingsViewModel3)) && (i3 & 384) != i4) ? z : true) | startRestartGroup.changedInstance(coroutineScope2) | startRestartGroup.changedInstance(context);
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (changedInstance) {
            }
            settingsViewModel2 = settingsViewModel3;
            i6 = i3;
            i7 = i4;
            context2 = context;
            i8 = 4;
            rememberedValue2 = (Function2) new SettingsScreenKt$SettingsScreen$1$1(settingsViewModel2, coroutineScope2, snackbarHostState, context2, null);
            startRestartGroup.updateRememberedValue(rememberedValue2);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            EffectsKt.LaunchedEffect((Object) true, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue2, startRestartGroup, 6);
            wifiOnly = SettingsScreen$lambda$1(collectAsStateWithLifecycle22).getWifiOnly();
            SettingsScreen$lambda$0 = SettingsScreen$lambda$0(collectAsStateWithLifecycle3);
            if (SettingsScreen$lambda$0 == null) {
            }
            DeviceNameState deviceName2 = SettingsScreen$lambda$1(collectAsStateWithLifecycle22).getDeviceName();
            boolean debugMode2 = SettingsScreen$lambda$1(collectAsStateWithLifecycle22).getDebugMode();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34640204, "CC(remember):SettingsScreen.kt#9igjgp");
            if (i5 > i7) {
            }
            rememberedValue3 = startRestartGroup.rememberedValue();
            if (!z2) {
            }
            rememberedValue3 = new Function1() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit SettingsScreen$lambda$3$0;
                    SettingsScreen$lambda$3$0 = SettingsScreenKt.SettingsScreen$lambda$3$0(SettingsViewModel.this, ((Boolean) obj).booleanValue());
                    return SettingsScreen$lambda$3$0;
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue3);
            Function1 function14 = (Function1) rememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34638231, "CC(remember):SettingsScreen.kt#9igjgp");
            if ((i6 & 14) != i8) {
            }
            rememberedValue4 = startRestartGroup.rememberedValue();
            if (!z3) {
            }
            rememberedValue4 = new Function0() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit SettingsScreen$lambda$4$0;
                    SettingsScreen$lambda$4$0 = SettingsScreenKt.SettingsScreen$lambda$4$0(AuthViewModel.this);
                    return SettingsScreen$lambda$4$0;
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue4);
            Function0 function03 = (Function0) rememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34636459, "CC(remember):SettingsScreen.kt#9igjgp");
            if (i5 > i7) {
            }
            rememberedValue5 = startRestartGroup.rememberedValue();
            if (!z4) {
            }
            rememberedValue5 = new Function0() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit SettingsScreen$lambda$5$0;
                    SettingsScreen$lambda$5$0 = SettingsScreenKt.SettingsScreen$lambda$5$0(SettingsViewModel.this);
                    return SettingsScreen$lambda$5$0;
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue5);
            Function0 function022 = (Function0) rememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34634250, "CC(remember):SettingsScreen.kt#9igjgp");
            if (i5 > 256) {
            }
            rememberedValue6 = startRestartGroup.rememberedValue();
            if (!z5) {
            }
            rememberedValue6 = new Function1() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit SettingsScreen$lambda$6$0;
                    SettingsScreen$lambda$6$0 = SettingsScreenKt.SettingsScreen$lambda$6$0(SettingsViewModel.this, (String) obj);
                    return SettingsScreen$lambda$6$0;
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue6);
            Function1 function122 = (Function1) rememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34632011, "CC(remember):SettingsScreen.kt#9igjgp");
            if (i5 > 256) {
            }
            z6 = wifiOnly;
            if ((i6 & 384) != 256) {
            }
            z7 = true;
            rememberedValue7 = startRestartGroup.rememberedValue();
            if (!z7) {
            }
            rememberedValue7 = new Function1() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit SettingsScreen$lambda$7$0;
                    SettingsScreen$lambda$7$0 = SettingsScreenKt.SettingsScreen$lambda$7$0(SettingsViewModel.this, ((Boolean) obj).booleanValue());
                    return SettingsScreen$lambda$7$0;
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue7);
            Function1 function1322 = (Function1) rememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -34630058, "CC(remember):SettingsScreen.kt#9igjgp");
            if (i5 > 256) {
            }
            z8 = z;
            changedInstance2 = z8 | startRestartGroup.changedInstance(context2);
            rememberedValue8 = startRestartGroup.rememberedValue();
            if (!changedInstance2) {
            }
            rememberedValue8 = new Function0() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit SettingsScreen$lambda$8$0;
                    SettingsScreen$lambda$8$0 = SettingsScreenKt.SettingsScreen$lambda$8$0(SettingsViewModel.this, context2);
                    return SettingsScreen$lambda$8$0;
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue8);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Composer composer222 = startRestartGroup;
            SettingsScreenContent(z6, SettingsScreen$lambda$0, deviceName2, debugMode2, function14, function03, function022, function122, function1322, (Function0) rememberedValue8, composer222, 0);
            startRestartGroup = composer222;
            if (ComposerKt.isTraceInProgress()) {
            }
        } else {
            startRestartGroup.skipToGroupEnd();
            settingsViewModel2 = settingsViewModel3;
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit SettingsScreen$lambda$9;
                    SettingsScreen$lambda$9 = SettingsScreenKt.SettingsScreen$lambda$9(AuthViewModel.this, snackbarHostState, settingsViewModel2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    return SettingsScreen$lambda$9;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$3$0(SettingsViewModel settingsViewModel, boolean z) {
        settingsViewModel.setWifiOnly(z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$4$0(AuthViewModel authViewModel) {
        authViewModel.logout();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$5$0(SettingsViewModel settingsViewModel) {
        settingsViewModel.editDeviceName();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$6$0(SettingsViewModel settingsViewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        settingsViewModel.setDeviceName(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$7$0(SettingsViewModel settingsViewModel, boolean z) {
        settingsViewModel.setDebugMode(z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$8$0(SettingsViewModel settingsViewModel, Context context) {
        settingsViewModel.sendLogs(context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SettingsScreenContent(final boolean z, String str, final DeviceNameState deviceNameState, boolean z2, final Function1<? super Boolean, Unit> function1, final Function0<Unit> function0, final Function0<Unit> function02, final Function1<? super String, Unit> function12, Function1<? super Boolean, Unit> function13, Function0<Unit> function03, Composer composer, final int i) {
        int i2;
        String str2;
        boolean z3;
        final Function1<? super Boolean, Unit> function14 = function13;
        final Function0<Unit> function04 = function03;
        Composer startRestartGroup = composer.startRestartGroup(1930237217);
        ComposerKt.sourceInformation(startRestartGroup, "C(SettingsScreenContent)N(wifiOnly,token,deviceName,debugModeActive,onWifiOnlyChanged,onResetToken,onEditDeviceName,onSubmitDeviceName,onDebugModeChanged,onSendLogs)92@3666L1121:SettingsScreen.kt#euo8mi");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(str) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changed(deviceNameState) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= startRestartGroup.changed(z2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= startRestartGroup.changedInstance(function1) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= startRestartGroup.changedInstance(function0) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= startRestartGroup.changedInstance(function02) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= startRestartGroup.changedInstance(function12) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i2 |= startRestartGroup.changedInstance(function14) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & i) == 0) {
            i2 |= startRestartGroup.changedInstance(function04) ? 536870912 : 268435456;
        }
        if (!startRestartGroup.shouldExecute((306783379 & i2) != 306783378, i2 & 1)) {
            str2 = str;
            z3 = z2;
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1930237217, i2, -1, "com.traffmonetizer.client.ui.main.settings.SettingsScreenContent (SettingsScreen.kt:91)");
            }
            float f = 20;
            Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(PaddingKt.m882paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7399constructorimpl(f), Dp.m7399constructorimpl(32), Dp.m7399constructorimpl(f), 0.0f, 8, null), 0.0f, 1, null);
            Alignment topCenter = Alignment.INSTANCE.getTopCenter();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(topCenter, false);
            int i3 = i2;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, fillMaxSize$default);
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
            Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(m))) {
                m3905constructorimpl.updateRememberedValue(Integer.valueOf(m));
                m3905constructorimpl.apply(Integer.valueOf(m), setCompositeKeyHash);
            }
            Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -28536135, "C102@3914L867:SettingsScreen.kt#euo8mi");
            Modifier m937widthInVpY3zN4$default = SizeKt.m937widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m7399constructorimpl(550), 1, null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m2 = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, m937widthInVpY3zN4$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(constructor2);
            } else {
                startRestartGroup.useNode();
            }
            Composer m3905constructorimpl2 = Updater.m3905constructorimpl(startRestartGroup);
            Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(m2))) {
                m3905constructorimpl2.updateRememberedValue(Integer.valueOf(m2));
                m3905constructorimpl2.apply(Integer.valueOf(m2), setCompositeKeyHash2);
            }
            Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1583195887, "C105@3998L773:SettingsScreen.kt#euo8mi");
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), startRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m3 = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap3 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(startRestartGroup, companion);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(constructor3);
            } else {
                startRestartGroup.useNode();
            }
            Composer m3905constructorimpl3 = Updater.m3905constructorimpl(startRestartGroup);
            Updater.m3912setimpl(m3905constructorimpl3, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl3.getInserting() || !Intrinsics.areEqual(m3905constructorimpl3.rememberedValue(), Integer.valueOf(m3))) {
                m3905constructorimpl3.updateRememberedValue(Integer.valueOf(m3));
                m3905constructorimpl3.apply(Integer.valueOf(m3), setCompositeKeyHash3);
            }
            Updater.m3912setimpl(m3905constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1158645214, "C106@4023L63,107@4103L30,108@4150L171,113@4338L30,114@4385L14,115@4416L30,116@4463L56,117@4536L30,118@4583L174:SettingsScreen.kt#euo8mi");
            int i4 = i3 >> 9;
            WifiOnlySwitch(z, function1, startRestartGroup, (i3 & 14) | (i4 & 112));
            float f2 = 12;
            SpacerKt.Spacer(SizeKt.m916height3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(f2)), startRestartGroup, 6);
            int i5 = i3 >> 15;
            DeviceNameViewKt.DeviceName(deviceNameState, function02, function12, startRestartGroup, ((i3 >> 6) & 14) | (i5 & 112) | (i5 & 896));
            SpacerKt.Spacer(SizeKt.m916height3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(f2)), startRestartGroup, 6);
            MoreSettings(startRestartGroup, 0);
            SpacerKt.Spacer(SizeKt.m916height3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(f2)), startRestartGroup, 6);
            str2 = str;
            TokenSection(str2, function0, startRestartGroup, ((i3 >> 3) & 14) | ((i3 >> 12) & 112));
            SpacerKt.Spacer(SizeKt.m916height3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(24)), startRestartGroup, 6);
            int i6 = i4 & 14;
            int i7 = i3 >> 21;
            int i8 = i6 | (i7 & 112) | (i7 & 896);
            z3 = z2;
            function14 = function13;
            function04 = function03;
            DebugModeKt.DebugModeView(z3, function14, function04, startRestartGroup, i8);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
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
            final String str3 = str2;
            final boolean z4 = z3;
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit SettingsScreenContent$lambda$1;
                    SettingsScreenContent$lambda$1 = SettingsScreenKt.SettingsScreenContent$lambda$1(z, str3, deviceNameState, z4, function1, function0, function02, function12, function14, function04, i, (Composer) obj, ((Integer) obj2).intValue());
                    return SettingsScreenContent$lambda$1;
                }
            });
        }
    }

    private static final void WifiOnlySwitch(final boolean z, final Function1<? super Boolean, Unit> function1, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(1791368733);
        ComposerKt.sourceInformation(startRestartGroup, "C(WifiOnlySwitch)N(value,onChanged)130@4882L450:SettingsScreen.kt#euo8mi");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if (!startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1791368733, i2, -1, "com.traffmonetizer.client.ui.main.settings.WifiOnlySwitch (SettingsScreen.kt:129)");
            }
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, startRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, fillMaxWidth$default);
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2104621096, "C136@5085L43,137@5164L10,135@5060L135,141@5271L45,139@5204L122:SettingsScreen.kt#euo8mi");
            TextKt.m2849Text4IGK_g(StringResources_androidKt.stringResource(R.string.settings_wifi_only, startRestartGroup, 0), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(startRestartGroup, MaterialTheme.$stable).getBodyMedium(), startRestartGroup, 0, 0, 65534);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2007547122, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean z2 = (i2 & 112) == 32;
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function1() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit WifiOnlySwitch$lambda$0$0$0;
                        WifiOnlySwitch$lambda$0$0$0 = SettingsScreenKt.WifiOnlySwitch$lambda$0$0$0(Function1.this, ((Boolean) obj).booleanValue());
                        return WifiOnlySwitch$lambda$0$0$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            SwitchKt.Switch(z, (Function1) rememberedValue, null, null, false, null, null, startRestartGroup, i2 & 14, 124);
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
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit WifiOnlySwitch$lambda$1;
                    WifiOnlySwitch$lambda$1 = SettingsScreenKt.WifiOnlySwitch$lambda$1(z, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                    return WifiOnlySwitch$lambda$1;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit WifiOnlySwitch$lambda$0$0$0(Function1 function1, boolean z) {
        function1.invoke(Boolean.valueOf(z));
        return Unit.INSTANCE;
    }

    private static final void TokenSection(final String str, Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        final Function0<Unit> function02;
        Composer startRestartGroup = composer.startRestartGroup(673560194);
        ComposerKt.sourceInformation(startRestartGroup, "C(TokenSection)N(token,onResetToken)150@5420L835:SettingsScreen.kt#euo8mi");
        if ((i & 6) == 0) {
            i2 = i | (startRestartGroup.changed(str) ? 4 : 2);
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if (!startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            function02 = function0;
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(673560194, i2, -1, "com.traffmonetizer.client.ui.main.settings.TokenSection (SettingsScreen.kt:149)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), startRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, companion);
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
            Updater.m3912setimpl(m3905constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(m))) {
                m3905constructorimpl.updateRememberedValue(Integer.valueOf(m));
                m3905constructorimpl.apply(Integer.valueOf(m), setCompositeKeyHash);
            }
            Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1639946315, "C152@5462L39,153@5537L10,151@5437L166,157@5678L10,155@5612L96,159@5717L30,165@5975L18,160@5756L493:SettingsScreen.kt#euo8mi");
            int i3 = i2;
            TextKt.m2849Text4IGK_g(StringResources_androidKt.stringResource(R.string.settings_token, startRestartGroup, 0), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TextStyle.m6861copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(startRestartGroup, MaterialTheme.$stable).getBodyMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), startRestartGroup, 0, 0, 65534);
            TextKt.m2849Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(startRestartGroup, MaterialTheme.$stable).getBodySmall(), startRestartGroup, i3 & 14, 0, 65534);
            SpacerKt.Spacer(SizeKt.m916height3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(12)), startRestartGroup, 6);
            Modifier m309borderxT4_qwU = BorderKt.m309borderxT4_qwU(SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7399constructorimpl(36)), Dp.m7399constructorimpl(1), Color.INSTANCE.m4645getGray0d7_KjU(), RoundedCornerShapeKt.m1243RoundedCornerShape0680j_4(Dp.m7399constructorimpl(8)));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2025292066, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean z = (i3 & 112) == 32;
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                function02 = function0;
                rememberedValue = new Function0() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit TokenSection$lambda$0$0$0;
                        TokenSection$lambda$0$0$0 = SettingsScreenKt.TokenSection$lambda$0$0$0(Function0.this);
                        return TokenSection$lambda$0$0$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            } else {
                function02 = function0;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier m338clickableoSLSa3U$default = ClickableKt.m338clickableoSLSa3U$default(m309borderxT4_qwU, false, null, null, null, (Function0) rememberedValue, 15, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m2 = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, m338clickableoSLSa3U$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(constructor2);
            } else {
                startRestartGroup.useNode();
            }
            Composer m3905constructorimpl2 = Updater.m3905constructorimpl(startRestartGroup);
            Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(m2))) {
                m3905constructorimpl2.updateRememberedValue(Integer.valueOf(m2));
                m3905constructorimpl2.apply(Integer.valueOf(m2), setCompositeKeyHash2);
            }
            Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1483565134, "C169@6097L43,170@6180L10,168@6068L171:SettingsScreen.kt#euo8mi");
            TextKt.m2849Text4IGK_g(StringResources_androidKt.stringResource(R.string.reset_token_button, startRestartGroup, 0), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TextStyle.m6861copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(startRestartGroup, MaterialTheme.$stable).getLabelLarge(), ColorKt.getGreyColor(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), startRestartGroup, 0, 0, 65534);
            startRestartGroup = startRestartGroup;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
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
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit TokenSection$lambda$1;
                    TokenSection$lambda$1 = SettingsScreenKt.TokenSection$lambda$1(str, function02, i, (Composer) obj, ((Integer) obj2).intValue());
                    return TokenSection$lambda$1;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TokenSection$lambda$0$0$0(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    private static final void MoreSettings(Composer composer, final int i) {
        Composer composer2;
        Composer startRestartGroup = composer.startRestartGroup(828547083);
        ComposerKt.sourceInformation(startRestartGroup, "C(MoreSettings)178@6337L7,184@6545L138,179@6349L536:SettingsScreen.kt#euo8mi");
        if (!startRestartGroup.shouldExecute(i != 0, i & 1)) {
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(828547083, i, -1, "com.traffmonetizer.client.ui.main.settings.MoreSettings (SettingsScreen.kt:177)");
            }
            ProvidableCompositionLocal<UriHandler> localUriHandler = CompositionLocalsKt.getLocalUriHandler();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localUriHandler);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final UriHandler uriHandler = (UriHandler) consume;
            Modifier m296backgroundbw27NRU = BackgroundKt.m296backgroundbw27NRU(SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7399constructorimpl(36)), ColorKt.getPurpleAccentColor(), RoundedCornerShapeKt.m1243RoundedCornerShape0680j_4(Dp.m7399constructorimpl(8)));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1664805653, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(uriHandler);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function0() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit MoreSettings$lambda$0$0;
                        MoreSettings$lambda$0$0 = SettingsScreenKt.MoreSettings$lambda$0$0(UriHandler.this);
                        return MoreSettings$lambda$0$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier m338clickableoSLSa3U$default = ClickableKt.m338clickableoSLSa3U$default(m296backgroundbw27NRU, false, null, null, null, (Function0) rememberedValue, 15, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, m338clickableoSLSa3U$default);
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
            Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(m))) {
                m3905constructorimpl.updateRememberedValue(Integer.valueOf(m));
                m3905constructorimpl.apply(Integer.valueOf(m), setCompositeKeyHash);
            }
            Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1068451975, "C193@6822L10,191@6746L133:SettingsScreen.kt#euo8mi");
            composer2 = startRestartGroup;
            TextKt.m2849Text4IGK_g("More settings", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TextStyle.m6861copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(startRestartGroup, MaterialTheme.$stable).getLabelLarge(), Color.INSTANCE.m4652getWhite0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer2, 6, 0, 65534);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit MoreSettings$lambda$2;
                    MoreSettings$lambda$2 = SettingsScreenKt.MoreSettings$lambda$2(i, (Composer) obj, ((Integer) obj2).intValue());
                    return MoreSettings$lambda$2;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MoreSettings$lambda$0$0(UriHandler uriHandler) {
        uriHandler.openUri("https://app.traffmonetizer.com/account/profile");
        return Unit.INSTANCE;
    }

    private static final void SettingsScreenPreview(Composer composer, final int i) {
        Composer startRestartGroup = composer.startRestartGroup(777896112);
        ComposerKt.sourceInformation(startRestartGroup, "C(SettingsScreenPreview)202@6953L495:SettingsScreen.kt#euo8mi");
        if (!startRestartGroup.shouldExecute(i != 0, i & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(777896112, i, -1, "com.traffmonetizer.client.ui.main.settings.SettingsScreenPreview (SettingsScreen.kt:201)");
            }
            ThemeKt.TraffmonetizerTheme(ComposableSingletons$SettingsScreenKt.INSTANCE.m8058getLambda$165721512$app_productionRelease(), startRestartGroup, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit SettingsScreenPreview$lambda$0;
                    SettingsScreenPreview$lambda$0 = SettingsScreenKt.SettingsScreenPreview$lambda$0(i, (Composer) obj, ((Integer) obj2).intValue());
                    return SettingsScreenPreview$lambda$0;
                }
            });
        }
    }

    private static final String SettingsScreen$lambda$0(State<String> state) {
        return state.getValue();
    }

    private static final SettingsState SettingsScreen$lambda$1(State<SettingsState> state) {
        return state.getValue();
    }
}
