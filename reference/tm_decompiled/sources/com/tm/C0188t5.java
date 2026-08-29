package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;

/* renamed from: com.tm.t5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0188t5 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ C5 b;
    public final /* synthetic */ String c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0188t5(C5 c5, String str, Continuation continuation) {
        super(2, continuation);
        this.b = c5;
        this.c = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0188t5(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0188t5(this.b, this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0065, code lost:
    
        if (r7 == r0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0067, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003e, code lost:
    
        if (r1.a(r4, r6) == r0) goto L18;
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
            C5 c5 = this.b;
            H3 h3 = c5.a;
            Y2 y2 = new Y2(((C0148o) c5.b.a.getValue()).c, this.c);
            this.a = 1;
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Boxing.boxBoolean(((Z2) obj).c);
            }
            ResultKt.throwOnFailure(obj);
        }
        X1 x1 = this.b.a.b;
        if (x1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("receiver");
            x1 = null;
        }
        C0181s5 c0181s5 = new C0181s5(new C0161p5(FlowKt.filterNotNull(x1.d)));
        this.a = 2;
        obj = FlowKt.first(c0181s5, this);
    }
}
