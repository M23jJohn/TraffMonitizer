package com.traffmonetizer.client.injection;

import android.content.Context;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import com.traffmonetizer.client.service.TraffmonetizerServiceManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class TraffmonetizerServiceManagerModule_ProvideServiceManagerFactory implements Factory<TraffmonetizerServiceManager> {
    private final Provider<Context> contextProvider;
    private final Provider<PreferencesRepository> preferencesRepositoryProvider;

    private TraffmonetizerServiceManagerModule_ProvideServiceManagerFactory(Provider<Context> provider, Provider<PreferencesRepository> provider2) {
        this.contextProvider = provider;
        this.preferencesRepositoryProvider = provider2;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public TraffmonetizerServiceManager get() {
        return provideServiceManager(this.contextProvider.get(), this.preferencesRepositoryProvider.get());
    }

    public static TraffmonetizerServiceManagerModule_ProvideServiceManagerFactory create(Provider<Context> provider, Provider<PreferencesRepository> provider2) {
        return new TraffmonetizerServiceManagerModule_ProvideServiceManagerFactory(provider, provider2);
    }

    public static TraffmonetizerServiceManager provideServiceManager(Context context, PreferencesRepository preferencesRepository) {
        return (TraffmonetizerServiceManager) Preconditions.checkNotNullFromProvides(TraffmonetizerServiceManagerModule.INSTANCE.provideServiceManager(context, preferencesRepository));
    }
}
