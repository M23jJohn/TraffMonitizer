package com.traffmonetizer.client.service;

import android.content.Context;
import android.content.Intent;
import androidx.core.content.ContextCompat;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import timber.log.Timber;

/* compiled from: RebootReceiver.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/traffmonetizer/client/service/RebootReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "preferencesRepository", "Lcom/traffmonetizer/client/preferences/PreferencesRepository;", "getPreferencesRepository", "()Lcom/traffmonetizer/client/preferences/PreferencesRepository;", "setPreferencesRepository", "(Lcom/traffmonetizer/client/preferences/PreferencesRepository;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "startForegroundService", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes3.dex */
public final class RebootReceiver extends Hilt_RebootReceiver {
    public static final int $stable = 8;

    @Inject
    public PreferencesRepository preferencesRepository;

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

    @Override // com.traffmonetizer.client.service.Hilt_RebootReceiver, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (context == null || intent == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new RebootReceiver$onReceive$1(this, intent, context, goAsync(), null), 3, null);
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
