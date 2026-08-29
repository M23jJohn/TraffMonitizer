package com.traffmonetizer.client.service;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.autofill.HintConstants;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import com.traffmonetizer.client.service.TraffmonetizerForegroundService;
import com.traffmonetizer.client.traffmonetizer_repository.model.ConnectionStatusEntity;
import com.traffmonetizer.client.traffmonetizer_repository.model.ProxyStatusEntity;
import com.traffmonetizer.sdk.model.BalanceInfo;
import com.traffmonetizer.sdk.model.StatsInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import timber.log.Timber;

/* compiled from: TraffmonetizerServiceManager.kt */
@Metadata(d1 = {"\u0000e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001bJ\u0006\u0010\u001d\u001a\u00020\u001bJ\u0006\u0010\u001e\u001a\u00020\u001bJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010 H\u0086@¢\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u0004\u0018\u00010#H\u0086@¢\u0006\u0002\u0010!J\u0006\u0010$\u001a\u00020\u001bJ\u000e\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\u0019J\u0010\u0010'\u001a\u0004\u0018\u00010(H\u0086@¢\u0006\u0002\u0010!J\u0018\u0010)\u001a\u0004\u0018\u00010\u00192\u0006\u0010*\u001a\u00020(H\u0086@¢\u0006\u0002\u0010+R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013¨\u0006,"}, d2 = {"Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager;", "", "context", "Landroid/content/Context;", "preferencesRepository", "Lcom/traffmonetizer/client/preferences/PreferencesRepository;", "<init>", "(Landroid/content/Context;Lcom/traffmonetizer/client/preferences/PreferencesRepository;)V", "traffmonetizerService", "Lcom/traffmonetizer/client/service/TraffmonetizerService;", "serviceConnection", "com/traffmonetizer/client/service/TraffmonetizerServiceManager$serviceConnection$1", "Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager$serviceConnection$1;", "traffConnectionStatusFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "traffConnectionStatus", "Lkotlinx/coroutines/flow/StateFlow;", "getTraffConnectionStatus", "()Lkotlinx/coroutines/flow/StateFlow;", "traffProxyStatusFlow", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity;", "traffProxyStatus", "getTraffProxyStatus", "isServiceRunning", "", "startAndBindService", "", "unbindService", "startProxy", "stopProxy", "getBalance", "Lcom/traffmonetizer/sdk/model/BalanceInfo;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStats", "Lcom/traffmonetizer/sdk/model/StatsInfo;", "reconnect", "setWifiOnly", "value", "getDeviceName", "", "setDeviceName", HintConstants.AUTOFILL_HINT_NAME, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TraffmonetizerServiceManager {
    public static final int $stable = 8;
    private final Context context;
    private final PreferencesRepository preferencesRepository;
    private final TraffmonetizerServiceManager$serviceConnection$1 serviceConnection;
    private final StateFlow<ConnectionStatusEntity> traffConnectionStatus;
    private final MutableStateFlow<ConnectionStatusEntity> traffConnectionStatusFlow;
    private final StateFlow<ProxyStatusEntity> traffProxyStatus;
    private final MutableStateFlow<ProxyStatusEntity> traffProxyStatusFlow;
    private TraffmonetizerService traffmonetizerService;

    /* JADX WARN: Type inference failed for: r2v1, types: [com.traffmonetizer.client.service.TraffmonetizerServiceManager$serviceConnection$1] */
    public TraffmonetizerServiceManager(Context context, PreferencesRepository preferencesRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        this.context = context;
        this.preferencesRepository = preferencesRepository;
        this.serviceConnection = new ServiceConnection() { // from class: com.traffmonetizer.client.service.TraffmonetizerServiceManager$serviceConnection$1
            private final List<Job> listenJobs = new ArrayList();

            public final List<Job> getListenJobs() {
                return this.listenJobs;
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName component, IBinder service) {
                Job launch$default;
                Job launch$default2;
                Timber.INSTANCE.d("onServiceConnected", new Object[0]);
                Intrinsics.checkNotNull(service, "null cannot be cast to non-null type com.traffmonetizer.client.service.TraffmonetizerForegroundService.LocalBinder");
                TraffmonetizerServiceManager.this.traffmonetizerService = ((TraffmonetizerForegroundService.LocalBinder) service).getService();
                List<Job> list = this.listenJobs;
                launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new TraffmonetizerServiceManager$serviceConnection$1$onServiceConnected$1(TraffmonetizerServiceManager.this, null), 3, null);
                list.add(launch$default);
                List<Job> list2 = this.listenJobs;
                launch$default2 = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new TraffmonetizerServiceManager$serviceConnection$1$onServiceConnected$2(TraffmonetizerServiceManager.this, null), 3, null);
                list2.add(launch$default2);
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName component) {
                Timber.INSTANCE.d("onServiceDisconnected", new Object[0]);
                Iterator<Job> it = this.listenJobs.iterator();
                while (it.hasNext()) {
                    Job.DefaultImpls.cancel$default(it.next(), (CancellationException) null, 1, (Object) null);
                }
                TraffmonetizerServiceManager.this.traffmonetizerService = null;
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName name) {
                Timber.INSTANCE.d("onBindingDied", new Object[0]);
                super.onBindingDied(name);
            }
        };
        MutableStateFlow<ConnectionStatusEntity> MutableStateFlow = StateFlowKt.MutableStateFlow(ConnectionStatusEntity.Disconnected.INSTANCE);
        this.traffConnectionStatusFlow = MutableStateFlow;
        this.traffConnectionStatus = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow<ProxyStatusEntity> MutableStateFlow2 = StateFlowKt.MutableStateFlow(ProxyStatusEntity.Stopped.INSTANCE);
        this.traffProxyStatusFlow = MutableStateFlow2;
        this.traffProxyStatus = FlowKt.asStateFlow(MutableStateFlow2);
    }

    public final StateFlow<ConnectionStatusEntity> getTraffConnectionStatus() {
        return this.traffConnectionStatus;
    }

    public final StateFlow<ProxyStatusEntity> getTraffProxyStatus() {
        return this.traffProxyStatus;
    }

    public final boolean isServiceRunning() {
        Object systemService = this.context.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        Iterator<ActivityManager.RunningServiceInfo> it = ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE).iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(TraffmonetizerForegroundService.class.getName(), it.next().service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public final void startAndBindService() {
        if (this.traffmonetizerService != null) {
            Timber.INSTANCE.d("startAndBindService: already bound!", new Object[0]);
        }
        boolean isServiceRunning = isServiceRunning();
        Timber.INSTANCE.d("startAndBindService: " + hashCode() + " serviceRunning=" + isServiceRunning, new Object[0]);
        if (!isServiceRunning) {
            try {
                this.context.startService(new Intent(this.context, (Class<?>) TraffmonetizerForegroundService.class));
            } catch (Exception e) {
                Timber.INSTANCE.e(e, "startAndBindService: error starting service", new Object[0]);
            }
        }
        this.context.bindService(new Intent(this.context, (Class<?>) TraffmonetizerForegroundService.class), this.serviceConnection, 1);
    }

    public final void unbindService() {
        Timber.INSTANCE.d("unbindService: " + hashCode() + " service=" + this.traffmonetizerService, new Object[0]);
        if (this.traffmonetizerService != null) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new TraffmonetizerServiceManager$unbindService$1(this, null), 3, null);
        }
    }

    public final void startProxy() {
        TraffmonetizerService traffmonetizerService = this.traffmonetizerService;
        if (traffmonetizerService != null) {
            traffmonetizerService.setProxyActive(true);
        }
    }

    public final void stopProxy() {
        TraffmonetizerService traffmonetizerService = this.traffmonetizerService;
        if (traffmonetizerService != null) {
            traffmonetizerService.setProxyActive(false);
        }
    }

    public final Object getBalance(Continuation<? super BalanceInfo> continuation) {
        TraffmonetizerService traffmonetizerService = this.traffmonetizerService;
        if (traffmonetizerService == null) {
            return null;
        }
        Object balance = traffmonetizerService.getBalance(continuation);
        return balance == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? balance : (BalanceInfo) balance;
    }

    public final Object getStats(Continuation<? super StatsInfo> continuation) {
        TraffmonetizerService traffmonetizerService = this.traffmonetizerService;
        if (traffmonetizerService == null) {
            return null;
        }
        Object stats = traffmonetizerService.getStats(continuation);
        return stats == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? stats : (StatsInfo) stats;
    }

    public final void reconnect() {
        TraffmonetizerService traffmonetizerService = this.traffmonetizerService;
        if (traffmonetizerService != null) {
            traffmonetizerService.reconnect();
        }
    }

    public final void setWifiOnly(boolean value) {
        TraffmonetizerService traffmonetizerService = this.traffmonetizerService;
        if (traffmonetizerService != null) {
            traffmonetizerService.setWifiOnly(value);
        }
    }

    public final Object getDeviceName(Continuation<? super String> continuation) {
        TraffmonetizerService traffmonetizerService = this.traffmonetizerService;
        if (traffmonetizerService == null) {
            return null;
        }
        Object deviceName = traffmonetizerService.getDeviceName(continuation);
        return deviceName == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? deviceName : (String) deviceName;
    }

    public final Object setDeviceName(String str, Continuation<? super Boolean> continuation) {
        TraffmonetizerService traffmonetizerService = this.traffmonetizerService;
        if (traffmonetizerService == null) {
            return null;
        }
        Object deviceName = traffmonetizerService.setDeviceName(str, continuation);
        return deviceName == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? deviceName : (Boolean) deviceName;
    }
}
