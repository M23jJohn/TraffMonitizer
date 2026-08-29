package androidx.compose.foundation.text.input.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: AndroidTextInputSession.android.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt", f = "AndroidTextInputSession.android.kt", i = {}, l = {84}, m = "platformSpecificTextInputSession", n = {}, s = {})
/* loaded from: classes.dex */
final class AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2(Continuation<? super AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return AndroidTextInputSession_androidKt.platformSpecificTextInputSession(null, null, null, null, null, null, null, null, null, null, this);
    }
}
