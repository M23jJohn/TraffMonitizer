package com.tm;

import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.q2, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0165q2 implements FlowCollector {
    public final /* synthetic */ J2 a;

    public C0165q2(J2 j2) {
        this.a = j2;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        Triple triple = (Triple) obj;
        boolean booleanValue = ((Boolean) triple.component1()).booleanValue();
        AbstractC0079e0 abstractC0079e0 = (AbstractC0079e0) triple.component2();
        C0 c0 = (C0) triple.component3();
        boolean z = X0.a;
        X0.a("ServiceBloc", "onListenersUpdated: active=" + booleanValue + ", connection=" + abstractC0079e0.a + ", connectivity=" + c0.a);
        if (booleanValue) {
            Z z2 = Z.b;
            if (Intrinsics.areEqual(abstractC0079e0, z2) && Intrinsics.areEqual(c0, C0093g0.b)) {
                this.a.add(N2.a);
            } else if (!Intrinsics.areEqual(abstractC0079e0, z2)) {
                this.a.add(new L2(EnumC0123k2.NO_CONNECTION));
            } else if (Intrinsics.areEqual(c0, C0218y0.b)) {
                this.a.add(new L2(EnumC0123k2.NO_WLAN));
            }
        } else {
            this.a.add(R2.a);
        }
        return Unit.INSTANCE;
    }
}
