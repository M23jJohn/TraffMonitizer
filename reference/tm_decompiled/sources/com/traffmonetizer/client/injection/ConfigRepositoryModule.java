package com.traffmonetizer.client.injection;

import com.traffmonetizer.client.BuildConfig;
import com.traffmonetizer.client.config.AppConfig;
import com.traffmonetizer.client.config.ConfigRepository;
import com.traffmonetizer.client.config.ConfigRepositoryImpl;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ConfigRepositoryModule.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'¨\u0006\t"}, d2 = {"Lcom/traffmonetizer/client/injection/ConfigRepositoryModule;", "", "<init>", "()V", "bindConfigRepository", "Lcom/traffmonetizer/client/config/ConfigRepository;", "impl", "Lcom/traffmonetizer/client/config/ConfigRepositoryImpl;", "Companion", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
/* loaded from: classes3.dex */
public abstract class ConfigRepositoryModule {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Singleton
    @Binds
    public abstract ConfigRepository bindConfigRepository(ConfigRepositoryImpl impl);

    /* compiled from: ConfigRepositoryModule.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"Lcom/traffmonetizer/client/injection/ConfigRepositoryModule$Companion;", "", "<init>", "()V", "provideDefaultConfig", "Lcom/traffmonetizer/client/config/AppConfig;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Provides
        @Singleton
        public final AppConfig provideDefaultConfig() {
            return new AppConfig(BuildConfig.VERSION_NAME, null, 2, null);
        }
    }
}
