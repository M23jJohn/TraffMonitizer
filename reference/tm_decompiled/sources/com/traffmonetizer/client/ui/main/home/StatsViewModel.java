package com.traffmonetizer.client.ui.main.home;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.traffmonetizer.client.service.TraffmonetizerServiceManager;
import com.traffmonetizer.client.traffmonetizer_repository.model.ConnectionStatusEntity;
import com.traffmonetizer.sdk.model.BalanceInfo;
import com.traffmonetizer.sdk.model.StatsInfo;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: StatsViewModel.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\r\u001a\u00020\u000eH\u0082@¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/traffmonetizer/client/ui/main/home/StatsViewModel;", "Landroidx/lifecycle/ViewModel;", "traffmonetizerServiceManager", "Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager;", "<init>", "(Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager;)V", "statsFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/traffmonetizer/client/ui/main/home/TraffStatsEntity;", "statsInfo", "Lkotlinx/coroutines/flow/StateFlow;", "getStatsInfo", "()Lkotlinx/coroutines/flow/StateFlow;", "requestData", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StatsViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<TraffStatsEntity> statsFlow;
    private final StateFlow<TraffStatsEntity> statsInfo;
    private final TraffmonetizerServiceManager traffmonetizerServiceManager;

    @Inject
    public StatsViewModel(TraffmonetizerServiceManager traffmonetizerServiceManager) {
        Intrinsics.checkNotNullParameter(traffmonetizerServiceManager, "traffmonetizerServiceManager");
        this.traffmonetizerServiceManager = traffmonetizerServiceManager;
        MutableStateFlow<TraffStatsEntity> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this.statsFlow = MutableStateFlow;
        this.statsInfo = FlowKt.asStateFlow(MutableStateFlow);
        FlowKt.launchIn(FlowKt.onEach(FlowKt.transformLatest(traffmonetizerServiceManager.getTraffConnectionStatus(), new AnonymousClass1(null)), new AnonymousClass2(null)), ViewModelKt.getViewModelScope(this));
    }

    public final StateFlow<TraffStatsEntity> getStatsInfo() {
        return this.statsInfo;
    }

    /* compiled from: StatsViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "connection", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.traffmonetizer.client.ui.main.home.StatsViewModel$1", f = "StatsViewModel.kt", i = {0, 0, 1, 1}, l = {32, 33}, m = "invokeSuspend", n = {"$this$transformLatest", "connection", "$this$transformLatest", "connection"}, s = {"L$0", "L$1", "L$0", "L$1"}, v = 1)
    /* renamed from: com.traffmonetizer.client.ui.main.home.StatsViewModel$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function3<FlowCollector<? super Unit>, ConnectionStatusEntity, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super Unit> flowCollector, ConnectionStatusEntity connectionStatusEntity, Continuation<? super Unit> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.L$0 = flowCollector;
            anonymousClass1.L$1 = connectionStatusEntity;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0059, code lost:
        
            return r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x0040, code lost:
        
            if (r0.emit(kotlin.Unit.INSTANCE, r8) == r2) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0057, code lost:
        
            if (kotlinx.coroutines.DelayKt.delay(60000, r8) == r2) goto L17;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x0057 -> B:6:0x002d). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            ConnectionStatusEntity connectionStatusEntity = (ConnectionStatusEntity) this.L$1;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (!(connectionStatusEntity instanceof ConnectionStatusEntity.Connected)) {
                    return Unit.INSTANCE;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = flowCollector;
                this.L$1 = SpillingKt.nullOutSpilledVariable(connectionStatusEntity);
                this.label = 2;
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = flowCollector;
            this.L$1 = SpillingKt.nullOutSpilledVariable(connectionStatusEntity);
            this.label = 1;
        }
    }

    /* compiled from: StatsViewModel.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.traffmonetizer.client.ui.main.home.StatsViewModel$2", f = "StatsViewModel.kt", i = {}, l = {37}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: com.traffmonetizer.client.ui.main.home.StatsViewModel$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return StatsViewModel.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (StatsViewModel.this.requestData(this) == coroutine_suspended) {
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
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0048, code lost:
    
        if (r15 == r1) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[LOOP:0: B:15:0x0065->B:18:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object requestData(Continuation<? super Unit> continuation) {
        StatsViewModel$requestData$1 statsViewModel$requestData$1;
        Object coroutine_suspended;
        int i;
        Object stats;
        BalanceInfo balanceInfo;
        StatsInfo statsInfo;
        MutableStateFlow<TraffStatsEntity> mutableStateFlow;
        if (continuation instanceof StatsViewModel$requestData$1) {
            statsViewModel$requestData$1 = (StatsViewModel$requestData$1) continuation;
            if ((statsViewModel$requestData$1.label & Integer.MIN_VALUE) != 0) {
                statsViewModel$requestData$1.label -= Integer.MIN_VALUE;
                Object obj = statsViewModel$requestData$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = statsViewModel$requestData$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    TraffmonetizerServiceManager traffmonetizerServiceManager = this.traffmonetizerServiceManager;
                    statsViewModel$requestData$1.label = 1;
                    obj = traffmonetizerServiceManager.getBalance(statsViewModel$requestData$1);
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        balanceInfo = (BalanceInfo) statsViewModel$requestData$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        statsInfo = (StatsInfo) obj;
                        if (balanceInfo != null && statsInfo != null) {
                            mutableStateFlow = this.statsFlow;
                            while (!mutableStateFlow.compareAndSet(mutableStateFlow.getValue(), new TraffStatsEntity(balanceInfo.getBalance(), balanceInfo.getLast30Days(), statsInfo.getInboundTraffic() + statsInfo.getOutboundTraffic()))) {
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                BalanceInfo balanceInfo2 = (BalanceInfo) obj;
                TraffmonetizerServiceManager traffmonetizerServiceManager2 = this.traffmonetizerServiceManager;
                statsViewModel$requestData$1.L$0 = balanceInfo2;
                statsViewModel$requestData$1.label = 2;
                stats = traffmonetizerServiceManager2.getStats(statsViewModel$requestData$1);
                if (stats != coroutine_suspended) {
                    balanceInfo = balanceInfo2;
                    obj = stats;
                    statsInfo = (StatsInfo) obj;
                    if (balanceInfo != null) {
                        mutableStateFlow = this.statsFlow;
                        while (!mutableStateFlow.compareAndSet(mutableStateFlow.getValue(), new TraffStatsEntity(balanceInfo.getBalance(), balanceInfo.getLast30Days(), statsInfo.getInboundTraffic() + statsInfo.getOutboundTraffic()))) {
                        }
                    }
                    return Unit.INSTANCE;
                }
                return coroutine_suspended;
            }
        }
        statsViewModel$requestData$1 = new StatsViewModel$requestData$1(this, continuation);
        Object obj2 = statsViewModel$requestData$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = statsViewModel$requestData$1.label;
        if (i != 0) {
        }
        BalanceInfo balanceInfo22 = (BalanceInfo) obj2;
        TraffmonetizerServiceManager traffmonetizerServiceManager22 = this.traffmonetizerServiceManager;
        statsViewModel$requestData$1.L$0 = balanceInfo22;
        statsViewModel$requestData$1.label = 2;
        stats = traffmonetizerServiceManager22.getStats(statsViewModel$requestData$1);
        if (stats != coroutine_suspended) {
        }
        return coroutine_suspended;
    }
}
