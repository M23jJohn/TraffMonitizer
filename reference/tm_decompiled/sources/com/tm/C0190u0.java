package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Bloc;
import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;

/* renamed from: com.tm.u0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0190u0 extends Bloc {
    public static final /* synthetic */ int e = 0;
    public final A0 a;
    public final C0155p b;
    public CoroutineScope c;
    public CoroutineScope d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0190u0(A0 connectivityRepository, C0155p configRepository) {
        super(C0211x0.b);
        Intrinsics.checkNotNullParameter(connectivityRepository, "connectivityRepository");
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        this.a = connectivityRepository;
        this.b = configRepository;
        FlowKt.launchIn(FlowKt.onEach(new C0176s0(transformEvents(((Bloc) this).eventFlow)), new C0183t0(new C0100h0(this, null), this, null)), getBlocScope());
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(C0190u0 c0190u0, Emitter emitter, Continuation continuation) {
        C0107i0 c0107i0;
        int i;
        CoroutineScope CoroutineScope;
        CoroutineScope CoroutineScope2;
        c0190u0.getClass();
        if (continuation instanceof C0107i0) {
            c0107i0 = (C0107i0) continuation;
            int i2 = c0107i0.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c0107i0.d = i2 - Integer.MIN_VALUE;
                Object obj = c0107i0.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c0107i0.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    boolean z = X0.a;
                    X0.a("ConnectivityBloc", "onStart wifiOnly = " + ((C0148o) c0190u0.b.a.getValue()).b + ", wifi = " + ((Boolean) c0190u0.a.b.getValue()).booleanValue());
                    C0 c0 = !((C0148o) c0190u0.b.a.getValue()).b ? C0093g0.b : ((Boolean) c0190u0.a.b.getValue()).booleanValue() ? C0093g0.b : C0218y0.b;
                    c0107i0.a = c0190u0;
                    c0107i0.d = 1;
                    if (emitter.emit(c0, c0107i0) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c0190u0 = c0107i0.a;
                    ResultKt.throwOnFailure(obj);
                }
                CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
                c0190u0.c = CoroutineScope;
                if (CoroutineScope != null) {
                    BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new C0121k0(c0190u0, null), 3, null);
                }
                CoroutineScope2 = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
                c0190u0.d = CoroutineScope2;
                if (CoroutineScope2 != null) {
                    BuildersKt__Builders_commonKt.launch$default(CoroutineScope2, null, null, new C0156p0(c0190u0, null), 3, null);
                }
                return Unit.INSTANCE;
            }
        }
        c0107i0 = new C0107i0(c0190u0, continuation);
        Object obj2 = c0107i0.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0107i0.d;
        if (i != 0) {
        }
        CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        c0190u0.c = CoroutineScope;
        if (CoroutineScope != null) {
        }
        CoroutineScope2 = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        c0190u0.d = CoroutineScope2;
        if (CoroutineScope2 != null) {
        }
        return Unit.INSTANCE;
    }
}
