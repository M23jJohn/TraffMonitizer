package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Emitter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* renamed from: com.tm.v, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0196v extends SuspendLambda implements Function3 {
    public int a;
    public /* synthetic */ Emitter b;
    public /* synthetic */ AbstractC0058b0 c;
    public final /* synthetic */ X d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0196v(X x, Continuation continuation) {
        super(3, continuation);
        this.d = x;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        C0196v c0196v = new C0196v(this.d, (Continuation) obj3);
        c0196v.b = (Emitter) obj;
        c0196v.c = (AbstractC0058b0) obj2;
        return c0196v.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0031, code lost:
    
        if (com.tm.X.a(r1, r12, r11) == r0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x016f, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x009f, code lost:
    
        if (r12 == r0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b4, code lost:
    
        if (com.tm.X.a(r11.d, (com.tm.V0) r1, r12, r11) == r0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c9, code lost:
    
        if (com.tm.X.a(r11.d, (com.tm.U0) r1, r12, r11) == r0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00e0, code lost:
    
        if (com.tm.X.b(r1, r12, r11) == r0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00f5, code lost:
    
        if (com.tm.X.a(r11.d, (com.tm.C0089f3) r1, r12, r11) == r0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x010a, code lost:
    
        if (com.tm.X.a(r11.d, (com.tm.N0) r1, r12, r11) == r0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0158, code lost:
    
        if (r12 == r0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x016d, code lost:
    
        if (com.tm.X.a(r11.d, (com.tm.C0082e3) r1, r12, r11) == r0) goto L76;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object emit;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.a) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Emitter emitter = this.b;
                AbstractC0058b0 abstractC0058b0 = this.c;
                if (!Intrinsics.areEqual(abstractC0058b0, C0072d0.a)) {
                    if (!Intrinsics.areEqual(abstractC0058b0, G0.a)) {
                        if (!(abstractC0058b0 instanceof V0)) {
                            if (!(abstractC0058b0 instanceof U0)) {
                                if (!Intrinsics.areEqual(abstractC0058b0, C0061b3.a)) {
                                    if (!(abstractC0058b0 instanceof C0089f3)) {
                                        if (!(abstractC0058b0 instanceof N0)) {
                                            if (!Intrinsics.areEqual(abstractC0058b0, Y.a)) {
                                                if (abstractC0058b0 instanceof C0082e3) {
                                                    this.b = null;
                                                    this.a = 9;
                                                    break;
                                                }
                                            } else {
                                                X x = this.d;
                                                this.b = null;
                                                this.a = 8;
                                                int i = X.k;
                                                x.getClass();
                                                boolean z = X0.a;
                                                X0.a("Connection", "onConnectionComplete: ");
                                                CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(x.j));
                                                x.h = CoroutineScope;
                                                if (CoroutineScope != null) {
                                                    BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new V(x, null), 3, null);
                                                }
                                                Object emit2 = emitter.emit(Z.b, this);
                                                if (emit2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                                    emit2 = Unit.INSTANCE;
                                                    break;
                                                }
                                            }
                                        } else {
                                            this.b = null;
                                            this.a = 7;
                                            break;
                                        }
                                    } else {
                                        this.b = null;
                                        this.a = 6;
                                        break;
                                    }
                                } else {
                                    X x2 = this.d;
                                    this.b = null;
                                    this.a = 5;
                                    break;
                                }
                            } else {
                                this.b = null;
                                this.a = 4;
                                break;
                            }
                        } else {
                            this.b = null;
                            this.a = 3;
                            break;
                        }
                    } else {
                        X x3 = this.d;
                        this.b = null;
                        this.a = 2;
                        int i2 = X.k;
                        CoroutineScope coroutineScope2 = x3.i;
                        if (coroutineScope2 != null) {
                            CoroutineScopeKt.cancel$default(coroutineScope2, null, 1, null);
                        }
                        x3.i = null;
                        if (!Intrinsics.areEqual(x3.getState(), Z.b) && !Intrinsics.areEqual(x3.getState(), L0.b)) {
                            emit = emitter.emit(C0051a0.b, this);
                            if (emit != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                emit = Unit.INSTANCE;
                                break;
                            }
                        } else {
                            CoroutineScope coroutineScope3 = x3.f;
                            if (coroutineScope3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("socketScope");
                                coroutineScope = null;
                            } else {
                                coroutineScope = coroutineScope3;
                            }
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new B(x3, null), 3, null);
                            emit = emitter.emit(C0051a0.b, this);
                            if (emit != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                emit = Unit.INSTANCE;
                                break;
                            }
                        }
                    }
                } else {
                    X x4 = this.d;
                    this.b = null;
                    this.a = 1;
                    break;
                }
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                ResultKt.throwOnFailure(obj);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
