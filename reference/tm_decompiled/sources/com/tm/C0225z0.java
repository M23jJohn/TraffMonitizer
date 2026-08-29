package com.tm;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.z0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0225z0 extends ConnectivityManager.NetworkCallback {
    public final /* synthetic */ A0 a;

    public C0225z0(A0 a0) {
        this.a = a0;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network network) {
        Intrinsics.checkNotNullParameter(network, "network");
        this.a.b.setValue(Boolean.valueOf(this.a.a()));
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        Intrinsics.checkNotNullParameter(network, "network");
        Intrinsics.checkNotNullParameter(networkCapabilities, "networkCapabilities");
        this.a.b.setValue(Boolean.valueOf(this.a.a()));
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        Intrinsics.checkNotNullParameter(network, "network");
        this.a.b.setValue(Boolean.valueOf(this.a.a()));
    }
}
