package androidx.datastore.core;

import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: FileStorage.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.datastore.core.FileWriteScope", f = "FileStorage.kt", i = {0}, l = {ComposerKt.providerKey}, m = "writeData", n = {"stream"}, s = {"L$1"})
/* loaded from: classes3.dex */
final class FileWriteScope$writeData$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FileWriteScope<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FileWriteScope$writeData$1(FileWriteScope<T> fileWriteScope, Continuation<? super FileWriteScope$writeData$1> continuation) {
        super(continuation);
        this.this$0 = fileWriteScope;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.writeData(null, this);
    }
}
