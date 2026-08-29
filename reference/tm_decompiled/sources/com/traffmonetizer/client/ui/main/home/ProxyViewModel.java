package com.traffmonetizer.client.ui.main.home;

import android.app.Activity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import com.traffmonetizer.client.service.TraffmonetizerServiceManager;
import com.traffmonetizer.client.traffmonetizer_repository.model.ProxyStatusEntity;
import com.traffmonetizer.client.utils.BatteryOptimisationUtils;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: ProxyViewModel.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0006\u0010\u0011\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Lcom/traffmonetizer/client/ui/main/home/ProxyViewModel;", "Landroidx/lifecycle/ViewModel;", "traffmonetizerServiceManager", "Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager;", "preferencesRepository", "Lcom/traffmonetizer/client/preferences/PreferencesRepository;", "<init>", "(Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager;Lcom/traffmonetizer/client/preferences/PreferencesRepository;)V", "traffProxyStatus", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity;", "getTraffProxyStatus", "()Lkotlinx/coroutines/flow/StateFlow;", "startProxy", "", "activity", "Landroid/app/Activity;", "stopProxy", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ProxyViewModel extends ViewModel {
    public static final int $stable = 8;
    private final PreferencesRepository preferencesRepository;
    private final StateFlow<ProxyStatusEntity> traffProxyStatus;
    private final TraffmonetizerServiceManager traffmonetizerServiceManager;

    @Inject
    public ProxyViewModel(TraffmonetizerServiceManager traffmonetizerServiceManager, PreferencesRepository preferencesRepository) {
        Intrinsics.checkNotNullParameter(traffmonetizerServiceManager, "traffmonetizerServiceManager");
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        this.traffmonetizerServiceManager = traffmonetizerServiceManager;
        this.preferencesRepository = preferencesRepository;
        this.traffProxyStatus = traffmonetizerServiceManager.getTraffProxyStatus();
    }

    public final StateFlow<ProxyStatusEntity> getTraffProxyStatus() {
        return this.traffProxyStatus;
    }

    public final void startProxy(Activity activity) {
        if (activity != null && !BatteryOptimisationUtils.INSTANCE.isIgnoringBatteryOptimizations(activity)) {
            BatteryOptimisationUtils.INSTANCE.requestIgnoreBatteryOptimization(activity);
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new ProxyViewModel$startProxy$2(this, null), 3, null);
    }

    public final void stopProxy() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new ProxyViewModel$stopProxy$1(this, null), 3, null);
    }
}
