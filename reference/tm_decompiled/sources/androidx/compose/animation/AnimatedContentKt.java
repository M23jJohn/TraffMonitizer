package androidx.compose.animation;

import android.R;
import androidx.autofill.HintConstants;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.animation.AnimatedContentTransitionScopeImpl;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimatedContent.kt */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a´\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001f\b\u0002\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000721\u0010\u0013\u001a-\u0012\u0004\u0012\u00020\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u0017\u001aP\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2>\b\u0002\u0010\u001c\u001a8\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0 0\u0014\u001a\u0015\u0010!\u001a\u00020\t*\u00020\"2\u0006\u0010#\u001a\u00020$H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020\t*\u00020\"2\u0006\u0010#\u001a\u00020$H\u0087\u0004\u001a¬\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020(2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001f\b\u0002\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000721\u0010\u0013\u001a-\u0012\u0004\u0012\u00020\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010)\"\u0010\u0010&\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010'¨\u0006*"}, d2 = {"AnimatedContent", "", "S", "targetState", "modifier", "Landroidx/compose/ui/Modifier;", "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/AnimatedContentTransitionScope;", "Landroidx/compose/animation/ContentTransform;", "Lkotlin/ExtensionFunctionType;", "contentAlignment", "Landroidx/compose/ui/Alignment;", "label", "", "contentKey", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "", "content", "Lkotlin/Function2;", "Landroidx/compose/animation/AnimatedContentScope;", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "SizeTransform", "Landroidx/compose/animation/SizeTransform;", "clip", "", "sizeAnimationSpec", "Landroidx/compose/ui/unit/IntSize;", "initialSize", "targetSize", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "togetherWith", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "with", "UnspecifiedSize", "J", "Landroidx/compose/animation/core/Transition;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "animation"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnimatedContentKt {
    private static final long UnspecifiedSize;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <S> void AnimatedContent(final S s, Modifier modifier, Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function1, Alignment alignment, String str, Function1<? super S, ? extends Object> function12, final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function13;
        int i5;
        Alignment alignment2;
        int i6;
        int i7;
        Function1<? super S, ? extends Object> function14;
        Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function42;
        final Modifier modifier2;
        final String str2;
        final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function15;
        final Alignment alignment3;
        ScopeUpdateScope endRestartGroup;
        int i8;
        Modifier modifier3;
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function16;
        int i9;
        Alignment alignment4;
        Composer startRestartGroup = composer.startRestartGroup(1501828832);
        ComposerKt.sourceInformation(startRestartGroup, "C(AnimatedContent)P(5,4,6,1,3,2)131@7139L226,138@7503L6,141@7610L58,142@7684L137:AnimatedContent.kt#xbi5r1");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? startRestartGroup.changed(s) : startRestartGroup.changedInstance(s) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i10 = i2 & 2;
        if (i10 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= startRestartGroup.changed(modifier) ? 32 : 16;
            i4 = i2 & 4;
            if (i4 == 0) {
                i3 |= 384;
            } else if ((i & 384) == 0) {
                function13 = function1;
                i3 |= startRestartGroup.changedInstance(function13) ? 256 : 128;
                i5 = i2 & 8;
                if (i5 != 0) {
                    i3 |= 3072;
                } else if ((i & 3072) == 0) {
                    alignment2 = alignment;
                    i3 |= startRestartGroup.changed(alignment2) ? 2048 : 1024;
                    i6 = i2 & 16;
                    if (i6 == 0) {
                        i3 |= 24576;
                    } else if ((i & 24576) == 0) {
                        i3 |= startRestartGroup.changed(str) ? 16384 : 8192;
                        i7 = i2 & 32;
                        if (i7 != 0) {
                            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((196608 & i) == 0) {
                            function14 = function12;
                            i3 |= startRestartGroup.changedInstance(function14) ? 131072 : 65536;
                            if ((i2 & 64) == 0) {
                                i3 |= 1572864;
                            } else if ((i & 1572864) == 0) {
                                function42 = function4;
                                i3 |= startRestartGroup.changedInstance(function42) ? 1048576 : 524288;
                                if (!startRestartGroup.shouldExecute((i3 & 599187) != 599186, i3 & 1)) {
                                    startRestartGroup.skipToGroupEnd();
                                    modifier2 = modifier;
                                    str2 = str;
                                    function15 = function13;
                                    alignment3 = alignment2;
                                } else {
                                    if (i10 != 0) {
                                        modifier3 = Modifier.INSTANCE;
                                        i8 = i6;
                                    } else {
                                        i8 = i6;
                                        modifier3 = modifier;
                                    }
                                    if (i4 != 0) {
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 148502018, "CC(remember):AnimatedContent.kt#9igjgp");
                                        AnimatedContentKt$AnimatedContent$1$1 rememberedValue = startRestartGroup.rememberedValue();
                                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue = new Function1<AnimatedContentTransitionScope<S>, ContentTransform>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$1$1
                                                @Override // kotlin.jvm.functions.Function1
                                                public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
                                                    return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m124scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        function16 = (Function1) rememberedValue;
                                    } else {
                                        function16 = function13;
                                    }
                                    if (i5 != 0) {
                                        alignment4 = Alignment.INSTANCE.getTopStart();
                                        i9 = i7;
                                    } else {
                                        i9 = i7;
                                        alignment4 = alignment2;
                                    }
                                    String str3 = i8 != 0 ? "AnimatedContent" : str;
                                    if (i9 != 0) {
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 148513446, "CC(remember):AnimatedContent.kt#9igjgp");
                                        AnimatedContentKt$AnimatedContent$2$1 rememberedValue2 = startRestartGroup.rememberedValue();
                                        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue2 = new Function1<S, S>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$2$1
                                                @Override // kotlin.jvm.functions.Function1
                                                public final S invoke(S s2) {
                                                    return s2;
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue2);
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        function14 = (Function1) rememberedValue2;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1501828832, i3, -1, "androidx.compose.animation.AnimatedContent (AnimatedContent.kt:140)");
                                    }
                                    Transition updateTransition = androidx.compose.animation.core.TransitionKt.updateTransition(s, str3, startRestartGroup, (i3 & 14) | ((i3 >> 9) & 112), 0);
                                    int i11 = i3 & 8176;
                                    int i12 = i3 >> 3;
                                    AnimatedContent(updateTransition, modifier3, function16, alignment4, function14, function42, startRestartGroup, i11 | (57344 & i12) | (i12 & 458752), 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    str2 = str3;
                                    modifier2 = modifier3;
                                    function15 = function16;
                                    alignment3 = alignment4;
                                }
                                final Function1<? super S, ? extends Object> function17 = function14;
                                endRestartGroup = startRestartGroup.endRestartGroup();
                                if (endRestartGroup != null) {
                                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$3
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                            invoke(composer2, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer2, int i13) {
                                            AnimatedContentKt.AnimatedContent(s, modifier2, function15, alignment3, str2, function17, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            function42 = function4;
                            if (!startRestartGroup.shouldExecute((i3 & 599187) != 599186, i3 & 1)) {
                            }
                            final Function1<? super S, ? extends Object> function172 = function14;
                            endRestartGroup = startRestartGroup.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        function14 = function12;
                        if ((i2 & 64) == 0) {
                        }
                        function42 = function4;
                        if (!startRestartGroup.shouldExecute((i3 & 599187) != 599186, i3 & 1)) {
                        }
                        final Function1<? super S, ? extends Object> function1722 = function14;
                        endRestartGroup = startRestartGroup.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    i7 = i2 & 32;
                    if (i7 != 0) {
                    }
                    function14 = function12;
                    if ((i2 & 64) == 0) {
                    }
                    function42 = function4;
                    if (!startRestartGroup.shouldExecute((i3 & 599187) != 599186, i3 & 1)) {
                    }
                    final Function1<? super S, ? extends Object> function17222 = function14;
                    endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                alignment2 = alignment;
                i6 = i2 & 16;
                if (i6 == 0) {
                }
                i7 = i2 & 32;
                if (i7 != 0) {
                }
                function14 = function12;
                if ((i2 & 64) == 0) {
                }
                function42 = function4;
                if (!startRestartGroup.shouldExecute((i3 & 599187) != 599186, i3 & 1)) {
                }
                final Function1<? super S, ? extends Object> function172222 = function14;
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            function13 = function1;
            i5 = i2 & 8;
            if (i5 != 0) {
            }
            alignment2 = alignment;
            i6 = i2 & 16;
            if (i6 == 0) {
            }
            i7 = i2 & 32;
            if (i7 != 0) {
            }
            function14 = function12;
            if ((i2 & 64) == 0) {
            }
            function42 = function4;
            if (!startRestartGroup.shouldExecute((i3 & 599187) != 599186, i3 & 1)) {
            }
            final Function1<? super S, ? extends Object> function1722222 = function14;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        i4 = i2 & 4;
        if (i4 == 0) {
        }
        function13 = function1;
        i5 = i2 & 8;
        if (i5 != 0) {
        }
        alignment2 = alignment;
        i6 = i2 & 16;
        if (i6 == 0) {
        }
        i7 = i2 & 32;
        if (i7 != 0) {
        }
        function14 = function12;
        if ((i2 & 64) == 0) {
        }
        function42 = function4;
        if (!startRestartGroup.shouldExecute((i3 & 599187) != 599186, i3 & 1)) {
        }
        final Function1<? super S, ? extends Object> function17222222 = function14;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    public static /* synthetic */ SizeTransform SizeTransform$default(boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            function2 = new Function2<IntSize, IntSize, SpringSpec<IntSize>>() { // from class: androidx.compose.animation.AnimatedContentKt$SizeTransform$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ SpringSpec<IntSize> invoke(IntSize intSize, IntSize intSize2) {
                    return m79invokeTemP2vQ(intSize.m7578unboximpl(), intSize2.m7578unboximpl());
                }

                /* renamed from: invoke-TemP2vQ, reason: not valid java name */
                public final SpringSpec<IntSize> m79invokeTemP2vQ(long j, long j2) {
                    return AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m7566boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
                }
            };
        }
        return SizeTransform(z, function2);
    }

    public static final SizeTransform SizeTransform(boolean z, Function2<? super IntSize, ? super IntSize, ? extends FiniteAnimationSpec<IntSize>> function2) {
        return new SizeTransformImpl(z, function2);
    }

    public static final ContentTransform togetherWith(EnterTransition enterTransition, ExitTransition exitTransition) {
        return new ContentTransform(enterTransition, exitTransition, 0.0f, null, 12, null);
    }

    @Deprecated(message = "Infix fun EnterTransition.with(ExitTransition) has been renamed to togetherWith", replaceWith = @ReplaceWith(expression = "togetherWith(exit)", imports = {}))
    public static final ContentTransform with(EnterTransition enterTransition, ExitTransition exitTransition) {
        return new ContentTransform(enterTransition, exitTransition, 0.0f, null, 12, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <S> void AnimatedContent(final Transition<S> transition, Modifier modifier, Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function1, Alignment alignment, Function1<? super S, ? extends Object> function12, final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function13;
        int i5;
        Alignment alignment2;
        int i6;
        Function1<? super S, ? extends Object> function14;
        Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function42;
        final Modifier modifier3;
        final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function15;
        ScopeUpdateScope endRestartGroup;
        String str;
        AnimatedContentTransitionScopeImpl animatedContentTransitionScopeImpl;
        SnapshotStateList snapshotStateList;
        MutableScatterMap mutableScatterMap;
        final Transition<S> transition2 = transition;
        Composer startRestartGroup = composer.startRestartGroup(511725103);
        ComposerKt.sourceInformation(startRestartGroup, "C(AnimatedContent)P(3,4,1,2)765@38134L226,771@38459L6,774@38592L7,776@38628L114,780@38830L51,781@38903L69,867@43438L58,868@43530L45,874@43785L52,869@43580L264:AnimatedContent.kt#xbi5r1");
        if ((i2 & Integer.MIN_VALUE) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(transition2) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i7 = i2 & 1;
        if (i7 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i4 = i2 & 2;
            if (i4 == 0) {
                i3 |= 384;
            } else if ((i & 384) == 0) {
                function13 = function1;
                i3 |= startRestartGroup.changedInstance(function13) ? 256 : 128;
                i5 = i2 & 4;
                if (i5 != 0) {
                    i3 |= 3072;
                } else if ((i & 3072) == 0) {
                    alignment2 = alignment;
                    i3 |= startRestartGroup.changed(alignment2) ? 2048 : 1024;
                    i6 = i2 & 8;
                    if (i6 == 0) {
                        i3 |= 24576;
                    } else if ((i & 24576) == 0) {
                        function14 = function12;
                        i3 |= startRestartGroup.changedInstance(function14) ? 16384 : 8192;
                        if ((i2 & 16) != 0) {
                            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            function42 = function4;
                            i3 |= startRestartGroup.changedInstance(function42) ? 131072 : 65536;
                            if (startRestartGroup.shouldExecute((74899 & i3) == 74898, i3 & 1)) {
                                startRestartGroup.skipToGroupEnd();
                                modifier3 = modifier2;
                                function15 = function13;
                            } else {
                                Modifier modifier4 = i7 != 0 ? Modifier.INSTANCE : modifier2;
                                String str2 = "CC(remember):AnimatedContent.kt#9igjgp";
                                if (i4 != 0) {
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2141559249, "CC(remember):AnimatedContent.kt#9igjgp");
                                    AnimatedContentKt$AnimatedContent$4$1 rememberedValue = startRestartGroup.rememberedValue();
                                    if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue = new Function1<AnimatedContentTransitionScope<S>, ContentTransform>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$4$1
                                            @Override // kotlin.jvm.functions.Function1
                                            public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
                                                return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m124scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
                                            }
                                        };
                                        startRestartGroup.updateRememberedValue(rememberedValue);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    function13 = (Function1) rememberedValue;
                                }
                                if (i5 != 0) {
                                    alignment2 = Alignment.INSTANCE.getTopStart();
                                }
                                if (i6 != 0) {
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2141569429, "CC(remember):AnimatedContent.kt#9igjgp");
                                    AnimatedContentKt$AnimatedContent$5$1 rememberedValue2 = startRestartGroup.rememberedValue();
                                    if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue2 = new Function1<S, S>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$5$1
                                            @Override // kotlin.jvm.functions.Function1
                                            public final S invoke(S s) {
                                                return s;
                                            }
                                        };
                                        startRestartGroup.updateRememberedValue(rememberedValue2);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    function14 = (Function1) rememberedValue2;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(511725103, i3, -1, "androidx.compose.animation.AnimatedContent (AnimatedContent.kt:773)");
                                }
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                Object consume = startRestartGroup.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                LayoutDirection layoutDirection = (LayoutDirection) consume;
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2141574945, "CC(remember):AnimatedContent.kt#9igjgp");
                                int i8 = i3 & 14;
                                boolean z = i8 == 4;
                                Object rememberedValue3 = startRestartGroup.rememberedValue();
                                if (z || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue3 = new AnimatedContentTransitionScopeImpl(transition2, alignment2, layoutDirection);
                                    startRestartGroup.updateRememberedValue(rememberedValue3);
                                }
                                AnimatedContentTransitionScopeImpl animatedContentTransitionScopeImpl2 = (AnimatedContentTransitionScopeImpl) rememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2141581346, "CC(remember):AnimatedContent.kt#9igjgp");
                                boolean z2 = i8 == 4;
                                Object rememberedValue4 = startRestartGroup.rememberedValue();
                                if (z2 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue4 = SnapshotStateKt.mutableStateListOf(transition2.getCurrentState());
                                    startRestartGroup.updateRememberedValue(rememberedValue4);
                                }
                                SnapshotStateList snapshotStateList2 = (SnapshotStateList) rememberedValue4;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2141583700, "CC(remember):AnimatedContent.kt#9igjgp");
                                boolean z3 = i8 == 4;
                                Object rememberedValue5 = startRestartGroup.rememberedValue();
                                if (z3 || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue5 = ScatterMapKt.mutableScatterMapOf();
                                    startRestartGroup.updateRememberedValue(rememberedValue5);
                                }
                                MutableScatterMap mutableScatterMap2 = (MutableScatterMap) rememberedValue5;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                if (!snapshotStateList2.contains(transition2.getCurrentState())) {
                                    snapshotStateList2.clear();
                                    snapshotStateList2.add(transition2.getCurrentState());
                                }
                                if (Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState())) {
                                    if (snapshotStateList2.size() != 1 || !Intrinsics.areEqual(snapshotStateList2.get(0), transition2.getCurrentState())) {
                                        snapshotStateList2.clear();
                                        snapshotStateList2.add(transition2.getCurrentState());
                                    }
                                    if (mutableScatterMap2.get_size() != 1 || mutableScatterMap2.containsKey(transition2.getCurrentState())) {
                                        mutableScatterMap2.clear();
                                    }
                                    animatedContentTransitionScopeImpl2.setContentAlignment(alignment2);
                                    animatedContentTransitionScopeImpl2.setLayoutDirection$animation(layoutDirection);
                                }
                                if (!Intrinsics.areEqual(transition2.getCurrentState(), transition2.getTargetState()) && !snapshotStateList2.contains(transition2.getTargetState())) {
                                    Iterator<T> it = snapshotStateList2.iterator();
                                    int i9 = 0;
                                    while (true) {
                                        if (!it.hasNext()) {
                                            i9 = -1;
                                            break;
                                        } else if (Intrinsics.areEqual(function14.invoke((Object) it.next()), function14.invoke(transition2.getTargetState()))) {
                                            break;
                                        } else {
                                            i9++;
                                        }
                                    }
                                    if (i9 == -1) {
                                        Boolean.valueOf(snapshotStateList2.add(transition2.getTargetState()));
                                    } else {
                                        snapshotStateList2.set(i9, transition2.getTargetState());
                                        Unit unit = Unit.INSTANCE;
                                    }
                                }
                                if (!mutableScatterMap2.containsKey(transition2.getTargetState()) || !mutableScatterMap2.containsKey(transition2.getCurrentState())) {
                                    startRestartGroup.startReplaceGroup(1966468977);
                                    ComposerKt.sourceInformation(startRestartGroup, "*817@40849L2545");
                                    mutableScatterMap2.clear();
                                    SnapshotStateList snapshotStateList3 = snapshotStateList2;
                                    int size = snapshotStateList3.size();
                                    int i10 = 0;
                                    while (i10 < size) {
                                        MutableScatterMap mutableScatterMap3 = mutableScatterMap2;
                                        final T t = snapshotStateList3.get(i10);
                                        int i11 = i10;
                                        final SnapshotStateList snapshotStateList4 = snapshotStateList2;
                                        int i12 = size;
                                        final AnimatedContentTransitionScopeImpl animatedContentTransitionScopeImpl3 = animatedContentTransitionScopeImpl2;
                                        final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function43 = function42;
                                        String str3 = str2;
                                        final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function16 = function13;
                                        mutableScatterMap3.set(t, ComposableLambdaKt.rememberComposableLambda(-23915175, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                                invoke(composer2, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX WARN: Multi-variable type inference failed */
                                            public final void invoke(Composer composer2, int i13) {
                                                Object initialContentExit;
                                                ComposerKt.sourceInformation(composer2, "C818@40885L38,822@41099L323,829@41455L125,840@42023L348,836@41832L25,847@42511L233,852@42764L616,834@41763L1617:AnimatedContent.kt#xbi5r1");
                                                if (!composer2.shouldExecute((i13 & 3) != 2, i13 & 1)) {
                                                    composer2.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-23915175, i13, -1, "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous> (AnimatedContent.kt:818)");
                                                }
                                                ComposerKt.sourceInformationMarkerStart(composer2, -1507645505, "CC(remember):AnimatedContent.kt#9igjgp");
                                                Function1<AnimatedContentTransitionScope<S>, ContentTransform> function17 = function16;
                                                Object obj = animatedContentTransitionScopeImpl3;
                                                ContentTransform rememberedValue6 = composer2.rememberedValue();
                                                if (rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                    rememberedValue6 = function17.invoke(obj);
                                                    composer2.updateRememberedValue(rememberedValue6);
                                                }
                                                final ContentTransform contentTransform = (ContentTransform) rememberedValue6;
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                boolean areEqual = Intrinsics.areEqual(transition2.getSegment().getTargetState(), t);
                                                ComposerKt.sourceInformationMarkerStart(composer2, -1507638372, "CC(remember):AnimatedContent.kt#9igjgp");
                                                boolean changed = composer2.changed(areEqual);
                                                Transition<S> transition3 = transition2;
                                                S s = t;
                                                Function1<AnimatedContentTransitionScope<S>, ContentTransform> function18 = function16;
                                                Object obj2 = animatedContentTransitionScopeImpl3;
                                                Object rememberedValue7 = composer2.rememberedValue();
                                                if (changed || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                                    if (Intrinsics.areEqual(transition3.getSegment().getTargetState(), s)) {
                                                        initialContentExit = ExitTransition.INSTANCE.getNone();
                                                    } else {
                                                        initialContentExit = function18.invoke(obj2).getInitialContentExit();
                                                    }
                                                    rememberedValue7 = initialContentExit;
                                                    composer2.updateRememberedValue(rememberedValue7);
                                                }
                                                final ExitTransition exitTransition = (ExitTransition) rememberedValue7;
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                ComposerKt.sourceInformationMarkerStart(composer2, -1507627178, "CC(remember):AnimatedContent.kt#9igjgp");
                                                S s2 = t;
                                                Transition<S> transition4 = transition2;
                                                Object rememberedValue8 = composer2.rememberedValue();
                                                if (rememberedValue8 == Composer.INSTANCE.getEmpty()) {
                                                    rememberedValue8 = new AnimatedContentTransitionScopeImpl.ChildData(Intrinsics.areEqual(s2, transition4.getTargetState()));
                                                    composer2.updateRememberedValue(rememberedValue8);
                                                }
                                                AnimatedContentTransitionScopeImpl.ChildData childData = (AnimatedContentTransitionScopeImpl.ChildData) rememberedValue8;
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                EnterTransition targetContentEnter = contentTransform.getTargetContentEnter();
                                                Modifier.Companion companion = Modifier.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart(composer2, -1507608779, "CC(remember):AnimatedContent.kt#9igjgp");
                                                boolean changedInstance = composer2.changedInstance(contentTransform);
                                                Object rememberedValue9 = composer2.rememberedValue();
                                                if (changedInstance || rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                                                    rememberedValue9 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$1$1
                                                        {
                                                            super(3);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function3
                                                        public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                                            return m78invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                                        }

                                                        /* renamed from: invoke-3p2s80s, reason: not valid java name */
                                                        public final MeasureResult m78invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                                                            final Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(j);
                                                            int width = mo6055measureBRTryo0.getWidth();
                                                            int height = mo6055measureBRTryo0.getHeight();
                                                            final ContentTransform contentTransform2 = ContentTransform.this;
                                                            return MeasureScope.CC.layout$default(measureScope, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$1$1.1
                                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                {
                                                                    super(1);
                                                                }

                                                                @Override // kotlin.jvm.functions.Function1
                                                                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                                    invoke2(placementScope);
                                                                    return Unit.INSTANCE;
                                                                }

                                                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                public final void invoke2(Placeable.PlacementScope placementScope) {
                                                                    placementScope.place(Placeable.this, 0, 0, contentTransform2.getTargetContentZIndex());
                                                                }
                                                            }, 4, null);
                                                        }
                                                    };
                                                    composer2.updateRememberedValue(rememberedValue9);
                                                }
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                Modifier layout = LayoutModifierKt.layout(companion, (Function3) rememberedValue9);
                                                childData.setTarget(Intrinsics.areEqual(t, transition2.getTargetState()));
                                                Modifier then = layout.then(childData);
                                                Transition<S> transition5 = transition2;
                                                ComposerKt.sourceInformationMarkerStart(composer2, -1507615214, "CC(remember):AnimatedContent.kt#9igjgp");
                                                boolean changedInstance2 = composer2.changedInstance(t);
                                                final S s3 = t;
                                                Object rememberedValue10 = composer2.rememberedValue();
                                                if (changedInstance2 || rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                                                    rememberedValue10 = (Function1) new Function1<S, Boolean>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$3$1
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(1);
                                                        }

                                                        /* JADX WARN: Can't rename method to resolve collision */
                                                        @Override // kotlin.jvm.functions.Function1
                                                        public final Boolean invoke(S s4) {
                                                            return Boolean.valueOf(Intrinsics.areEqual(s4, s3));
                                                        }

                                                        /* JADX WARN: Multi-variable type inference failed */
                                                        @Override // kotlin.jvm.functions.Function1
                                                        public /* bridge */ /* synthetic */ Boolean invoke(Object obj3) {
                                                            return invoke((AnimatedContentKt$AnimatedContent$6$1$3$1<S>) obj3);
                                                        }
                                                    };
                                                    composer2.updateRememberedValue(rememberedValue10);
                                                }
                                                Function1 function19 = (Function1) rememberedValue10;
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                ComposerKt.sourceInformationMarkerStart(composer2, -1507593278, "CC(remember):AnimatedContent.kt#9igjgp");
                                                boolean changed2 = composer2.changed(exitTransition);
                                                Object rememberedValue11 = composer2.rememberedValue();
                                                if (changed2 || rememberedValue11 == Composer.INSTANCE.getEmpty()) {
                                                    rememberedValue11 = (Function2) new Function2<EnterExitState, EnterExitState, Boolean>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1
                                                        {
                                                            super(2);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public final Boolean invoke(EnterExitState enterExitState, EnterExitState enterExitState2) {
                                                            return Boolean.valueOf(enterExitState == EnterExitState.PostExit && enterExitState2 == EnterExitState.PostExit && !ExitTransition.this.getData().getHold());
                                                        }
                                                    };
                                                    composer2.updateRememberedValue(rememberedValue11);
                                                }
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                final SnapshotStateList<S> snapshotStateList5 = snapshotStateList4;
                                                final S s4 = t;
                                                final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl4 = animatedContentTransitionScopeImpl3;
                                                final Function4<AnimatedContentScope, S, Composer, Integer, Unit> function44 = function43;
                                                AnimatedVisibilityKt.AnimatedEnterExitImpl(transition5, function19, then, targetContentEnter, exitTransition, (Function2) rememberedValue11, null, ComposableLambdaKt.rememberComposableLambda(-143346359, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.5
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    /* JADX WARN: Multi-variable type inference failed */
                                                    {
                                                        super(3);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function3
                                                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, Integer num) {
                                                        invoke(animatedVisibilityScope, composer3, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer3, int i14) {
                                                        ComposerKt.sourceInformation(composer3, "C854@42897L230,854@42874L253,862@43289L43,*862@43336L24:AnimatedContent.kt#xbi5r1");
                                                        if ((i14 & 6) == 0) {
                                                            i14 |= (i14 & 8) == 0 ? composer3.changed(animatedVisibilityScope) : composer3.changedInstance(animatedVisibilityScope) ? 4 : 2;
                                                        }
                                                        if (!composer3.shouldExecute((i14 & 19) != 18, i14 & 1)) {
                                                            composer3.skipToGroupEnd();
                                                            return;
                                                        }
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-143346359, i14, -1, "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous>.<anonymous> (AnimatedContent.kt:854)");
                                                        }
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -316893041, "CC(remember):AnimatedContent.kt#9igjgp");
                                                        boolean changed3 = composer3.changed(snapshotStateList5) | composer3.changedInstance(s4) | composer3.changedInstance(animatedContentTransitionScopeImpl4);
                                                        final SnapshotStateList<S> snapshotStateList6 = snapshotStateList5;
                                                        final S s5 = s4;
                                                        final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl5 = animatedContentTransitionScopeImpl4;
                                                        Object rememberedValue12 = composer3.rememberedValue();
                                                        if (changed3 || rememberedValue12 == Composer.INSTANCE.getEmpty()) {
                                                            rememberedValue12 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$5$1$1
                                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                {
                                                                    super(1);
                                                                }

                                                                @Override // kotlin.jvm.functions.Function1
                                                                public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                                                                    final SnapshotStateList<S> snapshotStateList7 = snapshotStateList6;
                                                                    final S s6 = s5;
                                                                    final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl6 = animatedContentTransitionScopeImpl5;
                                                                    return new DisposableEffectResult() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$5$1$1$invoke$$inlined$onDispose$1
                                                                        @Override // androidx.compose.runtime.DisposableEffectResult
                                                                        public void dispose() {
                                                                            SnapshotStateList.this.remove(s6);
                                                                            animatedContentTransitionScopeImpl6.getTargetSizeMap$animation().remove(s6);
                                                                        }
                                                                    };
                                                                }
                                                            };
                                                            composer3.updateRememberedValue(rememberedValue12);
                                                        }
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                        EffectsKt.DisposableEffect(animatedVisibilityScope, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue12, composer3, i14 & 14);
                                                        MutableScatterMap targetSizeMap$animation = animatedContentTransitionScopeImpl4.getTargetSizeMap$animation();
                                                        S s6 = s4;
                                                        Intrinsics.checkNotNull(animatedVisibilityScope, "null cannot be cast to non-null type androidx.compose.animation.AnimatedVisibilityScopeImpl");
                                                        targetSizeMap$animation.set(s6, ((AnimatedVisibilityScopeImpl) animatedVisibilityScope).getTargetSize$animation());
                                                        ComposerKt.sourceInformationMarkerStart(composer3, -316880684, "CC(remember):AnimatedContent.kt#9igjgp");
                                                        Object rememberedValue13 = composer3.rememberedValue();
                                                        if (rememberedValue13 == Composer.INSTANCE.getEmpty()) {
                                                            rememberedValue13 = new AnimatedContentScopeImpl(animatedVisibilityScope);
                                                            composer3.updateRememberedValue(rememberedValue13);
                                                        }
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                        function44.invoke((AnimatedContentScopeImpl) rememberedValue13, s4, composer3, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                        }
                                                    }
                                                }, composer2, 54), composer2, 12582912, 64);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }, startRestartGroup, 54));
                                        transition2 = transition;
                                        function13 = function16;
                                        mutableScatterMap2 = mutableScatterMap3;
                                        str2 = str3;
                                        function42 = function4;
                                        animatedContentTransitionScopeImpl2 = animatedContentTransitionScopeImpl3;
                                        size = i12;
                                        snapshotStateList2 = snapshotStateList4;
                                        i10 = i11 + 1;
                                        snapshotStateList3 = snapshotStateList3;
                                    }
                                    str = str2;
                                    function15 = function13;
                                    animatedContentTransitionScopeImpl = animatedContentTransitionScopeImpl2;
                                    snapshotStateList = snapshotStateList2;
                                    mutableScatterMap = mutableScatterMap2;
                                    startRestartGroup.endReplaceGroup();
                                } else {
                                    startRestartGroup.startReplaceGroup(1969054067);
                                    startRestartGroup.endReplaceGroup();
                                    str = "CC(remember):AnimatedContent.kt#9igjgp";
                                    function15 = function13;
                                    animatedContentTransitionScopeImpl = animatedContentTransitionScopeImpl2;
                                    snapshotStateList = snapshotStateList2;
                                    mutableScatterMap = mutableScatterMap2;
                                }
                                Transition.Segment<S> segment = transition.getSegment();
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2141728809, str);
                                boolean changed = startRestartGroup.changed(segment) | startRestartGroup.changed(animatedContentTransitionScopeImpl);
                                ContentTransform rememberedValue6 = startRestartGroup.rememberedValue();
                                if (changed || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue6 = function15.invoke(animatedContentTransitionScopeImpl);
                                    startRestartGroup.updateRememberedValue(rememberedValue6);
                                }
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                Modifier then = modifier4.then(animatedContentTransitionScopeImpl.createSizeAnimationModifier$animation((ContentTransform) rememberedValue6, startRestartGroup, 0));
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2141739907, str);
                                Object rememberedValue7 = startRestartGroup.rememberedValue();
                                if (rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue7 = new AnimatedContentMeasurePolicy(animatedContentTransitionScopeImpl);
                                    startRestartGroup.updateRememberedValue(rememberedValue7);
                                }
                                AnimatedContentMeasurePolicy animatedContentMeasurePolicy = (AnimatedContentMeasurePolicy) rememberedValue7;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)80@3267L27,83@3433L360:Layout.kt#80mrfh");
                                int m = UByte$$ExternalSyntheticBackport0.m(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                                CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
                                Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, then);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                startRestartGroup.startReusableNode();
                                if (startRestartGroup.getInserting()) {
                                    startRestartGroup.createNode(constructor);
                                } else {
                                    startRestartGroup.useNode();
                                }
                                Composer m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
                                Updater.m3912setimpl(m3905constructorimpl, animatedContentMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3912setimpl(m3905constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (m3905constructorimpl.getInserting() || !Intrinsics.areEqual(m3905constructorimpl.rememberedValue(), Integer.valueOf(m))) {
                                    m3905constructorimpl.updateRememberedValue(Integer.valueOf(m));
                                    m3905constructorimpl.apply(Integer.valueOf(m), setCompositeKeyHash);
                                }
                                Updater.m3912setimpl(m3905constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -895590996, "C:AnimatedContent.kt#xbi5r1");
                                startRestartGroup.startReplaceGroup(-860173498);
                                ComposerKt.sourceInformation(startRestartGroup, "");
                                SnapshotStateList snapshotStateList5 = snapshotStateList;
                                int size2 = snapshotStateList5.size();
                                for (int i13 = 0; i13 < size2; i13++) {
                                    R.animator animatorVar = (Object) snapshotStateList5.get(i13);
                                    startRestartGroup.startMovableGroup(-2026002954, function14.invoke(animatorVar));
                                    ComposerKt.sourceInformation(startRestartGroup, "");
                                    Function2 function2 = (Function2) mutableScatterMap.get(animatorVar);
                                    if (function2 == null) {
                                        startRestartGroup.startReplaceGroup(1618454323);
                                        startRestartGroup.endReplaceGroup();
                                    } else {
                                        startRestartGroup.startReplaceGroup(-2026001778);
                                        ComposerKt.sourceInformation(startRestartGroup, "872@43737L8");
                                        function2.invoke(startRestartGroup, 0);
                                        startRestartGroup.endReplaceGroup();
                                        Unit unit2 = Unit.INSTANCE;
                                    }
                                    startRestartGroup.endMovableGroup();
                                }
                                startRestartGroup.endReplaceGroup();
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                startRestartGroup.endNode();
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                            }
                            final Alignment alignment3 = alignment2;
                            final Function1<? super S, ? extends Object> function17 = function14;
                            endRestartGroup = startRestartGroup.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$9
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                        invoke(composer2, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer2, int i14) {
                                        AnimatedContentKt.AnimatedContent(transition, modifier3, function15, alignment3, function17, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        function42 = function4;
                        if (startRestartGroup.shouldExecute((74899 & i3) == 74898, i3 & 1)) {
                        }
                        final Alignment alignment32 = alignment2;
                        final Function1<? super S, ? extends Object> function172 = function14;
                        endRestartGroup = startRestartGroup.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    function14 = function12;
                    if ((i2 & 16) != 0) {
                    }
                    function42 = function4;
                    if (startRestartGroup.shouldExecute((74899 & i3) == 74898, i3 & 1)) {
                    }
                    final Alignment alignment322 = alignment2;
                    final Function1<? super S, ? extends Object> function1722 = function14;
                    endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                alignment2 = alignment;
                i6 = i2 & 8;
                if (i6 == 0) {
                }
                function14 = function12;
                if ((i2 & 16) != 0) {
                }
                function42 = function4;
                if (startRestartGroup.shouldExecute((74899 & i3) == 74898, i3 & 1)) {
                }
                final Alignment alignment3222 = alignment2;
                final Function1<? super S, ? extends Object> function17222 = function14;
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            function13 = function1;
            i5 = i2 & 4;
            if (i5 != 0) {
            }
            alignment2 = alignment;
            i6 = i2 & 8;
            if (i6 == 0) {
            }
            function14 = function12;
            if ((i2 & 16) != 0) {
            }
            function42 = function4;
            if (startRestartGroup.shouldExecute((74899 & i3) == 74898, i3 & 1)) {
            }
            final Alignment alignment32222 = alignment2;
            final Function1<? super S, ? extends Object> function172222 = function14;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i4 = i2 & 2;
        if (i4 == 0) {
        }
        function13 = function1;
        i5 = i2 & 4;
        if (i5 != 0) {
        }
        alignment2 = alignment;
        i6 = i2 & 8;
        if (i6 == 0) {
        }
        function14 = function12;
        if ((i2 & 16) != 0) {
        }
        function42 = function4;
        if (startRestartGroup.shouldExecute((74899 & i3) == 74898, i3 & 1)) {
        }
        final Alignment alignment322222 = alignment2;
        final Function1<? super S, ? extends Object> function1722222 = function14;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    static {
        long j = Integer.MIN_VALUE;
        UnspecifiedSize = IntSize.m7569constructorimpl((j & 4294967295L) | (j << 32));
    }
}
