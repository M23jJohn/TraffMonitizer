package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.e2, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0081e2 implements FlowCollector {
    public static final C0081e2 a = new C0081e2();

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        boolean z = X0.a;
        X0.a("AppService", "ConnectivityBloc = " + ((C0) obj).a);
        return Unit.INSTANCE;
    }
}
