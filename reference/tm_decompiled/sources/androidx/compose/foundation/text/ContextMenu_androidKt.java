package androidx.compose.foundation.text;

import android.content.res.Resources;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.contextmenu.ContextMenuArea_androidKt;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.contextmenu.ContextMenuState_androidKt;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope_androidKt;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.internal.PlatformDefaultTextContextMenuProviders_androidKt;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGesturesModifierKt;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt;
import androidx.compose.foundation.text.selection.SelectionManager;
import androidx.compose.foundation.text.selection.SelectionManager_androidKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: ContextMenu.android.kt */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\u0007\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\f\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\r2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\u000e\u001a5\u0010\u000f\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\u000e\b\u0004\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0080\b\u001a=\u0010\u0016\u001a\u00020\u0001*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00010\u001c¢\u0006\u0002\b\u001eH\u0000\u001a\u0012\u0010\u001f\u001a\u00020 *\u00020\tH\u0080@¢\u0006\u0002\u0010!\u001a\u0012\u0010\u001f\u001a\u00020 *\u00020\u0003H\u0080@¢\u0006\u0002\u0010\"¨\u0006#"}, d2 = {"ContextMenuArea", "", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "selectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "enabled", "", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/foundation/text/selection/SelectionManager;", "(Landroidx/compose/foundation/text/selection/SelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TextItem", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "label", "Landroidx/compose/foundation/text/TextContextMenuItems;", "operation", "textItem", "Landroidx/compose/foundation/text/contextmenu/builder/TextContextMenuBuilderScope;", "resources", "Landroid/content/res/Resources;", "item", "onClick", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuSession;", "Lkotlin/ExtensionFunctionType;", "getContextMenuItemsAvailability", "Landroidx/compose/foundation/text/MenuItemsAvailability;", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContextMenu_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContextMenuArea$lambda$16(TextFieldSelectionState textFieldSelectionState, boolean z, Function2 function2, int i, Composer composer, int i2) {
        ContextMenuArea(textFieldSelectionState, z, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContextMenuArea$lambda$20(SelectionManager selectionManager, Function2 function2, int i, Composer composer, int i2) {
        ContextMenuArea(selectionManager, (Function2<? super Composer, ? super Integer, Unit>) function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContextMenuArea$lambda$6(TextFieldSelectionManager textFieldSelectionManager, Function2 function2, int i, Composer composer, int i2) {
        ContextMenuArea(textFieldSelectionManager, (Function2<? super Composer, ? super Integer, Unit>) function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void ContextMenuArea(final TextFieldSelectionManager textFieldSelectionManager, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        final Function2<? super Composer, ? super Integer, Unit> function22;
        Composer startRestartGroup = composer.startRestartGroup(2080741862);
        ComposerKt.sourceInformation(startRestartGroup, "C(ContextMenuArea)N(manager,content):ContextMenu.android.kt#423gt5");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(textFieldSelectionManager) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if (!startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            function22 = function2;
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2080741862, i2, -1, "androidx.compose.foundation.text.ContextMenuArea (ContextMenu.android.kt:52)");
            }
            if (ComposeFoundationFlags.isNewContextMenuEnabled) {
                startRestartGroup.startReplaceGroup(-1881943916);
                ComposerKt.sourceInformation(startRestartGroup, "54@2576L88");
                PlatformDefaultTextContextMenuProviders_androidKt.ProvideDefaultPlatformTextContextMenuProviders(textFieldSelectionManager.getContextMenuAreaModifier(), function2, startRestartGroup, i2 & 112, 0);
                startRestartGroup.endReplaceGroup();
                function22 = function2;
            } else {
                startRestartGroup.startReplaceGroup(-1881814801);
                ComposerKt.sourceInformation(startRestartGroup, "56@2698L31,57@2759L24,58@2820L55,62@2993L17,65@3175L202,60@2925L494");
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1861819035, "CC(remember):ContextMenu.android.kt#9igjgp");
                Object rememberedValue = startRestartGroup.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    rememberedValue = new ContextMenuState(null, 1, null);
                    startRestartGroup.updateRememberedValue(rememberedValue);
                }
                final ContextMenuState contextMenuState = (ContextMenuState) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 683737348, "CC(remember):Effects.kt#9igjgp");
                Object rememberedValue2 = startRestartGroup.rememberedValue();
                if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue2 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, startRestartGroup);
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                }
                final CoroutineScope coroutineScope = (CoroutineScope) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1861815107, "CC(remember):ContextMenu.android.kt#9igjgp");
                Object rememberedValue3 = startRestartGroup.rememberedValue();
                if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MenuItemsAvailability.m1359boximpl(MenuItemsAvailability.INSTANCE.m1372getNoneJKCFgKw()), null, 2, null);
                    startRestartGroup.updateRememberedValue(rememberedValue3);
                }
                final MutableState mutableState = (MutableState) rememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1861809609, "CC(remember):ContextMenu.android.kt#9igjgp");
                Object rememberedValue4 = startRestartGroup.rememberedValue();
                if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue4 = new Function0() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit ContextMenuArea$lambda$3$lambda$2;
                            ContextMenuArea$lambda$3$lambda$2 = ContextMenu_androidKt.ContextMenuArea$lambda$3$lambda$2(ContextMenuState.this);
                            return ContextMenuArea$lambda$3$lambda$2;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue4);
                }
                Function0 function0 = (Function0) rememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Function1<ContextMenuScope, Unit> contextMenuBuilder = TextFieldSelectionManager_androidKt.contextMenuBuilder(textFieldSelectionManager, contextMenuState, mutableState);
                boolean enabled = textFieldSelectionManager.getEnabled();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1861803600, "CC(remember):ContextMenu.android.kt#9igjgp");
                boolean changedInstance = startRestartGroup.changedInstance(coroutineScope) | startRestartGroup.changedInstance(textFieldSelectionManager);
                Object rememberedValue5 = startRestartGroup.rememberedValue();
                if (changedInstance || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue5 = new Function0() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit ContextMenuArea$lambda$5$lambda$4;
                            ContextMenuArea$lambda$5$lambda$4 = ContextMenu_androidKt.ContextMenuArea$lambda$5$lambda$4(CoroutineScope.this, mutableState, textFieldSelectionManager);
                            return ContextMenuArea$lambda$5$lambda$4;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                function22 = function2;
                ContextMenuArea_androidKt.ContextMenuArea(contextMenuState, function0, contextMenuBuilder, null, enabled, (Function0) rememberedValue5, function22, startRestartGroup, ((i2 << 15) & 3670016) | 54, 8);
                startRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit ContextMenuArea$lambda$6;
                    ContextMenuArea$lambda$6 = ContextMenu_androidKt.ContextMenuArea$lambda$6(TextFieldSelectionManager.this, function22, i, (Composer) obj, ((Integer) obj2).intValue());
                    return ContextMenuArea$lambda$6;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContextMenuArea$lambda$3$lambda$2(ContextMenuState contextMenuState) {
        ContextMenuState_androidKt.close(contextMenuState);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContextMenuArea$lambda$5$lambda$4(CoroutineScope coroutineScope, MutableState mutableState, TextFieldSelectionManager textFieldSelectionManager) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new ContextMenu_androidKt$ContextMenuArea$2$1$1(mutableState, textFieldSelectionManager, null), 1, null);
        return Unit.INSTANCE;
    }

    public static final void ContextMenuArea(final TextFieldSelectionState textFieldSelectionState, boolean z, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        final boolean z2;
        final Function2<? super Composer, ? super Integer, Unit> function22;
        Modifier.Companion companion;
        Composer startRestartGroup = composer.startRestartGroup(-579239002);
        ComposerKt.sourceInformation(startRestartGroup, "C(ContextMenuArea)N(selectionState,enabled,content):ContextMenu.android.kt#423gt5");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(textFieldSelectionState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if (!startRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            z2 = z;
            function22 = function2;
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-579239002, i2, -1, "androidx.compose.foundation.text.ContextMenuArea (ContextMenu.android.kt:81)");
            }
            if (ComposeFoundationFlags.isNewContextMenuEnabled) {
                startRestartGroup.startReplaceGroup(1069759610);
                ComposerKt.sourceInformation(startRestartGroup, "97@4286L65");
                if (z) {
                    startRestartGroup.startReplaceGroup(1069803754);
                    ComposerKt.sourceInformation(startRestartGroup, "86@3828L371");
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1697080697, "CC(remember):ContextMenu.android.kt#9igjgp");
                    boolean changedInstance = startRestartGroup.changedInstance(textFieldSelectionState);
                    ContextMenu_androidKt$ContextMenuArea$modifier$1$1 rememberedValue = startRestartGroup.rememberedValue();
                    if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                        rememberedValue = new ContextMenu_androidKt$ContextMenuArea$modifier$1$1(textFieldSelectionState, null);
                        startRestartGroup.updateRememberedValue(rememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    companion = TextContextMenuGesturesModifierKt.textContextMenuGestures(companion2, (Function1) rememberedValue);
                    startRestartGroup.endReplaceGroup();
                } else {
                    startRestartGroup.startReplaceGroup(1070289524);
                    startRestartGroup.endReplaceGroup();
                    companion = Modifier.INSTANCE;
                }
                PlatformDefaultTextContextMenuProviders_androidKt.ProvideDefaultPlatformTextContextMenuProviders(companion, function2, startRestartGroup, (i2 >> 3) & 112, 0);
                startRestartGroup.endReplaceGroup();
                z2 = z;
                function22 = function2;
            } else {
                startRestartGroup.startReplaceGroup(1070458381);
                ComposerKt.sourceInformation(startRestartGroup, "99@4385L31,100@4446L24,101@4507L55,106@4762L537,121@5432L17,124@5561L209,119@5364L448");
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1697098181, "CC(remember):ContextMenu.android.kt#9igjgp");
                Object rememberedValue2 = startRestartGroup.rememberedValue();
                if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue2 = new ContextMenuState(null, 1, null);
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                }
                final ContextMenuState contextMenuState = (ContextMenuState) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 683737348, "CC(remember):Effects.kt#9igjgp");
                Object rememberedValue3 = startRestartGroup.rememberedValue();
                if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue3 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, startRestartGroup);
                    startRestartGroup.updateRememberedValue(rememberedValue3);
                }
                final CoroutineScope coroutineScope = (CoroutineScope) rememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1697102109, "CC(remember):ContextMenu.android.kt#9igjgp");
                Object rememberedValue4 = startRestartGroup.rememberedValue();
                if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MenuItemsAvailability.m1359boximpl(MenuItemsAvailability.INSTANCE.m1372getNoneJKCFgKw()), null, 2, null);
                    startRestartGroup.updateRememberedValue(rememberedValue4);
                }
                final MutableState mutableState = (MutableState) rememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                MutableState mutableState2 = mutableState;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1697110751, "CC(remember):ContextMenu.android.kt#9igjgp");
                boolean changedInstance2 = startRestartGroup.changedInstance(coroutineScope);
                Object rememberedValue5 = startRestartGroup.rememberedValue();
                if (changedInstance2 || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue5 = new Function2() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            Unit ContextMenuArea$lambda$11$lambda$10;
                            ContextMenuArea$lambda$11$lambda$10 = ContextMenu_androidKt.ContextMenuArea$lambda$11$lambda$10(CoroutineScope.this, (TextFieldSelectionState) obj, (TextContextMenuItems) obj2);
                            return ContextMenuArea$lambda$11$lambda$10;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Function1<ContextMenuScope, Unit> contextMenuBuilder = TextFieldSelectionState_androidKt.contextMenuBuilder(textFieldSelectionState, contextMenuState, mutableState2, (Function2) rememberedValue5);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1697131671, "CC(remember):ContextMenu.android.kt#9igjgp");
                Object rememberedValue6 = startRestartGroup.rememberedValue();
                if (rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue6 = new Function0() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit ContextMenuArea$lambda$13$lambda$12;
                            ContextMenuArea$lambda$13$lambda$12 = ContextMenu_androidKt.ContextMenuArea$lambda$13$lambda$12(ContextMenuState.this);
                            return ContextMenuArea$lambda$13$lambda$12;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue6);
                }
                Function0 function0 = (Function0) rememberedValue6;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1697135991, "CC(remember):ContextMenu.android.kt#9igjgp");
                boolean changedInstance3 = startRestartGroup.changedInstance(coroutineScope) | startRestartGroup.changedInstance(textFieldSelectionState);
                Object rememberedValue7 = startRestartGroup.rememberedValue();
                if (changedInstance3 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue7 = new Function0() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit ContextMenuArea$lambda$15$lambda$14;
                            ContextMenuArea$lambda$15$lambda$14 = ContextMenu_androidKt.ContextMenuArea$lambda$15$lambda$14(CoroutineScope.this, mutableState, textFieldSelectionState);
                            return ContextMenuArea$lambda$15$lambda$14;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue7);
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                z2 = z;
                function22 = function2;
                ContextMenuArea_androidKt.ContextMenuArea(contextMenuState, function0, contextMenuBuilder, null, z2, (Function0) rememberedValue7, function22, startRestartGroup, ((i2 << 9) & 57344) | 54 | ((i2 << 12) & 3670016), 8);
                startRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit ContextMenuArea$lambda$16;
                    ContextMenuArea$lambda$16 = ContextMenu_androidKt.ContextMenuArea$lambda$16(TextFieldSelectionState.this, z2, function22, i, (Composer) obj, ((Integer) obj2).intValue());
                    return ContextMenuArea$lambda$16;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContextMenuArea$lambda$11$lambda$10(CoroutineScope coroutineScope, TextFieldSelectionState textFieldSelectionState, TextContextMenuItems textContextMenuItems) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new ContextMenu_androidKt$ContextMenuArea$menuBuilder$1$1$1(textContextMenuItems, textFieldSelectionState, null), 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContextMenuArea$lambda$13$lambda$12(ContextMenuState contextMenuState) {
        ContextMenuState_androidKt.close(contextMenuState);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContextMenuArea$lambda$15$lambda$14(CoroutineScope coroutineScope, MutableState mutableState, TextFieldSelectionState textFieldSelectionState) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new ContextMenu_androidKt$ContextMenuArea$5$1$1(mutableState, textFieldSelectionState, null), 1, null);
        return Unit.INSTANCE;
    }

    public static final void ContextMenuArea(final SelectionManager selectionManager, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        final Function2<? super Composer, ? super Integer, Unit> function22;
        Composer startRestartGroup = composer.startRestartGroup(-954926513);
        ComposerKt.sourceInformation(startRestartGroup, "C(ContextMenuArea)N(manager,content):ContextMenu.android.kt#423gt5");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(selectionManager) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if (!startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            function22 = function2;
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-954926513, i2, -1, "androidx.compose.foundation.text.ContextMenuArea (ContextMenu.android.kt:136)");
            }
            if (ComposeFoundationFlags.isNewContextMenuEnabled) {
                startRestartGroup.startReplaceGroup(1647828491);
                ComposerKt.sourceInformation(startRestartGroup, "138@6039L88");
                PlatformDefaultTextContextMenuProviders_androidKt.ProvideDefaultPlatformTextContextMenuProviders(selectionManager.getContextMenuAreaModifier(), function2, startRestartGroup, i2 & 112, 0);
                startRestartGroup.endReplaceGroup();
                function22 = function2;
            } else {
                startRestartGroup.startReplaceGroup(1647943935);
                ComposerKt.sourceInformation(startRestartGroup, "140@6161L31,143@6309L17,141@6241L200");
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1438633230, "CC(remember):ContextMenu.android.kt#9igjgp");
                Object rememberedValue = startRestartGroup.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    rememberedValue = new ContextMenuState(null, 1, null);
                    startRestartGroup.updateRememberedValue(rememberedValue);
                }
                final ContextMenuState contextMenuState = (ContextMenuState) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1438637952, "CC(remember):ContextMenu.android.kt#9igjgp");
                Object rememberedValue2 = startRestartGroup.rememberedValue();
                if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue2 = new Function0() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit ContextMenuArea$lambda$19$lambda$18;
                            ContextMenuArea$lambda$19$lambda$18 = ContextMenu_androidKt.ContextMenuArea$lambda$19$lambda$18(ContextMenuState.this);
                            return ContextMenuArea$lambda$19$lambda$18;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                function22 = function2;
                ContextMenuArea_androidKt.ContextMenuArea(contextMenuState, (Function0) rememberedValue2, SelectionManager_androidKt.contextMenuBuilder(selectionManager, contextMenuState), null, false, null, function22, startRestartGroup, ((i2 << 15) & 3670016) | 54, 56);
                startRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit ContextMenuArea$lambda$20;
                    ContextMenuArea$lambda$20 = ContextMenu_androidKt.ContextMenuArea$lambda$20(SelectionManager.this, function22, i, (Composer) obj, ((Integer) obj2).intValue());
                    return ContextMenuArea$lambda$20;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContextMenuArea$lambda$19$lambda$18(ContextMenuState contextMenuState) {
        ContextMenuState_androidKt.close(contextMenuState);
        return Unit.INSTANCE;
    }

    public static final void TextItem(ContextMenuScope contextMenuScope, ContextMenuState contextMenuState, TextContextMenuItems textContextMenuItems, boolean z, Function0<Unit> function0) {
        if (z) {
            ContextMenuScope.item$default(contextMenuScope, new ContextMenu_androidKt$TextItem$1(textContextMenuItems), null, false, null, new ContextMenu_androidKt$TextItem$2(function0, contextMenuState), 14, null);
        }
    }

    public static final void textItem(TextContextMenuBuilderScope textContextMenuBuilderScope, Resources resources, TextContextMenuItems textContextMenuItems, boolean z, Function1<? super TextContextMenuSession, Unit> function1) {
        if (z) {
            TextContextMenuBuilderScope_androidKt.item(textContextMenuBuilderScope, textContextMenuItems.getKey(), textContextMenuItems.resolveString(resources), textContextMenuItems.getDrawableId(), function1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object getContextMenuItemsAvailability(TextFieldSelectionState textFieldSelectionState, Continuation<? super MenuItemsAvailability> continuation) {
        ContextMenu_androidKt$getContextMenuItemsAvailability$1 contextMenu_androidKt$getContextMenuItemsAvailability$1;
        int i;
        if (continuation instanceof ContextMenu_androidKt$getContextMenuItemsAvailability$1) {
            contextMenu_androidKt$getContextMenuItemsAvailability$1 = (ContextMenu_androidKt$getContextMenuItemsAvailability$1) continuation;
            if ((contextMenu_androidKt$getContextMenuItemsAvailability$1.label & Integer.MIN_VALUE) != 0) {
                contextMenu_androidKt$getContextMenuItemsAvailability$1.label -= Integer.MIN_VALUE;
                Object obj = contextMenu_androidKt$getContextMenuItemsAvailability$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = contextMenu_androidKt$getContextMenuItemsAvailability$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    contextMenu_androidKt$getContextMenuItemsAvailability$1.L$0 = textFieldSelectionState;
                    contextMenu_androidKt$getContextMenuItemsAvailability$1.label = 1;
                    if (textFieldSelectionState.updateClipboardEntry(contextMenu_androidKt$getContextMenuItemsAvailability$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    textFieldSelectionState = (TextFieldSelectionState) contextMenu_androidKt$getContextMenuItemsAvailability$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return MenuItemsAvailability.m1359boximpl(MenuItemsAvailability.m1361constructorimpl(textFieldSelectionState.canCopy(), textFieldSelectionState.canPaste(), textFieldSelectionState.canCut(), textFieldSelectionState.canSelectAll(), textFieldSelectionState.canAutofill()));
            }
        }
        contextMenu_androidKt$getContextMenuItemsAvailability$1 = new ContextMenu_androidKt$getContextMenuItemsAvailability$1(continuation);
        Object obj2 = contextMenu_androidKt$getContextMenuItemsAvailability$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = contextMenu_androidKt$getContextMenuItemsAvailability$1.label;
        if (i != 0) {
        }
        return MenuItemsAvailability.m1359boximpl(MenuItemsAvailability.m1361constructorimpl(textFieldSelectionState.canCopy(), textFieldSelectionState.canPaste(), textFieldSelectionState.canCut(), textFieldSelectionState.canSelectAll(), textFieldSelectionState.canAutofill()));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object getContextMenuItemsAvailability(TextFieldSelectionManager textFieldSelectionManager, Continuation<? super MenuItemsAvailability> continuation) {
        ContextMenu_androidKt$getContextMenuItemsAvailability$2 contextMenu_androidKt$getContextMenuItemsAvailability$2;
        int i;
        if (continuation instanceof ContextMenu_androidKt$getContextMenuItemsAvailability$2) {
            contextMenu_androidKt$getContextMenuItemsAvailability$2 = (ContextMenu_androidKt$getContextMenuItemsAvailability$2) continuation;
            if ((contextMenu_androidKt$getContextMenuItemsAvailability$2.label & Integer.MIN_VALUE) != 0) {
                contextMenu_androidKt$getContextMenuItemsAvailability$2.label -= Integer.MIN_VALUE;
                Object obj = contextMenu_androidKt$getContextMenuItemsAvailability$2.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = contextMenu_androidKt$getContextMenuItemsAvailability$2.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    contextMenu_androidKt$getContextMenuItemsAvailability$2.L$0 = textFieldSelectionManager;
                    contextMenu_androidKt$getContextMenuItemsAvailability$2.label = 1;
                    if (textFieldSelectionManager.updateClipboardEntry$foundation_release(contextMenu_androidKt$getContextMenuItemsAvailability$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    textFieldSelectionManager = (TextFieldSelectionManager) contextMenu_androidKt$getContextMenuItemsAvailability$2.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return MenuItemsAvailability.m1359boximpl(MenuItemsAvailability.m1361constructorimpl(textFieldSelectionManager.canCopy$foundation_release(), textFieldSelectionManager.canPaste$foundation_release(), textFieldSelectionManager.canCut$foundation_release(), textFieldSelectionManager.canSelectAll$foundation_release(), textFieldSelectionManager.canAutofill$foundation_release()));
            }
        }
        contextMenu_androidKt$getContextMenuItemsAvailability$2 = new ContextMenu_androidKt$getContextMenuItemsAvailability$2(continuation);
        Object obj2 = contextMenu_androidKt$getContextMenuItemsAvailability$2.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = contextMenu_androidKt$getContextMenuItemsAvailability$2.label;
        if (i != 0) {
        }
        return MenuItemsAvailability.m1359boximpl(MenuItemsAvailability.m1361constructorimpl(textFieldSelectionManager.canCopy$foundation_release(), textFieldSelectionManager.canPaste$foundation_release(), textFieldSelectionManager.canCut$foundation_release(), textFieldSelectionManager.canSelectAll$foundation_release(), textFieldSelectionManager.canAutofill$foundation_release()));
    }
}
