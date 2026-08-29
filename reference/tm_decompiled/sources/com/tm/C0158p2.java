package com.tm;

import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* renamed from: com.tm.p2, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0158p2 extends SuspendLambda implements Function4 {
    public /* synthetic */ boolean a;
    public /* synthetic */ AbstractC0079e0 b;
    public /* synthetic */ C0 c;

    public C0158p2(Continuation continuation) {
        super(4, continuation);
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        C0158p2 c0158p2 = new C0158p2((Continuation) obj4);
        c0158p2.a = booleanValue;
        c0158p2.b = (AbstractC0079e0) obj2;
        c0158p2.c = (C0) obj3;
        return c0158p2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        boolean z = this.a;
        return new Triple(Boxing.boxBoolean(z), this.b, this.c);
    }
}
