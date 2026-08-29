package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.w, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0203w implements FlowCollector {
    public final /* synthetic */ X a;

    public C0203w(X x) {
        this.a = x;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        X x;
        AbstractC0058b0 abstractC0058b0;
        C0 c0 = (C0) obj;
        EnumC0127l enumC0127l = ((C0148o) this.a.b.a.getValue()).i;
        if (Intrinsics.areEqual(c0, C0211x0.b)) {
            this.a.add(G0.a);
        } else if (Intrinsics.areEqual(c0, C0093g0.b)) {
            if (enumC0127l == EnumC0127l.SDK || !Intrinsics.areEqual(this.a.getState(), Z.b)) {
                this.a.add(C0072d0.a);
            }
        } else if (Intrinsics.areEqual(c0, C0218y0.b)) {
            if (enumC0127l == EnumC0127l.SDK) {
                x = this.a;
                abstractC0058b0 = G0.a;
            } else if (!Intrinsics.areEqual(this.a.getState(), Z.b)) {
                x = this.a;
                abstractC0058b0 = C0072d0.a;
            }
            x.add(abstractC0058b0);
        }
        return Unit.INSTANCE;
    }
}
