package androidx.compose.foundation.text.contextmenu.internal;

import android.content.Context;
import android.os.Build;
import androidx.compose.foundation.contextmenu.ContextMenuPopupPositionProvider;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuSpec;
import androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuComponent;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuData;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuItem;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSeparator;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuTextClassificationItem;
import androidx.compose.foundation.text.contextmenu.provider.BasicTextContextMenuProvider;
import androidx.compose.foundation.text.contextmenu.provider.BasicTextContextMenuProviderKt;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuProviderKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.PainterModifierKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: DefaultTextContextMenuDropdownProvider.android.kt */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004H\u0001¢\u0006\u0002\u0010\u0005\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004H\u0001¢\u0006\u0002\u0010\b\u001a\r\u0010\t\u001a\u00020\nH\u0001¢\u0006\u0002\u0010\u000b\u001a+\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0003H\u0003¢\u0006\u0002\u0010\u0015\u001a\u001d\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0003¢\u0006\u0002\u0010\u0019\u001a!\u0010\u001a\u001a\u00020\u00012\b\b\u0001\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0003¢\u0006\u0004\b\u001f\u0010 \"\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!²\u0006\n\u0010\u0017\u001a\u00020\u0018X\u008a\u0084\u0002"}, d2 = {"ProvideDefaultTextContextMenuDropdown", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "defaultTextContextMenuDropdown", "Landroidx/compose/foundation/text/contextmenu/provider/BasicTextContextMenuProvider;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/contextmenu/provider/BasicTextContextMenuProvider;", "DefaultPopupProperties", "Landroidx/compose/ui/window/PopupProperties;", "OpenContextMenu", "session", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuSession;", "dataProvider", "Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuDataProvider;", "anchorLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "(Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuSession;Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuDataProvider;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "DefaultTextContextMenuDropdown", "data", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuData;", "(Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuSession;Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuData;Landroidx/compose/runtime/Composer;I)V", "IconBox", "resId", "", "tint", "Landroidx/compose/ui/graphics/Color;", "IconBox-RPmYEkk", "(IJLandroidx/compose/runtime/Composer;I)V", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DefaultTextContextMenuDropdownProvider_androidKt {
    private static final PopupProperties DefaultPopupProperties = new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DefaultTextContextMenuDropdown$lambda$11(TextContextMenuSession textContextMenuSession, TextContextMenuData textContextMenuData, int i, Composer composer, int i2) {
        DefaultTextContextMenuDropdown(textContextMenuSession, textContextMenuData, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit IconBox_RPmYEkk$lambda$13(int i, long j, int i2, Composer composer, int i3) {
        m1444IconBoxRPmYEkk(i, j, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit IconBox_RPmYEkk$lambda$15(int i, long j, int i2, Composer composer, int i3) {
        m1444IconBoxRPmYEkk(i, j, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit OpenContextMenu$lambda$6(TextContextMenuSession textContextMenuSession, TextContextMenuDataProvider textContextMenuDataProvider, Function0 function0, int i, Composer composer, int i2) {
        OpenContextMenu(textContextMenuSession, textContextMenuDataProvider, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProvideDefaultTextContextMenuDropdown$lambda$0(Function2 function2, int i, Composer composer, int i2) {
        ProvideDefaultTextContextMenuDropdown(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProvideDefaultTextContextMenuDropdown$lambda$1(Modifier modifier, Function2 function2, int i, Composer composer, int i2) {
        ProvideDefaultTextContextMenuDropdown(modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void ProvideDefaultTextContextMenuDropdown(final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(422095256);
        ComposerKt.sourceInformation(startRestartGroup, "C(ProvideDefaultTextContextMenuDropdown)N(content)72@3752L297:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (!startRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(422095256, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.ProvideDefaultTextContextMenuDropdown (DefaultTextContextMenuDropdownProvider.android.kt:71)");
            }
            BasicTextContextMenuProviderKt.ProvideBasicTextContextMenu(TextContextMenuProviderKt.getLocalTextContextMenuDropdownProvider(), ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt.INSTANCE.getLambda$129995601$foundation_release(), function2, startRestartGroup, ((i2 << 6) & 896) | 54);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit ProvideDefaultTextContextMenuDropdown$lambda$0;
                    ProvideDefaultTextContextMenuDropdown$lambda$0 = DefaultTextContextMenuDropdownProvider_androidKt.ProvideDefaultTextContextMenuDropdown$lambda$0(Function2.this, i, (Composer) obj, ((Integer) obj2).intValue());
                    return ProvideDefaultTextContextMenuDropdown$lambda$0;
                }
            });
        }
    }

    public static final void ProvideDefaultTextContextMenuDropdown(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        final Modifier modifier2;
        final Function2<? super Composer, ? super Integer, Unit> function22;
        Composer startRestartGroup = composer.startRestartGroup(1392105195);
        ComposerKt.sourceInformation(startRestartGroup, "C(ProvideDefaultTextContextMenuDropdown)N(modifier,content)86@4186L326:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if (!startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            modifier2 = modifier;
            function22 = function2;
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1392105195, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.ProvideDefaultTextContextMenuDropdown (DefaultTextContextMenuDropdownProvider.android.kt:85)");
            }
            modifier2 = modifier;
            function22 = function2;
            BasicTextContextMenuProviderKt.ProvideBasicTextContextMenu(modifier2, TextContextMenuProviderKt.getLocalTextContextMenuDropdownProvider(), ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt.INSTANCE.getLambda$636288403$foundation_release(), function22, startRestartGroup, (i2 & 14) | 432 | ((i2 << 6) & 7168));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit ProvideDefaultTextContextMenuDropdown$lambda$1;
                    ProvideDefaultTextContextMenuDropdown$lambda$1 = DefaultTextContextMenuDropdownProvider_androidKt.ProvideDefaultTextContextMenuDropdown$lambda$1(Modifier.this, function22, i, (Composer) obj, ((Integer) obj2).intValue());
                    return ProvideDefaultTextContextMenuDropdown$lambda$1;
                }
            });
        }
    }

    public static final BasicTextContextMenuProvider defaultTextContextMenuDropdown(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1197778906, "C(defaultTextContextMenuDropdown)98@4610L158:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1197778906, i, -1, "androidx.compose.foundation.text.contextmenu.internal.defaultTextContextMenuDropdown (DefaultTextContextMenuDropdownProvider.android.kt:98)");
        }
        BasicTextContextMenuProvider basicTextContextMenuProvider = BasicTextContextMenuProviderKt.basicTextContextMenuProvider(ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt.INSTANCE.m1440getLambda$1357803046$foundation_release(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return basicTextContextMenuProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void OpenContextMenu(final TextContextMenuSession textContextMenuSession, final TextContextMenuDataProvider textContextMenuDataProvider, final Function0<? extends LayoutCoordinates> function0, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(-2040393164);
        ComposerKt.sourceInformation(startRestartGroup, "C(OpenContextMenu)N(session,dataProvider,anchorLayoutCoordinates)111@5065L252,121@5412L19,123@5484L143,119@5323L304:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? startRestartGroup.changed(textContextMenuSession) : startRestartGroup.changedInstance(textContextMenuSession) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? startRestartGroup.changed(textContextMenuDataProvider) : startRestartGroup.changedInstance(textContextMenuDataProvider) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        boolean z = false;
        if (!startRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2040393164, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.OpenContextMenu (DefaultTextContextMenuDropdownProvider.android.kt:109)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1982231056, "CC(remember):DefaultTextContextMenuDropdownProvider.android.kt#9igjgp");
            boolean z2 = (i2 & 112) == 32 || ((i2 & 64) != 0 && startRestartGroup.changed(textContextMenuDataProvider));
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new MaintainWindowPositionPopupPositionProvider(new ContextMenuPopupPositionProvider(new Function0() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        IntOffset OpenContextMenu$lambda$3$lambda$2;
                        OpenContextMenu$lambda$3$lambda$2 = DefaultTextContextMenuDropdownProvider_androidKt.OpenContextMenu$lambda$3$lambda$2(TextContextMenuDataProvider.this, function0);
                        return OpenContextMenu$lambda$3$lambda$2;
                    }
                }, (Function2) null, 2, (DefaultConstructorMarker) null));
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            MaintainWindowPositionPopupPositionProvider maintainWindowPositionPopupPositionProvider = (MaintainWindowPositionPopupPositionProvider) rememberedValue;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1982241927, "CC(remember):DefaultTextContextMenuDropdownProvider.android.kt#9igjgp");
            if ((i2 & 14) == 4 || ((i2 & 8) != 0 && startRestartGroup.changedInstance(textContextMenuSession))) {
                z = true;
            }
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (z || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = new Function0() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit OpenContextMenu$lambda$5$lambda$4;
                        OpenContextMenu$lambda$5$lambda$4 = DefaultTextContextMenuDropdownProvider_androidKt.OpenContextMenu$lambda$5$lambda$4(TextContextMenuSession.this);
                        return OpenContextMenu$lambda$5$lambda$4;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            AndroidPopup_androidKt.Popup(maintainWindowPositionPopupPositionProvider, (Function0) rememberedValue2, DefaultPopupProperties, ComposableLambdaKt.rememberComposableLambda(1315155414, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$OpenContextMenu$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    ComposerKt.sourceInformation(composer2, "C124@5506L61,125@5576L45:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
                    if (!composer2.shouldExecute((i3 & 3) != 2, i3 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1315155414, i3, -1, "androidx.compose.foundation.text.contextmenu.internal.OpenContextMenu.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:124)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composer2, 1143968595, "CC(remember):DefaultTextContextMenuDropdownProvider.android.kt#9igjgp");
                    boolean changed = composer2.changed(TextContextMenuDataProvider.this);
                    TextContextMenuDataProvider textContextMenuDataProvider2 = TextContextMenuDataProvider.this;
                    Object rememberedValue3 = composer2.rememberedValue();
                    if (changed || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        rememberedValue3 = SnapshotStateKt.derivedStateOf(new DefaultTextContextMenuDropdownProvider_androidKt$OpenContextMenu$2$data$2$1(textContextMenuDataProvider2));
                        composer2.updateRememberedValue(rememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    DefaultTextContextMenuDropdownProvider_androidKt.DefaultTextContextMenuDropdown(textContextMenuSession, invoke$lambda$1((State) rememberedValue3), composer2, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                private static final TextContextMenuData invoke$lambda$1(State<TextContextMenuData> state) {
                    return state.getValue();
                }
            }, startRestartGroup, 54), startRestartGroup, 3456, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit OpenContextMenu$lambda$6;
                    OpenContextMenu$lambda$6 = DefaultTextContextMenuDropdownProvider_androidKt.OpenContextMenu$lambda$6(TextContextMenuSession.this, textContextMenuDataProvider, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                    return OpenContextMenu$lambda$6;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntOffset OpenContextMenu$lambda$3$lambda$2(TextContextMenuDataProvider textContextMenuDataProvider, Function0 function0) {
        return IntOffset.m7522boximpl(IntOffsetKt.m7548roundk4lQ0M(textContextMenuDataProvider.mo1462positiontuRUvjQ((LayoutCoordinates) function0.invoke())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit OpenContextMenu$lambda$5$lambda$4(TextContextMenuSession textContextMenuSession) {
        textContextMenuSession.close();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DefaultTextContextMenuDropdown(final TextContextMenuSession textContextMenuSession, final TextContextMenuData textContextMenuData, Composer composer, final int i) {
        int i2;
        final Context context;
        Composer startRestartGroup = composer.startRestartGroup(1904307118);
        ComposerKt.sourceInformation(startRestartGroup, "C(DefaultTextContextMenuDropdown)N(session,data)140@5937L900,140@5912L925:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? startRestartGroup.changed(textContextMenuSession) : startRestartGroup.changedInstance(textContextMenuSession) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(textContextMenuData) ? 32 : 16;
        }
        boolean z = false;
        if (!startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1904307118, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdown (DefaultTextContextMenuDropdownProvider.android.kt:133)");
            }
            if (Build.VERSION.SDK_INT >= 28) {
                startRestartGroup.startReplaceGroup(-1009462744);
                ComposerKt.sourceInformation(startRestartGroup, "136@5856L7");
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object consume = startRestartGroup.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                context = (Context) consume;
                startRestartGroup.endReplaceGroup();
            } else {
                startRestartGroup.startReplaceGroup(-1009413640);
                startRestartGroup.endReplaceGroup();
                context = null;
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 383082994, "CC(remember):DefaultTextContextMenuDropdownProvider.android.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(textContextMenuData);
            if ((i2 & 14) == 4 || ((i2 & 8) != 0 && startRestartGroup.changedInstance(textContextMenuSession))) {
                z = true;
            }
            boolean changedInstance2 = changedInstance | z | startRestartGroup.changedInstance(context);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changedInstance2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit DefaultTextContextMenuDropdown$lambda$10$lambda$9;
                        DefaultTextContextMenuDropdown$lambda$10$lambda$9 = DefaultTextContextMenuDropdownProvider_androidKt.DefaultTextContextMenuDropdown$lambda$10$lambda$9(TextContextMenuData.this, context, textContextMenuSession, (ContextMenuScope) obj);
                        return DefaultTextContextMenuDropdown$lambda$10$lambda$9;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ContextMenuUi_androidKt.ContextMenuColumnBuilder(null, null, (Function1) rememberedValue, startRestartGroup, 0, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit DefaultTextContextMenuDropdown$lambda$11;
                    DefaultTextContextMenuDropdown$lambda$11 = DefaultTextContextMenuDropdownProvider_androidKt.DefaultTextContextMenuDropdown$lambda$11(TextContextMenuSession.this, textContextMenuData, i, (Composer) obj, ((Integer) obj2).intValue());
                    return DefaultTextContextMenuDropdown$lambda$11;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DefaultTextContextMenuDropdown$lambda$10$lambda$9(TextContextMenuData textContextMenuData, Context context, final TextContextMenuSession textContextMenuSession, ContextMenuScope contextMenuScope) {
        ContextMenuScope contextMenuScope2;
        List<TextContextMenuComponent> components = textContextMenuData.getComponents();
        int size = components.size();
        int i = 0;
        while (i < size) {
            final TextContextMenuComponent textContextMenuComponent = components.get(i);
            if (textContextMenuComponent instanceof TextContextMenuItem) {
                contextMenuScope2 = contextMenuScope;
                ContextMenuScope.item$default(contextMenuScope2, new Function2<Composer, Integer, String>() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$DefaultTextContextMenuDropdown$1$1$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ String invoke(Composer composer, Integer num) {
                        return invoke(composer, num.intValue());
                    }

                    public final String invoke(Composer composer, int i2) {
                        composer.startReplaceGroup(666084174);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(666084174, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdown.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:145)");
                        }
                        String label = ((TextContextMenuItem) TextContextMenuComponent.this).getLabel();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer.endReplaceGroup();
                        return label;
                    }
                }, null, false, ((TextContextMenuItem) textContextMenuComponent).getLeadingIcon() == 0 ? null : ComposableLambdaKt.composableLambdaInstance(-1930700965, true, new Function3<Color, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$DefaultTextContextMenuDropdown$1$1$1$2
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Color color, Composer composer, Integer num) {
                        m1446invokeek8zF_U(color.m4625unboximpl(), composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-ek8zF_U, reason: not valid java name */
                    public final void m1446invokeek8zF_U(long j, Composer composer, int i2) {
                        ComposerKt.sourceInformation(composer, "CN(color:c#ui.graphics.Color)150@6375L37:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
                        if ((i2 & 6) == 0) {
                            i2 |= composer.changed(j) ? 4 : 2;
                        }
                        if (!composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
                            composer.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1930700965, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdown.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:150)");
                        }
                        DefaultTextContextMenuDropdownProvider_androidKt.m1444IconBoxRPmYEkk(((TextContextMenuItem) TextContextMenuComponent.this).getLeadingIcon(), j, composer, (i2 << 3) & 112);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), new Function0() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit DefaultTextContextMenuDropdown$lambda$10$lambda$9$lambda$8$lambda$7;
                        DefaultTextContextMenuDropdown$lambda$10$lambda$9$lambda$8$lambda$7 = DefaultTextContextMenuDropdownProvider_androidKt.DefaultTextContextMenuDropdown$lambda$10$lambda$9$lambda$8$lambda$7(TextContextMenuComponent.this, textContextMenuSession);
                        return DefaultTextContextMenuDropdown$lambda$10$lambda$9$lambda$8$lambda$7;
                    }
                }, 6, null);
            } else {
                contextMenuScope2 = contextMenuScope;
                if (textContextMenuComponent instanceof TextContextMenuTextClassificationItem) {
                    if (Build.VERSION.SDK_INT >= 28) {
                        TextContextMenuHelperApi28.INSTANCE.textClassificationItem(contextMenuScope2, context, (TextContextMenuTextClassificationItem) textContextMenuComponent);
                    }
                } else if (textContextMenuComponent instanceof TextContextMenuSeparator) {
                    contextMenuScope2.separator();
                }
            }
            i++;
            contextMenuScope = contextMenuScope2;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DefaultTextContextMenuDropdown$lambda$10$lambda$9$lambda$8$lambda$7(TextContextMenuComponent textContextMenuComponent, TextContextMenuSession textContextMenuSession) {
        ((TextContextMenuItem) textContextMenuComponent).getOnClick().invoke(textContextMenuSession);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: IconBox-RPmYEkk, reason: not valid java name */
    public static final void m1444IconBoxRPmYEkk(final int i, final long j, Composer composer, final int i2) {
        int i3;
        ScopeUpdateScope endRestartGroup;
        Function2<? super Composer, ? super Integer, Unit> function2;
        Composer startRestartGroup = composer.startRestartGroup(-1240244237);
        ComposerKt.sourceInformation(startRestartGroup, "C(IconBox)N(resId,tint:c#ui.graphics.Color)167@6979L7,169@7024L182,176@7267L35,177@7325L75,178@7405L146:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        if ((i2 & 6) == 0) {
            i3 = (startRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= startRestartGroup.changed(j) ? 32 : 16;
        }
        if (!startRestartGroup.shouldExecute((i3 & 19) != 18, i3 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1240244237, i3, -1, "androidx.compose.foundation.text.contextmenu.internal.IconBox (DefaultTextContextMenuDropdownProvider.android.kt:166)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Context context = (Context) consume;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2126201449, "CC(remember):DefaultTextContextMenuDropdownProvider.android.kt#9igjgp");
            boolean changed = ((i3 & 14) == 4) | startRestartGroup.changed(context);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = Integer.valueOf(context.obtainStyledAttributes(new int[]{i}).getResourceId(0, -1));
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            int intValue = ((Number) rememberedValue).intValue();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (intValue == -1) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    function2 = new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            Unit IconBox_RPmYEkk$lambda$13;
                            IconBox_RPmYEkk$lambda$13 = DefaultTextContextMenuDropdownProvider_androidKt.IconBox_RPmYEkk$lambda$13(i, j, i2, (Composer) obj, ((Integer) obj2).intValue());
                            return IconBox_RPmYEkk$lambda$13;
                        }
                    };
                    endRestartGroup.updateScope(function2);
                }
                return;
            }
            Painter painterResource = PainterResources_androidKt.painterResource(intValue, startRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2126210974, "CC(remember):DefaultTextContextMenuDropdownProvider.android.kt#9igjgp");
            boolean z = (i3 & 112) == 32;
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (z || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = j == 16 ? null : ColorFilter.Companion.m4656tintxETnrds$default(ColorFilter.INSTANCE, j, 0, 2, null);
                startRestartGroup.updateRememberedValue(rememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            BoxKt.Box(PainterModifierKt.paint$default(SizeKt.m930size3ABfNKs(Modifier.INSTANCE, ContextMenuSpec.INSTANCE.m431getIconSizeD9Ej5fM()), painterResource, false, null, ContentScale.INSTANCE.getFit(), 0.0f, (ColorFilter) rememberedValue2, 22, null), startRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            function2 = new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit IconBox_RPmYEkk$lambda$15;
                    IconBox_RPmYEkk$lambda$15 = DefaultTextContextMenuDropdownProvider_androidKt.IconBox_RPmYEkk$lambda$15(i, j, i2, (Composer) obj, ((Integer) obj2).intValue());
                    return IconBox_RPmYEkk$lambda$15;
                }
            };
            endRestartGroup.updateScope(function2);
        }
    }
}
