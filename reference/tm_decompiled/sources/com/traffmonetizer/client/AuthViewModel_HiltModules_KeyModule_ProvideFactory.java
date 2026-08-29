package com.traffmonetizer.client;

import com.traffmonetizer.client.AuthViewModel_HiltModules;
import dagger.internal.Factory;

/* loaded from: classes3.dex */
public final class AuthViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provide());
    }

    public static AuthViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean provide() {
        return AuthViewModel_HiltModules.KeyModule.provide();
    }

    private static final class InstanceHolder {
        static final AuthViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new AuthViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
