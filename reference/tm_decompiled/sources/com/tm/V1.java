package com.tm;

import java.io.IOException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.MutableSharedFlow;

/* loaded from: classes3.dex */
public final class V1 extends SuspendLambda implements Function2 {
    public int a;
    public /* synthetic */ Object b;
    public final /* synthetic */ X1 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public V1(X1 x1, Continuation continuation) {
        super(2, continuation);
        this.c = x1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        V1 v1 = new V1(this.c, continuation);
        v1.b = obj;
        return v1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        V1 v1 = new V1(this.c, (Continuation) obj2);
        v1.b = (CoroutineScope) obj;
        return v1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        EnumC0052a1 enumC0052a1;
        boolean isActive;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.b;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.b;
            try {
                ResultKt.throwOnFailure(obj);
            } finally {
                if (isActive) {
                }
            }
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            int read = this.c.a.read();
            if (read == -1 && CoroutineScopeKt.isActive(coroutineScope)) {
                throw new IOException("EnfOfStream");
            }
            EnumC0052a1[] values = EnumC0052a1.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    enumC0052a1 = null;
                    break;
                }
                enumC0052a1 = values[i2];
                if (enumC0052a1.a == read) {
                    break;
                }
                i2++;
            }
            if (enumC0052a1 == null) {
                throw new IllegalArgumentException("No such message with id [" + read + ']');
            }
            X1 x1 = this.c;
            MutableSharedFlow mutableSharedFlow = x1.d;
            Z0 a = X1.a(x1, enumC0052a1);
            this.b = coroutineScope;
            this.a = 1;
            if (mutableSharedFlow.emit(a, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
