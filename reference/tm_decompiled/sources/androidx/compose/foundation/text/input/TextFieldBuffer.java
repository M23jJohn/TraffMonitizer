package androidx.compose.foundation.text.input;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.input.internal.ChangeTracker;
import androidx.compose.foundation.text.input.internal.OffsetMappingCalculator;
import androidx.compose.foundation.text.input.internal.PartialGapBuffer;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.ParagraphStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* compiled from: TextFieldBuffer.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\f\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0002\u0086\u0001B3\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010;\u001a\u00020'H\u0000¢\u0006\u0002\b<J\r\u0010=\u001a\u00020>H\u0000¢\u0006\u0002\b?J9\u0010@\u001a\u00020>2\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u00152\u001a\b\u0002\u0010C\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020706j\u0002`E\u0018\u00010DH\u0000¢\u0006\u0002\bFJ'\u0010L\u001a\u00020>2\u0006\u0010M\u001a\u00020H2\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u0015H\u0000¢\u0006\u0004\bN\u0010OJ\r\u0010P\u001a\u00020>H\u0000¢\u0006\u0002\bQJ\u001e\u0010R\u001a\u00020>2\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u00152\u0006\u0010S\u001a\u00020\u0019J9\u0010R\u001a\u00020>2\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u00152\u0006\u0010S\u001a\u00020\u00192\b\b\u0002\u0010T\u001a\u00020\u00152\b\b\u0002\u0010U\u001a\u00020\u0015H\u0000¢\u0006\u0002\bVJ\u0015\u0010W\u001a\u00020>2\u0006\u0010X\u001a\u00020\u0019H\u0000¢\u0006\u0002\bYJ\u0016\u0010Z\u001a\u00060\u0001j\u0002`\u00022\b\u0010S\u001a\u0004\u0018\u00010\u0019H\u0016J&\u0010Z\u001a\u00060\u0001j\u0002`\u00022\b\u0010S\u001a\u0004\u0018\u00010\u00192\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u0015H\u0016J\u0014\u0010Z\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010[\u001a\u00020\\H\u0016J \u0010]\u001a\u00020>2\u0006\u0010^\u001a\u00020\u00152\u0006\u0010_\u001a\u00020\u00152\u0006\u0010`\u001a\u00020\u0015H\u0002J\u000e\u0010a\u001a\u00020\\2\u0006\u0010b\u001a\u00020\u0015J\b\u0010c\u001a\u00020dH\u0016J\u0006\u0010e\u001a\u00020\u0019J\b\u0010f\u001a\u00020>H\u0002J\u0006\u0010g\u001a\u00020>J\u000e\u0010h\u001a\u00020>2\u0006\u0010b\u001a\u00020\u0015J\u000e\u0010i\u001a\u00020>2\u0006\u0010b\u001a\u00020\u0015J]\u0010j\u001a\u00020\u00042\b\b\u0002\u0010,\u001a\u00020\u001d2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u001d2\u001a\b\u0002\u00108\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020706j\u0002`E\u0018\u00010D2\u001a\b\u0002\u0010k\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020706j\u0002`E\u0018\u00010DH\u0000¢\u0006\u0004\bl\u0010mJ \u0010n\u001a\u00020>2\u0006\u0010b\u001a\u00020\u00152\u0006\u0010o\u001a\u00020'2\u0006\u0010p\u001a\u00020'H\u0002J\u0017\u0010q\u001a\u00020>2\u0006\u0010r\u001a\u00020\u001dH\u0002¢\u0006\u0004\bs\u0010/J&\u0010~\u001a\u00020>2\u0006\u0010\u007f\u001a\u0002072\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u0015H\u0000¢\u0006\u0003\b\u0080\u0001J!\u0010\u0081\u0001\u001a\u00020>2\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u0015J!\u0010\u0081\u0001\u001a\u00020>2\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u0015R\u0014\u0010\u0007\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020'8G¢\u0006\u0006\u001a\u0004\b&\u0010(R\u0010\u0010)\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0004\n\u0002\u0010*R$\u0010,\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\u001d8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b-\u0010\u001f\"\u0004\b.\u0010/R(\u00100\u001a\u0004\u0018\u00010\u001d2\b\u0010+\u001a\u0004\u0018\u00010\u001d@BX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R:\u00108\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020706\u0018\u0001052\u0014\u0010+\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020706\u0018\u000105@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R:\u0010I\u001a\u0010\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u001d\u0018\u00010G2\u0014\u0010+\u001a\u0010\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u001d\u0018\u00010G@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010KR\u001a\u0010t\u001a\u00020'X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010(\"\u0004\bv\u0010wR,\u0010x\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020706j\u0002`E\u0018\u00010yX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}¨\u0006\u0087\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldBuffer;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "initialValue", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "initialChanges", "Landroidx/compose/foundation/text/input/internal/ChangeTracker;", "originalValue", "offsetMappingCalculator", "Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;", "<init>", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;Landroidx/compose/foundation/text/input/internal/ChangeTracker;Landroidx/compose/foundation/text/input/TextFieldCharSequence;Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;)V", "getOriginalValue$foundation_release", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "buffer", "Landroidx/compose/foundation/text/input/internal/PartialGapBuffer;", "backingChangeTracker", "changeTracker", "getChangeTracker$foundation_release", "()Landroidx/compose/foundation/text/input/internal/ChangeTracker;", "length", "", "getLength", "()I", "originalText", "", "getOriginalText", "()Ljava/lang/CharSequence;", "originalSelection", "Landroidx/compose/ui/text/TextRange;", "getOriginalSelection-d9O1mEE", "()J", "changes", "Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "getChanges$annotations", "()V", "getChanges", "()Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "hasSelection", "", "()Z", "selectionInChars", "J", "value", "selection", "getSelection-d9O1mEE", "setSelection-5zc-tL8", "(J)V", "composition", "getComposition-MzsxiRA$foundation_release", "()Landroidx/compose/ui/text/TextRange;", "setComposition-OEnZFl4", "(Landroidx/compose/ui/text/TextRange;)V", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "composingAnnotations", "getComposingAnnotations$foundation_release", "()Landroidx/compose/runtime/collection/MutableVector;", "hasComposition", "hasComposition$foundation_release", "commitComposition", "", "commitComposition$foundation_release", "setComposition", "start", "end", "annotations", "", "Landroidx/compose/foundation/text/input/PlacedAnnotation;", "setComposition$foundation_release", "Lkotlin/Pair;", "Landroidx/compose/foundation/text/input/TextHighlightType;", "highlight", "getHighlight$foundation_release", "()Lkotlin/Pair;", "setHighlight", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "setHighlight-K7f2yys$foundation_release", "(III)V", "clearHighlight", "clearHighlight$foundation_release", "replace", "text", "textStart", "textEnd", "replace$foundation_release", "setTextIfChanged", "newText", "setTextIfChanged$foundation_release", "append", "char", "", "onTextWillChange", "replaceStart", "replaceEnd", "newLength", "charAt", "index", "toString", "", "asCharSequence", "clearChangeList", "revertAllChanges", "placeCursorBeforeCharAt", "placeCursorAfterCharAt", "toTextFieldCharSequence", "outputAnnotations", "toTextFieldCharSequence-wFTz33Y$foundation_release", "(JLandroidx/compose/ui/text/TextRange;Ljava/util/List;Ljava/util/List;)Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "requireValidIndex", "startExclusive", "endExclusive", "requireValidRange", "range", "requireValidRange-5zc-tL8", "canCallAddStyle", "getCanCallAddStyle$foundation_release", "setCanCallAddStyle$foundation_release", "(Z)V", "outputTransformationAnnotations", "", "getOutputTransformationAnnotations$foundation_release", "()Ljava/util/List;", "setOutputTransformationAnnotations$foundation_release", "(Ljava/util/List;)V", "addAnnotation", "annotation", "addAnnotation$foundation_release", "addStyle", "spanStyle", "Landroidx/compose/ui/text/SpanStyle;", "paragraphStyle", "Landroidx/compose/ui/text/ParagraphStyle;", "ChangeList", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldBuffer implements Appendable {
    public static final int $stable = 8;
    private ChangeTracker backingChangeTracker;
    private final PartialGapBuffer buffer;
    private boolean canCallAddStyle;
    private MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> composingAnnotations;
    private TextRange composition;
    private Pair<TextHighlightType, TextRange> highlight;
    private final OffsetMappingCalculator offsetMappingCalculator;
    private final TextFieldCharSequence originalValue;
    private List<AnnotatedString.Range<AnnotatedString.Annotation>> outputTransformationAnnotations;
    private long selectionInChars;

    /* compiled from: TextFieldBuffer.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&¢\u0006\u0004\b\f\u0010\nR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "", "changeCount", "", "getChangeCount", "()I", "getRange", "Landroidx/compose/ui/text/TextRange;", "changeIndex", "getRange--jx7JFs", "(I)J", "getOriginalRange", "getOriginalRange--jx7JFs", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface ChangeList {
        int getChangeCount();

        /* renamed from: getOriginalRange--jx7JFs, reason: not valid java name */
        long mo1477getOriginalRangejx7JFs(int changeIndex);

        /* renamed from: getRange--jx7JFs, reason: not valid java name */
        long mo1478getRangejx7JFs(int changeIndex);
    }

    public static /* synthetic */ void getChanges$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TextFieldBuffer(final TextFieldCharSequence textFieldCharSequence, ChangeTracker changeTracker, TextFieldCharSequence textFieldCharSequence2, OffsetMappingCalculator offsetMappingCalculator) {
        this.originalValue = textFieldCharSequence2;
        this.offsetMappingCalculator = offsetMappingCalculator;
        this.buffer = new PartialGapBuffer(textFieldCharSequence);
        MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> mutableVector = null;
        this.backingChangeTracker = changeTracker != null ? new ChangeTracker(changeTracker) : null;
        this.selectionInChars = textFieldCharSequence.getSelection();
        this.composition = textFieldCharSequence.getComposition();
        List<AnnotatedString.Range<AnnotatedString.Annotation>> composingAnnotations = textFieldCharSequence.getComposingAnnotations();
        if (composingAnnotations != null && !composingAnnotations.isEmpty()) {
            int size = textFieldCharSequence.getComposingAnnotations().size();
            Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.input.TextFieldBuffer$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    AnnotatedString.Range composingAnnotations$lambda$2;
                    composingAnnotations$lambda$2 = TextFieldBuffer.composingAnnotations$lambda$2(TextFieldCharSequence.this, ((Integer) obj).intValue());
                    return composingAnnotations$lambda$2;
                }
            };
            AnnotatedString.Range[] rangeArr = new AnnotatedString.Range[size];
            for (int i = 0; i < size; i++) {
                rangeArr[i] = function1.invoke(Integer.valueOf(i));
            }
            mutableVector = new MutableVector<>(rangeArr, size);
        }
        this.composingAnnotations = mutableVector;
        this.canCallAddStyle = this.offsetMappingCalculator != null;
    }

    public /* synthetic */ TextFieldBuffer(TextFieldCharSequence textFieldCharSequence, ChangeTracker changeTracker, TextFieldCharSequence textFieldCharSequence2, OffsetMappingCalculator offsetMappingCalculator, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(textFieldCharSequence, (i & 2) != 0 ? null : changeTracker, (i & 4) != 0 ? textFieldCharSequence : textFieldCharSequence2, (i & 8) != 0 ? null : offsetMappingCalculator);
    }

    /* renamed from: getOriginalValue$foundation_release, reason: from getter */
    public final TextFieldCharSequence getOriginalValue() {
        return this.originalValue;
    }

    public final ChangeTracker getChangeTracker$foundation_release() {
        ChangeTracker changeTracker = this.backingChangeTracker;
        if (changeTracker != null) {
            return changeTracker;
        }
        ChangeTracker changeTracker2 = new ChangeTracker(null, 1, null);
        this.backingChangeTracker = changeTracker2;
        return changeTracker2;
    }

    public final int getLength() {
        return this.buffer.length();
    }

    public final CharSequence getOriginalText() {
        return this.originalValue.getText();
    }

    /* renamed from: getOriginalSelection-d9O1mEE, reason: not valid java name */
    public final long m1472getOriginalSelectiond9O1mEE() {
        return this.originalValue.getSelection();
    }

    public final ChangeList getChanges() {
        return getChangeTracker$foundation_release();
    }

    public final boolean hasSelection() {
        return !TextRange.m6842getCollapsedimpl(getSelectionInChars());
    }

    /* renamed from: getSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getSelectionInChars() {
        return this.selectionInChars;
    }

    /* renamed from: setSelection-5zc-tL8, reason: not valid java name */
    public final void m1475setSelection5zctL8(long j) {
        m1468requireValidRange5zctL8(j);
        this.selectionInChars = j;
        this.highlight = null;
    }

    /* renamed from: getComposition-MzsxiRA$foundation_release, reason: not valid java name and from getter */
    public final TextRange getComposition() {
        return this.composition;
    }

    /* renamed from: setComposition-OEnZFl4, reason: not valid java name */
    private final void m1469setCompositionOEnZFl4(TextRange textRange) {
        if (textRange == null || TextRange.m6842getCollapsedimpl(textRange.getPackedValue())) {
            this.composition = null;
            MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> mutableVector = this.composingAnnotations;
            if (mutableVector != null) {
                mutableVector.clear();
                return;
            }
            return;
        }
        this.composition = textRange;
    }

    public final MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> getComposingAnnotations$foundation_release() {
        return this.composingAnnotations;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnnotatedString.Range composingAnnotations$lambda$2(TextFieldCharSequence textFieldCharSequence, int i) {
        return textFieldCharSequence.getComposingAnnotations().get(i);
    }

    public final boolean hasComposition$foundation_release() {
        return this.composition != null;
    }

    public final void commitComposition$foundation_release() {
        m1469setCompositionOEnZFl4(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void setComposition$foundation_release$default(TextFieldBuffer textFieldBuffer, int i, int i2, List list, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            list = null;
        }
        textFieldBuffer.setComposition$foundation_release(i, i2, list);
    }

    public final void setComposition$foundation_release(int start, int end, List<AnnotatedString.Range<AnnotatedString.Annotation>> annotations) {
        if (start < 0 || start > this.buffer.length()) {
            throw new IndexOutOfBoundsException("start (" + start + ") offset is outside of text region " + this.buffer.length());
        }
        if (end < 0 || end > this.buffer.length()) {
            throw new IndexOutOfBoundsException("end (" + end + ") offset is outside of text region " + this.buffer.length());
        }
        if (start >= end) {
            throw new IllegalArgumentException("Do not set reversed or empty range: " + start + " > " + end);
        }
        m1469setCompositionOEnZFl4(TextRange.m6836boximpl(TextRangeKt.TextRange(start, end)));
        MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> mutableVector = this.composingAnnotations;
        if (mutableVector != null) {
            mutableVector.clear();
        }
        List<AnnotatedString.Range<AnnotatedString.Annotation>> list = annotations;
        if (list == null || list.isEmpty()) {
            return;
        }
        if (this.composingAnnotations == null) {
            this.composingAnnotations = new MutableVector<>(new AnnotatedString.Range[16], 0);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnnotatedString.Range<AnnotatedString.Annotation> range = annotations.get(i);
            MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> mutableVector2 = this.composingAnnotations;
            if (mutableVector2 != null) {
                mutableVector2.add(AnnotatedString.Range.copy$default(range, null, range.getStart() + start, range.getEnd() + start, null, 9, null));
            }
        }
    }

    public final Pair<TextHighlightType, TextRange> getHighlight$foundation_release() {
        return this.highlight;
    }

    /* renamed from: setHighlight-K7f2yys$foundation_release, reason: not valid java name */
    public final void m1474setHighlightK7f2yys$foundation_release(int type, int start, int end) {
        if (start >= end) {
            throw new IllegalArgumentException("Do not set reversed or empty range: " + start + " > " + end);
        }
        this.highlight = new Pair<>(TextHighlightType.m1488boximpl(type), TextRange.m6836boximpl(TextRangeKt.TextRange(RangesKt.coerceIn(start, 0, getLength()), RangesKt.coerceIn(end, 0, getLength()))));
    }

    public final void clearHighlight$foundation_release() {
        this.highlight = null;
    }

    public final void replace(int start, int end, CharSequence text) {
        replace$foundation_release(start, end, text, 0, text.length());
    }

    public static /* synthetic */ void replace$foundation_release$default(TextFieldBuffer textFieldBuffer, int i, int i2, CharSequence charSequence, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i3 = 0;
        }
        int i6 = i3;
        if ((i5 & 16) != 0) {
            i4 = charSequence.length();
        }
        textFieldBuffer.replace$foundation_release(i, i2, charSequence, i6, i4);
    }

    public final void replace$foundation_release(int start, int end, CharSequence text, int textStart, int textEnd) {
        if (!(start <= end)) {
            InlineClassHelperKt.throwIllegalArgumentException("Expected start=" + start + " <= end=" + end);
        }
        if (!(textStart <= textEnd)) {
            InlineClassHelperKt.throwIllegalArgumentException("Expected textStart=" + textStart + " <= textEnd=" + textEnd);
        }
        onTextWillChange(start, end, textEnd - textStart);
        this.buffer.replace(start, end, text, textStart, textEnd);
        commitComposition$foundation_release();
        clearHighlight$foundation_release();
    }

    public final void setTextIfChanged$foundation_release(CharSequence newText) {
        int i;
        int i2;
        PartialGapBuffer partialGapBuffer = this.buffer;
        int length = partialGapBuffer.length();
        int length2 = newText.length();
        boolean z = false;
        if (partialGapBuffer.length() <= 0 || newText.length() <= 0) {
            i = 0;
            i2 = 0;
        } else {
            int i3 = 0;
            int i4 = 0;
            boolean z2 = false;
            while (true) {
                if (!z) {
                    if (partialGapBuffer.charAt(i3) == newText.charAt(i4)) {
                        i3++;
                        i4++;
                    } else {
                        z = true;
                    }
                }
                if (!z2) {
                    if (partialGapBuffer.charAt(length - 1) == newText.charAt(length2 - 1)) {
                        length--;
                        length2--;
                    } else {
                        z2 = true;
                    }
                }
                if (i3 >= length || i4 >= length2 || (z && z2)) {
                    break;
                }
            }
            i = i3;
            i2 = i4;
        }
        int i5 = length;
        int i6 = length2;
        if (i < i5 || i2 < i6) {
            replace$foundation_release(i, i5, newText, i2, i6);
        }
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence text) {
        if (text != null) {
            onTextWillChange(getLength(), getLength(), text.length());
            PartialGapBuffer partialGapBuffer = this.buffer;
            PartialGapBuffer.replace$default(partialGapBuffer, partialGapBuffer.length(), this.buffer.length(), text, 0, 0, 24, null);
        }
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence text, int start, int end) {
        if (text != null) {
            onTextWillChange(getLength(), getLength(), end - start);
            PartialGapBuffer partialGapBuffer = this.buffer;
            PartialGapBuffer.replace$default(partialGapBuffer, partialGapBuffer.length(), this.buffer.length(), text.subSequence(start, end), 0, 0, 24, null);
        }
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(char r12) {
        onTextWillChange(getLength(), getLength(), 1);
        PartialGapBuffer partialGapBuffer = this.buffer;
        PartialGapBuffer.replace$default(partialGapBuffer, partialGapBuffer.length(), this.buffer.length(), String.valueOf(r12), 0, 0, 24, null);
        return this;
    }

    private final void onTextWillChange(int replaceStart, int replaceEnd, int newLength) {
        getChangeTracker$foundation_release().trackChange(replaceStart, replaceEnd, newLength);
        OffsetMappingCalculator offsetMappingCalculator = this.offsetMappingCalculator;
        if (offsetMappingCalculator != null) {
            offsetMappingCalculator.recordEditOperation(replaceStart, replaceEnd, newLength);
        }
        this.selectionInChars = TextFieldBufferKt.m1479adjustTextRangevJH6DeI(getSelectionInChars(), replaceStart, replaceEnd, newLength);
    }

    public final char charAt(int index) {
        return this.buffer.charAt(index);
    }

    public String toString() {
        return this.buffer.toString();
    }

    public final CharSequence asCharSequence() {
        return this.buffer;
    }

    private final void clearChangeList() {
        getChangeTracker$foundation_release().clearChanges();
    }

    public final void revertAllChanges() {
        replace(0, getLength(), this.originalValue.toString());
        m1475setSelection5zctL8(this.originalValue.getSelection());
        clearChangeList();
    }

    public final void placeCursorBeforeCharAt(int index) {
        requireValidIndex(index, true, false);
        this.selectionInChars = TextRangeKt.TextRange(index);
    }

    public final void placeCursorAfterCharAt(int index) {
        requireValidIndex(index, false, true);
        this.selectionInChars = TextRangeKt.TextRange(RangesKt.coerceAtMost(index + 1, getLength()));
    }

    /* renamed from: toTextFieldCharSequence-wFTz33Y$foundation_release$default, reason: not valid java name */
    public static /* synthetic */ TextFieldCharSequence m1470toTextFieldCharSequencewFTz33Y$foundation_release$default(TextFieldBuffer textFieldBuffer, long j, TextRange textRange, List list, List list2, int i, Object obj) {
        List<AnnotatedString.Range<AnnotatedString.Annotation>> asMutableList;
        if ((i & 1) != 0) {
            j = textFieldBuffer.getSelectionInChars();
        }
        long j2 = j;
        if ((i & 2) != 0) {
            textRange = textFieldBuffer.composition;
        }
        TextRange textRange2 = textRange;
        if ((i & 4) != 0) {
            MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> mutableVector = textFieldBuffer.composingAnnotations;
            list = (mutableVector == null || (asMutableList = mutableVector.asMutableList()) == null || asMutableList.isEmpty()) ? null : asMutableList;
        }
        return textFieldBuffer.m1476toTextFieldCharSequencewFTz33Y$foundation_release(j2, textRange2, list, (i & 8) != 0 ? null : list2);
    }

    /* renamed from: toTextFieldCharSequence-wFTz33Y$foundation_release, reason: not valid java name */
    public final TextFieldCharSequence m1476toTextFieldCharSequencewFTz33Y$foundation_release(long selection, TextRange composition, List<AnnotatedString.Range<AnnotatedString.Annotation>> composingAnnotations, List<AnnotatedString.Range<AnnotatedString.Annotation>> outputAnnotations) {
        return new TextFieldCharSequence(this.buffer.toString(), selection, composition, null, composingAnnotations, outputAnnotations, 8, null);
    }

    private final void requireValidIndex(int index, boolean startExclusive, boolean endExclusive) {
        boolean z = false;
        int i = startExclusive ? 0 : -1;
        int length = endExclusive ? getLength() : getLength() + 1;
        if (i <= index && index < length) {
            z = true;
        }
        if (z) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("Expected " + index + " to be in [" + i + ", " + length + ')');
    }

    /* renamed from: requireValidRange-5zc-tL8, reason: not valid java name */
    private final void m1468requireValidRange5zctL8(long range) {
        long TextRange = TextRangeKt.TextRange(0, getLength());
        if (TextRange.m6838contains5zctL8(TextRange, range)) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("Expected " + ((Object) TextRange.m6851toStringimpl(range)) + " to be in " + ((Object) TextRange.m6851toStringimpl(TextRange)));
    }

    /* renamed from: getCanCallAddStyle$foundation_release, reason: from getter */
    public final boolean getCanCallAddStyle() {
        return this.canCallAddStyle;
    }

    public final void setCanCallAddStyle$foundation_release(boolean z) {
        this.canCallAddStyle = z;
    }

    public final List<AnnotatedString.Range<AnnotatedString.Annotation>> getOutputTransformationAnnotations$foundation_release() {
        return this.outputTransformationAnnotations;
    }

    public final void setOutputTransformationAnnotations$foundation_release(List<AnnotatedString.Range<AnnotatedString.Annotation>> list) {
        this.outputTransformationAnnotations = list;
    }

    public final void addAnnotation$foundation_release(AnnotatedString.Annotation annotation, int start, int end) {
        if (!this.canCallAddStyle) {
            InlineClassHelperKt.throwIllegalStateException("You can add styling to a [TextFieldBuffer] only from an [OutputTransformation].");
        }
        if (this.outputTransformationAnnotations == null) {
            this.outputTransformationAnnotations = new ArrayList();
        }
        List<AnnotatedString.Range<AnnotatedString.Annotation>> list = this.outputTransformationAnnotations;
        if (list != null) {
            list.add(new AnnotatedString.Range<>(annotation, start, end));
        }
    }

    public final void addStyle(SpanStyle spanStyle, int start, int end) {
        addAnnotation$foundation_release(spanStyle, start, end);
    }

    public final void addStyle(ParagraphStyle paragraphStyle, int start, int end) {
        addAnnotation$foundation_release(paragraphStyle, start, end);
    }
}
