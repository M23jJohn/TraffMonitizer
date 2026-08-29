package com.tm;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class N extends ContinuationImpl {
    public /* synthetic */ Object a;
    public int b;
    public final /* synthetic */ O c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public N(O o, Continuation continuation) {
        super(continuation);
        this.c = o;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.a = obj;
        this.b |= Integer.MIN_VALUE;
        return this.c.emit(null, this);
    }
}
