package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Bloc;
import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;

/* loaded from: classes3.dex */
public final class J2 extends Bloc {
    public final X a;
    public final C0190u0 b;
    public final C5 c;
    public final C0155p d;
    public CoroutineScope e;
    public final C0227z2 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public J2(X connectionBloc, C0190u0 connectivityBloc, C5 traffmonetizerRepository, C0155p configRepository) {
        super(S2.b);
        Intrinsics.checkNotNullParameter(connectionBloc, "connectionBloc");
        Intrinsics.checkNotNullParameter(connectivityBloc, "connectivityBloc");
        Intrinsics.checkNotNullParameter(traffmonetizerRepository, "traffmonetizerRepository");
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        this.a = connectionBloc;
        this.b = connectivityBloc;
        this.c = traffmonetizerRepository;
        this.d = configRepository;
        CoroutineExceptionHandler.Companion companion = CoroutineExceptionHandler.INSTANCE;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(new C0220y2(companion)));
        this.f = new C0227z2(companion, this);
        FlowKt.launchIn(FlowKt.onEach(new C2(transformEvents(((Bloc) this).eventFlow)), new D2(new C0137m2(this, null), this, null)), getBlocScope());
        EnumC0127l enumC0127l = ((C0148o) configRepository.a.getValue()).i;
        if (enumC0127l == EnumC0127l.SDK) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new C0151o2(this, null), 3, null);
        }
        if (enumC0127l == EnumC0127l.INTERNAL) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new C0192u2(this, null), 3, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(J2 j2, L2 l2, Emitter emitter, Continuation continuation) {
        C0199v2 c0199v2;
        int i;
        CoroutineScope coroutineScope;
        Job launch$default;
        j2.getClass();
        if (continuation instanceof C0199v2) {
            c0199v2 = (C0199v2) continuation;
            int i2 = c0199v2.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c0199v2.d = i2 - Integer.MIN_VALUE;
                Object obj = c0199v2.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c0199v2.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    M2 m2 = new M2(l2.a);
                    c0199v2.a = j2;
                    c0199v2.d = 1;
                    if (emitter.emit(m2, c0199v2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    j2 = c0199v2.a;
                    ResultKt.throwOnFailure(obj);
                }
                coroutineScope = j2.e;
                if (coroutineScope != null && launch$default != null) {
                    launch$default.invokeOnCompletion(new C0213x2(j2));
                }
                return Unit.INSTANCE;
            }
        }
        c0199v2 = new C0199v2(j2, continuation);
        Object obj2 = c0199v2.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c0199v2.d;
        if (i != 0) {
        }
        coroutineScope = j2.e;
        if (coroutineScope != null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C0206w2(j2, null), 3, null);
            launch$default.invokeOnCompletion(new C0213x2(j2));
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object b(J2 j2, Emitter emitter, Continuation continuation) {
        G2 g2;
        int i;
        CoroutineScope coroutineScope;
        Job launch$default;
        j2.getClass();
        if (continuation instanceof G2) {
            g2 = (G2) continuation;
            int i2 = g2.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                g2.d = i2 - Integer.MIN_VALUE;
                Object obj = g2.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = g2.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    S2 s2 = S2.b;
                    g2.a = j2;
                    g2.d = 1;
                    if (emitter.emit(s2, g2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    j2 = g2.a;
                    ResultKt.throwOnFailure(obj);
                }
                coroutineScope = j2.e;
                if (coroutineScope != null && launch$default != null) {
                    launch$default.invokeOnCompletion(new I2(j2));
                }
                return Unit.INSTANCE;
            }
        }
        g2 = new G2(j2, continuation);
        Object obj2 = g2.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = g2.d;
        if (i != 0) {
        }
        coroutineScope = j2.e;
        if (coroutineScope != null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new H2(j2, null), 3, null);
            launch$default.invokeOnCompletion(new I2(j2));
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object a(J2 j2, Emitter emitter, Continuation continuation) {
        E2 e2;
        int i;
        CoroutineScope CoroutineScope;
        j2.getClass();
        if (continuation instanceof E2) {
            e2 = (E2) continuation;
            int i2 = e2.d;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                e2.d = i2 - Integer.MIN_VALUE;
                Object obj = e2.b;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = e2.d;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    State state = j2.getState();
                    O2 o2 = O2.b;
                    if (Intrinsics.areEqual(state, o2)) {
                        return Unit.INSTANCE;
                    }
                    e2.a = j2;
                    e2.d = 1;
                    if (emitter.emit(o2, e2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    j2 = e2.a;
                    ResultKt.throwOnFailure(obj);
                }
                CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(j2.f));
                j2.e = CoroutineScope;
                if (CoroutineScope != null) {
                    BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new F2(j2, null), 3, null);
                }
                return Unit.INSTANCE;
            }
        }
        e2 = new E2(j2, continuation);
        Object obj2 = e2.b;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = e2.d;
        if (i != 0) {
        }
        CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(j2.f));
        j2.e = CoroutineScope;
        if (CoroutineScope != null) {
        }
        return Unit.INSTANCE;
    }
}
