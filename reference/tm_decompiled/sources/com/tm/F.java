package com.tm;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes3.dex */
public final class F extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ X b;
    public final /* synthetic */ V0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F(X x, V0 v0, Continuation continuation) {
        super(2, continuation);
        this.b = x;
        this.c = v0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new F(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new F(this.b, this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0049, code lost:
    
        if (r12 == r0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005b, code lost:
    
        if (r12 == r0) goto L18;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        C0075d3 connection;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (((C0148o) this.b.b.a.getValue()).f.length() > 0) {
                X x = this.b;
                String str = ((C0148o) x.b.a.getValue()).f;
                this.a = 1;
                obj = X.a(x, str, this);
            } else {
                X x2 = this.b;
                String str2 = this.c.a;
                this.a = 2;
                obj = X.a(x2, str2, this);
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            ResultKt.throwOnFailure(obj);
            connection = (C0075d3) obj;
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            connection = (C0075d3) obj;
        }
        X x3 = this.b;
        C5 c5 = x3.d;
        CoroutineScope outScope = x3.f;
        if (outScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("socketScope");
            outScope = null;
        }
        c5.getClass();
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(outScope, "outScope");
        CoroutineScopeKt.cancel$default(c5.d, null, 1, null);
        c5.d = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(c5.c));
        H3 h3 = c5.a;
        h3.getClass();
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(outScope, "outScope");
        Socket socket = connection.b;
        OutputStream outputStream = socket != null ? socket.getOutputStream() : null;
        Intrinsics.checkNotNull(outputStream);
        h3.a = new C0116j2(outputStream);
        Socket socket2 = connection.b;
        InputStream inputStream = socket2 != null ? socket2.getInputStream() : null;
        Intrinsics.checkNotNull(inputStream);
        h3.b = new X1(inputStream, outScope);
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(x3.j));
        x3.g = CoroutineScope;
        if (CoroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScope, new CoroutineName("IncomingPing"), null, new T(x3, null), 2, null);
        }
        this.b.add(C0061b3.a);
        return Unit.INSTANCE;
    }
}
