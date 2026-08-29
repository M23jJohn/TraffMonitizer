package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;

/* loaded from: classes3.dex */
public final class S3 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ C5 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S3(C5 c5, Continuation continuation) {
        super(2, continuation);
        this.b = c5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new S3(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new S3(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003c, code lost:
    
        if (r1.a(r4, r5) == r0) goto L19;
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
            C0113j c0113j = new C0113j(((C0148o) c5.b.a.getValue()).c);
            this.a = 1;
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
        }
        X1 x1 = this.b.a.b;
        if (x1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("receiver");
            x1 = null;
        }
        L3 l3 = new L3(new R3(new O3(FlowKt.filterNotNull(x1.d))));
        this.a = 2;
        Object first = FlowKt.first(l3, this);
        return first == coroutine_suspended ? coroutine_suspended : first;
    }
}
