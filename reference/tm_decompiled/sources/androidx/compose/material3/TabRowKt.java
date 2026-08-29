package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TabRow.kt */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\u001a\u0094\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00012\u001e\b\u0002\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u00172\u0013\b\u0002\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u00162\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0080\u0001\u0010\u001d\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u001e\b\u0002\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u00172\u0013\b\u0002\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u00162\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u009a\u0001\u0010 \u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00012.\b\u0002\u0010\u0013\u001a(\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\"0!¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u00162\u0013\b\u0002\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u00162\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a\u0086\u0001\u0010(\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u00172\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u00162\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u0016H\u0003ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a \u0001\u0010+\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2,\u0010\u0013\u001a(\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\"0!¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u00162\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00012\u0013\b\u0002\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u00162\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u00162\u0006\u0010\r\u001a\u00020\u000eH\u0003ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001a\u0094\u0001\u0010.\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00012\u001e\b\u0002\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u00172\u0013\b\u0002\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u00162\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u0010\u001c\u001a\u0080\u0001\u00100\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u001e\b\u0002\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u00172\u0013\b\u0002\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u00162\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b1\u0010\u001f\u001a\u0090\u0001\u00102\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102.\b\u0002\u0010\u0013\u001a(\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\"0!¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u00162\u0013\b\u0002\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u00162\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b3\u0010\u001f\u001an\u00104\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u00172\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u00162\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u0016H\u0003ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a~\u00107\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102,\u0010\u0013\u001a(\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\"0!¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u00162\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u00162\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0002\b\u0016H\u0003ø\u0001\u0000¢\u0006\u0004\b8\u00106\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00069"}, d2 = {"ScrollableTabRowMinimumTabWidth", "Landroidx/compose/ui/unit/Dp;", "F", "ScrollableTabRowScrollSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "TabRowIndicatorSpec", "PrimaryScrollableTabRow", "", "selectedTabIndex", "", "modifier", "Landroidx/compose/ui/Modifier;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "edgePadding", "indicator", "Lkotlin/Function1;", "Landroidx/compose/material3/TabIndicatorScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "divider", "Lkotlin/Function0;", "tabs", "PrimaryScrollableTabRow-qhFBPw4", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "PrimaryTabRow", "PrimaryTabRow-pAZo6Ak", "(ILandroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ScrollableTabRow", "", "Landroidx/compose/material3/TabPosition;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "tabPositions", "ScrollableTabRow-sKfQg0A", "(ILandroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ScrollableTabRowImpl", "ScrollableTabRowImpl-sKfQg0A", "(ILandroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/ScrollState;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "ScrollableTabRowWithSubcomposeImpl", "ScrollableTabRowWithSubcomposeImpl-qhFBPw4", "(ILkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/ScrollState;Landroidx/compose/runtime/Composer;II)V", "SecondaryScrollableTabRow", "SecondaryScrollableTabRow-qhFBPw4", "SecondaryTabRow", "SecondaryTabRow-pAZo6Ak", "TabRow", "TabRow-pAZo6Ak", "TabRowImpl", "TabRowImpl-DTcfvLk", "(Landroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TabRowWithSubcomposeImpl", "TabRowWithSubcomposeImpl-DTcfvLk", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TabRowKt {
    private static final float ScrollableTabRowMinimumTabWidth = Dp.m7399constructorimpl(90);
    private static final AnimationSpec<Float> ScrollableTabRowScrollSpec = AnimationSpecKt.tween$default(250, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
    private static final AnimationSpec<Dp> TabRowIndicatorSpec = AnimationSpecKt.tween$default(250, 0, EasingKt.getFastOutSlowInEasing(), 2, null);

    /* JADX WARN: Removed duplicated region for block: B:100:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0082  */
    /* renamed from: PrimaryTabRow-pAZo6Ak, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2748PrimaryTabRowpAZo6Ak(final int i, Modifier modifier, long j, long j2, Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long j3;
        long j4;
        int i5;
        Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function32;
        int i6;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Function2<? super Composer, ? super Integer, Unit> m2138getLambda1$material3_release;
        Modifier modifier3;
        long j5;
        Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function33;
        int i7;
        long j6;
        Composer composer2;
        final Modifier modifier4;
        final long j7;
        final long j8;
        final Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function34;
        final Function2<? super Composer, ? super Integer, Unit> function24;
        ScopeUpdateScope endRestartGroup;
        int i8;
        int i9;
        Composer startRestartGroup = composer.startRestartGroup(-1884787284);
        ComposerKt.sourceInformation(startRestartGroup, "C(PrimaryTabRow)P(5,4,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3)155@7603L21,156@7667L19,157@7746L189,166@8053L76:TabRow.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (startRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i10 = i3 & 2;
        if (i10 != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i2 & 384) != 0) {
                if ((i3 & 4) == 0) {
                    j3 = j;
                    if (startRestartGroup.changed(j3)) {
                        i9 = 256;
                        i4 |= i9;
                    }
                } else {
                    j3 = j;
                }
                i9 = 128;
                i4 |= i9;
            } else {
                j3 = j;
            }
            if ((i2 & 3072) != 0) {
                if ((i3 & 8) == 0) {
                    j4 = j2;
                    if (startRestartGroup.changed(j4)) {
                        i8 = 2048;
                        i4 |= i8;
                    }
                } else {
                    j4 = j2;
                }
                i8 = 1024;
                i4 |= i8;
            } else {
                j4 = j2;
            }
            i5 = i3 & 16;
            if (i5 == 0) {
                i4 |= 24576;
            } else if ((i2 & 24576) == 0) {
                function32 = function3;
                i4 |= startRestartGroup.changedInstance(function32) ? 16384 : 8192;
                i6 = i3 & 32;
                if (i6 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i2) == 0) {
                    function23 = function2;
                    i4 |= startRestartGroup.changedInstance(function23) ? 131072 : 65536;
                    if ((i3 & 64) == 0) {
                        i4 |= 1572864;
                    } else if ((i2 & 1572864) == 0) {
                        i4 |= startRestartGroup.changedInstance(function22) ? 1048576 : 524288;
                        if ((i4 & 599187) == 599186 || !startRestartGroup.getSkipping()) {
                            startRestartGroup.startDefaults();
                            if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                if (i10 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i3 & 4) != 0) {
                                    j3 = TabRowDefaults.INSTANCE.getPrimaryContainerColor(startRestartGroup, 6);
                                    i4 &= -897;
                                }
                                if ((i3 & 8) != 0) {
                                    j4 = TabRowDefaults.INSTANCE.getPrimaryContentColor(startRestartGroup, 6);
                                    i4 &= -7169;
                                }
                                if (i5 != 0) {
                                    function32 = ComposableLambdaKt.rememberComposableLambda(-2021049253, true, new Function3<TabIndicatorScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$PrimaryTabRow$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(TabIndicatorScope tabIndicatorScope, Composer composer3, Integer num) {
                                            invoke(tabIndicatorScope, composer3, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(TabIndicatorScope tabIndicatorScope, Composer composer3, int i11) {
                                            int i12;
                                            ComposerKt.sourceInformation(composer3, "C158@7771L158:TabRow.kt#uh7d8r");
                                            if ((i11 & 6) == 0) {
                                                i12 = ((i11 & 8) == 0 ? composer3.changed(tabIndicatorScope) : composer3.changedInstance(tabIndicatorScope) ? 4 : 2) | i11;
                                            } else {
                                                i12 = i11;
                                            }
                                            if ((i12 & 19) != 18 || !composer3.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-2021049253, i12, -1, "androidx.compose.material3.PrimaryTabRow.<anonymous> (TabRow.kt:158)");
                                                }
                                                TabRowDefaults.INSTANCE.m2743PrimaryIndicator10LGxhE(tabIndicatorScope.tabIndicatorOffset(Modifier.INSTANCE, i, true), Dp.INSTANCE.m7419getUnspecifiedD9Ej5fM(), 0.0f, 0L, null, composer3, 196656, 28);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer3.skipToGroupEnd();
                                        }
                                    }, startRestartGroup, 54);
                                }
                                if (i6 != 0) {
                                    m2138getLambda1$material3_release = ComposableSingletons$TabRowKt.INSTANCE.m2138getLambda1$material3_release();
                                    modifier3 = modifier2;
                                    j5 = j4;
                                    function33 = function32;
                                    i7 = -1884787284;
                                    j6 = j3;
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(i7, i4, -1, "androidx.compose.material3.PrimaryTabRow (TabRow.kt:165)");
                                    }
                                    composer2 = startRestartGroup;
                                    m2755TabRowImplDTcfvLk(modifier3, j6, j5, function33, m2138getLambda1$material3_release, function22, composer2, (i4 >> 3) & 524286);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    modifier4 = modifier3;
                                    j7 = j6;
                                    j8 = j5;
                                    function34 = function33;
                                    function24 = m2138getLambda1$material3_release;
                                }
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                }
                                if ((i3 & 8) != 0) {
                                    i4 &= -7169;
                                }
                            }
                            modifier3 = modifier2;
                            function33 = function32;
                            m2138getLambda1$material3_release = function23;
                            i7 = -1884787284;
                            j6 = j3;
                            j5 = j4;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer2 = startRestartGroup;
                            m2755TabRowImplDTcfvLk(modifier3, j6, j5, function33, m2138getLambda1$material3_release, function22, composer2, (i4 >> 3) & 524286);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            modifier4 = modifier3;
                            j7 = j6;
                            j8 = j5;
                            function34 = function33;
                            function24 = m2138getLambda1$material3_release;
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            composer2 = startRestartGroup;
                            modifier4 = modifier2;
                            j7 = j3;
                            j8 = j4;
                            function34 = function32;
                            function24 = function23;
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$PrimaryTabRow$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i11) {
                                    TabRowKt.m2748PrimaryTabRowpAZo6Ak(i, modifier4, j7, j8, function34, function24, function22, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if ((i4 & 599187) == 599186) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                    }
                    if (i10 != 0) {
                    }
                    if ((i3 & 4) != 0) {
                    }
                    if ((i3 & 8) != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if (i6 != 0) {
                    }
                    modifier3 = modifier2;
                    function33 = function32;
                    m2138getLambda1$material3_release = function23;
                    i7 = -1884787284;
                    j6 = j3;
                    j5 = j4;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    composer2 = startRestartGroup;
                    m2755TabRowImplDTcfvLk(modifier3, j6, j5, function33, m2138getLambda1$material3_release, function22, composer2, (i4 >> 3) & 524286);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    modifier4 = modifier3;
                    j7 = j6;
                    j8 = j5;
                    function34 = function33;
                    function24 = m2138getLambda1$material3_release;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                function23 = function2;
                if ((i3 & 64) == 0) {
                }
                if ((i4 & 599187) == 599186) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i10 != 0) {
                }
                if ((i3 & 4) != 0) {
                }
                if ((i3 & 8) != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                modifier3 = modifier2;
                function33 = function32;
                m2138getLambda1$material3_release = function23;
                i7 = -1884787284;
                j6 = j3;
                j5 = j4;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                composer2 = startRestartGroup;
                m2755TabRowImplDTcfvLk(modifier3, j6, j5, function33, m2138getLambda1$material3_release, function22, composer2, (i4 >> 3) & 524286);
                if (ComposerKt.isTraceInProgress()) {
                }
                modifier4 = modifier3;
                j7 = j6;
                j8 = j5;
                function34 = function33;
                function24 = m2138getLambda1$material3_release;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            function32 = function3;
            i6 = i3 & 32;
            if (i6 != 0) {
            }
            function23 = function2;
            if ((i3 & 64) == 0) {
            }
            if ((i4 & 599187) == 599186) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i10 != 0) {
            }
            if ((i3 & 4) != 0) {
            }
            if ((i3 & 8) != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            modifier3 = modifier2;
            function33 = function32;
            m2138getLambda1$material3_release = function23;
            i7 = -1884787284;
            j6 = j3;
            j5 = j4;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            composer2 = startRestartGroup;
            m2755TabRowImplDTcfvLk(modifier3, j6, j5, function33, m2138getLambda1$material3_release, function22, composer2, (i4 >> 3) & 524286);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier4 = modifier3;
            j7 = j6;
            j8 = j5;
            function34 = function33;
            function24 = m2138getLambda1$material3_release;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        if ((i2 & 384) != 0) {
        }
        if ((i2 & 3072) != 0) {
        }
        i5 = i3 & 16;
        if (i5 == 0) {
        }
        function32 = function3;
        i6 = i3 & 32;
        if (i6 != 0) {
        }
        function23 = function2;
        if ((i3 & 64) == 0) {
        }
        if ((i4 & 599187) == 599186) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i10 != 0) {
        }
        if ((i3 & 4) != 0) {
        }
        if ((i3 & 8) != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        modifier3 = modifier2;
        function33 = function32;
        m2138getLambda1$material3_release = function23;
        i7 = -1884787284;
        j6 = j3;
        j5 = j4;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        composer2 = startRestartGroup;
        m2755TabRowImplDTcfvLk(modifier3, j6, j5, function33, m2138getLambda1$material3_release, function22, composer2, (i4 >> 3) & 524286);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier4 = modifier3;
        j7 = j6;
        j8 = j5;
        function34 = function33;
        function24 = m2138getLambda1$material3_release;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0082  */
    /* renamed from: SecondaryTabRow-pAZo6Ak, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2753SecondaryTabRowpAZo6Ak(final int i, Modifier modifier, long j, long j2, Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long j3;
        long j4;
        int i5;
        Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function32;
        int i6;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Function2<? super Composer, ? super Integer, Unit> m2139getLambda2$material3_release;
        Modifier modifier3;
        long j5;
        Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function33;
        int i7;
        long j6;
        Composer composer2;
        final Modifier modifier4;
        final long j7;
        final long j8;
        final Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function34;
        final Function2<? super Composer, ? super Integer, Unit> function24;
        ScopeUpdateScope endRestartGroup;
        int i8;
        int i9;
        Composer startRestartGroup = composer.startRestartGroup(-1909540706);
        ComposerKt.sourceInformation(startRestartGroup, "C(SecondaryTabRow)P(5,4,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3)209@10549L23,210@10615L21,212@10716L160,220@10994L76:TabRow.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (startRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i10 = i3 & 2;
        if (i10 != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i2 & 384) != 0) {
                if ((i3 & 4) == 0) {
                    j3 = j;
                    if (startRestartGroup.changed(j3)) {
                        i9 = 256;
                        i4 |= i9;
                    }
                } else {
                    j3 = j;
                }
                i9 = 128;
                i4 |= i9;
            } else {
                j3 = j;
            }
            if ((i2 & 3072) != 0) {
                if ((i3 & 8) == 0) {
                    j4 = j2;
                    if (startRestartGroup.changed(j4)) {
                        i8 = 2048;
                        i4 |= i8;
                    }
                } else {
                    j4 = j2;
                }
                i8 = 1024;
                i4 |= i8;
            } else {
                j4 = j2;
            }
            i5 = i3 & 16;
            if (i5 == 0) {
                i4 |= 24576;
            } else if ((i2 & 24576) == 0) {
                function32 = function3;
                i4 |= startRestartGroup.changedInstance(function32) ? 16384 : 8192;
                i6 = i3 & 32;
                if (i6 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i2) == 0) {
                    function23 = function2;
                    i4 |= startRestartGroup.changedInstance(function23) ? 131072 : 65536;
                    if ((i3 & 64) == 0) {
                        i4 |= 1572864;
                    } else if ((i2 & 1572864) == 0) {
                        i4 |= startRestartGroup.changedInstance(function22) ? 1048576 : 524288;
                        if ((i4 & 599187) == 599186 || !startRestartGroup.getSkipping()) {
                            startRestartGroup.startDefaults();
                            if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                if (i10 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i3 & 4) != 0) {
                                    j3 = TabRowDefaults.INSTANCE.getSecondaryContainerColor(startRestartGroup, 6);
                                    i4 &= -897;
                                }
                                if ((i3 & 8) != 0) {
                                    j4 = TabRowDefaults.INSTANCE.getSecondaryContentColor(startRestartGroup, 6);
                                    i4 &= -7169;
                                }
                                if (i5 != 0) {
                                    function32 = ComposableLambdaKt.rememberComposableLambda(286693261, true, new Function3<TabIndicatorScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$SecondaryTabRow$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(TabIndicatorScope tabIndicatorScope, Composer composer3, Integer num) {
                                            invoke(tabIndicatorScope, composer3, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(TabIndicatorScope tabIndicatorScope, Composer composer3, int i11) {
                                            ComposerKt.sourceInformation(composer3, "C213@10745L121:TabRow.kt#uh7d8r");
                                            if ((i11 & 6) == 0) {
                                                i11 |= (i11 & 8) == 0 ? composer3.changed(tabIndicatorScope) : composer3.changedInstance(tabIndicatorScope) ? 4 : 2;
                                            }
                                            if ((i11 & 19) != 18 || !composer3.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(286693261, i11, -1, "androidx.compose.material3.SecondaryTabRow.<anonymous> (TabRow.kt:213)");
                                                }
                                                TabRowDefaults.INSTANCE.m2744SecondaryIndicator9IZ8Weo(tabIndicatorScope.tabIndicatorOffset(Modifier.INSTANCE, i, false), 0.0f, 0L, composer3, 3072, 6);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer3.skipToGroupEnd();
                                        }
                                    }, startRestartGroup, 54);
                                }
                                if (i6 != 0) {
                                    m2139getLambda2$material3_release = ComposableSingletons$TabRowKt.INSTANCE.m2139getLambda2$material3_release();
                                    modifier3 = modifier2;
                                    j5 = j4;
                                    function33 = function32;
                                    i7 = -1909540706;
                                    j6 = j3;
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(i7, i4, -1, "androidx.compose.material3.SecondaryTabRow (TabRow.kt:219)");
                                    }
                                    composer2 = startRestartGroup;
                                    m2755TabRowImplDTcfvLk(modifier3, j6, j5, function33, m2139getLambda2$material3_release, function22, composer2, (i4 >> 3) & 524286);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    modifier4 = modifier3;
                                    j7 = j6;
                                    j8 = j5;
                                    function34 = function33;
                                    function24 = m2139getLambda2$material3_release;
                                }
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                }
                                if ((i3 & 8) != 0) {
                                    i4 &= -7169;
                                }
                            }
                            modifier3 = modifier2;
                            function33 = function32;
                            m2139getLambda2$material3_release = function23;
                            i7 = -1909540706;
                            j6 = j3;
                            j5 = j4;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer2 = startRestartGroup;
                            m2755TabRowImplDTcfvLk(modifier3, j6, j5, function33, m2139getLambda2$material3_release, function22, composer2, (i4 >> 3) & 524286);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            modifier4 = modifier3;
                            j7 = j6;
                            j8 = j5;
                            function34 = function33;
                            function24 = m2139getLambda2$material3_release;
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            composer2 = startRestartGroup;
                            modifier4 = modifier2;
                            j7 = j3;
                            j8 = j4;
                            function34 = function32;
                            function24 = function23;
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$SecondaryTabRow$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i11) {
                                    TabRowKt.m2753SecondaryTabRowpAZo6Ak(i, modifier4, j7, j8, function34, function24, function22, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if ((i4 & 599187) == 599186) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                    }
                    if (i10 != 0) {
                    }
                    if ((i3 & 4) != 0) {
                    }
                    if ((i3 & 8) != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if (i6 != 0) {
                    }
                    modifier3 = modifier2;
                    function33 = function32;
                    m2139getLambda2$material3_release = function23;
                    i7 = -1909540706;
                    j6 = j3;
                    j5 = j4;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    composer2 = startRestartGroup;
                    m2755TabRowImplDTcfvLk(modifier3, j6, j5, function33, m2139getLambda2$material3_release, function22, composer2, (i4 >> 3) & 524286);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    modifier4 = modifier3;
                    j7 = j6;
                    j8 = j5;
                    function34 = function33;
                    function24 = m2139getLambda2$material3_release;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                function23 = function2;
                if ((i3 & 64) == 0) {
                }
                if ((i4 & 599187) == 599186) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i10 != 0) {
                }
                if ((i3 & 4) != 0) {
                }
                if ((i3 & 8) != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                modifier3 = modifier2;
                function33 = function32;
                m2139getLambda2$material3_release = function23;
                i7 = -1909540706;
                j6 = j3;
                j5 = j4;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                composer2 = startRestartGroup;
                m2755TabRowImplDTcfvLk(modifier3, j6, j5, function33, m2139getLambda2$material3_release, function22, composer2, (i4 >> 3) & 524286);
                if (ComposerKt.isTraceInProgress()) {
                }
                modifier4 = modifier3;
                j7 = j6;
                j8 = j5;
                function34 = function33;
                function24 = m2139getLambda2$material3_release;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            function32 = function3;
            i6 = i3 & 32;
            if (i6 != 0) {
            }
            function23 = function2;
            if ((i3 & 64) == 0) {
            }
            if ((i4 & 599187) == 599186) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i10 != 0) {
            }
            if ((i3 & 4) != 0) {
            }
            if ((i3 & 8) != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            modifier3 = modifier2;
            function33 = function32;
            m2139getLambda2$material3_release = function23;
            i7 = -1909540706;
            j6 = j3;
            j5 = j4;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            composer2 = startRestartGroup;
            m2755TabRowImplDTcfvLk(modifier3, j6, j5, function33, m2139getLambda2$material3_release, function22, composer2, (i4 >> 3) & 524286);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier4 = modifier3;
            j7 = j6;
            j8 = j5;
            function34 = function33;
            function24 = m2139getLambda2$material3_release;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        if ((i2 & 384) != 0) {
        }
        if ((i2 & 3072) != 0) {
        }
        i5 = i3 & 16;
        if (i5 == 0) {
        }
        function32 = function3;
        i6 = i3 & 32;
        if (i6 != 0) {
        }
        function23 = function2;
        if ((i3 & 64) == 0) {
        }
        if ((i4 & 599187) == 599186) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i10 != 0) {
        }
        if ((i3 & 4) != 0) {
        }
        if ((i3 & 8) != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        modifier3 = modifier2;
        function33 = function32;
        m2139getLambda2$material3_release = function23;
        i7 = -1909540706;
        j6 = j3;
        j5 = j4;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        composer2 = startRestartGroup;
        m2755TabRowImplDTcfvLk(modifier3, j6, j5, function33, m2139getLambda2$material3_release, function22, composer2, (i4 >> 3) & 524286);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier4 = modifier3;
        j7 = j6;
        j8 = j5;
        function34 = function33;
        function24 = m2139getLambda2$material3_release;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0082  */
    /* renamed from: TabRow-pAZo6Ak, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2754TabRowpAZo6Ak(final int i, Modifier modifier, long j, long j2, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long j3;
        long j4;
        int i5;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function32;
        int i6;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Function2<? super Composer, ? super Integer, Unit> m2140getLambda3$material3_release;
        Modifier modifier3;
        long j5;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function33;
        int i7;
        long j6;
        Composer composer2;
        final Modifier modifier4;
        final long j7;
        final long j8;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function34;
        final Function2<? super Composer, ? super Integer, Unit> function24;
        ScopeUpdateScope endRestartGroup;
        int i8;
        int i9;
        Composer startRestartGroup = composer.startRestartGroup(-1199178586);
        ComposerKt.sourceInformation(startRestartGroup, "C(TabRow)P(5,4,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3)303@15331L21,304@15395L19,306@15507L246,316@15871L90:TabRow.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (startRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i10 = i3 & 2;
        if (i10 != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i2 & 384) != 0) {
                if ((i3 & 4) == 0) {
                    j3 = j;
                    if (startRestartGroup.changed(j3)) {
                        i9 = 256;
                        i4 |= i9;
                    }
                } else {
                    j3 = j;
                }
                i9 = 128;
                i4 |= i9;
            } else {
                j3 = j;
            }
            if ((i2 & 3072) != 0) {
                if ((i3 & 8) == 0) {
                    j4 = j2;
                    if (startRestartGroup.changed(j4)) {
                        i8 = 2048;
                        i4 |= i8;
                    }
                } else {
                    j4 = j2;
                }
                i8 = 1024;
                i4 |= i8;
            } else {
                j4 = j2;
            }
            i5 = i3 & 16;
            if (i5 == 0) {
                i4 |= 24576;
            } else if ((i2 & 24576) == 0) {
                function32 = function3;
                i4 |= startRestartGroup.changedInstance(function32) ? 16384 : 8192;
                i6 = i3 & 32;
                if (i6 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i2) == 0) {
                    function23 = function2;
                    i4 |= startRestartGroup.changedInstance(function23) ? 131072 : 65536;
                    if ((i3 & 64) == 0) {
                        i4 |= 1572864;
                    } else if ((i2 & 1572864) == 0) {
                        i4 |= startRestartGroup.changedInstance(function22) ? 1048576 : 524288;
                        if ((i4 & 599187) == 599186 || !startRestartGroup.getSkipping()) {
                            startRestartGroup.startDefaults();
                            if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                if (i10 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i3 & 4) != 0) {
                                    j3 = TabRowDefaults.INSTANCE.getPrimaryContainerColor(startRestartGroup, 6);
                                    i4 &= -897;
                                }
                                if ((i3 & 8) != 0) {
                                    j4 = TabRowDefaults.INSTANCE.getPrimaryContentColor(startRestartGroup, 6);
                                    i4 &= -7169;
                                }
                                if (i5 != 0) {
                                    function32 = ComposableLambdaKt.rememberComposableLambda(-2052073983, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(List<? extends TabPosition> list, Composer composer3, Integer num) {
                                            invoke((List<TabPosition>) list, composer3, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(List<TabPosition> list, Composer composer3, int i11) {
                                            ComposerKt.sourceInformation(composer3, "C308@15612L117:TabRow.kt#uh7d8r");
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-2052073983, i11, -1, "androidx.compose.material3.TabRow.<anonymous> (TabRow.kt:307)");
                                            }
                                            if (i < list.size()) {
                                                TabRowDefaults.INSTANCE.m2744SecondaryIndicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, list.get(i)), 0.0f, 0L, composer3, 3072, 6);
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    }, startRestartGroup, 54);
                                }
                                if (i6 != 0) {
                                    m2140getLambda3$material3_release = ComposableSingletons$TabRowKt.INSTANCE.m2140getLambda3$material3_release();
                                    modifier3 = modifier2;
                                    j5 = j4;
                                    function33 = function32;
                                    i7 = -1199178586;
                                    j6 = j3;
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(i7, i4, -1, "androidx.compose.material3.TabRow (TabRow.kt:315)");
                                    }
                                    composer2 = startRestartGroup;
                                    m2756TabRowWithSubcomposeImplDTcfvLk(modifier3, j6, j5, function33, m2140getLambda3$material3_release, function22, composer2, (i4 >> 3) & 524286);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    modifier4 = modifier3;
                                    j7 = j6;
                                    j8 = j5;
                                    function34 = function33;
                                    function24 = m2140getLambda3$material3_release;
                                }
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                }
                                if ((i3 & 8) != 0) {
                                    i4 &= -7169;
                                }
                            }
                            modifier3 = modifier2;
                            function33 = function32;
                            m2140getLambda3$material3_release = function23;
                            i7 = -1199178586;
                            j6 = j3;
                            j5 = j4;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer2 = startRestartGroup;
                            m2756TabRowWithSubcomposeImplDTcfvLk(modifier3, j6, j5, function33, m2140getLambda3$material3_release, function22, composer2, (i4 >> 3) & 524286);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            modifier4 = modifier3;
                            j7 = j6;
                            j8 = j5;
                            function34 = function33;
                            function24 = m2140getLambda3$material3_release;
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            composer2 = startRestartGroup;
                            modifier4 = modifier2;
                            j7 = j3;
                            j8 = j4;
                            function34 = function32;
                            function24 = function23;
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i11) {
                                    TabRowKt.m2754TabRowpAZo6Ak(i, modifier4, j7, j8, function34, function24, function22, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if ((i4 & 599187) == 599186) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                    }
                    if (i10 != 0) {
                    }
                    if ((i3 & 4) != 0) {
                    }
                    if ((i3 & 8) != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if (i6 != 0) {
                    }
                    modifier3 = modifier2;
                    function33 = function32;
                    m2140getLambda3$material3_release = function23;
                    i7 = -1199178586;
                    j6 = j3;
                    j5 = j4;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    composer2 = startRestartGroup;
                    m2756TabRowWithSubcomposeImplDTcfvLk(modifier3, j6, j5, function33, m2140getLambda3$material3_release, function22, composer2, (i4 >> 3) & 524286);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    modifier4 = modifier3;
                    j7 = j6;
                    j8 = j5;
                    function34 = function33;
                    function24 = m2140getLambda3$material3_release;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                function23 = function2;
                if ((i3 & 64) == 0) {
                }
                if ((i4 & 599187) == 599186) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i10 != 0) {
                }
                if ((i3 & 4) != 0) {
                }
                if ((i3 & 8) != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                modifier3 = modifier2;
                function33 = function32;
                m2140getLambda3$material3_release = function23;
                i7 = -1199178586;
                j6 = j3;
                j5 = j4;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                composer2 = startRestartGroup;
                m2756TabRowWithSubcomposeImplDTcfvLk(modifier3, j6, j5, function33, m2140getLambda3$material3_release, function22, composer2, (i4 >> 3) & 524286);
                if (ComposerKt.isTraceInProgress()) {
                }
                modifier4 = modifier3;
                j7 = j6;
                j8 = j5;
                function34 = function33;
                function24 = m2140getLambda3$material3_release;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            function32 = function3;
            i6 = i3 & 32;
            if (i6 != 0) {
            }
            function23 = function2;
            if ((i3 & 64) == 0) {
            }
            if ((i4 & 599187) == 599186) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i10 != 0) {
            }
            if ((i3 & 4) != 0) {
            }
            if ((i3 & 8) != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            modifier3 = modifier2;
            function33 = function32;
            m2140getLambda3$material3_release = function23;
            i7 = -1199178586;
            j6 = j3;
            j5 = j4;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            composer2 = startRestartGroup;
            m2756TabRowWithSubcomposeImplDTcfvLk(modifier3, j6, j5, function33, m2140getLambda3$material3_release, function22, composer2, (i4 >> 3) & 524286);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier4 = modifier3;
            j7 = j6;
            j8 = j5;
            function34 = function33;
            function24 = m2140getLambda3$material3_release;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        if ((i2 & 384) != 0) {
        }
        if ((i2 & 3072) != 0) {
        }
        i5 = i3 & 16;
        if (i5 == 0) {
        }
        function32 = function3;
        i6 = i3 & 32;
        if (i6 != 0) {
        }
        function23 = function2;
        if ((i3 & 64) == 0) {
        }
        if ((i4 & 599187) == 599186) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i10 != 0) {
        }
        if ((i3 & 4) != 0) {
        }
        if ((i3 & 8) != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        modifier3 = modifier2;
        function33 = function32;
        m2140getLambda3$material3_release = function23;
        i7 = -1199178586;
        j6 = j3;
        j5 = j4;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        composer2 = startRestartGroup;
        m2756TabRowWithSubcomposeImplDTcfvLk(modifier3, j6, j5, function33, m2140getLambda3$material3_release, function22, composer2, (i4 >> 3) & 524286);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier4 = modifier3;
        j7 = j6;
        j8 = j5;
        function34 = function33;
        function24 = m2140getLambda3$material3_release;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00fe  */
    /* renamed from: PrimaryScrollableTabRow-qhFBPw4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2747PrimaryScrollableTabRowqhFBPw4(final int i, Modifier modifier, ScrollState scrollState, long j, long j2, float f, Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        ScrollState scrollState2;
        long j3;
        long j4;
        int i5;
        float f2;
        int i6;
        int i7;
        int i8;
        int i9;
        long j5;
        float f3;
        ScrollState scrollState3;
        long j6;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function32;
        Modifier modifier3;
        long j7;
        final Function2<? super Composer, ? super Integer, Unit> function24;
        final Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function33;
        final float f4;
        final Modifier modifier4;
        final ScrollState scrollState4;
        final long j8;
        final long j9;
        ScopeUpdateScope endRestartGroup;
        int i10;
        int i11;
        int i12;
        Composer startRestartGroup = composer.startRestartGroup(-1763241113);
        ComposerKt.sourceInformation(startRestartGroup, "C(PrimaryScrollableTabRow)P(7,5,6,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3:c#ui.unit.Dp,4)358@18337L21,359@18403L21,360@18467L19,363@18637L198,372@18953L328:TabRow.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (startRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i13 = i3 & 2;
        if (i13 != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i2 & 384) != 0) {
                if ((i3 & 4) == 0) {
                    scrollState2 = scrollState;
                    if (startRestartGroup.changed(scrollState2)) {
                        i12 = 256;
                        i4 |= i12;
                    }
                } else {
                    scrollState2 = scrollState;
                }
                i12 = 128;
                i4 |= i12;
            } else {
                scrollState2 = scrollState;
            }
            if ((i2 & 3072) != 0) {
                if ((i3 & 8) == 0) {
                    j3 = j;
                    if (startRestartGroup.changed(j3)) {
                        i11 = 2048;
                        i4 |= i11;
                    }
                } else {
                    j3 = j;
                }
                i11 = 1024;
                i4 |= i11;
            } else {
                j3 = j;
            }
            if ((i2 & 24576) != 0) {
                if ((i3 & 16) == 0) {
                    j4 = j2;
                    if (startRestartGroup.changed(j4)) {
                        i10 = 16384;
                        i4 |= i10;
                    }
                } else {
                    j4 = j2;
                }
                i10 = 8192;
                i4 |= i10;
            } else {
                j4 = j2;
            }
            i5 = i3 & 32;
            if (i5 == 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((196608 & i2) == 0) {
                f2 = f;
                i4 |= startRestartGroup.changed(f2) ? 131072 : 65536;
                i6 = i3 & 64;
                if (i6 != 0) {
                    i4 |= 1572864;
                } else if ((i2 & 1572864) == 0) {
                    i4 |= startRestartGroup.changedInstance(function3) ? 1048576 : 524288;
                }
                i7 = i3 & 128;
                if (i7 != 0) {
                    i4 |= 12582912;
                } else if ((i2 & 12582912) == 0) {
                    i8 = i7;
                    i4 |= startRestartGroup.changedInstance(function2) ? 8388608 : 4194304;
                    if ((i3 & 256) == 0) {
                        i4 |= 100663296;
                    } else if ((i2 & 100663296) == 0) {
                        i4 |= startRestartGroup.changedInstance(function22) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        if ((i4 & 38347923) == 38347922 || !startRestartGroup.getSkipping()) {
                            startRestartGroup.startDefaults();
                            if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                    scrollState2 = ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1);
                                }
                                if ((i3 & 8) != 0) {
                                    j3 = TabRowDefaults.INSTANCE.getPrimaryContainerColor(startRestartGroup, 6);
                                    i4 &= -7169;
                                }
                                if ((i3 & 16) != 0) {
                                    j4 = TabRowDefaults.INSTANCE.getPrimaryContentColor(startRestartGroup, 6);
                                    i4 &= -57345;
                                }
                                if (i5 != 0) {
                                    f2 = TabRowDefaults.INSTANCE.m2745getScrollableTabRowEdgeStartPaddingD9Ej5fM();
                                }
                                ComposableLambda rememberComposableLambda = i6 != 0 ? ComposableLambdaKt.rememberComposableLambda(1601820568, true, new Function3<TabIndicatorScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$PrimaryScrollableTabRow$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(TabIndicatorScope tabIndicatorScope, Composer composer2, Integer num) {
                                        invoke(tabIndicatorScope, composer2, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(TabIndicatorScope tabIndicatorScope, Composer composer2, int i14) {
                                        int i15;
                                        ComposerKt.sourceInformation(composer2, "C364@18666L159:TabRow.kt#uh7d8r");
                                        if ((i14 & 6) == 0) {
                                            i15 = ((i14 & 8) == 0 ? composer2.changed(tabIndicatorScope) : composer2.changedInstance(tabIndicatorScope) ? 4 : 2) | i14;
                                        } else {
                                            i15 = i14;
                                        }
                                        if ((i15 & 19) != 18 || !composer2.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1601820568, i15, -1, "androidx.compose.material3.PrimaryScrollableTabRow.<anonymous> (TabRow.kt:364)");
                                            }
                                            TabRowDefaults.INSTANCE.m2743PrimaryIndicator10LGxhE(tabIndicatorScope.tabIndicatorOffset(Modifier.INSTANCE, i, true), Dp.INSTANCE.m7419getUnspecifiedD9Ej5fM(), 0.0f, 0L, null, composer2, 196656, 28);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer2.skipToGroupEnd();
                                    }
                                }, startRestartGroup, 54) : function3;
                                if (i8 != 0) {
                                    i9 = i4;
                                    j6 = j4;
                                    function32 = rememberComposableLambda;
                                    function23 = ComposableSingletons$TabRowKt.INSTANCE.m2141getLambda4$material3_release();
                                    modifier3 = modifier2;
                                    j5 = j3;
                                    f3 = f2;
                                    scrollState3 = scrollState2;
                                } else {
                                    i9 = i4;
                                    j5 = j3;
                                    f3 = f2;
                                    scrollState3 = scrollState2;
                                    j6 = j4;
                                    function23 = function2;
                                    function32 = rememberComposableLambda;
                                    modifier3 = modifier2;
                                }
                                j7 = j6;
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                }
                                if ((i3 & 8) != 0) {
                                    i4 &= -7169;
                                }
                                if ((i3 & 16) != 0) {
                                    i4 &= -57345;
                                }
                                i9 = i4;
                                modifier3 = modifier2;
                                j5 = j3;
                                f3 = f2;
                                scrollState3 = scrollState2;
                                j7 = j4;
                                function32 = function3;
                                function23 = function2;
                            }
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1763241113, i9, -1, "androidx.compose.material3.PrimaryScrollableTabRow (TabRow.kt:371)");
                            }
                            int i14 = i9 >> 3;
                            m2750ScrollableTabRowImplsKfQg0A(i, modifier3, j5, j7, f3, scrollState3, function32, function23, function22, startRestartGroup, (i9 & 126) | (i14 & 896) | (i14 & 7168) | (i14 & 57344) | ((i9 << 9) & 458752) | (3670016 & i9) | (29360128 & i9) | (i9 & 234881024));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function24 = function23;
                            function33 = function32;
                            f4 = f3;
                            long j10 = j5;
                            modifier4 = modifier3;
                            scrollState4 = scrollState3;
                            j8 = j7;
                            j9 = j10;
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            function24 = function2;
                            modifier4 = modifier2;
                            scrollState4 = scrollState2;
                            j9 = j3;
                            j8 = j4;
                            f4 = f2;
                            function33 = function3;
                        }
                        endRestartGroup = startRestartGroup.endRestartGroup();
                        if (endRestartGroup != null) {
                            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$PrimaryScrollableTabRow$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i15) {
                                    TabRowKt.m2747PrimaryScrollableTabRowqhFBPw4(i, modifier4, scrollState4, j9, j8, f4, function33, function24, function22, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if ((i4 & 38347923) == 38347922) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                    }
                    if (i13 != 0) {
                    }
                    if ((i3 & 4) != 0) {
                    }
                    if ((i3 & 8) != 0) {
                    }
                    if ((i3 & 16) != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if (i6 != 0) {
                    }
                    if (i8 != 0) {
                    }
                    j7 = j6;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    int i142 = i9 >> 3;
                    m2750ScrollableTabRowImplsKfQg0A(i, modifier3, j5, j7, f3, scrollState3, function32, function23, function22, startRestartGroup, (i9 & 126) | (i142 & 896) | (i142 & 7168) | (i142 & 57344) | ((i9 << 9) & 458752) | (3670016 & i9) | (29360128 & i9) | (i9 & 234881024));
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    function24 = function23;
                    function33 = function32;
                    f4 = f3;
                    long j102 = j5;
                    modifier4 = modifier3;
                    scrollState4 = scrollState3;
                    j8 = j7;
                    j9 = j102;
                    endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                i8 = i7;
                if ((i3 & 256) == 0) {
                }
                if ((i4 & 38347923) == 38347922) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i13 != 0) {
                }
                if ((i3 & 4) != 0) {
                }
                if ((i3 & 8) != 0) {
                }
                if ((i3 & 16) != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                if (i8 != 0) {
                }
                j7 = j6;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                int i1422 = i9 >> 3;
                m2750ScrollableTabRowImplsKfQg0A(i, modifier3, j5, j7, f3, scrollState3, function32, function23, function22, startRestartGroup, (i9 & 126) | (i1422 & 896) | (i1422 & 7168) | (i1422 & 57344) | ((i9 << 9) & 458752) | (3670016 & i9) | (29360128 & i9) | (i9 & 234881024));
                if (ComposerKt.isTraceInProgress()) {
                }
                function24 = function23;
                function33 = function32;
                f4 = f3;
                long j1022 = j5;
                modifier4 = modifier3;
                scrollState4 = scrollState3;
                j8 = j7;
                j9 = j1022;
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            f2 = f;
            i6 = i3 & 64;
            if (i6 != 0) {
            }
            i7 = i3 & 128;
            if (i7 != 0) {
            }
            i8 = i7;
            if ((i3 & 256) == 0) {
            }
            if ((i4 & 38347923) == 38347922) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i13 != 0) {
            }
            if ((i3 & 4) != 0) {
            }
            if ((i3 & 8) != 0) {
            }
            if ((i3 & 16) != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            if (i8 != 0) {
            }
            j7 = j6;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            int i14222 = i9 >> 3;
            m2750ScrollableTabRowImplsKfQg0A(i, modifier3, j5, j7, f3, scrollState3, function32, function23, function22, startRestartGroup, (i9 & 126) | (i14222 & 896) | (i14222 & 7168) | (i14222 & 57344) | ((i9 << 9) & 458752) | (3670016 & i9) | (29360128 & i9) | (i9 & 234881024));
            if (ComposerKt.isTraceInProgress()) {
            }
            function24 = function23;
            function33 = function32;
            f4 = f3;
            long j10222 = j5;
            modifier4 = modifier3;
            scrollState4 = scrollState3;
            j8 = j7;
            j9 = j10222;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        if ((i2 & 384) != 0) {
        }
        if ((i2 & 3072) != 0) {
        }
        if ((i2 & 24576) != 0) {
        }
        i5 = i3 & 32;
        if (i5 == 0) {
        }
        f2 = f;
        i6 = i3 & 64;
        if (i6 != 0) {
        }
        i7 = i3 & 128;
        if (i7 != 0) {
        }
        i8 = i7;
        if ((i3 & 256) == 0) {
        }
        if ((i4 & 38347923) == 38347922) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i13 != 0) {
        }
        if ((i3 & 4) != 0) {
        }
        if ((i3 & 8) != 0) {
        }
        if ((i3 & 16) != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        if (i8 != 0) {
        }
        j7 = j6;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        int i142222 = i9 >> 3;
        m2750ScrollableTabRowImplsKfQg0A(i, modifier3, j5, j7, f3, scrollState3, function32, function23, function22, startRestartGroup, (i9 & 126) | (i142222 & 896) | (i142222 & 7168) | (i142222 & 57344) | ((i9 << 9) & 458752) | (3670016 & i9) | (29360128 & i9) | (i9 & 234881024));
        if (ComposerKt.isTraceInProgress()) {
        }
        function24 = function23;
        function33 = function32;
        f4 = f3;
        long j102222 = j5;
        modifier4 = modifier3;
        scrollState4 = scrollState3;
        j8 = j7;
        j9 = j102222;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00fe  */
    /* renamed from: SecondaryScrollableTabRow-qhFBPw4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2752SecondaryScrollableTabRowqhFBPw4(final int i, Modifier modifier, ScrollState scrollState, long j, long j2, float f, Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        ScrollState scrollState2;
        long j3;
        long j4;
        int i5;
        float f2;
        int i6;
        int i7;
        int i8;
        int i9;
        long j5;
        float f3;
        ScrollState scrollState3;
        long j6;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function32;
        Modifier modifier3;
        long j7;
        final Function2<? super Composer, ? super Integer, Unit> function24;
        final Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function33;
        final float f4;
        final Modifier modifier4;
        final ScrollState scrollState4;
        final long j8;
        final long j9;
        ScopeUpdateScope endRestartGroup;
        int i10;
        int i11;
        int i12;
        Composer startRestartGroup = composer.startRestartGroup(1821940917);
        ComposerKt.sourceInformation(startRestartGroup, "C(SecondaryScrollableTabRow)P(7,5,6,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3:c#ui.unit.Dp,4)428@21932L21,429@21998L23,430@22064L21,433@22236L160,441@22514L327:TabRow.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (startRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i13 = i3 & 2;
        if (i13 != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i2 & 384) != 0) {
                if ((i3 & 4) == 0) {
                    scrollState2 = scrollState;
                    if (startRestartGroup.changed(scrollState2)) {
                        i12 = 256;
                        i4 |= i12;
                    }
                } else {
                    scrollState2 = scrollState;
                }
                i12 = 128;
                i4 |= i12;
            } else {
                scrollState2 = scrollState;
            }
            if ((i2 & 3072) != 0) {
                if ((i3 & 8) == 0) {
                    j3 = j;
                    if (startRestartGroup.changed(j3)) {
                        i11 = 2048;
                        i4 |= i11;
                    }
                } else {
                    j3 = j;
                }
                i11 = 1024;
                i4 |= i11;
            } else {
                j3 = j;
            }
            if ((i2 & 24576) != 0) {
                if ((i3 & 16) == 0) {
                    j4 = j2;
                    if (startRestartGroup.changed(j4)) {
                        i10 = 16384;
                        i4 |= i10;
                    }
                } else {
                    j4 = j2;
                }
                i10 = 8192;
                i4 |= i10;
            } else {
                j4 = j2;
            }
            i5 = i3 & 32;
            if (i5 == 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((196608 & i2) == 0) {
                f2 = f;
                i4 |= startRestartGroup.changed(f2) ? 131072 : 65536;
                i6 = i3 & 64;
                if (i6 != 0) {
                    i4 |= 1572864;
                } else if ((i2 & 1572864) == 0) {
                    i4 |= startRestartGroup.changedInstance(function3) ? 1048576 : 524288;
                }
                i7 = i3 & 128;
                if (i7 != 0) {
                    i4 |= 12582912;
                } else if ((i2 & 12582912) == 0) {
                    i8 = i7;
                    i4 |= startRestartGroup.changedInstance(function2) ? 8388608 : 4194304;
                    if ((i3 & 256) == 0) {
                        i4 |= 100663296;
                    } else if ((i2 & 100663296) == 0) {
                        i4 |= startRestartGroup.changedInstance(function22) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        if ((i4 & 38347923) == 38347922 || !startRestartGroup.getSkipping()) {
                            startRestartGroup.startDefaults();
                            if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                if (i13 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                    scrollState2 = ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1);
                                }
                                if ((i3 & 8) != 0) {
                                    j3 = TabRowDefaults.INSTANCE.getSecondaryContainerColor(startRestartGroup, 6);
                                    i4 &= -7169;
                                }
                                if ((i3 & 16) != 0) {
                                    j4 = TabRowDefaults.INSTANCE.getSecondaryContentColor(startRestartGroup, 6);
                                    i4 &= -57345;
                                }
                                if (i5 != 0) {
                                    f2 = TabRowDefaults.INSTANCE.m2745getScrollableTabRowEdgeStartPaddingD9Ej5fM();
                                }
                                ComposableLambda rememberComposableLambda = i6 != 0 ? ComposableLambdaKt.rememberComposableLambda(1535842470, true, new Function3<TabIndicatorScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$SecondaryScrollableTabRow$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(TabIndicatorScope tabIndicatorScope, Composer composer2, Integer num) {
                                        invoke(tabIndicatorScope, composer2, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(TabIndicatorScope tabIndicatorScope, Composer composer2, int i14) {
                                        ComposerKt.sourceInformation(composer2, "C434@22265L121:TabRow.kt#uh7d8r");
                                        if ((i14 & 6) == 0) {
                                            i14 |= (i14 & 8) == 0 ? composer2.changed(tabIndicatorScope) : composer2.changedInstance(tabIndicatorScope) ? 4 : 2;
                                        }
                                        if ((i14 & 19) != 18 || !composer2.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1535842470, i14, -1, "androidx.compose.material3.SecondaryScrollableTabRow.<anonymous> (TabRow.kt:434)");
                                            }
                                            TabRowDefaults.INSTANCE.m2744SecondaryIndicator9IZ8Weo(tabIndicatorScope.tabIndicatorOffset(Modifier.INSTANCE, i, false), 0.0f, 0L, composer2, 3072, 6);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer2.skipToGroupEnd();
                                    }
                                }, startRestartGroup, 54) : function3;
                                if (i8 != 0) {
                                    i9 = i4;
                                    j6 = j4;
                                    function32 = rememberComposableLambda;
                                    function23 = ComposableSingletons$TabRowKt.INSTANCE.m2142getLambda5$material3_release();
                                    modifier3 = modifier2;
                                    j5 = j3;
                                    f3 = f2;
                                    scrollState3 = scrollState2;
                                } else {
                                    i9 = i4;
                                    j5 = j3;
                                    f3 = f2;
                                    scrollState3 = scrollState2;
                                    j6 = j4;
                                    function23 = function2;
                                    function32 = rememberComposableLambda;
                                    modifier3 = modifier2;
                                }
                                j7 = j6;
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                }
                                if ((i3 & 8) != 0) {
                                    i4 &= -7169;
                                }
                                if ((i3 & 16) != 0) {
                                    i4 &= -57345;
                                }
                                i9 = i4;
                                modifier3 = modifier2;
                                j5 = j3;
                                f3 = f2;
                                scrollState3 = scrollState2;
                                j7 = j4;
                                function32 = function3;
                                function23 = function2;
                            }
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1821940917, i9, -1, "androidx.compose.material3.SecondaryScrollableTabRow (TabRow.kt:440)");
                            }
                            int i14 = i9 >> 3;
                            m2750ScrollableTabRowImplsKfQg0A(i, modifier3, j5, j7, f3, scrollState3, function32, function23, function22, startRestartGroup, (i9 & 126) | (i14 & 896) | (i14 & 7168) | (i14 & 57344) | ((i9 << 9) & 458752) | (3670016 & i9) | (29360128 & i9) | (i9 & 234881024));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function24 = function23;
                            function33 = function32;
                            f4 = f3;
                            long j10 = j5;
                            modifier4 = modifier3;
                            scrollState4 = scrollState3;
                            j8 = j7;
                            j9 = j10;
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            function24 = function2;
                            modifier4 = modifier2;
                            scrollState4 = scrollState2;
                            j9 = j3;
                            j8 = j4;
                            f4 = f2;
                            function33 = function3;
                        }
                        endRestartGroup = startRestartGroup.endRestartGroup();
                        if (endRestartGroup != null) {
                            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$SecondaryScrollableTabRow$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i15) {
                                    TabRowKt.m2752SecondaryScrollableTabRowqhFBPw4(i, modifier4, scrollState4, j9, j8, f4, function33, function24, function22, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if ((i4 & 38347923) == 38347922) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                    }
                    if (i13 != 0) {
                    }
                    if ((i3 & 4) != 0) {
                    }
                    if ((i3 & 8) != 0) {
                    }
                    if ((i3 & 16) != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if (i6 != 0) {
                    }
                    if (i8 != 0) {
                    }
                    j7 = j6;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    int i142 = i9 >> 3;
                    m2750ScrollableTabRowImplsKfQg0A(i, modifier3, j5, j7, f3, scrollState3, function32, function23, function22, startRestartGroup, (i9 & 126) | (i142 & 896) | (i142 & 7168) | (i142 & 57344) | ((i9 << 9) & 458752) | (3670016 & i9) | (29360128 & i9) | (i9 & 234881024));
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    function24 = function23;
                    function33 = function32;
                    f4 = f3;
                    long j102 = j5;
                    modifier4 = modifier3;
                    scrollState4 = scrollState3;
                    j8 = j7;
                    j9 = j102;
                    endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                i8 = i7;
                if ((i3 & 256) == 0) {
                }
                if ((i4 & 38347923) == 38347922) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i13 != 0) {
                }
                if ((i3 & 4) != 0) {
                }
                if ((i3 & 8) != 0) {
                }
                if ((i3 & 16) != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                if (i8 != 0) {
                }
                j7 = j6;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                int i1422 = i9 >> 3;
                m2750ScrollableTabRowImplsKfQg0A(i, modifier3, j5, j7, f3, scrollState3, function32, function23, function22, startRestartGroup, (i9 & 126) | (i1422 & 896) | (i1422 & 7168) | (i1422 & 57344) | ((i9 << 9) & 458752) | (3670016 & i9) | (29360128 & i9) | (i9 & 234881024));
                if (ComposerKt.isTraceInProgress()) {
                }
                function24 = function23;
                function33 = function32;
                f4 = f3;
                long j1022 = j5;
                modifier4 = modifier3;
                scrollState4 = scrollState3;
                j8 = j7;
                j9 = j1022;
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            f2 = f;
            i6 = i3 & 64;
            if (i6 != 0) {
            }
            i7 = i3 & 128;
            if (i7 != 0) {
            }
            i8 = i7;
            if ((i3 & 256) == 0) {
            }
            if ((i4 & 38347923) == 38347922) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i13 != 0) {
            }
            if ((i3 & 4) != 0) {
            }
            if ((i3 & 8) != 0) {
            }
            if ((i3 & 16) != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            if (i8 != 0) {
            }
            j7 = j6;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            int i14222 = i9 >> 3;
            m2750ScrollableTabRowImplsKfQg0A(i, modifier3, j5, j7, f3, scrollState3, function32, function23, function22, startRestartGroup, (i9 & 126) | (i14222 & 896) | (i14222 & 7168) | (i14222 & 57344) | ((i9 << 9) & 458752) | (3670016 & i9) | (29360128 & i9) | (i9 & 234881024));
            if (ComposerKt.isTraceInProgress()) {
            }
            function24 = function23;
            function33 = function32;
            f4 = f3;
            long j10222 = j5;
            modifier4 = modifier3;
            scrollState4 = scrollState3;
            j8 = j7;
            j9 = j10222;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        if ((i2 & 384) != 0) {
        }
        if ((i2 & 3072) != 0) {
        }
        if ((i2 & 24576) != 0) {
        }
        i5 = i3 & 32;
        if (i5 == 0) {
        }
        f2 = f;
        i6 = i3 & 64;
        if (i6 != 0) {
        }
        i7 = i3 & 128;
        if (i7 != 0) {
        }
        i8 = i7;
        if ((i3 & 256) == 0) {
        }
        if ((i4 & 38347923) == 38347922) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i13 != 0) {
        }
        if ((i3 & 4) != 0) {
        }
        if ((i3 & 8) != 0) {
        }
        if ((i3 & 16) != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        if (i8 != 0) {
        }
        j7 = j6;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        int i142222 = i9 >> 3;
        m2750ScrollableTabRowImplsKfQg0A(i, modifier3, j5, j7, f3, scrollState3, function32, function23, function22, startRestartGroup, (i9 & 126) | (i142222 & 896) | (i142222 & 7168) | (i142222 & 57344) | ((i9 << 9) & 458752) | (3670016 & i9) | (29360128 & i9) | (i9 & 234881024));
        if (ComposerKt.isTraceInProgress()) {
        }
        function24 = function23;
        function33 = function32;
        f4 = f3;
        long j102222 = j5;
        modifier4 = modifier3;
        scrollState4 = scrollState3;
        j8 = j7;
        j9 = j102222;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00a1  */
    /* renamed from: ScrollableTabRow-sKfQg0A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2749ScrollableTabRowsKfQg0A(final int i, Modifier modifier, long j, long j2, float f, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long j3;
        long j4;
        int i5;
        float f2;
        int i6;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function32;
        int i7;
        int i8;
        final Modifier modifier3;
        final long j5;
        final long j6;
        final float f3;
        Function2<? super Composer, ? super Integer, Unit> m2143getLambda6$material3_release;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function33;
        final Function2<? super Composer, ? super Integer, Unit> function23;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function34;
        ScopeUpdateScope endRestartGroup;
        int i9;
        int i10;
        Composer startRestartGroup = composer.startRestartGroup(-497821003);
        ComposerKt.sourceInformation(startRestartGroup, "C(ScrollableTabRow)P(6,5,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3:c#ui.unit.Dp,4)496@25397L21,497@25461L19,500@25644L164,517@26250L21,508@25926L351:TabRow.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (startRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i11 = i3 & 2;
        if (i11 != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i2 & 384) != 0) {
                if ((i3 & 4) == 0) {
                    j3 = j;
                    if (startRestartGroup.changed(j3)) {
                        i10 = 256;
                        i4 |= i10;
                    }
                } else {
                    j3 = j;
                }
                i10 = 128;
                i4 |= i10;
            } else {
                j3 = j;
            }
            if ((i2 & 3072) != 0) {
                if ((i3 & 8) == 0) {
                    j4 = j2;
                    if (startRestartGroup.changed(j4)) {
                        i9 = 2048;
                        i4 |= i9;
                    }
                } else {
                    j4 = j2;
                }
                i9 = 1024;
                i4 |= i9;
            } else {
                j4 = j2;
            }
            i5 = i3 & 16;
            if (i5 == 0) {
                i4 |= 24576;
            } else if ((i2 & 24576) == 0) {
                f2 = f;
                i4 |= startRestartGroup.changed(f2) ? 16384 : 8192;
                i6 = i3 & 32;
                if (i6 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i2) == 0) {
                    function32 = function3;
                    i4 |= startRestartGroup.changedInstance(function32) ? 131072 : 65536;
                    i7 = i3 & 64;
                    if (i7 == 0) {
                        i4 |= 1572864;
                    } else if ((i2 & 1572864) == 0) {
                        i4 |= startRestartGroup.changedInstance(function2) ? 1048576 : 524288;
                    }
                    if ((i3 & 128) == 0) {
                        i4 |= 12582912;
                    } else if ((i2 & 12582912) == 0) {
                        i4 |= startRestartGroup.changedInstance(function22) ? 8388608 : 4194304;
                        if ((i4 & 4793491) == 4793490 || !startRestartGroup.getSkipping()) {
                            startRestartGroup.startDefaults();
                            if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                if (i11 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i3 & 4) != 0) {
                                    j3 = TabRowDefaults.INSTANCE.getPrimaryContainerColor(startRestartGroup, 6);
                                    i4 &= -897;
                                }
                                if ((i3 & 8) != 0) {
                                    j4 = TabRowDefaults.INSTANCE.getPrimaryContentColor(startRestartGroup, 6);
                                    i4 &= -7169;
                                }
                                if (i5 != 0) {
                                    f2 = TabRowDefaults.INSTANCE.m2745getScrollableTabRowEdgeStartPaddingD9Ej5fM();
                                }
                                if (i6 != 0) {
                                    function32 = ComposableLambdaKt.rememberComposableLambda(-913748678, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRow$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(List<? extends TabPosition> list, Composer composer2, Integer num) {
                                            invoke((List<TabPosition>) list, composer2, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(List<TabPosition> list, Composer composer2, int i12) {
                                            ComposerKt.sourceInformation(composer2, "C501@25689L109:TabRow.kt#uh7d8r");
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-913748678, i12, -1, "androidx.compose.material3.ScrollableTabRow.<anonymous> (TabRow.kt:501)");
                                            }
                                            TabRowDefaults.INSTANCE.m2744SecondaryIndicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, list.get(i)), 0.0f, 0L, composer2, 3072, 6);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    }, startRestartGroup, 54);
                                }
                                if (i7 != 0) {
                                    i8 = i4;
                                    modifier3 = modifier2;
                                    j5 = j3;
                                    j6 = j4;
                                    f3 = f2;
                                    m2143getLambda6$material3_release = ComposableSingletons$TabRowKt.INSTANCE.m2143getLambda6$material3_release();
                                    function33 = function32;
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-497821003, i8, -1, "androidx.compose.material3.ScrollableTabRow (TabRow.kt:507)");
                                    }
                                    int i12 = i8 << 3;
                                    m2751ScrollableTabRowWithSubcomposeImplqhFBPw4(i, function33, modifier3, j5, j6, f3, m2143getLambda6$material3_release, function22, ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), startRestartGroup, (i12 & 896) | (i8 & 14) | ((i8 >> 12) & 112) | (i12 & 7168) | (57344 & i12) | (458752 & i12) | (3670016 & i8) | (i8 & 29360128), 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    function23 = m2143getLambda6$material3_release;
                                    function34 = function33;
                                }
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                }
                                if ((i3 & 8) != 0) {
                                    i4 &= -7169;
                                }
                            }
                            i8 = i4;
                            modifier3 = modifier2;
                            j5 = j3;
                            j6 = j4;
                            f3 = f2;
                            function33 = function32;
                            m2143getLambda6$material3_release = function2;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            int i122 = i8 << 3;
                            m2751ScrollableTabRowWithSubcomposeImplqhFBPw4(i, function33, modifier3, j5, j6, f3, m2143getLambda6$material3_release, function22, ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), startRestartGroup, (i122 & 896) | (i8 & 14) | ((i8 >> 12) & 112) | (i122 & 7168) | (57344 & i122) | (458752 & i122) | (3670016 & i8) | (i8 & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            function23 = m2143getLambda6$material3_release;
                            function34 = function33;
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            function23 = function2;
                            modifier3 = modifier2;
                            j5 = j3;
                            j6 = j4;
                            f3 = f2;
                            function34 = function32;
                        }
                        endRestartGroup = startRestartGroup.endRestartGroup();
                        if (endRestartGroup != null) {
                            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRow$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                    invoke(composer2, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer2, int i13) {
                                    TabRowKt.m2749ScrollableTabRowsKfQg0A(i, modifier3, j5, j6, f3, function34, function23, function22, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if ((i4 & 4793491) == 4793490) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                    }
                    if (i11 != 0) {
                    }
                    if ((i3 & 4) != 0) {
                    }
                    if ((i3 & 8) != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if (i6 != 0) {
                    }
                    if (i7 != 0) {
                    }
                    i8 = i4;
                    modifier3 = modifier2;
                    j5 = j3;
                    j6 = j4;
                    f3 = f2;
                    function33 = function32;
                    m2143getLambda6$material3_release = function2;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    int i1222 = i8 << 3;
                    m2751ScrollableTabRowWithSubcomposeImplqhFBPw4(i, function33, modifier3, j5, j6, f3, m2143getLambda6$material3_release, function22, ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), startRestartGroup, (i1222 & 896) | (i8 & 14) | ((i8 >> 12) & 112) | (i1222 & 7168) | (57344 & i1222) | (458752 & i1222) | (3670016 & i8) | (i8 & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    function23 = m2143getLambda6$material3_release;
                    function34 = function33;
                    endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                function32 = function3;
                i7 = i3 & 64;
                if (i7 == 0) {
                }
                if ((i3 & 128) == 0) {
                }
                if ((i4 & 4793491) == 4793490) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i11 != 0) {
                }
                if ((i3 & 4) != 0) {
                }
                if ((i3 & 8) != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                if (i7 != 0) {
                }
                i8 = i4;
                modifier3 = modifier2;
                j5 = j3;
                j6 = j4;
                f3 = f2;
                function33 = function32;
                m2143getLambda6$material3_release = function2;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                int i12222 = i8 << 3;
                m2751ScrollableTabRowWithSubcomposeImplqhFBPw4(i, function33, modifier3, j5, j6, f3, m2143getLambda6$material3_release, function22, ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), startRestartGroup, (i12222 & 896) | (i8 & 14) | ((i8 >> 12) & 112) | (i12222 & 7168) | (57344 & i12222) | (458752 & i12222) | (3670016 & i8) | (i8 & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                }
                function23 = m2143getLambda6$material3_release;
                function34 = function33;
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            f2 = f;
            i6 = i3 & 32;
            if (i6 != 0) {
            }
            function32 = function3;
            i7 = i3 & 64;
            if (i7 == 0) {
            }
            if ((i3 & 128) == 0) {
            }
            if ((i4 & 4793491) == 4793490) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i11 != 0) {
            }
            if ((i3 & 4) != 0) {
            }
            if ((i3 & 8) != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            if (i7 != 0) {
            }
            i8 = i4;
            modifier3 = modifier2;
            j5 = j3;
            j6 = j4;
            f3 = f2;
            function33 = function32;
            m2143getLambda6$material3_release = function2;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            int i122222 = i8 << 3;
            m2751ScrollableTabRowWithSubcomposeImplqhFBPw4(i, function33, modifier3, j5, j6, f3, m2143getLambda6$material3_release, function22, ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), startRestartGroup, (i122222 & 896) | (i8 & 14) | ((i8 >> 12) & 112) | (i122222 & 7168) | (57344 & i122222) | (458752 & i122222) | (3670016 & i8) | (i8 & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            function23 = m2143getLambda6$material3_release;
            function34 = function33;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        if ((i2 & 384) != 0) {
        }
        if ((i2 & 3072) != 0) {
        }
        i5 = i3 & 16;
        if (i5 == 0) {
        }
        f2 = f;
        i6 = i3 & 32;
        if (i6 != 0) {
        }
        function32 = function3;
        i7 = i3 & 64;
        if (i7 == 0) {
        }
        if ((i3 & 128) == 0) {
        }
        if ((i4 & 4793491) == 4793490) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i11 != 0) {
        }
        if ((i3 & 4) != 0) {
        }
        if ((i3 & 8) != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        if (i7 != 0) {
        }
        i8 = i4;
        modifier3 = modifier2;
        j5 = j3;
        j6 = j4;
        f3 = f2;
        function33 = function32;
        m2143getLambda6$material3_release = function2;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        int i1222222 = i8 << 3;
        m2751ScrollableTabRowWithSubcomposeImplqhFBPw4(i, function33, modifier3, j5, j6, f3, m2143getLambda6$material3_release, function22, ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), startRestartGroup, (i1222222 & 896) | (i8 & 14) | ((i8 >> 12) & 112) | (i1222222 & 7168) | (57344 & i1222222) | (458752 & i1222222) | (3670016 & i8) | (i8 & 29360128), 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        function23 = m2143getLambda6$material3_release;
        function34 = function33;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: TabRowImpl-DTcfvLk, reason: not valid java name */
    public static final void m2755TabRowImplDTcfvLk(Modifier modifier, final long j, final long j2, final Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer composer, final int i) {
        Modifier modifier2;
        int i2;
        Composer composer2;
        Composer startRestartGroup = composer.startRestartGroup(1757425411);
        ComposerKt.sourceInformation(startRestartGroup, "C(TabRowImpl)P(4,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3)576@28021L4041,572@27891L4171:TabRow.kt#uh7d8r");
        if ((i & 6) == 0) {
            modifier2 = modifier;
            i2 = (startRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(j) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changed(j2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= startRestartGroup.changedInstance(function3) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= startRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= startRestartGroup.changedInstance(function22) ? 131072 : 65536;
        }
        if ((74899 & i2) != 74898 || !startRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1757425411, i2, -1, "androidx.compose.material3.TabRowImpl (TabRow.kt:571)");
            }
            int i3 = i2 << 3;
            composer2 = startRestartGroup;
            SurfaceKt.m2699SurfaceT9BRK9s(SelectableGroupKt.selectableGroup(modifier2), null, j, j2, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-65106680, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRowImpl$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i4) {
                    ComposerKt.sourceInformation(composer3, "C577@28043L1274,618@29505L21,620@29556L2500,612@29327L2729:TabRow.kt#uh7d8r");
                    if ((i4 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-65106680, i4, -1, "androidx.compose.material3.TabRowImpl.<anonymous> (TabRow.kt:577)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composer3, 1811399233, "CC(remember):TabRow.kt#9igjgp");
                        Object rememberedValue = composer3.rememberedValue();
                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                            rememberedValue = new TabRowKt$TabRowImpl$1$scope$1$1();
                            composer3.updateRememberedValue(rememberedValue);
                        }
                        final TabRowKt$TabRowImpl$1$scope$1$1 tabRowKt$TabRowImpl$1$scope$1$1 = (TabRowKt$TabRowImpl$1$scope$1$1) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        final Function3<TabIndicatorScope, Composer, Integer, Unit> function32 = function3;
                        List listOf = CollectionsKt.listOf((Object[]) new Function2[]{function22, function2, ComposableLambdaKt.rememberComposableLambda(1236693605, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRowImpl$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i5) {
                                ComposerKt.sourceInformation(composer4, "C618@29513L11:TabRow.kt#uh7d8r");
                                if ((i5 & 3) == 2 && composer4.getSkipping()) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1236693605, i5, -1, "androidx.compose.material3.TabRowImpl.<anonymous>.<anonymous> (TabRow.kt:618)");
                                }
                                function32.invoke(tabRowKt$TabRowImpl$1$scope$1$1, composer4, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer3, 54)});
                        ComposerKt.sourceInformationMarkerStart(composer3, 1811448875, "CC(remember):TabRow.kt#9igjgp");
                        MultiContentMeasurePolicy rememberedValue2 = composer3.rememberedValue();
                        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue2 = new MultiContentMeasurePolicy() { // from class: androidx.compose.material3.TabRowKt$TabRowImpl$1$2$1
                                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                                public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i5) {
                                    return MultiContentMeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i5);
                                }

                                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                                public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i5) {
                                    return MultiContentMeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i5);
                                }

                                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                                public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i5) {
                                    return MultiContentMeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i5);
                                }

                                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                                public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i5) {
                                    return MultiContentMeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i5);
                                }

                                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                                /* renamed from: measure-3p2s80s */
                                public final MeasureResult mo816measure3p2s80s(MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j3) {
                                    MeasureScope measureScope2 = measureScope;
                                    List<? extends Measurable> list2 = list.get(0);
                                    List<? extends Measurable> list3 = list.get(1);
                                    int i5 = 2;
                                    List<? extends Measurable> list4 = list.get(2);
                                    int m7342getMaxWidthimpl = Constraints.m7342getMaxWidthimpl(j3);
                                    int size = list2.size();
                                    final Ref.IntRef intRef = new Ref.IntRef();
                                    if (size > 0) {
                                        intRef.element = m7342getMaxWidthimpl / size;
                                    }
                                    int i6 = 0;
                                    int size2 = list2.size();
                                    for (int i7 = 0; i7 < size2; i7++) {
                                        i6 = Integer.valueOf(Math.max(list2.get(i7).maxIntrinsicHeight(intRef.element), i6.intValue()));
                                    }
                                    final int intValue = i6.intValue();
                                    TabRowKt$TabRowImpl$1$scope$1$1 tabRowKt$TabRowImpl$1$scope$1$12 = TabRowKt$TabRowImpl$1$scope$1$1.this;
                                    ArrayList arrayList = new ArrayList(size);
                                    int i8 = 0;
                                    while (i8 < size) {
                                        arrayList.add(new TabPosition(Dp.m7399constructorimpl(measureScope2.mo456toDpu2uoSUM(intRef.element) * i8), measureScope2.mo456toDpu2uoSUM(intRef.element), ((Dp) ComparisonsKt.maxOf(Dp.m7397boximpl(Dp.m7399constructorimpl(measureScope2.mo456toDpu2uoSUM(Math.min(list2.get(i8).maxIntrinsicWidth(intValue), intRef.element)) - Dp.m7399constructorimpl(TabKt.getHorizontalTextPadding() * i5))), Dp.m7397boximpl(Dp.m7399constructorimpl(24)))).m7413unboximpl(), null));
                                        i8++;
                                        measureScope2 = measureScope;
                                        i5 = 2;
                                    }
                                    tabRowKt$TabRowImpl$1$scope$1$12.setTabPositions(arrayList);
                                    ArrayList arrayList2 = new ArrayList(list2.size());
                                    int size3 = list2.size();
                                    for (int i9 = 0; i9 < size3; i9++) {
                                        Measurable measurable = list2.get(i9);
                                        int i10 = intValue;
                                        long m7331copyZbe2FdA = Constraints.m7331copyZbe2FdA(j3, intRef.element, intRef.element, i10, intValue);
                                        intValue = i10;
                                        arrayList2.add(measurable.mo6055measureBRTryo0(m7331copyZbe2FdA));
                                    }
                                    final ArrayList arrayList3 = arrayList2;
                                    ArrayList arrayList4 = new ArrayList(list3.size());
                                    int size4 = list3.size();
                                    for (int i11 = 0; i11 < size4; i11++) {
                                        arrayList4.add(list3.get(i11).mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(j3, 0, 0, 0, 0, 11, null)));
                                    }
                                    final ArrayList arrayList5 = arrayList4;
                                    ArrayList arrayList6 = new ArrayList(list4.size());
                                    int size5 = list4.size();
                                    int i12 = 0;
                                    while (i12 < size5) {
                                        int i13 = intValue;
                                        intValue = i13;
                                        arrayList6.add(list4.get(i12).mo6055measureBRTryo0(Constraints.m7331copyZbe2FdA(j3, intRef.element, intRef.element, 0, i13)));
                                        i12++;
                                        list4 = list4;
                                        arrayList6 = arrayList6;
                                    }
                                    final ArrayList arrayList7 = arrayList6;
                                    return MeasureScope.CC.layout$default(measureScope, m7342getMaxWidthimpl, intValue, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRowImpl$1$2$1.2
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                            invoke2(placementScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Placeable.PlacementScope placementScope) {
                                            List<Placeable> list5 = arrayList3;
                                            Ref.IntRef intRef2 = intRef;
                                            int size6 = list5.size();
                                            for (int i14 = 0; i14 < size6; i14++) {
                                                Placeable.PlacementScope.placeRelative$default(placementScope, list5.get(i14), i14 * intRef2.element, 0, 0.0f, 4, null);
                                            }
                                            List<Placeable> list6 = arrayList5;
                                            int i15 = intValue;
                                            int size7 = list6.size();
                                            for (int i16 = 0; i16 < size7; i16++) {
                                                Placeable placeable = list6.get(i16);
                                                Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, i15 - placeable.getHeight(), 0.0f, 4, null);
                                            }
                                            List<Placeable> list7 = arrayList7;
                                            int i17 = intValue;
                                            int size8 = list7.size();
                                            for (int i18 = 0; i18 < size8; i18++) {
                                                Placeable placeable2 = list7.get(i18);
                                                Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, 0, i17 - placeable2.getHeight(), 0.0f, 4, null);
                                            }
                                        }
                                    }, 4, null);
                                }
                            };
                            composer3.updateRememberedValue(rememberedValue2);
                        }
                        MultiContentMeasurePolicy multiContentMeasurePolicy = (MultiContentMeasurePolicy) rememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerStart(composer3, 1399185516, "CC(Layout)P(!1,2)173@6976L62,170@6862L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> combineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listOf);
                        ComposerKt.sourceInformationMarkerStart(composer3, -290761997, "CC(remember):Layout.kt#9igjgp");
                        Object rememberedValue3 = composer3.rememberedValue();
                        if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicy);
                            composer3.updateRememberedValue(rememberedValue3);
                        }
                        MeasurePolicy measurePolicy = (MeasurePolicy) rememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, fillMaxWidth$default);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                            composer3.createNode(constructor);
                        } else {
                            composer3.useNode();
                        }
                        Composer m3905constructorimpl = Updater.m3905constructorimpl(composer3);
                        Updater.m3912setimpl(m3905constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        combineAsVirtualLayouts.invoke(composer3, 0);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, startRestartGroup, 54), composer2, (i3 & 896) | 12582912 | (i3 & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
            composer2 = startRestartGroup;
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRowImpl$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i4) {
                    TabRowKt.m2755TabRowImplDTcfvLk(Modifier.this, j, j2, function3, function2, function22, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ScrollableTabRowImpl-sKfQg0A, reason: not valid java name */
    public static final void m2750ScrollableTabRowImplsKfQg0A(final int i, final Modifier modifier, final long j, final long j2, final float f, final ScrollState scrollState, final Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer composer, final int i2) {
        int i3;
        float f2;
        ScrollState scrollState2;
        Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function32;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Composer composer2;
        Composer startRestartGroup = composer.startRestartGroup(-1594140035);
        ComposerKt.sourceInformation(startRestartGroup, "C(ScrollableTabRowImpl)P(7,5,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3:c#ui.unit.Dp,6,4)710@32775L5081,700@32441L5415:TabRow.kt#uh7d8r");
        if ((i2 & 6) == 0) {
            i3 = (startRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= startRestartGroup.changed(modifier) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i3 |= startRestartGroup.changed(j) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i3 |= startRestartGroup.changed(j2) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            f2 = f;
            i3 |= startRestartGroup.changed(f2) ? 16384 : 8192;
        } else {
            f2 = f;
        }
        if ((196608 & i2) == 0) {
            scrollState2 = scrollState;
            i3 |= startRestartGroup.changed(scrollState2) ? 131072 : 65536;
        } else {
            scrollState2 = scrollState;
        }
        if ((1572864 & i2) == 0) {
            function32 = function3;
            i3 |= startRestartGroup.changedInstance(function32) ? 1048576 : 524288;
        } else {
            function32 = function3;
        }
        if ((i2 & 12582912) == 0) {
            function23 = function2;
            i3 |= startRestartGroup.changedInstance(function23) ? 8388608 : 4194304;
        } else {
            function23 = function2;
        }
        if ((100663296 & i2) == 0) {
            i3 |= startRestartGroup.changedInstance(function22) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i3 & 38347923) != 38347922 || !startRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1594140035, i3, -1, "androidx.compose.material3.ScrollableTabRowImpl (TabRow.kt:699)");
            }
            Modifier clipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), scrollState2, false, null, false, 14, null)));
            final Function2<? super Composer, ? super Integer, Unit> function24 = function23;
            final Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function33 = function32;
            final float f3 = f2;
            composer2 = startRestartGroup;
            SurfaceKt.m2699SurfaceT9BRK9s(clipToBounds, null, j, j2, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1556158104, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowImpl$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i4) {
                    ComposerKt.sourceInformation(composer3, "C711@32806L24,713@32875L147,717@33044L1274,757@34458L21,759@34509L3341,752@34328L3522:TabRow.kt#uh7d8r");
                    if ((i4 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1556158104, i4, -1, "androidx.compose.material3.ScrollableTabRowImpl.<anonymous> (TabRow.kt:711)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composer3, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composer3, -954363344, "CC(remember):Effects.kt#9igjgp");
                        Object rememberedValue = composer3.rememberedValue();
                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                            Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer3));
                            composer3.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                            rememberedValue = compositionScopedCoroutineScopeCanceller;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerStart(composer3, 413419233, "CC(remember):TabRow.kt#9igjgp");
                        boolean changed = composer3.changed(ScrollState.this) | composer3.changed(coroutineScope);
                        ScrollState scrollState3 = ScrollState.this;
                        Object rememberedValue2 = composer3.rememberedValue();
                        if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue2 = new ScrollableTabData(scrollState3, coroutineScope);
                            composer3.updateRememberedValue(rememberedValue2);
                        }
                        final ScrollableTabData scrollableTabData = (ScrollableTabData) rememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerStart(composer3, 413425768, "CC(remember):TabRow.kt#9igjgp");
                        Object rememberedValue3 = composer3.rememberedValue();
                        if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue3 = new TabRowKt$ScrollableTabRowImpl$1$scope$1$1();
                            composer3.updateRememberedValue(rememberedValue3);
                        }
                        final TabRowKt$ScrollableTabRowImpl$1$scope$1$1 tabRowKt$ScrollableTabRowImpl$1$scope$1$1 = (TabRowKt$ScrollableTabRowImpl$1$scope$1$1) rememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        final Function3<TabIndicatorScope, Composer, Integer, Unit> function34 = function33;
                        List listOf = CollectionsKt.listOf((Object[]) new Function2[]{function22, function24, ComposableLambdaKt.rememberComposableLambda(-1530560661, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowImpl$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                invoke(composer4, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i5) {
                                ComposerKt.sourceInformation(composer4, "C757@34466L11:TabRow.kt#uh7d8r");
                                if ((i5 & 3) == 2 && composer4.getSkipping()) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1530560661, i5, -1, "androidx.compose.material3.ScrollableTabRowImpl.<anonymous>.<anonymous> (TabRow.kt:757)");
                                }
                                function34.invoke(tabRowKt$ScrollableTabRowImpl$1$scope$1$1, composer4, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer3, 54)});
                        ComposerKt.sourceInformationMarkerStart(composer3, 413474715, "CC(remember):TabRow.kt#9igjgp");
                        boolean changed2 = composer3.changed(f3) | composer3.changed(i) | composer3.changedInstance(scrollableTabData);
                        final float f4 = f3;
                        final int i5 = i;
                        Object rememberedValue4 = composer3.rememberedValue();
                        if (changed2 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue4 = (MultiContentMeasurePolicy) new MultiContentMeasurePolicy() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowImpl$1$2$1
                                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                                public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i6) {
                                    return MultiContentMeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i6);
                                }

                                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                                public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i6) {
                                    return MultiContentMeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i6);
                                }

                                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                                public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i6) {
                                    return MultiContentMeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i6);
                                }

                                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                                public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i6) {
                                    return MultiContentMeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i6);
                                }

                                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                                /* renamed from: measure-3p2s80s */
                                public final MeasureResult mo816measure3p2s80s(final MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j3) {
                                    float f5;
                                    float f6;
                                    MeasureScope measureScope2 = measureScope;
                                    List<? extends Measurable> list2 = list.get(0);
                                    List<? extends Measurable> list3 = list.get(1);
                                    List<? extends Measurable> list4 = list.get(2);
                                    final int i6 = measureScope2.mo453roundToPx0680j_4(f4);
                                    int size = list2.size();
                                    f5 = TabRowKt.ScrollableTabRowMinimumTabWidth;
                                    int i7 = measureScope2.mo453roundToPx0680j_4(f5);
                                    int i8 = 0;
                                    int size2 = list2.size();
                                    for (int i9 = 0; i9 < size2; i9++) {
                                        i8 = Integer.valueOf(Math.max(i8.intValue(), list2.get(i9).maxIntrinsicHeight(Integer.MAX_VALUE)));
                                    }
                                    int intValue = i8.intValue();
                                    int i10 = i6 * 2;
                                    long m7332copyZbe2FdA$default = Constraints.m7332copyZbe2FdA$default(j3, i7, 0, intValue, intValue, 2, null);
                                    final Ref.FloatRef floatRef = new Ref.FloatRef();
                                    floatRef.element = f4;
                                    ArrayList arrayList = new ArrayList(list2.size());
                                    int size3 = list2.size();
                                    for (int i11 = 0; i11 < size3; i11++) {
                                        arrayList.add(list2.get(i11).mo6055measureBRTryo0(m7332copyZbe2FdA$default));
                                    }
                                    ArrayList arrayList2 = arrayList;
                                    ArrayList arrayList3 = new ArrayList(size);
                                    int i12 = 0;
                                    while (i12 < size) {
                                        f6 = TabRowKt.ScrollableTabRowMinimumTabWidth;
                                        float m7413unboximpl = ((Dp) ComparisonsKt.maxOf(Dp.m7397boximpl(f6), Dp.m7397boximpl(measureScope2.mo456toDpu2uoSUM(((Placeable) arrayList2.get(i12)).getWidth())))).m7413unboximpl();
                                        i10 += measureScope2.mo453roundToPx0680j_4(m7413unboximpl);
                                        TabPosition tabPosition = new TabPosition(floatRef.element, m7413unboximpl, ((Dp) ComparisonsKt.maxOf(Dp.m7397boximpl(Dp.m7399constructorimpl(m7413unboximpl - Dp.m7399constructorimpl(TabKt.getHorizontalTextPadding() * 2))), Dp.m7397boximpl(Dp.m7399constructorimpl(24)))).m7413unboximpl(), null);
                                        floatRef.element = Dp.m7399constructorimpl(floatRef.element + m7413unboximpl);
                                        arrayList3.add(tabPosition);
                                        i12++;
                                        arrayList2 = arrayList2;
                                    }
                                    final ArrayList arrayList4 = arrayList2;
                                    final ArrayList arrayList5 = arrayList3;
                                    tabRowKt$ScrollableTabRowImpl$1$scope$1$1.setTabPositions(arrayList5);
                                    ArrayList arrayList6 = new ArrayList(list3.size());
                                    int size4 = list3.size();
                                    int i13 = 0;
                                    while (i13 < size4) {
                                        int i14 = i10;
                                        arrayList6.add(list3.get(i13).mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(j3, i14, i10, 0, 0, 8, null)));
                                        i13++;
                                        list3 = list3;
                                        arrayList6 = arrayList6;
                                        i10 = i14;
                                    }
                                    int i15 = i10;
                                    final ArrayList arrayList7 = arrayList6;
                                    int i16 = i5;
                                    ArrayList arrayList8 = new ArrayList(list4.size());
                                    int size5 = list4.size();
                                    int i17 = 0;
                                    while (i17 < size5) {
                                        int i18 = intValue;
                                        intValue = i18;
                                        arrayList8.add(list4.get(i17).mo6055measureBRTryo0(Constraints.m7331copyZbe2FdA(j3, 0, measureScope2.mo453roundToPx0680j_4(((TabPosition) arrayList5.get(i16)).getWidth()), 0, i18)));
                                        i17++;
                                        measureScope2 = measureScope;
                                        list4 = list4;
                                    }
                                    final ArrayList arrayList9 = arrayList8;
                                    final float f7 = f4;
                                    final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                    final int i19 = i5;
                                    final int i20 = intValue;
                                    return MeasureScope.CC.layout$default(measureScope, i15, intValue, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowImpl$1$2$1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                            invoke2(placementScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Placeable.PlacementScope placementScope) {
                                            Ref.FloatRef.this.element = f7;
                                            List<Placeable> list5 = arrayList4;
                                            MeasureScope measureScope3 = measureScope;
                                            Ref.FloatRef floatRef2 = Ref.FloatRef.this;
                                            List<TabPosition> list6 = arrayList5;
                                            int size6 = list5.size();
                                            for (int i21 = 0; i21 < size6; i21++) {
                                                Placeable.PlacementScope.placeRelative$default(placementScope, list5.get(i21), measureScope3.mo453roundToPx0680j_4(floatRef2.element), 0, 0.0f, 4, null);
                                                floatRef2.element = Dp.m7399constructorimpl(floatRef2.element + list6.get(i21).getWidth());
                                            }
                                            List<Placeable> list7 = arrayList7;
                                            int i22 = i20;
                                            int size7 = list7.size();
                                            for (int i23 = 0; i23 < size7; i23++) {
                                                Placeable placeable = list7.get(i23);
                                                Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, i22 - placeable.getHeight(), 0.0f, 4, null);
                                            }
                                            List<Placeable> list8 = arrayList9;
                                            MeasureScope measureScope4 = measureScope;
                                            List<TabPosition> list9 = arrayList5;
                                            int i24 = i19;
                                            int i25 = i20;
                                            int size8 = list8.size();
                                            for (int i26 = 0; i26 < size8; i26++) {
                                                Placeable placeable2 = list8.get(i26);
                                                Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, Math.max(0, (measureScope4.mo453roundToPx0680j_4(list9.get(i24).getWidth()) - placeable2.getWidth()) / 2), i25 - placeable2.getHeight(), 0.0f, 4, null);
                                            }
                                            scrollableTabData2.onLaidOut(measureScope, i6, arrayList5, i19);
                                        }
                                    }, 4, null);
                                }
                            };
                            composer3.updateRememberedValue(rememberedValue4);
                        }
                        MultiContentMeasurePolicy multiContentMeasurePolicy = (MultiContentMeasurePolicy) rememberedValue4;
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerStart(composer3, 1399185516, "CC(Layout)P(!1,2)173@6976L62,170@6862L182:Layout.kt#80mrfh");
                        Modifier.Companion companion = Modifier.INSTANCE;
                        Function2<Composer, Integer, Unit> combineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listOf);
                        ComposerKt.sourceInformationMarkerStart(composer3, -290761997, "CC(remember):Layout.kt#9igjgp");
                        boolean changed3 = composer3.changed(multiContentMeasurePolicy);
                        Object rememberedValue5 = composer3.rememberedValue();
                        if (changed3 || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue5 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicy);
                            composer3.updateRememberedValue(rememberedValue5);
                        }
                        MeasurePolicy measurePolicy = (MeasurePolicy) rememberedValue5;
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, companion);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                            composer3.createNode(constructor);
                        } else {
                            composer3.useNode();
                        }
                        Composer m3905constructorimpl = Updater.m3905constructorimpl(composer3);
                        Updater.m3912setimpl(m3905constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        combineAsVirtualLayouts.invoke(composer3, 0);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, startRestartGroup, 54), composer2, (i3 & 896) | 12582912 | (i3 & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
            composer2 = startRestartGroup;
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowImpl$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i4) {
                    TabRowKt.m2750ScrollableTabRowImplsKfQg0A(i, modifier, j, j2, f, scrollState, function3, function2, function22, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: TabRowWithSubcomposeImpl-DTcfvLk, reason: not valid java name */
    public static final void m2756TabRowWithSubcomposeImplDTcfvLk(Modifier modifier, final long j, final long j2, final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer composer, final int i) {
        Modifier modifier2;
        int i2;
        Composer composer2;
        Composer startRestartGroup = composer.startRestartGroup(-160898917);
        ComposerKt.sourceInformation(startRestartGroup, "C(TabRowWithSubcomposeImpl)P(4,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3)953@41542L2218,949@41412L2348:TabRow.kt#uh7d8r");
        if ((i & 6) == 0) {
            modifier2 = modifier;
            i2 = (startRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(j) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changed(j2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= startRestartGroup.changedInstance(function3) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= startRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= startRestartGroup.changedInstance(function22) ? 131072 : 65536;
        }
        if ((74899 & i2) != 74898 || !startRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-160898917, i2, -1, "androidx.compose.material3.TabRowWithSubcomposeImpl (TabRow.kt:948)");
            }
            int i3 = i2 << 3;
            composer2 = startRestartGroup;
            SurfaceKt.m2699SurfaceT9BRK9s(SelectableGroupKt.selectableGroup(modifier2), null, j, j2, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1617702432, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRowWithSubcomposeImpl$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i4) {
                    ComposerKt.sourceInformation(composer3, "C954@41594L2160,954@41552L2202:TabRow.kt#uh7d8r");
                    if ((i4 & 3) == 2 && composer3.getSkipping()) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1617702432, i4, -1, "androidx.compose.material3.TabRowWithSubcomposeImpl.<anonymous> (TabRow.kt:954)");
                    }
                    Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(composer3, -1807606673, "CC(remember):TabRow.kt#9igjgp");
                    boolean changed = composer3.changed(function22) | composer3.changed(function2) | composer3.changed(function3);
                    final Function2<Composer, Integer, Unit> function23 = function22;
                    final Function2<Composer, Integer, Unit> function24 = function2;
                    final Function3<List<TabPosition>, Composer, Integer, Unit> function32 = function3;
                    Object rememberedValue = composer3.rememberedValue();
                    if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                        rememberedValue = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.TabRowKt$TabRowWithSubcomposeImpl$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                return m2764invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                            }

                            /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                            public final MeasureResult m2764invoke0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, final long j3) {
                                int m7342getMaxWidthimpl = Constraints.m7342getMaxWidthimpl(j3);
                                List<Measurable> subcompose = subcomposeMeasureScope.subcompose(TabSlots.Tabs, function23);
                                int size = subcompose.size();
                                final Ref.IntRef intRef = new Ref.IntRef();
                                if (size > 0) {
                                    intRef.element = m7342getMaxWidthimpl / size;
                                }
                                int i5 = 0;
                                int size2 = subcompose.size();
                                for (int i6 = 0; i6 < size2; i6++) {
                                    i5 = Integer.valueOf(Math.max(subcompose.get(i6).maxIntrinsicHeight(intRef.element), i5.intValue()));
                                }
                                final int intValue = i5.intValue();
                                ArrayList arrayList = new ArrayList(subcompose.size());
                                int size3 = subcompose.size();
                                int i7 = 0;
                                while (i7 < size3) {
                                    arrayList.add(subcompose.get(i7).mo6055measureBRTryo0(Constraints.m7331copyZbe2FdA(j3, intRef.element, intRef.element, intValue, intValue)));
                                    i7++;
                                    m7342getMaxWidthimpl = m7342getMaxWidthimpl;
                                }
                                final int i8 = m7342getMaxWidthimpl;
                                final ArrayList arrayList2 = arrayList;
                                ArrayList arrayList3 = new ArrayList(size);
                                for (int i9 = 0; i9 < size; i9++) {
                                    arrayList3.add(new TabPosition(Dp.m7399constructorimpl(subcomposeMeasureScope.mo456toDpu2uoSUM(intRef.element) * i9), subcomposeMeasureScope.mo456toDpu2uoSUM(intRef.element), ((Dp) ComparisonsKt.maxOf(Dp.m7397boximpl(Dp.m7399constructorimpl(subcomposeMeasureScope.mo456toDpu2uoSUM(Math.min(subcompose.get(i9).maxIntrinsicWidth(intValue), intRef.element)) - Dp.m7399constructorimpl(TabKt.getHorizontalTextPadding() * 2))), Dp.m7397boximpl(Dp.m7399constructorimpl(24)))).m7413unboximpl(), null));
                                }
                                final ArrayList arrayList4 = arrayList3;
                                final Function2<Composer, Integer, Unit> function25 = function24;
                                final Function3<List<TabPosition>, Composer, Integer, Unit> function33 = function32;
                                return MeasureScope.CC.layout$default(subcomposeMeasureScope, i8, intValue, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRowWithSubcomposeImpl$1$1$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                        invoke2(placementScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Placeable.PlacementScope placementScope) {
                                        List<Placeable> list = arrayList2;
                                        Ref.IntRef intRef2 = intRef;
                                        int size4 = list.size();
                                        for (int i10 = 0; i10 < size4; i10++) {
                                            Placeable.PlacementScope.placeRelative$default(placementScope, list.get(i10), i10 * intRef2.element, 0, 0.0f, 4, null);
                                        }
                                        List<Measurable> subcompose2 = subcomposeMeasureScope.subcompose(TabSlots.Divider, function25);
                                        long j4 = j3;
                                        int i11 = intValue;
                                        int size5 = subcompose2.size();
                                        for (int i12 = 0; i12 < size5; i12++) {
                                            Placeable mo6055measureBRTryo0 = subcompose2.get(i12).mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(j4, 0, 0, 0, 0, 11, null));
                                            Placeable.PlacementScope.placeRelative$default(placementScope, mo6055measureBRTryo0, 0, i11 - mo6055measureBRTryo0.getHeight(), 0.0f, 4, null);
                                        }
                                        SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                                        TabSlots tabSlots = TabSlots.Indicator;
                                        final Function3<List<TabPosition>, Composer, Integer, Unit> function34 = function33;
                                        final List<TabPosition> list2 = arrayList4;
                                        List<Measurable> subcompose3 = subcomposeMeasureScope2.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(1621992604, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt.TabRowWithSubcomposeImpl.1.1.1.1.3
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                                invoke(composer4, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer4, int i13) {
                                                ComposerKt.sourceInformation(composer4, "C1000@43547L23:TabRow.kt#uh7d8r");
                                                if ((i13 & 3) == 2 && composer4.getSkipping()) {
                                                    composer4.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1621992604, i13, -1, "androidx.compose.material3.TabRowWithSubcomposeImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:1000)");
                                                }
                                                function34.invoke(list2, composer4, 0);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }));
                                        int i13 = i8;
                                        int i14 = intValue;
                                        int size6 = subcompose3.size();
                                        for (int i15 = 0; i15 < size6; i15++) {
                                            Placeable.PlacementScope.placeRelative$default(placementScope, subcompose3.get(i15).mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(i13, i14)), 0, 0, 0.0f, 4, null);
                                        }
                                    }
                                }, 4, null);
                            }
                        };
                        composer3.updateRememberedValue(rememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    SubcomposeLayoutKt.SubcomposeLayout(fillMaxWidth$default, (Function2) rememberedValue, composer3, 6, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, startRestartGroup, 54), composer2, (i3 & 896) | 12582912 | (i3 & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
            composer2 = startRestartGroup;
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRowWithSubcomposeImpl$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i4) {
                    TabRowKt.m2756TabRowWithSubcomposeImplDTcfvLk(Modifier.this, j, j2, function3, function2, function22, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:106:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00c5  */
    /* renamed from: ScrollableTabRowWithSubcomposeImpl-qhFBPw4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2751ScrollableTabRowWithSubcomposeImplqhFBPw4(final int i, final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, long j, long j2, float f, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final ScrollState scrollState, Composer composer, final int i2, final int i3) {
        int i4;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function32;
        int i5;
        Modifier modifier2;
        long j3;
        long j4;
        int i6;
        float f2;
        int i7;
        Function2<? super Composer, ? super Integer, Unit> m2144getLambda7$material3_release;
        Composer composer2;
        final float f3;
        final Function2<? super Composer, ? super Integer, Unit> function23;
        final Modifier modifier3;
        final long j5;
        final long j6;
        ScopeUpdateScope endRestartGroup;
        int i8;
        int i9;
        Composer startRestartGroup = composer.startRestartGroup(-955409947);
        ComposerKt.sourceInformation(startRestartGroup, "C(ScrollableTabRowWithSubcomposeImpl)P(7,4,5,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3:c#ui.unit.Dp!1,8)1014@43999L21,1015@44063L19,1021@44384L3880,1021@44302L3962:TabRow.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (startRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i3 & 2) != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            function32 = function3;
            i4 |= startRestartGroup.changedInstance(function32) ? 32 : 16;
            i5 = i3 & 4;
            if (i5 == 0) {
                i4 |= 384;
            } else if ((i2 & 384) == 0) {
                modifier2 = modifier;
                i4 |= startRestartGroup.changed(modifier2) ? 256 : 128;
                if ((i2 & 3072) == 0) {
                    if ((i3 & 8) == 0) {
                        j3 = j;
                        if (startRestartGroup.changed(j3)) {
                            i9 = 2048;
                            i4 |= i9;
                        }
                    } else {
                        j3 = j;
                    }
                    i9 = 1024;
                    i4 |= i9;
                } else {
                    j3 = j;
                }
                if ((i2 & 24576) == 0) {
                    if ((i3 & 16) == 0) {
                        j4 = j2;
                        if (startRestartGroup.changed(j4)) {
                            i8 = 16384;
                            i4 |= i8;
                        }
                    } else {
                        j4 = j2;
                    }
                    i8 = 8192;
                    i4 |= i8;
                } else {
                    j4 = j2;
                }
                i6 = i3 & 32;
                if (i6 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i2) == 0) {
                    f2 = f;
                    i4 |= startRestartGroup.changed(f2) ? 131072 : 65536;
                    i7 = i3 & 64;
                    if (i7 == 0) {
                        i4 |= 1572864;
                    } else if ((i2 & 1572864) == 0) {
                        i4 |= startRestartGroup.changedInstance(function2) ? 1048576 : 524288;
                    }
                    if ((i3 & 128) == 0) {
                        i4 |= 12582912;
                    } else if ((i2 & 12582912) == 0) {
                        i4 |= startRestartGroup.changedInstance(function22) ? 8388608 : 4194304;
                        if ((i3 & 256) != 0) {
                            i4 |= 100663296;
                        } else if ((i2 & 100663296) == 0) {
                            i4 |= startRestartGroup.changed(scrollState) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                            if ((i4 & 38347923) == 38347922 || !startRestartGroup.getSkipping()) {
                                startRestartGroup.startDefaults();
                                if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if ((i3 & 8) != 0) {
                                        j3 = TabRowDefaults.INSTANCE.getPrimaryContainerColor(startRestartGroup, 6);
                                        i4 &= -7169;
                                    }
                                    if ((i3 & 16) != 0) {
                                        j4 = TabRowDefaults.INSTANCE.getPrimaryContentColor(startRestartGroup, 6);
                                        i4 &= -57345;
                                    }
                                    if (i6 != 0) {
                                        f2 = TabRowDefaults.INSTANCE.m2745getScrollableTabRowEdgeStartPaddingD9Ej5fM();
                                    }
                                    if (i7 != 0) {
                                        m2144getLambda7$material3_release = ComposableSingletons$TabRowKt.INSTANCE.m2144getLambda7$material3_release();
                                        long j7 = j4;
                                        final float f4 = f2;
                                        Modifier modifier4 = modifier2;
                                        long j8 = j3;
                                        startRestartGroup.endDefaults();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-955409947, i4, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl (TabRow.kt:1020)");
                                        }
                                        final Function2<? super Composer, ? super Integer, Unit> function24 = m2144getLambda7$material3_release;
                                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function33 = function32;
                                        ComposableLambda rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1572959552, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                invoke(composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer3, int i10) {
                                                ComposerKt.sourceInformation(composer3, "C1022@44415L24,1024@44484L147,1033@44882L3376,1027@44640L3618:TabRow.kt#uh7d8r");
                                                if ((i10 & 3) != 2 || !composer3.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-1572959552, i10, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous> (TabRow.kt:1022)");
                                                    }
                                                    ComposerKt.sourceInformationMarkerStart(composer3, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                                                    ComposerKt.sourceInformationMarkerStart(composer3, -954363344, "CC(remember):Effects.kt#9igjgp");
                                                    Object rememberedValue = composer3.rememberedValue();
                                                    if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                                        Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer3));
                                                        composer3.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                                                        rememberedValue = compositionScopedCoroutineScopeCanceller;
                                                    }
                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                    CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                    ComposerKt.sourceInformationMarkerStart(composer3, -702885223, "CC(remember):TabRow.kt#9igjgp");
                                                    boolean changed = composer3.changed(ScrollState.this) | composer3.changed(coroutineScope);
                                                    ScrollState scrollState2 = ScrollState.this;
                                                    Object rememberedValue2 = composer3.rememberedValue();
                                                    if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                                        rememberedValue2 = new ScrollableTabData(scrollState2, coroutineScope);
                                                        composer3.updateRememberedValue(rememberedValue2);
                                                    }
                                                    final ScrollableTabData scrollableTabData = (ScrollableTabData) rememberedValue2;
                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                    Modifier clipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), ScrollState.this, false, null, false, 14, null)));
                                                    ComposerKt.sourceInformationMarkerStart(composer3, -702869258, "CC(remember):TabRow.kt#9igjgp");
                                                    boolean changed2 = composer3.changed(f4) | composer3.changed(function22) | composer3.changed(function24) | composer3.changed(function33) | composer3.changedInstance(scrollableTabData) | composer3.changed(i);
                                                    final float f5 = f4;
                                                    final Function2<Composer, Integer, Unit> function25 = function22;
                                                    final Function2<Composer, Integer, Unit> function26 = function24;
                                                    final int i11 = i;
                                                    final Function3<List<TabPosition>, Composer, Integer, Unit> function34 = function33;
                                                    Object rememberedValue3 = composer3.rememberedValue();
                                                    if (changed2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                                        rememberedValue3 = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1
                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                            /* JADX WARN: Multi-variable type inference failed */
                                                            {
                                                                super(2);
                                                            }

                                                            @Override // kotlin.jvm.functions.Function2
                                                            public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                                                return m2762invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                                                            }

                                                            /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                                                            public final MeasureResult m2762invoke0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, final long j9) {
                                                                float f6;
                                                                f6 = TabRowKt.ScrollableTabRowMinimumTabWidth;
                                                                int i12 = subcomposeMeasureScope.mo453roundToPx0680j_4(f6);
                                                                final int i13 = subcomposeMeasureScope.mo453roundToPx0680j_4(f5);
                                                                List<Measurable> subcompose = subcomposeMeasureScope.subcompose(TabSlots.Tabs, function25);
                                                                int i14 = 0;
                                                                int size = subcompose.size();
                                                                for (int i15 = 0; i15 < size; i15++) {
                                                                    i14 = Integer.valueOf(Math.max(i14.intValue(), subcompose.get(i15).maxIntrinsicHeight(Integer.MAX_VALUE)));
                                                                }
                                                                final int intValue = i14.intValue();
                                                                long m7332copyZbe2FdA$default = Constraints.m7332copyZbe2FdA$default(j9, i12, 0, intValue, intValue, 2, null);
                                                                final ArrayList arrayList = new ArrayList();
                                                                final ArrayList arrayList2 = new ArrayList();
                                                                int size2 = subcompose.size();
                                                                for (int i16 = 0; i16 < size2; i16++) {
                                                                    Measurable measurable = subcompose.get(i16);
                                                                    Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(m7332copyZbe2FdA$default);
                                                                    float m7399constructorimpl = Dp.m7399constructorimpl(subcomposeMeasureScope.mo456toDpu2uoSUM(Math.min(measurable.maxIntrinsicWidth(mo6055measureBRTryo0.getHeight()), mo6055measureBRTryo0.getWidth())) - Dp.m7399constructorimpl(TabKt.getHorizontalTextPadding() * 2));
                                                                    arrayList.add(mo6055measureBRTryo0);
                                                                    arrayList2.add(Dp.m7397boximpl(m7399constructorimpl));
                                                                }
                                                                Integer valueOf = Integer.valueOf(i13 * 2);
                                                                int size3 = arrayList.size();
                                                                for (int i17 = 0; i17 < size3; i17++) {
                                                                    valueOf = Integer.valueOf(valueOf.intValue() + ((Placeable) arrayList.get(i17)).getWidth());
                                                                }
                                                                final int intValue2 = valueOf.intValue();
                                                                final Function2<Composer, Integer, Unit> function27 = function26;
                                                                final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                                                final int i18 = i11;
                                                                final Function3<List<TabPosition>, Composer, Integer, Unit> function35 = function34;
                                                                return MeasureScope.CC.layout$default(subcomposeMeasureScope, intValue2, intValue, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1.2
                                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                    /* JADX WARN: Multi-variable type inference failed */
                                                                    {
                                                                        super(1);
                                                                    }

                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                                        invoke2(placementScope);
                                                                        return Unit.INSTANCE;
                                                                    }

                                                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                    public final void invoke2(Placeable.PlacementScope placementScope) {
                                                                        final ArrayList arrayList3 = new ArrayList();
                                                                        int i19 = i13;
                                                                        List<Placeable> list = arrayList;
                                                                        SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                                                                        List<Dp> list2 = arrayList2;
                                                                        int size4 = list.size();
                                                                        int i20 = i19;
                                                                        for (int i21 = 0; i21 < size4; i21++) {
                                                                            Placeable placeable = list.get(i21);
                                                                            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i20, 0, 0.0f, 4, null);
                                                                            arrayList3.add(new TabPosition(subcomposeMeasureScope2.mo456toDpu2uoSUM(i20), subcomposeMeasureScope2.mo456toDpu2uoSUM(placeable.getWidth()), list2.get(i21).m7413unboximpl(), null));
                                                                            i20 += placeable.getWidth();
                                                                        }
                                                                        List<Measurable> subcompose2 = subcomposeMeasureScope.subcompose(TabSlots.Divider, function27);
                                                                        long j10 = j9;
                                                                        int i22 = intValue2;
                                                                        int i23 = intValue;
                                                                        int size5 = subcompose2.size();
                                                                        for (int i24 = 0; i24 < size5; i24++) {
                                                                            Placeable mo6055measureBRTryo02 = subcompose2.get(i24).mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(j10, i22, i22, 0, 0, 8, null));
                                                                            Placeable.PlacementScope.placeRelative$default(placementScope, mo6055measureBRTryo02, 0, i23 - mo6055measureBRTryo02.getHeight(), 0.0f, 4, null);
                                                                        }
                                                                        SubcomposeMeasureScope subcomposeMeasureScope3 = subcomposeMeasureScope;
                                                                        TabSlots tabSlots = TabSlots.Indicator;
                                                                        final Function3<List<TabPosition>, Composer, Integer, Unit> function36 = function35;
                                                                        List<Measurable> subcompose3 = subcomposeMeasureScope3.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(1734082948, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt.ScrollableTabRowWithSubcomposeImpl.1.1.1.2.3
                                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                            /* JADX WARN: Multi-variable type inference failed */
                                                                            {
                                                                                super(2);
                                                                            }

                                                                            @Override // kotlin.jvm.functions.Function2
                                                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                                                                invoke(composer4, num.intValue());
                                                                                return Unit.INSTANCE;
                                                                            }

                                                                            public final void invoke(Composer composer4, int i25) {
                                                                                ComposerKt.sourceInformation(composer4, "C1100@47792L23:TabRow.kt#uh7d8r");
                                                                                if ((i25 & 3) == 2 && composer4.getSkipping()) {
                                                                                    composer4.skipToGroupEnd();
                                                                                    return;
                                                                                }
                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                    ComposerKt.traceEventStart(1734082948, i25, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:1100)");
                                                                                }
                                                                                function36.invoke(arrayList3, composer4, 0);
                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                    ComposerKt.traceEventEnd();
                                                                                }
                                                                            }
                                                                        }));
                                                                        int i25 = intValue2;
                                                                        int i26 = intValue;
                                                                        int size6 = subcompose3.size();
                                                                        for (int i27 = 0; i27 < size6; i27++) {
                                                                            Placeable.PlacementScope.placeRelative$default(placementScope, subcompose3.get(i27).mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(i25, i26)), 0, 0, 0.0f, 4, null);
                                                                        }
                                                                        scrollableTabData2.onLaidOut(subcomposeMeasureScope, i13, arrayList3, i18);
                                                                    }
                                                                }, 4, null);
                                                            }
                                                        };
                                                        composer3.updateRememberedValue(rememberedValue3);
                                                    }
                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                    SubcomposeLayoutKt.SubcomposeLayout(clipToBounds, (Function2) rememberedValue3, composer3, 0, 0);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer3.skipToGroupEnd();
                                            }
                                        }, startRestartGroup, 54);
                                        int i10 = ((i4 >> 6) & 14) | 12582912;
                                        int i11 = i4 >> 3;
                                        composer2 = startRestartGroup;
                                        SurfaceKt.m2699SurfaceT9BRK9s(modifier4, null, j8, j7, 0.0f, 0.0f, null, rememberComposableLambda, composer2, i10 | (i11 & 896) | (i11 & 7168), 114);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        f3 = f4;
                                        function23 = function24;
                                        modifier3 = modifier4;
                                        j5 = j8;
                                        j6 = j7;
                                    }
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i3 & 8) != 0) {
                                        i4 &= -7169;
                                    }
                                    if ((i3 & 16) != 0) {
                                        i4 &= -57345;
                                    }
                                }
                                m2144getLambda7$material3_release = function2;
                                long j72 = j4;
                                final float f42 = f2;
                                Modifier modifier42 = modifier2;
                                long j82 = j3;
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                final Function2<? super Composer, ? super Integer, Unit> function242 = m2144getLambda7$material3_release;
                                final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function332 = function32;
                                ComposableLambda rememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-1572959552, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i102) {
                                        ComposerKt.sourceInformation(composer3, "C1022@44415L24,1024@44484L147,1033@44882L3376,1027@44640L3618:TabRow.kt#uh7d8r");
                                        if ((i102 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1572959552, i102, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous> (TabRow.kt:1022)");
                                            }
                                            ComposerKt.sourceInformationMarkerStart(composer3, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                                            ComposerKt.sourceInformationMarkerStart(composer3, -954363344, "CC(remember):Effects.kt#9igjgp");
                                            Object rememberedValue = composer3.rememberedValue();
                                            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                                Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer3));
                                                composer3.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                                                rememberedValue = compositionScopedCoroutineScopeCanceller;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            ComposerKt.sourceInformationMarkerStart(composer3, -702885223, "CC(remember):TabRow.kt#9igjgp");
                                            boolean changed = composer3.changed(ScrollState.this) | composer3.changed(coroutineScope);
                                            ScrollState scrollState2 = ScrollState.this;
                                            Object rememberedValue2 = composer3.rememberedValue();
                                            if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue2 = new ScrollableTabData(scrollState2, coroutineScope);
                                                composer3.updateRememberedValue(rememberedValue2);
                                            }
                                            final ScrollableTabData scrollableTabData = (ScrollableTabData) rememberedValue2;
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            Modifier clipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), ScrollState.this, false, null, false, 14, null)));
                                            ComposerKt.sourceInformationMarkerStart(composer3, -702869258, "CC(remember):TabRow.kt#9igjgp");
                                            boolean changed2 = composer3.changed(f42) | composer3.changed(function22) | composer3.changed(function242) | composer3.changed(function332) | composer3.changedInstance(scrollableTabData) | composer3.changed(i);
                                            final float f5 = f42;
                                            final Function2<? super Composer, ? super Integer, Unit> function25 = function22;
                                            final Function2<? super Composer, ? super Integer, Unit> function26 = function242;
                                            final int i112 = i;
                                            final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function34 = function332;
                                            Object rememberedValue3 = composer3.rememberedValue();
                                            if (changed2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue3 = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    /* JADX WARN: Multi-variable type inference failed */
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                                        return m2762invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                                                    }

                                                    /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                                                    public final MeasureResult m2762invoke0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, final long j9) {
                                                        float f6;
                                                        f6 = TabRowKt.ScrollableTabRowMinimumTabWidth;
                                                        int i12 = subcomposeMeasureScope.mo453roundToPx0680j_4(f6);
                                                        final int i13 = subcomposeMeasureScope.mo453roundToPx0680j_4(f5);
                                                        List<Measurable> subcompose = subcomposeMeasureScope.subcompose(TabSlots.Tabs, function25);
                                                        int i14 = 0;
                                                        int size = subcompose.size();
                                                        for (int i15 = 0; i15 < size; i15++) {
                                                            i14 = Integer.valueOf(Math.max(i14.intValue(), subcompose.get(i15).maxIntrinsicHeight(Integer.MAX_VALUE)));
                                                        }
                                                        final int intValue = i14.intValue();
                                                        long m7332copyZbe2FdA$default = Constraints.m7332copyZbe2FdA$default(j9, i12, 0, intValue, intValue, 2, null);
                                                        final List<Placeable> arrayList = new ArrayList();
                                                        final List<Dp> arrayList2 = new ArrayList();
                                                        int size2 = subcompose.size();
                                                        for (int i16 = 0; i16 < size2; i16++) {
                                                            Measurable measurable = subcompose.get(i16);
                                                            Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(m7332copyZbe2FdA$default);
                                                            float m7399constructorimpl = Dp.m7399constructorimpl(subcomposeMeasureScope.mo456toDpu2uoSUM(Math.min(measurable.maxIntrinsicWidth(mo6055measureBRTryo0.getHeight()), mo6055measureBRTryo0.getWidth())) - Dp.m7399constructorimpl(TabKt.getHorizontalTextPadding() * 2));
                                                            arrayList.add(mo6055measureBRTryo0);
                                                            arrayList2.add(Dp.m7397boximpl(m7399constructorimpl));
                                                        }
                                                        Integer valueOf = Integer.valueOf(i13 * 2);
                                                        int size3 = arrayList.size();
                                                        for (int i17 = 0; i17 < size3; i17++) {
                                                            valueOf = Integer.valueOf(valueOf.intValue() + ((Placeable) arrayList.get(i17)).getWidth());
                                                        }
                                                        final int intValue2 = valueOf.intValue();
                                                        final Function2<? super Composer, ? super Integer, Unit> function27 = function26;
                                                        final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                                        final int i18 = i112;
                                                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function35 = function34;
                                                        return MeasureScope.CC.layout$default(subcomposeMeasureScope, intValue2, intValue, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1.2
                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                            /* JADX WARN: Multi-variable type inference failed */
                                                            {
                                                                super(1);
                                                            }

                                                            @Override // kotlin.jvm.functions.Function1
                                                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                                invoke2(placementScope);
                                                                return Unit.INSTANCE;
                                                            }

                                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                            public final void invoke2(Placeable.PlacementScope placementScope) {
                                                                final List<TabPosition> arrayList3 = new ArrayList();
                                                                int i19 = i13;
                                                                List<Placeable> list = arrayList;
                                                                SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                                                                List<Dp> list2 = arrayList2;
                                                                int size4 = list.size();
                                                                int i20 = i19;
                                                                for (int i21 = 0; i21 < size4; i21++) {
                                                                    Placeable placeable = list.get(i21);
                                                                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i20, 0, 0.0f, 4, null);
                                                                    arrayList3.add(new TabPosition(subcomposeMeasureScope2.mo456toDpu2uoSUM(i20), subcomposeMeasureScope2.mo456toDpu2uoSUM(placeable.getWidth()), list2.get(i21).m7413unboximpl(), null));
                                                                    i20 += placeable.getWidth();
                                                                }
                                                                List<Measurable> subcompose2 = subcomposeMeasureScope.subcompose(TabSlots.Divider, function27);
                                                                long j10 = j9;
                                                                int i22 = intValue2;
                                                                int i23 = intValue;
                                                                int size5 = subcompose2.size();
                                                                for (int i24 = 0; i24 < size5; i24++) {
                                                                    Placeable mo6055measureBRTryo02 = subcompose2.get(i24).mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(j10, i22, i22, 0, 0, 8, null));
                                                                    Placeable.PlacementScope.placeRelative$default(placementScope, mo6055measureBRTryo02, 0, i23 - mo6055measureBRTryo02.getHeight(), 0.0f, 4, null);
                                                                }
                                                                SubcomposeMeasureScope subcomposeMeasureScope3 = subcomposeMeasureScope;
                                                                TabSlots tabSlots = TabSlots.Indicator;
                                                                final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function36 = function35;
                                                                List<Measurable> subcompose3 = subcomposeMeasureScope3.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(1734082948, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt.ScrollableTabRowWithSubcomposeImpl.1.1.1.2.3
                                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                    /* JADX WARN: Multi-variable type inference failed */
                                                                    {
                                                                        super(2);
                                                                    }

                                                                    @Override // kotlin.jvm.functions.Function2
                                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                                                        invoke(composer4, num.intValue());
                                                                        return Unit.INSTANCE;
                                                                    }

                                                                    public final void invoke(Composer composer4, int i25) {
                                                                        ComposerKt.sourceInformation(composer4, "C1100@47792L23:TabRow.kt#uh7d8r");
                                                                        if ((i25 & 3) == 2 && composer4.getSkipping()) {
                                                                            composer4.skipToGroupEnd();
                                                                            return;
                                                                        }
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventStart(1734082948, i25, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:1100)");
                                                                        }
                                                                        function36.invoke(arrayList3, composer4, 0);
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventEnd();
                                                                        }
                                                                    }
                                                                }));
                                                                int i25 = intValue2;
                                                                int i26 = intValue;
                                                                int size6 = subcompose3.size();
                                                                for (int i27 = 0; i27 < size6; i27++) {
                                                                    Placeable.PlacementScope.placeRelative$default(placementScope, subcompose3.get(i27).mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(i25, i26)), 0, 0, 0.0f, 4, null);
                                                                }
                                                                scrollableTabData2.onLaidOut(subcomposeMeasureScope, i13, arrayList3, i18);
                                                            }
                                                        }, 4, null);
                                                    }
                                                };
                                                composer3.updateRememberedValue(rememberedValue3);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            SubcomposeLayoutKt.SubcomposeLayout(clipToBounds, (Function2) rememberedValue3, composer3, 0, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }, startRestartGroup, 54);
                                int i102 = ((i4 >> 6) & 14) | 12582912;
                                int i112 = i4 >> 3;
                                composer2 = startRestartGroup;
                                SurfaceKt.m2699SurfaceT9BRK9s(modifier42, null, j82, j72, 0.0f, 0.0f, null, rememberComposableLambda2, composer2, i102 | (i112 & 896) | (i112 & 7168), 114);
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                f3 = f42;
                                function23 = function242;
                                modifier3 = modifier42;
                                j5 = j82;
                                j6 = j72;
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                composer2 = startRestartGroup;
                                modifier3 = modifier2;
                                j5 = j3;
                                j6 = j4;
                                f3 = f2;
                                function23 = function2;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i12) {
                                        TabRowKt.m2751ScrollableTabRowWithSubcomposeImplqhFBPw4(i, function3, modifier3, j5, j6, f3, function23, function22, scrollState, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        if ((i4 & 38347923) == 38347922) {
                        }
                        startRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                        }
                        if (i5 != 0) {
                        }
                        if ((i3 & 8) != 0) {
                        }
                        if ((i3 & 16) != 0) {
                        }
                        if (i6 != 0) {
                        }
                        if (i7 != 0) {
                        }
                        m2144getLambda7$material3_release = function2;
                        long j722 = j4;
                        final float f422 = f2;
                        Modifier modifier422 = modifier2;
                        long j822 = j3;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function2422 = m2144getLambda7$material3_release;
                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3322 = function32;
                        ComposableLambda rememberComposableLambda22 = ComposableLambdaKt.rememberComposableLambda(-1572959552, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1022) {
                                ComposerKt.sourceInformation(composer3, "C1022@44415L24,1024@44484L147,1033@44882L3376,1027@44640L3618:TabRow.kt#uh7d8r");
                                if ((i1022 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1572959552, i1022, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous> (TabRow.kt:1022)");
                                    }
                                    ComposerKt.sourceInformationMarkerStart(composer3, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                                    ComposerKt.sourceInformationMarkerStart(composer3, -954363344, "CC(remember):Effects.kt#9igjgp");
                                    Object rememberedValue = composer3.rememberedValue();
                                    if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                        Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer3));
                                        composer3.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                                        rememberedValue = compositionScopedCoroutineScopeCanceller;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -702885223, "CC(remember):TabRow.kt#9igjgp");
                                    boolean changed = composer3.changed(ScrollState.this) | composer3.changed(coroutineScope);
                                    ScrollState scrollState2 = ScrollState.this;
                                    Object rememberedValue2 = composer3.rememberedValue();
                                    if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue2 = new ScrollableTabData(scrollState2, coroutineScope);
                                        composer3.updateRememberedValue(rememberedValue2);
                                    }
                                    final ScrollableTabData scrollableTabData = (ScrollableTabData) rememberedValue2;
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    Modifier clipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), ScrollState.this, false, null, false, 14, null)));
                                    ComposerKt.sourceInformationMarkerStart(composer3, -702869258, "CC(remember):TabRow.kt#9igjgp");
                                    boolean changed2 = composer3.changed(f422) | composer3.changed(function22) | composer3.changed(function2422) | composer3.changed(function3322) | composer3.changedInstance(scrollableTabData) | composer3.changed(i);
                                    final float f5 = f422;
                                    final Function2<? super Composer, ? super Integer, Unit> function25 = function22;
                                    final Function2<? super Composer, ? super Integer, Unit> function26 = function2422;
                                    final int i1122 = i;
                                    final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function34 = function3322;
                                    Object rememberedValue3 = composer3.rememberedValue();
                                    if (changed2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue3 = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                                return m2762invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                                            }

                                            /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                                            public final MeasureResult m2762invoke0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, final long j9) {
                                                float f6;
                                                f6 = TabRowKt.ScrollableTabRowMinimumTabWidth;
                                                int i12 = subcomposeMeasureScope.mo453roundToPx0680j_4(f6);
                                                final int i13 = subcomposeMeasureScope.mo453roundToPx0680j_4(f5);
                                                List<Measurable> subcompose = subcomposeMeasureScope.subcompose(TabSlots.Tabs, function25);
                                                int i14 = 0;
                                                int size = subcompose.size();
                                                for (int i15 = 0; i15 < size; i15++) {
                                                    i14 = Integer.valueOf(Math.max(i14.intValue(), subcompose.get(i15).maxIntrinsicHeight(Integer.MAX_VALUE)));
                                                }
                                                final int intValue = i14.intValue();
                                                long m7332copyZbe2FdA$default = Constraints.m7332copyZbe2FdA$default(j9, i12, 0, intValue, intValue, 2, null);
                                                final List<Placeable> arrayList = new ArrayList();
                                                final List<Dp> arrayList2 = new ArrayList();
                                                int size2 = subcompose.size();
                                                for (int i16 = 0; i16 < size2; i16++) {
                                                    Measurable measurable = subcompose.get(i16);
                                                    Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(m7332copyZbe2FdA$default);
                                                    float m7399constructorimpl = Dp.m7399constructorimpl(subcomposeMeasureScope.mo456toDpu2uoSUM(Math.min(measurable.maxIntrinsicWidth(mo6055measureBRTryo0.getHeight()), mo6055measureBRTryo0.getWidth())) - Dp.m7399constructorimpl(TabKt.getHorizontalTextPadding() * 2));
                                                    arrayList.add(mo6055measureBRTryo0);
                                                    arrayList2.add(Dp.m7397boximpl(m7399constructorimpl));
                                                }
                                                Integer valueOf = Integer.valueOf(i13 * 2);
                                                int size3 = arrayList.size();
                                                for (int i17 = 0; i17 < size3; i17++) {
                                                    valueOf = Integer.valueOf(valueOf.intValue() + ((Placeable) arrayList.get(i17)).getWidth());
                                                }
                                                final int intValue2 = valueOf.intValue();
                                                final Function2<? super Composer, ? super Integer, Unit> function27 = function26;
                                                final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                                final int i18 = i1122;
                                                final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function35 = function34;
                                                return MeasureScope.CC.layout$default(subcomposeMeasureScope, intValue2, intValue, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1.2
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    /* JADX WARN: Multi-variable type inference failed */
                                                    {
                                                        super(1);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function1
                                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                        invoke2(placementScope);
                                                        return Unit.INSTANCE;
                                                    }

                                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                    public final void invoke2(Placeable.PlacementScope placementScope) {
                                                        final List<TabPosition> arrayList3 = new ArrayList();
                                                        int i19 = i13;
                                                        List<Placeable> list = arrayList;
                                                        SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                                                        List<Dp> list2 = arrayList2;
                                                        int size4 = list.size();
                                                        int i20 = i19;
                                                        for (int i21 = 0; i21 < size4; i21++) {
                                                            Placeable placeable = list.get(i21);
                                                            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i20, 0, 0.0f, 4, null);
                                                            arrayList3.add(new TabPosition(subcomposeMeasureScope2.mo456toDpu2uoSUM(i20), subcomposeMeasureScope2.mo456toDpu2uoSUM(placeable.getWidth()), list2.get(i21).m7413unboximpl(), null));
                                                            i20 += placeable.getWidth();
                                                        }
                                                        List<Measurable> subcompose2 = subcomposeMeasureScope.subcompose(TabSlots.Divider, function27);
                                                        long j10 = j9;
                                                        int i22 = intValue2;
                                                        int i23 = intValue;
                                                        int size5 = subcompose2.size();
                                                        for (int i24 = 0; i24 < size5; i24++) {
                                                            Placeable mo6055measureBRTryo02 = subcompose2.get(i24).mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(j10, i22, i22, 0, 0, 8, null));
                                                            Placeable.PlacementScope.placeRelative$default(placementScope, mo6055measureBRTryo02, 0, i23 - mo6055measureBRTryo02.getHeight(), 0.0f, 4, null);
                                                        }
                                                        SubcomposeMeasureScope subcomposeMeasureScope3 = subcomposeMeasureScope;
                                                        TabSlots tabSlots = TabSlots.Indicator;
                                                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function36 = function35;
                                                        List<Measurable> subcompose3 = subcomposeMeasureScope3.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(1734082948, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt.ScrollableTabRowWithSubcomposeImpl.1.1.1.2.3
                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                            /* JADX WARN: Multi-variable type inference failed */
                                                            {
                                                                super(2);
                                                            }

                                                            @Override // kotlin.jvm.functions.Function2
                                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                                                invoke(composer4, num.intValue());
                                                                return Unit.INSTANCE;
                                                            }

                                                            public final void invoke(Composer composer4, int i25) {
                                                                ComposerKt.sourceInformation(composer4, "C1100@47792L23:TabRow.kt#uh7d8r");
                                                                if ((i25 & 3) == 2 && composer4.getSkipping()) {
                                                                    composer4.skipToGroupEnd();
                                                                    return;
                                                                }
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventStart(1734082948, i25, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:1100)");
                                                                }
                                                                function36.invoke(arrayList3, composer4, 0);
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventEnd();
                                                                }
                                                            }
                                                        }));
                                                        int i25 = intValue2;
                                                        int i26 = intValue;
                                                        int size6 = subcompose3.size();
                                                        for (int i27 = 0; i27 < size6; i27++) {
                                                            Placeable.PlacementScope.placeRelative$default(placementScope, subcompose3.get(i27).mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(i25, i26)), 0, 0, 0.0f, 4, null);
                                                        }
                                                        scrollableTabData2.onLaidOut(subcomposeMeasureScope, i13, arrayList3, i18);
                                                    }
                                                }, 4, null);
                                            }
                                        };
                                        composer3.updateRememberedValue(rememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    SubcomposeLayoutKt.SubcomposeLayout(clipToBounds, (Function2) rememberedValue3, composer3, 0, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, startRestartGroup, 54);
                        int i1022 = ((i4 >> 6) & 14) | 12582912;
                        int i1122 = i4 >> 3;
                        composer2 = startRestartGroup;
                        SurfaceKt.m2699SurfaceT9BRK9s(modifier422, null, j822, j722, 0.0f, 0.0f, null, rememberComposableLambda22, composer2, i1022 | (i1122 & 896) | (i1122 & 7168), 114);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        f3 = f422;
                        function23 = function2422;
                        modifier3 = modifier422;
                        j5 = j822;
                        j6 = j722;
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    if ((i3 & 256) != 0) {
                    }
                    if ((i4 & 38347923) == 38347922) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if ((i3 & 8) != 0) {
                    }
                    if ((i3 & 16) != 0) {
                    }
                    if (i6 != 0) {
                    }
                    if (i7 != 0) {
                    }
                    m2144getLambda7$material3_release = function2;
                    long j7222 = j4;
                    final float f4222 = f2;
                    Modifier modifier4222 = modifier2;
                    long j8222 = j3;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function24222 = m2144getLambda7$material3_release;
                    final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function33222 = function32;
                    ComposableLambda rememberComposableLambda222 = ComposableLambdaKt.rememberComposableLambda(-1572959552, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i10222) {
                            ComposerKt.sourceInformation(composer3, "C1022@44415L24,1024@44484L147,1033@44882L3376,1027@44640L3618:TabRow.kt#uh7d8r");
                            if ((i10222 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1572959552, i10222, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous> (TabRow.kt:1022)");
                                }
                                ComposerKt.sourceInformationMarkerStart(composer3, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                                ComposerKt.sourceInformationMarkerStart(composer3, -954363344, "CC(remember):Effects.kt#9igjgp");
                                Object rememberedValue = composer3.rememberedValue();
                                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                    Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer3));
                                    composer3.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                                    rememberedValue = compositionScopedCoroutineScopeCanceller;
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerStart(composer3, -702885223, "CC(remember):TabRow.kt#9igjgp");
                                boolean changed = composer3.changed(ScrollState.this) | composer3.changed(coroutineScope);
                                ScrollState scrollState2 = ScrollState.this;
                                Object rememberedValue2 = composer3.rememberedValue();
                                if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue2 = new ScrollableTabData(scrollState2, coroutineScope);
                                    composer3.updateRememberedValue(rememberedValue2);
                                }
                                final ScrollableTabData scrollableTabData = (ScrollableTabData) rememberedValue2;
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                Modifier clipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), ScrollState.this, false, null, false, 14, null)));
                                ComposerKt.sourceInformationMarkerStart(composer3, -702869258, "CC(remember):TabRow.kt#9igjgp");
                                boolean changed2 = composer3.changed(f4222) | composer3.changed(function22) | composer3.changed(function24222) | composer3.changed(function33222) | composer3.changedInstance(scrollableTabData) | composer3.changed(i);
                                final float f5 = f4222;
                                final Function2<? super Composer, ? super Integer, Unit> function25 = function22;
                                final Function2<? super Composer, ? super Integer, Unit> function26 = function24222;
                                final int i11222 = i;
                                final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function34 = function33222;
                                Object rememberedValue3 = composer3.rememberedValue();
                                if (changed2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue3 = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                            return m2762invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                                        }

                                        /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                                        public final MeasureResult m2762invoke0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, final long j9) {
                                            float f6;
                                            f6 = TabRowKt.ScrollableTabRowMinimumTabWidth;
                                            int i12 = subcomposeMeasureScope.mo453roundToPx0680j_4(f6);
                                            final int i13 = subcomposeMeasureScope.mo453roundToPx0680j_4(f5);
                                            List<Measurable> subcompose = subcomposeMeasureScope.subcompose(TabSlots.Tabs, function25);
                                            int i14 = 0;
                                            int size = subcompose.size();
                                            for (int i15 = 0; i15 < size; i15++) {
                                                i14 = Integer.valueOf(Math.max(i14.intValue(), subcompose.get(i15).maxIntrinsicHeight(Integer.MAX_VALUE)));
                                            }
                                            final int intValue = i14.intValue();
                                            long m7332copyZbe2FdA$default = Constraints.m7332copyZbe2FdA$default(j9, i12, 0, intValue, intValue, 2, null);
                                            final List<Placeable> arrayList = new ArrayList();
                                            final List<Dp> arrayList2 = new ArrayList();
                                            int size2 = subcompose.size();
                                            for (int i16 = 0; i16 < size2; i16++) {
                                                Measurable measurable = subcompose.get(i16);
                                                Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(m7332copyZbe2FdA$default);
                                                float m7399constructorimpl = Dp.m7399constructorimpl(subcomposeMeasureScope.mo456toDpu2uoSUM(Math.min(measurable.maxIntrinsicWidth(mo6055measureBRTryo0.getHeight()), mo6055measureBRTryo0.getWidth())) - Dp.m7399constructorimpl(TabKt.getHorizontalTextPadding() * 2));
                                                arrayList.add(mo6055measureBRTryo0);
                                                arrayList2.add(Dp.m7397boximpl(m7399constructorimpl));
                                            }
                                            Integer valueOf = Integer.valueOf(i13 * 2);
                                            int size3 = arrayList.size();
                                            for (int i17 = 0; i17 < size3; i17++) {
                                                valueOf = Integer.valueOf(valueOf.intValue() + ((Placeable) arrayList.get(i17)).getWidth());
                                            }
                                            final int intValue2 = valueOf.intValue();
                                            final Function2<? super Composer, ? super Integer, Unit> function27 = function26;
                                            final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                            final int i18 = i11222;
                                            final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function35 = function34;
                                            return MeasureScope.CC.layout$default(subcomposeMeasureScope, intValue2, intValue, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1.2
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                /* JADX WARN: Multi-variable type inference failed */
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                    invoke2(placementScope);
                                                    return Unit.INSTANCE;
                                                }

                                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(Placeable.PlacementScope placementScope) {
                                                    final List<TabPosition> arrayList3 = new ArrayList();
                                                    int i19 = i13;
                                                    List<Placeable> list = arrayList;
                                                    SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                                                    List<Dp> list2 = arrayList2;
                                                    int size4 = list.size();
                                                    int i20 = i19;
                                                    for (int i21 = 0; i21 < size4; i21++) {
                                                        Placeable placeable = list.get(i21);
                                                        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i20, 0, 0.0f, 4, null);
                                                        arrayList3.add(new TabPosition(subcomposeMeasureScope2.mo456toDpu2uoSUM(i20), subcomposeMeasureScope2.mo456toDpu2uoSUM(placeable.getWidth()), list2.get(i21).m7413unboximpl(), null));
                                                        i20 += placeable.getWidth();
                                                    }
                                                    List<Measurable> subcompose2 = subcomposeMeasureScope.subcompose(TabSlots.Divider, function27);
                                                    long j10 = j9;
                                                    int i22 = intValue2;
                                                    int i23 = intValue;
                                                    int size5 = subcompose2.size();
                                                    for (int i24 = 0; i24 < size5; i24++) {
                                                        Placeable mo6055measureBRTryo02 = subcompose2.get(i24).mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(j10, i22, i22, 0, 0, 8, null));
                                                        Placeable.PlacementScope.placeRelative$default(placementScope, mo6055measureBRTryo02, 0, i23 - mo6055measureBRTryo02.getHeight(), 0.0f, 4, null);
                                                    }
                                                    SubcomposeMeasureScope subcomposeMeasureScope3 = subcomposeMeasureScope;
                                                    TabSlots tabSlots = TabSlots.Indicator;
                                                    final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function36 = function35;
                                                    List<Measurable> subcompose3 = subcomposeMeasureScope3.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(1734082948, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt.ScrollableTabRowWithSubcomposeImpl.1.1.1.2.3
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        /* JADX WARN: Multi-variable type inference failed */
                                                        {
                                                            super(2);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                                            invoke(composer4, num.intValue());
                                                            return Unit.INSTANCE;
                                                        }

                                                        public final void invoke(Composer composer4, int i25) {
                                                            ComposerKt.sourceInformation(composer4, "C1100@47792L23:TabRow.kt#uh7d8r");
                                                            if ((i25 & 3) == 2 && composer4.getSkipping()) {
                                                                composer4.skipToGroupEnd();
                                                                return;
                                                            }
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(1734082948, i25, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:1100)");
                                                            }
                                                            function36.invoke(arrayList3, composer4, 0);
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventEnd();
                                                            }
                                                        }
                                                    }));
                                                    int i25 = intValue2;
                                                    int i26 = intValue;
                                                    int size6 = subcompose3.size();
                                                    for (int i27 = 0; i27 < size6; i27++) {
                                                        Placeable.PlacementScope.placeRelative$default(placementScope, subcompose3.get(i27).mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(i25, i26)), 0, 0, 0.0f, 4, null);
                                                    }
                                                    scrollableTabData2.onLaidOut(subcomposeMeasureScope, i13, arrayList3, i18);
                                                }
                                            }, 4, null);
                                        }
                                    };
                                    composer3.updateRememberedValue(rememberedValue3);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                SubcomposeLayoutKt.SubcomposeLayout(clipToBounds, (Function2) rememberedValue3, composer3, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, startRestartGroup, 54);
                    int i10222 = ((i4 >> 6) & 14) | 12582912;
                    int i11222 = i4 >> 3;
                    composer2 = startRestartGroup;
                    SurfaceKt.m2699SurfaceT9BRK9s(modifier4222, null, j8222, j7222, 0.0f, 0.0f, null, rememberComposableLambda222, composer2, i10222 | (i11222 & 896) | (i11222 & 7168), 114);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    f3 = f4222;
                    function23 = function24222;
                    modifier3 = modifier4222;
                    j5 = j8222;
                    j6 = j7222;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                f2 = f;
                i7 = i3 & 64;
                if (i7 == 0) {
                }
                if ((i3 & 128) == 0) {
                }
                if ((i3 & 256) != 0) {
                }
                if ((i4 & 38347923) == 38347922) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i5 != 0) {
                }
                if ((i3 & 8) != 0) {
                }
                if ((i3 & 16) != 0) {
                }
                if (i6 != 0) {
                }
                if (i7 != 0) {
                }
                m2144getLambda7$material3_release = function2;
                long j72222 = j4;
                final float f42222 = f2;
                Modifier modifier42222 = modifier2;
                long j82222 = j3;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                final Function2<? super Composer, ? super Integer, Unit> function242222 = m2144getLambda7$material3_release;
                final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function332222 = function32;
                ComposableLambda rememberComposableLambda2222 = ComposableLambdaKt.rememberComposableLambda(-1572959552, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i102222) {
                        ComposerKt.sourceInformation(composer3, "C1022@44415L24,1024@44484L147,1033@44882L3376,1027@44640L3618:TabRow.kt#uh7d8r");
                        if ((i102222 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1572959552, i102222, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous> (TabRow.kt:1022)");
                            }
                            ComposerKt.sourceInformationMarkerStart(composer3, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                            ComposerKt.sourceInformationMarkerStart(composer3, -954363344, "CC(remember):Effects.kt#9igjgp");
                            Object rememberedValue = composer3.rememberedValue();
                            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer3));
                                composer3.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                                rememberedValue = compositionScopedCoroutineScopeCanceller;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerStart(composer3, -702885223, "CC(remember):TabRow.kt#9igjgp");
                            boolean changed = composer3.changed(ScrollState.this) | composer3.changed(coroutineScope);
                            ScrollState scrollState2 = ScrollState.this;
                            Object rememberedValue2 = composer3.rememberedValue();
                            if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                rememberedValue2 = new ScrollableTabData(scrollState2, coroutineScope);
                                composer3.updateRememberedValue(rememberedValue2);
                            }
                            final ScrollableTabData scrollableTabData = (ScrollableTabData) rememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            Modifier clipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), ScrollState.this, false, null, false, 14, null)));
                            ComposerKt.sourceInformationMarkerStart(composer3, -702869258, "CC(remember):TabRow.kt#9igjgp");
                            boolean changed2 = composer3.changed(f42222) | composer3.changed(function22) | composer3.changed(function242222) | composer3.changed(function332222) | composer3.changedInstance(scrollableTabData) | composer3.changed(i);
                            final float f5 = f42222;
                            final Function2<? super Composer, ? super Integer, Unit> function25 = function22;
                            final Function2<? super Composer, ? super Integer, Unit> function26 = function242222;
                            final int i112222 = i;
                            final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function34 = function332222;
                            Object rememberedValue3 = composer3.rememberedValue();
                            if (changed2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                rememberedValue3 = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                        return m2762invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                                    }

                                    /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                                    public final MeasureResult m2762invoke0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, final long j9) {
                                        float f6;
                                        f6 = TabRowKt.ScrollableTabRowMinimumTabWidth;
                                        int i12 = subcomposeMeasureScope.mo453roundToPx0680j_4(f6);
                                        final int i13 = subcomposeMeasureScope.mo453roundToPx0680j_4(f5);
                                        List<Measurable> subcompose = subcomposeMeasureScope.subcompose(TabSlots.Tabs, function25);
                                        int i14 = 0;
                                        int size = subcompose.size();
                                        for (int i15 = 0; i15 < size; i15++) {
                                            i14 = Integer.valueOf(Math.max(i14.intValue(), subcompose.get(i15).maxIntrinsicHeight(Integer.MAX_VALUE)));
                                        }
                                        final int intValue = i14.intValue();
                                        long m7332copyZbe2FdA$default = Constraints.m7332copyZbe2FdA$default(j9, i12, 0, intValue, intValue, 2, null);
                                        final List<Placeable> arrayList = new ArrayList();
                                        final List<Dp> arrayList2 = new ArrayList();
                                        int size2 = subcompose.size();
                                        for (int i16 = 0; i16 < size2; i16++) {
                                            Measurable measurable = subcompose.get(i16);
                                            Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(m7332copyZbe2FdA$default);
                                            float m7399constructorimpl = Dp.m7399constructorimpl(subcomposeMeasureScope.mo456toDpu2uoSUM(Math.min(measurable.maxIntrinsicWidth(mo6055measureBRTryo0.getHeight()), mo6055measureBRTryo0.getWidth())) - Dp.m7399constructorimpl(TabKt.getHorizontalTextPadding() * 2));
                                            arrayList.add(mo6055measureBRTryo0);
                                            arrayList2.add(Dp.m7397boximpl(m7399constructorimpl));
                                        }
                                        Integer valueOf = Integer.valueOf(i13 * 2);
                                        int size3 = arrayList.size();
                                        for (int i17 = 0; i17 < size3; i17++) {
                                            valueOf = Integer.valueOf(valueOf.intValue() + ((Placeable) arrayList.get(i17)).getWidth());
                                        }
                                        final int intValue2 = valueOf.intValue();
                                        final Function2<? super Composer, ? super Integer, Unit> function27 = function26;
                                        final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                        final int i18 = i112222;
                                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function35 = function34;
                                        return MeasureScope.CC.layout$default(subcomposeMeasureScope, intValue2, intValue, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1.2
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                invoke2(placementScope);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Placeable.PlacementScope placementScope) {
                                                final List<TabPosition> arrayList3 = new ArrayList();
                                                int i19 = i13;
                                                List<Placeable> list = arrayList;
                                                SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                                                List<Dp> list2 = arrayList2;
                                                int size4 = list.size();
                                                int i20 = i19;
                                                for (int i21 = 0; i21 < size4; i21++) {
                                                    Placeable placeable = list.get(i21);
                                                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i20, 0, 0.0f, 4, null);
                                                    arrayList3.add(new TabPosition(subcomposeMeasureScope2.mo456toDpu2uoSUM(i20), subcomposeMeasureScope2.mo456toDpu2uoSUM(placeable.getWidth()), list2.get(i21).m7413unboximpl(), null));
                                                    i20 += placeable.getWidth();
                                                }
                                                List<Measurable> subcompose2 = subcomposeMeasureScope.subcompose(TabSlots.Divider, function27);
                                                long j10 = j9;
                                                int i22 = intValue2;
                                                int i23 = intValue;
                                                int size5 = subcompose2.size();
                                                for (int i24 = 0; i24 < size5; i24++) {
                                                    Placeable mo6055measureBRTryo02 = subcompose2.get(i24).mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(j10, i22, i22, 0, 0, 8, null));
                                                    Placeable.PlacementScope.placeRelative$default(placementScope, mo6055measureBRTryo02, 0, i23 - mo6055measureBRTryo02.getHeight(), 0.0f, 4, null);
                                                }
                                                SubcomposeMeasureScope subcomposeMeasureScope3 = subcomposeMeasureScope;
                                                TabSlots tabSlots = TabSlots.Indicator;
                                                final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function36 = function35;
                                                List<Measurable> subcompose3 = subcomposeMeasureScope3.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(1734082948, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt.ScrollableTabRowWithSubcomposeImpl.1.1.1.2.3
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    /* JADX WARN: Multi-variable type inference failed */
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                                        invoke(composer4, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(Composer composer4, int i25) {
                                                        ComposerKt.sourceInformation(composer4, "C1100@47792L23:TabRow.kt#uh7d8r");
                                                        if ((i25 & 3) == 2 && composer4.getSkipping()) {
                                                            composer4.skipToGroupEnd();
                                                            return;
                                                        }
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(1734082948, i25, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:1100)");
                                                        }
                                                        function36.invoke(arrayList3, composer4, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                        }
                                                    }
                                                }));
                                                int i25 = intValue2;
                                                int i26 = intValue;
                                                int size6 = subcompose3.size();
                                                for (int i27 = 0; i27 < size6; i27++) {
                                                    Placeable.PlacementScope.placeRelative$default(placementScope, subcompose3.get(i27).mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(i25, i26)), 0, 0, 0.0f, 4, null);
                                                }
                                                scrollableTabData2.onLaidOut(subcomposeMeasureScope, i13, arrayList3, i18);
                                            }
                                        }, 4, null);
                                    }
                                };
                                composer3.updateRememberedValue(rememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            SubcomposeLayoutKt.SubcomposeLayout(clipToBounds, (Function2) rememberedValue3, composer3, 0, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, startRestartGroup, 54);
                int i102222 = ((i4 >> 6) & 14) | 12582912;
                int i112222 = i4 >> 3;
                composer2 = startRestartGroup;
                SurfaceKt.m2699SurfaceT9BRK9s(modifier42222, null, j82222, j72222, 0.0f, 0.0f, null, rememberComposableLambda2222, composer2, i102222 | (i112222 & 896) | (i112222 & 7168), 114);
                if (ComposerKt.isTraceInProgress()) {
                }
                f3 = f42222;
                function23 = function242222;
                modifier3 = modifier42222;
                j5 = j82222;
                j6 = j72222;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            modifier2 = modifier;
            if ((i2 & 3072) == 0) {
            }
            if ((i2 & 24576) == 0) {
            }
            i6 = i3 & 32;
            if (i6 != 0) {
            }
            f2 = f;
            i7 = i3 & 64;
            if (i7 == 0) {
            }
            if ((i3 & 128) == 0) {
            }
            if ((i3 & 256) != 0) {
            }
            if ((i4 & 38347923) == 38347922) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i5 != 0) {
            }
            if ((i3 & 8) != 0) {
            }
            if ((i3 & 16) != 0) {
            }
            if (i6 != 0) {
            }
            if (i7 != 0) {
            }
            m2144getLambda7$material3_release = function2;
            long j722222 = j4;
            final float f422222 = f2;
            Modifier modifier422222 = modifier2;
            long j822222 = j3;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            final Function2<? super Composer, ? super Integer, Unit> function2422222 = m2144getLambda7$material3_release;
            final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3322222 = function32;
            ComposableLambda rememberComposableLambda22222 = ComposableLambdaKt.rememberComposableLambda(-1572959552, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i1022222) {
                    ComposerKt.sourceInformation(composer3, "C1022@44415L24,1024@44484L147,1033@44882L3376,1027@44640L3618:TabRow.kt#uh7d8r");
                    if ((i1022222 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1572959552, i1022222, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous> (TabRow.kt:1022)");
                        }
                        ComposerKt.sourceInformationMarkerStart(composer3, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(composer3, -954363344, "CC(remember):Effects.kt#9igjgp");
                        Object rememberedValue = composer3.rememberedValue();
                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                            Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer3));
                            composer3.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                            rememberedValue = compositionScopedCoroutineScopeCanceller;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerStart(composer3, -702885223, "CC(remember):TabRow.kt#9igjgp");
                        boolean changed = composer3.changed(ScrollState.this) | composer3.changed(coroutineScope);
                        ScrollState scrollState2 = ScrollState.this;
                        Object rememberedValue2 = composer3.rememberedValue();
                        if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue2 = new ScrollableTabData(scrollState2, coroutineScope);
                            composer3.updateRememberedValue(rememberedValue2);
                        }
                        final ScrollableTabData scrollableTabData = (ScrollableTabData) rememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        Modifier clipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), ScrollState.this, false, null, false, 14, null)));
                        ComposerKt.sourceInformationMarkerStart(composer3, -702869258, "CC(remember):TabRow.kt#9igjgp");
                        boolean changed2 = composer3.changed(f422222) | composer3.changed(function22) | composer3.changed(function2422222) | composer3.changed(function3322222) | composer3.changedInstance(scrollableTabData) | composer3.changed(i);
                        final float f5 = f422222;
                        final Function2<? super Composer, ? super Integer, Unit> function25 = function22;
                        final Function2<? super Composer, ? super Integer, Unit> function26 = function2422222;
                        final int i1122222 = i;
                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function34 = function3322222;
                        Object rememberedValue3 = composer3.rememberedValue();
                        if (changed2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue3 = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                    return m2762invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                                }

                                /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                                public final MeasureResult m2762invoke0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, final long j9) {
                                    float f6;
                                    f6 = TabRowKt.ScrollableTabRowMinimumTabWidth;
                                    int i12 = subcomposeMeasureScope.mo453roundToPx0680j_4(f6);
                                    final int i13 = subcomposeMeasureScope.mo453roundToPx0680j_4(f5);
                                    List<Measurable> subcompose = subcomposeMeasureScope.subcompose(TabSlots.Tabs, function25);
                                    int i14 = 0;
                                    int size = subcompose.size();
                                    for (int i15 = 0; i15 < size; i15++) {
                                        i14 = Integer.valueOf(Math.max(i14.intValue(), subcompose.get(i15).maxIntrinsicHeight(Integer.MAX_VALUE)));
                                    }
                                    final int intValue = i14.intValue();
                                    long m7332copyZbe2FdA$default = Constraints.m7332copyZbe2FdA$default(j9, i12, 0, intValue, intValue, 2, null);
                                    final List<Placeable> arrayList = new ArrayList();
                                    final List<Dp> arrayList2 = new ArrayList();
                                    int size2 = subcompose.size();
                                    for (int i16 = 0; i16 < size2; i16++) {
                                        Measurable measurable = subcompose.get(i16);
                                        Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(m7332copyZbe2FdA$default);
                                        float m7399constructorimpl = Dp.m7399constructorimpl(subcomposeMeasureScope.mo456toDpu2uoSUM(Math.min(measurable.maxIntrinsicWidth(mo6055measureBRTryo0.getHeight()), mo6055measureBRTryo0.getWidth())) - Dp.m7399constructorimpl(TabKt.getHorizontalTextPadding() * 2));
                                        arrayList.add(mo6055measureBRTryo0);
                                        arrayList2.add(Dp.m7397boximpl(m7399constructorimpl));
                                    }
                                    Integer valueOf = Integer.valueOf(i13 * 2);
                                    int size3 = arrayList.size();
                                    for (int i17 = 0; i17 < size3; i17++) {
                                        valueOf = Integer.valueOf(valueOf.intValue() + ((Placeable) arrayList.get(i17)).getWidth());
                                    }
                                    final int intValue2 = valueOf.intValue();
                                    final Function2<? super Composer, ? super Integer, Unit> function27 = function26;
                                    final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                    final int i18 = i1122222;
                                    final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function35 = function34;
                                    return MeasureScope.CC.layout$default(subcomposeMeasureScope, intValue2, intValue, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1.2
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                            invoke2(placementScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Placeable.PlacementScope placementScope) {
                                            final List<TabPosition> arrayList3 = new ArrayList();
                                            int i19 = i13;
                                            List<Placeable> list = arrayList;
                                            SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                                            List<Dp> list2 = arrayList2;
                                            int size4 = list.size();
                                            int i20 = i19;
                                            for (int i21 = 0; i21 < size4; i21++) {
                                                Placeable placeable = list.get(i21);
                                                Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i20, 0, 0.0f, 4, null);
                                                arrayList3.add(new TabPosition(subcomposeMeasureScope2.mo456toDpu2uoSUM(i20), subcomposeMeasureScope2.mo456toDpu2uoSUM(placeable.getWidth()), list2.get(i21).m7413unboximpl(), null));
                                                i20 += placeable.getWidth();
                                            }
                                            List<Measurable> subcompose2 = subcomposeMeasureScope.subcompose(TabSlots.Divider, function27);
                                            long j10 = j9;
                                            int i22 = intValue2;
                                            int i23 = intValue;
                                            int size5 = subcompose2.size();
                                            for (int i24 = 0; i24 < size5; i24++) {
                                                Placeable mo6055measureBRTryo02 = subcompose2.get(i24).mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(j10, i22, i22, 0, 0, 8, null));
                                                Placeable.PlacementScope.placeRelative$default(placementScope, mo6055measureBRTryo02, 0, i23 - mo6055measureBRTryo02.getHeight(), 0.0f, 4, null);
                                            }
                                            SubcomposeMeasureScope subcomposeMeasureScope3 = subcomposeMeasureScope;
                                            TabSlots tabSlots = TabSlots.Indicator;
                                            final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function36 = function35;
                                            List<Measurable> subcompose3 = subcomposeMeasureScope3.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(1734082948, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt.ScrollableTabRowWithSubcomposeImpl.1.1.1.2.3
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                /* JADX WARN: Multi-variable type inference failed */
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                                    invoke(composer4, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer4, int i25) {
                                                    ComposerKt.sourceInformation(composer4, "C1100@47792L23:TabRow.kt#uh7d8r");
                                                    if ((i25 & 3) == 2 && composer4.getSkipping()) {
                                                        composer4.skipToGroupEnd();
                                                        return;
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(1734082948, i25, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:1100)");
                                                    }
                                                    function36.invoke(arrayList3, composer4, 0);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                    }
                                                }
                                            }));
                                            int i25 = intValue2;
                                            int i26 = intValue;
                                            int size6 = subcompose3.size();
                                            for (int i27 = 0; i27 < size6; i27++) {
                                                Placeable.PlacementScope.placeRelative$default(placementScope, subcompose3.get(i27).mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(i25, i26)), 0, 0, 0.0f, 4, null);
                                            }
                                            scrollableTabData2.onLaidOut(subcomposeMeasureScope, i13, arrayList3, i18);
                                        }
                                    }, 4, null);
                                }
                            };
                            composer3.updateRememberedValue(rememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        SubcomposeLayoutKt.SubcomposeLayout(clipToBounds, (Function2) rememberedValue3, composer3, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, startRestartGroup, 54);
            int i1022222 = ((i4 >> 6) & 14) | 12582912;
            int i1122222 = i4 >> 3;
            composer2 = startRestartGroup;
            SurfaceKt.m2699SurfaceT9BRK9s(modifier422222, null, j822222, j722222, 0.0f, 0.0f, null, rememberComposableLambda22222, composer2, i1022222 | (i1122222 & 896) | (i1122222 & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
            }
            f3 = f422222;
            function23 = function2422222;
            modifier3 = modifier422222;
            j5 = j822222;
            j6 = j722222;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        function32 = function3;
        i5 = i3 & 4;
        if (i5 == 0) {
        }
        modifier2 = modifier;
        if ((i2 & 3072) == 0) {
        }
        if ((i2 & 24576) == 0) {
        }
        i6 = i3 & 32;
        if (i6 != 0) {
        }
        f2 = f;
        i7 = i3 & 64;
        if (i7 == 0) {
        }
        if ((i3 & 128) == 0) {
        }
        if ((i3 & 256) != 0) {
        }
        if ((i4 & 38347923) == 38347922) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i5 != 0) {
        }
        if ((i3 & 8) != 0) {
        }
        if ((i3 & 16) != 0) {
        }
        if (i6 != 0) {
        }
        if (i7 != 0) {
        }
        m2144getLambda7$material3_release = function2;
        long j7222222 = j4;
        final float f4222222 = f2;
        Modifier modifier4222222 = modifier2;
        long j8222222 = j3;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        final Function2<? super Composer, ? super Integer, Unit> function24222222 = m2144getLambda7$material3_release;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function33222222 = function32;
        ComposableLambda rememberComposableLambda222222 = ComposableLambdaKt.rememberComposableLambda(-1572959552, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                invoke(composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer3, int i10222222) {
                ComposerKt.sourceInformation(composer3, "C1022@44415L24,1024@44484L147,1033@44882L3376,1027@44640L3618:TabRow.kt#uh7d8r");
                if ((i10222222 & 3) != 2 || !composer3.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1572959552, i10222222, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous> (TabRow.kt:1022)");
                    }
                    ComposerKt.sourceInformationMarkerStart(composer3, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composer3, -954363344, "CC(remember):Effects.kt#9igjgp");
                    Object rememberedValue = composer3.rememberedValue();
                    if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer3));
                        composer3.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                        rememberedValue = compositionScopedCoroutineScopeCanceller;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerStart(composer3, -702885223, "CC(remember):TabRow.kt#9igjgp");
                    boolean changed = composer3.changed(ScrollState.this) | composer3.changed(coroutineScope);
                    ScrollState scrollState2 = ScrollState.this;
                    Object rememberedValue2 = composer3.rememberedValue();
                    if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        rememberedValue2 = new ScrollableTabData(scrollState2, coroutineScope);
                        composer3.updateRememberedValue(rememberedValue2);
                    }
                    final ScrollableTabData scrollableTabData = (ScrollableTabData) rememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    Modifier clipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), ScrollState.this, false, null, false, 14, null)));
                    ComposerKt.sourceInformationMarkerStart(composer3, -702869258, "CC(remember):TabRow.kt#9igjgp");
                    boolean changed2 = composer3.changed(f4222222) | composer3.changed(function22) | composer3.changed(function24222222) | composer3.changed(function33222222) | composer3.changedInstance(scrollableTabData) | composer3.changed(i);
                    final float f5 = f4222222;
                    final Function2<? super Composer, ? super Integer, Unit> function25 = function22;
                    final Function2<? super Composer, ? super Integer, Unit> function26 = function24222222;
                    final int i11222222 = i;
                    final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function34 = function33222222;
                    Object rememberedValue3 = composer3.rememberedValue();
                    if (changed2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        rememberedValue3 = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                return m2762invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                            }

                            /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                            public final MeasureResult m2762invoke0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, final long j9) {
                                float f6;
                                f6 = TabRowKt.ScrollableTabRowMinimumTabWidth;
                                int i12 = subcomposeMeasureScope.mo453roundToPx0680j_4(f6);
                                final int i13 = subcomposeMeasureScope.mo453roundToPx0680j_4(f5);
                                List<Measurable> subcompose = subcomposeMeasureScope.subcompose(TabSlots.Tabs, function25);
                                int i14 = 0;
                                int size = subcompose.size();
                                for (int i15 = 0; i15 < size; i15++) {
                                    i14 = Integer.valueOf(Math.max(i14.intValue(), subcompose.get(i15).maxIntrinsicHeight(Integer.MAX_VALUE)));
                                }
                                final int intValue = i14.intValue();
                                long m7332copyZbe2FdA$default = Constraints.m7332copyZbe2FdA$default(j9, i12, 0, intValue, intValue, 2, null);
                                final List<Placeable> arrayList = new ArrayList();
                                final List<Dp> arrayList2 = new ArrayList();
                                int size2 = subcompose.size();
                                for (int i16 = 0; i16 < size2; i16++) {
                                    Measurable measurable = subcompose.get(i16);
                                    Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(m7332copyZbe2FdA$default);
                                    float m7399constructorimpl = Dp.m7399constructorimpl(subcomposeMeasureScope.mo456toDpu2uoSUM(Math.min(measurable.maxIntrinsicWidth(mo6055measureBRTryo0.getHeight()), mo6055measureBRTryo0.getWidth())) - Dp.m7399constructorimpl(TabKt.getHorizontalTextPadding() * 2));
                                    arrayList.add(mo6055measureBRTryo0);
                                    arrayList2.add(Dp.m7397boximpl(m7399constructorimpl));
                                }
                                Integer valueOf = Integer.valueOf(i13 * 2);
                                int size3 = arrayList.size();
                                for (int i17 = 0; i17 < size3; i17++) {
                                    valueOf = Integer.valueOf(valueOf.intValue() + ((Placeable) arrayList.get(i17)).getWidth());
                                }
                                final int intValue2 = valueOf.intValue();
                                final Function2<? super Composer, ? super Integer, Unit> function27 = function26;
                                final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                final int i18 = i11222222;
                                final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function35 = function34;
                                return MeasureScope.CC.layout$default(subcomposeMeasureScope, intValue2, intValue, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowWithSubcomposeImpl$1$1$1.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                        invoke2(placementScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Placeable.PlacementScope placementScope) {
                                        final List<TabPosition> arrayList3 = new ArrayList();
                                        int i19 = i13;
                                        List<Placeable> list = arrayList;
                                        SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                                        List<Dp> list2 = arrayList2;
                                        int size4 = list.size();
                                        int i20 = i19;
                                        for (int i21 = 0; i21 < size4; i21++) {
                                            Placeable placeable = list.get(i21);
                                            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i20, 0, 0.0f, 4, null);
                                            arrayList3.add(new TabPosition(subcomposeMeasureScope2.mo456toDpu2uoSUM(i20), subcomposeMeasureScope2.mo456toDpu2uoSUM(placeable.getWidth()), list2.get(i21).m7413unboximpl(), null));
                                            i20 += placeable.getWidth();
                                        }
                                        List<Measurable> subcompose2 = subcomposeMeasureScope.subcompose(TabSlots.Divider, function27);
                                        long j10 = j9;
                                        int i22 = intValue2;
                                        int i23 = intValue;
                                        int size5 = subcompose2.size();
                                        for (int i24 = 0; i24 < size5; i24++) {
                                            Placeable mo6055measureBRTryo02 = subcompose2.get(i24).mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(j10, i22, i22, 0, 0, 8, null));
                                            Placeable.PlacementScope.placeRelative$default(placementScope, mo6055measureBRTryo02, 0, i23 - mo6055measureBRTryo02.getHeight(), 0.0f, 4, null);
                                        }
                                        SubcomposeMeasureScope subcomposeMeasureScope3 = subcomposeMeasureScope;
                                        TabSlots tabSlots = TabSlots.Indicator;
                                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function36 = function35;
                                        List<Measurable> subcompose3 = subcomposeMeasureScope3.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(1734082948, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt.ScrollableTabRowWithSubcomposeImpl.1.1.1.2.3
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                                invoke(composer4, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer4, int i25) {
                                                ComposerKt.sourceInformation(composer4, "C1100@47792L23:TabRow.kt#uh7d8r");
                                                if ((i25 & 3) == 2 && composer4.getSkipping()) {
                                                    composer4.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1734082948, i25, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:1100)");
                                                }
                                                function36.invoke(arrayList3, composer4, 0);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }));
                                        int i25 = intValue2;
                                        int i26 = intValue;
                                        int size6 = subcompose3.size();
                                        for (int i27 = 0; i27 < size6; i27++) {
                                            Placeable.PlacementScope.placeRelative$default(placementScope, subcompose3.get(i27).mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(i25, i26)), 0, 0, 0.0f, 4, null);
                                        }
                                        scrollableTabData2.onLaidOut(subcomposeMeasureScope, i13, arrayList3, i18);
                                    }
                                }, 4, null);
                            }
                        };
                        composer3.updateRememberedValue(rememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    SubcomposeLayoutKt.SubcomposeLayout(clipToBounds, (Function2) rememberedValue3, composer3, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer3.skipToGroupEnd();
            }
        }, startRestartGroup, 54);
        int i10222222 = ((i4 >> 6) & 14) | 12582912;
        int i11222222 = i4 >> 3;
        composer2 = startRestartGroup;
        SurfaceKt.m2699SurfaceT9BRK9s(modifier4222222, null, j8222222, j7222222, 0.0f, 0.0f, null, rememberComposableLambda222222, composer2, i10222222 | (i11222222 & 896) | (i11222222 & 7168), 114);
        if (ComposerKt.isTraceInProgress()) {
        }
        f3 = f4222222;
        function23 = function24222222;
        modifier3 = modifier4222222;
        j5 = j8222222;
        j6 = j7222222;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }
}
