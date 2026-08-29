package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class I extends ContinuationImpl {
    public X a;
    public C0082e3 b;
    public Emitter c;
    public /* synthetic */ Object d;
    public final /* synthetic */ X e;
    public int f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(X x, Continuation continuation) {
        super(continuation);
        this.e = x;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.d = obj;
        this.f |= Integer.MIN_VALUE;
        return X.a(this.e, (C0082e3) null, (Emitter) null, this);
    }
}
