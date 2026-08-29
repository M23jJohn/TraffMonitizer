package com.traffmonetizer.client;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.traffmonetizer.client.AuthViewModel_HiltModules;
import com.traffmonetizer.client.TraffmonetizerApplication_HiltComponents;
import com.traffmonetizer.client.config.AppConfig;
import com.traffmonetizer.client.config.ConfigRepository;
import com.traffmonetizer.client.config.ConfigRepositoryImpl;
import com.traffmonetizer.client.injection.ConfigRepositoryModule_Companion_ProvideDefaultConfigFactory;
import com.traffmonetizer.client.injection.PreferencesRepositoryModule_ProvidesPreferencesRepositoryFactory;
import com.traffmonetizer.client.injection.TraffmonetizerServiceManagerModule_ProvideServiceManagerFactory;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import com.traffmonetizer.client.service.AlarmReceiver;
import com.traffmonetizer.client.service.AlarmReceiver_MembersInjector;
import com.traffmonetizer.client.service.RebootReceiver;
import com.traffmonetizer.client.service.RebootReceiver_MembersInjector;
import com.traffmonetizer.client.service.ServiceViewModel;
import com.traffmonetizer.client.service.ServiceViewModel_HiltModules;
import com.traffmonetizer.client.service.ServiceViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.traffmonetizer.client.service.ServiceViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.traffmonetizer.client.service.TraffmonetizerForegroundService;
import com.traffmonetizer.client.service.TraffmonetizerForegroundService_MembersInjector;
import com.traffmonetizer.client.service.TraffmonetizerServiceManager;
import com.traffmonetizer.client.traffmonetizer_repository.SdkTraffmonetizerRepositoryImpl;
import com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository;
import com.traffmonetizer.client.ui.main.connection.ConnectionCheckViewModel;
import com.traffmonetizer.client.ui.main.connection.ConnectionCheckViewModel_HiltModules;
import com.traffmonetizer.client.ui.main.connection.ConnectionCheckViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.traffmonetizer.client.ui.main.connection.ConnectionCheckViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.traffmonetizer.client.ui.main.home.ProxyViewModel;
import com.traffmonetizer.client.ui.main.home.ProxyViewModel_HiltModules;
import com.traffmonetizer.client.ui.main.home.ProxyViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.traffmonetizer.client.ui.main.home.ProxyViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.traffmonetizer.client.ui.main.home.StatsViewModel;
import com.traffmonetizer.client.ui.main.home.StatsViewModel_HiltModules;
import com.traffmonetizer.client.ui.main.home.StatsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.traffmonetizer.client.ui.main.home.StatsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.traffmonetizer.client.ui.main.settings.SettingsViewModel;
import com.traffmonetizer.client.ui.main.settings.SettingsViewModel_HiltModules;
import com.traffmonetizer.client.ui.main.settings.SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.traffmonetizer.client.ui.main.settings.SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public final class DaggerTraffmonetizerApplication_HiltComponents_SingletonC {
    private DaggerTraffmonetizerApplication_HiltComponents_SingletonC() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ApplicationContextModule applicationContextModule;

        private Builder() {
        }

        public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
            this.applicationContextModule = (ApplicationContextModule) Preconditions.checkNotNull(applicationContextModule);
            return this;
        }

        public TraffmonetizerApplication_HiltComponents.SingletonC build() {
            Preconditions.checkBuilderRequirement(this.applicationContextModule, ApplicationContextModule.class);
            return new SingletonCImpl(this.applicationContextModule);
        }
    }

    private static final class ActivityRetainedCBuilder implements TraffmonetizerApplication_HiltComponents.ActivityRetainedC.Builder {
        private SavedStateHandleHolder savedStateHandleHolder;
        private final SingletonCImpl singletonCImpl;

        private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
            this.singletonCImpl = singletonCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder
        public ActivityRetainedCBuilder savedStateHandleHolder(SavedStateHandleHolder savedStateHandleHolder) {
            this.savedStateHandleHolder = (SavedStateHandleHolder) Preconditions.checkNotNull(savedStateHandleHolder);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder
        public TraffmonetizerApplication_HiltComponents.ActivityRetainedC build() {
            Preconditions.checkBuilderRequirement(this.savedStateHandleHolder, SavedStateHandleHolder.class);
            return new ActivityRetainedCImpl(this.singletonCImpl, this.savedStateHandleHolder);
        }
    }

    private static final class ActivityCBuilder implements TraffmonetizerApplication_HiltComponents.ActivityC.Builder {
        private Activity activity;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;

        private ActivityCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityComponentBuilder
        public ActivityCBuilder activity(Activity activity) {
            this.activity = (Activity) Preconditions.checkNotNull(activity);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityComponentBuilder
        public TraffmonetizerApplication_HiltComponents.ActivityC build() {
            Preconditions.checkBuilderRequirement(this.activity, Activity.class);
            return new ActivityCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activity);
        }
    }

    private static final class FragmentCBuilder implements TraffmonetizerApplication_HiltComponents.FragmentC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private Fragment fragment;
        private final SingletonCImpl singletonCImpl;

        private FragmentCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.FragmentComponentBuilder
        public FragmentCBuilder fragment(Fragment fragment) {
            this.fragment = (Fragment) Preconditions.checkNotNull(fragment);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.FragmentComponentBuilder
        public TraffmonetizerApplication_HiltComponents.FragmentC build() {
            Preconditions.checkBuilderRequirement(this.fragment, Fragment.class);
            return new FragmentCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.fragment);
        }
    }

    private static final class ViewWithFragmentCBuilder implements TraffmonetizerApplication_HiltComponents.ViewWithFragmentC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl;
        private final SingletonCImpl singletonCImpl;
        private View view;

        private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, FragmentCImpl fragmentCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
            this.fragmentCImpl = fragmentCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder
        public ViewWithFragmentCBuilder view(View view) {
            this.view = (View) Preconditions.checkNotNull(view);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder
        public TraffmonetizerApplication_HiltComponents.ViewWithFragmentC build() {
            Preconditions.checkBuilderRequirement(this.view, View.class);
            return new ViewWithFragmentCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.fragmentCImpl, this.view);
        }
    }

    private static final class ViewCBuilder implements TraffmonetizerApplication_HiltComponents.ViewC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;
        private View view;

        private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ViewComponentBuilder
        public ViewCBuilder view(View view) {
            this.view = (View) Preconditions.checkNotNull(view);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewComponentBuilder
        public TraffmonetizerApplication_HiltComponents.ViewC build() {
            Preconditions.checkBuilderRequirement(this.view, View.class);
            return new ViewCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.view);
        }
    }

    private static final class ViewModelCBuilder implements TraffmonetizerApplication_HiltComponents.ViewModelC.Builder {
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private SavedStateHandle savedStateHandle;
        private final SingletonCImpl singletonCImpl;
        private ViewModelLifecycle viewModelLifecycle;

        private ViewModelCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ViewModelComponentBuilder
        public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
            this.savedStateHandle = (SavedStateHandle) Preconditions.checkNotNull(handle);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewModelComponentBuilder
        public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
            this.viewModelLifecycle = (ViewModelLifecycle) Preconditions.checkNotNull(viewModelLifecycle);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewModelComponentBuilder
        public TraffmonetizerApplication_HiltComponents.ViewModelC build() {
            Preconditions.checkBuilderRequirement(this.savedStateHandle, SavedStateHandle.class);
            Preconditions.checkBuilderRequirement(this.viewModelLifecycle, ViewModelLifecycle.class);
            return new ViewModelCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.savedStateHandle, this.viewModelLifecycle);
        }
    }

    private static final class ServiceCBuilder implements TraffmonetizerApplication_HiltComponents.ServiceC.Builder {
        private Service service;
        private final SingletonCImpl singletonCImpl;

        private ServiceCBuilder(SingletonCImpl singletonCImpl) {
            this.singletonCImpl = singletonCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ServiceComponentBuilder
        public ServiceCBuilder service(Service service) {
            this.service = (Service) Preconditions.checkNotNull(service);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ServiceComponentBuilder
        public TraffmonetizerApplication_HiltComponents.ServiceC build() {
            Preconditions.checkBuilderRequirement(this.service, Service.class);
            return new ServiceCImpl(this.singletonCImpl, this.service);
        }
    }

    private static final class ViewWithFragmentCImpl extends TraffmonetizerApplication_HiltComponents.ViewWithFragmentC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl;
        private final SingletonCImpl singletonCImpl;
        private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

        ViewWithFragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, FragmentCImpl fragmentCImpl, View viewParam) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
            this.fragmentCImpl = fragmentCImpl;
        }
    }

    private static final class FragmentCImpl extends TraffmonetizerApplication_HiltComponents.FragmentC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl = this;
        private final SingletonCImpl singletonCImpl;

        FragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, Fragment fragmentParam) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }

        @Override // dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories.FragmentEntryPoint
        public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
            return this.activityCImpl.getHiltInternalFactoryFactory();
        }

        @Override // dagger.hilt.android.internal.managers.ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint
        public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
            return new ViewWithFragmentCBuilder(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.fragmentCImpl);
        }
    }

    private static final class ViewCImpl extends TraffmonetizerApplication_HiltComponents.ViewC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;
        private final ViewCImpl viewCImpl = this;

        ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, View viewParam) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }
    }

    private static final class ActivityCImpl extends TraffmonetizerApplication_HiltComponents.ActivityC {
        private final ActivityCImpl activityCImpl = this;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;

        @Override // com.traffmonetizer.client.MainActivity_GeneratedInjector
        public void injectMainActivity(MainActivity arg0) {
        }

        ActivityCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
        }

        @Override // dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories.ActivityEntryPoint
        public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
            return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(this.singletonCImpl, this.activityRetainedCImpl));
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ActivityCreatorEntryPoint
        public Map<Class<?>, Boolean> getViewModelKeys() {
            return LazyClassKeyMap.of(MapBuilder.newMapBuilder(6).put(AuthViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(AuthViewModel_HiltModules.KeyModule.provide())).put(ConnectionCheckViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(ConnectionCheckViewModel_HiltModules.KeyModule.provide())).put(ProxyViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(ProxyViewModel_HiltModules.KeyModule.provide())).put(ServiceViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(ServiceViewModel_HiltModules.KeyModule.provide())).put(SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(SettingsViewModel_HiltModules.KeyModule.provide())).put(StatsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(StatsViewModel_HiltModules.KeyModule.provide())).build());
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ActivityCreatorEntryPoint
        public ViewModelComponentBuilder getViewModelComponentBuilder() {
            return new ViewModelCBuilder(this.singletonCImpl, this.activityRetainedCImpl);
        }

        @Override // dagger.hilt.android.internal.managers.FragmentComponentManager.FragmentComponentBuilderEntryPoint
        public FragmentComponentBuilder fragmentComponentBuilder() {
            return new FragmentCBuilder(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl);
        }

        @Override // dagger.hilt.android.internal.managers.ViewComponentManager.ViewComponentBuilderEntryPoint
        public ViewComponentBuilder viewComponentBuilder() {
            return new ViewCBuilder(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl);
        }
    }

    private static final class ViewModelCImpl extends TraffmonetizerApplication_HiltComponents.ViewModelC {
        private final ActivityRetainedCImpl activityRetainedCImpl;
        Provider<AuthViewModel> authViewModelProvider;
        Provider<ConnectionCheckViewModel> connectionCheckViewModelProvider;
        Provider<ProxyViewModel> proxyViewModelProvider;
        Provider<ServiceViewModel> serviceViewModelProvider;
        Provider<SettingsViewModel> settingsViewModelProvider;
        private final SingletonCImpl singletonCImpl;
        Provider<StatsViewModel> statsViewModelProvider;
        private final ViewModelCImpl viewModelCImpl = this;

        ViewModelCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam, ViewModelLifecycle viewModelLifecycleParam) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            initialize(savedStateHandleParam, viewModelLifecycleParam);
        }

        private void initialize(final SavedStateHandle savedStateHandleParam, final ViewModelLifecycle viewModelLifecycleParam) {
            this.authViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 0);
            this.connectionCheckViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 1);
            this.proxyViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 2);
            this.serviceViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 3);
            this.settingsViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 4);
            this.statsViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 5);
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ViewModelFactoriesEntryPoint
        public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
            return LazyClassKeyMap.of(MapBuilder.newMapBuilder(6).put(AuthViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.authViewModelProvider).put(ConnectionCheckViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.connectionCheckViewModelProvider).put(ProxyViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.proxyViewModelProvider).put(ServiceViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.serviceViewModelProvider).put(SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.settingsViewModelProvider).put(StatsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.statsViewModelProvider).build());
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ViewModelFactoriesEntryPoint
        public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
            return Collections.EMPTY_MAP;
        }

        private static final class SwitchingProvider<T> implements Provider<T> {
            private final ActivityRetainedCImpl activityRetainedCImpl;
            private final int id;
            private final SingletonCImpl singletonCImpl;
            private final ViewModelCImpl viewModelCImpl;

            SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ViewModelCImpl viewModelCImpl, int id) {
                this.singletonCImpl = singletonCImpl;
                this.activityRetainedCImpl = activityRetainedCImpl;
                this.viewModelCImpl = viewModelCImpl;
                this.id = id;
            }

            @Override // javax.inject.Provider, jakarta.inject.Provider
            public T get() {
                int i = this.id;
                if (i == 0) {
                    return (T) new AuthViewModel(this.singletonCImpl.providesPreferencesRepositoryProvider.get(), this.singletonCImpl.bindConfigRepositoryProvider.get());
                }
                if (i == 1) {
                    return (T) new ConnectionCheckViewModel(this.singletonCImpl.provideServiceManagerProvider.get());
                }
                if (i == 2) {
                    return (T) new ProxyViewModel(this.singletonCImpl.provideServiceManagerProvider.get(), this.singletonCImpl.providesPreferencesRepositoryProvider.get());
                }
                if (i == 3) {
                    return (T) new ServiceViewModel(this.singletonCImpl.provideServiceManagerProvider.get(), this.singletonCImpl.providesPreferencesRepositoryProvider.get());
                }
                if (i == 4) {
                    return (T) new SettingsViewModel(this.singletonCImpl.providesPreferencesRepositoryProvider.get(), this.singletonCImpl.provideServiceManagerProvider.get());
                }
                if (i == 5) {
                    return (T) new StatsViewModel(this.singletonCImpl.provideServiceManagerProvider.get());
                }
                throw new AssertionError(this.id);
            }
        }
    }

    private static final class ActivityRetainedCImpl extends TraffmonetizerApplication_HiltComponents.ActivityRetainedC {
        private final ActivityRetainedCImpl activityRetainedCImpl = this;
        Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;
        private final SingletonCImpl singletonCImpl;

        ActivityRetainedCImpl(SingletonCImpl singletonCImpl, SavedStateHandleHolder savedStateHandleHolderParam) {
            this.singletonCImpl = singletonCImpl;
            initialize(savedStateHandleHolderParam);
        }

        private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
            this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, 0));
        }

        @Override // dagger.hilt.android.internal.managers.ActivityComponentManager.ActivityComponentBuilderEntryPoint
        public ActivityComponentBuilder activityComponentBuilder() {
            return new ActivityCBuilder(this.singletonCImpl, this.activityRetainedCImpl);
        }

        @Override // dagger.hilt.android.internal.managers.ActivityRetainedComponentManager.ActivityRetainedLifecycleEntryPoint
        public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
            return this.provideActivityRetainedLifecycleProvider.get();
        }

        private static final class SwitchingProvider<T> implements Provider<T> {
            private final ActivityRetainedCImpl activityRetainedCImpl;
            private final int id;
            private final SingletonCImpl singletonCImpl;

            SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, int id) {
                this.singletonCImpl = singletonCImpl;
                this.activityRetainedCImpl = activityRetainedCImpl;
                this.id = id;
            }

            @Override // javax.inject.Provider, jakarta.inject.Provider
            public T get() {
                if (this.id == 0) {
                    return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();
                }
                throw new AssertionError(this.id);
            }
        }
    }

    private static final class ServiceCImpl extends TraffmonetizerApplication_HiltComponents.ServiceC {
        private final ServiceCImpl serviceCImpl = this;
        private final SingletonCImpl singletonCImpl;

        ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
            this.singletonCImpl = singletonCImpl;
        }

        @Override // com.traffmonetizer.client.service.TraffmonetizerForegroundService_GeneratedInjector
        public void injectTraffmonetizerForegroundService(TraffmonetizerForegroundService arg0) {
            injectTraffmonetizerForegroundService2(arg0);
        }

        private TraffmonetizerForegroundService injectTraffmonetizerForegroundService2(TraffmonetizerForegroundService instance) {
            TraffmonetizerForegroundService_MembersInjector.injectTraffmonetizerRepository(instance, this.singletonCImpl.provideTraffmonetizerRepositoryProvider.get());
            TraffmonetizerForegroundService_MembersInjector.injectPreferencesRepository(instance, this.singletonCImpl.providesPreferencesRepositoryProvider.get());
            return instance;
        }
    }

    private static final class SingletonCImpl extends TraffmonetizerApplication_HiltComponents.SingletonC {
        private final ApplicationContextModule applicationContextModule;
        Provider<ConfigRepository> bindConfigRepositoryProvider;
        Provider<ConfigRepositoryImpl> configRepositoryImplProvider;
        Provider<AppConfig> provideDefaultConfigProvider;
        Provider<TraffmonetizerServiceManager> provideServiceManagerProvider;
        Provider<TraffmonetizerRepository> provideTraffmonetizerRepositoryProvider;
        Provider<PreferencesRepository> providesPreferencesRepositoryProvider;
        Provider<SdkTraffmonetizerRepositoryImpl> sdkTraffmonetizerRepositoryImplProvider;
        private final SingletonCImpl singletonCImpl = this;

        @Override // com.traffmonetizer.client.TraffmonetizerApplication_GeneratedInjector
        public void injectTraffmonetizerApplication(TraffmonetizerApplication traffmonetizerApplication) {
        }

        SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
            this.applicationContextModule = applicationContextModuleParam;
            initialize(applicationContextModuleParam);
        }

        private void initialize(final ApplicationContextModule applicationContextModuleParam) {
            this.providesPreferencesRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 0));
            this.provideDefaultConfigProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 2));
            SwitchingProvider switchingProvider = new SwitchingProvider(this.singletonCImpl, 1);
            this.configRepositoryImplProvider = switchingProvider;
            this.bindConfigRepositoryProvider = DoubleCheck.provider((Provider) switchingProvider);
            this.provideServiceManagerProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 3));
            SwitchingProvider switchingProvider2 = new SwitchingProvider(this.singletonCImpl, 4);
            this.sdkTraffmonetizerRepositoryImplProvider = switchingProvider2;
            this.provideTraffmonetizerRepositoryProvider = DoubleCheck.provider((Provider) switchingProvider2);
        }

        @Override // com.traffmonetizer.client.service.AlarmReceiver_GeneratedInjector
        public void injectAlarmReceiver(AlarmReceiver arg0) {
            injectAlarmReceiver2(arg0);
        }

        @Override // com.traffmonetizer.client.service.RebootReceiver_GeneratedInjector
        public void injectRebootReceiver(RebootReceiver arg0) {
            injectRebootReceiver2(arg0);
        }

        @Override // dagger.hilt.android.flags.FragmentGetContextFix.FragmentGetContextFixEntryPoint
        public Set<Boolean> getDisableFragmentGetContextFix() {
            return Collections.EMPTY_SET;
        }

        @Override // dagger.hilt.android.internal.managers.ActivityRetainedComponentManager.ActivityRetainedComponentBuilderEntryPoint
        public ActivityRetainedComponentBuilder retainedComponentBuilder() {
            return new ActivityRetainedCBuilder(this.singletonCImpl);
        }

        @Override // dagger.hilt.android.internal.managers.ServiceComponentManager.ServiceComponentBuilderEntryPoint
        public ServiceComponentBuilder serviceComponentBuilder() {
            return new ServiceCBuilder(this.singletonCImpl);
        }

        private AlarmReceiver injectAlarmReceiver2(AlarmReceiver instance) {
            AlarmReceiver_MembersInjector.injectPreferencesRepository(instance, this.providesPreferencesRepositoryProvider.get());
            return instance;
        }

        private RebootReceiver injectRebootReceiver2(RebootReceiver instance2) {
            RebootReceiver_MembersInjector.injectPreferencesRepository(instance2, this.providesPreferencesRepositoryProvider.get());
            return instance2;
        }

        private static final class SwitchingProvider<T> implements Provider<T> {
            private final int id;
            private final SingletonCImpl singletonCImpl;

            SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
                this.singletonCImpl = singletonCImpl;
                this.id = id;
            }

            @Override // javax.inject.Provider, jakarta.inject.Provider
            public T get() {
                int i = this.id;
                if (i == 0) {
                    return (T) PreferencesRepositoryModule_ProvidesPreferencesRepositoryFactory.providesPreferencesRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                }
                if (i == 1) {
                    return (T) new ConfigRepositoryImpl(this.singletonCImpl.provideDefaultConfigProvider.get());
                }
                if (i == 2) {
                    return (T) ConfigRepositoryModule_Companion_ProvideDefaultConfigFactory.provideDefaultConfig();
                }
                if (i == 3) {
                    return (T) TraffmonetizerServiceManagerModule_ProvideServiceManagerFactory.provideServiceManager(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.providesPreferencesRepositoryProvider.get());
                }
                if (i == 4) {
                    return (T) new SdkTraffmonetizerRepositoryImpl(this.singletonCImpl.bindConfigRepositoryProvider.get());
                }
                throw new AssertionError(this.id);
            }
        }
    }
}
