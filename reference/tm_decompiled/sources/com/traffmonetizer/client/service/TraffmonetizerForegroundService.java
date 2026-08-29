package com.traffmonetizer.client.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import com.traffmonetizer.client.R;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import com.traffmonetizer.client.preferences.model.AppPreferences;
import com.traffmonetizer.client.service.AlarmReceiver;
import com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository;
import com.traffmonetizer.client.traffmonetizer_repository.model.ConnectionStatusEntity;
import com.traffmonetizer.client.traffmonetizer_repository.model.ProxyStatusEntity;
import com.traffmonetizer.sdk.model.BalanceInfo;
import com.traffmonetizer.sdk.model.StatsInfo;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import timber.log.Timber;

/* compiled from: TraffmonetizerForegroundService.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 E2\u00020\u00012\u00020\u0002:\u0002EFB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0012\u0010\u001e\u001a\u00020\u00152\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0012\u0010\u001f\u001a\u00020 2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010!\u001a\u00020 H\u0016J\"\u0010\"\u001a\u00020#2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#H\u0016J\b\u0010&\u001a\u00020 H\u0016J\u0018\u0010'\u001a\u00020 2\u0006\u0010%\u001a\u00020#2\u0006\u0010(\u001a\u00020#H\u0016J\u0012\u0010)\u001a\u00020 2\b\u0010*\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010+\u001a\u00020 H\u0002J\b\u0010,\u001a\u00020 H\u0002J\b\u0010-\u001a\u00020 H\u0002J\b\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u00020 H\u0003J\u000e\u00101\u001a\b\u0012\u0004\u0012\u00020302H\u0016J\u000e\u00104\u001a\b\u0012\u0004\u0012\u00020502H\u0016J\u0010\u00106\u001a\u00020 2\u0006\u00107\u001a\u00020\u0015H\u0016J\u000e\u00108\u001a\u000209H\u0096@¢\u0006\u0002\u0010:J\u000e\u0010;\u001a\u00020<H\u0096@¢\u0006\u0002\u0010:J\b\u0010=\u001a\u00020 H\u0016J\u0010\u0010>\u001a\u00020 2\u0006\u0010?\u001a\u00020\u0015H\u0016J\u0016\u0010@\u001a\u00020\u00152\u0006\u0010A\u001a\u00020BH\u0096@¢\u0006\u0002\u0010CJ\u000e\u0010D\u001a\u00020BH\u0096@¢\u0006\u0002\u0010:R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00060\u0012R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0017X\u0082.¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/traffmonetizer/client/service/TraffmonetizerForegroundService;", "Landroidx/lifecycle/LifecycleService;", "Lcom/traffmonetizer/client/service/TraffmonetizerService;", "<init>", "()V", "traffmonetizerRepository", "Lcom/traffmonetizer/client/traffmonetizer_repository/TraffmonetizerRepository;", "getTraffmonetizerRepository", "()Lcom/traffmonetizer/client/traffmonetizer_repository/TraffmonetizerRepository;", "setTraffmonetizerRepository", "(Lcom/traffmonetizer/client/traffmonetizer_repository/TraffmonetizerRepository;)V", "preferencesRepository", "Lcom/traffmonetizer/client/preferences/PreferencesRepository;", "getPreferencesRepository", "()Lcom/traffmonetizer/client/preferences/PreferencesRepository;", "setPreferencesRepository", "(Lcom/traffmonetizer/client/preferences/PreferencesRepository;)V", "binder", "Lcom/traffmonetizer/client/service/TraffmonetizerForegroundService$LocalBinder;", "foregroundStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "isForeground", "Lkotlinx/coroutines/flow/StateFlow;", "appPreferences", "Lcom/traffmonetizer/client/preferences/model/AppPreferences;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onUnbind", "onRebind", "", "onCreate", "onStartCommand", "", "flags", "startId", "onDestroy", "onTimeout", "fgsType", "onTaskRemoved", "rootIntent", "promoteToForeground", "denoteToBackground", "stop", "buildNotification", "Landroid/app/Notification;", "createNotificationsChannel", "getConnectionStatus", "Lkotlinx/coroutines/flow/Flow;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "getProxyStatus", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity;", "setProxyActive", "active", "getBalance", "Lcom/traffmonetizer/sdk/model/BalanceInfo;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStats", "Lcom/traffmonetizer/sdk/model/StatsInfo;", "reconnect", "setWifiOnly", "value", "setDeviceName", HintConstants.AUTOFILL_HINT_NAME, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDeviceName", "Companion", "LocalBinder", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes3.dex */
public final class TraffmonetizerForegroundService extends Hilt_TraffmonetizerForegroundService implements TraffmonetizerService {
    public static final String CHANNEL_DESCRIPTION = "Traffmonetizer service";
    public static final String CHANNEL_ID = "traffmonetizer_foreground";
    public static final String CHANNEL_NAME = "Traffmonetizer";
    public static final int SERVICE_ID = 1000;
    private StateFlow<AppPreferences> appPreferences;
    private final LocalBinder binder = new LocalBinder();
    private final MutableStateFlow<Boolean> foregroundStateFlow;
    private final StateFlow<Boolean> isForeground;

