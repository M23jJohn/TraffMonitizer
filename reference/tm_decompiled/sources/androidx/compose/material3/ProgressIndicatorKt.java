package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.tokens.ProgressIndicatorTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.app.NotificationCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

/* compiled from: ProgressIndicator.kt */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0010\u001aR\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\\\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00102\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b3\u00104\u001a0\u0010%\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001aD\u0010%\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/H\u0007ø\u0001\u0000¢\u0006\u0004\b7\u00108\u001a8\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00012\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001aL\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00012\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u0010;\u001aH\u0010<\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/H\u0007ø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001am\u0010<\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00102\u001a\u00020\u00052\u0019\b\u0002\u0010?\u001a\u0013\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020&0@¢\u0006\u0002\bBH\u0007ø\u0001\u0000¢\u0006\u0004\bC\u0010D\u001a0\u0010<\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+H\u0007ø\u0001\u0000¢\u0006\u0004\bE\u0010F\u001a:\u0010<\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/H\u0007ø\u0001\u0000¢\u0006\u0004\bG\u0010H\u001aD\u0010<\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00102\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\bI\u0010J\u001a8\u0010<\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00012\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+H\u0007ø\u0001\u0000¢\u0006\u0004\bK\u0010L\u001aB\u0010<\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00012\b\b\u0002\u0010)\u001a\u00020\u00132\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020/H\u0007ø\u0001\u0000¢\u0006\u0004\b=\u0010M\u001a6\u0010N\u001a\u00020&*\u00020A2\u0006\u0010O\u001a\u00020\u00012\u0006\u0010P\u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\u0006\u0010Q\u001a\u00020RH\u0002ø\u0001\u0000¢\u0006\u0004\bS\u0010T\u001a&\u0010U\u001a\u00020&*\u00020A2\u0006\u0010*\u001a\u00020+2\u0006\u0010Q\u001a\u00020RH\u0002ø\u0001\u0000¢\u0006\u0004\bV\u0010W\u001a6\u0010X\u001a\u00020&*\u00020A2\u0006\u0010O\u001a\u00020\u00012\u0006\u0010P\u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\u0006\u0010Q\u001a\u00020RH\u0002ø\u0001\u0000¢\u0006\u0004\bY\u0010T\u001a>\u0010Z\u001a\u00020&*\u00020A2\u0006\u0010O\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\u0006\u0010Q\u001a\u00020RH\u0002ø\u0001\u0000¢\u0006\u0004\b[\u0010\\\u001a>\u0010]\u001a\u00020&*\u00020A2\u0006\u0010^\u001a\u00020\u00012\u0006\u0010_\u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00012\u0006\u0010.\u001a\u00020/H\u0002ø\u0001\u0000¢\u0006\u0004\b`\u0010a\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\"\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0016\u001a\u00020\u0005X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0017\u0010\u0007\"\u0016\u0010\u0018\u001a\u00020\u0005X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0019\u0010\u0007\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010!\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010#\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u000e\u0010$\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006b"}, d2 = {"BaseRotationAngle", "", "CircularEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "CircularIndicatorDiameter", "Landroidx/compose/ui/unit/Dp;", "getCircularIndicatorDiameter", "()F", "F", "FirstLineHeadDelay", "", "FirstLineHeadDuration", "FirstLineHeadEasing", "FirstLineTailDelay", "FirstLineTailDuration", "FirstLineTailEasing", "HeadAndTailAnimationDuration", "HeadAndTailDelayDuration", "IncreaseSemanticsBounds", "Landroidx/compose/ui/Modifier;", "JumpRotationAngle", "LinearAnimationDuration", "LinearIndicatorHeight", "getLinearIndicatorHeight", "LinearIndicatorWidth", "getLinearIndicatorWidth", "RotationAngleOffset", "RotationDuration", "RotationsPerCycle", "SecondLineHeadDelay", "SecondLineHeadDuration", "SecondLineHeadEasing", "SecondLineTailDelay", "SecondLineTailDuration", "SecondLineTailEasing", "SemanticsBoundsPadding", "StartAngleOffset", "CircularProgressIndicator", "", NotificationCompat.CATEGORY_PROGRESS, "Lkotlin/Function0;", "modifier", "color", "Landroidx/compose/ui/graphics/Color;", "strokeWidth", "trackColor", "strokeCap", "Landroidx/compose/ui/graphics/StrokeCap;", "CircularProgressIndicator-DUhRLBM", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "gapSize", "CircularProgressIndicator-IyT6zlY", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JFJIFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-aM-cp0Q", "(Landroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-LxG7B9w", "(Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-MBs18nI", "(FLandroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "(FLandroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator", "LinearProgressIndicator-_5eSR-E", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "drawStopIndicator", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lkotlin/ExtensionFunctionType;", "LinearProgressIndicator-GJbTh5U", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JJIFLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-RIQooxk", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-2cYBFYY", "(Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-rIrjwxo", "(Landroidx/compose/ui/Modifier;JJIFLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-eaDK9VM", "(FLandroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "(FLandroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "drawCircularIndicator", "startAngle", "sweep", "stroke", "Landroidx/compose/ui/graphics/drawscope/Stroke;", "drawCircularIndicator-42QJj7c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawCircularIndicatorTrack", "drawCircularIndicatorTrack-bw27NRU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawDeterminateCircularIndicator", "drawDeterminateCircularIndicator-42QJj7c", "drawIndeterminateCircularIndicator", "drawIndeterminateCircularIndicator-hrjfTZI", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawLinearIndicator", "startFraction", "endFraction", "drawLinearIndicator-qYKTg0g", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJFI)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ProgressIndicatorKt {
    private static final float BaseRotationAngle = 286.0f;
    private static final CubicBezierEasing CircularEasing;
    private static final float CircularIndicatorDiameter;
    private static final int FirstLineHeadDelay = 0;
    private static final int FirstLineHeadDuration = 750;
    private static final CubicBezierEasing FirstLineHeadEasing;
    private static final int FirstLineTailDelay = 333;
    private static final int FirstLineTailDuration = 850;
    private static final CubicBezierEasing FirstLineTailEasing;
    private static final int HeadAndTailAnimationDuration = 666;
    private static final int HeadAndTailDelayDuration = 666;
    private static final Modifier IncreaseSemanticsBounds;
    private static final float JumpRotationAngle = 290.0f;
    private static final int LinearAnimationDuration = 1800;
    private static final float LinearIndicatorHeight;
    private static final float LinearIndicatorWidth;
    private static final float RotationAngleOffset = 216.0f;
    private static final int RotationDuration = 1332;
    private static final int RotationsPerCycle = 5;
    private static final int SecondLineHeadDelay = 1000;
    private static final int SecondLineHeadDuration = 567;
    private static final CubicBezierEasing SecondLineHeadEasing;
    private static final int SecondLineTailDelay = 1267;
    private static final int SecondLineTailDuration = 533;
    private static final CubicBezierEasing SecondLineTailEasing;
    private static final float SemanticsBoundsPadding;
    private static final float StartAngleOffset = -90.0f;

    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0060  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize` and `drawStopIndicator`, see `LegacyLinearProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(progress, modifier, color, trackColor, strokeCap, gapSize, drawStopIndicator)", imports = {}))
    /* renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m2530LinearProgressIndicator_5eSRE(final Function0 function0, Modifier modifier, long j, long j2, int i, Composer composer, final int i2, final int i3) {
        Function0 function02;
        int i4;
        Modifier modifier2;
        long j3;
        long j4;
        int i5;
        int i6;
        long j5;
        long j6;
        int i7;
        Modifier modifier3;
        long j7;
        Composer composer2;
        final Modifier modifier4;
        final long j8;
        final long j9;
        final int i8;
        ScopeUpdateScope endRestartGroup;
        int i9;
        Composer startRestartGroup = composer.startRestartGroup(-1796992155);
        ComposerKt.sourceInformation(startRestartGroup, "C(LinearProgressIndicator)P(2,1,0:c#ui.graphics.Color,4:c#ui.graphics.Color,3:c#ui.graphics.StrokeCap)96@4380L11,97@4443L16,100@4539L192:ProgressIndicator.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            function02 = function0;
        } else {
            function02 = function0;
            if ((i2 & 6) == 0) {
                i4 = (startRestartGroup.changedInstance(function02) ? 4 : 2) | i2;
            } else {
                i4 = i2;
            }
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
                j4 = j2;
                i4 |= ((i3 & 8) == 0 && startRestartGroup.changed(j4)) ? 2048 : 1024;
            } else {
                j4 = j2;
            }
            i5 = i3 & 16;
            if (i5 == 0) {
                i4 |= 24576;
            } else if ((i2 & 24576) == 0) {
                i6 = i;
                i4 |= startRestartGroup.changed(i6) ? 16384 : 8192;
                if ((i4 & 9363) == 9362 || !startRestartGroup.getSkipping()) {
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                        Modifier.Companion companion = i10 != 0 ? Modifier.INSTANCE : modifier2;
                        if ((i3 & 4) != 0) {
                            j5 = ProgressIndicatorDefaults.INSTANCE.getLinearColor(startRestartGroup, 6);
                            i4 &= -897;
                        } else {
                            j5 = j3;
                        }
                        if ((i3 & 8) != 0) {
                            j4 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(startRestartGroup, 6);
                            i4 &= -7169;
                        }
                        if (i5 != 0) {
                            i7 = ProgressIndicatorDefaults.INSTANCE.m2518getLinearStrokeCapKaPHkGw();
                            j6 = j4;
                        } else {
                            j6 = j4;
                            i7 = i6;
                        }
                        modifier3 = companion;
                        j7 = j5;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                        }
                        j6 = j4;
                        i7 = i6;
                        modifier3 = modifier2;
                        j7 = j3;
                    }
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1796992155, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:99)");
                    }
                    composer2 = startRestartGroup;
                    m2527LinearProgressIndicatorGJbTh5U(function02, modifier3, j7, j6, i7, ProgressIndicatorDefaults.INSTANCE.m2517getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (i4 & 57344), 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    j8 = j7;
                    j9 = j6;
                    i8 = i7;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    composer2 = startRestartGroup;
                    modifier4 = modifier2;
                    j8 = j3;
                    j9 = j4;
                    i8 = i6;
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11) {
                            ProgressIndicatorKt.m2530LinearProgressIndicator_5eSRE(function0, modifier4, j8, j9, i8, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                    return;
                }
                return;
            }
            i6 = i;
            if ((i4 & 9363) == 9362) {
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
            modifier3 = companion;
            j7 = j5;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            composer2 = startRestartGroup;
            m2527LinearProgressIndicatorGJbTh5U(function02, modifier3, j7, j6, i7, ProgressIndicatorDefaults.INSTANCE.m2517getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (i4 & 57344), 64);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier4 = modifier3;
            j8 = j7;
            j9 = j6;
            i8 = i7;
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
        i6 = i;
        if ((i4 & 9363) == 9362) {
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
        modifier3 = companion;
        j7 = j5;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        composer2 = startRestartGroup;
        m2527LinearProgressIndicatorGJbTh5U(function02, modifier3, j7, j6, i7, ProgressIndicatorDefaults.INSTANCE.m2517getLinearIndicatorTrackGapSizeD9Ej5fM(), null, composer2, (i4 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (i4 & 57344), 64);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier4 = modifier3;
        j8 = j7;
        j9 = j6;
        i8 = i7;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0270  */
    /* renamed from: LinearProgressIndicator-GJbTh5U, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2527LinearProgressIndicatorGJbTh5U(final Function0<Float> function0, Modifier modifier, long j, long j2, int i, float f, Function1<? super DrawScope, Unit> function1, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        final long j3;
        long j4;
        int i5;
        int i6;
        int i7;
        float f2;
        Function1<? super DrawScope, Unit> function12;
        Modifier.Companion companion;
        Function1<? super DrawScope, Unit> function13;
        final float f3;
        final int i8;
        boolean z;
        Object rememberedValue;
        boolean changed;
        Object rememberedValue2;
        boolean z2;
        Object rememberedValue3;
        final Function1<? super DrawScope, Unit> function14;
        final long j5;
        final long j6;
        final int i9;
        final float f4;
        final long j7;
        final Function1<? super DrawScope, Unit> function15;
        ScopeUpdateScope endRestartGroup;
        Composer startRestartGroup = composer.startRestartGroup(-339970038);
        ComposerKt.sourceInformation(startRestartGroup, "C(LinearProgressIndicator)P(4,3,0:c#ui.graphics.Color,6:c#ui.graphics.Color,5:c#ui.graphics.StrokeCap,2:c#ui.unit.Dp)140@6419L11,141@6482L16,144@6689L214,153@6935L31,157@7087L102,161@7259L806,154@6971L1094:ProgressIndicator.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (startRestartGroup.changedInstance(function0) ? 4 : 2) | i2;
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
                j3 = j;
                i4 |= ((i3 & 4) == 0 && startRestartGroup.changed(j3)) ? 256 : 128;
            } else {
                j3 = j;
            }
            if ((i2 & 3072) != 0) {
                j4 = j2;
                i4 |= ((i3 & 8) == 0 && startRestartGroup.changed(j4)) ? 2048 : 1024;
            } else {
                j4 = j2;
            }
            i5 = i3 & 16;
            if (i5 == 0) {
                i4 |= 24576;
            } else if ((i2 & 24576) == 0) {
                i6 = i;
                i4 |= startRestartGroup.changed(i6) ? 16384 : 8192;
                i7 = i3 & 32;
                if (i7 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    f2 = f;
                } else {
                    f2 = f;
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        i4 |= startRestartGroup.changed(f2) ? 131072 : 65536;
                    }
                }
                if ((i2 & 1572864) == 0) {
                    function12 = function1;
                    i4 |= ((i3 & 64) == 0 && startRestartGroup.changedInstance(function12)) ? 1048576 : 524288;
                } else {
                    function12 = function1;
                }
                if ((i4 & 599187) == 599186 || !startRestartGroup.getSkipping()) {
                    startRestartGroup.startDefaults();
                    boolean z3 = true;
                    if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                        companion = i10 != 0 ? Modifier.INSTANCE : modifier2;
                        if ((i3 & 4) != 0) {
                            j3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor(startRestartGroup, 6);
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            j4 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(startRestartGroup, 6);
                            i4 &= -7169;
                        }
                        final int m2518getLinearStrokeCapKaPHkGw = i5 != 0 ? ProgressIndicatorDefaults.INSTANCE.m2518getLinearStrokeCapKaPHkGw() : i6;
                        if (i7 != 0) {
                            f2 = ProgressIndicatorDefaults.INSTANCE.m2517getLinearIndicatorTrackGapSizeD9Ej5fM();
                        }
                        if ((i3 & 64) != 0) {
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1144997616, "CC(remember):ProgressIndicator.kt#9igjgp");
                            boolean z4 = ((((i4 & 896) ^ 384) > 256 && startRestartGroup.changed(j3)) || (i4 & 384) == 256) | ((57344 & i4) == 16384);
                            Object rememberedValue4 = startRestartGroup.rememberedValue();
                            if (z4 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                rememberedValue4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$2$1
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
                                        ProgressIndicatorDefaults.INSTANCE.m2512drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2519getLinearTrackStopIndicatorSizeD9Ej5fM(), j3, m2518getLinearStrokeCapKaPHkGw);
                                    }
                                };
                                startRestartGroup.updateRememberedValue(rememberedValue4);
                            }
                            function13 = (Function1) rememberedValue4;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            i4 &= -3670017;
                        } else {
                            function13 = function1;
                        }
                        f3 = f2;
                        i8 = m2518getLinearStrokeCapKaPHkGw;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                        }
                        function13 = function1;
                        f3 = f2;
                        companion = modifier2;
                        i8 = i6;
                    }
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-339970038, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:152)");
                    }
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145005305, "CC(remember):ProgressIndicator.kt#9igjgp");
                    z = (i4 & 14) == 4;
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (!z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                        rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                            }
                        };
                        startRestartGroup.updateRememberedValue(rememberedValue);
                    }
                    final Function0 function02 = (Function0) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    Modifier then = companion.then(IncreaseSemanticsBounds);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145010240, "CC(remember):ProgressIndicator.kt#9igjgp");
                    changed = startRestartGroup.changed(function02);
                    rememberedValue2 = startRestartGroup.rememberedValue();
                    if (!changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        rememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function02.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                            }
                        };
                        startRestartGroup.updateRememberedValue(rememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    Modifier m932sizeVpY3zN4 = SizeKt.m932sizeVpY3zN4(SemanticsModifierKt.semantics(then, true, (Function1) rememberedValue2), LinearIndicatorWidth, LinearIndicatorHeight);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145016448, "CC(remember):ProgressIndicator.kt#9igjgp");
                    boolean changed2 = ((57344 & i4) == 16384) | ((458752 & i4) == 131072) | startRestartGroup.changed(function02) | ((((i4 & 7168) ^ 3072) <= 2048 && startRestartGroup.changed(j4)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ 384) <= 256 && startRestartGroup.changed(j3)) || (i4 & 384) == 256);
                    if ((((3670016 & i4) ^ 1572864) > 1048576 || !startRestartGroup.changed(function13)) && (i4 & 1572864) != 1048576) {
                        z3 = false;
                    }
                    z2 = z3 | changed2;
                    rememberedValue3 = startRestartGroup.rememberedValue();
                    if (!z2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        function14 = function13;
                        j5 = j3;
                        j6 = j4;
                        rememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                                float f5;
                                DrawScope drawScope2;
                                float m4409getHeightimpl = Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc());
                                if (StrokeCap.m4991equalsimpl0(i8, StrokeCap.INSTANCE.m4995getButtKaPHkGw()) || Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc()) > Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) {
                                    f5 = f3;
                                } else {
                                    f5 = Dp.m7399constructorimpl(f3 + drawScope.mo455toDpu2uoSUM(m4409getHeightimpl));
                                }
                                float f6 = f5 / drawScope.mo455toDpu2uoSUM(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()));
                                float floatValue = function02.invoke().floatValue();
                                float min = floatValue + Math.min(floatValue, f6);
                                if (min <= 1.0f) {
                                    drawScope2 = drawScope;
                                    ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, min, 1.0f, j6, m4409getHeightimpl, i8);
                                } else {
                                    drawScope2 = drawScope;
                                }
                                ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, floatValue, j5, m4409getHeightimpl, i8);
                                function14.invoke(drawScope2);
                            }
                        };
                        startRestartGroup.updateRememberedValue(rememberedValue3);
                    } else {
                        function14 = function13;
                        j5 = j3;
                        j6 = j4;
                    }
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    CanvasKt.Canvas(m932sizeVpY3zN4, (Function1) rememberedValue3, startRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    i9 = i8;
                    f4 = f3;
                    j4 = j6;
                    j7 = j5;
                    function15 = function14;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    f4 = f2;
                    companion = modifier2;
                    i9 = i6;
                    j7 = j3;
                    function15 = function12;
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    final Modifier modifier3 = companion;
                    final long j8 = j4;
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$5
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

                        public final void invoke(Composer composer2, int i11) {
                            ProgressIndicatorKt.m2527LinearProgressIndicatorGJbTh5U(function0, modifier3, j7, j8, i9, f4, function15, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                    return;
                }
                return;
            }
            i6 = i;
            i7 = i3 & 32;
            if (i7 != 0) {
            }
            if ((i2 & 1572864) == 0) {
            }
            if ((i4 & 599187) == 599186) {
            }
            startRestartGroup.startDefaults();
            boolean z32 = true;
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
            if (i7 != 0) {
            }
            if ((i3 & 64) != 0) {
            }
            f3 = f2;
            i8 = m2518getLinearStrokeCapKaPHkGw;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145005305, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 14) == 4) {
            }
            rememberedValue = startRestartGroup.rememberedValue();
            if (!z) {
            }
            rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue);
            final Function0<Float> function022 = (Function0) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier then2 = companion.then(IncreaseSemanticsBounds);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145010240, "CC(remember):ProgressIndicator.kt#9igjgp");
            changed = startRestartGroup.changed(function022);
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (!changed) {
            }
            rememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function022.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue2);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier m932sizeVpY3zN42 = SizeKt.m932sizeVpY3zN4(SemanticsModifierKt.semantics(then2, true, (Function1) rememberedValue2), LinearIndicatorWidth, LinearIndicatorHeight);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145016448, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean changed22 = ((57344 & i4) == 16384) | ((458752 & i4) == 131072) | startRestartGroup.changed(function022) | ((((i4 & 7168) ^ 3072) <= 2048 && startRestartGroup.changed(j4)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ 384) <= 256 && startRestartGroup.changed(j3)) || (i4 & 384) == 256);
            if (((3670016 & i4) ^ 1572864) > 1048576) {
            }
            z32 = false;
            z2 = z32 | changed22;
            rememberedValue3 = startRestartGroup.rememberedValue();
            if (z2) {
            }
            function14 = function13;
            j5 = j3;
            j6 = j4;
            rememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                    float f5;
                    DrawScope drawScope2;
                    float m4409getHeightimpl = Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc());
                    if (StrokeCap.m4991equalsimpl0(i8, StrokeCap.INSTANCE.m4995getButtKaPHkGw()) || Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc()) > Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) {
                        f5 = f3;
                    } else {
                        f5 = Dp.m7399constructorimpl(f3 + drawScope.mo455toDpu2uoSUM(m4409getHeightimpl));
                    }
                    float f6 = f5 / drawScope.mo455toDpu2uoSUM(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()));
                    float floatValue = function022.invoke().floatValue();
                    float min = floatValue + Math.min(floatValue, f6);
                    if (min <= 1.0f) {
                        drawScope2 = drawScope;
                        ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, min, 1.0f, j6, m4409getHeightimpl, i8);
                    } else {
                        drawScope2 = drawScope;
                    }
                    ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, floatValue, j5, m4409getHeightimpl, i8);
                    function14.invoke(drawScope2);
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue3);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            CanvasKt.Canvas(m932sizeVpY3zN42, (Function1) rememberedValue3, startRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            i9 = i8;
            f4 = f3;
            j4 = j6;
            j7 = j5;
            function15 = function14;
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
        i6 = i;
        i7 = i3 & 32;
        if (i7 != 0) {
        }
        if ((i2 & 1572864) == 0) {
        }
        if ((i4 & 599187) == 599186) {
        }
        startRestartGroup.startDefaults();
        boolean z322 = true;
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
        if (i7 != 0) {
        }
        if ((i3 & 64) != 0) {
        }
        f3 = f2;
        i8 = m2518getLinearStrokeCapKaPHkGw;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145005305, "CC(remember):ProgressIndicator.kt#9igjgp");
        if ((i4 & 14) == 4) {
        }
        rememberedValue = startRestartGroup.rememberedValue();
        if (!z) {
        }
        rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$coercedProgress$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue);
        final Function0<Float> function0222 = (Function0) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Modifier then22 = companion.then(IncreaseSemanticsBounds);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145010240, "CC(remember):ProgressIndicator.kt#9igjgp");
        changed = startRestartGroup.changed(function0222);
        rememberedValue2 = startRestartGroup.rememberedValue();
        if (!changed) {
        }
        rememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$3$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function0222.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue2);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Modifier m932sizeVpY3zN422 = SizeKt.m932sizeVpY3zN4(SemanticsModifierKt.semantics(then22, true, (Function1) rememberedValue2), LinearIndicatorWidth, LinearIndicatorHeight);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145016448, "CC(remember):ProgressIndicator.kt#9igjgp");
        boolean changed222 = ((57344 & i4) == 16384) | ((458752 & i4) == 131072) | startRestartGroup.changed(function0222) | ((((i4 & 7168) ^ 3072) <= 2048 && startRestartGroup.changed(j4)) || (i4 & 3072) == 2048) | ((((i4 & 896) ^ 384) <= 256 && startRestartGroup.changed(j3)) || (i4 & 384) == 256);
        if (((3670016 & i4) ^ 1572864) > 1048576) {
        }
        z322 = false;
        z2 = z322 | changed222;
        rememberedValue3 = startRestartGroup.rememberedValue();
        if (z2) {
        }
        function14 = function13;
        j5 = j3;
        j6 = j4;
        rememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$4$1
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
                float f5;
                DrawScope drawScope2;
                float m4409getHeightimpl = Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc());
                if (StrokeCap.m4991equalsimpl0(i8, StrokeCap.INSTANCE.m4995getButtKaPHkGw()) || Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc()) > Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) {
                    f5 = f3;
                } else {
                    f5 = Dp.m7399constructorimpl(f3 + drawScope.mo455toDpu2uoSUM(m4409getHeightimpl));
                }
                float f6 = f5 / drawScope.mo455toDpu2uoSUM(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()));
                float floatValue = function0222.invoke().floatValue();
                float min = floatValue + Math.min(floatValue, f6);
                if (min <= 1.0f) {
                    drawScope2 = drawScope;
                    ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, min, 1.0f, j6, m4409getHeightimpl, i8);
                } else {
                    drawScope2 = drawScope;
                }
                ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, floatValue, j5, m4409getHeightimpl, i8);
                function14.invoke(drawScope2);
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue3);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        CanvasKt.Canvas(m932sizeVpY3zN422, (Function1) rememberedValue3, startRestartGroup, 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        i9 = i8;
        f4 = f3;
        j4 = j6;
        j7 = j5;
        function15 = function14;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00bd  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`, see `LegacyIndeterminateLinearProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(modifier, color, trackColor, strokeCap, gapSize)", imports = {}))
    /* renamed from: LinearProgressIndicator-2cYBFYY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m2526LinearProgressIndicator2cYBFYY(Modifier modifier, long j, long j2, int i, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        long j3;
        long j4;
        int i5;
        long j5;
        int i6;
        Modifier modifier3;
        final Modifier modifier4;
        final long j6;
        final long j7;
        final int i7;
        ScopeUpdateScope endRestartGroup;
        int i8;
        Composer startRestartGroup = composer.startRestartGroup(-476865359);
        ComposerKt.sourceInformation(startRestartGroup, "C(LinearProgressIndicator)P(1,0:c#ui.graphics.Color,3:c#ui.graphics.Color,2:c#ui.graphics.StrokeCap)214@9497L11,215@9560L16,218@9656L175:ProgressIndicator.kt#uh7d8r");
        int i9 = i3 & 1;
        if (i9 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (startRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            if ((i3 & 2) == 0) {
                j3 = j;
                if (startRestartGroup.changed(j3)) {
                    i8 = 32;
                    i4 |= i8;
                }
            } else {
                j3 = j;
            }
            i8 = 16;
            i4 |= i8;
        } else {
            j3 = j;
        }
        if ((i2 & 384) == 0) {
            j4 = j2;
            i4 |= ((i3 & 4) == 0 && startRestartGroup.changed(j4)) ? 256 : 128;
        } else {
            j4 = j2;
        }
        int i10 = i3 & 8;
        if (i10 != 0) {
            i4 |= 3072;
        } else if ((i2 & 3072) == 0) {
            i5 = i;
            i4 |= startRestartGroup.changed(i5) ? 2048 : 1024;
            if ((i4 & 1171) == 1170 || !startRestartGroup.getSkipping()) {
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                    Modifier.Companion companion = i9 == 0 ? Modifier.INSTANCE : modifier2;
                    if ((i3 & 2) != 0) {
                        j3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor(startRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        j4 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(startRestartGroup, 6);
                        i4 &= -897;
                    }
                    if (i10 == 0) {
                        i6 = ProgressIndicatorDefaults.INSTANCE.m2518getLinearStrokeCapKaPHkGw();
                        j5 = j4;
                    } else {
                        j5 = j4;
                        i6 = i5;
                    }
                    modifier3 = companion;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    if ((i3 & 2) != 0) {
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                    }
                    j5 = j4;
                    i6 = i5;
                    modifier3 = modifier2;
                }
                long j8 = j3;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-476865359, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:217)");
                }
                m2532LinearProgressIndicatorrIrjwxo(modifier3, j8, j5, i6, ProgressIndicatorDefaults.INSTANCE.m2517getLinearIndicatorTrackGapSizeD9Ej5fM(), startRestartGroup, (i4 & 14) | 24576 | (i4 & 112) | (i4 & 896) | (i4 & 7168), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j6 = j8;
                j7 = j5;
                i7 = i6;
            } else {
                startRestartGroup.skipToGroupEnd();
                modifier4 = modifier2;
                j6 = j3;
                j7 = j4;
                i7 = i5;
            }
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup == null) {
                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$6
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i11) {
                        ProgressIndicatorKt.m2526LinearProgressIndicator2cYBFYY(Modifier.this, j6, j7, i7, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    }
                });
                return;
            }
            return;
        }
        i5 = i;
        if ((i4 & 1171) == 1170) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i9 == 0) {
        }
        if ((i3 & 2) != 0) {
        }
        if ((i3 & 4) != 0) {
        }
        if (i10 == 0) {
        }
        modifier3 = companion;
        long j82 = j3;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        m2532LinearProgressIndicatorrIrjwxo(modifier3, j82, j5, i6, ProgressIndicatorDefaults.INSTANCE.m2517getLinearIndicatorTrackGapSizeD9Ej5fM(), startRestartGroup, (i4 & 14) | 24576 | (i4 & 112) | (i4 & 896) | (i4 & 7168), 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier4 = modifier3;
        j6 = j82;
        j7 = j5;
        i7 = i6;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x00f7  */
    /* renamed from: LinearProgressIndicator-rIrjwxo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2532LinearProgressIndicatorrIrjwxo(Modifier modifier, long j, long j2, int i, float f, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        long j3;
        long j4;
        int i5;
        int i6;
        final float f2;
        Modifier.Companion companion;
        final float m2517getLinearIndicatorTrackGapSizeD9Ej5fM;
        Composer composer2;
        boolean changed;
        Object rememberedValue;
        final long j5;
        final long j6;
        final Modifier modifier3;
        final int i7;
        final long j7;
        final long j8;
        ScopeUpdateScope endRestartGroup;
        Composer startRestartGroup = composer.startRestartGroup(567589233);
        ComposerKt.sourceInformation(startRestartGroup, "C(LinearProgressIndicator)P(2,0:c#ui.graphics.Color,4:c#ui.graphics.Color,3:c#ui.graphics.StrokeCap,1:c#ui.unit.Dp)249@11000L11,250@11063L16,254@11257L28,259@11547L396,272@11995L396,285@12444L400,298@12897L400,315@13472L1839,310@13302L2009:ProgressIndicator.kt#uh7d8r");
        int i8 = i3 & 1;
        if (i8 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (startRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            j3 = j;
            i4 |= ((i3 & 2) == 0 && startRestartGroup.changed(j3)) ? 32 : 16;
        } else {
            j3 = j;
        }
        if ((i2 & 384) == 0) {
            j4 = j2;
            i4 |= ((i3 & 4) == 0 && startRestartGroup.changed(j4)) ? 256 : 128;
        } else {
            j4 = j2;
        }
        int i9 = i3 & 8;
        if (i9 != 0) {
            i4 |= 3072;
        } else if ((i2 & 3072) == 0) {
            i5 = i;
            i4 |= startRestartGroup.changed(i5) ? 2048 : 1024;
            i6 = i3 & 16;
            if (i6 == 0) {
                i4 |= 24576;
            } else if ((i2 & 24576) == 0) {
                f2 = f;
                i4 |= startRestartGroup.changed(f2) ? 16384 : 8192;
                if ((i4 & 9363) == 9362 || !startRestartGroup.getSkipping()) {
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                        companion = i8 != 0 ? Modifier.INSTANCE : modifier2;
                        if ((i3 & 2) != 0) {
                            j3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor(startRestartGroup, 6);
                            i4 &= -113;
                        }
                        if ((i3 & 4) != 0) {
                            j4 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(startRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i9 != 0) {
                            i5 = ProgressIndicatorDefaults.INSTANCE.m2518getLinearStrokeCapKaPHkGw();
                        }
                        if (i6 != 0) {
                            m2517getLinearIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2517getLinearIndicatorTrackGapSizeD9Ej5fM();
                            final int i10 = i5;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(567589233, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:253)");
                            }
                            InfiniteTransition rememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(null, startRestartGroup, 0, 1);
                            final State<Float> animateFloat = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                    invoke2(keyframesSpecConfig);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                    CubicBezierEasing cubicBezierEasing;
                                    keyframesSpecConfig.setDurationMillis(1800);
                                    KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 0);
                                    cubicBezierEasing = ProgressIndicatorKt.FirstLineHeadEasing;
                                    keyframesSpecConfig.using(at, cubicBezierEasing);
                                    keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 750);
                                }
                            }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                            final State<Float> animateFloat2 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                    invoke2(keyframesSpecConfig);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                    CubicBezierEasing cubicBezierEasing;
                                    keyframesSpecConfig.setDurationMillis(1800);
                                    KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 333);
                                    cubicBezierEasing = ProgressIndicatorKt.FirstLineTailEasing;
                                    keyframesSpecConfig.using(at, cubicBezierEasing);
                                    keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 1183);
                                }
                            }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                            final State<Float> animateFloat3 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                    invoke2(keyframesSpecConfig);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                    CubicBezierEasing cubicBezierEasing;
                                    keyframesSpecConfig.setDurationMillis(1800);
                                    KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 1000);
                                    cubicBezierEasing = ProgressIndicatorKt.SecondLineHeadEasing;
                                    keyframesSpecConfig.using(at, cubicBezierEasing);
                                    keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 1567);
                                }
                            }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                            final State<Float> animateFloat4 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                    invoke2(keyframesSpecConfig);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                    CubicBezierEasing cubicBezierEasing;
                                    keyframesSpecConfig.setDurationMillis(1800);
                                    KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 1267);
                                    cubicBezierEasing = ProgressIndicatorKt.SecondLineTailEasing;
                                    keyframesSpecConfig.using(at, cubicBezierEasing);
                                    keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 1800);
                                }
                            }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                            composer2 = startRestartGroup;
                            Modifier m932sizeVpY3zN4 = SizeKt.m932sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion.then(IncreaseSemanticsBounds)), LinearIndicatorWidth, LinearIndicatorHeight);
                            ComposerKt.sourceInformationMarkerStart(composer2, 1145216297, "CC(remember):ProgressIndicator.kt#9igjgp");
                            changed = ((57344 & i4) != 16384) | ((i4 & 7168) != 2048) | composer2.changed(animateFloat) | ((((i4 & 896) ^ 384) <= 256 && composer2.changed(j4)) || (i4 & 384) == 256) | composer2.changed(animateFloat2) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(j3)) || (i4 & 48) == 32) | composer2.changed(animateFloat3) | composer2.changed(animateFloat4);
                            rememberedValue = composer2.rememberedValue();
                            if (!changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                j5 = j3;
                                j6 = j4;
                                rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
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
                                        float f3;
                                        DrawScope drawScope2;
                                        float m4409getHeightimpl = Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc());
                                        if (StrokeCap.m4991equalsimpl0(i10, StrokeCap.INSTANCE.m4995getButtKaPHkGw()) || Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc()) > Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) {
                                            f3 = m2517getLinearIndicatorTrackGapSizeD9Ej5fM;
                                        } else {
                                            f3 = Dp.m7399constructorimpl(m2517getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo455toDpu2uoSUM(m4409getHeightimpl));
                                        }
                                        float f4 = f3 / drawScope.mo455toDpu2uoSUM(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()));
                                        if (animateFloat.getValue().floatValue() < 1.0f - f4) {
                                            drawScope2 = drawScope;
                                            ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat.getValue().floatValue() > 0.0f ? animateFloat.getValue().floatValue() + f4 : 0.0f, 1.0f, j6, m4409getHeightimpl, i10);
                                        } else {
                                            drawScope2 = drawScope;
                                        }
                                        if (animateFloat.getValue().floatValue() - animateFloat2.getValue().floatValue() > 0.0f) {
                                            ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat.getValue().floatValue(), animateFloat2.getValue().floatValue(), j5, m4409getHeightimpl, i10);
                                        }
                                        if (animateFloat2.getValue().floatValue() > f4) {
                                            ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat3.getValue().floatValue() > 0.0f ? animateFloat3.getValue().floatValue() + f4 : 0.0f, animateFloat2.getValue().floatValue() < 1.0f ? animateFloat2.getValue().floatValue() - f4 : 1.0f, j6, m4409getHeightimpl, i10);
                                        }
                                        if (animateFloat3.getValue().floatValue() - animateFloat4.getValue().floatValue() > 0.0f) {
                                            ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat3.getValue().floatValue(), animateFloat4.getValue().floatValue(), j5, m4409getHeightimpl, i10);
                                        }
                                        if (animateFloat4.getValue().floatValue() > f4) {
                                            ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, animateFloat4.getValue().floatValue() < 1.0f ? animateFloat4.getValue().floatValue() - f4 : 1.0f, j6, m4409getHeightimpl, i10);
                                        }
                                    }
                                };
                                composer2.updateRememberedValue(rememberedValue);
                            } else {
                                j5 = j3;
                                j6 = j4;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            CanvasKt.Canvas(m932sizeVpY3zN4, (Function1) rememberedValue, composer2, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = companion;
                            i7 = i10;
                            f2 = m2517getLinearIndicatorTrackGapSizeD9Ej5fM;
                            j7 = j6;
                            j8 = j5;
                        }
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        if ((i3 & 2) != 0) {
                            i4 &= -113;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        companion = modifier2;
                    }
                    m2517getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
                    final int i102 = i5;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    InfiniteTransition rememberInfiniteTransition2 = InfiniteTransitionKt.rememberInfiniteTransition(null, startRestartGroup, 0, 1);
                    final State<Float> animateFloat5 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition2, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            invoke2(keyframesSpecConfig);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            CubicBezierEasing cubicBezierEasing;
                            keyframesSpecConfig.setDurationMillis(1800);
                            KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 0);
                            cubicBezierEasing = ProgressIndicatorKt.FirstLineHeadEasing;
                            keyframesSpecConfig.using(at, cubicBezierEasing);
                            keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 750);
                        }
                    }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    final State<Float> animateFloat22 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition2, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            invoke2(keyframesSpecConfig);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            CubicBezierEasing cubicBezierEasing;
                            keyframesSpecConfig.setDurationMillis(1800);
                            KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 333);
                            cubicBezierEasing = ProgressIndicatorKt.FirstLineTailEasing;
                            keyframesSpecConfig.using(at, cubicBezierEasing);
                            keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 1183);
                        }
                    }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    final State<Float> animateFloat32 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition2, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            invoke2(keyframesSpecConfig);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            CubicBezierEasing cubicBezierEasing;
                            keyframesSpecConfig.setDurationMillis(1800);
                            KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 1000);
                            cubicBezierEasing = ProgressIndicatorKt.SecondLineHeadEasing;
                            keyframesSpecConfig.using(at, cubicBezierEasing);
                            keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 1567);
                        }
                    }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    final State<Float> animateFloat42 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition2, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            invoke2(keyframesSpecConfig);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            CubicBezierEasing cubicBezierEasing;
                            keyframesSpecConfig.setDurationMillis(1800);
                            KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 1267);
                            cubicBezierEasing = ProgressIndicatorKt.SecondLineTailEasing;
                            keyframesSpecConfig.using(at, cubicBezierEasing);
                            keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 1800);
                        }
                    }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    composer2 = startRestartGroup;
                    Modifier m932sizeVpY3zN42 = SizeKt.m932sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion.then(IncreaseSemanticsBounds)), LinearIndicatorWidth, LinearIndicatorHeight);
                    ComposerKt.sourceInformationMarkerStart(composer2, 1145216297, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 7168) != 2048) {
                    }
                    changed = ((57344 & i4) != 16384) | ((i4 & 7168) != 2048) | composer2.changed(animateFloat5) | ((((i4 & 896) ^ 384) <= 256 && composer2.changed(j4)) || (i4 & 384) == 256) | composer2.changed(animateFloat22) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(j3)) || (i4 & 48) == 32) | composer2.changed(animateFloat32) | composer2.changed(animateFloat42);
                    rememberedValue = composer2.rememberedValue();
                    if (changed) {
                    }
                    j5 = j3;
                    j6 = j4;
                    rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
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
                            float f3;
                            DrawScope drawScope2;
                            float m4409getHeightimpl = Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc());
                            if (StrokeCap.m4991equalsimpl0(i102, StrokeCap.INSTANCE.m4995getButtKaPHkGw()) || Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc()) > Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) {
                                f3 = m2517getLinearIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                f3 = Dp.m7399constructorimpl(m2517getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo455toDpu2uoSUM(m4409getHeightimpl));
                            }
                            float f4 = f3 / drawScope.mo455toDpu2uoSUM(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()));
                            if (animateFloat5.getValue().floatValue() < 1.0f - f4) {
                                drawScope2 = drawScope;
                                ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat5.getValue().floatValue() > 0.0f ? animateFloat5.getValue().floatValue() + f4 : 0.0f, 1.0f, j6, m4409getHeightimpl, i102);
                            } else {
                                drawScope2 = drawScope;
                            }
                            if (animateFloat5.getValue().floatValue() - animateFloat22.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat5.getValue().floatValue(), animateFloat22.getValue().floatValue(), j5, m4409getHeightimpl, i102);
                            }
                            if (animateFloat22.getValue().floatValue() > f4) {
                                ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat32.getValue().floatValue() > 0.0f ? animateFloat32.getValue().floatValue() + f4 : 0.0f, animateFloat22.getValue().floatValue() < 1.0f ? animateFloat22.getValue().floatValue() - f4 : 1.0f, j6, m4409getHeightimpl, i102);
                            }
                            if (animateFloat32.getValue().floatValue() - animateFloat42.getValue().floatValue() > 0.0f) {
                                ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat32.getValue().floatValue(), animateFloat42.getValue().floatValue(), j5, m4409getHeightimpl, i102);
                            }
                            if (animateFloat42.getValue().floatValue() > f4) {
                                ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, animateFloat42.getValue().floatValue() < 1.0f ? animateFloat42.getValue().floatValue() - f4 : 1.0f, j6, m4409getHeightimpl, i102);
                            }
                        }
                    };
                    composer2.updateRememberedValue(rememberedValue);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    CanvasKt.Canvas(m932sizeVpY3zN42, (Function1) rememberedValue, composer2, 0);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    modifier3 = companion;
                    i7 = i102;
                    f2 = m2517getLinearIndicatorTrackGapSizeD9Ej5fM;
                    j7 = j6;
                    j8 = j5;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    composer2 = startRestartGroup;
                    modifier3 = modifier2;
                    j8 = j3;
                    j7 = j4;
                    i7 = i5;
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$8
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i11) {
                            ProgressIndicatorKt.m2532LinearProgressIndicatorrIrjwxo(Modifier.this, j8, j7, i7, f2, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                    return;
                }
                return;
            }
            f2 = f;
            if ((i4 & 9363) == 9362) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i8 != 0) {
            }
            if ((i3 & 2) != 0) {
            }
            if ((i3 & 4) != 0) {
            }
            if (i9 != 0) {
            }
            if (i6 != 0) {
            }
            m2517getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
            final int i1022 = i5;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            InfiniteTransition rememberInfiniteTransition22 = InfiniteTransitionKt.rememberInfiniteTransition(null, startRestartGroup, 0, 1);
            final State<Float> animateFloat52 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition22, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    CubicBezierEasing cubicBezierEasing;
                    keyframesSpecConfig.setDurationMillis(1800);
                    KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 0);
                    cubicBezierEasing = ProgressIndicatorKt.FirstLineHeadEasing;
                    keyframesSpecConfig.using(at, cubicBezierEasing);
                    keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 750);
                }
            }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> animateFloat222 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition22, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    CubicBezierEasing cubicBezierEasing;
                    keyframesSpecConfig.setDurationMillis(1800);
                    KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 333);
                    cubicBezierEasing = ProgressIndicatorKt.FirstLineTailEasing;
                    keyframesSpecConfig.using(at, cubicBezierEasing);
                    keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 1183);
                }
            }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> animateFloat322 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition22, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    CubicBezierEasing cubicBezierEasing;
                    keyframesSpecConfig.setDurationMillis(1800);
                    KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 1000);
                    cubicBezierEasing = ProgressIndicatorKt.SecondLineHeadEasing;
                    keyframesSpecConfig.using(at, cubicBezierEasing);
                    keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 1567);
                }
            }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> animateFloat422 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition22, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    CubicBezierEasing cubicBezierEasing;
                    keyframesSpecConfig.setDurationMillis(1800);
                    KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 1267);
                    cubicBezierEasing = ProgressIndicatorKt.SecondLineTailEasing;
                    keyframesSpecConfig.using(at, cubicBezierEasing);
                    keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 1800);
                }
            }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            composer2 = startRestartGroup;
            Modifier m932sizeVpY3zN422 = SizeKt.m932sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion.then(IncreaseSemanticsBounds)), LinearIndicatorWidth, LinearIndicatorHeight);
            ComposerKt.sourceInformationMarkerStart(composer2, 1145216297, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 7168) != 2048) {
            }
            changed = ((57344 & i4) != 16384) | ((i4 & 7168) != 2048) | composer2.changed(animateFloat52) | ((((i4 & 896) ^ 384) <= 256 && composer2.changed(j4)) || (i4 & 384) == 256) | composer2.changed(animateFloat222) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(j3)) || (i4 & 48) == 32) | composer2.changed(animateFloat322) | composer2.changed(animateFloat422);
            rememberedValue = composer2.rememberedValue();
            if (changed) {
            }
            j5 = j3;
            j6 = j4;
            rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
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
                    float f3;
                    DrawScope drawScope2;
                    float m4409getHeightimpl = Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc());
                    if (StrokeCap.m4991equalsimpl0(i1022, StrokeCap.INSTANCE.m4995getButtKaPHkGw()) || Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc()) > Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) {
                        f3 = m2517getLinearIndicatorTrackGapSizeD9Ej5fM;
                    } else {
                        f3 = Dp.m7399constructorimpl(m2517getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo455toDpu2uoSUM(m4409getHeightimpl));
                    }
                    float f4 = f3 / drawScope.mo455toDpu2uoSUM(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()));
                    if (animateFloat52.getValue().floatValue() < 1.0f - f4) {
                        drawScope2 = drawScope;
                        ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat52.getValue().floatValue() > 0.0f ? animateFloat52.getValue().floatValue() + f4 : 0.0f, 1.0f, j6, m4409getHeightimpl, i1022);
                    } else {
                        drawScope2 = drawScope;
                    }
                    if (animateFloat52.getValue().floatValue() - animateFloat222.getValue().floatValue() > 0.0f) {
                        ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat52.getValue().floatValue(), animateFloat222.getValue().floatValue(), j5, m4409getHeightimpl, i1022);
                    }
                    if (animateFloat222.getValue().floatValue() > f4) {
                        ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat322.getValue().floatValue() > 0.0f ? animateFloat322.getValue().floatValue() + f4 : 0.0f, animateFloat222.getValue().floatValue() < 1.0f ? animateFloat222.getValue().floatValue() - f4 : 1.0f, j6, m4409getHeightimpl, i1022);
                    }
                    if (animateFloat322.getValue().floatValue() - animateFloat422.getValue().floatValue() > 0.0f) {
                        ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat322.getValue().floatValue(), animateFloat422.getValue().floatValue(), j5, m4409getHeightimpl, i1022);
                    }
                    if (animateFloat422.getValue().floatValue() > f4) {
                        ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, animateFloat422.getValue().floatValue() < 1.0f ? animateFloat422.getValue().floatValue() - f4 : 1.0f, j6, m4409getHeightimpl, i1022);
                    }
                }
            };
            composer2.updateRememberedValue(rememberedValue);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            CanvasKt.Canvas(m932sizeVpY3zN422, (Function1) rememberedValue, composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier3 = companion;
            i7 = i1022;
            f2 = m2517getLinearIndicatorTrackGapSizeD9Ej5fM;
            j7 = j6;
            j8 = j5;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        i5 = i;
        i6 = i3 & 16;
        if (i6 == 0) {
        }
        f2 = f;
        if ((i4 & 9363) == 9362) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i8 != 0) {
        }
        if ((i3 & 2) != 0) {
        }
        if ((i3 & 4) != 0) {
        }
        if (i9 != 0) {
        }
        if (i6 != 0) {
        }
        m2517getLinearIndicatorTrackGapSizeD9Ej5fM = f2;
        final int i10222 = i5;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        InfiniteTransition rememberInfiniteTransition222 = InfiniteTransitionKt.rememberInfiniteTransition(null, startRestartGroup, 0, 1);
        final State<Float> animateFloat522 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition222, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineHead$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                invoke2(keyframesSpecConfig);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                CubicBezierEasing cubicBezierEasing;
                keyframesSpecConfig.setDurationMillis(1800);
                KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 0);
                cubicBezierEasing = ProgressIndicatorKt.FirstLineHeadEasing;
                keyframesSpecConfig.using(at, cubicBezierEasing);
                keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 750);
            }
        }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
        final State<Float> animateFloat2222 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition222, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$firstLineTail$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                invoke2(keyframesSpecConfig);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                CubicBezierEasing cubicBezierEasing;
                keyframesSpecConfig.setDurationMillis(1800);
                KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 333);
                cubicBezierEasing = ProgressIndicatorKt.FirstLineTailEasing;
                keyframesSpecConfig.using(at, cubicBezierEasing);
                keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 1183);
            }
        }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
        final State<Float> animateFloat3222 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition222, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineHead$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                invoke2(keyframesSpecConfig);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                CubicBezierEasing cubicBezierEasing;
                keyframesSpecConfig.setDurationMillis(1800);
                KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 1000);
                cubicBezierEasing = ProgressIndicatorKt.SecondLineHeadEasing;
                keyframesSpecConfig.using(at, cubicBezierEasing);
                keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 1567);
            }
        }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
        final State<Float> animateFloat4222 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition222, 0.0f, 1.0f, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$secondLineTail$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                invoke2(keyframesSpecConfig);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                CubicBezierEasing cubicBezierEasing;
                keyframesSpecConfig.setDurationMillis(1800);
                KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 1267);
                cubicBezierEasing = ProgressIndicatorKt.SecondLineTailEasing;
                keyframesSpecConfig.using(at, cubicBezierEasing);
                keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(1.0f), 1800);
            }
        }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
        composer2 = startRestartGroup;
        Modifier m932sizeVpY3zN4222 = SizeKt.m932sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(companion.then(IncreaseSemanticsBounds)), LinearIndicatorWidth, LinearIndicatorHeight);
        ComposerKt.sourceInformationMarkerStart(composer2, 1145216297, "CC(remember):ProgressIndicator.kt#9igjgp");
        if ((i4 & 7168) != 2048) {
        }
        changed = ((57344 & i4) != 16384) | ((i4 & 7168) != 2048) | composer2.changed(animateFloat522) | ((((i4 & 896) ^ 384) <= 256 && composer2.changed(j4)) || (i4 & 384) == 256) | composer2.changed(animateFloat2222) | ((((i4 & 112) ^ 48) <= 32 && composer2.changed(j3)) || (i4 & 48) == 32) | composer2.changed(animateFloat3222) | composer2.changed(animateFloat4222);
        rememberedValue = composer2.rememberedValue();
        if (changed) {
        }
        j5 = j3;
        j6 = j4;
        rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$7$1
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
                float f3;
                DrawScope drawScope2;
                float m4409getHeightimpl = Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc());
                if (StrokeCap.m4991equalsimpl0(i10222, StrokeCap.INSTANCE.m4995getButtKaPHkGw()) || Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc()) > Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) {
                    f3 = m2517getLinearIndicatorTrackGapSizeD9Ej5fM;
                } else {
                    f3 = Dp.m7399constructorimpl(m2517getLinearIndicatorTrackGapSizeD9Ej5fM + drawScope.mo455toDpu2uoSUM(m4409getHeightimpl));
                }
                float f4 = f3 / drawScope.mo455toDpu2uoSUM(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()));
                if (animateFloat522.getValue().floatValue() < 1.0f - f4) {
                    drawScope2 = drawScope;
                    ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat522.getValue().floatValue() > 0.0f ? animateFloat522.getValue().floatValue() + f4 : 0.0f, 1.0f, j6, m4409getHeightimpl, i10222);
                } else {
                    drawScope2 = drawScope;
                }
                if (animateFloat522.getValue().floatValue() - animateFloat2222.getValue().floatValue() > 0.0f) {
                    ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat522.getValue().floatValue(), animateFloat2222.getValue().floatValue(), j5, m4409getHeightimpl, i10222);
                }
                if (animateFloat2222.getValue().floatValue() > f4) {
                    ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat3222.getValue().floatValue() > 0.0f ? animateFloat3222.getValue().floatValue() + f4 : 0.0f, animateFloat2222.getValue().floatValue() < 1.0f ? animateFloat2222.getValue().floatValue() - f4 : 1.0f, j6, m4409getHeightimpl, i10222);
                }
                if (animateFloat3222.getValue().floatValue() - animateFloat4222.getValue().floatValue() > 0.0f) {
                    ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, animateFloat3222.getValue().floatValue(), animateFloat4222.getValue().floatValue(), j5, m4409getHeightimpl, i10222);
                }
                if (animateFloat4222.getValue().floatValue() > f4) {
                    ProgressIndicatorKt.m2542drawLinearIndicatorqYKTg0g(drawScope2, 0.0f, animateFloat4222.getValue().floatValue() < 1.0f ? animateFloat4222.getValue().floatValue() - f4 : 1.0f, j6, m4409getHeightimpl, i10222);
                }
            }
        };
        composer2.updateRememberedValue(rememberedValue);
        ComposerKt.sourceInformationMarkerEnd(composer2);
        CanvasKt.Canvas(m932sizeVpY3zN4222, (Function1) rememberedValue, composer2, 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier3 = companion;
        i7 = i10222;
        f2 = m2517getLinearIndicatorTrackGapSizeD9Ej5fM;
        j7 = j6;
        j8 = j5;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x005d  */
    @Deprecated(message = "Use the overload that takes `progress` as a lambda", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(\nprogress = { progress },\nmodifier = modifier,\ncolor = color,\ntrackColor = trackColor,\nstrokeCap = strokeCap,\n)", imports = {}))
    /* renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2529LinearProgressIndicator_5eSRE(final float f, Modifier modifier, long j, long j2, int i, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long j3;
        long j4;
        int i5;
        int i6;
        int m2518getLinearStrokeCapKaPHkGw;
        boolean z;
        Object rememberedValue;
        Composer composer2;
        final Modifier modifier3;
        final long j5;
        final long j6;
        final int i7;
        ScopeUpdateScope endRestartGroup;
        Composer startRestartGroup = composer.startRestartGroup(905419617);
        ComposerKt.sourceInformation(startRestartGroup, "C(LinearProgressIndicator)P(2,1,0:c#ui.graphics.Color,4:c#ui.graphics.Color,3:c#ui.graphics.StrokeCap)385@15871L11,386@15934L16,390@16074L12,389@16030L179:ProgressIndicator.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (startRestartGroup.changed(f) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i8 = i3 & 2;
        if (i8 != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i2 & 384) != 0) {
                j3 = j;
                i4 |= ((i3 & 4) == 0 && startRestartGroup.changed(j3)) ? 256 : 128;
            } else {
                j3 = j;
            }
            if ((i2 & 3072) != 0) {
                j4 = j2;
                i4 |= ((i3 & 8) == 0 && startRestartGroup.changed(j4)) ? 2048 : 1024;
            } else {
                j4 = j2;
            }
            i5 = i3 & 16;
            if (i5 == 0) {
                i4 |= 24576;
            } else if ((i2 & 24576) == 0) {
                i6 = i;
                i4 |= startRestartGroup.changed(i6) ? 16384 : 8192;
                if ((i4 & 9363) == 9362 || !startRestartGroup.getSkipping()) {
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                        if (i8 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            j3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor(startRestartGroup, 6);
                            i4 &= -897;
                        }
                        if ((i3 & 8) != 0) {
                            j4 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(startRestartGroup, 6);
                            i4 &= -7169;
                        }
                        if (i5 != 0) {
                            m2518getLinearStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2518getLinearStrokeCapKaPHkGw();
                            long j7 = j4;
                            long j8 = j3;
                            Modifier modifier4 = modifier2;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(905419617, i4, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:389)");
                            }
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145297734, "CC(remember):ProgressIndicator.kt#9igjgp");
                            z = (i4 & 14) != 4;
                            rememberedValue = startRestartGroup.rememberedValue();
                            if (!z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Float invoke() {
                                        return Float.valueOf(f);
                                    }
                                };
                                startRestartGroup.updateRememberedValue(rememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            composer2 = startRestartGroup;
                            m2527LinearProgressIndicatorGJbTh5U((Function0) rememberedValue, modifier4, j8, j7, m2518getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            j5 = j8;
                            j6 = j7;
                            i7 = m2518getLinearStrokeCapKaPHkGw;
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
                    m2518getLinearStrokeCapKaPHkGw = i6;
                    long j72 = j4;
                    long j82 = j3;
                    Modifier modifier42 = modifier2;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145297734, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 14) != 4) {
                    }
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (!z) {
                    }
                    rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(f);
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    composer2 = startRestartGroup;
                    m2527LinearProgressIndicatorGJbTh5U((Function0) rememberedValue, modifier42, j82, j72, m2518getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    modifier3 = modifier42;
                    j5 = j82;
                    j6 = j72;
                    i7 = m2518getLinearStrokeCapKaPHkGw;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    composer2 = startRestartGroup;
                    modifier3 = modifier2;
                    j5 = j3;
                    j6 = j4;
                    i7 = i6;
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$10
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i9) {
                            ProgressIndicatorKt.m2529LinearProgressIndicator_5eSRE(f, modifier3, j5, j6, i7, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                    return;
                }
                return;
            }
            i6 = i;
            if ((i4 & 9363) == 9362) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i8 != 0) {
            }
            if ((i3 & 4) != 0) {
            }
            if ((i3 & 8) != 0) {
            }
            if (i5 != 0) {
            }
            m2518getLinearStrokeCapKaPHkGw = i6;
            long j722 = j4;
            long j822 = j3;
            Modifier modifier422 = modifier2;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145297734, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 14) != 4) {
            }
            rememberedValue = startRestartGroup.rememberedValue();
            if (!z) {
            }
            rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return Float.valueOf(f);
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            composer2 = startRestartGroup;
            m2527LinearProgressIndicatorGJbTh5U((Function0) rememberedValue, modifier422, j822, j722, m2518getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier3 = modifier422;
            j5 = j822;
            j6 = j722;
            i7 = m2518getLinearStrokeCapKaPHkGw;
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
        i6 = i;
        if ((i4 & 9363) == 9362) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i8 != 0) {
        }
        if ((i3 & 4) != 0) {
        }
        if ((i3 & 8) != 0) {
        }
        if (i5 != 0) {
        }
        m2518getLinearStrokeCapKaPHkGw = i6;
        long j7222 = j4;
        long j8222 = j3;
        Modifier modifier4222 = modifier2;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1145297734, "CC(remember):ProgressIndicator.kt#9igjgp");
        if ((i4 & 14) != 4) {
        }
        rememberedValue = startRestartGroup.rememberedValue();
        if (!z) {
        }
        rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$9$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(f);
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        composer2 = startRestartGroup;
        m2527LinearProgressIndicatorGJbTh5U((Function0) rememberedValue, modifier4222, j8222, j7222, m2518getLinearStrokeCapKaPHkGw, 0.0f, null, composer2, i4 & 65520, 96);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier3 = modifier4222;
        j5 = j8222;
        j6 = j7222;
        i7 = m2518getLinearStrokeCapKaPHkGw;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0060  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: LinearProgressIndicator-eaDK9VM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m2531LinearProgressIndicatoreaDK9VM(final float f, Modifier modifier, long j, long j2, Composer composer, final int i, final int i2) {
        float f2;
        int i3;
        Modifier modifier2;
        long j3;
        long j4;
        long j5;
        long j6;
        Modifier modifier3;
        long j7;
        final Modifier modifier4;
        final long j8;
        final long j9;
        ScopeUpdateScope endRestartGroup;
        int i4;
        Composer startRestartGroup = composer.startRestartGroup(-372717133);
        ComposerKt.sourceInformation(startRestartGroup, "C(LinearProgressIndicator)P(2,1,0:c#ui.graphics.Color,3:c#ui.graphics.Color)403@16462L11,404@16525L16,406@16551L164:ProgressIndicator.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            f2 = f;
        } else {
            f2 = f;
            if ((i & 6) == 0) {
                i3 = (startRestartGroup.changed(f2) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        int i5 = i2 & 2;
        if (i5 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i & 384) != 0) {
                if ((i2 & 4) == 0) {
                    j3 = j;
                    if (startRestartGroup.changed(j3)) {
                        i4 = 256;
                        i3 |= i4;
                    }
                } else {
                    j3 = j;
                }
                i4 = 128;
                i3 |= i4;
            } else {
                j3 = j;
            }
            if ((i & 3072) != 0) {
                j4 = j2;
                i3 |= ((i2 & 8) == 0 && startRestartGroup.changed(j4)) ? 2048 : 1024;
            } else {
                j4 = j2;
            }
            if ((i3 & 1171) == 1170 || !startRestartGroup.getSkipping()) {
                startRestartGroup.startDefaults();
                if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                    Modifier.Companion companion = i5 == 0 ? Modifier.INSTANCE : modifier2;
                    if ((i2 & 4) == 0) {
                        j5 = ProgressIndicatorDefaults.INSTANCE.getLinearColor(startRestartGroup, 6);
                        i3 &= -897;
                    } else {
                        j5 = j3;
                    }
                    if ((i2 & 8) == 0) {
                        i3 &= -7169;
                        modifier3 = companion;
                        j7 = j5;
                        j6 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(startRestartGroup, 6);
                    } else {
                        j6 = j4;
                        modifier3 = companion;
                        j7 = j5;
                    }
                } else {
                    startRestartGroup.skipToGroupEnd();
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                    }
                    j6 = j4;
                    modifier3 = modifier2;
                    j7 = j3;
                }
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-372717133, i3, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:406)");
                }
                m2529LinearProgressIndicator_5eSRE(f2, modifier3, j7, j6, ProgressIndicatorDefaults.INSTANCE.m2518getLinearStrokeCapKaPHkGw(), startRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j8 = j7;
                j9 = j6;
            } else {
                startRestartGroup.skipToGroupEnd();
                modifier4 = modifier2;
                j8 = j3;
                j9 = j4;
            }
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup == null) {
                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$11
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i6) {
                        ProgressIndicatorKt.m2531LinearProgressIndicatoreaDK9VM(f, modifier4, j8, j9, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
                return;
            }
            return;
        }
        modifier2 = modifier;
        if ((i & 384) != 0) {
        }
        if ((i & 3072) != 0) {
        }
        if ((i3 & 1171) == 1170) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i5 == 0) {
        }
        if ((i2 & 4) == 0) {
        }
        if ((i2 & 8) == 0) {
        }
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        m2529LinearProgressIndicator_5eSRE(f2, modifier3, j7, j6, ProgressIndicatorDefaults.INSTANCE.m2518getLinearStrokeCapKaPHkGw(), startRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier4 = modifier3;
        j8 = j7;
        j9 = j6;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: LinearProgressIndicator-RIQooxk, reason: not valid java name */
    public static final /* synthetic */ void m2528LinearProgressIndicatorRIQooxk(Modifier modifier, long j, long j2, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long j3;
        long j4;
        long j5;
        Modifier modifier3;
        final Modifier modifier4;
        final long j6;
        final long j7;
        int i4;
        int i5;
        Composer startRestartGroup = composer.startRestartGroup(585576195);
        ComposerKt.sourceInformation(startRestartGroup, "C(LinearProgressIndicator)P(1,0:c#ui.graphics.Color,2:c#ui.graphics.Color)418@16922L11,419@16985L16,421@17011L146:ProgressIndicator.kt#uh7d8r");
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
            if ((i2 & 4) == 0) {
                j4 = j2;
                if (startRestartGroup.changed(j4)) {
                    i4 = 256;
                    i3 |= i4;
                }
            } else {
                j4 = j2;
            }
            i4 = 128;
            i3 |= i4;
        } else {
            j4 = j2;
        }
        if ((i3 & 147) == 146 && startRestartGroup.getSkipping()) {
            startRestartGroup.skipToGroupEnd();
            modifier4 = modifier2;
            j6 = j3;
            j7 = j4;
        } else {
            startRestartGroup.startDefaults();
            if ((i & 1) == 0 || startRestartGroup.getDefaultsInvalid()) {
                Modifier.Companion companion = i6 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i2 & 2) != 0) {
                    j3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor(startRestartGroup, 6);
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    j4 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(startRestartGroup, 6);
                    i3 &= -897;
                }
                j5 = j4;
                modifier3 = companion;
            } else {
                startRestartGroup.skipToGroupEnd();
                if ((i2 & 2) != 0) {
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
                j5 = j4;
                modifier3 = modifier2;
            }
            long j8 = j3;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(585576195, i3, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:421)");
            }
            m2532LinearProgressIndicatorrIrjwxo(modifier3, j8, j5, ProgressIndicatorDefaults.INSTANCE.m2518getLinearStrokeCapKaPHkGw(), 0.0f, startRestartGroup, (i3 & 14) | 3072 | (i3 & 112) | (i3 & 896), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j6 = j8;
            j7 = j5;
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$LinearProgressIndicator$12
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i7) {
                    ProgressIndicatorKt.m2528LinearProgressIndicatorRIQooxk(Modifier.this, j6, j7, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawLinearIndicator-qYKTg0g, reason: not valid java name */
    public static final void m2542drawLinearIndicatorqYKTg0g(DrawScope drawScope, float f, float f2, long j, float f3, int i) {
        float m4412getWidthimpl = Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc());
        float m4409getHeightimpl = Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc());
        float f4 = 2;
        float f5 = m4409getHeightimpl / f4;
        boolean z = drawScope.getLayoutDirection() == LayoutDirection.Ltr;
        float f6 = (z ? f : 1.0f - f2) * m4412getWidthimpl;
        float f7 = (z ? f2 : 1.0f - f) * m4412getWidthimpl;
        if (StrokeCap.m4991equalsimpl0(i, StrokeCap.INSTANCE.m4995getButtKaPHkGw()) || m4409getHeightimpl > m4412getWidthimpl) {
            DrawScope.CC.m5198drawLineNGM6Ib0$default(drawScope, j, OffsetKt.Offset(f6, f5), OffsetKt.Offset(f7, f5), f3, 0, null, 0.0f, null, 0, 496, null);
            return;
        }
        float f8 = f3 / f4;
        ClosedFloatingPointRange<Float> rangeTo = RangesKt.rangeTo(f8, m4412getWidthimpl - f8);
        float floatValue = ((Number) RangesKt.coerceIn(Float.valueOf(f6), rangeTo)).floatValue();
        float floatValue2 = ((Number) RangesKt.coerceIn(Float.valueOf(f7), rangeTo)).floatValue();
        if (Math.abs(f2 - f) > 0.0f) {
            DrawScope.CC.m5198drawLineNGM6Ib0$default(drawScope, j, OffsetKt.Offset(floatValue, f5), OffsetKt.Offset(floatValue2, f5), f3, i, null, 0.0f, null, 0, 480, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0060  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`, see `LegacyCircularProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(progress, modifier, color, strokeWidth, trackColor, strokeCap, gapSize)", imports = {}))
    /* renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m2521CircularProgressIndicatorDUhRLBM(final Function0 function0, Modifier modifier, long j, float f, long j2, int i, Composer composer, final int i2, final int i3) {
        Function0 function02;
        int i4;
        Modifier modifier2;
        long j3;
        int i5;
        float f2;
        long j4;
        int i6;
        int i7;
        long j5;
        int i8;
        Modifier modifier3;
        long j6;
        float f3;
        int i9;
        Composer composer2;
        final Modifier modifier4;
        final long j7;
        final float f4;
        final long j8;
        final int i10;
        ScopeUpdateScope endRestartGroup;
        int i11;
        int i12;
        Composer startRestartGroup = composer.startRestartGroup(-761680467);
        ComposerKt.sourceInformation(startRestartGroup, "C(CircularProgressIndicator)P(2,1,0:c#ui.graphics.Color,4:c#ui.unit.Dp,5:c#ui.graphics.Color,3:c#ui.graphics.StrokeCap)529@21735L13,531@21869L29,534@21991L217:ProgressIndicator.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            function02 = function0;
        } else {
            function02 = function0;
            if ((i2 & 6) == 0) {
                i4 = (startRestartGroup.changedInstance(function02) ? 4 : 2) | i2;
            } else {
                i4 = i2;
            }
        }
        int i13 = i3 & 2;
        if (i13 != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i2 & 384) != 0) {
                if ((i3 & 4) == 0) {
                    j3 = j;
                    if (startRestartGroup.changed(j3)) {
                        i12 = 256;
                        i4 |= i12;
                    }
                } else {
                    j3 = j;
                }
                i12 = 128;
                i4 |= i12;
            } else {
                j3 = j;
            }
            i5 = i3 & 8;
            if (i5 == 0) {
                i4 |= 3072;
            } else if ((i2 & 3072) == 0) {
                f2 = f;
                i4 |= startRestartGroup.changed(f2) ? 2048 : 1024;
                if ((i2 & 24576) == 0) {
                    if ((i3 & 16) == 0) {
                        j4 = j2;
                        if (startRestartGroup.changed(j4)) {
                            i11 = 16384;
                            i4 |= i11;
                        }
                    } else {
                        j4 = j2;
                    }
                    i11 = 8192;
                    i4 |= i11;
                } else {
                    j4 = j2;
                }
                i6 = i3 & 32;
                if (i6 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i2) == 0) {
                    i7 = i;
                    i4 |= startRestartGroup.changed(i7) ? 131072 : 65536;
                    if ((74899 & i4) == 74898 || !startRestartGroup.getSkipping()) {
                        startRestartGroup.startDefaults();
                        if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                            Modifier.Companion companion = i13 == 0 ? Modifier.INSTANCE : modifier2;
                            if ((i3 & 4) != 0) {
                                j3 = ProgressIndicatorDefaults.INSTANCE.getCircularColor(startRestartGroup, 6);
                                i4 &= -897;
                            }
                            if (i5 != 0) {
                                f2 = ProgressIndicatorDefaults.INSTANCE.m2516getCircularStrokeWidthD9Ej5fM();
                            }
                            if ((i3 & 16) != 0) {
                                i4 &= -57345;
                                j4 = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(startRestartGroup, 6);
                            }
                            if (i6 == 0) {
                                i8 = ProgressIndicatorDefaults.INSTANCE.m2513getCircularDeterminateStrokeCapKaPHkGw();
                                f3 = f2;
                                j5 = j4;
                                modifier3 = companion;
                                j6 = j3;
                            } else {
                                j5 = j4;
                                i8 = i7;
                                modifier3 = companion;
                                j6 = j3;
                                f3 = f2;
                            }
                            i9 = -761680467;
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                            }
                            if ((i3 & 16) != 0) {
                                i4 &= -57345;
                            }
                            j5 = j4;
                            i8 = i7;
                            i9 = -761680467;
                            modifier3 = modifier2;
                            j6 = j3;
                            f3 = f2;
                        }
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i9, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:533)");
                        }
                        composer2 = startRestartGroup;
                        m2522CircularProgressIndicatorIyT6zlY(function02, modifier3, j6, f3, j5, i8, ProgressIndicatorDefaults.INSTANCE.m2515getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier4 = modifier3;
                        j7 = j6;
                        f4 = f3;
                        j8 = j5;
                        i10 = i8;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        composer2 = startRestartGroup;
                        modifier4 = modifier2;
                        j7 = j3;
                        f4 = f2;
                        j8 = j4;
                        i10 = i7;
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i14) {
                                ProgressIndicatorKt.m2521CircularProgressIndicatorDUhRLBM(function0, modifier4, j7, f4, j8, i10, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                            }
                        });
                        return;
                    }
                    return;
                }
                i7 = i;
                if ((74899 & i4) == 74898) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i13 == 0) {
                }
                if ((i3 & 4) != 0) {
                }
                if (i5 != 0) {
                }
                if ((i3 & 16) != 0) {
                }
                if (i6 == 0) {
                }
                i9 = -761680467;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                composer2 = startRestartGroup;
                m2522CircularProgressIndicatorIyT6zlY(function02, modifier3, j6, f3, j5, i8, ProgressIndicatorDefaults.INSTANCE.m2515getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                }
                modifier4 = modifier3;
                j7 = j6;
                f4 = f3;
                j8 = j5;
                i10 = i8;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            f2 = f;
            if ((i2 & 24576) == 0) {
            }
            i6 = i3 & 32;
            if (i6 != 0) {
            }
            i7 = i;
            if ((74899 & i4) == 74898) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i13 == 0) {
            }
            if ((i3 & 4) != 0) {
            }
            if (i5 != 0) {
            }
            if ((i3 & 16) != 0) {
            }
            if (i6 == 0) {
            }
            i9 = -761680467;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            composer2 = startRestartGroup;
            m2522CircularProgressIndicatorIyT6zlY(function02, modifier3, j6, f3, j5, i8, ProgressIndicatorDefaults.INSTANCE.m2515getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier4 = modifier3;
            j7 = j6;
            f4 = f3;
            j8 = j5;
            i10 = i8;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        if ((i2 & 384) != 0) {
        }
        i5 = i3 & 8;
        if (i5 == 0) {
        }
        f2 = f;
        if ((i2 & 24576) == 0) {
        }
        i6 = i3 & 32;
        if (i6 != 0) {
        }
        i7 = i;
        if ((74899 & i4) == 74898) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i13 == 0) {
        }
        if ((i3 & 4) != 0) {
        }
        if (i5 != 0) {
        }
        if ((i3 & 16) != 0) {
        }
        if (i6 == 0) {
        }
        i9 = -761680467;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        composer2 = startRestartGroup;
        m2522CircularProgressIndicatorIyT6zlY(function02, modifier3, j6, f3, j5, i8, ProgressIndicatorDefaults.INSTANCE.m2515getCircularIndicatorTrackGapSizeD9Ej5fM(), composer2, (i4 & 14) | 1572864 | (i4 & 112) | (i4 & 896) | (i4 & 7168) | (57344 & i4) | (i4 & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier4 = modifier3;
        j7 = j6;
        f4 = f3;
        j8 = j5;
        i10 = i8;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0298  */
    /* renamed from: CircularProgressIndicator-IyT6zlY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2522CircularProgressIndicatorIyT6zlY(final Function0<Float> function0, Modifier modifier, long j, float f, long j2, int i, float f2, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long j3;
        int i5;
        float f3;
        long j4;
        int i6;
        int i7;
        int i8;
        float f4;
        long j5;
        final float m2515getCircularIndicatorTrackGapSizeD9Ej5fM;
        final int i9;
        boolean z;
        Object rememberedValue;
        boolean changed;
        Object rememberedValue2;
        boolean changed2;
        Object rememberedValue3;
        final long j6;
        final long j7;
        final float f5;
        final float f6;
        final long j8;
        ScopeUpdateScope endRestartGroup;
        Composer startRestartGroup = composer.startRestartGroup(-1798883595);
        ComposerKt.sourceInformation(startRestartGroup, "C(CircularProgressIndicator)P(3,2,0:c#ui.graphics.Color,5:c#ui.unit.Dp,6:c#ui.graphics.Color,4:c#ui.graphics.StrokeCap,1:c#ui.unit.Dp)575@23884L13,577@24018L29,581@24237L31,*582@24304L7,585@24446L102,589@24600L709,583@24373L936:ProgressIndicator.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (startRestartGroup.changedInstance(function0) ? 4 : 2) | i2;
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
                j3 = j;
                i4 |= ((i3 & 4) == 0 && startRestartGroup.changed(j3)) ? 256 : 128;
            } else {
                j3 = j;
            }
            i5 = i3 & 8;
            if (i5 == 0) {
                i4 |= 3072;
            } else if ((i2 & 3072) == 0) {
                f3 = f;
                i4 |= startRestartGroup.changed(f3) ? 2048 : 1024;
                if ((i2 & 24576) == 0) {
                    j4 = j2;
                    i4 |= ((i3 & 16) == 0 && startRestartGroup.changed(j4)) ? 16384 : 8192;
                } else {
                    j4 = j2;
                }
                i6 = i3 & 32;
                if (i6 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    i7 = i;
                } else {
                    i7 = i;
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        i4 |= startRestartGroup.changed(i7) ? 131072 : 65536;
                    }
                }
                i8 = i3 & 64;
                if (i8 != 0) {
                    i4 |= 1572864;
                    f4 = f2;
                } else {
                    f4 = f2;
                    if ((i2 & 1572864) == 0) {
                        i4 |= startRestartGroup.changed(f4) ? 1048576 : 524288;
                    }
                }
                if ((i4 & 599187) == 599186 || !startRestartGroup.getSkipping()) {
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                        if (i10 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            j3 = ProgressIndicatorDefaults.INSTANCE.getCircularColor(startRestartGroup, 6);
                            i4 &= -897;
                        }
                        if (i5 != 0) {
                            f3 = ProgressIndicatorDefaults.INSTANCE.m2516getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 16) != 0) {
                            j5 = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor(startRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            j5 = j4;
                        }
                        if (i6 != 0) {
                            i7 = ProgressIndicatorDefaults.INSTANCE.m2513getCircularDeterminateStrokeCapKaPHkGw();
                        }
                        if (i8 != 0) {
                            m2515getCircularIndicatorTrackGapSizeD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m2515getCircularIndicatorTrackGapSizeD9Ej5fM();
                            i9 = i7;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1798883595, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:580)");
                            }
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291619137, "CC(remember):ProgressIndicator.kt#9igjgp");
                            z = (i4 & 14) != 4;
                            rememberedValue = startRestartGroup.rememberedValue();
                            if (!z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Float invoke() {
                                        return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                                    }
                                };
                                startRestartGroup.updateRememberedValue(rememberedValue);
                            }
                            final Function0 function02 = (Function0) rememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object consume = startRestartGroup.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            final Stroke stroke = new Stroke(((Density) consume).mo459toPx0680j_4(f3), 0.0f, i9, 0, null, 26, null);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291612378, "CC(remember):ProgressIndicator.kt#9igjgp");
                            changed = startRestartGroup.changed(function02);
                            rememberedValue2 = startRestartGroup.rememberedValue();
                            if (!changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                rememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function02.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                                    }
                                };
                                startRestartGroup.updateRememberedValue(rememberedValue2);
                            }
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            Modifier m930size3ABfNKs = SizeKt.m930size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) rememberedValue2), CircularIndicatorDiameter);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291606843, "CC(remember):ProgressIndicator.kt#9igjgp");
                            changed2 = startRestartGroup.changed(function02) | ((458752 & i4) != 131072) | ((3670016 & i4) != 1048576) | ((i4 & 7168) != 2048) | ((((57344 & i4) ^ 24576) <= 16384 && startRestartGroup.changed(j5)) || (i4 & 24576) == 16384) | startRestartGroup.changedInstance(stroke) | ((((i4 & 896) ^ 384) <= 256 && startRestartGroup.changed(j3)) || (i4 & 384) == 256);
                            rememberedValue3 = startRestartGroup.rememberedValue();
                            if (!changed2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                j6 = j5;
                                j7 = j3;
                                f5 = f3;
                                rememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
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
                                        float f7;
                                        float floatValue = function02.invoke().floatValue() * 360.0f;
                                        if (StrokeCap.m4991equalsimpl0(i9, StrokeCap.INSTANCE.m4995getButtKaPHkGw()) || Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc()) > Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) {
                                            f7 = m2515getCircularIndicatorTrackGapSizeD9Ej5fM;
                                        } else {
                                            f7 = Dp.m7399constructorimpl(m2515getCircularIndicatorTrackGapSizeD9Ej5fM + f5);
                                        }
                                        float f8 = (f7 / ((float) (drawScope.mo455toDpu2uoSUM(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) * 3.141592653589793d))) * 360.0f;
                                        ProgressIndicatorKt.m2538drawCircularIndicator42QJj7c(drawScope, 270.0f + floatValue + Math.min(floatValue, f8), (360.0f - floatValue) - (Math.min(floatValue, f8) * 2), j6, stroke);
                                        ProgressIndicatorKt.m2540drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, floatValue, j7, stroke);
                                    }
                                };
                                startRestartGroup.updateRememberedValue(rememberedValue3);
                            } else {
                                j6 = j5;
                                j7 = j3;
                                f5 = f3;
                            }
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            CanvasKt.Canvas(m930size3ABfNKs, (Function1) rememberedValue3, startRestartGroup, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            f6 = f5;
                            j4 = j6;
                            j8 = j7;
                        }
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                        }
                        if ((i3 & 16) != 0) {
                            i4 &= -57345;
                        }
                        j5 = j4;
                    }
                    m2515getCircularIndicatorTrackGapSizeD9Ej5fM = f4;
                    i9 = i7;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291619137, "CC(remember):ProgressIndicator.kt#9igjgp");
                    if ((i4 & 14) != 4) {
                    }
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (!z) {
                    }
                    rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Float invoke() {
                            return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue);
                    final Function0<Float> function022 = (Function0) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume2 = startRestartGroup.consume(localDensity2);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    final Stroke stroke2 = new Stroke(((Density) consume2).mo459toPx0680j_4(f3), 0.0f, i9, 0, null, 26, null);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291612378, "CC(remember):ProgressIndicator.kt#9igjgp");
                    changed = startRestartGroup.changed(function022);
                    rememberedValue2 = startRestartGroup.rememberedValue();
                    if (!changed) {
                    }
                    rememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function022.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    Modifier m930size3ABfNKs2 = SizeKt.m930size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) rememberedValue2), CircularIndicatorDiameter);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291606843, "CC(remember):ProgressIndicator.kt#9igjgp");
                    changed2 = startRestartGroup.changed(function022) | ((458752 & i4) != 131072) | ((3670016 & i4) != 1048576) | ((i4 & 7168) != 2048) | ((((57344 & i4) ^ 24576) <= 16384 && startRestartGroup.changed(j5)) || (i4 & 24576) == 16384) | startRestartGroup.changedInstance(stroke2) | ((((i4 & 896) ^ 384) <= 256 && startRestartGroup.changed(j3)) || (i4 & 384) == 256);
                    rememberedValue3 = startRestartGroup.rememberedValue();
                    if (changed2) {
                    }
                    j6 = j5;
                    j7 = j3;
                    f5 = f3;
                    rememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
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
                            float f7;
                            float floatValue = function022.invoke().floatValue() * 360.0f;
                            if (StrokeCap.m4991equalsimpl0(i9, StrokeCap.INSTANCE.m4995getButtKaPHkGw()) || Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc()) > Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) {
                                f7 = m2515getCircularIndicatorTrackGapSizeD9Ej5fM;
                            } else {
                                f7 = Dp.m7399constructorimpl(m2515getCircularIndicatorTrackGapSizeD9Ej5fM + f5);
                            }
                            float f8 = (f7 / ((float) (drawScope.mo455toDpu2uoSUM(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) * 3.141592653589793d))) * 360.0f;
                            ProgressIndicatorKt.m2538drawCircularIndicator42QJj7c(drawScope, 270.0f + floatValue + Math.min(floatValue, f8), (360.0f - floatValue) - (Math.min(floatValue, f8) * 2), j6, stroke2);
                            ProgressIndicatorKt.m2540drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, floatValue, j7, stroke2);
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue3);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    CanvasKt.Canvas(m930size3ABfNKs2, (Function1) rememberedValue3, startRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    f6 = f5;
                    j4 = j6;
                    j8 = j7;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    j8 = j3;
                    f6 = f3;
                    m2515getCircularIndicatorTrackGapSizeD9Ej5fM = f4;
                    i9 = i7;
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    final Modifier modifier3 = modifier2;
                    final long j9 = j4;
                    final int i11 = i9;
                    final float f7 = m2515getCircularIndicatorTrackGapSizeD9Ej5fM;
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$4
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
                            ProgressIndicatorKt.m2522CircularProgressIndicatorIyT6zlY(function0, modifier3, j8, f6, j9, i11, f7, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                    return;
                }
                return;
            }
            f3 = f;
            if ((i2 & 24576) == 0) {
            }
            i6 = i3 & 32;
            if (i6 != 0) {
            }
            i8 = i3 & 64;
            if (i8 != 0) {
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
            if (i5 != 0) {
            }
            if ((i3 & 16) != 0) {
            }
            if (i6 != 0) {
            }
            if (i8 != 0) {
            }
            m2515getCircularIndicatorTrackGapSizeD9Ej5fM = f4;
            i9 = i7;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291619137, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 14) != 4) {
            }
            rememberedValue = startRestartGroup.rememberedValue();
            if (!z) {
            }
            rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue);
            final Function0<Float> function0222 = (Function0) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ProvidableCompositionLocal<Density> localDensity22 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume22 = startRestartGroup.consume(localDensity22);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final Stroke stroke22 = new Stroke(((Density) consume22).mo459toPx0680j_4(f3), 0.0f, i9, 0, null, 26, null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291612378, "CC(remember):ProgressIndicator.kt#9igjgp");
            changed = startRestartGroup.changed(function0222);
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (!changed) {
            }
            rememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function0222.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue2);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier m930size3ABfNKs22 = SizeKt.m930size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) rememberedValue2), CircularIndicatorDiameter);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291606843, "CC(remember):ProgressIndicator.kt#9igjgp");
            changed2 = startRestartGroup.changed(function0222) | ((458752 & i4) != 131072) | ((3670016 & i4) != 1048576) | ((i4 & 7168) != 2048) | ((((57344 & i4) ^ 24576) <= 16384 && startRestartGroup.changed(j5)) || (i4 & 24576) == 16384) | startRestartGroup.changedInstance(stroke22) | ((((i4 & 896) ^ 384) <= 256 && startRestartGroup.changed(j3)) || (i4 & 384) == 256);
            rememberedValue3 = startRestartGroup.rememberedValue();
            if (changed2) {
            }
            j6 = j5;
            j7 = j3;
            f5 = f3;
            rememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
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
                    float f72;
                    float floatValue = function0222.invoke().floatValue() * 360.0f;
                    if (StrokeCap.m4991equalsimpl0(i9, StrokeCap.INSTANCE.m4995getButtKaPHkGw()) || Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc()) > Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) {
                        f72 = m2515getCircularIndicatorTrackGapSizeD9Ej5fM;
                    } else {
                        f72 = Dp.m7399constructorimpl(m2515getCircularIndicatorTrackGapSizeD9Ej5fM + f5);
                    }
                    float f8 = (f72 / ((float) (drawScope.mo455toDpu2uoSUM(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) * 3.141592653589793d))) * 360.0f;
                    ProgressIndicatorKt.m2538drawCircularIndicator42QJj7c(drawScope, 270.0f + floatValue + Math.min(floatValue, f8), (360.0f - floatValue) - (Math.min(floatValue, f8) * 2), j6, stroke22);
                    ProgressIndicatorKt.m2540drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, floatValue, j7, stroke22);
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue3);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            CanvasKt.Canvas(m930size3ABfNKs22, (Function1) rememberedValue3, startRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            f6 = f5;
            j4 = j6;
            j8 = j7;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        if ((i2 & 384) != 0) {
        }
        i5 = i3 & 8;
        if (i5 == 0) {
        }
        f3 = f;
        if ((i2 & 24576) == 0) {
        }
        i6 = i3 & 32;
        if (i6 != 0) {
        }
        i8 = i3 & 64;
        if (i8 != 0) {
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
        if (i5 != 0) {
        }
        if ((i3 & 16) != 0) {
        }
        if (i6 != 0) {
        }
        if (i8 != 0) {
        }
        m2515getCircularIndicatorTrackGapSizeD9Ej5fM = f4;
        i9 = i7;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291619137, "CC(remember):ProgressIndicator.kt#9igjgp");
        if ((i4 & 14) != 4) {
        }
        rememberedValue = startRestartGroup.rememberedValue();
        if (!z) {
        }
        rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$coercedProgress$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(RangesKt.coerceIn(function0.invoke().floatValue(), 0.0f, 1.0f));
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue);
        final Function0<Float> function02222 = (Function0) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ProvidableCompositionLocal<Density> localDensity222 = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume222 = startRestartGroup.consume(localDensity222);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        final Stroke stroke222 = new Stroke(((Density) consume222).mo459toPx0680j_4(f3), 0.0f, i9, 0, null, 26, null);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291612378, "CC(remember):ProgressIndicator.kt#9igjgp");
        changed = startRestartGroup.changed(function02222);
        rememberedValue2 = startRestartGroup.rememberedValue();
        if (!changed) {
        }
        rememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(function02222.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue2);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Modifier m930size3ABfNKs222 = SizeKt.m930size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) rememberedValue2), CircularIndicatorDiameter);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291606843, "CC(remember):ProgressIndicator.kt#9igjgp");
        changed2 = startRestartGroup.changed(function02222) | ((458752 & i4) != 131072) | ((3670016 & i4) != 1048576) | ((i4 & 7168) != 2048) | ((((57344 & i4) ^ 24576) <= 16384 && startRestartGroup.changed(j5)) || (i4 & 24576) == 16384) | startRestartGroup.changedInstance(stroke222) | ((((i4 & 896) ^ 384) <= 256 && startRestartGroup.changed(j3)) || (i4 & 384) == 256);
        rememberedValue3 = startRestartGroup.rememberedValue();
        if (changed2) {
        }
        j6 = j5;
        j7 = j3;
        f5 = f3;
        rememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$3$1
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
                float f72;
                float floatValue = function02222.invoke().floatValue() * 360.0f;
                if (StrokeCap.m4991equalsimpl0(i9, StrokeCap.INSTANCE.m4995getButtKaPHkGw()) || Size.m4409getHeightimpl(drawScope.mo5121getSizeNHjbRc()) > Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) {
                    f72 = m2515getCircularIndicatorTrackGapSizeD9Ej5fM;
                } else {
                    f72 = Dp.m7399constructorimpl(m2515getCircularIndicatorTrackGapSizeD9Ej5fM + f5);
                }
                float f8 = (f72 / ((float) (drawScope.mo455toDpu2uoSUM(Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc())) * 3.141592653589793d))) * 360.0f;
                ProgressIndicatorKt.m2538drawCircularIndicator42QJj7c(drawScope, 270.0f + floatValue + Math.min(floatValue, f8), (360.0f - floatValue) - (Math.min(floatValue, f8) * 2), j6, stroke222);
                ProgressIndicatorKt.m2540drawDeterminateCircularIndicator42QJj7c(drawScope, 270.0f, floatValue, j7, stroke222);
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue3);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        CanvasKt.Canvas(m930size3ABfNKs222, (Function1) rememberedValue3, startRestartGroup, 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        f6 = f5;
        j4 = j6;
        j8 = j7;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0083  */
    /* renamed from: CircularProgressIndicator-LxG7B9w, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2523CircularProgressIndicatorLxG7B9w(Modifier modifier, long j, float f, long j2, int i, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        long j3;
        float f2;
        long j4;
        int i5;
        int i6;
        Modifier.Companion companion;
        int m2514getCircularIndeterminateStrokeCapKaPHkGw;
        int i7;
        long j5;
        long j6;
        boolean z;
        Object rememberedValue;
        final long j7;
        final long j8;
        final long j9;
        final float f3;
        final long j10;
        final int i8;
        final Modifier modifier3;
        ScopeUpdateScope endRestartGroup;
        Composer startRestartGroup = composer.startRestartGroup(-115871647);
        ComposerKt.sourceInformation(startRestartGroup, "C(CircularProgressIndicator)P(1,0:c#ui.graphics.Color,3:c#ui.unit.Dp,4:c#ui.graphics.Color,2:c#ui.graphics.StrokeCap)633@26429L13,635@26563L31,*638@26720L7,640@26807L28,643@26972L350,657@27444L208,666@27777L422,679@28240L431,691@28745L628,691@28676L697:ProgressIndicator.kt#uh7d8r");
        int i9 = i3 & 1;
        if (i9 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (startRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            j3 = j;
            i4 |= ((i3 & 2) == 0 && startRestartGroup.changed(j3)) ? 32 : 16;
        } else {
            j3 = j;
        }
        int i10 = i3 & 4;
        if (i10 != 0) {
            i4 |= 384;
        } else if ((i2 & 384) == 0) {
            f2 = f;
            i4 |= startRestartGroup.changed(f2) ? 256 : 128;
            if ((i2 & 3072) != 0) {
                j4 = j2;
                i4 |= ((i3 & 8) == 0 && startRestartGroup.changed(j4)) ? 2048 : 1024;
            } else {
                j4 = j2;
            }
            i5 = i3 & 16;
            if (i5 == 0) {
                i4 |= 24576;
            } else if ((i2 & 24576) == 0) {
                i6 = i;
                i4 |= startRestartGroup.changed(i6) ? 16384 : 8192;
                if ((i4 & 9363) == 9362 || !startRestartGroup.getSkipping()) {
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                        companion = i9 != 0 ? Modifier.INSTANCE : modifier2;
                        if ((i3 & 2) != 0) {
                            j3 = ProgressIndicatorDefaults.INSTANCE.getCircularColor(startRestartGroup, 6);
                            i4 &= -113;
                        }
                        if (i10 != 0) {
                            f2 = ProgressIndicatorDefaults.INSTANCE.m2516getCircularStrokeWidthD9Ej5fM();
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                            j4 = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor(startRestartGroup, 6);
                        }
                        if (i5 != 0) {
                            m2514getCircularIndeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2514getCircularIndeterminateStrokeCapKaPHkGw();
                            i7 = i4;
                            j5 = j3;
                            final float f4 = f2;
                            j6 = j4;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-115871647, i7, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:637)");
                            }
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object consume = startRestartGroup.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            final Stroke stroke = new Stroke(((Density) consume).mo459toPx0680j_4(f4), 0.0f, m2514getCircularIndeterminateStrokeCapKaPHkGw, 0, null, 26, null);
                            int i11 = m2514getCircularIndeterminateStrokeCapKaPHkGw;
                            InfiniteTransition rememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(null, startRestartGroup, 0, 1);
                            Modifier modifier4 = companion;
                            boolean z2 = true;
                            final State animateValue = InfiniteTransitionKt.animateValue(rememberInfiniteTransition, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
                            final State<Float> animateFloat = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, BaseRotationAngle, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                            final State<Float> animateFloat2 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, JumpRotationAngle, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$endAngle$1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                    invoke2(keyframesSpecConfig);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                    CubicBezierEasing cubicBezierEasing;
                                    keyframesSpecConfig.setDurationMillis(1332);
                                    KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 0);
                                    cubicBezierEasing = ProgressIndicatorKt.CircularEasing;
                                    keyframesSpecConfig.using(at, cubicBezierEasing);
                                    keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(290.0f), 666);
                                }
                            }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                            final State<Float> animateFloat3 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition, 0.0f, JumpRotationAngle, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$startAngle$1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                    invoke2(keyframesSpecConfig);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                                    CubicBezierEasing cubicBezierEasing;
                                    keyframesSpecConfig.setDurationMillis(1332);
                                    KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 666);
                                    cubicBezierEasing = ProgressIndicatorKt.CircularEasing;
                                    keyframesSpecConfig.using(at, cubicBezierEasing);
                                    keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(290.0f), keyframesSpecConfig.getDurationMillis());
                                }
                            }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                            Modifier m930size3ABfNKs = SizeKt.m930size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier4), CircularIndicatorDiameter);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291474284, "CC(remember):ProgressIndicator.kt#9igjgp");
                            boolean changedInstance = ((i7 & 896) != 256) | ((((i7 & 7168) ^ 3072) <= 2048 && startRestartGroup.changed(j6)) || (i7 & 3072) == 2048) | startRestartGroup.changedInstance(stroke) | startRestartGroup.changed(animateValue) | startRestartGroup.changed(animateFloat2) | startRestartGroup.changed(animateFloat3) | startRestartGroup.changed(animateFloat);
                            if ((((i7 & 112) ^ 48) > 32 || !startRestartGroup.changed(j5)) && (i7 & 48) != 32) {
                                z2 = false;
                            }
                            z = changedInstance | z2;
                            rememberedValue = startRestartGroup.rememberedValue();
                            if (!z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                j7 = j5;
                                j8 = j6;
                                rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
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
                                        ProgressIndicatorKt.m2539drawCircularIndicatorTrackbw27NRU(drawScope, j8, stroke);
                                        ProgressIndicatorKt.m2541drawIndeterminateCircularIndicatorhrjfTZI(drawScope, animateFloat3.getValue().floatValue() + (((animateValue.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + animateFloat.getValue().floatValue(), f4, Math.abs(animateFloat2.getValue().floatValue() - animateFloat3.getValue().floatValue()), j7, stroke);
                                    }
                                };
                                startRestartGroup.updateRememberedValue(rememberedValue);
                            } else {
                                j7 = j5;
                                j8 = j6;
                            }
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            CanvasKt.Canvas(m930size3ABfNKs, (Function1) rememberedValue, startRestartGroup, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            j9 = j8;
                            f3 = f4;
                            j10 = j7;
                            i8 = i11;
                            modifier3 = modifier4;
                        }
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        if ((i3 & 2) != 0) {
                            i4 &= -113;
                        }
                        if ((i3 & 8) != 0) {
                            i4 &= -7169;
                        }
                        companion = modifier2;
                    }
                    i7 = i4;
                    m2514getCircularIndeterminateStrokeCapKaPHkGw = i6;
                    j5 = j3;
                    final float f42 = f2;
                    j6 = j4;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume2 = startRestartGroup.consume(localDensity2);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    final Stroke stroke2 = new Stroke(((Density) consume2).mo459toPx0680j_4(f42), 0.0f, m2514getCircularIndeterminateStrokeCapKaPHkGw, 0, null, 26, null);
                    int i112 = m2514getCircularIndeterminateStrokeCapKaPHkGw;
                    InfiniteTransition rememberInfiniteTransition2 = InfiniteTransitionKt.rememberInfiniteTransition(null, startRestartGroup, 0, 1);
                    Modifier modifier42 = companion;
                    boolean z22 = true;
                    final State<Integer> animateValue2 = InfiniteTransitionKt.animateValue(rememberInfiniteTransition2, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
                    final State<Float> animateFloat4 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition2, 0.0f, BaseRotationAngle, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    final State<Float> animateFloat22 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition2, 0.0f, JumpRotationAngle, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$endAngle$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            invoke2(keyframesSpecConfig);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            CubicBezierEasing cubicBezierEasing;
                            keyframesSpecConfig.setDurationMillis(1332);
                            KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 0);
                            cubicBezierEasing = ProgressIndicatorKt.CircularEasing;
                            keyframesSpecConfig.using(at, cubicBezierEasing);
                            keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(290.0f), 666);
                        }
                    }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    final State<Float> animateFloat32 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition2, 0.0f, JumpRotationAngle, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$startAngle$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            invoke2(keyframesSpecConfig);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                            CubicBezierEasing cubicBezierEasing;
                            keyframesSpecConfig.setDurationMillis(1332);
                            KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 666);
                            cubicBezierEasing = ProgressIndicatorKt.CircularEasing;
                            keyframesSpecConfig.using(at, cubicBezierEasing);
                            keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(290.0f), keyframesSpecConfig.getDurationMillis());
                        }
                    }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
                    Modifier m930size3ABfNKs2 = SizeKt.m930size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier42), CircularIndicatorDiameter);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291474284, "CC(remember):ProgressIndicator.kt#9igjgp");
                    boolean changedInstance2 = ((i7 & 896) != 256) | ((((i7 & 7168) ^ 3072) <= 2048 && startRestartGroup.changed(j6)) || (i7 & 3072) == 2048) | startRestartGroup.changedInstance(stroke2) | startRestartGroup.changed(animateValue2) | startRestartGroup.changed(animateFloat22) | startRestartGroup.changed(animateFloat32) | startRestartGroup.changed(animateFloat4);
                    if (((i7 & 112) ^ 48) > 32) {
                    }
                    z22 = false;
                    z = changedInstance2 | z22;
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (z) {
                    }
                    j7 = j5;
                    j8 = j6;
                    rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
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
                            ProgressIndicatorKt.m2539drawCircularIndicatorTrackbw27NRU(drawScope, j8, stroke2);
                            ProgressIndicatorKt.m2541drawIndeterminateCircularIndicatorhrjfTZI(drawScope, animateFloat32.getValue().floatValue() + (((animateValue2.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + animateFloat4.getValue().floatValue(), f42, Math.abs(animateFloat22.getValue().floatValue() - animateFloat32.getValue().floatValue()), j7, stroke2);
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    CanvasKt.Canvas(m930size3ABfNKs2, (Function1) rememberedValue, startRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    j9 = j8;
                    f3 = f42;
                    j10 = j7;
                    i8 = i112;
                    modifier3 = modifier42;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    i8 = i6;
                    j10 = j3;
                    f3 = f2;
                    j9 = j4;
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$6
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
                            ProgressIndicatorKt.m2523CircularProgressIndicatorLxG7B9w(Modifier.this, j10, f3, j9, i8, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                        }
                    });
                    return;
                }
                return;
            }
            i6 = i;
            if ((i4 & 9363) == 9362) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i9 != 0) {
            }
            if ((i3 & 2) != 0) {
            }
            if (i10 != 0) {
            }
            if ((i3 & 8) != 0) {
            }
            if (i5 != 0) {
            }
            i7 = i4;
            m2514getCircularIndeterminateStrokeCapKaPHkGw = i6;
            j5 = j3;
            final float f422 = f2;
            j6 = j4;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            ProvidableCompositionLocal<Density> localDensity22 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume22 = startRestartGroup.consume(localDensity22);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final Stroke stroke22 = new Stroke(((Density) consume22).mo459toPx0680j_4(f422), 0.0f, m2514getCircularIndeterminateStrokeCapKaPHkGw, 0, null, 26, null);
            int i1122 = m2514getCircularIndeterminateStrokeCapKaPHkGw;
            InfiniteTransition rememberInfiniteTransition22 = InfiniteTransitionKt.rememberInfiniteTransition(null, startRestartGroup, 0, 1);
            Modifier modifier422 = companion;
            boolean z222 = true;
            final State<Integer> animateValue22 = InfiniteTransitionKt.animateValue(rememberInfiniteTransition22, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
            final State<Float> animateFloat42 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition22, 0.0f, BaseRotationAngle, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> animateFloat222 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition22, 0.0f, JumpRotationAngle, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$endAngle$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    CubicBezierEasing cubicBezierEasing;
                    keyframesSpecConfig.setDurationMillis(1332);
                    KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 0);
                    cubicBezierEasing = ProgressIndicatorKt.CircularEasing;
                    keyframesSpecConfig.using(at, cubicBezierEasing);
                    keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(290.0f), 666);
                }
            }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> animateFloat322 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition22, 0.0f, JumpRotationAngle, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$startAngle$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    invoke2(keyframesSpecConfig);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                    CubicBezierEasing cubicBezierEasing;
                    keyframesSpecConfig.setDurationMillis(1332);
                    KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 666);
                    cubicBezierEasing = ProgressIndicatorKt.CircularEasing;
                    keyframesSpecConfig.using(at, cubicBezierEasing);
                    keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(290.0f), keyframesSpecConfig.getDurationMillis());
                }
            }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            Modifier m930size3ABfNKs22 = SizeKt.m930size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier422), CircularIndicatorDiameter);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291474284, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean changedInstance22 = ((i7 & 896) != 256) | ((((i7 & 7168) ^ 3072) <= 2048 && startRestartGroup.changed(j6)) || (i7 & 3072) == 2048) | startRestartGroup.changedInstance(stroke22) | startRestartGroup.changed(animateValue22) | startRestartGroup.changed(animateFloat222) | startRestartGroup.changed(animateFloat322) | startRestartGroup.changed(animateFloat42);
            if (((i7 & 112) ^ 48) > 32) {
            }
            z222 = false;
            z = changedInstance22 | z222;
            rememberedValue = startRestartGroup.rememberedValue();
            if (z) {
            }
            j7 = j5;
            j8 = j6;
            rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
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
                    ProgressIndicatorKt.m2539drawCircularIndicatorTrackbw27NRU(drawScope, j8, stroke22);
                    ProgressIndicatorKt.m2541drawIndeterminateCircularIndicatorhrjfTZI(drawScope, animateFloat322.getValue().floatValue() + (((animateValue22.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + animateFloat42.getValue().floatValue(), f422, Math.abs(animateFloat222.getValue().floatValue() - animateFloat322.getValue().floatValue()), j7, stroke22);
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            CanvasKt.Canvas(m930size3ABfNKs22, (Function1) rememberedValue, startRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            j9 = j8;
            f3 = f422;
            j10 = j7;
            i8 = i1122;
            modifier3 = modifier422;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        f2 = f;
        if ((i2 & 3072) != 0) {
        }
        i5 = i3 & 16;
        if (i5 == 0) {
        }
        i6 = i;
        if ((i4 & 9363) == 9362) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i9 != 0) {
        }
        if ((i3 & 2) != 0) {
        }
        if (i10 != 0) {
        }
        if ((i3 & 8) != 0) {
        }
        if (i5 != 0) {
        }
        i7 = i4;
        m2514getCircularIndeterminateStrokeCapKaPHkGw = i6;
        j5 = j3;
        final float f4222 = f2;
        j6 = j4;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        ProvidableCompositionLocal<Density> localDensity222 = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume222 = startRestartGroup.consume(localDensity222);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        final Stroke stroke222 = new Stroke(((Density) consume222).mo459toPx0680j_4(f4222), 0.0f, m2514getCircularIndeterminateStrokeCapKaPHkGw, 0, null, 26, null);
        int i11222 = m2514getCircularIndeterminateStrokeCapKaPHkGw;
        InfiniteTransition rememberInfiniteTransition222 = InfiniteTransitionKt.rememberInfiniteTransition(null, startRestartGroup, 0, 1);
        Modifier modifier4222 = companion;
        boolean z2222 = true;
        final State<Integer> animateValue222 = InfiniteTransitionKt.animateValue(rememberInfiniteTransition222, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
        final State<Float> animateFloat422 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition222, 0.0f, BaseRotationAngle, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
        final State<Float> animateFloat2222 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition222, 0.0f, JumpRotationAngle, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$endAngle$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                invoke2(keyframesSpecConfig);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                CubicBezierEasing cubicBezierEasing;
                keyframesSpecConfig.setDurationMillis(1332);
                KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 0);
                cubicBezierEasing = ProgressIndicatorKt.CircularEasing;
                keyframesSpecConfig.using(at, cubicBezierEasing);
                keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(290.0f), 666);
            }
        }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
        final State<Float> animateFloat3222 = InfiniteTransitionKt.animateFloat(rememberInfiniteTransition222, 0.0f, JumpRotationAngle, AnimationSpecKt.m189infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$startAngle$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                invoke2(keyframesSpecConfig);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
                CubicBezierEasing cubicBezierEasing;
                keyframesSpecConfig.setDurationMillis(1332);
                KeyframesSpec.KeyframeEntity<Float> at = keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(0.0f), 666);
                cubicBezierEasing = ProgressIndicatorKt.CircularEasing;
                keyframesSpecConfig.using(at, cubicBezierEasing);
                keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) Float.valueOf(290.0f), keyframesSpecConfig.getDurationMillis());
            }
        }), null, 0L, 6, null), null, startRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
        Modifier m930size3ABfNKs222 = SizeKt.m930size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier4222), CircularIndicatorDiameter);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291474284, "CC(remember):ProgressIndicator.kt#9igjgp");
        boolean changedInstance222 = ((i7 & 896) != 256) | ((((i7 & 7168) ^ 3072) <= 2048 && startRestartGroup.changed(j6)) || (i7 & 3072) == 2048) | startRestartGroup.changedInstance(stroke222) | startRestartGroup.changed(animateValue222) | startRestartGroup.changed(animateFloat2222) | startRestartGroup.changed(animateFloat3222) | startRestartGroup.changed(animateFloat422);
        if (((i7 & 112) ^ 48) > 32) {
        }
        z2222 = false;
        z = changedInstance222 | z2222;
        rememberedValue = startRestartGroup.rememberedValue();
        if (z) {
        }
        j7 = j5;
        j8 = j6;
        rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$5$1
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
                ProgressIndicatorKt.m2539drawCircularIndicatorTrackbw27NRU(drawScope, j8, stroke222);
                ProgressIndicatorKt.m2541drawIndeterminateCircularIndicatorhrjfTZI(drawScope, animateFloat3222.getValue().floatValue() + (((animateValue222.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f) + animateFloat422.getValue().floatValue(), f4222, Math.abs(animateFloat2222.getValue().floatValue() - animateFloat3222.getValue().floatValue()), j7, stroke222);
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        CanvasKt.Canvas(m930size3ABfNKs222, (Function1) rememberedValue, startRestartGroup, 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        j9 = j8;
        f3 = f4222;
        j10 = j7;
        i8 = i11222;
        modifier3 = modifier4222;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0067  */
    @Deprecated(message = "Use the overload that takes `progress` as a lambda", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(\nprogress = { progress },\nmodifier = modifier,\ncolor = color,\nstrokeWidth = strokeWidth,\ntrackColor = trackColor,\nstrokeCap = strokeCap,\n)", imports = {}))
    /* renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2520CircularProgressIndicatorDUhRLBM(final float f, Modifier modifier, long j, float f2, long j2, int i, Composer composer, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        long j3;
        int i5;
        float f3;
        long j4;
        int i6;
        int i7;
        int m2513getCircularDeterminateStrokeCapKaPHkGw;
        float f4;
        long j5;
        int i8;
        Modifier modifier3;
        boolean z;
        Object rememberedValue;
        Composer composer2;
        final Modifier modifier4;
        final long j6;
        final float f5;
        final long j7;
        final int i9;
        ScopeUpdateScope endRestartGroup;
        int i10;
        int i11;
        Composer startRestartGroup = composer.startRestartGroup(-1472321743);
        ComposerKt.sourceInformation(startRestartGroup, "C(CircularProgressIndicator)P(2,1,0:c#ui.graphics.Color,4:c#ui.unit.Dp,5:c#ui.graphics.Color,3:c#ui.graphics.StrokeCap)730@30011L13,732@30145L18,736@30302L12,735@30256L216:ProgressIndicator.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (startRestartGroup.changed(f) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i12 = i3 & 2;
        if (i12 != 0) {
            i4 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i2 & 384) != 0) {
                if ((i3 & 4) == 0) {
                    j3 = j;
                    if (startRestartGroup.changed(j3)) {
                        i11 = 256;
                        i4 |= i11;
                    }
                } else {
                    j3 = j;
                }
                i11 = 128;
                i4 |= i11;
            } else {
                j3 = j;
            }
            i5 = i3 & 8;
            if (i5 == 0) {
                i4 |= 3072;
            } else if ((i2 & 3072) == 0) {
                f3 = f2;
                i4 |= startRestartGroup.changed(f3) ? 2048 : 1024;
                if ((i2 & 24576) == 0) {
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
                i6 = i3 & 32;
                if (i6 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i2) == 0) {
                    i7 = i;
                    i4 |= startRestartGroup.changed(i7) ? 131072 : 65536;
                    if ((i4 & 74899) == 74898 || !startRestartGroup.getSkipping()) {
                        startRestartGroup.startDefaults();
                        if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                            if (i12 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                j3 = ProgressIndicatorDefaults.INSTANCE.getCircularColor(startRestartGroup, 6);
                                i4 &= -897;
                            }
                            if (i5 != 0) {
                                f3 = ProgressIndicatorDefaults.INSTANCE.m2516getCircularStrokeWidthD9Ej5fM();
                            }
                            if ((i3 & 16) != 0) {
                                j4 = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(startRestartGroup, 6);
                                i4 &= -57345;
                            }
                            if (i6 != 0) {
                                m2513getCircularDeterminateStrokeCapKaPHkGw = ProgressIndicatorDefaults.INSTANCE.m2513getCircularDeterminateStrokeCapKaPHkGw();
                                f4 = f3;
                                j5 = j4;
                                i8 = -1472321743;
                                modifier3 = modifier2;
                                long j8 = j3;
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i8, i4, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:735)");
                                }
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                                z = (i4 & 14) == 4;
                                rememberedValue = startRestartGroup.rememberedValue();
                                if (!z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Float invoke() {
                                            return Float.valueOf(f);
                                        }
                                    };
                                    startRestartGroup.updateRememberedValue(rememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                composer2 = startRestartGroup;
                                m2522CircularProgressIndicatorIyT6zlY((Function0) rememberedValue, modifier3, j8, f4, j5, m2513getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier4 = modifier3;
                                j6 = j8;
                                f5 = f4;
                                j7 = j5;
                                i9 = m2513getCircularDeterminateStrokeCapKaPHkGw;
                            }
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                            }
                            if ((i3 & 16) != 0) {
                                i4 &= -57345;
                            }
                        }
                        f4 = f3;
                        m2513getCircularDeterminateStrokeCapKaPHkGw = i7;
                        i8 = -1472321743;
                        modifier3 = modifier2;
                        j5 = j4;
                        long j82 = j3;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                        if ((i4 & 14) == 4) {
                        }
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (!z) {
                        }
                        rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(f);
                            }
                        };
                        startRestartGroup.updateRememberedValue(rememberedValue);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        composer2 = startRestartGroup;
                        m2522CircularProgressIndicatorIyT6zlY((Function0) rememberedValue, modifier3, j82, f4, j5, m2513getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        modifier4 = modifier3;
                        j6 = j82;
                        f5 = f4;
                        j7 = j5;
                        i9 = m2513getCircularDeterminateStrokeCapKaPHkGw;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        composer2 = startRestartGroup;
                        modifier4 = modifier2;
                        j6 = j3;
                        f5 = f3;
                        j7 = j4;
                        i9 = i7;
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$8
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
                                ProgressIndicatorKt.m2520CircularProgressIndicatorDUhRLBM(f, modifier4, j6, f5, j7, i9, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                            }
                        });
                        return;
                    }
                    return;
                }
                i7 = i;
                if ((i4 & 74899) == 74898) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i12 != 0) {
                }
                if ((i3 & 4) != 0) {
                }
                if (i5 != 0) {
                }
                if ((i3 & 16) != 0) {
                }
                if (i6 != 0) {
                }
                f4 = f3;
                m2513getCircularDeterminateStrokeCapKaPHkGw = i7;
                i8 = -1472321743;
                modifier3 = modifier2;
                j5 = j4;
                long j822 = j3;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((i4 & 14) == 4) {
                }
                rememberedValue = startRestartGroup.rememberedValue();
                if (!z) {
                }
                rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(f);
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                composer2 = startRestartGroup;
                m2522CircularProgressIndicatorIyT6zlY((Function0) rememberedValue, modifier3, j822, f4, j5, m2513getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
                if (ComposerKt.isTraceInProgress()) {
                }
                modifier4 = modifier3;
                j6 = j822;
                f5 = f4;
                j7 = j5;
                i9 = m2513getCircularDeterminateStrokeCapKaPHkGw;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            f3 = f2;
            if ((i2 & 24576) == 0) {
            }
            i6 = i3 & 32;
            if (i6 != 0) {
            }
            i7 = i;
            if ((i4 & 74899) == 74898) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i12 != 0) {
            }
            if ((i3 & 4) != 0) {
            }
            if (i5 != 0) {
            }
            if ((i3 & 16) != 0) {
            }
            if (i6 != 0) {
            }
            f4 = f3;
            m2513getCircularDeterminateStrokeCapKaPHkGw = i7;
            i8 = -1472321743;
            modifier3 = modifier2;
            j5 = j4;
            long j8222 = j3;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((i4 & 14) == 4) {
            }
            rememberedValue = startRestartGroup.rememberedValue();
            if (!z) {
            }
            rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return Float.valueOf(f);
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            composer2 = startRestartGroup;
            m2522CircularProgressIndicatorIyT6zlY((Function0) rememberedValue, modifier3, j8222, f4, j5, m2513getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier4 = modifier3;
            j6 = j8222;
            f5 = f4;
            j7 = j5;
            i9 = m2513getCircularDeterminateStrokeCapKaPHkGw;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        if ((i2 & 384) != 0) {
        }
        i5 = i3 & 8;
        if (i5 == 0) {
        }
        f3 = f2;
        if ((i2 & 24576) == 0) {
        }
        i6 = i3 & 32;
        if (i6 != 0) {
        }
        i7 = i;
        if ((i4 & 74899) == 74898) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i12 != 0) {
        }
        if ((i3 & 4) != 0) {
        }
        if (i5 != 0) {
        }
        if ((i3 & 16) != 0) {
        }
        if (i6 != 0) {
        }
        f4 = f3;
        m2513getCircularDeterminateStrokeCapKaPHkGw = i7;
        i8 = -1472321743;
        modifier3 = modifier2;
        j5 = j4;
        long j82222 = j3;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -291425076, "CC(remember):ProgressIndicator.kt#9igjgp");
        if ((i4 & 14) == 4) {
        }
        rememberedValue = startRestartGroup.rememberedValue();
        if (!z) {
        }
        rememberedValue = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$7$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(f);
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        composer2 = startRestartGroup;
        m2522CircularProgressIndicatorIyT6zlY((Function0) rememberedValue, modifier3, j82222, f4, j5, m2513getCircularDeterminateStrokeCapKaPHkGw, 0.0f, composer2, i4 & 524272, 64);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier4 = modifier3;
        j6 = j82222;
        f5 = f4;
        j7 = j5;
        i9 = m2513getCircularDeterminateStrokeCapKaPHkGw;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0061  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: CircularProgressIndicator-MBs18nI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m2524CircularProgressIndicatorMBs18nI(final float f, Modifier modifier, long j, float f2, Composer composer, final int i, final int i2) {
        float f3;
        int i3;
        Modifier modifier2;
        long j2;
        int i4;
        float f4;
        float m2516getCircularStrokeWidthD9Ej5fM;
        long j3;
        Modifier modifier3;
        final Modifier modifier4;
        final long j4;
        final float f5;
        ScopeUpdateScope endRestartGroup;
        Composer startRestartGroup = composer.startRestartGroup(402841196);
        ComposerKt.sourceInformation(startRestartGroup, "C(CircularProgressIndicator)P(2,1,0:c#ui.graphics.Color,3:c#ui.unit.Dp)750@30727L13,758@30964L18,753@30818L247:ProgressIndicator.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            f3 = f;
        } else if ((i & 6) == 0) {
            f3 = f;
            i3 = (startRestartGroup.changed(f3) ? 4 : 2) | i;
        } else {
            f3 = f;
            i3 = i;
        }
        int i5 = i2 & 2;
        if (i5 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i & 384) != 0) {
                j2 = j;
                i3 |= ((i2 & 4) == 0 && startRestartGroup.changed(j2)) ? 256 : 128;
            } else {
                j2 = j;
            }
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                f4 = f2;
                i3 |= startRestartGroup.changed(f4) ? 2048 : 1024;
                if ((i3 & 1171) == 1170 || !startRestartGroup.getSkipping()) {
                    startRestartGroup.startDefaults();
                    if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                        Modifier.Companion companion = i5 != 0 ? Modifier.INSTANCE : modifier2;
                        if ((i2 & 4) != 0) {
                            j2 = ProgressIndicatorDefaults.INSTANCE.getCircularColor(startRestartGroup, 6);
                            i3 &= -897;
                        }
                        m2516getCircularStrokeWidthD9Ej5fM = i4 != 0 ? ProgressIndicatorDefaults.INSTANCE.m2516getCircularStrokeWidthD9Ej5fM() : f4;
                        j3 = j2;
                        modifier3 = companion;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        modifier3 = modifier2;
                        m2516getCircularStrokeWidthD9Ej5fM = f4;
                        j3 = j2;
                    }
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(402841196, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:753)");
                    }
                    float f6 = m2516getCircularStrokeWidthD9Ej5fM;
                    m2520CircularProgressIndicatorDUhRLBM(f3, modifier3, j3, f6, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(startRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2513getCircularDeterminateStrokeCapKaPHkGw(), startRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                    j4 = j3;
                    f5 = f6;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    modifier4 = modifier2;
                    j4 = j2;
                    f5 = f4;
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$9
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i6) {
                            ProgressIndicatorKt.m2524CircularProgressIndicatorMBs18nI(f, modifier4, j4, f5, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                    return;
                }
                return;
            }
            f4 = f2;
            if ((i3 & 1171) == 1170) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i5 != 0) {
            }
            if ((i2 & 4) != 0) {
            }
            if (i4 != 0) {
            }
            j3 = j2;
            modifier3 = companion;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            float f62 = m2516getCircularStrokeWidthD9Ej5fM;
            m2520CircularProgressIndicatorDUhRLBM(f3, modifier3, j3, f62, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(startRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2513getCircularDeterminateStrokeCapKaPHkGw(), startRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier4 = modifier3;
            j4 = j3;
            f5 = f62;
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
        f4 = f2;
        if ((i3 & 1171) == 1170) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i5 != 0) {
        }
        if ((i2 & 4) != 0) {
        }
        if (i4 != 0) {
        }
        j3 = j2;
        modifier3 = companion;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        float f622 = m2516getCircularStrokeWidthD9Ej5fM;
        m2520CircularProgressIndicatorDUhRLBM(f3, modifier3, j3, f622, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(startRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2513getCircularDeterminateStrokeCapKaPHkGw(), startRestartGroup, (i3 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i3 & 112) | (i3 & 896) | (i3 & 7168), 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier4 = modifier3;
        j4 = j3;
        f5 = f622;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009b  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: CircularProgressIndicator-aM-cp0Q, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m2525CircularProgressIndicatoraMcp0Q(Modifier modifier, long j, float f, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        long j2;
        float f2;
        float m2516getCircularStrokeWidthD9Ej5fM;
        long j3;
        Modifier modifier3;
        final Modifier modifier4;
        final long j4;
        final float f3;
        ScopeUpdateScope endRestartGroup;
        Composer startRestartGroup = composer.startRestartGroup(947193756);
        ComposerKt.sourceInformation(startRestartGroup, "C(CircularProgressIndicator)P(1,0:c#ui.graphics.Color,2:c#ui.unit.Dp)767@31299L13,774@31518L18,770@31390L231:ProgressIndicator.kt#uh7d8r");
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
        if ((i & 48) == 0) {
            j2 = j;
            i3 |= ((i2 & 2) == 0 && startRestartGroup.changed(j2)) ? 32 : 16;
        } else {
            j2 = j;
        }
        int i5 = i2 & 4;
        if (i5 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            f2 = f;
            i3 |= startRestartGroup.changed(f2) ? 256 : 128;
            if ((i3 & 147) == 146 || !startRestartGroup.getSkipping()) {
                startRestartGroup.startDefaults();
                if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                    Modifier.Companion companion = i4 == 0 ? Modifier.INSTANCE : modifier2;
                    if ((i2 & 2) != 0) {
                        j2 = ProgressIndicatorDefaults.INSTANCE.getCircularColor(startRestartGroup, 6);
                        i3 &= -113;
                    }
                    m2516getCircularStrokeWidthD9Ej5fM = i5 == 0 ? ProgressIndicatorDefaults.INSTANCE.m2516getCircularStrokeWidthD9Ej5fM() : f2;
                    j3 = j2;
                    modifier3 = companion;
                } else {
                    startRestartGroup.skipToGroupEnd();
                    if ((i2 & 2) != 0) {
                        i3 &= -113;
                    }
                    m2516getCircularStrokeWidthD9Ej5fM = f2;
                    j3 = j2;
                    modifier3 = modifier2;
                }
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(947193756, i3, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:770)");
                }
                m2523CircularProgressIndicatorLxG7B9w(modifier3, j3, m2516getCircularStrokeWidthD9Ej5fM, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(startRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2514getCircularIndeterminateStrokeCapKaPHkGw(), startRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                j4 = j3;
                f3 = m2516getCircularStrokeWidthD9Ej5fM;
            } else {
                startRestartGroup.skipToGroupEnd();
                modifier4 = modifier2;
                j4 = j2;
                f3 = f2;
            }
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup == null) {
                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$CircularProgressIndicator$10
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i6) {
                        ProgressIndicatorKt.m2525CircularProgressIndicatoraMcp0Q(Modifier.this, j4, f3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
                return;
            }
            return;
        }
        f2 = f;
        if ((i3 & 147) == 146) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i4 == 0) {
        }
        if ((i2 & 2) != 0) {
        }
        if (i5 == 0) {
        }
        j3 = j2;
        modifier3 = companion;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        m2523CircularProgressIndicatorLxG7B9w(modifier3, j3, m2516getCircularStrokeWidthD9Ej5fM, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor(startRestartGroup, 6), ProgressIndicatorDefaults.INSTANCE.m2514getCircularIndeterminateStrokeCapKaPHkGw(), startRestartGroup, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896), 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier4 = modifier3;
        j4 = j3;
        f3 = m2516getCircularStrokeWidthD9Ej5fM;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawCircularIndicator-42QJj7c, reason: not valid java name */
    public static final void m2538drawCircularIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, Stroke stroke) {
        float f3 = 2;
        float width = stroke.getWidth() / f3;
        float m4412getWidthimpl = Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc()) - (f3 * width);
        DrawScope.CC.m5191drawArcyD3GUKo$default(drawScope, j, f, f2, false, OffsetKt.Offset(width, width), androidx.compose.ui.geometry.SizeKt.Size(m4412getWidthimpl, m4412getWidthimpl), 0.0f, stroke, null, 0, 832, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawCircularIndicatorTrack-bw27NRU, reason: not valid java name */
    public static final void m2539drawCircularIndicatorTrackbw27NRU(DrawScope drawScope, long j, Stroke stroke) {
        m2538drawCircularIndicator42QJj7c(drawScope, 0.0f, 360.0f, j, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawDeterminateCircularIndicator-42QJj7c, reason: not valid java name */
    public static final void m2540drawDeterminateCircularIndicator42QJj7c(DrawScope drawScope, float f, float f2, long j, Stroke stroke) {
        m2538drawCircularIndicator42QJj7c(drawScope, f, f2, j, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawIndeterminateCircularIndicator-hrjfTZI, reason: not valid java name */
    public static final void m2541drawIndeterminateCircularIndicatorhrjfTZI(DrawScope drawScope, float f, float f2, float f3, long j, Stroke stroke) {
        m2538drawCircularIndicator42QJj7c(drawScope, f + (StrokeCap.m4991equalsimpl0(stroke.getCap(), StrokeCap.INSTANCE.m4995getButtKaPHkGw()) ? 0.0f : ((f2 / Dp.m7399constructorimpl(CircularIndicatorDiameter / 2)) * 57.29578f) / 2.0f), Math.max(f3, 0.1f), j, stroke);
    }

    public static final float getLinearIndicatorWidth() {
        return LinearIndicatorWidth;
    }

    public static final float getLinearIndicatorHeight() {
        return LinearIndicatorHeight;
    }

    public static final float getCircularIndicatorDiameter() {
        return CircularIndicatorDiameter;
    }

    static {
        float m7399constructorimpl = Dp.m7399constructorimpl(10);
        SemanticsBoundsPadding = m7399constructorimpl;
        IncreaseSemanticsBounds = PaddingKt.m880paddingVpY3zN4$default(SemanticsModifierKt.semantics(LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.material3.ProgressIndicatorKt$IncreaseSemanticsBounds$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                return m2543invoke3p2s80s(measureScope, measurable, constraints.getValue());
            }

            /* renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m2543invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                float f;
                f = ProgressIndicatorKt.SemanticsBoundsPadding;
                final int i = measureScope.mo453roundToPx0680j_4(f);
                int i2 = i * 2;
                final Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(ConstraintsKt.m7361offsetNN6EwU(j, 0, i2));
                return MeasureScope.CC.layout$default(measureScope, mo6055measureBRTryo0.getWidth(), mo6055measureBRTryo0.getHeight() - i2, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$IncreaseSemanticsBounds$1.1
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
                        Placeable.PlacementScope.place$default(placementScope, Placeable.this, 0, -i, 0.0f, 4, null);
                    }
                }, 4, null);
            }
        }), true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ProgressIndicatorKt$IncreaseSemanticsBounds$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }
        }), 0.0f, m7399constructorimpl, 1, null);
        LinearIndicatorWidth = Dp.m7399constructorimpl(240);
        LinearIndicatorHeight = ProgressIndicatorTokens.INSTANCE.m3653getTrackThicknessD9Ej5fM();
        CircularIndicatorDiameter = Dp.m7399constructorimpl(ProgressIndicatorTokens.INSTANCE.m3650getSizeD9Ej5fM() - Dp.m7399constructorimpl(ProgressIndicatorTokens.INSTANCE.m3653getTrackThicknessD9Ej5fM() * 2));
        FirstLineHeadEasing = new CubicBezierEasing(0.2f, 0.0f, 0.8f, 1.0f);
        FirstLineTailEasing = new CubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f);
        SecondLineHeadEasing = new CubicBezierEasing(0.0f, 0.0f, 0.65f, 1.0f);
        SecondLineTailEasing = new CubicBezierEasing(0.1f, 0.0f, 0.45f, 1.0f);
        CircularEasing = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);
    }
}
