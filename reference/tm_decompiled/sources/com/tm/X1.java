package com.tm;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* loaded from: classes3.dex */
public final class X1 {
    public final InputStream a;
    public final ExecutorCoroutineDispatcher b;
    public final CoroutineScope c;
    public final MutableSharedFlow d;

    public X1(InputStream inputStream, CoroutineScope outScope) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(outScope, "outScope");
        this.a = inputStream;
        this.d = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        W1 w1 = new W1(CoroutineExceptionHandler.INSTANCE, outScope);
        ExecutorCoroutineDispatcher newSingleThreadContext = ThreadPoolDispatcherKt.newSingleThreadContext("socketReadLoop");
        this.b = newSingleThreadContext;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(newSingleThreadContext.plus(w1));
        this.c = CoroutineScope;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScope, new CoroutineName("Receive"), null, new V1(this, null), 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x019e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Z0 a(X1 x1, EnumC0052a1 type) {
        Z0 c0059b1;
        Z0 rVar;
        Z0 c0189u;
        Z0 c0066c1;
        x1.getClass();
        DataInputStream stream = new DataInputStream(x1.a);
        long currentTimeMillis = System.currentTimeMillis();
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(stream, "stream");
        byte[] instanceId = new byte[16];
        stream.read(instanceId);
        switch (type) {
            case EF0:
                throw new b6();
            case b:
            case c:
            case EF5:
            case e:
            case f:
            case i:
            case j:
            case EF6:
            case l:
            case m:
                throw new a6(type);
            case d:
            case g:
            case h:
            case n:
            case o:
            case p:
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(instanceId, "instanceId");
                Intrinsics.checkNotNullParameter(stream, "stream");
                int a = AbstractC0099h.a(stream);
                int ordinal = type.ordinal();
                if (ordinal != 4) {
                    if (ordinal == 7) {
                        rVar = new r(instanceId, a, AbstractC0099h.c(stream), AbstractC0099h.a(stream));
                    } else if (ordinal != 8) {
                        switch (ordinal) {
                            case 15:
                                rVar = new R5(instanceId, a, AbstractC0099h.c(stream), AbstractC0099h.a(stream));
                                break;
                            case 16:
                                String c = AbstractC0099h.c(stream);
                                int a2 = AbstractC0099h.a(stream);
                                int a3 = AbstractC0099h.a(stream);
                                LinkedHashMap linkedHashMap = new LinkedHashMap();
                                for (int i = 0; i < a3; i++) {
                                    linkedHashMap.put(AbstractC0099h.c(stream), AbstractC0099h.c(stream));
                                }
                                c0189u = new C0189u(instanceId, a, c, a2, linkedHashMap);
                                c0059b1 = c0189u;
                                break;
                            case 17:
                                rVar = new C0085f(a, AbstractC0099h.b(stream), instanceId);
                                break;
                            default:
                                throw new a6(type);
                        }
                    } else {
                        c0059b1 = new H0(instanceId, a);
                    }
                    c0059b1 = rVar;
                } else {
                    byte[] bArr = new byte[AbstractC0099h.a(stream)];
                    stream.readFully(bArr);
                    c0059b1 = new C0059b1(instanceId, a, bArr, currentTimeMillis);
                }
                if (c0059b1 instanceof C0059b1) {
                    boolean z = X0.a;
                    int length = ((C0059b1) c0059b1).d.length;
                    Intrinsics.checkNotNullParameter("Receiver", "tag");
                    Intrinsics.checkNotNullParameter("<--", "message");
                }
                boolean z2 = X0.a;
                X0.a("Receiver", "<-- " + c0059b1);
                return c0059b1;
            case k:
                c0059b1 = new C0073d1(instanceId);
                if (c0059b1 instanceof C0059b1) {
                }
                boolean z22 = X0.a;
                X0.a("Receiver", "<-- " + c0059b1);
                return c0059b1;
            case q:
            case r:
            case EF1:
            case t:
            case u:
            case x:
            case y:
            case A:
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(instanceId, "instanceId");
                Intrinsics.checkNotNullParameter(stream, "stream");
                boolean readBoolean = stream.readBoolean();
                String c2 = AbstractC0099h.c(stream);
                int ordinal2 = type.ordinal();
                if (ordinal2 == 18) {
                    c0059b1 = new P0(instanceId, readBoolean, c2);
                } else if (ordinal2 == 19) {
                    c0059b1 = new C0141n(instanceId, readBoolean, c2);
                } else if (ordinal2 == 22) {
                    c0059b1 = new C0064c(instanceId, readBoolean, c2);
                } else if (ordinal2 != 23) {
                    if (ordinal2 == 26) {
                        c0189u = new C0172r3(instanceId, readBoolean, c2, AbstractC0099h.b(stream), AbstractC0099h.b(stream), AbstractC0099h.b(stream));
                    } else if (ordinal2 == 27) {
                        Intrinsics.checkNotNullParameter(stream, "<this>");
                        DoubleCompanionObject doubleCompanionObject = DoubleCompanionObject.INSTANCE;
                        double longBitsToDouble = Double.longBitsToDouble(AbstractC0099h.b(stream));
                        Intrinsics.checkNotNullParameter(stream, "<this>");
                        c0189u = new C0120k(instanceId, readBoolean, c2, longBitsToDouble, Double.longBitsToDouble(AbstractC0099h.b(stream)));
                    } else {
                        if (ordinal2 != 29) {
                            throw new a6(type);
                        }
                        c0059b1 = new Z2(instanceId, readBoolean, c2);
                    }
                    c0059b1 = c0189u;
                } else {
                    c0059b1 = new C0078e(instanceId, readBoolean, c2);
                }
                if (c0059b1 instanceof C0059b1) {
                }
                boolean z222 = X0.a;
                X0.a("Receiver", "<-- " + c0059b1);
                return c0059b1;
            case s:
            case v:
            case w:
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(instanceId, "instanceId");
                Intrinsics.checkNotNullParameter(stream, "stream");
                boolean readBoolean2 = stream.readBoolean();
                String c3 = AbstractC0099h.c(stream);
                int a4 = AbstractC0099h.a(stream);
                int ordinal3 = type.ordinal();
                if (ordinal3 == 21) {
                    c0066c1 = new C0066c1(instanceId, readBoolean2, c3, a4);
                } else {
                    if (ordinal3 != 25) {
                        throw new a6(type);
                    }
                    c0066c1 = new I0(instanceId, readBoolean2, c3, a4);
                }
                c0059b1 = c0066c1;
                if (c0059b1 instanceof C0059b1) {
                }
                boolean z2222 = X0.a;
                X0.a("Receiver", "<-- " + c0059b1);
                return c0059b1;
            case z:
                c0059b1 = new C0080e1(instanceId);
                if (c0059b1 instanceof C0059b1) {
                }
                boolean z22222 = X0.a;
                X0.a("Receiver", "<-- " + c0059b1);
                return c0059b1;
            case B:
                c0059b1 = new K0(instanceId, AbstractC0099h.c(stream));
                if (c0059b1 instanceof C0059b1) {
                }
                boolean z222222 = X0.a;
                X0.a("Receiver", "<-- " + c0059b1);
                return c0059b1;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
