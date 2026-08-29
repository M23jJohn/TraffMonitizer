package com.tm;

import java.util.Timer;
import kotlin.ResultKt;
import kotlin.concurrent.TimersKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DelayKt;

/* loaded from: classes3.dex */
public final class G3 {
    public final int a;
    public final long b;
    public final int c;
    public final Function1 d;
    public long e;
    public long f;
    public long g;
    public long h;
    public long i;
    public boolean j;
    public Timer k;

    public G3(int i, long j, int i2, C0228z3 sendAck) {
        Intrinsics.checkNotNullParameter(sendAck, "sendAck");
        this.a = i;
        this.b = j;
        this.c = i2;
        this.d = sendAck;
    }

    public final void a(long j) {
        this.h += j;
        boolean z = X0.a;
        X0.a("TcpWindowHandler", "onDataReceived: [" + this.a + "] total=" + this.h + ", new=" + j);
        if (!this.j) {
            a();
        }
        if (this.k == null) {
            long j2 = this.b;
            Timer timer = TimersKt.timer("ackTimer", false);
            timer.schedule(new E3(this), j2, j2);
            this.k = timer;
        }
    }

    public final void b(long j) {
        this.f += j;
        boolean z = X0.a;
        X0.a("TcpWindowHandler", "TcpWindowHandler: onDataSent: [" + this.a + "] total=" + this.f + ", new=" + j);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object a(Continuation continuation) {
        F3 f3;
        int i;
        long currentTimeMillis;
        G3 g3;
        int i2;
        if (continuation instanceof F3) {
            f3 = (F3) continuation;
            int i3 = f3.f;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                f3.f = i3 - Integer.MIN_VALUE;
                Object obj = f3.d;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = f3.f;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    currentTimeMillis = System.currentTimeMillis();
                    boolean z = X0.a;
                    X0.a("TcpWindowHandler", "TcpWindowHandler: send: [" + this.a + "] start");
                    g3 = this;
                    i2 = 0;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    currentTimeMillis = f3.c;
                    i2 = f3.b;
                    g3 = f3.a;
                    ResultKt.throwOnFailure(obj);
                }
                while (g3.f - g3.e > g3.b) {
                    f3.a = g3;
                    f3.b = 1;
                    f3.c = currentTimeMillis;
                    f3.f = 1;
                    if (DelayKt.delay(10L, f3) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    i2 = 1;
                }
                boolean z2 = X0.a;
                StringBuilder sb = new StringBuilder("TcpWindowHandler: send: [");
                sb.append(g3.a);
                sb.append("] waiting=");
                sb.append(i2 == 0);
                sb.append(", wait=");
                sb.append(System.currentTimeMillis() - currentTimeMillis);
                sb.append(" ms");
                X0.a("TcpWindowHandler", sb.toString());
                return Boxing.boxBoolean(i2 != 0);
            }
        }
        f3 = new F3(this, continuation);
        Object obj2 = f3.d;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = f3.f;
        if (i != 0) {
        }
        while (g3.f - g3.e > g3.b) {
        }
        boolean z22 = X0.a;
        StringBuilder sb2 = new StringBuilder("TcpWindowHandler: send: [");
        sb2.append(g3.a);
        sb2.append("] waiting=");
        sb2.append(i2 == 0);
        sb2.append(", wait=");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        sb2.append(" ms");
        X0.a("TcpWindowHandler", sb2.toString());
        return Boxing.boxBoolean(i2 != 0);
    }

    public final void a() {
        if (this.j) {
            return;
        }
        this.j = true;
        boolean z = X0.a;
        StringBuilder sb = new StringBuilder("TcpWindowHandler: checkSendAck: [");
        sb.append(this.a);
        sb.append("] lastAck=");
        sb.append(this.g);
        sb.append(", ackBytes=");
        sb.append(this.h);
        sb.append(", lastSent=");
        sb.append(System.currentTimeMillis() - this.i);
        sb.append(", delta=");
        sb.append(this.h - this.g);
        sb.append(", halfWindow=");
        long j = 2;
        sb.append(this.b / j);
        X0.a("TcpWindowHandler", sb.toString());
        if ((System.currentTimeMillis() - this.i > this.c || this.h - this.g > this.b / j) && this.g != this.h) {
            X0.a("TcpWindowHandler", "TcpWindowHandler: sendAck: [" + this.a + "] lastAck=" + this.g + ", ackBytes=" + this.h + ", lastSent=" + (System.currentTimeMillis() - this.i) + ", delta=" + (this.h - this.g) + ", halfWindow=" + (this.b / j));
            this.g = this.h;
            this.i = System.currentTimeMillis();
            Timer timer = this.k;
            if (timer != null) {
                timer.cancel();
            }
            this.k = null;
            this.d.invoke(Long.valueOf(this.g));
        }
        this.j = false;
    }
}
