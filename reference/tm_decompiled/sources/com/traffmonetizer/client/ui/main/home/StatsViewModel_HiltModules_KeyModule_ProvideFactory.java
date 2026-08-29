package com.traffmonetizer.client.ui.main.home;

import com.traffmonetizer.client.ui.main.home.StatsViewModel_HiltModules;
import dagger.internal.Factory;

/* loaded from: classes3.dex */
public final class StatsViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provide());
    }

    public static StatsViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean provide() {
        return StatsViewModel_HiltModules.KeyModule.provide();
    }

    private static final class InstanceHolder {
        static final StatsViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new StatsViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
