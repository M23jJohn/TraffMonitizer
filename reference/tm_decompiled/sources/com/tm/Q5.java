package com.tm;

import com.traffmonetizer.sdk.TraffmonetizerSdkImpl;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class Q5 extends ContinuationImpl {
    public /* synthetic */ Object a;
    public final /* synthetic */ TraffmonetizerSdkImpl b;
    public int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q5(TraffmonetizerSdkImpl traffmonetizerSdkImpl, Continuation continuation) {
        super(continuation);
        this.b = traffmonetizerSdkImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.a = obj;
        this.c |= Integer.MIN_VALUE;
        return this.b.a(this);
    }
}
