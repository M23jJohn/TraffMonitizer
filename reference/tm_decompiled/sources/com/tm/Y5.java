package com.tm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Unit;
import kotlin.concurrent.TimersKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* loaded from: classes3.dex */
public final class Y5 extends M1 {
    public Z5 f;
    public final ExecutorCoroutineDispatcher g;
    public final CoroutineScope h;
    public final CoroutineScope i;
    public Timer j;
    public final AtomicLong k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y5(int i, String host, int i2, P1 listener, C0166q3 socketRepository) {
        super(i, host, i2, listener, socketRepository);
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(socketRepository, "socketRepository");
        U5 u5 = new U5(CoroutineExceptionHandler.INSTANCE, i, listener);
        ExecutorCoroutineDispatcher newSingleThreadContext = ThreadPoolDispatcherKt.newSingleThreadContext("sessionReadLoop");
        this.g = newSingleThreadContext;
        this.h = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(u5));
        this.i = CoroutineScopeKt.CoroutineScope(newSingleThreadContext.plus(u5));
        CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().limitedParallelism(1).plus(u5));
        this.k = new AtomicLong(0L);
    }

    @Override // com.tm.M1
    public final void a(C0092g packet) {
        Intrinsics.checkNotNullParameter(packet, "packet");
    }

    @Override // com.tm.M1
    public final Object a(byte[] data, C0150o1 c0150o1) {
        try {
            Z5 z5 = this.f;
            if (z5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connection");
                z5 = null;
            }
            z5.getClass();
            Intrinsics.checkNotNullParameter(data, "data");
            int length = data.length;
            DatagramSocket datagramSocket = z5.a;
            Intrinsics.checkNotNull(datagramSocket);
            DatagramPacket datagramPacket = new DatagramPacket(data, length, datagramSocket.getRemoteSocketAddress());
            DatagramSocket datagramSocket2 = z5.a;
            if (datagramSocket2 != null) {
                datagramSocket2.send(datagramPacket);
            }
            this.k.incrementAndGet();
        } catch (Throwable th) {
            ((H1) this.d).a(this.a, th);
        }
        return Unit.INSTANCE;
    }

    @Override // com.tm.M1
    public final void a() {
        BuildersKt__Builders_commonKt.launch$default(this.h, new CoroutineName("connection[" + this.a + ']'), null, new V5(this, null), 2, null);
    }

    @Override // com.tm.M1
    public final Object a(Continuation continuation) {
        Timer timer = this.j;
        if (timer != null) {
            timer.cancel();
        }
        this.j = null;
        CoroutineScopeKt.cancel$default(this.i, null, 1, null);
        BuildersKt__Builders_commonKt.launch$default(this.h, null, null, new X5(this, null), 3, null);
        this.g.close();
        return Unit.INSTANCE;
    }

    public static final void a(Y5 y5) {
        Timer timer = y5.j;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = TimersKt.timer("[" + y5.a + "]killtimer", false);
        timer2.schedule(new W5(y5), 120000L, 120000L);
        y5.j = timer2;
    }
}
