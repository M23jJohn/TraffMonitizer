package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.a2, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0053a2 implements FlowCollector {
    public static final C0053a2 a = new C0053a2();

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        boolean z = X0.a;
        X0.a("AppService", "ServiceBloc = " + ((P2) obj).a);
        return Unit.INSTANCE;
    }
}
