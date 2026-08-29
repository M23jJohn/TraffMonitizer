package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class C extends ContinuationImpl {
    public X a;
    public N0 b;
    public Emitter c;
    public /* synthetic */ Object d;
    public final /* synthetic */ X e;
    public int f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C(X x, Continuation continuation) {
        super(continuation);
        this.e = x;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.d = obj;
        this.f |= Integer.MIN_VALUE;
        return X.a(this.e, (N0) null, (Emitter) null, this);
    }
}
