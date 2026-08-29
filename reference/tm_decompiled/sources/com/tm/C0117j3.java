package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

/* renamed from: com.tm.j3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0117j3 extends SuspendLambda implements Function2 {
    public C0166q3 a;
    public String b;
    public int c;
    public final /* synthetic */ C0166q3 d;
    public final /* synthetic */ String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0117j3(C0166q3 c0166q3, String str, Continuation continuation) {
        super(2, continuation);
        this.d = c0166q3;
        this.e = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0117j3(this.d, this.e, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0117j3(this.d, this.e, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        C0166q3 c0166q3;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.c;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            C0075d3 c0075d3 = (C0075d3) this.d.a.get(this.e);
            if (c0075d3 != null) {
                c0166q3 = this.d;
                String str2 = this.e;
                CoroutineDispatcher io = Dispatchers.getIO();
                C0110i3 c0110i3 = new C0110i3(c0075d3, null);
                this.a = c0166q3;
                this.b = str2;
                this.c = 1;
                if (BuildersKt.withContext(io, c0110i3, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str = str2;
            }
            throw new W2(this.e);
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        str = this.b;
        c0166q3 = this.a;
        ResultKt.throwOnFailure(obj);
        C0075d3 c0075d32 = (C0075d3) c0166q3.a.remove(str);
        if (c0075d32 != null) {
            return c0075d32;
        }
        throw new W2(this.e);
    }
}
