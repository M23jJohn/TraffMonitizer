package com.traffmonetizer.client.ui.main.home;

import com.traffmonetizer.client.service.TraffmonetizerServiceManager;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class StatsViewModel_Factory implements Factory<StatsViewModel> {
    private final Provider<TraffmonetizerServiceManager> traffmonetizerServiceManagerProvider;

    private StatsViewModel_Factory(Provider<TraffmonetizerServiceManager> provider) {
        this.traffmonetizerServiceManagerProvider = provider;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public StatsViewModel get() {
        return newInstance(this.traffmonetizerServiceManagerProvider.get());
    }

    public static StatsViewModel_Factory create(Provider<TraffmonetizerServiceManager> provider) {
        return new StatsViewModel_Factory(provider);
    }

    public static StatsViewModel newInstance(TraffmonetizerServiceManager traffmonetizerServiceManager) {
        return new StatsViewModel(traffmonetizerServiceManager);
    }
}
