package com.tm;

import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public final class B3 extends TimerTask {
    public final /* synthetic */ D3 a;

    public B3(D3 d3) {
        this.a = d3;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        if (this.a.o.get() != 0) {
            this.a.o.set(0L);
            return;
        }
        Timer timer = this.a.n;
        if (timer != null) {
            timer.cancel();
        }
        boolean z = X0.a;
        X0.a("TcpProxySession", "[" + this.a.a + "] killtimer: ");
        D3 d3 = this.a;
        ((H1) d3.d).a(d3.a, new U2());
    }
}
