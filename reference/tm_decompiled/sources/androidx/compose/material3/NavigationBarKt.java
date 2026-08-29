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
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.internal.MappedInteractionSource;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.NavigationBarTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
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
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
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
import androidx.compose.ui.unit.IntSize;
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

/* compiled from: NavigationBar.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u001ab\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00140\u001e¢\u0006\u0002\b ¢\u0006\u0002\b!H\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001aq\u0010$\u001a\u00020\u00142\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u00140&¢\u0006\u0002\b 2\u0011\u0010'\u001a\r\u0012\u0004\u0012\u00020\u00140&¢\u0006\u0002\b 2\u0011\u0010(\u001a\r\u0012\u0004\u0012\u00020\u00140&¢\u0006\u0002\b 2\u0013\u0010)\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010&¢\u0006\u0002\b 2\u0006\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0&H\u0003¢\u0006\u0002\u0010.\u001a\u0085\u0001\u0010/\u001a\u00020\u0014*\u00020\u001f2\u0006\u00100\u001a\u00020+2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00140&2\u0011\u0010(\u001a\r\u0012\u0004\u0012\u00020\u00140&¢\u0006\u0002\b 2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u00102\u001a\u00020+2\u0015\b\u0002\u0010)\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010&¢\u0006\u0002\b 2\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u000106H\u0007¢\u0006\u0002\u00107\u001a8\u00108\u001a\u000209*\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\b\u0010>\u001a\u0004\u0018\u00010<2\u0006\u0010?\u001a\u00020@H\u0002ø\u0001\u0000¢\u0006\u0004\bA\u0010B\u001aP\u0010C\u001a\u000209*\u00020:2\u0006\u0010D\u001a\u00020<2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\b\u0010>\u001a\u0004\u0018\u00010<2\u0006\u0010?\u001a\u00020@2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002ø\u0001\u0000¢\u0006\u0004\bE\u0010F\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\b\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\t\u0010\n\"\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\u000f\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0010\u0010\n\"\u0016\u0010\u0011\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0012\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006G²\u0006\n\u0010H\u001a\u00020\u0018X\u008a\u0084\u0002²\u0006\n\u0010I\u001a\u00020\u0018X\u008a\u0084\u0002²\u0006\n\u0010J\u001a\u00020\fX\u008a\u008e\u0002"}, d2 = {"IconLayoutIdTag", "", "IndicatorHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "IndicatorLayoutIdTag", "IndicatorRippleLayoutIdTag", "IndicatorVerticalOffset", "IndicatorVerticalPadding", "getIndicatorVerticalPadding", "()F", "ItemAnimationDurationMillis", "", "LabelLayoutIdTag", "NavigationBarHeight", "NavigationBarIndicatorToLabelPadding", "getNavigationBarIndicatorToLabelPadding", "NavigationBarItemHorizontalPadding", "getNavigationBarItemHorizontalPadding", "NavigationBar", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "NavigationBar-HsRjFd4", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "NavigationBarItemLayout", NavigationBarKt.IndicatorRippleLayoutIdTag, "Lkotlin/Function0;", NavigationBarKt.IndicatorLayoutIdTag, NavigationBarKt.IconLayoutIdTag, NavigationBarKt.LabelLayoutIdTag, "alwaysShowLabel", "", "animationProgress", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "NavigationBarItem", "selected", "onClick", "enabled", "colors", "Landroidx/compose/material3/NavigationBarItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/foundation/layout/RowScope;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/NavigationBarItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "indicatorRipplePlaceable", "indicatorPlaceable", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-X9ElhV4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "labelPlaceable", "placeLabelAndIcon-zUg2_y0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JZF)Landroidx/compose/ui/layout/MeasureResult;", "material3_release", "iconColor", "textColor", "itemWidth"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NavigationBarKt {
    private static final String IconLayoutIdTag = "icon";
    private static final float IndicatorHorizontalPadding;
    private static final String IndicatorLayoutIdTag = "indicator";
    private static final String IndicatorRippleLayoutIdTag = "indicatorRipple";
    private static final float IndicatorVerticalPadding;
    private static final int ItemAnimationDurationMillis = 100;
    private static final String LabelLayoutIdTag = "label";
    private static final float NavigationBarHeight = NavigationBarTokens.INSTANCE.m3506getContainerHeightD9Ej5fM();
    private static final float NavigationBarItemHorizontalPadding = Dp.m7399constructorimpl(8);
    private static final float NavigationBarIndicatorToLabelPadding = Dp.m7399constructorimpl(4);
    private static final float IndicatorVerticalOffset = Dp.m7399constructorimpl(12);

    /* JADX WARN: Removed duplicated region for block: B:26:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0099  */
    /* renamed from: NavigationBar-HsRjFd4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2429NavigationBarHsRjFd4(Modifier modifier, long j, long j2, float f, WindowInsets windowInsets, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long j3;
        long j4;
        float f2;
        WindowInsets windowInsets2;
        long j5;
        float f3;
        final WindowInsets windowInsets3;
        Modifier modifier3;
        Composer composer2;
        final WindowInsets windowInsets4;
        final Modifier modifier4;
        final long j6;
        final long j7;
        final float f4;
        ScopeUpdateScope endRestartGroup;
        int i4;
        int i5;
        Composer startRestartGroup = composer.startRestartGroup(1596802123);
        ComposerKt.sourceInformation(startRestartGroup, "C(NavigationBar)P(3,0:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp,5)110@5148L14,111@5204L11,113@5362L12,121@5582L441,116@5429L594:NavigationBar.kt#uh7d8r");
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
            f2 = f;
            i3 |= startRestartGroup.changed(f2) ? 2048 : 1024;
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
                i3 |= startRestartGroup.changedInstance(function3) ? 131072 : 65536;
            }
            if ((74899 & i3) == 74898 || !startRestartGroup.getSkipping()) {
                startRestartGroup.startDefaults();
                if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                    Modifier.Companion companion = i6 == 0 ? Modifier.INSTANCE : modifier2;
                    if ((i2 & 2) != 0) {
                        j3 = NavigationBarDefaults.INSTANCE.getContainerColor(startRestartGroup, 6);
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        j4 = ColorSchemeKt.m2084contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme(startRestartGroup, 6), j3);
                        i3 &= -897;
                    }
                    if (i7 != 0) {
                        f2 = NavigationBarDefaults.INSTANCE.m2414getElevationD9Ej5fM();
                    }
                    if ((i2 & 16) == 0) {
                        windowInsets3 = NavigationBarDefaults.INSTANCE.getWindowInsets(startRestartGroup, 6);
                        i3 &= -57345;
                        j5 = j4;
                        f3 = f2;
                    } else {
                        j5 = j4;
                        f3 = f2;
                        windowInsets3 = windowInsets2;
                    }
                    modifier3 = companion;
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
                    f3 = f2;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets2;
                }
                long j8 = j3;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1596802123, i3, -1, "androidx.compose.material3.NavigationBar (NavigationBar.kt:115)");
                }
                int i8 = i3 << 3;
                composer2 = startRestartGroup;
                SurfaceKt.m2699SurfaceT9BRK9s(modifier3, null, j8, j5, f3, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(105663120, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBar$1
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

                    public final void invoke(Composer composer3, int i9) {
                        float f5;
                        ComposerKt.sourceInformation(composer3, "C122@5592L425:NavigationBar.kt#uh7d8r");
                        if ((i9 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(105663120, i9, -1, "androidx.compose.material3.NavigationBar.<anonymous> (NavigationBar.kt:122)");
                            }
                            Modifier windowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), WindowInsets.this);
                            f5 = NavigationBarKt.NavigationBarHeight;
                            Modifier selectableGroup = SelectableGroupKt.selectableGroup(SizeKt.m915defaultMinSizeVpY3zN4$default(windowInsetsPadding, 0.0f, f5, 1, null));
                            Arrangement.HorizontalOrVertical m733spacedBy0680j_4 = Arrangement.INSTANCE.m733spacedBy0680j_4(NavigationBarKt.getNavigationBarItemHorizontalPadding());
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function32 = function3;
                            ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m733spacedBy0680j_4, centerVertically, composer3, 54);
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
                            Updater.m3912setimpl(m3905constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                            function32.invoke(RowScopeInstance.INSTANCE, composer3, 6);
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
                }, startRestartGroup, 54), composer2, (i3 & 14) | 12582912 | (i8 & 896) | (i8 & 7168) | (i8 & 57344), 98);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                windowInsets4 = windowInsets3;
                modifier4 = modifier3;
                j6 = j8;
                j7 = j5;
                f4 = f3;
            } else {
                startRestartGroup.skipToGroupEnd();
                composer2 = startRestartGroup;
                modifier4 = modifier2;
                j6 = j3;
                j7 = j4;
                f4 = f2;
                windowInsets4 = windowInsets2;
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBar$2
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

                    public final void invoke(Composer composer3, int i9) {
                        NavigationBarKt.m2429NavigationBarHsRjFd4(Modifier.this, j6, j7, f4, windowInsets4, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
                return;
            }
            return;
        }
        f2 = f;
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
        long j82 = j3;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        int i82 = i3 << 3;
        composer2 = startRestartGroup;
        SurfaceKt.m2699SurfaceT9BRK9s(modifier3, null, j82, j5, f3, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(105663120, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBar$1
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

            public final void invoke(Composer composer3, int i9) {
                float f5;
                ComposerKt.sourceInformation(composer3, "C122@5592L425:NavigationBar.kt#uh7d8r");
                if ((i9 & 3) != 2 || !composer3.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(105663120, i9, -1, "androidx.compose.material3.NavigationBar.<anonymous> (NavigationBar.kt:122)");
                    }
                    Modifier windowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), WindowInsets.this);
                    f5 = NavigationBarKt.NavigationBarHeight;
                    Modifier selectableGroup = SelectableGroupKt.selectableGroup(SizeKt.m915defaultMinSizeVpY3zN4$default(windowInsetsPadding, 0.0f, f5, 1, null));
                    Arrangement.HorizontalOrVertical m733spacedBy0680j_4 = Arrangement.INSTANCE.m733spacedBy0680j_4(NavigationBarKt.getNavigationBarItemHorizontalPadding());
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    Function3<RowScope, Composer, Integer, Unit> function32 = function3;
                    ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m733spacedBy0680j_4, centerVertically, composer3, 54);
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
                    Updater.m3912setimpl(m3905constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                    function32.invoke(RowScopeInstance.INSTANCE, composer3, 6);
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
        }, startRestartGroup, 54), composer2, (i3 & 14) | 12582912 | (i82 & 896) | (i82 & 7168) | (i82 & 57344), 98);
        if (ComposerKt.isTraceInProgress()) {
        }
        windowInsets4 = windowInsets3;
        modifier4 = modifier3;
        j6 = j82;
        j7 = j5;
        f4 = f3;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x046c  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0426  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void NavigationBarItem(final RowScope rowScope, final boolean z, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean z2, Function2<? super Composer, ? super Integer, Unit> function22, boolean z3, NavigationBarItemColors navigationBarItemColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        int i3;
        Function2<? super Composer, ? super Integer, Unit> function23;
        int i4;
        Modifier modifier2;
        int i5;
        final boolean z4;
        int i6;
        final Function2<? super Composer, ? super Integer, Unit> function24;
        int i7;
        boolean z5;
        int i8;
        int i9;
        int i10;
        NavigationBarItemColors navigationBarItemColors2;
        int i11;
        MutableInteractionSource mutableInteractionSource2;
        MutableInteractionSource mutableInteractionSource3;
        ComposableLambda composableLambda;
        ComposableLambda rememberComposableLambda;
        Object rememberedValue;
        Object rememberedValue2;
        int currentCompositeKeyHash;
        Composer m3905constructorimpl;
        boolean changed;
        Object rememberedValue3;
        boolean changed2;
        Object rememberedValue4;
        final MutableInteractionSource mutableInteractionSource4;
        final NavigationBarItemColors navigationBarItemColors3;
        final boolean z6;
        Composer composer2;
        final boolean z7;
        final Modifier modifier3;
        ScopeUpdateScope endRestartGroup;
        int i12;
        Composer startRestartGroup = composer.startRestartGroup(-663510974);
        ComposerKt.sourceInformation(startRestartGroup, "C(NavigationBarItem)P(8,7,3,6,2,5)178@8294L8,184@8526L633,214@9823L33,228@10265L24,216@9862L2687:NavigationBar.kt#uh7d8r");
        if ((Integer.MIN_VALUE & i2) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(rowScope) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 1) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= startRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i2 & 2) != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= startRestartGroup.changedInstance(function0) ? 256 : 128;
            if ((i2 & 4) == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                function23 = function2;
                i3 |= startRestartGroup.changedInstance(function23) ? 2048 : 1024;
                i4 = i2 & 8;
                if (i4 != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    modifier2 = modifier;
                    i3 |= startRestartGroup.changed(modifier2) ? 16384 : 8192;
                    i5 = i2 & 16;
                    if (i5 == 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((196608 & i) == 0) {
                        z4 = z2;
                        i3 |= startRestartGroup.changed(z4) ? 131072 : 65536;
                        i6 = i2 & 32;
                        if (i6 != 0) {
                            i3 |= 1572864;
                        } else if ((1572864 & i) == 0) {
                            function24 = function22;
                            i3 |= startRestartGroup.changedInstance(function24) ? 1048576 : 524288;
                            i7 = i2 & 64;
                            if (i7 == 0) {
                                i3 |= 12582912;
                                z5 = z3;
                            } else {
                                z5 = z3;
                                if ((i & 12582912) == 0) {
                                    i3 |= startRestartGroup.changed(z5) ? 8388608 : 4194304;
                                }
                            }
                            if ((i & 100663296) == 0) {
                                if ((i2 & 128) == 0 && startRestartGroup.changed(navigationBarItemColors)) {
                                    i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                                    i3 |= i12;
                                }
                                i12 = 33554432;
                                i3 |= i12;
                            }
                            i8 = i2 & 256;
                            if (i8 == 0) {
                                i3 |= 805306368;
                            } else if ((i & 805306368) == 0) {
                                i9 = i8;
                                i3 |= startRestartGroup.changed(mutableInteractionSource) ? 536870912 : 268435456;
                                if ((i3 & 306783379) == 306783378 || !startRestartGroup.getSkipping()) {
                                    startRestartGroup.startDefaults();
                                    i10 = i3;
                                    if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                        if (i4 != 0) {
                                            modifier2 = Modifier.INSTANCE;
                                        }
                                        if (i5 != 0) {
                                            z4 = true;
                                        }
                                        if (i6 != 0) {
                                            function24 = null;
                                        }
                                        if (i7 != 0) {
                                            z5 = true;
                                        }
                                        if ((i2 & 128) != 0) {
                                            navigationBarItemColors2 = NavigationBarItemDefaults.INSTANCE.colors(startRestartGroup, 6);
                                            i10 &= -234881025;
                                        } else {
                                            navigationBarItemColors2 = navigationBarItemColors;
                                        }
                                        if (i9 != 0) {
                                            i11 = i10;
                                            mutableInteractionSource2 = null;
                                            startRestartGroup.endDefaults();
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-663510974, i11, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:180)");
                                            }
                                            startRestartGroup.startReplaceGroup(-103236853);
                                            ComposerKt.sourceInformation(startRestartGroup, "182@8445L39");
                                            if (mutableInteractionSource2 != null) {
                                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103236202, "CC(remember):NavigationBar.kt#9igjgp");
                                                Object rememberedValue5 = startRestartGroup.rememberedValue();
                                                if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                                    rememberedValue5 = InteractionSourceKt.MutableInteractionSource();
                                                    startRestartGroup.updateRememberedValue(rememberedValue5);
                                                }
                                                mutableInteractionSource3 = (MutableInteractionSource) rememberedValue5;
                                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            } else {
                                                mutableInteractionSource3 = mutableInteractionSource2;
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            final NavigationBarItemColors navigationBarItemColors4 = navigationBarItemColors2;
                                            final Function2<? super Composer, ? super Integer, Unit> function25 = function23;
                                            final boolean z8 = z5;
                                            final boolean z9 = z4;
                                            final Function2<? super Composer, ? super Integer, Unit> function26 = function24;
                                            MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource3;
                                            ComposableLambda rememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-1419576100, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1
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

                                                public final void invoke(Composer composer3, int i13) {
                                                    ComposerKt.sourceInformation(composer3, "C186@8573L201,192@8956L193:NavigationBar.kt#uh7d8r");
                                                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-1419576100, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous> (NavigationBar.kt:185)");
                                                        }
                                                        State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationBarItemColors.this.m2425iconColorWaAFU9c$material3_release(z, z9), AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer3, 48, 12);
                                                        Modifier.Companion clearAndSetSemantics = (function26 == null || !(z8 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1.1
                                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                            }

                                                            @Override // kotlin.jvm.functions.Function1
                                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                                invoke2(semanticsPropertyReceiver);
                                                                return Unit.INSTANCE;
                                                            }
                                                        });
                                                        Function2<Composer, Integer, Unit> function27 = function25;
                                                        ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, clearAndSetSemantics);
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
                                                        Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer3);
                                                        Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                        if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                                            m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                                            m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash);
                                                        }
                                                        Updater.m3912setimpl(m3905constructorimpl2, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -1543730008, "C193@9057L78:NavigationBar.kt#uh7d8r");
                                                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function27, composer3, ProvidedValue.$stable);
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

                                                private static final long invoke$lambda$0(State<Color> state) {
                                                    return state.getValue().m4625unboximpl();
                                                }
                                            }, startRestartGroup, 54);
                                            startRestartGroup.startReplaceGroup(-103210706);
                                            ComposerKt.sourceInformation(startRestartGroup, "*199@9255L535");
                                            if (function24 != null) {
                                                composableLambda = rememberComposableLambda2;
                                                rememberComposableLambda = null;
                                            } else {
                                                composableLambda = rememberComposableLambda2;
                                                rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1644987592, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
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

                                                    public final void invoke(Composer composer3, int i13) {
                                                        ComposerKt.sourceInformation(composer3, "C200@9319L5,202@9378L213,206@9608L168:NavigationBar.kt#uh7d8r");
                                                        if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(1644987592, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:200)");
                                                            }
                                                            ProvideContentColorTextStyleKt.m3088ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationBarItemColors.this.m2426textColorWaAFU9c$material3_release(z, z4), AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer3, 48, 12)), TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), composer3, 6), function24, composer3, 0);
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventEnd();
                                                                return;
                                                            }
                                                            return;
                                                        }
                                                        composer3.skipToGroupEnd();
                                                    }

                                                    private static final long invoke$lambda$0(State<Color> state) {
                                                        return state.getValue().m4625unboximpl();
                                                    }
                                                }, startRestartGroup, 54);
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103192112, "CC(remember):NavigationBar.kt#9igjgp");
                                            rememberedValue = startRestartGroup.rememberedValue();
                                            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                                                startRestartGroup.updateRememberedValue(rememberedValue);
                                            }
                                            final MutableIntState mutableIntState = (MutableIntState) rememberedValue;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            Modifier modifier4 = modifier2;
                                            boolean z10 = z4;
                                            ComposableLambda composableLambda2 = rememberComposableLambda;
                                            Modifier weight$default = RowScope.CC.weight$default(rowScope, SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier4, z, mutableInteractionSource5, null, z10, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationBarHeight, 1, null), 1.0f, false, 2, null);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103177977, "CC(remember):NavigationBar.kt#9igjgp");
                                            rememberedValue2 = startRestartGroup.rememberedValue();
                                            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue2 = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$1$1
                                                    {
                                                        super(1);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function1
                                                    public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                                                        m2434invokeozmzZPI(intSize.m7578unboximpl());
                                                        return Unit.INSTANCE;
                                                    }

                                                    /* renamed from: invoke-ozmzZPI, reason: not valid java name */
                                                    public final void m2434invokeozmzZPI(long j) {
                                                        MutableIntState.this.setIntValue(IntSize.m7574getWidthimpl(j));
                                                    }
                                                };
                                                startRestartGroup.updateRememberedValue(rememberedValue2);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            Modifier onSizeChanged = OnRemeasuredModifierKt.onSizeChanged(weight$default, (Function1) rememberedValue2);
                                            Alignment center = Alignment.INSTANCE.getCenter();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, true);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                                            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, onSizeChanged);
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
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1652172306, "C233@10442L157,*242@10883L7,248@11165L128,255@11508L293,263@11850L395,280@12505L27,274@12255L288:NavigationBar.kt#uh7d8r");
                                            boolean z11 = z5;
                                            MutableInteractionSource mutableInteractionSource6 = mutableInteractionSource2;
                                            final State<Float> animateFloatAsState = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                            Object consume = startRestartGroup.consume(localDensity);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            long Offset = OffsetKt.Offset((NavigationBarItem$lambda$3(mutableIntState) - r2.mo453roundToPx0680j_4(NavigationBarTokens.INSTANCE.m3504getActiveIndicatorWidthD9Ej5fM())) / 2, ((Density) consume).mo459toPx0680j_4(IndicatorVerticalOffset));
                                            Unit unit = Unit.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024936814, "CC(remember):NavigationBar.kt#9igjgp");
                                            changed = startRestartGroup.changed(mutableInteractionSource5) | startRestartGroup.changed(Offset);
                                            rememberedValue3 = startRestartGroup.rememberedValue();
                                            if (!changed || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue3 = new MappedInteractionSource(mutableInteractionSource5, Offset, null);
                                                startRestartGroup.updateRememberedValue(rememberedValue3);
                                            }
                                            final MappedInteractionSource mappedInteractionSource = (MappedInteractionSource) rememberedValue3;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposableLambda rememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(691730997, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                    invoke(composer3, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer3, int i13) {
                                                    ComposerKt.sourceInformation(composer3, "C258@11668L5,259@11736L32,256@11526L261:NavigationBar.kt#uh7d8r");
                                                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(691730997, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:256)");
                                                        }
                                                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), MappedInteractionSource.this, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer3, 0, 7)), composer3, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer3.skipToGroupEnd();
                                                }
                                            }, startRestartGroup, 54);
                                            ComposableLambda rememberComposableLambda4 = ComposableLambdaKt.rememberComposableLambda(-474426875, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                    invoke(composer3, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer3, int i13) {
                                                    ComposerKt.sourceInformation(composer3, "C266@11972L35,269@12181L5,264@11868L363:NavigationBar.kt#uh7d8r");
                                                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-474426875, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:264)");
                                                        }
                                                        Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                                                        ComposerKt.sourceInformationMarkerStart(composer3, 1335768816, "CC(remember):NavigationBar.kt#9igjgp");
                                                        boolean changed3 = composer3.changed(animateFloatAsState);
                                                        final State<Float> state = animateFloatAsState;
                                                        Object rememberedValue6 = composer3.rememberedValue();
                                                        if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                            rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1$1$1
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
                                                            composer3.updateRememberedValue(rememberedValue6);
                                                        }
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                        BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue6), navigationBarItemColors4.getSelectedIndicatorColor(), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), composer3, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer3.skipToGroupEnd();
                                                }
                                            }, startRestartGroup, 54);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024979593, "CC(remember):NavigationBar.kt#9igjgp");
                                            changed2 = startRestartGroup.changed(animateFloatAsState);
                                            rememberedValue4 = startRestartGroup.rememberedValue();
                                            if (!changed2 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue4 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$2$1
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
                                                startRestartGroup.updateRememberedValue(rememberedValue4);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            NavigationBarItemLayout(rememberComposableLambda3, rememberComposableLambda4, composableLambda, composableLambda2, z11, (Function0) rememberedValue4, startRestartGroup, ((i11 >> 9) & 57344) | 438);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            startRestartGroup.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                            mutableInteractionSource4 = mutableInteractionSource6;
                                            navigationBarItemColors3 = navigationBarItemColors4;
                                            z6 = z11;
                                            composer2 = startRestartGroup;
                                            z7 = z10;
                                            modifier3 = modifier4;
                                        }
                                        mutableInteractionSource2 = mutableInteractionSource;
                                        i11 = i10;
                                        startRestartGroup.endDefaults();
                                        if (ComposerKt.isTraceInProgress()) {
                                        }
                                        startRestartGroup.startReplaceGroup(-103236853);
                                        ComposerKt.sourceInformation(startRestartGroup, "182@8445L39");
                                        if (mutableInteractionSource2 != null) {
                                        }
                                        startRestartGroup.endReplaceGroup();
                                        final NavigationBarItemColors navigationBarItemColors42 = navigationBarItemColors2;
                                        final Function2<? super Composer, ? super Integer, Unit> function252 = function23;
                                        final boolean z82 = z5;
                                        final boolean z92 = z4;
                                        final Function2<? super Composer, ? super Integer, Unit> function262 = function24;
                                        MutableInteractionSource mutableInteractionSource52 = mutableInteractionSource3;
                                        ComposableLambda rememberComposableLambda22 = ComposableLambdaKt.rememberComposableLambda(-1419576100, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1
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

                                            public final void invoke(Composer composer3, int i13) {
                                                ComposerKt.sourceInformation(composer3, "C186@8573L201,192@8956L193:NavigationBar.kt#uh7d8r");
                                                if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-1419576100, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous> (NavigationBar.kt:185)");
                                                    }
                                                    State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationBarItemColors.this.m2425iconColorWaAFU9c$material3_release(z, z92), AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer3, 48, 12);
                                                    Modifier.Companion clearAndSetSemantics = (function262 == null || !(z82 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1.1
                                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                        }

                                                        @Override // kotlin.jvm.functions.Function1
                                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                            invoke2(semanticsPropertyReceiver);
                                                            return Unit.INSTANCE;
                                                        }
                                                    });
                                                    Function2<Composer, Integer, Unit> function27 = function252;
                                                    ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                    MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                                    Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer3, clearAndSetSemantics);
                                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                    if (!(composer3.getApplier() instanceof Applier)) {
                                                        ComposablesKt.invalidApplier();
                                                    }
                                                    composer3.startReusableNode();
                                                    if (composer3.getInserting()) {
                                                        composer3.createNode(constructor2);
                                                    } else {
                                                        composer3.useNode();
                                                    }
                                                    Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer3);
                                                    Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                    if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                                        m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                                        m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                                    }
                                                    Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                                    ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                                    ComposerKt.sourceInformationMarkerStart(composer3, -1543730008, "C193@9057L78:NavigationBar.kt#uh7d8r");
                                                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function27, composer3, ProvidedValue.$stable);
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

                                            private static final long invoke$lambda$0(State<Color> state) {
                                                return state.getValue().m4625unboximpl();
                                            }
                                        }, startRestartGroup, 54);
                                        startRestartGroup.startReplaceGroup(-103210706);
                                        ComposerKt.sourceInformation(startRestartGroup, "*199@9255L535");
                                        if (function24 != null) {
                                        }
                                        startRestartGroup.endReplaceGroup();
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103192112, "CC(remember):NavigationBar.kt#9igjgp");
                                        rememberedValue = startRestartGroup.rememberedValue();
                                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                        }
                                        final MutableIntState mutableIntState2 = (MutableIntState) rememberedValue;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        Modifier modifier42 = modifier2;
                                        boolean z102 = z4;
                                        ComposableLambda composableLambda22 = rememberComposableLambda;
                                        Modifier weight$default2 = RowScope.CC.weight$default(rowScope, SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier42, z, mutableInteractionSource52, null, z102, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationBarHeight, 1, null), 1.0f, false, 2, null);
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103177977, "CC(remember):NavigationBar.kt#9igjgp");
                                        rememberedValue2 = startRestartGroup.rememberedValue();
                                        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        Modifier onSizeChanged2 = OnRemeasuredModifierKt.onSizeChanged(weight$default2, (Function1) rememberedValue2);
                                        Alignment center2 = Alignment.INSTANCE.getCenter();
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, true);
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                                        CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, onSizeChanged2);
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
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1652172306, "C233@10442L157,*242@10883L7,248@11165L128,255@11508L293,263@11850L395,280@12505L27,274@12255L288:NavigationBar.kt#uh7d8r");
                                        boolean z112 = z5;
                                        MutableInteractionSource mutableInteractionSource62 = mutableInteractionSource2;
                                        final State<Float> animateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                        Object consume2 = startRestartGroup.consume(localDensity2);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        long Offset2 = OffsetKt.Offset((NavigationBarItem$lambda$3(mutableIntState2) - r2.mo453roundToPx0680j_4(NavigationBarTokens.INSTANCE.m3504getActiveIndicatorWidthD9Ej5fM())) / 2, ((Density) consume2).mo459toPx0680j_4(IndicatorVerticalOffset));
                                        Unit unit2 = Unit.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024936814, "CC(remember):NavigationBar.kt#9igjgp");
                                        changed = startRestartGroup.changed(mutableInteractionSource52) | startRestartGroup.changed(Offset2);
                                        rememberedValue3 = startRestartGroup.rememberedValue();
                                        if (!changed) {
                                        }
                                        rememberedValue3 = new MappedInteractionSource(mutableInteractionSource52, Offset2, null);
                                        startRestartGroup.updateRememberedValue(rememberedValue3);
                                        final MappedInteractionSource mappedInteractionSource2 = (MappedInteractionSource) rememberedValue3;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposableLambda rememberComposableLambda32 = ComposableLambdaKt.rememberComposableLambda(691730997, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                invoke(composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer3, int i13) {
                                                ComposerKt.sourceInformation(composer3, "C258@11668L5,259@11736L32,256@11526L261:NavigationBar.kt#uh7d8r");
                                                if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(691730997, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:256)");
                                                    }
                                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), MappedInteractionSource.this, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer3, 0, 7)), composer3, 0);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer3.skipToGroupEnd();
                                            }
                                        }, startRestartGroup, 54);
                                        ComposableLambda rememberComposableLambda42 = ComposableLambdaKt.rememberComposableLambda(-474426875, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                invoke(composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer3, int i13) {
                                                ComposerKt.sourceInformation(composer3, "C266@11972L35,269@12181L5,264@11868L363:NavigationBar.kt#uh7d8r");
                                                if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-474426875, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:264)");
                                                    }
                                                    Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                                                    ComposerKt.sourceInformationMarkerStart(composer3, 1335768816, "CC(remember):NavigationBar.kt#9igjgp");
                                                    boolean changed3 = composer3.changed(animateFloatAsState2);
                                                    final State<Float> state = animateFloatAsState2;
                                                    Object rememberedValue6 = composer3.rememberedValue();
                                                    if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                        rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1$1$1
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
                                                        composer3.updateRememberedValue(rememberedValue6);
                                                    }
                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                    BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue6), navigationBarItemColors42.getSelectedIndicatorColor(), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), composer3, 0);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                composer3.skipToGroupEnd();
                                            }
                                        }, startRestartGroup, 54);
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024979593, "CC(remember):NavigationBar.kt#9igjgp");
                                        changed2 = startRestartGroup.changed(animateFloatAsState2);
                                        rememberedValue4 = startRestartGroup.rememberedValue();
                                        if (!changed2) {
                                        }
                                        rememberedValue4 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$2$1
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
                                        startRestartGroup.updateRememberedValue(rememberedValue4);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        NavigationBarItemLayout(rememberComposableLambda32, rememberComposableLambda42, composableLambda, composableLambda22, z112, (Function0) rememberedValue4, startRestartGroup, ((i11 >> 9) & 57344) | 438);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        startRestartGroup.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        if (ComposerKt.isTraceInProgress()) {
                                        }
                                        mutableInteractionSource4 = mutableInteractionSource62;
                                        navigationBarItemColors3 = navigationBarItemColors42;
                                        z6 = z112;
                                        composer2 = startRestartGroup;
                                        z7 = z102;
                                        modifier3 = modifier42;
                                    } else {
                                        startRestartGroup.skipToGroupEnd();
                                        if ((i2 & 128) != 0) {
                                            mutableInteractionSource2 = mutableInteractionSource;
                                            i11 = i10 & (-234881025);
                                            navigationBarItemColors2 = navigationBarItemColors;
                                            startRestartGroup.endDefaults();
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.startReplaceGroup(-103236853);
                                            ComposerKt.sourceInformation(startRestartGroup, "182@8445L39");
                                            if (mutableInteractionSource2 != null) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            final NavigationBarItemColors navigationBarItemColors422 = navigationBarItemColors2;
                                            final Function2<? super Composer, ? super Integer, Unit> function2522 = function23;
                                            final boolean z822 = z5;
                                            final boolean z922 = z4;
                                            final Function2<? super Composer, ? super Integer, Unit> function2622 = function24;
                                            MutableInteractionSource mutableInteractionSource522 = mutableInteractionSource3;
                                            ComposableLambda rememberComposableLambda222 = ComposableLambdaKt.rememberComposableLambda(-1419576100, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1
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

                                                public final void invoke(Composer composer3, int i13) {
                                                    ComposerKt.sourceInformation(composer3, "C186@8573L201,192@8956L193:NavigationBar.kt#uh7d8r");
                                                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-1419576100, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous> (NavigationBar.kt:185)");
                                                        }
                                                        State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationBarItemColors.this.m2425iconColorWaAFU9c$material3_release(z, z922), AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer3, 48, 12);
                                                        Modifier.Companion clearAndSetSemantics = (function2622 == null || !(z822 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1.1
                                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                            }

                                                            @Override // kotlin.jvm.functions.Function1
                                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                                invoke2(semanticsPropertyReceiver);
                                                                return Unit.INSTANCE;
                                                            }
                                                        });
                                                        Function2<Composer, Integer, Unit> function27 = function2522;
                                                        ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                        MeasurePolicy maybeCachedBoxMeasurePolicy22 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                        CompositionLocalMap currentCompositionLocalMap22 = composer3.getCurrentCompositionLocalMap();
                                                        Modifier materializeModifier22 = ComposedModifierKt.materializeModifier(composer3, clearAndSetSemantics);
                                                        Function0<ComposeUiNode> constructor22 = ComposeUiNode.INSTANCE.getConstructor();
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                        if (!(composer3.getApplier() instanceof Applier)) {
                                                            ComposablesKt.invalidApplier();
                                                        }
                                                        composer3.startReusableNode();
                                                        if (composer3.getInserting()) {
                                                            composer3.createNode(constructor22);
                                                        } else {
                                                            composer3.useNode();
                                                        }
                                                        Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer3);
                                                        Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash22 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                        if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                                            m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                                            m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash22);
                                                        }
                                                        Updater.m3912setimpl(m3905constructorimpl2, materializeModifier22, ComposeUiNode.INSTANCE.getSetModifier());
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                        BoxScopeInstance boxScopeInstance22 = BoxScopeInstance.INSTANCE;
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -1543730008, "C193@9057L78:NavigationBar.kt#uh7d8r");
                                                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function27, composer3, ProvidedValue.$stable);
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

                                                private static final long invoke$lambda$0(State<Color> state) {
                                                    return state.getValue().m4625unboximpl();
                                                }
                                            }, startRestartGroup, 54);
                                            startRestartGroup.startReplaceGroup(-103210706);
                                            ComposerKt.sourceInformation(startRestartGroup, "*199@9255L535");
                                            if (function24 != null) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103192112, "CC(remember):NavigationBar.kt#9igjgp");
                                            rememberedValue = startRestartGroup.rememberedValue();
                                            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            }
                                            final MutableIntState mutableIntState22 = (MutableIntState) rememberedValue;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            Modifier modifier422 = modifier2;
                                            boolean z1022 = z4;
                                            ComposableLambda composableLambda222 = rememberComposableLambda;
                                            Modifier weight$default22 = RowScope.CC.weight$default(rowScope, SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier422, z, mutableInteractionSource522, null, z1022, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationBarHeight, 1, null), 1.0f, false, 2, null);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103177977, "CC(remember):NavigationBar.kt#9igjgp");
                                            rememberedValue2 = startRestartGroup.rememberedValue();
                                            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            Modifier onSizeChanged22 = OnRemeasuredModifierKt.onSizeChanged(weight$default22, (Function1) rememberedValue2);
                                            Alignment center22 = Alignment.INSTANCE.getCenter();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                            MeasurePolicy maybeCachedBoxMeasurePolicy22 = BoxKt.maybeCachedBoxMeasurePolicy(center22, true);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                                            CompositionLocalMap currentCompositionLocalMap22 = startRestartGroup.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier22 = ComposedModifierKt.materializeModifier(startRestartGroup, onSizeChanged22);
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
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1652172306, "C233@10442L157,*242@10883L7,248@11165L128,255@11508L293,263@11850L395,280@12505L27,274@12255L288:NavigationBar.kt#uh7d8r");
                                            boolean z1122 = z5;
                                            MutableInteractionSource mutableInteractionSource622 = mutableInteractionSource2;
                                            final State<Float> animateFloatAsState22 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                                            ProvidableCompositionLocal<Density> localDensity22 = CompositionLocalsKt.getLocalDensity();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                            Object consume22 = startRestartGroup.consume(localDensity22);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            long Offset22 = OffsetKt.Offset((NavigationBarItem$lambda$3(mutableIntState22) - r2.mo453roundToPx0680j_4(NavigationBarTokens.INSTANCE.m3504getActiveIndicatorWidthD9Ej5fM())) / 2, ((Density) consume22).mo459toPx0680j_4(IndicatorVerticalOffset));
                                            Unit unit22 = Unit.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024936814, "CC(remember):NavigationBar.kt#9igjgp");
                                            changed = startRestartGroup.changed(mutableInteractionSource522) | startRestartGroup.changed(Offset22);
                                            rememberedValue3 = startRestartGroup.rememberedValue();
                                            if (!changed) {
                                            }
                                            rememberedValue3 = new MappedInteractionSource(mutableInteractionSource522, Offset22, null);
                                            startRestartGroup.updateRememberedValue(rememberedValue3);
                                            final MappedInteractionSource mappedInteractionSource22 = (MappedInteractionSource) rememberedValue3;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposableLambda rememberComposableLambda322 = ComposableLambdaKt.rememberComposableLambda(691730997, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                    invoke(composer3, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer3, int i13) {
                                                    ComposerKt.sourceInformation(composer3, "C258@11668L5,259@11736L32,256@11526L261:NavigationBar.kt#uh7d8r");
                                                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(691730997, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:256)");
                                                        }
                                                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), MappedInteractionSource.this, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer3, 0, 7)), composer3, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer3.skipToGroupEnd();
                                                }
                                            }, startRestartGroup, 54);
                                            ComposableLambda rememberComposableLambda422 = ComposableLambdaKt.rememberComposableLambda(-474426875, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                    invoke(composer3, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer3, int i13) {
                                                    ComposerKt.sourceInformation(composer3, "C266@11972L35,269@12181L5,264@11868L363:NavigationBar.kt#uh7d8r");
                                                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-474426875, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:264)");
                                                        }
                                                        Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                                                        ComposerKt.sourceInformationMarkerStart(composer3, 1335768816, "CC(remember):NavigationBar.kt#9igjgp");
                                                        boolean changed3 = composer3.changed(animateFloatAsState22);
                                                        final State<Float> state = animateFloatAsState22;
                                                        Object rememberedValue6 = composer3.rememberedValue();
                                                        if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                            rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1$1$1
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
                                                            composer3.updateRememberedValue(rememberedValue6);
                                                        }
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                        BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue6), navigationBarItemColors422.getSelectedIndicatorColor(), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), composer3, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer3.skipToGroupEnd();
                                                }
                                            }, startRestartGroup, 54);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024979593, "CC(remember):NavigationBar.kt#9igjgp");
                                            changed2 = startRestartGroup.changed(animateFloatAsState22);
                                            rememberedValue4 = startRestartGroup.rememberedValue();
                                            if (!changed2) {
                                            }
                                            rememberedValue4 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$2$1
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
                                            startRestartGroup.updateRememberedValue(rememberedValue4);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            NavigationBarItemLayout(rememberComposableLambda322, rememberComposableLambda422, composableLambda, composableLambda222, z1122, (Function0) rememberedValue4, startRestartGroup, ((i11 >> 9) & 57344) | 438);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            startRestartGroup.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            mutableInteractionSource4 = mutableInteractionSource622;
                                            navigationBarItemColors3 = navigationBarItemColors422;
                                            z6 = z1122;
                                            composer2 = startRestartGroup;
                                            z7 = z1022;
                                            modifier3 = modifier422;
                                        } else {
                                            navigationBarItemColors2 = navigationBarItemColors;
                                            mutableInteractionSource2 = mutableInteractionSource;
                                            i11 = i10;
                                            startRestartGroup.endDefaults();
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            startRestartGroup.startReplaceGroup(-103236853);
                                            ComposerKt.sourceInformation(startRestartGroup, "182@8445L39");
                                            if (mutableInteractionSource2 != null) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            final NavigationBarItemColors navigationBarItemColors4222 = navigationBarItemColors2;
                                            final Function2<? super Composer, ? super Integer, Unit> function25222 = function23;
                                            final boolean z8222 = z5;
                                            final boolean z9222 = z4;
                                            final Function2<? super Composer, ? super Integer, Unit> function26222 = function24;
                                            MutableInteractionSource mutableInteractionSource5222 = mutableInteractionSource3;
                                            ComposableLambda rememberComposableLambda2222 = ComposableLambdaKt.rememberComposableLambda(-1419576100, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1
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

                                                public final void invoke(Composer composer3, int i13) {
                                                    ComposerKt.sourceInformation(composer3, "C186@8573L201,192@8956L193:NavigationBar.kt#uh7d8r");
                                                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-1419576100, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous> (NavigationBar.kt:185)");
                                                        }
                                                        State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationBarItemColors.this.m2425iconColorWaAFU9c$material3_release(z, z9222), AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer3, 48, 12);
                                                        Modifier.Companion clearAndSetSemantics = (function26222 == null || !(z8222 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1.1
                                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                            }

                                                            @Override // kotlin.jvm.functions.Function1
                                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                                invoke2(semanticsPropertyReceiver);
                                                                return Unit.INSTANCE;
                                                            }
                                                        });
                                                        Function2<Composer, Integer, Unit> function27 = function25222;
                                                        ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                        MeasurePolicy maybeCachedBoxMeasurePolicy222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                        CompositionLocalMap currentCompositionLocalMap222 = composer3.getCurrentCompositionLocalMap();
                                                        Modifier materializeModifier222 = ComposedModifierKt.materializeModifier(composer3, clearAndSetSemantics);
                                                        Function0<ComposeUiNode> constructor222 = ComposeUiNode.INSTANCE.getConstructor();
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                                        if (!(composer3.getApplier() instanceof Applier)) {
                                                            ComposablesKt.invalidApplier();
                                                        }
                                                        composer3.startReusableNode();
                                                        if (composer3.getInserting()) {
                                                            composer3.createNode(constructor222);
                                                        } else {
                                                            composer3.useNode();
                                                        }
                                                        Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer3);
                                                        Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                        if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                                            m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                                            m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash222);
                                                        }
                                                        Updater.m3912setimpl(m3905constructorimpl2, materializeModifier222, ComposeUiNode.INSTANCE.getSetModifier());
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                        BoxScopeInstance boxScopeInstance222 = BoxScopeInstance.INSTANCE;
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -1543730008, "C193@9057L78:NavigationBar.kt#uh7d8r");
                                                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function27, composer3, ProvidedValue.$stable);
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

                                                private static final long invoke$lambda$0(State<Color> state) {
                                                    return state.getValue().m4625unboximpl();
                                                }
                                            }, startRestartGroup, 54);
                                            startRestartGroup.startReplaceGroup(-103210706);
                                            ComposerKt.sourceInformation(startRestartGroup, "*199@9255L535");
                                            if (function24 != null) {
                                            }
                                            startRestartGroup.endReplaceGroup();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103192112, "CC(remember):NavigationBar.kt#9igjgp");
                                            rememberedValue = startRestartGroup.rememberedValue();
                                            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            }
                                            final MutableIntState mutableIntState222 = (MutableIntState) rememberedValue;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            Modifier modifier4222 = modifier2;
                                            boolean z10222 = z4;
                                            ComposableLambda composableLambda2222 = rememberComposableLambda;
                                            Modifier weight$default222 = RowScope.CC.weight$default(rowScope, SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier4222, z, mutableInteractionSource5222, null, z10222, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationBarHeight, 1, null), 1.0f, false, 2, null);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103177977, "CC(remember):NavigationBar.kt#9igjgp");
                                            rememberedValue2 = startRestartGroup.rememberedValue();
                                            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            Modifier onSizeChanged222 = OnRemeasuredModifierKt.onSizeChanged(weight$default222, (Function1) rememberedValue2);
                                            Alignment center222 = Alignment.INSTANCE.getCenter();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                            MeasurePolicy maybeCachedBoxMeasurePolicy222 = BoxKt.maybeCachedBoxMeasurePolicy(center222, true);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                                            CompositionLocalMap currentCompositionLocalMap222 = startRestartGroup.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier222 = ComposedModifierKt.materializeModifier(startRestartGroup, onSizeChanged222);
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
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1652172306, "C233@10442L157,*242@10883L7,248@11165L128,255@11508L293,263@11850L395,280@12505L27,274@12255L288:NavigationBar.kt#uh7d8r");
                                            boolean z11222 = z5;
                                            MutableInteractionSource mutableInteractionSource6222 = mutableInteractionSource2;
                                            final State<Float> animateFloatAsState222 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                                            ProvidableCompositionLocal<Density> localDensity222 = CompositionLocalsKt.getLocalDensity();
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                            Object consume222 = startRestartGroup.consume(localDensity222);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            long Offset222 = OffsetKt.Offset((NavigationBarItem$lambda$3(mutableIntState222) - r2.mo453roundToPx0680j_4(NavigationBarTokens.INSTANCE.m3504getActiveIndicatorWidthD9Ej5fM())) / 2, ((Density) consume222).mo459toPx0680j_4(IndicatorVerticalOffset));
                                            Unit unit222 = Unit.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024936814, "CC(remember):NavigationBar.kt#9igjgp");
                                            changed = startRestartGroup.changed(mutableInteractionSource5222) | startRestartGroup.changed(Offset222);
                                            rememberedValue3 = startRestartGroup.rememberedValue();
                                            if (!changed) {
                                            }
                                            rememberedValue3 = new MappedInteractionSource(mutableInteractionSource5222, Offset222, null);
                                            startRestartGroup.updateRememberedValue(rememberedValue3);
                                            final MappedInteractionSource mappedInteractionSource222 = (MappedInteractionSource) rememberedValue3;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposableLambda rememberComposableLambda3222 = ComposableLambdaKt.rememberComposableLambda(691730997, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                    invoke(composer3, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer3, int i13) {
                                                    ComposerKt.sourceInformation(composer3, "C258@11668L5,259@11736L32,256@11526L261:NavigationBar.kt#uh7d8r");
                                                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(691730997, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:256)");
                                                        }
                                                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), MappedInteractionSource.this, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer3, 0, 7)), composer3, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer3.skipToGroupEnd();
                                                }
                                            }, startRestartGroup, 54);
                                            ComposableLambda rememberComposableLambda4222 = ComposableLambdaKt.rememberComposableLambda(-474426875, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                    invoke(composer3, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer composer3, int i13) {
                                                    ComposerKt.sourceInformation(composer3, "C266@11972L35,269@12181L5,264@11868L363:NavigationBar.kt#uh7d8r");
                                                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-474426875, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:264)");
                                                        }
                                                        Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                                                        ComposerKt.sourceInformationMarkerStart(composer3, 1335768816, "CC(remember):NavigationBar.kt#9igjgp");
                                                        boolean changed3 = composer3.changed(animateFloatAsState222);
                                                        final State<Float> state = animateFloatAsState222;
                                                        Object rememberedValue6 = composer3.rememberedValue();
                                                        if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                            rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1$1$1
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
                                                            composer3.updateRememberedValue(rememberedValue6);
                                                        }
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                        BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue6), navigationBarItemColors4222.getSelectedIndicatorColor(), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), composer3, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    composer3.skipToGroupEnd();
                                                }
                                            }, startRestartGroup, 54);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024979593, "CC(remember):NavigationBar.kt#9igjgp");
                                            changed2 = startRestartGroup.changed(animateFloatAsState222);
                                            rememberedValue4 = startRestartGroup.rememberedValue();
                                            if (!changed2) {
                                            }
                                            rememberedValue4 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$2$1
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
                                            startRestartGroup.updateRememberedValue(rememberedValue4);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            NavigationBarItemLayout(rememberComposableLambda3222, rememberComposableLambda4222, composableLambda, composableLambda2222, z11222, (Function0) rememberedValue4, startRestartGroup, ((i11 >> 9) & 57344) | 438);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            startRestartGroup.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            mutableInteractionSource4 = mutableInteractionSource6222;
                                            navigationBarItemColors3 = navigationBarItemColors4222;
                                            z6 = z11222;
                                            composer2 = startRestartGroup;
                                            z7 = z10222;
                                            modifier3 = modifier4222;
                                        }
                                    }
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    mutableInteractionSource4 = mutableInteractionSource;
                                    composer2 = startRestartGroup;
                                    modifier3 = modifier2;
                                    z6 = z5;
                                    z7 = z4;
                                    navigationBarItemColors3 = navigationBarItemColors;
                                }
                                final Function2<? super Composer, ? super Integer, Unit> function27 = function24;
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$3
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

                                        public final void invoke(Composer composer3, int i13) {
                                            NavigationBarKt.NavigationBarItem(RowScope.this, z, function0, function2, modifier3, z7, function27, z6, navigationBarItemColors3, mutableInteractionSource4, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            i9 = i8;
                            if ((i3 & 306783379) == 306783378) {
                            }
                            startRestartGroup.startDefaults();
                            i10 = i3;
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
                            i11 = i10;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            startRestartGroup.startReplaceGroup(-103236853);
                            ComposerKt.sourceInformation(startRestartGroup, "182@8445L39");
                            if (mutableInteractionSource2 != null) {
                            }
                            startRestartGroup.endReplaceGroup();
                            final NavigationBarItemColors navigationBarItemColors42222 = navigationBarItemColors2;
                            final Function2<? super Composer, ? super Integer, Unit> function252222 = function23;
                            final boolean z82222 = z5;
                            final boolean z92222 = z4;
                            final Function2<? super Composer, ? super Integer, Unit> function262222 = function24;
                            MutableInteractionSource mutableInteractionSource52222 = mutableInteractionSource3;
                            ComposableLambda rememberComposableLambda22222 = ComposableLambdaKt.rememberComposableLambda(-1419576100, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1
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

                                public final void invoke(Composer composer3, int i13) {
                                    ComposerKt.sourceInformation(composer3, "C186@8573L201,192@8956L193:NavigationBar.kt#uh7d8r");
                                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1419576100, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous> (NavigationBar.kt:185)");
                                        }
                                        State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationBarItemColors.this.m2425iconColorWaAFU9c$material3_release(z, z92222), AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer3, 48, 12);
                                        Modifier.Companion clearAndSetSemantics = (function262222 == null || !(z82222 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1.1
                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        Function2<Composer, Integer, Unit> function272 = function252222;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy maybeCachedBoxMeasurePolicy2222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                        CompositionLocalMap currentCompositionLocalMap2222 = composer3.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier2222 = ComposedModifierKt.materializeModifier(composer3, clearAndSetSemantics);
                                        Function0<ComposeUiNode> constructor2222 = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer3.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            composer3.createNode(constructor2222);
                                        } else {
                                            composer3.useNode();
                                        }
                                        Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer3);
                                        Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy2222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                            m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                            m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2222);
                                        }
                                        Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2222, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance2222 = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1543730008, "C193@9057L78:NavigationBar.kt#uh7d8r");
                                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function272, composer3, ProvidedValue.$stable);
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

                                private static final long invoke$lambda$0(State<Color> state) {
                                    return state.getValue().m4625unboximpl();
                                }
                            }, startRestartGroup, 54);
                            startRestartGroup.startReplaceGroup(-103210706);
                            ComposerKt.sourceInformation(startRestartGroup, "*199@9255L535");
                            if (function24 != null) {
                            }
                            startRestartGroup.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103192112, "CC(remember):NavigationBar.kt#9igjgp");
                            rememberedValue = startRestartGroup.rememberedValue();
                            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                            }
                            final MutableIntState mutableIntState2222 = (MutableIntState) rememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            Modifier modifier42222 = modifier2;
                            boolean z102222 = z4;
                            ComposableLambda composableLambda22222 = rememberComposableLambda;
                            Modifier weight$default2222 = RowScope.CC.weight$default(rowScope, SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier42222, z, mutableInteractionSource52222, null, z102222, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationBarHeight, 1, null), 1.0f, false, 2, null);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103177977, "CC(remember):NavigationBar.kt#9igjgp");
                            rememberedValue2 = startRestartGroup.rememberedValue();
                            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            }
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            Modifier onSizeChanged2222 = OnRemeasuredModifierKt.onSizeChanged(weight$default2222, (Function1) rememberedValue2);
                            Alignment center2222 = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy maybeCachedBoxMeasurePolicy2222 = BoxKt.maybeCachedBoxMeasurePolicy(center2222, true);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                            CompositionLocalMap currentCompositionLocalMap2222 = startRestartGroup.getCurrentCompositionLocalMap();
                            Modifier materializeModifier2222 = ComposedModifierKt.materializeModifier(startRestartGroup, onSizeChanged2222);
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
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1652172306, "C233@10442L157,*242@10883L7,248@11165L128,255@11508L293,263@11850L395,280@12505L27,274@12255L288:NavigationBar.kt#uh7d8r");
                            boolean z112222 = z5;
                            MutableInteractionSource mutableInteractionSource62222 = mutableInteractionSource2;
                            final State<Float> animateFloatAsState2222 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                            ProvidableCompositionLocal<Density> localDensity2222 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object consume2222 = startRestartGroup.consume(localDensity2222);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            long Offset2222 = OffsetKt.Offset((NavigationBarItem$lambda$3(mutableIntState2222) - r2.mo453roundToPx0680j_4(NavigationBarTokens.INSTANCE.m3504getActiveIndicatorWidthD9Ej5fM())) / 2, ((Density) consume2222).mo459toPx0680j_4(IndicatorVerticalOffset));
                            Unit unit2222 = Unit.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024936814, "CC(remember):NavigationBar.kt#9igjgp");
                            changed = startRestartGroup.changed(mutableInteractionSource52222) | startRestartGroup.changed(Offset2222);
                            rememberedValue3 = startRestartGroup.rememberedValue();
                            if (!changed) {
                            }
                            rememberedValue3 = new MappedInteractionSource(mutableInteractionSource52222, Offset2222, null);
                            startRestartGroup.updateRememberedValue(rememberedValue3);
                            final MappedInteractionSource mappedInteractionSource2222 = (MappedInteractionSource) rememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposableLambda rememberComposableLambda32222 = ComposableLambdaKt.rememberComposableLambda(691730997, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i13) {
                                    ComposerKt.sourceInformation(composer3, "C258@11668L5,259@11736L32,256@11526L261:NavigationBar.kt#uh7d8r");
                                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(691730997, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:256)");
                                        }
                                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), MappedInteractionSource.this, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer3, 0, 7)), composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, startRestartGroup, 54);
                            ComposableLambda rememberComposableLambda42222 = ComposableLambdaKt.rememberComposableLambda(-474426875, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i13) {
                                    ComposerKt.sourceInformation(composer3, "C266@11972L35,269@12181L5,264@11868L363:NavigationBar.kt#uh7d8r");
                                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-474426875, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:264)");
                                        }
                                        Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1335768816, "CC(remember):NavigationBar.kt#9igjgp");
                                        boolean changed3 = composer3.changed(animateFloatAsState2222);
                                        final State<Float> state = animateFloatAsState2222;
                                        Object rememberedValue6 = composer3.rememberedValue();
                                        if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1$1$1
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
                                            composer3.updateRememberedValue(rememberedValue6);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue6), navigationBarItemColors42222.getSelectedIndicatorColor(), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, startRestartGroup, 54);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024979593, "CC(remember):NavigationBar.kt#9igjgp");
                            changed2 = startRestartGroup.changed(animateFloatAsState2222);
                            rememberedValue4 = startRestartGroup.rememberedValue();
                            if (!changed2) {
                            }
                            rememberedValue4 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$2$1
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
                            startRestartGroup.updateRememberedValue(rememberedValue4);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            NavigationBarItemLayout(rememberComposableLambda32222, rememberComposableLambda42222, composableLambda, composableLambda22222, z112222, (Function0) rememberedValue4, startRestartGroup, ((i11 >> 9) & 57344) | 438);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            startRestartGroup.endNode();
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            mutableInteractionSource4 = mutableInteractionSource62222;
                            navigationBarItemColors3 = navigationBarItemColors42222;
                            z6 = z112222;
                            composer2 = startRestartGroup;
                            z7 = z102222;
                            modifier3 = modifier42222;
                            final Function2<? super Composer, ? super Integer, Unit> function272 = function24;
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        function24 = function22;
                        i7 = i2 & 64;
                        if (i7 == 0) {
                        }
                        if ((i & 100663296) == 0) {
                        }
                        i8 = i2 & 256;
                        if (i8 == 0) {
                        }
                        i9 = i8;
                        if ((i3 & 306783379) == 306783378) {
                        }
                        startRestartGroup.startDefaults();
                        i10 = i3;
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
                        i11 = i10;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        startRestartGroup.startReplaceGroup(-103236853);
                        ComposerKt.sourceInformation(startRestartGroup, "182@8445L39");
                        if (mutableInteractionSource2 != null) {
                        }
                        startRestartGroup.endReplaceGroup();
                        final NavigationBarItemColors navigationBarItemColors422222 = navigationBarItemColors2;
                        final Function2<? super Composer, ? super Integer, Unit> function2522222 = function23;
                        final boolean z822222 = z5;
                        final boolean z922222 = z4;
                        final Function2<? super Composer, ? super Integer, Unit> function2622222 = function24;
                        MutableInteractionSource mutableInteractionSource522222 = mutableInteractionSource3;
                        ComposableLambda rememberComposableLambda222222 = ComposableLambdaKt.rememberComposableLambda(-1419576100, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1
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

                            public final void invoke(Composer composer3, int i13) {
                                ComposerKt.sourceInformation(composer3, "C186@8573L201,192@8956L193:NavigationBar.kt#uh7d8r");
                                if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1419576100, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous> (NavigationBar.kt:185)");
                                    }
                                    State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationBarItemColors.this.m2425iconColorWaAFU9c$material3_release(z, z922222), AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer3, 48, 12);
                                    Modifier.Companion clearAndSetSemantics = (function2622222 == null || !(z822222 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1.1
                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }
                                    });
                                    Function2<Composer, Integer, Unit> function2722 = function2522222;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy maybeCachedBoxMeasurePolicy22222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap22222 = composer3.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier22222 = ComposedModifierKt.materializeModifier(composer3, clearAndSetSemantics);
                                    Function0<ComposeUiNode> constructor22222 = ComposeUiNode.INSTANCE.getConstructor();
                                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor22222);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer3);
                                    Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy22222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap22222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash22222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                        m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                        m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash22222);
                                    }
                                    Updater.m3912setimpl(m3905constructorimpl2, materializeModifier22222, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance22222 = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1543730008, "C193@9057L78:NavigationBar.kt#uh7d8r");
                                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function2722, composer3, ProvidedValue.$stable);
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

                            private static final long invoke$lambda$0(State<Color> state) {
                                return state.getValue().m4625unboximpl();
                            }
                        }, startRestartGroup, 54);
                        startRestartGroup.startReplaceGroup(-103210706);
                        ComposerKt.sourceInformation(startRestartGroup, "*199@9255L535");
                        if (function24 != null) {
                        }
                        startRestartGroup.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103192112, "CC(remember):NavigationBar.kt#9igjgp");
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                        }
                        final MutableIntState mutableIntState22222 = (MutableIntState) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        Modifier modifier422222 = modifier2;
                        boolean z1022222 = z4;
                        ComposableLambda composableLambda222222 = rememberComposableLambda;
                        Modifier weight$default22222 = RowScope.CC.weight$default(rowScope, SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier422222, z, mutableInteractionSource522222, null, z1022222, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationBarHeight, 1, null), 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103177977, "CC(remember):NavigationBar.kt#9igjgp");
                        rememberedValue2 = startRestartGroup.rememberedValue();
                        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        Modifier onSizeChanged22222 = OnRemeasuredModifierKt.onSizeChanged(weight$default22222, (Function1) rememberedValue2);
                        Alignment center22222 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy22222 = BoxKt.maybeCachedBoxMeasurePolicy(center22222, true);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap22222 = startRestartGroup.getCurrentCompositionLocalMap();
                        Modifier materializeModifier22222 = ComposedModifierKt.materializeModifier(startRestartGroup, onSizeChanged22222);
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
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1652172306, "C233@10442L157,*242@10883L7,248@11165L128,255@11508L293,263@11850L395,280@12505L27,274@12255L288:NavigationBar.kt#uh7d8r");
                        boolean z1122222 = z5;
                        MutableInteractionSource mutableInteractionSource622222 = mutableInteractionSource2;
                        final State<Float> animateFloatAsState22222 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                        ProvidableCompositionLocal<Density> localDensity22222 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object consume22222 = startRestartGroup.consume(localDensity22222);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        long Offset22222 = OffsetKt.Offset((NavigationBarItem$lambda$3(mutableIntState22222) - r2.mo453roundToPx0680j_4(NavigationBarTokens.INSTANCE.m3504getActiveIndicatorWidthD9Ej5fM())) / 2, ((Density) consume22222).mo459toPx0680j_4(IndicatorVerticalOffset));
                        Unit unit22222 = Unit.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024936814, "CC(remember):NavigationBar.kt#9igjgp");
                        changed = startRestartGroup.changed(mutableInteractionSource522222) | startRestartGroup.changed(Offset22222);
                        rememberedValue3 = startRestartGroup.rememberedValue();
                        if (!changed) {
                        }
                        rememberedValue3 = new MappedInteractionSource(mutableInteractionSource522222, Offset22222, null);
                        startRestartGroup.updateRememberedValue(rememberedValue3);
                        final MappedInteractionSource mappedInteractionSource22222 = (MappedInteractionSource) rememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposableLambda rememberComposableLambda322222 = ComposableLambdaKt.rememberComposableLambda(691730997, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i13) {
                                ComposerKt.sourceInformation(composer3, "C258@11668L5,259@11736L32,256@11526L261:NavigationBar.kt#uh7d8r");
                                if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(691730997, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:256)");
                                    }
                                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), MappedInteractionSource.this, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer3, 0, 7)), composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, startRestartGroup, 54);
                        ComposableLambda rememberComposableLambda422222 = ComposableLambdaKt.rememberComposableLambda(-474426875, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i13) {
                                ComposerKt.sourceInformation(composer3, "C266@11972L35,269@12181L5,264@11868L363:NavigationBar.kt#uh7d8r");
                                if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-474426875, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:264)");
                                    }
                                    Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1335768816, "CC(remember):NavigationBar.kt#9igjgp");
                                    boolean changed3 = composer3.changed(animateFloatAsState22222);
                                    final State<Float> state = animateFloatAsState22222;
                                    Object rememberedValue6 = composer3.rememberedValue();
                                    if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1$1$1
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
                                        composer3.updateRememberedValue(rememberedValue6);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue6), navigationBarItemColors422222.getSelectedIndicatorColor(), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, startRestartGroup, 54);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024979593, "CC(remember):NavigationBar.kt#9igjgp");
                        changed2 = startRestartGroup.changed(animateFloatAsState22222);
                        rememberedValue4 = startRestartGroup.rememberedValue();
                        if (!changed2) {
                        }
                        rememberedValue4 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$2$1
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
                        startRestartGroup.updateRememberedValue(rememberedValue4);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        NavigationBarItemLayout(rememberComposableLambda322222, rememberComposableLambda422222, composableLambda, composableLambda222222, z1122222, (Function0) rememberedValue4, startRestartGroup, ((i11 >> 9) & 57344) | 438);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        startRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        mutableInteractionSource4 = mutableInteractionSource622222;
                        navigationBarItemColors3 = navigationBarItemColors422222;
                        z6 = z1122222;
                        composer2 = startRestartGroup;
                        z7 = z1022222;
                        modifier3 = modifier422222;
                        final Function2<? super Composer, ? super Integer, Unit> function2722 = function24;
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    z4 = z2;
                    i6 = i2 & 32;
                    if (i6 != 0) {
                    }
                    function24 = function22;
                    i7 = i2 & 64;
                    if (i7 == 0) {
                    }
                    if ((i & 100663296) == 0) {
                    }
                    i8 = i2 & 256;
                    if (i8 == 0) {
                    }
                    i9 = i8;
                    if ((i3 & 306783379) == 306783378) {
                    }
                    startRestartGroup.startDefaults();
                    i10 = i3;
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
                    i11 = i10;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.startReplaceGroup(-103236853);
                    ComposerKt.sourceInformation(startRestartGroup, "182@8445L39");
                    if (mutableInteractionSource2 != null) {
                    }
                    startRestartGroup.endReplaceGroup();
                    final NavigationBarItemColors navigationBarItemColors4222222 = navigationBarItemColors2;
                    final Function2<? super Composer, ? super Integer, Unit> function25222222 = function23;
                    final boolean z8222222 = z5;
                    final boolean z9222222 = z4;
                    final Function2<? super Composer, ? super Integer, Unit> function26222222 = function24;
                    MutableInteractionSource mutableInteractionSource5222222 = mutableInteractionSource3;
                    ComposableLambda rememberComposableLambda2222222 = ComposableLambdaKt.rememberComposableLambda(-1419576100, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1
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

                        public final void invoke(Composer composer3, int i13) {
                            ComposerKt.sourceInformation(composer3, "C186@8573L201,192@8956L193:NavigationBar.kt#uh7d8r");
                            if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1419576100, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous> (NavigationBar.kt:185)");
                                }
                                State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationBarItemColors.this.m2425iconColorWaAFU9c$material3_release(z, z9222222), AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer3, 48, 12);
                                Modifier.Companion clearAndSetSemantics = (function26222222 == null || !(z8222222 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1.1
                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }
                                });
                                Function2<Composer, Integer, Unit> function27222 = function25222222;
                                ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy maybeCachedBoxMeasurePolicy222222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap222222 = composer3.getCurrentCompositionLocalMap();
                                Modifier materializeModifier222222 = ComposedModifierKt.materializeModifier(composer3, clearAndSetSemantics);
                                Function0<ComposeUiNode> constructor222222 = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer3.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor222222);
                                } else {
                                    composer3.useNode();
                                }
                                Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer3);
                                Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                    m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                    m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash222222);
                                }
                                Updater.m3912setimpl(m3905constructorimpl2, materializeModifier222222, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance222222 = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer3, -1543730008, "C193@9057L78:NavigationBar.kt#uh7d8r");
                                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function27222, composer3, ProvidedValue.$stable);
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

                        private static final long invoke$lambda$0(State<Color> state) {
                            return state.getValue().m4625unboximpl();
                        }
                    }, startRestartGroup, 54);
                    startRestartGroup.startReplaceGroup(-103210706);
                    ComposerKt.sourceInformation(startRestartGroup, "*199@9255L535");
                    if (function24 != null) {
                    }
                    startRestartGroup.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103192112, "CC(remember):NavigationBar.kt#9igjgp");
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    }
                    final MutableIntState mutableIntState222222 = (MutableIntState) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    Modifier modifier4222222 = modifier2;
                    boolean z10222222 = z4;
                    ComposableLambda composableLambda2222222 = rememberComposableLambda;
                    Modifier weight$default222222 = RowScope.CC.weight$default(rowScope, SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier4222222, z, mutableInteractionSource5222222, null, z10222222, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationBarHeight, 1, null), 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103177977, "CC(remember):NavigationBar.kt#9igjgp");
                    rememberedValue2 = startRestartGroup.rememberedValue();
                    if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    Modifier onSizeChanged222222 = OnRemeasuredModifierKt.onSizeChanged(weight$default222222, (Function1) rememberedValue2);
                    Alignment center222222 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy222222 = BoxKt.maybeCachedBoxMeasurePolicy(center222222, true);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap222222 = startRestartGroup.getCurrentCompositionLocalMap();
                    Modifier materializeModifier222222 = ComposedModifierKt.materializeModifier(startRestartGroup, onSizeChanged222222);
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
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1652172306, "C233@10442L157,*242@10883L7,248@11165L128,255@11508L293,263@11850L395,280@12505L27,274@12255L288:NavigationBar.kt#uh7d8r");
                    boolean z11222222 = z5;
                    MutableInteractionSource mutableInteractionSource6222222 = mutableInteractionSource2;
                    final State<Float> animateFloatAsState222222 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                    ProvidableCompositionLocal<Density> localDensity222222 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume222222 = startRestartGroup.consume(localDensity222222);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    long Offset222222 = OffsetKt.Offset((NavigationBarItem$lambda$3(mutableIntState222222) - r2.mo453roundToPx0680j_4(NavigationBarTokens.INSTANCE.m3504getActiveIndicatorWidthD9Ej5fM())) / 2, ((Density) consume222222).mo459toPx0680j_4(IndicatorVerticalOffset));
                    Unit unit222222 = Unit.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024936814, "CC(remember):NavigationBar.kt#9igjgp");
                    changed = startRestartGroup.changed(mutableInteractionSource5222222) | startRestartGroup.changed(Offset222222);
                    rememberedValue3 = startRestartGroup.rememberedValue();
                    if (!changed) {
                    }
                    rememberedValue3 = new MappedInteractionSource(mutableInteractionSource5222222, Offset222222, null);
                    startRestartGroup.updateRememberedValue(rememberedValue3);
                    final MappedInteractionSource mappedInteractionSource222222 = (MappedInteractionSource) rememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposableLambda rememberComposableLambda3222222 = ComposableLambdaKt.rememberComposableLambda(691730997, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i13) {
                            ComposerKt.sourceInformation(composer3, "C258@11668L5,259@11736L32,256@11526L261:NavigationBar.kt#uh7d8r");
                            if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(691730997, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:256)");
                                }
                                BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), MappedInteractionSource.this, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer3, 0, 7)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, startRestartGroup, 54);
                    ComposableLambda rememberComposableLambda4222222 = ComposableLambdaKt.rememberComposableLambda(-474426875, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i13) {
                            ComposerKt.sourceInformation(composer3, "C266@11972L35,269@12181L5,264@11868L363:NavigationBar.kt#uh7d8r");
                            if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-474426875, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:264)");
                                }
                                Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                                ComposerKt.sourceInformationMarkerStart(composer3, 1335768816, "CC(remember):NavigationBar.kt#9igjgp");
                                boolean changed3 = composer3.changed(animateFloatAsState222222);
                                final State<Float> state = animateFloatAsState222222;
                                Object rememberedValue6 = composer3.rememberedValue();
                                if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1$1$1
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
                                    composer3.updateRememberedValue(rememberedValue6);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue6), navigationBarItemColors4222222.getSelectedIndicatorColor(), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }, startRestartGroup, 54);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024979593, "CC(remember):NavigationBar.kt#9igjgp");
                    changed2 = startRestartGroup.changed(animateFloatAsState222222);
                    rememberedValue4 = startRestartGroup.rememberedValue();
                    if (!changed2) {
                    }
                    rememberedValue4 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$2$1
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
                    startRestartGroup.updateRememberedValue(rememberedValue4);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    NavigationBarItemLayout(rememberComposableLambda3222222, rememberComposableLambda4222222, composableLambda, composableLambda2222222, z11222222, (Function0) rememberedValue4, startRestartGroup, ((i11 >> 9) & 57344) | 438);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    startRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    mutableInteractionSource4 = mutableInteractionSource6222222;
                    navigationBarItemColors3 = navigationBarItemColors4222222;
                    z6 = z11222222;
                    composer2 = startRestartGroup;
                    z7 = z10222222;
                    modifier3 = modifier4222222;
                    final Function2<? super Composer, ? super Integer, Unit> function27222 = function24;
                    endRestartGroup = composer2.endRestartGroup();
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
                function24 = function22;
                i7 = i2 & 64;
                if (i7 == 0) {
                }
                if ((i & 100663296) == 0) {
                }
                i8 = i2 & 256;
                if (i8 == 0) {
                }
                i9 = i8;
                if ((i3 & 306783379) == 306783378) {
                }
                startRestartGroup.startDefaults();
                i10 = i3;
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
                i11 = i10;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.startReplaceGroup(-103236853);
                ComposerKt.sourceInformation(startRestartGroup, "182@8445L39");
                if (mutableInteractionSource2 != null) {
                }
                startRestartGroup.endReplaceGroup();
                final NavigationBarItemColors navigationBarItemColors42222222 = navigationBarItemColors2;
                final Function2<? super Composer, ? super Integer, Unit> function252222222 = function23;
                final boolean z82222222 = z5;
                final boolean z92222222 = z4;
                final Function2<? super Composer, ? super Integer, Unit> function262222222 = function24;
                MutableInteractionSource mutableInteractionSource52222222 = mutableInteractionSource3;
                ComposableLambda rememberComposableLambda22222222 = ComposableLambdaKt.rememberComposableLambda(-1419576100, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1
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

                    public final void invoke(Composer composer3, int i13) {
                        ComposerKt.sourceInformation(composer3, "C186@8573L201,192@8956L193:NavigationBar.kt#uh7d8r");
                        if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1419576100, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous> (NavigationBar.kt:185)");
                            }
                            State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationBarItemColors.this.m2425iconColorWaAFU9c$material3_release(z, z92222222), AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer3, 48, 12);
                            Modifier.Companion clearAndSetSemantics = (function262222222 == null || !(z82222222 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1.1
                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }
                            });
                            Function2<Composer, Integer, Unit> function272222 = function252222222;
                            ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy maybeCachedBoxMeasurePolicy2222222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap2222222 = composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier2222222 = ComposedModifierKt.materializeModifier(composer3, clearAndSetSemantics);
                            Function0<ComposeUiNode> constructor2222222 = ComposeUiNode.INSTANCE.getConstructor();
                            ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!(composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                                composer3.createNode(constructor2222222);
                            } else {
                                composer3.useNode();
                            }
                            Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer3);
                            Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy2222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2222222);
                            }
                            Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2222222, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance2222222 = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer3, -1543730008, "C193@9057L78:NavigationBar.kt#uh7d8r");
                            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function272222, composer3, ProvidedValue.$stable);
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

                    private static final long invoke$lambda$0(State<Color> state) {
                        return state.getValue().m4625unboximpl();
                    }
                }, startRestartGroup, 54);
                startRestartGroup.startReplaceGroup(-103210706);
                ComposerKt.sourceInformation(startRestartGroup, "*199@9255L535");
                if (function24 != null) {
                }
                startRestartGroup.endReplaceGroup();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103192112, "CC(remember):NavigationBar.kt#9igjgp");
                rememberedValue = startRestartGroup.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                final MutableIntState mutableIntState2222222 = (MutableIntState) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Modifier modifier42222222 = modifier2;
                boolean z102222222 = z4;
                ComposableLambda composableLambda22222222 = rememberComposableLambda;
                Modifier weight$default2222222 = RowScope.CC.weight$default(rowScope, SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier42222222, z, mutableInteractionSource52222222, null, z102222222, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationBarHeight, 1, null), 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103177977, "CC(remember):NavigationBar.kt#9igjgp");
                rememberedValue2 = startRestartGroup.rememberedValue();
                if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Modifier onSizeChanged2222222 = OnRemeasuredModifierKt.onSizeChanged(weight$default2222222, (Function1) rememberedValue2);
                Alignment center2222222 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy2222222 = BoxKt.maybeCachedBoxMeasurePolicy(center2222222, true);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2222222 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier2222222 = ComposedModifierKt.materializeModifier(startRestartGroup, onSizeChanged2222222);
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
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1652172306, "C233@10442L157,*242@10883L7,248@11165L128,255@11508L293,263@11850L395,280@12505L27,274@12255L288:NavigationBar.kt#uh7d8r");
                boolean z112222222 = z5;
                MutableInteractionSource mutableInteractionSource62222222 = mutableInteractionSource2;
                final State<Float> animateFloatAsState2222222 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
                ProvidableCompositionLocal<Density> localDensity2222222 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume2222222 = startRestartGroup.consume(localDensity2222222);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                long Offset2222222 = OffsetKt.Offset((NavigationBarItem$lambda$3(mutableIntState2222222) - r2.mo453roundToPx0680j_4(NavigationBarTokens.INSTANCE.m3504getActiveIndicatorWidthD9Ej5fM())) / 2, ((Density) consume2222222).mo459toPx0680j_4(IndicatorVerticalOffset));
                Unit unit2222222 = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024936814, "CC(remember):NavigationBar.kt#9igjgp");
                changed = startRestartGroup.changed(mutableInteractionSource52222222) | startRestartGroup.changed(Offset2222222);
                rememberedValue3 = startRestartGroup.rememberedValue();
                if (!changed) {
                }
                rememberedValue3 = new MappedInteractionSource(mutableInteractionSource52222222, Offset2222222, null);
                startRestartGroup.updateRememberedValue(rememberedValue3);
                final MappedInteractionSource mappedInteractionSource2222222 = (MappedInteractionSource) rememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposableLambda rememberComposableLambda32222222 = ComposableLambdaKt.rememberComposableLambda(691730997, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i13) {
                        ComposerKt.sourceInformation(composer3, "C258@11668L5,259@11736L32,256@11526L261:NavigationBar.kt#uh7d8r");
                        if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(691730997, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:256)");
                            }
                            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), MappedInteractionSource.this, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer3, 0, 7)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, startRestartGroup, 54);
                ComposableLambda rememberComposableLambda42222222 = ComposableLambdaKt.rememberComposableLambda(-474426875, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i13) {
                        ComposerKt.sourceInformation(composer3, "C266@11972L35,269@12181L5,264@11868L363:NavigationBar.kt#uh7d8r");
                        if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-474426875, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:264)");
                            }
                            Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                            ComposerKt.sourceInformationMarkerStart(composer3, 1335768816, "CC(remember):NavigationBar.kt#9igjgp");
                            boolean changed3 = composer3.changed(animateFloatAsState2222222);
                            final State<Float> state = animateFloatAsState2222222;
                            Object rememberedValue6 = composer3.rememberedValue();
                            if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1$1$1
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
                                composer3.updateRememberedValue(rememberedValue6);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue6), navigationBarItemColors42222222.getSelectedIndicatorColor(), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, startRestartGroup, 54);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024979593, "CC(remember):NavigationBar.kt#9igjgp");
                changed2 = startRestartGroup.changed(animateFloatAsState2222222);
                rememberedValue4 = startRestartGroup.rememberedValue();
                if (!changed2) {
                }
                rememberedValue4 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$2$1
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
                startRestartGroup.updateRememberedValue(rememberedValue4);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                NavigationBarItemLayout(rememberComposableLambda32222222, rememberComposableLambda42222222, composableLambda, composableLambda22222222, z112222222, (Function0) rememberedValue4, startRestartGroup, ((i11 >> 9) & 57344) | 438);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                }
                mutableInteractionSource4 = mutableInteractionSource62222222;
                navigationBarItemColors3 = navigationBarItemColors42222222;
                z6 = z112222222;
                composer2 = startRestartGroup;
                z7 = z102222222;
                modifier3 = modifier42222222;
                final Function2<? super Composer, ? super Integer, Unit> function272222 = function24;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            function23 = function2;
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
            function24 = function22;
            i7 = i2 & 64;
            if (i7 == 0) {
            }
            if ((i & 100663296) == 0) {
            }
            i8 = i2 & 256;
            if (i8 == 0) {
            }
            i9 = i8;
            if ((i3 & 306783379) == 306783378) {
            }
            startRestartGroup.startDefaults();
            i10 = i3;
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
            i11 = i10;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.startReplaceGroup(-103236853);
            ComposerKt.sourceInformation(startRestartGroup, "182@8445L39");
            if (mutableInteractionSource2 != null) {
            }
            startRestartGroup.endReplaceGroup();
            final NavigationBarItemColors navigationBarItemColors422222222 = navigationBarItemColors2;
            final Function2<? super Composer, ? super Integer, Unit> function2522222222 = function23;
            final boolean z822222222 = z5;
            final boolean z922222222 = z4;
            final Function2<? super Composer, ? super Integer, Unit> function2622222222 = function24;
            MutableInteractionSource mutableInteractionSource522222222 = mutableInteractionSource3;
            ComposableLambda rememberComposableLambda222222222 = ComposableLambdaKt.rememberComposableLambda(-1419576100, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1
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

                public final void invoke(Composer composer3, int i13) {
                    ComposerKt.sourceInformation(composer3, "C186@8573L201,192@8956L193:NavigationBar.kt#uh7d8r");
                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1419576100, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous> (NavigationBar.kt:185)");
                        }
                        State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationBarItemColors.this.m2425iconColorWaAFU9c$material3_release(z, z922222222), AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer3, 48, 12);
                        Modifier.Companion clearAndSetSemantics = (function2622222222 == null || !(z822222222 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1.1
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }
                        });
                        Function2<Composer, Integer, Unit> function2722222 = function2522222222;
                        ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy22222222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                        CompositionLocalMap currentCompositionLocalMap22222222 = composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier22222222 = ComposedModifierKt.materializeModifier(composer3, clearAndSetSemantics);
                        Function0<ComposeUiNode> constructor22222222 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                            composer3.createNode(constructor22222222);
                        } else {
                            composer3.useNode();
                        }
                        Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer3);
                        Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy22222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap22222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash22222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                            m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                            m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash22222222);
                        }
                        Updater.m3912setimpl(m3905constructorimpl2, materializeModifier22222222, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance22222222 = BoxScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer3, -1543730008, "C193@9057L78:NavigationBar.kt#uh7d8r");
                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function2722222, composer3, ProvidedValue.$stable);
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

                private static final long invoke$lambda$0(State<Color> state) {
                    return state.getValue().m4625unboximpl();
                }
            }, startRestartGroup, 54);
            startRestartGroup.startReplaceGroup(-103210706);
            ComposerKt.sourceInformation(startRestartGroup, "*199@9255L535");
            if (function24 != null) {
            }
            startRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103192112, "CC(remember):NavigationBar.kt#9igjgp");
            rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            }
            final MutableIntState mutableIntState22222222 = (MutableIntState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier modifier422222222 = modifier2;
            boolean z1022222222 = z4;
            ComposableLambda composableLambda222222222 = rememberComposableLambda;
            Modifier weight$default22222222 = RowScope.CC.weight$default(rowScope, SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier422222222, z, mutableInteractionSource522222222, null, z1022222222, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationBarHeight, 1, null), 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103177977, "CC(remember):NavigationBar.kt#9igjgp");
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier onSizeChanged22222222 = OnRemeasuredModifierKt.onSizeChanged(weight$default22222222, (Function1) rememberedValue2);
            Alignment center22222222 = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy22222222 = BoxKt.maybeCachedBoxMeasurePolicy(center22222222, true);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap22222222 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier22222222 = ComposedModifierKt.materializeModifier(startRestartGroup, onSizeChanged22222222);
            Function0<ComposeUiNode> constructor22222222 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
            }
            startRestartGroup.startReusableNode();
            if (!startRestartGroup.getInserting()) {
            }
            m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
            Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy22222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap22222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash22222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!m3905constructorimpl.getInserting()) {
            }
            m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash22222222);
            Updater.m3912setimpl(m3905constructorimpl, materializeModifier22222222, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance22222222 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1652172306, "C233@10442L157,*242@10883L7,248@11165L128,255@11508L293,263@11850L395,280@12505L27,274@12255L288:NavigationBar.kt#uh7d8r");
            boolean z1122222222 = z5;
            MutableInteractionSource mutableInteractionSource622222222 = mutableInteractionSource2;
            final State<Float> animateFloatAsState22222222 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
            ProvidableCompositionLocal<Density> localDensity22222222 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume22222222 = startRestartGroup.consume(localDensity22222222);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            long Offset22222222 = OffsetKt.Offset((NavigationBarItem$lambda$3(mutableIntState22222222) - r2.mo453roundToPx0680j_4(NavigationBarTokens.INSTANCE.m3504getActiveIndicatorWidthD9Ej5fM())) / 2, ((Density) consume22222222).mo459toPx0680j_4(IndicatorVerticalOffset));
            Unit unit22222222 = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024936814, "CC(remember):NavigationBar.kt#9igjgp");
            changed = startRestartGroup.changed(mutableInteractionSource522222222) | startRestartGroup.changed(Offset22222222);
            rememberedValue3 = startRestartGroup.rememberedValue();
            if (!changed) {
            }
            rememberedValue3 = new MappedInteractionSource(mutableInteractionSource522222222, Offset22222222, null);
            startRestartGroup.updateRememberedValue(rememberedValue3);
            final MappedInteractionSource mappedInteractionSource22222222 = (MappedInteractionSource) rememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposableLambda rememberComposableLambda322222222 = ComposableLambdaKt.rememberComposableLambda(691730997, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i13) {
                    ComposerKt.sourceInformation(composer3, "C258@11668L5,259@11736L32,256@11526L261:NavigationBar.kt#uh7d8r");
                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(691730997, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:256)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), MappedInteractionSource.this, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer3, 0, 7)), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, startRestartGroup, 54);
            ComposableLambda rememberComposableLambda422222222 = ComposableLambdaKt.rememberComposableLambda(-474426875, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i13) {
                    ComposerKt.sourceInformation(composer3, "C266@11972L35,269@12181L5,264@11868L363:NavigationBar.kt#uh7d8r");
                    if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-474426875, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:264)");
                        }
                        Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                        ComposerKt.sourceInformationMarkerStart(composer3, 1335768816, "CC(remember):NavigationBar.kt#9igjgp");
                        boolean changed3 = composer3.changed(animateFloatAsState22222222);
                        final State<Float> state = animateFloatAsState22222222;
                        Object rememberedValue6 = composer3.rememberedValue();
                        if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1$1$1
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
                            composer3.updateRememberedValue(rememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue6), navigationBarItemColors422222222.getSelectedIndicatorColor(), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, startRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024979593, "CC(remember):NavigationBar.kt#9igjgp");
            changed2 = startRestartGroup.changed(animateFloatAsState22222222);
            rememberedValue4 = startRestartGroup.rememberedValue();
            if (!changed2) {
            }
            rememberedValue4 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return animateFloatAsState22222222.getValue();
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue4);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            NavigationBarItemLayout(rememberComposableLambda322222222, rememberComposableLambda422222222, composableLambda, composableLambda222222222, z1122222222, (Function0) rememberedValue4, startRestartGroup, ((i11 >> 9) & 57344) | 438);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
            }
            mutableInteractionSource4 = mutableInteractionSource622222222;
            navigationBarItemColors3 = navigationBarItemColors422222222;
            z6 = z1122222222;
            composer2 = startRestartGroup;
            z7 = z1022222222;
            modifier3 = modifier422222222;
            final Function2<? super Composer, ? super Integer, Unit> function2722222 = function24;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        if ((i2 & 4) == 0) {
        }
        function23 = function2;
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
        function24 = function22;
        i7 = i2 & 64;
        if (i7 == 0) {
        }
        if ((i & 100663296) == 0) {
        }
        i8 = i2 & 256;
        if (i8 == 0) {
        }
        i9 = i8;
        if ((i3 & 306783379) == 306783378) {
        }
        startRestartGroup.startDefaults();
        i10 = i3;
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
        i11 = i10;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.startReplaceGroup(-103236853);
        ComposerKt.sourceInformation(startRestartGroup, "182@8445L39");
        if (mutableInteractionSource2 != null) {
        }
        startRestartGroup.endReplaceGroup();
        final NavigationBarItemColors navigationBarItemColors4222222222 = navigationBarItemColors2;
        final Function2<? super Composer, ? super Integer, Unit> function25222222222 = function23;
        final boolean z8222222222 = z5;
        final boolean z9222222222 = z4;
        final Function2<? super Composer, ? super Integer, Unit> function26222222222 = function24;
        MutableInteractionSource mutableInteractionSource5222222222 = mutableInteractionSource3;
        ComposableLambda rememberComposableLambda2222222222 = ComposableLambdaKt.rememberComposableLambda(-1419576100, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1
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

            public final void invoke(Composer composer3, int i13) {
                ComposerKt.sourceInformation(composer3, "C186@8573L201,192@8956L193:NavigationBar.kt#uh7d8r");
                if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1419576100, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous> (NavigationBar.kt:185)");
                    }
                    State<Color> m158animateColorAsStateeuL9pac = SingleValueAnimationKt.m158animateColorAsStateeuL9pac(NavigationBarItemColors.this.m2425iconColorWaAFU9c$material3_release(z, z9222222222), AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, composer3, 48, 12);
                    Modifier.Companion clearAndSetSemantics = (function26222222222 == null || !(z8222222222 || z)) ? Modifier.INSTANCE : SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledIcon$1.1
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }
                    });
                    Function2<Composer, Integer, Unit> function27222222 = function25222222222;
                    ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy222222222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap222222222 = composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier222222222 = ComposedModifierKt.materializeModifier(composer3, clearAndSetSemantics);
                    Function0<ComposeUiNode> constructor222222222 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor222222222);
                    } else {
                        composer3.useNode();
                    }
                    Composer m3905constructorimpl2 = Updater.m3905constructorimpl(composer3);
                    Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash222222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                        m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash222222222);
                    }
                    Updater.m3912setimpl(m3905constructorimpl2, materializeModifier222222222, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance222222222 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composer3, -1543730008, "C193@9057L78:NavigationBar.kt#uh7d8r");
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4605boximpl(invoke$lambda$0(m158animateColorAsStateeuL9pac))), function27222222, composer3, ProvidedValue.$stable);
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

            private static final long invoke$lambda$0(State<Color> state) {
                return state.getValue().m4625unboximpl();
            }
        }, startRestartGroup, 54);
        startRestartGroup.startReplaceGroup(-103210706);
        ComposerKt.sourceInformation(startRestartGroup, "*199@9255L535");
        if (function24 != null) {
        }
        startRestartGroup.endReplaceGroup();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103192112, "CC(remember):NavigationBar.kt#9igjgp");
        rememberedValue = startRestartGroup.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
        }
        final MutableIntState mutableIntState222222222 = (MutableIntState) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Modifier modifier4222222222 = modifier2;
        boolean z10222222222 = z4;
        ComposableLambda composableLambda2222222222 = rememberComposableLambda;
        Modifier weight$default222222222 = RowScope.CC.weight$default(rowScope, SizeKt.m915defaultMinSizeVpY3zN4$default(SelectableKt.m1205selectableO2vRcR0(modifier4222222222, z, mutableInteractionSource5222222222, null, z10222222222, Role.m6589boximpl(Role.INSTANCE.m6603getTabo7Vup1c()), function0), 0.0f, NavigationBarHeight, 1, null), 1.0f, false, 2, null);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -103177977, "CC(remember):NavigationBar.kt#9igjgp");
        rememberedValue2 = startRestartGroup.rememberedValue();
        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
        }
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Modifier onSizeChanged222222222 = OnRemeasuredModifierKt.onSizeChanged(weight$default222222222, (Function1) rememberedValue2);
        Alignment center222222222 = Alignment.INSTANCE.getCenter();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
        MeasurePolicy maybeCachedBoxMeasurePolicy222222222 = BoxKt.maybeCachedBoxMeasurePolicy(center222222222, true);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
        CompositionLocalMap currentCompositionLocalMap222222222 = startRestartGroup.getCurrentCompositionLocalMap();
        Modifier materializeModifier222222222 = ComposedModifierKt.materializeModifier(startRestartGroup, onSizeChanged222222222);
        Function0<ComposeUiNode> constructor222222222 = ComposeUiNode.INSTANCE.getConstructor();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
        if (!(startRestartGroup.getApplier() instanceof Applier)) {
        }
        startRestartGroup.startReusableNode();
        if (!startRestartGroup.getInserting()) {
        }
        m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
        Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash222222222 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if (!m3905constructorimpl.getInserting()) {
        }
        m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
        m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash222222222);
        Updater.m3912setimpl(m3905constructorimpl, materializeModifier222222222, ComposeUiNode.INSTANCE.getSetModifier());
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
        BoxScopeInstance boxScopeInstance222222222 = BoxScopeInstance.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1652172306, "C233@10442L157,*242@10883L7,248@11165L128,255@11508L293,263@11850L395,280@12505L27,274@12255L288:NavigationBar.kt#uh7d8r");
        boolean z11222222222 = z5;
        MutableInteractionSource mutableInteractionSource6222222222 = mutableInteractionSource2;
        final State<Float> animateFloatAsState222222222 = AnimateAsStateKt.animateFloatAsState(!z ? 1.0f : 0.0f, AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, null, null, startRestartGroup, 48, 28);
        ProvidableCompositionLocal<Density> localDensity222222222 = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume222222222 = startRestartGroup.consume(localDensity222222222);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        long Offset222222222 = OffsetKt.Offset((NavigationBarItem$lambda$3(mutableIntState222222222) - r2.mo453roundToPx0680j_4(NavigationBarTokens.INSTANCE.m3504getActiveIndicatorWidthD9Ej5fM())) / 2, ((Density) consume222222222).mo459toPx0680j_4(IndicatorVerticalOffset));
        Unit unit222222222 = Unit.INSTANCE;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024936814, "CC(remember):NavigationBar.kt#9igjgp");
        changed = startRestartGroup.changed(mutableInteractionSource5222222222) | startRestartGroup.changed(Offset222222222);
        rememberedValue3 = startRestartGroup.rememberedValue();
        if (!changed) {
        }
        rememberedValue3 = new MappedInteractionSource(mutableInteractionSource5222222222, Offset222222222, null);
        startRestartGroup.updateRememberedValue(rememberedValue3);
        final MappedInteractionSource mappedInteractionSource222222222 = (MappedInteractionSource) rememberedValue3;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposableLambda rememberComposableLambda3222222222 = ComposableLambdaKt.rememberComposableLambda(691730997, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                invoke(composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer3, int i13) {
                ComposerKt.sourceInformation(composer3, "C258@11668L5,259@11736L32,256@11526L261:NavigationBar.kt#uh7d8r");
                if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(691730997, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:256)");
                    }
                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), MappedInteractionSource.this, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer3, 0, 7)), composer3, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer3.skipToGroupEnd();
            }
        }, startRestartGroup, 54);
        ComposableLambda rememberComposableLambda4222222222 = ComposableLambdaKt.rememberComposableLambda(-474426875, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                invoke(composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer3, int i13) {
                ComposerKt.sourceInformation(composer3, "C266@11972L35,269@12181L5,264@11868L363:NavigationBar.kt#uh7d8r");
                if ((i13 & 3) != 2 || !composer3.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-474426875, i13, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:264)");
                    }
                    Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
                    ComposerKt.sourceInformationMarkerStart(composer3, 1335768816, "CC(remember):NavigationBar.kt#9igjgp");
                    boolean changed3 = composer3.changed(animateFloatAsState222222222);
                    final State<Float> state = animateFloatAsState222222222;
                    Object rememberedValue6 = composer3.rememberedValue();
                    if (changed3 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicator$1$1$1
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
                        composer3.updateRememberedValue(rememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    BoxKt.Box(BackgroundKt.m296backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(layoutId, (Function1) rememberedValue6), navigationBarItemColors4222222222.getSelectedIndicatorColor(), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer3, 6)), composer3, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer3.skipToGroupEnd();
            }
        }, startRestartGroup, 54);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2024979593, "CC(remember):NavigationBar.kt#9igjgp");
        changed2 = startRestartGroup.changed(animateFloatAsState222222222);
        rememberedValue4 = startRestartGroup.rememberedValue();
        if (!changed2) {
        }
        rememberedValue4 = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return animateFloatAsState222222222.getValue();
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue4);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        NavigationBarItemLayout(rememberComposableLambda3222222222, rememberComposableLambda4222222222, composableLambda, composableLambda2222222222, z11222222222, (Function0) rememberedValue4, startRestartGroup, ((i11 >> 9) & 57344) | 438);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        startRestartGroup.endNode();
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        if (ComposerKt.isTraceInProgress()) {
        }
        mutableInteractionSource4 = mutableInteractionSource6222222222;
        navigationBarItemColors3 = navigationBarItemColors4222222222;
        z6 = z11222222222;
        composer2 = startRestartGroup;
        z7 = z10222222222;
        modifier3 = modifier4222222222;
        final Function2<? super Composer, ? super Integer, Unit> function27222222 = function24;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    private static final int NavigationBarItem$lambda$3(MutableIntState mutableIntState) {
        return mutableIntState.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void NavigationBarItemLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, final boolean z, final Function0<Float> function0, Composer composer, final int i) {
        int i2;
        final Function2<? super Composer, ? super Integer, Unit> function25 = function24;
        Composer startRestartGroup = composer.startRestartGroup(-1427075886);
        ComposerKt.sourceInformation(startRestartGroup, "C(NavigationBarItemLayout)P(4,3,2,5)531@23350L1717,516@22954L2113:NavigationBar.kt#uh7d8r");
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
                ComposerKt.traceEventStart(-1427075886, i2, -1, "androidx.compose.material3.NavigationBarItemLayout (NavigationBar.kt:515)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -96243506, "CC(remember):NavigationBar.kt#9igjgp");
            int i3 = 458752 & i2;
            int i4 = 57344 & i2;
            boolean z2 = (i3 == 131072) | ((i2 & 7168) == 2048) | (i4 == 16384);
            MeasurePolicy rememberedValue = startRestartGroup.rememberedValue();
            if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItemLayout$2$1
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
                        MeasureResult m2433placeLabelAndIconzUg2_y0;
                        MeasureResult m2432placeIconX9ElhV4;
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
                                f = NavigationBarKt.IndicatorHorizontalPadding;
                                float f2 = 2;
                                int i6 = width + measureScope2.mo453roundToPx0680j_4(Dp.m7399constructorimpl(f * f2));
                                int roundToInt = MathKt.roundToInt(i6 * floatValue);
                                int height = mo6055measureBRTryo0.getHeight() + measureScope2.mo453roundToPx0680j_4(Dp.m7399constructorimpl(NavigationBarKt.getIndicatorVerticalPadding() * f2));
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
                                            m2432placeIconX9ElhV4 = NavigationBarKt.m2432placeIconX9ElhV4(measureScope2, mo6055measureBRTryo0, mo6055measureBRTryo02, mo6055measureBRTryo03, j);
                                            return m2432placeIconX9ElhV4;
                                        }
                                        Intrinsics.checkNotNull(placeable);
                                        m2433placeLabelAndIconzUg2_y0 = NavigationBarKt.m2433placeLabelAndIconzUg2_y0(measureScope, placeable, mo6055measureBRTryo0, mo6055measureBRTryo02, mo6055measureBRTryo03, j, z, floatValue);
                                        return m2433placeLabelAndIconzUg2_y0;
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -656747336, "C517@22971L17,518@22997L11,520@23018L50:NavigationBar.kt#uh7d8r");
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2034269370, "C520@23060L6:NavigationBar.kt#uh7d8r");
            function23.invoke(startRestartGroup, Integer.valueOf((i5 >> 6) & 14));
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.startReplaceGroup(1087196576);
            ComposerKt.sourceInformation(startRestartGroup, "524@23182L96,523@23111L221");
            if (function24 != null) {
                Modifier layoutId2 = LayoutIdKt.layoutId(Modifier.INSTANCE, LabelLayoutIdTag);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1087199736, "CC(remember):NavigationBar.kt#9igjgp");
                boolean z3 = (i4 == 16384) | (i3 == 131072);
                Object rememberedValue2 = startRestartGroup.rememberedValue();
                if (z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue2 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItemLayout$1$2$1
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
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2034020347, "C528@23311L7:NavigationBar.kt#uh7d8r");
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
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItemLayout$3
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
                    NavigationBarKt.NavigationBarItemLayout(function2, function22, function23, function25, z, function0, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: placeIcon-X9ElhV4, reason: not valid java name */
    public static final MeasureResult m2432placeIconX9ElhV4(MeasureScope measureScope, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, long j) {
        final int m7342getMaxWidthimpl = Constraints.m7342getMaxWidthimpl(j);
        final int m7358constrainHeightK40F9xA = ConstraintsKt.m7358constrainHeightK40F9xA(j, measureScope.mo453roundToPx0680j_4(NavigationBarHeight));
        final int width = (m7342getMaxWidthimpl - placeable.getWidth()) / 2;
        final int height = (m7358constrainHeightK40F9xA - placeable.getHeight()) / 2;
        final int width2 = (m7342getMaxWidthimpl - placeable2.getWidth()) / 2;
        final int height2 = (m7358constrainHeightK40F9xA - placeable2.getHeight()) / 2;
        return MeasureScope.CC.layout$default(measureScope, m7342getMaxWidthimpl, m7358constrainHeightK40F9xA, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$placeIcon$1
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
                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, (m7342getMaxWidthimpl - placeable4.getWidth()) / 2, (m7358constrainHeightK40F9xA - placeable4.getHeight()) / 2, 0.0f, 4, null);
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
    public static final MeasureResult m2433placeLabelAndIconzUg2_y0(final MeasureScope measureScope, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, final Placeable placeable4, long j, final boolean z, final float f) {
        float height = placeable2.getHeight();
        float f2 = IndicatorVerticalPadding;
        float f3 = height + measureScope.mo459toPx0680j_4(f2);
        float f4 = NavigationBarIndicatorToLabelPadding;
        float f5 = f3 + measureScope.mo459toPx0680j_4(f4) + placeable.getHeight();
        float f6 = 2;
        final float coerceAtLeast = RangesKt.coerceAtLeast((Constraints.m7343getMinHeightimpl(j) - f5) / f6, measureScope.mo459toPx0680j_4(f2));
        float f7 = f5 + (coerceAtLeast * f6);
        final float height2 = ((z ? coerceAtLeast : (f7 - placeable2.getHeight()) / f6) - coerceAtLeast) * (1 - f);
        final float height3 = placeable2.getHeight() + coerceAtLeast + measureScope.mo459toPx0680j_4(f2) + measureScope.mo459toPx0680j_4(f4);
        final int m7342getMaxWidthimpl = Constraints.m7342getMaxWidthimpl(j);
        final int width = (m7342getMaxWidthimpl - placeable.getWidth()) / 2;
        final int width2 = (m7342getMaxWidthimpl - placeable2.getWidth()) / 2;
        final int width3 = (m7342getMaxWidthimpl - placeable3.getWidth()) / 2;
        final float f8 = coerceAtLeast - measureScope.mo459toPx0680j_4(f2);
        return MeasureScope.CC.layout$default(measureScope, m7342getMaxWidthimpl, MathKt.roundToInt(f7), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$placeLabelAndIcon$1
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
                Placeable placeable5 = Placeable.this;
                if (placeable5 != null) {
                    int i = m7342getMaxWidthimpl;
                    float f9 = coerceAtLeast;
                    MeasureScope measureScope2 = measureScope;
                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, (i - placeable5.getWidth()) / 2, MathKt.roundToInt((f9 - measureScope2.mo453roundToPx0680j_4(NavigationBarKt.getIndicatorVerticalPadding())) + height2), 0.0f, 4, null);
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

    static {
        float f = 2;
        IndicatorHorizontalPadding = Dp.m7399constructorimpl(Dp.m7399constructorimpl(NavigationBarTokens.INSTANCE.m3504getActiveIndicatorWidthD9Ej5fM() - NavigationBarTokens.INSTANCE.m3507getIconSizeD9Ej5fM()) / f);
        IndicatorVerticalPadding = Dp.m7399constructorimpl(Dp.m7399constructorimpl(NavigationBarTokens.INSTANCE.m3503getActiveIndicatorHeightD9Ej5fM() - NavigationBarTokens.INSTANCE.m3507getIconSizeD9Ej5fM()) / f);
    }

    public static final float getNavigationBarItemHorizontalPadding() {
        return NavigationBarItemHorizontalPadding;
    }

    public static final float getNavigationBarIndicatorToLabelPadding() {
        return NavigationBarIndicatorToLabelPadding;
    }

    public static final float getIndicatorVerticalPadding() {
        return IndicatorVerticalPadding;
    }
}
