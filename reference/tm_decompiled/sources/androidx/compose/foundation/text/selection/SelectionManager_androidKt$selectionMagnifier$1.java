package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.PlatformMagnifierFactory;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: SelectionManager.android.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class SelectionManager_androidKt$selectionMagnifier$1 implements Function3<Modifier, Composer, Integer, Modifier> {
    final /* synthetic */ SelectionManager $manager;

    SelectionManager_androidKt$selectionMagnifier$1(SelectionManager selectionManager) {
        this.$manager = selectionManager;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
        return invoke(modifier, composer, num.intValue());
    }

    public final Modifier invoke(Modifier modifier, Composer composer, int i) {
        composer.startReplaceGroup(-1914520728);
        ComposerKt.sourceInformation(composer, "C54@2533L7,55@2570L41,57@2678L68,58@2780L540:SelectionManager.android.kt#eksfi3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1914520728, i, -1, "androidx.compose.foundation.text.selection.selectionMagnifier.<anonymous> (SelectionManager.android.kt:54)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object consume = composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composer);
        final Density density = (Density) consume;
        ComposerKt.sourceInformationMarkerStart(composer, -1608414511, "CC(remember):SelectionManager.android.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntSize.m7566boximpl(IntSize.INSTANCE.m7579getZeroYbymL2g()), null, 2, null);
            composer.updateRememberedValue(rememberedValue);
        }
        final MutableState mutableState = (MutableState) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -1608411028, "CC(remember):SelectionManager.android.kt#9igjgp");
        boolean changedInstance = composer.changedInstance(this.$manager);
        final SelectionManager selectionManager = this.$manager;
        Object rememberedValue2 = composer.rememberedValue();
        if (changedInstance || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Offset invoke$lambda$4$lambda$3;
                    invoke$lambda$4$lambda$3 = SelectionManager_androidKt$selectionMagnifier$1.invoke$lambda$4$lambda$3(SelectionManager.this, mutableState);
                    return invoke$lambda$4$lambda$3;
                }
            };
            composer.updateRememberedValue(rememberedValue2);
        }
        Function0 function0 = (Function0) rememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -1608407292, "CC(remember):SelectionManager.android.kt#9igjgp");
        boolean changed = composer.changed(density);
        Object rememberedValue3 = composer.rememberedValue();
        if (changed || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
            rememberedValue3 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Modifier invoke$lambda$9$lambda$8;
                    invoke$lambda$9$lambda$8 = SelectionManager_androidKt$selectionMagnifier$1.invoke$lambda$9$lambda$8(Density.this, mutableState, (Function0) obj);
                    return invoke$lambda$9$lambda$8;
                }
            };
            composer.updateRememberedValue(rememberedValue3);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        Modifier animatedSelectionMagnifier = SelectionMagnifierKt.animatedSelectionMagnifier(modifier, function0, (Function1) rememberedValue3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return animatedSelectionMagnifier;
    }

    private static final long invoke$lambda$1(MutableState<IntSize> mutableState) {
        return mutableState.getValue().m7578unboximpl();
    }

    private static final void invoke$lambda$2(MutableState<IntSize> mutableState, long j) {
        mutableState.setValue(IntSize.m7566boximpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset invoke$lambda$4$lambda$3(SelectionManager selectionManager, MutableState mutableState) {
        return Offset.m4332boximpl(SelectionManagerKt.m1836calculateSelectionMagnifierCenterAndroidO0kMr_c(selectionManager, invoke$lambda$1(mutableState)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier invoke$lambda$9$lambda$8(final Density density, final MutableState mutableState, final Function0 function0) {
        return Magnifier_androidKt.m378magnifierjPUL71Q$default(Modifier.INSTANCE, new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Offset invoke$lambda$9$lambda$8$lambda$5;
                invoke$lambda$9$lambda$8$lambda$5 = SelectionManager_androidKt$selectionMagnifier$1.invoke$lambda$9$lambda$8$lambda$5(Function0.this, (Density) obj);
                return invoke$lambda$9$lambda$8$lambda$5;
            }
        }, null, new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit invoke$lambda$9$lambda$8$lambda$7;
                invoke$lambda$9$lambda$8$lambda$7 = SelectionManager_androidKt$selectionMagnifier$1.invoke$lambda$9$lambda$8$lambda$7(Density.this, mutableState, (DpSize) obj);
                return invoke$lambda$9$lambda$8$lambda$7;
            }
        }, 0.0f, true, 0L, 0.0f, 0.0f, false, PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform(), 490, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset invoke$lambda$9$lambda$8$lambda$5(Function0 function0, Density density) {
        return (Offset) function0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$9$lambda$8$lambda$7(Density density, MutableState mutableState, DpSize dpSize) {
        invoke$lambda$2(mutableState, IntSize.m7569constructorimpl((density.mo453roundToPx0680j_4(DpSize.m7497getWidthD9Ej5fM(dpSize.getPackedValue())) << 32) | (density.mo453roundToPx0680j_4(DpSize.m7495getHeightD9Ej5fM(dpSize.getPackedValue())) & 4294967295L)));
        return Unit.INSTANCE;
    }
}
