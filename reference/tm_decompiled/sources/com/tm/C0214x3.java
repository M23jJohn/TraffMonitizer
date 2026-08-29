package com.tm;

import java.io.BufferedOutputStream;
import java.net.Socket;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;

/* renamed from: com.tm.x3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0214x3 extends SuspendLambda implements Function2 {
    public D3 a;
    public ReceiveChannel b;
    public ChannelIterator c;
    public int d;
    public /* synthetic */ Object e;
    public final /* synthetic */ D3 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0214x3(D3 d3, Continuation continuation) {
        super(2, continuation);
        this.f = d3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        C0214x3 c0214x3 = new C0214x3(this.f, continuation);
        c0214x3.e = obj;
        return c0214x3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        C0214x3 c0214x3 = new C0214x3(this.f, (Continuation) obj2);
        c0214x3.e = (CoroutineScope) obj;
        return c0214x3.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0044 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004e A[Catch: all -> 0x00c3, TRY_LEAVE, TryCatch #0 {all -> 0x00c3, blocks: (B:6:0x0015, B:7:0x0045, B:9:0x004e, B:32:0x0034, B:42:0x00ac, B:44:0x00b2, B:50:0x002c, B:11:0x0054, B:14:0x005f, B:16:0x0063, B:18:0x006b, B:20:0x006f, B:21:0x0075, B:23:0x0080, B:24:0x0083, B:26:0x0089, B:28:0x008d, B:29:0x0094, B:31:0x0099, B:36:0x009f, B:37:0x00aa, B:40:0x0059), top: B:2:0x0007, inners: #2 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0042 -> B:7:0x0045). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ReceiveChannel receiveChannel;
        D3 d3;
        ChannelIterator it;
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.d;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope2 = (CoroutineScope) this.e;
                D3 d32 = this.f;
                receiveChannel = d32.p;
                d3 = d32;
                it = receiveChannel.iterator();
                coroutineScope = coroutineScope2;
                this.e = coroutineScope;
                this.a = d3;
                this.b = receiveChannel;
                this.c = it;
                this.d = 1;
                obj = it.hasNext(this);
                if (obj == coroutine_suspended) {
                }
                G3 g3 = null;
                if (((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = this.c;
                receiveChannel = this.b;
                d3 = this.a;
                coroutineScope = (CoroutineScope) this.e;
                ResultKt.throwOnFailure(obj);
                G3 g32 = null;
                if (((Boolean) obj).booleanValue()) {
                    byte[] bArr = (byte[]) it.next();
                    try {
                    } catch (Throwable th) {
                        if (CoroutineScopeKt.isActive(coroutineScope)) {
                            ((H1) d3.d).a(d3.a, th);
                        }
                    }
                    C0075d3 c0075d3 = d3.l;
                    if (c0075d3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("connection");
                        c0075d3 = null;
                    }
                    Socket socket = c0075d3.b;
                    if ((socket != null ? socket.getOutputStream() : null) == null) {
                        throw new T2(String.valueOf(d3.a));
                    }
                    BufferedOutputStream bufferedOutputStream = d3.m;
                    if (bufferedOutputStream == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bufferedOutputStream");
                        bufferedOutputStream = null;
                    }
                    bufferedOutputStream.write(bArr);
                    if (d3.p.isEmpty()) {
                        bufferedOutputStream.flush();
                    }
                    if (d3.b()) {
                        G3 g33 = d3.g;
                        if (g33 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("tcpWindowHandler");
                        } else {
                            g32 = g33;
                        }
                        g32.a(bArr.length);
                    }
                    d3.o.incrementAndGet();
                    this.e = coroutineScope;
                    this.a = d3;
                    this.b = receiveChannel;
                    this.c = it;
                    this.d = 1;
                    obj = it.hasNext(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    G3 g322 = null;
                    if (((Boolean) obj).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel, null);
                        return Unit.INSTANCE;
                    }
                }
            }
        } finally {
        }
    }
}
