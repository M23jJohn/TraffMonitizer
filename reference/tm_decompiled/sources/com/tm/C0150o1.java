package com.tm;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* renamed from: com.tm.o1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0150o1 extends ContinuationImpl {
    public K1 a;
    public long b;
    public /* synthetic */ Object c;
    public final /* synthetic */ H1 d;
    public int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0150o1(H1 h1, Continuation continuation) {
        super(continuation);
        this.d = h1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.c = obj;
        this.e |= Integer.MIN_VALUE;
        return H1.a(this.d, (K1) null, this);
    }
}
