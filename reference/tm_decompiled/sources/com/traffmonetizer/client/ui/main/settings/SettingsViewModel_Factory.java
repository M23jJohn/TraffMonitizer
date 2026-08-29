package com.traffmonetizer.client.ui.main.settings;

import com.traffmonetizer.client.preferences.PreferencesRepository;
import com.traffmonetizer.client.service.TraffmonetizerServiceManager;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
    private final Provider<PreferencesRepository> preferencesRepositoryProvider;
    private final Provider<TraffmonetizerServiceManager> traffmonetizerServiceManagerProvider;

    private SettingsViewModel_Factory(Provider<PreferencesRepository> provider, Provider<TraffmonetizerServiceManager> provider2) {
        this.preferencesRepositoryProvider = provider;
        this.traffmonetizerServiceManagerProvider = provider2;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SettingsViewModel get() {
        return newInstance(this.preferencesRepositoryProvider.get(), this.traffmonetizerServiceManagerProvider.get());
    }

    public static SettingsViewModel_Factory create(Provider<PreferencesRepository> provider, Provider<TraffmonetizerServiceManager> provider2) {
        return new SettingsViewModel_Factory(provider, provider2);
    }

    public static SettingsViewModel newInstance(PreferencesRepository preferencesRepository, TraffmonetizerServiceManager traffmonetizerServiceManager) {
        return new SettingsViewModel(preferencesRepository, traffmonetizerServiceManager);
    }
}
