package com.tm;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* renamed from: com.tm.g3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0096g3 extends SuspendLambda implements Function2 {
    public final /* synthetic */ Z5 a;
    public final /* synthetic */ String b;
    public final /* synthetic */ int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0096g3(Z5 z5, String str, int i, Continuation continuation) {
        super(2, continuation);
        this.a = z5;
        this.b = str;
        this.c = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0096g3(this.a, this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((C0096g3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        Z5 z5 = this.a;
        String host = this.b;
        int i = this.c;
        z5.getClass();
        Intrinsics.checkNotNullParameter(host, "host");
        if (z5.a == null) {
            try {
                DatagramSocket datagramSocket = new DatagramSocket();
                z5.a = datagramSocket;
                Intrinsics.checkNotNull(datagramSocket);
                datagramSocket.connect(new InetSocketAddress(host, i));
            } catch (Throwable th) {
                z5.a = null;
                throw th;
            }
        }
        return Unit.INSTANCE;
    }
}
