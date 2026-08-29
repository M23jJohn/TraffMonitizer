package com.traffmonetizer.client.service;

import com.traffmonetizer.client.traffmonetizer_repository.model.ProxyStatusEntity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: TraffmonetizerServiceManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.traffmonetizer.client.service.TraffmonetizerServiceManager$serviceConnection$1$onServiceConnected$1", f = "TraffmonetizerServiceManager.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class TraffmonetizerServiceManager$serviceConnection$1$onServiceConnected$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ TraffmonetizerServiceManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TraffmonetizerServiceManager$serviceConnection$1$onServiceConnected$1(TraffmonetizerServiceManager traffmonetizerServiceManager, Continuation<? super TraffmonetizerServiceManager$serviceConnection$1$onServiceConnected$1> continuation) {
        super(2, continuation);
        this.this$0 = traffmonetizerServiceManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TraffmonetizerServiceManager$serviceConnection$1$onServiceConnected$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TraffmonetizerServiceManager$serviceConnection$1$onServiceConnected$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        TraffmonetizerService traffmonetizerService;
        Flow<ProxyStatusEntity> proxyStatus;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            traffmonetizerService = this.this$0.traffmonetizerService;
            if (traffmonetizerService != null && (proxyStatus = traffmonetizerService.getProxyStatus()) != null) {
                final TraffmonetizerServiceManager traffmonetizerServiceManager = this.this$0;
                this.label = 1;
                if (proxyStatus.collect(new FlowCollector() { // from class: com.traffmonetizer.client.service.TraffmonetizerServiceManager$serviceConnection$1$onServiceConnected$1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((ProxyStatusEntity) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(ProxyStatusEntity proxyStatusEntity, Continuation<? super Unit> continuation) {
                        MutableStateFlow mutableStateFlow;
                        mutableStateFlow = TraffmonetizerServiceManager.this.traffProxyStatusFlow;
                        mutableStateFlow.setValue(proxyStatusEntity);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
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
