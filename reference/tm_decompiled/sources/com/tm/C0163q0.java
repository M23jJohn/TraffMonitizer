package com.tm;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* renamed from: com.tm.q0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0163q0 extends ContinuationImpl {
    public /* synthetic */ Object a;
    public int b;
    public final /* synthetic */ C0169r0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0163q0(C0169r0 c0169r0, Continuation continuation) {
        super(continuation);
        this.c = c0169r0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.a = obj;
        this.b |= Integer.MIN_VALUE;
        return this.c.emit(null, this);
    }
}
