package com.traffmonetizer.client.service;

import android.content.Context;
import com.traffmonetizer.client.preferences.model.AppPreferences;
import com.traffmonetizer.client.traffmonetizer_repository.TraffmonetizerRepository;
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

/* compiled from: TraffmonetizerForegroundService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.traffmonetizer.client.service.TraffmonetizerForegroundService$onStartCommand$2", f = "TraffmonetizerForegroundService.kt", i = {}, l = {110}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class TraffmonetizerForegroundService$onStartCommand$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ TraffmonetizerForegroundService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TraffmonetizerForegroundService$onStartCommand$2(TraffmonetizerForegroundService traffmonetizerForegroundService, Continuation<? super TraffmonetizerForegroundService$onStartCommand$2> continuation) {
        super(2, continuation);
        this.this$0 = traffmonetizerForegroundService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TraffmonetizerForegroundService$onStartCommand$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TraffmonetizerForegroundService$onStartCommand$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
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
        AppPreferences appPreferences = (AppPreferences) obj;
        TraffmonetizerRepository traffmonetizerRepository = this.this$0.getTraffmonetizerRepository();
        Context applicationContext = this.this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        traffmonetizerRepository.init(applicationContext, appPreferences.getToken(), appPreferences.getWifiOnly(), appPreferences.getProxyActive(), true);
        this.this$0.getTraffmonetizerRepository().start();
        if (appPreferences.getProxyActive()) {
            this.this$0.promoteToForeground();
        }
        return Unit.INSTANCE;
    }
}
