package com.traffmonetizer.client.ui.main.settings;

import androidx.compose.material3.MenuKt;
import com.traffmonetizer.client.service.TraffmonetizerServiceManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: SettingsViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.traffmonetizer.client.ui.main.settings.SettingsViewModel$setDeviceName$2", f = "SettingsViewModel.kt", i = {1}, l = {110, MenuKt.InTransitionDuration}, m = "invokeSuspend", n = {"ex"}, s = {"L$0"}, v = 1)
/* loaded from: classes3.dex */
final class SettingsViewModel$setDeviceName$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $newName;
    Object L$0;
    int label;
    final /* synthetic */ SettingsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SettingsViewModel$setDeviceName$2(SettingsViewModel settingsViewModel, String str, Continuation<? super SettingsViewModel$setDeviceName$2> continuation) {
        super(2, continuation);
        this.this$0 = settingsViewModel;
        this.$newName = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SettingsViewModel$setDeviceName$2(this.this$0, this.$newName, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SettingsViewModel$setDeviceName$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a7, code lost:
    
        if (r0.send(r0.getMessage(), r12) != r1) goto L28;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object value;
        SettingsState settingsState;
        Channel channel;
        Object value2;
        SettingsState settingsState2;
        TraffmonetizerServiceManager traffmonetizerServiceManager;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Throwable th) {
            MutableStateFlow mutableStateFlow = this.this$0.settingsStateFlow;
            do {
                value = mutableStateFlow.getValue();
                settingsState = (SettingsState) value;
            } while (!mutableStateFlow.compareAndSet(value, SettingsState.copy$default(settingsState, false, false, DeviceNameState.copy$default(settingsState.getDeviceName(), null, DeviceNameStatus.SHOWING, 1, null), 3, null)));
            channel = this.this$0.errorChannel;
            this.L$0 = SpillingKt.nullOutSpilledVariable(th);
            this.label = 2;
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow mutableStateFlow2 = this.this$0.settingsStateFlow;
            do {
                value2 = mutableStateFlow2.getValue();
                settingsState2 = (SettingsState) value2;
            } while (!mutableStateFlow2.compareAndSet(value2, SettingsState.copy$default(settingsState2, false, false, DeviceNameState.copy$default(settingsState2.getDeviceName(), null, DeviceNameStatus.SUBMITTING, 1, null), 3, null)));
            traffmonetizerServiceManager = this.this$0.traffmonetizerServiceManager;
            this.label = 1;
            if (traffmonetizerServiceManager.setDeviceName(this.$newName, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.getDeviceName();
        return Unit.INSTANCE;
    }
}
