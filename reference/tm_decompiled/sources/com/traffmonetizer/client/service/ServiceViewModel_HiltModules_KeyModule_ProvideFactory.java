package com.traffmonetizer.client.service;

import com.traffmonetizer.client.service.ServiceViewModel_HiltModules;
import dagger.internal.Factory;

/* loaded from: classes3.dex */
public final class ServiceViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provide());
    }

    public static ServiceViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean provide() {
        return ServiceViewModel_HiltModules.KeyModule.provide();
    }

    private static final class InstanceHolder {
        static final ServiceViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new ServiceViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
