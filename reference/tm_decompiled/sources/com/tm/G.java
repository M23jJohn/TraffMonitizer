package com.tm;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class G extends ContinuationImpl {
    public X a;
    public /* synthetic */ Object b;
    public final /* synthetic */ X c;
    public int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public G(X x, Continuation continuation) {
        super(continuation);
        this.c = x;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.b = obj;
        this.d |= Integer.MIN_VALUE;
        return X.b(this.c, null, this);
    }
}
