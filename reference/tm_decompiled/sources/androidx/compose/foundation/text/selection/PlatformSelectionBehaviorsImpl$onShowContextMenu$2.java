package androidx.compose.foundation.text.selection;

import android.view.textclassifier.TextClassifier;
import androidx.compose.material3.internal.Listener$$ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: PlatformSelectionBehaviors.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroid/view/textclassifier/TextClassifier;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$onShowContextMenu$2", f = "PlatformSelectionBehaviors.android.kt", i = {}, l = {168}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class PlatformSelectionBehaviorsImpl$onShowContextMenu$2 extends SuspendLambda implements Function2<TextClassifier, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $selection;
    final /* synthetic */ CharSequence $text;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlatformSelectionBehaviorsImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlatformSelectionBehaviorsImpl$onShowContextMenu$2(PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl, CharSequence charSequence, long j, Continuation<? super PlatformSelectionBehaviorsImpl$onShowContextMenu$2> continuation) {
        super(2, continuation);
        this.this$0 = platformSelectionBehaviorsImpl;
        this.$text = charSequence;
        this.$selection = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlatformSelectionBehaviorsImpl$onShowContextMenu$2 platformSelectionBehaviorsImpl$onShowContextMenu$2 = new PlatformSelectionBehaviorsImpl$onShowContextMenu$2(this.this$0, this.$text, this.$selection, continuation);
        platformSelectionBehaviorsImpl$onShowContextMenu$2.L$0 = obj;
        return platformSelectionBehaviorsImpl$onShowContextMenu$2;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(TextClassifier textClassifier, Continuation<? super Unit> continuation) {
        return ((PlatformSelectionBehaviorsImpl$onShowContextMenu$2) create(textClassifier, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(TextClassifier textClassifier, Continuation<? super Unit> continuation) {
        return invoke2(Listener$$ExternalSyntheticApiModelOutline0.m3066m((Object) textClassifier), continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m1783classifyTextM8tDOmk;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TextClassifier m3066m = Listener$$ExternalSyntheticApiModelOutline0.m3066m(this.L$0);
            this.label = 1;
            m1783classifyTextM8tDOmk = this.this$0.m1783classifyTextM8tDOmk(this.$text, this.$selection, m3066m, this);
            if (m1783classifyTextM8tDOmk == coroutine_suspended) {
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
