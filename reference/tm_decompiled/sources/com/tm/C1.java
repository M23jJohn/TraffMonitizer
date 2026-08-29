package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class C1 implements FlowCollector {
    public final /* synthetic */ H1 a;

    public C1(H1 h1) {
        this.a = h1;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        C0092g c0092g = (C0092g) obj;
        H1 h1 = this.a;
        int i = H1.i;
        h1.getClass();
        boolean z = X0.a;
        X0.a("ProxyBloc", "onSessionAck: [" + c0092g.a + "]: " + c0092g.b + " bytes");
        M1 m1 = (M1) h1.h.get(Integer.valueOf(c0092g.a));
        if (m1 != null) {
            m1.a(c0092g);
        }
        return Unit.INSTANCE;
    }
}
