package androidx.activity.compose;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: ActivityResultRegistry.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001aM\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"rememberLauncherForActivityResult", "Landroidx/activity/compose/ManagedActivityResultLauncher;", "I", "O", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "onResult", "Lkotlin/Function1;", "", "(Landroidx/activity/result/contract/ActivityResultContract;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Landroidx/activity/compose/ManagedActivityResultLauncher;", "activity-compose_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ActivityResultRegistryKt {
    public static final <I, O> ManagedActivityResultLauncher<I, O> rememberLauncherForActivityResult(final ActivityResultContract<I, O> activityResultContract, Function1<? super O, Unit> function1, Composer composer, int i) {
        final ActivityResultRegistry activityResultRegistry;
        String str;
        ComposerKt.sourceInformationMarkerStart(composer, -1408504823, "C(rememberLauncherForActivityResult)84@3541L30,85@3598L30,89@3784L32,89@3767L49,92@3905L7,96@4088L46,97@4162L73,101@4418L176,101@4362L232:ActivityResultRegistry.kt#q1dkbc");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1408504823, i, -1, "androidx.activity.compose.rememberLauncherForActivityResult (ActivityResultRegistry.kt:82)");
        }
        State rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(activityResultContract, composer, i & 14);
        final State rememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(function1, composer, (i >> 3) & 14);
        Object[] objArr = new Object[0];
        ComposerKt.sourceInformationMarkerStart(composer, -658420343, "CC(remember):ActivityResultRegistry.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Function0() { // from class: androidx.activity.compose.ActivityResultRegistryKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    String rememberLauncherForActivityResult$lambda$1$lambda$0;
                    rememberLauncherForActivityResult$lambda$1$lambda$0 = ActivityResultRegistryKt.rememberLauncherForActivityResult$lambda$1$lambda$0();
                    return rememberLauncherForActivityResult$lambda$1$lambda$0;
                }
            };
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        final String str2 = (String) RememberSaveableKt.m4032rememberSaveable(objArr, (Saver) null, (String) null, (Function0) rememberedValue, composer, 3072, 6);
        ActivityResultRegistryOwner current = LocalActivityResultRegistryOwner.INSTANCE.getCurrent(composer, 6);
        if (current == null) {
            throw new IllegalStateException("No ActivityResultRegistryOwner was provided via LocalActivityResultRegistryOwner".toString());
        }
        ActivityResultRegistry activityResultRegistry2 = current.getActivityResultRegistry();
        ComposerKt.sourceInformationMarkerStart(composer, -658410601, "CC(remember):ActivityResultRegistry.kt#9igjgp");
        Object rememberedValue2 = composer.rememberedValue();
        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = new ActivityResultLauncherHolder();
            composer.updateRememberedValue(rememberedValue2);
        }
        final ActivityResultLauncherHolder activityResultLauncherHolder = (ActivityResultLauncherHolder) rememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -658408206, "CC(remember):ActivityResultRegistry.kt#9igjgp");
        Object rememberedValue3 = composer.rememberedValue();
        if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
            rememberedValue3 = new ManagedActivityResultLauncher(activityResultLauncherHolder, rememberUpdatedState);
            composer.updateRememberedValue(rememberedValue3);
        }
        ManagedActivityResultLauncher<I, O> managedActivityResultLauncher = (ManagedActivityResultLauncher) rememberedValue3;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -658399911, "CC(remember):ActivityResultRegistry.kt#9igjgp");
        boolean changedInstance = composer.changedInstance(activityResultLauncherHolder) | composer.changedInstance(activityResultRegistry2) | composer.changed(str2) | composer.changedInstance(activityResultContract) | composer.changed(rememberUpdatedState2);
        Object rememberedValue4 = composer.rememberedValue();
        if (changedInstance || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
            activityResultRegistry = activityResultRegistry2;
            Object obj = new Function1() { // from class: androidx.activity.compose.ActivityResultRegistryKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    DisposableEffectResult rememberLauncherForActivityResult$lambda$8$lambda$7;
                    rememberLauncherForActivityResult$lambda$8$lambda$7 = ActivityResultRegistryKt.rememberLauncherForActivityResult$lambda$8$lambda$7(ActivityResultLauncherHolder.this, activityResultRegistry, str2, activityResultContract, rememberUpdatedState2, (DisposableEffectScope) obj2);
                    return rememberLauncherForActivityResult$lambda$8$lambda$7;
                }
            };
            str = str2;
            composer.updateRememberedValue(obj);
            rememberedValue4 = obj;
        } else {
            str = str2;
            activityResultRegistry = activityResultRegistry2;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        ActivityResultRegistry activityResultRegistry3 = activityResultRegistry;
        EffectsKt.DisposableEffect(activityResultRegistry3, str, activityResultContract, (Function1) rememberedValue4, composer, (i << 6) & 896);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return managedActivityResultLauncher;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String rememberLauncherForActivityResult$lambda$1$lambda$0() {
        return UUID.randomUUID().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult rememberLauncherForActivityResult$lambda$8$lambda$7(final ActivityResultLauncherHolder activityResultLauncherHolder, ActivityResultRegistry activityResultRegistry, String str, ActivityResultContract activityResultContract, final State state, DisposableEffectScope disposableEffectScope) {
        activityResultLauncherHolder.setLauncher(activityResultRegistry.register(str, activityResultContract, new ActivityResultCallback() { // from class: androidx.activity.compose.ActivityResultRegistryKt$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ActivityResultRegistryKt.rememberLauncherForActivityResult$lambda$8$lambda$7$lambda$5(State.this, obj);
            }
        }));
        return new DisposableEffectResult() { // from class: androidx.activity.compose.ActivityResultRegistryKt$rememberLauncherForActivityResult$lambda$8$lambda$7$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                ActivityResultLauncherHolder.this.unregister();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void rememberLauncherForActivityResult$lambda$8$lambda$7$lambda$5(State state, Object obj) {
        ((Function1) state.getValue()).invoke(obj);
    }
}
