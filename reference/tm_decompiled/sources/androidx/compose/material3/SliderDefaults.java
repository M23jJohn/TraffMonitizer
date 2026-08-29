package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.tokens.SliderTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.RotateKt;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PointMode;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Slider.kt */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JB\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ3\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\u0010!J\u0096\u0001\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u0017\u001a\u00020\r2!\b\u0002\u0010\"\u001a\u001b\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0012\u0018\u00010#¢\u0006\u0002\b&2%\b\u0002\u0010'\u001a\u001f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00120(¢\u0006\u0002\b&2\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010-J3\u0010\u001e\u001a\u00020\u00122\u0006\u0010.\u001a\u00020/2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\u00100J3\u0010\u001e\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\u00103J\u0096\u0001\u0010\u001e\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u0017\u001a\u00020\r2!\b\u0002\u0010\"\u001a\u001b\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0012\u0018\u00010#¢\u0006\u0002\b&2%\b\u0002\u0010'\u001a\u001f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00120(¢\u0006\u0002\b&2\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u00104J\r\u0010\u0017\u001a\u00020\rH\u0007¢\u0006\u0002\u00105Jv\u0010\u0017\u001a\u00020\r2\b\b\u0002\u00106\u001a\u00020)2\b\b\u0002\u00107\u001a\u00020)2\b\b\u0002\u00108\u001a\u00020)2\b\b\u0002\u00109\u001a\u00020)2\b\b\u0002\u0010:\u001a\u00020)2\b\b\u0002\u0010;\u001a\u00020)2\b\b\u0002\u0010<\u001a\u00020)2\b\b\u0002\u0010=\u001a\u00020)2\b\b\u0002\u0010>\u001a\u00020)2\b\b\u0002\u0010?\u001a\u00020)H\u0007ø\u0001\u0000¢\u0006\u0004\b@\u0010AJ2\u0010\"\u001a\u00020\u00122\u0006\u0010B\u001a\u00020$2\u0006\u0010C\u001a\u00020%2\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020)H\u0002ø\u0001\u0000¢\u0006\u0004\bF\u0010GJÄ\u0001\u0010H\u001a\u00020\u0012*\u00020$2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020L2\u0006\u00109\u001a\u00020)2\u0006\u00107\u001a\u00020)2\u0006\u0010:\u001a\u00020)2\u0006\u00108\u001a\u00020)2\u0006\u0010N\u001a\u00020\u00042\u0006\u0010O\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u001f\u0010\"\u001a\u001b\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0012\u0018\u00010#¢\u0006\u0002\b&2#\u0010'\u001a\u001f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00120(¢\u0006\u0002\b&2\u0006\u0010Q\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\bR\u0010SJ>\u0010T\u001a\u00020\u0012*\u00020$2\u0006\u0010C\u001a\u00020%2\u0006\u0010D\u001a\u00020U2\u0006\u0010E\u001a\u00020)2\u0006\u0010V\u001a\u00020L2\u0006\u0010W\u001a\u00020LH\u0002ø\u0001\u0000¢\u0006\u0004\bX\u0010YR\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\u00020\r*\u00020\u000e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Z"}, d2 = {"Landroidx/compose/material3/SliderDefaults;", "", "()V", "TickSize", "Landroidx/compose/ui/unit/Dp;", "getTickSize-D9Ej5fM", "()F", "F", "TrackStopIndicatorSize", "getTrackStopIndicatorSize-D9Ej5fM", "trackPath", "Landroidx/compose/ui/graphics/Path;", "defaultSliderColors", "Landroidx/compose/material3/SliderColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultSliderColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SliderColors;", "Thumb", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "modifier", "Landroidx/compose/ui/Modifier;", "colors", "enabled", "", "thumbSize", "Landroidx/compose/ui/unit/DpSize;", "Thumb-9LiSoMs", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZJLandroidx/compose/runtime/Composer;II)V", "Track", "rangeSliderState", "Landroidx/compose/material3/RangeSliderState;", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "drawStopIndicator", "Lkotlin/Function2;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ExtensionFunctionType;", "drawTick", "Lkotlin/Function3;", "Landroidx/compose/ui/graphics/Color;", "thumbTrackGapSize", "trackInsideCornerSize", "Track-4EFweAY", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "sliderPositions", "Landroidx/compose/material3/SliderPositions;", "(Landroidx/compose/material3/SliderPositions;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "sliderState", "Landroidx/compose/material3/SliderState;", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderColors;ZLandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SliderColors;", "thumbColor", "activeTrackColor", "activeTickColor", "inactiveTrackColor", "inactiveTickColor", "disabledThumbColor", "disabledActiveTrackColor", "disabledActiveTickColor", "disabledInactiveTrackColor", "disabledInactiveTickColor", "colors-q0g_0yA", "(JJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SliderColors;", "drawScope", "offset", "size", "color", "drawStopIndicator-x3O1jOs", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFJ)V", "drawTrack", "tickFractions", "", "activeRangeStart", "", "activeRangeEnd", "height", "startThumbWidth", "endThumbWidth", "isRangeSlider", "drawTrack-ngJ0SCU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;[FFFJJJJFFFFFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Z)V", "drawTrackPath", "Landroidx/compose/ui/geometry/Size;", "startCornerRadius", "endCornerRadius", "drawTrackPath-Cx2C_VA", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJJFF)V", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SliderDefaults {
    public static final int $stable = 0;
    public static final SliderDefaults INSTANCE = new SliderDefaults();
    private static final float TrackStopIndicatorSize = SliderTokens.INSTANCE.m3684getStopIndicatorSizeD9Ej5fM();
    private static final float TickSize = SliderTokens.INSTANCE.m3684getStopIndicatorSizeD9Ej5fM();
    private static final Path trackPath = AndroidPath_androidKt.Path();

    private SliderDefaults() {
    }

    public final SliderColors colors(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1376295968, "C(colors)845@36907L11:Slider.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1376295968, i, -1, "androidx.compose.material3.SliderDefaults.colors (Slider.kt:845)");
        }
        SliderColors defaultSliderColors$material3_release = getDefaultSliderColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultSliderColors$material3_release;
    }

    /* renamed from: colors-q0g_0yA, reason: not valid java name */
    public final SliderColors m2651colorsq0g_0yA(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, Composer composer, int i, int i2, int i3) {
        ComposerKt.sourceInformationMarkerStart(composer, 885588574, "C(colors)P(9:c#ui.graphics.Color,1:c#ui.graphics.Color,0:c#ui.graphics.Color,8:c#ui.graphics.Color,7:c#ui.graphics.Color,6:c#ui.graphics.Color,3:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.graphics.Color,4:c#ui.graphics.Color)887@39230L11:Slider.kt#uh7d8r");
        long m4651getUnspecified0d7_KjU = (i3 & 1) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j;
        long m4651getUnspecified0d7_KjU2 = (i3 & 2) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j2;
        long m4651getUnspecified0d7_KjU3 = (i3 & 4) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j3;
        long m4651getUnspecified0d7_KjU4 = (i3 & 8) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j4;
        long m4651getUnspecified0d7_KjU5 = (i3 & 16) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j5;
        long m4651getUnspecified0d7_KjU6 = (i3 & 32) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j6;
        long m4651getUnspecified0d7_KjU7 = (i3 & 64) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j7;
        long m4651getUnspecified0d7_KjU8 = (i3 & 128) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j8;
        long m4651getUnspecified0d7_KjU9 = (i3 & 256) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j9;
        long m4651getUnspecified0d7_KjU10 = (i3 & 512) != 0 ? Color.INSTANCE.m4651getUnspecified0d7_KjU() : j10;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(885588574, i, i2, "androidx.compose.material3.SliderDefaults.colors (Slider.kt:887)");
        }
        SliderColors m2629copyK518z4 = getDefaultSliderColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m2629copyK518z4(m4651getUnspecified0d7_KjU, m4651getUnspecified0d7_KjU2, m4651getUnspecified0d7_KjU3, m4651getUnspecified0d7_KjU4, m4651getUnspecified0d7_KjU5, m4651getUnspecified0d7_KjU6, m4651getUnspecified0d7_KjU7, m4651getUnspecified0d7_KjU8, m4651getUnspecified0d7_KjU9, m4651getUnspecified0d7_KjU10);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return m2629copyK518z4;
    }

    public final SliderColors getDefaultSliderColors$material3_release(ColorScheme colorScheme) {
        SliderColors defaultSliderColorsCached = colorScheme.getDefaultSliderColorsCached();
        if (defaultSliderColorsCached != null) {
            return defaultSliderColorsCached;
        }
        SliderColors sliderColors = new SliderColors(ColorSchemeKt.fromToken(colorScheme, SliderTokens.INSTANCE.getHandleColor()), ColorSchemeKt.fromToken(colorScheme, SliderTokens.INSTANCE.getActiveTrackColor()), ColorSchemeKt.fromToken(colorScheme, SliderTokens.INSTANCE.getInactiveTrackColor()), ColorSchemeKt.fromToken(colorScheme, SliderTokens.INSTANCE.getInactiveTrackColor()), ColorSchemeKt.fromToken(colorScheme, SliderTokens.INSTANCE.getActiveTrackColor()), ColorKt.m4660compositeOverOWjLjI(Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, SliderTokens.INSTANCE.getDisabledHandleColor()), SliderTokens.INSTANCE.getDisabledHandleOpacity(), 0.0f, 0.0f, 0.0f, 14, null), colorScheme.getSurface()), Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, SliderTokens.INSTANCE.getDisabledActiveTrackColor()), SliderTokens.INSTANCE.getDisabledActiveTrackOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, SliderTokens.INSTANCE.getDisabledInactiveTrackColor()), SliderTokens.INSTANCE.getDisabledInactiveTrackOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, SliderTokens.INSTANCE.getDisabledInactiveTrackColor()), SliderTokens.INSTANCE.getDisabledInactiveTrackOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, SliderTokens.INSTANCE.getDisabledActiveTrackColor()), SliderTokens.INSTANCE.getDisabledActiveTrackOpacity(), 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultSliderColorsCached$material3_release(sliderColors);
        return sliderColors;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0069  */
    /* renamed from: Thumb-9LiSoMs, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m2648Thumb9LiSoMs(final MutableInteractionSource mutableInteractionSource, Modifier modifier, SliderColors sliderColors, boolean z, long j, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        SliderColors sliderColors2;
        int i4;
        boolean z2;
        int i5;
        long j2;
        long j3;
        long j4;
        Object rememberedValue;
        SnapshotStateList snapshotStateList;
        boolean z3;
        SliderDefaults$Thumb$1$1 rememberedValue2;
        int i6;
        long j5;
        final long j6;
        ScopeUpdateScope endRestartGroup;
        int i7;
        Composer startRestartGroup = composer.startRestartGroup(-290277409);
        ComposerKt.sourceInformation(startRestartGroup, "C(Thumb)P(2,3!,4:c#ui.unit.DpSize)947@42562L8,951@42678L46,952@42767L658,952@42733L692,975@43824L5,971@43620L220:Slider.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(mutableInteractionSource) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i8 = i2 & 2;
        if (i8 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i & 384) != 0) {
                if ((i2 & 4) == 0) {
                    sliderColors2 = sliderColors;
                    if (startRestartGroup.changed(sliderColors2)) {
                        i7 = 256;
                        i3 |= i7;
                    }
                } else {
                    sliderColors2 = sliderColors;
                }
                i7 = 128;
                i3 |= i7;
            } else {
                sliderColors2 = sliderColors;
            }
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                z2 = z;
                i3 |= startRestartGroup.changed(z2) ? 2048 : 1024;
                i5 = i2 & 16;
                if (i5 != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    j2 = j;
                    i3 |= startRestartGroup.changed(j2) ? 16384 : 8192;
                    if ((i2 & 32) == 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        i3 |= startRestartGroup.changed(this) ? 131072 : 65536;
                    }
                    if ((i3 & 74899) == 74898 || !startRestartGroup.getSkipping()) {
                        startRestartGroup.startDefaults();
                        if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                            if (i8 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i2 & 4) != 0) {
                                SliderColors colors = colors(startRestartGroup, (i3 >> 15) & 14);
                                i3 &= -897;
                                sliderColors2 = colors;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if (i5 != 0) {
                                j3 = SliderKt.ThumbSize;
                                j4 = j3;
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-290277409, i3, -1, "androidx.compose.material3.SliderDefaults.Thumb (Slider.kt:950)");
                                }
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1068636116, "CC(remember):Slider.kt#9igjgp");
                                rememberedValue = startRestartGroup.rememberedValue();
                                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue = SnapshotStateKt.mutableStateListOf();
                                    startRestartGroup.updateRememberedValue(rememberedValue);
                                }
                                snapshotStateList = (SnapshotStateList) rememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1068632656, "CC(remember):Slider.kt#9igjgp");
                                int i9 = i3 & 14;
                                z3 = i9 == 4;
                                rememberedValue2 = startRestartGroup.rememberedValue();
                                if (!z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                                    startRestartGroup.updateRememberedValue(rememberedValue2);
                                }
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue2, startRestartGroup, i9);
                                if (snapshotStateList.isEmpty()) {
                                    i6 = 2;
                                    j5 = j4;
                                } else {
                                    i6 = 2;
                                    j5 = DpSize.m7490copyDwJknco$default(j4, Dp.m7399constructorimpl(DpSize.m7497getWidthD9Ej5fM(j4) / 2), 0.0f, 2, null);
                                }
                                SpacerKt.Spacer(BackgroundKt.m296backgroundbw27NRU(HoverableKt.hoverable$default(SizeKt.m931size6HolHcs(modifier2, j5), mutableInteractionSource, false, i6, null), sliderColors2.m2640thumbColorvNxB06k$material3_release(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), startRestartGroup, 6)), startRestartGroup, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                j6 = j4;
                            }
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            if ((i2 & 4) != 0) {
                                i3 &= -897;
                            }
                        }
                        j4 = j2;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1068636116, "CC(remember):Slider.kt#9igjgp");
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                        }
                        snapshotStateList = (SnapshotStateList) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1068632656, "CC(remember):Slider.kt#9igjgp");
                        int i92 = i3 & 14;
                        if (i92 == 4) {
                        }
                        rememberedValue2 = startRestartGroup.rememberedValue();
                        if (!z3) {
                        }
                        rememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                        startRestartGroup.updateRememberedValue(rememberedValue2);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue2, startRestartGroup, i92);
                        if (snapshotStateList.isEmpty()) {
                        }
                        SpacerKt.Spacer(BackgroundKt.m296backgroundbw27NRU(HoverableKt.hoverable$default(SizeKt.m931size6HolHcs(modifier2, j5), mutableInteractionSource, false, i6, null), sliderColors2.m2640thumbColorvNxB06k$material3_release(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), startRestartGroup, 6)), startRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        j6 = j4;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        j6 = j2;
                    }
                    final SliderColors sliderColors3 = sliderColors2;
                    final boolean z4 = z2;
                    endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup == null) {
                        final Modifier modifier3 = modifier2;
                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Thumb$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i10) {
                                SliderDefaults.this.m2648Thumb9LiSoMs(mutableInteractionSource, modifier3, sliderColors3, z4, j6, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                        return;
                    }
                    return;
                }
                j2 = j;
                if ((i2 & 32) == 0) {
                }
                if ((i3 & 74899) == 74898) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i8 != 0) {
                }
                if ((i2 & 4) != 0) {
                }
                if (i4 != 0) {
                }
                if (i5 != 0) {
                }
                j4 = j2;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1068636116, "CC(remember):Slider.kt#9igjgp");
                rememberedValue = startRestartGroup.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                snapshotStateList = (SnapshotStateList) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1068632656, "CC(remember):Slider.kt#9igjgp");
                int i922 = i3 & 14;
                if (i922 == 4) {
                }
                rememberedValue2 = startRestartGroup.rememberedValue();
                if (!z3) {
                }
                rememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
                startRestartGroup.updateRememberedValue(rememberedValue2);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue2, startRestartGroup, i922);
                if (snapshotStateList.isEmpty()) {
                }
                SpacerKt.Spacer(BackgroundKt.m296backgroundbw27NRU(HoverableKt.hoverable$default(SizeKt.m931size6HolHcs(modifier2, j5), mutableInteractionSource, false, i6, null), sliderColors2.m2640thumbColorvNxB06k$material3_release(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), startRestartGroup, 6)), startRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                }
                j6 = j4;
                final SliderColors sliderColors32 = sliderColors2;
                final boolean z42 = z2;
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z2 = z;
            i5 = i2 & 16;
            if (i5 != 0) {
            }
            j2 = j;
            if ((i2 & 32) == 0) {
            }
            if ((i3 & 74899) == 74898) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i8 != 0) {
            }
            if ((i2 & 4) != 0) {
            }
            if (i4 != 0) {
            }
            if (i5 != 0) {
            }
            j4 = j2;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1068636116, "CC(remember):Slider.kt#9igjgp");
            rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            }
            snapshotStateList = (SnapshotStateList) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1068632656, "CC(remember):Slider.kt#9igjgp");
            int i9222 = i3 & 14;
            if (i9222 == 4) {
            }
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (!z3) {
            }
            rememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
            startRestartGroup.updateRememberedValue(rememberedValue2);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue2, startRestartGroup, i9222);
            if (snapshotStateList.isEmpty()) {
            }
            SpacerKt.Spacer(BackgroundKt.m296backgroundbw27NRU(HoverableKt.hoverable$default(SizeKt.m931size6HolHcs(modifier2, j5), mutableInteractionSource, false, i6, null), sliderColors2.m2640thumbColorvNxB06k$material3_release(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), startRestartGroup, 6)), startRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            j6 = j4;
            final SliderColors sliderColors322 = sliderColors2;
            final boolean z422 = z2;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        if ((i & 384) != 0) {
        }
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        z2 = z;
        i5 = i2 & 16;
        if (i5 != 0) {
        }
        j2 = j;
        if ((i2 & 32) == 0) {
        }
        if ((i3 & 74899) == 74898) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i8 != 0) {
        }
        if ((i2 & 4) != 0) {
        }
        if (i4 != 0) {
        }
        if (i5 != 0) {
        }
        j4 = j2;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1068636116, "CC(remember):Slider.kt#9igjgp");
        rememberedValue = startRestartGroup.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
        }
        snapshotStateList = (SnapshotStateList) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1068632656, "CC(remember):Slider.kt#9igjgp");
        int i92222 = i3 & 14;
        if (i92222 == 4) {
        }
        rememberedValue2 = startRestartGroup.rememberedValue();
        if (!z3) {
        }
        rememberedValue2 = new SliderDefaults$Thumb$1$1(mutableInteractionSource, snapshotStateList, null);
        startRestartGroup.updateRememberedValue(rememberedValue2);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue2, startRestartGroup, i92222);
        if (snapshotStateList.isEmpty()) {
        }
        SpacerKt.Spacer(BackgroundKt.m296backgroundbw27NRU(HoverableKt.hoverable$default(SizeKt.m931size6HolHcs(modifier2, j5), mutableInteractionSource, false, i6, null), sliderColors2.m2640thumbColorvNxB06k$material3_release(z2), ShapesKt.getValue(SliderTokens.INSTANCE.getHandleShape(), startRestartGroup, 6)), startRestartGroup, 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        j6 = j4;
        final SliderColors sliderColors3222 = sliderColors2;
        final boolean z4222 = z2;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0062  */
    @Deprecated(message = "Use version that supports slider state")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void Track(final SliderPositions sliderPositions, Modifier modifier, SliderColors sliderColors, boolean z, Composer composer, final int i, final int i2) {
        final SliderPositions sliderPositions2;
        int i3;
        Modifier modifier2;
        SliderColors sliderColors2;
        int i4;
        boolean z2;
        Modifier.Companion companion;
        int i5;
        SliderColors sliderColors3;
        boolean z3;
        boolean changed;
        Object rememberedValue;
        boolean z4;
        final SliderColors sliderColors4;
        final boolean z5;
        final Modifier modifier3;
        ScopeUpdateScope endRestartGroup;
        int i6;
        Composer startRestartGroup = composer.startRestartGroup(-1546713545);
        ComposerKt.sourceInformation(startRestartGroup, "C(Track)P(3,2)997@44755L8,1004@45160L1834,1004@45108L1886:Slider.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            sliderPositions2 = sliderPositions;
        } else {
            sliderPositions2 = sliderPositions;
            if ((i & 6) == 0) {
                i3 = (startRestartGroup.changed(sliderPositions2) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        int i7 = i2 & 2;
        if (i7 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i & 384) != 0) {
                if ((i2 & 4) == 0) {
                    sliderColors2 = sliderColors;
                    if (startRestartGroup.changed(sliderColors2)) {
                        i6 = 256;
                        i3 |= i6;
                    }
                } else {
                    sliderColors2 = sliderColors;
                }
                i6 = 128;
                i3 |= i6;
            } else {
                sliderColors2 = sliderColors;
            }
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                z2 = z;
                i3 |= startRestartGroup.changed(z2) ? 2048 : 1024;
                if ((i2 & 16) != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    i3 |= startRestartGroup.changed(this) ? 16384 : 8192;
                }
                if ((i3 & 9363) == 9362 || !startRestartGroup.getSkipping()) {
                    startRestartGroup.startDefaults();
                    if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                        companion = i7 != 0 ? Modifier.INSTANCE : modifier2;
                        if ((i2 & 4) != 0) {
                            SliderColors colors = colors(startRestartGroup, (i3 >> 12) & 14);
                            i3 &= -897;
                            sliderColors2 = colors;
                        }
                        SliderColors sliderColors5 = sliderColors2;
                        i5 = i3;
                        sliderColors3 = sliderColors5;
                        if (i4 != 0) {
                            z3 = true;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1546713545, i5, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:999)");
                            }
                            int i8 = i5;
                            final long m2642trackColorWaAFU9c$material3_release = sliderColors3.m2642trackColorWaAFU9c$material3_release(z3, false);
                            final long m2642trackColorWaAFU9c$material3_release2 = sliderColors3.m2642trackColorWaAFU9c$material3_release(z3, true);
                            Modifier modifier4 = companion;
                            final long m2641tickColorWaAFU9c$material3_release = sliderColors3.m2641tickColorWaAFU9c$material3_release(z3, false);
                            final long m2641tickColorWaAFU9c$material3_release2 = sliderColors3.m2641tickColorWaAFU9c$material3_release(z3, true);
                            SliderColors sliderColors6 = sliderColors3;
                            Modifier m916height3ABfNKs = SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, null), SliderKt.getTrackHeight());
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -801023075, "CC(remember):Slider.kt#9igjgp");
                            changed = startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release) | ((i8 & 14) != 4) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release2) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release2);
                            rememberedValue = startRestartGroup.rememberedValue();
                            if (!changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                z4 = z3;
                                rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                                        invoke2(drawScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(DrawScope drawScope) {
                                        ArrayList arrayList;
                                        long j;
                                        long j2;
                                        boolean z6 = drawScope.getLayoutDirection() == LayoutDirection.Rtl;
                                        long Offset = OffsetKt.Offset(0.0f, Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
                                        long Offset2 = OffsetKt.Offset(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
                                        long j3 = Offset;
                                        long j4 = z6 ? Offset2 : j3;
                                        if (!z6) {
                                            j3 = Offset2;
                                        }
                                        float f = drawScope.mo459toPx0680j_4(SliderDefaults.INSTANCE.m2652getTickSizeD9Ej5fM());
                                        float f2 = drawScope.mo459toPx0680j_4(SliderKt.getTrackHeight());
                                        DrawScope.CC.m5198drawLineNGM6Ib0$default(drawScope, m2642trackColorWaAFU9c$material3_release, j4, j3, f2, StrokeCap.INSTANCE.m4996getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                                        long j5 = j4;
                                        long j6 = j3;
                                        DrawScope.CC.m5198drawLineNGM6Ib0$default(drawScope, m2642trackColorWaAFU9c$material3_release2, OffsetKt.Offset(Offset.m4343getXimpl(j4) + ((Offset.m4343getXimpl(j3) - Offset.m4343getXimpl(j4)) * sliderPositions2.getActiveRange().getStart().floatValue()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0())), OffsetKt.Offset(Offset.m4343getXimpl(j4) + ((Offset.m4343getXimpl(j3) - Offset.m4343getXimpl(j4)) * sliderPositions2.getActiveRange().getEndInclusive().floatValue()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0())), f2, StrokeCap.INSTANCE.m4996getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                                        float[] tickFractions = sliderPositions2.getTickFractions();
                                        SliderPositions sliderPositions3 = sliderPositions2;
                                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                                        int length = tickFractions.length;
                                        for (int i9 = 0; i9 < length; i9++) {
                                            float f3 = tickFractions[i9];
                                            Boolean valueOf = Boolean.valueOf(f3 > sliderPositions3.getActiveRange().getEndInclusive().floatValue() || f3 < sliderPositions3.getActiveRange().getStart().floatValue());
                                            Object obj = linkedHashMap.get(valueOf);
                                            if (obj == null) {
                                                obj = (List) new ArrayList();
                                                linkedHashMap.put(valueOf, obj);
                                            }
                                            ((List) obj).add(Float.valueOf(f3));
                                        }
                                        long j7 = m2641tickColorWaAFU9c$material3_release;
                                        long j8 = m2641tickColorWaAFU9c$material3_release2;
                                        for (Map.Entry entry : linkedHashMap.entrySet()) {
                                            boolean booleanValue = ((Boolean) entry.getKey()).booleanValue();
                                            List list = (List) entry.getValue();
                                            ArrayList arrayList2 = new ArrayList(list.size());
                                            int size = list.size();
                                            for (int i10 = 0; i10 < size; i10++) {
                                                arrayList2.add(Offset.m4332boximpl(OffsetKt.Offset(Offset.m4343getXimpl(OffsetKt.m4366lerpWko1d7g(j5, j6, ((Number) list.get(i10)).floatValue())), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()))));
                                            }
                                            long j9 = j5;
                                            long j10 = j6;
                                            ArrayList arrayList3 = arrayList2;
                                            int m4943getPointsr_lszbg = PointMode.INSTANCE.m4943getPointsr_lszbg();
                                            if (booleanValue) {
                                                j = j8;
                                                arrayList = arrayList3;
                                                j2 = j7;
                                            } else {
                                                arrayList = arrayList3;
                                                j = j8;
                                                j2 = j;
                                            }
                                            DrawScope.CC.m5203drawPointsF8ZwMP8$default(drawScope, arrayList, m4943getPointsr_lszbg, j2, f, StrokeCap.INSTANCE.m4996getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                                            j6 = j10;
                                            j8 = j;
                                            j5 = j9;
                                        }
                                    }
                                };
                                startRestartGroup.updateRememberedValue(rememberedValue);
                            } else {
                                z4 = z3;
                            }
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            CanvasKt.Canvas(m916height3ABfNKs, (Function1) rememberedValue, startRestartGroup, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            sliderColors4 = sliderColors6;
                            z5 = z4;
                            modifier3 = modifier4;
                        }
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        SliderColors sliderColors7 = sliderColors2;
                        i5 = i3;
                        sliderColors3 = sliderColors7;
                        companion = modifier2;
                    }
                    z3 = z2;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    int i82 = i5;
                    final long m2642trackColorWaAFU9c$material3_release3 = sliderColors3.m2642trackColorWaAFU9c$material3_release(z3, false);
                    final long m2642trackColorWaAFU9c$material3_release22 = sliderColors3.m2642trackColorWaAFU9c$material3_release(z3, true);
                    Modifier modifier42 = companion;
                    final long m2641tickColorWaAFU9c$material3_release3 = sliderColors3.m2641tickColorWaAFU9c$material3_release(z3, false);
                    final long m2641tickColorWaAFU9c$material3_release22 = sliderColors3.m2641tickColorWaAFU9c$material3_release(z3, true);
                    SliderColors sliderColors62 = sliderColors3;
                    Modifier m916height3ABfNKs2 = SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(modifier42, 0.0f, 1, null), SliderKt.getTrackHeight());
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -801023075, "CC(remember):Slider.kt#9igjgp");
                    changed = startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release3) | ((i82 & 14) != 4) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release22) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release3) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release22);
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (changed) {
                    }
                    z4 = z3;
                    rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                            invoke2(drawScope);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DrawScope drawScope) {
                            ArrayList arrayList;
                            long j;
                            long j2;
                            boolean z6 = drawScope.getLayoutDirection() == LayoutDirection.Rtl;
                            long Offset = OffsetKt.Offset(0.0f, Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
                            long Offset2 = OffsetKt.Offset(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
                            long j3 = Offset;
                            long j4 = z6 ? Offset2 : j3;
                            if (!z6) {
                                j3 = Offset2;
                            }
                            float f = drawScope.mo459toPx0680j_4(SliderDefaults.INSTANCE.m2652getTickSizeD9Ej5fM());
                            float f2 = drawScope.mo459toPx0680j_4(SliderKt.getTrackHeight());
                            DrawScope.CC.m5198drawLineNGM6Ib0$default(drawScope, m2642trackColorWaAFU9c$material3_release3, j4, j3, f2, StrokeCap.INSTANCE.m4996getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                            long j5 = j4;
                            long j6 = j3;
                            DrawScope.CC.m5198drawLineNGM6Ib0$default(drawScope, m2642trackColorWaAFU9c$material3_release22, OffsetKt.Offset(Offset.m4343getXimpl(j4) + ((Offset.m4343getXimpl(j3) - Offset.m4343getXimpl(j4)) * sliderPositions2.getActiveRange().getStart().floatValue()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0())), OffsetKt.Offset(Offset.m4343getXimpl(j4) + ((Offset.m4343getXimpl(j3) - Offset.m4343getXimpl(j4)) * sliderPositions2.getActiveRange().getEndInclusive().floatValue()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0())), f2, StrokeCap.INSTANCE.m4996getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                            float[] tickFractions = sliderPositions2.getTickFractions();
                            SliderPositions sliderPositions3 = sliderPositions2;
                            LinkedHashMap linkedHashMap = new LinkedHashMap();
                            int length = tickFractions.length;
                            for (int i9 = 0; i9 < length; i9++) {
                                float f3 = tickFractions[i9];
                                Boolean valueOf = Boolean.valueOf(f3 > sliderPositions3.getActiveRange().getEndInclusive().floatValue() || f3 < sliderPositions3.getActiveRange().getStart().floatValue());
                                Object obj = linkedHashMap.get(valueOf);
                                if (obj == null) {
                                    obj = (List) new ArrayList();
                                    linkedHashMap.put(valueOf, obj);
                                }
                                ((List) obj).add(Float.valueOf(f3));
                            }
                            long j7 = m2641tickColorWaAFU9c$material3_release3;
                            long j8 = m2641tickColorWaAFU9c$material3_release22;
                            for (Map.Entry entry : linkedHashMap.entrySet()) {
                                boolean booleanValue = ((Boolean) entry.getKey()).booleanValue();
                                List list = (List) entry.getValue();
                                ArrayList arrayList2 = new ArrayList(list.size());
                                int size = list.size();
                                for (int i10 = 0; i10 < size; i10++) {
                                    arrayList2.add(Offset.m4332boximpl(OffsetKt.Offset(Offset.m4343getXimpl(OffsetKt.m4366lerpWko1d7g(j5, j6, ((Number) list.get(i10)).floatValue())), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()))));
                                }
                                long j9 = j5;
                                long j10 = j6;
                                ArrayList arrayList3 = arrayList2;
                                int m4943getPointsr_lszbg = PointMode.INSTANCE.m4943getPointsr_lszbg();
                                if (booleanValue) {
                                    j = j8;
                                    arrayList = arrayList3;
                                    j2 = j7;
                                } else {
                                    arrayList = arrayList3;
                                    j = j8;
                                    j2 = j;
                                }
                                DrawScope.CC.m5203drawPointsF8ZwMP8$default(drawScope, arrayList, m4943getPointsr_lszbg, j2, f, StrokeCap.INSTANCE.m4996getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                                j6 = j10;
                                j8 = j;
                                j5 = j9;
                            }
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    CanvasKt.Canvas(m916height3ABfNKs2, (Function1) rememberedValue, startRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    sliderColors4 = sliderColors62;
                    z5 = z4;
                    modifier3 = modifier42;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    sliderColors4 = sliderColors2;
                    z5 = z2;
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i9) {
                            SliderDefaults.this.Track(sliderPositions, modifier3, sliderColors4, z5, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                    return;
                }
                return;
            }
            z2 = z;
            if ((i2 & 16) != 0) {
            }
            if ((i3 & 9363) == 9362) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i7 != 0) {
            }
            if ((i2 & 4) != 0) {
            }
            SliderColors sliderColors52 = sliderColors2;
            i5 = i3;
            sliderColors3 = sliderColors52;
            if (i4 != 0) {
            }
            z3 = z2;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            int i822 = i5;
            final long m2642trackColorWaAFU9c$material3_release32 = sliderColors3.m2642trackColorWaAFU9c$material3_release(z3, false);
            final long m2642trackColorWaAFU9c$material3_release222 = sliderColors3.m2642trackColorWaAFU9c$material3_release(z3, true);
            Modifier modifier422 = companion;
            final long m2641tickColorWaAFU9c$material3_release32 = sliderColors3.m2641tickColorWaAFU9c$material3_release(z3, false);
            final long m2641tickColorWaAFU9c$material3_release222 = sliderColors3.m2641tickColorWaAFU9c$material3_release(z3, true);
            SliderColors sliderColors622 = sliderColors3;
            Modifier m916height3ABfNKs22 = SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(modifier422, 0.0f, 1, null), SliderKt.getTrackHeight());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -801023075, "CC(remember):Slider.kt#9igjgp");
            changed = startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release32) | ((i822 & 14) != 4) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release222) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release32) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release222);
            rememberedValue = startRestartGroup.rememberedValue();
            if (changed) {
            }
            z4 = z3;
            rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope drawScope) {
                    ArrayList arrayList;
                    long j;
                    long j2;
                    boolean z6 = drawScope.getLayoutDirection() == LayoutDirection.Rtl;
                    long Offset = OffsetKt.Offset(0.0f, Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
                    long Offset2 = OffsetKt.Offset(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
                    long j3 = Offset;
                    long j4 = z6 ? Offset2 : j3;
                    if (!z6) {
                        j3 = Offset2;
                    }
                    float f = drawScope.mo459toPx0680j_4(SliderDefaults.INSTANCE.m2652getTickSizeD9Ej5fM());
                    float f2 = drawScope.mo459toPx0680j_4(SliderKt.getTrackHeight());
                    DrawScope.CC.m5198drawLineNGM6Ib0$default(drawScope, m2642trackColorWaAFU9c$material3_release32, j4, j3, f2, StrokeCap.INSTANCE.m4996getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                    long j5 = j4;
                    long j6 = j3;
                    DrawScope.CC.m5198drawLineNGM6Ib0$default(drawScope, m2642trackColorWaAFU9c$material3_release222, OffsetKt.Offset(Offset.m4343getXimpl(j4) + ((Offset.m4343getXimpl(j3) - Offset.m4343getXimpl(j4)) * sliderPositions2.getActiveRange().getStart().floatValue()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0())), OffsetKt.Offset(Offset.m4343getXimpl(j4) + ((Offset.m4343getXimpl(j3) - Offset.m4343getXimpl(j4)) * sliderPositions2.getActiveRange().getEndInclusive().floatValue()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0())), f2, StrokeCap.INSTANCE.m4996getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                    float[] tickFractions = sliderPositions2.getTickFractions();
                    SliderPositions sliderPositions3 = sliderPositions2;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    int length = tickFractions.length;
                    for (int i9 = 0; i9 < length; i9++) {
                        float f3 = tickFractions[i9];
                        Boolean valueOf = Boolean.valueOf(f3 > sliderPositions3.getActiveRange().getEndInclusive().floatValue() || f3 < sliderPositions3.getActiveRange().getStart().floatValue());
                        Object obj = linkedHashMap.get(valueOf);
                        if (obj == null) {
                            obj = (List) new ArrayList();
                            linkedHashMap.put(valueOf, obj);
                        }
                        ((List) obj).add(Float.valueOf(f3));
                    }
                    long j7 = m2641tickColorWaAFU9c$material3_release32;
                    long j8 = m2641tickColorWaAFU9c$material3_release222;
                    for (Map.Entry entry : linkedHashMap.entrySet()) {
                        boolean booleanValue = ((Boolean) entry.getKey()).booleanValue();
                        List list = (List) entry.getValue();
                        ArrayList arrayList2 = new ArrayList(list.size());
                        int size = list.size();
                        for (int i10 = 0; i10 < size; i10++) {
                            arrayList2.add(Offset.m4332boximpl(OffsetKt.Offset(Offset.m4343getXimpl(OffsetKt.m4366lerpWko1d7g(j5, j6, ((Number) list.get(i10)).floatValue())), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()))));
                        }
                        long j9 = j5;
                        long j10 = j6;
                        ArrayList arrayList3 = arrayList2;
                        int m4943getPointsr_lszbg = PointMode.INSTANCE.m4943getPointsr_lszbg();
                        if (booleanValue) {
                            j = j8;
                            arrayList = arrayList3;
                            j2 = j7;
                        } else {
                            arrayList = arrayList3;
                            j = j8;
                            j2 = j;
                        }
                        DrawScope.CC.m5203drawPointsF8ZwMP8$default(drawScope, arrayList, m4943getPointsr_lszbg, j2, f, StrokeCap.INSTANCE.m4996getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                        j6 = j10;
                        j8 = j;
                        j5 = j9;
                    }
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            CanvasKt.Canvas(m916height3ABfNKs22, (Function1) rememberedValue, startRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            sliderColors4 = sliderColors622;
            z5 = z4;
            modifier3 = modifier422;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        if ((i & 384) != 0) {
        }
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        z2 = z;
        if ((i2 & 16) != 0) {
        }
        if ((i3 & 9363) == 9362) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i7 != 0) {
        }
        if ((i2 & 4) != 0) {
        }
        SliderColors sliderColors522 = sliderColors2;
        i5 = i3;
        sliderColors3 = sliderColors522;
        if (i4 != 0) {
        }
        z3 = z2;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        int i8222 = i5;
        final long m2642trackColorWaAFU9c$material3_release322 = sliderColors3.m2642trackColorWaAFU9c$material3_release(z3, false);
        final long m2642trackColorWaAFU9c$material3_release2222 = sliderColors3.m2642trackColorWaAFU9c$material3_release(z3, true);
        Modifier modifier4222 = companion;
        final long m2641tickColorWaAFU9c$material3_release322 = sliderColors3.m2641tickColorWaAFU9c$material3_release(z3, false);
        final long m2641tickColorWaAFU9c$material3_release2222 = sliderColors3.m2641tickColorWaAFU9c$material3_release(z3, true);
        SliderColors sliderColors6222 = sliderColors3;
        Modifier m916height3ABfNKs222 = SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4222, 0.0f, 1, null), SliderKt.getTrackHeight());
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -801023075, "CC(remember):Slider.kt#9igjgp");
        changed = startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release322) | ((i8222 & 14) != 4) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release2222) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release322) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release2222);
        rememberedValue = startRestartGroup.rememberedValue();
        if (changed) {
        }
        z4 = z3;
        rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope drawScope) {
                ArrayList arrayList;
                long j;
                long j2;
                boolean z6 = drawScope.getLayoutDirection() == LayoutDirection.Rtl;
                long Offset = OffsetKt.Offset(0.0f, Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
                long Offset2 = OffsetKt.Offset(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
                long j3 = Offset;
                long j4 = z6 ? Offset2 : j3;
                if (!z6) {
                    j3 = Offset2;
                }
                float f = drawScope.mo459toPx0680j_4(SliderDefaults.INSTANCE.m2652getTickSizeD9Ej5fM());
                float f2 = drawScope.mo459toPx0680j_4(SliderKt.getTrackHeight());
                DrawScope.CC.m5198drawLineNGM6Ib0$default(drawScope, m2642trackColorWaAFU9c$material3_release322, j4, j3, f2, StrokeCap.INSTANCE.m4996getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                long j5 = j4;
                long j6 = j3;
                DrawScope.CC.m5198drawLineNGM6Ib0$default(drawScope, m2642trackColorWaAFU9c$material3_release2222, OffsetKt.Offset(Offset.m4343getXimpl(j4) + ((Offset.m4343getXimpl(j3) - Offset.m4343getXimpl(j4)) * sliderPositions2.getActiveRange().getStart().floatValue()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0())), OffsetKt.Offset(Offset.m4343getXimpl(j4) + ((Offset.m4343getXimpl(j3) - Offset.m4343getXimpl(j4)) * sliderPositions2.getActiveRange().getEndInclusive().floatValue()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0())), f2, StrokeCap.INSTANCE.m4996getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                float[] tickFractions = sliderPositions2.getTickFractions();
                SliderPositions sliderPositions3 = sliderPositions2;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                int length = tickFractions.length;
                for (int i9 = 0; i9 < length; i9++) {
                    float f3 = tickFractions[i9];
                    Boolean valueOf = Boolean.valueOf(f3 > sliderPositions3.getActiveRange().getEndInclusive().floatValue() || f3 < sliderPositions3.getActiveRange().getStart().floatValue());
                    Object obj = linkedHashMap.get(valueOf);
                    if (obj == null) {
                        obj = (List) new ArrayList();
                        linkedHashMap.put(valueOf, obj);
                    }
                    ((List) obj).add(Float.valueOf(f3));
                }
                long j7 = m2641tickColorWaAFU9c$material3_release322;
                long j8 = m2641tickColorWaAFU9c$material3_release2222;
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    boolean booleanValue = ((Boolean) entry.getKey()).booleanValue();
                    List list = (List) entry.getValue();
                    ArrayList arrayList2 = new ArrayList(list.size());
                    int size = list.size();
                    for (int i10 = 0; i10 < size; i10++) {
                        arrayList2.add(Offset.m4332boximpl(OffsetKt.Offset(Offset.m4343getXimpl(OffsetKt.m4366lerpWko1d7g(j5, j6, ((Number) list.get(i10)).floatValue())), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()))));
                    }
                    long j9 = j5;
                    long j10 = j6;
                    ArrayList arrayList3 = arrayList2;
                    int m4943getPointsr_lszbg = PointMode.INSTANCE.m4943getPointsr_lszbg();
                    if (booleanValue) {
                        j = j8;
                        arrayList = arrayList3;
                        j2 = j7;
                    } else {
                        arrayList = arrayList3;
                        j = j8;
                        j2 = j;
                    }
                    DrawScope.CC.m5203drawPointsF8ZwMP8$default(drawScope, arrayList, m4943getPointsr_lszbg, j2, f, StrokeCap.INSTANCE.m4996getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                    j6 = j10;
                    j8 = j;
                    j5 = j9;
                }
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        CanvasKt.Canvas(m916height3ABfNKs222, (Function1) rememberedValue, startRestartGroup, 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        sliderColors4 = sliderColors6222;
        z5 = z4;
        modifier3 = modifier4222;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x005d  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `drawStopIndicator`, `drawTick`, `thumbTrackGapSize` and `trackInsideCornerSize`, see `LegacySliderSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "Track(sliderState, modifier, enabled, colors, drawStopIndicator, drawTick, thumbTrackGapSize, trackInsideCornerSize)", imports = {}))
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ void Track(final SliderState sliderState, Modifier modifier, SliderColors sliderColors, boolean z, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        SliderColors sliderColors2;
        int i4;
        boolean z2;
        Modifier.Companion companion;
        SliderColors sliderColors3;
        int i5;
        boolean z3;
        float f;
        float f2;
        final SliderColors sliderColors4;
        final boolean z4;
        final Modifier modifier3;
        ScopeUpdateScope endRestartGroup;
        int i6;
        Composer startRestartGroup = composer.startRestartGroup(593554206);
        ComposerKt.sourceInformation(startRestartGroup, "C(Track)P(3,2)1079@48295L8,1082@48353L213:Slider.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changedInstance(sliderState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i7 = i2 & 2;
        if (i7 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i & 384) != 0) {
                if ((i2 & 4) == 0) {
                    sliderColors2 = sliderColors;
                    if (startRestartGroup.changed(sliderColors2)) {
                        i6 = 256;
                        i3 |= i6;
                    }
                } else {
                    sliderColors2 = sliderColors;
                }
                i6 = 128;
                i3 |= i6;
            } else {
                sliderColors2 = sliderColors;
            }
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                z2 = z;
                i3 |= startRestartGroup.changed(z2) ? 2048 : 1024;
                if ((i2 & 16) != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    i3 |= startRestartGroup.changed(this) ? 16384 : 8192;
                }
                if ((i3 & 9363) == 9362 || !startRestartGroup.getSkipping()) {
                    startRestartGroup.startDefaults();
                    if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                        companion = i7 != 0 ? Modifier.INSTANCE : modifier2;
                        if ((i2 & 4) != 0) {
                            sliderColors3 = colors(startRestartGroup, (i3 >> 12) & 14);
                            i3 &= -897;
                        } else {
                            sliderColors3 = sliderColors2;
                        }
                        if (i4 != 0) {
                            i5 = i3;
                            z3 = true;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(593554206, i5, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1081)");
                            }
                            f = SliderKt.ThumbTrackGapSize;
                            f2 = SliderKt.TrackInsideCornerSize;
                            Modifier modifier4 = companion;
                            sliderColors4 = sliderColors3;
                            m2650Track4EFweAY(sliderState, modifier4, z3, sliderColors4, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, f, f2, startRestartGroup, (i5 & 14) | 14155776 | (i5 & 112) | ((i5 >> 3) & 896) | ((i5 << 3) & 7168) | ((i5 << 12) & 234881024), 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z3;
                            modifier3 = modifier4;
                        }
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        companion = modifier2;
                        sliderColors3 = sliderColors2;
                    }
                    i5 = i3;
                    z3 = z2;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    f = SliderKt.ThumbTrackGapSize;
                    f2 = SliderKt.TrackInsideCornerSize;
                    Modifier modifier42 = companion;
                    sliderColors4 = sliderColors3;
                    m2650Track4EFweAY(sliderState, modifier42, z3, sliderColors4, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, f, f2, startRestartGroup, (i5 & 14) | 14155776 | (i5 & 112) | ((i5 >> 3) & 896) | ((i5 << 3) & 7168) | ((i5 << 12) & 234881024), 48);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    z4 = z3;
                    modifier3 = modifier42;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    sliderColors4 = sliderColors2;
                    z4 = z2;
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i8) {
                            SliderDefaults.this.Track(sliderState, modifier3, sliderColors4, z4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                    return;
                }
                return;
            }
            z2 = z;
            if ((i2 & 16) != 0) {
            }
            if ((i3 & 9363) == 9362) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i7 != 0) {
            }
            if ((i2 & 4) != 0) {
            }
            if (i4 != 0) {
            }
            i5 = i3;
            z3 = z2;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            f = SliderKt.ThumbTrackGapSize;
            f2 = SliderKt.TrackInsideCornerSize;
            Modifier modifier422 = companion;
            sliderColors4 = sliderColors3;
            m2650Track4EFweAY(sliderState, modifier422, z3, sliderColors4, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, f, f2, startRestartGroup, (i5 & 14) | 14155776 | (i5 & 112) | ((i5 >> 3) & 896) | ((i5 << 3) & 7168) | ((i5 << 12) & 234881024), 48);
            if (ComposerKt.isTraceInProgress()) {
            }
            z4 = z3;
            modifier3 = modifier422;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        if ((i & 384) != 0) {
        }
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        z2 = z;
        if ((i2 & 16) != 0) {
        }
        if ((i3 & 9363) == 9362) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i7 != 0) {
        }
        if ((i2 & 4) != 0) {
        }
        if (i4 != 0) {
        }
        i5 = i3;
        z3 = z2;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        f = SliderKt.ThumbTrackGapSize;
        f2 = SliderKt.TrackInsideCornerSize;
        Modifier modifier4222 = companion;
        sliderColors4 = sliderColors3;
        m2650Track4EFweAY(sliderState, modifier4222, z3, sliderColors4, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, f, f2, startRestartGroup, (i5 & 14) | 14155776 | (i5 & 112) | ((i5 >> 3) & 896) | ((i5 << 3) & 7168) | ((i5 << 12) & 234881024), 48);
        if (ComposerKt.isTraceInProgress()) {
        }
        z4 = z3;
        modifier3 = modifier4222;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:79:0x027a, code lost:
    
        if (r5.changed(r2) == false) goto L170;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0268  */
    /* renamed from: Track-4EFweAY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m2650Track4EFweAY(final SliderState sliderState, Modifier modifier, boolean z, SliderColors sliderColors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float f, float f2, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        final boolean z2;
        final SliderColors sliderColors2;
        final Function2<? super DrawScope, ? super Offset, Unit> function22;
        int i5;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function32;
        int i6;
        float f3;
        int i7;
        final float f4;
        float f5;
        float f6;
        int i8;
        Function2<? super DrawScope, ? super Offset, Unit> function23;
        boolean z3;
        boolean z4;
        Object rememberedValue;
        Composer composer2;
        SliderColors sliderColors3;
        final float f7;
        boolean z5;
        final float f8;
        final boolean z6;
        final SliderColors sliderColors4;
        final Modifier modifier3;
        ScopeUpdateScope endRestartGroup;
        int i9;
        int i10;
        int i11;
        Composer startRestartGroup = composer.startRestartGroup(49984771);
        ComposerKt.sourceInformation(startRestartGroup, "C(Track)P(5,4,3!3,6:c#ui.unit.Dp,7:c#ui.unit.Dp)1114@49756L8,1115@49825L232,1137@50806L7,1138@50862L595,1133@50659L798:Slider.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changedInstance(sliderState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i12 = i2 & 2;
        if (i12 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i4 = i2 & 4;
            if (i4 == 0) {
                i3 |= 384;
            } else if ((i & 384) == 0) {
                z2 = z;
                i3 |= startRestartGroup.changed(z2) ? 256 : 128;
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        sliderColors2 = sliderColors;
                        if (startRestartGroup.changed(sliderColors2)) {
                            i11 = 2048;
                            i3 |= i11;
                        }
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i11 = 1024;
                    i3 |= i11;
                } else {
                    sliderColors2 = sliderColors;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        function22 = function2;
                        if (startRestartGroup.changedInstance(function22)) {
                            i10 = 16384;
                            i3 |= i10;
                        }
                    } else {
                        function22 = function2;
                    }
                    i10 = 8192;
                    i3 |= i10;
                } else {
                    function22 = function2;
                }
                i5 = i2 & 32;
                if (i5 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function32 = function3;
                } else {
                    function32 = function3;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        i3 |= startRestartGroup.changedInstance(function32) ? 131072 : 65536;
                    }
                }
                i6 = i2 & 64;
                if (i6 != 0) {
                    i3 |= 1572864;
                    f3 = f;
                } else {
                    f3 = f;
                    if ((i & 1572864) == 0) {
                        i3 |= startRestartGroup.changed(f3) ? 1048576 : 524288;
                    }
                }
                i7 = i2 & 128;
                if (i7 != 0) {
                    i3 |= 12582912;
                    f4 = f2;
                } else {
                    f4 = f2;
                    if ((i & 12582912) == 0) {
                        i3 |= startRestartGroup.changed(f4) ? 8388608 : 4194304;
                    }
                }
                if ((i2 & 256) == 0) {
                    i9 = (i & 100663296) == 0 ? startRestartGroup.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432 : 100663296;
                    if ((38347923 & i3) == 38347922 || !startRestartGroup.getSkipping()) {
                        startRestartGroup.startDefaults();
                        if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                            if (i12 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                SliderColors colors = colors(startRestartGroup, (i3 >> 24) & 14);
                                i3 &= -7169;
                                sliderColors2 = colors;
                            }
                            if ((i2 & 16) != 0) {
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -800875397, "CC(remember):Slider.kt#9igjgp");
                                boolean z7 = ((((i3 & 7168) ^ 3072) > 2048 && startRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                                Object rememberedValue2 = startRestartGroup.rememberedValue();
                                if (z7 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue2 = (Function2) new Function2<DrawScope, Offset, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$4$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset) {
                                            m2655invokeUv8p0NA(drawScope, offset.m4353unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke-Uv8p0NA, reason: not valid java name */
                                        public final void m2655invokeUv8p0NA(DrawScope drawScope, long j) {
                                            SliderDefaults.INSTANCE.m2645drawStopIndicatorx3O1jOs(drawScope, j, SliderDefaults.INSTANCE.m2653getTrackStopIndicatorSizeD9Ej5fM(), SliderColors.this.m2642trackColorWaAFU9c$material3_release(z2, true));
                                        }
                                    };
                                    startRestartGroup.updateRememberedValue(rememberedValue2);
                                }
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                i3 &= -57345;
                                function22 = (Function2) rememberedValue2;
                            }
                            if (i5 != 0) {
                                function32 = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$5
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m2656invokewPWG1Vc(drawScope, offset.m4353unboximpl(), color.m4625unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m2656invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults.INSTANCE.m2645drawStopIndicatorx3O1jOs(drawScope, j, SliderDefaults.INSTANCE.m2652getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                            }
                            if (i6 != 0) {
                                f6 = SliderKt.ThumbTrackGapSize;
                                f3 = f6;
                            }
                            if (i7 != 0) {
                                f5 = SliderKt.TrackInsideCornerSize;
                                f4 = f5;
                            }
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                        }
                        i8 = i3;
                        Modifier modifier4 = modifier2;
                        SliderColors sliderColors5 = sliderColors2;
                        float f9 = f3;
                        boolean z8 = z2;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(49984771, i8, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1128)");
                        }
                        final long m2642trackColorWaAFU9c$material3_release = sliderColors5.m2642trackColorWaAFU9c$material3_release(z8, false);
                        final long m2642trackColorWaAFU9c$material3_release2 = sliderColors5.m2642trackColorWaAFU9c$material3_release(z8, true);
                        float f10 = f4;
                        Function2<? super DrawScope, ? super Offset, Unit> function24 = function22;
                        final long m2641tickColorWaAFU9c$material3_release = sliderColors5.m2641tickColorWaAFU9c$material3_release(z8, false);
                        final long m2641tickColorWaAFU9c$material3_release2 = sliderColors5.m2641tickColorWaAFU9c$material3_release(z8, true);
                        Modifier m916height3ABfNKs = SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, null), SliderKt.getTrackHeight());
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object consume = startRestartGroup.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        Modifier rotate = RotateKt.rotate(m916height3ABfNKs, consume != LayoutDirection.Rtl ? 180.0f : 0.0f);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -800841850, "CC(remember):Slider.kt#9igjgp");
                        boolean changedInstance = ((3670016 & i8) != 1048576) | startRestartGroup.changedInstance(sliderState) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release2) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release2) | ((29360128 & i8) != 8388608);
                        if (((57344 & i8) ^ 24576) <= 16384) {
                            function23 = function24;
                        } else {
                            function23 = function24;
                        }
                        if ((i8 & 24576) != 16384) {
                            z3 = false;
                            z4 = changedInstance | z3 | ((458752 & i8) == 131072);
                            rememberedValue = startRestartGroup.rememberedValue();
                            if (!z4 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                composer2 = startRestartGroup;
                                sliderColors3 = sliderColors5;
                                f7 = f9;
                                function22 = function23;
                                f4 = f10;
                                z5 = z8;
                                rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$6$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                                        invoke2(drawScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(DrawScope drawScope) {
                                        SliderDefaults.INSTANCE.m2646drawTrackngJ0SCU(drawScope, SliderState.this.getTickFractions(), 0.0f, SliderState.this.getCoercedValueAsFraction$material3_release(), m2642trackColorWaAFU9c$material3_release, m2642trackColorWaAFU9c$material3_release2, m2641tickColorWaAFU9c$material3_release, m2641tickColorWaAFU9c$material3_release2, drawScope.mo455toDpu2uoSUM(SliderState.this.getTrackHeight$material3_release()), drawScope.mo456toDpu2uoSUM(0), drawScope.mo455toDpu2uoSUM(SliderState.this.getThumbWidth$material3_release()), f7, f4, function22, function32, false);
                                    }
                                };
                                composer2.updateRememberedValue(rememberedValue);
                            } else {
                                f7 = f9;
                                f4 = f10;
                                function22 = function23;
                                z5 = z8;
                                composer2 = startRestartGroup;
                                sliderColors3 = sliderColors5;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            CanvasKt.Canvas(rotate, (Function1) rememberedValue, composer2, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            f8 = f7;
                            z6 = z5;
                            sliderColors4 = sliderColors3;
                            modifier3 = modifier4;
                        }
                        z3 = true;
                        z4 = changedInstance | z3 | ((458752 & i8) == 131072);
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (z4) {
                        }
                        composer2 = startRestartGroup;
                        sliderColors3 = sliderColors5;
                        f7 = f9;
                        function22 = function23;
                        f4 = f10;
                        z5 = z8;
                        rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$6$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                                invoke2(drawScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(DrawScope drawScope) {
                                SliderDefaults.INSTANCE.m2646drawTrackngJ0SCU(drawScope, SliderState.this.getTickFractions(), 0.0f, SliderState.this.getCoercedValueAsFraction$material3_release(), m2642trackColorWaAFU9c$material3_release, m2642trackColorWaAFU9c$material3_release2, m2641tickColorWaAFU9c$material3_release, m2641tickColorWaAFU9c$material3_release2, drawScope.mo455toDpu2uoSUM(SliderState.this.getTrackHeight$material3_release()), drawScope.mo456toDpu2uoSUM(0), drawScope.mo455toDpu2uoSUM(SliderState.this.getThumbWidth$material3_release()), f7, f4, function22, function32, false);
                            }
                        };
                        composer2.updateRememberedValue(rememberedValue);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        CanvasKt.Canvas(rotate, (Function1) rememberedValue, composer2, 0);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        f8 = f7;
                        z6 = z5;
                        sliderColors4 = sliderColors3;
                        modifier3 = modifier4;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        composer2 = startRestartGroup;
                        modifier3 = modifier2;
                        sliderColors4 = sliderColors2;
                        f8 = f3;
                        z6 = z2;
                    }
                    final float f11 = f4;
                    final Function2<? super DrawScope, ? super Offset, Unit> function25 = function22;
                    final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function33 = function32;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$7
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
                                SliderDefaults.this.m2650Track4EFweAY(sliderState, modifier3, z6, sliderColors4, function25, function33, f8, f11, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                        return;
                    }
                    return;
                }
                i3 |= i9;
                if ((38347923 & i3) == 38347922) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i12 != 0) {
                }
                if (i4 != 0) {
                }
                if ((i2 & 8) != 0) {
                }
                if ((i2 & 16) != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                if (i7 != 0) {
                }
                i8 = i3;
                Modifier modifier42 = modifier2;
                SliderColors sliderColors52 = sliderColors2;
                float f92 = f3;
                boolean z82 = z2;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                final long m2642trackColorWaAFU9c$material3_release3 = sliderColors52.m2642trackColorWaAFU9c$material3_release(z82, false);
                final long m2642trackColorWaAFU9c$material3_release22 = sliderColors52.m2642trackColorWaAFU9c$material3_release(z82, true);
                float f102 = f4;
                Function2<? super DrawScope, ? super Offset, Unit> function242 = function22;
                final long m2641tickColorWaAFU9c$material3_release3 = sliderColors52.m2641tickColorWaAFU9c$material3_release(z82, false);
                final long m2641tickColorWaAFU9c$material3_release22 = sliderColors52.m2641tickColorWaAFU9c$material3_release(z82, true);
                Modifier m916height3ABfNKs2 = SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(modifier42, 0.0f, 1, null), SliderKt.getTrackHeight());
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume2 = startRestartGroup.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Modifier rotate2 = RotateKt.rotate(m916height3ABfNKs2, consume2 != LayoutDirection.Rtl ? 180.0f : 0.0f);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -800841850, "CC(remember):Slider.kt#9igjgp");
                boolean changedInstance2 = ((3670016 & i8) != 1048576) | startRestartGroup.changedInstance(sliderState) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release3) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release22) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release3) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release22) | ((29360128 & i8) != 8388608);
                if (((57344 & i8) ^ 24576) <= 16384) {
                }
                if ((i8 & 24576) != 16384) {
                }
                z3 = true;
                z4 = changedInstance2 | z3 | ((458752 & i8) == 131072);
                rememberedValue = startRestartGroup.rememberedValue();
                if (z4) {
                }
                composer2 = startRestartGroup;
                sliderColors3 = sliderColors52;
                f7 = f92;
                function22 = function23;
                f4 = f102;
                z5 = z82;
                rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$6$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope drawScope) {
                        SliderDefaults.INSTANCE.m2646drawTrackngJ0SCU(drawScope, SliderState.this.getTickFractions(), 0.0f, SliderState.this.getCoercedValueAsFraction$material3_release(), m2642trackColorWaAFU9c$material3_release3, m2642trackColorWaAFU9c$material3_release22, m2641tickColorWaAFU9c$material3_release3, m2641tickColorWaAFU9c$material3_release22, drawScope.mo455toDpu2uoSUM(SliderState.this.getTrackHeight$material3_release()), drawScope.mo456toDpu2uoSUM(0), drawScope.mo455toDpu2uoSUM(SliderState.this.getThumbWidth$material3_release()), f7, f4, function22, function32, false);
                    }
                };
                composer2.updateRememberedValue(rememberedValue);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                CanvasKt.Canvas(rotate2, (Function1) rememberedValue, composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                }
                f8 = f7;
                z6 = z5;
                sliderColors4 = sliderColors3;
                modifier3 = modifier42;
                final float f112 = f4;
                final Function2<? super DrawScope, ? super Offset, Unit> function252 = function22;
                final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function332 = function32;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z2 = z;
            if ((i & 3072) == 0) {
            }
            if ((i & 24576) == 0) {
            }
            i5 = i2 & 32;
            if (i5 != 0) {
            }
            i6 = i2 & 64;
            if (i6 != 0) {
            }
            i7 = i2 & 128;
            if (i7 != 0) {
            }
            if ((i2 & 256) == 0) {
            }
            i3 |= i9;
            if ((38347923 & i3) == 38347922) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i12 != 0) {
            }
            if (i4 != 0) {
            }
            if ((i2 & 8) != 0) {
            }
            if ((i2 & 16) != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            if (i7 != 0) {
            }
            i8 = i3;
            Modifier modifier422 = modifier2;
            SliderColors sliderColors522 = sliderColors2;
            float f922 = f3;
            boolean z822 = z2;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            final long m2642trackColorWaAFU9c$material3_release32 = sliderColors522.m2642trackColorWaAFU9c$material3_release(z822, false);
            final long m2642trackColorWaAFU9c$material3_release222 = sliderColors522.m2642trackColorWaAFU9c$material3_release(z822, true);
            float f1022 = f4;
            Function2<? super DrawScope, ? super Offset, Unit> function2422 = function22;
            final long m2641tickColorWaAFU9c$material3_release32 = sliderColors522.m2641tickColorWaAFU9c$material3_release(z822, false);
            final long m2641tickColorWaAFU9c$material3_release222 = sliderColors522.m2641tickColorWaAFU9c$material3_release(z822, true);
            Modifier m916height3ABfNKs22 = SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(modifier422, 0.0f, 1, null), SliderKt.getTrackHeight());
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection22 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume22 = startRestartGroup.consume(localLayoutDirection22);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier rotate22 = RotateKt.rotate(m916height3ABfNKs22, consume22 != LayoutDirection.Rtl ? 180.0f : 0.0f);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -800841850, "CC(remember):Slider.kt#9igjgp");
            boolean changedInstance22 = ((3670016 & i8) != 1048576) | startRestartGroup.changedInstance(sliderState) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release32) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release222) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release32) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release222) | ((29360128 & i8) != 8388608);
            if (((57344 & i8) ^ 24576) <= 16384) {
            }
            if ((i8 & 24576) != 16384) {
            }
            z3 = true;
            z4 = changedInstance22 | z3 | ((458752 & i8) == 131072);
            rememberedValue = startRestartGroup.rememberedValue();
            if (z4) {
            }
            composer2 = startRestartGroup;
            sliderColors3 = sliderColors522;
            f7 = f922;
            function22 = function23;
            f4 = f1022;
            z5 = z822;
            rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$6$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope drawScope) {
                    SliderDefaults.INSTANCE.m2646drawTrackngJ0SCU(drawScope, SliderState.this.getTickFractions(), 0.0f, SliderState.this.getCoercedValueAsFraction$material3_release(), m2642trackColorWaAFU9c$material3_release32, m2642trackColorWaAFU9c$material3_release222, m2641tickColorWaAFU9c$material3_release32, m2641tickColorWaAFU9c$material3_release222, drawScope.mo455toDpu2uoSUM(SliderState.this.getTrackHeight$material3_release()), drawScope.mo456toDpu2uoSUM(0), drawScope.mo455toDpu2uoSUM(SliderState.this.getThumbWidth$material3_release()), f7, f4, function22, function32, false);
                }
            };
            composer2.updateRememberedValue(rememberedValue);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            CanvasKt.Canvas(rotate22, (Function1) rememberedValue, composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            f8 = f7;
            z6 = z5;
            sliderColors4 = sliderColors3;
            modifier3 = modifier422;
            final float f1122 = f4;
            final Function2<? super DrawScope, ? super Offset, Unit> function2522 = function22;
            final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3322 = function32;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 == 0) {
        }
        z2 = z;
        if ((i & 3072) == 0) {
        }
        if ((i & 24576) == 0) {
        }
        i5 = i2 & 32;
        if (i5 != 0) {
        }
        i6 = i2 & 64;
        if (i6 != 0) {
        }
        i7 = i2 & 128;
        if (i7 != 0) {
        }
        if ((i2 & 256) == 0) {
        }
        i3 |= i9;
        if ((38347923 & i3) == 38347922) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i12 != 0) {
        }
        if (i4 != 0) {
        }
        if ((i2 & 8) != 0) {
        }
        if ((i2 & 16) != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        if (i7 != 0) {
        }
        i8 = i3;
        Modifier modifier4222 = modifier2;
        SliderColors sliderColors5222 = sliderColors2;
        float f9222 = f3;
        boolean z8222 = z2;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        final long m2642trackColorWaAFU9c$material3_release322 = sliderColors5222.m2642trackColorWaAFU9c$material3_release(z8222, false);
        final long m2642trackColorWaAFU9c$material3_release2222 = sliderColors5222.m2642trackColorWaAFU9c$material3_release(z8222, true);
        float f10222 = f4;
        Function2<? super DrawScope, ? super Offset, Unit> function24222 = function22;
        final long m2641tickColorWaAFU9c$material3_release322 = sliderColors5222.m2641tickColorWaAFU9c$material3_release(z8222, false);
        final long m2641tickColorWaAFU9c$material3_release2222 = sliderColors5222.m2641tickColorWaAFU9c$material3_release(z8222, true);
        Modifier m916height3ABfNKs222 = SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4222, 0.0f, 1, null), SliderKt.getTrackHeight());
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection222 = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume222 = startRestartGroup.consume(localLayoutDirection222);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Modifier rotate222 = RotateKt.rotate(m916height3ABfNKs222, consume222 != LayoutDirection.Rtl ? 180.0f : 0.0f);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -800841850, "CC(remember):Slider.kt#9igjgp");
        boolean changedInstance222 = ((3670016 & i8) != 1048576) | startRestartGroup.changedInstance(sliderState) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release322) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release2222) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release322) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release2222) | ((29360128 & i8) != 8388608);
        if (((57344 & i8) ^ 24576) <= 16384) {
        }
        if ((i8 & 24576) != 16384) {
        }
        z3 = true;
        z4 = changedInstance222 | z3 | ((458752 & i8) == 131072);
        rememberedValue = startRestartGroup.rememberedValue();
        if (z4) {
        }
        composer2 = startRestartGroup;
        sliderColors3 = sliderColors5222;
        f7 = f9222;
        function22 = function23;
        f4 = f10222;
        z5 = z8222;
        rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$6$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope drawScope) {
                SliderDefaults.INSTANCE.m2646drawTrackngJ0SCU(drawScope, SliderState.this.getTickFractions(), 0.0f, SliderState.this.getCoercedValueAsFraction$material3_release(), m2642trackColorWaAFU9c$material3_release322, m2642trackColorWaAFU9c$material3_release2222, m2641tickColorWaAFU9c$material3_release322, m2641tickColorWaAFU9c$material3_release2222, drawScope.mo455toDpu2uoSUM(SliderState.this.getTrackHeight$material3_release()), drawScope.mo456toDpu2uoSUM(0), drawScope.mo455toDpu2uoSUM(SliderState.this.getThumbWidth$material3_release()), f7, f4, function22, function32, false);
            }
        };
        composer2.updateRememberedValue(rememberedValue);
        ComposerKt.sourceInformationMarkerEnd(composer2);
        CanvasKt.Canvas(rotate222, (Function1) rememberedValue, composer2, 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        f8 = f7;
        z6 = z5;
        sliderColors4 = sliderColors3;
        modifier3 = modifier4222;
        final float f11222 = f4;
        final Function2<? super DrawScope, ? super Offset, Unit> function25222 = function22;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function33222 = function32;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x005d  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `drawStopIndicator`, `drawTick`, `thumbTrackGapSize` and `trackInsideCornerSize`, see `LegacyRangeSliderSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "Track(rangeSliderState, modifier, colors, enabled, drawStopIndicator, drawTick, thumbTrackGapSize, trackInsideCornerSize)", imports = {}))
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ void Track(final RangeSliderState rangeSliderState, Modifier modifier, SliderColors sliderColors, boolean z, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        SliderColors sliderColors2;
        int i4;
        boolean z2;
        Modifier.Companion companion;
        SliderColors sliderColors3;
        int i5;
        boolean z3;
        float f;
        float f2;
        final SliderColors sliderColors4;
        final boolean z4;
        final Modifier modifier3;
        ScopeUpdateScope endRestartGroup;
        int i6;
        Composer startRestartGroup = composer.startRestartGroup(-1617869097);
        ComposerKt.sourceInformation(startRestartGroup, "C(Track)P(3,2)1187@52807L8,1190@52865L218:Slider.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changedInstance(rangeSliderState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i7 = i2 & 2;
        if (i7 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i & 384) != 0) {
                if ((i2 & 4) == 0) {
                    sliderColors2 = sliderColors;
                    if (startRestartGroup.changed(sliderColors2)) {
                        i6 = 256;
                        i3 |= i6;
                    }
                } else {
                    sliderColors2 = sliderColors;
                }
                i6 = 128;
                i3 |= i6;
            } else {
                sliderColors2 = sliderColors;
            }
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                z2 = z;
                i3 |= startRestartGroup.changed(z2) ? 2048 : 1024;
                if ((i2 & 16) != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    i3 |= startRestartGroup.changed(this) ? 16384 : 8192;
                }
                if ((i3 & 9363) == 9362 || !startRestartGroup.getSkipping()) {
                    startRestartGroup.startDefaults();
                    if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                        companion = i7 != 0 ? Modifier.INSTANCE : modifier2;
                        if ((i2 & 4) != 0) {
                            sliderColors3 = colors(startRestartGroup, (i3 >> 12) & 14);
                            i3 &= -897;
                        } else {
                            sliderColors3 = sliderColors2;
                        }
                        if (i4 != 0) {
                            i5 = i3;
                            z3 = true;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1617869097, i5, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1189)");
                            }
                            f = SliderKt.ThumbTrackGapSize;
                            f2 = SliderKt.TrackInsideCornerSize;
                            Modifier modifier4 = companion;
                            sliderColors4 = sliderColors3;
                            m2649Track4EFweAY(rangeSliderState, modifier4, z3, sliderColors4, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, f, f2, startRestartGroup, (i5 & 14) | 14155776 | (i5 & 112) | ((i5 >> 3) & 896) | ((i5 << 3) & 7168) | ((i5 << 12) & 234881024), 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            z4 = z3;
                            modifier3 = modifier4;
                        }
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        companion = modifier2;
                        sliderColors3 = sliderColors2;
                    }
                    i5 = i3;
                    z3 = z2;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    f = SliderKt.ThumbTrackGapSize;
                    f2 = SliderKt.TrackInsideCornerSize;
                    Modifier modifier42 = companion;
                    sliderColors4 = sliderColors3;
                    m2649Track4EFweAY(rangeSliderState, modifier42, z3, sliderColors4, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, f, f2, startRestartGroup, (i5 & 14) | 14155776 | (i5 & 112) | ((i5 >> 3) & 896) | ((i5 << 3) & 7168) | ((i5 << 12) & 234881024), 48);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    z4 = z3;
                    modifier3 = modifier42;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    sliderColors4 = sliderColors2;
                    z4 = z2;
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$8
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i8) {
                            SliderDefaults.this.Track(rangeSliderState, modifier3, sliderColors4, z4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                    return;
                }
                return;
            }
            z2 = z;
            if ((i2 & 16) != 0) {
            }
            if ((i3 & 9363) == 9362) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i7 != 0) {
            }
            if ((i2 & 4) != 0) {
            }
            if (i4 != 0) {
            }
            i5 = i3;
            z3 = z2;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            f = SliderKt.ThumbTrackGapSize;
            f2 = SliderKt.TrackInsideCornerSize;
            Modifier modifier422 = companion;
            sliderColors4 = sliderColors3;
            m2649Track4EFweAY(rangeSliderState, modifier422, z3, sliderColors4, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, f, f2, startRestartGroup, (i5 & 14) | 14155776 | (i5 & 112) | ((i5 >> 3) & 896) | ((i5 << 3) & 7168) | ((i5 << 12) & 234881024), 48);
            if (ComposerKt.isTraceInProgress()) {
            }
            z4 = z3;
            modifier3 = modifier422;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        if ((i & 384) != 0) {
        }
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        z2 = z;
        if ((i2 & 16) != 0) {
        }
        if ((i3 & 9363) == 9362) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i7 != 0) {
        }
        if ((i2 & 4) != 0) {
        }
        if (i4 != 0) {
        }
        i5 = i3;
        z3 = z2;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        f = SliderKt.ThumbTrackGapSize;
        f2 = SliderKt.TrackInsideCornerSize;
        Modifier modifier4222 = companion;
        sliderColors4 = sliderColors3;
        m2649Track4EFweAY(rangeSliderState, modifier4222, z3, sliderColors4, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, f, f2, startRestartGroup, (i5 & 14) | 14155776 | (i5 & 112) | ((i5 >> 3) & 896) | ((i5 << 3) & 7168) | ((i5 << 12) & 234881024), 48);
        if (ComposerKt.isTraceInProgress()) {
        }
        z4 = z3;
        modifier3 = modifier4222;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:79:0x027a, code lost:
    
        if (r5.changed(r2) == false) goto L170;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0268  */
    /* renamed from: Track-4EFweAY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m2649Track4EFweAY(final RangeSliderState rangeSliderState, Modifier modifier, boolean z, SliderColors sliderColors, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, float f, float f2, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        final boolean z2;
        final SliderColors sliderColors2;
        final Function2<? super DrawScope, ? super Offset, Unit> function22;
        int i5;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function32;
        int i6;
        float f3;
        int i7;
        final float f4;
        float f5;
        float f6;
        int i8;
        Function2<? super DrawScope, ? super Offset, Unit> function23;
        boolean z3;
        boolean z4;
        Object rememberedValue;
        Composer composer2;
        SliderColors sliderColors3;
        final float f7;
        boolean z5;
        final float f8;
        final boolean z6;
        final SliderColors sliderColors4;
        final Modifier modifier3;
        ScopeUpdateScope endRestartGroup;
        int i9;
        int i10;
        int i11;
        Composer startRestartGroup = composer.startRestartGroup(-541824132);
        ComposerKt.sourceInformation(startRestartGroup, "C(Track)P(5,4,3!3,6:c#ui.unit.Dp,7:c#ui.unit.Dp)1222@54320L8,1223@54389L232,1245@55370L7,1246@55426L706,1241@55223L909:Slider.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changedInstance(rangeSliderState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i12 = i2 & 2;
        if (i12 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i4 = i2 & 4;
            if (i4 == 0) {
                i3 |= 384;
            } else if ((i & 384) == 0) {
                z2 = z;
                i3 |= startRestartGroup.changed(z2) ? 256 : 128;
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        sliderColors2 = sliderColors;
                        if (startRestartGroup.changed(sliderColors2)) {
                            i11 = 2048;
                            i3 |= i11;
                        }
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i11 = 1024;
                    i3 |= i11;
                } else {
                    sliderColors2 = sliderColors;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        function22 = function2;
                        if (startRestartGroup.changedInstance(function22)) {
                            i10 = 16384;
                            i3 |= i10;
                        }
                    } else {
                        function22 = function2;
                    }
                    i10 = 8192;
                    i3 |= i10;
                } else {
                    function22 = function2;
                }
                i5 = i2 & 32;
                if (i5 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function32 = function3;
                } else {
                    function32 = function3;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        i3 |= startRestartGroup.changedInstance(function32) ? 131072 : 65536;
                    }
                }
                i6 = i2 & 64;
                if (i6 != 0) {
                    i3 |= 1572864;
                    f3 = f;
                } else {
                    f3 = f;
                    if ((i & 1572864) == 0) {
                        i3 |= startRestartGroup.changed(f3) ? 1048576 : 524288;
                    }
                }
                i7 = i2 & 128;
                if (i7 != 0) {
                    i3 |= 12582912;
                    f4 = f2;
                } else {
                    f4 = f2;
                    if ((i & 12582912) == 0) {
                        i3 |= startRestartGroup.changed(f4) ? 8388608 : 4194304;
                    }
                }
                if ((i2 & 256) == 0) {
                    i9 = (i & 100663296) == 0 ? startRestartGroup.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432 : 100663296;
                    if ((38347923 & i3) == 38347922 || !startRestartGroup.getSkipping()) {
                        startRestartGroup.startDefaults();
                        if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                            if (i12 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 8) != 0) {
                                SliderColors colors = colors(startRestartGroup, (i3 >> 24) & 14);
                                i3 &= -7169;
                                sliderColors2 = colors;
                            }
                            if ((i2 & 16) != 0) {
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -800729349, "CC(remember):Slider.kt#9igjgp");
                                boolean z7 = ((((i3 & 7168) ^ 3072) > 2048 && startRestartGroup.changed(sliderColors2)) || (i3 & 3072) == 2048) | ((i3 & 896) == 256);
                                Object rememberedValue2 = startRestartGroup.rememberedValue();
                                if (z7 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue2 = (Function2) new Function2<DrawScope, Offset, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$9$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset) {
                                            m2657invokeUv8p0NA(drawScope, offset.m4353unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke-Uv8p0NA, reason: not valid java name */
                                        public final void m2657invokeUv8p0NA(DrawScope drawScope, long j) {
                                            SliderDefaults.INSTANCE.m2645drawStopIndicatorx3O1jOs(drawScope, j, SliderDefaults.INSTANCE.m2653getTrackStopIndicatorSizeD9Ej5fM(), SliderColors.this.m2642trackColorWaAFU9c$material3_release(z2, true));
                                        }
                                    };
                                    startRestartGroup.updateRememberedValue(rememberedValue2);
                                }
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                i3 &= -57345;
                                function22 = (Function2) rememberedValue2;
                            }
                            if (i5 != 0) {
                                function32 = new Function3<DrawScope, Offset, Color, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$10
                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope, Offset offset, Color color) {
                                        m2654invokewPWG1Vc(drawScope, offset.m4353unboximpl(), color.m4625unboximpl());
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke-wPWG1Vc, reason: not valid java name */
                                    public final void m2654invokewPWG1Vc(DrawScope drawScope, long j, long j2) {
                                        SliderDefaults.INSTANCE.m2645drawStopIndicatorx3O1jOs(drawScope, j, SliderDefaults.INSTANCE.m2652getTickSizeD9Ej5fM(), j2);
                                    }
                                };
                            }
                            if (i6 != 0) {
                                f6 = SliderKt.ThumbTrackGapSize;
                                f3 = f6;
                            }
                            if (i7 != 0) {
                                f5 = SliderKt.TrackInsideCornerSize;
                                f4 = f5;
                            }
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                        }
                        i8 = i3;
                        Modifier modifier4 = modifier2;
                        SliderColors sliderColors5 = sliderColors2;
                        float f9 = f3;
                        boolean z8 = z2;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-541824132, i8, -1, "androidx.compose.material3.SliderDefaults.Track (Slider.kt:1236)");
                        }
                        final long m2642trackColorWaAFU9c$material3_release = sliderColors5.m2642trackColorWaAFU9c$material3_release(z8, false);
                        final long m2642trackColorWaAFU9c$material3_release2 = sliderColors5.m2642trackColorWaAFU9c$material3_release(z8, true);
                        float f10 = f4;
                        Function2<? super DrawScope, ? super Offset, Unit> function24 = function22;
                        final long m2641tickColorWaAFU9c$material3_release = sliderColors5.m2641tickColorWaAFU9c$material3_release(z8, false);
                        final long m2641tickColorWaAFU9c$material3_release2 = sliderColors5.m2641tickColorWaAFU9c$material3_release(z8, true);
                        Modifier m916height3ABfNKs = SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, null), SliderKt.getTrackHeight());
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object consume = startRestartGroup.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        Modifier rotate = RotateKt.rotate(m916height3ABfNKs, consume != LayoutDirection.Rtl ? 180.0f : 0.0f);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -800695691, "CC(remember):Slider.kt#9igjgp");
                        boolean changedInstance = ((3670016 & i8) != 1048576) | startRestartGroup.changedInstance(rangeSliderState) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release2) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release2) | ((29360128 & i8) != 8388608);
                        if (((57344 & i8) ^ 24576) <= 16384) {
                            function23 = function24;
                        } else {
                            function23 = function24;
                        }
                        if ((i8 & 24576) != 16384) {
                            z3 = false;
                            z4 = changedInstance | z3 | ((458752 & i8) == 131072);
                            rememberedValue = startRestartGroup.rememberedValue();
                            if (!z4 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                composer2 = startRestartGroup;
                                sliderColors3 = sliderColors5;
                                f7 = f9;
                                function22 = function23;
                                f4 = f10;
                                z5 = z8;
                                rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$11$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                                        invoke2(drawScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(DrawScope drawScope) {
                                        SliderDefaults.INSTANCE.m2646drawTrackngJ0SCU(drawScope, RangeSliderState.this.getTickFractions(), RangeSliderState.this.getCoercedActiveRangeStartAsFraction$material3_release(), RangeSliderState.this.getCoercedActiveRangeEndAsFraction$material3_release(), m2642trackColorWaAFU9c$material3_release, m2642trackColorWaAFU9c$material3_release2, m2641tickColorWaAFU9c$material3_release, m2641tickColorWaAFU9c$material3_release2, drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getTrackHeight$material3_release()), drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getStartThumbWidth$material3_release()), drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getEndThumbWidth$material3_release()), f7, f4, function22, function32, true);
                                    }
                                };
                                composer2.updateRememberedValue(rememberedValue);
                            } else {
                                f7 = f9;
                                f4 = f10;
                                function22 = function23;
                                z5 = z8;
                                composer2 = startRestartGroup;
                                sliderColors3 = sliderColors5;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            CanvasKt.Canvas(rotate, (Function1) rememberedValue, composer2, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            f8 = f7;
                            z6 = z5;
                            sliderColors4 = sliderColors3;
                            modifier3 = modifier4;
                        }
                        z3 = true;
                        z4 = changedInstance | z3 | ((458752 & i8) == 131072);
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (z4) {
                        }
                        composer2 = startRestartGroup;
                        sliderColors3 = sliderColors5;
                        f7 = f9;
                        function22 = function23;
                        f4 = f10;
                        z5 = z8;
                        rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$11$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                                invoke2(drawScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(DrawScope drawScope) {
                                SliderDefaults.INSTANCE.m2646drawTrackngJ0SCU(drawScope, RangeSliderState.this.getTickFractions(), RangeSliderState.this.getCoercedActiveRangeStartAsFraction$material3_release(), RangeSliderState.this.getCoercedActiveRangeEndAsFraction$material3_release(), m2642trackColorWaAFU9c$material3_release, m2642trackColorWaAFU9c$material3_release2, m2641tickColorWaAFU9c$material3_release, m2641tickColorWaAFU9c$material3_release2, drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getTrackHeight$material3_release()), drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getStartThumbWidth$material3_release()), drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getEndThumbWidth$material3_release()), f7, f4, function22, function32, true);
                            }
                        };
                        composer2.updateRememberedValue(rememberedValue);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        CanvasKt.Canvas(rotate, (Function1) rememberedValue, composer2, 0);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        f8 = f7;
                        z6 = z5;
                        sliderColors4 = sliderColors3;
                        modifier3 = modifier4;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        composer2 = startRestartGroup;
                        modifier3 = modifier2;
                        sliderColors4 = sliderColors2;
                        f8 = f3;
                        z6 = z2;
                    }
                    final float f11 = f4;
                    final Function2<? super DrawScope, ? super Offset, Unit> function25 = function22;
                    final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function33 = function32;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$12
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
                                SliderDefaults.this.m2649Track4EFweAY(rangeSliderState, modifier3, z6, sliderColors4, function25, function33, f8, f11, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                        return;
                    }
                    return;
                }
                i3 |= i9;
                if ((38347923 & i3) == 38347922) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i12 != 0) {
                }
                if (i4 != 0) {
                }
                if ((i2 & 8) != 0) {
                }
                if ((i2 & 16) != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                if (i7 != 0) {
                }
                i8 = i3;
                Modifier modifier42 = modifier2;
                SliderColors sliderColors52 = sliderColors2;
                float f92 = f3;
                boolean z82 = z2;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                final long m2642trackColorWaAFU9c$material3_release3 = sliderColors52.m2642trackColorWaAFU9c$material3_release(z82, false);
                final long m2642trackColorWaAFU9c$material3_release22 = sliderColors52.m2642trackColorWaAFU9c$material3_release(z82, true);
                float f102 = f4;
                Function2<? super DrawScope, ? super Offset, Unit> function242 = function22;
                final long m2641tickColorWaAFU9c$material3_release3 = sliderColors52.m2641tickColorWaAFU9c$material3_release(z82, false);
                final long m2641tickColorWaAFU9c$material3_release22 = sliderColors52.m2641tickColorWaAFU9c$material3_release(z82, true);
                Modifier m916height3ABfNKs2 = SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(modifier42, 0.0f, 1, null), SliderKt.getTrackHeight());
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume2 = startRestartGroup.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Modifier rotate2 = RotateKt.rotate(m916height3ABfNKs2, consume2 != LayoutDirection.Rtl ? 180.0f : 0.0f);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -800695691, "CC(remember):Slider.kt#9igjgp");
                boolean changedInstance2 = ((3670016 & i8) != 1048576) | startRestartGroup.changedInstance(rangeSliderState) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release3) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release22) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release3) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release22) | ((29360128 & i8) != 8388608);
                if (((57344 & i8) ^ 24576) <= 16384) {
                }
                if ((i8 & 24576) != 16384) {
                }
                z3 = true;
                z4 = changedInstance2 | z3 | ((458752 & i8) == 131072);
                rememberedValue = startRestartGroup.rememberedValue();
                if (z4) {
                }
                composer2 = startRestartGroup;
                sliderColors3 = sliderColors52;
                f7 = f92;
                function22 = function23;
                f4 = f102;
                z5 = z82;
                rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$11$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope drawScope) {
                        SliderDefaults.INSTANCE.m2646drawTrackngJ0SCU(drawScope, RangeSliderState.this.getTickFractions(), RangeSliderState.this.getCoercedActiveRangeStartAsFraction$material3_release(), RangeSliderState.this.getCoercedActiveRangeEndAsFraction$material3_release(), m2642trackColorWaAFU9c$material3_release3, m2642trackColorWaAFU9c$material3_release22, m2641tickColorWaAFU9c$material3_release3, m2641tickColorWaAFU9c$material3_release22, drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getTrackHeight$material3_release()), drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getStartThumbWidth$material3_release()), drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getEndThumbWidth$material3_release()), f7, f4, function22, function32, true);
                    }
                };
                composer2.updateRememberedValue(rememberedValue);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                CanvasKt.Canvas(rotate2, (Function1) rememberedValue, composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                }
                f8 = f7;
                z6 = z5;
                sliderColors4 = sliderColors3;
                modifier3 = modifier42;
                final float f112 = f4;
                final Function2<? super DrawScope, ? super Offset, Unit> function252 = function22;
                final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function332 = function32;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z2 = z;
            if ((i & 3072) == 0) {
            }
            if ((i & 24576) == 0) {
            }
            i5 = i2 & 32;
            if (i5 != 0) {
            }
            i6 = i2 & 64;
            if (i6 != 0) {
            }
            i7 = i2 & 128;
            if (i7 != 0) {
            }
            if ((i2 & 256) == 0) {
            }
            i3 |= i9;
            if ((38347923 & i3) == 38347922) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i12 != 0) {
            }
            if (i4 != 0) {
            }
            if ((i2 & 8) != 0) {
            }
            if ((i2 & 16) != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            if (i7 != 0) {
            }
            i8 = i3;
            Modifier modifier422 = modifier2;
            SliderColors sliderColors522 = sliderColors2;
            float f922 = f3;
            boolean z822 = z2;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            final long m2642trackColorWaAFU9c$material3_release32 = sliderColors522.m2642trackColorWaAFU9c$material3_release(z822, false);
            final long m2642trackColorWaAFU9c$material3_release222 = sliderColors522.m2642trackColorWaAFU9c$material3_release(z822, true);
            float f1022 = f4;
            Function2<? super DrawScope, ? super Offset, Unit> function2422 = function22;
            final long m2641tickColorWaAFU9c$material3_release32 = sliderColors522.m2641tickColorWaAFU9c$material3_release(z822, false);
            final long m2641tickColorWaAFU9c$material3_release222 = sliderColors522.m2641tickColorWaAFU9c$material3_release(z822, true);
            Modifier m916height3ABfNKs22 = SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(modifier422, 0.0f, 1, null), SliderKt.getTrackHeight());
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection22 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume22 = startRestartGroup.consume(localLayoutDirection22);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier rotate22 = RotateKt.rotate(m916height3ABfNKs22, consume22 != LayoutDirection.Rtl ? 180.0f : 0.0f);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -800695691, "CC(remember):Slider.kt#9igjgp");
            boolean changedInstance22 = ((3670016 & i8) != 1048576) | startRestartGroup.changedInstance(rangeSliderState) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release32) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release222) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release32) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release222) | ((29360128 & i8) != 8388608);
            if (((57344 & i8) ^ 24576) <= 16384) {
            }
            if ((i8 & 24576) != 16384) {
            }
            z3 = true;
            z4 = changedInstance22 | z3 | ((458752 & i8) == 131072);
            rememberedValue = startRestartGroup.rememberedValue();
            if (z4) {
            }
            composer2 = startRestartGroup;
            sliderColors3 = sliderColors522;
            f7 = f922;
            function22 = function23;
            f4 = f1022;
            z5 = z822;
            rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$11$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope drawScope) {
                    SliderDefaults.INSTANCE.m2646drawTrackngJ0SCU(drawScope, RangeSliderState.this.getTickFractions(), RangeSliderState.this.getCoercedActiveRangeStartAsFraction$material3_release(), RangeSliderState.this.getCoercedActiveRangeEndAsFraction$material3_release(), m2642trackColorWaAFU9c$material3_release32, m2642trackColorWaAFU9c$material3_release222, m2641tickColorWaAFU9c$material3_release32, m2641tickColorWaAFU9c$material3_release222, drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getTrackHeight$material3_release()), drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getStartThumbWidth$material3_release()), drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getEndThumbWidth$material3_release()), f7, f4, function22, function32, true);
                }
            };
            composer2.updateRememberedValue(rememberedValue);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            CanvasKt.Canvas(rotate22, (Function1) rememberedValue, composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            f8 = f7;
            z6 = z5;
            sliderColors4 = sliderColors3;
            modifier3 = modifier422;
            final float f1122 = f4;
            final Function2<? super DrawScope, ? super Offset, Unit> function2522 = function22;
            final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3322 = function32;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 == 0) {
        }
        z2 = z;
        if ((i & 3072) == 0) {
        }
        if ((i & 24576) == 0) {
        }
        i5 = i2 & 32;
        if (i5 != 0) {
        }
        i6 = i2 & 64;
        if (i6 != 0) {
        }
        i7 = i2 & 128;
        if (i7 != 0) {
        }
        if ((i2 & 256) == 0) {
        }
        i3 |= i9;
        if ((38347923 & i3) == 38347922) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i12 != 0) {
        }
        if (i4 != 0) {
        }
        if ((i2 & 8) != 0) {
        }
        if ((i2 & 16) != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        if (i7 != 0) {
        }
        i8 = i3;
        Modifier modifier4222 = modifier2;
        SliderColors sliderColors5222 = sliderColors2;
        float f9222 = f3;
        boolean z8222 = z2;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        final long m2642trackColorWaAFU9c$material3_release322 = sliderColors5222.m2642trackColorWaAFU9c$material3_release(z8222, false);
        final long m2642trackColorWaAFU9c$material3_release2222 = sliderColors5222.m2642trackColorWaAFU9c$material3_release(z8222, true);
        float f10222 = f4;
        Function2<? super DrawScope, ? super Offset, Unit> function24222 = function22;
        final long m2641tickColorWaAFU9c$material3_release322 = sliderColors5222.m2641tickColorWaAFU9c$material3_release(z8222, false);
        final long m2641tickColorWaAFU9c$material3_release2222 = sliderColors5222.m2641tickColorWaAFU9c$material3_release(z8222, true);
        Modifier m916height3ABfNKs222 = SizeKt.m916height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4222, 0.0f, 1, null), SliderKt.getTrackHeight());
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection222 = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume222 = startRestartGroup.consume(localLayoutDirection222);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Modifier rotate222 = RotateKt.rotate(m916height3ABfNKs222, consume222 != LayoutDirection.Rtl ? 180.0f : 0.0f);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -800695691, "CC(remember):Slider.kt#9igjgp");
        boolean changedInstance222 = ((3670016 & i8) != 1048576) | startRestartGroup.changedInstance(rangeSliderState) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release322) | startRestartGroup.changed(m2642trackColorWaAFU9c$material3_release2222) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release322) | startRestartGroup.changed(m2641tickColorWaAFU9c$material3_release2222) | ((29360128 & i8) != 8388608);
        if (((57344 & i8) ^ 24576) <= 16384) {
        }
        if ((i8 & 24576) != 16384) {
        }
        z3 = true;
        z4 = changedInstance222 | z3 | ((458752 & i8) == 131072);
        rememberedValue = startRestartGroup.rememberedValue();
        if (z4) {
        }
        composer2 = startRestartGroup;
        sliderColors3 = sliderColors5222;
        f7 = f9222;
        function22 = function23;
        f4 = f10222;
        z5 = z8222;
        rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.SliderDefaults$Track$11$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope drawScope) {
                SliderDefaults.INSTANCE.m2646drawTrackngJ0SCU(drawScope, RangeSliderState.this.getTickFractions(), RangeSliderState.this.getCoercedActiveRangeStartAsFraction$material3_release(), RangeSliderState.this.getCoercedActiveRangeEndAsFraction$material3_release(), m2642trackColorWaAFU9c$material3_release322, m2642trackColorWaAFU9c$material3_release2222, m2641tickColorWaAFU9c$material3_release322, m2641tickColorWaAFU9c$material3_release2222, drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getTrackHeight$material3_release()), drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getStartThumbWidth$material3_release()), drawScope.mo455toDpu2uoSUM(RangeSliderState.this.getEndThumbWidth$material3_release()), f7, f4, function22, function32, true);
            }
        };
        composer2.updateRememberedValue(rememberedValue);
        ComposerKt.sourceInformationMarkerEnd(composer2);
        CanvasKt.Canvas(rotate222, (Function1) rememberedValue, composer2, 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        f8 = f7;
        z6 = z5;
        sliderColors4 = sliderColors3;
        modifier3 = modifier4222;
        final float f11222 = f4;
        final Function2<? super DrawScope, ? super Offset, Unit> function25222 = function22;
        final Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function33222 = function32;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawTrack-ngJ0SCU, reason: not valid java name */
    public final void m2646drawTrackngJ0SCU(DrawScope drawScope, float[] fArr, float f, float f2, long j, long j2, long j3, long j4, float f3, float f4, float f5, float f6, float f7, Function2<? super DrawScope, ? super Offset, Unit> function2, Function3<? super DrawScope, ? super Offset, ? super Color, Unit> function3, boolean z) {
        float f8;
        float f9;
        float f10;
        int i;
        float f11;
        float f12;
        long Offset = OffsetKt.Offset(0.0f, Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
        long Offset2 = OffsetKt.Offset(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
        float f13 = drawScope.mo459toPx0680j_4(f3);
        long Offset3 = OffsetKt.Offset(Offset.m4343getXimpl(Offset) + ((Offset.m4343getXimpl(Offset2) - Offset.m4343getXimpl(Offset)) * f2), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
        long Offset4 = OffsetKt.Offset(Offset.m4343getXimpl(Offset) + ((Offset.m4343getXimpl(Offset2) - Offset.m4343getXimpl(Offset)) * f), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
        float f14 = 2;
        float f15 = f13 / f14;
        float f16 = drawScope.mo459toPx0680j_4(f7);
        if (Dp.m7398compareTo0680j_4(f6, Dp.m7399constructorimpl(0)) > 0) {
            f8 = (drawScope.mo459toPx0680j_4(f4) / f14) + drawScope.mo459toPx0680j_4(f6);
            f9 = (drawScope.mo459toPx0680j_4(f5) / f14) + drawScope.mo459toPx0680j_4(f6);
        } else {
            f8 = 0.0f;
            f9 = 0.0f;
        }
        if (!z || Offset.m4343getXimpl(Offset4) <= Offset.m4343getXimpl(Offset) + f8 + f15) {
            f10 = f13;
            i = 0;
        } else {
            float m4343getXimpl = Offset.m4343getXimpl(Offset);
            f10 = f13;
            i = 0;
            m2647drawTrackPathCx2C_VA(drawScope, Offset.INSTANCE.m4359getZeroF1C5BW0(), androidx.compose.ui.geometry.SizeKt.Size((Offset.m4343getXimpl(Offset4) - f8) - m4343getXimpl, f13), j, f15, f16);
            if (function2 != null) {
                function2.invoke(drawScope, Offset.m4332boximpl(OffsetKt.Offset(m4343getXimpl + f15, Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()))));
            }
        }
        if (Offset.m4343getXimpl(Offset3) < (Offset.m4343getXimpl(Offset2) - f9) - f15) {
            float m4343getXimpl2 = Offset.m4343getXimpl(Offset3) + f9;
            float m4343getXimpl3 = Offset.m4343getXimpl(Offset2);
            float f17 = f10;
            f12 = f17;
            m2647drawTrackPathCx2C_VA(drawScope, OffsetKt.Offset(m4343getXimpl2, 0.0f), androidx.compose.ui.geometry.SizeKt.Size(m4343getXimpl3 - m4343getXimpl2, f17), j, f16, f15);
            f11 = f15;
            f16 = f16;
            if (function2 != null) {
                function2.invoke(drawScope, Offset.m4332boximpl(OffsetKt.Offset(m4343getXimpl3 - f11, Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()))));
            }
        } else {
            f11 = f15;
            f12 = f10;
        }
        float m4343getXimpl4 = z ? Offset.m4343getXimpl(Offset4) + f8 : 0.0f;
        float m4343getXimpl5 = Offset.m4343getXimpl(Offset3) - f9;
        float f18 = z ? f16 : f11;
        float f19 = m4343getXimpl5 - m4343getXimpl4;
        if (f19 > f18) {
            m2647drawTrackPathCx2C_VA(drawScope, OffsetKt.Offset(m4343getXimpl4, 0.0f), androidx.compose.ui.geometry.SizeKt.Size(f19, f12), j2, f18, f16);
        }
        long Offset5 = OffsetKt.Offset(Offset.m4343getXimpl(Offset) + f11, Offset.m4344getYimpl(Offset));
        long Offset6 = OffsetKt.Offset(Offset.m4343getXimpl(Offset2) - f11, Offset.m4344getYimpl(Offset2));
        ClosedFloatingPointRange<Float> rangeTo = RangesKt.rangeTo(Offset.m4343getXimpl(Offset4) - f8, Offset.m4343getXimpl(Offset4) + f8);
        ClosedFloatingPointRange<Float> rangeTo2 = RangesKt.rangeTo(Offset.m4343getXimpl(Offset3) - f9, Offset.m4343getXimpl(Offset3) + f9);
        int length = fArr.length;
        int i2 = i;
        int i3 = i2;
        while (i2 < length) {
            float f20 = fArr[i2];
            int i4 = i3 + 1;
            int i5 = 1;
            if (function2 == null || ((!z || i3 != 0) && i3 != fArr.length - 1)) {
                if (f20 <= f2 && f20 >= f) {
                    i5 = i;
                }
                long Offset7 = OffsetKt.Offset(Offset.m4343getXimpl(OffsetKt.m4366lerpWko1d7g(Offset5, Offset6, f20)), Offset.m4344getYimpl(drawScope.mo5120getCenterF1C5BW0()));
                if ((!z || !rangeTo.contains(Float.valueOf(Offset.m4343getXimpl(Offset7)))) && !rangeTo2.contains(Float.valueOf(Offset.m4343getXimpl(Offset7)))) {
                    function3.invoke(drawScope, Offset.m4332boximpl(Offset7), Color.m4605boximpl(i5 != 0 ? j3 : j4));
                    i2++;
                    i3 = i4;
                }
            }
            i2++;
            i3 = i4;
        }
    }

    /* renamed from: drawTrackPath-Cx2C_VA, reason: not valid java name */
    private final void m2647drawTrackPathCx2C_VA(DrawScope drawScope, long j, long j2, long j3, float f, float f2) {
        long CornerRadius = CornerRadiusKt.CornerRadius(f, f);
        long CornerRadius2 = CornerRadiusKt.CornerRadius(f2, f2);
        RoundRect m4395RoundRectZAM2FJo = RoundRectKt.m4395RoundRectZAM2FJo(RectKt.m4383Recttz77jQw(OffsetKt.Offset(Offset.m4343getXimpl(j), 0.0f), androidx.compose.ui.geometry.SizeKt.Size(Size.m4412getWidthimpl(j2), Size.m4409getHeightimpl(j2))), CornerRadius, CornerRadius2, CornerRadius2, CornerRadius);
        Path path = trackPath;
        Path.CC.addRoundRect$default(path, m4395RoundRectZAM2FJo, null, 2, null);
        DrawScope.CC.m5202drawPathLG529CI$default(drawScope, path, j3, 0.0f, null, null, 0, 60, null);
        path.rewind();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawStopIndicator-x3O1jOs, reason: not valid java name */
    public final void m2645drawStopIndicatorx3O1jOs(DrawScope drawScope, long offset, float size, long color) {
        DrawScope.CC.m5193drawCircleVaOC9Bg$default(drawScope, color, drawScope.mo459toPx0680j_4(size) / 2.0f, offset, 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
    }

    /* renamed from: getTrackStopIndicatorSize-D9Ej5fM, reason: not valid java name */
    public final float m2653getTrackStopIndicatorSizeD9Ej5fM() {
        return TrackStopIndicatorSize;
    }

    /* renamed from: getTickSize-D9Ej5fM, reason: not valid java name */
    public final float m2652getTickSizeD9Ej5fM() {
        return TickSize;
    }
}
