package com.traffmonetizer.client.injection;

import com.traffmonetizer.client.traffmonetizer_repository.SdkTraffmonetizerRepositoryImpl;
import com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository;
import dagger.Binds;
import dagger.Module;
import javax.inject.Singleton;
import kotlin.Metadata;

/* compiled from: SdkTraffmonetizerRepositoryModule.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'¨\u0006\b"}, d2 = {"Lcom/traffmonetizer/client/injection/SdkTraffmonetizerRepositoryModule;", "", "<init>", "()V", "provideTraffmonetizerRepository", "Lcom/traffmonetizer/client/traffmonetizer_repository/TraffmonetizerRepository;", "impl", "Lcom/traffmonetizer/client/traffmonetizer_repository/SdkTraffmonetizerRepositoryImpl;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
/* loaded from: classes3.dex */
public abstract class SdkTraffmonetizerRepositoryModule {
    public static final int $stable = 0;

    @Singleton
    @Binds
    public abstract TraffmonetizerRepository provideTraffmonetizerRepository(SdkTraffmonetizerRepositoryImpl impl);
}
