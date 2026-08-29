package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* renamed from: com.tm.h3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0103h3 extends SuspendLambda implements Function2 {
    public C0166q3 a;
    public String b;
    public Z5 c;
    public int d;
    public final /* synthetic */ C0166q3 e;
    public final /* synthetic */ String f;
    public final /* synthetic */ String g;
    public final /* synthetic */ int h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0103h3(C0166q3 c0166q3, String str, String str2, int i, Continuation continuation) {
        super(2, continuation);
        this.e = c0166q3;
        this.f = str;
        this.g = str2;
        this.h = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0103h3(this.e, this.f, this.g, this.h, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((C0103h3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Z5 z5;
        C0166q3 c0166q3;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.d;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Z5 z52 = new Z5();
            C0166q3 c0166q32 = this.e;
            String str2 = this.f;
            String str3 = this.g;
            int i2 = this.h;
            CoroutineDispatcher io = Dispatchers.getIO();
            C0096g3 c0096g3 = new C0096g3(z52, str3, i2, null);
            this.a = c0166q32;
            this.b = str2;
            this.c = z52;
            this.d = 1;
            if (BuildersKt.withContext(io, c0096g3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            z5 = z52;
            c0166q3 = c0166q32;
            str = str2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            z5 = this.c;
            str = this.b;
            c0166q3 = this.a;
            ResultKt.throwOnFailure(obj);
        }
        c0166q3.b.put(str, z5);
        return z5;
    }
}
