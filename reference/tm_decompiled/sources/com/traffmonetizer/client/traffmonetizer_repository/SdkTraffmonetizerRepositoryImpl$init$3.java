package com.traffmonetizer.client.traffmonetizer_repository;

import com.traffmonetizer.client.utils.Logger;
import com.traffmonetizer.sdk.TraffmonetizerSdk;
import com.traffmonetizer.sdk.model.DebugMessage;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: SdkTraffmonetizerRepositoryImpl.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.traffmonetizer.client.traffmonetizer_repository.SdkTraffmonetizerRepositoryImpl$init$3", f = "SdkTraffmonetizerRepositoryImpl.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class SdkTraffmonetizerRepositoryImpl$init$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    SdkTraffmonetizerRepositoryImpl$init$3(Continuation<? super SdkTraffmonetizerRepositoryImpl$init$3> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SdkTraffmonetizerRepositoryImpl$init$3(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SdkTraffmonetizerRepositoryImpl$init$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (TraffmonetizerSdk.INSTANCE.getDebugMessages().collect(new FlowCollector() { // from class: com.traffmonetizer.client.traffmonetizer_repository.SdkTraffmonetizerRepositoryImpl$init$3.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DebugMessage) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DebugMessage debugMessage, Continuation<? super Unit> continuation) {
                    Logger.INSTANCE.logSdk(debugMessage);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
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
