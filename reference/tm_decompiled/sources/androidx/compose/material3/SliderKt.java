package androidx.compose.material3;

import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.AccessibilityUtilKt;
import androidx.compose.material3.internal.DragGestureDetectorCopyKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.SliderTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.SuspendPointerInputElement;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0;
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
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: Slider.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0098\u0001\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\u0019\b\u0002\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010%\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$H\u0007¢\u0006\u0002\u0010'\u001aä\u0001\u0010\u0016\u001a\u00020\u00172\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0018\u0010+\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)\u0012\u0004\u0012\u00020\u00170#2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020*0)2\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010.2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\u0019\b\u0002\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010%\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\b\b\u0003\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u00101\u001a\u007f\u0010\u0016\u001a\u00020\u00172\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0018\u0010+\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)\u0012\u0004\u0012\u00020\u00170#2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020*0)2\b\b\u0003\u0010/\u001a\u0002002\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010.2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0007¢\u0006\u0002\u00102\u001a\u0080\u0001\u00103\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0017\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0017\u0010%\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$H\u0003¢\u0006\u0002\u00104\u001as\u00105\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u0002062\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u00107\u001a\u00020 2\u0019\b\u0002\u00108\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$H\u0007¢\u0006\u0002\u00109\u001a³\u0001\u00105\u001a\u00020\u00172\u0006\u0010(\u001a\u00020*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00170#2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010.2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u00107\u001a\u00020 2\b\b\u0003\u0010/\u001a\u0002002\u0019\b\u0002\u00108\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020*0)H\u0007¢\u0006\u0002\u0010:\u001a}\u00105\u001a\u00020\u00172\u0006\u0010(\u001a\u00020*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00170#2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020*0)2\b\b\u0003\u0010/\u001a\u0002002\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010.2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u00107\u001a\u00020 H\u0007¢\u0006\u0002\u0010;\u001a_\u0010<\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u0002062\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u00107\u001a\u00020 2\u0017\u00108\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$H\u0003¢\u0006\u0002\u0010=\u001a\u001d\u0010>\u001a\u00020\u00112\u0006\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020*H\u0001¢\u0006\u0002\u0010A\u001a\u001b\u0010>\u001a\u00020\u00112\f\u0010B\u001a\b\u0012\u0004\u0012\u00020*0)H\u0001¢\u0006\u0002\u0010C\u001a \u0010D\u001a\u00020*2\u0006\u0010E\u001a\u00020*2\u0006\u0010F\u001a\u00020*2\u0006\u0010G\u001a\u00020*H\u0002\u001a:\u0010H\u001a\u00020\u00112\u0006\u0010I\u001a\u00020*2\u0006\u0010J\u001a\u00020*2\u0006\u0010K\u001a\u00020\u00112\u0006\u0010L\u001a\u00020*2\u0006\u0010M\u001a\u00020*H\u0002ø\u0001\u0000¢\u0006\u0004\bN\u0010O\u001a0\u0010H\u001a\u00020*2\u0006\u0010I\u001a\u00020*2\u0006\u0010J\u001a\u00020*2\u0006\u0010P\u001a\u00020*2\u0006\u0010L\u001a\u00020*2\u0006\u0010M\u001a\u00020*H\u0002\u001a(\u0010Q\u001a\u00020*2\u0006\u0010R\u001a\u00020*2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020*2\u0006\u0010V\u001a\u00020*H\u0002\u001a\u0010\u0010W\u001a\u00020T2\u0006\u0010/\u001a\u000200H\u0002\u001a5\u0010X\u001a\u0010\u0012\u0004\u0012\u00020Z\u0012\u0004\u0012\u00020*\u0018\u00010Y*\u00020[2\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020_H\u0082@ø\u0001\u0000¢\u0006\u0004\b`\u0010a\u001a\u001c\u0010b\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0010H\u0002\u001a,\u0010c\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u0010H\u0003\u001a\u001c\u0010d\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0010H\u0002\u001a\u001c\u0010e\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u0002062\u0006\u0010\u001c\u001a\u00020\u0010H\u0002\u001a$\u0010f\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u0002062\u0006\u00107\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u0010H\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u0010\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\t\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\n\u0010\u000b\"\u0016\u0010\f\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\r\u0010\u000b\"\u0010\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u001e\u0010\u000f\u001a\u00020\u0010*\u00020\u00118@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006g"}, d2 = {"SliderRangeTolerance", "", "ThumbHeight", "Landroidx/compose/ui/unit/Dp;", "F", "ThumbSize", "Landroidx/compose/ui/unit/DpSize;", "J", "ThumbTrackGapSize", "ThumbWidth", "getThumbWidth", "()F", "TrackHeight", "getTrackHeight", "TrackInsideCornerSize", "isSpecified", "", "Landroidx/compose/material3/SliderRange;", "isSpecified-If1S1O4$annotations", "(J)V", "isSpecified-If1S1O4", "(J)Z", "RangeSlider", "", "state", "Landroidx/compose/material3/RangeSliderState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "colors", "Landroidx/compose/material3/SliderColors;", "startInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "endInteractionSource", "startThumb", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composable;", "endThumb", "track", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "value", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "onValueChange", "valueRange", "onValueChangeFinished", "Lkotlin/Function0;", "steps", "", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;ILandroidx/compose/runtime/Composer;III)V", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/runtime/Composer;II)V", "RangeSliderImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/RangeSliderState;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "Slider", "Landroidx/compose/material3/SliderState;", "interactionSource", "thumb", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;ILkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/ranges/ClosedFloatingPointRange;Landroidx/compose/runtime/Composer;III)V", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SliderImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderState;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "SliderRange", "start", "endInclusive", "(FF)J", "range", "(Lkotlin/ranges/ClosedFloatingPointRange;)J", "calcFraction", "a", "b", "pos", "scale", "a1", "b1", "x", "a2", "b2", "scale-ziovWd0", "(FFJFF)J", "x1", "snapValueToTick", "current", "tickFractions", "", "minPx", "maxPx", "stepsToTickFractions", "awaitSlop", "Lkotlin/Pair;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "id", "Landroidx/compose/ui/input/pointer/PointerId;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/input/pointer/PointerType;", "awaitSlop-8vUncbI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rangeSliderEndThumbSemantics", "rangeSliderPressDragModifier", "rangeSliderStartThumbSemantics", "sliderSemantics", "sliderTapModifier", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SliderKt {
    private static final double SliderRangeTolerance = 1.0E-4d;
    private static final float ThumbHeight;
    private static final long ThumbSize;
    private static final float ThumbTrackGapSize;
    private static final float ThumbWidth;
    private static final float TrackHeight = SliderTokens.INSTANCE.m3682getInactiveTrackHeightD9Ej5fM();
    private static final float TrackInsideCornerSize;

    /* renamed from: isSpecified-If1S1O4$annotations, reason: not valid java name */
    public static /* synthetic */ void m2662isSpecifiedIf1S1O4$annotations(long j) {
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void Slider(final float f, final Function1<? super Float, Unit> function1, Modifier modifier, boolean z, ClosedFloatingPointRange<Float> closedFloatingPointRange, int i, Function0<Unit> function0, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i2, final int i3) {
        float f2;
        int i4;
        Function1<? super Float, Unit> function12;
        Modifier modifier2;
        int i5;
        boolean z2;
        ClosedFloatingPointRange<Float> closedFloatingPointRange2;
        int i6;
        int i7;
        int i8;
        Function0<Unit> function02;
        int i9;
        int i10;
        int i11;
        final SliderColors sliderColors2;
        final MutableInteractionSource mutableInteractionSource2;
        Modifier modifier3;
        ClosedFloatingPointRange<Float> closedFloatingPointRange3;
        int i12;
        Function0<Unit> function03;
        int i13;
        Composer composer2;
        final Modifier modifier4;
        final boolean z3;
        final Function0<Unit> function04;
        final SliderColors sliderColors3;
        final MutableInteractionSource mutableInteractionSource3;
        final int i14;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange4;
        ScopeUpdateScope endRestartGroup;
        int i15;
        int i16;
        Composer startRestartGroup = composer.startRestartGroup(-202044027);
        ComposerKt.sourceInformation(startRestartGroup, "C(Slider)P(7,4,3,1,8,6,5)167@8058L8,168@8118L39,179@8458L181,186@8657L122,170@8166L652:Slider.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            f2 = f;
        } else {
            f2 = f;
            if ((i2 & 6) == 0) {
                i4 = (startRestartGroup.changed(f2) ? 4 : 2) | i2;
            } else {
                i4 = i2;
            }
        }
        if ((i3 & 2) != 0) {
            i4 |= 48;
            function12 = function1;
        } else {
            function12 = function1;
            if ((i2 & 48) == 0) {
                i4 |= startRestartGroup.changedInstance(function12) ? 32 : 16;
            }
        }
        int i17 = i3 & 4;
        if (i17 != 0) {
            i4 |= 384;
        } else if ((i2 & 384) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 256 : 128;
            i5 = i3 & 8;
            if (i5 == 0) {
                i4 |= 3072;
            } else if ((i2 & 3072) == 0) {
                z2 = z;
                i4 |= startRestartGroup.changed(z2) ? 2048 : 1024;
                if ((i2 & 24576) == 0) {
                    if ((i3 & 16) == 0) {
                        closedFloatingPointRange2 = closedFloatingPointRange;
                        if (startRestartGroup.changed(closedFloatingPointRange2)) {
                            i16 = 16384;
                            i4 |= i16;
                        }
                    } else {
                        closedFloatingPointRange2 = closedFloatingPointRange;
                    }
                    i16 = 8192;
                    i4 |= i16;
                } else {
                    closedFloatingPointRange2 = closedFloatingPointRange;
                }
                i6 = i3 & 32;
                if (i6 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i2) == 0) {
                    i7 = i;
                    i4 |= startRestartGroup.changed(i7) ? 131072 : 65536;
                    i8 = i3 & 64;
                    if (i8 == 0) {
                        i4 |= 1572864;
                    } else if ((1572864 & i2) == 0) {
                        function02 = function0;
                        i4 |= startRestartGroup.changedInstance(function02) ? 1048576 : 524288;
                        if ((i2 & 12582912) == 0) {
                            if ((i3 & 128) == 0 && startRestartGroup.changed(sliderColors)) {
                                i15 = 8388608;
                                i4 |= i15;
                            }
                            i15 = 4194304;
                            i4 |= i15;
                        }
                        i9 = i3 & 256;
                        if (i9 != 0) {
                            i4 |= 100663296;
                        } else if ((i2 & 100663296) == 0) {
                            i10 = i9;
                            i4 |= startRestartGroup.changed(mutableInteractionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                            int i18 = i4;
                            if ((i4 & 38347923) == 38347922 || !startRestartGroup.getSkipping()) {
                                startRestartGroup.startDefaults();
                                if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                    if (i17 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i5 != 0) {
                                        z2 = true;
                                    }
                                    if ((i3 & 16) == 0) {
                                        i11 = i18 & (-57345);
                                        closedFloatingPointRange2 = RangesKt.rangeTo(0.0f, 1.0f);
                                    } else {
                                        i11 = i18;
                                    }
                                    if (i6 != 0) {
                                        i7 = 0;
                                    }
                                    if (i8 != 0) {
                                        function02 = null;
                                    }
                                    if ((i3 & 128) == 0) {
                                        sliderColors2 = SliderDefaults.INSTANCE.colors(startRestartGroup, 6);
                                        i11 &= -29360129;
                                    } else {
                                        sliderColors2 = sliderColors;
                                    }
                                    if (i10 == 0) {
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -403669397, "CC(remember):Slider.kt#9igjgp");
                                        Object rememberedValue = startRestartGroup.rememberedValue();
                                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue = InteractionSourceKt.MutableInteractionSource();
                                            startRestartGroup.updateRememberedValue(rememberedValue);
                                        }
                                        mutableInteractionSource2 = (MutableInteractionSource) rememberedValue;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    } else {
                                        mutableInteractionSource2 = mutableInteractionSource;
                                    }
                                    modifier3 = modifier2;
                                    closedFloatingPointRange3 = closedFloatingPointRange2;
                                    i12 = i7;
                                    function03 = function02;
                                    i13 = -202044027;
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    int i19 = (i3 & 16) != 0 ? i18 & (-57345) : i18;
                                    if ((i3 & 128) != 0) {
                                        i19 &= -29360129;
                                    }
                                    mutableInteractionSource2 = mutableInteractionSource;
                                    i11 = i19;
                                    modifier3 = modifier2;
                                    closedFloatingPointRange3 = closedFloatingPointRange2;
                                    i12 = i7;
                                    function03 = function02;
                                    i13 = -202044027;
                                    sliderColors2 = sliderColors;
                                }
                                final boolean z4 = z2;
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i13, i11, -1, "androidx.compose.material3.Slider (Slider.kt:169)");
                                }
                                int i20 = i11 >> 6;
                                SliderColors sliderColors4 = sliderColors2;
                                composer2 = startRestartGroup;
                                MutableInteractionSource mutableInteractionSource4 = mutableInteractionSource2;
                                Slider(f2, function12, modifier3, z4, function03, sliderColors4, mutableInteractionSource4, i12, ComposableLambdaKt.rememberComposableLambda(308249025, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                                        invoke(sliderState, composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(SliderState sliderState, Composer composer3, int i21) {
                                        ComposerKt.sourceInformation(composer3, "C180@8487L142:Slider.kt#uh7d8r");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(308249025, i21, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:180)");
                                        }
                                        SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z4, 0L, composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1843234110, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                                        invoke(sliderState, composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(SliderState sliderState, Composer composer3, int i21) {
                                        ComposerKt.sourceInformation(composer3, "C187@8701L68:Slider.kt#uh7d8r");
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1843234110, i21, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:187)");
                                        }
                                        SliderDefaults.INSTANCE.m2650Track4EFweAY(sliderState, (Modifier) null, z4, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i21 & 14) | 100663296, 242);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, startRestartGroup, 54), closedFloatingPointRange3, composer2, (i11 & 14) | 905969664 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | (57344 & i20) | (458752 & i20) | (i20 & 3670016) | ((i11 << 6) & 29360128), (i11 >> 12) & 14, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier4 = modifier3;
                                z3 = z4;
                                function04 = function03;
                                sliderColors3 = sliderColors4;
                                mutableInteractionSource3 = mutableInteractionSource4;
                                i14 = i12;
                                closedFloatingPointRange4 = closedFloatingPointRange3;
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                sliderColors3 = sliderColors;
                                composer2 = startRestartGroup;
                                modifier4 = modifier2;
                                z3 = z2;
                                closedFloatingPointRange4 = closedFloatingPointRange2;
                                i14 = i7;
                                function04 = function02;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$4
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

                                    public final void invoke(Composer composer3, int i21) {
                                        SliderKt.Slider(f, function1, modifier4, z3, closedFloatingPointRange4, i14, function04, sliderColors3, mutableInteractionSource3, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        i10 = i9;
                        int i182 = i4;
                        if ((i4 & 38347923) == 38347922) {
                        }
                        startRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                        }
                        if (i17 != 0) {
                        }
                        if (i5 != 0) {
                        }
                        if ((i3 & 16) == 0) {
                        }
                        if (i6 != 0) {
                        }
                        if (i8 != 0) {
                        }
                        if ((i3 & 128) == 0) {
                        }
                        if (i10 == 0) {
                        }
                        modifier3 = modifier2;
                        closedFloatingPointRange3 = closedFloatingPointRange2;
                        i12 = i7;
                        function03 = function02;
                        i13 = -202044027;
                        final boolean z42 = z2;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        int i202 = i11 >> 6;
                        SliderColors sliderColors42 = sliderColors2;
                        composer2 = startRestartGroup;
                        MutableInteractionSource mutableInteractionSource42 = mutableInteractionSource2;
                        Slider(f2, function12, modifier3, z42, function03, sliderColors42, mutableInteractionSource42, i12, ComposableLambdaKt.rememberComposableLambda(308249025, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                                invoke(sliderState, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(SliderState sliderState, Composer composer3, int i21) {
                                ComposerKt.sourceInformation(composer3, "C180@8487L142:Slider.kt#uh7d8r");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(308249025, i21, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:180)");
                                }
                                SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z42, 0L, composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1843234110, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                                invoke(sliderState, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(SliderState sliderState, Composer composer3, int i21) {
                                ComposerKt.sourceInformation(composer3, "C187@8701L68:Slider.kt#uh7d8r");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1843234110, i21, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:187)");
                                }
                                SliderDefaults.INSTANCE.m2650Track4EFweAY(sliderState, (Modifier) null, z42, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i21 & 14) | 100663296, 242);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, startRestartGroup, 54), closedFloatingPointRange3, composer2, (i11 & 14) | 905969664 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | (57344 & i202) | (458752 & i202) | (i202 & 3670016) | ((i11 << 6) & 29360128), (i11 >> 12) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        modifier4 = modifier3;
                        z3 = z42;
                        function04 = function03;
                        sliderColors3 = sliderColors42;
                        mutableInteractionSource3 = mutableInteractionSource42;
                        i14 = i12;
                        closedFloatingPointRange4 = closedFloatingPointRange3;
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    function02 = function0;
                    if ((i2 & 12582912) == 0) {
                    }
                    i9 = i3 & 256;
                    if (i9 != 0) {
                    }
                    i10 = i9;
                    int i1822 = i4;
                    if ((i4 & 38347923) == 38347922) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                    }
                    if (i17 != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if ((i3 & 16) == 0) {
                    }
                    if (i6 != 0) {
                    }
                    if (i8 != 0) {
                    }
                    if ((i3 & 128) == 0) {
                    }
                    if (i10 == 0) {
                    }
                    modifier3 = modifier2;
                    closedFloatingPointRange3 = closedFloatingPointRange2;
                    i12 = i7;
                    function03 = function02;
                    i13 = -202044027;
                    final boolean z422 = z2;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    int i2022 = i11 >> 6;
                    SliderColors sliderColors422 = sliderColors2;
                    composer2 = startRestartGroup;
                    MutableInteractionSource mutableInteractionSource422 = mutableInteractionSource2;
                    Slider(f2, function12, modifier3, z422, function03, sliderColors422, mutableInteractionSource422, i12, ComposableLambdaKt.rememberComposableLambda(308249025, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                            invoke(sliderState, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(SliderState sliderState, Composer composer3, int i21) {
                            ComposerKt.sourceInformation(composer3, "C180@8487L142:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(308249025, i21, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:180)");
                            }
                            SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z422, 0L, composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1843234110, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                            invoke(sliderState, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(SliderState sliderState, Composer composer3, int i21) {
                            ComposerKt.sourceInformation(composer3, "C187@8701L68:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1843234110, i21, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:187)");
                            }
                            SliderDefaults.INSTANCE.m2650Track4EFweAY(sliderState, (Modifier) null, z422, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i21 & 14) | 100663296, 242);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, startRestartGroup, 54), closedFloatingPointRange3, composer2, (i11 & 14) | 905969664 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | (57344 & i2022) | (458752 & i2022) | (i2022 & 3670016) | ((i11 << 6) & 29360128), (i11 >> 12) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    modifier4 = modifier3;
                    z3 = z422;
                    function04 = function03;
                    sliderColors3 = sliderColors422;
                    mutableInteractionSource3 = mutableInteractionSource422;
                    i14 = i12;
                    closedFloatingPointRange4 = closedFloatingPointRange3;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                i7 = i;
                i8 = i3 & 64;
                if (i8 == 0) {
                }
                function02 = function0;
                if ((i2 & 12582912) == 0) {
                }
                i9 = i3 & 256;
                if (i9 != 0) {
                }
                i10 = i9;
                int i18222 = i4;
                if ((i4 & 38347923) == 38347922) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i17 != 0) {
                }
                if (i5 != 0) {
                }
                if ((i3 & 16) == 0) {
                }
                if (i6 != 0) {
                }
                if (i8 != 0) {
                }
                if ((i3 & 128) == 0) {
                }
                if (i10 == 0) {
                }
                modifier3 = modifier2;
                closedFloatingPointRange3 = closedFloatingPointRange2;
                i12 = i7;
                function03 = function02;
                i13 = -202044027;
                final boolean z4222 = z2;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                int i20222 = i11 >> 6;
                SliderColors sliderColors4222 = sliderColors2;
                composer2 = startRestartGroup;
                MutableInteractionSource mutableInteractionSource4222 = mutableInteractionSource2;
                Slider(f2, function12, modifier3, z4222, function03, sliderColors4222, mutableInteractionSource4222, i12, ComposableLambdaKt.rememberComposableLambda(308249025, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                        invoke(sliderState, composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(SliderState sliderState, Composer composer3, int i21) {
                        ComposerKt.sourceInformation(composer3, "C180@8487L142:Slider.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(308249025, i21, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:180)");
                        }
                        SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z4222, 0L, composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1843234110, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                        invoke(sliderState, composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(SliderState sliderState, Composer composer3, int i21) {
                        ComposerKt.sourceInformation(composer3, "C187@8701L68:Slider.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1843234110, i21, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:187)");
                        }
                        SliderDefaults.INSTANCE.m2650Track4EFweAY(sliderState, (Modifier) null, z4222, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i21 & 14) | 100663296, 242);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, startRestartGroup, 54), closedFloatingPointRange3, composer2, (i11 & 14) | 905969664 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | (57344 & i20222) | (458752 & i20222) | (i20222 & 3670016) | ((i11 << 6) & 29360128), (i11 >> 12) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                }
                modifier4 = modifier3;
                z3 = z4222;
                function04 = function03;
                sliderColors3 = sliderColors4222;
                mutableInteractionSource3 = mutableInteractionSource4222;
                i14 = i12;
                closedFloatingPointRange4 = closedFloatingPointRange3;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z2 = z;
            if ((i2 & 24576) == 0) {
            }
            i6 = i3 & 32;
            if (i6 != 0) {
            }
            i7 = i;
            i8 = i3 & 64;
            if (i8 == 0) {
            }
            function02 = function0;
            if ((i2 & 12582912) == 0) {
            }
            i9 = i3 & 256;
            if (i9 != 0) {
            }
            i10 = i9;
            int i182222 = i4;
            if ((i4 & 38347923) == 38347922) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i17 != 0) {
            }
            if (i5 != 0) {
            }
            if ((i3 & 16) == 0) {
            }
            if (i6 != 0) {
            }
            if (i8 != 0) {
            }
            if ((i3 & 128) == 0) {
            }
            if (i10 == 0) {
            }
            modifier3 = modifier2;
            closedFloatingPointRange3 = closedFloatingPointRange2;
            i12 = i7;
            function03 = function02;
            i13 = -202044027;
            final boolean z42222 = z2;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            int i202222 = i11 >> 6;
            SliderColors sliderColors42222 = sliderColors2;
            composer2 = startRestartGroup;
            MutableInteractionSource mutableInteractionSource42222 = mutableInteractionSource2;
            Slider(f2, function12, modifier3, z42222, function03, sliderColors42222, mutableInteractionSource42222, i12, ComposableLambdaKt.rememberComposableLambda(308249025, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                    invoke(sliderState, composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(SliderState sliderState, Composer composer3, int i21) {
                    ComposerKt.sourceInformation(composer3, "C180@8487L142:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(308249025, i21, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:180)");
                    }
                    SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z42222, 0L, composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1843234110, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                    invoke(sliderState, composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(SliderState sliderState, Composer composer3, int i21) {
                    ComposerKt.sourceInformation(composer3, "C187@8701L68:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1843234110, i21, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:187)");
                    }
                    SliderDefaults.INSTANCE.m2650Track4EFweAY(sliderState, (Modifier) null, z42222, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i21 & 14) | 100663296, 242);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, startRestartGroup, 54), closedFloatingPointRange3, composer2, (i11 & 14) | 905969664 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | (57344 & i202222) | (458752 & i202222) | (i202222 & 3670016) | ((i11 << 6) & 29360128), (i11 >> 12) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier4 = modifier3;
            z3 = z42222;
            function04 = function03;
            sliderColors3 = sliderColors42222;
            mutableInteractionSource3 = mutableInteractionSource42222;
            i14 = i12;
            closedFloatingPointRange4 = closedFloatingPointRange3;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i5 = i3 & 8;
        if (i5 == 0) {
        }
        z2 = z;
        if ((i2 & 24576) == 0) {
        }
        i6 = i3 & 32;
        if (i6 != 0) {
        }
        i7 = i;
        i8 = i3 & 64;
        if (i8 == 0) {
        }
        function02 = function0;
        if ((i2 & 12582912) == 0) {
        }
        i9 = i3 & 256;
        if (i9 != 0) {
        }
        i10 = i9;
        int i1822222 = i4;
        if ((i4 & 38347923) == 38347922) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i17 != 0) {
        }
        if (i5 != 0) {
        }
        if ((i3 & 16) == 0) {
        }
        if (i6 != 0) {
        }
        if (i8 != 0) {
        }
        if ((i3 & 128) == 0) {
        }
        if (i10 == 0) {
        }
        modifier3 = modifier2;
        closedFloatingPointRange3 = closedFloatingPointRange2;
        i12 = i7;
        function03 = function02;
        i13 = -202044027;
        final boolean z422222 = z2;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        int i2022222 = i11 >> 6;
        SliderColors sliderColors422222 = sliderColors2;
        composer2 = startRestartGroup;
        MutableInteractionSource mutableInteractionSource422222 = mutableInteractionSource2;
        Slider(f2, function12, modifier3, z422222, function03, sliderColors422222, mutableInteractionSource422222, i12, ComposableLambdaKt.rememberComposableLambda(308249025, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                invoke(sliderState, composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(SliderState sliderState, Composer composer3, int i21) {
                ComposerKt.sourceInformation(composer3, "C180@8487L142:Slider.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(308249025, i21, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:180)");
                }
                SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z422222, 0L, composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1843234110, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                invoke(sliderState, composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(SliderState sliderState, Composer composer3, int i21) {
                ComposerKt.sourceInformation(composer3, "C187@8701L68:Slider.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1843234110, i21, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:187)");
                }
                SliderDefaults.INSTANCE.m2650Track4EFweAY(sliderState, (Modifier) null, z422222, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i21 & 14) | 100663296, 242);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }, startRestartGroup, 54), closedFloatingPointRange3, composer2, (i11 & 14) | 905969664 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | (57344 & i2022222) | (458752 & i2022222) | (i2022222 & 3670016) | ((i11 << 6) & 29360128), (i11 >> 12) & 14, 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier4 = modifier3;
        z3 = z422222;
        function04 = function03;
        sliderColors3 = sliderColors422222;
        mutableInteractionSource3 = mutableInteractionSource422222;
        i14 = i12;
        closedFloatingPointRange4 = closedFloatingPointRange3;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void Slider(final float f, final Function1<? super Float, Unit> function1, Modifier modifier, boolean z, Function0<Unit> function0, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, int i, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function32, ClosedFloatingPointRange<Float> closedFloatingPointRange, Composer composer, final int i2, final int i3, final int i4) {
        int i5;
        Modifier modifier2;
        int i6;
        final boolean z2;
        int i7;
        Function0<Unit> function02;
        final SliderColors sliderColors2;
        int i8;
        final MutableInteractionSource mutableInteractionSource2;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function33;
        ComposableLambda composableLambda;
        ClosedFloatingPointRange<Float> closedFloatingPointRange2;
        int i17;
        int i18;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function34;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function35;
        MutableInteractionSource mutableInteractionSource3;
        boolean z3;
        Object rememberedValue;
        Composer composer2;
        final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function36;
        final boolean z4;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange3;
        final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function37;
        final MutableInteractionSource mutableInteractionSource4;
        final SliderColors sliderColors3;
        final Modifier modifier3;
        ScopeUpdateScope endRestartGroup;
        int i19;
        int i20;
        Composer startRestartGroup = composer.startRestartGroup(1191170377);
        ComposerKt.sourceInformation(startRestartGroup, "C(Slider)P(9,4,3,1,5)256@12184L8,257@12244L39,259@12372L157,266@12578L114,272@12779L92,278@12998L184:Slider.kt#uh7d8r");
        if ((i4 & 1) != 0) {
            i5 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i5 = (startRestartGroup.changed(f) ? 4 : 2) | i2;
        } else {
            i5 = i2;
        }
        if ((i4 & 2) != 0) {
            i5 |= 48;
        } else if ((i2 & 48) == 0) {
            i5 |= startRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i21 = i4 & 4;
        if (i21 != 0) {
            i5 |= 384;
        } else if ((i2 & 384) == 0) {
            modifier2 = modifier;
            i5 |= startRestartGroup.changed(modifier2) ? 256 : 128;
            i6 = i4 & 8;
            if (i6 == 0) {
                i5 |= 3072;
            } else if ((i2 & 3072) == 0) {
                z2 = z;
                i5 |= startRestartGroup.changed(z2) ? 2048 : 1024;
                i7 = i4 & 16;
                if (i7 != 0) {
                    i5 |= 24576;
                } else if ((i2 & 24576) == 0) {
                    function02 = function0;
                    i5 |= startRestartGroup.changedInstance(function02) ? 16384 : 8192;
                    if ((196608 & i2) != 0) {
                        if ((i4 & 32) == 0) {
                            sliderColors2 = sliderColors;
                            if (startRestartGroup.changed(sliderColors2)) {
                                i20 = 131072;
                                i5 |= i20;
                            }
                        } else {
                            sliderColors2 = sliderColors;
                        }
                        i20 = 65536;
                        i5 |= i20;
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i8 = i4 & 64;
                    if (i8 == 0) {
                        i5 |= 1572864;
                        mutableInteractionSource2 = mutableInteractionSource;
                    } else {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if ((i2 & 1572864) == 0) {
                            i5 |= startRestartGroup.changed(mutableInteractionSource2) ? 1048576 : 524288;
                        }
                    }
                    i9 = i4 & 128;
                    if (i9 == 0) {
                        i5 |= 12582912;
                        i10 = i;
                    } else {
                        i10 = i;
                        if ((i2 & 12582912) == 0) {
                            i5 |= startRestartGroup.changed(i10) ? 8388608 : 4194304;
                        }
                    }
                    i11 = i4 & 256;
                    if (i11 == 0) {
                        i5 |= 100663296;
                    } else if ((i2 & 100663296) == 0) {
                        i12 = i11;
                        i5 |= startRestartGroup.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        i13 = i4 & 512;
                        if (i13 != 0) {
                            i5 |= 805306368;
                        } else if ((i2 & 805306368) == 0) {
                            i14 = i13;
                            i5 |= startRestartGroup.changedInstance(function32) ? 536870912 : 268435456;
                            if ((i3 & 6) != 0) {
                                if ((i4 & 1024) == 0 && startRestartGroup.changed(closedFloatingPointRange)) {
                                    i19 = 4;
                                    i15 = i3 | i19;
                                }
                                i19 = 2;
                                i15 = i3 | i19;
                            } else {
                                i15 = i3;
                            }
                            int i22 = i5;
                            if ((i5 & 306783379) == 306783378 || (i15 & 3) != 2 || !startRestartGroup.getSkipping()) {
                                startRestartGroup.startDefaults();
                                if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                    if (i21 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i6 != 0) {
                                        z2 = true;
                                    }
                                    if (i7 != 0) {
                                        function02 = null;
                                    }
                                    if ((i4 & 32) == 0) {
                                        i16 = i22 & (-458753);
                                        sliderColors2 = SliderDefaults.INSTANCE.colors(startRestartGroup, 6);
                                    } else {
                                        i16 = i22;
                                    }
                                    if (i8 != 0) {
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -403537365, "CC(remember):Slider.kt#9igjgp");
                                        Object rememberedValue2 = startRestartGroup.rememberedValue();
                                        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue2 = InteractionSourceKt.MutableInteractionSource();
                                            startRestartGroup.updateRememberedValue(rememberedValue2);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        mutableInteractionSource2 = (MutableInteractionSource) rememberedValue2;
                                    }
                                    if (i9 != 0) {
                                        i10 = 0;
                                    }
                                    ComposableLambda rememberComposableLambda = i12 == 0 ? ComposableLambdaKt.rememberComposableLambda(-1756326375, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$6
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                                            invoke(sliderState, composer3, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(SliderState sliderState, Composer composer3, int i23) {
                                            ComposerKt.sourceInformation(composer3, "C260@12397L126:Slider.kt#uh7d8r");
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1756326375, i23, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:260)");
                                            }
                                            SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z2, 0L, composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    }, startRestartGroup, 54) : function3;
                                    if (i14 == 0) {
                                        function33 = rememberComposableLambda;
                                        composableLambda = ComposableLambdaKt.rememberComposableLambda(2083675534, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$7
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer3, Integer num) {
                                                invoke(sliderState, composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(SliderState sliderState, Composer composer3, int i23) {
                                                ComposerKt.sourceInformation(composer3, "C267@12618L68:Slider.kt#uh7d8r");
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(2083675534, i23, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:267)");
                                                }
                                                SliderDefaults.INSTANCE.m2650Track4EFweAY(sliderState, (Modifier) null, z2, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i23 & 14) | 100663296, 242);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }, startRestartGroup, 54);
                                    } else {
                                        function33 = rememberComposableLambda;
                                        composableLambda = function32;
                                    }
                                    if ((i4 & 1024) == 0) {
                                        closedFloatingPointRange2 = RangesKt.rangeTo(0.0f, 1.0f);
                                        i15 &= -15;
                                    } else {
                                        closedFloatingPointRange2 = closedFloatingPointRange;
                                    }
                                    i17 = i16;
                                    i18 = i15;
                                    function34 = composableLambda;
                                    function35 = function33;
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i4 & 32) != 0) {
                                        i22 &= -458753;
                                    }
                                    if ((i4 & 1024) != 0) {
                                        i15 &= -15;
                                    }
                                    function35 = function3;
                                    function34 = function32;
                                    closedFloatingPointRange2 = closedFloatingPointRange;
                                    i18 = i15;
                                    i17 = i22;
                                }
                                startRestartGroup.endDefaults();
                                Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function38 = function35;
                                if (ComposerKt.isTraceInProgress()) {
                                    mutableInteractionSource3 = mutableInteractionSource2;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource2;
                                    ComposerKt.traceEventStart(1191170377, i17, i18, "androidx.compose.material3.Slider (Slider.kt:270)");
                                }
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -403520192, "CC(remember):Slider.kt#9igjgp");
                                z3 = ((29360128 & i17) != 8388608) | ((((i18 & 14) ^ 6) > 4 && startRestartGroup.changed(closedFloatingPointRange2)) || (i18 & 6) == 4);
                                rememberedValue = startRestartGroup.rememberedValue();
                                if (!z3 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue = new SliderState(f, i10, function02, closedFloatingPointRange2);
                                    startRestartGroup.updateRememberedValue(rememberedValue);
                                }
                                SliderState sliderState = (SliderState) rememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                sliderState.setOnValueChangeFinished(function02);
                                sliderState.setOnValueChange$material3_release(function1);
                                sliderState.setValue(f);
                                int i23 = ((i17 >> 3) & PointerIconCompat.TYPE_TEXT) | ((i17 >> 6) & 57344);
                                int i24 = i17 >> 9;
                                Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function39 = function34;
                                Slider(sliderState, modifier2, z2, null, mutableInteractionSource3, function38, function39, startRestartGroup, i23 | (458752 & i24) | (i24 & 3670016), 8);
                                MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource3;
                                composer2 = startRestartGroup;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                boolean z5 = z2;
                                function36 = function39;
                                z4 = z5;
                                closedFloatingPointRange3 = closedFloatingPointRange2;
                                function37 = function38;
                                mutableInteractionSource4 = mutableInteractionSource5;
                                sliderColors3 = sliderColors2;
                                modifier3 = modifier2;
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                function37 = function3;
                                closedFloatingPointRange3 = closedFloatingPointRange;
                                composer2 = startRestartGroup;
                                z4 = z2;
                                function36 = function32;
                                mutableInteractionSource4 = mutableInteractionSource2;
                                modifier3 = modifier2;
                                sliderColors3 = sliderColors2;
                            }
                            final int i25 = i10;
                            final Function0<Unit> function03 = function02;
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$8
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
                                        SliderKt.Slider(f, function1, modifier3, z4, function03, sliderColors3, mutableInteractionSource4, i25, function37, function36, closedFloatingPointRange3, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        i14 = i13;
                        if ((i3 & 6) != 0) {
                        }
                        int i222 = i5;
                        if ((i5 & 306783379) == 306783378) {
                        }
                        startRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                        }
                        if (i21 != 0) {
                        }
                        if (i6 != 0) {
                        }
                        if (i7 != 0) {
                        }
                        if ((i4 & 32) == 0) {
                        }
                        if (i8 != 0) {
                        }
                        if (i9 != 0) {
                        }
                        if (i12 == 0) {
                        }
                        if (i14 == 0) {
                        }
                        if ((i4 & 1024) == 0) {
                        }
                        i17 = i16;
                        i18 = i15;
                        function34 = composableLambda;
                        function35 = function33;
                        startRestartGroup.endDefaults();
                        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function382 = function35;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -403520192, "CC(remember):Slider.kt#9igjgp");
                        if (((i18 & 14) ^ 6) > 4) {
                            z3 = ((29360128 & i17) != 8388608) | ((((i18 & 14) ^ 6) > 4 && startRestartGroup.changed(closedFloatingPointRange2)) || (i18 & 6) == 4);
                            rememberedValue = startRestartGroup.rememberedValue();
                            if (!z3) {
                            }
                            rememberedValue = new SliderState(f, i10, function02, closedFloatingPointRange2);
                            startRestartGroup.updateRememberedValue(rememberedValue);
                            SliderState sliderState2 = (SliderState) rememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            sliderState2.setOnValueChangeFinished(function02);
                            sliderState2.setOnValueChange$material3_release(function1);
                            sliderState2.setValue(f);
                            int i232 = ((i17 >> 3) & PointerIconCompat.TYPE_TEXT) | ((i17 >> 6) & 57344);
                            int i242 = i17 >> 9;
                            Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function392 = function34;
                            Slider(sliderState2, modifier2, z2, null, mutableInteractionSource3, function382, function392, startRestartGroup, i232 | (458752 & i242) | (i242 & 3670016), 8);
                            MutableInteractionSource mutableInteractionSource52 = mutableInteractionSource3;
                            composer2 = startRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            boolean z52 = z2;
                            function36 = function392;
                            z4 = z52;
                            closedFloatingPointRange3 = closedFloatingPointRange2;
                            function37 = function382;
                            mutableInteractionSource4 = mutableInteractionSource52;
                            sliderColors3 = sliderColors2;
                            modifier3 = modifier2;
                            final int i252 = i10;
                            final Function0<Unit> function032 = function02;
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        z3 = ((29360128 & i17) != 8388608) | ((((i18 & 14) ^ 6) > 4 && startRestartGroup.changed(closedFloatingPointRange2)) || (i18 & 6) == 4);
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (!z3) {
                        }
                        rememberedValue = new SliderState(f, i10, function02, closedFloatingPointRange2);
                        startRestartGroup.updateRememberedValue(rememberedValue);
                        SliderState sliderState22 = (SliderState) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        sliderState22.setOnValueChangeFinished(function02);
                        sliderState22.setOnValueChange$material3_release(function1);
                        sliderState22.setValue(f);
                        int i2322 = ((i17 >> 3) & PointerIconCompat.TYPE_TEXT) | ((i17 >> 6) & 57344);
                        int i2422 = i17 >> 9;
                        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3922 = function34;
                        Slider(sliderState22, modifier2, z2, null, mutableInteractionSource3, function382, function3922, startRestartGroup, i2322 | (458752 & i2422) | (i2422 & 3670016), 8);
                        MutableInteractionSource mutableInteractionSource522 = mutableInteractionSource3;
                        composer2 = startRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        boolean z522 = z2;
                        function36 = function3922;
                        z4 = z522;
                        closedFloatingPointRange3 = closedFloatingPointRange2;
                        function37 = function382;
                        mutableInteractionSource4 = mutableInteractionSource522;
                        sliderColors3 = sliderColors2;
                        modifier3 = modifier2;
                        final int i2522 = i10;
                        final Function0<Unit> function0322 = function02;
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    i12 = i11;
                    i13 = i4 & 512;
                    if (i13 != 0) {
                    }
                    i14 = i13;
                    if ((i3 & 6) != 0) {
                    }
                    int i2222 = i5;
                    if ((i5 & 306783379) == 306783378) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                    }
                    if (i21 != 0) {
                    }
                    if (i6 != 0) {
                    }
                    if (i7 != 0) {
                    }
                    if ((i4 & 32) == 0) {
                    }
                    if (i8 != 0) {
                    }
                    if (i9 != 0) {
                    }
                    if (i12 == 0) {
                    }
                    if (i14 == 0) {
                    }
                    if ((i4 & 1024) == 0) {
                    }
                    i17 = i16;
                    i18 = i15;
                    function34 = composableLambda;
                    function35 = function33;
                    startRestartGroup.endDefaults();
                    Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3822 = function35;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -403520192, "CC(remember):Slider.kt#9igjgp");
                    z3 = ((29360128 & i17) != 8388608) | ((((i18 & 14) ^ 6) > 4 && startRestartGroup.changed(closedFloatingPointRange2)) || (i18 & 6) == 4);
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (!z3) {
                    }
                    rememberedValue = new SliderState(f, i10, function02, closedFloatingPointRange2);
                    startRestartGroup.updateRememberedValue(rememberedValue);
                    SliderState sliderState222 = (SliderState) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    sliderState222.setOnValueChangeFinished(function02);
                    sliderState222.setOnValueChange$material3_release(function1);
                    sliderState222.setValue(f);
                    int i23222 = ((i17 >> 3) & PointerIconCompat.TYPE_TEXT) | ((i17 >> 6) & 57344);
                    int i24222 = i17 >> 9;
                    Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function39222 = function34;
                    Slider(sliderState222, modifier2, z2, null, mutableInteractionSource3, function3822, function39222, startRestartGroup, i23222 | (458752 & i24222) | (i24222 & 3670016), 8);
                    MutableInteractionSource mutableInteractionSource5222 = mutableInteractionSource3;
                    composer2 = startRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    boolean z5222 = z2;
                    function36 = function39222;
                    z4 = z5222;
                    closedFloatingPointRange3 = closedFloatingPointRange2;
                    function37 = function3822;
                    mutableInteractionSource4 = mutableInteractionSource5222;
                    sliderColors3 = sliderColors2;
                    modifier3 = modifier2;
                    final int i25222 = i10;
                    final Function0<Unit> function03222 = function02;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                function02 = function0;
                if ((196608 & i2) != 0) {
                }
                i8 = i4 & 64;
                if (i8 == 0) {
                }
                i9 = i4 & 128;
                if (i9 == 0) {
                }
                i11 = i4 & 256;
                if (i11 == 0) {
                }
                i12 = i11;
                i13 = i4 & 512;
                if (i13 != 0) {
                }
                i14 = i13;
                if ((i3 & 6) != 0) {
                }
                int i22222 = i5;
                if ((i5 & 306783379) == 306783378) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i21 != 0) {
                }
                if (i6 != 0) {
                }
                if (i7 != 0) {
                }
                if ((i4 & 32) == 0) {
                }
                if (i8 != 0) {
                }
                if (i9 != 0) {
                }
                if (i12 == 0) {
                }
                if (i14 == 0) {
                }
                if ((i4 & 1024) == 0) {
                }
                i17 = i16;
                i18 = i15;
                function34 = composableLambda;
                function35 = function33;
                startRestartGroup.endDefaults();
                Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function38222 = function35;
                if (ComposerKt.isTraceInProgress()) {
                }
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -403520192, "CC(remember):Slider.kt#9igjgp");
                z3 = ((29360128 & i17) != 8388608) | ((((i18 & 14) ^ 6) > 4 && startRestartGroup.changed(closedFloatingPointRange2)) || (i18 & 6) == 4);
                rememberedValue = startRestartGroup.rememberedValue();
                if (!z3) {
                }
                rememberedValue = new SliderState(f, i10, function02, closedFloatingPointRange2);
                startRestartGroup.updateRememberedValue(rememberedValue);
                SliderState sliderState2222 = (SliderState) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                sliderState2222.setOnValueChangeFinished(function02);
                sliderState2222.setOnValueChange$material3_release(function1);
                sliderState2222.setValue(f);
                int i232222 = ((i17 >> 3) & PointerIconCompat.TYPE_TEXT) | ((i17 >> 6) & 57344);
                int i242222 = i17 >> 9;
                Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function392222 = function34;
                Slider(sliderState2222, modifier2, z2, null, mutableInteractionSource3, function38222, function392222, startRestartGroup, i232222 | (458752 & i242222) | (i242222 & 3670016), 8);
                MutableInteractionSource mutableInteractionSource52222 = mutableInteractionSource3;
                composer2 = startRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                }
                boolean z52222 = z2;
                function36 = function392222;
                z4 = z52222;
                closedFloatingPointRange3 = closedFloatingPointRange2;
                function37 = function38222;
                mutableInteractionSource4 = mutableInteractionSource52222;
                sliderColors3 = sliderColors2;
                modifier3 = modifier2;
                final int i252222 = i10;
                final Function0<Unit> function032222 = function02;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            z2 = z;
            i7 = i4 & 16;
            if (i7 != 0) {
            }
            function02 = function0;
            if ((196608 & i2) != 0) {
            }
            i8 = i4 & 64;
            if (i8 == 0) {
            }
            i9 = i4 & 128;
            if (i9 == 0) {
            }
            i11 = i4 & 256;
            if (i11 == 0) {
            }
            i12 = i11;
            i13 = i4 & 512;
            if (i13 != 0) {
            }
            i14 = i13;
            if ((i3 & 6) != 0) {
            }
            int i222222 = i5;
            if ((i5 & 306783379) == 306783378) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i21 != 0) {
            }
            if (i6 != 0) {
            }
            if (i7 != 0) {
            }
            if ((i4 & 32) == 0) {
            }
            if (i8 != 0) {
            }
            if (i9 != 0) {
            }
            if (i12 == 0) {
            }
            if (i14 == 0) {
            }
            if ((i4 & 1024) == 0) {
            }
            i17 = i16;
            i18 = i15;
            function34 = composableLambda;
            function35 = function33;
            startRestartGroup.endDefaults();
            Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function382222 = function35;
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -403520192, "CC(remember):Slider.kt#9igjgp");
            z3 = ((29360128 & i17) != 8388608) | ((((i18 & 14) ^ 6) > 4 && startRestartGroup.changed(closedFloatingPointRange2)) || (i18 & 6) == 4);
            rememberedValue = startRestartGroup.rememberedValue();
            if (!z3) {
            }
            rememberedValue = new SliderState(f, i10, function02, closedFloatingPointRange2);
            startRestartGroup.updateRememberedValue(rememberedValue);
            SliderState sliderState22222 = (SliderState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            sliderState22222.setOnValueChangeFinished(function02);
            sliderState22222.setOnValueChange$material3_release(function1);
            sliderState22222.setValue(f);
            int i2322222 = ((i17 >> 3) & PointerIconCompat.TYPE_TEXT) | ((i17 >> 6) & 57344);
            int i2422222 = i17 >> 9;
            Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3922222 = function34;
            Slider(sliderState22222, modifier2, z2, null, mutableInteractionSource3, function382222, function3922222, startRestartGroup, i2322222 | (458752 & i2422222) | (i2422222 & 3670016), 8);
            MutableInteractionSource mutableInteractionSource522222 = mutableInteractionSource3;
            composer2 = startRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
            }
            boolean z522222 = z2;
            function36 = function3922222;
            z4 = z522222;
            closedFloatingPointRange3 = closedFloatingPointRange2;
            function37 = function382222;
            mutableInteractionSource4 = mutableInteractionSource522222;
            sliderColors3 = sliderColors2;
            modifier3 = modifier2;
            final int i2522222 = i10;
            final Function0<Unit> function0322222 = function02;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i6 = i4 & 8;
        if (i6 == 0) {
        }
        z2 = z;
        i7 = i4 & 16;
        if (i7 != 0) {
        }
        function02 = function0;
        if ((196608 & i2) != 0) {
        }
        i8 = i4 & 64;
        if (i8 == 0) {
        }
        i9 = i4 & 128;
        if (i9 == 0) {
        }
        i11 = i4 & 256;
        if (i11 == 0) {
        }
        i12 = i11;
        i13 = i4 & 512;
        if (i13 != 0) {
        }
        i14 = i13;
        if ((i3 & 6) != 0) {
        }
        int i2222222 = i5;
        if ((i5 & 306783379) == 306783378) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i21 != 0) {
        }
        if (i6 != 0) {
        }
        if (i7 != 0) {
        }
        if ((i4 & 32) == 0) {
        }
        if (i8 != 0) {
        }
        if (i9 != 0) {
        }
        if (i12 == 0) {
        }
        if (i14 == 0) {
        }
        if ((i4 & 1024) == 0) {
        }
        i17 = i16;
        i18 = i15;
        function34 = composableLambda;
        function35 = function33;
        startRestartGroup.endDefaults();
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3822222 = function35;
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -403520192, "CC(remember):Slider.kt#9igjgp");
        z3 = ((29360128 & i17) != 8388608) | ((((i18 & 14) ^ 6) > 4 && startRestartGroup.changed(closedFloatingPointRange2)) || (i18 & 6) == 4);
        rememberedValue = startRestartGroup.rememberedValue();
        if (!z3) {
        }
        rememberedValue = new SliderState(f, i10, function02, closedFloatingPointRange2);
        startRestartGroup.updateRememberedValue(rememberedValue);
        SliderState sliderState222222 = (SliderState) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        sliderState222222.setOnValueChangeFinished(function02);
        sliderState222222.setOnValueChange$material3_release(function1);
        sliderState222222.setValue(f);
        int i23222222 = ((i17 >> 3) & PointerIconCompat.TYPE_TEXT) | ((i17 >> 6) & 57344);
        int i24222222 = i17 >> 9;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function39222222 = function34;
        Slider(sliderState222222, modifier2, z2, null, mutableInteractionSource3, function3822222, function39222222, startRestartGroup, i23222222 | (458752 & i24222222) | (i24222222 & 3670016), 8);
        MutableInteractionSource mutableInteractionSource5222222 = mutableInteractionSource3;
        composer2 = startRestartGroup;
        if (ComposerKt.isTraceInProgress()) {
        }
        boolean z5222222 = z2;
        function36 = function39222222;
        z4 = z5222222;
        closedFloatingPointRange3 = closedFloatingPointRange2;
        function37 = function3822222;
        mutableInteractionSource4 = mutableInteractionSource5222222;
        sliderColors3 = sliderColors2;
        modifier3 = modifier2;
        final int i25222222 = i10;
        final Function0<Unit> function03222222 = function02;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void Slider(final SliderState sliderState, Modifier modifier, boolean z, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function32, Composer composer, final int i, final int i2) {
        SliderState sliderState2;
        int i3;
        Modifier modifier2;
        int i4;
        final boolean z2;
        final SliderColors sliderColors2;
        int i5;
        final MutableInteractionSource mutableInteractionSource2;
        int i6;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function33;
        int i7;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function34;
        boolean z3;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function35;
        MutableInteractionSource mutableInteractionSource3;
        Modifier modifier3;
        final SliderColors sliderColors3;
        final Modifier modifier4;
        final boolean z4;
        final MutableInteractionSource mutableInteractionSource4;
        final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function36;
        int i8;
        Composer startRestartGroup = composer.startRestartGroup(-1303883986);
        ComposerKt.sourceInformation(startRestartGroup, "C(Slider)P(4,3,1)339@15682L8,340@15742L39,341@15830L157,348@16036L114,354@16217L188:Slider.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            sliderState2 = sliderState;
        } else {
            sliderState2 = sliderState;
            if ((i & 6) == 0) {
                i3 = (startRestartGroup.changedInstance(sliderState2) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        int i9 = i2 & 2;
        if (i9 != 0) {
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
                            i8 = 2048;
                            i3 |= i8;
                        }
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i8 = 1024;
                    i3 |= i8;
                } else {
                    sliderColors2 = sliderColors;
                }
                i5 = i2 & 16;
                if (i5 != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    i3 |= startRestartGroup.changed(mutableInteractionSource2) ? 16384 : 8192;
                    i6 = i2 & 32;
                    if (i6 == 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((196608 & i) == 0) {
                        function33 = function3;
                        i3 |= startRestartGroup.changedInstance(function33) ? 131072 : 65536;
                        i7 = i2 & 64;
                        if (i7 != 0) {
                            i3 |= 1572864;
                        } else if ((1572864 & i) == 0) {
                            function34 = function32;
                            i3 |= startRestartGroup.changedInstance(function34) ? 1048576 : 524288;
                            if ((i3 & 599187) == 599186 || !startRestartGroup.getSkipping()) {
                                startRestartGroup.startDefaults();
                                if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                    Modifier.Companion companion = i9 == 0 ? Modifier.INSTANCE : modifier2;
                                    if (i4 != 0) {
                                        z2 = true;
                                    }
                                    if ((i2 & 8) != 0) {
                                        i3 &= -7169;
                                        sliderColors2 = SliderDefaults.INSTANCE.colors(startRestartGroup, 6);
                                    }
                                    if (i5 != 0) {
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -403425429, "CC(remember):Slider.kt#9igjgp");
                                        Object rememberedValue = startRestartGroup.rememberedValue();
                                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue = InteractionSourceKt.MutableInteractionSource();
                                            startRestartGroup.updateRememberedValue(rememberedValue);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        mutableInteractionSource2 = (MutableInteractionSource) rememberedValue;
                                    }
                                    if (i6 != 0) {
                                        function33 = ComposableLambdaKt.rememberComposableLambda(1426271326, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$10
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState3, Composer composer2, Integer num) {
                                                invoke(sliderState3, composer2, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(SliderState sliderState3, Composer composer2, int i10) {
                                                ComposerKt.sourceInformation(composer2, "C342@15855L126:Slider.kt#uh7d8r");
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1426271326, i10, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:342)");
                                                }
                                                SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z2, 0L, composer2, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }, startRestartGroup, 54);
                                    }
                                    if (i7 != 0) {
                                        function34 = ComposableLambdaKt.rememberComposableLambda(577038345, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$11
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState3, Composer composer2, Integer num) {
                                                invoke(sliderState3, composer2, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(SliderState sliderState3, Composer composer2, int i10) {
                                                ComposerKt.sourceInformation(composer2, "C349@16076L68:Slider.kt#uh7d8r");
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(577038345, i10, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:349)");
                                                }
                                                SliderDefaults.INSTANCE.m2650Track4EFweAY(sliderState3, (Modifier) null, z2, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer2, (i10 & 14) | 100663296, 242);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }, startRestartGroup, 54);
                                    }
                                    z3 = z2;
                                    function35 = function33;
                                    mutableInteractionSource3 = mutableInteractionSource2;
                                    modifier3 = companion;
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i2 & 8) != 0) {
                                        i3 &= -7169;
                                    }
                                    z3 = z2;
                                    function35 = function33;
                                    mutableInteractionSource3 = mutableInteractionSource2;
                                    modifier3 = modifier2;
                                }
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1303883986, i3, -1, "androidx.compose.material3.Slider (Slider.kt:351)");
                                }
                                if (sliderState2.getSteps() >= 0) {
                                    throw new IllegalArgumentException("steps should be >= 0".toString());
                                }
                                int i10 = i3 >> 3;
                                SliderImpl(modifier3, sliderState2, z3, mutableInteractionSource3, function35, function34, startRestartGroup, (i3 & 896) | (i10 & 14) | ((i3 << 3) & 112) | (i10 & 7168) | (57344 & i10) | (i10 & 458752));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                sliderColors3 = sliderColors2;
                                modifier4 = modifier3;
                                z4 = z3;
                                mutableInteractionSource4 = mutableInteractionSource3;
                                function36 = function35;
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                modifier4 = modifier2;
                                z4 = z2;
                                sliderColors3 = sliderColors2;
                                mutableInteractionSource4 = mutableInteractionSource2;
                                function36 = function33;
                            }
                            final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function37 = function34;
                            ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
                            if (endRestartGroup != null) {
                                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$Slider$13
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
                                        SliderKt.Slider(SliderState.this, modifier4, z4, sliderColors3, mutableInteractionSource4, function36, function37, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        function34 = function32;
                        if ((i3 & 599187) == 599186) {
                        }
                        startRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                        }
                        if (i9 == 0) {
                        }
                        if (i4 != 0) {
                        }
                        if ((i2 & 8) != 0) {
                        }
                        if (i5 != 0) {
                        }
                        if (i6 != 0) {
                        }
                        if (i7 != 0) {
                        }
                        z3 = z2;
                        function35 = function33;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (sliderState2.getSteps() >= 0) {
                        }
                    }
                    function33 = function3;
                    i7 = i2 & 64;
                    if (i7 != 0) {
                    }
                    function34 = function32;
                    if ((i3 & 599187) == 599186) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                    }
                    if (i9 == 0) {
                    }
                    if (i4 != 0) {
                    }
                    if ((i2 & 8) != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if (i6 != 0) {
                    }
                    if (i7 != 0) {
                    }
                    z3 = z2;
                    function35 = function33;
                    mutableInteractionSource3 = mutableInteractionSource2;
                    modifier3 = companion;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    if (sliderState2.getSteps() >= 0) {
                    }
                }
                mutableInteractionSource2 = mutableInteractionSource;
                i6 = i2 & 32;
                if (i6 == 0) {
                }
                function33 = function3;
                i7 = i2 & 64;
                if (i7 != 0) {
                }
                function34 = function32;
                if ((i3 & 599187) == 599186) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i9 == 0) {
                }
                if (i4 != 0) {
                }
                if ((i2 & 8) != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                if (i7 != 0) {
                }
                z3 = z2;
                function35 = function33;
                mutableInteractionSource3 = mutableInteractionSource2;
                modifier3 = companion;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                if (sliderState2.getSteps() >= 0) {
                }
            }
            z2 = z;
            if ((i & 3072) == 0) {
            }
            i5 = i2 & 16;
            if (i5 != 0) {
            }
            mutableInteractionSource2 = mutableInteractionSource;
            i6 = i2 & 32;
            if (i6 == 0) {
            }
            function33 = function3;
            i7 = i2 & 64;
            if (i7 != 0) {
            }
            function34 = function32;
            if ((i3 & 599187) == 599186) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i9 == 0) {
            }
            if (i4 != 0) {
            }
            if ((i2 & 8) != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            if (i7 != 0) {
            }
            z3 = z2;
            function35 = function33;
            mutableInteractionSource3 = mutableInteractionSource2;
            modifier3 = companion;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            if (sliderState2.getSteps() >= 0) {
            }
        }
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 == 0) {
        }
        z2 = z;
        if ((i & 3072) == 0) {
        }
        i5 = i2 & 16;
        if (i5 != 0) {
        }
        mutableInteractionSource2 = mutableInteractionSource;
        i6 = i2 & 32;
        if (i6 == 0) {
        }
        function33 = function3;
        i7 = i2 & 64;
        if (i7 != 0) {
        }
        function34 = function32;
        if ((i3 & 599187) == 599186) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i9 == 0) {
        }
        if (i4 != 0) {
        }
        if ((i2 & 8) != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        if (i7 != 0) {
        }
        z3 = z2;
        function35 = function33;
        mutableInteractionSource3 = mutableInteractionSource2;
        modifier3 = companion;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        if (sliderState2.getSteps() >= 0) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void RangeSlider(final ClosedFloatingPointRange<Float> closedFloatingPointRange, final Function1<? super ClosedFloatingPointRange<Float>, Unit> function1, Modifier modifier, boolean z, ClosedFloatingPointRange<Float> closedFloatingPointRange2, int i, Function0<Unit> function0, SliderColors sliderColors, Composer composer, final int i2, final int i3) {
        ClosedFloatingPointRange<Float> closedFloatingPointRange3;
        int i4;
        Function1<? super ClosedFloatingPointRange<Float>, Unit> function12;
        Modifier modifier2;
        int i5;
        boolean z2;
        ClosedFloatingPointRange<Float> closedFloatingPointRange4;
        int i6;
        int i7;
        int i8;
        Function0<Unit> function02;
        int i9;
        final SliderColors sliderColors2;
        final boolean z3;
        int i10;
        Function0<Unit> function03;
        Modifier modifier3;
        int i11;
        Object rememberedValue;
        Object rememberedValue2;
        Composer composer2;
        final SliderColors sliderColors3;
        final Modifier modifier4;
        final boolean z4;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange5;
        final Function0<Unit> function04;
        final int i12;
        ScopeUpdateScope endRestartGroup;
        int i13;
        int i14;
        Composer startRestartGroup = composer.startRestartGroup(-743091416);
        ComposerKt.sourceInformation(startRestartGroup, "C(RangeSlider)P(6,3,2,1,7,5,4)408@18812L8,410@18884L39,411@18981L39,423@19399L186,430@19606L184,437@19808L199,413@19026L987:Slider.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            closedFloatingPointRange3 = closedFloatingPointRange;
        } else {
            closedFloatingPointRange3 = closedFloatingPointRange;
            if ((i2 & 6) == 0) {
                i4 = (startRestartGroup.changed(closedFloatingPointRange3) ? 4 : 2) | i2;
            } else {
                i4 = i2;
            }
        }
        if ((i3 & 2) != 0) {
            i4 |= 48;
            function12 = function1;
        } else {
            function12 = function1;
            if ((i2 & 48) == 0) {
                i4 |= startRestartGroup.changedInstance(function12) ? 32 : 16;
            }
        }
        int i15 = i3 & 4;
        if (i15 != 0) {
            i4 |= 384;
        } else if ((i2 & 384) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 256 : 128;
            i5 = i3 & 8;
            if (i5 == 0) {
                i4 |= 3072;
            } else if ((i2 & 3072) == 0) {
                z2 = z;
                i4 |= startRestartGroup.changed(z2) ? 2048 : 1024;
                if ((i2 & 24576) == 0) {
                    if ((i3 & 16) == 0) {
                        closedFloatingPointRange4 = closedFloatingPointRange2;
                        if (startRestartGroup.changed(closedFloatingPointRange4)) {
                            i14 = 16384;
                            i4 |= i14;
                        }
                    } else {
                        closedFloatingPointRange4 = closedFloatingPointRange2;
                    }
                    i14 = 8192;
                    i4 |= i14;
                } else {
                    closedFloatingPointRange4 = closedFloatingPointRange2;
                }
                i6 = i3 & 32;
                if (i6 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i2) == 0) {
                    i7 = i;
                    i4 |= startRestartGroup.changed(i7) ? 131072 : 65536;
                    i8 = i3 & 64;
                    if (i8 == 0) {
                        i4 |= 1572864;
                    } else if ((1572864 & i2) == 0) {
                        function02 = function0;
                        i4 |= startRestartGroup.changedInstance(function02) ? 1048576 : 524288;
                        if ((i2 & 12582912) == 0) {
                            if ((i3 & 128) == 0 && startRestartGroup.changed(sliderColors)) {
                                i13 = 8388608;
                                i4 |= i13;
                            }
                            i13 = 4194304;
                            i4 |= i13;
                        }
                        int i16 = i4;
                        if ((i4 & 4793491) == 4793490 || !startRestartGroup.getSkipping()) {
                            startRestartGroup.startDefaults();
                            if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                Modifier.Companion companion = i15 != 0 ? Modifier.INSTANCE : modifier2;
                                if (i5 != 0) {
                                    z2 = true;
                                }
                                if ((i3 & 16) != 0) {
                                    i9 = i16 & (-57345);
                                    closedFloatingPointRange4 = RangesKt.rangeTo(0.0f, 1.0f);
                                } else {
                                    i9 = i16;
                                }
                                if (i6 != 0) {
                                    i7 = 0;
                                }
                                if (i8 != 0) {
                                    function02 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    sliderColors2 = SliderDefaults.INSTANCE.colors(startRestartGroup, 6);
                                    i9 &= -29360129;
                                } else {
                                    sliderColors2 = sliderColors;
                                }
                                z3 = z2;
                                i10 = i7;
                                function03 = function02;
                                modifier3 = companion;
                                i11 = i9;
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                i11 = (i3 & 16) != 0 ? i16 & (-57345) : i16;
                                if ((i3 & 128) != 0) {
                                    i11 &= -29360129;
                                }
                                sliderColors2 = sliderColors;
                                z3 = z2;
                                i10 = i7;
                                function03 = function02;
                                modifier3 = modifier2;
                            }
                            ClosedFloatingPointRange<Float> closedFloatingPointRange6 = closedFloatingPointRange4;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-743091416, i11, -1, "androidx.compose.material3.RangeSlider (Slider.kt:409)");
                            }
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661051042, "CC(remember):Slider.kt#9igjgp");
                            rememberedValue = startRestartGroup.rememberedValue();
                            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                rememberedValue = InteractionSourceKt.MutableInteractionSource();
                                startRestartGroup.updateRememberedValue(rememberedValue);
                            }
                            final MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) rememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661054146, "CC(remember):Slider.kt#9igjgp");
                            rememberedValue2 = startRestartGroup.rememberedValue();
                            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                rememberedValue2 = InteractionSourceKt.MutableInteractionSource();
                                startRestartGroup.updateRememberedValue(rememberedValue2);
                            }
                            final MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) rememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            composer2 = startRestartGroup;
                            RangeSlider(closedFloatingPointRange3, function12, modifier3, z3, closedFloatingPointRange6, function03, null, mutableInteractionSource, mutableInteractionSource2, ComposableLambdaKt.rememberComposableLambda(-811582901, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                                    invoke(rangeSliderState, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                                    ComposerKt.sourceInformation(composer3, "C424@19428L147:Slider.kt#uh7d8r");
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-811582901, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:424)");
                                    }
                                    SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z3, 0L, composer3, 196614, 18);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1832060001, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                                    invoke(rangeSliderState, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                                    ComposerKt.sourceInformation(composer3, "C431@19635L145:Slider.kt#uh7d8r");
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1832060001, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:431)");
                                    }
                                    SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z3, 0L, composer3, 196614, 18);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(377064480, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                                    invoke(rangeSliderState, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                                    ComposerKt.sourceInformation(composer3, "C438@19857L140:Slider.kt#uh7d8r");
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(377064480, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:438)");
                                    }
                                    SliderDefaults.INSTANCE.m2649Track4EFweAY(rangeSliderState, (Modifier) null, z3, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i17 & 14) | 100663296, 242);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, startRestartGroup, 54), i10, composer2, (i11 & 14) | 918552576 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | (57344 & i11) | ((i11 >> 3) & 458752), ((i11 >> 9) & 896) | 54, 64);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            sliderColors3 = sliderColors2;
                            modifier4 = modifier3;
                            z4 = z3;
                            closedFloatingPointRange5 = closedFloatingPointRange6;
                            function04 = function03;
                            i12 = i10;
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            sliderColors3 = sliderColors;
                            composer2 = startRestartGroup;
                            modifier4 = modifier2;
                            z4 = z2;
                            closedFloatingPointRange5 = closedFloatingPointRange4;
                            i12 = i7;
                            function04 = function02;
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$4
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

                                public final void invoke(Composer composer3, int i17) {
                                    SliderKt.RangeSlider(closedFloatingPointRange, function1, modifier4, z4, closedFloatingPointRange5, i12, function04, sliderColors3, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    function02 = function0;
                    if ((i2 & 12582912) == 0) {
                    }
                    int i162 = i4;
                    if ((i4 & 4793491) == 4793490) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                    }
                    if (i15 != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if ((i3 & 16) != 0) {
                    }
                    if (i6 != 0) {
                    }
                    if (i8 != 0) {
                    }
                    if ((i3 & 128) != 0) {
                    }
                    z3 = z2;
                    i10 = i7;
                    function03 = function02;
                    modifier3 = companion;
                    i11 = i9;
                    ClosedFloatingPointRange<Float> closedFloatingPointRange62 = closedFloatingPointRange4;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661051042, "CC(remember):Slider.kt#9igjgp");
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    }
                    final MutableInteractionSource mutableInteractionSource3 = (MutableInteractionSource) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661054146, "CC(remember):Slider.kt#9igjgp");
                    rememberedValue2 = startRestartGroup.rememberedValue();
                    if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    }
                    final MutableInteractionSource mutableInteractionSource22 = (MutableInteractionSource) rememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    composer2 = startRestartGroup;
                    RangeSlider(closedFloatingPointRange3, function12, modifier3, z3, closedFloatingPointRange62, function03, null, mutableInteractionSource3, mutableInteractionSource22, ComposableLambdaKt.rememberComposableLambda(-811582901, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                            invoke(rangeSliderState, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                            ComposerKt.sourceInformation(composer3, "C424@19428L147:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-811582901, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:424)");
                            }
                            SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z3, 0L, composer3, 196614, 18);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1832060001, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                            invoke(rangeSliderState, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                            ComposerKt.sourceInformation(composer3, "C431@19635L145:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1832060001, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:431)");
                            }
                            SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z3, 0L, composer3, 196614, 18);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(377064480, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                            invoke(rangeSliderState, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                            ComposerKt.sourceInformation(composer3, "C438@19857L140:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(377064480, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:438)");
                            }
                            SliderDefaults.INSTANCE.m2649Track4EFweAY(rangeSliderState, (Modifier) null, z3, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i17 & 14) | 100663296, 242);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, startRestartGroup, 54), i10, composer2, (i11 & 14) | 918552576 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | (57344 & i11) | ((i11 >> 3) & 458752), ((i11 >> 9) & 896) | 54, 64);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    sliderColors3 = sliderColors2;
                    modifier4 = modifier3;
                    z4 = z3;
                    closedFloatingPointRange5 = closedFloatingPointRange62;
                    function04 = function03;
                    i12 = i10;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                i7 = i;
                i8 = i3 & 64;
                if (i8 == 0) {
                }
                function02 = function0;
                if ((i2 & 12582912) == 0) {
                }
                int i1622 = i4;
                if ((i4 & 4793491) == 4793490) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i15 != 0) {
                }
                if (i5 != 0) {
                }
                if ((i3 & 16) != 0) {
                }
                if (i6 != 0) {
                }
                if (i8 != 0) {
                }
                if ((i3 & 128) != 0) {
                }
                z3 = z2;
                i10 = i7;
                function03 = function02;
                modifier3 = companion;
                i11 = i9;
                ClosedFloatingPointRange<Float> closedFloatingPointRange622 = closedFloatingPointRange4;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661051042, "CC(remember):Slider.kt#9igjgp");
                rememberedValue = startRestartGroup.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                final MutableInteractionSource mutableInteractionSource32 = (MutableInteractionSource) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661054146, "CC(remember):Slider.kt#9igjgp");
                rememberedValue2 = startRestartGroup.rememberedValue();
                if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                }
                final MutableInteractionSource mutableInteractionSource222 = (MutableInteractionSource) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                composer2 = startRestartGroup;
                RangeSlider(closedFloatingPointRange3, function12, modifier3, z3, closedFloatingPointRange622, function03, null, mutableInteractionSource32, mutableInteractionSource222, ComposableLambdaKt.rememberComposableLambda(-811582901, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                        invoke(rangeSliderState, composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                        ComposerKt.sourceInformation(composer3, "C424@19428L147:Slider.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-811582901, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:424)");
                        }
                        SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z3, 0L, composer3, 196614, 18);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1832060001, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                        invoke(rangeSliderState, composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                        ComposerKt.sourceInformation(composer3, "C431@19635L145:Slider.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1832060001, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:431)");
                        }
                        SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z3, 0L, composer3, 196614, 18);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(377064480, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                        invoke(rangeSliderState, composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                        ComposerKt.sourceInformation(composer3, "C438@19857L140:Slider.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(377064480, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:438)");
                        }
                        SliderDefaults.INSTANCE.m2649Track4EFweAY(rangeSliderState, (Modifier) null, z3, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i17 & 14) | 100663296, 242);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, startRestartGroup, 54), i10, composer2, (i11 & 14) | 918552576 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | (57344 & i11) | ((i11 >> 3) & 458752), ((i11 >> 9) & 896) | 54, 64);
                if (ComposerKt.isTraceInProgress()) {
                }
                sliderColors3 = sliderColors2;
                modifier4 = modifier3;
                z4 = z3;
                closedFloatingPointRange5 = closedFloatingPointRange622;
                function04 = function03;
                i12 = i10;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            z2 = z;
            if ((i2 & 24576) == 0) {
            }
            i6 = i3 & 32;
            if (i6 != 0) {
            }
            i7 = i;
            i8 = i3 & 64;
            if (i8 == 0) {
            }
            function02 = function0;
            if ((i2 & 12582912) == 0) {
            }
            int i16222 = i4;
            if ((i4 & 4793491) == 4793490) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i15 != 0) {
            }
            if (i5 != 0) {
            }
            if ((i3 & 16) != 0) {
            }
            if (i6 != 0) {
            }
            if (i8 != 0) {
            }
            if ((i3 & 128) != 0) {
            }
            z3 = z2;
            i10 = i7;
            function03 = function02;
            modifier3 = companion;
            i11 = i9;
            ClosedFloatingPointRange<Float> closedFloatingPointRange6222 = closedFloatingPointRange4;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661051042, "CC(remember):Slider.kt#9igjgp");
            rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            }
            final MutableInteractionSource mutableInteractionSource322 = (MutableInteractionSource) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661054146, "CC(remember):Slider.kt#9igjgp");
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            }
            final MutableInteractionSource mutableInteractionSource2222 = (MutableInteractionSource) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            composer2 = startRestartGroup;
            RangeSlider(closedFloatingPointRange3, function12, modifier3, z3, closedFloatingPointRange6222, function03, null, mutableInteractionSource322, mutableInteractionSource2222, ComposableLambdaKt.rememberComposableLambda(-811582901, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                    invoke(rangeSliderState, composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                    ComposerKt.sourceInformation(composer3, "C424@19428L147:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-811582901, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:424)");
                    }
                    SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z3, 0L, composer3, 196614, 18);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1832060001, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                    invoke(rangeSliderState, composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                    ComposerKt.sourceInformation(composer3, "C431@19635L145:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1832060001, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:431)");
                    }
                    SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z3, 0L, composer3, 196614, 18);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(377064480, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                    invoke(rangeSliderState, composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                    ComposerKt.sourceInformation(composer3, "C438@19857L140:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(377064480, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:438)");
                    }
                    SliderDefaults.INSTANCE.m2649Track4EFweAY(rangeSliderState, (Modifier) null, z3, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i17 & 14) | 100663296, 242);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, startRestartGroup, 54), i10, composer2, (i11 & 14) | 918552576 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | (57344 & i11) | ((i11 >> 3) & 458752), ((i11 >> 9) & 896) | 54, 64);
            if (ComposerKt.isTraceInProgress()) {
            }
            sliderColors3 = sliderColors2;
            modifier4 = modifier3;
            z4 = z3;
            closedFloatingPointRange5 = closedFloatingPointRange6222;
            function04 = function03;
            i12 = i10;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i5 = i3 & 8;
        if (i5 == 0) {
        }
        z2 = z;
        if ((i2 & 24576) == 0) {
        }
        i6 = i3 & 32;
        if (i6 != 0) {
        }
        i7 = i;
        i8 = i3 & 64;
        if (i8 == 0) {
        }
        function02 = function0;
        if ((i2 & 12582912) == 0) {
        }
        int i162222 = i4;
        if ((i4 & 4793491) == 4793490) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i15 != 0) {
        }
        if (i5 != 0) {
        }
        if ((i3 & 16) != 0) {
        }
        if (i6 != 0) {
        }
        if (i8 != 0) {
        }
        if ((i3 & 128) != 0) {
        }
        z3 = z2;
        i10 = i7;
        function03 = function02;
        modifier3 = companion;
        i11 = i9;
        ClosedFloatingPointRange<Float> closedFloatingPointRange62222 = closedFloatingPointRange4;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661051042, "CC(remember):Slider.kt#9igjgp");
        rememberedValue = startRestartGroup.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
        }
        final MutableInteractionSource mutableInteractionSource3222 = (MutableInteractionSource) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661054146, "CC(remember):Slider.kt#9igjgp");
        rememberedValue2 = startRestartGroup.rememberedValue();
        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
        }
        final MutableInteractionSource mutableInteractionSource22222 = (MutableInteractionSource) rememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        composer2 = startRestartGroup;
        RangeSlider(closedFloatingPointRange3, function12, modifier3, z3, closedFloatingPointRange62222, function03, null, mutableInteractionSource3222, mutableInteractionSource22222, ComposableLambdaKt.rememberComposableLambda(-811582901, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                invoke(rangeSliderState, composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                ComposerKt.sourceInformation(composer3, "C424@19428L147:Slider.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-811582901, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:424)");
                }
                SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z3, 0L, composer3, 196614, 18);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1832060001, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                invoke(rangeSliderState, composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                ComposerKt.sourceInformation(composer3, "C431@19635L145:Slider.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1832060001, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:431)");
                }
                SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z3, 0L, composer3, 196614, 18);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }, startRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(377064480, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                invoke(rangeSliderState, composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i17) {
                ComposerKt.sourceInformation(composer3, "C438@19857L140:Slider.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(377064480, i17, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:438)");
                }
                SliderDefaults.INSTANCE.m2649Track4EFweAY(rangeSliderState, (Modifier) null, z3, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i17 & 14) | 100663296, 242);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }, startRestartGroup, 54), i10, composer2, (i11 & 14) | 918552576 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | (57344 & i11) | ((i11 >> 3) & 458752), ((i11 >> 9) & 896) | 54, 64);
        if (ComposerKt.isTraceInProgress()) {
        }
        sliderColors3 = sliderColors2;
        modifier4 = modifier3;
        z4 = z3;
        closedFloatingPointRange5 = closedFloatingPointRange62222;
        function04 = function03;
        i12 = i10;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0371  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void RangeSlider(final ClosedFloatingPointRange<Float> closedFloatingPointRange, final Function1<? super ClosedFloatingPointRange<Float>, Unit> function1, Modifier modifier, boolean z, ClosedFloatingPointRange<Float> closedFloatingPointRange2, Function0<Unit> function0, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function32, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function33, int i, Composer composer, final int i2, final int i3, final int i4) {
        int i5;
        Modifier modifier2;
        int i6;
        final boolean z2;
        ClosedFloatingPointRange<Float> closedFloatingPointRange3;
        int i7;
        Function0<Unit> function02;
        final SliderColors sliderColors2;
        int i8;
        MutableInteractionSource mutableInteractionSource3;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        Modifier.Companion companion;
        final MutableInteractionSource mutableInteractionSource4;
        final MutableInteractionSource mutableInteractionSource5;
        ClosedFloatingPointRange<Float> closedFloatingPointRange4;
        int i19;
        boolean z3;
        ComposableLambda composableLambda;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function34;
        ComposableLambda composableLambda2;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function35;
        ComposableLambda composableLambda3;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function36;
        int i20;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function37;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function38;
        boolean z4;
        boolean z5;
        Object rememberedValue;
        int i21;
        boolean z6;
        Object rememberedValue2;
        Composer composer2;
        final SliderColors sliderColors3;
        final MutableInteractionSource mutableInteractionSource6;
        final int i22;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function39;
        final boolean z7;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange5;
        final Function0<Unit> function03;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function310;
        final Modifier modifier3;
        final MutableInteractionSource mutableInteractionSource7;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function311;
        ScopeUpdateScope endRestartGroup;
        int i23;
        Composer startRestartGroup = composer.startRestartGroup(-1048796133);
        ComposerKt.sourceInformation(startRestartGroup, "C(RangeSlider)P(11,5,4,1,12,6!1,7!1,8!1,10)511@23750L8,512@23815L39,513@23909L39,514@24007L162,521@24226L160,528@24440L175,538@24684L237,549@25005L44,553@25142L291:Slider.kt#uh7d8r");
        if ((i4 & 1) != 0) {
            i5 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i5 = (startRestartGroup.changed(closedFloatingPointRange) ? 4 : 2) | i2;
        } else {
            i5 = i2;
        }
        if ((i4 & 2) != 0) {
            i5 |= 48;
        } else if ((i2 & 48) == 0) {
            i5 |= startRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i24 = i4 & 4;
        if (i24 != 0) {
            i5 |= 384;
        } else if ((i2 & 384) == 0) {
            modifier2 = modifier;
            i5 |= startRestartGroup.changed(modifier2) ? 256 : 128;
            i6 = i4 & 8;
            if (i6 == 0) {
                i5 |= 3072;
            } else if ((i2 & 3072) == 0) {
                z2 = z;
                i5 |= startRestartGroup.changed(z2) ? 2048 : 1024;
                if ((i2 & 24576) == 0) {
                    if ((i4 & 16) == 0) {
                        closedFloatingPointRange3 = closedFloatingPointRange2;
                        if (startRestartGroup.changed(closedFloatingPointRange3)) {
                            i23 = 16384;
                            i5 |= i23;
                        }
                    } else {
                        closedFloatingPointRange3 = closedFloatingPointRange2;
                    }
                    i23 = 8192;
                    i5 |= i23;
                } else {
                    closedFloatingPointRange3 = closedFloatingPointRange2;
                }
                i7 = i4 & 32;
                if (i7 != 0) {
                    i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function02 = function0;
                } else {
                    function02 = function0;
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        i5 |= startRestartGroup.changedInstance(function02) ? 131072 : 65536;
                    }
                }
                if ((i2 & 1572864) == 0) {
                    sliderColors2 = sliderColors;
                    i5 |= ((i4 & 64) == 0 && startRestartGroup.changed(sliderColors2)) ? 1048576 : 524288;
                } else {
                    sliderColors2 = sliderColors;
                }
                i8 = i4 & 128;
                if (i8 != 0) {
                    i5 |= 12582912;
                    mutableInteractionSource3 = mutableInteractionSource;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                    if ((i2 & 12582912) == 0) {
                        i5 |= startRestartGroup.changed(mutableInteractionSource3) ? 8388608 : 4194304;
                    }
                }
                i9 = i4 & 256;
                if (i9 != 0) {
                    i5 |= 100663296;
                } else if ((i2 & 100663296) == 0) {
                    i10 = i9;
                    i5 |= startRestartGroup.changed(mutableInteractionSource2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                    i11 = i4 & 512;
                    if (i11 == 0) {
                        i5 |= 805306368;
                    } else if ((i2 & 805306368) == 0) {
                        i12 = i11;
                        i5 |= startRestartGroup.changedInstance(function3) ? 536870912 : 268435456;
                        i13 = i4 & 1024;
                        if (i13 != 0) {
                            i15 = i3 | 6;
                            i14 = i13;
                        } else if ((i3 & 6) == 0) {
                            i14 = i13;
                            i15 = i3 | (startRestartGroup.changedInstance(function32) ? 4 : 2);
                        } else {
                            i14 = i13;
                            i15 = i3;
                        }
                        i16 = i4 & 2048;
                        if (i16 != 0) {
                            i15 |= 48;
                            i17 = i16;
                        } else if ((i3 & 48) == 0) {
                            i17 = i16;
                            i15 |= startRestartGroup.changedInstance(function33) ? 32 : 16;
                        } else {
                            i17 = i16;
                        }
                        int i25 = i15;
                        i18 = i4 & 4096;
                        if (i18 != 0) {
                            i25 |= 384;
                        } else if ((i3 & 384) == 0) {
                            i25 |= startRestartGroup.changed(i) ? 256 : 128;
                            if ((i5 & 306783379) == 306783378 || (i25 & 147) != 146 || !startRestartGroup.getSkipping()) {
                                startRestartGroup.startDefaults();
                                if ((i2 & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                    companion = i24 == 0 ? Modifier.INSTANCE : modifier2;
                                    if (i6 != 0) {
                                        z2 = true;
                                    }
                                    if ((i4 & 16) != 0) {
                                        closedFloatingPointRange3 = RangesKt.rangeTo(0.0f, 1.0f);
                                        i5 &= -57345;
                                    }
                                    if (i7 != 0) {
                                        function02 = null;
                                    }
                                    if ((i4 & 64) != 0) {
                                        sliderColors2 = SliderDefaults.INSTANCE.colors(startRestartGroup, 6);
                                        i5 &= -3670017;
                                    }
                                    if (i8 == 0) {
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661208834, "CC(remember):Slider.kt#9igjgp");
                                        Object rememberedValue3 = startRestartGroup.rememberedValue();
                                        if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue3 = InteractionSourceKt.MutableInteractionSource();
                                            startRestartGroup.updateRememberedValue(rememberedValue3);
                                        }
                                        mutableInteractionSource4 = (MutableInteractionSource) rememberedValue3;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    } else {
                                        mutableInteractionSource4 = mutableInteractionSource3;
                                    }
                                    if (i10 == 0) {
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661211842, "CC(remember):Slider.kt#9igjgp");
                                        Object rememberedValue4 = startRestartGroup.rememberedValue();
                                        if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue4 = InteractionSourceKt.MutableInteractionSource();
                                            startRestartGroup.updateRememberedValue(rememberedValue4);
                                        }
                                        mutableInteractionSource5 = (MutableInteractionSource) rememberedValue4;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    } else {
                                        mutableInteractionSource5 = mutableInteractionSource2;
                                    }
                                    if (i12 == 0) {
                                        closedFloatingPointRange4 = closedFloatingPointRange3;
                                        i19 = i5;
                                        z3 = true;
                                        composableLambda = ComposableLambdaKt.rememberComposableLambda(-1963073082, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$7
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                                                invoke(rangeSliderState, composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i26) {
                                                ComposerKt.sourceInformation(composer3, "C515@24032L131:Slider.kt#uh7d8r");
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1963073082, i26, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:515)");
                                                }
                                                SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z2, 0L, composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }, startRestartGroup, 54);
                                    } else {
                                        closedFloatingPointRange4 = closedFloatingPointRange3;
                                        i19 = i5;
                                        z3 = true;
                                        composableLambda = function3;
                                    }
                                    if (i14 == 0) {
                                        function34 = composableLambda;
                                        composableLambda2 = ComposableLambdaKt.rememberComposableLambda(1908709951, z3, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$8
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                                                invoke(rangeSliderState, composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i26) {
                                                ComposerKt.sourceInformation(composer3, "C522@24251L129:Slider.kt#uh7d8r");
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1908709951, i26, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:522)");
                                                }
                                                SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z2, 0L, composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }, startRestartGroup, 54);
                                    } else {
                                        function34 = composableLambda;
                                        composableLambda2 = function32;
                                    }
                                    if (i17 == 0) {
                                        function35 = composableLambda2;
                                        composableLambda3 = ComposableLambdaKt.rememberComposableLambda(-429193201, z3, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$9
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer3, Integer num) {
                                                invoke(rangeSliderState, composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(RangeSliderState rangeSliderState, Composer composer3, int i26) {
                                                ComposerKt.sourceInformation(composer3, "C529@24485L124:Slider.kt#uh7d8r");
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-429193201, i26, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:529)");
                                                }
                                                SliderDefaults.INSTANCE.m2649Track4EFweAY(rangeSliderState, (Modifier) null, z2, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i26 & 14) | 100663296, 242);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }, startRestartGroup, 54);
                                    } else {
                                        function35 = composableLambda2;
                                        composableLambda3 = function33;
                                    }
                                    closedFloatingPointRange3 = closedFloatingPointRange4;
                                    i5 = i19;
                                    function36 = function35;
                                    if (i18 == 0) {
                                        function37 = composableLambda3;
                                        i20 = 0;
                                    } else {
                                        i20 = i;
                                        function37 = composableLambda3;
                                    }
                                    function38 = function34;
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i4 & 16) != 0) {
                                        i5 &= -57345;
                                    }
                                    if ((i4 & 64) != 0) {
                                        i5 &= -3670017;
                                    }
                                    function38 = function3;
                                    function37 = function33;
                                    i20 = i;
                                    mutableInteractionSource4 = mutableInteractionSource3;
                                    companion = modifier2;
                                    mutableInteractionSource5 = mutableInteractionSource2;
                                    function36 = function32;
                                }
                                startRestartGroup.endDefaults();
                                Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function312 = function38;
                                if (ComposerKt.isTraceInProgress()) {
                                    z4 = z2;
                                } else {
                                    z4 = z2;
                                    ComposerKt.traceEventStart(-1048796133, i5, i25, "androidx.compose.material3.RangeSlider (Slider.kt:536)");
                                }
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661236840, "CC(remember):Slider.kt#9igjgp");
                                z5 = ((i25 & 896) != 256) | ((((57344 & i5) ^ 24576) <= 16384 && startRestartGroup.changed(closedFloatingPointRange3)) || (i5 & 24576) == 16384);
                                rememberedValue = startRestartGroup.rememberedValue();
                                if (!z5 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                    int i26 = i20;
                                    rememberedValue = new RangeSliderState(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), i26, function02, closedFloatingPointRange3);
                                    i21 = i26;
                                    startRestartGroup.updateRememberedValue(rememberedValue);
                                } else {
                                    i21 = i20;
                                }
                                RangeSliderState rangeSliderState = (RangeSliderState) rememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                rangeSliderState.setOnValueChangeFinished(function02);
                                int i27 = i21;
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661246919, "CC(remember):Slider.kt#9igjgp");
                                z6 = (i5 & 112) != 32;
                                rememberedValue2 = startRestartGroup.rememberedValue();
                                if (!z6 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue2 = (Function1) new Function1<SliderRange, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$10$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(SliderRange sliderRange) {
                                            m2664invokeIf1S1O4(sliderRange.m2678unboximpl());
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke-If1S1O4, reason: not valid java name */
                                        public final void m2664invokeIf1S1O4(long j) {
                                            function1.invoke(RangesKt.rangeTo(SliderRange.m2675getStartimpl(j), SliderRange.m2674getEndInclusiveimpl(j)));
                                        }
                                    };
                                    startRestartGroup.updateRememberedValue(rememberedValue2);
                                }
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                rangeSliderState.setOnValueChange$material3_release((Function1) rememberedValue2);
                                rangeSliderState.setActiveRangeStart(closedFloatingPointRange.getStart().floatValue());
                                rangeSliderState.setActiveRangeEnd(closedFloatingPointRange.getEndInclusive().floatValue());
                                int i28 = i5 >> 9;
                                int i29 = i25 << 21;
                                boolean z8 = z4;
                                Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function313 = function36;
                                Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function314 = function37;
                                RangeSlider(rangeSliderState, companion, z8, null, mutableInteractionSource4, mutableInteractionSource5, function312, function313, function314, startRestartGroup, ((i5 >> 3) & PointerIconCompat.TYPE_TEXT) | (57344 & i28) | (458752 & i28) | (i28 & 3670016) | (29360128 & i29) | (i29 & 234881024), 8);
                                composer2 = startRestartGroup;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                sliderColors3 = sliderColors2;
                                mutableInteractionSource6 = mutableInteractionSource5;
                                i22 = i27;
                                function39 = function314;
                                z7 = z8;
                                closedFloatingPointRange5 = closedFloatingPointRange3;
                                function03 = function02;
                                function310 = function313;
                                modifier3 = companion;
                                mutableInteractionSource7 = mutableInteractionSource4;
                                function311 = function312;
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                function311 = function3;
                                composer2 = startRestartGroup;
                                z7 = z2;
                                closedFloatingPointRange5 = closedFloatingPointRange3;
                                sliderColors3 = sliderColors2;
                                function03 = function02;
                                mutableInteractionSource7 = mutableInteractionSource3;
                                modifier3 = modifier2;
                                mutableInteractionSource6 = mutableInteractionSource2;
                                function310 = function32;
                                function39 = function33;
                                i22 = i;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$11
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

                                    public final void invoke(Composer composer3, int i30) {
                                        SliderKt.RangeSlider(closedFloatingPointRange, function1, modifier3, z7, closedFloatingPointRange5, function03, sliderColors3, mutableInteractionSource7, mutableInteractionSource6, function311, function310, function39, i22, composer3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        if ((i5 & 306783379) == 306783378) {
                        }
                        startRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                        }
                        if (i24 == 0) {
                        }
                        if (i6 != 0) {
                        }
                        if ((i4 & 16) != 0) {
                        }
                        if (i7 != 0) {
                        }
                        if ((i4 & 64) != 0) {
                        }
                        if (i8 == 0) {
                        }
                        if (i10 == 0) {
                        }
                        if (i12 == 0) {
                        }
                        if (i14 == 0) {
                        }
                        if (i17 == 0) {
                        }
                        closedFloatingPointRange3 = closedFloatingPointRange4;
                        i5 = i19;
                        function36 = function35;
                        if (i18 == 0) {
                        }
                        function38 = function34;
                        startRestartGroup.endDefaults();
                        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3122 = function38;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661236840, "CC(remember):Slider.kt#9igjgp");
                        z5 = ((i25 & 896) != 256) | ((((57344 & i5) ^ 24576) <= 16384 && startRestartGroup.changed(closedFloatingPointRange3)) || (i5 & 24576) == 16384);
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (z5) {
                        }
                        int i262 = i20;
                        rememberedValue = new RangeSliderState(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), i262, function02, closedFloatingPointRange3);
                        i21 = i262;
                        startRestartGroup.updateRememberedValue(rememberedValue);
                        RangeSliderState rangeSliderState2 = (RangeSliderState) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        rangeSliderState2.setOnValueChangeFinished(function02);
                        int i272 = i21;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661246919, "CC(remember):Slider.kt#9igjgp");
                        if ((i5 & 112) != 32) {
                        }
                        rememberedValue2 = startRestartGroup.rememberedValue();
                        if (!z6) {
                        }
                        rememberedValue2 = (Function1) new Function1<SliderRange, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$10$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SliderRange sliderRange) {
                                m2664invokeIf1S1O4(sliderRange.m2678unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke-If1S1O4, reason: not valid java name */
                            public final void m2664invokeIf1S1O4(long j) {
                                function1.invoke(RangesKt.rangeTo(SliderRange.m2675getStartimpl(j), SliderRange.m2674getEndInclusiveimpl(j)));
                            }
                        };
                        startRestartGroup.updateRememberedValue(rememberedValue2);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        rangeSliderState2.setOnValueChange$material3_release((Function1) rememberedValue2);
                        rangeSliderState2.setActiveRangeStart(closedFloatingPointRange.getStart().floatValue());
                        rangeSliderState2.setActiveRangeEnd(closedFloatingPointRange.getEndInclusive().floatValue());
                        int i282 = i5 >> 9;
                        int i292 = i25 << 21;
                        boolean z82 = z4;
                        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3132 = function36;
                        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3142 = function37;
                        RangeSlider(rangeSliderState2, companion, z82, null, mutableInteractionSource4, mutableInteractionSource5, function3122, function3132, function3142, startRestartGroup, ((i5 >> 3) & PointerIconCompat.TYPE_TEXT) | (57344 & i282) | (458752 & i282) | (i282 & 3670016) | (29360128 & i292) | (i292 & 234881024), 8);
                        composer2 = startRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        sliderColors3 = sliderColors2;
                        mutableInteractionSource6 = mutableInteractionSource5;
                        i22 = i272;
                        function39 = function3142;
                        z7 = z82;
                        closedFloatingPointRange5 = closedFloatingPointRange3;
                        function03 = function02;
                        function310 = function3132;
                        modifier3 = companion;
                        mutableInteractionSource7 = mutableInteractionSource4;
                        function311 = function3122;
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i12 = i11;
                    i13 = i4 & 1024;
                    if (i13 != 0) {
                    }
                    i16 = i4 & 2048;
                    if (i16 != 0) {
                    }
                    int i252 = i15;
                    i18 = i4 & 4096;
                    if (i18 != 0) {
                    }
                    if ((i5 & 306783379) == 306783378) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                    }
                    if (i24 == 0) {
                    }
                    if (i6 != 0) {
                    }
                    if ((i4 & 16) != 0) {
                    }
                    if (i7 != 0) {
                    }
                    if ((i4 & 64) != 0) {
                    }
                    if (i8 == 0) {
                    }
                    if (i10 == 0) {
                    }
                    if (i12 == 0) {
                    }
                    if (i14 == 0) {
                    }
                    if (i17 == 0) {
                    }
                    closedFloatingPointRange3 = closedFloatingPointRange4;
                    i5 = i19;
                    function36 = function35;
                    if (i18 == 0) {
                    }
                    function38 = function34;
                    startRestartGroup.endDefaults();
                    Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function31222 = function38;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661236840, "CC(remember):Slider.kt#9igjgp");
                    z5 = ((i252 & 896) != 256) | ((((57344 & i5) ^ 24576) <= 16384 && startRestartGroup.changed(closedFloatingPointRange3)) || (i5 & 24576) == 16384);
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (z5) {
                    }
                    int i2622 = i20;
                    rememberedValue = new RangeSliderState(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), i2622, function02, closedFloatingPointRange3);
                    i21 = i2622;
                    startRestartGroup.updateRememberedValue(rememberedValue);
                    RangeSliderState rangeSliderState22 = (RangeSliderState) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    rangeSliderState22.setOnValueChangeFinished(function02);
                    int i2722 = i21;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661246919, "CC(remember):Slider.kt#9igjgp");
                    if ((i5 & 112) != 32) {
                    }
                    rememberedValue2 = startRestartGroup.rememberedValue();
                    if (!z6) {
                    }
                    rememberedValue2 = (Function1) new Function1<SliderRange, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$10$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SliderRange sliderRange) {
                            m2664invokeIf1S1O4(sliderRange.m2678unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke-If1S1O4, reason: not valid java name */
                        public final void m2664invokeIf1S1O4(long j) {
                            function1.invoke(RangesKt.rangeTo(SliderRange.m2675getStartimpl(j), SliderRange.m2674getEndInclusiveimpl(j)));
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    rangeSliderState22.setOnValueChange$material3_release((Function1) rememberedValue2);
                    rangeSliderState22.setActiveRangeStart(closedFloatingPointRange.getStart().floatValue());
                    rangeSliderState22.setActiveRangeEnd(closedFloatingPointRange.getEndInclusive().floatValue());
                    int i2822 = i5 >> 9;
                    int i2922 = i252 << 21;
                    boolean z822 = z4;
                    Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function31322 = function36;
                    Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function31422 = function37;
                    RangeSlider(rangeSliderState22, companion, z822, null, mutableInteractionSource4, mutableInteractionSource5, function31222, function31322, function31422, startRestartGroup, ((i5 >> 3) & PointerIconCompat.TYPE_TEXT) | (57344 & i2822) | (458752 & i2822) | (i2822 & 3670016) | (29360128 & i2922) | (i2922 & 234881024), 8);
                    composer2 = startRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    sliderColors3 = sliderColors2;
                    mutableInteractionSource6 = mutableInteractionSource5;
                    i22 = i2722;
                    function39 = function31422;
                    z7 = z822;
                    closedFloatingPointRange5 = closedFloatingPointRange3;
                    function03 = function02;
                    function310 = function31322;
                    modifier3 = companion;
                    mutableInteractionSource7 = mutableInteractionSource4;
                    function311 = function31222;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                i10 = i9;
                i11 = i4 & 512;
                if (i11 == 0) {
                }
                i12 = i11;
                i13 = i4 & 1024;
                if (i13 != 0) {
                }
                i16 = i4 & 2048;
                if (i16 != 0) {
                }
                int i2522 = i15;
                i18 = i4 & 4096;
                if (i18 != 0) {
                }
                if ((i5 & 306783379) == 306783378) {
                }
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                }
                if (i24 == 0) {
                }
                if (i6 != 0) {
                }
                if ((i4 & 16) != 0) {
                }
                if (i7 != 0) {
                }
                if ((i4 & 64) != 0) {
                }
                if (i8 == 0) {
                }
                if (i10 == 0) {
                }
                if (i12 == 0) {
                }
                if (i14 == 0) {
                }
                if (i17 == 0) {
                }
                closedFloatingPointRange3 = closedFloatingPointRange4;
                i5 = i19;
                function36 = function35;
                if (i18 == 0) {
                }
                function38 = function34;
                startRestartGroup.endDefaults();
                Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function312222 = function38;
                if (ComposerKt.isTraceInProgress()) {
                }
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661236840, "CC(remember):Slider.kt#9igjgp");
                z5 = ((i2522 & 896) != 256) | ((((57344 & i5) ^ 24576) <= 16384 && startRestartGroup.changed(closedFloatingPointRange3)) || (i5 & 24576) == 16384);
                rememberedValue = startRestartGroup.rememberedValue();
                if (z5) {
                }
                int i26222 = i20;
                rememberedValue = new RangeSliderState(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), i26222, function02, closedFloatingPointRange3);
                i21 = i26222;
                startRestartGroup.updateRememberedValue(rememberedValue);
                RangeSliderState rangeSliderState222 = (RangeSliderState) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                rangeSliderState222.setOnValueChangeFinished(function02);
                int i27222 = i21;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661246919, "CC(remember):Slider.kt#9igjgp");
                if ((i5 & 112) != 32) {
                }
                rememberedValue2 = startRestartGroup.rememberedValue();
                if (!z6) {
                }
                rememberedValue2 = (Function1) new Function1<SliderRange, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$10$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SliderRange sliderRange) {
                        m2664invokeIf1S1O4(sliderRange.m2678unboximpl());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-If1S1O4, reason: not valid java name */
                    public final void m2664invokeIf1S1O4(long j) {
                        function1.invoke(RangesKt.rangeTo(SliderRange.m2675getStartimpl(j), SliderRange.m2674getEndInclusiveimpl(j)));
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue2);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                rangeSliderState222.setOnValueChange$material3_release((Function1) rememberedValue2);
                rangeSliderState222.setActiveRangeStart(closedFloatingPointRange.getStart().floatValue());
                rangeSliderState222.setActiveRangeEnd(closedFloatingPointRange.getEndInclusive().floatValue());
                int i28222 = i5 >> 9;
                int i29222 = i2522 << 21;
                boolean z8222 = z4;
                Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function313222 = function36;
                Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function314222 = function37;
                RangeSlider(rangeSliderState222, companion, z8222, null, mutableInteractionSource4, mutableInteractionSource5, function312222, function313222, function314222, startRestartGroup, ((i5 >> 3) & PointerIconCompat.TYPE_TEXT) | (57344 & i28222) | (458752 & i28222) | (i28222 & 3670016) | (29360128 & i29222) | (i29222 & 234881024), 8);
                composer2 = startRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                }
                sliderColors3 = sliderColors2;
                mutableInteractionSource6 = mutableInteractionSource5;
                i22 = i27222;
                function39 = function314222;
                z7 = z8222;
                closedFloatingPointRange5 = closedFloatingPointRange3;
                function03 = function02;
                function310 = function313222;
                modifier3 = companion;
                mutableInteractionSource7 = mutableInteractionSource4;
                function311 = function312222;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z2 = z;
            if ((i2 & 24576) == 0) {
            }
            i7 = i4 & 32;
            if (i7 != 0) {
            }
            if ((i2 & 1572864) == 0) {
            }
            i8 = i4 & 128;
            if (i8 != 0) {
            }
            i9 = i4 & 256;
            if (i9 != 0) {
            }
            i10 = i9;
            i11 = i4 & 512;
            if (i11 == 0) {
            }
            i12 = i11;
            i13 = i4 & 1024;
            if (i13 != 0) {
            }
            i16 = i4 & 2048;
            if (i16 != 0) {
            }
            int i25222 = i15;
            i18 = i4 & 4096;
            if (i18 != 0) {
            }
            if ((i5 & 306783379) == 306783378) {
            }
            startRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
            }
            if (i24 == 0) {
            }
            if (i6 != 0) {
            }
            if ((i4 & 16) != 0) {
            }
            if (i7 != 0) {
            }
            if ((i4 & 64) != 0) {
            }
            if (i8 == 0) {
            }
            if (i10 == 0) {
            }
            if (i12 == 0) {
            }
            if (i14 == 0) {
            }
            if (i17 == 0) {
            }
            closedFloatingPointRange3 = closedFloatingPointRange4;
            i5 = i19;
            function36 = function35;
            if (i18 == 0) {
            }
            function38 = function34;
            startRestartGroup.endDefaults();
            Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3122222 = function38;
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661236840, "CC(remember):Slider.kt#9igjgp");
            z5 = ((i25222 & 896) != 256) | ((((57344 & i5) ^ 24576) <= 16384 && startRestartGroup.changed(closedFloatingPointRange3)) || (i5 & 24576) == 16384);
            rememberedValue = startRestartGroup.rememberedValue();
            if (z5) {
            }
            int i262222 = i20;
            rememberedValue = new RangeSliderState(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), i262222, function02, closedFloatingPointRange3);
            i21 = i262222;
            startRestartGroup.updateRememberedValue(rememberedValue);
            RangeSliderState rangeSliderState2222 = (RangeSliderState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            rangeSliderState2222.setOnValueChangeFinished(function02);
            int i272222 = i21;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661246919, "CC(remember):Slider.kt#9igjgp");
            if ((i5 & 112) != 32) {
            }
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (!z6) {
            }
            rememberedValue2 = (Function1) new Function1<SliderRange, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$10$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SliderRange sliderRange) {
                    m2664invokeIf1S1O4(sliderRange.m2678unboximpl());
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke-If1S1O4, reason: not valid java name */
                public final void m2664invokeIf1S1O4(long j) {
                    function1.invoke(RangesKt.rangeTo(SliderRange.m2675getStartimpl(j), SliderRange.m2674getEndInclusiveimpl(j)));
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue2);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            rangeSliderState2222.setOnValueChange$material3_release((Function1) rememberedValue2);
            rangeSliderState2222.setActiveRangeStart(closedFloatingPointRange.getStart().floatValue());
            rangeSliderState2222.setActiveRangeEnd(closedFloatingPointRange.getEndInclusive().floatValue());
            int i282222 = i5 >> 9;
            int i292222 = i25222 << 21;
            boolean z82222 = z4;
            Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3132222 = function36;
            Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3142222 = function37;
            RangeSlider(rangeSliderState2222, companion, z82222, null, mutableInteractionSource4, mutableInteractionSource5, function3122222, function3132222, function3142222, startRestartGroup, ((i5 >> 3) & PointerIconCompat.TYPE_TEXT) | (57344 & i282222) | (458752 & i282222) | (i282222 & 3670016) | (29360128 & i292222) | (i292222 & 234881024), 8);
            composer2 = startRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
            }
            sliderColors3 = sliderColors2;
            mutableInteractionSource6 = mutableInteractionSource5;
            i22 = i272222;
            function39 = function3142222;
            z7 = z82222;
            closedFloatingPointRange5 = closedFloatingPointRange3;
            function03 = function02;
            function310 = function3132222;
            modifier3 = companion;
            mutableInteractionSource7 = mutableInteractionSource4;
            function311 = function3122222;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i6 = i4 & 8;
        if (i6 == 0) {
        }
        z2 = z;
        if ((i2 & 24576) == 0) {
        }
        i7 = i4 & 32;
        if (i7 != 0) {
        }
        if ((i2 & 1572864) == 0) {
        }
        i8 = i4 & 128;
        if (i8 != 0) {
        }
        i9 = i4 & 256;
        if (i9 != 0) {
        }
        i10 = i9;
        i11 = i4 & 512;
        if (i11 == 0) {
        }
        i12 = i11;
        i13 = i4 & 1024;
        if (i13 != 0) {
        }
        i16 = i4 & 2048;
        if (i16 != 0) {
        }
        int i252222 = i15;
        i18 = i4 & 4096;
        if (i18 != 0) {
        }
        if ((i5 & 306783379) == 306783378) {
        }
        startRestartGroup.startDefaults();
        if ((i2 & 1) != 0) {
        }
        if (i24 == 0) {
        }
        if (i6 != 0) {
        }
        if ((i4 & 16) != 0) {
        }
        if (i7 != 0) {
        }
        if ((i4 & 64) != 0) {
        }
        if (i8 == 0) {
        }
        if (i10 == 0) {
        }
        if (i12 == 0) {
        }
        if (i14 == 0) {
        }
        if (i17 == 0) {
        }
        closedFloatingPointRange3 = closedFloatingPointRange4;
        i5 = i19;
        function36 = function35;
        if (i18 == 0) {
        }
        function38 = function34;
        startRestartGroup.endDefaults();
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function31222222 = function38;
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661236840, "CC(remember):Slider.kt#9igjgp");
        z5 = ((i252222 & 896) != 256) | ((((57344 & i5) ^ 24576) <= 16384 && startRestartGroup.changed(closedFloatingPointRange3)) || (i5 & 24576) == 16384);
        rememberedValue = startRestartGroup.rememberedValue();
        if (z5) {
        }
        int i2622222 = i20;
        rememberedValue = new RangeSliderState(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), i2622222, function02, closedFloatingPointRange3);
        i21 = i2622222;
        startRestartGroup.updateRememberedValue(rememberedValue);
        RangeSliderState rangeSliderState22222 = (RangeSliderState) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        rangeSliderState22222.setOnValueChangeFinished(function02);
        int i2722222 = i21;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661246919, "CC(remember):Slider.kt#9igjgp");
        if ((i5 & 112) != 32) {
        }
        rememberedValue2 = startRestartGroup.rememberedValue();
        if (!z6) {
        }
        rememberedValue2 = (Function1) new Function1<SliderRange, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$10$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SliderRange sliderRange) {
                m2664invokeIf1S1O4(sliderRange.m2678unboximpl());
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-If1S1O4, reason: not valid java name */
            public final void m2664invokeIf1S1O4(long j) {
                function1.invoke(RangesKt.rangeTo(SliderRange.m2675getStartimpl(j), SliderRange.m2674getEndInclusiveimpl(j)));
            }
        };
        startRestartGroup.updateRememberedValue(rememberedValue2);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        rangeSliderState22222.setOnValueChange$material3_release((Function1) rememberedValue2);
        rangeSliderState22222.setActiveRangeStart(closedFloatingPointRange.getStart().floatValue());
        rangeSliderState22222.setActiveRangeEnd(closedFloatingPointRange.getEndInclusive().floatValue());
        int i2822222 = i5 >> 9;
        int i2922222 = i252222 << 21;
        boolean z822222 = z4;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function31322222 = function36;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function31422222 = function37;
        RangeSlider(rangeSliderState22222, companion, z822222, null, mutableInteractionSource4, mutableInteractionSource5, function31222222, function31322222, function31422222, startRestartGroup, ((i5 >> 3) & PointerIconCompat.TYPE_TEXT) | (57344 & i2822222) | (458752 & i2822222) | (i2822222 & 3670016) | (29360128 & i2922222) | (i2922222 & 234881024), 8);
        composer2 = startRestartGroup;
        if (ComposerKt.isTraceInProgress()) {
        }
        sliderColors3 = sliderColors2;
        mutableInteractionSource6 = mutableInteractionSource5;
        i22 = i2722222;
        function39 = function31422222;
        z7 = z822222;
        closedFloatingPointRange5 = closedFloatingPointRange3;
        function03 = function02;
        function310 = function31322222;
        modifier3 = companion;
        mutableInteractionSource7 = mutableInteractionSource4;
        function311 = function31222222;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void RangeSlider(final RangeSliderState rangeSliderState, Modifier modifier, boolean z, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function32, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function33, Composer composer, final int i, final int i2) {
        RangeSliderState rangeSliderState2;
        int i3;
        Modifier modifier2;
        int i4;
        final boolean z2;
        final SliderColors sliderColors2;
        int i5;
        MutableInteractionSource mutableInteractionSource3;
        int i6;
        final MutableInteractionSource mutableInteractionSource4;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        final MutableInteractionSource mutableInteractionSource5;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> rememberComposableLambda;
        Modifier modifier3;
        MutableInteractionSource mutableInteractionSource6;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function34;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function35;
        Composer composer2;
        final SliderColors sliderColors3;
        final Modifier modifier4;
        final boolean z3;
        final MutableInteractionSource mutableInteractionSource7;
        final MutableInteractionSource mutableInteractionSource8;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function36;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function37;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function38;
        int i12;
        Composer startRestartGroup = composer.startRestartGroup(511405654);
        ComposerKt.sourceInformation(startRestartGroup, "C(RangeSlider)P(7,4,1!1,5!1,6)616@28157L8,617@28222L39,618@28316L39,619@28414L162,626@28633L160,633@28847L175,643@29089L295:Slider.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            rangeSliderState2 = rangeSliderState;
        } else {
            rangeSliderState2 = rangeSliderState;
            if ((i & 6) == 0) {
                i3 = (startRestartGroup.changedInstance(rangeSliderState2) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        int i13 = i2 & 2;
        if (i13 != 0) {
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
                            i12 = 2048;
                            i3 |= i12;
                        }
                    } else {
                        sliderColors2 = sliderColors;
                    }
                    i12 = 1024;
                    i3 |= i12;
                } else {
                    sliderColors2 = sliderColors;
                }
                i5 = i2 & 16;
                if (i5 != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    mutableInteractionSource3 = mutableInteractionSource;
                    i3 |= startRestartGroup.changed(mutableInteractionSource3) ? 16384 : 8192;
                    i6 = i2 & 32;
                    if (i6 == 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((196608 & i) == 0) {
                        mutableInteractionSource4 = mutableInteractionSource2;
                        i3 |= startRestartGroup.changed(mutableInteractionSource4) ? 131072 : 65536;
                        i7 = i2 & 64;
                        if (i7 != 0) {
                            i3 |= 1572864;
                        } else if ((i & 1572864) == 0) {
                            i3 |= startRestartGroup.changedInstance(function3) ? 1048576 : 524288;
                        }
                        i8 = i2 & 128;
                        if (i8 != 0) {
                            i3 |= 12582912;
                        } else if ((i & 12582912) == 0) {
                            i9 = i8;
                            i3 |= startRestartGroup.changedInstance(function32) ? 8388608 : 4194304;
                            i10 = i2 & 256;
                            if (i10 == 0) {
                                i3 |= 100663296;
                            } else if ((i & 100663296) == 0) {
                                i11 = i10;
                                i3 |= startRestartGroup.changedInstance(function33) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                if ((i3 & 38347923) == 38347922 || !startRestartGroup.getSkipping()) {
                                    startRestartGroup.startDefaults();
                                    if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                        if (i13 != 0) {
                                            modifier2 = Modifier.INSTANCE;
                                        }
                                        if (i4 != 0) {
                                            z2 = true;
                                        }
                                        if ((i2 & 8) != 0) {
                                            i3 &= -7169;
                                            sliderColors2 = SliderDefaults.INSTANCE.colors(startRestartGroup, 6);
                                        }
                                        if (i5 != 0) {
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661349858, "CC(remember):Slider.kt#9igjgp");
                                            Object rememberedValue = startRestartGroup.rememberedValue();
                                            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue = InteractionSourceKt.MutableInteractionSource();
                                                startRestartGroup.updateRememberedValue(rememberedValue);
                                            }
                                            mutableInteractionSource5 = (MutableInteractionSource) rememberedValue;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        } else {
                                            mutableInteractionSource5 = mutableInteractionSource3;
                                        }
                                        if (i6 != 0) {
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1661352866, "CC(remember):Slider.kt#9igjgp");
                                            Object rememberedValue2 = startRestartGroup.rememberedValue();
                                            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue2 = InteractionSourceKt.MutableInteractionSource();
                                                startRestartGroup.updateRememberedValue(rememberedValue2);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            mutableInteractionSource4 = (MutableInteractionSource) rememberedValue2;
                                        }
                                        ComposableLambda rememberComposableLambda2 = i7 != 0 ? ComposableLambdaKt.rememberComposableLambda(1884205643, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$14
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState3, Composer composer3, Integer num) {
                                                invoke(rangeSliderState3, composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(RangeSliderState rangeSliderState3, Composer composer3, int i14) {
                                                ComposerKt.sourceInformation(composer3, "C620@28439L131:Slider.kt#uh7d8r");
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1884205643, i14, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:620)");
                                                }
                                                SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z2, 0L, composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }, startRestartGroup, 54) : function3;
                                        ComposableLambda rememberComposableLambda3 = i9 != 0 ? ComposableLambdaKt.rememberComposableLambda(1016457138, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$15
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState3, Composer composer3, Integer num) {
                                                invoke(rangeSliderState3, composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(RangeSliderState rangeSliderState3, Composer composer3, int i14) {
                                                ComposerKt.sourceInformation(composer3, "C627@28658L129:Slider.kt#uh7d8r");
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1016457138, i14, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:627)");
                                                }
                                                SliderDefaults.INSTANCE.m2648Thumb9LiSoMs(MutableInteractionSource.this, null, sliderColors2, z2, 0L, composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }, startRestartGroup, 54) : function32;
                                        rememberComposableLambda = i11 != 0 ? ComposableLambdaKt.rememberComposableLambda(-1617375262, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$16
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(3);
                                            }

                                            @Override // kotlin.jvm.functions.Function3
                                            public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState3, Composer composer3, Integer num) {
                                                invoke(rangeSliderState3, composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(RangeSliderState rangeSliderState3, Composer composer3, int i14) {
                                                ComposerKt.sourceInformation(composer3, "C634@28892L124:Slider.kt#uh7d8r");
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1617375262, i14, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:634)");
                                                }
                                                SliderDefaults.INSTANCE.m2649Track4EFweAY(rangeSliderState3, (Modifier) null, z2, sliderColors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, composer3, (i14 & 14) | 100663296, 242);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }, startRestartGroup, 54) : function33;
                                        modifier3 = modifier2;
                                        mutableInteractionSource6 = mutableInteractionSource5;
                                        function34 = rememberComposableLambda2;
                                        function35 = rememberComposableLambda3;
                                    } else {
                                        startRestartGroup.skipToGroupEnd();
                                        if ((i2 & 8) != 0) {
                                            i3 &= -7169;
                                        }
                                        function34 = function3;
                                        function35 = function32;
                                        rememberComposableLambda = function33;
                                        modifier3 = modifier2;
                                        mutableInteractionSource6 = mutableInteractionSource3;
                                    }
                                    MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource4;
                                    boolean z4 = z2;
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(511405654, i3, -1, "androidx.compose.material3.RangeSlider (Slider.kt:640)");
                                    }
                                    if (rangeSliderState2.getSteps() < 0) {
                                        throw new IllegalArgumentException("steps should be >= 0".toString());
                                    }
                                    int i14 = i3 >> 3;
                                    composer2 = startRestartGroup;
                                    RangeSliderImpl(modifier3, rangeSliderState2, z4, mutableInteractionSource6, mutableInteractionSource9, function34, function35, rememberComposableLambda, composer2, (i3 & 896) | (i14 & 14) | ((i3 << 3) & 112) | (i14 & 7168) | (57344 & i14) | (458752 & i14) | (3670016 & i14) | (i14 & 29360128));
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    sliderColors3 = sliderColors2;
                                    modifier4 = modifier3;
                                    z3 = z4;
                                    mutableInteractionSource7 = mutableInteractionSource6;
                                    mutableInteractionSource8 = mutableInteractionSource9;
                                    function36 = function34;
                                    function37 = function35;
                                    function38 = rememberComposableLambda;
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    function37 = function32;
                                    composer2 = startRestartGroup;
                                    modifier4 = modifier2;
                                    z3 = z2;
                                    sliderColors3 = sliderColors2;
                                    mutableInteractionSource7 = mutableInteractionSource3;
                                    mutableInteractionSource8 = mutableInteractionSource4;
                                    function36 = function3;
                                    function38 = function33;
                                }
                                ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSlider$18
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

                                        public final void invoke(Composer composer3, int i15) {
                                            SliderKt.RangeSlider(RangeSliderState.this, modifier4, z3, sliderColors3, mutableInteractionSource7, mutableInteractionSource8, function36, function37, function38, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            i11 = i10;
                            if ((i3 & 38347923) == 38347922) {
                            }
                            startRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                            }
                            if (i13 != 0) {
                            }
                            if (i4 != 0) {
                            }
                            if ((i2 & 8) != 0) {
                            }
                            if (i5 != 0) {
                            }
                            if (i6 != 0) {
                            }
                            if (i7 != 0) {
                            }
                            if (i9 != 0) {
                            }
                            if (i11 != 0) {
                            }
                            modifier3 = modifier2;
                            mutableInteractionSource6 = mutableInteractionSource5;
                            function34 = rememberComposableLambda2;
                            function35 = rememberComposableLambda3;
                            MutableInteractionSource mutableInteractionSource92 = mutableInteractionSource4;
                            boolean z42 = z2;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            if (rangeSliderState2.getSteps() < 0) {
                            }
                        }
                        i9 = i8;
                        i10 = i2 & 256;
                        if (i10 == 0) {
                        }
                        i11 = i10;
                        if ((i3 & 38347923) == 38347922) {
                        }
                        startRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                        }
                        if (i13 != 0) {
                        }
                        if (i4 != 0) {
                        }
                        if ((i2 & 8) != 0) {
                        }
                        if (i5 != 0) {
                        }
                        if (i6 != 0) {
                        }
                        if (i7 != 0) {
                        }
                        if (i9 != 0) {
                        }
                        if (i11 != 0) {
                        }
                        modifier3 = modifier2;
                        mutableInteractionSource6 = mutableInteractionSource5;
                        function34 = rememberComposableLambda2;
                        function35 = rememberComposableLambda3;
                        MutableInteractionSource mutableInteractionSource922 = mutableInteractionSource4;
                        boolean z422 = z2;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (rangeSliderState2.getSteps() < 0) {
                        }
                    }
                    mutableInteractionSource4 = mutableInteractionSource2;
                    i7 = i2 & 64;
                    if (i7 != 0) {
                    }
                    i8 = i2 & 128;
                    if (i8 != 0) {
                    }
                    i9 = i8;
                    i10 = i2 & 256;
                    if (i10 == 0) {
                    }
                    i11 = i10;
                    if ((i3 & 38347923) == 38347922) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                    }
                    if (i13 != 0) {
                    }
                    if (i4 != 0) {
                    }
                    if ((i2 & 8) != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if (i6 != 0) {
                    }
                    if (i7 != 0) {
                    }
                    if (i9 != 0) {
                    }
                    if (i11 != 0) {
                    }
                    modifier3 = modifier2;
                    mutableInteractionSource6 = mutableInteractionSource5;
                    function34 = rememberComposableLambda2;
                    function35 = rememberComposableLambda3;
                    MutableInteractionSource mutableInteractionSource9222 = mutableInteractionSource4;
                    boolean z4222 = z2;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    if (rangeSliderState2.getSteps() < 0) {
                    }
                }
                mutableInteractionSource3 = mutableInteractionSource;
                i6 = i2 & 32;
                if (i6 == 0) {
                }
                mutableInteractionSource4 = mutableInteractionSource2;
                i7 = i2 & 64;
                if (i7 != 0) {
                }
                i8 = i2 & 128;
                if (i8 != 0) {
                }
                i9 = i8;
                i10 = i2 & 256;
                if (i10 == 0) {
                }
                i11 = i10;
                if ((i3 & 38347923) == 38347922) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i13 != 0) {
                }
                if (i4 != 0) {
                }
                if ((i2 & 8) != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                if (i7 != 0) {
                }
                if (i9 != 0) {
                }
                if (i11 != 0) {
                }
                modifier3 = modifier2;
                mutableInteractionSource6 = mutableInteractionSource5;
                function34 = rememberComposableLambda2;
                function35 = rememberComposableLambda3;
                MutableInteractionSource mutableInteractionSource92222 = mutableInteractionSource4;
                boolean z42222 = z2;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                if (rangeSliderState2.getSteps() < 0) {
                }
            }
            z2 = z;
            if ((i & 3072) == 0) {
            }
            i5 = i2 & 16;
            if (i5 != 0) {
            }
            mutableInteractionSource3 = mutableInteractionSource;
            i6 = i2 & 32;
            if (i6 == 0) {
            }
            mutableInteractionSource4 = mutableInteractionSource2;
            i7 = i2 & 64;
            if (i7 != 0) {
            }
            i8 = i2 & 128;
            if (i8 != 0) {
            }
            i9 = i8;
            i10 = i2 & 256;
            if (i10 == 0) {
            }
            i11 = i10;
            if ((i3 & 38347923) == 38347922) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i13 != 0) {
            }
            if (i4 != 0) {
            }
            if ((i2 & 8) != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            if (i7 != 0) {
            }
            if (i9 != 0) {
            }
            if (i11 != 0) {
            }
            modifier3 = modifier2;
            mutableInteractionSource6 = mutableInteractionSource5;
            function34 = rememberComposableLambda2;
            function35 = rememberComposableLambda3;
            MutableInteractionSource mutableInteractionSource922222 = mutableInteractionSource4;
            boolean z422222 = z2;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            if (rangeSliderState2.getSteps() < 0) {
            }
        }
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 == 0) {
        }
        z2 = z;
        if ((i & 3072) == 0) {
        }
        i5 = i2 & 16;
        if (i5 != 0) {
        }
        mutableInteractionSource3 = mutableInteractionSource;
        i6 = i2 & 32;
        if (i6 == 0) {
        }
        mutableInteractionSource4 = mutableInteractionSource2;
        i7 = i2 & 64;
        if (i7 != 0) {
        }
        i8 = i2 & 128;
        if (i8 != 0) {
        }
        i9 = i8;
        i10 = i2 & 256;
        if (i10 == 0) {
        }
        i11 = i10;
        if ((i3 & 38347923) == 38347922) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i13 != 0) {
        }
        if (i4 != 0) {
        }
        if ((i2 & 8) != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        if (i7 != 0) {
        }
        if (i9 != 0) {
        }
        if (i11 != 0) {
        }
        modifier3 = modifier2;
        mutableInteractionSource6 = mutableInteractionSource5;
        function34 = rememberComposableLambda2;
        function35 = rememberComposableLambda3;
        MutableInteractionSource mutableInteractionSource9222222 = mutableInteractionSource4;
        boolean z4222222 = z2;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        if (rangeSliderState2.getSteps() < 0) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SliderImpl(final Modifier modifier, final SliderState sliderState, boolean z, MutableInteractionSource mutableInteractionSource, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function32, Composer composer, final int i) {
        int i2;
        boolean z2;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function33;
        final MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function34 = function32;
        Composer startRestartGroup = composer.startRestartGroup(1390990089);
        ComposerKt.sourceInformation(startRestartGroup, "C(SliderImpl)P(2,3)665@29717L7,673@30073L28,698@30935L1093,678@30197L1831:Slider.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(sliderState) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= startRestartGroup.changed(mutableInteractionSource2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= startRestartGroup.changedInstance(function3) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= startRestartGroup.changedInstance(function34) ? 131072 : 65536;
        }
        if ((74899 & i2) != 74898 || !startRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1390990089, i2, -1, "androidx.compose.material3.SliderImpl (Slider.kt:664)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            sliderState.setRtl$material3_release(consume == LayoutDirection.Rtl);
            Modifier sliderTapModifier = sliderTapModifier(Modifier.INSTANCE, sliderState, mutableInteractionSource2, z);
            Modifier.Companion companion = Modifier.INSTANCE;
            Orientation orientation = Orientation.Horizontal;
            boolean isRtl = sliderState.getIsRtl();
            boolean isDragging$material3_release = sliderState.isDragging$material3_release();
            Modifier.Companion companion2 = companion;
            SliderState sliderState2 = sliderState;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 174725888, "CC(remember):Slider.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(sliderState);
            SliderKt$SliderImpl$drag$1$1 rememberedValue = startRestartGroup.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new SliderKt$SliderImpl$drag$1$1(sliderState, null);
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            z2 = z;
            int i3 = i2;
            Modifier draggable$default = DraggableKt.draggable$default(companion2, sliderState2, orientation, z2, mutableInteractionSource, isDragging$material3_release, null, (Function3) rememberedValue, isRtl, 32, null);
            mutableInteractionSource2 = mutableInteractionSource;
            Modifier then = FocusableKt.focusable(sliderSemantics(SizeKt.m926requiredSizeInqDBjuR0$default(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier), ThumbWidth, TrackHeight, 0.0f, 0.0f, 12, null), sliderState, z2), z2, mutableInteractionSource2).then(sliderTapModifier).then(draggable$default);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 174754537, "CC(remember):Slider.kt#9igjgp");
            boolean changedInstance2 = startRestartGroup.changedInstance(sliderState);
            MeasurePolicy rememberedValue2 = startRestartGroup.rememberedValue();
            if (changedInstance2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = new MeasurePolicy() { // from class: androidx.compose.material3.SliderKt$SliderImpl$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i4) {
                        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i4);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i4) {
                        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i4);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i4) {
                        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i4);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i4) {
                        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i4);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* renamed from: measure-3p2s80s */
                    public final MeasureResult mo80measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        int size = list.size();
                        int i4 = 0;
                        for (int i5 = 0; i5 < size; i5++) {
                            Measurable measurable = list.get(i5);
                            if (LayoutIdKt.getLayoutId(measurable) == SliderComponents.THUMB) {
                                long j2 = j;
                                final Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(j2);
                                int size2 = list.size();
                                while (i4 < size2) {
                                    Measurable measurable2 = list.get(i4);
                                    if (LayoutIdKt.getLayoutId(measurable2) == SliderComponents.TRACK) {
                                        final Placeable mo6055measureBRTryo02 = measurable2.mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(ConstraintsKt.m7362offsetNN6EwU$default(j2, -mo6055measureBRTryo0.getWidth(), 0, 2, null), 0, 0, 0, 0, 11, null));
                                        int width = mo6055measureBRTryo0.getWidth() + mo6055measureBRTryo02.getWidth();
                                        int max = Math.max(mo6055measureBRTryo02.getHeight(), mo6055measureBRTryo0.getHeight());
                                        SliderState.this.updateDimensions$material3_release(mo6055measureBRTryo02.getHeight(), width);
                                        final int width2 = mo6055measureBRTryo0.getWidth() / 2;
                                        final int roundToInt = MathKt.roundToInt(mo6055measureBRTryo02.getWidth() * SliderState.this.getCoercedValueAsFraction$material3_release());
                                        final int height = (max - mo6055measureBRTryo02.getHeight()) / 2;
                                        final int height2 = (max - mo6055measureBRTryo0.getHeight()) / 2;
                                        return MeasureScope.CC.layout$default(measureScope, width, max, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.SliderKt$SliderImpl$2$1.1
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
                                                Placeable.PlacementScope.placeRelative$default(placementScope, Placeable.this, width2, height, 0.0f, 4, null);
                                                Placeable.PlacementScope.placeRelative$default(placementScope, mo6055measureBRTryo0, roundToInt, height2, 0.0f, 4, null);
                                            }
                                        }, 4, null);
                                    }
                                    i4++;
                                    j2 = j;
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue2);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, then);
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -610171494, "C682@30354L85,680@30227L271,688@30511L74:Slider.kt#uh7d8r");
            Modifier wrapContentWidth$default = SizeKt.wrapContentWidth$default(LayoutIdKt.layoutId(Modifier.INSTANCE, SliderComponents.THUMB), null, false, 3, null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 534510168, "CC(remember):Slider.kt#9igjgp");
            boolean changedInstance3 = startRestartGroup.changedInstance(sliderState);
            Object rememberedValue3 = startRestartGroup.rememberedValue();
            if (changedInstance3 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                rememberedValue3 = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.material3.SliderKt$SliderImpl$1$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                        m2667invokeozmzZPI(intSize.m7578unboximpl());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-ozmzZPI, reason: not valid java name */
                    public final void m2667invokeozmzZPI(long j) {
                        SliderState.this.setThumbWidth$material3_release(IntSize.m7574getWidthimpl(j));
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier onSizeChanged = OnRemeasuredModifierKt.onSizeChanged(wrapContentWidth$default, (Function1) rememberedValue3);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, onSizeChanged);
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -469300363, "C686@30472L12:Slider.kt#uh7d8r");
            int i4 = (i3 >> 3) & 14;
            function33 = function3;
            function33.invoke(sliderState, startRestartGroup, Integer.valueOf(((i3 >> 9) & 112) | i4));
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, SliderComponents.TRACK);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(startRestartGroup, layoutId);
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -469202155, "C688@30571L12:Slider.kt#uh7d8r");
            function34 = function32;
            function34.invoke(sliderState, startRestartGroup, Integer.valueOf(((i3 >> 12) & 112) | i4));
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
            function33 = function3;
            z2 = z;
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            final boolean z3 = z2;
            final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function35 = function33;
            final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function36 = function34;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$SliderImpl$3
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

                public final void invoke(Composer composer2, int i5) {
                    SliderKt.SliderImpl(Modifier.this, sliderState, z3, mutableInteractionSource2, function35, function36, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RangeSliderImpl(final Modifier modifier, final RangeSliderState rangeSliderState, final boolean z, final MutableInteractionSource mutableInteractionSource, final MutableInteractionSource mutableInteractionSource2, final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function32, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function33, Composer composer, final int i) {
        Modifier modifier2;
        int i2;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function34;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function35 = function33;
        Composer startRestartGroup = composer.startRestartGroup(-1411725677);
        ComposerKt.sourceInformation(startRestartGroup, "C(RangeSliderImpl)P(3,6!1,4!1,5)736@32503L7,746@32756L35,747@32824L33,784@34404L2230,749@32863L3771:Slider.kt#uh7d8r");
        if ((i & 6) == 0) {
            modifier2 = modifier;
            i2 = (startRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(rangeSliderState) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= startRestartGroup.changed(mutableInteractionSource) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= startRestartGroup.changed(mutableInteractionSource2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= startRestartGroup.changedInstance(function3) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= startRestartGroup.changedInstance(function32) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= startRestartGroup.changedInstance(function35) ? 8388608 : 4194304;
        }
        if ((4793491 & i2) != 4793490 || !startRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1411725677, i2, -1, "androidx.compose.material3.RangeSliderImpl (Slider.kt:735)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            rangeSliderState.setRtl$material3_release(consume == LayoutDirection.Rtl);
            Modifier rangeSliderPressDragModifier = rangeSliderPressDragModifier(Modifier.INSTANCE, rangeSliderState, mutableInteractionSource, mutableInteractionSource2, z);
            Strings.Companion companion = Strings.INSTANCE;
            final String m3160getString2EP1pXo = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.range_start), startRestartGroup, 0);
            Strings.Companion companion2 = Strings.INSTANCE;
            final String m3160getString2EP1pXo2 = Strings_androidKt.m3160getString2EP1pXo(Strings.m3090constructorimpl(androidx.compose.ui.R.string.range_end), startRestartGroup, 0);
            Modifier then = SizeKt.m926requiredSizeInqDBjuR0$default(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier2), ThumbWidth, TrackHeight, 0.0f, 0.0f, 12, null).then(rangeSliderPressDragModifier);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1662324209, "CC(remember):Slider.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(rangeSliderState);
            MeasurePolicy rememberedValue = startRestartGroup.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* renamed from: measure-3p2s80s */
                    public final MeasureResult mo80measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        long j2 = j;
                        int size = list.size();
                        int i3 = 0;
                        int i4 = 0;
                        while (i4 < size) {
                            Measurable measurable = list.get(i4);
                            if (LayoutIdKt.getLayoutId(measurable) == RangeSliderComponents.STARTTHUMB) {
                                final Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(j2);
                                int size2 = list.size();
                                int i5 = 0;
                                while (i5 < size2) {
                                    Measurable measurable2 = list.get(i5);
                                    if (LayoutIdKt.getLayoutId(measurable2) == RangeSliderComponents.ENDTHUMB) {
                                        final Placeable mo6055measureBRTryo02 = measurable2.mo6055measureBRTryo0(j2);
                                        int size3 = list.size();
                                        while (i3 < size3) {
                                            Measurable measurable3 = list.get(i3);
                                            if (LayoutIdKt.getLayoutId(measurable3) == RangeSliderComponents.TRACK) {
                                                final Placeable mo6055measureBRTryo03 = measurable3.mo6055measureBRTryo0(Constraints.m7332copyZbe2FdA$default(ConstraintsKt.m7362offsetNN6EwU$default(j2, (-(mo6055measureBRTryo0.getWidth() + mo6055measureBRTryo02.getWidth())) / 2, 0, 2, null), 0, 0, 0, 0, 11, null));
                                                int width = mo6055measureBRTryo03.getWidth() + ((mo6055measureBRTryo0.getWidth() + mo6055measureBRTryo02.getWidth()) / 2);
                                                int max = Math.max(mo6055measureBRTryo03.getHeight(), Math.max(mo6055measureBRTryo0.getHeight(), mo6055measureBRTryo02.getHeight()));
                                                RangeSliderState.this.setTrackHeight$material3_release(mo6055measureBRTryo03.getHeight());
                                                RangeSliderState.this.setTotalWidth$material3_release(width);
                                                RangeSliderState.this.updateMinMaxPx$material3_release();
                                                final int width2 = mo6055measureBRTryo0.getWidth() / 2;
                                                final int roundToInt = MathKt.roundToInt(mo6055measureBRTryo03.getWidth() * RangeSliderState.this.getCoercedActiveRangeStartAsFraction$material3_release());
                                                final int roundToInt2 = MathKt.roundToInt((mo6055measureBRTryo03.getWidth() * RangeSliderState.this.getCoercedActiveRangeEndAsFraction$material3_release()) + ((mo6055measureBRTryo0.getWidth() - mo6055measureBRTryo02.getWidth()) / 2));
                                                final int height = (max - mo6055measureBRTryo03.getHeight()) / 2;
                                                final int height2 = (max - mo6055measureBRTryo0.getHeight()) / 2;
                                                final int height3 = (max - mo6055measureBRTryo02.getHeight()) / 2;
                                                return MeasureScope.CC.layout$default(measureScope, width, max, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$2$1.1
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
                                                        Placeable.PlacementScope.placeRelative$default(placementScope, Placeable.this, width2, height, 0.0f, 4, null);
                                                        Placeable.PlacementScope.placeRelative$default(placementScope, mo6055measureBRTryo0, roundToInt, height2, 0.0f, 4, null);
                                                        Placeable.PlacementScope.placeRelative$default(placementScope, mo6055measureBRTryo02, roundToInt2, height3, 0.0f, 4, null);
                                                    }
                                                }, 4, null);
                                            }
                                            i3++;
                                            j2 = j;
                                        }
                                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                    }
                                    i5++;
                                    j2 = j;
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                            i4++;
                            j2 = j;
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, then);
            int i3 = i2;
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2110426496, "C755@33080L46,757@33259L100,751@32893L598,768@33689L44,770@33864L98,764@33504L586,777@34103L79:Slider.kt#uh7d8r");
            Modifier wrapContentWidth$default = SizeKt.wrapContentWidth$default(LayoutIdKt.layoutId(Modifier.INSTANCE, RangeSliderComponents.STARTTHUMB), null, false, 3, null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1176461672, "CC(remember):Slider.kt#9igjgp");
            boolean changedInstance2 = startRestartGroup.changedInstance(rangeSliderState);
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (changedInstance2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$1$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                        m2665invokeozmzZPI(intSize.m7578unboximpl());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-ozmzZPI, reason: not valid java name */
                    public final void m2665invokeozmzZPI(long j) {
                        RangeSliderState.this.setStartThumbWidth$material3_release(IntSize.m7574getWidthimpl(j));
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier rangeSliderStartThumbSemantics = rangeSliderStartThumbSemantics(OnRemeasuredModifierKt.onSizeChanged(wrapContentWidth$default, (Function1) rememberedValue2), rangeSliderState, z);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1176467454, "CC(remember):Slider.kt#9igjgp");
            boolean changed = startRestartGroup.changed(m3160getString2EP1pXo);
            Object rememberedValue3 = startRestartGroup.rememberedValue();
            if (changed || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                rememberedValue3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$1$2$1
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
                        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, m3160getString2EP1pXo);
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier focusable = FocusableKt.focusable(SemanticsModifierKt.semantics(rangeSliderStartThumbSemantics, true, (Function1) rememberedValue3), z, mutableInteractionSource);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, focusable);
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 259534201, "C762@33460L17:Slider.kt#uh7d8r");
            int i4 = (i3 >> 3) & 14;
            function3.invoke(rangeSliderState, startRestartGroup, Integer.valueOf(((i3 >> 12) & 112) | i4));
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier wrapContentWidth$default2 = SizeKt.wrapContentWidth$default(LayoutIdKt.layoutId(Modifier.INSTANCE, RangeSliderComponents.ENDTHUMB), null, false, 3, null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1176481158, "CC(remember):Slider.kt#9igjgp");
            boolean changedInstance3 = startRestartGroup.changedInstance(rangeSliderState);
            Object rememberedValue4 = startRestartGroup.rememberedValue();
            if (changedInstance3 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                rememberedValue4 = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$1$4$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                        m2666invokeozmzZPI(intSize.m7578unboximpl());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-ozmzZPI, reason: not valid java name */
                    public final void m2666invokeozmzZPI(long j) {
                        RangeSliderState.this.setEndThumbWidth$material3_release(IntSize.m7574getWidthimpl(j));
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier rangeSliderEndThumbSemantics = rangeSliderEndThumbSemantics(OnRemeasuredModifierKt.onSizeChanged(wrapContentWidth$default2, (Function1) rememberedValue4), rangeSliderState, z);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1176486812, "CC(remember):Slider.kt#9igjgp");
            boolean changed2 = startRestartGroup.changed(m3160getString2EP1pXo2);
            Object rememberedValue5 = startRestartGroup.rememberedValue();
            if (changed2 || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                rememberedValue5 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$1$5$1
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
                        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, m3160getString2EP1pXo2);
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier focusable2 = FocusableKt.focusable(SemanticsModifierKt.semantics(rangeSliderEndThumbSemantics, true, (Function1) rememberedValue5), z, mutableInteractionSource2);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(startRestartGroup, focusable2);
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
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 260130331, "C775@34061L15:Slider.kt#uh7d8r");
            function34 = function32;
            function34.invoke(rangeSliderState, startRestartGroup, Integer.valueOf(((i3 >> 15) & 112) | i4));
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier layoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, RangeSliderComponents.TRACK);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(startRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(startRestartGroup, layoutId);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(constructor4);
            } else {
                startRestartGroup.useNode();
            }
            Composer m3905constructorimpl4 = Updater.m3905constructorimpl(startRestartGroup);
            Updater.m3912setimpl(m3905constructorimpl4, maybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3912setimpl(m3905constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (m3905constructorimpl4.getInserting() || !Intrinsics.areEqual(m3905constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                m3905constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                m3905constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m3912setimpl(m3905constructorimpl4, materializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 260236382, "C777@34168L12:Slider.kt#uh7d8r");
            Integer valueOf = Integer.valueOf(((i3 >> 18) & 112) | i4);
            function35 = function33;
            function35.invoke(rangeSliderState, startRestartGroup, valueOf);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
            function34 = function32;
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function36 = function34;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$3
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

                public final void invoke(Composer composer2, int i5) {
                    SliderKt.RangeSliderImpl(Modifier.this, rangeSliderState, z, mutableInteractionSource, mutableInteractionSource2, function3, function36, function35, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* renamed from: awaitSlop-8vUncbI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object m2660awaitSlop8vUncbI(AwaitPointerEventScope awaitPointerEventScope, long j, int i, Continuation<? super Pair<PointerInputChange, Float>> continuation) {
        SliderKt$awaitSlop$1 sliderKt$awaitSlop$1;
        int i2;
        Ref.FloatRef floatRef;
        PointerInputChange pointerInputChange;
        if (continuation instanceof SliderKt$awaitSlop$1) {
            sliderKt$awaitSlop$1 = (SliderKt$awaitSlop$1) continuation;
            if ((sliderKt$awaitSlop$1.label & Integer.MIN_VALUE) != 0) {
                sliderKt$awaitSlop$1.label -= Integer.MIN_VALUE;
                SliderKt$awaitSlop$1 sliderKt$awaitSlop$12 = sliderKt$awaitSlop$1;
                Object obj = sliderKt$awaitSlop$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = sliderKt$awaitSlop$12.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.FloatRef floatRef2 = new Ref.FloatRef();
                    Function2<PointerInputChange, Float, Unit> function2 = new Function2<PointerInputChange, Float, Unit>() { // from class: androidx.compose.material3.SliderKt$awaitSlop$postPointerSlop$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange2, Float f) {
                            invoke(pointerInputChange2, f.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(PointerInputChange pointerInputChange2, float f) {
                            pointerInputChange2.consume();
                            Ref.FloatRef.this.element = f;
                        }
                    };
                    sliderKt$awaitSlop$12.L$0 = floatRef2;
                    sliderKt$awaitSlop$12.label = 1;
                    Object m3049awaitHorizontalPointerSlopOrCancellationgDDlDlE = DragGestureDetectorCopyKt.m3049awaitHorizontalPointerSlopOrCancellationgDDlDlE(awaitPointerEventScope, j, i, function2, sliderKt$awaitSlop$12);
                    if (m3049awaitHorizontalPointerSlopOrCancellationgDDlDlE == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj = m3049awaitHorizontalPointerSlopOrCancellationgDDlDlE;
                    floatRef = floatRef2;
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    floatRef = (Ref.FloatRef) sliderKt$awaitSlop$12.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                pointerInputChange = (PointerInputChange) obj;
                if (pointerInputChange == null) {
                    return TuplesKt.to(pointerInputChange, Boxing.boxFloat(floatRef.element));
                }
                return null;
            }
        }
        sliderKt$awaitSlop$1 = new SliderKt$awaitSlop$1(continuation);
        SliderKt$awaitSlop$1 sliderKt$awaitSlop$122 = sliderKt$awaitSlop$1;
        Object obj2 = sliderKt$awaitSlop$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = sliderKt$awaitSlop$122.label;
        if (i2 != 0) {
        }
        pointerInputChange = (PointerInputChange) obj2;
        if (pointerInputChange == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float[] stepsToTickFractions(int i) {
        if (i == 0) {
            return new float[0];
        }
        int i2 = i + 2;
        float[] fArr = new float[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i3] = i3 / (i + 1);
        }
        return fArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float scale(float f, float f2, float f3, float f4, float f5) {
        return MathHelpersKt.lerp(f4, f5, calcFraction(f, f2, f3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: scale-ziovWd0, reason: not valid java name */
    public static final long m2663scaleziovWd0(float f, float f2, long j, float f3, float f4) {
        return SliderRange(scale(f, f2, SliderRange.m2675getStartimpl(j), f3, f4), scale(f, f2, SliderRange.m2674getEndInclusiveimpl(j), f3, f4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calcFraction(float f, float f2, float f3) {
        float f4 = f2 - f;
        return RangesKt.coerceIn(f4 == 0.0f ? 0.0f : (f3 - f) / f4, 0.0f, 1.0f);
    }

    private static final Modifier sliderSemantics(Modifier modifier, final SliderState sliderState, final boolean z) {
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt$sliderSemantics$1
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
                if (!z) {
                    SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
                }
                final SliderState sliderState2 = sliderState;
                SemanticsPropertiesKt.setProgress$default(semanticsPropertyReceiver, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material3.SliderKt$sliderSemantics$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f) {
                        return invoke(f.floatValue());
                    }

                    public final Boolean invoke(float f) {
                        int steps;
                        float coerceIn = RangesKt.coerceIn(f, SliderState.this.getValueRange().getStart().floatValue(), SliderState.this.getValueRange().getEndInclusive().floatValue());
                        boolean z2 = false;
                        if (SliderState.this.getSteps() > 0 && (steps = SliderState.this.getSteps() + 1) >= 0) {
                            float f2 = coerceIn;
                            float f3 = f2;
                            int i = 0;
                            while (true) {
                                float lerp = MathHelpersKt.lerp(SliderState.this.getValueRange().getStart().floatValue(), SliderState.this.getValueRange().getEndInclusive().floatValue(), i / (SliderState.this.getSteps() + 1));
                                float f4 = lerp - coerceIn;
                                if (Math.abs(f4) <= f2) {
                                    f2 = Math.abs(f4);
                                    f3 = lerp;
                                }
                                if (i == steps) {
                                    break;
                                }
                                i++;
                            }
                            coerceIn = f3;
                        }
                        if (coerceIn != SliderState.this.getValue()) {
                            if (coerceIn != SliderState.this.getValue()) {
                                if (SliderState.this.getOnValueChange$material3_release() != null) {
                                    Function1<Float, Unit> onValueChange$material3_release = SliderState.this.getOnValueChange$material3_release();
                                    if (onValueChange$material3_release != null) {
                                        onValueChange$material3_release.invoke(Float.valueOf(coerceIn));
                                    }
                                } else {
                                    SliderState.this.setValue(coerceIn);
                                }
                            }
                            Function0<Unit> onValueChangeFinished = SliderState.this.getOnValueChangeFinished();
                            if (onValueChangeFinished != null) {
                                onValueChangeFinished.invoke();
                            }
                            z2 = true;
                        }
                        return Boolean.valueOf(z2);
                    }
                }, 1, null);
            }
        }, 1, null).then(AccessibilityUtilKt.getIncreaseHorizontalSemanticsBounds()), sliderState.getValue(), RangesKt.rangeTo(sliderState.getValueRange().getStart().floatValue(), sliderState.getValueRange().getEndInclusive().floatValue()), sliderState.getSteps());
    }

    private static final Modifier rangeSliderStartThumbSemantics(Modifier modifier, final RangeSliderState rangeSliderState, final boolean z) {
        final ClosedFloatingPointRange<Float> rangeTo = RangesKt.rangeTo(rangeSliderState.getValueRange().getStart().floatValue(), rangeSliderState.getActiveRangeEnd());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt$rangeSliderStartThumbSemantics$1
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
                if (!z) {
                    SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
                }
                final ClosedFloatingPointRange<Float> closedFloatingPointRange = rangeTo;
                final RangeSliderState rangeSliderState2 = rangeSliderState;
                SemanticsPropertiesKt.setProgress$default(semanticsPropertyReceiver, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material3.SliderKt$rangeSliderStartThumbSemantics$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f) {
                        return invoke(f.floatValue());
                    }

                    public final Boolean invoke(float f) {
                        int startSteps$material3_release;
                        float coerceIn = RangesKt.coerceIn(f, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
                        boolean z2 = false;
                        if (rangeSliderState2.getStartSteps$material3_release() > 0 && (startSteps$material3_release = rangeSliderState2.getStartSteps$material3_release() + 1) >= 0) {
                            float f2 = coerceIn;
                            float f3 = f2;
                            int i = 0;
                            while (true) {
                                float lerp = MathHelpersKt.lerp(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), i / (rangeSliderState2.getStartSteps$material3_release() + 1));
                                float f4 = lerp - coerceIn;
                                if (Math.abs(f4) <= f2) {
                                    f2 = Math.abs(f4);
                                    f3 = lerp;
                                }
                                if (i == startSteps$material3_release) {
                                    break;
                                }
                                i++;
                            }
                            coerceIn = f3;
                        }
                        if (coerceIn != rangeSliderState2.getActiveRangeStart()) {
                            long SliderRange = SliderKt.SliderRange(coerceIn, rangeSliderState2.getActiveRangeEnd());
                            if (!SliderRange.m2673equalsimpl0(SliderRange, SliderKt.SliderRange(rangeSliderState2.getActiveRangeStart(), rangeSliderState2.getActiveRangeEnd()))) {
                                if (rangeSliderState2.getOnValueChange$material3_release() != null) {
                                    Function1<SliderRange, Unit> onValueChange$material3_release = rangeSliderState2.getOnValueChange$material3_release();
                                    if (onValueChange$material3_release != null) {
                                        onValueChange$material3_release.invoke(SliderRange.m2670boximpl(SliderRange));
                                    }
                                } else {
                                    rangeSliderState2.setActiveRangeStart(SliderRange.m2675getStartimpl(SliderRange));
                                    rangeSliderState2.setActiveRangeEnd(SliderRange.m2674getEndInclusiveimpl(SliderRange));
                                }
                            }
                            Function0<Unit> onValueChangeFinished = rangeSliderState2.getOnValueChangeFinished();
                            if (onValueChangeFinished != null) {
                                onValueChangeFinished.invoke();
                            }
                            z2 = true;
                        }
                        return Boolean.valueOf(z2);
                    }
                }, 1, null);
            }
        }, 1, null).then(AccessibilityUtilKt.getIncreaseHorizontalSemanticsBounds()), rangeSliderState.getActiveRangeStart(), rangeTo, rangeSliderState.getStartSteps$material3_release());
    }

    private static final Modifier rangeSliderEndThumbSemantics(Modifier modifier, final RangeSliderState rangeSliderState, final boolean z) {
        final ClosedFloatingPointRange<Float> rangeTo = RangesKt.rangeTo(rangeSliderState.getActiveRangeStart(), rangeSliderState.getValueRange().getEndInclusive().floatValue());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt$rangeSliderEndThumbSemantics$1
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
                if (!z) {
                    SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
                }
                final ClosedFloatingPointRange<Float> closedFloatingPointRange = rangeTo;
                final RangeSliderState rangeSliderState2 = rangeSliderState;
                SemanticsPropertiesKt.setProgress$default(semanticsPropertyReceiver, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material3.SliderKt$rangeSliderEndThumbSemantics$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f) {
                        return invoke(f.floatValue());
                    }

                    public final Boolean invoke(float f) {
                        int endSteps$material3_release;
                        float coerceIn = RangesKt.coerceIn(f, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
                        boolean z2 = false;
                        if (rangeSliderState2.getEndSteps$material3_release() > 0 && (endSteps$material3_release = rangeSliderState2.getEndSteps$material3_release() + 1) >= 0) {
                            float f2 = coerceIn;
                            float f3 = f2;
                            int i = 0;
                            while (true) {
                                float lerp = MathHelpersKt.lerp(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), i / (rangeSliderState2.getEndSteps$material3_release() + 1));
                                float f4 = lerp - coerceIn;
                                if (Math.abs(f4) <= f2) {
                                    f2 = Math.abs(f4);
                                    f3 = lerp;
                                }
                                if (i == endSteps$material3_release) {
                                    break;
                                }
                                i++;
                            }
                            coerceIn = f3;
                        }
                        if (coerceIn != rangeSliderState2.getActiveRangeEnd()) {
                            long SliderRange = SliderKt.SliderRange(rangeSliderState2.getActiveRangeStart(), coerceIn);
                            if (!SliderRange.m2673equalsimpl0(SliderRange, SliderKt.SliderRange(rangeSliderState2.getActiveRangeStart(), rangeSliderState2.getActiveRangeEnd()))) {
                                if (rangeSliderState2.getOnValueChange$material3_release() != null) {
                                    Function1<SliderRange, Unit> onValueChange$material3_release = rangeSliderState2.getOnValueChange$material3_release();
                                    if (onValueChange$material3_release != null) {
                                        onValueChange$material3_release.invoke(SliderRange.m2670boximpl(SliderRange));
                                    }
                                } else {
                                    rangeSliderState2.setActiveRangeStart(SliderRange.m2675getStartimpl(SliderRange));
                                    rangeSliderState2.setActiveRangeEnd(SliderRange.m2674getEndInclusiveimpl(SliderRange));
                                }
                            }
                            Function0<Unit> onValueChangeFinished = rangeSliderState2.getOnValueChangeFinished();
                            if (onValueChangeFinished != null) {
                                onValueChangeFinished.invoke();
                            }
                            z2 = true;
                        }
                        return Boolean.valueOf(z2);
                    }
                }, 1, null);
            }
        }, 1, null).then(AccessibilityUtilKt.getIncreaseHorizontalSemanticsBounds()), rangeSliderState.getActiveRangeEnd(), rangeTo, rangeSliderState.getEndSteps$material3_release());
    }

    private static final Modifier sliderTapModifier(Modifier modifier, SliderState sliderState, MutableInteractionSource mutableInteractionSource, boolean z) {
        Modifier then;
        if (!z) {
            return modifier;
        }
        then = modifier.then(new SuspendPointerInputElement(sliderState, mutableInteractionSource, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0(new SliderKt$sliderTapModifier$1(sliderState, null)), 4, null));
        return then;
    }

    private static final Modifier rangeSliderPressDragModifier(Modifier modifier, RangeSliderState rangeSliderState, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, boolean z) {
        Modifier then;
        if (!z) {
            return modifier;
        }
        then = modifier.then(new SuspendPointerInputElement(null, null, new Object[]{mutableInteractionSource, mutableInteractionSource2, rangeSliderState}, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0(new SliderKt$rangeSliderPressDragModifier$1(rangeSliderState, mutableInteractionSource, mutableInteractionSource2, null)), 3, null));
        return then;
    }

    static {
        float m3680getHandleWidthD9Ej5fM = SliderTokens.INSTANCE.m3680getHandleWidthD9Ej5fM();
        ThumbWidth = m3680getHandleWidthD9Ej5fM;
        float m3679getHandleHeightD9Ej5fM = SliderTokens.INSTANCE.m3679getHandleHeightD9Ej5fM();
        ThumbHeight = m3679getHandleHeightD9Ej5fM;
        ThumbSize = DpKt.m7421DpSizeYgX7TsA(m3680getHandleWidthD9Ej5fM, m3679getHandleHeightD9Ej5fM);
        ThumbTrackGapSize = SliderTokens.INSTANCE.m3672getActiveHandleLeadingSpaceD9Ej5fM();
        TrackInsideCornerSize = Dp.m7399constructorimpl(2);
    }

    public static final float getTrackHeight() {
        return TrackHeight;
    }

    public static final float getThumbWidth() {
        return ThumbWidth;
    }

    public static final long SliderRange(float f, float f2) {
        if ((!Float.isNaN(f) || !Float.isNaN(f2)) && f > f2 + SliderRangeTolerance) {
            throw new IllegalArgumentException(("start(" + f + ") must be <= endInclusive(" + f2 + ')').toString());
        }
        return SliderRange.m2671constructorimpl((Float.floatToRawIntBits(f2) & 4294967295L) | (Float.floatToRawIntBits(f) << 32));
    }

    public static final long SliderRange(ClosedFloatingPointRange<Float> closedFloatingPointRange) {
        float floatValue = closedFloatingPointRange.getStart().floatValue();
        float floatValue2 = closedFloatingPointRange.getEndInclusive().floatValue();
        if ((!Float.isNaN(floatValue) || !Float.isNaN(floatValue2)) && floatValue > floatValue2 + SliderRangeTolerance) {
            throw new IllegalArgumentException(("ClosedFloatingPointRange<Float>.start(" + floatValue + ") must be <= ClosedFloatingPoint.endInclusive(" + floatValue2 + ')').toString());
        }
        return SliderRange.m2671constructorimpl((Float.floatToRawIntBits(floatValue) << 32) | (Float.floatToRawIntBits(floatValue2) & 4294967295L));
    }

    /* renamed from: isSpecified-If1S1O4, reason: not valid java name */
    public static final boolean m2661isSpecifiedIf1S1O4(long j) {
        return j != SliderRange.INSTANCE.m2680getUnspecifiedFYbKRX4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator] */
    public static final float snapValueToTick(float f, float[] fArr, float f2, float f3) {
        Float valueOf;
        if (fArr.length == 0) {
            valueOf = null;
        } else {
            float f4 = fArr[0];
            int lastIndex = ArraysKt.getLastIndex(fArr);
            if (lastIndex != 0) {
                float abs = Math.abs(MathHelpersKt.lerp(f2, f3, f4) - f);
                ?? it = new IntRange(1, lastIndex).iterator();
                while (it.hasNext()) {
                    float f5 = fArr[it.nextInt()];
                    float abs2 = Math.abs(MathHelpersKt.lerp(f2, f3, f5) - f);
                    if (Float.compare(abs, abs2) > 0) {
                        f4 = f5;
                        abs = abs2;
                    }
                }
                valueOf = Float.valueOf(f4);
            } else {
                valueOf = Float.valueOf(f4);
            }
        }
        return valueOf != null ? MathHelpersKt.lerp(f2, f3, valueOf.floatValue()) : f;
    }
}
