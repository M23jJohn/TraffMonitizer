package com.tm;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class K extends ContinuationImpl {
    public /* synthetic */ Object a;
    public final /* synthetic */ X b;
    public int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K(X x, Continuation continuation) {
        super(continuation);
        this.b = x;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.a = obj;
        this.c |= Integer.MIN_VALUE;
        return this.b.b(this);
    }
}
