package com.traffmonetizer.client.service;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import timber.log.Timber;

/* compiled from: ServiceViewModel.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "isLoggedIn", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.traffmonetizer.client.service.ServiceViewModel$listenLoggedState$2", f = "ServiceViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class ServiceViewModel$listenLoggedState$2 extends SuspendLambda implements Function2<Boolean, Continuation<? super Unit>, Object> {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ ServiceViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ServiceViewModel$listenLoggedState$2(ServiceViewModel serviceViewModel, Continuation<? super ServiceViewModel$listenLoggedState$2> continuation) {
        super(2, continuation);
        this.this$0 = serviceViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ServiceViewModel$listenLoggedState$2 serviceViewModel$listenLoggedState$2 = new ServiceViewModel$listenLoggedState$2(this.this$0, continuation);
        serviceViewModel$listenLoggedState$2.Z$0 = ((Boolean) obj).booleanValue();
        return serviceViewModel$listenLoggedState$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Boolean bool, Continuation<? super Unit> continuation) {
        return invoke(bool.booleanValue(), continuation);
    }

    public final Object invoke(boolean z, Continuation<? super Unit> continuation) {
        return ((ServiceViewModel$listenLoggedState$2) create(Boolean.valueOf(z), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Timber.INSTANCE.d("isLoggedIn = " + z, new Object[0]);
        if (z) {
            this.this$0.bindService();
        } else {
            this.this$0.unbindService();
        }
        return Unit.INSTANCE;
    }
}
