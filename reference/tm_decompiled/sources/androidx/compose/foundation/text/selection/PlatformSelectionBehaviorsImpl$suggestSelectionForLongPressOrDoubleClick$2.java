package androidx.compose.foundation.text.selection;

import android.os.Build;
import android.os.LocaleList;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;
import androidx.compose.material3.internal.Listener$$ExternalSyntheticApiModelOutline0;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;

/* compiled from: PlatformSelectionBehaviors.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/TextRange;", "Landroid/view/textclassifier/TextClassifier;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2", f = "PlatformSelectionBehaviors.android.kt", i = {0, 0, 0, 1}, l = {351, 158}, m = "invokeSuspend", n = {"suggestedSelection", "$this$withLock_u24default$iv", "newSelection", "newSelection"}, s = {"L$0", "L$1", "J$0", "J$0"})
/* loaded from: classes.dex */
final class PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2 extends SuspendLambda implements Function2<TextClassifier, Continuation<? super TextRange>, Object> {
    final /* synthetic */ long $selection;
    final /* synthetic */ CharSequence $text;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ PlatformSelectionBehaviorsImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2(CharSequence charSequence, long j, PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl, Continuation<? super PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2> continuation) {
        super(2, continuation);
        this.$text = charSequence;
        this.$selection = j;
        this.this$0 = platformSelectionBehaviorsImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2 platformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2 = new PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2(this.$text, this.$selection, this.this$0, continuation);
        platformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2.L$0 = obj;
        return platformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(TextClassifier textClassifier, Continuation<? super TextRange> continuation) {
        return ((PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2) create(textClassifier, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(TextClassifier textClassifier, Continuation<? super TextRange> continuation) {
        return invoke2(Listener$$ExternalSyntheticApiModelOutline0.m3066m((Object) textClassifier), continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LocaleList androidLocalList;
        TextSelection.Request.Builder defaultLocales;
        TextSelection.Request build;
        TextSelection suggestSelection;
        int selectionStartIndex;
        int selectionEndIndex;
        Object m1783classifyTextM8tDOmk;
        long j;
        TextClassification textClassification;
        Mutex mutex;
        CharSequence charSequence;
        long j2;
        TextSelection textSelection;
        PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl;
        TextClassification textClassification2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TextClassifier m3066m = Listener$$ExternalSyntheticApiModelOutline0.m3066m(this.L$0);
            Listener$$ExternalSyntheticApiModelOutline0.m$2();
            TextSelection.Request.Builder m3067m = Listener$$ExternalSyntheticApiModelOutline0.m3067m(this.$text, TextRange.m6846getMinimpl(this.$selection), TextRange.m6845getMaximpl(this.$selection));
            androidLocalList = this.this$0.getAndroidLocalList();
            defaultLocales = m3067m.setDefaultLocales(androidLocalList);
            if (Build.VERSION.SDK_INT >= 31) {
                defaultLocales.setIncludeTextClassification(true);
            }
            build = defaultLocales.build();
            suggestSelection = m3066m.suggestSelection(build);
            selectionStartIndex = suggestSelection.getSelectionStartIndex();
            selectionEndIndex = suggestSelection.getSelectionEndIndex();
            long TextRange = TextRangeKt.TextRange(selectionStartIndex, selectionEndIndex);
            if (Build.VERSION.SDK_INT >= 31) {
                textClassification = suggestSelection.getTextClassification();
                if (textClassification != null) {
                    mutex = this.this$0.mutex;
                    PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl2 = this.this$0;
                    CharSequence charSequence2 = this.$text;
                    this.L$0 = suggestSelection;
                    this.L$1 = mutex;
                    this.L$2 = platformSelectionBehaviorsImpl2;
                    this.L$3 = charSequence2;
                    this.J$0 = TextRange;
                    this.label = 1;
                    if (mutex.lock(null, this) != coroutine_suspended) {
                        charSequence = charSequence2;
                        j2 = TextRange;
                        textSelection = suggestSelection;
                        platformSelectionBehaviorsImpl = platformSelectionBehaviorsImpl2;
                        textClassification2 = textSelection.getTextClassification();
                        Intrinsics.checkNotNull(textClassification2);
                        platformSelectionBehaviorsImpl.setTextClassificationResult(new TextClassificationResult(charSequence, j2, textClassification2, null));
                        Unit unit = Unit.INSTANCE;
                    }
                    return coroutine_suspended;
                }
            }
            this.J$0 = TextRange;
            this.label = 2;
            m1783classifyTextM8tDOmk = this.this$0.m1783classifyTextM8tDOmk(this.$text, TextRange, m3066m, this);
            if (m1783classifyTextM8tDOmk != coroutine_suspended) {
                j = TextRange;
                j2 = j;
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            long j3 = this.J$0;
            CharSequence charSequence3 = (CharSequence) this.L$3;
            platformSelectionBehaviorsImpl = (PlatformSelectionBehaviorsImpl) this.L$2;
            mutex = (Mutex) this.L$1;
            textSelection = Listener$$ExternalSyntheticApiModelOutline0.m3068m(this.L$0);
            ResultKt.throwOnFailure(obj);
            charSequence = charSequence3;
            j2 = j3;
            try {
                textClassification2 = textSelection.getTextClassification();
                Intrinsics.checkNotNull(textClassification2);
                platformSelectionBehaviorsImpl.setTextClassificationResult(new TextClassificationResult(charSequence, j2, textClassification2, null));
                Unit unit2 = Unit.INSTANCE;
            } finally {
                mutex.unlock(null);
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j = this.J$0;
            ResultKt.throwOnFailure(obj);
            j2 = j;
        }
        return TextRange.m6836boximpl(j2);
    }
}
