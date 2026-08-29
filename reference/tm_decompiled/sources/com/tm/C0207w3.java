package com.tm;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* renamed from: com.tm.w3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0207w3 extends SuspendLambda implements Function2 {
    public byte[] a;
    public D3 b;
    public byte[] c;
    public long d;
    public int e;
    public /* synthetic */ Object f;
    public final /* synthetic */ D3 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0207w3(D3 d3, Continuation continuation) {
        super(2, continuation);
        this.g = d3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        C0207w3 c0207w3 = new C0207w3(this.g, continuation);
        c0207w3.f = obj;
        return c0207w3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        C0207w3 c0207w3 = new C0207w3(this.g, (Continuation) obj2);
        c0207w3.f = (CoroutineScope) obj;
        return c0207w3.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00f9 A[Catch: all -> 0x0118, TryCatch #2 {all -> 0x0118, blocks: (B:7:0x001d, B:9:0x00f3, B:11:0x00f9, B:13:0x00fd, B:14:0x0101, B:15:0x0106, B:16:0x0051, B:18:0x0057, B:21:0x0066, B:23:0x006a, B:24:0x0070, B:26:0x007b, B:28:0x0081, B:30:0x0094, B:32:0x0098, B:33:0x009c, B:58:0x0110, B:59:0x0117, B:62:0x0060, B:74:0x004e), top: B:2:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0057 A[Catch: all -> 0x0118, TryCatch #2 {all -> 0x0118, blocks: (B:7:0x001d, B:9:0x00f3, B:11:0x00f9, B:13:0x00fd, B:14:0x0101, B:15:0x0106, B:16:0x0051, B:18:0x0057, B:21:0x0066, B:23:0x006a, B:24:0x0070, B:26:0x007b, B:28:0x0081, B:30:0x0094, B:32:0x0098, B:33:0x009c, B:58:0x0110, B:59:0x0117, B:62:0x0060, B:74:0x004e), top: B:2:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ba A[Catch: all -> 0x00d1, TRY_LEAVE, TryCatch #1 {all -> 0x00d1, blocks: (B:37:0x00b2, B:39:0x00ba), top: B:36:0x00b2 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0120  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00ef -> B:9:0x00f3). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        CoroutineScope coroutineScope2;
        byte[] bArr;
        long j;
        CoroutineScope coroutineScope3;
        D3 d3;
        byte[] bArr2;
        byte[] bArr3;
        long j2;
        P1 p1;
        int i;
        long j3;
        D3 d32;
        CoroutineScope coroutineScope4;
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.e;
        try {
        } catch (Throwable th) {
            th = th;
            coroutineScope = coroutineScope2;
        }
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope2 = (CoroutineScope) this.f;
            bArr = new byte[8192];
            if (CoroutineScopeKt.isActive(coroutineScope2)) {
            }
            return Unit.INSTANCE;
        }
        if (i2 == 1) {
            long j4 = this.d;
            bArr2 = this.c;
            d32 = this.b;
            bArr = this.a;
            coroutineScope = (CoroutineScope) this.f;
            try {
                ResultKt.throwOnFailure(obj);
                coroutineScope4 = coroutineScope;
                j3 = j4;
                obj2 = obj;
                if (((Boolean) obj2).booleanValue()) {
                }
                coroutineScope3 = coroutineScope4;
                j = j3;
                d3 = d32;
                bArr3 = bArr;
                j2 = j;
                p1 = d3.d;
                i = d3.a;
                this.f = coroutineScope3;
                this.a = bArr3;
                this.b = d3;
                this.c = bArr2;
                this.e = 2;
                if (((H1) p1).a(i, bArr2, j2, this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            } catch (Throwable th2) {
                th = th2;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    D3 d33 = this.g;
                    ((H1) d33.d).a(d33.a, th);
                }
                return Unit.INSTANCE;
            }
        }
        if (i2 != 2) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        byte[] bArr4 = this.c;
        D3 d34 = this.b;
        byte[] bArr5 = this.a;
        coroutineScope2 = (CoroutineScope) this.f;
        ResultKt.throwOnFailure(obj);
        bArr = bArr5;
        if (d34.b()) {
            G3 g3 = d34.g;
            if (g3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tcpWindowHandler");
                g3 = null;
            }
            g3.b(bArr4.length);
        }
        d34.o.incrementAndGet();
        if (CoroutineScopeKt.isActive(coroutineScope2)) {
            C0075d3 c0075d3 = this.g.l;
            if (c0075d3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connection");
                c0075d3 = null;
            }
            Socket socket = c0075d3.b;
            DataInputStream dataInputStream = new DataInputStream(socket != null ? socket.getInputStream() : null);
            D3 d35 = this.g;
            int read = dataInputStream.read(bArr);
            if (read < 0 || !CoroutineScopeKt.isActive(coroutineScope2)) {
                throw new IOException("EndOfStream");
            }
            j3 = System.currentTimeMillis();
            bArr2 = Arrays.copyOf(bArr, read);
            Intrinsics.checkNotNullExpressionValue(bArr2, "copyOf(this, newSize)");
            if (d35.b()) {
                G3 g32 = d35.g;
                if (g32 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tcpWindowHandler");
                    g32 = null;
                }
                this.f = coroutineScope2;
                this.a = bArr;
                this.b = d35;
                this.c = bArr2;
                this.d = j3;
                this.e = 1;
                obj2 = g32.a(this);
                if (obj2 != coroutine_suspended) {
                    CoroutineScope coroutineScope5 = coroutineScope2;
                    d32 = d35;
                    coroutineScope4 = coroutineScope5;
                    try {
                        if (((Boolean) obj2).booleanValue()) {
                            boolean z = X0.a;
                            int length = bArr2.length;
                            Intrinsics.checkNotNullParameter("TcpProxySession", "tag");
                            Intrinsics.checkNotNullParameter("<-- tcpWindowWait", "message");
                        }
                        coroutineScope3 = coroutineScope4;
                        j = j3;
                        d3 = d32;
                        bArr3 = bArr;
                        j2 = j;
                        p1 = d3.d;
                        i = d3.a;
                        this.f = coroutineScope3;
                        this.a = bArr3;
                        this.b = d3;
                        this.c = bArr2;
                        this.e = 2;
                        if (((H1) p1).a(i, bArr2, j2, this) != coroutine_suspended) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        coroutineScope = coroutineScope4;
                        if (CoroutineScopeKt.isActive(coroutineScope)) {
                        }
                        return Unit.INSTANCE;
                    }
                }
                return coroutine_suspended;
            }
            try {
                coroutineScope3 = coroutineScope2;
                j = j3;
                d3 = d35;
                p1 = d3.d;
                i = d3.a;
                this.f = coroutineScope3;
                this.a = bArr3;
                this.b = d3;
                this.c = bArr2;
                this.e = 2;
                if (((H1) p1).a(i, bArr2, j2, this) != coroutine_suspended) {
                    bArr4 = bArr2;
                    d34 = d3;
                    bArr = bArr3;
                    coroutineScope2 = coroutineScope3;
                    if (d34.b()) {
                    }
                    d34.o.incrementAndGet();
                    if (CoroutineScopeKt.isActive(coroutineScope2)) {
                    }
                }
                return coroutine_suspended;
            } catch (Throwable th4) {
                th = th4;
                coroutineScope = coroutineScope3;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
                return Unit.INSTANCE;
            }
            bArr3 = bArr;
            j2 = j;
        }
        return Unit.INSTANCE;
    }
}
