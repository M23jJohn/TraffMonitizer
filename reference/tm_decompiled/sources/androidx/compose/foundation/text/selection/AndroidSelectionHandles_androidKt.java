package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.material3.MenuKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.AbsoluteAlignment;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.compose.ui.window.SecureFlagPolicy;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidSelectionHandles.android.kt */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aI\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a+\u0010\u0011\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010\u0015\u001a\"\u0010\u0016\u001a\u00020\u000e*\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0000\u001a\u0014\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH\u0000\u001a0\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001e2\u0011\u0010\u001f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b H\u0001¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"SelectionHandle", "", "offsetProvider", "Landroidx/compose/foundation/text/selection/OffsetProvider;", "isStartHandle", "", "direction", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "handlesCrossed", "minTouchTargetSize", "Landroidx/compose/ui/unit/DpSize;", "lineHeight", "", "modifier", "Landroidx/compose/ui/Modifier;", "SelectionHandle-wLIcFTc", "(Landroidx/compose/foundation/text/selection/OffsetProvider;ZLandroidx/compose/ui/text/style/ResolvedTextDirection;ZJFLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "SelectionHandleIcon", "iconVisible", "Lkotlin/Function0;", "isLeft", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "drawSelectionHandle", "createHandleImage", "Landroidx/compose/ui/graphics/ImageBitmap;", "Landroidx/compose/ui/draw/CacheDrawScope;", "radius", "HandlePopup", "positionProvider", "handleReferencePoint", "Landroidx/compose/ui/Alignment;", "content", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/text/selection/OffsetProvider;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidSelectionHandles_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HandlePopup$lambda$6(OffsetProvider offsetProvider, Alignment alignment, Function2 function2, int i, Composer composer, int i2) {
        HandlePopup(offsetProvider, alignment, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionHandleIcon$lambda$3(Modifier modifier, Function0 function0, boolean z, int i, Composer composer, int i2) {
        SelectionHandleIcon(modifier, function0, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionHandle_wLIcFTc$lambda$2(OffsetProvider offsetProvider, boolean z, ResolvedTextDirection resolvedTextDirection, boolean z2, long j, float f, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m1766SelectionHandlewLIcFTc(offsetProvider, z, resolvedTextDirection, z2, j, f, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00e5, code lost:
    
        if ((r28 & 16) != 0) goto L83;
     */
    /* renamed from: SelectionHandle-wLIcFTc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m1766SelectionHandlewLIcFTc(final OffsetProvider offsetProvider, final boolean z, final ResolvedTextDirection resolvedTextDirection, boolean z2, long j, final float f, final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        boolean z3;
        long j2;
        Composer startRestartGroup = composer.startRestartGroup(-466280168);
        ComposerKt.sourceInformation(startRestartGroup, "C(SelectionHandle)N(offsetProvider,isStartHandle,direction,handlesCrossed,minTouchTargetSize:c#ui.unit.DpSize,lineHeight,modifier)71@3073L394,83@3574L7,84@3678L1346,84@3586L1438:AndroidSelectionHandles.android.kt#eksfi3");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? startRestartGroup.changed(offsetProvider) : startRestartGroup.changedInstance(offsetProvider) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= startRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= startRestartGroup.changed(resolvedTextDirection.ordinal()) ? 256 : 128;
        }
        if ((i2 & 8) != 0) {
            i3 |= 3072;
            z3 = z2;
        } else {
            z3 = z2;
            if ((i & 3072) == 0) {
                i3 |= startRestartGroup.changed(z3) ? 2048 : 1024;
            }
        }
        if ((i & 24576) == 0) {
            j2 = j;
            i3 |= ((i2 & 16) == 0 && startRestartGroup.changed(j2)) ? 16384 : 8192;
        } else {
            j2 = j;
        }
        if ((i2 & 64) != 0) {
            i3 |= 1572864;
        } else if ((i & 1572864) == 0) {
            i3 |= startRestartGroup.changed(modifier) ? 1048576 : 524288;
        }
        if (startRestartGroup.shouldExecute((533651 & i3) != 533650, i3 & 1)) {
            startRestartGroup.startDefaults();
            if ((i & 1) == 0 || startRestartGroup.getDefaultsInvalid()) {
                if ((i2 & 16) != 0) {
                    j2 = DpSize.INSTANCE.m7506getUnspecifiedMYxV2XQ();
                    i3 &= -57345;
                }
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-466280168, i3, -1, "androidx.compose.foundation.text.selection.SelectionHandle (AndroidSelectionHandles.android.kt:65)");
                }
                final boolean isLeftSelectionHandle = SelectionHandlesKt.isLeftSelectionHandle(z, resolvedTextDirection, z2);
                AbsoluteAlignment absoluteAlignment = AbsoluteAlignment.INSTANCE;
                Alignment topRight = isLeftSelectionHandle ? absoluteAlignment.getTopRight() : absoluteAlignment.getTopLeft();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1418623230, "CC(remember):AndroidSelectionHandles.android.kt#9igjgp");
                int i4 = i3 & 14;
                boolean changed = ((i3 & 112) == 32) | (i4 == 4 || ((i3 & 8) != 0 && startRestartGroup.changedInstance(offsetProvider))) | startRestartGroup.changed(isLeftSelectionHandle);
                Object rememberedValue = startRestartGroup.rememberedValue();
                if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                    rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            Unit SelectionHandle_wLIcFTc$lambda$1$lambda$0;
                            SelectionHandle_wLIcFTc$lambda$1$lambda$0 = AndroidSelectionHandles_androidKt.SelectionHandle_wLIcFTc$lambda$1$lambda$0(OffsetProvider.this, z, isLeftSelectionHandle, (SemanticsPropertyReceiver) obj);
                            return SelectionHandle_wLIcFTc$lambda$1$lambda$0;
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                final Modifier semantics$default = SemanticsModifierKt.semantics$default(modifier, false, (Function1) rememberedValue, 1, null);
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object consume = startRestartGroup.consume(localViewConfiguration);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                final ViewConfiguration viewConfiguration = (ViewConfiguration) consume;
                final long j3 = j2;
                HandlePopup(offsetProvider, topRight, ComposableLambdaKt.rememberComposableLambda(1365123137, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1

                    /* compiled from: AndroidSelectionHandles.android.kt */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                    /* renamed from: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1$1, reason: invalid class name */
                    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
                        final /* synthetic */ boolean $isLeft;
                        final /* synthetic */ long $minTouchTargetSize;
                        final /* synthetic */ OffsetProvider $offsetProvider;
                        final /* synthetic */ Modifier $semanticsModifier;

                        AnonymousClass1(long j, boolean z, Modifier modifier, OffsetProvider offsetProvider) {
                            this.$minTouchTargetSize = j;
                            this.$isLeft = z;
                            this.$semanticsModifier = modifier;
                            this.$offsetProvider = offsetProvider;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer, int i) {
                            Arrangement.Horizontal left;
                            ComposerKt.sourceInformation(composer, "C:AndroidSelectionHandles.android.kt#eksfi3");
                            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                                composer.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1260045569, i, -1, "androidx.compose.foundation.text.selection.SelectionHandle.<anonymous>.<anonymous> (AndroidSelectionHandles.android.kt:86)");
                            }
                            if (this.$minTouchTargetSize != InlineClassHelperKt.UnspecifiedPackedFloats) {
                                composer.startReplaceGroup(3458246);
                                ComposerKt.sourceInformation(composer, "96@4179L576");
                                if (this.$isLeft) {
                                    left = Arrangement.Absolute.INSTANCE.getRight();
                                } else {
                                    left = Arrangement.Absolute.INSTANCE.getLeft();
                                }
                                Modifier m926requiredSizeInqDBjuR0$default = SizeKt.m926requiredSizeInqDBjuR0$default(this.$semanticsModifier, DpSize.m7497getWidthD9Ej5fM(this.$minTouchTargetSize), DpSize.m7495getHeightD9Ej5fM(this.$minTouchTargetSize), 0.0f, 0.0f, 12, null);
                                final OffsetProvider offsetProvider = this.$offsetProvider;
                                boolean z = this.$isLeft;
                                ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(left, Alignment.INSTANCE.getTop(), composer, 0);
                                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                                int m = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, m926requiredSizeInqDBjuR0$default);
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
                                Updater.m3912setimpl(m3905constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(m))) {
                                    m3905constructorimpl.updateRememberedValue(Integer.valueOf(m));
                                    m3905constructorimpl.apply(Integer.valueOf(m), setCompositeKeyHash);
                                }
                                Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer, -1665345997, "C106@4633L40,104@4529L208:AndroidSelectionHandles.android.kt#eksfi3");
                                Modifier.Companion companion = Modifier.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer, -1439191000, "CC(remember):AndroidSelectionHandles.android.kt#9igjgp");
                                boolean changedInstance = composer.changedInstance(offsetProvider);
                                Object rememberedValue = composer.rememberedValue();
                                if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue = 
                                    /*  JADX ERROR: Method code generation error
                                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0135: CONSTRUCTOR (r1v10 'rememberedValue' java.lang.Object) = (r3v6 'offsetProvider' androidx.compose.foundation.text.selection.OffsetProvider A[DONT_INLINE]) A[MD:(androidx.compose.foundation.text.selection.OffsetProvider):void (m)] (LINE:107) call: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1$1$$ExternalSyntheticLambda0.<init>(androidx.compose.foundation.text.selection.OffsetProvider):void type: CONSTRUCTOR in method: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1.1.invoke(androidx.compose.runtime.Composer, int):void, file: classes.dex
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
                                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:305)
                                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                        	... 31 more
                                        */
                                    /*
                                        Method dump skipped, instructions count: 422
                                        To view this dump add '--comments-level debug' option
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1.AnonymousClass1.invoke(androidx.compose.runtime.Composer, int):void");
                                }

                                /* JADX INFO: Access modifiers changed from: private */
                                public static final boolean invoke$lambda$2$lambda$1$lambda$0(OffsetProvider offsetProvider) {
                                    return (offsetProvider.mo1265provideF1C5BW0() & 9223372034707292159L) != InlineClassHelperKt.UnspecifiedPackedFloats;
                                }

                                /* JADX INFO: Access modifiers changed from: private */
                                public static final boolean invoke$lambda$4$lambda$3(OffsetProvider offsetProvider) {
                                    return (offsetProvider.mo1265provideF1C5BW0() & 9223372034707292159L) != InlineClassHelperKt.UnspecifiedPackedFloats;
                                }
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i5) {
                                ComposerKt.sourceInformation(composer2, "C85@3764L1254,85@3688L1330:AndroidSelectionHandles.android.kt#eksfi3");
                                if (!composer2.shouldExecute((i5 & 3) != 2, i5 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1365123137, i5, -1, "androidx.compose.foundation.text.selection.SelectionHandle.<anonymous> (AndroidSelectionHandles.android.kt:85)");
                                }
                                CompositionLocalKt.CompositionLocalProvider(CompositionLocalsKt.getLocalViewConfiguration().provides(ViewConfiguration.this), ComposableLambdaKt.rememberComposableLambda(1260045569, true, new AnonymousClass1(j3, isLeftSelectionHandle, semantics$default, offsetProvider), composer2, 54), composer2, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, startRestartGroup, 54), startRestartGroup, i4 | 384);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j2 = j3;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                    }
                } else {
                    startRestartGroup.skipToGroupEnd();
                }
                ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    final boolean z4 = z3;
                    final long j4 = j2;
                    endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            Unit SelectionHandle_wLIcFTc$lambda$2;
                            SelectionHandle_wLIcFTc$lambda$2 = AndroidSelectionHandles_androidKt.SelectionHandle_wLIcFTc$lambda$2(OffsetProvider.this, z, resolvedTextDirection, z4, j4, f, modifier, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            return SelectionHandle_wLIcFTc$lambda$2;
                        }
                    });
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final Unit SelectionHandle_wLIcFTc$lambda$1$lambda$0(OffsetProvider offsetProvider, boolean z, boolean z2, SemanticsPropertyReceiver semanticsPropertyReceiver) {
                long mo1265provideF1C5BW0 = offsetProvider.mo1265provideF1C5BW0();
                semanticsPropertyReceiver.set(SelectionHandlesKt.getSelectionHandleInfoKey(), new SelectionHandleInfo(z ? Handle.SelectionStart : Handle.SelectionEnd, mo1265provideF1C5BW0, z2 ? SelectionHandleAnchor.Left : SelectionHandleAnchor.Right, (9223372034707292159L & mo1265provideF1C5BW0) != InlineClassHelperKt.UnspecifiedPackedFloats, null));
                return Unit.INSTANCE;
            }

            public static final void SelectionHandleIcon(final Modifier modifier, final Function0<Boolean> function0, final boolean z, Composer composer, final int i) {
                int i2;
                Composer startRestartGroup = composer.startRestartGroup(2111672474);
                ComposerKt.sourceInformation(startRestartGroup, "C(SelectionHandleIcon)N(modifier,iconVisible,isLeft)124@5167L89:AndroidSelectionHandles.android.kt#eksfi3");
                if ((i & 6) == 0) {
                    i2 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
                } else {
                    i2 = i;
                }
                if ((i & 48) == 0) {
                    i2 |= startRestartGroup.changedInstance(function0) ? 32 : 16;
                }
                if ((i & 384) == 0) {
                    i2 |= startRestartGroup.changed(z) ? 256 : 128;
                }
                if (!startRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
                    startRestartGroup.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(2111672474, i2, -1, "androidx.compose.foundation.text.selection.SelectionHandleIcon (AndroidSelectionHandles.android.kt:123)");
                    }
                    SpacerKt.Spacer(drawSelectionHandle(SizeKt.m932sizeVpY3zN4(modifier, SelectionHandlesKt.getHandleWidth(), SelectionHandlesKt.getHandleHeight()), function0, z), startRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            Unit SelectionHandleIcon$lambda$3;
                            SelectionHandleIcon$lambda$3 = AndroidSelectionHandles_androidKt.SelectionHandleIcon$lambda$3(Modifier.this, function0, z, i, (Composer) obj, ((Integer) obj2).intValue());
                            return SelectionHandleIcon$lambda$3;
                        }
                    });
                }
            }

            public static final Modifier drawSelectionHandle(Modifier modifier, Function0<Boolean> function0, boolean z) {
                return ComposedModifierKt.composed$default(modifier, null, new AndroidSelectionHandles_androidKt$drawSelectionHandle$1(function0, z), 1, null);
            }

            public static final ImageBitmap createHandleImage(CacheDrawScope cacheDrawScope, float f) {
                int ceil = ((int) Math.ceil(f)) * 2;
                ImageBitmap imageBitmap = HandleImageCache.INSTANCE.getImageBitmap();
                Canvas canvas = HandleImageCache.INSTANCE.getCanvas();
                CanvasDrawScope canvasDrawScope = HandleImageCache.INSTANCE.getCanvasDrawScope();
                if (imageBitmap == null || canvas == null || ceil > imageBitmap.getWidth() || ceil > imageBitmap.getHeight()) {
                    imageBitmap = ImageBitmapKt.m4861ImageBitmapx__hDU$default(ceil, ceil, ImageBitmapConfig.INSTANCE.m4855getAlpha8_sVssgQ(), false, null, 24, null);
                    HandleImageCache.INSTANCE.setImageBitmap(imageBitmap);
                    canvas = CanvasKt.Canvas(imageBitmap);
                    HandleImageCache.INSTANCE.setCanvas(canvas);
                }
                ImageBitmap imageBitmap2 = imageBitmap;
                Canvas canvas2 = canvas;
                if (canvasDrawScope == null) {
                    canvasDrawScope = new CanvasDrawScope();
                    HandleImageCache.INSTANCE.setCanvasDrawScope(canvasDrawScope);
                }
                CanvasDrawScope canvasDrawScope2 = canvasDrawScope;
                LayoutDirection layoutDirection = cacheDrawScope.getLayoutDirection();
                float width = imageBitmap2.getWidth();
                float height = imageBitmap2.getHeight();
                long m4403constructorimpl = Size.m4403constructorimpl((Float.floatToRawIntBits(height) & 4294967295L) | (Float.floatToRawIntBits(width) << 32));
                CanvasDrawScope.DrawParams drawParams = canvasDrawScope2.getDrawParams();
                Density density = drawParams.getDensity();
                LayoutDirection layoutDirection2 = drawParams.getLayoutDirection();
                Canvas canvas3 = drawParams.getCanvas();
                long size = drawParams.getSize();
                CanvasDrawScope.DrawParams drawParams2 = canvasDrawScope2.getDrawParams();
                drawParams2.setDensity(cacheDrawScope);
                drawParams2.setLayoutDirection(layoutDirection);
                drawParams2.setCanvas(canvas2);
                drawParams2.m5127setSizeuvyYCjk(m4403constructorimpl);
                canvas2.save();
                CanvasDrawScope canvasDrawScope3 = canvasDrawScope2;
                DrawScope.CC.m5206drawRectnJ9OG0$default(canvasDrawScope3, Color.INSTANCE.m4641getBlack0d7_KjU(), 0L, canvasDrawScope3.mo5121getSizeNHjbRc(), 0.0f, null, null, BlendMode.INSTANCE.m4529getClear0nO6VwU(), 58, null);
                DrawScope.CC.m5206drawRectnJ9OG0$default(canvasDrawScope3, ColorKt.Color(4278190080L), Offset.INSTANCE.m4359getZeroF1C5BW0(), Size.m4403constructorimpl((Float.floatToRawIntBits(f) << 32) | (Float.floatToRawIntBits(f) & 4294967295L)), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
                DrawScope.CC.m5193drawCircleVaOC9Bg$default(canvasDrawScope3, ColorKt.Color(4278190080L), f, Offset.m4335constructorimpl((Float.floatToRawIntBits(f) & 4294967295L) | (Float.floatToRawIntBits(f) << 32)), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
                canvas2.restore();
                CanvasDrawScope.DrawParams drawParams3 = canvasDrawScope2.getDrawParams();
                drawParams3.setDensity(density);
                drawParams3.setLayoutDirection(layoutDirection2);
                drawParams3.setCanvas(canvas3);
                drawParams3.m5127setSizeuvyYCjk(size);
                return imageBitmap2;
            }

            public static final void HandlePopup(final OffsetProvider offsetProvider, final Alignment alignment, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
                int i2;
                Composer startRestartGroup = composer.startRestartGroup(-1090171650);
                ComposerKt.sourceInformation(startRestartGroup, "C(HandlePopup)N(positionProvider,handleReferencePoint,content)221@8864L135,224@9004L190:AndroidSelectionHandles.android.kt#eksfi3");
                if ((i & 6) == 0) {
                    i2 = ((i & 8) == 0 ? startRestartGroup.changed(offsetProvider) : startRestartGroup.changedInstance(offsetProvider) ? 4 : 2) | i;
                } else {
                    i2 = i;
                }
                if ((i & 48) == 0) {
                    i2 |= startRestartGroup.changed(alignment) ? 32 : 16;
                }
                if ((i & 384) == 0) {
                    i2 |= startRestartGroup.changedInstance(function2) ? 256 : 128;
                }
                boolean z = false;
                if (!startRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
                    startRestartGroup.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1090171650, i2, -1, "androidx.compose.foundation.text.selection.HandlePopup (AndroidSelectionHandles.android.kt:219)");
                    }
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 317070917, "CC(remember):AndroidSelectionHandles.android.kt#9igjgp");
                    boolean z2 = (i2 & 112) == 32;
                    if ((i2 & 14) == 4 || ((i2 & 8) != 0 && startRestartGroup.changed(offsetProvider))) {
                        z = true;
                    }
                    boolean z3 = z2 | z;
                    Object rememberedValue = startRestartGroup.rememberedValue();
                    if (z3 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                        rememberedValue = new HandlePositionProvider(alignment, offsetProvider);
                        startRestartGroup.updateRememberedValue(rememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    AndroidPopup_androidKt.Popup((HandlePositionProvider) rememberedValue, null, new PopupProperties(false, false, false, (SecureFlagPolicy) null, true, false, 15, (DefaultConstructorMarker) null), function2, startRestartGroup, ((i2 << 3) & 7168) | 384, 2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            Unit HandlePopup$lambda$6;
                            HandlePopup$lambda$6 = AndroidSelectionHandles_androidKt.HandlePopup$lambda$6(OffsetProvider.this, alignment, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                            return HandlePopup$lambda$6;
                        }
                    });
                }
            }
        }
