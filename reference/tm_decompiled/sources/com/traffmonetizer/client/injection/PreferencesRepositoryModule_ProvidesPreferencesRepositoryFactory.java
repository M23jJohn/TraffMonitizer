package com.traffmonetizer.client.injection;

import android.content.Context;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class PreferencesRepositoryModule_ProvidesPreferencesRepositoryFactory implements Factory<PreferencesRepository> {
    private final Provider<Context> contextProvider;

    private PreferencesRepositoryModule_ProvidesPreferencesRepositoryFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PreferencesRepository get() {
        return providesPreferencesRepository(this.contextProvider.get());
    }

    public static PreferencesRepositoryModule_ProvidesPreferencesRepositoryFactory create(Provider<Context> provider) {
        return new PreferencesRepositoryModule_ProvidesPreferencesRepositoryFactory(provider);
    }

    public static PreferencesRepository providesPreferencesRepository(Context context) {
        return (PreferencesRepository) Preconditions.checkNotNullFromProvides(PreferencesRepositoryModule.INSTANCE.providesPreferencesRepository(context));
    }
}
