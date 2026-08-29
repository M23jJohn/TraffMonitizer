package com.traffmonetizer.client.traffmonetizer_repository;

import android.content.Context;
import androidx.autofill.HintConstants;
import com.traffmonetizer.client.config.ConfigRepository;
import com.traffmonetizer.client.traffmonetizer_repository.model.ConnectionStatusEntity;
import com.traffmonetizer.client.traffmonetizer_repository.model.ProxyStatusEntity;
import com.traffmonetizer.sdk.TraffmonetizerSdk;
import com.traffmonetizer.sdk.model.BalanceInfo;
import com.traffmonetizer.sdk.model.StatsInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import timber.log.Timber;

/* compiled from: SdkTraffmonetizerRepositoryImpl.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J2\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0016J\b\u0010\u0019\u001a\u00020\u000fH\u0016J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u001bH\u0016J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\u001bH\u0016J\u0010\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0015H\u0016J\u000e\u0010\u001f\u001a\u00020 H\u0096@¢\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020#H\u0096@¢\u0006\u0002\u0010!J\b\u0010$\u001a\u00020\u000fH\u0016J\u0010\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u0015H\u0016J\u0016\u0010'\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\u0013H\u0096@¢\u0006\u0002\u0010)J\u000e\u0010*\u001a\u00020\u0013H\u0096@¢\u0006\u0002\u0010!J\u0010\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/SdkTraffmonetizerRepositoryImpl;", "Lcom/traffmonetizer/client/traffmonetizer_repository/TraffmonetizerRepository;", "configRepository", "Lcom/traffmonetizer/client/config/ConfigRepository;", "<init>", "(Lcom/traffmonetizer/client/config/ConfigRepository;)V", "connectionStatusFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "proxyStatusFlow", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity;", "sdkListenJobs", "", "Lkotlinx/coroutines/Job;", "init", "", "context", "Landroid/content/Context;", "token", "", "wifiOnly", "", "proxyActive", "loggingActive", "start", "stop", "getConnectionStatus", "Lkotlinx/coroutines/flow/Flow;", "getProxyStatus", "setProxyActive", "active", "getBalance", "Lcom/traffmonetizer/sdk/model/BalanceInfo;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStats", "Lcom/traffmonetizer/sdk/model/StatsInfo;", "reconnect", "setWifiOnly", "value", "setDeviceName", HintConstants.AUTOFILL_HINT_NAME, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDeviceName", "setLoggingEnabled", "enabled", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SdkTraffmonetizerRepositoryImpl implements TraffmonetizerRepository {
    public static final int $stable = 8;
    private final ConfigRepository configRepository;
    private final MutableStateFlow<ConnectionStatusEntity> connectionStatusFlow;
    private final MutableStateFlow<ProxyStatusEntity> proxyStatusFlow;
    private List<Job> sdkListenJobs;

    @Inject
    public SdkTraffmonetizerRepositoryImpl(ConfigRepository configRepository) {
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        this.configRepository = configRepository;
        this.connectionStatusFlow = StateFlowKt.MutableStateFlow(ConnectionStatusEntity.Disconnected.INSTANCE);
        this.proxyStatusFlow = StateFlowKt.MutableStateFlow(ProxyStatusEntity.Stopped.INSTANCE);
        this.sdkListenJobs = new ArrayList();
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public void init(Context context, String token, boolean wifiOnly, boolean proxyActive, boolean loggingActive) {
        Job launch$default;
        Job launch$default2;
        Job launch$default3;
        Intrinsics.checkNotNullParameter(context, "context");
        Timber.INSTANCE.d("init: " + this.configRepository.getDefaultConfig(), new Object[0]);
        if (token == null && this.configRepository.getDefaultConfig().getToken() == null) {
            throw new IllegalArgumentException("Token can't be null!");
        }
        TraffmonetizerSdk.INSTANCE.enableVerboseLogging(loggingActive);
        TraffmonetizerSdk traffmonetizerSdk = TraffmonetizerSdk.INSTANCE;
        if (token == null) {
            token = this.configRepository.getDefaultConfig().getToken();
            Intrinsics.checkNotNull(token);
        }
        traffmonetizerSdk.init(context, token, this.configRepository.getDefaultConfig().getAppVersion(), wifiOnly, proxyActive, this.configRepository.getDefaultConfig().getToken() != null);
        List<Job> list = this.sdkListenJobs;
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new SdkTraffmonetizerRepositoryImpl$init$1(this, null), 3, null);
        list.add(launch$default);
        List<Job> list2 = this.sdkListenJobs;
        launch$default2 = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new SdkTraffmonetizerRepositoryImpl$init$2(this, null), 3, null);
        list2.add(launch$default2);
        List<Job> list3 = this.sdkListenJobs;
        launch$default3 = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new SdkTraffmonetizerRepositoryImpl$init$3(null), 3, null);
        list3.add(launch$default3);
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public void start() {
        Timber.INSTANCE.d("start: ", new Object[0]);
        TraffmonetizerSdk.INSTANCE.start();
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public void stop() {
        Timber.INSTANCE.d("stop: ", new Object[0]);
        if (TraffmonetizerSdk.INSTANCE.isInitialized()) {
            TraffmonetizerSdk.INSTANCE.stop();
        }
        TraffmonetizerSdk.INSTANCE.deinit();
        Iterator<Job> it = this.sdkListenJobs.iterator();
        while (it.hasNext()) {
            Job.DefaultImpls.cancel$default(it.next(), (CancellationException) null, 1, (Object) null);
        }
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public Flow<ConnectionStatusEntity> getConnectionStatus() {
        return FlowKt.asStateFlow(this.connectionStatusFlow);
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public Flow<ProxyStatusEntity> getProxyStatus() {
        return FlowKt.asStateFlow(this.proxyStatusFlow);
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public void setProxyActive(boolean active) {
        TraffmonetizerSdk.INSTANCE.setServiceActive(active);
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public Object getBalance(Continuation<? super BalanceInfo> continuation) {
        return TraffmonetizerSdk.INSTANCE.getBalance(continuation);
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public Object getStats(Continuation<? super StatsInfo> continuation) {
        return TraffmonetizerSdk.INSTANCE.getStats(continuation);
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public void reconnect() {
        TraffmonetizerSdk.INSTANCE.reconnect();
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public void setWifiOnly(boolean value) {
        TraffmonetizerSdk.INSTANCE.setWifiOnly(value);
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public Object setDeviceName(String str, Continuation<? super Boolean> continuation) {
        return TraffmonetizerSdk.INSTANCE.setDeviceName(str, continuation);
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public Object getDeviceName(Continuation<? super String> continuation) {
        return TraffmonetizerSdk.INSTANCE.getDeviceName(continuation);
    }

    @Override // com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository
    public void setLoggingEnabled(boolean enabled) {
        TraffmonetizerSdk.INSTANCE.enableVerboseLogging(enabled);
    }
}
