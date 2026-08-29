package com.tm;

import java.util.Iterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class F1 extends ContinuationImpl {
    public H1 a;
    public Iterator b;
    public /* synthetic */ Object c;
    public final /* synthetic */ H1 d;
    public int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F1(H1 h1, Continuation continuation) {
        super(continuation);
        this.d = h1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.c = obj;
        this.e |= Integer.MIN_VALUE;
        return this.d.a(this);
    }
}
