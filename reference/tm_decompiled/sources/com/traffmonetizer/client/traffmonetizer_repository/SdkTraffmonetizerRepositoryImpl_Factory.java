package com.traffmonetizer.client.traffmonetizer_repository;

import com.traffmonetizer.client.config.ConfigRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class SdkTraffmonetizerRepositoryImpl_Factory implements Factory<SdkTraffmonetizerRepositoryImpl> {
    private final Provider<ConfigRepository> configRepositoryProvider;

    private SdkTraffmonetizerRepositoryImpl_Factory(Provider<ConfigRepository> provider) {
        this.configRepositoryProvider = provider;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SdkTraffmonetizerRepositoryImpl get() {
        return newInstance(this.configRepositoryProvider.get());
    }

    public static SdkTraffmonetizerRepositoryImpl_Factory create(Provider<ConfigRepository> provider) {
        return new SdkTraffmonetizerRepositoryImpl_Factory(provider);
    }

    public static SdkTraffmonetizerRepositoryImpl newInstance(ConfigRepository configRepository) {
        return new SdkTraffmonetizerRepositoryImpl(configRepository);
    }
}
