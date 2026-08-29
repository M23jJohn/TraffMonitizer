package com.traffmonetizer.client.service;

import androidx.autofill.HintConstants;
import com.traffmonetizer.client.traffmonetizer_repository.model.ConnectionStatusEntity;
import com.traffmonetizer.client.traffmonetizer_repository.model.ProxyStatusEntity;
import com.traffmonetizer.sdk.model.BalanceInfo;
import com.traffmonetizer.sdk.model.StatsInfo;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* compiled from: TraffmonetizerService.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\u000e\u0010\u000b\u001a\u00020\fH¦@¢\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\u000fH¦@¢\u0006\u0002\u0010\rJ\b\u0010\u0010\u001a\u00020\bH&J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\nH&J\u0016\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H¦@¢\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u0015H¦@¢\u0006\u0002\u0010\r¨\u0006\u0018À\u0006\u0003"}, d2 = {"Lcom/traffmonetizer/client/service/TraffmonetizerService;", "", "getConnectionStatus", "Lkotlinx/coroutines/flow/Flow;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "getProxyStatus", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity;", "setProxyActive", "", "active", "", "getBalance", "Lcom/traffmonetizer/sdk/model/BalanceInfo;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStats", "Lcom/traffmonetizer/sdk/model/StatsInfo;", "reconnect", "setWifiOnly", "value", "setDeviceName", HintConstants.AUTOFILL_HINT_NAME, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDeviceName", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface TraffmonetizerService {
    Object getBalance(Continuation<? super BalanceInfo> continuation);

    Flow<ConnectionStatusEntity> getConnectionStatus();

    Object getDeviceName(Continuation<? super String> continuation);

    Flow<ProxyStatusEntity> getProxyStatus();

    Object getStats(Continuation<? super StatsInfo> continuation);

    void reconnect();

    Object setDeviceName(String str, Continuation<? super Boolean> continuation);

    void setProxyActive(boolean active);

    void setWifiOnly(boolean value);
}
