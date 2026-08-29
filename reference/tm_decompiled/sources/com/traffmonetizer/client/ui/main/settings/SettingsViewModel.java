package com.traffmonetizer.client.ui.main.settings;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import com.traffmonetizer.client.preferences.model.AppPreferences;
import com.traffmonetizer.client.service.TraffmonetizerServiceManager;
import com.traffmonetizer.client.traffmonetizer_repository.model.ConnectionStatusEntity;
import com.traffmonetizer.client.utils.Logger;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: SettingsViewModel.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u0017H\u0002J\u000e\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u0011J\u0006\u0010!\u001a\u00020\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/traffmonetizer/client/ui/main/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "preferencesRepository", "Lcom/traffmonetizer/client/preferences/PreferencesRepository;", "traffmonetizerServiceManager", "Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager;", "<init>", "(Lcom/traffmonetizer/client/preferences/PreferencesRepository;Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager;)V", "settingsStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/traffmonetizer/client/ui/main/settings/SettingsState;", "settingsState", "Lkotlinx/coroutines/flow/StateFlow;", "getSettingsState", "()Lkotlinx/coroutines/flow/StateFlow;", "errorChannel", "Lkotlinx/coroutines/channels/Channel;", "", "error", "Lkotlinx/coroutines/flow/Flow;", "getError", "()Lkotlinx/coroutines/flow/Flow;", "setWifiOnly", "", "value", "", "setDebugMode", "sendLogs", "context", "Landroid/content/Context;", "getDeviceName", "setDeviceName", "newName", "editDeviceName", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SettingsViewModel extends ViewModel {
    public static final int $stable = 8;
    private final Flow<String> error;
    private final Channel<String> errorChannel;
    private final PreferencesRepository preferencesRepository;
    private final StateFlow<SettingsState> settingsState;
    private final MutableStateFlow<SettingsState> settingsStateFlow;
    private final TraffmonetizerServiceManager traffmonetizerServiceManager;

    @Inject
    public SettingsViewModel(PreferencesRepository preferencesRepository, TraffmonetizerServiceManager traffmonetizerServiceManager) {
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        Intrinsics.checkNotNullParameter(traffmonetizerServiceManager, "traffmonetizerServiceManager");
        this.preferencesRepository = preferencesRepository;
        this.traffmonetizerServiceManager = traffmonetizerServiceManager;
        MutableStateFlow<SettingsState> MutableStateFlow = StateFlowKt.MutableStateFlow(new SettingsState(true, false, new DeviceNameState(null, DeviceNameStatus.LOADING)));
        this.settingsStateFlow = MutableStateFlow;
        this.settingsState = FlowKt.asStateFlow(MutableStateFlow);
        Channel<String> Channel$default = ChannelKt.Channel$default(0, null, null, 7, null);
        this.errorChannel = Channel$default;
        this.error = FlowKt.receiveAsFlow(Channel$default);
        SettingsViewModel settingsViewModel = this;
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(settingsViewModel), null, null, new AnonymousClass1(null), 3, null);
        FlowKt.launchIn(FlowKt.onEach(FlowKt.transformLatest(traffmonetizerServiceManager.getTraffConnectionStatus(), new AnonymousClass2(null)), new AnonymousClass3(null)), ViewModelKt.getViewModelScope(settingsViewModel));
    }

    public final StateFlow<SettingsState> getSettingsState() {
        return this.settingsState;
    }

    public final Flow<String> getError() {
        return this.error;
    }

    /* compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.traffmonetizer.client.ui.main.settings.SettingsViewModel$1", f = "SettingsViewModel.kt", i = {}, l = {44}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: com.traffmonetizer.client.ui.main.settings.SettingsViewModel$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new AnonymousClass1(continuation);
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
                Flow<AppPreferences> appPreferences = SettingsViewModel.this.preferencesRepository.getAppPreferences();
                final SettingsViewModel settingsViewModel = SettingsViewModel.this;
                this.label = 1;
                if (appPreferences.collect(new FlowCollector() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsViewModel.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((AppPreferences) obj2, (Continuation<? super Unit>) continuation);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    public final Object emit(AppPreferences appPreferences2, Continuation<? super Unit> continuation) {
                        Object value;
                        MutableStateFlow mutableStateFlow = SettingsViewModel.this.settingsStateFlow;
                        do {
                            value = mutableStateFlow.getValue();
                        } while (!mutableStateFlow.compareAndSet(value, SettingsState.copy$default((SettingsState) value, appPreferences2.getWifiOnly(), appPreferences2.getDebugMode(), null, 4, null)));
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

    /* compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "connection", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.traffmonetizer.client.ui.main.settings.SettingsViewModel$2", f = "SettingsViewModel.kt", i = {0, 0}, l = {55}, m = "invokeSuspend", n = {"$this$transformLatest", "connection"}, s = {"L$0", "L$1"}, v = 1)
    /* renamed from: com.traffmonetizer.client.ui.main.settings.SettingsViewModel$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function3<FlowCollector<? super Unit>, ConnectionStatusEntity, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super Unit> flowCollector, ConnectionStatusEntity connectionStatusEntity, Continuation<? super Unit> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = flowCollector;
            anonymousClass2.L$1 = connectionStatusEntity;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            ConnectionStatusEntity connectionStatusEntity = (ConnectionStatusEntity) this.L$1;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (connectionStatusEntity instanceof ConnectionStatusEntity.Connected) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(connectionStatusEntity);
                    this.label = 1;
                    if (flowCollector.emit(Unit.INSTANCE, this) == coroutine_suspended) {
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

    /* compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.traffmonetizer.client.ui.main.settings.SettingsViewModel$3", f = "SettingsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: com.traffmonetizer.client.ui.main.settings.SettingsViewModel$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                SettingsViewModel.this.getDeviceName();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void setWifiOnly(boolean value) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new SettingsViewModel$setWifiOnly$1(this, value, null), 3, null);
    }

    public final void setDebugMode(boolean value) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new SettingsViewModel$setDebugMode$1(this, value, null), 3, null);
    }

    public final void sendLogs(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Logger.INSTANCE.shareLogFile(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getDeviceName() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new SettingsViewModel$getDeviceName$1(this, null), 3, null);
    }

    public final void setDeviceName(String newName) {
        SettingsState value;
        SettingsState settingsState;
        Intrinsics.checkNotNullParameter(newName, "newName");
        if (!Intrinsics.areEqual(this.settingsStateFlow.getValue().getDeviceName().getName(), newName)) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new SettingsViewModel$setDeviceName$2(this, newName, null), 3, null);
            return;
        }
        MutableStateFlow<SettingsState> mutableStateFlow = this.settingsStateFlow;
        do {
            value = mutableStateFlow.getValue();
            settingsState = value;
        } while (!mutableStateFlow.compareAndSet(value, SettingsState.copy$default(settingsState, false, false, DeviceNameState.copy$default(settingsState.getDeviceName(), null, DeviceNameStatus.SHOWING, 1, null), 3, null)));
    }

    public final void editDeviceName() {
        SettingsState value;
        SettingsState settingsState;
        MutableStateFlow<SettingsState> mutableStateFlow = this.settingsStateFlow;
        do {
            value = mutableStateFlow.getValue();
            settingsState = value;
        } while (!mutableStateFlow.compareAndSet(value, SettingsState.copy$default(settingsState, false, false, DeviceNameState.copy$default(settingsState.getDeviceName(), null, DeviceNameStatus.EDITING, 1, null), 3, null)));
    }
}
