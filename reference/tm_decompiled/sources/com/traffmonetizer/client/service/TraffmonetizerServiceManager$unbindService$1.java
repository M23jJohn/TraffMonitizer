package com.traffmonetizer.client.service;

import android.content.Context;
import android.content.Intent;
import com.traffmonetizer.client.preferences.PreferencesRepository;
import com.traffmonetizer.client.preferences.model.AppPreferences;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: TraffmonetizerServiceManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.traffmonetizer.client.service.TraffmonetizerServiceManager$unbindService$1", f = "TraffmonetizerServiceManager.kt", i = {}, l = {106}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class TraffmonetizerServiceManager$unbindService$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ TraffmonetizerServiceManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TraffmonetizerServiceManager$unbindService$1(TraffmonetizerServiceManager traffmonetizerServiceManager, Continuation<? super TraffmonetizerServiceManager$unbindService$1> continuation) {
        super(2, continuation);
        this.this$0 = traffmonetizerServiceManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TraffmonetizerServiceManager$unbindService$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TraffmonetizerServiceManager$unbindService$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Context context;
        TraffmonetizerServiceManager$serviceConnection$1 traffmonetizerServiceManager$serviceConnection$1;
        PreferencesRepository preferencesRepository;
        Context context2;
        Context context3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            context = this.this$0.context;
            traffmonetizerServiceManager$serviceConnection$1 = this.this$0.serviceConnection;
            context.unbindService(traffmonetizerServiceManager$serviceConnection$1);
            preferencesRepository = this.this$0.preferencesRepository;
            this.label = 1;
            obj = FlowKt.first(preferencesRepository.getAppPreferences(), this);
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
            context2 = this.this$0.context;
            Intent intent = new Intent(context2, (Class<?>) TraffmonetizerForegroundService.class);
            context3 = this.this$0.context;
            context3.stopService(intent);
        }
        this.this$0.traffmonetizerService = null;
        return Unit.INSTANCE;
    }
}
