package com.tm;

import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public final class W5 extends TimerTask {
    public final /* synthetic */ Y5 a;

    public W5(Y5 y5) {
        this.a = y5;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        if (this.a.k.get() != 0) {
            this.a.k.set(0L);
            return;
        }
        Timer timer = this.a.j;
        if (timer != null) {
            timer.cancel();
        }
        boolean z = X0.a;
        X0.a("UdpProxySession", "[" + this.a.a + "] killtimer: ");
        Y5 y5 = this.a;
        ((H1) y5.d).a(y5.a, new U2());
    }
}
