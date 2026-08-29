package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;

/* renamed from: com.tm.j4, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0118j4 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ C5 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0118j4(C5 c5, Continuation continuation) {
        super(2, continuation);
        this.b = c5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0118j4(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0118j4(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
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
            C0186t3 c0186t3 = new C0186t3(((C0148o) c5.b.a.getValue()).c);
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
        C0069c4 c0069c4 = new C0069c4(new C0111i4(new C0090f4(FlowKt.filterNotNull(x1.d))));
        this.a = 2;
        Object first = FlowKt.first(c0069c4, this);
        return first == coroutine_suspended ? coroutine_suspended : first;
    }
}
