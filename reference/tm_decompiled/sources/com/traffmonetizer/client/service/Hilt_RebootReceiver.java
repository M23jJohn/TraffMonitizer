package com.traffmonetizer.client.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import dagger.hilt.android.internal.managers.BroadcastReceiverComponentManager;
import dagger.hilt.internal.UnsafeCasts;

/* loaded from: classes3.dex */
public abstract class Hilt_RebootReceiver extends BroadcastReceiver {
    private volatile boolean injected = false;
    private final Object injectedLock = new Object();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        inject(context);
    }

    protected void inject(Context context) {
        if (this.injected) {
            return;
        }
        synchronized (this.injectedLock) {
            if (!this.injected) {
                ((RebootReceiver_GeneratedInjector) BroadcastReceiverComponentManager.generatedComponent(context)).injectRebootReceiver((RebootReceiver) UnsafeCasts.unsafeCast(this));
                this.injected = true;
            }
        }
    }
}
