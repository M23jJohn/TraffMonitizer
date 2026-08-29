package com.tm;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class F3 extends ContinuationImpl {
    public G3 a;
    public int b;
    public long c;
    public /* synthetic */ Object d;
    public final /* synthetic */ G3 e;
    public int f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F3(G3 g3, Continuation continuation) {
        super(continuation);
        this.e = g3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.d = obj;
        this.f |= Integer.MIN_VALUE;
        return this.e.a(this);
    }
}
