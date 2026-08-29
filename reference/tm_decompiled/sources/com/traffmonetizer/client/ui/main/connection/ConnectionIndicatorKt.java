package com.traffmonetizer.client.ui.main.connection;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import com.traffmonetizer.client.R;
import com.traffmonetizer.client.traffmonetizer_repository.model.ConnectionStatusEntity;
import com.traffmonetizer.client.ui.main.home.TraffStatsEntity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConnectionIndicator.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"ConnectionIndicator", "", "connectionStatus", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "onCheckConnection", "Lkotlin/Function0;", "(Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_productionRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConnectionIndicatorKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ConnectionIndicator$lambda$2(ConnectionStatusEntity connectionStatusEntity, Function0 function0, int i, Composer composer, int i2) {
        ConnectionIndicator(connectionStatusEntity, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void ConnectionIndicator(final ConnectionStatusEntity connectionStatus, final Function0<Unit> onCheckConnection, Composer composer, final int i) {
        int i2;
        long m4649getRed0d7_KjU;
        String stringResource;
        Intrinsics.checkNotNullParameter(connectionStatus, "connectionStatus");
        Intrinsics.checkNotNullParameter(onCheckConnection, "onCheckConnection");
        Composer startRestartGroup = composer.startRestartGroup(-61223328);
        ComposerKt.sourceInformation(startRestartGroup, "C(ConnectionIndicator)N(connectionStatus,onCheckConnection)31@1253L51,28@1111L1621:ConnectionIndicator.kt#8cw6nj");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(connectionStatus) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(onCheckConnection) ? 32 : 16;
        }
        if (!startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-61223328, i2, -1, "com.traffmonetizer.client.ui.main.connection.ConnectionIndicator (ConnectionIndicator.kt:27)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            boolean z = connectionStatus instanceof ConnectionStatusEntity.Failed;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1293932147, "CC(remember):ConnectionIndicator.kt#9igjgp");
            boolean z2 = (i2 & 112) == 32;
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function0() { // from class: com.traffmonetizer.client.ui.main.connection.ConnectionIndicatorKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit ConnectionIndicator$lambda$0$0;
                        ConnectionIndicator$lambda$0$0 = ConnectionIndicatorKt.ConnectionIndicator$lambda$0$0(Function0.this);
                        return ConnectionIndicator$lambda$0$0;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier m338clickableoSLSa3U$default = ClickableKt.m338clickableoSLSa3U$default(companion, z, null, null, null, (Function0) rememberedValue, 14, null);
            Alignment.Horizontal end = Alignment.INSTANCE.getEnd();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), end, startRestartGroup, 48);
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1934670419, "C37@1376L992:ConnectionIndicator.kt#8cw6nj");
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, startRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
            int m2 = TraffStatsEntity$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, companion2);
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
            Updater.m3912setimpl(m3905constructorimpl2, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(m2))) {
                m3905constructorimpl2.updateRememberedValue(Integer.valueOf(m2));
                m3905constructorimpl2.apply(Integer.valueOf(m2), setCompositeKeyHash2);
            }
            Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -557955360, "C40@1465L507,52@1985L28,59@2324L10,53@2026L332:ConnectionIndicator.kt#8cw6nj");
            Modifier m930size3ABfNKs = SizeKt.m930size3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(12));
            if (Intrinsics.areEqual(connectionStatus, ConnectionStatusEntity.Connected.INSTANCE)) {
                m4649getRed0d7_KjU = Color.INSTANCE.m4646getGreen0d7_KjU();
            } else if (Intrinsics.areEqual(connectionStatus, ConnectionStatusEntity.Disconnected.INSTANCE) || z) {
                m4649getRed0d7_KjU = Color.INSTANCE.m4649getRed0d7_KjU();
            } else {
                m4649getRed0d7_KjU = Color.INSTANCE.m4653getYellow0d7_KjU();
            }
            BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(m930size3ABfNKs, m4649getRed0d7_KjU, RoundedCornerShapeKt.getCircleShape()), startRestartGroup, 0);
            SpacerKt.Spacer(SizeKt.m935width3ABfNKs(Modifier.INSTANCE, Dp.m7399constructorimpl(4)), startRestartGroup, 6);
            if (connectionStatus instanceof ConnectionStatusEntity.Connected) {
                startRestartGroup.startReplaceGroup(-557337686);
                ComposerKt.sourceInformation(startRestartGroup, "55@2135L42");
                stringResource = StringResources_androidKt.stringResource(R.string.connection_online, startRestartGroup, 0);
                startRestartGroup.endReplaceGroup();
            } else {
                startRestartGroup.startReplaceGroup(-557250359);
                ComposerKt.sourceInformation(startRestartGroup, "57@2223L43");
                stringResource = StringResources_androidKt.stringResource(R.string.connection_offline, startRestartGroup, 0);
                startRestartGroup.endReplaceGroup();
            }
            TextKt.m2849Text4IGK_g(stringResource, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(startRestartGroup, MaterialTheme.$stable).getBodySmall(), startRestartGroup, 0, 0, 65534);
            startRestartGroup = startRestartGroup;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (z) {
                startRestartGroup.startReplaceGroup(1935685234);
                ComposerKt.sourceInformation(startRestartGroup, "64@2475L41,65@2556L10,63@2446L270");
                TextKt.m2849Text4IGK_g(StringResources_androidKt.stringResource(R.string.check_connection, startRestartGroup, 0), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TextStyle.m6861copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(startRestartGroup, MaterialTheme.$stable).getBodySmall(), Color.INSTANCE.m4642getBlue0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, TextDecoration.INSTANCE.getUnderline(), null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16773118, null), startRestartGroup, 0, 0, 65534);
                startRestartGroup = startRestartGroup;
            } else {
                startRestartGroup.startReplaceGroup(1933262584);
            }
            startRestartGroup.endReplaceGroup();
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
            endRestartGroup.updateScope(new Function2() { // from class: com.traffmonetizer.client.ui.main.connection.ConnectionIndicatorKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit ConnectionIndicator$lambda$2;
                    ConnectionIndicator$lambda$2 = ConnectionIndicatorKt.ConnectionIndicator$lambda$2(ConnectionStatusEntity.this, onCheckConnection, i, (Composer) obj, ((Integer) obj2).intValue());
                    return ConnectionIndicator$lambda$2;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ConnectionIndicator$lambda$0$0(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }
}
