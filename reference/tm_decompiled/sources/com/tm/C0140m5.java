package com.tm;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* renamed from: com.tm.m5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0140m5 extends ContinuationImpl {
    public C5 a;
    public /* synthetic */ Object b;
    public final /* synthetic */ C5 c;
    public int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0140m5(C5 c5, Continuation continuation) {
        super(continuation);
        this.c = c5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.b = obj;
        this.d |= Integer.MIN_VALUE;
        return this.c.d(this);
    }
}
