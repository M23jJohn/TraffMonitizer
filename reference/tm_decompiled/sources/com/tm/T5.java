package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* loaded from: classes3.dex */
public final class T5 extends SuspendLambda implements Function2 {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ Y5 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public T5(Y5 y5, Continuation continuation) {
        super(2, continuation);
        this.c = y5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        T5 t5 = new T5(this.c, continuation);
        t5.b = obj;
        return t5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        T5 t5 = new T5(this.c, (Continuation) obj2);
        t5.b = (CoroutineScope) obj;
        return t5.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002e A[Catch: all -> 0x0063, TryCatch #0 {all -> 0x0063, blocks: (B:11:0x0028, B:13:0x002e, B:16:0x003b, B:22:0x0035), top: B:10:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0054 -> B:8:0x0057). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Throwable th;
        Throwable th2;
        T5 t5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.b;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.b;
            try {
                ResultKt.throwOnFailure(obj);
                t5 = this;
            } catch (Throwable th3) {
                th = th3;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
                return Unit.INSTANCE;
            }
            try {
                try {
                } catch (Throwable th4) {
                    th2 = th4;
                    th = th2;
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        throw th;
                    }
                    return Unit.INSTANCE;
                }
                t5.c.k.incrementAndGet();
            } catch (Throwable th5) {
                th2 = th5;
                th = th2;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
                return Unit.INSTANCE;
            }
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                return Unit.INSTANCE;
            }
            Z5 z5 = this.c.f;
            if (z5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connection");
                z5 = null;
            }
            byte[] a = z5.a();
            long currentTimeMillis = System.currentTimeMillis();
            Y5 y5 = this.c;
            P1 p1 = y5.d;
            int i2 = y5.a;
            this.b = coroutineScope;
            this.a = 1;
            t5 = this;
            if (((H1) p1).a(i2, a, currentTimeMillis, t5) == coroutine_suspended) {
                return coroutine_suspended;
            }
            t5.c.k.incrementAndGet();
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
        }
    }
}
