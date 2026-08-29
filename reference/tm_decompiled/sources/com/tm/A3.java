package com.tm;

import androidx.compose.runtime.ComposerKt;
import java.io.BufferedOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* loaded from: classes3.dex */
public final class A3 extends SuspendLambda implements Function2 {
    public D3 a;
    public int b;
    public final /* synthetic */ D3 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A3(D3 d3, Continuation continuation) {
        super(2, continuation);
        this.c = d3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new A3(this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new A3(this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutineScope;
        D3 d3;
        String str;
        String str2;
        InetAddress inetAddress;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.b;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.c.b()) {
                    D3 d32 = this.c;
                    int i2 = d32.a;
                    Map map = d32.f;
                    Long valueOf = (map == null || (str2 = (String) map.get("wnd")) == null) ? null : Long.valueOf(Long.parseLong(str2));
                    Intrinsics.checkNotNull(valueOf);
                    long longValue = valueOf.longValue();
                    Map map2 = this.c.f;
                    d32.g = new G3(i2, longValue, (map2 == null || (str = (String) map2.get("wndi")) == null) ? ComposerKt.invocationKey : Integer.parseInt(str), new C0228z3(this.c));
                }
                D3 d33 = this.c;
                C0166q3 c0166q3 = d33.e;
                String valueOf2 = String.valueOf(d33.a);
                D3 d34 = this.c;
                String str3 = d34.b;
                int i3 = d34.c;
                this.a = d33;
                this.b = 1;
                c0166q3.getClass();
                coroutineScope = CoroutineScopeKt.coroutineScope(new C0159p3(c0166q3, valueOf2, str3, i3, null), this);
                if (coroutineScope == coroutine_suspended) {
                    return coroutine_suspended;
                }
                d3 = d33;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                d3 = this.a;
                ResultKt.throwOnFailure(obj);
                coroutineScope = obj;
            }
            C0075d3 c0075d3 = (C0075d3) coroutineScope;
            d3.getClass();
            Intrinsics.checkNotNullParameter(c0075d3, "<set-?>");
            d3.l = c0075d3;
            D3 d35 = this.c;
            C0075d3 c0075d32 = this.c.l;
            if (c0075d32 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connection");
                c0075d32 = null;
            }
            Socket socket = c0075d32.b;
            d35.m = new BufferedOutputStream(socket != null ? socket.getOutputStream() : null);
            D3 d36 = this.c;
            P1 p1 = d36.d;
            int i4 = d36.a;
            C0075d3 c0075d33 = d36.l;
            if (c0075d33 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connection");
                c0075d33 = null;
            }
            Socket socket2 = c0075d33.b;
            byte[] address = (socket2 == null || (inetAddress = socket2.getInetAddress()) == null) ? null : inetAddress.getAddress();
            if (address == null) {
                address = new byte[0];
            }
            byte[] remoteIp = address;
            C0075d3 c0075d34 = this.c.l;
            if (c0075d34 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connection");
                c0075d34 = null;
            }
            Socket socket3 = c0075d34.b;
            Integer valueOf3 = socket3 != null ? Integer.valueOf(socket3.getPort()) : null;
            int intValue = valueOf3 != null ? valueOf3.intValue() : 0;
            H1 h1 = (H1) p1;
            h1.getClass();
            Intrinsics.checkNotNullParameter(remoteIp, "remoteIp");
            boolean z = X0.a;
            X0.a("ProxyBloc", "onConnectionSuccess: [" + i4 + "]: active sessions = " + h1.h.size());
            BuildersKt__Builders_commonKt.launch$default(h1.g, null, null, new C0115j1(h1, i4, remoteIp, intValue, null), 3, null);
            D3 d37 = this.c;
            BuildersKt__Builders_commonKt.launch$default(d37.k, null, null, new C0207w3(d37, null), 3, null);
            D3 d38 = this.c;
            BuildersKt__Builders_commonKt.launch$default(d38.i, null, null, new C0214x3(d38, null), 3, null);
            D3.a(this.c);
        } catch (Throwable ex) {
            D3 d39 = this.c;
            P1 p12 = d39.d;
            int i5 = d39.a;
            H1 h12 = (H1) p12;
            h12.getClass();
            Intrinsics.checkNotNullParameter(ex, "ex");
            boolean z2 = X0.a;
            X0.a("ProxyBloc", "onConnectionError: [" + i5 + "]: " + ex);
            BuildersKt__Builders_commonKt.launch$default(h12.g, null, null, new C0108i1(h12, i5, ex, null), 3, null);
        }
        return Unit.INSTANCE;
    }
}
