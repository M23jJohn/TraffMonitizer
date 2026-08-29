package com.tm;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes3.dex */
public final class W1 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    public final /* synthetic */ CoroutineScope a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public W1(CoroutineExceptionHandler.Companion companion, CoroutineScope coroutineScope) {
        super(companion);
        this.a = coroutineScope;
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public final void handleException(CoroutineContext coroutineContext, Throwable th) {
        BuildersKt__Builders_commonKt.launch$default(this.a, null, null, new U1(th, null), 3, null);
    }
}
