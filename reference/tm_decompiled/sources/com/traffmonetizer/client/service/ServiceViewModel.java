package com.traffmonetizer.client.service;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import com.traffmonetizer.client.preferences.model.AppPreferences;
import com.traffmonetizer.client.traffmonetizer_repository.model.ConnectionStatusEntity;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;

/* compiled from: ServiceViewModel.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\u0006\u0010\u0011\u001a\u00020\u0010J\u0006\u0010\u0012\u001a\u00020\u0010J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/traffmonetizer/client/service/ServiceViewModel;", "Landroidx/lifecycle/ViewModel;", "traffmonetizerServiceManager", "Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager;", "preferencesRepository", "Lcom/traffmonetizer/client/preferences/PreferencesRepository;", "<init>", "(Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager;Lcom/traffmonetizer/client/preferences/PreferencesRepository;)V", "traffConnectionStatus", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "getTraffConnectionStatus", "()Lkotlinx/coroutines/flow/StateFlow;", "loggedStateJob", "Lkotlinx/coroutines/Job;", "onCleared", "", "onPause", "onResume", "listenLoggedState", "bindService", "unbindService", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ServiceViewModel extends ViewModel {
    public static final int $stable = 8;
    private Job loggedStateJob;
    private final PreferencesRepository preferencesRepository;
    private final StateFlow<ConnectionStatusEntity> traffConnectionStatus;
    private final TraffmonetizerServiceManager traffmonetizerServiceManager;

    @Inject
    public ServiceViewModel(TraffmonetizerServiceManager traffmonetizerServiceManager, PreferencesRepository preferencesRepository) {
        Intrinsics.checkNotNullParameter(traffmonetizerServiceManager, "traffmonetizerServiceManager");
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        this.traffmonetizerServiceManager = traffmonetizerServiceManager;
        this.preferencesRepository = preferencesRepository;
        this.traffConnectionStatus = traffmonetizerServiceManager.getTraffConnectionStatus();
        Timber.INSTANCE.d("init: " + hashCode(), new Object[0]);
        listenLoggedState();
    }

    public final StateFlow<ConnectionStatusEntity> getTraffConnectionStatus() {
        return this.traffConnectionStatus;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        Timber.INSTANCE.d("onCleared: " + hashCode(), new Object[0]);
        super.onCleared();
        unbindService();
    }

    public final void onPause() {
        Job job = this.loggedStateJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.loggedStateJob = null;
        unbindService();
    }

    public final void onResume() {
        if (this.loggedStateJob == null) {
            listenLoggedState();
        }
    }

    private final void listenLoggedState() {
        final Flow<AppPreferences> appPreferences = this.preferencesRepository.getAppPreferences();
        this.loggedStateJob = FlowKt.launchIn(FlowKt.onEach(FlowKt.distinctUntilChanged(new Flow<Boolean>() { // from class: com.traffmonetizer.client.service.ServiceViewModel$listenLoggedState$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 2, 0}, xi = 48)
            /* renamed from: com.traffmonetizer.client.service.ServiceViewModel$listenLoggedState$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
                @DebugMetadata(c = "com.traffmonetizer.client.service.ServiceViewModel$listenLoggedState$$inlined$map$1$2", f = "ServiceViewModel.kt", i = {0, 0, 0, 0, 0}, l = {219}, m = "emit", n = {"value", "$completion", "value", "$this$map_u24lambda_u245", "$i$a$-unsafeTransform-FlowKt__TransformKt$map$1"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
                /* renamed from: com.traffmonetizer.client.service.ServiceViewModel$listenLoggedState$$inlined$map$1$2$1, reason: invalid class name */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0040  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    int i;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                            Object obj2 = anonymousClass1.result;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            i = anonymousClass1.label;
                            if (i != 0) {
                                ResultKt.throwOnFailure(obj2);
                                FlowCollector flowCollector = this.$this_unsafeFlow;
                                Boolean boxBoolean = Boxing.boxBoolean(((AppPreferences) obj).getToken() != null);
                                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass1);
                                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                                anonymousClass1.I$0 = 0;
                                anonymousClass1.label = 1;
                                if (flowCollector.emit(boxBoolean, anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                int i2 = anonymousClass1.I$0;
                                Object obj3 = anonymousClass1.L$2;
                                Object obj4 = anonymousClass1.L$0;
                                ResultKt.throwOnFailure(obj2);
                            }
                            return Unit.INSTANCE;
                        }
                    }
                    anonymousClass1 = new AnonymousClass1(continuation);
                    Object obj22 = anonymousClass1.result;
                    Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = anonymousClass1.label;
                    if (i != 0) {
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super Boolean> flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        }), new ServiceViewModel$listenLoggedState$2(this, null)), ViewModelKt.getViewModelScope(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bindService() {
        this.traffmonetizerServiceManager.startAndBindService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unbindService() {
        this.traffmonetizerServiceManager.unbindService();
    }
}
