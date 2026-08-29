package com.tm;

import java.io.BufferedOutputStream;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.concurrent.TimersKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ReceiveChannel;

/* loaded from: classes3.dex */
public final class D3 extends M1 {
    public final Map f;
    public G3 g;
    public final ExecutorCoroutineDispatcher h;
    public final CoroutineScope i;
    public final CoroutineScope j;
    public final CoroutineScope k;
    public C0075d3 l;
    public BufferedOutputStream m;
    public Timer n;
    public final AtomicLong o;
    public final Channel p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D3(int i, String host, int i2, P1 listener, C0166q3 socketRepository, Map map) {
        super(i, host, i2, listener, socketRepository);
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(socketRepository, "socketRepository");
        this.f = map;
        new C0221y3(CoroutineExceptionHandler.INSTANCE, i, listener);
        ExecutorCoroutineDispatcher newSingleThreadContext = ThreadPoolDispatcherKt.newSingleThreadContext("sessionReadLoop");
        this.h = newSingleThreadContext;
        this.i = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        this.j = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().limitedParallelism(1));
        this.k = CoroutineScopeKt.CoroutineScope(newSingleThreadContext);
        this.o = new AtomicLong(0L);
        this.p = ChannelKt.Channel$default(256, null, null, 6, null);
    }

    @Override // com.tm.M1
    public final void a() {
        BuildersKt__Builders_commonKt.launch$default(this.j, new CoroutineName("connection[" + this.a + ']'), null, new A3(this, null), 2, null);
    }

    public final boolean b() {
        String str;
        Map map = this.f;
        return ((map == null || (str = (String) map.get("wnd")) == null) ? null : Long.valueOf(Long.parseLong(str))) != null;
    }

    @Override // com.tm.M1
    public final Object a(byte[] bArr, C0150o1 c0150o1) {
        Object send = this.p.send(bArr, c0150o1);
        return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    @Override // com.tm.M1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object a(Continuation continuation) {
        C3 c3;
        int i;
        D3 d3;
        if (continuation instanceof C3) {
            c3 = (C3) continuation;
            int i2 = c3.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c3.d = i2 - Integer.MIN_VALUE;
                Object obj = c3.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c3.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    boolean z = X0.a;
                    X0.a("TcpProxySession", "TcpProxySession: stop: [" + this.a + ']');
                    ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) this.p, (CancellationException) null, 1, (Object) null);
                    CoroutineScopeKt.cancel$default(this.k, null, 1, null);
                    CoroutineScopeKt.cancel$default(this.i, null, 1, null);
                    Timer timer = this.n;
                    if (timer != null) {
                        timer.cancel();
                    }
                    this.n = null;
                    try {
                        C0166q3 c0166q3 = this.e;
                        String valueOf = String.valueOf(this.a);
                        c3.a = this;
                        c3.d = 1;
                        c0166q3.getClass();
                        if (CoroutineScopeKt.coroutineScope(new C0117j3(c0166q3, valueOf, null), c3) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        d3 = this;
                    } catch (Throwable th) {
                        th = th;
                        d3 = this;
                        boolean z2 = X0.a;
                        X0.a("TcpProxySession", "TcpProxySession: stopped error: [" + d3.a + "]: " + th);
                        if (d3.b()) {
                        }
                        CoroutineScopeKt.cancel$default(d3.j, null, 1, null);
                        d3.h.close();
                        return Unit.INSTANCE;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    d3 = c3.a;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th2) {
                        th = th2;
                        boolean z22 = X0.a;
                        X0.a("TcpProxySession", "TcpProxySession: stopped error: [" + d3.a + "]: " + th);
                        if (d3.b()) {
                        }
                        CoroutineScopeKt.cancel$default(d3.j, null, 1, null);
                        d3.h.close();
                        return Unit.INSTANCE;
                    }
                }
                if (d3.b()) {
                    G3 g3 = d3.g;
                    if (g3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tcpWindowHandler");
                        g3 = null;
                    }
                    Timer timer2 = g3.k;
                    if (timer2 != null) {
                        timer2.cancel();
                    }
                    g3.k = null;
                }
                CoroutineScopeKt.cancel$default(d3.j, null, 1, null);
                d3.h.close();
                return Unit.INSTANCE;
            }
        }
        c3 = new C3(this, continuation);
        Object obj2 = c3.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c3.d;
        if (i != 0) {
        }
        if (d3.b()) {
        }
        CoroutineScopeKt.cancel$default(d3.j, null, 1, null);
        d3.h.close();
        return Unit.INSTANCE;
    }

    @Override // com.tm.M1
    public final void a(C0092g ack) {
        Intrinsics.checkNotNullParameter(ack, "packet");
        if (b()) {
            G3 g3 = this.g;
            if (g3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tcpWindowHandler");
                g3 = null;
            }
            g3.getClass();
            Intrinsics.checkNotNullParameter(ack, "ack");
            boolean z = X0.a;
            X0.a("TcpWindowHandler", "TcpWindowHandler: onAck: [" + g3.a + "] last=" + g3.e + ", new=" + ack.b);
            g3.e = ack.b;
        }
    }

    public static final void a(D3 d3) {
        Timer timer = d3.n;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = TimersKt.timer("[" + d3.a + "]killtimer", false);
        timer2.schedule(new B3(d3), 120000L, 120000L);
        d3.n = timer2;
    }
}
