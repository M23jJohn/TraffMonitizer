package com.traffmonetizer.client.ui.main.connection;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.LazyClassKey;

/* loaded from: classes3.dex */
public final class ConnectionCheckViewModel_HiltModules {
    private ConnectionCheckViewModel_HiltModules() {
    }

    @Module
    public static abstract class BindsModule {
        @LazyClassKey(ConnectionCheckViewModel.class)
        @Binds
        @IntoMap
        public abstract ViewModel binds(ConnectionCheckViewModel connectionCheckViewModel);

        private BindsModule() {
        }
    }

    @Module
    public static final class KeyModule {
        @Provides
        @LazyClassKey(ConnectionCheckViewModel.class)
        @IntoMap
        public static boolean provide() {
            return true;
        }

        private KeyModule() {
        }
    }
}
