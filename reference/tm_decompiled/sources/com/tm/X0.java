package com.tm;

import android.util.Log;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* loaded from: classes3.dex */
public final class X0 {
    public static boolean a;
    public static boolean b;
    public static final MutableStateFlow c = StateFlowKt.MutableStateFlow(new F0(-1, "initial"));

    public static void a(String message) {
        Intrinsics.checkNotNullParameter("SDK", "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        c.setValue(new F0(System.currentTimeMillis(), "SDK: ".concat(message)));
        Log.e("SDK", message);
    }

    public static void a(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        if (a) {
            c.setValue(new F0(System.currentTimeMillis(), tag + ": " + message));
            if (b) {
                Log.d("TraffmonetizerSDK:".concat(tag), message);
            }
        }
    }

    public static void a(String tag, String message, Throwable th) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        if (a) {
            c.setValue(new F0(System.currentTimeMillis(), tag + ": " + message));
            if (b) {
                Log.e("TraffmonetizerSDK:".concat(tag), message, th);
            }
        }
    }
}
