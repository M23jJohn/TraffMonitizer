package com.traffmonetizer.client;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.WindowCompat;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.traffmonetizer.client.service.ServiceViewModel;
import com.traffmonetizer.client.ui.AppNavigationKt;
import com.traffmonetizer.client.ui.theme.ThemeKt;
import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import timber.log.Timber;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0014J\b\u0010\u0014\u001a\u00020\u0010H\u0014J\b\u0010\u0015\u001a\u00020\u0010H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/traffmonetizer/client/MainActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "authViewModel", "Lcom/traffmonetizer/client/AuthViewModel;", "getAuthViewModel", "()Lcom/traffmonetizer/client/AuthViewModel;", "authViewModel$delegate", "Lkotlin/Lazy;", "serviceViewModel", "Lcom/traffmonetizer/client/service/ServiceViewModel;", "getServiceViewModel", "()Lcom/traffmonetizer/client/service/ServiceViewModel;", "serviceViewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "setOrientation", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes3.dex */
public final class MainActivity extends Hilt_MainActivity {
    public static final int $stable = 8;

    /* renamed from: authViewModel$delegate, reason: from kotlin metadata */
    private final Lazy authViewModel;

    /* renamed from: serviceViewModel$delegate, reason: from kotlin metadata */
    private final Lazy serviceViewModel;

    public MainActivity() {
        final MainActivity mainActivity = this;
        final Function0 function0 = null;
        this.authViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(AuthViewModel.class), new Function0<ViewModelStore>() { // from class: com.traffmonetizer.client.MainActivity$special$$inlined$viewModels$default$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return ComponentActivity.this.getViewModelStore();
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.traffmonetizer.client.MainActivity$special$$inlined$viewModels$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return ComponentActivity.this.getDefaultViewModelProviderFactory();
            }
        }, new Function0<CreationExtras>() { // from class: com.traffmonetizer.client.MainActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                return (function02 == null || (creationExtras = (CreationExtras) function02.invoke()) == null) ? mainActivity.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
        this.serviceViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(ServiceViewModel.class), new Function0<ViewModelStore>() { // from class: com.traffmonetizer.client.MainActivity$special$$inlined$viewModels$default$5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return ComponentActivity.this.getViewModelStore();
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.traffmonetizer.client.MainActivity$special$$inlined$viewModels$default$4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return ComponentActivity.this.getDefaultViewModelProviderFactory();
            }
        }, new Function0<CreationExtras>() { // from class: com.traffmonetizer.client.MainActivity$special$$inlined$viewModels$default$6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                return (function02 == null || (creationExtras = (CreationExtras) function02.invoke()) == null) ? mainActivity.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
    }

    private final AuthViewModel getAuthViewModel() {
        return (AuthViewModel) this.authViewModel.getValue();
    }

    private final ServiceViewModel getServiceViewModel() {
        return (ServiceViewModel) this.serviceViewModel.getValue();
    }

    @Override // com.traffmonetizer.client.Hilt_MainActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Timber.INSTANCE.d("onCreate: ", new Object[0]);
        SplashScreen installSplashScreen = SplashScreen.INSTANCE.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setOrientation();
        MainActivity mainActivity = this;
        EdgeToEdge.enable$default(mainActivity, null, null, 3, null);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView()).setAppearanceLightStatusBars(true);
        installSplashScreen.setKeepOnScreenCondition(new SplashScreen.KeepOnScreenCondition() { // from class: com.traffmonetizer.client.MainActivity$$ExternalSyntheticLambda1
            @Override // androidx.core.splashscreen.SplashScreen.KeepOnScreenCondition
            public final boolean shouldKeepOnScreen() {
                boolean onCreate$lambda$1;
                onCreate$lambda$1 = MainActivity.onCreate$lambda$1(MainActivity.this);
                return onCreate$lambda$1;
            }
        });
        ComponentActivityKt.setContent$default(mainActivity, null, ComposableLambdaKt.composableLambdaInstance(2014871522, true, new Function2() { // from class: com.traffmonetizer.client.MainActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit onCreate$lambda$2;
                onCreate$lambda$2 = MainActivity.onCreate$lambda$2(MainActivity.this, (Composer) obj, ((Integer) obj2).intValue());
                return onCreate$lambda$2;
            }
        }), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$1(MainActivity mainActivity) {
        return mainActivity.getAuthViewModel().isLoggedIn().getValue() == null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$2(final MainActivity mainActivity, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C43@1583L99,43@1563L119:MainActivity.kt#xflsae");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2014871522, i, -1, "com.traffmonetizer.client.MainActivity.onCreate.<anonymous> (MainActivity.kt:43)");
            }
            ThemeKt.TraffmonetizerTheme(ComposableLambdaKt.rememberComposableLambda(956260154, true, new Function2() { // from class: com.traffmonetizer.client.MainActivity$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit onCreate$lambda$2$0;
                    onCreate$lambda$2$0 = MainActivity.onCreate$lambda$2$0(MainActivity.this, (Composer) obj, ((Integer) obj2).intValue());
                    return onCreate$lambda$2$0;
                }
            }, composer, 54), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$2$0(MainActivity mainActivity, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C44@1601L67:MainActivity.kt#xflsae");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(956260154, i, -1, "com.traffmonetizer.client.MainActivity.onCreate.<anonymous>.<anonymous> (MainActivity.kt:44)");
            }
            AppNavigationKt.AppNavigation(Modifier.INSTANCE, mainActivity.getAuthViewModel(), mainActivity.getServiceViewModel(), composer, (ServiceViewModel.$stable << 6) | 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    @Override // android.app.Activity
    protected void onPause() {
        Timber.INSTANCE.d("onPause", new Object[0]);
        super.onPause();
        if (isChangingConfigurations()) {
            return;
        }
        getServiceViewModel().onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        Timber.INSTANCE.d("onResume", new Object[0]);
        super.onResume();
        if (isChangingConfigurations()) {
            return;
        }
        getServiceViewModel().onResume();
    }

    private final void setOrientation() {
        setRequestedOrientation(getResources().getConfiguration().smallestScreenWidthDp >= 600 ? 10 : 1);
    }
}
