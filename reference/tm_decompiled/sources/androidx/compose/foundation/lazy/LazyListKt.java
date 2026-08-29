package androidx.compose.foundation.lazy;

import android.os.Trace;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.ScrollingContainerKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.CacheWindowLogic;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.foundation.lazy.layout.StickyItemsPlacement;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyList.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a¢\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00010\u001b¢\u0006\u0002\b\u001dH\u0001¢\u0006\u0002\u0010\u001e\u001a\u0085\u0001\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0003¢\u0006\u0002\u0010*\u001a\"\u0010+\u001a\u00020\u0001*\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u00100\u001a\u000201H\u0002¨\u00062"}, d2 = {"LazyList", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/LazyListState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "isVertical", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "beyondBoundsItemCount", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyListScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/OverscrollEffect;ILandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "rememberLazyListMeasurePolicy", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/LazyListItemProvider;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "stickyItemsPlacement", "Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZZILandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "keepAroundItems", "Landroidx/compose/foundation/lazy/layout/CacheWindowLogic;", "visibleItemsList", "", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "measuredItemProvider", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyListKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LazyList$lambda$0(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, boolean z2, FlingBehavior flingBehavior, boolean z3, OverscrollEffect overscrollEffect, int i, Alignment.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, Arrangement.Horizontal horizontal2, Function1 function1, int i2, int i3, int i4, Composer composer, int i5) {
        LazyList(modifier, lazyListState, paddingValues, z, z2, flingBehavior, z3, overscrollEffect, i, horizontal, vertical, vertical2, horizontal2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void LazyList(final Modifier modifier, LazyListState lazyListState, final PaddingValues paddingValues, final boolean z, final boolean z2, final FlingBehavior flingBehavior, final boolean z3, final OverscrollEffect overscrollEffect, int i, Alignment.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, Arrangement.Horizontal horizontal2, final Function1<? super LazyListScope, Unit> function1, Composer composer, final int i2, final int i3, final int i4) {
        int i5;
        PaddingValues paddingValues2;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        Composer composer2;
        LazyListState lazyListState2;
        final Alignment.Horizontal horizontal3;
        final Arrangement.Vertical vertical3;
        final Alignment.Vertical vertical4;
        final Arrangement.Horizontal horizontal4;
        final int i15;
        ScopeUpdateScope endRestartGroup;
        Alignment.Horizontal horizontal5;
        Arrangement.Vertical vertical5;
        int i16;
        Alignment.Vertical vertical6;
        Arrangement.Horizontal horizontal6;
        Object rememberedValue;
        Modifier.Companion companion;
        Modifier scrollingContainer;
        int i17;
        Composer startRestartGroup = composer.startRestartGroup(924924659);
        ComposerKt.sourceInformation(startRestartGroup, "C(LazyList)N(modifier,state,contentPadding,reverseLayout,isVertical,flingBehavior,userScrollEnabled,overscrollEffect,beyondBoundsItemCount,horizontalAlignment,verticalArrangement,verticalAlignment,horizontalArrangement,content)86@4193L50,88@4269L48,89@4343L24,90@4415L7,91@4484L7,94@4525L484,133@5813L302,128@5644L1176:LazyList.kt#428nma");
        if ((i4 & 1) != 0) {
            i5 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i5 = (startRestartGroup.changed(modifier) ? 4 : 2) | i2;
        } else {
            i5 = i2;
        }
        if ((i4 & 2) != 0) {
            i5 |= 48;
        } else if ((i2 & 48) == 0) {
            i5 |= startRestartGroup.changed(lazyListState) ? 32 : 16;
        }
        if ((i4 & 4) != 0) {
            i5 |= 384;
        } else if ((i2 & 384) == 0) {
            paddingValues2 = paddingValues;
            i5 |= startRestartGroup.changed(paddingValues2) ? 256 : 128;
            if ((i4 & 8) == 0) {
                i5 |= 3072;
            } else if ((i2 & 3072) == 0) {
                i5 |= startRestartGroup.changed(z) ? 2048 : 1024;
            }
            if ((i4 & 16) == 0) {
                i5 |= 24576;
            } else if ((i2 & 24576) == 0) {
                i5 |= startRestartGroup.changed(z2) ? 16384 : 8192;
            }
            if ((i4 & 32) == 0) {
                i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                i5 |= startRestartGroup.changed(flingBehavior) ? 131072 : 65536;
                if ((i4 & 64) != 0) {
                    i5 |= 1572864;
                } else if ((i2 & 1572864) == 0) {
                    i5 |= startRestartGroup.changed(z3) ? 1048576 : 524288;
                }
                if ((i4 & 128) != 0) {
                    i5 |= 12582912;
                } else if ((i2 & 12582912) == 0) {
                    i5 |= startRestartGroup.changed(overscrollEffect) ? 8388608 : 4194304;
                    if ((i2 & 100663296) != 0) {
                        if ((i4 & 256) == 0) {
                            i6 = i;
                            if (startRestartGroup.changed(i6)) {
                                i17 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                                i5 |= i17;
                            }
                        } else {
                            i6 = i;
                        }
                        i17 = 33554432;
                        i5 |= i17;
                    } else {
                        i6 = i;
                    }
                    i7 = i4 & 512;
                    if (i7 == 0) {
                        i5 |= 805306368;
                    } else if ((i2 & 805306368) == 0) {
                        i5 |= startRestartGroup.changed(horizontal) ? 536870912 : 268435456;
                    }
                    i8 = i4 & 1024;
                    if (i8 == 0) {
                        i10 = i3 | 6;
                        i9 = i8;
                    } else if ((i3 & 6) == 0) {
                        i9 = i8;
                        i10 = i3 | (startRestartGroup.changed(vertical) ? 4 : 2);
                    } else {
                        i9 = i8;
                        i10 = i3;
                    }
                    i11 = i4 & 2048;
                    if (i11 == 0) {
                        i10 |= 48;
                        i12 = i11;
                    } else if ((i3 & 48) == 0) {
                        i12 = i11;
                        i10 |= startRestartGroup.changed(vertical2) ? 32 : 16;
                    } else {
                        i12 = i11;
                    }
                    int i18 = i10;
                    i13 = i4 & 4096;
                    if (i13 == 0) {
                        i14 = i18 | 384;
                    } else {
                        int i19 = i18;
                        if ((i3 & 384) == 0) {
                            i19 |= startRestartGroup.changed(horizontal2) ? 256 : 128;
                        }
                        i14 = i19;
                    }
                    if ((i4 & 8192) == 0) {
                        i14 |= 3072;
                    } else if ((i3 & 3072) == 0) {
                        i14 |= startRestartGroup.changedInstance(function1) ? 2048 : 1024;
                    }
                    if (!startRestartGroup.shouldExecute((306783379 & i5) == 306783378 || (i14 & 1171) != 1170, i5 & 1)) {
                        startRestartGroup.startDefaults();
                        ComposerKt.sourceInformation(startRestartGroup, "74@3490L38");
                        if ((i2 & 1) == 0 || startRestartGroup.getDefaultsInvalid()) {
                            if ((i4 & 256) != 0) {
                                i5 &= -234881025;
                                i6 = LazyList_androidKt.defaultLazyListBeyondBoundsItemCount(startRestartGroup, 0);
                            }
                            Alignment.Horizontal horizontal7 = i7 != 0 ? null : horizontal;
                            horizontal5 = horizontal7;
                            vertical5 = i9 != 0 ? null : vertical;
                            i16 = i5;
                            vertical6 = i12 != 0 ? null : vertical2;
                            if (i13 != 0) {
                                horizontal6 = null;
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(924924659, i16, i14, "androidx.compose.foundation.lazy.LazyList (LazyList.kt:85)");
                                }
                                int i20 = (i16 >> 3) & 14;
                                Function0<LazyListItemProvider> rememberLazyListItemProviderLambda = LazyListItemProviderKt.rememberLazyListItemProviderLambda(lazyListState, function1, startRestartGroup, i20 | ((i14 >> 6) & 112));
                                int i21 = i16 >> 9;
                                int i22 = i14;
                                LazyLayoutSemanticState rememberLazyListSemanticState = LazyListSemanticsKt.rememberLazyListSemanticState(lazyListState, z2, startRestartGroup, i20 | (i21 & 112));
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 683737348, "CC(remember):Effects.kt#9igjgp");
                                rememberedValue = startRestartGroup.rememberedValue();
                                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, startRestartGroup);
                                    startRestartGroup.updateRememberedValue(rememberedValue);
                                }
                                CoroutineScope coroutineScope = (CoroutineScope) rememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ProvidableCompositionLocal<GraphicsContext> localGraphicsContext = CompositionLocalsKt.getLocalGraphicsContext();
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                Object consume = startRestartGroup.consume(localGraphicsContext);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                GraphicsContext graphicsContext = (GraphicsContext) consume;
                                CompositionLocal<Boolean> localScrollCaptureInProgress = CompositionLocalsKt.getLocalScrollCaptureInProgress();
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                Object consume2 = startRestartGroup.consume(localScrollCaptureInProgress);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                int i23 = i22 << 18;
                                int i24 = (65520 & i16) | (458752 & i21) | (3670016 & i21) | (29360128 & i23) | (i23 & 234881024) | ((i22 << 27) & 1879048192);
                                int i25 = i6;
                                StickyItemsPlacement stickToTopPlacement = ((Boolean) consume2).booleanValue() ? StickyItemsPlacement.INSTANCE.getStickToTopPlacement() : null;
                                int i26 = i16;
                                LazyLayoutMeasurePolicy rememberLazyListMeasurePolicy = rememberLazyListMeasurePolicy(rememberLazyListItemProviderLambda, lazyListState, paddingValues2, z, z2, i25, horizontal5, vertical6, horizontal6, vertical5, coroutineScope, graphicsContext, stickToTopPlacement, startRestartGroup, i24, 0);
                                Alignment.Horizontal horizontal8 = horizontal5;
                                Alignment.Vertical vertical7 = vertical6;
                                Arrangement.Horizontal horizontal9 = horizontal6;
                                Arrangement.Vertical vertical8 = vertical5;
                                lazyListState2 = lazyListState;
                                Orientation orientation = !z2 ? Orientation.Vertical : Orientation.Horizontal;
                                if (!z3) {
                                    startRestartGroup.startReplaceGroup(-2077085864);
                                    ComposerKt.sourceInformation(startRestartGroup, "116@5260L166");
                                    companion = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.INSTANCE, LazyListBeyondBoundsModifierKt.rememberLazyListBeyondBoundsState(lazyListState2, i25, startRestartGroup, i20 | ((i26 >> 21) & 112)), lazyListState2.getBeyondBoundsInfo(), z, orientation);
                                    startRestartGroup.endReplaceGroup();
                                } else {
                                    startRestartGroup.startReplaceGroup(-2076657041);
                                    startRestartGroup.endReplaceGroup();
                                    companion = Modifier.INSTANCE;
                                }
                                scrollingContainer = ScrollingContainerKt.scrollingContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(lazyListState2.getRemeasurementModifier()).then(lazyListState2.getAwaitLayoutModifier()), rememberLazyListItemProviderLambda, rememberLazyListSemanticState, orientation, z3, z, startRestartGroup, ((i26 >> 6) & 57344) | ((i26 << 6) & 458752)).then(companion).then(lazyListState2.getItemAnimator$foundation_release().getModifier()), lazyListState2, orientation, z3, z, flingBehavior, lazyListState2.getInternalInteractionSource(), false, overscrollEffect, (r21 & 256) != 0 ? null : null);
                                composer2 = startRestartGroup;
                                LazyLayoutKt.LazyLayout(rememberLazyListItemProviderLambda, scrollingContainer, lazyListState2.getPrefetchState(), rememberLazyListMeasurePolicy, composer2, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                i15 = i25;
                                horizontal3 = horizontal8;
                                vertical4 = vertical7;
                                horizontal4 = horizontal9;
                                vertical3 = vertical8;
                            }
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            if ((i4 & 256) != 0) {
                                i5 &= -234881025;
                            }
                            horizontal5 = horizontal;
                            vertical5 = vertical;
                            vertical6 = vertical2;
                            i16 = i5;
                        }
                        horizontal6 = horizontal2;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        int i202 = (i16 >> 3) & 14;
                        Function0<LazyListItemProvider> rememberLazyListItemProviderLambda2 = LazyListItemProviderKt.rememberLazyListItemProviderLambda(lazyListState, function1, startRestartGroup, i202 | ((i14 >> 6) & 112));
                        int i212 = i16 >> 9;
                        int i222 = i14;
                        LazyLayoutSemanticState rememberLazyListSemanticState2 = LazyListSemanticsKt.rememberLazyListSemanticState(lazyListState, z2, startRestartGroup, i202 | (i212 & 112));
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 683737348, "CC(remember):Effects.kt#9igjgp");
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                        }
                        CoroutineScope coroutineScope2 = (CoroutineScope) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext2 = CompositionLocalsKt.getLocalGraphicsContext();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                        Object consume3 = startRestartGroup.consume(localGraphicsContext2);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        GraphicsContext graphicsContext2 = (GraphicsContext) consume3;
                        CompositionLocal<Boolean> localScrollCaptureInProgress2 = CompositionLocalsKt.getLocalScrollCaptureInProgress();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                        Object consume22 = startRestartGroup.consume(localScrollCaptureInProgress2);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        int i232 = i222 << 18;
                        int i242 = (65520 & i16) | (458752 & i212) | (3670016 & i212) | (29360128 & i232) | (i232 & 234881024) | ((i222 << 27) & 1879048192);
                        int i252 = i6;
                        StickyItemsPlacement stickToTopPlacement2 = ((Boolean) consume22).booleanValue() ? StickyItemsPlacement.INSTANCE.getStickToTopPlacement() : null;
                        int i262 = i16;
                        LazyLayoutMeasurePolicy rememberLazyListMeasurePolicy2 = rememberLazyListMeasurePolicy(rememberLazyListItemProviderLambda2, lazyListState, paddingValues2, z, z2, i252, horizontal5, vertical6, horizontal6, vertical5, coroutineScope2, graphicsContext2, stickToTopPlacement2, startRestartGroup, i242, 0);
                        Alignment.Horizontal horizontal82 = horizontal5;
                        Alignment.Vertical vertical72 = vertical6;
                        Arrangement.Horizontal horizontal92 = horizontal6;
                        Arrangement.Vertical vertical82 = vertical5;
                        lazyListState2 = lazyListState;
                        Orientation orientation2 = !z2 ? Orientation.Vertical : Orientation.Horizontal;
                        if (!z3) {
                        }
                        scrollingContainer = ScrollingContainerKt.scrollingContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(lazyListState2.getRemeasurementModifier()).then(lazyListState2.getAwaitLayoutModifier()), rememberLazyListItemProviderLambda2, rememberLazyListSemanticState2, orientation2, z3, z, startRestartGroup, ((i262 >> 6) & 57344) | ((i262 << 6) & 458752)).then(companion).then(lazyListState2.getItemAnimator$foundation_release().getModifier()), lazyListState2, orientation2, z3, z, flingBehavior, lazyListState2.getInternalInteractionSource(), false, overscrollEffect, (r21 & 256) != 0 ? null : null);
                        composer2 = startRestartGroup;
                        LazyLayoutKt.LazyLayout(rememberLazyListItemProviderLambda2, scrollingContainer, lazyListState2.getPrefetchState(), rememberLazyListMeasurePolicy2, composer2, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        i15 = i252;
                        horizontal3 = horizontal82;
                        vertical4 = vertical72;
                        horizontal4 = horizontal92;
                        vertical3 = vertical82;
                    } else {
                        composer2 = startRestartGroup;
                        lazyListState2 = lazyListState;
                        composer2.skipToGroupEnd();
                        horizontal3 = horizontal;
                        vertical3 = vertical;
                        vertical4 = vertical2;
                        horizontal4 = horizontal2;
                        i15 = i6;
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                        final LazyListState lazyListState3 = lazyListState2;
                        endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.LazyListKt$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                Unit LazyList$lambda$0;
                                LazyList$lambda$0 = LazyListKt.LazyList$lambda$0(Modifier.this, lazyListState3, paddingValues, z, z2, flingBehavior, z3, overscrollEffect, i15, horizontal3, vertical3, vertical4, horizontal4, function1, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                return LazyList$lambda$0;
                            }
                        });
                        return;
                    }
                    return;
                }
                if ((i2 & 100663296) != 0) {
                }
                i7 = i4 & 512;
                if (i7 == 0) {
                }
                i8 = i4 & 1024;
                if (i8 == 0) {
                }
                i11 = i4 & 2048;
                if (i11 == 0) {
                }
                int i182 = i10;
                i13 = i4 & 4096;
                if (i13 == 0) {
                }
                if ((i4 & 8192) == 0) {
                }
                if (!startRestartGroup.shouldExecute((306783379 & i5) == 306783378 || (i14 & 1171) != 1170, i5 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            if ((i4 & 64) != 0) {
            }
            if ((i4 & 128) != 0) {
            }
            if ((i2 & 100663296) != 0) {
            }
            i7 = i4 & 512;
            if (i7 == 0) {
            }
            i8 = i4 & 1024;
            if (i8 == 0) {
            }
            i11 = i4 & 2048;
            if (i11 == 0) {
            }
            int i1822 = i10;
            i13 = i4 & 4096;
            if (i13 == 0) {
            }
            if ((i4 & 8192) == 0) {
            }
            if (!startRestartGroup.shouldExecute((306783379 & i5) == 306783378 || (i14 & 1171) != 1170, i5 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        paddingValues2 = paddingValues;
        if ((i4 & 8) == 0) {
        }
        if ((i4 & 16) == 0) {
        }
        if ((i4 & 32) == 0) {
        }
        if ((i4 & 64) != 0) {
        }
        if ((i4 & 128) != 0) {
        }
        if ((i2 & 100663296) != 0) {
        }
        i7 = i4 & 512;
        if (i7 == 0) {
        }
        i8 = i4 & 1024;
        if (i8 == 0) {
        }
        i11 = i4 & 2048;
        if (i11 == 0) {
        }
        int i18222 = i10;
        i13 = i4 & 4096;
        if (i13 == 0) {
        }
        if ((i4 & 8192) == 0) {
        }
        if (!startRestartGroup.shouldExecute((306783379 & i5) == 306783378 || (i14 & 1171) != 1170, i5 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0078, code lost:
    
        if (r34.changed(r4) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00af, code lost:
    
        if (r34.changed(r9) == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00cb, code lost:
    
        if (r34.changed(r12) == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00e7, code lost:
    
        if (r34.changed(r13) == false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0103, code lost:
    
        if (r34.changed(r5) == false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0122, code lost:
    
        if (r34.changed(r6) == false) goto L93;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final LazyLayoutMeasurePolicy rememberLazyListMeasurePolicy(Function0<? extends LazyListItemProvider> function0, LazyListState lazyListState, PaddingValues paddingValues, boolean z, boolean z2, int i, Alignment.Horizontal horizontal, Alignment.Vertical vertical, Arrangement.Horizontal horizontal2, Arrangement.Vertical vertical2, CoroutineScope coroutineScope, GraphicsContext graphicsContext, StickyItemsPlacement stickyItemsPlacement, Composer composer, int i2, int i3) {
        boolean z3;
        boolean z4;
        Alignment.Horizontal horizontal3;
        boolean z5;
        Alignment.Vertical vertical3;
        boolean z6;
        Arrangement.Horizontal horizontal4;
        boolean z7;
        Arrangement.Vertical vertical4;
        boolean z8;
        StickyItemsPlacement stickyItemsPlacement2;
        boolean z9;
        boolean z10;
        LazyListKt$rememberLazyListMeasurePolicy$1$1 rememberedValue;
        ComposerKt.sourceInformationMarkerStart(composer, 406165748, "C(rememberLazyListMeasurePolicy)N(itemProviderLambda,state,contentPadding,reverseLayout,isVertical,beyondBoundsItemCount,horizontalAlignment,verticalAlignment,horizontalArrangement,verticalArrangement,coroutineScope,graphicsContext,stickyItemsPlacement)188@8087L9831:LazyList.kt#428nma");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(406165748, i2, i3, "androidx.compose.foundation.lazy.rememberLazyListMeasurePolicy (LazyList.kt:188)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -516689861, "CC(remember):LazyList.kt#9igjgp");
        boolean z11 = ((((i2 & 112) ^ 48) > 32 && composer.changed(lazyListState)) || (i2 & 48) == 32) | ((((i2 & 896) ^ 384) > 256 && composer.changed(paddingValues)) || (i2 & 384) == 256) | ((((i2 & 7168) ^ 3072) > 2048 && composer.changed(z)) || (i2 & 3072) == 2048);
        if (((57344 & i2) ^ 24576) > 16384) {
            z3 = z2;
        } else {
            z3 = z2;
        }
        if ((i2 & 24576) != 16384) {
            z4 = false;
            boolean z12 = z11 | z4 | ((((458752 & i2) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072 && composer.changed(i)) || (i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072);
            if (((3670016 & i2) ^ 1572864) <= 1048576) {
                horizontal3 = horizontal;
            } else {
                horizontal3 = horizontal;
            }
            if ((1572864 & i2) != 1048576) {
                z5 = false;
                boolean z13 = z12 | z5;
                if (((29360128 & i2) ^ 12582912) > 8388608) {
                    vertical3 = vertical;
                } else {
                    vertical3 = vertical;
                }
                if ((12582912 & i2) != 8388608) {
                    z6 = false;
                    boolean z14 = z13 | z6;
                    if (((234881024 & i2) ^ 100663296) <= 67108864) {
                        horizontal4 = horizontal2;
                    } else {
                        horizontal4 = horizontal2;
                    }
                    if ((100663296 & i2) != 67108864) {
                        z7 = false;
                        boolean z15 = z14 | z7;
                        if (((1879048192 & i2) ^ 805306368) > 536870912) {
                            vertical4 = vertical2;
                        } else {
                            vertical4 = vertical2;
                        }
                        if ((i2 & 805306368) != 536870912) {
                            z8 = false;
                            boolean changed = z8 | z15 | composer.changed(graphicsContext);
                            if (((i3 & 896) ^ 384) <= 256) {
                                stickyItemsPlacement2 = stickyItemsPlacement;
                            } else {
                                stickyItemsPlacement2 = stickyItemsPlacement;
                            }
                            if ((i3 & 384) != 256) {
                                z9 = false;
                                z10 = changed | z9;
                                rememberedValue = composer.rememberedValue();
                                if (!z10 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                                    composer.updateRememberedValue(rememberedValue);
                                }
                                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy = (LazyLayoutMeasurePolicy) rememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                return lazyLayoutMeasurePolicy;
                            }
                            z9 = true;
                            z10 = changed | z9;
                            rememberedValue = composer.rememberedValue();
                            if (!z10) {
                            }
                            rememberedValue = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                            composer.updateRememberedValue(rememberedValue);
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy2 = (LazyLayoutMeasurePolicy) rememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            return lazyLayoutMeasurePolicy2;
                        }
                        z8 = true;
                        boolean changed2 = z8 | z15 | composer.changed(graphicsContext);
                        if (((i3 & 896) ^ 384) <= 256) {
                        }
                        if ((i3 & 384) != 256) {
                        }
                        z9 = true;
                        z10 = changed2 | z9;
                        rememberedValue = composer.rememberedValue();
                        if (!z10) {
                        }
                        rememberedValue = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                        composer.updateRememberedValue(rememberedValue);
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy22 = (LazyLayoutMeasurePolicy) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        return lazyLayoutMeasurePolicy22;
                    }
                    z7 = true;
                    boolean z152 = z14 | z7;
                    if (((1879048192 & i2) ^ 805306368) > 536870912) {
                    }
                    if ((i2 & 805306368) != 536870912) {
                    }
                    z8 = true;
                    boolean changed22 = z8 | z152 | composer.changed(graphicsContext);
                    if (((i3 & 896) ^ 384) <= 256) {
                    }
                    if ((i3 & 384) != 256) {
                    }
                    z9 = true;
                    z10 = changed22 | z9;
                    rememberedValue = composer.rememberedValue();
                    if (!z10) {
                    }
                    rememberedValue = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                    composer.updateRememberedValue(rememberedValue);
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy222 = (LazyLayoutMeasurePolicy) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    return lazyLayoutMeasurePolicy222;
                }
                z6 = true;
                boolean z142 = z13 | z6;
                if (((234881024 & i2) ^ 100663296) <= 67108864) {
                }
                if ((100663296 & i2) != 67108864) {
                }
                z7 = true;
                boolean z1522 = z142 | z7;
                if (((1879048192 & i2) ^ 805306368) > 536870912) {
                }
                if ((i2 & 805306368) != 536870912) {
                }
                z8 = true;
                boolean changed222 = z8 | z1522 | composer.changed(graphicsContext);
                if (((i3 & 896) ^ 384) <= 256) {
                }
                if ((i3 & 384) != 256) {
                }
                z9 = true;
                z10 = changed222 | z9;
                rememberedValue = composer.rememberedValue();
                if (!z10) {
                }
                rememberedValue = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
                composer.updateRememberedValue(rememberedValue);
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy2222 = (LazyLayoutMeasurePolicy) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composer);
                if (ComposerKt.isTraceInProgress()) {
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                return lazyLayoutMeasurePolicy2222;
            }
            z5 = true;
            boolean z132 = z12 | z5;
            if (((29360128 & i2) ^ 12582912) > 8388608) {
            }
            if ((12582912 & i2) != 8388608) {
            }
            z6 = true;
            boolean z1422 = z132 | z6;
            if (((234881024 & i2) ^ 100663296) <= 67108864) {
            }
            if ((100663296 & i2) != 67108864) {
            }
            z7 = true;
            boolean z15222 = z1422 | z7;
            if (((1879048192 & i2) ^ 805306368) > 536870912) {
            }
            if ((i2 & 805306368) != 536870912) {
            }
            z8 = true;
            boolean changed2222 = z8 | z15222 | composer.changed(graphicsContext);
            if (((i3 & 896) ^ 384) <= 256) {
            }
            if ((i3 & 384) != 256) {
            }
            z9 = true;
            z10 = changed2222 | z9;
            rememberedValue = composer.rememberedValue();
            if (!z10) {
            }
            rememberedValue = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
            composer.updateRememberedValue(rememberedValue);
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy22222 = (LazyLayoutMeasurePolicy) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            return lazyLayoutMeasurePolicy22222;
        }
        z4 = true;
        boolean z122 = z11 | z4 | ((((458752 & i2) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072 && composer.changed(i)) || (i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072);
        if (((3670016 & i2) ^ 1572864) <= 1048576) {
        }
        if ((1572864 & i2) != 1048576) {
        }
        z5 = true;
        boolean z1322 = z122 | z5;
        if (((29360128 & i2) ^ 12582912) > 8388608) {
        }
        if ((12582912 & i2) != 8388608) {
        }
        z6 = true;
        boolean z14222 = z1322 | z6;
        if (((234881024 & i2) ^ 100663296) <= 67108864) {
        }
        if ((100663296 & i2) != 67108864) {
        }
        z7 = true;
        boolean z152222 = z14222 | z7;
        if (((1879048192 & i2) ^ 805306368) > 536870912) {
        }
        if ((i2 & 805306368) != 536870912) {
        }
        z8 = true;
        boolean changed22222 = z8 | z152222 | composer.changed(graphicsContext);
        if (((i3 & 896) ^ 384) <= 256) {
        }
        if ((i3 & 384) != 256) {
        }
        z9 = true;
        z10 = changed22222 | z9;
        rememberedValue = composer.rememberedValue();
        if (!z10) {
        }
        rememberedValue = new LazyListKt$rememberLazyListMeasurePolicy$1$1(lazyListState, z3, paddingValues, z, function0, vertical4, horizontal4, i, coroutineScope, graphicsContext, stickyItemsPlacement2, horizontal3, vertical3);
        composer.updateRememberedValue(rememberedValue);
        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy222222 = (LazyLayoutMeasurePolicy) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return lazyLayoutMeasurePolicy222222;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void keepAroundItems(CacheWindowLogic cacheWindowLogic, List<LazyListMeasuredItem> list, LazyListMeasuredItemProvider lazyListMeasuredItemProvider) {
        Trace.beginSection("compose:lazy:cache_window:keepAroundItems");
        try {
            if (cacheWindowLogic.hasValidBounds() && !list.isEmpty()) {
                int index = ((LazyListMeasuredItem) CollectionsKt.first((List) list)).getIndex();
                int index2 = ((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex();
                for (int prefetchWindowStartLine = cacheWindowLogic.getPrefetchWindowStartLine(); prefetchWindowStartLine < index; prefetchWindowStartLine++) {
                    lazyListMeasuredItemProvider.keepAround(prefetchWindowStartLine);
                }
                int i = index2 + 1;
                int prefetchWindowEndLine = cacheWindowLogic.getPrefetchWindowEndLine();
                if (i <= prefetchWindowEndLine) {
                    while (true) {
                        lazyListMeasuredItemProvider.keepAround(i);
                        if (i == prefetchWindowEndLine) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }
}
