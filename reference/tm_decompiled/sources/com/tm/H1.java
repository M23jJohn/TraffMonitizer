package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Bloc;
import com.ptrbrynt.kotlin_bloc.core.Emitter;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import kotlinx.coroutines.flow.FlowKt;

/* loaded from: classes3.dex */
public final class H1 extends Bloc implements P1 {
    public static final /* synthetic */ int i = 0;
    public final C5 a;
    public final O1 b;
    public final C0157p1 c;
    public ExecutorCoroutineDispatcher d;
    public ExecutorCoroutineDispatcher e;
    public CoroutineScope f;
    public CoroutineScope g;
    public final ConcurrentHashMap h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H1(J2 serviceBloc, C5 traffmonetizerRepository, O1 sessionFactory, C0155p configRepository) {
        super(T1.a);
        Intrinsics.checkNotNullParameter(serviceBloc, "serviceBloc");
        Intrinsics.checkNotNullParameter(traffmonetizerRepository, "traffmonetizerRepository");
        Intrinsics.checkNotNullParameter(sessionFactory, "sessionFactory");
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        this.a = traffmonetizerRepository;
        this.b = sessionFactory;
        C0157p1 c0157p1 = new C0157p1(CoroutineExceptionHandler.INSTANCE);
        this.c = c0157p1;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        this.d = ThreadPoolDispatcherKt.newSingleThreadContext("proxyReceive");
        this.e = ThreadPoolDispatcherKt.newSingleThreadContext("proxySend");
        this.f = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(c0157p1));
        this.g = CoroutineScopeKt.CoroutineScope(this.e.plus(c0157p1));
        this.h = new ConcurrentHashMap();
        FlowKt.launchIn(FlowKt.onEach(new C0177s1(transformEvents(((Bloc) this).eventFlow)), new C0184t1(new C0087f1(this, null), this, null)), getBlocScope());
        BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new C0101h1(serviceBloc, this, null), 3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(H1 h1, K1 k1, Continuation continuation) {
        C0150o1 c0150o1;
        int i2;
        K1 k12;
        long j;
        if (continuation instanceof C0150o1) {
            c0150o1 = (C0150o1) continuation;
            int i3 = c0150o1.e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                c0150o1.e = i3 - Integer.MIN_VALUE;
                Object obj = c0150o1.c;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = c0150o1.e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    long currentTimeMillis = System.currentTimeMillis();
                    M1 m1 = (M1) h1.h.get(Boxing.boxInt(k1.a));
                    boolean z = X0.a;
                    StringBuilder sb = new StringBuilder("onSessionPacket: [");
                    sb.append(k1.a);
                    sb.append("]: [");
                    sb.append(currentTimeMillis);
                    sb.append("] ");
                    sb.append(k1.b.length);
                    sb.append(" bytes, session=");
                    sb.append(m1 != null);
                    X0.a("ProxyBloc", sb.toString());
                    int length = k1.b.length;
                    Intrinsics.checkNotNullParameter("ProxyBloc", "tag");
                    Intrinsics.checkNotNullParameter("<--", "message");
                    if (m1 != null) {
                        byte[] bArr = k1.b;
                        c0150o1.a = k1;
                        c0150o1.b = currentTimeMillis;
                        c0150o1.e = 1;
                        if (m1.a(bArr, c0150o1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    k12 = k1;
                    j = currentTimeMillis;
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    j = c0150o1.b;
                    k12 = c0150o1.a;
                    ResultKt.throwOnFailure(obj);
                }
                boolean z2 = X0.a;
                X0.a("ProxyBloc", "onSessionPacket: [" + k12.a + "]: [" + j + "] end " + k12.b.length + " bytes");
                return Unit.INSTANCE;
            }
        }
        c0150o1 = new C0150o1(h1, continuation);
        Object obj2 = c0150o1.c;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = c0150o1.e;
        if (i2 != 0) {
        }
        boolean z22 = X0.a;
        X0.a("ProxyBloc", "onSessionPacket: [" + k12.a + "]: [" + j + "] end " + k12.b.length + " bytes");
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0056, code lost:
    
        if (r6.emit(r5, r0) != r1) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0058, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
    
        if (r5.a(r0) == r1) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object b(H1 h1, Emitter emitter, Continuation continuation) {
        G1 g1;
        int i2;
        h1.getClass();
        if (continuation instanceof G1) {
            g1 = (G1) continuation;
            int i3 = g1.d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                g1.d = i3 - Integer.MIN_VALUE;
                Object obj = g1.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = g1.d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    g1.a = emitter;
                    g1.d = 1;
                } else {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    emitter = g1.a;
                    ResultKt.throwOnFailure(obj);
                }
                T1 t1 = T1.a;
                g1.a = null;
                g1.d = 2;
            }
        }
        g1 = new G1(h1, continuation);
        Object obj2 = g1.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = g1.d;
        if (i2 != 0) {
        }
        T1 t12 = T1.a;
        g1.a = null;
        g1.d = 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(H1 h1, Emitter emitter, Continuation continuation) {
        E1 e1;
        int i2;
        h1.getClass();
        if (continuation instanceof E1) {
            e1 = (E1) continuation;
            int i3 = e1.d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                e1.d = i3 - Integer.MIN_VALUE;
                Object obj = e1.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = e1.d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    L1 l1 = L1.a;
                    e1.a = h1;
                    e1.d = 1;
                    if (emitter.emit(l1, e1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    h1 = e1.a;
                    ResultKt.throwOnFailure(obj);
                }
                h1.getClass();
                h1.d = ThreadPoolDispatcherKt.newSingleThreadContext("proxyReceive");
                h1.e = ThreadPoolDispatcherKt.newSingleThreadContext("proxySend");
                h1.f = CoroutineScopeKt.CoroutineScope(h1.d.plus(h1.c));
                h1.g = CoroutineScopeKt.CoroutineScope(h1.e.plus(h1.c));
                BuildersKt__Builders_commonKt.launch$default(h1.f, new CoroutineName("connect"), null, new C0198v1(h1, null), 2, null);
                BuildersKt__Builders_commonKt.launch$default(h1.f, new CoroutineName("connectUdp"), null, new C0212x1(h1, null), 2, null);
                BuildersKt__Builders_commonKt.launch$default(h1.f, new CoroutineName("eof"), null, new C0226z1(h1, null), 2, null);
                BuildersKt__Builders_commonKt.launch$default(h1.f, new CoroutineName("packet"), null, new B1(h1, null), 2, null);
                BuildersKt__Builders_commonKt.launch$default(h1.f, new CoroutineName("ack"), null, new D1(h1, null), 2, null);
                return Unit.INSTANCE;
            }
        }
        e1 = new E1(h1, continuation);
        Object obj2 = e1.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = e1.d;
        if (i2 != 0) {
        }
        h1.getClass();
        h1.d = ThreadPoolDispatcherKt.newSingleThreadContext("proxyReceive");
        h1.e = ThreadPoolDispatcherKt.newSingleThreadContext("proxySend");
        h1.f = CoroutineScopeKt.CoroutineScope(h1.d.plus(h1.c));
        h1.g = CoroutineScopeKt.CoroutineScope(h1.e.plus(h1.c));
        BuildersKt__Builders_commonKt.launch$default(h1.f, new CoroutineName("connect"), null, new C0198v1(h1, null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(h1.f, new CoroutineName("connectUdp"), null, new C0212x1(h1, null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(h1.f, new CoroutineName("eof"), null, new C0226z1(h1, null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(h1.f, new CoroutineName("packet"), null, new B1(h1, null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(h1.f, new CoroutineName("ack"), null, new D1(h1, null), 2, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object a(Continuation continuation) {
        F1 f1;
        int i2;
        Iterator it;
        H1 h1;
        if (continuation instanceof F1) {
            f1 = (F1) continuation;
            int i3 = f1.e;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                f1.e = i3 - Integer.MIN_VALUE;
                Object obj = f1.c;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = f1.e;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScopeKt.cancel$default(this.g, null, 1, null);
                    CoroutineScopeKt.cancel$default(this.f, null, 1, null);
                    this.d.close();
                    this.e.close();
                    Collection values = this.h.values();
                    Intrinsics.checkNotNullExpressionValue(values, "sessions.values");
                    it = values.iterator();
                    h1 = this;
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    it = f1.b;
                    h1 = f1.a;
                    ResultKt.throwOnFailure(obj);
                }
                while (it.hasNext()) {
                    M1 m1 = (M1) it.next();
                    boolean z = X0.a;
                    X0.a("ProxyBloc", "stop: session[" + m1.a + ']');
                    f1.a = h1;
                    f1.b = it;
                    f1.e = 1;
                    if (m1.a(f1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                h1.h.clear();
                return Unit.INSTANCE;
            }
        }
        f1 = new F1(this, continuation);
        Object obj2 = f1.c;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = f1.e;
        if (i2 != 0) {
        }
        while (it.hasNext()) {
        }
        h1.h.clear();
        return Unit.INSTANCE;
    }

    public static final void a(H1 listener, I1 connectRequest) {
        H1 h1;
        M1 y5;
        boolean z = X0.a;
        X0.a("ProxyBloc", "onNewSession: type=" + connectRequest.getClass().getSimpleName() + " [" + connectRequest.a() + ']');
        O1 o1 = listener.b;
        o1.getClass();
        Intrinsics.checkNotNullParameter(connectRequest, "connectRequest");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (connectRequest instanceof C0200v3) {
            C0200v3 c0200v3 = (C0200v3) connectRequest;
            h1 = listener;
            y5 = new D3(c0200v3.a, c0200v3.b, c0200v3.c, h1, o1.a, c0200v3.d);
        } else if (connectRequest instanceof S5) {
            S5 s5 = (S5) connectRequest;
            h1 = listener;
            y5 = new Y5(s5.a, s5.b, s5.c, listener, o1.a);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        h1.h.put(Integer.valueOf(y5.a), y5);
        y5.a();
    }

    public final void a(int i2, boolean z) {
        boolean z2 = X0.a;
        X0.a("ProxyBloc", "onSessionEnd: [" + i2 + ']');
        M1 m1 = (M1) this.h.remove(Integer.valueOf(i2));
        BuildersKt__Builders_commonKt.launch$default(this.f, null, null, new C0129l1(m1, null), 3, null);
        X0.a("ProxyBloc", "onSessionEnd: closed [" + i2 + "], active sessions = " + this.h.size());
        if (m1 == null || !z) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(this.g, null, null, new C0136m1(this, i2, null), 3, null);
    }

    public final void a(int i2, Throwable ex) {
        Intrinsics.checkNotNullParameter(ex, "ex");
        boolean z = X0.a;
        X0.a("ProxyBloc", "onSessionError: [" + i2 + "]: " + ex);
        BuildersKt__Builders_commonKt.launch$default(this.g, null, null, new C0143n1(this, i2, null), 3, null);
    }

    public final Object a(int i2, byte[] packet, long j, Continuation continuation) {
        C5 c5 = this.a;
        Intrinsics.checkNotNullParameter(packet, "packet");
        Object a = c5.a.a(new C0059b1(((C0148o) c5.b.a.getValue()).c, i2, packet, j), continuation);
        if (a != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            a = Unit.INSTANCE;
        }
        return a == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? a : Unit.INSTANCE;
    }
}
