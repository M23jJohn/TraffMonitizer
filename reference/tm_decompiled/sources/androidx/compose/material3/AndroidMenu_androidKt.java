package androidx.compose.material3;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.internal.DropdownMenuPositionProvider;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AndroidMenu.android.kt */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001an\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a¢\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001ad\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a\u0090\u0001\u0010%\u001a\u00020\u00052\u0011\u0010&\u001a\r\u0012\u0004\u0012\u00020\u00050\t¢\u0006\u0002\b\u00142\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0015\b\u0002\u0010(\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010)\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t¢\u0006\u0002\b\u00142\b\b\u0002\u0010*\u001a\u00020\u00072\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.2\n\b\u0002\u0010/\u001a\u0004\u0018\u000100H\u0007¢\u0006\u0002\u00101\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00062"}, d2 = {"DefaultMenuProperties", "Landroidx/compose/ui/window/PopupProperties;", "getDefaultMenuProperties", "()Landroidx/compose/ui/window/PopupProperties;", "DropdownMenu", "", "expanded", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "offset", "Landroidx/compose/ui/unit/DpOffset;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "properties", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "DropdownMenu-4kj-_NE", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/ScrollState;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "shadowElevation", "border", "Landroidx/compose/foundation/BorderStroke;", "DropdownMenu-IlH_yew", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/ScrollState;Landroidx/compose/ui/window/PopupProperties;Landroidx/compose/ui/graphics/Shape;JFFLandroidx/compose/foundation/BorderStroke;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "DropdownMenu-ILWXrKs", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenuItem", "text", "onClick", "leadingIcon", "trailingIcon", "enabled", "colors", "Landroidx/compose/material3/MenuItemColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/MenuItemColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidMenu_androidKt {
    private static final PopupProperties DefaultMenuProperties = new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null);

    /* JADX WARN: Removed duplicated region for block: B:100:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x038a  */
    /* renamed from: DropdownMenu-IlH_yew, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m1919DropdownMenuIlH_yew(final boolean z, final Function0<Unit> function0, Modifier modifier, long j, ScrollState scrollState, PopupProperties popupProperties, Shape shape, long j2, float f, float f2, BorderStroke borderStroke, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        Function0<Unit> function02;
        int i5;
        Modifier modifier2;
        int i6;
        long j3;
        int i7;
        PopupProperties popupProperties2;
        Shape shape2;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        ScrollState scrollState2;
        Shape shape3;
        long j4;
        final BorderStroke borderStroke2;
        final Modifier modifier3;
        final float f3;
        final Shape shape4;
        final ScrollState scrollState3;
        final long j5;
        final float f4;
        Object rememberedValue;
        boolean z2;
        final MutableTransitionState mutableTransitionState;
        Object rememberedValue2;
        boolean changed;
        Object rememberedValue3;
        Composer composer2;
        final long j6;
        final Modifier modifier4;
        final ScrollState scrollState4;
        final Shape shape5;
        final long j7;
        final float f5;
        final float f6;
        final BorderStroke borderStroke3;
        ScopeUpdateScope endRestartGroup;
        int i14;
        Composer startRestartGroup = composer.startRestartGroup(1431928300);
        ComposerKt.sourceInformation(startRestartGroup, "C(DropdownMenu)P(3,6,4,5:c#ui.unit.DpOffset,8,7,10,1:c#ui.graphics.Color,11:c#ui.unit.Dp,9:c#ui.unit.Dp)182@6555L21,185@6667L5,187@6715L14,55@2073L42,59@2260L51,60@2347L7,62@2403L251,72@2825L494,68@2664L655:AndroidMenu.android.kt#uh7d8r");
        if ((i3 & 1) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = (startRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        if ((i3 & 2) != 0) {
            i4 |= 48;
        } else if ((i & 48) == 0) {
            function02 = function0;
            i4 |= startRestartGroup.changedInstance(function02) ? 32 : 16;
            i5 = i3 & 4;
            if (i5 == 0) {
                i4 |= 384;
            } else if ((i & 384) == 0) {
                modifier2 = modifier;
                i4 |= startRestartGroup.changed(modifier2) ? 256 : 128;
                i6 = i3 & 8;
                if (i6 != 0) {
                    i4 |= 3072;
                    j3 = j;
                } else {
                    j3 = j;
                    if ((i & 3072) == 0) {
                        i4 |= startRestartGroup.changed(j3) ? 2048 : 1024;
                    }
                }
                if ((i & 24576) == 0) {
                    if ((i3 & 16) == 0 && startRestartGroup.changed(scrollState)) {
                        i14 = 16384;
                        i4 |= i14;
                    }
                    i14 = 8192;
                    i4 |= i14;
                }
                i7 = i3 & 32;
                if (i7 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    popupProperties2 = popupProperties;
                } else {
                    popupProperties2 = popupProperties;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        i4 |= startRestartGroup.changed(popupProperties2) ? 131072 : 65536;
                    }
                }
                if ((i & 1572864) == 0) {
                    shape2 = shape;
                    i4 |= ((i3 & 64) == 0 && startRestartGroup.changed(shape2)) ? 1048576 : 524288;
                } else {
                    shape2 = shape;
                }
                if ((i & 12582912) == 0) {
                    i4 |= ((i3 & 128) == 0 && startRestartGroup.changed(j2)) ? 8388608 : 4194304;
                }
                i8 = i3 & 256;
                if (i8 != 0) {
                    i4 |= 100663296;
                } else if ((i & 100663296) == 0) {
                    i4 |= startRestartGroup.changed(f) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                }
                i9 = i3 & 512;
                if (i9 != 0) {
                    i4 |= 805306368;
                } else if ((i & 805306368) == 0) {
                    i10 = i9;
                    i4 |= startRestartGroup.changed(f2) ? 536870912 : 268435456;
                    i11 = i3 & 1024;
                    if (i11 == 0) {
                        i13 = i2 | 6;
                        i12 = i11;
                    } else if ((i2 & 6) == 0) {
                        i12 = i11;
                        i13 = i2 | (startRestartGroup.changed(borderStroke) ? 4 : 2);
                    } else {
                        i12 = i11;
                        i13 = i2;
                    }
                    if ((i3 & 2048) == 0) {
                        i13 |= 48;
                    } else if ((i2 & 48) == 0) {
                        i13 |= startRestartGroup.changedInstance(function3) ? 32 : 16;
                    }
                    int i15 = i13;
                    if ((i4 & 306783379) == 306783378 || (i15 & 19) != 18 || !startRestartGroup.getSkipping()) {
                        startRestartGroup.startDefaults();
                        if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                            Modifier.Companion companion = i5 == 0 ? Modifier.INSTANCE : modifier2;
                            if (i6 != 0) {
                                float f7 = 0;
                                j3 = DpKt.m7420DpOffsetYgX7TsA(Dp.m7399constructorimpl(f7), Dp.m7399constructorimpl(f7));
                            }
                            if ((i3 & 16) == 0) {
                                scrollState2 = ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1);
                                i4 &= -57345;
                            } else {
                                scrollState2 = scrollState;
                            }
                            if (i7 != 0) {
                                popupProperties2 = DefaultMenuProperties;
                            }
                            if ((i3 & 64) == 0) {
                                shape3 = MenuDefaults.INSTANCE.getShape(startRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                shape3 = shape2;
                            }
                            if ((i3 & 128) == 0) {
                                j4 = MenuDefaults.INSTANCE.getContainerColor(startRestartGroup, 6);
                                i4 &= -29360129;
                            } else {
                                j4 = j2;
                            }
                            float m2384getTonalElevationD9Ej5fM = i8 == 0 ? MenuDefaults.INSTANCE.m2384getTonalElevationD9Ej5fM() : f;
                            float m2383getShadowElevationD9Ej5fM = i10 == 0 ? MenuDefaults.INSTANCE.m2383getShadowElevationD9Ej5fM() : f2;
                            if (i12 == 0) {
                                modifier3 = companion;
                                f3 = m2384getTonalElevationD9Ej5fM;
                                shape4 = shape3;
                                scrollState3 = scrollState2;
                                j5 = j4;
                                f4 = m2383getShadowElevationD9Ej5fM;
                                borderStroke2 = null;
                            } else {
                                borderStroke2 = borderStroke;
                                modifier3 = companion;
                                f3 = m2384getTonalElevationD9Ej5fM;
                                shape4 = shape3;
                                scrollState3 = scrollState2;
                                j5 = j4;
                                f4 = m2383getShadowElevationD9Ej5fM;
                            }
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            if ((i3 & 16) != 0) {
                                i4 &= -57345;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                            }
                            scrollState3 = scrollState;
                            j5 = j2;
                            f3 = f;
                            f4 = f2;
                            borderStroke2 = borderStroke;
                            modifier3 = modifier2;
                            shape4 = shape2;
                        }
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1431928300, i4, i15, "androidx.compose.material3.DropdownMenu (AndroidMenu.android.kt:54)");
                        }
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1468213501, "CC(remember):AndroidMenu.android.kt#9igjgp");
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (rememberedValue != Composer.INSTANCE.getEmpty()) {
                            z2 = false;
                            rememberedValue = new MutableTransitionState(false);
                            startRestartGroup.updateRememberedValue(rememberedValue);
                        } else {
                            z2 = false;
                        }
                        mutableTransitionState = (MutableTransitionState) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        mutableTransitionState.setTargetState$animation_core(Boolean.valueOf(z));
                        if (!((Boolean) mutableTransitionState.getCurrentState()).booleanValue() || ((Boolean) mutableTransitionState.getTargetState()).booleanValue()) {
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1468219494, "CC(remember):AndroidMenu.android.kt#9igjgp");
                            rememberedValue2 = startRestartGroup.rememberedValue();
                            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                rememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m5020boximpl(TransformOrigin.INSTANCE.m5033getCenterSzJe1aQ()), null, 2, null);
                                startRestartGroup.updateRememberedValue(rememberedValue2);
                            }
                            final MutableState mutableState = (MutableState) rememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object consume = startRestartGroup.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            Density density = (Density) consume;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1468224270, "CC(remember):AndroidMenu.android.kt#9igjgp");
                            if ((i4 & 7168) == 2048) {
                                z2 = true;
                            }
                            changed = z2 | startRestartGroup.changed(density);
                            rememberedValue3 = startRestartGroup.rememberedValue();
                            if (!changed || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                rememberedValue3 = new DropdownMenuPositionProvider(j3, density, 0, new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(IntRect intRect, IntRect intRect2) {
                                        invoke2(intRect, intRect2);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(IntRect intRect, IntRect intRect2) {
                                        mutableState.setValue(TransformOrigin.m5020boximpl(MenuKt.calculateTransformOrigin(intRect, intRect2)));
                                    }
                                }, 4, null);
                                startRestartGroup.updateRememberedValue(rememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            AndroidPopup_androidKt.Popup((DropdownMenuPositionProvider) rememberedValue3, function02, popupProperties2, ComposableLambdaKt.rememberComposableLambda(2126968933, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i16) {
                                    ComposerKt.sourceInformation(composer3, "C73@2839L470:AndroidMenu.android.kt#uh7d8r");
                                    if ((i16 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2126968933, i16, -1, "androidx.compose.material3.DropdownMenu.<anonymous> (AndroidMenu.android.kt:73)");
                                        }
                                        MenuKt.m2397DropdownMenuContentQj0Zi0g(Modifier.this, mutableTransitionState, mutableState, scrollState3, shape4, j5, f3, f4, borderStroke2, function3, composer3, (MutableTransitionState.$stable << 3) | 384);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }, startRestartGroup, 54), startRestartGroup, (i4 & 112) | 3072 | ((i4 >> 9) & 896), 0);
                            composer2 = startRestartGroup;
                        } else {
                            composer2 = startRestartGroup;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j6 = j3;
                        modifier4 = modifier3;
                        scrollState4 = scrollState3;
                        shape5 = shape4;
                        j7 = j5;
                        f5 = f3;
                        f6 = f4;
                        borderStroke3 = borderStroke2;
                    } else {
                        startRestartGroup.skipToGroupEnd();
                        scrollState4 = scrollState;
                        f6 = f2;
                        composer2 = startRestartGroup;
                        j6 = j3;
                        modifier4 = modifier2;
                        shape5 = shape2;
                        j7 = j2;
                        f5 = f;
                        borderStroke3 = borderStroke;
                    }
                    final PopupProperties popupProperties3 = popupProperties2;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i16) {
                                AndroidMenu_androidKt.m1919DropdownMenuIlH_yew(z, function0, modifier4, j6, scrollState4, popupProperties3, shape5, j7, f5, f6, borderStroke3, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
                            }
                        });
                        return;
                    }
                    return;
                }
                i10 = i9;
                i11 = i3 & 1024;
                if (i11 == 0) {
                }
                if ((i3 & 2048) == 0) {
                }
                int i152 = i13;
                if ((i4 & 306783379) == 306783378) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i5 == 0) {
                }
                if (i6 != 0) {
                }
                if ((i3 & 16) == 0) {
                }
                if (i7 != 0) {
                }
                if ((i3 & 64) == 0) {
                }
                if ((i3 & 128) == 0) {
                }
                if (i8 == 0) {
                }
                if (i10 == 0) {
                }
                if (i12 == 0) {
                }
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1468213501, "CC(remember):AndroidMenu.android.kt#9igjgp");
                rememberedValue = startRestartGroup.rememberedValue();
                if (rememberedValue != Composer.INSTANCE.getEmpty()) {
                }
                mutableTransitionState = (MutableTransitionState) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                mutableTransitionState.setTargetState$animation_core(Boolean.valueOf(z));
                if (((Boolean) mutableTransitionState.getCurrentState()).booleanValue()) {
                }
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1468219494, "CC(remember):AndroidMenu.android.kt#9igjgp");
                rememberedValue2 = startRestartGroup.rememberedValue();
                if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                }
                final MutableState<TransformOrigin> mutableState2 = (MutableState) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume2 = startRestartGroup.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Density density2 = (Density) consume2;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1468224270, "CC(remember):AndroidMenu.android.kt#9igjgp");
                if ((i4 & 7168) == 2048) {
                }
                changed = z2 | startRestartGroup.changed(density2);
                rememberedValue3 = startRestartGroup.rememberedValue();
                if (!changed) {
                }
                rememberedValue3 = new DropdownMenuPositionProvider(j3, density2, 0, new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IntRect intRect, IntRect intRect2) {
                        invoke2(intRect, intRect2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IntRect intRect, IntRect intRect2) {
                        mutableState2.setValue(TransformOrigin.m5020boximpl(MenuKt.calculateTransformOrigin(intRect, intRect2)));
                    }
                }, 4, null);
                startRestartGroup.updateRememberedValue(rememberedValue3);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                AndroidPopup_androidKt.Popup((DropdownMenuPositionProvider) rememberedValue3, function02, popupProperties2, ComposableLambdaKt.rememberComposableLambda(2126968933, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i16) {
                        ComposerKt.sourceInformation(composer3, "C73@2839L470:AndroidMenu.android.kt#uh7d8r");
                        if ((i16 & 3) != 2 || !composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2126968933, i16, -1, "androidx.compose.material3.DropdownMenu.<anonymous> (AndroidMenu.android.kt:73)");
                            }
                            MenuKt.m2397DropdownMenuContentQj0Zi0g(Modifier.this, mutableTransitionState, mutableState2, scrollState3, shape4, j5, f3, f4, borderStroke2, function3, composer3, (MutableTransitionState.$stable << 3) | 384);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer3.skipToGroupEnd();
                    }
                }, startRestartGroup, 54), startRestartGroup, (i4 & 112) | 3072 | ((i4 >> 9) & 896), 0);
                composer2 = startRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                }
                j6 = j3;
                modifier4 = modifier3;
                scrollState4 = scrollState3;
                shape5 = shape4;
                j7 = j5;
                f5 = f3;
                f6 = f4;
                borderStroke3 = borderStroke2;
                final PopupProperties popupProperties32 = popupProperties2;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            modifier2 = modifier;
            i6 = i3 & 8;
            if (i6 != 0) {
            }
            if ((i & 24576) == 0) {
            }
            i7 = i3 & 32;
            if (i7 != 0) {
            }
            if ((i & 1572864) == 0) {
            }
            if ((i & 12582912) == 0) {
            }
            i8 = i3 & 256;
            if (i8 != 0) {
            }
            i9 = i3 & 512;
            if (i9 != 0) {
            }
            i10 = i9;
            i11 = i3 & 1024;
            if (i11 == 0) {
            }
            if ((i3 & 2048) == 0) {
            }
            int i1522 = i13;
            if ((i4 & 306783379) == 306783378) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i5 == 0) {
            }
            if (i6 != 0) {
            }
            if ((i3 & 16) == 0) {
            }
            if (i7 != 0) {
            }
            if ((i3 & 64) == 0) {
            }
            if ((i3 & 128) == 0) {
            }
            if (i8 == 0) {
            }
            if (i10 == 0) {
            }
            if (i12 == 0) {
            }
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1468213501, "CC(remember):AndroidMenu.android.kt#9igjgp");
            rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue != Composer.INSTANCE.getEmpty()) {
            }
            mutableTransitionState = (MutableTransitionState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            mutableTransitionState.setTargetState$animation_core(Boolean.valueOf(z));
            if (((Boolean) mutableTransitionState.getCurrentState()).booleanValue()) {
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1468219494, "CC(remember):AndroidMenu.android.kt#9igjgp");
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            }
            final MutableState<TransformOrigin> mutableState22 = (MutableState) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ProvidableCompositionLocal<Density> localDensity22 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume22 = startRestartGroup.consume(localDensity22);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Density density22 = (Density) consume22;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1468224270, "CC(remember):AndroidMenu.android.kt#9igjgp");
            if ((i4 & 7168) == 2048) {
            }
            changed = z2 | startRestartGroup.changed(density22);
            rememberedValue3 = startRestartGroup.rememberedValue();
            if (!changed) {
            }
            rememberedValue3 = new DropdownMenuPositionProvider(j3, density22, 0, new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IntRect intRect, IntRect intRect2) {
                    invoke2(intRect, intRect2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IntRect intRect, IntRect intRect2) {
                    mutableState22.setValue(TransformOrigin.m5020boximpl(MenuKt.calculateTransformOrigin(intRect, intRect2)));
                }
            }, 4, null);
            startRestartGroup.updateRememberedValue(rememberedValue3);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            AndroidPopup_androidKt.Popup((DropdownMenuPositionProvider) rememberedValue3, function02, popupProperties2, ComposableLambdaKt.rememberComposableLambda(2126968933, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i16) {
                    ComposerKt.sourceInformation(composer3, "C73@2839L470:AndroidMenu.android.kt#uh7d8r");
                    if ((i16 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2126968933, i16, -1, "androidx.compose.material3.DropdownMenu.<anonymous> (AndroidMenu.android.kt:73)");
                        }
                        MenuKt.m2397DropdownMenuContentQj0Zi0g(Modifier.this, mutableTransitionState, mutableState22, scrollState3, shape4, j5, f3, f4, borderStroke2, function3, composer3, (MutableTransitionState.$stable << 3) | 384);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, startRestartGroup, 54), startRestartGroup, (i4 & 112) | 3072 | ((i4 >> 9) & 896), 0);
            composer2 = startRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
            }
            j6 = j3;
            modifier4 = modifier3;
            scrollState4 = scrollState3;
            shape5 = shape4;
            j7 = j5;
            f5 = f3;
            f6 = f4;
            borderStroke3 = borderStroke2;
            final PopupProperties popupProperties322 = popupProperties2;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        function02 = function0;
        i5 = i3 & 4;
        if (i5 == 0) {
        }
        modifier2 = modifier;
        i6 = i3 & 8;
        if (i6 != 0) {
        }
        if ((i & 24576) == 0) {
        }
        i7 = i3 & 32;
        if (i7 != 0) {
        }
        if ((i & 1572864) == 0) {
        }
        if ((i & 12582912) == 0) {
        }
        i8 = i3 & 256;
        if (i8 != 0) {
        }
        i9 = i3 & 512;
        if (i9 != 0) {
        }
        i10 = i9;
        i11 = i3 & 1024;
        if (i11 == 0) {
        }
        if ((i3 & 2048) == 0) {
        }
        int i15222 = i13;
        if ((i4 & 306783379) == 306783378) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i5 == 0) {
        }
        if (i6 != 0) {
        }
        if ((i3 & 16) == 0) {
        }
        if (i7 != 0) {
        }
        if ((i3 & 64) == 0) {
        }
        if ((i3 & 128) == 0) {
        }
        if (i8 == 0) {
        }
        if (i10 == 0) {
        }
        if (i12 == 0) {
        }
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1468213501, "CC(remember):AndroidMenu.android.kt#9igjgp");
        rememberedValue = startRestartGroup.rememberedValue();
        if (rememberedValue != Composer.INSTANCE.getEmpty()) {
        }
        mutableTransitionState = (MutableTransitionState) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        mutableTransitionState.setTargetState$animation_core(Boolean.valueOf(z));
        if (((Boolean) mutableTransitionState.getCurrentState()).booleanValue()) {
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1468219494, "CC(remember):AndroidMenu.android.kt#9igjgp");
        rememberedValue2 = startRestartGroup.rememberedValue();
        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
        }
        final MutableState<TransformOrigin> mutableState222 = (MutableState) rememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        ProvidableCompositionLocal<Density> localDensity222 = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume222 = startRestartGroup.consume(localDensity222);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Density density222 = (Density) consume222;
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1468224270, "CC(remember):AndroidMenu.android.kt#9igjgp");
        if ((i4 & 7168) == 2048) {
        }
        changed = z2 | startRestartGroup.changed(density222);
        rememberedValue3 = startRestartGroup.rememberedValue();
        if (!changed) {
        }
        rememberedValue3 = new DropdownMenuPositionProvider(j3, density222, 0, new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IntRect intRect, IntRect intRect2) {
                invoke2(intRect, intRect2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IntRect intRect, IntRect intRect2) {
                mutableState222.setValue(TransformOrigin.m5020boximpl(MenuKt.calculateTransformOrigin(intRect, intRect2)));
            }
        }, 4, null);
        startRestartGroup.updateRememberedValue(rememberedValue3);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        AndroidPopup_androidKt.Popup((DropdownMenuPositionProvider) rememberedValue3, function02, popupProperties2, ComposableLambdaKt.rememberComposableLambda(2126968933, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                invoke(composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer3, int i16) {
                ComposerKt.sourceInformation(composer3, "C73@2839L470:AndroidMenu.android.kt#uh7d8r");
                if ((i16 & 3) != 2 || !composer3.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(2126968933, i16, -1, "androidx.compose.material3.DropdownMenu.<anonymous> (AndroidMenu.android.kt:73)");
                    }
                    MenuKt.m2397DropdownMenuContentQj0Zi0g(Modifier.this, mutableTransitionState, mutableState222, scrollState3, shape4, j5, f3, f4, borderStroke2, function3, composer3, (MutableTransitionState.$stable << 3) | 384);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer3.skipToGroupEnd();
            }
        }, startRestartGroup, 54), startRestartGroup, (i4 & 112) | 3072 | ((i4 >> 9) & 896), 0);
        composer2 = startRestartGroup;
        if (ComposerKt.isTraceInProgress()) {
        }
        j6 = j3;
        modifier4 = modifier3;
        scrollState4 = scrollState3;
        shape5 = shape4;
        j7 = j5;
        f5 = f3;
        f6 = f4;
        borderStroke3 = borderStroke2;
        final PopupProperties popupProperties3222 = popupProperties2;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0069  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with parameters for shape, color, elevation, and border.", replaceWith = @ReplaceWith(expression = "DropdownMenu(\n    expanded = expanded,\n    onDismissRequest = onDismissRequest,\n    modifier = modifier,\n    offset = offset,\n    scrollState = scrollState,\n    properties = properties,\n    shape = MenuDefaults.shape,\n    containerColor = MenuDefaults.containerColor,\n    tonalElevation = MenuDefaults.TonalElevation,\n    shadowElevation = MenuDefaults.ShadowElevation,\n    border = null,\n    content = content,\n)", imports = {}))
    /* renamed from: DropdownMenu-4kj-_NE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m1917DropdownMenu4kj_NE(final boolean z, final Function0 function0, Modifier modifier, long j, ScrollState scrollState, PopupProperties popupProperties, final Function3 function3, Composer composer, final int i, final int i2) {
        boolean z2;
        int i3;
        Function0 function02;
        Modifier modifier2;
        int i4;
        long j2;
        ScrollState scrollState2;
        int i5;
        PopupProperties popupProperties2;
        Modifier modifier3;
        long j3;
        ScrollState scrollState3;
        PopupProperties popupProperties3;
        int i6;
        Composer composer2;
        final Modifier modifier4;
        final long j4;
        final ScrollState scrollState4;
        final PopupProperties popupProperties4;
        int i7;
        Composer startRestartGroup = composer.startRestartGroup(-1137929566);
        ComposerKt.sourceInformation(startRestartGroup, "C(DropdownMenu)P(1,4,2,3:c#ui.unit.DpOffset,6,5)119@4573L21,130@4960L5,131@5005L14,123@4721L465:AndroidMenu.android.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            z2 = z;
        } else {
            z2 = z;
            if ((i & 6) == 0) {
                i3 = (startRestartGroup.changed(z2) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
            function02 = function0;
        } else {
            function02 = function0;
            if ((i & 48) == 0) {
                i3 |= startRestartGroup.changedInstance(function02) ? 32 : 16;
            }
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
                j2 = j;
                i3 |= startRestartGroup.changed(j2) ? 2048 : 1024;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        scrollState2 = scrollState;
                        if (startRestartGroup.changed(scrollState2)) {
                            i7 = 16384;
                            i3 |= i7;
                        }
                    } else {
                        scrollState2 = scrollState;
                    }
                    i7 = 8192;
                    i3 |= i7;
                } else {
                    scrollState2 = scrollState;
                }
                i5 = i2 & 32;
                if (i5 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i) == 0) {
                    popupProperties2 = popupProperties;
                    i3 |= startRestartGroup.changed(popupProperties2) ? 131072 : 65536;
                    if ((i2 & 64) == 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        i3 |= startRestartGroup.changedInstance(function3) ? 1048576 : 524288;
                        if ((599187 & i3) == 599186 || !startRestartGroup.getSkipping()) {
                            startRestartGroup.startDefaults();
                            if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                Modifier.Companion companion = i8 != 0 ? Modifier.INSTANCE : modifier2;
                                if (i4 != 0) {
                                    float f = 0;
                                    j2 = DpKt.m7420DpOffsetYgX7TsA(Dp.m7399constructorimpl(f), Dp.m7399constructorimpl(f));
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                    scrollState2 = ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1);
                                }
                                if (i5 != 0) {
                                    modifier3 = companion;
                                    j3 = j2;
                                    scrollState3 = scrollState2;
                                    popupProperties3 = new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null);
                                    i6 = -1137929566;
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(i6, i3, -1, "androidx.compose.material3.DropdownMenu (AndroidMenu.android.kt:123)");
                                    }
                                    composer2 = startRestartGroup;
                                    m1919DropdownMenuIlH_yew(z2, function02, modifier3, j3, scrollState3, popupProperties3, MenuDefaults.INSTANCE.getShape(startRestartGroup, 6), MenuDefaults.INSTANCE.getContainerColor(startRestartGroup, 6), MenuDefaults.INSTANCE.m2384getTonalElevationD9Ej5fM(), MenuDefaults.INSTANCE.m2383getShadowElevationD9Ej5fM(), null, function3, composer2, (i3 & 14) | 905969664 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3), ((i3 >> 15) & 112) | 6, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    modifier4 = modifier3;
                                    j4 = j3;
                                    scrollState4 = scrollState3;
                                    popupProperties4 = popupProperties3;
                                } else {
                                    modifier3 = companion;
                                }
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                                modifier3 = modifier2;
                            }
                            scrollState3 = scrollState2;
                            popupProperties3 = popupProperties2;
                            i6 = -1137929566;
                            j3 = j2;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer2 = startRestartGroup;
                            m1919DropdownMenuIlH_yew(z2, function02, modifier3, j3, scrollState3, popupProperties3, MenuDefaults.INSTANCE.getShape(startRestartGroup, 6), MenuDefaults.INSTANCE.getContainerColor(startRestartGroup, 6), MenuDefaults.INSTANCE.m2384getTonalElevationD9Ej5fM(), MenuDefaults.INSTANCE.m2383getShadowElevationD9Ej5fM(), null, function3, composer2, (i3 & 14) | 905969664 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3), ((i3 >> 15) & 112) | 6, 0);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            modifier4 = modifier3;
                            j4 = j3;
                            scrollState4 = scrollState3;
                            popupProperties4 = popupProperties3;
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            composer2 = startRestartGroup;
                            modifier4 = modifier2;
                            j4 = j2;
                            scrollState4 = scrollState2;
                            popupProperties4 = popupProperties2;
                        }
                        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i9) {
                                    AndroidMenu_androidKt.m1917DropdownMenu4kj_NE(z, function0, modifier4, j4, scrollState4, popupProperties4, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if ((599187 & i3) == 599186) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                    }
                    if (i8 != 0) {
                    }
                    if (i4 != 0) {
                    }
                    if ((i2 & 16) != 0) {
                    }
                    if (i5 != 0) {
                    }
                }
                popupProperties2 = popupProperties;
                if ((i2 & 64) == 0) {
                }
                if ((599187 & i3) == 599186) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i8 != 0) {
                }
                if (i4 != 0) {
                }
                if ((i2 & 16) != 0) {
                }
                if (i5 != 0) {
                }
            }
            j2 = j;
            if ((i & 24576) == 0) {
            }
            i5 = i2 & 32;
            if (i5 != 0) {
            }
            popupProperties2 = popupProperties;
            if ((i2 & 64) == 0) {
            }
            if ((599187 & i3) == 599186) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i8 != 0) {
            }
            if (i4 != 0) {
            }
            if ((i2 & 16) != 0) {
            }
            if (i5 != 0) {
            }
        }
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        j2 = j;
        if ((i & 24576) == 0) {
        }
        i5 = i2 & 32;
        if (i5 != 0) {
        }
        popupProperties2 = popupProperties;
        if ((i2 & 64) == 0) {
        }
        if ((599187 & i3) == 599186) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i8 != 0) {
        }
        if (i4 != 0) {
        }
        if ((i2 & 16) != 0) {
        }
        if (i5 != 0) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0069  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced by a DropdownMenu function with a ScrollState parameter", replaceWith = @ReplaceWith(expression = "DropdownMenu(expanded,onDismissRequest, modifier, offset, rememberScrollState(), properties, content)", imports = {"androidx.compose.foundation.rememberScrollState"}))
    /* renamed from: DropdownMenu-ILWXrKs, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m1918DropdownMenuILWXrKs(final boolean z, final Function0 function0, Modifier modifier, long j, PopupProperties popupProperties, final Function3 function3, Composer composer, final int i, final int i2) {
        boolean z2;
        int i3;
        Function0 function02;
        Modifier modifier2;
        int i4;
        long j2;
        int i5;
        PopupProperties popupProperties2;
        Function3 function32;
        Composer composer2;
        final Modifier modifier3;
        final long j3;
        final PopupProperties popupProperties3;
        ScopeUpdateScope endRestartGroup;
        Composer startRestartGroup = composer.startRestartGroup(354826666);
        ComposerKt.sourceInformation(startRestartGroup, "C(DropdownMenu)P(1,4,2,3:c#ui.unit.DpOffset,5)163@6060L21,158@5896L251:AndroidMenu.android.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            z2 = z;
        } else {
            z2 = z;
            if ((i & 6) == 0) {
                i3 = (startRestartGroup.changed(z2) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
            function02 = function0;
        } else {
            function02 = function0;
            if ((i & 48) == 0) {
                i3 |= startRestartGroup.changedInstance(function02) ? 32 : 16;
            }
        }
        int i6 = i2 & 4;
        if (i6 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 256 : 128;
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                j2 = j;
                i3 |= startRestartGroup.changed(j2) ? 2048 : 1024;
                i5 = i2 & 16;
                if (i5 != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    popupProperties2 = popupProperties;
                    i3 |= startRestartGroup.changed(popupProperties2) ? 16384 : 8192;
                    if ((i2 & 32) == 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        function32 = function3;
                        i3 |= startRestartGroup.changedInstance(function32) ? 131072 : 65536;
                        if ((74899 & i3) == 74898 || !startRestartGroup.getSkipping()) {
                            Modifier.Companion companion = i6 != 0 ? Modifier.INSTANCE : modifier2;
                            if (i4 != 0) {
                                float f = 0;
                                j2 = DpKt.m7420DpOffsetYgX7TsA(Dp.m7399constructorimpl(f), Dp.m7399constructorimpl(f));
                            }
                            PopupProperties popupProperties4 = i5 != 0 ? new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null) : popupProperties2;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(354826666, i3, -1, "androidx.compose.material3.DropdownMenu (AndroidMenu.android.kt:158)");
                            }
                            composer2 = startRestartGroup;
                            Modifier modifier4 = companion;
                            long j4 = j2;
                            m1919DropdownMenuIlH_yew(z2, function02, modifier4, j4, ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), popupProperties4, null, 0L, 0.0f, 0.0f, null, function32, composer2, (i3 & 8190) | ((i3 << 3) & 458752), (i3 >> 12) & 112, 1984);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            j3 = j4;
                            popupProperties3 = popupProperties4;
                        } else {
                            startRestartGroup.skipToGroupEnd();
                            composer2 = startRestartGroup;
                            modifier3 = modifier2;
                            j3 = j2;
                            popupProperties3 = popupProperties2;
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$4
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i7) {
                                    AndroidMenu_androidKt.m1918DropdownMenuILWXrKs(z, function0, modifier3, j3, popupProperties3, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    function32 = function3;
                    if ((74899 & i3) == 74898) {
                    }
                    if (i6 != 0) {
                    }
                    if (i4 != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    composer2 = startRestartGroup;
                    Modifier modifier42 = companion;
                    long j42 = j2;
                    m1919DropdownMenuIlH_yew(z2, function02, modifier42, j42, ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), popupProperties4, null, 0L, 0.0f, 0.0f, null, function32, composer2, (i3 & 8190) | ((i3 << 3) & 458752), (i3 >> 12) & 112, 1984);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    modifier3 = modifier42;
                    j3 = j42;
                    popupProperties3 = popupProperties4;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                popupProperties2 = popupProperties;
                if ((i2 & 32) == 0) {
                }
                function32 = function3;
                if ((74899 & i3) == 74898) {
                }
                if (i6 != 0) {
                }
                if (i4 != 0) {
                }
                if (i5 != 0) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                composer2 = startRestartGroup;
                Modifier modifier422 = companion;
                long j422 = j2;
                m1919DropdownMenuIlH_yew(z2, function02, modifier422, j422, ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), popupProperties4, null, 0L, 0.0f, 0.0f, null, function32, composer2, (i3 & 8190) | ((i3 << 3) & 458752), (i3 >> 12) & 112, 1984);
                if (ComposerKt.isTraceInProgress()) {
                }
                modifier3 = modifier422;
                j3 = j422;
                popupProperties3 = popupProperties4;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            j2 = j;
            i5 = i2 & 16;
            if (i5 != 0) {
            }
            popupProperties2 = popupProperties;
            if ((i2 & 32) == 0) {
            }
            function32 = function3;
            if ((74899 & i3) == 74898) {
            }
            if (i6 != 0) {
            }
            if (i4 != 0) {
            }
            if (i5 != 0) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
            composer2 = startRestartGroup;
            Modifier modifier4222 = companion;
            long j4222 = j2;
            m1919DropdownMenuIlH_yew(z2, function02, modifier4222, j4222, ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), popupProperties4, null, 0L, 0.0f, 0.0f, null, function32, composer2, (i3 & 8190) | ((i3 << 3) & 458752), (i3 >> 12) & 112, 1984);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier3 = modifier4222;
            j3 = j4222;
            popupProperties3 = popupProperties4;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        j2 = j;
        i5 = i2 & 16;
        if (i5 != 0) {
        }
        popupProperties2 = popupProperties;
        if ((i2 & 32) == 0) {
        }
        function32 = function3;
        if ((74899 & i3) == 74898) {
        }
        if (i6 != 0) {
        }
        if (i4 != 0) {
        }
        if (i5 != 0) {
        }
        if (ComposerKt.isTraceInProgress()) {
        }
        composer2 = startRestartGroup;
        Modifier modifier42222 = companion;
        long j42222 = j2;
        m1919DropdownMenuIlH_yew(z2, function02, modifier42222, j42222, ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), popupProperties4, null, 0L, 0.0f, 0.0f, null, function32, composer2, (i3 & 8190) | ((i3 << 3) & 458752), (i3 >> 12) & 112, 1984);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier3 = modifier42222;
        j3 = j42222;
        popupProperties3 = popupProperties4;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void DropdownMenuItem(final Function2<? super Composer, ? super Integer, Unit> function2, final Function0<Unit> function0, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, boolean z, MenuItemColors menuItemColors, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        Function2<? super Composer, ? super Integer, Unit> function24;
        int i3;
        Function0<Unit> function02;
        Modifier modifier2;
        int i4;
        Function2<? super Composer, ? super Integer, Unit> function25;
        int i5;
        Function2<? super Composer, ? super Integer, Unit> function26;
        int i6;
        boolean z2;
        MenuItemColors menuItemColors2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        MutableInteractionSource mutableInteractionSource2;
        PaddingValues paddingValues2;
        Composer composer2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function27;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        final boolean z3;
        final MenuItemColors menuItemColors3;
        final PaddingValues paddingValues3;
        final MutableInteractionSource mutableInteractionSource3;
        ScopeUpdateScope endRestartGroup;
        int i12;
        Composer startRestartGroup = composer.startRestartGroup(1826340448);
        ComposerKt.sourceInformation(startRestartGroup, "C(DropdownMenuItem)P(7,6,5,4,8,2)194@8925L12,180@6505L319:AndroidMenu.android.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            function24 = function2;
        } else {
            function24 = function2;
            if ((i & 6) == 0) {
                i3 = (startRestartGroup.changedInstance(function24) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
            function02 = function0;
        } else {
            function02 = function0;
            if ((i & 48) == 0) {
                i3 |= startRestartGroup.changedInstance(function02) ? 32 : 16;
            }
        }
        int i13 = i2 & 4;
        if (i13 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            modifier2 = modifier;
            i3 |= startRestartGroup.changed(modifier2) ? 256 : 128;
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                function25 = function22;
                i3 |= startRestartGroup.changedInstance(function25) ? 2048 : 1024;
                i5 = i2 & 16;
                if (i5 != 0) {
                    i3 |= 24576;
                } else if ((i & 24576) == 0) {
                    function26 = function23;
                    i3 |= startRestartGroup.changedInstance(function26) ? 16384 : 8192;
                    i6 = i2 & 32;
                    if (i6 == 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((196608 & i) == 0) {
                        z2 = z;
                        i3 |= startRestartGroup.changed(z2) ? 131072 : 65536;
                        if ((1572864 & i) == 0) {
                            if ((i2 & 64) == 0) {
                                menuItemColors2 = menuItemColors;
                                if (startRestartGroup.changed(menuItemColors2)) {
                                    i12 = 1048576;
                                    i3 |= i12;
                                }
                            } else {
                                menuItemColors2 = menuItemColors;
                            }
                            i12 = 524288;
                            i3 |= i12;
                        } else {
                            menuItemColors2 = menuItemColors;
                        }
                        i7 = i2 & 128;
                        if (i7 != 0) {
                            i3 |= 12582912;
                        } else if ((i & 12582912) == 0) {
                            i8 = i7;
                            i3 |= startRestartGroup.changed(paddingValues) ? 8388608 : 4194304;
                            i9 = i2 & 256;
                            if (i9 == 0) {
                                i3 |= 100663296;
                            } else if ((i & 100663296) == 0) {
                                i10 = i9;
                                i3 |= startRestartGroup.changed(mutableInteractionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                int i14 = i3;
                                if ((i3 & 38347923) == 38347922 || !startRestartGroup.getSkipping()) {
                                    startRestartGroup.startDefaults();
                                    if ((i & 1) != 0 || startRestartGroup.getDefaultsInvalid()) {
                                        if (i13 != 0) {
                                            modifier2 = Modifier.INSTANCE;
                                        }
                                        if (i4 != 0) {
                                            function25 = null;
                                        }
                                        if (i5 != 0) {
                                            function26 = null;
                                        }
                                        if (i6 != 0) {
                                            z2 = true;
                                        }
                                        if ((i2 & 64) != 0) {
                                            i11 = i14 & (-3670017);
                                            menuItemColors2 = MenuDefaults.INSTANCE.itemColors(startRestartGroup, 6);
                                        } else {
                                            i11 = i14;
                                        }
                                        PaddingValues dropdownMenuItemContentPadding = i8 != 0 ? MenuDefaults.INSTANCE.getDropdownMenuItemContentPadding() : paddingValues;
                                        mutableInteractionSource2 = i10 != 0 ? null : mutableInteractionSource;
                                        paddingValues2 = dropdownMenuItemContentPadding;
                                    } else {
                                        startRestartGroup.skipToGroupEnd();
                                        if ((i2 & 64) != 0) {
                                            i11 = i14 & (-3670017);
                                            paddingValues2 = paddingValues;
                                            mutableInteractionSource2 = mutableInteractionSource;
                                        } else {
                                            paddingValues2 = paddingValues;
                                            mutableInteractionSource2 = mutableInteractionSource;
                                            i11 = i14;
                                        }
                                    }
                                    Function2<? super Composer, ? super Integer, Unit> function29 = function26;
                                    boolean z4 = z2;
                                    MenuItemColors menuItemColors4 = menuItemColors2;
                                    Modifier modifier4 = modifier2;
                                    Function2<? super Composer, ? super Integer, Unit> function210 = function25;
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1826340448, i11, -1, "androidx.compose.material3.DropdownMenuItem (AndroidMenu.android.kt:179)");
                                    }
                                    composer2 = startRestartGroup;
                                    MenuKt.DropdownMenuItemContent(function24, function02, modifier4, function210, function29, z4, menuItemColors4, paddingValues2, mutableInteractionSource2, composer2, i11 & 268435454);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    modifier3 = modifier4;
                                    function27 = function210;
                                    function28 = function29;
                                    z3 = z4;
                                    menuItemColors3 = menuItemColors4;
                                    paddingValues3 = paddingValues2;
                                    mutableInteractionSource3 = mutableInteractionSource2;
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    mutableInteractionSource3 = mutableInteractionSource;
                                    composer2 = startRestartGroup;
                                    modifier3 = modifier2;
                                    function27 = function25;
                                    function28 = function26;
                                    z3 = z2;
                                    menuItemColors3 = menuItemColors2;
                                    paddingValues3 = paddingValues;
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenuItem$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                            invoke(composer3, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer3, int i15) {
                                            AndroidMenu_androidKt.DropdownMenuItem(function2, function0, modifier3, function27, function28, z3, menuItemColors3, paddingValues3, mutableInteractionSource3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            i10 = i9;
                            int i142 = i3;
                            if ((i3 & 38347923) == 38347922) {
                            }
                            startRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                            }
                            if (i13 != 0) {
                            }
                            if (i4 != 0) {
                            }
                            if (i5 != 0) {
                            }
                            if (i6 != 0) {
                            }
                            if ((i2 & 64) != 0) {
                            }
                            if (i8 != 0) {
                            }
                            if (i10 != 0) {
                            }
                            paddingValues2 = dropdownMenuItemContentPadding;
                            Function2<? super Composer, ? super Integer, Unit> function292 = function26;
                            boolean z42 = z2;
                            MenuItemColors menuItemColors42 = menuItemColors2;
                            Modifier modifier42 = modifier2;
                            Function2<? super Composer, ? super Integer, Unit> function2102 = function25;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer2 = startRestartGroup;
                            MenuKt.DropdownMenuItemContent(function24, function02, modifier42, function2102, function292, z42, menuItemColors42, paddingValues2, mutableInteractionSource2, composer2, i11 & 268435454);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            modifier3 = modifier42;
                            function27 = function2102;
                            function28 = function292;
                            z3 = z42;
                            menuItemColors3 = menuItemColors42;
                            paddingValues3 = paddingValues2;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        i8 = i7;
                        i9 = i2 & 256;
                        if (i9 == 0) {
                        }
                        i10 = i9;
                        int i1422 = i3;
                        if ((i3 & 38347923) == 38347922) {
                        }
                        startRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                        }
                        if (i13 != 0) {
                        }
                        if (i4 != 0) {
                        }
                        if (i5 != 0) {
                        }
                        if (i6 != 0) {
                        }
                        if ((i2 & 64) != 0) {
                        }
                        if (i8 != 0) {
                        }
                        if (i10 != 0) {
                        }
                        paddingValues2 = dropdownMenuItemContentPadding;
                        Function2<? super Composer, ? super Integer, Unit> function2922 = function26;
                        boolean z422 = z2;
                        MenuItemColors menuItemColors422 = menuItemColors2;
                        Modifier modifier422 = modifier2;
                        Function2<? super Composer, ? super Integer, Unit> function21022 = function25;
                        startRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        composer2 = startRestartGroup;
                        MenuKt.DropdownMenuItemContent(function24, function02, modifier422, function21022, function2922, z422, menuItemColors422, paddingValues2, mutableInteractionSource2, composer2, i11 & 268435454);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        modifier3 = modifier422;
                        function27 = function21022;
                        function28 = function2922;
                        z3 = z422;
                        menuItemColors3 = menuItemColors422;
                        paddingValues3 = paddingValues2;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    z2 = z;
                    if ((1572864 & i) == 0) {
                    }
                    i7 = i2 & 128;
                    if (i7 != 0) {
                    }
                    i8 = i7;
                    i9 = i2 & 256;
                    if (i9 == 0) {
                    }
                    i10 = i9;
                    int i14222 = i3;
                    if ((i3 & 38347923) == 38347922) {
                    }
                    startRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                    }
                    if (i13 != 0) {
                    }
                    if (i4 != 0) {
                    }
                    if (i5 != 0) {
                    }
                    if (i6 != 0) {
                    }
                    if ((i2 & 64) != 0) {
                    }
                    if (i8 != 0) {
                    }
                    if (i10 != 0) {
                    }
                    paddingValues2 = dropdownMenuItemContentPadding;
                    Function2<? super Composer, ? super Integer, Unit> function29222 = function26;
                    boolean z4222 = z2;
                    MenuItemColors menuItemColors4222 = menuItemColors2;
                    Modifier modifier4222 = modifier2;
                    Function2<? super Composer, ? super Integer, Unit> function210222 = function25;
                    startRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    composer2 = startRestartGroup;
                    MenuKt.DropdownMenuItemContent(function24, function02, modifier4222, function210222, function29222, z4222, menuItemColors4222, paddingValues2, mutableInteractionSource2, composer2, i11 & 268435454);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    modifier3 = modifier4222;
                    function27 = function210222;
                    function28 = function29222;
                    z3 = z4222;
                    menuItemColors3 = menuItemColors4222;
                    paddingValues3 = paddingValues2;
                    mutableInteractionSource3 = mutableInteractionSource2;
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                function26 = function23;
                i6 = i2 & 32;
                if (i6 == 0) {
                }
                z2 = z;
                if ((1572864 & i) == 0) {
                }
                i7 = i2 & 128;
                if (i7 != 0) {
                }
                i8 = i7;
                i9 = i2 & 256;
                if (i9 == 0) {
                }
                i10 = i9;
                int i142222 = i3;
                if ((i3 & 38347923) == 38347922) {
                }
                startRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                }
                if (i13 != 0) {
                }
                if (i4 != 0) {
                }
                if (i5 != 0) {
                }
                if (i6 != 0) {
                }
                if ((i2 & 64) != 0) {
                }
                if (i8 != 0) {
                }
                if (i10 != 0) {
                }
                paddingValues2 = dropdownMenuItemContentPadding;
                Function2<? super Composer, ? super Integer, Unit> function292222 = function26;
                boolean z42222 = z2;
                MenuItemColors menuItemColors42222 = menuItemColors2;
                Modifier modifier42222 = modifier2;
                Function2<? super Composer, ? super Integer, Unit> function2102222 = function25;
                startRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                composer2 = startRestartGroup;
                MenuKt.DropdownMenuItemContent(function24, function02, modifier42222, function2102222, function292222, z42222, menuItemColors42222, paddingValues2, mutableInteractionSource2, composer2, i11 & 268435454);
                if (ComposerKt.isTraceInProgress()) {
                }
                modifier3 = modifier42222;
                function27 = function2102222;
                function28 = function292222;
                z3 = z42222;
                menuItemColors3 = menuItemColors42222;
                paddingValues3 = paddingValues2;
                mutableInteractionSource3 = mutableInteractionSource2;
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            function25 = function22;
            i5 = i2 & 16;
            if (i5 != 0) {
            }
            function26 = function23;
            i6 = i2 & 32;
            if (i6 == 0) {
            }
            z2 = z;
            if ((1572864 & i) == 0) {
            }
            i7 = i2 & 128;
            if (i7 != 0) {
            }
            i8 = i7;
            i9 = i2 & 256;
            if (i9 == 0) {
            }
            i10 = i9;
            int i1422222 = i3;
            if ((i3 & 38347923) == 38347922) {
            }
            startRestartGroup.startDefaults();
            if ((i & 1) != 0) {
            }
            if (i13 != 0) {
            }
            if (i4 != 0) {
            }
            if (i5 != 0) {
            }
            if (i6 != 0) {
            }
            if ((i2 & 64) != 0) {
            }
            if (i8 != 0) {
            }
            if (i10 != 0) {
            }
            paddingValues2 = dropdownMenuItemContentPadding;
            Function2<? super Composer, ? super Integer, Unit> function2922222 = function26;
            boolean z422222 = z2;
            MenuItemColors menuItemColors422222 = menuItemColors2;
            Modifier modifier422222 = modifier2;
            Function2<? super Composer, ? super Integer, Unit> function21022222 = function25;
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            composer2 = startRestartGroup;
            MenuKt.DropdownMenuItemContent(function24, function02, modifier422222, function21022222, function2922222, z422222, menuItemColors422222, paddingValues2, mutableInteractionSource2, composer2, i11 & 268435454);
            if (ComposerKt.isTraceInProgress()) {
            }
            modifier3 = modifier422222;
            function27 = function21022222;
            function28 = function2922222;
            z3 = z422222;
            menuItemColors3 = menuItemColors422222;
            paddingValues3 = paddingValues2;
            mutableInteractionSource3 = mutableInteractionSource2;
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        function25 = function22;
        i5 = i2 & 16;
        if (i5 != 0) {
        }
        function26 = function23;
        i6 = i2 & 32;
        if (i6 == 0) {
        }
        z2 = z;
        if ((1572864 & i) == 0) {
        }
        i7 = i2 & 128;
        if (i7 != 0) {
        }
        i8 = i7;
        i9 = i2 & 256;
        if (i9 == 0) {
        }
        i10 = i9;
        int i14222222 = i3;
        if ((i3 & 38347923) == 38347922) {
        }
        startRestartGroup.startDefaults();
        if ((i & 1) != 0) {
        }
        if (i13 != 0) {
        }
        if (i4 != 0) {
        }
        if (i5 != 0) {
        }
        if (i6 != 0) {
        }
        if ((i2 & 64) != 0) {
        }
        if (i8 != 0) {
        }
        if (i10 != 0) {
        }
        paddingValues2 = dropdownMenuItemContentPadding;
        Function2<? super Composer, ? super Integer, Unit> function29222222 = function26;
        boolean z4222222 = z2;
        MenuItemColors menuItemColors4222222 = menuItemColors2;
        Modifier modifier4222222 = modifier2;
        Function2<? super Composer, ? super Integer, Unit> function210222222 = function25;
        startRestartGroup.endDefaults();
        if (ComposerKt.isTraceInProgress()) {
        }
        composer2 = startRestartGroup;
        MenuKt.DropdownMenuItemContent(function24, function02, modifier4222222, function210222222, function29222222, z4222222, menuItemColors4222222, paddingValues2, mutableInteractionSource2, composer2, i11 & 268435454);
        if (ComposerKt.isTraceInProgress()) {
        }
        modifier3 = modifier4222222;
        function27 = function210222222;
        function28 = function29222222;
        z3 = z4222222;
        menuItemColors3 = menuItemColors4222222;
        paddingValues3 = paddingValues2;
        mutableInteractionSource3 = mutableInteractionSource2;
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    public static final PopupProperties getDefaultMenuProperties() {
        return DefaultMenuProperties;
    }
}
