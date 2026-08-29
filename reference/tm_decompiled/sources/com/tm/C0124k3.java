package com.tm;

import java.net.DatagramSocket;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* renamed from: com.tm.k3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0124k3 extends SuspendLambda implements Function2 {
    public final /* synthetic */ Z5 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0124k3(Z5 z5, Continuation continuation) {
        super(2, continuation);
        this.a = z5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0124k3(this.a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0124k3(this.a, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        Z5 z5 = this.a;
        DatagramSocket datagramSocket = z5.a;
        if (datagramSocket != null) {
            if (!datagramSocket.isClosed()) {
                datagramSocket.close();
            }
            z5.a = null;
        }
        return Unit.INSTANCE;
    }
}
