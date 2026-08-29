package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.l0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0128l0 implements FlowCollector {
    public final /* synthetic */ C0190u0 a;

    public C0128l0(C0190u0 c0190u0) {
        this.a = c0190u0;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        this.a.add(new C0197v0(((Boolean) obj).booleanValue()));
        return Unit.INSTANCE;
    }
}
