package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* compiled from: DataStoreImpl.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "", "T"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$3", f = "DataStoreImpl.kt", i = {}, l = {387, 388, 390}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class DataStoreImpl$readDataOrHandleCorruption$3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<T> $newData;
    final /* synthetic */ Ref.IntRef $version;
    Object L$0;
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataStoreImpl$readDataOrHandleCorruption$3(Ref.ObjectRef<T> objectRef, DataStoreImpl<T> dataStoreImpl, Ref.IntRef intRef, Continuation<? super DataStoreImpl$readDataOrHandleCorruption$3> continuation) {
        super(1, continuation);
        this.$newData = objectRef;
        this.this$0 = dataStoreImpl;
        this.$version = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new DataStoreImpl$readDataOrHandleCorruption$3(this.$newData, this.this$0, this.$version, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((DataStoreImpl$readDataOrHandleCorruption$3) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref.IntRef intRef;
        Object obj2;
        Ref.ObjectRef objectRef;
        Object readDataFromFileOrDefault;
        T t;
        Ref.IntRef intRef2;
        Object obj3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (CorruptionException unused) {
            Ref.IntRef intRef3 = this.$version;
            this.L$0 = intRef3;
            this.label = 3;
            Object writeData$datastore_core_release = this.this$0.writeData$datastore_core_release(this.$newData.element, true, this);
            if (writeData$datastore_core_release != coroutine_suspended) {
                intRef = intRef3;
                obj2 = writeData$datastore_core_release;
            }
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            objectRef = this.$newData;
            this.L$0 = objectRef;
            this.label = 1;
            readDataFromFileOrDefault = this.this$0.readDataFromFileOrDefault(this);
            t = readDataFromFileOrDefault;
            if (readDataFromFileOrDefault == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    intRef2 = (Ref.IntRef) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj3 = obj;
                    intRef2.element = ((Number) obj3).intValue();
                    return Unit.INSTANCE;
                }
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                intRef = (Ref.IntRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = obj;
                intRef.element = ((Number) obj2).intValue();
                return Unit.INSTANCE;
            }
            objectRef = (Ref.ObjectRef) this.L$0;
            ResultKt.throwOnFailure(obj);
            t = obj;
        }
        objectRef.element = t;
        intRef2 = this.$version;
        this.L$0 = intRef2;
        this.label = 2;
        Object version = this.this$0.getCoordinator().getVersion(this);
        obj3 = version;
        if (version == coroutine_suspended) {
            return coroutine_suspended;
        }
        intRef2.element = ((Number) obj3).intValue();
        return Unit.INSTANCE;
    }
}
