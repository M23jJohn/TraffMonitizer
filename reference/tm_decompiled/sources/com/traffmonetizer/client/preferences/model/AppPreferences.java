package com.traffmonetizer.client.preferences.model;

import com.traffmonetizer.client.ui.main.home.TraffStatsEntity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppPreferences.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/traffmonetizer/client/preferences/model/AppPreferences;", "", "token", "", "proxyActive", "", "wifiOnly", "debugMode", "<init>", "(Ljava/lang/String;ZZZ)V", "getToken", "()Ljava/lang/String;", "getProxyActive", "()Z", "getWifiOnly", "getDebugMode", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class AppPreferences {
    public static final int $stable = 0;
    private final boolean debugMode;
    private final boolean proxyActive;
    private final String token;
    private final boolean wifiOnly;

    public AppPreferences() {
        this(null, false, false, false, 15, null);
    }

    public static /* synthetic */ AppPreferences copy$default(AppPreferences appPreferences, String str, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = appPreferences.token;
        }
        if ((i & 2) != 0) {
            z = appPreferences.proxyActive;
        }
        if ((i & 4) != 0) {
            z2 = appPreferences.wifiOnly;
        }
        if ((i & 8) != 0) {
            z3 = appPreferences.debugMode;
        }
        return appPreferences.copy(str, z, z2, z3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getToken() {
        return this.token;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getProxyActive() {
        return this.proxyActive;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getWifiOnly() {
        return this.wifiOnly;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getDebugMode() {
        return this.debugMode;
    }

    public final AppPreferences copy(String token, boolean proxyActive, boolean wifiOnly, boolean debugMode) {
        return new AppPreferences(token, proxyActive, wifiOnly, debugMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppPreferences)) {
            return false;
        }
        AppPreferences appPreferences = (AppPreferences) other;
        return Intrinsics.areEqual(this.token, appPreferences.token) && this.proxyActive == appPreferences.proxyActive && this.wifiOnly == appPreferences.wifiOnly && this.debugMode == appPreferences.debugMode;
    }

    public int hashCode() {
        String str = this.token;
        return ((((((str == null ? 0 : str.hashCode()) * 31) + TraffStatsEntity$$ExternalSyntheticBackport0.m(this.proxyActive)) * 31) + TraffStatsEntity$$ExternalSyntheticBackport0.m(this.wifiOnly)) * 31) + TraffStatsEntity$$ExternalSyntheticBackport0.m(this.debugMode);
    }

    public String toString() {
        return "AppPreferences(token=" + this.token + ", proxyActive=" + this.proxyActive + ", wifiOnly=" + this.wifiOnly + ", debugMode=" + this.debugMode + ')';
    }

    public AppPreferences(String str, boolean z, boolean z2, boolean z3) {
        this.token = str;
        this.proxyActive = z;
        this.wifiOnly = z2;
        this.debugMode = z3;
    }

    public /* synthetic */ AppPreferences(String str, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? true : z2, (i & 8) != 0 ? false : z3);
    }

    public final String getToken() {
        return this.token;
    }

    public final boolean getProxyActive() {
        return this.proxyActive;
    }

    public final boolean getWifiOnly() {
        return this.wifiOnly;
    }

    public final boolean getDebugMode() {
        return this.debugMode;
    }
}
