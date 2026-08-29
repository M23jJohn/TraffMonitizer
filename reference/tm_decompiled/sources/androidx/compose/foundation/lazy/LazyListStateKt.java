package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.DensityKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: LazyListState.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001a+\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\b\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000b\"\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"rememberLazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "initialFirstVisibleItemIndex", "", "initialFirstVisibleItemScrollOffset", "(IILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/LazyListState;", "prefetchStrategy", "Landroidx/compose/foundation/lazy/LazyListPrefetchStrategy;", "(IILandroidx/compose/foundation/lazy/LazyListPrefetchStrategy;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/LazyListState;", "cacheWindow", "Landroidx/compose/foundation/lazy/layout/LazyLayoutCacheWindow;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutCacheWindow;IILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/LazyListState;", "EmptyLazyListMeasureResult", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "NumberOfItemsToTeleport", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyListStateKt {
    private static final LazyListMeasureResult EmptyLazyListMeasureResult;
    private static final int NumberOfItemsToTeleport = 100;

    public static final LazyListState rememberLazyListState(final int i, final int i2, Composer composer, int i3, int i4) {
        ComposerKt.sourceInformationMarkerStart(composer, 1470655220, "C(rememberLazyListState)N(initialFirstVisibleItemIndex,initialFirstVisibleItemScrollOffset)78@3732L96,78@3686L142:LazyListState.kt#428nma");
        if ((i4 & 1) != 0) {
            i = 0;
        }
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1470655220, i3, -1, "androidx.compose.foundation.lazy.rememberLazyListState (LazyListState.kt:77)");
        }
        Object[] objArr = new Object[0];
        Saver<LazyListState, ?> saver = LazyListState.INSTANCE.getSaver();
        ComposerKt.sourceInformationMarkerStart(composer, 255432788, "CC(remember):LazyListState.kt#9igjgp");
        boolean z = true;
        boolean z2 = (((i3 & 14) ^ 6) > 4 && composer.changed(i)) || (i3 & 6) == 4;
        if ((((i3 & 112) ^ 48) <= 32 || !composer.changed(i2)) && (i3 & 48) != 32) {
            z = false;
        }
        boolean z3 = z2 | z;
        Object rememberedValue = composer.rememberedValue();
        if (z3 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Function0() { // from class: androidx.compose.foundation.lazy.LazyListStateKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    LazyListState rememberLazyListState$lambda$1$lambda$0;
                    rememberLazyListState$lambda$1$lambda$0 = LazyListStateKt.rememberLazyListState$lambda$1$lambda$0(i, i2);
                    return rememberLazyListState$lambda$1$lambda$0;
                }
            };
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        LazyListState lazyListState = (LazyListState) RememberSaveableKt.m4033rememberSaveable(objArr, (Saver) saver, (Function0) rememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return lazyListState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazyListState rememberLazyListState$lambda$1$lambda$0(int i, int i2) {
        return new LazyListState(i, i2);
    }

    public static final LazyListState rememberLazyListState(final int i, final int i2, final LazyListPrefetchStrategy lazyListPrefetchStrategy, Composer composer, int i3, int i4) {
        ComposerKt.sourceInformationMarkerStart(composer, 1287535208, "C(rememberLazyListState)N(initialFirstVisibleItemIndex,initialFirstVisibleItemScrollOffset,prefetchStrategy)100@4595L39,102@4748L161,102@4666L243:LazyListState.kt#428nma");
        if ((i4 & 1) != 0) {
            i = 0;
        }
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        boolean z = true;
        if ((i4 & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart(composer, 370760335, "CC(remember):LazyListState.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = LazyListPrefetchStrategyKt.LazyListPrefetchStrategy$default(0, 1, null);
                composer.updateRememberedValue(rememberedValue);
            }
            lazyListPrefetchStrategy = (LazyListPrefetchStrategy) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1287535208, i3, -1, "androidx.compose.foundation.lazy.rememberLazyListState (LazyListState.kt:101)");
        }
        Object[] objArr = {lazyListPrefetchStrategy};
        Saver<LazyListState, ?> saver$foundation_release = LazyListState.INSTANCE.saver$foundation_release(lazyListPrefetchStrategy);
        ComposerKt.sourceInformationMarkerStart(composer, 370765353, "CC(remember):LazyListState.kt#9igjgp");
        boolean z2 = ((((i3 & 14) ^ 6) > 4 && composer.changed(i)) || (i3 & 6) == 4) | ((((i3 & 112) ^ 48) > 32 && composer.changed(i2)) || (i3 & 48) == 32);
        if ((((i3 & 896) ^ 384) <= 256 || !composer.changedInstance(lazyListPrefetchStrategy)) && (i3 & 384) != 256) {
            z = false;
        }
        boolean z3 = z2 | z;
        Object rememberedValue2 = composer.rememberedValue();
        if (z3 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = new Function0() { // from class: androidx.compose.foundation.lazy.LazyListStateKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    LazyListState rememberLazyListState$lambda$4$lambda$3;
                    rememberLazyListState$lambda$4$lambda$3 = LazyListStateKt.rememberLazyListState$lambda$4$lambda$3(i, i2, lazyListPrefetchStrategy);
                    return rememberLazyListState$lambda$4$lambda$3;
                }
            };
            composer.updateRememberedValue(rememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        LazyListState lazyListState = (LazyListState) RememberSaveableKt.m4033rememberSaveable(objArr, (Saver) saver$foundation_release, (Function0) rememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return lazyListState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazyListState rememberLazyListState$lambda$4$lambda$3(int i, int i2, LazyListPrefetchStrategy lazyListPrefetchStrategy) {
        return new LazyListState(i, i2, lazyListPrefetchStrategy);
    }

    public static final LazyListState rememberLazyListState(final LazyLayoutCacheWindow lazyLayoutCacheWindow, final int i, final int i2, Composer composer, int i3, int i4) {
        ComposerKt.sourceInformationMarkerStart(composer, 1588550476, "C(rememberLazyListState)N(cacheWindow,initialFirstVisibleItemIndex,initialFirstVisibleItemScrollOffset)130@5782L156,130@5710L228:LazyListState.kt#428nma");
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1588550476, i3, -1, "androidx.compose.foundation.lazy.rememberLazyListState (LazyListState.kt:129)");
        }
        boolean z = true;
        Object[] objArr = {lazyLayoutCacheWindow};
        Saver<LazyListState, ?> saver$foundation_release = LazyListState.INSTANCE.saver$foundation_release(lazyLayoutCacheWindow);
        ComposerKt.sourceInformationMarkerStart(composer, 1883627144, "CC(remember):LazyListState.kt#9igjgp");
        boolean z2 = ((((i3 & 14) ^ 6) > 4 && composer.changed(lazyLayoutCacheWindow)) || (i3 & 6) == 4) | ((((i3 & 112) ^ 48) > 32 && composer.changed(i)) || (i3 & 48) == 32);
        if ((((i3 & 896) ^ 384) <= 256 || !composer.changed(i2)) && (i3 & 384) != 256) {
            z = false;
        }
        boolean z3 = z2 | z;
        Object rememberedValue = composer.rememberedValue();
        if (z3 || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Function0() { // from class: androidx.compose.foundation.lazy.LazyListStateKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    LazyListState rememberLazyListState$lambda$6$lambda$5;
                    rememberLazyListState$lambda$6$lambda$5 = LazyListStateKt.rememberLazyListState$lambda$6$lambda$5(LazyLayoutCacheWindow.this, i, i2);
                    return rememberLazyListState$lambda$6$lambda$5;
                }
            };
            composer.updateRememberedValue(rememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        LazyListState lazyListState = (LazyListState) RememberSaveableKt.m4033rememberSaveable(objArr, (Saver) saver$foundation_release, (Function0) rememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return lazyListState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazyListState rememberLazyListState$lambda$6$lambda$5(LazyLayoutCacheWindow lazyLayoutCacheWindow, int i, int i2) {
        return new LazyListState(lazyLayoutCacheWindow, i, i2);
    }

    static {
        MeasureResult measureResult = new MeasureResult() { // from class: androidx.compose.foundation.lazy.LazyListStateKt$EmptyLazyListMeasureResult$1
            private final Map<AlignmentLine, Integer> alignmentLines = MapsKt.emptyMap();
            private final int height;
            private final int width;

            public static /* synthetic */ void getAlignmentLines$annotations() {
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public /* synthetic */ Function1 getRulers() {
                return MeasureResult.CC.$default$getRulers(this);
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getWidth() {
                return this.width;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getHeight() {
                return this.height;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Map<AlignmentLine, Integer> getAlignmentLines() {
                return this.alignmentLines;
            }
        };
        MeasureResult measureResult2 = measureResult;
        EmptyLazyListMeasureResult = new LazyListMeasureResult(null, 0, false, 0.0f, measureResult2, 0.0f, false, CoroutineScopeKt.CoroutineScope(EmptyCoroutineContext.INSTANCE), DensityKt.Density$default(1.0f, 0.0f, 2, null), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null), CollectionsKt.emptyList(), 0, 0, 0, false, Orientation.Vertical, 0, 0, null);
    }
}
