package com.traffmonetizer.client.ui.login;

import android.content.Context;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.MenuKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldDefaults;
import androidx.compose.material3.TextFieldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.UriHandler;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.navigation.NavController;
import com.traffmonetizer.client.AuthViewModel;
import com.traffmonetizer.client.R;
import com.traffmonetizer.client.ui.main.home.TraffStatsEntity$$ExternalSyntheticBackport0;
import com.traffmonetizer.client.ui.theme.ColorKt;
import com.traffmonetizer.client.ui.theme.ThemeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoginScreen.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a9\u0010\u0007\u001a\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0003¢\u0006\u0002\u0010\u000e\u001a9\u0010\u000f\u001a\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0003¢\u0006\u0002\u0010\u000e\u001a\u001a\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tH\u0002\u001a\r\u0010\u0014\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u0015\u001a\r\u0010\u0016\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0015¨\u0006\u0017²\u0006\f\u0010\b\u001a\u0004\u0018\u00010\tX\u008a\u0084\u0002²\u0006\n\u0010\u0018\u001a\u00020\tX\u008a\u008e\u0002²\u0006\f\u0010\u0019\u001a\u0004\u0018\u00010\tX\u008a\u008e\u0002"}, d2 = {"LoginScreen", "", "rootNavController", "Landroidx/navigation/NavController;", "authViewModel", "Lcom/traffmonetizer/client/AuthViewModel;", "(Landroidx/navigation/NavController;Lcom/traffmonetizer/client/AuthViewModel;Landroidx/compose/runtime/Composer;I)V", "LoginScreenContent", "token", "", "onTokenSubmitted", "Lkotlin/Function1;", "onRegister", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "TokenForm", "validateToken", "context", "Landroid/content/Context;", "input", "PrivacyPolicy", "(Landroidx/compose/runtime/Composer;I)V", "LoginScreenContentPreview", "app_productionRelease", "text", "errorMessage"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LoginScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LoginScreen$lambda$3(NavController navController, AuthViewModel authViewModel, int i, Composer composer, int i2) {
        LoginScreen(navController, authViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LoginScreenContent$lambda$1(String str, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        LoginScreenContent(str, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LoginScreenContentPreview$lambda$0(int i, Composer composer, int i2) {
        LoginScreenContentPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PrivacyPolicy$lambda$1(int i, Composer composer, int i2) {
        PrivacyPolicy(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TokenForm$lambda$8(String str, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        TokenForm(str, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void LoginScreen(final NavController rootNavController, final AuthViewModel authViewModel, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(rootNavController, "rootNavController");
        Intrinsics.checkNotNullParameter(authViewModel, "authViewModel");
        Composer startRestartGroup = composer.startRestartGroup(2087342140);
        ComposerKt.sourceInformation(startRestartGroup, "C(LoginScreen)N(rootNavController,authViewModel)53@2264L16,54@2318L7,58@2401L59,60@2475L152,56@2331L302:LoginScreen.kt#twq4g7");
        if ((i & 48) == 0) {
            i2 = ((i & 64) == 0 ? startRestartGroup.changed(authViewModel) : startRestartGroup.changedInstance(authViewModel) ? 32 : 16) | i;
        } else {
            i2 = i;
        }
        boolean z = true;
        if (!startRestartGroup.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2087342140, i2, -1, "com.traffmonetizer.client.ui.login.LoginScreen (LoginScreen.kt:52)");
            }
            State collectAsState = SnapshotStateKt.collectAsState(authViewModel.getToken(), null, startRestartGroup, 0, 1);
            ProvidableCompositionLocal<UriHandler> localUriHandler = CompositionLocalsKt.getLocalUriHandler();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localUriHandler);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final UriHandler uriHandler = (UriHandler) consume;
            String LoginScreen$lambda$0 = LoginScreen$lambda$0(collectAsState);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 186077879, "CC(remember):LoginScreen.kt#9igjgp");
            if ((i2 & 112) != 32 && ((i2 & 64) == 0 || !startRestartGroup.changedInstance(authViewModel))) {
                z = false;
            }
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function1() { // from class: com.traffmonetizer.client.ui.login.LoginScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit LoginScreen$lambda$1$0;
                        LoginScreen$lambda$1$0 = LoginScreenKt.LoginScreen$lambda$1$0(AuthViewModel.this, (String) obj);
                        return LoginScreen$lambda$1$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            Function1 function1 = (Function1) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 186080340, "CC(remember):LoginScreen.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(uriHandler);
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (changedInstance || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = new Function0() { // from class: com.traffmonetizer.client.ui.login.LoginScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit LoginScreen$lambda$2$0;
                        LoginScreen$lambda$2$0 = LoginScreenKt.LoginScreen$lambda$2$0(UriHandler.this);
                        return LoginScreen$lambda$2$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            LoginScreenContent(LoginScreen$lambda$0, function1, (Function0) rememberedValue2, startRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.login.LoginScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit LoginScreen$lambda$3;
                    LoginScreen$lambda$3 = LoginScreenKt.LoginScreen$lambda$3(NavController.this, authViewModel, i, (Composer) obj, ((Integer) obj2).intValue());
                    return LoginScreen$lambda$3;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LoginScreen$lambda$1$0(AuthViewModel authViewModel, String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        authViewModel.login(token);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LoginScreen$lambda$2$0(UriHandler uriHandler) {
        uriHandler.openUri("https://app.traffmonetizer.com/sign-up");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LoginScreenContent(final String str, final Function1<? super String, Unit> function1, final Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer startRestartGroup = composer.startRestartGroup(1370117166);
        ComposerKt.sourceInformation(startRestartGroup, "C(LoginScreenContent)N(token,onTokenSubmitted,onRegister)73@2786L1013,73@2777L1022:LoginScreen.kt#twq4g7");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        if (!startRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1370117166, i2, -1, "com.traffmonetizer.client.ui.login.LoginScreenContent (LoginScreen.kt:72)");
            }
            composer2 = startRestartGroup;
            ScaffoldKt.m2564ScaffoldTvnljyQ(null, null, null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(-352418371, true, new Function3() { // from class: com.traffmonetizer.client.ui.login.LoginScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Unit LoginScreenContent$lambda$0;
                    LoginScreenContent$lambda$0 = LoginScreenKt.LoginScreenContent$lambda$0(str, function1, function0, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    return LoginScreenContent$lambda$0;
                }
            }, startRestartGroup, 54), composer2, 805306368, 511);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.login.LoginScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit LoginScreenContent$lambda$1;
                    LoginScreenContent$lambda$1 = LoginScreenKt.LoginScreenContent$lambda$1(str, function1, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                    return LoginScreenContent$lambda$1;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LoginScreenContent$lambda$0(String str, Function1 function1, Function0 function0, PaddingValues innerPadding, Composer composer, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(innerPadding, "innerPadding");
        ComposerKt.sourceInformation(composer, "CN(innerPadding)74@2812L981:LoginScreen.kt#twq4g7");
        if ((i & 6) == 0) {
            i2 = i | (composer.changed(innerPadding) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (!composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-352418371, i2, -1, "com.traffmonetizer.client.ui.login.LoginScreenContent.<anonymous> (LoginScreen.kt:74)");
            }
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding(Modifier.INSTANCE, innerPadding), 0.0f, 1, null);
            Alignment topCenter = Alignment.INSTANCE.getTopCenter();
            ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(topCenter, false);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, fillMaxWidth$default);
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
            ComposerKt.sourceInformationMarkerStart(composer, 908472889, "C80@2996L787:LoginScreen.kt#twq4g7");
            float f = 24;
            Modifier m937widthInVpY3zN4$default = SizeKt.m937widthInVpY3zN4$default(PaddingKt.m879paddingVpY3zN4(Modifier.INSTANCE, Dp.m7399constructorimpl(f), Dp.m7399constructorimpl(8)), 0.0f, Dp.m7399constructorimpl(550), 1, null);
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
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
            Updater.m3912setimpl(m3905constructorimpl2, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(m2))) {
                m3905constructorimpl2.updateRememberedValue(Integer.valueOf(m2));
                m3905constructorimpl2.apply(Integer.valueOf(m2), setCompositeKeyHash2);
            }
            Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -712617194, "C85@3181L27,87@3262L37,86@3225L274,93@3516L27,94@3560L133,99@3710L27,100@3754L15:LoginScreen.kt#twq4g7");
            SpacerKt.Spacer(ColumnScope.CC.weight$default(columnScopeInstance, Modifier.INSTANCE, 2.0f, false, 2, null), composer, 0);
            ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.logo_font, composer, 0), "Logo", SizeKt.m916height3ABfNKs(PaddingKt.m880paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7399constructorimpl(f), 0.0f, 2, null), Dp.m7399constructorimpl(70)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer, 432, MenuKt.InTransitionDuration);
            SpacerKt.Spacer(ColumnScope.CC.weight$default(columnScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), composer, 0);
            TokenForm(str, function1, function0, composer, 0);
            SpacerKt.Spacer(ColumnScope.CC.weight$default(columnScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), composer, 0);
            PrivacyPolicy(composer, 0);
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

    private static final void TokenForm(final String str, final Function1<? super String, Unit> function1, Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        final Function0<Unit> function02 = function0;
        Composer startRestartGroup = composer.startRestartGroup(1884798801);
        ComposerKt.sourceInformation(startRestartGroup, "C(TokenForm)N(token,onTokenSubmitted,onRegister)112@3960L7,113@3984L40,114@4049L42,116@4124L140,123@4270L2928:LoginScreen.kt#twq4g7");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changedInstance(function02) ? 256 : 128;
        }
        if (!startRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1884798801, i2, -1, "com.traffmonetizer.client.ui.login.TokenForm (LoginScreen.kt:111)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final Context context = (Context) consume;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1184545959, "CC(remember):LoginScreen.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str == null ? "" : str, null, 2, null);
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            final MutableState mutableState = (MutableState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1184543877, "CC(remember):LoginScreen.kt#9igjgp");
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                startRestartGroup.updateRememberedValue(rememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1184541379, "CC(remember):LoginScreen.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(context) | ((i2 & 112) == 32);
            Object rememberedValue3 = startRestartGroup.rememberedValue();
            if (changedInstance || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                rememberedValue3 = new Function0() { // from class: com.traffmonetizer.client.ui.login.LoginScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit TokenForm$lambda$6$0;
                        TokenForm$lambda$6$0 = LoginScreenKt.TokenForm$lambda$6$0(context, function1, mutableState, mutableState2);
                        return TokenForm$lambda$6$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue3);
            }
            final Function0 function03 = (Function0) rememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1278701169, "C129@4466L42,135@4698L10,144@5109L375,126@4352L57,124@4287L1483,169@6107L41,175@6343L78,170@6157L480,185@6646L40,186@6695L497:LoginScreen.kt#twq4g7");
            String TokenForm$lambda$1 = TokenForm$lambda$1(mutableState);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 928585765, "CC(remember):LoginScreen.kt#9igjgp");
            boolean changed = startRestartGroup.changed(function03);
            Object rememberedValue4 = startRestartGroup.rememberedValue();
            if (changed || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                rememberedValue4 = new Function1() { // from class: com.traffmonetizer.client.ui.login.LoginScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit TokenForm$lambda$7$0$0;
                        TokenForm$lambda$7$0$0 = LoginScreenKt.TokenForm$lambda$7$0$0(Function0.this, (KeyboardActionScope) obj);
                        return TokenForm$lambda$7$0$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            KeyboardActions keyboardActions = new KeyboardActions((Function1) rememberedValue4, null, null, null, null, null, 62, null);
            boolean z = TokenForm$lambda$4(mutableState2) != null;
            TextStyle m6861copyp1EtxEg$default = TextStyle.m6861copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(startRestartGroup, MaterialTheme.$stable).getLabelLarge(), Color.INSTANCE.m4641getBlack0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null);
            TextFieldColors m2830colors0hiis_0 = TextFieldDefaults.INSTANCE.m2830colors0hiis_0(0L, 0L, 0L, 0L, Color.INSTANCE.m4650getTransparent0d7_KjU(), Color.INSTANCE.m4650getTransparent0d7_KjU(), 0L, Color.INSTANCE.m4650getTransparent0d7_KjU(), 0L, 0L, null, Color.INSTANCE.m4650getTransparent0d7_KjU(), Color.INSTANCE.m4650getTransparent0d7_KjU(), 0L, Color.INSTANCE.m4650getTransparent0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, startRestartGroup, 12804096, 25008, 0, 0, 3072, 2147460943, 4095);
            int i3 = i2;
            boolean z2 = z;
            float f = 8;
            Modifier m309borderxT4_qwU = BorderKt.m309borderxT4_qwU(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7399constructorimpl(1), TokenForm$lambda$4(mutableState2) != null ? Color.INSTANCE.m4649getRed0d7_KjU() : Color.INSTANCE.m4645getGray0d7_KjU(), RoundedCornerShapeKt.m1243RoundedCornerShape0680j_4(Dp.m7399constructorimpl(f)));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 928582132, "CC(remember):LoginScreen.kt#9igjgp");
            Object rememberedValue5 = startRestartGroup.rememberedValue();
            if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                rememberedValue5 = new Function1() { // from class: com.traffmonetizer.client.ui.login.LoginScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit TokenForm$lambda$7$1$0;
                        TokenForm$lambda$7$1$0 = LoginScreenKt.TokenForm$lambda$7$1$0(MutableState.this, (String) obj);
                        return TokenForm$lambda$7$1$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            TextFieldKt.TextField(TokenForm$lambda$1, (Function1<? super String, Unit>) rememberedValue5, m309borderxT4_qwU, false, false, m6861copyp1EtxEg$default, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$LoginScreenKt.INSTANCE.m8024getLambda$874819137$app_productionRelease(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$LoginScreenKt.INSTANCE.m8023getLambda$347425087$app_productionRelease(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, z2, (VisualTransformation) null, (KeyboardOptions) null, keyboardActions, true, 0, 0, (MutableInteractionSource) null, (Shape) null, m2830colors0hiis_0, startRestartGroup, 102236208, 12582912, 0, 3989144);
            if (TokenForm$lambda$4(mutableState2) != null) {
                startRestartGroup.startReplaceGroup(-1277263142);
                ComposerKt.sourceInformation(startRestartGroup, "166@6012L10,161@5819L269");
                Modifier m882paddingqDBjuR0$default = PaddingKt.m882paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7399constructorimpl(12), Dp.m7399constructorimpl(4), 0.0f, 0.0f, 12, null);
                String TokenForm$lambda$4 = TokenForm$lambda$4(mutableState2);
                Intrinsics.checkNotNull(TokenForm$lambda$4);
                TextKt.m2849Text4IGK_g(TokenForm$lambda$4, m882paddingqDBjuR0$default, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TextStyle.m6861copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(startRestartGroup, MaterialTheme.$stable).getBodySmall(), Color.INSTANCE.m4649getRed0d7_KjU(), TextUnitKt.getSp(11), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null), startRestartGroup, 48, 0, 65532);
            } else {
                startRestartGroup.startReplaceGroup(-1283044921);
            }
            startRestartGroup.endReplaceGroup();
            SpacerKt.Spacer(SizeKt.m916height3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(16)), startRestartGroup, 6);
            ButtonKt.Button(function03, SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7399constructorimpl(54)), false, RoundedCornerShapeKt.m1243RoundedCornerShape0680j_4(Dp.m7399constructorimpl(f)), ButtonDefaults.INSTANCE.m1964buttonColorsro_MJ88(ColorKt.getPurpleAccentColor(), 0L, 0L, 0L, startRestartGroup, ButtonDefaults.$stable << 12, 14), null, null, null, null, ComposableSingletons$LoginScreenKt.INSTANCE.m8021getLambda$1121317589$app_productionRelease(), startRestartGroup, 805306416, 484);
            SpacerKt.Spacer(SizeKt.m916height3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(4)), startRestartGroup, 6);
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment centerEnd = Alignment.INSTANCE.getCenterEnd();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(centerEnd, false);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m2 = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, fillMaxWidth$default);
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2127405381, "C189@6811L371:LoginScreen.kt#twq4g7");
            function02 = function0;
            ButtonKt.TextButton(function02, null, false, null, null, null, null, null, null, ComposableSingletons$LoginScreenKt.INSTANCE.m8022getLambda$1466003656$app_productionRelease(), startRestartGroup, ((i3 >> 6) & 14) | 805306368, 510);
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
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.login.LoginScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit TokenForm$lambda$8;
                    TokenForm$lambda$8 = LoginScreenKt.TokenForm$lambda$8(str, function1, function02, i, (Composer) obj, ((Integer) obj2).intValue());
                    return TokenForm$lambda$8;
                }
            });
        }
    }

    private static final String TokenForm$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String TokenForm$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TokenForm$lambda$6$0(Context context, Function1 function1, MutableState mutableState, MutableState mutableState2) {
        mutableState2.setValue(validateToken(context, TokenForm$lambda$1(mutableState)));
        if (TokenForm$lambda$4(mutableState2) == null) {
            function1.invoke(TokenForm$lambda$1(mutableState));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TokenForm$lambda$7$1$0(MutableState mutableState, String newText) {
        Intrinsics.checkNotNullParameter(newText, "newText");
        mutableState.setValue(newText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TokenForm$lambda$7$0$0(Function0 function0, KeyboardActionScope KeyboardActions) {
        Intrinsics.checkNotNullParameter(KeyboardActions, "$this$KeyboardActions");
        function0.invoke();
        return Unit.INSTANCE;
    }

    private static final String validateToken(Context context, String str) {
        if (str.length() == 0) {
            return context.getString(R.string.token_empty_error);
        }
        if (str.length() != 44) {
            return context.getString(R.string.token_invalid_error);
        }
        return null;
    }

    private static final void PrivacyPolicy(Composer composer, final int i) {
        Composer startRestartGroup = composer.startRestartGroup(-1727695318);
        ComposerKt.sourceInformation(startRestartGroup, "C(PrivacyPolicy)213@7550L7,214@7562L408:LoginScreen.kt#twq4g7");
        if (!startRestartGroup.shouldExecute(i != 0, i & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1727695318, i, -1, "com.traffmonetizer.client.ui.login.PrivacyPolicy (LoginScreen.kt:212)");
            }
            ProvidableCompositionLocal<UriHandler> localUriHandler = CompositionLocalsKt.getLocalUriHandler();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localUriHandler);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final UriHandler uriHandler = (UriHandler) consume;
            Alignment centerStart = Alignment.INSTANCE.getCenterStart();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(centerStart, false);
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 118759571, "C218@7666L95,217@7632L332:LoginScreen.kt#twq4g7");
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1935830845, "CC(remember):LoginScreen.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(uriHandler);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function0() { // from class: com.traffmonetizer.client.ui.login.LoginScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit PrivacyPolicy$lambda$0$0$0;
                        PrivacyPolicy$lambda$0$0$0 = LoginScreenKt.PrivacyPolicy$lambda$0$0$0(UriHandler.this);
                        return PrivacyPolicy$lambda$0$0$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ButtonKt.TextButton((Function0) rememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$LoginScreenKt.INSTANCE.getLambda$518136455$app_productionRelease(), startRestartGroup, 805306368, 510);
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
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.login.LoginScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit PrivacyPolicy$lambda$1;
                    PrivacyPolicy$lambda$1 = LoginScreenKt.PrivacyPolicy$lambda$1(i, (Composer) obj, ((Integer) obj2).intValue());
                    return PrivacyPolicy$lambda$1;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PrivacyPolicy$lambda$0$0$0(UriHandler uriHandler) {
        uriHandler.openUri("https://traffmonetizer.com/privacy-policy");
        return Unit.INSTANCE;
    }

    public static final void LoginScreenContentPreview(Composer composer, final int i) {
        Composer startRestartGroup = composer.startRestartGroup(524584864);
        ComposerKt.sourceInformation(startRestartGroup, "C(LoginScreenContentPreview)234@8034L161:LoginScreen.kt#twq4g7");
        if (!startRestartGroup.shouldExecute(i != 0, i & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(524584864, i, -1, "com.traffmonetizer.client.ui.login.LoginScreenContentPreview (LoginScreen.kt:233)");
            }
            ThemeKt.TraffmonetizerTheme(ComposableSingletons$LoginScreenKt.INSTANCE.getLambda$794888952$app_productionRelease(), startRestartGroup, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.login.LoginScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit LoginScreenContentPreview$lambda$0;
                    LoginScreenContentPreview$lambda$0 = LoginScreenKt.LoginScreenContentPreview$lambda$0(i, (Composer) obj, ((Integer) obj2).intValue());
                    return LoginScreenContentPreview$lambda$0;
                }
            });
        }
    }

    private static final String LoginScreen$lambda$0(State<String> state) {
        return state.getValue();
    }
}
