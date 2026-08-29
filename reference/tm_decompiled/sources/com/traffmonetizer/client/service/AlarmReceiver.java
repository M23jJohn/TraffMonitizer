package com.traffmonetizer.client.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import timber.log.Timber;

/* compiled from: AlarmReceiver.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/traffmonetizer/client/service/AlarmReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "preferencesRepository", "Lcom/traffmonetizer/client/preferences/PreferencesRepository;", "getPreferencesRepository", "()Lcom/traffmonetizer/client/preferences/PreferencesRepository;", "setPreferencesRepository", "(Lcom/traffmonetizer/client/preferences/PreferencesRepository;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "startForegroundService", "Companion", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes3.dex */
public final class AlarmReceiver extends Hilt_AlarmReceiver {
    private static final int SET_RESTART_SERVICE_ALARM = 100;

    @Inject
    public PreferencesRepository preferencesRepository;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

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

    /* compiled from: AlarmReceiver.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/traffmonetizer/client/service/AlarmReceiver$Companion;", "", "<init>", "()V", "SET_RESTART_SERVICE_ALARM", "", "setRestartAlarm", "", "context", "Landroid/content/Context;", "millis", "cancelRestartAlarm", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void setRestartAlarm(Context context, int millis) {
            Intrinsics.checkNotNullParameter(context, "context");
            long currentTimeMillis = System.currentTimeMillis() + millis;
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 100, new Intent(context, (Class<?>) AlarmReceiver.class), Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728);
            Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
            ((AlarmManager) systemService).set(0, currentTimeMillis, broadcast);
        }

        public final void cancelRestartAlarm(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 100, new Intent(context, (Class<?>) AlarmReceiver.class), Build.VERSION.SDK_INT >= 31 ? 301989888 : 268435456);
            Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
            ((AlarmManager) systemService).cancel(broadcast);
        }
    }

    @Override // com.traffmonetizer.client.service.Hilt_AlarmReceiver, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (context == null) {
            return;
        }
        Timber.INSTANCE.d("onReceive: ", new Object[0]);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AlarmReceiver$onReceive$1(this, context, goAsync(), null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startForegroundService(Context context) {
        try {
            ContextCompat.startForegroundService(context, new Intent(context, (Class<?>) TraffmonetizerForegroundService.class));
        } catch (Throwable th) {
            Timber.INSTANCE.e(th, "startForegroundService: error restarting service", new Object[0]);
        }
    }
}
