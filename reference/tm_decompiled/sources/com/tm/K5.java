package com.tm;

import com.traffmonetizer.sdk.TraffmonetizerSdk;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class K5 extends ContinuationImpl {
    public /* synthetic */ Object a;
    public final /* synthetic */ TraffmonetizerSdk b;
    public int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K5(TraffmonetizerSdk traffmonetizerSdk, Continuation continuation) {
        super(continuation);
        this.b = traffmonetizerSdk;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.a = obj;
        this.c |= Integer.MIN_VALUE;
        return this.b.getDeviceName(this);
    }
}
