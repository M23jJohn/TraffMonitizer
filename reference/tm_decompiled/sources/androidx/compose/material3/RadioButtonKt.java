package androidx.compose.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.tokens.RadioButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: RadioButton.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aO\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007¢\u0006\u0002\u0010\u0014\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004¨\u0006\u0015"}, d2 = {"RadioAnimationDuration", "", "RadioButtonDotSize", "Landroidx/compose/ui/unit/Dp;", "F", "RadioButtonPadding", "RadioStrokeWidth", "RadioButton", "", "selected", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "colors", "Landroidx/compose/material3/RadioButtonColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/RadioButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RadioButtonKt {
    private static final int RadioAnimationDuration = 100;
    private static final float RadioButtonDotSize = Dp.m7399constructorimpl(12);
    private static final float RadioButtonPadding;
    private static final float RadioStrokeWidth;

    /* JADX WARN: Removed duplicated region for block: B:13:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0066  */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r11v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void RadioButton(final boolean z, final Function0<Unit> function0, Modifier modifier, boolean z2, RadioButtonColors radioButtonColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        boolean z3;
        RadioButtonColors radioButtonColors2;
        int i5;
        MutableInteractionSource mutableInteractionSource2;
        Modifier.Companion companion;
        MutableInteractionSource mutableInteractionSource3;
        int i6;
        boolean z4;
        RadioButtonColors radioButtonColors3;
        float m7399constructorimpl;
        ?? r11;
        Modifier modifier3;
        State<Dp> state;
        final boolean z5;
        final State<Color> state2;
        Modifier.Companion companion2;
        Modifier.Companion companion3;
        boolean changed;
        Object rememberedValue;
        final MutableInteractionSource mutableInteractionSource4;
        final Modifier modifier4;
        int i7;
        Composer startRestartGroup = composer.startRestartGroup(408580840);
        ComposerKt.sourceInformation(startRestartGroup, "C(RadioButton)P(5,4,3,1)80@3770L8,84@3868L176,88@4073L29,119@5097L415,106@4679L833:RadioButton.kt#uh7d8r");
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
            i3 |= startRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        int i8 = i2 & 4;
        if (i8 != 0) {
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
                        radioButtonColors2 = radioButtonColors;
                        if (startRestartGroup.changed(radioButtonColors2)) {
                            i7 = 16384;
                            i3 |= i7;
                        }
                    } else {
                        radioButtonColors2 = radioButtonColors;
                    }
                    i7 = 8192;
                    i3 |= i7;
                } else {
                    radioButtonColors2 = radioButtonColors;
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
                            companion = i8 == 0 ? Modifier.INSTANCE : modifier2;
                            if (i4 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                                radioButtonColors2 = RadioButtonDefaults.INSTANCE.colors(startRestartGroup, 6);
                            }
                            if (i5 == 0) {
                                i6 = i3;
                                z4 = z3;
                                radioButtonColors3 = radioButtonColors2;
                                mutableInteractionSource3 = null;
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(408580840, i6, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:82)");
                                }
                                if (!z) {
                                    m7399constructorimpl = Dp.m7399constructorimpl(RadioButtonDotSize / 2);
                                } else {
                                    m7399constructorimpl = Dp.m7399constructorimpl(0);
                                }
                                State<Dp> m178animateDpAsStateAjpBEmI = AnimateAsStateKt.m178animateDpAsStateAjpBEmI(m7399constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, startRestartGroup, 48, 12);
                                State<Color> radioColor$material3_release = radioButtonColors3.radioColor$material3_release(z4, z, startRestartGroup, ((i6 >> 6) & 896) | ((i6 >> 9) & 14) | ((i6 << 3) & 112));
                                startRestartGroup.startReplaceGroup(1327106656);
                                ComposerKt.sourceInformation(startRestartGroup, "98@4448L164");
                                if (function0 == null) {
                                    state = m178animateDpAsStateAjpBEmI;
                                    radioButtonColors2 = radioButtonColors3;
                                    r11 = 0;
                                    modifier3 = companion;
                                    z5 = z4;
                                    state2 = radioColor$material3_release;
                                    companion2 = SelectableKt.m1205selectableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, RippleKt.m2563rippleOrFallbackImplementation9IZ8Weo(false, Dp.m7399constructorimpl(RadioButtonTokens.INSTANCE.m3655getStateLayerSizeD9Ej5fM() / 2), 0L, startRestartGroup, 54, 4), z5, Role.m6589boximpl(Role.INSTANCE.m6601getRadioButtono7Vup1c()), function0);
                                } else {
                                    r11 = 0;
                                    modifier3 = companion;
                                    radioButtonColors2 = radioButtonColors3;
                                    state = m178animateDpAsStateAjpBEmI;
                                    z5 = z4;
                                    state2 = radioColor$material3_release;
                                    companion2 = Modifier.INSTANCE;
                                }
                                startRestartGroup.endReplaceGroup();
                                if (function0 == null) {
                                    companion3 = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                                } else {
                                    companion3 = Modifier.INSTANCE;
                                }
                                Modifier m922requiredSize3ABfNKs = SizeKt.m922requiredSize3ABfNKs(PaddingKt.m878padding3ABfNKs(SizeKt.wrapContentSize$default(modifier3.then(companion3).then(companion2), Alignment.INSTANCE.getCenter(), r11, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3654getIconSizeD9Ej5fM());
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                                final State<Dp> state3 = state;
                                changed = startRestartGroup.changed(state2) | startRestartGroup.changed(state3);
                                rememberedValue = startRestartGroup.rememberedValue();
                                if (!changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                    rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                            float f;
                                            f = RadioButtonKt.RadioStrokeWidth;
                                            float f2 = drawScope.mo459toPx0680j_4(f);
                                            float f3 = 2;
                                            float f4 = f2 / f3;
                                            DrawScope.CC.m5193drawCircleVaOC9Bg$default(drawScope, state2.getValue().m4625unboximpl(), drawScope.mo459toPx0680j_4(Dp.m7399constructorimpl(RadioButtonTokens.INSTANCE.m3654getIconSizeD9Ej5fM() / f3)) - f4, 0L, 0.0f, new Stroke(f2, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                            if (Dp.m7398compareTo0680j_4(state3.getValue().m7413unboximpl(), Dp.m7399constructorimpl(0)) > 0) {
                                                DrawScope.CC.m5193drawCircleVaOC9Bg$default(drawScope, state2.getValue().m4625unboximpl(), drawScope.mo459toPx0680j_4(state3.getValue().m7413unboximpl()) - f4, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                            }
                                        }
                                    };
                                    startRestartGroup.updateRememberedValue(rememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                CanvasKt.Canvas(m922requiredSize3ABfNKs, (Function1) rememberedValue, startRestartGroup, r11);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                mutableInteractionSource4 = mutableInteractionSource3;
                                modifier4 = modifier3;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                                i6 = i3;
                            }
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            if ((i2 & 16) != 0) {
                                i3 &= -57345;
                            }
                            mutableInteractionSource3 = mutableInteractionSource;
                            i6 = i3;
                            companion = modifier2;
                        }
                        z4 = z3;
                        radioButtonColors3 = radioButtonColors2;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        if (!z) {
                        }
                        State<Dp> m178animateDpAsStateAjpBEmI2 = AnimateAsStateKt.m178animateDpAsStateAjpBEmI(m7399constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, startRestartGroup, 48, 12);
                        State<Color> radioColor$material3_release2 = radioButtonColors3.radioColor$material3_release(z4, z, startRestartGroup, ((i6 >> 6) & 896) | ((i6 >> 9) & 14) | ((i6 << 3) & 112));
                        startRestartGroup.startReplaceGroup(1327106656);
                        ComposerKt.sourceInformation(startRestartGroup, "98@4448L164");
                        if (function0 == null) {
                        }
                        startRestartGroup.endReplaceGroup();
                        if (function0 == null) {
                        }
                        Modifier m922requiredSize3ABfNKs2 = SizeKt.m922requiredSize3ABfNKs(PaddingKt.m878padding3ABfNKs(SizeKt.wrapContentSize$default(modifier3.then(companion3).then(companion2), Alignment.INSTANCE.getCenter(), r11, 2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m3654getIconSizeD9Ej5fM());
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1327137161, "CC(remember):RadioButton.kt#9igjgp");
                        final State<Dp> state32 = state;
                        changed = startRestartGroup.changed(state2) | startRestartGroup.changed(state32);
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (!changed) {
                        }
                        rememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$1$1
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
                                float f;
                                f = RadioButtonKt.RadioStrokeWidth;
                                float f2 = drawScope.mo459toPx0680j_4(f);
                                float f3 = 2;
                                float f4 = f2 / f3;
                                DrawScope.CC.m5193drawCircleVaOC9Bg$default(drawScope, state2.getValue().m4625unboximpl(), drawScope.mo459toPx0680j_4(Dp.m7399constructorimpl(RadioButtonTokens.INSTANCE.m3654getIconSizeD9Ej5fM() / f3)) - f4, 0L, 0.0f, new Stroke(f2, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                                if (Dp.m7398compareTo0680j_4(state32.getValue().m7413unboximpl(), Dp.m7399constructorimpl(0)) > 0) {
                                    DrawScope.CC.m5193drawCircleVaOC9Bg$default(drawScope, state2.getValue().m4625unboximpl(), drawScope.mo459toPx0680j_4(state32.getValue().m7413unboximpl()) - f4, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                                }
                            }
                        };
                        startRestartGroup.updateRememberedValue(rememberedValue);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        CanvasKt.Canvas(m922requiredSize3ABfNKs2, (Function1) rememberedValue, startRestartGroup, r11);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        mutableInteractionSource4 = mutableInteractionSource3;
                        modifier4 = modifier3;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        modifier4 = modifier2;
                        z5 = z3;
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    final RadioButtonColors radioButtonColors4 = radioButtonColors2;
                    ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup != null) {
                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                invoke(composer2, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i9) {
                                RadioButtonKt.RadioButton(z, function0, modifier4, z5, radioButtonColors4, mutableInteractionSource4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
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
                if (i8 == 0) {
                }
                if (i4 != 0) {
                }
                if ((i2 & 16) != 0) {
                }
                if (i5 == 0) {
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
            if ((i & 1) != 0) {
            }
            if (i8 == 0) {
            }
            if (i4 != 0) {
            }
            if ((i2 & 16) != 0) {
            }
            if (i5 == 0) {
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
        if ((i & 1) != 0) {
        }
        if (i8 == 0) {
        }
        if (i4 != 0) {
        }
        if ((i2 & 16) != 0) {
        }
        if (i5 == 0) {
        }
    }

    static {
        float f = 2;
        RadioButtonPadding = Dp.m7399constructorimpl(f);
        RadioStrokeWidth = Dp.m7399constructorimpl(f);
    }
}
