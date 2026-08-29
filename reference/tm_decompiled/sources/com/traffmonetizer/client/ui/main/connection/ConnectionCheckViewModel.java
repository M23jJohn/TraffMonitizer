package com.traffmonetizer.client.ui.main.connection;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.traffmonetizer.client.service.TraffmonetizerServiceManager;
import com.traffmonetizer.client.traffmonetizer_repository.model.ConnectionStatusEntity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: ConnectionCheckViewModel.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J*\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u0002`\u00162\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/traffmonetizer/client/ui/main/connection/ConnectionCheckViewModel;", "Landroidx/lifecycle/ViewModel;", "traffmonetizerServiceManager", "Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager;", "<init>", "(Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager;)V", "checkFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/traffmonetizer/client/ui/main/connection/ConnectionCheckStep;", "Lcom/traffmonetizer/client/ui/main/connection/ConnectionCheckStatus;", "checkState", "Lkotlinx/coroutines/flow/StateFlow;", "getCheckState", "()Lkotlinx/coroutines/flow/StateFlow;", "checkJob", "Lkotlinx/coroutines/Job;", "onConnectionStatusUpdated", "", NotificationCompat.CATEGORY_STATUS, "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "mapStatus", "Lcom/traffmonetizer/client/ui/main/connection/ConnectionCheckState;", "lastStep", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConnectionCheckViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<Map<ConnectionCheckStep, ConnectionCheckStatus>> checkFlow;
    private Job checkJob;
    private final StateFlow<Map<ConnectionCheckStep, ConnectionCheckStatus>> checkState;
    private final TraffmonetizerServiceManager traffmonetizerServiceManager;

    @Inject
    public ConnectionCheckViewModel(TraffmonetizerServiceManager traffmonetizerServiceManager) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(traffmonetizerServiceManager, "traffmonetizerServiceManager");
        this.traffmonetizerServiceManager = traffmonetizerServiceManager;
        MutableStateFlow<Map<ConnectionCheckStep, ConnectionCheckStatus>> MutableStateFlow = StateFlowKt.MutableStateFlow(MapsKt.mapOf(TuplesKt.to(ConnectionCheckStep.INTERNET_CONNECTION, ConnectionCheckStatus.PROGRESS)));
        this.checkFlow = MutableStateFlow;
        this.checkState = FlowKt.asStateFlow(MutableStateFlow);
        traffmonetizerServiceManager.reconnect();
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
        this.checkJob = launch$default;
    }

    public final StateFlow<Map<ConnectionCheckStep, ConnectionCheckStatus>> getCheckState() {
        return this.checkState;
    }

    /* compiled from: ConnectionCheckViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.traffmonetizer.client.ui.main.connection.ConnectionCheckViewModel$1", f = "ConnectionCheckViewModel.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: com.traffmonetizer.client.ui.main.connection.ConnectionCheckViewModel$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ConnectionCheckViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow drop = FlowKt.drop(ConnectionCheckViewModel.this.traffmonetizerServiceManager.getTraffConnectionStatus(), 1);
                final ConnectionCheckViewModel connectionCheckViewModel = ConnectionCheckViewModel.this;
                this.label = 1;
                if (drop.collect(new FlowCollector() { // from class: com.traffmonetizer.client.ui.main.connection.ConnectionCheckViewModel.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((ConnectionStatusEntity) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(ConnectionStatusEntity connectionStatusEntity, Continuation<? super Unit> continuation) {
                        ConnectionCheckViewModel.this.onConnectionStatusUpdated(connectionStatusEntity);
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void onConnectionStatusUpdated(ConnectionStatusEntity status) {
        if ((status instanceof ConnectionStatusEntity.Connected) || (status instanceof ConnectionStatusEntity.Failed)) {
            Job.DefaultImpls.cancel$default(this.checkJob, (CancellationException) null, 1, (Object) null);
        }
        Map<ConnectionCheckStep, ConnectionCheckStatus> mutableMap = MapsKt.toMutableMap(this.checkFlow.getValue());
        mutableMap.putAll(mapStatus(status, (ConnectionCheckStep) CollectionsKt.lastOrNull(mutableMap.keySet())));
        MutableStateFlow<Map<ConnectionCheckStep, ConnectionCheckStatus>> mutableStateFlow = this.checkFlow;
        while (!mutableStateFlow.compareAndSet(mutableStateFlow.getValue(), mutableMap)) {
        }
    }

    private final Map<ConnectionCheckStep, ConnectionCheckStatus> mapStatus(ConnectionStatusEntity status, ConnectionCheckStep lastStep) {
        if (Intrinsics.areEqual(status, ConnectionStatusEntity.SocketConnecting.INSTANCE)) {
            return MapsKt.mapOf(TuplesKt.to(ConnectionCheckStep.INTERNET_CONNECTION, ConnectionCheckStatus.OK), TuplesKt.to(ConnectionCheckStep.LOAD_BALANCER, ConnectionCheckStatus.OK), TuplesKt.to(ConnectionCheckStep.SERVER_CONNECTION, ConnectionCheckStatus.PROGRESS));
        }
        if (Intrinsics.areEqual(status, ConnectionStatusEntity.HelloConnecting.INSTANCE)) {
            return MapsKt.mapOf(TuplesKt.to(ConnectionCheckStep.SERVER_CONNECTION, ConnectionCheckStatus.OK), TuplesKt.to(ConnectionCheckStep.TOKEN_CHECK, ConnectionCheckStatus.PROGRESS));
        }
        if (Intrinsics.areEqual(status, ConnectionStatusEntity.Connected.INSTANCE)) {
            return MapsKt.mapOf(TuplesKt.to(ConnectionCheckStep.TOKEN_CHECK, ConnectionCheckStatus.OK), TuplesKt.to(ConnectionCheckStep.NO_ERROR, ConnectionCheckStatus.OK));
        }
        if (status instanceof ConnectionStatusEntity.Failed) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (lastStep != null) {
            }
            linkedHashMap.put(ConnectionCheckStep.ERROR, ConnectionCheckStatus.EMPTY);
            return linkedHashMap;
        }
        return MapsKt.emptyMap();
    }
}
