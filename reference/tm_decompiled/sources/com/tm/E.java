package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class E extends ContinuationImpl {
    public X a;
    public V0 b;
    public /* synthetic */ Object c;
    public final /* synthetic */ X d;
    public int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E(X x, Continuation continuation) {
        super(continuation);
        this.d = x;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.c = obj;
        this.e |= Integer.MIN_VALUE;
        return X.a(this.d, (V0) null, (Emitter) null, this);
    }
}
