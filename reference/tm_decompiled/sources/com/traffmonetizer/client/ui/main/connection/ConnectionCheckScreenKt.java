package com.traffmonetizer.client.ui.main.connection;

import android.content.Context;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.layout.WindowInsets_androidKt;
import androidx.compose.material3.IconButtonDefaults;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextKt;
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
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.Dp;
import androidx.core.internal.view.SupportMenu;
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
import androidx.navigation.NavController;
import androidx.profileinstaller.ProfileVerifier;
import com.traffmonetizer.client.R;
import com.traffmonetizer.client.ui.main.home.TraffStatsEntity$$ExternalSyntheticBackport0;
import com.traffmonetizer.client.ui.theme.ColorKt;
import com.traffmonetizer.client.ui.theme.ThemeKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: ConnectionCheckScreen.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0007\u001a\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003¢\u0006\u0002\u0010\n\u001a(\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u0002`\u0013H\u0002\u001a\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0011H\u0002\u001a\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0012H\u0002\u001a\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0011H\u0002\u001a\u0018\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0012H\u0002\u001a\r\u0010\u001c\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u001d¨\u0006\u001e²\u0006\u0016\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u008a\u0084\u0002"}, d2 = {"ConnectionCheckScreen", "", "rootNavController", "Landroidx/navigation/NavController;", "connectionCheckViewModel", "Lcom/traffmonetizer/client/ui/main/connection/ConnectionCheckViewModel;", "(Landroidx/navigation/NavController;Lcom/traffmonetizer/client/ui/main/connection/ConnectionCheckViewModel;Landroidx/compose/runtime/Composer;II)V", "MainTopBar", "onBack", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "mapState", "Landroidx/compose/ui/text/AnnotatedString;", "context", "Landroid/content/Context;", "connectionCheckState", "", "Lcom/traffmonetizer/client/ui/main/connection/ConnectionCheckStep;", "Lcom/traffmonetizer/client/ui/main/connection/ConnectionCheckStatus;", "Lcom/traffmonetizer/client/ui/main/connection/ConnectionCheckState;", "getStepStyle", "Landroidx/compose/ui/text/SpanStyle;", "step", "getResultStyle", "result", "getStepString", "", "getResultString", "MainTopBarPreview", "(Landroidx/compose/runtime/Composer;I)V", "app_productionRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConnectionCheckScreenKt {

    /* compiled from: ConnectionCheckScreen.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ConnectionCheckStep.values().length];
            try {
                iArr[ConnectionCheckStep.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ConnectionCheckStep.NO_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ConnectionCheckStep.INTERNET_CONNECTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ConnectionCheckStep.LOAD_BALANCER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ConnectionCheckStep.SERVER_CONNECTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ConnectionCheckStep.TOKEN_CHECK.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ConnectionCheckStatus.values().length];
            try {
                iArr2[ConnectionCheckStatus.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[ConnectionCheckStatus.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[ConnectionCheckStatus.PROGRESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[ConnectionCheckStatus.EMPTY.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ConnectionCheckScreen$lambda$3(NavController navController, ConnectionCheckViewModel connectionCheckViewModel, int i, int i2, Composer composer, int i3) {
        ConnectionCheckScreen(navController, connectionCheckViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainTopBar$lambda$1(Function0 function0, int i, Composer composer, int i2) {
        MainTopBar(function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainTopBarPreview$lambda$0(int i, Composer composer, int i2) {
        MainTopBarPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0141  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void ConnectionCheckScreen(final NavController rootNavController, ConnectionCheckViewModel connectionCheckViewModel, Composer composer, final int i, final int i2) {
        int i3;
        Composer composer2;
        CreationExtras.Empty empty;
        int i4;
        final ConnectionCheckViewModel connectionCheckViewModel2 = connectionCheckViewModel;
        Intrinsics.checkNotNullParameter(rootNavController, "rootNavController");
        Composer startRestartGroup = composer.startRestartGroup(-355565058);
        ComposerKt.sourceInformation(startRestartGroup, "C(ConnectionCheckScreen)N(rootNavController,connectionCheckViewModel)52@2379L29,53@2440L7,55@2479L147,62@2633L547,54@2452L728:ConnectionCheckScreen.kt#8cw6nj");
        if ((i & 6) == 0) {
            i3 = (startRestartGroup.changedInstance(rootNavController) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                if ((i & 64) == 0 ? startRestartGroup.changed(connectionCheckViewModel2) : startRestartGroup.changedInstance(connectionCheckViewModel2)) {
                    i4 = 32;
                    i3 |= i4;
                }
            }
            i4 = 16;
            i3 |= i4;
        }
        int i5 = i3;
        if (startRestartGroup.shouldExecute((i5 & 19) != 18, i5 & 1)) {
            startRestartGroup.startDefaults();
            ComposerKt.sourceInformation(startRestartGroup, "50@2291L15");
            if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                startRestartGroup.skipToGroupEnd();
                if ((i2 & 2) != 0) {
                    i5 &= -113;
                }
            } else if ((i2 & 2) != 0) {
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
                ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(ConnectionCheckViewModel.class), current, (String) null, createHiltViewModelFactory, empty, startRestartGroup, 0, 0);
                composer2 = startRestartGroup;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                connectionCheckViewModel2 = (ConnectionCheckViewModel) viewModel;
                i5 &= -113;
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-355565058, i5, -1, "com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreen (ConnectionCheckScreen.kt:51)");
                }
                Composer composer3 = composer2;
                final State collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(connectionCheckViewModel2.getCheckState(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composer3, 0, 7);
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object consume = composer3.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                final Context context = (Context) consume;
                ScaffoldKt.m2564ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(698601146, true, new Function2() { // from class: com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        Unit ConnectionCheckScreen$lambda$1;
                        ConnectionCheckScreen$lambda$1 = ConnectionCheckScreenKt.ConnectionCheckScreen$lambda$1(NavController.this, (Composer) obj, ((Integer) obj2).intValue());
                        return ConnectionCheckScreen$lambda$1;
                    }
                }, composer3, 54), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(974466575, true, new Function3() { // from class: com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        Unit ConnectionCheckScreen$lambda$2;
                        ConnectionCheckScreen$lambda$2 = ConnectionCheckScreenKt.ConnectionCheckScreen$lambda$2(context, collectAsStateWithLifecycle, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        return ConnectionCheckScreen$lambda$2;
                    }
                }, composer3, 54), composer2, 805306416, 509);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            composer2 = startRestartGroup;
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            Composer composer32 = composer2;
            final State collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(connectionCheckViewModel2.getCheckState(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composer32, 0, 7);
            ProvidableCompositionLocal<Context> localContext2 = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composer32, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume2 = composer32.consume(localContext2);
            ComposerKt.sourceInformationMarkerEnd(composer32);
            final Context context2 = (Context) consume2;
            ScaffoldKt.m2564ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(698601146, true, new Function2() { // from class: com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit ConnectionCheckScreen$lambda$1;
                    ConnectionCheckScreen$lambda$1 = ConnectionCheckScreenKt.ConnectionCheckScreen$lambda$1(NavController.this, (Composer) obj, ((Integer) obj2).intValue());
                    return ConnectionCheckScreen$lambda$1;
                }
            }, composer32, 54), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(974466575, true, new Function3() { // from class: com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Unit ConnectionCheckScreen$lambda$2;
                    ConnectionCheckScreen$lambda$2 = ConnectionCheckScreenKt.ConnectionCheckScreen$lambda$2(context2, collectAsStateWithLifecycle2, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    return ConnectionCheckScreen$lambda$2;
                }
            }, composer32, 54), composer2, 805306416, 509);
            if (ComposerKt.isTraceInProgress()) {
            }
        } else {
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit ConnectionCheckScreen$lambda$3;
                    ConnectionCheckScreen$lambda$3 = ConnectionCheckScreenKt.ConnectionCheckScreen$lambda$3(NavController.this, connectionCheckViewModel2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    return ConnectionCheckScreen$lambda$3;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ConnectionCheckScreen$lambda$1(final NavController navController, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C57@2530L72,56@2493L123:ConnectionCheckScreen.kt#8cw6nj");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(698601146, i, -1, "com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreen.<anonymous> (ConnectionCheckScreen.kt:56)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 1340804834, "CC(remember):ConnectionCheckScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(navController);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function0() { // from class: com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit ConnectionCheckScreen$lambda$1$0$0;
                        ConnectionCheckScreen$lambda$1$0$0 = ConnectionCheckScreenKt.ConnectionCheckScreen$lambda$1$0$0(NavController.this);
                        return ConnectionCheckScreen$lambda$1$0$0;
                    }
                };
                composer.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            MainTopBar((Function0) rememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ConnectionCheckScreen$lambda$1$0$0(NavController navController) {
        navController.popBackStack();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ConnectionCheckScreen$lambda$2(Context context, State state, PaddingValues innerPadding, Composer composer, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(innerPadding, "innerPadding");
        ComposerKt.sourceInformation(composer, "CN(innerPadding)63@2659L515:ConnectionCheckScreen.kt#8cw6nj");
        if ((i & 6) == 0) {
            i2 = i | (composer.changed(innerPadding) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (!composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(974466575, i2, -1, "com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreen.<anonymous> (ConnectionCheckScreen.kt:63)");
            }
            Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(PaddingKt.padding(Modifier.INSTANCE, innerPadding), 0.0f, 1, null);
            Alignment topCenter = Alignment.INSTANCE.getTopCenter();
            ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(topCenter, false);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, fillMaxSize$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer m3905constructorimpl = Updater.m3905constructorimpl(composer);
            Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(m))) {
                m3905constructorimpl.updateRememberedValue(Integer.valueOf(m));
                m3905constructorimpl.apply(Integer.valueOf(m), setCompositeKeyHash);
            }
            Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -136115316, "C69@2842L322:ConnectionCheckScreen.kt#8cw6nj");
            Modifier m937widthInVpY3zN4$default = SizeKt.m937widthInVpY3zN4$default(PaddingKt.m878padding3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(16)), 0.0f, Dp.m7399constructorimpl(550), 1, null);
            ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m2 = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer, m937widthInVpY3zN4$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer);
            Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(m2))) {
                m3905constructorimpl2.updateRememberedValue(Integer.valueOf(m2));
                m3905constructorimpl2.apply(Integer.valueOf(m2), setCompositeKeyHash2);
            }
            Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -1715921907, "C77@3111L10,75@2995L155:ConnectionCheckScreen.kt#8cw6nj");
            TextKt.m2850TextIbK3jfQ(mapState(context, ConnectionCheckScreen$lambda$0(state)), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodyMedium(), composer, 0, 0, 131070);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MainTopBar(final Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer startRestartGroup = composer.startRestartGroup(1025289292);
        ComposerKt.sourceInformation(startRestartGroup, "C(MainTopBar)N(onBack)93@3436L10,88@3251L924:ConnectionCheckScreen.kt#8cw6nj");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (!startRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1025289292, i2, -1, "com.traffmonetizer.client.ui.main.connection.MainTopBar (ConnectionCheckScreen.kt:87)");
            }
            Modifier m880paddingVpY3zN4$default = PaddingKt.m880paddingVpY3zN4$default(SizeKt.m916height3ABfNKs(WindowInsetsPaddingKt.windowInsetsPadding(BackgroundKt.m297backgroundbw27NRU$default(ShadowKt.m4190shadows4CzXII$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7399constructorimpl(12), null, false, 0L, 0L, 26, null), ColorKt.getAppBarColor(), null, 2, null), WindowInsets_androidKt.getStatusBars(WindowInsets.INSTANCE, startRestartGroup, 6)), Dp.m7399constructorimpl(52)), 0.0f, Dp.m7399constructorimpl(8), 1, null);
            Arrangement.Horizontal start = Arrangement.INSTANCE.getStart();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, startRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, m880paddingVpY3zN4$default);
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1404742886, "C100@3670L40,103@3752L72,99@3636L354,112@3999L28,114@4061L41,115@4138L10,113@4036L133:ConnectionCheckScreen.kt#8cw6nj");
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1617253104, "CC(remember):ConnectionCheckScreen.kt#9igjgp");
            boolean z = (i2 & 14) == 4;
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function0() { // from class: com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit MainTopBar$lambda$0$0$0;
                        MainTopBar$lambda$0$0$0 = ConnectionCheckScreenKt.MainTopBar$lambda$0$0$0(Function0.this);
                        return MainTopBar$lambda$0$0$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            composer2 = startRestartGroup;
            IconButtonKt.IconButton((Function0) rememberedValue, null, false, IconButtonDefaults.INSTANCE.m2300iconButtonColorsro_MJ88(0L, ColorKt.getGreyColor(), 0L, 0L, composer2, IconButtonDefaults.$stable << 12, 13), null, ComposableSingletons$ConnectionCheckScreenKt.INSTANCE.m8043getLambda$1860359253$app_productionRelease(), composer2, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 22);
            SpacerKt.Spacer(SizeKt.m935width3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(6)), composer2, 6);
            TextKt.m2849Text4IGK_g(StringResources_androidKt.stringResource(R.string.check_connection, composer2, 0), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getTitleLarge(), composer2, 0, 0, 65534);
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
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit MainTopBar$lambda$1;
                    MainTopBar$lambda$1 = ConnectionCheckScreenKt.MainTopBar$lambda$1(Function0.this, i, (Composer) obj, ((Integer) obj2).intValue());
                    return MainTopBar$lambda$1;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainTopBar$lambda$0$0$0(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    private static final SpanStyle getStepStyle(ConnectionCheckStep connectionCheckStep) {
        int i = WhenMappings.$EnumSwitchMapping$0[connectionCheckStep.ordinal()];
        if (i == 1) {
            return new SpanStyle(Color.INSTANCE.m4649getRed0d7_KjU(), 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65534, (DefaultConstructorMarker) null);
        }
        if (i == 2) {
            return new SpanStyle(Color.INSTANCE.m4646getGreen0d7_KjU(), 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65534, (DefaultConstructorMarker) null);
        }
        return new SpanStyle(0L, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, SupportMenu.USER_MASK, (DefaultConstructorMarker) null);
    }

    private static final SpanStyle getResultStyle(ConnectionCheckStatus connectionCheckStatus) {
        int i = WhenMappings.$EnumSwitchMapping$1[connectionCheckStatus.ordinal()];
        if (i == 1) {
            return new SpanStyle(Color.INSTANCE.m4646getGreen0d7_KjU(), 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65534, (DefaultConstructorMarker) null);
        }
        if (i == 2) {
            return new SpanStyle(Color.INSTANCE.m4649getRed0d7_KjU(), 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65534, (DefaultConstructorMarker) null);
        }
        return new SpanStyle(0L, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, SupportMenu.USER_MASK, (DefaultConstructorMarker) null);
    }

    private static final String getStepString(Context context, ConnectionCheckStep connectionCheckStep) {
        int i;
        switch (WhenMappings.$EnumSwitchMapping$0[connectionCheckStep.ordinal()]) {
            case 1:
                i = R.string.errors_found;
                break;
            case 2:
                i = R.string.errors_not_found;
                break;
            case 3:
                i = R.string.checking_internet_connection;
                break;
            case 4:
                i = R.string.checking_load_balancer;
                break;
            case 5:
                i = R.string.checking_server_connection;
                break;
            case 6:
                i = R.string.checking_token;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        String string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    private static final String getResultString(Context context, ConnectionCheckStatus connectionCheckStatus) {
        int i = WhenMappings.$EnumSwitchMapping$1[connectionCheckStatus.ordinal()];
        if (i == 1) {
            String string = context.getString(R.string.connection_check_ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        if (i == 2) {
            String string2 = context.getString(R.string.connection_check_failed);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            return string2;
        }
        if (i == 3) {
            return " ";
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        return "";
    }

    private static final void MainTopBarPreview(Composer composer, final int i) {
        Composer startRestartGroup = composer.startRestartGroup(-258640132);
        ComposerKt.sourceInformation(startRestartGroup, "C(MainTopBarPreview)181@6379L81:ConnectionCheckScreen.kt#8cw6nj");
        if (!startRestartGroup.shouldExecute(i != 0, i & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-258640132, i, -1, "com.traffmonetizer.client.ui.main.connection.MainTopBarPreview (ConnectionCheckScreen.kt:180)");
            }
            ThemeKt.TraffmonetizerTheme(ComposableSingletons$ConnectionCheckScreenKt.INSTANCE.m8042getLambda$1072712108$app_productionRelease(), startRestartGroup, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.connection.ConnectionCheckScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit MainTopBarPreview$lambda$0;
                    MainTopBarPreview$lambda$0 = ConnectionCheckScreenKt.MainTopBarPreview$lambda$0(i, (Composer) obj, ((Integer) obj2).intValue());
                    return MainTopBarPreview$lambda$0;
                }
            });
        }
    }

    private static final AnnotatedString mapState(Context context, Map<ConnectionCheckStep, ? extends ConnectionCheckStatus> map) {
        AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, null);
        for (Map.Entry<ConnectionCheckStep, ? extends ConnectionCheckStatus> entry : map.entrySet()) {
            ConnectionCheckStep key = entry.getKey();
            ConnectionCheckStatus value = entry.getValue();
            int pushStyle = builder.pushStyle(getStepStyle(key));
            try {
                builder.append(getStepString(context, key));
                Unit unit = Unit.INSTANCE;
                builder.pop(pushStyle);
                if (value != ConnectionCheckStatus.EMPTY) {
                    builder.append(" ... ");
                    pushStyle = builder.pushStyle(getResultStyle(value));
                    try {
                        builder.append(getResultString(context, value));
                        Unit unit2 = Unit.INSTANCE;
                    } finally {
                    }
                }
                builder.append("\n");
            } finally {
            }
        }
        return builder.toAnnotatedString();
    }

    private static final Map<ConnectionCheckStep, ConnectionCheckStatus> ConnectionCheckScreen$lambda$0(State<? extends Map<ConnectionCheckStep, ? extends ConnectionCheckStatus>> state) {
        return (Map) state.getValue();
    }
}
