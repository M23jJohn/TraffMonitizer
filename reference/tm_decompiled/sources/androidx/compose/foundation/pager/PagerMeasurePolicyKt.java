package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Alignment;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PagerMeasurePolicy.kt */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0087\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003H\u0001¢\u0006\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"rememberPagerMeasurePolicy", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "state", "Landroidx/compose/foundation/pager/PagerState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "beyondViewportPageCount", "", "pageSpacing", "Landroidx/compose/ui/unit/Dp;", "pageSize", "Landroidx/compose/foundation/pager/PageSize;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "snapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "pageCount", "rememberPagerMeasurePolicy-8u0NR3k", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;IFLandroidx/compose/foundation/pager/PageSize;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PagerMeasurePolicyKt {
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0099, code lost:
    
        if (r34.changed(r4) == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b5, code lost:
    
        if (r34.changed(r9) == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00d1, code lost:
    
        if (r34.changed(r12) == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ed, code lost:
    
        if (r34.changed(r13) == false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0106, code lost:
    
        if (r34.changed(r14) == false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0139, code lost:
    
        if (r34.changed(r3) == false) goto L101;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00b8  */
    /* renamed from: rememberPagerMeasurePolicy-8u0NR3k, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final LazyLayoutMeasurePolicy m1190rememberPagerMeasurePolicy8u0NR3k(Function0<PagerLazyLayoutItemProvider> function0, PagerState pagerState, PaddingValues paddingValues, boolean z, Orientation orientation, int i, float f, PageSize pageSize, Alignment.Horizontal horizontal, Alignment.Vertical vertical, SnapPosition snapPosition, CoroutineScope coroutineScope, Function0<Integer> function02, Composer composer, int i2, int i3) {
        Alignment.Horizontal horizontal2;
        boolean z2;
        Alignment.Vertical vertical2;
        boolean z3;
        float f2;
        boolean z4;
        PageSize pageSize2;
        boolean z5;
        SnapPosition snapPosition2;
        boolean z6;
        int i4;
        boolean z7;
        boolean changed;
        PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 rememberedValue;
        ComposerKt.sourceInformationMarkerStart(composer, -1294131537, "C(rememberPagerMeasurePolicy)N(itemProviderLambda,state,contentPadding,reverseLayout,orientation,beyondViewportPageCount,pageSpacing:c#ui.unit.Dp,pageSize,horizontalAlignment,verticalAlignment,snapPosition,coroutineScope,pageCount)58@2351L7519:PagerMeasurePolicy.kt#g6yjnt");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1294131537, i2, i3, "androidx.compose.foundation.pager.rememberPagerMeasurePolicy (PagerMeasurePolicy.kt:58)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 1880133646, "CC(remember):PagerMeasurePolicy.kt#9igjgp");
        boolean z8 = ((((i2 & 112) ^ 48) > 32 && composer.changed(pagerState)) || (i2 & 48) == 32) | ((((i2 & 896) ^ 384) > 256 && composer.changed(paddingValues)) || (i2 & 384) == 256) | ((((i2 & 7168) ^ 3072) > 2048 && composer.changed(z)) || (i2 & 3072) == 2048) | ((((57344 & i2) ^ 24576) > 16384 && composer.changed(orientation.ordinal())) || (i2 & 24576) == 16384);
        if (((234881024 & i2) ^ 100663296) > 67108864) {
            horizontal2 = horizontal;
        } else {
            horizontal2 = horizontal;
        }
        if ((100663296 & i2) != 67108864) {
            z2 = false;
            boolean z9 = z8 | z2;
            if (((1879048192 & i2) ^ 805306368) <= 536870912) {
                vertical2 = vertical;
            } else {
                vertical2 = vertical;
            }
            if ((805306368 & i2) != 536870912) {
                z3 = false;
                boolean z10 = z9 | z3;
                if (((3670016 & i2) ^ 1572864) > 1048576) {
                    f2 = f;
                } else {
                    f2 = f;
                }
                if ((1572864 & i2) != 1048576) {
                    z4 = false;
                    boolean z11 = z10 | z4;
                    if (((29360128 & i2) ^ 12582912) <= 8388608) {
                        pageSize2 = pageSize;
                    } else {
                        pageSize2 = pageSize;
                    }
                    if ((12582912 & i2) != 8388608) {
                        z5 = false;
                        boolean z12 = z11 | z5;
                        if (((i3 & 14) ^ 6) > 4) {
                            snapPosition2 = snapPosition;
                        } else {
                            snapPosition2 = snapPosition;
                        }
                        if ((i3 & 6) != 4) {
                            z6 = false;
                            boolean z13 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function02)) || (i3 & 384) == 256) | z12 | z6;
                            if (((458752 & i2) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072) {
                                i4 = i;
                            } else {
                                i4 = i;
                            }
                            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 131072) {
                                z7 = false;
                                changed = z13 | z7 | composer.changed(coroutineScope);
                                rememberedValue = composer.rememberedValue();
                                if (!changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function02, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
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
                            z7 = true;
                            changed = z13 | z7 | composer.changed(coroutineScope);
                            rememberedValue = composer.rememberedValue();
                            if (!changed) {
                            }
                            rememberedValue = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function02, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                            composer.updateRememberedValue(rememberedValue);
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy2 = (LazyLayoutMeasurePolicy) rememberedValue;
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            return lazyLayoutMeasurePolicy2;
                        }
                        z6 = true;
                        boolean z132 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function02)) || (i3 & 384) == 256) | z12 | z6;
                        if (((458752 & i2) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072) {
                        }
                        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 131072) {
                        }
                        z7 = true;
                        changed = z132 | z7 | composer.changed(coroutineScope);
                        rememberedValue = composer.rememberedValue();
                        if (!changed) {
                        }
                        rememberedValue = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function02, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                        composer.updateRememberedValue(rememberedValue);
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy22 = (LazyLayoutMeasurePolicy) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        return lazyLayoutMeasurePolicy22;
                    }
                    z5 = true;
                    boolean z122 = z11 | z5;
                    if (((i3 & 14) ^ 6) > 4) {
                    }
                    if ((i3 & 6) != 4) {
                    }
                    z6 = true;
                    boolean z1322 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function02)) || (i3 & 384) == 256) | z122 | z6;
                    if (((458752 & i2) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072) {
                    }
                    if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 131072) {
                    }
                    z7 = true;
                    changed = z1322 | z7 | composer.changed(coroutineScope);
                    rememberedValue = composer.rememberedValue();
                    if (!changed) {
                    }
                    rememberedValue = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function02, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                    composer.updateRememberedValue(rememberedValue);
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy222 = (LazyLayoutMeasurePolicy) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    return lazyLayoutMeasurePolicy222;
                }
                z4 = true;
                boolean z112 = z10 | z4;
                if (((29360128 & i2) ^ 12582912) <= 8388608) {
                }
                if ((12582912 & i2) != 8388608) {
                }
                z5 = true;
                boolean z1222 = z112 | z5;
                if (((i3 & 14) ^ 6) > 4) {
                }
                if ((i3 & 6) != 4) {
                }
                z6 = true;
                boolean z13222 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function02)) || (i3 & 384) == 256) | z1222 | z6;
                if (((458752 & i2) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072) {
                }
                if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 131072) {
                }
                z7 = true;
                changed = z13222 | z7 | composer.changed(coroutineScope);
                rememberedValue = composer.rememberedValue();
                if (!changed) {
                }
                rememberedValue = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function02, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
                composer.updateRememberedValue(rememberedValue);
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy2222 = (LazyLayoutMeasurePolicy) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composer);
                if (ComposerKt.isTraceInProgress()) {
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                return lazyLayoutMeasurePolicy2222;
            }
            z3 = true;
            boolean z102 = z9 | z3;
            if (((3670016 & i2) ^ 1572864) > 1048576) {
            }
            if ((1572864 & i2) != 1048576) {
            }
            z4 = true;
            boolean z1122 = z102 | z4;
            if (((29360128 & i2) ^ 12582912) <= 8388608) {
            }
            if ((12582912 & i2) != 8388608) {
            }
            z5 = true;
            boolean z12222 = z1122 | z5;
            if (((i3 & 14) ^ 6) > 4) {
            }
            if ((i3 & 6) != 4) {
            }
            z6 = true;
            boolean z132222 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function02)) || (i3 & 384) == 256) | z12222 | z6;
            if (((458752 & i2) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072) {
            }
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 131072) {
            }
            z7 = true;
            changed = z132222 | z7 | composer.changed(coroutineScope);
            rememberedValue = composer.rememberedValue();
            if (!changed) {
            }
            rememberedValue = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function02, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
            composer.updateRememberedValue(rememberedValue);
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy22222 = (LazyLayoutMeasurePolicy) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            return lazyLayoutMeasurePolicy22222;
        }
        z2 = true;
        boolean z92 = z8 | z2;
        if (((1879048192 & i2) ^ 805306368) <= 536870912) {
        }
        if ((805306368 & i2) != 536870912) {
        }
        z3 = true;
        boolean z1022 = z92 | z3;
        if (((3670016 & i2) ^ 1572864) > 1048576) {
        }
        if ((1572864 & i2) != 1048576) {
        }
        z4 = true;
        boolean z11222 = z1022 | z4;
        if (((29360128 & i2) ^ 12582912) <= 8388608) {
        }
        if ((12582912 & i2) != 8388608) {
        }
        z5 = true;
        boolean z122222 = z11222 | z5;
        if (((i3 & 14) ^ 6) > 4) {
        }
        if ((i3 & 6) != 4) {
        }
        z6 = true;
        boolean z1322222 = ((((i3 & 896) ^ 384) <= 256 && composer.changed(function02)) || (i3 & 384) == 256) | z122222 | z6;
        if (((458752 & i2) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072) {
        }
        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 131072) {
        }
        z7 = true;
        changed = z1322222 | z7 | composer.changed(coroutineScope);
        rememberedValue = composer.rememberedValue();
        if (!changed) {
        }
        rememberedValue = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(pagerState, orientation, paddingValues, z, f2, pageSize2, function0, function02, vertical2, horizontal2, i4, snapPosition2, coroutineScope);
        composer.updateRememberedValue(rememberedValue);
        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy222222 = (LazyLayoutMeasurePolicy) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return lazyLayoutMeasurePolicy222222;
    }
}
