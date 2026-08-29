package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.j0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0114j0 implements FlowCollector {
    public final /* synthetic */ C0190u0 a;

    public C0114j0(C0190u0 c0190u0) {
        this.a = c0190u0;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        this.a.add(new E0(((Boolean) obj).booleanValue()));
        return Unit.INSTANCE;
    }
}
