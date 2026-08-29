package com.traffmonetizer.client.traffmonetizer_repository;

import android.content.Context;
import androidx.autofill.HintConstants;
import com.traffmonetizer.client.traffmonetizer_repository.model.ConnectionStatusEntity;
import com.traffmonetizer.client.traffmonetizer_repository.model.ProxyStatusEntity;
import com.traffmonetizer.sdk.model.BalanceInfo;
import com.traffmonetizer.sdk.model.StatsInfo;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* compiled from: TraffmonetizerRepository.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH&J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000fH&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\tH&J\u000e\u0010\u0015\u001a\u00020\u0016H¦@¢\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\u0019H¦@¢\u0006\u0002\u0010\u0017J\b\u0010\u001a\u001a\u00020\u0003H&J\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\tH&J\u0016\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u0007H¦@¢\u0006\u0002\u0010\u001fJ\u000e\u0010 \u001a\u00020\u0007H¦@¢\u0006\u0002\u0010\u0017J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\tH&¨\u0006#À\u0006\u0003"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/TraffmonetizerRepository;", "", "init", "", "context", "Landroid/content/Context;", "token", "", "wifiOnly", "", "proxyActive", "loggingActive", "start", "stop", "getConnectionStatus", "Lkotlinx/coroutines/flow/Flow;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "getProxyStatus", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity;", "setProxyActive", "active", "getBalance", "Lcom/traffmonetizer/sdk/model/BalanceInfo;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStats", "Lcom/traffmonetizer/sdk/model/StatsInfo;", "reconnect", "setWifiOnly", "value", "setDeviceName", HintConstants.AUTOFILL_HINT_NAME, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDeviceName", "setLoggingEnabled", "enabled", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface TraffmonetizerRepository {
    Object getBalance(Continuation<? super BalanceInfo> continuation);

    Flow<ConnectionStatusEntity> getConnectionStatus();

    Object getDeviceName(Continuation<? super String> continuation);

    Flow<ProxyStatusEntity> getProxyStatus();

    Object getStats(Continuation<? super StatsInfo> continuation);

    void init(Context context, String token, boolean wifiOnly, boolean proxyActive, boolean loggingActive);

    void reconnect();

    Object setDeviceName(String str, Continuation<? super Boolean> continuation);

    void setLoggingEnabled(boolean enabled);

    void setProxyActive(boolean active);

    void setWifiOnly(boolean value);

    void start();

    void stop();
}
