package com.traffmonetizer.client.ui.main.connection;

import com.traffmonetizer.client.ui.main.connection.ConnectionCheckViewModel_HiltModules;
import dagger.internal.Factory;

/* loaded from: classes3.dex */
public final class ConnectionCheckViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provide());
    }

    public static ConnectionCheckViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean provide() {
        return ConnectionCheckViewModel_HiltModules.KeyModule.provide();
    }

    private static final class InstanceHolder {
        static final ConnectionCheckViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new ConnectionCheckViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
