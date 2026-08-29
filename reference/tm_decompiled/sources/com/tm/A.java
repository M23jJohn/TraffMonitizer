package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class A extends ContinuationImpl {
    public X a;
    public /* synthetic */ Object b;
    public final /* synthetic */ X c;
    public int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A(X x, Continuation continuation) {
        super(continuation);
        this.c = x;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.b = obj;
        this.d |= Integer.MIN_VALUE;
        return X.a(this.c, (Emitter) null, this);
    }
}
