package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.m2, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0137m2 extends SuspendLambda implements Function3 {
    public int a;
    public /* synthetic */ Emitter b;
    public /* synthetic */ K2 c;
    public final /* synthetic */ J2 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0137m2(J2 j2, Continuation continuation) {
        super(3, continuation);
        this.d = j2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        C0137m2 c0137m2 = new C0137m2(this.d, (Continuation) obj3);
        c0137m2.b = (Emitter) obj;
        c0137m2.c = (K2) obj2;
        return c0137m2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
    
        if (com.tm.J2.a(r1, r8, r7) == r0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0062, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004d, code lost:
    
        if (com.tm.J2.b(r1, r8, r7) == r0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0060, code lost:
    
        if (com.tm.J2.a(r7.d, (com.tm.L2) r1, r8, r7) == r0) goto L25;
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
            K2 k2 = this.c;
            if (Intrinsics.areEqual(k2, N2.a)) {
                J2 j2 = this.d;
                this.b = null;
                this.a = 1;
            } else if (Intrinsics.areEqual(k2, R2.a)) {
                J2 j22 = this.d;
                this.b = null;
                this.a = 2;
            } else if (k2 instanceof L2) {
                this.b = null;
                this.a = 3;
            }
        } else {
            if (i != 1 && i != 2 && i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
