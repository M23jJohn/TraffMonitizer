package androidx.compose.foundation.layout;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.layout.ContextualFlowColumnOverflow;
import androidx.compose.foundation.layout.FlowLayoutOverflow;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: FlowLayoutOverflow.kt */
@Deprecated(message = "ContextualFlowLayouts are no longer maintained")
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0089\u0001\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u00120\b\u0002\u0010\u0007\u001a*\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000f\u0018\u00010\b\u00120\b\u0002\u0010\u0010\u001a*\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000f\u0018\u00010\b¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/layout/ContextualFlowColumnOverflow;", "Landroidx/compose/foundation/layout/FlowLayoutOverflow;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/foundation/layout/FlowLayoutOverflow$OverflowType;", "minLinesToShowCollapse", "", "minCrossAxisSizeToShowCollapse", "seeMoreGetter", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "state", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "collapseGetter", "<init>", "(Landroidx/compose/foundation/layout/FlowLayoutOverflow$OverflowType;IILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "Companion", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContextualFlowColumnOverflow extends FlowLayoutOverflow {
    public static final int $stable = 0;
    private static final ContextualFlowColumnOverflow Clip;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ContextualFlowColumnOverflow Visible;

    public /* synthetic */ ContextualFlowColumnOverflow(FlowLayoutOverflow.OverflowType overflowType, int i, int i2, Function1 function1, Function1 function12, DefaultConstructorMarker defaultConstructorMarker) {
        this(overflowType, i, i2, function1, function12);
    }

    /* synthetic */ ContextualFlowColumnOverflow(FlowLayoutOverflow.OverflowType overflowType, int i, int i2, Function1 function1, Function1 function12, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(overflowType, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? null : function1, (i3 & 16) != 0 ? null : function12);
    }

    private ContextualFlowColumnOverflow(FlowLayoutOverflow.OverflowType overflowType, int i, int i2, Function1<? super FlowLayoutOverflowState, ? extends Function2<? super Composer, ? super Integer, Unit>> function1, Function1<? super FlowLayoutOverflowState, ? extends Function2<? super Composer, ? super Integer, Unit>> function12) {
        super(overflowType, i, i2, function1, function12, null);
    }

    /* compiled from: FlowLayoutOverflow.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\f\u001a\u00020\u00052\u001c\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0007¢\u0006\u0002\u0010\u0013J_\u0010\u0014\u001a\u00020\u00052\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u00122\u001c\u0010\u0015\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001a\u0010\u001bR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0003\u001a\u0004\b\u000b\u0010\b¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/layout/ContextualFlowColumnOverflow$Companion;", "", "<init>", "()V", "Visible", "Landroidx/compose/foundation/layout/ContextualFlowColumnOverflow;", "getVisible$annotations", "getVisible", "()Landroidx/compose/foundation/layout/ContextualFlowColumnOverflow;", "Clip", "getClip$annotations", "getClip", "expandIndicator", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ContextualFlowColumnOverflowScope;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/layout/ContextualFlowColumnOverflow;", "expandOrCollapseIndicator", "collapseIndicator", "minColumnsToShowCollapse", "", "minWidthToShowCollapse", "Landroidx/compose/ui/unit/Dp;", "expandOrCollapseIndicator--jt2gSs", "(Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;IFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/layout/ContextualFlowColumnOverflow;", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @Deprecated(message = "ContextualFlowLayouts are no longer maintained")
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getClip$annotations() {
        }

        public static /* synthetic */ void getVisible$annotations() {
        }

        private Companion() {
        }

        public final ContextualFlowColumnOverflow getVisible() {
            return ContextualFlowColumnOverflow.Visible;
        }

        public final ContextualFlowColumnOverflow getClip() {
            return ContextualFlowColumnOverflow.Clip;
        }

        public final ContextualFlowColumnOverflow expandIndicator(final Function3<? super ContextualFlowColumnOverflowScope, ? super Composer, ? super Integer, Unit> content) {
            return new ContextualFlowColumnOverflow(FlowLayoutOverflow.OverflowType.ExpandIndicator, 0, 0, new Function1() { // from class: androidx.compose.foundation.layout.ContextualFlowColumnOverflow$Companion$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Function2 expandIndicator$lambda$0;
                    expandIndicator$lambda$0 = ContextualFlowColumnOverflow.Companion.expandIndicator$lambda$0(Function3.this, (FlowLayoutOverflowState) obj);
                    return expandIndicator$lambda$0;
                }
            }, null, 22, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Function2 expandIndicator$lambda$0(final Function3 function3, final FlowLayoutOverflowState flowLayoutOverflowState) {
            return ComposableLambdaKt.composableLambdaInstance(-800948529, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowColumnOverflow$Companion$expandIndicator$seeMoreGetter$1$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    ComposerKt.sourceInformation(composer, "C505@23919L9:FlowLayoutOverflow.kt#2w3rfo");
                    if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-800948529, i, -1, "androidx.compose.foundation.layout.ContextualFlowColumnOverflow.Companion.expandIndicator.<anonymous>.<anonymous> (FlowLayoutOverflow.kt:504)");
                    }
                    function3.invoke(new ContextualFlowColumnOverflowScopeImpl(FlowLayoutOverflowState.this), composer, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            });
        }

        /* renamed from: expandOrCollapseIndicator--jt2gSs, reason: not valid java name */
        public final ContextualFlowColumnOverflow m773expandOrCollapseIndicatorjt2gSs(final Function3<? super ContextualFlowColumnOverflowScope, ? super Composer, ? super Integer, Unit> function3, final Function3<? super ContextualFlowColumnOverflowScope, ? super Composer, ? super Integer, Unit> function32, int i, float f, Composer composer, int i2, int i3) {
            ComposerKt.sourceInformationMarkerStart(composer, -1169622732, "C(expandOrCollapseIndicator)N(expandIndicator,collapseIndicator,minColumnsToShowCollapse,minWidthToShowCollapse:c#ui.unit.Dp)545@26090L7,546@26157L1124:FlowLayoutOverflow.kt#2w3rfo");
            boolean z = true;
            int i4 = (i3 & 4) != 0 ? 1 : i;
            float m7399constructorimpl = (i3 & 8) != 0 ? Dp.m7399constructorimpl(0) : f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1169622732, i2, -1, "androidx.compose.foundation.layout.ContextualFlowColumnOverflow.Companion.expandOrCollapseIndicator (FlowLayoutOverflow.kt:543)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composer);
            int mo453roundToPx0680j_4 = ((Density) consume).mo453roundToPx0680j_4(m7399constructorimpl);
            ComposerKt.sourceInformationMarkerStart(composer, 1274004440, "CC(remember):FlowLayoutOverflow.kt#9igjgp");
            boolean changed = ((((i2 & 896) ^ 384) > 256 && composer.changed(i4)) || (i2 & 384) == 256) | composer.changed(mo453roundToPx0680j_4) | ((((i2 & 14) ^ 6) > 4 && composer.changed(function3)) || (i2 & 6) == 4);
            if ((((i2 & 112) ^ 48) <= 32 || !composer.changed(function32)) && (i2 & 48) != 32) {
                z = false;
            }
            boolean z2 = changed | z;
            Object rememberedValue = composer.rememberedValue();
            if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                ContextualFlowColumnOverflow contextualFlowColumnOverflow = new ContextualFlowColumnOverflow(FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator, i4, mo453roundToPx0680j_4, new Function1() { // from class: androidx.compose.foundation.layout.ContextualFlowColumnOverflow$Companion$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Function2 expandOrCollapseIndicator__jt2gSs$lambda$4$lambda$2;
                        expandOrCollapseIndicator__jt2gSs$lambda$4$lambda$2 = ContextualFlowColumnOverflow.Companion.expandOrCollapseIndicator__jt2gSs$lambda$4$lambda$2(Function3.this, (FlowLayoutOverflowState) obj);
                        return expandOrCollapseIndicator__jt2gSs$lambda$4$lambda$2;
                    }
                }, new Function1() { // from class: androidx.compose.foundation.layout.ContextualFlowColumnOverflow$Companion$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Function2 expandOrCollapseIndicator__jt2gSs$lambda$4$lambda$3;
                        expandOrCollapseIndicator__jt2gSs$lambda$4$lambda$3 = ContextualFlowColumnOverflow.Companion.expandOrCollapseIndicator__jt2gSs$lambda$4$lambda$3(Function3.this, (FlowLayoutOverflowState) obj);
                        return expandOrCollapseIndicator__jt2gSs$lambda$4$lambda$3;
                    }
                }, null);
                composer.updateRememberedValue(contextualFlowColumnOverflow);
                rememberedValue = contextualFlowColumnOverflow;
            }
            ContextualFlowColumnOverflow contextualFlowColumnOverflow2 = (ContextualFlowColumnOverflow) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            return contextualFlowColumnOverflow2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Function2 expandOrCollapseIndicator__jt2gSs$lambda$4$lambda$2(final Function3 function3, final FlowLayoutOverflowState flowLayoutOverflowState) {
            return ComposableLambdaKt.composableLambdaInstance(494662780, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowColumnOverflow$Companion$expandOrCollapseIndicator$1$seeMoreGetter$1$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    ComposerKt.sourceInformation(composer, "C555@26552L17:FlowLayoutOverflow.kt#2w3rfo");
                    if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(494662780, i, -1, "androidx.compose.foundation.layout.ContextualFlowColumnOverflow.Companion.expandOrCollapseIndicator.<anonymous>.<anonymous>.<anonymous> (FlowLayoutOverflow.kt:554)");
                    }
                    function3.invoke(new ContextualFlowColumnOverflowScopeImpl(FlowLayoutOverflowState.this), composer, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Function2 expandOrCollapseIndicator__jt2gSs$lambda$4$lambda$3(final Function3 function3, final FlowLayoutOverflowState flowLayoutOverflowState) {
            return ComposableLambdaKt.composableLambdaInstance(1829455235, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowColumnOverflow$Companion$expandOrCollapseIndicator$1$collapseGetter$1$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    ComposerKt.sourceInformation(composer, "C562@26829L19:FlowLayoutOverflow.kt#2w3rfo");
                    if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1829455235, i, -1, "androidx.compose.foundation.layout.ContextualFlowColumnOverflow.Companion.expandOrCollapseIndicator.<anonymous>.<anonymous>.<anonymous> (FlowLayoutOverflow.kt:561)");
                    }
                    function3.invoke(new ContextualFlowColumnOverflowScopeImpl(FlowLayoutOverflowState.this), composer, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            });
        }
    }

    static {
        int i = 0;
        Function1 function1 = null;
        Visible = new ContextualFlowColumnOverflow(FlowLayoutOverflow.OverflowType.Visible, 0, i, null, function1, 30, null);
        Clip = new ContextualFlowColumnOverflow(FlowLayoutOverflow.OverflowType.Clip, i, 0, function1, null, 30, null);
    }
}
