package com.tm;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* loaded from: classes3.dex */
public final class I2 extends Lambda implements Function1 {
    public final /* synthetic */ J2 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I2(J2 j2) {
        super(1);
        this.a = j2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        CoroutineScope coroutineScope = this.a.e;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        return Unit.INSTANCE;
    }
}
