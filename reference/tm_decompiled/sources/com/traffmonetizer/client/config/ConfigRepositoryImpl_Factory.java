package com.traffmonetizer.client.config;

import dagger.internal.Factory;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class ConfigRepositoryImpl_Factory implements Factory<ConfigRepositoryImpl> {
    private final Provider<AppConfig> defaultConfigProvider;

    private ConfigRepositoryImpl_Factory(Provider<AppConfig> provider) {
        this.defaultConfigProvider = provider;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ConfigRepositoryImpl get() {
        return newInstance(this.defaultConfigProvider.get());
    }

    public static ConfigRepositoryImpl_Factory create(Provider<AppConfig> provider) {
        return new ConfigRepositoryImpl_Factory(provider);
    }

    public static ConfigRepositoryImpl newInstance(AppConfig appConfig) {
        return new ConfigRepositoryImpl(appConfig);
    }
}
