package com.traffmonetizer.client.ui;

import androidx.compose.runtime.State;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptionsBuilder;
import androidx.navigation.PopUpToBuilder;
import com.traffmonetizer.client.ui.Screen;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AppNavigation.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.traffmonetizer.client.ui.AppNavigationKt$LoginNavigation$1$1", f = "AppNavigation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class AppNavigationKt$LoginNavigation$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ State<Boolean> $isLoggedIn$delegate;
    final /* synthetic */ NavController $navController;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AppNavigationKt$LoginNavigation$1$1(NavController navController, State<Boolean> state, Continuation<? super AppNavigationKt$LoginNavigation$1$1> continuation) {
        super(2, continuation);
        this.$navController = navController;
        this.$isLoggedIn$delegate = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppNavigationKt$LoginNavigation$1$1(this.$navController, this.$isLoggedIn$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppNavigationKt$LoginNavigation$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Boolean LoginNavigation$lambda$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        NavDestination currentDestination = this.$navController.getCurrentDestination();
        final String route = currentDestination != null ? currentDestination.getRoute() : null;
        LoginNavigation$lambda$0 = AppNavigationKt.LoginNavigation$lambda$0(this.$isLoggedIn$delegate);
        if (Intrinsics.areEqual(LoginNavigation$lambda$0, Boxing.boxBoolean(true))) {
            this.$navController.navigate(Screen.MainGraph.INSTANCE.getRoute(), new Function1() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$LoginNavigation$1$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Unit invokeSuspend$lambda$0;
                    invokeSuspend$lambda$0 = AppNavigationKt$LoginNavigation$1$1.invokeSuspend$lambda$0(route, (NavOptionsBuilder) obj2);
                    return invokeSuspend$lambda$0;
                }
            });
        } else if (Intrinsics.areEqual(LoginNavigation$lambda$0, Boxing.boxBoolean(false))) {
            this.$navController.navigate(Screen.Login.INSTANCE.getRoute(), new Function1() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$LoginNavigation$1$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Unit invokeSuspend$lambda$1;
                    invokeSuspend$lambda$1 = AppNavigationKt$LoginNavigation$1$1.invokeSuspend$lambda$1(route, (NavOptionsBuilder) obj2);
                    return invokeSuspend$lambda$1;
                }
            });
        } else if (LoginNavigation$lambda$0 != null) {
            throw new NoWhenBranchMatchedException();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(String str, NavOptionsBuilder navOptionsBuilder) {
        if (str == null) {
            str = Screen.Login.INSTANCE.getRoute();
        }
        navOptionsBuilder.popUpTo(str, new Function1() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$LoginNavigation$1$1$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit invokeSuspend$lambda$0$0;
                invokeSuspend$lambda$0$0 = AppNavigationKt$LoginNavigation$1$1.invokeSuspend$lambda$0$0((PopUpToBuilder) obj);
                return invokeSuspend$lambda$0$0;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0$0(PopUpToBuilder popUpToBuilder) {
        popUpToBuilder.setInclusive(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(String str, NavOptionsBuilder navOptionsBuilder) {
        if (str == null) {
            str = Screen.MainGraph.INSTANCE.getRoute();
        }
        navOptionsBuilder.popUpTo(str, new Function1() { // from class: com.traffmonetizer.client.ui.AppNavigationKt$LoginNavigation$1$1$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit invokeSuspend$lambda$1$0;
                invokeSuspend$lambda$1$0 = AppNavigationKt$LoginNavigation$1$1.invokeSuspend$lambda$1$0((PopUpToBuilder) obj);
                return invokeSuspend$lambda$1$0;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1$0(PopUpToBuilder popUpToBuilder) {
        popUpToBuilder.setInclusive(true);
        return Unit.INSTANCE;
    }
}
