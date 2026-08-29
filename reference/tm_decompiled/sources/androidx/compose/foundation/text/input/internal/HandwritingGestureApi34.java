package androidx.compose.foundation.text.input.internal;

import android.graphics.PointF;
import android.graphics.RectF;
import android.os.CancellationSignal;
import android.view.inputmethod.DeleteGesture;
import android.view.inputmethod.DeleteRangeGesture;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InsertGesture;
import android.view.inputmethod.JoinOrSplitGesture;
import android.view.inputmethod.PreviewableHandwritingGesture;
import android.view.inputmethod.RemoveSpaceGesture;
import android.view.inputmethod.SelectGesture;
import android.view.inputmethod.SelectRangeGesture;
import androidx.activity.ComponentDialog$$ExternalSyntheticApiModelOutline0;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.TextHighlightType;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextGranularity;
import androidx.compose.ui.text.TextInclusionStrategy;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteSurroundingTextCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.SetSelectionCommand;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

/* compiled from: HandwritingGesture.android.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J;\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0000¢\u0006\u0002\b\u0010J+\u0010\u0011\u001a\u00020\u0012*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0000¢\u0006\u0002\b\u0016J,\u0010\u0017\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0002J\u001c\u0010\u001a\u001a\u00020\r*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u001b\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u001d\u001a\u00020\r*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\nH\u0002J,\u0010\u001e\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u001f2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0002J\u001c\u0010 \u001a\u00020\r*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u001f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010!\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\"2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010#\u001a\u00020\r*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\"2\u0006\u0010\t\u001a\u00020\nH\u0002J&\u0010$\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020%2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J&\u0010&\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020'2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J&\u0010(\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020)2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J#\u0010*\u001a\u00020\r*\u00020\u00062\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0012H\u0002¢\u0006\u0004\b.\u0010/J\u0014\u00100\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\bH\u0002J#\u00101\u001a\u00020\r*\u00020\u00062\u0006\u00102\u001a\u00020,2\u0006\u00103\u001a\u000204H\u0002¢\u0006\u0004\b5\u00106JA\u0010\u0004\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020\b2\b\u00108\u001a\u0004\u0018\u0001092\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0000¢\u0006\u0002\b\u0010J-\u0010\u0011\u001a\u00020\u0012*\u0002072\u0006\u0010\u0018\u001a\u00020\u00132\b\u00108\u001a\u0004\u0018\u0001092\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0000¢\u0006\u0002\b\u0016J2\u0010\u0017\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010=\u001a\u0004\u0018\u0001092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J\u001e\u0010\u001a\u001a\u00020\r*\u0002072\u0006\u0010\u0018\u001a\u00020\u00192\b\u00108\u001a\u0004\u0018\u000109H\u0002J0\u0010\u001b\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020\u001c2\u0006\u0010>\u001a\u00020?2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J\u001e\u0010\u001d\u001a\u00020\r*\u0002072\u0006\u0010\u0018\u001a\u00020\u001c2\b\u00108\u001a\u0004\u0018\u000109H\u0002J2\u0010\u001e\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020\u001f2\b\u0010=\u001a\u0004\u0018\u0001092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J\u001e\u0010 \u001a\u00020\r*\u0002072\u0006\u0010\u0018\u001a\u00020\u001f2\b\u00108\u001a\u0004\u0018\u000109H\u0002J0\u0010!\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020\"2\u0006\u0010>\u001a\u00020?2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J\u001e\u0010#\u001a\u00020\r*\u0002072\u0006\u0010\u0018\u001a\u00020\"2\b\u00108\u001a\u0004\u0018\u000109H\u0002J:\u0010$\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020%2\u0006\u0010>\u001a\u00020?2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J2\u0010&\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020'2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J:\u0010(\u001a\u00020\u0005*\u0002072\u0006\u0010\u0018\u001a\u00020)2\u0006\u0010>\u001a\u00020?2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J,\u0010@\u001a\u00020\r2\u0006\u0010A\u001a\u00020\u00052\u0006\u0010>\u001a\u00020B2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J5\u0010C\u001a\u00020\r2\u0006\u00102\u001a\u00020,2\b\u0010=\u001a\u0004\u0018\u0001092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002¢\u0006\u0004\bD\u0010EJ;\u0010F\u001a\u00020\r2\u0006\u00102\u001a\u00020,2\u0006\u0010>\u001a\u00020?2\u0006\u0010-\u001a\u00020\u00122\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002¢\u0006\u0004\bG\u0010HJ$\u0010I\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\b2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\r0;H\u0002J\u0013\u0010J\u001a\u00020K*\u00020\u0005H\u0002¢\u0006\u0004\bL\u0010M¨\u0006N"}, d2 = {"Landroidx/compose/foundation/text/input/internal/HandwritingGestureApi34;", "", "<init>", "()V", "performHandwritingGesture", "", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "handwritingGesture", "Landroid/view/inputmethod/HandwritingGesture;", "layoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "updateSelectionState", "Lkotlin/Function0;", "", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "performHandwritingGesture$foundation_release", "previewHandwritingGesture", "", "Landroid/view/inputmethod/PreviewableHandwritingGesture;", "cancellationSignal", "Landroid/os/CancellationSignal;", "previewHandwritingGesture$foundation_release", "performSelectGesture", "gesture", "Landroid/view/inputmethod/SelectGesture;", "previewSelectGesture", "performDeleteGesture", "Landroid/view/inputmethod/DeleteGesture;", "previewDeleteGesture", "performSelectRangeGesture", "Landroid/view/inputmethod/SelectRangeGesture;", "previewSelectRangeGesture", "performDeleteRangeGesture", "Landroid/view/inputmethod/DeleteRangeGesture;", "previewDeleteRangeGesture", "performJoinOrSplitGesture", "Landroid/view/inputmethod/JoinOrSplitGesture;", "performInsertGesture", "Landroid/view/inputmethod/InsertGesture;", "performRemoveSpaceGesture", "Landroid/view/inputmethod/RemoveSpaceGesture;", "performDeletion", "rangeInTransformedText", "Landroidx/compose/ui/text/TextRange;", "adjustRange", "performDeletion-Sb-Bc2M", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;JZ)V", "fallback", "highlightRange", "range", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/foundation/text/input/TextHighlightType;", "highlightRange-XJREzCE", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;JI)V", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "textFieldSelectionManager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "editCommandConsumer", "Lkotlin/Function1;", "Landroidx/compose/ui/text/input/EditCommand;", "textSelectionManager", "text", "Landroidx/compose/ui/text/AnnotatedString;", "performInsertionOnLegacyTextField", "offset", "", "performSelectionOnLegacyTextField", "performSelectionOnLegacyTextField-8ffj60Q", "(JLandroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function1;)V", "performDeletionOnLegacyTextField", "performDeletionOnLegacyTextField-vJH6DeI", "(JLandroidx/compose/ui/text/AnnotatedString;ZLkotlin/jvm/functions/Function1;)V", "fallbackOnLegacyTextField", "toTextGranularity", "Landroidx/compose/ui/text/TextGranularity;", "toTextGranularity-NUwxegE", "(I)I", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class HandwritingGestureApi34 {
    public static final int $stable = 0;
    public static final HandwritingGestureApi34 INSTANCE = new HandwritingGestureApi34();

    private HandwritingGestureApi34() {
    }

    public final int performHandwritingGesture$foundation_release(TransformedTextFieldState transformedTextFieldState, HandwritingGesture handwritingGesture, TextLayoutState textLayoutState, Function0<Unit> function0, ViewConfiguration viewConfiguration) {
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$4(handwritingGesture)) {
            return performSelectGesture(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m24m((Object) handwritingGesture), textLayoutState, function0);
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$2(handwritingGesture)) {
            return performDeleteGesture(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m17m((Object) handwritingGesture), textLayoutState);
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$5(handwritingGesture)) {
            return performSelectRangeGesture(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m25m((Object) handwritingGesture), textLayoutState, function0);
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$6(handwritingGesture)) {
            return performDeleteRangeGesture(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m18m((Object) handwritingGesture), textLayoutState);
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$3(handwritingGesture)) {
            return performJoinOrSplitGesture(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m22m((Object) handwritingGesture), textLayoutState, viewConfiguration);
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m37m((Object) handwritingGesture)) {
            return performInsertGesture(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m21m((Object) handwritingGesture), textLayoutState, viewConfiguration);
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$1(handwritingGesture)) {
            return performRemoveSpaceGesture(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m23m((Object) handwritingGesture), textLayoutState, viewConfiguration);
        }
        return 2;
    }

    public final boolean previewHandwritingGesture$foundation_release(final TransformedTextFieldState transformedTextFieldState, PreviewableHandwritingGesture previewableHandwritingGesture, TextLayoutState textLayoutState, CancellationSignal cancellationSignal) {
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$4(previewableHandwritingGesture)) {
            previewSelectGesture(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m24m((Object) previewableHandwritingGesture), textLayoutState);
        } else if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$2(previewableHandwritingGesture)) {
            previewDeleteGesture(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m17m((Object) previewableHandwritingGesture), textLayoutState);
        } else if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$5(previewableHandwritingGesture)) {
            previewSelectRangeGesture(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m25m((Object) previewableHandwritingGesture), textLayoutState);
        } else {
            if (!ComponentDialog$$ExternalSyntheticApiModelOutline0.m$6(previewableHandwritingGesture)) {
                return false;
            }
            previewDeleteRangeGesture(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m18m((Object) previewableHandwritingGesture), textLayoutState);
        }
        if (cancellationSignal == null) {
            return true;
        }
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.compose.foundation.text.input.internal.HandwritingGestureApi34$$ExternalSyntheticLambda34
            @Override // android.os.CancellationSignal.OnCancelListener
            public final void onCancel() {
                HandwritingGestureApi34.previewHandwritingGesture$lambda$1(TransformedTextFieldState.this);
            }
        });
        return true;
    }

    private final int performSelectGesture(TransformedTextFieldState transformedTextFieldState, SelectGesture selectGesture, TextLayoutState textLayoutState, Function0<Unit> function0) {
        RectF selectionArea;
        int granularity;
        long m1558getRangeForScreenRectOH9lIzo;
        selectionArea = selectGesture.getSelectionArea();
        Rect composeRect = RectHelper_androidKt.toComposeRect(selectionArea);
        granularity = selectGesture.getGranularity();
        m1558getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m1558getRangeForScreenRectOH9lIzo(textLayoutState, composeRect, m1540toTextGranularityNUwxegE(granularity), TextInclusionStrategy.INSTANCE.getContainsCenter());
        if (TextRange.m6842getCollapsedimpl(m1558getRangeForScreenRectOH9lIzo)) {
            return INSTANCE.fallback(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) selectGesture));
        }
        transformedTextFieldState.m1638selectCharsIn5zctL8(m1558getRangeForScreenRectOH9lIzo);
        if (function0 == null) {
            return 1;
        }
        function0.invoke();
        return 1;
    }

    private final void previewSelectGesture(TransformedTextFieldState transformedTextFieldState, SelectGesture selectGesture, TextLayoutState textLayoutState) {
        RectF selectionArea;
        int granularity;
        long m1558getRangeForScreenRectOH9lIzo;
        selectionArea = selectGesture.getSelectionArea();
        Rect composeRect = RectHelper_androidKt.toComposeRect(selectionArea);
        granularity = selectGesture.getGranularity();
        m1558getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m1558getRangeForScreenRectOH9lIzo(textLayoutState, composeRect, m1540toTextGranularityNUwxegE(granularity), TextInclusionStrategy.INSTANCE.getContainsCenter());
        m1536highlightRangeXJREzCE(transformedTextFieldState, m1558getRangeForScreenRectOH9lIzo, TextHighlightType.INSTANCE.m1496getHandwritingSelectPreviewsxJuwY());
    }

    private final int performDeleteGesture(TransformedTextFieldState transformedTextFieldState, DeleteGesture deleteGesture, TextLayoutState textLayoutState) {
        int granularity;
        RectF deletionArea;
        long m1558getRangeForScreenRectOH9lIzo;
        granularity = deleteGesture.getGranularity();
        int m1540toTextGranularityNUwxegE = m1540toTextGranularityNUwxegE(granularity);
        deletionArea = deleteGesture.getDeletionArea();
        m1558getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m1558getRangeForScreenRectOH9lIzo(textLayoutState, RectHelper_androidKt.toComposeRect(deletionArea), m1540toTextGranularityNUwxegE, TextInclusionStrategy.INSTANCE.getContainsCenter());
        if (TextRange.m6842getCollapsedimpl(m1558getRangeForScreenRectOH9lIzo)) {
            return INSTANCE.fallback(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) deleteGesture));
        }
        m1537performDeletionSbBc2M(transformedTextFieldState, m1558getRangeForScreenRectOH9lIzo, TextGranularity.m6806equalsimpl0(m1540toTextGranularityNUwxegE, TextGranularity.INSTANCE.m6811getWordDRrd7Zo()));
        return 1;
    }

    private final void previewDeleteGesture(TransformedTextFieldState transformedTextFieldState, DeleteGesture deleteGesture, TextLayoutState textLayoutState) {
        RectF deletionArea;
        int granularity;
        long m1558getRangeForScreenRectOH9lIzo;
        deletionArea = deleteGesture.getDeletionArea();
        Rect composeRect = RectHelper_androidKt.toComposeRect(deletionArea);
        granularity = deleteGesture.getGranularity();
        m1558getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m1558getRangeForScreenRectOH9lIzo(textLayoutState, composeRect, m1540toTextGranularityNUwxegE(granularity), TextInclusionStrategy.INSTANCE.getContainsCenter());
        m1536highlightRangeXJREzCE(transformedTextFieldState, m1558getRangeForScreenRectOH9lIzo, TextHighlightType.INSTANCE.m1495getHandwritingDeletePreviewsxJuwY());
    }

    private final int performSelectRangeGesture(TransformedTextFieldState transformedTextFieldState, SelectRangeGesture selectRangeGesture, TextLayoutState textLayoutState, Function0<Unit> function0) {
        RectF selectionStartArea;
        RectF selectionEndArea;
        int granularity;
        long m1560getRangeForScreenRectsO048IG0;
        selectionStartArea = selectRangeGesture.getSelectionStartArea();
        Rect composeRect = RectHelper_androidKt.toComposeRect(selectionStartArea);
        selectionEndArea = selectRangeGesture.getSelectionEndArea();
        Rect composeRect2 = RectHelper_androidKt.toComposeRect(selectionEndArea);
        granularity = selectRangeGesture.getGranularity();
        m1560getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m1560getRangeForScreenRectsO048IG0(textLayoutState, composeRect, composeRect2, m1540toTextGranularityNUwxegE(granularity), TextInclusionStrategy.INSTANCE.getContainsCenter());
        if (TextRange.m6842getCollapsedimpl(m1560getRangeForScreenRectsO048IG0)) {
            return INSTANCE.fallback(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) selectRangeGesture));
        }
        transformedTextFieldState.m1638selectCharsIn5zctL8(m1560getRangeForScreenRectsO048IG0);
        if (function0 == null) {
            return 1;
        }
        function0.invoke();
        return 1;
    }

    private final void previewSelectRangeGesture(TransformedTextFieldState transformedTextFieldState, SelectRangeGesture selectRangeGesture, TextLayoutState textLayoutState) {
        RectF selectionStartArea;
        RectF selectionEndArea;
        int granularity;
        long m1560getRangeForScreenRectsO048IG0;
        selectionStartArea = selectRangeGesture.getSelectionStartArea();
        Rect composeRect = RectHelper_androidKt.toComposeRect(selectionStartArea);
        selectionEndArea = selectRangeGesture.getSelectionEndArea();
        Rect composeRect2 = RectHelper_androidKt.toComposeRect(selectionEndArea);
        granularity = selectRangeGesture.getGranularity();
        m1560getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m1560getRangeForScreenRectsO048IG0(textLayoutState, composeRect, composeRect2, m1540toTextGranularityNUwxegE(granularity), TextInclusionStrategy.INSTANCE.getContainsCenter());
        m1536highlightRangeXJREzCE(transformedTextFieldState, m1560getRangeForScreenRectsO048IG0, TextHighlightType.INSTANCE.m1496getHandwritingSelectPreviewsxJuwY());
    }

    private final int performDeleteRangeGesture(TransformedTextFieldState transformedTextFieldState, DeleteRangeGesture deleteRangeGesture, TextLayoutState textLayoutState) {
        int granularity;
        RectF deletionStartArea;
        RectF deletionEndArea;
        long m1560getRangeForScreenRectsO048IG0;
        granularity = deleteRangeGesture.getGranularity();
        int m1540toTextGranularityNUwxegE = m1540toTextGranularityNUwxegE(granularity);
        deletionStartArea = deleteRangeGesture.getDeletionStartArea();
        Rect composeRect = RectHelper_androidKt.toComposeRect(deletionStartArea);
        deletionEndArea = deleteRangeGesture.getDeletionEndArea();
        m1560getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m1560getRangeForScreenRectsO048IG0(textLayoutState, composeRect, RectHelper_androidKt.toComposeRect(deletionEndArea), m1540toTextGranularityNUwxegE, TextInclusionStrategy.INSTANCE.getContainsCenter());
        if (TextRange.m6842getCollapsedimpl(m1560getRangeForScreenRectsO048IG0)) {
            return INSTANCE.fallback(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) deleteRangeGesture));
        }
        m1537performDeletionSbBc2M(transformedTextFieldState, m1560getRangeForScreenRectsO048IG0, TextGranularity.m6806equalsimpl0(m1540toTextGranularityNUwxegE, TextGranularity.INSTANCE.m6811getWordDRrd7Zo()));
        return 1;
    }

    private final void previewDeleteRangeGesture(TransformedTextFieldState transformedTextFieldState, DeleteRangeGesture deleteRangeGesture, TextLayoutState textLayoutState) {
        RectF deletionStartArea;
        RectF deletionEndArea;
        int granularity;
        long m1560getRangeForScreenRectsO048IG0;
        deletionStartArea = deleteRangeGesture.getDeletionStartArea();
        Rect composeRect = RectHelper_androidKt.toComposeRect(deletionStartArea);
        deletionEndArea = deleteRangeGesture.getDeletionEndArea();
        Rect composeRect2 = RectHelper_androidKt.toComposeRect(deletionEndArea);
        granularity = deleteRangeGesture.getGranularity();
        m1560getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m1560getRangeForScreenRectsO048IG0(textLayoutState, composeRect, composeRect2, m1540toTextGranularityNUwxegE(granularity), TextInclusionStrategy.INSTANCE.getContainsCenter());
        m1536highlightRangeXJREzCE(transformedTextFieldState, m1560getRangeForScreenRectsO048IG0, TextHighlightType.INSTANCE.m1495getHandwritingDeletePreviewsxJuwY());
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
    
        if (r12 == true) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int performJoinOrSplitGesture(TransformedTextFieldState transformedTextFieldState, JoinOrSplitGesture joinOrSplitGesture, TextLayoutState textLayoutState, ViewConfiguration viewConfiguration) {
        PointF joinOrSplitPoint;
        long offset;
        int m1553getOffsetForHandwritingGestured4ec7I;
        long rangeOfWhitespaces;
        boolean isBiDiBoundary;
        if (transformedTextFieldState.getOutputText() != transformedTextFieldState.getUntransformedText()) {
            return 3;
        }
        joinOrSplitPoint = joinOrSplitGesture.getJoinOrSplitPoint();
        offset = HandwritingGesture_androidKt.toOffset(joinOrSplitPoint);
        m1553getOffsetForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m1553getOffsetForHandwritingGestured4ec7I(textLayoutState, offset, viewConfiguration);
        if (m1553getOffsetForHandwritingGestured4ec7I != -1) {
            TextLayoutResult layoutResult = textLayoutState.getLayoutResult();
            if (layoutResult != null) {
                isBiDiBoundary = HandwritingGesture_androidKt.isBiDiBoundary(layoutResult, m1553getOffsetForHandwritingGestured4ec7I);
            }
            rangeOfWhitespaces = HandwritingGesture_androidKt.rangeOfWhitespaces(transformedTextFieldState.getVisualText(), m1553getOffsetForHandwritingGestured4ec7I);
            if (TextRange.m6842getCollapsedimpl(rangeOfWhitespaces)) {
                TransformedTextFieldState.m1631replaceTextM8tDOmk$default(transformedTextFieldState, " ", rangeOfWhitespaces, null, false, 12, null);
            } else {
                m1537performDeletionSbBc2M(transformedTextFieldState, rangeOfWhitespaces, false);
            }
            return 1;
        }
        return fallback(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) joinOrSplitGesture));
    }

    private final int performInsertGesture(TransformedTextFieldState transformedTextFieldState, InsertGesture insertGesture, TextLayoutState textLayoutState, ViewConfiguration viewConfiguration) {
        PointF insertionPoint;
        long offset;
        int m1553getOffsetForHandwritingGestured4ec7I;
        String textToInsert;
        insertionPoint = insertGesture.getInsertionPoint();
        offset = HandwritingGesture_androidKt.toOffset(insertionPoint);
        m1553getOffsetForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m1553getOffsetForHandwritingGestured4ec7I(textLayoutState, offset, viewConfiguration);
        if (m1553getOffsetForHandwritingGestured4ec7I == -1) {
            return fallback(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) insertGesture));
        }
        textToInsert = insertGesture.getTextToInsert();
        TransformedTextFieldState.m1631replaceTextM8tDOmk$default(transformedTextFieldState, textToInsert, TextRangeKt.TextRange(m1553getOffsetForHandwritingGestured4ec7I), null, false, 12, null);
        return 1;
    }

    private final int performRemoveSpaceGesture(TransformedTextFieldState transformedTextFieldState, RemoveSpaceGesture removeSpaceGesture, TextLayoutState textLayoutState, ViewConfiguration viewConfiguration) {
        PointF startPoint;
        long offset;
        PointF endPoint;
        long offset2;
        long m1555getRangeForRemoveSpaceGesture5iVPX68;
        TextLayoutResult layoutResult = textLayoutState.getLayoutResult();
        startPoint = removeSpaceGesture.getStartPoint();
        offset = HandwritingGesture_androidKt.toOffset(startPoint);
        endPoint = removeSpaceGesture.getEndPoint();
        offset2 = HandwritingGesture_androidKt.toOffset(endPoint);
        m1555getRangeForRemoveSpaceGesture5iVPX68 = HandwritingGesture_androidKt.m1555getRangeForRemoveSpaceGesture5iVPX68(layoutResult, offset, offset2, textLayoutState.getTextLayoutNodeCoordinates(), viewConfiguration);
        if (TextRange.m6842getCollapsedimpl(m1555getRangeForRemoveSpaceGesture5iVPX68)) {
            return INSTANCE.fallback(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) removeSpaceGesture));
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = -1;
        String replace = new Regex("\\s+").replace(TextRangeKt.m6855substringFDrldGo(transformedTextFieldState.getVisualText(), m1555getRangeForRemoveSpaceGesture5iVPX68), new Function1() { // from class: androidx.compose.foundation.text.input.internal.HandwritingGestureApi34$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CharSequence performRemoveSpaceGesture$lambda$7;
                performRemoveSpaceGesture$lambda$7 = HandwritingGestureApi34.performRemoveSpaceGesture$lambda$7(Ref.IntRef.this, intRef2, (MatchResult) obj);
                return performRemoveSpaceGesture$lambda$7;
            }
        });
        if (intRef.element == -1 || intRef2.element == -1) {
            return fallback(transformedTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) removeSpaceGesture));
        }
        long TextRange = TextRangeKt.TextRange(TextRange.m6848getStartimpl(m1555getRangeForRemoveSpaceGesture5iVPX68) + intRef.element, TextRange.m6848getStartimpl(m1555getRangeForRemoveSpaceGesture5iVPX68) + intRef2.element);
        String substring = replace.substring(intRef.element, replace.length() - (TextRange.m6844getLengthimpl(m1555getRangeForRemoveSpaceGesture5iVPX68) - intRef2.element));
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        TransformedTextFieldState.m1631replaceTextM8tDOmk$default(transformedTextFieldState, substring, TextRange, null, false, 12, null);
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence performRemoveSpaceGesture$lambda$7(Ref.IntRef intRef, Ref.IntRef intRef2, MatchResult matchResult) {
        if (intRef.element == -1) {
            intRef.element = matchResult.getRange().getFirst();
        }
        intRef2.element = matchResult.getRange().getLast() + 1;
        return "";
    }

    /* renamed from: performDeletion-Sb-Bc2M, reason: not valid java name */
    private final void m1537performDeletionSbBc2M(TransformedTextFieldState transformedTextFieldState, long j, boolean z) {
        if (z) {
            j = HandwritingGesture_androidKt.m1549adjustHandwritingDeleteGestureRange72CqOWE(j, transformedTextFieldState.getVisualText());
        }
        TransformedTextFieldState.m1631replaceTextM8tDOmk$default(transformedTextFieldState, "", j, null, false, 12, null);
    }

    /* renamed from: highlightRange-XJREzCE, reason: not valid java name */
    private final void m1536highlightRangeXJREzCE(TransformedTextFieldState transformedTextFieldState, long j, int i) {
        if (TextRange.m6842getCollapsedimpl(j)) {
            TextFieldState textFieldState = transformedTextFieldState.textFieldState;
            InputTransformation inputTransformation = transformedTextFieldState.inputTransformation;
            TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
            textFieldState.getMainBuffer().getChangeTracker$foundation_release().clearChanges();
            TextFieldBuffer mainBuffer = textFieldState.getMainBuffer();
            mainBuffer.clearHighlight$foundation_release();
            transformedTextFieldState.updateWedgeAffinity(mainBuffer);
            textFieldState.commitEditAsUser(inputTransformation, true, textFieldEditUndoBehavior);
            return;
        }
        transformedTextFieldState.m1632highlightCharsIn7RAjNK8(i, j);
    }

    public final int performHandwritingGesture$foundation_release(LegacyTextFieldState legacyTextFieldState, HandwritingGesture handwritingGesture, TextFieldSelectionManager textFieldSelectionManager, ViewConfiguration viewConfiguration, Function1<? super EditCommand, Unit> function1) {
        TextLayoutResult value;
        TextLayoutInput layoutInput;
        AnnotatedString untransformedText = legacyTextFieldState.getUntransformedText();
        if (untransformedText == null) {
            return 3;
        }
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        if (!Intrinsics.areEqual(untransformedText, (layoutResult == null || (value = layoutResult.getValue()) == null || (layoutInput = value.getLayoutInput()) == null) ? null : layoutInput.getText())) {
            return 3;
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$4(handwritingGesture)) {
            return performSelectGesture(legacyTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m24m((Object) handwritingGesture), textFieldSelectionManager, function1);
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$2(handwritingGesture)) {
            return performDeleteGesture(legacyTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m17m((Object) handwritingGesture), untransformedText, function1);
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$5(handwritingGesture)) {
            return performSelectRangeGesture(legacyTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m25m((Object) handwritingGesture), textFieldSelectionManager, function1);
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$6(handwritingGesture)) {
            return performDeleteRangeGesture(legacyTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m18m((Object) handwritingGesture), untransformedText, function1);
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$3(handwritingGesture)) {
            return performJoinOrSplitGesture(legacyTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m22m((Object) handwritingGesture), untransformedText, viewConfiguration, function1);
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m37m((Object) handwritingGesture)) {
            return performInsertGesture(legacyTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m21m((Object) handwritingGesture), viewConfiguration, function1);
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$1(handwritingGesture)) {
            return performRemoveSpaceGesture(legacyTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m23m((Object) handwritingGesture), untransformedText, viewConfiguration, function1);
        }
        return 2;
    }

    public final boolean previewHandwritingGesture$foundation_release(LegacyTextFieldState legacyTextFieldState, PreviewableHandwritingGesture previewableHandwritingGesture, final TextFieldSelectionManager textFieldSelectionManager, CancellationSignal cancellationSignal) {
        TextLayoutResult value;
        TextLayoutInput layoutInput;
        AnnotatedString untransformedText = legacyTextFieldState.getUntransformedText();
        if (untransformedText == null) {
            return false;
        }
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        if (!Intrinsics.areEqual(untransformedText, (layoutResult == null || (value = layoutResult.getValue()) == null || (layoutInput = value.getLayoutInput()) == null) ? null : layoutInput.getText())) {
            return false;
        }
        if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$4(previewableHandwritingGesture)) {
            previewSelectGesture(legacyTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m24m((Object) previewableHandwritingGesture), textFieldSelectionManager);
        } else if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$2(previewableHandwritingGesture)) {
            previewDeleteGesture(legacyTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m17m((Object) previewableHandwritingGesture), textFieldSelectionManager);
        } else if (ComponentDialog$$ExternalSyntheticApiModelOutline0.m$5(previewableHandwritingGesture)) {
            previewSelectRangeGesture(legacyTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m25m((Object) previewableHandwritingGesture), textFieldSelectionManager);
        } else {
            if (!ComponentDialog$$ExternalSyntheticApiModelOutline0.m$6(previewableHandwritingGesture)) {
                return false;
            }
            previewDeleteRangeGesture(legacyTextFieldState, ComponentDialog$$ExternalSyntheticApiModelOutline0.m18m((Object) previewableHandwritingGesture), textFieldSelectionManager);
        }
        if (cancellationSignal == null) {
            return true;
        }
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.compose.foundation.text.input.internal.HandwritingGestureApi34$$ExternalSyntheticLambda31
            @Override // android.os.CancellationSignal.OnCancelListener
            public final void onCancel() {
                HandwritingGestureApi34.previewHandwritingGesture$lambda$10(TextFieldSelectionManager.this);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void previewHandwritingGesture$lambda$10(TextFieldSelectionManager textFieldSelectionManager) {
        if (textFieldSelectionManager != null) {
            textFieldSelectionManager.clearPreviewHighlight$foundation_release();
        }
    }

    private final int performSelectGesture(LegacyTextFieldState legacyTextFieldState, SelectGesture selectGesture, TextFieldSelectionManager textFieldSelectionManager, Function1<? super EditCommand, Unit> function1) {
        RectF selectionArea;
        int granularity;
        long m1557getRangeForScreenRectOH9lIzo;
        selectionArea = selectGesture.getSelectionArea();
        Rect composeRect = RectHelper_androidKt.toComposeRect(selectionArea);
        granularity = selectGesture.getGranularity();
        m1557getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m1557getRangeForScreenRectOH9lIzo(legacyTextFieldState, composeRect, m1540toTextGranularityNUwxegE(granularity), TextInclusionStrategy.INSTANCE.getContainsCenter());
        if (TextRange.m6842getCollapsedimpl(m1557getRangeForScreenRectOH9lIzo)) {
            return INSTANCE.fallbackOnLegacyTextField(ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) selectGesture), function1);
        }
        m1539performSelectionOnLegacyTextField8ffj60Q(m1557getRangeForScreenRectOH9lIzo, textFieldSelectionManager, function1);
        return 1;
    }

    private final void previewSelectGesture(LegacyTextFieldState legacyTextFieldState, SelectGesture selectGesture, TextFieldSelectionManager textFieldSelectionManager) {
        RectF selectionArea;
        int granularity;
        long m1557getRangeForScreenRectOH9lIzo;
        if (textFieldSelectionManager != null) {
            selectionArea = selectGesture.getSelectionArea();
            Rect composeRect = RectHelper_androidKt.toComposeRect(selectionArea);
            granularity = selectGesture.getGranularity();
            m1557getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m1557getRangeForScreenRectOH9lIzo(legacyTextFieldState, composeRect, m1540toTextGranularityNUwxegE(granularity), TextInclusionStrategy.INSTANCE.getContainsCenter());
            textFieldSelectionManager.m1873setSelectionPreviewHighlight5zctL8$foundation_release(m1557getRangeForScreenRectOH9lIzo);
        }
    }

    private final int performDeleteGesture(LegacyTextFieldState legacyTextFieldState, DeleteGesture deleteGesture, AnnotatedString annotatedString, Function1<? super EditCommand, Unit> function1) {
        int granularity;
        RectF deletionArea;
        long m1557getRangeForScreenRectOH9lIzo;
        granularity = deleteGesture.getGranularity();
        int m1540toTextGranularityNUwxegE = m1540toTextGranularityNUwxegE(granularity);
        deletionArea = deleteGesture.getDeletionArea();
        m1557getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m1557getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deletionArea), m1540toTextGranularityNUwxegE, TextInclusionStrategy.INSTANCE.getContainsCenter());
        if (TextRange.m6842getCollapsedimpl(m1557getRangeForScreenRectOH9lIzo)) {
            return INSTANCE.fallbackOnLegacyTextField(ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) deleteGesture), function1);
        }
        m1538performDeletionOnLegacyTextFieldvJH6DeI(m1557getRangeForScreenRectOH9lIzo, annotatedString, TextGranularity.m6806equalsimpl0(m1540toTextGranularityNUwxegE, TextGranularity.INSTANCE.m6811getWordDRrd7Zo()), function1);
        return 1;
    }

    private final void previewDeleteGesture(LegacyTextFieldState legacyTextFieldState, DeleteGesture deleteGesture, TextFieldSelectionManager textFieldSelectionManager) {
        RectF deletionArea;
        int granularity;
        long m1557getRangeForScreenRectOH9lIzo;
        if (textFieldSelectionManager != null) {
            deletionArea = deleteGesture.getDeletionArea();
            Rect composeRect = RectHelper_androidKt.toComposeRect(deletionArea);
            granularity = deleteGesture.getGranularity();
            m1557getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m1557getRangeForScreenRectOH9lIzo(legacyTextFieldState, composeRect, m1540toTextGranularityNUwxegE(granularity), TextInclusionStrategy.INSTANCE.getContainsCenter());
            textFieldSelectionManager.m1871setDeletionPreviewHighlight5zctL8$foundation_release(m1557getRangeForScreenRectOH9lIzo);
        }
    }

    private final int performSelectRangeGesture(LegacyTextFieldState legacyTextFieldState, SelectRangeGesture selectRangeGesture, TextFieldSelectionManager textFieldSelectionManager, Function1<? super EditCommand, Unit> function1) {
        RectF selectionStartArea;
        RectF selectionEndArea;
        int granularity;
        long m1559getRangeForScreenRectsO048IG0;
        selectionStartArea = selectRangeGesture.getSelectionStartArea();
        Rect composeRect = RectHelper_androidKt.toComposeRect(selectionStartArea);
        selectionEndArea = selectRangeGesture.getSelectionEndArea();
        Rect composeRect2 = RectHelper_androidKt.toComposeRect(selectionEndArea);
        granularity = selectRangeGesture.getGranularity();
        m1559getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m1559getRangeForScreenRectsO048IG0(legacyTextFieldState, composeRect, composeRect2, m1540toTextGranularityNUwxegE(granularity), TextInclusionStrategy.INSTANCE.getContainsCenter());
        if (TextRange.m6842getCollapsedimpl(m1559getRangeForScreenRectsO048IG0)) {
            return INSTANCE.fallbackOnLegacyTextField(ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) selectRangeGesture), function1);
        }
        m1539performSelectionOnLegacyTextField8ffj60Q(m1559getRangeForScreenRectsO048IG0, textFieldSelectionManager, function1);
        return 1;
    }

    private final void previewSelectRangeGesture(LegacyTextFieldState legacyTextFieldState, SelectRangeGesture selectRangeGesture, TextFieldSelectionManager textFieldSelectionManager) {
        RectF selectionStartArea;
        RectF selectionEndArea;
        int granularity;
        long m1559getRangeForScreenRectsO048IG0;
        if (textFieldSelectionManager != null) {
            selectionStartArea = selectRangeGesture.getSelectionStartArea();
            Rect composeRect = RectHelper_androidKt.toComposeRect(selectionStartArea);
            selectionEndArea = selectRangeGesture.getSelectionEndArea();
            Rect composeRect2 = RectHelper_androidKt.toComposeRect(selectionEndArea);
            granularity = selectRangeGesture.getGranularity();
            m1559getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m1559getRangeForScreenRectsO048IG0(legacyTextFieldState, composeRect, composeRect2, m1540toTextGranularityNUwxegE(granularity), TextInclusionStrategy.INSTANCE.getContainsCenter());
            textFieldSelectionManager.m1873setSelectionPreviewHighlight5zctL8$foundation_release(m1559getRangeForScreenRectsO048IG0);
        }
    }

    private final int performDeleteRangeGesture(LegacyTextFieldState legacyTextFieldState, DeleteRangeGesture deleteRangeGesture, AnnotatedString annotatedString, Function1<? super EditCommand, Unit> function1) {
        int granularity;
        RectF deletionStartArea;
        RectF deletionEndArea;
        long m1559getRangeForScreenRectsO048IG0;
        granularity = deleteRangeGesture.getGranularity();
        int m1540toTextGranularityNUwxegE = m1540toTextGranularityNUwxegE(granularity);
        deletionStartArea = deleteRangeGesture.getDeletionStartArea();
        Rect composeRect = RectHelper_androidKt.toComposeRect(deletionStartArea);
        deletionEndArea = deleteRangeGesture.getDeletionEndArea();
        m1559getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m1559getRangeForScreenRectsO048IG0(legacyTextFieldState, composeRect, RectHelper_androidKt.toComposeRect(deletionEndArea), m1540toTextGranularityNUwxegE, TextInclusionStrategy.INSTANCE.getContainsCenter());
        if (TextRange.m6842getCollapsedimpl(m1559getRangeForScreenRectsO048IG0)) {
            return INSTANCE.fallbackOnLegacyTextField(ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) deleteRangeGesture), function1);
        }
        m1538performDeletionOnLegacyTextFieldvJH6DeI(m1559getRangeForScreenRectsO048IG0, annotatedString, TextGranularity.m6806equalsimpl0(m1540toTextGranularityNUwxegE, TextGranularity.INSTANCE.m6811getWordDRrd7Zo()), function1);
        return 1;
    }

    private final void previewDeleteRangeGesture(LegacyTextFieldState legacyTextFieldState, DeleteRangeGesture deleteRangeGesture, TextFieldSelectionManager textFieldSelectionManager) {
        RectF deletionStartArea;
        RectF deletionEndArea;
        int granularity;
        long m1559getRangeForScreenRectsO048IG0;
        if (textFieldSelectionManager != null) {
            deletionStartArea = deleteRangeGesture.getDeletionStartArea();
            Rect composeRect = RectHelper_androidKt.toComposeRect(deletionStartArea);
            deletionEndArea = deleteRangeGesture.getDeletionEndArea();
            Rect composeRect2 = RectHelper_androidKt.toComposeRect(deletionEndArea);
            granularity = deleteRangeGesture.getGranularity();
            m1559getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m1559getRangeForScreenRectsO048IG0(legacyTextFieldState, composeRect, composeRect2, m1540toTextGranularityNUwxegE(granularity), TextInclusionStrategy.INSTANCE.getContainsCenter());
            textFieldSelectionManager.m1871setDeletionPreviewHighlight5zctL8$foundation_release(m1559getRangeForScreenRectsO048IG0);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
    
        if (r8 == true) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int performJoinOrSplitGesture(LegacyTextFieldState legacyTextFieldState, JoinOrSplitGesture joinOrSplitGesture, AnnotatedString annotatedString, ViewConfiguration viewConfiguration, Function1<? super EditCommand, Unit> function1) {
        PointF joinOrSplitPoint;
        long offset;
        int m1552getOffsetForHandwritingGestured4ec7I;
        long rangeOfWhitespaces;
        TextLayoutResult value;
        boolean isBiDiBoundary;
        if (viewConfiguration == null) {
            return fallbackOnLegacyTextField(ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) joinOrSplitGesture), function1);
        }
        joinOrSplitPoint = joinOrSplitGesture.getJoinOrSplitPoint();
        offset = HandwritingGesture_androidKt.toOffset(joinOrSplitPoint);
        m1552getOffsetForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m1552getOffsetForHandwritingGestured4ec7I(legacyTextFieldState, offset, viewConfiguration);
        if (m1552getOffsetForHandwritingGestured4ec7I != -1) {
            TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
            if (layoutResult != null && (value = layoutResult.getValue()) != null) {
                isBiDiBoundary = HandwritingGesture_androidKt.isBiDiBoundary(value, m1552getOffsetForHandwritingGestured4ec7I);
            }
            rangeOfWhitespaces = HandwritingGesture_androidKt.rangeOfWhitespaces(annotatedString, m1552getOffsetForHandwritingGestured4ec7I);
            if (TextRange.m6842getCollapsedimpl(rangeOfWhitespaces)) {
                performInsertionOnLegacyTextField(TextRange.m6848getStartimpl(rangeOfWhitespaces), " ", function1);
            } else {
                m1538performDeletionOnLegacyTextFieldvJH6DeI(rangeOfWhitespaces, annotatedString, false, function1);
            }
            return 1;
        }
        return fallbackOnLegacyTextField(ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) joinOrSplitGesture), function1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
    
        if (r3 == true) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int performInsertGesture(LegacyTextFieldState legacyTextFieldState, InsertGesture insertGesture, ViewConfiguration viewConfiguration, Function1<? super EditCommand, Unit> function1) {
        PointF insertionPoint;
        long offset;
        int m1552getOffsetForHandwritingGestured4ec7I;
        String textToInsert;
        TextLayoutResult value;
        boolean isBiDiBoundary;
        if (viewConfiguration == null) {
            return fallbackOnLegacyTextField(ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) insertGesture), function1);
        }
        insertionPoint = insertGesture.getInsertionPoint();
        offset = HandwritingGesture_androidKt.toOffset(insertionPoint);
        m1552getOffsetForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m1552getOffsetForHandwritingGestured4ec7I(legacyTextFieldState, offset, viewConfiguration);
        if (m1552getOffsetForHandwritingGestured4ec7I != -1) {
            TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
            if (layoutResult != null && (value = layoutResult.getValue()) != null) {
                isBiDiBoundary = HandwritingGesture_androidKt.isBiDiBoundary(value, m1552getOffsetForHandwritingGestured4ec7I);
            }
            textToInsert = insertGesture.getTextToInsert();
            performInsertionOnLegacyTextField(m1552getOffsetForHandwritingGestured4ec7I, textToInsert, function1);
            return 1;
        }
        return fallbackOnLegacyTextField(ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) insertGesture), function1);
    }

    private final int performRemoveSpaceGesture(LegacyTextFieldState legacyTextFieldState, RemoveSpaceGesture removeSpaceGesture, AnnotatedString annotatedString, ViewConfiguration viewConfiguration, Function1<? super EditCommand, Unit> function1) {
        PointF startPoint;
        long offset;
        PointF endPoint;
        long offset2;
        long m1555getRangeForRemoveSpaceGesture5iVPX68;
        EditCommand compoundEditCommand;
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        TextLayoutResult value = layoutResult != null ? layoutResult.getValue() : null;
        startPoint = removeSpaceGesture.getStartPoint();
        offset = HandwritingGesture_androidKt.toOffset(startPoint);
        endPoint = removeSpaceGesture.getEndPoint();
        offset2 = HandwritingGesture_androidKt.toOffset(endPoint);
        m1555getRangeForRemoveSpaceGesture5iVPX68 = HandwritingGesture_androidKt.m1555getRangeForRemoveSpaceGesture5iVPX68(value, offset, offset2, legacyTextFieldState.getLayoutCoordinates(), viewConfiguration);
        if (TextRange.m6842getCollapsedimpl(m1555getRangeForRemoveSpaceGesture5iVPX68)) {
            return INSTANCE.fallbackOnLegacyTextField(ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) removeSpaceGesture), function1);
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = -1;
        String replace = new Regex("\\s+").replace(TextRangeKt.m6855substringFDrldGo(annotatedString, m1555getRangeForRemoveSpaceGesture5iVPX68), new Function1() { // from class: androidx.compose.foundation.text.input.internal.HandwritingGestureApi34$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CharSequence performRemoveSpaceGesture$lambda$16;
                performRemoveSpaceGesture$lambda$16 = HandwritingGestureApi34.performRemoveSpaceGesture$lambda$16(Ref.IntRef.this, intRef2, (MatchResult) obj);
                return performRemoveSpaceGesture$lambda$16;
            }
        });
        if (intRef.element == -1 || intRef2.element == -1) {
            return fallbackOnLegacyTextField(ComponentDialog$$ExternalSyntheticApiModelOutline0.m20m((Object) removeSpaceGesture), function1);
        }
        int m6848getStartimpl = TextRange.m6848getStartimpl(m1555getRangeForRemoveSpaceGesture5iVPX68) + intRef.element;
        int m6848getStartimpl2 = TextRange.m6848getStartimpl(m1555getRangeForRemoveSpaceGesture5iVPX68) + intRef2.element;
        String substring = replace.substring(intRef.element, replace.length() - (TextRange.m6844getLengthimpl(m1555getRangeForRemoveSpaceGesture5iVPX68) - intRef2.element));
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        compoundEditCommand = HandwritingGesture_androidKt.compoundEditCommand(new SetSelectionCommand(m6848getStartimpl, m6848getStartimpl2), new CommitTextCommand(substring, 1));
        function1.invoke(compoundEditCommand);
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence performRemoveSpaceGesture$lambda$16(Ref.IntRef intRef, Ref.IntRef intRef2, MatchResult matchResult) {
        if (intRef.element == -1) {
            intRef.element = matchResult.getRange().getFirst();
        }
        intRef2.element = matchResult.getRange().getLast() + 1;
        return "";
    }

    private final void performInsertionOnLegacyTextField(int offset, String text, Function1<? super EditCommand, Unit> editCommandConsumer) {
        EditCommand compoundEditCommand;
        compoundEditCommand = HandwritingGesture_androidKt.compoundEditCommand(new SetSelectionCommand(offset, offset), new CommitTextCommand(text, 1));
        editCommandConsumer.invoke(compoundEditCommand);
    }

    /* renamed from: performSelectionOnLegacyTextField-8ffj60Q, reason: not valid java name */
    private final void m1539performSelectionOnLegacyTextField8ffj60Q(long range, TextFieldSelectionManager textSelectionManager, Function1<? super EditCommand, Unit> editCommandConsumer) {
        editCommandConsumer.invoke(new SetSelectionCommand(TextRange.m6848getStartimpl(range), TextRange.m6843getEndimpl(range)));
        if (textSelectionManager != null) {
            textSelectionManager.enterSelectionMode$foundation_release(true);
        }
    }

    /* renamed from: performDeletionOnLegacyTextField-vJH6DeI, reason: not valid java name */
    private final void m1538performDeletionOnLegacyTextFieldvJH6DeI(long range, AnnotatedString text, boolean adjustRange, Function1<? super EditCommand, Unit> editCommandConsumer) {
        EditCommand compoundEditCommand;
        if (adjustRange) {
            range = HandwritingGesture_androidKt.m1549adjustHandwritingDeleteGestureRange72CqOWE(range, text);
        }
        compoundEditCommand = HandwritingGesture_androidKt.compoundEditCommand(new SetSelectionCommand(TextRange.m6843getEndimpl(range), TextRange.m6843getEndimpl(range)), new DeleteSurroundingTextCommand(TextRange.m6844getLengthimpl(range), 0));
        editCommandConsumer.invoke(compoundEditCommand);
    }

    private final int fallbackOnLegacyTextField(HandwritingGesture gesture, Function1<? super EditCommand, Unit> editCommandConsumer) {
        String fallbackText;
        fallbackText = gesture.getFallbackText();
        if (fallbackText == null) {
            return 3;
        }
        editCommandConsumer.invoke(new CommitTextCommand(fallbackText, 1));
        return 5;
    }

    /* renamed from: toTextGranularity-NUwxegE, reason: not valid java name */
    private final int m1540toTextGranularityNUwxegE(int i) {
        if (i == 1) {
            return TextGranularity.INSTANCE.m6811getWordDRrd7Zo();
        }
        if (i == 2) {
            return TextGranularity.INSTANCE.m6810getCharacterDRrd7Zo();
        }
        return TextGranularity.INSTANCE.m6810getCharacterDRrd7Zo();
    }

    private final int fallback(TransformedTextFieldState transformedTextFieldState, HandwritingGesture handwritingGesture) {
        String fallbackText;
        TextFieldState textFieldState = transformedTextFieldState.textFieldState;
        InputTransformation inputTransformation = transformedTextFieldState.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        textFieldState.getMainBuffer().getChangeTracker$foundation_release().clearChanges();
        TextFieldBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.clearHighlight$foundation_release();
        transformedTextFieldState.updateWedgeAffinity(mainBuffer);
        textFieldState.commitEditAsUser(inputTransformation, true, textFieldEditUndoBehavior);
        fallbackText = handwritingGesture.getFallbackText();
        if (fallbackText == null) {
            return 3;
        }
        TransformedTextFieldState.replaceSelectedText$default(transformedTextFieldState, fallbackText, true, null, false, 12, null);
        return 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void previewHandwritingGesture$lambda$1(TransformedTextFieldState transformedTextFieldState) {
        TextFieldState textFieldState = transformedTextFieldState.textFieldState;
        InputTransformation inputTransformation = transformedTextFieldState.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        textFieldState.getMainBuffer().getChangeTracker$foundation_release().clearChanges();
        TextFieldBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.clearHighlight$foundation_release();
        transformedTextFieldState.updateWedgeAffinity(mainBuffer);
        textFieldState.commitEditAsUser(inputTransformation, true, textFieldEditUndoBehavior);
    }
}
