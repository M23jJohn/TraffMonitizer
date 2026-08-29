package com.traffmonetizer.client.ui.main.connection;

import com.traffmonetizer.client.service.TraffmonetizerServiceManager;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class ConnectionCheckViewModel_Factory implements Factory<ConnectionCheckViewModel> {
    private final Provider<TraffmonetizerServiceManager> traffmonetizerServiceManagerProvider;

    private ConnectionCheckViewModel_Factory(Provider<TraffmonetizerServiceManager> provider) {
        this.traffmonetizerServiceManagerProvider = provider;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ConnectionCheckViewModel get() {
        return newInstance(this.traffmonetizerServiceManagerProvider.get());
    }

    public static ConnectionCheckViewModel_Factory create(Provider<TraffmonetizerServiceManager> provider) {
        return new ConnectionCheckViewModel_Factory(provider);
    }

    public static ConnectionCheckViewModel newInstance(TraffmonetizerServiceManager traffmonetizerServiceManager) {
        return new ConnectionCheckViewModel(traffmonetizerServiceManager);
    }
}
