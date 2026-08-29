package com.tm;

import android.content.Context;
import android.provider.Settings;
import java.security.MessageDigest;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* renamed from: com.tm.p, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0155p {
    public final MutableStateFlow a;

    public C0155p(Context context, C0148o defaultConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(defaultConfig, "defaultConfig");
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(defaultConfig);
        this.a = MutableStateFlow;
        String deviceId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        Intrinsics.checkNotNullExpressionValue(deviceId, "deviceId");
        byte[] bytes = deviceId.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        byte[] digest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(digest, "getInstance(\"MD5\").digest(deviceId.toByteArray())");
        MutableStateFlow.setValue(C0148o.a(defaultConfig, null, false, digest, 0, null, false, null, false, null, 2043));
    }
}
