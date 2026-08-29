package com.tm;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* renamed from: com.tm.j2, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0116j2 {
    public final BufferedOutputStream a;
    public final ExecutorCoroutineDispatcher b;
    public final CoroutineScope c;
    public final Channel d;

    public C0116j2(OutputStream outputStream) {
        Intrinsics.checkNotNullParameter(outputStream, "outputStream");
        this.a = new BufferedOutputStream(outputStream);
        C0109i2 c0109i2 = new C0109i2(CoroutineExceptionHandler.INSTANCE);
        ExecutorCoroutineDispatcher newSingleThreadContext = ThreadPoolDispatcherKt.newSingleThreadContext("socketWriteLoop");
        this.b = newSingleThreadContext;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(newSingleThreadContext.plus(c0109i2));
        this.c = CoroutineScope;
        Channel Channel$default = ChannelKt.Channel$default(0, null, null, 7, null);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new C0102h2(Channel$default, this, null), 3, null);
        this.d = Channel$default;
    }

    public static final void a(C0116j2 c0116j2, Z0 z0) {
        c0116j2.getClass();
        try {
            if (z0 instanceof C0059b1) {
                z0.a(c0116j2.a);
            } else {
                c0116j2.a.write(z0.b());
            }
            boolean z = X0.a;
            X0.a("ApiSender", "--> flush(" + c0116j2.d.isEmpty() + ") " + z0);
            if (c0116j2.d.isEmpty()) {
                c0116j2.a.flush();
            }
        } catch (Throwable th) {
            boolean z2 = X0.a;
            X0.a("ApiSender", "Exception: " + th.getMessage(), th);
            throw th;
        }
    }
}
