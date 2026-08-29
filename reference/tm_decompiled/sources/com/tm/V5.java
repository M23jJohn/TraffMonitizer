package com.tm;

import java.net.DatagramSocket;
import java.net.InetAddress;
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
public final class V5 extends SuspendLambda implements Function2 {
    public Y5 a;
    public int b;
    public final /* synthetic */ Y5 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public V5(Y5 y5, Continuation continuation) {
        super(2, continuation);
        this.c = y5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new V5(this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new V5(this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutineScope;
        Y5 y5;
        InetAddress inetAddress;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.b;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Y5 y52 = this.c;
                C0166q3 c0166q3 = y52.e;
                String valueOf = String.valueOf(y52.a);
                Y5 y53 = this.c;
                String str = y53.b;
                int i2 = y53.c;
                this.a = y52;
                this.b = 1;
                c0166q3.getClass();
                coroutineScope = CoroutineScopeKt.coroutineScope(new C0103h3(c0166q3, valueOf, str, i2, null), this);
                if (coroutineScope == coroutine_suspended) {
                    return coroutine_suspended;
                }
                y5 = y52;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                y5 = this.a;
                ResultKt.throwOnFailure(obj);
                coroutineScope = obj;
            }
            Z5 z5 = (Z5) coroutineScope;
            y5.getClass();
            Intrinsics.checkNotNullParameter(z5, "<set-?>");
            y5.f = z5;
            Y5 y54 = this.c;
            P1 p1 = y54.d;
            int i3 = y54.a;
            Z5 z52 = y54.f;
            if (z52 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connection");
                z52 = null;
            }
            DatagramSocket datagramSocket = z52.a;
            byte[] address = (datagramSocket == null || (inetAddress = datagramSocket.getInetAddress()) == null) ? null : inetAddress.getAddress();
            if (address == null) {
                address = new byte[0];
            }
            byte[] remoteIp = address;
            Z5 z53 = this.c.f;
            if (z53 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connection");
                z53 = null;
            }
            DatagramSocket datagramSocket2 = z53.a;
            Integer valueOf2 = datagramSocket2 != null ? Integer.valueOf(datagramSocket2.getPort()) : null;
            int intValue = valueOf2 != null ? valueOf2.intValue() : 0;
            H1 h1 = (H1) p1;
            h1.getClass();
            Intrinsics.checkNotNullParameter(remoteIp, "remoteIp");
            boolean z = X0.a;
            X0.a("ProxyBloc", "onConnectionSuccess: [" + i3 + "]: active sessions = " + h1.h.size());
            BuildersKt__Builders_commonKt.launch$default(h1.g, null, null, new C0115j1(h1, i3, remoteIp, intValue, null), 3, null);
            Y5 y55 = this.c;
            BuildersKt__Builders_commonKt.launch$default(y55.i, null, null, new T5(y55, null), 3, null);
            Y5.a(this.c);
        } catch (Throwable ex) {
            Y5 y56 = this.c;
            P1 p12 = y56.d;
            int i4 = y56.a;
            H1 h12 = (H1) p12;
            h12.getClass();
            Intrinsics.checkNotNullParameter(ex, "ex");
            boolean z2 = X0.a;
            X0.a("ProxyBloc", "onConnectionError: [" + i4 + "]: " + ex);
            BuildersKt__Builders_commonKt.launch$default(h12.g, null, null, new C0108i1(h12, i4, ex, null), 3, null);
        }
        return Unit.INSTANCE;
    }
}
