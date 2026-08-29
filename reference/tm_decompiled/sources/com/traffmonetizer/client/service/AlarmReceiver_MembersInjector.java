package com.traffmonetizer.client.service;

import com.traffmonetizer.client.preferences.PreferencesRepository;
import dagger.MembersInjector;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class AlarmReceiver_MembersInjector implements MembersInjector<AlarmReceiver> {
    private final Provider<PreferencesRepository> preferencesRepositoryProvider;

    private AlarmReceiver_MembersInjector(Provider<PreferencesRepository> provider) {
        this.preferencesRepositoryProvider = provider;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlarmReceiver alarmReceiver) {
        injectPreferencesRepository(alarmReceiver, this.preferencesRepositoryProvider.get());
    }

    public static MembersInjector<AlarmReceiver> create(Provider<PreferencesRepository> provider) {
        return new AlarmReceiver_MembersInjector(provider);
    }

    public static void injectPreferencesRepository(AlarmReceiver alarmReceiver, PreferencesRepository preferencesRepository) {
        alarmReceiver.preferencesRepository = preferencesRepository;
    }
}
