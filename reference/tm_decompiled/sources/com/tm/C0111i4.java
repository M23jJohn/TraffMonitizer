package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* renamed from: com.tm.i4, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0111i4 implements Flow {
    public final /* synthetic */ Flow a;

    public C0111i4(C0090f4 c0090f4) {
        this.a = c0090f4;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.a.collect(new C0104h4(flowCollector), continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
