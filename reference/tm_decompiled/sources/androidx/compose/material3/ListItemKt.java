package androidx.compose.material3;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.tokens.ListTokens;
import androidx.compose.material3.tokens.TypographyKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: ListItem.kt */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a©\u0001\u0010\u0016\u001a\u00020\u00172\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\u00170\u0019¢\u0006\u0002\b\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0015\b\u0002\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0015\b\u0002\u0010\u001e\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0015\b\u0002\u0010\u001f\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0015\b\u0002\u0010 \u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u00012\b\b\u0002\u0010$\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001at\u0010'\u001a\u00020\u00172\u0013\u0010(\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0013\u0010)\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0011\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00170\u0019¢\u0006\u0002\b\u001a2\u0013\u0010+\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0013\u0010,\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001aH\u0003¢\u0006\u0002\u0010-\u001a5\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0011\u00103\u001a\r\u0012\u0004\u0012\u00020\u00170\u0019¢\u0006\u0002\b\u001aH\u0003ø\u0001\u0000¢\u0006\u0004\b4\u00105\u001a\u001a\u00106\u001a\u00020\u00012\u0006\u00107\u001a\u000208H\u0002ø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001aV\u0010;\u001a\u00020<*\u00020=2\u0006\u0010>\u001a\u00020<2\u0006\u0010?\u001a\u00020<2\u0006\u0010@\u001a\u00020<2\u0006\u0010A\u001a\u00020<2\u0006\u0010B\u001a\u00020<2\u0006\u00107\u001a\u0002082\u0006\u00106\u001a\u00020<2\u0006\u0010C\u001a\u00020DH\u0002ø\u0001\u0000¢\u0006\u0004\bE\u0010F\u001aN\u0010G\u001a\u00020<*\u00020=2\u0006\u0010H\u001a\u00020<2\u0006\u0010I\u001a\u00020<2\u0006\u0010J\u001a\u00020<2\u0006\u0010K\u001a\u00020<2\u0006\u0010L\u001a\u00020<2\u0006\u0010M\u001a\u00020<2\u0006\u0010C\u001a\u00020DH\u0002ø\u0001\u0000¢\u0006\u0004\bN\u0010O\u001a\u0014\u0010P\u001a\u00020Q*\u00020R2\u0006\u0010S\u001a\u00020<H\u0002\u001an\u0010T\u001a\u00020U*\u00020V2\u0006\u0010W\u001a\u00020<2\u0006\u0010X\u001a\u00020<2\b\u0010Y\u001a\u0004\u0018\u00010Z2\b\u0010[\u001a\u0004\u0018\u00010Z2\b\u0010\\\u001a\u0004\u0018\u00010Z2\b\u0010]\u001a\u0004\u0018\u00010Z2\b\u0010^\u001a\u0004\u0018\u00010Z2\u0006\u0010_\u001a\u00020Q2\u0006\u0010`\u001a\u00020<2\u0006\u0010a\u001a\u00020<2\u0006\u0010b\u001a\u00020<H\u0002\u001a\u0014\u0010c\u001a\u00020<*\u00020<2\u0006\u0010d\u001a\u00020<H\u0002\"\u001e\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001e\u0010\u0007\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\t\u0010\u0005\"\u001e\u0010\n\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\u0005\"\u001e\u0010\r\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\u0005\"\u001e\u0010\u0010\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u0012\u0010\u0005\"\u001e\u0010\u0013\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0015\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006e"}, d2 = {"LeadingContentEndPadding", "Landroidx/compose/ui/unit/Dp;", "getLeadingContentEndPadding$annotations", "()V", "getLeadingContentEndPadding", "()F", "F", "ListItemEndPadding", "getListItemEndPadding$annotations", "getListItemEndPadding", "ListItemStartPadding", "getListItemStartPadding$annotations", "getListItemStartPadding", "ListItemThreeLineVerticalPadding", "getListItemThreeLineVerticalPadding$annotations", "getListItemThreeLineVerticalPadding", "ListItemVerticalPadding", "getListItemVerticalPadding$annotations", "getListItemVerticalPadding", "TrailingContentStartPadding", "getTrailingContentStartPadding$annotations", "getTrailingContentStartPadding", "ListItem", "", "headlineContent", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "overlineContent", "supportingContent", "leadingContent", "trailingContent", "colors", "Landroidx/compose/material3/ListItemColors;", "tonalElevation", "shadowElevation", "ListItem-HXNGIdc", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/ListItemColors;FFLandroidx/compose/runtime/Composer;II)V", "ListItemLayout", "leading", "trailing", "headline", "overline", "supporting", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "ProvideTextStyleFromToken", "color", "Landroidx/compose/ui/graphics/Color;", "textToken", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "content", "ProvideTextStyleFromToken-3J-VO9M", "(JLandroidx/compose/material3/tokens/TypographyKeyTokens;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "verticalPadding", "listItemType", "Landroidx/compose/material3/ListItemType;", "verticalPadding-yh95HIg", "(I)F", "calculateHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "leadingHeight", "trailingHeight", "headlineHeight", "overlineHeight", "supportingHeight", "constraints", "Landroidx/compose/ui/unit/Constraints;", "calculateHeight-N4Jib3Y", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;IIIIIIIJ)I", "calculateWidth", "leadingWidth", "trailingWidth", "headlineWidth", "overlineWidth", "supportingWidth", "horizontalPadding", "calculateWidth-yeHjK3Y", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;IIIIIIJ)I", "isSupportingMultilineHeuristic", "", "Landroidx/compose/ui/unit/Density;", "estimatedSupportingHeight", "place", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "width", "height", "leadingPlaceable", "Landroidx/compose/ui/layout/Placeable;", "trailingPlaceable", "headlinePlaceable", "overlinePlaceable", "supportingPlaceable", "isThreeLine", "startPadding", "endPadding", "topPadding", "subtractConstraintSafely", "n", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ListItemKt {
    private static final float LeadingContentEndPadding;
    private static final float ListItemEndPadding;
    private static final float ListItemStartPadding;
    private static final float TrailingContentStartPadding;
    private static final float ListItemVerticalPadding = Dp.m7399constructorimpl(8);
    private static final float ListItemThreeLineVerticalPadding = Dp.m7399constructorimpl(12);

    public static /* synthetic */ void getLeadingContentEndPadding$annotations() {
    }

    public static /* synthetic */ void getListItemEndPadding$annotations() {
    }

    public static /* synthetic */ void getListItemStartPadding$annotations() {
    }

    public static /* synthetic */ void getListItemThreeLineVerticalPadding$annotations() {
    }

    public static /* synthetic */ void getListItemVerticalPadding$annotations() {
    }

    public static /* synthetic */ void getTrailingContentStartPadding$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int subtractConstraintSafely(int i, int i2) {
        return i == Integer.MAX_VALUE ? i : i - i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01be  */
    /* renamed from: ListItem-HXNGIdc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2351ListItemHXNGIdc(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, ListItemColors listItemColors, float f, float f2, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        final Function2<? super Composer, ? super Integer, Unit> function26;
        int i5;
        final Function2<? super Composer, ? super Integer, Unit> function27;
        int i6;
        int i7;
        Function2<? super Composer, ? super Integer, Unit> function28;
        int i8;
        int i9;
        int i10;
        int i11;
        float f3;
        Composer composer2;
        final Function2<? super Composer, ? super Integer, Unit> function29;
        final Function2<? super Composer, ? super Integer, Unit> function210;
        int i12;
        int i13;
        Composer composer3;
        ListItemColors listItemColors2;
        float f4;
        float f5;
        final ListItemColors listItemColors3;
        Function2<? super Composer, ? super Integer, Unit> function211;
        ComposableLambda rememberComposableLambda;
        final ComposableLambda composableLambda;
        final Function2<? super Composer, ? super Integer, Unit> function212;
        final Function2<? super Composer, ? super Integer, Unit> function213;
        final Function2<? super Composer, ? super Integer, Unit> function214;
        final float f6;
        final float f7;
        final ListItemColors listItemColors4;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function215;
        ScopeUpdateScope endRestartGroup;
        Composer startRestartGroup = composer.startRestartGroup(-1647707763);
        ComposerKt.sourceInformation(startRestartGroup, "C(ListItem)P(1,3,4,6,2,8!1,7:c#ui.unit.Dp,5:c#ui.unit.Dp)100@4555L8,104@4735L176,157@6562L5,162@6761L288,155@6438L611:ListItem.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i14 = i2 & 2;
        if (i14 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i4 = i2 & 4;
            if (i4 == 0) {
                i3 |= 384;
            } else if ((i & 384) == 0) {
                function26 = function22;
                i3 |= startRestartGroup.changedInstance(function26) ? 256 : 128;
                i5 = i2 & 8;
                if (i5 != 0) {
                    i3 |= 3072;
                } else if ((i & 3072) == 0) {
                    function27 = function23;
                    i3 |= startRestartGroup.changedInstance(function27) ? 2048 : 1024;
                    i6 = i2 & 16;
                    if (i6 == 0) {
                        i3 |= 24576;
                    } else if ((i & 24576) == 0) {
                        i3 |= startRestartGroup.changedInstance(function24) ? 16384 : 8192;
                        i7 = i2 & 32;
                        if (i7 != 0) {
                            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((196608 & i) == 0) {
                            function28 = function25;
                            i3 |= startRestartGroup.changedInstance(function28) ? 131072 : 65536;
                            if ((i & 1572864) == 0) {
                                i3 |= ((i2 & 64) == 0 && startRestartGroup.changed(listItemColors)) ? 1048576 : 524288;
                            }
                            i8 = i2 & 128;
                            if (i8 == 0) {
                                i3 |= 12582912;
                            } else if ((i & 12582912) == 0) {
                                i9 = i8;
                                i3 |= startRestartGroup.changed(f) ? 8388608 : 4194304;
                                i10 = i2 & 256;
                                if (i10 != 0) {
                                    i3 |= 100663296;
                                } else if ((i & 100663296) == 0) {
                                    i11 = i10;
                                    f3 = f2;
                                    i3 |= startRestartGroup.changed(f3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                    composer2 = startRestartGroup;
                                    if ((i3 & 38347923) == 38347922 || !composer2.getSkipping()) {
                                        composer2.startDefaults();
                                        ComposableLambda composableLambda2 = null;
                                        if ((i & 1) != 0 || composer2.getDefaultsInvalid()) {
                                            if (i14 != 0) {
                                                modifier2 = Modifier.INSTANCE;
                                            }
                                            if (i4 != 0) {
                                                function26 = null;
                                            }
                                            if (i5 != 0) {
                                                function27 = null;
                                            }
                                            function29 = i6 == 0 ? null : function24;
                                            function210 = i7 == 0 ? null : function28;
                                            if ((i2 & 64) == 0) {
                                                i12 = i9;
                                                i13 = i11;
                                                listItemColors2 = ListItemDefaults.INSTANCE.m2349colorsJ08w3E(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer2, 805306368, 511);
                                                composer3 = composer2;
                                                i3 &= -3670017;
                                            } else {
                                                i12 = i9;
                                                i13 = i11;
                                                composer3 = composer2;
                                                listItemColors2 = listItemColors;
                                            }
                                            float m2350getElevationD9Ej5fM = i12 == 0 ? ListItemDefaults.INSTANCE.m2350getElevationD9Ej5fM() : f;
                                            if (i13 == 0) {
                                                f5 = m2350getElevationD9Ej5fM;
                                                f4 = ListItemDefaults.INSTANCE.m2350getElevationD9Ej5fM();
                                            } else {
                                                f4 = f2;
                                                f5 = m2350getElevationD9Ej5fM;
                                            }
                                            listItemColors3 = listItemColors2;
                                        } else {
                                            composer2.skipToGroupEnd();
                                            if ((i2 & 64) != 0) {
                                                i3 &= -3670017;
                                            }
                                            function29 = function24;
                                            listItemColors3 = listItemColors;
                                            f5 = f;
                                            f4 = f3;
                                            function210 = function28;
                                            composer3 = composer2;
                                        }
                                        composer3.endDefaults();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1647707763, i3, -1, "androidx.compose.material3.ListItem (ListItem.kt:103)");
                                        }
                                        final ComposableLambda rememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-403249643, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedHeadlineContent$1
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

                                            public final void invoke(Composer composer4, int i15) {
                                                ComposerKt.sourceInformation(composer4, "C105@4745L160:ListItem.kt#uh7d8r");
                                                if ((i15 & 3) != 2 || !composer4.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-403249643, i15, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:105)");
                                                    }
                                                    ListItemKt.m2352ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2344headlineColorvNxB06k$material3_release(true), ListTokens.INSTANCE.getListItemLabelTextFont(), function2, composer4, 48);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer4.skipToGroupEnd();
                                            }
                                        }, composer3, 54);
                                        composer3.startReplaceGroup(1640970492);
                                        ComposerKt.sourceInformation(composer3, "*113@5021L204");
                                        ComposableLambda rememberComposableLambda3 = function27 != null ? null : ComposableLambdaKt.rememberComposableLambda(-1020860251, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedSupportingContent$1$1
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

                                            public final void invoke(Composer composer4, int i15) {
                                                ComposerKt.sourceInformation(composer4, "C114@5039L172:ListItem.kt#uh7d8r");
                                                if ((i15 & 3) != 2 || !composer4.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-1020860251, i15, -1, "androidx.compose.material3.ListItem.<anonymous>.<anonymous> (ListItem.kt:114)");
                                                    }
                                                    ListItemKt.m2352ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2347supportingColor0d7_KjU$material3_release(), ListTokens.INSTANCE.getListItemSupportingTextFont(), function27, composer4, 48);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer4.skipToGroupEnd();
                                            }
                                        }, composer3, 54);
                                        composer3.endReplaceGroup();
                                        composer3.startReplaceGroup(1640980724);
                                        ComposerKt.sourceInformation(composer3, "*123@5341L196");
                                        ComposableLambda rememberComposableLambda4 = function26 != null ? null : ComposableLambdaKt.rememberComposableLambda(-764441232, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedOverlineContent$1$1
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

                                            public final void invoke(Composer composer4, int i15) {
                                                ComposerKt.sourceInformation(composer4, "C124@5359L164:ListItem.kt#uh7d8r");
                                                if ((i15 & 3) != 2 || !composer4.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-764441232, i15, -1, "androidx.compose.material3.ListItem.<anonymous>.<anonymous> (ListItem.kt:124)");
                                                    }
                                                    ListItemKt.m2352ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2346overlineColor0d7_KjU$material3_release(), ListTokens.INSTANCE.getListItemOverlineFont(), function26, composer4, 48);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer4.skipToGroupEnd();
                                            }
                                        }, composer3, 54);
                                        composer3.endReplaceGroup();
                                        composer3.startReplaceGroup(1640990750);
                                        ComposerKt.sourceInformation(composer3, "*133@5651L302");
                                        if (function29 != null) {
                                            function211 = function29;
                                            rememberComposableLambda = null;
                                        } else {
                                            function211 = function29;
                                            rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1400509200, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedLeadingContent$1$1
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

                                                public final void invoke(Composer composer4, int i15) {
                                                    ComposerKt.sourceInformation(composer4, "C134@5669L270:ListItem.kt#uh7d8r");
                                                    if ((i15 & 3) != 2 || !composer4.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(1400509200, i15, -1, "androidx.compose.material3.ListItem.<anonymous>.<anonymous> (ListItem.kt:134)");
                                                        }
                                                        Modifier m882paddingqDBjuR0$default = PaddingKt.m882paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, ListItemKt.getLeadingContentEndPadding(), 0.0f, 11, null);
                                                        ListItemColors listItemColors5 = ListItemColors.this;
                                                        Function2<Composer, Integer, Unit> function216 = function29;
                                                        ComposerKt.sourceInformationMarkerStart(composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                        ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer4, m882paddingqDBjuR0$default);
                                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                        ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                        if (!(composer4.getApplier() instanceof Applier)) {
                                                            ComposablesKt.invalidApplier();
                                                        }
                                                        composer4.startReusableNode();
                                                        if (composer4.getInserting()) {
                                                            composer4.createNode(constructor);
                                                        } else {
                                                            composer4.useNode();
                                                        }
                                                        Composer m3905constructorimpl = Updater.m3905constructorimpl(composer4);
                                                        Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                        if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                            m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                            m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                                        }
                                                        Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                                        ComposerKt.sourceInformationMarkerStart(composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                        ComposerKt.sourceInformationMarkerStart(composer4, 690160162, "C135@5745L176:ListItem.kt#uh7d8r");
                                                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(listItemColors5.m2345leadingIconColorvNxB06k$material3_release(true))), function216, composer4, ProvidedValue.$stable);
                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                        composer4.endNode();
                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer4.skipToGroupEnd();
                                                }
                                            }, composer3, 54);
                                        }
                                        composer3.endReplaceGroup();
                                        composer3.startReplaceGroup(1641004177);
                                        ComposerKt.sourceInformation(composer3, "*144@6069L353");
                                        if (function210 != null) {
                                            composableLambda = rememberComposableLambda;
                                        } else {
                                            composableLambda = rememberComposableLambda;
                                            composableLambda2 = ComposableLambdaKt.rememberComposableLambda(1512306332, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedTrailingContent$1$1
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

                                                public final void invoke(Composer composer4, int i15) {
                                                    ComposerKt.sourceInformation(composer4, "C145@6087L321:ListItem.kt#uh7d8r");
                                                    if ((i15 & 3) != 2 || !composer4.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(1512306332, i15, -1, "androidx.compose.material3.ListItem.<anonymous>.<anonymous> (ListItem.kt:145)");
                                                        }
                                                        Modifier m882paddingqDBjuR0$default = PaddingKt.m882paddingqDBjuR0$default(Modifier.INSTANCE, ListItemKt.getTrailingContentStartPadding(), 0.0f, 0.0f, 0.0f, 14, null);
                                                        ListItemColors listItemColors5 = ListItemColors.this;
                                                        Function2<Composer, Integer, Unit> function216 = function210;
                                                        ComposerKt.sourceInformationMarkerStart(composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                        ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer4, m882paddingqDBjuR0$default);
                                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                        ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                        if (!(composer4.getApplier() instanceof Applier)) {
                                                            ComposablesKt.invalidApplier();
                                                        }
                                                        composer4.startReusableNode();
                                                        if (composer4.getInserting()) {
                                                            composer4.createNode(constructor);
                                                        } else {
                                                            composer4.useNode();
                                                        }
                                                        Composer m3905constructorimpl = Updater.m3905constructorimpl(composer4);
                                                        Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                        if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                            m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                            m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                                        }
                                                        Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                                        ComposerKt.sourceInformationMarkerStart(composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                        ComposerKt.sourceInformationMarkerStart(composer4, 690581204, "C146@6168L222:ListItem.kt#uh7d8r");
                                                        ListItemKt.m2352ProvideTextStyleFromToken3JVO9M(listItemColors5.m2348trailingIconColorvNxB06k$material3_release(true), ListTokens.INSTANCE.getListItemTrailingSupportingTextFont(), function216, composer4, 48);
                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                        composer4.endNode();
                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer4.skipToGroupEnd();
                                                }
                                            }, composer3, 54);
                                        }
                                        composer3.endReplaceGroup();
                                        final ComposableLambda composableLambda3 = rememberComposableLambda4;
                                        final ComposableLambda composableLambda4 = rememberComposableLambda3;
                                        final ComposableLambda composableLambda5 = composableLambda2;
                                        int i15 = i3 >> 9;
                                        composer2 = composer3;
                                        SurfaceKt.m2699SurfaceT9BRK9s(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$1
                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }
                                        }).then(modifier2), ListItemDefaults.INSTANCE.getShape(composer3, 6), listItemColors3.getContainerColor(), listItemColors3.m2344headlineColorvNxB06k$material3_release(true), f5, f4, null, ComposableLambdaKt.rememberComposableLambda(1502590376, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$2
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

                                            public final void invoke(Composer composer4, int i16) {
                                                ComposerKt.sourceInformation(composer4, "C163@6771L272:ListItem.kt#uh7d8r");
                                                if ((i16 & 3) != 2 || !composer4.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(1502590376, i16, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:163)");
                                                    }
                                                    ListItemKt.ListItemLayout(composableLambda, composableLambda5, rememberComposableLambda2, composableLambda3, composableLambda4, composer4, 384);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer4.skipToGroupEnd();
                                            }
                                        }, composer3, 54), composer2, (57344 & i15) | 12582912 | (i15 & 458752), 64);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        function212 = function210;
                                        function213 = function26;
                                        function214 = function27;
                                        f6 = f5;
                                        f7 = f4;
                                        listItemColors4 = listItemColors3;
                                        modifier3 = modifier2;
                                        function215 = function211;
                                    } else {
                                        composer2.skipToGroupEnd();
                                        f6 = f;
                                        modifier3 = modifier2;
                                        function213 = function26;
                                        function214 = function27;
                                        f7 = f3;
                                        function212 = function28;
                                        function215 = function24;
                                        listItemColors4 = listItemColors;
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup == null) {
                                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$3
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

                                            public final void invoke(Composer composer4, int i16) {
                                                ListItemKt.m2351ListItemHXNGIdc(function2, modifier3, function213, function214, function215, function212, listItemColors4, f6, f7, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                            }
                                        });
                                        return;
                                    }
                                    return;
                                }
                                i11 = i10;
                                f3 = f2;
                                composer2 = startRestartGroup;
                                if ((i3 & 38347923) == 38347922) {
                                }
                                composer2.startDefaults();
                                ComposableLambda composableLambda22 = null;
                                if ((i & 1) != 0) {
                                }
                                if (i14 != 0) {
                                }
                                if (i4 != 0) {
                                }
                                if (i5 != 0) {
                                }
                                if (i6 == 0) {
                                }
                                if (i7 == 0) {
                                }
                                if ((i2 & 64) == 0) {
                                }
                                if (i12 == 0) {
                                }
                                if (i13 == 0) {
                                }
                                listItemColors3 = listItemColors2;
                                composer3.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                final Function2<? super Composer, ? super Integer, Unit> rememberComposableLambda22 = ComposableLambdaKt.rememberComposableLambda(-403249643, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedHeadlineContent$1
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

                                    public final void invoke(Composer composer4, int i152) {
                                        ComposerKt.sourceInformation(composer4, "C105@4745L160:ListItem.kt#uh7d8r");
                                        if ((i152 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-403249643, i152, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:105)");
                                            }
                                            ListItemKt.m2352ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2344headlineColorvNxB06k$material3_release(true), ListTokens.INSTANCE.getListItemLabelTextFont(), function2, composer4, 48);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54);
                                composer3.startReplaceGroup(1640970492);
                                ComposerKt.sourceInformation(composer3, "*113@5021L204");
                                if (function27 != null) {
                                }
                                composer3.endReplaceGroup();
                                composer3.startReplaceGroup(1640980724);
                                ComposerKt.sourceInformation(composer3, "*123@5341L196");
                                if (function26 != null) {
                                }
                                composer3.endReplaceGroup();
                                composer3.startReplaceGroup(1640990750);
                                ComposerKt.sourceInformation(composer3, "*133@5651L302");
                                if (function29 != null) {
                                }
                                composer3.endReplaceGroup();
                                composer3.startReplaceGroup(1641004177);
                                ComposerKt.sourceInformation(composer3, "*144@6069L353");
                                if (function210 != null) {
                                }
                                composer3.endReplaceGroup();
                                final Function2<? super Composer, ? super Integer, Unit> composableLambda32 = rememberComposableLambda4;
                                final Function2<? super Composer, ? super Integer, Unit> composableLambda42 = rememberComposableLambda3;
                                final Function2<? super Composer, ? super Integer, Unit> composableLambda52 = composableLambda22;
                                int i152 = i3 >> 9;
                                composer2 = composer3;
                                SurfaceKt.m2699SurfaceT9BRK9s(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$1
                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }
                                }).then(modifier2), ListItemDefaults.INSTANCE.getShape(composer3, 6), listItemColors3.getContainerColor(), listItemColors3.m2344headlineColorvNxB06k$material3_release(true), f5, f4, null, ComposableLambdaKt.rememberComposableLambda(1502590376, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$2
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

                                    public final void invoke(Composer composer4, int i16) {
                                        ComposerKt.sourceInformation(composer4, "C163@6771L272:ListItem.kt#uh7d8r");
                                        if ((i16 & 3) != 2 || !composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1502590376, i16, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:163)");
                                            }
                                            ListItemKt.ListItemLayout(composableLambda, composableLambda52, rememberComposableLambda22, composableLambda32, composableLambda42, composer4, 384);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer4.skipToGroupEnd();
                                    }
                                }, composer3, 54), composer2, (57344 & i152) | 12582912 | (i152 & 458752), 64);
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                function212 = function210;
                                function213 = function26;
                                function214 = function27;
                                f6 = f5;
                                f7 = f4;
                                listItemColors4 = listItemColors3;
                                modifier3 = modifier2;
                                function215 = function211;
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup == null) {
                                }
                            }
                            i9 = i8;
                            i10 = i2 & 256;
                            if (i10 != 0) {
                            }
                            i11 = i10;
                            f3 = f2;
                            composer2 = startRestartGroup;
                            if ((i3 & 38347923) == 38347922) {
                            }
                            composer2.startDefaults();
                            ComposableLambda composableLambda222 = null;
                            if ((i & 1) != 0) {
                            }
                            if (i14 != 0) {
                            }
                            if (i4 != 0) {
                            }
                            if (i5 != 0) {
                            }
                            if (i6 == 0) {
                            }
                            if (i7 == 0) {
                            }
                            if ((i2 & 64) == 0) {
                            }
                            if (i12 == 0) {
                            }
                            if (i13 == 0) {
                            }
                            listItemColors3 = listItemColors2;
                            composer3.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            final Function2<? super Composer, ? super Integer, Unit> rememberComposableLambda222 = ComposableLambdaKt.rememberComposableLambda(-403249643, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedHeadlineContent$1
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

                                public final void invoke(Composer composer4, int i1522) {
                                    ComposerKt.sourceInformation(composer4, "C105@4745L160:ListItem.kt#uh7d8r");
                                    if ((i1522 & 3) != 2 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-403249643, i1522, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:105)");
                                        }
                                        ListItemKt.m2352ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2344headlineColorvNxB06k$material3_release(true), ListTokens.INSTANCE.getListItemLabelTextFont(), function2, composer4, 48);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54);
                            composer3.startReplaceGroup(1640970492);
                            ComposerKt.sourceInformation(composer3, "*113@5021L204");
                            if (function27 != null) {
                            }
                            composer3.endReplaceGroup();
                            composer3.startReplaceGroup(1640980724);
                            ComposerKt.sourceInformation(composer3, "*123@5341L196");
                            if (function26 != null) {
                            }
                            composer3.endReplaceGroup();
                            composer3.startReplaceGroup(1640990750);
                            ComposerKt.sourceInformation(composer3, "*133@5651L302");
                            if (function29 != null) {
                            }
                            composer3.endReplaceGroup();
                            composer3.startReplaceGroup(1641004177);
                            ComposerKt.sourceInformation(composer3, "*144@6069L353");
                            if (function210 != null) {
                            }
                            composer3.endReplaceGroup();
                            final Function2<? super Composer, ? super Integer, Unit> composableLambda322 = rememberComposableLambda4;
                            final Function2<? super Composer, ? super Integer, Unit> composableLambda422 = rememberComposableLambda3;
                            final Function2<? super Composer, ? super Integer, Unit> composableLambda522 = composableLambda222;
                            int i1522 = i3 >> 9;
                            composer2 = composer3;
                            SurfaceKt.m2699SurfaceT9BRK9s(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$1
                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }
                            }).then(modifier2), ListItemDefaults.INSTANCE.getShape(composer3, 6), listItemColors3.getContainerColor(), listItemColors3.m2344headlineColorvNxB06k$material3_release(true), f5, f4, null, ComposableLambdaKt.rememberComposableLambda(1502590376, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$2
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

                                public final void invoke(Composer composer4, int i16) {
                                    ComposerKt.sourceInformation(composer4, "C163@6771L272:ListItem.kt#uh7d8r");
                                    if ((i16 & 3) != 2 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1502590376, i16, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:163)");
                                        }
                                        ListItemKt.ListItemLayout(composableLambda, composableLambda522, rememberComposableLambda222, composableLambda322, composableLambda422, composer4, 384);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54), composer2, (57344 & i1522) | 12582912 | (i1522 & 458752), 64);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            function212 = function210;
                            function213 = function26;
                            function214 = function27;
                            f6 = f5;
                            f7 = f4;
                            listItemColors4 = listItemColors3;
                            modifier3 = modifier2;
                            function215 = function211;
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                            }
                        }
                        function28 = function25;
                        if ((i & 1572864) == 0) {
                        }
                        i8 = i2 & 128;
                        if (i8 == 0) {
                        }
                        i9 = i8;
                        i10 = i2 & 256;
                        if (i10 != 0) {
                        }
                        i11 = i10;
                        f3 = f2;
                        composer2 = startRestartGroup;
                        if ((i3 & 38347923) == 38347922) {
                        }
                        composer2.startDefaults();
                        ComposableLambda composableLambda2222 = null;
                        if ((i & 1) != 0) {
                        }
                        if (i14 != 0) {
                        }
                        if (i4 != 0) {
                        }
                        if (i5 != 0) {
                        }
                        if (i6 == 0) {
                        }
                        if (i7 == 0) {
                        }
                        if ((i2 & 64) == 0) {
                        }
                        if (i12 == 0) {
                        }
                        if (i13 == 0) {
                        }
                        listItemColors3 = listItemColors2;
                        composer3.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        final Function2<? super Composer, ? super Integer, Unit> rememberComposableLambda2222 = ComposableLambdaKt.rememberComposableLambda(-403249643, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedHeadlineContent$1
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

                            public final void invoke(Composer composer4, int i15222) {
                                ComposerKt.sourceInformation(composer4, "C105@4745L160:ListItem.kt#uh7d8r");
                                if ((i15222 & 3) != 2 || !composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-403249643, i15222, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:105)");
                                    }
                                    ListItemKt.m2352ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2344headlineColorvNxB06k$material3_release(true), ListTokens.INSTANCE.getListItemLabelTextFont(), function2, composer4, 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer4.skipToGroupEnd();
                            }
                        }, composer3, 54);
                        composer3.startReplaceGroup(1640970492);
                        ComposerKt.sourceInformation(composer3, "*113@5021L204");
                        if (function27 != null) {
                        }
                        composer3.endReplaceGroup();
                        composer3.startReplaceGroup(1640980724);
                        ComposerKt.sourceInformation(composer3, "*123@5341L196");
                        if (function26 != null) {
                        }
                        composer3.endReplaceGroup();
                        composer3.startReplaceGroup(1640990750);
                        ComposerKt.sourceInformation(composer3, "*133@5651L302");
                        if (function29 != null) {
                        }
                        composer3.endReplaceGroup();
                        composer3.startReplaceGroup(1641004177);
                        ComposerKt.sourceInformation(composer3, "*144@6069L353");
                        if (function210 != null) {
                        }
                        composer3.endReplaceGroup();
                        final Function2<? super Composer, ? super Integer, Unit> composableLambda3222 = rememberComposableLambda4;
                        final Function2<? super Composer, ? super Integer, Unit> composableLambda4222 = rememberComposableLambda3;
                        final Function2<? super Composer, ? super Integer, Unit> composableLambda5222 = composableLambda2222;
                        int i15222 = i3 >> 9;
                        composer2 = composer3;
                        SurfaceKt.m2699SurfaceT9BRK9s(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$1
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }
                        }).then(modifier2), ListItemDefaults.INSTANCE.getShape(composer3, 6), listItemColors3.getContainerColor(), listItemColors3.m2344headlineColorvNxB06k$material3_release(true), f5, f4, null, ComposableLambdaKt.rememberComposableLambda(1502590376, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$2
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

                            public final void invoke(Composer composer4, int i16) {
                                ComposerKt.sourceInformation(composer4, "C163@6771L272:ListItem.kt#uh7d8r");
                                if ((i16 & 3) != 2 || !composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1502590376, i16, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:163)");
                                    }
                                    ListItemKt.ListItemLayout(composableLambda, composableLambda5222, rememberComposableLambda2222, composableLambda3222, composableLambda4222, composer4, 384);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer4.skipToGroupEnd();
                            }
                        }, composer3, 54), composer2, (57344 & i15222) | 12582912 | (i15222 & 458752), 64);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        function212 = function210;
                        function213 = function26;
                        function214 = function27;
                        f6 = f5;
                        f7 = f4;
                        listItemColors4 = listItemColors3;
                        modifier3 = modifier2;
                        function215 = function211;
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i7 = i2 & 32;
                    if (i7 != 0) {
                    }
                    function28 = function25;
                    if ((i & 1572864) == 0) {
                    }
                    i8 = i2 & 128;
                    if (i8 == 0) {
                    }
                    i9 = i8;
                    i10 = i2 & 256;
                    if (i10 != 0) {
                    }
                    i11 = i10;
                    f3 = f2;
                    composer2 = startRestartGroup;
                    if ((i3 & 38347923) == 38347922) {
                    }
                    composer2.startDefaults();
                    ComposableLambda composableLambda22222 = null;
                    if ((i & 1) != 0) {
                    }
                    if (i14 != 0) {
                    }
                    if (i4 != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if (i6 == 0) {
                    }
                    if (i7 == 0) {
                    }
                    if ((i2 & 64) == 0) {
                    }
                    if (i12 == 0) {
                    }
                    if (i13 == 0) {
                    }
                    listItemColors3 = listItemColors2;
                    composer3.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    final Function2<? super Composer, ? super Integer, Unit> rememberComposableLambda22222 = ComposableLambdaKt.rememberComposableLambda(-403249643, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedHeadlineContent$1
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

                        public final void invoke(Composer composer4, int i152222) {
                            ComposerKt.sourceInformation(composer4, "C105@4745L160:ListItem.kt#uh7d8r");
                            if ((i152222 & 3) != 2 || !composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-403249643, i152222, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:105)");
                                }
                                ListItemKt.m2352ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2344headlineColorvNxB06k$material3_release(true), ListTokens.INSTANCE.getListItemLabelTextFont(), function2, composer4, 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer4.skipToGroupEnd();
                        }
                    }, composer3, 54);
                    composer3.startReplaceGroup(1640970492);
                    ComposerKt.sourceInformation(composer3, "*113@5021L204");
                    if (function27 != null) {
                    }
                    composer3.endReplaceGroup();
                    composer3.startReplaceGroup(1640980724);
                    ComposerKt.sourceInformation(composer3, "*123@5341L196");
                    if (function26 != null) {
                    }
                    composer3.endReplaceGroup();
                    composer3.startReplaceGroup(1640990750);
                    ComposerKt.sourceInformation(composer3, "*133@5651L302");
                    if (function29 != null) {
                    }
                    composer3.endReplaceGroup();
                    composer3.startReplaceGroup(1641004177);
                    ComposerKt.sourceInformation(composer3, "*144@6069L353");
                    if (function210 != null) {
                    }
                    composer3.endReplaceGroup();
                    final Function2<? super Composer, ? super Integer, Unit> composableLambda32222 = rememberComposableLambda4;
                    final Function2<? super Composer, ? super Integer, Unit> composableLambda42222 = rememberComposableLambda3;
                    final Function2<? super Composer, ? super Integer, Unit> composableLambda52222 = composableLambda22222;
                    int i152222 = i3 >> 9;
                    composer2 = composer3;
                    SurfaceKt.m2699SurfaceT9BRK9s(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$1
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }
                    }).then(modifier2), ListItemDefaults.INSTANCE.getShape(composer3, 6), listItemColors3.getContainerColor(), listItemColors3.m2344headlineColorvNxB06k$material3_release(true), f5, f4, null, ComposableLambdaKt.rememberComposableLambda(1502590376, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$2
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

                        public final void invoke(Composer composer4, int i16) {
                            ComposerKt.sourceInformation(composer4, "C163@6771L272:ListItem.kt#uh7d8r");
                            if ((i16 & 3) != 2 || !composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1502590376, i16, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:163)");
                                }
                                ListItemKt.ListItemLayout(composableLambda, composableLambda52222, rememberComposableLambda22222, composableLambda32222, composableLambda42222, composer4, 384);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer4.skipToGroupEnd();
                        }
                    }, composer3, 54), composer2, (57344 & i152222) | 12582912 | (i152222 & 458752), 64);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    function212 = function210;
                    function213 = function26;
                    function214 = function27;
                    f6 = f5;
                    f7 = f4;
                    listItemColors4 = listItemColors3;
                    modifier3 = modifier2;
                    function215 = function211;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                function27 = function23;
                i6 = i2 & 16;
                if (i6 == 0) {
                }
                i7 = i2 & 32;
                if (i7 != 0) {
                }
                function28 = function25;
                if ((i & 1572864) == 0) {
                }
                i8 = i2 & 128;
                if (i8 == 0) {
                }
                i9 = i8;
                i10 = i2 & 256;
                if (i10 != 0) {
                }
                i11 = i10;
                f3 = f2;
                composer2 = startRestartGroup;
                if ((i3 & 38347923) == 38347922) {
                }
                composer2.startDefaults();
                ComposableLambda composableLambda222222 = null;
                if ((i & 1) != 0) {
                }
                if (i14 != 0) {
                }
                if (i4 != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 == 0) {
                }
                if (i7 == 0) {
                }
                if ((i2 & 64) == 0) {
                }
                if (i12 == 0) {
                }
                if (i13 == 0) {
                }
                listItemColors3 = listItemColors2;
                composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                final Function2<? super Composer, ? super Integer, Unit> rememberComposableLambda222222 = ComposableLambdaKt.rememberComposableLambda(-403249643, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedHeadlineContent$1
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

                    public final void invoke(Composer composer4, int i1522222) {
                        ComposerKt.sourceInformation(composer4, "C105@4745L160:ListItem.kt#uh7d8r");
                        if ((i1522222 & 3) != 2 || !composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-403249643, i1522222, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:105)");
                            }
                            ListItemKt.m2352ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2344headlineColorvNxB06k$material3_release(true), ListTokens.INSTANCE.getListItemLabelTextFont(), function2, composer4, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer4.skipToGroupEnd();
                    }
                }, composer3, 54);
                composer3.startReplaceGroup(1640970492);
                ComposerKt.sourceInformation(composer3, "*113@5021L204");
                if (function27 != null) {
                }
                composer3.endReplaceGroup();
                composer3.startReplaceGroup(1640980724);
                ComposerKt.sourceInformation(composer3, "*123@5341L196");
                if (function26 != null) {
                }
                composer3.endReplaceGroup();
                composer3.startReplaceGroup(1640990750);
                ComposerKt.sourceInformation(composer3, "*133@5651L302");
                if (function29 != null) {
                }
                composer3.endReplaceGroup();
                composer3.startReplaceGroup(1641004177);
                ComposerKt.sourceInformation(composer3, "*144@6069L353");
                if (function210 != null) {
                }
                composer3.endReplaceGroup();
                final Function2<? super Composer, ? super Integer, Unit> composableLambda322222 = rememberComposableLambda4;
                final Function2<? super Composer, ? super Integer, Unit> composableLambda422222 = rememberComposableLambda3;
                final Function2<? super Composer, ? super Integer, Unit> composableLambda522222 = composableLambda222222;
                int i1522222 = i3 >> 9;
                composer2 = composer3;
                SurfaceKt.m2699SurfaceT9BRK9s(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$1
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }
                }).then(modifier2), ListItemDefaults.INSTANCE.getShape(composer3, 6), listItemColors3.getContainerColor(), listItemColors3.m2344headlineColorvNxB06k$material3_release(true), f5, f4, null, ComposableLambdaKt.rememberComposableLambda(1502590376, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$2
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

                    public final void invoke(Composer composer4, int i16) {
                        ComposerKt.sourceInformation(composer4, "C163@6771L272:ListItem.kt#uh7d8r");
                        if ((i16 & 3) != 2 || !composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1502590376, i16, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:163)");
                            }
                            ListItemKt.ListItemLayout(composableLambda, composableLambda522222, rememberComposableLambda222222, composableLambda322222, composableLambda422222, composer4, 384);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer4.skipToGroupEnd();
                    }
                }, composer3, 54), composer2, (57344 & i1522222) | 12582912 | (i1522222 & 458752), 64);
                if (ComposerKt.isTraceInProgress()) {
                }
                function212 = function210;
                function213 = function26;
                function214 = function27;
                f6 = f5;
                f7 = f4;
                listItemColors4 = listItemColors3;
                modifier3 = modifier2;
                function215 = function211;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            function26 = function22;
            i5 = i2 & 8;
            if (i5 != 0) {
            }
            function27 = function23;
            i6 = i2 & 16;
            if (i6 == 0) {
            }
            i7 = i2 & 32;
            if (i7 != 0) {
            }
            function28 = function25;
            if ((i & 1572864) == 0) {
            }
            i8 = i2 & 128;
            if (i8 == 0) {
            }
            i9 = i8;
            i10 = i2 & 256;
            if (i10 != 0) {
            }
            i11 = i10;
            f3 = f2;
            composer2 = startRestartGroup;
            if ((i3 & 38347923) == 38347922) {
            }
            composer2.startDefaults();
            ComposableLambda composableLambda2222222 = null;
            if ((i & 1) != 0) {
            }
            if (i14 != 0) {
            }
            if (i4 != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 == 0) {
            }
            if (i7 == 0) {
            }
            if ((i2 & 64) == 0) {
            }
            if (i12 == 0) {
            }
            if (i13 == 0) {
            }
            listItemColors3 = listItemColors2;
            composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            final Function2<? super Composer, ? super Integer, Unit> rememberComposableLambda2222222 = ComposableLambdaKt.rememberComposableLambda(-403249643, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedHeadlineContent$1
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

                public final void invoke(Composer composer4, int i15222222) {
                    ComposerKt.sourceInformation(composer4, "C105@4745L160:ListItem.kt#uh7d8r");
                    if ((i15222222 & 3) != 2 || !composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-403249643, i15222222, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:105)");
                        }
                        ListItemKt.m2352ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2344headlineColorvNxB06k$material3_release(true), ListTokens.INSTANCE.getListItemLabelTextFont(), function2, composer4, 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer4.skipToGroupEnd();
                }
            }, composer3, 54);
            composer3.startReplaceGroup(1640970492);
            ComposerKt.sourceInformation(composer3, "*113@5021L204");
            if (function27 != null) {
            }
            composer3.endReplaceGroup();
            composer3.startReplaceGroup(1640980724);
            ComposerKt.sourceInformation(composer3, "*123@5341L196");
            if (function26 != null) {
            }
            composer3.endReplaceGroup();
            composer3.startReplaceGroup(1640990750);
            ComposerKt.sourceInformation(composer3, "*133@5651L302");
            if (function29 != null) {
            }
            composer3.endReplaceGroup();
            composer3.startReplaceGroup(1641004177);
            ComposerKt.sourceInformation(composer3, "*144@6069L353");
            if (function210 != null) {
            }
            composer3.endReplaceGroup();
            final Function2<? super Composer, ? super Integer, Unit> composableLambda3222222 = rememberComposableLambda4;
            final Function2<? super Composer, ? super Integer, Unit> composableLambda4222222 = rememberComposableLambda3;
            final Function2<? super Composer, ? super Integer, Unit> composableLambda5222222 = composableLambda2222222;
            int i15222222 = i3 >> 9;
            composer2 = composer3;
            SurfaceKt.m2699SurfaceT9BRK9s(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }
            }).then(modifier2), ListItemDefaults.INSTANCE.getShape(composer3, 6), listItemColors3.getContainerColor(), listItemColors3.m2344headlineColorvNxB06k$material3_release(true), f5, f4, null, ComposableLambdaKt.rememberComposableLambda(1502590376, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$2
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

                public final void invoke(Composer composer4, int i16) {
                    ComposerKt.sourceInformation(composer4, "C163@6771L272:ListItem.kt#uh7d8r");
                    if ((i16 & 3) != 2 || !composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1502590376, i16, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:163)");
                        }
                        ListItemKt.ListItemLayout(composableLambda, composableLambda5222222, rememberComposableLambda2222222, composableLambda3222222, composableLambda4222222, composer4, 384);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer4.skipToGroupEnd();
                }
            }, composer3, 54), composer2, (57344 & i15222222) | 12582912 | (i15222222 & 458752), 64);
            if (ComposerKt.isTraceInProgress()) {
            }
            function212 = function210;
            function213 = function26;
            function214 = function27;
            f6 = f5;
            f7 = f4;
            listItemColors4 = listItemColors3;
            modifier3 = modifier2;
            function215 = function211;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 == 0) {
        }
        function26 = function22;
        i5 = i2 & 8;
        if (i5 != 0) {
        }
        function27 = function23;
        i6 = i2 & 16;
        if (i6 == 0) {
        }
        i7 = i2 & 32;
        if (i7 != 0) {
        }
        function28 = function25;
        if ((i & 1572864) == 0) {
        }
        i8 = i2 & 128;
        if (i8 == 0) {
        }
        i9 = i8;
        i10 = i2 & 256;
        if (i10 != 0) {
        }
        i11 = i10;
        f3 = f2;
        composer2 = startRestartGroup;
        if ((i3 & 38347923) == 38347922) {
        }
        composer2.startDefaults();
        ComposableLambda composableLambda22222222 = null;
        if ((i & 1) != 0) {
        }
        if (i14 != 0) {
        }
        if (i4 != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 == 0) {
        }
        if (i7 == 0) {
        }
        if ((i2 & 64) == 0) {
        }
        if (i12 == 0) {
        }
        if (i13 == 0) {
        }
        listItemColors3 = listItemColors2;
        composer3.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        final Function2<? super Composer, ? super Integer, Unit> rememberComposableLambda22222222 = ComposableLambdaKt.rememberComposableLambda(-403249643, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedHeadlineContent$1
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

            public final void invoke(Composer composer4, int i152222222) {
                ComposerKt.sourceInformation(composer4, "C105@4745L160:ListItem.kt#uh7d8r");
                if ((i152222222 & 3) != 2 || !composer4.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-403249643, i152222222, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:105)");
                    }
                    ListItemKt.m2352ProvideTextStyleFromToken3JVO9M(ListItemColors.this.m2344headlineColorvNxB06k$material3_release(true), ListTokens.INSTANCE.getListItemLabelTextFont(), function2, composer4, 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer4.skipToGroupEnd();
            }
        }, composer3, 54);
        composer3.startReplaceGroup(1640970492);
        ComposerKt.sourceInformation(composer3, "*113@5021L204");
        if (function27 != null) {
        }
        composer3.endReplaceGroup();
        composer3.startReplaceGroup(1640980724);
        ComposerKt.sourceInformation(composer3, "*123@5341L196");
        if (function26 != null) {
        }
        composer3.endReplaceGroup();
        composer3.startReplaceGroup(1640990750);
        ComposerKt.sourceInformation(composer3, "*133@5651L302");
        if (function29 != null) {
        }
        composer3.endReplaceGroup();
        composer3.startReplaceGroup(1641004177);
        ComposerKt.sourceInformation(composer3, "*144@6069L353");
        if (function210 != null) {
        }
        composer3.endReplaceGroup();
        final Function2<? super Composer, ? super Integer, Unit> composableLambda32222222 = rememberComposableLambda4;
        final Function2<? super Composer, ? super Integer, Unit> composableLambda42222222 = rememberComposableLambda3;
        final Function2<? super Composer, ? super Integer, Unit> composableLambda52222222 = composableLambda22222222;
        int i152222222 = i3 >> 9;
        composer2 = composer3;
        SurfaceKt.m2699SurfaceT9BRK9s(SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }
        }).then(modifier2), ListItemDefaults.INSTANCE.getShape(composer3, 6), listItemColors3.getContainerColor(), listItemColors3.m2344headlineColorvNxB06k$material3_release(true), f5, f4, null, ComposableLambdaKt.rememberComposableLambda(1502590376, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$2
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

            public final void invoke(Composer composer4, int i16) {
                ComposerKt.sourceInformation(composer4, "C163@6771L272:ListItem.kt#uh7d8r");
                if ((i16 & 3) != 2 || !composer4.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1502590376, i16, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:163)");
                    }
                    ListItemKt.ListItemLayout(composableLambda, composableLambda52222222, rememberComposableLambda22222222, composableLambda32222222, composableLambda42222222, composer4, 384);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer4.skipToGroupEnd();
            }
        }, composer3, 54), composer2, (57344 & i152222222) | 12582912 | (i152222222 & 458752), 64);
        if (ComposerKt.isTraceInProgress()) {
        }
        function212 = function210;
        function213 = function26;
        function214 = function27;
        f6 = f5;
        f7 = f4;
        listItemColors4 = listItemColors3;
        modifier3 = modifier2;
        function215 = function211;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ListItemLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final Function2<? super Composer, ? super Integer, Unit> function25, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(2052297037);
        ComposerKt.sourceInformation(startRestartGroup, "C(ListItemLayout)P(1,4)181@7324L36,182@7365L261:ListItem.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function22) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changedInstance(function23) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= startRestartGroup.changedInstance(function24) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= startRestartGroup.changedInstance(function25) ? 16384 : 8192;
        }
        if ((i2 & 9363) != 9362 || !startRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2052297037, i2, -1, "androidx.compose.material3.ListItemLayout (ListItem.kt:180)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -748109790, "CC(remember):ListItem.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new ListItemMeasurePolicy();
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ListItemMeasurePolicy listItemMeasurePolicy = (ListItemMeasurePolicy) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Function2[] function2Arr = new Function2[5];
            function2Arr[0] = function23;
            function2Arr[1] = function24 == null ? ComposableSingletons$ListItemKt.INSTANCE.m2121getLambda1$material3_release() : function24;
            function2Arr[2] = function25 == null ? ComposableSingletons$ListItemKt.INSTANCE.m2122getLambda2$material3_release() : function25;
            function2Arr[3] = function2 == null ? ComposableSingletons$ListItemKt.INSTANCE.m2123getLambda3$material3_release() : function2;
            function2Arr[4] = function22 == null ? ComposableSingletons$ListItemKt.INSTANCE.m2124getLambda4$material3_release() : function22;
            List listOf = CollectionsKt.listOf((Object[]) function2Arr);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1399185516, "CC(Layout)P(!1,2)173@6976L62,170@6862L182:Layout.kt#80mrfh");
            Modifier.Companion companion = Modifier.INSTANCE;
            Function2<Composer, Integer, Unit> combineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listOf);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -290761997, "CC(remember):Layout.kt#9igjgp");
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = MultiContentMeasurePolicyKt.createMeasurePolicy(listItemMeasurePolicy);
                startRestartGroup.updateRememberedValue(rememberedValue2);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, companion);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
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
            Updater.m3912setimpl(m3905constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            combineAsVirtualLayouts.invoke(startRestartGroup, 0);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItemLayout$1
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

                public final void invoke(Composer composer2, int i3) {
                    ListItemKt.ListItemLayout(function2, function22, function23, function24, function25, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateWidth-yeHjK3Y, reason: not valid java name */
    public static final int m2358calculateWidthyeHjK3Y(IntrinsicMeasureScope intrinsicMeasureScope, int i, int i2, int i3, int i4, int i5, int i6, long j) {
        if (Constraints.m7338getHasBoundedWidthimpl(j)) {
            return Constraints.m7342getMaxWidthimpl(j);
        }
        return i6 + i + Math.max(i3, Math.max(i4, i5)) + i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateHeight-N4Jib3Y, reason: not valid java name */
    public static final int m2357calculateHeightN4Jib3Y(IntrinsicMeasureScope intrinsicMeasureScope, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j) {
        float m3501getListItemTwoLineContainerHeightD9Ej5fM;
        if (ListItemType.m2364equalsimpl0(i6, ListItemType.INSTANCE.m2369getOneLineAlXitO8())) {
            m3501getListItemTwoLineContainerHeightD9Ej5fM = ListTokens.INSTANCE.m3496getListItemOneLineContainerHeightD9Ej5fM();
        } else {
            m3501getListItemTwoLineContainerHeightD9Ej5fM = ListItemType.m2364equalsimpl0(i6, ListItemType.INSTANCE.m2371getTwoLineAlXitO8()) ? ListTokens.INSTANCE.m3501getListItemTwoLineContainerHeightD9Ej5fM() : ListTokens.INSTANCE.m3498getListItemThreeLineContainerHeightD9Ej5fM();
        }
        return RangesKt.coerceAtMost(Math.max(Math.max(Constraints.m7343getMinHeightimpl(j), intrinsicMeasureScope.mo453roundToPx0680j_4(m3501getListItemTwoLineContainerHeightD9Ej5fM)), i7 + Math.max(i, Math.max(i3 + i4 + i5, i2))), Constraints.m7341getMaxHeightimpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult place(MeasureScope measureScope, final int i, final int i2, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, final Placeable placeable4, final Placeable placeable5, final boolean z, final int i3, final int i4, final int i5) {
        return MeasureScope.CC.layout$default(measureScope, i, i2, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.ListItemKt$place$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                Placeable.PlacementScope placementScope2;
                int align;
                Placeable placeable6 = Placeable.this;
                if (placeable6 != null) {
                    int i6 = i3;
                    boolean z2 = z;
                    int i7 = i5;
                    int i8 = i2;
                    if (!z2) {
                        i7 = Alignment.INSTANCE.getCenterVertically().align(placeable6.getHeight(), i8);
                    }
                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable6, i6, i7, 0.0f, 4, null);
                    placementScope2 = placementScope;
                } else {
                    placementScope2 = placementScope;
                }
                Placeable placeable7 = placeable2;
                if (placeable7 != null) {
                    int i9 = i;
                    int i10 = i4;
                    boolean z3 = z;
                    int i11 = i5;
                    int i12 = i2;
                    int width = (i9 - i10) - placeable7.getWidth();
                    if (!z3) {
                        i11 = Alignment.INSTANCE.getCenterVertically().align(placeable7.getHeight(), i12);
                    }
                    Placeable.PlacementScope.placeRelative$default(placementScope2, placeable7, width, i11, 0.0f, 4, null);
                }
                int widthOrZero = i3 + TextFieldImplKt.widthOrZero(Placeable.this);
                if (z) {
                    align = i5;
                } else {
                    align = Alignment.INSTANCE.getCenterVertically().align(TextFieldImplKt.heightOrZero(placeable3) + TextFieldImplKt.heightOrZero(placeable4) + TextFieldImplKt.heightOrZero(placeable5), i2);
                }
                int i13 = align;
                Placeable placeable8 = placeable4;
                if (placeable8 != null) {
                    Placeable.PlacementScope.placeRelative$default(placementScope2, placeable8, widthOrZero, i13, 0.0f, 4, null);
                }
                int heightOrZero = i13 + TextFieldImplKt.heightOrZero(placeable4);
                Placeable placeable9 = placeable3;
                if (placeable9 != null) {
                    Placeable.PlacementScope.placeRelative$default(placementScope2, placeable9, widthOrZero, heightOrZero, 0.0f, 4, null);
                }
                int heightOrZero2 = heightOrZero + TextFieldImplKt.heightOrZero(placeable3);
                Placeable placeable10 = placeable5;
                if (placeable10 != null) {
                    Placeable.PlacementScope.placeRelative$default(placementScope2, placeable10, widthOrZero, heightOrZero2, 0.0f, 4, null);
                }
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ProvideTextStyleFromToken-3J-VO9M, reason: not valid java name */
    public static final void m2352ProvideTextStyleFromToken3JVO9M(long j, final TypographyKeyTokens typographyKeyTokens, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        long j2;
        final Function2<? super Composer, ? super Integer, Unit> function22;
        Composer startRestartGroup = composer.startRestartGroup(1133967795);
        ComposerKt.sourceInformation(startRestartGroup, "C(ProvideTextStyleFromToken)P(0:c#ui.graphics.Color,2)660@26959L5,658@26869L128:ListItem.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(typographyKeyTokens) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 147) != 146 || !startRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1133967795, i2, -1, "androidx.compose.material3.ProvideTextStyleFromToken (ListItem.kt:658)");
            }
            j2 = j;
            ProvideContentColorTextStyleKt.m3088ProvideContentColorTextStyle3JVO9M(j2, TypographyKt.getValue(typographyKeyTokens, startRestartGroup, (i2 >> 3) & 14), function2, startRestartGroup, i2 & 910);
            function22 = function2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
            j2 = j;
            function22 = function2;
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            final long j3 = j2;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ProvideTextStyleFromToken$1
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

                public final void invoke(Composer composer2, int i3) {
                    ListItemKt.m2352ProvideTextStyleFromToken3JVO9M(j3, typographyKeyTokens, function22, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static final float getListItemVerticalPadding() {
        return ListItemVerticalPadding;
    }

    public static final float getListItemThreeLineVerticalPadding() {
        return ListItemThreeLineVerticalPadding;
    }

    public static final float getListItemStartPadding() {
        return ListItemStartPadding;
    }

    public static final float getListItemEndPadding() {
        return ListItemEndPadding;
    }

    public static final float getLeadingContentEndPadding() {
        return LeadingContentEndPadding;
    }

    public static final float getTrailingContentStartPadding() {
        return TrailingContentStartPadding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isSupportingMultilineHeuristic(Density density, int i) {
        return i > density.mo452roundToPxR2X_6o(TextUnitKt.getSp(30));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: verticalPadding-yh95HIg, reason: not valid java name */
    public static final float m2359verticalPaddingyh95HIg(int i) {
        return ListItemType.m2364equalsimpl0(i, ListItemType.INSTANCE.m2370getThreeLineAlXitO8()) ? ListItemThreeLineVerticalPadding : ListItemVerticalPadding;
    }

    static {
        float f = 16;
        ListItemStartPadding = Dp.m7399constructorimpl(f);
        ListItemEndPadding = Dp.m7399constructorimpl(f);
        LeadingContentEndPadding = Dp.m7399constructorimpl(f);
        TrailingContentStartPadding = Dp.m7399constructorimpl(f);
    }
}
