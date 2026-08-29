package com.tm;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes3.dex */
public final class S0 {
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object a(String str, Continuation continuation) {
        Q0 q0;
        int i;
        if (continuation instanceof Q0) {
            q0 = (Q0) continuation;
            int i2 = q0.c;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                q0.c = i2 - Integer.MIN_VALUE;
                Object obj = q0.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = q0.c;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher io = Dispatchers.getIO();
                    R0 r0 = new R0(str, null);
                    q0.c = 1;
                    obj = BuildersKt.withContext(io, r0, q0);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                Intrinsics.checkNotNullExpressionValue(obj, "url: String): String =\n …              }\n        }");
                return obj;
            }
        }
        q0 = new Q0(this, continuation);
        Object obj2 = q0.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = q0.c;
        if (i != 0) {
        }
        Intrinsics.checkNotNullExpressionValue(obj2, "url: String): String =\n …              }\n        }");
        return obj2;
    }
}
