package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.text.ContextMenu_androidKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: SelectionContainer.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a \u0010\b\u001a\u00020\u00012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0002\u0010\t\u001aJ\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0004\u0012\u00020\u00010\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\u000f¨\u0006\u0010²\u0006\f\u0010\n\u001a\u0004\u0018\u00010\u000bX\u008a\u008e\u0002"}, d2 = {"SelectionContainer", "", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "DisableSelection", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "selection", "Landroidx/compose/foundation/text/selection/Selection;", "onSelectionChange", "Lkotlin/Function1;", "children", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/selection/Selection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SelectionContainerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DisableSelection$lambda$6(Function2 function2, int i, Composer composer, int i2) {
        DisableSelection(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionContainer$lambda$15(Modifier modifier, Selection selection, Function1 function1, Function2 function2, int i, int i2, Composer composer, int i3) {
        SelectionContainer(modifier, selection, function1, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionContainer$lambda$5(Modifier modifier, Function2 function2, int i, int i2, Composer composer, int i3) {
        SelectionContainer(modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void SelectionContainer(final Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        int i3;
        final Function2<? super Composer, ? super Integer, Unit> function22;
        Composer startRestartGroup = composer.startRestartGroup(1949207773);
        ComposerKt.sourceInformation(startRestartGroup, "C(SelectionContainer)N(modifier,content)55@2474L45,59@2632L18,56@2524L161:SelectionContainer.kt#eksfi3");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= startRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if (!startRestartGroup.shouldExecute((i3 & 19) != 18, i3 & 1)) {
            function22 = function2;
            startRestartGroup.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1949207773, i3, -1, "androidx.compose.foundation.text.selection.SelectionContainer (SelectionContainer.kt:54)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 582932746, "CC(remember):SelectionContainer.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            final MutableState mutableState = (MutableState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            int i5 = i3;
            Selection SelectionContainer$lambda$1 = SelectionContainer$lambda$1(mutableState);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 582937775, "CC(remember):SelectionContainer.kt#9igjgp");
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit SelectionContainer$lambda$4$lambda$3;
                        SelectionContainer$lambda$4$lambda$3 = SelectionContainerKt.SelectionContainer$lambda$4$lambda$3(MutableState.this, (Selection) obj);
                        return SelectionContainer$lambda$4$lambda$3;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            int i6 = (i5 & 14) | 384 | ((i5 << 6) & 7168);
            Modifier modifier2 = modifier;
            function22 = function2;
            SelectionContainer(modifier2, SelectionContainer$lambda$1, (Function1) rememberedValue2, function22, startRestartGroup, i6, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier = modifier2;
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit SelectionContainer$lambda$5;
                    SelectionContainer$lambda$5 = SelectionContainerKt.SelectionContainer$lambda$5(Modifier.this, function22, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    return SelectionContainer$lambda$5;
                }
            });
        }
    }

    private static final Selection SelectionContainer$lambda$1(MutableState<Selection> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionContainer$lambda$4$lambda$3(MutableState mutableState, Selection selection) {
        mutableState.setValue(selection);
        return Unit.INSTANCE;
    }

    public static final void DisableSelection(final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(-1162635549);
        ComposerKt.sourceInformation(startRestartGroup, "C(DisableSelection)N(content)72@2976L82:SelectionContainer.kt#eksfi3");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (!startRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1162635549, i2, -1, "androidx.compose.foundation.text.selection.DisableSelection (SelectionContainer.kt:71)");
            }
            CompositionLocalKt.CompositionLocalProvider(SelectionRegistrarKt.getLocalSelectionRegistrar().provides(null), function2, startRestartGroup, ((i2 << 3) & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit DisableSelection$lambda$6;
                    DisableSelection$lambda$6 = SelectionContainerKt.DisableSelection$lambda$6(Function2.this, i, (Composer) obj, ((Integer) obj2).intValue());
                    return DisableSelection$lambda$6;
                }
            });
        }
    }

    public static final void SelectionContainer(Modifier modifier, final Selection selection, final Function1<? super Selection, Unit> function1, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        Modifier.Companion companion;
        Composer startRestartGroup = composer.startRestartGroup(-917932944);
        ComposerKt.sourceInformation(startRestartGroup, "C(SelectionContainer)N(modifier,selection,onSelectionChange,children)93@3724L28,93@3669L83,95@3772L44,97@3853L7,98@3886L24,99@3960L7,101@4004L253,108@4301L7,125@5054L2468,125@4997L2525,181@7554L106,181@7528L132:SelectionContainer.kt#eksfi3");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (startRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= startRestartGroup.changed(selection) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= startRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            i3 |= startRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if (!startRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            startRestartGroup.skipToGroupEnd();
            companion = modifier2;
        } else {
            companion = i4 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-917932944, i3, -1, "androidx.compose.foundation.text.selection.SelectionContainer (SelectionContainer.kt:91)");
            }
            Object[] objArr = new Object[0];
            Saver<SelectionRegistrarImpl, Long> saver = SelectionRegistrarImpl.INSTANCE.getSaver();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1665247828, "CC(remember):SelectionContainer.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        SelectionRegistrarImpl SelectionContainer$lambda$8$lambda$7;
                        SelectionContainer$lambda$8$lambda$7 = SelectionContainerKt.SelectionContainer$lambda$8$lambda$7();
                        return SelectionContainer$lambda$8$lambda$7;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final SelectionRegistrarImpl selectionRegistrarImpl = (SelectionRegistrarImpl) RememberSaveableKt.m4033rememberSaveable(objArr, (Saver) saver, (Function0) rememberedValue, startRestartGroup, 384);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1665246276, "CC(remember):SelectionContainer.kt#9igjgp");
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = new SelectionManager(selectionRegistrarImpl);
                startRestartGroup.updateRememberedValue(rememberedValue2);
            }
            final SelectionManager selectionManager = (SelectionManager) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ProvidableCompositionLocal<Clipboard> localClipboard = CompositionLocalsKt.getLocalClipboard();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localClipboard);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final Clipboard clipboard = (Clipboard) consume;
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
            ProvidableCompositionLocal<HapticFeedback> localHapticFeedback = CompositionLocalsKt.getLocalHapticFeedback();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume2 = startRestartGroup.consume(localHapticFeedback);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            selectionManager.setHapticFeedBack((HapticFeedback) consume2);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1665238643, "CC(remember):SelectionContainer.kt#9igjgp");
            boolean changed = startRestartGroup.changed(coroutineScope) | startRestartGroup.changed(clipboard);
            Object rememberedValue4 = startRestartGroup.rememberedValue();
            if (changed || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                rememberedValue4 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit SelectionContainer$lambda$11$lambda$10;
                        SelectionContainer$lambda$11$lambda$10 = SelectionContainerKt.SelectionContainer$lambda$11$lambda$10(CoroutineScope.this, clipboard, (AnnotatedString) obj);
                        return SelectionContainer$lambda$11$lambda$10;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            selectionManager.setOnCopyHandler((Function1) rememberedValue4);
            ProvidableCompositionLocal<TextToolbar> localTextToolbar = CompositionLocalsKt.getLocalTextToolbar();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume3 = startRestartGroup.consume(localTextToolbar);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            selectionManager.setTextToolbar((TextToolbar) consume3);
            selectionManager.setOnSelectionChange(function1);
            selectionManager.setSelection(selection);
            if (ComposeFoundationFlags.isSmartSelectionEnabled) {
                startRestartGroup.startReplaceGroup(-82306500);
                ComposerKt.sourceInformation(startRestartGroup, "114@4553L69");
                selectionManager.setPlatformSelectionBehaviors$foundation_release(PlatformSelectionBehaviors_androidKt.rememberPlatformSelectionBehaviors(SelectedTextType.StaticText, null, startRestartGroup, 54));
                selectionManager.setCoroutineScope$foundation_release(coroutineScope);
                startRestartGroup.endReplaceGroup();
            } else {
                startRestartGroup.startReplaceGroup(-82131598);
                startRestartGroup.endReplaceGroup();
            }
            SimpleLayoutKt.SimpleLayout(companion.then(selectionManager.getModifier()), ComposableLambdaKt.rememberComposableLambda(-1799563674, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$SelectionContainer$4
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i5) {
                    ComposerKt.sourceInformation(composer2, "C126@5089L2427,126@5064L2452:SelectionContainer.kt#eksfi3");
                    if (!composer2.shouldExecute((i5 & 3) != 2, i5 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1799563674, i5, -1, "androidx.compose.foundation.text.selection.SelectionContainer.<anonymous> (SelectionContainer.kt:126)");
                    }
                    SelectionManager selectionManager2 = SelectionManager.this;
                    final SelectionRegistrarImpl selectionRegistrarImpl2 = selectionRegistrarImpl;
                    final Function2<Composer, Integer, Unit> function22 = function2;
                    final SelectionManager selectionManager3 = SelectionManager.this;
                    ContextMenu_androidKt.ContextMenuArea(selectionManager2, ComposableLambdaKt.rememberComposableLambda(-284825865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$SelectionContainer$4.1

                        /* compiled from: SelectionContainer.kt */
                        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                        /* renamed from: androidx.compose.foundation.text.selection.SelectionContainerKt$SelectionContainer$4$1$1, reason: invalid class name and collision with other inner class name */
                        static final class C00181 implements Function2<Composer, Integer, Unit> {
                            final /* synthetic */ Function2<Composer, Integer, Unit> $children;
                            final /* synthetic */ SelectionManager $manager;

                            /* JADX WARN: Multi-variable type inference failed */
                            C00181(Function2<? super Composer, ? super Integer, Unit> function2, SelectionManager selectionManager) {
                                this.$children = function2;
                                this.$manager = selectionManager;
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer, int i) {
                                Object obj;
                                ResolvedTextDirection direction;
                                float endHandleLineHeight;
                                ComposerKt.sourceInformation(composer, "C128@5194L10:SelectionContainer.kt#eksfi3");
                                if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                                    composer.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(610483127, i, -1, "androidx.compose.foundation.text.selection.SelectionContainer.<anonymous>.<anonymous>.<anonymous> (SelectionContainer.kt:128)");
                                }
                                this.$children.invoke(composer, 0);
                                if (this.$manager.isInTouchMode() && this.$manager.getHasFocus() && !this.$manager.isTriviallyCollapsedSelection$foundation_release()) {
                                    composer.startReplaceGroup(-1736225307);
                                    ComposerKt.sourceInformation(composer, "");
                                    Selection selection = this.$manager.getSelection();
                                    if (selection == null) {
                                        composer.startReplaceGroup(2011629175);
                                    } else {
                                        composer.startReplaceGroup(2011629176);
                                        ComposerKt.sourceInformation(composer, "");
                                        final SelectionManager selectionManager = this.$manager;
                                        composer.startReplaceGroup(-1736222526);
                                        ComposerKt.sourceInformation(composer, "*137@5595L137,142@5831L385,170@7268L127,163@6830L596");
                                        List listOf = CollectionsKt.listOf((Object[]) new Boolean[]{true, false});
                                        int size = listOf.size();
                                        for (int i2 = 0; i2 < size; i2++) {
                                            boolean booleanValue = ((Boolean) listOf.get(i2)).booleanValue();
                                            ComposerKt.sourceInformationMarkerStart(composer, -465225385, "CC(remember):SelectionContainer.kt#9igjgp");
                                            boolean changed = composer.changed(booleanValue);
                                            Object rememberedValue = composer.rememberedValue();
                                            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue = selectionManager.handleDragObserver(booleanValue);
                                                composer.updateRememberedValue(rememberedValue);
                                            }
                                            final TextDragObserver textDragObserver = (TextDragObserver) rememberedValue;
                                            ComposerKt.sourceInformationMarkerEnd(composer);
                                            ComposerKt.sourceInformationMarkerStart(composer, -465217585, "CC(remember):SelectionContainer.kt#9igjgp");
                                            boolean changed2 = composer.changed(booleanValue);
                                            Object rememberedValue2 = composer.rememberedValue();
                                            if (changed2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                                if (booleanValue) {
                                                    obj = 
                                                    /*  JADX ERROR: Method code generation error
                                                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00e9: CONSTRUCTOR (r3v7 'obj' java.lang.Object) = (r13v0 'selectionManager' androidx.compose.foundation.text.selection.SelectionManager A[DONT_INLINE]) A[MD:(androidx.compose.foundation.text.selection.SelectionManager):void (m)] (LINE:145) call: androidx.compose.foundation.text.selection.SelectionContainerKt$SelectionContainer$4$1$1$$ExternalSyntheticLambda0.<init>(androidx.compose.foundation.text.selection.SelectionManager):void type: CONSTRUCTOR in method: androidx.compose.foundation.text.selection.SelectionContainerKt.SelectionContainer.4.1.1.invoke(androidx.compose.runtime.Composer, int):void, file: classes.dex
                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                                        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:195)
                                                        	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:140)
                                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.foundation.text.selection.SelectionContainerKt$SelectionContainer$4$1$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                                                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:305)
                                                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                                        	... 49 more
                                                        */
                                                    /*
                                                        Method dump skipped, instructions count: 389
                                                        To view this dump add '--comments-level debug' option
                                                    */
                                                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionContainerKt$SelectionContainer$4.AnonymousClass1.C00181.invoke(androidx.compose.runtime.Composer, int):void");
                                                }

                                                /* JADX INFO: Access modifiers changed from: private */
                                                public static final Offset invoke$lambda$6$lambda$5$lambda$3$lambda$1(SelectionManager selectionManager) {
                                                    Offset m1831getStartHandlePosition_m7T9E = selectionManager.m1831getStartHandlePosition_m7T9E();
                                                    return Offset.m4332boximpl(m1831getStartHandlePosition_m7T9E != null ? m1831getStartHandlePosition_m7T9E.m4353unboximpl() : Offset.INSTANCE.m4358getUnspecifiedF1C5BW0());
                                                }

                                                /* JADX INFO: Access modifiers changed from: private */
                                                public static final Offset invoke$lambda$6$lambda$5$lambda$3$lambda$2(SelectionManager selectionManager) {
                                                    Offset m1830getEndHandlePosition_m7T9E = selectionManager.m1830getEndHandlePosition_m7T9E();
                                                    return Offset.m4332boximpl(m1830getEndHandlePosition_m7T9E != null ? m1830getEndHandlePosition_m7T9E.m4353unboximpl() : Offset.INSTANCE.m4358getUnspecifiedF1C5BW0());
                                                }
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                invoke(composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer3, int i6) {
                                                ComposerKt.sourceInformation(composer3, "C127@5176L2330,127@5103L2403:SelectionContainer.kt#eksfi3");
                                                if (!composer3.shouldExecute((i6 & 3) != 2, i6 & 1)) {
                                                    composer3.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-284825865, i6, -1, "androidx.compose.foundation.text.selection.SelectionContainer.<anonymous>.<anonymous> (SelectionContainer.kt:127)");
                                                }
                                                CompositionLocalKt.CompositionLocalProvider(SelectionRegistrarKt.getLocalSelectionRegistrar().provides(SelectionRegistrarImpl.this), ComposableLambdaKt.rememberComposableLambda(610483127, true, new C00181(function22, selectionManager3), composer3, 54), composer3, ProvidedValue.$stable | 48);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }, composer2, 54), composer2, 48);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, startRestartGroup, 54), startRestartGroup, 48, 0);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1665125190, "CC(remember):SelectionContainer.kt#9igjgp");
                                boolean changedInstance = startRestartGroup.changedInstance(selectionManager);
                                Object rememberedValue5 = startRestartGroup.rememberedValue();
                                if (changedInstance || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue5 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda3
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            DisposableEffectResult SelectionContainer$lambda$14$lambda$13;
                                            SelectionContainer$lambda$14$lambda$13 = SelectionContainerKt.SelectionContainer$lambda$14$lambda$13(SelectionManager.this, (DisposableEffectScope) obj);
                                            return SelectionContainer$lambda$14$lambda$13;
                                        }
                                    };
                                    startRestartGroup.updateRememberedValue(rememberedValue5);
                                }
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                EffectsKt.DisposableEffect(selectionManager, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue5, startRestartGroup, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                            ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
                            if (endRestartGroup != null) {
                                final Modifier modifier3 = companion;
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$$ExternalSyntheticLambda4
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit SelectionContainer$lambda$15;
                                        SelectionContainer$lambda$15 = SelectionContainerKt.SelectionContainer$lambda$15(Modifier.this, selection, function1, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                        return SelectionContainer$lambda$15;
                                    }
                                });
                            }
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final SelectionRegistrarImpl SelectionContainer$lambda$8$lambda$7() {
                            return new SelectionRegistrarImpl();
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final Unit SelectionContainer$lambda$11$lambda$10(CoroutineScope coroutineScope, Clipboard clipboard, AnnotatedString annotatedString) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new SelectionContainerKt$SelectionContainer$3$1$1(clipboard, annotatedString, null), 1, null);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final DisposableEffectResult SelectionContainer$lambda$14$lambda$13(final SelectionManager selectionManager, DisposableEffectScope disposableEffectScope) {
                            return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.selection.SelectionContainerKt$SelectionContainer$lambda$14$lambda$13$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    SelectionManager.this.onRelease();
                                    SelectionManager.this.setHasFocus(false);
                                }
                            };
                        }
                    }
