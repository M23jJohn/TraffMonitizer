package com.traffmonetizer.client.config;

import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConfigRepositoryImpl.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/traffmonetizer/client/config/ConfigRepositoryImpl;", "Lcom/traffmonetizer/client/config/ConfigRepository;", "defaultConfig", "Lcom/traffmonetizer/client/config/AppConfig;", "<init>", "(Lcom/traffmonetizer/client/config/AppConfig;)V", "getConfig", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConfigRepositoryImpl implements ConfigRepository {
    public static final int $stable = 0;
    private final AppConfig defaultConfig;

    @Inject
    public ConfigRepositoryImpl(AppConfig defaultConfig) {
        Intrinsics.checkNotNullParameter(defaultConfig, "defaultConfig");
        this.defaultConfig = defaultConfig;
    }

    @Override // com.traffmonetizer.client.config.ConfigRepository
    /* renamed from: getConfig, reason: from getter */
    public AppConfig getDefaultConfig() {
        return this.defaultConfig;
    }
}
