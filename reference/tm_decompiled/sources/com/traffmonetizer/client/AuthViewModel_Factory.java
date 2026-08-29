package com.traffmonetizer.client;

import com.traffmonetizer.client.config.ConfigRepository;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
    private final Provider<ConfigRepository> configRepositoryProvider;
    private final Provider<PreferencesRepository> preferencesRepositoryProvider;

    private AuthViewModel_Factory(Provider<PreferencesRepository> provider, Provider<ConfigRepository> provider2) {
        this.preferencesRepositoryProvider = provider;
        this.configRepositoryProvider = provider2;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AuthViewModel get() {
        return newInstance(this.preferencesRepositoryProvider.get(), this.configRepositoryProvider.get());
    }

    public static AuthViewModel_Factory create(Provider<PreferencesRepository> provider, Provider<ConfigRepository> provider2) {
        return new AuthViewModel_Factory(provider, provider2);
    }

    public static AuthViewModel newInstance(PreferencesRepository preferencesRepository, ConfigRepository configRepository) {
        return new AuthViewModel(preferencesRepository, configRepository);
    }
}
