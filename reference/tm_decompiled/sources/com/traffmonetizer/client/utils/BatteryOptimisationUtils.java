package com.traffmonetizer.client.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BatteryOptimisationUtils.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/traffmonetizer/client/utils/BatteryOptimisationUtils;", "", "<init>", "()V", "REQUEST_CODE", "", "isIgnoringBatteryOptimizations", "", "context", "Landroid/content/Context;", "openIgnoreBatteryOptimizationSettings", "", "activity", "Landroid/app/Activity;", "requestIgnoreBatteryOptimization", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BatteryOptimisationUtils {
    public static final int $stable = 0;
    public static final BatteryOptimisationUtils INSTANCE = new BatteryOptimisationUtils();
    private static final int REQUEST_CODE = 200;

    private BatteryOptimisationUtils() {
    }

    public final boolean isIgnoringBatteryOptimizations(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("power");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        return ((PowerManager) systemService).isIgnoringBatteryOptimizations(context.getPackageName());
    }

    public final void openIgnoreBatteryOptimizationSettings(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        activity.startActivityForResult(new Intent("android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS"), 200);
    }

    public final void requestIgnoreBatteryOptimization(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        activity.startActivityForResult(new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", Uri.parse("package:" + activity.getPackageName())), 200);
    }
}
