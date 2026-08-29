package com.tm;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* loaded from: classes3.dex */
public final class A0 {
    public final ConnectivityManager a;
    public MutableStateFlow b;
    public final C0225z0 c;

    public A0(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getApplicationContext().getSystemService("connectivity");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        this.a = (ConnectivityManager) systemService;
        this.b = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.c = new C0225z0(this);
    }

    public final void b() {
        this.b = StateFlowKt.MutableStateFlow(Boolean.valueOf(a()));
        this.a.registerNetworkCallback(new NetworkRequest.Builder().build(), this.c);
    }

    public final void c() {
        this.a.unregisterNetworkCallback(this.c);
    }

    public final boolean a() {
        ConnectivityManager connectivityManager = this.a;
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities != null) {
            return networkCapabilities.hasTransport(1);
        }
        return false;
    }
}
