package com.tm;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* renamed from: com.tm.i0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0107i0 extends ContinuationImpl {
    public C0190u0 a;
    public /* synthetic */ Object b;
    public final /* synthetic */ C0190u0 c;
    public int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0107i0(C0190u0 c0190u0, Continuation continuation) {
        super(continuation);
        this.c = c0190u0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.b = obj;
        this.d |= Integer.MIN_VALUE;
        return C0190u0.a(this.c, null, this);
    }
}
