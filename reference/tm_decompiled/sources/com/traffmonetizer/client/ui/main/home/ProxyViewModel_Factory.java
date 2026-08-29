package com.traffmonetizer.client.ui.main.home;

import com.traffmonetizer.client.preferences.PreferencesRepository;
import com.traffmonetizer.client.service.TraffmonetizerServiceManager;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class ProxyViewModel_Factory implements Factory<ProxyViewModel> {
    private final Provider<PreferencesRepository> preferencesRepositoryProvider;
    private final Provider<TraffmonetizerServiceManager> traffmonetizerServiceManagerProvider;

    private ProxyViewModel_Factory(Provider<TraffmonetizerServiceManager> provider, Provider<PreferencesRepository> provider2) {
        this.traffmonetizerServiceManagerProvider = provider;
        this.preferencesRepositoryProvider = provider2;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ProxyViewModel get() {
        return newInstance(this.traffmonetizerServiceManagerProvider.get(), this.preferencesRepositoryProvider.get());
    }

    public static ProxyViewModel_Factory create(Provider<TraffmonetizerServiceManager> provider, Provider<PreferencesRepository> provider2) {
        return new ProxyViewModel_Factory(provider, provider2);
    }

    public static ProxyViewModel newInstance(TraffmonetizerServiceManager traffmonetizerServiceManager, PreferencesRepository preferencesRepository) {
        return new ProxyViewModel(traffmonetizerServiceManager, preferencesRepository);
    }
}
