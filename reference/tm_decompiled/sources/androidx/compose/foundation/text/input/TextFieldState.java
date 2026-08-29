package androidx.compose.foundation.text.input;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextUndoManager;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: TextFieldState.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0002\\]B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\u001d\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\nJ\"\u0010/\u001a\u0002002\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020002¢\u0006\u0002\b3H\u0086\bJ\b\u00104\u001a\u00020\u0003H\u0016J\b\u0010:\u001a\u00020\u000fH\u0001J\u0010\u0010;\u001a\u0002002\u0006\u0010<\u001a\u00020\u000fH\u0001J\b\u0010=\u001a\u000200H\u0001JE\u0010>\u001a\u0002002\b\u0010?\u001a\u0004\u0018\u00010@2\b\b\u0002\u0010A\u001a\u00020\u00172\b\b\u0002\u0010B\u001a\u00020C2\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020002¢\u0006\u0002\b3H\u0080\b¢\u0006\u0002\bDJ'\u0010E\u001a\u0002002\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020002¢\u0006\u0002\b3H\u0080\b¢\u0006\u0002\bFJ&\u0010G\u001a\u0002002\b\u0010?\u001a\u0004\u0018\u00010@2\b\b\u0002\u0010A\u001a\u00020\u00172\b\b\u0002\u0010B\u001a\u00020CH\u0002J \u0010H\u001a\u0002002\u0006\u0010I\u001a\u00020\u001e2\u0006\u0010<\u001a\u00020\u001e2\u0006\u0010A\u001a\u00020\u0017H\u0002J(\u0010J\u001a\u0002002\u0006\u0010K\u001a\u00020\u001e2\u0006\u0010L\u001a\u00020\u001e2\u0006\u0010M\u001a\u00020N2\u0006\u0010B\u001a\u00020CH\u0002J\u0015\u0010O\u001a\u0002002\u0006\u0010P\u001a\u00020QH\u0000¢\u0006\u0002\bRJ\u0015\u0010S\u001a\u0002002\u0006\u0010P\u001a\u00020QH\u0000¢\u0006\u0002\bTJ%\u0010U\u001a\u0002002\u0006\u0010V\u001a\u00020\u000f2\u0006\u0010W\u001a\u00020\u00172\u0006\u0010X\u001a\u00020\u0017H\u0001¢\u0006\u0002\bYR\u0014\u0010\u000b\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR$\u0010\u000e\u001a\u00020\u000f8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R+\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00178B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR+\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u001e8@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010\u001d\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0011\u0010%\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0011\u0010)\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0013\u0010,\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b-\u0010.R\u001c\u00105\u001a\u0002068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b7\u0010\u0011\u001a\u0004\b8\u00109R\u0014\u0010Z\u001a\b\u0012\u0004\u0012\u00020Q0[X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006^"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldState;", "", "initialText", "", "initialSelection", "Landroidx/compose/ui/text/TextRange;", "initialTextUndoManager", "Landroidx/compose/foundation/text/input/TextUndoManager;", "<init>", "(Ljava/lang/String;JLandroidx/compose/foundation/text/input/TextUndoManager;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "(Ljava/lang/String;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "textUndoManager", "getTextUndoManager$foundation_release", "()Landroidx/compose/foundation/text/input/TextUndoManager;", "mainBuffer", "Landroidx/compose/foundation/text/input/TextFieldBuffer;", "getMainBuffer$foundation_release$annotations", "()V", "getMainBuffer$foundation_release", "()Landroidx/compose/foundation/text/input/TextFieldBuffer;", "setMainBuffer$foundation_release", "(Landroidx/compose/foundation/text/input/TextFieldBuffer;)V", "<set-?>", "", "isEditing", "()Z", "setEditing", "(Z)V", "isEditing$delegate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "value", "getValue$foundation_release", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "setValue", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;)V", "value$delegate", "text", "", "getText", "()Ljava/lang/CharSequence;", "selection", "getSelection-d9O1mEE", "()J", "composition", "getComposition-MzsxiRA", "()Landroidx/compose/ui/text/TextRange;", "edit", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "toString", "undoState", "Landroidx/compose/foundation/text/input/UndoState;", "getUndoState$annotations", "getUndoState", "()Landroidx/compose/foundation/text/input/UndoState;", "startEdit", "commitEdit", "newValue", "finishEditing", "editAsUser", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "restartImeIfContentChanges", "undoBehavior", "Landroidx/compose/foundation/text/input/internal/undo/TextFieldEditUndoBehavior;", "editAsUser$foundation_release", "editWithNoSideEffects", "editWithNoSideEffects$foundation_release", "commitEditAsUser", "updateValueAndNotifyListeners", "oldValue", "recordEditForUndo", "previousValue", "postValue", "changes", "Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "addNotifyImeListener", "notifyImeListener", "Landroidx/compose/foundation/text/input/TextFieldState$NotifyImeListener;", "addNotifyImeListener$foundation_release", "removeNotifyImeListener", "removeNotifyImeListener$foundation_release", "syncMainBufferToTemporaryBuffer", "temporaryBuffer", "textChanged", "selectionChanged", "syncMainBufferToTemporaryBuffer$foundation_release", "notifyImeListeners", "Landroidx/compose/runtime/collection/MutableVector;", "NotifyImeListener", "Saver", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldState {
    public static final int $stable = 0;

    /* renamed from: isEditing$delegate, reason: from kotlin metadata */
    private final MutableState isEditing;
    private TextFieldBuffer mainBuffer;
    private final MutableVector<NotifyImeListener> notifyImeListeners;
    private final TextUndoManager textUndoManager;
    private final UndoState undoState;

    /* renamed from: value$delegate, reason: from kotlin metadata */
    private final MutableState value;

    /* compiled from: TextFieldState.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldState$NotifyImeListener;", "", "onChange", "", "oldValue", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "newValue", "restartIme", "", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface NotifyImeListener {
        void onChange(TextFieldCharSequence oldValue, TextFieldCharSequence newValue, boolean restartIme);
    }

    /* compiled from: TextFieldState.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TextFieldEditUndoBehavior.values().length];
            try {
                iArr[TextFieldEditUndoBehavior.ClearHistory.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextFieldEditUndoBehavior.MergeIfPossible.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TextFieldEditUndoBehavior.NeverMerge.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ TextFieldState(String str, long j, TextUndoManager textUndoManager, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, textUndoManager);
    }

    public /* synthetic */ TextFieldState(String str, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j);
    }

    public static /* synthetic */ void getMainBuffer$foundation_release$annotations() {
    }

    public static /* synthetic */ void getUndoState$annotations() {
    }

    private TextFieldState(String str, long j, TextUndoManager textUndoManager) {
        this.textUndoManager = textUndoManager;
        String str2 = str;
        this.mainBuffer = new TextFieldBuffer(new TextFieldCharSequence(str2, TextRangeKt.m6854coerceIn8ffj60Q(j, 0, str.length()), null, null, null, null, 60, null), null, null, null, 14, null);
        this.isEditing = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.value = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldCharSequence(str2, j, null, null, null, null, 60, null), null, 2, null);
        this.undoState = new UndoState(this);
        this.notifyImeListeners = new MutableVector<>(new NotifyImeListener[16], 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ TextFieldState(String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? TextRangeKt.TextRange(str.length()) : j, (DefaultConstructorMarker) null);
        str = (i & 1) != 0 ? "" : str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TextFieldState(String str, long j) {
        this(str, j, new TextUndoManager(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0), (DefaultConstructorMarker) null);
    }

    /* renamed from: getTextUndoManager$foundation_release, reason: from getter */
    public final TextUndoManager getTextUndoManager() {
        return this.textUndoManager;
    }

    /* renamed from: getMainBuffer$foundation_release, reason: from getter */
    public final TextFieldBuffer getMainBuffer() {
        return this.mainBuffer;
    }

    public final void setMainBuffer$foundation_release(TextFieldBuffer textFieldBuffer) {
        this.mainBuffer = textFieldBuffer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isEditing() {
        return ((Boolean) this.isEditing.getValue()).booleanValue();
    }

    private final void setEditing(boolean z) {
        this.isEditing.setValue(Boolean.valueOf(z));
    }

    private final void setValue(TextFieldCharSequence textFieldCharSequence) {
        this.value.setValue(textFieldCharSequence);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final TextFieldCharSequence getValue$foundation_release() {
        return (TextFieldCharSequence) this.value.getValue();
    }

    public final CharSequence getText() {
        return getValue$foundation_release().getText();
    }

    /* renamed from: getSelection-d9O1mEE, reason: not valid java name */
    public final long m1483getSelectiond9O1mEE() {
        return getValue$foundation_release().getSelection();
    }

    /* renamed from: getComposition-MzsxiRA, reason: not valid java name */
    public final TextRange m1482getCompositionMzsxiRA() {
        return getValue$foundation_release().getComposition();
    }

    public final void edit(Function1<? super TextFieldBuffer, Unit> block) {
        TextFieldBuffer startEdit = startEdit();
        try {
            block.invoke(startEdit);
            commitEdit(startEdit);
        } finally {
            finishEditing();
        }
    }

    public String toString() {
        Snapshot.Companion companion = Snapshot.INSTANCE;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            return "TextFieldState(selection=" + ((Object) TextRange.m6851toStringimpl(m1483getSelectiond9O1mEE())) + ", text=\"" + ((Object) getText()) + "\")";
        } finally {
            companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
        }
    }

    public final UndoState getUndoState() {
        return this.undoState;
    }

    public final TextFieldBuffer startEdit() {
        Snapshot.Companion companion = Snapshot.INSTANCE;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot makeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            if (isEditing()) {
                InlineClassHelperKt.throwIllegalStateException("TextFieldState does not support concurrent or nested editing.");
            }
            setEditing(true);
            return new TextFieldBuffer(getValue$foundation_release(), null, null, null, 14, null);
        } finally {
            companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
        }
    }

    public final void commitEdit(TextFieldBuffer newValue) {
        TextFieldBuffer textFieldBuffer;
        boolean z = newValue.getChanges().getChangeCount() > 0;
        boolean m6841equalsimpl0 = TextRange.m6841equalsimpl0(newValue.getSelectionInChars(), this.mainBuffer.getSelectionInChars());
        boolean z2 = !m6841equalsimpl0;
        if (!z && m6841equalsimpl0) {
            newValue.setCanCallAddStyle$foundation_release(true);
        }
        if (z) {
            textFieldBuffer = newValue;
            recordEditForUndo(getValue$foundation_release(), TextFieldBuffer.m1470toTextFieldCharSequencewFTz33Y$foundation_release$default(textFieldBuffer, 0L, null, null, null, 15, null), textFieldBuffer.getChanges(), TextFieldEditUndoBehavior.NeverMerge);
        } else {
            textFieldBuffer = newValue;
        }
        syncMainBufferToTemporaryBuffer$foundation_release(textFieldBuffer, z, z2);
    }

    public final void finishEditing() {
        setEditing(false);
    }

    public static /* synthetic */ void editAsUser$foundation_release$default(TextFieldState textFieldState, InputTransformation inputTransformation, boolean z, TextFieldEditUndoBehavior textFieldEditUndoBehavior, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        textFieldState.getMainBuffer().getChangeTracker$foundation_release().clearChanges();
        function1.invoke(textFieldState.getMainBuffer());
        textFieldState.commitEditAsUser(inputTransformation, z, textFieldEditUndoBehavior);
    }

    public final void editAsUser$foundation_release(InputTransformation inputTransformation, boolean restartImeIfContentChanges, TextFieldEditUndoBehavior undoBehavior, Function1<? super TextFieldBuffer, Unit> block) {
        getMainBuffer().getChangeTracker$foundation_release().clearChanges();
        block.invoke(getMainBuffer());
        commitEditAsUser(inputTransformation, restartImeIfContentChanges, undoBehavior);
    }

    public final void editWithNoSideEffects$foundation_release(Function1<? super TextFieldBuffer, Unit> block) {
        getMainBuffer().getChangeTracker$foundation_release().clearChanges();
        block.invoke(getMainBuffer());
        updateValueAndNotifyListeners(getValue$foundation_release(), TextFieldBuffer.m1470toTextFieldCharSequencewFTz33Y$foundation_release$default(getMainBuffer(), 0L, null, null, null, 15, null), true);
    }

    static /* synthetic */ void commitEditAsUser$default(TextFieldState textFieldState, InputTransformation inputTransformation, boolean z, TextFieldEditUndoBehavior textFieldEditUndoBehavior, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        textFieldState.commitEditAsUser(inputTransformation, z, textFieldEditUndoBehavior);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void commitEditAsUser(InputTransformation inputTransformation, boolean restartImeIfContentChanges, TextFieldEditUndoBehavior undoBehavior) {
        List m1486finalizeComposingAnnotationsitr0ztk;
        List m1486finalizeComposingAnnotationsitr0ztk2;
        TextFieldCharSequence value$foundation_release = getValue$foundation_release();
        if (this.mainBuffer.getChangeTracker$foundation_release().getChangeCount() == 0 && TextRange.m6841equalsimpl0(value$foundation_release.getSelection(), this.mainBuffer.getSelectionInChars())) {
            if (Intrinsics.areEqual(value$foundation_release.getComposition(), this.mainBuffer.getComposition()) && Intrinsics.areEqual(value$foundation_release.getHighlight(), this.mainBuffer.getHighlight$foundation_release()) && Intrinsics.areEqual(value$foundation_release.getComposingAnnotations(), this.mainBuffer.getComposingAnnotations$foundation_release())) {
                return;
            }
            TextFieldCharSequence value$foundation_release2 = getValue$foundation_release();
            String textFieldBuffer = this.mainBuffer.toString();
            long selectionInChars = this.mainBuffer.getSelectionInChars();
            TextRange composition = this.mainBuffer.getComposition();
            Pair<TextHighlightType, TextRange> highlight$foundation_release = this.mainBuffer.getHighlight$foundation_release();
            m1486finalizeComposingAnnotationsitr0ztk2 = TextFieldStateKt.m1486finalizeComposingAnnotationsitr0ztk(this.mainBuffer.getComposition(), this.mainBuffer.getComposingAnnotations$foundation_release());
            updateValueAndNotifyListeners(value$foundation_release2, new TextFieldCharSequence(textFieldBuffer, selectionInChars, composition, highlight$foundation_release, m1486finalizeComposingAnnotationsitr0ztk2, null, 32, null), restartImeIfContentChanges);
            return;
        }
        boolean z = false;
        boolean z2 = this.mainBuffer.getChangeTracker$foundation_release().getChangeCount() != 0;
        String textFieldBuffer2 = this.mainBuffer.toString();
        long selectionInChars2 = this.mainBuffer.getSelectionInChars();
        TextRange composition2 = this.mainBuffer.getComposition();
        Pair<TextHighlightType, TextRange> highlight$foundation_release2 = this.mainBuffer.getHighlight$foundation_release();
        m1486finalizeComposingAnnotationsitr0ztk = TextFieldStateKt.m1486finalizeComposingAnnotationsitr0ztk(this.mainBuffer.getComposition(), this.mainBuffer.getComposingAnnotations$foundation_release());
        TextFieldCharSequence textFieldCharSequence = new TextFieldCharSequence(textFieldBuffer2, selectionInChars2, composition2, highlight$foundation_release2, m1486finalizeComposingAnnotationsitr0ztk, null, 32, null);
        if (inputTransformation == null) {
            if (z2 && restartImeIfContentChanges) {
                z = true;
            }
            updateValueAndNotifyListeners(value$foundation_release, textFieldCharSequence, z);
            recordEditForUndo(value$foundation_release, textFieldCharSequence, this.mainBuffer.getChangeTracker$foundation_release(), undoBehavior);
            return;
        }
        TextFieldBuffer textFieldBuffer3 = new TextFieldBuffer(textFieldCharSequence, this.mainBuffer.getChangeTracker$foundation_release(), value$foundation_release, null, 8, null);
        inputTransformation.transformInput(textFieldBuffer3);
        boolean contentEquals = StringsKt.contentEquals(textFieldBuffer3.asCharSequence(), textFieldCharSequence);
        boolean z3 = !contentEquals;
        boolean m6841equalsimpl0 = TextRange.m6841equalsimpl0(textFieldBuffer3.getSelectionInChars(), textFieldCharSequence.getSelection());
        boolean z4 = !m6841equalsimpl0;
        if (!contentEquals || !m6841equalsimpl0) {
            syncMainBufferToTemporaryBuffer$foundation_release(textFieldBuffer3, z3, z4);
        } else {
            updateValueAndNotifyListeners(value$foundation_release, TextFieldBuffer.m1470toTextFieldCharSequencewFTz33Y$foundation_release$default(textFieldBuffer3, 0L, textFieldCharSequence.getComposition(), null, null, 13, null), restartImeIfContentChanges);
        }
        recordEditForUndo(value$foundation_release, getValue$foundation_release(), textFieldBuffer3.getChanges(), undoBehavior);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateValueAndNotifyListeners(TextFieldCharSequence oldValue, TextFieldCharSequence newValue, boolean restartImeIfContentChanges) {
        setValue(newValue);
        finishEditing();
        MutableVector<NotifyImeListener> mutableVector = this.notifyImeListeners;
        NotifyImeListener[] notifyImeListenerArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            notifyImeListenerArr[i].onChange(oldValue, newValue, (!restartImeIfContentChanges || oldValue.contentEquals(newValue) || oldValue.getComposition() == null) ? false : true);
        }
    }

    private final void recordEditForUndo(TextFieldCharSequence previousValue, TextFieldCharSequence postValue, TextFieldBuffer.ChangeList changes, TextFieldEditUndoBehavior undoBehavior) {
        int i = WhenMappings.$EnumSwitchMapping$0[undoBehavior.ordinal()];
        if (i == 1) {
            this.textUndoManager.clearHistory();
        } else if (i == 2) {
            TextUndoManagerKt.recordChanges(this.textUndoManager, previousValue, postValue, changes, true);
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            TextUndoManagerKt.recordChanges(this.textUndoManager, previousValue, postValue, changes, false);
        }
    }

    public final void addNotifyImeListener$foundation_release(NotifyImeListener notifyImeListener) {
        this.notifyImeListeners.add(notifyImeListener);
    }

    public final void removeNotifyImeListener$foundation_release(NotifyImeListener notifyImeListener) {
        this.notifyImeListeners.remove(notifyImeListener);
    }

    public final void syncMainBufferToTemporaryBuffer$foundation_release(TextFieldBuffer temporaryBuffer, boolean textChanged, boolean selectionChanged) {
        TextFieldCharSequence m1470toTextFieldCharSequencewFTz33Y$foundation_release$default = TextFieldBuffer.m1470toTextFieldCharSequencewFTz33Y$foundation_release$default(this.mainBuffer, 0L, null, null, null, 15, null);
        if (textChanged) {
            this.mainBuffer = new TextFieldBuffer(new TextFieldCharSequence(temporaryBuffer.toString(), temporaryBuffer.getSelectionInChars(), null, null, null, null, 60, null), null, null, null, 14, null);
        } else if (selectionChanged) {
            this.mainBuffer.m1475setSelection5zctL8(TextRangeKt.TextRange(TextRange.m6848getStartimpl(temporaryBuffer.getSelectionInChars()), TextRange.m6843getEndimpl(temporaryBuffer.getSelectionInChars())));
        }
        if (textChanged || selectionChanged || !Intrinsics.areEqual(m1470toTextFieldCharSequencewFTz33Y$foundation_release$default.getComposition(), temporaryBuffer.getComposition())) {
            this.mainBuffer.commitComposition$foundation_release();
        }
        updateValueAndNotifyListeners(m1470toTextFieldCharSequencewFTz33Y$foundation_release$default, TextFieldBuffer.m1470toTextFieldCharSequencewFTz33Y$foundation_release$default(this.mainBuffer, 0L, null, null, null, 15, null), true);
    }

    /* compiled from: TextFieldState.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0003*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldState$Saver;", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text/input/TextFieldState;", "", "<init>", "()V", "save", "Landroidx/compose/runtime/saveable/SaverScope;", "value", "restore", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Saver implements androidx.compose.runtime.saveable.Saver<TextFieldState, Object> {
        public static final int $stable = 0;
        public static final Saver INSTANCE = new Saver();

        private Saver() {
        }

        @Override // androidx.compose.runtime.saveable.Saver
        public Object save(SaverScope saverScope, TextFieldState textFieldState) {
            return CollectionsKt.listOf(textFieldState.getText().toString(), Integer.valueOf(TextRange.m6848getStartimpl(textFieldState.m1483getSelectiond9O1mEE())), Integer.valueOf(TextRange.m6843getEndimpl(textFieldState.m1483getSelectiond9O1mEE())), TextUndoManager.Companion.Saver.INSTANCE.save(saverScope, textFieldState.getTextUndoManager()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.compose.runtime.saveable.Saver
        public TextFieldState restore(Object value) {
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.collections.List<*>");
            List list = (List) value;
            Object obj = list.get(0);
            Object obj2 = list.get(1);
            Object obj3 = list.get(2);
            Object obj4 = list.get(3);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
            String str = (String) obj;
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
            int intValue = ((Integer) obj2).intValue();
            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
            long TextRange = TextRangeKt.TextRange(intValue, ((Integer) obj3).intValue());
            TextUndoManager.Companion.Saver saver = TextUndoManager.Companion.Saver.INSTANCE;
            Intrinsics.checkNotNull(obj4);
            TextUndoManager restore = saver.restore(obj4);
            Intrinsics.checkNotNull(restore);
            return new TextFieldState(str, TextRange, restore, (DefaultConstructorMarker) null);
        }
    }
}
