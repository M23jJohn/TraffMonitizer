package androidx.datastore.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileStorage.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0004J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u000e\u0010\u0011\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010\u0012R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Landroidx/datastore/core/FileReadScope;", "T", "Landroidx/datastore/core/ReadScope;", "file", "Ljava/io/File;", "serializer", "Landroidx/datastore/core/Serializer;", "(Ljava/io/File;Landroidx/datastore/core/Serializer;)V", "closed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getFile", "()Ljava/io/File;", "getSerializer", "()Landroidx/datastore/core/Serializer;", "checkNotClosed", "", "close", "readData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class FileReadScope<T> implements ReadScope<T> {
    private final java.util.concurrent.atomic.AtomicBoolean closed;
    private final File file;
    private final Serializer<T> serializer;

    @Override // androidx.datastore.core.ReadScope
    public Object readData(Continuation<? super T> continuation) {
        return readData$suspendImpl(this, continuation);
    }

    public FileReadScope(File file, Serializer<T> serializer) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.file = file;
        this.serializer = serializer;
        this.closed = new java.util.concurrent.atomic.AtomicBoolean(false);
    }

    protected final File getFile() {
        return this.file;
    }

    protected final Serializer<T> getSerializer() {
        return this.serializer;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|1|(2:3|(6:5|6|7|(1:(1:(5:11|12|13|14|15)(2:25|26))(3:27|28|29))(7:40|41|42|44|45|(1:47)|48)|30|31))|66|6|7|(0)(0)|30|31|(3:(1:21)|(0)|(1:36))) */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0077, code lost:
    
        r8 = r2;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v16, types: [androidx.datastore.core.FileReadScope] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ <T> Object readData$suspendImpl(FileReadScope<T> fileReadScope, Continuation<? super T> continuation) {
        FileReadScope$readData$1 fileReadScope$readData$1;
        ?? r2;
        Throwable th;
        java.io.Closeable closeable;
        FileInputStream fileInputStream;
        java.io.Closeable closeable2;
        Throwable th2;
        if (continuation instanceof FileReadScope$readData$1) {
            fileReadScope$readData$1 = (FileReadScope$readData$1) continuation;
            if ((fileReadScope$readData$1.label & Integer.MIN_VALUE) != 0) {
                fileReadScope$readData$1.label -= Integer.MIN_VALUE;
                Object obj = fileReadScope$readData$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                r2 = fileReadScope$readData$1.label;
                if (r2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    fileReadScope.checkNotClosed();
                    try {
                        fileInputStream = new FileInputStream(((FileReadScope) fileReadScope).file);
                    } catch (FileNotFoundException unused) {
                        if (((FileReadScope) fileReadScope).file.exists()) {
                            FileInputStream fileInputStream2 = new FileInputStream(((FileReadScope) fileReadScope).file);
                            try {
                                fileReadScope$readData$1.L$0 = fileInputStream2;
                                fileReadScope$readData$1.L$1 = null;
                                fileReadScope$readData$1.label = 2;
                                Object readFrom = ((FileReadScope) fileReadScope).serializer.readFrom(fileInputStream2, fileReadScope$readData$1);
                                if (readFrom != coroutine_suspended) {
                                    obj = readFrom;
                                    closeable = fileInputStream2;
                                    kotlin.io.CloseableKt.closeFinally(closeable, null);
                                    return obj;
                                }
                                return coroutine_suspended;
                            } catch (Throwable th3) {
                                th = th3;
                                closeable = fileInputStream2;
                                throw th;
                            }
                        }
                        return ((FileReadScope) fileReadScope).serializer.getDefaultValue();
                    }
                    try {
                        fileReadScope$readData$1.L$0 = fileReadScope;
                        fileReadScope$readData$1.L$1 = fileInputStream;
                        fileReadScope$readData$1.label = 1;
                        Object readFrom2 = ((FileReadScope) fileReadScope).serializer.readFrom(fileInputStream, fileReadScope$readData$1);
                        if (readFrom2 != coroutine_suspended) {
                            closeable2 = fileInputStream;
                            obj = readFrom2;
                        }
                        return coroutine_suspended;
                    } catch (Throwable th4) {
                        r2 = fileReadScope;
                        closeable2 = fileInputStream;
                        th2 = th4;
                        throw th;
                    }
                }
                if (r2 != 1) {
                    if (r2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    closeable = (java.io.Closeable) fileReadScope$readData$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        kotlin.io.CloseableKt.closeFinally(closeable, null);
                        return obj;
                    } catch (Throwable th5) {
                        th = th5;
                        try {
                            throw th;
                        } finally {
                        }
                    }
                }
                closeable2 = (java.io.Closeable) fileReadScope$readData$1.L$1;
                r2 = (FileReadScope) fileReadScope$readData$1.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Throwable th6) {
                    th2 = th6;
                    try {
                        throw th;
                    } finally {
                    }
                }
                kotlin.io.CloseableKt.closeFinally(closeable2, null);
                return obj;
            }
        }
        fileReadScope$readData$1 = new FileReadScope$readData$1(fileReadScope, continuation);
        Object obj2 = fileReadScope$readData$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = fileReadScope$readData$1.label;
        if (r2 != 0) {
        }
        kotlin.io.CloseableKt.closeFinally(closeable2, null);
        return obj2;
    }

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
    }

    protected final void checkNotClosed() {
        if (this.closed.get()) {
            throw new IllegalStateException("This scope has already been closed.".toString());
        }
    }
}
