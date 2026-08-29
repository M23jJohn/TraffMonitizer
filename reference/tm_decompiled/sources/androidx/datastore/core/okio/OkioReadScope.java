package androidx.datastore.core.okio;

import androidx.datastore.core.ReadScope;
import java.io.Closeable;
import java.io.FileNotFoundException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Okio;
import okio.Path;

/* compiled from: OkioStorage.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\b\u0010\u0012\u001a\u00020\u0013H\u0004J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u000e\u0010\u0015\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, d2 = {"Landroidx/datastore/core/okio/OkioReadScope;", "T", "Landroidx/datastore/core/ReadScope;", "fileSystem", "Lokio/FileSystem;", "path", "Lokio/Path;", "serializer", "Landroidx/datastore/core/okio/OkioSerializer;", "(Lokio/FileSystem;Lokio/Path;Landroidx/datastore/core/okio/OkioSerializer;)V", "closed", "Landroidx/datastore/core/okio/AtomicBoolean;", "getFileSystem", "()Lokio/FileSystem;", "getPath", "()Lokio/Path;", "getSerializer", "()Landroidx/datastore/core/okio/OkioSerializer;", "checkClose", "", "close", "readData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core-okio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class OkioReadScope<T> implements ReadScope<T> {
    private final AtomicBoolean closed;
    private final FileSystem fileSystem;
    private final Path path;
    private final OkioSerializer<T> serializer;

    @Override // androidx.datastore.core.ReadScope
    public Object readData(Continuation<? super T> continuation) {
        return readData$suspendImpl(this, continuation);
    }

    public OkioReadScope(FileSystem fileSystem, Path path, OkioSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.fileSystem = fileSystem;
        this.path = path;
        this.serializer = serializer;
        this.closed = new AtomicBoolean(false);
    }

    protected final FileSystem getFileSystem() {
        return this.fileSystem;
    }

    protected final Path getPath() {
        return this.path;
    }

    protected final OkioSerializer<T> getSerializer() {
        return this.serializer;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|1|(2:3|(7:5|6|7|(1:(1:(5:11|12|13|(2:20|21)|(2:16|17)(1:19))(2:33|34))(3:35|36|37))(7:57|58|59|60|61|(1:63)|64)|(2:44|45)|39|(2:41|42)(1:43)))|83|6|7|(0)(0)|(0)|39|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0091, code lost:
    
        r8 = r2;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00cb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00d7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0097 A[Catch: FileNotFoundException -> 0x0091, TryCatch #2 {FileNotFoundException -> 0x0091, blocks: (B:41:0x0097, B:43:0x009b, B:56:0x008d, B:53:0x0088), top: B:7:0x0024, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009b A[Catch: FileNotFoundException -> 0x0091, TRY_LEAVE, TryCatch #2 {FileNotFoundException -> 0x0091, blocks: (B:41:0x0097, B:43:0x009b, B:56:0x008d, B:53:0x0088), top: B:7:0x0024, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0088 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v15, types: [androidx.datastore.core.okio.OkioReadScope] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ <T> Object readData$suspendImpl(OkioReadScope<T> okioReadScope, Continuation<? super T> continuation) {
        OkioReadScope$readData$1 okioReadScope$readData$1;
        ?? r2;
        Throwable th;
        Closeable closeable;
        Object readFrom;
        Closeable closeable2;
        Throwable th2;
        Throwable th3;
        if (continuation instanceof OkioReadScope$readData$1) {
            okioReadScope$readData$1 = (OkioReadScope$readData$1) continuation;
            if ((okioReadScope$readData$1.label & Integer.MIN_VALUE) != 0) {
                okioReadScope$readData$1.label -= Integer.MIN_VALUE;
                Object obj = okioReadScope$readData$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                r2 = okioReadScope$readData$1.label;
                Throwable th4 = null;
                if (r2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    okioReadScope.checkClose();
                    try {
                        BufferedSource buffer = Okio.buffer(((OkioReadScope) okioReadScope).fileSystem.source(((OkioReadScope) okioReadScope).path));
                        try {
                            OkioSerializer<T> okioSerializer = ((OkioReadScope) okioReadScope).serializer;
                            okioReadScope$readData$1.L$0 = okioReadScope;
                            okioReadScope$readData$1.L$1 = buffer;
                            okioReadScope$readData$1.label = 1;
                            Object readFrom2 = okioSerializer.readFrom(buffer, okioReadScope$readData$1);
                            if (readFrom2 != coroutine_suspended) {
                                closeable2 = buffer;
                                obj = readFrom2;
                            }
                        } catch (Throwable th5) {
                            r2 = okioReadScope;
                            closeable2 = buffer;
                            th2 = th5;
                            if (closeable2 != null) {
                                try {
                                    closeable2.close();
                                } catch (Throwable th6) {
                                    ExceptionsKt.addSuppressed(th2, th6);
                                }
                            }
                            th3 = th2;
                            obj = null;
                            if (th3 == null) {
                            }
                        }
                    } catch (FileNotFoundException unused) {
                        if (!((OkioReadScope) okioReadScope).fileSystem.exists(((OkioReadScope) okioReadScope).path)) {
                            return ((OkioReadScope) okioReadScope).serializer.getDefaultValue();
                        }
                        BufferedSource buffer2 = Okio.buffer(((OkioReadScope) okioReadScope).fileSystem.source(((OkioReadScope) okioReadScope).path));
                        try {
                            OkioSerializer<T> okioSerializer2 = ((OkioReadScope) okioReadScope).serializer;
                            okioReadScope$readData$1.L$0 = buffer2;
                            okioReadScope$readData$1.L$1 = null;
                            okioReadScope$readData$1.label = 2;
                            readFrom = okioSerializer2.readFrom(buffer2, okioReadScope$readData$1);
                        } catch (Throwable th7) {
                            th = th7;
                            closeable = buffer2;
                            if (closeable != null) {
                                try {
                                    closeable.close();
                                } catch (Throwable th8) {
                                    ExceptionsKt.addSuppressed(th, th8);
                                }
                            }
                            th4 = th;
                            obj = null;
                            if (th4 == null) {
                            }
                        }
                        if (readFrom != coroutine_suspended) {
                            obj = readFrom;
                            closeable = buffer2;
                            if (closeable != null) {
                            }
                            if (th4 == null) {
                            }
                        }
                        return coroutine_suspended;
                    }
                    return coroutine_suspended;
                }
                if (r2 != 1) {
                    if (r2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    closeable = (Closeable) okioReadScope$readData$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        if (closeable != null) {
                            try {
                                closeable.close();
                            } catch (Throwable th9) {
                                th4 = th9;
                            }
                        }
                    } catch (Throwable th10) {
                        th = th10;
                        if (closeable != null) {
                        }
                        th4 = th;
                        obj = null;
                        if (th4 == null) {
                        }
                    }
                    if (th4 == null) {
                        throw th4;
                    }
                    Intrinsics.checkNotNull(obj);
                    return obj;
                }
                closeable2 = (Closeable) okioReadScope$readData$1.L$1;
                r2 = (OkioReadScope) okioReadScope$readData$1.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Throwable th11) {
                    th2 = th11;
                    if (closeable2 != null) {
                    }
                    th3 = th2;
                    obj = null;
                    if (th3 == null) {
                    }
                }
                if (closeable2 != null) {
                    try {
                        closeable2.close();
                    } catch (Throwable th12) {
                        th3 = th12;
                    }
                }
                th3 = null;
                if (th3 == null) {
                    throw th3;
                }
                Intrinsics.checkNotNull(obj);
                return obj;
            }
        }
        okioReadScope$readData$1 = new OkioReadScope$readData$1(okioReadScope, continuation);
        Object obj2 = okioReadScope$readData$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = okioReadScope$readData$1.label;
        Throwable th42 = null;
        if (r2 != 0) {
        }
        if (closeable2 != null) {
        }
        th3 = null;
        if (th3 == null) {
        }
    }

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
    }

    protected final void checkClose() {
        if (this.closed.get()) {
            throw new IllegalStateException("This scope has already been closed.".toString());
        }
    }
}
