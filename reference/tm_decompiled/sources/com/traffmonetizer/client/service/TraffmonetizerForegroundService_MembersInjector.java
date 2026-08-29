package com.traffmonetizer.client.service;

import com.traffmonetizer.client.preferences.PreferencesRepository;
import com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository;
import dagger.MembersInjector;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class TraffmonetizerForegroundService_MembersInjector implements MembersInjector<TraffmonetizerForegroundService> {
    private final Provider<PreferencesRepository> preferencesRepositoryProvider;
    private final Provider<TraffmonetizerRepository> traffmonetizerRepositoryProvider;

    private TraffmonetizerForegroundService_MembersInjector(Provider<TraffmonetizerRepository> provider, Provider<PreferencesRepository> provider2) {
        this.traffmonetizerRepositoryProvider = provider;
        this.preferencesRepositoryProvider = provider2;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TraffmonetizerForegroundService traffmonetizerForegroundService) {
        injectTraffmonetizerRepository(traffmonetizerForegroundService, this.traffmonetizerRepositoryProvider.get());
        injectPreferencesRepository(traffmonetizerForegroundService, this.preferencesRepositoryProvider.get());
    }

    public static MembersInjector<TraffmonetizerForegroundService> create(Provider<TraffmonetizerRepository> provider, Provider<PreferencesRepository> provider2) {
        return new TraffmonetizerForegroundService_MembersInjector(provider, provider2);
    }

    public static void injectTraffmonetizerRepository(TraffmonetizerForegroundService traffmonetizerForegroundService, TraffmonetizerRepository traffmonetizerRepository) {
        traffmonetizerForegroundService.traffmonetizerRepository = traffmonetizerRepository;
    }

    public static void injectPreferencesRepository(TraffmonetizerForegroundService traffmonetizerForegroundService, PreferencesRepository preferencesRepository) {
        traffmonetizerForegroundService.preferencesRepository = preferencesRepository;
    }
}
