package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.f1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0087f1 extends SuspendLambda implements Function3 {
    public int a;
    public /* synthetic */ Emitter b;
    public /* synthetic */ J1 c;
    public final /* synthetic */ H1 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0087f1(H1 h1, Continuation continuation) {
        super(3, continuation);
        this.d = h1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        C0087f1 c0087f1 = new C0087f1(this.d, (Continuation) obj3);
        c0087f1.b = (Emitter) obj;
        c0087f1.c = (J1) obj2;
        return c0087f1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0035, code lost:
    
        if (com.tm.H1.a(r1, r7, r6) == r0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004c, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004a, code lost:
    
        if (com.tm.H1.b(r1, r7, r6) == r0) goto L19;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Emitter emitter = this.b;
            J1 j1 = this.c;
            if (Intrinsics.areEqual(j1, Q1.a)) {
                H1 h1 = this.d;
                this.b = null;
                this.a = 1;
            } else if (Intrinsics.areEqual(j1, S1.a)) {
                H1 h12 = this.d;
                this.b = null;
                this.a = 2;
            }
        } else {
            if (i != 1 && i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
