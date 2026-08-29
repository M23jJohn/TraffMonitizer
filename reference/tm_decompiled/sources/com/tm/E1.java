package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class E1 extends ContinuationImpl {
    public H1 a;
    public /* synthetic */ Object b;
    public final /* synthetic */ H1 c;
    public int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E1(H1 h1, Continuation continuation) {
        super(continuation);
        this.c = h1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.b = obj;
        this.d |= Integer.MIN_VALUE;
        return H1.a(this.c, (Emitter) null, this);
    }
}
