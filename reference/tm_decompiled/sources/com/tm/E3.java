package com.tm;

import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public final class E3 extends TimerTask {
    public final /* synthetic */ G3 a;

    public E3(G3 g3) {
        this.a = g3;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        G3 g3 = this.a;
        Timer timer = g3.k;
        if (timer != null) {
            timer.cancel();
        }
        g3.k = null;
        if (g3.j) {
            return;
        }
        g3.a();
    }
}
