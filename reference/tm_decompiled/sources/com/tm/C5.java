package com.tm;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;

/* loaded from: classes3.dex */
public final class C5 {
    public final H3 a;
    public final C0155p b;
    public final C0195u5 c;
    public CoroutineScope d;

    public C5(H3 api, C0155p configRepository) {
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        this.a = api;
        this.b = configRepository;
        C0195u5 c0195u5 = new C0195u5(CoroutineExceptionHandler.INSTANCE);
        this.c = c0195u5;
        this.d = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(c0195u5));
    }

    public final void a() {
        boolean z = X0.a;
        X0.a("TraffRepo", "unbindApi");
        H3 h3 = this.a;
        C0116j2 c0116j2 = h3.a;
        if (c0116j2 != null) {
            CoroutineScopeKt.cancel$default(c0116j2.c, null, 1, null);
            c0116j2.b.close();
        }
        X1 x1 = h3.b;
        if (x1 != null) {
            CoroutineScopeKt.cancel$default(x1.c, null, 1, null);
            x1.b.close();
        }
        CoroutineScopeKt.cancel$default(this.d, null, 1, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x00b6, code lost:
    
        if (r11 != r1) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object b(Continuation continuation) {
        C0167q4 c0167q4;
        int i;
        String str;
        C5 c5;
        X1 x1;
        if (continuation instanceof C0167q4) {
            c0167q4 = (C0167q4) continuation;
            int i2 = c0167q4.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c0167q4.d = i2 - Integer.MIN_VALUE;
                Object obj = c0167q4.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c0167q4.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    C0148o c0148o = (C0148o) this.b.a.getValue();
                    H3 h3 = this.a;
                    byte[] bArr = c0148o.c;
                    String str2 = c0148o.a;
                    if (c0148o.i == EnumC0127l.SDK) {
                        str = c0148o.h + "/sdk";
                    } else {
                        str = c0148o.h + "/app/" + c0148o.k;
                    }
                    O0 o0 = new O0(bArr, str2, str);
                    c0167q4.a = this;
                    c0167q4.d = 1;
                    if (h3.a(o0, c0167q4) != coroutine_suspended) {
                        c5 = this;
                    }
                    return coroutine_suspended;
                }
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Boxing.boxBoolean(((P0) obj).c);
                }
                c5 = c0167q4.a;
                ResultKt.throwOnFailure(obj);
                x1 = c5.a.b;
                if (x1 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("receiver");
                    x1 = null;
                }
                C0160p4 c0160p4 = new C0160p4(new C0139m4(FlowKt.filterNotNull(x1.d)));
                c0167q4.a = null;
                c0167q4.d = 2;
                obj = FlowKt.first(c0160p4, c0167q4);
            }
        }
        c0167q4 = new C0167q4(this, continuation);
        Object obj2 = c0167q4.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0167q4.d;
        if (i != 0) {
        }
        x1 = c5.a.b;
        if (x1 == null) {
        }
        C0160p4 c0160p42 = new C0160p4(new C0139m4(FlowKt.filterNotNull(x1.d)));
        c0167q4.a = null;
        c0167q4.d = 2;
        obj2 = FlowKt.first(c0160p42, c0167q4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0081, code lost:
    
        if (r7 != r1) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object c(Continuation continuation) {
        C0091f5 c0091f5;
        int i;
        C5 c5;
        X1 x1;
        if (continuation instanceof C0091f5) {
            c0091f5 = (C0091f5) continuation;
            int i2 = c0091f5.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c0091f5.d = i2 - Integer.MIN_VALUE;
                Object obj = c0091f5.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c0091f5.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    H3 h3 = this.a;
                    C0073d1 c0073d1 = new C0073d1(((C0148o) this.b.a.getValue()).c);
                    c0091f5.a = this;
                    c0091f5.d = 1;
                    if (h3.a(c0073d1, c0091f5) != coroutine_suspended) {
                        c5 = this;
                    }
                    return coroutine_suspended;
                }
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Boxing.boxBoolean(true);
                }
                c5 = c0091f5.a;
                ResultKt.throwOnFailure(obj);
                x1 = c5.a.b;
                if (x1 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("receiver");
                    x1 = null;
                }
                C0084e5 c0084e5 = new C0084e5(new C0063b5(FlowKt.filterNotNull(x1.d)));
                c0091f5.a = null;
                c0091f5.d = 2;
                obj = FlowKt.first(c0084e5, c0091f5);
            }
        }
        c0091f5 = new C0091f5(this, continuation);
        Object obj2 = c0091f5.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0091f5.d;
        if (i != 0) {
        }
        x1 = c5.a.b;
        if (x1 == null) {
        }
        C0084e5 c0084e52 = new C0084e5(new C0063b5(FlowKt.filterNotNull(x1.d)));
        c0091f5.a = null;
        c0091f5.d = 2;
        obj2 = FlowKt.first(c0084e52, c0091f5);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0081, code lost:
    
        if (r7 != r1) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object d(Continuation continuation) {
        C0140m5 c0140m5;
        int i;
        C5 c5;
        X1 x1;
        if (continuation instanceof C0140m5) {
            c0140m5 = (C0140m5) continuation;
            int i2 = c0140m5.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c0140m5.d = i2 - Integer.MIN_VALUE;
                Object obj = c0140m5.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c0140m5.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    H3 h3 = this.a;
                    C0050a c0050a = new C0050a(((C0148o) this.b.a.getValue()).c);
                    c0140m5.a = this;
                    c0140m5.d = 1;
                    if (h3.a(c0050a, c0140m5) != coroutine_suspended) {
                        c5 = this;
                    }
                    return coroutine_suspended;
                }
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Boxing.boxBoolean(((C0064c) obj).c);
                }
                c5 = c0140m5.a;
                ResultKt.throwOnFailure(obj);
                x1 = c5.a.b;
                if (x1 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("receiver");
                    x1 = null;
                }
                C0133l5 c0133l5 = new C0133l5(new C0112i5(FlowKt.filterNotNull(x1.d)));
                c0140m5.a = null;
                c0140m5.d = 2;
                obj = FlowKt.first(c0133l5, c0140m5);
            }
        }
        c0140m5 = new C0140m5(this, continuation);
        Object obj2 = c0140m5.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0140m5.d;
        if (i != 0) {
        }
        x1 = c5.a.b;
        if (x1 == null) {
        }
        C0133l5 c0133l52 = new C0133l5(new C0112i5(FlowKt.filterNotNull(x1.d)));
        c0140m5.a = null;
        c0140m5.d = 2;
        obj2 = FlowKt.first(c0133l52, c0140m5);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0081, code lost:
    
        if (r7 != r1) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object e(Continuation continuation) {
        B5 b5;
        int i;
        C5 c5;
        X1 x1;
        if (continuation instanceof B5) {
            b5 = (B5) continuation;
            int i2 = b5.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                b5.d = i2 - Integer.MIN_VALUE;
                Object obj = b5.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = b5.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    H3 h3 = this.a;
                    C0071d c0071d = new C0071d(((C0148o) this.b.a.getValue()).c);
                    b5.a = this;
                    b5.d = 1;
                    if (h3.a(c0071d, b5) != coroutine_suspended) {
                        c5 = this;
                    }
                    return coroutine_suspended;
                }
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Boxing.boxBoolean(((C0078e) obj).c);
                }
                c5 = b5.a;
                ResultKt.throwOnFailure(obj);
                x1 = c5.a.b;
                if (x1 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("receiver");
                    x1 = null;
                }
                A5 a5 = new A5(new C0216x5(FlowKt.filterNotNull(x1.d)));
                b5.a = null;
                b5.d = 2;
                obj = FlowKt.first(a5, b5);
            }
        }
        b5 = new B5(this, continuation);
        Object obj2 = b5.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = b5.d;
        if (i != 0) {
        }
        x1 = c5.a.b;
        if (x1 == null) {
        }
        A5 a52 = new A5(new C0216x5(FlowKt.filterNotNull(x1.d)));
        b5.a = null;
        b5.d = 2;
        obj2 = FlowKt.first(a52, b5);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object a(Continuation continuation) {
        I3 i3;
        int i;
        if (continuation instanceof I3) {
            i3 = (I3) continuation;
            int i2 = i3.c;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                i3.c = i2 - Integer.MIN_VALUE;
                Object obj = i3.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = i3.c;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    H3 h3 = this.a;
                    C0134m c0134m = new C0134m(((C0148o) this.b.a.getValue()).c);
                    i3.c = 1;
                    if (h3.a(c0134m, i3) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Boxing.boxBoolean(true);
            }
        }
        i3 = new I3(this, continuation);
        Object obj2 = i3.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = i3.c;
        if (i != 0) {
        }
        return Boxing.boxBoolean(true);
    }
}
