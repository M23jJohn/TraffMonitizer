package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.foundation.text.selection.TextFieldPreparedSelection;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextPreparedSelectionState;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteSurroundingTextCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.EditProcessor;
import androidx.compose.ui.text.input.FinishComposingTextCommand;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.core.app.NotificationCompat;
import androidx.core.view.MotionEventCompat;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: TextFieldKeyInput.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0083\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010,\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020.0-H\u0002J\f\u0010,\u001a\u00020\u0017*\u00020.H\u0002J\u0019\u0010/\u001a\u0004\u0018\u0001002\u0006\u00101\u001a\u000202H\u0002¢\u0006\u0004\b3\u00104J\u0015\u00105\u001a\u00020\t2\u0006\u00101\u001a\u000202¢\u0006\u0004\b6\u00107J!\u00108\u001a\u00020\u00172\u0017\u00109\u001a\u0013\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0002\b;H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010+¨\u0006<"}, d2 = {"Landroidx/compose/foundation/text/TextFieldKeyInput;", "", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "selectionManager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "editable", "", "singleLine", "preparedSelectionState", "Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "undoManager", "Landroidx/compose/foundation/text/UndoManager;", "keyCombiner", "Landroidx/compose/foundation/text/DeadKeyCombiner;", "keyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "onValueChange", "Lkotlin/Function1;", "", "imeAction", "Landroidx/compose/ui/text/input/ImeAction;", "<init>", "(Landroidx/compose/foundation/text/LegacyTextFieldState;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/ui/text/input/TextFieldValue;ZZLandroidx/compose/foundation/text/selection/TextPreparedSelectionState;Landroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/foundation/text/UndoManager;Landroidx/compose/foundation/text/DeadKeyCombiner;Landroidx/compose/foundation/text/KeyMapping;Lkotlin/jvm/functions/Function1;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getState", "()Landroidx/compose/foundation/text/LegacyTextFieldState;", "getSelectionManager", "()Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "getValue", "()Landroidx/compose/ui/text/input/TextFieldValue;", "getEditable", "()Z", "getSingleLine", "getPreparedSelectionState", "()Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;", "getOffsetMapping", "()Landroidx/compose/ui/text/input/OffsetMapping;", "getUndoManager", "()Landroidx/compose/foundation/text/UndoManager;", "I", "apply", "", "Landroidx/compose/ui/text/input/EditCommand;", "typedCommand", "Landroidx/compose/ui/text/input/CommitTextCommand;", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/key/KeyEvent;", "typedCommand-ZmokQxo", "(Landroid/view/KeyEvent;)Landroidx/compose/ui/text/input/CommitTextCommand;", "process", "process-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "commandExecutionContext", "block", "Landroidx/compose/foundation/text/selection/TextFieldPreparedSelection;", "Lkotlin/ExtensionFunctionType;", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldKeyInput {
    public static final int $stable = 8;
    private final boolean editable;
    private final int imeAction;
    private final DeadKeyCombiner keyCombiner;
    private final KeyMapping keyMapping;
    private final OffsetMapping offsetMapping;
    private final Function1<TextFieldValue, Unit> onValueChange;
    private final TextPreparedSelectionState preparedSelectionState;
    private final TextFieldSelectionManager selectionManager;
    private final boolean singleLine;
    private final LegacyTextFieldState state;
    private final UndoManager undoManager;
    private final TextFieldValue value;

    /* compiled from: TextFieldKeyInput.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KeyCommand.values().length];
            try {
                iArr[KeyCommand.COPY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KeyCommand.PASTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KeyCommand.CUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[KeyCommand.LEFT_CHAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[KeyCommand.RIGHT_CHAR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[KeyCommand.LEFT_WORD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[KeyCommand.RIGHT_WORD.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[KeyCommand.PREV_PARAGRAPH.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[KeyCommand.NEXT_PARAGRAPH.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[KeyCommand.UP.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[KeyCommand.DOWN.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[KeyCommand.PAGE_UP.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[KeyCommand.PAGE_DOWN.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[KeyCommand.LINE_START.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[KeyCommand.LINE_END.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[KeyCommand.LINE_LEFT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[KeyCommand.LINE_RIGHT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[KeyCommand.HOME.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[KeyCommand.END.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[KeyCommand.DELETE_PREV_CHAR.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[KeyCommand.DELETE_NEXT_CHAR.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[KeyCommand.DELETE_PREV_WORD.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr[KeyCommand.DELETE_NEXT_WORD.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr[KeyCommand.DELETE_FROM_LINE_START.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr[KeyCommand.DELETE_TO_LINE_END.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr[KeyCommand.NEW_LINE.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr[KeyCommand.TAB.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr[KeyCommand.SELECT_ALL.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr[KeyCommand.SELECT_LEFT_CHAR.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                iArr[KeyCommand.SELECT_RIGHT_CHAR.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                iArr[KeyCommand.SELECT_LEFT_WORD.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                iArr[KeyCommand.SELECT_RIGHT_WORD.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                iArr[KeyCommand.SELECT_PREV_PARAGRAPH.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                iArr[KeyCommand.SELECT_NEXT_PARAGRAPH.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                iArr[KeyCommand.SELECT_LINE_START.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                iArr[KeyCommand.SELECT_LINE_END.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                iArr[KeyCommand.SELECT_LINE_LEFT.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                iArr[KeyCommand.SELECT_LINE_RIGHT.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                iArr[KeyCommand.SELECT_UP.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                iArr[KeyCommand.SELECT_DOWN.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                iArr[KeyCommand.SELECT_PAGE_UP.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                iArr[KeyCommand.SELECT_PAGE_DOWN.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                iArr[KeyCommand.SELECT_HOME.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                iArr[KeyCommand.SELECT_END.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                iArr[KeyCommand.DESELECT.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                iArr[KeyCommand.UNDO.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                iArr[KeyCommand.REDO.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                iArr[KeyCommand.CHARACTER_PALETTE.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                iArr[KeyCommand.CENTER.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ TextFieldKeyInput(LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, boolean z, boolean z2, TextPreparedSelectionState textPreparedSelectionState, OffsetMapping offsetMapping, UndoManager undoManager, DeadKeyCombiner deadKeyCombiner, KeyMapping keyMapping, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(legacyTextFieldState, textFieldSelectionManager, textFieldValue, z, z2, textPreparedSelectionState, offsetMapping, undoManager, deadKeyCombiner, keyMapping, function1, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TextFieldKeyInput(LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, boolean z, boolean z2, TextPreparedSelectionState textPreparedSelectionState, OffsetMapping offsetMapping, UndoManager undoManager, DeadKeyCombiner deadKeyCombiner, KeyMapping keyMapping, Function1<? super TextFieldValue, Unit> function1, int i) {
        this.state = legacyTextFieldState;
        this.selectionManager = textFieldSelectionManager;
        this.value = textFieldValue;
        this.editable = z;
        this.singleLine = z2;
        this.preparedSelectionState = textPreparedSelectionState;
        this.offsetMapping = offsetMapping;
        this.undoManager = undoManager;
        this.keyCombiner = deadKeyCombiner;
        this.keyMapping = keyMapping;
        this.onValueChange = function1;
        this.imeAction = i;
    }

    public final LegacyTextFieldState getState() {
        return this.state;
    }

    public final TextFieldSelectionManager getSelectionManager() {
        return this.selectionManager;
    }

    public /* synthetic */ TextFieldKeyInput(LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, boolean z, boolean z2, TextPreparedSelectionState textPreparedSelectionState, OffsetMapping offsetMapping, UndoManager undoManager, DeadKeyCombiner deadKeyCombiner, KeyMapping keyMapping, Function1 function1, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(legacyTextFieldState, textFieldSelectionManager, (i2 & 4) != 0 ? new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null) : textFieldValue, (i2 & 8) != 0 ? true : z, (i2 & 16) != 0 ? false : z2, textPreparedSelectionState, (i2 & 64) != 0 ? OffsetMapping.INSTANCE.getIdentity() : offsetMapping, (i2 & 128) != 0 ? null : undoManager, deadKeyCombiner, (i2 & 512) != 0 ? KeyMapping_androidKt.getPlatformDefaultKeyMapping() : keyMapping, (i2 & 1024) != 0 ? new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit _init_$lambda$0;
                _init_$lambda$0 = TextFieldKeyInput._init_$lambda$0((TextFieldValue) obj);
                return _init_$lambda$0;
            }
        } : function1, i, null);
    }

    public final TextFieldValue getValue() {
        return this.value;
    }

    public final boolean getEditable() {
        return this.editable;
    }

    public final boolean getSingleLine() {
        return this.singleLine;
    }

    public final TextPreparedSelectionState getPreparedSelectionState() {
        return this.preparedSelectionState;
    }

    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    public final UndoManager getUndoManager() {
        return this.undoManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$0(TextFieldValue textFieldValue) {
        return Unit.INSTANCE;
    }

    private final void apply(List<? extends EditCommand> list) {
        EditProcessor processor = this.state.getProcessor();
        List<? extends EditCommand> mutableList = CollectionsKt.toMutableList((Collection) list);
        mutableList.add(0, new FinishComposingTextCommand());
        this.onValueChange.invoke(processor.apply(mutableList));
    }

    private final void apply(EditCommand editCommand) {
        apply(CollectionsKt.listOf(editCommand));
    }

    /* renamed from: typedCommand-ZmokQxo, reason: not valid java name */
    private final CommitTextCommand m1405typedCommandZmokQxo(KeyEvent event) {
        Integer m1301consumeZmokQxo;
        if (TextFieldKeyInput_androidKt.m1411isTypedEventZmokQxo(event) && (m1301consumeZmokQxo = this.keyCombiner.m1301consumeZmokQxo(event)) != null) {
            return new CommitTextCommand(StringHelpers_jvmKt.appendCodePointX(new StringBuilder(), m1301consumeZmokQxo.intValue()).toString(), 1);
        }
        return null;
    }

    /* renamed from: process-ZmokQxo, reason: not valid java name */
    public final boolean m1406processZmokQxo(KeyEvent event) {
        final KeyCommand mo1304mapZmokQxo;
        CommitTextCommand m1405typedCommandZmokQxo = m1405typedCommandZmokQxo(event);
        if (m1405typedCommandZmokQxo != null) {
            if (!this.editable) {
                return false;
            }
            apply(m1405typedCommandZmokQxo);
            this.preparedSelectionState.resetCachedX();
            return true;
        }
        if (!KeyEventType.m5769equalsimpl0(KeyEvent_androidKt.m5777getTypeZmokQxo(event), KeyEventType.INSTANCE.m5773getKeyDownCS__XNY()) || (mo1304mapZmokQxo = this.keyMapping.mo1304mapZmokQxo(event)) == null || (mo1304mapZmokQxo.getEditsText() && !this.editable)) {
            return false;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        commandExecutionContext(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit process_ZmokQxo$lambda$17;
                process_ZmokQxo$lambda$17 = TextFieldKeyInput.process_ZmokQxo$lambda$17(KeyCommand.this, this, booleanRef, (TextFieldPreparedSelection) obj);
                return process_ZmokQxo$lambda$17;
            }
        });
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
        return booleanRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit process_ZmokQxo$lambda$17(KeyCommand keyCommand, TextFieldKeyInput textFieldKeyInput, Ref.BooleanRef booleanRef, TextFieldPreparedSelection textFieldPreparedSelection) {
        TextFieldValue undo;
        TextFieldValue redo;
        switch (keyCommand == null ? -1 : WhenMappings.$EnumSwitchMapping$0[keyCommand.ordinal()]) {
            case 1:
                textFieldKeyInput.selectionManager.copy$foundation_release(false);
                return Unit.INSTANCE;
            case 2:
                textFieldKeyInput.selectionManager.paste$foundation_release();
                return Unit.INSTANCE;
            case 3:
                textFieldKeyInput.selectionManager.cut$foundation_release();
                return Unit.INSTANCE;
            case 4:
                textFieldPreparedSelection.collapseLeftOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit process_ZmokQxo$lambda$17$lambda$3;
                        process_ZmokQxo$lambda$17$lambda$3 = TextFieldKeyInput.process_ZmokQxo$lambda$17$lambda$3((TextFieldPreparedSelection) obj);
                        return process_ZmokQxo$lambda$17$lambda$3;
                    }
                });
                return Unit.INSTANCE;
            case 5:
                textFieldPreparedSelection.collapseRightOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit process_ZmokQxo$lambda$17$lambda$4;
                        process_ZmokQxo$lambda$17$lambda$4 = TextFieldKeyInput.process_ZmokQxo$lambda$17$lambda$4((TextFieldPreparedSelection) obj);
                        return process_ZmokQxo$lambda$17$lambda$4;
                    }
                });
                return Unit.INSTANCE;
            case 6:
                textFieldPreparedSelection.moveCursorLeftByWord();
                return Unit.INSTANCE;
            case 7:
                textFieldPreparedSelection.moveCursorRightByWord();
                return Unit.INSTANCE;
            case 8:
                textFieldPreparedSelection.moveCursorPrevByParagraph();
                return Unit.INSTANCE;
            case 9:
                textFieldPreparedSelection.moveCursorNextByParagraph();
                return Unit.INSTANCE;
            case 10:
                textFieldPreparedSelection.moveCursorUpByLine();
                return Unit.INSTANCE;
            case 11:
                textFieldPreparedSelection.moveCursorDownByLine();
                return Unit.INSTANCE;
            case 12:
                textFieldPreparedSelection.moveCursorUpByPage();
                return Unit.INSTANCE;
            case 13:
                textFieldPreparedSelection.moveCursorDownByPage();
                return Unit.INSTANCE;
            case 14:
                textFieldPreparedSelection.moveCursorToLineStart();
                return Unit.INSTANCE;
            case 15:
                textFieldPreparedSelection.moveCursorToLineEnd();
                return Unit.INSTANCE;
            case 16:
                textFieldPreparedSelection.moveCursorToLineLeftSide();
                return Unit.INSTANCE;
            case 17:
                textFieldPreparedSelection.moveCursorToLineRightSide();
                return Unit.INSTANCE;
            case 18:
                textFieldPreparedSelection.moveCursorToHome();
                return Unit.INSTANCE;
            case 19:
                textFieldPreparedSelection.moveCursorToEnd();
                return Unit.INSTANCE;
            case 20:
                List<EditCommand> deleteIfSelectedOr = textFieldPreparedSelection.deleteIfSelectedOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        EditCommand process_ZmokQxo$lambda$17$lambda$5;
                        process_ZmokQxo$lambda$17$lambda$5 = TextFieldKeyInput.process_ZmokQxo$lambda$17$lambda$5((TextFieldPreparedSelection) obj);
                        return process_ZmokQxo$lambda$17$lambda$5;
                    }
                });
                if (deleteIfSelectedOr != null) {
                    textFieldKeyInput.apply(deleteIfSelectedOr);
                    Unit unit = Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            case 21:
                List<EditCommand> deleteIfSelectedOr2 = textFieldPreparedSelection.deleteIfSelectedOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        EditCommand process_ZmokQxo$lambda$17$lambda$6;
                        process_ZmokQxo$lambda$17$lambda$6 = TextFieldKeyInput.process_ZmokQxo$lambda$17$lambda$6((TextFieldPreparedSelection) obj);
                        return process_ZmokQxo$lambda$17$lambda$6;
                    }
                });
                if (deleteIfSelectedOr2 != null) {
                    textFieldKeyInput.apply(deleteIfSelectedOr2);
                    Unit unit2 = Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            case 22:
                List<EditCommand> deleteIfSelectedOr3 = textFieldPreparedSelection.deleteIfSelectedOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        EditCommand process_ZmokQxo$lambda$17$lambda$8;
                        process_ZmokQxo$lambda$17$lambda$8 = TextFieldKeyInput.process_ZmokQxo$lambda$17$lambda$8((TextFieldPreparedSelection) obj);
                        return process_ZmokQxo$lambda$17$lambda$8;
                    }
                });
                if (deleteIfSelectedOr3 != null) {
                    textFieldKeyInput.apply(deleteIfSelectedOr3);
                    Unit unit3 = Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            case 23:
                List<EditCommand> deleteIfSelectedOr4 = textFieldPreparedSelection.deleteIfSelectedOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        EditCommand process_ZmokQxo$lambda$17$lambda$10;
                        process_ZmokQxo$lambda$17$lambda$10 = TextFieldKeyInput.process_ZmokQxo$lambda$17$lambda$10((TextFieldPreparedSelection) obj);
                        return process_ZmokQxo$lambda$17$lambda$10;
                    }
                });
                if (deleteIfSelectedOr4 != null) {
                    textFieldKeyInput.apply(deleteIfSelectedOr4);
                    Unit unit4 = Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            case 24:
                List<EditCommand> deleteIfSelectedOr5 = textFieldPreparedSelection.deleteIfSelectedOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        EditCommand process_ZmokQxo$lambda$17$lambda$12;
                        process_ZmokQxo$lambda$17$lambda$12 = TextFieldKeyInput.process_ZmokQxo$lambda$17$lambda$12((TextFieldPreparedSelection) obj);
                        return process_ZmokQxo$lambda$17$lambda$12;
                    }
                });
                if (deleteIfSelectedOr5 != null) {
                    textFieldKeyInput.apply(deleteIfSelectedOr5);
                    Unit unit5 = Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            case 25:
                List<EditCommand> deleteIfSelectedOr6 = textFieldPreparedSelection.deleteIfSelectedOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        EditCommand process_ZmokQxo$lambda$17$lambda$14;
                        process_ZmokQxo$lambda$17$lambda$14 = TextFieldKeyInput.process_ZmokQxo$lambda$17$lambda$14((TextFieldPreparedSelection) obj);
                        return process_ZmokQxo$lambda$17$lambda$14;
                    }
                });
                if (deleteIfSelectedOr6 != null) {
                    textFieldKeyInput.apply(deleteIfSelectedOr6);
                    Unit unit6 = Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            case 26:
                if (!textFieldKeyInput.singleLine) {
                    textFieldKeyInput.apply(new CommitTextCommand("\n", 1));
                } else {
                    booleanRef.element = textFieldKeyInput.state.getOnImeActionPerformedWithResult().invoke(ImeAction.m7017boximpl(textFieldKeyInput.imeAction)).booleanValue();
                }
                Unit unit7 = Unit.INSTANCE;
                return Unit.INSTANCE;
            case 27:
                if (!textFieldKeyInput.singleLine) {
                    textFieldKeyInput.apply(new CommitTextCommand("\t", 1));
                } else {
                    booleanRef.element = false;
                }
                Unit unit8 = Unit.INSTANCE;
                return Unit.INSTANCE;
            case 28:
                textFieldPreparedSelection.selectAll();
                return Unit.INSTANCE;
            case 29:
                textFieldPreparedSelection.moveCursorLeft().selectMovement();
                return Unit.INSTANCE;
            case 30:
                textFieldPreparedSelection.moveCursorRight().selectMovement();
                return Unit.INSTANCE;
            case 31:
                textFieldPreparedSelection.moveCursorLeftByWord().selectMovement();
                return Unit.INSTANCE;
            case 32:
                textFieldPreparedSelection.moveCursorRightByWord().selectMovement();
                return Unit.INSTANCE;
            case 33:
                textFieldPreparedSelection.moveCursorPrevByParagraph().selectMovement();
                return Unit.INSTANCE;
            case 34:
                textFieldPreparedSelection.moveCursorNextByParagraph().selectMovement();
                return Unit.INSTANCE;
            case 35:
                textFieldPreparedSelection.moveCursorToLineStart().selectMovement();
                return Unit.INSTANCE;
            case 36:
                textFieldPreparedSelection.moveCursorToLineEnd().selectMovement();
                return Unit.INSTANCE;
            case 37:
                textFieldPreparedSelection.moveCursorToLineLeftSide().selectMovement();
                return Unit.INSTANCE;
            case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                textFieldPreparedSelection.moveCursorToLineRightSide().selectMovement();
                return Unit.INSTANCE;
            case 39:
                textFieldPreparedSelection.moveCursorUpByLine().selectMovement();
                return Unit.INSTANCE;
            case 40:
                textFieldPreparedSelection.moveCursorDownByLine().selectMovement();
                return Unit.INSTANCE;
            case 41:
                textFieldPreparedSelection.moveCursorUpByPage().selectMovement();
                return Unit.INSTANCE;
            case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                textFieldPreparedSelection.moveCursorDownByPage().selectMovement();
                return Unit.INSTANCE;
            case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                textFieldPreparedSelection.moveCursorToHome().selectMovement();
                return Unit.INSTANCE;
            case 44:
                textFieldPreparedSelection.moveCursorToEnd().selectMovement();
                return Unit.INSTANCE;
            case 45:
                textFieldPreparedSelection.deselect();
                return Unit.INSTANCE;
            case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                UndoManager undoManager = textFieldKeyInput.undoManager;
                if (undoManager != null) {
                    undoManager.makeSnapshot(textFieldPreparedSelection.getValue());
                }
                UndoManager undoManager2 = textFieldKeyInput.undoManager;
                if (undoManager2 != null && (undo = undoManager2.undo()) != null) {
                    textFieldKeyInput.onValueChange.invoke(undo);
                    Unit unit9 = Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                UndoManager undoManager3 = textFieldKeyInput.undoManager;
                if (undoManager3 != null && (redo = undoManager3.redo()) != null) {
                    textFieldKeyInput.onValueChange.invoke(redo);
                    Unit unit10 = Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            case 48:
                KeyEventHelpers_androidKt.showCharacterPalette();
            case 49:
                Unit unit11 = Unit.INSTANCE;
                return Unit.INSTANCE;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit process_ZmokQxo$lambda$17$lambda$3(TextFieldPreparedSelection textFieldPreparedSelection) {
        textFieldPreparedSelection.moveCursorLeft();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit process_ZmokQxo$lambda$17$lambda$4(TextFieldPreparedSelection textFieldPreparedSelection) {
        textFieldPreparedSelection.moveCursorRight();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditCommand process_ZmokQxo$lambda$17$lambda$5(TextFieldPreparedSelection textFieldPreparedSelection) {
        int precedingCodePointOrEmojiStartIndex = textFieldPreparedSelection.getPrecedingCodePointOrEmojiStartIndex();
        if (precedingCodePointOrEmojiStartIndex == -1) {
            return null;
        }
        return new DeleteSurroundingTextCommand(TextRange.m6843getEndimpl(textFieldPreparedSelection.getSelection()) - precedingCodePointOrEmojiStartIndex, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditCommand process_ZmokQxo$lambda$17$lambda$6(TextFieldPreparedSelection textFieldPreparedSelection) {
        int nextCharacterIndex = textFieldPreparedSelection.getNextCharacterIndex();
        if (nextCharacterIndex != -1) {
            return new DeleteSurroundingTextCommand(0, nextCharacterIndex - TextRange.m6843getEndimpl(textFieldPreparedSelection.getSelection()));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditCommand process_ZmokQxo$lambda$17$lambda$8(TextFieldPreparedSelection textFieldPreparedSelection) {
        DeleteSurroundingTextCommand deleteSurroundingTextCommand;
        Integer previousWordOffset = textFieldPreparedSelection.getPreviousWordOffset();
        if (previousWordOffset != null) {
            deleteSurroundingTextCommand = new DeleteSurroundingTextCommand(TextRange.m6843getEndimpl(textFieldPreparedSelection.getSelection()) - previousWordOffset.intValue(), 0);
        } else {
            deleteSurroundingTextCommand = null;
        }
        return deleteSurroundingTextCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditCommand process_ZmokQxo$lambda$17$lambda$10(TextFieldPreparedSelection textFieldPreparedSelection) {
        Integer nextWordOffset = textFieldPreparedSelection.getNextWordOffset();
        return nextWordOffset != null ? new DeleteSurroundingTextCommand(0, nextWordOffset.intValue() - TextRange.m6843getEndimpl(textFieldPreparedSelection.getSelection())) : null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditCommand process_ZmokQxo$lambda$17$lambda$12(TextFieldPreparedSelection textFieldPreparedSelection) {
        DeleteSurroundingTextCommand deleteSurroundingTextCommand;
        Integer lineStartByOffset = textFieldPreparedSelection.getLineStartByOffset();
        if (lineStartByOffset != null) {
            deleteSurroundingTextCommand = new DeleteSurroundingTextCommand(TextRange.m6843getEndimpl(textFieldPreparedSelection.getSelection()) - lineStartByOffset.intValue(), 0);
        } else {
            deleteSurroundingTextCommand = null;
        }
        return deleteSurroundingTextCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditCommand process_ZmokQxo$lambda$17$lambda$14(TextFieldPreparedSelection textFieldPreparedSelection) {
        Integer lineEndByOffset = textFieldPreparedSelection.getLineEndByOffset();
        return lineEndByOffset != null ? new DeleteSurroundingTextCommand(0, lineEndByOffset.intValue() - TextRange.m6843getEndimpl(textFieldPreparedSelection.getSelection())) : null;
    }

    private final void commandExecutionContext(Function1<? super TextFieldPreparedSelection, Unit> block) {
        TextFieldPreparedSelection textFieldPreparedSelection = new TextFieldPreparedSelection(this.value, this.offsetMapping, this.state.getLayoutResult(), this.preparedSelectionState);
        block.invoke(textFieldPreparedSelection);
        if (TextRange.m6841equalsimpl0(textFieldPreparedSelection.getSelection(), this.value.getSelection()) && Intrinsics.areEqual(textFieldPreparedSelection.getAnnotatedString(), this.value.getAnnotatedString())) {
            return;
        }
        this.onValueChange.invoke(textFieldPreparedSelection.getValue());
    }
}