    @Inject
    public PreferencesRepository preferencesRepository;

    @Inject
    public TraffmonetizerRepository traffmonetizerRepository;
    public static final int $stable = 8;

    public TraffmonetizerForegroundService() {
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(false);
        this.foregroundStateFlow = MutableStateFlow;
        this.isForeground = FlowKt.asStateFlow(MutableStateFlow);
    }

    public final TraffmonetizerRepository getTraffmonetizerRepository() {
        TraffmonetizerRepository traffmonetizerRepository = this.traffmonetizerRepository;
        if (traffmonetizerRepository != null) {
            return traffmonetizerRepository;
        }
        Intrinsics.throwUninitializedPropertyAccessException("traffmonetizerRepository");
        return null;
    }

    public final void setTraffmonetizerRepository(TraffmonetizerRepository traffmonetizerRepository) {
        Intrinsics.checkNotNullParameter(traffmonetizerRepository, "<set-?>");
        this.traffmonetizerRepository = traffmonetizerRepository;
    }

    public final PreferencesRepository getPreferencesRepository() {
        PreferencesRepository preferencesRepository = this.preferencesRepository;
        if (preferencesRepository != null) {
            return preferencesRepository;
        }
        Intrinsics.throwUninitializedPropertyAccessException("preferencesRepository");
        return null;
    }

    public final void setPreferencesRepository(PreferencesRepository preferencesRepository) {
        Intrinsics.checkNotNullParameter(preferencesRepository, "<set-?>");
        this.preferencesRepository = preferencesRepository;
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onBind(intent);
        Timber.Companion companion = Timber.INSTANCE;
        StringBuilder sb = new StringBuilder("onBind: ");
        sb.append(hashCode());
        sb.append(", proxyActive=");
        StateFlow<AppPreferences> stateFlow = this.appPreferences;
        if (stateFlow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appPreferences");
            stateFlow = null;
        }
        sb.append(stateFlow.getValue().getProxyActive());
        sb.append(", foreground=");
        sb.append(this.isForeground.getValue().booleanValue());
        companion.d(sb.toString(), new Object[0]);
        return this.binder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Timber.Companion companion = Timber.INSTANCE;
        StringBuilder sb = new StringBuilder("onUnbind: ");
        sb.append(hashCode());
        sb.append(", proxyActive=");
        StateFlow<AppPreferences> stateFlow = this.appPreferences;
        if (stateFlow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appPreferences");
            stateFlow = null;
        }
        sb.append(stateFlow.getValue().getProxyActive());
        sb.append(", foreground=");
        sb.append(this.isForeground.getValue().booleanValue());
        companion.d(sb.toString(), new Object[0]);
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        Timber.Companion companion = Timber.INSTANCE;
        StringBuilder sb = new StringBuilder("onRebind: ");
        sb.append(hashCode());
        sb.append(", proxyActive=");
        StateFlow<AppPreferences> stateFlow = this.appPreferences;
        if (stateFlow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appPreferences");
            stateFlow = null;
        }
        sb.append(stateFlow.getValue().getProxyActive());
        sb.append(", foreground=");
        sb.append(this.isForeground.getValue().booleanValue());
        companion.d(sb.toString(), new Object[0]);
        super.onRebind(intent);
    }

