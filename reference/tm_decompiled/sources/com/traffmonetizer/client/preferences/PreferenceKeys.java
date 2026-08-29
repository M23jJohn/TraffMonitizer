package com.traffmonetizer.client.preferences;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import kotlin.Metadata;

/* compiled from: PreferencesRepository.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\bR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/traffmonetizer/client/preferences/PreferenceKeys;", "", "<init>", "()V", "TOKEN", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "getTOKEN", "()Landroidx/datastore/preferences/core/Preferences$Key;", "PROXY_ACTIVE", "", "getPROXY_ACTIVE", "WIFI_ONLY", "getWIFI_ONLY", "DEBUG_MODE", "getDEBUG_MODE", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
final class PreferenceKeys {
    public static final PreferenceKeys INSTANCE = new PreferenceKeys();
    private static final Preferences.Key<String> TOKEN = PreferencesKeys.stringKey("token");
    private static final Preferences.Key<Boolean> PROXY_ACTIVE = PreferencesKeys.booleanKey("proxy_active");
    private static final Preferences.Key<Boolean> WIFI_ONLY = PreferencesKeys.booleanKey("wifi_only");
    private static final Preferences.Key<Boolean> DEBUG_MODE = PreferencesKeys.booleanKey("debug_mode");

    private PreferenceKeys() {
    }

    public final Preferences.Key<String> getTOKEN() {
        return TOKEN;
    }

    public final Preferences.Key<Boolean> getPROXY_ACTIVE() {
        return PROXY_ACTIVE;
    }

    public final Preferences.Key<Boolean> getWIFI_ONLY() {
        return WIFI_ONLY;
    }

    public final Preferences.Key<Boolean> getDEBUG_MODE() {
        return DEBUG_MODE;
    }
}
