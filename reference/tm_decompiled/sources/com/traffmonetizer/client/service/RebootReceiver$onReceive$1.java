package com.traffmonetizer.client.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.traffmonetizer.client.preferences.model.AppPreferences;
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

/* compiled from: RebootReceiver.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.traffmonetizer.client.service.RebootReceiver$onReceive$1", f = "RebootReceiver.kt", i = {}, l = {29}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class RebootReceiver$onReceive$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Intent $intent;
    final /* synthetic */ BroadcastReceiver.PendingResult $pendingResult;
    int label;
    final /* synthetic */ RebootReceiver this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RebootReceiver$onReceive$1(RebootReceiver rebootReceiver, Intent intent, Context context, BroadcastReceiver.PendingResult pendingResult, Continuation<? super RebootReceiver$onReceive$1> continuation) {
        super(2, continuation);
        this.this$0 = rebootReceiver;
        this.$intent = intent;
        this.$context = context;
        this.$pendingResult = pendingResult;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RebootReceiver$onReceive$1(this.this$0, this.$intent, this.$context, this.$pendingResult, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RebootReceiver$onReceive$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
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
            if (!((AppPreferences) obj).getProxyActive()) {
                Timber.INSTANCE.d("onReceive: proxy is not active", new Object[0]);
            } else if (Intrinsics.areEqual(this.$intent.getAction(), "android.intent.action.BOOT_COMPLETED")) {
                Timber.INSTANCE.d("onReceive: ACTION_BOOT_COMPLETED", new Object[0]);
                this.this$0.startForegroundService(this.$context);
            } else if (Intrinsics.areEqual(this.$intent.getAction(), "android.intent.action.MY_PACKAGE_REPLACED")) {
                Timber.INSTANCE.d("onReceive: ACTION_MY_PACKAGE_REPLACED", new Object[0]);
                this.this$0.startForegroundService(this.$context);
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