    /* compiled from: TraffmonetizerForegroundService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/traffmonetizer/client/service/TraffmonetizerForegroundService$LocalBinder;", "Landroid/os/Binder;", "<init>", "(Lcom/traffmonetizer/client/service/TraffmonetizerForegroundService;)V", "getService", "Lcom/traffmonetizer/client/service/TraffmonetizerService;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public final TraffmonetizerService getService() {
            return TraffmonetizerForegroundService.this;
        }
    }

    @Override // com.traffmonetizer.client.service.Hilt_TraffmonetizerForegroundService, androidx.lifecycle.LifecycleService, android.app.Service
    public void onCreate() {
        Boolean value;
        super.onCreate();
        Timber.INSTANCE.d("onCreate: " + hashCode(), new Object[0]);
        MutableStateFlow<Boolean> mutableStateFlow = this.foregroundStateFlow;
        do {
            value = mutableStateFlow.getValue();
            value.booleanValue();
        } while (!mutableStateFlow.compareAndSet(value, false));
        this.appPreferences = FlowKt.stateIn(getPreferencesRepository().getAppPreferences(), LifecycleOwnerKt.getLifecycleScope(this), SharingStarted.INSTANCE.getEagerly(), new AppPreferences(null, false, false, false, 15, null));
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Timber.INSTANCE.d("onStartCommand: " + hashCode(), new Object[0]);
        TraffmonetizerForegroundService traffmonetizerForegroundService = this;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(traffmonetizerForegroundService), null, null, new TraffmonetizerForegroundService$onStartCommand$1(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(traffmonetizerForegroundService), null, null, new TraffmonetizerForegroundService$onStartCommand$2(this, null), 3, null);
        return 1;
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Timber.INSTANCE.d("onDestroy: " + hashCode(), new Object[0]);
        StateFlow<AppPreferences> stateFlow = this.appPreferences;
        if (stateFlow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appPreferences");
            stateFlow = null;
        }
        if (stateFlow.getValue().getProxyActive()) {
            AlarmReceiver.Companion companion = AlarmReceiver.INSTANCE;
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            companion.setRestartAlarm(applicationContext, 5000);
        } else {
            AlarmReceiver.Companion companion2 = AlarmReceiver.INSTANCE;
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
            companion2.cancelRestartAlarm(applicationContext2);
        }
        stop();
    }

    public void onTimeout(int startId, int fgsType) {
        super.onTimeout(startId, fgsType);
        stop();
        Timber.INSTANCE.e("onTimeout: Stopped by timeout", new Object[0]);
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Timber.INSTANCE.d("onTaskRemoved: ", new Object[0]);
        StateFlow<AppPreferences> stateFlow = this.appPreferences;
        if (stateFlow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appPreferences");
            stateFlow = null;
        }
        if (stateFlow.getValue().getProxyActive()) {
            AlarmReceiver.Companion companion = AlarmReceiver.INSTANCE;
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            companion.setRestartAlarm(applicationContext, 5000);
        } else {
            AlarmReceiver.Companion companion2 = AlarmReceiver.INSTANCE;
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
            companion2.cancelRestartAlarm(applicationContext2);
        }
        stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void promoteToForeground() {
        Boolean value;
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                createNotificationsChannel();
            }
            Notification buildNotification = buildNotification();
            if (Build.VERSION.SDK_INT >= 29) {
                startForeground(1000, buildNotification, 16);
            } else {
                startForeground(1000, buildNotification);
            }
            MutableStateFlow<Boolean> mutableStateFlow = this.foregroundStateFlow;
            do {
                value = mutableStateFlow.getValue();
                value.booleanValue();
            } while (!mutableStateFlow.compareAndSet(value, true));
        } catch (Exception e) {
            Timber.INSTANCE.d(e, "promoteToForeground: ", new Object[0]);
        }
    }

    private final void denoteToBackground() {
        Boolean value;
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                stopForeground(1);
            } else {
                stopForeground(true);
            }
            MutableStateFlow<Boolean> mutableStateFlow = this.foregroundStateFlow;
            do {
                value = mutableStateFlow.getValue();
                value.booleanValue();
            } while (!mutableStateFlow.compareAndSet(value, false));
        } catch (Exception e) {
            Timber.INSTANCE.d(e, "denoteToBackground: ", new Object[0]);
        }
    }

    private final void stop() {
        if (this.isForeground.getValue().booleanValue()) {
            denoteToBackground();
        }
        getTraffmonetizerRepository().stop();
        stopSelf();
    }

    private final Notification buildNotification() {
        if (Build.VERSION.SDK_INT >= 26) {
            TraffmonetizerForegroundService$$ExternalSyntheticApiModelOutline0.m();
            Notification.Builder m = TraffmonetizerForegroundService$$ExternalSyntheticApiModelOutline0.m(this, CHANNEL_ID);
            m.setSmallIcon(R.drawable.ic_notification);
            if (Build.VERSION.SDK_INT >= 31) {
                m.setForegroundServiceBehavior(1);
            }
            Notification build = m.build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            return build;
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_notification);
        Notification build2 = builder.build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        return build2;
    }

    private final void createNotificationsChannel() {
        NotificationChannel notificationChannel;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        notificationChannel = notificationManager.getNotificationChannel(CHANNEL_ID);
        if (notificationChannel == null) {
            TraffmonetizerForegroundService$$ExternalSyntheticApiModelOutline0.m$1();
            NotificationChannel m = TraffmonetizerForegroundService$$ExternalSyntheticApiModelOutline0.m(CHANNEL_ID, CHANNEL_NAME, 2);
            m.setDescription(CHANNEL_DESCRIPTION);
            m.setShowBadge(true);
            notificationManager.createNotificationChannel(m);
        }
    }

    @Override // com.traffmonetizer.client.service.TraffmonetizerService
    public Flow<ConnectionStatusEntity> getConnectionStatus() {
        return getTraffmonetizerRepository().getConnectionStatus();
    }

    @Override // com.traffmonetizer.client.service.TraffmonetizerService
    public Flow<ProxyStatusEntity> getProxyStatus() {
        return getTraffmonetizerRepository().getProxyStatus();
    }

    @Override // com.traffmonetizer.client.service.TraffmonetizerService
    public void setProxyActive(boolean active) {
        Timber.INSTANCE.d("setProxyActive: isForeground=" + this.isForeground.getValue().booleanValue(), new Object[0]);
        if (active) {
            if (!this.isForeground.getValue().booleanValue()) {
                promoteToForeground();
            }
        } else if (this.isForeground.getValue().booleanValue()) {
            denoteToBackground();
        }
        getTraffmonetizerRepository().setProxyActive(active);
    }

    @Override // com.traffmonetizer.client.service.TraffmonetizerService
    public Object getBalance(Continuation<? super BalanceInfo> continuation) {
        return getTraffmonetizerRepository().getBalance(continuation);
    }

    @Override // com.traffmonetizer.client.service.TraffmonetizerService
    public Object getStats(Continuation<? super StatsInfo> continuation) {
        return getTraffmonetizerRepository().getStats(continuation);
    }

    @Override // com.traffmonetizer.client.service.TraffmonetizerService
    public void reconnect() {
        getTraffmonetizerRepository().reconnect();
    }

    @Override // com.traffmonetizer.client.service.TraffmonetizerService
    public void setWifiOnly(boolean value) {
        getTraffmonetizerRepository().setWifiOnly(value);
    }

    @Override // com.traffmonetizer.client.service.TraffmonetizerService
    public Object setDeviceName(String str, Continuation<? super Boolean> continuation) {
        return getTraffmonetizerRepository().setDeviceName(str, continuation);
    }

    @Override // com.traffmonetizer.client.service.TraffmonetizerService
    public Object getDeviceName(Continuation<? super String> continuation) {
        return getTraffmonetizerRepository().getDeviceName(continuation);
    }
}
