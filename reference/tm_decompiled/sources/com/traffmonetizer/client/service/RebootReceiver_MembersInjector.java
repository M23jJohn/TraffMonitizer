package com.traffmonetizer.client.service;

import com.traffmonetizer.client.preferences.PreferencesRepository;
import dagger.MembersInjector;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class RebootReceiver_MembersInjector implements MembersInjector<RebootReceiver> {
    private final Provider<PreferencesRepository> preferencesRepositoryProvider;

    private RebootReceiver_MembersInjector(Provider<PreferencesRepository> provider) {
        this.preferencesRepositoryProvider = provider;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RebootReceiver rebootReceiver) {
        injectPreferencesRepository(rebootReceiver, this.preferencesRepositoryProvider.get());
    }

    public static MembersInjector<RebootReceiver> create(Provider<PreferencesRepository> provider) {
        return new RebootReceiver_MembersInjector(provider);
    }

    public static void injectPreferencesRepository(RebootReceiver rebootReceiver, PreferencesRepository preferencesRepository) {
        rebootReceiver.preferencesRepository = preferencesRepository;
    }
}
