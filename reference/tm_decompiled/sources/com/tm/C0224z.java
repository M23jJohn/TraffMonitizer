package com.tm;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* renamed from: com.tm.z, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0224z extends ContinuationImpl {
    public /* synthetic */ Object a;
    public final /* synthetic */ X b;
    public int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0224z(X x, Continuation continuation) {
        super(continuation);
        this.b = x;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.a = obj;
        this.c |= Integer.MIN_VALUE;
        return X.a(this.b, (String) null, this);
    }
}
