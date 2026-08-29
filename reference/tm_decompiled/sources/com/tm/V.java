package com.tm;

import androidx.lifecycle.CoroutineLiveDataKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;

/* loaded from: classes3.dex */
public final class V extends SuspendLambda implements Function2 {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ X c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public V(X x, Continuation continuation) {
        super(2, continuation);
        this.c = x;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        V v = new V(this.c, continuation);
        v.b = obj;
        return v;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        V v = new V(this.c, (Continuation) obj2);
        v.b = (CoroutineScope) obj;
        return v.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0055, code lost:
    
        if (kotlinx.coroutines.DelayKt.delay(20000, r6) != r0) goto L17;
     */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object, kotlinx.coroutines.CoroutineScope] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0055 -> B:8:0x0030). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0058 -> B:8:0x0030). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.a;
        try {
        } catch (TimeoutCancellationException e) {
            boolean z = X0.a;
            X0.a("Connection", "OutPing: timeout!");
            this.c.add(new C0082e3(e));
            r1 = r1;
        }
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            r1 = (CoroutineScope) this.b;
        } else if (r1 == 1) {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.b;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope2;
            this.b = coroutineScope;
            this.a = 2;
            r1 = coroutineScope;
        } else {
            if (r1 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CoroutineScope coroutineScope3 = (CoroutineScope) this.b;
            ResultKt.throwOnFailure(obj);
            r1 = coroutineScope3;
        }
        if (!CoroutineScopeKt.isActive(r1)) {
            return Unit.INSTANCE;
        }
        U u = new U(this.c, null);
        this.b = r1;
        this.a = 1;
        coroutineScope = r1;
        if (TimeoutKt.withTimeout(CoroutineLiveDataKt.DEFAULT_TIMEOUT, u, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        this.b = coroutineScope;
        this.a = 2;
        r1 = coroutineScope;
    }
}
