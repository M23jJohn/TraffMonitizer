package com.traffmonetizer.sdk;

import android.content.Context;
import androidx.autofill.HintConstants;
import com.tm.AbstractC0162q;
import com.tm.B0;
import com.tm.C0051a0;
import com.tm.C0072d0;
import com.tm.C0095g2;
import com.tm.C0106i;
import com.tm.C0118j4;
import com.tm.C0148o;
import com.tm.C0179s3;
import com.tm.C0188t5;
import com.tm.C5;
import com.tm.D0;
import com.tm.D5;
import com.tm.EnumC0127l;
import com.tm.G5;
import com.tm.J5;
import com.tm.K5;
import com.tm.N5;
import com.tm.O5;
import com.tm.P5;
import com.tm.S3;
import com.tm.X0;
import com.traffmonetizer.sdk.model.BalanceInfo;
import com.traffmonetizer.sdk.model.ConnectionStatus;
import com.traffmonetizer.sdk.model.DebugMessage;
import com.traffmonetizer.sdk.model.ServiceStatus;
import com.traffmonetizer.sdk.model.StatsInfo;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0006J\u0011\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000fJ\u0011\u0010\u0013\u001a\u00020\u0014H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000fJ\u0011\u0010\u0017\u001a\u00020\u0018H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ>\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u00142\b\b\u0002\u0010\u001e\u001a\u00020\u00062\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010 \u001a\u00020\u0006H\u0007J\u0006\u0010!\u001a\u00020\u0006J\u0006\u0010\"\u001a\u00020\u0006J\u0006\u0010#\u001a\u00020\bJ\u0019\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u0014H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010&J\u000e\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\u0006J\u000e\u0010)\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u0006J\u0006\u0010*\u001a\u00020\bJ\u0006\u0010+\u001a\u00020\bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lcom/traffmonetizer/sdk/TraffmonetizerSdk;", "", "()V", "instance", "Lcom/traffmonetizer/sdk/TraffmonetizerSdkImpl;", "verboseLogging", "", "deinit", "", "enableVerboseLogging", "enable", "getBalance", "Lcom/traffmonetizer/sdk/model/BalanceInfo;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getConnectionStatus", "Lkotlinx/coroutines/flow/Flow;", "Lcom/traffmonetizer/sdk/model/ConnectionStatus;", "getDebugMessages", "Lcom/traffmonetizer/sdk/model/DebugMessage;", "getDeviceName", "", "getServiceStatus", "Lcom/traffmonetizer/sdk/model/ServiceStatus;", "getStats", "Lcom/traffmonetizer/sdk/model/StatsInfo;", "init", "context", "Landroid/content/Context;", "token", "version", "wifiOnly", "serviceActive", "developConfig", "isInitialized", "isStarted", "reconnect", "setDeviceName", HintConstants.AUTOFILL_HINT_NAME, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setServiceActive", "active", "setWifiOnly", "start", "stop", "TraffmonetizerSDK-v1.2.11_internalRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TraffmonetizerSdk {
    public static final TraffmonetizerSdk INSTANCE = new TraffmonetizerSdk();
    private static TraffmonetizerSdkImpl instance;
    private static boolean verboseLogging;

    private TraffmonetizerSdk() {
    }

    public static /* synthetic */ void init$default(TraffmonetizerSdk traffmonetizerSdk, Context context, String str, String str2, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        if ((i & 16) != 0) {
            z2 = true;
        }
        if ((i & 32) != 0) {
            z3 = false;
        }
        traffmonetizerSdk.init(context, str, str2, z, z2, z3);
    }

    public final void deinit() {
        if (instance == null) {
            X0.a("Not initialized!");
        } else {
            instance = null;
        }
    }

    public final void enableVerboseLogging(boolean enable) {
        boolean z = X0.a;
        X0.a = enable;
        X0.b = false;
        verboseLogging = enable;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object getBalance(Continuation continuation) {
        D5 d5;
        int i;
        Deferred async$default;
        C0106i c0106i;
        if (continuation instanceof D5) {
            d5 = (D5) continuation;
            int i2 = d5.c;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                d5.c = i2 - Integer.MIN_VALUE;
                Object obj = d5.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = d5.c;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    TraffmonetizerSdkImpl traffmonetizerSdkImpl = instance;
                    if (traffmonetizerSdkImpl != null) {
                        d5.c = 1;
                        C5 c5 = traffmonetizerSdkImpl.c;
                        async$default = BuildersKt__Builders_commonKt.async$default(c5.d, null, null, new S3(c5, null), 3, null);
                        obj = async$default.await(d5);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    throw new IllegalStateException("TraffmonetizerSdk is not initialised");
                }
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                c0106i = (C0106i) obj;
                if (c0106i != null) {
                    Intrinsics.checkNotNullParameter(c0106i, "<this>");
                    return new BalanceInfo(c0106i.a, c0106i.b);
                }
                throw new IllegalStateException("TraffmonetizerSdk is not initialised");
            }
        }
        d5 = new D5(this, continuation);
        Object obj2 = d5.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = d5.c;
        if (i != 0) {
        }
        c0106i = (C0106i) obj2;
        if (c0106i != null) {
        }
        throw new IllegalStateException("TraffmonetizerSdk is not initialised");
    }

    public final Flow<ConnectionStatus> getConnectionStatus() {
        Flow<State> stateFlow;
        TraffmonetizerSdkImpl traffmonetizerSdkImpl = instance;
        if (traffmonetizerSdkImpl == null || (stateFlow = traffmonetizerSdkImpl.d.b.getStateFlow()) == 0) {
            throw new IllegalStateException("TraffmonetizerSdk is not initialised");
        }
        return new G5(stateFlow);
    }

    public final Flow<DebugMessage> getDebugMessages() {
        boolean z = X0.a;
        return new J5();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object getDeviceName(Continuation continuation) {
        K5 k5;
        int i;
        String str;
        if (continuation instanceof K5) {
            k5 = (K5) continuation;
            int i2 = k5.c;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                k5.c = i2 - Integer.MIN_VALUE;
                Object obj = k5.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = k5.c;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    TraffmonetizerSdkImpl traffmonetizerSdkImpl = instance;
                    if (traffmonetizerSdkImpl != null) {
                        k5.c = 1;
                        obj = traffmonetizerSdkImpl.a(k5);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    throw new IllegalStateException("TraffmonetizerSdk is not initialised");
                }
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                str = (String) obj;
                if (str != null) {
                    return str;
                }
                throw new IllegalStateException("TraffmonetizerSdk is not initialised");
            }
        }
        k5 = new K5(this, continuation);
        Object obj2 = k5.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = k5.c;
        if (i != 0) {
        }
        str = (String) obj2;
        if (str != null) {
        }
        throw new IllegalStateException("TraffmonetizerSdk is not initialised");
    }

    public final Flow<ServiceStatus> getServiceStatus() {
        Flow<State> stateFlow;
        TraffmonetizerSdkImpl traffmonetizerSdkImpl = instance;
        if (traffmonetizerSdkImpl == null || (stateFlow = traffmonetizerSdkImpl.d.c.getStateFlow()) == 0) {
            throw new IllegalStateException("TraffmonetizerSdk is not initialised");
        }
        return new N5(stateFlow);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object getStats(Continuation continuation) {
        O5 o5;
        int i;
        Deferred async$default;
        C0179s3 c0179s3;
        if (continuation instanceof O5) {
            o5 = (O5) continuation;
            int i2 = o5.c;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                o5.c = i2 - Integer.MIN_VALUE;
                Object obj = o5.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = o5.c;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    TraffmonetizerSdkImpl traffmonetizerSdkImpl = instance;
                    if (traffmonetizerSdkImpl != null) {
                        o5.c = 1;
                        C5 c5 = traffmonetizerSdkImpl.c;
                        async$default = BuildersKt__Builders_commonKt.async$default(c5.d, null, null, new C0118j4(c5, null), 3, null);
                        obj = async$default.await(o5);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    throw new IllegalStateException("TraffmonetizerSdk is not initialised");
                }
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                c0179s3 = (C0179s3) obj;
                if (c0179s3 != null) {
                    Intrinsics.checkNotNullParameter(c0179s3, "<this>");
                    return new StatsInfo(c0179s3.a, c0179s3.b, c0179s3.c);
                }
                throw new IllegalStateException("TraffmonetizerSdk is not initialised");
            }
        }
        o5 = new O5(this, continuation);
        Object obj2 = o5.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = o5.c;
        if (i != 0) {
        }
        c0179s3 = (C0179s3) obj2;
        if (c0179s3 != null) {
        }
        throw new IllegalStateException("TraffmonetizerSdk is not initialised");
    }

    public final void init(Context context, String token, String version) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(version, "version");
        init$default(this, context, token, version, false, false, false, 56, null);
    }

    public final boolean isInitialized() {
        return instance != null;
    }

    public final boolean isStarted() {
        TraffmonetizerSdkImpl traffmonetizerSdkImpl = instance;
        if (traffmonetizerSdkImpl != null) {
            return traffmonetizerSdkImpl.d.e;
        }
        throw new IllegalStateException("TraffmonetizerSdk is not initialised");
    }

    public final void reconnect() {
        Unit unit;
        TraffmonetizerSdkImpl traffmonetizerSdkImpl = instance;
        if (traffmonetizerSdkImpl != null) {
            C0095g2 c0095g2 = traffmonetizerSdkImpl.d;
            if (Intrinsics.areEqual(c0095g2.b.getState(), C0051a0.b)) {
                c0095g2.b.add(C0072d0.a);
            }
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new IllegalStateException("TraffmonetizerSdk is not initialised");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object setDeviceName(String str, Continuation continuation) {
        P5 p5;
        int i;
        Deferred async$default;
        if (continuation instanceof P5) {
            p5 = (P5) continuation;
            int i2 = p5.c;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                p5.c = i2 - Integer.MIN_VALUE;
                Object obj = p5.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = p5.c;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    TraffmonetizerSdkImpl traffmonetizerSdkImpl = instance;
                    if (traffmonetizerSdkImpl == null) {
                        throw new IllegalStateException("TraffmonetizerSdk is not initialised");
                    }
                    p5.c = 1;
                    C5 c5 = traffmonetizerSdkImpl.c;
                    async$default = BuildersKt__Builders_commonKt.async$default(c5.d, null, null, new C0188t5(c5, str, null), 3, null);
                    obj = async$default.await(p5);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Boxing.boxBoolean(((Boolean) obj).booleanValue());
            }
        }
        p5 = new P5(this, continuation);
        Object obj2 = p5.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = p5.c;
        if (i != 0) {
        }
        return Boxing.boxBoolean(((Boolean) obj2).booleanValue());
    }

    public final void setServiceActive(boolean active) {
        Unit unit;
        TraffmonetizerSdkImpl traffmonetizerSdkImpl = instance;
        if (traffmonetizerSdkImpl != null) {
            MutableStateFlow mutableStateFlow = traffmonetizerSdkImpl.a.a;
            mutableStateFlow.setValue(C0148o.a((C0148o) mutableStateFlow.getValue(), null, false, null, 0, null, false, null, active, null, 1535));
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new IllegalStateException("TraffmonetizerSdk is not initialised");
        }
    }

    public final void setWifiOnly(boolean wifiOnly) {
        Unit unit;
        TraffmonetizerSdkImpl traffmonetizerSdkImpl = instance;
        if (traffmonetizerSdkImpl != null) {
            MutableStateFlow mutableStateFlow = traffmonetizerSdkImpl.a.a;
            mutableStateFlow.setValue(C0148o.a((C0148o) mutableStateFlow.getValue(), null, wifiOnly, null, 0, null, false, null, false, null, 2045));
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new IllegalStateException("TraffmonetizerSdk is not initialised");
        }
    }

    public final void start() {
        Unit unit;
        TraffmonetizerSdkImpl traffmonetizerSdkImpl = instance;
        if (traffmonetizerSdkImpl != null) {
            if (traffmonetizerSdkImpl.d.e) {
                X0.a("Already started!");
            } else {
                X0.a("SDK", "start: ");
                traffmonetizerSdkImpl.b.b();
                C0095g2 c0095g2 = traffmonetizerSdkImpl.d;
                c0095g2.e = true;
                c0095g2.a.add(B0.a);
            }
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new IllegalStateException("TraffmonetizerSdk is not initialised");
        }
    }

    public final void stop() {
        Unit unit;
        TraffmonetizerSdkImpl traffmonetizerSdkImpl = instance;
        if (traffmonetizerSdkImpl != null) {
            X0.a("SDK", "stop: ");
            traffmonetizerSdkImpl.b.c();
            C0095g2 c0095g2 = traffmonetizerSdkImpl.d;
            c0095g2.a.add(D0.a);
            c0095g2.e = false;
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new IllegalStateException("TraffmonetizerSdk is not initialised");
        }
    }

    public final void init(Context context, String token, String version, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(version, "version");
        init$default(this, context, token, version, z, false, false, 48, null);
    }

    public final void init(Context context, String token, String version, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(version, "version");
        init$default(this, context, token, version, z, z2, false, 32, null);
    }

    public final void init(Context context, String token, String version, boolean wifiOnly, boolean serviceActive, boolean developConfig) {
        C0148o c0148o;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(version, "version");
        if (instance != null) {
            X0.a("Already initialized!");
            return;
        }
        boolean z = X0.a;
        StringBuilder sb = new StringBuilder("init with ");
        sb.append(developConfig ? "dev" : "prod");
        sb.append(" config");
        X0.a("SDK", sb.toString());
        if (developConfig) {
            c0148o = AbstractC0162q.b;
        } else {
            c0148o = AbstractC0162q.a;
        }
        instance = new TraffmonetizerSdkImpl(context, C0148o.a(c0148o, token, wifiOnly, null, 0, null, verboseLogging, EnumC0127l.INTERNAL, serviceActive, version, 188));
    }
}
