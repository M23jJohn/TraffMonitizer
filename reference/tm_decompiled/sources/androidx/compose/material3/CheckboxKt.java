package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.material3.tokens.CheckboxTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.state.ToggleableStateKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;

/* compiled from: Checkbox.kt */
@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aU\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a-\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0003¢\u0006\u0002\u0010\u001b\u001aO\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001a2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0007¢\u0006\u0002\u0010 \u001a6\u0010!\u001a\u00020\u000b*\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'H\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a>\u0010+\u001a\u00020\u000b*\u00020\"2\u0006\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020'2\u0006\u0010.\u001a\u00020'2\u0006\u0010/\u001a\u00020'2\u0006\u00100\u001a\u000201H\u0002ø\u0001\u0000¢\u0006\u0004\b2\u00103\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0010\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0010\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0010\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00064"}, d2 = {"BoxInDuration", "", "BoxOutDuration", "CheckAnimationDuration", "CheckboxDefaultPadding", "Landroidx/compose/ui/unit/Dp;", "F", "CheckboxSize", "RadiusSize", "StrokeWidth", "Checkbox", "", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "colors", "Landroidx/compose/material3/CheckboxColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/CheckboxColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "CheckboxImpl", "value", "Landroidx/compose/ui/state/ToggleableState;", "(ZLandroidx/compose/ui/state/ToggleableState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/CheckboxColors;Landroidx/compose/runtime/Composer;I)V", "TriStateCheckbox", "state", "onClick", "Lkotlin/Function0;", "(Landroidx/compose/ui/state/ToggleableState;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/CheckboxColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "drawBox", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "boxColor", "Landroidx/compose/ui/graphics/Color;", "borderColor", "radius", "", "strokeWidth", "drawBox-1wkBAMs", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJFF)V", "drawCheck", "checkColor", "checkFraction", "crossCenterGravitation", "strokeWidthPx", "drawingCache", "Landroidx/compose/material3/CheckDrawingCache;", "drawCheck-3IgeMak", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFFFLandroidx/compose/material3/CheckDrawingCache;)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CheckboxKt {
    private static final int BoxInDuration = 50;
    private static final int BoxOutDuration = 100;
    private static final int CheckAnimationDuration = 100;
    private static final float CheckboxDefaultPadding;
    private static final float CheckboxSize = Dp.m7399constructorimpl(20);
    private static final float RadiusSize;
    private static final float StrokeWidth;

    /* compiled from: Checkbox.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToggleableState.values().length];
            try {
                iArr[ToggleableState.On.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ToggleableState.Off.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ToggleableState.Indeterminate.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void Checkbox(final boolean z, final Function1<? super Boolean, Unit> function1, Modifier modifier, boolean z2, CheckboxColors checkboxColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        boolean z3;
        CheckboxColors checkboxColors2;
        int i5;
        MutableInteractionSource mutableInteractionSource2;
        CheckboxColors checkboxColors3;
        final Modifier modifier3;
        final boolean z4;
        final CheckboxColors checkboxColors4;
        ScopeUpdateScope endRestartGroup;
        int i6;
        Composer startRestartGroup = composer.startRestartGroup(-1406741137);
        ComposerKt.sourceInformation(startRestartGroup, "C(Checkbox)P(!1,5,4,2)96@4296L8,99@4370L356:Checkbox.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= startRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i7 = i2 & 4;
        if (i7 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 256 : 128;
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                z3 = z2;
                i3 |= startRestartGroup.changed(z3) ? 2048 : 1024;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        checkboxColors2 = checkboxColors;
                        if (startRestartGroup.changed(checkboxColors2)) {
                            i6 = 16384;
                            i3 |= i6;
                        }
                    } else {
                        checkboxColors2 = checkboxColors;
                    }
                    i6 = 8192;
                    i3 |= i6;
                } else {
                    checkboxColors2 = checkboxColors;
                }
                i5 = i2 & 32;
                if (i5 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    i3 |= startRestartGroup.changed(mutableInteractionSource2) ? 131072 : 65536;
                    if ((74899 & i3) == 74898 || !startRestartGroup.getSkipping()) {
                        startRestartGroup.startDefaults();
                        Function0 function0 = null;
                        if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                checkboxColors2 = CheckboxDefaults.INSTANCE.colors(startRestartGroup, 6);
                            }
                            if (i5 != 0) {
                                checkboxColors3 = checkboxColors2;
                                mutableInteractionSource2 = null;
                                boolean z5 = z3;
                                Modifier modifier4 = modifier2;
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1406741137, i3, -1, "androidx.compose.material3.Checkbox (Checkbox.kt:98)");
                                }
                                ToggleableState ToggleableState = ToggleableStateKt.ToggleableState(z);
                                startRestartGroup.startReplaceGroup(1046936362);
                                ComposerKt.sourceInformation(startRestartGroup, "103@4507L29");
                                if (function1 != null) {
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1046937763, "CC(remember):Checkbox.kt#9igjgp");
                                    boolean z6 = ((i3 & 112) == 32) | ((i3 & 14) == 4);
                                    Object rememberedValue = startRestartGroup.rememberedValue();
                                    if (z6 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$1$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(0);
                                            }

                                            @Override // kotlin.jvm.functions.Function0
                                            public /* bridge */ /* synthetic */ Unit invoke() {
                                                invoke2();
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2() {
                                                function1.invoke(Boolean.valueOf(!z));
                                            }
                                        };
                                        startRestartGroup.updateRememberedValue(rememberedValue);
                                    }
                                    function0 = (Function0) rememberedValue;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                }
                                startRestartGroup.endReplaceGroup();
                                TriStateCheckbox(ToggleableState, function0, modifier4, z5, checkboxColors3, mutableInteractionSource2, startRestartGroup, i3 & 524160, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                z4 = z5;
                                checkboxColors4 = checkboxColors3;
                            }
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                        }
                        checkboxColors3 = checkboxColors2;
                        boolean z52 = z3;
                        Modifier modifier42 = modifier2;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        ToggleableState ToggleableState2 = ToggleableStateKt.ToggleableState(z);
                        startRestartGroup.startReplaceGroup(1046936362);
                        ComposerKt.sourceInformation(startRestartGroup, "103@4507L29");
                        if (function1 != null) {
                        }
                        startRestartGroup.endReplaceGroup();
                        TriStateCheckbox(ToggleableState2, function0, modifier42, z52, checkboxColors3, mutableInteractionSource2, startRestartGroup, i3 & 524160, 0);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        modifier3 = modifier42;
                        z4 = z52;
                        checkboxColors4 = checkboxColors3;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z4 = z3;
                        checkboxColors4 = checkboxColors2;
                    }
                    final MutableInteractionSource mutableInteractionSource3 = mutableInteractionSource2;
                    endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup == null) {
                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt$Checkbox$2
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

                            public final void invoke(Composer composer2, int i8) {
                                CheckboxKt.Checkbox(z, function1, modifier3, z4, checkboxColors4, mutableInteractionSource3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                        return;
                    }
                    return;
                }
                mutableInteractionSource2 = mutableInteractionSource;
                if ((74899 & i3) == 74898) {
                }
                startRestartGroup.startDefaults();
                Function0 function02 = null;
                if ((i & 1) != 0) {
                }
                if (i7 != 0) {
                }
                if (i4 != 0) {
                }
                if ((i2 & 16) != 0) {
                }
                if (i5 != 0) {
                }
                checkboxColors3 = checkboxColors2;
                boolean z522 = z3;
                Modifier modifier422 = modifier2;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                ToggleableState ToggleableState22 = ToggleableStateKt.ToggleableState(z);
                startRestartGroup.startReplaceGroup(1046936362);
                ComposerKt.sourceInformation(startRestartGroup, "103@4507L29");
                if (function1 != null) {
                }
                startRestartGroup.endReplaceGroup();
                TriStateCheckbox(ToggleableState22, function02, modifier422, z522, checkboxColors3, mutableInteractionSource2, startRestartGroup, i3 & 524160, 0);
                if (ComposerKt.isTraceInProgress()) {
                }
                modifier3 = modifier422;
                z4 = z522;
                checkboxColors4 = checkboxColors3;
                final MutableInteractionSource mutableInteractionSource32 = mutableInteractionSource2;
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z3 = z2;
            if ((i & 24576) == 0) {
            }
            i5 = i2 & 32;
            if (i5 != 0) {
            }
            mutableInteractionSource2 = mutableInteractionSource;
            if ((74899 & i3) == 74898) {
            }
            startRestartGroup.startDefaults();
            Function0 function022 = null;
            if ((i & 1) != 0) {
            }
            if (i7 != 0) {
            }
            if (i4 != 0) {
            }
            if ((i2 & 16) != 0) {
            }
            if (i5 != 0) {
            }
            checkboxColors3 = checkboxColors2;
            boolean z5222 = z3;
            Modifier modifier4222 = modifier2;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            ToggleableState ToggleableState222 = ToggleableStateKt.ToggleableState(z);
            startRestartGroup.startReplaceGroup(1046936362);
            ComposerKt.sourceInformation(startRestartGroup, "103@4507L29");
            if (function1 != null) {
            }
            startRestartGroup.endReplaceGroup();
            TriStateCheckbox(ToggleableState222, function022, modifier4222, z5222, checkboxColors3, mutableInteractionSource2, startRestartGroup, i3 & 524160, 0);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier3 = modifier4222;
            z4 = z5222;
            checkboxColors4 = checkboxColors3;
            final MutableInteractionSource mutableInteractionSource322 = mutableInteractionSource2;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        z3 = z2;
        if ((i & 24576) == 0) {
        }
        i5 = i2 & 32;
        if (i5 != 0) {
        }
        mutableInteractionSource2 = mutableInteractionSource;
        if ((74899 & i3) == 74898) {
        }
        startRestartGroup.startDefaults();
        Function0 function0222 = null;
        if ((i & 1) != 0) {
        }
        if (i7 != 0) {
        }
        if (i4 != 0) {
        }
        if ((i2 & 16) != 0) {
        }
        if (i5 != 0) {
        }
        checkboxColors3 = checkboxColors2;
        boolean z52222 = z3;
        Modifier modifier42222 = modifier2;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        ToggleableState ToggleableState2222 = ToggleableStateKt.ToggleableState(z);
        startRestartGroup.startReplaceGroup(1046936362);
        ComposerKt.sourceInformation(startRestartGroup, "103@4507L29");
        if (function1 != null) {
        }
        startRestartGroup.endReplaceGroup();
        TriStateCheckbox(ToggleableState2222, function0222, modifier42222, z52222, checkboxColors3, mutableInteractionSource2, startRestartGroup, i3 & 524160, 0);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier3 = modifier42222;
        z4 = z52222;
        checkboxColors4 = checkboxColors3;
        final MutableInteractionSource mutableInteractionSource3222 = mutableInteractionSource2;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void TriStateCheckbox(final ToggleableState toggleableState, final Function0<Unit> function0, Modifier modifier, boolean z, CheckboxColors checkboxColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        ToggleableState toggleableState2;
        int i3;
        Modifier modifier2;
        int i4;
        boolean z2;
        CheckboxColors checkboxColors2;
        int i5;
        MutableInteractionSource mutableInteractionSource2;
        Modifier modifier3;
        CheckboxColors checkboxColors3;
        MutableInteractionSource mutableInteractionSource3;
        int i6;
        boolean z3;
        boolean z4;
        int i7;
        Modifier.Companion companion;
        Modifier.Companion companion2;
        final CheckboxColors checkboxColors4;
        final Modifier modifier4;
        final boolean z5;
        ScopeUpdateScope endRestartGroup;
        int i8;
        Composer startRestartGroup = composer.startRestartGroup(-1608358065);
        ComposerKt.sourceInformation(startRestartGroup, "C(TriStateCheckbox)P(5,4,3,1)149@6731L8,169@7373L460:Checkbox.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            toggleableState2 = toggleableState;
        } else if ((i & 6) == 0) {
            toggleableState2 = toggleableState;
            i3 = (startRestartGroup.changed(toggleableState2) ? 4 : 2) | i;
        } else {
            toggleableState2 = toggleableState;
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= startRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        int i9 = i2 & 4;
        if (i9 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 256 : 128;
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                z2 = z;
                i3 |= startRestartGroup.changed(z2) ? 2048 : 1024;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        checkboxColors2 = checkboxColors;
                        if (startRestartGroup.changed(checkboxColors2)) {
                            i8 = 16384;
                            i3 |= i8;
                        }
                    } else {
                        checkboxColors2 = checkboxColors;
                    }
                    i8 = 8192;
                    i3 |= i8;
                } else {
                    checkboxColors2 = checkboxColors;
                }
                i5 = i2 & 32;
                if (i5 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    i3 |= startRestartGroup.changed(mutableInteractionSource2) ? 131072 : 65536;
                    if ((74899 & i3) == 74898 || !startRestartGroup.getSkipping()) {
                        startRestartGroup.startDefaults();
                        if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                            Modifier.Companion companion3 = i9 == 0 ? Modifier.INSTANCE : modifier2;
                            if (i4 != 0) {
                                z2 = true;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                checkboxColors2 = CheckboxDefaults.INSTANCE.colors(startRestartGroup, 6);
                            }
                            if (i5 == 0) {
                                modifier3 = companion3;
                                mutableInteractionSource3 = null;
                                checkboxColors3 = checkboxColors2;
                            } else {
                                modifier3 = companion3;
                                checkboxColors3 = checkboxColors2;
                                mutableInteractionSource3 = mutableInteractionSource2;
                            }
                            i6 = i3;
                            z3 = z2;
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                            i6 = i3;
                            modifier3 = modifier2;
                            z3 = z2;
                            checkboxColors3 = checkboxColors2;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1608358065, i6, -1, "androidx.compose.material3.TriStateCheckbox (Checkbox.kt:151)");
                        }
                        startRestartGroup.startReplaceGroup(-97239746);
                        ComposerKt.sourceInformation(startRestartGroup, "161@7145L161");
                        if (function0 == null) {
                            i7 = i6;
                            companion = ToggleableKt.m1218triStateToggleableO2vRcR0(Modifier.INSTANCE, toggleableState2, mutableInteractionSource3, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, Dp.m7399constructorimpl(CheckboxTokens.INSTANCE.m3214getStateLayerSizeD9Ej5fM() / 2), 0L, startRestartGroup, 54, 4), z3, Role.m6589boximpl(Role.INSTANCE.m6598getCheckboxo7Vup1c()), function0);
                            mutableInteractionSource2 = mutableInteractionSource3;
                            z4 = z3;
                        } else {
                            mutableInteractionSource2 = mutableInteractionSource3;
                            z4 = z3;
                            i7 = i6;
                            companion = Modifier.INSTANCE;
                        }
                        startRestartGroup.endReplaceGroup();
                        if (function0 == null) {
                            companion2 = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                        } else {
                            companion2 = Modifier.INSTANCE;
                        }
                        CheckboxColors checkboxColors5 = checkboxColors3;
                        CheckboxImpl(z4, toggleableState, PaddingKt.m878padding3ABfNKs(modifier3.then(companion2).then(companion), CheckboxDefaultPadding), checkboxColors5, startRestartGroup, ((i7 >> 9) & 14) | ((i7 << 3) & 112) | ((i7 >> 3) & 7168));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        checkboxColors4 = checkboxColors5;
                        modifier4 = modifier3;
                        z5 = z4;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        modifier4 = modifier2;
                        z5 = z2;
                        checkboxColors4 = checkboxColors2;
                    }
                    final MutableInteractionSource mutableInteractionSource4 = mutableInteractionSource2;
                    endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup == null) {
                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt$TriStateCheckbox$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i10) {
                                CheckboxKt.TriStateCheckbox(ToggleableState.this, function0, modifier4, z5, checkboxColors4, mutableInteractionSource4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                        return;
                    }
                    return;
                }
                mutableInteractionSource2 = mutableInteractionSource;
                if ((74899 & i3) == 74898) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i9 == 0) {
                }
                if (i4 != 0) {
                }
                if ((i2 & 16) != 0) {
                }
                if (i5 == 0) {
                }
                i6 = i3;
                z3 = z2;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                startRestartGroup.startReplaceGroup(-97239746);
                ComposerKt.sourceInformation(startRestartGroup, "161@7145L161");
                if (function0 == null) {
                }
                startRestartGroup.endReplaceGroup();
                if (function0 == null) {
                }
                CheckboxColors checkboxColors52 = checkboxColors3;
                CheckboxImpl(z4, toggleableState, PaddingKt.m878padding3ABfNKs(modifier3.then(companion2).then(companion), CheckboxDefaultPadding), checkboxColors52, startRestartGroup, ((i7 >> 9) & 14) | ((i7 << 3) & 112) | ((i7 >> 3) & 7168));
                if (ComposerKt.isTraceInProgress()) {
                }
                checkboxColors4 = checkboxColors52;
                modifier4 = modifier3;
                z5 = z4;
                final MutableInteractionSource mutableInteractionSource42 = mutableInteractionSource2;
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z2 = z;
            if ((i & 24576) == 0) {
            }
            i5 = i2 & 32;
            if (i5 != 0) {
            }
            mutableInteractionSource2 = mutableInteractionSource;
            if ((74899 & i3) == 74898) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i9 == 0) {
            }
            if (i4 != 0) {
            }
            if ((i2 & 16) != 0) {
            }
            if (i5 == 0) {
            }
            i6 = i3;
            z3 = z2;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.startReplaceGroup(-97239746);
            ComposerKt.sourceInformation(startRestartGroup, "161@7145L161");
            if (function0 == null) {
            }
            startRestartGroup.endReplaceGroup();
            if (function0 == null) {
            }
            CheckboxColors checkboxColors522 = checkboxColors3;
            CheckboxImpl(z4, toggleableState, PaddingKt.m878padding3ABfNKs(modifier3.then(companion2).then(companion), CheckboxDefaultPadding), checkboxColors522, startRestartGroup, ((i7 >> 9) & 14) | ((i7 << 3) & 112) | ((i7 >> 3) & 7168));
            if (ComposerKt.isTraceInProgress()) {
            }
            checkboxColors4 = checkboxColors522;
            modifier4 = modifier3;
            z5 = z4;
            final MutableInteractionSource mutableInteractionSource422 = mutableInteractionSource2;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        z2 = z;
        if ((i & 24576) == 0) {
        }
        i5 = i2 & 32;
        if (i5 != 0) {
        }
        mutableInteractionSource2 = mutableInteractionSource;
        if ((74899 & i3) == 74898) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i9 == 0) {
        }
        if (i4 != 0) {
        }
        if ((i2 & 16) != 0) {
        }
        if (i5 == 0) {
        }
        i6 = i3;
        z3 = z2;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        startRestartGroup.startReplaceGroup(-97239746);
        ComposerKt.sourceInformation(startRestartGroup, "161@7145L161");
        if (function0 == null) {
        }
        startRestartGroup.endReplaceGroup();
        if (function0 == null) {
        }
        CheckboxColors checkboxColors5222 = checkboxColors3;
        CheckboxImpl(z4, toggleableState, PaddingKt.m878padding3ABfNKs(modifier3.then(companion2).then(companion), CheckboxDefaultPadding), checkboxColors5222, startRestartGroup, ((i7 >> 9) & 14) | ((i7 << 3) & 112) | ((i7 >> 3) & 7168));
        if (ComposerKt.isTraceInProgress()) {
        }
        checkboxColors4 = checkboxColors5222;
        modifier4 = modifier3;
        z5 = z4;
        final MutableInteractionSource mutableInteractionSource4222 = mutableInteractionSource2;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0204  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void CheckboxImpl(final boolean z, final ToggleableState toggleableState, final Modifier modifier, final CheckboxColors checkboxColors, Composer composer, final int i) {
        int i2;
        float f;
        ToggleableState toggleableState2;
        Transition transition;
        int i3;
        char c;
        float f2;
        int i4;
        int i5;
        int i6;
        float f3;
        int i7;
        Object rememberedValue;
        boolean changed;
        Object rememberedValue2;
        Composer startRestartGroup = composer.startRestartGroup(2007131616);
        ComposerKt.sourceInformation(startRestartGroup, "C(CheckboxImpl)P(1,3,2)272@12420L23,274@12491L499,291@13057L514,306@13593L32,307@13654L21,308@13702L24,309@13756L27,310@13866L538,310@13788L616:Checkbox.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(toggleableState) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= startRestartGroup.changed(checkboxColors) ? 2048 : 1024;
        }
        int i8 = i2;
        if ((i8 & 1171) == 1170 && startRestartGroup.getSkipping()) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2007131616, i8, -1, "androidx.compose.material3.CheckboxImpl (Checkbox.kt:271)");
            }
            int i9 = i8 >> 3;
            int i10 = i9 & 14;
            Transition updateTransition = TransitionKt.updateTransition(toggleableState, (String) null, startRestartGroup, i10, 2);
            CheckboxKt$CheckboxImpl$checkDrawFraction$1 checkboxKt$CheckboxImpl$checkDrawFraction$1 = new Function3<Transition.Segment<ToggleableState>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$checkDrawFraction$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, Integer num) {
                    return invoke(segment, composer2, num.intValue());
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, int i11) {
                    SpringSpec snap;
                    composer2.startReplaceGroup(1373301606);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1373301606, i11, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:276)");
                    }
                    if (segment.getInitialState() == ToggleableState.Off) {
                        snap = AnimationSpecKt.tween$default(100, 0, null, 6, null);
                    } else {
                        snap = segment.getTargetState() == ToggleableState.Off ? AnimationSpecKt.snap(100) : AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return snap;
                }
            };
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
            ToggleableState toggleableState3 = (ToggleableState) updateTransition.getCurrentState();
            startRestartGroup.startReplaceGroup(1800065638);
            ComposerKt.sourceInformation(startRestartGroup, "C:Checkbox.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1800065638, 0, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:283)");
            }
            int i11 = WhenMappings.$EnumSwitchMapping$0[toggleableState3.ordinal()];
            float f4 = 0.0f;
            if (i11 != 1) {
                if (i11 == 2) {
                    f = 0.0f;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    startRestartGroup.endReplaceGroup();
                    Float valueOf = Float.valueOf(f);
                    ToggleableState toggleableState4 = (ToggleableState) updateTransition.getTargetState();
                    startRestartGroup.startReplaceGroup(1800065638);
                    ComposerKt.sourceInformation(startRestartGroup, "C:Checkbox.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        toggleableState2 = toggleableState4;
                        transition = updateTransition;
                    } else {
                        toggleableState2 = toggleableState4;
                        transition = updateTransition;
                        ComposerKt.traceEventStart(1800065638, 0, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:283)");
                    }
                    i3 = WhenMappings.$EnumSwitchMapping$0[toggleableState2.ordinal()];
                    if (i3 != 1) {
                        c = 3;
                    } else if (i3 != 2) {
                        c = 3;
                        if (i3 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                    } else {
                        c = 3;
                        f2 = 0.0f;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        startRestartGroup.endReplaceGroup();
                        Transition transition2 = transition;
                        final State createTransitionAnimation = TransitionKt.createTransitionAnimation(transition2, valueOf, Float.valueOf(f2), checkboxKt$CheckboxImpl$checkDrawFraction$1.invoke((CheckboxKt$CheckboxImpl$checkDrawFraction$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter, "FloatAnimation", startRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1 checkboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1 = new Function3<Transition.Segment<ToggleableState>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, Integer num) {
                                return invoke(segment, composer2, num.intValue());
                            }

                            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, int i12) {
                                TweenSpec snap;
                                composer2.startReplaceGroup(-1324481169);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1324481169, i12, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:293)");
                                }
                                if (segment.getInitialState() == ToggleableState.Off) {
                                    snap = AnimationSpecKt.snap$default(0, 1, null);
                                } else {
                                    snap = segment.getTargetState() == ToggleableState.Off ? AnimationSpecKt.snap(100) : AnimationSpecKt.tween$default(100, 0, null, 6, null);
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer2.endReplaceGroup();
                                return snap;
                            }
                        };
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                        TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                        ToggleableState toggleableState5 = (ToggleableState) transition2.getCurrentState();
                        startRestartGroup.startReplaceGroup(-1426969489);
                        ComposerKt.sourceInformation(startRestartGroup, "C:Checkbox.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            i4 = -1;
                            ComposerKt.traceEventStart(-1426969489, 0, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:300)");
                        } else {
                            i4 = -1;
                        }
                        i5 = WhenMappings.$EnumSwitchMapping$0[toggleableState5.ordinal()];
                        if (i5 != 1 || i5 == 2) {
                            i6 = 3;
                            f3 = 0.0f;
                        } else {
                            i6 = 3;
                            if (i5 != 3) {
                                throw new NoWhenBranchMatchedException();
                            }
                            f3 = 1.0f;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        startRestartGroup.endReplaceGroup();
                        Float valueOf2 = Float.valueOf(f3);
                        ToggleableState toggleableState6 = (ToggleableState) transition2.getTargetState();
                        startRestartGroup.startReplaceGroup(-1426969489);
                        ComposerKt.sourceInformation(startRestartGroup, "C:Checkbox.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1426969489, 0, i4, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:300)");
                        }
                        i7 = WhenMappings.$EnumSwitchMapping$0[toggleableState6.ordinal()];
                        if (i7 != 1 && i7 != 2) {
                            if (i7 == i6) {
                                throw new NoWhenBranchMatchedException();
                            }
                            f4 = 1.0f;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        startRestartGroup.endReplaceGroup();
                        final State createTransitionAnimation2 = TransitionKt.createTransitionAnimation(transition2, valueOf2, Float.valueOf(f4), checkboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1.invoke((CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1) transition2.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter2, "FloatAnimation", startRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 974828454, "CC(remember):Checkbox.kt#9igjgp");
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                            rememberedValue = new CheckDrawingCache(null, null, null, 7, null);
                            startRestartGroup.updateRememberedValue(rememberedValue);
                        }
                        final CheckDrawingCache checkDrawingCache = (CheckDrawingCache) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        final State<Color> checkmarkColor$material3_release = checkboxColors.checkmarkColor$material3_release(toggleableState, startRestartGroup, i10 | ((i8 >> 6) & 112));
                        int i12 = (i9 & 896) | (i8 & 126);
                        final State<Color> boxColor$material3_release = checkboxColors.boxColor$material3_release(z, toggleableState, startRestartGroup, i12);
                        final State<Color> borderColor$material3_release = checkboxColors.borderColor$material3_release(z, toggleableState, startRestartGroup, i12);
                        Modifier m922requiredSize3ABfNKs = SizeKt.m922requiredSize3ABfNKs(SizeKt.wrapContentSize$default(modifier, Alignment.INSTANCE.getCenter(), false, 2, null), CheckboxSize);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 974837696, "CC(remember):Checkbox.kt#9igjgp");
                        changed = startRestartGroup.changed(boxColor$material3_release) | startRestartGroup.changed(borderColor$material3_release) | startRestartGroup.changed(checkmarkColor$material3_release) | startRestartGroup.changed(createTransitionAnimation) | startRestartGroup.changed(createTransitionAnimation2);
                        rememberedValue2 = startRestartGroup.rememberedValue();
                        if (!changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            rememberedValue2 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                                    invoke2(drawScope);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(DrawScope drawScope) {
                                    float f5;
                                    float f6;
                                    f5 = CheckboxKt.StrokeWidth;
                                    float floor = (float) Math.floor(drawScope.mo459toPx0680j_4(f5));
                                    long m4625unboximpl = boxColor$material3_release.getValue().m4625unboximpl();
                                    long m4625unboximpl2 = borderColor$material3_release.getValue().m4625unboximpl();
                                    f6 = CheckboxKt.RadiusSize;
                                    CheckboxKt.m2007drawBox1wkBAMs(drawScope, m4625unboximpl, m4625unboximpl2, drawScope.mo459toPx0680j_4(f6), floor);
                                    CheckboxKt.m2008drawCheck3IgeMak(drawScope, checkmarkColor$material3_release.getValue().m4625unboximpl(), createTransitionAnimation.getValue().floatValue(), createTransitionAnimation2.getValue().floatValue(), floor, checkDrawingCache);
                                }
                            };
                            startRestartGroup.updateRememberedValue(rememberedValue2);
                        }
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        CanvasKt.Canvas(m922requiredSize3ABfNKs, (Function1) rememberedValue2, startRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    f2 = 1.0f;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    Transition transition22 = transition;
                    final State<Float> createTransitionAnimation3 = TransitionKt.createTransitionAnimation(transition22, valueOf, Float.valueOf(f2), checkboxKt$CheckboxImpl$checkDrawFraction$1.invoke((CheckboxKt$CheckboxImpl$checkDrawFraction$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter, "FloatAnimation", startRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1 checkboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$12 = new Function3<Transition.Segment<ToggleableState>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, Integer num) {
                            return invoke(segment, composer2, num.intValue());
                        }

                        public final FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, int i122) {
                            TweenSpec snap;
                            composer2.startReplaceGroup(-1324481169);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1324481169, i122, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:293)");
                            }
                            if (segment.getInitialState() == ToggleableState.Off) {
                                snap = AnimationSpecKt.snap$default(0, 1, null);
                            } else {
                                snap = segment.getTargetState() == ToggleableState.Off ? AnimationSpecKt.snap(100) : AnimationSpecKt.tween$default(100, 0, null, 6, null);
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer2.endReplaceGroup();
                            return snap;
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
                    TwoWayConverter<Float, AnimationVector1D> vectorConverter22 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
                    ToggleableState toggleableState52 = (ToggleableState) transition22.getCurrentState();
                    startRestartGroup.startReplaceGroup(-1426969489);
                    ComposerKt.sourceInformation(startRestartGroup, "C:Checkbox.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    i5 = WhenMappings.$EnumSwitchMapping$0[toggleableState52.ordinal()];
                    if (i5 != 1) {
                    }
                    i6 = 3;
                    f3 = 0.0f;
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    Float valueOf22 = Float.valueOf(f3);
                    ToggleableState toggleableState62 = (ToggleableState) transition22.getTargetState();
                    startRestartGroup.startReplaceGroup(-1426969489);
                    ComposerKt.sourceInformation(startRestartGroup, "C:Checkbox.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    i7 = WhenMappings.$EnumSwitchMapping$0[toggleableState62.ordinal()];
                    if (i7 != 1) {
                        if (i7 == i6) {
                        }
                    }
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    startRestartGroup.endReplaceGroup();
                    final State<Float> createTransitionAnimation22 = TransitionKt.createTransitionAnimation(transition22, valueOf22, Float.valueOf(f4), checkboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$12.invoke((CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1) transition22.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter22, "FloatAnimation", startRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 974828454, "CC(remember):Checkbox.kt#9igjgp");
                    rememberedValue = startRestartGroup.rememberedValue();
                    if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    }
                    final CheckDrawingCache checkDrawingCache2 = (CheckDrawingCache) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    final State<Color> checkmarkColor$material3_release2 = checkboxColors.checkmarkColor$material3_release(toggleableState, startRestartGroup, i10 | ((i8 >> 6) & 112));
                    int i122 = (i9 & 896) | (i8 & 126);
                    final State<Color> boxColor$material3_release2 = checkboxColors.boxColor$material3_release(z, toggleableState, startRestartGroup, i122);
                    final State<Color> borderColor$material3_release2 = checkboxColors.borderColor$material3_release(z, toggleableState, startRestartGroup, i122);
                    Modifier m922requiredSize3ABfNKs2 = SizeKt.m922requiredSize3ABfNKs(SizeKt.wrapContentSize$default(modifier, Alignment.INSTANCE.getCenter(), false, 2, null), CheckboxSize);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 974837696, "CC(remember):Checkbox.kt#9igjgp");
                    changed = startRestartGroup.changed(boxColor$material3_release2) | startRestartGroup.changed(borderColor$material3_release2) | startRestartGroup.changed(checkmarkColor$material3_release2) | startRestartGroup.changed(createTransitionAnimation3) | startRestartGroup.changed(createTransitionAnimation22);
                    rememberedValue2 = startRestartGroup.rememberedValue();
                    if (!changed) {
                    }
                    rememberedValue2 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                            invoke2(drawScope);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DrawScope drawScope) {
                            float f5;
                            float f6;
                            f5 = CheckboxKt.StrokeWidth;
                            float floor = (float) Math.floor(drawScope.mo459toPx0680j_4(f5));
                            long m4625unboximpl = boxColor$material3_release2.getValue().m4625unboximpl();
                            long m4625unboximpl2 = borderColor$material3_release2.getValue().m4625unboximpl();
                            f6 = CheckboxKt.RadiusSize;
                            CheckboxKt.m2007drawBox1wkBAMs(drawScope, m4625unboximpl, m4625unboximpl2, drawScope.mo459toPx0680j_4(f6), floor);
                            CheckboxKt.m2008drawCheck3IgeMak(drawScope, checkmarkColor$material3_release2.getValue().m4625unboximpl(), createTransitionAnimation3.getValue().floatValue(), createTransitionAnimation22.getValue().floatValue(), floor, checkDrawingCache2);
                        }
                    };
                    startRestartGroup.updateRememberedValue(rememberedValue2);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    CanvasKt.Canvas(m922requiredSize3ABfNKs2, (Function1) rememberedValue2, startRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                } else if (i11 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
            }
            f = 1.0f;
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            Float valueOf3 = Float.valueOf(f);
            ToggleableState toggleableState42 = (ToggleableState) updateTransition.getTargetState();
            startRestartGroup.startReplaceGroup(1800065638);
            ComposerKt.sourceInformation(startRestartGroup, "C:Checkbox.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
            }
            i3 = WhenMappings.$EnumSwitchMapping$0[toggleableState2.ordinal()];
            if (i3 != 1) {
            }
            f2 = 1.0f;
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            Transition transition222 = transition;
            final State<Float> createTransitionAnimation32 = TransitionKt.createTransitionAnimation(transition222, valueOf3, Float.valueOf(f2), checkboxKt$CheckboxImpl$checkDrawFraction$1.invoke((CheckboxKt$CheckboxImpl$checkDrawFraction$1) transition.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter, "FloatAnimation", startRestartGroup, 0);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1 checkboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$122 = new Function3<Transition.Segment<ToggleableState>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, Integer num) {
                    return invoke(segment, composer2, num.intValue());
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer2, int i1222) {
                    TweenSpec snap;
                    composer2.startReplaceGroup(-1324481169);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1324481169, i1222, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:293)");
                    }
                    if (segment.getInitialState() == ToggleableState.Off) {
                        snap = AnimationSpecKt.snap$default(0, 1, null);
                    } else {
                        snap = segment.getTargetState() == ToggleableState.Off ? AnimationSpecKt.snap(100) : AnimationSpecKt.tween$default(100, 0, null, 6, null);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return snap;
                }
            };
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1338768149, "CC(animateFloat)P(2)1966@80444L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter222 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -142660079, "CC(animateValue)P(3,2)1883@77007L32,1884@77062L31,1885@77118L23,1887@77154L89:Transition.kt#pdpnli");
            ToggleableState toggleableState522 = (ToggleableState) transition222.getCurrentState();
            startRestartGroup.startReplaceGroup(-1426969489);
            ComposerKt.sourceInformation(startRestartGroup, "C:Checkbox.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
            }
            i5 = WhenMappings.$EnumSwitchMapping$0[toggleableState522.ordinal()];
            if (i5 != 1) {
            }
            i6 = 3;
            f3 = 0.0f;
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            Float valueOf222 = Float.valueOf(f3);
            ToggleableState toggleableState622 = (ToggleableState) transition222.getTargetState();
            startRestartGroup.startReplaceGroup(-1426969489);
            ComposerKt.sourceInformation(startRestartGroup, "C:Checkbox.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
            }
            i7 = WhenMappings.$EnumSwitchMapping$0[toggleableState622.ordinal()];
            if (i7 != 1) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
            startRestartGroup.endReplaceGroup();
            final State<Float> createTransitionAnimation222 = TransitionKt.createTransitionAnimation(transition222, valueOf222, Float.valueOf(f4), checkboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$122.invoke((CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1) transition222.getSegment(), (Transition.Segment) startRestartGroup, (Composer) 0), vectorConverter222, "FloatAnimation", startRestartGroup, 0);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 974828454, "CC(remember):Checkbox.kt#9igjgp");
            rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            }
            final CheckDrawingCache checkDrawingCache22 = (CheckDrawingCache) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final State<Color> checkmarkColor$material3_release22 = checkboxColors.checkmarkColor$material3_release(toggleableState, startRestartGroup, i10 | ((i8 >> 6) & 112));
            int i1222 = (i9 & 896) | (i8 & 126);
            final State<Color> boxColor$material3_release22 = checkboxColors.boxColor$material3_release(z, toggleableState, startRestartGroup, i1222);
            final State<Color> borderColor$material3_release22 = checkboxColors.borderColor$material3_release(z, toggleableState, startRestartGroup, i1222);
            Modifier m922requiredSize3ABfNKs22 = SizeKt.m922requiredSize3ABfNKs(SizeKt.wrapContentSize$default(modifier, Alignment.INSTANCE.getCenter(), false, 2, null), CheckboxSize);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 974837696, "CC(remember):Checkbox.kt#9igjgp");
            changed = startRestartGroup.changed(boxColor$material3_release22) | startRestartGroup.changed(borderColor$material3_release22) | startRestartGroup.changed(checkmarkColor$material3_release22) | startRestartGroup.changed(createTransitionAnimation32) | startRestartGroup.changed(createTransitionAnimation222);
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (!changed) {
            }
            rememberedValue2 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope drawScope) {
                    float f5;
                    float f6;
                    f5 = CheckboxKt.StrokeWidth;
                    float floor = (float) Math.floor(drawScope.mo459toPx0680j_4(f5));
                    long m4625unboximpl = boxColor$material3_release22.getValue().m4625unboximpl();
                    long m4625unboximpl2 = borderColor$material3_release22.getValue().m4625unboximpl();
                    f6 = CheckboxKt.RadiusSize;
                    CheckboxKt.m2007drawBox1wkBAMs(drawScope, m4625unboximpl, m4625unboximpl2, drawScope.mo459toPx0680j_4(f6), floor);
                    CheckboxKt.m2008drawCheck3IgeMak(drawScope, checkmarkColor$material3_release22.getValue().m4625unboximpl(), createTransitionAnimation32.getValue().floatValue(), createTransitionAnimation222.getValue().floatValue(), floor, checkDrawingCache22);
                }
            };
            startRestartGroup.updateRememberedValue(rememberedValue2);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            CanvasKt.Canvas(m922requiredSize3ABfNKs22, (Function1) rememberedValue2, startRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CheckboxKt$CheckboxImpl$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i13) {
                    CheckboxKt.CheckboxImpl(z, toggleableState, modifier, checkboxColors, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawBox-1wkBAMs, reason: not valid java name */
    public static final void m2007drawBox1wkBAMs(DrawScope drawScope, long j, long j2, float f, float f2) {
        float f3 = f2 / 2.0f;
        Stroke stroke = new Stroke(f2, 0.0f, 0, 0, null, 30, null);
        float m4412getWidthimpl = Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc());
        if (Color.m4616equalsimpl0(j, j2)) {
            DrawScope.CC.m5208drawRoundRectuAw5IA$default(drawScope, j, 0L, androidx.compose.ui.geometry.SizeKt.Size(m4412getWidthimpl, m4412getWidthimpl), CornerRadiusKt.CornerRadius$default(f, 0.0f, 2, null), Fill.INSTANCE, 0.0f, null, 0, 226, null);
            return;
        }
        float f4 = m4412getWidthimpl - (2 * f2);
        DrawScope.CC.m5208drawRoundRectuAw5IA$default(drawScope, j, OffsetKt.Offset(f2, f2), androidx.compose.ui.geometry.SizeKt.Size(f4, f4), CornerRadiusKt.CornerRadius$default(Math.max(0.0f, f - f2), 0.0f, 2, null), Fill.INSTANCE, 0.0f, null, 0, 224, null);
        float f5 = m4412getWidthimpl - f2;
        DrawScope.CC.m5208drawRoundRectuAw5IA$default(drawScope, j2, OffsetKt.Offset(f3, f3), androidx.compose.ui.geometry.SizeKt.Size(f5, f5), CornerRadiusKt.CornerRadius$default(f - f3, 0.0f, 2, null), stroke, 0.0f, null, 0, 224, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawCheck-3IgeMak, reason: not valid java name */
    public static final void m2008drawCheck3IgeMak(DrawScope drawScope, long j, float f, float f2, float f3, CheckDrawingCache checkDrawingCache) {
        Stroke stroke = new Stroke(f3, 0.0f, StrokeCap.INSTANCE.m4997getSquareKaPHkGw(), 0, null, 26, null);
        float m4412getWidthimpl = Size.m4412getWidthimpl(drawScope.mo5121getSizeNHjbRc());
        float lerp = MathHelpersKt.lerp(0.4f, 0.5f, f2);
        float lerp2 = MathHelpersKt.lerp(0.7f, 0.5f, f2);
        float lerp3 = MathHelpersKt.lerp(0.5f, 0.5f, f2);
        float lerp4 = MathHelpersKt.lerp(0.3f, 0.5f, f2);
        checkDrawingCache.getCheckPath().reset();
        checkDrawingCache.getCheckPath().moveTo(0.2f * m4412getWidthimpl, lerp3 * m4412getWidthimpl);
        checkDrawingCache.getCheckPath().lineTo(lerp * m4412getWidthimpl, lerp2 * m4412getWidthimpl);
        checkDrawingCache.getCheckPath().lineTo(0.8f * m4412getWidthimpl, m4412getWidthimpl * lerp4);
        checkDrawingCache.getPathMeasure().setPath(checkDrawingCache.getCheckPath(), false);
        checkDrawingCache.getPathToDraw().reset();
        checkDrawingCache.getPathMeasure().getSegment(0.0f, checkDrawingCache.getPathMeasure().getLength() * f, checkDrawingCache.getPathToDraw(), true);
        DrawScope.CC.m5202drawPathLG529CI$default(drawScope, checkDrawingCache.getPathToDraw(), j, 0.0f, stroke, null, 0, 52, null);
    }

    static {
        float f = 2;
        CheckboxDefaultPadding = Dp.m7399constructorimpl(f);
        StrokeWidth = Dp.m7399constructorimpl(f);
        RadiusSize = Dp.m7399constructorimpl(f);
    }
}
