package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.material3.internal.AnchoredDraggableKt;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.material3.internal.DraggableAnchors;
import androidx.compose.material3.internal.DraggableAnchorsConfig;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.SuspendPointerInputElement;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: ModalBottomSheet.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÄ\u0001\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00132\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010\n¢\u0006\u0002\b\u00182\u0013\b\u0002\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u001a0\n¢\u0006\u0002\b\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\b0\u001e¢\u0006\u0002\b\u0018¢\u0006\u0002\b H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a0\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u00132\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n2\u0006\u0010%\u001a\u00020&H\u0003ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a-\u0010)\u001a\u00020\u000e2\b\b\u0002\u0010*\u001a\u00020&2\u0014\b\u0002\u0010+\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020&0\u001eH\u0007¢\u0006\u0002\u0010-\u001aó\u0001\u0010.\u001a\u00020\b*\u00020/2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u000203012\u0006\u00104\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u00020\b0\n2!\u00107\u001a\u001d\u0012\u0013\u0012\u001102¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\b0\u001e2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00042\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010\n¢\u0006\u0002\b\u00182\u0013\b\u0002\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u001a0\n¢\u0006\u0002\b\u00182\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\b0\u001e¢\u0006\u0002\b\u0018¢\u0006\u0002\b H\u0001ø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a\u0014\u0010=\u001a\u000202*\u00020>2\u0006\u0010?\u001a\u000202H\u0002\u001a\u0014\u0010@\u001a\u000202*\u00020>2\u0006\u0010?\u001a\u000202H\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006A²\u0006\n\u0010B\u001a\u000202X\u008a\u0084\u0002"}, d2 = {"PredictiveBackChildTransformOrigin", "Landroidx/compose/ui/graphics/TransformOrigin;", "J", "PredictiveBackMaxScaleXDistance", "Landroidx/compose/ui/unit/Dp;", "F", "PredictiveBackMaxScaleYDistance", "ModalBottomSheet", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "sheetState", "Landroidx/compose/material3/SheetState;", "sheetMaxWidth", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "scrimColor", "dragHandle", "Landroidx/compose/runtime/Composable;", "contentWindowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "properties", "Landroidx/compose/material3/ModalBottomSheetProperties;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "ModalBottomSheet-dYc4hso", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;FLandroidx/compose/ui/graphics/Shape;JJFJLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/ModalBottomSheetProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "Scrim", "color", "visible", "", "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "rememberModalBottomSheetState", "skipPartiallyExpanded", "confirmValueChange", "Landroidx/compose/material3/SheetValue;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "ModalBottomSheetContent", "Landroidx/compose/foundation/layout/BoxScope;", "predictiveBackProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "animateToDismiss", "settleToDismiss", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "velocity", "ModalBottomSheetContent-IQkwcL4", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/animation/core/Animatable;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;FLandroidx/compose/ui/graphics/Shape;JJFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "calculatePredictiveBackScaleX", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", NotificationCompat.CATEGORY_PROGRESS, "calculatePredictiveBackScaleY", "material3_release", "alpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ModalBottomSheetKt {
    private static final float PredictiveBackMaxScaleXDistance = Dp.m7399constructorimpl(48);
    private static final float PredictiveBackMaxScaleYDistance = Dp.m7399constructorimpl(24);
    private static final long PredictiveBackChildTransformOrigin = TransformOriginKt.TransformOrigin(0.5f, 0.0f);

    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x04aa  */
    /* JADX WARN: Removed duplicated region for block: B:85:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01d1  */
    /* renamed from: ModalBottomSheet-dYc4hso, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2398ModalBottomSheetdYc4hso(final Function0<Unit> function0, Modifier modifier, SheetState sheetState, float f, Shape shape, long j, long j2, float f2, long j3, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, ? extends WindowInsets> function22, ModalBottomSheetProperties modalBottomSheetProperties, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        final SheetState sheetState2;
        int i5;
        float f3;
        Shape shape2;
        long j4;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function32;
        int i15;
        Shape shape3;
        long j5;
        float f4;
        long j6;
        ModalBottomSheetKt$ModalBottomSheet$1 modalBottomSheetKt$ModalBottomSheet$1;
        final float f5;
        ModalBottomSheetProperties modalBottomSheetProperties2;
        final float f6;
        final Function2<? super Composer, ? super Integer, Unit> function23;
        final Function2<? super Composer, ? super Integer, ? extends WindowInsets> function24;
        final Shape shape4;
        final long j7;
        final Modifier modifier3;
        final long j8;
        final long j9;
        Object rememberedValue;
        boolean changedInstance;
        Object rememberedValue2;
        boolean changedInstance2;
        Object rememberedValue3;
        Object rememberedValue4;
        boolean changedInstance3;
        Object rememberedValue5;
        Composer composer2;
        final SheetState sheetState3;
        final ModalBottomSheetProperties modalBottomSheetProperties3;
        final float f7;
        final Shape shape5;
        final long j10;
        final long j11;
        final float f8;
        final Function2<? super Composer, ? super Integer, Unit> function25;
        final Function2<? super Composer, ? super Integer, ? extends WindowInsets> function26;
        ScopeUpdateScope endRestartGroup;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        Composer startRestartGroup = composer.startRestartGroup(2132719801);
        ComposerKt.sourceInformation(startRestartGroup, "C(ModalBottomSheet)P(6,5,11,10:c#ui.unit.Dp,9,0:c#ui.graphics.Color,2:c#ui.graphics.Color,12:c#ui.unit.Dp,8:c#ui.graphics.Color,4,3,7)121@6012L31,123@6143L13,124@6206L14,125@6248L31,127@6356L10,133@6697L24,134@6761L327,145@7142L149,151@7326L42,155@7458L708,167@8231L771,153@7374L1628,193@9077L21,193@9050L48:ModalBottomSheet.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = (startRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        int i21 = i3 & 2;
        if (i21 != 0) {
            i4 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            if ((i & 384) != 0) {
                if ((i3 & 4) == 0) {
                    sheetState2 = sheetState;
                    if (startRestartGroup.changed(sheetState2)) {
                        i20 = 256;
                        i4 |= i20;
                    }
                } else {
                    sheetState2 = sheetState;
                }
                i20 = 128;
                i4 |= i20;
            } else {
                sheetState2 = sheetState;
            }
            i5 = i3 & 8;
            if (i5 == 0) {
                i4 |= 3072;
            } else if ((i & 3072) == 0) {
                f3 = f;
                i4 |= startRestartGroup.changed(f3) ? 2048 : 1024;
                if ((i & 24576) == 0) {
                    if ((i3 & 16) == 0) {
                        shape2 = shape;
                        if (startRestartGroup.changed(shape2)) {
                            i19 = 16384;
                            i4 |= i19;
                        }
                    } else {
                        shape2 = shape;
                    }
                    i19 = 8192;
                    i4 |= i19;
                } else {
                    shape2 = shape;
                }
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    i4 |= ((i3 & 32) == 0 && startRestartGroup.changed(j)) ? 131072 : 65536;
                }
                if ((i & 1572864) == 0) {
                    j4 = j2;
                    i4 |= ((i3 & 64) == 0 && startRestartGroup.changed(j4)) ? 1048576 : 524288;
                } else {
                    j4 = j2;
                }
                i6 = i3 & 128;
                if (i6 != 0) {
                    i4 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    i4 |= startRestartGroup.changed(f2) ? 8388608 : 4194304;
                }
                if ((i & 100663296) == 0) {
                    if ((i3 & 256) == 0) {
                        i17 = i4;
                        if (startRestartGroup.changed(j3)) {
                            i18 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            i7 = i17 | i18;
                        }
                    } else {
                        i17 = i4;
                    }
                    i18 = 33554432;
                    i7 = i17 | i18;
                } else {
                    i7 = i4;
                }
                i8 = i3 & 512;
                if (i8 != 0) {
                    i10 = i7 | 805306368;
                } else {
                    if ((i & 805306368) == 0) {
                        i9 = i7 | (startRestartGroup.changedInstance(function2) ? 536870912 : 268435456);
                    } else {
                        i9 = i7;
                    }
                    i10 = i9;
                }
                if ((i2 & 6) == 0) {
                    i11 = i6;
                    if ((i3 & 1024) == 0 && startRestartGroup.changedInstance(function22)) {
                        i16 = 4;
                        i12 = i2 | i16;
                    }
                    i16 = 2;
                    i12 = i2 | i16;
                } else {
                    i11 = i6;
                    i12 = i2;
                }
                i13 = i3 & 2048;
                if (i13 != 0) {
                    i12 |= 48;
                    i14 = i13;
                } else if ((i2 & 48) == 0) {
                    i14 = i13;
                    i12 |= startRestartGroup.changed(modalBottomSheetProperties) ? 32 : 16;
                } else {
                    i14 = i13;
                }
                int i22 = i12;
                if ((i3 & 4096) != 0) {
                    i22 |= 384;
                } else if ((i2 & 384) == 0) {
                    function32 = function3;
                    i22 |= startRestartGroup.changedInstance(function32) ? 256 : 128;
                    if ((306783379 & i10) == 306783378 || (i22 & 147) != 146 || !startRestartGroup.getSkipping()) {
                        startRestartGroup.startDefaults();
                        if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                            if (i21 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i10 &= -897;
                                sheetState2 = rememberModalBottomSheetState(false, null, startRestartGroup, 0, 3);
                            }
                            float m1948getSheetMaxWidthD9Ej5fM = i5 == 0 ? BottomSheetDefaults.INSTANCE.m1948getSheetMaxWidthD9Ej5fM() : f3;
                            if ((i3 & 16) == 0) {
                                i15 = 6;
                                shape3 = BottomSheetDefaults.INSTANCE.getExpandedShape(startRestartGroup, 6);
                                i10 &= -57345;
                            } else {
                                i15 = 6;
                                shape3 = shape2;
                            }
                            if ((i3 & 32) == 0) {
                                i10 &= -458753;
                                j5 = BottomSheetDefaults.INSTANCE.getContainerColor(startRestartGroup, i15);
                            } else {
                                j5 = j;
                            }
                            if ((i3 & 64) != 0) {
                                j4 = ColorSchemeKt.m2085contentColorForek8zF_U(j5, startRestartGroup, (i10 >> 15) & 14);
                                i10 &= -3670017;
                            }
                            int i23 = i10;
                            float m7399constructorimpl = i11 == 0 ? Dp.m7399constructorimpl(0) : f2;
                            if ((i3 & 256) == 0) {
                                f4 = m7399constructorimpl;
                                j6 = BottomSheetDefaults.INSTANCE.getScrimColor(startRestartGroup, 6);
                                i10 = i23 & (-234881025);
                            } else {
                                f4 = m7399constructorimpl;
                                i10 = i23;
                                j6 = j3;
                            }
                            Function2<? super Composer, ? super Integer, Unit> m2125getLambda1$material3_release = i8 == 0 ? ComposableSingletons$ModalBottomSheetKt.INSTANCE.m2125getLambda1$material3_release() : function2;
                            if ((i3 & 1024) == 0) {
                                modalBottomSheetKt$ModalBottomSheet$1 = new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$1
                                    public final WindowInsets invoke(Composer composer3, int i24) {
                                        composer3.startReplaceGroup(58488196);
                                        ComposerKt.sourceInformation(composer3, "C129@6530L12:ModalBottomSheet.kt#uh7d8r");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(58488196, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.kt:129)");
                                        }
                                        WindowInsets windowInsets = BottomSheetDefaults.INSTANCE.getWindowInsets(composer3, 6);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        composer3.endReplaceGroup();
                                        return windowInsets;
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer3, Integer num) {
                                        return invoke(composer3, num.intValue());
                                    }
                                };
                                i22 &= -15;
                            } else {
                                modalBottomSheetKt$ModalBottomSheet$1 = function22;
                            }
                            if (i14 == 0) {
                                modalBottomSheetProperties2 = ModalBottomSheetDefaults.INSTANCE.getProperties();
                                f5 = f4;
                            } else {
                                f5 = f4;
                                modalBottomSheetProperties2 = modalBottomSheetProperties;
                            }
                            f6 = m1948getSheetMaxWidthD9Ej5fM;
                            function23 = m2125getLambda1$material3_release;
                            function24 = modalBottomSheetKt$ModalBottomSheet$1;
                            shape4 = shape3;
                            j7 = j5;
                            modifier3 = modifier2;
                            j8 = j4;
                            j9 = j6;
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            if ((i3 & 4) != 0) {
                                i10 &= -897;
                            }
                            if ((i3 & 16) != 0) {
                                i10 &= -57345;
                            }
                            if ((i3 & 32) != 0) {
                                i10 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                i10 &= -3670017;
                            }
                            if ((i3 & 256) != 0) {
                                i10 &= -234881025;
                            }
                            if ((i3 & 1024) != 0) {
                                i22 &= -15;
                            }
                            j7 = j;
                            f5 = f2;
                            j9 = j3;
                            function23 = function2;
                            function24 = function22;
                            modalBottomSheetProperties2 = modalBottomSheetProperties;
                            f6 = f3;
                            shape4 = shape2;
                            modifier3 = modifier2;
                            j8 = j4;
                        }
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2132719801, i10, i22, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.kt:132)");
                        }
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -954363344, "CC(remember):Effects.kt#9igjgp");
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                            Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, startRestartGroup));
                            startRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                            rememberedValue = compositionScopedCoroutineScopeCanceller;
                        }
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011393839, "CC(remember):ModalBottomSheet.kt#9igjgp");
                        int i24 = (i10 & 896) ^ 384;
                        int i25 = i10 & 14;
                        changedInstance = ((i24 <= 256 && startRestartGroup.changed(sheetState2)) || (i10 & 384) == 256) | startRestartGroup.changedInstance(coroutineScope) | (i25 != 4);
                        rememberedValue2 = startRestartGroup.rememberedValue();
                        if (!changedInstance || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    Job launch$default;
                                    if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Hidden).booleanValue()) {
                                        launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(SheetState.this, null), 3, null);
                                        final SheetState sheetState4 = SheetState.this;
                                        final Function0<Unit> function02 = function0;
                                        launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1.2
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                                invoke2(th);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Throwable th) {
                                                if (SheetState.this.isVisible()) {
                                                    return;
                                                }
                                                function02.invoke();
                                            }
                                        });
                                    }
                                }

                                /* compiled from: ModalBottomSheet.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {138}, m = "invokeSuspend", n = {}, s = {})
                                /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1$1, reason: invalid class name */
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ SheetState $sheetState;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.$sheetState = sheetState;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass1(this.$sheetState, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            this.label = 1;
                                            if (this.$sheetState.hide(this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }
                            };
                            startRestartGroup.updateRememberedValue(rememberedValue2);
                        }
                        final Function0 function02 = (Function0) rememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011381825, "CC(remember):ModalBottomSheet.kt#9igjgp");
                        changedInstance2 = startRestartGroup.changedInstance(coroutineScope) | ((i24 <= 256 && startRestartGroup.changed(sheetState2)) || (i10 & 384) == 256) | (i25 != 4);
                        rememberedValue3 = startRestartGroup.rememberedValue();
                        if (!changedInstance2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue3 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Float f9) {
                                    invoke(f9.floatValue());
                                    return Unit.INSTANCE;
                                }

                                /* compiled from: ModalBottomSheet.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
                                /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1$1, reason: invalid class name */
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ float $it;
                                    final /* synthetic */ SheetState $sheetState;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(SheetState sheetState, float f, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.$sheetState = sheetState;
                                        this.$it = f;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass1(this.$sheetState, this.$it, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            this.label = 1;
                                            if (this.$sheetState.settle$material3_release(this.$it, this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }

                                public final void invoke(float f9) {
                                    Job launch$default;
                                    launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(sheetState2, f9, null), 3, null);
                                    final SheetState sheetState4 = sheetState2;
                                    final Function0<Unit> function03 = function0;
                                    launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1.2
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                            invoke2(th);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Throwable th) {
                                            if (SheetState.this.isVisible()) {
                                                return;
                                            }
                                            function03.invoke();
                                        }
                                    });
                                }
                            };
                            startRestartGroup.updateRememberedValue(rememberedValue3);
                        }
                        final Function1 function1 = (Function1) rememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011376044, "CC(remember):ModalBottomSheet.kt#9igjgp");
                        rememberedValue4 = startRestartGroup.rememberedValue();
                        if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue4 = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
                            startRestartGroup.updateRememberedValue(rememberedValue4);
                        }
                        final Animatable animatable = (Animatable) rememberedValue4;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011371154, "CC(remember):ModalBottomSheet.kt#9igjgp");
                        changedInstance3 = (i25 != 4) | ((i24 <= 256 && startRestartGroup.changed(sheetState2)) || (i10 & 384) == 256) | startRestartGroup.changedInstance(coroutineScope) | startRestartGroup.changedInstance(animatable);
                        rememberedValue5 = startRestartGroup.rememberedValue();
                        if (!changedInstance3 || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue5 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    Job launch$default;
                                    if (SheetState.this.getCurrentValue() != SheetValue.Expanded || !SheetState.this.getHasPartiallyExpandedState()) {
                                        launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass3(SheetState.this, null), 3, null);
                                        final Function0<Unit> function03 = function0;
                                        launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1.4
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                                invoke2(th);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Throwable th) {
                                                function03.invoke();
                                            }
                                        });
                                    } else {
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(animatable, null), 3, null);
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass2(SheetState.this, null), 3, null);
                                    }
                                }

                                /* compiled from: ModalBottomSheet.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$1", f = "ModalBottomSheet.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {})
                                /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$1, reason: invalid class name */
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ Animatable<Float, AnimationVector1D> $predictiveBackProgress;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.$predictiveBackProgress = animatable;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass1(this.$predictiveBackProgress, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            this.label = 1;
                                            if (Animatable.animateTo$default(this.$predictiveBackProgress, Boxing.boxFloat(0.0f), null, null, null, this, 14, null) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }

                                /* compiled from: ModalBottomSheet.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$2", f = "ModalBottomSheet.kt", i = {}, l = {162}, m = "invokeSuspend", n = {}, s = {})
                                /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$2, reason: invalid class name */
                                static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ SheetState $sheetState;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass2(SheetState sheetState, Continuation<? super AnonymousClass2> continuation) {
                                        super(2, continuation);
                                        this.$sheetState = sheetState;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass2(this.$sheetState, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            this.label = 1;
                                            if (this.$sheetState.partialExpand(this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }

                                /* compiled from: ModalBottomSheet.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$3", f = "ModalBottomSheet.kt", i = {}, l = {164}, m = "invokeSuspend", n = {}, s = {})
                                /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$3, reason: invalid class name */
                                static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ SheetState $sheetState;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass3(SheetState sheetState, Continuation<? super AnonymousClass3> continuation) {
                                        super(2, continuation);
                                        this.$sheetState = sheetState;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass3(this.$sheetState, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            this.label = 1;
                                            if (this.$sheetState.hide(this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }
                            };
                            startRestartGroup.updateRememberedValue(rememberedValue5);
                        }
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function33 = function32;
                        final SheetState sheetState4 = sheetState2;
                        ModalBottomSheet_androidKt.ModalBottomSheetDialog((Function0) rememberedValue5, modalBottomSheetProperties2, animatable, ComposableLambdaKt.rememberComposableLambda(-314673510, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$3
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

                            public final void invoke(Composer composer3, int i26) {
                                ComposerKt.sourceInformation(composer3, "C168@8241L755:ModalBottomSheet.kt#uh7d8r");
                                if ((i26 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-314673510, i26, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.kt:168)");
                                    }
                                    Modifier semantics$default = SemanticsModifierKt.semantics$default(WindowInsetsPadding_androidKt.imePadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null)), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$3.1
                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            invoke2(semanticsPropertyReceiver);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                            SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
                                        }
                                    }, 1, null);
                                    long j12 = j9;
                                    Function0<Unit> function03 = function02;
                                    SheetState sheetState5 = sheetState4;
                                    Animatable<Float, AnimationVector1D> animatable2 = animatable;
                                    CoroutineScope coroutineScope2 = coroutineScope;
                                    Function1<Float, Unit> function12 = function1;
                                    Modifier modifier4 = modifier3;
                                    float f9 = f6;
                                    Shape shape6 = shape4;
                                    long j13 = j7;
                                    long j14 = j8;
                                    float f10 = f5;
                                    Function2<Composer, Integer, Unit> function27 = function23;
                                    Function2<Composer, Integer, WindowInsets> function28 = function24;
                                    Function3<ColumnScope, Composer, Integer, Unit> function34 = function33;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, semantics$default);
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
                                    Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1107507610, "C169@8345L169,174@8527L459:ModalBottomSheet.kt#uh7d8r");
                                    ModalBottomSheetKt.m2400Scrim3JVO9M(j12, function03, sheetState5.getTargetValue() != SheetValue.Hidden, composer3, 0);
                                    ModalBottomSheetKt.m2399ModalBottomSheetContentIQkwcL4(boxScopeInstance, animatable2, coroutineScope2, function03, function12, modifier4, sheetState5, f9, shape6, j13, j14, f10, function27, function28, function34, composer3, (Animatable.$stable << 3) | 6, 0, 0);
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
                        }, startRestartGroup, 54), startRestartGroup, (i22 & 112) | 3072 | (Animatable.$stable << 6));
                        composer2 = startRestartGroup;
                        if (sheetState2.getHasExpandedState()) {
                            ComposerKt.sourceInformationMarkerStart(composer2, -2011320033, "CC(remember):ModalBottomSheet.kt#9igjgp");
                            boolean z = (i24 > 256 && composer2.changed(sheetState2)) || (i10 & 384) == 256;
                            Object rememberedValue6 = composer2.rememberedValue();
                            if (z || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                rememberedValue6 = (Function2) new ModalBottomSheetKt$ModalBottomSheet$4$1(sheetState2, null);
                                composer2.updateRememberedValue(rememberedValue6);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            EffectsKt.LaunchedEffect(sheetState2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue6, composer2, (i10 >> 6) & 14);
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        sheetState3 = sheetState2;
                        modalBottomSheetProperties3 = modalBottomSheetProperties2;
                        modifier2 = modifier3;
                        f7 = f6;
                        shape5 = shape4;
                        j10 = j7;
                        j11 = j8;
                        f8 = f5;
                        function25 = function23;
                        function26 = function24;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        j10 = j;
                        j9 = j3;
                        composer2 = startRestartGroup;
                        f7 = f3;
                        shape5 = shape2;
                        sheetState3 = sheetState2;
                        j11 = j4;
                        f8 = f2;
                        function25 = function2;
                        function26 = function22;
                        modalBottomSheetProperties3 = modalBottomSheetProperties;
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                        final Modifier modifier4 = modifier2;
                        final long j12 = j9;
                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$5
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

                            public final void invoke(Composer composer3, int i26) {
                                ModalBottomSheetKt.m2398ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, f7, shape5, j10, j11, f8, j12, function25, function26, modalBottomSheetProperties3, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                        return;
                    }
                    return;
                }
                function32 = function3;
                if ((306783379 & i10) == 306783378) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i21 != 0) {
                }
                if ((i3 & 4) != 0) {
                }
                if (i5 == 0) {
                }
                if ((i3 & 16) == 0) {
                }
                if ((i3 & 32) == 0) {
                }
                if ((i3 & 64) != 0) {
                }
                int i232 = i10;
                if (i11 == 0) {
                }
                if ((i3 & 256) == 0) {
                }
                if (i8 == 0) {
                }
                if ((i3 & 1024) == 0) {
                }
                if (i14 == 0) {
                }
                f6 = m1948getSheetMaxWidthD9Ej5fM;
                function23 = m2125getLambda1$material3_release;
                function24 = modalBottomSheetKt$ModalBottomSheet$1;
                shape4 = shape3;
                j7 = j5;
                modifier3 = modifier2;
                j8 = j4;
                j9 = j6;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -954363344, "CC(remember):Effects.kt#9igjgp");
                rememberedValue = startRestartGroup.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                final CoroutineScope coroutineScope2 = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011393839, "CC(remember):ModalBottomSheet.kt#9igjgp");
                int i242 = (i10 & 896) ^ 384;
                int i252 = i10 & 14;
                changedInstance = ((i242 <= 256 && startRestartGroup.changed(sheetState2)) || (i10 & 384) == 256) | startRestartGroup.changedInstance(coroutineScope2) | (i252 != 4);
                rememberedValue2 = startRestartGroup.rememberedValue();
                if (!changedInstance) {
                }
                rememberedValue2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Job launch$default;
                        if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Hidden).booleanValue()) {
                            launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(SheetState.this, null), 3, null);
                            final SheetState sheetState42 = SheetState.this;
                            final Function0<Unit> function022 = function0;
                            launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                    invoke2(th);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Throwable th) {
                                    if (SheetState.this.isVisible()) {
                                        return;
                                    }
                                    function022.invoke();
                                }
                            });
                        }
                    }

                    /* compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {138}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1$1, reason: invalid class name */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $sheetState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$sheetState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                this.label = 1;
                                if (this.$sheetState.hide(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue2);
                final Function0<Unit> function022 = (Function0) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011381825, "CC(remember):ModalBottomSheet.kt#9igjgp");
                changedInstance2 = startRestartGroup.changedInstance(coroutineScope2) | ((i242 <= 256 && startRestartGroup.changed(sheetState2)) || (i10 & 384) == 256) | (i252 != 4);
                rememberedValue3 = startRestartGroup.rememberedValue();
                if (!changedInstance2) {
                }
                rememberedValue3 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Float f9) {
                        invoke(f9.floatValue());
                        return Unit.INSTANCE;
                    }

                    /* compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1$1, reason: invalid class name */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ float $it;
                        final /* synthetic */ SheetState $sheetState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SheetState sheetState, float f, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                            this.$it = f;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$sheetState, this.$it, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                this.label = 1;
                                if (this.$sheetState.settle$material3_release(this.$it, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    public final void invoke(float f9) {
                        Job launch$default;
                        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(sheetState2, f9, null), 3, null);
                        final SheetState sheetState42 = sheetState2;
                        final Function0<Unit> function03 = function0;
                        launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable th) {
                                if (SheetState.this.isVisible()) {
                                    return;
                                }
                                function03.invoke();
                            }
                        });
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue3);
                final Function1<? super Float, Unit> function12 = (Function1) rememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011376044, "CC(remember):ModalBottomSheet.kt#9igjgp");
                rememberedValue4 = startRestartGroup.rememberedValue();
                if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                }
                final Animatable<Float, AnimationVector1D> animatable2 = (Animatable) rememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011371154, "CC(remember):ModalBottomSheet.kt#9igjgp");
                changedInstance3 = (i252 != 4) | ((i242 <= 256 && startRestartGroup.changed(sheetState2)) || (i10 & 384) == 256) | startRestartGroup.changedInstance(coroutineScope2) | startRestartGroup.changedInstance(animatable2);
                rememberedValue5 = startRestartGroup.rememberedValue();
                if (!changedInstance3) {
                }
                rememberedValue5 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Job launch$default;
                        if (SheetState.this.getCurrentValue() != SheetValue.Expanded || !SheetState.this.getHasPartiallyExpandedState()) {
                            launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass3(SheetState.this, null), 3, null);
                            final Function0<Unit> function03 = function0;
                            launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1.4
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                    invoke2(th);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Throwable th) {
                                    function03.invoke();
                                }
                            });
                        } else {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(animatable2, null), 3, null);
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass2(SheetState.this, null), 3, null);
                        }
                    }

                    /* compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$1", f = "ModalBottomSheet.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$1, reason: invalid class name */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Animatable<Float, AnimationVector1D> $predictiveBackProgress;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$predictiveBackProgress = animatable;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$predictiveBackProgress, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                this.label = 1;
                                if (Animatable.animateTo$default(this.$predictiveBackProgress, Boxing.boxFloat(0.0f), null, null, null, this, 14, null) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$2", f = "ModalBottomSheet.kt", i = {}, l = {162}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$2, reason: invalid class name */
                    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $sheetState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass2(SheetState sheetState, Continuation<? super AnonymousClass2> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass2(this.$sheetState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                this.label = 1;
                                if (this.$sheetState.partialExpand(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$3", f = "ModalBottomSheet.kt", i = {}, l = {164}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$3, reason: invalid class name */
                    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $sheetState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass3(SheetState sheetState, Continuation<? super AnonymousClass3> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass3(this.$sheetState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                this.label = 1;
                                if (this.$sheetState.hide(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue5);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function332 = function32;
                final SheetState sheetState42 = sheetState2;
                ModalBottomSheet_androidKt.ModalBottomSheetDialog((Function0) rememberedValue5, modalBottomSheetProperties2, animatable2, ComposableLambdaKt.rememberComposableLambda(-314673510, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$3
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

                    public final void invoke(Composer composer3, int i26) {
                        ComposerKt.sourceInformation(composer3, "C168@8241L755:ModalBottomSheet.kt#uh7d8r");
                        if ((i26 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-314673510, i26, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.kt:168)");
                            }
                            Modifier semantics$default = SemanticsModifierKt.semantics$default(WindowInsetsPadding_androidKt.imePadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null)), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$3.1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
                                }
                            }, 1, null);
                            long j122 = j9;
                            Function0<Unit> function03 = function022;
                            SheetState sheetState5 = sheetState42;
                            Animatable<Float, AnimationVector1D> animatable22 = animatable2;
                            CoroutineScope coroutineScope22 = coroutineScope2;
                            Function1<Float, Unit> function122 = function12;
                            Modifier modifier42 = modifier3;
                            float f9 = f6;
                            Shape shape6 = shape4;
                            long j13 = j7;
                            long j14 = j8;
                            float f10 = f5;
                            Function2<Composer, Integer, Unit> function27 = function23;
                            Function2<Composer, Integer, WindowInsets> function28 = function24;
                            Function3<ColumnScope, Composer, Integer, Unit> function34 = function332;
                            ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, semantics$default);
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
                            Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer3, 1107507610, "C169@8345L169,174@8527L459:ModalBottomSheet.kt#uh7d8r");
                            ModalBottomSheetKt.m2400Scrim3JVO9M(j122, function03, sheetState5.getTargetValue() != SheetValue.Hidden, composer3, 0);
                            ModalBottomSheetKt.m2399ModalBottomSheetContentIQkwcL4(boxScopeInstance, animatable22, coroutineScope22, function03, function122, modifier42, sheetState5, f9, shape6, j13, j14, f10, function27, function28, function34, composer3, (Animatable.$stable << 3) | 6, 0, 0);
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
                }, startRestartGroup, 54), startRestartGroup, (i22 & 112) | 3072 | (Animatable.$stable << 6));
                composer2 = startRestartGroup;
                if (sheetState2.getHasExpandedState()) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                sheetState3 = sheetState2;
                modalBottomSheetProperties3 = modalBottomSheetProperties2;
                modifier2 = modifier3;
                f7 = f6;
                shape5 = shape4;
                j10 = j7;
                j11 = j8;
                f8 = f5;
                function25 = function23;
                function26 = function24;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            f3 = f;
            if ((i & 24576) == 0) {
            }
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            }
            if ((i & 1572864) == 0) {
            }
            i6 = i3 & 128;
            if (i6 != 0) {
            }
            if ((i & 100663296) == 0) {
            }
            i8 = i3 & 512;
            if (i8 != 0) {
            }
            if ((i2 & 6) == 0) {
            }
            i13 = i3 & 2048;
            if (i13 != 0) {
            }
            int i222 = i12;
            if ((i3 & 4096) != 0) {
            }
            function32 = function3;
            if ((306783379 & i10) == 306783378) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i21 != 0) {
            }
            if ((i3 & 4) != 0) {
            }
            if (i5 == 0) {
            }
            if ((i3 & 16) == 0) {
            }
            if ((i3 & 32) == 0) {
            }
            if ((i3 & 64) != 0) {
            }
            int i2322 = i10;
            if (i11 == 0) {
            }
            if ((i3 & 256) == 0) {
            }
            if (i8 == 0) {
            }
            if ((i3 & 1024) == 0) {
            }
            if (i14 == 0) {
            }
            f6 = m1948getSheetMaxWidthD9Ej5fM;
            function23 = m2125getLambda1$material3_release;
            function24 = modalBottomSheetKt$ModalBottomSheet$1;
            shape4 = shape3;
            j7 = j5;
            modifier3 = modifier2;
            j8 = j4;
            j9 = j6;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -954363344, "CC(remember):Effects.kt#9igjgp");
            rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final CoroutineScope coroutineScope22 = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011393839, "CC(remember):ModalBottomSheet.kt#9igjgp");
            int i2422 = (i10 & 896) ^ 384;
            int i2522 = i10 & 14;
            changedInstance = ((i2422 <= 256 && startRestartGroup.changed(sheetState2)) || (i10 & 384) == 256) | startRestartGroup.changedInstance(coroutineScope22) | (i2522 != 4);
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (!changedInstance) {
            }
            rememberedValue2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Job launch$default;
                    if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Hidden).booleanValue()) {
                        launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope22, null, null, new AnonymousClass1(SheetState.this, null), 3, null);
                        final SheetState sheetState422 = SheetState.this;
                        final Function0<Unit> function0222 = function0;
                        launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable th) {
                                if (SheetState.this.isVisible()) {
                                    return;
                                }
                                function0222.invoke();
                            }
                        });
                    }
                }

                /* compiled from: ModalBottomSheet.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {138}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1$1, reason: invalid class name */
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ SheetState $sheetState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$sheetState = sheetState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$sheetState, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            if (this.$sheetState.hide(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue2);
            final Function0<Unit> function0222 = (Function0) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011381825, "CC(remember):ModalBottomSheet.kt#9igjgp");
            changedInstance2 = startRestartGroup.changedInstance(coroutineScope22) | ((i2422 <= 256 && startRestartGroup.changed(sheetState2)) || (i10 & 384) == 256) | (i2522 != 4);
            rememberedValue3 = startRestartGroup.rememberedValue();
            if (!changedInstance2) {
            }
            rememberedValue3 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Float f9) {
                    invoke(f9.floatValue());
                    return Unit.INSTANCE;
                }

                /* compiled from: ModalBottomSheet.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1$1, reason: invalid class name */
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ float $it;
                    final /* synthetic */ SheetState $sheetState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(SheetState sheetState, float f, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$sheetState = sheetState;
                        this.$it = f;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$sheetState, this.$it, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            if (this.$sheetState.settle$material3_release(this.$it, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                public final void invoke(float f9) {
                    Job launch$default;
                    launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(sheetState2, f9, null), 3, null);
                    final SheetState sheetState422 = sheetState2;
                    final Function0<Unit> function03 = function0;
                    launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                            invoke2(th);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Throwable th) {
                            if (SheetState.this.isVisible()) {
                                return;
                            }
                            function03.invoke();
                        }
                    });
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue3);
            final Function1<? super Float, Unit> function122 = (Function1) rememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011376044, "CC(remember):ModalBottomSheet.kt#9igjgp");
            rememberedValue4 = startRestartGroup.rememberedValue();
            if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
            }
            final Animatable<Float, AnimationVector1D> animatable22 = (Animatable) rememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011371154, "CC(remember):ModalBottomSheet.kt#9igjgp");
            changedInstance3 = (i2522 != 4) | ((i2422 <= 256 && startRestartGroup.changed(sheetState2)) || (i10 & 384) == 256) | startRestartGroup.changedInstance(coroutineScope22) | startRestartGroup.changedInstance(animatable22);
            rememberedValue5 = startRestartGroup.rememberedValue();
            if (!changedInstance3) {
            }
            rememberedValue5 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Job launch$default;
                    if (SheetState.this.getCurrentValue() != SheetValue.Expanded || !SheetState.this.getHasPartiallyExpandedState()) {
                        launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope22, null, null, new AnonymousClass3(SheetState.this, null), 3, null);
                        final Function0<Unit> function03 = function0;
                        launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable th) {
                                function03.invoke();
                            }
                        });
                    } else {
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope22, null, null, new AnonymousClass1(animatable22, null), 3, null);
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope22, null, null, new AnonymousClass2(SheetState.this, null), 3, null);
                    }
                }

                /* compiled from: ModalBottomSheet.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$1", f = "ModalBottomSheet.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$1, reason: invalid class name */
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Animatable<Float, AnimationVector1D> $predictiveBackProgress;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$predictiveBackProgress = animatable;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$predictiveBackProgress, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            if (Animatable.animateTo$default(this.$predictiveBackProgress, Boxing.boxFloat(0.0f), null, null, null, this, 14, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* compiled from: ModalBottomSheet.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$2", f = "ModalBottomSheet.kt", i = {}, l = {162}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$2, reason: invalid class name */
                static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ SheetState $sheetState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass2(SheetState sheetState, Continuation<? super AnonymousClass2> continuation) {
                        super(2, continuation);
                        this.$sheetState = sheetState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass2(this.$sheetState, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            if (this.$sheetState.partialExpand(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* compiled from: ModalBottomSheet.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$3", f = "ModalBottomSheet.kt", i = {}, l = {164}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$3, reason: invalid class name */
                static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ SheetState $sheetState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass3(SheetState sheetState, Continuation<? super AnonymousClass3> continuation) {
                        super(2, continuation);
                        this.$sheetState = sheetState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass3(this.$sheetState, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            if (this.$sheetState.hide(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue5);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3322 = function32;
            final SheetState sheetState422 = sheetState2;
            ModalBottomSheet_androidKt.ModalBottomSheetDialog((Function0) rememberedValue5, modalBottomSheetProperties2, animatable22, ComposableLambdaKt.rememberComposableLambda(-314673510, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$3
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

                public final void invoke(Composer composer3, int i26) {
                    ComposerKt.sourceInformation(composer3, "C168@8241L755:ModalBottomSheet.kt#uh7d8r");
                    if ((i26 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-314673510, i26, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.kt:168)");
                        }
                        Modifier semantics$default = SemanticsModifierKt.semantics$default(WindowInsetsPadding_androidKt.imePadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null)), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$3.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
                            }
                        }, 1, null);
                        long j122 = j9;
                        Function0<Unit> function03 = function0222;
                        SheetState sheetState5 = sheetState422;
                        Animatable<Float, AnimationVector1D> animatable222 = animatable22;
                        CoroutineScope coroutineScope222 = coroutineScope22;
                        Function1<Float, Unit> function1222 = function122;
                        Modifier modifier42 = modifier3;
                        float f9 = f6;
                        Shape shape6 = shape4;
                        long j13 = j7;
                        long j14 = j8;
                        float f10 = f5;
                        Function2<Composer, Integer, Unit> function27 = function23;
                        Function2<Composer, Integer, WindowInsets> function28 = function24;
                        Function3<ColumnScope, Composer, Integer, Unit> function34 = function3322;
                        ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, semantics$default);
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
                        Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer3, 1107507610, "C169@8345L169,174@8527L459:ModalBottomSheet.kt#uh7d8r");
                        ModalBottomSheetKt.m2400Scrim3JVO9M(j122, function03, sheetState5.getTargetValue() != SheetValue.Hidden, composer3, 0);
                        ModalBottomSheetKt.m2399ModalBottomSheetContentIQkwcL4(boxScopeInstance, animatable222, coroutineScope222, function03, function1222, modifier42, sheetState5, f9, shape6, j13, j14, f10, function27, function28, function34, composer3, (Animatable.$stable << 3) | 6, 0, 0);
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
            }, startRestartGroup, 54), startRestartGroup, (i222 & 112) | 3072 | (Animatable.$stable << 6));
            composer2 = startRestartGroup;
            if (sheetState2.getHasExpandedState()) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
            sheetState3 = sheetState2;
            modalBottomSheetProperties3 = modalBottomSheetProperties2;
            modifier2 = modifier3;
            f7 = f6;
            shape5 = shape4;
            j10 = j7;
            j11 = j8;
            f8 = f5;
            function25 = function23;
            function26 = function24;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        if ((i & 384) != 0) {
        }
        i5 = i3 & 8;
        if (i5 == 0) {
        }
        f3 = f;
        if ((i & 24576) == 0) {
        }
        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
        }
        if ((i & 1572864) == 0) {
        }
        i6 = i3 & 128;
        if (i6 != 0) {
        }
        if ((i & 100663296) == 0) {
        }
        i8 = i3 & 512;
        if (i8 != 0) {
        }
        if ((i2 & 6) == 0) {
        }
        i13 = i3 & 2048;
        if (i13 != 0) {
        }
        int i2222 = i12;
        if ((i3 & 4096) != 0) {
        }
        function32 = function3;
        if ((306783379 & i10) == 306783378) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i21 != 0) {
        }
        if ((i3 & 4) != 0) {
        }
        if (i5 == 0) {
        }
        if ((i3 & 16) == 0) {
        }
        if ((i3 & 32) == 0) {
        }
        if ((i3 & 64) != 0) {
        }
        int i23222 = i10;
        if (i11 == 0) {
        }
        if ((i3 & 256) == 0) {
        }
        if (i8 == 0) {
        }
        if ((i3 & 1024) == 0) {
        }
        if (i14 == 0) {
        }
        f6 = m1948getSheetMaxWidthD9Ej5fM;
        function23 = m2125getLambda1$material3_release;
        function24 = modalBottomSheetKt$ModalBottomSheet$1;
        shape4 = shape3;
        j7 = j5;
        modifier3 = modifier2;
        j8 = j4;
        j9 = j6;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -954363344, "CC(remember):Effects.kt#9igjgp");
        rememberedValue = startRestartGroup.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
        }
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        final CoroutineScope coroutineScope222 = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011393839, "CC(remember):ModalBottomSheet.kt#9igjgp");
        int i24222 = (i10 & 896) ^ 384;
        int i25222 = i10 & 14;
        changedInstance = ((i24222 <= 256 && startRestartGroup.changed(sheetState2)) || (i10 & 384) == 256) | startRestartGroup.changedInstance(coroutineScope222) | (i25222 != 4);
        rememberedValue2 = startRestartGroup.rememberedValue();
        if (!changedInstance) {
        }
        rememberedValue2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Job launch$default;
                if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Hidden).booleanValue()) {
                    launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope222, null, null, new AnonymousClass1(SheetState.this, null), 3, null);
                    final SheetState sheetState4222 = SheetState.this;
                    final Function0<Unit> function02222 = function0;
                    launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                            invoke2(th);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Throwable th) {
                            if (SheetState.this.isVisible()) {
                                return;
                            }
                            function02222.invoke();
                        }
                    });
                }
            }

            /* compiled from: ModalBottomSheet.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {138}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1$1, reason: invalid class name */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ SheetState $sheetState;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$sheetState = sheetState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$sheetState, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (this.$sheetState.hide(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue2);
        final Function0<Unit> function02222 = (Function0) rememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011381825, "CC(remember):ModalBottomSheet.kt#9igjgp");
        changedInstance2 = startRestartGroup.changedInstance(coroutineScope222) | ((i24222 <= 256 && startRestartGroup.changed(sheetState2)) || (i10 & 384) == 256) | (i25222 != 4);
        rememberedValue3 = startRestartGroup.rememberedValue();
        if (!changedInstance2) {
        }
        rememberedValue3 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Float f9) {
                invoke(f9.floatValue());
                return Unit.INSTANCE;
            }

            /* compiled from: ModalBottomSheet.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1$1, reason: invalid class name */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ float $it;
                final /* synthetic */ SheetState $sheetState;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(SheetState sheetState, float f, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$sheetState = sheetState;
                    this.$it = f;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$sheetState, this.$it, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (this.$sheetState.settle$material3_release(this.$it, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            public final void invoke(float f9) {
                Job launch$default;
                launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(sheetState2, f9, null), 3, null);
                final SheetState sheetState4222 = sheetState2;
                final Function0<Unit> function03 = function0;
                launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable th) {
                        if (SheetState.this.isVisible()) {
                            return;
                        }
                        function03.invoke();
                    }
                });
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue3);
        final Function1<? super Float, Unit> function1222 = (Function1) rememberedValue3;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011376044, "CC(remember):ModalBottomSheet.kt#9igjgp");
        rememberedValue4 = startRestartGroup.rememberedValue();
        if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
        }
        final Animatable<Float, AnimationVector1D> animatable222 = (Animatable) rememberedValue4;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2011371154, "CC(remember):ModalBottomSheet.kt#9igjgp");
        changedInstance3 = (i25222 != 4) | ((i24222 <= 256 && startRestartGroup.changed(sheetState2)) || (i10 & 384) == 256) | startRestartGroup.changedInstance(coroutineScope222) | startRestartGroup.changedInstance(animatable222);
        rememberedValue5 = startRestartGroup.rememberedValue();
        if (!changedInstance3) {
        }
        rememberedValue5 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Job launch$default;
                if (SheetState.this.getCurrentValue() != SheetValue.Expanded || !SheetState.this.getHasPartiallyExpandedState()) {
                    launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope222, null, null, new AnonymousClass3(SheetState.this, null), 3, null);
                    final Function0<Unit> function03 = function0;
                    launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                            invoke2(th);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Throwable th) {
                            function03.invoke();
                        }
                    });
                } else {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope222, null, null, new AnonymousClass1(animatable222, null), 3, null);
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope222, null, null, new AnonymousClass2(SheetState.this, null), 3, null);
                }
            }

            /* compiled from: ModalBottomSheet.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$1", f = "ModalBottomSheet.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$1, reason: invalid class name */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Animatable<Float, AnimationVector1D> $predictiveBackProgress;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$predictiveBackProgress = animatable;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$predictiveBackProgress, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (Animatable.animateTo$default(this.$predictiveBackProgress, Boxing.boxFloat(0.0f), null, null, null, this, 14, null) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: ModalBottomSheet.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$2", f = "ModalBottomSheet.kt", i = {}, l = {162}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$2, reason: invalid class name */
            static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ SheetState $sheetState;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass2(SheetState sheetState, Continuation<? super AnonymousClass2> continuation) {
                    super(2, continuation);
                    this.$sheetState = sheetState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass2(this.$sheetState, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (this.$sheetState.partialExpand(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: ModalBottomSheet.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$3", f = "ModalBottomSheet.kt", i = {}, l = {164}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$3, reason: invalid class name */
            static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ SheetState $sheetState;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass3(SheetState sheetState, Continuation<? super AnonymousClass3> continuation) {
                    super(2, continuation);
                    this.$sheetState = sheetState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass3(this.$sheetState, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (this.$sheetState.hide(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue5);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function33222 = function32;
        final SheetState sheetState4222 = sheetState2;
        ModalBottomSheet_androidKt.ModalBottomSheetDialog((Function0) rememberedValue5, modalBottomSheetProperties2, animatable222, ComposableLambdaKt.rememberComposableLambda(-314673510, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$3
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

            public final void invoke(Composer composer3, int i26) {
                ComposerKt.sourceInformation(composer3, "C168@8241L755:ModalBottomSheet.kt#uh7d8r");
                if ((i26 & 3) != 2 || !composer3.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-314673510, i26, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.kt:168)");
                    }
                    Modifier semantics$default = SemanticsModifierKt.semantics$default(WindowInsetsPadding_androidKt.imePadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null)), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$3.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
                        }
                    }, 1, null);
                    long j122 = j9;
                    Function0<Unit> function03 = function02222;
                    SheetState sheetState5 = sheetState4222;
                    Animatable<Float, AnimationVector1D> animatable2222 = animatable222;
                    CoroutineScope coroutineScope2222 = coroutineScope222;
                    Function1<Float, Unit> function12222 = function1222;
                    Modifier modifier42 = modifier3;
                    float f9 = f6;
                    Shape shape6 = shape4;
                    long j13 = j7;
                    long j14 = j8;
                    float f10 = f5;
                    Function2<Composer, Integer, Unit> function27 = function23;
                    Function2<Composer, Integer, WindowInsets> function28 = function24;
                    Function3<ColumnScope, Composer, Integer, Unit> function34 = function33222;
                    ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, semantics$default);
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
                    Updater.m3912setimpl(m3905constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        m3905constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        m3905constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composer3, 1107507610, "C169@8345L169,174@8527L459:ModalBottomSheet.kt#uh7d8r");
                    ModalBottomSheetKt.m2400Scrim3JVO9M(j122, function03, sheetState5.getTargetValue() != SheetValue.Hidden, composer3, 0);
                    ModalBottomSheetKt.m2399ModalBottomSheetContentIQkwcL4(boxScopeInstance, animatable2222, coroutineScope2222, function03, function12222, modifier42, sheetState5, f9, shape6, j13, j14, f10, function27, function28, function34, composer3, (Animatable.$stable << 3) | 6, 0, 0);
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
        }, startRestartGroup, 54), startRestartGroup, (i2222 & 112) | 3072 | (Animatable.$stable << 6));
        composer2 = startRestartGroup;
        if (sheetState2.getHasExpandedState()) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        sheetState3 = sheetState2;
        modalBottomSheetProperties3 = modalBottomSheetProperties2;
        modifier2 = modifier3;
        f7 = f6;
        shape5 = shape4;
        j10 = j7;
        j11 = j8;
        f8 = f5;
        function25 = function23;
        function26 = function24;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x01ad, code lost:
    
        if (r6.changedInstance(r59) != false) goto L152;
     */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0444  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x04c6  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x04dc  */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x020c  */
    /* renamed from: ModalBottomSheetContent-IQkwcL4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m2399ModalBottomSheetContentIQkwcL4(final BoxScope boxScope, final Animatable<Float, AnimationVector1D> animatable, final CoroutineScope coroutineScope, final Function0<Unit> function0, final Function1<? super Float, Unit> function1, Modifier modifier, SheetState sheetState, float f, Shape shape, long j, long j2, float f2, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, ? extends WindowInsets> function22, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        Function0<Unit> function02;
        int i5;
        Modifier modifier2;
        final SheetState sheetState2;
        int i6;
        final Shape shape2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        Function2<? super Composer, ? super Integer, Unit> function23;
        int i14;
        long j3;
        long j4;
        Function2<? super Composer, ? super Integer, Unit> m2126getLambda2$material3_release;
        Shape shape3;
        long j5;
        int i15;
        Modifier modifier3;
        float f3;
        float f4;
        Function2<? super Composer, ? super Integer, ? extends WindowInsets> function24;
        final Function2<? super Composer, ? super Integer, ? extends WindowInsets> function25;
        int i16;
        float f5;
        boolean z;
        Object rememberedValue;
        boolean z2;
        Object rememberedValue2;
        boolean z3;
        Object rememberedValue3;
        boolean changed;
        Object rememberedValue4;
        boolean z4;
        Object rememberedValue5;
        Composer composer2;
        final SheetState sheetState3;
        final Function2<? super Composer, ? super Integer, Unit> function26;
        final Function2<? super Composer, ? super Integer, ? extends WindowInsets> function27;
        final float f6;
        final Modifier modifier4;
        final float f7;
        final long j6;
        final long j7;
        ScopeUpdateScope endRestartGroup;
        int i17;
        int i18;
        int i19;
        Composer startRestartGroup = composer.startRestartGroup(-1676960531);
        ComposerKt.sourceInformation(startRestartGroup, "C(ModalBottomSheetContent)P(7,8!1,9,6,12,11:c#ui.unit.Dp,10,1:c#ui.graphics.Color,3:c#ui.graphics.Color,13:c#ui.unit.Dp,5,4)205@9432L31,207@9563L13,208@9626L14,209@9668L31,215@10018L48,224@10294L324,232@10728L1476,267@12552L23,269@12621L112,273@12765L612,288@13518L2623,217@10072L6069:ModalBottomSheet.kt#uh7d8r");
        if ((Integer.MIN_VALUE & i3) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = (startRestartGroup.changed(boxScope) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        if ((i3 & 1) != 0) {
            i4 |= 48;
        } else if ((i & 48) == 0) {
            i4 |= (i & 64) == 0 ? startRestartGroup.changed(animatable) : startRestartGroup.changedInstance(animatable) ? 32 : 16;
        }
        if ((i3 & 2) != 0) {
            i4 |= 384;
        } else if ((i & 384) == 0) {
            i4 |= startRestartGroup.changedInstance(coroutineScope) ? 256 : 128;
            int i20 = 2048;
            if ((i3 & 4) == 0) {
                i4 |= 3072;
            } else if ((i & 3072) == 0) {
                function02 = function0;
                i4 |= startRestartGroup.changedInstance(function02) ? 2048 : 1024;
                if ((i3 & 8) != 0) {
                    i4 |= 24576;
                } else if ((i & 24576) == 0) {
                    i4 |= startRestartGroup.changedInstance(function1) ? 16384 : 8192;
                }
                i5 = i3 & 16;
                if (i5 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        i4 |= startRestartGroup.changed(modifier2) ? 131072 : 65536;
                    }
                }
                if ((i & 1572864) == 0) {
                    sheetState2 = sheetState;
                    i4 |= ((i3 & 32) == 0 && startRestartGroup.changed(sheetState2)) ? 1048576 : 524288;
                } else {
                    sheetState2 = sheetState;
                }
                i6 = i3 & 64;
                if (i6 != 0) {
                    i4 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    i4 |= startRestartGroup.changed(f) ? 8388608 : 4194304;
                }
                if ((i & 100663296) == 0) {
                    if ((i3 & 128) == 0) {
                        shape2 = shape;
                        if (startRestartGroup.changed(shape2)) {
                            i19 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            i4 |= i19;
                        }
                    } else {
                        shape2 = shape;
                    }
                    i19 = 33554432;
                    i4 |= i19;
                } else {
                    shape2 = shape;
                }
                if ((i & 805306368) == 0) {
                    if ((i3 & 256) == 0) {
                        i17 = i4;
                        if (startRestartGroup.changed(j)) {
                            i18 = 536870912;
                            i7 = i17 | i18;
                        }
                    } else {
                        i17 = i4;
                    }
                    i18 = 268435456;
                    i7 = i17 | i18;
                } else {
                    i7 = i4;
                }
                if ((i2 & 6) == 0) {
                    i8 = (((i3 & 512) == 0 && startRestartGroup.changed(j2)) ? 4 : 2) | i2;
                } else {
                    i8 = i2;
                }
                int i21 = i8;
                i9 = i3 & 1024;
                if (i9 != 0) {
                    i12 = i21 | 48;
                    i10 = i9;
                } else if ((i2 & 48) == 0) {
                    i10 = i9;
                    i12 = i21 | (startRestartGroup.changed(f2) ? 32 : 16);
                } else {
                    i10 = i9;
                    i11 = i21;
                    i13 = i3 & 2048;
                    if (i13 == 0) {
                        i11 |= 384;
                    } else if ((i2 & 384) == 0) {
                        function23 = function2;
                        i11 |= startRestartGroup.changedInstance(function23) ? 256 : 128;
                        int i22 = i11;
                        if ((i2 & 3072) == 0) {
                            if ((i3 & 4096) != 0) {
                            }
                            i20 = 1024;
                            i14 = i22 | i20;
                        } else {
                            i14 = i22;
                        }
                        if ((i3 & 8192) != 0) {
                            i14 |= 24576;
                        } else if ((i2 & 24576) == 0) {
                            i14 |= startRestartGroup.changedInstance(function3) ? 16384 : 8192;
                            if ((i7 & 306783379) == 306783378 || (i14 & 9363) != 9362 || !startRestartGroup.getSkipping()) {
                                startRestartGroup.startDefaults();
                                if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if ((i3 & 32) != 0) {
                                        i7 &= -3670017;
                                        sheetState2 = rememberModalBottomSheetState(false, null, startRestartGroup, 0, 3);
                                    }
                                    float m1948getSheetMaxWidthD9Ej5fM = i6 == 0 ? BottomSheetDefaults.INSTANCE.m1948getSheetMaxWidthD9Ej5fM() : f;
                                    if ((i3 & 128) != 0) {
                                        shape2 = BottomSheetDefaults.INSTANCE.getExpandedShape(startRestartGroup, 6);
                                        i7 &= -234881025;
                                    }
                                    if ((i3 & 256) == 0) {
                                        i7 = (-1879048193) & i7;
                                        j3 = BottomSheetDefaults.INSTANCE.getContainerColor(startRestartGroup, 6);
                                    } else {
                                        j3 = j;
                                    }
                                    if ((i3 & 512) == 0) {
                                        j4 = ColorSchemeKt.m2085contentColorForek8zF_U(j3, startRestartGroup, (i7 >> 27) & 14);
                                        i14 &= -15;
                                    } else {
                                        j4 = j2;
                                    }
                                    float m1947getElevationD9Ej5fM = i10 == 0 ? BottomSheetDefaults.INSTANCE.m1947getElevationD9Ej5fM() : f2;
                                    m2126getLambda2$material3_release = i13 == 0 ? ComposableSingletons$ModalBottomSheetKt.INSTANCE.m2126getLambda2$material3_release() : function2;
                                    float f8 = m1948getSheetMaxWidthD9Ej5fM;
                                    if ((i3 & 4096) == 0) {
                                        shape3 = shape2;
                                        j5 = j3;
                                        float f9 = m1947getElevationD9Ej5fM;
                                        i15 = i14 & (-7169);
                                        modifier3 = modifier2;
                                        f3 = f9;
                                        function24 = new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$1
                                            public final WindowInsets invoke(Composer composer3, int i23) {
                                                composer3.startReplaceGroup(-11444670);
                                                ComposerKt.sourceInformation(composer3, "C212@9919L12:ModalBottomSheet.kt#uh7d8r");
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-11444670, i23, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:212)");
                                                }
                                                WindowInsets windowInsets = BottomSheetDefaults.INSTANCE.getWindowInsets(composer3, 6);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                                composer3.endReplaceGroup();
                                                return windowInsets;
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer3, Integer num) {
                                                return invoke(composer3, num.intValue());
                                            }
                                        };
                                        f4 = f8;
                                    } else {
                                        shape3 = shape2;
                                        j5 = j3;
                                        float f10 = m1947getElevationD9Ej5fM;
                                        i15 = i14;
                                        modifier3 = modifier2;
                                        f3 = f10;
                                        f4 = f8;
                                        function24 = function22;
                                    }
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i3 & 32) != 0) {
                                        i7 &= -3670017;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i7 &= -234881025;
                                    }
                                    if ((i3 & 256) != 0) {
                                        i7 &= -1879048193;
                                    }
                                    if ((i3 & 512) != 0) {
                                        i14 &= -15;
                                    }
                                    if ((i3 & 4096) != 0) {
                                        i14 &= -7169;
                                    }
                                    f4 = f;
                                    j4 = j2;
                                    function24 = function22;
                                    i15 = i14;
                                    m2126getLambda2$material3_release = function23;
                                    shape3 = shape2;
                                    modifier3 = modifier2;
                                    j5 = j;
                                    f3 = f2;
                                }
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    function25 = function24;
                                } else {
                                    function25 = function24;
                                    ComposerKt.traceEventStart(-1676960531, i7, i15, "androidx.compose.material3.ModalBottomSheetContent (ModalBottomSheet.kt:214)");
                                }
                                Strings.Companion companion = Strings.INSTANCE;
                                final String m3160getString2EP1pXo = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_pane_title), startRestartGroup, 0);
                                Modifier modifier5 = modifier3;
                                Shape shape4 = shape3;
                                Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(SizeKt.m937widthInVpY3zN4$default(boxScope.align(modifier3, Alignment.INSTANCE.getTopCenter()), 0.0f, f4, 1, null), 0.0f, 1, null);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237857395, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                i16 = (3670016 & i7) ^ 1572864;
                                if (i16 > 1048576 || !startRestartGroup.changed(sheetState2)) {
                                    f5 = f4;
                                    if ((i7 & 1572864) != 1048576) {
                                        z = false;
                                        rememberedValue = startRestartGroup.rememberedValue();
                                        if (!z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState2, Orientation.Vertical, function1);
                                            startRestartGroup.updateRememberedValue(rememberedValue);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        long j8 = j5;
                                        Modifier nestedScroll$default = NestedScrollModifierKt.nestedScroll$default(fillMaxWidth$default, (NestedScrollConnection) rememberedValue, null, 2, null);
                                        AnchoredDraggableState<SheetValue> anchoredDraggableState$material3_release = sheetState2.getAnchoredDraggableState$material3_release();
                                        Orientation orientation = Orientation.Vertical;
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237872435, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                        z2 = (i16 <= 1048576 && startRestartGroup.changed(sheetState2)) || (i7 & 1572864) == 1048576;
                                        rememberedValue2 = startRestartGroup.rememberedValue();
                                        if (!z2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue2 = (Function2) new Function2<IntSize, Constraints, Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue>>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1

                                                /* compiled from: ModalBottomSheet.kt */
                                                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                                public /* synthetic */ class WhenMappings {
                                                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                                                    static {
                                                        int[] iArr = new int[SheetValue.values().length];
                                                        try {
                                                            iArr[SheetValue.Hidden.ordinal()] = 1;
                                                        } catch (NoSuchFieldError unused) {
                                                        }
                                                        try {
                                                            iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                                                        } catch (NoSuchFieldError unused2) {
                                                        }
                                                        try {
                                                            iArr[SheetValue.Expanded.ordinal()] = 3;
                                                        } catch (NoSuchFieldError unused3) {
                                                        }
                                                        $EnumSwitchMapping$0 = iArr;
                                                    }
                                                }

                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue> invoke(IntSize intSize, Constraints constraints) {
                                                    return m2402invokeGpV2Q24(intSize.m7578unboximpl(), constraints.getValue());
                                                }

                                                /* renamed from: invoke-GpV2Q24, reason: not valid java name */
                                                public final Pair<DraggableAnchors<SheetValue>, SheetValue> m2402invokeGpV2Q24(final long j9, long j10) {
                                                    SheetValue sheetValue;
                                                    final float m7341getMaxHeightimpl = Constraints.m7341getMaxHeightimpl(j10);
                                                    final SheetState sheetState4 = SheetState.this;
                                                    DraggableAnchors DraggableAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<SheetValue>, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1$newAnchors$1
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(1);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function1
                                                        public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                                            invoke2(draggableAnchorsConfig);
                                                            return Unit.INSTANCE;
                                                        }

                                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                        public final void invoke2(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                                            draggableAnchorsConfig.at(SheetValue.Hidden, m7341getMaxHeightimpl);
                                                            if (IntSize.m7573getHeightimpl(j9) > m7341getMaxHeightimpl / 2 && !sheetState4.getSkipPartiallyExpanded()) {
                                                                draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, m7341getMaxHeightimpl / 2.0f);
                                                            }
                                                            if (IntSize.m7573getHeightimpl(j9) != 0) {
                                                                draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(0.0f, m7341getMaxHeightimpl - IntSize.m7573getHeightimpl(j9)));
                                                            }
                                                        }
                                                    });
                                                    int i23 = WhenMappings.$EnumSwitchMapping$0[SheetState.this.getAnchoredDraggableState$material3_release().getTargetValue().ordinal()];
                                                    if (i23 == 1) {
                                                        sheetValue = SheetValue.Hidden;
                                                    } else if (i23 == 2 || i23 == 3) {
                                                        if (DraggableAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                                                            sheetValue = SheetValue.PartiallyExpanded;
                                                        } else {
                                                            sheetValue = DraggableAnchors.hasAnchorFor(SheetValue.Expanded) ? SheetValue.Expanded : SheetValue.Hidden;
                                                        }
                                                    } else {
                                                        throw new NoWhenBranchMatchedException();
                                                    }
                                                    return TuplesKt.to(DraggableAnchors, sheetValue);
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue2);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        Modifier draggableAnchors = AnchoredDraggableKt.draggableAnchors(nestedScroll$default, anchoredDraggableState$material3_release, orientation, (Function2) rememberedValue2);
                                        DraggableState draggableState = sheetState2.getAnchoredDraggableState$material3_release().getDraggableState();
                                        Orientation orientation2 = Orientation.Vertical;
                                        boolean isVisible = sheetState2.isVisible();
                                        boolean isAnimationRunning = sheetState2.getAnchoredDraggableState$material3_release().isAnimationRunning();
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237929350, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                        z3 = (57344 & i7) == 16384;
                                        rememberedValue3 = startRestartGroup.rememberedValue();
                                        if (!z3 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue3 = (Function3) new ModalBottomSheetKt$ModalBottomSheetContent$4$1(function1, null);
                                            startRestartGroup.updateRememberedValue(rememberedValue3);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        Modifier draggable$default = DraggableKt.draggable$default(draggableAnchors, draggableState, orientation2, isVisible, null, isAnimationRunning, null, (Function3) rememberedValue3, false, 168, null);
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237931647, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                        changed = startRestartGroup.changed(m3160getString2EP1pXo);
                                        rememberedValue4 = startRestartGroup.rememberedValue();
                                        if (!changed || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$5$1
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
                                                    SemanticsPropertiesKt.setPaneTitle(semanticsPropertyReceiver, m3160getString2EP1pXo);
                                                    SemanticsPropertiesKt.setTraversalIndex(semanticsPropertyReceiver, 0.0f);
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue4);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        boolean z5 = false;
                                        Modifier semantics$default = SemanticsModifierKt.semantics$default(draggable$default, false, (Function1) rememberedValue4, 1, null);
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237936755, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                        boolean z6 = (i16 <= 1048576 && startRestartGroup.changed(sheetState2)) || (i7 & 1572864) == 1048576;
                                        if ((i7 & 112) != 32 || ((i7 & 64) != 0 && startRestartGroup.changedInstance(animatable))) {
                                            z5 = true;
                                        }
                                        z4 = z5 | z6;
                                        rememberedValue5 = startRestartGroup.rememberedValue();
                                        if (!z4 || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue5 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$6$1
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
                                                    float calculatePredictiveBackScaleX;
                                                    float calculatePredictiveBackScaleY;
                                                    float offset = SheetState.this.getAnchoredDraggableState$material3_release().getOffset();
                                                    float m4409getHeightimpl = Size.m4409getHeightimpl(graphicsLayerScope.getSize());
                                                    if (Float.isNaN(offset) || Float.isNaN(m4409getHeightimpl) || m4409getHeightimpl == 0.0f) {
                                                        return;
                                                    }
                                                    float floatValue = animatable.getValue().floatValue();
                                                    calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                                                    graphicsLayerScope.setScaleX(calculatePredictiveBackScaleX);
                                                    calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                                                    graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY);
                                                    graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, (offset + m4409getHeightimpl) / m4409getHeightimpl));
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue5);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        Modifier graphicsLayer = GraphicsLayerModifierKt.graphicsLayer(semantics$default, (Function1) rememberedValue5);
                                        final SheetState sheetState4 = sheetState2;
                                        final Function0<Unit> function03 = function02;
                                        final Function2<? super Composer, ? super Integer, Unit> function28 = m2126getLambda2$material3_release;
                                        Function2<? super Composer, ? super Integer, ? extends WindowInsets> function29 = function25;
                                        int i23 = i7 >> 21;
                                        int i24 = i15 << 9;
                                        long j9 = j4;
                                        composer2 = startRestartGroup;
                                        SurfaceKt.m2699SurfaceT9BRK9s(graphicsLayer, shape4, j8, j9, f3, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-692668920, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7
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

                                            public final void invoke(Composer composer3, int i25) {
                                                String str;
                                                ComposerKt.sourceInformation(composer3, "C290@13592L21,290@13629L550,289@13528L2607:ModalBottomSheet.kt#uh7d8r");
                                                if ((i25 & 3) != 2 || !composer3.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-692668920, i25, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:289)");
                                                    }
                                                    Modifier windowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), function25.invoke(composer3, 0));
                                                    ComposerKt.sourceInformationMarkerStart(composer3, 320924084, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                                    boolean changedInstance = composer3.changedInstance(animatable);
                                                    final Animatable<Float, AnimationVector1D> animatable2 = animatable;
                                                    Object rememberedValue6 = composer3.rememberedValue();
                                                    if (changedInstance || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                        rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$1$1
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
                                                                float calculatePredictiveBackScaleX;
                                                                float calculatePredictiveBackScaleY;
                                                                long j10;
                                                                float floatValue = animatable2.getValue().floatValue();
                                                                calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                                                                calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                                                                graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY == 0.0f ? 1.0f : calculatePredictiveBackScaleX / calculatePredictiveBackScaleY);
                                                                j10 = ModalBottomSheetKt.PredictiveBackChildTransformOrigin;
                                                                graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(j10);
                                                            }
                                                        };
                                                        composer3.updateRememberedValue(rememberedValue6);
                                                    }
                                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                                    Modifier graphicsLayer2 = GraphicsLayerModifierKt.graphicsLayer(windowInsetsPadding, (Function1) rememberedValue6);
                                                    Function2<Composer, Integer, Unit> function210 = function28;
                                                    final SheetState sheetState5 = sheetState4;
                                                    final Function0<Unit> function04 = function03;
                                                    final CoroutineScope coroutineScope2 = coroutineScope;
                                                    Function3<ColumnScope, Composer, Integer, Unit> function32 = function3;
                                                    ComposerKt.sourceInformationMarkerStart(composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                                                    MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, graphicsLayer2);
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
                                                    ComposerKt.sourceInformationMarkerStart(composer3, 806123989, "C340@16116L9:ModalBottomSheet.kt#uh7d8r");
                                                    composer3.startReplaceGroup(-1636564008);
                                                    ComposerKt.sourceInformation(composer3, "303@14272L54,304@14368L48,305@14457L47,309@14672L1346,306@14521L1568");
                                                    if (function210 != null) {
                                                        Strings.Companion companion2 = Strings.INSTANCE;
                                                        final String m3160getString2EP1pXo2 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer3, 0);
                                                        Strings.Companion companion3 = Strings.INSTANCE;
                                                        final String m3160getString2EP1pXo3 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer3, 0);
                                                        Strings.Companion companion4 = Strings.INSTANCE;
                                                        final String m3160getString2EP1pXo4 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer3, 0);
                                                        Modifier align = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -1636549585, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                                        boolean changed2 = composer3.changed(sheetState5) | composer3.changed(m3160getString2EP1pXo3) | composer3.changed(function04) | composer3.changed(m3160getString2EP1pXo4) | composer3.changedInstance(coroutineScope2) | composer3.changed(m3160getString2EP1pXo2);
                                                        Object rememberedValue7 = composer3.rememberedValue();
                                                        if (changed2 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                                            str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                                                            rememberedValue7 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1
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
                                                                    final SheetState sheetState6 = SheetState.this;
                                                                    String str2 = m3160getString2EP1pXo3;
                                                                    String str3 = m3160getString2EP1pXo4;
                                                                    String str4 = m3160getString2EP1pXo2;
                                                                    final Function0<Unit> function05 = function04;
                                                                    final CoroutineScope coroutineScope3 = coroutineScope2;
                                                                    SemanticsPropertiesKt.dismiss(semanticsPropertyReceiver, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1
                                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                        {
                                                                            super(0);
                                                                        }

                                                                        /* JADX WARN: Can't rename method to resolve collision */
                                                                        @Override // kotlin.jvm.functions.Function0
                                                                        public final Boolean invoke() {
                                                                            function05.invoke();
                                                                            return true;
                                                                        }
                                                                    });
                                                                    if (sheetState6.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                                                        SemanticsPropertiesKt.expand(semanticsPropertyReceiver, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2
                                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                            {
                                                                                super(0);
                                                                            }

                                                                            /* compiled from: ModalBottomSheet.kt */
                                                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                                            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1", f = "ModalBottomSheet.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
                                                                            /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1, reason: invalid class name */
                                                                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                                                final /* synthetic */ SheetState $sheetState;
                                                                                int label;

                                                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                                AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                                    super(2, continuation);
                                                                                    this.$sheetState = sheetState;
                                                                                }

                                                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                                    return new AnonymousClass1(this.$sheetState, continuation);
                                                                                }

                                                                                @Override // kotlin.jvm.functions.Function2
                                                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                                                }

                                                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                                public final Object invokeSuspend(Object obj) {
                                                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                                    int i = this.label;
                                                                                    if (i == 0) {
                                                                                        ResultKt.throwOnFailure(obj);
                                                                                        this.label = 1;
                                                                                        if (this.$sheetState.expand(this) == coroutine_suspended) {
                                                                                            return coroutine_suspended;
                                                                                        }
                                                                                    } else {
                                                                                        if (i != 1) {
                                                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                                        }
                                                                                        ResultKt.throwOnFailure(obj);
                                                                                    }
                                                                                    return Unit.INSTANCE;
                                                                                }
                                                                            }

                                                                            /* JADX WARN: Can't rename method to resolve collision */
                                                                            @Override // kotlin.jvm.functions.Function0
                                                                            public final Boolean invoke() {
                                                                                if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(sheetState6, null), 3, null);
                                                                                }
                                                                                return true;
                                                                            }
                                                                        });
                                                                    } else if (sheetState6.getHasPartiallyExpandedState()) {
                                                                        SemanticsPropertiesKt.collapse(semanticsPropertyReceiver, str4, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3
                                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                            {
                                                                                super(0);
                                                                            }

                                                                            /* JADX WARN: Can't rename method to resolve collision */
                                                                            @Override // kotlin.jvm.functions.Function0
                                                                            public final Boolean invoke() {
                                                                                if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(SheetState.this, null), 3, null);
                                                                                }
                                                                                return true;
                                                                            }

                                                                            /* compiled from: ModalBottomSheet.kt */
                                                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                                            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1", f = "ModalBottomSheet.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {})
                                                                            /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1, reason: invalid class name */
                                                                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                                                final /* synthetic */ SheetState $this_with;
                                                                                int label;

                                                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                                AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                                    super(2, continuation);
                                                                                    this.$this_with = sheetState;
                                                                                }

                                                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                                    return new AnonymousClass1(this.$this_with, continuation);
                                                                                }

                                                                                @Override // kotlin.jvm.functions.Function2
                                                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                                                }

                                                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                                public final Object invokeSuspend(Object obj) {
                                                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                                    int i = this.label;
                                                                                    if (i == 0) {
                                                                                        ResultKt.throwOnFailure(obj);
                                                                                        this.label = 1;
                                                                                        if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                                            return coroutine_suspended;
                                                                                        }
                                                                                    } else {
                                                                                        if (i != 1) {
                                                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                                        }
                                                                                        ResultKt.throwOnFailure(obj);
                                                                                    }
                                                                                    return Unit.INSTANCE;
                                                                                }
                                                                            }
                                                                        });
                                                                    }
                                                                }
                                                            };
                                                            composer3.updateRememberedValue(rememberedValue7);
                                                        } else {
                                                            str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                                                        }
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                        Modifier semantics = SemanticsModifierKt.semantics(align, true, (Function1) rememberedValue7);
                                                        ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, str);
                                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                        CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                                        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer3, semantics);
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
                                                        Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                        if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                                            m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                                            m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                                        }
                                                        Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -760467413, "C337@16059L12:ModalBottomSheet.kt#uh7d8r");
                                                        function210.invoke(composer3, 0);
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                        composer3.endNode();
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                    }
                                                    composer3.endReplaceGroup();
                                                    function32.invoke(columnScopeInstance, composer3, 6);
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
                                        }, startRestartGroup, 54), composer2, (i23 & 896) | (i23 & 112) | 12582912 | (i24 & 7168) | (i24 & 57344), 96);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        sheetState3 = sheetState4;
                                        function26 = function28;
                                        function27 = function29;
                                        shape2 = shape4;
                                        f6 = f3;
                                        modifier4 = modifier5;
                                        f7 = f5;
                                        j6 = j9;
                                        j7 = j8;
                                    }
                                } else {
                                    f5 = f4;
                                }
                                z = true;
                                rememberedValue = startRestartGroup.rememberedValue();
                                if (!z) {
                                }
                                rememberedValue = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState2, Orientation.Vertical, function1);
                                startRestartGroup.updateRememberedValue(rememberedValue);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                long j82 = j5;
                                Modifier nestedScroll$default2 = NestedScrollModifierKt.nestedScroll$default(fillMaxWidth$default, (NestedScrollConnection) rememberedValue, null, 2, null);
                                AnchoredDraggableState<SheetValue> anchoredDraggableState$material3_release2 = sheetState2.getAnchoredDraggableState$material3_release();
                                Orientation orientation3 = Orientation.Vertical;
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237872435, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                if (i16 <= 1048576) {
                                }
                                rememberedValue2 = startRestartGroup.rememberedValue();
                                if (!z2) {
                                }
                                rememberedValue2 = (Function2) new Function2<IntSize, Constraints, Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue>>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1

                                    /* compiled from: ModalBottomSheet.kt */
                                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                    public /* synthetic */ class WhenMappings {
                                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                                        static {
                                            int[] iArr = new int[SheetValue.values().length];
                                            try {
                                                iArr[SheetValue.Hidden.ordinal()] = 1;
                                            } catch (NoSuchFieldError unused) {
                                            }
                                            try {
                                                iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                                            } catch (NoSuchFieldError unused2) {
                                            }
                                            try {
                                                iArr[SheetValue.Expanded.ordinal()] = 3;
                                            } catch (NoSuchFieldError unused3) {
                                            }
                                            $EnumSwitchMapping$0 = iArr;
                                        }
                                    }

                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue> invoke(IntSize intSize, Constraints constraints) {
                                        return m2402invokeGpV2Q24(intSize.m7578unboximpl(), constraints.getValue());
                                    }

                                    /* renamed from: invoke-GpV2Q24, reason: not valid java name */
                                    public final Pair<DraggableAnchors<SheetValue>, SheetValue> m2402invokeGpV2Q24(final long j92, long j10) {
                                        SheetValue sheetValue;
                                        final float m7341getMaxHeightimpl = Constraints.m7341getMaxHeightimpl(j10);
                                        final SheetState sheetState42 = SheetState.this;
                                        DraggableAnchors DraggableAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<SheetValue>, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1$newAnchors$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                                invoke2(draggableAnchorsConfig);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                                draggableAnchorsConfig.at(SheetValue.Hidden, m7341getMaxHeightimpl);
                                                if (IntSize.m7573getHeightimpl(j92) > m7341getMaxHeightimpl / 2 && !sheetState42.getSkipPartiallyExpanded()) {
                                                    draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, m7341getMaxHeightimpl / 2.0f);
                                                }
                                                if (IntSize.m7573getHeightimpl(j92) != 0) {
                                                    draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(0.0f, m7341getMaxHeightimpl - IntSize.m7573getHeightimpl(j92)));
                                                }
                                            }
                                        });
                                        int i232 = WhenMappings.$EnumSwitchMapping$0[SheetState.this.getAnchoredDraggableState$material3_release().getTargetValue().ordinal()];
                                        if (i232 == 1) {
                                            sheetValue = SheetValue.Hidden;
                                        } else if (i232 == 2 || i232 == 3) {
                                            if (DraggableAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                                                sheetValue = SheetValue.PartiallyExpanded;
                                            } else {
                                                sheetValue = DraggableAnchors.hasAnchorFor(SheetValue.Expanded) ? SheetValue.Expanded : SheetValue.Hidden;
                                            }
                                        } else {
                                            throw new NoWhenBranchMatchedException();
                                        }
                                        return TuplesKt.to(DraggableAnchors, sheetValue);
                                    }
                                };
                                startRestartGroup.updateRememberedValue(rememberedValue2);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                Modifier draggableAnchors2 = AnchoredDraggableKt.draggableAnchors(nestedScroll$default2, anchoredDraggableState$material3_release2, orientation3, (Function2) rememberedValue2);
                                DraggableState draggableState2 = sheetState2.getAnchoredDraggableState$material3_release().getDraggableState();
                                Orientation orientation22 = Orientation.Vertical;
                                boolean isVisible2 = sheetState2.isVisible();
                                boolean isAnimationRunning2 = sheetState2.getAnchoredDraggableState$material3_release().isAnimationRunning();
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237929350, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                if ((57344 & i7) == 16384) {
                                }
                                rememberedValue3 = startRestartGroup.rememberedValue();
                                if (!z3) {
                                }
                                rememberedValue3 = (Function3) new ModalBottomSheetKt$ModalBottomSheetContent$4$1(function1, null);
                                startRestartGroup.updateRememberedValue(rememberedValue3);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                Modifier draggable$default2 = DraggableKt.draggable$default(draggableAnchors2, draggableState2, orientation22, isVisible2, null, isAnimationRunning2, null, (Function3) rememberedValue3, false, 168, null);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237931647, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                changed = startRestartGroup.changed(m3160getString2EP1pXo);
                                rememberedValue4 = startRestartGroup.rememberedValue();
                                if (!changed) {
                                }
                                rememberedValue4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$5$1
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
                                        SemanticsPropertiesKt.setPaneTitle(semanticsPropertyReceiver, m3160getString2EP1pXo);
                                        SemanticsPropertiesKt.setTraversalIndex(semanticsPropertyReceiver, 0.0f);
                                    }
                                };
                                startRestartGroup.updateRememberedValue(rememberedValue4);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                boolean z52 = false;
                                Modifier semantics$default2 = SemanticsModifierKt.semantics$default(draggable$default2, false, (Function1) rememberedValue4, 1, null);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237936755, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                if (i16 <= 1048576) {
                                }
                                if ((i7 & 112) != 32) {
                                }
                                z52 = true;
                                z4 = z52 | z6;
                                rememberedValue5 = startRestartGroup.rememberedValue();
                                if (!z4) {
                                }
                                rememberedValue5 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$6$1
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
                                        float calculatePredictiveBackScaleX;
                                        float calculatePredictiveBackScaleY;
                                        float offset = SheetState.this.getAnchoredDraggableState$material3_release().getOffset();
                                        float m4409getHeightimpl = Size.m4409getHeightimpl(graphicsLayerScope.getSize());
                                        if (Float.isNaN(offset) || Float.isNaN(m4409getHeightimpl) || m4409getHeightimpl == 0.0f) {
                                            return;
                                        }
                                        float floatValue = animatable.getValue().floatValue();
                                        calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                                        graphicsLayerScope.setScaleX(calculatePredictiveBackScaleX);
                                        calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                                        graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY);
                                        graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, (offset + m4409getHeightimpl) / m4409getHeightimpl));
                                    }
                                };
                                startRestartGroup.updateRememberedValue(rememberedValue5);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                Modifier graphicsLayer2 = GraphicsLayerModifierKt.graphicsLayer(semantics$default2, (Function1) rememberedValue5);
                                final SheetState sheetState42 = sheetState2;
                                final Function0<Unit> function032 = function02;
                                final Function2<? super Composer, ? super Integer, Unit> function282 = m2126getLambda2$material3_release;
                                Function2<? super Composer, ? super Integer, ? extends WindowInsets> function292 = function25;
                                int i232 = i7 >> 21;
                                int i242 = i15 << 9;
                                long j92 = j4;
                                composer2 = startRestartGroup;
                                SurfaceKt.m2699SurfaceT9BRK9s(graphicsLayer2, shape4, j82, j92, f3, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-692668920, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7
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

                                    public final void invoke(Composer composer3, int i25) {
                                        String str;
                                        ComposerKt.sourceInformation(composer3, "C290@13592L21,290@13629L550,289@13528L2607:ModalBottomSheet.kt#uh7d8r");
                                        if ((i25 & 3) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-692668920, i25, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:289)");
                                            }
                                            Modifier windowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), function25.invoke(composer3, 0));
                                            ComposerKt.sourceInformationMarkerStart(composer3, 320924084, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                            boolean changedInstance = composer3.changedInstance(animatable);
                                            final Animatable<Float, AnimationVector1D> animatable2 = animatable;
                                            Object rememberedValue6 = composer3.rememberedValue();
                                            if (changedInstance || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$1$1
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
                                                        float calculatePredictiveBackScaleX;
                                                        float calculatePredictiveBackScaleY;
                                                        long j10;
                                                        float floatValue = animatable2.getValue().floatValue();
                                                        calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                                                        calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                                                        graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY == 0.0f ? 1.0f : calculatePredictiveBackScaleX / calculatePredictiveBackScaleY);
                                                        j10 = ModalBottomSheetKt.PredictiveBackChildTransformOrigin;
                                                        graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(j10);
                                                    }
                                                };
                                                composer3.updateRememberedValue(rememberedValue6);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            Modifier graphicsLayer22 = GraphicsLayerModifierKt.graphicsLayer(windowInsetsPadding, (Function1) rememberedValue6);
                                            Function2<Composer, Integer, Unit> function210 = function282;
                                            final SheetState sheetState5 = sheetState42;
                                            final Function0<Unit> function04 = function032;
                                            final CoroutineScope coroutineScope2 = coroutineScope;
                                            Function3<ColumnScope, Composer, Integer, Unit> function32 = function3;
                                            ComposerKt.sourceInformationMarkerStart(composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                                            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                                            ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, graphicsLayer22);
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
                                            ComposerKt.sourceInformationMarkerStart(composer3, 806123989, "C340@16116L9:ModalBottomSheet.kt#uh7d8r");
                                            composer3.startReplaceGroup(-1636564008);
                                            ComposerKt.sourceInformation(composer3, "303@14272L54,304@14368L48,305@14457L47,309@14672L1346,306@14521L1568");
                                            if (function210 != null) {
                                                Strings.Companion companion2 = Strings.INSTANCE;
                                                final String m3160getString2EP1pXo2 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer3, 0);
                                                Strings.Companion companion3 = Strings.INSTANCE;
                                                final String m3160getString2EP1pXo3 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer3, 0);
                                                Strings.Companion companion4 = Strings.INSTANCE;
                                                final String m3160getString2EP1pXo4 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer3, 0);
                                                Modifier align = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
                                                ComposerKt.sourceInformationMarkerStart(composer3, -1636549585, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                                boolean changed2 = composer3.changed(sheetState5) | composer3.changed(m3160getString2EP1pXo3) | composer3.changed(function04) | composer3.changed(m3160getString2EP1pXo4) | composer3.changedInstance(coroutineScope2) | composer3.changed(m3160getString2EP1pXo2);
                                                Object rememberedValue7 = composer3.rememberedValue();
                                                if (changed2 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                                    str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                                                    rememberedValue7 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1
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
                                                            final SheetState sheetState6 = SheetState.this;
                                                            String str2 = m3160getString2EP1pXo3;
                                                            String str3 = m3160getString2EP1pXo4;
                                                            String str4 = m3160getString2EP1pXo2;
                                                            final Function0<Unit> function05 = function04;
                                                            final CoroutineScope coroutineScope3 = coroutineScope2;
                                                            SemanticsPropertiesKt.dismiss(semanticsPropertyReceiver, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1
                                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                {
                                                                    super(0);
                                                                }

                                                                /* JADX WARN: Can't rename method to resolve collision */
                                                                @Override // kotlin.jvm.functions.Function0
                                                                public final Boolean invoke() {
                                                                    function05.invoke();
                                                                    return true;
                                                                }
                                                            });
                                                            if (sheetState6.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                                                SemanticsPropertiesKt.expand(semanticsPropertyReceiver, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2
                                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                    {
                                                                        super(0);
                                                                    }

                                                                    /* compiled from: ModalBottomSheet.kt */
                                                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1", f = "ModalBottomSheet.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
                                                                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1, reason: invalid class name */
                                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                                        final /* synthetic */ SheetState $sheetState;
                                                                        int label;

                                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                            super(2, continuation);
                                                                            this.$sheetState = sheetState;
                                                                        }

                                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                            return new AnonymousClass1(this.$sheetState, continuation);
                                                                        }

                                                                        @Override // kotlin.jvm.functions.Function2
                                                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                                        }

                                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                        public final Object invokeSuspend(Object obj) {
                                                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                            int i = this.label;
                                                                            if (i == 0) {
                                                                                ResultKt.throwOnFailure(obj);
                                                                                this.label = 1;
                                                                                if (this.$sheetState.expand(this) == coroutine_suspended) {
                                                                                    return coroutine_suspended;
                                                                                }
                                                                            } else {
                                                                                if (i != 1) {
                                                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                                }
                                                                                ResultKt.throwOnFailure(obj);
                                                                            }
                                                                            return Unit.INSTANCE;
                                                                        }
                                                                    }

                                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                                    @Override // kotlin.jvm.functions.Function0
                                                                    public final Boolean invoke() {
                                                                        if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(sheetState6, null), 3, null);
                                                                        }
                                                                        return true;
                                                                    }
                                                                });
                                                            } else if (sheetState6.getHasPartiallyExpandedState()) {
                                                                SemanticsPropertiesKt.collapse(semanticsPropertyReceiver, str4, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3
                                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                    {
                                                                        super(0);
                                                                    }

                                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                                    @Override // kotlin.jvm.functions.Function0
                                                                    public final Boolean invoke() {
                                                                        if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(SheetState.this, null), 3, null);
                                                                        }
                                                                        return true;
                                                                    }

                                                                    /* compiled from: ModalBottomSheet.kt */
                                                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1", f = "ModalBottomSheet.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {})
                                                                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1, reason: invalid class name */
                                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                                        final /* synthetic */ SheetState $this_with;
                                                                        int label;

                                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                            super(2, continuation);
                                                                            this.$this_with = sheetState;
                                                                        }

                                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                            return new AnonymousClass1(this.$this_with, continuation);
                                                                        }

                                                                        @Override // kotlin.jvm.functions.Function2
                                                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                                        }

                                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                        public final Object invokeSuspend(Object obj) {
                                                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                            int i = this.label;
                                                                            if (i == 0) {
                                                                                ResultKt.throwOnFailure(obj);
                                                                                this.label = 1;
                                                                                if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                                    return coroutine_suspended;
                                                                                }
                                                                            } else {
                                                                                if (i != 1) {
                                                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                                }
                                                                                ResultKt.throwOnFailure(obj);
                                                                            }
                                                                            return Unit.INSTANCE;
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(rememberedValue7);
                                                } else {
                                                    str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                                                }
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                Modifier semantics = SemanticsModifierKt.semantics(align, true, (Function1) rememberedValue7);
                                                ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                                ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, str);
                                                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer3, semantics);
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
                                                Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                                    m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                                    m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                                }
                                                Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                                ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart(composer3, -760467413, "C337@16059L12:ModalBottomSheet.kt#uh7d8r");
                                                function210.invoke(composer3, 0);
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                composer3.endNode();
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                            }
                                            composer3.endReplaceGroup();
                                            function32.invoke(columnScopeInstance, composer3, 6);
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
                                }, startRestartGroup, 54), composer2, (i232 & 896) | (i232 & 112) | 12582912 | (i242 & 7168) | (i242 & 57344), 96);
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                sheetState3 = sheetState42;
                                function26 = function282;
                                function27 = function292;
                                shape2 = shape4;
                                f6 = f3;
                                modifier4 = modifier5;
                                f7 = f5;
                                j6 = j92;
                                j7 = j82;
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                j7 = j;
                                f6 = f2;
                                function27 = function22;
                                composer2 = startRestartGroup;
                                function26 = function23;
                                sheetState3 = sheetState2;
                                modifier4 = modifier2;
                                f7 = f;
                                j6 = j2;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$8
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

                                    public final void invoke(Composer composer3, int i25) {
                                        ModalBottomSheetKt.m2399ModalBottomSheetContentIQkwcL4(BoxScope.this, animatable, coroutineScope, function0, function1, modifier4, sheetState3, f7, shape2, j7, j6, f6, function26, function27, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        if ((i7 & 306783379) == 306783378) {
                        }
                        startRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                        }
                        if (i5 != 0) {
                        }
                        if ((i3 & 32) != 0) {
                        }
                        if (i6 == 0) {
                        }
                        if ((i3 & 128) != 0) {
                        }
                        if ((i3 & 256) == 0) {
                        }
                        if ((i3 & 512) == 0) {
                        }
                        if (i10 == 0) {
                        }
                        if (i13 == 0) {
                        }
                        float f82 = m1948getSheetMaxWidthD9Ej5fM;
                        if ((i3 & 4096) == 0) {
                        }
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        Strings.Companion companion2 = Strings.INSTANCE;
                        final String m3160getString2EP1pXo2 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_pane_title), startRestartGroup, 0);
                        Modifier modifier52 = modifier3;
                        Shape shape42 = shape3;
                        Modifier fillMaxWidth$default2 = SizeKt.fillMaxWidth$default(SizeKt.m937widthInVpY3zN4$default(boxScope.align(modifier3, Alignment.INSTANCE.getTopCenter()), 0.0f, f4, 1, null), 0.0f, 1, null);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237857395, "CC(remember):ModalBottomSheet.kt#9igjgp");
                        i16 = (3670016 & i7) ^ 1572864;
                        if (i16 > 1048576) {
                        }
                        f5 = f4;
                        if ((i7 & 1572864) != 1048576) {
                        }
                        z = true;
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (!z) {
                        }
                        rememberedValue = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState2, Orientation.Vertical, function1);
                        startRestartGroup.updateRememberedValue(rememberedValue);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        long j822 = j5;
                        Modifier nestedScroll$default22 = NestedScrollModifierKt.nestedScroll$default(fillMaxWidth$default2, (NestedScrollConnection) rememberedValue, null, 2, null);
                        AnchoredDraggableState<SheetValue> anchoredDraggableState$material3_release22 = sheetState2.getAnchoredDraggableState$material3_release();
                        Orientation orientation32 = Orientation.Vertical;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237872435, "CC(remember):ModalBottomSheet.kt#9igjgp");
                        if (i16 <= 1048576) {
                        }
                        rememberedValue2 = startRestartGroup.rememberedValue();
                        if (!z2) {
                        }
                        rememberedValue2 = (Function2) new Function2<IntSize, Constraints, Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue>>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1

                            /* compiled from: ModalBottomSheet.kt */
                            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                            public /* synthetic */ class WhenMappings {
                                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                                static {
                                    int[] iArr = new int[SheetValue.values().length];
                                    try {
                                        iArr[SheetValue.Hidden.ordinal()] = 1;
                                    } catch (NoSuchFieldError unused) {
                                    }
                                    try {
                                        iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                                    } catch (NoSuchFieldError unused2) {
                                    }
                                    try {
                                        iArr[SheetValue.Expanded.ordinal()] = 3;
                                    } catch (NoSuchFieldError unused3) {
                                    }
                                    $EnumSwitchMapping$0 = iArr;
                                }
                            }

                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue> invoke(IntSize intSize, Constraints constraints) {
                                return m2402invokeGpV2Q24(intSize.m7578unboximpl(), constraints.getValue());
                            }

                            /* renamed from: invoke-GpV2Q24, reason: not valid java name */
                            public final Pair<DraggableAnchors<SheetValue>, SheetValue> m2402invokeGpV2Q24(final long j922, long j10) {
                                SheetValue sheetValue;
                                final float m7341getMaxHeightimpl = Constraints.m7341getMaxHeightimpl(j10);
                                final SheetState sheetState422 = SheetState.this;
                                DraggableAnchors DraggableAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<SheetValue>, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1$newAnchors$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                        invoke2(draggableAnchorsConfig);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                        draggableAnchorsConfig.at(SheetValue.Hidden, m7341getMaxHeightimpl);
                                        if (IntSize.m7573getHeightimpl(j922) > m7341getMaxHeightimpl / 2 && !sheetState422.getSkipPartiallyExpanded()) {
                                            draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, m7341getMaxHeightimpl / 2.0f);
                                        }
                                        if (IntSize.m7573getHeightimpl(j922) != 0) {
                                            draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(0.0f, m7341getMaxHeightimpl - IntSize.m7573getHeightimpl(j922)));
                                        }
                                    }
                                });
                                int i2322 = WhenMappings.$EnumSwitchMapping$0[SheetState.this.getAnchoredDraggableState$material3_release().getTargetValue().ordinal()];
                                if (i2322 == 1) {
                                    sheetValue = SheetValue.Hidden;
                                } else if (i2322 == 2 || i2322 == 3) {
                                    if (DraggableAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                                        sheetValue = SheetValue.PartiallyExpanded;
                                    } else {
                                        sheetValue = DraggableAnchors.hasAnchorFor(SheetValue.Expanded) ? SheetValue.Expanded : SheetValue.Hidden;
                                    }
                                } else {
                                    throw new NoWhenBranchMatchedException();
                                }
                                return TuplesKt.to(DraggableAnchors, sheetValue);
                            }
                        };
                        startRestartGroup.updateRememberedValue(rememberedValue2);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        Modifier draggableAnchors22 = AnchoredDraggableKt.draggableAnchors(nestedScroll$default22, anchoredDraggableState$material3_release22, orientation32, (Function2) rememberedValue2);
                        DraggableState draggableState22 = sheetState2.getAnchoredDraggableState$material3_release().getDraggableState();
                        Orientation orientation222 = Orientation.Vertical;
                        boolean isVisible22 = sheetState2.isVisible();
                        boolean isAnimationRunning22 = sheetState2.getAnchoredDraggableState$material3_release().isAnimationRunning();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237929350, "CC(remember):ModalBottomSheet.kt#9igjgp");
                        if ((57344 & i7) == 16384) {
                        }
                        rememberedValue3 = startRestartGroup.rememberedValue();
                        if (!z3) {
                        }
                        rememberedValue3 = (Function3) new ModalBottomSheetKt$ModalBottomSheetContent$4$1(function1, null);
                        startRestartGroup.updateRememberedValue(rememberedValue3);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        Modifier draggable$default22 = DraggableKt.draggable$default(draggableAnchors22, draggableState22, orientation222, isVisible22, null, isAnimationRunning22, null, (Function3) rememberedValue3, false, 168, null);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237931647, "CC(remember):ModalBottomSheet.kt#9igjgp");
                        changed = startRestartGroup.changed(m3160getString2EP1pXo2);
                        rememberedValue4 = startRestartGroup.rememberedValue();
                        if (!changed) {
                        }
                        rememberedValue4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$5$1
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
                                SemanticsPropertiesKt.setPaneTitle(semanticsPropertyReceiver, m3160getString2EP1pXo2);
                                SemanticsPropertiesKt.setTraversalIndex(semanticsPropertyReceiver, 0.0f);
                            }
                        };
                        startRestartGroup.updateRememberedValue(rememberedValue4);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        boolean z522 = false;
                        Modifier semantics$default22 = SemanticsModifierKt.semantics$default(draggable$default22, false, (Function1) rememberedValue4, 1, null);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237936755, "CC(remember):ModalBottomSheet.kt#9igjgp");
                        if (i16 <= 1048576) {
                        }
                        if ((i7 & 112) != 32) {
                        }
                        z522 = true;
                        z4 = z522 | z6;
                        rememberedValue5 = startRestartGroup.rememberedValue();
                        if (!z4) {
                        }
                        rememberedValue5 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$6$1
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
                                float calculatePredictiveBackScaleX;
                                float calculatePredictiveBackScaleY;
                                float offset = SheetState.this.getAnchoredDraggableState$material3_release().getOffset();
                                float m4409getHeightimpl = Size.m4409getHeightimpl(graphicsLayerScope.getSize());
                                if (Float.isNaN(offset) || Float.isNaN(m4409getHeightimpl) || m4409getHeightimpl == 0.0f) {
                                    return;
                                }
                                float floatValue = animatable.getValue().floatValue();
                                calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                                graphicsLayerScope.setScaleX(calculatePredictiveBackScaleX);
                                calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                                graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY);
                                graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, (offset + m4409getHeightimpl) / m4409getHeightimpl));
                            }
                        };
                        startRestartGroup.updateRememberedValue(rememberedValue5);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        Modifier graphicsLayer22 = GraphicsLayerModifierKt.graphicsLayer(semantics$default22, (Function1) rememberedValue5);
                        final SheetState sheetState422 = sheetState2;
                        final Function0<Unit> function0322 = function02;
                        final Function2<? super Composer, ? super Integer, Unit> function2822 = m2126getLambda2$material3_release;
                        Function2<? super Composer, ? super Integer, ? extends WindowInsets> function2922 = function25;
                        int i2322 = i7 >> 21;
                        int i2422 = i15 << 9;
                        long j922 = j4;
                        composer2 = startRestartGroup;
                        SurfaceKt.m2699SurfaceT9BRK9s(graphicsLayer22, shape42, j822, j922, f3, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-692668920, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7
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

                            public final void invoke(Composer composer3, int i25) {
                                String str;
                                ComposerKt.sourceInformation(composer3, "C290@13592L21,290@13629L550,289@13528L2607:ModalBottomSheet.kt#uh7d8r");
                                if ((i25 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-692668920, i25, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:289)");
                                    }
                                    Modifier windowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), function25.invoke(composer3, 0));
                                    ComposerKt.sourceInformationMarkerStart(composer3, 320924084, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                    boolean changedInstance = composer3.changedInstance(animatable);
                                    final Animatable<Float, AnimationVector1D> animatable2 = animatable;
                                    Object rememberedValue6 = composer3.rememberedValue();
                                    if (changedInstance || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$1$1
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
                                                float calculatePredictiveBackScaleX;
                                                float calculatePredictiveBackScaleY;
                                                long j10;
                                                float floatValue = animatable2.getValue().floatValue();
                                                calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                                                calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                                                graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY == 0.0f ? 1.0f : calculatePredictiveBackScaleX / calculatePredictiveBackScaleY);
                                                j10 = ModalBottomSheetKt.PredictiveBackChildTransformOrigin;
                                                graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(j10);
                                            }
                                        };
                                        composer3.updateRememberedValue(rememberedValue6);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    Modifier graphicsLayer222 = GraphicsLayerModifierKt.graphicsLayer(windowInsetsPadding, (Function1) rememberedValue6);
                                    Function2<Composer, Integer, Unit> function210 = function2822;
                                    final SheetState sheetState5 = sheetState422;
                                    final Function0<Unit> function04 = function0322;
                                    final CoroutineScope coroutineScope2 = coroutineScope;
                                    Function3<ColumnScope, Composer, Integer, Unit> function32 = function3;
                                    ComposerKt.sourceInformationMarkerStart(composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                                    MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, graphicsLayer222);
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
                                    ComposerKt.sourceInformationMarkerStart(composer3, 806123989, "C340@16116L9:ModalBottomSheet.kt#uh7d8r");
                                    composer3.startReplaceGroup(-1636564008);
                                    ComposerKt.sourceInformation(composer3, "303@14272L54,304@14368L48,305@14457L47,309@14672L1346,306@14521L1568");
                                    if (function210 != null) {
                                        Strings.Companion companion22 = Strings.INSTANCE;
                                        final String m3160getString2EP1pXo22 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer3, 0);
                                        Strings.Companion companion3 = Strings.INSTANCE;
                                        final String m3160getString2EP1pXo3 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer3, 0);
                                        Strings.Companion companion4 = Strings.INSTANCE;
                                        final String m3160getString2EP1pXo4 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer3, 0);
                                        Modifier align = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1636549585, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                        boolean changed2 = composer3.changed(sheetState5) | composer3.changed(m3160getString2EP1pXo3) | composer3.changed(function04) | composer3.changed(m3160getString2EP1pXo4) | composer3.changedInstance(coroutineScope2) | composer3.changed(m3160getString2EP1pXo22);
                                        Object rememberedValue7 = composer3.rememberedValue();
                                        if (changed2 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                            str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                                            rememberedValue7 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1
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
                                                    final SheetState sheetState6 = SheetState.this;
                                                    String str2 = m3160getString2EP1pXo3;
                                                    String str3 = m3160getString2EP1pXo4;
                                                    String str4 = m3160getString2EP1pXo22;
                                                    final Function0<Unit> function05 = function04;
                                                    final CoroutineScope coroutineScope3 = coroutineScope2;
                                                    SemanticsPropertiesKt.dismiss(semanticsPropertyReceiver, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(0);
                                                        }

                                                        /* JADX WARN: Can't rename method to resolve collision */
                                                        @Override // kotlin.jvm.functions.Function0
                                                        public final Boolean invoke() {
                                                            function05.invoke();
                                                            return true;
                                                        }
                                                    });
                                                    if (sheetState6.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                                        SemanticsPropertiesKt.expand(semanticsPropertyReceiver, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2
                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                            {
                                                                super(0);
                                                            }

                                                            /* compiled from: ModalBottomSheet.kt */
                                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1", f = "ModalBottomSheet.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
                                                            /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1, reason: invalid class name */
                                                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                                final /* synthetic */ SheetState $sheetState;
                                                                int label;

                                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                    super(2, continuation);
                                                                    this.$sheetState = sheetState;
                                                                }

                                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                    return new AnonymousClass1(this.$sheetState, continuation);
                                                                }

                                                                @Override // kotlin.jvm.functions.Function2
                                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                                }

                                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                public final Object invokeSuspend(Object obj) {
                                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                    int i = this.label;
                                                                    if (i == 0) {
                                                                        ResultKt.throwOnFailure(obj);
                                                                        this.label = 1;
                                                                        if (this.$sheetState.expand(this) == coroutine_suspended) {
                                                                            return coroutine_suspended;
                                                                        }
                                                                    } else {
                                                                        if (i != 1) {
                                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                        }
                                                                        ResultKt.throwOnFailure(obj);
                                                                    }
                                                                    return Unit.INSTANCE;
                                                                }
                                                            }

                                                            /* JADX WARN: Can't rename method to resolve collision */
                                                            @Override // kotlin.jvm.functions.Function0
                                                            public final Boolean invoke() {
                                                                if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(sheetState6, null), 3, null);
                                                                }
                                                                return true;
                                                            }
                                                        });
                                                    } else if (sheetState6.getHasPartiallyExpandedState()) {
                                                        SemanticsPropertiesKt.collapse(semanticsPropertyReceiver, str4, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3
                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                            {
                                                                super(0);
                                                            }

                                                            /* JADX WARN: Can't rename method to resolve collision */
                                                            @Override // kotlin.jvm.functions.Function0
                                                            public final Boolean invoke() {
                                                                if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(SheetState.this, null), 3, null);
                                                                }
                                                                return true;
                                                            }

                                                            /* compiled from: ModalBottomSheet.kt */
                                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1", f = "ModalBottomSheet.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {})
                                                            /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1, reason: invalid class name */
                                                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                                final /* synthetic */ SheetState $this_with;
                                                                int label;

                                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                    super(2, continuation);
                                                                    this.$this_with = sheetState;
                                                                }

                                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                    return new AnonymousClass1(this.$this_with, continuation);
                                                                }

                                                                @Override // kotlin.jvm.functions.Function2
                                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                                }

                                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                public final Object invokeSuspend(Object obj) {
                                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                    int i = this.label;
                                                                    if (i == 0) {
                                                                        ResultKt.throwOnFailure(obj);
                                                                        this.label = 1;
                                                                        if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                            return coroutine_suspended;
                                                                        }
                                                                    } else {
                                                                        if (i != 1) {
                                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                        }
                                                                        ResultKt.throwOnFailure(obj);
                                                                    }
                                                                    return Unit.INSTANCE;
                                                                }
                                                            }
                                                        });
                                                    }
                                                }
                                            };
                                            composer3.updateRememberedValue(rememberedValue7);
                                        } else {
                                            str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        Modifier semantics = SemanticsModifierKt.semantics(align, true, (Function1) rememberedValue7);
                                        ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, str);
                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                        CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer3, semantics);
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
                                        Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                            m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                            m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                        }
                                        Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer3, -760467413, "C337@16059L12:ModalBottomSheet.kt#uh7d8r");
                                        function210.invoke(composer3, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                    }
                                    composer3.endReplaceGroup();
                                    function32.invoke(columnScopeInstance, composer3, 6);
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
                        }, startRestartGroup, 54), composer2, (i2322 & 896) | (i2322 & 112) | 12582912 | (i2422 & 7168) | (i2422 & 57344), 96);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        sheetState3 = sheetState422;
                        function26 = function2822;
                        function27 = function2922;
                        shape2 = shape42;
                        f6 = f3;
                        modifier4 = modifier52;
                        f7 = f5;
                        j6 = j922;
                        j7 = j822;
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    function23 = function2;
                    int i222 = i11;
                    if ((i2 & 3072) == 0) {
                    }
                    if ((i3 & 8192) != 0) {
                    }
                    if ((i7 & 306783379) == 306783378) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if ((i3 & 32) != 0) {
                    }
                    if (i6 == 0) {
                    }
                    if ((i3 & 128) != 0) {
                    }
                    if ((i3 & 256) == 0) {
                    }
                    if ((i3 & 512) == 0) {
                    }
                    if (i10 == 0) {
                    }
                    if (i13 == 0) {
                    }
                    float f822 = m1948getSheetMaxWidthD9Ej5fM;
                    if ((i3 & 4096) == 0) {
                    }
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    Strings.Companion companion22 = Strings.INSTANCE;
                    final String m3160getString2EP1pXo22 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_pane_title), startRestartGroup, 0);
                    Modifier modifier522 = modifier3;
                    Shape shape422 = shape3;
                    Modifier fillMaxWidth$default22 = SizeKt.fillMaxWidth$default(SizeKt.m937widthInVpY3zN4$default(boxScope.align(modifier3, Alignment.INSTANCE.getTopCenter()), 0.0f, f4, 1, null), 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237857395, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    i16 = (3670016 & i7) ^ 1572864;
                    if (i16 > 1048576) {
                    }
                    f5 = f4;
                    if ((i7 & 1572864) != 1048576) {
                    }
                    z = true;
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (!z) {
                    }
                    rememberedValue = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState2, Orientation.Vertical, function1);
                    startRestartGroup.updateRememberedValue(rememberedValue);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    long j8222 = j5;
                    Modifier nestedScroll$default222 = NestedScrollModifierKt.nestedScroll$default(fillMaxWidth$default22, (NestedScrollConnection) rememberedValue, null, 2, null);
                    AnchoredDraggableState<SheetValue> anchoredDraggableState$material3_release222 = sheetState2.getAnchoredDraggableState$material3_release();
                    Orientation orientation322 = Orientation.Vertical;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237872435, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    if (i16 <= 1048576) {
                    }
                    rememberedValue2 = startRestartGroup.rememberedValue();
                    if (!z2) {
                    }
                    rememberedValue2 = (Function2) new Function2<IntSize, Constraints, Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue>>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1

                        /* compiled from: ModalBottomSheet.kt */
                        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                        public /* synthetic */ class WhenMappings {
                            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                            static {
                                int[] iArr = new int[SheetValue.values().length];
                                try {
                                    iArr[SheetValue.Hidden.ordinal()] = 1;
                                } catch (NoSuchFieldError unused) {
                                }
                                try {
                                    iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                                } catch (NoSuchFieldError unused2) {
                                }
                                try {
                                    iArr[SheetValue.Expanded.ordinal()] = 3;
                                } catch (NoSuchFieldError unused3) {
                                }
                                $EnumSwitchMapping$0 = iArr;
                            }
                        }

                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue> invoke(IntSize intSize, Constraints constraints) {
                            return m2402invokeGpV2Q24(intSize.m7578unboximpl(), constraints.getValue());
                        }

                        /* renamed from: invoke-GpV2Q24, reason: not valid java name */
                        public final Pair<DraggableAnchors<SheetValue>, SheetValue> m2402invokeGpV2Q24(final long j9222, long j10) {
                            SheetValue sheetValue;
                            final float m7341getMaxHeightimpl = Constraints.m7341getMaxHeightimpl(j10);
                            final SheetState sheetState4222 = SheetState.this;
                            DraggableAnchors DraggableAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<SheetValue>, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1$newAnchors$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                    invoke2(draggableAnchorsConfig);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                    draggableAnchorsConfig.at(SheetValue.Hidden, m7341getMaxHeightimpl);
                                    if (IntSize.m7573getHeightimpl(j9222) > m7341getMaxHeightimpl / 2 && !sheetState4222.getSkipPartiallyExpanded()) {
                                        draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, m7341getMaxHeightimpl / 2.0f);
                                    }
                                    if (IntSize.m7573getHeightimpl(j9222) != 0) {
                                        draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(0.0f, m7341getMaxHeightimpl - IntSize.m7573getHeightimpl(j9222)));
                                    }
                                }
                            });
                            int i23222 = WhenMappings.$EnumSwitchMapping$0[SheetState.this.getAnchoredDraggableState$material3_release().getTargetValue().ordinal()];
                            if (i23222 == 1) {
                                sheetValue = SheetValue.Hidden;
                            } else if (i23222 == 2 || i23222 == 3) {
                                if (DraggableAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                                    sheetValue = SheetValue.PartiallyExpanded;
                                } else {
                                    sheetValue = DraggableAnchors.hasAnchorFor(SheetValue.Expanded) ? SheetValue.Expanded : SheetValue.Hidden;
                                }
                            } else {
                                throw new NoWhenBranchMatchedException();
                            }
                            return TuplesKt.to(DraggableAnchors, sheetValue);
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    Modifier draggableAnchors222 = AnchoredDraggableKt.draggableAnchors(nestedScroll$default222, anchoredDraggableState$material3_release222, orientation322, (Function2) rememberedValue2);
                    DraggableState draggableState222 = sheetState2.getAnchoredDraggableState$material3_release().getDraggableState();
                    Orientation orientation2222 = Orientation.Vertical;
                    boolean isVisible222 = sheetState2.isVisible();
                    boolean isAnimationRunning222 = sheetState2.getAnchoredDraggableState$material3_release().isAnimationRunning();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237929350, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    if ((57344 & i7) == 16384) {
                    }
                    rememberedValue3 = startRestartGroup.rememberedValue();
                    if (!z3) {
                    }
                    rememberedValue3 = (Function3) new ModalBottomSheetKt$ModalBottomSheetContent$4$1(function1, null);
                    startRestartGroup.updateRememberedValue(rememberedValue3);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    Modifier draggable$default222 = DraggableKt.draggable$default(draggableAnchors222, draggableState222, orientation2222, isVisible222, null, isAnimationRunning222, null, (Function3) rememberedValue3, false, 168, null);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237931647, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    changed = startRestartGroup.changed(m3160getString2EP1pXo22);
                    rememberedValue4 = startRestartGroup.rememberedValue();
                    if (!changed) {
                    }
                    rememberedValue4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$5$1
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
                            SemanticsPropertiesKt.setPaneTitle(semanticsPropertyReceiver, m3160getString2EP1pXo22);
                            SemanticsPropertiesKt.setTraversalIndex(semanticsPropertyReceiver, 0.0f);
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue4);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    boolean z5222 = false;
                    Modifier semantics$default222 = SemanticsModifierKt.semantics$default(draggable$default222, false, (Function1) rememberedValue4, 1, null);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237936755, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    if (i16 <= 1048576) {
                    }
                    if ((i7 & 112) != 32) {
                    }
                    z5222 = true;
                    z4 = z5222 | z6;
                    rememberedValue5 = startRestartGroup.rememberedValue();
                    if (!z4) {
                    }
                    rememberedValue5 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$6$1
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
                            float calculatePredictiveBackScaleX;
                            float calculatePredictiveBackScaleY;
                            float offset = SheetState.this.getAnchoredDraggableState$material3_release().getOffset();
                            float m4409getHeightimpl = Size.m4409getHeightimpl(graphicsLayerScope.getSize());
                            if (Float.isNaN(offset) || Float.isNaN(m4409getHeightimpl) || m4409getHeightimpl == 0.0f) {
                                return;
                            }
                            float floatValue = animatable.getValue().floatValue();
                            calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                            graphicsLayerScope.setScaleX(calculatePredictiveBackScaleX);
                            calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                            graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY);
                            graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, (offset + m4409getHeightimpl) / m4409getHeightimpl));
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue5);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    Modifier graphicsLayer222 = GraphicsLayerModifierKt.graphicsLayer(semantics$default222, (Function1) rememberedValue5);
                    final SheetState sheetState4222 = sheetState2;
                    final Function0<Unit> function03222 = function02;
                    final Function2<? super Composer, ? super Integer, Unit> function28222 = m2126getLambda2$material3_release;
                    Function2<? super Composer, ? super Integer, ? extends WindowInsets> function29222 = function25;
                    int i23222 = i7 >> 21;
                    int i24222 = i15 << 9;
                    long j9222 = j4;
                    composer2 = startRestartGroup;
                    SurfaceKt.m2699SurfaceT9BRK9s(graphicsLayer222, shape422, j8222, j9222, f3, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-692668920, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7
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

                        public final void invoke(Composer composer3, int i25) {
                            String str;
                            ComposerKt.sourceInformation(composer3, "C290@13592L21,290@13629L550,289@13528L2607:ModalBottomSheet.kt#uh7d8r");
                            if ((i25 & 3) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-692668920, i25, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:289)");
                                }
                                Modifier windowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), function25.invoke(composer3, 0));
                                ComposerKt.sourceInformationMarkerStart(composer3, 320924084, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                boolean changedInstance = composer3.changedInstance(animatable);
                                final Animatable<Float, AnimationVector1D> animatable2 = animatable;
                                Object rememberedValue6 = composer3.rememberedValue();
                                if (changedInstance || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$1$1
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
                                            float calculatePredictiveBackScaleX;
                                            float calculatePredictiveBackScaleY;
                                            long j10;
                                            float floatValue = animatable2.getValue().floatValue();
                                            calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                                            calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                                            graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY == 0.0f ? 1.0f : calculatePredictiveBackScaleX / calculatePredictiveBackScaleY);
                                            j10 = ModalBottomSheetKt.PredictiveBackChildTransformOrigin;
                                            graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(j10);
                                        }
                                    };
                                    composer3.updateRememberedValue(rememberedValue6);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                Modifier graphicsLayer2222 = GraphicsLayerModifierKt.graphicsLayer(windowInsetsPadding, (Function1) rememberedValue6);
                                Function2<Composer, Integer, Unit> function210 = function28222;
                                final SheetState sheetState5 = sheetState4222;
                                final Function0<Unit> function04 = function03222;
                                final CoroutineScope coroutineScope2 = coroutineScope;
                                Function3<ColumnScope, Composer, Integer, Unit> function32 = function3;
                                ComposerKt.sourceInformationMarkerStart(composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                                MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                                ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, graphicsLayer2222);
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
                                ComposerKt.sourceInformationMarkerStart(composer3, 806123989, "C340@16116L9:ModalBottomSheet.kt#uh7d8r");
                                composer3.startReplaceGroup(-1636564008);
                                ComposerKt.sourceInformation(composer3, "303@14272L54,304@14368L48,305@14457L47,309@14672L1346,306@14521L1568");
                                if (function210 != null) {
                                    Strings.Companion companion222 = Strings.INSTANCE;
                                    final String m3160getString2EP1pXo222 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer3, 0);
                                    Strings.Companion companion3 = Strings.INSTANCE;
                                    final String m3160getString2EP1pXo3 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer3, 0);
                                    Strings.Companion companion4 = Strings.INSTANCE;
                                    final String m3160getString2EP1pXo4 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer3, 0);
                                    Modifier align = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1636549585, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                    boolean changed2 = composer3.changed(sheetState5) | composer3.changed(m3160getString2EP1pXo3) | composer3.changed(function04) | composer3.changed(m3160getString2EP1pXo4) | composer3.changedInstance(coroutineScope2) | composer3.changed(m3160getString2EP1pXo222);
                                    Object rememberedValue7 = composer3.rememberedValue();
                                    if (changed2 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                        str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                                        rememberedValue7 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1
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
                                                final SheetState sheetState6 = SheetState.this;
                                                String str2 = m3160getString2EP1pXo3;
                                                String str3 = m3160getString2EP1pXo4;
                                                String str4 = m3160getString2EP1pXo222;
                                                final Function0<Unit> function05 = function04;
                                                final CoroutineScope coroutineScope3 = coroutineScope2;
                                                SemanticsPropertiesKt.dismiss(semanticsPropertyReceiver, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        function05.invoke();
                                                        return true;
                                                    }
                                                });
                                                if (sheetState6.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                                    SemanticsPropertiesKt.expand(semanticsPropertyReceiver, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(0);
                                                        }

                                                        /* compiled from: ModalBottomSheet.kt */
                                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                        @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1", f = "ModalBottomSheet.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
                                                        /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1, reason: invalid class name */
                                                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                            final /* synthetic */ SheetState $sheetState;
                                                            int label;

                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                            AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                super(2, continuation);
                                                                this.$sheetState = sheetState;
                                                            }

                                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                return new AnonymousClass1(this.$sheetState, continuation);
                                                            }

                                                            @Override // kotlin.jvm.functions.Function2
                                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                            }

                                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                            public final Object invokeSuspend(Object obj) {
                                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                int i = this.label;
                                                                if (i == 0) {
                                                                    ResultKt.throwOnFailure(obj);
                                                                    this.label = 1;
                                                                    if (this.$sheetState.expand(this) == coroutine_suspended) {
                                                                        return coroutine_suspended;
                                                                    }
                                                                } else {
                                                                    if (i != 1) {
                                                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                    }
                                                                    ResultKt.throwOnFailure(obj);
                                                                }
                                                                return Unit.INSTANCE;
                                                            }
                                                        }

                                                        /* JADX WARN: Can't rename method to resolve collision */
                                                        @Override // kotlin.jvm.functions.Function0
                                                        public final Boolean invoke() {
                                                            if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                                BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(sheetState6, null), 3, null);
                                                            }
                                                            return true;
                                                        }
                                                    });
                                                } else if (sheetState6.getHasPartiallyExpandedState()) {
                                                    SemanticsPropertiesKt.collapse(semanticsPropertyReceiver, str4, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(0);
                                                        }

                                                        /* JADX WARN: Can't rename method to resolve collision */
                                                        @Override // kotlin.jvm.functions.Function0
                                                        public final Boolean invoke() {
                                                            if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                                BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(SheetState.this, null), 3, null);
                                                            }
                                                            return true;
                                                        }

                                                        /* compiled from: ModalBottomSheet.kt */
                                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                        @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1", f = "ModalBottomSheet.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {})
                                                        /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1, reason: invalid class name */
                                                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                            final /* synthetic */ SheetState $this_with;
                                                            int label;

                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                            AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                super(2, continuation);
                                                                this.$this_with = sheetState;
                                                            }

                                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                return new AnonymousClass1(this.$this_with, continuation);
                                                            }

                                                            @Override // kotlin.jvm.functions.Function2
                                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                            }

                                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                            public final Object invokeSuspend(Object obj) {
                                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                int i = this.label;
                                                                if (i == 0) {
                                                                    ResultKt.throwOnFailure(obj);
                                                                    this.label = 1;
                                                                    if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                        return coroutine_suspended;
                                                                    }
                                                                } else {
                                                                    if (i != 1) {
                                                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                    }
                                                                    ResultKt.throwOnFailure(obj);
                                                                }
                                                                return Unit.INSTANCE;
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        };
                                        composer3.updateRememberedValue(rememberedValue7);
                                    } else {
                                        str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    Modifier semantics = SemanticsModifierKt.semantics(align, true, (Function1) rememberedValue7);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, str);
                                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer3, semantics);
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
                                    Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                        m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                        m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                    }
                                    Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                    ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer3, -760467413, "C337@16059L12:ModalBottomSheet.kt#uh7d8r");
                                    function210.invoke(composer3, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                }
                                composer3.endReplaceGroup();
                                function32.invoke(columnScopeInstance, composer3, 6);
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
                    }, startRestartGroup, 54), composer2, (i23222 & 896) | (i23222 & 112) | 12582912 | (i24222 & 7168) | (i24222 & 57344), 96);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    sheetState3 = sheetState4222;
                    function26 = function28222;
                    function27 = function29222;
                    shape2 = shape422;
                    f6 = f3;
                    modifier4 = modifier522;
                    f7 = f5;
                    j6 = j9222;
                    j7 = j8222;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                i11 = i12;
                i13 = i3 & 2048;
                if (i13 == 0) {
                }
                function23 = function2;
                int i2222 = i11;
                if ((i2 & 3072) == 0) {
                }
                if ((i3 & 8192) != 0) {
                }
                if ((i7 & 306783379) == 306783378) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i5 != 0) {
                }
                if ((i3 & 32) != 0) {
                }
                if (i6 == 0) {
                }
                if ((i3 & 128) != 0) {
                }
                if ((i3 & 256) == 0) {
                }
                if ((i3 & 512) == 0) {
                }
                if (i10 == 0) {
                }
                if (i13 == 0) {
                }
                float f8222 = m1948getSheetMaxWidthD9Ej5fM;
                if ((i3 & 4096) == 0) {
                }
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                Strings.Companion companion222 = Strings.INSTANCE;
                final String m3160getString2EP1pXo222 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_pane_title), startRestartGroup, 0);
                Modifier modifier5222 = modifier3;
                Shape shape4222 = shape3;
                Modifier fillMaxWidth$default222 = SizeKt.fillMaxWidth$default(SizeKt.m937widthInVpY3zN4$default(boxScope.align(modifier3, Alignment.INSTANCE.getTopCenter()), 0.0f, f4, 1, null), 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237857395, "CC(remember):ModalBottomSheet.kt#9igjgp");
                i16 = (3670016 & i7) ^ 1572864;
                if (i16 > 1048576) {
                }
                f5 = f4;
                if ((i7 & 1572864) != 1048576) {
                }
                z = true;
                rememberedValue = startRestartGroup.rememberedValue();
                if (!z) {
                }
                rememberedValue = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState2, Orientation.Vertical, function1);
                startRestartGroup.updateRememberedValue(rememberedValue);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                long j82222 = j5;
                Modifier nestedScroll$default2222 = NestedScrollModifierKt.nestedScroll$default(fillMaxWidth$default222, (NestedScrollConnection) rememberedValue, null, 2, null);
                AnchoredDraggableState<SheetValue> anchoredDraggableState$material3_release2222 = sheetState2.getAnchoredDraggableState$material3_release();
                Orientation orientation3222 = Orientation.Vertical;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237872435, "CC(remember):ModalBottomSheet.kt#9igjgp");
                if (i16 <= 1048576) {
                }
                rememberedValue2 = startRestartGroup.rememberedValue();
                if (!z2) {
                }
                rememberedValue2 = (Function2) new Function2<IntSize, Constraints, Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue>>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1

                    /* compiled from: ModalBottomSheet.kt */
                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[SheetValue.values().length];
                            try {
                                iArr[SheetValue.Hidden.ordinal()] = 1;
                            } catch (NoSuchFieldError unused) {
                            }
                            try {
                                iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                            } catch (NoSuchFieldError unused2) {
                            }
                            try {
                                iArr[SheetValue.Expanded.ordinal()] = 3;
                            } catch (NoSuchFieldError unused3) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue> invoke(IntSize intSize, Constraints constraints) {
                        return m2402invokeGpV2Q24(intSize.m7578unboximpl(), constraints.getValue());
                    }

                    /* renamed from: invoke-GpV2Q24, reason: not valid java name */
                    public final Pair<DraggableAnchors<SheetValue>, SheetValue> m2402invokeGpV2Q24(final long j92222, long j10) {
                        SheetValue sheetValue;
                        final float m7341getMaxHeightimpl = Constraints.m7341getMaxHeightimpl(j10);
                        final SheetState sheetState42222 = SheetState.this;
                        DraggableAnchors DraggableAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<SheetValue>, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1$newAnchors$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                invoke2(draggableAnchorsConfig);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                                draggableAnchorsConfig.at(SheetValue.Hidden, m7341getMaxHeightimpl);
                                if (IntSize.m7573getHeightimpl(j92222) > m7341getMaxHeightimpl / 2 && !sheetState42222.getSkipPartiallyExpanded()) {
                                    draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, m7341getMaxHeightimpl / 2.0f);
                                }
                                if (IntSize.m7573getHeightimpl(j92222) != 0) {
                                    draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(0.0f, m7341getMaxHeightimpl - IntSize.m7573getHeightimpl(j92222)));
                                }
                            }
                        });
                        int i232222 = WhenMappings.$EnumSwitchMapping$0[SheetState.this.getAnchoredDraggableState$material3_release().getTargetValue().ordinal()];
                        if (i232222 == 1) {
                            sheetValue = SheetValue.Hidden;
                        } else if (i232222 == 2 || i232222 == 3) {
                            if (DraggableAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                                sheetValue = SheetValue.PartiallyExpanded;
                            } else {
                                sheetValue = DraggableAnchors.hasAnchorFor(SheetValue.Expanded) ? SheetValue.Expanded : SheetValue.Hidden;
                            }
                        } else {
                            throw new NoWhenBranchMatchedException();
                        }
                        return TuplesKt.to(DraggableAnchors, sheetValue);
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue2);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Modifier draggableAnchors2222 = AnchoredDraggableKt.draggableAnchors(nestedScroll$default2222, anchoredDraggableState$material3_release2222, orientation3222, (Function2) rememberedValue2);
                DraggableState draggableState2222 = sheetState2.getAnchoredDraggableState$material3_release().getDraggableState();
                Orientation orientation22222 = Orientation.Vertical;
                boolean isVisible2222 = sheetState2.isVisible();
                boolean isAnimationRunning2222 = sheetState2.getAnchoredDraggableState$material3_release().isAnimationRunning();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237929350, "CC(remember):ModalBottomSheet.kt#9igjgp");
                if ((57344 & i7) == 16384) {
                }
                rememberedValue3 = startRestartGroup.rememberedValue();
                if (!z3) {
                }
                rememberedValue3 = (Function3) new ModalBottomSheetKt$ModalBottomSheetContent$4$1(function1, null);
                startRestartGroup.updateRememberedValue(rememberedValue3);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Modifier draggable$default2222 = DraggableKt.draggable$default(draggableAnchors2222, draggableState2222, orientation22222, isVisible2222, null, isAnimationRunning2222, null, (Function3) rememberedValue3, false, 168, null);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237931647, "CC(remember):ModalBottomSheet.kt#9igjgp");
                changed = startRestartGroup.changed(m3160getString2EP1pXo222);
                rememberedValue4 = startRestartGroup.rememberedValue();
                if (!changed) {
                }
                rememberedValue4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$5$1
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
                        SemanticsPropertiesKt.setPaneTitle(semanticsPropertyReceiver, m3160getString2EP1pXo222);
                        SemanticsPropertiesKt.setTraversalIndex(semanticsPropertyReceiver, 0.0f);
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue4);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                boolean z52222 = false;
                Modifier semantics$default2222 = SemanticsModifierKt.semantics$default(draggable$default2222, false, (Function1) rememberedValue4, 1, null);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237936755, "CC(remember):ModalBottomSheet.kt#9igjgp");
                if (i16 <= 1048576) {
                }
                if ((i7 & 112) != 32) {
                }
                z52222 = true;
                z4 = z52222 | z6;
                rememberedValue5 = startRestartGroup.rememberedValue();
                if (!z4) {
                }
                rememberedValue5 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$6$1
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
                        float calculatePredictiveBackScaleX;
                        float calculatePredictiveBackScaleY;
                        float offset = SheetState.this.getAnchoredDraggableState$material3_release().getOffset();
                        float m4409getHeightimpl = Size.m4409getHeightimpl(graphicsLayerScope.getSize());
                        if (Float.isNaN(offset) || Float.isNaN(m4409getHeightimpl) || m4409getHeightimpl == 0.0f) {
                            return;
                        }
                        float floatValue = animatable.getValue().floatValue();
                        calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                        graphicsLayerScope.setScaleX(calculatePredictiveBackScaleX);
                        calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                        graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY);
                        graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, (offset + m4409getHeightimpl) / m4409getHeightimpl));
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue5);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Modifier graphicsLayer2222 = GraphicsLayerModifierKt.graphicsLayer(semantics$default2222, (Function1) rememberedValue5);
                final SheetState sheetState42222 = sheetState2;
                final Function0<Unit> function032222 = function02;
                final Function2<? super Composer, ? super Integer, Unit> function282222 = m2126getLambda2$material3_release;
                Function2<? super Composer, ? super Integer, ? extends WindowInsets> function292222 = function25;
                int i232222 = i7 >> 21;
                int i242222 = i15 << 9;
                long j92222 = j4;
                composer2 = startRestartGroup;
                SurfaceKt.m2699SurfaceT9BRK9s(graphicsLayer2222, shape4222, j82222, j92222, f3, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-692668920, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7
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

                    public final void invoke(Composer composer3, int i25) {
                        String str;
                        ComposerKt.sourceInformation(composer3, "C290@13592L21,290@13629L550,289@13528L2607:ModalBottomSheet.kt#uh7d8r");
                        if ((i25 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-692668920, i25, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:289)");
                            }
                            Modifier windowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), function25.invoke(composer3, 0));
                            ComposerKt.sourceInformationMarkerStart(composer3, 320924084, "CC(remember):ModalBottomSheet.kt#9igjgp");
                            boolean changedInstance = composer3.changedInstance(animatable);
                            final Animatable<Float, AnimationVector1D> animatable2 = animatable;
                            Object rememberedValue6 = composer3.rememberedValue();
                            if (changedInstance || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$1$1
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
                                        float calculatePredictiveBackScaleX;
                                        float calculatePredictiveBackScaleY;
                                        long j10;
                                        float floatValue = animatable2.getValue().floatValue();
                                        calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                                        calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                                        graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY == 0.0f ? 1.0f : calculatePredictiveBackScaleX / calculatePredictiveBackScaleY);
                                        j10 = ModalBottomSheetKt.PredictiveBackChildTransformOrigin;
                                        graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(j10);
                                    }
                                };
                                composer3.updateRememberedValue(rememberedValue6);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            Modifier graphicsLayer22222 = GraphicsLayerModifierKt.graphicsLayer(windowInsetsPadding, (Function1) rememberedValue6);
                            Function2<Composer, Integer, Unit> function210 = function282222;
                            final SheetState sheetState5 = sheetState42222;
                            final Function0<Unit> function04 = function032222;
                            final CoroutineScope coroutineScope2 = coroutineScope;
                            Function3<ColumnScope, Composer, Integer, Unit> function32 = function3;
                            ComposerKt.sourceInformationMarkerStart(composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                            ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, graphicsLayer22222);
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
                            ComposerKt.sourceInformationMarkerStart(composer3, 806123989, "C340@16116L9:ModalBottomSheet.kt#uh7d8r");
                            composer3.startReplaceGroup(-1636564008);
                            ComposerKt.sourceInformation(composer3, "303@14272L54,304@14368L48,305@14457L47,309@14672L1346,306@14521L1568");
                            if (function210 != null) {
                                Strings.Companion companion2222 = Strings.INSTANCE;
                                final String m3160getString2EP1pXo2222 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer3, 0);
                                Strings.Companion companion3 = Strings.INSTANCE;
                                final String m3160getString2EP1pXo3 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer3, 0);
                                Strings.Companion companion4 = Strings.INSTANCE;
                                final String m3160getString2EP1pXo4 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer3, 0);
                                Modifier align = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
                                ComposerKt.sourceInformationMarkerStart(composer3, -1636549585, "CC(remember):ModalBottomSheet.kt#9igjgp");
                                boolean changed2 = composer3.changed(sheetState5) | composer3.changed(m3160getString2EP1pXo3) | composer3.changed(function04) | composer3.changed(m3160getString2EP1pXo4) | composer3.changedInstance(coroutineScope2) | composer3.changed(m3160getString2EP1pXo2222);
                                Object rememberedValue7 = composer3.rememberedValue();
                                if (changed2 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                    str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                                    rememberedValue7 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1
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
                                            final SheetState sheetState6 = SheetState.this;
                                            String str2 = m3160getString2EP1pXo3;
                                            String str3 = m3160getString2EP1pXo4;
                                            String str4 = m3160getString2EP1pXo2222;
                                            final Function0<Unit> function05 = function04;
                                            final CoroutineScope coroutineScope3 = coroutineScope2;
                                            SemanticsPropertiesKt.dismiss(semanticsPropertyReceiver, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    function05.invoke();
                                                    return true;
                                                }
                                            });
                                            if (sheetState6.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                                SemanticsPropertiesKt.expand(semanticsPropertyReceiver, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* compiled from: ModalBottomSheet.kt */
                                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1", f = "ModalBottomSheet.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
                                                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1, reason: invalid class name */
                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ SheetState $sheetState;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                            super(2, continuation);
                                                            this.$sheetState = sheetState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new AnonymousClass1(this.$sheetState, continuation);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Object invokeSuspend(Object obj) {
                                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                            int i = this.label;
                                                            if (i == 0) {
                                                                ResultKt.throwOnFailure(obj);
                                                                this.label = 1;
                                                                if (this.$sheetState.expand(this) == coroutine_suspended) {
                                                                    return coroutine_suspended;
                                                                }
                                                            } else {
                                                                if (i != 1) {
                                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                }
                                                                ResultKt.throwOnFailure(obj);
                                                            }
                                                            return Unit.INSTANCE;
                                                        }
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(sheetState6, null), 3, null);
                                                        }
                                                        return true;
                                                    }
                                                });
                                            } else if (sheetState6.getHasPartiallyExpandedState()) {
                                                SemanticsPropertiesKt.collapse(semanticsPropertyReceiver, str4, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(SheetState.this, null), 3, null);
                                                        }
                                                        return true;
                                                    }

                                                    /* compiled from: ModalBottomSheet.kt */
                                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1", f = "ModalBottomSheet.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {})
                                                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1, reason: invalid class name */
                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ SheetState $this_with;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                            super(2, continuation);
                                                            this.$this_with = sheetState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new AnonymousClass1(this.$this_with, continuation);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Object invokeSuspend(Object obj) {
                                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                            int i = this.label;
                                                            if (i == 0) {
                                                                ResultKt.throwOnFailure(obj);
                                                                this.label = 1;
                                                                if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                    return coroutine_suspended;
                                                                }
                                                            } else {
                                                                if (i != 1) {
                                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                }
                                                                ResultKt.throwOnFailure(obj);
                                                            }
                                                            return Unit.INSTANCE;
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    };
                                    composer3.updateRememberedValue(rememberedValue7);
                                } else {
                                    str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                Modifier semantics = SemanticsModifierKt.semantics(align, true, (Function1) rememberedValue7);
                                ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, str);
                                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer3, semantics);
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
                                Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                    m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                    m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                }
                                Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer3, -760467413, "C337@16059L12:ModalBottomSheet.kt#uh7d8r");
                                function210.invoke(composer3, 0);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                composer3.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                            }
                            composer3.endReplaceGroup();
                            function32.invoke(columnScopeInstance, composer3, 6);
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
                }, startRestartGroup, 54), composer2, (i232222 & 896) | (i232222 & 112) | 12582912 | (i242222 & 7168) | (i242222 & 57344), 96);
                if (ComposerKt.isTraceInProgress()) {
                }
                sheetState3 = sheetState42222;
                function26 = function282222;
                function27 = function292222;
                shape2 = shape4222;
                f6 = f3;
                modifier4 = modifier5222;
                f7 = f5;
                j6 = j92222;
                j7 = j82222;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            function02 = function0;
            if ((i3 & 8) != 0) {
            }
            i5 = i3 & 16;
            if (i5 != 0) {
            }
            if ((i & 1572864) == 0) {
            }
            i6 = i3 & 64;
            if (i6 != 0) {
            }
            if ((i & 100663296) == 0) {
            }
            if ((i & 805306368) == 0) {
            }
            if ((i2 & 6) == 0) {
            }
            int i212 = i8;
            i9 = i3 & 1024;
            if (i9 != 0) {
            }
            i11 = i12;
            i13 = i3 & 2048;
            if (i13 == 0) {
            }
            function23 = function2;
            int i22222 = i11;
            if ((i2 & 3072) == 0) {
            }
            if ((i3 & 8192) != 0) {
            }
            if ((i7 & 306783379) == 306783378) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i5 != 0) {
            }
            if ((i3 & 32) != 0) {
            }
            if (i6 == 0) {
            }
            if ((i3 & 128) != 0) {
            }
            if ((i3 & 256) == 0) {
            }
            if ((i3 & 512) == 0) {
            }
            if (i10 == 0) {
            }
            if (i13 == 0) {
            }
            float f82222 = m1948getSheetMaxWidthD9Ej5fM;
            if ((i3 & 4096) == 0) {
            }
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            Strings.Companion companion2222 = Strings.INSTANCE;
            final String m3160getString2EP1pXo2222 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_pane_title), startRestartGroup, 0);
            Modifier modifier52222 = modifier3;
            Shape shape42222 = shape3;
            Modifier fillMaxWidth$default2222 = SizeKt.fillMaxWidth$default(SizeKt.m937widthInVpY3zN4$default(boxScope.align(modifier3, Alignment.INSTANCE.getTopCenter()), 0.0f, f4, 1, null), 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237857395, "CC(remember):ModalBottomSheet.kt#9igjgp");
            i16 = (3670016 & i7) ^ 1572864;
            if (i16 > 1048576) {
            }
            f5 = f4;
            if ((i7 & 1572864) != 1048576) {
            }
            z = true;
            rememberedValue = startRestartGroup.rememberedValue();
            if (!z) {
            }
            rememberedValue = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState2, Orientation.Vertical, function1);
            startRestartGroup.updateRememberedValue(rememberedValue);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            long j822222 = j5;
            Modifier nestedScroll$default22222 = NestedScrollModifierKt.nestedScroll$default(fillMaxWidth$default2222, (NestedScrollConnection) rememberedValue, null, 2, null);
            AnchoredDraggableState<SheetValue> anchoredDraggableState$material3_release22222 = sheetState2.getAnchoredDraggableState$material3_release();
            Orientation orientation32222 = Orientation.Vertical;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237872435, "CC(remember):ModalBottomSheet.kt#9igjgp");
            if (i16 <= 1048576) {
            }
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (!z2) {
            }
            rememberedValue2 = (Function2) new Function2<IntSize, Constraints, Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue>>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1

                /* compiled from: ModalBottomSheet.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[SheetValue.values().length];
                        try {
                            iArr[SheetValue.Hidden.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        try {
                            iArr[SheetValue.Expanded.ordinal()] = 3;
                        } catch (NoSuchFieldError unused3) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue> invoke(IntSize intSize, Constraints constraints) {
                    return m2402invokeGpV2Q24(intSize.m7578unboximpl(), constraints.getValue());
                }

                /* renamed from: invoke-GpV2Q24, reason: not valid java name */
                public final Pair<DraggableAnchors<SheetValue>, SheetValue> m2402invokeGpV2Q24(final long j922222, long j10) {
                    SheetValue sheetValue;
                    final float m7341getMaxHeightimpl = Constraints.m7341getMaxHeightimpl(j10);
                    final SheetState sheetState422222 = SheetState.this;
                    DraggableAnchors DraggableAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<SheetValue>, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1$newAnchors$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                            invoke2(draggableAnchorsConfig);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                            draggableAnchorsConfig.at(SheetValue.Hidden, m7341getMaxHeightimpl);
                            if (IntSize.m7573getHeightimpl(j922222) > m7341getMaxHeightimpl / 2 && !sheetState422222.getSkipPartiallyExpanded()) {
                                draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, m7341getMaxHeightimpl / 2.0f);
                            }
                            if (IntSize.m7573getHeightimpl(j922222) != 0) {
                                draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(0.0f, m7341getMaxHeightimpl - IntSize.m7573getHeightimpl(j922222)));
                            }
                        }
                    });
                    int i2322222 = WhenMappings.$EnumSwitchMapping$0[SheetState.this.getAnchoredDraggableState$material3_release().getTargetValue().ordinal()];
                    if (i2322222 == 1) {
                        sheetValue = SheetValue.Hidden;
                    } else if (i2322222 == 2 || i2322222 == 3) {
                        if (DraggableAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                            sheetValue = SheetValue.PartiallyExpanded;
                        } else {
                            sheetValue = DraggableAnchors.hasAnchorFor(SheetValue.Expanded) ? SheetValue.Expanded : SheetValue.Hidden;
                        }
                    } else {
                        throw new NoWhenBranchMatchedException();
                    }
                    return TuplesKt.to(DraggableAnchors, sheetValue);
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue2);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier draggableAnchors22222 = AnchoredDraggableKt.draggableAnchors(nestedScroll$default22222, anchoredDraggableState$material3_release22222, orientation32222, (Function2) rememberedValue2);
            DraggableState draggableState22222 = sheetState2.getAnchoredDraggableState$material3_release().getDraggableState();
            Orientation orientation222222 = Orientation.Vertical;
            boolean isVisible22222 = sheetState2.isVisible();
            boolean isAnimationRunning22222 = sheetState2.getAnchoredDraggableState$material3_release().isAnimationRunning();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237929350, "CC(remember):ModalBottomSheet.kt#9igjgp");
            if ((57344 & i7) == 16384) {
            }
            rememberedValue3 = startRestartGroup.rememberedValue();
            if (!z3) {
            }
            rememberedValue3 = (Function3) new ModalBottomSheetKt$ModalBottomSheetContent$4$1(function1, null);
            startRestartGroup.updateRememberedValue(rememberedValue3);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier draggable$default22222 = DraggableKt.draggable$default(draggableAnchors22222, draggableState22222, orientation222222, isVisible22222, null, isAnimationRunning22222, null, (Function3) rememberedValue3, false, 168, null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237931647, "CC(remember):ModalBottomSheet.kt#9igjgp");
            changed = startRestartGroup.changed(m3160getString2EP1pXo2222);
            rememberedValue4 = startRestartGroup.rememberedValue();
            if (!changed) {
            }
            rememberedValue4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$5$1
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
                    SemanticsPropertiesKt.setPaneTitle(semanticsPropertyReceiver, m3160getString2EP1pXo2222);
                    SemanticsPropertiesKt.setTraversalIndex(semanticsPropertyReceiver, 0.0f);
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue4);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            boolean z522222 = false;
            Modifier semantics$default22222 = SemanticsModifierKt.semantics$default(draggable$default22222, false, (Function1) rememberedValue4, 1, null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237936755, "CC(remember):ModalBottomSheet.kt#9igjgp");
            if (i16 <= 1048576) {
            }
            if ((i7 & 112) != 32) {
            }
            z522222 = true;
            z4 = z522222 | z6;
            rememberedValue5 = startRestartGroup.rememberedValue();
            if (!z4) {
            }
            rememberedValue5 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$6$1
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
                    float calculatePredictiveBackScaleX;
                    float calculatePredictiveBackScaleY;
                    float offset = SheetState.this.getAnchoredDraggableState$material3_release().getOffset();
                    float m4409getHeightimpl = Size.m4409getHeightimpl(graphicsLayerScope.getSize());
                    if (Float.isNaN(offset) || Float.isNaN(m4409getHeightimpl) || m4409getHeightimpl == 0.0f) {
                        return;
                    }
                    float floatValue = animatable.getValue().floatValue();
                    calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                    graphicsLayerScope.setScaleX(calculatePredictiveBackScaleX);
                    calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                    graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY);
                    graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, (offset + m4409getHeightimpl) / m4409getHeightimpl));
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue5);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier graphicsLayer22222 = GraphicsLayerModifierKt.graphicsLayer(semantics$default22222, (Function1) rememberedValue5);
            final SheetState sheetState422222 = sheetState2;
            final Function0<Unit> function0322222 = function02;
            final Function2<? super Composer, ? super Integer, Unit> function2822222 = m2126getLambda2$material3_release;
            Function2<? super Composer, ? super Integer, ? extends WindowInsets> function2922222 = function25;
            int i2322222 = i7 >> 21;
            int i2422222 = i15 << 9;
            long j922222 = j4;
            composer2 = startRestartGroup;
            SurfaceKt.m2699SurfaceT9BRK9s(graphicsLayer22222, shape42222, j822222, j922222, f3, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-692668920, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7
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

                public final void invoke(Composer composer3, int i25) {
                    String str;
                    ComposerKt.sourceInformation(composer3, "C290@13592L21,290@13629L550,289@13528L2607:ModalBottomSheet.kt#uh7d8r");
                    if ((i25 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-692668920, i25, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:289)");
                        }
                        Modifier windowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), function25.invoke(composer3, 0));
                        ComposerKt.sourceInformationMarkerStart(composer3, 320924084, "CC(remember):ModalBottomSheet.kt#9igjgp");
                        boolean changedInstance = composer3.changedInstance(animatable);
                        final Animatable<Float, AnimationVector1D> animatable2 = animatable;
                        Object rememberedValue6 = composer3.rememberedValue();
                        if (changedInstance || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$1$1
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
                                    float calculatePredictiveBackScaleX;
                                    float calculatePredictiveBackScaleY;
                                    long j10;
                                    float floatValue = animatable2.getValue().floatValue();
                                    calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                                    calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                                    graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY == 0.0f ? 1.0f : calculatePredictiveBackScaleX / calculatePredictiveBackScaleY);
                                    j10 = ModalBottomSheetKt.PredictiveBackChildTransformOrigin;
                                    graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(j10);
                                }
                            };
                            composer3.updateRememberedValue(rememberedValue6);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        Modifier graphicsLayer222222 = GraphicsLayerModifierKt.graphicsLayer(windowInsetsPadding, (Function1) rememberedValue6);
                        Function2<Composer, Integer, Unit> function210 = function2822222;
                        final SheetState sheetState5 = sheetState422222;
                        final Function0<Unit> function04 = function0322222;
                        final CoroutineScope coroutineScope2 = coroutineScope;
                        Function3<ColumnScope, Composer, Integer, Unit> function32 = function3;
                        ComposerKt.sourceInformationMarkerStart(composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, graphicsLayer222222);
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
                        ComposerKt.sourceInformationMarkerStart(composer3, 806123989, "C340@16116L9:ModalBottomSheet.kt#uh7d8r");
                        composer3.startReplaceGroup(-1636564008);
                        ComposerKt.sourceInformation(composer3, "303@14272L54,304@14368L48,305@14457L47,309@14672L1346,306@14521L1568");
                        if (function210 != null) {
                            Strings.Companion companion22222 = Strings.INSTANCE;
                            final String m3160getString2EP1pXo22222 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer3, 0);
                            Strings.Companion companion3 = Strings.INSTANCE;
                            final String m3160getString2EP1pXo3 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer3, 0);
                            Strings.Companion companion4 = Strings.INSTANCE;
                            final String m3160getString2EP1pXo4 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer3, 0);
                            Modifier align = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
                            ComposerKt.sourceInformationMarkerStart(composer3, -1636549585, "CC(remember):ModalBottomSheet.kt#9igjgp");
                            boolean changed2 = composer3.changed(sheetState5) | composer3.changed(m3160getString2EP1pXo3) | composer3.changed(function04) | composer3.changed(m3160getString2EP1pXo4) | composer3.changedInstance(coroutineScope2) | composer3.changed(m3160getString2EP1pXo22222);
                            Object rememberedValue7 = composer3.rememberedValue();
                            if (changed2 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                                rememberedValue7 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1
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
                                        final SheetState sheetState6 = SheetState.this;
                                        String str2 = m3160getString2EP1pXo3;
                                        String str3 = m3160getString2EP1pXo4;
                                        String str4 = m3160getString2EP1pXo22222;
                                        final Function0<Unit> function05 = function04;
                                        final CoroutineScope coroutineScope3 = coroutineScope2;
                                        SemanticsPropertiesKt.dismiss(semanticsPropertyReceiver, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Boolean invoke() {
                                                function05.invoke();
                                                return true;
                                            }
                                        });
                                        if (sheetState6.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                            SemanticsPropertiesKt.expand(semanticsPropertyReceiver, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* compiled from: ModalBottomSheet.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1", f = "ModalBottomSheet.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
                                                /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1, reason: invalid class name */
                                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ SheetState $sheetState;
                                                    int label;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                        super(2, continuation);
                                                        this.$sheetState = sheetState;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new AnonymousClass1(this.$sheetState, continuation);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Object invokeSuspend(Object obj) {
                                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                        int i = this.label;
                                                        if (i == 0) {
                                                            ResultKt.throwOnFailure(obj);
                                                            this.label = 1;
                                                            if (this.$sheetState.expand(this) == coroutine_suspended) {
                                                                return coroutine_suspended;
                                                            }
                                                        } else {
                                                            if (i != 1) {
                                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                            }
                                                            ResultKt.throwOnFailure(obj);
                                                        }
                                                        return Unit.INSTANCE;
                                                    }
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(sheetState6, null), 3, null);
                                                    }
                                                    return true;
                                                }
                                            });
                                        } else if (sheetState6.getHasPartiallyExpandedState()) {
                                            SemanticsPropertiesKt.collapse(semanticsPropertyReceiver, str4, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(SheetState.this, null), 3, null);
                                                    }
                                                    return true;
                                                }

                                                /* compiled from: ModalBottomSheet.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1", f = "ModalBottomSheet.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {})
                                                /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1, reason: invalid class name */
                                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ SheetState $this_with;
                                                    int label;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                        super(2, continuation);
                                                        this.$this_with = sheetState;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new AnonymousClass1(this.$this_with, continuation);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Object invokeSuspend(Object obj) {
                                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                        int i = this.label;
                                                        if (i == 0) {
                                                            ResultKt.throwOnFailure(obj);
                                                            this.label = 1;
                                                            if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                return coroutine_suspended;
                                                            }
                                                        } else {
                                                            if (i != 1) {
                                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                            }
                                                            ResultKt.throwOnFailure(obj);
                                                        }
                                                        return Unit.INSTANCE;
                                                    }
                                                }
                                            });
                                        }
                                    }
                                };
                                composer3.updateRememberedValue(rememberedValue7);
                            } else {
                                str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            Modifier semantics = SemanticsModifierKt.semantics(align, true, (Function1) rememberedValue7);
                            ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, str);
                            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer3, semantics);
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
                            Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                            }
                            Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                            ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer3, -760467413, "C337@16059L12:ModalBottomSheet.kt#uh7d8r");
                            function210.invoke(composer3, 0);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                        }
                        composer3.endReplaceGroup();
                        function32.invoke(columnScopeInstance, composer3, 6);
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
            }, startRestartGroup, 54), composer2, (i2322222 & 896) | (i2322222 & 112) | 12582912 | (i2422222 & 7168) | (i2422222 & 57344), 96);
            if (ComposerKt.isTraceInProgress()) {
            }
            sheetState3 = sheetState422222;
            function26 = function2822222;
            function27 = function2922222;
            shape2 = shape42222;
            f6 = f3;
            modifier4 = modifier52222;
            f7 = f5;
            j6 = j922222;
            j7 = j822222;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        int i202 = 2048;
        if ((i3 & 4) == 0) {
        }
        function02 = function0;
        if ((i3 & 8) != 0) {
        }
        i5 = i3 & 16;
        if (i5 != 0) {
        }
        if ((i & 1572864) == 0) {
        }
        i6 = i3 & 64;
        if (i6 != 0) {
        }
        if ((i & 100663296) == 0) {
        }
        if ((i & 805306368) == 0) {
        }
        if ((i2 & 6) == 0) {
        }
        int i2122 = i8;
        i9 = i3 & 1024;
        if (i9 != 0) {
        }
        i11 = i12;
        i13 = i3 & 2048;
        if (i13 == 0) {
        }
        function23 = function2;
        int i222222 = i11;
        if ((i2 & 3072) == 0) {
        }
        if ((i3 & 8192) != 0) {
        }
        if ((i7 & 306783379) == 306783378) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i5 != 0) {
        }
        if ((i3 & 32) != 0) {
        }
        if (i6 == 0) {
        }
        if ((i3 & 128) != 0) {
        }
        if ((i3 & 256) == 0) {
        }
        if ((i3 & 512) == 0) {
        }
        if (i10 == 0) {
        }
        if (i13 == 0) {
        }
        float f822222 = m1948getSheetMaxWidthD9Ej5fM;
        if ((i3 & 4096) == 0) {
        }
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        Strings.Companion companion22222 = Strings.INSTANCE;
        final String m3160getString2EP1pXo22222 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_pane_title), startRestartGroup, 0);
        Modifier modifier522222 = modifier3;
        Shape shape422222 = shape3;
        Modifier fillMaxWidth$default22222 = SizeKt.fillMaxWidth$default(SizeKt.m937widthInVpY3zN4$default(boxScope.align(modifier3, Alignment.INSTANCE.getTopCenter()), 0.0f, f4, 1, null), 0.0f, 1, null);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237857395, "CC(remember):ModalBottomSheet.kt#9igjgp");
        i16 = (3670016 & i7) ^ 1572864;
        if (i16 > 1048576) {
        }
        f5 = f4;
        if ((i7 & 1572864) != 1048576) {
        }
        z = true;
        rememberedValue = startRestartGroup.rememberedValue();
        if (!z) {
        }
        rememberedValue = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState2, Orientation.Vertical, function1);
        startRestartGroup.updateRememberedValue(rememberedValue);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        long j8222222 = j5;
        Modifier nestedScroll$default222222 = NestedScrollModifierKt.nestedScroll$default(fillMaxWidth$default22222, (NestedScrollConnection) rememberedValue, null, 2, null);
        AnchoredDraggableState<SheetValue> anchoredDraggableState$material3_release222222 = sheetState2.getAnchoredDraggableState$material3_release();
        Orientation orientation322222 = Orientation.Vertical;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237872435, "CC(remember):ModalBottomSheet.kt#9igjgp");
        if (i16 <= 1048576) {
        }
        rememberedValue2 = startRestartGroup.rememberedValue();
        if (!z2) {
        }
        rememberedValue2 = (Function2) new Function2<IntSize, Constraints, Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue>>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1

            /* compiled from: ModalBottomSheet.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[SheetValue.values().length];
                    try {
                        iArr[SheetValue.Hidden.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[SheetValue.Expanded.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Pair<? extends DraggableAnchors<SheetValue>, ? extends SheetValue> invoke(IntSize intSize, Constraints constraints) {
                return m2402invokeGpV2Q24(intSize.m7578unboximpl(), constraints.getValue());
            }

            /* renamed from: invoke-GpV2Q24, reason: not valid java name */
            public final Pair<DraggableAnchors<SheetValue>, SheetValue> m2402invokeGpV2Q24(final long j9222222, long j10) {
                SheetValue sheetValue;
                final float m7341getMaxHeightimpl = Constraints.m7341getMaxHeightimpl(j10);
                final SheetState sheetState4222222 = SheetState.this;
                DraggableAnchors DraggableAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<SheetValue>, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$3$1$newAnchors$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                        invoke2(draggableAnchorsConfig);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
                        draggableAnchorsConfig.at(SheetValue.Hidden, m7341getMaxHeightimpl);
                        if (IntSize.m7573getHeightimpl(j9222222) > m7341getMaxHeightimpl / 2 && !sheetState4222222.getSkipPartiallyExpanded()) {
                            draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, m7341getMaxHeightimpl / 2.0f);
                        }
                        if (IntSize.m7573getHeightimpl(j9222222) != 0) {
                            draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(0.0f, m7341getMaxHeightimpl - IntSize.m7573getHeightimpl(j9222222)));
                        }
                    }
                });
                int i23222222 = WhenMappings.$EnumSwitchMapping$0[SheetState.this.getAnchoredDraggableState$material3_release().getTargetValue().ordinal()];
                if (i23222222 == 1) {
                    sheetValue = SheetValue.Hidden;
                } else if (i23222222 == 2 || i23222222 == 3) {
                    if (DraggableAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                        sheetValue = SheetValue.PartiallyExpanded;
                    } else {
                        sheetValue = DraggableAnchors.hasAnchorFor(SheetValue.Expanded) ? SheetValue.Expanded : SheetValue.Hidden;
                    }
                } else {
                    throw new NoWhenBranchMatchedException();
                }
                return TuplesKt.to(DraggableAnchors, sheetValue);
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue2);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Modifier draggableAnchors222222 = AnchoredDraggableKt.draggableAnchors(nestedScroll$default222222, anchoredDraggableState$material3_release222222, orientation322222, (Function2) rememberedValue2);
        DraggableState draggableState222222 = sheetState2.getAnchoredDraggableState$material3_release().getDraggableState();
        Orientation orientation2222222 = Orientation.Vertical;
        boolean isVisible222222 = sheetState2.isVisible();
        boolean isAnimationRunning222222 = sheetState2.getAnchoredDraggableState$material3_release().isAnimationRunning();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237929350, "CC(remember):ModalBottomSheet.kt#9igjgp");
        if ((57344 & i7) == 16384) {
        }
        rememberedValue3 = startRestartGroup.rememberedValue();
        if (!z3) {
        }
        rememberedValue3 = (Function3) new ModalBottomSheetKt$ModalBottomSheetContent$4$1(function1, null);
        startRestartGroup.updateRememberedValue(rememberedValue3);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Modifier draggable$default222222 = DraggableKt.draggable$default(draggableAnchors222222, draggableState222222, orientation2222222, isVisible222222, null, isAnimationRunning222222, null, (Function3) rememberedValue3, false, 168, null);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237931647, "CC(remember):ModalBottomSheet.kt#9igjgp");
        changed = startRestartGroup.changed(m3160getString2EP1pXo22222);
        rememberedValue4 = startRestartGroup.rememberedValue();
        if (!changed) {
        }
        rememberedValue4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$5$1
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
                SemanticsPropertiesKt.setPaneTitle(semanticsPropertyReceiver, m3160getString2EP1pXo22222);
                SemanticsPropertiesKt.setTraversalIndex(semanticsPropertyReceiver, 0.0f);
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue4);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        boolean z5222222 = false;
        Modifier semantics$default222222 = SemanticsModifierKt.semantics$default(draggable$default222222, false, (Function1) rememberedValue4, 1, null);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1237936755, "CC(remember):ModalBottomSheet.kt#9igjgp");
        if (i16 <= 1048576) {
        }
        if ((i7 & 112) != 32) {
        }
        z5222222 = true;
        z4 = z5222222 | z6;
        rememberedValue5 = startRestartGroup.rememberedValue();
        if (!z4) {
        }
        rememberedValue5 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$6$1
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
                float calculatePredictiveBackScaleX;
                float calculatePredictiveBackScaleY;
                float offset = SheetState.this.getAnchoredDraggableState$material3_release().getOffset();
                float m4409getHeightimpl = Size.m4409getHeightimpl(graphicsLayerScope.getSize());
                if (Float.isNaN(offset) || Float.isNaN(m4409getHeightimpl) || m4409getHeightimpl == 0.0f) {
                    return;
                }
                float floatValue = animatable.getValue().floatValue();
                calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                graphicsLayerScope.setScaleX(calculatePredictiveBackScaleX);
                calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY);
                graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, (offset + m4409getHeightimpl) / m4409getHeightimpl));
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue5);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Modifier graphicsLayer222222 = GraphicsLayerModifierKt.graphicsLayer(semantics$default222222, (Function1) rememberedValue5);
        final SheetState sheetState4222222 = sheetState2;
        final Function0<Unit> function03222222 = function02;
        final Function2<? super Composer, ? super Integer, Unit> function28222222 = m2126getLambda2$material3_release;
        Function2<? super Composer, ? super Integer, ? extends WindowInsets> function29222222 = function25;
        int i23222222 = i7 >> 21;
        int i24222222 = i15 << 9;
        long j9222222 = j4;
        composer2 = startRestartGroup;
        SurfaceKt.m2699SurfaceT9BRK9s(graphicsLayer222222, shape422222, j8222222, j9222222, f3, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-692668920, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7
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

            public final void invoke(Composer composer3, int i25) {
                String str;
                ComposerKt.sourceInformation(composer3, "C290@13592L21,290@13629L550,289@13528L2607:ModalBottomSheet.kt#uh7d8r");
                if ((i25 & 3) != 2 || !composer3.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-692668920, i25, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:289)");
                    }
                    Modifier windowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), function25.invoke(composer3, 0));
                    ComposerKt.sourceInformationMarkerStart(composer3, 320924084, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    boolean changedInstance = composer3.changedInstance(animatable);
                    final Animatable<Float, AnimationVector1D> animatable2 = animatable;
                    Object rememberedValue6 = composer3.rememberedValue();
                    if (changedInstance || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        rememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$1$1
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
                                float calculatePredictiveBackScaleX;
                                float calculatePredictiveBackScaleY;
                                long j10;
                                float floatValue = animatable2.getValue().floatValue();
                                calculatePredictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX(graphicsLayerScope, floatValue);
                                calculatePredictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY(graphicsLayerScope, floatValue);
                                graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY == 0.0f ? 1.0f : calculatePredictiveBackScaleX / calculatePredictiveBackScaleY);
                                j10 = ModalBottomSheetKt.PredictiveBackChildTransformOrigin;
                                graphicsLayerScope.mo4796setTransformOrigin__ExYCQ(j10);
                            }
                        };
                        composer3.updateRememberedValue(rememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    Modifier graphicsLayer2222222 = GraphicsLayerModifierKt.graphicsLayer(windowInsetsPadding, (Function1) rememberedValue6);
                    Function2<Composer, Integer, Unit> function210 = function28222222;
                    final SheetState sheetState5 = sheetState4222222;
                    final Function0<Unit> function04 = function03222222;
                    final CoroutineScope coroutineScope2 = coroutineScope;
                    Function3<ColumnScope, Composer, Integer, Unit> function32 = function3;
                    ComposerKt.sourceInformationMarkerStart(composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer3, graphicsLayer2222222);
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
                    ComposerKt.sourceInformationMarkerStart(composer3, 806123989, "C340@16116L9:ModalBottomSheet.kt#uh7d8r");
                    composer3.startReplaceGroup(-1636564008);
                    ComposerKt.sourceInformation(composer3, "303@14272L54,304@14368L48,305@14457L47,309@14672L1346,306@14521L1568");
                    if (function210 != null) {
                        Strings.Companion companion222222 = Strings.INSTANCE;
                        final String m3160getString2EP1pXo222222 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_collapse_description), composer3, 0);
                        Strings.Companion companion3 = Strings.INSTANCE;
                        final String m3160getString2EP1pXo3 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), composer3, 0);
                        Strings.Companion companion4 = Strings.INSTANCE;
                        final String m3160getString2EP1pXo4 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(R.string.m3c_bottom_sheet_expand_description), composer3, 0);
                        Modifier align = columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
                        ComposerKt.sourceInformationMarkerStart(composer3, -1636549585, "CC(remember):ModalBottomSheet.kt#9igjgp");
                        boolean changed2 = composer3.changed(sheetState5) | composer3.changed(m3160getString2EP1pXo3) | composer3.changed(function04) | composer3.changed(m3160getString2EP1pXo4) | composer3.changedInstance(coroutineScope2) | composer3.changed(m3160getString2EP1pXo222222);
                        Object rememberedValue7 = composer3.rememberedValue();
                        if (changed2 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                            str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                            rememberedValue7 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1
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
                                    final SheetState sheetState6 = SheetState.this;
                                    String str2 = m3160getString2EP1pXo3;
                                    String str3 = m3160getString2EP1pXo4;
                                    String str4 = m3160getString2EP1pXo222222;
                                    final Function0<Unit> function05 = function04;
                                    final CoroutineScope coroutineScope3 = coroutineScope2;
                                    SemanticsPropertiesKt.dismiss(semanticsPropertyReceiver, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Boolean invoke() {
                                            function05.invoke();
                                            return true;
                                        }
                                    });
                                    if (sheetState6.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                        SemanticsPropertiesKt.expand(semanticsPropertyReceiver, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            /* compiled from: ModalBottomSheet.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1", f = "ModalBottomSheet.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
                                            /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$2$1, reason: invalid class name */
                                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ SheetState $sheetState;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                    super(2, continuation);
                                                    this.$sheetState = sheetState;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new AnonymousClass1(this.$sheetState, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object obj) {
                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    int i = this.label;
                                                    if (i == 0) {
                                                        ResultKt.throwOnFailure(obj);
                                                        this.label = 1;
                                                        if (this.$sheetState.expand(this) == coroutine_suspended) {
                                                            return coroutine_suspended;
                                                        }
                                                    } else {
                                                        if (i != 1) {
                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                        }
                                                        ResultKt.throwOnFailure(obj);
                                                    }
                                                    return Unit.INSTANCE;
                                                }
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Boolean invoke() {
                                                if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(sheetState6, null), 3, null);
                                                }
                                                return true;
                                            }
                                        });
                                    } else if (sheetState6.getHasPartiallyExpandedState()) {
                                        SemanticsPropertiesKt.collapse(semanticsPropertyReceiver, str4, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Boolean invoke() {
                                                if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(SheetState.this, null), 3, null);
                                                }
                                                return true;
                                            }

                                            /* compiled from: ModalBottomSheet.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                            @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1", f = "ModalBottomSheet.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {})
                                            /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$2$1$1$1$3$1, reason: invalid class name */
                                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ SheetState $this_with;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                    super(2, continuation);
                                                    this.$this_with = sheetState;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new AnonymousClass1(this.$this_with, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object obj) {
                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    int i = this.label;
                                                    if (i == 0) {
                                                        ResultKt.throwOnFailure(obj);
                                                        this.label = 1;
                                                        if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                            return coroutine_suspended;
                                                        }
                                                    } else {
                                                        if (i != 1) {
                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                        }
                                                        ResultKt.throwOnFailure(obj);
                                                    }
                                                    return Unit.INSTANCE;
                                                }
                                            }
                                        });
                                    }
                                }
                            };
                            composer3.updateRememberedValue(rememberedValue7);
                        } else {
                            str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        Modifier semantics = SemanticsModifierKt.semantics(align, true, (Function1) rememberedValue7);
                        ComposerKt.sourceInformationMarkerStart(composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, str);
                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer3, semantics);
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
                        Updater.m3912setimpl(m3905constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3912setimpl(m3905constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (m3905constructorimpl2.getInserting() || !Intrinsics.areEqual(m3905constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                            m3905constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                            m3905constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                        }
                        Updater.m3912setimpl(m3905constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer3, -760467413, "C337@16059L12:ModalBottomSheet.kt#uh7d8r");
                        function210.invoke(composer3, 0);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                    }
                    composer3.endReplaceGroup();
                    function32.invoke(columnScopeInstance, composer3, 6);
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
        }, startRestartGroup, 54), composer2, (i23222222 & 896) | (i23222222 & 112) | 12582912 | (i24222222 & 7168) | (i24222222 & 57344), 96);
        if (ComposerKt.isTraceInProgress()) {
        }
        sheetState3 = sheetState4222222;
        function26 = function28222222;
        function27 = function29222222;
        shape2 = shape422222;
        f6 = f3;
        modifier4 = modifier522222;
        f7 = f5;
        j6 = j9222222;
        j7 = j8222222;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleX(GraphicsLayerScope graphicsLayerScope, float f) {
        float m4412getWidthimpl = Size.m4412getWidthimpl(graphicsLayerScope.getSize());
        if (Float.isNaN(m4412getWidthimpl) || m4412getWidthimpl == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (MathHelpersKt.lerp(0.0f, Math.min(graphicsLayerScope.mo459toPx0680j_4(PredictiveBackMaxScaleXDistance), m4412getWidthimpl), f) / m4412getWidthimpl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleY(GraphicsLayerScope graphicsLayerScope, float f) {
        float m4409getHeightimpl = Size.m4409getHeightimpl(graphicsLayerScope.getSize());
        if (Float.isNaN(m4409getHeightimpl) || m4409getHeightimpl == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (MathHelpersKt.lerp(0.0f, Math.min(graphicsLayerScope.mo459toPx0680j_4(PredictiveBackMaxScaleYDistance), m4409getHeightimpl), f) / m4409getHeightimpl);
    }

    public static final SheetState rememberModalBottomSheetState(boolean z, Function1<? super SheetValue, Boolean> function1, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -778250030, "C(rememberModalBottomSheetState)P(1)400@18058L160:ModalBottomSheet.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i2 & 2) != 0) {
            function1 = new Function1<SheetValue, Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$rememberModalBottomSheetState$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(SheetValue sheetValue) {
                    return true;
                }
            };
        }
        Function1<? super SheetValue, Boolean> function12 = function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-778250030, i, -1, "androidx.compose.material3.rememberModalBottomSheetState (ModalBottomSheet.kt:400)");
        }
        SheetState rememberSheetState = SheetDefaultsKt.rememberSheetState(z2, function12, SheetValue.Hidden, false, composer, (i & 14) | 384 | (i & 112), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return rememberSheetState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Scrim-3J-VO9M, reason: not valid java name */
    public static final void m2400Scrim3JVO9M(final long j, final Function0<Unit> function0, final boolean z, Composer composer, final int i) {
        int i2;
        Modifier.Companion companion;
        Modifier then;
        Composer startRestartGroup = composer.startRestartGroup(951870469);
        ComposerKt.sourceInformation(startRestartGroup, "C(Scrim)P(0:c#ui.graphics.Color)410@18376L87,411@18489L29,426@19112L79,426@19062L129:ModalBottomSheet.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i2 & 147) != 146 || !startRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(951870469, i2, -1, "androidx.compose.material3.Scrim (ModalBottomSheet.kt:407)");
            }
            if (j != 16) {
                int i3 = i2;
                final State<Float> animateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 1.0f : 0.0f, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, startRestartGroup, 48, 28);
                Strings.Companion companion2 = Strings.INSTANCE;
                final String m3160getString2EP1pXo = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.close_sheet), startRestartGroup, 0);
                startRestartGroup.startReplaceGroup(-1785653838);
                ComposerKt.sourceInformation(startRestartGroup, "414@18629L44,415@18730L263");
                if (z) {
                    Modifier.Companion companion3 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1785652017, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    int i4 = i3 & 112;
                    boolean z2 = i4 == 32;
                    ModalBottomSheetKt$Scrim$dismissSheet$1$1 rememberedValue = startRestartGroup.rememberedValue();
                    if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                        rememberedValue = new ModalBottomSheetKt$Scrim$dismissSheet$1$1(function0, null);
                        startRestartGroup.updateRememberedValue(rememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    then = companion3.then(new SuspendPointerInputElement(function0, null, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0((Function2) rememberedValue), 6, null));
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1785648566, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    boolean changed = (i4 == 32) | startRestartGroup.changed(m3160getString2EP1pXo);
                    Object rememberedValue2 = startRestartGroup.rememberedValue();
                    if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        rememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$dismissSheet$2$1
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
                                SemanticsPropertiesKt.setTraversalIndex(semanticsPropertyReceiver, 1.0f);
                                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, m3160getString2EP1pXo);
                                final Function0<Unit> function02 = function0;
                                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$dismissSheet$2$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        function02.invoke();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        startRestartGroup.updateRememberedValue(rememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    companion = SemanticsModifierKt.semantics(then, true, (Function1) rememberedValue2);
                } else {
                    companion = Modifier.INSTANCE;
                }
                startRestartGroup.endReplaceGroup();
                Modifier then2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(companion);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1785636526, "CC(remember):ModalBottomSheet.kt#9igjgp");
                boolean changed2 = startRestartGroup.changed(animateFloatAsState) | ((i3 & 14) == 4);
                Object rememberedValue3 = startRestartGroup.rememberedValue();
                if (changed2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$1$1
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
                            float Scrim_3J_VO9M$lambda$10;
                            long j2 = j;
                            Scrim_3J_VO9M$lambda$10 = ModalBottomSheetKt.Scrim_3J_VO9M$lambda$10(animateFloatAsState);
                            DrawScope.CC.m5206drawRectnJ9OG0$default(drawScope, j2, 0L, 0L, RangesKt.coerceIn(Scrim_3J_VO9M$lambda$10, 0.0f, 1.0f), null, null, 0, 118, null);
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                CanvasKt.Canvas(then2, (Function1) rememberedValue3, startRestartGroup, 0);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i5) {
                    ModalBottomSheetKt.m2400Scrim3JVO9M(j, function0, z, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Scrim_3J_VO9M$lambda$10(State<Float> state) {
        return state.getValue().floatValue();
    }
}
