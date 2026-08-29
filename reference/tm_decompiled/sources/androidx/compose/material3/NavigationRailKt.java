package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.internal.MappedInteractionSource;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.NavigationRailTokens;
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
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: NavigationRail.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001az\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2 \b\u0002\u0010\u001d\u001a\u001a\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001e¢\u0006\u0002\b ¢\u0006\u0002\b!2\b\b\u0002\u0010\"\u001a\u00020#2\u001c\u0010$\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00170\u001e¢\u0006\u0002\b ¢\u0006\u0002\b!H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u0081\u0001\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00170+2\u0011\u0010,\u001a\r\u0012\u0004\u0012\u00020\u00170+¢\u0006\u0002\b 2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010-\u001a\u00020)2\u0015\b\u0002\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010+¢\u0006\u0002\b 2\b\b\u0002\u0010/\u001a\u00020)2\b\b\u0002\u00100\u001a\u0002012\n\b\u0002\u00102\u001a\u0004\u0018\u000103H\u0007¢\u0006\u0002\u00104\u001aq\u00105\u001a\u00020\u00172\u0011\u00106\u001a\r\u0012\u0004\u0012\u00020\u00170+¢\u0006\u0002\b 2\u0011\u00107\u001a\r\u0012\u0004\u0012\u00020\u00170+¢\u0006\u0002\b 2\u0011\u0010,\u001a\r\u0012\u0004\u0012\u00020\u00170+¢\u0006\u0002\b 2\u0013\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010+¢\u0006\u0002\b 2\u0006\u0010/\u001a\u00020)2\f\u00108\u001a\b\u0012\u0004\u0012\u0002090+H\u0003¢\u0006\u0002\u0010:\u001a8\u0010;\u001a\u00020<*\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020?2\b\u0010A\u001a\u0004\u0018\u00010?2\u0006\u0010B\u001a\u00020CH\u0002ø\u0001\u0000¢\u0006\u0004\bD\u0010E\u001aP\u0010F\u001a\u00020<*\u00020=2\u0006\u0010G\u001a\u00020?2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020?2\b\u0010A\u001a\u0004\u0018\u00010?2\u0006\u0010B\u001a\u00020C2\u0006\u0010/\u001a\u00020)2\u0006\u00108\u001a\u000209H\u0002ø\u0001\u0000¢\u0006\u0004\bH\u0010I\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\r\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u000e\u0010\u000f\"\u0016\u0010\u0010\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0011\u0010\u000f\"\u0016\u0010\u0012\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0013\u0010\u000f\"\u0016\u0010\u0014\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0015\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006J²\u0006\n\u0010K\u001a\u00020\u001bX\u008a\u0084\u0002²\u0006\n\u0010L\u001a\u00020\u001bX\u008a\u0084\u0002"}, d2 = {"IconLayoutIdTag", "", "IndicatorHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "IndicatorLayoutIdTag", "IndicatorRippleLayoutIdTag", "IndicatorVerticalPaddingNoLabel", "IndicatorVerticalPaddingWithLabel", "ItemAnimationDurationMillis", "", "LabelLayoutIdTag", "NavigationRailHeaderPadding", "NavigationRailItemHeight", "getNavigationRailItemHeight", "()F", "NavigationRailItemVerticalPadding", "getNavigationRailItemVerticalPadding", "NavigationRailItemWidth", "getNavigationRailItemWidth", "NavigationRailVerticalPadding", "getNavigationRailVerticalPadding", "NavigationRail", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "header", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "NavigationRail-qi6gXK8", "(Landroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "NavigationRailItem", "selected", "", "onClick", "Lkotlin/Function0;", NavigationRailKt.IconLayoutIdTag, "enabled", NavigationRailKt.LabelLayoutIdTag, "alwaysShowLabel", "colors", "Landroidx/compose/material3/NavigationRailItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/NavigationRailItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "NavigationRailItemLayout", NavigationRailKt.IndicatorRippleLayoutIdTag, NavigationRailKt.IndicatorLayoutIdTag, "animationProgress", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "indicatorRipplePlaceable", "indicatorPlaceable", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-X9ElhV4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "labelPlaceable", "placeLabelAndIcon-zUg2_y0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JZF)Landroidx/compose/ui/layout/MeasureResult;", "material3_release", "iconColor", "textColor"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NavigationRailKt {
    private static final String IconLayoutIdTag = "icon";
    private static final float IndicatorHorizontalPadding;
    private static final String IndicatorLayoutIdTag = "indicator";
    private static final String IndicatorRippleLayoutIdTag = "indicatorRipple";
    private static final float IndicatorVerticalPaddingNoLabel;
    private static final float IndicatorVerticalPaddingWithLabel;
    private static final int ItemAnimationDurationMillis = 150;
    private static final String LabelLayoutIdTag = "label";
    private static final float NavigationRailItemVerticalPadding;
    private static final float NavigationRailVerticalPadding;
    private static final float NavigationRailHeaderPadding = Dp.m7399constructorimpl(8);
    private static final float NavigationRailItemWidth = NavigationRailTokens.INSTANCE.m3517getContainerWidthD9Ej5fM();
    private static final float NavigationRailItemHeight = NavigationRailTokens.INSTANCE.m3520getNoLabelActiveIndicatorHeightD9Ej5fM();

    /* JADX WARN: Removed duplicated region for block: B:26:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0099  */
    /* renamed from: NavigationRail-qi6gXK8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2491NavigationRailqi6gXK8(Modifier modifier, long j, long j2, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function32, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long j3;
        long j4;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function33;
        WindowInsets windowInsets2;
        long j5;
        final WindowInsets windowInsets3;
        Modifier modifier3;
        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function34;
        Composer composer2;
        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function35;
        final WindowInsets windowInsets4;
        final Modifier modifier4;
        final long j6;
        final long j7;
        ScopeUpdateScope endRestartGroup;
        int i4;
        int i5;
        Composer startRestartGroup = composer.startRestartGroup(118552648);
        ComposerKt.sourceInformation(startRestartGroup, "C(NavigationRail)P(4,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3,5)113@5242L14,114@5284L31,116@5431L12,123@5614L618,119@5501L731:NavigationRail.kt#uh7d8r");
        int i6 = i2 & 1;
        if (i6 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (startRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                j3 = j;
                if (startRestartGroup.changed(j3)) {
                    i5 = 32;
                    i3 |= i5;
                }
            } else {
                j3 = j;
            }
            i5 = 16;
            i3 |= i5;
        } else {
            j3 = j;
        }
        if ((i & 384) == 0) {
            j4 = j2;
            i3 |= ((i2 & 4) == 0 && startRestartGroup.changed(j4)) ? 256 : 128;
        } else {
            j4 = j2;
        }
        int i7 = i2 & 8;
        if (i7 != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            function33 = function3;
            i3 |= startRestartGroup.changedInstance(function33) ? 2048 : 1024;
            if ((i & 24576) != 0) {
                if ((i2 & 16) == 0) {
                    windowInsets2 = windowInsets;
                    if (startRestartGroup.changed(windowInsets2)) {
                        i4 = 16384;
                        i3 |= i4;
                    }
                } else {
                    windowInsets2 = windowInsets;
                }
                i4 = 8192;
                i3 |= i4;
            } else {
                windowInsets2 = windowInsets;
            }
            if ((i2 & 32) == 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                i3 |= startRestartGroup.changedInstance(function32) ? 131072 : 65536;
            }
            if ((74899 & i3) == 74898 || !startRestartGroup.getSkipping()) {
                startRestartGroup.startDefaults();
                if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                    Modifier.Companion companion = i6 == 0 ? Modifier.INSTANCE : modifier2;
                    if ((i2 & 2) != 0) {
                        j3 = NavigationRailDefaults.INSTANCE.getContainerColor(startRestartGroup, 6);
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        j4 = ColorSchemeKt.m2085contentColorForek8zF_U(j3, startRestartGroup, (i3 >> 3) & 14);
                        i3 &= -897;
                    }
                    if (i7 != 0) {
                        function33 = null;
                    }
                    if ((i2 & 16) == 0) {
                        windowInsets3 = NavigationRailDefaults.INSTANCE.getWindowInsets(startRestartGroup, 6);
                        i3 &= -57345;
                        j5 = j4;
                    } else {
                        j5 = j4;
                        windowInsets3 = windowInsets2;
                    }
                    modifier3 = companion;
                    function34 = function33;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    if ((i2 & 2) != 0) {
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -57345;
                    }
                    j5 = j4;
                    function34 = function33;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets2;
                }
                long j8 = j3;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(118552648, i3, -1, "androidx.compose.material3.NavigationRail (NavigationRail.kt:118)");
                }
                int i8 = (i3 & 14) | 12582912;
                int i9 = i3 << 3;
                composer2 = startRestartGroup;
                SurfaceKt.m2699SurfaceT9BRK9s(modifier3, null, j8, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-2092683357, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRail$1
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
                        float f;
                        ComposerKt.sourceInformation(composer3, "C124@5624L602:NavigationRail.kt#uh7d8r");
                        if ((i10 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2092683357, i10, -1, "androidx.compose.material3.NavigationRail.<anonymous> (NavigationRail.kt:124)");
                            }
                            Modifier selectableGroup = SelectableGroupKt.selectableGroup(PaddingKt.m880paddingVpY3zN4$default(SizeKt.m937widthInVpY3zN4$default(WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), WindowInsets.this), NavigationRailTokens.INSTANCE.m3517getContainerWidthD9Ej5fM(), 0.0f, 2, null), 0.0f, NavigationRailKt.getNavigationRailVerticalPadding(), 1, null));
                            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                            Arrangement.HorizontalOrVertical m733spacedBy0680j_4 = Arrangement.INSTANCE.m733spacedBy0680j_4(NavigationRailKt.getNavigationRailVerticalPadding());
                            Function3<ColumnScope, Composer, Integer, Unit> function36 = function34;
                            Function3<ColumnScope, Composer, Integer, Unit> function37 = function32;
                            ComposerKt.sourceInformationMarkerStart(composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(m733spacedBy0680j_4, centerHorizontally, composer3, 54);
                            ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, selectableGroup);
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
                            Updater.m3912setimpl(m3905constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer3, 933375077, "C137@6207L9:NavigationRail.kt#uh7d8r");
                            composer3.startReplaceGroup(722845512);
                            ComposerKt.sourceInformation(composer3, "134@6103L8,135@6128L52");
                            if (function36 != null) {
                                function36.invoke(columnScopeInstance, composer3, 6);
                                Modifier.Companion companion2 = Modifier.INSTANCE;
                                f = NavigationRailKt.NavigationRailHeaderPadding;
                                SpacerKt.Spacer(SizeKt.m916height3ABfNKs(companion2, f), composer3, 6);
                            }
                            composer3.endReplaceGroup();
                            function37.invoke(columnScopeInstance, composer3, 6);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
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
                }, startRestartGroup, 54), composer2, i8 | (i9 & 896) | (i9 & 7168), 114);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function35 = function34;
                windowInsets4 = windowInsets3;
                modifier4 = modifier3;
                j6 = j8;
                j7 = j5;
            } else {
                startRestartGroup.skipToGroupEnd();
                composer2 = startRestartGroup;
                modifier4 = modifier2;
                j6 = j3;
                j7 = j4;
                function35 = function33;
                windowInsets4 = windowInsets2;
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRail$2
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
                        NavigationRailKt.m2491NavigationRailqi6gXK8(Modifier.this, j6, j7, function35, windowInsets4, function32, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
                return;
            }
            return;
        }
        function33 = function3;
        if ((i & 24576) != 0) {
        }
        if ((i2 & 32) == 0) {
        }
        if ((74899 & i3) == 74898) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i6 == 0) {
        }
        if ((i2 & 2) != 0) {
        }
        if ((i2 & 4) != 0) {
        }
        if (i7 != 0) {
        }
        if ((i2 & 16) == 0) {
        }
        modifier3 = companion;
        function34 = function33;
        long j82 = j3;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        int i82 = (i3 & 14) | 12582912;
        int i92 = i3 << 3;
        composer2 = startRestartGroup;
        SurfaceKt.m2699SurfaceT9BRK9s(modifier3, null, j82, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-2092683357, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRail$1
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
                float f;
                ComposerKt.sourceInformation(composer3, "C124@5624L602:NavigationRail.kt#uh7d8r");
                if ((i10 & 3) != 2 || !composer3.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2092683357, i10, -1, "androidx.compose.material3.NavigationRail.<anonymous> (NavigationRail.kt:124)");
                    }
                    Modifier selectableGroup = SelectableGroupKt.selectableGroup(PaddingKt.m880paddingVpY3zN4$default(SizeKt.m937widthInVpY3zN4$default(WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), WindowInsets.this), NavigationRailTokens.INSTANCE.m3517getContainerWidthD9Ej5fM(), 0.0f, 2, null), 0.0f, NavigationRailKt.getNavigationRailVerticalPadding(), 1, null));
                    Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                    Arrangement.HorizontalOrVertical m733spacedBy0680j_4 = Arrangement.INSTANCE.m733spacedBy0680j_4(NavigationRailKt.getNavigationRailVerticalPadding());
                    Function3<ColumnScope, Composer, Integer, Unit> function36 = function34;
                    Function3<ColumnScope, Composer, Integer, Unit> function37 = function32;
                    ComposerKt.sourceInformationMarkerStart(composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(m733spacedBy0680j_4, centerHorizontally, composer3, 54);
                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, selectableGroup);
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
                    Updater.m3912setimpl(m3905constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composer3, 933375077, "C137@6207L9:NavigationRail.kt#uh7d8r");
                    composer3.startReplaceGroup(722845512);
                    ComposerKt.sourceInformation(composer3, "134@6103L8,135@6128L52");
                    if (function36 != null) {
                        function36.invoke(columnScopeInstance, composer3, 6);
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        f = NavigationRailKt.NavigationRailHeaderPadding;
                        SpacerKt.Spacer(SizeKt.m916height3ABfNKs(companion2, f), composer3, 6);
                    }
                    composer3.endReplaceGroup();
                    function37.invoke(columnScopeInstance, composer3, 6);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
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
        }, startRestartGroup, 54), composer2, i82 | (i92 & 896) | (i92 & 7168), 114);
        if (ComposerKt.isTraceInProgress()) {
        }
        function35 = function34;
        windowInsets4 = windowInsets3;
        modifier4 = modifier3;
        j6 = j82;
        j7 = j5;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0419  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void NavigationRailItem(final boolean z, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean z2, Function2<? super Composer, ? super Integer, Unit> function22, boolean z3, NavigationRailItemColors navigationRailItemColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        Modifier modifier2;
        int i5;
        final boolean z4;
        int i6;
        final Function2<? super Composer, ? super Integer, Unit> function23;
        int i7;
        boolean z5;
        int i8;
        int i9;
        NavigationRailItemColors navigationRailItemColors2;
        int i10;
        MutableInteractionSource mutableInteractionSource2;
        MutableInteractionSource mutableInteractionSource3;
        int currentCompositeKeyHash;
        Composer m3905constructorimpl;
        boolean changed;
        Object rememberedValue;
        final Shape value;
        boolean changed2;
        Object rememberedValue2;
        final boolean z6;
        final NavigationRailItemColors navigationRailItemColors3;
        final MutableInteractionSource mutableInteractionSource4;
        final Modifier modifier3;
        final boolean z7;
        ScopeUpdateScope endRestartGroup;
        int i11;
        Composer startRestartGroup = composer.startRestartGroup(-1533971045);
        ComposerKt.sourceInformation(startRestartGroup, "C(NavigationRailItem)P(8,7,3,6,2,5)179@8055L8,185@8288L633,215@9569L2776:NavigationRail.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= startRestartGroup.changedInstance(function0) ? 32 : 16;
            if ((i2 & 4) == 0) {
                i3 |= 384;
            } else if ((i & 384) == 0) {
                i3 |= startRestartGroup.changedInstance(function2) ? 256 : 128;
                i4 = i2 & 8;
                if (i4 != 0) {
                    i3 |= 3072;
                } else if ((i & 3072) == 0) {
                    modifier2 = modifier;
                    i3 |= startRestartGroup.changed(modifier2) ? 2048 : 1024;
                    i5 = i2 & 16;
                    if (i5 == 0) {
                        i3 |= 24576;
                    } else if ((i & 24576) == 0) {
                        z4 = z2;
                        i3 |= startRestartGroup.changed(z4) ? 16384 : 8192;
                        i6 = i2 & 32;
                        if (i6 != 0) {
                            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((196608 & i) == 0) {
                            function23 = function22;
                            i3 |= startRestartGroup.changedInstance(function23) ? 131072 : 65536;
                            i7 = i2 & 64;
                            if (i7 == 0) {
                                i3 |= 1572864;
                                z5 = z3;
                            } else {
                                z5 = z3;
                                if ((i & 1572864) == 0) {
                                    i3 |= startRestartGroup.changed(z5) ? 1048576 : 524288;
                                }
                            }
                            if ((i & 12582912) == 0) {
                                if ((i2 & 128) == 0 && startRestartGroup.changed(navigationRailItemColors)) {
                                    i11 = 8388608;
                                    i3 |= i11;
                                }
                                i11 = 4194304;
                                i3 |= i11;
                            }
                            i8 = i2 & 256;
                            if (i8 == 0) {
                                i3 |= 100663296;
                            } else if ((i & 100663296) == 0) {
                                i9 = i8;
                                i3 |= startRestartGroup.changed(mutableInteractionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                if ((i3 & 38347923) == 38347922 || !startRestartGroup.getSkipping()) {
                                    startRestartGroup.startDefaults();
                                    if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                        if (i4 != 0) {
                                            modifier2 = Modifier.INSTANCE;
                                        }
                                        if (i5 != 0) {
                                            z4 = true;
                                        }
                                        if (i6 != 0) {
                                            function23 = null;
                                        }
                                        if (i7 != 0) {
                                            z5 = true;
                                        }
                                        if ((i2 & 128) != 0) {
                                            navigationRailItemColors2 = NavigationRailItemDefaults.INSTANCE.colors(startRestartGroup, 6);
                                            i3 &= -29360129;
                                        } else {
                                            navigationRailItemColors2 = navigationRailItemColors;
                                        }
                                        if (i9 != 0) {
                                            i10 = i3;
                                            mutableInteractionSource2 = null;
                                            startRestartGroup.endDefaults();
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1533971045, i10, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:181)");
                                            }
                                            startRestartGroup.startReplaceGroup(-782588008);
                                            ComposerKt.sourceInformation(startRestartGroup, "183@8207L39");
                                            if (mutableInteractionSource2 != null) {
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -782587357, "CC(remember):NavigationRail.kt#9igjgp");
                                                Object rememberedValue3 = startRestartGroup.rememberedValue();
                                                if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                                    rememberedValue3 = InteractionSourceKt.MutableInteractionSource();
                                                    startRestartGroup.updateRememberedValue(rememberedValue3);
                                                }
                                                mutableInteractionSource3 = (MutableInteractionSource) rememberedValue3;
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            } else {
                                                mutableInteractionSource3 = mutableInteractionSource2;
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            final NavigationRailItemColors navigationRailItemColors4 = navigationRailItemColors2;
                                            final boolean z8 = z5;
                                            final boolean z9 = z4;
                                            final Function2<? super Composer, ? super Integer, Unit> function24 = function23;
                                            Modifier modifier4 = modifier2;
                                            ComposableLambda rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1023357515, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1
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

                                                public final void invoke(Composer composer2, int i12) {
                                                    ComposerKt.sourceInformation(composer2, "C187@8335L201,193@8718L193:NavigationRail.kt#uh7d8r");
                                                    if ((i12 & 3) != 2 || !composer2.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-1023357515, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous> (NavigationRail.kt:186)");
                                                        }
                                                        State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationRailItemColors.this.m2487iconColorWaAFU9c$material3_release(z, z9), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer2, 48, 12);
                                                        Modifier.Companion clearAndSetSemantics = (function24 == null || !(z8 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1.1
                                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                            }

                                                            @Override // kotlin.jvm.functions.Function1
                                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                                invoke2(semanticsPropertyReceiver);
                                                                return Unit.INSTANCE;
                                                            }
                                                        });
                                                        Function2<Composer, Integer, Unit> function25 = function2;
                                                        ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                        ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer2, clearAndSetSemantics);
                                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                        ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                        if (!(composer2.getApplier() instanceof Applier)) {
                                                            ComposablesKt.invalidApplier();
                                                        }
                                                        composer2.startReusableNode();
                                                        if (composer2.getInserting()) {
                                                            composer2.createNode(constructor);
                                                        } else {
                                                            composer2.useNode();
                                                        }
                                                        Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer2);
                                                        Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                        if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                                            m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                                            m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash);
                                                        }
                                                        Updater.m3912setimpl(m3905constructorimpl2, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                                        ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                        ComposerKt.sourceInformationMarkerStart(composer2, -296474437, "C194@8819L78:NavigationRail.kt#uh7d8r");
                                                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function25, composer2, ProvidedValue.$stable);
                                                        ComposerKt.sourceInformationMarkerEnd(composer2);
                                                        ComposerKt.sourceInformationMarkerEnd(composer2);
                                                        composer2.endNode();
                                                        ComposerKt.sourceInformationMarkerEnd(composer2);
                                                        ComposerKt.sourceInformationMarkerEnd(composer2);
                                                        ComposerKt.sourceInformationMarkerEnd(composer2);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer2.skipToGroupEnd();
                                                }

                                                private static final long invoke$lambda$0(State<Color> state) {
                                                    return state.getValue().m4625unboximpl();
                                                }
                                            }, startRestartGroup, 54);
                                            startRestartGroup.startReplaceGroup(-782561860);
                                            ComposerKt.sourceInformation(startRestartGroup, "*200@9017L536");
                                            ComposableLambda rememberComposableLambda2 = function23 != null ? null : ComposableLambdaKt.rememberComposableLambda(-105269599, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
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

                                                public final void invoke(Composer composer2, int i12) {
                                                    ComposerKt.sourceInformation(composer2, "C201@9082L5,203@9141L213,207@9371L168:NavigationRail.kt#uh7d8r");
                                                    if ((i12 & 3) != 2 || !composer2.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-105269599, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:201)");
                                                        }
                                                        ProvideContentColorTextStyleKt.m3088ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationRailItemColors.this.m2488textColorWaAFU9c$material3_release(z, z4), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer2, 48, 12)), TypographyKt.getValue(NavigationRailTokens.INSTANCE.getLabelTextFont(), composer2, 6), function23, composer2, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer2.skipToGroupEnd();
                                                }

                                                private static final long invoke$lambda$0(State<Color> state) {
                                                    return state.getValue().m4625unboximpl();
                                                }
                                            }, startRestartGroup, 54);
                                            startRestartGroup.endReplaceGroup();
                                            MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource3;
                                            boolean z10 = z4;
                                            Modifier m937widthInVpY3zN4$default = SizeKt.m937widthInVpY3zN4$default(SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier4, z, mutableInteractionSource5, null, z10, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationRailItemHeight, 1, null), NavigationRailItemWidth, 0.0f, 2, null);
                                            Alignment center = Alignment.INSTANCE.getCenter();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, true);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                                            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, m937widthInVpY3zN4$default);
                                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            startRestartGroup.startReusableNode();
                                            if (!startRestartGroup.getInserting()) {
                                                startRestartGroup.createNode(constructor);
                                            } else {
                                                startRestartGroup.useNode();
                                            }
                                            m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
                                            Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if (!m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                            }
                                            Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 584386773, "C231@10130L157,*240@10571L7,246@10874L128,260@11450L261,268@11760L280,282@12301L27,276@12050L289:NavigationRail.kt#uh7d8r");
                                            boolean z11 = z5;
                                            final State<Float> animateFloatAsState = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                            Object consume = startRestartGroup.consume(localDensity);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            Density density = (Density) consume;
                                            long Offset = OffsetKt.Offset((density.mo453roundToPx0680j_4(r2) - density.mo453roundToPx0680j_4(NavigationRailTokens.INSTANCE.m3515getActiveIndicatorWidthD9Ej5fM())) / 2, 0.0f);
                                            Unit unit = Unit.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119672869, "CC(remember):NavigationRail.kt#9igjgp");
                                            changed = startRestartGroup.changed(mutableInteractionSource5) | startRestartGroup.changed(Offset);
                                            rememberedValue = startRestartGroup.rememberedValue();
                                            if (!changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue = new MappedInteractionSource(mutableInteractionSource5, Offset, null);
                                                startRestartGroup.updateRememberedValue(rememberedValue);
                                            }
                                            final MappedInteractionSource mappedInteractionSource = (MappedInteractionSource) rememberedValue;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            if (function23 == null) {
                                                startRestartGroup.startReplaceGroup(585295320);
                                                ComposerKt.sourceInformation(startRestartGroup, "252@11124L5");
                                                value = ShapesKt.getValue(NavigationRailTokens.INSTANCE.getActiveIndicatorShape(), startRestartGroup, 6);
                                                startRestartGroup.endReplaceGroup();
                                            } else {
                                                startRestartGroup.startReplaceGroup(585379857);
                                                ComposerKt.sourceInformation(startRestartGroup, "254@11216L5");
                                                value = ShapesKt.getValue(NavigationRailTokens.INSTANCE.getNoLabelActiveIndicatorShape(), startRestartGroup, 6);
                                                startRestartGroup.endReplaceGroup();
                                            }
                                            ComposableLambda rememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(211026382, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                                    invoke(composer2, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer2, int i12) {
                                                    ComposerKt.sourceInformation(composer2, "C264@11646L32,261@11468L229:NavigationRail.kt#uh7d8r");
                                                    if ((i12 & 3) != 2 || !composer2.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(211026382, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:261)");
                                                        }
                                                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), Shape.this), mappedInteractionSource, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer2, 0, 7)), composer2, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer2.skipToGroupEnd();
                                                }
                                            }, startRestartGroup, 54);
                                            ComposableLambda rememberComposableLambda4 = ComposableLambdaKt.rememberComposableLambda(-1862011490, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                                    invoke(composer2, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer2, int i12) {
                                                    ComposerKt.sourceInformation(composer2, "C271@11882L35,269@11778L248:NavigationRail.kt#uh7d8r");
                                                    if ((i12 & 3) != 2 || !composer2.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-1862011490, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:269)");
                                                        }
                                                        Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                                                        ComposerKt.sourceInformationMarkerStart(composer2, -425107715, "CC(remember):NavigationRail.kt#9igjgp");
                                                        boolean changed3 = composer2.changed(animateFloatAsState);
                                                        final State<Float> state = animateFloatAsState;
                                                        Object rememberedValue4 = composer2.rememberedValue();
                                                        if (changed3 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                            rememberedValue4 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1$1$1
                                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                {
                                                                    super(1);
                                                                }

                                                                @Override // kotlin.jvm.functions.Function1
                                                                public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                                                    invoke2(graphicsLayerScope);
                                                                    return Unit.INSTANCE;
                                                                }

                                                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                                                                    graphicsLayerScope.setAlpha(state.getValue().floatValue());
                                                                }
                                                            };
                                                            composer2.updateRememberedValue(rememberedValue4);
                                                        }
                                                        ComposerKt.sourceInformationMarkerEnd(composer2);
                                                        BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue4), navigationRailItemColors4.getSelectedIndicatorColor(), value), composer2, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer2.skipToGroupEnd();
                                                }
                                            }, startRestartGroup, 54);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119627306, "CC(remember):NavigationRail.kt#9igjgp");
                                            changed2 = startRestartGroup.changed(animateFloatAsState);
                                            rememberedValue2 = startRestartGroup.rememberedValue();
                                            if (!changed2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$2$1
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Float invoke() {
                                                        return animateFloatAsState.getValue();
                                                    }
                                                };
                                                startRestartGroup.updateRememberedValue(rememberedValue2);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            NavigationRailItemLayout(rememberComposableLambda3, rememberComposableLambda4, rememberComposableLambda, rememberComposableLambda2, z11, (Function0) rememberedValue2, startRestartGroup, (57344 & (i10 >> 6)) | 438);
                                            startRestartGroup = startRestartGroup;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            startRestartGroup.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                            z6 = z11;
                                            navigationRailItemColors3 = navigationRailItemColors4;
                                            mutableInteractionSource4 = mutableInteractionSource2;
                                            modifier3 = modifier4;
                                            z7 = z10;
                                        }
                                    } else {
                                        startRestartGroup.skipToGroupEnd();
                                        if ((i2 & 128) != 0) {
                                            i3 &= -29360129;
                                        }
                                        navigationRailItemColors2 = navigationRailItemColors;
                                    }
                                    mutableInteractionSource2 = mutableInteractionSource;
                                    i10 = i3;
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    startRestartGroup.startReplaceGroup(-782588008);
                                    ComposerKt.sourceInformation(startRestartGroup, "183@8207L39");
                                    if (mutableInteractionSource2 != null) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    final NavigationRailItemColors navigationRailItemColors42 = navigationRailItemColors2;
                                    final boolean z82 = z5;
                                    final boolean z92 = z4;
                                    final Function2<? super Composer, ? super Integer, Unit> function242 = function23;
                                    Modifier modifier42 = modifier2;
                                    ComposableLambda rememberComposableLambda5 = ComposableLambdaKt.rememberComposableLambda(-1023357515, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1
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

                                        public final void invoke(Composer composer2, int i12) {
                                            ComposerKt.sourceInformation(composer2, "C187@8335L201,193@8718L193:NavigationRail.kt#uh7d8r");
                                            if ((i12 & 3) != 2 || !composer2.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1023357515, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous> (NavigationRail.kt:186)");
                                                }
                                                State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationRailItemColors.this.m2487iconColorWaAFU9c$material3_release(z, z92), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer2, 48, 12);
                                                Modifier.Companion clearAndSetSemantics = (function242 == null || !(z82 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1.1
                                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                    }

                                                    @Override // kotlin.jvm.functions.Function1
                                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                        invoke2(semanticsPropertyReceiver);
                                                        return Unit.INSTANCE;
                                                    }
                                                });
                                                Function2<Composer, Integer, Unit> function25 = function2;
                                                ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                                CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                                                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer2, clearAndSetSemantics);
                                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                if (!(composer2.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                composer2.startReusableNode();
                                                if (composer2.getInserting()) {
                                                    composer2.createNode(constructor2);
                                                } else {
                                                    composer2.useNode();
                                                }
                                                Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer2);
                                                Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                                    m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                                    m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                                }
                                                Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                                ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart(composer2, -296474437, "C194@8819L78:NavigationRail.kt#uh7d8r");
                                                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function25, composer2, ProvidedValue.$stable);
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                composer2.endNode();
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer2.skipToGroupEnd();
                                        }

                                        private static final long invoke$lambda$0(State<Color> state) {
                                            return state.getValue().m4625unboximpl();
                                        }
                                    }, startRestartGroup, 54);
                                    startRestartGroup.startReplaceGroup(-782561860);
                                    ComposerKt.sourceInformation(startRestartGroup, "*200@9017L536");
                                    if (function23 != null) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    MutableInteractionSource mutableInteractionSource52 = mutableInteractionSource3;
                                    boolean z102 = z4;
                                    Modifier m937widthInVpY3zN4$default2 = SizeKt.m937widthInVpY3zN4$default(SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier42, z, mutableInteractionSource52, null, z102, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationRailItemHeight, 1, null), NavigationRailItemWidth, 0.0f, 2, null);
                                    Alignment center2 = Alignment.INSTANCE.getCenter();
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, true);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                                    CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, m937widthInVpY3zN4$default2);
                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(startRestartGroup.getApplier() instanceof Applier)) {
                                    }
                                    startRestartGroup.startReusableNode();
                                    if (!startRestartGroup.getInserting()) {
                                    }
                                    m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
                                    Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (!m3905constructorimpl.getInserting()) {
                                    }
                                    m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash2);
                                    Updater.m3912setimpl(m3905constructorimpl, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 584386773, "C231@10130L157,*240@10571L7,246@10874L128,260@11450L261,268@11760L280,282@12301L27,276@12050L289:NavigationRail.kt#uh7d8r");
                                    boolean z112 = z5;
                                    final State<Float> animateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                    Object consume2 = startRestartGroup.consume(localDensity2);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    Density density2 = (Density) consume2;
                                    long Offset2 = OffsetKt.Offset((density2.mo453roundToPx0680j_4(r2) - density2.mo453roundToPx0680j_4(NavigationRailTokens.INSTANCE.m3515getActiveIndicatorWidthD9Ej5fM())) / 2, 0.0f);
                                    Unit unit2 = Unit.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119672869, "CC(remember):NavigationRail.kt#9igjgp");
                                    changed = startRestartGroup.changed(mutableInteractionSource52) | startRestartGroup.changed(Offset2);
                                    rememberedValue = startRestartGroup.rememberedValue();
                                    if (!changed) {
                                    }
                                    rememberedValue = new MappedInteractionSource(mutableInteractionSource52, Offset2, null);
                                    startRestartGroup.updateRememberedValue(rememberedValue);
                                    final MappedInteractionSource mappedInteractionSource2 = (MappedInteractionSource) rememberedValue;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    if (function23 == null) {
                                    }
                                    ComposableLambda rememberComposableLambda32 = ComposableLambdaKt.rememberComposableLambda(211026382, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                            invoke(composer2, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer2, int i12) {
                                            ComposerKt.sourceInformation(composer2, "C264@11646L32,261@11468L229:NavigationRail.kt#uh7d8r");
                                            if ((i12 & 3) != 2 || !composer2.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(211026382, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:261)");
                                                }
                                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), Shape.this), mappedInteractionSource2, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer2, 0, 7)), composer2, 0);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer2.skipToGroupEnd();
                                        }
                                    }, startRestartGroup, 54);
                                    ComposableLambda rememberComposableLambda42 = ComposableLambdaKt.rememberComposableLambda(-1862011490, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                            invoke(composer2, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer2, int i12) {
                                            ComposerKt.sourceInformation(composer2, "C271@11882L35,269@11778L248:NavigationRail.kt#uh7d8r");
                                            if ((i12 & 3) != 2 || !composer2.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1862011490, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:269)");
                                                }
                                                Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                                                ComposerKt.sourceInformationMarkerStart(composer2, -425107715, "CC(remember):NavigationRail.kt#9igjgp");
                                                boolean changed3 = composer2.changed(animateFloatAsState2);
                                                final State<Float> state = animateFloatAsState2;
                                                Object rememberedValue4 = composer2.rememberedValue();
                                                if (changed3 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                    rememberedValue4 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1$1$1
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(1);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function1
                                                        public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                                            invoke2(graphicsLayerScope);
                                                            return Unit.INSTANCE;
                                                        }

                                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                        public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                                                            graphicsLayerScope.setAlpha(state.getValue().floatValue());
                                                        }
                                                    };
                                                    composer2.updateRememberedValue(rememberedValue4);
                                                }
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue4), navigationRailItemColors42.getSelectedIndicatorColor(), value), composer2, 0);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer2.skipToGroupEnd();
                                        }
                                    }, startRestartGroup, 54);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119627306, "CC(remember):NavigationRail.kt#9igjgp");
                                    changed2 = startRestartGroup.changed(animateFloatAsState2);
                                    rememberedValue2 = startRestartGroup.rememberedValue();
                                    if (!changed2) {
                                    }
                                    rememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$2$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Float invoke() {
                                            return animateFloatAsState2.getValue();
                                        }
                                    };
                                    startRestartGroup.updateRememberedValue(rememberedValue2);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    NavigationRailItemLayout(rememberComposableLambda32, rememberComposableLambda42, rememberComposableLambda5, rememberComposableLambda2, z112, (Function0) rememberedValue2, startRestartGroup, (57344 & (i10 >> 6)) | 438);
                                    startRestartGroup = startRestartGroup;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    startRestartGroup.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    z6 = z112;
                                    navigationRailItemColors3 = navigationRailItemColors42;
                                    mutableInteractionSource4 = mutableInteractionSource2;
                                    modifier3 = modifier42;
                                    z7 = z102;
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    navigationRailItemColors3 = navigationRailItemColors;
                                    modifier3 = modifier2;
                                    z6 = z5;
                                    z7 = z4;
                                    mutableInteractionSource4 = mutableInteractionSource;
                                }
                                Composer composer2 = startRestartGroup;
                                final Function2<? super Composer, ? super Integer, Unit> function25 = function23;
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$2
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
                                            NavigationRailKt.NavigationRailItem(z, function0, function2, modifier3, z7, function25, z6, navigationRailItemColors3, mutableInteractionSource4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            i9 = i8;
                            if ((i3 & 38347923) == 38347922) {
                            }
                            startRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                            }
                            if (i4 != 0) {
                            }
                            if (i5 != 0) {
                            }
                            if (i6 != 0) {
                            }
                            if (i7 != 0) {
                            }
                            if ((i2 & 128) != 0) {
                            }
                            if (i9 != 0) {
                            }
                            mutableInteractionSource2 = mutableInteractionSource;
                            i10 = i3;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.startReplaceGroup(-782588008);
                            ComposerKt.sourceInformation(startRestartGroup, "183@8207L39");
                            if (mutableInteractionSource2 != null) {
                            }
                            startRestartGroup.endReplaceGroup();
                            final NavigationRailItemColors navigationRailItemColors422 = navigationRailItemColors2;
                            final boolean z822 = z5;
                            final boolean z922 = z4;
                            final Function2<? super Composer, ? super Integer, Unit> function2422 = function23;
                            Modifier modifier422 = modifier2;
                            ComposableLambda rememberComposableLambda52 = ComposableLambdaKt.rememberComposableLambda(-1023357515, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer22, Integer num) {
                                    invoke(composer22, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer22, int i12) {
                                    ComposerKt.sourceInformation(composer22, "C187@8335L201,193@8718L193:NavigationRail.kt#uh7d8r");
                                    if ((i12 & 3) != 2 || !composer22.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1023357515, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous> (NavigationRail.kt:186)");
                                        }
                                        State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationRailItemColors.this.m2487iconColorWaAFU9c$material3_release(z, z922), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer22, 48, 12);
                                        Modifier.Companion clearAndSetSemantics = (function2422 == null || !(z822 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1.1
                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        Function2<Composer, Integer, Unit> function252 = function2;
                                        ComposerKt.sourceInformationMarkerStart(composer22, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy maybeCachedBoxMeasurePolicy22 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer22, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer22, 0);
                                        CompositionLocalMap currentCompositionLocalMap22 = composer22.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier22 = ComposedModifierKt.materializeModifier(composer22, clearAndSetSemantics);
                                        Function0<ComposeUiNode> constructor22 = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer22, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer22.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer22.startReusableNode();
                                        if (composer22.getInserting()) {
                                            composer22.createNode(constructor22);
                                        } else {
                                            composer22.useNode();
                                        }
                                        Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer22);
                                        Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash22 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                            m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                            m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash22);
                                        }
                                        Updater.m3912setimpl(m3905constructorimpl2, materializeModifier22, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer22, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance22 = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer22, -296474437, "C194@8819L78:NavigationRail.kt#uh7d8r");
                                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function252, composer22, ProvidedValue.$stable);
                                        ComposerKt.sourceInformationMarkerEnd(composer22);
                                        ComposerKt.sourceInformationMarkerEnd(composer22);
                                        composer22.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer22);
                                        ComposerKt.sourceInformationMarkerEnd(composer22);
                                        ComposerKt.sourceInformationMarkerEnd(composer22);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer22.skipToGroupEnd();
                                }

                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m4625unboximpl();
                                }
                            }, startRestartGroup, 54);
                            startRestartGroup.startReplaceGroup(-782561860);
                            ComposerKt.sourceInformation(startRestartGroup, "*200@9017L536");
                            if (function23 != null) {
                            }
                            startRestartGroup.endReplaceGroup();
                            MutableInteractionSource mutableInteractionSource522 = mutableInteractionSource3;
                            boolean z1022 = z4;
                            Modifier m937widthInVpY3zN4$default22 = SizeKt.m937widthInVpY3zN4$default(SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier422, z, mutableInteractionSource522, null, z1022, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationRailItemHeight, 1, null), NavigationRailItemWidth, 0.0f, 2, null);
                            Alignment center22 = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy maybeCachedBoxMeasurePolicy22 = BoxKt.maybeCachedBoxMeasurePolicy(center22, true);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap22 = startRestartGroup.getCurrentCompositionLocalMap();
                            Modifier materializeModifier22 = ComposedModifierKt.materializeModifier(startRestartGroup, m937widthInVpY3zN4$default22);
                            Function0<ComposeUiNode> constructor22 = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                            }
                            startRestartGroup.startReusableNode();
                            if (!startRestartGroup.getInserting()) {
                            }
                            m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
                            Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash22 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!m3905constructorimpl.getInserting()) {
                            }
                            m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash22);
                            Updater.m3912setimpl(m3905constructorimpl, materializeModifier22, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance22 = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 584386773, "C231@10130L157,*240@10571L7,246@10874L128,260@11450L261,268@11760L280,282@12301L27,276@12050L289:NavigationRail.kt#uh7d8r");
                            boolean z1122 = z5;
                            final State<Float> animateFloatAsState22 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                            ProvidableCompositionLocal<Density> localDensity22 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object consume22 = startRestartGroup.consume(localDensity22);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            Density density22 = (Density) consume22;
                            long Offset22 = OffsetKt.Offset((density22.mo453roundToPx0680j_4(r2) - density22.mo453roundToPx0680j_4(NavigationRailTokens.INSTANCE.m3515getActiveIndicatorWidthD9Ej5fM())) / 2, 0.0f);
                            Unit unit22 = Unit.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119672869, "CC(remember):NavigationRail.kt#9igjgp");
                            changed = startRestartGroup.changed(mutableInteractionSource522) | startRestartGroup.changed(Offset22);
                            rememberedValue = startRestartGroup.rememberedValue();
                            if (!changed) {
                            }
                            rememberedValue = new MappedInteractionSource(mutableInteractionSource522, Offset22, null);
                            startRestartGroup.updateRememberedValue(rememberedValue);
                            final MappedInteractionSource mappedInteractionSource22 = (MappedInteractionSource) rememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            if (function23 == null) {
                            }
                            ComposableLambda rememberComposableLambda322 = ComposableLambdaKt.rememberComposableLambda(211026382, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer22, Integer num) {
                                    invoke(composer22, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer22, int i12) {
                                    ComposerKt.sourceInformation(composer22, "C264@11646L32,261@11468L229:NavigationRail.kt#uh7d8r");
                                    if ((i12 & 3) != 2 || !composer22.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(211026382, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:261)");
                                        }
                                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), Shape.this), mappedInteractionSource22, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer22, 0, 7)), composer22, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer22.skipToGroupEnd();
                                }
                            }, startRestartGroup, 54);
                            ComposableLambda rememberComposableLambda422 = ComposableLambdaKt.rememberComposableLambda(-1862011490, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer22, Integer num) {
                                    invoke(composer22, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer22, int i12) {
                                    ComposerKt.sourceInformation(composer22, "C271@11882L35,269@11778L248:NavigationRail.kt#uh7d8r");
                                    if ((i12 & 3) != 2 || !composer22.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1862011490, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:269)");
                                        }
                                        Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                                        ComposerKt.sourceInformationMarkerStart(composer22, -425107715, "CC(remember):NavigationRail.kt#9igjgp");
                                        boolean changed3 = composer22.changed(animateFloatAsState22);
                                        final State<Float> state = animateFloatAsState22;
                                        Object rememberedValue4 = composer22.rememberedValue();
                                        if (changed3 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue4 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1$1$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                                    invoke2(graphicsLayerScope);
                                                    return Unit.INSTANCE;
                                                }

                                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                                                    graphicsLayerScope.setAlpha(state.getValue().floatValue());
                                                }
                                            };
                                            composer22.updateRememberedValue(rememberedValue4);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer22);
                                        BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue4), navigationRailItemColors422.getSelectedIndicatorColor(), value), composer22, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer22.skipToGroupEnd();
                                }
                            }, startRestartGroup, 54);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119627306, "CC(remember):NavigationRail.kt#9igjgp");
                            changed2 = startRestartGroup.changed(animateFloatAsState22);
                            rememberedValue2 = startRestartGroup.rememberedValue();
                            if (!changed2) {
                            }
                            rememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Float invoke() {
                                    return animateFloatAsState22.getValue();
                                }
                            };
                            startRestartGroup.updateRememberedValue(rememberedValue2);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            NavigationRailItemLayout(rememberComposableLambda322, rememberComposableLambda422, rememberComposableLambda52, rememberComposableLambda2, z1122, (Function0) rememberedValue2, startRestartGroup, (57344 & (i10 >> 6)) | 438);
                            startRestartGroup = startRestartGroup;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            startRestartGroup.endNode();
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            z6 = z1122;
                            navigationRailItemColors3 = navigationRailItemColors422;
                            mutableInteractionSource4 = mutableInteractionSource2;
                            modifier3 = modifier422;
                            z7 = z1022;
                            Composer composer22 = startRestartGroup;
                            final Function2<? super Composer, ? super Integer, Unit> function252 = function23;
                            endRestartGroup = composer22.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        function23 = function22;
                        i7 = i2 & 64;
                        if (i7 == 0) {
                        }
                        if ((i & 12582912) == 0) {
                        }
                        i8 = i2 & 256;
                        if (i8 == 0) {
                        }
                        i9 = i8;
                        if ((i3 & 38347923) == 38347922) {
                        }
                        startRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                        }
                        if (i4 != 0) {
                        }
                        if (i5 != 0) {
                        }
                        if (i6 != 0) {
                        }
                        if (i7 != 0) {
                        }
                        if ((i2 & 128) != 0) {
                        }
                        if (i9 != 0) {
                        }
                        mutableInteractionSource2 = mutableInteractionSource;
                        i10 = i3;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.startReplaceGroup(-782588008);
                        ComposerKt.sourceInformation(startRestartGroup, "183@8207L39");
                        if (mutableInteractionSource2 != null) {
                        }
                        startRestartGroup.endReplaceGroup();
                        final NavigationRailItemColors navigationRailItemColors4222 = navigationRailItemColors2;
                        final boolean z8222 = z5;
                        final boolean z9222 = z4;
                        final Function2<? super Composer, ? super Integer, Unit> function24222 = function23;
                        Modifier modifier4222 = modifier2;
                        ComposableLambda rememberComposableLambda522 = ComposableLambdaKt.rememberComposableLambda(-1023357515, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer222, Integer num) {
                                invoke(composer222, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer222, int i12) {
                                ComposerKt.sourceInformation(composer222, "C187@8335L201,193@8718L193:NavigationRail.kt#uh7d8r");
                                if ((i12 & 3) != 2 || !composer222.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1023357515, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous> (NavigationRail.kt:186)");
                                    }
                                    State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationRailItemColors.this.m2487iconColorWaAFU9c$material3_release(z, z9222), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer222, 48, 12);
                                    Modifier.Companion clearAndSetSemantics = (function24222 == null || !(z8222 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1.1
                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }
                                    });
                                    Function2<Composer, Integer, Unit> function2522 = function2;
                                    ComposerKt.sourceInformationMarkerStart(composer222, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy maybeCachedBoxMeasurePolicy222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer222, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer222, 0);
                                    CompositionLocalMap currentCompositionLocalMap222 = composer222.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier222 = ComposedModifierKt.materializeModifier(composer222, clearAndSetSemantics);
                                    Function0<ComposeUiNode> constructor222 = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer222, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer222.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer222.startReusableNode();
                                    if (composer222.getInserting()) {
                                        composer222.createNode(constructor222);
                                    } else {
                                        composer222.useNode();
                                    }
                                    Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer222);
                                    Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                        m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                        m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash222);
                                    }
                                    Updater.m3912setimpl(m3905constructorimpl2, materializeModifier222, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer222, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance222 = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer222, -296474437, "C194@8819L78:NavigationRail.kt#uh7d8r");
                                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function2522, composer222, ProvidedValue.$stable);
                                    ComposerKt.sourceInformationMarkerEnd(composer222);
                                    ComposerKt.sourceInformationMarkerEnd(composer222);
                                    composer222.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer222);
                                    ComposerKt.sourceInformationMarkerEnd(composer222);
                                    ComposerKt.sourceInformationMarkerEnd(composer222);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer222.skipToGroupEnd();
                            }

                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m4625unboximpl();
                            }
                        }, startRestartGroup, 54);
                        startRestartGroup.startReplaceGroup(-782561860);
                        ComposerKt.sourceInformation(startRestartGroup, "*200@9017L536");
                        if (function23 != null) {
                        }
                        startRestartGroup.endReplaceGroup();
                        MutableInteractionSource mutableInteractionSource5222 = mutableInteractionSource3;
                        boolean z10222 = z4;
                        Modifier m937widthInVpY3zN4$default222 = SizeKt.m937widthInVpY3zN4$default(SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier4222, z, mutableInteractionSource5222, null, z10222, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationRailItemHeight, 1, null), NavigationRailItemWidth, 0.0f, 2, null);
                        Alignment center222 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy222 = BoxKt.maybeCachedBoxMeasurePolicy(center222, true);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap222 = startRestartGroup.getCurrentCompositionLocalMap();
                        Modifier materializeModifier222 = ComposedModifierKt.materializeModifier(startRestartGroup, m937widthInVpY3zN4$default222);
                        Function0<ComposeUiNode> constructor222 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(startRestartGroup.getApplier() instanceof Applier)) {
                        }
                        startRestartGroup.startReusableNode();
                        if (!startRestartGroup.getInserting()) {
                        }
                        m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
                        Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!m3905constructorimpl.getInserting()) {
                        }
                        m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash222);
                        Updater.m3912setimpl(m3905constructorimpl, materializeModifier222, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance222 = BoxScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 584386773, "C231@10130L157,*240@10571L7,246@10874L128,260@11450L261,268@11760L280,282@12301L27,276@12050L289:NavigationRail.kt#uh7d8r");
                        boolean z11222 = z5;
                        final State<Float> animateFloatAsState222 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                        ProvidableCompositionLocal<Density> localDensity222 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object consume222 = startRestartGroup.consume(localDensity222);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        Density density222 = (Density) consume222;
                        long Offset222 = OffsetKt.Offset((density222.mo453roundToPx0680j_4(r2) - density222.mo453roundToPx0680j_4(NavigationRailTokens.INSTANCE.m3515getActiveIndicatorWidthD9Ej5fM())) / 2, 0.0f);
                        Unit unit222 = Unit.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119672869, "CC(remember):NavigationRail.kt#9igjgp");
                        changed = startRestartGroup.changed(mutableInteractionSource5222) | startRestartGroup.changed(Offset222);
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (!changed) {
                        }
                        rememberedValue = new MappedInteractionSource(mutableInteractionSource5222, Offset222, null);
                        startRestartGroup.updateRememberedValue(rememberedValue);
                        final MappedInteractionSource mappedInteractionSource222 = (MappedInteractionSource) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        if (function23 == null) {
                        }
                        ComposableLambda rememberComposableLambda3222 = ComposableLambdaKt.rememberComposableLambda(211026382, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer222, Integer num) {
                                invoke(composer222, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer222, int i12) {
                                ComposerKt.sourceInformation(composer222, "C264@11646L32,261@11468L229:NavigationRail.kt#uh7d8r");
                                if ((i12 & 3) != 2 || !composer222.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(211026382, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:261)");
                                    }
                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), Shape.this), mappedInteractionSource222, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer222, 0, 7)), composer222, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer222.skipToGroupEnd();
                            }
                        }, startRestartGroup, 54);
                        ComposableLambda rememberComposableLambda4222 = ComposableLambdaKt.rememberComposableLambda(-1862011490, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer222, Integer num) {
                                invoke(composer222, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer222, int i12) {
                                ComposerKt.sourceInformation(composer222, "C271@11882L35,269@11778L248:NavigationRail.kt#uh7d8r");
                                if ((i12 & 3) != 2 || !composer222.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1862011490, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:269)");
                                    }
                                    Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                                    ComposerKt.sourceInformationMarkerStart(composer222, -425107715, "CC(remember):NavigationRail.kt#9igjgp");
                                    boolean changed3 = composer222.changed(animateFloatAsState222);
                                    final State<Float> state = animateFloatAsState222;
                                    Object rememberedValue4 = composer222.rememberedValue();
                                    if (changed3 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue4 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1$1$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                                invoke2(graphicsLayerScope);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                                                graphicsLayerScope.setAlpha(state.getValue().floatValue());
                                            }
                                        };
                                        composer222.updateRememberedValue(rememberedValue4);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer222);
                                    BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue4), navigationRailItemColors4222.getSelectedIndicatorColor(), value), composer222, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer222.skipToGroupEnd();
                            }
                        }, startRestartGroup, 54);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119627306, "CC(remember):NavigationRail.kt#9igjgp");
                        changed2 = startRestartGroup.changed(animateFloatAsState222);
                        rememberedValue2 = startRestartGroup.rememberedValue();
                        if (!changed2) {
                        }
                        rememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return animateFloatAsState222.getValue();
                            }
                        };
                        startRestartGroup.updateRememberedValue(rememberedValue2);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        NavigationRailItemLayout(rememberComposableLambda3222, rememberComposableLambda4222, rememberComposableLambda522, rememberComposableLambda2, z11222, (Function0) rememberedValue2, startRestartGroup, (57344 & (i10 >> 6)) | 438);
                        startRestartGroup = startRestartGroup;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        startRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        z6 = z11222;
                        navigationRailItemColors3 = navigationRailItemColors4222;
                        mutableInteractionSource4 = mutableInteractionSource2;
                        modifier3 = modifier4222;
                        z7 = z10222;
                        Composer composer222 = startRestartGroup;
                        final Function2<? super Composer, ? super Integer, Unit> function2522 = function23;
                        endRestartGroup = composer222.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    z4 = z2;
                    i6 = i2 & 32;
                    if (i6 != 0) {
                    }
                    function23 = function22;
                    i7 = i2 & 64;
                    if (i7 == 0) {
                    }
                    if ((i & 12582912) == 0) {
                    }
                    i8 = i2 & 256;
                    if (i8 == 0) {
                    }
                    i9 = i8;
                    if ((i3 & 38347923) == 38347922) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                    }
                    if (i4 != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if (i6 != 0) {
                    }
                    if (i7 != 0) {
                    }
                    if ((i2 & 128) != 0) {
                    }
                    if (i9 != 0) {
                    }
                    mutableInteractionSource2 = mutableInteractionSource;
                    i10 = i3;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.startReplaceGroup(-782588008);
                    ComposerKt.sourceInformation(startRestartGroup, "183@8207L39");
                    if (mutableInteractionSource2 != null) {
                    }
                    startRestartGroup.endReplaceGroup();
                    final NavigationRailItemColors navigationRailItemColors42222 = navigationRailItemColors2;
                    final boolean z82222 = z5;
                    final boolean z92222 = z4;
                    final Function2<? super Composer, ? super Integer, Unit> function242222 = function23;
                    Modifier modifier42222 = modifier2;
                    ComposableLambda rememberComposableLambda5222 = ComposableLambdaKt.rememberComposableLambda(-1023357515, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2222, Integer num) {
                            invoke(composer2222, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2222, int i12) {
                            ComposerKt.sourceInformation(composer2222, "C187@8335L201,193@8718L193:NavigationRail.kt#uh7d8r");
                            if ((i12 & 3) != 2 || !composer2222.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1023357515, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous> (NavigationRail.kt:186)");
                                }
                                State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationRailItemColors.this.m2487iconColorWaAFU9c$material3_release(z, z92222), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer2222, 48, 12);
                                Modifier.Companion clearAndSetSemantics = (function242222 == null || !(z82222 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1.1
                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }
                                });
                                Function2<Composer, Integer, Unit> function25222 = function2;
                                ComposerKt.sourceInformationMarkerStart(composer2222, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy maybeCachedBoxMeasurePolicy2222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer2222, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2222, 0);
                                CompositionLocalMap currentCompositionLocalMap2222 = composer2222.getCurrentCompositionLocalMap();
                                Modifier materializeModifier2222 = ComposedModifierKt.materializeModifier(composer2222, clearAndSetSemantics);
                                Function0<ComposeUiNode> constructor2222 = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer2222, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer2222.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2222.startReusableNode();
                                if (composer2222.getInserting()) {
                                    composer2222.createNode(constructor2222);
                                } else {
                                    composer2222.useNode();
                                }
                                Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer2222);
                                Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy2222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                    m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                    m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2222);
                                }
                                Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2222, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer2222, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance2222 = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2222, -296474437, "C194@8819L78:NavigationRail.kt#uh7d8r");
                                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function25222, composer2222, ProvidedValue.$stable);
                                ComposerKt.sourceInformationMarkerEnd(composer2222);
                                ComposerKt.sourceInformationMarkerEnd(composer2222);
                                composer2222.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer2222);
                                ComposerKt.sourceInformationMarkerEnd(composer2222);
                                ComposerKt.sourceInformationMarkerEnd(composer2222);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2222.skipToGroupEnd();
                        }

                        private static final long invoke$lambda$0(State<Color> state) {
                            return state.getValue().m4625unboximpl();
                        }
                    }, startRestartGroup, 54);
                    startRestartGroup.startReplaceGroup(-782561860);
                    ComposerKt.sourceInformation(startRestartGroup, "*200@9017L536");
                    if (function23 != null) {
                    }
                    startRestartGroup.endReplaceGroup();
                    MutableInteractionSource mutableInteractionSource52222 = mutableInteractionSource3;
                    boolean z102222 = z4;
                    Modifier m937widthInVpY3zN4$default2222 = SizeKt.m937widthInVpY3zN4$default(SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier42222, z, mutableInteractionSource52222, null, z102222, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationRailItemHeight, 1, null), NavigationRailItemWidth, 0.0f, 2, null);
                    Alignment center2222 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy2222 = BoxKt.maybeCachedBoxMeasurePolicy(center2222, true);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap2222 = startRestartGroup.getCurrentCompositionLocalMap();
                    Modifier materializeModifier2222 = ComposedModifierKt.materializeModifier(startRestartGroup, m937widthInVpY3zN4$default2222);
                    Function0<ComposeUiNode> constructor2222 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(startRestartGroup.getApplier() instanceof Applier)) {
                    }
                    startRestartGroup.startReusableNode();
                    if (!startRestartGroup.getInserting()) {
                    }
                    m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
                    Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy2222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap2222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!m3905constructorimpl.getInserting()) {
                    }
                    m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash2222);
                    Updater.m3912setimpl(m3905constructorimpl, materializeModifier2222, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2222 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 584386773, "C231@10130L157,*240@10571L7,246@10874L128,260@11450L261,268@11760L280,282@12301L27,276@12050L289:NavigationRail.kt#uh7d8r");
                    boolean z112222 = z5;
                    final State<Float> animateFloatAsState2222 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                    ProvidableCompositionLocal<Density> localDensity2222 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume2222 = startRestartGroup.consume(localDensity2222);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    Density density2222 = (Density) consume2222;
                    long Offset2222 = OffsetKt.Offset((density2222.mo453roundToPx0680j_4(r2) - density2222.mo453roundToPx0680j_4(NavigationRailTokens.INSTANCE.m3515getActiveIndicatorWidthD9Ej5fM())) / 2, 0.0f);
                    Unit unit2222 = Unit.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119672869, "CC(remember):NavigationRail.kt#9igjgp");
                    changed = startRestartGroup.changed(mutableInteractionSource52222) | startRestartGroup.changed(Offset2222);
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (!changed) {
                    }
                    rememberedValue = new MappedInteractionSource(mutableInteractionSource52222, Offset2222, null);
                    startRestartGroup.updateRememberedValue(rememberedValue);
                    final MappedInteractionSource mappedInteractionSource2222 = (MappedInteractionSource) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    if (function23 == null) {
                    }
                    ComposableLambda rememberComposableLambda32222 = ComposableLambdaKt.rememberComposableLambda(211026382, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2222, Integer num) {
                            invoke(composer2222, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2222, int i12) {
                            ComposerKt.sourceInformation(composer2222, "C264@11646L32,261@11468L229:NavigationRail.kt#uh7d8r");
                            if ((i12 & 3) != 2 || !composer2222.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(211026382, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:261)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), Shape.this), mappedInteractionSource2222, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer2222, 0, 7)), composer2222, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2222.skipToGroupEnd();
                        }
                    }, startRestartGroup, 54);
                    ComposableLambda rememberComposableLambda42222 = ComposableLambdaKt.rememberComposableLambda(-1862011490, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2222, Integer num) {
                            invoke(composer2222, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2222, int i12) {
                            ComposerKt.sourceInformation(composer2222, "C271@11882L35,269@11778L248:NavigationRail.kt#uh7d8r");
                            if ((i12 & 3) != 2 || !composer2222.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1862011490, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:269)");
                                }
                                Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                                ComposerKt.sourceInformationMarkerStart(composer2222, -425107715, "CC(remember):NavigationRail.kt#9igjgp");
                                boolean changed3 = composer2222.changed(animateFloatAsState2222);
                                final State<Float> state = animateFloatAsState2222;
                                Object rememberedValue4 = composer2222.rememberedValue();
                                if (changed3 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue4 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                            invoke2(graphicsLayerScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                                            graphicsLayerScope.setAlpha(state.getValue().floatValue());
                                        }
                                    };
                                    composer2222.updateRememberedValue(rememberedValue4);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer2222);
                                BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue4), navigationRailItemColors42222.getSelectedIndicatorColor(), value), composer2222, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2222.skipToGroupEnd();
                        }
                    }, startRestartGroup, 54);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119627306, "CC(remember):NavigationRail.kt#9igjgp");
                    changed2 = startRestartGroup.changed(animateFloatAsState2222);
                    rememberedValue2 = startRestartGroup.rememberedValue();
                    if (!changed2) {
                    }
                    rememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return animateFloatAsState2222.getValue();
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    NavigationRailItemLayout(rememberComposableLambda32222, rememberComposableLambda42222, rememberComposableLambda5222, rememberComposableLambda2, z112222, (Function0) rememberedValue2, startRestartGroup, (57344 & (i10 >> 6)) | 438);
                    startRestartGroup = startRestartGroup;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    startRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    z6 = z112222;
                    navigationRailItemColors3 = navigationRailItemColors42222;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    modifier3 = modifier42222;
                    z7 = z102222;
                    Composer composer2222 = startRestartGroup;
                    final Function2<? super Composer, ? super Integer, Unit> function25222 = function23;
                    endRestartGroup = composer2222.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                modifier2 = modifier;
                i5 = i2 & 16;
                if (i5 == 0) {
                }
                z4 = z2;
                i6 = i2 & 32;
                if (i6 != 0) {
                }
                function23 = function22;
                i7 = i2 & 64;
                if (i7 == 0) {
                }
                if ((i & 12582912) == 0) {
                }
                i8 = i2 & 256;
                if (i8 == 0) {
                }
                i9 = i8;
                if ((i3 & 38347923) == 38347922) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i4 != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                if (i7 != 0) {
                }
                if ((i2 & 128) != 0) {
                }
                if (i9 != 0) {
                }
                mutableInteractionSource2 = mutableInteractionSource;
                i10 = i3;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.startReplaceGroup(-782588008);
                ComposerKt.sourceInformation(startRestartGroup, "183@8207L39");
                if (mutableInteractionSource2 != null) {
                }
                startRestartGroup.endReplaceGroup();
                final NavigationRailItemColors navigationRailItemColors422222 = navigationRailItemColors2;
                final boolean z822222 = z5;
                final boolean z922222 = z4;
                final Function2<? super Composer, ? super Integer, Unit> function2422222 = function23;
                Modifier modifier422222 = modifier2;
                ComposableLambda rememberComposableLambda52222 = ComposableLambdaKt.rememberComposableLambda(-1023357515, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer22222, Integer num) {
                        invoke(composer22222, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer22222, int i12) {
                        ComposerKt.sourceInformation(composer22222, "C187@8335L201,193@8718L193:NavigationRail.kt#uh7d8r");
                        if ((i12 & 3) != 2 || !composer22222.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1023357515, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous> (NavigationRail.kt:186)");
                            }
                            State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationRailItemColors.this.m2487iconColorWaAFU9c$material3_release(z, z922222), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer22222, 48, 12);
                            Modifier.Companion clearAndSetSemantics = (function2422222 == null || !(z822222 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1.1
                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }
                            });
                            Function2<Composer, Integer, Unit> function252222 = function2;
                            ComposerKt.sourceInformationMarkerStart(composer22222, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy maybeCachedBoxMeasurePolicy22222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer22222, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer22222, 0);
                            CompositionLocalMap currentCompositionLocalMap22222 = composer22222.getCurrentCompositionLocalMap();
                            Modifier materializeModifier22222 = ComposedModifierKt.materializeModifier(composer22222, clearAndSetSemantics);
                            Function0<ComposeUiNode> constructor22222 = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer22222, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer22222.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer22222.startReusableNode();
                            if (composer22222.getInserting()) {
                                composer22222.createNode(constructor22222);
                            } else {
                                composer22222.useNode();
                            }
                            Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer22222);
                            Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy22222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap22222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash22222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash22222);
                            }
                            Updater.m3912setimpl(m3905constructorimpl2, materializeModifier22222, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer22222, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance22222 = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer22222, -296474437, "C194@8819L78:NavigationRail.kt#uh7d8r");
                            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function252222, composer22222, ProvidedValue.$stable);
                            ComposerKt.sourceInformationMarkerEnd(composer22222);
                            ComposerKt.sourceInformationMarkerEnd(composer22222);
                            composer22222.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer22222);
                            ComposerKt.sourceInformationMarkerEnd(composer22222);
                            ComposerKt.sourceInformationMarkerEnd(composer22222);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer22222.skipToGroupEnd();
                    }

                    private static final long invoke$lambda$0(State<Color> state) {
                        return state.getValue().m4625unboximpl();
                    }
                }, startRestartGroup, 54);
                startRestartGroup.startReplaceGroup(-782561860);
                ComposerKt.sourceInformation(startRestartGroup, "*200@9017L536");
                if (function23 != null) {
                }
                startRestartGroup.endReplaceGroup();
                MutableInteractionSource mutableInteractionSource522222 = mutableInteractionSource3;
                boolean z1022222 = z4;
                Modifier m937widthInVpY3zN4$default22222 = SizeKt.m937widthInVpY3zN4$default(SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier422222, z, mutableInteractionSource522222, null, z1022222, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationRailItemHeight, 1, null), NavigationRailItemWidth, 0.0f, 2, null);
                Alignment center22222 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy22222 = BoxKt.maybeCachedBoxMeasurePolicy(center22222, true);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap22222 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier22222 = ComposedModifierKt.materializeModifier(startRestartGroup, m937widthInVpY3zN4$default22222);
                Function0<ComposeUiNode> constructor22222 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                }
                startRestartGroup.startReusableNode();
                if (!startRestartGroup.getInserting()) {
                }
                m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
                Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy22222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap22222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash22222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!m3905constructorimpl.getInserting()) {
                }
                m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash22222);
                Updater.m3912setimpl(m3905constructorimpl, materializeModifier22222, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance22222 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 584386773, "C231@10130L157,*240@10571L7,246@10874L128,260@11450L261,268@11760L280,282@12301L27,276@12050L289:NavigationRail.kt#uh7d8r");
                boolean z1122222 = z5;
                final State<Float> animateFloatAsState22222 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                ProvidableCompositionLocal<Density> localDensity22222 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume22222 = startRestartGroup.consume(localDensity22222);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Density density22222 = (Density) consume22222;
                long Offset22222 = OffsetKt.Offset((density22222.mo453roundToPx0680j_4(r2) - density22222.mo453roundToPx0680j_4(NavigationRailTokens.INSTANCE.m3515getActiveIndicatorWidthD9Ej5fM())) / 2, 0.0f);
                Unit unit22222 = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119672869, "CC(remember):NavigationRail.kt#9igjgp");
                changed = startRestartGroup.changed(mutableInteractionSource522222) | startRestartGroup.changed(Offset22222);
                rememberedValue = startRestartGroup.rememberedValue();
                if (!changed) {
                }
                rememberedValue = new MappedInteractionSource(mutableInteractionSource522222, Offset22222, null);
                startRestartGroup.updateRememberedValue(rememberedValue);
                final MappedInteractionSource mappedInteractionSource22222 = (MappedInteractionSource) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                if (function23 == null) {
                }
                ComposableLambda rememberComposableLambda322222 = ComposableLambdaKt.rememberComposableLambda(211026382, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer22222, Integer num) {
                        invoke(composer22222, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer22222, int i12) {
                        ComposerKt.sourceInformation(composer22222, "C264@11646L32,261@11468L229:NavigationRail.kt#uh7d8r");
                        if ((i12 & 3) != 2 || !composer22222.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(211026382, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:261)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), Shape.this), mappedInteractionSource22222, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer22222, 0, 7)), composer22222, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer22222.skipToGroupEnd();
                    }
                }, startRestartGroup, 54);
                ComposableLambda rememberComposableLambda422222 = ComposableLambdaKt.rememberComposableLambda(-1862011490, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer22222, Integer num) {
                        invoke(composer22222, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer22222, int i12) {
                        ComposerKt.sourceInformation(composer22222, "C271@11882L35,269@11778L248:NavigationRail.kt#uh7d8r");
                        if ((i12 & 3) != 2 || !composer22222.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1862011490, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:269)");
                            }
                            Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                            ComposerKt.sourceInformationMarkerStart(composer22222, -425107715, "CC(remember):NavigationRail.kt#9igjgp");
                            boolean changed3 = composer22222.changed(animateFloatAsState22222);
                            final State<Float> state = animateFloatAsState22222;
                            Object rememberedValue4 = composer22222.rememberedValue();
                            if (changed3 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                rememberedValue4 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                        invoke2(graphicsLayerScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                                        graphicsLayerScope.setAlpha(state.getValue().floatValue());
                                    }
                                };
                                composer22222.updateRememberedValue(rememberedValue4);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer22222);
                            BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue4), navigationRailItemColors422222.getSelectedIndicatorColor(), value), composer22222, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer22222.skipToGroupEnd();
                    }
                }, startRestartGroup, 54);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119627306, "CC(remember):NavigationRail.kt#9igjgp");
                changed2 = startRestartGroup.changed(animateFloatAsState22222);
                rememberedValue2 = startRestartGroup.rememberedValue();
                if (!changed2) {
                }
                rememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return animateFloatAsState22222.getValue();
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue2);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                NavigationRailItemLayout(rememberComposableLambda322222, rememberComposableLambda422222, rememberComposableLambda52222, rememberComposableLambda2, z1122222, (Function0) rememberedValue2, startRestartGroup, (57344 & (i10 >> 6)) | 438);
                startRestartGroup = startRestartGroup;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                }
                z6 = z1122222;
                navigationRailItemColors3 = navigationRailItemColors422222;
                mutableInteractionSource4 = mutableInteractionSource2;
                modifier3 = modifier422222;
                z7 = z1022222;
                Composer composer22222 = startRestartGroup;
                final Function2<? super Composer, ? super Integer, Unit> function252222 = function23;
                endRestartGroup = composer22222.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            i4 = i2 & 8;
            if (i4 != 0) {
            }
            modifier2 = modifier;
            i5 = i2 & 16;
            if (i5 == 0) {
            }
            z4 = z2;
            i6 = i2 & 32;
            if (i6 != 0) {
            }
            function23 = function22;
            i7 = i2 & 64;
            if (i7 == 0) {
            }
            if ((i & 12582912) == 0) {
            }
            i8 = i2 & 256;
            if (i8 == 0) {
            }
            i9 = i8;
            if ((i3 & 38347923) == 38347922) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i4 != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            if (i7 != 0) {
            }
            if ((i2 & 128) != 0) {
            }
            if (i9 != 0) {
            }
            mutableInteractionSource2 = mutableInteractionSource;
            i10 = i3;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.startReplaceGroup(-782588008);
            ComposerKt.sourceInformation(startRestartGroup, "183@8207L39");
            if (mutableInteractionSource2 != null) {
            }
            startRestartGroup.endReplaceGroup();
            final NavigationRailItemColors navigationRailItemColors4222222 = navigationRailItemColors2;
            final boolean z8222222 = z5;
            final boolean z9222222 = z4;
            final Function2<? super Composer, ? super Integer, Unit> function24222222 = function23;
            Modifier modifier4222222 = modifier2;
            ComposableLambda rememberComposableLambda522222 = ComposableLambdaKt.rememberComposableLambda(-1023357515, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer222222, Integer num) {
                    invoke(composer222222, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer222222, int i12) {
                    ComposerKt.sourceInformation(composer222222, "C187@8335L201,193@8718L193:NavigationRail.kt#uh7d8r");
                    if ((i12 & 3) != 2 || !composer222222.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1023357515, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous> (NavigationRail.kt:186)");
                        }
                        State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationRailItemColors.this.m2487iconColorWaAFU9c$material3_release(z, z9222222), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer222222, 48, 12);
                        Modifier.Companion clearAndSetSemantics = (function24222222 == null || !(z8222222 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1.1
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }
                        });
                        Function2<Composer, Integer, Unit> function2522222 = function2;
                        ComposerKt.sourceInformationMarkerStart(composer222222, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy222222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart(composer222222, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer222222, 0);
                        CompositionLocalMap currentCompositionLocalMap222222 = composer222222.getCurrentCompositionLocalMap();
                        Modifier materializeModifier222222 = ComposedModifierKt.materializeModifier(composer222222, clearAndSetSemantics);
                        Function0<ComposeUiNode> constructor222222 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer222222, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer222222.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer222222.startReusableNode();
                        if (composer222222.getInserting()) {
                            composer222222.createNode(constructor222222);
                        } else {
                            composer222222.useNode();
                        }
                        Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer222222);
                        Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                            m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                            m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash222222);
                        }
                        Updater.m3912setimpl(m3905constructorimpl2, materializeModifier222222, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer222222, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance222222 = BoxScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer222222, -296474437, "C194@8819L78:NavigationRail.kt#uh7d8r");
                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function2522222, composer222222, ProvidedValue.$stable);
                        ComposerKt.sourceInformationMarkerEnd(composer222222);
                        ComposerKt.sourceInformationMarkerEnd(composer222222);
                        composer222222.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer222222);
                        ComposerKt.sourceInformationMarkerEnd(composer222222);
                        ComposerKt.sourceInformationMarkerEnd(composer222222);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer222222.skipToGroupEnd();
                }

                private static final long invoke$lambda$0(State<Color> state) {
                    return state.getValue().m4625unboximpl();
                }
            }, startRestartGroup, 54);
            startRestartGroup.startReplaceGroup(-782561860);
            ComposerKt.sourceInformation(startRestartGroup, "*200@9017L536");
            if (function23 != null) {
            }
            startRestartGroup.endReplaceGroup();
            MutableInteractionSource mutableInteractionSource5222222 = mutableInteractionSource3;
            boolean z10222222 = z4;
            Modifier m937widthInVpY3zN4$default222222 = SizeKt.m937widthInVpY3zN4$default(SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier4222222, z, mutableInteractionSource5222222, null, z10222222, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationRailItemHeight, 1, null), NavigationRailItemWidth, 0.0f, 2, null);
            Alignment center222222 = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy222222 = BoxKt.maybeCachedBoxMeasurePolicy(center222222, true);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap222222 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier222222 = ComposedModifierKt.materializeModifier(startRestartGroup, m937widthInVpY3zN4$default222222);
            Function0<ComposeUiNode> constructor222222 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
            }
            startRestartGroup.startReusableNode();
            if (!startRestartGroup.getInserting()) {
            }
            m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
            Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!m3905constructorimpl.getInserting()) {
            }
            m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash222222);
            Updater.m3912setimpl(m3905constructorimpl, materializeModifier222222, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance222222 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 584386773, "C231@10130L157,*240@10571L7,246@10874L128,260@11450L261,268@11760L280,282@12301L27,276@12050L289:NavigationRail.kt#uh7d8r");
            boolean z11222222 = z5;
            final State<Float> animateFloatAsState222222 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
            ProvidableCompositionLocal<Density> localDensity222222 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume222222 = startRestartGroup.consume(localDensity222222);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Density density222222 = (Density) consume222222;
            long Offset222222 = OffsetKt.Offset((density222222.mo453roundToPx0680j_4(r2) - density222222.mo453roundToPx0680j_4(NavigationRailTokens.INSTANCE.m3515getActiveIndicatorWidthD9Ej5fM())) / 2, 0.0f);
            Unit unit222222 = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119672869, "CC(remember):NavigationRail.kt#9igjgp");
            changed = startRestartGroup.changed(mutableInteractionSource5222222) | startRestartGroup.changed(Offset222222);
            rememberedValue = startRestartGroup.rememberedValue();
            if (!changed) {
            }
            rememberedValue = new MappedInteractionSource(mutableInteractionSource5222222, Offset222222, null);
            startRestartGroup.updateRememberedValue(rememberedValue);
            final MappedInteractionSource mappedInteractionSource222222 = (MappedInteractionSource) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (function23 == null) {
            }
            ComposableLambda rememberComposableLambda3222222 = ComposableLambdaKt.rememberComposableLambda(211026382, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer222222, Integer num) {
                    invoke(composer222222, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer222222, int i12) {
                    ComposerKt.sourceInformation(composer222222, "C264@11646L32,261@11468L229:NavigationRail.kt#uh7d8r");
                    if ((i12 & 3) != 2 || !composer222222.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(211026382, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:261)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), Shape.this), mappedInteractionSource222222, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer222222, 0, 7)), composer222222, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer222222.skipToGroupEnd();
                }
            }, startRestartGroup, 54);
            ComposableLambda rememberComposableLambda4222222 = ComposableLambdaKt.rememberComposableLambda(-1862011490, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer222222, Integer num) {
                    invoke(composer222222, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer222222, int i12) {
                    ComposerKt.sourceInformation(composer222222, "C271@11882L35,269@11778L248:NavigationRail.kt#uh7d8r");
                    if ((i12 & 3) != 2 || !composer222222.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1862011490, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:269)");
                        }
                        Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                        ComposerKt.sourceInformationMarkerStart(composer222222, -425107715, "CC(remember):NavigationRail.kt#9igjgp");
                        boolean changed3 = composer222222.changed(animateFloatAsState222222);
                        final State<Float> state = animateFloatAsState222222;
                        Object rememberedValue4 = composer222222.rememberedValue();
                        if (changed3 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue4 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                    invoke2(graphicsLayerScope);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                                    graphicsLayerScope.setAlpha(state.getValue().floatValue());
                                }
                            };
                            composer222222.updateRememberedValue(rememberedValue4);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer222222);
                        BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue4), navigationRailItemColors4222222.getSelectedIndicatorColor(), value), composer222222, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer222222.skipToGroupEnd();
                }
            }, startRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119627306, "CC(remember):NavigationRail.kt#9igjgp");
            changed2 = startRestartGroup.changed(animateFloatAsState222222);
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (!changed2) {
            }
            rememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return animateFloatAsState222222.getValue();
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue2);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            NavigationRailItemLayout(rememberComposableLambda3222222, rememberComposableLambda4222222, rememberComposableLambda522222, rememberComposableLambda2, z11222222, (Function0) rememberedValue2, startRestartGroup, (57344 & (i10 >> 6)) | 438);
            startRestartGroup = startRestartGroup;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
            }
            z6 = z11222222;
            navigationRailItemColors3 = navigationRailItemColors4222222;
            mutableInteractionSource4 = mutableInteractionSource2;
            modifier3 = modifier4222222;
            z7 = z10222222;
            Composer composer222222 = startRestartGroup;
            final Function2<? super Composer, ? super Integer, Unit> function2522222 = function23;
            endRestartGroup = composer222222.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        if ((i2 & 4) == 0) {
        }
        i4 = i2 & 8;
        if (i4 != 0) {
        }
        modifier2 = modifier;
        i5 = i2 & 16;
        if (i5 == 0) {
        }
        z4 = z2;
        i6 = i2 & 32;
        if (i6 != 0) {
        }
        function23 = function22;
        i7 = i2 & 64;
        if (i7 == 0) {
        }
        if ((i & 12582912) == 0) {
        }
        i8 = i2 & 256;
        if (i8 == 0) {
        }
        i9 = i8;
        if ((i3 & 38347923) == 38347922) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i4 != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        if (i7 != 0) {
        }
        if ((i2 & 128) != 0) {
        }
        if (i9 != 0) {
        }
        mutableInteractionSource2 = mutableInteractionSource;
        i10 = i3;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.startReplaceGroup(-782588008);
        ComposerKt.sourceInformation(startRestartGroup, "183@8207L39");
        if (mutableInteractionSource2 != null) {
        }
        startRestartGroup.endReplaceGroup();
        final NavigationRailItemColors navigationRailItemColors42222222 = navigationRailItemColors2;
        final boolean z82222222 = z5;
        final boolean z92222222 = z4;
        final Function2<? super Composer, ? super Integer, Unit> function242222222 = function23;
        Modifier modifier42222222 = modifier2;
        ComposableLambda rememberComposableLambda5222222 = ComposableLambdaKt.rememberComposableLambda(-1023357515, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2222222, Integer num) {
                invoke(composer2222222, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2222222, int i12) {
                ComposerKt.sourceInformation(composer2222222, "C187@8335L201,193@8718L193:NavigationRail.kt#uh7d8r");
                if ((i12 & 3) != 2 || !composer2222222.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1023357515, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous> (NavigationRail.kt:186)");
                    }
                    State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationRailItemColors.this.m2487iconColorWaAFU9c$material3_release(z, z92222222), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer2222222, 48, 12);
                    Modifier.Companion clearAndSetSemantics = (function242222222 == null || !(z82222222 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledIcon$1.1
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }
                    });
                    Function2<Composer, Integer, Unit> function25222222 = function2;
                    ComposerKt.sourceInformationMarkerStart(composer2222222, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy2222222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composer2222222, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2222222, 0);
                    CompositionLocalMap currentCompositionLocalMap2222222 = composer2222222.getCurrentCompositionLocalMap();
                    Modifier materializeModifier2222222 = ComposedModifierKt.materializeModifier(composer2222222, clearAndSetSemantics);
                    Function0<ComposeUiNode> constructor2222222 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composer2222222, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composer2222222.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2222222.startReusableNode();
                    if (composer2222222.getInserting()) {
                        composer2222222.createNode(constructor2222222);
                    } else {
                        composer2222222.useNode();
                    }
                    Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer2222222);
                    Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy2222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                        m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2222222);
                    }
                    Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2222222, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composer2222222, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2222222 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composer2222222, -296474437, "C194@8819L78:NavigationRail.kt#uh7d8r");
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function25222222, composer2222222, ProvidedValue.$stable);
                    ComposerKt.sourceInformationMarkerEnd(composer2222222);
                    ComposerKt.sourceInformationMarkerEnd(composer2222222);
                    composer2222222.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2222222);
                    ComposerKt.sourceInformationMarkerEnd(composer2222222);
                    ComposerKt.sourceInformationMarkerEnd(composer2222222);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer2222222.skipToGroupEnd();
            }

            private static final long invoke$lambda$0(State<Color> state) {
                return state.getValue().m4625unboximpl();
            }
        }, startRestartGroup, 54);
        startRestartGroup.startReplaceGroup(-782561860);
        ComposerKt.sourceInformation(startRestartGroup, "*200@9017L536");
        if (function23 != null) {
        }
        startRestartGroup.endReplaceGroup();
        MutableInteractionSource mutableInteractionSource52222222 = mutableInteractionSource3;
        boolean z102222222 = z4;
        Modifier m937widthInVpY3zN4$default2222222 = SizeKt.m937widthInVpY3zN4$default(SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier42222222, z, mutableInteractionSource52222222, null, z102222222, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationRailItemHeight, 1, null), NavigationRailItemWidth, 0.0f, 2, null);
        Alignment center2222222 = Alignment.INSTANCE.getCenter();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
        MeasurePolicy maybeCachedBoxMeasurePolicy2222222 = BoxKt.maybeCachedBoxMeasurePolicy(center2222222, true);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
        CompositionLocalMap currentCompositionLocalMap2222222 = startRestartGroup.getCurrentCompositionLocalMap();
        Modifier materializeModifier2222222 = ComposedModifierKt.materializeModifier(startRestartGroup, m937widthInVpY3zN4$default2222222);
        Function0<ComposeUiNode> constructor2222222 = ComposeUiNode.INSTANCE.getConstructor();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
        if (!(startRestartGroup.getApplier() instanceof Applier)) {
        }
        startRestartGroup.startReusableNode();
        if (!startRestartGroup.getInserting()) {
        }
        m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
        Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy2222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap2222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if (!m3905constructorimpl.getInserting()) {
        }
        m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
        m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash2222222);
        Updater.m3912setimpl(m3905constructorimpl, materializeModifier2222222, ComposeUiNode.INSTANCE.getSetModifier());
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
        BoxScopeInstance boxScopeInstance2222222 = BoxScopeInstance.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 584386773, "C231@10130L157,*240@10571L7,246@10874L128,260@11450L261,268@11760L280,282@12301L27,276@12050L289:NavigationRail.kt#uh7d8r");
        boolean z112222222 = z5;
        final State<Float> animateFloatAsState2222222 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
        ProvidableCompositionLocal<Density> localDensity2222222 = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2222222 = startRestartGroup.consume(localDensity2222222);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Density density2222222 = (Density) consume2222222;
        long Offset2222222 = OffsetKt.Offset((density2222222.mo453roundToPx0680j_4(r2) - density2222222.mo453roundToPx0680j_4(NavigationRailTokens.INSTANCE.m3515getActiveIndicatorWidthD9Ej5fM())) / 2, 0.0f);
        Unit unit2222222 = Unit.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119672869, "CC(remember):NavigationRail.kt#9igjgp");
        changed = startRestartGroup.changed(mutableInteractionSource52222222) | startRestartGroup.changed(Offset2222222);
        rememberedValue = startRestartGroup.rememberedValue();
        if (!changed) {
        }
        rememberedValue = new MappedInteractionSource(mutableInteractionSource52222222, Offset2222222, null);
        startRestartGroup.updateRememberedValue(rememberedValue);
        final MappedInteractionSource mappedInteractionSource2222222 = (MappedInteractionSource) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        if (function23 == null) {
        }
        ComposableLambda rememberComposableLambda32222222 = ComposableLambdaKt.rememberComposableLambda(211026382, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2222222, Integer num) {
                invoke(composer2222222, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2222222, int i12) {
                ComposerKt.sourceInformation(composer2222222, "C264@11646L32,261@11468L229:NavigationRail.kt#uh7d8r");
                if ((i12 & 3) != 2 || !composer2222222.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(211026382, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:261)");
                    }
                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), Shape.this), mappedInteractionSource2222222, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer2222222, 0, 7)), composer2222222, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer2222222.skipToGroupEnd();
            }
        }, startRestartGroup, 54);
        ComposableLambda rememberComposableLambda42222222 = ComposableLambdaKt.rememberComposableLambda(-1862011490, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2222222, Integer num) {
                invoke(composer2222222, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2222222, int i12) {
                ComposerKt.sourceInformation(composer2222222, "C271@11882L35,269@11778L248:NavigationRail.kt#uh7d8r");
                if ((i12 & 3) != 2 || !composer2222222.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1862011490, i12, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:269)");
                    }
                    Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                    ComposerKt.sourceInformationMarkerStart(composer2222222, -425107715, "CC(remember):NavigationRail.kt#9igjgp");
                    boolean changed3 = composer2222222.changed(animateFloatAsState2222222);
                    final State<Float> state = animateFloatAsState2222222;
                    Object rememberedValue4 = composer2222222.rememberedValue();
                    if (changed3 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        rememberedValue4 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicator$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                invoke2(graphicsLayerScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                                graphicsLayerScope.setAlpha(state.getValue().floatValue());
                            }
                        };
                        composer2222222.updateRememberedValue(rememberedValue4);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2222222);
                    BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue4), navigationRailItemColors42222222.getSelectedIndicatorColor(), value), composer2222222, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer2222222.skipToGroupEnd();
            }
        }, startRestartGroup, 54);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -119627306, "CC(remember):NavigationRail.kt#9igjgp");
        changed2 = startRestartGroup.changed(animateFloatAsState2222222);
        rememberedValue2 = startRestartGroup.rememberedValue();
        if (!changed2) {
        }
        rememberedValue2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return animateFloatAsState2222222.getValue();
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue2);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        NavigationRailItemLayout(rememberComposableLambda32222222, rememberComposableLambda42222222, rememberComposableLambda5222222, rememberComposableLambda2, z112222222, (Function0) rememberedValue2, startRestartGroup, (57344 & (i10 >> 6)) | 438);
        startRestartGroup = startRestartGroup;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        startRestartGroup.endNode();
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        if (ComposerKt.isTraceInProgress()) {
        }
        z6 = z112222222;
        navigationRailItemColors3 = navigationRailItemColors42222222;
        mutableInteractionSource4 = mutableInteractionSource2;
        modifier3 = modifier42222222;
        z7 = z102222222;
        Composer composer2222222 = startRestartGroup;
        final Function2<? super Composer, ? super Integer, Unit> function25222222 = function23;
        endRestartGroup = composer2222222.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void NavigationRailItemLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, final boolean z, final Function0<Float> function0, Composer composer, final int i) {
        int i2;
        final Function2<? super Composer, ? super Integer, Unit> function25 = function24;
        Composer startRestartGroup = composer.startRestartGroup(1498399348);
        ComposerKt.sourceInformation(startRestartGroup, "C(NavigationRailItemLayout)P(4,3,2,5)530@23300L1924,515@22904L2320:NavigationRail.kt#uh7d8r");
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
            i2 |= startRestartGroup.changedInstance(function25) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= startRestartGroup.changed(z) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= startRestartGroup.changedInstance(function0) ? 131072 : 65536;
        }
        if ((74899 & i2) != 74898 || !startRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1498399348, i2, -1, "androidx.compose.material3.NavigationRailItemLayout (NavigationRail.kt:514)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1881815062, "CC(remember):NavigationRail.kt#9igjgp");
            int i3 = 458752 & i2;
            int i4 = 57344 & i2;
            boolean z2 = (i3 == 131072) | ((i2 & 7168) == 2048) | (i4 == 16384);
            MeasurePolicy rememberedValue = startRestartGroup.rememberedValue();
            if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItemLayout$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i5) {
                        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i5);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i5) {
                        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i5);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i5) {
                        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i5);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i5) {
                        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i5);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* renamed from: measure-3p2s80s */
                    public final MeasureResult mo80measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        float f;
                        Measurable measurable;
                        Placeable placeable;
                        MeasureResult m2495placeLabelAndIconzUg2_y0;
                        MeasureResult m2494placeIconX9ElhV4;
                        MeasureScope measureScope2 = measureScope;
                        float floatValue = function0.invoke().floatValue();
                        long m7332copyZbe2FdA$default = Constraints.m7332copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
                        int size = list.size();
                        int i5 = 0;
                        while (i5 < size) {
                            Measurable measurable2 = list.get(i5);
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "icon")) {
                                Placeable mo6055measureBRTryo0 = measurable2.mo6055measureBRTryo0(m7332copyZbe2FdA$default);
                                int width = mo6055measureBRTryo0.getWidth();
                                f = NavigationRailKt.IndicatorHorizontalPadding;
                                float f2 = 2;
                                int i6 = width + measureScope2.mo453roundToPx0680j_4(Dp.m7399constructorimpl(f * f2));
                                int roundToInt = MathKt.roundToInt(i6 * floatValue);
                                int height = mo6055measureBRTryo0.getHeight() + measureScope2.mo453roundToPx0680j_4(Dp.m7399constructorimpl((function25 == null ? NavigationRailKt.IndicatorVerticalPaddingNoLabel : NavigationRailKt.IndicatorVerticalPaddingWithLabel) * f2));
                                int size2 = list.size();
                                int i7 = 0;
                                while (i7 < size2) {
                                    Measurable measurable3 = list.get(i7);
                                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), "indicatorRipple")) {
                                        Placeable mo6055measureBRTryo02 = measurable3.mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(i6, height));
                                        int size3 = list.size();
                                        int i8 = 0;
                                        while (true) {
                                            if (i8 >= size3) {
                                                measurable = null;
                                                break;
                                            }
                                            measurable = list.get(i8);
                                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "indicator")) {
                                                break;
                                            }
                                            i8++;
                                        }
                                        Measurable measurable4 = measurable;
                                        Placeable mo6055measureBRTryo03 = measurable4 != null ? measurable4.mo6055measureBRTryo0(Constraints.INSTANCE.m7352fixedJhjzzOo(roundToInt, height)) : null;
                                        if (function25 != null) {
                                            int size4 = list.size();
                                            for (int i9 = 0; i9 < size4; i9++) {
                                                Measurable measurable5 = list.get(i9);
                                                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable5), "label")) {
                                                    placeable = measurable5.mo6055measureBRTryo0(m7332copyZbe2FdA$default);
                                                }
                                            }
                                            throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                        }
                                        placeable = null;
                                        if (function25 == null) {
                                            m2494placeIconX9ElhV4 = NavigationRailKt.m2494placeIconX9ElhV4(measureScope2, mo6055measureBRTryo0, mo6055measureBRTryo02, mo6055measureBRTryo03, j);
                                            return m2494placeIconX9ElhV4;
                                        }
                                        Intrinsics.checkNotNull(placeable);
                                        m2495placeLabelAndIconzUg2_y0 = NavigationRailKt.m2495placeLabelAndIconzUg2_y0(measureScope, placeable, mo6055measureBRTryo0, mo6055measureBRTryo02, mo6055measureBRTryo03, j, z, floatValue);
                                        return m2495placeLabelAndIconzUg2_y0;
                                    }
                                    i7++;
                                    measureScope2 = measureScope;
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                            i5++;
                            measureScope2 = measureScope;
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            Modifier.Companion companion = Modifier.INSTANCE;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, companion);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i5 = i2;
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2082816907, "C516@22921L17,517@22947L11,519@22968L50:NavigationRail.kt#uh7d8r");
            function2.invoke(startRestartGroup, Integer.valueOf(i5 & 14));
            function22.invoke(startRestartGroup, Integer.valueOf((i5 >> 3) & 14));
            Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, IconLayoutIdTag);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, layoutId);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
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
            if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 402566553, "C519@23010L6:NavigationRail.kt#uh7d8r");
            function23.invoke(startRestartGroup, Integer.valueOf((i5 >> 6) & 14));
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.startReplaceGroup(2145400941);
            ComposerKt.sourceInformation(startRestartGroup, "523@23132L96,522@23061L221");
            if (function24 != null) {
                Modifier layoutId2 = LayoutIdKt.layoutId(Modifier.INSTANCE, LabelLayoutIdTag);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2145404101, "CC(remember):NavigationRail.kt#9igjgp");
                boolean z3 = (i4 == 16384) | (i3 == 131072);
                Object rememberedValue2 = startRestartGroup.rememberedValue();
                if (z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue2 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItemLayout$1$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                            invoke2(graphicsLayerScope);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                            graphicsLayerScope.setAlpha(z ? 1.0f : function0.invoke().floatValue());
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Modifier graphicsLayer = GraphicsLayerModifierKt.graphicsLayer(layoutId2, (Function1) rememberedValue2);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(startRestartGroup, graphicsLayer);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                startRestartGroup.startReusableNode();
                if (startRestartGroup.getInserting()) {
                    startRestartGroup.createNode(constructor3);
                } else {
                    startRestartGroup.useNode();
                }
                Composer m3905constructorimpl3 = Updater.m3905constructorimpl(startRestartGroup);
                Updater.m3912setimpl(m3905constructorimpl3, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3912setimpl(m3905constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (m3905constructorimpl3.getInserting() || !Intrinsics.areEqual(m3905constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    m3905constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    m3905constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m3912setimpl(m3905constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 402815576, "C527@23261L7:NavigationRail.kt#uh7d8r");
                function25 = function24;
                function25.invoke(startRestartGroup, Integer.valueOf((i5 >> 9) & 14));
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            } else {
                function25 = function24;
            }
            startRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
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
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItemLayout$3
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

                public final void invoke(Composer composer2, int i6) {
                    NavigationRailKt.NavigationRailItemLayout(function2, function22, function23, function25, z, function0, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: placeIcon-X9ElhV4, reason: not valid java name */
    public static final MeasureResult m2494placeIconX9ElhV4(MeasureScope measureScope, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, long j) {
        final int m7359constrainWidthK40F9xA = ConstraintsKt.m7359constrainWidthK40F9xA(j, Math.max(placeable.getWidth(), Math.max(placeable2.getWidth(), placeable3 != null ? placeable3.getWidth() : 0)));
        final int m7358constrainHeightK40F9xA = ConstraintsKt.m7358constrainHeightK40F9xA(j, measureScope.mo453roundToPx0680j_4(NavigationRailItemHeight));
        final int width = (m7359constrainWidthK40F9xA - placeable.getWidth()) / 2;
        final int height = (m7358constrainHeightK40F9xA - placeable.getHeight()) / 2;
        final int width2 = (m7359constrainWidthK40F9xA - placeable2.getWidth()) / 2;
        final int height2 = (m7358constrainHeightK40F9xA - placeable2.getHeight()) / 2;
        return MeasureScope.CC.layout$default(measureScope, m7359constrainWidthK40F9xA, m7358constrainHeightK40F9xA, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$placeIcon$1
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
                Placeable placeable4 = Placeable.this;
                if (placeable4 != null) {
                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, (m7359constrainWidthK40F9xA - placeable4.getWidth()) / 2, (m7358constrainHeightK40F9xA - placeable4.getHeight()) / 2, 0.0f, 4, null);
                    placementScope2 = placementScope;
                } else {
                    placementScope2 = placementScope;
                }
                Placeable.PlacementScope.placeRelative$default(placementScope2, placeable, width, height, 0.0f, 4, null);
                Placeable.PlacementScope.placeRelative$default(placementScope2, placeable2, width2, height2, 0.0f, 4, null);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: placeLabelAndIcon-zUg2_y0, reason: not valid java name */
    public static final MeasureResult m2495placeLabelAndIconzUg2_y0(final MeasureScope measureScope, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, final Placeable placeable4, long j, final boolean z, final float f) {
        float height = placeable2.getHeight();
        float f2 = IndicatorVerticalPaddingWithLabel;
        float f3 = height + measureScope.mo459toPx0680j_4(f2);
        float f4 = NavigationRailItemVerticalPadding;
        float f5 = f3 + measureScope.mo459toPx0680j_4(f4) + placeable.getHeight();
        float f6 = 2;
        final float coerceAtLeast = RangesKt.coerceAtLeast((Constraints.m7343getMinHeightimpl(j) - f5) / f6, measureScope.mo459toPx0680j_4(f2));
        float f7 = f5 + (coerceAtLeast * f6);
        final float height2 = ((z ? coerceAtLeast : (f7 - placeable2.getHeight()) / f6) - coerceAtLeast) * (1 - f);
        final float height3 = placeable2.getHeight() + coerceAtLeast + measureScope.mo459toPx0680j_4(f2) + measureScope.mo459toPx0680j_4(f4);
        final int m7359constrainWidthK40F9xA = ConstraintsKt.m7359constrainWidthK40F9xA(j, Math.max(placeable2.getWidth(), Math.max(placeable.getWidth(), placeable4 != null ? placeable4.getWidth() : 0)));
        final int width = (m7359constrainWidthK40F9xA - placeable.getWidth()) / 2;
        final int width2 = (m7359constrainWidthK40F9xA - placeable2.getWidth()) / 2;
        final int width3 = (m7359constrainWidthK40F9xA - placeable3.getWidth()) / 2;
        final float f8 = coerceAtLeast - measureScope.mo459toPx0680j_4(f2);
        return MeasureScope.CC.layout$default(measureScope, m7359constrainWidthK40F9xA, MathKt.roundToInt(f7), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$placeLabelAndIcon$1
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
                float f9;
                Placeable placeable5 = Placeable.this;
                if (placeable5 != null) {
                    int i = m7359constrainWidthK40F9xA;
                    float f10 = coerceAtLeast;
                    MeasureScope measureScope2 = measureScope;
                    float f11 = height2;
                    int width4 = (i - placeable5.getWidth()) / 2;
                    f9 = NavigationRailKt.IndicatorVerticalPaddingWithLabel;
                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, width4, MathKt.roundToInt((f10 - measureScope2.mo459toPx0680j_4(f9)) + f11), 0.0f, 4, null);
                    placementScope2 = placementScope;
                } else {
                    placementScope2 = placementScope;
                }
                if (z || f != 0.0f) {
                    Placeable.PlacementScope.placeRelative$default(placementScope2, placeable, width, MathKt.roundToInt(height3 + height2), 0.0f, 4, null);
                }
                Placeable.PlacementScope.placeRelative$default(placementScope2, placeable2, width2, MathKt.roundToInt(coerceAtLeast + height2), 0.0f, 4, null);
                Placeable.PlacementScope.placeRelative$default(placementScope2, placeable3, width3, MathKt.roundToInt(f8 + height2), 0.0f, 4, null);
            }
        }, 4, null);
    }

    public static final float getNavigationRailVerticalPadding() {
        return NavigationRailVerticalPadding;
    }

    public static final float getNavigationRailItemWidth() {
        return NavigationRailItemWidth;
    }

    public static final float getNavigationRailItemHeight() {
        return NavigationRailItemHeight;
    }

    public static final float getNavigationRailItemVerticalPadding() {
        return NavigationRailItemVerticalPadding;
    }

    static {
        float f = 4;
        NavigationRailVerticalPadding = Dp.m7399constructorimpl(f);
        NavigationRailItemVerticalPadding = Dp.m7399constructorimpl(f);
        float f2 = 2;
        IndicatorHorizontalPadding = Dp.m7399constructorimpl(Dp.m7399constructorimpl(NavigationRailTokens.INSTANCE.m3515getActiveIndicatorWidthD9Ej5fM() - NavigationRailTokens.INSTANCE.m3518getIconSizeD9Ej5fM()) / f2);
        IndicatorVerticalPaddingWithLabel = Dp.m7399constructorimpl(Dp.m7399constructorimpl(NavigationRailTokens.INSTANCE.m3514getActiveIndicatorHeightD9Ej5fM() - NavigationRailTokens.INSTANCE.m3518getIconSizeD9Ej5fM()) / f2);
        IndicatorVerticalPaddingNoLabel = Dp.m7399constructorimpl(Dp.m7399constructorimpl(NavigationRailTokens.INSTANCE.m3520getNoLabelActiveIndicatorHeightD9Ej5fM() - NavigationRailTokens.INSTANCE.m3518getIconSizeD9Ej5fM()) / f2);
    }
}
