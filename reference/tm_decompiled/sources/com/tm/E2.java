package com.tm;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class E2 extends ContinuationImpl {
    public J2 a;
    public /* synthetic */ Object b;
    public final /* synthetic */ J2 c;
    public int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E2(J2 j2, Continuation continuation) {
        super(continuation);
        this.c = j2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.b = obj;
        this.d |= Integer.MIN_VALUE;
        return J2.a(this.c, null, this);
    }
}
