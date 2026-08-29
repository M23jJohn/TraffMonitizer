package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.n2, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0144n2 implements FlowCollector {
    public final /* synthetic */ J2 a;

    public C0144n2(J2 j2) {
        this.a = j2;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        AbstractC0079e0 abstractC0079e0 = (AbstractC0079e0) obj;
        boolean z = X0.a;
        X0.a("ServiceBloc", "onConnectionChanged: " + abstractC0079e0.a);
        if (abstractC0079e0 instanceof Z) {
            this.a.add(N2.a);
        } else {
            if (Intrinsics.areEqual(abstractC0079e0, C0051a0.b) ? true : abstractC0079e0 instanceof C0065c0) {
                this.a.add(R2.a);
            }
        }
        return Unit.INSTANCE;
    }
}
