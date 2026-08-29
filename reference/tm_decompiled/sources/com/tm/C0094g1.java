package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.g1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0094g1 implements FlowCollector {
    public final /* synthetic */ H1 a;

    public C0094g1(H1 h1) {
        this.a = h1;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        P2 p2 = (P2) obj;
        if (Intrinsics.areEqual(p2, O2.b)) {
            this.a.add(Q1.a);
        } else if (Intrinsics.areEqual(p2, S2.b)) {
            this.a.add(S1.a);
        }
        return Unit.INSTANCE;
    }
}
