package androidx.datastore.core;

import androidx.autofill.HintConstants;
import androidx.datastore.core.Message;
import androidx.datastore.core.UpdatingDataContextElement;
import androidx.datastore.core.handlers.NoOpCorruptionHandler;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: DataStoreImpl.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 V*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002VWBn\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012?\b\u0002\u0010\u0005\u001a9\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00070\u0006\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u000e\u00103\u001a\u00020\rH\u0082@¢\u0006\u0002\u00104JG\u00105\u001a\u0002H6\"\u0004\b\u0001\u001062\u0006\u00107\u001a\u0002082\u001c\u00109\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H60\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0:H\u0082@\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010;J\u001c\u0010<\u001a\u00020\r2\f\u0010=\u001a\b\u0012\u0004\u0012\u00028\u000002H\u0082@¢\u0006\u0002\u0010>J\u000e\u0010?\u001a\u00020\rH\u0082@¢\u0006\u0002\u00104J\u000e\u0010@\u001a\u00020\rH\u0082@¢\u0006\u0002\u00104J\u001c\u0010A\u001a\b\u0012\u0004\u0012\u00028\u00000B2\u0006\u0010C\u001a\u000208H\u0082@¢\u0006\u0002\u0010DJ\u000e\u0010E\u001a\u00028\u0000H\u0082@¢\u0006\u0002\u00104J\u001c\u0010F\u001a\b\u0012\u0004\u0012\u00028\u00000G2\u0006\u00107\u001a\u000208H\u0082@¢\u0006\u0002\u0010DJ\u001c\u0010H\u001a\b\u0012\u0004\u0012\u00028\u00000B2\u0006\u0010C\u001a\u000208H\u0082@¢\u0006\u0002\u0010DJI\u0010I\u001a\u00028\u000021\u0010J\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(K\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00072\u0006\u0010L\u001a\u00020MH\u0082@¢\u0006\u0002\u0010NJA\u0010O\u001a\u00028\u000021\u0010J\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(K\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007H\u0096@¢\u0006\u0002\u0010PJ \u0010Q\u001a\u00020\u00152\u0006\u0010R\u001a\u00028\u00002\u0006\u0010S\u001a\u000208H\u0080@¢\u0006\u0004\bT\u0010UR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000!X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010&\u001a\f0'R\b\u0012\u0004\u0012\u00028\u00000\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000)8@X\u0080\u0084\u0002¢\u0006\f\u001a\u0004\b,\u0010-*\u0004\b*\u0010+R\u001a\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000)0/X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000201X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Landroidx/datastore/core/DataStoreImpl;", "T", "Landroidx/datastore/core/DataStore;", "storage", "Landroidx/datastore/core/Storage;", "initTasksList", "", "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "api", "Lkotlin/coroutines/Continuation;", "", "", "corruptionHandler", "Landroidx/datastore/core/CorruptionHandler;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/datastore/core/Storage;Ljava/util/List;Landroidx/datastore/core/CorruptionHandler;Lkotlinx/coroutines/CoroutineScope;)V", "collectorCounter", "", "collectorJob", "Lkotlinx/coroutines/Job;", "collectorMutex", "Lkotlinx/coroutines/sync/Mutex;", "coordinator", "Landroidx/datastore/core/InterProcessCoordinator;", "getCoordinator", "()Landroidx/datastore/core/InterProcessCoordinator;", "coordinator$delegate", "Lkotlin/Lazy;", "data", "Lkotlinx/coroutines/flow/Flow;", "getData", "()Lkotlinx/coroutines/flow/Flow;", "inMemoryCache", "Landroidx/datastore/core/DataStoreInMemoryCache;", "readAndInit", "Landroidx/datastore/core/DataStoreImpl$InitDataStore;", "storageConnection", "Landroidx/datastore/core/StorageConnection;", "getStorageConnection$datastore_core_release$delegate", "(Landroidx/datastore/core/DataStoreImpl;)Ljava/lang/Object;", "getStorageConnection$datastore_core_release", "()Landroidx/datastore/core/StorageConnection;", "storageConnectionDelegate", "Lkotlin/Lazy;", "writeActor", "Landroidx/datastore/core/SimpleActor;", "Landroidx/datastore/core/Message$Update;", "decrementCollector", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doWithWriteFileLock", "R", "hasWriteFileLock", "", "block", "Lkotlin/Function1;", "(ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleUpdate", "update", "(Landroidx/datastore/core/Message$Update;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "incrementCollector", "readAndInitOrPropagateAndThrowFailure", "readDataAndUpdateCache", "Landroidx/datastore/core/State;", "requireLock", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readDataFromFileOrDefault", "readDataOrHandleCorruption", "Landroidx/datastore/core/Data;", "readState", "transformAndWrite", "transform", "t", "callerContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateData", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeData", "newData", "updateCache", "writeData$datastore_core_release", "(Ljava/lang/Object;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "InitDataStore", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DataStoreImpl<T> implements DataStore<T> {
    private static final String BUG_MESSAGE = "This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542";
    private int collectorCounter;
    private Job collectorJob;
    private final Mutex collectorMutex;

    /* renamed from: coordinator$delegate, reason: from kotlin metadata */
    private final Lazy coordinator;
    private final CorruptionHandler<T> corruptionHandler;
    private final Flow<T> data;
    private final DataStoreInMemoryCache<T> inMemoryCache;
    private final DataStoreImpl<T>.InitDataStore readAndInit;
    private final CoroutineScope scope;
    private final Storage<T> storage;
    private final Lazy<StorageConnection<T>> storageConnectionDelegate;
    private final SimpleActor<Message.Update<T>> writeActor;

    public DataStoreImpl(Storage<T> storage, List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasksList, CorruptionHandler<T> corruptionHandler, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(initTasksList, "initTasksList");
        Intrinsics.checkNotNullParameter(corruptionHandler, "corruptionHandler");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.storage = storage;
        this.corruptionHandler = corruptionHandler;
        this.scope = scope;
        this.data = FlowKt.flow(new DataStoreImpl$data$1(this, null));
        this.collectorMutex = MutexKt.Mutex$default(false, 1, null);
        this.inMemoryCache = new DataStoreInMemoryCache<>();
        this.readAndInit = new InitDataStore(this, initTasksList);
        this.storageConnectionDelegate = LazyKt.lazy(new Function0<StorageConnection<T>>(this) { // from class: androidx.datastore.core.DataStoreImpl$storageConnectionDelegate$1
            final /* synthetic */ DataStoreImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final StorageConnection<T> invoke() {
                Storage storage2;
                storage2 = ((DataStoreImpl) this.this$0).storage;
                return storage2.createConnection();
            }
        });
        this.coordinator = LazyKt.lazy(new Function0<InterProcessCoordinator>(this) { // from class: androidx.datastore.core.DataStoreImpl$coordinator$2
            final /* synthetic */ DataStoreImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final InterProcessCoordinator invoke() {
                return this.this$0.getStorageConnection$datastore_core_release().getCoordinator();
            }
        });
        this.writeActor = new SimpleActor<>(scope, new Function1<Throwable, Unit>(this) { // from class: androidx.datastore.core.DataStoreImpl$writeActor$1
            final /* synthetic */ DataStoreImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Lazy lazy;
                if (th != null) {
                    ((DataStoreImpl) this.this$0).inMemoryCache.tryUpdate(new Final(th));
                }
                lazy = ((DataStoreImpl) this.this$0).storageConnectionDelegate;
                if (lazy.isInitialized()) {
                    this.this$0.getStorageConnection$datastore_core_release().close();
                }
            }
        }, new Function2<Message.Update<T>, Throwable, Unit>() { // from class: androidx.datastore.core.DataStoreImpl$writeActor$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object obj, Throwable th) {
                invoke((Message.Update) obj, th);
                return Unit.INSTANCE;
            }

            public final void invoke(Message.Update<T> msg, Throwable th) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                CompletableDeferred<T> ack = msg.getAck();
                if (th == null) {
                    th = new CancellationException("DataStore scope was cancelled before updateData could complete");
                }
                ack.completeExceptionally(th);
            }
        }, new DataStoreImpl$writeActor$3(this, null));
    }

    public /* synthetic */ DataStoreImpl(Storage storage, List list, NoOpCorruptionHandler noOpCorruptionHandler, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(storage, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? new NoOpCorruptionHandler() : noOpCorruptionHandler, (i & 8) != 0 ? CoroutineScopeKt.CoroutineScope(Actual_jvmKt.ioDispatcher().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null))) : coroutineScope);
    }

    @Override // androidx.datastore.core.DataStore
    public Flow<T> getData() {
        return this.data;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0056 A[Catch: all -> 0x0072, TryCatch #0 {all -> 0x0072, blocks: (B:11:0x004f, B:13:0x0056, B:14:0x006a), top: B:10:0x004f }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object incrementCollector(Continuation<? super Unit> continuation) {
        DataStoreImpl$incrementCollector$1 dataStoreImpl$incrementCollector$1;
        int i;
        DataStoreImpl<T> dataStoreImpl;
        Mutex mutex;
        int i2;
        Job launch$default;
        try {
            if (continuation instanceof DataStoreImpl$incrementCollector$1) {
                dataStoreImpl$incrementCollector$1 = (DataStoreImpl$incrementCollector$1) continuation;
                if ((dataStoreImpl$incrementCollector$1.label & Integer.MIN_VALUE) != 0) {
                    dataStoreImpl$incrementCollector$1.label -= Integer.MIN_VALUE;
                    Object obj = dataStoreImpl$incrementCollector$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = dataStoreImpl$incrementCollector$1.label;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        Mutex mutex2 = this.collectorMutex;
                        dataStoreImpl$incrementCollector$1.L$0 = this;
                        dataStoreImpl$incrementCollector$1.L$1 = mutex2;
                        dataStoreImpl$incrementCollector$1.label = 1;
                        if (mutex2.lock(null, dataStoreImpl$incrementCollector$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        dataStoreImpl = this;
                        mutex = mutex2;
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        mutex = (Mutex) dataStoreImpl$incrementCollector$1.L$1;
                        dataStoreImpl = (DataStoreImpl) dataStoreImpl$incrementCollector$1.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    i2 = dataStoreImpl.collectorCounter + 1;
                    dataStoreImpl.collectorCounter = i2;
                    if (i2 == 1) {
                        launch$default = BuildersKt__Builders_commonKt.launch$default(dataStoreImpl.scope, null, null, new DataStoreImpl$incrementCollector$2$1(dataStoreImpl, null), 3, null);
                        dataStoreImpl.collectorJob = launch$default;
                    }
                    Unit unit = Unit.INSTANCE;
                    mutex.unlock(null);
                    return Unit.INSTANCE;
                }
            }
            i2 = dataStoreImpl.collectorCounter + 1;
            dataStoreImpl.collectorCounter = i2;
            if (i2 == 1) {
            }
            Unit unit2 = Unit.INSTANCE;
            mutex.unlock(null);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            mutex.unlock(null);
            throw th;
        }
        dataStoreImpl$incrementCollector$1 = new DataStoreImpl$incrementCollector$1(this, continuation);
        Object obj2 = dataStoreImpl$incrementCollector$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = dataStoreImpl$incrementCollector$1.label;
        if (i != 0) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0057 A[Catch: all -> 0x0068, TryCatch #0 {all -> 0x0068, blocks: (B:11:0x004f, B:13:0x0057, B:15:0x005b, B:16:0x005e, B:17:0x0060), top: B:10:0x004f }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object decrementCollector(Continuation<? super Unit> continuation) {
        DataStoreImpl$decrementCollector$1 dataStoreImpl$decrementCollector$1;
        int i;
        DataStoreImpl<T> dataStoreImpl;
        Mutex mutex;
        int i2;
        try {
            if (continuation instanceof DataStoreImpl$decrementCollector$1) {
                dataStoreImpl$decrementCollector$1 = (DataStoreImpl$decrementCollector$1) continuation;
                if ((dataStoreImpl$decrementCollector$1.label & Integer.MIN_VALUE) != 0) {
                    dataStoreImpl$decrementCollector$1.label -= Integer.MIN_VALUE;
                    Object obj = dataStoreImpl$decrementCollector$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = dataStoreImpl$decrementCollector$1.label;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        Mutex mutex2 = this.collectorMutex;
                        dataStoreImpl$decrementCollector$1.L$0 = this;
                        dataStoreImpl$decrementCollector$1.L$1 = mutex2;
                        dataStoreImpl$decrementCollector$1.label = 1;
                        if (mutex2.lock(null, dataStoreImpl$decrementCollector$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        dataStoreImpl = this;
                        mutex = mutex2;
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        mutex = (Mutex) dataStoreImpl$decrementCollector$1.L$1;
                        dataStoreImpl = (DataStoreImpl) dataStoreImpl$decrementCollector$1.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    i2 = dataStoreImpl.collectorCounter - 1;
                    dataStoreImpl.collectorCounter = i2;
                    if (i2 == 0) {
                        Job job = dataStoreImpl.collectorJob;
                        if (job != null) {
                            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                        }
                        dataStoreImpl.collectorJob = null;
                    }
                    Unit unit = Unit.INSTANCE;
                    mutex.unlock(null);
                    return Unit.INSTANCE;
                }
            }
            i2 = dataStoreImpl.collectorCounter - 1;
            dataStoreImpl.collectorCounter = i2;
            if (i2 == 0) {
            }
            Unit unit2 = Unit.INSTANCE;
            mutex.unlock(null);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            mutex.unlock(null);
            throw th;
        }
        dataStoreImpl$decrementCollector$1 = new DataStoreImpl$decrementCollector$1(this, continuation);
        Object obj2 = dataStoreImpl$decrementCollector$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = dataStoreImpl$decrementCollector$1.label;
        if (i != 0) {
        }
    }

    @Override // androidx.datastore.core.DataStore
    public Object updateData(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        UpdatingDataContextElement updatingDataContextElement = (UpdatingDataContextElement) continuation.getContext().get(UpdatingDataContextElement.Companion.Key.INSTANCE);
        if (updatingDataContextElement != null) {
            updatingDataContextElement.checkNotUpdating(this);
        }
        return BuildersKt.withContext(new UpdatingDataContextElement(updatingDataContextElement, this), new DataStoreImpl$updateData$2(this, function2, null), continuation);
    }

    public final StorageConnection<T> getStorageConnection$datastore_core_release() {
        return this.storageConnectionDelegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InterProcessCoordinator getCoordinator() {
        return (InterProcessCoordinator) this.coordinator.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readState(boolean z, Continuation<? super State<T>> continuation) {
        return BuildersKt.withContext(this.scope.getCoroutineContext(), new DataStoreImpl$readState$2(this, z, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(9:0|1|(2:3|(5:5|6|7|(6:(1:(1:(2:12|13))(3:15|16|17))|26|27|21|22|23)(5:28|29|30|(6:32|(1:34)|20|21|22|23)(3:35|(1:37)(1:53)|(2:39|(2:41|(1:43)(1:44))(2:45|46))(2:47|(2:49|50)(2:51|52)))|25)|18))|58|6|7|(0)(0)|18|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00b4, code lost:
    
        if (r9 != r1) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0050, code lost:
    
        r10 = th;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /* JADX WARN: Type inference failed for: r8v0, types: [androidx.datastore.core.DataStoreImpl, androidx.datastore.core.DataStoreImpl<T>, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v3, types: [kotlinx.coroutines.CompletableDeferred] */
    /* JADX WARN: Type inference failed for: r9v33 */
    /* JADX WARN: Type inference failed for: r9v34 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object handleUpdate(Message.Update<T> update, Continuation<? super Unit> continuation) {
        DataStoreImpl$handleUpdate$1 dataStoreImpl$handleUpdate$1;
        int i;
        Object m8075constructorimpl;
        ?? r9;
        CompletableDeferred<T> ack;
        State<T> currentState;
        DataStoreImpl dataStoreImpl;
        Object transformAndWrite;
        Message.Update<T> update2;
        if (continuation instanceof DataStoreImpl$handleUpdate$1) {
            dataStoreImpl$handleUpdate$1 = (DataStoreImpl$handleUpdate$1) continuation;
            if ((dataStoreImpl$handleUpdate$1.label & Integer.MIN_VALUE) != 0) {
                dataStoreImpl$handleUpdate$1.label -= Integer.MIN_VALUE;
                Object obj = dataStoreImpl$handleUpdate$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = dataStoreImpl$handleUpdate$1.label;
                boolean z = true;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    ack = update.getAck();
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        currentState = this.inMemoryCache.getCurrentState();
                    } catch (Throwable th) {
                        th = th;
                        update = ack;
                        Result.Companion companion2 = Result.INSTANCE;
                        m8075constructorimpl = Result.m8075constructorimpl(ResultKt.createFailure(th));
                        r9 = update;
                        CompletableDeferredKt.completeWith(r9, m8075constructorimpl);
                        return Unit.INSTANCE;
                    }
                    if (currentState instanceof Data) {
                        Function2<T, Continuation<? super T>, Object> transform = update.getTransform();
                        CoroutineContext callerContext = update.getCallerContext();
                        dataStoreImpl$handleUpdate$1.L$0 = ack;
                        dataStoreImpl$handleUpdate$1.label = 1;
                        transformAndWrite = transformAndWrite(transform, callerContext, dataStoreImpl$handleUpdate$1);
                        if (transformAndWrite == coroutine_suspended) {
                        }
                        Message.Update<T> update3 = ack;
                        obj = transformAndWrite;
                        update = update3;
                        m8075constructorimpl = Result.m8075constructorimpl(obj);
                        r9 = update;
                        CompletableDeferredKt.completeWith(r9, m8075constructorimpl);
                        return Unit.INSTANCE;
                    }
                    if (!(currentState instanceof ReadException)) {
                        z = currentState instanceof UnInitialized;
                    }
                    if (z) {
                        if (currentState == update.getLastState()) {
                            dataStoreImpl$handleUpdate$1.L$0 = update;
                            dataStoreImpl$handleUpdate$1.L$1 = this;
                            dataStoreImpl$handleUpdate$1.L$2 = ack;
                            dataStoreImpl$handleUpdate$1.label = 2;
                            if (readAndInitOrPropagateAndThrowFailure(dataStoreImpl$handleUpdate$1) != coroutine_suspended) {
                                dataStoreImpl = this;
                                update2 = update;
                            }
                        } else {
                            Intrinsics.checkNotNull(currentState, "null cannot be cast to non-null type androidx.datastore.core.ReadException<T of androidx.datastore.core.DataStoreImpl.handleUpdate$lambda$2>");
                            throw ((ReadException) currentState).getReadException();
                        }
                    } else {
                        if (currentState instanceof Final) {
                            throw ((Final) currentState).getFinalException();
                        }
                        throw new NoWhenBranchMatchedException();
                    }
                    return coroutine_suspended;
                }
                if (i != 1) {
                    if (i == 2) {
                        CompletableDeferred<T> completableDeferred = (CompletableDeferred) dataStoreImpl$handleUpdate$1.L$2;
                        dataStoreImpl = (DataStoreImpl) dataStoreImpl$handleUpdate$1.L$1;
                        Message.Update<T> update4 = (Message.Update) dataStoreImpl$handleUpdate$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        ack = completableDeferred;
                        update2 = update4;
                    } else if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
                Message.Update<T> update5 = (Message.Update<T>) ((CompletableDeferred) dataStoreImpl$handleUpdate$1.L$0);
                ResultKt.throwOnFailure(obj);
                update = update5;
                m8075constructorimpl = Result.m8075constructorimpl(obj);
                r9 = update;
                CompletableDeferredKt.completeWith(r9, m8075constructorimpl);
                return Unit.INSTANCE;
                Function2<T, Continuation<? super T>, Object> transform2 = update2.getTransform();
                CoroutineContext callerContext2 = update2.getCallerContext();
                dataStoreImpl$handleUpdate$1.L$0 = ack;
                dataStoreImpl$handleUpdate$1.L$1 = null;
                dataStoreImpl$handleUpdate$1.L$2 = null;
                dataStoreImpl$handleUpdate$1.label = 3;
                transformAndWrite = dataStoreImpl.transformAndWrite(transform2, callerContext2, dataStoreImpl$handleUpdate$1);
            }
        }
        dataStoreImpl$handleUpdate$1 = new DataStoreImpl$handleUpdate$1(this, continuation);
        Object obj2 = dataStoreImpl$handleUpdate$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = dataStoreImpl$handleUpdate$1.label;
        boolean z2 = true;
        if (i != 0) {
        }
        Function2<T, Continuation<? super T>, Object> transform22 = update2.getTransform();
        CoroutineContext callerContext22 = update2.getCallerContext();
        dataStoreImpl$handleUpdate$1.L$0 = ack;
        dataStoreImpl$handleUpdate$1.L$1 = null;
        dataStoreImpl$handleUpdate$1.L$2 = null;
        dataStoreImpl$handleUpdate$1.label = 3;
        transformAndWrite = dataStoreImpl.transformAndWrite(transform22, callerContext22, dataStoreImpl$handleUpdate$1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006a, code lost:
    
        if (r4.runIfNeeded(r0) != r1) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object readAndInitOrPropagateAndThrowFailure(Continuation<? super Unit> continuation) {
        DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1 dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1;
        int i;
        DataStoreImpl<T> dataStoreImpl;
        int intValue;
        int i2;
        Throwable th;
        DataStoreImpl<T> dataStoreImpl2;
        try {
            if (continuation instanceof DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1) {
                dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1 = (DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1) continuation;
                if ((dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.label & Integer.MIN_VALUE) != 0) {
                    dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.label -= Integer.MIN_VALUE;
                    Object obj = dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.label;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        InterProcessCoordinator coordinator = getCoordinator();
                        dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.L$0 = this;
                        dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.label = 1;
                        obj = coordinator.getVersion(dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1);
                        if (obj != coroutine_suspended) {
                            dataStoreImpl = this;
                        }
                        return coroutine_suspended;
                    }
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        i2 = dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.I$0;
                        dataStoreImpl2 = (DataStoreImpl) dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        } catch (Throwable th2) {
                            th = th2;
                            dataStoreImpl2.inMemoryCache.tryUpdate(new ReadException(th, i2));
                            throw th;
                        }
                    }
                    dataStoreImpl = (DataStoreImpl) dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    intValue = ((Number) obj).intValue();
                    DataStoreImpl<T>.InitDataStore initDataStore = dataStoreImpl.readAndInit;
                    dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.L$0 = dataStoreImpl;
                    dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.I$0 = intValue;
                    dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.label = 2;
                }
            }
            DataStoreImpl<T>.InitDataStore initDataStore2 = dataStoreImpl.readAndInit;
            dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.L$0 = dataStoreImpl;
            dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.I$0 = intValue;
            dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.label = 2;
        } catch (Throwable th3) {
            i2 = intValue;
            th = th3;
            dataStoreImpl2 = dataStoreImpl;
            dataStoreImpl2.inMemoryCache.tryUpdate(new ReadException(th, i2));
            throw th;
        }
        dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1 = new DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1(this, continuation);
        Object obj2 = dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.label;
        if (i != 0) {
        }
        intValue = ((Number) obj2).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00aa, code lost:
    
        if (r11 == r1) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c5, code lost:
    
        if (r11 == r1) goto L39;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object readDataAndUpdateCache(boolean z, Continuation<? super State<T>> continuation) {
        DataStoreImpl$readDataAndUpdateCache$1 dataStoreImpl$readDataAndUpdateCache$1;
        Object coroutine_suspended;
        int i;
        State<T> state;
        Object obj;
        boolean z2;
        DataStoreImpl<T> dataStoreImpl;
        boolean z3;
        Pair pair;
        if (continuation instanceof DataStoreImpl$readDataAndUpdateCache$1) {
            dataStoreImpl$readDataAndUpdateCache$1 = (DataStoreImpl$readDataAndUpdateCache$1) continuation;
            if ((dataStoreImpl$readDataAndUpdateCache$1.label & Integer.MIN_VALUE) != 0) {
                dataStoreImpl$readDataAndUpdateCache$1.label -= Integer.MIN_VALUE;
                Object obj2 = dataStoreImpl$readDataAndUpdateCache$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = dataStoreImpl$readDataAndUpdateCache$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    State<T> currentState = this.inMemoryCache.getCurrentState();
                    if (currentState instanceof UnInitialized) {
                        throw new IllegalStateException(BUG_MESSAGE.toString());
                    }
                    InterProcessCoordinator coordinator = getCoordinator();
                    dataStoreImpl$readDataAndUpdateCache$1.L$0 = this;
                    dataStoreImpl$readDataAndUpdateCache$1.L$1 = currentState;
                    dataStoreImpl$readDataAndUpdateCache$1.Z$0 = z;
                    dataStoreImpl$readDataAndUpdateCache$1.label = 1;
                    Object version = coordinator.getVersion(dataStoreImpl$readDataAndUpdateCache$1);
                    if (version != coroutine_suspended) {
                        state = currentState;
                        obj = version;
                        z2 = z;
                        dataStoreImpl = this;
                    }
                    return coroutine_suspended;
                }
                if (i != 1) {
                    if (i == 2) {
                        dataStoreImpl = (DataStoreImpl) dataStoreImpl$readDataAndUpdateCache$1.L$0;
                        ResultKt.throwOnFailure(obj2);
                        pair = (Pair) obj2;
                        State<T> state2 = (State) pair.component1();
                        if (((Boolean) pair.component2()).booleanValue()) {
                        }
                        return state2;
                    }
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    dataStoreImpl = (DataStoreImpl) dataStoreImpl$readDataAndUpdateCache$1.L$0;
                    ResultKt.throwOnFailure(obj2);
                    pair = (Pair) obj2;
                    State<T> state22 = (State) pair.component1();
                    if (((Boolean) pair.component2()).booleanValue()) {
                        dataStoreImpl.inMemoryCache.tryUpdate(state22);
                    }
                    return state22;
                }
                boolean z4 = dataStoreImpl$readDataAndUpdateCache$1.Z$0;
                State<T> state3 = (State) dataStoreImpl$readDataAndUpdateCache$1.L$1;
                DataStoreImpl<T> dataStoreImpl2 = (DataStoreImpl) dataStoreImpl$readDataAndUpdateCache$1.L$0;
                ResultKt.throwOnFailure(obj2);
                z2 = z4;
                dataStoreImpl = dataStoreImpl2;
                state = state3;
                obj = obj2;
                int intValue = ((Number) obj).intValue();
                z3 = state instanceof Data;
                int version2 = !z3 ? state.getVersion() : -1;
                if (!z3 && intValue == version2) {
                    return state;
                }
                if (!z2) {
                    InterProcessCoordinator coordinator2 = dataStoreImpl.getCoordinator();
                    DataStoreImpl$readDataAndUpdateCache$3 dataStoreImpl$readDataAndUpdateCache$3 = new DataStoreImpl$readDataAndUpdateCache$3(dataStoreImpl, null);
                    dataStoreImpl$readDataAndUpdateCache$1.L$0 = dataStoreImpl;
                    dataStoreImpl$readDataAndUpdateCache$1.L$1 = null;
                    dataStoreImpl$readDataAndUpdateCache$1.label = 2;
                    obj2 = coordinator2.lock(dataStoreImpl$readDataAndUpdateCache$3, dataStoreImpl$readDataAndUpdateCache$1);
                } else {
                    InterProcessCoordinator coordinator3 = dataStoreImpl.getCoordinator();
                    DataStoreImpl$readDataAndUpdateCache$4 dataStoreImpl$readDataAndUpdateCache$4 = new DataStoreImpl$readDataAndUpdateCache$4(dataStoreImpl, version2, null);
                    dataStoreImpl$readDataAndUpdateCache$1.L$0 = dataStoreImpl;
                    dataStoreImpl$readDataAndUpdateCache$1.L$1 = null;
                    dataStoreImpl$readDataAndUpdateCache$1.label = 3;
                    obj2 = coordinator3.tryLock(dataStoreImpl$readDataAndUpdateCache$4, dataStoreImpl$readDataAndUpdateCache$1);
                }
                return coroutine_suspended;
            }
        }
        dataStoreImpl$readDataAndUpdateCache$1 = new DataStoreImpl$readDataAndUpdateCache$1(this, continuation);
        Object obj22 = dataStoreImpl$readDataAndUpdateCache$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = dataStoreImpl$readDataAndUpdateCache$1.label;
        if (i != 0) {
        }
        int intValue2 = ((Number) obj).intValue();
        z3 = state instanceof Data;
        if (!z3) {
        }
        if (!z3) {
        }
        if (!z2) {
        }
        return coroutine_suspended;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readDataFromFileOrDefault(Continuation<? super T> continuation) {
        return StorageConnectionKt.readData(getStorageConnection$datastore_core_release(), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object transformAndWrite(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, CoroutineContext coroutineContext, Continuation<? super T> continuation) {
        return getCoordinator().lock(new DataStoreImpl$transformAndWrite$2(this, coroutineContext, function2, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object writeData$datastore_core_release(T t, boolean z, Continuation<? super Integer> continuation) {
        DataStoreImpl$writeData$1 dataStoreImpl$writeData$1;
        int i;
        Ref.IntRef intRef;
        if (continuation instanceof DataStoreImpl$writeData$1) {
            dataStoreImpl$writeData$1 = (DataStoreImpl$writeData$1) continuation;
            if ((dataStoreImpl$writeData$1.label & Integer.MIN_VALUE) != 0) {
                dataStoreImpl$writeData$1.label -= Integer.MIN_VALUE;
                Object obj = dataStoreImpl$writeData$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = dataStoreImpl$writeData$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.IntRef intRef2 = new Ref.IntRef();
                    StorageConnection<T> storageConnection$datastore_core_release = getStorageConnection$datastore_core_release();
                    DataStoreImpl$writeData$2 dataStoreImpl$writeData$2 = new DataStoreImpl$writeData$2(intRef2, this, t, z, null);
                    dataStoreImpl$writeData$1.L$0 = intRef2;
                    dataStoreImpl$writeData$1.label = 1;
                    if (storageConnection$datastore_core_release.writeScope(dataStoreImpl$writeData$2, dataStoreImpl$writeData$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    intRef = intRef2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    intRef = (Ref.IntRef) dataStoreImpl$writeData$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Boxing.boxInt(intRef.element);
            }
        }
        dataStoreImpl$writeData$1 = new DataStoreImpl$writeData$1(this, continuation);
        Object obj2 = dataStoreImpl$writeData$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = dataStoreImpl$writeData$1.label;
        if (i != 0) {
        }
        return Boxing.boxInt(intRef.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(8:0|1|(2:3|(4:5|6|7|8))|83|6|7|8|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0090, code lost:
    
        r11 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0091, code lost:
    
        r7 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object readDataOrHandleCorruption(boolean z, Continuation<? super Data<T>> continuation) {
        DataStoreImpl$readDataOrHandleCorruption$1 dataStoreImpl$readDataOrHandleCorruption$1;
        ?? r2;
        DataStoreImpl<T> dataStoreImpl;
        Ref.ObjectRef objectRef;
        Object handleCorruption;
        CorruptionException corruptionException;
        Ref.ObjectRef objectRef2;
        CorruptionException corruptionException2;
        DataStoreImpl$readDataOrHandleCorruption$3 dataStoreImpl$readDataOrHandleCorruption$3;
        Ref.IntRef intRef;
        Ref.ObjectRef objectRef3;
        DataStoreImpl<T> dataStoreImpl2;
        DataStoreImpl<T> dataStoreImpl3;
        int hashCode;
        Object version;
        boolean z2;
        int i;
        Object obj;
        if (continuation instanceof DataStoreImpl$readDataOrHandleCorruption$1) {
            dataStoreImpl$readDataOrHandleCorruption$1 = (DataStoreImpl$readDataOrHandleCorruption$1) continuation;
            if ((dataStoreImpl$readDataOrHandleCorruption$1.label & Integer.MIN_VALUE) != 0) {
                dataStoreImpl$readDataOrHandleCorruption$1.label -= Integer.MIN_VALUE;
                Object obj2 = (T) dataStoreImpl$readDataOrHandleCorruption$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                r2 = dataStoreImpl$readDataOrHandleCorruption$1.label;
                switch (r2) {
                    case 0:
                        ResultKt.throwOnFailure(obj2);
                        try {
                            if (z) {
                                dataStoreImpl$readDataOrHandleCorruption$1.L$0 = this;
                                dataStoreImpl$readDataOrHandleCorruption$1.Z$0 = z;
                                dataStoreImpl$readDataOrHandleCorruption$1.label = 1;
                                obj2 = (T) readDataFromFileOrDefault(dataStoreImpl$readDataOrHandleCorruption$1);
                                if (obj2 != coroutine_suspended) {
                                    dataStoreImpl3 = this;
                                    if (obj2 == null) {
                                        try {
                                            hashCode = obj2.hashCode();
                                        } catch (CorruptionException e) {
                                            e = e;
                                            dataStoreImpl = dataStoreImpl3;
                                            objectRef = new Ref.ObjectRef();
                                            CorruptionHandler<T> corruptionHandler = dataStoreImpl.corruptionHandler;
                                            dataStoreImpl$readDataOrHandleCorruption$1.L$0 = dataStoreImpl;
                                            dataStoreImpl$readDataOrHandleCorruption$1.L$1 = e;
                                            dataStoreImpl$readDataOrHandleCorruption$1.L$2 = objectRef;
                                            dataStoreImpl$readDataOrHandleCorruption$1.L$3 = objectRef;
                                            dataStoreImpl$readDataOrHandleCorruption$1.Z$0 = z;
                                            dataStoreImpl$readDataOrHandleCorruption$1.label = 5;
                                            handleCorruption = corruptionHandler.handleCorruption(e, dataStoreImpl$readDataOrHandleCorruption$1);
                                            if (handleCorruption != coroutine_suspended) {
                                            }
                                            return coroutine_suspended;
                                        }
                                    } else {
                                        hashCode = 0;
                                    }
                                    InterProcessCoordinator coordinator = dataStoreImpl3.getCoordinator();
                                    dataStoreImpl$readDataOrHandleCorruption$1.L$0 = dataStoreImpl3;
                                    dataStoreImpl$readDataOrHandleCorruption$1.L$1 = obj2;
                                    dataStoreImpl$readDataOrHandleCorruption$1.Z$0 = z;
                                    dataStoreImpl$readDataOrHandleCorruption$1.I$0 = hashCode;
                                    dataStoreImpl$readDataOrHandleCorruption$1.label = 2;
                                    version = coordinator.getVersion(dataStoreImpl$readDataOrHandleCorruption$1);
                                    if (version != coroutine_suspended) {
                                        int i2 = hashCode;
                                        z2 = z;
                                        i = i2;
                                        obj = obj2;
                                        obj2 = (T) version;
                                        return new Data(obj, i, ((Number) obj2).intValue());
                                    }
                                }
                            } else {
                                InterProcessCoordinator coordinator2 = getCoordinator();
                                dataStoreImpl$readDataOrHandleCorruption$1.L$0 = this;
                                dataStoreImpl$readDataOrHandleCorruption$1.Z$0 = z;
                                dataStoreImpl$readDataOrHandleCorruption$1.label = 3;
                                obj2 = (T) coordinator2.getVersion(dataStoreImpl$readDataOrHandleCorruption$1);
                                if (obj2 != coroutine_suspended) {
                                    dataStoreImpl2 = this;
                                    int intValue = ((Number) obj2).intValue();
                                    InterProcessCoordinator coordinator3 = dataStoreImpl2.getCoordinator();
                                    DataStoreImpl$readDataOrHandleCorruption$2 dataStoreImpl$readDataOrHandleCorruption$2 = new DataStoreImpl$readDataOrHandleCorruption$2(dataStoreImpl2, intValue, null);
                                    dataStoreImpl$readDataOrHandleCorruption$1.L$0 = dataStoreImpl2;
                                    dataStoreImpl$readDataOrHandleCorruption$1.Z$0 = z;
                                    dataStoreImpl$readDataOrHandleCorruption$1.label = 4;
                                    obj2 = (T) coordinator3.tryLock(dataStoreImpl$readDataOrHandleCorruption$2, dataStoreImpl$readDataOrHandleCorruption$1);
                                    if (obj2 == coroutine_suspended) {
                                    }
                                    return (Data) obj2;
                                }
                            }
                        } catch (CorruptionException e2) {
                            e = e2;
                            dataStoreImpl = this;
                            objectRef = new Ref.ObjectRef();
                            CorruptionHandler<T> corruptionHandler2 = dataStoreImpl.corruptionHandler;
                            dataStoreImpl$readDataOrHandleCorruption$1.L$0 = dataStoreImpl;
                            dataStoreImpl$readDataOrHandleCorruption$1.L$1 = e;
                            dataStoreImpl$readDataOrHandleCorruption$1.L$2 = objectRef;
                            dataStoreImpl$readDataOrHandleCorruption$1.L$3 = objectRef;
                            dataStoreImpl$readDataOrHandleCorruption$1.Z$0 = z;
                            dataStoreImpl$readDataOrHandleCorruption$1.label = 5;
                            handleCorruption = corruptionHandler2.handleCorruption(e, dataStoreImpl$readDataOrHandleCorruption$1);
                            if (handleCorruption != coroutine_suspended) {
                                corruptionException = e;
                                obj2 = (T) handleCorruption;
                                objectRef2 = objectRef;
                                objectRef2.element = (T) obj2;
                                Ref.IntRef intRef2 = new Ref.IntRef();
                                try {
                                    dataStoreImpl$readDataOrHandleCorruption$3 = new DataStoreImpl$readDataOrHandleCorruption$3(objectRef, dataStoreImpl, intRef2, null);
                                    dataStoreImpl$readDataOrHandleCorruption$1.L$0 = corruptionException;
                                    dataStoreImpl$readDataOrHandleCorruption$1.L$1 = objectRef;
                                    dataStoreImpl$readDataOrHandleCorruption$1.L$2 = intRef2;
                                    dataStoreImpl$readDataOrHandleCorruption$1.L$3 = null;
                                    dataStoreImpl$readDataOrHandleCorruption$1.label = 6;
                                    if (dataStoreImpl.doWithWriteFileLock(z, dataStoreImpl$readDataOrHandleCorruption$3, dataStoreImpl$readDataOrHandleCorruption$1) != coroutine_suspended) {
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    corruptionException2 = corruptionException;
                                    ExceptionsKt.addSuppressed(corruptionException2, th);
                                    throw corruptionException2;
                                }
                            }
                            return coroutine_suspended;
                        }
                        return coroutine_suspended;
                    case 1:
                        z = dataStoreImpl$readDataOrHandleCorruption$1.Z$0;
                        DataStoreImpl<T> dataStoreImpl4 = (DataStoreImpl) dataStoreImpl$readDataOrHandleCorruption$1.L$0;
                        ResultKt.throwOnFailure(obj2);
                        dataStoreImpl3 = dataStoreImpl4;
                        if (obj2 == null) {
                        }
                        InterProcessCoordinator coordinator4 = dataStoreImpl3.getCoordinator();
                        dataStoreImpl$readDataOrHandleCorruption$1.L$0 = dataStoreImpl3;
                        dataStoreImpl$readDataOrHandleCorruption$1.L$1 = obj2;
                        dataStoreImpl$readDataOrHandleCorruption$1.Z$0 = z;
                        dataStoreImpl$readDataOrHandleCorruption$1.I$0 = hashCode;
                        dataStoreImpl$readDataOrHandleCorruption$1.label = 2;
                        version = coordinator4.getVersion(dataStoreImpl$readDataOrHandleCorruption$1);
                        if (version != coroutine_suspended) {
                        }
                        return coroutine_suspended;
                    case 2:
                        i = dataStoreImpl$readDataOrHandleCorruption$1.I$0;
                        z2 = dataStoreImpl$readDataOrHandleCorruption$1.Z$0;
                        obj = dataStoreImpl$readDataOrHandleCorruption$1.L$1;
                        dataStoreImpl3 = (DataStoreImpl) dataStoreImpl$readDataOrHandleCorruption$1.L$0;
                        try {
                            ResultKt.throwOnFailure(obj2);
                            return new Data(obj, i, ((Number) obj2).intValue());
                        } catch (CorruptionException e3) {
                            e = e3;
                            z = z2;
                            dataStoreImpl = dataStoreImpl3;
                            objectRef = new Ref.ObjectRef();
                            CorruptionHandler<T> corruptionHandler22 = dataStoreImpl.corruptionHandler;
                            dataStoreImpl$readDataOrHandleCorruption$1.L$0 = dataStoreImpl;
                            dataStoreImpl$readDataOrHandleCorruption$1.L$1 = e;
                            dataStoreImpl$readDataOrHandleCorruption$1.L$2 = objectRef;
                            dataStoreImpl$readDataOrHandleCorruption$1.L$3 = objectRef;
                            dataStoreImpl$readDataOrHandleCorruption$1.Z$0 = z;
                            dataStoreImpl$readDataOrHandleCorruption$1.label = 5;
                            handleCorruption = corruptionHandler22.handleCorruption(e, dataStoreImpl$readDataOrHandleCorruption$1);
                            if (handleCorruption != coroutine_suspended) {
                            }
                            return coroutine_suspended;
                        }
                    case 3:
                        z = dataStoreImpl$readDataOrHandleCorruption$1.Z$0;
                        dataStoreImpl2 = (DataStoreImpl) dataStoreImpl$readDataOrHandleCorruption$1.L$0;
                        ResultKt.throwOnFailure(obj2);
                        int intValue2 = ((Number) obj2).intValue();
                        InterProcessCoordinator coordinator32 = dataStoreImpl2.getCoordinator();
                        DataStoreImpl$readDataOrHandleCorruption$2 dataStoreImpl$readDataOrHandleCorruption$22 = new DataStoreImpl$readDataOrHandleCorruption$2(dataStoreImpl2, intValue2, null);
                        dataStoreImpl$readDataOrHandleCorruption$1.L$0 = dataStoreImpl2;
                        dataStoreImpl$readDataOrHandleCorruption$1.Z$0 = z;
                        dataStoreImpl$readDataOrHandleCorruption$1.label = 4;
                        obj2 = (T) coordinator32.tryLock(dataStoreImpl$readDataOrHandleCorruption$22, dataStoreImpl$readDataOrHandleCorruption$1);
                        if (obj2 == coroutine_suspended) {
                        }
                        return (Data) obj2;
                    case 4:
                        boolean z3 = dataStoreImpl$readDataOrHandleCorruption$1.Z$0;
                        ResultKt.throwOnFailure(obj2);
                        return (Data) obj2;
                    case 5:
                        z = dataStoreImpl$readDataOrHandleCorruption$1.Z$0;
                        Ref.ObjectRef objectRef4 = (Ref.ObjectRef) dataStoreImpl$readDataOrHandleCorruption$1.L$3;
                        Ref.ObjectRef objectRef5 = (Ref.ObjectRef) dataStoreImpl$readDataOrHandleCorruption$1.L$2;
                        corruptionException = (CorruptionException) dataStoreImpl$readDataOrHandleCorruption$1.L$1;
                        dataStoreImpl = (DataStoreImpl) dataStoreImpl$readDataOrHandleCorruption$1.L$0;
                        ResultKt.throwOnFailure(obj2);
                        objectRef2 = objectRef4;
                        objectRef = objectRef5;
                        objectRef2.element = (T) obj2;
                        Ref.IntRef intRef22 = new Ref.IntRef();
                        dataStoreImpl$readDataOrHandleCorruption$3 = new DataStoreImpl$readDataOrHandleCorruption$3(objectRef, dataStoreImpl, intRef22, null);
                        dataStoreImpl$readDataOrHandleCorruption$1.L$0 = corruptionException;
                        dataStoreImpl$readDataOrHandleCorruption$1.L$1 = objectRef;
                        dataStoreImpl$readDataOrHandleCorruption$1.L$2 = intRef22;
                        dataStoreImpl$readDataOrHandleCorruption$1.L$3 = null;
                        dataStoreImpl$readDataOrHandleCorruption$1.label = 6;
                        if (dataStoreImpl.doWithWriteFileLock(z, dataStoreImpl$readDataOrHandleCorruption$3, dataStoreImpl$readDataOrHandleCorruption$1) != coroutine_suspended) {
                            intRef = intRef22;
                            objectRef3 = objectRef;
                            T t = objectRef3.element;
                            T t2 = objectRef3.element;
                            return new Data(t, t2 != null ? t2.hashCode() : 0, intRef.element);
                        }
                        return coroutine_suspended;
                    case 6:
                        intRef = (Ref.IntRef) dataStoreImpl$readDataOrHandleCorruption$1.L$2;
                        objectRef3 = (Ref.ObjectRef) dataStoreImpl$readDataOrHandleCorruption$1.L$1;
                        corruptionException2 = (CorruptionException) dataStoreImpl$readDataOrHandleCorruption$1.L$0;
                        try {
                            ResultKt.throwOnFailure(obj2);
                            T t3 = objectRef3.element;
                            T t22 = objectRef3.element;
                            return new Data(t3, t22 != null ? t22.hashCode() : 0, intRef.element);
                        } catch (Throwable th2) {
                            th = th2;
                            ExceptionsKt.addSuppressed(corruptionException2, th);
                            throw corruptionException2;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        dataStoreImpl$readDataOrHandleCorruption$1 = new DataStoreImpl$readDataOrHandleCorruption$1(this, continuation);
        Object obj22 = (T) dataStoreImpl$readDataOrHandleCorruption$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = dataStoreImpl$readDataOrHandleCorruption$1.label;
        switch (r2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> Object doWithWriteFileLock(boolean z, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super R> continuation) {
        if (z) {
            return function1.invoke(continuation);
        }
        return getCoordinator().lock(new DataStoreImpl$doWithWriteFileLock$3(function1, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001BD\u0012=\u0010\u0002\u001a9\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00040\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u000e\u001a\u00020\nH\u0094@¢\u0006\u0002\u0010\u000fRG\u0010\r\u001a;\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/datastore/core/DataStoreImpl$InitDataStore;", "Landroidx/datastore/core/RunOnce;", "initTasksList", "", "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "api", "Lkotlin/coroutines/Continuation;", "", "", "(Landroidx/datastore/core/DataStoreImpl;Ljava/util/List;)V", "initTasks", "doRun", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    final class InitDataStore extends RunOnce {
        private List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasks;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public InitDataStore(DataStoreImpl dataStoreImpl, List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasksList) {
            Intrinsics.checkNotNullParameter(initTasksList, "initTasksList");
            this.this$0 = dataStoreImpl;
            this.initTasks = CollectionsKt.toList(initTasksList);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0041  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
        @Override // androidx.datastore.core.RunOnce
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        protected Object doRun(Continuation<? super Unit> continuation) {
            DataStoreImpl$InitDataStore$doRun$1 dataStoreImpl$InitDataStore$doRun$1;
            int i;
            InitDataStore initDataStore;
            Data data;
            if (continuation instanceof DataStoreImpl$InitDataStore$doRun$1) {
                dataStoreImpl$InitDataStore$doRun$1 = (DataStoreImpl$InitDataStore$doRun$1) continuation;
                if ((dataStoreImpl$InitDataStore$doRun$1.label & Integer.MIN_VALUE) != 0) {
                    dataStoreImpl$InitDataStore$doRun$1.label -= Integer.MIN_VALUE;
                    Object obj = dataStoreImpl$InitDataStore$doRun$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = dataStoreImpl$InitDataStore$doRun$1.label;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> list = this.initTasks;
                        if (list != null) {
                            Intrinsics.checkNotNull(list);
                            if (!list.isEmpty()) {
                                InterProcessCoordinator coordinator = this.this$0.getCoordinator();
                                DataStoreImpl$InitDataStore$doRun$initData$1 dataStoreImpl$InitDataStore$doRun$initData$1 = new DataStoreImpl$InitDataStore$doRun$initData$1(this.this$0, this, null);
                                dataStoreImpl$InitDataStore$doRun$1.L$0 = this;
                                dataStoreImpl$InitDataStore$doRun$1.label = 2;
                                obj = coordinator.lock(dataStoreImpl$InitDataStore$doRun$initData$1, dataStoreImpl$InitDataStore$doRun$1);
                                if (obj != coroutine_suspended) {
                                    initDataStore = this;
                                    data = (Data) obj;
                                }
                                return coroutine_suspended;
                            }
                        }
                        DataStoreImpl<T> dataStoreImpl = this.this$0;
                        dataStoreImpl$InitDataStore$doRun$1.L$0 = this;
                        dataStoreImpl$InitDataStore$doRun$1.label = 1;
                        obj = dataStoreImpl.readDataOrHandleCorruption(false, dataStoreImpl$InitDataStore$doRun$1);
                        if (obj != coroutine_suspended) {
                            initDataStore = this;
                            data = (Data) obj;
                        }
                        return coroutine_suspended;
                    }
                    if (i == 1) {
                        initDataStore = (InitDataStore) dataStoreImpl$InitDataStore$doRun$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        data = (Data) obj;
                    } else {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        initDataStore = (InitDataStore) dataStoreImpl$InitDataStore$doRun$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        data = (Data) obj;
                    }
                    ((DataStoreImpl) initDataStore.this$0).inMemoryCache.tryUpdate(data);
                    return Unit.INSTANCE;
                }
            }
            dataStoreImpl$InitDataStore$doRun$1 = new DataStoreImpl$InitDataStore$doRun$1(this, continuation);
            Object obj2 = dataStoreImpl$InitDataStore$doRun$1.result;
            Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            i = dataStoreImpl$InitDataStore$doRun$1.label;
            if (i != 0) {
            }
            ((DataStoreImpl) initDataStore.this$0).inMemoryCache.tryUpdate(data);
            return Unit.INSTANCE;
        }
    }
}
