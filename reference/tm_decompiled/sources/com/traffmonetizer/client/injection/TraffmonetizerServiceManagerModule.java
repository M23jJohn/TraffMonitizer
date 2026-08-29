package com.traffmonetizer.client.injection;

import android.content.Context;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import com.traffmonetizer.client.service.TraffmonetizerServiceManager;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TraffmonetizerServiceManagerModule.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lcom/traffmonetizer/client/injection/TraffmonetizerServiceManagerModule;", "", "<init>", "()V", "provideServiceManager", "Lcom/traffmonetizer/client/service/TraffmonetizerServiceManager;", "context", "Landroid/content/Context;", "preferencesRepository", "Lcom/traffmonetizer/client/preferences/PreferencesRepository;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
/* loaded from: classes3.dex */
public final class TraffmonetizerServiceManagerModule {
    public static final int $stable = 0;
    public static final TraffmonetizerServiceManagerModule INSTANCE = new TraffmonetizerServiceManagerModule();

    private TraffmonetizerServiceManagerModule() {
    }

    @Provides
    @Singleton
    public final TraffmonetizerServiceManager provideServiceManager(@ApplicationContext Context context, PreferencesRepository preferencesRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        return new TraffmonetizerServiceManager(context, preferencesRepository);
    }
}
