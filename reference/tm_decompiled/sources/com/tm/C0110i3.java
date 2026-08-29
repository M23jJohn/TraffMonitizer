package com.tm;

import java.net.Socket;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* renamed from: com.tm.i3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0110i3 extends SuspendLambda implements Function2 {
    public final /* synthetic */ C0075d3 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0110i3(C0075d3 c0075d3, Continuation continuation) {
        super(2, continuation);
        this.a = c0075d3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0110i3(this.a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0110i3(this.a, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        C0075d3 c0075d3 = this.a;
        Socket socket = c0075d3.b;
        if (socket != null) {
            if (!socket.isClosed()) {
                socket.close();
            }
            c0075d3.b = null;
        }
        return Unit.INSTANCE;
    }
}
