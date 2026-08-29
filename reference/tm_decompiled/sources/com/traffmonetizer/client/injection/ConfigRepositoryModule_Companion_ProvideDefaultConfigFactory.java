package com.traffmonetizer.client.injection;

import com.traffmonetizer.client.config.AppConfig;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* loaded from: classes3.dex */
public final class ConfigRepositoryModule_Companion_ProvideDefaultConfigFactory implements Factory<AppConfig> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AppConfig get() {
        return provideDefaultConfig();
    }

    public static ConfigRepositoryModule_Companion_ProvideDefaultConfigFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static AppConfig provideDefaultConfig() {
        return (AppConfig) Preconditions.checkNotNullFromProvides(ConfigRepositoryModule.INSTANCE.provideDefaultConfig());
    }

    private static final class InstanceHolder {
        static final ConfigRepositoryModule_Companion_ProvideDefaultConfigFactory INSTANCE = new ConfigRepositoryModule_Companion_ProvideDefaultConfigFactory();

        private InstanceHolder() {
        }
    }
}
