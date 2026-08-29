package com.traffmonetizer.client.service;

import com.traffmonetizer.client.preferences.PreferencesRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class ServiceViewModel_Factory implements Factory<ServiceViewModel> {
    private final Provider<PreferencesRepository> preferencesRepositoryProvider;
    private final Provider<TraffmonetizerServiceManager> traffmonetizerServiceManagerProvider;

    private ServiceViewModel_Factory(Provider<TraffmonetizerServiceManager> provider, Provider<PreferencesRepository> provider2) {
        this.traffmonetizerServiceManagerProvider = provider;
        this.preferencesRepositoryProvider = provider2;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ServiceViewModel get() {
        return newInstance(this.traffmonetizerServiceManagerProvider.get(), this.preferencesRepositoryProvider.get());
    }

    public static ServiceViewModel_Factory create(Provider<TraffmonetizerServiceManager> provider, Provider<PreferencesRepository> provider2) {
        return new ServiceViewModel_Factory(provider, provider2);
    }

    public static ServiceViewModel newInstance(TraffmonetizerServiceManager traffmonetizerServiceManager, PreferencesRepository preferencesRepository) {
        return new ServiceViewModel(traffmonetizerServiceManager, preferencesRepository);
    }
}
