package com.tm;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* renamed from: com.tm.m0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0135m0 extends ContinuationImpl {
    public /* synthetic */ Object a;
    public int b;
    public final /* synthetic */ C0142n0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0135m0(C0142n0 c0142n0, Continuation continuation) {
        super(continuation);
        this.c = c0142n0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.a = obj;
        this.b |= Integer.MIN_VALUE;
        return this.c.emit(null, this);
    }
}
