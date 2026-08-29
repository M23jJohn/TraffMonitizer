package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Bloc;
import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;

/* loaded from: classes3.dex */
public final class X extends Bloc {
    public static final /* synthetic */ int k = 0;
    public final S0 a;
    public final C0155p b;
    public final C0166q3 c;
    public final C5 d;
    public final C0190u0 e;
    public CoroutineScope f;
    public CoroutineScope g;
    public CoroutineScope h;
    public CoroutineScope i;
    public final M j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public X(S0 loadBalancer, C0155p configRepository, C0166q3 socketRepository, C5 traffmonetizerRepository, C0190u0 connectivityBloc) {
        super(C0051a0.b);
        Intrinsics.checkNotNullParameter(loadBalancer, "loadBalancer");
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        Intrinsics.checkNotNullParameter(socketRepository, "socketRepository");
        Intrinsics.checkNotNullParameter(traffmonetizerRepository, "traffmonetizerRepository");
        Intrinsics.checkNotNullParameter(connectivityBloc, "connectivityBloc");
        this.a = loadBalancer;
        this.b = configRepository;
        this.c = socketRepository;
        this.d = traffmonetizerRepository;
        this.e = connectivityBloc;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        this.j = new M(CoroutineExceptionHandler.INSTANCE, this);
        FlowKt.launchIn(FlowKt.onEach(new P(transformEvents(((Bloc) this).eventFlow)), new Q(new C0196v(this, null), this, null)), getBlocScope());
        BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new C0210x(this, null), 3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(X x, String str, Continuation continuation) {
        C0224z c0224z;
        int i;
        try {
            if (continuation instanceof C0224z) {
                c0224z = (C0224z) continuation;
                int i2 = c0224z.c;
                if ((i2 & Integer.MIN_VALUE) != 0) {
                    c0224z.c = i2 - Integer.MIN_VALUE;
                    Object obj = c0224z.a;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = c0224z.c;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        boolean z = X0.a;
                        X0.a("Connection", "connectSocket: connect to [" + str + ':' + ((C0148o) x.b.a.getValue()).e + ']');
                        C0166q3 c0166q3 = x.c;
                        int i3 = ((C0148o) x.b.a.getValue()).e;
                        c0224z.c = 1;
                        c0166q3.getClass();
                        obj = CoroutineScopeKt.coroutineScope(new C0145n3(c0166q3, str, i3, null), c0224z);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return (C0075d3) obj;
                }
            }
            if (i != 0) {
            }
            return (C0075d3) obj;
        } catch (Throwable th) {
            throw new C0054a3(th);
        }
        c0224z = new C0224z(x, continuation);
        Object obj2 = c0224z.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0224z.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object b(X x, Emitter emitter, Continuation continuation) {
        G g;
        int i;
        CoroutineScope coroutineScope;
        CoroutineScope coroutineScope2;
        x.getClass();
        if (continuation instanceof G) {
            g = (G) continuation;
            int i2 = g.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                g.d = i2 - Integer.MIN_VALUE;
                Object obj = g.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = g.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    L0 l0 = L0.b;
                    g.a = x;
                    g.d = 1;
                    if (emitter.emit(l0, g) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    x = g.a;
                    ResultKt.throwOnFailure(obj);
                }
                coroutineScope = x.f;
                if (coroutineScope != null) {
                    Intrinsics.throwUninitializedPropertyAccessException("socketScope");
                    coroutineScope2 = null;
                } else {
                    coroutineScope2 = coroutineScope;
                }
                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new H(x, null), 3, null);
                return Unit.INSTANCE;
            }
        }
        g = new G(x, continuation);
        Object obj2 = g.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = g.d;
        if (i != 0) {
        }
        coroutineScope = x.f;
        if (coroutineScope != null) {
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new H(x, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object b(Continuation continuation) {
        K k2;
        int i;
        try {
            if (continuation instanceof K) {
                k2 = (K) continuation;
                int i2 = k2.c;
                if ((i2 & Integer.MIN_VALUE) != 0) {
                    k2.c = i2 - Integer.MIN_VALUE;
                    Object obj = k2.a;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = k2.c;
                    if (i == 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return obj;
                    }
                    ResultKt.throwOnFailure(obj);
                    if (((C0148o) this.b.a.getValue()).f.length() > 0) {
                        return ((C0148o) this.b.a.getValue()).f;
                    }
                    String str = ((C0148o) this.b.a.getValue()).d;
                    S0 s0 = this.a;
                    k2.c = 1;
                    Object a = s0.a(str, k2);
                    return a == coroutine_suspended ? coroutine_suspended : a;
                }
            }
            if (i == 0) {
            }
        } catch (Throwable th) {
            throw new T0(th);
        }
        k2 = new K(this, continuation);
        Object obj2 = k2.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = k2.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004a A[Catch: all -> 0x0057, TRY_ENTER, TryCatch #0 {all -> 0x0057, blocks: (B:10:0x0025, B:11:0x003f, B:16:0x004a, B:17:0x0056, B:22:0x0034), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(X x, Continuation continuation) {
        L l;
        Object obj;
        int i;
        try {
            if (continuation instanceof L) {
                l = (L) continuation;
                int i2 = l.c;
                if ((i2 & Integer.MIN_VALUE) != 0) {
                    l.c = i2 - Integer.MIN_VALUE;
                    obj = l.a;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = l.c;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        C5 c5 = x.d;
                        l.c = 1;
                        obj = c5.b(l);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    if (!((Boolean) obj).booleanValue()) {
                        return Unit.INSTANCE;
                    }
                    throw new M0(new IllegalStateException("Hello not successful"));
                }
            }
            if (i != 0) {
            }
            if (!((Boolean) obj).booleanValue()) {
            }
        } catch (Throwable th) {
            throw new M0(th);
        }
        l = new L(x, continuation);
        obj = l.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = l.c;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:0|1|(2:3|(5:5|6|7|(1:(1:(5:11|12|13|14|15)(2:18|19))(1:20))(3:24|(1:26)|27)|21))|31|6|7|(0)(0)|21) */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006a, code lost:
    
        if (r7 != r1) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006c, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005f, code lost:
    
        if (r6.emit(r7, r0) == r1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0078, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0079, code lost:
    
        r5.add(new com.tm.U0(r6));
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(X x, Emitter emitter, Continuation continuation) {
        A a;
        int i;
        x.getClass();
        if (continuation instanceof A) {
            a = (A) continuation;
            int i2 = a.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                a.d = i2 - Integer.MIN_VALUE;
                Object obj = a.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = a.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    boolean z = X0.a;
                    X0.a("Connection", "onConnect: ");
                    CoroutineScope coroutineScope = x.i;
                    if (coroutineScope != null) {
                        CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
                    }
                    x.i = null;
                    W0 w0 = W0.b;
                    a.a = x;
                    a.d = 1;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        x = a.a;
                        ResultKt.throwOnFailure(obj);
                        x.add(new V0((String) obj));
                        return Unit.INSTANCE;
                    }
                    x = a.a;
                    ResultKt.throwOnFailure(obj);
                }
                a.a = x;
                a.d = 2;
                obj = x.b(a);
            }
        }
        a = new A(x, continuation);
        Object obj2 = a.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = a.d;
        if (i != 0) {
        }
        a.a = x;
        a.d = 2;
        obj2 = x.b(a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0089, code lost:
    
        if (r8.emit(r9, r0) != r1) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x008b, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0073, code lost:
    
        if (r6.a(r0) == r1) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(X x, N0 n0, Emitter emitter, Continuation continuation) {
        C c;
        int i;
        x.getClass();
        if (continuation instanceof C) {
            c = (C) continuation;
            int i2 = c.f;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c.f = i2 - Integer.MIN_VALUE;
                Object obj = c.d;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c.f;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    boolean z = X0.a;
                    X0.a("Connection", "onHelloFailed: " + n0.a);
                    CoroutineScope coroutineScope = x.g;
                    if (coroutineScope != null) {
                        CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
                    }
                    x.g = null;
                    c.a = x;
                    c.b = n0;
                    c.c = emitter;
                    c.f = 1;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        x = c.a;
                        ResultKt.throwOnFailure(obj);
                        x.a();
                        return Unit.INSTANCE;
                    }
                    emitter = c.c;
                    n0 = c.b;
                    x = c.a;
                    ResultKt.throwOnFailure(obj);
                }
                C0065c0 c0065c0 = new C0065c0(n0.a);
                c.a = x;
                c.b = null;
                c.c = null;
                c.f = 2;
            }
        }
        c = new C(x, continuation);
        Object obj2 = c.d;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c.f;
        if (i != 0) {
        }
        C0065c0 c0065c02 = new C0065c0(n0.a);
        c.a = x;
        c.b = null;
        c.c = null;
        c.f = 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(X x, U0 u0, Emitter emitter, Continuation continuation) {
        D d;
        int i;
        x.getClass();
        if (continuation instanceof D) {
            d = (D) continuation;
            int i2 = d.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                d.d = i2 - Integer.MIN_VALUE;
                Object obj = d.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = d.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    boolean z = X0.a;
                    X0.a("Connection", "onLoadBalancerFailed: " + u0.a);
                    C0065c0 c0065c0 = new C0065c0(u0.a);
                    d.a = x;
                    d.d = 1;
                    if (emitter.emit(c0065c0, d) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    x = d.a;
                    ResultKt.throwOnFailure(obj);
                }
                x.a();
                return Unit.INSTANCE;
            }
        }
        d = new D(x, continuation);
        Object obj2 = d.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = d.d;
        if (i != 0) {
        }
        x.a();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(X x, V0 v0, Emitter emitter, Continuation continuation) {
        E e;
        int i;
        CoroutineScope CoroutineScope;
        CoroutineScope coroutineScope;
        x.getClass();
        if (continuation instanceof E) {
            e = (E) continuation;
            int i2 = e.e;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                e.e = i2 - Integer.MIN_VALUE;
                Object obj = e.c;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = e.e;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    boolean z = X0.a;
                    X0.a("Connection", "onLoadBalancerResolved: [" + v0.a + ']');
                    C0068c3 c0068c3 = C0068c3.b;
                    e.a = x;
                    e.b = v0;
                    e.e = 1;
                    if (emitter.emit(c0068c3, e) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    v0 = e.b;
                    x = e.a;
                    ResultKt.throwOnFailure(obj);
                }
                CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(x.j));
                x.f = CoroutineScope;
                if (CoroutineScope != null) {
                    Intrinsics.throwUninitializedPropertyAccessException("socketScope");
                    coroutineScope = null;
                } else {
                    coroutineScope = CoroutineScope;
                }
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new F(x, v0, null), 3, null);
                return Unit.INSTANCE;
            }
        }
        e = new E(x, continuation);
        Object obj2 = e.c;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = e.e;
        if (i != 0) {
        }
        CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(x.j));
        x.f = CoroutineScope;
        if (CoroutineScope != null) {
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new F(x, v0, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0092, code lost:
    
        if (r8.emit(r9, r0) != r1) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0094, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x007c, code lost:
    
        if (r6.a(r0) == r1) goto L27;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(X x, C0082e3 c0082e3, Emitter emitter, Continuation continuation) {
        I i;
        int i2;
        x.getClass();
        if (continuation instanceof I) {
            i = (I) continuation;
            int i3 = i.f;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                i.f = i3 - Integer.MIN_VALUE;
                Object obj = i.d;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = i.f;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    boolean z = X0.a;
                    X0.a("Connection", "onSocketConnectionBroken: " + c0082e3.a);
                    CoroutineScope coroutineScope = x.g;
                    if (coroutineScope != null) {
                        CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
                    }
                    x.g = null;
                    CoroutineScope coroutineScope2 = x.h;
                    if (coroutineScope2 != null) {
                        CoroutineScopeKt.cancel$default(coroutineScope2, null, 1, null);
                    }
                    x.h = null;
                    i.a = x;
                    i.b = c0082e3;
                    i.c = emitter;
                    i.f = 1;
                } else {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        x = i.a;
                        ResultKt.throwOnFailure(obj);
                        x.a();
                        return Unit.INSTANCE;
                    }
                    emitter = i.c;
                    c0082e3 = i.b;
                    x = i.a;
                    ResultKt.throwOnFailure(obj);
                }
                C0065c0 c0065c0 = new C0065c0(c0082e3.a);
                i.a = x;
                i.b = null;
                i.c = null;
                i.f = 2;
            }
        }
        i = new I(x, continuation);
        Object obj2 = i.d;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = i.f;
        if (i2 != 0) {
        }
        C0065c0 c0065c02 = new C0065c0(c0082e3.a);
        i.a = x;
        i.b = null;
        i.c = null;
        i.f = 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(X x, C0089f3 c0089f3, Emitter emitter, Continuation continuation) {
        J j;
        int i;
        x.getClass();
        if (continuation instanceof J) {
            j = (J) continuation;
            int i2 = j.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                j.d = i2 - Integer.MIN_VALUE;
                Object obj = j.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = j.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    boolean z = X0.a;
                    X0.a("Connection", "onLoadBalancerFailed: " + c0089f3.a);
                    C0065c0 c0065c0 = new C0065c0(c0089f3.a);
                    j.a = x;
                    j.d = 1;
                    if (emitter.emit(c0065c0, j) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    x = j.a;
                    ResultKt.throwOnFailure(obj);
                }
                x.a();
                return Unit.INSTANCE;
            }
        }
        j = new J(x, continuation);
        Object obj2 = j.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = j.d;
        if (i != 0) {
        }
        x.a();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|1|(2:3|(7:5|6|7|(1:(1:10)(2:16|17))(3:18|19|(1:21))|11|12|13))|23|6|7|(0)(0)|11|12|13) */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object a(Continuation continuation) {
        C0217y c0217y;
        int i;
        if (continuation instanceof C0217y) {
            c0217y = (C0217y) continuation;
            int i2 = c0217y.c;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c0217y.c = i2 - Integer.MIN_VALUE;
                Object obj = c0217y.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c0217y.c;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    this.d.a();
                    C0166q3 c0166q3 = this.c;
                    c0217y.c = 1;
                    c0166q3.getClass();
                    if (CoroutineScopeKt.coroutineScope(new C0117j3(c0166q3, "main", null), c0217y) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
        c0217y = new C0217y(this, continuation);
        Object obj2 = c0217y.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0217y.c;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }

    public final void a() {
        CoroutineScope coroutineScope = this.i;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.i = null;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(this.j));
        this.i = CoroutineScope;
        if (CoroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScope, new CoroutineName("Reconnect"), null, new W(this, null), 2, null);
        }
    }
}
