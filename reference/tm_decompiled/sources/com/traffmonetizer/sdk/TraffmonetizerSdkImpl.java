package com.traffmonetizer.sdk;

import android.content.Context;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.ptrbrynt.kotlin_bloc.core.Bloc;
import com.tm.A0;
import com.tm.B0;
import com.tm.C0095g2;
import com.tm.C0148o;
import com.tm.C0155p;
import com.tm.C0166q3;
import com.tm.C5;
import com.tm.D0;
import com.tm.H3;
import com.tm.K0;
import com.tm.Q5;
import com.tm.S0;
import com.tm.X0;
import com.tm.Y0;
import com.tm.Z3;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Deferred;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/traffmonetizer/sdk/TraffmonetizerSdkImpl;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "TraffmonetizerSDK-v1.2.11_internalRelease"}, k = 1, mv = {1, 6, 0})
/* loaded from: classes3.dex */
public final class TraffmonetizerSdkImpl implements DefaultLifecycleObserver {
    public final C0155p a;
    public final A0 b;
    public final C5 c;
    public final C0095g2 d;

    public TraffmonetizerSdkImpl(Context context, C0148o defaultConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(defaultConfig, "defaultConfig");
        C0155p c0155p = new C0155p(context, defaultConfig);
        this.a = c0155p;
        A0 a0 = new A0(context);
        this.b = a0;
        C5 c5 = new C5(new H3(), c0155p);
        this.c = c5;
        this.d = new C0095g2(c0155p, c5, new S0(), new C0166q3(), a0);
        Bloc.INSTANCE.setObserver(new Y0());
        boolean z = X0.a;
        X0.a("SDK", "SDK version = " + ((C0148o) c0155p.a.getValue()).h);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object a(Continuation continuation) {
        Q5 q5;
        int i;
        Deferred async$default;
        if (continuation instanceof Q5) {
            q5 = (Q5) continuation;
            int i2 = q5.c;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                q5.c = i2 - Integer.MIN_VALUE;
                Object obj = q5.a;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = q5.c;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    C5 c5 = this.c;
                    q5.c = 1;
                    async$default = BuildersKt__Builders_commonKt.async$default(c5.d, null, null, new Z3(c5, null), 3, null);
                    obj = async$default.await(q5);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return ((K0) obj).c;
            }
        }
        q5 = new Q5(this, continuation);
        Object obj2 = q5.a;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = q5.c;
        if (i != 0) {
        }
        return ((K0) obj2).c;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onCreate(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(owner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onDestroy(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(owner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStart(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(owner, "owner");
        if (this.d.e) {
            X0.a("Already started!");
            return;
        }
        X0.a("SDK", "start: ");
        this.b.b();
        C0095g2 c0095g2 = this.d;
        c0095g2.e = true;
        c0095g2.a.add(B0.a);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStop(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(owner, "owner");
        X0.a("SDK", "stop: ");
        this.b.c();
        C0095g2 c0095g2 = this.d;
        c0095g2.a.add(D0.a);
        c0095g2.e = false;
    }
}
