package com.traffmonetizer.client.ui.main.settings;

import android.content.Context;
import androidx.compose.material3.SnackbarDuration;
import androidx.compose.material3.SnackbarHostState;
import com.traffmonetizer.client.R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: SettingsScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$SettingsScreen$1$1", f = "SettingsScreen.kt", i = {}, l = {56}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class SettingsScreenKt$SettingsScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ SettingsViewModel $settingsViewModel;
    final /* synthetic */ SnackbarHostState $snackbarHostState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SettingsScreenKt$SettingsScreen$1$1(SettingsViewModel settingsViewModel, CoroutineScope coroutineScope, SnackbarHostState snackbarHostState, Context context, Continuation<? super SettingsScreenKt$SettingsScreen$1$1> continuation) {
        super(2, continuation);
        this.$settingsViewModel = settingsViewModel;
        this.$scope = coroutineScope;
        this.$snackbarHostState = snackbarHostState;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SettingsScreenKt$SettingsScreen$1$1(this.$settingsViewModel, this.$scope, this.$snackbarHostState, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SettingsScreenKt$SettingsScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<String> error = this.$settingsViewModel.getError();
            final CoroutineScope coroutineScope = this.$scope;
            final SnackbarHostState snackbarHostState = this.$snackbarHostState;
            final Context context = this.$context;
            this.label = 1;
            if (error.collect(new FlowCollector() { // from class: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$SettingsScreen$1$1.1

                /* compiled from: SettingsScreen.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
                @DebugMetadata(c = "com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$SettingsScreen$1$1$1$1", f = "SettingsScreen.kt", i = {}, l = {58}, m = "invokeSuspend", n = {}, s = {}, v = 1)
                /* renamed from: com.traffmonetizer.client.ui.main.settings.SettingsScreenKt$SettingsScreen$1$1$1$1, reason: invalid class name and collision with other inner class name */
                static final class C00461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Context $context;
                    final /* synthetic */ SnackbarHostState $snackbarHostState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00461(SnackbarHostState snackbarHostState, Context context, Continuation<? super C00461> continuation) {
                        super(2, continuation);
                        this.$snackbarHostState = snackbarHostState;
                        this.$context = context;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C00461(this.$snackbarHostState, this.$context, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            SnackbarHostState snackbarHostState = this.$snackbarHostState;
                            String string = this.$context.getString(R.string.unable_to_rename_device);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                            this.label = 1;
                            if (SnackbarHostState.showSnackbar$default(snackbarHostState, string, null, false, SnackbarDuration.Short, this, 6, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((String) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(String str, Continuation<? super Unit> continuation) {
                    BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new C00461(snackbarHostState, context, null), 3, null);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
