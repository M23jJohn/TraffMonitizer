package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* renamed from: com.tm.h0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0100h0 extends SuspendLambda implements Function3 {
    public int a;
    public /* synthetic */ Emitter b;
    public /* synthetic */ AbstractC0204w0 c;
    public final /* synthetic */ C0190u0 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0100h0(C0190u0 c0190u0, Continuation continuation) {
        super(3, continuation);
        this.d = c0190u0;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        C0100h0 c0100h0 = new C0100h0(this.d, (Continuation) obj3);
        c0100h0.b = (Emitter) obj;
        c0100h0.c = (AbstractC0204w0) obj2;
        return c0100h0.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
    
        if (com.tm.C0190u0.a(r1, r10, r9) == r0) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x013e, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0071, code lost:
    
        if (r10 == r0) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00da, code lost:
    
        if (r10 == r0) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x013c, code lost:
    
        if (r10 == r0) goto L58;
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
            AbstractC0204w0 abstractC0204w0 = this.c;
            if (abstractC0204w0 instanceof B0) {
                C0190u0 c0190u0 = this.d;
                this.b = null;
                this.a = 1;
            } else if (abstractC0204w0 instanceof D0) {
                C0190u0 c0190u02 = this.d;
                this.b = null;
                this.a = 2;
                int i2 = C0190u0.e;
                c0190u02.getClass();
                boolean z = X0.a;
                X0.a("ConnectivityBloc", "onStop");
                CoroutineScope coroutineScope = c0190u02.c;
                if (coroutineScope != null) {
                    CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
                }
                CoroutineScope coroutineScope2 = c0190u02.d;
                if (coroutineScope2 != null) {
                    CoroutineScopeKt.cancel$default(coroutineScope2, null, 1, null);
                }
                Object emit = emitter.emit(C0211x0.b, this);
                if (emit != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    emit = Unit.INSTANCE;
                }
            } else if (abstractC0204w0 instanceof C0197v0) {
                C0190u0 c0190u03 = this.d;
                C0197v0 c0197v0 = (C0197v0) abstractC0204w0;
                this.b = null;
                this.a = 3;
                int i3 = C0190u0.e;
                c0190u03.getClass();
                boolean z2 = X0.a;
                X0.a("ConnectivityBloc", "onConfigChanged wifiOnly = " + c0197v0.a + ", wifi = " + ((Boolean) c0190u03.a.b.getValue()).booleanValue());
                Object emit2 = emitter.emit(!c0197v0.a ? C0093g0.b : ((Boolean) c0190u03.a.b.getValue()).booleanValue() ? C0093g0.b : C0218y0.b, this);
                if (emit2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    emit2 = Unit.INSTANCE;
                }
            } else if (abstractC0204w0 instanceof E0) {
                C0190u0 c0190u04 = this.d;
                E0 e0 = (E0) abstractC0204w0;
                this.b = null;
                this.a = 4;
                int i4 = C0190u0.e;
                c0190u04.getClass();
                boolean z3 = X0.a;
                X0.a("ConnectivityBloc", "onWifiChanged wifiOnly = " + ((C0148o) c0190u04.b.a.getValue()).b + ", wifi = " + e0.a);
                Object emit3 = emitter.emit(!((C0148o) c0190u04.b.a.getValue()).b ? C0093g0.b : e0.a ? C0093g0.b : C0218y0.b, this);
                if (emit3 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    emit3 = Unit.INSTANCE;
                }
            }
        } else {
            if (i != 1 && i != 2 && i != 3 && i != 4) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
