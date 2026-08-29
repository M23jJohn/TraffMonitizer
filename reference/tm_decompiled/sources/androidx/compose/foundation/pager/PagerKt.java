package androidx.compose.foundation.pager;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.OverscrollKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.pager.PageSize;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Pager.kt */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001aë\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132%\b\u0002\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00162\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 21\u0010!\u001a-\u0012\u0004\u0012\u00020#\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010\"¢\u0006\u0002\b%¢\u0006\u0002\b&H\u0007¢\u0006\u0004\b'\u0010(\u001aß\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132%\b\u0002\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00162\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e21\u0010!\u001a-\u0012\u0004\u0012\u00020#\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010\"¢\u0006\u0002\b%¢\u0006\u0002\b&H\u0007¢\u0006\u0004\b)\u0010*\u001aë\u0001\u0010+\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010,\u001a\u00020-2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132%\b\u0002\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00162\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 21\u0010!\u001a-\u0012\u0004\u0012\u00020#\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010\"¢\u0006\u0002\b%¢\u0006\u0002\b&H\u0007¢\u0006\u0004\b.\u0010/\u001aß\u0001\u0010+\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010,\u001a\u00020-2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132%\b\u0002\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00162\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e21\u0010!\u001a-\u0012\u0004\u0012\u00020#\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010\"¢\u0006\u0002\b%¢\u0006\u0002\b&H\u0007¢\u0006\u0004\b0\u00101\u001aL\u00102\u001a\u00020\u000b*\u00020\u001e2\u0006\u00103\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u000b2\u0006\u00104\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000b2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u000bH\u0000\u001a,\u0010;\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010<\u001a\u00020\u00132\u0006\u0010=\u001a\u00020>2\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u001a\u0017\u0010?\u001a\u00020\u00012\f\u0010@\u001a\b\u0012\u0004\u0012\u00020B0AH\u0082\b¨\u0006C"}, d2 = {"HorizontalPager", "", "state", "Landroidx/compose/foundation/pager/PagerState;", "modifier", "Landroidx/compose/ui/Modifier;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "pageSize", "Landroidx/compose/foundation/pager/PageSize;", "beyondViewportPageCount", "", "pageSpacing", "Landroidx/compose/ui/unit/Dp;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "flingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "userScrollEnabled", "", "reverseLayout", "key", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "index", "", "pageNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "snapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "pageContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/pager/PagerScope;", "page", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "HorizontalPager--8jOkeI", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/pager/PageSize;IFLandroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/TargetedFlingBehavior;ZZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Landroidx/compose/foundation/OverscrollEffect;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "HorizontalPager-oI3XNZo", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/pager/PageSize;IFLandroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/TargetedFlingBehavior;ZZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "VerticalPager", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "VerticalPager--8jOkeI", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/pager/PageSize;IFLandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/TargetedFlingBehavior;ZZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Landroidx/compose/foundation/OverscrollEffect;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "VerticalPager-oI3XNZo", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/pager/PageSize;IFLandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/TargetedFlingBehavior;ZZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "currentPageOffset", "layoutSize", "spaceBetweenPages", "beforeContentPadding", "afterContentPadding", "currentPage", "currentPageOffsetFraction", "", "pageCount", "pagerSemantics", "isVertical", "scope", "Lkotlinx/coroutines/CoroutineScope;", "debugLog", "generateMsg", "Lkotlin/Function0;", "", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PagerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HorizontalPager__8jOkeI$lambda$0(PagerState pagerState, Modifier modifier, PaddingValues paddingValues, PageSize pageSize, int i, float f, Alignment.Vertical vertical, TargetedFlingBehavior targetedFlingBehavior, boolean z, boolean z2, Function1 function1, NestedScrollConnection nestedScrollConnection, SnapPosition snapPosition, OverscrollEffect overscrollEffect, Function4 function4, int i2, int i3, int i4, Composer composer, int i5) {
        m1182HorizontalPager8jOkeI(pagerState, modifier, paddingValues, pageSize, i, f, vertical, targetedFlingBehavior, z, z2, function1, nestedScrollConnection, snapPosition, overscrollEffect, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HorizontalPager_oI3XNZo$lambda$1(PagerState pagerState, Modifier modifier, PaddingValues paddingValues, PageSize pageSize, int i, float f, Alignment.Vertical vertical, TargetedFlingBehavior targetedFlingBehavior, boolean z, boolean z2, Function1 function1, NestedScrollConnection nestedScrollConnection, SnapPosition snapPosition, Function4 function4, int i2, int i3, int i4, Composer composer, int i5) {
        m1183HorizontalPageroI3XNZo(pagerState, modifier, paddingValues, pageSize, i, f, vertical, targetedFlingBehavior, z, z2, function1, nestedScrollConnection, snapPosition, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit VerticalPager__8jOkeI$lambda$2(PagerState pagerState, Modifier modifier, PaddingValues paddingValues, PageSize pageSize, int i, float f, Alignment.Horizontal horizontal, TargetedFlingBehavior targetedFlingBehavior, boolean z, boolean z2, Function1 function1, NestedScrollConnection nestedScrollConnection, SnapPosition snapPosition, OverscrollEffect overscrollEffect, Function4 function4, int i2, int i3, int i4, Composer composer, int i5) {
        m1184VerticalPager8jOkeI(pagerState, modifier, paddingValues, pageSize, i, f, horizontal, targetedFlingBehavior, z, z2, function1, nestedScrollConnection, snapPosition, overscrollEffect, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit VerticalPager_oI3XNZo$lambda$3(PagerState pagerState, Modifier modifier, PaddingValues paddingValues, PageSize pageSize, int i, float f, Alignment.Horizontal horizontal, TargetedFlingBehavior targetedFlingBehavior, boolean z, boolean z2, Function1 function1, NestedScrollConnection nestedScrollConnection, SnapPosition snapPosition, Function4 function4, int i2, int i3, int i4, Composer composer, int i5) {
        m1185VerticalPageroI3XNZo(pagerState, modifier, paddingValues, pageSize, i, f, horizontal, targetedFlingBehavior, z, z2, function1, nestedScrollConnection, snapPosition, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    private static final void debugLog(Function0<String> function0) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x03ee  */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [int] */
    /* renamed from: HorizontalPager--8jOkeI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m1182HorizontalPager8jOkeI(final PagerState pagerState, Modifier modifier, PaddingValues paddingValues, PageSize pageSize, int i, float f, Alignment.Vertical vertical, TargetedFlingBehavior targetedFlingBehavior, boolean z, boolean z2, Function1<? super Integer, ? extends Object> function1, NestedScrollConnection nestedScrollConnection, SnapPosition snapPosition, OverscrollEffect overscrollEffect, final Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i2, final int i3, final int i4) {
        int i5;
        Modifier modifier2;
        int i6;
        int i7;
        int i8;
        final int i9;
        int i10;
        float f2;
        int i11;
        Alignment.Vertical vertical2;
        TargetedFlingBehavior targetedFlingBehavior2;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        Composer composer2;
        final PaddingValues paddingValues2;
        final PageSize pageSize2;
        final boolean z3;
        final boolean z4;
        final Function1<? super Integer, ? extends Object> function12;
        final NestedScrollConnection nestedScrollConnection2;
        final SnapPosition snapPosition2;
        final float f3;
        final Modifier modifier3;
        final Alignment.Vertical vertical3;
        final TargetedFlingBehavior targetedFlingBehavior3;
        final OverscrollEffect overscrollEffect2;
        ScopeUpdateScope endRestartGroup;
        PagerState pagerState2;
        ?? r4;
        int i22;
        int i23;
        NestedScrollConnection nestedScrollConnection3;
        int i24;
        Function1<? super Integer, ? extends Object> function13;
        NestedScrollConnection nestedScrollConnection4;
        int i25;
        PaddingValues paddingValues3;
        TargetedFlingBehavior targetedFlingBehavior4;
        PageSize pageSize3;
        boolean z5;
        int i26;
        float f4;
        int i27;
        boolean z6;
        SnapPosition snapPosition3;
        Modifier modifier4;
        OverscrollEffect overscrollEffect3;
        int i28;
        int i29;
        Composer startRestartGroup = composer.startRestartGroup(1860873769);
        ComposerKt.sourceInformation(startRestartGroup, "C(HorizontalPager)N(state,modifier,contentPadding,pageSize,beyondViewportPageCount,pageSpacing:c#ui.unit.Dp,verticalAlignment,flingBehavior,userScrollEnabled,reverseLayout,key,pageNestedScrollConnection,snapPosition,overscrollEffect,pageContent)130@7468L707:Pager.kt#g6yjnt");
        if ((i4 & 1) != 0) {
            i5 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i5 = (startRestartGroup.changed(pagerState) ? 4 : 2) | i2;
        } else {
            i5 = i2;
        }
        int i30 = i4 & 2;
        if (i30 != 0) {
            i5 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i5 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i6 = i4 & 4;
            if (i6 == 0) {
                i5 |= 384;
            } else if ((i2 & 384) == 0) {
                i5 |= startRestartGroup.changed(paddingValues) ? 256 : 128;
                i7 = i4 & 8;
                int i31 = 1024;
                if (i7 != 0) {
                    i5 |= 3072;
                } else if ((i2 & 3072) == 0) {
                    i5 |= startRestartGroup.changed(pageSize) ? 2048 : 1024;
                    i8 = i4 & 16;
                    if (i8 == 0) {
                        i5 |= 24576;
                    } else if ((i2 & 24576) == 0) {
                        i9 = i;
                        i5 |= startRestartGroup.changed(i9) ? 16384 : 8192;
                        i10 = i4 & 32;
                        if (i10 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            f2 = f;
                        } else {
                            f2 = f;
                            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i5 |= startRestartGroup.changed(f2) ? 131072 : 65536;
                            }
                        }
                        i11 = i4 & 64;
                        if (i11 != 0) {
                            i5 |= 1572864;
                            vertical2 = vertical;
                        } else {
                            vertical2 = vertical;
                            if ((i2 & 1572864) == 0) {
                                i5 |= startRestartGroup.changed(vertical2) ? 1048576 : 524288;
                            }
                        }
                        if ((i2 & 12582912) == 0) {
                            if ((i4 & 128) == 0) {
                                targetedFlingBehavior2 = targetedFlingBehavior;
                                if (startRestartGroup.changed(targetedFlingBehavior2)) {
                                    i29 = 8388608;
                                    i5 |= i29;
                                }
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehavior;
                            }
                            i29 = 4194304;
                            i5 |= i29;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehavior;
                        }
                        i12 = i4 & 256;
                        if (i12 != 0) {
                            i5 |= 100663296;
                        } else if ((i2 & 100663296) == 0) {
                            i5 |= startRestartGroup.changed(z) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        }
                        i13 = i4 & 512;
                        if (i13 != 0) {
                            i15 = i5 | 805306368;
                            i14 = i13;
                        } else {
                            if ((i2 & 805306368) == 0) {
                                i14 = i13;
                                i5 |= startRestartGroup.changed(z2) ? 536870912 : 268435456;
                            } else {
                                i14 = i13;
                            }
                            i15 = i5;
                        }
                        i16 = i4 & 1024;
                        if (i16 != 0) {
                            i17 = i3 | 6;
                        } else if ((i3 & 6) == 0) {
                            i17 = i3 | (startRestartGroup.changedInstance(function1) ? 4 : 2);
                        } else {
                            i17 = i3;
                        }
                        if ((i3 & 48) == 0) {
                            i18 = i16;
                            if ((i4 & 2048) == 0 && startRestartGroup.changedInstance(nestedScrollConnection)) {
                                i28 = 32;
                                i17 |= i28;
                            }
                            i28 = 16;
                            i17 |= i28;
                        } else {
                            i18 = i16;
                        }
                        int i32 = i17;
                        i19 = i4 & 4096;
                        if (i19 != 0) {
                            i20 = i32 | 384;
                        } else {
                            i20 = i32;
                            if ((i3 & 384) == 0) {
                                i20 |= startRestartGroup.changed(snapPosition) ? 256 : 128;
                                if ((i3 & 3072) == 0) {
                                    if ((i4 & 8192) == 0 && startRestartGroup.changed(overscrollEffect)) {
                                        i31 = 2048;
                                    }
                                    i20 |= i31;
                                }
                                i21 = i20;
                                if ((i4 & 16384) == 0) {
                                    i21 |= 24576;
                                } else if ((i3 & 24576) == 0) {
                                    i21 |= startRestartGroup.changedInstance(function4) ? 16384 : 8192;
                                    if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
                                        startRestartGroup.startDefaults();
                                        ComposerKt.sourceInformation(startRestartGroup, "120@6993L28,125@7217L57,127@7371L26");
                                        if ((i2 & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                            startRestartGroup.skipToGroupEnd();
                                            if ((i4 & 128) != 0) {
                                                i15 &= -29360129;
                                            }
                                            if ((i4 & 2048) != 0) {
                                                i21 &= -113;
                                            }
                                            if ((i4 & 8192) != 0) {
                                                i21 &= -7169;
                                            }
                                            paddingValues3 = paddingValues;
                                            pageSize3 = pageSize;
                                            z6 = z2;
                                            function13 = function1;
                                            nestedScrollConnection4 = nestedScrollConnection;
                                            i25 = i21;
                                            composer2 = startRestartGroup;
                                            f4 = f2;
                                            modifier4 = modifier2;
                                            i27 = i15;
                                            z5 = z;
                                            overscrollEffect3 = overscrollEffect;
                                            i26 = i9;
                                            targetedFlingBehavior4 = targetedFlingBehavior2;
                                            snapPosition3 = snapPosition;
                                        } else {
                                            Modifier modifier5 = i30 != 0 ? Modifier.INSTANCE : modifier2;
                                            PaddingValues m871PaddingValues0680j_4 = i6 != 0 ? PaddingKt.m871PaddingValues0680j_4(Dp.m7399constructorimpl(0)) : paddingValues;
                                            PageSize pageSize4 = i7 != 0 ? PageSize.Fill.INSTANCE : pageSize;
                                            int i33 = i8 != 0 ? 0 : i9;
                                            float m7399constructorimpl = i10 != 0 ? Dp.m7399constructorimpl(0) : f2;
                                            if (i11 != 0) {
                                                vertical2 = Alignment.INSTANCE.getCenterVertically();
                                            }
                                            if ((i4 & 128) != 0) {
                                                i22 = i19;
                                                i23 = i21;
                                                pagerState2 = pagerState;
                                                TargetedFlingBehavior flingBehavior = PagerDefaults.INSTANCE.flingBehavior(pagerState2, null, null, null, 0.0f, startRestartGroup, (i15 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                                i15 &= -29360129;
                                                r4 = 0;
                                                targetedFlingBehavior2 = flingBehavior;
                                            } else {
                                                pagerState2 = pagerState;
                                                r4 = 0;
                                                i22 = i19;
                                                i23 = i21;
                                            }
                                            boolean z7 = i12 == 0 ? z : true;
                                            boolean z8 = i14 != 0 ? r4 : z2;
                                            Function1<? super Integer, ? extends Object> function14 = i18 != 0 ? null : function1;
                                            if ((i4 & 2048) != 0) {
                                                nestedScrollConnection3 = PagerDefaults.INSTANCE.pageNestedScrollConnection(pagerState2, Orientation.Horizontal, startRestartGroup, (i15 & 14) | 432);
                                                i24 = i23 & (-113);
                                            } else {
                                                nestedScrollConnection3 = nestedScrollConnection;
                                                i24 = i23;
                                            }
                                            SnapPosition.Start start = i22 != 0 ? SnapPosition.Start.INSTANCE : snapPosition;
                                            if ((i4 & 8192) != 0) {
                                                SnapPosition snapPosition4 = start;
                                                overscrollEffect3 = OverscrollKt.rememberOverscrollEffect(startRestartGroup, r4);
                                                i25 = i24 & (-7169);
                                                targetedFlingBehavior4 = targetedFlingBehavior2;
                                                snapPosition3 = snapPosition4;
                                                function13 = function14;
                                                nestedScrollConnection4 = nestedScrollConnection3;
                                                composer2 = startRestartGroup;
                                                paddingValues3 = m871PaddingValues0680j_4;
                                                pageSize3 = pageSize4;
                                                z5 = z7;
                                                i26 = i33;
                                                f4 = m7399constructorimpl;
                                                i27 = i15;
                                                z6 = z8;
                                                modifier4 = modifier5;
                                            } else {
                                                function13 = function14;
                                                nestedScrollConnection4 = nestedScrollConnection3;
                                                i25 = i24;
                                                composer2 = startRestartGroup;
                                                paddingValues3 = m871PaddingValues0680j_4;
                                                targetedFlingBehavior4 = targetedFlingBehavior2;
                                                pageSize3 = pageSize4;
                                                z5 = z7;
                                                i26 = i33;
                                                f4 = m7399constructorimpl;
                                                i27 = i15;
                                                z6 = z8;
                                                snapPosition3 = start;
                                                modifier4 = modifier5;
                                                overscrollEffect3 = overscrollEffect;
                                            }
                                        }
                                        composer2.endDefaults();
                                        Modifier modifier6 = modifier4;
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1860873769, i27, i25, "androidx.compose.foundation.pager.HorizontalPager (Pager.kt:129)");
                                        }
                                        int i34 = i25;
                                        int i35 = i27 >> 6;
                                        int i36 = i27 << 12;
                                        int i37 = ((i27 >> 3) & 14) | 24576 | ((i27 << 3) & 112) | (i27 & 896) | ((i27 >> 18) & 7168) | (i35 & 458752) | (i35 & 3670016) | ((i34 << 12) & 29360128) | (i36 & 234881024) | (i36 & 1879048192);
                                        int i38 = ((i27 >> 9) & 14) | 3072 | (i34 & 112);
                                        int i39 = i34 << 6;
                                        LazyLayoutPagerKt.m1174PagereLwUrMk(modifier6, pagerState, paddingValues3, z6, Orientation.Horizontal, targetedFlingBehavior4, z5, overscrollEffect3, i26, f4, pageSize3, nestedScrollConnection4, function13, Alignment.INSTANCE.getCenterHorizontally(), vertical2, snapPosition3, function4, composer2, i37, i38 | (i39 & 896) | (i35 & 57344) | ((i34 << 9) & 458752) | (i39 & 3670016), 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        int i40 = i26;
                                        targetedFlingBehavior3 = targetedFlingBehavior4;
                                        i9 = i40;
                                        float f5 = f4;
                                        z3 = z5;
                                        f3 = f5;
                                        Alignment.Vertical vertical4 = vertical2;
                                        overscrollEffect2 = overscrollEffect3;
                                        vertical3 = vertical4;
                                        Function1<? super Integer, ? extends Object> function15 = function13;
                                        nestedScrollConnection2 = nestedScrollConnection4;
                                        function12 = function15;
                                        pageSize2 = pageSize3;
                                        snapPosition2 = snapPosition3;
                                        z4 = z6;
                                        paddingValues2 = paddingValues3;
                                        modifier3 = modifier6;
                                    } else {
                                        composer2 = startRestartGroup;
                                        composer2.skipToGroupEnd();
                                        paddingValues2 = paddingValues;
                                        pageSize2 = pageSize;
                                        z3 = z;
                                        z4 = z2;
                                        function12 = function1;
                                        nestedScrollConnection2 = nestedScrollConnection;
                                        snapPosition2 = snapPosition;
                                        f3 = f2;
                                        modifier3 = modifier2;
                                        vertical3 = vertical2;
                                        targetedFlingBehavior3 = targetedFlingBehavior2;
                                        overscrollEffect2 = overscrollEffect;
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup != null) {
                                        endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.pager.PagerKt$$ExternalSyntheticLambda8
                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(Object obj, Object obj2) {
                                                Unit HorizontalPager__8jOkeI$lambda$0;
                                                HorizontalPager__8jOkeI$lambda$0 = PagerKt.HorizontalPager__8jOkeI$lambda$0(PagerState.this, modifier3, paddingValues2, pageSize2, i9, f3, vertical3, targetedFlingBehavior3, z3, z4, function12, nestedScrollConnection2, snapPosition2, overscrollEffect2, function4, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                                return HorizontalPager__8jOkeI$lambda$0;
                                            }
                                        });
                                        return;
                                    }
                                    return;
                                }
                                if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                }
                            }
                        }
                        if ((i3 & 3072) == 0) {
                        }
                        i21 = i20;
                        if ((i4 & 16384) == 0) {
                        }
                        if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    i9 = i;
                    i10 = i4 & 32;
                    if (i10 != 0) {
                    }
                    i11 = i4 & 64;
                    if (i11 != 0) {
                    }
                    if ((i2 & 12582912) == 0) {
                    }
                    i12 = i4 & 256;
                    if (i12 != 0) {
                    }
                    i13 = i4 & 512;
                    if (i13 != 0) {
                    }
                    i16 = i4 & 1024;
                    if (i16 != 0) {
                    }
                    if ((i3 & 48) == 0) {
                    }
                    int i322 = i17;
                    i19 = i4 & 4096;
                    if (i19 != 0) {
                    }
                    if ((i3 & 3072) == 0) {
                    }
                    i21 = i20;
                    if ((i4 & 16384) == 0) {
                    }
                    if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                i8 = i4 & 16;
                if (i8 == 0) {
                }
                i9 = i;
                i10 = i4 & 32;
                if (i10 != 0) {
                }
                i11 = i4 & 64;
                if (i11 != 0) {
                }
                if ((i2 & 12582912) == 0) {
                }
                i12 = i4 & 256;
                if (i12 != 0) {
                }
                i13 = i4 & 512;
                if (i13 != 0) {
                }
                i16 = i4 & 1024;
                if (i16 != 0) {
                }
                if ((i3 & 48) == 0) {
                }
                int i3222 = i17;
                i19 = i4 & 4096;
                if (i19 != 0) {
                }
                if ((i3 & 3072) == 0) {
                }
                i21 = i20;
                if ((i4 & 16384) == 0) {
                }
                if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            i7 = i4 & 8;
            int i312 = 1024;
            if (i7 != 0) {
            }
            i8 = i4 & 16;
            if (i8 == 0) {
            }
            i9 = i;
            i10 = i4 & 32;
            if (i10 != 0) {
            }
            i11 = i4 & 64;
            if (i11 != 0) {
            }
            if ((i2 & 12582912) == 0) {
            }
            i12 = i4 & 256;
            if (i12 != 0) {
            }
            i13 = i4 & 512;
            if (i13 != 0) {
            }
            i16 = i4 & 1024;
            if (i16 != 0) {
            }
            if ((i3 & 48) == 0) {
            }
            int i32222 = i17;
            i19 = i4 & 4096;
            if (i19 != 0) {
            }
            if ((i3 & 3072) == 0) {
            }
            i21 = i20;
            if ((i4 & 16384) == 0) {
            }
            if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i6 = i4 & 4;
        if (i6 == 0) {
        }
        i7 = i4 & 8;
        int i3122 = 1024;
        if (i7 != 0) {
        }
        i8 = i4 & 16;
        if (i8 == 0) {
        }
        i9 = i;
        i10 = i4 & 32;
        if (i10 != 0) {
        }
        i11 = i4 & 64;
        if (i11 != 0) {
        }
        if ((i2 & 12582912) == 0) {
        }
        i12 = i4 & 256;
        if (i12 != 0) {
        }
        i13 = i4 & 512;
        if (i13 != 0) {
        }
        i16 = i4 & 1024;
        if (i16 != 0) {
        }
        if ((i3 & 48) == 0) {
        }
        int i322222 = i17;
        i19 = i4 & 4096;
        if (i19 != 0) {
        }
        if ((i3 & 3072) == 0) {
        }
        i21 = i20;
        if ((i4 & 16384) == 0) {
        }
        if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    /* renamed from: HorizontalPager-oI3XNZo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m1183HorizontalPageroI3XNZo(final PagerState pagerState, Modifier modifier, PaddingValues paddingValues, PageSize pageSize, int i, float f, Alignment.Vertical vertical, TargetedFlingBehavior targetedFlingBehavior, boolean z, boolean z2, Function1 function1, NestedScrollConnection nestedScrollConnection, SnapPosition snapPosition, final Function4 function4, Composer composer, final int i2, final int i3, final int i4) {
        int i5;
        Modifier modifier2;
        int i6;
        int i7;
        int i8;
        final int i9;
        int i10;
        final float f2;
        int i11;
        Alignment.Vertical vertical2;
        TargetedFlingBehavior targetedFlingBehavior2;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        final PaddingValues paddingValues2;
        final PageSize pageSize2;
        final boolean z3;
        final boolean z4;
        final Function1 function12;
        final NestedScrollConnection nestedScrollConnection2;
        final Modifier modifier3;
        final Alignment.Vertical vertical3;
        final TargetedFlingBehavior targetedFlingBehavior3;
        final SnapPosition snapPosition2;
        ScopeUpdateScope endRestartGroup;
        Modifier modifier4;
        int i21;
        PagerState pagerState2;
        int i22;
        Composer composer2;
        int i23;
        NestedScrollConnection nestedScrollConnection3;
        SnapPosition snapPosition3;
        boolean z5;
        Function1 function13;
        NestedScrollConnection nestedScrollConnection4;
        TargetedFlingBehavior targetedFlingBehavior4;
        PageSize pageSize3;
        boolean z6;
        float f3;
        PaddingValues paddingValues3;
        int i24;
        int i25;
        int i26;
        Composer startRestartGroup = composer.startRestartGroup(1163833967);
        ComposerKt.sourceInformation(startRestartGroup, "C(HorizontalPager)N(state,modifier,contentPadding,pageSize,beyondViewportPageCount,pageSpacing:c#ui.unit.Dp,verticalAlignment,flingBehavior,userScrollEnabled,reverseLayout,key,pageNestedScrollConnection,snapPosition,pageContent)184@9636L26,170@9083L621:Pager.kt#g6yjnt");
        if ((i4 & 1) != 0) {
            i5 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i5 = (startRestartGroup.changed(pagerState) ? 4 : 2) | i2;
        } else {
            i5 = i2;
        }
        int i27 = i4 & 2;
        if (i27 != 0) {
            i5 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i5 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i6 = i4 & 4;
            if (i6 == 0) {
                i5 |= 384;
            } else if ((i2 & 384) == 0) {
                i5 |= startRestartGroup.changed(paddingValues) ? 256 : 128;
                i7 = i4 & 8;
                if (i7 != 0) {
                    i5 |= 3072;
                } else if ((i2 & 3072) == 0) {
                    i5 |= startRestartGroup.changed(pageSize) ? 2048 : 1024;
                    i8 = i4 & 16;
                    if (i8 == 0) {
                        i5 |= 24576;
                    } else if ((i2 & 24576) == 0) {
                        i9 = i;
                        i5 |= startRestartGroup.changed(i9) ? 16384 : 8192;
                        i10 = i4 & 32;
                        if (i10 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            f2 = f;
                        } else {
                            f2 = f;
                            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i5 |= startRestartGroup.changed(f2) ? 131072 : 65536;
                            }
                        }
                        i11 = i4 & 64;
                        if (i11 != 0) {
                            i5 |= 1572864;
                            vertical2 = vertical;
                        } else {
                            vertical2 = vertical;
                            if ((i2 & 1572864) == 0) {
                                i5 |= startRestartGroup.changed(vertical2) ? 1048576 : 524288;
                            }
                        }
                        if ((i2 & 12582912) == 0) {
                            if ((i4 & 128) == 0) {
                                targetedFlingBehavior2 = targetedFlingBehavior;
                                if (startRestartGroup.changed(targetedFlingBehavior2)) {
                                    i26 = 8388608;
                                    i5 |= i26;
                                }
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehavior;
                            }
                            i26 = 4194304;
                            i5 |= i26;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehavior;
                        }
                        i12 = i4 & 256;
                        if (i12 != 0) {
                            i5 |= 100663296;
                        } else if ((i2 & 100663296) == 0) {
                            i5 |= startRestartGroup.changed(z) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        }
                        i13 = i4 & 512;
                        if (i13 != 0) {
                            i15 = i5 | 805306368;
                            i14 = i13;
                        } else {
                            if ((i2 & 805306368) == 0) {
                                i14 = i13;
                                i5 |= startRestartGroup.changed(z2) ? 536870912 : 268435456;
                            } else {
                                i14 = i13;
                            }
                            i15 = i5;
                        }
                        i16 = i4 & 1024;
                        if (i16 != 0) {
                            i17 = i3 | 6;
                        } else if ((i3 & 6) == 0) {
                            i17 = i3 | (startRestartGroup.changedInstance(function1) ? 4 : 2);
                        } else {
                            i17 = i3;
                        }
                        if ((i3 & 48) == 0) {
                            i18 = i16;
                            if ((i4 & 2048) == 0 && startRestartGroup.changedInstance(nestedScrollConnection)) {
                                i25 = 32;
                                i17 |= i25;
                            }
                            i25 = 16;
                            i17 |= i25;
                        } else {
                            i18 = i16;
                        }
                        int i28 = i17;
                        i19 = i4 & 4096;
                        if (i19 != 0) {
                            i20 = i28 | 384;
                        } else {
                            int i29 = i28;
                            if ((i3 & 384) == 0) {
                                i29 |= startRestartGroup.changed(snapPosition) ? 256 : 128;
                            }
                            i20 = i29;
                        }
                        if ((i4 & 8192) != 0) {
                            i20 |= 3072;
                        } else if ((i3 & 3072) == 0) {
                            i20 |= startRestartGroup.changedInstance(function4) ? 2048 : 1024;
                            if (!startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i20 & 1171) != 1170, i15 & 1)) {
                                startRestartGroup.startDefaults();
                                ComposerKt.sourceInformation(startRestartGroup, "161@8678L28,166@8902L57");
                                if ((i2 & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i4 & 128) != 0) {
                                        i15 &= -29360129;
                                    }
                                    if ((i4 & 2048) != 0) {
                                        i20 &= -113;
                                    }
                                    pagerState2 = pagerState;
                                    paddingValues3 = paddingValues;
                                    z6 = z;
                                    z5 = z2;
                                    function13 = function1;
                                    nestedScrollConnection4 = nestedScrollConnection;
                                    snapPosition3 = snapPosition;
                                    i22 = 0;
                                    i21 = i9;
                                    f3 = f2;
                                    modifier4 = modifier2;
                                    targetedFlingBehavior4 = targetedFlingBehavior2;
                                    composer2 = startRestartGroup;
                                    i24 = i15;
                                    pageSize3 = pageSize;
                                } else {
                                    modifier4 = i27 != 0 ? Modifier.INSTANCE : modifier2;
                                    PaddingValues m871PaddingValues0680j_4 = i6 != 0 ? PaddingKt.m871PaddingValues0680j_4(Dp.m7399constructorimpl(0)) : paddingValues;
                                    PageSize pageSize4 = i7 != 0 ? PageSize.Fill.INSTANCE : pageSize;
                                    i21 = i8 != 0 ? 0 : i9;
                                    float m7399constructorimpl = i10 != 0 ? Dp.m7399constructorimpl(0) : f2;
                                    if (i11 != 0) {
                                        vertical2 = Alignment.INSTANCE.getCenterVertically();
                                    }
                                    if ((i4 & 128) != 0) {
                                        composer2 = startRestartGroup;
                                        i23 = i20;
                                        pagerState2 = pagerState;
                                        TargetedFlingBehavior flingBehavior = PagerDefaults.INSTANCE.flingBehavior(pagerState2, null, null, null, 0.0f, composer2, (i15 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                        i15 &= -29360129;
                                        i22 = 0;
                                        targetedFlingBehavior2 = flingBehavior;
                                    } else {
                                        pagerState2 = pagerState;
                                        i22 = 0;
                                        composer2 = startRestartGroup;
                                        i23 = i20;
                                    }
                                    boolean z7 = i12 == 0 ? z : true;
                                    boolean z8 = i14 != 0 ? i22 == true ? 1 : 0 : z2;
                                    Function1 function14 = i18 != 0 ? null : function1;
                                    if ((i4 & 2048) != 0) {
                                        nestedScrollConnection3 = PagerDefaults.INSTANCE.pageNestedScrollConnection(pagerState2, Orientation.Horizontal, composer2, (i15 & 14) | 432);
                                        i23 &= -113;
                                    } else {
                                        nestedScrollConnection3 = nestedScrollConnection;
                                    }
                                    if (i19 != 0) {
                                        z5 = z8;
                                        function13 = function14;
                                        nestedScrollConnection4 = nestedScrollConnection3;
                                        snapPosition3 = SnapPosition.Start.INSTANCE;
                                    } else {
                                        snapPosition3 = snapPosition;
                                        z5 = z8;
                                        function13 = function14;
                                        nestedScrollConnection4 = nestedScrollConnection3;
                                    }
                                    targetedFlingBehavior4 = targetedFlingBehavior2;
                                    i20 = i23;
                                    pageSize3 = pageSize4;
                                    z6 = z7;
                                    f3 = m7399constructorimpl;
                                    paddingValues3 = m871PaddingValues0680j_4;
                                    i24 = i15;
                                }
                                composer2.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1163833967, i24, i20, "androidx.compose.foundation.pager.HorizontalPager (Pager.kt:169)");
                                    i22 = 0;
                                }
                                int i30 = (i20 & 1022) | ((i20 << 3) & 57344);
                                PagerState pagerState3 = pagerState2;
                                startRestartGroup = composer2;
                                Alignment.Vertical vertical4 = vertical2;
                                Modifier modifier5 = modifier4;
                                int i31 = i21;
                                m1182HorizontalPager8jOkeI(pagerState3, modifier5, paddingValues3, pageSize3, i31, f3, vertical4, targetedFlingBehavior4, z6, z5, function13, nestedScrollConnection4, snapPosition3, OverscrollKt.rememberOverscrollEffect(composer2, i22), function4, startRestartGroup, i24 & 2147483646, i30, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                snapPosition2 = snapPosition3;
                                nestedScrollConnection2 = nestedScrollConnection4;
                                function12 = function13;
                                z4 = z5;
                                z3 = z6;
                                targetedFlingBehavior3 = targetedFlingBehavior4;
                                vertical3 = vertical4;
                                f2 = f3;
                                i9 = i31;
                                pageSize2 = pageSize3;
                                paddingValues2 = paddingValues3;
                                modifier3 = modifier5;
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                paddingValues2 = paddingValues;
                                pageSize2 = pageSize;
                                z3 = z;
                                z4 = z2;
                                function12 = function1;
                                nestedScrollConnection2 = nestedScrollConnection;
                                modifier3 = modifier2;
                                vertical3 = vertical2;
                                targetedFlingBehavior3 = targetedFlingBehavior2;
                                snapPosition2 = snapPosition;
                            }
                            endRestartGroup = startRestartGroup.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.pager.PagerKt$$ExternalSyntheticLambda7
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit HorizontalPager_oI3XNZo$lambda$1;
                                        HorizontalPager_oI3XNZo$lambda$1 = PagerKt.HorizontalPager_oI3XNZo$lambda$1(PagerState.this, modifier3, paddingValues2, pageSize2, i9, f2, vertical3, targetedFlingBehavior3, z3, z4, function12, nestedScrollConnection2, snapPosition2, function4, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                        return HorizontalPager_oI3XNZo$lambda$1;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        if (!startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i20 & 1171) != 1170, i15 & 1)) {
                        }
                        endRestartGroup = startRestartGroup.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i9 = i;
                    i10 = i4 & 32;
                    if (i10 != 0) {
                    }
                    i11 = i4 & 64;
                    if (i11 != 0) {
                    }
                    if ((i2 & 12582912) == 0) {
                    }
                    i12 = i4 & 256;
                    if (i12 != 0) {
                    }
                    i13 = i4 & 512;
                    if (i13 != 0) {
                    }
                    i16 = i4 & 1024;
                    if (i16 != 0) {
                    }
                    if ((i3 & 48) == 0) {
                    }
                    int i282 = i17;
                    i19 = i4 & 4096;
                    if (i19 != 0) {
                    }
                    if ((i4 & 8192) != 0) {
                    }
                    if (!startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i20 & 1171) != 1170, i15 & 1)) {
                    }
                    endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                i8 = i4 & 16;
                if (i8 == 0) {
                }
                i9 = i;
                i10 = i4 & 32;
                if (i10 != 0) {
                }
                i11 = i4 & 64;
                if (i11 != 0) {
                }
                if ((i2 & 12582912) == 0) {
                }
                i12 = i4 & 256;
                if (i12 != 0) {
                }
                i13 = i4 & 512;
                if (i13 != 0) {
                }
                i16 = i4 & 1024;
                if (i16 != 0) {
                }
                if ((i3 & 48) == 0) {
                }
                int i2822 = i17;
                i19 = i4 & 4096;
                if (i19 != 0) {
                }
                if ((i4 & 8192) != 0) {
                }
                if (!startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i20 & 1171) != 1170, i15 & 1)) {
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            i7 = i4 & 8;
            if (i7 != 0) {
            }
            i8 = i4 & 16;
            if (i8 == 0) {
            }
            i9 = i;
            i10 = i4 & 32;
            if (i10 != 0) {
            }
            i11 = i4 & 64;
            if (i11 != 0) {
            }
            if ((i2 & 12582912) == 0) {
            }
            i12 = i4 & 256;
            if (i12 != 0) {
            }
            i13 = i4 & 512;
            if (i13 != 0) {
            }
            i16 = i4 & 1024;
            if (i16 != 0) {
            }
            if ((i3 & 48) == 0) {
            }
            int i28222 = i17;
            i19 = i4 & 4096;
            if (i19 != 0) {
            }
            if ((i4 & 8192) != 0) {
            }
            if (!startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i20 & 1171) != 1170, i15 & 1)) {
            }
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i6 = i4 & 4;
        if (i6 == 0) {
        }
        i7 = i4 & 8;
        if (i7 != 0) {
        }
        i8 = i4 & 16;
        if (i8 == 0) {
        }
        i9 = i;
        i10 = i4 & 32;
        if (i10 != 0) {
        }
        i11 = i4 & 64;
        if (i11 != 0) {
        }
        if ((i2 & 12582912) == 0) {
        }
        i12 = i4 & 256;
        if (i12 != 0) {
        }
        i13 = i4 & 512;
        if (i13 != 0) {
        }
        i16 = i4 & 1024;
        if (i16 != 0) {
        }
        if ((i3 & 48) == 0) {
        }
        int i282222 = i17;
        i19 = i4 & 4096;
        if (i19 != 0) {
        }
        if ((i4 & 8192) != 0) {
        }
        if (!startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i20 & 1171) != 1170, i15 & 1)) {
        }
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x03ee  */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [int] */
    /* renamed from: VerticalPager--8jOkeI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m1184VerticalPager8jOkeI(final PagerState pagerState, Modifier modifier, PaddingValues paddingValues, PageSize pageSize, int i, float f, Alignment.Horizontal horizontal, TargetedFlingBehavior targetedFlingBehavior, boolean z, boolean z2, Function1<? super Integer, ? extends Object> function1, NestedScrollConnection nestedScrollConnection, SnapPosition snapPosition, OverscrollEffect overscrollEffect, final Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i2, final int i3, final int i4) {
        int i5;
        Modifier modifier2;
        int i6;
        int i7;
        int i8;
        final int i9;
        int i10;
        float f2;
        int i11;
        Alignment.Horizontal horizontal2;
        TargetedFlingBehavior targetedFlingBehavior2;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        Composer composer2;
        final PaddingValues paddingValues2;
        final PageSize pageSize2;
        final boolean z3;
        final boolean z4;
        final Function1<? super Integer, ? extends Object> function12;
        final NestedScrollConnection nestedScrollConnection2;
        final SnapPosition snapPosition2;
        final float f3;
        final Modifier modifier3;
        final Alignment.Horizontal horizontal3;
        final TargetedFlingBehavior targetedFlingBehavior3;
        final OverscrollEffect overscrollEffect2;
        ScopeUpdateScope endRestartGroup;
        PagerState pagerState2;
        ?? r4;
        int i22;
        int i23;
        NestedScrollConnection nestedScrollConnection3;
        int i24;
        Function1<? super Integer, ? extends Object> function13;
        NestedScrollConnection nestedScrollConnection4;
        int i25;
        PaddingValues paddingValues3;
        Alignment.Horizontal horizontal4;
        TargetedFlingBehavior targetedFlingBehavior4;
        PageSize pageSize3;
        boolean z5;
        int i26;
        float f4;
        int i27;
        boolean z6;
        SnapPosition snapPosition3;
        Modifier modifier4;
        OverscrollEffect overscrollEffect3;
        int i28;
        int i29;
        Composer startRestartGroup = composer.startRestartGroup(-1590376023);
        ComposerKt.sourceInformation(startRestartGroup, "C(VerticalPager)N(state,modifier,contentPadding,pageSize,beyondViewportPageCount,pageSpacing:c#ui.unit.Dp,horizontalAlignment,flingBehavior,userScrollEnabled,reverseLayout,key,pageNestedScrollConnection,snapPosition,overscrollEffect,pageContent)260@14397L705:Pager.kt#g6yjnt");
        if ((i4 & 1) != 0) {
            i5 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i5 = (startRestartGroup.changed(pagerState) ? 4 : 2) | i2;
        } else {
            i5 = i2;
        }
        int i30 = i4 & 2;
        if (i30 != 0) {
            i5 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i5 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i6 = i4 & 4;
            if (i6 == 0) {
                i5 |= 384;
            } else if ((i2 & 384) == 0) {
                i5 |= startRestartGroup.changed(paddingValues) ? 256 : 128;
                i7 = i4 & 8;
                int i31 = 1024;
                if (i7 != 0) {
                    i5 |= 3072;
                } else if ((i2 & 3072) == 0) {
                    i5 |= startRestartGroup.changed(pageSize) ? 2048 : 1024;
                    i8 = i4 & 16;
                    if (i8 == 0) {
                        i5 |= 24576;
                    } else if ((i2 & 24576) == 0) {
                        i9 = i;
                        i5 |= startRestartGroup.changed(i9) ? 16384 : 8192;
                        i10 = i4 & 32;
                        if (i10 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            f2 = f;
                        } else {
                            f2 = f;
                            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i5 |= startRestartGroup.changed(f2) ? 131072 : 65536;
                            }
                        }
                        i11 = i4 & 64;
                        if (i11 != 0) {
                            i5 |= 1572864;
                            horizontal2 = horizontal;
                        } else {
                            horizontal2 = horizontal;
                            if ((i2 & 1572864) == 0) {
                                i5 |= startRestartGroup.changed(horizontal2) ? 1048576 : 524288;
                            }
                        }
                        if ((i2 & 12582912) == 0) {
                            if ((i4 & 128) == 0) {
                                targetedFlingBehavior2 = targetedFlingBehavior;
                                if (startRestartGroup.changed(targetedFlingBehavior2)) {
                                    i29 = 8388608;
                                    i5 |= i29;
                                }
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehavior;
                            }
                            i29 = 4194304;
                            i5 |= i29;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehavior;
                        }
                        i12 = i4 & 256;
                        if (i12 != 0) {
                            i5 |= 100663296;
                        } else if ((i2 & 100663296) == 0) {
                            i5 |= startRestartGroup.changed(z) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        }
                        i13 = i4 & 512;
                        if (i13 != 0) {
                            i15 = i5 | 805306368;
                            i14 = i13;
                        } else {
                            if ((i2 & 805306368) == 0) {
                                i14 = i13;
                                i5 |= startRestartGroup.changed(z2) ? 536870912 : 268435456;
                            } else {
                                i14 = i13;
                            }
                            i15 = i5;
                        }
                        i16 = i4 & 1024;
                        if (i16 != 0) {
                            i17 = i3 | 6;
                        } else if ((i3 & 6) == 0) {
                            i17 = i3 | (startRestartGroup.changedInstance(function1) ? 4 : 2);
                        } else {
                            i17 = i3;
                        }
                        if ((i3 & 48) == 0) {
                            i18 = i16;
                            if ((i4 & 2048) == 0 && startRestartGroup.changedInstance(nestedScrollConnection)) {
                                i28 = 32;
                                i17 |= i28;
                            }
                            i28 = 16;
                            i17 |= i28;
                        } else {
                            i18 = i16;
                        }
                        int i32 = i17;
                        i19 = i4 & 4096;
                        if (i19 != 0) {
                            i20 = i32 | 384;
                        } else {
                            i20 = i32;
                            if ((i3 & 384) == 0) {
                                i20 |= startRestartGroup.changed(snapPosition) ? 256 : 128;
                                if ((i3 & 3072) == 0) {
                                    if ((i4 & 8192) == 0 && startRestartGroup.changed(overscrollEffect)) {
                                        i31 = 2048;
                                    }
                                    i20 |= i31;
                                }
                                i21 = i20;
                                if ((i4 & 16384) == 0) {
                                    i21 |= 24576;
                                } else if ((i3 & 24576) == 0) {
                                    i21 |= startRestartGroup.changedInstance(function4) ? 16384 : 8192;
                                    if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
                                        startRestartGroup.startDefaults();
                                        ComposerKt.sourceInformation(startRestartGroup, "250@13924L28,255@14148L55,257@14300L26");
                                        if ((i2 & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                            startRestartGroup.skipToGroupEnd();
                                            if ((i4 & 128) != 0) {
                                                i15 &= -29360129;
                                            }
                                            if ((i4 & 2048) != 0) {
                                                i21 &= -113;
                                            }
                                            if ((i4 & 8192) != 0) {
                                                i21 &= -7169;
                                            }
                                            paddingValues3 = paddingValues;
                                            pageSize3 = pageSize;
                                            z6 = z2;
                                            function13 = function1;
                                            nestedScrollConnection4 = nestedScrollConnection;
                                            i25 = i21;
                                            composer2 = startRestartGroup;
                                            f4 = f2;
                                            modifier4 = modifier2;
                                            horizontal4 = horizontal2;
                                            i27 = i15;
                                            z5 = z;
                                            overscrollEffect3 = overscrollEffect;
                                            i26 = i9;
                                            targetedFlingBehavior4 = targetedFlingBehavior2;
                                            snapPosition3 = snapPosition;
                                        } else {
                                            Modifier modifier5 = i30 != 0 ? Modifier.INSTANCE : modifier2;
                                            PaddingValues m871PaddingValues0680j_4 = i6 != 0 ? PaddingKt.m871PaddingValues0680j_4(Dp.m7399constructorimpl(0)) : paddingValues;
                                            PageSize pageSize4 = i7 != 0 ? PageSize.Fill.INSTANCE : pageSize;
                                            int i33 = i8 != 0 ? 0 : i9;
                                            float m7399constructorimpl = i10 != 0 ? Dp.m7399constructorimpl(0) : f2;
                                            if (i11 != 0) {
                                                horizontal2 = Alignment.INSTANCE.getCenterHorizontally();
                                            }
                                            if ((i4 & 128) != 0) {
                                                i22 = i19;
                                                i23 = i21;
                                                pagerState2 = pagerState;
                                                TargetedFlingBehavior flingBehavior = PagerDefaults.INSTANCE.flingBehavior(pagerState2, null, null, null, 0.0f, startRestartGroup, (i15 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                                i15 &= -29360129;
                                                r4 = 0;
                                                targetedFlingBehavior2 = flingBehavior;
                                            } else {
                                                pagerState2 = pagerState;
                                                r4 = 0;
                                                i22 = i19;
                                                i23 = i21;
                                            }
                                            boolean z7 = i12 == 0 ? z : true;
                                            boolean z8 = i14 != 0 ? r4 : z2;
                                            Function1<? super Integer, ? extends Object> function14 = i18 != 0 ? null : function1;
                                            if ((i4 & 2048) != 0) {
                                                nestedScrollConnection3 = PagerDefaults.INSTANCE.pageNestedScrollConnection(pagerState2, Orientation.Vertical, startRestartGroup, (i15 & 14) | 432);
                                                i24 = i23 & (-113);
                                            } else {
                                                nestedScrollConnection3 = nestedScrollConnection;
                                                i24 = i23;
                                            }
                                            SnapPosition.Start start = i22 != 0 ? SnapPosition.Start.INSTANCE : snapPosition;
                                            if ((i4 & 8192) != 0) {
                                                SnapPosition snapPosition4 = start;
                                                overscrollEffect3 = OverscrollKt.rememberOverscrollEffect(startRestartGroup, r4);
                                                i25 = i24 & (-7169);
                                                targetedFlingBehavior4 = targetedFlingBehavior2;
                                                snapPosition3 = snapPosition4;
                                                function13 = function14;
                                                nestedScrollConnection4 = nestedScrollConnection3;
                                                composer2 = startRestartGroup;
                                                paddingValues3 = m871PaddingValues0680j_4;
                                                horizontal4 = horizontal2;
                                                pageSize3 = pageSize4;
                                                z5 = z7;
                                                i26 = i33;
                                                f4 = m7399constructorimpl;
                                                i27 = i15;
                                                z6 = z8;
                                                modifier4 = modifier5;
                                            } else {
                                                function13 = function14;
                                                nestedScrollConnection4 = nestedScrollConnection3;
                                                i25 = i24;
                                                composer2 = startRestartGroup;
                                                paddingValues3 = m871PaddingValues0680j_4;
                                                horizontal4 = horizontal2;
                                                targetedFlingBehavior4 = targetedFlingBehavior2;
                                                pageSize3 = pageSize4;
                                                z5 = z7;
                                                i26 = i33;
                                                f4 = m7399constructorimpl;
                                                i27 = i15;
                                                z6 = z8;
                                                snapPosition3 = start;
                                                modifier4 = modifier5;
                                                overscrollEffect3 = overscrollEffect;
                                            }
                                        }
                                        composer2.endDefaults();
                                        Modifier modifier6 = modifier4;
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1590376023, i27, i25, "androidx.compose.foundation.pager.VerticalPager (Pager.kt:259)");
                                        }
                                        int i34 = i25;
                                        int i35 = i27 >> 6;
                                        int i36 = ((i27 >> 3) & 14) | 24576 | ((i27 << 3) & 112) | (i27 & 896) | ((i27 >> 18) & 7168) | (i35 & 458752) | (i35 & 3670016) | ((i34 << 12) & 29360128);
                                        int i37 = i27 << 12;
                                        int i38 = i36 | (i37 & 234881024) | (i37 & 1879048192);
                                        int i39 = i27 >> 9;
                                        int i40 = i34 << 6;
                                        PaddingValues paddingValues4 = paddingValues3;
                                        LazyLayoutPagerKt.m1174PagereLwUrMk(modifier6, pagerState, paddingValues4, z6, Orientation.Vertical, targetedFlingBehavior4, z5, overscrollEffect3, i26, f4, pageSize3, nestedScrollConnection4, function13, horizontal4, Alignment.INSTANCE.getCenterVertically(), snapPosition3, function4, composer2, i38, (i39 & 7168) | (i40 & 896) | (i39 & 14) | 24576 | (i34 & 112) | ((i34 << 9) & 458752) | (i40 & 3670016), 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        int i41 = i26;
                                        targetedFlingBehavior3 = targetedFlingBehavior4;
                                        i9 = i41;
                                        float f5 = f4;
                                        z3 = z5;
                                        f3 = f5;
                                        Function1<? super Integer, ? extends Object> function15 = function13;
                                        nestedScrollConnection2 = nestedScrollConnection4;
                                        function12 = function15;
                                        overscrollEffect2 = overscrollEffect3;
                                        pageSize2 = pageSize3;
                                        horizontal3 = horizontal4;
                                        snapPosition2 = snapPosition3;
                                        z4 = z6;
                                        paddingValues2 = paddingValues4;
                                        modifier3 = modifier6;
                                    } else {
                                        composer2 = startRestartGroup;
                                        composer2.skipToGroupEnd();
                                        paddingValues2 = paddingValues;
                                        pageSize2 = pageSize;
                                        z3 = z;
                                        z4 = z2;
                                        function12 = function1;
                                        nestedScrollConnection2 = nestedScrollConnection;
                                        snapPosition2 = snapPosition;
                                        f3 = f2;
                                        modifier3 = modifier2;
                                        horizontal3 = horizontal2;
                                        targetedFlingBehavior3 = targetedFlingBehavior2;
                                        overscrollEffect2 = overscrollEffect;
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup != null) {
                                        endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.pager.PagerKt$$ExternalSyntheticLambda6
                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(Object obj, Object obj2) {
                                                Unit VerticalPager__8jOkeI$lambda$2;
                                                VerticalPager__8jOkeI$lambda$2 = PagerKt.VerticalPager__8jOkeI$lambda$2(PagerState.this, modifier3, paddingValues2, pageSize2, i9, f3, horizontal3, targetedFlingBehavior3, z3, z4, function12, nestedScrollConnection2, snapPosition2, overscrollEffect2, function4, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                                return VerticalPager__8jOkeI$lambda$2;
                                            }
                                        });
                                        return;
                                    }
                                    return;
                                }
                                if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                }
                            }
                        }
                        if ((i3 & 3072) == 0) {
                        }
                        i21 = i20;
                        if ((i4 & 16384) == 0) {
                        }
                        if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    i9 = i;
                    i10 = i4 & 32;
                    if (i10 != 0) {
                    }
                    i11 = i4 & 64;
                    if (i11 != 0) {
                    }
                    if ((i2 & 12582912) == 0) {
                    }
                    i12 = i4 & 256;
                    if (i12 != 0) {
                    }
                    i13 = i4 & 512;
                    if (i13 != 0) {
                    }
                    i16 = i4 & 1024;
                    if (i16 != 0) {
                    }
                    if ((i3 & 48) == 0) {
                    }
                    int i322 = i17;
                    i19 = i4 & 4096;
                    if (i19 != 0) {
                    }
                    if ((i3 & 3072) == 0) {
                    }
                    i21 = i20;
                    if ((i4 & 16384) == 0) {
                    }
                    if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                i8 = i4 & 16;
                if (i8 == 0) {
                }
                i9 = i;
                i10 = i4 & 32;
                if (i10 != 0) {
                }
                i11 = i4 & 64;
                if (i11 != 0) {
                }
                if ((i2 & 12582912) == 0) {
                }
                i12 = i4 & 256;
                if (i12 != 0) {
                }
                i13 = i4 & 512;
                if (i13 != 0) {
                }
                i16 = i4 & 1024;
                if (i16 != 0) {
                }
                if ((i3 & 48) == 0) {
                }
                int i3222 = i17;
                i19 = i4 & 4096;
                if (i19 != 0) {
                }
                if ((i3 & 3072) == 0) {
                }
                i21 = i20;
                if ((i4 & 16384) == 0) {
                }
                if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            i7 = i4 & 8;
            int i312 = 1024;
            if (i7 != 0) {
            }
            i8 = i4 & 16;
            if (i8 == 0) {
            }
            i9 = i;
            i10 = i4 & 32;
            if (i10 != 0) {
            }
            i11 = i4 & 64;
            if (i11 != 0) {
            }
            if ((i2 & 12582912) == 0) {
            }
            i12 = i4 & 256;
            if (i12 != 0) {
            }
            i13 = i4 & 512;
            if (i13 != 0) {
            }
            i16 = i4 & 1024;
            if (i16 != 0) {
            }
            if ((i3 & 48) == 0) {
            }
            int i32222 = i17;
            i19 = i4 & 4096;
            if (i19 != 0) {
            }
            if ((i3 & 3072) == 0) {
            }
            i21 = i20;
            if ((i4 & 16384) == 0) {
            }
            if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i6 = i4 & 4;
        if (i6 == 0) {
        }
        i7 = i4 & 8;
        int i3122 = 1024;
        if (i7 != 0) {
        }
        i8 = i4 & 16;
        if (i8 == 0) {
        }
        i9 = i;
        i10 = i4 & 32;
        if (i10 != 0) {
        }
        i11 = i4 & 64;
        if (i11 != 0) {
        }
        if ((i2 & 12582912) == 0) {
        }
        i12 = i4 & 256;
        if (i12 != 0) {
        }
        i13 = i4 & 512;
        if (i13 != 0) {
        }
        i16 = i4 & 1024;
        if (i16 != 0) {
        }
        if ((i3 & 48) == 0) {
        }
        int i322222 = i17;
        i19 = i4 & 4096;
        if (i19 != 0) {
        }
        if ((i3 & 3072) == 0) {
        }
        i21 = i20;
        if ((i4 & 16384) == 0) {
        }
        if (startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i21 & 9363) != 9362, i15 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    /* renamed from: VerticalPager-oI3XNZo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m1185VerticalPageroI3XNZo(final PagerState pagerState, Modifier modifier, PaddingValues paddingValues, PageSize pageSize, int i, float f, Alignment.Horizontal horizontal, TargetedFlingBehavior targetedFlingBehavior, boolean z, boolean z2, Function1 function1, NestedScrollConnection nestedScrollConnection, SnapPosition snapPosition, final Function4 function4, Composer composer, final int i2, final int i3, final int i4) {
        int i5;
        Modifier modifier2;
        int i6;
        int i7;
        int i8;
        final int i9;
        int i10;
        final float f2;
        int i11;
        Alignment.Horizontal horizontal2;
        TargetedFlingBehavior targetedFlingBehavior2;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        final PaddingValues paddingValues2;
        final PageSize pageSize2;
        final boolean z3;
        final boolean z4;
        final Function1 function12;
        final NestedScrollConnection nestedScrollConnection2;
        final Modifier modifier3;
        final Alignment.Horizontal horizontal3;
        final TargetedFlingBehavior targetedFlingBehavior3;
        final SnapPosition snapPosition2;
        ScopeUpdateScope endRestartGroup;
        Modifier modifier4;
        int i21;
        PagerState pagerState2;
        int i22;
        Composer composer2;
        int i23;
        NestedScrollConnection nestedScrollConnection3;
        SnapPosition snapPosition3;
        boolean z5;
        Function1 function13;
        NestedScrollConnection nestedScrollConnection4;
        TargetedFlingBehavior targetedFlingBehavior4;
        PageSize pageSize3;
        boolean z6;
        float f3;
        PaddingValues paddingValues3;
        int i24;
        int i25;
        int i26;
        Composer startRestartGroup = composer.startRestartGroup(-1474550033);
        ComposerKt.sourceInformation(startRestartGroup, "C(VerticalPager)N(state,modifier,contentPadding,pageSize,beyondViewportPageCount,pageSpacing:c#ui.unit.Dp,horizontalAlignment,flingBehavior,userScrollEnabled,reverseLayout,key,pageNestedScrollConnection,snapPosition,pageContent)314@16567L26,300@16012L623:Pager.kt#g6yjnt");
        if ((i4 & 1) != 0) {
            i5 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i5 = (startRestartGroup.changed(pagerState) ? 4 : 2) | i2;
        } else {
            i5 = i2;
        }
        int i27 = i4 & 2;
        if (i27 != 0) {
            i5 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i5 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i6 = i4 & 4;
            if (i6 == 0) {
                i5 |= 384;
            } else if ((i2 & 384) == 0) {
                i5 |= startRestartGroup.changed(paddingValues) ? 256 : 128;
                i7 = i4 & 8;
                if (i7 != 0) {
                    i5 |= 3072;
                } else if ((i2 & 3072) == 0) {
                    i5 |= startRestartGroup.changed(pageSize) ? 2048 : 1024;
                    i8 = i4 & 16;
                    if (i8 == 0) {
                        i5 |= 24576;
                    } else if ((i2 & 24576) == 0) {
                        i9 = i;
                        i5 |= startRestartGroup.changed(i9) ? 16384 : 8192;
                        i10 = i4 & 32;
                        if (i10 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            f2 = f;
                        } else {
                            f2 = f;
                            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i5 |= startRestartGroup.changed(f2) ? 131072 : 65536;
                            }
                        }
                        i11 = i4 & 64;
                        if (i11 != 0) {
                            i5 |= 1572864;
                            horizontal2 = horizontal;
                        } else {
                            horizontal2 = horizontal;
                            if ((i2 & 1572864) == 0) {
                                i5 |= startRestartGroup.changed(horizontal2) ? 1048576 : 524288;
                            }
                        }
                        if ((i2 & 12582912) == 0) {
                            if ((i4 & 128) == 0) {
                                targetedFlingBehavior2 = targetedFlingBehavior;
                                if (startRestartGroup.changed(targetedFlingBehavior2)) {
                                    i26 = 8388608;
                                    i5 |= i26;
                                }
                            } else {
                                targetedFlingBehavior2 = targetedFlingBehavior;
                            }
                            i26 = 4194304;
                            i5 |= i26;
                        } else {
                            targetedFlingBehavior2 = targetedFlingBehavior;
                        }
                        i12 = i4 & 256;
                        if (i12 != 0) {
                            i5 |= 100663296;
                        } else if ((i2 & 100663296) == 0) {
                            i5 |= startRestartGroup.changed(z) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        }
                        i13 = i4 & 512;
                        if (i13 != 0) {
                            i15 = i5 | 805306368;
                            i14 = i13;
                        } else {
                            if ((i2 & 805306368) == 0) {
                                i14 = i13;
                                i5 |= startRestartGroup.changed(z2) ? 536870912 : 268435456;
                            } else {
                                i14 = i13;
                            }
                            i15 = i5;
                        }
                        i16 = i4 & 1024;
                        if (i16 != 0) {
                            i17 = i3 | 6;
                        } else if ((i3 & 6) == 0) {
                            i17 = i3 | (startRestartGroup.changedInstance(function1) ? 4 : 2);
                        } else {
                            i17 = i3;
                        }
                        if ((i3 & 48) == 0) {
                            i18 = i16;
                            if ((i4 & 2048) == 0 && startRestartGroup.changedInstance(nestedScrollConnection)) {
                                i25 = 32;
                                i17 |= i25;
                            }
                            i25 = 16;
                            i17 |= i25;
                        } else {
                            i18 = i16;
                        }
                        int i28 = i17;
                        i19 = i4 & 4096;
                        if (i19 != 0) {
                            i20 = i28 | 384;
                        } else {
                            int i29 = i28;
                            if ((i3 & 384) == 0) {
                                i29 |= startRestartGroup.changed(snapPosition) ? 256 : 128;
                            }
                            i20 = i29;
                        }
                        if ((i4 & 8192) != 0) {
                            i20 |= 3072;
                        } else if ((i3 & 3072) == 0) {
                            i20 |= startRestartGroup.changedInstance(function4) ? 2048 : 1024;
                            if (!startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i20 & 1171) != 1170, i15 & 1)) {
                                startRestartGroup.startDefaults();
                                ComposerKt.sourceInformation(startRestartGroup, "291@15609L28,296@15833L55");
                                if ((i2 & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i4 & 128) != 0) {
                                        i15 &= -29360129;
                                    }
                                    if ((i4 & 2048) != 0) {
                                        i20 &= -113;
                                    }
                                    pagerState2 = pagerState;
                                    paddingValues3 = paddingValues;
                                    z6 = z;
                                    z5 = z2;
                                    function13 = function1;
                                    nestedScrollConnection4 = nestedScrollConnection;
                                    snapPosition3 = snapPosition;
                                    i22 = 0;
                                    i21 = i9;
                                    f3 = f2;
                                    modifier4 = modifier2;
                                    targetedFlingBehavior4 = targetedFlingBehavior2;
                                    composer2 = startRestartGroup;
                                    i24 = i15;
                                    pageSize3 = pageSize;
                                } else {
                                    modifier4 = i27 != 0 ? Modifier.INSTANCE : modifier2;
                                    PaddingValues m871PaddingValues0680j_4 = i6 != 0 ? PaddingKt.m871PaddingValues0680j_4(Dp.m7399constructorimpl(0)) : paddingValues;
                                    PageSize pageSize4 = i7 != 0 ? PageSize.Fill.INSTANCE : pageSize;
                                    i21 = i8 != 0 ? 0 : i9;
                                    float m7399constructorimpl = i10 != 0 ? Dp.m7399constructorimpl(0) : f2;
                                    if (i11 != 0) {
                                        horizontal2 = Alignment.INSTANCE.getCenterHorizontally();
                                    }
                                    if ((i4 & 128) != 0) {
                                        composer2 = startRestartGroup;
                                        i23 = i20;
                                        pagerState2 = pagerState;
                                        TargetedFlingBehavior flingBehavior = PagerDefaults.INSTANCE.flingBehavior(pagerState2, null, null, null, 0.0f, composer2, (i15 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                        i15 &= -29360129;
                                        i22 = 0;
                                        targetedFlingBehavior2 = flingBehavior;
                                    } else {
                                        pagerState2 = pagerState;
                                        i22 = 0;
                                        composer2 = startRestartGroup;
                                        i23 = i20;
                                    }
                                    boolean z7 = i12 == 0 ? z : true;
                                    boolean z8 = i14 != 0 ? i22 == true ? 1 : 0 : z2;
                                    Function1 function14 = i18 != 0 ? null : function1;
                                    if ((i4 & 2048) != 0) {
                                        nestedScrollConnection3 = PagerDefaults.INSTANCE.pageNestedScrollConnection(pagerState2, Orientation.Vertical, composer2, (i15 & 14) | 432);
                                        i23 &= -113;
                                    } else {
                                        nestedScrollConnection3 = nestedScrollConnection;
                                    }
                                    if (i19 != 0) {
                                        z5 = z8;
                                        function13 = function14;
                                        nestedScrollConnection4 = nestedScrollConnection3;
                                        snapPosition3 = SnapPosition.Start.INSTANCE;
                                    } else {
                                        snapPosition3 = snapPosition;
                                        z5 = z8;
                                        function13 = function14;
                                        nestedScrollConnection4 = nestedScrollConnection3;
                                    }
                                    targetedFlingBehavior4 = targetedFlingBehavior2;
                                    i20 = i23;
                                    pageSize3 = pageSize4;
                                    z6 = z7;
                                    f3 = m7399constructorimpl;
                                    paddingValues3 = m871PaddingValues0680j_4;
                                    i24 = i15;
                                }
                                composer2.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1474550033, i24, i20, "androidx.compose.foundation.pager.VerticalPager (Pager.kt:299)");
                                    i22 = 0;
                                }
                                int i30 = (i20 & 1022) | ((i20 << 3) & 57344);
                                PagerState pagerState3 = pagerState2;
                                startRestartGroup = composer2;
                                Alignment.Horizontal horizontal4 = horizontal2;
                                Modifier modifier5 = modifier4;
                                int i31 = i21;
                                m1184VerticalPager8jOkeI(pagerState3, modifier5, paddingValues3, pageSize3, i31, f3, horizontal4, targetedFlingBehavior4, z6, z5, function13, nestedScrollConnection4, snapPosition3, OverscrollKt.rememberOverscrollEffect(composer2, i22), function4, startRestartGroup, i24 & 2147483646, i30, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                snapPosition2 = snapPosition3;
                                nestedScrollConnection2 = nestedScrollConnection4;
                                function12 = function13;
                                z4 = z5;
                                z3 = z6;
                                targetedFlingBehavior3 = targetedFlingBehavior4;
                                horizontal3 = horizontal4;
                                f2 = f3;
                                i9 = i31;
                                pageSize2 = pageSize3;
                                paddingValues2 = paddingValues3;
                                modifier3 = modifier5;
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                paddingValues2 = paddingValues;
                                pageSize2 = pageSize;
                                z3 = z;
                                z4 = z2;
                                function12 = function1;
                                nestedScrollConnection2 = nestedScrollConnection;
                                modifier3 = modifier2;
                                horizontal3 = horizontal2;
                                targetedFlingBehavior3 = targetedFlingBehavior2;
                                snapPosition2 = snapPosition;
                            }
                            endRestartGroup = startRestartGroup.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.pager.PagerKt$$ExternalSyntheticLambda1
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit VerticalPager_oI3XNZo$lambda$3;
                                        VerticalPager_oI3XNZo$lambda$3 = PagerKt.VerticalPager_oI3XNZo$lambda$3(PagerState.this, modifier3, paddingValues2, pageSize2, i9, f2, horizontal3, targetedFlingBehavior3, z3, z4, function12, nestedScrollConnection2, snapPosition2, function4, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                        return VerticalPager_oI3XNZo$lambda$3;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        if (!startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i20 & 1171) != 1170, i15 & 1)) {
                        }
                        endRestartGroup = startRestartGroup.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i9 = i;
                    i10 = i4 & 32;
                    if (i10 != 0) {
                    }
                    i11 = i4 & 64;
                    if (i11 != 0) {
                    }
                    if ((i2 & 12582912) == 0) {
                    }
                    i12 = i4 & 256;
                    if (i12 != 0) {
                    }
                    i13 = i4 & 512;
                    if (i13 != 0) {
                    }
                    i16 = i4 & 1024;
                    if (i16 != 0) {
                    }
                    if ((i3 & 48) == 0) {
                    }
                    int i282 = i17;
                    i19 = i4 & 4096;
                    if (i19 != 0) {
                    }
                    if ((i4 & 8192) != 0) {
                    }
                    if (!startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i20 & 1171) != 1170, i15 & 1)) {
                    }
                    endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                i8 = i4 & 16;
                if (i8 == 0) {
                }
                i9 = i;
                i10 = i4 & 32;
                if (i10 != 0) {
                }
                i11 = i4 & 64;
                if (i11 != 0) {
                }
                if ((i2 & 12582912) == 0) {
                }
                i12 = i4 & 256;
                if (i12 != 0) {
                }
                i13 = i4 & 512;
                if (i13 != 0) {
                }
                i16 = i4 & 1024;
                if (i16 != 0) {
                }
                if ((i3 & 48) == 0) {
                }
                int i2822 = i17;
                i19 = i4 & 4096;
                if (i19 != 0) {
                }
                if ((i4 & 8192) != 0) {
                }
                if (!startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i20 & 1171) != 1170, i15 & 1)) {
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            i7 = i4 & 8;
            if (i7 != 0) {
            }
            i8 = i4 & 16;
            if (i8 == 0) {
            }
            i9 = i;
            i10 = i4 & 32;
            if (i10 != 0) {
            }
            i11 = i4 & 64;
            if (i11 != 0) {
            }
            if ((i2 & 12582912) == 0) {
            }
            i12 = i4 & 256;
            if (i12 != 0) {
            }
            i13 = i4 & 512;
            if (i13 != 0) {
            }
            i16 = i4 & 1024;
            if (i16 != 0) {
            }
            if ((i3 & 48) == 0) {
            }
            int i28222 = i17;
            i19 = i4 & 4096;
            if (i19 != 0) {
            }
            if ((i4 & 8192) != 0) {
            }
            if (!startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i20 & 1171) != 1170, i15 & 1)) {
            }
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i6 = i4 & 4;
        if (i6 == 0) {
        }
        i7 = i4 & 8;
        if (i7 != 0) {
        }
        i8 = i4 & 16;
        if (i8 == 0) {
        }
        i9 = i;
        i10 = i4 & 32;
        if (i10 != 0) {
        }
        i11 = i4 & 64;
        if (i11 != 0) {
        }
        if ((i2 & 12582912) == 0) {
        }
        i12 = i4 & 256;
        if (i12 != 0) {
        }
        i13 = i4 & 512;
        if (i13 != 0) {
        }
        i16 = i4 & 1024;
        if (i16 != 0) {
        }
        if ((i3 & 48) == 0) {
        }
        int i282222 = i17;
        i19 = i4 & 4096;
        if (i19 != 0) {
        }
        if ((i4 & 8192) != 0) {
        }
        if (!startRestartGroup.shouldExecute((i15 & 306783379) == 306783378 || (i20 & 1171) != 1170, i15 & 1)) {
        }
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    public static final int currentPageOffset(SnapPosition snapPosition, int i, int i2, int i3, int i4, int i5, int i6, float f, int i7) {
        return MathKt.roundToInt(snapPosition.position(i, i2, i4, i5, i6, i7) - (f * (i2 + i3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean pagerSemantics$performForwardPaging(PagerState pagerState, CoroutineScope coroutineScope) {
        if (!pagerState.getCanScrollForward()) {
            return false;
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new PagerKt$pagerSemantics$performForwardPaging$1(pagerState, null), 3, null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean pagerSemantics$performBackwardPaging(PagerState pagerState, CoroutineScope coroutineScope) {
        if (!pagerState.getCanScrollBackward()) {
            return false;
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new PagerKt$pagerSemantics$performBackwardPaging$1(pagerState, null), 3, null);
        return true;
    }

    public static final Modifier pagerSemantics(Modifier modifier, final PagerState pagerState, final boolean z, final CoroutineScope coroutineScope, boolean z2) {
        if (z2) {
            return modifier.then(SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1() { // from class: androidx.compose.foundation.pager.PagerKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit pagerSemantics$lambda$8;
                    pagerSemantics$lambda$8 = PagerKt.pagerSemantics$lambda$8(z, pagerState, coroutineScope, (SemanticsPropertyReceiver) obj);
                    return pagerSemantics$lambda$8;
                }
            }, 1, null));
        }
        return modifier.then(Modifier.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit pagerSemantics$lambda$8(boolean z, final PagerState pagerState, final CoroutineScope coroutineScope, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (z) {
            SemanticsPropertiesKt.pageUp$default(semanticsPropertyReceiver, null, new Function0() { // from class: androidx.compose.foundation.pager.PagerKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    boolean pagerSemantics$performBackwardPaging;
                    pagerSemantics$performBackwardPaging = PagerKt.pagerSemantics$performBackwardPaging(PagerState.this, coroutineScope);
                    return Boolean.valueOf(pagerSemantics$performBackwardPaging);
                }
            }, 1, null);
            SemanticsPropertiesKt.pageDown$default(semanticsPropertyReceiver, null, new Function0() { // from class: androidx.compose.foundation.pager.PagerKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    boolean pagerSemantics$performForwardPaging;
                    pagerSemantics$performForwardPaging = PagerKt.pagerSemantics$performForwardPaging(PagerState.this, coroutineScope);
                    return Boolean.valueOf(pagerSemantics$performForwardPaging);
                }
            }, 1, null);
        } else {
            SemanticsPropertiesKt.pageLeft$default(semanticsPropertyReceiver, null, new Function0() { // from class: androidx.compose.foundation.pager.PagerKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    boolean pagerSemantics$performBackwardPaging;
                    pagerSemantics$performBackwardPaging = PagerKt.pagerSemantics$performBackwardPaging(PagerState.this, coroutineScope);
                    return Boolean.valueOf(pagerSemantics$performBackwardPaging);
                }
            }, 1, null);
            SemanticsPropertiesKt.pageRight$default(semanticsPropertyReceiver, null, new Function0() { // from class: androidx.compose.foundation.pager.PagerKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    boolean pagerSemantics$performForwardPaging;
                    pagerSemantics$performForwardPaging = PagerKt.pagerSemantics$performForwardPaging(PagerState.this, coroutineScope);
                    return Boolean.valueOf(pagerSemantics$performForwardPaging);
                }
            }, 1, null);
        }
        return Unit.INSTANCE;
    }
}
