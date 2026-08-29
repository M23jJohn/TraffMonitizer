package com.traffmonetizer.client.ui.main.home;

import android.app.Activity;
import androidx.activity.compose.LocalActivityKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
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
import com.traffmonetizer.client.traffmonetizer_repository.model.ProxyStatusEntity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: HomeScreen.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u001a!\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007²\u0006\n\u0010\b\u001a\u00020\tX\u008a\u0084\u0002²\u0006\f\u0010\n\u001a\u0004\u0018\u00010\u000bX\u008a\u0084\u0002"}, d2 = {"HomeScreen", "", "proxyViewModel", "Lcom/traffmonetizer/client/ui/main/home/ProxyViewModel;", "statsViewModel", "Lcom/traffmonetizer/client/ui/main/home/StatsViewModel;", "(Lcom/traffmonetizer/client/ui/main/home/ProxyViewModel;Lcom/traffmonetizer/client/ui/main/home/StatsViewModel;Landroidx/compose/runtime/Composer;II)V", "app_productionRelease", "proxyStatus", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity;", "statsStatus", "Lcom/traffmonetizer/client/ui/main/home/TraffStatsEntity;"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HomeScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$3(ProxyViewModel proxyViewModel, StatsViewModel statsViewModel, int i, int i2, Composer composer, int i3) {
        HomeScreen(proxyViewModel, statsViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r13v22 */
    /* JADX WARN: Type inference failed for: r13v23 */
    /* JADX WARN: Type inference failed for: r13v3, types: [boolean, int] */
    public static final void HomeScreen(ProxyViewModel proxyViewModel, StatsViewModel statsViewModel, Composer composer, final int i, final int i2) {
        int i3;
        final ProxyViewModel proxyViewModel2;
        Composer composer2;
        boolean z;
        String str;
        String str2;
        int i4;
        int i5;
        Composer composer3;
        int i6;
        int i7;
        CreationExtras.Empty empty;
        CreationExtras.Empty empty2;
        ?? r13;
        Composer composer4;
        int i8;
        int i9;
        final StatsViewModel statsViewModel2 = statsViewModel;
        Composer startRestartGroup = composer.startRestartGroup(1159305897);
        ComposerKt.sourceInformation(startRestartGroup, "C(HomeScreen)N(proxyViewModel,statsViewModel)24@1037L29,25@1115L29,27@1179L7,28@1191L910:HomeScreen.kt#92mpw2");
        if ((i & 6) == 0) {
            if ((i2 & 1) == 0) {
                if ((i & 8) == 0 ? startRestartGroup.changed(proxyViewModel) : startRestartGroup.changedInstance(proxyViewModel)) {
                    i9 = 4;
                    i3 = i9 | i;
                }
            }
            i9 = 2;
            i3 = i9 | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                if ((i & 64) == 0 ? startRestartGroup.changed(statsViewModel2) : startRestartGroup.changedInstance(statsViewModel2)) {
                    i8 = 32;
                    i3 |= i8;
                }
            }
            i8 = 16;
            i3 |= i8;
        }
        int i10 = i3;
        if (startRestartGroup.shouldExecute((i10 & 19) != 18, i10 & 1)) {
            startRestartGroup.startDefaults();
            ComposerKt.sourceInformation(startRestartGroup, "21@908L15,22@962L15");
            if ((i & 1) == 0 || startRestartGroup.getDefaultsInvalid()) {
                if ((i2 & 1) != 0) {
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1192010736, "CC(hiltViewModel)P(1)38@1622L7,43@1774L47,44@1833L54:HiltViewModel.kt#gplxbw");
                    ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent(startRestartGroup, LocalViewModelStoreOwner.$stable);
                    if (current == null) {
                        throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                    }
                    ViewModelProvider.Factory createHiltViewModelFactory = HiltViewModelKt.createHiltViewModelFactory(current, startRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1729797275, "CC(viewModel)P(3,2,1)56@2573L7,67@2980L63:ViewModel.kt#3tja67");
                    if (current instanceof HasDefaultViewModelProviderFactory) {
                        empty2 = ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras();
                    } else {
                        empty2 = CreationExtras.Empty.INSTANCE;
                    }
                    str2 = "CC(hiltViewModel)P(1)38@1622L7,43@1774L47,44@1833L54:HiltViewModel.kt#gplxbw";
                    i4 = 1192010736;
                    z = false;
                    str = "CC(viewModel)P(3,2,1)56@2573L7,67@2980L63:ViewModel.kt#3tja67";
                    ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(ProxyViewModel.class), current, (String) null, createHiltViewModelFactory, empty2, startRestartGroup, 0, 0);
                    Composer composer5 = startRestartGroup;
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    i5 = i10 & (-15);
                    proxyViewModel2 = (ProxyViewModel) viewModel;
                    composer3 = composer5;
                } else {
                    z = false;
                    str = "CC(viewModel)P(3,2,1)56@2573L7,67@2980L63:ViewModel.kt#3tja67";
                    str2 = "CC(hiltViewModel)P(1)38@1622L7,43@1774L47,44@1833L54:HiltViewModel.kt#gplxbw";
                    i4 = 1192010736;
                    i5 = i10;
                    proxyViewModel2 = proxyViewModel;
                    composer3 = startRestartGroup;
                }
                if ((i2 & 2) != 0) {
                    ComposerKt.sourceInformationMarkerStart(composer3, i4, str2);
                    ViewModelStoreOwner current2 = LocalViewModelStoreOwner.INSTANCE.getCurrent(composer3, LocalViewModelStoreOwner.$stable);
                    if (current2 == null) {
                        throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                    }
                    ViewModelProvider.Factory createHiltViewModelFactory2 = HiltViewModelKt.createHiltViewModelFactory(current2, composer3, z ? 1 : 0);
                    ComposerKt.sourceInformationMarkerStart(composer3, 1729797275, str);
                    if (current2 instanceof HasDefaultViewModelProviderFactory) {
                        empty = ((HasDefaultViewModelProviderFactory) current2).getDefaultViewModelCreationExtras();
                    } else {
                        empty = CreationExtras.Empty.INSTANCE;
                    }
                    Composer composer6 = composer3;
                    CreationExtras creationExtras = empty;
                    i6 = 1;
                    ViewModel viewModel2 = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(StatsViewModel.class), current2, (String) null, createHiltViewModelFactory2, creationExtras, composer6, 0, 0);
                    Composer composer7 = composer6;
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    statsViewModel2 = (StatsViewModel) viewModel2;
                    i7 = i5 & (-113);
                    composer4 = composer7;
                    r13 = z;
                } else {
                    i6 = 1;
                    i7 = i5;
                    composer4 = composer3;
                    r13 = z;
                }
            } else {
                startRestartGroup.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i10 &= -15;
                }
                if ((i2 & 2) != 0) {
                    i10 &= -113;
                }
                r13 = 0;
                i7 = i10;
                proxyViewModel2 = proxyViewModel;
                i6 = 1;
                composer4 = startRestartGroup;
            }
            composer4.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1159305897, i7, -1, "com.traffmonetizer.client.ui.main.home.HomeScreen (HomeScreen.kt:23)");
            }
            final State collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(proxyViewModel2.getTraffProxyStatus(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composer4, 0, 7);
            State collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(statsViewModel2.getStatsInfo(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composer4, 0, 7);
            ProvidableCompositionLocal<Activity> localActivity = LocalActivityKt.getLocalActivity();
            ComposerKt.sourceInformationMarkerStart(composer4, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = composer4.consume(localActivity);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            final Activity activity = (Activity) consume;
            float f = 20;
            Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(PaddingKt.m882paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7399constructorimpl(f), Dp.m7399constructorimpl(34), Dp.m7399constructorimpl(f), 0.0f, 8, null), 0.0f, i6, null);
            Alignment topCenter = Alignment.INSTANCE.getTopCenter();
            ComposerKt.sourceInformationMarkerStart(composer4, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(topCenter, r13);
            ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, r13));
            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer4, fillMaxSize$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            StatsViewModel statsViewModel3 = statsViewModel2;
            ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer4.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer4.startReusableNode();
            if (composer4.getInserting()) {
                composer4.createNode(constructor);
            } else {
                composer4.useNode();
            }
            Composer m3905constructorimpl = Updater.m3905constructorimpl(composer4);
            Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(m))) {
                m3905constructorimpl.updateRememberedValue(Integer.valueOf(m));
                m3905constructorimpl.apply(Integer.valueOf(m), setCompositeKeyHash);
            }
            Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer4, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer4, 1520796836, "C38@1439L656:HomeScreen.kt#92mpw2");
            Modifier m937widthInVpY3zN4$default = SizeKt.m937widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m7399constructorimpl(550), 1, null);
            ComposerKt.sourceInformationMarkerStart(composer4, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m2 = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composer4.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer4, m937widthInVpY3zN4$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer4.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer4.startReusableNode();
            if (composer4.getInserting()) {
                composer4.createNode(constructor2);
            } else {
                composer4.useNode();
            }
            Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer4);
            Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(m2))) {
                m3905constructorimpl2.updateRememberedValue(Integer.valueOf(m2));
                m3905constructorimpl2.apply(Integer.valueOf(m2), setCompositeKeyHash2);
            }
            Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer4, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer4, 983083260, "C41@1523L562:HomeScreen.kt#92mpw2");
            ComposerKt.sourceInformationMarkerStart(composer4, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
            ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m3 = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
            CompositionLocalMap currentCompositionLocalMap3 = composer4.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composer4, companion);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer4.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer4.startReusableNode();
            if (composer4.getInserting()) {
                composer4.createNode(constructor3);
            } else {
                composer4.useNode();
            }
            Composer m3905constructorimpl3 = Updater.m3905constructorimpl(composer4);
            Updater.m3912setimpl(m3905constructorimpl3, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl3.getInserting() || !Intrinsics.areEqual(m3905constructorimpl3.rememberedValue(), Integer.valueOf(m3))) {
                m3905constructorimpl3.updateRememberedValue(Integer.valueOf(m3));
                m3905constructorimpl3.apply(Integer.valueOf(m3), setCompositeKeyHash3);
            }
            Updater.m3912setimpl(m3905constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer4, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer4, -655108083, "C42@1548L77,45@1642L30,48@1779L274,46@1689L382:HomeScreen.kt#92mpw2");
            StatsViewKt.HomeStatsView(HomeScreen$lambda$1(collectAsStateWithLifecycle2), composer4, 0);
            SpacerKt.Spacer(SizeKt.m916height3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(18)), composer4, 6);
            ProxyStatusEntity HomeScreen$lambda$0 = HomeScreen$lambda$0(collectAsStateWithLifecycle);
            ComposerKt.sourceInformationMarkerStart(composer4, 948705949, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changed = ((((i7 & 14) ^ 6) > 4 && composer4.changedInstance(proxyViewModel2)) || (6 & i7) == 4) | composer4.changed(collectAsStateWithLifecycle) | composer4.changedInstance(activity);
            Object rememberedValue = composer4.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function0() { // from class: com.traffmonetizer.client.ui.main.home.HomeScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit HomeScreen$lambda$2$0$0$0$0;
                        HomeScreen$lambda$2$0$0$0$0 = HomeScreenKt.HomeScreen$lambda$2$0$0$0$0(ProxyViewModel.this, activity, collectAsStateWithLifecycle);
                        return HomeScreen$lambda$2$0$0$0$0;
                    }
                };
                composer4.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ProxyViewKt.ProxyButton(HomeScreen$lambda$0, (Function0) rememberedValue, composer4, 0);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            statsViewModel2 = statsViewModel3;
            composer2 = composer4;
        } else {
            startRestartGroup.skipToGroupEnd();
            proxyViewModel2 = proxyViewModel;
            composer2 = startRestartGroup;
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.home.HomeScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit HomeScreen$lambda$3;
                    HomeScreen$lambda$3 = HomeScreenKt.HomeScreen$lambda$3(ProxyViewModel.this, statsViewModel2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    return HomeScreen$lambda$3;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$2$0$0$0$0(ProxyViewModel proxyViewModel, Activity activity, State state) {
        if (!(HomeScreen$lambda$0(state) instanceof ProxyStatusEntity.Stopped)) {
            proxyViewModel.stopProxy();
        } else {
            proxyViewModel.startProxy(activity);
        }
        return Unit.INSTANCE;
    }

    private static final ProxyStatusEntity HomeScreen$lambda$0(State<? extends ProxyStatusEntity> state) {
        return state.getValue();
    }

    private static final TraffStatsEntity HomeScreen$lambda$1(State<TraffStatsEntity> state) {
        return state.getValue();
    }
}
