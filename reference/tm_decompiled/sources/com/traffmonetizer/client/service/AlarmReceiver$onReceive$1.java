package com.traffmonetizer.client.service;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import com.traffmonetizer.client.preferences.model.AppPreferences;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import timber.log.Timber;

/* compiled from: AlarmReceiver.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.traffmonetizer.client.service.AlarmReceiver$onReceive$1", f = "AlarmReceiver.kt", i = {}, l = {67}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class AlarmReceiver$onReceive$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ BroadcastReceiver.PendingResult $pendingResult;
    int label;
    final /* synthetic */ AlarmReceiver this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AlarmReceiver$onReceive$1(AlarmReceiver alarmReceiver, Context context, BroadcastReceiver.PendingResult pendingResult, Continuation<? super AlarmReceiver$onReceive$1> continuation) {
        super(2, continuation);
        this.this$0 = alarmReceiver;
        this.$context = context;
        this.$pendingResult = pendingResult;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AlarmReceiver$onReceive$1(this.this$0, this.$context, this.$pendingResult, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AlarmReceiver$onReceive$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        boolean z = true;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = FlowKt.first(this.this$0.getPreferencesRepository().getAppPreferences(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            boolean proxyActive = ((AppPreferences) obj).getProxyActive();
            Object systemService = this.$context.getSystemService("activity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
            List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE);
            Intrinsics.checkNotNullExpressionValue(runningServices, "getRunningServices(...)");
            List<ActivityManager.RunningServiceInfo> list = runningServices;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(((ActivityManager.RunningServiceInfo) it.next()).service.getClassName(), TraffmonetizerForegroundService.class.getName())) {
                        break;
                    }
                }
            }
            z = false;
            if (!z && proxyActive) {
                this.this$0.startForegroundService(this.$context);
            } else {
                Timber.INSTANCE.d("onReceive: ignore alarm, serviceRunning=" + z + ", proxyActive=" + proxyActive, new Object[0]);
            }
        } finally {
            try {
                this.$pendingResult.finish();
                return Unit.INSTANCE;
            } catch (Throwable th) {
            }
        }
        this.$pendingResult.finish();
        return Unit.INSTANCE;
    }
}
