package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes3.dex */
public final class Y1 implements FlowCollector {
    public static final Y1 a = new Y1();

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        boolean z = X0.a;
        X0.a("AppService", "ConnectionBloc = " + ((AbstractC0079e0) obj).a);
        return Unit.INSTANCE;
    }
}
